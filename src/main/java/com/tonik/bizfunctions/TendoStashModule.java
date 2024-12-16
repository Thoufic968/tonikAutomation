package com.tonik.bizfunctions;

import com.driverInstance.DriverManager;
import com.jcraft.jsch.JSchException;
import com.tonik.pageObject.*;
import com.tonik.utility.Utilities;
import io.appium.java_client.ios.IOSElement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import static com.tonik.utility.DB_Utilites.insertQuery;
import static com.tonik.utility.DB_Utilites.selectQuery;
import static com.tonik.utility.ExtentReporter.HeaderChildNode;
import static com.tonik.utility.ExtentReporter.extentLoggerFail;
import static com.tonik.utility.Utilities.*;

public class TendoStashModule extends BaseClass{
    public TendoStashModule() {
        super();
    }
    StashModule stashModule = new StashModule();
    String platform = Utilities.getPlatform();
    SendMoneyModule sendMoneyModule = new SendMoneyModule();
    TopUpModule topUpModule = new TopUpModule();
    LoanCommonMethods loan = new LoanCommonMethods();
    /**
     * Reusable method to Insert Tendo Stash Query
     * @param mobileNumber
     * @throws Exception
     */
    public void insertTendoStash(String mobileNumber) throws Exception {
        insertQuery("Insert into accounts.tdbk_stash_offer_mtb(mobile_no,first_nm,last_nm,middle_nm,employment_id,email,employment_status,status,vendor_id,created_by,created_dt)\n" +
                "values('"+mobileNumber+"','"+getFirstName(loan.getUserId(mobileNumber))+"','"+getLastName(loan.getUserId(mobileNumber))+"'," + "'"+getMiddleName(loan.getUserId(mobileNumber))+"','"+loan.generateRandomTINNUMBER()+"'," +
                "'"+getEmailAddress(mobileNumber)+"','"+propertyFileReader("employment_status","TendoStash")+"','"+propertyFileReader("status","TendoStash")+"','"+propertyFileReader("vendor_id","TendoStash")+"'," +
                "'"+propertyFileReader("created_by","TendoStash")+"','"+loan.extractedOneDayBackDate(1)+"');\n");
    }
    /**
     * Reusable method to get First name
     * @param userId
     * @return
     * @throws JSchException
     * @throws SQLException
     */
    public String getFirstName(String userId) throws JSchException, SQLException {
        return selectQuery("customer","SELECT first_nm FROM customer.tdbk_cust_profile_mtb where user_id= '" + userId + "';");
    }
    /**
     * Reusable method to get Last name
     * @param userId
     * @return
     * @throws JSchException
     * @throws SQLException
     */
    public String getLastName(String userId) throws JSchException, SQLException {
        return selectQuery("customer","SELECT last_nm FROM customer.tdbk_cust_profile_mtb where user_id= '" + userId + "';");
    }
    /**
     * Reusable method to get Middle name
     * @param userId
     * @return
     * @throws JSchException
     * @throws SQLException
     */
    public String getMiddleName(String userId) throws JSchException, SQLException {
        return selectQuery("customer","SELECT middle_nm FROM customer.tdbk_cust_profile_mtb where user_id= '" + userId + "';");
    }
    /**
     * Common method to get Email Address
     * @param mobileNumber
     * @return
     * @throws Exception
     */
    public String getEmailAddress(String mobileNumber) throws Exception {
        return selectQuery("customer","SELECT email FROM customer.tdbk_customer_mtb where mobile_no='"+mobileNumber+"';");
    }
    /**
     * Reusable method to get Tendo Stash Details
     * @param mobileNumber
     * @param column
     * @return
     * @throws JSchException
     * @throws SQLException
     */
    public String getTendoStashDetails(String mobileNumber,String column) throws JSchException, SQLException {
        return selectQuery("customer","SELECT "+column+" FROM accounts.tdbk_stash_offer_mtb where mobile_no = '"+mobileNumber+"';");
    }
    /**
     * Reusable method for validating Tendo Stash tile
     * @throws Exception
     */
    public void validateTendoStashTile() throws Exception {
        waitForElementToBePresent(TendoStashPage.getByOSType(platform,TendoStashPage.objTendoStashTile),5,"Tendo Stash Tile");
        verifyElementPresent(TendoStashPage.getByOSType(platform,TendoStashPage.objTendoStashTile),getTextVal(TendoStashPage.getByOSType(platform,TendoStashPage.objTendoStashTile),"Tile"));
        assertionValidation(getText(TendoStashPage.getByOSType(platform,TendoStashPage.objTendoStashTile)), propertyFileReader("TendoStash","TendoStash"), "Tile");
        assertionValidation(getText(TendoStashPage.getByOSType(platform,TendoStashPage.objTendoStashTileInfo)), propertyFileReader("TendoStashInfo","TendoStash"), "Tile Info");
    }
    /**
     * Reusable method to Create Stash
     * @param targetAmount
     * @param initialAmount
     * @param stashName
     * @throws Exception
     */
    public void createStash(int targetAmount, int initialAmount, String stashName) throws Exception {
        clearField(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText), "Target Amount TextField");
        type(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText), String.valueOf(targetAmount), "text_field");
        click(StashPage.getByOSType(platform,StashPage.objNextButton), getText(StashPage.getByOSType(platform,StashPage.objNextButton)) + " : Button");
        if (initialAmount == 0) {
            click(StashPage.getByOSType(platform,StashPage.objSkipForNow), getText(StashPage.getByOSType(platform,StashPage.objSkipForNow)) + " :Button");
        } else {
            type(StashPage.getByOSType(platform,StashPage.objInitialSavingAmountInputTextField), String.valueOf(initialAmount), "Initial Amount Textfield");
            verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNextButton), getText(StashPage.getByOSType(platform,StashPage.objNextButton)) + " : Button");
        }
        stashModule.verifyReviewStashDetails(String.valueOf(targetAmount), String.valueOf(initialAmount), stashName);
        verticalSwipeTillElementDisplayed(StashPage.getByOSType(platform,StashPage.objCreateStashButtonEnabled));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objCreateStashButtonEnabled), getText(StashPage.getByOSType(platform,StashPage.objCreateStashButtonEnabled)) + " :Button");
    }
    /**
     * Method to verify manage Stash screen UI validation
     * @throws Exception
     */
    public void manageStashScreen() throws Exception {
        verifyElementPresentAndClick(TendoStashPage.getByOSType(platform,TendoStashPage.objManageTendoStash), "Manage on the image");
        waitTime(3000);
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objManageStash), "ManageStash"), "ManageStash", "ManageStash", "contains");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objStashDetails), "StashDetails"), "StashDetails", "StashDetails", "contains");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objModify), "Modify"), "Modify", "Modify", "contains");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objWithdrawToYourTonikAccount), "WithdrawToYourTonikAccount"), "WithdrawToYourTonikAccount", "WithdrawToYourTonikAccount", "contains");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objClose), "Close"), "Close", "Close", "contains");
    }
    /**
     * Reusable method for add to Stash screen for Tendo Stash validation
     * @throws Exception
     */
    public void addToStashScreenForTendoStash() throws Exception {
        verifyElementPresentAndClick(TendoStashPage.getByOSType(platform, TendoStashPage.ObjTendoStashAddToStash), "Add To stash on the image");
        waitTime(3000);
        valueValidation(getTextVal(StashPage.getByOSType(platform, StashPage.objAddToStashHeader), "AddToStashHeader"), "AddToStashHeader", "AddToStashHeader", "contains");
        verifyElementPresent(StashPage.getByOSType(platform, StashPage.objStashBalance), getTextVal(StashPage.getByOSType(platform, StashPage.objStashBalance), ":Stash Balance"));
        verifyElementPresent(StashPage.getByOSType(platform, StashPage.objStashPhpText), "Stash Php text");
        verifyElementPresent(StashPage.getByOSType(platform, StashPage.objNextButton), "Next Button");
    }
    /**
     * Reusable method to validate set Up Your Stash Screen
     * @throws Exception
     */
    public void setUpYourStashScreen() throws Exception {
        waitTime(3000);
        verifyElementPresent(StashPage.getByOSType(platform, StashPage.objSetUpYourStash), "Set up your Stash");
        containsValidation(getText(StashPage.getByOSType(platform, StashPage.objSetUpYourStash)), propertyFileReader("SetUpYourStashScreen", "Stash"), ":SetUpYourStashScreen");
        containsValidation(getText(StashPage.getByOSType(platform, StashPage.objStashFor)), propertyFileReader("StashFor", "Stash"), ":StashFor");
        assertionValidation(getText(StashPage.getByOSType(platform, StashPage.objStashForEditText)),propertyFileReader("TendoStash","TendoStash"),"Stash name");
        containsValidation(getText(StashPage.getByOSType(platform, StashPage.objTargetAmount)), propertyFileReader("TargetAmount", "Stash"), ":TargetAmount");
        verifyElementPresent(StashPage.getByOSType(platform, StashPage.objNextButton), "Next button");
    }
    /**
     * Reusable method to upload Stash Image
     * @throws Exception
     */
    public void uploadStashImage() throws Exception {
        waitTime(3000);
        if(verifyElementDisplayed(StashPage.getByOSType(platform, StashPage.objCameraIcon))) {
            click(StashPage.getByOSType(platform, StashPage.objCameraIcon), "Camera Icon");
        }else{
            click(TendoStashPage.getByOSType(platform, TendoStashPage.objCameraIcon), "Camera Icon");
        }
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objTakeAPhoto), "Take a Photo");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objShutterButton), "Shutter Button");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objDoneButton), "Done Button");
    }
    /**
     * Reusable method to set Initial Saving amount
     * @param initialSaving
     * @throws Exception
     */
    public void setInitialSavingAmount(String initialSaving) throws Exception {
        waitTime(3000);
        verifyElementPresent(StashPage.getByOSType(platform, StashPage.objSetInitialSaving), getTextVal(StashPage.getByOSType(platform, StashPage.objSetInitialSaving), "page title"));
        assertionValidation(getText(StashPage.getByOSType(platform, StashPage.objSetInitialSaving)), propertyFileReader("SetInitialSaving", "stash"), "page title");
        verifyElementPresent(StashPage.getByOSType(platform, StashPage.objBalanceOn), getTextVal(StashPage.getByOSType(platform, StashPage.objBalanceOn), "Balance in Set initial saving"));
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objInitialSavingAmountInputTextField), "initial saving input text field");
        type(StashPage.getByOSType(platform, StashPage.objInitialSavingAmountInputTextField), initialSaving, "initialSaving");
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objNextButton), "Next button");
    }
    /**
     * Method to verify review stash details screen
     * @throws Exception
     */
    public void reviewStashDetailsScreen(String exceptedInterest,String stashAmount) throws Exception {
        if (verifyElementPresent(StashPage.getByOSType(platform,StashPage.objReviewStashDetails), "Review Stash details")) {
            valueValidation(getText(StashPage.getByOSType(platform,StashPage.objReviewStashDetails)), propertyFileReader("ReviewStashDetails","stash"), "ReviewStashDetails", "contains");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashNameOnTheImage), "Stash Name On the Image");
            valueValidation(getText(StashPage.getByOSType(platform,StashPage.objTargetAmountHeader)), propertyFileReader("TargetAmount","stash"), "TargetAmount", "contains");
            String targetAmount=getText(StashPage.getByOSType(platform,StashPage.objTargetAmountOnReviewStash)).replaceAll("[,₱]", "");
            double amount = Double.parseDouble(targetAmount);
            assertionValidation(String.valueOf((int)amount),stashAmount,"Target amount");
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objInitialSavingHeader), "InitialSaving"), propertyFileReader("InitialSaving","stash"), "InitialSaving", "contains");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objInitialSavingOnReviewStash), getTextVal(StashPage.getByOSType(platform,StashPage.objInitialSavingOnReviewStash), ":Initial Saving on review Stash"));
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objTransferFromHeader), "TransferFrom"), propertyFileReader("TransferFrom","stash"), "TransferFrom", "contains");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTransferFromOnReviewStash), getTextVal(StashPage.getByOSType(platform,StashPage.objTransferFromOnReviewStash), ":Transfer From on review Stash"));
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objInterestRateHeader), "InterestRate"), propertyFileReader("InterestRate","stash"), "InterestRate", "contains");
            assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objInterestRateOnReviewStash)),exceptedInterest,"Interest value");
            swipe("UP",1);
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objStashPDICText), "StashPDICText"), propertyFileReader("StashPDICText","stash"), "StashPDICText", "contains");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTermsAndCondition), "Terms and Condition");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTermsAndConditionCheck), "Terms and Condition Check Box");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objCreateStashButtonDisabled), "Create Stash");
        }
    }
    /**
     * Method to Verify that the system can save the record to Tendo Stash offer database if Mobile number x TSA passed the criteria
     * @throws Exception
     */
    public void verifySystemCanSaveTendoStashInDataBaseIfTSAisCreated_TDB_TS_04() throws Exception {
        HeaderChildNode("TDB_TS_04, Tendo Stash - Verify that the system can save the record to Tendo Stash offer database if Mobile number x TSA passed the criteria");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresent(OnBoardingPage.objHistoryIcon,"History icon");
        assertionValidation(getAttributValue("enabled", OnBoardingPage.objHistoryIcon),"true",": History icon");
        insertTendoStash("63"+propertyFileReader("NonLoanMobileNumber","TestDataNumbers"));
        assertionValidation(getFirstName(loan.getUserId("63"+propertyFileReader("NonLoanMobileNumber","TestDataNumbers"))),getTendoStashDetails("63"+propertyFileReader("NonLoanMobileNumber","TestDataNumbers"),"first_nm"),"First name field");
        logger.info("TDB_TS_04, Tendo Stash - System can save the record to Tendo Stash offer database if Mobile number x TSA passed the criteria validated");
        extentLoggerPass("TDB_TS_04", "TDB_TS_04, Tendo Stash - System can save the record to Tendo Stash offer database if Mobile number x TSA passed the criteria validated");
    }
    /**
     * Method to Verify that the system can reject the mobile number if TSA is not active or has Debit/Credit restrictions
     * @throws Exception
     */
    public void verifySystemCanRejectMobileNumberIfTSAIsNotActive_TDB_TS_07() throws Exception {
        HeaderChildNode("TDB_TS_07, Tendo Stash - Verify that the system can reject the mobile number if TSA is not active or has Debit/Credit restrictions");
        insertTendoStash("63"+propertyFileReader("NonTSAMobileNumber","TestDataNumbers"));
        tonikLogin(propertyFileReader("NonTSAPassword", "Login"));
        stashModule.stashScreen();
        stashModule.newStashScreen();
        swipe("DOWN",1);
        if(verifyElementDisplayed(TendoStashPage.getByOSType(platform,TendoStashPage.objTendoStashTile))){
            logger.error("TDB_TS_07, Tendo Stash - System can't reject the mobile number if TSA is not active or has Debit/Credit restrictions validated");
            extentLoggerFail("TDB_TS_07","TDB_TS_07, Tendo Stash - System can't reject the mobile number if TSA is not active or has Debit/Credit restrictions validated");
        }else {
            logger.info("TDB_TS_07, Tendo Stash - System rejected the mobile number if TSA is not active or has Debit/Credit restrictions validated");
            extentLoggerPass("TDB_TS_07", "TDB_TS_07, Tendo Stash - System rejected the mobile number if TSA is not active or has Debit/Credit restrictions validated");
        }
    }
    /**
     * Method to Verify that the system can reject the mobile number if it is a duplicate entry
     * @throws Exception
     */
    public void verifySystemCanRejectMobileNumberIsDuplicate_TDB_TS_08() throws Exception {
        HeaderChildNode("TDB_TS_08, Tendo Stash - Verify that the system can reject the mobile number if it is a duplicate entry");
        insertTendoStash("63"+propertyFileReader("NonTSAMobileNumber","TestDataNumbers"));
        tonikLogin(propertyFileReader("password", "Login"));
        stashModule.stashScreen();
        stashModule.newStashScreen();
        swipe("DOWN", 1);
        validateTendoStashTile();
        List<IOSElement> values = DriverManager.getAppiumDriver().findElements(TendoStashPage.getByOSType(platform,TendoStashPage.objTendoStashTile));
        if(values.size()>1){
            logger.info("TDB_TS_08, System can't rejected the mobile number if TSA is not active or has Debit/Credit restrictions validated");
            extentLoggerFail("TDB_TS_08","System can't rejected the mobile number if TSA is not active or has Debit/Credit restrictions validated");
        }else{
            logger.info("TDB_TS_08, Tendo Stash - System rejected the mobile number if TSA is not active or has Debit/Credit restrictions validated");
            extentLoggerPass("TDB_TS_08", "TDB_TS_08, Tendo Stash - System rejected the mobile number if TSA is not active or has Debit/Credit restrictions validated");
        }
    }
    /**
     * Method to Verify that the system can reject the mobile number if it is a BKYC customer
     * @throws Exception
     */
    public void verifySystemCanRejectMobileNumberIfCustomerIsBKYC_TDB_TS_09() throws Exception {
        HeaderChildNode("TDB_TS_09, Tendo Stash - Verify that the system can reject the mobile number if it is a BKYC customer");
        insertTendoStash("63"+propertyFileReader("BKYCMobileNumber","TestDataNumbers"));
        tonikLogin(propertyFileReader("BKYCPassword", "Login"));
        stashModule.stashScreen();
        stashModule.newStashScreen();
        swipe("DOWN",1);
        if(verifyElementDisplayed(TendoStashPage.getByOSType(platform,TendoStashPage.objTendoStashTile))){
            logger.error("TDB_TS_09, Tendo Stash - System can't reject the mobile number if it is a BKYC customer validated");
            extentLoggerFail("TDB_TS_09","TDB_TS_09, Tendo Stash - System can't reject the mobile number if it is a BKYC customer validated");
        }else {
            logger.info("TDB_TS_09, Tendo Stash - System rejected the mobile number if it is a BKYC customer validated");
            extentLoggerPass("TDB_TS_09", "TDB_TS_09, Tendo Stash - System rejected the mobile number if it is a BKYC customer validated");
        }
    }
    /**
     * Method to Check if Tendo Stash tile is visible to Tendo employee
     * @throws Exception
     */
    public void verifyIfTendoStashTileisVisibleToTendoEmployee_TDB_TS_18() throws Exception {
        HeaderChildNode("TDB_TS_18, Tendo Stash - Check if Tendo Stash tile is visible to Tendo employee");
        tonikLogin(propertyFileReader("password", "Login"));
        stashModule.stashScreen();
        stashModule.newStashScreen();
        swipe("DOWN",1);
        validateTendoStashTile();
        screencapture();
        logger.info("TDB_TS_18, Tendo Stash - Tendo Stash tile is visible to Tendo employee validated");
        extentLoggerPass("TDB_TS_18", "TDB_TS_18, Tendo Stash - Tendo Stash tile is visible to Tendo employee validated");
    }
    /**
     * Method to Check if Tendo Stash tile is not visible to regular customers
     * @throws Exception
     */
    public void verifyTendoStashTileIsNotVisibleToRegularCustomer_TDB_TS_19() throws Exception {
        HeaderChildNode("TDB_TS_19, Tendo Stash - Check if Tendo Stash tile is not visible to regular customers");
        tonikLogin(propertyFileReader("password", "Login"));
        stashModule.stashScreen();
        stashModule.newStashScreen();
        swipe("DOWN",1);
        if(verifyElementDisplayed(TendoStashPage.getByOSType(platform,TendoStashPage.objTendoStashTile))){
            logger.error("TDB_TS_19, Tendo Stash - Tendo Stash tile is visible to regular customers validated");
            extentLoggerFail("TDB_TS_19","TDB_TS_19, Tendo Stash - Tendo Stash tile is visible to regular customers validated");
        }else {
            logger.info("TDB_TS_19, Tendo Stash - Tendo Stash tile is not visible to regular customers validated");
            extentLoggerPass("TDB_TS_19", "TDB_TS_19, Tendo Stash - Tendo Stash tile is not visible to regular customers validated");
        }
    }
    /**
     * Method to Check if user with 0 of 5 available stashes cannot open Tendo Stash
     * @param targetAmount
     * @param initialAmount
     * @param stashName
     * @param stashType
     * @throws Exception
     */
    public void verifyIfUserWith0of5AvailableStashesCanOpenStash_TDB_TS_20(int targetAmount,int initialAmount, String stashName,String stashType) throws Exception {
        HeaderChildNode("TDB_TS_20, Tendo Stash - Check if user with 0 of 5 available stashes cannot open Tendo Stash");
        tonikLogin(propertyFileReader("password", "Login"));
        stashModule.stashScreen();
        waitTime(4000);
        while(!getText(StashPage.getByOSType(platform,StashPage.obj5Of5AvailableStashes)).equals("0 of 5 available stashes")){
            verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStartANewStash),getText(StashPage.getByOSType(platform,StashPage.objStartANewStash))+" : Button");
            waitTime(3000);
            verifyElementPresentAndClick(StashPage.objAllStashOption(getPlatform(),stashName),getText(StashPage.objAllStashOption(getPlatform(),stashName))+" Stash Option");
            stashModule.verifySelectStashPopupAndSelectTypeOfStash(stashType);;
            createStash(targetAmount,initialAmount,stashName);
            stashModule.verifyGroupSoloStashCreatedSuccessMessage();
            waitTime(5000);
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStartANewStash),getText(StashPage.getByOSType(platform,StashPage.objStartANewStash))+" : Button");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objPopupHeader)),"Sorry, you can only have 5 actived Stashes created by yourself at the same time. You can close a Stash and create a new one."," Popup Header");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objPopupOkButton),getText(StashPage.getByOSType(platform,StashPage.objPopupOkButton))+" :Button");
        logger.info("TDB_TS_20, Tendo Stash - User with 0 of 5 available stashes cannot open Tendo Stash validated");
        extentLoggerPass("TDB_TS_20", "TDB_TS_20, Tendo Stash - User with 0 of 5 available stashes cannot open Tendo Stash validated");
    }
    /**
     * Method to Check if Tendo Stash tile is visible when the user is eligible for Tendo Stash
     * @throws Exception
     */
    public void verifyTendoStashVisibleWhenUserEligibleForTendoStash_TDB_TS_21() throws Exception {
        HeaderChildNode("TDB_TS_21, Tendo Stash - Check if Tendo Stash tile is visible when the user is eligible for Tendo Stash");
        tonikLogin(propertyFileReader("password", "Login"));
        swipe("UP", 2);
        if (verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader))) {
            click(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader), "Stash card");
        } else {
            click(StashPage.getByOSType(platform,StashPage.objStashCard), "Stash card");
        }
        waitTime(3000);
        if(!Objects.equals(getTendoStashDetails("63" + propertyFileReader("NonLoanMobileNumber","TestDataNumbers"), "first_nm"), "NULL")){
            while(getText(StashPage.getByOSType(platform,StashPage.obj5Of5AvailableStashes)).equals("0 of 5 available stashes")) {
                stashModule.manageStashScreen();
                click(StashPage.getByOSType(platform,StashPage.objClose), "Close");
                click(StashPage.getByOSType(platform,StashPage.objYesCloseTheStash), "Close");
                click(StashPage.getByOSType(platform,StashPage.objDoneButton), "Close");
            }
            stashModule.newStashScreen();
            swipe("DOWN",1);
            validateTendoStashTile();
            screencapture();
        }else {
            logger.error("TDB_TS_21, Tendo Stash - Tendo Stash tile is not visible when the user is non eligible for Tendo Stash validated");
            extentLoggerPass("TDB_TS_21", "TDB_TS_21, Tendo Stash - Tendo Stash tile is not visible when the user is non eligible for Tendo Stash validated");
        }
        logger.info("TDB_TS_21, Tendo Stash - Tendo Stash tile is visible when the user is eligible for Tendo Stash validated");
        extentLoggerPass("TDB_TS_21", "TDB_TS_21, Tendo Stash - Tendo Stash tile is visible when the user is eligible for Tendo Stash validated");
    }
    /**
     * Method to Verify that user can view Tendo Stash details
     * @throws Exception
     */
    public void verifyUserCanViewTendoStashDetails_TDB_TS_41() throws Exception {
        HeaderChildNode("TDB_TS_41, Tendo Stash - Verify that user can view Tendo Stash details");
        tonikLogin(propertyFileReader("password", "Login"));
        stashModule.stashScreen();
        swipe("UP",2);
        manageStashScreen();
        click(StashPage.getByOSType(platform,StashPage.objStashDetails), "Stash Details");
        stashModule.stashDetailsScreen();
        logger.info("TDB_TS_41, Tendo Stash - Tendo Stash tile is visible when the user is eligible for Tendo Stash validated");
        extentLoggerPass("TDB_TS_41", "TDB_TS_41, Tendo Stash - Tendo Stash tile is visible when the user is eligible for Tendo Stash validated");
    }
    /**
     * Method to Verify that user can modify Tendo Stash photo and target amount
     * @throws Exception
     */
    public void verifyUserCanModifyTendoStashPhotoAndTargetAmount_TDB_TS_42() throws Exception {
        HeaderChildNode("TDB_TS_42, Tendo Stash - Verify that user can modify Tendo Stash photo and target amount");
        tonikLogin(propertyFileReader("password", "Login"));
        stashModule.stashScreen();
        swipe("UP",2);
        manageStashScreen();
        click(StashPage.getByOSType(platform,StashPage.objModify), "Modify");
        uploadStashImage();
        waitTime(3000);
        String targetAmount = getText(StashPage.getByOSType(platform, StashPage.objTargetAmountEditText));
        click(StashPage.getByOSType(platform, StashPage.objTargetAmountEditText),"Target amount");
        clearField(StashPage.getByOSType(platform, StashPage.objTargetAmountEditText),"Target amount");
        type(StashPage.getByOSType(platform, StashPage.objTargetAmountEditText), propertyFileReader("ModifiedTargetAmount", "TendoStash"), "Target amount");
        String modifiedTargetAmount = getText(StashPage.getByOSType(platform, StashPage.objTargetAmountEditText));
        if(!Objects.equals(modifiedTargetAmount, targetAmount)){
            logger.info("User can able to modify Target amount");
            extentLoggerPass("TDB_TS_42", "User can able to modify Target amount");
        }else{
            logger.error("User can't able to modify Target amount");
            extentLoggerFail("TDB_TS_42", "User can't able to modify Target amount");
        }
        logger.info("TDB_TS_42, Tendo Stash - User can modify Tendo Stash photo and target amount validated");
        extentLoggerPass("TDB_TS_42", "TDB_TS_42, Tendo Stash - User can modify Tendo Stash photo and target amount validated");
    }
    /**
     * Method to Verify that user cannot modify Stash name
     * @throws Exception
     */
    public void verifyUserCannotModifyStashName_TDB_TS_43() throws Exception {
        HeaderChildNode("TDB_TS_43, Tendo Stash - Verify that user cannot modify Stash name");
        tonikLogin(propertyFileReader("password", "Login"));
        stashModule.stashScreen();
        swipe("UP",2);
        manageStashScreen();
        click(StashPage.getByOSType(platform,StashPage.objModify), "Modify");
        String stashName = getText(StashPage.getByOSType(platform, StashPage.objNameInputTxt));
        clearField(StashPage.getByOSType(platform, StashPage.objNameInputTextField),"Stash name edit field");
        String modifyStashName = getText(StashPage.getByOSType(platform, StashPage.objNameInputTxt));
        if(stashName.equals(modifyStashName)){
            logger.info("TDB_TS_43, Tendo Stash - User can't able modify Stash name validated");
            extentLoggerPass("TDB_TS_43", "TDB_TS_43, Tendo Stash - User can't able modify Stash name validated");
        }else{
            logger.error("TDB_TS_43, Tendo Stash - User can able modify Stash name validated");
            extentLoggerFail("TDB_TS_43", "User can able to modify Target amount");
        }
    }
    /**
     * Method to Verify that user can view Tendo Stash history
     * @throws Exception
     */
    public void verifyUserCanViewTendoStashHistory_TDB_TS_44() throws Exception {
        HeaderChildNode("TDB_TS_44, Tendo Stash - Verify that user can view Tendo Stash history");
        tonikLogin(propertyFileReader("password", "Login"));
        stashModule.stashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objStartANewStash), "Start a New Stash");
        validateTendoStashTile();
        verifyElementPresentAndClick(TendoStashPage.getByOSType(platform, TendoStashPage.objTendoStashTile), getTextVal(TendoStashPage.getByOSType(platform, TendoStashPage.objTendoStashTile), "Tile"));
        setUpYourStashScreen();
        type(StashPage.getByOSType(platform, StashPage.objTargetAmountEditText), propertyFileReader("TargetAmount", "TendoStash"), "text_field");
        click(StashPage.getByOSType(platform, StashPage.objNextButton), "Next button");
        setInitialSavingAmount(propertyFileReader("initialSaving", "Stash"));
        reviewStashDetailsScreen(propertyFileReader("TendoStashInterestRateValue","TendoStash"),propertyFileReader("TargetAmount","TendoStash"));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTermsAndConditionCheck), "Terms and condition Check box");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objCreateStashButtonEnabled), "Create Stash");
        if (verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objDoItLater))) {
            click(StashPage.getByOSType(platform,StashPage.objDoItLater), "Do It Later");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objDoneButton), "Done Button");
        waitTime(3000);
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objBackArrowButton),"Back Button");
        sendMoneyModule.navigateToAccountHistory();
        verifyElementPresent(TendoStashPage.getByOSType(platform,TendoStashPage.objTendoStashTile),getTextVal(TendoStashPage.getByOSType(platform,TendoStashPage.objTendoStashTile),"Transaction"));
        assertionValidation(getText(TendoStashPage.getByOSType(platform,TendoStashPage.objTendoStashTile)),propertyFileReader("TendoStash","TendoStash"),"Transaction");
        containsValidation(getText(TendoStashPage.getByOSType(platform,TendoStashPage.objTendoStashAmount)).trim(),propertyFileReader("initialSaving", "Stash"),"Transaction amount");
        click(TendoStashPage.getByOSType(platform,TendoStashPage.objTendoStashTile),getTextVal(TendoStashPage.getByOSType(platform,TendoStashPage.objTendoStashTile),"Transaction"));
        stashModule.historyTransactionDetailsScreen();
        logger.info("TDB_TS_44, Tendo Stash - User can view Tendo Stash history validated");
        extentLoggerPass("TDB_TS_44", "TDB_TS_44, Tendo Stash - User can view Tendo Stash history validated");
    }
    /**
     * Method to Verify that user can add money to Tendo Stash
     * @param amount
     * @throws Exception
     */
    public void verifyUserCanAddMoneyToTendoStash_TDB_TS_45A(String amount) throws Exception {
        HeaderChildNode("TDB_TS_45, Tendo Stash - Verify that user can add money to Tendo Stash");
        tonikLogin(propertyFileReader("password", "Login"));
        double sAvailableBeforeAfterAddingStash = topUpModule.getAvailableBalanceInteger(OnBoardingPage.objAvailableBalance);
        extentLoggerPass("TDB_TS_45", "Available Balance : "+sAvailableBeforeAfterAddingStash);
        stashModule.stashScreen();
        swipe("UP",2);
        addToStashScreenForTendoStash();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), "Add to stash Edit field");
        type(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), amount, "Amount Input Field");
        if(platform.equalsIgnoreCase("Android")){
            hideKeyboard();
        }else{
            tapOnScreen(300,150,"screen");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNextButton), "Next button");
        stashModule.confirmTransferToStashScreen(amount);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objConfirmButtonOnConfirmTransferToStash), "Confirm button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objYouAdded), 10, "Money Stashed");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objMoneyStashed), "MoneyStashed"), "MoneyStashed", "MoneyStashed", "contains");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objYouAdded), "You Added");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objViewDetailsOfHowWeMovedIt), "View Details How We Moved It");
        stashModule.transactionDetailsScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objQuestionMarkIconOnTransactionDetails), "contact customer Icon ");
        verifyElementPresent(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader), "Get In Touch Screen");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objQuestionMarkIconOnTransactionDetails), 10, "contact customer Icon");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objDoneButton), "Done Button");
        waitTime(3000);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objBackArrowButton), "Back Button");
        waitForElementToBePresent(OnBoardingPage.objAvailableBalance,5,"Dashboard");
        double sAvailableBalanceAfterAddingStash = topUpModule.getAvailableBalanceInteger(OnBoardingPage.objAvailableBalance);
        if(sAvailableBalanceAfterAddingStash==sAvailableBeforeAfterAddingStash-Double.parseDouble(amount)) {
            logger.info("TDB_TS_45A, Tendo Stash - User can add money to Tendo Stash validated");
            extentLoggerPass("TDB_TS_45A", "TDB_TS_45, Tendo Stash - User can add money to Tendo Stash validated");
        }else{
            logger.error("TDB_TS_45A, Tendo Stash - User can't add money to Tendo Stash validated");
            extentLoggerFail("TDB_TS_45A", "TDB_TS_45A, Tendo Stash - User can't add money to Tendo Stash validated");
        }
    }
    /**
     * Method to Verify that user can withdraw money to Tendo Stash
     * @param amount
     * @throws Exception
     */
    public void verifyUserCanWithdrawMoneyToTendoStash_TDB_TS_45B(String amount) throws Exception {
        HeaderChildNode("TDB_TS_45, Tendo Stash - Verify that user can withdraw money to Tendo Stash");
        tonikLogin(propertyFileReader("password", "Login"));
        double sAvailableBeforeAfterAddingStash = topUpModule.getAvailableBalanceInteger(OnBoardingPage.objAvailableBalance);
        extentLoggerPass("TDB_TS_45", "Available Balance : " + sAvailableBeforeAfterAddingStash);
        stashModule.stashScreen();
        swipe("UP",2);
        manageStashScreen();
        click(StashPage.getByOSType(platform,StashPage.objWithdrawToYourTonikAccount), "Withdraw To Your Tonik Account");
        stashModule.withdrawFromYourStashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashTargetAmountInputField), "Stash target Amount field");
        type(StashPage.getByOSType(platform,StashPage.objStashTargetAmountInputField), propertyFileReader("LessThanStashBalance", "Stash"), "LessThan Stash Balance ");
        if(platform.equalsIgnoreCase("Android")) {
            hideKeyboard();
        }else{
            click(StashPage.getByOSType(platform,StashPage.objKeyboardDoneButton),"Key board done button");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objWithdrawButton), "With draw Button");
        stashModule.reviewWithDrawal();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objConfirmButton), "Confirm button");
        click(StashPage.getByOSType(platform,StashPage.objOhYeahButton), "Yeah Button");
        waitTime(3000);
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementToBePresent(OnBoardingPage.objAvailableBalance,5,"Dashboard");
        double sAvailableBalanceAfterAddingStash = topUpModule.getAvailableBalanceInteger(OnBoardingPage.objAvailableBalance);
        if(sAvailableBalanceAfterAddingStash==sAvailableBeforeAfterAddingStash+Double.parseDouble(amount)) {
            logger.info("TDB_TS_45B, Tendo Stash - User can withdraw money to Tendo Stash validated");
            extentLoggerPass("TDB_TS_45B", "TDB_TS_45B, Tendo Stash - User can withdraw money to Tendo Stash validated");
        }else{
            logger.error("TDB_TS_45B, Tendo Stash - User can't withdraw money to Tendo Stash validated");
            extentLoggerFail("TDB_TS_45B", "TDB_TS_45B, Tendo Stash - User can't withdraw money to Tendo Stash validated");
        }
    }
    /**
     * Method to Verify that the 'Tendo Stash' tile is clickable
     * @throws Exception
     */
    public void verifyTendoStashTileIsClickable_TDB_TS_47() throws Exception {
        HeaderChildNode("TDB_TS_47, Tendo Stash - Verify that the 'Tendo Stash' tile is clickable");
            tonikLogin(propertyFileReader("password", "Login"));
        stashModule.stashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStartANewStash), "Start a New Stash");
        validateTendoStashTile();
        if(isClickable(TendoStashPage.getByOSType(platform,TendoStashPage.objTendoStashTile),"Tendo Stash Tile")){
            logger.info("TDB_TS_47, Tendo Stash - 'Tendo Stash' tile is clickable");
            extentLoggerPass("TDB_TS_47", "TDB_TS_47, Tendo Stash - 'Tendo Stash' tile is clickable");
        }else{
            logger.error("TDB_TS_47, Tendo Stash - 'Tendo Stash' tile is not clickable");
            extentLoggerFail("TDB_TS_47", "TDB_TS_47, Tendo Stash - 'Tendo Stash' tile is not clickable");
        }
    }
    /**
     * Method to Verify that the user is able to input a valid Target amount greater than or equal to 1,000 PHP
     * @throws Exception
     */
    public void verifyUserAbleToInputValidTargetAmount_TDB_TS_52() throws Exception {
        HeaderChildNode("TDB_TS_52, Tendo Stash - Verify that the user is able to input a valid Target amount greater than or equal to 1,000 PHP");
        tonikLogin(propertyFileReader("password", "Login"));
        stashModule.stashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objStartANewStash), "Start a New Stash");
        validateTendoStashTile();
        verifyElementPresentAndClick(TendoStashPage.getByOSType(platform,TendoStashPage.objTendoStashTile),getTextVal(TendoStashPage.getByOSType(platform,TendoStashPage.objTendoStashTile),"Tile"));
        setUpYourStashScreen();
        type(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText),propertyFileReader("TargetAmount","TendoStash"), "text_field");
        isButtonEnabled(StashPage.getByOSType(platform,StashPage.objNextButton),"Next button");
        logger.info("TDB_TS_52, Tendo Stash - User can able to input a valid Target amount greater than or equal to 1,000 PHP and Next button got enabled");
        extentLoggerPass("TDB_TS_52", "TDB_TS_52, Tendo Stash - User can able to input a valid Target amount greater than or equal to 1,000 PHP and Next button got enabled");
    }
    /**
     * Method to Verify that the user is not able to click the 'Next' button if the Target amount is invalid
     * @throws Exception
     */
    public void verifyUserAbleToInputInvalidTargetAmount_TDB_TS_53() throws Exception {
        HeaderChildNode("TDB_TS_53, Tendo Stash - Verify that the user is not able to click the 'Next' button if the Target amount is invalid");
        tonikLogin(propertyFileReader("password", "Login"));
        stashModule.stashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objStartANewStash), "Start a New Stash");
        validateTendoStashTile();
        verifyElementPresentAndClick(TendoStashPage.getByOSType(platform,TendoStashPage.objTendoStashTile),getTextVal(TendoStashPage.getByOSType(platform,TendoStashPage.objTendoStashTile),"Tile"));
        setUpYourStashScreen();
        type(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText),propertyFileReader("InvalidTargetAmount","TendoStash"), "text_field");
        click(StashPage.getByOSType(platform,StashPage.objNextButton),"Next button");
        if(verifyElementPresent(StashPage.getByOSType(platform,StashPage.objMinimumAmountCanBe),getTextVal(StashPage.getByOSType(platform,StashPage.objMinimumAmountCanBe),"Error message"))) {
            assertionValidation(getText(StashPage.getByOSType(platform, StashPage.objMinimumAmountCanBe)), propertyFileReader("MinTargetAmountMsg", "TendoStash"), " Min Target Amount Error Message");
            logger.info("TDB_TS_53, Tendo Stash - User can't able to click the 'Next' button if the Target amount is invalid validated");
            extentLoggerPass("TDB_TS_53", "TDB_TS_53, Tendo Stash - User can't able to click the 'Next' button if the Target amount is invalid validated");
        }
    }
    /**
     * Method to Verify that the user is redirected to the 'Set initial saving' screen after clicking the 'Next' button
     * @throws Exception
     */
    public void verifyUserIsNavigatedToSetInitialSavingAfterClickingOnNextButton_TDB_TS_54() throws Exception {
        HeaderChildNode("TDB_TS_54, Tendo Stash - Verify that the user is redirected to the 'Set initial saving' screen after clicking the 'Next' button");
        tonikLogin(propertyFileReader("password", "Login"));
        stashModule.stashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objStartANewStash), "Start a New Stash");
        validateTendoStashTile();
        verifyElementPresentAndClick(TendoStashPage.getByOSType(platform, TendoStashPage.objTendoStashTile), getTextVal(TendoStashPage.getByOSType(platform, TendoStashPage.objTendoStashTile), "Tile"));
        setUpYourStashScreen();
        type(StashPage.getByOSType(platform, StashPage.objTargetAmountEditText), propertyFileReader("TargetAmount", "TendoStash"), "text_field");
        click(StashPage.getByOSType(platform, StashPage.objNextButton), "Next button");
        if(verifyElementPresent(StashPage.getByOSType(platform, StashPage.objSetInitialSaving),getTextVal(StashPage.getByOSType(platform, StashPage.objSetInitialSaving),"page title"))){
            assertionValidation(getText(StashPage.getByOSType(platform, StashPage.objSetInitialSaving)), propertyFileReader("SetInitialSaving", "stash"), "page title");
            logger.info("TDB_TS_54, Tendo Stash - User redirected to the 'Set initial saving' screen after clicking the 'Next' button validated");
            extentLoggerPass("TDB_TS_54", "TDB_TS_54, Tendo Stash - User redirected to the 'Set initial saving' screen after clicking the 'Next' button validated");
        }
    }
    /**
     * Method to Verify that the user is able to see his current TSA balance
     * @throws Exception
     */
    public void verifyUserIsNavigatedToSetInitialSavingAfterClickingOnNextButton_TDB_TS_55() throws Exception {
        HeaderChildNode("TDB_TS_55, Tendo Stash - Verify that the user is able to see his current TSA balance");
        tonikLogin(propertyFileReader("password", "Login"));
        String balance = getText(OnBoardingPage.objAvailableBalance);
        stashModule.stashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objStartANewStash), "Start a New Stash");
        validateTendoStashTile();
        verifyElementPresentAndClick(TendoStashPage.getByOSType(platform, TendoStashPage.objTendoStashTile), getTextVal(TendoStashPage.getByOSType(platform, TendoStashPage.objTendoStashTile), "Tile"));
        setUpYourStashScreen();
        type(StashPage.getByOSType(platform, StashPage.objTargetAmountEditText), propertyFileReader("TargetAmount", "TendoStash"), "text_field");
        click(StashPage.getByOSType(platform, StashPage.objNextButton), "Next button");
        verifyElementPresent(StashPage.getByOSType(platform, StashPage.objSetInitialSaving), getTextVal(StashPage.getByOSType(platform, StashPage.objSetInitialSaving), "page title"));
        assertionValidation(getText(StashPage.getByOSType(platform, StashPage.objSetInitialSaving)), propertyFileReader("SetInitialSaving", "stash"), "page title");
        if(verifyElementPresent(StashPage.getByOSType(platform, StashPage.objBalanceOn),getTextVal(StashPage.getByOSType(platform, StashPage.objBalanceOn),"Balance in Set initial saving"))) {
            containsValidation(getText(StashPage.getByOSType(platform, StashPage.objBalanceOn)), balance, "Balance in Set initial saving");
            logger.info("TDB_TS_55, Tendo Stash - User can able to see his current TSA balance validated");
            extentLoggerPass("TDB_TS_55", "TDB_TS_55, Tendo Stash - User can able to see his current TSA balance validated");
        }else{
            logger.error("TDB_TS_55, Tendo Stash - User can't able to see his current TSA balance validated");
            extentLoggerFail("TDB_TS_55", "TDB_TS_55, Tendo Stash - User can't able to see his current TSA balance validated");
        }
    }
    /**
     * Method to Verify that the user is not able to skip setting initial saving
     * @throws Exception
     */
    public void verifyUserIsNotAbleToSettingInitialSaving_TDB_TS_56() throws Exception {
        HeaderChildNode("TDB_TS_56, Tendo Stash - Verify that the user is not able to skip setting initial saving");
        tonikLogin(propertyFileReader("password", "Login"));
        stashModule.stashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objStartANewStash), "Start a New Stash");
        validateTendoStashTile();
        verifyElementPresentAndClick(TendoStashPage.getByOSType(platform, TendoStashPage.objTendoStashTile), getTextVal(TendoStashPage.getByOSType(platform, TendoStashPage.objTendoStashTile), "Tile"));
        setUpYourStashScreen();
        type(StashPage.getByOSType(platform, StashPage.objTargetAmountEditText), propertyFileReader("TargetAmount", "TendoStash"), "text_field");
        click(StashPage.getByOSType(platform, StashPage.objNextButton), "Next button");
        waitTime(3000);
        verifyElementPresent(StashPage.getByOSType(platform, StashPage.objSetInitialSaving), getTextVal(StashPage.getByOSType(platform, StashPage.objSetInitialSaving), "page title"));
        assertionValidation(getText(StashPage.getByOSType(platform, StashPage.objSetInitialSaving)), propertyFileReader("SetInitialSaving", "stash"), "page title");
        verifyElementPresent(StashPage.getByOSType(platform, StashPage.objBalanceOn), getTextVal(StashPage.getByOSType(platform, StashPage.objBalanceOn), "Balance in Set initial saving"));
        if (verifyElementDisplayed(StashPage.getByOSType(platform, StashPage.objSkipForNow))) {
            logger.error("TDB_TS_56, Tendo Stash - User can able to skip setting initial saving validated");
            extentLoggerFail("TDB_TS_56", "TDB_TS_56, Tendo Stash - User can able to skip setting initial saving validated");
        } else {
            logger.info("TDB_TS_56, Tendo Stash - User is not able to skip setting initial saving validated");
            extentLoggerPass("TDB_TS_56", "TDB_TS_55, Tendo Stash - User is not able to skip setting initial saving validated");
        }
    }
    /**
     * Method to Verify that the user is able to set at least 1.00 PHP as initial saving to open the Tendo Stash
     * @throws Exception
     */
    public void verifyUserIsAbleToSetAtLeast1PHP_TDB_TS_57() throws Exception {
        HeaderChildNode("TDB_TS_57, Tendo Stash - Verify that the user is able to set at least 1.00 PHP as initial saving to open the Tendo Stash");
        tonikLogin(propertyFileReader("password", "Login"));
        stashModule.stashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objStartANewStash), "Start a New Stash");
        validateTendoStashTile();
        verifyElementPresentAndClick(TendoStashPage.getByOSType(platform, TendoStashPage.objTendoStashTile), getTextVal(TendoStashPage.getByOSType(platform, TendoStashPage.objTendoStashTile), "Tile"));
        setUpYourStashScreen();
        type(StashPage.getByOSType(platform, StashPage.objTargetAmountEditText), propertyFileReader("TargetAmount", "TendoStash"), "text_field");
        click(StashPage.getByOSType(platform, StashPage.objNextButton), "Next button");
        waitTime(3000);
        verifyElementPresent(StashPage.getByOSType(platform, StashPage.objSetInitialSaving), getTextVal(StashPage.getByOSType(platform, StashPage.objSetInitialSaving), "page title"));
        assertionValidation(getText(StashPage.getByOSType(platform, StashPage.objSetInitialSaving)), propertyFileReader("SetInitialSaving", "stash"), "page title");
        verifyElementPresent(StashPage.getByOSType(platform, StashPage.objBalanceOn), getTextVal(StashPage.getByOSType(platform, StashPage.objBalanceOn), "Balance in Set initial saving"));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objInitialSavingAmountInputTextField), "initial saving input text field");
        type(StashPage.getByOSType(platform,StashPage.objInitialSavingAmountInputTextField), propertyFileReader("initialSaving", "Stash"), "initialSaving");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNextButton), "Next button");
        waitTime(3000);
        if(verifyElementPresent(StashPage.getByOSType(platform,StashPage.objReviewStashDetails),getTextVal(StashPage.getByOSType(platform,StashPage.objReviewStashDetails),"Header"))) {
            assertionValidation(getText(StashPage.getByOSType(platform, StashPage.objReviewStashDetails)), propertyFileReader("ReviewStashDetails", "Stash"), " Header");
            logger.info("TDB_TS_57, Tendo Stash - User able to set at least 1.00 PHP as initial saving to open the Tendo Stash and navigated to Review Stash details screen");
            extentLoggerPass("TDB_TS_57", "TDB_TS_55, Tendo Stash - User able to set at least 1.00 PHP as initial saving to open the Tendo Stash Review Stash details screen");
        }
    }
    /**
     * Method to Verify that the user is redirected to the 'Stash summary' screen after setting initial saving
     * @throws Exception
     */
    public void verifyUserIsRedirectedReviewStashDetailsAfterSettingInitialSaving_TDB_TS_58() throws Exception {
        HeaderChildNode("TDB_TS_58, Tendo Stash - Verify that the user is redirected to the 'Stash summary' screen after setting initial saving");
        tonikLogin(propertyFileReader("password", "Login"));
        stashModule.stashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objStartANewStash), "Start a New Stash");
        validateTendoStashTile();
        verifyElementPresentAndClick(TendoStashPage.getByOSType(platform, TendoStashPage.objTendoStashTile), getTextVal(TendoStashPage.getByOSType(platform, TendoStashPage.objTendoStashTile), "Tile"));
        setUpYourStashScreen();
        type(StashPage.getByOSType(platform, StashPage.objTargetAmountEditText), propertyFileReader("TargetAmount", "TendoStash"), "text_field");
        click(StashPage.getByOSType(platform, StashPage.objNextButton), "Next button");
        setInitialSavingAmount(propertyFileReader("initialSaving", "Stash"));
        if(verifyElementPresent(StashPage.getByOSType(platform,StashPage.objReviewStashDetails),getTextVal(StashPage.getByOSType(platform,StashPage.objReviewStashDetails),"Header"))) {
            assertionValidation(getText(StashPage.getByOSType(platform, StashPage.objReviewStashDetails)), propertyFileReader("ReviewStashDetails", "Stash"), " Header");
            reviewStashDetailsScreen(propertyFileReader("TendoStashInterestRateValue","TendoStash"),propertyFileReader("TargetAmount","TendoStash"));
            logger.info("TDB_TS_58, Tendo Stash - User able to set at least 1.00 PHP as initial saving to open the Tendo Stash and navigated to Review Stash details screen");
            extentLoggerPass("TDB_TS_58", "TDB_TS_58, Tendo Stash - User able to set at least 1.00 PHP as initial saving to open the Tendo Stash Review Stash details screen");
        }
    }
    /**
     * Method to Verify that the user is able to see the following details in Stash for Tendo Stash: Stash Name, Target Amount, Interest Rate, and Initial Saving
     * @throws Exception
     */
    public void reviewStashDetailsScreenValidation_TDB_TS_59() throws Exception {
        HeaderChildNode("TDB_TS_59, Tendo Stash - Verify that the user is able to see the following details in Stash for Tendo Stash: Stash Name, Target Amount, Interest Rate, and Initial Saving");
        tonikLogin(propertyFileReader("password", "Login"));
        stashModule.stashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objStartANewStash), "Start a New Stash");
        validateTendoStashTile();
        verifyElementPresentAndClick(TendoStashPage.getByOSType(platform, TendoStashPage.objTendoStashTile), getTextVal(TendoStashPage.getByOSType(platform, TendoStashPage.objTendoStashTile), "Tile"));
        setUpYourStashScreen();
        type(StashPage.getByOSType(platform, StashPage.objTargetAmountEditText), propertyFileReader("TargetAmount", "TendoStash"), "text_field");
        click(StashPage.getByOSType(platform, StashPage.objNextButton), "Next button");
        setInitialSavingAmount(propertyFileReader("initialSaving", "Stash"));
        if(verifyElementPresent(StashPage.getByOSType(platform,StashPage.objReviewStashDetails),getTextVal(StashPage.getByOSType(platform,StashPage.objReviewStashDetails),"Header"))) {
            reviewStashDetailsScreen(propertyFileReader("TendoStashInterestRateValue","TendoStash"),propertyFileReader("TargetAmount","TendoStash"));
            logger.info("TDB_TS_59, Tendo Stash - User able to set at least 1.00 PHP as initial saving to open the Tendo Stash and navigated to Review Stash details screen");
            extentLoggerPass("TDB_TS_59", "TDB_TS_59, Tendo Stash - User able to set at least 1.00 PHP as initial saving to open the Tendo Stash Review Stash details screen");
        }
    }
    /**
     * Method to Verify that the interest rate for Tendo Stash is 4.75%
     * @param exceptedInterest
     * @throws Exception
     */
    public void verifyInterestRateForTendoStash_TDB_TS_60(String exceptedInterest) throws Exception {
        HeaderChildNode("TDB_TS_60, Tendo Stash - Verify that the interest rate for Tendo Stash is 4.75%");
        tonikLogin(propertyFileReader("password", "Login"));
        stashModule.stashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objStartANewStash), "Start a New Stash");
        validateTendoStashTile();
        verifyElementPresentAndClick(TendoStashPage.getByOSType(platform, TendoStashPage.objTendoStashTile), getTextVal(TendoStashPage.getByOSType(platform, TendoStashPage.objTendoStashTile), "Tile"));
        setUpYourStashScreen();
        type(StashPage.getByOSType(platform, StashPage.objTargetAmountEditText), propertyFileReader("TargetAmount", "TendoStash"), "text_field");
        click(StashPage.getByOSType(platform, StashPage.objNextButton), "Next button");
        setInitialSavingAmount(propertyFileReader("initialSaving", "Stash"));
        if(verifyElementPresent(StashPage.getByOSType(platform,StashPage.objReviewStashDetails),getTextVal(StashPage.getByOSType(platform,StashPage.objReviewStashDetails),"Header"))) {
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objInterestRateHeader), "InterestRate"), propertyFileReader("InterestRate","stash"), "InterestRate", "contains");
            assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objInterestRateOnReviewStash)),exceptedInterest,"Interest value");
            logger.info("TDB_TS_60, Tendo Stash - User able to set at least 1.00 PHP as initial saving to open the Tendo Stash and navigated to Review Stash details screen");
            extentLoggerPass("TDB_TS_60", "TDB_TS_60, Tendo Stash - User able to set at least 1.00 PHP as initial saving to open the Tendo Stash Review Stash details screen");
        }
    }
    /**
     * Method to Verify that the user is able to upload a stash photo while opening Tendo Stash
     * @throws Exception
     */
    public void verifyUserAbleToUploadStashPhotoWhileOpeningTendoStash_TDB_TS_50() throws Exception {
        HeaderChildNode("TDB_TS_50, Tendo Stash - Verify that the user is able to upload a stash photo while opening Tendo Stash");
        tonikLogin(propertyFileReader("password", "Login"));
        stashModule.stashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objStartANewStash), "Start a New Stash");
        validateTendoStashTile();
        verifyElementPresentAndClick(TendoStashPage.getByOSType(platform, TendoStashPage.objTendoStashTile), getTextVal(TendoStashPage.getByOSType(platform, TendoStashPage.objTendoStashTile), "Tile"));
        setUpYourStashScreen();
        uploadStashImage();
        waitTime(3000);
        verifyElementPresent(StashPage.getByOSType(platform, StashPage.objSetUpYourStash), "Set up your Stash");
        containsValidation(getText(StashPage.getByOSType(platform, StashPage.objSetUpYourStash)), propertyFileReader("SetUpYourStashScreen", "Stash"), ":SetUpYourStashScreen");
        logger.info("TDB_TS_50, Tendo Stash - User able to upload a stash photo while opening Tendo Stash validated");
        extentLoggerPass("TDB_TS_50", "TDB_TS_50, Tendo Stash - User able to upload a stash photo while opening Tendo Stash validated");
    }
    /**
     * Method to Verify that the user is able to see Stash for Tendo Stash
     * @throws Exception
     */
    public void verifyUserAbleToSeeStashForTendoStash_TDB_TS_51() throws Exception {
        HeaderChildNode("TDB_TS_51, Tendo Stash - Verify that the user is able to see Stash for Tendo Stash");
        tonikLogin(propertyFileReader("password", "Login"));
        stashModule.stashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objStartANewStash), "Start a New Stash");
        validateTendoStashTile();
        verifyElementPresentAndClick(TendoStashPage.getByOSType(platform,TendoStashPage.objTendoStashTile),getTextVal(TendoStashPage.getByOSType(platform,TendoStashPage.objTendoStashTile),"Tile"));
        setUpYourStashScreen();
        type(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText),propertyFileReader("TargetAmount","TendoStash"), "text_field");
        isButtonEnabled(StashPage.getByOSType(platform,StashPage.objNextButton),"Next button");
        logger.info("TDB_TS_52, Tendo Stash - User can able to input a valid Target amount greater than or equal to 1,000 PHP and Next button got enabled");
        extentLoggerPass("TDB_TS_52", "TDB_TS_52, Tendo Stash - User can able to input a valid Target amount greater than or equal to 1,000 PHP and Next button got enabled");
    }
}