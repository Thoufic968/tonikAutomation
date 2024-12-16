package com.tonik.regression;

import org.testng.annotations.Test;
import static com.tonik.utility.Utilities.propertyFileReader;
import static com.tonik.utility.Utilities.softAssert;
public class ShopInstallmentLoanWithoutVasScripts extends BaseTest{
    String password = propertyFileReader("password","Login");
    String mobileNumber = propertyFileReader("SILMobileNumber","TestDataNumbers");
    @Test(priority = 1)
    public void TDB_SIL_004() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyIfNewlyOnboardedUserCanAccessTheShopInstallmentLoanFromTheMainDashboard_TDB_SIL_004(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 2)
    public void TDB_SIL_010() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyUserCanAcceptTheLoanOfferWithoutVASFromTheLoanSummaryScreen_TDB_SIL_010(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 3,dependsOnMethods = {"TDB_SIL_010"})
    public void TDB_SIL_011() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyUserCanSelectFieldOfWork_TDB_SIL_011(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 4,dependsOnMethods = {"TDB_SIL_010"})
    public void TDB_SIL_012() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyUserCanSelectTheOccupationDetails_TDB_SIL_012(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 5,dependsOnMethods = {"TDB_SIL_010"})
    public void TDB_SIL_013() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyUserCanSelectIndustryAndSubIndustryOptions_TDB_SIL_013(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 6,dependsOnMethods = {"TDB_SIL_010"})
    public void TDB_SIL_014() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyUserCanSelectMaritalStatus_TDB_SIL_014(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 7,dependsOnMethods = {"TDB_SIL_010"})
    public void TDB_SIL_015() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyUserCanSelectDependents_TDB_SIL_015(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 8,dependsOnMethods = {"TDB_SIL_010"})
    public void TDB_SIL_016() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyUserCanSelectTheEducationalDetails_TDB_SIL_016(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 9,dependsOnMethods = {"TDB_SIL_010"})
    public void TDB_SIL_017() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyUserCanInputReferenceContactDetails_TDB_SIL_017(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 10,dependsOnMethods = {"TDB_SIL_010"})
    public void TDB_SIL_018() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyUserCanSelectCurrentLivingCity_TDB_SIL_018(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 11,dependsOnMethods = {"TDB_SIL_010"})
    public void TDB_SIL_019() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyUserCanInputTheMonthlyIncomeCompanyNameAndTINDetails_TDB_SIL_019(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 12,dependsOnMethods = {"TDB_SIL_010"})
    public void TDB_SIL_020() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyUserCanInputSecondaryContactDetails_TDB_SIL_020(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 13,dependsOnMethods = {"TDB_SIL_010"})
    public void TDB_SIL_021() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyUserCanAcceptTermsAndConditions_TDB_SIL_021(password,mobileNumber);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 14,dependsOnMethods = {"TDB_SIL_021"})
    public void TDB_SIL_027() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyLoanTileIfTheUserIsPreApproved_TDB_SIL_027(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 15,dependsOnMethods = {"TDB_SIL_021"})
    public void TDB_SIL_028() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.VerifyUserCanAcceptTheLoanOfferFromLoanOfferScreen_TDB_SIL_028(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 16,dependsOnMethods = {"TDB_SIL_021"})
    public void TDB_SIL_029() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyUserCanSelectPayDayAndConfirmMonthlyInstallmentSummary_TDB_SIL_029(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 17,dependsOnMethods = {"TDB_SIL_021"})
    public void TDB_SIL_031() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyUserCanSignAllTheLoanDocumentsAndDisburseTheLoanSuccessfully_TDB_SIL_031(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 18,dependsOnMethods = {"TDB_SIL_021"})
    public void TDB_SIL_032() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyUserCanViewAndAccessTimeToClaimYourItemScreen_TDB_SIL_032(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 19,dependsOnMethods = {"TDB_SIL_021"})
    public void TDB_SIL_033() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyUserCanRequestConfirmationCodeFromTheCashier_TDB_SIL_033(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 20,dependsOnMethods = {"TDB_SIL_021"})
    public void TDB_SIL_034() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyUserCanInputConfirmationCodeValidation_TDB_SIL_034(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 21,dependsOnMethods = {"TDB_SIL_034"})
    public void TDB_SIL_035() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyTheLoansTileStatusIsChangedAfterTheSuccessfulLoanDisbursal_TDB_SIL_035(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 22,dependsOnMethods = {"TDB_SIL_034"})
    public void TDB_SIL_037() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyUserCanViewTheLoanDashboardScreen_TDB_SIL_037(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 23,dependsOnMethods = {"TDB_SIL_034"})
    public void TDB_SIL_038() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyUserCanAccessTheLoanInformationScreen_TDB_SIL_038(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 24,dependsOnMethods = {"TDB_SIL_034"})
    public void TDB_SIL_039() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyUserCanViewRatesFeesDetails_TDB_SIL_039(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 25,dependsOnMethods = {"TDB_SIL_034"})
    public void TDB_SIL_040() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyUserCanViewAndDownloadAllTheLoanDocuments_TDB_SIL_040(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 26,dependsOnMethods = {"TDB_SIL_034"})
    public void TDB_SIL_043() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyUserCanViewLoanPaymentScreen_TDB_SIL_043(password,Integer.parseInt(propertyFileReader("DueDateDays","ShopInstallmentLoan")));
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 27,dependsOnMethods = {"TDB_SIL_034"})
    public void TDB_SIL_045() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyUserCanMakePaymentViaTonikAccount_TDB_SIL_045(password,Integer.parseInt(propertyFileReader("DueDateDays","ShopInstallmentLoan")));
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 28,dependsOnMethods = {"TDB_SIL_034"})
    public void TDB_SIL_055_057() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyLoansTileStatusAfterTheFullLoanRepaymentAndReApllySIL_TDB_SILV_055_057(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 29,dependsOnMethods = {"TDB_SIL_055_057"})
    public void TDB_SIL_063() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifySKYCUserCanApplySILifTSAIsCreatedAlready_TDB_SIL_063(password,mobileNumber);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 30,dependsOnMethods = {"TDB_SIL_063"})
    public void TDB_SIL_064() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyUserCanQuitTheLoanApplication_TDB_SIL_064(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 31)
    public void TDB_SIL_059() throws Exception {
        try {
            shopInstallmentLoanWithoutVasModule.verifyLoansTileStatusIfBKYCUserIsNotUpgradedToSKYC_TDB_SIL_059(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
}