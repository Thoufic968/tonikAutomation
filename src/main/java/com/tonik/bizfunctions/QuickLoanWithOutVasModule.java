package com.tonik.bizfunctions;

import com.driverInstance.DriverManager;
import com.tonik.pageObject.*;
import com.tonik.utility.Utilities;
import org.openqa.selenium.WebElement;
import java.util.List;
import static com.tonik.utility.ExtentReporter.HeaderChildNode;
import static com.tonik.utility.Utilities.*;

public class QuickLoanWithOutVasModule extends BaseClass{
    public QuickLoanWithOutVasModule() {
        super();
    }
    LoanCommonMethods loan = new LoanCommonMethods();
    String platform = Utilities.getPlatform();
    SendMoneyModule sendMoneyModule = new SendMoneyModule();
    /**
     * Common method to you Are Applying For Screen
     * @param amount
     * @throws Exception
     */
    public void youAreApplyingFor(String amount,String interest) throws Exception {
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYouareApplyingTxt)), propertyFileReader("youAreApplyingTxt", "QuickLoanWithVas"), "Page Text", "contains");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTotalLoanAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTotalLoanAmount), "Loan Amount"));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYourInstallmentTxt)), propertyFileReader("chooseInstallmentTxt", "QuickLoanWithVas"), "Page Text", "contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.obActualAmountTxt)), propertyFileReader("actualAmountTxt", "QuickLoanWithVas"),"Page Text", "contains");
        loan.vasEmiVerification(amount, QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanTenure), QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyInstallmentAmount), QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPayhingCalcTxt),Double.parseDouble(interest));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPayHingaTxtOnInsurance)), propertyFileReader("payHingaTxt", "QuickLoanWithVas"),"Text on Pay Hinga Tab", "contains");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPayhingCalcTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPayhingCalcTxt), "Text on Pay Hinga Tab"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPayHingaInformationIcon), "Information icon");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUnprotectedloanBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUnprotectedloanBtn), "Button"));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUnprotectedloanBtn)), propertyFileReader("applyForButtonTxt", "QuickLoanWithVas"), "Button text","contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn)),propertyFileReader("continueWithpayHingaBtn", "QuickLoanWithVas"), "Button text", "contains");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objApplyingLoanPageBackBtn), "Back Button");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactUsBtn1), "Contact us Button");
    }
    /**
     * Common method are you sure popup validation
     * @throws Exception
     */
    public void areYouSurePopup() throws Exception {
        verifyElementPresent(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objAreYouSure),getTextVal(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objAreYouSure),"Popup header"));
        assertionValidation(getText(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objAreYouSure)),propertyFileReader("AreYouSure","QuickLoanWithVas"),"Popup header");
        containsValidation(getText(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objYouAreMissingOut)),propertyFileReader("youAreMissingOut","QuickLoanWithVas"),"popup Sub-header");
        verifyElementPresent(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objClickHere),getTextVal(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objClickHere),"Button"));
        verifyElementPresent(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objNoThanks),getTextVal(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objNoThanks),"Button"));
        verifyElementPresent(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objCountMeIn),getTextVal(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objCountMeIn),"Button"));
    }
    /**
     * Method to Verify if newly onboarded user can access the Quick Loan from the main Dashboard
     * @param amount
     * @throws Exception
     */
    public void onBoardAsNewSKYCUserWithNoTSA_TDB_QL_001(String amount) throws Exception {
        HeaderChildNode("TDB_QL_001, Quick Loan Without VAS - Verify if newly onboarded user can access the Quick Loan from the main Dashboard");
        tonikLogin(propertyFileReader("password","Login"));
        loan.mainLoanTileValidationLatest();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        loan.needFastCashPageValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
        if (verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objProfileName), getTextVal(LoginPage.getByOSType(platform,LoginPage.objProfileName), " : Profile Name"))) {
            logger.info("Succesfully navigated to  Dasshboard screen");
            extentLoggerPass("Payments Page", "Succesfully navigated to  Dasshboard screen");
        }
        scrollToVertical(propertyFileReader("loanTile", "QuickLoanWithVas"));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCreditBuilderTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCreditBuilderTile), "Tile"));
        loan.readySetBoostPageValidation();
        loan.callMeMayBePageValidation();
        loan.nowWithPayHingaPageValidation();
        loan.howMuchDoYouNeedPage();
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAmount),  "Amount Field");
        clearField(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAmount),  "Amount Field");
        type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAmount),amount,"Amount Field");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueBtn), "Continue Button");
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanCalculatorPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanCalculatorPage), "Page"))) {
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYourInstallmentTxt)), propertyFileReader("chooseInstallmentTxt", "QuickLoanWithVas"), "Page Text", "contains");
            logger.info("TDB_QL_001, Quick Loan Without VAS - Newly onboarded user can access the Quick Loan from the main Dashboard validated");
            extentLoggerPass("TDB_QL_001", "TDB_QL_001, Quick Loan Without VAS - Newly onboarded user can access the Quick Loan from the main Dashboard validated");
        }
    }
    /**
     * Method to Verify if user can accept the Quick Loan with VAS offer from the 'Are you sure?' pop up
     * @param amount
     * @throws Exception
     */
    public void userCanAcceptQuickLoanWithVASOfferFromAreYouSurePopup_TDB_QL_004(String amount,String tenure,String interest) throws Exception {
        HeaderChildNode("TDB_QL_004, Quick Loan Without VAS - Verify if user can accept the Quick Loan with VAS offer from the 'Are you sure?' pop up");
        tonikLogin(propertyFileReader("password","Login"));
        loan.chooseInstallmentPageNavigation(amount);
        youAreApplyingFor(amount,interest);
        click(FlexPivotPage.objSelectLoanTenure(platform,tenure),getTextVal(FlexPivotPage.objSelectLoanTenure(platform,tenure),"Tenure"));
        loan.installAmount = getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyInstallmentAmount));
        loan.installAmount = loan.removeSpecialCharacter(loan.installAmount, false);
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUnprotectedloanBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUnprotectedloanBtn), "Button"));
        areYouSurePopup();
        click(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objClickHere), getTextVal(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objClickHere), "Button"));
        if (verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn))) {
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), "Button"));
        }
        loan.protectYourselfPageValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn),"Button"));
        loan.summaryPageValidation("WithoutVAS");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), "Button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objWhatDoYouNeedItForPage),5,"What do you need it for screen");
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"))){
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("WhatDoYouNeedItForPage","QuickLoanWithVas"),": page title");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)),propertyFileReader("WhatDoYouNeedItForPageSubTitle","QuickLoanWithVas"),": page subtitle");
            logger.info("TDB_QL_004, Quick Loan Without VAS - User can accept the Quick Loan with VAS offer from the 'Are you sure?' pop up validated");
            extentLoggerPass("TDB_QL_004", "TDB_QL_004, Quick Loan Without VAS - User can accept the Quick Loan with VAS offer from the 'Are you sure?' pop up validated");
        }
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLeaveIcon),"Leave Icon");
        click(QuickLoanWithVasPage.objLeavingSoonReason(platform,"I have other reasons"),getTextVal(QuickLoanWithVasPage.objLeavingSoonReason(platform,"I have other reasons"),": reason"));
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),": button"));
    }
    /**
     * Method to Verify if user can decline the Quick Loan with VAS offer from the 'Are you sure?' pop up
     * @param amount
     * @throws Exception
     */
    public void userCanDeclineQuickLoanWithVASOfferFromAreYouSurePopup_TDB_QL_005(String amount,String tenure,String interest) throws Exception {
        HeaderChildNode("TDB_QL_005, Quick Loan Without VAS - Verify if user can decline the Quick Loan with VAS offer from the 'Are you sure?' pop up");
        tonikLogin(propertyFileReader("password","Login"));
        loan.chooseInstallmentPageNavigation(amount);
        youAreApplyingFor(amount,interest);
        click(FlexPivotPage.objSelectLoanTenure(platform,tenure),getTextVal(FlexPivotPage.objSelectLoanTenure(platform,tenure),"Tenure"));
        loan.installAmount = getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyInstallmentAmount));
        loan.installAmount = loan.removeSpecialCharacter(loan.installAmount, false);
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUnprotectedloanBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUnprotectedloanBtn), "Button"));
        areYouSurePopup();
        click(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objNoThanks),getTextVal(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objNoThanks),"Button"));
        loan.summaryPageValidation("WithoutVAS");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), "Button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objWhatDoYouNeedItForPage),5,"What do you need it for screen");
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"))){
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("WhatDoYouNeedItForPage","QuickLoanWithVas"),": page title");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)),propertyFileReader("WhatDoYouNeedItForPageSubTitle","QuickLoanWithVas"),": page subtitle");
            logger.info("TDB_QL_005, Quick Loan Without VAS - User can decline the Quick Loan with VAS offer from the 'Are you sure?' pop up validated");
            extentLoggerPass("TDB_QL_005", "TDB_QL_005, Quick Loan Without VAS - User can decline the Quick Loan with VAS offer from the 'Are you sure?' pop up validated");
        }
    }
    /**
     * Method to Verify if user can reapply Quick loan after the full loan repayment
     * @throws Exception
     */
    public void verifyUserCanReapplyQuickLoanAfterFullLoanRepayment_TDB_QL_049() throws Exception {
        HeaderChildNode("TDB_QL_049, Quick Loan Without VAS - Verify if user can reapply Quick loan after the full loan repayment");
        tonikLogin(propertyFileReader("password","Login"));
        scrollToVertical(propertyFileReader("loanTile", "QuickLoanWithVas"));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loan Tile");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCreditBuilderTile), "Quick Loan Tile");
        loan.readySetBoostPageValidation();
        loan.callMeMayBePageValidation();
        loan.nowWithPayHingaPageValidation();
        loan.howMuchDoYouNeedPage();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueBtn), "Continue Button");
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanCalculatorPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanCalculatorPage), "Page"))) {
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYourInstallmentTxt)), propertyFileReader("chooseInstallmentTxt", "QuickLoanWithVas"), "Page Text", "contains");
            logger.info("TDB_QL_049, Quick Loan Without VAS - User can reapply Quick loan after the full loan repayment validated");
            extentLoggerPass("TDB_QL_049", "TDB_QL_049, Quick Loan Without VAS - User can reapply Quick loan after the full loan repayment validated");
        }
    }
    /**
     * Method to Verify if SKYC user can apply Quick Loan if TSA is created already
     * @param mobileNo
     * @param quickAmount
     * @throws Exception
     */
    public void verifySKYCUserCanApplyQuickLoanTSACreatedAlready_TDB_QL_055(String mobileNo, String quickAmount,String tenure,String interestRate) throws Exception {
        HeaderChildNode("TDB_QL_055, Quick Loan Without VAS - Verify if SKYC user can apply Quick Loan if TSA is created already");
        tonikLogin(propertyFileReader("password","Login"));
        loan.mainLoanTileValidationLatest();
        waitTime(2000);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
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
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUnprotectedloanBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUnprotectedloanBtn), "Button"));
        areYouSurePopup();
        click(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objNoThanks),getTextVal(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objNoThanks),"Button"));
        waitTime(2000);
        loan.verifyOTPPopup(propertyFileReader("QuickLoanMobileNumber","TestDataNumbers"));
        waitTime(3000);
        loan.nooicePageValidation();
        waitTime(3000);
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHereisSummaryTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHereisSummaryTxt), "Page"));
        waitTime(3000);
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
        click(TopUpPage.getByOSType(platform,TopUpPage.objTopUpIcon),"Top up Icon");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
        loan.loanApprovedTileValidation();
        // end of part 1
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),"Tile");
        loan.wootYouGotApprovedPageValidation();
        waitTime(2000);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAcceptOfferBtn),"I Accept Offer Button");
        waitTime(3000);
        loan.tellUsYourPayDayPageValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), "Next Button");
        loan.monthlyInstallmentSummaryPageValidation("WithoutVAS",loan.extractedDate(), propertyFileReader("monthlyPageSubHeader", "QuickLoanWithVas"));
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
        waitTime(6000);
        loan.disclosureStatementPageValidation();
        waitTime(6000);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign");
        waitTime(6000);
        updateSignature();
        isButtonEnabled(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignDisclosureStatementBtn), "Sign Disclosure Statement Button");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignDisclosureStatementBtn), "Sign Disclosure Statement Button");
        waitTime(5000);
        loan.amortizationScheduleValidation();
        waitTime(6000);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign Button");
        waitTime(6000);
        loan.yourSignatureRequiredPageValidation();
        updateSignature();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignAmortizationScheduleBtn), "Sign Amortization Schedule Button");
        waitTime(6000);
        loan.yourLoanAlmostReadyPageValidation();
        waitTime(4000); // it takes time to load popup
        loan.iWillDoItLaterPopup();
        // end of part 2
        loan.yesYesYesTileValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),"Tile");
        waitTime(2000);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoButton), "Information Button");
        loan.loanInformationScreen("WithoutVAS",quickAmount,tenure,interestRate);
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoScreeRateAndFees), "Rates and fees");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), 10, "Rates And Fees Page");
        loan.ratesAndFeesValidation(quickAmount, tenure, interestRate,"WithoutVAS");
        waitTime(3000);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
        swipe("UP", 1);
        if(platform.equalsIgnoreCase("iOS")){
            List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansDocuments));
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
        loan.insufficientTSA(propertyFileReader("loanAmount", "QuickLoanWithVas"));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),"Loan Tile");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseBtn), "I want to close my loan Button");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTopUpTonicAccountButton),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTopUpTonicAccountButton),"Button"));
        waitTime(5000);
        loan.topUp(propertyFileReader("loanAmount", "QuickLoanWithVas"));
        waitTime(3000);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseLoanButton), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseLoanButton), "Button"));
        waitTime(2000);
        loan.fullRepaymentConfirmationPopup();
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCloseMyLoanButton),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCloseMyLoanButton),"button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDropTheMicPage),30,"Drop the Mic Page");
        waitTime(2000);
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDropTheMicPage)), propertyFileReader("dropMicPageHeader", "QuickLoanWithVas"), "Page Header");
        waitTime(3000);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashBoardButton), "Back to Dashboard Button");
        waitTime(3000);
        swipe("UP",2);
        loan.mainLoanTileValidationLatest();
        logger.info("TDB_QL_055, Verify if SKYC user can apply Quick Loan if TSA is created already validated");
        extentLoggerPass("TDB_QL_055", "TDB_QL_055, Verify if SKYC user can apply Quick Loan if TSA is created already validated");
    }
}