//package com.jquery;
//
//import com.aventstack.extentreports.Status;
//import commons.BaseTest;
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
//import pageObjects.jquery.HomePO;
//import pageObjects.jquery.PageGenerator;
//import reportConfigs.ExtentManagerV5;
//
//import java.lang.reflect.Method;
//
//public class Level_15_ExtentReport_v5 extends BaseTest {
//    String browserName;
//    private WebDriver driver;
//    private HomePO homePage;
//
//    @Parameters({"browser", "url"})
//    @BeforeClass
//    public void beforeClass(String browserName, String url) {
//        driver = getBrowserDriver(browserName, url);
//        homePage = PageGenerator.getHomePage(driver);
//        this.browserName = browserName;
//    }
//
//    @Test
//    public void Table_01_Switch_Page(Method method) {
//        ExtentManagerV5.startTest(method.getName() + " - Run on" + this.browserName.toUpperCase(), "Table_01_Switch_Page");
//        ExtentManagerV5.getTest().log(Status.INFO, "Table_01_Switch_Page - STEP 01: Switch to Page 15");
//        homePage.openPageByNumber("15");
//        verifyTrue(homePage.isPageNumberActived("15"));
//
//        ExtentManagerV5.getTest().log(Status.INFO, "Table_01_Switch_Page - STEP 02: Switch to Page 5");
//        homePage.openPageByNumber("5");
//        verifyTrue(homePage.isPageNumberActived("5"));
//
//        ExtentManagerV5.getTest().log(Status.INFO, "Table_01_Switch_Page - STEP 03: Switch to Page 12");
//        homePage.openPageByNumber("12");
//        verifyTrue(homePage.isPageNumberActived("13"));
//        homePage.refreshCurrentPage(driver);
//    }
//
//    @Test
//    public void Table_02_Search(Method method) {
//        ExtentManagerV5.startTest(method.getName() + " - Run on" + this.browserName.toUpperCase(), "Table_02_Search");
//        ExtentManagerV5.getTest().log(Status.INFO, "Table_02_Search - STEP 01: Search by Country");
//        homePage.enterToTextboxByHeaderName("Country", "Algeria");
//        homePage.sleepInSecond(1);
//
//        ExtentManagerV5.getTest().log(Status.INFO, "Table_02_Search - STEP 02: Check result");
//        verifyTrue(homePage.isRowDataValueDisplayed("283821", "Algeria", "295140", "578961"));
//        homePage.refreshCurrentPage(driver);
//
//        ExtentManagerV5.getTest().log(Status.INFO, "Table_02_Search - STEP 03: Search by Males");
//        homePage.enterToTextboxByHeaderName("Males", "12599691");
//
//        ExtentManagerV5.getTest().log(Status.INFO, "Table_02_Search - STEP 04: Check result");
//        verifyTrue(homePage.isRowDataValueDisplayed("12253515", "AFRICA", "12599691", "24853148"));
//        homePage.sleepInSecond(1);
//        homePage.refreshCurrentPage(driver);
//
//        ExtentManagerV5.getTest().log(Status.INFO, "Table_02_Search - STEP 05: Search by Females");
//        homePage.enterToTextboxByHeaderName("Females", "764956");
//
//        ExtentManagerV5.getTest().log(Status.INFO, "Table_02_Search - STEP 06: Check result");
//        verifyTrue(homePage.isRowDataValueDisplayed("764956", "Arab Rep of Egypt", "802948", "1567904"));
//        homePage.sleepInSecond(1);
//        homePage.refreshCurrentPage(driver);
//    }
//
//    @Test
//    public void Table_03_Delete_Edit(Method method) {
//        ExtentManagerV5.startTest(method.getName() + " - Run on" + this.browserName.toUpperCase(), "Table_03_Delete_Edit");
//
//        ExtentManagerV5.getTest().log(Status.INFO, "Table_03_Delete_Edit - STEP 01: Search by Country");
//        homePage.enterToTextboxByHeaderName("Country", "Arab Rep of Egypt");
//        homePage.sleepInSecond(2);
//
//        ExtentManagerV5.getTest().log(Status.INFO, "Table_03_Delete_Edit - STEP 02: Open country details");
//        homePage.editRowByCountryName("Arab Rep of Egypt");
//
//        ExtentManagerV5.getTest().log(Status.INFO, "Table_03_Delete_Edit - STEP 03: Edit females field");
//        homePage.editRecordDetails("females", "2");
//
//        ExtentManagerV5.getTest().log(Status.INFO, "Table_03_Delete_Edit - STEP 04: Search by Country");
//        homePage.enterToTextboxByHeaderName("Country", "Arab Rep of Egypt");
//
//        ExtentManagerV5.getTest().log(Status.INFO, "Table_03_Delete_Edit - STEP 05: Verify edited data");
//        Assert.assertTrue(homePage.isRowDataValueDisplayed("2", "Arab Rep of Egypt", "802948", "1567904"));
//        homePage.refreshCurrentPage(driver);
//    }
//
//    @AfterClass
//    public void afterClass() {
//        driver.quit();
//    }
//}