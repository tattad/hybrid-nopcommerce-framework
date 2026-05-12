package pageObjects.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.user.UserSidebarPageUI;

public class UserSidebarPO extends BasePage {
    WebDriver driver;

    public UserSidebarPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public UserRewardPointPO openRewardPointPage(WebDriver driver) {
        waitForElementClickable(UserSidebarPageUI.REWARD_POINT_LINK);
        clickToElement(driver, UserSidebarPageUI.REWARD_POINT_LINK);
        return new UserRewardPointPO(driver);
    }

    public UserCustomerInfoPO openCustomerInforPage(WebDriver driver) {
        waitForElementClickable(UserSidebarPageUI.CUSTOMER_INFO_LINK);
        clickToElement(driver, UserSidebarPageUI.CUSTOMER_INFO_LINK);
        return new UserCustomerInfoPO(driver);
    }

    public UserAddressPO openAddressPage(WebDriver driver) {
        waitForElementClickable(UserSidebarPageUI.ADDRESS_LINK);
        clickToElement(driver, UserSidebarPageUI.ADDRESS_LINK);
        return PageGenerator.getUserAddressPage(driver);
    }

    public UserOrderPO openOrderPage(WebDriver driver) {
        waitForElementClickable(UserSidebarPageUI.ORDER_LINK);
        clickToElement(driver, UserSidebarPageUI.ORDER_LINK);
        return PageGenerator.getUserOrderPage(driver);
    }
}