package com.project.function;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.AccountSettingPage;
import pageObjects.HomePageObject;
import pageObjects.LandingPageObject;
import pageObjects.SignInPageObject;

public class Test_Case_Account_Setting extends BaseTest {
    WebDriver driver;

    LandingPageObject landingPage;
    SignInPageObject signInPage;
    HomePageObject homePage;

    AccountSettingPage accountSettingPage;
    String email ="test123@gmail.com";
    String password ="12345678";

    @Parameters({"browser", "url"})
    @BeforeClass
    public void initBrowser(String browser, String url) {
        log.info("Pre condition: Open Browser: " + browser + " and navigate to url: " + url);
        driver = getBrowserDriver(browser, url);
        driver.manage().window().maximize();

        log.info("try to click to Sign Up at Landing Page");
        landingPage = new LandingPageObject(driver);
        signInPage = landingPage.clickToSignInButton();
        log.info("Sign In");
        homePage = signInPage.SignInToEklipse(email,password);
    }

    @Test(priority = 1)
    public void TC_Click_To_Account_Setting(){

        log.info("select Profile => Account Setting");
        homePage.clickToProfile();
        homePage.clickToProfileOption();

        log.info("Verify page Accoutn Setting is displayed");
        accountSettingPage = new AccountSettingPage(driver);
        String actual = accountSettingPage.getAccountSettingTitle();
        String expect ="Account Setting";

        verifyEquals(actual,expect);
    }
}
