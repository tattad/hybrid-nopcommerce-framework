package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.CustomerInforPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

import java.time.Duration;

public class Level_03_Page_Object {
    //Declare variables
    private WebDriver driver;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;
    private CustomerInforPageObject customerInforPage;

    //Pre-condition
    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        //Page đó được sinh ra và bắt đầu làm những action của page đó
        homePage = new HomePageObject();
    }

    //Testcases
    @Test
    public void User_01_Register() {
        homePage.clickToRegisterLink();

        //Từ Home Page qua Register Page
        //Page đó được sinh ra và bắt đầu làm những action của page đó
        registerPage = new RegisterPageObject();

        registerPage.clickToMaleRadio();
        registerPage.enterToFirstNameTextbox();
        registerPage.enterToLastNameTextbox();
        registerPage.enterToEmailTextbox();
        registerPage.enterToCompanyTextbox();
        registerPage.enterToPasswordTextbox();
        registerPage.enterToConfirmPasswordTextbox();
        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
    }

    @Test
    public void User_02_Login() {
        registerPage.clickToLoginButton();

        //Từ Register Page qua Login Page
        //Page đó được sinh ra và bắt đầu làm những action của page đó
        loginPage = new LoginPageObject();

        loginPage.enterToEmailTextbox();
        loginPage.enterToPasswordTextbox();
        loginPage.clickToLoginButton();

        //Từ Login Page qua Home Page
        //Page đó được sinh ra và bắt đầu làm những action của page đó
        homePage = new HomePageObject();

        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    @Test
    public void User_03_MyAccount() {
        homePage.clickToMyAccountLink();

        //Từ Home Page qua Customer Info Page
        //Page đó được sinh ra và bắt đầu làm những action của page đó
        customerInforPage = new CustomerInforPageObject();

        Assert.assertTrue(customerInforPage.isGenderMaleIsSelected());

        Assert.assertEquals(customerInforPage.getFirstNameTextboxValue(),"");
        Assert.assertEquals(customerInforPage.getLastNameTextboxValue(), "");
        Assert.assertEquals(customerInforPage.getEmailTextboxValue(), "");
    }

    //Post-condition
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
