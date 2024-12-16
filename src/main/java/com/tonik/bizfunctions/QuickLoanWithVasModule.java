package com.tonik.bizfunctions;

import com.driverInstance.DriverManager;
import com.tonik.pageObject.*;
import com.tonik.utility.ExtentReporter;
import com.tonik.utility.Utilities;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.WebElement;
import java.util.List;
import static com.tonik.utility.ExtentReporter.HeaderChildNode;
import static com.tonik.utility.Utilities.*;
public class QuickLoanWithVasModule extends BaseClass {
	LoanCommonMethods loan = new LoanCommonMethods();
	public QuickLoanWithVasModule() {
		super();
	}
	SendMoneyModule sendMoneyModule=new SendMoneyModule();
	String platform = Utilities.getPlatform();
	String tsaAmountInDashboard;
	/**
	 * Verify if newly onboarded user can access the Quick Loan from the main Dashboard
	 * @throws Exception
	 */
	public void onBoardAsNewSKYCUserWithNoTSA_TDB_QLV_004() throws Exception {
		HeaderChildNode("TDB-QLV-004, Verify if newly onboarded user can access the Quick Loan from the main Dashboard");
		tonikLogin(propertyFileReader("password", "Login"));
		loan.onBoardAsNewSKYCUserWithNoTSA(propertyFileReader("loanAmount","QuickLoanWithVas"));
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanCalculatorPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanCalculatorPage), "Page"))) {
			valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYourInstallmentTxt)), propertyFileReader("chooseInstallmentTxt", "QuickLoanWithVas"), "Page Text", "contains");
			logger.info("TDB-QLV-004, Verify if newly onboarded user can access the Quick Loan from the main Dashboard validated");
			extentLoggerPass("TDB-QLV-004", "TDB-QLV-004, Verify if newly onboarded user can access the Quick Loan from the main Dashboard");
		}
	}
	/**
	 * Verify if user can edit the amount in the "How much do you need?" screen
	 * @throws Exception
	 */
	public void  userCanEditAmountInHowMuchDoYouNeedScreen_TDB_QLV_005() throws Exception {
		HeaderChildNode("TDB-QLV-005, Verify if user can edit the amount in the 'How much do you need?' screen");
		tonikLogin(propertyFileReader("password", "Login"));
		loan.userCanEditAmountInHowMuchDoYouNeedScreen();
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanCalculatorPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanCalculatorPage), "Page"))) {
			valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYourInstallmentTxt)), propertyFileReader("chooseInstallmentTxt", "QuickLoanWithVas"), "Page Text", "contains");
			logger.info("TDB-QLV-005, Verify if user can edit the amount in the 'How much do you need?' screen validated");
			extentLoggerPass("TDB-QLV-005", "TDB-QLV-005, Verify if user can edit the amount in the 'How much do you need?' screen validated");
		}
	}
	/**
	 * Verify if user can apply the loan with VAS from the Loan Calculator screen
	 * @param amount
	 * @throws Exception
	 */
	public void  userCanApplyLoanWithVASFromLoanCalculatorScreen_TDB_QLV_007(String amount) throws Exception {
		HeaderChildNode("TDB-QLV-007, Verify if user can apply the loan with VAS from the Loan Calculator screen");
		tonikLogin(propertyFileReader("password", "Login"));
		loan.userCanApplyLoanWithVASFromLoanCalculatorScreen(amount);
		if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRelaxPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRelaxPage), "Page Text"))) {
			valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRelaxPage)), propertyFileReader("relaxPageTxt", "QuickLoanWithVas"), "Page Header", "contains");
			logger.info("TDB-QLV-007, Verify if user can apply the loan with VAS from the Loan Calculator screen validated");
			extentLoggerPass("TDB-QLV-007", "TDB-QLV-007, Verify if user can apply the loan with VAS from the Loan Calculator screen validated");
		}
	}
	/**
	 * Verify if user can accept the loan offer with VAS from the Loan Summary screen
	 * @param amount
	 * @throws Exception
	 */
	public void userCanAcceptLoanOfferWithVASFromTheLoanSummaryScreen_TDB_QLV_008(String amount,String tenure) throws Exception {
		HeaderChildNode("TDB-QLV-008, Verify if user can accept the loan offer with VAS from the Loan Summary screen");
		tonikLogin(propertyFileReader("password", "Login"));
		loan.chooseInstallmentPageNavigation(amount);
		click(FlexPivotPage.objSelectLoanTenure(platform,tenure),getTextVal(FlexPivotPage.objSelectLoanTenure(platform,tenure),"Tenure"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), 10, "Button");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), "Button"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), 10, "Button");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), "Button"));
		action_clickOnPosition(527, 1577);
		if(platform.equalsIgnoreCase("ios")){
			swipe("UP", 1);
		}
		scrollToVertical(propertyFileReader("figuresMayChangeTxt", "QuickLoanWithVas"));
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHowItWorksBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHowItWorksBtn), "Button"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRelaxPage), 10, "Relax Page");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRelaxPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRelaxPage), "Page Text"));
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), "Button"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHereisSummaryTxt), 10, "Button");
		if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHereisSummaryTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHereisSummaryTxt), "Page"))) {
			valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHereisSummaryTxt)), propertyFileReader("whatDoYouNeedPage", "QuickLoanWithVas"), "Page Header", "contains");
			logger.info("Successfully navigated to " + getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHereisSummaryTxt), "Page"));
			extentLoggerPass("What do yoy need page", "Successfully navigated to " + getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHereisSummaryTxt), "Page"));
		}
	}
	/**
	 * Verify if user can select the reason for the loan application (loan purpose screen)
	 * @throws Exception
	 */
	public void reasonForLoanApplicationValidation_TDB_QLV_009() throws Exception {
		HeaderChildNode("TDB_QLV_009, Quick Loan With VAS - Verify if user can select the reason for the loan application");
		tonikLogin(propertyFileReader("password", "Login"));
		loan.reasonForLoanApplicationValidation("Education");
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"))) {
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("FieldOfWorkPage","QuickLoanWithVas"),": page title");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)),propertyFileReader("FieldOfWorkPageSubtitle","QuickLoanWithVas"),": page subtitle");
			logger.info("TDB_QLV_009, Quick Loan With VAS - User can select the reason for the loan application and navigated to Field of work screen validated");
			extentLoggerPass("TDB_QLV_009", "TDB_QLV_009, Quick Loan With VAS - User can select the reason for the loan application and navigated to Field of work screen validated");
		}
	}
	/**
	 * Verify if user can select the field of work
	 * @throws Exception
	 */
	public void selectFieldOfWorkValidation_TDB_QLV_010() throws Exception {
		HeaderChildNode("TDB_QLV_010, Quick Loan With VAS - Verify if user can select the field of work");
		tonikLogin(propertyFileReader("password", "Login"));
		loan.loanValidation();
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), ": Tile"));
		loan.whatDoYouNeedItForScreen("Education");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("FieldOfWorkPage", "QuickLoanWithVas"), ": page title");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("WhatDoYouNeedItForPage","QuickLoanWithVas"),": page title");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),getTextVal(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),": button"));
		loan.leavingSoonPageValidation();
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFieldOfWorkPage), 5, "Field of work page");
		if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"))) {
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("FieldOfWorkPage", "QuickLoanWithVas"), ": page title");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("FieldOfWorkPageSubtitle", "QuickLoanWithVas"), ": page subtitle");
			if (platform.equalsIgnoreCase("ios")) {
				List<IOSElement> values = DriverManager.getAppiumDriver().findElements(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objList));
				for (int info = 0; info < values.size(); info++) {
					String displayedItem = values.get(info).getText();
					logger.info("Field of work : '" + displayedItem + "' is displayed");
					ExtentReporter.extentLogger(" ", "Field of work : '" + displayedItem + "' is displayed");
				}
			} else {
				List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objList));
				for (int info = 0; info < values.size(); info++) {
					String displayedItem = values.get(info).getText();
					logger.info("Field of work : '" + displayedItem + "' is displayed");
					ExtentReporter.extentLogger(" ", "Field of work : '" + displayedItem + "' is displayed");
				}
			}
			assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "false", ": Enable Attribute value");
			waitTime(2000);//Hard wait required for execution
			type(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFieldOfWokSearchField), propertyFileReader("FieldOfWork","QuickLoanWithVas"), "Field name");
			verifyElementPresentAndClick(QuickLoanWithVasPage.objList(platform, 1), getTextVal(QuickLoanWithVasPage.objList(platform, 1), ": Field Of Work"));
			if (platform.equalsIgnoreCase("ios")) {
				click(QuickLoanWithVasPage.objList(platform, 1), getTextVal(QuickLoanWithVasPage.objList(platform, 1), ": Field Of Work"));
			}
			assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "true", ": Enable Attribute value");
			click(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), getTextVal(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), ": button"));
		}
		waitTime(4000);
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"))) {
			containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("WhatsYourJobRolePage","QuickLoanWithVas"),": page title");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)),propertyFileReader("WhatsYourJobRolePageSubtitle","QuickLoanWithVas"),": page subtitle");
			logger.info("TDB_QLV_009, Quick Loan With VAS - User can select the field of work and navigated to What's your job Role? screen validated");
			extentLoggerPass("TDB_QLV_010", "TDB_QLV_010, Quick Loan With VAS - User can select the field of work and navigated to What's your job Role? screen validated");
		}
	}
	/**
	 * Verify if user can select the Occupation details
	 * @throws Exception
	 */
	public void selectOccupationValidation_TDB_QLV_011() throws Exception {
		HeaderChildNode("TDB_QLV_011, Quick Loan With VAS - Verify if user can select the Occupation details");
		tonikLogin(propertyFileReader("password", "Login"));
		loan.loanValidation();
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), ": Tile"));
		loan.whatDoYouNeedItForScreen("Education");
		loan.fieldOfWork(propertyFileReader("FieldOfWork","QuickLoanWithVas"));
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("WhatsYourJobRolePage", "QuickLoanWithVas"), ": page title");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("FieldOfWorkPage","QuickLoanWithVas"),": page title");
		click(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),getTextVal(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),": button"));
		loan.leavingSoonPageValidation();
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objWhatsYourJobRolePage), 5, "What's your Job Role? Screen");
		if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"))) {
			containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("WhatsYourJobRolePage", "QuickLoanWithVas"), ": page title");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("WhatsYourJobRolePageSubtitle", "QuickLoanWithVas"), ": page subtitle");
			if (platform.equalsIgnoreCase("ios")) {
				List<IOSElement> values = DriverManager.getAppiumDriver().findElements(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objList));
				for (int info = 0; info < values.size(); info++) {
					String displayedItem = values.get(info).getText();
					logger.info("Occupation : '" + displayedItem + "' is displayed");
					ExtentReporter.extentLogger(" ", "Occupation : '" + displayedItem + "' is displayed");
				}
			} else {
				List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objList));
				for (int info = 0; info < values.size(); info++) {
					String displayedItem = values.get(info).getText();
					logger.info("Occupation : '" + displayedItem + "' is displayed");
					ExtentReporter.extentLogger(" ", "Occupation : '" + displayedItem + "' is displayed");
				}
			}
			assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "false", ": Enable Attribute value");
			type(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFieldOfWokSearchField), propertyFileReader("Occupation","QuickLoanWithVas"), "occupation input field");
			verifyElementPresentAndClick(QuickLoanWithVasPage.objList(platform, 1), getTextVal(QuickLoanWithVasPage.objList(platform, 1), ": Occupation"));
			if (platform.equalsIgnoreCase("ios")) {
				click(QuickLoanWithVasPage.objList(platform, 1), getTextVal(QuickLoanWithVasPage.objList(platform, 1), ": Field Of Work"));
			}
			assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "true", ": Enable Attribute value");
			click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), getTextVal(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), ": button"));
		}
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIndustrySelectionScreen), 5, "Industry Selection screen");
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"))) {
			containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("IndustrySelectionScreen","QuickLoanWithVas"),": page title");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)),propertyFileReader("IndustrySelectionScreenSubTitle","QuickLoanWithVas"),": page subtitle");
			logger.info("TDB_QLV_011, Quick Loan With VAS - User can select the field of work and navigated to What's your job Role? screen validated");
			extentLoggerPass("TDB_QLV_011", "TDB_QLV_011, Quick Loan With VAS - User can select the field of work and navigated to What's your job Role? screen validated");
		}
	}

	/**
	 * Verify if user can select the Industry & the Sub-Industry options
	 * @throws Exception
	 */
	public void selectIndustryAndSubIndustryValidation_TDB_QLV_012() throws Exception {
		HeaderChildNode("TDB_QLV_012, Quick Loan With VAS - Verify if user can select the Industry & the Sub-Industry options");
		tonikLogin(propertyFileReader("password", "Login"));
		loan.loanValidation();
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), ": Tile"));
		loan.whatDoYouNeedItForScreen("Education");
		loan.fieldOfWork(propertyFileReader("FieldOfWork","QuickLoanWithVas"));
		loan.selectOccupation(propertyFileReader("Occupation","QuickLoanWithVas"));
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("IndustrySelectionScreen", "QuickLoanWithVas"), ": page title");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("WhatsYourJobRolePage","QuickLoanWithVas"),": page title");
		click(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),getTextVal(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),": button"));
		loan.leavingSoonPageValidation();
		loan.selectIndustry(propertyFileReader("Industry1","QuickLoanWithVas"),propertyFileReader("SubIndustry","QuickLoanWithVas"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMaritalStatusPage),5,"Martial Status screen");
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"))) {
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("MaritalStatus","QuickLoanWithVas"),": page title");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)),propertyFileReader("MaritalStatusSubTitle","QuickLoanWithVas"),": page subtitle");
			logger.info("TDB_QLV_012, Quick Loan With VAS - User can select the Industry & the Sub-Industry options validated");
			extentLoggerPass("TDB_QLV_012", "TDB_QLV_012, Quick Loan With VAS - User can select the Industry & the Sub-Industry options validated");
		}
	}

	/**
	 * Verify if user can select the Marital Status
	 * @throws Exception
	 */
	public void selectMaritalStatusValidation_TDB_QLV_013() throws Exception {
		HeaderChildNode("TDB_QLV_013, Quick Loan With VAS - Verify if user can select the Marital Status");
		tonikLogin(propertyFileReader("password", "Login"));
		loan.loanValidation();
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), ": Tile"));
		loan.whatDoYouNeedItForScreen("Education");
		loan.fieldOfWork(propertyFileReader("FieldOfWork","QuickLoanWithVas"));
		loan.selectOccupation(propertyFileReader("Occupation","QuickLoanWithVas"));
		loan.selectIndustry(propertyFileReader("Industry1","QuickLoanWithVas"),propertyFileReader("SubIndustry","QuickLoanWithVas"));
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("MaritalStatus","QuickLoanWithVas"),": page title");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"));
		containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("IndustrySelectionScreen", "QuickLoanWithVas"), ": page title");
		verifyElementPresentAndClick(QuickLoanWithVasPage.objSelectIndustry(platform, propertyFileReader("Industry1","QuickLoanWithVas")), getTextVal(QuickLoanWithVasPage.objSelectIndustry(platform, propertyFileReader("Industry1","QuickLoanWithVas")), ": industry"));
		verifyElementPresentAndClick(QuickLoanWithVasPage.objSelectSubIndustryRadioButton(platform, propertyFileReader("SubIndustry","QuickLoanWithVas")), getTextVal(QuickLoanWithVasPage.objSelectIndustry(platform, propertyFileReader("SubIndustry","QuickLoanWithVas")), ": Sub Industry"));
		click(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), getTextVal(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), ": button"));
		loan.leavingSoonPageValidation();
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMaritalStatusPage), 5, "Marital status selection screen");
		if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"))) {
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("MaritalStatus", "QuickLoanWithVas"), ": page title");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("MaritalStatusSubTitle", "QuickLoanWithVas"), ": page subtitle");
			if (platform.equalsIgnoreCase("ios")) {
				List<IOSElement> values = DriverManager.getAppiumDriver().findElements(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objList));
				for (int maritalStatusOption = 1; maritalStatusOption <= values.size(); maritalStatusOption++) {
					assertionValidation(getText(QuickLoanWithVasPage.objListFromSelectIndustry(platform, maritalStatusOption)), propertyFileReader("MaritalStatusOption" + maritalStatusOption, "QuickLoanWithVas"), ": Marital Status Option " + maritalStatusOption);
				}
			} else {
				List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objList));
				for (int maritalStatusOption = 1; maritalStatusOption <= values.size(); maritalStatusOption++) {
					assertionValidation(getText(QuickLoanWithVasPage.objList(platform, maritalStatusOption)), propertyFileReader("MaritalStatusOption" + maritalStatusOption, "QuickLoanWithVas"), ": Marital Status Option " + maritalStatusOption);
				}
			}
			assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "false", ": Enable Attribute value");
			verifyElementPresentAndClick(QuickLoanWithVasPage.objSelectMaritalStatus(platform, propertyFileReader("MaritalStatusOption1","QuickLoanWithVas")), getTextVal(QuickLoanWithVasPage.objSelectMaritalStatus(platform, propertyFileReader("MaritalStatusOption1","QuickLoanWithVas")), ": marital status"));
			assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "true", ": Enable Attribute value");
			click(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), getTextVal(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), ": button"));
		}
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageTitle), ": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageTitle)), propertyFileReader("SelectNoOfKids", "QuickLoanWithVas"), ": page title");
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("SelectNoOfKidsSubTitle", "QuickLoanWithVas"), ": page subtitle");
		click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back button");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageTitle), ": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageTitle)), propertyFileReader("MaritalStatus", "QuickLoanWithVas"), ": page title");
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("MaritalStatusSubTitle", "QuickLoanWithVas"), ": page subtitle");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), ": button"));
		loan.selectKidsOrDependents(propertyFileReader("DependentsOption2", "QuickLoanWithVas"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHighestEducationalAttainmentPage),5,"What is your highest educational attainment?");
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"))) {
			containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("HighestEducationalAttainment","QuickLoanWithVas"),": page title");
			containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)),propertyFileReader("HighestEducationalAttainmentSubTitle","QuickLoanWithVas"),": page subtitle");
			logger.info("TDB_QLV_013, Quick Loan With VAS - User can select the Marital Status and navigated to Kids selection screen validated");
			extentLoggerPass("TDB_QLV_013", "TDB_QLV_013, Quick Loan With VAS - User can select the Marital Status and navigated to Kids selection screen validated");
		}
	}
	/**
	 * Verify if user can select the Educational details
	 * @throws Exception
	 */
	public void selectEducationalDetails_TDB_QLV_014() throws Exception {
		HeaderChildNode("TDB_QLV_014, Quick Loan With VAS - Verify if user can select the Educational details");
		tonikLogin(propertyFileReader("password", "Login"));
		loan.loanValidation();
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), ": Tile"));
		loan.whatDoYouNeedItForScreen("Education");
		loan.fieldOfWork(propertyFileReader("FieldOfWork", "QuickLoanWithVas"));
		loan.selectOccupation(propertyFileReader("Occupation", "QuickLoanWithVas"));
		loan.selectIndustry(propertyFileReader("Industry1", "QuickLoanWithVas"), propertyFileReader("SubIndustry", "QuickLoanWithVas"));
		loan.selectMaritalStatus(propertyFileReader("MaritalStatusOption1","QuickLoanWithVas"));
		loan.selectKidsOrDependents(propertyFileReader("DependentsOption2","QuickLoanWithVas"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHighestEducationalAttainmentPage),5,"Select number of Kids");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("HighestEducationalAttainment","QuickLoanWithVas"),": page title");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("SelectNoOfKids", "QuickLoanWithVas"), ": page title");
		click(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), getTextVal(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), ": button"));
		loan.leavingSoonPageValidation();
		loan.selectHighestEducationalAttainment(propertyFileReader("EducationalAttainment1","QuickLoanWithVas"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactReferencePage),5,"Contact Reference");
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"))) {
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("ContactReference","QuickLoanWithVas"),": page title");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)),propertyFileReader("ContactReferenceSubTitle","QuickLoanWithVas"),": page subtitle");
			logger.info("TDB_QLV_014, Quick Loan With VAS - User can select the Educational details and navigated to Highest Educational Attainment screen validated");
			extentLoggerPass("TDB_QLV_014", "TDB_QLV_014, Quick Loan With VAS - User can select the Educational details and navigated to Highest Educational Attainment screen validated");
		}
	}

	/**
	 * Verify if user can input the reference contact details
	 * @throws Exception
	 */
	public void inputReferenceContactDetailsValidation_TDB_QLV_015() throws Exception {
		HeaderChildNode("TDB_QLV_015, Quick Loan With VAS - Verify if user can input the reference contact details");
		tonikLogin(propertyFileReader("password", "Login"));
		loan.loanValidation();
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), ": Tile"));
		loan.whatDoYouNeedItForScreen("Education");
		loan.fieldOfWork(propertyFileReader("FieldOfWork", "QuickLoanWithVas"));
		loan.selectOccupation(propertyFileReader("Occupation", "QuickLoanWithVas"));
		loan.selectIndustry(propertyFileReader("Industry1", "QuickLoanWithVas"), propertyFileReader("SubIndustry", "QuickLoanWithVas"));
		loan.selectMaritalStatus(propertyFileReader("MaritalStatusOption1", "QuickLoanWithVas"));
		loan.selectKidsOrDependents(propertyFileReader("DependentsOption2", "QuickLoanWithVas"));
		loan.selectHighestEducationalAttainment(propertyFileReader("EducationalAttainment1","QuickLoanWithVas"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactReferencePage),5,"Contact reference page");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("ContactReference","QuickLoanWithVas"),": page title");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("HighestEducationalAttainment", "QuickLoanWithVas"), ": page title");
		click(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), getTextVal(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), ": button"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactReferencePage),5,"Contact reference page");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("ContactReference","QuickLoanWithVas"),": page title");
		loan.leavingSoonPageValidation();
		loan.updateContactReference(propertyFileReader("InvalidReferenceNumber","QuickLoanWithVas"));
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInvalidMobileNumberMsg),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInvalidMobileNumberMsg),"Error message"))){
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInvalidMobileNumberMsg)),propertyFileReader("InvalidMobileNumberMsg","QuickLoanWithVas"),"Error message");
		}
		loan.updateContactReference(propertyFileReader("ContactMobileNumber","QuickLoanWithVas"));
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRegisteredMobileNumberErrorMsg),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRegisteredMobileNumberErrorMsg),"Error message"))){
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRegisteredMobileNumberErrorMsg)),propertyFileReader("RegisteredMobileNumberErrorMsg","QuickLoanWithVas"),"Error message");
		}
		loan.updateContactReference(propertyFileReader("ReferenceNumber1","QuickLoanWithVas"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHaveAnotherReference),5,"Have another Reference page");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("HaveAnotherReference","QuickLoanWithVas"),": page title");
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)),propertyFileReader("HaveAnotherReferenceSubtitle","QuickLoanWithVas"),": page subtitle");
		loan.updateContactReference(propertyFileReader("ContactMobileNumber","QuickLoanWithVas"));
		if(verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader))){
			verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),": popup header"));
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubtext)),propertyFileReader("SameNumberPopSubTxt","QuickLoanWithVas"),": Popup subtext");
			verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),": popup button"));
		}
		loan.updateContactReference(propertyFileReader("ReferenceNumber1","QuickLoanWithVas"));
		if(verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader))){
			verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),": popup header"));
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubtext)),propertyFileReader("PopSubtext","QuickLoanWithVas"),": Popup subtext");
			verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),": popup button"));
		}
		loan.updateContactReference(propertyFileReader("ReferenceNumber2","QuickLoanWithVas"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIsThisYourCurrentAddPage),5,"is this your current address screen");
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"))) {
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("IsThisYourCurrentAddPage","QuickLoanWithVas"),": page title");
			containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)),propertyFileReader("IsThisYourCurrentAddPageSubtitle","QuickLoanWithVas"),": page subtitle");
			logger.info("TDB_QLV_015, Quick Loan With VAS - User can input the reference contact details and navigated to Is This Your Current Address screen validated");
			extentLoggerPass("TDB_QLV_015", "TDB_QLV_015, Quick Loan With VAS - User can input the reference contact details and navigated to Is This Your Current Address screen validated");
		}
	}

	/**
	 * Verify if user can select the Current Living City
	 * @throws Exception
	 */
	public void selectYourCurrentLivingCityValidation_TDB_QLV_016() throws Exception {
		HeaderChildNode("TDB_QLV_016, Quick Loan With VAS - Verify if user can select the Current Living City");
		tonikLogin(propertyFileReader("password", "Login"));
		loan.loanValidation();
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), ": Tile"));
		loan.whatDoYouNeedItForScreen("Education");
		loan.fieldOfWork(propertyFileReader("FieldOfWork", "QuickLoanWithVas"));
		loan.selectOccupation(propertyFileReader("Occupation", "QuickLoanWithVas"));
		loan.selectIndustry(propertyFileReader("Industry1", "QuickLoanWithVas"), propertyFileReader("SubIndustry", "QuickLoanWithVas"));
		loan.selectMaritalStatus(propertyFileReader("MaritalStatusOption1", "QuickLoanWithVas"));
		loan.selectKidsOrDependents(propertyFileReader("DependentsOption2", "QuickLoanWithVas"));
		loan.selectHighestEducationalAttainment(propertyFileReader("EducationalAttainment1", "QuickLoanWithVas"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactReferencePage),5,"Contact reference page");
		loan.updateContactReference(propertyFileReader("ReferenceNumber1","QuickLoanWithVas"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHaveAnotherReference),5,"Have another Reference Screen");
		loan.updateContactReference(propertyFileReader("ReferenceNumber2","QuickLoanWithVas"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIsThisYourCurrentAddPage),5,"Is this your current address? page");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("IsThisYourCurrentAddPage","QuickLoanWithVas"),": page title");
		containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)),propertyFileReader("IsThisYourCurrentAddPageSubtitle","QuickLoanWithVas"),": page subtitle");
		if(platform.equalsIgnoreCase("ios")){
			List<IOSElement> values = DriverManager.getAppiumDriver().findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAddress));
			for (int address = 0; address < values.size(); address++) {
				String displayedItem = values.get(address).getText();
				logger.info("'" + displayedItem + "' Address "+address+"is displayed");
				ExtentReporter.extentLogger(" ", "'" + displayedItem + "' Address "+address+" is displayed");
			}
		} else {
			List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAddress));
			for (int address = 0; address < values.size(); address++) {
				String displayedItem = values.get(address).getText();
				logger.info("'" + displayedItem + "' Address "+address+"is displayed");
				ExtentReporter.extentLogger(" ", "'" + displayedItem + "' Address "+address+" is displayed");
			}
		}
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objChangeAddressBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objChangeAddressBtn),": button"));
		loan.applyZipCode(propertyFileReader("ValidZipCode","onboarding"));
		waitTime(3000);
		swipe("UP", 3);
		loan.selectBarangay();
		waitTime(5000);
		swipeAddress();
		swipeAddress();
		type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHouseUnitFlrNumberInputField),propertyFileReader("HouseStreetName","QuickLoanWithVas"),"House/Street Name");
		verifyElementPresentAndClick(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),getTextVal(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),": button"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyIncomeScreen),5,"What's your monthly income? screen");
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"))) {
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("MonthlyIncomeScreen", "QuickLoanWithVas"), ": page title");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("MonthlyIncomeScreenSubTitle", "QuickLoanWithVas"), ": page subtitle");
		}
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
		waitTime(2000);
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYesThisMyAddressBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYesThisMyAddressBtn),": button"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyIncomeScreen),5,"What's your monthly income? screen");
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"))) {
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("MonthlyIncomeScreen", "QuickLoanWithVas"), ": page title");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("MonthlyIncomeScreenSubTitle", "QuickLoanWithVas"), ": page subtitle");
			logger.info("TDB_QLV_016, Quick Loan With VAS - User can select the Current Living City and navigated to Is This Your Monthly Income screen validated");
			extentLoggerPass("TDB_QLV_016", "TDB_QLV_016, Quick Loan With VAS - User can select the Current Living City and navigated to Is This Your Monthly Income screen validated");
		}
	}

	/**
	 * Verify if user can input the Monthly income, Company name & TIN details
	 * @throws Exception
	 */
	public void inputMonthlyIncomeCompanyNameTINDetails_TDB_QLV_017() throws Exception {
		HeaderChildNode("TDB_QLV_017, Quick Loan With VAS - Verify if user can input the Monthly income, Company name & TIN details");
		tonikLogin(propertyFileReader("password", "Login"));
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), ": Tile"));
		loan.whatDoYouNeedItForScreen("Education");
		loan.fieldOfWork(propertyFileReader("FieldOfWork", "QuickLoanWithVas"));
		loan.selectOccupation(propertyFileReader("Occupation", "QuickLoanWithVas"));
		loan.selectIndustry(propertyFileReader("Industry1", "QuickLoanWithVas"), propertyFileReader("SubIndustry", "QuickLoanWithVas"));
		loan.selectMaritalStatus(propertyFileReader("MaritalStatusOption1", "QuickLoanWithVas"));
		loan.selectKidsOrDependents(propertyFileReader("DependentsOption2", "QuickLoanWithVas"));
		loan.selectHighestEducationalAttainment(propertyFileReader("EducationalAttainment1", "QuickLoanWithVas"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactReferencePage), 5, "Contact reference page");
		loan.updateContactReference(propertyFileReader("ReferenceNumber1", "QuickLoanWithVas"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHaveAnotherReference), 5, "Have another Reference Screen");
		loan.updateContactReference(propertyFileReader("ReferenceNumber2", "QuickLoanWithVas"));
		loan.isThisYourCurrentAddressConfirmation();
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyIncomeScreen),5,"Monthly Income Screen");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("MonthlyIncomeScreen", "QuickLoanWithVas"), ": page title");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("IsThisYourCurrentAddPage","QuickLoanWithVas"),": page title");
		containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)),propertyFileReader("IsThisYourCurrentAddPageSubtitle","QuickLoanWithVas"),": page subtitle");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYesThisMyAddressBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYesThisMyAddressBtn),": button"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyIncomeScreen),5,"monthly income screen");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("MonthlyIncomeScreen", "QuickLoanWithVas"), ": page title");
		waitTime(3000);
		loan.leavingSoonPageValidation();
		type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyIncomeField),propertyFileReader("ValidIncomeAmount","QuickLoanWithVas"),"Income input field");
		if(platform.equalsIgnoreCase("ios")){
			click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objKeyboardDoneBtn), "Keyboard Done Button");
		}
		click(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), getTextVal(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), ": button"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInputCompanyScreen),5,"Where do you work, luv? Screen");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("InputCompanyScreen", "QuickLoanWithVas"), ": page title");
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("InputCompanyScreenSubtitle", "QuickLoanWithVas"), ": page subtitle");
		loan.leavingSoonPageValidation();
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("MonthlyIncomeScreen", "QuickLoanWithVas"), ": page title");
		verifyElementPresentAndClick(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),getTextVal(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),": button"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCompanyInputField),5,"Company Input field");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("InputCompanyScreen", "QuickLoanWithVas"), ": page title");
		type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCompanyInputField),propertyFileReader("MaxLimitCompanyName","QuickLoanWithVas"),"Company Input Field");
		if(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCompanyInputField)).length()==50){
			logger.info("Maximum input value for Company input field is 50 character validated");
			extentLoggerPass("", "Maximum input value for Company input field is 50 character validated");
		}else{
			logger.info("Maximum input value for Company input field isn't 50 validated");
			extentLoggerFail("", "Maximum input value for Company input field isn't 50 character validated");
		}
		clearField(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCompanyInputField),"Company input field");
		type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCompanyInputField),propertyFileReader("Company","QuickLoanWithVas"),"Company Input Field");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUpdateButton),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUpdateButton),": button"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTINInputField),5,"TIN Screen");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("TINScreen", "QuickLoanWithVas"), ": page title");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCompanyInputField),5,"Company Input screen");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("InputCompanyScreen", "QuickLoanWithVas"), ": page title");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUpdateButton),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUpdateButton),": button"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTINInputField),5,"TIN Screen");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("TINScreen", "QuickLoanWithVas"), ": page title");
		type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTINInputField),propertyFileReader("InvalidTIN","QuickLoanWithVas"),"TIN Input field");
		verifyElementPresentAndClick(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),getTextVal(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),": button"));
		if(verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInvalidTINMsg))){
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInvalidTINMsg)), propertyFileReader("InvalidTINMsg", "QuickLoanWithVas"), ": page title");
		}
		type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTINInputField),propertyFileReader("ValidTIN","QuickLoanWithVas"),"TIN Input field");
		verifyElementPresentAndClick(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),getTextVal(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),": button"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAnotherWayToReachYou),3,"Another way to Reach you screen");
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"))) {
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("AnotherWayToReachYou", "QuickLoanWithVas"), ": page title");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("AnotherWayToReachYouSubtitle", "QuickLoanWithVas"), ": page subtitle");
			logger.info("TDB_QLV_017, Quick Loan With VAS - User can input the Monthly income, Company name & TIN details and navigated to Another way to reach you? screen validated");
			extentLoggerPass("TDB_QLV_017", "TDB_QLV_017, Quick Loan With VAS - User can input the Monthly income, Company name & TIN details and navigated to Another way to reach you? screen validated");
		}
	}

	/**
	 * Verify if user can input Seconday Contact details
	 * @throws Exception
	 */
	public void inputSecondaryContactDetailsValidation_TDB_QLV_018(String mobileNumber,String referenceContact) throws Exception {
		HeaderChildNode("TDB_QLV_018, Quick Loan With VAS - Verify if user can input Secondary Contact details");
		tonikLogin(propertyFileReader("password", "Login"));
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), ": Tile"));
		loan.whatDoYouNeedItForScreen("Education");
		loan.fieldOfWork(propertyFileReader("FieldOfWork", "QuickLoanWithVas"));
		loan.selectOccupation(propertyFileReader("Occupation", "QuickLoanWithVas"));
		loan.selectIndustry(propertyFileReader("Industry1", "QuickLoanWithVas"), propertyFileReader("SubIndustry", "QuickLoanWithVas"));
		loan.selectMaritalStatus(propertyFileReader("MaritalStatusOption1", "QuickLoanWithVas"));
		loan.selectKidsOrDependents(propertyFileReader("DependentsOption2", "QuickLoanWithVas"));
		loan.selectHighestEducationalAttainment(propertyFileReader("EducationalAttainment1", "QuickLoanWithVas"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactReferencePage), 5, "Contact reference page");
		loan.updateContactReference(propertyFileReader("ReferenceNumber1", "QuickLoanWithVas"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHaveAnotherReference), 5, "Have another Reference Screen");
		loan.updateContactReference(propertyFileReader("ReferenceNumber2", "QuickLoanWithVas"));
		loan.isThisYourCurrentAddressConfirmation();
		loan.inputIncomeCompanyAndTINDetails(propertyFileReader("ValidTIN","QuickLoanWithVas"),propertyFileReader("ValidIncome","QuickLoanWithVas"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAnotherWayToReachYou),5,"Another way to reach you? Screen");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("AnotherWayToReachYou", "QuickLoanWithVas"), ": page title");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTINInputField),5,"TIN Screen");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("TINScreen", "QuickLoanWithVas"), ": page title");
		verifyElementPresentAndClick(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),getTextVal(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),": button"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAnotherWayToReachYou),5,"Another way to reach you? Screen");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("AnotherWayToReachYou", "QuickLoanWithVas"), ": page title");
		loan.inputMobileNumberAndNext(mobileNumber);
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),"Popup header"))){
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubtext)),propertyFileReader("SameOnboardedNumberPopup","QuickLoanWithVas"),"Popup header");
			verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),": button"));
		}
		loan.inputMobileNumberAndNext(referenceContact);
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),"Popup header"))){
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubtext)),propertyFileReader("SameAsReferenceNumberPopup","QuickLoanWithVas"),"Popup header");
			verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),": button"));
		}
		loan.inputMobileNumberAndNext(propertyFileReader("SecondaryContact","QuickLoanWithVas"));
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),"Popup header"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubtext)),propertyFileReader("SecondaryContactConfirmation","QuickLoanWithVas"),"Popup header");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),": button"));
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionScreen),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionScreen),": page"))){
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionSubtitle)),propertyFileReader("TermsAndConditionScreenSubtitle","QuickLoanWithVas"),"Page Subtitle");
		}
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAnotherWayToReachYou),5,"Another way to reach you? Screen");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("AnotherWayToReachYou", "QuickLoanWithVas"), ": page title");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSkipBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSkipBtn),": button"));
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),"Popup header"))){
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubtext)),propertyFileReader("SecondaryContactConfirmation","QuickLoanWithVas"),"Popup header");
			verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),": button"));
		}
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionScreen),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionScreen),": page"))){
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionSubtitle)),propertyFileReader("TermsAndConditionScreenSubtitle","QuickLoanWithVas"),"Page Subtitle");
			logger.info("TDB_QLV_018, Quick Loan With VAS - User can input Secondary Contact details and Terms and Conditions screen validated");
			extentLoggerPass("TDB_QLV_018", "TDB_QLV_018, Quick Loan With VAS - User can input Secondary Contact details and Terms and Conditions screen validated");
		}
	}

	/**
	 * Verify if user can accept the Terms & Conditions
	 * @throws Exception
	 */
	public void acceptTermsAndConditionValidation_TDB_QLV_019() throws Exception {
		HeaderChildNode("TDB_QLV_019, Quick Loan With VAS - Verify if user can accept the Terms & Conditions");
		tonikLogin(propertyFileReader("password", "Login"));
		loan.loanValidation();
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), ": Tile"));
		loan.whatDoYouNeedItForScreen(propertyFileReader("ReasonForLoan", "QuickLoanWithVas"));
		loan.fieldOfWork(propertyFileReader("FieldOfWork", "QuickLoanWithVas"));
		loan.selectOccupation(propertyFileReader("Occupation", "QuickLoanWithVas"));
		loan.selectIndustry(propertyFileReader("Industry7", "QuickLoanWithVas"), propertyFileReader("SubIndustry", "QuickLoanWithVas"));
		loan.selectMaritalStatus(propertyFileReader("MaritalStatusOption1", "QuickLoanWithVas"));
		loan.selectKidsOrDependents(propertyFileReader("DependentsOption1", "QuickLoanWithVas"));
		loan.selectHighestEducationalAttainment(propertyFileReader("EducationalAttainment1", "QuickLoanWithVas"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContactReferencePage), 5, "Contact reference page");
		loan.updateContactReference( propertyFileReader("ReferenceNumber1", "QuickLoanWithVas"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHaveAnotherReference), 5, "Have another Reference Screen");
		loan.updateContactReference(propertyFileReader("ReferenceNumber2", "QuickLoanWithVas"));
		loan.isThisYourCurrentAddressConfirmation();
		loan.inputIncomeCompanyAndTINDetails(loan.generateRandomTINNUMBER(), propertyFileReader("ValidIncomeAmount","QuickLoanWithVas"));
		loan.anotherWayToReachYou();
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),"Popup header"))){
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubtext)),propertyFileReader("SecondaryContactConfirmation","QuickLoanWithVas"),"Popup header");
			verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),": button"));
		}
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionScreen),5,"Terms and Condition Screen");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionScreen),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionScreen),": page"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionSubtitle)),propertyFileReader("TermsAndConditionScreenSubtitle","QuickLoanWithVas"),"Page Subtitle");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadTermsAndConditionBtn),"Download Terms and condition button");
		if(platform.equalsIgnoreCase("ios")){
			click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCopyButtonIOS),"Copy Button");
			waitTime(2000);
			click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCloseIcon), "Close Icon");
		}
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),5,"Back button");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAnotherWayToReachYou),5,"Another way to reach you? Screen");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("AnotherWayToReachYou", "QuickLoanWithVas"), ": page title");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSkipBtn),"Skip Button");
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),"Popup header"))){
			verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),": button"));
		}
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionScreen),5,"Terms and Condition Screen");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionScreen),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionScreen),": page"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionSubtitle)),propertyFileReader("TermsAndConditionScreenSubtitle","QuickLoanWithVas"),"Page Subtitle");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadTermsAndConditionBtn),"Download Terms and condition button");
		if(platform.equalsIgnoreCase("ios")){
			click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCopyButtonIOS),"Copy Button");
			waitTime(2000);
			click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCloseIcon), "Close Icon");
		}
		assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadTermsAndConditionBtn)),"true","Attribute value");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionCheckBox),"Terms and condition check box");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIAcceptBtn),"I accept and give my consent button");
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHoldOnPopupTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHoldOnPopupTitle),": page"))){
			containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHoldOnPopupTitle)),propertyFileReader("HoldOnBabeScreen","QuickLoanWithVas"),"Page Title");
			containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHoldOnPopupSubTitle)),propertyFileReader("HoldOnBabeScreenSubHeader","QuickLoanWithVas"),"Page Subtitle");
			logger.info("TDB_QLV_019, Quick Loan With VAS - User can input Secondary Contact details and Terms and Conditions screen validated");
			extentLoggerPass("TDB_QLV_019", "TDB_QLV_019, Quick Loan With VAS - User can input Secondary Contact details and Terms and Conditions screen validated");
		}
	}

	/**
	 * Verify the Loan tile status if the user is soft rejected
	 * @param amount
	 * @throws Exception
	 */
	public void loanSoftRejectionValidation_TDB_QLV_020(String amount) throws Exception {
		HeaderChildNode("TDB_QLV_020, Quick Loan With VAS - Verify the Loan tile status if the user is soft rejected");
		tonikLogin(propertyFileReader("password", "Login"));
		loan.chooseInstallmentPageNavigation(amount);
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn),5,"Continue with PayHinga");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), "Button"));
		if(verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objVerifyNow))) {
			verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objVerifyNow), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objVerifyNow), ": button"));
			waitTime(3000);
			String emailAddress = loan.getEmailAddress("63" + propertyFileReader("SoftRejectMobileNumber", "TestDataNumbers"));
			String otp = loan.getOTP(emailAddress);
			enterOTP(otp);
			click(OnBoardingPage.objCloseBtn,getTextVal(OnBoardingPage.objCloseBtn,"Button"));
			click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), "Button"));
		}
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn),5,"I want To be Protected Btn");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), "Button"));
		waitTime(4000);
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn),5,"Sweet I accept button");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), "Button"));
		loan.loanSegment2Navigation(propertyFileReader("SoftRejectTIN","QuickLoanWithVas"),propertyFileReader("ValidIncome","QuickLoanWithVas"), propertyFileReader("RejectNumber1", "QuickLoanWithVas"),propertyFileReader("RejectNumber2", "QuickLoanWithVas"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHardToSayScreen),5,"Hard to say no");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHardToSayScreen),"Hard To Say Screen");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashboard),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashboard),": button"));
		waitForElementToBePresent(LoginPage.getByOSType(platform,LoginPage.objProfileName),10,"Dashboard");
		scrollToVertical(propertyFileReader("loanTile", "QuickLoanWithVas"));
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSoftRejectLoanStatus),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSoftRejectLoanStatus),"Status"))) {
			verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSoftRejectLoanInfo),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSoftRejectLoanInfo),": info"));
			String userId = loan.getUserId("63" + propertyFileReader("SoftRejectMobileNumber", "TestDataNumbers"));
			assertionValidation(loan.getLoanStatus(userId), propertyFileReader("RejectedStatus", "QuickLoanWithVas"), "Status");
			logger.info("Rejection type"+loan.getLoanRejectionType(userId));
			extentLogger("","Rejection type"+loan.getLoanRejectionType(userId));
			logger.info("TDB_QLV_020, Quick Loan With VAS - Loan tile status if the user is soft rejected validated");
			extentLoggerPass("TDB_QLV_020", "TDB_QLV_020, Quick Loan With VAS - Loan tile status if the user is soft rejected validated");
		}
	}

	/**
	 * Verify the Loan tile status if the user is hard rejected
	 * @param amount
	 * @throws Exception
	 */
	public void loanHardRejectionValidation_TDB_QLV_021(String amount) throws Exception {
		HeaderChildNode("TDB_QLV_021, Quick Loan With VAS - Verify the Loan tile status if the user is hard rejected");
		tonikLogin(propertyFileReader("password", "Login"));
		loan.chooseInstallmentPageNavigation(amount);
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn),5,"Continue with PayHinga");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), "Button"));
		if(verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objVerifyNow))) {
			verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objVerifyNow), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objVerifyNow), ": button"));
			waitTime(3000);
			String emailAddress = loan.getEmailAddress("63" + propertyFileReader("HardRejectMobileNumber", "TestDataNumbers"));
			String otp = loan.getOTP(emailAddress);
			enterOTP(otp);
			click(OnBoardingPage.objCloseBtn,getTextVal(OnBoardingPage.objCloseBtn,"Button"));
			click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), "Button"));
		}
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn),5,"I want To be Protected Btn");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), "Button"));
		waitTime(4000);
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn),5,"Sweet I accept button");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), "Button"));
		loan.loanSegment2Navigation(propertyFileReader("ValidTIN","QuickLoanWithVas"),propertyFileReader("ValidIncomeAmount","QuickLoanWithVas"),
				propertyFileReader("RejectNumber1", "QuickLoanWithVas"),propertyFileReader("RejectNumber2", "QuickLoanWithVas"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHardToSayScreen),5,"Hard to say no");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHardToSayScreen),"Hard To Say Screen");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashboard),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashboard),": button"));
		waitForElementToBePresent(LoginPage.getByOSType(platform,LoginPage.objProfileName),10,"Dashboard");
		swipe("UP",2);
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSoftRejectLoanStatus),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSoftRejectLoanStatus),"Status"))) {
			verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSoftRejectLoanInfo),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSoftRejectLoanInfo),": info"));
			String userId = loan.getUserId("63" + propertyFileReader("HardRejectMobileNumber", "TestDataNumbers"));
			assertionValidation(loan.getLoanStatus(userId), propertyFileReader("RejectedStatus", "QuickLoanWithVas"), "Status");
			logger.info("Rejection type : "+loan.getLoanRejectionType(userId));
			extentLogger("","Rejection type : "+loan.getLoanRejectionType(userId));
			logger.info("TDB_QLV_021, Quick Loan With VAS - Loan tile status if the user is soft rejected validated");
			extentLoggerPass("TDB_QLV_021", "TDB_QLV_021, Quick Loan With VAS - Loan tile status if the user is soft rejected validated");
		}
	}

	/**
	 * Verify the Loan tile if the user is pre-approved
	 * @throws Exception
	 */
	public void verifyLoanTileIfUserPreApproved_TDB_QLV_025(String mobileNo) throws Exception {
		HeaderChildNode("TDB-QLV-025, Verify the Loan tile if the user is pre-approved");
		tonikLogin(propertyFileReader("password", "Login"));
		loan.inProgressTileValidation();
		waitTime(20000);
		loan.approveLoanStatus(mobileNo);
		waitTime(5000);
		if(platform.equalsIgnoreCase("ios")){
			closeiOSTonikApp();
		} else{
			closeAndroidTonikApp();
		}
		waitTime(3000);
		relaunchApp(platform);
		tonikLogin(propertyFileReader("password", "Login"));
		loan.loanApprovedTileValidation();
		boolean isBtnEnabled;
		if(platform.equalsIgnoreCase("ios")){
			IOSElement historyBtn = (IOSElement) DriverManager.getAppiumDriver().findElement(FriendsWithBenefitsPages.getByOSType(platform,FriendsWithBenefitsPages.objHistoryBtn));
			isBtnEnabled = historyBtn.isEnabled();
		}else {
			AndroidElement historyBtn = (AndroidElement) findElement(FriendsWithBenefitsPages.getByOSType(platform,FriendsWithBenefitsPages.objHistoryBtn));
			isBtnEnabled = historyBtn.isEnabled();
		}
		if(isBtnEnabled) {
			logger.info("TSA created & all the tiles menu are enabled");
			extentLoggerPass("TSA Created","TSA created & all the tiles menu are enabled");
		}else{
			logger.info("Failed to create TSA created & all the tiles menu are disabled");
			extentLoggerFail("No TSA Created","Failed to create TSA created & all the tiles menu are disabled");
		}
	}

	/**
	 * Verify if user can accept the loan offer from the Loan Offer screen
	 * @throws Exception
	 */
	public void verifyUserAcceptLoanOfferFromLoanOfferScreen_TDB_QLV_026(String amount,String loanType,String loanAmount,String tenure,String interestRate) throws Exception {
		HeaderChildNode("TDB-QLV-026, Verify if user can accept the loan offer from the Loan Offer screen");
		tonikLogin(propertyFileReader("password", "Login"));
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
		loan.wootYouGotApprovedPageValidation();
		String approveAmount = getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objApprovedAmountTxt));
		approveAmount = loan.removeSpecialCharacter(approveAmount, false);
		containsValidation(approveAmount, amount, "Amount");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTenureAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTenureAmount), "Amount"));
		String approvedTenureAmount = getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTenureAmount));
		approvedTenureAmount = loan.removeSpecialCharacter(approvedTenureAmount, false);
		valueValidation(approvedTenureAmount, loan.reviseedTotalEmiAmout(loanAmount,tenure,interestRate,loanType), "EMI Amount", "contains");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objApprovedAmountTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objApprovedAmountTxt), "Amount"));
		valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objApprovedRecepientTxt)), propertyFileReader("confirmInstallmentTermTxt", "QuickLoanWithVas"), "Text", "contains");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAcceptOfferBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAcceptOfferBtn), "Button"));
		valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAcceptOfferBtn)), propertyFileReader("accetOfferBtnTxt", "QuickLoanWithVas"), "Button Text", "contains");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
		loan.loanApprovedTileValidation();
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAcceptOfferBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAcceptOfferBtn), "Button"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), 10, "Tell us your pay day's screen");
		if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), "Page Header"))) {
			logger.info("User redirected to " + getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), "Page Header"));
			extentLoggerPass("Tell us page ", "User redirected to " + getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), "Page Header"));
		}
	}

	/**
	 * Verify if user can select the Pay Day & confirm Monthly Installment Summary
	 * @throws Exception
	 */
	public void verifyUserSelectPayDayAndConfirmMonthlyInstallmentSummary_TDB_QLV_27(String loanType) throws Exception {
		HeaderChildNode("TDB-QLV-027, Verify if user can select the Pay Day & confirm Monthly Installment Summary");
		tonikLogin(propertyFileReader("password", "Login"));
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAcceptOfferBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAcceptOfferBtn), "Button"));
		loan.tellUsYourPayDayPageValidation();
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), "Next Button");
		loan.monthlyInstallmentSummaryPageValidation(loanType,loan.extractedDate(), propertyFileReader("monthlyPageSubHeader", "QuickLoanWithVas"));
		loan.contactUsPageValidation();
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objConfirmBtn), "Confirm Button");
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignedPage), 10, "Signed, sealed page");
		if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignedPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignedPage), "Page"))) {
			logger.info("TDB-QLV-027, Successfully navigated to : " + getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignedPage), "Page"));
			extentLoggerPass("Signed, Sealed Page", "TDB-QLV-027, Successfully navigaed to : " + getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignedPage), "Page"));
		}
	}

	/**
	 * Verify if user can sign all the Loan documents & disburse the loan successfully
	 * @throws Exception
	 */
	public void verifyUserCanSignLoanDocumentsDisburseLoanSuccessfully_TDB_QLV_28() throws Exception {
		HeaderChildNode("TDB-QLV-028, Verify if user can sign all the Loan documents & disburse the loan successfully");
		tonikLogin(propertyFileReader("password", "Login"));
		loan.documentarySignProcessValidation();
		waitTime(2000);
		loan.iWillDoItLaterPopup();
		if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainDashBoard), "Your tonik account header")) {
			logger.info("TDB-QLV-028, Verify if user can sign all the Loan documents & disburse the loan successfully validated");
			extentLoggerPass("TDB-QLV-028", " Verify if user can sign all the Loan documents & disburse the loan successfully validated");
		}
	}

	/**
	 * Verify the Loans tile status after the successful loan disbursal
	 * @throws Exception
	 */
	public void verifyLoansTileStatusAfterSuccessfulLoanDisbursal_TDB_QLV_029() throws Exception {
		HeaderChildNode("TDB-QLV-029, Verify the Loans tile status after the successful loan disbursal");
		tonikLogin(propertyFileReader("password", "Login"));
		if(verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIWillDoItBtn))){
			click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIWillDoItBtn), "I Will Do It Later Button");
		}
		loan.yesYesYesTileValidation();
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseBtn), "I want to close my loan Button")) {
			logger.info("TDB-QLV-029, Verify the Loans tile status after the successful loan disbursal validated");
			extentLoggerPass("TDB-QLV-029", "TDB-QLV-029, Verify the Loans tile status after the successful loan disbursal validated");
		}
	}

	/**
	 * Verify if the user can view the Loan Dashboard screen
	 * @throws Exception
	 */
	public void verifyUserViewLoanDashboardScreen_TDB_QLV_031(String amount,String tenure,String interest,String loanType) throws Exception {
		HeaderChildNode("TDB-QLV-031, Verify if the user can view the Loan Dashboard screen");
		tonikLogin(propertyFileReader("password", "Login"));
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
		loan.iWantToCloseMyLoanPageValidation(loanType,amount,tenure,interest);
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Redirected to main Dashboard Page");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactUsButton),"Contact Us Button");
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objGetInTouchPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objGetInTouchPage), "Page"))) {
			logger.info("TDB-QLV-031, Verify if the user can view the Loan Dashboard screen validated");
			extentLoggerPass("TDB-QLV-031", "TDB-QLV-031 Verify if the user can view the Loan Dashboard screen validated");
		}
	}

	/**
	 * Verify if user can access the PayHinga tile from the Loan Dashboard screen
	 * @throws Exception
	 */
	public void verifyUserCanAccessPayHingaTileFromLoanDashboardScreen_TDB_QLV_032() throws Exception {
		HeaderChildNode("TDB-QLV-032, Verify if user can access the PayHinga tile from the Loan Dashboard screen");
		tonikLogin(propertyFileReader("password", "Login"));
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPayhingaTitle)), propertyFileReader("payHingaTxt", "QuickLoanWithVas"), "Payhinga Tile");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLearnMoreBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLearnMoreBtn), "Button"));
		waitTime(3000);
		if(platform.equalsIgnoreCase("ios")){
			List<IOSElement> values = DriverManager.getAppiumDriver().findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTileSubHeader));
			for (int tile = 1; tile < values.size(); tile++) {
				assertionValidation(getText(QuickLoanWithVasPage.objTileSubHeader(platform, tile)),propertyFileReader("tile"+tile,"QuickLoanWithVas"),": Tile");
				containsValidation(getText(QuickLoanWithVasPage.objTileInfo(platform, tile)),propertyFileReader("tileInfo"+tile,"QuickLoanWithVas"),": Tile Info");
			}
		} else {
			List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTileSubHeader));
			for (int tile = 1; tile < values.size(); tile++) {
				assertionValidation(getText(QuickLoanWithVasPage.objTileSubHeader(platform, tile)),propertyFileReader("tile"+tile,"QuickLoanWithVas"),": Tile");
				containsValidation(getText(QuickLoanWithVasPage.objTileInfo(platform, tile)),propertyFileReader("tileInfo"+tile,"QuickLoanWithVas"),": Tile Info");
			}
		}
		if(platform.equalsIgnoreCase("android")){
			click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadOurFAQBtn), "Read our FAQs to learn more Button");
			waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objWebView), 3, "Switching to Webview");
			verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objWebView),"Web Page");
			DriverManager.getAppiumDriver().navigate().back();
		} else {
			swipe("UP", 1);
			verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadOurFAQBtn), "Read our FAQs to learn more Button");
		}
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objGotItButton), "Got It Button");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextInstallmentTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextInstallmentTxt), "Loan Dashboard Screen"));
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLearnMoreBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLearnMoreBtn), "Button"));
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMunaPageHowItWorks), "How it works Button");
		if(platform.equalsIgnoreCase("ios")){
			List<IOSElement> values = DriverManager.getAppiumDriver().findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTileSubHeader));
			for (int tile = 1; tile < values.size(); tile++) {
				assertionValidation(getText(QuickLoanWithVasPage.objTileSubHeader(platform, tile)),propertyFileReader("howItWorksTile"+tile,"QuickLoanWithVas"),": Tile");
				containsValidation(getText(QuickLoanWithVasPage.objTileInfo(platform, tile)),propertyFileReader("howItWorksInfo"+tile,"QuickLoanWithVas"),": Tile Info");
			}
		} else {
			List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTileSubHeader));
			for (int tile = 1; tile < values.size(); tile++) {
				assertionValidation(getText(QuickLoanWithVasPage.objTileSubHeader(platform, tile)),propertyFileReader("howItWorksTile"+tile,"QuickLoanWithVas"),": Tile");
				containsValidation(getText(QuickLoanWithVasPage.objTileInfo(platform, tile)),propertyFileReader("howItWorksInfo"+tile,"QuickLoanWithVas"),": Tile Info");
			}
		}
		swipe("UP", 1);
		containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHelUsTxt)), propertyFileReader("howItWorksTile5", "QuickLoanWithVas"), "Tile header");
		containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHelpUsTileSubHeader)), propertyFileReader("howItWorksInfo5", "QuickLoanWithVas"), "Tile Sub Header");
		containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadOurFAQBtn)), propertyFileReader("readOurFAQButtonTxt", "QuickLoanWithVas"), "Button Text");
		containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objGotItButton)), propertyFileReader("gotItBtnTxt", "QuickLoanWithVas"), "Button Text");
		if(platform.equalsIgnoreCase("android")){
			click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadOurFAQBtn), "Read our FAQs to learn more Button");
			waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objWebView), 10, "Switching to Webview");
			verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objWebView), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objWebView), "URL"));
			DriverManager.getAppiumDriver().navigate().back();
		} else {
			swipe("UP", 1);
			verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadOurFAQBtn), "Read our FAQs to learn more Button");
		}
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objGotItButton), "Got It Button");
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextInstallmentTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextInstallmentTxt), "Loan Dashboard Screen"))) {
			logger.info("TDB-QLV-032, Verify if user can access the PayHinga tile from the Loan Dashboard screen validated");
			extentLoggerPass("TDB-QLV-032", "TDB-QLV-032, Verify if user can access the PayHinga tile from the Loan Dashboard screen validated");
		}
	}

	/**
	 * Verify if user can access the Life Insurance tile from the Loan Dashboard screen
	 * @throws Exception
	 */
	public void verifyUserCanAccessLifeInsuranceTileFromLoanDashboardScreen_TDB_QLV_033() throws Exception {
		HeaderChildNode("TDB-QLV-033, Verify if user can access the Life Insurance tile from the Loan Dashboard screen");
		tonikLogin(propertyFileReader("password", "Login"));
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLifeInsurancePayhinga), 10, "Life Insurance Payhinga");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLifeInsurancePayhinga), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLifeInsurancePayhinga),"Payhinga Tile"));
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt),"Page Header"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("proofOfCover", "QuickLoanWithVas"), "Page Header");
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), 10, "Download Button");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), "Button"));
		if(platform.equalsIgnoreCase("ios")){
			waitTime(3000);
			click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCopyButtonIOS), "Copy Button");
			waitTime(2000);
			click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCloseIcon), "Close Icon");
		}
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextInstallmentTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextInstallmentTxt), "Text"))) {
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseLoanButton)), propertyFileReader("iWantToCloseLoanBtn", "QuickLoanWithVas"), "Button");
			logger.info("TDB-QLV-033, Verify if user can access the Life Insurance tile from the Loan Dashboard screen validated");
			extentLoggerPass("TDB-QLV-033", "Verify if user can access the Life Insurance tile from the Loan Dashboard screen validated");
		}
	}

	/**
	 * Verify if user can access the Loan Information screen
	 * @throws Exception
	 */
	public void verifyUserAccessLoanInformationScreen_TDB_QLV_034 (String amount,String tenure,String interest,String loanType) throws Exception {
		HeaderChildNode("TDB-QLV-034, Verify if user can access the Loan Information screen");
		tonikLogin(propertyFileReader("password", "Login"));
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoButton), 10, "Information Button");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoButton), "Information Button");
		loan.loanInformationScreen(loanType,amount,tenure,interest);
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
		if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainDashBoard), "Your tonik account header")) {
			logger.info("TDB-QLV-034, Verify if user can access the Loan Information screen validated");
			extentLoggerPass("TDB-QLV-034", "Verify if user can access the Loan Information screen validated");
		}
	}

	/**
	 * Verify if user can view the Rates and fees details
	 * @throws Exception
	 */
	public void verifyUserCanViewRatesFeesDetails_TDB_QLV_035(String amount,String tenure,String interest,String loanType) throws Exception {
		HeaderChildNode("TDB-QLV-035, Verify if user can view the Rates and fees details");
		tonikLogin(propertyFileReader("password", "Login"));
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoButton), 10, "Information Button");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoButton), "Information Button");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoScreeRateAndFees), "Rates and fees");
		loan.ratesAndFeesValidation(amount,tenure,interest,loanType);
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
		if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt)))) {
			logger.info("TDB-QLV-035, Verify if user can view the Rates and fees details validated");
			extentLoggerPass("TDB-QLV-035", "Verify if user can view the Rates and fees details validated");
		}
	}

	/**
	 * Verify if user can view & download all the Loan documents
	 * @throws Exception
	 */
	public void userCanViewAndDownloadAllLoanDocumentsValidation_TDB_QLV_036(String vasType) throws Exception {
		HeaderChildNode("TDB_QLV_036, Quick Loan With VAS - Verify if user can view & download all the Loan documents");
		tonikLogin(propertyFileReader("password", "Login"));
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), ": Tile"));
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoButton), "Info button");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("LoanInformation", "QuickLoanWithVas"), ": page title");
		swipe("UP", 1);
		if(platform.equalsIgnoreCase("ios")){
			List<IOSElement> values = DriverManager.getAppiumDriver().findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansDocuments));
			for (int document = 1; document < values.size(); document++) {
				assertionValidation(getText(QuickLoanWithVasPage.objLoansDocuments(platform, document)), propertyFileReader("LoanDocument" + document, "QuickLoanWithVas"), "Loan document");
				click(QuickLoanWithVasPage.objLoansDocuments(platform, document), getTextVal(QuickLoanWithVasPage.objLoansDocuments(platform, document), "Document"));
				waitTime(5000);
				verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"));
				assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("LoanDocument" + document, "QuickLoanWithVas"), ": Loan document");
				isButtonEnabled(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), ": button"));
				click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), ": button"));
				waitTime(3000);
				click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCopyButtonIOS), "Copy Button");
				waitTime(1000);
				click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCloseIcon), "Close Icon");
				waitTime(2000);
				click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back button");
			}
		} else {
			List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansDocuments));
			for (int document = 1; document < values.size(); document++) {
				assertionValidation(getText(QuickLoanWithVasPage.objLoansDocuments(platform, document)), propertyFileReader("LoanDocument" + document, "QuickLoanWithVas"), "Loan document");
				click(QuickLoanWithVasPage.objLoansDocuments(platform, document), getTextVal(QuickLoanWithVasPage.objLoansDocuments(platform, document), "Document"));
				waitTime(5000);
				verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"));
				assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("LoanDocument" + document, "QuickLoanWithVas"), ": Loan document");
				isButtonEnabled(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), ": button"));
				click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), ": button"));
				click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back button");
			}
		}
		if(vasType.equalsIgnoreCase("WithVAS")){
			verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoScreenAboutPayhingaTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoScreenAboutPayhingaTxt), ": document"));
			waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAboutPayhingaScreen), 5, "About payhinga screen");
			verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page"));
			containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("AboutPayhinga", "QuickLoanWithVas"), "page title");
		}
		logger.info("TDB_QLV_036, Quick Loan With VAS - User can view & download all the Loan documents validated");
		extentLoggerPass("TDB_QLV_036", "TDB_QLV_036, Quick Loan With VAS - User can view & download all the Loan documents validated");
	}
	/**
	 * Verify the TSA balance in the main Dashboard after Loan disbursal
	 * @throws Exception
	 */
	public void verifyTsaBalanceInMainScreenAfterLoanDisbursal_TDB_QLV_037(String amount,String tenure) throws Exception {
		HeaderChildNode("TDB-QLV-037, Verify the TSA balance in the main Dashboard after Loan disbursal");
		tonikLogin(propertyFileReader("password", "Login"));
		if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceOnScreen), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceOnScreen), "Balance On Screen"))) {
			String actualBalanceOnScreen = getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceOnScreen));
			actualBalanceOnScreen = loan.removeSpecialCharacter(actualBalanceOnScreen, false);
			assertionValidation(actualBalanceOnScreen, loan.loanDisbursalAmount(amount,tenure,propertyFileReader("processingFee", "QuickLoanWithVas")), "Amount");
			logger.info("TDB-QLV-037, Verify the TSA balance in the main Dashboard after Loan disbursal validated");
			extentLoggerPass("TDB-QLV-037", "TDB-QLV-037, Verify the TSA balance in the main Dashboard after Loan disbursal validated");
		}
	}

	/**
	 * Verify the loan transaction details from the History screen
	 * @param amount
	 * @throws Exception
	 */
	public void loanTransactionDetailsValidationFromHistoryScreen_TDB_QLV_038(String amount,String mobileNumber,String tenure) throws Exception {
		HeaderChildNode("TDB_QLV_038, Quick Loan With VAS - Verify the loan transaction details from the History screen");
		tonikLogin(propertyFileReader("password", "Login"));
		verifyElementPresentAndClick(FriendsWithBenefitsPages.getByOSType(platform,FriendsWithBenefitsPages.objHistoryBtn),"History Icon");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("AccountHistory", "QuickLoanWithVas"), ": page title");
		verifyElementPresent(QuickLoanWithVasPage.objTransaction(platform,"Processing Fee"),getTextVal(QuickLoanWithVasPage.objTransaction(platform,"Processing Fee"),": transaction"));
		verifyElementPresent(QuickLoanWithVasPage.objTransaction(platform,"DST Fee"),getTextVal(QuickLoanWithVasPage.objTransaction(platform,"DST Fee"),": transaction"));
		verifyElementPresent(QuickLoanWithVasPage.objTransaction(platform,"Money Credited"),getTextVal(QuickLoanWithVasPage.objTransaction(platform,"Money Credited"),": transaction"));
		String dst = String.valueOf(Math.round(Float.parseFloat(loan.documentStampFeeCalculator(Integer.parseInt(amount),Integer.parseInt(tenure)))));
		containsValidation(getText(QuickLoanWithVasPage.objTransactionAmount(platform,"Processing Fee")),propertyFileReader("ProcessingFee","QuickLoanWithVas"),": Processing Fee");
		containsValidation(getText(QuickLoanWithVasPage.objTransactionAmount(platform,"DST Fee")), dst,": DST Fee");
		containsValidation(loan.removeSpecialCharacter(getText(QuickLoanWithVasPage.objTransactionAmount(platform, "Money Credited")),true),amount,": Money Credited");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
		if(verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objProfileName),getTextVal(LoginPage.getByOSType(platform,LoginPage.objProfileName),": profile name"))){
			logger.info("Navigated back to Dashboard");
			extentLoggerPass("TDB_QLV_038", "Navigated back to Dashboard");
		}
		verifyElementPresentAndClick(OnBoardingPage.objHistoryIcon,"History Icon");
		click(QuickLoanWithVasPage.objTransaction(platform,"Money Credited"),getTextVal(QuickLoanWithVasPage.objTransaction(platform,"Money Credited"),": transaction"));
		loan.transactionDetails(amount,propertyFileReader("LoanMoneyCreditedFrom","QuickLoanWithVas"),loan.getAccountNumber("63"+mobileNumber),"QuickLoanWithVas");
		waitTime(4000);
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
		click(QuickLoanWithVasPage.objTransaction(platform,"DST Fee"),getTextVal(QuickLoanWithVasPage.objTransaction(platform,"DST Fee"),": transaction"));
		loan.transactionDetails(dst,loan.getAccountNumber("63"+mobileNumber),propertyFileReader("DocumentStampFeeTo","QuickLoanWithVas"),"QuickLoanWithVas");
		waitTime(4000);
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
		click(QuickLoanWithVasPage.objTransaction(platform,"Processing Fee"),getTextVal(QuickLoanWithVasPage.objTransaction(platform,"Processing Fee"),": transaction"));
		loan.transactionDetails(propertyFileReader("ProcessingFee","QuickLoanWithVas"),loan.getAccountNumber("63"+mobileNumber),propertyFileReader("ProcessingFeeTo","QuickLoanWithVas"),"QuickLoanWithVas");
		logger.info("TDB_QLV_038, Quick Loan With VAS - Loan transaction details from the History screen validated");
		extentLoggerPass("TDB_QLV_038", "TDB_QLV_038, Quick Loan With VAS - Loan transaction details from the History screen validated");
	}

	/**
	 * Verify if user can view the Loan Repayment screen
	 * @param dueDate
	 * @throws Exception
	 */
	public void verifyUserCanViewLoanPaymentScreen_TDB_QLV_039(int dueDate,String mobileNumber) throws Exception {
		HeaderChildNode("TDB_QLV_039, Quick Loan With VAS - Verify if user can view the Loan Payment screen");
		String userId = loan.getUserId("63"+mobileNumber);
		String loanAccountNumber = loan.getLoanAccountNumber(userId);
		String date = loan.returnDate(dueDate,"yyyy-MM-dd");
		loan.updateDueDateInLoanInstallmentDetails(date,loanAccountNumber);
		logger.info("Due date modified in Loan Installment details: "+date);
		extentLogger("","Due date modified in Loan Installment details: "+date);
		loan.updateRepaymentDateInLoanRepaymentSchedule(date,loanAccountNumber,"1");
		tonikLogin(propertyFileReader("password","Login"));
		String balance = getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceOnScreen));
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPayNowBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPayNowBtn),"Button"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanPaymentScreen),5,"Loan payment screen");
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),"Screen"))){
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("LoanPaymentScreen","QuickLoanWithVas"),"Screen");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAmountDueField)),propertyFileReader("AmountDueField","QuickLoanWithVas"),"field");
			containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAmountDue)).replaceAll(",",""),loan.getLoanRepaymentDetails("repaymentAmount",loanAccountNumber,"1"),"Amount due");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLateFeesField)),propertyFileReader("LateFeesField","QuickLoanWithVas"),"field");
			containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLateFee)).replaceAll(",",""),propertyFileReader("LateFee","QuickLoanWithVas"),"Late fee");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAmountToBePaidField)),propertyFileReader("AmountToBePaidField","QuickLoanWithVas"),"field");
			containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAmountToBePaid)).replaceAll(",",""),
					String.valueOf(loan.getAvailableBalanceInteger(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAmountDue))+loan.getAvailableBalanceInteger(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLateFee))), "Amount to be paid");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPaymentOptionsHeader)),propertyFileReader("PaymentOptionsHeader","QuickLoanWithVas"),"Header");
			containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceDisplayedInLoanPaymentScreen)),balance,"Balance");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objOtherHeader)),propertyFileReader("OtherHeader","QuickLoanWithVas"),"Header");
			swipe("UP", 1);
			if(platform.equalsIgnoreCase("ios")){
				List<IOSElement> values = DriverManager.getAppiumDriver().findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objOtherPaymentOptions));
				for (int option = 1; option <= values.size(); option++) {
					assertionValidation(getText(QuickLoanWithVasPage.objOtherPaymentOptions(platform, option)), propertyFileReader("OtherPaymentOptions" + option, "QuickLoanWithVas"), ": Other Payment option");
					assertionValidation(getText(QuickLoanWithVasPage.objOtherPaymentOptionsInfo(platform, option)), propertyFileReader("OtherPaymentOptionsInfo" + option, "QuickLoanWithVas"), ": Other Payment option info");
				}
			} else {
				List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objOtherPaymentOptions));
				for (int option = 1; option <= values.size(); option++) {
					assertionValidation(getText(QuickLoanWithVasPage.objOtherPaymentOptions(platform, option)), propertyFileReader("OtherPaymentOptions" + option, "QuickLoanWithVas"), ": Other Payment option");
					assertionValidation(getText(QuickLoanWithVasPage.objOtherPaymentOptionsInfo(platform, option)), propertyFileReader("OtherPaymentOptionsInfo" + option, "QuickLoanWithVas"), ": Other Payment option info");
				}
			}
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSelectPaymentOption)),propertyFileReader("SelectPaymentOption","QuickLoanWithVas"),"Text");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objConfirmPaymentBtn)),propertyFileReader("ConfirmPaymentBtn","QuickLoanWithVas"),"Button");
			click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceOnScreen)),balance,"Balance");
			logger.info("TDB_QLV_039, Quick Loan With VAS - User can view the Loan Payment screen validated");
			extentLoggerPass("TDB_QLV_039", "TDB_QLV_039, Quick Loan With VAS - User can view the Loan Payment screen validated");
		}
	}

	/**
	 * Verify if user can make full repayment with insufficient TSA balance
	 * @throws Exception
	 */
	public void verifyUserMakeFullRepaymentWithInsufficientTSABalance_TDB_QLV_110(String amount,String tenure,String interest,String loanType) throws Exception {
		HeaderChildNode("TDB-QLV-110, Verify if user can make full repayment with insufficient TSA balance");
		tonikLogin(propertyFileReader("password", "Login"));
		tsaAmountInDashboard = getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTSAAmount)).substring(1).replaceAll(",", "");
		if(Double.parseDouble(tsaAmountInDashboard)>Double.parseDouble(propertyFileReader("loanAmount","QuickLoanWithVas"))){
			loan.sendMoney(String.valueOf(Double.parseDouble(tsaAmountInDashboard)-Double.parseDouble(amount)));
		}
		click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), "Loans Tile");
		click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIwantToCloseLoanButton), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIwantToCloseLoanButton), "Button"));
		loan.fullRepaymentConfirmationPopup();
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt), "Page Header"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("beforeCloseLoanPage", "QuickLoanWithVas"), "Page Header");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSubTitleTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSubTitleTxt), "Page Header"));
		containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSubTitleTxt)), propertyFileReader("canWeTakeLookTxt", "QuickLoanWithVas"), "Page Sub Header");
		containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFullRepaymentTxt)), propertyFileReader("fullRepaymentAmountTxt", "QuickLoanWithVas"), "Text");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objRepaymentAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objRepaymentAmount), "Repayment Amount"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInterestTxt)), propertyFileReader("interestTxt", "QuickLoanWithVas"), "Text");
		String interestRate = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInterestRate));
		interestRate = loan.removeSpecialCharacter(interestRate, false);
		String actualRepaymentAmount = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objRepaymentAmount));
		actualRepaymentAmount = loan.removeSpecialCharacter(actualRepaymentAmount, false);
		double repayAmount = Double.parseDouble(loan.repaymentAmountCalculation(loanType, amount, tenure, interest, false)) + Double.parseDouble(interestRate);
		String expectedRepayAmount = loan.formatToTwoDecimalPlaces(String.valueOf(repayAmount));
		assertionValidation(actualRepaymentAmount, expectedRepayAmount, "Repayment Amount");
		containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBreakDownTxt)), propertyFileReader("breakDownTxt", "QuickLoanWithVas"), "Text");
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanProduct)), propertyFileReader("loanProduct", "QuickLoanWithVas"), "Text");
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objQuickLoan)), propertyFileReader("quickLoanTxt", "QuickLoanWithVas"), "Text");
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPrincipal)), propertyFileReader("principalTxt", "QuickLoanWithVas"), "Text");
		String principalAmount = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPrincipalAmount));
		principalAmount = loan.removeSpecialCharacter(principalAmount, false);
		assertionValidation(principalAmount, loan.formatToTwoDecimalPlaces(amount), "Text");
		if (loanType.equalsIgnoreCase("WithVAS")) {
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayHingaFeeTxt)), propertyFileReader("payHingaFeeTxt", "QuickLoanWithVas"), "Text");
			String payHingaFee = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayHingaFeeAmount));
			payHingaFee = loan.removeSpecialCharacter(payHingaFee, false);
			String expectedPayHingaFee = loan.payHinga(loan.calculateEMI(Double.parseDouble(amount), Double.parseDouble(tenure), Double.parseDouble(interest)));
			assertionValidation(payHingaFee, loan.formatToTwoDecimalPlaces(expectedPayHingaFee), "Payhinga Fee Amount");
		}
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNotEnoughBalanceTxt)), propertyFileReader("notEnoughBalance", "QuickLoanWithVas"), "Text");
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTheOnlyWayToProcess)), propertyFileReader("noBustTxt", "QuickLoanWithVas"), "Text");
		swipe("UP", 1);
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTonikAccBalTxt)), propertyFileReader("tonikAccBal", "QuickLoanWithVas"), "Text");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceAmount), "Balance Amount"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTopUpTonicAccountButton)), propertyFileReader("topUpMyTonikBtnTxt", "QuickLoanWithVas"), "Button");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextInstallmentTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextInstallmentTxt), "Loan Dashboard Screen"))) {
			logger.info("TDB-QLV-110, Verify if user can make full repayment with insufficient TSA balance validated");
			extentLoggerPass("TDB-QLV-110", "TDB-QLV-110, Verify if user can make full repayment with insufficient TSA balance validated");
		}
	}

	/***
	 * Verify if user can make full repayment with sufficient TSA balance
	 * @throws Exception
	 */
	public void verifyUserCanMakeFullRepaymentWithSufficientTSABalance_TDB_QLV_111(String amount,String tenure,String interest,String mobileNumber,String loanType) throws Exception {
		HeaderChildNode("TDB-QLV-111, Verify if user can make full repayment with sufficient TSA balance");
		tonikLogin(propertyFileReader("password", "Login"));
		loan.insufficientTSA(amount);
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseLoanButton), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseLoanButton),"Button"));
		loan.fullRepaymentConfirmationPopup();
		waitForElementToBePresent((QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objFullRepaymentTxt)),3,"Full repayment text");
		verticalSwipeTillElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceAmount));
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceAmount), "Balance Amount"));
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTopUpTonicAccountButton), "Top up my Tonik account Button");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTopUpTonicAccountButton), "Top up my Tonik account Button");
		loan.topUp(String.valueOf(Integer.parseInt(amount)+2000));
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseLoanButton), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseLoanButton),"Button"));
		loan.fullRepaymentConfirmationPopup();
		waitForElementToBePresent((QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objFullRepaymentTxt)),3,"Full repayment text");
		verticalSwipeTillElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceAmount));
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceAmount), "Balance Amount"));
		String tonikAccBalAfterTopup = getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceAmount));
		tonikAccBalAfterTopup = loan.removeSpecialCharacter(tonikAccBalAfterTopup, false);
		double balAfterTopUp=Double.parseDouble(tonikAccBalAfterTopup);
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCloseMyLoanButton), "Close My Loan Button");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCloseMyLoanButton),"Close My Loan Button");
		waitForElementToBePresent((QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDropTheMicPage)),5,"Drop The Mic Page");
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDropTheMicPage)), propertyFileReader("dropMicPageHeader", "QuickLoanWithVas"), "Page Header");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashBoardButton), "Back to Dashboard Button");
		double balanceAmount = balAfterTopUp - Double.parseDouble(loan.repaymentAmountCalculation(loanType,amount,tenure,interest,false));
		balanceAmount= Double.parseDouble(loan.formatToTwoDecimalPlaces(String.valueOf(balanceAmount)));
		String availableBalance = getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceOnScreen));
		containsValidation(loan.removeSpecialCharacter(availableBalance,false),String.valueOf(balanceAmount),"Amount");
		verifyElementPresentAndClick(FriendsWithBenefitsPages.getByOSType(platform,FriendsWithBenefitsPages.objHistoryBtn), "History Icon");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanPaidInFull),"Loan paid in Full");
		loan.transactionDetails(String.valueOf(Math.round(Double.parseDouble(loan.repaymentAmountCalculation(loanType,amount,tenure,interest,false)))), loan.getAccountNumber("63"+mobileNumber),propertyFileReader("LoanMoneyCreditedFrom1","QuickLoanWithVas"),"QuickLoanWithVas");
		logger.info("TDB-QLV-111, Verify if user can make full repayment with sufficient TSA balance validated");
		extentLoggerPass("TDB-QLV-111","TDB-QLV-111, Verify if user can make full repayment with sufficient TSA balance validated");
	}

	/**
	 * Verify the Loans tile status after the full loan repayment
	 * @throws Exception
	 */
	public void verifyLoansTileStatusAfterFullLoanRepayment_TDB_QLV_113() throws Exception {
		HeaderChildNode("TDB-QLV-113, Verify the Loans tile status after the full loan repayment");
		tonikLogin(propertyFileReader("password", "Login"));
		scrollToVertical(propertyFileReader("loanTile", "QuickLoanWithVas"));
		swipe("UP", 1);
		valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNewLoanTileHeader)), propertyFileReader("newLoanTileHeader", "QuickLoanWithVas"), "Loan Tile header", "contains");
		valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile)), propertyFileReader("loanTile", "QuickLoanWithVas"), "Loan Tile", "contains");
		valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNewLoanTileSubHeader)), propertyFileReader("newloanTileSubHeader", "QuickLoanWithVas"),"Loan Tile", "contains");
		if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNewLoanTileHeader), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNewLoanTileHeader), "Tile Header"))) {
			logger.info("TDB-QLV-113, Verify the Loans tile status after the full loan repayment validated");
			extentLoggerPass("TDB-QLV-113", "TDB-QLV-113, Verify the Loans tile status after the full loan repayment validated");
		}
	}

	/**
	 * Verify if user can reapply Quick loan after the full loan repayment
	 * @throws Exception
	 */
	public void verifyUserCanReapplyQuickLoanAfterFullLoanRepayment_TDB_QLV_114() throws Exception {
		HeaderChildNode("TDB-QLV-114, Verify if user can reapply Quick loan after the full loan repayment");
		tonikLogin(propertyFileReader("password", "Login"));
		scrollToVertical(propertyFileReader("loanTile", "QuickLoanWithVas"));
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loan Tile");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCreditBuilderTile), "Credit Builder Tile");
		loan.readySetBoostPageValidation();
		loan.callMeMayBePageValidation();
		loan.nowWithPayHingaPageValidation();
		loan.howMuchDoYouNeedPage();
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueBtn), "Continue Button");
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanCalculatorPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanCalculatorPage), "Page"))) {
			valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYourInstallmentTxt)), propertyFileReader("chooseInstallmentTxt", "QuickLoanWithVas"), "Page Text", "contains");
			logger.info("TDB-QLV-114, Verify if user can reapply Quick loan after the full loan repayment validated");
			extentLoggerPass("TDB-QLV-114", "TDB-QLV-114, Verify if user can reapply Quick loan after the full loan repayment validated");
		}
	}

	/**
	 * Verify if BKYC user can apply Quick Loan if TSA is not created (SKYC upgrade failed)
	 * @param amount
	 * @throws Exception
	 */
	public void bkycUserCanApplyQuickLoanIfTSAIsNotCreatedValidation_TDB_QLV_118(String amount) throws Exception {
		HeaderChildNode("TDB_QLV_118, Quick Loan With VAS - Verify if BKYC user can apply Quick Loan if TSA is not created (SKYC upgrade failed)");
		tonikLogin(propertyFileReader("password", "Login"));
		loan.chooseInstallmentPageNavigation(amount);
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn),5,"Continue With PayHing Btn");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), "Button"));
		loan.verifyOTPPopup(propertyFileReader("BKYCMobileNumber","TestDataNumbers"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn),5,"I want To be Protected Btn");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), "Button"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn),5,"Sweet I accept button");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn),": button"));
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubHeader),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubHeader),": popup header"))){
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubHeader)),propertyFileReader("UpdateIdPopup","QuickLoanWithVas"),": popup header");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYesVerifyMyID)),propertyFileReader("UpdateIdPopupBtn","QuickLoanWithVas"),": button");
			logger.info("TDB_QLV_118, Quick Loan With VAS - BKYC user can't apply Quick Loan if TSA is not created validated");
			extentLoggerPass("TDB_QLV_118", "TDB_QLV_118, Quick Loan With VAS - BKYC user can't apply Quick Loan if TSA is not created validated");
		}
	}

	/**
	 * Verify if user can quit the loan application
	 * @param amount
	 * @throws Exception
	 */
	public void userCanQuitLoanApplicationValidation_TDB_QLV_121(String amount,String mobileNumber) throws Exception {
		HeaderChildNode("TDB-QLV-121, Quick Loan With VAS - Verify if user can quit the loan application");
		tonikLogin(propertyFileReader("password", "Login"));
		loan.chooseInstallmentPageNavigation(amount);
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn),5,"Continue With PayHing Btn");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), "Button"));
		loan.verifyOTPPopup(mobileNumber);
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn),5,"I want To be Protected Btn");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), "Button"));
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), "Button"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLeaveIcon),5,"Leave Icon");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLeaveIcon),"Leave Icon");
		click(QuickLoanWithVasPage.objLeavingSoonReason(platform,"I have other reasons"),getTextVal(QuickLoanWithVasPage.objLeavingSoonReason(platform,"I have other reasons"),": reason"));
		verifyElementPresentAndClick(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),getTextVal(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),": button"));
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objGoodByeScreen),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objGoodByeScreen),": Screen"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objGoodByeScreen)),propertyFileReader("GoodByeScreen","QuickLoanWithVas"),"Screen");
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objGoodByeInfo)),propertyFileReader("GoodByeInfo","QuickLoanWithVas"),"Info");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashboard),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashboard),": button"));
		verticalSwipeTillElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile));
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),"tile"))){
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansStatusAfterLeaveApplication)),propertyFileReader("LoansStatusAfterLeaveApplication","QuickLoanWithVas"),"Status");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanTileInfoAfterLeaveApplication)),propertyFileReader("LoanTileInfoAfterLeaveApplication","QuickLoanWithVas"),"Status");
			logger.info("TDB-QLV-121, Quick Loan With VAS - User can quit the loan application validated");
			extentLoggerPass("TDB-QLV-121", "TDB-QLV-121, Quick Loan With VAS - User can quit the loan application validated");
		}
	}

	/**
	 * Verify if SKYC user's ID expires in 45 days
	 * @param amount
	 * @throws Exception
	 */
	public void verifySKYCUserIDExpiresIn45Days_TDB_QLV_122(String amount) throws Exception {
		HeaderChildNode("TDB_QLV_122, Quick Loan With VAS - Verify if SKYC user's ID expires in 45 days");
		loan.modifyDate("63"+propertyFileReader("ExpiredIDMobileNumber","QuickLoanWithVas"),45,"yyyy-MM-dd");
		tonikLogin(propertyFileReader("ExpiredIDPassword", "QuickLoanWithVas"));
		loan.chooseInstallmentPageNavigation(amount);
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn),2,"Continue with PayHinga");
		if(verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanCalculatorPage))) {
			click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), "Button"));
			loan.verifyOTPPopup(propertyFileReader("ExpiredIDMobileNumber","QuickLoanWithVas"));
			waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), 5, "I want To be Protected Btn");
			click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), "Button"));
			waitTime(4000);
		}
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn),5,"Sweet I accept button");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), "Button"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubHeader),3,"popup SubHeader");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubHeader),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubHeader),"Popup SubHeader"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubHeader)),propertyFileReader("UploadIDPopup","QuickLoanWithVas"),"Popup SubHeader");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUploadNewId),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUploadNewId),"Button"));
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objStepsToVerifyScreen),"Screen"))){
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("StepsToVerifyScreen","QuickLoanWithVas"),"Title");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSteps1ToVerify)), propertyFileReader("Steps1ToVerify","QuickLoanWithVas"), "Step 1 to Verify");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSteps2ToVerify)), propertyFileReader("Steps2ToVerify","QuickLoanWithVas"), "Step 2 to Verify");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueButton)),propertyFileReader("ContinueBtn","QuickLoanWithVas"),"Button");
			logger.info("TDB_QLV_122, Quick Loan With VAS - If SKYC user's ID expires in 45 days, app navigates to upload ID popup and Steps to verify account screen validated");
			extentLoggerPass("TDB_QLV_122", "TDB_QLV_122, Quick Loan With VAS - If SKYC user's ID expires in 45 days, app navigates to upload ID popup and Steps to verify account screen validated");
		}
	}

	/**
	 * Verify if SKYC user's ID is already expired
	 * @param amount
	 * @throws Exception
	 */
	public void verifySKYCUserIDAlreadyExpired_TDB_QLV_123(String amount) throws Exception {
		HeaderChildNode("TDB_QLV_123, Quick Loan With VAS - Verify if SKYC user's ID is already expired");
		loan.modifyDate("63"+propertyFileReader("ExpiredIDMobileNumber","QuickLoanWithVas"),(-1),"yyyy-MM-dd");
		tonikLogin(propertyFileReader("ExpiredIDPassword", "QuickLoanWithVas"));
		loan.chooseInstallmentPageNavigation(amount);
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn),2,"Continue with PayHinga");
		if(verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanCalculatorPage))) {
			click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), "Button"));
			loan.verifyOTPPopup(propertyFileReader("ExpiredIDMobileNumber","QuickLoanWithVas"));
			waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), 5, "I want To be Protected Btn");
			click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), "Button"));
			waitTime(4000);
		}
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn),5,"Sweet I accept button");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), "Button"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubHeader),3,"popup SubHeader");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubHeader),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubHeader),"Popup SubHeader"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubHeader)).replaceAll("’",""),propertyFileReader("ExpiredIDPopup","QuickLoanWithVas"),"Popup SubHeader");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUploadNewId),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUploadNewId),"Button"));
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objStepsToVerifyScreen),"Screen"))){
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("StepsToVerifyScreen","QuickLoanWithVas"),"Title");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSteps1ToVerify)), propertyFileReader("Steps1ToVerify","QuickLoanWithVas"), "Step 1 to Verify");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSteps2ToVerify)), propertyFileReader("Steps2ToVerify","QuickLoanWithVas"), "Step 2 to Verify");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueButton)),propertyFileReader("ContinueBtn","QuickLoanWithVas"),"Button");
			logger.info("TDB_QLV_123, Quick Loan With VAS - If SKYC user's ID is already expired, app navigates to upload ID popup and Steps to verify account screen validated");
			extentLoggerPass("TDB_QLV_123", "TDB_QLV_123, Quick Loan With VAS - If SKYC user's ID is already expired, app navigates to upload ID popup and Steps to verify account screen validated");
		}
	}

	/**
	 * Verify if SKYC user can apply Quick Loan if TSA is created already
	 * @param mobileNo
	 * @param quickAmount
	 * @throws Exception
	 */
	public void verifySKYCUserCanApplyQuickLoanTSACreatedAlready_TDB_QLV_120(String mobileNo, String quickAmount,String tenure,String interest) throws Exception {
		HeaderChildNode("TDB-QLV-120, Verify if SKYC user can apply Quick Loan if TSA is created already");
		tonikLogin(propertyFileReader("password", "Login"));
		loan.mainLoanTileValidationLatest();
		waitTime(2000);
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
		loan.needFastCashPageValidation();
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCreditBuilderTile), "Credit Builder Tile");
		waitTime(2000);
		loan.readySetBoostPageValidation();
		loan.callMeMayBePageValidation();
		loan.nowWithPayHingaPageValidation();
		loan.howMuchDoYouNeedPage();
		clearField(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAmount), "Amount Field");
		type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAmount), quickAmount,"Amount Field");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueBtn), "Continue Button");
		waitTime(3000);
		valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYourInstallmentTxt)), propertyFileReader("chooseInstallmentTxt", "QuickLoanWithVas"), "Page Text", "contains");
		click(FlexPivotPage.objSelectLoanTenure(platform,tenure),getTextVal(FlexPivotPage.objSelectLoanTenure(platform,tenure),"Tenure"));
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), "Button"));
		waitTime(2000);
		loan.verifyOTPPopup(mobileNo);
		waitTime(3000);
		loan.nooicePageValidation();
		waitTime(3000);
		loan.protectYourselfPageValidation();
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), "Button"));
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHereisSummaryTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHereisSummaryTxt), "Page"));
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), "Sweet! I accept");
		loan.loanSegment2Navigation(loan.generateRandomTINNUMBER(),propertyFileReader("ValidIncomeAmount","QuickLoanWithVas"),
				propertyFileReader("ReferenceNumber1", "QuickLoanWithVas"),propertyFileReader("ReferenceNumber2", "QuickLoanWithVas"));
		waitTime(2000);
		loan.holdOnBabeScreenValidation();
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInprogressStatus),80,"In Progress Tile");
		waitTime(3000);
		loan.inProgressTileValidation();
		waitTime(20000);
		loan.approveLoanStatus(mobileNo);
		waitTime(5000);
		if(platform.equalsIgnoreCase("ios")){
			closeiOSTonikApp();
		} else{
			closeAndroidTonikApp();
		}
		waitTime(3000);
		relaunchApp(platform);
		tonikLogin(propertyFileReader("password", "Login"));
		loan.loanApprovedTileValidation();
