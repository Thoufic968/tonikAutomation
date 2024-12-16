package com.tonik.bizfunctions;

import com.jcraft.jsch.JSchException;
import com.tonik.pageObject.*;
import com.tonik.utility.Utilities;
import org.openqa.selenium.WebElement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import static com.tonik.utility.DB_Utilites.insertQuery;
import static com.tonik.utility.DB_Utilites.selectQuery;
import static com.tonik.utility.ExtentReporter.*;
import static com.tonik.utility.Utilities.*;

public class ReloanModule extends BaseClass {
    public ReloanModule() {
        super();
    }
    public static DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
    LoanCommonMethods loanCommonMethods = new LoanCommonMethods();
    FlexUpModule flexUpModule = new FlexUpModule();
    String platform = Utilities.getPlatform();
    String additionalPayment;
    String monthlyInstallment;
    /**
     * reusable method to verify TONIK to Rescue page
     * @throws Exception
     */
    public void verifyTonikToRescuePage() throws Exception {
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objTonikToRescuePageHeader)).replaceAll("\\s"," "),propertyFileReader("TonikToTheRescue","Reloan")," Page Header");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objTonikToRescueDescription)).replaceAll("\\s"," "),propertyFileReader("TonikToTheRescueDescription","Reloan")," Page Description");
        swipe("UP",1);
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objDeclineTheOfferButton)),propertyFileReader("DeclineTheOffer","Reloan")," Btton");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objTellMeMoreButton)),propertyFileReader("TellMeMore","Reloan")," Page Header");
    }

    /**
     * reusable method to verify approved loan offer
     * @throws Exception
     */
    public void verifyApprovedLoanOfferPage() throws Exception {
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objLoanOfferPageHeader)),propertyFileReader("YouGotLoanOffer","Reloan")," Page Header");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objLoanOfferPageHeaderDescription)).trim(),propertyFileReader("SelectPayment","Reloan").trim()," Page Description");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objChooseYourPayment)),propertyFileReader("ChooseYourterm","Reloan")," Text");
        waitForElementToBePresent(ReloanPage.getByOSType(platform,ReloanPage.objYourMonthlyInstallmentAmount),3,"Monthly Installment loan");
        String monthlyInstallmentAmount = getText(ReloanPage.getByOSType(platform,ReloanPage.objYourMonthlyInstallmentAmount)).substring(1).replaceAll(",","");
        List<WebElement> values = findElements(ReloanPage.getByOSType(platform,ReloanPage.objMonths));
        for(int term=0;term<values.size();term++){
            String reLoanAmount = loanCommonMethods.reloanCalculation(monthlyInstallmentAmount,propertyFileReader("Month" + term, "Reloan"),propertyFileReader("ReloanInterest", "Reloan"));
            verifyElementPresent(ReloanPage.objTerms(platform,propertyFileReader("Month"+term,"Reloan")),getTextVal(ReloanPage.objTerms(platform,propertyFileReader("Month"+term,"Reloan"))," Month"));
            assertionValidation(getText(ReloanPage.objLoanAmountBasedOnTerms(platform,propertyFileReader("Month"+term,"Reloan"))),"₱"+decimalFormat.format(Double.parseDouble(reLoanAmount))," ReLoan Amount");
            assertionValidation(getText(ReloanPage.objMonthsBasedOnTerms(platform,propertyFileReader("Month"+term,"Reloan"))),propertyFileReader("Months","Reloan")," Text");
            assertionValidation(getText(ReloanPage.objYouWillReceiveTextBasedOnTerms(platform,propertyFileReader("Month"+term,"Reloan"))),"Amount you’ll receive"," Text");
        }
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objYourMonthlyInstallmentText)),propertyFileReader("YourMonthlyInstallment","Reloan")," Text");
        verifyElementPresent(ReloanPage.getByOSType(platform,ReloanPage.objYourMonthlyInstallmentAmount),getText(ReloanPage.getByOSType(platform,ReloanPage.objYourMonthlyInstallmentAmount)));
        swipe("UP",1);
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objContinueWithPayHingBtn)), propertyFileReader("continueWithpayHingaBtn", "QuickLoanWithVas"), "Button text");
    }

    /**
     * reusable method to verify loan summary page
     * @param loanAmount
     * @param monthlyPayment
     * @param term
     * @param addOnRate
     * @param documentaryTaxValue
     * @throws Exception
     */
    public void verifyLoanSummaryPage(String loanAmount, String monthlyPayment,String term, String addOnRate, String documentaryTaxValue,String vasType) throws Exception {
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objLoanSummaryHeader)),propertyFileReader("SummaryLoan","Reloan")," Header");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objLoanAmountText)),propertyFileReader("LoanAmount","Reloan")," Text");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objLoanAmountValue)),"₱"+decimalFormat.format(Double.parseDouble(loanAmount))," Loan Amount");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objInstallmentTermsText)),propertyFileReader("InstallmentTerms","Reloan")," Text");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objInstallmentTermsValue)),term+" Months"," Monthly Payment Amount");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objMonthlyAddOnRateText)),propertyFileReader("MonthlyAddOn","Reloan")," Text");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objMonthlyAddOnRateValue)).replaceAll("\\s"," "),addOnRate+"% "+propertyFileReader("MonthlyAddOnDescription","Reloan")," Monthly Add On Text");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objDeductedFromLoanText)),propertyFileReader("DeductedFrom","Reloan")," Text");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objProcessingFeeText)),propertyFileReader("ProcessingFee","Reloan")," Text");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objProcessingFeeValue)),"₱"+propertyFileReader("ProcessingFeeAmount","Reloan")," Loan Amount");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objDocumentaryStampText)),propertyFileReader("DocumentaryStam","Reloan")," Text");
//        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objDocumentaryStampValue)),"₱"+documentaryTaxValue," Monthly Payment Amount");
        if(vasType.equalsIgnoreCase("WithVAS")) {
            String creditLifeInsuranceAmount = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPaymentHolidayPayHingaSubTxt));
            creditLifeInsuranceAmount = loanCommonMethods.extractNumbers(creditLifeInsuranceAmount);
            creditLifeInsuranceAmount = loanCommonMethods.removeSpecialCharacter(creditLifeInsuranceAmount, false);
            creditLifeInsuranceAmount = creditLifeInsuranceAmount.replace(" ", "");
            valueValidation(creditLifeInsuranceAmount, loanCommonMethods.payHingaPaymentHolidayCalc(loanAmount), "Credit Life Insurance Amount", "contains");
            additionalPayment = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAdditionalPayHingsSubTxt));
            additionalPayment = loanCommonMethods.extractNumbers(additionalPayment);
            additionalPayment = loanCommonMethods.removeSpecialCharacter(additionalPayment, false);
            containsValidation(additionalPayment, loanCommonMethods.additionalMonthlyPayment(monthlyPayment), "Additional monthly payment");
            swipe("UP",1);
            containsValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyInstallmentText)), propertyFileReader("TotalMonthlyInstallmentWithPayHinga", "flexUp"), "Text");
            String monthlyInstallmentWithPayHinga = String.valueOf(Double.parseDouble(monthlyPayment) + Double.parseDouble(loanCommonMethods.additionalMonthlyPayment(monthlyPayment)));
            containsValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyInstallmentvalue)).replace("₱", "").replace(",", ""), monthlyInstallmentWithPayHinga, "Value");
        }else{
            assertionValidation(getText(FlexUpPage.getByOSType(platform,FlexUpPage.objInfoScreenMonthlyPaymentTxt)), propertyFileReader("monthlyPayment", "flexUp"), "Text");
            containsValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objInfoScreenMonthlyPaymentAmount        )).replace("₱", "").replace(",", ""), monthlyPayment, "Value");
        }
        swipe("UP",2);
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objSummaryLoanPageFooter)),propertyFileReader("FooterText","Reloan")," Footer Text");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objSweetIAcceptText)),propertyFileReader("SweetAccept","Reloan")," Button");
    }

    /**
     * reusable method to decline the Re-Loan offer
     * @throws Exception
     */
    public void declineTheReLoanOffer() throws Exception {
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objLoanTileHeader),getText(ReloanPage.getByOSType(platform,ReloanPage.objLoanTileHeader)));
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objDeclineTheOfferButton),getText(ReloanPage.getByOSType(platform,ReloanPage.objDeclineTheOfferButton)));
        verifyElementPresent(ReloanPage.getByOSType(platform,ReloanPage.objSorryIt),getText(ReloanPage.getByOSType(platform,ReloanPage.objSorryIt)));
        verifyElementPresentAndClick(ReloanPage.objLoanDeclineReason(platform,"I don’t want a new loan right now"),"I don’t want a new loan right now");
        assertionValidation(getAttributValue("enabled", ReloanPage.getByOSType(platform,ReloanPage.objNextButtonInDeclineLoanPage)), "true", ": Enable Attribute value");
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objNextButtonInDeclineLoanPage),"Next Button");
        swipe("UP",2);
        waitForElementToBePresent(ReloanPage.getByOSType(platform,ReloanPage.objLoanTileHeaderAfterDecline),2," Loan Header");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objLoanTileHeaderAfterDecline)),propertyFileReader("loanTileHeader","Reloan")," Header");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objLoansTile)),propertyFileReader("loanTile","Reloan")," SubText");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objFindTheRightLoan)),propertyFileReader("FindTheRightLoan","Reloan")," SubText");
    }
    /**
     * reusable method to accept the Re-Loan offer
     * @param month
     * @throws Exception
     */
    public void acceptTheReloanOffer(String month,String vasType) throws Exception {
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objLoanTileHeader),getText(ReloanPage.getByOSType(platform,ReloanPage.objLoanTileHeader)));
        verifyTonikToRescuePage();
        click(ReloanPage.getByOSType(platform,ReloanPage.objTellMeMoreButton),getText(ReloanPage.getByOSType(platform,ReloanPage.objTellMeMoreButton)));
        loanCommonMethods.nowWithPayHingaPageValidation();
        verifyApprovedLoanOfferPage();
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objBackArrowButton),"Back Arrow Button");
        loanCommonMethods.nowWithPayHingaPageValidation();
        if(verifyElementDisplayed(ReloanPage.getByOSType(platform,ReloanPage.objTellMeMoreButton))) {
            click(ReloanPage.getByOSType(platform, ReloanPage.objTellMeMoreButton), getText(ReloanPage.getByOSType(platform, ReloanPage.objTellMeMoreButton)));
        }
        click(ReloanPage.objTerms(platform,month),month);
        String monthlyInstallmentAmount = getText(ReloanPage.getByOSType(platform,ReloanPage.objYourMonthlyInstallmentAmount)).substring(1).replaceAll(",","");
        String reLoanAmount = loanCommonMethods.reloanCalculation(monthlyInstallmentAmount,month,propertyFileReader("ReloanInterest", "Reloan"));
        double addOnrate = loanCommonMethods.monthlyAddOnCalculation(Double.parseDouble(monthlyInstallmentAmount), Double.parseDouble(reLoanAmount), Integer.parseInt(month));
        swipe("UP",1);
        if(propertyFileReader("BuildNo","Reloan").equalsIgnoreCase("6.0.2"))
            click(ReloanPage.getByOSType(platform,ReloanPage.objIAcceptTheOfferButton),getText(ReloanPage.getByOSType(platform,ReloanPage.objIAcceptTheOfferButton)));
        else
            navigateToSummary(vasType);
        verifyLoanSummaryPage(reLoanAmount,monthlyInstallmentAmount,month,String.valueOf(addOnrate),loanCommonMethods.calculateDocumentarySummaryTax(Double.parseDouble(month),Double.parseDouble(reLoanAmount)),vasType);
        click(ReloanPage.getByOSType(platform,ReloanPage.objSweetIAcceptText),getText(ReloanPage.getByOSType(platform,ReloanPage.objSweetIAcceptText)));
        loanCommonMethods.whatDoYouNeedItForScreen("Education");
    }
    /**
     * Reusable method to navigate to summary
     * @throws Exception
     */
    public void navigateToSummary(String vasType) throws Exception {
        if(vasType.equalsIgnoreCase("WithVAS")) {
            click(FlexUpPage.getByOSType(platform, FlexUpPage.objContinueWithPayHingBtn), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objContinueWithPayHingBtn), "Button"));
        }else{
            monthlyInstallment = getText(FlexUpPage.getByOSType(platform,FlexUpPage.objMonthlyInstallmentAmount)).replace("₱", "").replace(",", "");
            click(FlexUpPage.getByOSType(platform, FlexUpPage.objUnprotectedloanBtn),getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objUnprotectedloanBtn),"Button"));
            click(FlexUpPage.getByOSType(platform, FlexUpPage.objCustomButtonText),getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objCustomButtonText),"Button"));
        }
    }
    /**
     * reusable method to verify monthly installment summary page
     * @param subHeader
     * @throws Exception
     */
    public void monthlyInstallmentSummaryPageValidation(String subHeader) throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyInstallmentSummary), 15, getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyInstallmentSummary), "Page Header"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyInstallmentSummary), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyInstallmentSummary), "Page Header"));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyInstallmentSummary)), propertyFileReader("monthlyInstallmentPageHeader", "QuickLoanWithVas"), "Page Header", "contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSubTitleTxt)), subHeader, "Sub Header", "contains");
        valueValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objFirstInstallmentTxt)), propertyFileReader("firstInstallmentTxt", "QuickLoanWithVas"), "Text", "contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDueDate)), propertyFileReader("dueDateTxt", "QuickLoanWithVas"), "Text", "contains");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDueDateMonth), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDueDateMonth), "Text"));
        valueValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objInsatallmentTxt)), propertyFileReader("installmentPeriodTxt", "QuickLoanWithVas"), "Text", "contains");
        verifyElementPresent(ReloanPage.getByOSType(platform,ReloanPage.objInstallmentPeriodMonth), getTextVal(ReloanPage.getByOSType(platform,ReloanPage.objInstallmentPeriodMonth), "Monthly payment due"));
        String installmentPeriod = getText(ReloanPage.getByOSType(platform,ReloanPage.objInstallmentPeriodMonth));
        installmentPeriod = loanCommonMethods.extractNumbers(installmentPeriod);
        logger.info(installmentPeriod);
        valueValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objFirstInstallmentTxt)), propertyFileReader("firstInstallmentTxt", "QuickLoanWithVas"), "Text", "contains");
        verifyElementPresent(ReloanPage.getByOSType(platform,ReloanPage.objInstallmentDuedate), getTextVal(ReloanPage.getByOSType(platform,ReloanPage.objInstallmentDuedate), "Text"));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objConfirmBtn)), propertyFileReader("confirmBtn", "QuickLoanWithVas"), "Text", "contains");
        boolean enabledConfirmButton = findElement(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objConfirmBtn)).isEnabled();
        if(enabledConfirmButton) {
            logger.info("Confirm button is enabled state");
            extentLoggerPass("Month Options", "Confirm button is enabled state");
        }
    }

    /**
     * reusable method to verify I want to close my loan page
     * @throws Exception
     */
    public void iWantToCloseMyLoanPageValidation() throws Exception {
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextInstallmentTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextInstallmentTxt), "Text"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanDashBoardDueDate), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanDashBoardDueDate), "Text"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanDashBoardEmiAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanDashBoardEmiAmount), "EMI Amount"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objThisAmountMessage), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objThisAmountMessage), "Text"));
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objThisAmountMessage)), propertyFileReader("thisAmountMessage", "QuickLoanWithVas"), "Text");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPaymentDoneOf),getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPaymentDoneOf)));
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPaymentDoneOf)), propertyFileReader("paymentDoneOf", "Reloan"), "Text");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoButton), "Info Button");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseLoanButton), "I want to close my loan");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseLoanButton)), propertyFileReader("iWantToCloseLoanBtn", "QuickLoanWithVas"), "Button");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPaymentRecordTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPaymentRecordTxt), "Text"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPaymentRecordTxt)), propertyFileReader("paymentRecord", "QuickLoanWithVas"), "Button");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTodaysDate), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTodaysDate), "Date"));
        String todaysDateActual=getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTodaysDate)).replace("Yesterday,", "").trim();
        containsValidation(todaysDateActual, loanCommonMethods.todaysDate(), "Date");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMoneyCredited), "Money Credited Text");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMoneyCreditedAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMoneyCreditedAmount), "Amount"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objFundTransferredTo), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objFundTransferredTo), "Text"));
    }

    /**
     * reusable method for Loan information page
     * @throws Exception
     */
    public void loanInformationScreen(String loanType) throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoButton), 10, "Information Button");
        String loanDashBoardEmiAmount = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanDashBoardEmiAmount));
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoButton), "Information Button");
        verifyElementPresent(FlexUpPage.getByOSType(platform,FlexUpPage.objMainTitleTxt), getTextVal(FlexUpPage.getByOSType(platform,FlexUpPage.objMainTitleTxt),"Page Header"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform,FlexUpPage.objMainTitleTxt)), propertyFileReader("loanInformation", "flexUp"), "Page Header");
        assertionValidation(getText(FlexUpPage.getByOSType(platform,FlexUpPage.objLoanAmountTxt)), propertyFileReader("LoanAmountText", "flexUp"), "Text");
        verifyElementPresent(FlexUpPage.getByOSType(platform,FlexUpPage.objLoanAmount), getTextVal(FlexUpPage.getByOSType(platform,FlexUpPage.objLoanAmount), "Loan Amount"));
        String installmentTerms = loanCommonMethods.extractNumbers(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objInstallmentTermsValue)));
        if (loanType.equalsIgnoreCase("WithVAS")) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenBorrowDateTxt)), propertyFileReader("borrowingTxt", "QuickLoanWithVas"), "Text");
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenBorrowDate)), loanCommonMethods.todaysDateMMMM(), "Borrow Date");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenInstallmentTermsTxt)), propertyFileReader("installmentTermsTxt", "QuickLoanWithVas"), "Text");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenInsuranceCoverageTxt)), propertyFileReader("insuranceCoverage", "QuickLoanWithVas"), "Text");
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenInsuranceAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenInsuranceAmount), "Insurance Amount"));
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenLoanDocumentTxt)), propertyFileReader("loanDocumentsTxt", "QuickLoanWithVas"), "Text");
        } else {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenDateBookedTxt)), propertyFileReader("DateBooked", "QuickLoanWithVas"), "Text");
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenDateBooked)), loanCommonMethods.todaysDateMMMM(), "Date Booked");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenInstallmentTermsTxt)), propertyFileReader("installmentPeriodTxt","QuickLoanWithVas"), "Text");
        }
        assertionValidation(getText(FlexUpPage.getByOSType(platform,FlexUpPage.objInfoScreenMonthlyPaymentTxt)), propertyFileReader("monthlyPayment", "flexUp"), "Text");
        String actualMonthlyPaymentMethod = getText(FlexUpPage.getByOSType(platform,FlexUpPage.objInfoScreenMonthlyPaymentAmount));
        assertionValidation(actualMonthlyPaymentMethod, loanDashBoardEmiAmount, "Monthly Payment Amount");
        assertionValidation(getText(FlexUpPage.getByOSType(platform,FlexUpPage.objInfoScreenDueDateTxt)), propertyFileReader("DueDateText", "flexUp"), "Text");
        verifyElementPresent(FlexUpPage.getByOSType(platform,FlexUpPage.objInfoScreenDueDate), getTextVal(FlexUpPage.getByOSType(platform,FlexUpPage.objInfoScreenDueDate), "Due Date"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform,FlexUpPage.objInfoScreeRateAndFees)), propertyFileReader("RatesAndFeesHeader", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform,FlexUpPage.objInfoScreenTermsAndConditionTxt)), propertyFileReader("TermsAndConditionsText", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform,FlexUpPage.objInfoScreenPromissoryNoteTxt)), propertyFileReader("PromissoryNoteText", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform,FlexUpPage.objInfoScreenDisclosureTxt)), propertyFileReader("DisclosureStatementText", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform,FlexUpPage.objInfoScreenAmortizationTxt)), propertyFileReader("AmortizationScheduleText", "flexUp"), "Text");
    }
    /**
     * Reusable method to insert Flex Pivot Offer
     * @param mobileNumber
     * @throws SQLException
     */
    public void insertReLoanOffer(String mobileNumber) throws SQLException, JSchException {
        insertQuery("Insert into loans.tdbk_loan_offers_trx(user_id,cust_id,mobile_number,offer_start_date,offer_expired_date,offer_status,offer_type,offer_amount,offer_min_amount,offer_max_amount,min_emi_amount,max_emi_amount,min_dp_percent,status,loan_product_type,is_eligible,created_date,updated_by,interest_rate,customer_margin,tenures,file_uploaded_date,popup_triggered,max_tenure,prev_loan_acc_no)\n" +
                "values('"+ loanCommonMethods.getUserId(mobileNumber)+"','"+loanCommonMethods.getCustomerId(mobileNumber)+"','"+mobileNumber+"','"+loanCommonMethods.extractedOneDayBackDate(1)+"','"+loanCommonMethods.extractUpcomingDate(5)+"','"+propertyFileReader("offer_status","flexUp")+"','"+propertyFileReader("offer_type","Reloan")+"','"+propertyFileReader("offer_amount","flexUp")+"'," +
                "'"+propertyFileReader("offer_min_amount","flexUp")+"','"+propertyFileReader("offer_max_amount","flexUp")+"','"+propertyFileReader("min_emi_amount","flexUp")+"','"+propertyFileReader("max_emi_amount","flexUp")+"','"+propertyFileReader("min_dp_percent","flexUp")+"'," +
                "'"+propertyFileReader("status","flexUp")+"','"+propertyFileReader("loan_product_type","Reloan")+"','"+propertyFileReader("is_eligible","flexUp")+"','"+loanCommonMethods.todayDate()+"','"+propertyFileReader("updated_by","flexUp")+"','"+propertyFileReader("interest_rate","flexUp")+"'," +
                "'"+propertyFileReader("customer_margin","flexUp")+"','"+propertyFileReader("tenures","flexUp")+"','"+loanCommonMethods.todayDate()+"','"+propertyFileReader("popup_triggered","flexUp")+"',"+Integer.parseInt(propertyFileReader("max_tenure","flexUp"))+",'"+getPreviousLoanAccountNumber(loanCommonMethods.getUserId(mobileNumber))+"');\n");
    }
    public String getPreviousLoanAccountNumber(String userId) throws JSchException, SQLException {
        return selectQuery("customer","SELECT loanAccountNumber\n" +
                "FROM loans.tdbk_digital_loan_application \n" +
                "WHERE userId = '"+userId+"' and applicationStatus = 'COMPLETED'\n" +
                "ORDER BY Updatedtimestamp ASC\n" +
                "LIMIT 1;");
    }
    public String getPreviousLoanEMI(String userId) throws JSchException, SQLException {
        return selectQuery("customer","SELECT monthlyPayment\n" +
                "FROM loans.tdbk_digital_loan_application \n" +
                "WHERE userId = '"+userId+"' and applicationStatus = 'COMPLETED'\n" +
                "ORDER BY Updatedtimestamp ASC\n" +
                "LIMIT 1;");
    }
    /**
    * Method to Verify the loan tile for a Reloan Offer
     * @param password
     * @throws Exception
     */
    public void verifyLoanTileForReloanOffer_TDB_RL_001(String password) throws Exception {
        HeaderChildNode("TDB-RL-001, ReLoan - Verify the loan tile for a Reloan Offer");
        insertReLoanOffer("63"+propertyFileReader("ReLoanMobileNumber","TestDataNumbers"));
        tonikLogin(password);
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objLoanTileHeader)),propertyFileReader("LoanHeader","Reloan")," Header");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objLoansTileSubText1)),propertyFileReader("LoanTileSubText1","Reloan")," SubText");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objLoansTileSubText2)),propertyFileReader("LoanTileSubText2","Reloan")," SubText");
        logger.info("TDB-RL-001, ReLoan - Verify the loan tile for a Reloan Offer");
        extentLoggerPass("TDB-RL-001", "TDB-RL-001, ReLoan - Verify the loan tile for a Reloan Offer");
    }
    /**
    * Method to Verify if user can decline the Reloan offer
     * @param password
     * @throws Exception
     */
    public void verifyUserCanDeclineTheReloanOffer_TDB_RL_003(String password) throws Exception {
        HeaderChildNode("TDB-RL-003, ReLoan - Verify if user can decline the Reloan offer");
        tonikLogin(password);
        declineTheReLoanOffer();
        logger.info("TDB-RL-003, ReLoan - Verify if user can decline the Reloan offer");
        extentLoggerPass("TDB-RL-003", "TDB-RL-003, ReLoan - Verify if user can decline the Reloan offer");
    }
    /**
    * Method to Verify if user can accept the Reloan offer
     * @param password
     * @param month
     * @throws Exception
     */
    public void verifyUserCanAcceptTheReloanOffer_TDB_RL_004(String password,String month) throws Exception {
        HeaderChildNode("TDB-RL-004, ReLoan - Verify if user can accept the Reloan offer");
        tonikLogin(password);
        acceptTheReloanOffer(month,"WithVAS");
        logger.info("TDB-RL-004, ReLoan - Verify if user can accept the Reloan offer");
        extentLoggerPass("TDB-RL-004", "TDB-RL-004, ReLoan - Verify if user can accept the Reloan offer");
    }
    /**
    * Method to Verify if user can select a Reloan reason
     * @param password
     * @param loanPurpose
     * @throws Exception
     */
    public void verifyUserCanSelectReloanReason_TDB_RL_005(String password,String loanPurpose) throws Exception {
        HeaderChildNode("TDB-RL-005, ReLoan - Verify if user can select a Reloan reason");
        tonikLogin(password);
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objLoanTileHeader),getText(ReloanPage.getByOSType(platform,ReloanPage.objLoanTileHeader)));
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objTonikToRescuePageHeader)).replaceAll("\\s"," "),propertyFileReader("TonikToTheRescue","Reloan")," Page Header");
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objTellMeMoreButton),getText(ReloanPage.getByOSType(platform,ReloanPage.objTellMeMoreButton)));
        loanCommonMethods.nowWithPayHingaPageValidation();;
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objLoanOfferPageHeader)),propertyFileReader("YouGotLoanOffer","Reloan")," Page Header");
        swipe("UP",1);
        if(propertyFileReader("BuildNo","Reloan").equalsIgnoreCase("6.0.2")) {
            verifyElementPresentAndClick(ReloanPage.getByOSType(platform, ReloanPage.objIAcceptTheOfferButton), getText(ReloanPage.getByOSType(platform, ReloanPage.objIAcceptTheOfferButton)));
        }else {
            navigateToSummary("WithVAS");
        }
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objLoanSummaryHeader)),propertyFileReader("SummaryLoan","Reloan")," Header");
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objSweetIAcceptText),getText(ReloanPage.getByOSType(platform,ReloanPage.objSweetIAcceptText)));
        verifyElementPresentAndClick(ReloanPage.objLoanPurpose(platform,loanPurpose),loanPurpose);
        assertionValidation(getAttributValue("enabled", ReloanPage.getByOSType(platform,ReloanPage.objNextButton)), "true", ": Enable Attribute value");
        click(ReloanPage.getByOSType(platform,ReloanPage.objNextButton)," Next Button");
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objYesThisMyAddressBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objYesThisMyAddressBtn), ": button"));
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objTermsAndConditionPageHeader)),propertyFileReader("TermsAndCondition","Reloan")," Page Header");
        logger.info("TDB-RL-005, ReLoan - Verify if user can select a Reloan reason");
        extentLoggerPass("TDB-RL-005", "TDB-RL-005, ReLoan - Verify if user can select a Reloan reason");
    }
    /**
    * Method to Verify if user can decline the Reloan offer (closed Flex Loan)
     * @param password
     * @throws Exception
     */
    public void verifyUserCanDeclineTheReloanOfferClosedFlexLoan_TDB_RL_023(String password) throws Exception {
        HeaderChildNode("TDB-RL-023, ReLoan - Verify if user can decline the Reloan offer (closed Flex Loan)");
        tonikLogin(password);
        declineTheReLoanOffer();
        logger.info("TDB-RL-023, ReLoan - Verify if user can decline the Reloan offer (closed Flex Loan)");
        extentLoggerPass("TDB-RL-023", "TDB-RL-023, ReLoan - Verify if user can decline the Reloan offer (closed Flex Loan)");
    }
    /**
    * Method to Verify if user can accept the Reloan offer (closed Flex Loan)
     * @param password
     * @param month
     * @throws Exception
     */
    public void verifyUserCanAcceptTheReloanOfferClosedFlexLoan_TDB_RL_024(String password,String month) throws Exception {
        HeaderChildNode("TDB-RL-024, ReLoan - Verify if user can accept the Reloan offer (closed Flex Loan)");
        tonikLogin(password);
        acceptTheReloanOffer(month,"WithVAS");
        logger.info("TDB-RL-024, ReLoan - Verify if user can accept the Reloan offer (closed Flex Loan)");
        extentLoggerPass("TDB-RL-024", "TDB-RL-024, ReLoan - Verify if user can accept the Reloan offer (closed Flex Loan)");
    }
    /**
    * Method to Verify if user can accept the Terms and Conditions
     * @param password
     * @param loanPurpose
     * @throws Exception
     */
    public void verifyIfUserCanAcceptTermsAndConditions_TDB_RL_006(String password,String loanPurpose)throws Exception{
        HeaderChildNode("TDB-RL-006, ReLoan - Verify if user can accept the Terms and Conditions");
        tonikLogin(password);
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objLoanTileHeader),getText(ReloanPage.getByOSType(platform,ReloanPage.objLoanTileHeader)));
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objTonikToRescuePageHeader)).replaceAll("\\s"," "),propertyFileReader("TonikToTheRescue","Reloan")," Page Header");
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objTellMeMoreButton),getText(ReloanPage.getByOSType(platform,ReloanPage.objTellMeMoreButton)));
        loanCommonMethods.nowWithPayHingaPageValidation();
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objLoanOfferPageHeader)),propertyFileReader("YouGotLoanOffer","Reloan")," Page Header");
        swipe("UP",1);
        if(propertyFileReader("BuildNo","Reloan").equalsIgnoreCase("6.0.2"))
            verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objIAcceptTheOfferButton),getText(ReloanPage.getByOSType(platform,ReloanPage.objIAcceptTheOfferButton)));
        else
            navigateToSummary("WithVAS");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objLoanSummaryHeader)),propertyFileReader("SummaryLoan","Reloan")," Header");
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objSweetIAcceptText),getText(ReloanPage.getByOSType(platform,ReloanPage.objSweetIAcceptText)));
        verifyElementPresentAndClick(ReloanPage.objLoanPurpose(platform,loanPurpose),loanPurpose);
        assertionValidation(getAttributValue("enabled", ReloanPage.getByOSType(platform,ReloanPage.objNextButton)), "true", ": Enable Attribute value");
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objNextButton)," Next Button");
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objIsThisYourCurrentAddressButton),"Is This Your CurrentAddress Button");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionScreen),5,"Terms and Condition Screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionScreen),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionScreen),": page"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionSubtitle)),propertyFileReader("TermsAndConditionScreenSubtitle","QuickLoanWithVas"),"Page Subtitle");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadTermsAndConditionBtn),"Download Terms and condition button");
        if(platform.equalsIgnoreCase("ios")){
            waitTime(3000);
            tapOnScreen(350,200,"screen");
        }
        waitTime(3000);
        click(FlexUpPage.getByOSType(platform,FlexUpPage.objBackBtn),"Back button");
        waitForElementToBePresent(ReloanPage.getByOSType(platform,ReloanPage.objIsThisYourCurrentAddressButton),10,"Is This Your CurrentAddress Button");
        click(ReloanPage.getByOSType(platform,ReloanPage.objIsThisYourCurrentAddressButton),"Is This Your CurrentAddress Button");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionScreen),5,"Terms and Condition Screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionScreen),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionScreen),": page"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionSubtitle)),propertyFileReader("TermsAndConditionScreenSubtitle","QuickLoanWithVas"),"Page Subtitle");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadTermsAndConditionBtn),"Download Terms and condition button");
        if(platform.equalsIgnoreCase("ios")){
            waitTime(3000);
            tapOnScreen(350,200,"screen");
        }
        assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadTermsAndConditionBtn)),"true","Attribute value");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionCheckBox),"Terms and condition check box");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIAcceptBtn),"I accept and give my consent button");
        waitTime(2000);
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), "Page Header"));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("tellUsPageHeader", "QuickLoanWithVas"), "Page Header", "contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSubTitleTxt)), propertyFileReader("weAreNotTxt", "QuickLoanWithVas"), "Sub Header", "contains");
        logger.info("TDB-RL-006, ReLoan - Verify if user can accept the Terms and Conditions");
        extentLoggerPass("TDB-RL-006", "TDB-RL-006, ReLoan - Verify if user can accept the Terms and Conditions");
    }

    /**
    * Method to Verify if user can select a pay days
     * @param password
     * @param loanPurpose
     * @throws Exception
     */
    public void verifyUserCanSelectAPayDays_TDB_RL_007(String password,String loanPurpose)throws Exception{
        HeaderChildNode("TDB-RL-007, ReLoan - Verify if user can select a pay days");
        tonikLogin(password);
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objLoanTileHeader),getText(ReloanPage.getByOSType(platform,ReloanPage.objLoanTileHeader)));
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objTonikToRescuePageHeader)).replaceAll("\\s"," "),propertyFileReader("TonikToTheRescue","Reloan")," Page Header");
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objTellMeMoreButton),getText(ReloanPage.getByOSType(platform,ReloanPage.objTellMeMoreButton)));
        loanCommonMethods.nowWithPayHingaPageValidation();
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objLoanOfferPageHeader)),propertyFileReader("YouGotLoanOffer","Reloan")," Page Header");
        swipe("UP",1);
        if(propertyFileReader("BuildNo","Reloan").equalsIgnoreCase("6.0.2"))
            verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objIAcceptTheOfferButton),getText(ReloanPage.getByOSType(platform,ReloanPage.objIAcceptTheOfferButton)));
        else
            navigateToSummary("WithVAS");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objLoanSummaryHeader)),propertyFileReader("SummaryLoan","Reloan")," Header");
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objSweetIAcceptText),getText(ReloanPage.getByOSType(platform,ReloanPage.objSweetIAcceptText)));
        verifyElementPresentAndClick(ReloanPage.objLoanPurpose(platform,loanPurpose),loanPurpose);
        assertionValidation(getAttributValue("enabled", ReloanPage.getByOSType(platform,ReloanPage.objNextButton)), "true", ": Enable Attribute value");
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objNextButton)," Next Button");
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objIsThisYourCurrentAddressButton),"Is This Your CurrentAddress Button");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objTermsAndConditionPageHeader)),propertyFileReader("TermsAndCondition","Reloan")," Page Header");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionCheckBox),"Terms and condition check box");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIAcceptBtn),"I accept and give my consent button");
        loanCommonMethods.tellUsYourPayDayPageValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), "Next Button");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyInstallmentSummary), 15, getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyInstallmentSummary), "Page Header"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyInstallmentSummary), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyInstallmentSummary), "Page Header"));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyInstallmentSummary)), propertyFileReader("monthlyInstallmentPageHeader", "QuickLoanWithVas"), "Page Header", "contains");
        logger.info("TDB-RL-007, ReLoan - Verify if user can select a pay days");
        extentLoggerPass("TDB-RL-007", "TDB-RL-007, ReLoan - Verify if user can select a pay days");
    }
    /**
    * Method to Verify if user can confirm the monthly installment summary
     * @param password
     * @param loanPurpose
     * @throws Exception
     */
    public void verifyUserCanConfirmMonthlyInstallmentSummary_TDB_RL_008(String password,String loanPurpose)throws Exception{
        HeaderChildNode("TDB-RL-008, ReLoan - Verify if user can confirm the monthly installment summary");
        tonikLogin(password);
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objLoanTileHeader),getText(ReloanPage.getByOSType(platform,ReloanPage.objLoanTileHeader)));
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objTonikToRescuePageHeader)).replaceAll("\\s"," "),propertyFileReader("TonikToTheRescue","Reloan")," Page Header");
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objTellMeMoreButton),getText(ReloanPage.getByOSType(platform,ReloanPage.objTellMeMoreButton)));
        loanCommonMethods.nowWithPayHingaPageValidation();
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objLoanOfferPageHeader)),propertyFileReader("YouGotLoanOffer","Reloan")," Page Header");
        swipe("UP",1);
        if(propertyFileReader("BuildNo","Reloan").equalsIgnoreCase("6.0.2"))
            verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objIAcceptTheOfferButton),getText(ReloanPage.getByOSType(platform,ReloanPage.objIAcceptTheOfferButton)));
        else
            navigateToSummary("WithVAS");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objLoanSummaryHeader)),propertyFileReader("SummaryLoan","Reloan")," Header");
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objSweetIAcceptText),getText(ReloanPage.getByOSType(platform,ReloanPage.objSweetIAcceptText)));
        verifyElementPresentAndClick(ReloanPage.objLoanPurpose(platform,loanPurpose),loanPurpose);
        assertionValidation(getAttributValue("enabled", ReloanPage.getByOSType(platform,ReloanPage.objNextButton)), "true", ": Enable Attribute value");
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objNextButton)," Next Button");
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objIsThisYourCurrentAddressButton),"Is This Your CurrentAddress Button");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objTermsAndConditionPageHeader)),propertyFileReader("TermsAndCondition","Reloan")," Page Header");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionCheckBox),"Terms and condition check box");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIAcceptBtn),"I accept and give my consent button");
        loanCommonMethods.tellUsYourPayDayPageValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), "Next Button");
        monthlyInstallmentSummaryPageValidation(propertyFileReader("monthlyPageSubHeader", "QuickLoanWithVas"));
        loanCommonMethods.contactUsPageValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objConfirmBtn), "Confirm Button");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignedPage), 10, "Signed, sealed page");
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignedPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignedPage), "Page"))) {
            logger.info("Successfully navigated to : " + getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignedPage), "Page"));
            extentLoggerPass("Signed, Sealed Page", "Successfully navigated to : " + getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignedPage), "Page"));
        }
        logger.info("TDB-RL-008, ReLoan - Verify if user can confirm the monthly installment summary");
        extentLoggerPass("TDB-RL-008", "TDB-RL-008, ReLoan - Verify if user can confirm the monthly installment summary");
    }

    /**
    * Method to Verify if user can sign all the Lon documents
     * @param password
     * @param loanPurpose
     * @throws Exception
     */
    public void verifyUserCanSignAllLoanDocuments_TDB_RL_009(String password,String loanPurpose)throws Exception{
        HeaderChildNode("TDB-RL-009, ReLoan - Verify if user can sign all the Lon documents");
        tonikLogin(password);
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objLoanTileHeader),getText(ReloanPage.getByOSType(platform,ReloanPage.objLoanTileHeader)));
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objTonikToRescuePageHeader)).replaceAll("\\s"," "),propertyFileReader("TonikToTheRescue","Reloan")," Page Header");
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objTellMeMoreButton),getText(ReloanPage.getByOSType(platform,ReloanPage.objTellMeMoreButton)));
        loanCommonMethods.nowWithPayHingaPageValidation();
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objLoanOfferPageHeader)),propertyFileReader("YouGotLoanOffer","Reloan")," Page Header");
        swipe("UP",1);
        if(propertyFileReader("BuildNo","Reloan").equalsIgnoreCase("6.0.2"))
            verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objIAcceptTheOfferButton),getText(ReloanPage.getByOSType(platform,ReloanPage.objIAcceptTheOfferButton)));
        else
            navigateToSummary("WithVAS");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objLoanSummaryHeader)),propertyFileReader("SummaryLoan","Reloan")," Header");
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objSweetIAcceptText),getText(ReloanPage.getByOSType(platform,ReloanPage.objSweetIAcceptText)));
        verifyElementPresentAndClick(ReloanPage.objLoanPurpose(platform,loanPurpose),loanPurpose);
        assertionValidation(getAttributValue("enabled", ReloanPage.getByOSType(platform,ReloanPage.objNextButton)), "true", ": Enable Attribute value");
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objNextButton)," Next Button");
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform,ReloanPage.objIsThisYourCurrentAddressButton),"Is This Your CurrentAddress Button");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objTermsAndConditionPageHeader)),propertyFileReader("TermsAndCondition","Reloan")," Page Header");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionCheckBox),"Terms and condition check box");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIAcceptBtn),"I accept and give my consent button");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objFifthOfTheMonth), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objFifthOfTheMonth), "Radio Button"));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), "Next Button");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objConfirmBtn), "Confirm Button");
        flexUpModule.documentarySignProcessValidation();
        waitForElementDisplayed(FlexUpPage.getByOSType(platform,FlexUpPage.objPopTheChampagneTitle), 10);
        flexUpModule.popTheChampagneScreen();
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform,FlexUpPage.objCustomButtonClick), "Show me the money!");
        loanCommonMethods.iWillDoItLaterPopup();
        waitForElementToBePresent(FlexUpPage.getByOSType(platform,FlexUpPage.objYourTonikAccount), 5,"Dashboard");
        if(verifyElementPresent(FlexUpPage.getByOSType(platform,FlexUpPage.objYourTonikAccount), getTextVal(FlexUpPage.getByOSType(platform,FlexUpPage.objYourTonikAccount),"Text"))) {
            logger.info("TDB-RL-009, Verify if user can sign all the Loan documents");
            extentLoggerPass("TDB-RL-009", "TDB-RL-009, ReLoan - Verify if user can sign all the Lon documents");
        }
    }

    /**
    * Method to Verify if Reloan amount is disbursed successfully
     * @param password
     * @throws Exception
     */
    public void verifyReloanAmountIsDisbursedSuccessfully_TDB_RL_010(String password)throws Exception{
        HeaderChildNode("TDB-RL-010, ReLoan - Verify if Reloan amount is disbursed successfully");
        tonikLogin(password);
        loanCommonMethods.yesYesYesTileValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseBtn), "I want to close my loan Button")) {
            logger.info("TDB-RL-010, ReLoan - Verify if Reloan amount is disbursed successfully");
            extentLoggerPass("TDB-RL-010", "TDB-RL-010, ReLoan - Verify if Reloan amount is disbursed successfully");
        }
    }
    /**
    * Method to Verify if user can view the Account History of the disbursed Reloan
     * @param password
     * @throws Exception
     */
    public void verifyUserCanViewAccountHistoryOfDisburseReloan_TDB_RL_011(String password)throws Exception{
        HeaderChildNode("TDB-RL-011, ReLoan - Verify if user can view the Account History of the disbursed Reloan");
        tonikLogin(password);
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform,FlexUpPage.objHistoryBtn),"History Icon");
        verifyElementPresent(FlexUpPage.getByOSType(platform,FlexUpPage.objPageTitle),getTextVal(FlexUpPage.getByOSType(platform,FlexUpPage.objPageTitle),": page title"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform,FlexUpPage.objPageTitle)), propertyFileReader("AccountHistory", "QuickLoanWithVas"), ": page title");
        verifyElementPresent(QuickLoanWithVasPage.objTransaction(platform,"Processing Fee"),getTextVal(QuickLoanWithVasPage.objTransaction(platform,"Processing Fee"),": transaction"));
        verifyElementPresent(QuickLoanWithVasPage.objTransaction(platform,"DST Fee"),getTextVal(QuickLoanWithVasPage.objTransaction(platform,"DST Fee"),": transaction"));
        verifyElementPresent(QuickLoanWithVasPage.objTransaction(platform,"Money Credited"),getTextVal(QuickLoanWithVasPage.objTransaction(platform,"Money Credited"),": transaction"));
        String amount = loanCommonMethods.removeSpecialCharacter(getText(QuickLoanWithVasPage.objTransactionAmount(platform, "Money Credited")),true);
