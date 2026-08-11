package com.testcases.nopcommerce.user;

import actions.commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import actions.pageObjects.nopCommerce.PageGenerators;
import actions.pageObjects.nopCommerce.user.UserCustomerInfoPO;
import actions.pageObjects.nopCommerce.user.UserHomePO;
import actions.pageObjects.nopCommerce.user.UserLoginPO;
import actions.pageObjects.nopCommerce.user.UserRegisterPO;

public class Level_14_Log4J extends BaseTest {

    private WebDriver driver;
    private UserHomePO homePage;
    private UserRegisterPO registerPage;
    private UserLoginPO loginPage;
    private UserCustomerInfoPO customerInfoPage;
    private String firstName, lastName, emailAddress, companyName, password;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {

        driver = getBrowserDriver(browserName);

        homePage = PageGenerators.getUserHomePage(driver);

        firstName = "Automation";
        lastName = "Testing";
        emailAddress = "autotest" + generateRandomNumber() + "@gmail.com";
        companyName = "Continental";
        password = "12345678";
    }

    @Test
    public void User_01_Register() {
        log.info("User_01_Register - STEP 01: Open Register Page");
        registerPage = homePage.openToRegisterPage();

        log.info("User_01_Register - STEP 02: Click to Male radio button");
        registerPage.clickToMaleRadio();

        log.info("User_01_Register - STEP 03: Enter to First Name textbox with value is '" + firstName + "'");
        registerPage.enterToFirstNameTextbox(firstName);

        log.info("User_01_Register - STEP 04: Enter to Last Name textbox with value is '" + lastName + "'");
        registerPage.enterToLastNameTextbox(lastName);

        log.info("User_01_Register - STEP 05: Enter to Email textbox with value is '" + emailAddress + "'");
        registerPage.enterToEmailTextbox(emailAddress);

        log.info("User_01_Register - STEP 06: Enter to Company textbox with value is '" + companyName + "'");
        registerPage.enterToCompanyTextbox(companyName);

        log.info("User_01_Register - STEP 07: Enter to Password textbox with value is '" + password + "'");
        registerPage.enterToPasswordTextbox(password);

        log.info("User_01_Register - STEP 08: Enter to Confirm Password textbox with value is '" + password + "'");
        registerPage.enterToConfirmPasswordTextbox(password);

        log.info("User_01_Register - STEP 09: Click to Register button");
        registerPage.clickToRegisterButton();

        log.info("User_01_Register - STEP 10: Click to Log Out link");
        homePage = registerPage.clickToLogOutLink();
    }

    @Test
    public void User_02_Login() {
        log.info("User_02_Login - STEP 01: Open Login Page");
        loginPage = homePage.openLoginPage();

        log.info("User_02_Login - STEP 02: Login with email '" + emailAddress + "'" + " and password '" + password + "'");
        homePage = loginPage.loginToSystem(emailAddress, password);
    }

    @Test
    public void User_03_MyAccount() {
        log.info("User_03_MyAccount - STEP 01: Open My Account");
        customerInfoPage = homePage.openCustomerInfoPage();

        log.info("User_03_MyAccount - STEP 02: Verify Gender is Male");
        verifyTrue(customerInfoPage.isGenderMaleIsSelected());

        log.info("User_03_MyAccount - STEP 03: Verify First Name is correct");
        verifyEqual(customerInfoPage.getFirstNameTextboxValue(), firstName);

        log.info("User_03_MyAccount - STEP 04: Verify Last Name is correct");
        verifyEqual(customerInfoPage.getLastNameTextboxValue(), lastName);

        log.info("User_03_MyAccount - STEP 05: Verify Email is correct");
        verifyEqual(customerInfoPage.getEmailTextboxValue(), emailAddress);

        log.info("User_03_MyAccount - STEP 06: Verify Company is correct");
        verifyEqual(customerInfoPage.getCompanyTextboxValue(), companyName);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}