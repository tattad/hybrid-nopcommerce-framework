package commons;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    //1 - Access Modifier: public/ protected/ private/ default
    //2 - Kiểu dữ liệu của hàmg (Data type): void/ int/ String/ boolean/ WebElement
    //  - Nó sẽ liên quan đến cái chức năng mình viết trong thân hàm
    //3 - Tên hàm: Đặt tên có nghĩa theo chức năng đang cần viết
    //    Convention tuân theo chuẩn của từng ngôn ngữ lập trình (Java)
    //    camelCase: từ đầu tiên viết thường - Chữ cái đầu tiên của các từ tiếp theo sẽ viết hoa
    //4 - Có tham số hay không (tùy vào chức năng cần viết)
    //5 - Kiểu dữ liệu trả về cho hàm (liên quan đến các step mình viết trong hàm đó)
    //    Nếu như có return dữ liệu thì sẽ khớp vs kiểu dữ liệu ở số 2
    //    Nếu như có return thì nó là cái step cuối cùng
    private WebDriver driver;

    public boolean isElemenetDisplayed() {
        return driver.findElement(By.cssSelector("")).isDisplayed();
    }

    public void clickToElement() {
        driver.findElement(By.cssSelector("")).click();
    }

    public void getElementText() {
        driver.findElement(By.cssSelector("")).getText();
    }

    //Giá trị sử dụng trong hàm sẽ truyền từ bên ngoài thông qua các tham số
    public void sendKeyToElement(String value) {
        driver.findElement(By.cssSelector("")).sendKeys(value);
    }
}
