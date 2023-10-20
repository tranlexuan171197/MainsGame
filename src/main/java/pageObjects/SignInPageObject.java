package pageObjects;

import commons.BasePage;
import commons.PageGenerateManager;
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

    public HomePageObject clickToSignInButton(){
        waitForElementClickable(driver, SignInPageUI.SIGN_IN_BUTTON);
        clickToElement(driver, SignInPageUI.SIGN_IN_BUTTON);
        return PageGenerateManager.getHomePage(driver);
    }

    public HomePageObject SignInToEklipse(String email, String password){
        sendKeyToEmailField(email);
        sendKeyToPasswordField(password);
        return clickToSignInButton();
    }

    public String getErrorTitle(){
        return getElementText(driver,SignInPageUI.LOGIN_FAIL_TITLE_MESSAGE);
    }

    public void clickToCloseErrorButton(){
        waitForElementClickable(driver, SignInPageUI.CLOSE_LOGIN_FAIL_WARNING_BUTTON);
        clickToElement(driver, SignInPageUI.CLOSE_LOGIN_FAIL_WARNING_BUTTON);
    }

}
