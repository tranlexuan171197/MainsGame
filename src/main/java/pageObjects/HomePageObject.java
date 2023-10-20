package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUis.HomePageUI;

public class HomePageObject extends BasePage {

    WebDriver driver;

    public HomePageObject(WebDriver driver){
        this.driver = driver;
    }

    public String getUrl(){
        return getPageUrl(driver);
    }

    public void clickToProfile(){
        waitForElementClickable(driver, HomePageUI.PROFILE);
        clickToElement(driver, HomePageUI.PROFILE);
    }

    public void clickToProfileOption(){
        waitForElementClickable(driver, HomePageUI.ACCOUNT_SETTING);
        clickToElement(driver, HomePageUI.ACCOUNT_SETTING);
    }

}
