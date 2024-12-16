package com.tonik.utility;

import com.driverInstance.DriverInstance;
import com.driverInstance.DriverManager;
import com.google.common.collect.Ordering;
import com.tonik.pageObject.OnBoardingPage;
import com.tonik.propsreader.PropertyFileReader;
import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Utilities extends ExtentReporter {

	public Utilities() {
	}
	/**
	 * Time out
	 */
	private static int timeout;
	/**
	 * Retry Count
	 */
	private static int retryCount;
	public static SoftAssert softAssert = new SoftAssert();
	@SuppressWarnings("rawtypes")
	public static TouchAction touchAction;
	public static boolean relaunch = true;
	public static String CTUserName;
	public static String CTPWD;
	public static String setPlatform = "";
	/**
	 * The Constant logger.
	 */
	public static LoggingUtils logger = new LoggingUtils();

	/**
	 * The Android driver.
	 */
	public AndroidDriver<AndroidElement> androidDriver;
	/**
	 * window handler
	 */
	static ArrayList<String> win = new ArrayList<>();
	/**
	 * The Android driver.
	 */
	public IOSDriver<WebElement> iOSDriver;
	public static int getTimeout() {
		return 30;
	}
	public static WebDriver getWebDriver() {
		return DriverInstance.tlWebDriver.get();
	}
	public static int getRetryCount() {
		return retryCount;
	}
	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}
	public static AppiumDriver<WebElement> getDriver() {
		return DriverInstance.tlDriver.get();
	}
	public static String getPlatform() {
		return DriverInstance.getPlatform();
	}
	public void setPlatform(String Platform) {
		DriverInstance.setPlatfrom(Platform);
	}
	static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Actions action;
	public static void initDriver() {
		if (getPlatform().equals("Web")) {
			wait = new WebDriverWait(getWebDriver(), 600);
			js = (JavascriptExecutor) getWebDriver();
			action = new Actions(getWebDriver());
			getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		} else if (getPlatform().equals("Android") || getPlatform().equals("MPWA")|| getPlatform().equals("BrowserStack") || getPlatform().equals("IOSBrowserStack") || getPlatform().equals("iOS")) {
			wait = new WebDriverWait(DriverManager.getAppiumDriver(), 600);
			js = (JavascriptExecutor) DriverManager.getAppiumDriver();
			action = new Actions(DriverManager.getAppiumDriver());
			DriverManager.getAppiumDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
	}
	public static void javaScriptExecutor() {
		if (getPlatform().equals("Web")) {
			JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();
		} else if (getPlatform().equals("Android") || getPlatform().equals("MPWA") || getPlatform().equals("TV")) {
		}
	}
	public static void setScreenshotSource() {
		if (getPlatformFromtools().equalsIgnoreCase("Web")) {
			src = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
		} else if (getPlatformFromtools().equals("Android") || getPlatformFromtools().equals("PWA")|| getPlatformFromtools().equals("TV") || getPlatformFromtools().equals("iOS")) {
			src = ((TakesScreenshot) DriverManager.getAppiumDriver())
					.getScreenshotAs(org.openqa.selenium.OutputType.FILE);
		} else if (getPlatformFromtools().equalsIgnoreCase("MPWA")) {
			src = ((TakesScreenshot) DriverManager.getAppiumDriver())
					.getScreenshotAs(org.openqa.selenium.OutputType.FILE);
		}
	}
	public static boolean JSClick(By byLocator, String text) throws Exception {
		try {
			js = (JavascriptExecutor) getWebDriver();
			js.executeScript("arguments[0].click();", getWebDriver().findElement(byLocator));
			logger.info("" + text + " " + " is clicked");
			ExtentReporter.extentLoggerPass("checkElementPresent", "" + text + " is clicked");
			return true;
		} catch (Exception e) {
			logger.error(text + " " + " is not clicked");
			ExtentReporter.extentLoggerFail("checkElementNotPresent", "" + text + " is not clicked");
			ExtentReporter.screencapture();
			return false;
		}
	}
	public static WebElement findElement(By byLocator) throws Exception {
		WebElement element = null;
		if (getPlatform().equalsIgnoreCase("Web")) {
			element = getWebDriver().findElement(byLocator);
			return element;
		} else if (getPlatform().equals("MPWA") || getPlatform().equals("Android") || getPlatform().equals("iOS")) {
			element = DriverManager.getAppiumDriver().findElement(byLocator);
			return element;
		}
		return element;
	}
	public void setWifiConnectionToONOFF(String Value) {
		try {
			if (Value.equalsIgnoreCase("On")) {
				String cmd = "adb shell svc wifi enable";
				Runtime.getRuntime().exec(cmd);
				waitTime(5000);
				logger.info("Wifi Data toggle is Switched On");
				ExtentReporter.extentLoggerPass("Wifi Toggle", "Wifi Data toggle is Switched On");
			} else if (Value.equalsIgnoreCase("Off")) {
				String cmd = "adb shell svc wifi disable";
				Runtime.getRuntime().exec(cmd);
				waitTime(3000);
				logger.info("Wifi Data toggle is Switched Off");
				ExtentReporter.extentLoggerPass("Wifi Toggle", "Wifi Data toggle is Switched Off");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * wait until element is displayed.
	 * @param
	 * @return true, if successful
	 */
	public static boolean waitForElementDisplayed(By byLocator, int iTimeOut) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
			return false;
		} catch (Exception e) {
			return true;
		}
	}
	/**
	 * Check element not present.
	 * @param byLocator the by locator
	 * @return true, if successful
	 */
	public static boolean verifyElementNotPresent(By byLocator, int iTimeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getAppiumDriver(), iTimeOut);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
			return false;
		} catch (NoSuchElementException e) {
			return true;
		}
	}
	/*
	 * For avoiding the stale element expception
	 */
	public static boolean waitforelementtoappear(By locator, int time) {
		WebDriverWait wait = new WebDriverWait(getDriver(), time);
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(locator));
		return true;
	}
	public static List<WebElement> staleexception_Click(By locator) {
		List<WebElement> outcome = null;
		int repeat = 0;
		while (repeat <= 6) {
			try {
				List<WebElement> ele = DriverManager.getDriver().findElements(locator);
				break;
			} catch (StaleElementReferenceException e) {
				e.printStackTrace();
			}
			repeat++;
		}
		return outcome;
	}
	/**
	 * Check element present.
	 * @param byLocator the by locator
	 * @return true, if successful
	 */
	public static boolean verifyElementPresent(By byLocator, String validationtext) throws Exception {
		if (isPresentWithWait(byLocator)) {
			logger.info(validationtext + " is displayed");
			ExtentReporter.extentLoggerPass("checkElementPresent", validationtext + " is displayed");
			return true;
		} else {
			logger.error(validationtext + " is not displayed");
			ExtentReporter.extentLoggerFail("checkElementPresent", validationtext + " is not displayed");
			return false;
		}
	}
	public static boolean verifyElementPresentOld(By byLocator, String validationtext) throws Exception {
		if (isPresentWithWait(byLocator)) {
			WebElement element = findElement(byLocator);
			softAssert.assertEquals(element.isDisplayed(), true, "" + validationtext + " " + " is displayed");
			logger.info(validationtext + " is displayed");
			ExtentReporter.extentLoggerPass("checkElementPresent", validationtext + " is displayed");
			return true;
		} else {
			softAssert.assertEquals(false, true, validationtext + " " + " is not displayed");
			logger.info(validationtext + " is not displayed");
			ExtentReporter.extentLoggerFail("checkElementPresent", validationtext + " is not displayed");
			return false;
		}
	}
	public static boolean verifyElementExist(By byLocator, String str) throws Exception {
		WebElement element;
		try {
			if(getPlatform().equalsIgnoreCase("web")) {
				element = getWebDriver().findElement(byLocator);
				explicitWaitVisibility(byLocator, 20);
				if (element.isDisplayed()) {
					softAssert.assertEquals(element.isDisplayed(), true, "" + str + " " + " is displayed");
					ExtentReporter.extentLoggerPass("checkElementPresent", "" + str + " is displayed");
					logger.info("" + str + " is displayed");
					return true;
				}
			} else if(getPlatform().equalsIgnoreCase("MPWAiOSSafari")){
				boolean flag = false;
				for(int i=0;i<10;i++){
					try {
						DriverManager.getAppiumDriver().findElement(byLocator).isDisplayed();
						flag=true;
						break;
					} catch (Exception e) {
						Thread.sleep(1000);
					}
				}
				if(flag){
					ExtentReporter.extentLoggerPass("checkElementPresent", "" + str + " is displayed");
					logger.info("" + str + " is displayed");
				} else {
					ExtentReporter.extentLoggerFail("checkElementPresent", "" + str + " is not displayed");
					logger.error("\u001B[31m" + str + " is not displayed" + "\u001B[0m");
				}
				return flag;
			} else{
				element = DriverManager.getAppiumDriver().findElement(byLocator);
				explicitWaitVisibility(byLocator, 20);
				if (element.isDisplayed()) {
					softAssert.assertEquals(element.isDisplayed(), true, "" + str + " " + " is displayed");
					ExtentReporter.extentLoggerPass("checkElementPresent", "" + str + " is displayed");
					logger.info("" + str + " is displayed");
					return true;
				}
			}
		} catch (Exception e) {
			softAssert.assertEquals(false, true, str + " " + " is not displayed");
			ExtentReporter.extentLoggerFail("checkElementPresent", "" + str + " is not displayed");
			logger.error("\u001B[31m" + str + " is not displayed" + "\u001B[0m");
			return false;
		}
		return true;
	}
	/**
	 * boolean return type for conditions
	 * @param byLocator
	 * @return
	 * @throws Exception
	 */
	public static boolean verifyElementDisplayed(By byLocator) throws Exception {
		if (platform.equalsIgnoreCase("web")) {
			try {
				WebElement element = DriverManager.getDriver().findElement(byLocator);
				if (element.isDisplayed()) {
					return true;
				}
			} catch (Exception e) {
				return false;
			}
		} else if (platform.equalsIgnoreCase("Android") || platform.equalsIgnoreCase("BrowserStack")|| platform.equalsIgnoreCase("IOSBrowserStack")) {
			try {
				WebElement element = DriverManager.getAppiumDriver().findElement(byLocator);
				if (element.isDisplayed()) {
					return true;
				}
			} catch (Exception e) {
				return false;
			}
		} else if (platform.equalsIgnoreCase("iOS")) {
			try {
				WebElement element = DriverManager.getAppiumDriver().findElement(byLocator);
				if (element.isDisplayed()) {
					return true;
				}
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}
	public static boolean checkElementExist(By byLocator, String str) throws Exception {
		try {
			getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			List<WebElement> list = getDriver().findElements(byLocator);
			getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if (list.size() == 0) {
				ExtentReporter.extentLogger("checkElementPresent", "" + str + " is not displayed");
				logger.info("" + str + " is not displayed");
				return false;
			} else {
				ExtentReporter.extentLogger("checkElementPresent", "" + str + " is displayed");
				logger.info("" + str + " is displayed");
				return list.get(0).isDisplayed();
			}
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * Check element present and click.
	 * @param byLocator the by locator
	 * @return true, if successful
	 */
	public static boolean verifyElementPresentAndClick(By byLocator, String validationtext) throws Exception {
		String platform = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("Platform");
		WebElement element = null;
		try {
			if (platform.equalsIgnoreCase("web")) {
				element = getWebDriver().findElement(byLocator);
			} else if (platform.equalsIgnoreCase("Android") || platform.equalsIgnoreCase("BrowserStack")|| platform.equalsIgnoreCase("IOSBrowserStack") || platform.equalsIgnoreCase("iOS")) {
				element = DriverManager.getAppiumDriver().findElement(byLocator);
			}
			verifyElementPresent(byLocator,validationtext);
			if (platform.equalsIgnoreCase("web")) {
				getWebDriver().findElement(byLocator).click();
			} else if (platform.equalsIgnoreCase("Android") || platform.equalsIgnoreCase("BrowserStack")|| platform.equalsIgnoreCase("IOSBrowserStack") || platform.equalsIgnoreCase("iOS")) {
				DriverManager.getAppiumDriver().findElement(byLocator).click();
			}
			logger.info("Clicked on " + validationtext);
			ExtentReporter.extentLoggerPass("click", "Clicked on " + validationtext);
			return true;
		} catch (Exception e) {
			logger.error("Element " + validationtext + " " + "is not visible");
			ExtentReporter.extentLoggerFail("checkElementPresent", "" + validationtext + " is not displayed");
			ExtentReporter.screencapture();
			return false;
		}
	}
	public static String getAdId() throws IOException {
		String cmd = "adb shell grep adid_key /data/data/com.google.android.gms/shared_prefs/adid_settings.xml";
		Process p = Runtime.getRuntime().exec(cmd);
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String strBuffer = br.readLine().trim();
		String[] getadid = strBuffer.split(">")[1].split("<");
		logger.info("\nAdID: " + getadid[0]);
		return getadid[0];
	}
	/**
	 * @param byLocator
	 * @return true or false
	 */
	public static boolean checkcondition(By byLocator) throws Exception {
		boolean iselementPresent = false;
		try {
			iselementPresent = getDriver().findElement(byLocator).isDisplayed();
			iselementPresent = true;
		} catch (Exception e) {
			iselementPresent = false;
		}
		return iselementPresent;
	}
	/**
	 * Click on a web element.
	 * @param byLocator the by locator
	 */
	public static void click(By byLocator, String validationtext) throws Exception {
		String platform = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("Platform");
		try {
			if (platform.equalsIgnoreCase("BrowserStack") || platform.equalsIgnoreCase("Android")|| platform.equalsIgnoreCase("IOSBrowserStack")) {
				AndroidElement element = (AndroidElement) DriverManager.getAppiumDriver().findElement(byLocator);
				element.click();
			} else if (platform.equalsIgnoreCase("mpwa")) {
				WebElement element = DriverManager.getAppiumDriver().findElement(byLocator);
				element.click();
			} else if (platform.equalsIgnoreCase("iOS")) {
				WebElement element = DriverManager.getAppiumDriver().findElement(byLocator);
				element.click();
			} else if (platform.equalsIgnoreCase("web")) {
				WebElement element = getWebDriver().findElement(byLocator);
				element.click();
			}
			logger.info("Clicked on " + validationtext);
			ExtentReporter.extentLoggerPass("click", "Clicked on " + validationtext);
		} catch (Exception e) {
			screencapture();
			logger.info("Not clicked on " + validationtext);
			ExtentReporter.extentLoggerFail("click", "Not Clicked on " + validationtext);
		}
	}
	public static void DoubleClick(By locator, String OptionName) {
		try {
			Actions act = new Actions(DriverManager.getAppiumDriver());
			WebElement ele = DriverManager.getAppiumDriver().findElement(locator);
			act.doubleClick(ele).perform();
			logger.info("Clicked on the " + OptionName);
			ExtentReporter.extentLogger("Click", " " + OptionName);
		} catch (NoSuchElementException e) {
			logger.info("Did not click on " + OptionName);
			ExtentReporter.extentLogger("Did not Click on", " " + OptionName);
		}
	}
	public static boolean verifyElementNotPresentForWeb(By byLocator, int iTimeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), iTimeOut);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
			return false;
		} catch (NoSuchElementException e) {
			return true;
		}
	}
	/**
	 * clear the text field
	 * @param byLocator
	 */
	public static void clearField(By byLocator, String text) {
		try {
			DriverManager.getAppiumDriver().findElement(byLocator).clear();
			logger.info("Cleared the text in : " + text);
			ExtentReporter.extentLogger("clear text", "Cleared the text in : " + text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Get Text from an object
	 * @param byLocator
	 * @return
	 * @throws Exception
	 */
	public static String getText(By byLocator) throws Exception {
		String platform=null;
		if(getPlatform().equalsIgnoreCase("web")) {
			platform = "web";
		}else {
			platform = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("Platform");
		}
		String value = null;
		if (platform.equalsIgnoreCase("web")) {
			WebElement element = getWebDriver().findElement(byLocator);
			value = element.getText();
		} else if (platform.equalsIgnoreCase("Android") || platform.equalsIgnoreCase("BrowserStack") || platform.equalsIgnoreCase("iOS")) {
			WebElement element = DriverManager.getAppiumDriver().findElement(byLocator);
			value = element.getText();
		}
		return value;
	}

	@SuppressWarnings({ "rawtypes" })
	public String OTPNotification() throws Exception {
		ExtentReporter.HeaderChildNode("Fetching Otp From Notification");
		waitTime(2000);
		getDriver().context("NATIVE_APP");
		AndroidDriver driver = (AndroidDriver) getDriver();
		driver.openNotifications();
		waitTime(3000);
		List<WebElement> allnotifications = getDriver().findElements(By.xpath("(//*[@resource-id='android:id/message_text'])[1]"));
		logger.info("Size : " + allnotifications.size());
		String Otp = null;
		for (WebElement webElement : allnotifications) {
			Otp = webElement.getText();
			if (webElement.getText().contains("something")) {
				break;
			}
		}
		Back(1);
		getDriver().context("WEBVIEW_1");
		return Otp;
	}
	public boolean verifyElementIsNotDisplayed(By by) {
		try {
			getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			return getDriver().findElements(by).isEmpty();
		} catch (Exception e) {
			getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return false;
		}
	}
	public static boolean verifyIsElementDisplayed(By by) {
		List<WebElement> list = null;
		if (getPlatform().equals("Web")) {
			DriverManager.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			list = DriverManager.getDriver().findElements(by);
			DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} else {
			DriverManager.getAppiumDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			list = DriverManager.getAppiumDriver().findElements(by);
			DriverManager.getAppiumDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		if (list.size() == 0) {
			return false;
		} else {
			return list.get(0).isDisplayed();
		}
	}
	public static boolean verifyIsElementDisplayed(By by, String validationtext) {
		List<WebElement> list = null;
		if (getPlatform().equals("Web")) {
			DriverManager.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			list = DriverManager.getDriver().findElements(by);
			DriverManager.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} else {
			getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			list = getDriver().findElements(by);
			getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		if (list.size() == 0) {
			logger.info("Element " + validationtext + " " + " is not displayed");
			ExtentReporter.extentLogger("checkElementPresent", "" + validationtext + " is not displayed");
			return false;
		} else {
			logger.info("" + validationtext + " " + "is displayed");
			ExtentReporter.extentLogger("checkElementPresent", "" + validationtext + " is displayed");
			return list.get(0).isDisplayed();
		}
	}
	public static boolean checkElementExist(By byLocator) throws Exception {
		try {
			WebElement element = DriverManager.getDriver().findElement(byLocator);
			if (element.isDisplayed()) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	/**
	 * Kill or start an application using activity
	 * @param command  to START or KILL an application
	 * @param activity Start an application by passing the activity
	 */
	public void adbStartKill(String command, String activity) {
		String cmd;
		try {
			if (command.equalsIgnoreCase("START")) {
				cmd = "adb shell am start -n" + " " + activity;
				Runtime.getRuntime().exec(cmd);
				logger.info("Started the activity" + cmd);
				ExtentReporter.extentLogger("adbStart", "Started the activity" + cmd);
			} else if (command.equalsIgnoreCase("KILL")) {
				cmd = "adb shell am force-stop" + " " + activity;
				Runtime.getRuntime().exec(cmd);
				logger.info("Executed the App switch");
				ExtentReporter.extentLogger("adbKill", "Executed the App switch");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @return true if keyboard is displayed
	 * @throws IOException
	 */
	public boolean checkKeyboardDisplayed() throws IOException {
		boolean mInputShown = false;
		try {
			String cmd = "adb shell dumpsys input_method | grep mInputShown";
			Process p = Runtime.getRuntime().exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String outputText = "";
			while ((outputText = br.readLine()) != null) {
				if (!outputText.trim().equals("")) {
					String[] output = outputText.split(" ");
					String[] value = output[output.length - 1].split("=");
					String keyFlag = value[1];
					if (keyFlag.equalsIgnoreCase("True")) {
						mInputShown = true;
					}
				}
			}
			br.close();
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mInputShown;
	}
	/**
	 * Closes the Keyboard
	 */
	public static void hideKeyboard() {
		try {
			DriverManager.getAppiumDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			ExtentReporter.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		} catch (Exception e) {
			logger.info("Hiding keyboard was not Successfull");
		}
	}

	/**
	 * Type on a web element.
	 * @param byLocator the by locator
	 * @param input
	 */
	public static void type(By byLocator, String input, String FieldName) throws Exception {
		try {
			if (platform.equalsIgnoreCase("Android") || platform.equalsIgnoreCase("BrowserStack")
					|| platform.equalsIgnoreCase("IOSBrowserStack") || platform.equalsIgnoreCase("MPWAiOSSafari")) {
				WebElement ele = DriverManager.getAppiumDriver().findElement(byLocator);
				ele.sendKeys(input);
			} else if (platform.equalsIgnoreCase("iOS")) {
				WebElement ele = DriverManager.getAppiumDriver().findElement(byLocator);
				ele.sendKeys(input);
			}else if (platform.equalsIgnoreCase("mpwa")) {
				Actions a = new Actions(DriverManager.getAppiumDriver());
				a.sendKeys(input);
				a.perform();
			} else if (platform.equalsIgnoreCase("Web")) {
				getWebDriver().findElement(byLocator).sendKeys(input);
			}
			input = input.split("\n")[0];
			logger.info("Typed the value " + input + " into " + FieldName);
			ExtentReporter.extentLoggerPass("", "Typed the value " + input + " into " + FieldName);
		} catch (Exception e) {
			logger.error("Not Typed the value " + input + " into " + FieldName);
			ExtentReporter.extentLoggerFail("", "Not Typed the value " + input + " into " + FieldName);
		}
	}

	public static void enter(By byLocator, String input, String FieldName) {
		try {
			waitTime(1000);
			if (!getPlatform().equals("Web")) {
				Actions a = new Actions(getDriver());
				a.sendKeys(input);
				a.perform();
			} else {
				WebElement element = DriverManager.getDriver().findElement(byLocator);
				element.sendKeys(input);
			}
			input = input.split("\n")[0];
			logger.info("Typed the value into " + FieldName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Wait
	 * @param x seconds to lock
	 */
	public static void Wait(int x) {
		try {
			getDriver().manage().timeouts().implicitlyWait(x, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void refresh() {
		try {
			getDriver().navigate().refresh();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void waitTime(int x) {
		try {
			Thread.sleep(x);
		} catch (Exception e) {
			logger.error(e);
		}
	}
	/**
	 * @param keyevent pass the android key event value to perform specific action
	 */
	public void adbKeyevents(int keyevent) {
		try {
			String cmd = "adb shell input keyevent" + " " + keyevent;
			Runtime.getRuntime().exec(cmd);
			logger.info("Performed the Keyevent" + keyevent);
			ExtentReporter.extentLogger("adbKeyevent", "Performed the Keyevent" + keyevent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param byLocator
	 * @returns the list count of the element
	 */
	public int getCount(By byLocator) {
		int count = 0;
		try {
			count = getDriver().findElements(byLocator).size();
			logger.info("List count for" + " " + byLocator + " " + "is" + " " + count);
			ExtentReporter.extentLogger("getCount", "List count for" + " " + byLocator + " " + "is" + " " + count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	public static List<WebElement> findElements(By byLocator) {
		if (getPlatform().equalsIgnoreCase("web")) {
			platform = "web";
		} else {
		}
		if (platform.equalsIgnoreCase("web")) {
			return getWebDriver().findElements(byLocator);
		} else if (platform.equalsIgnoreCase("Android") || platform.equalsIgnoreCase("BrowserStack") || platform.equalsIgnoreCase("iOS")) {
			return DriverManager.getAppiumDriver().findElements(byLocator);
		}
		return null;
	}
	/**
	 * @param temp
	 * @param byLocator
	 * @returns the By locator
	 */
	public String iterativeXpathtoStringGenerator(int temp, By byLocator, String property) {
		WebElement element = null;
		String drug = null;
		try {
			String xpath = byLocator.toString();
			String var = "'" + temp + "'";
			xpath = xpath.replaceAll("__placeholder", var);
			String[] test = xpath.split(": ");
			xpath = test[1];
			element = getDriver().findElement(By.xpath(xpath));
			drug = element.getAttribute(property);
		} catch (Exception e) {
			logger.error(e);
		}
		return drug;
	}
	/**
	 * Back
	 * @throws Exception
	 */
	public static void Back(int x) throws Exception {
		try {
			if (getPlatform().equals("Web")) {
				for (int i = 0; i < x; i++) {
					DriverManager.getDriver().navigate().back();
					logger.info("Back button is tapped");
					ExtentReporter.extentLogger("Back", "Back button is tapped");
				}
			} else if (getPlatform().equals("Android") || getPlatform().equals("MPWA")
					|| platform.equalsIgnoreCase("BrowserStack") || platform.equalsIgnoreCase("IOSBrowserStack") || platform.equalsIgnoreCase("IOS")) {
				for (int i = 0; i < x; i++) {
					DriverManager.getAppiumDriver().navigate().back();
					logger.info("Back button is tapped");
					ExtentReporter.extentLogger("Back", "Back button is tapped");
					waitTime(6000);
				}
			}
		} catch (Exception e) {
			ExtentReporter.screencapture();
		}
	}
	/**
	 * Finding the duplicate elements in the list
	 * @param mono
	 */
	public List<String> findDuplicateElements(List<String> mono) {
		List<String> duplicate = new ArrayList<String>();
		Set<String> s = new HashSet<String>();
		try {
			if (mono.size() > 0) {
				for (String content : mono) {
					if (s.add(content) == false) {
						int i = 1;
						duplicate.add(content);
						i++;
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return duplicate;
	}
	/**
	 * @param contents
	 * @return the list without duplicate elements
	 */
	public List<String> removeDuplicateElements(List<String> contents) {
		LinkedHashSet<String> set = new LinkedHashSet<String>(contents);
		ArrayList<String> listWithoutDuplicateElements = new ArrayList<String>();
		try {
			if (contents.size() > 0) {
				listWithoutDuplicateElements = new ArrayList<String>(set);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return listWithoutDuplicateElements;
	}
	/**
	 * @param temp
	 * @param byLocator
	 */
	public void iteratorClick(int temp, By byLocator) {
		try {
			String xpath = byLocator.toString();
			String var = "'" + temp + "'";
			xpath = xpath.replaceAll("__placeholder", var);
			String[] test = xpath.split(": ");
			xpath = test[1];
			getDriver().findElement(By.xpath(xpath)).click();
		} catch (Exception e) {
			logger.error(e);
		}
	}
	/**
	 * Get specific property value of a web element and stores to string variable.
	 * @param property  the property of the element.
	 * @param byLocator the by locator
	 * @return value of the property.
	 */
	public static String getElementPropertyToString(String property, By byLocator, String object) {
		String propertyValue = null;
		if (platform.equalsIgnoreCase("web")) {
			try {
				WebElement element = DriverManager.getDriver().findElement(byLocator);
				propertyValue = element.getAttribute(property);
			} catch (Exception e) {
				logger.error(e);
			}
		} else if (platform.equalsIgnoreCase("mpwa")) {
			try {
				WebElement element = DriverManager.getAppiumDriver().findElement(byLocator);
				propertyValue = element.getAttribute(property);
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return propertyValue;
	}
	/**
	 * @param ListToSort
	 * @return true if the list is sorted
	 * @return false if the list is not sorted
	 */
	public static boolean checkListIsSorted(List<String> ListToSort) {
		boolean isSorted = false;
		if (ListToSort.size() > 0) {
			try {
				if (Ordering.natural().isOrdered(ListToSort)) {
					ExtentReporter.extentLogger("Check sorting", "List is sorted");
					logger.info("List is sorted");
					isSorted = true;
					return isSorted;
				} else {
					ExtentReporter.extentLogger("Check sorting", ListToSort + " " + "List is not sorted");
					logger.info(ListToSort + "List is notsorted");
					isSorted = false;
				}
			} catch (Exception e) {
				logger.error(e);
			}
		} else {
			logger.info("The size of the list is zero");
			ExtentReporter.extentLogger("",
					ListToSort + " " + "There are no elements in the list to check the sort order");
		}
		return isSorted;
	}
	/**
	 * @param byLocator
	 * @returns the list count of the element
	 */
	public int iterativeGetCount(int temp, By byLocator) {
		int count = 0;
		try {
			String xpath = byLocator.toString();
			String var = "'" + temp + "'";
			xpath = xpath.replaceAll("__placeholder", var);
			String[] test = xpath.split(": ");
			xpath = test[1];
			count = getDriver().findElements(By.xpath(xpath)).size();
			logger.info("List count for" + " " + xpath + " " + "is" + " " + count);
			ExtentReporter.extentLogger("getCount", "List count for" + " " + xpath + " " + "is" + " " + count);
		} catch (Exception e) {
			logger.error(e);
		}
		return count;
	}
	/**
	 * @param temp
	 * @param byLocator
	 * @return
	 */
	public By iterativeXpathText(String temp, By byLocator) {
		By searchResultList = null;
		try {
			String xpath = byLocator.toString();
			String var = "'" + temp + "'";
			xpath = xpath.replaceAll("__placeholder", var);
			String[] test = xpath.split(": ");
			xpath = test[1];
			searchResultList = By.xpath(xpath);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResultList;
	}
	/**
	 * @param byLocator Checks whether element is not displayed
	 * @throws Exception
	 */
	public void checkElementNotPresent(By byLocator) throws Exception {
		boolean isElementNotPresent = true;
		try {
			isElementNotPresent = checkcondition(byLocator);
			softAssert.assertEquals(isElementNotPresent, false);
			logger.info("" + byLocator + " " + "is not displayed");
			ExtentReporter.extentLogger("checkElementNotPresent", "" + byLocator + "is not displayed");
		} catch (Exception e) {
			softAssert.assertEquals(isElementNotPresent, true, "Element" + byLocator + " " + "is visible");
			logger.error(byLocator + " " + "is visible");
			ExtentReporter.extentLogger("checkElementNotPresent", "" + byLocator + "is displayed");
			ExtentReporter.screencapture();
		}
	}
	public static void Resize_Browser(int width, int height) {
		try {
			Dimension scale = new Dimension(width, height);
			getDriver().manage().window().setSize(scale);
		} catch (Exception e) {
			logger.info("Unable to set the size of the browser");
		}
	}
	public void SwipeRail(By From) throws Exception {
		WebElement element = DriverManager.getDriver().findElement(From);
		Dimension size = getDriver().manage().window().getSize();
		int startx = (int) (size.width * 0.8);
		int endx = (int) (size.width * 0.1);
		String eleY = element.getAttribute("y");
		int currentPosY = Integer.parseInt(eleY);
		currentPosY = Integer.parseInt(eleY) + 200;
		touchAction = new TouchAction(getDriver());
		touchAction.press(PointOption.point(startx, currentPosY))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
				.moveTo(PointOption.point(endx, currentPosY)).release().perform();
	}
	/**
	 * Swipes the screen in left or right or Up or Down or direction
	 * @param direction to swipe Left or Right or Up or Down
	 * @param count     to swipe
	 */
	@SuppressWarnings("rawtypes")
	public void PartialSwipe(String direction, int count) {
		touchAction = new TouchAction(getDriver());
		String dire = direction;
		try {
			if (dire.equalsIgnoreCase("LEFT")) {
				for (int i = 0; i < count; i++) {
					Dimension size = getDriver().manage().window().getSize();
					int startx = (int) (size.width * 0.4);
					int endx = (int) (size.width * 0.1);
					int starty = size.height / 2;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(endx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
					ExtentReporter.extentLogger("SwipeLeft",
							"Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
				}
			} else if (dire.equalsIgnoreCase("RIGHT")) {
				for (int j = 0; j < count; j++) {
					Dimension size = getDriver().manage().window().getSize();
					int endx = (int) (size.width * 0.4);
					int startx = (int) (size.width * 0.1);
					int starty = size.height / 2;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(endx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					ExtentReporter.extentLogger("SwipeRight",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				}
			} else if (dire.equalsIgnoreCase("UP")) {
				for (int j = 0; j < count; j++) {
					Dimension size = getDriver().manage().window().getSize();
					int starty = (int) (size.height * 0.40);
					int endy = (int) (size.height * 0.1);
					int startx = size.width / 2;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(startx, endy)).release().perform();
					logger.info("Swiping screen in " + dire + " direction" + " " + (j + 1) + " times");
					ExtentReporter.extentLogger("SwipeUp",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				}
			} else if (dire.equalsIgnoreCase("DOWN")) {
				for (int j = 0; j < count; j++) {
					Dimension size = getDriver().manage().window().getSize();
					int starty = (int) (size.height * 0.4);
					int endy = (int) (size.height * 0.1);
					int startx = size.width / 2;
					touchAction.press(PointOption.point(startx, endy))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(startx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					ExtentReporter.extentLogger("SwipeDown",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}
	/**
	 * Drag window
	 * @param byLocator, byTimer
	 */
	@SuppressWarnings("rawtypes")
	public void DragWindow(By byLocator, String direction) throws Exception {
		WebElement element = getDriver().findElement(byLocator);
		touchAction = new TouchAction(getDriver());
		if (direction.equalsIgnoreCase("LEFT")) {
			Dimension size = element.getSize();
			int startx = (int) (size.width * 0.4);
			int endx = (int) (size.width * 0.1);
			int starty = size.height / 2;
			touchAction.longPress(PointOption.point(startx, starty))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point(endx, starty)).release().perform();
			logger.info("Swiping " + " " + direction + " direction");
			ExtentReporter.extentLogger("SwipeLeft", "Swiping " + " " + direction + " direction");
		} else if (direction.equalsIgnoreCase("DOWN")) {
			Dimension size = getDriver().manage().window().getSize();
			int starty = (int) (size.height * 0.80);
			int endy = (int) (size.height * 0.20);
			int startx = size.width / 2;
			touchAction.longPress(PointOption.point(startx, endy))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point((int) startx, (int) starty)).release().perform();
			logger.info("Swiping " + " " + direction + " direction");
			ExtentReporter.extentLogger("SwipeLeft", "Swiping " + " " + direction + " direction");
		}
	}
	/**
	 * @param bundleID
	 */
	public void launchiOSApp(String bundleID) {
		try {
			iOSDriver = (IOSDriver<WebElement>) getDriver();
			logger.info("Started the bundle id" + " " + bundleID);
			ExtentReporter.extentLogger("Started the bundle id" + " " + bundleID,
					"Started the bundle id" + " " + bundleID);
		} catch (Exception e) {
			logger.info("Unable to Start the bundle id" + " " + bundleID);
			ExtentReporter.extentLogger("Unable to Start the bundle id" + " " + bundleID,
					"Unable to Start the bundle id" + " " + bundleID);
		}
	}
	/**
	 * Get the Mobile Orientation
	 * @throws Exception
	 */
	public void GetAndVerifyOrientation(String Value) throws Exception {
		ScreenOrientation orientation = getDriver().getOrientation();
		String ScreenOrientation = orientation.toString();
		try {
			softAssert.assertEquals(ScreenOrientation.equalsIgnoreCase(Value), true,
					"The screen Orientation is " + ScreenOrientation);
			logger.info("The screen Orientation is " + ScreenOrientation);
			ExtentReporter.extentLogger("Screen Orientation", "The screen Orientation is " + ScreenOrientation);
		} catch (Exception e) {
			softAssert.assertEquals(false, true, "The screen Orientation is not " + ScreenOrientation);
			logger.error("The screen Orientation is not " + ScreenOrientation);
			ExtentReporter.extentLogger("Screen Orientation", "The screen Orientation is not " + ScreenOrientation);
			ExtentReporter.screencapture();
		}
	}
	/**
	 * Closes the iOS keyboard
	 */
	public void closeIosKeyboard() {
		try {
			iOSDriver = (IOSDriver<WebElement>) getDriver();
			ExtentReporter.extentLogger("Hiding keyboard successful", "Hiding keyboard successful");
		} catch (Exception e) {
			ExtentReporter.extentLogger("Hiding keyboard not successful", "Hiding keyboard not successful");
		}
	}
	/**
	 * closes the application
	 */
	public void closeiOSApp() {
		try {
			iOSDriver = (IOSDriver<WebElement>) getDriver();
			iOSDriver.closeApp();
			ExtentReporter.extentLogger("Killed the appliaction successfully", "Killed the appliaction successfully");
		} catch (Exception e) {
			ExtentReporter.extentLogger("Unable to Kill the application", "Unable to Kill the application");
		}
	}
	/**
	 * closes the Android application
	 */
	public void closeAndroidApp() {
		try {
			getDriver().resetApp();
			ExtentReporter.extentLogger("Killed the application successfully", "Killed the application successfully");
		} catch (Exception e) {
			ExtentReporter.extentLogger("Unable to Kill the application", "Unable to Kill the application");
		}
	}
	/**
	 * Verifies if a new page or app is open
	 */
	public boolean newPageOrNt() {
		boolean app = false;
		try {
			String cmd = "adb shell \"dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp'\"";
			Process p = Runtime.getRuntime().exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String outputText = "";
			while ((outputText = br.readLine()) != null) {
				if (!outputText.trim().contains("com.tv.v18.viola")) {
					app = true;
					logger.info("The app is navigated to ad page");
					ExtentReporter.extentLogger("Navigated to ad page or not", "The app is navigated to ad page");
				} else {
					logger.error("The app is not navigated to ad page");
					ExtentReporter.extentLogger("Navigated to ad page or not", "The app is not navigated to ad page");
				}
			}
			br.close();
			p.waitFor();
		} catch (Exception e) {
			logger.error(e);
		}
		return app;
	}
	public void IosDragWindow(By byLocator, String direction) throws Exception {
		WebElement element = getDriver().findElement(byLocator);
		@SuppressWarnings({ "rawtypes", "unused" })
		TouchAction action = new TouchAction(getDriver());
		if (direction.equalsIgnoreCase("LEFT")) {
			Dimension size = element.getSize();
			int startx = (int) (size.width * 0.4);
			int endx = 0;
			int starty = 1250;
			SwipeAnElement(element, startx, starty);
			logger.info("Swiping " + " " + direction + " direction");
			ExtentReporter.extentLogger("SwipeLeft", "Swiping " + " " + direction + " direction");
		}
	}
	public static String getAttributValue(String property, By byLocator) throws Exception {
		WebElement element;
		String Value = null;
		if(getPlatform().equalsIgnoreCase("Web")){
			element = getWebDriver().findElement(byLocator);
		}else {
			element = DriverManager.getAppiumDriver().findElement(byLocator);
		}
		Value = element.getAttribute(property);
		return Value;
	}
	public void SwipeAnElement(WebElement element, int posx, int posy) {
		AndroidTouchAction touch = new AndroidTouchAction(getDriver());
		touch.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(element)))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(posx, posy))
				.release().perform();
	}
	public void longPressContent(By element) throws Exception {
		AndroidTouchAction touch = new AndroidTouchAction(getDriver());
		touch.longPress(LongPressOptions.longPressOptions()
				.withElement(ElementOption.element(getDriver().findElement(element)))).release().perform();
		TouchActions action = new TouchActions(getDriver());
		action.singleTap(getDriver().findElement(element));
		action.click();
	}
	public static boolean verifyElementExist(WebElement ele, String str) throws Exception {
		try {
			WebElement element = ele;
			if (element.isDisplayed()) {
				ExtentReporter.extentLogger("checkElementPresent", "<b>" + str + "</b> is displayed");
				logger.info("" + str + " is displayed");
				return true;
			}
		} catch (Exception e) {
			ExtentReporter.extentLogger("checkElementPresent", "<b>" + str + "</b> is not displayed");
			logger.info(str + " is not displayed");
			return false;
		}
		return false;
	}
	public boolean checkcondition(String s) throws Exception {
		boolean iselementPresent = false;
		try {
			String element = "//*[@text='[" + s + "]']";
			iselementPresent = ((WebElement) getDriver().findElementsByXPath(element)).isDisplayed();
		} catch (Exception e) {
			iselementPresent = false;
		}
		return iselementPresent;
	}
	public void switchtoLandscapeMode() throws IOException {
		Runtime.getRuntime().exec(
				"adb shell content insert --uri content://settings/system --bind name:s:user_rotation --bind value:i:1");
	}
	public void switchtoPortraitMode() throws IOException {
		Runtime.getRuntime().exec(
				"adb shell content insert --uri content://settings/system --bind name:s:user_rotation --bind value:i:0");
	}
	@SuppressWarnings("rawtypes")
	public void PartialSwipeInConsumptionScreen(String direction, int count) {
		touchAction = new TouchAction(getDriver());
		String dire = direction;
		try {
			if (dire.equalsIgnoreCase("UP")) {
				for (int j = 0; j < count; j++) {
					Dimension size = getDriver().manage().window().getSize();
					int starty = (int) (size.height * 0.8);
					int endy = (int) (size.height * 0.5);
					int startx = size.width / 2;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(startx, endy)).release().perform();
					logger.info("Swiping screen in " + dire + " direction" + " " + (j + 1) + " times");
					ExtentReporter.extentLogger("SwipeUp",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				}
			} else if (dire.equalsIgnoreCase("DOWN")) {
				for (int j = 0; j < count; j++) {
					Dimension size = getDriver().manage().window().getSize();
					int starty = (int) (size.height * 0.8);
					int endy = (int) (size.height * 0.5);
					int startx = size.width / 2;
					touchAction.press(PointOption.point(startx, endy))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(startx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					ExtentReporter.extentLogger("SwipeDown",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}
	/**
	 * Function to ExplicitWait Visibility
	 * @param element
	 * @param time
	 * @throws Exception
	 */
	public static void explicitWaitVisibility(By element, int time) throws Exception {
		if (getPlatform().equalsIgnoreCase("web")) {
			wait.until(ExpectedConditions.visibilityOf(getWebDriver().findElement(element)));
		} else {
			wait.until(ExpectedConditions.visibilityOf(DriverManager.getAppiumDriver().findElement(element)));
		}
	}
	public static void explicitWaitVisible(By byLocator, int time) throws Exception {
		WebDriverWait wait = new WebDriverWait(DriverManager.getAppiumDriver(), time);
		wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
	}
	/**
	 * Function to ExplicitWait for Clickable
	 * @param element
	 * @param time
	 * @throws Exception
	 */
	public void explicitWaitClickable(By element, int time) throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(element)));
	}
	/**
	 * Function to ExplicitWait for windows
	 * @param noOfWindows
	 */
	public static void explicitWaitForWindows(int noOfWindows) {
		wait.until(ExpectedConditions.numberOfWindowsToBe(noOfWindows));
	}
	/**
	 * Function for ExplicitWait of Element Refresh
	 * @throws Exception
	 */
	public void explicitWaitForElementRefresh(By element) throws Exception {
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(getDriver().findElement(element))));
	}
	/**
	 * Function for implicitWait
	 */
	public static void implicitWait(int time) {
		DriverManager.getAppiumDriver().manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	/**
	 * Function to select by visible text from drop down
	 * @param element
	 * @param value
	 * @throws Exception
	 */
	public void selectByVisibleTextFromDD(By element, String value) throws Exception {
		explicitWaitVisibility(element, 20);
		Select select = new Select(getDriver().findElement(element));
		select.selectByVisibleText(value);
	}
	/**
	 * Function to select by value from drop down
	 * @param element
	 * @param value
	 * @throws Exception
	 */
	public void selectByValueFromDD(By element, String value) throws Exception {
		explicitWaitVisibility(element, 20);
		Select select = new Select(getDriver().findElement(element));
		select.selectByValue(value);
	}
	/**
	 * Function to select By index From Drop down
	 * @param element
	 * @param value
	 * @throws Exception
	 */
	public void selectByIndexFromDD(By element, String value) throws Exception {
		explicitWaitVisibility(element, 20);
		Select select = new Select(getDriver().findElement(element));
		select.selectByValue(value);
	}
	/**
	 * Function to get First Element from Drop down
	 * @param element
	 * @return
	 * @throws Exception
	 */
	public String getFirstElementFromDD(By element) throws Exception {
		Select select = new Select(getDriver().findElement(element));
		return select.getFirstSelectedOption().getText();
	}
	/**
	 * Function to scroll down
	 */
	public static void scrollDownWEB() {
		js = (JavascriptExecutor) getWebDriver();
		js.executeScript("window.scrollBy(0,250)", "");
	}
	/**
	 * Function to Scroll By
	 */
	public static void scrollByWEB() {
		js = (JavascriptExecutor) getWebDriver();
		js.executeScript("window.scrollBy(0,250)", "");
	}
	/**
	 * Function to scroll bottom of page
	 */
	public static void scrollToBottomOfPageWEB() {
		js = (JavascriptExecutor) getWebDriver();
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	public static void scrollToBottomOfPage() {
		js = (JavascriptExecutor) DriverManager.getAppiumDriver();
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	/**
	 * Function to scroll to top of the page
	 */
	public static void scrollToTopOfPageWEB() {
		js = (JavascriptExecutor) getWebDriver();
		js.executeScript("window.scrollBy(0,-250)", "");
	}
	public static void scrollToTopOfPage() {
		js = (JavascriptExecutor) DriverManager.getDriver();
		js.executeScript("window.scrollBy(0,-250)", "");
	}
	/**
	 * Function Scroll to Element
	 * @param element
	 * @throws Exception
	 */
	public static void ScrollToTheElement(By element) throws Exception {
		if (getPlatform().equalsIgnoreCase("web")) {
			js = (JavascriptExecutor) getWebDriver();
			js.executeScript("arguments[0].scrollIntoView(true);", getWebDriver().findElement(element));
			js.executeScript("window.scrollBy(0,-50)", "");
		} else{
			js = (JavascriptExecutor) DriverManager.getDriver();
			js.executeScript("arguments[0].scrollIntoView(true);", DriverManager.getDriver().findElement(element));
			js.executeScript("window.scrollBy(0,-50)", "");
		}
	}
	/**
	 * Function for hard sleep
	 * @param seconds
	 */
	public void sleep(int seconds) {
		try {
			int ms = 1000 * seconds;
			Thread.sleep(ms);
		} catch (InterruptedException e) {
		}
	}
	/**
	 * Function to switch the tab
	 * @param tab
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void switchTab(int tab) {
		ArrayList<String> window = new ArrayList(DriverManager.getDriver().getWindowHandles());
		DriverManager.getDriver().switchTo().window(window.get(tab));
	}
	/**
	 * Function to generate random integer of specified maxValue
	 * @param maxValue
	 * @return
	 */
	public static String generateRandomInt(int maxValue) {
		Random rand = new Random();
		int x = rand.nextInt(maxValue);
		String randomInt = Integer.toString(x);
		return randomInt;
	}
	public static String RandomIntegerGenerator(int n) {
		String number = "0123456789";
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			int index = (int) (number.length() * Math.random());
			sb.append(number.charAt(index));
		}
		return sb.toString();
	}
	/**
	 * Function to generate Random String of length 4
	 * @return
	 */
	public static String generateRandomString(int size) {
		String strNumbers = "abcdefghijklmnopqrstuvwxyzacvbe";
		Random rnd = new Random();
		StringBuilder strRandomNumber = new StringBuilder(9);
		strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));
		String s1 = strRandomNumber.toString().toUpperCase();
		for (int i = 1; i < size; i++) {
			strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));
		}
		return s1 + strRandomNumber.toString();
	}
	/**
	 * Function to generate Random characters of specified length
	 * @param candidateChars
	 * @param length
	 * @return
	 */
	public String generateRandomChars(String candidateChars, int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
		}
		return sb.toString();
	}
	/**
	 * Function to generate Random Integer between range
	 * @param maxValue
	 * @param minValue
	 * @return
	 * @throws InterruptedException
	 */
	public String generateRandomIntwithrange(int maxValue, int minValue) throws InterruptedException {
		Thread.sleep(2000);
		Random rand = new Random();
		int x = rand.nextInt(maxValue - minValue) + 1;
        return Integer.toString(x);
	}
	/**
	 * Function to drag and drop an object
	 * @param From
	 * @param To
	 * @throws Exception
	 */
	public void dragnddrop(By From, By To) throws Exception {
		WebElement Drag = getDriver().findElement(From);
		WebElement Drop = getDriver().findElement(To);
		Thread.sleep(1000);
		Actions builder = new Actions(getDriver());
		builder.clickAndHold(Drag).moveToElement(Drop).release(Drop).build().perform();
	}
	/**
	 * Function Convert from String to Integer @param(String)
	 */
	public int convertToInt(String string) {
		int i = Integer.parseInt(string);
		return i;
	}
	/**
	 * Function Convert from Integer to String @param(integer)
	 */
	public String convertToString(int i) {
		String string = Integer.toString(i);
		return string;
	}
	/**
	 * Click On element without waiting or verifying
	 *
	 * @param byLocator the by locator
	 */
	public static void clickDirectly(By byLocator, String validationtext) throws Exception {
		try {
			getDriver().findElement(byLocator).click();
			logger.info("Clicked on the " + validationtext);
			ExtentReporter.extentLogger("click", "Clicked on the <b> " + validationtext);
		} catch (Exception e) {
			ExtentReporter.screencapture();
		}
	}
	public static void verifyAlert() {
		try {
			DriverManager.getDriver().switchTo().alert().dismiss();
			logger.info("Dismissed the alert Pop Up");
			ExtentReporter.extentLogger("Alert PopUp", "Dismissed the alert Pop Up");
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void acceptAlert() {
		try {
			DriverManager.getDriver().switchTo().alert().accept();
			logger.info("Dismissed the alert Pop Up");
			ExtentReporter.extentLogger("Alert PopUp", "Dismissed the alert Pop Up");
		} catch (Exception e) {
			logger.error(e);
		}
	}
	public static boolean clickElementWithLocator(By locator) throws Exception {
		String url = Utilities.getParameterFromXML("url");
		if (platform.equalsIgnoreCase("web")) {
			try {
				DriverManager.getDriver().findElement(locator).click();
				return true;
			} catch (Exception e) {
				logger.error(e);
			}
		} else if (platform.equalsIgnoreCase("mpwa")) {
			try {
				DriverManager.getAppiumDriver().findElement(locator).click();
				return true;
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return false;
	}

	public static boolean clickElementWithWebElement(WebElement element) throws Exception {
		try {
			element.click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public static int timeToSec(String s) {
		String[] t = s.split(":");
		int num = 0;
		if (t.length == 2) {
			num = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]); // minutes since 00:00
		}
		if (t.length == 3) {
			num = ((Integer.parseInt(t[0]) * 60) * 60) + Integer.parseInt(t[1]) * 60 + Integer.parseInt(t[2]);
		}
		return num;
	}
	public static void partialScrollDown() {
		JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getDriver();
		jse.executeScript("window.scrollBy(0,500)", "");
	}
	public static void clickByElement(WebElement ele, String validationtext) throws Exception {
		try {
			WebElement element = ele;
			element.click();
			logger.info("Clicked on the " + validationtext);
			ExtentReporter.extentLogger("click", "Clicked on the <b> " + validationtext);
		} catch (Exception e) {
			logger.error(e);
			ExtentReporter.screencapture();
		}
	}
	public static boolean verifyElementEnabled(By byLocator, String str) throws Exception {
		try {
			WebElement element = DriverManager.getDriver().findElement(byLocator);
			if (element.isEnabled()) {
				ExtentReporter.extentLogger("checkElementPresent", "" + str + " is displayed");
				logger.info("" + str + " is displayed");
				return true;
			}
		} catch (Exception e) {
			ExtentReporter.extentLogger("checkElementPresent", "" + str + " is not displayed");
			logger.info(str + " is not displayed");
			return false;
		}
		return false;
	}
	public static int getCountweb(By byLocator) {
		int count = 0;
		try {
			count = DriverManager.getDriver().findElements(byLocator).size();
			logger.info("List count for" + " " + byLocator + " " + "is" + " " + count);
			ExtentReporter.extentLogger("getCount", "List count for" + " " + byLocator + " " + "is" + " " + count);
		} catch (Exception e) {
			logger.error(e);
		}
		return count;
	}
	public static boolean waitForElementAndClickIfPresent(By locator, int seconds, String message) throws Exception {
		try {
			if (getPlatform().equals("Web")) {
				for (int time = 0; time <= seconds; time++) {
					try {
						getWebDriver().findElement(locator).click();
						logger.info("Clicked element " + message);
						ExtentReporter.extentLogger("clickedElement", "Clicked element " + message);
						return true;
					} catch (Exception e) {
						Thread.sleep(1000);
					}
				}
			} else if (getPlatform().equals("Android") || getPlatform().equals("MPWA") || getPlatform().equals("iOS") || getPlatform().equals("MPWAiOSSafari")) {
				for (int time = 0; time <= seconds; time++) {
					try {
						DriverManager.getAppiumDriver().findElement(locator).click();
						logger.info("Clicked on " + message);
						ExtentReporter.extentLogger("clickedElement", "Clicked on " + message);
						return true;
					} catch (Exception e) {
						Thread.sleep(1000);
					}
				}
			}
		} catch (Exception e) {
			ExtentReporter.screencapture();
			ExtentReporter.extentLoggerFail("","Not clicked on element " + message);
		}
		return false;
	}
	public static String RandomStringGenerator(int n) {
		{
			String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
			StringBuilder sb = new StringBuilder(n);
			for (int i = 0; i < n; i++) {
				int index = (int) (AlphaNumericString.length() * Math.random());
				sb.append(AlphaNumericString.charAt(index));
			}
			return sb.toString();
		}
	}
	/**
	 * Method to swipe bottom of page
	 * @throws Exception
	 */
	public static void swipeToBottomOfPage() throws Exception {
		for (int i = 0; i < 5; i++) {
			scrollToBottomOfPage();
			waitTime(4000);
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void androidSwitchTab() {
		ArrayList<String> window = new ArrayList(getDriver().getWindowHandles());
		getDriver().switchTo().window(window.get(window.size() - 1));
	}
	/**
	 * Function to switch to parent Window
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void AndroidSwitchToParentWindow() {
		try {
			ArrayList<String> window = new ArrayList(getDriver().getWindowHandles());
			getDriver().switchTo().window(window.get(0));
		} catch (Exception e) {
			logger.error(e);
		}
	}
	public static String getTheOSVersion() {
		String version = null;
		try {
			String cmd1 = "adb shell getprop ro.build.version.release";
			Process p1 = Runtime.getRuntime().exec(cmd1);
			BufferedReader br = new BufferedReader(new InputStreamReader(p1.getInputStream()));
			while ((version = br.readLine()) != null) {
				logger.info("Version :: " + version.toString());
				Thread.sleep(3000);
				break;
			}
		} catch (Exception e) {
			 logger.error(e);
		}
		return version;
	}
	public void TurnOFFWifi() throws Exception {
		String Deviceversion = getTheOSVersion();
		if (Deviceversion.contains("6")) {
			Runtime.getRuntime().exec("adb shell am broadcast -a io.appium.settings.wifi --es setstatus disable");
			logger.info("Turning off wifi");
			ExtentReporter.extentLoggerPass("Turning off wifi", "Turning off wifi");
		} else {
			Runtime.getRuntime().exec("adb shell svc wifi disable");
			logger.info("Turning off wifi");
			ExtentReporter.extentLoggerPass("Turning off wifi", "Turning off wifi");
		}
	}
	public void TurnONWifi() throws Exception {
		String Deviceversion = getTheOSVersion();
		if (Deviceversion.contains("6")) {
			Runtime.getRuntime().exec("adb shell am broadcast -a io.appium.settings.wifi --es setstatus enable");
			logger.info("Turning ON wifi");
			ExtentReporter.extentLoggerPass("Turning ON wifi", "Turning ON wifi");
		} else {
			Runtime.getRuntime().exec("adb shell svc wifi enable");
			logger.info("Turning ON wifi");
			ExtentReporter.extentLoggerPass("Turning ON wifi", "Turning ON wifi");
		}
	}
	@SuppressWarnings("rawtypes")
	public void carouselSwipe(String direction, int count) {
		touchAction = new TouchAction(getDriver());
		String dire = direction;
		try {
			if (dire.equalsIgnoreCase("LEFT")) {
				for (int i = 0; i < count; i++) {
					Dimension size = getDriver().manage().window().getSize();

					int startx = (int) (size.width * 0.9);
					int endx = (int) (size.width * 0.20);
					int starty = size.height / 2;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(endx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
					ExtentReporter.extentLogger("SwipeLeft",
							"Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
				}
			} else if (dire.equalsIgnoreCase("RIGHT")) {
				for (int j = 0; j < count; j++) {
					Dimension size = getDriver().manage().window().getSize();
					int endx = (int) (size.width * 0.9);
					int startx = (int) (size.width * 0.20);
					if (size.height > 2000) {
						int starty = (int) (size.height / 2);
						touchAction.press(PointOption.point(startx, starty))
								.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
								.moveTo(PointOption.point(endx, starty)).release().perform();
					} else {
						int starty = (int) (size.height / 1.5);
						touchAction.press(PointOption.point(startx, starty))
								.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
								.moveTo(PointOption.point(endx, starty)).release().perform();
					}
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					ExtentReporter.extentLogger("SwipeRight",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public static void ScrollToTheElementWEB(By element) throws Exception {
		js = (JavascriptExecutor) DriverManager.getDriver();
		js.executeScript("arguments[0].scrollIntoView(true);", DriverManager.getDriver().findElement(element));
		js.executeScript("window.scrollBy(0,-250)", "");
	}
	public static boolean checkElementDisplayed(By byLocator, String str) throws Exception {
		if (platform.equalsIgnoreCase("web")) {
			try {
				WebElement element = DriverManager.getDriver().findElement(byLocator);
				if (element.isDisplayed()) {
					ExtentReporter.extentLogger("checkElementPresent", "" + str + " is displayed");
					logger.info("" + str + " is displayed");
					return true;
				}
			} catch (Exception e) {
				ExtentReporter.extentLogger("checkElementPresent", "" + str + " is not displayed");
				logger.info("" + str + " is not displayed");
				return false;
			}
		} else if (platform.equalsIgnoreCase("mpwa")) {
			try {
				WebElement element = DriverManager.getAppiumDriver().findElement(byLocator);
				if (element.isDisplayed()) {
					ExtentReporter.extentLogger("checkElementPresent", "" + str + " is displayed");
					logger.info("" + str + " is displayed");
					return true;
				}
			} catch (Exception e) {
				ExtentReporter.extentLogger("checkElementPresent", "" + str + " is not displayed");
				logger.info("" + str + " is not displayed");
				return false;
			}
		}
		return false;
	}
	public static String getParameterFromXML(String param) {
		return ExtentReporter.testContext.getCurrentXmlTest().getParameter(param);
	}
	/**
	 * @param expectedtitle
	 */
	public static void getTitle(String expectedtitle) {
		String title = DriverManager.getDriver().getTitle();
		Assert.assertEquals(title, expectedtitle, "Actual and expected titles are matching");
		logger.info("Expected Title " + title + " is matching");
		logger.info("Home Page is displayed");
		extentLogger("Expected Title ", title + " is matching");
	}
	@SuppressWarnings("rawtypes")
	public void SwipeInLandscapeMode(String direction, int count) {
		touchAction = new TouchAction(getDriver());
		String dire = direction;
		try {
			if (dire.equalsIgnoreCase("DOWN") | dire.equalsIgnoreCase("LEFT")) {

				for (int i = 0; i < count; i++) {
					Dimension size = getDriver().manage().window().getSize();
					int xCor = (int) (size.height / 2);
					int startY = (int) (size.width * 0.20);
					int endY = (int) (size.width * 0.85);
					touchAction.press(PointOption.point(xCor, startY))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(xCor, endY)).release().perform();
					logger.info("Swiping screen in " + dire + " direction" + (i + 1) + " times");
					ExtentReporter.extentLogger("SwipeLeft",
							"Swiping screen in " + dire + " direction" + (i + 1) + " times");
				}
			} else if (dire.equalsIgnoreCase("UP") | dire.equalsIgnoreCase("RIGHT")) {

				for (int j = 0; j < count; j++) {
					Dimension size = getDriver().manage().window().getSize();
					int xCor = (int) (size.height / 2);
					int startY = (int) (size.width * 0.85);
					int endY = (int) (size.width * 0.20);
					touchAction.press(PointOption.point(xCor, startY))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(xCor, endY)).release().perform();
					logger.info("Swiping screen in " + dire + " direction " + (j + 1) + " times");
					ExtentReporter.extentLogger("SwipeRight",
							"Swiping screen in " + dire + " direction " + (j + 1) + " times");
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}
	public void clearBackgroundApps() throws IOException {
		String adbRecentApp = "adb shell input keyevent KEYCODE_APP_SWITCH";
		String adbSelectApp = "adb shell input keyevent KEYCODE_DPAD_DOWN";
		String adbClearApp = "adb shell input keyevent KEYCODE_DEL";
		String adbHomeScreen = "adb shell input keyevent KEYCODE_HOME";
		Runtime.getRuntime().exec(adbRecentApp);
		for (int iterator = 1; iterator <= 7; iterator++) {
			waitTime(1000);
			Runtime.getRuntime().exec(adbClearApp);
			Runtime.getRuntime().exec(adbSelectApp);
		}
		waitTime(1000);
		Runtime.getRuntime().exec(adbHomeScreen);
	}
	public boolean findElementInRefreshingConvivaPage(WebDriver webdriver, By locator, String displayText)
			throws Exception {
		js = (JavascriptExecutor) DriverManager.getDriver();
		for (int i = 1; i <= 500; i++) {
			DriverManager.getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			try {
				DriverManager.getDriver().findElement(locator);
				logger.info(displayText + " is displayed");
				ExtentReporter.extentLogger("", displayText + " is displayed");
				return true;
			} catch (Exception e) {
				try {
					js.executeScript("window.scrollBy(0,100)", "");
					waitTime(2000);
				} catch (Exception e1) {
					logger.error(e);
				}
			}
		}
		return false;
	}
	public static void BrowsertearDown() {
		getWebDriver().quit();
	}
	public void decode() {
		CTUserName = new String(Base64.getDecoder().decode(getParameterFromXML("CTUser")));
		CTPWD = new String(Base64.getDecoder().decode(getParameterFromXML("CTPwd")));
	}
	/**
	 * The method will wait for the element to not be located for a maximum of given
	 * seconds. The method terminates immediately once the element is located and
	 * throws error.
	 */
	public static void waitForElementAbsence(By locator, int seconds, String message) throws InterruptedException {
		main: for (int time = 0; time <= seconds; time++) {
			try {
				Utilities.findElement(locator);
				logger.error("Located element " + message);
				ExtentReporter.extentLoggerFail("locatedElement", "Located element " + message);
				break main;
			} catch (Exception e) {
				Thread.sleep(1000);
				if (time == seconds) {
					logger.info("Expected behavior: " + message + " is not displayed");
					ExtentReporter.extentLogger("failedLocateElement",
							"Expected behavior: " + message + " is not displayed");
				}
			}
		}
	}
	/**
	 * This method will wait for element presence till the given time
	 * @param locator
	 * @param seconds
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public static boolean waitForElementPresence(By locator, int seconds, String message) throws Exception {
		try {
			if (platform.equalsIgnoreCase("web")) {
				WebDriverWait w = new WebDriverWait(DriverManager.getDriver(), seconds);
				w.until(ExpectedConditions.presenceOfElementLocated(locator));
				logger.info(message + " is displayed");
				ExtentReporter.extentLogger("element is displayed", message + " is displayed");
				return true;
			} else if (platform.equalsIgnoreCase("mpwa")) {
				WebDriverWait w = new WebDriverWait(DriverManager.getAppiumDriver(), seconds);
				w.until(ExpectedConditions.presenceOfElementLocated(locator));
				logger.info(message + " is displayed");
				ExtentReporter.extentLogger("element is displayed", message + " is displayed");
				return true;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return false;
	}
	/**
	 * This method will wait for element presence till the given time
	 * @param locator
	 * @param seconds
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public static boolean waitForElementVisible(By locator, int seconds, String message) throws Exception {
		try {
			WebDriverWait w = new WebDriverWait(DriverManager.getDriver(), seconds);
			w.until(ExpectedConditions.visibilityOfElementLocated(locator));
			logger.info(message + " is displayed");
			ExtentReporter.extentLogger("element is displayed", message + " is displayed");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * Generic method to return browser current url
	 * @return
	 * @throws Exception
	 */
	public static String getBrowserCurrentUrl() throws Exception {
		try {
			return (DriverManager.getDriver().getCurrentUrl());
		} catch (Exception e) {
			return "";
		}
	}
	/**
	 * Function to switch to window
	 * @param noOfWindows
	 */
	public static void switchToWindow(int noOfWindows) {
		try {
			wait.until(ExpectedConditions.numberOfWindowsToBe(noOfWindows));
			for (String winHandle : DriverManager.getDriver().getWindowHandles()) {
				win.add(winHandle);
				DriverManager.getDriver().switchTo().window(winHandle);
				DriverManager.getDriver().manage().window().maximize();
			}
		} catch (Exception e) {
			logger.error("\n No window is displayed!");
		}
	}
	public static void fullScreen() {
		try {
			DriverManager.getDriver().manage().window().fullscreen();
		} catch (Exception e) {
			logger.error("Failed to full screen");
		}
	}
	/**
	 * Method to Move to Element using Actions
	 * @param element
	 * @throws Exception
	 */
	public static void moveToElementAction(WebElement element, String message) throws Exception {
		try {
			Actions a = new Actions(DriverManager.getDriver());
			a.moveToElement(element).build().perform();
			logger.info("Moved to element " + message);
			ExtentReporter.extentLogger("", "Moved to element " + message);
		} catch (Exception e) {
			logger.error("Failed to move to element " + message);
			ExtentReporter.extentLoggerFail("", "Failed to move to element " + message);
		}
	}
	public static void waitUntilElementVisible_NoCustomMessage(By by) {
		String platform = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("Platform");
		try {
			if (platform.equalsIgnoreCase("web")) {
				WebDriverWait wait = new WebDriverWait(getWebDriver(), 20);
				wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			}
			if (platform.equalsIgnoreCase("Android")||platform.equalsIgnoreCase("BrowserStack")||platform.equalsIgnoreCase("iOS")) {
				WebDriverWait wait = new WebDriverWait(DriverManager.getAppiumDriver(), 20);
				wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			}
		} catch (Exception e) {
			throw new ElementNotVisibleException(getCustomErrorMessageForReport(by), e.getCause());
		}
	}
	public static boolean isPresentWithWait(By by) {
		boolean flag = true;
		try {
			waitUntilElementVisible_NoCustomMessage(by);
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	public static String getCustomErrorMessageForReport(By by) {
		String className = by.getClass().getSimpleName();
		String value = "";
		try {
			Field field = by.getClass().getDeclaredFields()[1];
			field.setAccessible(true);
			value = field.get(by).toString();
		} catch (Exception ignored) {
			ignored.printStackTrace();
		}
		return "Timed out waiting for visibility of element located = " + className + "(" + value + ")";
	}
	public static void waitForElementAndClick(By locator, int seconds, String message) throws Exception {
		main: for (int time = 0; time <= seconds; time++) {
			try {
				if (platform.equalsIgnoreCase("web")) {
					DriverManager.getDriver().findElement(locator).click();
				} else if (platform.equalsIgnoreCase("mpwa")) {
					DriverManager.getAppiumDriver().findElement(locator).click();
				}
				logger.info("Clicked element " + message);
				ExtentReporter.extentLogger("clickedElement", "Clicked element " + message);
				break main;
			} catch (Exception e) {
				Thread.sleep(1000);
				if (time == seconds) {
					logger.error("Failed to click element " + message);
					ExtentReporter.extentLoggerFail("failedClickElement", "Failed to click element " + message);
				}
			}
		}
	}
	/**
	 * Function to select by Locator text from drop down
	 * @param byLocator
	 * @param value
	 * @throws Exception
	 */
	public static void selectByVisibleTextByLocator(By byLocator, String value) throws Exception {
		explicitWaitVisibility(byLocator, 20);
		Select select = new Select(findElement(byLocator));
		select.selectByVisibleText(value);
	}
	public static void selectByValue(By byLocator, String value) throws Exception {
		explicitWaitVisibility(byLocator, 20);
		Select select = new Select(findElement(byLocator));
		select.selectByValue(value);
	}
	/**
	 * Robot Function to press key
	 * @throws Exception
	 */
	public static void robotClassUp() throws Exception {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_UP);
		robot.keyRelease(KeyEvent.VK_PAGE_UP);
	}
	/**
	 * Robot Function to press key
	 * @throws Exception
	 */
	public static void robotClassDown() throws Exception {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
	}
	/**
	 * Function to switch to window
	 * @param frame_id
	 */
	public static void switchFrame_id(String frame_id) {
		getWebDriver().switchTo().frame(frame_id);
	}
	public static void switchFrame_parent() {
		DriverManager.getDriver().switchTo().parentFrame();
	}
	/**
	 * Method to Move to Element using Actions and click
	 * @param byLocator
	 * @throws Exception
	 */
	public static void moveToElementActionAndClick(By byLocator, String message) throws Exception {
		try {
			WebElement element = DriverManager.getDriver().findElement(byLocator);
			Actions a = new Actions(DriverManager.getDriver());
			a.moveToElement(element).click().build().perform();
			logger.info("Moved to element " + message);
			ExtentReporter.extentLogger("", "Moved to element and Click " + message);
		} catch (Exception e) {
			logger.error("Failed to move to element and click " + message);
			ExtentReporter.extentLoggerFail("", "Failed to move to element " + message);
		}
	}
	/**
	 * This method will wait for element absence till the given time
	 * @param locator
	 * @param seconds
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public static boolean waitForElementInVisible(By locator, int seconds, String message) throws Exception {
		try {
			WebDriverWait w = new WebDriverWait(DriverManager.getDriver(), seconds);
			w.until(ExpectedConditions.invisibilityOfElementLocated(locator));
			logger.info(message + " is displayed");
			ExtentReporter.extentLogger("element is displayed", message + " is displayed");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public static void Swipe(String direction, int count) {
		touchAction = new TouchAction(DriverManager.getAppiumDriver());
		String dire = direction;
		try {
			if (dire.equalsIgnoreCase("LEFT")) {
				for (int i = 0; i < count; i++) {
					Dimension size = DriverManager.getAppiumDriver().manage().window().getSize();
					int startx = (int) (size.width * 0.5);
					int endx = (int) (size.width * 0.1);
					int starty = size.height / 2;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(endx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
					ExtentReporter.extentLogger("SwipeLeft",
							"Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
				}
			} else if (dire.equalsIgnoreCase("RIGHT")) {
				for (int j = 0; j < count; j++) {
					Dimension size = DriverManager.getAppiumDriver().manage().window().getSize();
					int endx = (int) (size.width * 0.8);
					int startx = (int) (size.width * 0.20);
					if (size.height > 2000) {
						int starty = (int) (size.height / 2);
						touchAction.press(PointOption.point(startx, starty))
								.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
								.moveTo(PointOption.point(endx, starty)).release().perform();
					} else {
						int starty = (int) (size.height / 1.5);
						touchAction.press(PointOption.point(startx, starty))
								.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
								.moveTo(PointOption.point(endx, starty)).release().perform();
					}
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					ExtentReporter.extentLogger("SwipeRight",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				}
			} else if (dire.equalsIgnoreCase("UP")) {
				for (int j = 0; j < count; j++) {
					Dimension size = DriverManager.getAppiumDriver().manage().window().getSize();
					int starty = (int) (size.height * 0.80);// 0.8
					int endy = (int) (size.height * 0.20);// 0.2
					int startx = size.width / 2;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000)))
							.moveTo(PointOption.point(startx, endy)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					ExtentReporter.extentLogger("SwipeUp",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				}
			} else if (dire.equalsIgnoreCase("DOWN")) {
				for (int j = 0; j < count; j++) {
					Dimension size = DriverManager.getAppiumDriver().manage().window().getSize();
					int starty = (int) (size.height * 0.70);
					int endy = (int) (size.height * 0.30);
					int startx = size.width / 2;
					touchAction.press(PointOption.point(startx, endy))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(startx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					ExtentReporter.extentLogger("SwipeDown",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}
	public static void assertionValidation(String actual, String expected, String validationText) throws Exception {
		softAssert.assertEquals(actual, expected);
		if (actual.equals(expected)) {
			logger.info(actual + " and " + expected + " "+validationText+" are matched");
			ExtentReporter.extentLoggerPass("Assertion", actual + " and " + expected + " "+validationText+" are matched");
		} else {
			logger.info(actual + " and " + expected + " "+validationText+" are not matched");
			ExtentReporter.extentLoggerFail("Assertion", actual + " and " + expected + " "+validationText+" are not matched");
		}
	}

	public static void containsValidation(String actual, String expected, String validationText) throws Exception {
		if (actual.contains(expected)) {
			softAssert.assertEquals(actual.contains(expected), true);
			logger.info(actual + " "+validationText+" contains " + expected);
			ExtentReporter.extentLoggerPass("Assertion", actual + " "+validationText+" contains " + expected);
		} else {
			logger.info(actual + " "+validationText+" doesn't contains " + expected);
			ExtentReporter.extentLoggerFail("Assertion", actual + " "+validationText+" doesn't contains " + expected);
			Assert.assertEquals(actual.equals(expected), false);
		}
	}
	public static void scrollToFirstHorizontalScrollableElement(String textToSearch) {
		DriverManager.getAppiumDriver().findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true)).setAsHorizontalList().scrollIntoView(new UiSelector().text(\""
						+ textToSearch + "\"))"));
	}
	public static void scrollToVertical(String text) {
		if(getPlatform().equalsIgnoreCase("ios")){
			for(int i=0;i<5;i++){
				try{
					if (verifyElementDisplayed(By.xpath("//*[contains(@name,'"+text+"')] | //*[contains(@value,'"+text+"')]"))){
						logger.info("Swiped until the element " + text + " displayed");
						break;
					} else {
						swipe("UP", 1);
					}
				} catch(Exception e){
					swipe("UP", 1);
				}
			}
		} else {
			((FindsByAndroidUIAutomator<MobileElement>) DriverManager.getAppiumDriver()).findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
							+ text + "\").instance(0))");
			logger.info("Swiped until the element " + text + " displayed");
		}
	}
	public static void handleOtp(String otp) throws IOException, InterruptedException {
		for (int i = 0; i < otp.length(); i++) {
			char ch = otp.charAt(i);
			Thread.sleep(1000);
			String cmd = "adb shell input text " + ch + "";
			Thread.sleep(2000);
			Runtime.getRuntime().exec(cmd);
		}
		logger.info("Entered OTP " + otp + " Successfully");
		ExtentReporter.extentLogger("Enter OTP", "Entered OTP " + otp + " Successfully");
	}
	public static MobileElement scrollToElement(String an, String av) {
		return (MobileElement) DriverManager.getAppiumDriver().findElement(MobileBy
				.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(" + an + "(\"" + av + "\"))"));
	}
	public static void switchPlatformToWeb(String url) {
		new DriverInstance().LaunchBrowser("Chrome","");
		waitTime(5000);
		if(!url.equalsIgnoreCase(""))
			getWebDriver().get(url);
		new Utilities().setPlatform("Web");
	}
	public static void switchPlatformToAndroid() {
		waitTime(6000);
		new Utilities().setPlatform("Android");
	}
	public static void closeWebBrowser() {
		waitTime(5000);
		getWebDriver().close();
	}
	/**
	 * @tap on the center of center for tour dashboard
	 * @param locator
	 * @return
	 * @throws Exception
	 */
	public static int tapOnFirstSuggestion(By locator) throws Exception {
		WebElement element = DriverManager.getAppiumDriver().findElement(locator);
		String tourText = getText(locator);
		Point point = element.getLocation();
		int length = element.getSize().getWidth();
		int height = element.getSize().getHeight();
		int getY = point.getY();
		int middleY = (int) (getY + height * 1.5);
		TouchAction ta = new TouchAction(DriverManager.getAppiumDriver());
		ta.tap(PointOption.point(length / 2, middleY)).perform();
		logger.info(tourText + " dashboard tour is displayed Tapped at the center of Screen");
		ExtentReporter.extentLoggerPass("Tour",tourText + " dashboard tour is displayed Tapped at the center of Screen");
		return middleY;
	}
	public static void suggestionTapMidScreen(By locator) throws Exception {
		int guideSuggestion = 1;
		for (int i = 0; i < guideSuggestion; i++) {
			tapOnFirstSuggestion(locator);
			if (verifyElementDisplayed(locator)) {
				guideSuggestion++;
			} else {
				break;
			}
		}
	}
	public static boolean verifyElementAvailable(By byLocator, String validationtext) throws Exception {
		if (isPresentWithWait(byLocator)) {
			WebElement element = findElement(byLocator);
			logger.info(validationtext + " is displayed");
			ExtentReporter.extentLoggerPass("checkElementPresent", validationtext + " is displayed");
			return true;
		} else {
			logger.info(validationtext + " is not displayed");
			ExtentReporter.extentLogger("checkElementPresent", validationtext + " is not displayed");
			return false;
		}
	}
	public static void SwipeBanner(By byLocator, String direction, int k) throws Exception {
		TouchAction action = new TouchAction(DriverManager.getAppiumDriver());
		waitTime(1000);
		WebElement ele = DriverManager.getAppiumDriver().findElement(byLocator);
		Dimension size = DriverManager.getAppiumDriver().manage().window().getSize();
		int startX = Math.toIntExact(Math.round(size.getWidth() * 0.70));
		int startY = Math.toIntExact(Math.round(size.getHeight() * 0.25));
		int endX = Math.toIntExact(Math.round(size.getWidth() * 0.10));
		int endY = 0;
		for (int i = 1; i <= k; i++) {
			if (direction.equalsIgnoreCase("Left")) {
				action.press(PointOption.point(startX, startY))
						.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
						.moveTo(PointOption.point(endX, startY)).release().perform();
				logger.info("Swiping Banner in " + " " + direction + " direction");
				ExtentReporter.extentLogger("SwipeLeft", "Swiping Banner in " + " " + direction + " direction");
			} else {
				action.press(PointOption.point(startX, endY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
						.moveTo(PointOption.point(startX, startY)).release().perform();
			}
		}
	}
	public static void tapUsingCoordinates(int x, int y) throws Exception {
		TouchAction t = new TouchAction(DriverManager.getAppiumDriver());
		try {
			t.tap(PointOption.point(x, y)).perform().release();
			logger.info("Tapped on " + x + "," + y + " co-ordinates");
			ExtentReporter.extentLoggerPass("Tap", "Tapped on " + x + " , " + y + " co-ordinates");
		} catch (Exception e) {
			logger.info("Failed to tap on" + x + "," + y + " co-ordinates");
			ExtentReporter.extentLoggerFail("Tap", "Failed to Tap on" + x + "," + y + " co-ordinates");
		}
	}
	public static void typeWeb(By byLocator, String value, String FieldName) {
		try {
			waitTime(2000);
			if (!getPlatform().equals("Web")) {
				Actions a = new Actions(getDriver());
				a.sendKeys(value);
				a.perform();
			} else {
				WebElement element = findElement(byLocator);

				element.sendKeys(value);
			}
			value = value.split("\n")[0];
			logger.info("Typed the value " + value + " into " + FieldName);
			ExtentReporter.extentLoggerPass("", "Typed the value " + value + " into " + FieldName);
		} catch (Exception e) {
			logger.error(e);
		}
	}
	public static void clearWebField() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_A);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_A);
		r.keyPress(KeyEvent.VK_BACK_SPACE);
		r.keyRelease(KeyEvent.VK_BACK_SPACE);
	}
	public static boolean isClickable(By locator,String str) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getAppiumDriver(), 5);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			logger.info(str+" Element is clickable");
			ExtentReporter.extentLoggerPass("Clickable", str+" Element is clickable");
			return true;
		} catch (Exception e) {
			logger.info(str+" Element is not clickable");
			ExtentReporter.extentLoggerPass("Clickable", str+" Element is not clickable");
			return false;
		}
	}
	/**
	 * clear the text field
	 * @param byLocator
	 */
	public static void clearWebField(By byLocator, String text) {
		try {
			findElement(byLocator).clear();
			logger.info("Cleared the text in : " + text);
			ExtentReporter.extentLogger("clear text", "Cleared the text in : " + text);
		} catch (Exception e) {
			logger.error(e);
		}
	}
	/**
	 * Mouse Hover
	 * @param byLocator
	 * @throws Exception
	 */
	public static void mouseHover(By byLocator) throws Exception {
		WebElement ele = findElement(byLocator);
		Actions action = new Actions(getWebDriver());
		action.moveToElement(ele).perform();
	}
	public static void deleteRecentDownloadedFile() {
		String cmd1 = "adb shell ls /sdcard/download";
		String osName=System.getProperty("os.name").toLowerCase();
		Process listName;
		try {
			if(osName.contains("mac")) {
				listName =Runtime.getRuntime().exec(new String[] {"bash","-l","-c",cmd1});
			}else {
				listName = Runtime.getRuntime().exec(cmd1);
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(listName.getInputStream()));
			String fileList = br.readLine();
			while (fileList != null) {
				if (fileList.contains("frame")) {
					String cmd2 = "adb shell rm -f /sdcard/Download/" + fileList;
					if(osName.contains("mac")) {
						Runtime.getRuntime().exec(new String[] {"bash","-l","-c",cmd2});
					}else {
						Runtime.getRuntime().exec(cmd2);
					}
					logger.info("File deleted");
					ExtentReporter.extentLogger("Delete", "File deleted");
				}
				fileList = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void swipeTheElementHorizontally(By locator) throws Exception {
		WebElement element = findElement(locator);
		int centerX = element.getLocation().getX() + element.getSize().getWidth() / 2;
		int centerY = element.getLocation().getY() + element.getSize().getHeight() / 2;
		int startX = centerX;
		int endX = centerX - 800;
		int startY = centerY;
		int endY = centerY;
		TouchAction action = new TouchAction(DriverManager.getAppiumDriver());
		action.press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
				.moveTo(PointOption.point(endX, endY)).release().perform();
	}
	public static void launchMobileChromeAppWithURL(String url) throws MalformedURLException {
		new DriverInstance().pwaLauch();
		DriverManager.getAppiumDriver().get(url);
	}
	/**
	 * @tap on the center of center for tour dashboard
	 * @param locator
	 * @return
	 * @throws Exception
	 */
	public static int tapOnSuggestion(By locator) throws Exception {
		WebElement element = DriverManager.getAppiumDriver().findElement(locator);
		String tourText = getText(locator);
		Point point = element.getLocation();
		int length = element.getSize().getWidth();
		int height = element.getSize().getHeight();
		int getY = point.getY();
		int middleY = (int) (getY + height * 1.5);
		TouchAction ta = new TouchAction(DriverManager.getAppiumDriver());
		ta.tap(PointOption.point(length / 2, middleY)).perform();
		return middleY;
	}
	/**
	 * @return
	 * @throws ParseException
	 * @method Get current date and time
	 */
	public static String getCurrentDateTime(String continentCountry) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy hh:mm a");
		TimeZone philippines = TimeZone.getTimeZone(continentCountry);
		formatter.setTimeZone(philippines);
		return formatter.format(new Date());
	}
	/**
	 * @return
	 * @throws ParseException
	 * @method time
	 */
	public static String getTime(String continentCountry) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
		TimeZone philippines = TimeZone.getTimeZone(continentCountry);
		formatter.setTimeZone(philippines);
		return formatter.format(new Date());
	}
	public static void findElementAndVerifyText(By byLocator, String expectedtext)throws Exception {
		WebElement element = null;
		try {
			element = DriverManager.getAppiumDriver().findElement(byLocator);
			String nameWithProperSpacing = element.getText().replaceAll("\\s+", " ");
			explicitWaitVisibility(byLocator, 20);
			if (element.isDisplayed()) {
				softAssert.assertEquals(nameWithProperSpacing, expectedtext);
				logger.info(nameWithProperSpacing + " is displayed and matched");
				ExtentReporter.extentLoggerPass("Pass",nameWithProperSpacing+ " is displayed and matched");
			} else {
				logger.error(element.getText() +" is not displayed and matched");
				ExtentReporter.extentLoggerFail("Fail", nameWithProperSpacing + " is not displayed and matched");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			ExtentReporter.extentLoggerFail("testcase", e.getMessage());
		}
	}
	public static void findElementAndVerifyText(By byLocator, String expectedtext, String title)throws Exception {
		WebElement element = null;
		try {
			element = DriverManager.getAppiumDriver().findElement(byLocator);
			String nameWithProperSpacing = element.getText().replaceAll("\\s+", " ");
			explicitWaitVisibility(byLocator, 20);
			if (element.isDisplayed()) {
				softAssert.assertEquals(nameWithProperSpacing, expectedtext);
				logger.info(nameWithProperSpacing + " "+ title+ " is displayed and matched");
				ExtentReporter.extentLoggerPass("Pass",nameWithProperSpacing +" "+title+" is displayed and matched");
			} else {
				logger.error(element.getText() +" "+ title+" is not displayed and matched");
				ExtentReporter.extentLoggerFail("Fail", nameWithProperSpacing +" "+title+" is not displayed and matched");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			ExtentReporter.extentLoggerFail("testcase", e.getMessage());
		}
	}
	/**
	 * @return
	 * @throws ParseException
	 * @method time
	 */
	public static String getDay(String continentCountry) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd");
		TimeZone philippines = TimeZone.getTimeZone(continentCountry);
		formatter.setTimeZone(philippines);
		return formatter.format(new Date());
	}
	public static void swipeDownUntilElementVisible(String text) {
		((FindsByAndroidUIAutomator<MobileElement>) DriverManager.getAppiumDriver()).findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));");
		logger.info("Swiped until the element " + text + " displayed");
	}
	public static boolean swipeElementAndroid(By particularBanner, By tillElement) throws Exception {
		boolean elementFound = false;
		WebElement banner = DriverManager.getAppiumDriver().findElement(particularBanner);
		TouchAction action = new TouchAction(DriverManager.getAppiumDriver());
		Dimension screenSize = DriverManager.getAppiumDriver().findElement(particularBanner).getSize();
		int startX = Math.toIntExact(Math.round(screenSize.getWidth() * 0.50));
		int startY = Math.toIntExact(Math.round(screenSize.getHeight() * 0.50));
		int endX = Math.toIntExact(Math.round(screenSize.getWidth() * 0.10));
		int endY = 0;
		for (int i = 0; i <= 4; i++) {
			try {
				WebElement element = findElement(tillElement);
				elementFound = element.isDisplayed();
			} catch (Exception e) {
				if (elementFound == false) {
					action.press(PointOption.point(startX, startY))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
							.moveTo(PointOption.point(endX, startY)).release();
					DriverManager.getAppiumDriver().performTouchAction(action);
				}
				elementFound = false;
			}
			if (elementFound == true)
				break;
		}
		return elementFound;
	}
	public static boolean verifyElementText(String expectedtext, String actualtext) throws Exception {
		try {
			if (actualtext.contains(expectedtext)) {
				ExtentReporter.extentLoggerPass("checkText", "<b>" + expectedtext + "</b> is displayed");
				logger.info("" + expectedtext + " is displayed");
				return true;
			}
		} catch (Exception e) {
			ExtentReporter.extentLoggerFail("checkText", "<b>" + expectedtext + "</b> is displayed");
			logger.info(expectedtext + " is not displayed");
			return false;
		}
		return false;
	}
	/**
	 * Function Scroll to Element
	 * @param element
	 * @throws Exception
	 */
	public static void ScrollToTheElementWeb(By element, String message) throws Exception {
		try {
			if (getPlatform().equalsIgnoreCase("web") || getPlatform().equalsIgnoreCase("mpwa")) {
				js = (JavascriptExecutor) getWebDriver();
				js.executeScript("arguments[0].scrollIntoView(true);", getWebDriver().findElement(element));
				js.executeScript("window.scrollBy(0,-50)", "");
			} else {
				js = (JavascriptExecutor) DriverManager.getDriver();
				js.executeScript("arguments[0].scrollIntoView(true);", DriverManager.getDriver().findElement(element));
				js.executeScript("window.scrollBy(0,-50)", "");
			}
			logger.info("Scrolled to element " + message);
			ExtentReporter.extentLogger("ScrollToElement", "Scrolled to element " + message);
		} catch (Exception e){
			logger.error(e);
		}
	}
	/**
	 * Function Scroll to Element
	 * @param element
	 * @throws Exception
	 */
	public static void ScrollUpToTheElementWeb(By element, String message) throws Exception {
		try {
			if (getPlatform().equalsIgnoreCase("web") || getPlatform().equalsIgnoreCase("web")) {
				js = (JavascriptExecutor) getWebDriver();
				js.executeScript("arguments[0].scrollIntoView(true);", getWebDriver().findElement(element));
				js.executeScript("window.scrollBy(-50,0)", "");
			} else {
				js = (JavascriptExecutor) DriverManager.getDriver();
				js.executeScript("arguments[0].scrollIntoView(true);", DriverManager.getDriver().findElement(element));
				js.executeScript("window.scrollBy(0,-50)", "");
			}
			logger.info("Scrolled to element " + message);
			ExtentReporter.extentLogger("ScrollToElement", "Scrolled to element " + message);
		} catch (Exception e) {
			logger.error(e);
		}
	}
	public static boolean verifyElementNotDisplayed(By byLocator, String str) throws Exception {
		WebElement element;
		try {
			if(getPlatform().equalsIgnoreCase("web")) {
				element = getWebDriver().findElement(byLocator);
				explicitWaitVisibility(byLocator, 20);
				if (element.isDisplayed()) {
					softAssert.assertEquals(element.isDisplayed(), true, "" + str + " " + " is displayed");
					ExtentReporter.extentLogger("checkElementPresent", "" + str + " is displayed");
					logger.info("" + str + " is displayed");
					return false;
				}
			}else if(getPlatform().equalsIgnoreCase("MPWAiOSSafari")){
				boolean flag = true;
				for(int i=0;i<10;i++){
					try {
						Thread.sleep(5000);
						DriverManager.getAppiumDriver().findElement(byLocator).isDisplayed();
						flag=false;
					} catch (Exception e) {
						break;
					}
				}
				if(!flag){
					ExtentReporter.extentLogger("checkElementPresent", "" + str + " is displayed");
					logger.info("" + str + " is displayed");
				} else {
					ExtentReporter.extentLogger("checkElementPresent", "" + str + " is not displayed");
					logger.info("" + str + " is not displayed" + "");
				}
				return flag;
			}else {
				element = DriverManager.getAppiumDriver().findElement(byLocator);
				explicitWaitVisibility(byLocator, 20);
				if (element.isDisplayed()) {
					softAssert.assertEquals(element.isDisplayed(), true, "" + str + " " + " is displayed");
					ExtentReporter.extentLogger("checkElementPresent", "" + str + " is displayed");
					logger.info("" + str + " is displayed");
					return false;
				}
			}
		} catch (Exception e) {
			ExtentReporter.extentLogger("checkElementPresent", "" + str + " is not displayed");
			logger.info(str + " is not displayed");
			return true;
		}
		return false;
	}
	public static String generateRandomEmail() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder randomString = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			int index = new Random().nextInt(characters.length());
			randomString.append(characters.charAt(index));
		}
		String domain = "xyz.com"; // Set the domain
		return randomString.toString() + "@" + domain;
	}
	/**
	 * Vertical Swipe till element is displayed
	 * @param byElement
	 * @return
	 * @throws Exception
	 */
	public static boolean verticalSwipeTillElementDispalyed(By byElement) {
		AppiumDriver driver = DriverManager.getAppiumDriver();
		boolean flag = false;
		int width = driver.manage().window().getSize().getWidth();
		int height = driver.manage().window().getSize().getHeight();
		int startx = width / 2;
		int endx = startx;
		int starty = (height / 4) * 3;
		int endy = (height / 8) * 3;
		int expectedPosition = height / 2;
		for (int i = 0; i < 10; i++) {
			try {
				driver.findElement(byElement).isDisplayed();
				flag= true;
				break;
			} catch (Exception e) {
				TouchAction act = new TouchAction(driver);
				(new TouchAction(driver)).press(PointOption.point(startx, starty))
						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
						.moveTo(PointOption.point(startx, endy)).release().perform();
			}
		}
		return flag;
	}
	public static boolean verticalSwipeTillElementDispalyed1(WebElement element) {
		AppiumDriver driver = DriverManager.getAppiumDriver();
		boolean flag = false;
		Dimension size = DriverManager.getAppiumDriver().manage().window().getSize();
		int starty = (int) (size.height * 0.80);// 0.8
		int endy = (int) (size.height * 0.20);// 0.2
		int startx = size.width / 2;
		for (int i = 0; i < 10; i++) {
			try {
				element.isDisplayed();
				flag= true;
				break;
			} catch (Exception e) {
				PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "Fingername");
				Sequence sequence = new Sequence(finger1, 1)
						.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty))
						.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
						.addAction(new Pause(finger1, Duration.ofMillis(2000)))
						.addAction(finger1.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), startx, endy))
						.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
				driver.perform(Collections.singletonList(sequence));
				logger.info("swipe till element");
				ExtentReporter.extentLogger("swipe", "swipe till element");
			}
		}
		return flag;
	}
	/**
	 * Tap on screen using Bounds
	 * @throws Exception
	 */
	public static boolean tapOnScreenUsingBounds(int leftX, int topY, int rightX, int bottomY, String message) {
		AppiumDriver driver = DriverManager.getAppiumDriver();
		boolean flag = true;
		leftX = 176;
		topY = 1402;
		rightX = 808;
		bottomY = 1523;
		int centerX = (leftX + rightX) / 2;
		int centerY = (topY + bottomY) / 2;
		TouchAction touchAction = new TouchAction(driver);
		touchAction.tap(PointOption.point(centerX, centerY)).perform();
		logger.info("Clicked on "+message);
		ExtentReporter.extentLogger("Clicked", "Clicked on "+message);
		return flag;
	}
	public static void tapOnScreen(int x, int y, String message) {
		AppiumDriver driver = DriverManager.getAppiumDriver();
		try {
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence tap = new Sequence(finger, 0);
			tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
			tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			tap.addAction(new Pause(finger, Duration.ofMillis(200)));
			tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			driver.perform(Collections.singletonList(tap));
			logger.info("Clicked on "+message);
			ExtentReporter.extentLogger("Clicked", "Clicked on "+message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Tap on screen using Bounds
	 * @throws Exception
	 */
	public static boolean tapOnScreenUsingBounds(int centerX, int centerY, String message) {
		AppiumDriver driver = DriverManager.getAppiumDriver();
		boolean flag = true;
		TouchAction touchAction = new TouchAction(driver);
		touchAction.tap(PointOption.point(centerX, centerY)).perform();
		logger.info("Clicked on "+message);
		ExtentReporter.extentLogger("Clicked", "Clicked on "+message);
		return flag;
	}
	/**
	 * Tap on screen using Bounds
	 * @throws Exception
	 */
	public static boolean switchBackToTMOApp() {
		AppiumDriver driver = DriverManager.getAppiumDriver();
		if(platform.equalsIgnoreCase("Android")){
			driver.activateApp("com.tmobile.pr.mytmobile");
		} else if (platform.equalsIgnoreCase("iOS")){
			driver.activateApp("com.tmobile.TMOAccess");
		}
		return true;
	}
	/**
	 * Vertical Reverse Swipe till element is displayed
	 * @param byElement
	 * @return
	 * @throws Exception
	 */
	public static boolean verticalReverseSwipeTillElementDispalyed(By byElement) {
		AppiumDriver driver = DriverManager.getAppiumDriver();
		boolean flag = false;
		int width = driver.manage().window().getSize().getWidth();
		int height = driver.manage().window().getSize().getHeight();
		int startx = width / 2;
		int endx = startx;
		int starty = (height / 4) * 3;
		int endy = (height / 8) * 3;
		int expectedPosition = height / 2;
		for (int i = 0; i < 6; i++) {
			try {
				driver.findElement(byElement).isDisplayed();
				flag= true;
				break;
			} catch (Exception e) {
				TouchAction act = new TouchAction(driver);
				(new TouchAction(driver)).press(PointOption.point(startx, endy))
						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
						.moveTo(PointOption.point(startx, starty)).release().perform();
			}
		}
		return flag;
	}
	public static boolean verifyElementExistiOS(By byLocator, String str) throws Exception {
		boolean flag = false;
		for(int i=0;i<10;i++){
			try {
				DriverManager.getAppiumDriver().findElement(byLocator).isDisplayed();
				flag=true;
				break;
			} catch (Exception e) {
				Thread.sleep(1000);
			}
		}
		if(flag){
			ExtentReporter.extentLoggerPass("checkElementPresent", "" + str + " is displayed");
			logger.info("" + str + " is displayed");
		} else {
			ExtentReporter.extentLoggerFail("checkElementPresent", "" + str + " is not displayed");
			logger.error("\u001B[31m" + str + " is not displayed" + "\u001B[0m");
		}
		return flag;
	}
	public static boolean verifyElementNotExistiOS(By byLocator, String str) throws Exception {
		boolean flag = true;
		for(int i=0;i<10;i++){
			try {
				Thread.sleep(5000);
				DriverManager.getAppiumDriver().findElement(byLocator).isDisplayed();
				flag=false;
			} catch (Exception e) {
				break;
			}
		}
		if(!flag){
			ExtentReporter.extentLogger("checkElementPresent", "" + str + " is displayed");
			logger.info("" + str + " is displayed");
		} else {
			ExtentReporter.extentLogger("checkElementPresent", "" + str + " is not displayed");
			logger.info("" + str + " is not displayed" + "");
		}
		return flag;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void switchContextHandles(String ViewType) {
		Set<String> contextNames = DriverManager.getAppiumDriver().getContextHandles();
		for (String contextName : contextNames) {
			if (contextName.contains(ViewType)) {
				DriverManager.getAppiumDriver().context(contextName);
				break;
			}
		}
	}
	public static void typePWA(By byLocator, String input, String FieldName) throws Exception {
		try {
			WebElement ele = DriverManager.getAppiumDriver().findElement(byLocator);
			ele.sendKeys(input);
			input = input.split("\n")[0];
			logger.info("Typed the value " + input + " into " + FieldName);
			ExtentReporter.extentLoggerPass("", "Typed the value " + input + " into " + FieldName);
		} catch (Exception e) {
			logger.info("Not Typed the value " + input + " into " + FieldName);
			ExtentReporter.extentLoggerFail("", "Not Typed the value " + input + " into " + FieldName);
		}
	}
	public static int GetCoordinatesXForFirstProfileIcon() throws Exception {
		int x=0;
		if(getParameterFromXML("deviceName").equals("RZCW20MGVVK")){
			x=353;
		} else if(getParameterFromXML("deviceName").equals("ZY326L686M")){
			x=469;
		}
		return x;
	}
	public static String  propertyFileReader(String Data,String Module) {
		String osName=System.getProperty("os.name").toLowerCase();
		PropertyFileReader prop;
		String KeyValue;
		if(osName.contains("mac") || osName.contains("linux")){
			prop = new PropertyFileReader(".//properties//testdata//"+Module+".properties");
			KeyValue = prop.getproperty(Data);
		}else {
			prop = new PropertyFileReader(".\\properties\\testdata\\"+Module+".properties");
			KeyValue = prop.getproperty(Data);
		}
		return KeyValue;
	}
	public static boolean waitForElementToBePresent(By locator, int seconds, String message)
			throws Exception {
		try {
			if (getPlatform().equals("Web")) {
				for (int time = 0; time <= seconds; time++) {
					try {
						getWebDriver().findElement(locator);
						logger.info("Verified element " + message +" is present");
						ExtentReporter.extentLogger("verifyElement", "Verified element " + message+" is present");
						return true;
					} catch (Exception e) {
						Thread.sleep(1000);
					}
				}
			} else if (getPlatform().equals("Android") || getPlatform().equals("MPWA") || getPlatform().equals("iOS")) {
				for (int time = 0; time <= seconds; time++) {
					try {
						DriverManager.getAppiumDriver().findElement(locator);
						logger.info("Verified element " + message +" is present");
						ExtentReporter.extentLogger("verifyElement", "Verified element " + message+" is present");
						return true;
					} catch (Exception e) {
						Thread.sleep(1000);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
			screencapture();
		}
		return false;
	}
	public static String getTextVal(By byLocator, String concatValue) throws Exception {
		WebElement element;
		String Value = null;
		if(getPlatform().equalsIgnoreCase("Web")){
			element = getWebDriver().findElement(byLocator);

		}else {
			element = DriverManager.getAppiumDriver().findElement(byLocator);
		}
		Value = element.getText();
		String finalValue = Value + " " + concatValue;
		return finalValue;
	}
	public static void actionSendKeys(By byLocator,String input,String validationText) throws Exception {//*[@value='Source of Funds']/preceding-sibling::*)
		try{
			Actions a = new Actions(DriverManager.getAppiumDriver());
			WebElement ele = DriverManager.getAppiumDriver().findElement(byLocator);
			a.sendKeys(ele, input).perform();
			logger.info("Typed the value " + input + " into " + validationText);
			ExtentReporter.extentLoggerPass("", "Typed the value " + input + " into " + validationText);
		} catch (Exception e) {
			logger.info("Not Typed the value " + input + " into " + validationText);
			ExtentReporter.extentLoggerFail("", "Not Typed the value " + input + " into " + validationText);
		}
	}
	public static String  propertyFileReaderFromProperties(String Data) {
		String osName=System.getProperty("os.name").toLowerCase();
		PropertyFileReader prop;
		String KeyValue;
		if(osName.contains("mac") || osName.contains("linux")){
			prop = new PropertyFileReader(".//properties//testdata.properties");
			KeyValue = prop.getproperty(Data);
		}else {
			prop = new PropertyFileReader(".\\properties\\AppPackageActivity.properties");
			KeyValue = prop.getproperty(Data);
		}
		return KeyValue;
	}
	public static void relaunchApp( String platform) {
		try {
			switch (platform.toLowerCase()) {
				case "android": {
					DriverManager.getAppiumDriver().activateApp("com.tonik.mobile");
					logger.info("App relaunched successfully");
					break;
				}
				case "ios": {
					DriverManager.getAppiumDriver().terminateApp("com.mobile.tonik");
					Thread.sleep(3000);
					DriverManager.getAppiumDriver().activateApp("com.mobile.tonik");
					logger.info("App relaunched successfully");
					break;
				}
			}
		} catch (Exception e) {
			logger.info( "App Driver Failed to Relaunch");
		}
	}
	public static void closeAndroidTonikApp() {
		try {
			DriverManager.getAppiumDriver().terminateApp("com.tonik.mobile");
			ExtentReporter.extentLogger("Killed the application successfully", "Killed the application successfully");
			logger.info("The user Killed the application successfully");
		} catch (Exception e) {
			ExtentReporter.extentLogger("Unable to Kill the application", "Unable to Kill the application");
			logger.info("The user failed kill the application successfully");
		}
	}
	public static void updateSignature(){
		if(platform.equalsIgnoreCase("Android")) {
			WebElement element = DriverManager.getAppiumDriver().findElement(By.xpath("//*[contains(@text,'Sign here')]/preceding-sibling::android.widget.LinearLayout"));
			int centerX = element.getRect().getX() + (element.getRect().getWidth() / 2);
			int centerY = element.getRect().getY() + (element.getRect().getHeight() / 2);
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence longPress = new Sequence(finger, 1)
					.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), centerX, centerY))
					.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
					.addAction(new Pause(finger, Duration.ofSeconds(1))) // Long press for 1 second
					.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), 682, 1068))
					.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			DriverManager.getAppiumDriver().perform(Arrays.asList(longPress));
		}else{
			PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
			Sequence sequence = new Sequence(finger1, 0);
			sequence.addAction(finger1.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), 55, 319));
			sequence.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			sequence.addAction(new Pause(finger1, Duration.ofMillis(1000))); // Pause for 1 second
			sequence.addAction(finger1.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), 248, 497));
			sequence.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			DriverManager.getAppiumDriver().perform(Arrays.asList(sequence));
		}
	}
	public static void swipe(String direction, int count) {
		AppiumDriver driver = DriverManager.getAppiumDriver();
		touchAction = new TouchAction(DriverManager.getAppiumDriver());
		String dire = direction;
		try {
			if (dire.equalsIgnoreCase("LEFT")) {
				for (int i = 0; i < count; i++) {
					Dimension size = DriverManager.getAppiumDriver().manage().window().getSize();
					int startx = (int) (size.width * 0.8);
					int endx = (int) (size.width * 0.1);
					int starty = size.height / 2;
					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "Fingername");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(200)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endx, starty))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
					driver.perform(Arrays.asList(sequence));
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
					ExtentReporter.extentLogger("SwipeLeft",
							"Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
				}
			} else if (dire.equalsIgnoreCase("RIGHT")) {
				for (int j = 0; j < count; j++) {
					Dimension size = DriverManager.getAppiumDriver().manage().window().getSize();
					int endx = (int) (size.width * 0.8);
					int startx = (int) (size.width * 0.20);
					if (size.height > 2000) {
						int starty = (int) (size.height / 2);
						PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "Fingername");
						Sequence sequence = new Sequence(finger1, 1)
								.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty))
								.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
								.addAction(new Pause(finger1, Duration.ofMillis(200)))
								.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endx, starty))
								.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
						driver.perform(Collections.singletonList(sequence));
					} else {
						int starty = (int) (size.height / 1.5);
						PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "Fingername");
						Sequence sequence = new Sequence(finger1, 1)
								.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty))
								.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
								.addAction(new Pause(finger1, Duration.ofMillis(200)))
								.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endx, starty))
								.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
						driver.perform(Collections.singletonList(sequence));
					}
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					ExtentReporter.extentLogger("SwipeRight",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				}
			} else if (dire.equalsIgnoreCase("UP")) {
				for (int j = 0; j < count; j++) {
					Dimension size = DriverManager.getAppiumDriver().manage().window().getSize();
					int starty = (int) (size.height * 0.80);// 0.8
					int endy = (int) (size.height * 0.20);// 0.2
					int startx = size.width / 2;
					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "Fingername");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(200)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), startx, endy))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
					driver.perform(Collections.singletonList(sequence));
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					ExtentReporter.extentLogger("SwipeUp",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				}
			} else if (dire.equalsIgnoreCase("DOWN")) {
				for (int j = 0; j < count; j++) {
					Dimension size = DriverManager.getAppiumDriver().manage().window().getSize();
					int starty = (int) (size.height * 0.70);
					int endy = (int) (size.height * 0.30);
					int startx = size.width / 2;
					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "Fingername");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, endy))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(200)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), startx, starty))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
					driver.perform(Collections.singletonList(sequence));
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					ExtentReporter.extentLogger("SwipeUp", "Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}
	/**
	 * Function to Scroll to Text
	 * @param by
	 * @param attribute
	 * @param text
	 * @return
	 */
	public static boolean scroll_To_Text(By by, String attribute, String text) {
		int a = 0;
		boolean flag = false;
		do {
			List<WebElement> lists = findElements(by);
			for (WebElement e : lists) {
				if (e.getAttribute(attribute).equalsIgnoreCase(text)) {
					flag = true;
					break;
				}
			}
			if (!flag)
				newScrollMethodVertical(0.5, 0.8, 0.5, 0.2);
			a++;
			break;
		} while (!flag || a == 5);
		return flag;
	}
	/**
	 * Function to vertical scroll method
	 * @param start_xd
	 * @param start_yd
	 * @param end_xd
	 * @param end_yd
	 */
	public static void newScrollMethodVertical(double start_xd, double start_yd, double end_xd, double end_yd) {
		Dimension dimension = DriverManager.getAppiumDriver().manage().window().getSize();
		int start_x = (int) (dimension.getWidth() * start_xd); //0.5
		int start_y = (int) (dimension.getHeight() * start_yd); //0.8
		int end_x = (int) (dimension.getWidth() * end_xd);//0.5
		int end_y = (int) (dimension.getHeight() * end_yd);//0.2
		TouchAction touch = new TouchAction(DriverManager.getAppiumDriver());
		touch.press(PointOption.point(start_x, start_y)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
				.moveTo(PointOption.point(end_x, end_y)).release().perform();
	}
	/**
	 * Reusable method to generate Random OTP
	 * @return
	 */
	public static String generateRandomOTP(){
		Random rnd = new Random();
		int number1 = rnd.nextInt(999999);
		String number = String.valueOf(number1);
		return number;
	}
	/**
	 * Common methods to validate values, equals and contains
	 * @param actual
	 * @param expected
	 * @param validationText
	 * @param compare
	 * @throws Exception
	 */
	public static void valueValidation(String actual, String expected, String validationText,String compare) throws Exception {
		try {
			if(compare.equals("equals")) {
				softAssert.assertEquals(actual, expected);
				logger.info(actual + " and " + expected + " "+validationText+" are matched");
				ExtentReporter.extentLoggerPass("Assertion", actual + " and " + expected + " "+validationText+" are matched");
			}
			else if(compare.equals("contains")){
				logger.info(actual + " "+validationText+" contains " + expected);
				ExtentReporter.extentLoggerPass("Assertion", actual + " "+validationText+" contains " + expected);
				softAssert.assertEquals(actual.contains(expected), true, expected+" does not match");
			}
		}
		catch(Exception e) {
			logger.info(actual + " and " + expected + " "+validationText+" are not matched");
			ExtentReporter.extentLoggerFail("Assertion", actual + " and " + expected + " "+validationText+" are not matched");
		}
	}
	public static boolean verifyElementDisabled(By byLocator, String str) throws Exception {
		try {
			WebElement element = DriverManager.getAppiumDriver().findElement(byLocator);
			element.isEnabled();
			ExtentReporter.extentLogger("checkElementPresent", "" + str + " is not disabled");
			logger.info("" + str + " is not disabled");
			return false;
		} catch (Exception e) {
			ExtentReporter.extentLogger("checkElementPresent", "" + str + " is disabled");
			logger.info(str + " is disabled");
			return true;
		}
	}
	/**
	 * Function to generate random password
	 * @return
	 */
	public static String RandomPassword() {
		String number = "0123456789";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			int index = (int) (number.length() * Math.random());
			sb.append(number.charAt(index));
		}
		String domain = "Uat";
		return domain +"@" + sb.toString();
	}
	/**
	 * Common method to get current date
	 * @return
	 */
	public static String getCurrentDate() {
		Date current = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy ", Locale.ENGLISH);
		TimeZone timeZone = TimeZone.getTimeZone("Asia/Singapore");
		simpleDateFormat.setTimeZone(timeZone);
		String date = simpleDateFormat.format(current);
		return date;
	}
	public static void isButtonEnabled(By element,String buttonName) throws Exception {
		WebElement button = findElement(element);
		boolean sButton=button.isEnabled();
		if(sButton) {
			logger.info(buttonName+" button is enabled state");
			extentLoggerPass("Month Options", buttonName+" button is enabled state");
		} else {
			logger.info(buttonName+" button is disabled state");
			extentLoggerPass("Month Options", buttonName+" button is disabled state");
		}
	}
	/**
	 * Assertion validation for Double Type
	 * @param actual
	 * @param expected
	 * @param validationText
	 * @throws Exception
	 */
	public static void assertionValidationForDoubleType(double actual, double expected, String validationText) throws Exception {
		softAssert.assertEquals(actual, expected);
		if (Objects.equals(actual, expected)) {
			logger.info(actual + " and " + expected + " "+validationText+" are matched");
			ExtentReporter.extentLoggerPass("Assertion", actual + " and " + expected + " "+validationText+" are matched");
		} else {
			logger.info(actual + " and " + expected + " "+validationText+" are not matched");
			ExtentReporter.extentLoggerFail("Assertion", actual + " and " + expected + " "+validationText+" are not matched");
		}
	}
	public static void assertionValidationForIntegerType(int actual, int expected, String validationText) throws Exception {
		softAssert.assertEquals(actual, expected);
		if (Objects.equals(actual, expected)) {
			logger.info(actual + " and " + expected + " "+validationText+" are matched");
			ExtentReporter.extentLoggerPass("Assertion", actual + " and " + expected + " "+validationText+" are matched");
		} else {
			logger.info(actual + " and " + expected + " "+validationText+" are not matched");
			ExtentReporter.extentLoggerFail("Assertion", actual + " and " + expected + " "+validationText+" are not matched");
		}
	}
	/**
	 * Input text into the field
	 * @param keyevent
	 */
	public static void adbInputText(int keyevent) {
		try {
			String cmd = "adb shell input text" + " " + keyevent;
			Runtime.getRuntime().exec(cmd);
			logger.info("Performed the Keyevent" + keyevent);
			ExtentReporter.extentLogger("adbKeyevent", "Performed the Keyevent" + keyevent);
		} catch (Exception e) {
       		logger.error(e);
		}
	}
	/**
	 * Convert String to Double
	 * @param input
	 * @return
	 */
	public static double convertStringIntoDouble(String input) {
		return Double.parseDouble(input);
	}
	/**
	 * Convert String to Int
	 * @param input
	 * @return
	 */
	public static int convertStringIntoInt(String input) {
		return Integer.parseInt(input);
	}
	public static boolean verticalSwipeTillElementDisplayed(By byElement) {
		AppiumDriver driver = DriverManager.getAppiumDriver();
		boolean flag = false;
		Dimension size = driver.manage().window().getSize();
		int starty = (int) (size.height * 0.80);
		int endy = (int) (size.height * 0.20);
		int startx = size.width / 2;
		for (int i = 0; i < 10; i++) {
			if (driver.findElements(byElement).size() > 0 && driver.findElement(byElement).isDisplayed()) {
				flag = true;
				break;
			} else {
				PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "Fingername");
				Sequence sequence = new Sequence(finger1, 1)
						.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty))
						.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
						.addAction(new Pause(finger1, Duration.ofMillis(2000)))
						.addAction(finger1.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), startx, endy))
						.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
				driver.perform(Collections.singletonList(sequence));
			}
		}
		return flag;
	}
	public Response getApiResponseOfUrl(String url, Map<String, String> map) {
		RestAssured.urlEncodingEnabled = false;
		logger.info("URL : " + url);
		Response resp = null;
		if (url.contains("view")) {
			resp = RestAssured.given().relaxedHTTPSValidation().contentType(ContentType.JSON)
					.params("responseType", "common").params("premiumTrays", "")
					.params("features", "include:buttonsTray").headers("Content-Type", "application/json")
					.headers("Content-Version", "V5").headers("usertype", "").headers(map).when().get(url);
		} else if (url.contains("editorial?")) {
			resp = RestAssured.given().contentType(ContentType.JSON).headers("Content-Type", "application/json")
					.headers("Content-Version", "V5").headers(map).when().get(url);
		} else {
			resp = RestAssured.given().relaxedHTTPSValidation().contentType(ContentType.JSON)
					.params("responseType", "common").headers("Content-Type", "application/json")
					.headers("Content-Version", "V5").headers("user-agent", "okhttp/4.10.0").headers(map).when().get(url);
		}
		return resp;
	}
	public static Response PostMethod_Header(String url, String header, String postData) throws IOException {
		Response resp = null;
		RestAssured.baseURI = url.trim();
		if (header.equalsIgnoreCase("NA") || header.isEmpty()) {
			resp = RestAssured.given().relaxedHTTPSValidation().contentType(ContentType.JSON).accept(ContentType.JSON)
					.body(postData).when().post("");
		}
        return resp;
    }
	/**
	 * Vertical Swipe till element is displayed
	 * @return
	 * @throws Exception
	 */
	public static boolean verticalSwipe() {
		AppiumDriver driver = DriverManager.getAppiumDriver();
		boolean flag = false;
		int width = driver.manage().window().getSize().getWidth();
		int height = driver.manage().window().getSize().getHeight();
		int startx = width / 2;
		int endx = startx;
		int starty = (height / 4) * 3;
		int endy = (height / 8) * 3;
		int expectedPosition = height / 2;
		PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "Fingername");
		Sequence sequence = new Sequence(finger1, 1)
				.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty))
				.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(new Pause(finger1, Duration.ofMillis(200)))
				.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endx, endy))
				.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Collections.singletonList(sequence));
		return flag;
	}
	public static void newScrollMethodHorizontal(double start_xd, double start_yd, double end_xd, double end_yd) {
		AppiumDriver driver = DriverManager.getAppiumDriver();
			Dimension size = DriverManager.getAppiumDriver().manage().window().getSize();
			int start_x = (int) (size.getWidth() * start_xd); //0.8
			int start_y = (int) (size.getHeight() * start_yd); //0.5
			int end_x = (int) (size.getWidth() * end_xd);//0.2
			int end_y = (int) (size.getHeight() * end_yd);//0.5
			PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "Fingername");
			Sequence sequence = new Sequence(finger1, 1)
					.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start_x, start_y))
					.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
					.addAction(new Pause(finger1, Duration.ofMillis(200)))
					.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), end_x, end_y))
					.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			driver.perform(Collections.singletonList(sequence));
	}
	public static void newScrollMethodHorizontal1(double start_xd, double start_yd, double end_xd, double end_yd) {
		Dimension dimension = DriverManager.getAppiumDriver().manage().window().getSize();
		int start_x = (int) (dimension.getWidth() * start_xd); //0.8
		int start_y = (int) (dimension.getHeight() * start_yd); //0.5
		int end_x = (int) (dimension.getWidth() * end_xd);//0.2
		int end_y = (int) (dimension.getHeight() * end_yd);//0.5
		TouchAction touch = new TouchAction(DriverManager.getAppiumDriver());
		touch.press(PointOption.point(start_x, start_y)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
				.moveTo(PointOption.point(end_x, end_y)).release().perform();
	}

	/**
	 * Fetch The current date in format
	 * @param format
	 * @return
	 */
	public static String getCurrentDateInFormat(String format) {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		String formattedDate = currentDate.format(formatter);
		return formattedDate;
	}
	public static void action_clickOnPosition(int pointA_X, int pointA_Y) {
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence clickPosition = new Sequence(finger, 1);
		clickPosition .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), pointA_X,pointA_Y)) .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		DriverManager.getAppiumDriver().perform(Arrays.asList(clickPosition));
	}
	/**
	 * Reusable method to Enter OTP
	 * @throws Exception
	 */
	public static void enterOTP(String otp) throws Exception {
		click(OnBoardingPage.objOTPInputField("1"),"OTP Input field");
		waitTime(3000);
		DriverManager.getAppiumDriver().getKeyboard().sendKeys(otp);
	}
	public static void isButtonDisabled(By element,String buttonName) throws Exception {
		WebElement button = findElement(element);
		boolean sButton=button.isEnabled();
		if(sButton) {
			logger.info(buttonName+" button is enabled state");
			extentLoggerFail("Month Options", buttonName+" button is in enabled state");
		} else {
			logger.info(buttonName+" button is disabled state");
			extentLoggerPass("Month Options", buttonName+" button is in disabled state");
		}
	}
	/**
	 * Back
	 * @throws Exception
	 */
	public static void 	back(int x) throws Exception {
		try {
			if (getPlatform().equals("Web")) {
				for (int i = 0; i < x; i++) {
					getWebDriver().navigate().back();
					logger.info("Back button is tapped");
					ExtentReporter.extentLogger("Back", "Back button is tapped");
				}
			} else if (getPlatform().equals("Android") || getPlatform().equals("MPWA")
					|| platform.equalsIgnoreCase("BrowserStack") || platform.equalsIgnoreCase("IOSBrowserStack")) {
				for (int i = 0; i < x; i++) {
					DriverManager.getAppiumDriver().navigate().back();
					logger.info("Back button is tapped");
					ExtentReporter.extentLogger("Back", "Back button is tapped");
					waitTime(6000);
				}
			}
		} catch (Exception e) {
			ExtentReporter.screencapture();
		}
	}
	public static boolean switchBackToApp() {
		AppiumDriver driver = DriverManager.getAppiumDriver();
		if(platform.equalsIgnoreCase("Android")){
			driver.activateApp("com.tonik.mobile");
		} else if (platform.equalsIgnoreCase("iOS")){
			driver.activateApp("com.mobile.tonik");
		}
		return true;
	}
	public static void closeiOSTonikApp() {
		try {
			DriverManager.getAppiumDriver().terminateApp(propertyFileReaderFromProperties("iOSBundleId"));
			ExtentReporter.extentLogger("Killed the application successfully", "Killed the application successfully");
			logger.info("The user Killed the application successfully");
		} catch (Exception e) {
			ExtentReporter.extentLogger("Unable to Kill the application", "Unable to Kill the application");
			logger.info("The user failed kill the application successfully");
		}
	}
	public static void swipeFromHalfOfTheScreen(){
		if(platform.equalsIgnoreCase("Android")) {
			TouchAction touchAction = new TouchAction(DriverManager.getAppiumDriver());
			WebElement element = DriverManager.getAppiumDriver().findElement(By.xpath("(//XCUIElementTypeStaticText[contains(@name,'Sign here')]/preceding-sibling::XCUIElementTypeOther)[1]/child::XCUIElementTypeOther"));
			touchAction.longPress(ElementOption.element(element)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point(376, 397)).release().perform();
		}else{
			PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
			Sequence sequence = new Sequence(finger1, 0);
			sequence.addAction(finger1.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), 189, 339));
			sequence.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			sequence.addAction(new Pause(finger1, Duration.ofMillis(1000))); // Pause for 1 second
			sequence.addAction(finger1.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), 187, 45));
			sequence.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			DriverManager.getAppiumDriver().perform(Arrays.asList(sequence));
		}
	}
	public static void swipeAddress(){
		if(platform.equalsIgnoreCase("Android")) {
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence longPress = new Sequence(finger, 1)
					.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), 333,1166 ))
					.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
					.addAction(new Pause(finger, Duration.ofSeconds(3))) // Long press for 1 second
					.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), 335, 379))
					.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			DriverManager.getAppiumDriver().perform(Arrays.asList(longPress));
		}else{
			PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
			Sequence sequence = new Sequence(finger1, 0);
			sequence.addAction(finger1.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), 55, 319));
			sequence.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			sequence.addAction(new Pause(finger1, Duration.ofMillis(1000))); // Pause for 1 second
			sequence.addAction(finger1.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), 248, 497));
			sequence.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			DriverManager.getAppiumDriver().perform(Arrays.asList(sequence));
		}
	}
}