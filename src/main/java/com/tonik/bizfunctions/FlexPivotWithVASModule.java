package com.tonik.bizfunctions;

import static com.tonik.utility.ExtentReporter.HeaderChildNode;
public class FlexPivotWithVASModule extends BaseClass{
    LoanCommonMethods loan = new LoanCommonMethods();
    FlexPivotWithoutVASModule flexPivotWithoutVASModule = new FlexPivotWithoutVASModule();
    public FlexPivotWithVASModule(){
        super();
    }
    /**
     * Method to Verify the existing user can access the Flex Pivot Loan from the main Dashboard
     * @param password
     * @throws Exception
     */
    public void verifyUserCanAccessFlexPivotLoanFromMainDashBoard_TDB_FPV_001(String password,String mobileNumber) throws Exception {
        HeaderChildNode("TDB_FPV_001, Flex Pivot With VAS- Verify the existing user can access the Flex Pivot Loan from the main Dashboard");
        flexPivotWithoutVASModule.insertFlexPivotOffer("63"+mobileNumber,loan.getUserId("63"+mobileNumber), loan.getCustomerId("63"+mobileNumber));
        loan.updateOfferStatus(loan.getUserId("63"+mobileNumber));
        loan.updatePopupTriggered(loan.getUserId("63"+mobileNumber));
        loan.insertedOfferPopupValidation(password,"Flex Pivot With VAS","TDB_FPV_001");
    }
    /**
     * Method to Verify the existing user can access the Flex Pivot Loan from the main Dashboard with no Pop up / or by clicking out of the pop up
     * @param password
     * @throws Exception
     */
    public void verifyUserCanAccessFlexPivotLoanFromMainDashBoardWithNoPopup_TDB_FPV_002(String password,String mobileNumber) throws Exception {
        HeaderChildNode("TDB_FPV_002, Flex Pivot With VAS - Verify the existing user can access the Flex Pivot Loan from the main Dashboard with no Pop up / or by clicking out of the pop up");
        loan.updatePopupTriggered(loan.getUserId("63" + mobileNumber));
        loan.offerTileValidation(password,"Flex Pivot With VAS","TDB_FPV_002");
    }
    /**
     * Verify if user can apply the loan with VAS from the Loan Calculator screen
     * @throws Exception
     */
    public void  userCanApplyLoanWithVASFromLoanCalculatorScreen_TDB_FPV_007(String tenure) throws Exception {
        HeaderChildNode("TDB_FPV_007, Flex Pivot With VAS - Verify if user can apply the loan with VAS from the Loan Calculator screen");
        loan.applyLoan("WithVAS",tenure,"Flex Pivot With VAS","TD_FPV_007");
    }
    /**
     * Verify if user can accept the loan offer with VAS from the Loan Summary screen
     * @throws Exception
     */
    public void userCanAcceptLoanOfferWithVASFromTheLoanSummaryScreen_TDB_FPV_008(String tenure) throws Exception {
        HeaderChildNode("TDB_FPV_008, Verify if user can accept the loan offer with VAS from the Loan Summary screen");
        loan.acceptLoanOffer("WithVAS","TDB_FPV_008",tenure);
    }

