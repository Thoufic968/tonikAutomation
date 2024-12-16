package com.tonik.regression;

import org.testng.annotations.Test;

import static com.tonik.utility.Utilities.softAssert;

public class OnboardingScriptsIOS extends BaseTest{

    @Test(priority = 1)
    public void TDB_OB_001() throws Exception {
        try {
            onboardingModuleIOS.openTonikApp_TBD_OB_001();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 2)
    public void TDB_OB_002() throws Exception {
        try {
            onboardingModuleIOS.createTonikAccountUsingInvalidMobileNumber_TBD_OB_002();
        } catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 3)
    public void TDB_OB_003() throws Exception {
        try {
            onboardingModuleIOS.createTonikAccountUsingValidMobileNumber_TBD_OB_003();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 4)
    public void TDB_OB_004() throws Exception {
        try {
            onboardingModuleIOS.termsAndConditionDataPrivacyStatementValidation_TBD_OB_004();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 5)
    public void TDB_OB_005() throws Exception {
        try {
            onboardingModuleIOS.invalidOTPValidation_TBD_OB_005();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 6)
    public void TDB_OB_006() throws Exception {
        try {
            onboardingModuleIOS.resendOTPLinkValidation_TBD_OB_006();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 7)
    public void TDB_OB_007() throws Exception {
        try {
            onboardingModuleIOS.resendOTPMaxAttemptValidation_TBD_OB_007();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 8)
    public void TDB_OB_020() throws Exception {
        try {
            onboardingModuleIOS.proceedingToNextPageAfterSegmentation1Validation_TBD_OB_020();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 9)
    public void TBD_OB_022_TBD_OB_027() throws Exception {
        try {
            onboardingModuleIOS.autoPopulatedPassportDetailsValidation_TBD_OB_022_TBD_OB_027();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 10)
    public void TBD_OB_023() throws Exception {
        try {
            onboardingModuleIOS.inputOrEditInputGenderFieldValidation_TBD_OB_023();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 11)
    public void TBD_OB_024() throws Exception {
        try {
            onboardingModuleIOS.inputOrEditInputDateOfBirthFieldValidation_TBD_OB_024();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 12)
    public void TBD_OB_025() throws Exception {
        try {
            onboardingModuleIOS.placeOfBirthInputFieldValidation_TBD_OB_025();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 13)
    public void TBD_OB_026() throws Exception {
        try {
            onboardingModuleIOS.placeOfBirthAlphaNumericInputFieldValidation_TBD_OB_026();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 14)
    public void TBD_OB_028() throws Exception {
        try {
            onboardingModuleIOS.howToFindYourZipCodeHyperlinkValidation_TBD_OB_028();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 15)
    public void TBD_OB_029() throws Exception {
        try {
            onboardingModuleIOS.invalidZipCodeDetailsValidation_TBD_OB_029();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 16)
    public void TBD_OB_030_TBD_OB_031() throws Exception {
        try {
            onboardingModuleIOS.validZipCodeDetailsValidation_TBD_OB_030_TBD_OB_031();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 17)
    public void TBD_OB_032() throws Exception {
        try {
            onboardingModuleIOS.barangaySelectionScreenValidation_TBD_OB_032();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 18)
    public void TBD_OB_033() throws Exception {
        try {
            onboardingModuleIOS.autoPopulatedHouseOrStreetFieldValidation_TBD_OB_033();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 19)
    public void TBD_OB_034() throws Exception {
        try {
            onboardingModuleIOS.inputOrEditInputDateOfBirthFieldValidation_TBD_OB_034();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 20)
    public void TBD_OB_035() throws Exception {
        try {
            onboardingModuleIOS.selectSourceOfFundsValidation_BD_OB_035();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 21)
    public void TBD_OB_036() throws Exception {
        try {
            onboardingModuleIOS.selectCurrentEmploymentStatusValidation_BD_OB_036();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 22)
    public void TBD_OB_037() throws Exception {
        try {
            onboardingModuleIOS.selectNatureOfWorkValidation_BD_OB_037();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 23)
    public void TBD_OB_038() throws Exception {
        try {
            onboardingModuleIOS.selectIndustryValidation_BD_OB_038();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 24)
    public void TBD_OB_039() throws Exception {
        try {
            onboardingModuleIOS.FACTAPageUIValidation_TBD_OB_039();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 25)
    public void TBD_OB_041() throws Exception {
        try {
            onboardingModuleIOS.inputExistingEmailValidation_TBD_OB_041();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 26)
    public void TBD_OB_043_TBD_OB_044_TBD_OB_045() throws Exception {
        try {
            onboardingModuleIOS.inputInvalidEmailValidation_TBD_OB_043_TBD_OB_044_TBD_OB_045();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 27)
    public void TBD_OB_046() throws Exception {
        try {
            onboardingModuleIOS.validMotherMaidenNameValidation_TBD_OB_046();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 28)
    public void TBD_OB_047() throws Exception {
        try {
            onboardingModuleIOS.invalidMotherMaidenNameValidation_TBD_OB_047_BD_OB_048();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 29)
    public void TBD_OB_049() throws Exception {
        try {
            onboardingModuleIOS.selectBankingRelationshipValidation_BD_OB_049();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 30)
    public void TBD_OB_042() throws Exception {
        try {
            onboardingModuleIOS.proceedingToNextPageAfterSegmentation2Validation_TBD_OB_042();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 31)
    public void TBD_OB_050() throws Exception {
        try {
            onboardingModuleIOS.signatureScreenValidation_BD_OB_050();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 32)
    public void TBD_OB_051() throws Exception {
        try {
            onboardingModuleIOS.hereTheDealScreenUIValidation_TBD_OB_051();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 33)
    public void TBD_OB_052() throws Exception {
        try {
            onboardingModuleIOS.incorrectConfirmPasswordValueValidation_BD_OB_052();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 34)
    public void BD_OB_053_BD_OB_054_BD_OB_056_BD_OB_055() throws Exception {
        try {
            onboardingModuleIOS.createValidPasswordValidation_BD_OB_053_BD_OB_054_BD_OB_056_BD_OB_055();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 35)
    public void TBD_OB_058() throws Exception {
        try {
            onboardingModuleIOS.onboardingNonExistingUserValidation_TBD_OB_058();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 36)
    public void TBD_OB_059() throws Exception {
        try {
            onboardingModuleIOS.inputExistingAccountMobileNumberValidation_TBD_OB_059();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 37)
    public void TBD_OB_064() throws Exception {
        try {
            onboardingModuleIOS.onboardingSKYCWithHighRiskProfile_TBD_OB_064();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 38)
    public void TBD_OB_065() throws Exception {
        try {
            onboardingModuleIOS.onboardingSKYCUserValidation_TBD_OB_065_BD_OB_057();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 39)
    public void BD_OB_040() throws Exception {
        try {
            onboardingModuleIOS.usaCitizenFATCAValidation_BD_OB_040();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 39)
    public void TBD_OB_060() throws Exception {
        try {
            onboardingModuleIOS.onboardingBKYCUserValidation_TDB_OB_060();
        } catch (AssertionError | Exception e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
}

