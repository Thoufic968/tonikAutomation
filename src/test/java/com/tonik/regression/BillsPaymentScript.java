package com.tonik.regression;

import org.testng.annotations.Test;
import static com.tonik.utility.Utilities.softAssert;

public class BillsPaymentScript extends BaseTest{
    @Test(priority = 1)
    public void TDB_BP_001() throws Exception {
        try {
            billsPaymentModule.verifyUserCanAccessBillsPayModuleWhenTSANotCreated_TDB_BP_001();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 2)
    public void TDB_BP_002() throws Exception {
        try {
            billsPaymentModule.verifyUserCanCreateTSAFromBillsPay_TDB_BP_002();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 4)
    public void TDB_BP_004() throws Exception {
        try {
            billsPaymentModule.verifyUserCanVerifyTheEmailAddressFromBillsPay_TDB_BP_004();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
	
	@Test(priority = 5)
    public void TDB_BP_005() throws Exception {
        try {
        	billsPaymentModule.verifyUserCanViewListOfBillers_TDB_BP_005();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

	@Test(priority = 6)
    public void TDB_BP_006() throws Exception {
        try {
        	billsPaymentModule.verifyUserCanViewBillerCategoriesInTabs_TDB_BP_006();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
	
	@Test(priority = 7)
    public void TDB_BP_007() throws Exception {
        try {
        	billsPaymentModule.verifyUserCanSearchBiller_TDB_BP_007();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
	
	@Test(priority = 8)
    public void TDB_BP_008() throws Exception {
        try {
        	billsPaymentModule.verifyUserCanTypeAccountNumberSubscriberNumberInAccountInfoScreen_TDB_BP_008();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
	
	@Test(priority = 9)
    public void TDB_BP_009() throws Exception {
        try {
        	billsPaymentModule.verifyUserCanSetDueDateReminderForPayingBill_TDB_BP_009();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
	
	@Test(priority = 10)
    public void TDB_BP_010() throws Exception {
        try {
        	billsPaymentModule.verifyUserCanPayAsNewBiller_Meralco_TDB_BP_010();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
	
	@Test(priority = 11)
    public void TDB_BP_011() throws Exception {
        try {
        	billsPaymentModule.verifyUserCanPayAsNewBiller_ManilaWater_TDB_BP_011();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
	
	@Test(priority = 12)
    public void TDB_BP_012() throws Exception {
        try {
        	billsPaymentModule.verifyUserCanPayAsNewBiller_MayniladWater_TDB_BP_012();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
	
	@Test(priority = 13)
    public void TDB_BP_013() throws Exception {
        try {
        	billsPaymentModule.verifyUserCanPayAsNewBiller_EasytripServicesCorporation_TDB_BP_013();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
	
	@Test(priority = 14)
    public void TDB_BP_014() throws Exception {
        try {
        	billsPaymentModule.verifyUserCanPayAsNewBiller_BPI_TDB_BP_014();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
	
	@Test(priority = 15)
    public void TDB_BP_015() throws Exception {
        try {
        	billsPaymentModule.verifyUserCanPayAsNewBiller_MetrobankCard_TDB_BP_015();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
	
	@Test(priority = 16)
    public void TDB_BP_016() throws Exception {
        try {
        	billsPaymentModule.verifyUserCanPayAsNewBiller_RCBCBankard_TDB_BP_016();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
	
	@Test(priority = 17)
    public void TDB_BP_017() throws Exception {
        try {
        	billsPaymentModule.verifyUserCanPayAsNewBiller_VisayanElectric_TDB_BP_017();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
	
	@Test(priority = 18)
    public void TDB_BP_018() throws Exception {
        try {
        	billsPaymentModule.verifyUserCanPayAsNewBiller_Converge_TDB_BP_018();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
	
	@Test(priority = 19)
    public void TDB_BP_019() throws Exception {
        try {
        	billsPaymentModule.verifyUserCanPayAsNewBiller_PLDT_TDB_BP_019();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
	
	@Test(priority = 20)
    public void TDB_BP_020() throws Exception {
        try {
        	billsPaymentModule.verifyUserCanPayAsNewBiller_Smart_TDB_BP_020();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
	
	@Test(priority = 21)
    public void TDB_BP_021() throws Exception {
        try {
        	billsPaymentModule.verifyUserCanPayAsNewBiller_PagIBIG_TDB_BP_021();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 22)
    public void TDB_BP_022() throws Exception {
        try {
            billsPaymentModule.verifyUserCanPayBillFromGoToBillersListOption_TDB_BP_022("Easytrip Services Corporation","520005298010","Nick","500.00","15.00");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 23)
    public void TDB_BP_023() throws Exception {
        try {
            billsPaymentModule.verifyIfUserCanPayTheBillFromTheSavedBillerslist_TDB_BP_023("Easytrip Services Corporation","520005298010","Nick","500.00","15.00");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 24)
    public void TDB_BP_024() throws Exception {
        try {
            billsPaymentModule.verifyAccountHolderCanDeleteTheSavedBiller_TDB_BP_024();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 25)
    public void TDB_BP_025() throws Exception {
        try {
            billsPaymentModule.verifyErrorMessageForTheUnsuccessfulBillPayment_TDB_BP_025("Bank of the Philippine Islands (BPI)","Nick");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 26)
    public void TDB_BP_026() throws Exception {
        try {
            billsPaymentModule.verifyUserCanPayTheBillWithInsufficientTSABalance_TDB_BP_026("Bank of the Philippine Islands (BPI)","Nick");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 27)
    public void TDB_BP_027() throws Exception {
        try {
            billsPaymentModule.verifyBKYCUserCanMakeBillPayment_TDB_BP_027();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 30)
    public void TDB_BP_030() throws Exception {
        try {
            billsPaymentModule.verifyUserCanRequestForNewOTP_TDB_BP_030("Meralco","0110920126","Nick","500.00","0.00");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
}