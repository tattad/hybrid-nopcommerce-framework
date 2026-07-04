package testcases.com.nopcommerce.common;

import org.testng.annotations.*;

public class TestNG_Annotation {

    @BeforeTest
    public void beforeTest() {
        System.out.println("Run before test");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("Run after test");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("Run before class");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("Run after class");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Run before method");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Run after method");
    }

    @Test
    public void TC_01() {
        System.out.println("TC_01");
    }

    @Test
    public void TC_02() {
        System.out.println("TC_02");
    }
}