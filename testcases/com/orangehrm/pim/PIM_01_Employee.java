package com.orangehrm.pim;

import commons.BaseTest;
import commons.GlobalConstants;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.orangehrm.DashboardPO;
import pageObjects.orangehrm.LoginPO;
import pageObjects.orangehrm.PageGenerator;
import pageObjects.orangehrm.pim.employee.AddNewEmployeePO;
import pageObjects.orangehrm.pim.employee.EmployeeListPO;
import pageObjects.orangehrm.pim.employee.PersonalDetailsPO;

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

        loginPage.enterToTextBoxByLabel(driver, "Username", GlobalConstants.ADMIN_USERNAME_ORANGEHRM);
        loginPage.enterToTextBoxByLabel(driver, "Password", GlobalConstants.ADMIN_PASSWORD_ORANGEHRM);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPO.class, driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Dashboard"));
    }

    @Test
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
        personalDetailsPage.clickToRadioButtonByLabel(driver, "Enabled");

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

        verifyEqual(personalDetailsPage.getTextboxValueByName(driver, "firstName"), employeeFirstName);
        verifyEqual(personalDetailsPage.getTextboxValueByName(driver, "lastName"), employeeLastName);
        verifyEqual(personalDetailsPage.getTextboxValueByLabel(driver, "Employee Id"), employeeID);
    }

    @Test
    public void Employee_02_Upload_Avatar() {
        personalDetailsPage.clickToProfileImage();
        Dimension beforeUpload = personalDetailsPage.getAvatarSize();
        personalDetailsPage.uploadMultipleFiles(driver, avatarImageName);
//        verifyEqual(personalDetailsPage.getErrorMessageAtProfileImage(), "File type not allowed");
//        verifyEqual(personalDetailsPage.getErrorMessageAtProfileImage(), "Attachment Size Exceeded");

        personalDetailsPage.clickToButtonByText(driver, "Save");
        verifyTrue(personalDetailsPage.isToastMessageDisplayed(driver, "Successfully Updated"));
        verifyTrue(personalDetailsPage.isLoadingSpinnerDisappear(driver));
        personalDetailsPage.sleepInSecond(5);

        Assert.assertTrue(personalDetailsPage.isProfileAvatarUpdateSuccess(beforeUpload));
    }

//    @Test
//    public void Employee_03_Edit_Personal_Details() {
//        personalDetailsPage.openPersonalDetailsPage();
//
//        personalDetailsPage.enterToFirstNameTextbox("");
//        personalDetailsPage.enterToLastNameTextbox("");
//    }

    //    @Test
    public void Employee_04_Contact_Details() {

    }

    //    @Test
    public void Employee_05_Emergency_Details() {

    }

    //    @Test
    public void Employee_06_Dependents() {

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }
}
