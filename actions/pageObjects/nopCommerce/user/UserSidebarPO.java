package pageObjects.nopCommerce.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.nopCommerce.PageGenerators;
import pageUIs.nopCommerce.user.UserSidebarPageUI;

public class UserSidebarPO extends BasePage {
    WebDriver driver;

    public UserSidebarPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /*Only use for level_08_Page_Navigation*/
    public UserRewardPointPO openRewardPointPage() {
        waitForElementClickable(driver, UserSidebarPageUI.REWARD_POINT_LINK);
        clickToElement(driver, UserSidebarPageUI.REWARD_POINT_LINK);
        return new UserRewardPointPO(driver);
    }

    public UserCustomerInfoPO openCustomerInforPage() {
        waitForElementClickable(driver, UserSidebarPageUI.CUSTOMER_INFO_LINK);
        clickToElement(driver, UserSidebarPageUI.CUSTOMER_INFO_LINK);
        return new UserCustomerInfoPO(driver);
    }

    public UserAddressPO openAddressPage() {
        waitForElementClickable(driver, UserSidebarPageUI.ADDRESS_LINK);
        clickToElement(driver, UserSidebarPageUI.ADDRESS_LINK);
        return PageGenerators.getUserAddressPage(driver);
    }

    public UserOrderPO openOrderPage() {
        waitForElementClickable(driver, UserSidebarPageUI.ORDER_LINK);
        clickToElement(driver, UserSidebarPageUI.ORDER_LINK);
        return PageGenerators.getUserOrderPage(driver);
    }

    //Phù hợp cho số lượng page ít - switch case ít
    public UserSidebarPO openSidebarLinkPageName(String pageName) {
        waitForElementClickable(driver, UserSidebarPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
        clickToElement(driver, UserSidebarPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);

        switch (pageName) {
            case "Addresses":
                return PageGenerators.getUserAddressPage(driver);
            case "Reward Points":
                return PageGenerators.getUserRewardPointPage(driver);
            case "Orders":
                return PageGenerators.getUserOrderPage(driver);
            case "Customer Info":
                return PageGenerators.getUserCustomerPage(driver);
            default:
                throw new RuntimeException("Page name is invalid!!!");
        }
    }

    public void openSidebarLinkPageNames(String pageName) {
        waitForElementClickable(driver, UserSidebarPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
        clickToElement(driver, UserSidebarPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
    }
}