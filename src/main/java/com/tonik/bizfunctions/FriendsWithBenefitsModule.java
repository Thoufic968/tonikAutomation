package com.tonik.bizfunctions;
import com.driverInstance.DriverManager;
import com.tonik.pageObject.FriendsWithBenefitsPages;
import com.tonik.utility.ExtentReporter;
import com.tonik.utility.Utilities;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSElement;
import static com.tonik.utility.ExtentReporter.extentLoggerPass;
import static com.tonik.utility.Utilities.*;

public class FriendsWithBenefitsModule extends BaseClass {
	public FriendsWithBenefitsModule() {
		super();
	}
	String platform = Utilities.getPlatform();
	/**
	 * Method to Friends with benefits tile displayed
	 * @throws Exception
	 */
	public void friendsWithBenefitsTile() throws Exception {
		if (verifyElementDisplayed(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objFriendsWithBenefitsTile))) {
			logger.info("Friends with benefits tile seen for no TSA user");
			extentLoggerPass("Friends With Benefits Tile", "Friends with benefits tile seen for no TSA user");
		} else {
			logger.info("Friends with benefits tile  not seen for no TSA user");
			extentLoggerFail("Friends With Benefits Tile", "Friends with benefits tile  not seen for no TSA user");
		}
	}
	/***
	 * Method to Friends with benefits tile screen validation
	 * @throws Exception
	 */
	public void friendWithBenefitsScreenValidation() throws Exception {
		verifyElementPresent(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objFriendsWithBenefitsTile), getTextVal(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objFriendsWithBenefitsTile), "Tile"));
		valueValidation(getText(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objFriendsWithBenefitsTile)), propertyFileReader("friendWithBenefitTileHeader", "FriendsWithBenefits"), "Tile", "contains");
		valueValidation(getText(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objFriendsTileSubTitle)), propertyFileReader("friendWithBenefitSubTitle", "FriendsWithBenefits"), "Tile Sub Header", "contains");
		valueValidation(getText(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objFriendsTileSubTitle)), propertyFileReader("successfulLoanReferral", "FriendsWithBenefits"), "Tile Sub Header", "contains");
		verifyElementPresentAndClick(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objFriendsTileSubTitle), getText(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objFriendsTileSubTitle)));
		valueValidation(getText(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objFriendsWithBenefitScreen)), propertyFileReader("friendsWithPage", "FriendsWithBenefits"), "Page", "contains");
		valueValidation(getText(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objFriendsWithBenefitScreen)), propertyFileReader("benefitsTxt", "FriendsWithBenefits"), "Page", "contains");
		valueValidation(getText(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objSuccessfullyBookText)), propertyFileReader("friendpageSubTitle", "FriendsWithBenefits"), "Page", "contains");
		valueValidation(getText(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objSuccessfullyBookText)), propertyFileReader("friendpageSubTitle1", "FriendsWithBenefits"), "Page", "contains");
		valueValidation(getText(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objSuccessfullyBookText)), propertyFileReader("friendpageSubTitle2", "FriendsWithBenefits"), "Page", "contains");
		valueValidation(getText(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objReferralLinkText)), propertyFileReader("referralLinkTxt", "FriendsWithBenefits"), "Page", "contains");
		verifyElementPresent(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objLink), getTextVal(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objLink), "Link"));
		valueValidation(getText(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objReferralLinkText)), propertyFileReader("referralLinkTxt", "FriendsWithBenefits"), "Page", "contains");
		valueValidation(getText(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objYourReferralTxt)), propertyFileReader("referralLinkSubTxt", "FriendsWithBenefits"), "Page", "contains");
		valueValidation(getText(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objYourReferralTxt)), propertyFileReader("referralLinkSubTxt1", "FriendsWithBenefits"), "Page", "contains");
		valueValidation(getText(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objYourReferralTxt)), propertyFileReader("referralLinkSubTxt2", "FriendsWithBenefits"), "Page", "contains");
		valueValidation(getText(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objHowDoesThisWorkText)), propertyFileReader("howDoesTxt", "FriendsWithBenefits"), "Page", "contains");
		valueValidation(getText(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objShareYourCodeButton)), propertyFileReader("shareYourCode", "FriendsWithBenefits"), "Button", "contains");
	}
	/**
	 * Method to Verify the user can access the Friends with benefits if TSA is not created
	 *
	 * @throws Exception *
	 */
	public void verifyUserCanAccessFriendWithBenefitsTileWithNoTSA_TDB_FB_001() throws Exception {
		HeaderChildNode("'TDB_FB_001', Verify the user can access the Friends with benefits if TSA is not created");
		tonikLogin(propertyFileReader("noTSAPassword", "FriendsWithBenefits"));
		friendsWithBenefitsTile();
		logger.info("'TDB_FB_001', Verify the user can access the Friends with benefits if TSA is not created validated");
		extentLoggerPass("TDB_FB_001", "'TDB_FB_001', Verify the user can access the Friends with benefits if TSA is not created validated");
	}
	/**
	 * Method to Verify the user can access the Friends with benefits if TSA is created
	 *
	 * @throws Exception *
	 */
	public void verifyUserCanAccessFriendWithBenefitsTileWithTSA_TDB_FB_002() throws Exception {
		HeaderChildNode("'TDB_FB_002', Verify the user can access the Friends with benefits if TSA is created");
		tonikLogin(propertyFileReader("noTSAPassword", "FriendsWithBenefits"));
		friendsWithBenefitsTile();
		click(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objProfileIcon), "Profile Icon");
		valueValidation(getText(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objAccountInformation)), propertyFileReader("acconutInformation", "FriendsWithBenefits"), "page", "contains");
		click(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objAccountInformation), getTextVal(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objAccountInformation), "Tab"));
		if (verifyElementDisplayed(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objWeAreAlmostReady))) {
			logger.info("We are Almost Ready Screen is Displayed");
			extentLoggerPass("Screen", "We are Almost Ready Screen is Displayed");
		}
		click(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objBackBtn), "Back Button");
		click(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objBackBtn), "Back Button");
		if (platform.equalsIgnoreCase("Android")) {
			AndroidElement historyBtn = (AndroidElement) findElement(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objHistoryBtn));
			if (historyBtn.isEnabled()) {
				logger.info("User is redirected to the Main Dashboard with TSA Created");
				extentLoggerPass("TSA Created", "User is redirected to the Main Dashboard with TSA Created");
			} else {
				logger.info("User is redirected to the Main Dashboard with No TSA Created");
				extentLoggerFail("No TSA Created", "User is redirected to the Main Dashboard with No TSA Created");
			}
		} else {
			IOSElement historyButton = (IOSElement) findElement(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objHistoryBtn));
			if (historyButton.isEnabled()) {
				logger.info("User is redirected to the Main Dashboard with TSA Created");
				ExtentReporter.extentLoggerPass("TSA Created", "User is redirected to the Main Dashboard with TSA Created");
			} else {
				logger.info("User is redirected to the Main Dashboard with No TSA Created");
				ExtentReporter.extentLoggerFail("No TSA Created", "User is redirected to the Main Dashboard with No TSA Created");
			}
		}
		friendWithBenefitsScreenValidation();
		logger.info("TDB_FB_002, Verify the user can access the Friends with benefits if TSA is created validated");
		extentLoggerPass("TDB_FB_002", "TDB_FB_002 Verify the user can access the Friends with benefits if TSA is created validated");
	}
	/**
	 * Method to Verify if the user can copy the Referral link
	 *
	 * @throws Exception
	 */
	public void verifyUserCanCopyReferralLink_TDB_FB_003() throws Exception {
		HeaderChildNode("'TDB_FB_003', Verify if the user can copy the Referral link");
		tonikLogin(propertyFileReader("noTSAPassword", "FriendsWithBenefits"));
		friendWithBenefitsScreenValidation();
		click(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objClipBoardButton), "Clip Board Button");
		waitForElementToBePresent(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objClipBoardMsg), 10, "Copied to click board message");
		valueValidation(getText(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objClipBoardMsg)), propertyFileReader("copyToClipBoard", "FriendsWithBenefits"), "Toast Message", "contains");
		click(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objHowDoesThisWorkText), getTextVal(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objHowDoesThisWorkText), "Button"));
		waitForElementToBePresent(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objWebView), 10, "Web view");
		verifyElementPresent(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objWebView), getTextVal(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objWebView), "Webview Text"));
		if (platform.equalsIgnoreCase("Android")) {
			DriverManager.getAppiumDriver().navigate().back();
		} else {
			DriverManager.getAppiumDriver().activateApp("com.mobile.tonik");
		}
		if (verifyElementPresent(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objFriendsWithBenefitScreen), "Friends with screen")) {
			valueValidation(getText(FriendsWithBenefitsPages.getByOSType(platform, FriendsWithBenefitsPages.objFriendsWithBenefitScreen)), propertyFileReader("friendsWithPage", "FriendsWithBenefits"), "Page", "contains");
			logger.info("TDB-FB-003, Verify the user can access the Friends with benefits if TSA is not created validated");
			extentLoggerPass("TDB-FB-003", "TDB-FB-003 Verify the user can access the Friends with benefits if TSA is not created validated");
		}
	}
}