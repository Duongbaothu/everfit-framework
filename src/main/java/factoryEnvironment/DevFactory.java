package factoryEnvironment;

import factoryBrowser.*;
import org.openqa.selenium.WebDriver;

public class DevFactory implements EnvironmentFactory {
    private WebDriver driver;
    private String browserName;

    public DevFactory(String browserName) {
        this.browserName = browserName;
    }

    @Override
    public WebDriver createDriver() {
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        switch (browser) {
            case FIREFOX:
                driver = new FirefoxDriverManager().getBrowserDriver();
                break;
            case CHROME:
                driver = new ChromeDriverManager().getBrowserDriver();
                break;
            case EDGE:
                driver = new EdgeDriverManager().getBrowserDriver();
                break;
            case CHROME_HEADLESS:
                driver = new HeadlessChromeDriverManager().getBrowserDriver();
                break;
            case FIREFOX_HEADLESS:
                driver = new HeadlessFirefoxDriverManager().getBrowserDriver();
                break;
            case EDGE_HEADLESS:
                driver = new HeadlessEdgeDriverManager().getBrowserDriver();
                break;
            default:
                throw new RuntimeException("Browser not supported: " + browserName);
        }
        return driver;
    }
}
