package com.tonik.regression;
import com.tonik.pageObject.SILPurpleAppPage;
import org.testng.annotations.Test;
import static com.tonik.utility.Utilities.propertyFileReader;
import static com.tonik.utility.Utilities.softAssert;
public class SILPurpleAppScripts extends BaseTest{
    @Test(priority = 1)
    public void TDB_PW_001() throws Exception {
        try {
            silPurpleAppModule.verifyIfPromoterCanLoadPurpleAppLoginPageValidation_TDB_PW_001();
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 2)
    public void TDB_PW_002() throws Exception {
        try {
            silPurpleAppModule.validUserNameAndPasswordValidation_TDB_PW_002();
        }catch (AssertionError e) {
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }
//    @Test(priority = 3)
//    public void TDB_PW_004() throws Exception {
//        try {
//            silPurpleAppModule.invalidUserNameAndPasswordValidation_TDB_PW_004();
//        }catch (AssertionError e) {
//            throw e;
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 4)
//    public void TDB_PW_006() throws Exception {
//        try {
//            silPurpleAppModule.verifyMerchantCanSelectMerchantName_TDB_PW_006();
//        }catch (AssertionError e) {
//            throw e;
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 5)
//    public void TDB_PW_007() throws Exception {
//        try {
//            silPurpleAppModule.logoutFromPurpleWebValidation_TDB_PW_007();
//        }catch (AssertionError e) {
//            throw e;
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 6)
//    public void TDB_PW_008() throws Exception {
//        try {
//            silPurpleAppModule.addItemToCartValidation_TDB_PW_008();
//        }catch (AssertionError e) {
//            throw e;
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 7)
//    public void TDB_PW_009() throws Exception {
//        try {
//            silPurpleAppModule.addSecondItemToCartValidation_TDB_PW_009();
//        }catch (AssertionError e) {
//            throw e;
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 8)
//    public void TDB_PW_010() throws Exception {
//        try {
//            silPurpleAppModule.addThirdItemToCartValidation_TDB_PW_010();
//        }catch (AssertionError e) {
//            throw e;
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 9)
//    public void TDB_PW_011() throws Exception {
//        try {
//            silPurpleAppModule.loanSummaryDetailsValidationAfterInputtingPriceAmount_TDB_PW_011();
//        }catch (AssertionError e) {
//            throw e;
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 10)
//    public void TDB_PW_012() throws Exception {
//        try {
//            silPurpleAppModule.rateChartValidationUponInputtingPriceAmount_TDB_PW_012();
//        }catch (AssertionError e) {
//            throw e;
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 11)
//    public void TDB_PW_013() throws Exception {
//        try {
//            silPurpleAppModule.installmentTermsValidationUponInputtingPriceAmount_TDB_PW_013(propertyFileReader("Price","SILPurpleApp"));
//        }catch (AssertionError e) {
//            throw e;
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 12)
//    public void TDB_PW_014() throws Exception {
//        try {
//            silPurpleAppModule.downPaymentPercentageIfPromoterSelectsMobilePhones_TDB_PW_014(propertyFileReader("FourDownPaymentAmount","SILPurpleApp"),propertyFileReader("Category1","SILPurpleApp"),propertyFileReader("Category2","SILPurpleApp"));
//        }catch (AssertionError e) {
//            throw e;
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 13)
//    public void TDB_PW_015() throws Exception {
//        try {
//            silPurpleAppModule.downPaymentPercentageIfPromoterSelectsOtherThanMobilePhones_TDB_PW_015(propertyFileReader("FourDownPaymentAmount","SILPurpleApp"),propertyFileReader("Category4","SILPurpleApp"));
//        }catch (AssertionError e) {
//            throw e;
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 14)
//    public void TDB_PW_016() throws Exception {
//        try {
//            silPurpleAppModule.downPaymentPercentageIfPromoterSelectsBothMobilePhonesAndOtherItems_TDB_PW_016(propertyFileReader("FourDownPaymentAmount","SILPurpleApp"),propertyFileReader("FourDownPaymentAmount","SILPurpleApp"));
//        }catch (AssertionError e) {
//            throw e;
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 15)
//    public void TDB_PW_017() throws Exception {
//        try {
//            silPurpleAppModule.minimumAndMaximumLoanableAmountValidation_TDB_PW_017(propertyFileReader("LessPrice5K","SILPurpleApp"),propertyFileReader("MaxAmount","SILPurpleApp"));
//        }catch (AssertionError e) {
//            throw e;
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 16)
//    public void TDB_PW_018() throws Exception {
//        try {
//            silPurpleAppModule.verifyPromoterCanAbleToDecreaseDownPaymentOrNotValidation_TDB_PW_018(propertyFileReader("MaxAmount","SILPurpleApp"));
//        }catch (AssertionError e) {
//            throw e;
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 17)
//    public void TDB_PW_019() throws Exception {
//        try {
//            silPurpleAppModule.verifyPromoterCanAbleToSameDownPaymentOrNotValidation_TDB_PW_019(propertyFileReader("MaxAmount","SILPurpleApp"));
//        }catch (AssertionError e) {
//            throw e;
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 18)
//    public void TDB_PW_021() throws Exception {
//        try {
//            silPurpleAppModule.verifyPromoterCanCloseDownPaymentWindowValidation_TDB_PW_021(propertyFileReader("MaxAmount","SILPurpleApp"));
//        }catch (AssertionError e) {
//            throw e;
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 19)
//    public void TDB_PW_022() throws Exception {
//        try {
//            silPurpleAppModule.verifyPaanoGumaganaAngPayHingaAtBakitKailanganNiClientItoHyperLinkValidationValidation_TDB_PW_022(propertyFileReader("MaxAmount","SILPurpleApp"));
//        }catch (AssertionError e) {
//            throw e;
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 20)
//    public void TDB_PW_023() throws Exception {
//        try {
//            silPurpleAppModule.selectInstallmentTermsGenerateCodeBtnValidation_TDB_PW_023(propertyFileReader("Price","SILPurpleApp"));
//        }catch (AssertionError e) {
//            throw e;
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 21)
//    public void TDB_PW_024() throws Exception {
//        try {
//            silPurpleAppModule.verifyPromoterCanGenerateQRCode0Validation_TDB_PW_024(propertyFileReader("Price","SILPurpleApp"), SILPurpleAppPage.objAssessmentCode0);
//        }catch (AssertionError e) {
//            throw e;
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 22)
//    public void TDB_PW_025() throws Exception {
//        try {
//            silPurpleAppModule.verifyPromoterCanGenerateQRCode1Validation_TDB_PW_025(propertyFileReader("Price","SILPurpleApp"), SILPurpleAppPage.objAssessmentCode1);
//        }catch (AssertionError e) {
//            throw e;
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 23)
//    public void TDB_PW_026() throws Exception {
//        try {
//            silPurpleAppModule.verifyPromoterCanGenerateQRCode2Validation_TDB_PW_026(propertyFileReader("Price","SILPurpleApp"), SILPurpleAppPage.objAssessmentCode2);
//        }catch (AssertionError e) {
//            throw e;
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 24)
//    public void TDB_PW_028() throws Exception {
//        try {
//            silPurpleAppModule.verifyPromoterCanGenerateQRCodeWithoutPayHingaValidation_TDB_PW_028(propertyFileReader("Price","SILPurpleApp"), SILPurpleAppPage.objAssessmentCode0);
//        }catch (AssertionError e) {
//            throw e;
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 25)
//    public void TDB_PW_029() throws Exception {
//        try {
//            silPurpleAppModule.verifyPromoterCanGenerateQRCodeWithPayHingaValidation_TDB_PW_029(propertyFileReader("Price","SILPurpleApp"), SILPurpleAppPage.objAssessmentCode0);
//        }catch (AssertionError e) {
//            throw e;
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 26)
//    public void TDB_PW_030() throws Exception {
//        try {
//            silPurpleAppModule.verifyPromoterCanLogOutFromQRCodeScreenValidation_TDB_PW_030(propertyFileReader("Price","SILPurpleApp"), SILPurpleAppPage.objAssessmentCode0);
//        }catch (AssertionError e) {
//            throw e;
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 27)
//    public void TDB_PW_027() throws Exception {
//        try {
//            silPurpleAppModule.verifyPromoterCanLogOutFromQRCodeScreenValidation_TDB_PW_027(propertyFileReader("MaxAmount","SILPurpleApp"));
//        }catch (AssertionError e) {
//            throw e;
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 28)
//    public void TDB_PW_020() throws Exception {
//        try {
//            silPurpleAppModule.rateChartValidationUponInputtingValidPriceAmount_TDB_PW_020(propertyFileReader("MoreThan5K","SILPurpleApp"));
//        }catch (AssertionError e) {
//            throw e;
//        } finally {
//            softAssert.assertAll();
//        }
//    }
}