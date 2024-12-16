package com.tonik.regression;

import org.testng.annotations.Test;
import static com.tonik.utility.Utilities.propertyFileReader;
import static com.tonik.utility.Utilities.softAssert;
public class FlexPivotWithoutScripts extends BaseTest{
    String mobileNumber = propertyFileReader("FlexPivotMobileNumber","TestDataNumbers");
    String referenceContact = propertyFileReader("FlexPivotReferenceContact","TestDataNumbers");
    String password = propertyFileReader("password", "Login");
    String loanAmount = propertyFileReader("FlexPivotLoanAmount", "FlexPivot");
    String flexPivotLoanTenure = propertyFileReader("loanTenure", "FlexPivot");
    String flexPivotInterestRate = propertyFileReader("FlexPivotInterestRate", "FlexPivot");
    String loanType = "WithoutVAS";
    @Test(priority = 1)
    public void TDB_FP_001() throws Exception {
        try {
            flexPivotWithoutVASModule.verifyUserCanAccessFlexPivotLoanFromMainDashBoard_TDB_FP_001(password,mobileNumber);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 2)
    public void TDB_FP_002() throws Exception {
        try {
            flexPivotWithoutVASModule.verifyUserCanAccessFlexPivotLoanFromMainDashBoardWithNoPopup_TDB_FP_002(password,mobileNumber);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 3)
    public void TDB_FP_005() throws Exception {
        try {
            flexPivotWithoutVASModule.verifyUserCanDeclineFlexLoanWithVASOfferFromAreYouSurePopup_TDB_FP_005(mobileNumber,flexPivotLoanTenure);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 4,dependsOnMethods = {"TDB_FP_005"})
    public void TDB_FP_006() throws Exception {
        try {
            quickLoanWithVasModule.reasonForLoanApplicationValidation_TDB_QLV_009();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 5,dependsOnMethods = {"TDB_FP_005"})
    public void TDB_FP_007() throws Exception {
        try {
            quickLoanWithVasModule.selectFieldOfWorkValidation_TDB_QLV_010();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 6,dependsOnMethods = {"TDB_FP_005"})
    public void TDB_FP_008() throws Exception {
        try {
            quickLoanWithVasModule.selectOccupationValidation_TDB_QLV_011();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 7,dependsOnMethods = {"TDB_FP_005"})
    public void TDB_FP_009() throws Exception {
        try {
            quickLoanWithVasModule.selectIndustryAndSubIndustryValidation_TDB_QLV_012();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 8,dependsOnMethods = {"TDB_FP_005"})
    public void TDB_FP_010() throws Exception {
        try {
            quickLoanWithVasModule.selectMaritalStatusValidation_TDB_QLV_013();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 9,dependsOnMethods = {"TDB_FP_005"})
    public void TDB_FP_011() throws Exception {
        try {
            quickLoanWithVasModule.selectEducationalDetails_TDB_QLV_014();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 10,dependsOnMethods = {"TDB_FP_005"})
    public void TDB_FP_012() throws Exception {
        try {
            quickLoanWithVasModule.inputReferenceContactDetailsValidation_TDB_QLV_015();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 11,dependsOnMethods = {"TDB_FP_005"})
    public void TDB_FP_013() throws Exception {
        try {
            quickLoanWithVasModule.selectYourCurrentLivingCityValidation_TDB_QLV_016();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 12,dependsOnMethods = {"TDB_FP_005"})
    public void TDB_FP_014() throws Exception {
        try {
            flexPivotWithoutVASModule.inputMonthlyIncomeTINDetails_TDB_FP_014();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 13,dependsOnMethods = {"TDB_FP_005"})
    public void TDB_FP_015() throws Exception {
        try {
            quickLoanWithVasModule.inputSecondaryContactDetailsValidation_TDB_QLV_018(mobileNumber,referenceContact);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 14,dependsOnMethods = {"TDB_FP_005"})
    public void TDB_FP_016() throws Exception {
        try {
            quickLoanWithVasModule.acceptTermsAndConditionValidation_TDB_QLV_019();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 15,dependsOnMethods = {"TDB_FP_016"})
    public void TDB_FP_022() throws Exception {
        try {
            quickLoanWithVasModule.verifyLoanTileIfUserPreApproved_TDB_QLV_025(mobileNumber);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 16,dependsOnMethods = {"TDB_FP_022"})
    public void TDB_FP_023() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserAcceptLoanOfferFromLoanOfferScreen_TDB_QLV_026(loanAmount,loanType,loanAmount,flexPivotLoanTenure,flexPivotInterestRate);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 17,dependsOnMethods = {"TDB_FP_022"})
    public void TDB_FP_024() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserSelectPayDayAndConfirmMonthlyInstallmentSummary_TDB_QLV_27(loanType);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 18,dependsOnMethods = {"TDB_FP_022"})
    public void TDB_FP_026() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserCanSignLoanDocumentsDisburseLoanSuccessfully_TDB_QLV_28();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 19,dependsOnMethods = {"TDB_FP_026"})
    public void TDB_FP_027() throws Exception {
        try {
            flexPivotWithoutVASModule.verifyLoansTileStatusAfterSuccessfulLoanDisbursal_TDB_FP_027();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 20,dependsOnMethods = {"TDB_FP_026"})
    public void TDB_FP_029() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserViewLoanDashboardScreen_TDB_QLV_031(loanAmount,flexPivotLoanTenure,flexPivotInterestRate,loanType);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 21,dependsOnMethods = {"TDB_FP_026"})
    public void TDB_FP_030() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserAccessLoanInformationScreen_TDB_QLV_034(loanAmount,flexPivotLoanTenure,flexPivotInterestRate,loanType);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 22,dependsOnMethods = {"TDB_FP_026"})
    public void TDB_FP_031() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserCanViewRatesFeesDetails_TDB_QLV_035(loanAmount,flexPivotLoanTenure,flexPivotInterestRate,loanType);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 23,dependsOnMethods = {"TDB_FP_026"})
    public void TDB_FP_032() throws Exception {
        try {
            quickLoanWithVasModule.userCanViewAndDownloadAllLoanDocumentsValidation_TDB_QLV_036(loanType);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 24,dependsOnMethods = {"TDB_FP_026"})
    public void TDB_FP_033() throws Exception {
        try {
            quickLoanWithVasModule.verifyTsaBalanceInMainScreenAfterLoanDisbursal_TDB_QLV_037(loanAmount,flexPivotLoanTenure);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 25,dependsOnMethods = {"TDB_FP_026"})
    public void TDB_FP_034() throws Exception {
        try {
            quickLoanWithVasModule.loanTransactionDetailsValidationFromHistoryScreen_TDB_QLV_038(loanAmount,loanAmount,flexPivotLoanTenure);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 26,dependsOnMethods = {"TDB_FP_026"})
    public void TDB_FP_035() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserCanViewLoanPaymentScreen_TDB_QLV_039(Integer.parseInt(propertyFileReader("DueDay","QuickLoanWithVas")),mobileNumber);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 27,dependsOnMethods = {"TDB_FP_026"})
    public void TDB_FP_037() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserCanMakePaymentViaTonikAccount_TDB_QLV_041(Integer.parseInt(propertyFileReader("DueDay","QuickLoanWithVas")),mobileNumber,loanType);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 28,dependsOnMethods = {"TDB_FP_026"})
    public void TDB_FP_045() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserMakeFullRepaymentWithInsufficientTSABalance_TDB_QLV_110(loanAmount,flexPivotLoanTenure,flexPivotInterestRate,loanType);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 29,dependsOnMethods = {"TDB_FP_026"})
    public void TDB_FP_046() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserCanMakeFullRepaymentWithSufficientTSABalance_TDB_QLV_111(loanAmount,flexPivotLoanTenure,flexPivotInterestRate,mobileNumber,loanType);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 30,dependsOnMethods = {"TDB_FP_046"})
    public void TDB_FP_048() throws Exception {
        try {
            quickLoanWithVasModule.verifyLoansTileStatusAfterFullLoanRepayment_TDB_QLV_113();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 31,dependsOnMethods = {"TDB_FP_046"})
    public void TDB_FP_049() throws Exception {
        try {
            flexPivotWithoutVASModule.verifyUserCanReapplyFlexPivotWithoutVASAfterFullLoanRepayment_TDB_FP_049(password,mobileNumber);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 32,dependsOnMethods = {"TDB_FP_046"})
    public void TDB_FP_056() throws Exception {
        try {
            flexPivotWithoutVASModule.userCanQuitLoanApplicationValidation_TDB_FP_056(mobileNumber);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 33,dependsOnMethods = {"TDB_FP_056"})
    public void TDB_FP_055() throws Exception {
        try {
            flexPivotWithoutVASModule.verifySKYCUserCanApplyFlexPivotWithoutVASTSACreatedAlready_TDB_FP_055(mobileNumber,loanType,flexPivotLoanTenure);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
}