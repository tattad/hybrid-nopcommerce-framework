package pageObjects.orangehrm.pim.employee;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.orangehrm.PageGenerator;
import pageUIs.orangehrm.pim.employee.AddNewEmployeePUI;

public class AddNewEmployeePO extends BasePage {
    private WebDriver driver;

    public AddNewEmployeePO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Enter to FirstName textbox with value: {0}")
    public void enterToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, AddNewEmployeePUI.FIRSTNAME_TEXTBOX);
        sendkeyToElement(driver, AddNewEmployeePUI.FIRSTNAME_TEXTBOX, firstName);
    }

    @Step("Enter to LastName textbox with value: {0}")
    public void enterToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, AddNewEmployeePUI.LASTNAME_TEXTBOX);
        sendkeyToElement(driver, AddNewEmployeePUI.LASTNAME_TEXTBOX, lastName);
    }

    @Step("Get Employee ID from Add Employee page")
    public String getEmployeeID() {
        waitForElementVisible(driver, AddNewEmployeePUI.EMPLOYEE_ID_TEXTBOX);
        return getElementAttribute(driver, AddNewEmployeePUI.EMPLOYEE_ID_TEXTBOX, "value");
    }

    @Step("Click to Save button at Employee container")
    public PersonalDetailsPO clickToSaveButtonAtEmployeeContainer() {
        waitForElementClickable(driver, AddNewEmployeePUI.SAVE_BUTTON_AT_ADD_EMPLOYEE_CONTAINER);
        clickToElement(driver, AddNewEmployeePUI.SAVE_BUTTON_AT_ADD_EMPLOYEE_CONTAINER);
        waitForAllLoadingIconInvisible(driver);
        return PageGenerator.getPage(PersonalDetailsPO.class, driver);
    }
}