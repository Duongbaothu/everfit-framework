package pageUIs.AdminPageUIs;

public class PayGradePageUI {
    public static final String SAVE_CURRENCY_BUTTON = "xpath=//h6[text()='Add Currency']/following-sibling::form//button[contains(string(),'Save')]";

    public static final String DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME_IN_CURRENCIES_TABLE =
            "xpath=//h6[text()='Currencies']/ancestor::div[contains(@class,'orangehrm-header-container')]//following-sibling::div//div[text()='%s']/preceding-sibling::div";
    public static final String DYNAMIC_CURRENCY_VALUE_IN_TABLE = "xpath=//h6[text()='Currencies']/ancestor::div[contains(@class,'orangehrm-header-container')]//following-sibling::div[@class='orangehrm-container']//div[contains(@class,'oxd-table-body')]//div[@role='row'][%s]/div[%s]/div[contains(text(),'%s')]";
}