//        String dst = loanCommonMethods.documentStampFeeCalculator(Double.parseDouble(amount),18);
        containsValidation(getText(QuickLoanWithVasPage.objTransactionAmount(platform,"Processing Fee")),propertyFileReader("ProcessingFeeAmount","Reloan"),": Processing Fee");
//        containsValidation(getText(QuickLoanWithVasPage.objTransactionAmount(platform,"DST Fee")), dst,": DST Fee");
        containsValidation(getText(QuickLoanWithVasPage.objTransactionAmount(platform,"DST Fee")), "",": DST Fee");
        containsValidation(loanCommonMethods.removeSpecialCharacter(getText(QuickLoanWithVasPage.objTransactionAmount(platform, "Money Credited")),true),amount,": Money Credited");
        click(FlexUpPage.getByOSType(platform,FlexUpPage.objBackBtn),"Back button");
        if(verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objProfileName),getTextVal(LoginPage.getByOSType(platform,LoginPage.objProfileName),": profile name"))){
            logger.info("Navigated back to Dashboard");
            extentLoggerPass("TDB-RL-011", "Navigated back to Dashboard");
        }
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform,FlexUpPage.objHistoryBtn),"History Icon");
        click(QuickLoanWithVasPage.objTransaction(platform,"Money Credited"),getTextVal(QuickLoanWithVasPage.objTransaction(platform,"Money Credited"),": transaction"));
        waitTime(3000);
        loanCommonMethods.transactionDetails(amount,propertyFileReader("LoanMoneyCreditedFrom","QuickLoanWithVas"),loanCommonMethods.getAccountNumber("63"+propertyFileReader("ReLoanMobileNumber","TestDataNumbers")),"QuickLoanWithVas");
        waitTime(3000);
        click(FlexUpPage.getByOSType(platform,FlexUpPage.objBackBtn),"Back button");
        click(QuickLoanWithVasPage.objTransaction(platform,"DST Fee"),getTextVal(QuickLoanWithVasPage.objTransaction(platform,"DST Fee"),": transaction"));
        waitTime(3000);
        loanCommonMethods.transactionDetails("",loanCommonMethods.getAccountNumber("63"+propertyFileReader("ReLoanMobileNumber","TestDataNumbers")),propertyFileReader("DocumentStampFeeTo","QuickLoanWithVas"),"QuickLoanWithVas");
        waitTime(3000);
        click(FlexUpPage.getByOSType(platform,FlexUpPage.objBackBtn),"Back button");
        click(QuickLoanWithVasPage.objTransaction(platform,"Processing Fee"),getTextVal(QuickLoanWithVasPage.objTransaction(platform,"Processing Fee"),": transaction"));
        waitTime(3000);
        loanCommonMethods.transactionDetails(propertyFileReader("ProcessingFeeValue","Reloan"),loanCommonMethods.getAccountNumber("63"+propertyFileReader("ReLoanMobileNumber","TestDataNumbers")),propertyFileReader("ProcessingFeeTo","Reloan"),"QuickLoanWithVas");
        logger.info("TDB-RL-011, ReLoan - Verify if user can view the Account History of the disbursed Reloan");
        extentLoggerPass("TDB-RL-011", "TDB-RL-011, ReLoan - Verify if user can view the Account History of the disbursed Reloan");
    }

    /**
    * Method to Verify if user can view the Loan Dashboard after successfully Reloan disbursement
     * @param password
     * @throws Exception
     */
    public void verifyUserCanViewLoanDashboardAfterSuccessfullyReloanDisbursement_TDB_RL_012(String password)throws Exception{
        HeaderChildNode("TDB-RL-012, ReLoan - Verify if user can view the Loan Dashboard after successfully Reloan disbursement");
        tonikLogin(password);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
        iWantToCloseMyLoanPageValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Redirected to main Dashboard Page");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactUsButton),"Contact Us Button");
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objGetInTouchPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objGetInTouchPage), "Page"))) {
            logger.info("TDB-RL-012, Verify if the user can view the Loan Dashboard screen validated");
            extentLoggerPass("TDB-RL-012", "TDB-RL-012 Verify if the user can view the Loan Dashboard screen validated");
        }
    }

    /**
    * Method to Verify if user can view the Loan information screen
     * @param password
     * @throws Exception
     */
    public void verifyUserCanViewLoanInformationScreen_TDB_RL_013(String password)throws Exception{
        HeaderChildNode("TDB-RL-013, ReLoan - Verify if user can view the Loan information screen");
        tonikLogin(password);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
        loanInformationScreen("WithVAS");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
        waitTime(3000);
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainDashBoard), "Your tonik account header")) {
            logger.info("TDB-QLV-013, Verify if user can view the Loan information screen");
            extentLoggerPass("TDB-QLV-013", "Verify if user can view the Loan information screen");
        }
    }

    /**
    * Method to Verify if user can view the Rates and Fees screen
     * @param password
     * @throws Exception
     */
    public void verifyUserCanViewRatesAndFeesScreen_TDB_RL_014(String password)throws Exception{
        HeaderChildNode("TDB-RL-014, ReLoan - Verify if user can view the Rates and Fees screen");
        tonikLogin(password);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoButton), 10, "Information Button");
        String emiAmount = getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanDashBoardEmiAmount));
        emiAmount = loanCommonMethods.removeSpecialCharacter(emiAmount, false);
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoButton), "Information Button");
        String installmentTerms = loanCommonMethods.extractNumbers(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objInstallmentTermsValue)));
        String infoScreenAmount = getText(FlexUpPage.getByOSType(platform,FlexUpPage.objLoanAmount));
        infoScreenAmount=loanCommonMethods.removeSpecialCharacter(infoScreenAmount, false);
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoScreeRateAndFees), "Rates and fees");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), 10, "Rates And Fees Page");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("ratesAndFeesTxt", "QuickLoanWithVas"), "Text");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyAddOnRate)), propertyFileReader("monthlyAddOnRateTxt", "QuickLoanWithVas"), "Text");
        String reLoanAmount = loanCommonMethods.reloanCalculation(emiAmount, installmentTerms, propertyFileReader("ReloanInterest", "Reloan"));
        double monthlyaddOnrate = loanCommonMethods.monthlyAddOnCalculation(Double.parseDouble(emiAmount), Double.parseDouble(reLoanAmount), Integer.parseInt(installmentTerms));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRatesPageMonthlyAddOnRate)), String.valueOf(monthlyaddOnrate), "Monthly add on rate", "contains");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAddOnRateSubTxt) ,getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAddOnRateSubTxt)));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objProcessingFeeTxt)), propertyFileReader("processingFeeTxt", "QuickLoanWithVas"), "Processing Fee");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRatesPageProcessingFee), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRatesPageProcessingFee), "Processing Fee Amount"));
        String processingFee=getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRatesPageProcessingFee));
        processingFee = loanCommonMethods.removeSpecialCharacter(processingFee, true);
        assertionValidation(processingFee, propertyFileReader("ProcessingFeeValue", "Reloan"), "Processing Fee");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objEffectiveInterestRate)), propertyFileReader("effectiveInterestRate", "QuickLoanWithVas"), "Text");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objEffectiveInterestRateValue), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objEffectiveInterestRate),"Effective Interest Rate"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDocStampTaxTxt), 10, "Documentary Stamp Tax");
        String expectedDocumentaryStampTaxAmount = loanCommonMethods.calculateDocumentarySummaryTax(Double.parseDouble(propertyFileReader("Month0", "Reloan")), Double.parseDouble(infoScreenAmount));
