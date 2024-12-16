package com.tonik.bizfunctions;

import com.jcraft.jsch.JSchException;
import com.tonik.pageObject.*;
import com.tonik.utility.Utilities;
import org.openqa.selenium.WebElement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.tonik.utility.DB_Utilites.insertQuery;
import static com.tonik.utility.DB_Utilites.selectQuery;
import static com.tonik.utility.ExtentReporter.*;
import static com.tonik.utility.Utilities.*;

public class FlexUpModule extends BaseClass {
    public FlexUpModule() {
        super();
    }
    String platform = Utilities.getPlatform();
    String monthlyInstallment;
    String amountYouReceive;
    List<String> paymentTerms = new ArrayList<>();
    String additionalPayment;
    LoanCommonMethods loanCommonMethods = new LoanCommonMethods();

    /**
     * Reusable method to insert Flex Pivot Offer
     * @param mobileNumber
     * @throws SQLException
     */
    public void insertFlexUpOffer(String mobileNumber) throws SQLException, JSchException {
        insertQuery("Insert into loans.tdbk_loan_offers_trx(user_id,cust_id,mobile_number,offer_start_date,offer_expired_date,offer_status,offer_type,offer_amount,offer_min_amount,offer_max_amount,min_emi_amount,max_emi_amount,min_dp_percent,status,loan_product_type,is_eligible,created_date,updated_by,interest_rate,customer_margin,tenures,file_uploaded_date,popup_triggered,max_tenure,prev_loan_acc_no,previous_loan_emi)\n" +
                "values('" + loanCommonMethods.getUserId(mobileNumber) + "','" + loanCommonMethods.getCustomerId(mobileNumber) + "','" + mobileNumber + "','" + loanCommonMethods.extractedOneDayBackDate(1) + "','" + loanCommonMethods.extractUpcomingDate(5) + "','" + propertyFileReader("offer_status", "flexUp") + "','" + propertyFileReader("offer_type", "flexUp") + "','" + propertyFileReader("offer_amount", "flexUp") + "'," +
                "'" + propertyFileReader("offer_min_amount", "flexUp") + "','" + propertyFileReader("offer_max_amount", "flexUp") + "','" + propertyFileReader("min_emi_amount", "flexUp") + "','" + propertyFileReader("max_emi_amount", "flexUp") + "','" + propertyFileReader("min_dp_percent", "flexUp") + "'," +
                "'" + propertyFileReader("status", "flexUp") + "','" + propertyFileReader("loan_product_type", "flexUp") + "','" + propertyFileReader("is_eligible", "flexUp") + "','" + loanCommonMethods.todayDate() + "','" + propertyFileReader("updated_by", "flexUp") + "','" + propertyFileReader("interest_rate", "flexUp") + "'," +
                "'" + propertyFileReader("customer_margin", "flexUp") + "','" + propertyFileReader("tenures", "flexUp") + "','" + loanCommonMethods.todayDate() + "','" + propertyFileReader("popup_triggered", "flexUp") + "'," + Integer.parseInt(propertyFileReader("max_tenure", "flexUp")) + ",'" + loanCommonMethods.getLoanAccountNumber(loanCommonMethods.getUserId(mobileNumber)) + "','" + getActiveLoanEMI(loanCommonMethods.getUserId("63" + propertyFileReader("FlexUpMobileNumber", "TestDataNumbers"))) + "');\n");
    }
    /**
     * Reusable metod to get Active Loan EMI
     * @param userId
     * @return
     * @throws JSchException
     * @throws SQLException
     */
    public static String getActiveLoanEMI(String userId) throws JSchException, SQLException {
        return selectQuery("customer", "SELECT monthlyPayment FROM loans.tdbk_digital_loan_application where userId ='" + userId + "' and applicationStatus='ACTIVATED';");
    }
    /**
     * Reusable method for Loan tile screen
     * @throws Exception
     */
    public void loansTileScreen() throws Exception {
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objLoansText), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objLoansText), "Title"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objLoansText)), propertyFileReader("LoansText", "flexUp"), ":Title");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objTodayLuckyDayHeader)), propertyFileReader("TodaysYourLuckyDay", "flexUp"), ":Header");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objSomethingExcitingDesc)), propertyFileReader("SomethingExcitingText", "flexUp"), ":Description");
    }
    /**
     * Reusable method for Next installment page
     * @throws Exception
     */
    public void nextInstallmentSreen() throws Exception {
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objNextInstallmentMessage), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objNextInstallmentMessage), "Message"));
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objPaymentDueDate), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objPaymentDueDate), "Payment date"));
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objAmountTitle), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objAmountTitle), "Amount"));
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objNextInstallmentSubMessage), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objNextInstallmentSubMessage), "Description"));
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objPaymentDone), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objPaymentDone), "Payment done"));
    }
    /**
     * Reusable method for Upgrade to flex up screen
     * @throws Exception
     */
    public void upgradeToFlexUpScreen() throws Exception {
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objFlexHeadText)), propertyFileReader("FlexUpHeader", "flexUp"), ":Header");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objFlexUpLearnText)), propertyFileReader("FlexUpLearnText", "flexUp"), ":Text");
    }
    /**
     * Reusable method for Pay hinga cup screen
     * @throws Exception
     */
    public void payHingaCupScreen() throws Exception {
        waitForElementToBePresent(FlexUpPage.getByOSType(platform, FlexUpPage.objPayHingaTitle), 3, "PayHinga title");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objPayHingaTitle)), propertyFileReader("PayHingaText", "flexUp"), ":Title");
        valueValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objPayHingaSubTitle)), propertyFileReader("LeftInstallmentText", "flexUp"), "Text", "contains");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objLearnMoreText)), propertyFileReader("LearnMoreText", "flexUp"), ":Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objLifeInsuance)), propertyFileReader("LifeInsuranceText", "flexUp"), ":Title");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objTapForMoreInfo)), propertyFileReader("TapForMoreInfoText", "flexUp"), ":Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objCoveredText)), propertyFileReader("CoveredText", "flexUp"), ":Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objCloseLoanText)), propertyFileReader("CloseLoanText", "flexUp"), ":Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objPaymentRecordText)), propertyFileReader("PaymentRecordText", "flexUp"), ":Text");
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objDateText), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objDateText), "Date"));
    }
    /**
     * Reusable method for Flex to the max screen
     * @throws Exception
     */
    public void flexToTheMaxScreen() throws Exception {
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objFlexToTheMaxHeader), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objFlexToTheMaxHeader), "Header"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objFlexToTheMaxHeader)), propertyFileReader("FlexToMaxHeader", "flexUp"), ":Header");
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objIncreaseLoanAmountDesc), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objIncreaseLoanAmountDesc), "Description"));
        assertionValidation(getText(FlexUpPage.objCustomBtn(platform, propertyFileReader("TellMeMoreText", "flexUp"))), propertyFileReader("TellMeMoreText", "flexUp"), ":Text");
    }
    /**
     * Reusable method for How it works screen
     * @throws Exception
     */
    public void howItWorksScreen() throws Exception {
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objHowItWorksHeader), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objHowItWorksHeader), "Header"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objHowItWorksHeader)), propertyFileReader("HowItWorksHeader", "flexUp"), ":Header");
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objHowItWorksDesc), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objHowItWorksDesc), "Description"));
        assertionValidation(getText(FlexUpPage.objCustomBtn(platform, propertyFileReader("GotItText", "flexUp"))), propertyFileReader("GotItText", "flexUp"), ":Text");
    }

    /**
     * Reusable method for Lets do the math screen
     * @throws Exception
     */
    public void letsDoTheMathScreen() throws Exception {
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objLetsDoTheMathHeader), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objLetsDoTheMathHeader), "Header"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objLetsDoTheMathHeader)), propertyFileReader("LetsDoMathHeader", "flexUp"), ":Header");
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objLetsDoTheMathDesc), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objLetsDoTheMathDesc), "Description"));
        assertionValidation(getText(FlexUpPage.objCustomBtn(platform, propertyFileReader("NiceWhereMyOfferText", "flexUp"))), propertyFileReader("NiceWhereMyOfferText", "flexUp"), ":Text");
    }
    /**
     * Reusable method for Flex'in the possibilities page
     * @throws Exception
     */
    public void flexinThePossibilitiesScreen() throws Exception {
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objFlexinPossibilitiesHeader), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objFlexinPossibilitiesHeader), "Header"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objFlexinPossibilitiesHeader)), propertyFileReader("FlexinThePossiblities", "flexUp"), "Header");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objSelectPaymentOptionText)).trim(), propertyFileReader("SelectPaymentOptionDesc", "flexUp"), "description");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objChooseYourPayentTermText)), propertyFileReader("ChooseYourPaymentTerm", "flexUp"), "Text");
        List<WebElement> allPaymentTerms = findElements(FlexUpPage.getByOSType(platform, FlexUpPage.objNumberOfMonths));
        for (WebElement terms : allPaymentTerms) {
            paymentTerms.add(terms.getText());
        }
        for (String terms : paymentTerms) {
            verifyElementPresentAndClick(FlexUpPage.objPaymentTerms(platform, terms), terms + " Payment Terms");
            assertionValidation(getText(FlexUpPage.objPaymentTerms(platform, terms)), terms, "Payment Terms");
            assertionValidation(getText(FlexUpPage.objPaymentAmount(platform, terms)), getText(FlexUpPage.getByOSType(platform, FlexUpPage.objAmountYouReceive)), "Value");
            assertionValidation(getText(FlexUpPage.objMonthsText(platform, terms)), propertyFileReader("MonthsText", "flexUp"), "Text");
            assertionValidation(getText(FlexUpPage.objAmountReceiveText(platform, terms)), propertyFileReader("AmountReceiveText", "flexUp"), "Text");
        }
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objLearnMoreAboutFlex)), propertyFileReader("LearnMoreAboutFlex", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objYourMonthlyInstallments)), propertyFileReader("YourMonthlyInstallments", "flexUp"), "Text");
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyInstallmentAmount), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyInstallmentAmount), "Monthly Installment Amount"));
        monthlyInstallment = getText(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyInstallmentAmount)).replace("₱", "").replace(",", "");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objAmountYouReceiveText)), propertyFileReader("AmountReceiveText", "flexUp"), "Text");
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objAmountYouReceive), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objAmountYouReceive), "Amount you'll receive"));
        amountYouReceive = getText(FlexUpPage.getByOSType(platform, FlexUpPage.objAmountYouReceive));
        verticalSwipeTillElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objContinueWithPayHingBtn));
        if(propertyFileReader("BuildNo","FlexUp").equalsIgnoreCase("6.0.2")) {
            assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objNoThanksText)), propertyFileReader("NoThanks", "flexUp"), "Text");
            assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objIAcceptTheOfferText)), propertyFileReader("IAcceptTheOffer", "flexUp"), "Text");
        }else{
            assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objUnprotectedloanBtn)), propertyFileReader("applyForButtonTxt", "QuickLoanWithVas"), "Button text");
            assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objContinueWithPayHingBtn)), propertyFileReader("continueWithpayHingaBtn", "QuickLoanWithVas"), "Button text");
        }
    }
    /**
     * Reusable method for loan summary page
     * @throws Exception
     */
    public void loanSummaryScreen() throws Exception {
        for (String terms : paymentTerms) {
            verifyElementPresentAndClick(FlexUpPage.objPaymentTerms(platform, terms), "Terms");
            if(propertyFileReader("BuildNo","FlexUp").equalsIgnoreCase("6.0.2")) {
                verticalSwipeTillElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objIAcceptTheOfferText));
                click(FlexUpPage.getByOSType(platform, FlexUpPage.objIAcceptTheOfferButton), "I accept the offer");
            }else {
                swipe("UP", 2);
                click(FlexUpPage.getByOSType(platform, FlexUpPage.objContinueWithPayHingBtn), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objContinueWithPayHingBtn), "Button"));
            }
            verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objLoanSummaryHeader), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objLoanSummaryHeader), "Header"));
            assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objLoanSummaryHeader)), propertyFileReader("LoanSummaryHeader", "flexUp"), "Header");
            assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objLoanAmountText)), propertyFileReader("LoanAmountText", "flexUp"), "Text");
            String loanAmount = loanCommonMethods.loanAmountCalculation(monthlyInstallment, terms, propertyFileReader("ReloanInterest", "flexUp"));
            String documentaryStampTax = loanCommonMethods.calculateDocumentarySummaryTax(Double.parseDouble(terms), Double.parseDouble(loanAmount));
            String outstandingBalance = getText(FlexUpPage.getByOSType(platform, FlexUpPage.objOutstandingBalanceValue)).replace("₱", "").replace(",", "");
            Double amountReceive = Double.parseDouble(loanAmount) - (Double.parseDouble(outstandingBalance) + Double.parseDouble(documentaryStampTax));
