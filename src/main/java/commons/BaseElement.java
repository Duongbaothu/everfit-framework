package commons;

import org.openqa.selenium.WebDriver;
import pageUIs.BaseElementUI;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import testData.EmployeeInfo;

import java.util.List;

public class BaseElement extends BasePage{
    WebDriver driver;

    public BaseElement(WebDriver driver){
        this.driver = driver;
    }

    public boolean isPageHeaderDisplayed(String pageHeader) {
        waitForElementVisible(driver, BaseElementUI.PAGES_HEADER, pageHeader);
        return isElementDisplayed(driver, BaseElementUI.PAGES_HEADER, pageHeader);
    }

    public void waitForSpinnerIconInvisible() {
        waitForListElementInvisible(driver, BaseElementUI.SPINNER_ICON);
    }

    public void clickToNavTabItemByText(String navItemName) {
        waitForElementClickable(driver, BaseElementUI.DYNAMIC_NAV_TAB_ITEM_BY_TEXT, navItemName);
        clickToElement(driver, BaseElementUI.DYNAMIC_NAV_TAB_ITEM_BY_TEXT, navItemName);
        waitForSpinnerIconInvisible();
    }

    public void clickToButtonByText(String buttonText) {
        waitForElementClickable(driver, BaseElementUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
        clickToElement(driver, BaseElementUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
    }

    public boolean isSuccessMessageDisplayed(String message) {
        waitForElementVisible(driver, BaseElementUI.DYNAMIC_SUCCESS_MSG, message);
        return isElementDisplayed(driver, BaseElementUI.DYNAMIC_SUCCESS_MSG, message);
    }

    public void enterToTextboxByText(String textboxText, String valueToSendkey) {
        waitForElementVisible(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_TEXT, textboxText);
        sendkeyToElement(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_TEXT, valueToSendkey, textboxText);
    }

    public void enterToTextboxByName(String textboxName, String valueToSendkey) {
        waitForElementVisible(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_NAME, textboxName);
        sendkeyToElement(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_NAME, valueToSendkey, textboxName);
    }

    public String getTextboxAttributeByName(String textboxName) {
        waitForElementVisible(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_NAME, textboxName);
        return getAttributeValue(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_NAME, "value", textboxName);
    }

    public String getTextboxAttributeByText(String textboxText) {
        waitForElementVisible(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_TEXT, textboxText);
        return getAttributeValue(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_TEXT, "value", textboxText);
    }

    public String getItemTextSelectedInDropdownByText(String dropdownLabel) {
        return getElementText(driver, BaseElementUI.DYNAMIC_ITEM_SELECTED_IN_DROPDOWN_BY_TEXT, dropdownLabel);
    }

    public void openMainMenuPageByName(String pageName) {
        waitForElementClickable(driver, BaseElementUI.DYNAMIC_MAIN_MENU_MODULE, pageName);
        clickToElement(driver, BaseElementUI.DYNAMIC_MAIN_MENU_MODULE, pageName);
        waitForSpinnerIconInvisible();
    }

    public boolean isValueDisplayedAtColumnName(String columnName, String rowIndex, String rowValue) {
        int columnIndex = getListElementSize(driver, BaseElementUI.DYNAMIC_INDEX_BY_COLUMN_NAME, columnName) + 1;
        return isElementDisplayed(driver, BaseElementUI.DYNAMIC_ROW_VALUE_BY_COLUMN_INDEX_ROW_INDEX, rowIndex, String.valueOf(columnIndex), rowValue);
    }

    public boolean isValueDisplayedAtColumnNameWithTableName(String tableName, String columnName, String rowIndex, String rowValue) {
        int columnIndex = getListElementSize(driver, BaseElementUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME_WITH_TABLE_NAME, tableName, columnName) + 1;
        return isElementDisplayed(driver, BaseElementUI.DYNAMIC_ROW_VALUE_BY_COLUMN_INDEX_ROW_INDEX_WITH_TABLE_NAME, tableName, rowIndex, String.valueOf(columnIndex), rowValue);
    }

    public boolean isRecordPresentInTableByColumnName(String columnName, String rowValue) {
        int columnIndex = getListElementSize(driver, BaseElementUI.DYNAMIC_INDEX_BY_COLUMN_NAME, columnName) + 1;
        List<WebElement> rows = getListWebElement(driver, BaseElementUI.ALL_ROWS_IN_TABLE);
        for (int i = 1; i <= rows.size(); i++) {
            String dynamicRowLocator = String.format(BaseElementUI.DYNAMIC_ROW_VALUE_BY_COLUMN_INDEX_ROW_INDEX, i, String.valueOf(columnIndex), rowValue);
            if (isElementDisplayed(driver, dynamicRowLocator)) {
                return true;
            }
        }
        return false;
    }

    public void clickToRadioButtonByLabel(String radioLabelName) {
        clickToElementByJS(driver, BaseElementUI.DYNAMIC_RADIOBUTTON_BY_TEXT, radioLabelName);
    }

    public boolean isRadioButtonItemSelectedByLabel(String radioLabelName) {
        return isElementSelected(driver, BaseElementUI.DYNAMIC_RADIOBUTTON_BY_TEXT, radioLabelName);
    }

    public void clickToCheckboxByLabel(String checkboxLabelName) {
        if (!isElementSelected(driver, BaseElementUI.DYNAMIC_CHECKBOX_BY_TEXT, checkboxLabelName)){
            clickToElementByJS(driver, BaseElementUI.DYNAMIC_CHECKBOX_BY_TEXT, checkboxLabelName);
        }
    }

    public void selectInDropdown(String dropdownName, String itemSelected) {
        String formattedDropdownLocator = String.format(BaseElementUI.DYNAMIC_DROPDOWN_BY_TEXT, dropdownName);
        String formattedItemLocator = String.format(BaseElementUI.DYNAMIC_ITEM_IN_DROPDOWN_BY_TEXT, dropdownName, itemSelected);

        waitForElementClickable(driver, formattedDropdownLocator);
        selectItemInCustomDropdown(driver, formattedDropdownLocator, formattedItemLocator, itemSelected);
    }

    public void enterAndSelectInDropdown(String dropdownName, String valueToSend, String itemSelected) {
        String formattedDropdownLocator = String.format(BaseElementUI.DYNAMIC_TEXTBOX_BY_TEXT, dropdownName);
        String formattedItemLocator = String.format(BaseElementUI.DYNAMIC_ITEM_IN_EDITABLE_DROPDOWN_BY_TEXT, dropdownName, itemSelected);

        waitForElementVisible(driver, formattedDropdownLocator);
        selectItemInEditableDropdown(driver, formattedDropdownLocator, valueToSend, formattedItemLocator, itemSelected);
    }

    public void clickToButtonIncludeLabel(String parentLabel, String buttonLabel) {
        waitForElementClickable(driver, BaseElementUI.DYNAMIC_BUTTON_BY_LABEL, parentLabel, buttonLabel);
        clickToElement(driver, BaseElementUI.DYNAMIC_BUTTON_BY_LABEL, parentLabel, buttonLabel);
    }

    public void uploadFileType(WebDriver driver, String uploadFieldLabel, String... fileNames) {
        String filePath = GlobalConstants.getGlobalConstants().getUploadPath();
        String fullFileName = "";
        for (String file: fileNames) {
            fullFileName = fullFileName + filePath + file + "\n";
        }
        fullFileName = fullFileName.trim();
        String dynamicXPath = String.format(BaseElementUI.UPLOAD_FILE_TYPE, uploadFieldLabel);
        getWebElement(driver, dynamicXPath).sendKeys(fullFileName);
        //getWebElement(driver, BaseElementUI.UPLOAD_FILE_TYPE).sendKeys(fullFileName);
        sleepInSeconds(2);
    }

    public boolean isFileLoadedSuccess(String uploadFieldLabel, String fileName) {
        waitForElementVisible(driver, BaseElementUI.FILE_LOADED, uploadFieldLabel, fileName);
        return isElementDisplayed(driver, BaseElementUI.FILE_LOADED, uploadFieldLabel, fileName);
    }

    public void clickToToggleByLabel(String toggleLabel) {
        waitForElementClickable(driver, BaseElementUI.DYNAMIC_TOGGLE_BY_TEXT, toggleLabel);
        clickToElement(driver, BaseElementUI.DYNAMIC_TOGGLE_BY_TEXT, toggleLabel);
    }

    public void scrollToElementAtBottomPage() {
        scrollToElementOnDown(driver, BaseElementUI.ELEMENT_ON_DOWN);
    }


    public void clickToHeaderMenu(String headerMenu) {
        waitForElementClickable(driver, BaseElementUI.DYNAMIC_HEADER_MENU_BY_LABEL, headerMenu);
        clickToElement(driver, BaseElementUI.DYNAMIC_HEADER_MENU_BY_LABEL, headerMenu);
    }

    public void clickToItemInHeaderMenu(String childItemMenu) {
        waitForElementClickable(driver, BaseElementUI.DYNAMIC_ITEM_IN_HEADER_MENU_BY_LABEL, childItemMenu);
        clickToElement(driver, BaseElementUI.DYNAMIC_ITEM_IN_HEADER_MENU_BY_LABEL, childItemMenu);
        waitForSpinnerIconInvisible();
    }

    public String getTextboxErrorMessageByTextboxLabel(String textboxLabel) {
        waitForElementVisible(driver, BaseElementUI.DYNAMIC_TEXTBOX_ERROR_MSG_BY_TEXTBOX_LABEL, textboxLabel);
        return getElementText(driver, BaseElementUI.DYNAMIC_TEXTBOX_ERROR_MSG_BY_TEXTBOX_LABEL, textboxLabel);
    }

    public void verifyAttachmentsTable(EmployeeInfo employeeInfo) {
        Assert.assertTrue(isValueDisplayedAtColumnNameWithTableName("Attachments","File Name", "1", employeeInfo.getImage()));
        Assert.assertTrue(isValueDisplayedAtColumnNameWithTableName("Attachments","Date Added", "1", employeeInfo.getDateAdded()));
    }

    public void addAttachmentFile(EmployeeInfo employeeInfo) {
        clickToButtonIncludeLabel("Attachments", "Add");
        Assert.assertTrue(isPageHeaderDisplayed("Add Attachment"));

        uploadFileType(driver, "Select File", employeeInfo.getImage());

        Assert.assertTrue(isFileLoadedSuccess("Select File", employeeInfo.getImage()));
    }

    public void clickToSaveButtonWithHeader(String headerName) {
        waitForElementClickable(driver, BaseElementUI.SAVE_BUTTON_WITH_HEADER_NAME, headerName);
        clickToElement(driver, BaseElementUI.SAVE_BUTTON_WITH_HEADER_NAME, headerName);
    }
}
