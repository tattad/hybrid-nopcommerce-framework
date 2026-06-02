package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.PageGenerator;
import pageObjects.nopCommerce.user.UserCustomerInfoPO;
import pageObjects.nopCommerce.user.UserHomePO;
import pageObjects.nopCommerce.user.UserLoginPO;
import pageObjects.nopCommerce.user.UserRegisterPO;

public class Level_19_Pattern_Object extends BaseTest {

    private WebDriver driver;
    private UserHomePO homePage;
    private UserRegisterPO registerPage;
    private UserLoginPO loginPage;
    private UserCustomerInfoPO customerInfoPage;
    private String firstName, lastName, emailAddress, companyName, password;

    @Parameters({"browser", "userUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {

        driver = getBrowserDriver(browserName);
        homePage = PageGenerator.getUserHomePage(driver);

        firstName = "Automation";
        lastName = "Testing";
        emailAddress = "autotest" + generateRandomNumber() + "@gmail.com";
        companyName = "Continental";
        password = "12345678";
    }

    @Test
    public void User_01_Register() {
        registerPage = homePage.openToRegisterPage();

        registerPage.clickToRadioByID(driver, "gender-male");

        registerPage.enterToTextBoxByID(driver, "FirstName", firstName);
        registerPage.enterToTextBoxByID(driver, "LastName", lastName);
        registerPage.enterToTextBoxByID(driver, "Email", emailAddress);
        registerPage.enterToTextBoxByID(driver, "Company", companyName);

        registerPage.clickToCheckboxByID(driver, "Newsletter");

        registerPage.enterToTextBoxByID(driver, "Password", password);
        registerPage.enterToTextBoxByID(driver, "ConfirmPassword", password);

        registerPage.clickToButtonByText(driver, "Register");

        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
    }

    @Test
    public void User_02_Login() {
        homePage = registerPage.clickToLogOutLink();
        loginPage = homePage.openLoginPage();

        loginPage.enterToTextBoxByID(driver, "Email", emailAddress);
        loginPage.enterToTextBoxByID(driver, "Password", password);

        loginPage.clickToButtonByText(driver, "Log in");
        homePage = PageGenerator.getUserHomePage(driver);

        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    @Test
    public void User_03_MyAccount() {
        customerInfoPage = homePage.openCustomerInfoPage();

        verifyTrue(customerInfoPage.isGenderMaleIsSelected());

        Assert.assertTrue(customerInfoPage.isRadioByIDIsSelected(driver, "gender-male"));

        verifyEqual(customerInfoPage.getTextboxValueByID(driver, "FirstName"), firstName);
        verifyEqual(customerInfoPage.getTextboxValueByID(driver, "LastName"), lastName);
        verifyEqual(customerInfoPage.getTextboxValueByID(driver, "Email"), emailAddress);
        verifyEqual(customerInfoPage.getTextboxValueByID(driver, "Company"), companyName);

        Assert.assertTrue(customerInfoPage.isCheckboxByIDIsSelected(driver, "Newsletter"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}