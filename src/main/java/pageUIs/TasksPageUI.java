package pageUIs;

public class TasksPageUI {
    public static final String TODAY_CALENDAR = "xpath=//div[contains(@class,'day-label active')]/parent::div/following-sibling::div";
    public static final String ADD_NEW_ICON_OF_TODAY = "xpath=//div[contains(@class,'day-label active')]/following-sibling::div/div[@class='add-new']";
    public static final String CREATE_TASK_POPUP = "xpath=//div[text()='Create a task']";
    public static final String HABIT_ICON = "xpath=//p[text()='Habit']";
    public static final String ADD_NEW_HABIT_POPUP = "xpath=//span[text()='Add new habit']";
    public static final String CREATE_CUSTOM_HABIT_BUTTON = "xpath=//a[text()='Create Custom Habit']";
    public static final String HABIT_NAME_TXT = "xpath=//div[@placeholder='Name your Habit']";
    public static final String HABIT_NAME_TXT_INVALID = "xpath=//div[contains(@class,'text-editable__container--invalid')]";
    public static final String GOAL_VALUE_TXT = "xpath=//input[@placeholder='Goal Value']";
    public static final String GOAL_VALUE_TXT_INVALID = "xpath=//input[@placeholder='Goal Value' and contains(@class,'gpVPo')]";
    public static final String DYNAMIC_CALENDAR_BY_NAME = "xpath=//div[@name='%s']";
    public static final String DYNAMIC_CALENDAR_CSS_BY_NAME = "div[name='%s']";
    public static final String END_DATE_ERROR_MSG = "xpath=//span[text()='END DATE']/following-sibling::p";
    public static final String DYNAMIC_DATE_PICKUP_BY_CALENDAR_LABEL = "xpath=//span[text()='%s']/following-sibling::div//td[contains(@class,'%s')]";
    public static final String DYNAMIC_DATE_SELECTED_BY_CALENDAR_LABEL = "xpath=//span[text()='%s']/following-sibling::div//div[@name='%s']/span";
    public static final String FUTURE_DATES_PICKUP = "xpath=//div[@name='%s']/parent::div/following-sibling::div//tbody//td[not(contains(@class, 'rdtDisabled')) and not(contains(@class, 'rdtOld'))]";
    public static final String CLOSE_TASK_BUTTON = "xpath=//button[@class='close-button']";
    public static final String DYNAMIC_DROPDOWN_BY_CLASS = "xpath=//div[contains(@class,'%s')]";
    public static final String DYNAMIC_ITEM_IN_DROPDOWN_BY_TEXT = "xpath=//div[contains(@class,'%s')]//div[text()='%s']";
    public static final String DYNAMIC_ITEM_SELECTED_IN_DROPDOWN_BY_CLASS = "xpath=//div[contains(@class,'%s')]//span";
    public static final String DATE_CHECKED_IN_REPEAT_WEEKLY = "xpath=//span[text()='every']/following-sibling::div//input[@checked]";
    public static final String SET_REMINDER_TOGGLE = "xpath=//span[text()='Set Reminder']";
    public static final String TASK_TITLE_BY_DATE_SELECTED = "xpath=//div[text()='%s']/ancestor::div[contains(@class,'view-more')]/following-sibling::div//div[text()='%s']";
    public static final String TASK_TITLE = "xpath=//div[text()='%s']";
    public static final String DYNAMIC_WEEK_NUMBER = "xpath=//div[text()='%s']";
    public static final String DYNAMIC_WEEK_NUMBER_SELECTED = "xpath=//div[@class='item selected' and text()='%s']";
    public static final String TASK_DOT_BY_DATE_SELECTED = "xpath=//div[text()='%s']/ancestor::div[contains(@class,'view-more')]/following-sibling::div//div[text()='%s']/parent::div/following-sibling::div/div[@class='dots']";

    public static final String ACTION_POPUP = "xpath=//div[contains(@class,'action-popup')]";
    public static final String DYNAMIC_ACTION_IN_ACTION_POPUP = "xpath=//div[text()='%s']";

    public static final String DYNAMIC_CONFIRM_POPUP = "xpath=//p[text()='%s']";
    public static final String DYNAMIC_BUTTON_IN_CONFIRM_POPUP = "xpath=//button[text()='%s']";

    public static final String DYNAMIC_MONTH_YEAR_TITLE_IN_CALENDAR = "xpath=//div[@name='%s']/parent::div/following-sibling::div//th[@class='rdtSwitch']";
    public static final String DYNAMIC_NEXT_ICON_IN_CALENDAR= "xpath=//div[@name='%s']/parent::div/following-sibling::div//th[@class='rdtNext']";

    public static final String DELETE_SUCCESS_MSG = "xpath=//div[contains(@class,'toast-body')]";
    public static final String ITEM_SELECTED_IN_REMINDER_TIME = "xpath=//div[contains(@class,'single-value')]";
}
