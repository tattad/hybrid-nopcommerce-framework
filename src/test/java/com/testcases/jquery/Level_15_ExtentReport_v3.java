package com.testcases.jquery;//package com.jquery;
//
//import commons.BaseTest;
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
//import pageObjects.jquery.HomePO;
//import pageObjects.jquery.PageGenerator;
//
//import java.lang.reflect.Method;
//
//public class Level_15_ExtentReport_v3 extends BaseTest {
//
//    private WebDriver driver;
//    private HomePO homePage;
//
//    @Parameters({"browser", "url"})
//    @BeforeClass
//    public void beforeClass(String browserName, String url) {
//        driver = getBrowserDriver(browserName, url);
//        homePage = PageGenerator.getHomePage(driver);
//    }
//
//    @Test
//    public void Table_01_Switch_Page(Method method) {
//        homePage.openPageByNumber("15");
//        verifyTrue(homePage.isPageNumberActived("15"));
//
//        homePage.openPageByNumber("5");
//        verifyTrue(homePage.isPageNumberActived("5"));
//
//        homePage.openPageByNumber("12");
//        verifyTrue(homePage.isPageNumberActived("13"));
//        homePage.refreshCurrentPage(driver);
//    }
//
//    @Test
//    public void Table_02_Search(Method method) {
//        homePage.enterToTextboxByHeaderName("Country", "Algeria");
//        homePage.sleepInSecond(1);
//
//        verifyTrue(homePage.isRowDataValueDisplayed("283821", "Algeria", "295140", "578961"));
//        homePage.refreshCurrentPage(driver);
//
//        homePage.enterToTextboxByHeaderName("Males", "12599691");
//
//        verifyTrue(homePage.isRowDataValueDisplayed("12253515", "AFRICA", "12599691", "24853148"));
//        homePage.sleepInSecond(1);
//        homePage.refreshCurrentPage(driver);
//
//        homePage.enterToTextboxByHeaderName("Females", "764956");
//
//        verifyTrue(homePage.isRowDataValueDisplayed("764956", "Arab Rep of Egypt", "802948", "1567904"));
//        homePage.sleepInSecond(1);
//        homePage.refreshCurrentPage(driver);
//    }
//
//    @Test
//    public void Table_03_Delete_Edit(Method method) {
//        homePage.enterToTextboxByHeaderName("Country", "Arab Rep of Egypt");
//        homePage.sleepInSecond(2);
//
//        homePage.editRowByCountryName("Arab Rep of Egypt");
//
//        homePage.editRecordDetails("females", "2");
//
//        homePage.enterToTextboxByHeaderName("Country", "Arab Rep of Egypt");
//
//        Assert.assertTrue(homePage.isRowDataValueDisplayed("2", "Arab Rep of Egypt", "802948", "1567904"));
//        homePage.refreshCurrentPage(driver);
//    }
//
//    @AfterClass
//    public void afterClass() {
//        driver.quit();
//    }
//}