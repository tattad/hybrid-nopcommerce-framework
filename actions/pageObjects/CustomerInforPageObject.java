package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.CustomerInfoPageUI;

public class CustomerInforPageObject extends BasePage {

    private WebDriver driver;

    public CustomerInforPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean isGenderMaleIsSelected() {
        waitForElementSelected(CustomerInfoPageUI.GENDER_MALE_RADIO);
        return isElementSelected(CustomerInfoPageUI.GENDER_MALE_RADIO);
    }

    public String getFirstNameTextboxValue() {
        waitForElementVisible(CustomerInfoPageUI.FIRST_NAME_TEXTBOX);
        return getElementAttribute(CustomerInfoPageUI.FIRST_NAME_TEXTBOX, "value");
    }

    public String getLastNameTextboxValue() {
        waitForElementVisible(CustomerInfoPageUI.LAST_NAME_TEXTBOX);
        return getElementAttribute(CustomerInfoPageUI.LAST_NAME_TEXTBOX, "value");
    }

    public String getEmailTextboxValue() {
        waitForElementVisible(CustomerInfoPageUI.EMAIL_TEXTBOX);
        return getElementAttribute(CustomerInfoPageUI.EMAIL_TEXTBOX, "value");
    }

    public String getCompanyTextboxValue() {
        waitForElementVisible(CustomerInfoPageUI.COMPANY_NAME_TEXTBOX);
        return getElementAttribute(CustomerInfoPageUI.COMPANY_NAME_TEXTBOX, "value");
    }
}