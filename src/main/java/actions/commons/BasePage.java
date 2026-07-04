package actions.commons;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import actions.pageObjects.nopCommerce.PageGenerators;
import actions.pageObjects.nopCommerce.user.*;
import actions.pageObjects.orangehrm.LoginPO;
import interfaces.pageUIs.nopCommerce.BasePageUI;
import interfaces.pageUIs.nopCommerce.user.UserHomePageUI;
import interfaces.pageUIs.nopCommerce.user.UserRegisterPageUI;
import interfaces.pageUIs.orangehrm.BasePUI;
import actions.pageObjects.orangehrm.PageGenerator;

import java.time.Duration;
import java.util.List;
import java.util.Set;

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
    private WebDriverWait waitExplicit;
    private By byLocator;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    //Common function (hàm dùng chung) cho nhiều class khác
    public void openPageUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Alert waitForAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.alertIsPresent());
    }

    public void acceptToAlert(WebDriver driver) {
        waitForAlertPresence(driver).accept();
    }

    public void cancelToAlert(WebDriver driver) {
        driver.switchTo().alert().dismiss();
    }

    public String getAlertText(WebDriver driver) {
        return waitForAlertPresence(driver).getText();
    }

    public void sendkeyToAlert(WebDriver driver, String keysToSend) {
        waitForAlertPresence(driver).sendKeys(keysToSend);
    }

    public void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            driver.switchTo().window(runWindow);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    public WebElement getWebElement(WebDriver driver, String locator) {
        return driver.findElement(getByLocator(locator));
    }

    public List<WebElement> getListElement(WebDriver driver, String locator) {
        return driver.findElements(getByLocator(locator));
    }

    public List<WebElement> getListElement(WebDriver driver, String locator, String... restValue) {
        return driver.findElements(getByLocator(castParameter(locator, restValue)));
    }

    private String castParameter(String locator, String... restParameter) {
        return String.format(locator, (Object[]) restParameter);
    }

    //Truyền tham số vào loại gì sẽ trả về kiểu By tương ứng
    //String prefix: css/ id/ name/ class => By.css/ By.id/ By.name/...
    //Convention: css/Css/CSS - id/Id/ID
    private By getByLocator(String prefixLocator) {
        By by = null;
        if (prefixLocator.toUpperCase().startsWith("ID")) {
            by = By.id(prefixLocator.substring(3));
        } else if (prefixLocator.toUpperCase().startsWith("CLASS")) {
            by = By.className(prefixLocator.substring(6));
        } else if (prefixLocator.toUpperCase().startsWith("NAME")) {
            by = By.name(prefixLocator.substring(5));
        } else if (prefixLocator.toUpperCase().startsWith("TAGNAME")) {
            by = By.tagName(prefixLocator.substring(8));
        } else if (prefixLocator.toUpperCase().startsWith("CSS")) {
            by = By.cssSelector(prefixLocator.substring(4));
        } else if (prefixLocator.toUpperCase().startsWith("XPATH")) {
            by = By.xpath(prefixLocator.substring(6));
        } else {
            throw new RuntimeException("Locator type is not supported!!!");
        }
        return by;
    }

    private By getByXpath(String locator) {
        return By.xpath(locator);
    }

    public Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public void setCookies(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            Cookie newCookie = new Cookie.Builder(cookie.getName(), cookie.getValue())
                    .domain(cookie.getDomain())
                    .path(cookie.getPath())
                    .expiresOn(cookie.getExpiry())
                    .isHttpOnly(cookie.isHttpOnly())
                    .isSecure(false) // Set secure to false for HTTP
                    .build();
            driver.manage().addCookie(newCookie);
        }
        sleepInSecond(3);
    }

    public void clickToElement(WebDriver driver, String locator) {
        getWebElement(driver, locator).click();
    }

    public void clickToElement(WebDriver driver, String locator, String... restValue) {
        getWebElement(driver, castParameter(locator, restValue)).click();
    }

    public void sendkeyToElement(WebDriver driver, String locator, String value) {
        getWebElement(driver, locator).clear();
        getWebElement(driver, locator).sendKeys(value);
    }

    public void sendkeyToElement(WebDriver driver, String locator, String value, String... restValue) {
        getWebElement(driver, castParameter(locator, restValue)).clear();
        getWebElement(driver, castParameter(locator, restValue)).sendKeys(value);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String itemText) {
        new Select(getWebElement(driver, locator)).selectByVisibleText(itemText);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String itemText, String... restValue) {
        new Select(getWebElement(driver, castParameter(locator, restValue))).selectByVisibleText(itemText);
    }

    public String getSelectedItemInDropdown(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator)).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator)).isMultiple();
    }

    public void selectItemInSelectableDropdown(WebDriver driver, String parentLocator, String childLocator, String itemText) {
        clickToElement(driver, parentLocator);
        sleepInSecond(1);

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(itemText)) {
                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    public void selectItemInSelectableDropdown(WebDriver driver, String parentLocator, String childLocator, String itemText, String... restValue) {
        clickToElement(driver, castParameter(parentLocator, restValue));
        sleepInSecond(1);

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(itemText)) {
                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        getWebElement(driver, parentLocator).click();
        sleepInSecond(2);

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.xpath(childItemLocator))));

        sleepInSecond(2);
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                item.click();
                break;
            }
        }
    }

    public void sleepInSecond(long timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void sleepInMiliSecond(long timeInMiliSeconds) {
        try {
            Thread.sleep(timeInMiliSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
        return getWebElement(driver, locator).getAttribute(attributeName);
    }

    public String getElementAttribute(WebDriver driver, String locator, String attributeName, String... restValue) {
        return getWebElement(driver, castParameter(locator, restValue)).getAttribute(attributeName);
    }

    public Dimension getElementSize(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getSize();
    }

    public String getElementDOMAttribute(WebDriver driver, String locator, String attributeName) {
        return getWebElement(driver, locator).getAttribute(attributeName);
    }

    public String getElementDOMAttribute(WebDriver driver, String locator, String attributeName, String... restValue) {
        return getWebElement(driver, castParameter(locator, restValue)).getAttribute(attributeName);
    }

    public String getElementDOMProperty(WebDriver driver, String locator, String propertyName) {
        return getWebElement(driver, locator).getDomProperty(propertyName);
    }

    public String getElementDOMProperty(WebDriver driver, String locator, String propertyName, String... restValue) {
        return getWebElement(driver, castParameter(locator, restValue)).getDomProperty(propertyName);
    }

    public String getElementText(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getText();
    }

    public String getElementText(WebDriver driver, String locator, String... restValue) {
        return getWebElement(driver, castParameter(locator, restValue)).getText();
    }

    public String getCssValue(WebDriver driver, String locator, String propertyName) {
        return getWebElement(driver, locator).getCssValue(propertyName);
    }

    public String getHexaColorFromRGBA(WebDriver driver, String rgbaValue) {
        return Color.fromString(rgbaValue).asHex().toUpperCase();
    }

    public int getListElementNumber(WebDriver driver, String locator) {
        return getListElement(driver, locator).size();
    }

    public void checkToCheckboxOrRadio(WebDriver driver, String locator) {
        if (!getWebElement(driver, locator).isSelected()) {
            getWebElement(driver, locator).click();
        }
    }

    public void checkToCheckboxOrRadio(WebDriver driver, String locator, String... restValue) {
        if (!getWebElement(driver, castParameter(locator, restValue)).isSelected()) {
            getWebElement(driver, castParameter(locator, restValue)).click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locator) {
        if (getWebElement(driver, locator).isSelected()) {
            getWebElement(driver, locator).click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locator, String... restValue) {
        if (getWebElement(driver, castParameter(locator, restValue)).isSelected()) {
            getWebElement(driver, castParameter(locator, restValue)).click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        boolean status = false;
        try {
            //TH 1: Element có hiển thị ở trên UI và có xuất hiện ở trong DOM (Visible/ Displayed) => true
            //TH 2: Element không hiển thị ở trên UI và có xuất hiện ở trong DOM (Invisible) => false
            WebElement element = getWebElement(driver, locator);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            //TH 3: Element không hiển thị ở trên UI và không có ở trong DOM (Invisible) => false
            return status;
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator, String... restValue) {
        boolean status = false;
        try {
            WebElement element = getWebElement(driver, castParameter(locator, restValue));
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return status;
        }
    }

    public void overideGlobalTimeout(WebDriver driver, long timeout) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
    }

//    public boolean isElementUndisplayed(WebDriver driver, String locator) {
//        try {
//            overideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
//            List<WebElement> elements = getListElement(driver, locator);
//            if (elements.isEmpty()) {
//                System.out.println("Element not in DOM");
//                return true;
//            }
//            return !elements.get(0).isDisplayed();
//        } catch (NoSuchElementException | StaleElementReferenceException e) {
//            return true;
//        } finally {
//            overideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
//        }
//    }

    public boolean isElementUndisplayed(WebDriver driver, String locator) {
        overideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        List<WebElement> elements = getListElement(driver, locator);
        overideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
        if (elements.size() == 0) {
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

//    public boolean isElementUndisplayed(WebDriver driver, String locator, String... restValue) {
//        try {
//            overideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
//            List<WebElement> elements = getListElement(driver, castParameter(locator, restValue));
//            if (elements.isEmpty()) {
//                System.out.println("Element not in DOM");
//                return true;
//            }
//            return !elements.get(0).isDisplayed();
//        } catch (NoSuchElementException | StaleElementReferenceException e) {
//            return true;
//        } finally {
//            overideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
//        }
//    }

    public boolean isElementUndisplayed(WebDriver driver, String locator, String... restValue) {
        overideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        List<WebElement> elements = getListElement(driver, castParameter(locator, restValue));
        overideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
        if (elements.size() == 0) {
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isElementEnabled(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isEnabled();
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locator, String... restValue) {
        return getWebElement(driver, castParameter(locator, restValue)).isSelected();
    }

    public void switchToIFrame(WebDriver driver, String locator) {
        driver.switchTo().frame(getWebElement(driver, locator));
    }

    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void hoverToElement(WebDriver driver, String locator) {
        new Actions(driver).moveToElement(getWebElement(driver, locator)).perform();
    }

    public void clickToElementByAction(WebDriver driver, String locator) {
        new Actions(driver).click(getWebElement(driver, locator)).perform();
    }

    public void clickAndHoldToElement(WebDriver driver, String locator) {
        new Actions(driver).clickAndHold(getWebElement(driver, locator)).perform();
    }

    public void releaseLeftMouse(WebDriver driver) {
        new Actions(driver).release();
    }

    public void doubleClickToElement(WebDriver driver, String locator) {
        new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator) {
        new Actions(driver).contextClick(getWebElement(driver, locator)).perform();
    }

    public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
        new Actions(driver).dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, targetLocator)).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
        new Actions(driver).sendKeys(getWebElement(driver, locator), key).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys key, String... restValue) {
        new Actions(driver).sendKeys(getWebElement(driver, castParameter(locator, restValue)), key).perform();
    }

    public void scrollToElement(WebDriver driver, String locator) {
        new Actions(driver).scrollToElement(getWebElement(driver, locator)).perform();
    }

    public void scrollToBottomPageByJS(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void hightlightElementByJS(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
        sleepInSecond(3);
    }

    public String getElementTextByJS(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", getWebElement(driver, locator));
    }

    public void scrollToElementOnTopByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    public void scrollToElementOnDownByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
    }

    public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getWebElement(driver, locator));
    }

    public void removeAttributeInDOMByJS(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
    }

    public String getAttributeInDOMByJS(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver, locator));
    }

    public String getElementValidationMessageByJS(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public boolean isImageLoadedByJS(WebDriver driver, String locator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
    }

//    public void waitForElementVisible(WebDriver driver, String locator) {
//        waitExplicit = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
//        byLocator = getByLocator(locator);
//        try{
//            waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
//        }
//        catch (Exception e){
//            log.debug("Element doesn't exist");
//        }
//    }

//    public boolean isControlDisplayed(WebDriver driver, String locator) {
//        boolean status=true;
//        try {
//            element=driver.findElement(By.xpath(locator));
//            if (element.isDisplayed()){
//                return status;
//            }
//        }catch (Exception e){
//            status=false;
//        }
//        return status;
//    }

    public void waitForElementVisible(WebDriver driver, String locator, String... restParameter) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castParameter(locator, restParameter))));
    }

    public void waitForElementAttribute(WebDriver driver, String locator, String attributeName, String attributeValue) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.attributeToBe(getByLocator(locator), attributeName, attributeValue));
    }

    public void waitForElementAttribute(WebDriver driver, String locator, String attributeName, String attributeValue, String... restParameter) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.attributeToBe(getByLocator(castParameter(locator, restParameter)), attributeName, attributeValue));
    }

    public void waitForElementSelected(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
    }

    public void waitForElementSelected(WebDriver driver, String locator, String... restParameter) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByLocator(castParameter(locator, restParameter))));
    }

    public void waitForElementPresence(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementPresence(WebDriver driver, String locator, String... restParameter) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(getByLocator(castParameter(locator, restParameter))));
    }

    public void waitForElementInvisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementInvisible(WebDriver driver, String locator, String... restParameter) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castParameter(locator, restParameter))));
    }

    public boolean waitForElementInvisibleNotInDom(WebDriver driver, String locator, String... restParameter) {
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castParameter(locator, restParameter))));
    }

    public boolean waitForListElementInvisible(WebDriver driver, String locator) {
//        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver, locator)));
        By byLocator = getByLocator(locator);
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.numberOfElementsToBe(byLocator, 0)) != null;
    }

    public void waitForElementClickable(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator, String... restParameter) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(castParameter(locator, restParameter))));
    }

    public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
        //Lấy ra đuờng dẫn thư mục upload file
        String filePath = GlobalConstants.UPLOAD_PATH;
        String fullFileName = "";
        //Dùng vòng lặp duyệt qua các file name
        for (String file : fileNames) {
            fullFileName += filePath + file + "\n";
        }
        //Cắt kí tự xuống dòng (\n) ở đầu 2 chuỗi
        fullFileName = fullFileName.trim();
        getWebElement(driver, BasePageUI.UPLOAD_FILE_TYPE).sendKeys(fullFileName.trim());
        sleepInSecond(1);
    }

    /*Only use for Level_07_Switch_Page_Object*/
    public UserRewardPointPO openRewardPointPage() {
        waitForElementClickable(driver, BasePageUI.REWARD_POINT_LINK);
        clickToElement(driver, BasePageUI.REWARD_POINT_LINK);
        return new UserRewardPointPO(driver);
    }

    public UserCustomerInfoPO openCustomerInforPage() {
        waitForElementClickable(driver, BasePageUI.CUSTOMER_INFO_LINK);
        clickToElement(driver, BasePageUI.CUSTOMER_INFO_LINK);
        return new UserCustomerInfoPO(driver);
    }

    public UserAddressPO openAddressPage() {
        waitForElementClickable(driver, BasePageUI.ADDRESS_LINK);
        clickToElement(driver, BasePageUI.ADDRESS_LINK);
        return PageGenerators.getUserAddressPage(driver);
    }

    public UserRegisterPO openToRegisterPage() {
        waitForElementClickable(driver, UserHomePageUI.REGISTER_LINK);
        clickToElement(driver, UserHomePageUI.REGISTER_LINK);
        return PageGenerators.getUserRegisterPage(driver);
    }

    public UserCustomerInfoPO openCustomerInfoPage() {
        waitForElementClickable(driver, UserHomePageUI.MY_ACCOUNT_LINK);
        clickToElement(driver, UserHomePageUI.MY_ACCOUNT_LINK);
        return PageGenerators.getUserCustomerPage(driver);
    }

    public UserOrderPO openOrderPage() {
        waitForElementClickable(driver, BasePageUI.ORDER_LINK);
        clickToElement(driver, BasePageUI.ORDER_LINK);
        return PageGenerators.getUserOrderPage(driver);
    }

    public UserLoginPO openLoginPage() {
        waitForElementClickable(driver, UserRegisterPageUI.LOGIN_LINK);
        clickToElement(driver, UserRegisterPageUI.LOGIN_LINK);
        return PageGenerators.getUserLoginPage(driver);
    }

    public void openAdminSite(WebDriver driver, String adminUrl) {
        openPageUrl(driver, adminUrl);
    }

    public void enterToTextBoxByID(WebDriver driver, String textboxID, String value) {
        waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxID);
        sendkeyToElement(driver, BasePageUI.TEXTBOX_BY_ID, value, textboxID);
    }

    public void clickToRadioByID(WebDriver driver, String radioID) {
        waitForElementClickable(driver, BasePageUI.RADIO_BY_ID, radioID);
        checkToCheckboxOrRadio(driver, BasePageUI.RADIO_BY_ID, radioID);
    }

    public void clickToCheckboxByID(WebDriver driver, String checkboxID) {
        waitForElementClickable(driver, BasePageUI.CHECKBOX_BY_ID, checkboxID);
        checkToCheckboxOrRadio(driver, BasePageUI.CHECKBOX_BY_ID, checkboxID);
    }

    public String getTextboxValueByID(WebDriver driver, String textboxID) {
        waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxID);
        return getElementAttribute(driver, BasePageUI.TEXTBOX_BY_ID, "value", textboxID);
    }

    public boolean isRadioByIDIsSelected(WebDriver driver, String radioID) {
        waitForElementSelected(driver, BasePageUI.RADIO_BY_ID, radioID);
        return isElementSelected(driver, BasePageUI.RADIO_BY_ID, radioID);
    }

    public boolean isCheckboxByIDIsSelected(WebDriver driver, String checkboxID) {
        waitForElementSelected(driver, BasePageUI.CHECKBOX_BY_ID, checkboxID);
        return isElementSelected(driver, BasePageUI.CHECKBOX_BY_ID, checkboxID);
    }

    /* Only use for OrangeHRM project */
    public void waitForAllLoadingIconInvisible(WebDriver driver) {
        waitForListElementInvisible(driver, BasePUI.LOADING_SPINNER_ICON);
    }

    @Step("Click to {0} module Menu item")
    public void clickToModuleByTextInMenuItem(WebDriver driver, String menuName) {
        waitForElementClickable(driver, BasePUI.DYNAMIC_MODULE_BY_TEXT_IN_MENU_ITEM, menuName);
        clickToElement(driver, BasePUI.DYNAMIC_MODULE_BY_TEXT_IN_MENU_ITEM, menuName);
    }

    public boolean isModuleByTextInMenuItemDisplayed(WebDriver driver, String menuName) {
        waitForElementVisible(driver, BasePUI.DYNAMIC_MODULE_BY_TEXT_IN_MENU_ITEM, menuName);
        return isElementDisplayed(driver, BasePUI.DYNAMIC_MODULE_BY_TEXT_IN_MENU_ITEM, menuName);
    }

    public boolean isModuleByTextInMenuItemUndisplayed(WebDriver driver, String menuName) {
//        waitForElementInvisibleNotInDom(driver, BasePUI.DYNAMIC_MODULE_BY_TEXT_IN_MENU_ITEM, menuName);
        return isElementUndisplayed(driver, BasePUI.DYNAMIC_MODULE_BY_TEXT_IN_MENU_ITEM, menuName);
    }

    @Step("Waiting for Loading Spinner disappear")
    public boolean isLoadingSpinnerDisappear(WebDriver driver) {
        return waitForListElementInvisible(driver, BasePUI.LOADING_SPINNER_ICON);
    }

    @Step("Enter to {0} textbox by name with value {1}")
    public void enterToTextBoxByName(WebDriver driver, String textboxNameAttribute, String value) {
        waitForElementVisible(driver, BasePUI.DYNAMIC_TEXBOX_BY_NAME, textboxNameAttribute);
        sendkeyToElement(driver, BasePUI.DYNAMIC_TEXBOX_BY_NAME, value, textboxNameAttribute);
    }

    @Step("Enter to {0} textbox by label with value {1}")
    public void enterToTextBoxByLabel(WebDriver driver, String textboxLabel, String value) {
        waitForElementVisible(driver, BasePUI.DYNAMIC_TEXTBOX_BY_LABEL, textboxLabel);
        sendkeyToElement(driver, BasePUI.DYNAMIC_TEXTBOX_BY_LABEL, value, textboxLabel);
    }

    public void clearTextBoxByLabel(WebDriver driver, String textboxLabel) {
        waitForElementVisible(driver, BasePUI.DYNAMIC_TEXTBOX_BY_LABEL, textboxLabel);
        sendkeyToElement(driver, BasePUI.DYNAMIC_TEXTBOX_BY_LABEL, Keys.chord(Keys.CONTROL, "a"), textboxLabel);
        sendkeyToElement(driver, BasePUI.DYNAMIC_TEXTBOX_BY_LABEL, String.valueOf(Keys.DELETE), textboxLabel);
    }

    @Step("Check if {0} toast message is displayed")
    public boolean isToastMessageDisplayed(WebDriver driver, String toastMessage) {
        waitForElementVisible(driver, BasePUI.DYNAMIC_TOAST_MESSAGE_BY_TEXT, toastMessage);
        return isElementDisplayed(driver, BasePUI.DYNAMIC_TOAST_MESSAGE_BY_TEXT, toastMessage);
    }

    @Step("Click to {0} button by text")
    public void clickToButtonByText(WebDriver driver, String buttonText) {
        waitForElementClickable(driver, BasePUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
        clickToElement(driver, BasePUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
    }

    @Step("Click to {0} button by main title")
    public void clickToButtonByMainTitle(WebDriver driver, String buttonText, String mainTitle) {
        waitForElementClickable(driver, BasePUI.DYNAMIC_BUTTON_BY_MAIN_TITLE, mainTitle, buttonText);
        clickToElement(driver, BasePUI.DYNAMIC_BUTTON_BY_MAIN_TITLE, mainTitle, buttonText);
    }

    public String getTextboxValueByName(WebDriver driver, String textboxNameAttribute) {
        waitForElementVisible(driver, BasePUI.DYNAMIC_TEXBOX_BY_NAME, textboxNameAttribute);
        return getElementDOMProperty(driver, BasePUI.DYNAMIC_TEXBOX_BY_NAME, "value", textboxNameAttribute);
    }

    public String getTextboxValueByLabel(WebDriver driver, String textboxLabel) {
        waitForElementVisible(driver, BasePUI.DYNAMIC_TEXTBOX_BY_LABEL, textboxLabel);
        return getElementDOMProperty(driver, BasePUI.DYNAMIC_TEXTBOX_BY_LABEL, "value", textboxLabel);
    }

    public void selectDropdownByLabel(WebDriver driver, String labelName, String value) {
        waitForElementClickable(driver, BasePUI.DYNAMIC_PARENT_DROPDOWN_BY_LABEL, labelName);
        selectItemInSelectableDropdown(driver, BasePUI.DYNAMIC_PARENT_DROPDOWN_BY_LABEL, BasePUI.DYNAMIC_CHILD_DROPDOWN_BY_LABEL, value, labelName);
    }

    public void clickToRadioButtonByLabel(WebDriver driver, String labelName) {
        waitForElementPresence(driver, BasePUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, labelName);
        clickToElement(driver, BasePUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, labelName);
    }

    public void clickToCheckboxByLabel(WebDriver driver, String labelName) {
        waitForElementPresence(driver, BasePUI.DYNAMIC_CHECKBOX_BY_LABEL, labelName);
        checkToCheckboxOrRadio(driver, BasePUI.DYNAMIC_CHECKBOX_BY_LABEL, labelName);
    }

    public LoginPO clickLogoutOnTopMenu(WebDriver driver) {
        waitForElementClickable(driver, BasePUI.USER_DROPDOWN);
        clickToElement(driver, BasePUI.USER_DROPDOWN);
        waitForElementClickable(driver, BasePUI.LOGOUT_LINK);
        clickToElement(driver, BasePUI.LOGOUT_LINK);
        return PageGenerator.getPage(LoginPO.class, driver);
    }
}