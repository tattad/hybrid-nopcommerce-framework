package pageUIs.jquery;

public class HomePageUI {
    public static final String DYNAMIC_PAGE_LINK = "XPATH=//li[@class='qgrd-pagination-page']/a[text()='%s']";
    public static final String DYNAMIC_TEXTBOX_BY_HEADER_NAME = "XPATH=//div[@class='qgrd-header-text' and text()='%s']//parent::div//following-sibling::input";
    public static final String DYNAMIC_DATA_ROW = "XPATH=//td[@data-key='females' and text()='%s']//following-sibling::" +
                                                          "td[@data-key='country' and text()='%s']//following-sibling::" +
                                                          "td[@data-key='males' and text()='%s']//following-sibling::" +
                                                          "td[@data-key='total' and text()='%s']";
    public static final String IFRAME_GOOGLE_AD = "css=ins.adsbygoogle-noablate div#aswift_2_host iframe";
    public static final String GOOGLE_AD_CLOSE_BUTTON = "css=div#dismiss-button-element";
    public static final String DYNAMIC_REMOVE_BUTTON_BY_COUNTRY_NAME = "xpath=//td[@data-key='country' and text()='%s']//preceding-sibling::td[@class='qgrd-actions']/button[@class='qgrd-remove-row-btn']";
    public static final String DYNAMIC_EDIT_BUTTON_BY_COUNTRY_NAME = "xpath=//td[@data-key='country' and text()='%s']//preceding-sibling::td[@class='qgrd-actions']/button[@class='qgrd-edit-row-btn']";
}