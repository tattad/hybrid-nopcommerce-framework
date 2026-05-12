package com.nopcommerce.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_02_BasePage_3_Inheritance extends BasePage {
    WebDriver driver;
    private String firstName, lastName, email, companyName, password;

    public Level_02_BasePage_3_Inheritance(WebDriver driver) {
        super(driver);
    }

    @BeforeClass
    public void beforeClass() {
        driver = new EdgeDriver();

        driver.get("https://demo.nopcommerce.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        email = "thomasmuller" + generateRandomNumber() + "@gmail.com";

        firstName = "Thomas";
        lastName = "Muller";
        companyName = "Bayern Munich";
        password = "12345678";
    }

    @Test
    public void TC_01_Register() {
        waitForElementClickable("//a[@class='ico-register']");
        clickToElement(driver, "//a[@class='ico-register']");

        waitForElementClickable("//input[@id='gender-male']");
        clickToElement(driver, "//input[@id='gender-male']");

        sendkeyToElement(driver, "//input[@id='FirstName']", firstName);
        sendkeyToElement(driver, "//input[@id='LastName']", lastName);
        sendkeyToElement(driver, "//input[@id='Email']", email);
        sendkeyToElement(driver, "//input[@id='Company']", companyName);
        sendkeyToElement(driver, "//input[@id='Password']", password);
        sendkeyToElement(driver, "//input[@id='ConfirmPassword']", password);

        waitForElementClickable("//button[@id='register-button']");
        clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
    }

    @Test
    public void TC_02_Login() {
        waitForElementClickable("//a[@class='ico-login']");
        clickToElement(driver, "//a[@class='ico-login']");

        sendkeyToElement(driver, "//input[@id='Email']", email);
        sendkeyToElement(driver, "//input[@id='Password']", password);

        waitForElementClickable("//button[contains(@class,'login-button')]");
        clickToElement(driver, "//button[contains(@class,'login-button')]");

        Assert.assertTrue(isElementDisplayed("//a[@class='ico-account' and text()='My account']"));
    }

    @Test
    public void TC_03_MyAccount() {
        waitForElementClickable("//a[@class='ico-account']");
        clickToElement(driver, "//a[@class='ico-account']");


        Assert.assertTrue(isElementSelected("//input[@id='gender-male']"));

        Assert.assertEquals(getElementAttribute(driver, "//input[@id='FirstName']", "value"), firstName);
        Assert.assertEquals(getElementAttribute(driver, "//input[@id='LastName']", "value"), lastName);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private int generateRandomNumber() {
        return new Random().nextInt(99999);
    }
}
