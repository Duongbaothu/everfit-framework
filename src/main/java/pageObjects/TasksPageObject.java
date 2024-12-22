package pageObjects;

import commons.BaseElement;
import commons.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.BaseElementUI;
import pageUIs.TasksPageUI;
import testData.HabitTaskData;
import utilities.ExcelConfig;

import java.util.List;

public class TasksPageObject extends BaseElement {
    private WebDriver driver;
    private int today;
    private ExcelConfig excelConfig;

    public TasksPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
        excelConfig = ExcelConfig.getExcelData();
    }

    public void clickToAddNewIcon() {
        waitForElementClickable(driver, TasksPageUI.ADD_NEW_ICON_OF_TODAY);
        clickToElement(driver, TasksPageUI.ADD_NEW_ICON_OF_TODAY);
    }

    public boolean isCreateTaskPopupDisplayed() {
        waitForElementVisible(driver, TasksPageUI.CREATE_TASK_POPUP);
        return isElementDisplayed(driver, TasksPageUI.CREATE_TASK_POPUP);
    }

    public void clickToHabitIcon() {
        waitForElementClickable(driver, TasksPageUI.HABIT_ICON);
        clickToElement(driver, TasksPageUI.HABIT_ICON);
    }

    public boolean isAddNewHabitPopupDisplayed() {
        waitForElementVisible(driver, TasksPageUI.ADD_NEW_HABIT_POPUP);
        return isElementDisplayed(driver, TasksPageUI.ADD_NEW_HABIT_POPUP);
    }

    public void clickToCreateCustomHabitButton() {
        waitForElementClickable(driver, TasksPageUI.CREATE_CUSTOM_HABIT_BUTTON);
        clickToElement(driver, TasksPageUI.CREATE_CUSTOM_HABIT_BUTTON);
    }

    public boolean isHabitNameTxtDisplayed() {
        waitForElementVisible(driver, TasksPageUI.HABIT_NAME_TXT);
        return isElementDisplayed(driver, TasksPageUI.HABIT_NAME_TXT);
    }

    public String getBackgroundBorderOfHabitNameTxt() {
        waitForElementVisible(driver, TasksPageUI.HABIT_NAME_TXT_INVALID);
        return getElementCssValue(driver, TasksPageUI.HABIT_NAME_TXT_INVALID, "border-color");
    }

    public String getBackgroundBorderOfGoalValueTxt() {
        waitForElementVisible(driver, TasksPageUI.GOAL_VALUE_TXT_INVALID);
        return getElementCssValue(driver, TasksPageUI.GOAL_VALUE_TXT_INVALID, "border-color");
    }

    public void hoverToToday() {
        waitForElementClickable(driver, TasksPageUI.TODAY_CALENDAR);
        hoverToElement(driver, TasksPageUI.TODAY_CALENDAR);
    }

    public String getEndDateTextboxErrorMessage() {
        waitForElementVisible(driver, TasksPageUI.END_DATE_ERROR_MSG);
        return getElementText(driver, TasksPageUI.END_DATE_ERROR_MSG);
    }

    public void clickToCloseTaskButton() {
        waitForElementClickable(driver, TasksPageUI.CLOSE_TASK_BUTTON);
        clickToElement(driver, TasksPageUI.CLOSE_TASK_BUTTON);
    }

    public void selectToDayInCalendarByLabel(String calendarLabel, String dateSelected) {
        waitForElementClickable(driver, TasksPageUI.DYNAMIC_DATE_PICKUP_BY_CALENDAR_LABEL, calendarLabel, dateSelected);
        clickToElement(driver, TasksPageUI.DYNAMIC_DATE_PICKUP_BY_CALENDAR_LABEL, calendarLabel, dateSelected);
    }

