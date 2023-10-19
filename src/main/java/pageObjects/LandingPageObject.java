package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUis.LandingPageUI;

public class LandingPageObject extends BasePage {
    WebDriver driver;

    public LandingPageObject(WebDriver driver){
        this.driver = driver;
    }

    public void clickToSignInButton(){
        waitForElementClickable(driver, LandingPageUI.SIGN_IN_BUTTON);
        clickToElement(driver, LandingPageUI.SIGN_IN_BUTTON);
    }

    public void clickToSignUpButton(){
        waitForElementClickable(driver, LandingPageUI.SIGN_UP_BUTTON);
        clickToElement(driver, LandingPageUI.SIGN_UP_BUTTON);
    }


}
