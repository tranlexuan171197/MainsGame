package pageUis;

public class SignInPageUI {

    public static final String EMAIL_FIELD = "//input[@id='username']";
    public static final String PASSWORD_FIELD = "//input[@id='password']";
    public static final String SIGN_IN_BUTTON = "//button[@type='submit']";
    public static final String SIGN_UP_HYPERLINK = "//a[contains(text(),'Sign Up')]";
    public static final String FORGOT_PASSWORD_HYPERLINK = "//a[contains(text(),'Forgot your password?')]";

    public static final  String TWITCH_BUTTON = "//button[contains(@class,'btn-twitch')]";
    public static final  String GOOGLE_BUTTON = "//button[contains(@class,'btn-google')]";
    public static final  String FACEBOOK_BUTTON = "//button[contains(@class,'login-facebook')]";

    public static final String LOGIN_FAIL_TITLE_MESSAGE = "//h2[@class='swal2-title']";
    public static final String LOGIN_FAIL_DESC_MESSAGE = "//div[@class='swal2-html-container']";
    public static final String CLOSE_LOGIN_FAIL_WARNING_BUTTON = "//button[contains(text(),'Continue')]";

}
