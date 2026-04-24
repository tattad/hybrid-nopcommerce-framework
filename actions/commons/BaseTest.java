package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Random;

public class BaseTest {

    private WebDriver driver;
    private String projectPath = System.getProperty("user.dir");

    protected WebDriver getBrowserDriver(String browserName) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList) {
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case CHORME:
                driver = new ChromeDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser not recognized");
        }
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;
    }

    protected int generateRandomNumber() {
        return new Random().nextInt(99999);
    }
}
