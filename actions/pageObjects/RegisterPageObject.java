package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {

    private WebDriver driver;

    public RegisterPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void clickToMaleRadio() {
        waitForElementClickable(RegisterPageUI.GENDER_MALE_RADIO);
        checkToCheckboxOrRadio(RegisterPageUI.GENDER_MALE_RADIO);
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitForElementVisible(RegisterPageUI.FIRST_NAME_TEXTBOX);
        sendkeyToElement(RegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitForElementVisible(RegisterPageUI.LAST_NAME_TEXTBOX);
        sendkeyToElement(RegisterPageUI.LAST_NAME_TEXTBOX, lastName);
    }

    public void enterToEmailTextbox(String email) {
        waitForElementVisible(RegisterPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(RegisterPageUI.EMAIL_TEXTBOX, email);
    }

    public void enterToCompanyTextbox(String companyName) {
        waitForElementVisible(RegisterPageUI.COMPANY_NAME_TEXTBOX);
        sendkeyToElement(RegisterPageUI.COMPANY_NAME_TEXTBOX, companyName);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(RegisterPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(RegisterPageUI.PASSWORD_TEXTBOX, password);
    }

    public void enterToConfirmPasswordTextbox(String confirmPassword) {
        waitForElementVisible(RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendkeyToElement(RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
    }

    public void clickToRegisterButton() {
    }

    public String getRegisterSuccessMessage() {
        return getRegisterSuccessMessage();
    }

    public void clickToLoginButton() {

    }
}