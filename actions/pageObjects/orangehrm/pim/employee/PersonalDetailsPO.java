package pageObjects.orangehrm.pim.employee;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import pageUIs.orangehrm.pim.employee.PersonalDetailsPUI;

public class PersonalDetailsPO extends EmployeeTabs {
    private WebDriver driver;

    public PersonalDetailsPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public Dimension getAvatarSize() {
        return getElementSize(driver, PersonalDetailsPUI.EMPLOYEE_IMAGE);
    }

    public void clickToEmployeeAvatar() {
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
        Dimension afterUpload = getAvatarSize();
        return beforeUpload.equals(afterUpload);
    }
}