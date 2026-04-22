package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.CustomerInforPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

import java.time.Duration;

public class Level_03_Page_Object extends BaseTest {
    //Declare variables
    private WebDriver driver;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;
    private CustomerInforPageObject customerInforPage;
    private String firstName, lastName, emailAddress, companyName, password;

    //Pre-condition
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        //Page đó được sinh ra và bắt đầu làm những action của page đó
        homePage = new HomePageObject(driver);

        firstName = "Automation";
        lastName = "Testing";
        emailAddress = "autotest" + generateRandomNumber() + "@gmail.com";
        companyName = "Continental";
        password = "12345678";
    }

    //Testcases
    @Test
    public void User_01_Register() {
        homePage.clickToRegisterLink();

        //Từ Home Page qua Register Page
        //Page đó được sinh ra và bắt đầu làm những action của page đó
        registerPage = new RegisterPageObject(driver);

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
        registerPage.clickToLoginButton();

        //Từ Register Page qua Login Page
        //Page đó được sinh ra và bắt đầu làm những action của page đó
        loginPage = new LoginPageObject(driver);

        loginPage.enterToEmailTextbox(emailAddress);
        loginPage.enterToPasswordTextbox(password);
        loginPage.clickToLoginButton();

        //Từ Login Page qua Home Page
        //Page đó được sinh ra và bắt đầu làm những action của page đó
        homePage = new HomePageObject(driver);

        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    @Test
    public void User_03_MyAccount() {
        homePage.clickToMyAccountLink();

        //Từ Home Page qua Customer Info Page
        //Page đó được sinh ra và bắt đầu làm những action của page đó
        customerInforPage = new CustomerInforPageObject(driver);

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