//			String monthlyInstallment = loanCommonMethods.calculateEMI(Double.parseDouble(loanAmount), Double.parseDouble(terms), Double.parseDouble(propertyFileReader("ReloanInterest", "flexUp")));
            double monthlyAddOnRate = loanCommonMethods.monthlyAddOnCalculation(Double.parseDouble(monthlyInstallment), Double.parseDouble(loanAmount), Integer.parseInt(terms));
            assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objLoanAmountValue)).replace("₱", "").replace(",", ""), loanAmount, "Loan Amount");
            assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objOutstandingBalanceText)), propertyFileReader("OutstandingBalanceText", "flexUp"), "Text");
            verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objOutstandingBalanceValue), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objOutstandingBalanceValue), "Value"));
            assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objAmountReceiveText)), propertyFileReader("AmountReceiveText", "flexUp"), "Text");
//			containsValidation(getText(FlexUpPage.getByOSType(platform,FlexUpPage.objAmountReceiveValue)).replace("₱", "").replace(",", ""), String.valueOf(LoanCommonMethods.round(amountReceive, 2)), "Value");
            assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objInstallmentTermsText)), propertyFileReader("InstallmentTermsText", "flexUp"), "Text");
            assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objInstallmentTermsValue)), terms + " Months", "Value");
            assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyAddOnRateText)), propertyFileReader("MonthlyAddOnRateText", "flexUp"), "Text");
            valueValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyAddOnRateValue)), propertyFileReader("MonthlyAddOnRate", "flexUp"), "Text", "contains");
            assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyAddOnRateValue)).split(" ")[0].substring(0, 4), String.valueOf(LoanCommonMethods.round(monthlyAddOnRate, 2)), "Value");
            verticalSwipeTillElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyInstallmentText));
            String creditLifeInsuranceAmount = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPaymentHolidayPayHingaSubTxt));
            creditLifeInsuranceAmount = loanCommonMethods.extractNumbers(creditLifeInsuranceAmount);
            creditLifeInsuranceAmount = loanCommonMethods.removeSpecialCharacter(creditLifeInsuranceAmount, false);
            creditLifeInsuranceAmount = creditLifeInsuranceAmount.replace(" ", "");
            valueValidation(creditLifeInsuranceAmount, loanCommonMethods.payHingaPaymentHolidayCalc(loanAmount), "Credit Life Insurance Amount", "contains");
            additionalPayment = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAdditionalPayHingsSubTxt));
            additionalPayment = loanCommonMethods.extractNumbers(additionalPayment);
            additionalPayment = loanCommonMethods.removeSpecialCharacter(additionalPayment, false);
            containsValidation(additionalPayment, loanCommonMethods.additionalMonthlyPayment(monthlyInstallment), "Additional monthly payment");
            containsValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyInstallmentText)), propertyFileReader("TotalMonthlyInstallmentWithPayHinga", "flexUp"), "Text");
            String monthlyInstallmentWithPayHinga = String.valueOf(Double.parseDouble(monthlyInstallment) + Double.parseDouble(loanCommonMethods.additionalMonthlyPayment(monthlyInstallment)));
            containsValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyInstallmentvalue)).replace("₱", "").replace(",", ""), monthlyInstallmentWithPayHinga, "Value");
            assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objProcessingFeeText)), propertyFileReader("ProcessingFeeText", "flexUp"), "Text");
            verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objProcessingFeeValue), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objProcessingFeeValue), "Processing Fee"));
            assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objDocumentaryStampText)), propertyFileReader("DocumentaryStampText", "flexUp"), "Text");
