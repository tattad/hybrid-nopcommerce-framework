package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.nopCommerce.PageGenerator;
import pageObjects.nopCommerce.user.*;
import pageUIs.nopCommerce.BasePageUI;
import pageUIs.nopCommerce.user.UserHomePageUI;
import pageUIs.nopCommerce.user.UserRegisterPageUI;

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

    public WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(getByLocator(locator));
    }

    public List<WebElement> getListElement(WebDriver driver, String locator) {
        return driver.findElements(getByLocator(locator));
    }

    public List<WebElement> getListElement(WebDriver driver, String locator, String... restParamter) {
        return driver.findElements(getByLocator(castParameter(locator, restParamter)));
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
        getElement(driver, locator).click();
    }

    public void clickToElement(WebDriver driver, String locator, String... restParamter) {
        getElement(driver, castParameter(locator, restParamter)).click();
    }

    public void sendkeyToElement(WebDriver driver, String locator, String value) {
        getElement(driver, locator).clear();
        getElement(driver, locator).sendKeys(value);
    }

    public void sendkeyToElement(WebDriver driver, String locator, String value, String... restParamter) {
        getElement(driver, castParameter(locator, restParamter)).clear();
        getElement(driver, castParameter(locator, restParamter)).sendKeys(value);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String itemText) {
        new Select(getElement(driver, locator)).selectByVisibleText(itemText);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String itemText, String... restParamter) {
        new Select(getElement(driver, castParameter(locator, restParamter))).selectByVisibleText(itemText);
    }

    public String getSelectedItemInDropdown(WebDriver driver, String locator) {
        return new Select(getElement(driver, locator)).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        return new Select(getElement(driver, locator)).isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        getElement(driver, parentLocator).click();
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

    public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
        return getElement(driver, locator).getAttribute(attributeName);
    }

    public String getElementAttribute(WebDriver driver, String locator, String attributeName, String... restParamter) {
        return getElement(driver, castParameter(locator, restParamter)).getAttribute(attributeName);
    }

    public String getElementText(WebDriver driver, String locator) {
        return getElement(driver, locator).getText();
    }

    public String getElementText(WebDriver driver, String locator, String... restParamter) {
        return getElement(driver, castParameter(locator, restParamter)).getText();
    }

    public String getCssValue(WebDriver driver, String locator, String propertyName) {
        return getElement(driver, locator).getCssValue(propertyName);
    }

    public String getHexaColorFromRGBA(WebDriver driver, String rgbaValue) {
        return Color.fromString(rgbaValue).asHex().toUpperCase();
    }

    public int getListElementNumber(WebDriver driver, String locator) {
        return getListElement(driver, locator).size();
    }

    public void checkToCheckboxOrRadio(WebDriver driver, String locator) {
        if (!getElement(driver, locator).isSelected()) {
            getElement(driver, locator).click();
        }
    }

    public void checkToCheckboxOrRadio(WebDriver driver, String locator, String... restParamter) {
        if (!getElement(driver, castParameter(locator, restParamter)).isSelected()) {
            getElement(driver, castParameter(locator, restParamter)).click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locator) {
        if (getElement(driver, locator).isSelected()) {
            getElement(driver, locator).click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locator, String... restParamter) {
        if (getElement(driver, castParameter(locator, restParamter)).isSelected()) {
            getElement(driver, castParameter(locator, restParamter)).click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        return getElement(driver, locator).isDisplayed();
    }

    public boolean isElementDisplayed(WebDriver driver, String locator, String... restParamter) {
        return getElement(driver, castParameter(locator, restParamter)).isDisplayed();
    }

    public void overideGlobalTimeout(WebDriver driver, long timeout) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
    }

    public boolean isElementUndisplayed(WebDriver driver, String locator) {
        try {
            overideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
            List<WebElement> elements = getListElement(driver, locator);
            if (elements.isEmpty()) {
                System.out.println("Element not in DOM");
                return true;
            }
            return !elements.get(0).isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return true;
        } finally {
            overideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
        }
    }

    public boolean isElementUndisplayed(WebDriver driver, String locator, String... restParamter) {
        try {
            overideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
            List<WebElement> elements = getListElement(driver, castParameter(locator, restParamter));
            if (elements.isEmpty()) {
                System.out.println("Element not in DOM");
                return true;
            }
            return !elements.get(0).isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return true;
        } finally {
            overideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
        }
    }

    public boolean isElementEnabled(WebDriver driver, String locator) {
        return getElement(driver, locator).isEnabled();
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getElement(driver, locator).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locator, String... restParamter) {
        return getElement(driver, castParameter(locator, restParamter)).isSelected();
    }

    public void switchToIFrame(WebDriver driver, String locator) {
        driver.switchTo().frame(getElement(driver, locator));
    }

    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void hoverToElement(WebDriver driver, String locator) {
        new Actions(driver).moveToElement(getElement(driver, locator)).perform();
    }

    public void clickToElementByAction(WebDriver driver, String locator) {
        new Actions(driver).click(getElement(driver, locator)).perform();
    }

    public void clickAndHoldToElement(WebDriver driver, String locator) {
        new Actions(driver).clickAndHold(getElement(driver, locator)).perform();
    }

    public void releaseLeftMouse(WebDriver driver) {
        new Actions(driver).release();
    }

    public void doubleClickToElement(WebDriver driver, String locator) {
        new Actions(driver).doubleClick(getElement(driver, locator)).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator) {
        new Actions(driver).contextClick(getElement(driver, locator)).perform();
    }

    public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
        new Actions(driver).dragAndDrop(getElement(driver, sourceLocator), getElement(driver, targetLocator)).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
        new Actions(driver).sendKeys(getElement(driver, locator), key).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys key, String... restParamter) {
        new Actions(driver).sendKeys(getElement(driver, castParameter(locator, restParamter)), key).perform();
    }

    public void scrollToElement(WebDriver driver, String locator) {
        new Actions(driver).scrollToElement(getElement(driver, locator)).perform();
    }

    public void scrollToBottomPageByJS(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void hightlightElementByJS(WebDriver driver, String locator) {
        WebElement element = getElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(driver, locator));
        sleepInSecond(3);
    }

    public String getElementTextByJS(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", getElement(driver, locator));
    }

    public void scrollToElementOnTopByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
    }

    public void scrollToElementOnDownByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getElement(driver, locator));
    }

    public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getElement(driver, locator));
    }

    public void removeAttributeInDOMByJS(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
    }

    public String getAttributeInDOMByJS(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(driver, locator));
    }

    public String getElementValidationMessageByJS(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
    }

    public boolean isImageLoadedByJS(WebDriver driver, String locator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(driver, locator));
    }

    public void waitForElementVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

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

    public void waitForElementInvisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementInvisible(WebDriver driver, String locator, String... restParameter) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castParameter(locator, restParameter))));
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
        getElement(driver, BasePageUI.UPLOAD_FILE_TYPE).sendKeys(fullFileName);
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
        return PageGenerator.getUserAddressPage(driver);
    }

    public UserRegisterPO openToRegisterPage() {
        waitForElementClickable(driver, UserHomePageUI.REGISTER_LINK);
        clickToElement(driver, UserHomePageUI.REGISTER_LINK);
        return PageGenerator.getUserRegisterPage(driver);
    }

    public UserCustomerInfoPO openCustomerInfoPage() {
        waitForElementClickable(driver, UserHomePageUI.MY_ACCOUNT_LINK);
        clickToElement(driver, UserHomePageUI.MY_ACCOUNT_LINK);
        return PageGenerator.getUserCustomerPage(driver);
    }

    public UserOrderPO openOrderPage() {
        waitForElementClickable(driver, BasePageUI.ORDER_LINK);
        clickToElement(driver, BasePageUI.ORDER_LINK);
        return PageGenerator.getUserOrderPage(driver);
    }

    public UserLoginPO openLoginPage() {
        waitForElementClickable(driver, UserRegisterPageUI.LOGIN_LINK);
        clickToElement(driver, UserRegisterPageUI.LOGIN_LINK);
        return PageGenerator.getUserLoginPage(driver);
    }

    public void openAdminSite(WebDriver driver, String adminUrl) {
        openPageUrl(driver, adminUrl);
    }

    public void enterToTextBoxByID(WebDriver driver, String textboxID, String value) {
        waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxID);
        sendkeyToElement(driver, BasePageUI.TEXTBOX_BY_ID, value, textboxID);
    }

    public void clickToButtonByText(WebDriver driver, String buttonText) {
        waitForElementClickable(driver, BasePageUI.BUTTON_BY_TEXT, buttonText);
        clickToElement(driver, BasePageUI.BUTTON_BY_TEXT, buttonText);
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
}