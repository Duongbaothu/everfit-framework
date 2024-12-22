import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.ClientListPageObject;
import pageObjects.ClientPageObject;
import pageObjects.TasksPageObject;
import pageObjects.LoginPageObject;
import reportConfig.ExtentTestManager;
import testData.ClientData;
import testData.HabitTaskData;
import utilities.ExcelConfig;
import utilities.PropertiesConfig;

import java.lang.reflect.Method;

public class Tasks extends BaseTest {
    private WebDriver driver;
    private String browserName;
    private ExcelConfig excelConfig;
    //private String clientName;
    //private String habitName, goalValue, habitUnit, habitFrequency, startDate, endDate, habitRepeat, reminderTime
    private String startDateOnSchedule, endDateOnSchedule;

    private LoginPageObject loginPage;
    private TasksPageObject tasksPage;
    private ClientListPageObject clientListPage;
    private ClientPageObject clientPage;
    private PropertiesConfig propertiesConfig;

    @Parameters({"browser", "server"})
    @BeforeClass
    public void beforeClass(String browserName, String serverName){
        propertiesConfig = PropertiesConfig.getProperties(serverName);
        driver = EnvironmentFactoryProvider.createDriver(browserName, serverName);
        this.browserName = browserName;

        loginPage = PageGeneratorManager.getLoginPage(driver, propertiesConfig);
        loginPage.loginToEverfit();

        clientListPage = PageGeneratorManager.getClientListPage(driver);
        Assert.assertTrue(clientListPage.isAllClientListPageDisplayed());

        excelConfig = ExcelConfig.getExcelData();

        ClientData clientData = clientListPage.getClientDataFromFile(1);

        clientListPage.clickToClient(clientData.getClientName());
        clientPage = PageGeneratorManager.getClientPage(driver);
        Assert.assertEquals(clientPage.actualClientName, clientData.getClientName());

    }

    @Test
    public void Habit_01_Create_New_Empty_Data_Unsuccess(Method method){
        ExtentTestManager.startTest(method.getName(), "Habit_01_Create_New_Empty_Data");
        clientPage.clickToTasksTab();
        tasksPage = PageGeneratorManager.getTasksPage(driver);
        tasksPage.hoverToToday();
        tasksPage.clickToAddNewIcon();
        Assert.assertTrue(tasksPage.isCreateTaskPopupDisplayed());
        tasksPage.clickToHabitIcon();
        Assert.assertTrue(tasksPage.isAddNewHabitPopupDisplayed());
        tasksPage.clickToCreateCustomHabitButton();
        Assert.assertTrue(tasksPage.isHabitNameTxtDisplayed());

        tasksPage.clickToSaveCloseButton();

        Assert.assertEquals(tasksPage.getBackgroundBorderOfHabitNameTxt(), "rgb(234, 49, 74)");
        Assert.assertEquals(tasksPage.getBackgroundBorderOfGoalValueTxt(), "rgb(234, 49, 74)");
        Assert.assertEquals(tasksPage.getEndDateTextboxErrorMessage(), "Please choose an end date");
        Assert.assertFalse(tasksPage.isSaveCloseButtonEnabled());
        Assert.assertFalse(tasksPage.isSaveButtonEnabled());
    }

