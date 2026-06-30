package pageObjects.nopCommerce.user;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.nopCommerce.PageGenerators;
import pageUIs.nopCommerce.user.UserLoginPageUI;

public class UserLoginPO extends BasePage {

    private WebDriver driver;

    public UserLoginPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void enterToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, UserLoginPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    @Step("Enter to Password textbox with value is: {0}")
    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, password);
    }

    @Step("Click to Login button")
    public UserHomePO clickToLoginButton() {
        waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
        return PageGenerators.getUserHomePage(driver);
    }

    @Step("Login to application with valid Email and Password")
    public UserHomePO loginToSystem(String emailAddress, String password) {
        enterToEmailTextbox(emailAddress);
        enterToPasswordTextbox(password);
        clickToLoginButton();
        return PageGenerators.getUserHomePage(driver);
    }
}