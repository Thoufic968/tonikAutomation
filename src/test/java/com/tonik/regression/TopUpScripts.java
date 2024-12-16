package com.tonik.regression;

import org.testng.annotations.Test;
import static com.tonik.utility.Utilities.propertyFileReader;
import static com.tonik.utility.Utilities.softAssert;

public class TopUpScripts extends BaseTest{
    @Test(priority = 1)
    public void TDB_TU_001() throws Exception {
        try {
            topUpModule.accessTopUpModuleIfTSANotCreated_TDB_TU_001();
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 2)
    public void TDB_TU_002() throws Exception {
        try {
            topUpModule.accessTopUpModuleIfTSAIsCreated_TDB_TU_002();
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 3)
    public void TDB_TU_003() throws Exception {
        try {
            topUpModule.topUpCopyAccountDetailsFromPESONetAndInstaPayValidation_TDB_TU_003();
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 4)
    public void TDB_TU_004() throws Exception {
        try {
            topUpModule.topUpViaGCashAccount_TDB_TU_004(propertyFileReader("TopUpMaxAmount","TopUp"),propertyFileReader("ValidAmount","TopUp"));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 5)
    public void TDB_TU_005() throws Exception {
        try {
            topUpModule.topUpBeyondMaxLimitOfGCashValidation_TDB_TU_005(propertyFileReader("MAXTopUpAmount","TopUp"));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 6)
    public void TDB_TU_007() throws Exception {
        try {
            topUpModule.topUpBeyondMaxLimitOfBPIValidation_TDB_TU_007(propertyFileReader("InvalidAmount","TopUp"));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 7)
    public void TDB_TU_009() throws Exception {
        try {
            topUpModule.topUpBeyondMaxLimitOfChinaBankValidation_TDB_TU_009(propertyFileReader("InvalidAmount","TopUp"));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 8)
    public void TDB_TU_011() throws Exception {
        try {
            topUpModule.topUpBeyondMaxLimitOfRCBCValidation_TDB_TU_011(propertyFileReader("InvalidAmount","TopUp"));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 9)
    public void TDB_TU_013() throws Exception {
        try {
            topUpModule.topUpBeyondMaxLimitOfUnionBankValidation_TDB_TU_013(propertyFileReader("MAXTopUpAmount","TopUp"));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 10)
    public void TDB_TU_017() throws Exception {
        try {
            topUpModule.topUpBKYCUserWhenHasMaxAccountBalance_TDB_TU_017("100");
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
}