package com.tonik.regression;

import org.testng.annotations.Test;
import static com.tonik.utility.Utilities.propertyFileReader;
import static com.tonik.utility.Utilities.softAssert;

public class QuickLoanWithoutVasScripts extends BaseTest{
    String mobileNumber = propertyFileReader("QuickLoanMobileNumber","TestDataNumbers");
    String referenceContact = propertyFileReader("QuickLoanReferenceContact","TestDataNumbers");
    String loanAmount = propertyFileReader("loanAmount", "QuickLoanWithVas");
    String qlLoanTenure = propertyFileReader("LoanTenure", "QuickLoanWithVas");
    String qlInterestRate = propertyFileReader("QLInterestRate", "QuickLoanWithVas");
    @Test(priority = 1)
    public void TDB_QL_001() throws Exception {
        try {
            quickLoanWithOutVasModule.onBoardAsNewSKYCUserWithNoTSA_TDB_QL_001(loanAmount);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 2)
    public void TDB_QL_002() throws Exception {
        try {
            quickLoanWithVasModule.userCanEditAmountInHowMuchDoYouNeedScreen_TDB_QLV_005();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 3)
    public void TDB_QL_004() throws Exception {
        try {
            quickLoanWithOutVasModule.userCanAcceptQuickLoanWithVASOfferFromAreYouSurePopup_TDB_QL_004(loanAmount,qlLoanTenure,qlInterestRate);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 4)
    public void TDB_QL_005() throws Exception {
        try {
            quickLoanWithOutVasModule.userCanDeclineQuickLoanWithVASOfferFromAreYouSurePopup_TDB_QL_005(loanAmount,qlLoanTenure,qlInterestRate);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 5, dependsOnMethods = {"TDB_QL_005"})
    public void TDB_QL_006() throws Exception {
        try {
            quickLoanWithVasModule.reasonForLoanApplicationValidation_TDB_QLV_009();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 6, dependsOnMethods = {"TDB_QL_005"})
    public void TDB_QL_007() throws Exception {
        try {
            quickLoanWithVasModule.selectFieldOfWorkValidation_TDB_QLV_010();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 7, dependsOnMethods = {"TDB_QL_005"})
    public void TDB_QL_008() throws Exception {
        try {
            quickLoanWithVasModule.selectOccupationValidation_TDB_QLV_011();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 8, dependsOnMethods = {"TDB_QL_005"})
    public void TDB_QL_009() throws Exception {
        try {
            quickLoanWithVasModule.selectIndustryAndSubIndustryValidation_TDB_QLV_012();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 9, dependsOnMethods = {"TDB_QL_005"})
    public void TDB_QL_010() throws Exception {
        try {
            quickLoanWithVasModule.selectMaritalStatusValidation_TDB_QLV_013();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 10, dependsOnMethods = {"TDB_QL_005"})
    public void TDB_QL_011() throws Exception {
        try {
            quickLoanWithVasModule.selectEducationalDetails_TDB_QLV_014();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 11, dependsOnMethods = {"TDB_QL_005"})
    public void TDB_QL_012() throws Exception {
        try {
            quickLoanWithVasModule.inputReferenceContactDetailsValidation_TDB_QLV_015();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 12, dependsOnMethods = {"TDB_QL_005"})
    public void TDB_QL_013() throws Exception {
        try {
            quickLoanWithVasModule.selectYourCurrentLivingCityValidation_TDB_QLV_016();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 13, dependsOnMethods = {"TDB_QL_005"})
    public void TDB_QL_014() throws Exception {
        try {
            quickLoanWithVasModule.inputMonthlyIncomeCompanyNameTINDetails_TDB_QLV_017();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 14, dependsOnMethods = {"TDB_QL_005"})
    public void TDB_QL_015() throws Exception {
        try {
            quickLoanWithVasModule.inputSecondaryContactDetailsValidation_TDB_QLV_018(mobileNumber,referenceContact);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 15, dependsOnMethods = {"TDB_QL_005"})
    public void TDB_QL_016() throws Exception {
        try {
            quickLoanWithVasModule.acceptTermsAndConditionValidation_TDB_QLV_019();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 16, dependsOnMethods = {"TDB_QL_016"})
    public void TDB_QL_022() throws Exception {
        try {
            quickLoanWithVasModule.verifyLoanTileIfUserPreApproved_TDB_QLV_025(mobileNumber);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 17, dependsOnMethods = {"TDB_QL_022"})
    public void TDB_QL_023() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserAcceptLoanOfferFromLoanOfferScreen_TDB_QLV_026(loanAmount,"WithoutVAS",loanAmount,qlLoanTenure,qlInterestRate);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 18, dependsOnMethods = {"TDB_QL_022"})
    public void TDB_QL_024() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserSelectPayDayAndConfirmMonthlyInstallmentSummary_TDB_QLV_27("WithoutVAS");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 19, dependsOnMethods = {"TDB_QL_022"})
    public void TDB_QL_026() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserCanSignLoanDocumentsDisburseLoanSuccessfully_TDB_QLV_28();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 20, dependsOnMethods = {"TDB_QL_026"})
    public void TDB_QL_027() throws Exception {
        try {
            quickLoanWithVasModule.verifyLoansTileStatusAfterSuccessfulLoanDisbursal_TDB_QLV_029();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 21, dependsOnMethods = {"TDB_QL_026"})
    public void TDB_QL_029() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserViewLoanDashboardScreen_TDB_QLV_031(loanAmount,qlLoanTenure,qlInterestRate,"WithoutVAS");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 22, dependsOnMethods = {"TDB_QL_026"})
    public void TDB_QL_030() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserAccessLoanInformationScreen_TDB_QLV_034(loanAmount,qlLoanTenure,qlInterestRate,"WithoutVAS");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 23, dependsOnMethods = {"TDB_QL_026"})
    public void TDB_QL_031() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserCanViewRatesFeesDetails_TDB_QLV_035(loanAmount,qlLoanTenure,qlInterestRate,"WithoutVAS");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 24, dependsOnMethods = {"TDB_QL_026"})
    public void TDB_QL_032() throws Exception {
        try {
            quickLoanWithVasModule.userCanViewAndDownloadAllLoanDocumentsValidation_TDB_QLV_036("WithoutVAS");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 25, dependsOnMethods = {"TDB_QL_026"})
    public void TDB_QL_033() throws Exception {
        try {
            quickLoanWithVasModule.verifyTsaBalanceInMainScreenAfterLoanDisbursal_TDB_QLV_037(loanAmount,qlLoanTenure);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 26, dependsOnMethods = {"TDB_QL_026"})
    public void TDB_QL_034() throws Exception {
        try {
            quickLoanWithVasModule.loanTransactionDetailsValidationFromHistoryScreen_TDB_QLV_038(loanAmount,mobileNumber,qlLoanTenure);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 27, dependsOnMethods = {"TDB_QL_026"})
    public void TDB_QL_035() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserCanViewLoanPaymentScreen_TDB_QLV_039(Integer.parseInt(propertyFileReader("DueDay","QuickLoanWithVas")),mobileNumber);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 28, dependsOnMethods = {"TDB_QL_026"})
    public void TDB_QL_037() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserCanMakePaymentViaTonikAccount_TDB_QLV_041(Integer.parseInt(propertyFileReader("DueDay","QuickLoanWithVas")),mobileNumber,"WithoutVAS");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 29, dependsOnMethods = {"TDB_QL_026"})
    public  void TDB_QL_045() throws Exception{
        try {
            quickLoanWithVasModule.verifyUserMakeFullRepaymentWithInsufficientTSABalance_TDB_QLV_110(loanAmount,qlLoanTenure,qlInterestRate,"WithoutVAS");
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 30, dependsOnMethods = {"TDB_QL_026"})
    public void TDB_QL_046() throws Exception {
        try {
            quickLoanWithVasModule.verifyUserCanMakeFullRepaymentWithSufficientTSABalance_TDB_QLV_111(loanAmount,qlLoanTenure,qlInterestRate,mobileNumber,"WithoutVAS");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 31, dependsOnMethods = {"TDB_QL_046"})
    public void TDB_QL_048() throws Exception {
        try {
            quickLoanWithVasModule.verifyLoansTileStatusAfterFullLoanRepayment_TDB_QLV_113();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 32, dependsOnMethods = {"TDB_QL_048"})
    public void TDB_QL_049() throws Exception {
        try {
            quickLoanWithOutVasModule.verifyUserCanReapplyQuickLoanAfterFullLoanRepayment_TDB_QL_049();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 33, dependsOnMethods = {"TDB_QL_049"})
    public void TDB_QL_051() throws Exception {
        try {
            quickLoanWithVasModule.bkycUserCanApplyQuickLoanIfTSAIsNotCreatedValidation_TDB_QLV_118(propertyFileReader("LoanAmount","QuickLoanWithVas"));
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 34, dependsOnMethods = {"TDB_QL_051"})
    public void TDB_QL_056() throws Exception {
        try {
            quickLoanWithVasModule.userCanQuitLoanApplicationValidation_TDB_QLV_121(loanAmount,mobileNumber);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 35, dependsOnMethods = {"TDB_QL_056"})
    public void TDB_QL_055() throws Exception {
        try {
            quickLoanWithOutVasModule.verifySKYCUserCanApplyQuickLoanTSACreatedAlready_TDB_QL_055(mobileNumber,loanAmount,qlLoanTenure,qlInterestRate);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
}