package pageObjects;

import commons.BaseElement;
import org.openqa.selenium.WebDriver;
import pageUIs.BaseElementUI;
import pageUIs.ClientListPageUI;
import utilities.ExcelConfig;

public class ClientListPageObject extends BaseElement {
    private WebDriver driver;

    public ClientListPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean isAllClientListPageDisplayed() {
        waitForElementVisible(driver, ClientListPageUI.ALL_CLIENT_LABEL);
        return isElementDisplayed(driver, ClientListPageUI.ALL_CLIENT_LABEL);
    }

    public void clickToClient(String clientName) {
        waitForElementClickable(driver, ClientListPageUI.CLIENT_NAME, clientName);
        clickToElement(driver, ClientListPageUI.CLIENT_NAME, clientName);
    }
}
