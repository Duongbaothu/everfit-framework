package commons;

import factoryBrowser.*;
import factoryEnvironment.DevFactory;
import factoryEnvironment.EnvironmentFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import utilities.PropertiesConfig;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


public class BaseTest {
    protected final Logger log;
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public BaseTest(){
        log = LogManager.getLogger(getClass());
    }

    public class EnvironmentFactoryProvider {
        private static ThreadLocal<WebDriver> tDriver = new ThreadLocal<>();

        public static EnvironmentFactory getFactory(String browserName, String environment) {
            switch (environment.toUpperCase()) {
                case "DEV":
                    return new DevFactory(browserName);
                default:
                    throw new RuntimeException("Unsupported environment: " + environment);
            }
        }

        public static WebDriver createDriver(String browserName, String environment) {
            if (tDriver.get() == null) {
                PropertiesConfig propertiesConfig = new PropertiesConfig(environment.toLowerCase());
                String url = propertiesConfig.getApplicationUrl();
                String username = propertiesConfig.getApplicationUserName();
                String password = propertiesConfig.getApplicationPassword();

                WebDriver driver = getFactory(browserName, environment).createDriver();

                driver.manage().window().setPosition(new Point(0, 0));
                driver.manage().window().setSize(new Dimension(1920, 1080));
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.getGlobalConstants().getLongTimeout()));
                driver.get(url);

                tDriver.set(driver);

            }
            return tDriver.get();
        }

        public static void removeDriver() {
            if (tDriver.get() != null) {
                tDriver.get().quit();
                tDriver.remove();
            }
        }
    }

    public static int getTodayWeekday() {
        LocalDate today = LocalDate.now();
        return today.getDayOfWeek().getValue(); // Returns 1 for Monday, 7 for Sunday
    }


    protected void closeBrowser() {
        String cmd = null;
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            log.info("OS name = " + osName);

            String driverInstanceName = driver != null ? driver.toString().toLowerCase() : "";
            log.info("Driver instance name = " + driverInstanceName);

            String browserDriverName = null;

            if (driverInstanceName.contains("chrome")) {
                browserDriverName = "chromedriver";
            } else if (driverInstanceName.contains("internetexplorer")) {
                browserDriverName = "IEDriverServer";
            } else if (driverInstanceName.contains("firefox")) {
                browserDriverName = "geckodriver";
            } else if (driverInstanceName.contains("edge")) {
                browserDriverName = "msedgedriver";
            } else if (driverInstanceName.contains("opera")) {
                browserDriverName = "operadriver";
            } else if (driverInstanceName.contains("safari")) {
                browserDriverName = "safaridriver";
            }

            if (browserDriverName != null) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
                } else {
                    cmd = "pkill " + browserDriverName;
                }
            } else {
                log.warn("Could not determine browser driver name, skipping kill command.");
            }

            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (Exception e) {
            log.error("Error occurred while closing the browser: " + e.getMessage());
        } finally {
            if (cmd != null) {
                try {
                    Process process = Runtime.getRuntime().exec(cmd);
                    process.waitFor();
                } catch (IOException e) {
                    log.error("Failed to execute command: " + e.getMessage());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    log.error("Command execution interrupted: " + e.getMessage());
                }
            } else {
                log.warn("Command is null, skipping process execution.");
            }
        }
    }



    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
            log.info("------------PASSED-------------");
        } catch (Throwable e) {
            log.info("------------FAILED-------------");
            pass = false;

            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
            log.info("------------PASSED-------------");
        } catch (Throwable e) {
            log.info("------------FAILED-------------");
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info("------------PASSED-------------");
        } catch (Throwable e) {
            log.info("------------FAILED-------------");
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    @BeforeSuite
    public void deleteFileInReport() {
        log.info("Remove all file in ReportNG screenshot (image)");
        deleteAllFileInFolder("reportNGImage");
        log.info("Delete success");
        log.info("Remove all file in Allure attachment (json file)");
        deleteAllFileInFolder("allure-json");
    }

    public void deleteAllFileInFolder(String folderName) {
        try {
            String pathFolderDownload = GlobalConstants.getGlobalConstants().getRelativeProjectPath() + File.separator +folderName;
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            if (listOfFiles.length != 0) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
                        new File(listOfFiles[i].toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
