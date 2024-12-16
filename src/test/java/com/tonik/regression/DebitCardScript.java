package com.tonik.regression;

import static com.tonik.utility.Utilities.softAssert;
import org.testng.annotations.Test;
public class DebitCardScript extends BaseTest {
    @Test(priority = 1)
    public void TDB_PC_001() throws Exception {
        try {
            debitCardModule.verifyTheUserCanAccessDebitCardTileIfTSAIsNotCreated_TDB_PC_001();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

	@Test(priority = 2)
    public void TDB_PC_002() throws Exception {
        try {
            debitCardModule.verifyUserCanAccessDebitCardTile_TDB_PC_002();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
	
	@Test(priority = 3)
    public void TDB_PC_003() throws Exception {
        try {
            debitCardModule.verifyUserCanConfirmAddressWhileOrderingDebitCard_TDB_PC_003();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
	
	@Test(priority = 4)
    public void TDB_PC_004() throws Exception {
        try {
            debitCardModule.verifyUserCanOrderDebitCard_TDB_PC_004("");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 6)
    public void TDB_PC_006() throws Exception {
        try {
            debitCardModule.verifyUserCanHideUnhideDetailsOfDebitCard_TDB_PC_006();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 7)
    public void TDB_PC_007() throws Exception {
        try {
            debitCardModule.verifyUserCanLockUnlockDebitCard_TDB_PC_007();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 8)
    public void TDB_PC_008() throws Exception {
        try {
            debitCardModule.verifyUserCanResetPinOfDebitCard_TDB_PC_008();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 9)
    public void TDB_PC_009() throws Exception {
        try {
            debitCardModule.verifyUserCanAccessTheSecurityFeaturesOfTheDebitCard_TDB_PC_009();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 10)
    public void TDB_PC_010() throws Exception {
        try {
            debitCardModule.verifyUserCanChangeDebitCardATMWithdrawalLimit_TDB_PC_010();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 11)
    public void TDB_PC_011() throws Exception {
        try {
            debitCardModule.verifyUserCanChangeDebitCardOnlinePhysicalPaymentsLimit_TDB_PC_011();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 12)
    public void TDB_PC_012() throws Exception {
        try {
            debitCardModule.verifyUserCanSeeDebitCardFees_TDB_PC_012();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
}