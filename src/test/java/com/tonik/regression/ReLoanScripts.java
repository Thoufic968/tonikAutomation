package com.tonik.regression;

import org.testng.annotations.Test;
import static com.tonik.utility.Utilities.propertyFileReader;
import static com.tonik.utility.Utilities.softAssert;
public class ReLoanScripts extends BaseTest{
    String password = propertyFileReader("password","Login");
    @Test(priority = 1)
    public void TDB_RL_001() throws Exception {
        try {
            reloanModule.verifyLoanTileForReloanOffer_TDB_RL_001(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 2)
    public void TDB_RL_003() throws Exception {
        try {
            reloanModule.verifyUserCanDeclineTheReloanOffer_TDB_RL_003(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 3)
    public void TDB_RL_004() throws Exception {
        try {
            reloanModule.verifyUserCanAcceptTheReloanOffer_TDB_RL_004(password,propertyFileReader("Month1","Reloan"));
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 4)
    public void TDB_RL_005() throws Exception {
        try {
            reloanModule.verifyUserCanSelectReloanReason_TDB_RL_005(password,propertyFileReader("LoanPurpose","Reloan"));
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 5)
    public void TDB_RL_023() throws Exception {
        try {
            reloanModule.verifyUserCanDeclineTheReloanOfferClosedFlexLoan_TDB_RL_023(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 6)
    public void TDB_RL_024() throws Exception {
        try {
            reloanModule.verifyUserCanAcceptTheReloanOfferClosedFlexLoan_TDB_RL_024(password,propertyFileReader("Month2","Reloan"));
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 7)
    public void TDB_RL_006() throws Exception {
        try {
           reloanModule.verifyIfUserCanAcceptTermsAndConditions_TDB_RL_006(password,propertyFileReader("LoanPurpose","Reloan"));
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 8)
    public void TDB_RL_007() throws Exception {
        try {
            reloanModule.verifyUserCanSelectAPayDays_TDB_RL_007(password,propertyFileReader("LoanPurpose","Reloan"));
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 9)
    public void TDB_RL_008() throws Exception {
        try {
            reloanModule.verifyUserCanConfirmMonthlyInstallmentSummary_TDB_RL_008(password,propertyFileReader("LoanPurpose","Reloan"));
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 10)
    public void TDB_RL_009() throws Exception {
        try {
            reloanModule.verifyUserCanSignAllLoanDocuments_TDB_RL_009(password,propertyFileReader("LoanPurpose","Reloan"));
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 11)
    public void TDB_RL_010() throws Exception {
        try {
            reloanModule.verifyReloanAmountIsDisbursedSuccessfully_TDB_RL_010(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 12)
    public void TDB_RL_011() throws Exception {
        try {
            reloanModule.verifyUserCanViewAccountHistoryOfDisburseReloan_TDB_RL_011(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 13)
    public void TDB_RL_012() throws Exception {
        try {
            reloanModule.verifyUserCanViewLoanDashboardAfterSuccessfullyReloanDisbursement_TDB_RL_012(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 14)
    public void TDB_RL_013() throws Exception {
        try {
            reloanModule.verifyUserCanViewLoanInformationScreen_TDB_RL_013(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 15)
    public void TDB_RL_014() throws Exception {
        try {
            reloanModule.verifyUserCanViewRatesAndFeesScreen_TDB_RL_014(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 16)
    public void TDB_RL_015() throws Exception {
        try {
            reloanModule.verifyUserCanViewAndDownloadLoanDocuments_TDB_RL_015(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 17)
    public void TDB_RL_016() throws Exception {
        try {
            reloanModule.verifyUserCanMakeFullRepaymentWithInsufficientTSABalance_TDB_RL_016(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 18)
    public void TDB_RL_017() throws Exception {
        try {
            reloanModule.verifyUserCanMakeFullPaymentWithSufficientTSABalance_TDB_RL_017(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 19)
    public void TDB_RL_019() throws Exception {
        try {
            reloanModule.verifyLoanTileStatusAfterFullLoanRepaymentOfReloan_TDB_RL_019(password);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 20)
    public void reloanEndToEndJourney() throws Exception {
        try {
            reloanModule.reloanEndToEndJourney(propertyFileReader("Month0","Reloan"),"WithoutVAS");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
}