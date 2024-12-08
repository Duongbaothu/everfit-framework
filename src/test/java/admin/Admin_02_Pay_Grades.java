package admin;

import pageObjects.AdminPageObjects.PayGradePageObject;
import pageObjects.AdminPageObjects.SystemUsersPageObject;
import pageObjects.PIMPageObjects.DashboardPageObject;
import pageObjects.PIMPageObjects.LoginPageObject;
import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import reportConfig.ExtentTestManager;
import utilities.PropertiesConfig;

import java.lang.reflect.Method;

public class Admin_02_Pay_Grades extends BaseTest {
    private WebDriver driver;
    private String browserName;
    public static String payGradeName;
    private String currency;
    private String expectedCurrencyInTable;

    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private SystemUsersPageObject systemUsersPage;
    private PayGradePageObject addPayGradePage;
    private PropertiesConfig propertiesConfig;

    @Parameters({"browser", "server"})
    @BeforeClass
    public void beforeClass(String browserName, String serverName){
        propertiesConfig = PropertiesConfig.getProperties(serverName);
        driver = EnvironmentFactoryProvider.createDriver(browserName, serverName);
        this.browserName = browserName;

        payGradeName = "Pay Grade" + " " + getNumberRandom();
        currency = "USD - United States Dollar";
        expectedCurrencyInTable = "United States Dollar";

        loginPage = PageGeneratorManager.getLoginPage(driver, propertiesConfig);

        loginPage.loginToAdmin();

        dashboardPage = PageGeneratorManager.getDashboardPage(driver);

        dashboardPage.openMainMenuPageByName("Admin");
        systemUsersPage = PageGeneratorManager.getSystemUsersPage(driver);
    }

    @Test
    public void Pay_Grade_01_Add_New_Empty_Data(Method method){
        ExtentTestManager.startTest(method.getName(), "Pay_Grade_01_Add_New_Empty_Data");

        systemUsersPage.clickToHeaderMenu("Job");
        systemUsersPage.clickToItemInHeaderMenu("Pay Grades");
        addPayGradePage = PageGeneratorManager.getPayGradePage(driver);

        addPayGradePage.clickToButtonByText("Add");


        addPayGradePage.clickToButtonByText("Save");

        Assert.assertEquals(addPayGradePage.getTextboxErrorMessageByTextboxLabel("Name"), "Required");
    }

    @Test
    public void Pay_Grade_02_Add_New_Success(Method method){
        ExtentTestManager.startTest(method.getName(), "Pay_Grade_02_Add_New_Success");

        addPayGradePage.clickToHeaderMenu("Job");
        addPayGradePage.clickToItemInHeaderMenu("Pay Grades");
        addPayGradePage = PageGeneratorManager.getPayGradePage(driver);

        addPayGradePage.clickToButtonByText("Add");

        addPayGradePage.enterToTextboxByText("Name", payGradeName);

        addPayGradePage.clickToButtonByText("Save");

        addPayGradePage.waitForSpinnerIconInvisible();

        Assert.assertTrue(addPayGradePage.isSuccessMessageDisplayed("No Records Found"));

        Assert.assertEquals(addPayGradePage.getTextboxAttributeByText("Name"), payGradeName);

        addPayGradePage.clickToButtonIncludeLabel("Currencies", "Add");
        Assert.assertTrue(addPayGradePage.isPageHeaderDisplayed("Add Currency"));

        addPayGradePage.selectInDropdown("Currency", currency);

        addPayGradePage.clickToSaveButtonWithHeader("Add Currency");

        addPayGradePage.waitForSpinnerIconInvisible();


        Assert.assertTrue(addPayGradePage.isValueDisplayedAtColumnNameInCurrenciesTable("Currency", "1", expectedCurrencyInTable));

        addPayGradePage.clickToHeaderMenu("Job");
        addPayGradePage.clickToItemInHeaderMenu("Pay Grades");
        addPayGradePage = PageGeneratorManager.getPayGradePage(driver);

        Assert.assertTrue(addPayGradePage.isRecordPresentInTableByColumnName("Name", payGradeName));
        Assert.assertTrue(addPayGradePage.isRecordPresentInTableByColumnName("Currency", expectedCurrencyInTable));
    }

    @AfterClass
    public void afterClass(){
        closeBrowser();
    }
}
