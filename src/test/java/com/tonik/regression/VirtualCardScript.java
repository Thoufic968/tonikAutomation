package com.tonik.regression;

import org.testng.annotations.Test;

import static com.tonik.utility.Utilities.softAssert;

public class VirtualCardScript extends BaseTest{

    @Test(priority = 1)
    public void TDB_VC_001() throws Exception {
        try {
            virtualCardModule.verifyTheUserCanAccessVirtualCardTileIfTSAIsNotCreated_TDB_VC_001();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 2)
    public void TDB_VC_002() throws Exception {
        try {
            virtualCardModule.verifyTheUserCanAccessVirtualCardTileIfTSAisCreated_TDB_VC_002();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 3)
    public void TDB_VC_003() throws Exception {
        try {
            virtualCardModule.verifyIfUserCanHideUnHideTheDetailsOfTheVirtualCard_TDB_VC_003();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
        @Test(priority = 4)
    public void TDB_VC_004() throws Exception {
        try {
            virtualCardModule.verifyIfUserCanLockUnlockTheVirtualCard_TDB_VC_004();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 5)
    public void TDB_VC_005() throws Exception {
        try {
            virtualCardModule.verifyIfUserCanBlockTheVirtualCardFromOnlinePayments_TDB_VC_005();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 6)
    public void TDB_VC_006() throws Exception {
        try {
            virtualCardModule.verifyIfUserCanChangeTheVirtualCardsDailyOnlineAndPhysicalPaymentLimit_TDB_VC_006();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 7)
    public void TDB_VC_011() throws Exception {
        try {
            virtualCardModule.verifyIfUsersMaximumCardLimitIsUntil250000IfUserIsSKYC_TDB_VC_011();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 8)
    public void TDB_VC_010() throws Exception {
        try {
            virtualCardModule.verifyIfUsersMaximumCardLimitIsOnlyUntil40000IfUserIsBKYC_TDB_VC_010();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
}
