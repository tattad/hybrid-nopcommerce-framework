package com.orangehrm.pim;

import actions.commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class PIM_02_Employee extends BaseTest {
    private WebDriver driver;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browser, String url) {
        driver = getBrowserDriver(browser, url);
    }

    @Test
    public void Employee_07_Immigration() {

    }

    @Test
    public void Employee_08_Job() {

    }

    @Test
    public void Employee_09_Salary() {

    }

    @Test
    public void Employee_10_Report_To() {

    }

    @Test
    public void Employee_11_Qualifications() {

    }

    @AfterClass
    public void afterClass() {
        closeBrowserDriver();
    }
}
