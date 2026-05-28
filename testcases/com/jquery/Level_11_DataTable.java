package com.jquery;

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

    @Test
    public void Table_01_Switch_Page() {
        //Navigate to any page (paging)
        log.info("Table_01_Switch_Page - STEP 01: Switch to Page 15");
        homePage.openPageByNumber("15");
        verifyTrue(homePage.isPageNumberActived("15"));

        log.info("Table_01_Switch_Page - STEP 02: Switch to Page 5");
        homePage.openPageByNumber("5");
        verifyTrue(homePage.isPageNumberActived("5"));

        log.info("Table_01_Switch_Page - STEP 03: Switch to Page 12");
        homePage.openPageByNumber("12");
        verifyTrue(homePage.isPageNumberActived("13"));
        homePage.refreshCurrentPage(driver);
    }

    @Test
    public void Table_02_Search() {
        //Enter value to header textbox and search -> verify data in first row
        log.info("Table_02_Search - STEP 01: Search by Country");
        homePage.enterToTextboxByHeaderName("Country", "Algeria");
        homePage.sleepInSecond(1);

        log.info("Table_02_Search - STEP 02: Check result");
        verifyTrue(homePage.isRowDataValueDisplayed("283821", "Algeria", "295140", "578961"));
        homePage.refreshCurrentPage(driver);

        log.info("Table_02_Search - STEP 03: Search by Males");
        homePage.enterToTextboxByHeaderName("Males", "12599691");

        log.info("Table_02_Search - STEP 04: Check result");
        verifyTrue(homePage.isRowDataValueDisplayed("12253515", "AFRICA", "12599691", "24853148"));
        homePage.sleepInSecond(1);
        homePage.refreshCurrentPage(driver);

        log.info("Table_02_Search - STEP 05: Search by Females");
        homePage.enterToTextboxByHeaderName("Females", "764956");

        log.info("Table_02_Search - STEP 06: Check result");
        verifyTrue(homePage.isRowDataValueDisplayed("764956", "Arab Rep of Egypt", "802948", "1567904"));
        homePage.sleepInSecond(1);
        homePage.refreshCurrentPage(driver);
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

        log.info("Table_03_Delete_Edit - STEP 01: Search by Country");
        homePage.enterToTextboxByHeaderName("Country", "Arab Rep of Egypt");
        homePage.sleepInSecond(2);

        log.info("Table_03_Delete_Edit - STEP 02: Open country details");
        homePage.editRowByCountryName("Arab Rep of Egypt");

        log.info("Table_03_Delete_Edit - STEP 03: Edit females field");;
        homePage.editRecordDetails("females", "2");

        log.info("Table_03_Delete_Edit - STEP 04: Search by Country");
        homePage.enterToTextboxByHeaderName("Country", "Arab Rep of Egypt");

        log.info("Table_03_Delete_Edit - STEP 05: Verify edited data");
        Assert.assertTrue(homePage.isRowDataValueDisplayed("2", "Arab Rep of Egypt", "802948", "1567904"));
        homePage.refreshCurrentPage(driver);
    }

    //    @Test
    public void Table_04_Get_All_Value_Row_Or_Index() {
        homePage.getAllValueAtColumnName("Country");
        homePage.getAllValueAtColumnName("Females");
    }

    //    @Test
    public void Table_05_Action_By_Index() {
        homePage.openPageUrl(driver, "https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");
        homePage.clickToLoadDataButon();

        homePage.enterToTextboxByIndex("4", "Contact Person", "Automation");
        homePage.enterToTextboxByIndex("2", "Company", "Automation Company");

        homePage.selectToDropdownByIndex("6", "Country", "Hong Kong");
        homePage.selectToDropdownByIndex("8", "Country", "United Kingdom");

        homePage.checkToCheckboxByIndex("5", "NPO?", false);
        homePage.checkToCheckboxByIndex("6", "NPO?", true);

        homePage.clickToIconByIndex("8", "Move Up");
        homePage.clickToIconByIndex("6", "Remove");
        homePage.clickToIconByIndex("4", "Insert");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}