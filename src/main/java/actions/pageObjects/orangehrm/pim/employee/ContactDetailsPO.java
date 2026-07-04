package actions.pageObjects.orangehrm.pim.employee;

import org.openqa.selenium.WebDriver;

public class ContactDetailsPO extends EmployeeTabs {
    private WebDriver driver;

    public ContactDetailsPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}