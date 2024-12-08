package pageObjects.AdminPageObjects;

import commons.BaseElement;
import org.openqa.selenium.WebDriver;
import pageUIs.AdminPageUIs.PayGradePageUI;

public class PayGradePageObject extends BaseElement {
    private WebDriver driver;

    public PayGradePageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void clickToSaveCurrencyButton() {
        waitForElementClickable(driver, PayGradePageUI.SAVE_CURRENCY_BUTTON);
        clickToElement(driver, PayGradePageUI.SAVE_CURRENCY_BUTTON);
    }

    public boolean isValueDisplayedAtColumnNameInCurrenciesTable( String columnName, String rowIndex, String rowValue) {
        int columnIndex = getListElementSize(driver, PayGradePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME_IN_CURRENCIES_TABLE, columnName) + 1;
        return isElementDisplayed(driver, PayGradePageUI.DYNAMIC_CURRENCY_VALUE_IN_TABLE, rowIndex, String.valueOf(columnIndex), rowValue);
    }

}