//        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDocStampTaxAmount)), expectedDocumentaryStampTaxAmount, "Documentary stamp tax", "contains");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNetProceedsTxt)), propertyFileReader("netProceeds", "QuickLoanWithVas"), "Text");
        containsValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objLateFeeTxt)).replaceAll("F","f"), propertyFileReader("LateFeeTxt", "Reloan"), "Text");
        assertionValidation(loanCommonMethods.removeSpecialCharacter(getText(ReloanPage.getByOSType(platform,ReloanPage.objLateFeeAmount)), false), propertyFileReader("LateFee", "Reloan"), "Late Fee Amount");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt)))) {
            logger.info("TDB-RL-014, ReLoan - Verify if user can view the Rates and Fees screen");
            extentLoggerPass("TDB-RL-014", "ReLoan - Verify if user can view the Rates and Fees screen");
        }
    }

    /**
    * Method to Verify if user can view and download the loan documents
     * @param password
     * @throws Exception
     */
    public void verifyUserCanViewAndDownloadLoanDocuments_TDB_RL_015(String password)throws Exception{
        HeaderChildNode("TDB-RL-015, ReLoan - Verify if user can view and download the loan documents");
        tonikLogin(password);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoButton), 10, "Information Button");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoButton), "Information Button");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform,FlexUpPage.objTermsAndConditionsClick), "Terms and Conditions");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform,FlexUpPage.objDownloadText), 10);
        assertionValidation(getText(FlexUpPage.getByOSType(platform,FlexUpPage.objHeader)), propertyFileReader("TermsAndConditionsText", "flexUp"), ":Header");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform,FlexUpPage.objDownloadBtn),"Download button");
        if(platform.equalsIgnoreCase("IOS")){
            waitTime(3000);
            tapOnScreen(350,100,"screen");
        }
        click(FlexUpPage.getByOSType(platform,FlexUpPage.objBackIcon), "Back Icon");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform,FlexUpPage.objPromissoryNoteClick), "Promissory Note");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform,FlexUpPage.objNoteDocument), 10);
        assertionValidation(getText(FlexUpPage.getByOSType(platform,FlexUpPage.objHeader)), propertyFileReader("PromissoryNoteText", "flexUp"), ":Header");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform,FlexUpPage.objDownloadBtn),"Download button");
        if(platform.equalsIgnoreCase("IOS")){
            waitTime(3000);
            tapOnScreen(350,100,"screen");
        }
        click(FlexUpPage.getByOSType(platform,FlexUpPage.objBackIcon), "Back Icon");
        swipe("UP",2);
        click(FlexUpPage.getByOSType(platform,FlexUpPage.objDisclosureStatementClick), "Disclosure Statement");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform,FlexUpPage.objNoteDocument), 10);
        assertionValidation(getText(FlexUpPage.getByOSType(platform,FlexUpPage.objHeader)), propertyFileReader("DisclosureStatementText", "flexUp"), ":Header");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform,FlexUpPage.objDownloadBtn),"Download button");
        if(platform.equalsIgnoreCase("IOS")){
            waitTime(3000);
            tapOnScreen(350,100,"screen");
        }
        click(FlexUpPage.getByOSType(platform,FlexUpPage.objBackIcon), "Back Icon");
        swipe("UP",2);
        click(FlexUpPage.getByOSType(platform,FlexUpPage.objAmortizationScheduleClick), "Amortization Schedule");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform,FlexUpPage.objNoteDocument), 10);
        assertionValidation(getText(FlexUpPage.getByOSType(platform,FlexUpPage.objHeader)), propertyFileReader("AmortizationScheduleText", "flexUp"), ":Header");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform,FlexUpPage.objDownloadBtn),"Download button");
        if(platform.equalsIgnoreCase("IOS")){
            tapOnScreen(300,150,"screen");
        }
        click(FlexUpPage.getByOSType(platform,FlexUpPage.objBackIcon), "Back Icon");
        logger.info("TDB-RL-015, ReLoan - Verify if user can view and download the loan documents");
        extentLoggerPass("TDB-RL-015", "ReLoan - Verify if user can view and download the loan documents");
    }

    /**
    * Method to Verify if user can make full repayment with insufficient TSA balance
     * @param password
     * @throws Exception
     */
    public void verifyUserCanMakeFullRepaymentWithInsufficientTSABalance_TDB_RL_016(String password)throws Exception{
        HeaderChildNode("TDB-RL-016, ReLoan - Verify if user can make full repayment with insufficient TSA balance");
        tonikLogin(password);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
        String loanAmount = getText(ReloanPage.getByOSType(platform, ReloanPage.objMoneyCreditedAmount));
        loanAmount = loanCommonMethods.removeSpecialCharacter(loanAmount, false);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseLoanButton), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseLoanButton),"Button"));
        loanCommonMethods.fullRepaymentConfirmationPopup();
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt),"Page Header"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("beforeCloseLoanPage", "QuickLoanWithVas"), "Page Header");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSubTitleTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSubTitleTxt),"Page Header"));
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSubTitleTxt)), propertyFileReader("canWeTakeLookTxt", "QuickLoanWithVas"), "Page Sub Header");
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objFullRepaymentTxt)), propertyFileReader("fullRepaymentAmountTxt", "QuickLoanWithVas"), "Text");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRepaymentAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRepaymentAmount),"Repayment Amount"));
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSubjectToChangeTxt)),propertyFileReader("SubjectToChangeText","Reloan"),":Descriptions");
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBreakDownTxt)), propertyFileReader("breakDownTxt", "QuickLoanWithVas"), "Text");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanProduct)), propertyFileReader("loanProduct", "QuickLoanWithVas"), "Text");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPrincipal)),propertyFileReader("principalTxt", "QuickLoanWithVas"), "Text");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInterestTxt)), propertyFileReader("interestTxt", "QuickLoanWithVas"), "Text");
        String interestRate = getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInterestRate));
        interestRate = loanCommonMethods.removeSpecialCharacter(interestRate, false);
        String actualRepaymentAmount = getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRepaymentAmount));
        actualRepaymentAmount = loanCommonMethods.removeSpecialCharacter(actualRepaymentAmount, false);
        double repayAmount = Double.parseDouble(loanAmount) + Double.parseDouble(interestRate);
        String expectedRepayAmount = loanCommonMethods.formatToTwoDecimalPlaces(String.valueOf(repayAmount));
        String monthlyInstallment = loanCommonMethods.calculateEMI(Double.parseDouble(loanAmount), Double.parseDouble("9"), Double.parseDouble(propertyFileReader("ReloanInterest", "flexUp")));
        assertionValidation(actualRepaymentAmount, String.valueOf(Double.parseDouble(expectedRepayAmount)+Double.parseDouble(loanCommonMethods.payHinga(monthlyInstallment))), "Repayment Amount");
        String principalAmount=getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPrincipalAmount));
        principalAmount = loanCommonMethods.removeSpecialCharacter(principalAmount, false);
        assertionValidation(principalAmount, loanAmount, "Text");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNotEnoughBalanceTxt)), propertyFileReader("notEnoughBalance", "QuickLoanWithVas"), "Text");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTheOnlyWayToProcess)), propertyFileReader("noBustTxt", "QuickLoanWithVas"), "Text");
        swipe("UP",2);
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTonikAccBalTxt)), propertyFileReader("tonikAccBal", "QuickLoanWithVas"), "Text");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceAmount), "Balance Amount"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTopUpTonicAccountButton)), propertyFileReader("topUpMyTonikBtnTxt", "QuickLoanWithVas"), "Button");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextInstallmentTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextInstallmentTxt), "Loan Dashboard Screen"))) {
            logger.info("TDB-RL-016, ReLoan - Verify if user can make full repayment with insufficient TSA balance");
            extentLoggerPass("TDB-RL-016", "TDB-RL-016, ReLoan - Verify if user can make full repayment with insufficient TSA balance");
        }
    }

    /**
    * Method to Verify if user can make full repayment with sufficient TSA balance
     * @param password
     * @throws Exception
     */
    public void verifyUserCanMakeFullPaymentWithSufficientTSABalance_TDB_RL_017(String password)throws Exception{
        HeaderChildNode("TDB-RL-017, ReLoan - Verify if user can make full repayment with sufficient TSA balance");
        tonikLogin(password);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
        String loanAmount = getText(ReloanPage.getByOSType(platform, ReloanPage.objMoneyCreditedAmount));
        loanAmount = loanCommonMethods.removeSpecialCharacter(loanAmount, false);
        int topUpAmount = (int) Double.parseDouble(loanAmount);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseLoanButton), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseLoanButton),"Button"));
        loanCommonMethods.fullRepaymentConfirmationPopup();
        swipe("UP",2);
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceAmount), "Balance Amount"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTopUpTonicAccountButton), "Top up my Tonik account Button");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTopUpTonicAccountButton), "Top up my Tonik account Button");
        loanCommonMethods.topUp(String.valueOf(topUpAmount));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseLoanButton), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseLoanButton),"Button"));
        loanCommonMethods.fullRepaymentConfirmationPopup();
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceAmount), "Balance Amount"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCloseMyLoanButton), "Close My Loan Button");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCloseMyLoanButton),"Close My Loan Button");
        waitTime(3000);
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDropTheMicPage)), propertyFileReader("dropMicPageHeader", "QuickLoanWithVas"), "Page Header");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashBoardButton), "Back to Dashboard Button");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform,FlexUpPage.objHistoryBtn), "History Icon");
        String amount = loanCommonMethods.removeSpecialCharacter(getText(QuickLoanWithVasPage.objTransactionAmount(platform, "Loan paid in Full")).replaceAll("- ",""),true);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanPaidInFull),"Loan paid in Full");
        waitTime(3000);
        loanCommonMethods.transactionDetails(amount, loanCommonMethods.getAccountNumber("63"+propertyFileReader("ReLoanMobileNumber","TestDataNumbers")),propertyFileReader("LoanMoneyCreditedFrom1","QuickLoanWithVas"),"QuickLoanWithVas");
        logger.info("TDB-RL-017, ReLoan - Verify if user can make full repayment with sufficient TSA balance");
        extentLoggerPass("TDB-RL-017","TDB-RL-017, ReLoan - Verify if user can make full repayment with sufficient TSA balance validated");
    }
    /**
    * Method to Verify the loans tile status after the full loan repayment of a Re-loan
     * @param password
     * @throws Exception
     */
    public void verifyLoanTileStatusAfterFullLoanRepaymentOfReloan_TDB_RL_019(String password)throws Exception{
        HeaderChildNode("TDB-RL-019, ReLoan - Verify the loans tile status after the full loan repayment of a Re-loan");
        tonikLogin(password);
        swipe("UP",1);
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objLoanTileHeader)),propertyFileReader("LoanHeader","Reloan")," Header");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objFastCashText)),propertyFileReader("FastCashEasyPaymentsText","Reloan")," Title");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objLoanTileDesc)),propertyFileReader("AfterReLoanCloseLoanTileDesc","Reloan")," Description");
        logger.info("TDB-RL-0019, ReLoan - Verify the loan tile for a Reloan Offer");
        extentLoggerPass("TDB-RL-0019", "TDB-RL-0019, ReLoan - Verify the loan tile for a Reloan Offer");
    }
    public void reloanEndToEndJourney(String month,String vasType) throws Exception {
        HeaderChildNode("Reloan End to End Journey");
        insertReLoanOffer("63"+propertyFileReader("ReLoanMobileNumber","TestDataNumbers"));
        tonikLogin(propertyFileReader("password", "Login"));
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objLoanTileHeader)),propertyFileReader("LoanHeader","Reloan")," Header");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objLoansTileSubText1)),propertyFileReader("LoanTileSubText1","Reloan")," SubText");
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objLoansTileSubText2)),propertyFileReader("LoanTileSubText2","Reloan")," SubText");
        acceptTheReloanOffer(month,vasType);
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objYesThisMyAddressBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objYesThisMyAddressBtn), ": button"));
        assertionValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objTermsAndConditionPageHeader)),propertyFileReader("TermsAndCondition","Reloan")," Page Header");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionCheckBox),"Terms and condition check box");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIAcceptBtn),"I accept and give my consent button");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objFifthOfTheMonth), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objFifthOfTheMonth), "Radio Button"));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), "Next Button");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objConfirmBtn), "Confirm Button");
        flexUpModule.documentarySignProcessValidation();
        waitForElementDisplayed(FlexUpPage.getByOSType(platform,FlexUpPage.objPopTheChampagneTitle), 10);
        flexUpModule.popTheChampagneScreen();
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform,FlexUpPage.objCustomButtonClick), "Show me the money!");
        loanCommonMethods.iWillDoItLaterPopup();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
        iWantToCloseMyLoanPageValidation();
        loanInformationScreen("WithoutVAS");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoButton), 10, "Information Button");
        String emiAmount = getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanDashBoardEmiAmount));
        emiAmount = loanCommonMethods.removeSpecialCharacter(emiAmount, false);
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoButton), "Information Button");
        String installmentTerms = loanCommonMethods.extractNumbers(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objInstallmentTermsValue)));
        String infoScreenAmount = getText(FlexUpPage.getByOSType(platform,FlexUpPage.objLoanAmount));
        infoScreenAmount=loanCommonMethods.removeSpecialCharacter(infoScreenAmount, false);
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoScreeRateAndFees), "Rates and fees");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), 10, "Rates And Fees Page");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("ratesAndFeesTxt", "QuickLoanWithVas"), "Text");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyAddOnRate)), propertyFileReader("monthlyAddOnRateTxt", "QuickLoanWithVas"), "Text");
        String reLoanAmount = loanCommonMethods.reloanCalculation(emiAmount, installmentTerms, propertyFileReader("ReloanInterest", "Reloan"));
        double monthlyaddOnrate = loanCommonMethods.monthlyAddOnCalculation(Double.parseDouble(emiAmount), Double.parseDouble(reLoanAmount), Integer.parseInt(installmentTerms));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRatesPageMonthlyAddOnRate)), String.valueOf(monthlyaddOnrate), "Monthly add on rate", "contains");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAddOnRateSubTxt) ,getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAddOnRateSubTxt)));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objProcessingFeeTxt)), propertyFileReader("processingFeeTxt", "QuickLoanWithVas"), "Processing Fee");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRatesPageProcessingFee), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRatesPageProcessingFee), "Processing Fee Amount"));
        String processingFee=getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRatesPageProcessingFee));
        processingFee = loanCommonMethods.removeSpecialCharacter(processingFee, true);
        assertionValidation(processingFee, propertyFileReader("ProcessingFeeValue", "Reloan"), "Processing Fee");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objEffectiveInterestRate)), propertyFileReader("effectiveInterestRate", "QuickLoanWithVas"), "Text");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objEffectiveInterestRateValue), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objEffectiveInterestRate),"Effective Interest Rate"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDocStampTaxTxt), 10, "Documentary Stamp Tax");
        String expectedDocumentaryStampTaxAmount = loanCommonMethods.calculateDocumentarySummaryTax(Double.parseDouble(propertyFileReader("Month0", "Reloan")), Double.parseDouble(infoScreenAmount));
