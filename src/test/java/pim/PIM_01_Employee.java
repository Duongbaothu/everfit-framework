package pim;

import pageObjects.PIMPageObjects.*;
import admin.Admin_01_Job_Titles;
import admin.Admin_03_Employment_Status;
import com.aventstack.extentreports.Status;
import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import reportConfig.ExtentTestManager;
import testData.EmployeeInfo;
import utilities.PropertiesConfig;

import java.lang.reflect.Method;

public class PIM_01_Employee extends BaseTest {
    private WebDriver driver;
    private String browserName, workEmail, dateAdded, image, employeeID;
    private LoginPageObject loginPage;
    private EmployeeInfo employeeInfo;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private AddEmployeePageObject addEmployeePage;
    private PersonalDetailsPageObject personalDetailsPage;
    private ChangeProfilePicturePageObject changeProfilePicturePage;
    private ContactDetailsPageObject contactDetailsPage;
    private EmergencyContactsPageObject emergencyContactsPage;
    private DependentsPageObject dependentsPage;
    private ImmigrationPageObject immigrationPage;
    private EmployeeJobPageObject employeeJobPage;
    private SalaryPageObject salaryPage;
    private ReportToPageObject reportToPage;
    private QualificationsPageObject qualificationsPage;
    private PropertiesConfig propertiesConfig;

    @Parameters({"browser", "server"})
    @BeforeClass
    public void beforeClass(String browserName, String serverName){
        propertiesConfig = PropertiesConfig.getProperties(serverName);
        driver = EnvironmentFactoryProvider.createDriver(browserName, serverName);
        this.browserName = browserName;
        employeeInfo = EmployeeInfo.getEmployee();

        employeeInfo.setImage("image01.jpg");
        employeeInfo.setWorkEmail(getEmailRandom());
        employeeInfo.setDateAdded(getCurrentDate());
        employeeInfo.setJobTitleEmployee(Admin_01_Job_Titles.jobTitle);
        employeeInfo.setEmploymentStatus(Admin_03_Employment_Status.employmentStatusName);

        loginPage = PageGeneratorManager.getLoginPage(driver, propertiesConfig);

        loginPage.loginToAdmin();

        dashboardPage = PageGeneratorManager.getDashboardPage(driver);

        dashboardPage.openMainMenuPageByName("PIM");
        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
    }

    @Test
    public void Employee_01_Add_New(Method method){
        ExtentTestManager.startTest(method.getName(), "Employee_01_Add_New");
        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Open 'New Customer' page");

        employeeListPage.clickToNavTabItemByText("Add Employee");
        addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);

        addEmployeePage.enterToTextboxByName("firstName", employeeInfo.getFirstName());
        addEmployeePage.enterToTextboxByName("lastName", employeeInfo.getLastName());
        employeeInfo.setEmployeeID(addEmployeePage.getTextboxAttributeByText("Employee Id"));

        addEmployeePage.clickToButtonByText("Save");

        Assert.assertTrue(addEmployeePage.isSuccessMessageDisplayed("Successfully Saved"));
        addEmployeePage.waitForSpinnerIconInvisible();

        personalDetailsPage = PageGeneratorManager.getPersonalDetailsPage(driver);
        personalDetailsPage.waitForSpinnerIconInvisible();

        Assert.assertEquals(personalDetailsPage.getTextboxAttributeByName("firstName"), employeeInfo.getFirstName());
        Assert.assertEquals(personalDetailsPage.getTextboxAttributeByName("lastName"), employeeInfo.getLastName());
        Assert.assertEquals(personalDetailsPage.getTextboxAttributeByText("Employee Id"), employeeInfo.getEmployeeID());

