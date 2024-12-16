package com.tonik.bizfunctions;

import com.tonik.utility.Utilities;
import static com.tonik.utility.ExtentReporter.*;
import static com.tonik.utility.Utilities.*;
public class SevenElevenWithVASModule extends BaseClass{
    public SevenElevenWithVASModule() {
        super();
    }
    String platform = Utilities.getPlatform();
    LoanCommonMethods loan = new LoanCommonMethods();
    SevenElevenWithoutVASModule sevenElevenWithoutVASModule = new SevenElevenWithoutVASModule();
    /**
     * Method to Verify the existing user can access the Seven Eleven Loan from the main Dashboard
     * @param password
     * @throws Exception
     */
    public void verifyUserCanAccessSevenElevenLoanFromMainDashBoard_TDB_SEV_001(String password,String mobileNumber) throws Exception {
        HeaderChildNode("TDB_SEV_001, Seven Eleven With VAS - Verify the existing user can access the Seven Eleven Loan from the main Dashboard");
        sevenElevenWithoutVASModule.insertSevenElevenOffer("63"+mobileNumber,loan.getUserId("63"+mobileNumber), loan.getCustomerId("63"+mobileNumber));
        loan.insertedOfferPopupValidation(password,"Seven Eleven With VAS","TDB_SEV_001");
    }
    /**
     * Method to Verify the existing user can access the Seven Eleven Loan from the main Dashboard with no Pop up / or by clicking out of the pop up
     * @param password
     * @throws Exception
     */
    public void verifyUserCanAccessSevenElevenLoanFromMainDashBoardWithNoPopup_TDB_SEV_002(String password) throws Exception {
        HeaderChildNode("TDB_SEV_002, Seven Eleven With VAS - Verify the existing user can access the Seven Eleven Loan from the main Dashboard with no Pop up / or by clicking out of the pop up");
        loan.updatePopupTriggered(loan.getUserId("63" + propertyFileReader("SevenElevenMobileNumber", "TestDataNumbers")));
        loan.offerTileValidation(password,"Seven Eleven With VAS","TDB_SEV_002");
    }
    /**
     * Verify if user can apply the loan with VAS from the Loan Calculator screen
     * @throws Exception
     */
    public void  userCanApplyLoanWithVASFromLoanCalculatorScreen_TDB_SEV_007(String tenure) throws Exception {
        HeaderChildNode("TDB_SEV_007, Seven Eleven With VAS - Verify if user can apply the loan with VAS from the Loan Calculator screen");
        loan.applyLoan("WithVAS",tenure,"Seven Eleven With VAS","TDB_SEV_007");
    }
    /**
     * Verify if user can accept the loan offer with VAS from the Loan Summary screen
     * @throws Exception
     */
    public void userCanAcceptLoanOfferWithVASFromTheLoanSummaryScreen_TDB_SEV_008(String tenure) throws Exception {
        HeaderChildNode("TDB_SEV_008, Seven Eleven With VAS - Verify if user can accept the loan offer with VAS from the Loan Summary screen");
        loan.acceptLoanOffer("WithVAS","TDB_SEV_008",tenure);
    }
    /**
     * Verify if user can reapply Seven Eleven loan after the full loan repayment
     * @throws Exception
     */
    public void verifyUserCanReapplySevenElevenAfterFullLoanRepayment_TDB_SEV_114(String password,String mobileNumber) throws Exception {
        HeaderChildNode("TDB_SEV_114, Verify if user can reapply Seven Eleven with VAS after the full loan repayment");
        sevenElevenWithoutVASModule.insertSevenElevenOffer("63"+mobileNumber,loan.getUserId("63"+mobileNumber), loan.getCustomerId("63"+mobileNumber));
        loan.reapplyAfterFullRepayment(password,"Seven Eleven with VAS","TDB_SEV_114");
    }
    /**
     * Method to Verify if SKYC user can apply Seven Eleven Loan if TSA is created already
     * @param mobileNo
     * @throws Exception
     */
    public void verifySKYCUserCanApplySevenElevenWithVASLoanTSACreatedAlready_TDB_SEV_120(String mobileNo,String tenure) throws Exception {
        HeaderChildNode("TDB_SEV_120, Seven Eleven With VAS - Verify if SKYC user can apply Seven Eleven if TSA is created already");
        loan.updateOfferStatus(loan.getUserId("63"+propertyFileReader("SevenElevenMobileNumber","TestDataNumbers")));
        loan.updatePopupTriggered(loan.getUserId("63"+propertyFileReader("SevenElevenMobileNumber","TestDataNumbers")));
        loan.endToEndJourney(mobileNo,"WithVAS","Seven Eleven With VAS","TDB_SEV_120",tenure);
    }
    /**
     * Verify the Loan tile status if the user is soft rejected
     * @throws Exception
     */
    public void loanSoftRejectionValidation_TDB_SEV_020(String password,String mobileNumber) throws Exception {
        HeaderChildNode("TDB_SEV_020, Seven Eleven With VAS - Verify the Loan tile status if the user is soft rejected");
        sevenElevenWithoutVASModule.insertSevenElevenOffer("63"+mobileNumber,loan.getUserId("63"+mobileNumber), loan.getCustomerId("63"+mobileNumber));
        loan.updateOfferStatus(loan.getUserId("63"+mobileNumber));
        loan.updatePopupTriggered(loan.getUserId("63"+mobileNumber));
        loan.softRejectValidation(password,"Seven Eleven With VAS","TDB_SEV_020",mobileNumber);
    }
    /**
     * Verify the Loan tile status if the user is hard rejected
     * @throws Exception
     */
    public void loanHardRejectionValidation_TDB_SEV_021(String password,String mobileNumber) throws Exception {
        HeaderChildNode("TDB_SEV_021, Seven Eleven With VAS - Verify the Loan tile status if the user is hard rejected");
        sevenElevenWithoutVASModule.insertSevenElevenOffer("63"+mobileNumber,loan.getUserId("63"+mobileNumber), loan.getCustomerId("63"+mobileNumber));
        loan.updateOfferStatus(loan.getUserId("63"+mobileNumber));
        loan.updatePopupTriggered(loan.getUserId("63"+mobileNumber));
        loan.hardRejectValidation(password,"Seven Eleven With VAS","TDB_SEV_021");
    }
    /**
     * Method to Verify if user can quit the loan application
     * @throws Exception
     */
    public void userCanQuitLoanApplicationValidation_TDB_SEV_121(String mobileNumber) throws Exception {
        HeaderChildNode("TDB_SEV_121, Seven Eleven With VAS - Verify if user can quit the loan application");
        loan.updateOfferStatus(loan.getUserId("63"+mobileNumber));
        loan.quitLoanApplication("Seven Eleven With VAS","TDB_SEV_121",mobileNumber);
    }
    /**
     * Verify if BKYC user can apply Seven Eleven Loan if TSA is not created (SKYC upgrade failed)
     * @throws Exception
     */
    public void bkycUserCanApplySevenelevenLoanIfTSAIsNotCreatedValidation_TDB_SEV_118(String password,String mobileNumber) throws Exception {
        HeaderChildNode("TDB_SEV_118, Seven Eleven With VAS - Verify if BKYC user can apply Seven Eleven Loan if TSA is not created (SKYC upgrade failed)");
        sevenElevenWithoutVASModule.insertSevenElevenOffer("63"+mobileNumber,loan.getUserId("63"+mobileNumber), loan.getCustomerId("63"+mobileNumber));
        loan.updateOfferStatus(loan.getUserId("63"+mobileNumber));
        loan.updatePopupTriggered(loan.getUserId("63"+mobileNumber));
        loan.applyLoanForBKYCUser(password,mobileNumber,"Seven Eleven With VAS","TDB_SEV_118");
    }
    /**
     * Verify the Loans tile if the user didn't accept the Loan offer in 30 days
     * @param mobileNo
     * @throws Exception
     */
    public void verifyLoansTileIfUserNotAcceptLoanOfferIn30Days_TDB_SEV_128(String password,String mobileNo) throws Exception {
        HeaderChildNode("TDB_SEV_128, Seven Eleven Loan With VAS - Verify the Loans tile if the user didn't accept the Loan offer in 30 days ");
        sevenElevenWithoutVASModule.insertSevenElevenOffer("63" + mobileNo, loan.getUserId("63" + mobileNo), loan.getCustomerId("63" + mobileNo));
        loan.updateOfferStatus(loan.getUserId("63" + mobileNo));
        loan.updatePopupTriggered(loan.getUserId("63" + mobileNo));
        loan.userNotAcceptOfferIn30days(password,mobileNo,"Seven Eleven With VAS","TDB_SEV_128");
    }
}