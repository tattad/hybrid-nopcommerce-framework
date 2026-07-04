package actions.pageObjects.jquery;

import actions.commons.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import interfaces.pageUIs.jquery.HomePageUI;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

public class HomePO extends BasePage {
    WebDriver driver;

    public HomePO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Open page number {0}")
    public void openPageByNumber(String pageNumber) {
        waitForElementClickable(driver, HomePageUI.DYNAMIC_PAGE_LINK, pageNumber);
        clickToElement(driver, HomePageUI.DYNAMIC_PAGE_LINK, pageNumber);
        closeToGoogleAd();
        sleepInSecond(2);
    }

    @Step("Close Google Ad")
    public void closeToGoogleAd() {
        if (isElementDisplayed(driver, HomePageUI.IFRAME_GOOGLE_AD)) {
            switchToIFrame(driver, HomePageUI.IFRAME_GOOGLE_AD);
            waitForElementClickable(driver, HomePageUI.GOOGLE_AD_CLOSE_BUTTON);
            clickToElement(driver, HomePageUI.GOOGLE_AD_CLOSE_BUTTON);
            switchToDefaultContent(driver);
        }
    }

    @Step("Check if page number {0} is active")
    public boolean isPageNumberActived(String pageNumber) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_PAGE_LINK, pageNumber);
        return getElementAttribute(driver, HomePageUI.DYNAMIC_PAGE_LINK, "class", pageNumber).endsWith("active");
    }

    @Step("Enter value ({1}) into textbox by header name ({0})")
    public void enterToTextboxByHeaderName(String headerName, String value) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_HEADER_NAME, headerName);
        sendkeyToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_HEADER_NAME, value, headerName);
        pressKeyToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_HEADER_NAME, Keys.ENTER, headerName);
    }

    @Step("Check if row data is displayed")
    public boolean isRowDataValueDisplayed(String females, String country, String males, String total) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_DATA_ROW, females, country, males, total);
        return isElementDisplayed(driver, HomePageUI.DYNAMIC_DATA_ROW, females, country, males, total);
    }

    @Step("Remove row by country name {0}")
    public void removeRowByCountryName(String countryName) {
        waitForElementClickable(driver, HomePageUI.DYNAMIC_REMOVE_BUTTON_BY_COUNTRY_NAME, countryName);
        clickToElement(driver, HomePageUI.DYNAMIC_REMOVE_BUTTON_BY_COUNTRY_NAME, countryName);
        sleepInSecond(2);
    }

    @Step("Edit row by country name {0}")
    public void editRowByCountryName(String countryName) {
        waitForElementClickable(driver, HomePageUI.DYNAMIC_EDIT_BUTTON_BY_COUNTRY_NAME, countryName);
        clickToElement(driver, HomePageUI.DYNAMIC_EDIT_BUTTON_BY_COUNTRY_NAME, countryName);
        sleepInSecond(2);
    }

    @Step("Edit field name {0} with data {1}")
    public void editRecordDetails(String fieldName, String editData) {
        waitForElementVisible(driver, HomePageUI.EDIT_RECORD_POPUP);
        sendkeyToElement(driver, HomePageUI.DYNAMIC_EDIT, editData, fieldName);
        clickToElement(driver, HomePageUI.SUBMIT_BUTTON);
    }

    @Step("Click load data button")
    public void clickToLoadDataButon() {
        waitForElementClickable(driver, HomePageUI.LOAD_DATA_BUTTON);
        clickToElement(driver, HomePageUI.LOAD_DATA_BUTTON);
    }

    @Step("Enter text ({2}) into textbox by row index ({0}) and column name ({1})")
    public void enterToTextboxByIndex(String rowIndex, String columnName, String value) {
        //Từ column name làm sao để lấy ra được column index
        int columnIndexNumber = getListElement(driver, HomePageUI.DYNAMIC_PRECEDING_SIBLING_COLUMN_NUMBER, columnName).size() + 1;
        //Convert nó qua dạng text (String)
        String columnIndex = String.valueOf(columnIndexNumber);
        //Truyền 2 giá trị: rowIndex/ columnIndex vào locator để tương tác và sendkey
        sendkeyToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_ROW_AND_COLUMN_INDEX, value, rowIndex, columnIndex);
    }

    @Step("Select ({2}) at row number {0} and column name {1}")
    public void selectToDropdownByIndex(String rowIndex, String columnName, String valueToSelect) {
        int columnIndexNumber = getListElement(driver, HomePageUI.DYNAMIC_PRECEDING_SIBLING_COLUMN_NUMBER, columnName).size() + 1;
        String columnIndex = String.valueOf(columnIndexNumber);
        //Truyền 2 giá trị: rowIndex/ columnIndex vào locator để tương tác và select dropdown
        selectItemInDropdown(driver, HomePageUI.DYNAMIC_DROPDOWN_BY_ROW_AND_COLUMN_INDEX, valueToSelect, rowIndex, columnIndex);
//        selectItemInDropdown(driver, HomePageUI.DYNAMIC_DROPDOWN_BY_ROW_AND_COLUMN_INDEX_2, valueToSelect, columnIndex, rowIndex);
    }

    @Step("Check to checkbox by index {0} and column name {1}")
    public void checkToCheckboxByIndex(String rowIndex, String columnName, boolean checkOrUncheck) {
        int columnIndexNumber = getListElement(driver, HomePageUI.DYNAMIC_PRECEDING_SIBLING_COLUMN_NUMBER, columnName).size() + 1;
        String columnIndex = String.valueOf(columnIndexNumber);
        if (checkOrUncheck) {
            checkToCheckboxOrRadio(driver, HomePageUI.DYNAMIC_CHECKBOX_BY_ROW_AND_COLUMN_INDEX, rowIndex, columnIndex);
        } else {
            uncheckToCheckbox(driver, HomePageUI.DYNAMIC_CHECKBOX_BY_ROW_AND_COLUMN_INDEX, rowIndex, columnIndex);
        }
    }

    @Step("Click icon by index {0} and icon name {1}")
    public void clickToIconByIndex(String rowIndex, String iconName) {
        waitForElementClickable(driver, HomePageUI.DYNAMIC_ICON_BY_ROW_INDEX, rowIndex, iconName);
        clickToElement(driver, HomePageUI.DYNAMIC_ICON_BY_ROW_INDEX, rowIndex, iconName);
    }

    public List<String> getAllValueAtColumnName(String columnName) {
        int columnIndexNumber = getListElement(driver, HomePageUI.DYNAMIC_PRECEDING_SIBLING_COLUMN_NUMBER_2, columnName).size() + 1;
        String columnIndex = String.valueOf(columnIndexNumber);
        List<WebElement> allElementValueAtColumn = getListElement(driver, HomePageUI.ALL_VALUE_BY_COLUMN_INDEX, columnIndex);
        List<String> allTextValues = new ArrayList<>();

        System.out.println("-------------------------");
        for (WebElement element : allElementValueAtColumn) {
            allTextValues.add(element.getText());
        }
        System.out.println(allTextValues);
        return allTextValues;
    }

    public boolean isFileLoadedByName(String fileName) {
        waitForElementVisible(driver, HomePageUI.FILE_LOADED_BY_FILE_NAME, fileName);
        return isElementDisplayed(driver, HomePageUI.FILE_LOADED_BY_FILE_NAME, fileName);
    }

    public void clickToUploadButton() {
        List<WebElement> startButton = getListElement(driver, HomePageUI.UPLOAD_BUTTON);
        for (WebElement button : startButton) {
            button.click();
            sleepInSecond(3);
        }
    }

    public boolean isFileUploadedByName(String fileName) {
        waitForElementVisible(driver, HomePageUI.FILE_UPLOADED_SUCCESS_BY_FILE_NAME, fileName);
        return isElementDisplayed(driver, HomePageUI.FILE_UPLOADED_SUCCESS_BY_FILE_NAME, fileName);
    }
}