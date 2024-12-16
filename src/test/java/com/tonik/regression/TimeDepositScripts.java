package com.tonik.regression;

import org.testng.annotations.Test;
import static com.tonik.utility.Utilities.*;

public class TimeDepositScripts extends BaseTest {
    @Test(priority = 01)
    public void TDB_TD_001() throws Exception {
        try {
            timeDepositModule.timeDepositNewlyOnboardedUserWithNoTSA_TDB_TD_001(propertyFileReader("password","Login"));
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 02)
    public void TDB_TD_002() throws Exception {
        try {
            timeDepositModule.verifyUserCanCreateTSAFromTimeDeposit_TDB_TD_002(propertyFileReader("password","Login"));
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 03)
    public void TDB_TD_003() throws Exception {
        try {
            timeDepositModule.verifyUserCanVerifyTheEmailAddressFromTimeDeposit_TDB_TD_003(propertyFileReader("password","Login"));
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 04)
    public void TDB_TD_004() throws Exception {
        try {
            timeDepositModule.verifyTheInterestRateCalculatorScreen_TDB_TD_004(propertyFileReader("password","Login"),Double.parseDouble(propertyFileReader("Interest1","TimeDeposit")),Integer.parseInt(propertyFileReader("Month1","TimeDeposit")));
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 05)
    public void TDB_TD_005() throws Exception {
        try {
            timeDepositModule.verifyUserCanEditTheAmountAndSelectTheInstallmentMonth_TDB_TD_005(propertyFileReader("password","Login"),propertyFileReader("Interest2","TimeDeposit"),Integer.parseInt(propertyFileReader("Month2","TimeDeposit")));
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 06)
    public void TDB_TD_006() throws Exception {
        try {
            timeDepositModule.verifyIfUserCanOpenTimeDepositWith6MonthTerm_TDB_TD_006(propertyFileReader("password","Login"),"5" + RandomIntegerGenerator(3),propertyFileReader("Month0","TimeDeposit"),propertyFileReader("Interest0","TimeDeposit"));
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 07)
    public void TDB_TD_007() throws Exception {
        try {
            timeDepositModule.verifyIfUserCanOpenTimeDepositWith9MonthTerm_TDB_TD_007(propertyFileReader("password","Login"),"5" + RandomIntegerGenerator(3),propertyFileReader("Month1","TimeDeposit"),propertyFileReader("Interest1","TimeDeposit"));
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 8)
    public void TDB_TD_008() throws Exception {
        try {
            timeDepositModule.verifyIfUserCanOpenTimeDepositWith12MonthTerm_TDB_TD_008(propertyFileReader("password","Login"),"5" + RandomIntegerGenerator(3),propertyFileReader("Month2","TimeDeposit"),propertyFileReader("Interest2","TimeDeposit"));
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority =9)
    public void TDB_TD_009() throws Exception {
        try {
            timeDepositModule.verifyIfUserCanOpenTimeDepositWith18MonthTerm_TDB_TD_009(propertyFileReader("password","Login"),"5" + RandomIntegerGenerator(3),propertyFileReader("Month3","TimeDeposit"),propertyFileReader("Interest3","TimeDeposit"));
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 10)
    public void TDB_TD_010() throws Exception {
        try {
            timeDepositModule.verifyIfUserCanOpenTimeDepositWith24MonthTerm_TDB_TD_010(propertyFileReader("password","Login"),"5" + RandomIntegerGenerator(3),propertyFileReader("Month4","TimeDeposit"),propertyFileReader("Interest4","TimeDeposit"));
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 12)
    public void TDB_TD_012() throws Exception {
        try {
            timeDepositModule.verifyUserCanOpenTimeDepositWithInsufficientTSABalance_TDB_TD_012(propertyFileReader("password","Login"),"Insufficient",propertyFileReader("Month0","TimeDeposit"));
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 13)
    public void TDB_TD_013() throws Exception {
        try {
            timeDepositModule.verifyUserCanTopupTSAFromTD_TDB_TD_013(propertyFileReader("password","Login"),"5" + RandomIntegerGenerator(3),propertyFileReader("Month1","TimeDeposit"),propertyFileReader("Interest1","TimeDeposit"));
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 14)
    public void TDB_TD_014() throws Exception {
        try {
            timeDepositModule.verifyUserCanChangeNicknameOfTheTimeDeposit_TDB_TD_014(propertyFileReader("password","Login"));
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 15)
    public void TDB_TD_015() throws Exception {
        try {
            timeDepositModule.verifyUserCanChangeTheInvestmentAmountOfTheTimeDeposit_TDB_TD_015(propertyFileReader("password","Login"),"5" + RandomIntegerGenerator(3));
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 16)
    public void TDB_TD_016() throws Exception {
        try {
            timeDepositModule.verifyUserCanChangeTheTermOfTheTimeDeposit_TDB_TD_016(propertyFileReader("password","Login"),"5" + RandomIntegerGenerator(3),propertyFileReader("Month1","TimeDeposit"));
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 17)
    public void TDB_TD_017() throws Exception {
        try {
            timeDepositModule.verifyUserCanRollOverONTheTimeDeposit_TDB_TD_017(propertyFileReader("password","Login"));
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 19)
    public void TDB_TD_019() throws Exception {
        try {
            timeDepositModule.verifyUserCanRollOverOFFTheTimeDeposit_TDB_TD_019(propertyFileReader("password","Login"));
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 21)
    public void TDB_TD_021() throws Exception {
        try {
            timeDepositModule.verifyUserCanOpenMoreThan5TimeDeposits_TDB_TD_021(propertyFileReader("password","Login"));
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 22)
    public void TDB_TD_022() throws Exception {
        try {
            timeDepositModule.verifyUserCanWithdrawTimeDepositEarly_TDB_TD_022(propertyFileReader("password","Login"),"5" + RandomIntegerGenerator(3),propertyFileReader("Month2","TimeDeposit"),propertyFileReader("Interest2","TimeDeposit"));
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 24)
    public void TDB_TD_024() throws Exception {
        try {
            timeDepositModule.verifyBKYCUserCanOpenTheTimeDeposit_TDB_TD_024(propertyFileReader("password","Login"));
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
}
