package pageUIs;

public class BaseElementUI {
    public static final String PAGES_HEADER = "xpath=//h6[contains(string(),'%s')]";
    public static final String UPLOAD_FILE_TYPE = "xpath=//label[text()='%s']//parent::div//following-sibling::div/input[@type='file']";
    public static final String FILE_LOADED = "xpath=//label[text()='%s']//parent::div//following-sibling::div/div/div[contains(string(),'%s')]";
    public static final String DYNAMIC_TOGGLE_BY_TEXT = "xpath=//p[text()='%s']//following-sibling::div[@class='oxd-switch-wrapper']";
    public static final String DYNAMIC_BUTTON_BY_TEXT = "xpath=//button[contains(string(),'%s')]";
    public static final String DYNAMIC_TEXTBOX_ERROR_MSG_BY_TEXTBOX_LABEL = "xpath=//label[string()='%s']//parent::div/following-sibling::span[contains(@class,'error-message')]";
    public static final String DYNAMIC_SUCCESS_MSG = "xpath=//p[contains(@class,'oxd-text--toast-message') and text()='%s']";
    public static final String DYNAMIC_TEXTBOX_BY_TEXT = "xpath=//label[contains(text(),'%s')]//parent::div//following-sibling::div//input";
    public static final String DYNAMIC_TEXTBOX_BY_NAME = "css=input[name='%s']";
    public static final String DYNAMIC_NAV_TAB_ITEM_BY_TEXT = "xpath=//a[text()='%s']";
    public static final String DYNAMIC_MAIN_MENU_MODULE = "xpath=//ul[@class='oxd-main-menu']//span[text()='%s']";
    public static final String SPINNER_ICON = "css=div.oxd-loading-spinner-container";
    public static final String DYNAMIC_INDEX_BY_COLUMN_NAME = "xpath=//div[text()='%s']/preceding-sibling::div";
    public static final String DYNAMIC_ROW_VALUE_BY_COLUMN_INDEX_ROW_INDEX = "xpath=//div[@class='oxd-table-card']/div[@role='row'][%s]/div[%s]/div[contains(text(),'%s')]";

    public static final String DYNAMIC_INDEX_BY_COLUMN_NAME_WITH_TABLE_NAME = "xpath=//h6[text()='%s']/ancestor::div[contains(@class,'vertical-padding')]" +
            "//following-sibling::div//div[text()='%s']/preceding-sibling::div";
    public static final String DYNAMIC_ROW_VALUE_BY_COLUMN_INDEX_ROW_INDEX_WITH_TABLE_NAME = "xpath=//h6[text()='%s']/ancestor::div[contains(@class,'vertical-padding')]" +
            "//following-sibling::div//div[@class='oxd-table-card']/div[@role='row'][%s]/div[%s]/div[contains(text(),'%s')]";
    public static final String DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME_WITH_TABLE_NAME =
            "xpath=//h6[text()='%s']/ancestor::div[contains(@class,'vertical-padding')]" +
                    "//following-sibling::div//div[text()='%s']/preceding-sibling::div";

    public static final String ALL_ROWS_IN_TABLE = "xpath=//div[@class='oxd-table-card']/div[@role='row']";

    public static final String DYNAMIC_DROPDOWN_BY_TEXT = "xpath=//label[contains(text(),'%s')]//parent::div//following-sibling::div//i";
    public static final String DYNAMIC_ITEM_IN_DROPDOWN_BY_TEXT = "xpath=//label[contains(text(),'%s')]//parent::div//following-sibling::div//div[@class='oxd-select-option']/span[(text()='%s')]";
    public static final String DYNAMIC_ITEM_SELECTED_IN_DROPDOWN_BY_TEXT = "xpath=//label[contains(text(),'%s')]//parent::div//following-sibling::div//div[@class='oxd-select-text-input']";

    public static final String DYNAMIC_ITEM_IN_EDITABLE_DROPDOWN_BY_TEXT = "xpath=//label[contains(text(),'%s')]//parent::div//following-sibling::div//div[@class='oxd-autocomplete-option']/span[normalize-space(text())='%s']";

    public static final String DYNAMIC_RADIOBUTTON_BY_TEXT = "xpath=//label[string()='%s']/input";
    public static final String DYNAMIC_CHECKBOX_BY_TEXT = "xpath=//label[text()='%s']/parent::div/following-sibling::div//input";
    public static final String DYNAMIC_BUTTON_BY_LABEL = "xpath=//h6[text()='%s']/parent::div/button[contains(string(),'%s')]";
    public static final String ELEMENT_ON_DOWN = "xpath=//a[text()='OrangeHRM, Inc']";

    public static final String DYNAMIC_HEADER_MENU_BY_LABEL = "xpath=//span[contains(text(),'%s')]";
    public static final String DYNAMIC_ITEM_IN_HEADER_MENU_BY_LABEL = "xpath=//a[contains(text(),'%s')]";

    public static final String SAVE_BUTTON_WITH_HEADER_NAME = "xpath=//h6[text()='%s']/following-sibling::form//button[contains(string(),'Save')]";

}
