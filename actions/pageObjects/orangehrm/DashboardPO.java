package pageObjects.orangehrm;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.orangehrm.pim.employee.EmployeeListPO;

public class DashboardPO extends BasePage {
    private WebDriver driver;

    public DashboardPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public EmployeeListPO clickToPIMPage() {
        return null;
    }
}