package com.tonik.regression;

import org.testng.annotations.Test;
import static com.tonik.utility.Utilities.propertyFileReader;
import static com.tonik.utility.Utilities.softAssert;
public class ShopInstallmentLoanWithVasScripts extends BaseTest {
    String password = propertyFileReader("password","Login");
    String mobileNumber = propertyFileReader("SILMobileNumber","TestDataNumbers");
    @Test(priority = 1)
    public void TDB_SILV_004() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyIfNewlyOnboardedUserCanAccessTheShopInstallmentLoanFromTheMainDashboard_TDB_SILV_004(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 2)
    public void TDB_SILV_010() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyUserCanAcceptTheLoanOfferWithVASFromTheLoanSummaryScreen_TDB_SILV_010(password,mobileNumber);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 3,dependsOnMethods = {"TDB_SILV_010"})
    public void TDB_SILV_011() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyUserCanSelectFieldOfWork_TDB_SILV_011(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 4,dependsOnMethods = {"TDB_SILV_010"})
    public void TDB_SILV_012() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyUserCanSelectTheOccupationDetails_TDB_SILV_012(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 5,dependsOnMethods = {"TDB_SILV_010"})
    public void TDB_SILV_013() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyUserCanSelectIndustryAndSubIndustryOptions_TDB_SILV_013(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 6,dependsOnMethods = {"TDB_SILV_010"})
    public void TDB_SILV_014() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyUserCanSelectMaritalStatus_TDB_SILV_014(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 7,dependsOnMethods = {"TDB_SILV_010"})
    public void TDB_SILV_015() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyUserCanSelectDependents_TDB_SILV_015(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 8,dependsOnMethods = {"TDB_SILV_010"})
    public void TDB_SILV_016() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyUserCanSelectTheEducationalDetails_TDB_SILV_016(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 9,dependsOnMethods = {"TDB_SILV_010"})
    public void TDB_SILV_017() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyUserCanInputReferenceContactDetails_TDB_SILV_017(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 10,dependsOnMethods = {"TDB_SILV_010"})
    public void TDB_SILV_018() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyUserCanSelectCurrentLivingCity_TDB_SILV_018(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 11,dependsOnMethods = {"TDB_SILV_010"})
    public void TDB_SILV_019() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyUserCanInputTheMonthlyIncomeCompanyNameAndTINDetails_TDB_SILV_019(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 12,dependsOnMethods = {"TDB_SILV_010"})
    public void TDB_SILV_020() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyUserCanInputSecondaryContactDetails_TDB_SILV_020(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 13,dependsOnMethods = {"TDB_SILV_010"})
    public void TDB_SILV_021() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyUserCanAcceptTermsAndConditions_TDB_SILV_021(password,mobileNumber);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 14,dependsOnMethods = {"TDB_SILV_021"})
    public void TDB_SILV_027() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyLoanTileIfTheUserIsPreApproved_TDB_SILV_027(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 15,dependsOnMethods = {"TDB_SILV_021"})
    public void TDB_SILV_028() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.VerifyUserCanAcceptTheLoanOfferFromLoanOfferScreen_TDB_SILV_028(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 16,dependsOnMethods = {"TDB_SILV_021"})
    public void TDB_SILV_029() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyUserCanSelectPayDayAndConfirmMonthlyInstallmentSummary_TDB_SILV_029(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 17,dependsOnMethods = {"TDB_SILV_021"})
    public void TDB_SILV_031() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyUserCanSignAllTheLoanDocumentsAndDisburseTheLoanSuccessfully_TDB_SILV_031(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 18,dependsOnMethods = {"TDB_SILV_021"})
    public void TDB_SILV_032() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyUserCanViewAndAccessTimeToClaimYourItemScreen_TDB_SILV_032(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 19,dependsOnMethods = {"TDB_SILV_021"})
    public void TDB_SILV_033() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyUserCanRequestConfirmationCodeFromTheCashier_TDB_SILV_033(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 20,dependsOnMethods = {"TDB_SILV_021"})
    public void TDB_SILV_034() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyUserCanInputConfirmationCodeValidation_TDB_SILV_034(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 21,dependsOnMethods = {"TDB_SILV_021"})
    public void TDB_SILV_035() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyTheLoansTileStatusIsChangedAfterTheSuccessfulLoanDisbursal_TDB_SILV_035(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 22,dependsOnMethods = {"TDB_SILV_035"})
    public void TDB_SILV_037() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyUserCanViewTheLoanDashboardScreen_TDB_SILV_037(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 23,dependsOnMethods = {"TDB_SILV_035"})
    public void TDB_SILV_038() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyUserCanAccessThePayHingaTileFromTheLoanDashboardScreen_TDB_SILV_038(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 24,dependsOnMethods = {"TDB_SILV_035"})
    public void TDB_SILV_039() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyUserCanAccessLifeInsuranceTileFromLoanDashboardScreen_TDB_SILV_039(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 25,dependsOnMethods = {"TDB_SILV_035"})
    public void TDB_SILV_040() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyUserCanAccessTheLoanInformationScreen_TDB_SILV_040(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 26,dependsOnMethods = {"TDB_SILV_035"})
    public void TDB_SILV_041() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyUserCanViewRatesFeesDetails_TDB_QLV_041(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 27,dependsOnMethods = {"TDB_SILV_035"})
    public void TDB_SILV_042() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyUserCanViewAndDownloadAllTheLoanDocuments_TDB_QLV_042(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 28,dependsOnMethods = {"TDB_SILV_035"})
    public void TDB_SILV_045() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyUserCanViewLoanPaymentScreen_TDB_SILV_045(password,7);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 29,dependsOnMethods = {"TDB_SILV_035"})
    public void TDB_SILV_116() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyUserMakeFullRepaymentWithInsufficientTSABalance_TDB_SILV_116(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 30,dependsOnMethods = {"TDB_SILV_035"})
    public void TDB_SILV_117() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyUserCanMakeTopupFromLoanDashboardAndDoTheFullRepayment_TDB_SILV_117(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 31,dependsOnMethods = {"TDB_SILV_117"})
    public void TDB_SILV_119_120() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyLoansTileStatusAfterTheFullLoanRepayment_TDB_SILV_119_120(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 32,dependsOnMethods = {"TDB_SILV_119_120"})
    public void TDB_SILV_126() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifySKYCUserCanApplySILIfTSAIsCreatedAlready_TDB_SILV_126(password,mobileNumber);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 33,dependsOnMethods = {"TDB_SILV_126"})
    public void TDB_SILV_127() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyUserCanQuitTheLoanApplication_TDB_SILV_127(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 34)
    public void TDB_SILV_022() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyTheLoanTileIfTheUserIsSoftRejected_TDB_SILV_022(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 35)
    public void TDB_SILV_023() throws Exception {
        try {
            shopInstallmentLoanWithVasModule.verifyTheLoanTileIfTheUserIsHardRejected_TDB_SILV_023(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
}