//		// end of part 1
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),"Tile");
		loan.wootYouGotApprovedPageValidation();
		waitTime(2000);
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAcceptOfferBtn),"I Accept Offer Button");
		waitTime(3000);
		loan.tellUsYourPayDayPageValidation();
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), "Next Button");
		loan.monthlyInstallmentSummaryPageValidation(propertyFileReader("WithVAS","QuickLoanWithVas"),loan.extractedDate(), propertyFileReader("monthlyPageSubHeader", "QuickLoanWithVas"));
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objConfirmBtn), "Confirm Button");
		loan.signedSealedDeliveredPageValidation();
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignPromissoryNoteBtn), "Sign Promissory Note Button");
		waitTime(20000);
		loan.promissoryNotePageValidation();
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign");
		loan.yourSignatureRequiredPageValidation();
		waitTime(6000);
    	updateSignature();
		isButtonEnabled(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignPromissoryNoteBtn), "Sign Promissory Note Button");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignPromissoryNoteBtn), "Sign Promissory Note Button");
		waitTime(8000);
		loan.disclosureStatementPageValidation();
		waitTime(6000);
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign");
		waitTime(3000);
		updateSignature();
		isButtonEnabled(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignDisclosureStatementBtn), "Sign Disclosure Statement Button");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignDisclosureStatementBtn), "Sign Disclosure Statement Button");
		waitTime(10000);
		loan.amortizationScheduleValidation();
		waitTime(3000);
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign Button");
		waitTime(3000);
		loan.yourSignatureRequiredPageValidation();
		updateSignature();
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignAmortizationScheduleBtn), "Sign Amortization Schedule Button");
		waitTime(2000);
		loan.yourLoanAlmostReadyPageValidation();
		waitTime(4000); // it takes time to load popup
		loan.iWillDoItLaterPopup();
		// end of part 2
		loan.yesYesYesTileValidation();
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),"Tile");
		waitTime(2000);
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoButton), "Information Button");
		loan.loanInformationScreen(propertyFileReader("WithVAS","QuickLoanWithVas"),quickAmount,tenure,interest);
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoScreeRateAndFees), "Rates and fees");
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), 10, "Rates And Fees Page");
		loan.ratesAndFeesValidation(quickAmount, tenure,interest,"WithVAS");
		waitTime(3000);
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
		swipe("UP", 1);
		if(platform.equalsIgnoreCase("ios")){
			List<IOSElement> values = DriverManager.getAppiumDriver().findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansDocuments));
			for (int document = 1; document < values.size(); document++) {
				assertionValidation(getText(QuickLoanWithVasPage.objLoansDocuments(platform, document)), propertyFileReader("LoanDocument" + document, "QuickLoanWithVas"), "Loan document");
				click(QuickLoanWithVasPage.objLoansDocuments(platform, document), getTextVal(QuickLoanWithVasPage.objLoansDocuments(platform, document), "Document"));
				waitTime(5000);
				verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"));
				assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("LoanDocument" + document, "QuickLoanWithVas"), ": Loan document");
				isButtonEnabled(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), ": button"));
				click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), ": button"));
				waitTime(3000);
				click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCopyButtonIOS), "Copy Button");
				waitTime(1000);
				click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCloseIcon), "Close Icon");
				waitTime(3000);
				click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back button");
			}
		} else {
			List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansDocuments));
			for (int document = 1; document < values.size(); document++) {
				assertionValidation(getText(QuickLoanWithVasPage.objLoansDocuments(platform, document)), propertyFileReader("LoanDocument" + document, "QuickLoanWithVas"), "Loan document");
				click(QuickLoanWithVasPage.objLoansDocuments(platform, document), getTextVal(QuickLoanWithVasPage.objLoansDocuments(platform, document), "Document"));
				waitTime(5000);
				verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"));
				assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("LoanDocument" + document, "QuickLoanWithVas"), ": Loan document");
				isButtonEnabled(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), ": button"));
				click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), ": button"));
				click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back button");
			}
		}
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back button");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLearnMoreBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLearnMoreBtn), "Button"));
		waitTime(3000);
		if(platform.equalsIgnoreCase("ios")){
			List<IOSElement> values = DriverManager.getAppiumDriver().findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTileSubHeader));
			for (int tile = 1; tile < values.size(); tile++) {
				assertionValidation(getText(QuickLoanWithVasPage.objTileSubHeader(platform, tile)),propertyFileReader("tile"+tile,"QuickLoanWithVas"),": Tile");
				containsValidation(getText(QuickLoanWithVasPage.objTileInfo(platform, tile)),propertyFileReader("tileInfo"+tile,"QuickLoanWithVas"),": Tile Info");
			}
		} else {
			List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTileSubHeader));
			for (int tile = 1; tile < values.size(); tile++) {
				assertionValidation(getText(QuickLoanWithVasPage.objTileSubHeader(platform, tile)),propertyFileReader("tile"+tile,"QuickLoanWithVas"),": Tile");
				containsValidation(getText(QuickLoanWithVasPage.objTileInfo(platform, tile)),propertyFileReader("tileInfo"+tile,"QuickLoanWithVas"),": Tile Info");
			}
		}
		waitTime(2000);
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back Button");
		waitTime(2000);
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCoveredBtn), "Button");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt),"Page Header"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("proofOfCover", "QuickLoanWithVas"), "Page Header");
		waitTime(5000);
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back button");
		waitTime(2000);
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back button");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceOnScreen), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceOnScreen),"TSA Balance"));
		verifyElementPresentAndClick(OnBoardingPage.objHistoryIcon,"History Icon");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("AccountHistory", "QuickLoanWithVas"), ": page title");
		verifyElementPresent(QuickLoanWithVasPage.objTransaction(platform, "Processing Fee"),getTextVal(QuickLoanWithVasPage.objTransaction(platform, "Processing Fee"),": transaction"));
		verifyElementPresent(QuickLoanWithVasPage.objTransaction(platform, "DST Fee"),getTextVal(QuickLoanWithVasPage.objTransaction(platform, "DST Fee"),": transaction"));
		verifyElementPresent(QuickLoanWithVasPage.objTransaction(platform, "Money Credited"),getTextVal(QuickLoanWithVasPage.objTransaction(platform, "Money Credited"),": transaction"));
		waitTime(3000);
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back button");
		logger.info("TDB-QLV-120, Verify if SKYC user can apply Quick Loan if TSA is created already validated");
		extentLoggerPass("TDB_QLV_120", "TDB-QLV-120, Verify if SKYC user can apply Quick Loan if TSA is created already validated");
	}

	/**
	 * Verify if user can make the payment via Tonik Account with sufficient balance
	 * @param dueDate
	 * @throws Exception
	 */
	public void verifyUserCanMakePaymentViaTonikAccount_TDB_QLV_041(int dueDate,String mobileNumber,String loanType) throws Exception {
		HeaderChildNode("TDB_QLV_041, Quick Loan With VAS - Verify if user can make the payment via Tonik Account with sufficient balance");
		String userId = loan.getUserId("63" + mobileNumber);
		String loanAccountNumber = loan.getLoanAccountNumber(userId);
		String date = loan.returnDate(dueDate, "yyyy-MM-dd");
		loan.updateDueDateInLoanInstallmentDetails(date, loanAccountNumber);
		logger.info("Due date modified in Loan Installment details: " + date);
		extentLogger("", "Due date modified in Loan Installment details: " + date);
		loan.updateRepaymentDateInLoanRepaymentSchedule(date, loanAccountNumber, "1");
		tonikLogin(propertyFileReader("password", "Login"));
		String balance = getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceOnScreen));
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPayNowBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPayNowBtn), "Button"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanPaymentScreen), 5, "Loan payment screen");
		String amount = getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAmountToBePaid));
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objConfirmPaymentBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objConfirmPaymentBtn),"Button"));
		waitTime(4000);
		containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanPaymentReceivedScreen)),propertyFileReader("LoanPaymentReceived","QuickLoanWithVas"),"Screen");
		containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanPaymentReceivedInfo)),amount.replaceAll("₱",""),"Info");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objViewTransactionLink),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objViewTransactionLink),"Link"));
		if(amount.contains("₱")) {
			amount=loan.removeSpecialCharacter(amount, false);
		}
		loan.transactionDetailsLoanRepayment(amount,loan.getAccountNumber("63"+mobileNumber),"");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashboard),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashboard),"button"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYourLoanAccount)),propertyFileReader("YourLoanAccount","QuickLoanWithVas"),"Loan text");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanBlueTick),"Loan blue tick");
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanTileAfter1stRepayment)),propertyFileReader("LoanTileAfter1stRepayment","QuickLoanWithVas"),"Loan tile");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextPaymentDate),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextPaymentDate),""));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanTileMsg)),propertyFileReader("LoanTileMsg","QuickLoanWithVas"),"Loan tile");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanTileAfter1stRepayment),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanTileAfter1stRepayment),"Tile"));
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextInstallment),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextInstallment),""));
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextDueDate),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextDueDate),""));
		String principalAmount = loan.getLoanRepaymentDetails("repaymentAmount", loanAccountNumber, "2");
		containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextRepaymentAmount)).replaceAll("₱","").replaceAll(",",""),principalAmount,"2nd month Installment amount");
		containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSubMessage)).replaceAll("₱",""),propertyFileReader("NextInstallmentMsg","QuickLoanWithVas"),"Sub message");
		containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPaymentDone)),propertyFileReader("1stPaymentDoneMSg","QuickLoanWithVas"),"");
		if(loanType.equalsIgnoreCase("WithVAS")){
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPayHingaTitle)),propertyFileReader("PayHingaTitle","QuickLoanWithVas"),"Tile");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPayHingaSubtitle)),propertyFileReader("PendingInstallment","QuickLoanWithVas"),"Installment");
		}
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
		sendMoneyModule.navigateToAccountHistory();
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanPaymentTransaction),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanPaymentTransaction),"transaction"));
		loan.transactionDetailsLoanRepayment(amount,loan.getAccountNumber("63"+mobileNumber),"");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": title"))) {
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("AccountHistory", "QuickLoanWithVas"), ": page title");
			logger.info("TDB_QLV_041, Quick Loan With VAS - User can make the payment via Tonik Account with sufficient balance validated");
			extentLoggerPass("TDB_QLV_041", "TDB_QLV_041, Quick Loan With VAS - User can make the payment via Tonik Account with sufficient balance validated");
		}
	}
	/**
	 * Verify the user can activate PayHinga if loan has no outstanding dues- 6 months tenure
	 * @param firstMonthDueDate
	 * @param secondMonthDueDate
	 * @throws Exception
	 */
	public void verifyUserCanActivatePayHingaWithNoOutStandingDue_TDB_QLV_060(int firstMonthDueDate,int secondMonthDueDate,String mobileNumber,String loanAmount,String tenure,String interest) throws Exception {
		HeaderChildNode("TDB_QLV_060, Quick Loan With VAS - Verify the user can activate PayHinga if loan has no outstanding dues- 6 months tenure");
		String userId = loan.getUserId("63" + mobileNumber);
		String loanAccountNumber = loan.getLoanAccountNumber(userId);
		String date = loan.returnDate(firstMonthDueDate, "yyyy-MM-dd");
		loan.updateDueDateInLoanInstallmentDetails(date, loanAccountNumber);
		logger.info("Due date modified in Loan Installment details: " + date);
		extentLogger("", "Due date modified in Loan Installment details: " + date);
		loan.updateRepaymentDateInLoanRepaymentSchedule(date, loanAccountNumber, "1");
		String date2 = loan.returnDate(secondMonthDueDate, "yyyy-MM-dd");
		logger.info("Due date modified in Loan Installment details: " + date);
		extentLogger("", "Due date modified in Loan Installment details: " + date);
		loan.updateRepaymentDateInLoanRepaymentSchedule(date2, loanAccountNumber, "2");
		loan.updateSweepStatus(loanAccountNumber,"1");
		loan.updateSweepStatus(loanAccountNumber,"2");
		String digitalLoanId=loan.getDigitalLoanId(loanAccountNumber);
		loan.updateNormalPaidPayments(digitalLoanId,"3");
		loan.updateEligiblePaidHolidays(digitalLoanId,"1");
		tonikLogin(propertyFileReader("password", "Login"));
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),"Tile"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPayHingaSubtitle)),propertyFileReader("PayHingaAvailable","QuickLoanWithVas"),"Subtitle");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objActivateBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objActivateBtn),"Button"));
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objWantToActivatePayHingaScreen),5,"Are you sure you want to activate Payhinga? Screen");
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("WantToActivatePayHingaScreen","QuickLoanWithVas"),"Screen");
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)),propertyFileReader("WantToActivatePayHingaSubtitle","QuickLoanWithVas"),"Screen");
		if(platform.equalsIgnoreCase("ios")){
			List<IOSElement> values = DriverManager.getAppiumDriver().findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReasonForPayHinga));
			for (int reason = 1; reason <= values.size(); reason++) {
				assertionValidation(getText(QuickLoanWithVasPage.objReasonForPayHinga(platform, reason)),propertyFileReader("ReasonForPayHinga"+reason,"QuickLoanWithVas"),": Reason");
			}
		} else {
			List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReasonForPayHinga));
			for (int reason = 1; reason <= values.size(); reason++) {
				assertionValidation(getText(QuickLoanWithVasPage.objReasonForPayHinga(platform, reason)),propertyFileReader("ReasonForPayHinga"+reason,"QuickLoanWithVas"),": Reason");
			}
		}
		click(QuickLoanWithVasPage.objReasonForPayHinga(platform, 2),getTextVal(QuickLoanWithVasPage.objReasonForPayHinga(platform, 2),"Reason"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMayBeLaterBtn)),propertyFileReader("MaybeLaterBtn","QuickLoanWithVas"),"button");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYesImSureBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYesImSureBtn),"Button"));
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPayHingaConfirmationMsg),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPayHingaConfirmationMsg),"Message"));
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objActivateNow),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objActivateNow),"Button"));
		waitTime(5000);
		containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objApprovedPayHingaMsg)),propertyFileReader("ApprovedPayHingaMsg","QuickLoanWithVas"),"message");
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashboard),5,"Back to dashboard button");
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashboard),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashboard),"Button"));
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objQuickLoanPayHinga),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objQuickLoanPayHinga),"Tile"));
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPayHingaSubtitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPayHingaSubtitle),"Tile"));
		if(platform.equalsIgnoreCase("ios")){
			List<IOSElement> values2 = DriverManager.getAppiumDriver().findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTileSubHeader));
			for (int tile = 1; tile < values2.size(); tile++) {
				assertionValidation(getText(QuickLoanWithVasPage.objTileSubHeader(platform, tile)),propertyFileReader("tile"+tile,"QuickLoanWithVas"),": Tile");
				containsValidation(getText(QuickLoanWithVasPage.objTileInfo(platform, tile)),propertyFileReader("tileInfo"+tile,"QuickLoanWithVas"),": Tile Info");
			}
		} else{
			List<WebElement> values2 = findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTileSubHeader));
			for (int tile = 1; tile < values2.size(); tile++) {
				assertionValidation(getText(QuickLoanWithVasPage.objTileSubHeader(platform, tile)),propertyFileReader("tile"+tile,"QuickLoanWithVas"),": Tile");
				containsValidation(getText(QuickLoanWithVasPage.objTileInfo(platform, tile)),propertyFileReader("tileInfo"+tile,"QuickLoanWithVas"),": Tile Info");
			}
		}
		if(platform.equalsIgnoreCase("ios")){
			verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadOurFAQBtn),"Read our FAQs to learn more Button");
		}
		else {
			click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadOurFAQBtn), "Read our FAQs to learn more Button");
			waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objWebView), 3, "Switching to Webview");
			verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objWebView),"Web page");
			DriverManager.getAppiumDriver().navigate().back();
		}
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMunaPageHowItWorks), "How it works Button");
		if(platform.equalsIgnoreCase("ios")){
			List<IOSElement> values2 = DriverManager.getAppiumDriver().findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTileSubHeader));
			for (int tile = 1; tile < values2.size(); tile++) {
				assertionValidation(getText(QuickLoanWithVasPage.objTileSubHeader(platform, tile)),propertyFileReader("howItWorksTile"+tile,"QuickLoanWithVas"),": Tile");
				containsValidation(getText(QuickLoanWithVasPage.objTileInfo(platform, tile)),propertyFileReader("howItWorksInfo"+tile,"QuickLoanWithVas"),": Tile Info");
			}
		} else {
			List<WebElement> values2 = findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTileSubHeader));
			for (int tile = 1; tile < values2.size(); tile++) {
				assertionValidation(getText(QuickLoanWithVasPage.objTileSubHeader(platform, tile)),propertyFileReader("howItWorksTile"+tile,"QuickLoanWithVas"),": Tile");
				containsValidation(getText(QuickLoanWithVasPage.objTileInfo(platform, tile)),propertyFileReader("howItWorksInfo"+tile,"QuickLoanWithVas"),": Tile Info");
			}
		}
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objGotItButton), "Got It Button");
		waitTime(2000);
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoButton), "Information Button");
		loan.loanInformationScreen(propertyFileReader("WithVAS","QuickLoanWithVas"),loanAmount,tenure,interest);
		waitTime(2000);
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoScreenProofOfCoverTxt),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoScreenProofOfCoverTxt),"Option"));
		waitTime(3000);
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("proofOfCoverTxt", "QuickLoanWithVas"), "Text");
		waitTime(7000);
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt),"Page Header"))){
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("loanInformation", "QuickLoanWithVas"), "Page Header");
			logger.info("TDB_QLV_060, Quick Loan With VAS - User can activate PayHinga if loan has no outstanding dues- 6 months tenure validated");
			extentLoggerPass("TDB_QLV_060", "TDB_QLV_060, Quick Loan With VAS - User can activate PayHinga if loan has no outstanding dues- 6 months tenure validated");
		}
	}
	/**
	 * Verify the Loan segmentation for the entire Quick Loan VAS journey
	 * @throws Exception
	 */
	public void loanSegmentationForEntireQuickLoanVASJourney_TDB_QLV_127() throws Exception {
		HeaderChildNode("TDB_QLV_127, Quick Loan With VAS - Verify the Loan segmentation for the entire Quick Loan VAS journey");
		tonikLogin(propertyFileReader("password", "Login"));
		loan.inProgressTileValidation();
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), ": Tile"));
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objWhatDoYouNeedItForPage),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objWhatDoYouNeedItForPage),": page"));
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), ": Tile"));
		loan.leavingSoonPageValidation();
		loan.whatDoYouNeedItForScreen("Education");
		waitTime(3000);
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("FieldOfWorkPage","QuickLoanWithVas"),": page title");
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)),propertyFileReader("FieldOfWorkPageSubtitle","QuickLoanWithVas"),": page subtitle");
		if(platform.equalsIgnoreCase("ios")){
			closeiOSTonikApp();
		} else{
			closeAndroidTonikApp();
		}
		relaunchApp(platform);
		tonikLogin(propertyFileReader("password", "Login"));
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), ": Tile"));
		if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"))) {
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("WhatDoYouNeedItForPage", "QuickLoanWithVas"), ": page title");
			assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("WhatDoYouNeedItForPageSubTitle", "QuickLoanWithVas"), ": page subtitle");
			logger.info("TDB_QLV_127, Quick Loan With VAS - Loan segmentation for the entire Quick Loan VAS journey validated");
			extentLoggerPass("TDB_QLV_127", "TDB_QLV_127, Quick Loan With VAS - Loan segmentation for the entire Quick Loan VAS journey validated");
		}
	}

	/**
	 * Verify the Loans tile if the user didn't accept the Loan offer in 30 days
	 * @param mobileNo
	 * @param quickAmount
	 * @throws Exception
	 */
	public void verifyLoansTileIfUserNotAcceptLoanOfferIn30Days_TDB_QLV_128(String mobileNo, String quickAmount) throws Exception {
		HeaderChildNode("TDB-QLV-128,  Quick Loan With VAS - Verify the Loans tile if the user didn't accept the Loan offer in 30 days ");
		tonikLogin(propertyFileReader("password", "Login"));
		loan.mainLoanTileValidationLatest();
		waitTime(2000);
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
		loan.needFastCashPageValidation();
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCreditBuilderTile), "Credit Builder Tile");
		waitTime(2000);
		loan.readySetBoostPageValidation();
		loan.callMeMayBePageValidation();
		loan.nowWithPayHingaPageValidation();
		loan.howMuchDoYouNeedPage();
		clearField(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAmount), "Amount Field");
		type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAmount), quickAmount, "Amount Field");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueBtn), "Continue Button");
		waitTime(3000);
		valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYourInstallmentTxt)), propertyFileReader("chooseInstallmentTxt", "QuickLoanWithVas"), "Page Text", "contains");
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), "Button"));
		waitTime(2000);
		loan.verifyOTPPopup(mobileNo);
		waitTime(3000);
		loan.nooicePageValidation();
		waitTime(3000);
		loan.protectYourselfPageValidation();
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), "Button"));
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHereisSummaryTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHereisSummaryTxt), "Page"));
		waitTime(3000);
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), "Sweet! I accept");
		loan.loanSegment2Navigation(loan.generateRandomTINNUMBER(),propertyFileReader("ValidIncomeAmount","QuickLoanWithVas"),
				propertyFileReader("ReferenceNumber1", "QuickLoanWithVas"), propertyFileReader("ReferenceNumber2", "QuickLoanWithVas"));
		waitTime(2000);
		loan.holdOnBabeScreenValidation();
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInprogressStatus), 80, "Inprogress");
		waitTime(3000);
		loan.inProgressTileValidation();
		loan.approveLoanStatus(mobileNo);
		waitTime(3000);
		if(platform.equalsIgnoreCase("ios")){
			closeiOSTonikApp();
		} else{
			closeAndroidTonikApp();
		}
		waitTime(2000);
		relaunchApp(platform);
		tonikLogin(propertyFileReader("password", "Login"));
		loan.loanApprovedTileValidation();
		click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),"Tile");
		loan.wootYouGotApprovedPageValidation();
		waitTime(5000);
		String userId = loan.getUserId("63"+mobileNo);
		String date = loan.returnDate(-35,"yyyy-MM-dd");
		loan.updatePoi3VerificationDateAndTime(date+" "+loan.getCurrentTime(),userId);
		loan.updateUpdatedTimeStamp(date+" "+loan.getCurrentTime(),userId);
		switchPlatformToWeb("https://uat.alb.tonikbank.com/loans/v1/loanstatusupdate");
		switchBackToApp();
		waitTime(5000);
		if(platform.equalsIgnoreCase("ios")){
			closeiOSTonikApp();
		} else{
			closeAndroidTonikApp();
		}
		waitTime(3000);
		relaunchApp(platform);
		tonikLogin(propertyFileReader("password", "Login"));
		loan.mainLoanTileValidationLatest();
		logger.info("TDB-QLV-128,  Quick Loan With VAS - Verify the Loans tile if the user didn't accept the Loan offer in 30 days");
		extentLoggerPass("TDB_QLV_128", "TDB-QLV-128,  Quick Loan With VAS - Verify the Loans tile if the user didn't accept the Loan offer in 30 days");
	}
}