//			assertionValidation(getText(FlexUpPage.getByOSType(platform,FlexUpPage.objDocumentaryStampValue)).replace("₱", "").replace(",", ""), documentaryStampTax, "value");
            assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objSweetIAcceptText)), propertyFileReader("SweetIAcceptText", "flexUp"), "Text");
            verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objBackIcon), "Back Icon");
        }
    }

    /**
     * Reusable method for What do you need it for? page
     * @throws Exception
     */
    public void whatDoYouNeedItForScreen() throws Exception {
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objEducationText), 10);
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader), "Header"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader)), propertyFileReader("WhatDoYouNeedItForHeader", "flexUp"), "Header");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objSubTitle)), propertyFileReader("IWillUseTheAmountText", "flexUp"), "Sub Title");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objEducationText)), propertyFileReader("EducationText", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objMedicalOrOtherEmergencyText)), propertyFileReader("MedicalOrOtherEmergencyText", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objHouseFurnitureText)), propertyFileReader("HouseFurnitureText", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objHomeRepairImprovementText)), propertyFileReader("HomeRepairImprovementText", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objWeddingText)), propertyFileReader("WeddingText", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objHouseholdApplianceText)), propertyFileReader("HouseholdApplianceText", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objMobileAccessoriesText)), propertyFileReader("MobileAccessoriesText", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objElectronicsText)), propertyFileReader("ElectronicsText", "flexUp"), "Text");
        swipe("UP", 1);
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objTravelOrVacationText)), propertyFileReader("TravelOrVacationText", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objLivingExpenseText)), propertyFileReader("LivingExpenseText", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objDeptRepaymentText)), propertyFileReader("DeptRepaymentText", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objHelpingFamilyAndFriendText)), propertyFileReader("HelpingFamilyAndFriendText", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objOtherText)), propertyFileReader("OtherText", "flexUp"), "Text");
    }

    /**
     * Reusable method for Is this your current address? page
     * @throws Exception
     */
    public void isThisYourCurrentAddressScreen() throws Exception {
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objIHaveDifferentAddressText), 10);
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader), "Header"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader)), propertyFileReader("IsThisYourCurrentAddressHeader", "flexUp"), "Header");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objIHaveDifferentAddressText)), propertyFileReader("IHaveDifferentAddressText", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objThisIsMyCurrentAddressText)), propertyFileReader("ThisIsMyCurrentAddressText", "flexUp"), "Text");
    }

    /**
     * Reusable method for Don't forget Luv! screen
     * @throws Exception
     */
    public void dontForgetLuvScreen() throws Exception {
        waitForElementToBePresent(FlexUpPage.getByOSType(platform, FlexUpPage.objDontForgetLuvHeader), 10, "Don't forget luv header");
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objDontForgetLuvHeader), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objDontForgetLuvHeader), "Header"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objDontForgetLuvHeader)), propertyFileReader("DontForgetLuvHeader", "flexUp"), "Header");
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objDontForgetLuvDesc), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objDontForgetLuvDesc), "Descriptions"));
        assertionValidation(getText(FlexUpPage.objCustomBtn(platform, propertyFileReader("GotItText", "flexUp"))), propertyFileReader("GotItText", "flexUp"), ":Text");
    }

    /**
     * Reusable method for Monthly installment summary page
     * @param terms
     * @param subHeader
     * @throws Exception
     */
    public void monthlyInstallmentSummaryPageValidation(String terms, String subHeader) throws Exception {
        waitForElementToBePresent(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyInstallmentSummary), 15, getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyInstallmentSummary), "Page Header"));
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyInstallmentSummary), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyInstallmentSummary), "Page Header"));
        valueValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyInstallmentSummary)), propertyFileReader("monthlyInstallmentPageHeader", "flexUp"), "Page Header", "contains");
        valueValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objSubTitleTxt)), subHeader, "Sub Header", "contains");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyPaymentDueText)), propertyFileReader("MonthlyPaymentDueText", "flexUp"), "Text");
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyPaymentDueValue), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyPaymentDueValue), "Monthly Payment Due"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objDueDateText)), propertyFileReader("DueDateText", "flexUp"), "Text");
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objDueDateValue), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objDueDateValue), "Text"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objInstallmentPeriodText)), propertyFileReader("InstallmentPeriodText", "flexUp"), "Text");
        String installmentPeriod = getText(FlexUpPage.getByOSType(platform, FlexUpPage.objInstallmentPeriodValue));
        installmentPeriod = loanCommonMethods.extractNumbers(installmentPeriod);
        assertionValidation(installmentPeriod, terms, "Date");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objFirstIntallmentDDText)), propertyFileReader("FirstInstallmentDueDateText", "flexUp"), "Text");
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objFirstIntallmentDDValue), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objFirstIntallmentDDValue), "First Installment Due Date"));
        valueValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objConfirmBtn)), propertyFileReader("confirmBtn", "flexUp"), "Text", "contains");
        boolean enabledConfirmButtonButton = findElement(FlexUpPage.getByOSType(platform, FlexUpPage.objConfirmBtn)).isEnabled();
        if (enabledConfirmButtonButton) {
            logger.info("Confirm button is enabled state");
            extentLoggerPass("Month Options", "Confirm button is enabled state");
        }
    }

    /**
     * Reusable method for Pop the Champagne screen
     * @throws Exception
     */
    public void popTheChampagneScreen() throws Exception {
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objPopTheChampagneTitle), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objPopTheChampagneTitle), "Title"));
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objAmountOnPopTheChampagne), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objAmountOnPopTheChampagne), "Amount"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objCustomButtonText)), propertyFileReader("ShowMeTheMoneyText", "flexUp"), "Text");
    }

    /**
     * Reusable method for Loan information page
     * @throws Exception
     */
    public void loanInformationScreen(String loanType, String additionalPayment) throws Exception {
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objMainTitleTxt), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objMainTitleTxt), "Page Header"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objMainTitleTxt)), propertyFileReader("loanInformation", "flexUp"), "Page Header");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objLoanAmountTxt)), propertyFileReader("LoanAmountText", "flexUp"), "Text");
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objLoanAmount), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objLoanAmount), "Loan Amount"));
        String infoScreenAmount = getText(FlexUpPage.getByOSType(platform, FlexUpPage.objLoanAmount));
        infoScreenAmount = loanCommonMethods.removeSpecialCharacter(infoScreenAmount, false);
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
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenInstallmentTermsTxt)), propertyFileReader("installmentPeriodTxt", "QuickLoanWithVas"), "Text");
        }
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objInfoScreenMonthlyPaymentTxt)), propertyFileReader("monthlyPayment", "flexUp"), "Text");
        String actualMonthlyPaymentMethod = getText(FlexUpPage.getByOSType(platform, FlexUpPage.objInfoScreenMonthlyPaymentAmount));
        actualMonthlyPaymentMethod = loanCommonMethods.removeSpecialCharacter(actualMonthlyPaymentMethod, false);
        String expectedEmi = String.valueOf(Double.parseDouble(loanCommonMethods.calculateEMI(Double.parseDouble(infoScreenAmount), Double.parseDouble(installmentTerms), Double.parseDouble(propertyFileReader("ReloanInterest", "flexUp")))) + Double.parseDouble(additionalPayment));
        containsValidation(actualMonthlyPaymentMethod, expectedEmi, "Monthly Payment Amount");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objInfoScreenDueDateTxt)), propertyFileReader("DueDateText", "flexUp"), "Text");
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objInfoScreenDueDate), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objInfoScreenDueDate), "Due Date"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objInfoScreeRateAndFees)), propertyFileReader("RatesAndFeesHeader", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objInfoScreenTermsAndConditionTxt)), propertyFileReader("TermsAndConditionsText", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objInfoScreenPromissoryNoteTxt)), propertyFileReader("PromissoryNoteText", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objInfoScreenDisclosureTxt)), propertyFileReader("DisclosureStatementText", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objInfoScreenAmortizationTxt)), propertyFileReader("AmortizationScheduleText", "flexUp"), "Text");
    }

    /**
     * Reusable method for Drop the Mic! screen
     * @throws Exception
     */
    public void dropTheMicScreen() throws Exception {
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objDropTheMicText), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objDropTheMicText), ":Header"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objDropTheMicText)), propertyFileReader("DropTheMicText", "flexUp"), ":Header");
        valueValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objYourLoanFinallyPaidTitle)), propertyFileReader("YourLoanFinallyPaidTitle", "flexUp"), "Title", "contains");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objBackToDashboardText)), propertyFileReader("BackToDashboardText", "flexUp"), "Text");
    }

    /**
     * Reusable method for Are you sure? popup
     * @throws Exception
     */
    public void areYouSurePopup() throws Exception {
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objAreYouSureText), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objAreYouSureText), "Title"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objAreYouSureText)), propertyFileReader("AreYouSureText", "flexUp"), "Title");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objAreYouSureDesc)), propertyFileReader("AreYouSureDesc", "flexUp"), "Descriptions");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objOfferNotForMeText)), propertyFileReader("OfferNotForMeText", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objTakeAnotherLookText)), propertyFileReader("TakeAnotherLookText", "flexUp"), "Text");
    }
    /**
     * Reusable method for Sorry it was not match screen
     * @throws Exception
     */
    public void sorryItWasNotMatchScreen() throws Exception {
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objINeedHelpText), 10);
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader), "Header"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader)), propertyFileReader("SorryItWasNotMatchHeader", "flexUp"), "Header");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objSubTitle)), propertyFileReader("SorryItWasNotMatchSubTitle", "flexUp"), "Sub Title");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objINeedHelpText)), propertyFileReader("INeedHelpText", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objTheAmountText)), propertyFileReader("TheAmountText", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objNewLoanRightNowText)), propertyFileReader("NewLoanRightNowText", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objOtherReasonText)), propertyFileReader("OtherReasonText", "flexUp"), "Text");
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objINeedHelpText), "I need help!");
        verifyElementEnabled(FlexUpPage.getByOSType(platform, FlexUpPage.objNextBtnEnabled), "Next button");
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objTheAmountText), "The amount doesn’t make sense for me");
        verifyElementEnabled(FlexUpPage.getByOSType(platform, FlexUpPage.objNextBtnEnabled), "Next button");
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objNewLoanRightNowText), "I don’t want a new loan right now");
        verifyElementEnabled(FlexUpPage.getByOSType(platform, FlexUpPage.objNextBtnEnabled), "Next button");
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objOtherReasonText), "I have other reasons");
        verifyElementEnabled(FlexUpPage.getByOSType(platform, FlexUpPage.objNextBtnEnabled), "Next button");
    }
    /**
     * Reusable method to verify if user can accept and disburse the flex up offer
     * @throws Exception
     */
    public void verifyUserCanAcceptAndDisburseTheFlexUpOffer() throws Exception {
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objLoansTile), "Loan Tile");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objFlexUpTileButton), "Upgrade To Flex Up");
        waitTime(6000);
        swipe("LEFT", 1);
        verifyElementPresentAndClick(FlexUpPage.objCustomBtn(platform, propertyFileReader("NiceWhereMyOfferText", "flexUp")), "Nice! Where's my offer");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objLearnMoreAboutFlex), 10);
        monthlyInstallment = getText(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyInstallmentAmount));
        verticalSwipeTillElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objContinueWithPayHingBtn));
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objContinueWithPayHingBtn), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objContinueWithPayHingBtn), "Button"));

        click(FlexUpPage.getByOSType(platform, FlexUpPage.objSweetIAcceptButton), "Sweet I Accept Button");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objElectronicsText), "Electronics");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objNextButton), "Next button");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objThisIsMyCurrentAddressBtn), 10);
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objThisIsMyCurrentAddressBtn), "Yes, this is my current address");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objTermsConditionsCheck), 10);
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objTermsConditionsCheck), "Terms & Conditions checkbox");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objAcceptAndGiveConsentBtn), "I accept and give my consent");
        dontForgetLuvScreen();
        verifyElementPresentAndClick(FlexUpPage.objCustomBtn(platform, propertyFileReader("GotItText", "flexUp")), "Got it");
        monthlyInstallmentSummaryPageValidation(propertyFileReader("InstallmentTerms", "flexUp"), propertyFileReader("monthlyPageSubHeader", "flexUp"));
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objConfirmBtn), "Confirm Button");
        loanCommonMethods.signedSealedDeliveredPageValidation();
        waitTime(3000);
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objSignPromissoryNoteBtn), "Sign Promissory Note Button");
        loanCommonMethods.promissoryNotePageValidation();
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objReadyToSignButton), "I am ready to sign");
        loanCommonMethods.yourSignatureRequiredPageValidation();
        updateSignature();
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objSignPromissoryNoteBtn), "Sign Promissory Note Button");
        loanCommonMethods.disclosureStatementPageValidation();
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objReadyToSignButton), "I am ready to sign");
        updateSignature();
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objSignDisclosureStatementBtn), "Sign Disclosure Statement Button");
        loanCommonMethods.amortizationScheduleValidation();
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objReadyToSignButton), "I am ready to sign");
        updateSignature();
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objSignSignAmortizationBtn), "Sign Amortization Schedule");
        waitTime(2000);
        loanCommonMethods.yourLoanAlmostReadyPageValidation();
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objPopTheChampagneTitle), 10);
        popTheChampagneScreen();
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objCustomButtonClick), "Show me the money!");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objLoansTile), 10);
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objLoansTile), "Yes, yes, yes! Loan Tile");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objLoanInfoIcon), "Loan Info Icon");
        loanInformationScreen("WithVAS", additionalPayment);
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objRatesAndFeesClick), "Rates and fees");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objRatesAndFeesHeader), 10);
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objRatesAndFeesHeader)), propertyFileReader("RatesAndFeesHeader", "flexUp"), ":Header");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objBackIcon), "Back Icon");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objTermsAndConditionsClick), "Terms and Conditions");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objDownloadText), 10);
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader)), propertyFileReader("TermsAndConditionsText", "flexUp"), ":Header");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objDownloadBtn), "Download button");
        if (platform.equalsIgnoreCase("IOS")) {
            waitTime(5000);
            tapOnScreen(350, 100, "screen");
        }
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objBackIcon), "Back Icon");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objPromissoryNoteClick), "Promissory Note");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objNoteDocument), 10);
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader)), propertyFileReader("PromissoryNoteText", "flexUp"), ":Header");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objDownloadBtn), "Download button");
        if (platform.equalsIgnoreCase("IOS")) {
            waitTime(5000);
            tapOnScreen(350, 100, "screen");
        }
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objBackIcon), "Back Icon");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objDisclosureStatementClick), "Disclosure Statement");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objNoteDocument), 10);
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader)), propertyFileReader("DisclosureStatementText", "flexUp"), ":Header");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objDownloadBtn), "Download button");
        if (platform.equalsIgnoreCase("IOS")) {
            waitTime(5000);
            tapOnScreen(350, 100, "screen");
        }
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objBackIcon), "Back Icon");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objAmortizationScheduleClick), "Amortization Schedule");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objNoteDocument), 10);
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader)), propertyFileReader("AmortizationScheduleText", "flexUp"), ":Header");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objDownloadBtn), "Download button");
        if (platform.equalsIgnoreCase("IOS")) {
            waitTime(5000);
            tapOnScreen(300, 150, "screen");
        }
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objBackIcon), "Back Icon");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objDateBookedText), 10);
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objBackIcon), "Back Icon");
        waitTime(3000);
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objCloseLoanText)), propertyFileReader("CloseMyLoanText", "flexUp"), ":Header");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objCloseLoanText), "I want to close my loan");
        waitTime(3000);
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader)), propertyFileReader("BeforeWeCloseThisLoanHeader", "flexUp"), ":Header");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objCloseMyLoanBtn), "Close My Loan");
        dropTheMicScreen();
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objBackToDashboardBtn), "Back to dashboard");
    }
    /**
     * Reusable method to verify user can decline flex up offer
     * @throws Exception
     */
    public void verifyUserCanDeclineFlexUpOffer() throws Exception {
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objLoansTile), "Loan Tile");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objFlexUpTileButton), "Upgrade To Flex Up");
        waitForElementToBePresent(FlexUpPage.objCustomBtn(platform, propertyFileReader("GotItText", "flexUp")), 5, "Got it button");
        verifyElementPresentAndClick(FlexUpPage.objCustomBtn(platform, propertyFileReader("GotItText", "flexUp")), "Got it");
        verifyElementPresentAndClick(FlexUpPage.objCustomBtn(platform, propertyFileReader("NiceWhereMyOfferText", "flexUp")), "Nice! Where's my offer");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objNoThanksText), 10);
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objNoThanksText), "No Thanks");
        areYouSurePopup();
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objTakeAnotherLookBtn), "I’ll take another look");
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objFlexinPossibilitiesHeader), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objFlexinPossibilitiesHeader), "Header"));
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objNoThanksLink), "No Thanks");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objOfferNotForMeBtn), "The offer’s not for me");
        sorryItWasNotMatchScreen();
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objNextBtn), "Next");
    }
    /**
     * Reusable method to verify I want to close my loan page
     * @throws Exception
     */
    public void iWantToCloseMyLoanPageValidation() throws Exception {
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNextInstallmentTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNextInstallmentTxt), "Text"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanDashBoardDueDate), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanDashBoardDueDate), "Text"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanDashBoardEmiAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanDashBoardEmiAmount), "EMI Amount"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objThisAmountMessage), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objThisAmountMessage), "Text"));
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objThisAmountMessage)), propertyFileReader("thisAmountMessage", "QuickLoanWithVas"), "Text");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPaymentDoneOf), getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPaymentDoneOf)));
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPaymentDoneOf)), propertyFileReader("paymentDoneOf", "Reloan"), "Text");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoButton), "Info Button");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIwantToCloseLoanButton), "I want to close my loan");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIwantToCloseLoanButton)), propertyFileReader("iWantToCloseLoanBtn", "QuickLoanWithVas"), "Button");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPaymentRecordTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPaymentRecordTxt), "Text"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPaymentRecordTxt)), propertyFileReader("paymentRecord", "QuickLoanWithVas"), "Button");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTodaysDate), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTodaysDate), "Date"));
        String todaysDateActual = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTodaysDate)).replace("Yesterday,", "").trim();
        containsValidation(todaysDateActual, loanCommonMethods.todaysDate(), "Date");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMoneyCredited), "Money Credited Text");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMoneyCreditedAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMoneyCreditedAmount), "Amount"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFundTransferredTo), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFundTransferredTo), "Text"));
    }
    /**
     * Reusable method to validate all the documentary sign process
     * @throws Exception
     */
    public void documentarySignProcessValidation() throws Exception {
        waitTime(3000);
        loanCommonMethods.signedSealedDeliveredPageValidation();
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignPromissoryNoteBtn), "Sign Promissory Note Button");
        waitTime(4000);
        loanCommonMethods.promissoryNotePageValidation();
        waitTime(3000);
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
        loanCommonMethods.signedSealedDeliveredPageValidation();
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignPromissoryNoteBtn), "Sign Promissory Note Button");
        waitTime(10000);
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign");
        loanCommonMethods.yourSignatureRequiredPageValidation();
        waitTime(3000);
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
        loanCommonMethods.signedSealedDeliveredPageValidation();
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignPromissoryNoteBtn), "Sign Promissory Note Button");
        waitTime(10000);
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign Button");
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objConatctUsBtn), "Contact Us Button");
        contactUsPageUI();
        waitTime(3000);
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
        updateSignature();
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objEraseBtn), "Erase Button");
        updateSignature();
        isButtonEnabled(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignPromissoryNoteBtn), "Sign Promissory Note Button");
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignPromissoryNoteBtn), "Sign Promissory Note Button");
        waitTime(5000);
        loanCommonMethods.disclosureStatementPageValidation();
        waitTime(3000);
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign Button");
        waitTime(3000);
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), "Page"));
        isButtonEnabled(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignDisclosureStatementBtn), "Sign Disclosure Statement Button");
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignDisclosureStatementBtn), "Sign Disclosure Statement Button");
        waitTime(5000);
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign Button");
        waitTime(3000);
        loanCommonMethods.yourSignatureRequiredPageValidation();
        waitTime(3000);
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
        waitTime(3000);
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), "Page"));
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignDisclosureStatementBtn), "Sign Disclosure Statement Button");
        waitTime(8000);
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign Button");
        waitTime(2000);
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContactUsButton), "Contact Us Button");
        waitTime(3000);
        contactUsPageUI();
        waitTime(3000);
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
        updateSignature();
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objEraseBtn), "Erase Button");
        updateSignature();
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignDisclosureStatementBtn), "Sign Disclosure Statement Button");
        waitTime(3000);
        loanCommonMethods.amortizationScheduleValidation();
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
        waitTime(3000);
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), "Page"));
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignAmortizationScheduleBtn), "Sign Amortization Schedule");
        waitTime(2000);
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign Button");
        waitTime(3000);
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("yourSignatureTxt", "QuickLoanWithVas"), "Page Header");
        waitTime(3000);
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
        waitTime(3000);
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), "Page"));
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignAmortizationScheduleBtn), "Sign Amortization Schedule");
        waitTime(5000);
        loanCommonMethods.amortizationScheduleValidation();
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign Button");
        waitTime(3000);
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objConatctUsBtn), "Contact Us Button");
        waitTime(3000);
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
        waitTime(3000);
        updateSignature();
        waitTime(3000);
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignAmortizationScheduleBtn), "Sign Amortization Schedule");
        waitTime(3000);
    }
    /**
     * Method to Verify the loan tile for the Flex Up Offer
     * @throws Exception
     */
    public void verifyLoanTileForFlexUpOffer_TDB_FU_001() throws Exception {
        HeaderChildNode("TDB_FU_001, FlexUpLoan -Verify the loan tile for the Flex Up Offer - TDB_FU_001");
        insertFlexUpOffer("63" + propertyFileReader("FlexUpMobileNumber", "TestDataNumbers"));
        tonikLogin(propertyFileReader("password", "Login"));
        loansTileScreen();
        logger.info("TDB_FU_001, FlexUpLoan - verifyLoanTileForFlexUpOffer_TDB_FU_001");
        extentLoggerPass("TDB_FU_001", "TDB_FU_001, FlexUpLoan - verifyLoanTileForFlexUpOffer_TDB_FU_001 is passed");
    }
    /**
     * Method to Verify the Flex Up sales screen
     * @throws Exception
     */
    public void verifyFlexUpSalesScreen_TDB_FU_003() throws Exception {
        HeaderChildNode("TDB_FU_003, FlexUpLoan -Verify the Flex Up sales screen - TDB_FU_003");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objLoansTile), "Loan Tile");
        nextInstallmentSreen();
        upgradeToFlexUpScreen();
