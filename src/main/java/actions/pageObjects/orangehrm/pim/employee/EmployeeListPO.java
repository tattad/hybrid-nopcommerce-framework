package actions.pageObjects.orangehrm.pim.employee;

import actions.commons.BasePage;
import org.openqa.selenium.WebDriver;
import actions.pageObjects.orangehrm.PageGenerator;
import interfaces.pageUIs.orangehrm.pim.employee.EmployeeListPUI;

public class EmployeeListPO extends BasePage {
    private WebDriver driver;

    public EmployeeListPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public AddNewEmployeePO clickToAddEmployeeButton() {
        waitForElementClickable(driver, EmployeeListPUI.ADD_EMPLOYEE_NAV_BUTTON);
        clickToElement(driver, EmployeeListPUI.ADD_EMPLOYEE_NAV_BUTTON);
        waitForAllLoadingIconInvisible(driver);
        return PageGenerator.getPage(AddNewEmployeePO.class, driver);
    }
}