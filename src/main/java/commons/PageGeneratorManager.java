package commons;

import pageObjects.ClientListPageObject;
import pageObjects.ClientPageObject;
import pageObjects.LoginPageObject;
import pageObjects.TasksPageObject;
import org.openqa.selenium.WebDriver;
import utilities.PropertiesConfig;

public class PageGeneratorManager {

    public static LoginPageObject getLoginPage(WebDriver driver, PropertiesConfig propertiesConfig) {
        return new LoginPageObject(driver, propertiesConfig);
    }

    public static TasksPageObject getTasksPage(WebDriver driver){
        return new TasksPageObject(driver);
    }

    public static ClientListPageObject getClientListPage(WebDriver driver){
        return new ClientListPageObject(driver);
    }

    public static ClientPageObject getClientPage(WebDriver driver){
        return new ClientPageObject(driver);
    }


}