    @Test
    public void Habit_02_Create_New_EndDate_Equals_To_StartDate_Unsuccess(Method method){
        ExtentTestManager.startTest(method.getName(), "Habit_02_Create_New_EndDate_Equals_To_StartDate_Unsuccess");

        tasksPage.clickToCloseTaskButton();
        tasksPage.hoverToToday();
        tasksPage.clickToAddNewIcon();
        Assert.assertTrue(tasksPage.isCreateTaskPopupDisplayed());
        tasksPage.clickToHabitIcon();
        Assert.assertTrue(tasksPage.isAddNewHabitPopupDisplayed());
        tasksPage.clickToCreateCustomHabitButton();
        Assert.assertTrue(tasksPage.isHabitNameTxtDisplayed());

        HabitTaskData habitTaskData = tasksPage.getHabitTaskDataFromFile(1);

        tasksPage.enterToHabitNameTextbox(habitTaskData.getHabitName());


        tasksPage.enterToGoalValueTextbox(habitTaskData.getGoalValue());
        tasksPage.selectInDropdown("habit-unit", habitTaskData.getHabitUnit());
        tasksPage.selectInDropdown("habit-frequency", habitTaskData.getHabitFrequency());

        Assert.assertEquals(tasksPage.getItemTextSelectedInCalendarByText("START DATE", "startDate"), habitTaskData.getStartDateText());
        Assert.assertEquals(tasksPage.getItemTextSelectedInCalendarByText("END DATE", "endDate"), habitTaskData.getEndDateText());

        tasksPage.openCalendarByName("endDate");
        tasksPage.selectToDayInCalendarByLabel("END DATE", "rdtToday");

        Assert.assertEquals(tasksPage.getEndDateTextboxErrorMessage(), "Minimum 7 days apart");
        Assert.assertFalse(tasksPage.isSaveCloseButtonEnabled());
        Assert.assertFalse(tasksPage.isSaveButtonEnabled());

    }

    @Test
    public void Habit_03_Create_New_Success(Method method){
        ExtentTestManager.startTest(method.getName(), "Habit_03_Create_New_Success");

        tasksPage.clickToCloseTaskButton();
        Assert.assertTrue(tasksPage.isConfirmPopupDisplayed("Discard Changes?"));
        tasksPage.clickToButtonInConfirmPopup("Discard Changes");

        tasksPage.hoverToToday();
        tasksPage.clickToAddNewIcon();
        Assert.assertTrue(tasksPage.isCreateTaskPopupDisplayed());
        tasksPage.clickToHabitIcon();
        Assert.assertTrue(tasksPage.isAddNewHabitPopupDisplayed());
        tasksPage.clickToCreateCustomHabitButton();
        Assert.assertTrue(tasksPage.isHabitNameTxtDisplayed());

        HabitTaskData habitTaskData = tasksPage.getHabitTaskDataFromFile(1);
        startDateOnSchedule = habitTaskData.getStartDate().substring(0, 2);
        endDateOnSchedule = habitTaskData.getEndDate().substring(0, 2);

        tasksPage.enterToHabitNameTextbox(habitTaskData.getHabitName());
        tasksPage.enterToGoalValueTextbox(habitTaskData.getGoalValue());
        tasksPage.selectInDropdown("habit-unit", habitTaskData.getHabitUnit());
        tasksPage.selectInDropdown("habit-frequency", habitTaskData.getHabitFrequency());

        Assert.assertEquals(tasksPage.getItemTextSelectedInCalendarByText("START DATE", "startDate"), habitTaskData.getStartDateText());
        Assert.assertEquals(tasksPage.getItemTextSelectedInCalendarByText("END DATE", "endDate"), habitTaskData.getEndDateText());

        tasksPage.selectInDropdown("habit-repeat", habitTaskData.getHabitRepeat());
        Assert.assertTrue(tasksPage.isTodaySelectedInRepeatWeekly());
        tasksPage.clickToSetReminderToggle();
        tasksPage.selectInDropdown("evfSelectBoxContainer", habitTaskData.getReminderTime());

        tasksPage.clickToSaveCloseButton();
        Assert.assertTrue(tasksPage.isTaskTitleDisplayedOnScheduleAtDateSelected(startDateOnSchedule, habitTaskData.getHabitName()));
        Assert.assertTrue(tasksPage.isTaskTitleDisplayedOnScheduleAtDateSelected(endDateOnSchedule, habitTaskData.getHabitName()));
    }

