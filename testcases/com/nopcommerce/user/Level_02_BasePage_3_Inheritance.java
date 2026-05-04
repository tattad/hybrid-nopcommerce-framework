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
        clickToElement("//a[@class='ico-register']");

        waitForElementClickable("//input[@id='gender-male']");
        clickToElement("//input[@id='gender-male']");

        sendkeyToElement("//input[@id='FirstName']", firstName);
        sendkeyToElement("//input[@id='LastName']", lastName);
        sendkeyToElement("//input[@id='Email']", email);
        sendkeyToElement("//input[@id='Company']", companyName);
        sendkeyToElement("//input[@id='Password']", password);
        sendkeyToElement("//input[@id='ConfirmPassword']", password);

        waitForElementClickable("//button[@id='register-button']");
        clickToElement("//button[@id='register-button']");

        Assert.assertEquals(getElementText("//div[@class='result']"), "Your registration completed");
    }

    @Test
    public void TC_02_Login() {
        waitForElementClickable("//a[@class='ico-login']");
        clickToElement("//a[@class='ico-login']");

        sendkeyToElement("//input[@id='Email']", email);
        sendkeyToElement("//input[@id='Password']", password);

        waitForElementClickable("//button[contains(@class,'login-button')]");
        clickToElement("//button[contains(@class,'login-button')]");

        Assert.assertTrue(isElementDisplayed("//a[@class='ico-account' and text()='My account']"));
    }

    @Test
    public void TC_03_MyAccount() {
        waitForElementClickable("//a[@class='ico-account']");
        clickToElement("//a[@class='ico-account']");


        Assert.assertTrue(isElementSelected("//input[@id='gender-male']"));

        Assert.assertEquals(getElementAttribute("//input[@id='FirstName']", "value"), firstName);
        Assert.assertEquals(getElementAttribute("//input[@id='LastName']", "value"), lastName);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private int generateRandomNumber() {
        return new Random().nextInt(99999);
    }
}
