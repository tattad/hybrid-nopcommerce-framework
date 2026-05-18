package pageUIs.user;

public class UserHomePageUI {

    //public: gọi hàm/ biến ra sử dụng bình thường
    //private/ default: khác package không dùng được
    //protected: các class bên PO không kế thừa PUI nên không áp dụng

    //static: cho phép gọi trực tiếp từ class
    //final: ngăn việc update lại giá trị trong quá trình chạy
    //String: vì cái By Locator của Selenium đều nhận vào String
    //REGISTER_LINK: static final để quy ước 1 biến là Hằng số
    //Convention cho hằng số: phải viết hoa - nhiều hơn 1 từ thì phải dùng dấu _ để phân tách

    public static final String MY_ACCOUNT_LINK = "xpath=//a[@class='ico-account']";
    public static final String LOGIN_LINK = "xpath=//a[@class='ico-login']";
    public static final String REGISTER_LINK = "xpath=//a[@class='ico-register']";
}