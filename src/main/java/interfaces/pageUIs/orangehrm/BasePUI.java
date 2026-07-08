package interfaces.pageUIs.orangehrm;

public class BasePUI {
    public static final String LOADING_SPINNER_ICON = "css=div.oxd-loading-spinner";
    public static final String DYNAMIC_TEXTBOX_BY_LABEL = "xpath=//label[text()=\"%s\"]/parent::div/following-sibling::div//input";
    public static final String DYNAMIC_TEXBOX_BY_NAME = "xpath=//input[@name='%s']";
    public static final String DYNAMIC_BUTTON_BY_TEXT = "xpath=//button[contains(string(),'%s')]";
    public static final String DYNAMIC_BUTTON_BY_MAIN_TITLE = "xpath=//h6[text()='%s']/following-sibling::button[contains(string(),'%s')]";
    public static final String DYNAMIC_TOAST_MESSAGE_BY_TEXT = "xpath=//p[contains(@class,'oxd-text--toast-message') and text()='%s']";
    public static final String DYNAMIC_MODULE_BY_TEXT_IN_MENU_ITEM = "xpath=//a[contains(@class,'oxd-main-menu-item')]//span[text()='%s']";
    public static final String DYNAMIC_PARENT_DROPDOWN_BY_LABEL = "xpath=//label[text()='%s']/parent::div/following-sibling::div//i";
    public static final String DYNAMIC_CHILD_DROPDOWN_BY_LABEL = "xpath=//label[text()='%s']/parent::div/following-sibling::div//div[@class='oxd-select-option']/span";
    public static final String DYNAMIC_RADIO_BUTTON_BY_LABEL = "xpath=//label[text()='%s']/span";
    public static final String DYNAMIC_CHECKBOX_BY_LABEL = "xpath=//p[text()='%s']/following-sibling::div//span";
    public static final String USER_DROPDOWN = "css=p.oxd-userdropdown-name";
    public static final String LOGOUT_LINK = "xpath=//a[@class='oxd-userdropdown-link' and text()='Logout']";
    public static final String DYNAMIC_TEXTAREA_BY_LABEL = "xpath=//label[text()='Comment']/parent::div/following-sibling::div/textarea";
    public static final String DYNAMIC_SELECT_CHECKBOX_BY_FILE_NAME = "xpath=//div[text()='%s']/parent::div/preceding-sibling::div//span";
    public static final String DELETE_ACTION_BY_FILE_NAME = "xpath=//div[text()='%s']/parent::div/following-sibling::div//i[contains(@class,'bi-trash')]";
    public static final String DYNAMIC_SELECT_ALL_CHECKBOX_BY_FIRST_COLUMN_NAME = "xpath=//div[text()='%s']/parent::div//span";
    public static final String CONFIRMATION_POPUP = "xpath=//p[text()='%s']/parent::div/following-sibling::div/p[text()='%s']";
    public static final String DYNAMIC_EMERGENCY_CONTACT_VALUES_IN_ROW_BY_FIRST_COLUMN = "xpath=//div[@role='row' and .//div[text()='%s']]//div[@role='cell' and not(.//div[contains(@class,'oxd-table-cell-actions')]) and not(.//div[contains(@class,'oxd-checkbox-wrapper')])]";
}

