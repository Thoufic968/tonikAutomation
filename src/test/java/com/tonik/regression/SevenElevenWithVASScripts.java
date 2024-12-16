package com.tonik.regression;

import org.testng.annotations.Test;
import static com.tonik.utility.Utilities.propertyFileReader;
import static com.tonik.utility.Utilities.softAssert;
public class SevenElevenWithVASScripts extends BaseTest {
    String mobileNumber = propertyFileReader("SevenElevenMobileNumber","TestDataNumbers");
    String referenceContact = propertyFileReader("SevenElevenReferenceContact","TestDataNumbers");
    String password = propertyFileReader("password", "Login");
    String loanAmount = propertyFileReader("FlexPivotLoanAmount", "FlexPivot");
    String flexPivotLoanTenure = propertyFileReader("loanTenure", "FlexPivot");
    String flexPivotInterestRate = propertyFileReader("FlexPivotInterestRate", "FlexPivot");
    String loanType = "WithVAS";
    @Test(priority = 1)
    public void TDB_SEV_001() throws Exception {
        try {
            sevenElevenWithVASModule.verifyUserCanAccessSevenElevenLoanFromMainDashBoard_TDB_SEV_001(password,mobileNumber);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 2)
    public void TDB_SEV_002() throws Exception {
        try {
            sevenElevenWithVASModule.verifyUserCanAccessSevenElevenLoanFromMainDashBoardWithNoPopup_TDB_SEV_002(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 3)
    public void TDB_SEV_007() throws Exception {
        try {
            sevenElevenWithVASModule.userCanApplyLoanWithVASFromLoanCalculatorScreen_TDB_SEV_007(flexPivotLoanTenure);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 4)
    public void TDB_SEV_008() throws Exception {
        try {
            sevenElevenWithVASModule.userCanAcceptLoanOfferWithVASFromTheLoanSummaryScreen_TDB_SEV_008(flexPivotLoanTenure);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 5,dependsOnMethods = {"TDB_SEV_008"})
    public void TDB_SEV_009() throws Exception {
        try {
            quickLoanWithVasModule.reasonForLoanApplicationValidation_TDB_QLV_009();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 6,dependsOnMethods = {"TDB_SEV_008"})
    public void TDB_SEV_127()throws Exception {
        try{
            quickLoanWithVasModule.loanSegmentationForEntireQuickLoanVASJourney_TDB_QLV_127();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 7,dependsOnMethods = {"TDB_SEV_008"})
    public void TDB_SEV_010() throws Exception {
        try {
            quickLoanWithVasModule.selectFieldOfWorkValidation_TDB_QLV_010();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 8,dependsOnMethods = {"TDB_SEV_008"})
    public void TDB_SEV_011() throws Exception {
        try {
            quickLoanWithVasModule.selectOccupationValidation_TDB_QLV_011();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 9,dependsOnMethods = {"TDB_SEV_008"})
    public void TDB_SEV_012() throws Exception {
        try {
            quickLoanWithVasModule.selectIndustryAndSubIndustryValidation_TDB_QLV_012();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 10,dependsOnMethods = {"TDB_SEV_008"})
    public void TDB_SEV_013() throws Exception {
        try {
            quickLoanWithVasModule.selectMaritalStatusValidation_TDB_QLV_013();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 11,dependsOnMethods = {"TDB_SEV_008"})
    public void TDB_SEV_014() throws Exception {
        try {
            quickLoanWithVasModule.selectEducationalDetails_TDB_QLV_014();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 12,dependsOnMethods = {"TDB_SEV_008"})
    public void TDB_SEV_015() throws Exception {
        try {
            quickLoanWithVasModule.inputReferenceContactDetailsValidation_TDB_QLV_015();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 13,dependsOnMethods = {"TDB_SEV_008"})
    public void TDB_SEV_016() throws Exception {
        try {
            quickLoanWithVasModule.selectYourCurrentLivingCityValidation_TDB_QLV_016();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 14,dependsOnMethods = {"TDB_SEV_008"})
    public void TDB_SEV_017() throws Exception {
        try {
            flexPivotWithoutVASModule.inputMonthlyIncomeTINDetails_TDB_FP_014();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 15,dependsOnMethods = {"TDB_SEV_008"})
    public void TDB_SEV_018() throws Exception {
        try {
            quickLoanWithVasModule.inputSecondaryContactDetailsValidation_TDB_QLV_018(mobileNumber,referenceContact);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 16,dependsOnMethods = {"TDB_SEV_008"})
    public void TDB_SEV_019() throws Exception {
        try {
            quickLoanWithVasModule.acceptTermsAndConditionValidation_TDB_QLV_019();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 17,dependsOnMethods = {"TDB_SEV_019"})
    public void TDB_SEV_025() throws Exception {
        try {
            quickLoanWithVasModule.verifyLoanTileIfUserPreApproved_TDB_QLV_025(mobileNumber);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 18,dependsOnMethods = {"TDB_SEV_025"})
    public void TDB_SEV_026() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserAcceptLoanOfferFromLoanOfferScreen_TDB_QLV_026(loanAmount,loanType,loanAmount,flexPivotLoanTenure,flexPivotInterestRate);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 19,dependsOnMethods = {"TDB_SEV_025"})
    public void TDB_SEV_027() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserSelectPayDayAndConfirmMonthlyInstallmentSummary_TDB_QLV_27(loanType);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 20,dependsOnMethods = {"TDB_SEV_025"})
    public void TDB_SEV_028() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserCanSignLoanDocumentsDisburseLoanSuccessfully_TDB_QLV_28();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 21,dependsOnMethods = {"TDB_SEV_028"})
    public void TDB_SEV_029() throws Exception {
        try {
            flexPivotWithoutVASModule.verifyLoansTileStatusAfterSuccessfulLoanDisbursal_TDB_FP_027();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 22,dependsOnMethods = {"TDB_SEV_029"})
    public void TDB_SEV_031() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserViewLoanDashboardScreen_TDB_QLV_031(loanAmount,flexPivotLoanTenure,flexPivotInterestRate,loanType);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 23,dependsOnMethods = {"TDB_SEV_029"})
    public void TDB_SEV_032() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserCanAccessPayHingaTileFromLoanDashboardScreen_TDB_QLV_032();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 24,dependsOnMethods = {"TDB_SEV_029"})
    public void TDB_SEV_033() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserCanAccessLifeInsuranceTileFromLoanDashboardScreen_TDB_QLV_033();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 25,dependsOnMethods = {"TDB_SEV_029"})
    public void TDB_SEV_034() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserAccessLoanInformationScreen_TDB_QLV_034(loanAmount,flexPivotLoanTenure,flexPivotInterestRate,loanType);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 26,dependsOnMethods = {"TDB_SEV_029"})
    public void TDB_SEV_035() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserCanViewRatesFeesDetails_TDB_QLV_035(loanAmount,flexPivotLoanTenure, flexPivotInterestRate,loanType);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 27,dependsOnMethods = {"TDB_SEV_029"})
    public void TDB_SEV_036() throws Exception {
        try {
            quickLoanWithVasModule.userCanViewAndDownloadAllLoanDocumentsValidation_TDB_QLV_036(loanType);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 28,dependsOnMethods = {"TDB_SEV_029"})
    public void TDB_SEV_037() throws Exception {
        try {
            quickLoanWithVasModule.verifyTsaBalanceInMainScreenAfterLoanDisbursal_TDB_QLV_037(loanAmount,flexPivotLoanTenure);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 29,dependsOnMethods = {"TDB_SEV_029"})
    public void TDB_SEV_038() throws Exception {
        try {
            quickLoanWithVasModule.loanTransactionDetailsValidationFromHistoryScreen_TDB_QLV_038(loanAmount,mobileNumber,flexPivotLoanTenure);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 30,dependsOnMethods = {"TDB_SEV_029"})
    public void TDB_SEV_039() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserCanViewLoanPaymentScreen_TDB_QLV_039(Integer.parseInt(propertyFileReader("DueDay", "QuickLoanWithVas")),mobileNumber);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 31,dependsOnMethods = {"TDB_SEV_029"})
    public void TDB_SEV_110() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserMakeFullRepaymentWithInsufficientTSABalance_TDB_QLV_110(loanAmount,flexPivotLoanTenure,flexPivotInterestRate,loanType);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 32,dependsOnMethods = {"TDB_SEV_029"})
    public void TDB_SEV_111() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserCanMakeFullRepaymentWithSufficientTSABalance_TDB_QLV_111(loanAmount,flexPivotLoanTenure,flexPivotInterestRate,mobileNumber,loanType);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 33,dependsOnMethods = {"TDB_SEV_110"})
    public void TDB_SEV_113() throws Exception {
        try {
            quickLoanWithVasModule.verifyLoansTileStatusAfterFullLoanRepayment_TDB_QLV_113();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 34,dependsOnMethods = {"TDB_SEV_110"})
    public void TDB_SEV_114() throws Exception {
        try {
            sevenElevenWithVASModule.verifyUserCanReapplySevenElevenAfterFullLoanRepayment_TDB_SEV_114(propertyFileReader("password","Login"),mobileNumber);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 35,dependsOnMethods = {"TDB_SEV_110"})
    public void TDB_SEV_121() throws Exception {
        try {
            sevenElevenWithVASModule.userCanQuitLoanApplicationValidation_TDB_SEV_121(mobileNumber);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 36,dependsOnMethods = {"TDB_SEV_110"})
    public void TDB_SEV_120() throws Exception {
        try {
            sevenElevenWithVASModule.verifySKYCUserCanApplySevenElevenWithVASLoanTSACreatedAlready_TDB_SEV_120(mobileNumber,flexPivotLoanTenure);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 37,dependsOnMethods = {"TDB_SEV_120"})
    public void TDB_SEV_041()throws Exception {
        try{
            quickLoanWithVasModule.verifyUserCanMakePaymentViaTonikAccount_TDB_QLV_041(Integer.parseInt(propertyFileReader("DueDay","QuickLoanWithVas")),mobileNumber,loanType);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 38,dependsOnMethods = {"TDB_SEV_120"})
    public void TDB_SEV_060()throws Exception {
        try{
            quickLoanWithVasModule.verifyUserCanActivatePayHingaWithNoOutStandingDue_TDB_QLV_060(61,30,mobileNumber,loanAmount,flexPivotLoanTenure,flexPivotInterestRate);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 39)
    public void TDB_SEV_118() throws Exception {
        try {
            sevenElevenWithVASModule.bkycUserCanApplySevenelevenLoanIfTSAIsNotCreatedValidation_TDB_SEV_118(propertyFileReader("BKYCPassword", "Login"),propertyFileReader("BKYCMobileNumber","TestDataNumbers"));
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 40)
    public void TDB_SEV_128()throws Exception {
        try{
            sevenElevenWithVASModule.verifyLoansTileIfUserNotAcceptLoanOfferIn30Days_TDB_SEV_128(password,mobileNumber);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 41)
    public void TDB_SEV_020() throws Exception {
        try {
            sevenElevenWithVASModule.loanSoftRejectionValidation_TDB_SEV_020(password,propertyFileReader("SoftRejectMobileNumber","TestDataNumbers"));
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 42)
    public void TDB_SEV_021() throws Exception {
        try {
            sevenElevenWithVASModule.loanHardRejectionValidation_TDB_SEV_021(password,propertyFileReader("HardRejectMobileNumber","TestDataNumbers"));
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
}