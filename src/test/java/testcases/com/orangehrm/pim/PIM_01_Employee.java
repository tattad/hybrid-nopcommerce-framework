package testcases.com.orangehrm.pim;

import actions.commons.BaseTest;
import actions.pageObjects.orangehrm.DashboardPO;
import actions.pageObjects.orangehrm.LoginPO;
import actions.pageObjects.orangehrm.PageGenerator;
import actions.pageObjects.orangehrm.pim.employee.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class PIM_01_Employee extends BaseTest {
    private WebDriver driver;
    private LoginPO loginPage;
    private DashboardPO dashboardPage;
    private AddNewEmployeePO addNewEmployeePage;
    private EmployeeListPO employeeListPage;
    private PersonalDetailsPO personalDetailsPage;
    private ContactDetailsPO contactDetailsPage;
    private EmergencyContactsPO emergencyContactsPage;
    private String employeeID, employeeFirstName, employeeLastName;
    private String employeeUsername, employeePassword;
    private String avatarImageName = "female.jpg";
    private String emergencyContactName = "", emergencyContactRelationship = "", emergencyContactHomeTelephone = "", emergencyContactMobile = "", emergencyContactWorkTelephone = "";

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

    //    @Test
    public void Employee_03_Edit_Personal_Details() {
        personalDetailsPage.openPersonalDetailsPage();

        /*Edit Personal Details*/
        personalDetailsPage.clearTextBoxByName(driver, "firstName");
        personalDetailsPage.enterToTextBoxByName(driver, "firstName", "Hani");
        personalDetailsPage.clearTextBoxByName(driver, "lastName");
        personalDetailsPage.enterToTextBoxByName(driver, "lastName", "0013");
        personalDetailsPage.selectDropdownByLabel(driver, "Nationality", "German");
        personalDetailsPage.selectDropdownByLabel(driver, "Marital Status", "Married");
        personalDetailsPage.sleepInSecond(1);
        personalDetailsPage.clickToRadioButtonByLabel(driver, "Female");

        personalDetailsPage.clickToButtonByText(driver, "Save");
        verifyTrue(personalDetailsPage.isToastMessageDisplayed(driver, "Successfully Updated"));
        verifyTrue(personalDetailsPage.isLoadingSpinnerDisappear(driver));
        personalDetailsPage.sleepInSecond(2);

        /*Add Attachment files*/
        personalDetailsPage.clickToButtonByText(driver, "Add");
        personalDetailsPage.uploadMultipleFiles(driver, avatarImageName);
        personalDetailsPage.enterToTextAreaByLabel(driver, "Comment", "Add an image");
        personalDetailsPage.clickToButtonByMainTitle(driver, "Save", "Add Attachment");
        verifyTrue(personalDetailsPage.isToastMessageDisplayed(driver, "Successfully Saved"));
        verifyTrue(personalDetailsPage.isLoadingSpinnerDisappear(driver));

        personalDetailsPage.clickToButtonByText(driver, "Add");
        personalDetailsPage.uploadMultipleFiles(driver, "cutehost.jpg");
        personalDetailsPage.clickToButtonByMainTitle(driver, "Save", "Add Attachment");
        verifyTrue(personalDetailsPage.isToastMessageDisplayed(driver, "Successfully Saved"));
        verifyTrue(personalDetailsPage.isLoadingSpinnerDisappear(driver));

        /*Edit Records Found*/
        personalDetailsPage.deleteRecordByFileName(driver, avatarImageName);
        verifyTrue(personalDetailsPage.isConfirmationPopupDisplayed("Are you Sure?", "The selected record will be permanently deleted. Are you sure you want to continue?"));
        personalDetailsPage.sleepInSecond(2);
        personalDetailsPage.clickToButtonByText(driver, "Yes, Delete");

        verifyTrue(personalDetailsPage.isToastMessageDisplayed(driver, "Successfully Deleted"));
        verifyTrue(personalDetailsPage.isLoadingSpinnerDisappear(driver));

        personalDetailsPage.selectAllRecordsByFirstColumnName(driver, "File Name");
        personalDetailsPage.clickToButtonByText(driver, "Delete Selected");

        verifyTrue(personalDetailsPage.isConfirmationPopupDisplayed("Are you Sure?", "The selected record will be permanently deleted. Are you sure you want to continue?"));
        personalDetailsPage.sleepInSecond(2);
        personalDetailsPage.clickToButtonByText(driver, "Yes, Delete");

        verifyTrue(personalDetailsPage.isToastMessageDisplayed(driver, "Successfully Deleted"));
        verifyTrue(personalDetailsPage.isLoadingSpinnerDisappear(driver));
    }

    //    @Test
    public void Employee_04_Contact_Details() {
        dashboardPage.clickToModuleByTextInMenuItem(driver, "My Info");
        personalDetailsPage = PageGenerator.getPage(PersonalDetailsPO.class, driver);
        verifyTrue(personalDetailsPage.isLoadingSpinnerDisappear(driver));
        personalDetailsPage.sleepInSecond(2);

        personalDetailsPage.openContactDetailsPage();
        contactDetailsPage = PageGenerator.getPage(ContactDetailsPO.class, driver);
        verifyTrue(contactDetailsPage.isLoadingSpinnerDisappear(driver));
        contactDetailsPage.sleepInSecond(2);

        contactDetailsPage.enterToTextBoxByLabel(driver, "Street 1", "Street 1");
        contactDetailsPage.enterToTextBoxByLabel(driver, "Street 2", "Street 2");
        contactDetailsPage.enterToTextBoxByLabel(driver, "City", "Ho Chi Minh");
        contactDetailsPage.selectDropdownByLabel(driver, "Country", "France");
        contactDetailsPage.enterToTextBoxByLabel(driver, "Mobile", "+847755443311");

        contactDetailsPage.clickToButtonByText(driver, "Save");
        verifyTrue(contactDetailsPage.isToastMessageDisplayed(driver, "Successfully Updated"));
        verifyTrue(contactDetailsPage.isLoadingSpinnerDisappear(driver));
        contactDetailsPage.sleepInSecond(2);
    }

    @Test
    public void Employee_05_Emergency_Contacts() {
        personalDetailsPage.openEmergencyContactsPage();
        emergencyContactsPage = PageGenerator.getPage(EmergencyContactsPO.class, driver);
        verifyTrue(emergencyContactsPage.isLoadingSpinnerDisappear(driver));
        emergencyContactsPage.sleepInSecond(2);

        emergencyContactName = "Contact 2";
        emergencyContactRelationship = "Relations 2";
        emergencyContactMobile = "+847755443311";
        emergencyContactsPage.clickToButtonByMainTitle(driver, "Add", "Assigned Emergency Contacts");
        emergencyContactsPage.enterToTextBoxByLabel(driver, "Name", emergencyContactName);
        emergencyContactsPage.enterToTextBoxByLabel(driver, "Relationship", emergencyContactRelationship);
        emergencyContactsPage.enterToTextBoxByLabel(driver, "Mobile", emergencyContactMobile);
        emergencyContactsPage.clickToButtonByText(driver, "Save");
        verifyTrue(emergencyContactsPage.isToastMessageDisplayed(driver, "Successfully Saved"));
        verifyTrue(emergencyContactsPage.isLoadingSpinnerDisappear(driver));
        emergencyContactsPage.sleepInSecond(2);

        List<String> expectedRowData = Arrays.asList(emergencyContactName, emergencyContactRelationship, emergencyContactHomeTelephone, emergencyContactMobile, emergencyContactWorkTelephone);
        verifyEqual(emergencyContactsPage.getAllEmergencyContactValuesInRow(driver, emergencyContactName), expectedRowData);
    }

//    @Test
public void Employee_06_Dependents() {
    dashboardPage.clickToModuleByTextInMenuItem(driver, "My Info");
    personalDetailsPage = PageGenerator.getPage(PersonalDetailsPO.class, driver);
    verifyTrue(personalDetailsPage.isLoadingSpinnerDisappear(driver));
    personalDetailsPage.sleepInSecond(2);


}

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
