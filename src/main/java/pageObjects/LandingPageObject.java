package pageObjects;

import commons.BasePage;
import commons.PageGenerateManager;
import org.openqa.selenium.WebDriver;
import pageUis.LandingPageUI;

public class LandingPageObject extends BasePage {
    WebDriver driver;

    public LandingPageObject(WebDriver driver){
        this.driver = driver;
    }

    public SignInPageObject clickToSignInButton(){
        waitForElementClickable(driver, LandingPageUI.SIGN_IN_BUTTON);
        clickToElement(driver, LandingPageUI.SIGN_IN_BUTTON);
        return  PageGenerateManager.getSigninPage(driver);
    }

    public SignUpPageObject clickToSignUpButton(){
        waitForElementClickable(driver, LandingPageUI.SIGN_UP_BUTTON);
        clickToElement(driver, LandingPageUI.SIGN_UP_BUTTON);
        return PageGenerateManager.getSignupPage(driver);
    }


}
