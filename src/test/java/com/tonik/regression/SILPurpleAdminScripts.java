package com.tonik.regression;
import org.testng.annotations.Test;

import static com.tonik.utility.Utilities.propertyFileReader;
import static com.tonik.utility.Utilities.softAssert;

public class SILPurpleAdminScripts extends BaseTest {

    String PromoterUsername = propertyFileReader("PromoterUsername","SILAdminApp");
    String PromoterPassword = propertyFileReader("PromoterPassword","SILAdminApp");
    String SSSUserName = propertyFileReader("SSSUserName","SILAdminApp");
    String SSSPassword = propertyFileReader("SSSPassword","SILAdminApp");

    @Test(priority = 1)
    public void TDB_MA_012() throws Exception {
        try {
            silDuploModule.VerifyTheMerchantManagementEditOptionTDB_MA_12(PromoterUsername,PromoterPassword,SSSUserName,SSSPassword);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 2)
    public void TDB_MA_013() throws Exception {
        try {
            silDuploModule.ToVerifyTheEnableDisableOptionByClickingEditPencilButton_TDB_MA_013(PromoterUsername,PromoterPassword,SSSUserName,SSSPassword);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 3)
    public void TDB_MA_014() throws Exception {
        try {
            silDuploModule.verifyTheBulkUploadAndMerchantManagement_TDB_MA_014(PromoterUsername,PromoterPassword,SSSUserName,SSSPassword);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 4)
    public void TDB_MA_015() throws Exception {
        try {
            silDuploModule.verifyTheMerchantListExtractFile_TDB_MA_015(PromoterUsername,PromoterPassword,SSSUserName,SSSPassword);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 5)
    public void TDB_MA_016() throws Exception {
        try {
            silDuploModule.verifyMerchantManagementViewSearchPartner_TDB_MA_016(PromoterUsername, PromoterPassword, SSSUserName, SSSPassword);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
        @Test(priority = 6)
        public void TDB_MA_017() throws Exception {
            try {
                silDuploModule.verifyMerchantManagementViewSearchMerchant_TDB_MA_017(PromoterUsername,PromoterPassword,SSSUserName,SSSPassword);
            } catch (AssertionError e) {
                throw e;
            } finally {
                softAssert.assertAll();
            }
    }
    @Test(priority = 7)
    public void TDB_MA_018() throws Exception {
        try {
            silDuploModule.verifyPartnerManagementCreatePartner_TDB_MA_018(PromoterUsername,PromoterPassword,SSSUserName,SSSPassword);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 8)
    public void TDB_MA_019() throws Exception {
        try {
            silDuploModule.verifyPartnerManagementEditPartner_TDB_MA_019(PromoterUsername,PromoterPassword,SSSUserName,SSSPassword);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 9)
    public void TDB_MA_020() throws Exception {
        try {
            silDuploModule.verifyPartnersListBulkExtractReport_TDB_MA_020(PromoterUsername,PromoterPassword,SSSUserName,SSSPassword);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 10)
    public void TDB_MA_021() throws Exception {
        try {
            silDuploModule.verifyMerchantManagementCreateMerchant_TDB_MA_021(PromoterUsername,PromoterPassword,SSSUserName,SSSPassword);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 11)
    public void TDB_MA_022() throws Exception {
        try {
            silDuploModule.verifyEditPartnerEnableDisablePartner_TDB_MA_022(PromoterUsername,PromoterPassword,SSSUserName,SSSPassword);
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
}
