package com.tonik.bizfunctions;

import com.tonik.pageObject.ContactUsPage;
import com.tonik.pageObject.LoginPage;
import com.tonik.pageObject.OnBoardingPage;
import com.tonik.pageObject.OnBoardingPageiOS;
import com.tonik.utility.Utilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import com.driverInstance.CommandBase;
import com.driverInstance.DriverManager;
import com.tonik.propsreader.PropertyFileReader;
import com.tonik.utility.LoggingUtils;
import java.util.List;
import java.util.Random;

import static com.tonik.utility.Utilities.*;

public class BaseClass {
	private static ThreadLocal<String> platform = new ThreadLocal<>();
	public static String getPlatform() {
		return platform.get();
	}
	public static void setPlatform(String value) {
		platform.set(value);
	}
	static String oSPlatformName = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getSuite().getName().replaceAll(" (0)", "").trim();
	public static LoggingUtils logger = new LoggingUtils();
	public static PropertyFileReader prop;
	public static String os = System.getProperty("os.name").toLowerCase();
	String osName=System.getProperty("os.name").toLowerCase();
	private int timeout;
	private int retryCount;
	//0-Param Constructor
	public BaseClass() {
	}
	//3-Param Constructor
	public BaseClass(String Application, String deviceName, String portno) throws InterruptedException {
		new CommandBase(Application, deviceName, portno);
		init();
	}
	//Tear Down Application
	public void tearDown() {
		if(oSPlatformName.equals("Web")) {
			getWebDriver().close();
		}else {
			logger.info("Session ID: " + ((RemoteWebDriver) DriverManager.getAppiumDriver()).getSessionId());
			extentLogger("","Session ID: " + ((RemoteWebDriver) DriverManager.getAppiumDriver()).getSessionId());
			logger.info("Session is quit");
			extentLogger("", "Session is quit");
			setScreenshotSource();
			DriverManager.unloadMobile();
		}
	}
	public void init(){
		PropertyFileReader handler;
		handler = new PropertyFileReader("properties/Execution.properties");
		setTimeout(Integer.parseInt(handler.getproperty("TIMEOUT")));
		setRetryCount(Integer.parseInt(handler.getproperty("RETRY_COUNT")));
		logger.info("Loaded the following properties" + " TimeOut :" + getTimeout() + " RetryCount :" + getRetryCount());
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public int getRetryCount() {
		return retryCount;
	}
	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}
	public static String tonikLogin(String password) throws Exception {
		setPlatform(Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("Platform"));
		String platform = getPlatform();
		String profileName = "";
		if(waitForElementToBePresent(LoginPage.getByOSType(platform,LoginPage.objEditPassword), 60,"Password edit field")) {
			String camPermHeaderTxt = getText(LoginPage.getByOSType(platform,LoginPage.objEditPassword));
			Assert.assertEquals(camPermHeaderTxt, "Password");
			click(LoginPage.getByOSType(platform,LoginPage.objEditPassword), "Password field");
			type(LoginPage.getByOSType(platform,LoginPage.objEditPassword)  , password, "Enter secret code");
			verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objLoginBtn),getTextVal(LoginPage.getByOSType(platform,LoginPage.objLoginBtn),"Button"));
			waitForElementToBePresent(LoginPage.getByOSType(platform,LoginPage.objProfileName),5,": Profile Name");
			verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objProfileName),getTextVal(LoginPage.getByOSType(platform,LoginPage.objProfileName)," : Profile Name"));
			String profileNameInDashboard = getText(LoginPage.getByOSType(platform,LoginPage.objProfileName));
			profileName = profileNameInDashboard.replace("Hi, ","");
			logger.info("Successfully logged into Tonik app");
			extentLoggerPass("", "Successfully logged into Tonik app");
			return profileName;
		}
		else {
			logger.info("Login page not loaded");
			extentLoggerFail("Login page", "Login page is not loaded successfully");
		}
		return profileName;
	}
	public void contactUsPageUI() throws Exception {
		setPlatform(Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("Platform"));
		String platform = Utilities.getPlatform();
		waitForElementToBePresent(OnBoardingPageiOS.objGetInTouchWithCustomerCarePage,3,"Get in touch page");
		verifyElementPresent(OnBoardingPageiOS.objPageHeader,getTextVal(OnBoardingPageiOS.objPageHeader," : Page Header"));
		if (verifyElementDisplayed(ContactUsPage.getByOSType(platform,ContactUsPage.objGetInTouchInfo))) {
			List<WebElement> values = findElements(ContactUsPage.getByOSType(platform,ContactUsPage.objGetInTouchInfo));
			for (int info = 0; info < values.size(); info++) {
				if(info%2==0) {
					String displayedItem = values.get(info).getText();
					logger.info("Info : '" + displayedItem + "' is displayed");
					extentLogger(" ", "Info : '" + displayedItem + "' is displayed");
				}
			}
		}
		verifyElementPresent(ContactUsPage.getByOSType(platform,ContactUsPage.objChatWithUs),getTextVal(ContactUsPage.getByOSType(platform,ContactUsPage.objChatWithUs),"Contact Option"));
		assertionValidation(getText(ContactUsPage.getByOSType(platform,ContactUsPage.objChatWithUs)),propertyFileReader("ChatWithUs","contactUs"),": Option");
		verifyElementPresent(ContactUsPage.getByOSType(platform,ContactUsPage.objContactNumber),getTextVal(ContactUsPage.getByOSType(platform,ContactUsPage.objContactNumber),"Contact Number"));
		assertionValidation(getText(ContactUsPage.getByOSType(platform,ContactUsPage.objContactNumber)),propertyFileReader("ContactNumber","contactUs"),": Option");
		verifyElementPresent(ContactUsPage.getByOSType(platform,ContactUsPage.objFrequentlyAskedQuestion),getTextVal(ContactUsPage.getByOSType(platform,ContactUsPage.objFrequentlyAskedQuestion),"option"));
		assertionValidation(getText(ContactUsPage.getByOSType(platform,ContactUsPage.objFrequentlyAskedQuestion)),propertyFileReader("FrequentlyAskedQuestion","contactUs"),": Option");
		verifyElementPresent(ContactUsPage.getByOSType(platform,ContactUsPage.objBackBtn),getTextVal(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader)," : Page Back button"));
	}
	public void handlePopup() throws Exception {
		setPlatform(Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("Platform"));
		String platform = getPlatform();
		if(platform.equalsIgnoreCase("Android")) {
			if (verifyElementDisplayed(LoginPage.getByOSType(platform,LoginPage.objCallsPermissionPopup))) {
				verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objCallsPermissionPopup), getTextVal(LoginPage.getByOSType(platform,LoginPage.objCallsPermissionPopup), ": Calls Permission popup"));
				click(LoginPage.getByOSType(platform,LoginPage.objAllowBtn), getTextVal(LoginPage.getByOSType(platform,LoginPage.objAllowBtn), ": Button"));
			}
			if (verifyElementDisplayed(LoginPage.getByOSType(platform,LoginPage.objLocationAccessPopup))) {
				verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objLocationAccessPopup), getTextVal(LoginPage.getByOSType(platform,LoginPage.objLocationAccessPopup), ": Location Permission popup"));
				verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objWhileUsingApp), getTextVal(LoginPage.getByOSType(platform,LoginPage.objWhileUsingApp), ": Button"));
			}
			if (verifyElementDisplayed(LoginPage.getByOSType(platform,LoginPage.objPictureAccessPopup))) {
				verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objPictureAccessPopup), getTextVal(LoginPage.getByOSType(platform,LoginPage.objPictureAccessPopup), ": Picture Permission popup"));
				verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objAllowBtn), getTextVal(LoginPage.getByOSType(platform,LoginPage.objAllowBtn), ": Button"));
			}
			if (verifyElementDisplayed(LoginPage.getByOSType(platform,LoginPage.objAllowNotificationPopup))) {
				verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objAllowNotificationPopup), getTextVal(LoginPage.getByOSType(platform,LoginPage.objAllowNotificationPopup), ": Take Picture Permission popup"));
				verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objAllowBtn), getTextVal(LoginPage.getByOSType(platform,LoginPage.objAllowBtn), ": Button"));
			}
		}else{
			if (verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objLocationAccessPopupIos),"Location popup")) {
				verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objWhileUsingAppIos), getTextVal(LoginPage.getByOSType(platform,LoginPage.objWhileUsingAppIos), ": Button"));
			}
			if (verifyElementDisplayed(LoginPage.getByOSType(platform,LoginPage.objPictureAccessPopupIos))) {
				verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objOkBtnIos), getTextVal(LoginPage.getByOSType(platform,LoginPage.objOkBtnIos), ": Button"));
			}
			if (verifyElementDisplayed(LoginPage.getByOSType(platform,LoginPage.objContactAccessPopupIos))) {
				verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objOkBtnIos), getTextVal(LoginPage.getByOSType(platform,LoginPage.objOkBtnIos), ": Button"));
			}
			if (verifyElementDisplayed(LoginPage.getByOSType(platform,LoginPage.objPhotoAccessPopupIos))) {
				verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objOkBtnIos), getTextVal(LoginPage.getByOSType(platform,LoginPage.objOkBtnIos), ": Button"));
			}
			if (verifyElementDisplayed(LoginPage.getByOSType(platform,LoginPage.objPhotoAccessPopupIos))) {
				verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objAllowAccessAllPhotosIos), getTextVal(LoginPage.getByOSType(platform,LoginPage.objAllowAccessAllPhotosIos), ": Button"));
			}
			if (verifyElementDisplayed(LoginPage.getByOSType(platform,LoginPage.objNotificationPopupIos))) {
				verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objAllowBtnIos), getTextVal(LoginPage.getByOSType(platform,LoginPage.objAllowBtnIos), ": Button"));
			}
		}
	}
	public void tonikDashBordWhenNOTSAValidationiOS() throws Exception {
		setPlatform(Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("Platform"));
		String platform = getPlatform();
		verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objProfileName),getTextVal(LoginPage.getByOSType(platform,LoginPage.objProfileName)," : Profile Name"));
		containsValidation(getText(LoginPage.getByOSType(platform,LoginPage.objProfileName)),propertyFileReader("ProfilePreFix","onboarding"),"Profile prefix");
		containsValidation(getText(OnBoardingPageiOS.objAvailableBalance),"₱","PHP");
		verifyElementPresent(OnBoardingPageiOS.objDashboardCustomerCareIcon,"Dashboard Customer care icon");
		verifyElementPresent(OnBoardingPageiOS.objTopUpIcon,"Top up icon");
		assertionValidation(getAttributValue("enabled", OnBoardingPageiOS.objTopUpIcon),"true",": Top up icon");
		verifyElementPresent(OnBoardingPageiOS.objSendIcon,"Send icon");
		assertionValidation(getAttributValue("enabled", OnBoardingPageiOS.objSendIcon),"true",": Send icon");
		verifyElementPresent(OnBoardingPageiOS.objPayIcon,"Pay icon");
		assertionValidation(getAttributValue("enabled", OnBoardingPageiOS.objPayIcon),"true",": Pay icon");
		verifyElementPresent(OnBoardingPageiOS.objHistoryIcon,"History icon");
		assertionValidation(getAttributValue("enabled", OnBoardingPageiOS.objHistoryIcon),"false",": History icon");
		verifyElementPresent(OnBoardingPageiOS.objVirtualCard,"Virtual card");
		assertionValidation(getAttributValue("enabled", OnBoardingPageiOS.objVirtualCard),"false",": Virtual card");
		verifyElementPresent(OnBoardingPageiOS.objDebitCard,"Debit card");
		assertionValidation(getAttributValue("enabled", OnBoardingPageiOS.objDebitCard),"true",": Debit card");
		screencapture();
		scrollToVertical("Loans");
		verifyElementPresent(OnBoardingPageiOS.objStashTile,getTextVal(OnBoardingPageiOS.objStashTile,": tile"));
		assertionValidation(getText(OnBoardingPageiOS.objStashTileInfo),propertyFileReader("StashInfo","onboarding"),": Stash info");
		verifyElementPresent(OnBoardingPageiOS.objTimeDepositTile,getTextVal(OnBoardingPageiOS.objTimeDepositTile,": tile"));
		assertionValidation(getText(OnBoardingPageiOS.objTimeDepositTileInfo),propertyFileReader("TimeDepositInfo","onboarding"),": Time Deposit info");
		verifyElementPresent(OnBoardingPageiOS.objLoansTile,getTextVal(OnBoardingPageiOS.objLoansTile,": tile"));
		containsValidation(getText(OnBoardingPageiOS.objLoansTileInfo),propertyFileReader("LoansInfo","onboarding"),": Loans info");
	}
	public void tonikDashBordWhenNOTSAValidation() throws Exception {
		setPlatform(Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("Platform"));
		String platform = getPlatform();
		verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objProfileName),getTextVal(LoginPage.getByOSType(platform,LoginPage.objProfileName)," : Profile Name"));
		containsValidation(getText(LoginPage.getByOSType(platform,LoginPage.objProfileName)),propertyFileReader("ProfilePreFix","onboarding"),"Profile prefix");
		containsValidation(getText(OnBoardingPage.objAvailableBalance),"₱","PHP");
		verifyElementPresent(OnBoardingPage.objTopUpIcon,"Top up icon");
		assertionValidation(getAttributValue("enabled", OnBoardingPage.objTopUpIcon),"true",": Top up icon");
		verifyElementPresent(OnBoardingPage.objSendIcon,"Send icon");
		assertionValidation(getAttributValue("enabled", OnBoardingPage.objSendIcon),"true",": Send icon");
		verifyElementPresent(OnBoardingPage.objPayIcon,"Pay icon");
		assertionValidation(getAttributValue("enabled", OnBoardingPage.objPayIcon),"true",": Pay icon");
		verifyElementPresent(OnBoardingPage.objHistoryIcon,"History icon");
		assertionValidation(getAttributValue("enabled", OnBoardingPage.objHistoryIcon),"false",": History icon");
		verifyElementPresent(OnBoardingPage.objVirtualCard,"Virtual card");
		assertionValidation(getAttributValue("enabled", OnBoardingPage.objVirtualCard),"false",": Virtual card");
		verifyElementPresent(OnBoardingPage.objDebitCard,"Debit card");
		assertionValidation(getAttributValue("enabled", OnBoardingPage.objDebitCard),"true",": Debit card");
		screencapture();
		Swipe("UP",2);
		verifyElementPresent(OnBoardingPage.objStashTile,getTextVal(OnBoardingPage.objStashTile,": tile"));
		assertionValidation(getText(OnBoardingPage.objStashTileInfo),propertyFileReader("StashInfo","onboarding"),": Stash info");
		scrollToVertical("Loans");
		verifyElementPresent(OnBoardingPage.objTimeDepositTile,getTextVal(OnBoardingPage.objTimeDepositTile,": tile"));
		assertionValidation(getText(OnBoardingPage.objTimeDepositTileInfo),propertyFileReader("TimeDepositInfo","onboarding"),": Time Deposit info");
		verifyElementPresent(OnBoardingPage.objLoansTile,getTextVal(OnBoardingPage.objLoansTile,": tile"));
		containsValidation(getText(OnBoardingPage.objLoansTileInfo),propertyFileReader("LoansInfo","onboarding"),": Loans info");
	}
	public void enterMobileNumber(String sMobileNumber) throws Exception {
		setPlatform(Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("Platform"));
		String platform = getPlatform();
		waitForElementToBePresent(OnBoardingPageiOS.objMobileNumberInputField,3,"Mobile number input field");
		type(OnBoardingPageiOS.objMobileNumberInputField, sMobileNumber, "Mobile number input field");
		verifyElementPresentAndClick(LoginPage.getByOSType(platform, LoginPage.objNextBtn), getTextVal(OnBoardingPageiOS.objNextBtn, ": Button"));
	}
	public static void enterOTP(String otp) throws Exception {
		click(OnBoardingPageiOS.objOTPInputField("1"),"OTP Input field");
		waitTime(3000);
		DriverManager.getAppiumDriver().getKeyboard().sendKeys(otp);
	}
	/**
	 * Method to create random mobile numbers by using a phone pattern and updating the last 3 digits
	 * @throws Exception
	 */
	public String generateRandomMobileNumber() {
		//script that will generate a random mobile number that will be used for the test case
		Random rnd = new Random();
		int randomNumber = rnd.nextInt(999);
		int numDigits = String.valueOf(randomNumber).length();
		while (numDigits < 3) {
			randomNumber = rnd.nextInt(999);
			numDigits = String.valueOf(randomNumber).length();
		}
		return propertyFileReader("randomMobilePhonePattern","onboarding") + Integer.toString(randomNumber);
	}
}