//    public void selectDateInCalendarByLabel(String calendarName, String value) {
//        sendkeyToElementByJS(driver, TasksPageUI.DYNAMIC_CALENDAR_CSS_BY_NAME, value, calendarName);
//    }

    public void selectDateInCalendarByLabel(String calendarName, String value) {
        openCalendarByName(calendarName);
        // Lấy thông tin ngày từ value (dạng "dd/MM/yyyy")
        String day = value.substring(0, 2);
        String monthNumber = value.substring(3, 5);
        String year = value.substring(6, 10);

        String month = getMonthName(Integer.parseInt(monthNumber));

        while(true)
        {
            String monthyear = getElementText(driver, TasksPageUI.DYNAMIC_MONTH_YEAR_TITLE_IN_CALENDAR, calendarName);
            String[] arr =monthyear.split(" ");
            String mon=arr[0];
            String yr=arr[1];

            if (mon.equalsIgnoreCase(month) && yr.equals(year)) {
                break;
            } else {
                clickToElement(driver, TasksPageUI.DYNAMIC_NEXT_ICON_IN_CALENDAR, calendarName);
            }
        }

        List<WebElement> allDates = getListWebElement(driver, TasksPageUI.FUTURE_DATES_PICKUP, calendarName);

        for (WebElement element : allDates){
            String date = element.getText();
            if (date.equals(day)){
                element.click();
                break;
            }
        }
    }

    private String getMonthName(int monthNumber) {
        String[] months = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };
        return months[monthNumber - 1];  // tháng trong mảng bắt đầu từ 0, nên trừ đi 1
    }

    public void openCalendarByName(String calendarName) {
        waitForElementClickable(driver, TasksPageUI.DYNAMIC_CALENDAR_BY_NAME, calendarName);
        clickToElement(driver, TasksPageUI.DYNAMIC_CALENDAR_BY_NAME, calendarName);
    }

    public void enterToHabitNameTextbox(String valueToSendkey) {
        waitForElementVisible(driver, TasksPageUI.HABIT_NAME_TXT);
        sendkeyToElement(driver, TasksPageUI.HABIT_NAME_TXT, valueToSendkey);
    }

    public void enterToGoalValueTextbox(String valueToSendkey) {
        waitForElementVisible(driver, TasksPageUI.GOAL_VALUE_TXT);
        sendkeyToElement(driver, TasksPageUI.GOAL_VALUE_TXT, valueToSendkey);
    }

    public void selectInDropdown(String dropdownClass, String itemSelected) {
        String formattedDropdownLocator = String.format(TasksPageUI.DYNAMIC_DROPDOWN_BY_CLASS, dropdownClass);
        String formattedItemLocator = String.format(TasksPageUI.DYNAMIC_ITEM_IN_DROPDOWN_BY_TEXT, dropdownClass, itemSelected);

        waitForElementClickable(driver, formattedDropdownLocator);
        selectItemInCustomDropdown(driver, formattedDropdownLocator, formattedItemLocator, itemSelected);
    }

    public String getCalendarOptionAttributeByLabel(String calendarLabel, String dateSelected) {
        waitForElementVisible(driver, TasksPageUI.DYNAMIC_DATE_PICKUP_BY_CALENDAR_LABEL, calendarLabel, dateSelected);
        return getAttributeValue(driver, TasksPageUI.DYNAMIC_DATE_PICKUP_BY_CALENDAR_LABEL, "data-value", calendarLabel, dateSelected);
    }

    public String getItemTextSelectedInCalendarByText(String dropdownLabel, String dropdownName) {
        return getElementText(driver, TasksPageUI.DYNAMIC_DATE_SELECTED_BY_CALENDAR_LABEL, dropdownLabel, dropdownName);
    }

    public String getItemTextSelectedInDropdownByClass(String dropdownClass) {
        return getElementText(driver, TasksPageUI.DYNAMIC_ITEM_SELECTED_IN_DROPDOWN_BY_CLASS, dropdownClass);
    }

    public String getItemTextSelectedInReminderDropdown() {
        return getElementText(driver, TasksPageUI.ITEM_SELECTED_IN_REMINDER_TIME);
    }

    public boolean isTodaySelectedInRepeatWeekly() {
        today = BaseTest.getTodayWeekday();
        String selectedDay = getAttributeValue(driver, TasksPageUI.DATE_CHECKED_IN_REPEAT_WEEKLY, "value");
        return Integer.parseInt(selectedDay) == today;
    }

    public void clickToSetReminderToggle() {
        waitForElementClickable(driver, TasksPageUI.SET_REMINDER_TOGGLE);
        clickToElement(driver, TasksPageUI.SET_REMINDER_TOGGLE);
    }

    public boolean isTaskTitleDisplayedOnScheduleAtDateSelected(String dateSelected, String taskName) {
        String day = dateSelected.substring(0, 2);
        waitForElementVisible(driver, TasksPageUI.TASK_TITLE_BY_DATE_SELECTED, day, taskName);
        return isElementDisplayed(driver, TasksPageUI.TASK_TITLE_BY_DATE_SELECTED, day, taskName);
    }

    public boolean isTaskTitleInvisible(String taskName) {
        waitForListElementInvisible(driver, TasksPageUI.TASK_TITLE, taskName);
        return isElementDisplayed(driver, TasksPageUI.TASK_TITLE, taskName);
    }

    public boolean isConfirmPopupDisplayed(String popupName) {
        waitForElementVisible(driver, TasksPageUI.DYNAMIC_CONFIRM_POPUP, popupName);
        return isElementDisplayed(driver, TasksPageUI.DYNAMIC_CONFIRM_POPUP, popupName);
    }

    public void clickToButtonInConfirmPopup(String buttonName) {
        waitForElementClickable(driver, TasksPageUI.DYNAMIC_BUTTON_IN_CONFIRM_POPUP, buttonName);
        clickToElement(driver, TasksPageUI.DYNAMIC_BUTTON_IN_CONFIRM_POPUP, buttonName);
    }

    public void clickToTaskDot(String dateSelected, String taskName) {
        String day = dateSelected.substring(0, 2);
        waitForElementClickable(driver, TasksPageUI.TASK_DOT_BY_DATE_SELECTED, day, taskName);
        clickToElement(driver, TasksPageUI.TASK_DOT_BY_DATE_SELECTED, day, taskName);
    }

    public boolean isActionPopupDisplayed() {
        waitForElementVisible(driver, TasksPageUI.ACTION_POPUP);
        return isElementDisplayed(driver, TasksPageUI.ACTION_POPUP);
    }

    public void clickToActionInActionPopup(String actionName) {
        waitForElementClickable(driver, TasksPageUI.DYNAMIC_ACTION_IN_ACTION_POPUP, actionName);
        clickToElement(driver, TasksPageUI.DYNAMIC_ACTION_IN_ACTION_POPUP, actionName);
    }

    public void clickToTask(String dateSelected, String taskName) {
        waitForElementClickable(driver, TasksPageUI.TASK_TITLE_BY_DATE_SELECTED, dateSelected, taskName);
        clickToElement(driver, TasksPageUI.TASK_TITLE_BY_DATE_SELECTED, dateSelected, taskName);
    }

    public String getGoalValueTextboxAttribute() {
        waitForElementVisible(driver, TasksPageUI.GOAL_VALUE_TXT);
        return getAttributeValue(driver, TasksPageUI.GOAL_VALUE_TXT, "value");
    }

    public String getHabitNameTextboxAttribute() {
        waitForElementVisible(driver, TasksPageUI.HABIT_NAME_TXT);
        return getElementText(driver, TasksPageUI.HABIT_NAME_TXT);
    }

    public HabitTaskData getHabitTaskDataFromFile(int rowIndex) {
        excelConfig.refreshAllFormulas();
        excelConfig.switchToSheet("habitTaskData");
        String habitName = excelConfig.getCellData("HabitName", rowIndex);
        String goalValue = excelConfig.getCellData("GoalValue", rowIndex);
        String habitUnit = excelConfig.getCellData("HabitUnit", rowIndex);
        String habitFrequency = excelConfig.getCellData("HabitFrequency", rowIndex);
        String startDate = excelConfig.getCellData("StartDate", rowIndex);
        String endDate = excelConfig.getCellData("EndDate", rowIndex);
        String habitRepeat = excelConfig.getCellData("HabitRepeat", rowIndex);
        String reminderTime = excelConfig.getCellData("ReminderTime", rowIndex);
        String startDateText = excelConfig.getCellData("startDateText", rowIndex);
        String endDateText = excelConfig.getCellData("endDateText", rowIndex);
        return new HabitTaskData(habitName, goalValue, habitUnit, habitFrequency, startDate, endDate, habitRepeat, reminderTime, startDateText, endDateText);
    }

    public int getTaskNumber(String taskName) {
        List<WebElement> tasks = getListWebElement(driver, TasksPageUI.TASK_TITLE, taskName);
        return tasks.size();
    }

    public void clickToWeekNumber(String weekText) {
        waitForElementClickable(driver, TasksPageUI.DYNAMIC_WEEK_NUMBER, weekText);
        clickToElement(driver, TasksPageUI.DYNAMIC_WEEK_NUMBER, weekText);
        waitForElementVisible(driver, TasksPageUI.DYNAMIC_WEEK_NUMBER_SELECTED, weekText);
    }

    public String getMessageText() {
        return getElementText(driver, TasksPageUI.DELETE_SUCCESS_MSG);
    }
}
