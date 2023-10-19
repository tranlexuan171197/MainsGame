package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUis.SignInPageUI;

public class SignInPageObject extends BasePage {
    WebDriver driver;

    public SignInPageObject (WebDriver driver){

        this.driver = driver;
    }
    /**
     * Input value to Email field
     * @param email input email address
     */
    public void sendKeyToEmailField(String email){
        waitForElementVisible(driver, SignInPageUI.EMAIL_FIELD);
        sendKeyToElement(driver, SignInPageUI.EMAIL_FIELD,email);
    }

    /**
     * Input value to Password field
     * @param password input Password
     */
    public void sendKeyToPasswordField(String password){
        waitForElementVisible(driver, SignInPageUI.PASSWORD_FIELD);
        sendKeyToElement(driver, SignInPageUI.PASSWORD_FIELD,password);
    }

    public void clickToSignInButton(){
        waitForElementClickable(driver, SignInPageUI.SIGN_IN_BUTTON);
        clickToElement(driver, SignInPageUI.SIGN_IN_BUTTON);
    }

    public void SignInToEklipse(String email, String password){
        sendKeyToEmailField(email);
        sendKeyToPasswordField(password);
        clickToSignInButton();
    }
}
