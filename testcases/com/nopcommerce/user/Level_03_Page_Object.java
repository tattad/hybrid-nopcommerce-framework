package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.UserCustomerInfoPO;
import pageObjects.nopCommerce.user.UserHomePO;
import pageObjects.nopCommerce.user.UserLoginPO;
import pageObjects.nopCommerce.user.UserRegisterPO;

import java.time.Duration;

public class Level_03_Page_Object extends BaseTest {
    //Declare variables
    private WebDriver driver;
    private UserHomePO homePage;
    private UserRegisterPO registerPage;
    private UserLoginPO loginPage;
    private UserCustomerInfoPO customerInforPage;
    private String firstName, lastName, emailAddress, companyName, password;

    //Pre-condition
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        //Page đó được sinh ra và bắt đầu làm những action của page đó
        homePage = new UserHomePO(driver);

        firstName = "Automation";
        lastName = "Testing";
        emailAddress = "autotest" + generateRandomNumber() + "@gmail.com";
        companyName = "Continental";
        password = "12345678";
    }

    //Testcases
    @Test
    public void User_01_Register() {
        homePage.openToRegisterPage();

        //Từ Home Page qua Register Page
        //Page đó được sinh ra và bắt đầu làm những action của page đó
        registerPage = new UserRegisterPO(driver);

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
        registerPage.openLoginPage();

        //Từ Register Page qua Login Page
        //Page đó được sinh ra và bắt đầu làm những action của page đó
        loginPage = new UserLoginPO(driver);

        loginPage.loginToSystem(emailAddress, password);

        //Từ Login Page qua Home Page
        //Page đó được sinh ra và bắt đầu làm những action của page đó
        homePage = new UserHomePO(driver);

        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    @Test
    public void User_03_MyAccount() {
        homePage.openCustomerInfoPage();

        //Từ Home Page qua Customer Info Page
        //Page đó được sinh ra và bắt đầu làm những action của page đó
        customerInforPage = new UserCustomerInfoPO(driver);

        Assert.assertTrue(customerInforPage.isGenderMaleIsSelected());

        Assert.assertEquals(customerInforPage.getFirstNameTextboxValue(), firstName);
        Assert.assertEquals(customerInforPage.getLastNameTextboxValue(), lastName);
        Assert.assertEquals(customerInforPage.getEmailTextboxValue(), emailAddress);
        Assert.assertEquals(customerInforPage.getCompanyTextboxValue(), companyName);
    }

    //Post-condition
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}