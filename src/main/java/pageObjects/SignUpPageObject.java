package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUis.SignInPageUI;
import pageUis.SignUpPageUI;

public class SignUpPageObject extends BasePage {
    WebDriver driver;

    public SignUpPageObject(WebDriver driver){

        this.driver = driver;
    }

    public void sendKeyToNameField(String name){
        waitForElementVisible(driver, SignUpPageUI.NAME_FIELD);
        sendKeyToElement(driver, SignUpPageUI.NAME_FIELD,name);
    }


    /**
     * Input value to Email field
     * @param email input email address
     */
    public void sendKeyToEmailField(String email){
        waitForElementVisible(driver, SignUpPageUI.EMAIL_FIELD);
        sendKeyToElement(driver, SignUpPageUI.EMAIL_FIELD,email);
    }

    /**
     * Input value to Password field
     * @param password input Password
     */
    public void sendKeyToPasswordField(String password){
        waitForElementVisible(driver, SignUpPageUI.PASSWORD_FIELD);
        sendKeyToElement(driver, SignUpPageUI.PASSWORD_FIELD,password);
    }
    public void sendKeyToConfirmPasswordField(String confirmPassword){
        waitForElementVisible(driver, SignUpPageUI.CONFIRM_PASSWORD_FIELD);
        sendKeyToElement(driver, SignUpPageUI.CONFIRM_PASSWORD_FIELD,confirmPassword);
    }

    public void clickToSignInButton(){
        waitForElementClickable(driver, SignUpPageUI.SIGN_UP_BUTTON);
        clickToElement(driver, SignUpPageUI.SIGN_UP_BUTTON);
    }

    public void SignUnToEklipse(String name,String email, String password, String confirmPassword){
        sendKeyToNameField(name);
        sendKeyToEmailField(email);
        sendKeyToPasswordField(password);
        sendKeyToConfirmPasswordField(confirmPassword);
        clickToSignInButton();
    }
}
