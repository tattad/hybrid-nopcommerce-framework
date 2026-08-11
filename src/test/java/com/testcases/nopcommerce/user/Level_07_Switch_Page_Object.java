package com.testcases.nopcommerce.user;

import actions.commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import actions.pageObjects.nopCommerce.PageGenerators;
import actions.pageObjects.nopCommerce.user.*;

public class Level_07_Switch_Page_Object extends BaseTest {

    private WebDriver driver;
    private UserHomePO homePage;
    private UserRegisterPO registerPage;
    private UserLoginPO loginPage;
    private UserCustomerInfoPO customerInforPage;
    private UserAddressPO addressPage;
    private UserOrderPO orderPage;
    private UserRewardPointPO rewardPointPage;
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

        registerPage.clickToMaleRadio();
        registerPage.enterToFirstNameTextbox(firstName);
        registerPage.enterToLastNameTextbox(lastName);
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToCompanyTextbox(companyName);
        registerPage.enterToPasswordTextbox(password);
        registerPage.enterToConfirmPasswordTextbox(password);
        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
    }

    @Test
    public void User_02_Login() {
        loginPage = registerPage.openLoginPage();

        homePage = loginPage.loginToSystem(emailAddress, password);

        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    @Test
    public void User_03_MyAccount() {
        customerInforPage = homePage.openCustomerInfoPage();

        Assert.assertTrue(customerInforPage.isGenderMaleIsSelected());
        Assert.assertEquals(customerInforPage.getFirstNameTextboxValue(), firstName);
        Assert.assertEquals(customerInforPage.getLastNameTextboxValue(), lastName);
        Assert.assertEquals(customerInforPage.getEmailTextboxValue(), emailAddress);
        Assert.assertEquals(customerInforPage.getCompanyTextboxValue(), companyName);
    }

    @Test
    public void User_04_Switch_Page() {
        // Customer Infor -> Address
        addressPage = customerInforPage.openAddressPage();

        // Address -> Reward Point
        rewardPointPage = addressPage.openRewardPointPage();

        // Reward Point -> Order
        orderPage = rewardPointPage.openOrderPage();

         // Order -> Address
        addressPage = orderPage.openAddressPage();

        // Address -> Customer Info
        customerInforPage = addressPage.openCustomerInforPage();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}