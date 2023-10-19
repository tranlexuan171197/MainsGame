package commons;


import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BasePage {
	public static BasePage getBasePage() {
		return new BasePage();
	}

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void setAllCookies(WebDriver driver, Set<Cookie> allCookies) {
		for (Cookie cookie : allCookies) {
			driver.manage().addCookie(cookie);
		}
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void refreshToPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	//----------------------Action on Alert-------------------------------------------------
	public Alert waitForAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		alert = waitForAlertPresence(driver);
		alert.accept();
	}

	public void cancelAlert(WebDriver driver) {
		alert = waitForAlertPresence(driver);
		alert.dismiss();
	}

	public void sendKeyToAlert(WebDriver driver, String value) {
		alert = waitForAlertPresence(driver);
		alert.sendKeys(value);
	}

	public void getAlertText(WebDriver driver) {
		alert = waitForAlertPresence(driver);
		alert.getText();
	}

	//========================Switch Window===============================

	public void switchToWindowbyID(WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String windowID : allWindowIDs) {
			if (!windowID.equals(parentID)) {
				driver.switchTo().window(windowID);
				break;
			}
		}
	}

	public void switchToWindowbyTitle(WebDriver driver, String exceptedWindowTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String windowID : allWindowIDs) {
			driver.switchTo().window(windowID);
			String actualWindowTitle = driver.getTitle();
			if (actualWindowTitle.equals(exceptedWindowTitle)) {
				break;
			}
		}
	}

	public void closeAllWindowsExceptParent(WebDriver driver, String parentID) {
//		Lay ra tat ca cac ID cua window/tab dang co
		Set<String> allwindowIDs = driver.getWindowHandles();
//		duyet tung di
		for (String windowID : allwindowIDs) {
			if (!windowID.equals(parentID)) {
				driver.switchTo().window(windowID);
//				chi dong tab window dang active
				driver.close();
				sleepInSecond(1);
			}
			if (driver.getWindowHandles().size() == 1) {
				driver.switchTo().window(windowID);
				break;
			}
		}
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//====================Get/Action on Element=================
	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		String highlightStyle = "border: 2px solid red; border-style: dashed;";
		jsExecutor.executeScript("arguments[0].setAttribute(argument[1], argument[2]", element, "style", highlightStyle);
		try {
			sleepInSecond(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsExecutor.executeScript("arguments[0].setAttribute(argument[1], argument[2]", element, "style", originalStyle);
	}

	public String getDynamicLocator(String locator, String... params) {
		return String.format(locator, (Object[]) params);
	}

	public By getByXpath(String locator) {
		return By.xpath(locator);
	}

	public List<WebElement> getElements(WebDriver driver, String locator) {
		return driver.findElements(getByXpath(locator));
	}

	public WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(getByXpath(locator));
	}

	public void clickToElement(WebDriver driver, String locator) {
//		highlightElement(driver,locator);
		getElement(driver, locator).click();
	}

	public void clickToElement(WebDriver driver, String locator, String... params) {
//		highlightElement(driver,locator);
		getElement(driver, getDynamicLocator(locator, params)).click();
	}

	public void sendKeyToElement(WebDriver driver, String locator, String value) {
		getElement(driver, locator).clear();
		getElement(driver, locator).sendKeys(value);
	}

	public void sendKeyToElement(WebDriver driver, String locator, String value, String... params) {
		locator = getDynamicLocator(locator, params);
//		highlightElement(driver,locator);
		getElement(driver, locator).clear();
		getElement(driver, locator).sendKeys(value);
	}

	public void selectDropdownByText(WebDriver driver, String locator, String itemText) {
		select = new Select(getElement(driver, locator));
		select.selectByVisibleText(itemText);
	}

	public void selectDropdownByText(WebDriver driver, String locator, String itemText, String... params) {

		locator = getDynamicLocator(locator, params);
		select = new Select(getElement(driver, locator));
		select.selectByVisibleText(itemText);
	}

	public String getSelectItemDropdown(WebDriver driver, String locator) {
		select = new Select(getElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public String getSelectItemDropdown(WebDriver driver, String locator, String... params) {
		locator = getDynamicLocator(locator, params);
		select = new Select(getElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		select = new Select(getElement(driver, locator));
		return select.isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		getElement(driver, parentLocator).click();
		sleepInSecond(1);

		explicitWait = new WebDriverWait(driver, longTimeout);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("argument[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	public String getElementAttributeValue(WebDriver driver, String locator, String attributeName) {
		return getElement(driver, locator).getAttribute(attributeName);
	}

	public String getElementAttributeValue(WebDriver driver, String locator, String attributeName, String... params) {
		locator = getDynamicLocator(locator, params);
		return getElement(driver, locator).getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, String locator) {
		return getElement(driver, locator).getText();
	}

	public String getElementText(WebDriver driver, String locator, String... params) {
		locator = getDynamicLocator(locator, params);
		return getElement(driver, locator).getText();
	}

	public boolean isElementSelected(WebDriver driver, String locator) {
		return getElement(driver, locator).isSelected();
	}

	public boolean isElementSelected(WebDriver driver, String locator, String... params) {
		locator = getDynamicLocator(locator, params);
		return getElement(driver, locator).isSelected();
	}

	public void checkToCheckboxOrRadio(WebDriver driver, String locator) {
		if (!isElementSelected(driver, locator)) {
			getElement(driver, locator).click();
		}
	}

	public void checkToCheckboxOrRadio(WebDriver driver, String locator, String... params) {
		locator = getDynamicLocator(locator, params);
		if (!isElementSelected(driver, locator)) {
			getElement(driver, locator).click();
		}
	}

	public void uncheckToCheckbox(WebDriver driver, String locator) {
		if (isElementSelected(driver, locator)) {
			getElement(driver, locator).click();
		}
	}

	public void uncheckToCheckbox(WebDriver driver, String locator, String... params) {
		locator = getDynamicLocator(locator, params);
		if (isElementSelected(driver, locator)) {
			getElement(driver, locator).click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator) {
		try {
			return getElement(driver, locator).isDisplayed();
		} catch (Exception e) {
			return true;
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator, String... params) {
		locator = getDynamicLocator(locator, params);
		try {
			return getElement(driver, locator).isDisplayed();
		} catch (Exception e) {
			return true;
		}
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		System.out.println("Start time: " + new Date().toString());
		overrideGlobalTimeput(driver, shortTimeout);
		List<WebElement> elements = getElements(driver, locator);
		overrideGlobalTimeput(driver, longTimeout);
		if (elements.size() == 0) {
			System.out.println("Element not in DOM");
			System.out.println("Start time: " + new Date().toString());
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible/ displayed on UI");
			System.out.println("Start time: " + new Date().toString());
			return true;
		} else {
			System.out.println("Element in DOM is visible on UI");
			return false;
		}
	}

	public void overrideGlobalTimeput(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public boolean isElementEnabled(WebDriver driver, String locator) {
		return getElement(driver, locator).isEnabled();
	}

	public boolean isElementEnabled(WebDriver driver, String locator, String... params) {
		return getElement(driver, getDynamicLocator(locator, params)).isEnabled();
	}

	public WebDriver switchToIframeByElement(WebDriver driver, String locator) {
		return driver.switchTo().frame(getElement(driver, locator));
	}

	public WebDriver switchToDefaultContent(WebDriver driver, String locator) {
		return driver.switchTo().defaultContent();
	}

	public void hoverToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(getElement(driver, locator)).perform();
	}

	public void hoverToElement(WebDriver driver, String locator, String... params) {
		locator = getDynamicLocator(locator, params);
		action = new Actions(driver);
		action.moveToElement(getElement(driver, locator)).perform();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(getElement(driver, locator)).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(getElement(driver, locator)).perform();
	}

	public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
		action = new Actions(driver);
		action.dragAndDrop(getElement(driver, sourceLocator), getElement(driver, targetLocator)).perform();
	}

	public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(getElement(driver, locator), key).perform();
	}

	public void pressKeyToElement(WebDriver driver, String locator, Keys key, String... params) {
		locator = getDynamicLocator(locator, params);
		action = new Actions(driver);
		action.sendKeys(getElement(driver, locator), key).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String expectedText) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + expectedText + "')[0]");
		return textActual.equals(expectedText);
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight");
	}

	public void navigateToUrlByJs(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location='" + url + "'");
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click", getElement(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", getElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value','" + value + "')", getElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String loccator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "')", getElement(driver, loccator));
	}

	public boolean jQueryAJAXCallsHaveCompleted(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return (Boolean) (jsExecutor).executeScript("return(window.jQuery != null) && (jQuery.active ===0");
			}
		};
		return explicitWait.until(jQueryLoad);
	}

	public Boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return JQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth >0", getElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
	}

	public void waitForElementVisible(WebDriver driver, String locator, String... params) {
		locator = getDynamicLocator(locator, params);
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
	}

	public void waitForAllElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
	}

	public void waitForAllElementVisible(WebDriver driver, String locator, String... params) {
		locator = getDynamicLocator(locator, params);
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
	}

	public void waitForElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
	}

	public void waitForElementClickable(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(locator, params))));
	}

	public void waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
	}

	public void waitForElementInvisible(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicLocator(locator, params))));
	}

	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		String filePath = GlobalContants.UPLOAD_FOLDER_PATH;
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
	}


	private WebDriverWait explicitWait;
	private long shortTimeout = GlobalContants.SHORT_TIMEOUT;
	private long longTimeout = GlobalContants.LONG_TIMEOUT;
	private Alert alert;
	private JavascriptExecutor jsExecutor;
	private Select select;
	private Actions action;
}