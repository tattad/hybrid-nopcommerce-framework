package testcases.com.jquery;

import actions.commons.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import actions.pageObjects.jquery.HomePO;
import actions.pageObjects.jquery.PageGenerator;

import java.lang.reflect.Method;

@Feature("Allure Report")
public class Level_16_AllureReport extends BaseTest {
    String browserName;
    private WebDriver driver;
    private HomePO homePage;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);
        homePage = PageGenerator.getHomePage(driver);
        this.browserName = browserName;
    }

    @Description("Verify that user can switch to any page by clicking on page number")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void Table_01_Switch_Page(Method method) {
        homePage.openPageByNumber("15");
        verifyTrue(homePage.isPageNumberActived("15"));

        homePage.openPageByNumber("5");
        verifyTrue(homePage.isPageNumberActived("5"));

        homePage.openPageByNumber("12");
        verifyTrue(homePage.isPageNumberActived("13"));
        homePage.refreshCurrentPage(driver);
    }

    @Description("Search for rows based on column values")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void Table_02_Search(Method method) {
        homePage.enterToTextboxByHeaderName("Country", "Algeria");
        homePage.sleepInSecond(1);

        verifyTrue(homePage.isRowDataValueDisplayed("283821", "Algeria", "295140", "578961"));
        homePage.refreshCurrentPage(driver);

        homePage.enterToTextboxByHeaderName("Males", "12599691");

        verifyTrue(homePage.isRowDataValueDisplayed("12253515", "AFRICA", "12599691", "24853148"));
        homePage.sleepInSecond(1);
        homePage.refreshCurrentPage(driver);

        homePage.enterToTextboxByHeaderName("Females", "764956");

        verifyTrue(homePage.isRowDataValueDisplayed("764956", "Arab Rep of Egypt", "802948", "1567904"));
        homePage.sleepInSecond(1);
        homePage.refreshCurrentPage(driver);
    }

    @Description("Delete and edit a row in the table")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void Table_03_Delete_Edit(Method method) {
        homePage.enterToTextboxByHeaderName("Country", "Arab Rep of Egypt");
        homePage.sleepInSecond(2);

        homePage.editRowByCountryName("Arab Rep of Egypt");

        homePage.editRecordDetails("females", "2");

        homePage.enterToTextboxByHeaderName("Country", "Arab Rep of Egypt");

        Assert.assertTrue(homePage.isRowDataValueDisplayed("2", "Arab Rep of Egypt", "802948", "1567904"));
        homePage.refreshCurrentPage(driver);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}