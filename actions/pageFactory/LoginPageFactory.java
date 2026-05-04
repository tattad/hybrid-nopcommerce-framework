package pageFactory;

import org.openqa.selenium.WebDriver;

public class LoginPageFactory extends BasePage {
    private WebDriver driver;

    public LoginPageFactory(WebDriver driver) {
        this.driver = driver;
    }

    public void loginToSystem(String emailAddress, String password) {
    }
}