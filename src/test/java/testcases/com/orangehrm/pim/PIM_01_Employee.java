package testcases.com.orangehrm.pim;

import actions.commons.BaseTest;
import actions.pageObjects.orangehrm.DashboardPO;
import actions.pageObjects.orangehrm.LoginPO;
import actions.pageObjects.orangehrm.PageGenerator;
import actions.pageObjects.orangehrm.pim.employee.AddNewEmployeePO;
import actions.pageObjects.orangehrm.pim.employee.EmployeeListPO;
import actions.pageObjects.orangehrm.pim.employee.PersonalDetailsPO;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PIM_01_Employee extends BaseTest {
    private WebDriver driver;
    private LoginPO loginPage;
    private DashboardPO dashboardPage;
    private AddNewEmployeePO addNewEmployeePage;
    private EmployeeListPO employeeListPage;
    private PersonalDetailsPO personalDetailsPage;
    private String employeeID, employeeFirstName, employeeLastName;
    private String employeeUsername, employeePassword;
    private String avatarImageName = "female.jpg";

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browser, String url) {
        driver = getBrowserDriver(browser, url);

        loginPage = PageGenerator.getPage(LoginPO.class, driver);

        employeeFirstName = "Hani";
//        employeeID = String.valueOf(getRandomNumber());
        employeePassword = "Or@ngeHRM123";

//        loginPage.enterToTextBoxByLabel(driver, "Username", "Admin");
//        loginPage.enterToTextBoxByLabel(driver, "Password", "admin123");
        loginPage.enterToTextBoxByLabel(driver, "Username", "Hani.0013");
        loginPage.enterToTextBoxByLabel(driver, "Password", "Or@ngeHRM123");
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPO.class, driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Dashboard"));
    }

    //    @Test
    public void Employee_01_NewEmployee_Enabled() {
        dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
        employeeListPage = PageGenerator.getPage(EmployeeListPO.class, driver);
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        employeeListPage.clickToButtonByText(driver, "Add");
        addNewEmployeePage = PageGenerator.getPage(AddNewEmployeePO.class, driver);
        verifyTrue(addNewEmployeePage.isLoadingSpinnerDisappear(driver));

//        addNewEmployeePage.clearTextBoxByLabel(driver, "Employee Id");
//        addNewEmployeePage.enterToTextBoxByLabel(driver, "Employee Id", employeeID);
        employeeID = addNewEmployeePage.getTextboxValueByLabel(driver, "Employee Id");
        employeeLastName = employeeID;
        addNewEmployeePage.enterToTextBoxByName(driver, "firstName", employeeFirstName);
        addNewEmployeePage.enterToTextBoxByName(driver, "lastName", employeeLastName);

        employeeID = addNewEmployeePage.getTextboxValueByLabel(driver, "Employee Id");
        addNewEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");

        employeeUsername = employeeFirstName + "." + employeeLastName;
        addNewEmployeePage.enterToTextBoxByLabel(driver, "Username", employeeUsername);
        addNewEmployeePage.enterToTextBoxByLabel(driver, "Password", employeePassword);
        addNewEmployeePage.enterToTextBoxByLabel(driver, "Confirm Password", employeePassword);
//        addNewEmployeePage.clickToRadioButtonByLabel(driver, "Enabled");

        addNewEmployeePage.clickToButtonByText(driver, "Save");
        personalDetailsPage = PageGenerator.getPage(PersonalDetailsPO.class, driver);
        verifyTrue(personalDetailsPage.isToastMessageDisplayed(driver, "Successfully Saved"));

        verifyTrue(personalDetailsPage.isLoadingSpinnerDisappear(driver));
        personalDetailsPage.sleepInSecond(5);

        verifyEqual(personalDetailsPage.getTextboxValueByName(driver, "firstName"), employeeFirstName);
        verifyEqual(personalDetailsPage.getTextboxValueByName(driver, "lastName"), employeeLastName);
        verifyEqual(personalDetailsPage.getTextboxValueByLabel(driver, "Employee Id"), employeeID);

        //Logout
        loginPage = personalDetailsPage.clickLogoutOnTopMenu(driver);

        //Login bằng employee vừa tạo
        loginPage.enterToTextBoxByLabel(driver, "Username", employeeUsername);
        loginPage.enterToTextBoxByLabel(driver, "Password", employeePassword);
        loginPage.clickToLoginButton();
        dashboardPage = PageGenerator.getPage(DashboardPO.class, driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(5);

        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "My Info"));

        dashboardPage.clickToModuleByTextInMenuItem(driver, "My Info");
        personalDetailsPage = PageGenerator.getPage(PersonalDetailsPO.class, driver);
        personalDetailsPage.sleepInSecond(5);

        verifyEqual(personalDetailsPage.getTextboxValueByName(driver, "firstName"), employeeFirstName);
        verifyEqual(personalDetailsPage.getTextboxValueByName(driver, "lastName"), employeeLastName);
        verifyEqual(personalDetailsPage.getTextboxValueByLabel(driver, "Employee Id"), employeeID);
    }

    //    @Test
    public void Employee_02_Upload_Avatar() {
        personalDetailsPage.clickToProfileImage();
        Dimension beforeUpload = personalDetailsPage.getProfileNaturalImageSize();
        personalDetailsPage.uploadMultipleFiles(driver, avatarImageName);
//        verifyEqual(personalDetailsPage.getErrorMessageAtProfileImage(), "File type not allowed");
//        verifyEqual(personalDetailsPage.getErrorMessageAtProfileImage(), "Attachment Size Exceeded");

        personalDetailsPage.clickToButtonByText(driver, "Save");
        verifyTrue(personalDetailsPage.isToastMessageDisplayed(driver, "Successfully Updated"));
        verifyTrue(personalDetailsPage.isLoadingSpinnerDisappear(driver));
        personalDetailsPage.sleepInSecond(5);
        Dimension afterUpload = personalDetailsPage.getProfileNaturalImageSize();

//        Assert.assertFalse(personalDetailsPage.isProfileAvatarUpdateSuccess(beforeUpload));
        verifyNotEqual(beforeUpload, afterUpload);
    }

    @Test
    public void Employee_03_Edit_Personal_Details() {
        dashboardPage.clickToModuleByTextInMenuItem(driver, "My Info");
        personalDetailsPage = PageGenerator.getPage(PersonalDetailsPO.class, driver);
        personalDetailsPage.sleepInSecond(5);

//        personalDetailsPage.openPersonalDetailsPage();

        personalDetailsPage.enterToTextBoxByName(driver, "firstName", "0013");
        personalDetailsPage.enterToTextBoxByName(driver, "lastName", "Hani");
        personalDetailsPage.selectDropdownByLabel(driver, "Nationality", "German");
        personalDetailsPage.selectDropdownByLabel(driver, "Marital Status", "Married");
        personalDetailsPage.clickToRadioButtonByLabel(driver, "Female");

        personalDetailsPage.clickToButtonByText(driver, "Save");
        verifyTrue(personalDetailsPage.isToastMessageDisplayed(driver, "Successfully Updated"));
        verifyTrue(personalDetailsPage.isLoadingSpinnerDisappear(driver));
        personalDetailsPage.sleepInSecond(2);

        personalDetailsPage.clickToButtonByText(driver, "Add");
    }

//    @Test
//    public void Employee_04_Contact_Details() {
//
//    }
//
//    @Test
//    public void Employee_05_Emergency_Details() {
//
//    }
//
//    @Test
//    public void Employee_06_Dependents() {
//
//    }
//
//    @Test
//    public void Employee_07_Immigration() {
//
//    }
//
//    @Test
//    public void Employee_08_Job() {
//
//    }
//
//    @Test
//    public void Employee_09_Salary() {
//
//    }
//
//    @Test
//    public void Employee_10_Report_To() {
//
//    }
//
//    @Test
//    public void Employee_11_Qualifications() {
//
//    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }
}
