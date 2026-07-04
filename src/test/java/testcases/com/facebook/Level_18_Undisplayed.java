package testcases.com.facebook;

import actions.commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import actions.pageObjects.facebook.LoginPO;
import actions.pageObjects.facebook.PageGenerator;

public class Level_18_Undisplayed extends BaseTest {
    private WebDriver driver;
    private LoginPO loginPage;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);
        loginPage = PageGenerator.getLoginPage(driver);

        loginPage.clickToNewAccountButton();
    }

    @Test
    public void TC_01_Element_Undisplayed() {
        loginPage.enterToEmailAddressTextbox("a@gmail.com");

        //Case 1 - Verify Cofirm Email textbox is displayed (visible)
        Assert.assertTrue(loginPage.isConfirmEmailTextboxDisplayed());

        //Case 2 - Verify Cofirm Email textbox is not displayed (presence)
        loginPage.enterToEmailAddressTextbox("");
        Assert.assertFalse(loginPage.isConfirmEmailTextboxDisplayed());

        //Case 3 - Verify Confirm email textbox is not displayed (non-presence)
        loginPage.clickToCloseIcon();
        Assert.assertTrue(loginPage.isConfirmEmailTextboxUndisplayed());
    }

    @Test
    public void TC_02_Element_Undisplayed() {
        loginPage.enterToEmailAddressTextbox("a@gmail.com");

        //Case 1 - Verify Cofirm Email textbox is displayed (visible)
        Assert.assertFalse(loginPage.isConfirmEmailTextboxUndisplayed());

        //Case 2 - Verify Cofirm Email textbox is not displayed (presence)
        loginPage.enterToEmailAddressTextbox("");
        Assert.assertTrue(loginPage.isConfirmEmailTextboxUndisplayed());

        //Case 3 - Verify Confirm email textbox is not displayed (non-presence)
        loginPage.clickToCloseIcon();
        Assert.assertTrue(loginPage.isConfirmEmailTextboxUndisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}