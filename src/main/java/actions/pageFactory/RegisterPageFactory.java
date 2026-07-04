package actions.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPageFactory extends BasePage {
    private WebDriver driver;

    public RegisterPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "gender-male")
    private WebElement genderMaleRadio;

    @FindBy(id = "FirstName")
    private WebElement firstNameTextbox;

    @FindBy(id = "LastName")
    private WebElement lastNameTextbox;

    @FindBy(id = "Email")
    private WebElement emailTextbox;

    @FindBy(id = "Company")
    private WebElement companyNameTextbox;

    @FindBy(id = "Password")
    private WebElement passwordTextbox;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPasswordTextbox;

    @FindBy(id = "register-button")
    private WebElement registerButton;

    @FindBy(className = "result")
    private WebElement registerSuccessMessage;

    @FindBy(className = "ico-login")
    private WebElement loginLink;

    @FindBy(xpath = "//li[text()='1']")
    @CacheLookup
    private WebElement oneNumberText;

    @FindBy(css = "button.fhs-btn-login")
    private WebElement loginButton;

    public boolean isLoginButtonDisabled() {
        waitForElementVisible(driver, loginButton); //Page Factory: find element first time
        return !isElementEnabled(loginButton); //Page Factory: find element second time
    }

    public void clickToLoginButton() {
        waitForElementClickable(driver, loginButton); //Page Factory: find element third time
        loginButton.click(); //Page Factory: find element fourth time
    }

    public void clickToOneNumberText() {
        waitForElementClickable(driver, oneNumberText);
        clickToElement(oneNumberText);
    }

    public boolean isOneNumberTextSelected() {
        waitForElementSelected(driver, oneNumberText);
        return isElementSelected(oneNumberText);
    }

    public void clickToMaleRadio() {
        waitForElementClickable(driver, genderMaleRadio);
        clickToElement(genderMaleRadio);
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, firstNameTextbox);
        sendkeyToElement(firstNameTextbox, firstName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, lastNameTextbox);
        sendkeyToElement(lastNameTextbox, lastName);
    }

    public void enterToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, emailTextbox);
        sendkeyToElement(emailTextbox, emailAddress);
    }

    public void enterToCompanyTextbox(String companyName) {
        waitForElementVisible(driver, companyNameTextbox);
        sendkeyToElement(companyNameTextbox, companyName);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, passwordTextbox);
        sendkeyToElement(passwordTextbox, password);
    }

    public void enterToConfirmPasswordTextbox(String password) {
        waitForElementVisible(driver, confirmPasswordTextbox);
        sendkeyToElement(confirmPasswordTextbox, password);
    }

    public void clickToRegisterButton() {
        waitForElementClickable(driver, registerButton);
        clickToElement(registerButton);
    }

    public String getRegisterSuccessMessage() {
        waitForElementVisible(driver, registerSuccessMessage);
        return getElementText(registerSuccessMessage);
    }

    public void clickToLoginLink() {
        waitForElementClickable(driver, loginLink);
        clickToElement(loginLink);
    }
}