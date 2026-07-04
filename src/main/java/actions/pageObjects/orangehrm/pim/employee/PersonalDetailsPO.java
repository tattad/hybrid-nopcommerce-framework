package actions.pageObjects.orangehrm.pim.employee;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import interfaces.pageUIs.orangehrm.pim.employee.PersonalDetailsPUI;

public class PersonalDetailsPO extends EmployeeTabs {
    private WebDriver driver;

    public PersonalDetailsPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public Dimension getProfileNaturalImageSize() {
        waitForElementVisible(driver, PersonalDetailsPUI.EMPLOYEE_IMAGE);
        int x = Integer.parseInt(getElementDOMProperty(driver, PersonalDetailsPUI.EMPLOYEE_IMAGE, "naturalWidth"));
        int y = Integer.parseInt(getElementDOMProperty(driver, PersonalDetailsPUI.EMPLOYEE_IMAGE, "naturalHeight"));
        return new Dimension(x, y);
    }

    public void clickToProfileImage() {
        waitForElementClickable(driver, PersonalDetailsPUI.EMPLOYEE_IMAGE);
        clickToElement(driver, PersonalDetailsPUI.EMPLOYEE_IMAGE);
    }

    public void clickToSaveButtonAtChangeProfilePictureContainer() {
        waitForElementClickable(driver, PersonalDetailsPUI.SAVE_BUTTON_AT_CHANGE_PROFILE_PICTURE_CONTAINER);
        clickToElement(driver, PersonalDetailsPUI.SAVE_BUTTON_AT_CHANGE_PROFILE_PICTURE_CONTAINER);
    }

    public boolean isSuccessMessageDisplayed() {
        waitForElementVisible(driver, PersonalDetailsPUI.SUCCESS_MESSAGE);
        return isElementDisplayed(driver, PersonalDetailsPUI.SUCCESS_MESSAGE);
    }

    public boolean isProfileAvatarUpdateSuccess(Dimension beforeUpload) {
        Dimension afterUpload = getProfileNaturalImageSize();
        return beforeUpload.equals(afterUpload);
    }

    public String getErrorMessageAtProfileImage() {
        waitForElementVisible(driver, PersonalDetailsPUI.PROFILE_IMAGE_UPLOAD_ERROR_MSG);
        return getElementText(driver, PersonalDetailsPUI.PROFILE_IMAGE_UPLOAD_ERROR_MSG);
    }
}