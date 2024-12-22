package pageObjects;

import commons.BaseElement;
import org.openqa.selenium.WebDriver;
import pageUIs.BaseElementUI;
import pageUIs.ClientListPageUI;
import pageUIs.ClientPageUI;

public class ClientPageObject extends BaseElement {
    private WebDriver driver;
    public static String actualClientName;

    public ClientPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
        actualClientName = getClientPageName().replaceAll("\\s+", " ").trim();
    }

    public String getClientPageName() {
        waitForElementVisible(driver, ClientPageUI.CLIENT_PAGE);
        return getElementText(driver, ClientPageUI.CLIENT_PAGE);
    }

    public void clickToTasksTab() {
        waitForElementClickable(driver, ClientPageUI.TASKS_TAB);
        clickToElement(driver, ClientPageUI.TASKS_TAB);
    }
}
