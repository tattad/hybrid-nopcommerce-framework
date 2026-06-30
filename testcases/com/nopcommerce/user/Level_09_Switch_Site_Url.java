package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.PageGenerators;
import pageObjects.nopCommerce.admin.AdminDashboardPO;
import pageObjects.nopCommerce.admin.AdminLoginPO;
import pageObjects.nopCommerce.user.UserCustomerInfoPO;
import pageObjects.nopCommerce.user.UserHomePO;
import pageObjects.nopCommerce.user.UserLoginPO;
import pageObjects.nopCommerce.user.UserRegisterPO;

public class Level_09_Switch_Site_Url extends BaseTest {

    private WebDriver driver;
    private UserHomePO userHomePage;
    private UserRegisterPO userRegisterPage;
    private UserLoginPO userLoginPage;
    private UserCustomerInfoPO userCustomerInfoPage;
    private AdminLoginPO adminLoginPage;
    private AdminDashboardPO adminDashboardPage;
    private String firstName, lastName, emailAddress, companyName, password;
    private String userUrlValue, adminUrlValue;
    private String adminEmailAddress, adminPassword;

    @Parameters({"browser", "userUrl", "adminUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String userUrl, String adminUrl) {
        userUrlValue = userUrl;
        adminUrlValue = adminUrl;

        driver = getBrowserDriver(browserName, userUrlValue);
        userHomePage = PageGenerators.getUserHomePage(driver);

        firstName = "Automation";
        lastName = "Testing";
        emailAddress = "autotest" + generateRandomNumber() + "@gmail.com";
        companyName = "Continental";
        password = "12345678";

        adminEmailAddress = "admin@yourstore.com";
        adminPassword = "admin";

        userRegisterPage = userHomePage.openToRegisterPage();

        userRegisterPage.clickToMaleRadio();
        userRegisterPage.enterToFirstNameTextbox(firstName);
        userRegisterPage.enterToLastNameTextbox(lastName);
        userRegisterPage.enterToEmailTextbox(emailAddress);
        userRegisterPage.enterToCompanyTextbox(companyName);
        userRegisterPage.enterToPasswordTextbox(password);
        userRegisterPage.enterToConfirmPasswordTextbox(password);
        userRegisterPage.clickToRegisterButton();

        Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");

        userHomePage = userRegisterPage.clickToLogOutLink();
    }

    @Test
    public void Role_01_User_Site_To_Admin_Site() {
        userLoginPage = userHomePage.openLoginPage();

        userHomePage = userLoginPage.loginToSystem(emailAddress, password);

        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());

        //Step để order 1 product nào đó
        //....
        //Qua trang admin để verify/ approve cái order vs quyền admin
        userHomePage.openAdminSite(driver, adminUrlValue);
        adminLoginPage = PageGenerators.getAdminLoginPage(driver);

        //Login vào trang Admin
        adminLoginPage.enterToEmailTextbox(adminEmailAddress);
        adminLoginPage.enterToPasswordTextbox(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();
    }

    @Test
    public void Role_02_Admin_Site_To_User_Site() {
        //Vào trang Order/ Customer...
        //...
        adminDashboardPage.openPageUrl(driver, userUrlValue);
        userHomePage = PageGenerators.getUserHomePage(driver);

        //Action các step tiếp theo
        //...
        userCustomerInfoPage = userHomePage.openCustomerInfoPage();

        Assert.assertTrue(userCustomerInfoPage.isGenderMaleIsSelected());
        Assert.assertEquals(userCustomerInfoPage.getFirstNameTextboxValue(), firstName);
        Assert.assertEquals(userCustomerInfoPage.getLastNameTextboxValue(), lastName);
        Assert.assertEquals(userCustomerInfoPage.getEmailTextboxValue(), emailAddress);
        Assert.assertEquals(userCustomerInfoPage.getCompanyTextboxValue(), companyName);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}