package com.tonik.regression;

import org.testng.annotations.Test;
import static com.tonik.utility.Utilities.*;

public class StashScript extends BaseTest {

    @Test(priority = 1)
    public void TBD_ST_001() throws Exception {
        try {
            stashModule.verifyIfUserCanAccessTheStashModuleWhenTSAIsNotYetCreated_TDB_ST_001();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 2)
    public void TBD_ST_002() throws Exception {
        try {
            stashModule.verifyIfUserCanCreateTheTSAFromStash_TDB_ST_002();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 3)
    public void TBD_ST_003() throws Exception {
        try {
            stashModule.verifyIfUserCanCreateSoloStash_TDB_ST_003();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 4)
    public void TBD_ST_004() throws Exception {
        try {
            stashModule.verifyIfUserCanSetInitialSavingWhileCreatingTheStash_TDB_ST_004();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 5)
    public void TBD_ST_005() throws Exception {
        try {
            stashModule.verifyIfUserCanAddFundsToSoloStashFromTheListDetailsScreen_TDB_ST_005();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 6)
    public void TBD_ST_006() throws Exception {
        try {
            stashModule.verifyIfUserCanManageTheSoloStashFromTheListDetailsScreen_TDB_ST_006();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 7)
    public void TBD_ST_007() throws Exception {
        try {
            stashModule.verifyIfUserCanAddFundsToSoloStashFromTheSummaryDetailsScreen_TDB_ST_007();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 8)
    public void TBD_ST_008() throws Exception {
        try {
            stashModule.verifyIfTSABalanceIsDebitedTwiceIfConfirmButtonIsClickedTwiceWhileAddingFundInSoloStash_TDB_ST_008();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 9)
    public void TBD_ST_009() throws Exception {
        try {
            stashModule.verifyIfUserCanAddMoneyAboveTheTargetAmount_TDB_ST_009();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 10)
    public void TBD_ST_010() throws Exception {
        try {
            stashModule.verifyIfUserCanManageTheSoloStashFromTheSummaryDetailsScreen_TDB_ST_010();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 11)
    public void TBD_ST_011() throws Exception {
        try {
            stashModule.verifyIfUserCanViewTheSoloStashDetails_TDB_ST_011();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 12)
    public void TBD_ST_012() throws Exception {
        try {
            stashModule.verifyIfUserCanChangeTheNameModifyThePictureOfTheSoloStash_TDB_ST_012();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 13)
    public void TBD_ST_013() throws Exception {
        try {
            stashModule.verifyTheCharactersLimitForSoloStashName_TDB_ST_013();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }


    @Test(priority = 14)
    public void TBD_ST_014() throws Exception {
        try {
            stashModule.verifyIfUserCanModifyTheTargetAmountOfTheSoloStash_TDB_ST_014();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 15)
    public void TBD_ST_015() throws Exception {
        try {
            stashModule.verifyIfUserCanModifyTheTargetAmountInSoloStashValidation_TDB_ST_015();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }


    @Test(priority = 16)
    public void TBD_ST_016() throws Exception {
        try {
            stashModule.verifyIfUserCanWithdrawTheAmountFromSoloStash_TDB_ST_016();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 17)
    public void TBD_ST_017() throws Exception {
        try {
            stashModule.verifyIfTSABalanceIsCreditedTwiceIfConfirmButtonClickedTwice_TDB_ST_017();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 18)
    public void TBD_ST_018() throws Exception {
        try {
            stashModule.verifyIfUserCanWithdrawAmountFromSoloStashInsufficientBalance_TDB_ST_018();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 19)
    public void TBD_ST_019() throws Exception {
        try {
            stashModule.verifyIfUserCanCloseTheSoloStash_TDB_ST_019();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 20)
    public void TBD_ST_020() throws Exception {
        try {
            stashModule.verifyUserCanAchieveGoalForSoloStash_TDB_ST_020();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 21)
    public void TBD_ST_021() throws Exception {
        try {
            stashModule.verifyUserCanStillAddTheMoneyAfterTheGoalIsAchieved_TDB_ST_021();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 22)
    public void TBD_ST_022() throws Exception {
        try {
            stashModule.verifyUserCanSetHigherGoalForAchievedGoalSoloStash_TDB_ST_022();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 23)
    public void TBD_ST_023() throws Exception {
        try {
            stashModule.verifyUserCanConvertStashToTimeDepositForSoloStash_TDB_ST_023();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 24)
    public void TBD_ST_024() throws Exception {
        try {
            stashModule.verifyUserCanCreateEmergencySoloStash_TDB_ST024(Integer.parseInt(5+RandomIntegerGenerator(3)),0,"Emergency Stash","Solo Stash");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority =25)
    public void TBD_ST_025() throws Exception {
        try {
            stashModule.verifyUserCanCreateVacationSoloStash_TDB_ST025(Integer.parseInt(5+RandomIntegerGenerator(3)),0,"Vacation Stash","Solo Stash");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 26)
    public void TBD_ST_026() throws Exception {
        try {
            stashModule.verifyUserCanCreateTuitionSoloStash_TDB_ST026(Integer.parseInt(5+RandomIntegerGenerator(3)),0,"Tuition Stash","Solo Stash");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 27)
    public void TBD_ST_027() throws Exception {
        try {
            stashModule.verifyUserCanCreateNewRideSoloStash_TDB_ST027(Integer.parseInt(5+RandomIntegerGenerator(3)),0,"Luv Stash","Solo Stash");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 28)
    public void TBD_ST_028() throws Exception {
        try {
            stashModule.verifyUserCanCreateGameConsoleSoloStash_TDB_ST028(Integer.parseInt(5+RandomIntegerGenerator(3)),0,"Game Console","Solo Stash");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 29)
    public void TBD_ST_029() throws Exception {
        try {
            stashModule.verifyUserCanCreateMoreThan5SoloStashes_TDB_ST029(Integer.parseInt(5+RandomIntegerGenerator(3)),0,"Luv Stash","Solo Stash");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 30)
    public void TBD_ST_030() throws Exception {
        try {
            stashModule.verifyUserCanCreateGroupStash();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 31)
    public void TBD_ST_031() throws Exception {
        try {
            stashModule.verifyUserCanAddFundsToGroupStashFromDetailsScreen();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 32)
    public void TBD_ST_032() throws Exception {
        try {
            stashModule.verifyUserCanManageGroupStashFromListDetailsScreen();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 33)
    public void TBD_ST_033() throws Exception {
        try {
            stashModule.verifyUserCanAddFundsToGroupStashFromSummaryDetails();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 34)
    public void TBD_ST_034() throws Exception {
        try {
            stashModule.verifyTSABalanceDebitedTwiceIfConfirmButtonClickedTwiceWhileAddingFundInGroupStash();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 35)
    public void TBD_ST_035() throws Exception {
        try {
            stashModule.verifyUserCanManageGroupStashFromSummaryDetailsScreen();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 36)
    public void TBD_ST_036() throws Exception {
        try {
            stashModule.verifyUserCanViewGroupStashDetails();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 37)
    public void TBD_ST_037() throws Exception {
        try {
            stashModule.verifyUserCanChangeNameAndModifyPictureOfGroupStash();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 38)
    public void TBD_ST_038() throws Exception {
        try {
            stashModule.verifyCharactersLimitForGroupStashName();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 39)
    public void TBD_ST_039_040() throws Exception {
        try {
            stashModule.verifyUserCanModifyTargetAmountOfGroupStash();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 41)
    public void TBD_ST_041() throws Exception {
        try {
            stashModule.verifyUserCanWithdrawAmountFromGroupStash();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 42)
    public void TBD_ST_042() throws Exception {
        try {
            stashModule.verifyTSABalanceCreditedTwiceIfConfirmButtonClickedTwiceWhileWithdrawingFundInGroupStash();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 43)
    public void TBD_ST_043() throws Exception {
        try {
            stashModule.verifyUserCanWithdrawMoreThanStashAmountFromGroupStash();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 44)
    public void TBD_ST_044() throws Exception {
        try {
            stashModule.verifyUserCanCloseGroupStash();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 45)
    public void TBD_ST_045() throws Exception {
        try {
            stashModule.verifyUserCanAchieveGoalForGroupStash();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 46)
    public void TBD_ST_046() throws Exception {
        try {
            stashModule.verifyUserCanConvertStashToTimeDepositForGroupStash();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 47)
    public void TBD_ST_047() throws Exception {
        try {
            stashModule.VerifyUserCanSetHigherGoalForAchievedGoalGroupStash();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 48)
    public void TBD_ST_048() throws Exception {
        try {
            stashModule.verifyUserCanCreateEmergencyGroupStash_TDB_ST048(Integer.parseInt(5+RandomIntegerGenerator(3)),0,"Emergency Stash","Group Stash");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 49)
    public void TBD_ST_049() throws Exception {
        try {
            stashModule.verifyUserCanCreateVacationGroupStash_TDB_ST049(Integer.parseInt(5+RandomIntegerGenerator(3)),0,"Vacation Stash","Group Stash");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 50)
    public void TBD_ST_050() throws Exception {
        try {
            stashModule.verifyUserCanCreateTuitionGroupStash_TDB_ST050(Integer.parseInt(5+RandomIntegerGenerator(3)),0,"Tuition Stash","Group Stash");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 51)
    public void TBD_ST_051() throws Exception {
        try {
            stashModule.verifyUserCanCreateNewRideGroupStash_TDB_ST051(Integer.parseInt(5+RandomIntegerGenerator(3)),0,"Luv Stash","Group Stash");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 52)
    public void TBD_ST_052() throws Exception {
        try {
            stashModule.verifyUserCanCreateGameConsoleGroupStash_TDB_ST052(Integer.parseInt(5+RandomIntegerGenerator(3)),0,"Game Console","Group Stash");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 53)
    public void TBD_ST_053() throws Exception {
        try {
            stashModule.verifyUserCanCreateMoreThan5GroupStashes_TDB_ST053(Integer.parseInt(5+RandomIntegerGenerator(3)),0,"Luv Stash","Group Stash");
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 54)
    public void TBD_ST_054() throws Exception {
        try {
            stashModule. verifyUserCanSendAnInviteToOtherTonikCustomer_TDB_ST_054();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 55)
    public void TBD_ST_055() throws Exception {
        try {
            stashModule.VerifyIfUserCanResendTheInvite_TDB_ST_055();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 56)
    public void TBD_ST_056() throws Exception {
        try {
            stashModule.verifyUserCanInviteMoreMembers_TDB_ST_056();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 57)
    public void TBD_ST_057() throws Exception {
        try {
            stashModule.verifyUserCanInviteTenMembersAtATimeFromContacts_TDB_ST_057();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 58)
    public void TBD_ST_058() throws Exception {
        try {
            stashModule.verifyUserCanInviteNonTonikCustomer_TDB_ST_058();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 59)
    public void TBD_ST_059() throws Exception {
        try {
            stashModule.verifyUserCanViewTheSaversList_TDB_ST_059();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 61)
    public void TBD_ST_061() throws Exception {
        try {
            stashModule.verifyUserCanViewTheContributionsAddedByMemberOfGroupStash_TDB_ST_061();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 62)
    public void TBD_ST_062() throws Exception {
        try {
            stashModule.verifyStatusOfInvitedMemberIfInvitationIsNotAccepted_TDB_ST_062();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 63)
    public void TBD_ST_063() throws Exception {
        try {
            stashModule.verifyUserCanRemoveTheMember_TDB_ST_063();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 64)
    public void TBD_ST_064() throws Exception {
        try {
            stashModule.verifyUserCanAcceptTheStashGroupInvitation_TDB_ST_064();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 65)
    public void TBD_ST_065() throws Exception {
        try {
            stashModule.verifyUserCanDeclineTheStashGroupInvitation_TDB_ST_065();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 66)
    public void TBD_ST_066() throws Exception {
        try {
            stashModule.verifyUserCanContributeTheAmountToStashGroupInvitation_TDB_ST_066();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 67)
    public void TBD_ST_067() throws Exception {
        try {
            stashModule.verifyUserCanLeaveFromTheGroupStash_TDB_ST_067();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 68)
    public void TBD_ST_068() throws Exception {
        try {
            stashModule.verifyIfBKYCUserCanCreateTheStashAndAchieveTheGoal_TDB_ST_068();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 69)
    public void TBD_ST_069() throws Exception {
        try {
            stashModule.verifyBKYCUserCanCreateSoloStashForMoreThan50K_TDB_ST_069();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 70)
    public void TBD_ST_070() throws Exception {
        try {
            stashModule.verifyBKYCUserCanCreateTheGroupStash_TDB_ST_070();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 71)
    public void TBD_ST_071() throws Exception {
        try {
            stashModule.verifyBKYCUserCanOnlyCreateMaximum2SoloStashes_TBD_PST_0071(propertyFileReader("TargetAmountForBKYC","stash"),propertyFileReader("InitialAmountForBKYC","stash"),propertyFileReader("StashNameForBKYC","stash"));
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
}
