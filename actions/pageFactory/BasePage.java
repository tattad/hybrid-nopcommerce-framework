package pageFactory;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class BasePage {

    public void clickToElement(WebElement element) {
        element.click();
    }

    public void sendkeyToElement(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    public void selectItemInDropdown(WebElement element, String itemText) {
        new Select(element).selectByVisibleText(itemText);
    }

    public String getSelectedItemInDropdown(WebElement element) {
        return new Select(element).getFirstSelectedOption().getText();
    }

    public String getElementAttribute(WebElement element, String attributeName) {
        return element.getAttribute(attributeName);
    }

    public String getElementText(WebElement element) {
        return element.getText();
    }

    public boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public boolean isElementSelected(WebElement element) {
        return element.isSelected();
    }

    public void waitForElementVisible(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementClickable(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(element));
    }
}