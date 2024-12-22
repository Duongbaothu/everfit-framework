package pageObjects;

import commons.BaseElement;
import org.openqa.selenium.WebDriver;
import pageUIs.LoginPageUI;
import utilities.PropertiesConfig;

public class LoginPageObject extends BaseElement {
    private PropertiesConfig propertiesConfig;
    private WebDriver driver;

    public LoginPageObject(WebDriver driver, PropertiesConfig propertiesConfig) {
        super(driver);
        this.driver = driver;
        this.propertiesConfig = propertiesConfig;
    }

    public void loginToEverfit(){
        enterToTextboxByPlaceholder("Your Email Address", propertiesConfig.getApplicationUserName());
        enterToTextboxByPlaceholder("Password", propertiesConfig.getApplicationPassword());
        clickToLoginButton();
        waitForSpinnerIconInvisible();
    }

    public void clickToLoginButton() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
    }
}
