package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUis.AccountSettingPageUI;

public class AccountSettingPage extends BasePage {
    WebDriver driver;

    public AccountSettingPage (WebDriver driver){

        this.driver = driver;
    }

    public String getAccountSettingTitle(){
        return getElementText(driver, AccountSettingPageUI.ACCOUNT_SETTING_TITLE);
    }
}
