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

public class Level_02_BasePage_2_Static_Method {
    WebDriver driver;
    BasePage basePage; //Declare
    private String firstName, lastName, email, companyName, password;

    @BeforeClass
    public void beforeClass() {
        driver = new EdgeDriver();
//        basePage = BasePage.getBasePage();

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
        basePage.waitForElementClickable("//a[@class='ico-register']");
        basePage.clickToElement("//a[@class='ico-register']");

        basePage.waitForElementClickable("//input[@id='gender-male']");
        basePage.clickToElement("//input[@id='gender-male']");

        basePage.sendkeyToElement("//input[@id='FirstName']", firstName);
        basePage.sendkeyToElement("//input[@id='LastName']", lastName);
        basePage.sendkeyToElement("//input[@id='Email']", email);
        basePage.sendkeyToElement("//input[@id='Company']", companyName);
        basePage.sendkeyToElement("//input[@id='Password']", password);
        basePage.sendkeyToElement("//input[@id='ConfirmPassword']", password);

        basePage.waitForElementClickable("//button[@id='register-button']");
        basePage.clickToElement("//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementText("//div[@class='result']"), "Your registration completed");
    }

    @Test
    public void TC_02_Login() {
        basePage.waitForElementClickable("//a[@class='ico-login']");
        basePage.clickToElement("//a[@class='ico-login']");

        basePage.sendkeyToElement("//input[@id='Email']", email);
        basePage.sendkeyToElement("//input[@id='Password']", password);

        basePage.waitForElementClickable("//button[contains(@class,'login-button')]");
        basePage.clickToElement("//button[contains(@class,'login-button')]");

        Assert.assertTrue(basePage.isElementDisplayed("//a[@class='ico-account' and text()='My account']"));
    }

    @Test
    public void TC_03_MyAccount() {
        basePage.waitForElementClickable("//a[@class='ico-account']");
        basePage.clickToElement("//a[@class='ico-account']");


        Assert.assertTrue(basePage.isElementSelected("//input[@id='gender-male']"));

        Assert.assertEquals(basePage.getElementAttribute("//input[@id='FirstName']", "value"), firstName);
        Assert.assertEquals(basePage.getElementAttribute("//input[@id='LastName']", "value"), lastName);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private int generateRandomNumber() {
        return new Random().nextInt(99999);
    }
}
