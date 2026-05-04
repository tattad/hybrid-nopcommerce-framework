package pageFactory;

import org.openqa.selenium.WebDriver;

public class CustomerInfoPageFactory extends BasePage {
    private WebDriver driver;

    public CustomerInfoPageFactory(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isGenderMaleIsSelected() {
        return false;
    }

    public byte[] getFirstNameTextboxValue() {
    }

    public byte[] getLastNameTextboxValue() {
    }

    public byte[] getEmailTextboxValue() {
    }

    public byte[] getCompanyTextboxValue() {
    }
}