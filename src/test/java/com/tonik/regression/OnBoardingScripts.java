package com.tonik.regression;
import org.testng.annotations.Test;
import static com.tonik.utility.Utilities.softAssert;
public class OnBoardingScripts extends BaseTest{
    @Test(priority = 1)
    public void TDB_OB_001() throws Exception {
        try {
            onboardingModule.openTonikApp_TBD_OB_001();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 2)
    public void TDB_OB_002() throws Exception {
        try {
            onboardingModule.createTonikAccountUsingInvalidMobileNumber_TBD_OB_002();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 3)
    public void TDB_OB_003() throws Exception {
        try {
            onboardingModule.createTonikAccountUsingValidMobileNumber_TBD_OB_003();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 4)
    public void TDB_OB_004() throws Exception {
        try {
            onboardingModule.termsAndConditionDataPrivacyStatementValidation_TBD_OB_004();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 5)
    public void TBD_OB_005() throws Exception {
        try {
            onboardingModule.invalidOTPValidation_TBD_OB_005();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 6)
    public void TBD_OB_006() throws Exception {
        try {
            onboardingModule.resendOTPLinkValidation_TBD_OB_006();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 7)
    public void TBD_OB_007() throws Exception {
        try {
            onboardingModule.resendOTPMaxAttemptValidation_TBD_OB_007();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 8)
    public void TBD_OB_020() throws Exception {
        try {
            onboardingModule.proceedingToNextPageAfterSegmentation1Validation_TBD_OB_020();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 9)
    public void TBD_OB_022_TBD_OB_027() throws Exception {
        try {
            onboardingModule.autoPopulatedPassportDetailsValidation_TBD_OB_022_TBD_OB_027();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 10)
    public void TBD_OB_023() throws Exception {
        try {
            onboardingModule.inputOrEditInputGenderFieldValidation_TBD_OB_023();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 11)
    public void TBD_OB_024() throws Exception {
        try {
            onboardingModule.inputOrEditInputDateOfBirthFieldValidation_TBD_OB_024();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 12)
    public void TBD_OB_025() throws Exception {
        try {
            onboardingModule.placeOfBirthInputFieldValidation_TBD_OB_025();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 13)
    public void TBD_OB_026() throws Exception {
        try {
            onboardingModule.placeOfBirthAlphaNumericInputFieldValidation_TBD_OB_026();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 14)
    public void TBD_OB_028() throws Exception {
        try {
            onboardingModule.howToFindYourZipCodeHyperlinkValidation_TBD_OB_028();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 15)
    public void TBD_OB_029() throws Exception {
        try {
            onboardingModule.invalidZipCodeDetailsValidation_TBD_OB_029();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 16)
    public void TBD_OB_030() throws Exception {
        try {
            onboardingModule.validZipCodeDetailsValidation_TBD_OB_030_TBD_OB_031();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 17)
    public void TBD_OB_032() throws Exception {
        try {
            onboardingModule.barangaySelectionScreenValidation_TBD_OB_032();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 18)
    public void TBD_OB_033() throws Exception {
        try {
            onboardingModule.autoPopulatedHouseOrStreetFieldValidation_TBD_OB_033();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 19)
    public void TBD_OB_034() throws Exception {
        try {
            onboardingModule.inputOrEditInputDateOfBirthFieldValidation_TBD_OB_034();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 20)
    public void TBD_OB_035() throws Exception {
        try {
            onboardingModule.selectSourceOfFundsValidation_BD_OB_035();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 21)
    public void TBD_OB_036() throws Exception {
        try {
            onboardingModule.selectCurrentEmploymentStatusValidation_BD_OB_036();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 22)
    public void TBD_OB_037() throws Exception {
        try {
            onboardingModule.selectNatureOfWorkValidation_BD_OB_037();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 23)
    public void TBD_OB_038() throws Exception {
        try {
            onboardingModule.selectIndustryValidation_BD_OB_038();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 24)
    public void TBD_OB_039() throws Exception {
        try {
            onboardingModule.FACTAPageUIValidation_TBD_OB_039();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 25)
    public void TBD_OB_041() throws Exception {
        try {
            onboardingModule.inputExistingEmailValidation_TBD_OB_041();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 26)
    public void TBD_OB_043_TBD_OB_044_TBD_OB_045() throws Exception {
        try {
            onboardingModule.inputInvalidEmailValidation_TBD_OB_043_TBD_OB_044_TBD_OB_045();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 27)
    public void TBD_OB_046() throws Exception {
        try {
            onboardingModule.validMotherMaidenNameValidation_TBD_OB_046();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 28)
    public void TBD_OB_047() throws Exception {
        try {
            onboardingModule.invalidMotherMaidenNameValidation_TBD_OB_047_BD_OB_048();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 29)
    public void TBD_OB_049() throws Exception {
        try {
            onboardingModule.selectBankingRelationshipValidation_BD_OB_049();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 30)
    public void TBD_OB_042() throws Exception {
        try {
            onboardingModule.proceedingToNextPageAfterSegmentation2Validation_TBD_OB_042();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 31)
    public void TBD_OB_050() throws Exception {
        try {
            onboardingModule.signatureScreenValidation_BD_OB_050();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 32)
    public void TBD_OB_051() throws Exception {
        try {
            onboardingModule.hereTheDealScreenUIValidation_BD_OB_051();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 33)
    public void TBD_OB_052() throws Exception {
        try {
            onboardingModule.incorrectConfirmPasswordValueValidation_BD_OB_052();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 34)
    public void TBD_OB_053_BD_OB_054_BD_OB_056_BD_OB_055() throws Exception {
        try {
            onboardingModule.createValidPasswordValidation_BD_OB_053_BD_OB_054_BD_OB_056_BD_OB_055();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 35)
    public void TBD_OB_058() throws Exception {
        try {
            onboardingModule.onboardingNonExistingUserValidation_TBD_OB_058();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 36)
    public void TBD_OB_059() throws Exception {
        try {
            onboardingModule.inputExistingAccountMobileNumberValidation_TBD_OB_059();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 37)
    public void TBD_OB_064() throws Exception {
        try {
            onboardingModule.onboardingSKYCWithHighRiskProfile_TBD_OB_064();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 38)
    public void TBD_OB_065() throws Exception {
        try {
            onboardingModule.onboardingSKYCUserValidation_TBD_OB_065_BD_OB_057();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 39)
    public void TBD_OB_040() throws Exception {
        try {
            onboardingModule.usaCitizenFATCAValidation_BD_OB_040();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 40)
    public void TBD_OB_060() throws Exception {
        try {
            onboardingModule.onboardingBKYCUserValidation_TDB_OB_060();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
}