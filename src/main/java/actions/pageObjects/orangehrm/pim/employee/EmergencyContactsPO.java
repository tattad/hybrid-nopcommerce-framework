package actions.pageObjects.orangehrm.pim.employee;

import org.openqa.selenium.WebDriver;

public class EmergencyContactsPO extends EmployeeTabs {
    private WebDriver driver;

    public EmergencyContactsPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}