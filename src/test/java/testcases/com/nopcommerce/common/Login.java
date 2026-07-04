package testcases.com.nopcommerce.common;

import actions.commons.BaseTest;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import actions.pageObjects.nopCommerce.PageGenerators;
import actions.pageObjects.nopCommerce.user.UserCustomerInfoPO;
import actions.pageObjects.nopCommerce.user.UserHomePO;
import actions.pageObjects.nopCommerce.user.UserLoginPO;
import actions.pageObjects.nopCommerce.user.UserRegisterPO;

import java.util.Set;

public class Login extends BaseTest {

    private WebDriver driver;
    private UserHomePO homePage;
    private UserRegisterPO registerPage;
    private UserLoginPO loginPage;
    private UserCustomerInfoPO customerInfoPage;
    private String firstName, lastName, emailAddress, companyName, password;
    public static Set<Cookie> nopCommerceCookie;

    @Parameters({"browser", "userUrl"})
    @BeforeTest
    public void beforeTest(String browserName, String url) {
        driver = getBrowserDriver(browserName);
        homePage = PageGenerators.getUserHomePage(driver);

        //Data test
        firstName = "Automation";
        lastName = "Testing";
        emailAddress = "autotest" + generateRandomNumber() + "@gmail.com";
        companyName = "Continental";
        password = "12345678";

        //New user
        registerPage = homePage.openToRegisterPage();
        registerPage.clickToRadioByID(driver, "gender-male");
        registerPage.enterToTextBoxByID(driver, "FirstName", firstName);
        registerPage.enterToTextBoxByID(driver, "LastName", lastName);
        registerPage.enterToTextBoxByID(driver, "Email", emailAddress);
        registerPage.enterToTextBoxByID(driver, "Company", companyName);
//        registerPage.clickToCheckboxByID(driver, "Newsletter");
        registerPage.enterToTextBoxByID(driver, "Password", password);
        registerPage.enterToTextBoxByID(driver, "ConfirmPassword", password);
        registerPage.clickToButtonByText(driver, "Register");
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        //Login
        homePage = registerPage.clickToLogOutLink();
        loginPage = homePage.openLoginPage();
        loginPage.enterToTextBoxByID(driver, "Email", emailAddress);
        loginPage.enterToTextBoxByID(driver, "Password", password);
        loginPage.clickToButtonByText(driver, "Log in");
        homePage = PageGenerators.getUserHomePage(driver);
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

        //Get Cookies
        nopCommerceCookie = homePage.getAllCookies(driver);
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}