package com.tonik.bizfunctions;

import com.tonik.utility.*;
import java.sql.SQLException;
import static com.tonik.utility.DB_Utilites.insertQuery;
import static com.tonik.utility.ExtentReporter.HeaderChildNode;
import static com.tonik.utility.Utilities.*;
public class SevenElevenWithoutVASModule extends BaseClass{
    public SevenElevenWithoutVASModule() {
        super();
    }
    String platform = Utilities.getPlatform();
    LoanCommonMethods loan = new LoanCommonMethods();
    /**
     * Reusable method to insert Seven Eleven Offer
     * @param mobileNumber
     * @param userId
     * @param customerId
     * @throws SQLException
     */
    public void insertSevenElevenOffer(String mobileNumber, String userId, String customerId) throws SQLException {
        insertQuery("Insert into loans.tdbk_loan_offers_trx(user_id,cust_id,mobile_number,offer_start_date,offer_expired_date,offer_status,offer_type,offer_amount,offer_min_amount,offer_max_amount,min_emi_amount,max_emi_amount,min_dp_percent,status,loan_product_type,loan_sub_product_type,is_eligible,created_date,updated_by,interest_rate,customer_margin,tenures,file_uploaded_date,popup_triggered,max_tenure)\n" +
                "values('" + userId + "','" + customerId + "','" + mobileNumber + "','" + loan.extractedOneDayBackDate(1) + "','" + loan.extractUpcomingDate(5) + "','" + propertyFileReader("offer_status", "FlexPivot") + "','" + propertyFileReader("offer_type", "SevenEleven") + "','" + propertyFileReader("offer_amount", "FlexPivot") + "'," +
                "'" + propertyFileReader("offer_min_amount", "FlexPivot") + "','" + propertyFileReader("offer_max_amount", "FlexPivot") + "','" + propertyFileReader("min_emi_amount", "FlexPivot") + "','" + propertyFileReader("max_emi_amount", "FlexPivot") + "','" + propertyFileReader("min_dp_percent", "FlexPivot") + "'," +
                "'" + propertyFileReader("status", "FlexPivot") + "','" + propertyFileReader("loan_product_type", "FlexPivot") + "','" + propertyFileReader("loan_sub_product_type", "SevenEleven") + "','" + propertyFileReader("is_eligible", "FlexPivot") + "','" + loan.todayDate() + "','" + propertyFileReader("updated_by", "FlexPivot") + "','" + propertyFileReader("interest_rate", "FlexPivot") + "'," +
                "'" + propertyFileReader("customer_margin", "FlexPivot") + "','" + propertyFileReader("tenures", "FlexPivot") + "','" + loan.todayDate() + "','" + propertyFileReader("popup_triggered", "FlexPivot") + "'," + Integer.parseInt(propertyFileReader("max_tenure", "FlexPivot")) + ");\n");
    }
    /**
     * Method to Verify the existing user can access the Seven Eleven Loan from the main Dashboard
     * @param password
     * @throws Exception
     */
    public void verifyUserCanAccessSevenElevenWithoutVASLoanFromMainDashBoard_TDB_SE_001(String password,String mobileNumber) throws Exception {
        HeaderChildNode("TDB_SE_001, Seven Eleven Without VAS- Verify the existing user can access the Seven Eleven Loan from the main Dashboard");
        insertSevenElevenOffer("63"+mobileNumber,loan.getUserId("63"+mobileNumber), loan.getCustomerId("63"+mobileNumber));
        loan.insertedOfferPopupValidation(password,"Seven Eleven Without VAS","TDB_SE_001");
    }
    /**
     * Method to Verify the existing user can access the Seven Eleven Loan from the main Dashboard with no Pop up / or by clicking out of the pop up
     * @param password
     * @throws Exception
     */
    public void verifyUserCanAccessSevenElevenWithoutVASFromMainDashBoardWithNoPopup_TDB_SE_002(String password) throws Exception {
        HeaderChildNode("TDB_SE_002, Seven Eleven Without VAS - Verify the existing user can access the Seven Eleven Loan from the main Dashboard with no Pop up / or by clicking out of the pop up");
        loan.updatePopupTriggered(loan.getUserId("63" + propertyFileReader("SevenElevenMobileNumber", "TestDataNumbers")));
        loan.offerTileValidation(password,"Seven Eleven Without VAS","TDB_SE_002");
    }
    /**
     * Method to Verify if user can decline the Seven Eleven  Loan with VAS offer from the 'Are you sure?' pop up
     * @throws Exception
     */
    public void verifyUserCanDeclineSevenElevenWithVASOfferFromAreYouSurePopup_TDB_SE_005(String mobileNumber,String tenure) throws Exception {
        HeaderChildNode("TDB_SE_005, Seven Eleven Without VAS - Verify if user can decline the Seven Eleven  Loan with VAS offer from the 'Are you sure?' pop up");
        loan.declineWithVASOfferValidation(mobileNumber,"Seven Eleven","TDB_SE_005",tenure);
    }
    public void verifyUserCanReapplySevenElevenAfterFullLoanRepayment_TDB_SE_049(String password,String mobileNumber) throws Exception {
        HeaderChildNode("TDB_SE_049, Seven Eleven without VAS - Verify if user can reapply Seven Eleven after the full loan repayment");
        insertSevenElevenOffer("63"+mobileNumber,loan.getUserId("63"+mobileNumber), loan.getCustomerId("63"+mobileNumber));
        loan.reapplyAfterFullRepayment(password,"Seven Eleven without VAS","TDB_SE_049");
    }
    /**
     * Method to Verify if SKYC user can apply Quick Loan if TSA is created already
     * @param mobileNo
     * @throws Exception
     */
    public void verifySKYCUserCanApplySevenElevenWithoutVASTSACreatedAlready_TDB_SE_055(String mobileNo,String loanType,String tenure) throws Exception {
        HeaderChildNode("TDB_SE_055, Seven Eleven Without VAS - Verify if SKYC user can apply Quick Loan if TSA is created already");
        loan.updateOfferStatus(loan.getUserId("63"+mobileNo));
        loan.updatePopupTriggered(loan.getUserId("63"+mobileNo));
        loan.endToEndJourney(mobileNo,loanType,"Seven Eleven Without VAS","TDB_SE_055",tenure);
    }
    /**
     * Method to Verify if user can quit the loan application
     * @throws Exception
     */
    public void userCanQuitLoanApplicationValidation_TDB_SE_056(String mobileNumber) throws Exception {
        HeaderChildNode("TDB_SE_056, Seven Eleven Without VAS - Verify if user can quit the loan application");
        loan.updateOfferStatus(loan.getUserId("63"+propertyFileReader("SevenElevenMobileNumber","TestDataNumbers")));
        loan.quitLoanApplication("Seven Eleven Without VAS","TDB_SE_056",mobileNumber);
    }
}