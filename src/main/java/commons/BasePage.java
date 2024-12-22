package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    public static BasePage getBasePage(){
        return new BasePage();
    }

    public void openPageUrl(WebDriver driver, String pageUrl){
        driver.get(pageUrl);
    }

    public String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }

    public String getCurrentPageUrl(WebDriver driver){
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver){
        return driver.getPageSource();
    }

    public void back(WebDriver driver){
        driver.navigate().back();
    }

    public void forward(WebDriver driver){
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver){
        driver.navigate().refresh();
    }

    public Alert waitForAlertPresence(WebDriver driver){
        return new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver){
        waitForAlertPresence(driver).accept();
    }

    public void cancelAlert(WebDriver driver){
        waitForAlertPresence(driver).dismiss();
    }

    public String getTextAlert(WebDriver driver){
        return waitForAlertPresence(driver).getText();
    }

    public void sendkeyToAlert(WebDriver driver, String keyToSend){
        waitForAlertPresence(driver).sendKeys(keyToSend);
    }

    public String getWindowHandle(WebDriver driver){
        return driver.getWindowHandle();
    }

    public void switchToWindowByID(WebDriver driver){
        driver.switchTo().window(getWindowHandle(driver));
    }

    public void switchToWindowByTitle(WebDriver driver, String expectedTitle){
        Set<String> allTabWindowIDs = driver.getWindowHandles();
        for (String id: allTabWindowIDs){
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(expectedTitle)){
                break;
            }
        }

    }

    public boolean closeAllWindowsWithoutParentID(WebDriver driver, String parentID){
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows:allWindows){
            if (!runWindows.equals(parentID)){
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
        if (driver.getWindowHandles().size()==1)
            return true;
        else
            return false;
    }

    public void sleepInSeconds(long timeInSecond){
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    public Set<Cookie> getBrowserCookies(WebDriver driver){
        return driver.manage().getCookies();
    }

    public void setCookies(WebDriver driver, Set<Cookie> cookies){
        for (Cookie cookie : cookies){
            driver.manage().addCookie(cookie);
        }
    }

    public void deleteAllCookies(WebDriver driver){
        driver.manage().deleteAllCookies();
    }

    private By getByLocator(String locatorValue) {
        By by = null;

        if (locatorValue.startsWith("id=") || locatorValue.startsWith("ID=") || locatorValue.startsWith("Id=")) {
            by = By.id(locatorValue.substring(3));
        } else if (locatorValue.startsWith("class=") || locatorValue.startsWith("CLASS=") || locatorValue.startsWith("Class=")) {
            by = By.className(locatorValue.substring(6));
        } else if (locatorValue.startsWith("name=") || locatorValue.startsWith("NAME=") || locatorValue.startsWith("Name=")) {
            by = By.name(locatorValue.substring(5));
        } else if (locatorValue.startsWith("css=") || locatorValue.startsWith("CSS=") || locatorValue.startsWith("Css=")) {
            by = By.cssSelector(locatorValue.substring(4));
        } else if (locatorValue.startsWith("xpath=") || locatorValue.startsWith("XPATH=") || locatorValue.startsWith("Xpath=") || locatorValue.startsWith("XPath=")) {
            by = By.xpath(locatorValue.substring(6));
        } else {
            throw new RuntimeException("Locator type is not valid!");
        }
        return by;
    }

    public By getByXpath(String locator){
        return By.xpath(locator);
    }

    public String getDynamicLocator(String locator, String... restParams){
        return String.format(locator, (Object[]) restParams);
    }

    public WebElement getWebElement(WebDriver driver, String locator){
        return driver.findElement(getByLocator(locator));
    }

    public List<WebElement> getListWebElement(WebDriver driver, String locator){
        return driver.findElements(getByLocator(locator));
    }

    public List<WebElement> getListWebElement(WebDriver driver, String locator, String... restParams){
        return driver.findElements(getByLocator(getDynamicLocator(locator, restParams)));
    }

    // Ham de click vao 1 element co dinh
    public void clickToElement(WebDriver driver, String locator){
        getWebElement(driver, locator).click();
    }

    public void clickToElement(WebDriver driver, WebElement element){
        element.click();
    }

    // Ham de click vao 1 element ko co dinh (dynamic) voi n tham so
    public void clickToElement(WebDriver driver, String locator, String... restParams){
        getWebElement(driver, getDynamicLocator(locator, restParams)).click();
    }

    public void sendkeyToElement(WebDriver driver, String locator, String valueToSend){
        getWebElement(driver, locator).clear();
        getWebElement(driver, locator).sendKeys(valueToSend);
    }

    public void sendkeyToElement(WebDriver driver, String locator, String valueToSend, String... restParams){
        getWebElement(driver, getDynamicLocator(locator, restParams)).clear();
        getWebElement(driver, getDynamicLocator(locator, restParams)).sendKeys(valueToSend);
    }

    public String getElementText(WebDriver driver, String locator){
        return getWebElement(driver, locator).getText();
    }

    public String getElementText(WebDriver driver, String locator, String... restParams){
        return getWebElement(driver, getDynamicLocator(locator, restParams)).getText();
    }

    public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemValue){
        new Select(getWebElement(driver, locator)).selectByVisibleText(itemValue);
    }

    public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemValue, String... restParams){
        new Select(getWebElement(driver, getDynamicLocator(locator, restParams))).selectByVisibleText(itemValue);
    }

    public String getFirstSelectedTextInDefaultDropdown(WebDriver driver, String locator){
        return new Select(getWebElement(driver, locator)).getFirstSelectedOption().getText();
    }

    public boolean isDefaultDropdownMultiple(WebDriver driver, String locator){
        return new Select(getWebElement(driver, locator)).isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String itemTextExpected){
        getWebElement(driver, parentLocator).click();
        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
        for (WebElement item: allItems){
            if (item.getText().equals(itemTextExpected)){
                sleepInSeconds(1);
                item.click();
                break;
            }
        }
    }

    public void selectItemInEditableDropdown(WebDriver driver, String parentLocator, String valueToSend, String childLocator, String itemTextExpected){
        getWebElement(driver, parentLocator).sendKeys(valueToSend);
        sleepInSeconds(1);
        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
        for (WebElement item: allItems){
            if (item.getText().equals(itemTextExpected)){
                sleepInSeconds(1);
                item.click();
                break;
            }
        }
    }

    public String getAttributeValue(WebDriver driver, String locator, String attributeName){
        return getWebElement(driver, locator).getAttribute(attributeName);
    }

    public String getAttributeValue(WebDriver driver, String locator, String attributeName, String... restParams){
        return getWebElement(driver, getDynamicLocator(locator, restParams)).getAttribute(attributeName);
    }

    public String getElementCssValue(WebDriver driver, String locator, String cssPropertyName){
        return getWebElement(driver, locator).getCssValue(cssPropertyName);
    }

    public String convertRGBAToHexaColor(WebDriver driver, String locator) {
        return Color.fromString(getElementCssValue(driver, locator, "background-color")).asHex();
    }

    public int getListElementSize(WebDriver driver, String locator){
        return getListWebElement(driver, locator).size();
    }

    public int getListElementSize(WebDriver driver, String locator, String... restParams){
        return getListWebElement(driver, getDynamicLocator(locator, restParams)).size();
    }

    /**
     * Apply for Default checkbox and radio button
     *
     * @param locator
     */
    public void checkToElement(WebDriver driver, String locator){
        if (!getWebElement(driver, locator).isSelected()){
            getWebElement(driver, locator).click();
        }
    }

    public void checkToElement(WebDriver driver, String locator, String... restParams){
        if (!getWebElement(driver, getDynamicLocator(locator, restParams)).isSelected()){
            getWebElement(driver, getDynamicLocator(locator, restParams)).click();
        }
    }

    /**
     * Only apply for checkbox
     *
     * @param locator
     */
    public void uncheckToElement(WebDriver driver, String locator){
        if (getWebElement(driver, locator).isSelected()){
            getWebElement(driver, locator).click();
        }
    }

    // Case 1: Element is displayed on UI and in HTML -> isDisplayed return true
    // Case 2: Element is not displayed on UI but displayed in HTML -> isDisplayed return false
    public boolean isElementDisplayed(WebDriver driver, String locator){
        return getWebElement(driver, locator).isDisplayed();
    }

    public boolean isElementDisplayed(WebDriver driver, String locator, String... restParams){
        return getWebElement(driver, getDynamicLocator(locator, restParams)).isDisplayed();
    }

    public void setImplicitWait(WebDriver driver, long timeout){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
    }

    // Case 3: Element is not displayed on UI and in HTML -> isDisplayed return false
    public boolean isElementUndisplayed(WebDriver driver, String locator) {
        // Truoc khi tim element thi set time ngan thoi
        setImplicitWait(driver, shortTimout);
        List<WebElement> elements = getListWebElement(driver, locator);
        //Tra lai timeout mac dinh cho cac step con lai
        setImplicitWait(driver, longTimeout);
        if (elements.size() == 0) {
            //Element not in DOM
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            //Element in DOM but not visible/ displayed
            return true;
        } else {
            //Element in DOM and visible
            return false;
        }
    }

    public boolean isElementSelected(WebDriver driver, String locator){
        return getWebElement(driver, locator).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locator, String... restParams){
        return getWebElement(driver, getDynamicLocator(locator, restParams)).isSelected();
    }

    public boolean isElementEnabled(WebDriver driver, String locator){
        return getWebElement(driver, locator).isEnabled();
    }

    public void pressEnter(WebDriver driver){
       new Actions(driver).sendKeys(Keys.ENTER).perform();
    }

    public void switchToIFrame(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getByLocator(locator)));
    }

    public void switchToDefaultContent(WebDriver driver){
        driver.switchTo().defaultContent();
    }

    public void doubleClickToElement(WebDriver driver, String locator){
        new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
    }

    public void hoverToElement(WebDriver driver, String locator){
        new Actions(driver).moveToElement(getWebElement(driver, locator)).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator){
        new Actions(driver).contextClick(getWebElement(driver, locator)).perform();
    }

    public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator){
        new Actions(driver).dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, targetLocator)).perform();
    }

    public void sendKeyBoardToElement(WebDriver driver, String locator, Keys key){
        new Actions(driver).sendKeys(getWebElement(driver, locator), key).perform();
    }

    public Object executeForBrowser(WebDriver driver, String javaScript) {
        return ((JavascriptExecutor) driver).executeScript(javaScript);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
    }

    public void clickToElementByJS(WebDriver driver, String locator, String... restParams) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, getDynamicLocator(locator, restParams)));
    }

    public String getInnerText(WebDriver driver) {
        return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(WebDriver driver, String textExpected) {
        String textActual = (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToElementOnDown(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value, String... restParams) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String script = "element = document.querySelector(arguments[0]); " +
                "element.textContent = arguments[1]; " +
                "element.setAttribute('value', '" + value + "'); " +
                "element.dispatchEvent(new Event('input')); ";
        jsExecutor.executeScript(script, getDynamicLocator(locator, restParams), value);
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator){
        boolean status = (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0");
        return status;
    }

    public void waitForElementVisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String... restParams){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicLocator(locator, restParams))));
    }

    public void waitForListElementVisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfAllElements(getListWebElement(driver, locator)));
    }

    public boolean waitForElementInvisible(WebDriver driver, String locator){
        return new  WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public boolean waitForListElementInvisible(WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locator)));
    }

    public boolean waitForListElementInvisible(WebDriver driver, String locator, String... restParams){
        return new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, getDynamicLocator(locator, restParams))));
    }

    public void waitForElementSelected(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public void waitForElementClickable(WebDriver driver, WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementClickable(WebDriver driver, String locator, String... restParams){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicLocator(locator, restParams))));
    }

    public void waitForAlertPresence(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.alertIsPresent());
    }

    public boolean isPageLoadedSuccess(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        });
    }

    private long shortTimout = GlobalConstants.getGlobalConstants().getShortTimeout();
    private long longTimeout = GlobalConstants.getGlobalConstants().getLongTimeout();

}