//		payHingaCupScreen();
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objFlexUpTileButton), "Upgrade To Flex Up");
//		flexToTheMaxScreen();
//		verifyElementPresentAndClick(FlexUpPage.objCustomBtn(platform,propertyFileReader("TellMeMoreText", "flexUp")), "Tell me more");
        howItWorksScreen();
        verifyElementPresentAndClick(FlexUpPage.objCustomBtn(platform, propertyFileReader("GotItText", "flexUp")), "Got it");
        letsDoTheMathScreen();
        verifyElementPresentAndClick(FlexUpPage.objCustomBtn(platform, propertyFileReader("NiceWhereMyOfferText", "flexUp")), "Nice! Where's my offer");
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objFlexinPossibilitiesHeader), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objFlexinPossibilitiesHeader), "Header"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objFlexinPossibilitiesHeader)), propertyFileReader("FlexinThePossiblities", "flexUp"), "Header");
        logger.info("TDB_FU_003, FlexUpLoan - verifyFlexUpSalesScreen_TDB_FU_003");
        extentLoggerPass("TDB_FU_003", "TDB_FU_003, FlexUpLoan - verifyFlexUpSalesScreen_TDB_FU_003 is passed");
    }
    /**
     * Method to Verify if user can accept the Flex Up offer
     * @throws Exception
     */
    public void verifyUserCanAcceptFlexUpOffer_TDB_FU_005() throws Exception {
        HeaderChildNode("TDB_FU_005, FlexUpLoan -Verify if user can accept the Flex Up offer - TDB_FU_005");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objLoansTile), "Loan Tile");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objFlexUpTileButton), "Upgrade To Flex Up");
        waitForElementToBePresent(FlexUpPage.objCustomBtn(platform, propertyFileReader("GotItText", "flexUp")), 5, "Got it button");
        verifyElementPresentAndClick(FlexUpPage.objCustomBtn(platform, propertyFileReader("GotItText", "flexUp")), "Got it");
        verifyElementPresentAndClick(FlexUpPage.objCustomBtn(platform, propertyFileReader("NiceWhereMyOfferText", "flexUp")), "Nice! Where's my offer");
        flexinThePossibilitiesScreen();
        if (platform.equalsIgnoreCase("Android")) {
            verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objLearnMoreAboutFlex), "Learn more about Flex Up computations");
            waitTime(3000);
            Back(1);
        }
        loanSummaryScreen();
        swipe("DOWN", 1);
        logger.info("TDB_FU_005, FlexUpLoan - verifyUserCanAcceptFlexUpOffer_TDB_FU_005");
        extentLoggerPass("TDB_FU_005", "TDB_FU_005, FlexUpLoan - verifyUserCanAcceptFlexUpOffer_TDB_FU_005 is passed");
    }
    /**
     * Method to Verify if user can accept the loan from Loan Summary screen
     * @throws Exception
     */
    public void verifyUserCanAcceptLoanFromLoanSummaryScreen_TDB_FU_006() throws Exception {
        HeaderChildNode("TDB_FU_006, FlexUpLoan -Verify if user can accept the loan from Loan Summary screen - TDB_FU_006");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objLoansTile), "Loan Tile");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objFlexUpTileButton), "Upgrade To Flex Up");
        waitForElementToBePresent(FlexUpPage.objCustomBtn(platform, propertyFileReader("GotItText", "flexUp")), 5, "Got it button");
        verifyElementPresentAndClick(FlexUpPage.objCustomBtn(platform, propertyFileReader("GotItText", "flexUp")), "Got it");
        swipe("LEFT", 1);
        verifyElementPresentAndClick(FlexUpPage.objCustomBtn(platform, propertyFileReader("NiceWhereMyOfferText", "flexUp")), "Nice! Where's my offer");
        if(propertyFileReader("BuildNo","FlexUp").equalsIgnoreCase("6.0.2")) {
            verticalSwipeTillElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objIAcceptTheOfferText));
            click(FlexUpPage.getByOSType(platform, FlexUpPage.objIAcceptTheOfferButton), "I accept the offer");
        }else {
            verticalSwipeTillElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objContinueWithPayHingBtn));
            click(FlexUpPage.getByOSType(platform, FlexUpPage.objContinueWithPayHingBtn), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objContinueWithPayHingBtn), "Button"));
        }
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objSweetIAcceptButton), "Sweet I Accept Button");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objEducationText), 10);
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader), "Header"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader)), propertyFileReader("WhatDoYouNeedItForHeader", "flexUp"), "Header");
        logger.info("TDB_FU_006, FlexUpLoan - verifyUserCanAcceptLoanFromLoanSummaryScreen_TDB_FU_006");
        extentLoggerPass("TDB_FU_006", "TDB_FU_006, FlexUpLoan - verifyUserCanAcceptLoanFromLoanSummaryScreen_TDB_FU_006 is passed");
    }
    /**
     * Method to Verify if user can select a Flex Up reason
     * @throws Exception
     */
    public void verifyUserCanSelectAFlexUpReason_TDB_FU_007() throws Exception {
        HeaderChildNode("TDB_FU_007, FlexUpLoan -Verify if user can select a Flex Up reason - TDB_FU_007");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objLoansTile), "Loan Tile");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objFlexUpTileButton), "Upgrade To Flex Up");
        waitForElementToBePresent(FlexUpPage.objCustomBtn(platform, propertyFileReader("GotItText", "flexUp")), 5, "Got it button");
        verifyElementPresentAndClick(FlexUpPage.objCustomBtn(platform, propertyFileReader("GotItText", "flexUp")), "Got it");
        verifyElementPresentAndClick(FlexUpPage.objCustomBtn(platform, propertyFileReader("NiceWhereMyOfferText", "flexUp")), "Nice! Where's my offer");
        verticalSwipeTillElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objContinueWithPayHingBtn));
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objContinueWithPayHingBtn), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objContinueWithPayHingBtn), "Button"));
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objSweetIAcceptButton), "Sweet I Accept Button");
        whatDoYouNeedItForScreen();
        verifyElementDisabled(FlexUpPage.getByOSType(platform, FlexUpPage.objNextButtonDisabled), "Next button");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objElectronicsText), "Electronics");
        verifyElementEnabled(FlexUpPage.getByOSType(platform, FlexUpPage.objNextButtonEnabled), "Next button");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objNextButton), "Next button");
        isThisYourCurrentAddressScreen();
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objThisIsMyCurrentAddressBtn), "Yes, this is my current address");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objAcceptAndGiveConsentText), 10);
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader), "Header"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader)), propertyFileReader("TermsAndConditionsText", "flexUp"), "Header");
        logger.info("TDB_FU_007, FlexUpLoan - verifyUserCanSelectAFlexUpReason_TDB_FU_007");
        extentLoggerPass("TDB_FU_007", "TDB_FU_007, FlexUpLoan - verifyUserCanSelectAFlexUpReason_TDB_FU_007 is passed");
    }
    /**
     * Method to Verify if ongoing Flex loan user can accept & disburse the Flex Up offer
     * @throws Exception
     */
    public void verifyOngoingFlexLoanUserCanAcceptAndDisburseFlexUpOffer_TDB_FU_026() throws Exception {
        HeaderChildNode("TDB_FU_026, FlexUpLoan -Verify if ongoing Flex loan user can accept & disburse the Flex Up offer - TDB_FU_026");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyUserCanAcceptAndDisburseTheFlexUpOffer();
        if (verifyElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objYourTonikAccount))) {
            logger.info("TDB_FU_026, FlexUpLoan - verifyOngoingFlexLoanUserCanAcceptAndDisburseFlexUpOffer_TDB_FU_026");
            extentLoggerPass("TDB_FU_026", "TDB_FU_026, FlexUpLoan - verifyOngoingFlexLoanUserCanAcceptAndDisburseFlexUpOffer_TDB_FU_026 is passed");
        }
    }
    /**
     * Method to Verify if ongoing SIL loan user can accept & disburse the Flex Up offer
     * @throws Exception
     */
    public void verifyOngoingSILLoanUserCanAcceptAndDisburseFlexUpOffer_TDB_FU_027() throws Exception {
        HeaderChildNode("TDB_FU_027, FlexUpLoan -Verify if ongoing SIL loan user can accept & disburse the Flex Up offer - TDB_FU_027");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyUserCanAcceptAndDisburseTheFlexUpOffer();
        if (verifyElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objYourTonikAccount))) {
            logger.info("TDB_FU_027, FlexUpLoan - verifyOngoingSILLoanUserCanAcceptAndDisburseFlexUpOffer_TDB_FU_027");
            extentLoggerPass("TDB_FU_027", "TDB_FU_027, FlexUpLoan - verifyOngoingSILLoanUserCanAcceptAndDisburseFlexUpOffer_TDB_FU_027 is passed");
        }
    }
    /**
     * Method to Verify if ongoing Flex up loan user can accept & disburse the Flex Up offer
     * @throws Exception
     */
    public void verifyOngoingFlexUpLoanUserCanAcceptAndDisburseFlexUpOffer_TDB_FU_028() throws Exception {
        HeaderChildNode("TDB_FU_028, FlexUpLoan -Verify if ongoing Flex up loan user can accept & disburse the Flex Up offer - TDB_FU_028");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyUserCanAcceptAndDisburseTheFlexUpOffer();
        if (verifyElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objYourTonikAccount))) {
            logger.info("TDB_FU_028, FlexUpLoan - verifyOngoingFlexUpLoanUserCanAcceptAndDisburseFlexUpOffer_TDB_FU_028");
            extentLoggerPass("TDB_FU_028", "TDB_FU_028, FlexUpLoan - verifyOngoingFlexUpLoanUserCanAcceptAndDisburseFlexUpOffer_TDB_FU_028 is passed");
        }
    }
    /**
     * Method to Verify if user can decline the Flex up offer
     * @throws Exception
     */
    public void verifyUserCanDeclineFlexUpOffer_TDB_FU_004() throws Exception {
        HeaderChildNode("TDB_FU_004, FlexUpLoan -Verify if user can decline the Flex up offer - TDB_FU_004");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyUserCanDeclineFlexUpOffer();
        if (verifyElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objYourTonikAccount))) {
            logger.info("TDB_FU_004, FlexUpLoan - verifyUserCanDeclineFlexUpOffer_TDB_FU_004");
            extentLoggerPass("TDB_FU_004", "TDB_FU_004, FlexUpLoan - verifyUserCanDeclineFlexUpOffer_TDB_FU_004 is passed");
        }
    }
    /**
     * Method to Verify if user can decline the Flex up offer (Ongoing Flex Loan)
     * @throws Exception
     */
    public void verifyUserCanDeclineFlexUpOfferOngoingFlexLoan_TDB_FU_024() throws Exception {
        HeaderChildNode("TDB_FU_024, FlexUpLoan -Verify if user can decline the Flex up offer (Ongoing Flex Loan) - TDB_FU_024");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyUserCanDeclineFlexUpOffer();
        if (verifyElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objYourTonikAccount))) {
            logger.info("TDB_FU_024, FlexUpLoan - verifyUserCanDeclineFlexUpOfferOngoingFlexLoan_TDB_FU_024");
            extentLoggerPass("TDB_FU_024", "TDB_FU_024, FlexUpLoan - verifyUserCanDeclineFlexUpOfferOngoingFlexLoan_TDB_FU_024 is passed");
        }
    }
    /**
     * Method to Verify if user can accept the Terms and Conditions
     * @param loanPurpose
     * @throws Exception
     */
    public void verifyIfUserCanAcceptTermsAndConditions_TDB_FU_008(String loanPurpose) throws Exception {
        HeaderChildNode("TDB_FU_008, FlexUpLoan - Verify if user can accept the Terms and Conditions");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objLoansTile), "Loan Tile");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objFlexUpTileButton), "Upgrade To Flex Up");
        waitForElementToBePresent(FlexUpPage.objCustomBtn(platform, propertyFileReader("GotItText", "flexUp")), 5, "Got it button");
        verifyElementPresentAndClick(FlexUpPage.objCustomBtn(platform, propertyFileReader("GotItText", "flexUp")), "Got it");
        verifyElementPresentAndClick(FlexUpPage.objCustomBtn(platform, propertyFileReader("NiceWhereMyOfferText", "flexUp")), "Nice! Where's my offer");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objLearnMoreAboutFlex), 10);
        swipe("UP", 1);
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objContinueWithPayHingBtn), getText(FlexUpPage.getByOSType(platform, FlexUpPage.objContinueWithPayHingBtn)));
        assertionValidation(getText(ReloanPage.getByOSType(platform, ReloanPage.objLoanSummaryHeader)), propertyFileReader("SummaryLoan", "Reloan"), " Header");
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform, ReloanPage.objSweetIAcceptText), getText(ReloanPage.getByOSType(platform, ReloanPage.objSweetIAcceptText)));
        verifyElementPresentAndClick(ReloanPage.objLoanPurpose(platform, loanPurpose), loanPurpose);
        assertionValidation(getAttributValue("enabled", ReloanPage.getByOSType(platform, ReloanPage.objNextButton)), "true", ": Enable Attribute value");
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform, ReloanPage.objNextButton), " Next Button");
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform, ReloanPage.objIsThisYourCurrentAddressButton), "Is This Your CurrentAddress Button");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTermsAndConditionScreen), 5, "Terms and Condition Screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTermsAndConditionScreen), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTermsAndConditionScreen), ": page"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTermsAndConditionSubtitle)), propertyFileReader("TermsAndConditionScreenSubtitle", "QuickLoanWithVas"), "Page Subtitle");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objDownloadTermsAndConditionBtn), "Download Terms and condition button");
        if (platform.equalsIgnoreCase("ios")) {
            waitTime(3000);
            tapOnScreen(350, 200, "screen");
        }
        waitTime(3000);
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objBackBtn), "Back button");
        waitForElementToBePresent(ReloanPage.getByOSType(platform, ReloanPage.objIsThisYourCurrentAddressButton), 10, "Is This Your CurrentAddress Button");
        click(ReloanPage.getByOSType(platform, ReloanPage.objIsThisYourCurrentAddressButton), "Is This Your CurrentAddress Button");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTermsAndConditionScreen), 5, "Terms and Condition Screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTermsAndConditionScreen), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTermsAndConditionScreen), ": page"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTermsAndConditionSubtitle)), propertyFileReader("TermsAndConditionScreenSubtitle", "QuickLoanWithVas"), "Page Subtitle");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objDownloadTermsAndConditionBtn), "Download Terms and condition button");
        if (platform.equalsIgnoreCase("ios")) {
            waitTime(3000);
            tapOnScreen(350, 200, "screen");
        }
        assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objDownloadTermsAndConditionBtn)), "true", "Attribute value");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTermsAndConditionCheckBox), "Terms and condition check box");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIAcceptBtn), "I accept and give my consent button");
        waitTime(6000);
        dontForgetLuvScreen();
        logger.info("TDB_FU_008, FlexUpLoan - Verify if user can accept the Terms and Conditions");
        extentLoggerPass("TDB_FU_008", "TDB_FU_008, FlexUpLoan - Verify if user can accept the Terms and Conditions");
    }
    /**
     * Method to Verify if user can confirm the Monthly Installment summary
     * @param loanPurpose
     * @throws Exception
     */
    public void verifyUserCanConfirmMonthlyInstallmentSummary_TDB_FU_009(String loanPurpose) throws Exception {
        HeaderChildNode("TDB_FU_009, FlexUpLoan - Verify if user can confirm the Monthly Installment summary");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objLoansTile), "Loan Tile");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objFlexUpTileButton), "Upgrade To Flex Up");
        waitTime(4000);
        for (int swipe = 0; swipe <= 3; swipe++) {
            if (verifyElementDisplayed(FlexUpPage.objCustomBtn(platform, propertyFileReader("NiceWhereMyOfferText", "flexUp")))) {
                click(FlexUpPage.objCustomBtn(platform, propertyFileReader("NiceWhereMyOfferText", "flexUp")), "Nice! Where's my offer");
                break;
            } else {
                swipe("LEFT", 2);
            }
        }
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objLearnMoreAboutFlex), 10);
        monthlyInstallment = getText(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyInstallmentAmount));
        swipe("UP", 1);
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objContinueWithPayHingBtn), getText(FlexUpPage.getByOSType(platform, FlexUpPage.objContinueWithPayHingBtn)));
        assertionValidation(getText(ReloanPage.getByOSType(platform, ReloanPage.objLoanSummaryHeader)), propertyFileReader("SummaryLoan", "Reloan"), " Header");
        String installmentTerms = getText(FlexUpPage.getByOSType(platform, FlexUpPage.objInstallmentTermsValue));
        installmentTerms = loanCommonMethods.extractNumbers(installmentTerms);
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform, ReloanPage.objSweetIAcceptText), getText(ReloanPage.getByOSType(platform, ReloanPage.objSweetIAcceptText)));
        verifyElementPresentAndClick(ReloanPage.objLoanPurpose(platform, loanPurpose), loanPurpose);
        assertionValidation(getAttributValue("enabled", ReloanPage.getByOSType(platform, ReloanPage.objNextButton)), "true", ": Enable Attribute value");
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform, ReloanPage.objNextButton), " Next Button");
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform, ReloanPage.objIsThisYourCurrentAddressButton), "Is This Your CurrentAddress Button");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTermsAndConditionCheckBox), "Terms and condition check box");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIAcceptBtn), "I accept and give my consent button");
        waitTime(6000);
        dontForgetLuvScreen();
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objBackIcon), "Back Icon");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIAcceptBtn), "I accept and give my consent button");
        verifyElementPresentAndClick(FlexUpPage.objCustomBtn(platform, propertyFileReader("GotItText", "flexUp")), "Got it");
        monthlyInstallmentSummaryPageValidation(installmentTerms, propertyFileReader("monthlyPageSubHeader", "flexUp"));
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objConatctUsBtn), "Contact Us Button");
        waitTime(3000);
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
        waitForElementToBePresent(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyInstallmentSummary), 15, getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyInstallmentSummary), "Page Header"));
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyInstallmentSummary), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyInstallmentSummary), "Page Header"));
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objConfirmBtn), "Confirm Button");
        loanCommonMethods.signedSealedDeliveredPageValidation();
        logger.info("TDB_FU_009, FlexUpLoan - Verify if user can confirm the Monthly Installment summary");
        extentLoggerPass("TDB_FU_009", "TDB_FU_009, FlexUpLoan - Verify if user can confirm the Monthly Installment summary");
    }
    /**
     * Method to Verify if user can sign all the Loan documents
     * @param loanPurpose
     * @throws Exception
     */
    public void verifyUserCanSignAllTheLoanDocuments_TDB_FU_010(String loanPurpose) throws Exception {
        HeaderChildNode("TDB_FU_010, FlexUpLoan - Verify if user can sign all the Loan documents");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objLoansTile), "Loan Tile");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objFlexUpTileButton), "Upgrade To Flex Up");
        waitTime(4000);
        for (int swipe = 0; swipe <= 3; swipe++) {
            if (verifyElementDisplayed(FlexUpPage.objCustomBtn(platform, propertyFileReader("NiceWhereMyOfferText", "flexUp")))) {
                click(FlexUpPage.objCustomBtn(platform, propertyFileReader("NiceWhereMyOfferText", "flexUp")), "Nice! Where's my offer");
                break;
            } else {
                swipe("LEFT", 2);
            }
        }
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objLearnMoreAboutFlex), 10);
        monthlyInstallment = getText(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyInstallmentAmount));
        swipe("UP", 1);
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objContinueWithPayHingBtn), getText(FlexUpPage.getByOSType(platform, FlexUpPage.objContinueWithPayHingBtn)));
        assertionValidation(getText(ReloanPage.getByOSType(platform, ReloanPage.objLoanSummaryHeader)), propertyFileReader("SummaryLoan", "Reloan"), " Header");
        String installmentTerms = getText(FlexUpPage.getByOSType(platform, FlexUpPage.objInstallmentTermsValue));
        installmentTerms = loanCommonMethods.extractNumbers(installmentTerms);
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform, ReloanPage.objSweetIAcceptText), getText(ReloanPage.getByOSType(platform, ReloanPage.objSweetIAcceptText)));
        verifyElementPresentAndClick(ReloanPage.objLoanPurpose(platform, loanPurpose), loanPurpose);
        assertionValidation(getAttributValue("enabled", ReloanPage.getByOSType(platform, ReloanPage.objNextButton)), "true", ": Enable Attribute value");
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform, ReloanPage.objNextButton), " Next Button");
        waitForElementDisplayed(ReloanPage.getByOSType(platform, ReloanPage.objIsThisYourCurrentAddressButton), 10);
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform, ReloanPage.objIsThisYourCurrentAddressButton), "Is This Your CurrentAddress Button");
        waitForElementDisplayed(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTermsAndConditionCheckBox), 10);
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTermsAndConditionCheckBox), "Terms and condition check box");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIAcceptBtn), "I accept and give my consent button");
        waitTime(6000);
        dontForgetLuvScreen();
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objBackIcon), "Back Icon");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIAcceptBtn), "I accept and give my consent button");
        verifyElementPresentAndClick(FlexUpPage.objCustomBtn(platform, propertyFileReader("GotItText", "flexUp")), "Got it");
        monthlyInstallmentSummaryPageValidation(installmentTerms, propertyFileReader("monthlyPageSubHeader", "flexUp"));
        waitTime(2000);
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objConfirmBtn), "Confirm Button");
        documentarySignProcessValidation();
        loanCommonMethods.yourLoanAlmostReadyPageValidation();
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objPopTheChampagneTitle), 10);
        popTheChampagneScreen();
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objCustomButtonClick), "Show me the money!");
        loanCommonMethods.iWillDoItLaterPopup();
        waitForElementToBePresent(FlexUpPage.getByOSType(platform, FlexUpPage.objYourTonikAccount), 5, "Dashboard");
        if (verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objYourTonikAccount), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objYourTonikAccount), "Text"))) {
            logger.info("TDB_FU_010, FlexUpLoan - Verify if user can sign all the Loan documents");
            extentLoggerPass("TDB_FU_010", "TDB_FU_010, FlexUpLoan - Verify if user can sign all the Loan documents");
        }
    }
    /**
     * Method to Verify if Flex Up offer amount is disbursed successfully
     * @throws Exception
     */
    public void verifyFlexUpOfferAmountDisbursedSuccessfully_TDB_FU_011() throws Exception {
        HeaderChildNode("TDB_FU_011, FlexUpLoan - Verify if Flex Up offer amount is disbursed successfully");
        tonikLogin(propertyFileReader("password", "Login"));
        loanCommonMethods.yesYesYesTileValidation();
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), "Loans Tile");
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIwantToCloseBtn), "I want to close my loan Button")) {
            logger.info("TDB_FU_011, FlexUpLoan - Verify if Flex Up offer amount is disbursed successfully");
            extentLoggerPass("TDB_FU_011", "TDB_FU_011, FlexUpLoan - Verify if Flex Up offer amount is disbursed successfully");
        }
    }
    /**
     * Method to Verify if user can view the Account History of the disbursed Flex Up
     * @throws Exception
     */
    public void verifyUserCanViewAccountHistoryOfDisbursedFlexUp_TDB_FU_012(String amount) throws Exception {
        HeaderChildNode("TDB_FU_012, FlexUpLoan - Verify if user can view the Account History of the disbursed Flex Up");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objHistoryBtn), "History Icon");
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objPageTitle), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objPageTitle), ": page title"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objPageTitle)), propertyFileReader("AccountHistory", "QuickLoanWithVas"), ": page title");
        verifyElementPresent(QuickLoanWithVasPage.objTransaction(platform, "Money Credited"), getTextVal(QuickLoanWithVasPage.objTransaction(platform, "Money Credited"), ": transaction"));
        String flexUpAmount = loanCommonMethods.removeSpecialCharacter(getText(QuickLoanWithVasPage.objTransactionAmount(platform, "Money Credited")), true);
        containsValidation(loanCommonMethods.removeSpecialCharacter(getText(QuickLoanWithVasPage.objTransactionAmount(platform, "Money Credited")), true), flexUpAmount, ": Money Credited");
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objBackBtn), "Back button");
        if (verifyElementPresent(LoginPage.getByOSType(platform, LoginPage.objProfileName), getTextVal(LoginPage.getByOSType(platform, LoginPage.objProfileName), ": profile name"))) {
            logger.info("Navigated back to Dashboard");
            extentLoggerPass("TDB_FU_012", "Navigated back to Dashboard");
        }
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objHistoryBtn), "History Icon");
        click(QuickLoanWithVasPage.objTransaction(platform, "Money Credited"), getTextVal(QuickLoanWithVasPage.objTransaction(platform, "Money Credited"), ": transaction"));
        waitTime(3000);
        loanCommonMethods.transactionDetails(flexUpAmount, propertyFileReader("LoanMoneyCreditedFrom", "QuickLoanWithVas"), loanCommonMethods.getAccountNumber("63" + propertyFileReader("FlexUpMobileNumber", "TestDataNumbers")), "QuickLoanWithVas");
        waitTime(4000);
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objBackBtn), "Back button");
        logger.info("TDB_FU_012, FlexUpLoan - Verify if user can view the Account History of the disbursed Flex Up");
        extentLoggerPass("TDB_FU_012", "TDB_FU_012, FlexUpLoan - Verify if user can view the Account History of the disbursed Flex Up");
    }
    /**
     * Method to Verify if user can view the Loan Dashboard after successfully Flex Up disbursement
     * @throws Exception
     */
    public void verifyUserCanViewLoanDashboardAfterSuccessfullyFlexUpDisbursement_TDB_FU_013() throws Exception {
        HeaderChildNode("TDB_FU_013, FlexUpLoan - Verify if user can view the Loan Dashboard after successfully Flex Up disbursement");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objLoansTile), "Loan Tile");
        iWantToCloseMyLoanPageValidation();
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), "Redirected to main Dashboard Page");
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContactUsButton), "Contact Us Button");
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objGetInTouchPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objGetInTouchPage), "Page"))) {
            logger.info("TDB_FU_013, FlexUpLoan - Verify if the user can view the Loan Dashboard screen validated");
            extentLoggerPass("TDB_FU_013", "TDB_FU_013, FlexUpLoan - Verify if the user can view the Loan Dashboard screen validated");
        }
    }

    /**
     * Method to Verify if user can view the Loan information screen
     * @throws Exception
     */
    public void verifyUserCanViewLoanInformationScreen_TDB_FU_014() throws Exception {
        HeaderChildNode("TDB_FU_014, FlexUpLoan - Verify if user can view the Loan information screen");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objLoansTile), "Loan Tile");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoButton), 10, "Information Button");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoButton), "Information Button");
        loanInformationScreen("WithVAS", additionalPayment);
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
        waitTime(3000);
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainDashBoard), "Your tonik account header")) {
            logger.info("TDB_FU_014, FlexUpLoan - Verify if user can view the Loan information screen");
            extentLoggerPass("TDB_FU_014", "TDB_FU_014, FlexUpLoan - Verify if user can view the Loan information screen");
        }
    }

    /**
     * Method to Verify if user can view the Rates and Fees screen
     * @throws Exception
     */
    public void verifyUserCanViewRatesAndFeesScreen_TDB_FU_015() throws Exception {
        HeaderChildNode("TDB_FU_015, FlexUpLoan - Verify if user can view the Rates and Fees screen");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objLoansTile), "Loan Tile");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoButton), 10, "Information Button");
        String emiAmount = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanDashBoardEmiAmount));
        emiAmount = loanCommonMethods.removeSpecialCharacter(emiAmount, false);
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoButton), "Information Button");
        String installmentTerms = loanCommonMethods.extractNumbers(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objInstallmentTermsValue)));
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreeRateAndFees), "Rates and fees");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt), 10, "Rates And Fees Page");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("ratesAndFeesTxt", "QuickLoanWithVas"), "Text");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMonthlyAddOnRate)), propertyFileReader("monthlyAddOnRateTxt", "QuickLoanWithVas"), "Text");
        String loanAmount = loanCommonMethods.loanAmountCalculation(emiAmount, installmentTerms, propertyFileReader("ReloanInterest", "flexUp"));
        double monthlyaddOnrate = loanCommonMethods.monthlyAddOnCalculation(Double.parseDouble(emiAmount), Double.parseDouble(loanAmount), Integer.parseInt(installmentTerms));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objRatesPageMonthlyAddOnRate)), String.valueOf(monthlyaddOnrate), "Monthly add on rate", "contains");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAddOnRateSubTxt), getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAddOnRateSubTxt)));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objEffectiveInterestRateTxt)), propertyFileReader("EffectiveInterestRateText", "flexUp"), "Text");
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.getObjEffectiveInterestRate), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.getObjEffectiveInterestRate), "Effective interest rate"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objProcessingFeeTxt)), propertyFileReader("processingFeeTxt", "QuickLoanWithVas"), "Processing Fee");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objRatesPageProcessingFee), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objRatesPageProcessingFee), "Processing Fee Amount"));
        String expectedDocumentaryStampTaxAmount = loanCommonMethods.calculateDocumentarySummaryTax(Double.parseDouble(installmentTerms), Double.parseDouble(loanAmount));
