package com.tonik.regression;

import static com.tonik.utility.Utilities.softAssert;
import org.testng.annotations.Test;
public class FriendsWithBenefitsScripts extends BaseTest {
	@Test(priority = 1)
    public void verifyUserCanAccessFriendWithBenefitsTileWithNoTSA_TDB_FB_001() throws Exception {
        try {
            friendsWithBenefitsModule.verifyUserCanAccessFriendWithBenefitsTileWithNoTSA_TDB_FB_001();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
	@Test(priority = 2)
	public void verifyUserCanAccessFriendWithBenefitsTileWithTSA_TDB_FB_002() throws Exception {
        try {
            friendsWithBenefitsModule.verifyUserCanAccessFriendWithBenefitsTileWithTSA_TDB_FB_002();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
	@Test(priority = 3)
	public void verifyUserCanCopyReferralLink_TDB_FB_003() throws Exception {
        try {
            friendsWithBenefitsModule.verifyUserCanCopyReferralLink_TDB_FB_003();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
}