//        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDocStampTaxAmount)), expectedDocumentaryStampTaxAmount, "Documentary stamp tax", "contains");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNetProceedsTxt)), propertyFileReader("netProceeds", "QuickLoanWithVas"), "Text");
        containsValidation(getText(ReloanPage.getByOSType(platform,ReloanPage.objLateFeeTxt)).replaceAll("F","f"), propertyFileReader("LateFeeTxt", "Reloan"), "Text");
        assertionValidation(loanCommonMethods.removeSpecialCharacter(getText(ReloanPage.getByOSType(platform,ReloanPage.objLateFeeAmount)), false), propertyFileReader("LateFee", "Reloan"), "Late Fee Amount");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoButton), "Information Button");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform,FlexUpPage.objTermsAndConditionsClick), "Terms and Conditions");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform,FlexUpPage.objDownloadText), 10);
        assertionValidation(getText(FlexUpPage.getByOSType(platform,FlexUpPage.objHeader)), propertyFileReader("TermsAndConditionsText", "flexUp"), ":Header");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform,FlexUpPage.objDownloadBtn),"Download button");
        if(platform.equalsIgnoreCase("IOS")){
            waitTime(3000);
            tapOnScreen(350,100,"screen");
        }
        click(FlexUpPage.getByOSType(platform,FlexUpPage.objBackIcon), "Back Icon");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform,FlexUpPage.objPromissoryNoteClick), "Promissory Note");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform,FlexUpPage.objNoteDocument), 10);
        assertionValidation(getText(FlexUpPage.getByOSType(platform,FlexUpPage.objHeader)), propertyFileReader("PromissoryNoteText", "flexUp"), ":Header");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform,FlexUpPage.objDownloadBtn),"Download button");
        if(platform.equalsIgnoreCase("IOS")){
            waitTime(3000);
            tapOnScreen(350,100,"screen");
        }
        click(FlexUpPage.getByOSType(platform,FlexUpPage.objBackIcon), "Back Icon");
        click(FlexUpPage.getByOSType(platform,FlexUpPage.objDisclosureStatementClick), "Disclosure Statement");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform,FlexUpPage.objNoteDocument), 10);
        assertionValidation(getText(FlexUpPage.getByOSType(platform,FlexUpPage.objHeader)), propertyFileReader("DisclosureStatementText", "flexUp"), ":Header");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform,FlexUpPage.objDownloadBtn),"Download button");
        if(platform.equalsIgnoreCase("IOS")){
            waitTime(3000);
            tapOnScreen(350,100,"screen");
        }
        click(FlexUpPage.getByOSType(platform,FlexUpPage.objBackIcon), "Back Icon");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform,FlexUpPage.objAmortizationScheduleClick), "Amortization Schedule");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform,FlexUpPage.objNoteDocument), 10);
        assertionValidation(getText(FlexUpPage.getByOSType(platform,FlexUpPage.objHeader)), propertyFileReader("AmortizationScheduleText", "flexUp"), ":Header");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform,FlexUpPage.objDownloadBtn),"Download button");
        if(platform.equalsIgnoreCase("IOS")){
            tapOnScreen(300,150,"screen");
        }
        click(FlexUpPage.getByOSType(platform,FlexUpPage.objBackIcon), "Back Icon");
        logger.info("ReLoan - Reloan without VAS End to End Journey validated");
        extentLoggerPass("Reloan", "ReLoan - Reloan without VAS End to End Journey validated");
    }
}