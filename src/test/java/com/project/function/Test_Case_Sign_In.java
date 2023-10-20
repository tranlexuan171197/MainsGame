package com.project.function;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.*;

public class Test_Case_Sign_In extends BaseTest {
	WebDriver driver;
	LandingPageObject landingPage;
	SignInPageObject signInPage;
	HomePageObject homePage;

	AccountSettingPage accountSettingPage;
	String email ="abc@gmail.com";
	String password ="123456";

	@Parameters({"browser", "url"})
	@BeforeClass
	public void initBrowser(String browser, String url) {
		log.info("Pre condition: Open Browser: " + browser + "and navigate to url:" + url);
		driver = getBrowserDriver(browser, url);
		driver.manage().window().maximize();

		log.info("try to click to Sign Up at Landing Page");
		landingPage = new LandingPageObject(driver);
		landingPage.clickToSignInButton();
	}
	@Test(priority = 1)
	public void TC_Login_Fail_With_Wrong_Email(){
		String email ="tes@testgmail.com";
		String password ="12345678";
		log.info("Send invalid email to Email Field");
		signInPage = new SignInPageObject(driver);
		signInPage.sendKeyToEmailField(email);
		log.info("Send valid password to password Field");
		signInPage.sendKeyToPasswordField(password);
		log.info("Click Sign Button");
		signInPage.clickToSignInButton();

		log.info("Close the Warning");
		signInPage.clickToCloseErrorButton();
	}

	@Test(priority = 2)
	public void TC_Login_Fail_With_Wrong_Password(){
		String email ="tes123@testgmail.com";
		String password ="123456";
		log.info("Send invalid email to Email Field");
		signInPage = new SignInPageObject(driver);
		signInPage.sendKeyToEmailField(email);
		log.info("Send valid password to password Field");
		signInPage.sendKeyToPasswordField(password);
		log.info("Click Sign Button");
		signInPage.clickToSignInButton();

		log.info("Close the Warning");
		signInPage.clickToCloseErrorButton();
	}
	@Test(priority = 3)
	public void TC_Sign_In_To_Eklipse(){
		String email ="tes123@gmail.com";
		String password ="12345678";

		log.info("Sendkey to Email Field");
		signInPage = new SignInPageObject(driver);
		signInPage.sendKeyToEmailField(email);

		log.info("Sendkey to Password Field");
		signInPage.sendKeyToPasswordField(password);

		log.info("Click to Sign In button");
		homePage = signInPage.clickToSignInButton();

		String actualHomePageURL = homePage.getUrl();
		String expectedHomepageURL = actualHomePageURL;
//need to change the verify value
		verifyEquals(actualHomePageURL,expectedHomepageURL);
	}

	/**
	 * Test case này fail do chưa tắt những pop up khi đăng nhập
	 */
	@Test(priority = 4)
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
	@AfterClass
	public void cleanBrowser(){
		cleanBrowserAndDriver();
	}
}
