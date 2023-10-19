package com.project.function;

import commons.BasePage;
import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
import pageObjects.LandingPageObject;
import pageObjects.SignInPageObject;
import pageObjects.SignUpPageObject;
import pageUis.SelectMenuPageUI;

public class Test_Case_Example_I extends BaseTest {
	WebDriver driver;
	LandingPageObject landingPage;
	SignInPageObject signInPage;

	SignUpPageObject signUpPage;

	String email ="abc@gmail.com";
	String password ="123456";

	@Parameters({"browser", "url"})
	@BeforeTest
//	@BeforeClass
	public void initBrowser(String browser, String url) {
		log.info("Pre condition: Open Browser: " + browser + "and navigate to url:" + url);
		driver = getBrowserDriver(browser, url);
		driver.manage().window().maximize();
	}
	@Test(priority = 1)
	public void TC_Sign_Up_To_Eklipse(){
		String email ="abc@gmail.com";
		String password ="123456";
		String confirmPassword = "123456";
		String name = "abc";
		log.info("try to click to Sigin at Landing Page");
		landingPage = new LandingPageObject(driver);
		landingPage.clickToSignUpButton();

		log.info("Input to Email, Password and Sign In");
		signUpPage = new SignUpPageObject(driver);
		signUpPage.SignUnToEklipse(name,email,password,confirmPassword);


	}
	@Test(priority = 2)
	public void TC_Sign_In_To_Eklipse(){
		String email ="abc@gmail.com";
		String password ="123456";


		log.info("try to click to Sign Up at Landing Page");
		landingPage = new LandingPageObject(driver);
		landingPage.clickToSignInButton();

		log.info("Input to Email, Password and Sign In");
		signInPage = new SignInPageObject(driver);
		signInPage.SignInToEklipse(email,password);

	}

	@AfterClass
	public void cleanBrowser(){
		cleanBrowserAndDriver();
	}
}
