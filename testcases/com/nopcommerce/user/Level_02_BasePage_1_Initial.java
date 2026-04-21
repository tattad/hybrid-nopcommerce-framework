package com.nopcommerce.user;

import commons.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_02_BasePage_1_Initial {
    WebDriver driver;
    BasePage basePage; //Declare
    private String firstName, lastName, email, companyName, password;

    @BeforeClass
    public void beforeClass() {
        driver = new EdgeDriver();
        basePage = new BasePage();

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
        basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.waitForElementClickable(driver, "//input[@id='gender-male']");
        basePage.clickToElement(driver, "//input[@id='gender-male']");

        basePage.sendkeyToElement(driver, "//input[@id='FirstName']", firstName);
        basePage.sendkeyToElement(driver, "//input[@id='LastName']", lastName);
        basePage.sendkeyToElement(driver, "//input[@id='Email']", email);
        basePage.sendkeyToElement(driver, "//input[@id='Company']", companyName);
        basePage.sendkeyToElement(driver, "//input[@id='Password']", password);
        basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", password);

        basePage.waitForElementClickable(driver, "//button[@id='register-button']");
        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver, "//div[@class='result']"), "Your registration completed");
    }

    @Test
    public void TC_02_Login() {
        basePage.waitForElementClickable(driver, "//a[@class='ico-login']");
        basePage.clickToElement(driver, "//a[@class='ico-login']");

        basePage.sendkeyToElement(driver, "//input[@id='Email']", email);
        basePage.sendkeyToElement(driver, "//input[@id='Password']", password);

        basePage.waitForElementClickable(driver, "//button[contains(@class,'login-button')]");
        basePage.clickToElement(driver, "//button[contains(@class,'login-button')]");

        Assert.assertTrue(basePage.isElementDisplayed(driver, "//a[@class='ico-account' and text()='My account']"));
    }

    @Test
    public void TC_03_MyAccount() {
        basePage.waitForElementClickable(driver, "//a[@class='ico-account']");
        basePage.clickToElement(driver, "//a[@class='ico-account']");


        Assert.assertTrue(basePage.isElementSelected(driver, "//input[@id='gender-male']"));

        Assert.assertEquals(basePage.getElementAttribute(driver, "//input[@id='FirstName']", "value"), firstName);
        Assert.assertEquals(basePage.getElementAttribute(driver, "//input[@id='LastName']", "value"), lastName);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private int generateRandomNumber() {
        return new Random().nextInt(99999);
    }
}
