package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.PageGenerators;
import pageObjects.nopCommerce.user.*;

public class Level_13_Assert extends BaseTest {

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
        Assert.assertEquals(registerPage.getRegisterPageTitle(), "REGISTER");

        registerPage.clickToMaleRadio();
        registerPage.enterToFirstNameTextbox(firstName);
        registerPage.enterToLastNameTextbox(lastName);
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToCompanyTextbox(companyName);
        registerPage.enterToPasswordTextbox(password);
        registerPage.enterToConfirmPasswordTextbox(password);
        registerPage.clickToRegisterButton();

        //Assert 02
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        homePage = registerPage.clickToLogOutLink();
    }

    @Test
    public void User_02_Login() {
        loginPage = homePage.openLoginPage();

        homePage = loginPage.loginToSystem(emailAddress, password);

        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    @Test
    public void User_03_MyAccount() {
        customerInfoPage = homePage.openCustomerInfoPage();

        Assert.assertTrue(customerInfoPage.isGenderMaleIsSelected());
        Assert.assertEquals(customerInfoPage.getFirstNameTextboxValue(), firstName);
        Assert.assertEquals(customerInfoPage.getLastNameTextboxValue(), lastName);
        Assert.assertEquals(customerInfoPage.getEmailTextboxValue(), emailAddress);
        Assert.assertEquals(customerInfoPage.getCompanyTextboxValue(), companyName);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}