//		valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDocStampTaxAmount)), expectedDocumentaryStampTaxAmount, "Documentary stamp tax", "contains");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNetProceedsTxt)), propertyFileReader("netProceeds", "QuickLoanWithVas"), "Text");
        containsValidation(getText(ReloanPage.getByOSType(platform, ReloanPage.objLateFeeTxt)).replaceAll("F", "f"), propertyFileReader("LateFeeTxt", "Reloan"), "Text");
        assertionValidation(loanCommonMethods.removeSpecialCharacter(getText(ReloanPage.getByOSType(platform, ReloanPage.objLateFeeAmount)), false), propertyFileReader("LateFee", "Reloan"), "Late Fee Amount");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt), getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt)))) {
            logger.info("TDB_FU_015, FlexUpLoan - Verify if user can view the Rates and Fees screen");
            extentLoggerPass("TDB_FU_015", "TDB_FU_015, FlexUpLoan - Verify if user can view the Rates and Fees screen");
        }
    }

    /**
     * Method to Verify if user can view and download the loan documents
     * @throws Exception
     */
    public void verifyUserCanViewAndDownloadLoanDocuments_TDB_FU_016() throws Exception {
        HeaderChildNode("TDB_FU_016, FlexUpLoan - Verify if user can view and download the loan documents");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objLoansTile), "Loan Tile");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoButton), 10, "Information Button");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoButton), "Information Button");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objTermsAndConditionsClick), "Terms and Conditions");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objDownloadText), 10);
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader)), propertyFileReader("TermsAndConditionsText", "flexUp"), ":Header");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objDownloadBtn), "Download button");
        if (platform.equalsIgnoreCase("IOS")) {
            waitTime(3000);
            tapOnScreen(350, 100, "screen");
        }
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objBackIcon), "Back Icon");
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objPromissoryNoteClick), "Promissory Note");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objNoteDocument), 10);
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader)), propertyFileReader("PromissoryNoteText", "flexUp"), ":Header");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objDownloadBtn), "Download button");
        if (platform.equalsIgnoreCase("IOS")) {
            waitTime(3000);
            tapOnScreen(350, 100, "screen");
        }
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objBackIcon), "Back Icon");
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objDisclosureStatementClick), "Disclosure Statement");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objNoteDocument), 10);
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader)), propertyFileReader("DisclosureStatementText", "flexUp"), ":Header");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objDownloadBtn), "Download button");
        if (platform.equalsIgnoreCase("IOS")) {
            waitTime(3000);
            tapOnScreen(350, 100, "screen");
        }
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objBackIcon), "Back Icon");
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objAmortizationScheduleClick), "Amortization Schedule");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objNoteDocument), 10);
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader)), propertyFileReader("AmortizationScheduleText", "flexUp"), ":Header");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objDownloadBtn), "Download button");
        if (platform.equalsIgnoreCase("IOS")) {
            waitTime(3000);
            tapOnScreen(300, 150, "screen");
        }
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objBackIcon), "Back Icon");
        logger.info("TDB_FU_016, FlexUpLoan - Verify if user can view and download the loan documents");
        extentLoggerPass("TDB_FU_016", "TDB_FU_016, FlexUpLoan - Verify if user can view and download the loan documents");
    }
    /**
     * Method to Verify if user can make full repayment with insufficient TSA balance
     * @throws Exception
     */
    public void verifyUserCanMakeFullRepaymentWithInsufficientTSABalance_TDB_FU_017(String vasType) throws Exception {
        HeaderChildNode("TDB_FU_017, FlexUpLoan - Verify if user can make full repayment with insufficient TSA balance");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objLoansTile), "Loan Tile");
        String loanAmount = getText(ReloanPage.getByOSType(platform, ReloanPage.objMoneyCreditedAmount));
        loanAmount = loanCommonMethods.removeSpecialCharacter(loanAmount, false);
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIwantToCloseLoanButton), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIwantToCloseLoanButton), "Button"));
        loanCommonMethods.fullRepaymentConfirmationPopup();
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt), "Page Header"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("beforeCloseLoanPage", "QuickLoanWithVas"), "Page Header");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSubTitleTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSubTitleTxt), "Page Header"));
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSubTitleTxt)), propertyFileReader("canWeTakeLookTxt", "QuickLoanWithVas"), "Page Sub Header");
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFullRepaymentTxt)), propertyFileReader("fullRepaymentAmountTxt", "QuickLoanWithVas"), "Text");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objRepaymentAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objRepaymentAmount), "Repayment Amount"));
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSubjectToChangeTxt)), propertyFileReader("SubjectToChangeText", "Reloan"), ":Descriptions");
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBreakDownTxt)), propertyFileReader("breakDownTxt", "QuickLoanWithVas"), "Text");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanProduct)), propertyFileReader("loanProduct", "QuickLoanWithVas"), "Text");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPrincipal)), propertyFileReader("principalTxt", "QuickLoanWithVas"), "Text");
        if (vasType.equalsIgnoreCase("WithVAS")) {
            valueValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objPayhingaFeeTxt)), propertyFileReader("payHingaFee", "QuickLoanWithVas"), "Text", "contains");
            verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objPayhingaAmount), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objPayhingaAmount), "Pay hinga fee"));
        }
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInterestTxt)), propertyFileReader("interestTxt", "QuickLoanWithVas"), "Text");
        String interestRate = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInterestRate));
        interestRate = loanCommonMethods.removeSpecialCharacter(interestRate, false);
        String actualRepaymentAmount = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objRepaymentAmount));
        actualRepaymentAmount = loanCommonMethods.removeSpecialCharacter(actualRepaymentAmount, false);
        double repayAmount = Double.parseDouble(loanAmount) + Double.parseDouble(interestRate);
        String expectedRepayAmount = loanCommonMethods.formatToTwoDecimalPlaces(String.valueOf(repayAmount));
        String monthlyInstallment = loanCommonMethods.calculateEMI(Double.parseDouble(loanAmount), Double.parseDouble("9"), Double.parseDouble(propertyFileReader("ReloanInterest", "flexUp")));
        assertionValidation(actualRepaymentAmount, String.valueOf(Double.parseDouble(expectedRepayAmount) + Double.parseDouble(loanCommonMethods.payHinga(monthlyInstallment))), "Repayment Amount");
        String principalAmount = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPrincipalAmount));
        principalAmount = loanCommonMethods.removeSpecialCharacter(principalAmount, false);
        assertionValidation(principalAmount, loanAmount, "Text");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNotEnoughBalanceTxt)), propertyFileReader("notEnoughBalance", "QuickLoanWithVas"), "Text");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTheOnlyWayToProcess)), propertyFileReader("noBustTxt", "QuickLoanWithVas"), "Text");
        swipe("UP", 2);
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTonikAccBalTxt)), propertyFileReader("tonikAccBal", "QuickLoanWithVas"), "Text");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBalanceAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBalanceAmount), "Balance Amount"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTopUpTonicAccountButton)), propertyFileReader("topUpMyTonikBtnTxt", "QuickLoanWithVas"), "Button");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNextInstallmentTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNextInstallmentTxt), "Loan Dashboard Screen"))) {
            logger.info("TDB_FU_017, FlexUpLoan - Verify if user can make full repayment with insufficient TSA balance");
            extentLoggerPass("TDB_FU_017", "TDB_FU_017, FlexUpLoan - Verify if user can make full repayment with insufficient TSA balance");
        }
    }

    /**
     * Method to Verify if user can make full repayment with sufficient TSA balanc
     * @throws Exception
     */
    public void verifyUserCanMakeFullPaymentWithSufficientTSABalance_TDB_FU_018() throws Exception {
        HeaderChildNode("TDB_FU_018, FlexUpLoan - Verify if user can make full repayment with sufficient TSA balance");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objLoansTile), "Loan Tile");
        String loanAmount = getText(ReloanPage.getByOSType(platform, ReloanPage.objMoneyCreditedAmount));
        loanAmount = loanCommonMethods.removeSpecialCharacter(loanAmount, false);
        int topUpAmount = (int) Double.parseDouble(loanAmount);
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIwantToCloseLoanButton), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIwantToCloseLoanButton), "Button"));
        loanCommonMethods.fullRepaymentConfirmationPopup();
        swipe("UP", 2);
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBalanceAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBalanceAmount), "Balance Amount"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTopUpTonicAccountButton), "Top up my Tonik account Button");
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTopUpTonicAccountButton), "Top up my Tonik account Button");
        loanCommonMethods.topUp(String.valueOf(topUpAmount));
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), "Loans Tile");
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIwantToCloseLoanButton), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIwantToCloseLoanButton), "Button"));
        loanCommonMethods.fullRepaymentConfirmationPopup();
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBalanceAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBalanceAmount), "Balance Amount"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objCloseMyLoanButton), "Close My Loan Button");
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objCloseMyLoanButton), "Close My Loan Button");
        waitTime(3000);
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objDropTheMicPage)), propertyFileReader("dropMicPageHeader", "QuickLoanWithVas"), "Page Header");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackToDashBoardButton), "Back to Dashboard Button");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objHistoryBtn), "History Icon");
        String amount = loanCommonMethods.removeSpecialCharacter(getText(QuickLoanWithVasPage.objTransactionAmount(platform, "Loan paid in Full")), true);
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanPaidInFull), "Loan paid in Full");
        waitTime(3000);
        loanCommonMethods.transactionDetails(amount, loanCommonMethods.getAccountNumber("63" + propertyFileReader("FlexUpMobileNumber", "TestDataNumbers")), propertyFileReader("LoanMoneyCreditedFrom1", "QuickLoanWithVas"), "QuickLoanWithVas");
        logger.info("TDB_FU_018, FlexUpLoan - Verify if user can make full repayment with sufficient TSA balance");
        extentLoggerPass("TDB_FU_018", "TDB_FU_018, FlexUpLoan - Verify if user can make full repayment with sufficient TSA balance validated");
    }
    /**
     * Method to Verify the loans tile status after the full loan repayment of a FlexUpLoan
     * @throws Exception
     */
    public void verifyLoanTileStatusAfterFullLoanRepaymentOfReloan_TDB_FU_019() throws Exception {
        HeaderChildNode("TDB_FU_019, FlexUpLoan - Verify the loans tile status after the full loan repayment of a FlexUpLoan");
        tonikLogin(propertyFileReader("password", "Login"));
        swipe("UP", 1);
        assertionValidation(getText(ReloanPage.getByOSType(platform, ReloanPage.objLoanTileHeader)), propertyFileReader("LoanHeader", "Reloan"), " Header");
        assertionValidation(getText(ReloanPage.getByOSType(platform, ReloanPage.objFastCashText)), propertyFileReader("FastCashEasyPaymentsText", "Reloan"), " Title");
        assertionValidation(getText(ReloanPage.getByOSType(platform, ReloanPage.objLoanTileDesc)), propertyFileReader("AfterReLoanCloseLoanTileDesc", "Reloan"), " Description");
        logger.info("TDB_FU_019, FlexUpLoan - Verify the loan tile for a FlexUp Offer");
        extentLoggerPass("TDB_FU_019", "TDB_FU_019, FlexUpLoan - Verify the loan tile for a FlexUp Offer");
    }
    /**
     * Method to Verify if user can reapply any loan after the full loan repayment of Flex Up
     * @throws Exception
     */
    public void verifyUserCanReapplyAnyLoanAfterTheFullLoanRepaymentOfFlexUp_TDB_FU_021() throws Exception {
        HeaderChildNode("TDB_FU_021, FlexUpLoan - Verify if user can reapply any loan after the full loan repayment of Flex Up");
        tonikLogin(propertyFileReader("password", "Login"));
        swipe("UP", 1);
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), "Loan Tile");
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objCreditBuilderTile), "Credit Builder Tile");
        loanCommonMethods.readySetBoostPageValidation();
        loanCommonMethods.callMeMayBePageValidation();
        loanCommonMethods.nowWithPayHingaPageValidation();
        loanCommonMethods.howMuchDoYouNeedPage();
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContinueBtn), "Continue Button");
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanCalculatorPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanCalculatorPage), "Page"))) {
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objYourInstallmentTxt)), propertyFileReader("chooseInstallmentTxt", "QuickLoanWithVas"), "Page Text", "contains");
            logger.info("TDB_FU_021, FlexUpLoan - Verify if user can reapply any loan after the full loan repayment of Flex Up validated");
            extentLoggerPass("TDB_FU_021", "TDB_FU_021, FlexUpLoan - Verify if user can reapply any loan after the full loan repayment of Flex Up validated");
        }
    }
    /**
     * Method to Flex up without VAS End to End Journey
     * @param terms
     * @param loanPurpose
     * @throws Exception
     */
    public void flexUpEndToEndJourney(String terms, String loanPurpose) throws Exception {
        HeaderChildNode("Flex up loan without VAS End to end Journey");
        insertFlexUpOffer("63" + propertyFileReader("FlexUpMobileNumber", "TestDataNumbers"));
        tonikLogin(propertyFileReader("password", "Login"));
        loansTileScreen();
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objLoansTile), "Loan Tile");
        nextInstallmentSreen();
        upgradeToFlexUpScreen();
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objFlexUpTileButton), "Upgrade To Flex Up");
        howItWorksScreen();
        verifyElementPresentAndClick(FlexUpPage.objCustomBtn(platform, propertyFileReader("GotItText", "flexUp")), "Got it");
        letsDoTheMathScreen();
        verifyElementPresentAndClick(FlexUpPage.objCustomBtn(platform, propertyFileReader("NiceWhereMyOfferText", "flexUp")), "Nice! Where's my offer");
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objFlexinPossibilitiesHeader), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objFlexinPossibilitiesHeader), "Header"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objFlexinPossibilitiesHeader)), propertyFileReader("FlexinThePossiblities", "flexUp"), "Header");
        verifyElementPresentAndClick(FlexUpPage.objPaymentTerms(platform, terms), "Terms");
        swipe("UP", 2);
        monthlyInstallment = getText(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyInstallmentAmount)).replace("₱", "").replace(",", "");
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objUnprotectedloanBtn), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objUnprotectedloanBtn), "Button"));
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objCustomButtonText), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objCustomButtonText), "Button"));
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objLoanSummaryHeader), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objLoanSummaryHeader), "Header"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objLoanSummaryHeader)), propertyFileReader("LoanSummaryHeader", "flexUp"), "Header");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objLoanAmountText)), propertyFileReader("LoanAmountText", "flexUp"), "Text");
        String loanAmount = loanCommonMethods.loanAmountCalculation(monthlyInstallment, terms, propertyFileReader("ReloanInterest", "flexUp"));
        String documentaryStampTax = loanCommonMethods.calculateDocumentarySummaryTax(Double.parseDouble(terms), Double.parseDouble(loanAmount));
        String outstandingBalance = getText(FlexUpPage.getByOSType(platform, FlexUpPage.objOutstandingBalanceValue)).replace("₱", "").replace(",", "");
        Double amountReceive = Double.parseDouble(loanAmount) - (Double.parseDouble(outstandingBalance) + Double.parseDouble(documentaryStampTax));
        double monthlyAddOnRate = loanCommonMethods.monthlyAddOnCalculation(Double.parseDouble(monthlyInstallment), Double.parseDouble(loanAmount), Integer.parseInt(terms));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objLoanAmountValue)).replace("₱", "").replace(",", ""), loanAmount, "Loan Amount");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objOutstandingBalanceText)), propertyFileReader("OutstandingBalanceText", "flexUp"), "Text");
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objOutstandingBalanceValue), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objOutstandingBalanceValue), "Value"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objAmountReceiveText)), propertyFileReader("AmountReceiveText", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyInstallmentText)), propertyFileReader("MonthlyInstallmentText", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyInstallment)).replace("₱", "").replace(",", ""), monthlyInstallment, "Value");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objInstallmentTermsText)), propertyFileReader("InstallmentTermsText", "flexUp"), "Text");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objInstallmentTermsValue)), terms + " Months", "Value");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyAddOnRateText)), propertyFileReader("MonthlyAddOnRateText", "flexUp"), "Text");
        valueValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyAddOnRateValue)), propertyFileReader("MonthlyAddOnRate", "flexUp"), "Text", "contains");
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objMonthlyAddOnRateValue)).split(" ")[0].substring(0, 4), String.valueOf(LoanCommonMethods.round(monthlyAddOnRate, 2)), "Value");
        swipe("UP", 1);
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objProcessingFeeText)), propertyFileReader("ProcessingFeeText", "flexUp"), "Text");
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objProcessingFeeValue), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objProcessingFeeValue), "Processing Fee"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objDocumentaryStampText)), propertyFileReader("DocumentaryStampText", "flexUp"), "Text");
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objSweetIAcceptText), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objSweetIAcceptText), "Button"));
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform, ReloanPage.objSweetIAcceptText), getText(ReloanPage.getByOSType(platform, ReloanPage.objSweetIAcceptText)));
        verifyElementPresentAndClick(ReloanPage.objLoanPurpose(platform, loanPurpose), loanPurpose);
        assertionValidation(getAttributValue("enabled", ReloanPage.getByOSType(platform, ReloanPage.objNextButton)), "true", ": Enable Attribute value");
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform, ReloanPage.objNextButton), " Next Button");
        waitForElementDisplayed(ReloanPage.getByOSType(platform, ReloanPage.objIsThisYourCurrentAddressButton), 10);
        verifyElementPresentAndClick(ReloanPage.getByOSType(platform, ReloanPage.objIsThisYourCurrentAddressButton), "Is This Your CurrentAddress Button");
        waitForElementDisplayed(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTermsAndConditionCheckBox), 10);
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTermsAndConditionCheckBox), "Terms and condition check box");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIAcceptBtn), "I accept and give my consent button");
        waitTime(6000);
        dontForgetLuvScreen();
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objBackIcon), "Back Icon");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIAcceptBtn), "I accept and give my consent button");
        verifyElementPresentAndClick(FlexUpPage.objCustomBtn(platform, propertyFileReader("GotItText", "flexUp")), "Got it");
        monthlyInstallmentSummaryPageValidation(terms, propertyFileReader("monthlyPageSubHeader", "flexUp"));
        waitTime(2000);
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objConfirmBtn), "Confirm Button");
        documentarySignProcessValidation();
        loanCommonMethods.yourLoanAlmostReadyPageValidation();
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objPopTheChampagneTitle), 10);
        popTheChampagneScreen();
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objCustomButtonClick), "Show me the money!");
        loanCommonMethods.iWillDoItLaterPopup();
        loanCommonMethods.yesYesYesTileValidation();
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objHistoryBtn), "History Icon");
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.objPageTitle), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.objPageTitle), ": page title"));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objPageTitle)), propertyFileReader("AccountHistory", "QuickLoanWithVas"), ": page title");
        verifyElementPresent(QuickLoanWithVasPage.objTransaction(platform, "Money Credited"), getTextVal(QuickLoanWithVasPage.objTransaction(platform, "Money Credited"), ": transaction"));
        String flexUpAmount = loanCommonMethods.removeSpecialCharacter(getText(QuickLoanWithVasPage.objTransactionAmount(platform, "Money Credited")), true);
        containsValidation(loanCommonMethods.removeSpecialCharacter(getText(QuickLoanWithVasPage.objTransactionAmount(platform, "Money Credited")), true), flexUpAmount, ": Money Credited");
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objBackBtn), "Back button");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objHistoryBtn), "History Icon");
        click(QuickLoanWithVasPage.objTransaction(platform, "Money Credited"), getTextVal(QuickLoanWithVasPage.objTransaction(platform, "Money Credited"), ": transaction"));
        waitTime(3000);
        loanCommonMethods.transactionDetails(flexUpAmount, propertyFileReader("LoanMoneyCreditedFrom", "QuickLoanWithVas"), loanCommonMethods.getAccountNumber("63" + propertyFileReader("FlexUpMobileNumber", "TestDataNumbers")), "QuickLoanWithVas");
        waitTime(4000);
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objBackBtn), "Back button");
        waitTime(3000);
        click(FlexUpPage.getByOSType(platform, FlexUpPage.objBackBtn), "Back button");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objLoansTile), "Loan Tile");
        iWantToCloseMyLoanPageValidation();
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoButton), 10, "Information Button");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoButton), "Information Button");
        loanInformationScreen("WithoutVAS", "0.00");
        waitTime(3000);
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoButton), 10, "Information Button");
        String emiAmount = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanDashBoardEmiAmount));
        emiAmount = loanCommonMethods.removeSpecialCharacter(emiAmount, false);
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoButton), "Information Button");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreeRateAndFees), "Rates and fees");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt), 10, "Rates And Fees Page");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("ratesAndFeesTxt", "QuickLoanWithVas"), "Text");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMonthlyAddOnRate)), propertyFileReader("monthlyAddOnRateTxt", "QuickLoanWithVas"), "Text");
        double monthlyaddOnrate = loanCommonMethods.monthlyAddOnCalculation(Double.parseDouble(emiAmount), Double.parseDouble(loanAmount), Integer.parseInt(terms));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objRatesPageMonthlyAddOnRate)), String.valueOf(monthlyaddOnrate), "Monthly add on rate", "contains");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAddOnRateSubTxt), getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAddOnRateSubTxt)));
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objEffectiveInterestRateTxt)), propertyFileReader("EffectiveInterestRateText", "flexUp"), "Text");
        verifyElementPresent(FlexUpPage.getByOSType(platform, FlexUpPage.getObjEffectiveInterestRate), getTextVal(FlexUpPage.getByOSType(platform, FlexUpPage.getObjEffectiveInterestRate), "Effective interest rate"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objProcessingFeeTxt)), propertyFileReader("processingFeeTxt", "QuickLoanWithVas"), "Processing Fee");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objRatesPageProcessingFee), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objRatesPageProcessingFee), "Processing Fee Amount"));
        String expectedDocumentaryStampTaxAmount = loanCommonMethods.calculateDocumentarySummaryTax(Double.parseDouble(terms), Double.parseDouble(loanAmount));
