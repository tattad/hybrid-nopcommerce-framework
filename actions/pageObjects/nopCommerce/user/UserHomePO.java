package pageObjects.nopCommerce.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.user.UserHomePageUI;

public class UserHomePO extends BasePage {

    private WebDriver driver;

    public UserHomePO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    //Hàm khởi tạo (Constructor function)
    //1 - Cùng tên với tên Class
    //2 - Không có kiểu trả về (Data Type)
    //3 - Chạy đầu tiên khi class này được gọi
    //4 - Có tham số hoặc không
    //5 - Không tự define hàm khởi tạo thì JVM sẽ mặc định tạo ra 1 hàm khởi tạo rỗng


    public boolean isMyAccountLinkDisplayed() {
        waitForElementVisible(driver, UserHomePageUI.MY_ACCOUNT_LINK);
        return isElementDisplayed(driver, UserHomePageUI.MY_ACCOUNT_LINK);
    }
}