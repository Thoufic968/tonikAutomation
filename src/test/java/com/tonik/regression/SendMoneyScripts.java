package com.tonik.regression;

import com.tonik.pageObject.SendMoneyPage;
import com.tonik.utility.Utilities;
import org.testng.annotations.Test;
import static com.tonik.utility.ExtentReporter.platform;
import static com.tonik.utility.Utilities.*;

public class SendMoneyScripts extends BaseTest{
    String platform = Utilities.getPlatform();
    @Test(priority = 1)
    public void TDB_SM_001() throws Exception {
        try {
            sendMoneyModule.sendMoneyNewlyOnboardedUserWithNoTSA_TDB_SM_001(propertyFileReader("password", "SendMoney"));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 2)
    public void TDB_SM_002() throws Exception {
        try {
            sendMoneyModule.createTSAUsingSendMoney__TDB_SM_002(propertyFileReader("password","SendMoney"));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 3)
    public void TDB_SM_004() throws Exception {
        try {
            sendMoneyModule.sendMoneyEmailAddressValidation_TDB_SM_004(propertyFileReader("password","SendMoney"));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 4)
    public void TDB_SM_005() throws Exception {
        try {
            sendMoneyModule.sendMoneyToOtherBankViaPESONet_TDB_SM_005(propertyFileReader("password","SendMoney"),SendMoneyPage.getByOSType(Utilities.getPlatform(),SendMoneyPage.objPesonetOption),propertyFileReader("ValidAmount","SendMoney"));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 5)
    public void TDB_SM_006() throws Exception {
        try {
            sendMoneyModule.sendMoneyToOtherBankViaInstaPay_TDB_SM_006(propertyFileReader("password","SendMoney"),SendMoneyPage.getByOSType(Utilities.getPlatform(),SendMoneyPage.objInstaPayOption),propertyFileReader("ValidAmount","SendMoney"));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 6)
    public void TDB_SM_009() throws Exception {
        try {
            sendMoneyModule.sendMoneyToBankInstaPayInsufficientBalanceValidation_TDB_SM_009(propertyFileReader("password","SendMoney"), SendMoneyPage.getByOSType(Utilities.getPlatform(),SendMoneyPage.objInstaPayOption));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 7)
    public void TDB_SM_010() throws Exception {
        try {
            sendMoneyModule.sendMoneyToBankPESONetInsufficientBalanceValidation_TDB_SM_010(propertyFileReader("password","SendMoney"), SendMoneyPage.getByOSType(Utilities.getPlatform(),SendMoneyPage.objPesonetOption));
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 8)
    public void TDB_SM_011() throws Exception {
        try {
            sendMoneyModule.sendMoneyToTonikCustomerByEnteringRecipientMobileNumber_TDB_SM_011(propertyFileReader("password","SendMoney"), SendMoneyPage.getByOSType(Utilities.getPlatform(),SendMoneyPage.objToAnotherTonikCustomerOption),propertyFileReader("ValidAmount","SendMoney"));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 9)
    public void TDB_SM_012() throws Exception {
        try {
            sendMoneyModule.sendMoneyToTonikCustomerViaContacts_TDB_SM_012(propertyFileReader("password","SendMoney"), SendMoneyPage.getByOSType(Utilities.getPlatform(),SendMoneyPage.objToAnotherTonikCustomerOption),propertyFileReader("ValidAmount","SendMoney"));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 10)
    public void TDB_SM_013() throws Exception {
        try {
            sendMoneyModule.sendMoneyToTonikCustomerUsingRecipientTonikAccountNumber_TDB_SM_013(propertyFileReader("password","SendMoney"), SendMoneyPage.getByOSType(Utilities.getPlatform(),SendMoneyPage.objToAnotherTonikCustomerOption),propertyFileReader("ValidAmount","SendMoney"));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 11)
    public void TDB_SM_014() throws Exception {
        try {
            sendMoneyModule.sendMoneyAboveLimitedTransactionToTonikCustomer_TDB_SM_014(propertyFileReader("password","SendMoney"), SendMoneyPage.getByOSType(Utilities.getPlatform(),SendMoneyPage.objToAnotherTonikCustomerOption));
        }catch (AssertionError e) {
            throw e;
        }finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 12)
    public void TDB_SM_015() throws Exception {
        try {
            sendMoneyModule.sendMoneyTransactionToTonikCustomerViaMobileNumberInsufficientBalance_TDB_SM_015(propertyFileReader("password","SendMoney"), SendMoneyPage.getByOSType(Utilities.getPlatform(),SendMoneyPage.objToAnotherTonikCustomerOption));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 13)
    public void TDB_SM_016() throws Exception {
        try {
            sendMoneyModule.sendMoneyTransactionToTonikCustomerViaContactsInsufficientBalance_TDB_SM_016(propertyFileReader("password","SendMoney"), SendMoneyPage.getByOSType(Utilities.getPlatform(),SendMoneyPage.objToAnotherTonikCustomerOption));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 14)
    public void TDB_SM_017() throws Exception {
        try {
            sendMoneyModule.sendMoneyTransactionToTonikCustomerViaTonikAccountNumberInsufficientBalance_TDB_SM_017(propertyFileReader("password","SendMoney"), SendMoneyPage.getByOSType(Utilities.getPlatform(),SendMoneyPage.objToAnotherTonikCustomerOption));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 15)
    public void TDB_SM_018() throws Exception {
        try {
            sendMoneyModule.sendMoneyTransactionToOwnAccountUsingRegisteredMobileNumber_TDB_SM_018(propertyFileReader("password","SendMoney"), SendMoneyPage.getByOSType(Utilities.getPlatform(),SendMoneyPage.objToAnotherTonikCustomerOption),propertyFileReader("ValidAmount","SendMoney"));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 16)
    public void TDB_SM_019() throws Exception {
        try {
            sendMoneyModule.sendMoneyTransactionToOwnAccountUsingTonikAccountNumber_TDB_SM_019(propertyFileReader("password","SendMoney"), SendMoneyPage.getByOSType(Utilities.getPlatform(),SendMoneyPage.objToAnotherTonikCustomerOption),propertyFileReader("ValidAmount","SendMoney"));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 17)
    public void TDB_SM_021() throws Exception {
        try {
            sendMoneyModule.sendMoneyTransactionToUnregisteredTonikNumber_TDB_SM_021(propertyFileReader("password","SendMoney"), SendMoneyPage.getByOSType(Utilities.getPlatform(),SendMoneyPage.objToAnotherTonikCustomerOption),propertyFileReader("ValidAmount","SendMoney"));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 18)
    public void TDB_SM_020() throws Exception {
        try {
            sendMoneyModule.sendMoneyToInvalidAccountNumberViaInstaPayTDB_SM_020(propertyFileReader("password","SendMoney"),propertyFileReader("ValidAmount","SendMoney"));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 19)
    public void TDB_SM_022() throws Exception {
        try {
            sendMoneyModule.sendMoneyTransactionInvalidAccountNumber_TDB_SM_022(propertyFileReader("password","SendMoney"), SendMoneyPage.getByOSType(Utilities.getPlatform(),SendMoneyPage.objToAnotherTonikCustomerOption),propertyFileReader("ValidAmount","SendMoney"));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 20)
    public void TDB_SM_023() throws Exception {
        try {
            sendMoneyModule.sendMoneySaveARecipient_TDB_SM_023(propertyFileReader("password", "SendMoney"), propertyFileReader("ValidAmount","SendMoney"));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 21)
    public void TDB_SM_024() throws Exception {
        try {
            sendMoneyModule.sendMoneyFromQuickSendList_TDB_SM_024(propertyFileReader("password", "SendMoney"), propertyFileReader("ValidAmount","SendMoney"));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 22)
    public void TDB_SM_025() throws Exception {
        try {
            sendMoneyModule.sendMoneyDeleteRecipientFromQuickSendList_TDB_SM_025(propertyFileReader("password", "SendMoney"));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 23)
    public void TDB_SM_026() throws Exception {
        try {
            sendMoneyModule.addMessageWhileSendMoneyTransaction_TDB_SM_026(propertyFileReader("password", "SendMoney"),propertyFileReader("ValidAmount","SendMoney"),SendMoneyPage.getByOSType(Utilities.getPlatform(),SendMoneyPage.objToAnotherTonikCustomerOption));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 24)
    public void TDB_SM_007() throws Exception {
        try {
            sendMoneyModule.sendMoneyToBankInstaPayMaxTransactionValidation_TDB_SM_007(propertyFileReader("password", "SendMoney"),SendMoneyPage.getByOSType(Utilities.getPlatform(),SendMoneyPage.objInstaPayOption));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 25)
    public void TDB_SM_008() throws Exception {
        try {
            sendMoneyModule.sendMoneyToBankPESONetMaxTransactionValidation_TDB_SM_008(propertyFileReader("password", "SendMoney"),SendMoneyPage.getByOSType(Utilities.getPlatform(),SendMoneyPage.objPesonetOption));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 26)
    public void TDB_SM_027() throws Exception {
        try {
            sendMoneyModule.sendMoneyMoreThanMaxLimitFromBKYCUser_TDB_SM_027(propertyFileReader("BKYCUserPassword", "SendMoney"), SendMoneyPage.getByOSType(Utilities.getPlatform(),SendMoneyPage.objToAnotherTonikCustomerOption));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 27)
    public void TDB_SM_028() throws Exception {
        try {
            sendMoneyModule.sendMoneyToBKYCTonikCustomerByEnteringRecipientMobileNumber_TDB_SM_028(propertyFileReader("BKYCUserPassword", "SendMoney"),SendMoneyPage.getByOSType(Utilities.getPlatform(),SendMoneyPage.objToAnotherTonikCustomerOption),propertyFileReader("ValidAmount","SendMoney"));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 28)
    public void TDB_SM_030() throws Exception {
        try {
            sendMoneyModule.sendMoneyToOtherBankMaxOTPAttemptValidation_TDB_SM_030(propertyFileReader("BKYCUserPassword", "SendMoney"),SendMoneyPage.getByOSType(Utilities.getPlatform(),SendMoneyPage.objInstaPayOption),propertyFileReader("ValidAmount","SendMoney"));
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
}