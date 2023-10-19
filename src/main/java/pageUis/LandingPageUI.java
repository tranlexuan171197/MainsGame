package pageUis;

public class LandingPageUI {
    public static final String HEADER_MENU = "//ul[@class='primary-menu']//li//span[contains(text(),'%')]//parent::a";
    public static final String SIGN_IN_BUTTON = "//div[contains(@class,'header-main__col--right')]//a[contains(text(),'Sign In')]";
    public static final String SIGN_UP_BUTTON = "//div[contains(@class,'header-main__col--right')]//a[@class='btn btn-register']";
    public static final String WELCOME_BAR ="//div[@class='mysticky-welcomebar-fixed-wrap']";
    public static final String CLOSE_WELCOME_BAR_BTN = "//div[@class='mysticky-welcomebar-fixed-wrap']//span[contains(text(),'X')]";


    //div[@class='mysticky-welcomebar-fixed-wrap']/parent::div[contains(@style,'display: none')]
}
