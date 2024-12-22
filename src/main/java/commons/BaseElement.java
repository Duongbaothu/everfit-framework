package commons;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.BaseElementUI;
import testData.ClientData;
import testData.HabitTaskData;
import utilities.ExcelConfig;

import java.util.HashMap;
import java.util.Map;

public class BaseElement extends BasePage{
    WebDriver driver;
    private ExcelConfig excelConfig;

    public BaseElement(WebDriver driver){
        this.driver = driver;
        excelConfig = ExcelConfig.getExcelData();
    }

    public void waitForSpinnerIconInvisible() {
        waitForListElementInvisible(driver, BaseElementUI.SPINNER_ICON);
    }

    public void enterToTextboxByPlaceholder(String textboxPlaceholder, String valueToSendkey) {
        waitForElementVisible(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_PLACEHOLDER, textboxPlaceholder);
        sendkeyToElement(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_PLACEHOLDER, valueToSendkey, textboxPlaceholder);
    }

    public void clickToSaveCloseButton() {
        waitForElementClickable(driver, BaseElementUI.SAVE_CLOSE_BTN);
        clickToElement(driver, BaseElementUI.SAVE_CLOSE_BTN);
    }

    public boolean isSaveCloseButtonEnabled() {
        waitForElementVisible(driver, BaseElementUI.SAVE_CLOSE_BTN);
        return isElementEnabled(driver, BaseElementUI.SAVE_CLOSE_BTN);
    }

    public boolean isSaveButtonEnabled() {
        waitForElementVisible(driver, BaseElementUI.SAVE_BTN);
        return isElementEnabled(driver, BaseElementUI.SAVE_BTN);
    }

    public ClientData getClientDataFromFile(int rowIndex) {
        excelConfig.refreshAllFormulas();
        excelConfig.switchToSheet("clientData");
        String clientName = excelConfig.getCellData("clientName", rowIndex);
        return new ClientData(clientName);
    }


}
