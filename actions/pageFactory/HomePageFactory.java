package pageFactory;

import org.openqa.selenium.WebDriver;

public class HomePageFactory extends BasePage {
    private WebDriver driver;

    public HomePageFactory(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToRegisterLink() {

    }

    public boolean isMyAccountLinkDisplayed() {
        return false;
    }

    public void clickToMyAccountLink() {

    }
}