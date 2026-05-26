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

public class Level_12_Upload extends BaseTest {

    private WebDriver driver;
    private HomePO homePage;
    private String brightSide, darkSide, karik;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);
        homePage = PageGenerator.getHomePage(driver);

        brightSide = "brightside.jpg";
        darkSide = "darkside.jpg";
        karik = "karik.jpg";
    }

    @Test
    public void Table_01_Upload() {
        //Lấy ra được đường dẫn của file/ thư mục cho đúng
        //Tất cả các OS: Window/Mac/Linux đều chạy được
        //UPLOAD_PATH

        //Có thể upload 1 lần 1 file => dùng 1 hàm
//        homePage.uploadMultipleFiles(driver, karik);
//        homePage.sleepInSecond(3);
//        homePage.refreshCurrentPage(driver);

        //Có thể upload 1 lần nhiều file => dùng 1 hàm
        homePage.uploadMultipleFiles(driver, karik, brightSide, darkSide);
        homePage.sleepInSecond(3);

        //Verify load file lên
        Assert.assertTrue(homePage.isFileLoadedByName(karik));
        Assert.assertTrue(homePage.isFileLoadedByName(brightSide));
        Assert.assertTrue(homePage.isFileLoadedByName(darkSide));

        //Click upload button tại từng file
        homePage.clickToUploadButton();

        //Có thể verify 1 file/ nhiều file được upload xong
        Assert.assertTrue(homePage.isFileUploadedByName(karik));
        Assert.assertTrue(homePage.isFileUploadedByName(brightSide));
        Assert.assertTrue(homePage.isFileUploadedByName(darkSide));
    }

    @Test
    public void Table_02_() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}