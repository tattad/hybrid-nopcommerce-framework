package actions.commons;

//log4j version 1
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

//log4j version 2

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.time.Duration;
import java.util.Random;

public class BaseTest {
    protected WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    //log4j version 1
    //protected final Log log;
    //log4j version 2
    protected final Logger log;
    private String projectPath = System.getProperty("user.dir");

    public BaseTest() {
//        log = LogFactory.getLog(BaseTest.class);
        log = LogManager.getLogger(getClass());
    }

    protected WebDriver getBrowserDriver(String browserName) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList) {
            case FIREFOX:
                FirefoxOptions options = new FirefoxOptions();
                // Nhận tham số "headless" từ câu lệnh Maven
                String isHeadless = System.getProperty("headless");
                // Nếu người dùng truyền vào tham số -Dheadless=true thì mới chạy ẩn
                if (isHeadless != null && isHeadless.equalsIgnoreCase("true")) {
                    options.addArguments("--headless");
                }
                driver = new FirefoxDriver(options);
                break;
            case CHROME:
                driver = new ChromeDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser not recognized");
        }
        driver.get(GlobalConstants.DEV_USER_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String url) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList) {
            case FIREFOX:
                FirefoxOptions options = new FirefoxOptions();
                // Nhận tham số "headless" từ câu lệnh Maven
                String isHeadless = System.getProperty("headless");
                // Nếu người dùng truyền vào tham số -Dheadless=true thì mới chạy ẩn
                if (isHeadless != null && isHeadless.equalsIgnoreCase("true")) {
                    options.addArguments("--headless");
                }
                driver = new FirefoxDriver(options);
                break;
            case CHROME:
                driver = new ChromeDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser not recognized");
        }
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        return driver;
    }

    protected int generateRandomNumber() {
        return new Random().nextInt(99999);
    }

    protected void assertTrue(boolean condition) {
        Assert.assertTrue(verifyTrue(condition));
    }

    protected boolean verifyTrue(boolean condition) {
        boolean status = true;
        try {
            Assert.assertTrue(condition);
        } catch (Throwable e) {
            status = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean status = true;
        try {
            Assert.assertFalse(condition);
        } catch (Throwable e) {
            status = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

    protected boolean verifyEqual(Object actual, Object expected) {
        boolean status = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info("-------------------- PASSED --------------------");
        } catch (Throwable e) {
            status = false;
            log.info("-------------------- FAILED --------------------");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

    protected boolean verifyNotEqual(Object actual, Object expected) {
        boolean status = true;
        try {
            Assert.assertNotEquals(actual, expected);
        } catch (Throwable e) {
            status = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

//   ReportNG
//    @BeforeSuite
//    public void deleteReportFolder() {
//        deleteAllFileInFolder("htmlReportNG");
//    }
//    private void deleteAllFileInFolder(String folderName) {
//        try {
//            String pathFolderDownload = GlobalConstants.PROJECT_PATH + File.separator + folderName;
//            File file = new File(pathFolderDownload);
//            File[] listOfFiles = file.listFiles();
//            if (listOfFiles != null && listOfFiles.length != 0) {
//                for (int i = 0; i < listOfFiles.length; i++) {
//                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
//                        new File(listOfFiles[i].toString()).delete();
//                    }
//                }
//            }
//        } catch (Exception e) {
//            System.out.print(e.getMessage());
//        }
//    }

    @BeforeSuite
    public void deleteFileInReport() {
        // Remove all file in ReportNG screenshot (image)
        deleteAllFileInFolder("reportNGImage");

        // Remove all file in Allure attachment (json file)
        deleteAllFileInFolder("allure-results");
    }

    public void deleteAllFileInFolder(String folderName) {
        try {
            String pathFolderDownload = GlobalConstants.PROJECT_PATH + File.separator + folderName;
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            if (listOfFiles != null && listOfFiles.length > 0) {
                for (File currentFile : listOfFiles) {
                    if (currentFile.isFile() && !currentFile.getName().equals("environment.properties")) {
                        currentFile.delete();
                    }
                }
            } else {
                System.out.println("Empty folder");
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    // lỗi thời do Selenium 4.x đã có tính năng tự động đóng driver khi gọi quit() hoặc close()
//    protected void closeBrowserDriver() {
//        String cmd = null;
//        try {
//            String osName = System.getProperty("os.name").toLowerCase();
//            log.info("OS name = " + osName);
//
//            String driverInstanceName = driver.toString().toLowerCase();
//            log.info("Driver instance name = " + driverInstanceName);
//
//            String browserDriverName = null;
//
//            if (driverInstanceName.contains("chrome")) {
//                browserDriverName = "chromedriver";
//            } else if (driverInstanceName.contains("firefox")) {
//                browserDriverName = "geckodriver";
//            } else if (driverInstanceName.contains("edge")) {
//                browserDriverName = "msedgedriver";
//            } else if (driverInstanceName.contains("opera")) {
//                browserDriverName = "operadriver";
//            } else {
//                browserDriverName = "safaridriver";
//            }
//
//            if (osName.contains("window")) {
//                cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
//            } else {
//                cmd = "pkill " + browserDriverName;
//            }
//
//            if (driver != null) {
//                driver.manage().deleteAllCookies();
//                driver.quit();
//            }
//        } catch (Exception e) {
//            log.info(e.getMessage());
//        } finally {
//            try {/**/
//                Process process = Runtime.getRuntime().exec(cmd);
//                process.waitFor();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    protected int getRandomNumber() {
        return new Random().nextInt(99999);
    }

    protected void closeBrowserDriver() {
        try {
            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
                log.info("Closed browser successfully.");
            }
        } catch (Exception e) {
            log.error("Error while closing browser: " + e.getMessage());
        } finally {
            //Ép garbage collector của Java dọn dẹp rác (an toàn hơn kill OS)
            System.gc();
        }
    }
}