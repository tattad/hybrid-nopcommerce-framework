package testcases.com.nopcommerce.user;

import actions.commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import actions.pageObjects.nopCommerce.PageGenerators;
import actions.pageObjects.nopCommerce.user.*;

public class Level_10_Dynamic_Locator extends BaseTest {

    private WebDriver driver;
    private UserHomePO homePage;
    private UserRegisterPO registerPage;
    private UserLoginPO loginPage;
    private UserCustomerInfoPO customerInfoPage;
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

    @Test
    public void User_04_Dynamic_Page() {
        // Customer Infor -> Address
        addressPage = (UserAddressPO) customerInfoPage.openSidebarLinkPageName("Addresses");

        // Address -> Reward Point
        rewardPointPage = (UserRewardPointPO) addressPage.openSidebarLinkPageName("Reward Points");

        // Reward Point -> Order
        orderPage = (UserOrderPO) rewardPointPage.openSidebarLinkPageName("Orders");

        // Order -> Address
        addressPage = (UserAddressPO) orderPage.openSidebarLinkPageName("Addresses");

        // Address -> Customer Info
        customerInfoPage = (UserCustomerInfoPO) addressPage.openSidebarLinkPageName("Customer Info");
    }

    @Test
    public void User_05_Dynamic_Page() {
        // Address -> Reward Point
        addressPage.openSidebarLinkPageName("Reward Points");
        rewardPointPage = PageGenerators.getUserRewardPointPage(driver);

        // Reward Point -> Order
        rewardPointPage.openSidebarLinkPageName("Orders");
        orderPage = PageGenerators.getUserOrderPage(driver);

        // Order -> Address
        orderPage.openSidebarLinkPageName("Addresses");
        addressPage = PageGenerators.getUserAddressPage(driver);

        // Address -> Customer Info
        addressPage.openSidebarLinkPageName("Customer Info");
        customerInfoPage = PageGenerators.getUserCustomerPage(driver);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}