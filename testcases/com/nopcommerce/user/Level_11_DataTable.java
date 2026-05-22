package com.nopcommerce.user;

import commons.BasePage;
import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jquery.HomePO;
import pageObjects.jquery.PageGenerator;

public class Level_11_DataTable extends BaseTest {

    private WebDriver driver;
    private HomePO homePage;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);
        homePage = PageGenerator.getHomePage(driver);
    }

    //    @Test
    public void Table_01_Switch_Page() {
        //Navigate to any page (paging)
        homePage.openPageByNumber("15");
        Assert.assertTrue(homePage.isPageNumberActived("15"));

        homePage.openPageByNumber("5");
        Assert.assertTrue(homePage.isPageNumberActived("5"));

        homePage.openPageByNumber("12");
        Assert.assertTrue(homePage.isPageNumberActived("12"));
    }

    //    @Test
    public void Table_02_Search() {
        //Enter value to header textbox and search -> verify data in first row
        homePage.enterToTextboxByHeaderName("Country", "Algeria");
        homePage.sleepInSecond(2);
        Assert.assertTrue(homePage.isRowDataValueDisplayed("283821", "Algeria", "295140", "578961"));
        homePage.refreshCurrentPage(driver);

        homePage.enterToTextboxByHeaderName("Males", "12599691");
        Assert.assertTrue(homePage.isRowDataValueDisplayed("12253515", "AFRICA", "12599691", "24853148"));
        homePage.sleepInSecond(2);
        homePage.refreshCurrentPage(driver);

        homePage.enterToTextboxByHeaderName("Females", "764956");
        Assert.assertTrue(homePage.isRowDataValueDisplayed("764956", "Arab Rep of Egypt", "802948", "1567904"));
        homePage.sleepInSecond(2);
    }

    @Test
    public void Table_03_Delete_Edit() {
//        //Enter value to header textbox and search
//        homePage.enterToTextboxByHeaderName("Country", "Afghanistan");
//        homePage.sleepInSecond(2);
//
//        //Click Remove button
//        homePage.removeRowByCountryName("Afghanistan");
//        homePage.refreshCurrentPage(driver);
//
//        homePage.enterToTextboxByHeaderName("Country", "Angola");
//        homePage.sleepInSecond(2);
//        homePage.removeRowByCountryName("Angola");
//        homePage.refreshCurrentPage(driver);

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