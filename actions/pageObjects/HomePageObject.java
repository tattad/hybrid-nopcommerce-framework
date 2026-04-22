package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {

    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    //Hàm khởi tạo (Constructor function)
    //1 - Cùng tên với tên Class
    //2 - Không có kiểu trả về (Data Type)
    //3 - Chạy đầu tiên khi class này được gọi
    //4 - Có tham số hoặc không
    //5 - Không tự define hàm khởi tạo thì JVM sẽ mặc định tạo ra 1 hàm khởi tạo rỗng

    public void clickToRegisterLink() {
        waitForElementClickable(HomePageUI.REGISTER_LINK);
        clickToElement(HomePageUI.REGISTER_LINK);
    }

    public boolean isMyAccountLinkDisplayed() {
        return false;
    }

    public void clickToMyAccountLink() {
        waitForElementClickable(HomePageUI.MY_ACCOUNT_LINK);
        clickToElement(HomePageUI.MY_ACCOUNT_LINK);
    }
}