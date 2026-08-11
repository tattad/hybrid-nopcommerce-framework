package com.testcases.nopcommerce.user;

import com.testcases.nopcommerce.common.Login;
import actions.commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import actions.pageObjects.nopCommerce.PageGenerators;
import actions.pageObjects.nopCommerce.user.UserCustomerInfoPO;
import actions.pageObjects.nopCommerce.user.UserHomePO;
import actions.pageObjects.nopCommerce.user.UserLoginPO;
import actions.pageObjects.nopCommerce.user.UserRegisterPO;

public class Level_20_Share_State extends BaseTest {

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
        homePage = PageGenerators.getUserHomePage(driver);

        firstName = "Automation";
        lastName = "Testing";
        emailAddress = "autotest" + generateRandomNumber() + "@gmail.com";
        companyName = "Continental";
        password = "12345678";

        //Pre-condition: login by cookie
        homePage.setCookies(driver, Login.nopCommerceCookie);
        homePage.refreshCurrentPage(driver);

        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    @Test
    public void User_01_MyAccount() {
        customerInfoPage = homePage.openCustomerInfoPage();
        Assert.assertTrue(customerInfoPage.isRadioByIDIsSelected(driver, "gender-male"));
    }

    @Test
    public void User_02_Order() {

    }

    @Test
    public void User_03_Payment() {

    }

    @AfterClass
    public void afterClass() {
        closeBrowserDriver();
    }
}