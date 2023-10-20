package com.project.function;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.HomePageObject;
import pageObjects.LandingPageObject;
import pageObjects.SignInPageObject;
import pageObjects.SignUpPageObject;

public class Test_Case_Sign_In extends BaseTest {
	WebDriver driver;
	LandingPageObject landingPage;
	SignInPageObject signInPage;
	HomePageObject homePage;
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
	public void TC_Sign_In_To_Eklipse(){
		String email ="tes123@gmail.com";
		String password ="12345678";

		log.info("Sendkey  to Email Field");
		signInPage = new SignInPageObject(driver);
		signInPage.sendKeyToEmailField(email);

		log.info("Sendkey  to Password Field");
		signInPage.sendKeyToPasswordField(password);

		log.info("Click to Sign In button");
		homePage = signInPage.clickToSignInButton();

		String actualHomePageURL = homePage.getUrl();
		String expectedHomepageURL = actualHomePageURL;

		verifyEquals(actualHomePageURL,expectedHomepageURL);


	}

	@AfterClass
	public void cleanBrowser(){
		cleanBrowserAndDriver();
	}
}