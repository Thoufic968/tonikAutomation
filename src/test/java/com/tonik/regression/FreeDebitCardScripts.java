package com.tonik.regression;
import static com.tonik.utility.Utilities.softAssert;
import org.testng.annotations.Test;
public class FreeDebitCardScripts extends BaseTest {
    @Test(priority = 1)
    public void TDB_FPC_001() throws Exception {
        try {
            debitCardModule.verifyUserReceiveFreeDebitCard_TDB_PC_013();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 2)
    public void TDB_FPC_002() throws Exception {
        try {
            debitCardModule.verifyUserCanConfirmAddressWhileOrderingDebitCard_TDB_PC_003();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 3)
    public void TDB_FPC_003() throws Exception {
        try {
            debitCardModule.verifyUserCanOrderDebitCard_TDB_PC_004("Free");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
}
