package interfaces.pageUIs.nopCommerce;

public class BasePageUI {
    //NopCommerce
    public static final String REWARD_POINT_LINK = "xpath=//div[contains(@class,'block-account-navigation')]//a[text()='Reward points']";
    public static final String CUSTOMER_INFO_LINK = "xpath=//div[contains(@class,'block-account-navigation')]//a[text()='Customer Info']";
    public static final String ADDRESS_LINK = "xpath=//div[contains(@class,'block-account-navigation')]//a[text()='Addresses']";
    public static final String ORDER_LINK = "xpath=//div[contains(@class,'block-account-navigation')]//a[text()='Order']";
    //JQuery
    public static final String UPLOAD_FILE_TYPE = "css=input[type='file']";

    //Component
    public static final String TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
    public static final String CHECKBOX_BY_ID = "xpath=//input[@id='%s']";
    public static final String RADIO_BY_ID = "xpath=//input[@id='%s']";
    public static final String BUTTON_BY_TEXT = "xpath=//button[text()='%s']";
}