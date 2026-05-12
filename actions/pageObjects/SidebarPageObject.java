package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.BasePageUI;
import pageUIs.SidebarPageUI;

public class SidebarPageObject extends BasePage {
    WebDriver driver;

    public SidebarPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public RewardPointPageObject openRewardPointPage(WebDriver driver) {
        waitForElementClickable(SidebarPageUI.REWARD_POINT_LINK);
        clickToElement(SidebarPageUI.REWARD_POINT_LINK);
        return new RewardPointPageObject(driver);
    }

    public CustomerInfoPageObject openCustomerInforPage(WebDriver driver) {
        waitForElementClickable(SidebarPageUI.CUSTOMER_INFO_LINK);
        clickToElement(SidebarPageUI.CUSTOMER_INFO_LINK);
        return new CustomerInfoPageObject(driver);
    }

    public AddressPageObject openAddressPage(WebDriver driver) {
        waitForElementClickable(SidebarPageUI.ADDRESS_LINK);
        clickToElement(SidebarPageUI.ADDRESS_LINK);
        return PageGenerator.getAddressPage(driver);
    }

    public OrderPageObject openOrderPage(WebDriver driver) {
        waitForElementClickable(SidebarPageUI.ORDER_LINK);
        clickToElement(SidebarPageUI.ORDER_LINK);
        return PageGenerator.getOrderPage(driver);
    }
}