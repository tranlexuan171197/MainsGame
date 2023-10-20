package pageUis;

public class SignUpPageUI {
    public static final String NAME_FIELD = "//input[@id='name']";
    public static final String EMAIL_FIELD = "//input[@id='email']";
    public static final String PASSWORD_FIELD = "//input[@id='password']";
    public static final String CONFIRM_PASSWORD_FIELD = "//input[@id='password_confirmation']";
    public static final String SIGN_UP_BUTTON = "//button[@type='submit']";
    public static final String LOGIN_HYPERLINK = "//a[@href='/login']";
    public static final String EMAIL_ERROR_MESSAGE="//div[@class='invalid-feedback' and contains(text(),'The email field must be a valid email.')]";
    public static final String PASSWORD_ERROR_MESSAGE = "//div[@class='invalid-feedback' and contains(text(),'The password field must be at least 8 characters.')]";
    public static final String CONFIRMATION_PASSWORD_ERROR_MESSAGE = "//div[@class='invalid-feedback' and contains(text(),'The password_confirmation field must be at least 8 characters.')]";
}
