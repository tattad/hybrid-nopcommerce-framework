package actions.pageObjects.nopCommerce.admin;

import actions.commons.BasePage;
import org.openqa.selenium.WebDriver;

public class AdminDashboardPO extends BasePage {
    WebDriver driver;

    public AdminDashboardPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}