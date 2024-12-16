package com.tonik.regression;

import org.testng.annotations.Test;
import static com.tonik.utility.Utilities.propertyFileReader;
import static com.tonik.utility.Utilities.softAssert;
public class QuickLoanWithVasScripts extends BaseTest {
    String mobileNumber = propertyFileReader("QuickLoanMobileNumber","TestDataNumbers");
    String referenceContact = propertyFileReader("QuickLoanReferenceContact","TestDataNumbers");
    String loanAmount = propertyFileReader("loanAmount", "QuickLoanWithVas");
    String qlLoanTenure = propertyFileReader("LoanTenure", "QuickLoanWithVas");
    String qlInterestRate = propertyFileReader("QLInterestRate", "QuickLoanWithVas");
    @Test(priority = 1,groups = {"Positive"})
    public void OnboardAsNewSKYCUserWithNoTSA_TDB_QLV_004() throws Exception {
        try {
            quickLoanWithVasModule.onBoardAsNewSKYCUserWithNoTSA_TDB_QLV_004();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 2,groups = {"Positive"})
    public void userCanEditAmountInHowMuchDoYouNeedScreen_TDB_QLV_005() throws Exception {
        try {
            quickLoanWithVasModule.userCanEditAmountInHowMuchDoYouNeedScreen_TDB_QLV_005();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 3,groups = {"Positive"})
    public void userCanApplyLoanWithVASFromLoanCalculatorScreen_TDB_QLV_007() throws Exception {
        try {
            quickLoanWithVasModule.userCanApplyLoanWithVASFromLoanCalculatorScreen_TDB_QLV_007(loanAmount);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 4,groups = {"Positive"})
    public void userCanAcceptLoanOfferWithVASFromTheLoanSummaryScreen_TDB_QLV_008() throws Exception {
        try {
            quickLoanWithVasModule.userCanAcceptLoanOfferWithVASFromTheLoanSummaryScreen_TDB_QLV_008(loanAmount,qlLoanTenure);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 5,groups = {"Positive"})
    public void reasonForLoanApplicationValidation_TDB_QLV_009() throws Exception {
        try {
            quickLoanWithVasModule.reasonForLoanApplicationValidation_TDB_QLV_009();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 6,groups = {"Positive"})
    public void loanSegmentationForEntireQuickLoanVASJourney_TDB_QLV_127()throws Exception {
        try{
            quickLoanWithVasModule.loanSegmentationForEntireQuickLoanVASJourney_TDB_QLV_127();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 7,groups = {"Positive"})
    public void selectFieldOfWorkValidation_TDB_QLV_010() throws Exception {
        try {
            quickLoanWithVasModule.selectFieldOfWorkValidation_TDB_QLV_010();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 8,groups = {"Positive"})
    public void selectOccupationValidation_TDB_QLV_011() throws Exception {
        try {
            quickLoanWithVasModule.selectOccupationValidation_TDB_QLV_011();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 9,groups = {"Positive"})
    public void selectIndustryAndSubIndustryValidation_TDB_QLV_012() throws Exception {
        try {
            quickLoanWithVasModule.selectIndustryAndSubIndustryValidation_TDB_QLV_012();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 10,groups = {"Positive"})
    public void selectMaritalStatusValidation_TDB_QLV_013() throws Exception {
        try {
            quickLoanWithVasModule.selectMaritalStatusValidation_TDB_QLV_013();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 11,groups = {"Positive"})
    public void selectEducationalDetails_TDB_QLV_014() throws Exception {
        try {
            quickLoanWithVasModule.selectEducationalDetails_TDB_QLV_014();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 12,groups = {"Positive"})
    public void inputReferenceContactDetailsValidation_TDB_QLV_015() throws Exception {
        try {
            quickLoanWithVasModule.inputReferenceContactDetailsValidation_TDB_QLV_015();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 13,groups = {"Positive"})
    public void selectYourCurrentLivingCityValidation_TDB_QLV_016() throws Exception {
        try {
            quickLoanWithVasModule.selectYourCurrentLivingCityValidation_TDB_QLV_016();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 14,groups = {"Positive"})
    public void inputMonthlyIncomeCompanyNameTINDetails_TDB_QLV_017() throws Exception {
        try {
            quickLoanWithVasModule.inputMonthlyIncomeCompanyNameTINDetails_TDB_QLV_017();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 15,groups = {"Positive"})
    public void inputSecondaryContactDetailsValidation_TDB_QLV_018() throws Exception {
        try {
            quickLoanWithVasModule.inputSecondaryContactDetailsValidation_TDB_QLV_018(mobileNumber,referenceContact);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 16,groups = {"Positive"})
    public void acceptTermsAndConditionValidation_TDB_QLV_019() throws Exception {
        try {
            quickLoanWithVasModule.acceptTermsAndConditionValidation_TDB_QLV_019();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 17,dependsOnMethods = {"acceptTermsAndConditionValidation_TDB_QLV_019"},groups = {"Positive"})
    public void verifyLoanTileIfUserPreApproved_TDB_QLV_025() throws Exception {
        try {
            quickLoanWithVasModule.verifyLoanTileIfUserPreApproved_TDB_QLV_025(mobileNumber);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 18,dependsOnMethods = {"verifyLoanTileIfUserPreApproved_TDB_QLV_025"},groups = {"Positive"})
    public void verifyUserAcceptLoanOfferFromLoanOfferScreen_TDB_QLV_026() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserAcceptLoanOfferFromLoanOfferScreen_TDB_QLV_026(loanAmount,propertyFileReader("WithVAS","QuickLoanWithVas"),loanAmount,qlLoanTenure,qlInterestRate);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 19,dependsOnMethods = {"verifyLoanTileIfUserPreApproved_TDB_QLV_025"},groups = {"Positive"})
    public void verifyUserSelectPayDayAndConfirmMonthlyInstallmentSummary_TDB_QLV_27() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserSelectPayDayAndConfirmMonthlyInstallmentSummary_TDB_QLV_27(propertyFileReader("WithVAS","QuickLoanWithVas"));
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 20,dependsOnMethods = {"verifyLoanTileIfUserPreApproved_TDB_QLV_025"},groups = {"Positive"})
    public void verifyUserCanSignLoanDocumentsDisburseLoanSuccessfully_TDB_QLV_28() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserCanSignLoanDocumentsDisburseLoanSuccessfully_TDB_QLV_28();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 21,dependsOnMethods = {"verifyUserCanSignLoanDocumentsDisburseLoanSuccessfully_TDB_QLV_28"},groups = {"Positive"})
    public void verifyLoansTileStatusAfterSuccessfulLoanDisbursal_TDB_QLV_029() throws Exception {
        try {
            quickLoanWithVasModule.verifyLoansTileStatusAfterSuccessfulLoanDisbursal_TDB_QLV_029();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 22,dependsOnMethods = {"verifyUserCanSignLoanDocumentsDisburseLoanSuccessfully_TDB_QLV_28"},groups = {"Positive"})
    public void verifyUserViewLoanDashboardScreen_TDB_QLV_031() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserViewLoanDashboardScreen_TDB_QLV_031(loanAmount,qlLoanTenure,qlInterestRate,"WithVAS");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 23,dependsOnMethods = {"verifyUserCanSignLoanDocumentsDisburseLoanSuccessfully_TDB_QLV_28"},groups = {"Positive"})
    public void verifyUserCanAccessPayHingaTileFromLoanDashboardScreen_TDB_QLV_032() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserCanAccessPayHingaTileFromLoanDashboardScreen_TDB_QLV_032();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 24,dependsOnMethods = {"verifyUserCanSignLoanDocumentsDisburseLoanSuccessfully_TDB_QLV_28"},groups = {"Positive"})
    public void verifyUserCanAccessLifeInsuranceTileFromLoanDashboardScreen_TDB_QLV_033() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserCanAccessLifeInsuranceTileFromLoanDashboardScreen_TDB_QLV_033();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 25,dependsOnMethods = {"verifyUserCanSignLoanDocumentsDisburseLoanSuccessfully_TDB_QLV_28"},groups = {"Positive"})
    public void verifyUserAccessLoanInformationScreen_TDB_QLV_034() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserAccessLoanInformationScreen_TDB_QLV_034(loanAmount,qlLoanTenure,qlInterestRate,propertyFileReader("WithVAS","QuickLoanWithVas"));
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 26,dependsOnMethods = {"verifyUserCanSignLoanDocumentsDisburseLoanSuccessfully_TDB_QLV_28"},groups = {"Positive"})
    public void verifyUserCanViewRatesFeesDetails_TDB_QLV_035() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserCanViewRatesFeesDetails_TDB_QLV_035(loanAmount,qlLoanTenure,qlInterestRate,"WithVAS");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 27,dependsOnMethods = {"verifyUserCanSignLoanDocumentsDisburseLoanSuccessfully_TDB_QLV_28"},groups = {"Positive"})
    public void userCanViewAndDownloadAllLoanDocumentsValidation_TDB_QLV_036() throws Exception {
        try {
            quickLoanWithVasModule.userCanViewAndDownloadAllLoanDocumentsValidation_TDB_QLV_036("WithVAS");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 28,dependsOnMethods = {"verifyUserCanSignLoanDocumentsDisburseLoanSuccessfully_TDB_QLV_28"},groups = {"Positive"})
    public void verifyTsaBalanceInMainScreenAfterLoanDisbursal_TDB_QLV_037() throws Exception {
        try {
            quickLoanWithVasModule.verifyTsaBalanceInMainScreenAfterLoanDisbursal_TDB_QLV_037(loanAmount,qlLoanTenure);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 29,dependsOnMethods = {"verifyUserCanSignLoanDocumentsDisburseLoanSuccessfully_TDB_QLV_28"},groups = {"Positive"})
    public void loanTransactionDetailsValidationFromHistoryScreen_TDB_QLV_038() throws Exception {
        try {
            quickLoanWithVasModule.loanTransactionDetailsValidationFromHistoryScreen_TDB_QLV_038(loanAmount,mobileNumber,qlLoanTenure);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 30,dependsOnMethods = {"verifyUserCanSignLoanDocumentsDisburseLoanSuccessfully_TDB_QLV_28"},groups = {"Positive"})
    public void verifyUserCanViewLoanPaymentScreen_TDB_QLV_039() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserCanViewLoanPaymentScreen_TDB_QLV_039(Integer.parseInt(propertyFileReader("DueDay", "QuickLoanWithVas")),mobileNumber);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 31,dependsOnMethods = {"verifyUserCanSignLoanDocumentsDisburseLoanSuccessfully_TDB_QLV_28"},groups = {"Positive"})
    public void verifyUserMakeFullRepaymentWithInsufficientTSABalance_TDB_QLV_110() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserMakeFullRepaymentWithInsufficientTSABalance_TDB_QLV_110(loanAmount,qlLoanTenure,qlInterestRate,"WithVAS");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 32,dependsOnMethods = {"verifyUserCanSignLoanDocumentsDisburseLoanSuccessfully_TDB_QLV_28"},groups = {"Positive"})
    public void verifyUserCanMakeFullRepaymentWithSufficientTSABalance_TDB_QLV_111() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserCanMakeFullRepaymentWithSufficientTSABalance_TDB_QLV_111(loanAmount,qlLoanTenure,qlInterestRate,mobileNumber,"WithVAS");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 33,dependsOnMethods = {"verifyUserCanSignLoanDocumentsDisburseLoanSuccessfully_TDB_QLV_28"},groups = {"Positive"})
    public void verifyLoansTileStatusAfterFullLoanRepayment_TDB_QLV_113() throws Exception {
        try {
            quickLoanWithVasModule.verifyLoansTileStatusAfterFullLoanRepayment_TDB_QLV_113();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 34,dependsOnMethods = {"verifyUserCanMakeFullRepaymentWithSufficientTSABalance_TDB_QLV_111"},groups = {"Positive"})
    public void verifyUserCanReapplyQuickLoanAfterFullLoanRepayment_TDB_QLV_114() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserCanReapplyQuickLoanAfterFullLoanRepayment_TDB_QLV_114();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 35,dependsOnMethods = {"verifyUserCanReapplyQuickLoanAfterFullLoanRepayment_TDB_QLV_114"},groups = {"Positive"})
    public void userCanQuitLoanApplicationValidation_TDB_QLV_121() throws Exception {
        try {
            quickLoanWithVasModule.userCanQuitLoanApplicationValidation_TDB_QLV_121(loanAmount,mobileNumber);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 36,dependsOnMethods = {"userCanQuitLoanApplicationValidation_TDB_QLV_121"},groups = {"Positive"})
    public void verifySKYCUserCanApplyQuickLoanTSACreatedAlready_TDB_QLV_120() throws Exception {
        try {
            quickLoanWithVasModule.verifySKYCUserCanApplyQuickLoanTSACreatedAlready_TDB_QLV_120(mobileNumber,loanAmount,qlLoanTenure,qlInterestRate);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 37,dependsOnMethods = {"verifySKYCUserCanApplyQuickLoanTSACreatedAlready_TDB_QLV_120"},groups = {"Positive"})
    public void verifyUserCanMakePaymentViaTonikAccount_TDB_QLV_041()throws Exception {
        try{
            quickLoanWithVasModule.verifyUserCanMakePaymentViaTonikAccount_TDB_QLV_041(Integer.parseInt(propertyFileReader("DueDay","QuickLoanWithVas")),mobileNumber,"WIthVAS");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 38,dependsOnMethods = {"verifySKYCUserCanApplyQuickLoanTSACreatedAlready_TDB_QLV_120"},groups = {"Positive"})
    public void verifyUserCanActivatePayHingaWithNoOutStandingDue_TDB_QLV_060()throws Exception {
        try{
            quickLoanWithVasModule.verifyUserCanActivatePayHingaWithNoOutStandingDue_TDB_QLV_060(61,30,mobileNumber,loanAmount,qlLoanTenure,qlInterestRate);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 39,groups = {"Negative"})
    public void verifyLoansTileIfUserNotAcceptLoanOfferIn30Days_TDB_QLV_128()throws Exception {
        try{
            quickLoanWithVasModule.verifyLoansTileIfUserNotAcceptLoanOfferIn30Days_TDB_QLV_128(mobileNumber,loanAmount);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 40,groups = {"Negative"})
    public void verifySKYCUserIDExpiresIn45Days_TDB_QLV_122() throws Exception {
        try {
            quickLoanWithVasModule.verifySKYCUserIDExpiresIn45Days_TDB_QLV_122(loanAmount);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 41,groups = {"Negative"})
    public void verifySKYCUserIDAlreadyExpired_TDB_QLV_123() throws Exception {
        try {
            quickLoanWithVasModule.verifySKYCUserIDAlreadyExpired_TDB_QLV_123(loanAmount);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 42,groups = {"Negative"})
    public void loanSoftRejectionValidation_TDB_QLV_020() throws Exception {
        try {
            quickLoanWithVasModule.loanSoftRejectionValidation_TDB_QLV_020(loanAmount);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 43,groups = {"Negative"})
    public void loanHardRejectionValidation_TDB_QLV_021() throws Exception {
        try {
            quickLoanWithVasModule.loanHardRejectionValidation_TDB_QLV_021(loanAmount);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 44,groups = {"Negative"})
    public void bkycUserCanApplyQuickLoanIfTSAIsNotCreatedValidation_TDB_QLV_118() throws Exception {
        try {
            quickLoanWithVasModule.bkycUserCanApplyQuickLoanIfTSAIsNotCreatedValidation_TDB_QLV_118(loanAmount);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
}