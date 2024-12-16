package com.tonik.regression;

import org.testng.annotations.Test;
import static com.tonik.utility.Utilities.*;
public class TendoStashScripts extends BaseTest{
    @Test(priority = 1)
    public void TDB_TS_19() throws Exception {
        try {
            tendoStashModule.verifyTendoStashTileIsNotVisibleToRegularCustomer_TDB_TS_19();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 2)
    public void TDB_TS_04() throws Exception {
        try {
            tendoStashModule.verifySystemCanSaveTendoStashInDataBaseIfTSAisCreated_TDB_TS_04();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 3)
    public void TDB_TS_08() throws Exception {
        try {
            tendoStashModule.verifySystemCanRejectMobileNumberIsDuplicate_TDB_TS_08();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 4)
    public void TDB_TS_18() throws Exception {
        try {
            tendoStashModule.verifyIfTendoStashTileisVisibleToTendoEmployee_TDB_TS_18();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 5)
    public void TDB_TS_20() throws Exception {
        try {
            tendoStashModule.verifyIfUserWith0of5AvailableStashesCanOpenStash_TDB_TS_20(Integer.parseInt(5+RandomIntegerGenerator(3)),0,"Emergency Stash","Solo Stash");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 6)
    public void TDB_TS_21() throws Exception {
        try {
            tendoStashModule.verifyTendoStashVisibleWhenUserEligibleForTendoStash_TDB_TS_21();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 7)
    public void TDB_TS_47() throws Exception {
        try {
            tendoStashModule.verifyTendoStashTileIsClickable_TDB_TS_47();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 8)
    public void TDB_TS_50() throws Exception {
        try {
            tendoStashModule.verifyUserAbleToUploadStashPhotoWhileOpeningTendoStash_TDB_TS_50();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 9)
    public void TDB_TS_51() throws Exception {
        try {
            tendoStashModule.verifyUserAbleToSeeStashForTendoStash_TDB_TS_51();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 10)
    public void TDB_TS_52() throws Exception {
        try {
            tendoStashModule.verifyUserAbleToInputValidTargetAmount_TDB_TS_52();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 11)
    public void TDB_TS_53() throws Exception {
        try {
            tendoStashModule.verifyUserAbleToInputInvalidTargetAmount_TDB_TS_53();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 12)
    public void TDB_TS_54() throws Exception {
        try {
            tendoStashModule.verifyUserIsNavigatedToSetInitialSavingAfterClickingOnNextButton_TDB_TS_54();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 13)
    public void TDB_TS_55() throws Exception {
        try {
            tendoStashModule.verifyUserIsNavigatedToSetInitialSavingAfterClickingOnNextButton_TDB_TS_55();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 14)
    public void TDB_TS_56() throws Exception {
        try {
            tendoStashModule.verifyUserIsNotAbleToSettingInitialSaving_TDB_TS_56();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 15)
    public void TDB_TS_57() throws Exception {
        try {
            tendoStashModule.verifyUserIsAbleToSetAtLeast1PHP_TDB_TS_57();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 16)
    public void TDB_TS_58() throws Exception {
        try {
            tendoStashModule.verifyUserIsRedirectedReviewStashDetailsAfterSettingInitialSaving_TDB_TS_58();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 17)
    public void TDB_TS_59() throws Exception {
        try {
            tendoStashModule.reviewStashDetailsScreenValidation_TDB_TS_59();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 18)
    public void TDB_TS_60() throws Exception {
        try {
            tendoStashModule.verifyInterestRateForTendoStash_TDB_TS_60(propertyFileReader("TendoStashInterestRateValue","TendoStash"));
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 19)
    public void TDB_TS_44() throws Exception {
        try {
            tendoStashModule.verifyUserCanViewTendoStashHistory_TDB_TS_44();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 20)
    public void TDB_TS_41() throws Exception {
        try {
            tendoStashModule.verifyUserCanViewTendoStashDetails_TDB_TS_41();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 21)
    public void TDB_TS_42() throws Exception {
        try {
            tendoStashModule.verifyUserCanModifyTendoStashPhotoAndTargetAmount_TDB_TS_42();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 22)
    public void TDB_TS_43() throws Exception {
        try {
            tendoStashModule.verifyUserCannotModifyStashName_TDB_TS_43();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 23)
    public void TDB_TS_45A() throws Exception {
        try {
            tendoStashModule.verifyUserCanAddMoneyToTendoStash_TDB_TS_45A(propertyFileReader("ValidPeso", "Stash"));
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 24)
    public void TDB_TS_45B() throws Exception {
        try {
            tendoStashModule.verifyUserCanWithdrawMoneyToTendoStash_TDB_TS_45B(propertyFileReader("ValidPeso", "Stash"));
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
//=================================== Negative scenarios ===============================================================//
    @Test(priority = 25)
    public void TDB_TS_07() throws Exception {
        try {
            tendoStashModule.verifySystemCanRejectMobileNumberIfTSAIsNotActive_TDB_TS_07();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 26)
    public void TDB_TS_09() throws Exception {
        try {
            tendoStashModule.verifySystemCanRejectMobileNumberIfCustomerIsBKYC_TDB_TS_09();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
}