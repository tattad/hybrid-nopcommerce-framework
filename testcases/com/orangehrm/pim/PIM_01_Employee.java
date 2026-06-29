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

        employeeFirstName = "Ha";
        employeeLastName = "Ni";
        employeeUsername = employeeFirstName + "." + employeeLastName + "." + getRandomNumber();
        employeePassword = "Or@ngeHRM123";

        loginPage.enterToTextBoxByLabel(driver, "Username", GlobalConstants.ADMIN_USERNAME_ORANGEHRM);
        loginPage.enterToTextBoxByLabel(driver, "Password", GlobalConstants.ADMIN_PASSWORD_ORANGEHRM);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPO.class, driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        dashboardPage.waitForAllLoadingIconInvisible(driver);
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Dashboard"));
    }

    @Test
    public void Employee_01_Add_New() {
        dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
        employeeListPage = PageGenerator.getPage(EmployeeListPO.class, driver);
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        employeeListPage.clickToButtonByText(driver, "Add");
        addNewEmployeePage = PageGenerator.getPage(AddNewEmployeePO.class, driver);
        verifyTrue(addNewEmployeePage.isLoadingSpinnerDisappear(driver));

        addNewEmployeePage.enterToTextBoxByName(driver, "firstName", employeeFirstName);
        addNewEmployeePage.enterToTextBoxByName(driver, "lastName", employeeLastName);

        employeeID = addNewEmployeePage.getTextboxValueByLabel(driver, "Employee Id");
        addNewEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");

        addNewEmployeePage.enterToTextBoxByLabel(driver, "Username", employeeUsername);
        addNewEmployeePage.enterToTextBoxByLabel(driver, "Password", employeePassword);
        addNewEmployeePage.enterToTextBoxByLabel(driver, "Confirm Password", employeePassword);
        personalDetailsPage.clickToRadioButtonByLabel(driver, "Enabled");

        addNewEmployeePage.clickToButtonByText(driver, "Save");
        personalDetailsPage = PageGenerator.getPage(PersonalDetailsPO.class, driver);
        verifyTrue(personalDetailsPage.isToastMessageDisplayed(driver, "Successfully Saved"));

        verifyTrue(personalDetailsPage.isLoadingSpinnerDisappear(driver));
        personalDetailsPage.sleepInSecond(2);

        verifyEqual(personalDetailsPage.getTextboxValueByName(driver, "firstName"), employeeFirstName);
        verifyEqual(personalDetailsPage.getTextboxValueByName(driver, "lastName"), employeeLastName);
        verifyEqual(personalDetailsPage.getTextboxValueByLabel(driver, "Employee Id"), employeeID);

        personalDetailsPage.selectDropdownByLabel(driver, "Nationality", "British");
        personalDetailsPage.selectDropdownByLabel(driver, "Marital Status", "Married");
        personalDetailsPage.clickToButtonByText(driver, "Save");
        verifyTrue(personalDetailsPage.isToastMessageDisplayed(driver, "Successfully Updated"));

//        //Logout
//        loginPage = personalDetailsPage.clickLogoutOnTopMenu(driver);
//
//        //Login = quyền user vừa tạo
//        loginPage.enterToTextBoxByLabel(driver, "Username", employeeUsername);
//        loginPage.enterToTextBoxByLabel(driver, "Password", employeePassword);
//        loginPage.clickToButtonByText(driver, "Login");
//        dashboardPage = PageGenerator.getPage(DashboardPO.class, driver);
//
//        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
//        dashboardPage.sleepInSecond(2);
//
//        //Đến màn hình Dashboard
//        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "My Info"));
//
//        dashboardPage.clickToModuleByTextInMenuItem(driver, "My Info");
//        personalDetailsPage = PageGenerator.getPage(PersonalDetailsPO.class, driver);
    }

    //    @Test
    public void Employee_02_Upload_Avatar() {
        personalDetailsPage.clickToEmployeeAvatar();
        Dimension beforeUpload = personalDetailsPage.getAvatarSize();
        personalDetailsPage.uploadMultipleFiles(driver, avatarImageName);

        personalDetailsPage.clickToSaveButtonAtChangeProfilePictureContainer();
        Assert.assertTrue(personalDetailsPage.isSuccessMessageDisplayed());

        personalDetailsPage.waitForAllLoadingIconInvisible(driver);
        Assert.assertTrue(personalDetailsPage.isProfileAvatarUpdateSuccess(beforeUpload));
    }

//    @Test
//    public void Employee_03_Personal_Details() {
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

    @AfterClass
    public void afterClass() {
        closeBrowserDriver();
    }
}