    @Test
    public void Habit_04_Modify_Success(Method method){
        ExtentTestManager.startTest(method.getName(), "Habit_04_Modify_Success");

        HabitTaskData oldHabitTaskData = tasksPage.getHabitTaskDataFromFile(1);

        tasksPage.clickToTask(startDateOnSchedule, oldHabitTaskData.getHabitName());
        Assert.assertTrue(tasksPage.isHabitNameTxtDisplayed());

        Assert.assertEquals(tasksPage.getHabitNameTextboxAttribute(), oldHabitTaskData.getHabitName());
        Assert.assertEquals(tasksPage.getGoalValueTextboxAttribute(), oldHabitTaskData.getGoalValue());
        Assert.assertEquals(tasksPage.getItemTextSelectedInDropdownByClass("habit-unit"), oldHabitTaskData.getHabitUnit());
        Assert.assertEquals(tasksPage.getItemTextSelectedInDropdownByClass("habit-frequency"), oldHabitTaskData.getHabitFrequency());
        Assert.assertEquals(tasksPage.getItemTextSelectedInCalendarByText("START DATE", "startDate"), oldHabitTaskData.getStartDateText());
        Assert.assertEquals(tasksPage.getItemTextSelectedInCalendarByText("END DATE", "endDate"), oldHabitTaskData.getEndDateText());
        Assert.assertEquals(tasksPage.getItemTextSelectedInDropdownByClass("habit-repeat"), oldHabitTaskData.getHabitRepeat());
        Assert.assertTrue(tasksPage.isTodaySelectedInRepeatWeekly());
        Assert.assertEquals(tasksPage.getItemTextSelectedInReminderDropdown(), oldHabitTaskData.getReminderTime());

        HabitTaskData newHabitTaskData = tasksPage.getHabitTaskDataFromFile(2);

        tasksPage.enterToHabitNameTextbox(newHabitTaskData.getHabitName());
        tasksPage.enterToGoalValueTextbox(newHabitTaskData.getGoalValue());
        tasksPage.selectInDropdown("habit-unit", newHabitTaskData.getHabitUnit());
        tasksPage.selectInDropdown("habit-frequency", newHabitTaskData.getHabitFrequency());
        tasksPage.selectDateInCalendarByLabel("startDate", newHabitTaskData.getStartDate());
        tasksPage.selectDateInCalendarByLabel("endDate", newHabitTaskData.getEndDate());
        tasksPage.selectInDropdown("habit-repeat", newHabitTaskData.getHabitRepeat());
        tasksPage.selectInDropdown("evfSelectBoxContainer", newHabitTaskData.getReminderTime());

        tasksPage.clickToSaveCloseButton();
        tasksPage.clickToWeekNumber("4 Week");
        Assert.assertEquals(tasksPage.getTaskNumber(newHabitTaskData.getHabitName()), 8);
        Assert.assertTrue(tasksPage.isTaskTitleDisplayedOnScheduleAtDateSelected(newHabitTaskData.getStartDate(), newHabitTaskData.getHabitName()));
        Assert.assertTrue(tasksPage.isTaskTitleDisplayedOnScheduleAtDateSelected(newHabitTaskData.getEndDate(), newHabitTaskData.getHabitName()));

    }

    @Test
    public void Habit_05_Delete_Success(Method method){
        ExtentTestManager.startTest(method.getName(), "Habit_05_Delete_Success");

        HabitTaskData HabitTaskData = tasksPage.getHabitTaskDataFromFile(2);

        tasksPage.clickToTaskDot(HabitTaskData.getStartDate(), HabitTaskData.getHabitName());
        Assert.assertTrue(tasksPage.isActionPopupDisplayed());
        tasksPage.clickToActionInActionPopup("Delete Habit");

        Assert.assertTrue(tasksPage.isConfirmPopupDisplayed("Delete Habit"));
        tasksPage.clickToButtonInConfirmPopup("Delete habit entirely");

        Assert.assertEquals(tasksPage.getMessageText(), "The habit has been deleted.");
        Assert.assertEquals(tasksPage.getTaskNumber(HabitTaskData.getHabitName()), 0);
        Assert.assertFalse(tasksPage.isTaskTitleInvisible(HabitTaskData.getHabitName()));
    }

    @AfterClass
    public void afterClass() {
        EnvironmentFactoryProvider.removeDriver();
    }
}