//		valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDocStampTaxAmount)), expectedDocumentaryStampTaxAmount, "Documentary stamp tax", "contains");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNetProceedsTxt)), propertyFileReader("netProceeds", "QuickLoanWithVas"), "Text");
        containsValidation(getText(ReloanPage.getByOSType(platform, ReloanPage.objLateFeeTxt)).replaceAll("F", "f"), propertyFileReader("LateFeeTxt", "Reloan"), "Text");
        assertionValidation(loanCommonMethods.removeSpecialCharacter(getText(ReloanPage.getByOSType(platform, ReloanPage.objLateFeeAmount)), false), propertyFileReader("LateFee", "Reloan"), "Late Fee Amount");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
        waitTime(3000);
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoButton), 10, "Information Button");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoButton), "Information Button");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objTermsAndConditionsClick), "Terms and Conditions");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objDownloadText), 10);
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader)), propertyFileReader("TermsAndConditionsText", "flexUp"), ":Header");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objDownloadBtn), "Download button");
        if (platform.equalsIgnoreCase("IOS")) {
            waitTime(3000);
            tapOnScreen(350, 100, "screen");
        }
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objBackIcon), "Back Icon");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objPromissoryNoteClick), "Promissory Note");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objNoteDocument), 10);
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader)), propertyFileReader("PromissoryNoteText", "flexUp"), ":Header");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objDownloadBtn), "Download button");
        if (platform.equalsIgnoreCase("IOS")) {
            waitTime(3000);
            tapOnScreen(350, 100, "screen");
        }
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objBackIcon), "Back Icon");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objDisclosureStatementClick), "Disclosure Statement");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objNoteDocument), 10);
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader)), propertyFileReader("DisclosureStatementText", "flexUp"), ":Header");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objDownloadBtn), "Download button");
        if (platform.equalsIgnoreCase("IOS")) {
            waitTime(3000);
            tapOnScreen(350, 100, "screen");
        }
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objBackIcon), "Back Icon");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objAmortizationScheduleClick), "Amortization Schedule");
        waitForElementDisplayed(FlexUpPage.getByOSType(platform, FlexUpPage.objNoteDocument), 10);
        assertionValidation(getText(FlexUpPage.getByOSType(platform, FlexUpPage.objHeader)), propertyFileReader("AmortizationScheduleText", "flexUp"), ":Header");
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objDownloadBtn), "Download button");
        if (platform.equalsIgnoreCase("IOS")) {
            waitTime(3000);
            tapOnScreen(300, 150, "screen");
        }
        verifyElementPresentAndClick(FlexUpPage.getByOSType(platform, FlexUpPage.objBackIcon), "Back Icon");
        logger.info("FlexUpLoan - Flex up loan without VAS End to end Journey validated");
        extentLoggerPass("", "FlexUpLoan - Flex up loan without VAS End to end Journey validated");
    }
}