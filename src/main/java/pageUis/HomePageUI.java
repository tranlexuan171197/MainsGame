package pageUis;

public class HomePageUI {
    public static final String WELCOME_POP_UP_TITLE = "//div[@class='question-container']//h3";
    public static final String WELCOME_POP_UP_DESC = "//div[@class='question-container']//p[@class='text-center']";
    public static final String WELCOME_POP_UP_CONTINUE_BUTTON = "//div[@class='question-container']//div//button[@id='btn-onboarding']";
    public static final String CLOSE_POP_UP_BUTTON ="//div[@class='modal-content']//button[@class='btn btn-close']";

    public static final String PROFILE = "//ul[@class='nav-account nav']//p[contains(text(),'Profile')]";

    public static final String ACCOUNT_SETTING = "//ul[@class='nav-account nav']//div//span[contains(text(),'Account Settings')]/parent::button";
}