        personalDetailsPage.clickToNavTabItemByText("Employee List");
        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);

        employeeListPage.enterToTextboxByText("Employee Id", employeeInfo.getEmployeeID());
        employeeListPage.clickToButtonByText("Search");

        Assert.assertTrue(employeeListPage.isValueDisplayedAtColumnName("Id", "1", employeeInfo.getEmployeeID()));
        Assert.assertTrue(employeeListPage.isValueDisplayedAtColumnName("First (& Middle) Name", "1", employeeInfo.getFirstName()));
        Assert.assertTrue(employeeListPage.isValueDisplayedAtColumnName("Last Name", "1", employeeInfo.getLastName()));
    }

    @Test
    public void Employee_02_Personal_Details(Method method){
        ExtentTestManager.startTest(method.getName(), "Employee_02_Personal_Details");
        employeeListPage.clickEmployeeActionIconByClass(employeeInfo.getEmployeeID(), "bi-pencil-fill");
        personalDetailsPage = PageGeneratorManager.getPersonalDetailsPage(driver);

        Assert.assertTrue(personalDetailsPage.isPageHeaderDisplayed("Personal Details"));

        Assert.assertEquals(personalDetailsPage.getTextboxAttributeByName("firstName"), employeeInfo.getFirstName());
        Assert.assertEquals(personalDetailsPage.getTextboxAttributeByName("lastName"), employeeInfo.getLastName());
        Assert.assertEquals(personalDetailsPage.getTextboxAttributeByText("Employee Id"), employeeInfo.getEmployeeID());

        personalDetailsPage.setPersionalDetail(employeeInfo);

        personalDetailsPage.clickToSaveButtonWithHeader("Personal Details");

        Assert.assertTrue(personalDetailsPage.isSuccessMessageDisplayed("Successfully Updated"));
        personalDetailsPage.waitForSpinnerIconInvisible();

        personalDetailsPage.verifyPersonalDetail(employeeInfo);
    }

    @Test
    public void Employee_03_Upload_Avatar(Method method){
        ExtentTestManager.startTest(method.getName(), "Employee_03_Upload_Avatar");
        personalDetailsPage.clickToAvatarImg();

        changeProfilePicturePage = PageGeneratorManager.getChangeProfilePicturePage(driver);
        Assert.assertTrue(changeProfilePicturePage.isPageHeaderDisplayed("Change Profile Picture"));

        changeProfilePicturePage.uploadAvatar(driver, employeeInfo.getImage());

        Assert.assertTrue(changeProfilePicturePage.isAvatarLoadedSuccess(employeeInfo.getImage()));

        changeProfilePicturePage.clickToButtonByText("Save");

        Assert.assertTrue(changeProfilePicturePage.isSuccessMessageDisplayed("Successfully Updated"));
        changeProfilePicturePage.waitForSpinnerIconInvisible();
    }

    @Test
    public void Employee_04_Contact_Details(Method method){
        ExtentTestManager.startTest(method.getName(), "Employee_04_Contact_Details");
        changeProfilePicturePage.clickToNavTabItemByText("Contact Details");

        contactDetailsPage = PageGeneratorManager.getContactDetailsPage(driver);
        Assert.assertTrue(contactDetailsPage.isPageHeaderDisplayed("Contact Details"));

        contactDetailsPage.setContactDetail(employeeInfo);

        contactDetailsPage.clickToButtonByText("Save");

        Assert.assertTrue(contactDetailsPage.isSuccessMessageDisplayed("Successfully Updated"));
        contactDetailsPage.waitForSpinnerIconInvisible();

        contactDetailsPage.verifyContactDetail(employeeInfo);
    }

    @Test
    public void Employee_05_Emergency_Contacts(Method method){
        ExtentTestManager.startTest(method.getName(), "Employee_05_Emergency_Contacts");
        contactDetailsPage.clickToNavTabItemByText("Emergency Contacts");

        emergencyContactsPage = PageGeneratorManager.getEmergencyContactsPage(driver);

        emergencyContactsPage.clickToButtonIncludeLabel("Assigned Emergency Contacts", "Add");
        Assert.assertTrue(emergencyContactsPage.isPageHeaderDisplayed("Save Emergency Contact"));

        emergencyContactsPage.setEmergencyContact(employeeInfo);

        emergencyContactsPage.clickToButtonByText("Save");

        Assert.assertTrue(emergencyContactsPage.isSuccessMessageDisplayed("Successfully Saved"));
        emergencyContactsPage.waitForSpinnerIconInvisible();

        emergencyContactsPage.verifyEmergencyContact(employeeInfo);

        emergencyContactsPage.addAttachmentFile(employeeInfo);

        emergencyContactsPage.clickToButtonByText("Save");

        Assert.assertTrue(emergencyContactsPage.isSuccessMessageDisplayed("Successfully Saved"));
        emergencyContactsPage.waitForSpinnerIconInvisible();

        emergencyContactsPage.verifyAttachmentsTable(employeeInfo);
    }

    @Test
    public void Employee_06_Dependents(Method method){
        ExtentTestManager.startTest(method.getName(), "Employee_06_Dependents");
        emergencyContactsPage.clickToNavTabItemByText("Dependents");

        dependentsPage = PageGeneratorManager.getDependentsPage(driver);

        dependentsPage.clickToButtonIncludeLabel("Assigned Dependents", "Add");
        Assert.assertTrue(dependentsPage.isPageHeaderDisplayed("Add Dependent"));

        dependentsPage.enterToTextboxByText("Name", employeeInfo.getDependentName());
        dependentsPage.selectInDropdown("Relationship", employeeInfo.getDependentRelationship());
        dependentsPage.enterToTextboxByText("Date of Birth", employeeInfo.getDependentDateOfBirth());

        dependentsPage.clickToButtonByText("Save");

        Assert.assertTrue(dependentsPage.isSuccessMessageDisplayed("Successfully Saved"));
        dependentsPage.waitForSpinnerIconInvisible();

        Assert.assertTrue(dependentsPage.isValueDisplayedAtColumnNameWithTableName("Assigned Dependents","Name", "1", employeeInfo.getDependentName()));
        Assert.assertTrue(dependentsPage.isValueDisplayedAtColumnNameWithTableName("Assigned Dependents","Relationship", "1", employeeInfo.getDependentRelationship()));
        Assert.assertTrue(dependentsPage.isValueDisplayedAtColumnNameWithTableName("Assigned Dependents","Date of Birth", "1", employeeInfo.getDependentDateOfBirth()));

        dependentsPage.addAttachmentFile(employeeInfo);

        dependentsPage.clickToButtonByText("Save");

        Assert.assertTrue(dependentsPage.isSuccessMessageDisplayed("Successfully Saved"));
        dependentsPage.waitForSpinnerIconInvisible();

        dependentsPage.verifyAttachmentsTable(employeeInfo);
    }

    @Test
    public void Employee_07_Immigration(Method method){
        ExtentTestManager.startTest(method.getName(), "Employee_07_Immigration");
        dependentsPage.clickToNavTabItemByText("Immigration");

        immigrationPage = PageGeneratorManager.getImmigrationPage(driver);

        immigrationPage.clickToButtonIncludeLabel("Assigned Immigration Records", "Add");
        Assert.assertTrue(immigrationPage.isPageHeaderDisplayed("Add Immigration"));

        immigrationPage.setImmigration(employeeInfo);

        immigrationPage.clickToButtonByText("Save");

        Assert.assertTrue(immigrationPage.isSuccessMessageDisplayed("Successfully Saved"));
        immigrationPage.waitForSpinnerIconInvisible();

        immigrationPage.verifyImmigration(employeeInfo);

        immigrationPage.addAttachmentFile(employeeInfo);

        immigrationPage.clickToButtonByText("Save");

        Assert.assertTrue(immigrationPage.isSuccessMessageDisplayed("Successfully Saved"));
        immigrationPage.waitForSpinnerIconInvisible();

        immigrationPage.verifyAttachmentsTable(employeeInfo);
    }

    @Test
    public void Employee_08_Job(Method method){
        ExtentTestManager.startTest(method.getName(), "Employee_08_Job");
        immigrationPage.clickToNavTabItemByText("Job");

        employeeJobPage = PageGeneratorManager.getEmployeeJobPage(driver);

        employeeJobPage.setEmployeeJob(employeeInfo);

        employeeJobPage.clickToToggleByLabel("Include Employment Contract Details");

        employeeJobPage.setEmployeeContractDetails(employeeInfo);

        Assert.assertTrue(employeeJobPage.isFileLoadedSuccess("Contract Details", employeeInfo.getImage()));

        employeeJobPage.clickToButtonByText("Save");

        Assert.assertTrue(employeeJobPage.isSuccessMessageDisplayed("Successfully Updated"));
        employeeJobPage.waitForSpinnerIconInvisible();

        employeeJobPage.verifyEmployeeJob(employeeInfo);
        employeeJobPage.verifyEmployeeContractDetails(employeeInfo);
    }

    @Test
    public void Employee_09_Salary(Method method){
        ExtentTestManager.startTest(method.getName(), "Employee_09_Salary");
        employeeJobPage.clickToNavTabItemByText("Salary");

        salaryPage = PageGeneratorManager.getSalaryPage(driver);

        salaryPage.clickToButtonIncludeLabel("Assigned Salary Components", "Add");
        Assert.assertTrue(salaryPage.isPageHeaderDisplayed("Add Salary Component"));

        salaryPage.setSalary(employeeInfo);

        salaryPage.clickToToggleByLabel("Include Direct Deposit Details");
        salaryPage.setDirectDepositDetails(employeeInfo);

        salaryPage.clickToButtonByText("Save");

        Assert.assertTrue(salaryPage.isSuccessMessageDisplayed("Successfully Saved"));
        salaryPage.waitForSpinnerIconInvisible();

        salaryPage.verifySalary(employeeInfo);

        salaryPage.verifyDirectDepositDetails(employeeInfo);

        salaryPage.addAttachmentFile(employeeInfo);

        salaryPage.clickToButtonByText("Save");

        Assert.assertTrue(salaryPage.isSuccessMessageDisplayed("Successfully Saved"));
        salaryPage.waitForSpinnerIconInvisible();

        salaryPage.verifyAttachmentsTable(employeeInfo);
    }

    @Test
    public void Employee_10_Report(Method method){
        ExtentTestManager.startTest(method.getName(), "Employee_10_Report");
        salaryPage.clickToNavTabItemByText("Report-to");

        reportToPage = PageGeneratorManager.getReportToPage(driver);

        reportToPage.clickToButtonIncludeLabel("Assigned Supervisors", "Add");
        Assert.assertTrue(reportToPage.isPageHeaderDisplayed("Add Supervisor"));

        reportToPage.setSupervisor(employeeInfo);

        reportToPage.clickToButtonByText("Save");

        Assert.assertTrue(reportToPage.isSuccessMessageDisplayed("Successfully Saved"));
        reportToPage.waitForSpinnerIconInvisible();

        reportToPage.verifySupervisor(employeeInfo);

        reportToPage.clickToButtonIncludeLabel("Assigned Subordinates", "Add");
        Assert.assertTrue(reportToPage.isPageHeaderDisplayed("Add Subordinate"));

        reportToPage.setSubordinate(employeeInfo);

        reportToPage.clickToButtonByText("Save");

        Assert.assertTrue(reportToPage.isSuccessMessageDisplayed("Successfully Saved"));
        reportToPage.waitForSpinnerIconInvisible();

        reportToPage.verifySubordinate(employeeInfo);

        reportToPage.addAttachmentFile(employeeInfo);

        reportToPage.clickToButtonByText("Save");

        Assert.assertTrue(reportToPage.isSuccessMessageDisplayed("Successfully Saved"));
        reportToPage.waitForSpinnerIconInvisible();

        reportToPage.verifyAttachmentsTable(employeeInfo);
    }

    @Test
    public void Employee_11_Qualifications(Method method){
        ExtentTestManager.startTest(method.getName(), "Employee_11_Qualifications");
        reportToPage.clickToNavTabItemByText("Qualifications");

        qualificationsPage = PageGeneratorManager.getQualificationsPage(driver);

        qualificationsPage.clickToButtonIncludeLabel("Work Experience", "Add");
        Assert.assertTrue(qualificationsPage.isPageHeaderDisplayed("Add Work Experience"));

        qualificationsPage.setQualification(employeeInfo);

        qualificationsPage.clickToButtonByText("Save");

        Assert.assertTrue(qualificationsPage.isSuccessMessageDisplayed("Successfully Saved"));
        qualificationsPage.waitForSpinnerIconInvisible();

        qualificationsPage.verifyQualification(employeeInfo);
    }

    @AfterClass
    public void afterClass(){
        closeBrowser();
    }
}