    /**
     * Verify if BKYC user can apply Flex Pivot if TSA is not created (SKYC upgrade failed)
     * @throws Exception
     */
    public void bkycUserCanApplyFlexPivotIfTSAIsNotCreatedValidation_TDB_QLV_118(String password,String mobileNumber) throws Exception {
        HeaderChildNode("TDB_FPV_118, Flex Pivot With VAS - Verify if BKYC user can apply Flex Pivot if TSA is not created (SKYC upgrade failed)");
        flexPivotWithoutVASModule.insertFlexPivotOffer("63"+mobileNumber,loan.getUserId("63"+mobileNumber), loan.getCustomerId("63"+mobileNumber));
        loan.updateOfferStatus(loan.getUserId("63"+mobileNumber));
        loan.updatePopupTriggered(loan.getUserId("63"+mobileNumber));
        loan.applyLoanForBKYCUser(password,mobileNumber,"Flex Pivot With VAS","TDB_FPV_118");
    }
    /**
     * Verify the Loans tile if the user didn't accept the Loan offer in 30 days
     * @param mobileNo
     * @throws Exception
     */
    public void verifyLoansTileIfUserNotAcceptLoanOfferIn30Days_TDB_QLV_128(String password,String mobileNo) throws Exception {
        HeaderChildNode("TDB_FPV_128, Flex Pivot With VAS - Verify the Loans tile if the user didn't accept the Loan offer in 30 days ");
        flexPivotWithoutVASModule.insertFlexPivotOffer("63"+mobileNo,loan.getUserId("63"+mobileNo), loan.getCustomerId("63"+mobileNo));
        loan.updateOfferStatus(loan.getUserId("63"+mobileNo));
        loan.updatePopupTriggered(loan.getUserId("63"+mobileNo));
        loan.userNotAcceptOfferIn30days(password,mobileNo,"Flex Pivot With VAS","TDB_FPV_128");
    }
    /**
     * Verify the Loan tile status if the user is soft rejected
     * @throws Exception
     */
    public void loanSoftRejectionValidation_TDB_FPV_020(String password,String mobileNumber) throws Exception {
        HeaderChildNode("TDB_FPV_020, Flex Pivot With VAS - Verify the Loan tile status if the user is soft rejected");
        flexPivotWithoutVASModule.insertFlexPivotOffer("63"+mobileNumber,loan.getUserId("63"+mobileNumber), loan.getCustomerId("63"+mobileNumber));
        loan.updateOfferStatus(loan.getUserId("63"+mobileNumber));
        loan.updatePopupTriggered(loan.getUserId("63"+mobileNumber));
        loan.softRejectValidation(password,"Flex Pivot With VAS","TDB_FPV_020",mobileNumber);
    }
    /**
     * Verify the Loan tile status if the user is hard rejected
     * @throws Exception
     */
    public void loanHardRejectionValidation_TDB_FPV_021(String password,String mobileNumber) throws Exception {
        HeaderChildNode("TDB_FPV_021, Flex Pivot With VAS - Verify the Loan tile status if the user is hard rejected");
        flexPivotWithoutVASModule.insertFlexPivotOffer("63"+mobileNumber,loan.getUserId("63"+mobileNumber), loan.getCustomerId("63"+mobileNumber));
        loan.updateOfferStatus(loan.getUserId("63"+mobileNumber));
        loan.updatePopupTriggered(loan.getUserId("63"+mobileNumber));
        loan.hardRejectValidation(password,"Flex Pivot With VAS","TDB_FPV_021");
    }
    /**
     * Verify if user can reapply Flex Pivot loan after the full loan repayment
     * @throws Exception
     */
    public void verifyUserCanReapplyFlexPivotAfterFullLoanRepayment_TDB_FPV_114(String password,String mobileNumber) throws Exception {
        HeaderChildNode("TDB_FPV_114, Flex Pivot With VAS - Verify if user can reapply Flex Pivot after the full loan repayment");
        loan.updateOfferStatus(loan.getUserId("63"+mobileNumber));
        loan.updatePopupTriggered(loan.getUserId("63"+mobileNumber));
        loan.reapplyAfterFullRepayment(password,"Flex Pivot with VAS","TDB_FPV_114");
    }
    /**
     * Method to Verify if user can quit the loan application
     * @throws Exception
     */
    public void userCanQuitLoanApplicationValidation_TDB_FPV_121(String mobileNumber) throws Exception {
        HeaderChildNode("TDB_FPV_121, Flex Pivot With VAS - Verify if user can quit the loan application");
        loan.updateOfferStatus(loan.getUserId("63"+mobileNumber));
        loan.quitLoanApplication("Flex Pivot With VAS","TDB_FPV_121",mobileNumber);
    }
    /**
     * Method to Verify if SKYC user can apply Flex Pivot if TSA is created already
     * @param mobileNo
     * @throws Exception
     */
    public void verifySKYCUserCanApplyFlexPivotWithVASLoanTSACreatedAlready_TDB_FPV_120(String mobileNo,String tenure) throws Exception {
        HeaderChildNode("TDB_FPV_120, FLex Pivot With VAS - Verify if SKYC user can apply FLex Pivot With VAS if TSA is created already");
        loan.updateOfferStatus(loan.getUserId("63"+mobileNo));
        loan.updatePopupTriggered(loan.getUserId("63"+mobileNo));
        loan.endToEndJourney(mobileNo,"WithVAS","FLex Pivot With VAS","TDB_FPV_120",tenure);
    }
}
