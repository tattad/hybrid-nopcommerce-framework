package com.orangehrm;

import actions.commons.BaseTest;
import actions.pageObjects.orangehrm.DashboardPO;
import actions.pageObjects.orangehrm.LoginPO;
import actions.pageObjects.orangehrm.PageGenerator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Level_23_Browser_Config extends BaseTest {
    private WebDriver driver;
    private LoginPO loginPage;
    private DashboardPO dashboardPage;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browser, String url) {
        driver = getBrowserDriver(browser, url);

        loginPage = PageGenerator.getPage(LoginPO.class, driver);

        //Selenium 3.x
//        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//        desiredCapabilities.setBrowserName("Chrome");
//        desiredCapabilities.setVersion("144");
//        desiredCapabilities.setPlatform(Platform.WIN11);

        //Selenium 4.x
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setBrowserVersion("144");
//        chromeOptions.configureFromEnv();
//        chromeOptions.useWebView(true);

//        FirefoxOptions firefoxOptions = new FirefoxOptions();
//        firefoxOptions.setBrowserVersion("144");
//        firefoxOptions.configureFromEnv();
//        firefoxOptions.useWebView(true);

//        EdgeOptions edgeOptions = new EdgeOptions();
//        edgeOptions.useWebView(true);

        loginPage.enterToTextBoxByLabel(driver, "Username", "automationtada");
        loginPage.enterToTextBoxByLabel(driver, "Password", "D@ttran8895");
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPO.class, driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);
    }

    @Test
    public void Employee_01_NewEmployee_Enabled() {

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }
}
