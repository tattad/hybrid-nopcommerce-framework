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

public class Level_13_Verify extends BaseTest {

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
        registerPage = homePage.openToRegisterPage();

        //Assert 01 => Failed
        verifyEqual(registerPage.getRegisterPageTitle(), "REGISTER");

        registerPage.clickToMaleRadio();
        registerPage.enterToFirstNameTextbox(firstName);
        registerPage.enterToLastNameTextbox(lastName);
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToCompanyTextbox(companyName);
        registerPage.enterToPasswordTextbox(password);
        registerPage.enterToConfirmPasswordTextbox(password);
        registerPage.clickToRegisterButton();

        //Assert 02
        verifyEqual(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        homePage = registerPage.clickToLogOutLink();
    }

    @Test
    public void User_02_Login() {
        loginPage = homePage.openLoginPage();

        homePage = loginPage.loginToSystem(emailAddress, password);

        verifyTrue(homePage.isMyAccountLinkDisplayed());
    }

    @Test
    public void User_03_MyAccount() {
        customerInfoPage = homePage.openCustomerInfoPage();

        verifyTrue(customerInfoPage.isGenderMaleIsSelected());
        verifyEqual(customerInfoPage.getFirstNameTextboxValue(), firstName);
        verifyEqual(customerInfoPage.getLastNameTextboxValue(), lastName);
        verifyEqual(customerInfoPage.getEmailTextboxValue(), emailAddress);
        verifyEqual(customerInfoPage.getCompanyTextboxValue(), companyName);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}