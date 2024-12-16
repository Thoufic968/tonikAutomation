package com.tonik.bizfunctions;

import com.driverInstance.DriverManager;
import com.tonik.pageObject.*;
import com.tonik.utility.Utilities;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;

import static com.tonik.utility.Utilities.*;
public class StashModule extends BaseClass {
    StashAPIModule stashAPIModule=new StashAPIModule();
    String platform = Utilities.getPlatform();
    public StashModule() {
        super();
    }
    double achievedAmount;
    double stashBalanceOnTile;
    double groupStashAmountValue;
    double currentAmountValue;
    double stashedAmountValue;
    double updatedAmountValue;
    double updatedTargetAmountValue;
    double groupTargetAmountValue;
    String groupTargetAmount;
    String groupStashAmount;
    String accountHolderName;
    String contributedAmount;
    String currentAmount;
    String stashName;
    String stashOwnerName;
    String ownerName;
    double initialStashBalanceOnTile;
    /**
     * Method to verify stash welcome screens
     * @throws Exception
     */
    public void stashWelcomeScreens() throws Exception {
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objIndividualStashWelcomeScreen),5,"Stash Welcome screen");
        if (verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objIndividualStashWelcomeScreen))){
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objIndividualStashDescription), "individualStashDescription"), "individualStashDescription", "Stash", "contains");
            click(StashPage.getByOSType(platform,StashPage.objAlrightButton), getTextVal(StashPage.getByOSType(platform,StashPage.objAlrightButton), ":Right Button"));
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objDeserveThis), "DeserveThis"), "DeserveThis", "Stash", "contains");
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objDeserveThisDescription), "Deserve This Description"), "DeserveThisDescription", "Stash", "contains");
            click(StashPage.getByOSType(platform,StashPage.objAmazingButton), getTextVal(StashPage.getByOSType(platform,StashPage.objAmazingButton), ":Amazing Button"));
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objRebound), "Rebound"), "Rebound", "Stash", "contains");
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objReboundDescription), "Rebound Description"), "ReboundDescription", "Stash", "contains");
            click(StashPage.getByOSType(platform,StashPage.objReallyButton), getTextVal(StashPage.getByOSType(platform,StashPage.objReallyButton), ":Really Button"));
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objGoals), "Goals"), "Goals", "Stash", "contains");
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objGoalsDescription), "Goals Description"), "GoalsDescription", "Stash", "contains");
            click(StashPage.getByOSType(platform,StashPage.objCoolButton), getTextVal(StashPage.getByOSType(platform,StashPage.objCoolButton), ":Cool Button"));
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objGoals), "werkIt"), "werkIt", "Stash", "contains");
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objWerkitDescription), "Rebound Description"), "ReboundDescription", "Stash", "contains");
            click(StashPage.getByOSType(platform,StashPage.objLetsStartButton), getTextVal(StashPage.getByOSType(platform,StashPage.objLetsStartButton), ":Start Stashing Button"));
        }
    }
    /**
     * Method to verify Stash Screen Ui validations
     * @throws Exception
     */
    public void stashScreen() throws Exception {
        swipe("UP", 2);
        if (verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader))) {
            click(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader), "Stash card");
        } else {
            click(StashPage.getByOSType(platform,StashPage.objStashCard), "Stash card");
        }
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashHeader), "Stash Header");
        containsValidation(getText(StashPage.getByOSType(platform,StashPage.objStashHeader)), propertyFileReader("StashHeader", "Stash"), ": Header :Stash");
        containsValidation(getText(StashPage.getByOSType(platform,StashPage.objStartANewStash)), propertyFileReader("StartANewStash", "Stash"), ": StartANewStash");
        containsValidation(getText(StashPage.getByOSType(platform,StashPage.obj5Of5AvailableStashes)), propertyFileReader("5Of5AvailableStashes", "Stash"), ": 5Of5AvailableStashes");
    }
    /**
     * Method verify new stash screen validations
     * @throws Exception
     */
    public void newStashScreen() throws Exception {
        if (verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStartANewStash), "Start a New Stash")) {
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashHeader), "Stash Header");
            waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objOpenANewStash), 10, "Open a New Stash");
            containsValidation(getText(StashPage.getByOSType(platform,StashPage.objStashHeader)), propertyFileReader("StashHeader", "Stash"), ": Stash");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objLearnMoreInOurFAQ), "Learn More In Our FAQ");
            containsValidation(getText(StashPage.getByOSType(platform,StashPage.objOpenANewStash)), propertyFileReader("OpenANewStash", "Stash"), ": open a New Stash");
            containsValidation(getText(StashPage.getByOSType(platform,StashPage.objLuvStash)), propertyFileReader("LuvStash", "Stash"), ":Luv Stash");
            containsValidation(getText(StashPage.getByOSType(platform,StashPage.objEmergencyStash)), propertyFileReader("EmergencyStash", "Stash"), ":Emergency Stash");
            containsValidation(getText(StashPage.getByOSType(platform,StashPage.objVacationStash)), propertyFileReader("VacationStash", "Stash"), ":Vacation Stash");
            swipe("UP",1);
            verticalSwipeTillElementDisplayed(StashPage.getByOSType(platform,StashPage.objTuitionStash));
            containsValidation(getText(StashPage.getByOSType(platform,StashPage.objTuitionStash)), propertyFileReader("TuitionStash", "Stash"), ":Tuition Stash");
            containsValidation(getText(StashPage.getByOSType(platform,StashPage.objGameConsole)), propertyFileReader("GameConsole", "Stash"), ":Game Console");
        }
    }
    /**
     * Method to verify open a new stash screen UI validations
     * @throws Exception
     */
    public void openANewStashScreen() throws Exception {
        if (verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objOpenANewStash), "Open a New Stash")) {
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSelectStashTypePopup), "Select Stash type Popup");
            containsValidation(getText(StashPage.getByOSType(platform,StashPage.objSelectStashTypePopup)), propertyFileReader("SelectStashTypePopup", "Stash"), ":Stash Type popup");
            containsValidation(getText(StashPage.getByOSType(platform,StashPage.objThisCantBeChangeLater)), propertyFileReader("ThisCantBeChangeLater", "Stash"), ":This can't be changed later");
            containsValidation(getText(StashPage.getByOSType(platform,StashPage.objGroupStashOnPopup)), propertyFileReader("GroupStash", "Stash"), ":Group Stash");
            containsValidation(getText(StashPage.getByOSType(platform,StashPage.objTeamworkMakesThisStashWorkOnPopup)), propertyFileReader("TeamWorkMakesThisStashWork", "Stash"), ":TeamWorkMakesThisStashWork");
            containsValidation(getText(StashPage.getByOSType(platform,StashPage.objSoloStashOnPopup)), propertyFileReader("SoloStash", "Stash"), ":SoloStash");
            containsValidation(getText(StashPage.getByOSType(platform,StashPage.objKeepItPersonalAndSaveInThisOnPopup)), propertyFileReader("KeepItPersonalAndSaveInThis", "Stash"), ":KeepItPersonalAndSaveInThis");
        }
    }
    /**
     * Method to verify Setup your Stash screen UI validations
     * @throws Exception
     */
    public void setUpYourStashScreen() throws Exception {
        if (verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSoloStashOnPopup), "Select Solo Stash on pop up")) {
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSetUpYourStash), "Set up your Stash");
            containsValidation(getText(StashPage.getByOSType(platform,StashPage.objSetUpYourStash)), propertyFileReader("SetUpYourStashScreen", "Stash"), ":SetUpYourStashScreen");
            containsValidation(getText(StashPage.getByOSType(platform,StashPage.objStashFor)), propertyFileReader("StashFor", "Stash"), ":StashFor");
            containsValidation(getText(StashPage.getByOSType(platform,StashPage.objTargetAmount)), propertyFileReader("TargetAmount", "Stash"), ":TargetAmount");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objNextButton), "Next button");
        }
    }
    /**
     * Method to verify Setup or modify your Stash screen validations
     * @throws Exception
     */
    public void setUpOrModifyYourStashScreenValidations() throws Exception {
        uploadProfilePicture();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNameInputTextField), "Stash for Edit text field");
        clearField(StashPage.getByOSType(platform,StashPage.objNameInputTextField), "Stash for Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objNameInputTextField), propertyFileReader("lessThan", "Stash"), "text_field");
        click(ProfilePage.getByOSType(platform,ProfilePage.objProfilePicture), "Profile Picture");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objPleaseEnterAValidStashName), "Please Enter a valid stash name inline Error Message");
        click(StashPage.getByOSType(platform,StashPage.objNameInputTextField), "Stash for Edit text field");
        clearField(StashPage.getByOSType(platform,StashPage.objNameInputTextField), "Stash for Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objNameInputTextField), propertyFileReader("MoreThan", "Stash"), "MoreThan");
        click(ProfilePage.getByOSType(platform,ProfilePage.objProfilePicture), "Profile Picture");
        String updatedName = getText(StashPage.getByOSType(platform,StashPage.objNameInputTextField));
        if (!Objects.equals(updatedName, propertyFileReader("MoreThan", "Stash"))) {
            logger.info("Accepted only 35 character");
            extentLoggerPass("Minimum Char", "Accepted only 35 character");
        }
        click(StashPage.getByOSType(platform,StashPage.objNameInputTextField), "Stash for Edit text field");
        clearField(StashPage.getByOSType(platform,StashPage.objNameInputTextField), "Stash for Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objNameInputTextField), propertyFileReader("validInput", "Stash"), "text_field");
        click(ProfilePage.getByOSType(platform,ProfilePage.objProfilePicture), "Profile Picture");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTargetAmountField), "Target Amount Edit text field");
        clearField(StashPage.getByOSType(platform,StashPage.objTargetAmountField), "Target Amount Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objTargetAmountField), propertyFileReader("LessThanTarget", "Stash"), "LessThan Target Amount ");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSetUpYourStashNextButton), "Next button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objMinimumAmountCanBe), "Minimum Amount can be 1000 Peso Error Message");
        clearField(StashPage.getByOSType(platform,StashPage.objTargetAmountField), "Target Amount Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objTargetAmountField), propertyFileReader("MoreThanTarget", "Stash"), "MoreThan Target Amount");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSetUpYourStashNextButton), "Next button");
    }
    /**
     * Method to verify set initial saving screen UI validations
     * @throws Exception
     */
    public void setInitialSavingScreen() throws Exception {
        if (verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSetInitialSaving), "Select Solo Stash on pop up")) {
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objSetInitialSaving), "SetInitialSaving"), "SetInitialSaving", "SetInitialSaving", "contains");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objBalanceOn), getTextVal(StashPage.getByOSType(platform,StashPage.objBalanceOn), ":Balance"));
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objPhpText), "Php text");
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objSkipForNow), "SkipForNow"), "SkipForNow", "SkipForNow", "contains");
        }
    }
    /**
     * Method to verify review stash details screen
     * @throws Exception
     */
    public void reviewStashDetailsScreen() throws Exception {
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objTransferFromHeader),10,"Transfer From Header");
        if (verifyElementPresent(StashPage.getByOSType(platform,StashPage.objReviewStashDetails), "Review Stash details")) {
            valueValidation(getText(StashPage.getByOSType(platform,StashPage.objReviewStashDetails)), propertyFileReader("ReviewStashDetails","stash"), "ReviewStashDetails", "contains");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashNameOnTheImage), "Stash Name On the Image");
            valueValidation(getText(StashPage.getByOSType(platform,StashPage.objTargetAmountHeader)), propertyFileReader("TargetAmount","stash"), "TargetAmount", "contains");
            String targetAmount=getText(StashPage.getByOSType(platform,StashPage.objTargetAmountOnReviewStash)).replaceAll("[,₱]", "");
            double amount = Double.parseDouble(targetAmount);
            assertionValidation(String.valueOf((int)amount),propertyFileReader("MoreThanTarget","stash"),"Target amount");
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objInitialSavingHeader), "InitialSaving"), propertyFileReader("InitialSaving","stash"), "InitialSaving", "contains");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objInitialSavingOnReviewStash), getTextVal(StashPage.getByOSType(platform,StashPage.objInitialSavingOnReviewStash), ":Initial Saving on review Stash"));
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objTransferFromHeader), "TransferFrom"), propertyFileReader("TransferFrom","stash"), "TransferFrom", "contains");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTransferFromOnReviewStash), getTextVal(StashPage.getByOSType(platform,StashPage.objTransferFromOnReviewStash), ":Transfer From on review Stash"));
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objInterestRateHeader), "InterestRate"), propertyFileReader("InterestRate","stash"), "InterestRate", "contains");
            assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objInterestRateOnReviewStash)),propertyFileReader("InterestRateValue","stash"),"Interest value");
            swipe("UP",1);
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objStashPDICText), "StashPDICText"), propertyFileReader("StashPDICText","stash"), "StashPDICText", "contains");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTermsAndCondition), "Terms and Condition");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTermsAndConditionCheck), "Terms and Condition Check Box");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objCreateStashButtonDisabled), "Create Stash");
        }
    }
    /**
     * Method to verify Stash success screen
     * @throws Exception
     */
    public void stashSuccessScreen() throws Exception{
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objCreateStashButtonEnabled), "Create Stash");
        if (verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objDoItLater))) {
            click(StashPage.getByOSType(platform,StashPage.objDoItLater), "Do It Later");
        }
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSoloStashCreatedLuv), "Solo Stash created Luv");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objSoloStashCreatedLuv), "SoloStashCreatedLuv"), "SoloStashCreatedLuv", "SoloStashCreatedLuv", "contains");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objStashSuccessDescription), "StashSuccessDescription"), "StashSuccessDescription", "StashSuccessDescription", "contains");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objDoneButton), "Done Button");
    }
    /**
     * Method to verify Newly created solo stash
     * @throws Exception
     */
    public void newlyCreatedSoloGroupStash() throws Exception {
        swipe("UP",3);
        if (verifyElementPresent(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image")) {
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objLastTargetAmount), "Achieved");
            String lastTargetAmount = getText(StashPage.getByOSType(platform,StashPage.objLastTargetAmount)).split(" ")[1];
            achievedAmount = getAvailableBalanceInteger(lastTargetAmount);
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objLastAddToStashButton), "Add to stash");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objLastManageButton), "Manage");
        }
    }
    /**
     * Method to verify manage Stash screen UI validation
     * @throws Exception
     */
    public void manageStashScreen() throws Exception {
        swipe("up",3);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastManageButton), "Manage on the image");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objManageStash), "ManageStash"), "ManageStash", "ManageStash", "contains");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashDetails), "Stash details");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objStashDetails), "StashDetails"), "StashDetails", "StashDetails", "contains");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objModify), "Modify");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objModify), "Modify"), "Modify", "Modify", "contains");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objWithdrawToYourTonikAccount), "WithDraw To Your Tonik Account");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objWithdrawToYourTonikAccount), "WithdrawToYourTonikAccount"), "WithdrawToYourTonikAccount", "WithdrawToYourTonikAccount", "contains");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objClose), "Close");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objClose), "Close"), "Close", "Close", "contains");
    }
    /**
     * Method to verify stash tile content UI validation
     * @throws Exception
     */
    public void stashTileContent() throws Exception {
        swipe("UP",1);
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader), "Total Stash balance On header");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceOnDashBoard), getTextVal(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceOnDashBoard), ":Stash Balance"));
        if(verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objLooksLonelyHere))){
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objLooksLonelyHere), "Looks Lonely here");
        }else{
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceDesc),"Team work");
        }
        if(verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objLooksLonelyHere))) {
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objLooksLonelyHere), "Looks Lonely here");
        }else {
            verifyElementPresent(StashPage.getByOSType(platform, StashPage.objTeamWorks), "Team works");
        }
    }
    /**
     * Method to verify Confirm Transfer to stash screen validation
     * @throws Exception
     */
    public void confirmTransferToStashScreen(String amount) throws Exception {
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objAmount),10,"Amount to transfer");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objConfirmTransferToStash), "Confirm Transfer To Stash");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objConfirmTransferToStash), "ConfirmTransferToStash"), "ConfirmTransferToStash", "ConfirmTransferToStash", "contains");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objAmount), getTextVal(StashPage.getByOSType(platform,StashPage.objAmount), ":Amount to transfer"));
        containsValidation(getText(StashPage.getByOSType(platform,StashPage.objAmount)).replaceAll("[,₱]",""),amount,"amount");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objFromAccount), getTextVal(StashPage.getByOSType(platform,StashPage.objFromAccount), ":From Account"));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objToAccount), getTextVal(StashPage.getByOSType(platform,StashPage.objToAccount), ":To Account"));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashOwner), getTextVal(StashPage.getByOSType(platform,StashPage.objStashOwner), ":Stash Owner "));
    }
    /**
     * Method to verify transaction details screen validations
     * @throws Exception
     */
    public void transactionDetailsScreen() throws Exception {
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTransactionDetails), "Transaction Details");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objTransactionDetails), "TransactionDetails"), "TransactionDetails", "TransactionDetails", "contains");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objAmount), getTextVal(StashPage.getByOSType(platform,StashPage.objAmount), ":Amount to transfer"));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objFromTransactionDetails), getTextVal(StashPage.getByOSType(platform,StashPage.objFromTransactionDetails), ":From Account"));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objToAccount), getTextVal(StashPage.getByOSType(platform,StashPage.objToAccount), ":To Account"));
        if (verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objWhen))) {
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objWhen), getTextVal(StashPage.getByOSType(platform,StashPage.objWhen), ":When"));
        }
        if (verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objRefNumber))) {
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objRefNumber), getTextVal(StashPage.getByOSType(platform,StashPage.objRefNumber), ":Ref Number"));
        }
    }
    /**
     * Method to verify Selected solo Stash screen UI validations
     * @param stash
     * @throws Exception
     */
    public void selectedSoloGroupStashScreen(String stash) throws Exception {
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objNameOfTheSoloStash), "Name Of The Solo Stash");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objAchievedAmountOnSoloStash), getTextVal(StashPage.getByOSType(platform,StashPage.objAchievedAmountOnSoloStash), ":Achieved Amount On Solo Stash"));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objInterestRateValue), getTextVal(StashPage.getByOSType(platform,StashPage.objInterestRateValue), "Interest Value"));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objAddToStash), "Add To stash");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objManage), "Manage");
        if (verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objMyself))) {
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objMyself), "MySelf"), "MySelf", "MySelf", "contains");
        } else {
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objManageSaversText), "ManageSavers"), "ManageSavers", "ManageSavers", "contains");
        }
        if (verifyElementDisplayed(StashPage.objCredit(platform,stash))) {
            valueValidation(getTextVal(StashPage.objCredit(platform,stash), "Credit"), "Credit", "Credit", "contains");
        }
        if (verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objMyselfTransactionAmount))) {
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objMyselfTransactionAmount), getTextVal(StashPage.getByOSType(platform,StashPage.objMyselfTransactionAmount), "Myself Transaction Amount"));
        }
    }
    /**
     * Method to verify Add to stash validations
     * @throws Exception
     */
    public void addToStashValidations() throws Exception {
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), "Add to stash Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), propertyFileReader("lessThanPeso", "Stash"), "LessThan Amount Input Text Field");
        if(platform.equalsIgnoreCase("Android")) {
            hideKeyboard();
        }else{
            click(StashPage.getByOSType(platform,StashPage.objKeyboardDoneButton),"Key board done button");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNextButton), "Next button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objMinimumAmountCanBe1Peso), "Minimum Amount Can be 1 Peso");
        click(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), "Initial Saving Amount Input Text Field");
        clearField(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), "Target Amount Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), propertyFileReader("MoreThanPeso", "Stash"), "More than Tonik balance");
        if(platform.equalsIgnoreCase("Android")) {
            hideKeyboard();
        }else{
            click(StashPage.getByOSType(platform,StashPage.objKeyboardDoneButton),"Key board done button");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNextButton), "Next button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objNoEnoughBalanceInTonikAccount), "No Enough Balance In Tonik Account");
        click(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), "Initial Saving Amount Input Text Field");
        clearField(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), "Target Amount Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), propertyFileReader("ValidPeso", "Stash"), "Valid Peso");
        if(platform.equalsIgnoreCase("Android")) {
            hideKeyboard();
        }else{
            click(StashPage.getByOSType(platform,StashPage.objKeyboardDoneButton),"Key board done button");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNextButton), "Next button");
    }
    /**
     * Method to verify Money Stashed screen UI validations
     * @throws Exception
     */
    public void moneyStashedScreenValidation() throws Exception {
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objYouAdded), 10, "Money Stashed");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objMoneyStashed), "MoneyStashed"), "MoneyStashed", "MoneyStashed", "contains");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objYouAdded), "You Added");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objViewDetailsOfHowWeMovedIt), "View Details How We Moved It");
    }
    /**
     * Method to verify History transaction details screen validations
     * @throws Exception
     */
    public void historyTransactionDetailsScreen() throws Exception {
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objFromAccount),10,"From account");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTransactionDetails), "Transaction Details");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objTransactionDetails), "TransactionDetails"), "TransactionDetails", "TransactionDetails", "contains");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objAmountDetails), getTextVal(StashPage.getByOSType(platform,StashPage.objAmountDetails), ":Amount to transfer"));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objFromAccount), getTextVal(StashPage.getByOSType(platform,StashPage.objFromAccount), ":From Account"));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objHistoryTransactionDetailsToAccount), getTextVal(StashPage.getByOSType(platform,StashPage.objHistoryTransactionDetailsToAccount), ":To Account"));
        if (verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objWhenDetails))) {
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objWhenDetails), getTextVal(StashPage.getByOSType(platform,StashPage.objWhenDetails), ":When"));
        }
        if (verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objRefNoDetails))) {
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objRefNoDetails), getTextVal(StashPage.getByOSType(platform,StashPage.objRefNoDetails), ":Ref Number"));
        }
    }
    /**
     * Method to verify Stash Details screen UI validations
     * @throws Exception
     */
    public void stashDetailsScreen() throws Exception {
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashDetailsHeader), "Stash details");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objStashDetailsHeader), "StashDetails"), "StashDetails", "StashDetails", "contains");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objStashIDOption), "StashID"), "StashID", "StashID", "contains");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashID), getTextVal(StashPage.getByOSType(platform,StashPage.objStashID), ":Stash ID"));
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objStartingDateOption), "StartingDate"), "StartingDate", "StartingDate", "contains");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStartingDate), getTextVal(StashPage.getByOSType(platform,StashPage.objStartingDate), ":Starting date"));
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.ObjTargetAmountOption), "TargetAmount"), "TargetAmount", "TargetAmount", "contains");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashDetailsTargetAmountValue), getTextVal(StashPage.getByOSType(platform,StashPage.objStashDetailsTargetAmountValue), ":Target amount value"));
    }
    /**
     * Method to verify Upload a profile picture from camera and gallery
     * @throws Exception
     */
    public void uploadProfilePicture() throws Exception {
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfilePicture), "Profile Picture");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objPickFromYourGallery), "Pick from Your Gallery");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objTakeAPhoto), "Take a Photo");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objSwitchCameraButton), "Switch camera Button");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objCameraCloseButton), "Camera Close Button");
        if (verifyElementDisplayed(ProfilePage.getByOSType(platform, ProfilePage.objTakeAPhoto))) {
            click(ProfilePage.getByOSType(platform, ProfilePage.objTakeAPhoto), "Take a Photo");
        } else {
            verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfilePicture), "Profile Picture");
            verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objTakeAPhoto), "Take a Photo");
        }
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objSwitchCameraButton), "Switch camera Button");
        waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objShutterButton), 10, "Shutter Button");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objShutterButton), "Shutter Button");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objDoneButton), "Done Button");
        click(ProfilePage.getByOSType(platform, ProfilePage.objProfilePicture), "Profile Picture");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objPickFromYourGallery), "Pick from Your Gallery");
        if (platform.equalsIgnoreCase("Android")) {
            click(ProfilePage.getByOSType(platform, ProfilePage.objGallery), "Gallery");
            click(ProfilePage.getByOSType(platform, ProfilePage.objAllPhotos), "All Photos");
        } else {
            click(ProfilePage.getByOSType(platform, ProfilePage.objAlbumIOS), "Album");
        }
        click(ProfilePage.getByOSType(platform, ProfilePage.objAutomationFolder), "Automation Folder");
        click(ProfilePage.getByOSType(platform, ProfilePage.objSelectPhotoInFolder), "Select Photo");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objCameraIcon), "Camera icon on bottom of the Picture");
    }
    /**
     * Method to verify Modify stash screen validations
     * @throws Exception
     */
    public void modifyStashScreen() throws Exception {
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objModifyStashHeader), "Modify Stash");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objImageView), "Image");
        if(platform.equalsIgnoreCase("Android")) {
            verifyElementPresent(StashPage.getByOSType(platform, StashPage.objNameInputTxt), "name field");
        }else{
            verifyElementPresent(StashPage.getByOSType(platform, StashPage.objNameInputTextField), "name field");
        }
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTargetAmountField), "Traget Amount field");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSetUpYourStashNextButton), "Save button");
    }
    /**
     * Method to verify You updates your stash screen
     * @throws Exception
     */
    public void youUpdatesYourStashScreen() throws Exception {
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objYouUpdatesYourStash), "You updates Your Stash");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objYouUpdatesYourStashDescription), "YouUpdatedYourStashDescription"), "YouUpdatedYourStashDescription", "YouUpdatedYourStashDescription", "contains");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objDoneButton), "Done Button");
    }
    /**
     * Method to verify With draw from your stash screen
     * @throws Exception
     */
    public void withdrawFromYourStashScreen() throws Exception {
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objWithdrawFromStashTitle), "Withdraw from your Stash");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objBalanceTxt), "Balance: amount");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashTargetAmountInputField), "Traget Amount field");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objWithdrawButton), "Withdraw Button");
    }
    /**
     * Method to verify review with withdrawl
     * @throws Exception
     */
    public void reviewWithDrawal() throws Exception {
        waitTime(3000);
        if (verifyElementPresent(StashPage.getByOSType(platform,StashPage.objReviewWithDrawalHeader), "Review Withdrawl")) {
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objReviewWithDrawalHeader), "ReviewWithDrawl"), "ReviewWithDrawl", "ReviewWithDrawl", "contains");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objReviewWithDrawalAmount), "Amount");
            containsValidation(getText(StashPage.getByOSType(platform,StashPage.objReviewWithDrawalAmount)), propertyFileReader("LessThanStashBalance", "Stash"),"Withdraw amount");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objReviewWithDrawalFrom), "From ");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objReviewWithDrawalTo), "To");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objConfirmButton), "Confirm button");
        }
    }
    /**
     * Method to verify sweet screen UI validations
     * @throws Exception
     */
    public void sweetScreen() throws Exception {
        if (verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSweet), "Review Withdrawl")) {
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objSweet), "sweet"), "sweet", "sweet", "contains");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSweetDescription), "Sweet description");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSweetViewDetails), "Sweet view Details ");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objOhYeahButton), "Oh Yeah button");
        }
    }
    /**
     * Method to verify close popup validation
     * @throws Exception
     */
    public void closePopup() throws Exception {
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objAreYouSureYouWantToCloseTheStashPopup), "Are you sure close popup");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStayButton), "Stay Button On popup");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objYesCloseTheStash), "Yes Close The Stash On popup");
    }
    /**
     * Method to verify Broke the stash screen validations
     * @throws Exception
     */
    public void brokeTheStashScreen() throws Exception {
        if (verifyElementPresent(StashPage.getByOSType(platform,StashPage.objYouBrokeTheStash), "broke the Stash")) {
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objYouBrokeTheStash), "YouBrokeTheStash"), "YouBrokeTheStash", "YouBrokeTheStash", "contains");
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objYouBrokeTheStashDescription), "You Broke The Stash Description"), propertyFileReader("YouBrokeTheStashDescription","stash"), "YouBrokeTheStashDescription", "contains");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objDoneButton), " Done Button On popup");
        }
    }
    /**
     * Method to verify add to stash screen for group and solo
     * @throws Exception
     */
    public void addToStashScreenForGroupSolo() throws Exception {
        swipe("UP",3);
        if (verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastAddToStashButton), "Add To stash on the image")) {
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objAddToStashHeader), "AddToStashHeader"), "AddToStashHeader", "AddToStashHeader", "contains");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashBalance), getTextVal(StashPage.getByOSType(platform,StashPage.objStashBalance), ":Stash Balance"));
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashPhpText), "Stash Php text");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objNextButton), "Next Button");
        }
    }
    /**
     * Method to verify review withdrawl screen
     * @throws Exception
     */
    public void reviewWithdrawalScreen() throws Exception {
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objReviewWithdrawal), "Review withdrawal");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objWithdrawAmountText), getTextVal(StashPage.getByOSType(platform,StashPage.objWithdrawAmountText), " :Target Amount"));
        String amount = getText(StashPage.getByOSType(platform,StashPage.objWithdrawAmountText));
        amount = amount.substring(amount.indexOf("₱") + 1, amount.indexOf("."));
        double amountValue = convertStringIntoDouble(amount);
        assertionValidationForDoubleType(stashedAmountValue, amountValue, "Withdrawal amount");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objFromValue), getTextVal(StashPage.getByOSType(platform,StashPage.objFromValue), " :From Account"));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objToTonikAccount), getTextVal(StashPage.getByOSType(platform,StashPage.objToTonikAccount), " :To Account"));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objConfirmButton), "Confirm Button");
    }
    /**
     * Common method to validate transaction details
     * @throws Exception
     */
    public void transactionDetailsValidation(String amount,String to,String from) throws Exception {
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionDetails),3,"Transaction details");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionDetails),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionDetails),": title"));
        containsValidation(getText(StashPage.getByOSType(platform,StashPage.objAmount)).replaceAll(",",""),"₱"+amount,"Amount");
        System.out.println(to);
        String sand= getText(StashPage.getByOSType(platform,StashPage.objToAccount));
        System.out.println(sand);
        containsValidation(getText(StashPage.getByOSType(platform,StashPage.objToAccount)),to,": To");
        containsValidation(getText(StashPage.getByOSType(platform,StashPage.objFromValue)), from, ": From");
        String date = getCurrentDate().substring(0,6);
        containsValidation(getText(StashPage.getByOSType(platform,StashPage.objWhen)), date, ": When");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objRefNumber), getTextVal(StashPage.getByOSType(platform,StashPage.objRefNumber),": Reference No"));
    }
    /**
     * Method to verify content of group stash
     * @throws Exception
     */
    public void verifyContentOfGroupStash() throws Exception {
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objLastTargetAmount), "Achieved Target Amount");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objLastAddToStashButton), "Add to stash");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objLastManageButton), "Manage");
    }
    /**
     * Method to verify you broke the stash screen
     * @throws Exception
     */
    public void youBrokeTheStashScreen() throws Exception {
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objYouUpdatedYourStashTitle), "You broke the Stash! : Header");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashSuccessDesc), "just kidding, you've closed it. : description");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashDoneButton), "Stash Done Button");
    }
    /**
     * Method to verify your account screen
     * @throws Exception
     */
    public void verifyYourAccountScreen() throws Exception {
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objStepsToVerifyYourAccount), "Steps to verify you account"), propertyFileReader("StepsToVerifyYourAccount","stash"),"StepsToVerifyYourAccount", "contains");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objTakeAFaceIdentityScan), "Take A Face Identity Scan"), propertyFileReader("TakeAFaceIdentityScan","stash"),"TakeAFaceIdentityScan", "contains");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objScanOneValidIDYouOwn), "Scan one Valid ID You Own"), propertyFileReader("ScanOneValidIDYouOwn","stash"), "ScanOneValidIDYouOwn", "contains");
    }
    /**
     * Method to use navigate to set up stash page
     * @param stashName
     * @param stashType
     * @throws Exception
     */
    public void navigateToSetupStashPage(String stashName, String stashType) throws Exception {
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStartANewStash), getText(StashPage.getByOSType(platform,StashPage.objStartANewStash)) + " : Button");
        waitTime(2000);
        swipe("up",1);
        verifyElementPresentAndClick(StashPage.objAllStashOption(platform,stashName), getText(StashPage.objAllStashOption(platform,stashName)) + " Stash Option");
        verifySelectStashPopupAndSelectTypeOfStash(stashType);
    }
    /**
     * Method to verify review stash details
     * @param targetAmount
     * @param initialSavingAmount
     * @param stashName
     * @throws Exception
     */
    public void verifyReviewStashDetails(String targetAmount, String initialSavingAmount, String stashName) throws Exception {
        waitTime(2000);
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objReviewStashDetails)), propertyFileReader("ReviewStashDetails", "Stash"), " Header");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objStashNameOnTheImage)), stashName, "Stash Name On the Image");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objTargetAmountHeader)), propertyFileReader("TargetAmount", "Stash"), " Text");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objTargetAmountOnReviewStash)).replaceAll(",", ""), "₱" + targetAmount + ".00", ":Target Amount on review Stash");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objInitialSavingHeader)), propertyFileReader("InitialSaving", "Stash"), " Text");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objInitialSavingOnReviewStash)).replaceAll(",", ""), "₱" + initialSavingAmount + ".00", ":Initial Saving on review Stash");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objTransferFromHeader)), propertyFileReader("TransferFrom", "Stash"), " Text");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTransferFromOnReviewStash), getTextVal(StashPage.getByOSType(platform,StashPage.objTransferFromOnReviewStash), ":Transfer From on review Stash"));
        containsValidation(getText(StashPage.getByOSType(platform,StashPage.objInterestRateHeader)), propertyFileReader("InterestRate", "Stash"), " Text");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objInterestRateOnReviewStash), getTextVal(StashPage.getByOSType(platform,StashPage.objInterestRateOnReviewStash), ":Interest Rate on review Stash"));
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objInterestRateOnReviewStash)), propertyFileReader("InterestRateValue", "Stash"), " Text");
        containsValidation(getText(StashPage.getByOSType(platform,StashPage.objStashPDICText)), propertyFileReader("StashPDICText", "Stash"), " Description");
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTermsAndConditionCheck), "Terms and Condition Check Box");
    }
    /**
     * Method to use create stash
     * @param targetAmount
     * @param initialAmount
     * @param stashName
     * @throws Exception
     */
    public void createStash(int targetAmount, int initialAmount, String stashName) throws Exception {
        for (int stash = 0; stash < 2; stash++) {
            click(StashPage.getByOSType(platform,StashPage.objStashForEditText), "Stash for Edit text field");
            clearField(StashPage.getByOSType(platform,StashPage.objStashForEditText), "Stash for Edit text field");
            type(StashPage.getByOSType(platform,StashPage.objStashForEditText), propertyFileReader("StashName" + stash, "Stash"), "text_field");
            click(ProfilePage.getByOSType(platform,ProfilePage.objProfilePicture), "Profile Picture");
            assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objStashNameErrorMessage)), propertyFileReader("StashNameErrorMessage" + stash, "stash"), " : inline Error message");
        }
        clearField(StashPage.getByOSType(platform,StashPage.objStashForEditText), "Stash for Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objStashForEditText), stashName, "text_field");
        clearField(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText), "Target Amount TextField");
        type(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText), RandomIntegerGenerator(3), "text_field");
        click(StashPage.getByOSType(platform,StashPage.objNextButton), getText(StashPage.getByOSType(platform,StashPage.objNextButton)) + " : Button");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objMinimumAmountCanBe)), "Min amount can be 1000 Peso.", " Min Target Amount Error Message");
        clearField(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText), "Target Amount TextField");
        type(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText), String.valueOf(targetAmount), "text_field");
        click(StashPage.getByOSType(platform,StashPage.objNextButton), getText(StashPage.getByOSType(platform,StashPage.objNextButton)) + " : Button");
        setInitialSavingScreen();
        if (initialAmount == 0) {
            click(StashPage.getByOSType(platform,StashPage.objSkipForNow), getText(StashPage.getByOSType(platform,StashPage.objSkipForNow)) + " :Button");
        } else {
            type(StashPage.getByOSType(platform,StashPage.objInitialSavingAmountInputTextField), String.valueOf(initialAmount), "Initial Amount Textfield");
            verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNextButton), getText(StashPage.getByOSType(platform,StashPage.objNextButton)) + " : Button");
        }
        verifyReviewStashDetails(String.valueOf(targetAmount), String.valueOf(initialAmount), stashName);
        verticalSwipeTillElementDisplayed(StashPage.getByOSType(platform,StashPage.objCreateStashButtonEnabled));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objCreateStashButtonEnabled), getText(StashPage.getByOSType(platform,StashPage.objCreateStashButtonEnabled)) + " :Button");
    }
    /**
     * Method to verify select stash popup and select type of stash
     * @param stashType
     * @throws Exception
     */
    public void verifySelectStashPopupAndSelectTypeOfStash(String stashType) throws Exception {
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSelectStashTypePopup), "Select Stash type Popup");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objSelectStashTypePopup), "SelectStashTypePopup"), "SelectStashTypePopup", "Stash Type popup", "contains");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objThisCantBeChangeLater), "ThisCantBeChangeLater"), "ThisCantBeChangeLater", "This can't be changed later", "contains");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objGroupStashOnPopup), "GroupStash"), "GroupStash", "Group Stash", "compare");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objTeamworkMakesThisStashWorkOnPopup), "TeamWorkMakesThisStashWork"), "TeamWorkMakesThisStashWork", "TeamWorkMakesThisStashWork", "contains");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objSoloStashOnPopup), "SoloStash"), "SoloStash", "SoloStash", "compare");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objKeepItPersonalAndSaveInThisOnPopup), "KeepItPersonalAndSaveInThis"), "KeepItPersonalAndSaveInThis", "KeepItPersonalAndSaveInThis", "contains");
        verifyElementPresentAndClick(StashPage.objTextButton(getPlatform(),stashType), getText(StashPage.objTextButton(getPlatform(),stashType)) + " Stash Type");
    }
    /**
     * Method to verify solo/group stash screen after full amount added
     * @throws Exception
     */
    public void soloGroupStashScreenAfterFullAmountAdded() throws Exception {
        if (verifyElementPresent(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image")) {
            if(verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objGroupIconAfterGoalAchieved))) {
                verifyElementPresent(StashPage.getByOSType(platform,StashPage.objGroupIconAfterGoalAchieved), "Group Stash icon");
            }
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objGoalAchievedTxt), "Congrats! Goal achieved!");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objHorizontalBar), "thin horizontal bar");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objLastTargetAmount), "Achieved");
            achievedAmount=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objLastTargetAmount)).split(" ")[1]);
            assertionValidationForDoubleType(achievedAmount,currentAmountValue,"Achieved amount");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objLastAddToStashButton), "Add to stash");
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objLastManageButton), "Manage");
        }
    }
    /**
     * Method to verify stash tile content after full amount added
     * @throws Exception
     */
    public void stashTileContentAfterFullAmountAdded() throws Exception {
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader), "Total Stash balance On header");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objHeartSymbolOnTotalStashBalance), "Heart Symbol on Total Stash Balance");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceOnDashBoard), getTextVal(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceOnDashBoard), ":Stash Balance"));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceDesc), getTextVal(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceDesc), " :Description"));
    }
    /**
     * Method to verify group stash goal achieved screen
     * @throws Exception
     */
    public void groupStashGoalAchievedScreen() throws Exception {
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objGoalAchievedHeader), "Congrats! Goal achieved!");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTargetAmountText), "Target Amount");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTargetAmountValue), "Value Of Target Amount");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objTargetAmountValue)), groupTargetAmount, "Target Amount");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashBalanceText), "Stash Balance");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashBalanceValue), "Value Of Stash Balance");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objStashBalanceValue)), groupStashAmount, "Stash Balance");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStartingDateText), "Starting Date");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStartingDateValue), "Value Of Starting Date");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objInterestRateText), "Interest Rate (p.a.)");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objGoalAchievedInterestRateValue), "Value Of Interest Rate (p.a.)");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objGoalAchievedInterestRateValue)), propertyFileReader("GoalAchievedInterestValue", "stash"), "Interest Rate (p.a.)");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objWithholdinTaxText), "Withholding Tax");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objWithholdinTaxValue), "Value Of Withholding Tax");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objWithholdinTaxValue)), propertyFileReader("WithHoldingTax", "stash"), "Withholding Tax");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objCloseStashDesc), "You will continue to earn this interest rate until you make a full withdrawal");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objGoalAchievedNextText), "What's your next step?");
    }
    /**
     * Method to verify goal achieved next step pop up
     * @throws Exception
     */
    public void goalAchievedNextStepPopup() throws Exception {
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objNextStepHeader), "What's your next step?");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objConvertToTimeDepositText), "Convert to Time Deposit");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSetAHigherGoalText), "Set a higher goal");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objBackToStashText), "Back to Stash");
    }
    /**
     * Method to convert stash to TD popup
     * @throws Exception
     */
    public void convertStashToTDPopup() throws Exception {
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objConvertStashToTDText), "Convert This Stash to Time Deposit");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objCloseStashTDText), "Close Stash");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objLeaveItAsText), "Leave it as is");
    }
    /**
     * Method to verify you broke the stash screen for goal achieved
     * @throws Exception
     */
    public void youBrokeTheStashScreenForGoalAchieved() throws Exception {
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objYouUpdatedYourStashTitle), "You broke the Stash! : Header");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashSuccessDesc), "just kidding, you've closed it. : description");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStartATDText), "Start A Time Deposit");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objCloseStashLaterText), "Close Stash Later");
    }
    /**
     * Method to verify manage savers screen
     * @throws Exception
     */
    public void ManageSaversScreen() throws Exception {
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSaversHeader), ":Savers Header");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objSaversHeader), "Savers"), "Savers", "Savers", "contains");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objInviteMoreSavers), "InviteMoreSavers"), "InviteMoreSavers", "InviteMoreSavers", "contains");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objAccountHoldersName), getTextVal(StashPage.getByOSType(platform,StashPage.objAccountHoldersName), ":Account Holders Name"));
        accountHolderName = getText(StashPage.getByOSType(platform,StashPage.objAccountHoldersName));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objContributeAmount), getTextVal(StashPage.getByOSType(platform,StashPage.objContributeAmount), ":Contribute Amount"));
        String amount = getText(StashPage.getByOSType(platform,StashPage.objContributeAmount));
        contributedAmount = amount.replace("₱", "");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashUserText), getTextVal(StashPage.getByOSType(platform,StashPage.objStashUserText), ":Stash user text"));
    }
    /**
     * Method to cuse convert to double
     * @param values
     * @return
     * @throws Exception
     */
    public double getAvailableBalanceInteger(String values) throws Exception {
        String trim = values.replace("₱", "");
        if (trim.contains(",")) {
            trim = trim.replace(",", "");
        }
        return Double.parseDouble(trim);
    }
    /**
     * Method to verify group or solo stash created success message
     * @throws Exception
     */
    public void verifyGroupSoloStashCreatedSuccessMessage() throws Exception {
        if(verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objSoloStashCreatedLuv))) {
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSoloStashCreatedLuv),getText(StashPage.getByOSType(platform,StashPage.objSoloStashCreatedLuv)));
            assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objSoloStashCreatedLuv)),"Solo Stash created, luv!"," Success Header");
            assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objStashSuccessDescription)),"“You add many more money, you stash regularly.” - Chinese Proverb (maybe?)"," Success Description");
            verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objDoneButton),getText(StashPage.getByOSType(platform,StashPage.objDoneButton))+" Button");
        }else{
            verifyElementPresent(StashPage.getByOSType(platform, StashPage.objGroupStashReadyText), getText(StashPage.getByOSType(platform, StashPage.objGroupStashReadyText)));
            assertionValidation(getText(StashPage.getByOSType(platform, StashPage.objGroupStashReadyText)), "Group Stash is ready!", " Success Header");
            assertionValidation(getText(StashPage.getByOSType(platform, StashPage.objGroupStashReadyPageDesc)), "Time to invite friends with Tonik accounts to join. If your friends don’t have Tonik, send them an invite. They can join your Group Stash when they download and create a Tonik account. Magic!", " Success Description");
            verifyElementPresent(StashPage.getByOSType(platform, StashPage.objInvitePeopleInStash), getText(StashPage.getByOSType(platform, StashPage.objInvitePeopleInStash)) + " Button");
            verifyElementPresent(StashPage.objTextButton(platform, "Close"), getText(StashPage.objTextButton(platform, "Close")) + " Button");
            verifyElementPresentAndClick(StashPage.objTextButton(platform,"Close"),getText(StashPage.objTextButton(platform,"Close"))+" Button");
        }
    }
    /**
     * Method to verify newly created group stash
     * @param stashName
     * @param TargetAmount
     * @throws Exception
     */
    public void verifyNewlyCreatedGroupStash(String stashName, String TargetAmount) throws Exception {
        swipe("UP", 4);
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objLastStashName)),stashName, "Stash Name on the image");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objLastTargetAmount)).replaceAll(",",""), "Achieved ₱0.00 of ₱"+TargetAmount+".00"," : Achieved text");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objLastAddToStashButton), "Add to stash");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objLastManageButton), "Manage");
    }

    /**
     * Method to create stash and verify the stash tile
     * @param targetAmount
     * @param initialAmount
     * @param stashName
     * @param stashType
     * @throws Exception
     */
    public void createStashAndVerifyTheStashTileAdHistory(int targetAmount,int initialAmount, String stashName,String stashType) throws Exception {
        tonikLogin(propertyFileReader("password", "Login"));
        String tonikBalance = getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard));
        swipe("UP",2);
        if (verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader))) {
            click(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader),"Stash card");
        }else{
            click(StashPage.getByOSType(platform,StashPage.objPutYourCashInAStash),"Stash card");
        }
        navigateToSetupStashPage(stashName,stashType);
        createStash(targetAmount,initialAmount,stashName);
        verifyGroupSoloStashCreatedSuccessMessage();
        verifyNewlyCreatedGroupStash(stashName,String.valueOf(targetAmount));
        addToStashScreenForGroupSolo();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objBackArrowButton),"Back Arrow Button");
        manageStashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objBackArrowButton),"Back Arrow Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objBackArrowButton),3,"Back Button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objBackArrowButton),"Back Arrow Button");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)),tonikBalance," TonikBalance Amount");
        stashTileContent();
        swipe("DOWN",1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objHistoryIcon),"History Icon");
        if(verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.ojHistoryScreen))){
            extentLoggerPass("pass","User is redirected to history screen");
        }
        else{
            extentLoggerFail("Fail","Use is not redirected history screen");
        }
    }
    /**
     * Method to verify solo stash created success message
     * @throws Exception
     */
    public void verifySoloStashCreatedSuccessMessage() throws Exception {
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSoloStashCreatedLuv),getText(StashPage.getByOSType(platform,StashPage.objSoloStashCreatedLuv)));
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objSoloStashCreatedLuv)),"Solo Stash created, luv!"," Success Header");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objStashSuccessDescription)),"“You add many more money, you stash regularly.” - Chinese Proverb (maybe?)"," Success Description");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objDoneButton),getText(StashPage.getByOSType(platform,StashPage.objDoneButton))+" Button");
    }
    /**
     * Method to verify invite to your stash screen
     * @throws Exception
     */
    public void inviteToYourStashScreen() throws Exception {
        waitForElementDisplayed(StashPage.getByOSType(platform,StashPage.objSearchInput), 10);
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objInviteToYourStashText), getTextVal(StashPage.getByOSType(platform,StashPage.objInviteToYourStashText), "Invite to your stash"));
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objInviteToYourStashText)), propertyFileReader("InviteToYourStashText", "stash"), ":Header");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSearchInput), getTextVal(StashPage.getByOSType(platform,StashPage.objSearchInput), "Input"));
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objInviteFriendText)), propertyFileReader("SendInviteViaSMSText", "stash"), "Text");
    }
    /**
     * Method to verify stash invitation screen
     * @throws Exception
     */
    public void stashInvitationScreen() throws Exception {
        waitForElementDisplayed(StashPage.getByOSType(platform,StashPage.objStashTargetAmountText), 10);
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashInvitationHeader), getTextVal(StashPage.getByOSType(platform,StashPage.objStashInvitationHeader), ":Header"));
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objStashInvitationHeader)), propertyFileReader("StashInvitationText", "stash"), ":Header");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashName), getTextVal(StashPage.getByOSType(platform,StashPage.objStashName), "Stash Name "));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashOwnerName), getTextVal(StashPage.getByOSType(platform,StashPage.objStashOwnerName), "Stash Owner name"));
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objStashTargetAmountText)), propertyFileReader("TargetAmount", "stash"), ":Text");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashTargetAmountValue), getTextVal(StashPage.getByOSType(platform,StashPage.objStashTargetAmountValue), "Stash Target amount"));
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objStashTargetDateText)), propertyFileReader("TargetDateText", "stash"), ":Text");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objStashTargetDateValue)), propertyFileReader("NotApplicaleText", "stash"), ":Text");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objOthersStasherText)), propertyFileReader("OtherStashersText", "stash"), ":Text");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objNumberOfSavers), getTextVal(StashPage.getByOSType(platform,StashPage.objNumberOfSavers), "Number of savers"));
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objAcceptInvitationText)), propertyFileReader("AcceptInvitationText", "stash"), ":Text");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objDeclineInvitationText)), propertyFileReader("DeclineText", "stash"), ":Text");
    }
    /**
     * Metod to verify selected member screen
     * @throws Exception
     */
    public void selectedMemberScreen() throws Exception {
        waitForElementDisplayed(StashPage.getByOSType(platform,StashPage.objTotalContributionText), 10);
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objHeader)), ownerName, "Header");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objTotalContributionText)), propertyFileReader("TotalContributionText", "stash"), "Text");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashSavedAmount), getTextVal(StashPage.getByOSType(platform,StashPage.objStashSavedAmount), "Stash Saved Amount"));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objNoContributionText), getTextVal(StashPage.getByOSType(platform,StashPage.objNoContributionText), "No Contribution text"));
        valueValidation(getText(StashPage.getByOSType(platform,StashPage.objNoContributionText)), propertyFileReader("NoContributionText", "stash"), "Text", "contains");
    }
    /**
     * Method to verify selected member contribution screen
     * @throws Exception
     */
    public void selectedMemberContributionScreen() throws Exception {
        String memberName = getText(StashPage.getByOSType(platform,StashPage.objOwnerName)).toUpperCase();
        String stashAmount = getText(StashPage.getByOSType(platform,StashPage.objStashSavedAmountText));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSelectMember), "Member");
        waitForElementDisplayed(StashPage.getByOSType(platform,StashPage.objTotalContributionText), 10);
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objHeader)), memberName, "Header");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objTotalContributionText)), propertyFileReader("TotalContributionText", "stash"), "Text");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objStashSavedAmount)), stashAmount, "Stash Amount");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objDepositText)), propertyFileReader("DepositText", "stash"), "Text");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objCreditText)), propertyFileReader("CreditText", "stash"), "Text");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objTransactionAmount)), stashAmount, "Transaction Amount");
    }
    /**
     * Method to verify confirm transfer to stash screen for contribution
     * @throws Exception
     */
    public void confirmTransferToStashScreenForContribution() throws Exception {
        waitForElementDisplayed(StashPage.getByOSType(platform,StashPage.objAmountText), 10);
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objConfirmTransferToStash), getTextVal(StashPage.getByOSType(platform,StashPage.objConfirmTransferToStash), ":Header"));
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objConfirmTransferToStash)), propertyFileReader("ConfirmTransferToStash", "stash"), ":Header");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objAmountText)), propertyFileReader("AmountText", "stash"), "Text");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objAmountValue), getTextVal(StashPage.getByOSType(platform,StashPage.objAmount), ":Amount to transfer"));
        String amount = getText(StashPage.getByOSType(platform,StashPage.objAmountValue)).replace("₱", "").replace(",", "");
        double amountValue = convertStringIntoDouble(amount);
        assertionValidationForDoubleType(amountValue, stashedAmountValue, "Amount to transfer");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objFromText)), propertyFileReader("FromText", "stash"), "Text");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objMyTonikAccountText)), propertyFileReader("MyTonikAccountText", "stash"), "Text");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objToText)), propertyFileReader("ToText", "stash"), "Text");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objToValue)), stashName, "Text");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objStashOwnerText)), propertyFileReader("StashOwnerText", "stash"), "Text");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objStashOwnerName)), stashOwnerName, "Stash owner name");
    }
    /**
     * Method to verify Money stashed screen
     * @throws Exception
     */
    public void moneyStashedScreen() throws Exception {
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objMoneyStashedText), getTextVal(StashPage.getByOSType(platform,StashPage.objMoneyStashedText), ":Title"));
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objMoneyStashedText)), propertyFileReader("MoneyStashedText", "stash"), ":Title");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objMoneyStashedDesc), getTextVal(StashPage.getByOSType(platform,StashPage.objMoneyStashedDesc), ":Title"));
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objViewStashDetailsText)), propertyFileReader("ViewStashDetailsText", "stash"), ":Descriptions");
    }
    /**
     * Method to verify transaction details screen for contribution
     * @throws Exception
     */
    public void transactionDetailsScreenForContribution() throws Exception {
        waitForElementDisplayed(StashPage.getByOSType(platform,StashPage.objAmountText), 10);
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objHeader), getTextVal(StashPage.getByOSType(platform,StashPage.objHeader), ":Header"));
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objHeader)), propertyFileReader("TransactionDetails", "stash"), ":Header");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objAmountText)), propertyFileReader("AmountText", "stash"), "Text");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objAmountValue), getTextVal(StashPage.getByOSType(platform,StashPage.objAmount), ":Amount to transfer"));
        String amount = getText(StashPage.getByOSType(platform,StashPage.objAmountValue)).replace("₱", "").replace(",", "");
        double amountValue = convertStringIntoDouble(amount);
        assertionValidationForDoubleType(amountValue, stashedAmountValue, "Amount to transfer");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objToText)), propertyFileReader("ToText", "stash"), "Text");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objToValue)), stashName, "Text");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objFromText)), propertyFileReader("FromText", "stash"), "Text");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashFromValue), getTextVal(StashPage.getByOSType(platform,StashPage.objStashFromValue), "Text"));
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objStashWhenText)), propertyFileReader("WhenText", "stash"), "Text");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashTxnDate), getTextVal(StashPage.getByOSType(platform,StashPage.objStashTxnDate), "Transaction Date"));
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objStashRefText)), propertyFileReader("RefNumberText", "stash"), "Text");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashRefValue), getTextVal(StashPage.getByOSType(platform,StashPage.objStashRefValue), "Reference Number"));
    }
    /**
     * Method to verify fund contributed member screen
     * @throws Exception
     */
    public void fundContributedMemberScreen() throws Exception {
        String memberName = getText(StashPage.getByOSType(platform,StashPage.objContributedMemberName)).toUpperCase();
        String stashAmount = getText(StashPage.getByOSType(platform,StashPage.objContributedMemberFund));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objFirstMember), "First Member");
        waitForElementDisplayed(StashPage.getByOSType(platform,StashPage.objTotalContributionText), 10);
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objHeader)), memberName, "Header");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objTotalContributionText)), propertyFileReader("TotalContributionText", "stash"), "Text");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objStashSavedAmount)), stashAmount, "Stash Amount");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashTransactionDate), "Tansaction Date");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objDepositText)), propertyFileReader("DepositText", "stash"), "Text");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objCreditText)), propertyFileReader("CreditText", "stash"), "Text");
        String amount = getText(StashPage.getByOSType(platform,StashPage.objTransactionAmount)).replace("₱", "").replace(",", "");
        double amountValue = convertStringIntoDouble(amount);
        assertionValidationForDoubleType(amountValue, stashedAmountValue, "Amount to transfer");
    }
    /**
     * Method to verify account history screen
     * @throws Exception
     */
    public void accountHistoryScreen() throws Exception {
        waitForElementDisplayed(StashPage.getByOSType(platform,StashPage.objAccountNumber), 10);
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objHeader), getTextVal(StashPage.getByOSType(platform,StashPage.objHeader), ":Header"));
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objHeader)), propertyFileReader("AccountHistoryText", "stash"), ":Header");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objAccountNumber), getTextVal(StashPage.getByOSType(platform,StashPage.objAccountNumber), "Account Number"));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTSAAmount), getTextVal(StashPage.getByOSType(platform,StashPage.objTSAAmount), "TSA Amount"));
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objTopUpText)), propertyFileReader("TopUpText", "stash"), "Text");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objManageText)), propertyFileReader("ManageText", "stash"), "Text");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objStashNameOnAccountHistory)), stashName, "Text");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objMoneyContributedText)), propertyFileReader("MoneyContributedText", "stash"), "Text");
    }
    /**
     * Method to verify leave stash pop up screen
     * @throws Exception
     */
    public void leaveStashPopupScreen() throws Exception {
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objPopupHeader)), propertyFileReader("LeaveStashDesc", "stash"), "Descriptions");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objStayText)), propertyFileReader("StayText", "stash"), "Text");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objCloseStashButton)), propertyFileReader("LeaveTheStashText", "stash"), "Text");
    }

    /**
     * Method to verify login and navigate to set up your stash Screen
     * @throws Exception
     */
    public void loginAndNavigateToSetupYourStashScreen() throws Exception{
        tonikLogin(propertyFileReader("password", "Login"));
        swipe("UP", 2);
        if (verifyElementPresent(StashPage.getByOSType(platform, StashPage.objPutYourCashInAStash), "Put Your Cash In Stash")) {
            valueValidation(getTextVal(StashPage.getByOSType(platform, StashPage.objPutYourCashInAStash), "PutYourCashInaStash"), "PutYourCashInaStash", "PutYourCashInaStash", "contains");
            valueValidation(getTextVal(StashPage.getByOSType(platform, StashPage.objStashes), "StashesOption"), "StashesOption", "StashesOption", "contains");
            valueValidation(getTextVal(StashPage.getByOSType(platform, StashPage.objSaveAndGrowYourMoney), "SaveAndGrowYourMoney"), "SaveAndGrowYourMoney", "SaveAndGrowYourMoney", "contains");
        }
        click(StashPage.getByOSType(platform, StashPage.objStashes), "StashesOption");
        stashWelcomeScreens();
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashHeader), "Stash Header");
        containsValidation(getText(StashPage.getByOSType(platform,StashPage.objStashHeader)), propertyFileReader("StashHeader", "Stash"), ": Header :Stash");
        containsValidation(getText(StashPage.getByOSType(platform,StashPage.objStartANewStash)), propertyFileReader("StartANewStash", "Stash"), ": StartANewStash");
        containsValidation(getText(StashPage.getByOSType(platform,StashPage.obj5Of5AvailableStashes)), propertyFileReader("5Of5AvailableStashes", "Stash"), ": 5Of5AvailableStashes");
        newStashScreen();
        swipe("UP", 2);
        openANewStashScreen();
        setUpYourStashScreen();
    }
    /**
     * Method to verify user can access the stash module when TSA is not created
     * @throws Exception
     */
    public void verifyIfUserCanAccessTheStashModuleWhenTSAIsNotYetCreated_TDB_ST_001() throws Exception {
        HeaderChildNode("TDB_ST_001, Stash -verify If User Can Access The Stash Module When TSA Is Not Yet Created_TDB_ST_001");
        loginAndNavigateToSetupYourStashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashForEditText), "Stash for Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objStashForEditText), propertyFileReader("validInput", "Stash"), "text_field");
        click(ProfilePage.getByOSType(platform,ProfilePage.objProfilePicture), "Profile Picture");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText), "Target Amount Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText), propertyFileReader("MoreThanTarget", "Stash"), "More Than Target Amount ");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSetUpYourStashNextButton), "Next button");
        click(StashPage.getByOSType(platform,StashPage.objSkipForNow), "Profile Picture");
        reviewStashDetailsScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTermsAndConditionCheck), "Terms and condition Check box");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objCreateStashButtonEnabled), "Create Stash");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSoloStashCreatedLuv), "Solo Stash created Luv");
        containsValidation(getText(StashPage.getByOSType(platform,StashPage.objStashSuccessDescription)), propertyFileReader("StashSuccessDescription", "Stash"), ":Stash success Description");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTargetAmountHeader), "TargetAmount");
        swipe("DOWN", 2);
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objSetUpYourStash), 10, "Set up your stash header");
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objStashHeader), 10, " stash header");
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementToBePresent(ProfilePage.getByOSType(platform,ProfilePage.objProfileIcon), 10, "Profile Icon");
        if (verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objDisabledAmount))) {
            logger.info("TDB_ST_001, Stash - TSA not Yet Created");
            extentLoggerPass("TDB_ST_001", "TDB_ST_001, Stash -TSA not Yet Created is passed");
        }
    }
    /**
     * Method to verify user can create TSA from stash
     * @throws Exception
     */
    public void verifyIfUserCanCreateTheTSAFromStash_TDB_ST_002() throws Exception {
        HeaderChildNode("TDB_ST_002, Stash -verify If User Can Create The TSA From Stash_TDB_ST_002");
        loginAndNavigateToSetupYourStashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashForEditText), "Stash for Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objStashForEditText), propertyFileReader("validInput", "Stash"), "text_field");
        click(ProfilePage.getByOSType(platform,ProfilePage.objProfilePicture), "Profile Picture");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText), "Target Amount Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText), propertyFileReader("MoreThanTarget", "Stash"), "MoreThan Target Amount ");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSetUpYourStashNextButton), "Next button");
        setInitialSavingScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objInitialSavingAmountInputTextField), "initial saving input text field");
        type(StashPage.getByOSType(platform,StashPage.objInitialSavingAmountInputTextField), propertyFileReader("initialSaving", "Stash"), "initialSaving");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNextButton), "Next button");
        verifyReviewStashDetails(propertyFileReader("MoreThanTarget","Stash"),propertyFileReader("initialSaving","Stash"),propertyFileReader("validInput","Stash"));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objCreateStashButtonEnabled), "Create Stash");

        if (verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objCommunicationIsKeyPopup))) {
            click(StashPage.getByOSType(platform,StashPage.objDoItLater), "I'll do it later, Pinky swear");
        }
        if (verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSoloStashCreatedLuv), "Solo Stash created Luv")) {
            click(StashPage.getByOSType(platform,StashPage.objDoneButton), "Done Button");
        }
        if (verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objWeAreAlmostReady))) {
            logger.info("We are Almost Ready Screen is Displayed");
            extentLoggerPass("Screen", "We are Almost Ready Screen is Displayed");
        }
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementToBePresent(ProfilePage.getByOSType(platform,ProfilePage.objProfileIcon), 10, "Profile Icon");
        if (verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objEnabledAmount))) {
            logger.info("TDB_ST_002, Stash - TSA is Created");
            extentLoggerPass("TDB_ST_002", "TDB_ST_002, Stash -TSA is Created is passed");
        }
    }
    /**
     * Method to verify user can create solo stash
     * @throws Exception
     */
    public void verifyIfUserCanCreateSoloStash_TDB_ST_003() throws Exception {
        HeaderChildNode("TDB_ST_003, Stash -verify If User Can Create Solo Stash_TDB_ST_003");
        tonikLogin(propertyFileReader("password", "Login"));
        stashScreen();
        newStashScreen();
        openANewStashScreen();
        setUpYourStashScreen();
        setUpOrModifyYourStashScreenValidations();
        setInitialSavingScreen();
        click(StashPage.getByOSType(platform,StashPage.objSkipForNow), "Skip For Now");
        reviewStashDetailsScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTermsAndConditionCheck), "Terms and condition Check box");
        stashSuccessScreen();
        newlyCreatedSoloGroupStash();
        addToStashScreenForGroupSolo();
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementToBePresent(StashPage.objManage(platform,"0"), 10, "Manage on the image");
        manageStashScreen();
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.obj5Of5AvailableStashes), getTextVal(StashPage.getByOSType(platform,StashPage.obj5Of5AvailableStashes), ":Available Stash"));
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        verticalSwipeTillElementDisplayed(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader));
        stashTileContent();
        swipe("DOWN", 2);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objHistoryIcon), "HistoryIcon");
        logger.info("TBD_PRO_003, Stash - verify If User Can Create Solo Stash is passed");
        extentLoggerPass("TBD_PRO_003", "TBD_PRO_003, Stash -verify If User Can Create Solo Stash_TBD_PST_003 is passed");
    }
    /**
     * Method to verify user can set initial saving while creating the stash
     * @throws Exception
     */
    public void verifyIfUserCanSetInitialSavingWhileCreatingTheStash_TDB_ST_004() throws Exception {
        HeaderChildNode("TDB_ST_004, Stash -verify If User Can Set Initial Saving While Creating The Stash_TDB_ST_004");
        tonikLogin(propertyFileReader("password", "Login"));
        initialStashBalanceOnTile=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceOnDashBoard)));
        stashScreen();
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objStartANewStash), 10,"Start a New Stash");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStartANewStash), "Start a New Stash");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objOpenANewStash), "Open a New Stash");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSoloStashOnPopup), "Select Solo Stash on pop up");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashForEditText), "Stash for Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objStashForEditText), propertyFileReader("validInput", "Stash"), "text_field");
        click(ProfilePage.getByOSType(platform,ProfilePage.objProfilePicture), "Profile Picture");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText), "Target Amount Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText), propertyFileReader("MoreThanTarget", "Stash"), "MoreThan Target Amount");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSetUpYourStashNextButton), "Next button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objInitialSavingAmountInputTextField), "Initial Saving Amount Input Text Field");
        type(StashPage.getByOSType(platform,StashPage.objInitialSavingAmountInputTextField), propertyFileReader("lessThanPeso", "Stash"), "Initial Saving Amount Input Text Field");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNextButton), "Next button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objMinimumAmountCanBe1Peso), "Minimum Amount Can be 1 Peso");
        click(StashPage.getByOSType(platform,StashPage.objInitialSavingAmountInputTextField), "Initial Saving Amount Input Text Field");
        clearField(StashPage.getByOSType(platform,StashPage.objInitialSavingAmountInputTextField), "Target Amount Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objInitialSavingAmountInputTextField), propertyFileReader("ValidPeso", "Stash"), "Valid Peso");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNextButton), "Next button");
        verifyReviewStashDetails(propertyFileReader("MoreThanTarget","Stash"),propertyFileReader("ValidPeso","Stash"),propertyFileReader("validInput","Stash"));
        stashSuccessScreen();
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objLastStashName), 10, "Stash Name on the image");
        newlyCreatedSoloGroupStash();
        addToStashScreenForGroupSolo();
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        manageStashScreen();
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        swipe("DOWN",2);
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.obj5Of5AvailableStashes), getTextVal(StashPage.getByOSType(platform,StashPage.obj5Of5AvailableStashes), ":Available Stash"));
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        swipe("UP", 2);
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceOnDashBoard), getTextVal(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceOnDashBoard), ":Stash Balance"));
        stashBalanceOnTile=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceOnDashBoard)));
        assertionValidationForDoubleType(achievedAmount+initialStashBalanceOnTile,stashBalanceOnTile,"Stash balance");
        swipe("DOWN", 2);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objHistoryIcon), "HistoryIcon");
        if (verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objYouHaveNoTransactionYet))) {
            logger.info("TBD_PRO_004, Stash - verify If User Can Create Solo Stash_TBD_PST_004");
            extentLoggerPass("TBD_PRO_004", "TBD_PRO_004, Stash -verify If User Can Set Initial Saving While Creating The Stash_TBD_PST_004 is passed");
        }
    }
    /**
     * Method to verify user can add funds to solo stash
     * @throws Exception
     */
    public void verifyIfUserCanAddFundsToSoloStashFromTheListDetailsScreen_TDB_ST_005() throws Exception {
        HeaderChildNode("TDB_ST_005, Stash -verify if User Can Add Funds To Solo Stash From The List Details Screen_TDB_ST_005");
        tonikLogin(propertyFileReader("password", "Login"));
        currentAmountValue=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        stashedAmountValue=this.getAvailableBalanceInteger(propertyFileReader("ValidPeso", "Stash"));
        initialStashBalanceOnTile=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceOnDashBoard)));
        stashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastAddToStashButton), "Add To stash on the image");
        addToStashValidations();
        confirmTransferToStashScreen( propertyFileReader("ValidPeso", "Stash"));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objConfirmButtonOnConfirmTransferToStash), "Confirm button");
        moneyStashedScreenValidation();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objViewDetailsOfHowWeMovedIt), "View Details How We Moved It");
        transactionDetailsScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objQuestionMarkIconOnTransactionDetails), "contact customer Icon ");
        verifyElementPresent(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader), "Get In Touch Screen");
        waitTime(3000);//hard wait required here
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitTime(3000);//Hard wait time required otherwise will get stale element exception
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objFromTransactionDetails), 10, "From On transaction details");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objDoneButton), "Done Button");
        newlyCreatedSoloGroupStash();
        swipe("DOWN",2);
        verifyElementPresentAndClick(StashPage.objStashNameOnTheImageOnStashScreen(platform,"0"), "Stash Name on the image");
        selectedSoloGroupStashScreen("0");
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitTime(3000);
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objAvailableStashText),10,"Available stashes");
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        double updatedAmount=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        assertionValidationForDoubleType(updatedAmount,currentAmountValue-stashedAmountValue,"Stash balance");
        if (currentAmountValue > updatedAmount) {
            logger.info("TSA Balance Reduced based on the amount stashed");
            extentLoggerPass("Stashed Amount", "TSA Balance Reduced based on the amount stashed");
        }
        swipe("UP", 1);
        stashTileContent();
        stashBalanceOnTile=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceOnDashBoard)));
        assertionValidationForDoubleType(achievedAmount+initialStashBalanceOnTile,stashBalanceOnTile,"Stash balance");
        swipe("DOWN", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objHistoryIcon), "HistoryIcon");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objNameOfTheStashOnHistory), getTextVal(StashPage.getByOSType(platform,StashPage.objNameOfTheStashOnHistory), ":Name Of The Stash On History"));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), getTextVal(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), ":Transaction Value On History"));
        historyTransactionDetailsScreen();
        logger.info("TDB_ST_005, Stash - verify  If User Can Manage The Solo Stash From The List Details Screen_TDB_ST_005");
        extentLoggerPass("TDB_ST_005", "TDB_ST_005, Stash -verify If User Can Manage The Solo Stash From The List DetailsScreen_TDB_ST_005 is passed");
    }
    /**
     * Method to verify user can manage the solo stash from the list details screen
     * @throws Exception
     */
    public void verifyIfUserCanManageTheSoloStashFromTheListDetailsScreen_TDB_ST_006() throws Exception {
        HeaderChildNode("TDB_ST_006, Stash -verify If User Can Manage The Solo Stash From The List Details Screen_TDB_ST_006");
        tonikLogin(propertyFileReader("password", "Login"));
        stashScreen();
        manageStashScreen();
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        verifyElementPresentAndClick(StashPage.objManage(platform,"0"), "Manage on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashDetails), "Stash details");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashDetailsHeader), "Stash details");
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objModify), 20, "Modify");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objModify), "Modify");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objModifyStashHeader), "Modify Stash");
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objWithdrawToYourTonikAccount), 20, "WithdrawToYourTonikAccount");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objWithdrawToYourTonikAccount), "WithdrawToYourTonikAccount");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objWithdrawFromYourStashHeader), "WithdrawFromYourStash");
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objClose), 20, "Close");
        if (verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objClose), "Close")) {
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objAreYouSureYouWantToCloseTheStashPopup), "Are you sure you want to close the pop up");
            logger.info("TDB_ST_006, Stash - verify  If User Can Manage The Solo Stash From The List Details Screen_TDB_ST_006");
            extentLoggerPass("TDB_ST_006", "TDB_ST_006, Stash -verify If User Can Manage The Solo Stash From The List DetailsScreen_TDB_ST_006 is passed");
        }
    }
    /**
     * Method to verify user can add funds to solo stash from summary details screen
     * @throws Exception
     */
    public void verifyIfUserCanAddFundsToSoloStashFromTheSummaryDetailsScreen_TDB_ST_007() throws Exception {
        HeaderChildNode("TDB_ST_007, Stash -verify If User Can Add Funds To Solo Stash From The Summary Details Screen_TDB_ST_007");
        tonikLogin(propertyFileReader("password", "Login"));
        currentAmountValue=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        stashedAmountValue=this.getAvailableBalanceInteger(propertyFileReader("ValidPeso", "Stash"));
        initialStashBalanceOnTile=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceOnDashBoard)));
        stashScreen();
        verifyElementPresentAndClick(StashPage.objAddToStash(platform,"0"), "Add stash on the image");
        addToStashValidations();
        confirmTransferToStashScreen( propertyFileReader("ValidPeso", "Stash"));
        click(StashPage.getByOSType(platform,StashPage.objConfirmButtonOnConfirmTransferToStash), "Confirm Button");
        moneyStashedScreenValidation();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objViewDetailsOfHowWeMovedIt), "View Details How We Moved It");
        transactionDetailsScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objQuestionMarkIconOnTransactionDetails), "contact customer Icon ");
        verifyElementPresent(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader), "Get In Touch Screen");
        waitTime(3000);
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objQuestionMarkIconOnTransactionDetails), 10, "contact customer Icon");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objDoneButton), "Done Button");
        newlyCreatedSoloGroupStash();
        verifyElementPresentAndClick(StashPage.objStashNameOnTheImageOnStashScreen(platform,"0"), "Stash Name on the image");
        selectedSoloGroupStashScreen("0");
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objDoneButton),10,"Available stash");
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        double updatedAmount=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        assertionValidationForDoubleType(updatedAmount,currentAmountValue-stashedAmountValue,"Stash balance");
        if (currentAmountValue > updatedAmount) {
            logger.info("TSA Balance Reduced based on the amount stashed");
            extentLoggerPass("Stashed Amount", "TSA Balance Reduced based on the amount stashed");
        }
        swipe("UP", 1);
        stashTileContent();
        stashBalanceOnTile=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceOnDashBoard)));
        assertionValidationForDoubleType(achievedAmount+initialStashBalanceOnTile,stashBalanceOnTile,"Stash balance");
        swipe("DOWN", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objHistoryIcon), "HistoryIcon");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objNameOfTheStashOnHistory), getTextVal(StashPage.getByOSType(platform,StashPage.objNameOfTheStashOnHistory), ":Name Of The Stash On History"));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), getTextVal(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), ":Transaction Value On History"));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), "Transaction History");
        historyTransactionDetailsScreen();
        logger.info("TDB_ST_007, Stash - verify If User Can Add Funds To Solo Stash From The Summary Details Screen_TDB_ST_007");
        extentLoggerPass("TDB_ST_007", "TBD_PRO_007, Stash -verify If User Can Add Funds To Solo Stash From The Summary Details Screen_TDB_ST_007 is passed");
    }
    /**
     * Method to verify TSA balance is debited twice if confirm click twice while adding the fund
     * @throws Exception
     */
    public void verifyIfTSABalanceIsDebitedTwiceIfConfirmButtonIsClickedTwiceWhileAddingFundInSoloStash_TDB_ST_008() throws Exception {
        HeaderChildNode("TDB_ST_008, Stash -verify If TSA Balance Is Debited Twice If Confirm Button Is Clicked Twice While Adding Fund In SoloStash_TDB_ST_008");
        tonikLogin(propertyFileReader("password", "Login"));
        currentAmountValue=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        stashedAmountValue=this.getAvailableBalanceInteger(propertyFileReader("ValidPeso", "Stash"));
        stashScreen();
        verifyElementPresentAndClick(StashPage.objStashNameOnTheImageOnStashScreen(platform,"0"), "Stash Name on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objAddToStash), "Add to stash");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), "Add to stash Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), propertyFileReader("ValidPeso", "Stash"), "valid Amount Input Text Field");
        if(platform.equalsIgnoreCase("Android")) {
            hideKeyboard();
        }else{
            click(StashPage.getByOSType(platform,StashPage.objKeyboardDoneButton),"Key board done button");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNextButton), "Next button");
        confirmTransferToStashScreen( propertyFileReader("ValidPeso", "Stash"));
        click(StashPage.getByOSType(platform,StashPage.objConfirmButtonOnConfirmTransferToStash), "Confirm Button");
        click(StashPage.getByOSType(platform,StashPage.objConfirmButtonOnConfirmTransferToStash), "Confirm Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objYouAdded), 10, "Money Stashed");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objMoneyStashed), "MoneyStashed"), "MoneyStashed", "MoneyStashed", "contains");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objYouAdded), "You Added");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objDoneButton), "Done Button");
        waitForElementToBePresent(LoginPage.getByOSType(platform,LoginPage.objBackIcon),10, "Back Button");
        waitTime(3000);//Hard wait required Here
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        updatedAmountValue =this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        assertionValidationForDoubleType(updatedAmountValue, currentAmountValue - stashedAmountValue, "TSA Balance");
        if (Objects.equals(updatedAmountValue, currentAmountValue - stashedAmountValue)) {
            logger.info("Stashed amount debited only once from TSA balance");
            extentLoggerPass("Stashed Amount", "Stashed amount debited only once from TSA balance");
        } else {
            logger.error("Stashed amount debited twice from TSA balance");
            extentLoggerFail("Stashed Amount", "Stashed amount debited twice from TSA balance");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objHistoryIcon), "HistoryIcon");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objNameOfTheStashOnHistory), getTextVal(StashPage.getByOSType(platform,StashPage.objNameOfTheStashOnHistory), ":Name Of The Stash On History"));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), getTextVal(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), ":Transaction Value On History"));
        logger.info("TDB-ST-008, Stash - verify If TSA Balance Is Debited Twice If Confirm Button Is Clicked Twice While Adding Fund In SoloStash");
        extentLoggerPass("TDB-ST-008", "TDB-ST-008, Stash -verify If TSA Balance Is Debited Twice If Confirm Button Is Clicked Twice While Adding Fund In SoloStash is passed");
    }
    /**
     * Method to verify user can add money above the target amount
     * @throws Exception
     */
    public void verifyIfUserCanAddMoneyAboveTheTargetAmount_TDB_ST_009() throws Exception {
        HeaderChildNode("TDB_ST_009, Stash -verify If User Can Add Money Above The TargetAmount_TDB_ST_009");
        tonikLogin(propertyFileReader("password", "Login"));
        currentAmountValue=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        stashedAmountValue=this.getAvailableBalanceInteger(propertyFileReader("ValidPeso", "Stash"));
        stashScreen();
        verifyElementPresentAndClick(StashPage.objStashNameOnTheImageOnStashScreen(platform,"0"), "Stash Name on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objAddToStash), "Add To stash on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), "Add to stash Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), propertyFileReader("ValidPeso", "Stash"), "valid Amount Input Text Field");
        if(platform.equalsIgnoreCase("Android")) {
            hideKeyboard();
        }else{
            click(StashPage.getByOSType(platform,StashPage.objKeyboardDoneButton),"Key board done button");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNextButton), "Next button");
        confirmTransferToStashScreen( propertyFileReader("ValidPeso", "Stash"));
        click(StashPage.getByOSType(platform,StashPage.objConfirmButtonOnConfirmTransferToStash), "Confirm Button");
        moneyStashedScreenValidation();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objDoneButton), "Done Button");
        newlyCreatedSoloGroupStash();
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        updatedAmountValue =this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        assertionValidationForDoubleType(updatedAmountValue, currentAmountValue - stashedAmountValue, "TSA Balance");
        if (Objects.equals(updatedAmountValue, currentAmountValue - stashedAmountValue)) {
            logger.info("Stashed amount debited only once from TSA balance");
            extentLoggerPass("Stashed Amount", "Stashed amount debited only once from TSA balance");
        }
        swipe("UP", 1);
        stashTileContent();
        swipe("DOWN", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objHistoryIcon), "HistoryIcon");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objNameOfTheStashOnHistory), getTextVal(StashPage.getByOSType(platform,StashPage.objNameOfTheStashOnHistory), ":Name Of The Stash On History"));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), getTextVal(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), ":Transaction Value On History"));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), "Transaction History");
        historyTransactionDetailsScreen();
        logger.info("TDB_ST_009, Stash - verify If User Can Add Money Above The TargetAmount_TDB_ST_009");
        extentLoggerPass("TBD_ST_009", "TDB_ST_009, Stash -verify If User Can Add Money Above The TargetAmount_TDB_ST_009 is passed");
    }
    /**
     * Method to verify user can manage the solo stash from the summary details screen
     * @throws Exception
     */
    public void verifyIfUserCanManageTheSoloStashFromTheSummaryDetailsScreen_TDB_ST_010() throws Exception {
        HeaderChildNode("TDB_ST_010, Stash -verify If User Can Manage The Solo Stash From The Summary Details Screen_TDB_ST_010");
        tonikLogin(propertyFileReader("password", "Login"));
        stashScreen();
        verifyElementPresentAndClick(StashPage.objStashNameOnTheImageOnStashScreen(platform,"0"), "Stash Name on the image");
        selectedSoloGroupStashScreen("0");
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastManageButton), "Manage on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashDetails), "Stash details");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashDetailsHeader), "Stash details");
        waitTime(3000);
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objModify), 20, "Modify");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objModify), "Modify");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objModifyStashHeader), "Modify Stash");
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objWithdrawToYourTonikAccount), 20, "WithdrawToYourTonikAccount");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objWithdrawToYourTonikAccount), "WithdrawToYourTonikAccount");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objWithdrawFromYourStashHeader), "WithdrawFromYourStash");
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objClose), 20, "Close");
        if (verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objClose), "Close")) {
            verifyElementPresent(StashPage.getByOSType(platform,StashPage.objAreYouSureYouWantToCloseTheStashPopup), "Are you sure you want to close the pop up");
            logger.info("TDB_ST_010, Stash - verify If User Can Manage The Solo Stash From The Summary Details Screen_TDB_ST_010");
            extentLoggerPass("TBD_ST_010", "TDB_ST_010, Stash -verify If User Can Manage The Solo Stash From The Summary Details Screen_TDB_ST_010 is passed");
        }
    }
    /**
     * Method to verify user can view the solo stash details
     * @throws Exception
     */
    public void verifyIfUserCanViewTheSoloStashDetails_TDB_ST_011() throws Exception {
        HeaderChildNode("TDB_ST_011, Stash -verify If User Can View The Solo Stash Details_TDB_ST_011");
        tonikLogin(propertyFileReader("password", "Login"));
        stashScreen();
        manageStashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashDetails), "Stash details");
        stashDetailsScreen();
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        if (verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objStashHeader))) {
            logger.info("TDB_ST_011, Stash - verify If User Can View The Solo Stash Details_TDB_ST_011");
            extentLoggerPass("TDB_ST_011", "TDB_ST_011, Stash -verify If User Can View The Solo Stash Details_TBD_ST_011 is passed");
        }
    }
    /**
     * Method to verify user can change the name and modify the picture
     * @throws Exception
     */
    public void verifyIfUserCanChangeTheNameModifyThePictureOfTheSoloStash_TDB_ST_012() throws Exception {
        HeaderChildNode("TDB_ST_012, Stash -verify If User Can Change The Name Modify The Picture Of The Solo Stash_TDB_ST_012");
        tonikLogin(propertyFileReader("password", "Login"));
        stashScreen();
        String currentStashName = getText(StashPage.objStashNameOnTheImageOnStashScreen(platform,"0"));
        manageStashScreen();
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        click(StashPage.getByOSType(platform,StashPage.objLastManageButton), "Manage on the image");
        click(StashPage.getByOSType(platform,StashPage.objModify), "Modify");
        modifyStashScreen();
        uploadProfilePicture();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNameInputTextField), "Stash for Edit text field");
        clearField(StashPage.getByOSType(platform,StashPage.objNameInputTextField), "Stash for Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objNameInputTextField), propertyFileReader("lessThan", "Stash"), "text_field");
        click(ProfilePage.getByOSType(platform,ProfilePage.objProfilePicture), "Profile Picture");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objPleaseEnterAValidStashName), "Please Enter a valid stash name inline Error Message");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNameInputTextField), "Stash for Edit text field");
        clearField(StashPage.getByOSType(platform,StashPage.objNameInputTextField), "Stash for Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objNameInputTextField), propertyFileReader("SpecialChar", "Stash"), "Special char");
        click(ProfilePage.getByOSType(platform,ProfilePage.objProfilePicture), "Profile Picture");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objEnterAValidStashName), "Please Enter a valid stash name inline Error Message");
        click(StashPage.getByOSType(platform,StashPage.objNameInputTextField), "Stash for Edit text field");
        clearField(StashPage.getByOSType(platform,StashPage.objNameInputTextField), "Stash for Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objNameInputTextField), propertyFileReader("validInput1", "Stash"), "text_field");
        click(ProfilePage.getByOSType(platform,ProfilePage.objProfilePicture), "Profile Picture");
        click(StashPage.getByOSType(platform,StashPage.objSaveButton), "Save Button");
        youUpdatesYourStashScreen();
        String updatedStashName = getText(StashPage.objStashNameOnTheImageOnStashScreen(platform,"0"));
        if (!Objects.equals(currentStashName, updatedStashName)) {
            logger.info("TDB_ST_012, Stash - verify If User Can Change The Name Modify The Picture Of The SoloStash_TDB_ST_012");
            extentLoggerPass("TDB_ST_012", "TDB_ST_012, Stash -verify If User Can Change The Name Modify The Picture Of The SoloStash_TDB_ST_012 is passed");
        }
    }
    /**
     * Method to verify the characters limits for solo stash name
     * @throws Exception
     */
    public void verifyTheCharactersLimitForSoloStashName_TDB_ST_013() throws Exception {
        HeaderChildNode("TDB_ST_013, Stash -verify The Characters Limit For Solo StashName_TDB_ST_013");
        tonikLogin(propertyFileReader("password", "Login"));
        stashScreen();
        String currentStashName = getText(StashPage.objStashNameOnTheImageOnStashScreen(platform,"0"));
        manageStashScreen();
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        click(StashPage.getByOSType(platform,StashPage.objLastManageButton), "Manage on the image");
        click(StashPage.getByOSType(platform,StashPage.objModify), "Modify");
        modifyStashScreen();
        uploadProfilePicture();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNameInputTextField), "Stash for Edit text field");
        clearField(StashPage.getByOSType(platform,StashPage.objNameInputTextField),"Stash for Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objNameInputTextField), propertyFileReader("lessThan", "Stash"), "text_field");
        click(ProfilePage.getByOSType(platform,ProfilePage.objProfilePicture), "Profile Picture");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objPleaseEnterAValidStashName), "Please Enter a valid stash name inline Error Message");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNameInputTextField), "Stash for Edit text field");
        clearField(StashPage.getByOSType(platform,StashPage.objNameInputTextField), "Stash for Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objNameInputTextField), propertyFileReader("SpecialChar", "Stash"), "Special char");
        click(ProfilePage.getByOSType(platform,ProfilePage.objProfilePicture), "Profile Picture");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objEnterAValidStashName), "Please Enter a valid stash name inline Error Message");
        click(StashPage.getByOSType(platform,StashPage.objNameInputTextField), "Stash for Edit text field");
        clearField(StashPage.getByOSType(platform,StashPage.objNameInputTextField), "Stash for Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objNameInputTextField), propertyFileReader("MoreThan", "Stash"), "MoreThan");
        click(ProfilePage.getByOSType(platform,ProfilePage.objProfilePicture), "Profile Picture");
        String updatedName = getText(StashPage.getByOSType(platform,StashPage.objNameInputTextField));
        if (!Objects.equals(updatedName, propertyFileReader("MoreThan", "Stash"))) {
            logger.info("Accepted only 35 character");
            extentLoggerPass("Minimum Char", "Accepted only 35 character");
        }
        click(StashPage.getByOSType(platform,StashPage.objNameInputTextField), "Stash for Edit text field");
        clearField(StashPage.getByOSType(platform,StashPage.objNameInputTextField), "Stash for Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objNameInputTextField), propertyFileReader("validInput", "Stash"), "text_field");
        click(ProfilePage.getByOSType(platform,ProfilePage.objProfilePicture), "Profile Picture");
        click(StashPage.getByOSType(platform,StashPage.objSaveButton), "Save Button");
        youUpdatesYourStashScreen();
        String updatedStashName = getText(StashPage.objStashNameOnTheImageOnStashScreen(platform,"0"));
        if (!Objects.equals(currentStashName, updatedStashName)) {
            logger.info("TDB_ST_013, Stash - verify The Characters Limit For Solo Stas hName_TDB_ST_013");
            extentLoggerPass("TDB_ST_013", "TBD_PRO_012, Stash -verify The Characters Limit For Solo Stash Name_TDB_ST_013 is passed");
        }
    }
    /**
     * Method To verify user can modify the target amount of the solo stash
     * @throws Exception
     */
    public void verifyIfUserCanModifyTheTargetAmountOfTheSoloStash_TDB_ST_014() throws Exception {
        HeaderChildNode("TDB_ST_014, Stash -verify If User Can Modify The Target Amount Of The SoloStash_TDB_ST_014");
        tonikLogin(propertyFileReader("password", "Login"));
        stashScreen();
        String currentTarget = getText(StashPage.getByOSType(platform,StashPage.ObjTargetAmountOnTheImage)).split(" ")[3];
        double currentTargetAmount=this.getAvailableBalanceInteger(currentTarget);
        double moreThanTarget=this.getAvailableBalanceInteger(propertyFileReader("MoreThanTargetForModify", "Stash"));
        manageStashScreen();
        click(StashPage.getByOSType(platform,StashPage.objModify), "Modify");
        modifyStashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTargetAmountField), "Target Amount Edit text field");
        clearField(StashPage.getByOSType(platform,StashPage.objTargetAmountField), "Stash for Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objTargetAmountField), propertyFileReader("MoreThanTargetForModify", "Stash"), "More Than Target For Modify Target Amount ");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSaveButton), "Save button");
        youUpdatesYourStashScreen();
        waitTime(3000);//hard wait Time required here
        swipe("UP",2);
        String updatedTarget = getText(StashPage.getByOSType(platform,StashPage.objLastTargetAmount)).split(" ")[3];
        double updatedTargetAmount=this.getAvailableBalanceInteger(updatedTarget);
        assertionValidationForDoubleType(updatedTargetAmount,moreThanTarget,"Target Amount");
        if (currentTargetAmount < updatedTargetAmount) {
            logger.info("TDB_ST_014, Stash - verify If User Can Modify The Target Amount Of The SoloStash_TDB_ST_014");
            extentLoggerPass("TDB_ST_014", "TDB_ST_014, Stash -verify If User Can Modify The Target Amount Of The SoloStash_TDB_ST_014 is passed");
        }else{
            logger.error("TDB_ST_014, Stash - verify If User Can Modify The Target Amount Of The SoloStash_TDB_ST_014");
            extentLoggerFail("TDB_ST_014", "TDB_ST_014, Stash -verify If User Can Modify The Target Amount Of The SoloStash_TDB_ST_014 is passed");
        }
    }
    /**
     * Method to verify user can modify the target amount in solo stash validation
     * @throws Exception
     */
    public void verifyIfUserCanModifyTheTargetAmountInSoloStashValidation_TDB_ST_015() throws Exception {
        HeaderChildNode("TDB_ST_015, Stash -verify If User Can Modify The Target Amount In Solo Stash Validation_TDB_ST_015");
        tonikLogin(propertyFileReader("password", "Login"));
        stashScreen();
        manageStashScreen();
        click(StashPage.getByOSType(platform,StashPage.objModify), "Modify");
        modifyStashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objTargetAmountField), "Target Amount Edit text field");
        clearField(StashPage.getByOSType(platform, StashPage.objTargetAmountField), "Stash for Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objTargetAmountField), propertyFileReader("LessThanTarget", "Stash"), "LessThan Target Amount ");
        if (platform.equalsIgnoreCase("IOS")) {
            click(StashPage.getByOSType(platform, StashPage.objKeyboardDoneButton), "Key board done button");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSetUpYourStashNextButton), "Next button");
        if (verifyElementPresent(StashPage.getByOSType(platform,StashPage.objMinimumAmountCanBe), "Minimum Amount can be 1000 Peso Error Message")) {
            logger.info("TDB_ST_015, Stash - verify If User Can Modify The Target Amount In Solo Stash Validation_TDB_ST_015");
            extentLoggerPass("TDB_ST_015", "TBD_PRO_015, Stash -verify If User Can Modify The Target Amount In Solo Stash Validation_TDB_ST_015 is passed");
        }
    }
    /**
     * Method to verify user can withdraw the amount from solo stash
     * @throws Exception
     */
    public void verifyIfUserCanWithdrawTheAmountFromSoloStash_TDB_ST_016() throws Exception {
        HeaderChildNode("TDB_ST_016, Stash -verify If User Can Withdraw The Amount From  SoloStash_TDB_ST_016");
        tonikLogin(propertyFileReader("password", "Login"));
        currentAmountValue=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        stashedAmountValue=this.getAvailableBalanceInteger(propertyFileReader("LessThanStashBalance", "Stash"));
        stashScreen();
        manageStashScreen();
        click(StashPage.getByOSType(platform,StashPage.objWithdrawToYourTonikAccount), "With draw To Your Tonik Account");
        withdrawFromYourStashScreen();
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objWithdrawToYourTonikAccount), 20, "With draw To Your Tonik Account");
        click(StashPage.getByOSType(platform,StashPage.objWithdrawToYourTonikAccount), "With draw To Your Tonik Account");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashTargetAmountInputField), "Stash target Amount field");
        type(StashPage.getByOSType(platform,StashPage.objStashTargetAmountInputField), propertyFileReader("LessThanStashBalance", "Stash"), "LessThan Stash Balance ");
        if(platform.equalsIgnoreCase("Android")) {
            hideKeyboard();
        }else{
            click(StashPage.getByOSType(platform,StashPage.objKeyboardDoneButton),"Key board done button");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objWithdrawButton), "With draw Button");
        reviewWithDrawal();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objConfirmButton), "Confirm button");
        sweetScreen();
        click(StashPage.getByOSType(platform,StashPage.objSweetViewDetails), "Sweet view Details ");
        transactionDetailsScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objQuestionMarkIconOnTransactionDetails), "contact customer Icon ");
        verifyElementPresent(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader), "Get In Touch Screen");
        waitTime(3000);
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objTransactionDetails), 10, "Sweet screen");
        waitTime(3000);
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objSweet), 10, "Sweet screen");
        click(StashPage.getByOSType(platform,StashPage.objOhYeahButton), "Yeah Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objStashHeader), 10, "Stash Header");
        newlyCreatedSoloGroupStash();
        verifyElementPresentAndClick(StashPage.objStashNameOnTheImageOnStashScreen(platform,"0"), "Stash Name on the image");
        selectedSoloGroupStashScreen("0");
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objStashHeader), 10, "Stash Header");
        waitTime(3000);
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        updatedAmountValue=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        assertionValidationForDoubleType(updatedAmountValue,currentAmountValue+stashedAmountValue,"Amount");
        if (currentAmountValue<updatedAmountValue) {
            logger.info("TSA Balance Reduced based on the amount stashed");
            extentLoggerPass("Stashed Amount", "TSA Balance Reduced based on the amount stashed");
        }
        swipe("UP", 1);
        stashTileContent();
        swipe("DOWN", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objHistoryIcon), "HistoryIcon");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objMoneyUnStashed), "money UnStashed");
        historyTransactionDetailsScreen();
        logger.info("TDB_ST_016, Stash - verify If User Can Withdraw The Amount From SoloStash_TDB_ST_016");
        extentLoggerPass("TDB_ST_016", "TBD_ST_016, Stash -verify If User Can Withdraw The Amount From SoloStash_TDB_ST_016 is passed");
    }
    /**
     * Method to verify TSA balance is credited twice if confirm button clicked twice
     * @throws Exception
     */
    public void verifyIfTSABalanceIsCreditedTwiceIfConfirmButtonClickedTwice_TDB_ST_017() throws Exception {
        HeaderChildNode("TDB_ST_017, Stash -verify If TSA Balance Is Credited Twice If Confirm Button Clicked Twice_TDB_ST_017");
        tonikLogin(propertyFileReader("password", "Login"));
        currentAmountValue=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        stashedAmountValue=this.getAvailableBalanceInteger(propertyFileReader("LessThanStashBalance", "Stash"));
        stashScreen();
        manageStashScreen();
        click(StashPage.getByOSType(platform,StashPage.objWithdrawToYourTonikAccount), "With draw To Your Tonik Account");
        withdrawFromYourStashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashTargetAmountInputField), "Stash target Amount field");
        type(StashPage.getByOSType(platform,StashPage.objStashTargetAmountInputField), propertyFileReader("LessThanStashBalance", "Stash"), "LessThan Stash Balance ");
        if(platform.equalsIgnoreCase("Android")) {
            hideKeyboard();
        }else{
            click(StashPage.getByOSType(platform,StashPage.objKeyboardDoneButton),"Key board done button");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objWithdrawButton), "With draw Button");
        reviewWithDrawal();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objConfirmButton), "Confirm button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objConfirmButton), "Confirm button");
        click(StashPage.getByOSType(platform,StashPage.objOhYeahButton), "Yeah Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objStashHeader), 10, "Stash header");
        waitTime(3000);
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        String updatedAmount = getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard));
        updatedAmountValue=this.getAvailableBalanceInteger(updatedAmount);
        assertionValidationForDoubleType(updatedAmountValue,currentAmountValue+stashedAmountValue,"Amount");
        if (currentAmountValue<updatedAmountValue) {
            logger.info("TSA Balance Reduced based on the amount stashed");
            extentLoggerPass("Stashed Amount", "TSA Balance Reduced based on the amount stashed");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objHistoryIcon), "HistoryIcon");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objMoneyUnStashed), "money UnStashed");
        historyTransactionDetailsScreen();
        logger.info("TDB_ST_017, Stash - verify If TSA Balance Is Credited Twice If Confirm Button Clicked Twice_TDB_ST_017");
        extentLoggerPass("TDB_ST_017", "TDB_ST_017, Stash -verify If TSA Balance Is Credited Twice If Confirm Button Clicked Twice_TDB_ST_017 is passed");
    }
    /**
     * Method to verify user can withdraw amount from solo stash insufficient balance
     * @throws Exception
     */
    public void verifyIfUserCanWithdrawAmountFromSoloStashInsufficientBalance_TDB_ST_018() throws Exception {
        HeaderChildNode("TDB_ST_018, Stash -verify If User Can Withdraw Amount From Solo Stash Insufficient Balance_TDB_ST_018");
        tonikLogin(propertyFileReader("password", "Login"));
        stashScreen();
        manageStashScreen();
        click(StashPage.getByOSType(platform,StashPage.objWithdrawToYourTonikAccount), "With draw To Your Tonik Account");
        withdrawFromYourStashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashTargetAmountInputField), "Stash target Amount field");
        type(StashPage.getByOSType(platform,StashPage.objStashTargetAmountInputField), propertyFileReader("MoreThanStashBalance", "Stash"), "More Than Stash Balance ");
        if(platform.equalsIgnoreCase("Android")) {
            hideKeyboard();
        }else{
            click(StashPage.getByOSType(platform,StashPage.objKeyboardDoneButton),"Key board done button");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objWithdrawButton), "With draw Button");
        if (verifyElementPresent(StashPage.getByOSType(platform,StashPage.objNoEnoughBalanceInStashAccount), "No Enough Balance In Stash Account")) {
            logger.info("TBD_ST_018, Stash - verify If User Can Withdraw Amount From Solo Stash Insufficient Balance_TBD_PST_018");
            extentLoggerPass("TBD_ST_018", "TBD_ST_018, Stash -verify If User Can Withdraw Amount From Solo Stash Insufficient Balance_ TBD_PST_018 is passed");
        }
    }
    /**
     * Method to verify user can close the solo stash
     * @throws Exception
     */
    public void verifyIfUserCanCloseTheSoloStash_TDB_ST_019() throws Exception {
        HeaderChildNode("TDB_ST_019, Stash -verify If User Can Close The Solo Stash_TDB_ST_019");
        tonikLogin(propertyFileReader("password", "Login"));
        currentAmountValue=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        stashScreen();
        String stashesDetails = getText(StashPage.getByOSType(platform,StashPage.obj5Of5AvailableStashes));
        String beforeClosingStash = stashesDetails.substring(0, stashesDetails.indexOf(" "));
        int beforeClosingStashValue = convertStringIntoInt(beforeClosingStash);
        swipe("UP", 1);
        String soloStashAmount = getText(StashPage.getByOSType(platform,StashPage.objLastTargetAmount)).split(" ")[1];
        stashedAmountValue=this.getAvailableBalanceInteger(soloStashAmount);
        manageStashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objClose), "Close");
        closePopup();
        click(StashPage.getByOSType(platform,StashPage.objStayButton), "Stay Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objClose), 10, "Close");
        click(StashPage.getByOSType(platform,StashPage.objClose), "Close");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objYesCloseTheStash), 10, "Yes Close The Stash");
        click(StashPage.getByOSType(platform,StashPage.objYesCloseTheStash), "Yes Close The Stash");
        brokeTheStashScreen();
        click(StashPage.getByOSType(platform,StashPage.objDoneButton), "Done button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objStashHeader), 10, "Stash Header");
        String updatedAvailableStashes = getText(StashPage.getByOSType(platform,StashPage.obj5Of5AvailableStashes));
        String afterClosingStash = updatedAvailableStashes.substring(0, stashesDetails.indexOf(" "));
        int afterClosingStashValue = convertStringIntoInt(afterClosingStash);
        if (!Objects.equals(beforeClosingStashValue, afterClosingStashValue)) {
            logger.info("Available Stash increased by one");
            extentLoggerPass("Available Stash", "Available Stash Increased by one");
        }
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        updatedAmountValue=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        assertionValidationForDoubleType(updatedAmountValue, currentAmountValue + stashedAmountValue, "TSA Balance");
        if (updatedAmountValue > currentAmountValue) {
            logger.info("TSA Balance changed based on the close Stash amount ");
            extentLoggerPass("Stashed Amount", "TSA Balance changed based on the close Stash amount");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objHistoryIcon), "HistoryIcon");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objCloserAmountTransaction), "Closer Amount");
        historyTransactionDetailsScreen();
        logger.info("TDB_ST_019, Stash - verify If User Can Close The Solo Stash_TDB_ST_019");
        extentLoggerPass("TDB_ST_019", "TBD_ST_019, Stash -verifyIfUserCanCloseTheSoloStash_TDB_ST_019 is passed");
    }
    /**
     * Method to verify user can achieve goal stash
     * @throws Exception
     */
    public void verifyUserCanAchieveGoalForSoloStash_TDB_ST_020() throws Exception {
        HeaderChildNode("TDB-ST-020, Stash -Verify if user can achieve the goal for Solo Stash");
        tonikLogin(propertyFileReader("password", "Login"));
        String currentAmount=getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)).replaceAll("[,₱]","");
        currentAmountValue=this.getAvailableBalanceInteger(currentAmount);
        stashScreen();
        navigateToSetupStashPage("Open a new Stash", "Solo Stash");
        createStash(1001, 0, propertyFileReader("validInput", "stash"));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objDoneButton), "Done Button");
        waitTime(3000);
        swipe("UP", 2);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objAddToStashBtn), "Add to Stash button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), "Add to stash Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), currentAmount, "Full Amount Input Text Field");
        if(platform.equalsIgnoreCase("Android")) {
            hideKeyboard();
        }else{
            click(StashPage.getByOSType(platform,StashPage.objKeyboardDoneButton),"Key board done button");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNextButton), "Next button");
        confirmTransferToStashScreen(currentAmount);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objConfirmButtonOnConfirmTransferToStash), "Confirm button");
        if (verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objCommunicationIsKeyPopup))) {
            click(StashPage.getByOSType(platform,StashPage.objDoItLater), "I'll do it later, Pinky swear");
        }
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objYouAdded), 10, "Money Stashed");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objMoneyStashed), "MoneyStashed"), "MoneyStashed", "MoneyStashed", "contains");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objYouAdded), "You Added");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objViewDetailsOfHowWeMovedIt), "View Details How We Moved It");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSweetDoneButton), "Money Stashed Done button");
        waitTime(3000);
        swipe("UP", 1);
        soloGroupStashScreenAfterFullAmountAdded();
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        String updatedAmount=getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)).replace("₱", "");
        updatedAmountValue=this.getAvailableBalanceInteger(updatedAmount);
        assertionValidationForDoubleType(currentAmountValue-achievedAmount, updatedAmountValue, "TSA Balance");
        if (Objects.equals(updatedAmountValue, "0.0")) {
            logger.info("Full stash amount reduced from TSA Balance");
            extentLoggerPass("Stashed Amount", "Full stash amount reduced from TSA Balance");
        }
        swipe("UP", 1);
        stashTileContentAfterFullAmountAdded();
        swipe("DOWN", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objHistoryIcon), "HistoryIcon");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objNameOfTheStashOnHistory), getTextVal(StashPage.getByOSType(platform,StashPage.objNameOfTheStashOnHistory), ":Name Of The Stash On History"));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), getTextVal(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), ":Transaction Value On History"));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), "Transaction History");
        historyTransactionDetailsScreen();
        logger.info("TDB-ST-020, Stash - verify User Can Achieve Goal For Solo Stash_TDB-ST-020");
        extentLoggerPass("TDB-ST-020", "TDB-ST-020, verify User Can Achieve Goal For Solo Stash_TDB-ST-020 is passed");
    }
    /**
     * Method to verify user can still add the money after the goal is achieved
     * @throws Exception
     */
    public void verifyUserCanStillAddTheMoneyAfterTheGoalIsAchieved_TDB_ST_021() throws Exception {
        HeaderChildNode("TDB-ST-021, Stash -verify User Can Still Add The Money After The Goal Is Achieved");
        tonikLogin(propertyFileReader("password", "Login"));
        String currentAmount=getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)).replaceAll("[,₱]","");
        currentAmountValue=this.getAvailableBalanceInteger(currentAmount);
        stashScreen();
        navigateToSetupStashPage("Open a new Stash", "Solo Stash");
        createStash(1001, 0, propertyFileReader("validInput", "stash"));
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objDoneButton), "Done Button");
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objLastStashName), "Stash Name on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objAddToStashBtn), "Add to Stash button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objAddToStashEditText), "Add to stash Edit text field");
        type(StashPage.getByOSType(platform, StashPage.objAddToStashEditText), currentAmount, "Full Amount Input Text Field");
        if(platform.equalsIgnoreCase("Android")) {
            hideKeyboard();
        }else{
            click(StashPage.getByOSType(platform,StashPage.objKeyboardDoneButton),"Key board done button");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objNextButton), "Next button");
        confirmTransferToStashScreen(currentAmount);
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objConfirmButtonOnConfirmTransferToStash), "Confirm button");
        waitForElementToBePresent(StashPage.getByOSType(platform, StashPage.objYouAdded), 10, "Money Stashed");
        valueValidation(getTextVal(StashPage.getByOSType(platform, StashPage.objMoneyStashed), "MoneyStashed"), "MoneyStashed", "MoneyStashed", "contains");
        verifyElementPresent(StashPage.getByOSType(platform, StashPage.objYouAdded), "You Added");
        verifyElementPresent(StashPage.getByOSType(platform, StashPage.objViewDetailsOfHowWeMovedIt), "View Details How We Moved It");
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objSweetDoneButton), "Money Stashed Done button");
        swipe("UP", 1);
        soloGroupStashScreenAfterFullAmountAdded();
        click(LoginPage.getByOSType(platform, LoginPage.objBackIcon), "Back Button");
        updatedAmountValue = this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform, StashPage.objTonikAmountOnDashboard)));
        assertionValidationForDoubleType(currentAmountValue - achievedAmount, updatedAmountValue, "TSA Balance");
        if (Objects.equals(updatedAmountValue, "0.00")) {
            logger.info("Full stash amount reduced from TSA Balance");
            extentLoggerPass("Stashed Amount", "Full stash amount reduced from TSA Balance");
        }
    }
    /**
     * Method to verify user can set higher goal for achieved goal solo stash
     * @throws Exception
     */
    public void verifyUserCanSetHigherGoalForAchievedGoalSoloStash_TDB_ST_022() throws Exception {
        HeaderChildNode("TDB_ST_022, Stash -Verify if user can set a higher goal for achieved goal in Solo Stash");
        tonikLogin(propertyFileReader("password", "Login"));
        String currentAmount=getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)).replaceAll("[,₱]","");
        stashedAmountValue=this.getAvailableBalanceInteger(currentAmount);
        stashScreen();
        swipe("UP",2);
        if(!verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objGoalAchievedTxt))) {
            navigateToSetupStashPage("Open a new Stash", "Solo Stash");
            createStash(1001, 0, propertyFileReader("validInput", "stash"));
            waitForElementToBePresent(StashPage.getByOSType(platform, StashPage.objDoneButton), 10, "Done button");
            verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objDoneButton), "Done button");
            if (verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objCommunicationIsKeyPopup))) {
                click(StashPage.getByOSType(platform,StashPage.objDoItLater), "I'll do it later, Pinky swear");
            }
            waitTime(3000);
            swipe("UP", 1);
            verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objLastStashName), "Stash Name on the image");
            verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objAddToStashBtn), "Add to Stash button");
            verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objAddToStashEditText), "Add to stash Edit text field");
            type(StashPage.getByOSType(platform, StashPage.objAddToStashEditText), currentAmount, "Full Amount Input Text Field");
            if (platform.equalsIgnoreCase("Android")) {
                hideKeyboard();
            } else {
                click(StashPage.getByOSType(platform, StashPage.objKeyboardDoneButton), "Key board done button");
            }
            verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objNextButton), "Next button");
            confirmTransferToStashScreen(currentAmount);
            verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objConfirmButtonOnConfirmTransferToStash), "Confirm button");
            waitForElementToBePresent(StashPage.getByOSType(platform, StashPage.objYouAdded), 10, "Money Stashed");
            if (verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objCommunicationIsKeyPopup))) {
                click(StashPage.getByOSType(platform,StashPage.objDoItLater), "I'll do it later, Pinky swear");
            }
            valueValidation(getTextVal(StashPage.getByOSType(platform, StashPage.objMoneyStashed), "MoneyStashed"), "MoneyStashed", "MoneyStashed", "contains");
            verifyElementPresent(StashPage.getByOSType(platform, StashPage.objYouAdded), "You Added");
            verifyElementPresent(StashPage.getByOSType(platform, StashPage.objViewDetailsOfHowWeMovedIt), "View Details How We Moved It");
            verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objSweetDoneButton), "Money Stashed Done button");
        }
        waitTime(3000);
        swipe("UP", 2);
        String achievedAmounts = getText(StashPage.getByOSType(platform,StashPage.objLastTargetAmount));
        groupStashAmount = achievedAmounts.split(" ")[1];
        groupTargetAmount = achievedAmounts.split(" ")[3];
        String stashAmount = groupStashAmount.substring(1, groupStashAmount.indexOf(".")).replace(",", "");
        int groupStashAmountValue = convertStringIntoInt(stashAmount);
        int lessThanStashAmount = groupStashAmountValue - 1;
        int moreThanStashAmount = groupStashAmountValue + 1;
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        if (verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objCommunicationIsKeyPopup))) {
            click(StashPage.getByOSType(platform,StashPage.objDoItLater), "I'll do it later, Pinky swear");
        }
        groupStashGoalAchievedScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objGoalAchievedNextButton), "What's your next step?");
        goalAchievedNextStepPopup();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSetAHigherGoalText), "Set a higher goal");
        modifyStashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTargetAmountField), "Target Amount Edit text field");
        clearField(StashPage.getByOSType(platform,StashPage.objTargetAmountField), "Target Amount Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objTargetAmountField), String.valueOf(lessThanStashAmount), "LessThan Target Amount ");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSetUpYourStashNextButton), "Next button");
        click(ProfilePage.getByOSType(platform,ProfilePage.objProfilePicture), "Profile Picture");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashAmountError), "Amount cannot be less than or equal to Stash balance");
        clearField(StashPage.getByOSType(platform,StashPage.objTargetAmountField), "Target Amount Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objTargetAmountField), String.valueOf(moreThanStashAmount), "MoreThan Target Amount");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSetUpYourStashNextButton), "Next button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objYouUpdatedYourStashTitle), "You updated your Stash.");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashSuccessDesc), "Update when you go out with someone who is better looking than you. Thanks for updating.");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashDoneButton), "Success Stash Done");
        waitTime(3000);
        swipe("UP", 1);
        if (verifyElementDisplayed(StashPage.getByOSType(platform, StashPage.objGoalAchievedTxt))) {
            logger.error("Goal achieved text is displayed");
            extentLoggerFail("Goal achieved", "Goal achieved text is displayed");
        } else {
            logger.info("Goal achieved text is not displayed after set higher Goal");
            extentLoggerPass("Higher goal set", "Goal achieved text is not displayed after set higher Goal");
        }
        String updatedTargetAmount = getText(StashPage.getByOSType(platform,StashPage.objLastTargetAmount)).split(" ")[3];
        updatedTargetAmount = updatedTargetAmount.substring(1, updatedTargetAmount.indexOf(".")).replace(",", "");
        updatedTargetAmountValue = convertStringIntoDouble(updatedTargetAmount);
        assertionValidationForDoubleType(moreThanStashAmount, updatedTargetAmountValue, "Target Amount for Solo Stash");
        logger.info("TDB-ST-022, Stash - Verify User Can Set Higher Goal For Achieved Goal Solo Stash_TDB-ST-022");
        extentLoggerPass("TDB-ST-022", "TDB-ST-022, Verify User Can Set Higher Goal For Achieved Goal Group Stash_TDB-ST-022 is passed");
    }
    /**
     * Method to verify user can convert stash to time deposit for solo stash
     * @throws Exception
     */
    public void verifyUserCanConvertStashToTimeDepositForSoloStash_TDB_ST_023() throws Exception {
        HeaderChildNode("TDB-ST-023, Stash -Verify if user can convert a stash to Time Deposit for Solo Stash");
        tonikLogin(propertyFileReader("password", "Login"));
        stashScreen();
        String stashesDetails = getText(StashPage.getByOSType(platform,StashPage.obj5Of5AvailableStashes));
        String beforeClosingStash = stashesDetails.substring(0, stashesDetails.indexOf(" "));
        int beforeClosingStashValue = convertStringIntoInt(beforeClosingStash);
        swipe("UP", 1);
        String achievedAmounts = getText(StashPage.getByOSType(platform,StashPage.objLastTargetAmount));
        groupStashAmount = achievedAmounts.split(" ")[1];
        groupTargetAmount = achievedAmounts.split(" ")[3];
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        groupStashGoalAchievedScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objGoalAchievedNextButton), "What's your next step?");
        goalAchievedNextStepPopup();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objBackToStashText), "Back to Stash");
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objGoalAchievedNextButton), "What's your next step?");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objConvertToTimeDepositText), "Convert to Time Deposit");
        convertStashToTDPopup();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLeaveItAsButton), "Leave it as is");
        selectedSoloGroupStashScreen("0");
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        swipe("UP", 2);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objGoalAchievedNextButton), "What's your next step?");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objConvertToTimeDepositText), "Convert to Time Deposit");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objCloseStashTDButton), "Close Stash");
        youBrokeTheStashScreenForGoalAchieved();
        tapOnScreen(216,842,"close start later");//object locator not available
//        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objCloseStashLaterButton), "Close Stash Later");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashHeader), "Stash Header");
        stashesDetails = getText(StashPage.getByOSType(platform,StashPage.obj5Of5AvailableStashes));
        String afterClosingStash = stashesDetails.substring(0, stashesDetails.indexOf(" "));
        int afterClosingStashValue = convertStringIntoInt(afterClosingStash);
        if (Objects.equals(afterClosingStashValue, beforeClosingStashValue + 1)) {
            logger.info("User closed the stash successfully");
            extentLoggerPass("Stash count", "Stash count increase : User closed the stash successfully");
        }
        navigateToSetupStashPage("Open a new Stash", "Solo Stash");
        createStash(1001, 0, propertyFileReader("validInput", "stash"));
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objDoneButton), 10, "close button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objDoneButton), "close button");
        swipe("UP", 3);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objAddToStashBtn), "Add to Stash button");
        String stashBalance = getText(StashPage.getByOSType(platform,StashPage.objStashBalance)).split(" ")[1].replace("₱", "").replace(",", "");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), "Add to stash Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), stashBalance, "Full Amount Input Text Field");
        if(platform.equalsIgnoreCase("Android")) {
            hideKeyboard();
        }else{
            click(StashPage.getByOSType(platform,StashPage.objKeyboardDoneButton),"Key board done button");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNextButton), "Next button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objConfirmButtonOnConfirmTransferToStash), "Confirm button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objYouAdded), 10, "Money Stashed");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objMoneyStashed), "MoneyStashed"), "MoneyStashed", "MoneyStashed", "contains");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objYouAdded), "You Added");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objViewDetailsOfHowWeMovedIt), "View Details How We Moved It");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSweetDoneButton), "Money Stashed Done button");
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objGoalAchievedNextButton), "What's your next step?");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objConvertToTimeDepositText), "Convert to Time Deposit");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objCloseStashTDButton), "Close Stash");
        youBrokeTheStashScreenForGoalAchieved();
        tapOnScreen(212,761,"Start a Time deposit");//object element not available
//      verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStartATDButton), "Start a Time Deposit");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objHowMuchInvestHeader), "How much will you invest");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTDCalculatorNextButton), "I am interested");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSetupTDHeader), "Set up a Time Deposit");
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSetupTDToggleButton), "I accept Terms and Conditions");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSetUpTDNextButton), "Let's open this Time Deposit");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objWootDoneButton),10,"Woot Done Button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objWootDoneButton), "Woot Done Button");
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        stashScreen();
        stashesDetails = getText(StashPage.getByOSType(platform,StashPage.obj5Of5AvailableStashes));
        String afterClosingStash2 = stashesDetails.substring(0, stashesDetails.indexOf(" "));
        int afterClosingStashValue2 = convertStringIntoInt(afterClosingStash2);
        if (Objects.equals(afterClosingStashValue2, beforeClosingStashValue + 1)) {
            logger.info("User closed the stash successfully");
            extentLoggerPass("Stash count", "Stash count increase : User closed the stash successfully");
        }
        logger.info("TDB-ST-023, Stash - verify User Can Convert Stash To Time Deposit For Solo Stash_TDB-ST-023");
        extentLoggerPass("TDB-ST-023", "TDB-ST-023, verify User Can Convert Stash To Time Deposit For Solo Stash_TDB-ST-023 is passed");
    }
    /**
     * Method to verify user can create emergency solo stash
     * @param targetAmount
     * @param initialAmount
     * @param stashName
     * @param stashType
     * @throws Exception
     */
    public void verifyUserCanCreateEmergencySoloStash_TDB_ST024(int targetAmount, int initialAmount, String stashName, String stashType) throws Exception {
        HeaderChildNode("TDB-ST-024, Stash - Verify if user can create Emergency Solo Stash");
        createStashAndVerifyTheStashTileAdHistory(targetAmount, initialAmount, stashName, stashType);
        logger.info("TDB-ST-024, Stash - Verify if user can create Emergency Solo Stash");
        extentLoggerPass("TDB-ST-024", "Stash - Verify if user can create Emergency Solo Stash");
    }
    /**
     * Method to verify user can create vacation solo stash
     * @param targetAmount
     * @param initialAmount
     * @param stashName
     * @param stashType
     * @throws Exception
     */
    public void verifyUserCanCreateVacationSoloStash_TDB_ST025(int targetAmount,int initialAmount, String stashName,String stashType) throws Exception {
        HeaderChildNode("TDB-ST-025, Stash - Verify if user can create Vacation Group Stash");
        createStashAndVerifyTheStashTileAdHistory(targetAmount,initialAmount,stashName,stashType);
        logger.info("TDB-ST-025, Stash - Verify if user can create Vacation Group Stash");
        extentLoggerPass("TDB-ST-025", "Stash - Verify if user can create Vacation Group Stash");
    }
    /**
     * Method to verify user can create tuition solo stash
     * @param targetAmount
     * @param initialAmount
     * @param stashName
     * @param stashType
     * @throws Exception
     */
    public void verifyUserCanCreateTuitionSoloStash_TDB_ST026(int targetAmount,int initialAmount, String stashName,String stashType) throws Exception {
        HeaderChildNode("TDB-ST-026, Stash - Verify if user can create Tuition Solo Stash");
        createStashAndVerifyTheStashTileAdHistory(targetAmount,initialAmount,stashName,stashType);
        logger.info("TDB-ST-026, Stash - Verify if user can create Tuition Solo Stash");
        extentLoggerPass("TDB-ST-026", "Stash - Verify if user can create Tuition Solo Stash");
    }
    /**
     * Method to verify user can create new ride solo stash
     * @param targetAmount
     * @param initialAmount
     * @param stashName
     * @param stashType
     * @throws Exception
     */
    public void verifyUserCanCreateNewRideSoloStash_TDB_ST027(int targetAmount,int initialAmount, String stashName,String stashType) throws Exception {
        HeaderChildNode("TDB-ST-027, Stash - Verify if user can create New Ride Solo Stash");
        createStashAndVerifyTheStashTileAdHistory(targetAmount,initialAmount,stashName,stashType);
        logger.info("TDB-ST-027, Stash - Verify if user can create New Ride Solo Stash");
        extentLoggerPass("TDB-ST-027", "Stash - Verify if user can create New Ride Solo Stash");
    }
    /**
     * Method to verify user can create game console solo stash
     * @param targetAmount
     * @param initialAmount
     * @param stashName
     * @param stashType
     * @throws Exception
     */
    public void verifyUserCanCreateGameConsoleSoloStash_TDB_ST028(int targetAmount,int initialAmount, String stashName,String stashType) throws Exception {
        HeaderChildNode("TDB-ST-028, Stash - Verify if user can create Game Console Solo Stash");
        createStashAndVerifyTheStashTileAdHistory(targetAmount,initialAmount,stashName,stashType);
        logger.info("TDB-ST-028, Stash - Verify if user can create Game Console Solo Stash");
        extentLoggerPass("TDB-ST-028", "Stash - Verify if user can create Game Console Solo Stash");
    }
    /**
     * Method to verify user can create more than 5 solo stash
     * @param targetAmount
     * @param initialAmount
     * @param stashName
     * @param stashType
     * @throws Exception
     */
    public void verifyUserCanCreateMoreThan5SoloStashes_TDB_ST029(int targetAmount,int initialAmount, String stashName,String stashType) throws Exception {
        HeaderChildNode("TDB-ST-029, Stash - Verify if user can create more than 5 Group stashes");
        tonikLogin(propertyFileReader("password", "Login"));
        swipe("UP",2);
        if (verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader))) {
            click(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader),"Stash card");
        }else{
            click(StashPage.getByOSType(platform,StashPage.objPutYourCashInAStash),"Stash card");
        }
        waitTime(4000);
        while(!getText(StashPage.getByOSType(platform,StashPage.obj5Of5AvailableStashes)).equals("0 of 5 available stashes")){
            verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStartANewStash),getText(StashPage.getByOSType(platform,StashPage.objStartANewStash))+" : Button");
            verifyElementPresentAndClick(StashPage.objAllStashOption(getPlatform(),stashName),getText(StashPage.objAllStashOption(getPlatform(),stashName))+" Stash Option");
            verifySelectStashPopupAndSelectTypeOfStash(stashType);;
            createStash(targetAmount,initialAmount,stashName);
            verifyGroupSoloStashCreatedSuccessMessage();
            waitTime(5000);
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStartANewStash),getText(StashPage.getByOSType(platform,StashPage.objStartANewStash))+" : Button");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objPopupHeader)),"Sorry, you can only have 5 actived Stashes created by yourself at the same time. You can close a Stash and create a new one."," Popup Header");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objPopupOkButton),getText(StashPage.getByOSType(platform,StashPage.objPopupOkButton))+" :Button");
        logger.info("TDB-ST-029, Stash - Verify if user can create more than 5 Solo stashes");
        extentLoggerPass("TDB-ST-029", "Stash - Verify if user can create more than 5 Solo stashes");
    }
    /**
     * Method to verify Setup group stash screen
     * @throws Exception
     */
    public void setUpGroupStashScreen() throws Exception {
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSetUpYourStash), "Set up your Stash");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objSetUpYourStash), "SetUpYourStashScreen"), "SetUpYourStashScreen", "SetUpYourStashScreen", "contains");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objStashFor), "StashFor"), "StashFor", "StashFor", "contains");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objTargetAmount), "TargetAmount"), "TargetAmount", "TargetAmount", "contains");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objNextButton), "Next button");
    }
    /**
     * method to Verify if user can create Group stash
     * @throws Exception
     */
    public void verifyUserCanCreateGroupStash() throws Exception {
        HeaderChildNode("TDB-ST-030, Stash -Verify if user can create Group stash");
        tonikLogin(propertyFileReader("password", "Login"));
        stashScreen();
        newStashScreen();
        openANewStashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objGroupStashOnPopup), "Select Group Stash on pop up");
        setUpGroupStashScreen();
        setUpOrModifyYourStashScreenValidations();
        setInitialSavingScreen();
        click(StashPage.getByOSType(platform,StashPage.objSkipForNow), "Skip For Now");
        reviewStashDetailsScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTermsAndCondition), "Terms and Condition");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTermsAndConditionScreen), "Terms and Condition Screen");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTermsAndConditionBackIcon), "Back Button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTermsAndConditionCheck), "Terms and condition Check box");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objCreateStashButtonEnabled), "Create Stash");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objGroupStashReadyText), "Group Stash is ready!");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objGroupStashReadyPageDesc), "Time to invite friends with Tonik accounts to join.");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objInvitePeopleInStash), "Invite people to your Stash");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objInviteToYourStashText), "Invite to your Stash");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSearchInput), "Search text field");
        verifyElementDisabled(StashPage.getByOSType(platform,StashPage.objInviteFriendButton), "Send Invite via SMS");
        click(StashPage.getByOSType(platform,StashPage.objSelectContactButton_1), "contact 1");
        click(StashPage.getByOSType(platform,StashPage.objSelectContactButton_2), "contact 2");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objInviteFriendButton), "Send Invite via SMS");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objMessageApp), "message app");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSendButton), "send button for message");
        click(StashPage.getByOSType(platform,StashPage.objCancelButton), "cancel the message");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objGoBackToStashText), "Go back to my Stash");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objInviteFriendButton), "Go back to my Stash");
        newlyCreatedSoloGroupStash();
        addToStashScreenForGroupSolo();
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        manageStashScreen();
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        verticalSwipeTillElementDisplayed(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader));
        stashTileContent();
        swipe("DOWN", 2);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objHistoryIcon), "HistoryIcon");
        logger.info("TDB-ST-030, Stash - verify If User Can Create Group Stash_TDB-ST-030");
        extentLoggerPass("TDB-ST-030", "TDB-ST-030, Stash -verify If User Can Create GroupStash_TDB-ST-030 is passed");
    }
    /**
     * method to Verify if user can add funds to Group stash from the List details screen
     * @throws Exception
     */
    public void verifyUserCanAddFundsToGroupStashFromDetailsScreen() throws Exception {
        HeaderChildNode("TDB-ST-031, Stash -Verify if user can add funds to Group stash from the List details screen");
        tonikLogin(propertyFileReader("password", "Login"));
        String stashedAmount = propertyFileReader("ValidPeso", "Stash");
        currentAmountValue=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        stashedAmountValue=this.getAvailableBalanceInteger(propertyFileReader("ValidPeso", "Stash"));
        stashScreen();
        addToStashScreenForGroupSolo();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), "Add to stash Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), propertyFileReader("lessThanPeso", "Stash"), "LessThan Amount Input Text Field");
        if(platform.equalsIgnoreCase("Android")){
            hideKeyboard();
        }else{
            tapOnScreen(300,150,"screen");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNextButton), "Next button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objMinimumAmountCanBe), "Minimum Amount Can be 1 Peso");
        click(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), "Initial Saving Amount Input Text Field");
        clearField(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), "Target Amount Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), propertyFileReader("MoreThanPeso", "Stash"), "More than Tonik balance");
        if(platform.equalsIgnoreCase("Android")){
            hideKeyboard();
        }else{
            tapOnScreen(300,150,"screen");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNextButton), "Next button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objNoEnoughBalanceInTonikAccount), "No Enough Balance In Tonik Account");
        click(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), "Initial Saving Amount Input Text Field");
        clearField(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), "Target Amount Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), stashedAmount, "Valid Peso");
        if(platform.equalsIgnoreCase("Android")){
            hideKeyboard();
        }else{
            tapOnScreen(300,150,"screen");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNextButton), "Next button");
        confirmTransferToStashScreen(stashedAmount);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objConfirmButtonOnConfirmTransferToStash), "Confirm button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objYouAdded), 10, "Money Stashed");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objMoneyStashed), "MoneyStashed"), "MoneyStashed", "MoneyStashed", "contains");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objYouAdded), "You Added");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objViewDetailsOfHowWeMovedIt), "View Details How We Moved It");
        transactionDetailsScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objQuestionMarkIconOnTransactionDetails), "contact customer Icon ");
        explicitWaitVisible(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader), 10);
        verifyElementPresent(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader), "Get In Touch Screen");
        explicitWaitVisible(ProfilePage.getByOSType(platform,ProfilePage.objBackIcon), 10);
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objQuestionMarkIconOnTransactionDetails), 10, "contact customer Icon");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objDoneButton), "Done Button");
        swipe("UP", 1);
        groupStashAmountValue=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objLastTargetAmount)).split(" ")[1]);
        newlyCreatedSoloGroupStash();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        selectedSoloGroupStashScreen("0");
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        explicitWaitVisible(ProfilePage.getByOSType(platform,ProfilePage.objBackIcon), 10);
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        updatedAmountValue=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        assertionValidationForDoubleType(updatedAmountValue, currentAmountValue - stashedAmountValue, "TSA Balance");
        if (updatedAmountValue < currentAmountValue) {
            logger.info("TSA Balance Reduced based on the amount stashed");
            extentLoggerPass("Stashed Amount", "TSA Balance Reduced based on the amount stashed");
        }
        swipe("UP", 1);
        stashTileContent();
        swipe("DOWN", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objHistoryIcon), "HistoryIcon");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objNameOfTheStashOnHistory), getTextVal(StashPage.getByOSType(platform,StashPage.objNameOfTheStashOnHistory), ":Name Of The Stash On History"));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), getTextVal(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), ":Transaction Value On History"));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), "Transaction History");
        historyTransactionDetailsScreen();
        logger.info("TDB-ST-031, Stash - Verify User Can Add Funds To Group Stash From Details Screen-ST-031");
        extentLoggerPass("TDB-ST-031", "TDB-ST-031, Stash -Verify User Can Add Funds To Group Stash From Details Screen-ST-031 is passed");
    }
    /**
     * method to Verify if user can Manage the Group stash from the List details screen
     * @throws Exception
     */
    public void verifyUserCanManageGroupStashFromListDetailsScreen() throws Exception {
        HeaderChildNode("TDB-ST-032, Stash -Verify if user can Manage the Group stash from the List details screen");
        tonikLogin(propertyFileReader("password", "Login"));
        stashScreen();
        swipe("UP", 1);
        groupTargetAmount = getText(StashPage.getByOSType(platform,StashPage.objLastTargetAmount)).split(" ")[3];
        manageStashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashDetails), "Stash details");
        stashDetailsScreen();
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objManageStash), "Manage Stash");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objManageStash), "ManageStash"), "ManageStash", "ManageStash", "contains");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objModify), "Modify for Manage Stash");
        modifyStashScreen();
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objManageStash), "Manage Stash");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objManageStash), "ManageStash"), "ManageStash", "ManageStash", "contains");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objWithdrawToYourTonikAccount), " Withdraw to your TONIK Account");
        withdrawFromYourStashScreen();
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objManageStash), "Manage Stash");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objManageStash), "ManageStash"), "ManageStash", "ManageStash", "contains");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objClose), "Close");
        closePopup();
        logger.info("TDB-ST-032, Stash - Verify User Can Manage Group Stash From Details Screen-ST-032");
        extentLoggerPass("TDB-ST-032", "TDB-ST-032, Stash -Verify User Can Manage Group Stash From Details Screen-ST-032 is passed");
    }
    /**
     * method to Verify if user can add funds to Group stash from the Summary details screen
     * @throws Exception
     */
    public void verifyUserCanAddFundsToGroupStashFromSummaryDetails() throws Exception {
        HeaderChildNode("TDB-ST-033, Stash -Verify if user can add funds to Group stash from the Summary details screen");
        tonikLogin(propertyFileReader("password", "Login"));
        String stashedAmount = propertyFileReader("ValidPeso", "Stash");
        stashedAmountValue = convertStringIntoDouble(stashedAmount);
        currentAmountValue=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        stashScreen();
        swipe("UP", 1);
        String groupSatshAmount = getText(StashPage.getByOSType(platform,StashPage.objLastTargetAmount)).split(" ")[1];
        groupSatshAmount = groupSatshAmount.substring(1, groupSatshAmount.indexOf("."));
        groupStashAmountValue = convertStringIntoDouble(groupSatshAmount);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        selectedSoloGroupStashScreen("0");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objAddToStashBtn), "Add to Stash button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), "Add to stash Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), propertyFileReader("lessThanPeso", "Stash"), "LessThan Amount Input Text Field");
        if(platform.equalsIgnoreCase("Android")){
            hideKeyboard();
        }else{
            tapOnScreen(300,150,"screen");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNextButton), "Next button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objMinimumAmountCanBe), "Minimum Amount Can be 1 Peso");
        click(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), "Initial Saving Amount Input Text Field");
        clearField(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), "Target Amount Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), propertyFileReader("MoreThanPeso", "Stash"), "More than Tonik balance");
        if(platform.equalsIgnoreCase("Android")){
            hideKeyboard();
        }else{
            tapOnScreen(300,150,"screen");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNextButton), "Next button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objNoEnoughBalanceInTonikAccount), "No Enough Balance In Tonik Account");
        click(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), "Initial Saving Amount Input Text Field");
        clearField(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), "Target Amount Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), stashedAmount, "Valid Peso");
        if(platform.equalsIgnoreCase("Android")){
            hideKeyboard();
        }else{
            tapOnScreen(300,150,"screen");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNextButton), "Next button");
        confirmTransferToStashScreen(stashedAmount);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objConfirmButtonOnConfirmTransferToStash), "Confirm button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objYouAdded), 10, "Money Stashed");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objMoneyStashed), "MoneyStashed"), "MoneyStashed", "MoneyStashed", "contains");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objYouAdded), "You Added");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objViewDetailsOfHowWeMovedIt), "View Details How We Moved It");
        transactionDetailsScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objQuestionMarkIconOnTransactionDetails), "contact customer Icon ");
        verifyElementPresent(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader), "Get In Touch Screen");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objQuestionMarkIconOnTransactionDetails), 10, "contact customer Icon");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objDoneButton), "Done Button");
        swipe("UP", 1);
        newlyCreatedSoloGroupStash();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        selectedSoloGroupStashScreen("0");
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitTime(3000);
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        updatedAmountValue=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        assertionValidationForDoubleType(updatedAmountValue, currentAmountValue - stashedAmountValue, "TSA Balance");
        if (updatedAmountValue < currentAmountValue) {
            logger.info("TSA Balance Reduced based on the amount stashed");
            extentLoggerPass("Stashed Amount", "TSA Balance Reduced based on the amount stashed");
        }
        swipe("UP", 1);
        stashTileContent();
        swipe("DOWN", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objHistoryIcon), "HistoryIcon");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objNameOfTheStashOnHistory), getTextVal(StashPage.getByOSType(platform,StashPage.objNameOfTheStashOnHistory), ":Name Of The Stash On History"));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), getTextVal(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), ":Transaction Value On History"));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), "Transaction History");
        historyTransactionDetailsScreen();
        logger.info("TDB-ST-033, Stash - Verify User Can Add Funds To Group Stash From Summary Details-ST-033");
        extentLoggerPass("TDB-ST-033", "TDB-ST-033, Stash -Verify User Can Add Funds To Group Stash From Summary Details-ST-033 is passed");
    }
    /**
     * method to Verify if user can Manage the Group stash from the Summary details screen
     * @throws Exception
     */
    public void verifyUserCanManageGroupStashFromSummaryDetailsScreen() throws Exception {
        HeaderChildNode("TDB-ST-035, Stash -Verify if user can Manage the Group stash from the Summary details screen");
        tonikLogin(propertyFileReader("password", "Login"));
        stashScreen();
        swipe("UP", 1);
        groupTargetAmount = getText(StashPage.getByOSType(platform,StashPage.objLastTargetAmount)).split(" ")[3];
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        selectedSoloGroupStashScreen("0");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objManageStashBtn), "Manage Stash button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashDetails), "Stash details");
        stashDetailsScreen();
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objManageStash), "Manage Stash");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objManageStash), "ManageStash"), "ManageStash", "ManageStash", "contains");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objModify), "Modify for Manage Stash");
        modifyStashScreen();
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objManageStash), "Manage Stash");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objManageStash), "ManageStash"), "ManageStash", "ManageStash", "contains");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objWithdrawToYourTonikAccount), " Withdraw to your TONIK Account");
        withdrawFromYourStashScreen();
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objManageStash), "Manage Stash");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objManageStash), "ManageStash"), "ManageStash", "ManageStash", "contains");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objClose), "Close");
        closePopup();
        logger.info("TDB-ST-035, Stash - Verify User Can Manage Group Stash From Summary Details Screen-ST-035");
        extentLoggerPass("TDB-ST-035", "TDB-ST-035, Stash -Verify User Can Manage Group Stash From Summary Details Screen-ST-035 is passed");
    }
    /**
     * method to Verify if user can view the Group stash details
     * @throws Exception
     */
    public void verifyUserCanViewGroupStashDetails() throws Exception {
        HeaderChildNode("TDB-ST-036, Stash -Verify if user can view the Group stash details");
        tonikLogin(propertyFileReader("password", "Login"));
        stashScreen();
        swipe("UP", 1);
        groupTargetAmount = getText(StashPage.getByOSType(platform,StashPage.objLastTargetAmount)).split(" ")[3];
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        selectedSoloGroupStashScreen("0");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objManageStashBtn), "Manage Stash button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objManageStash), "Manage Stash");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashDetails), "Stash details");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objModify), "Modify");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objWithdrawToYourTonikAccount), "WithDraw To Your Tonik Account");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objClose), "Close");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashDetails), "Stash details");
        stashDetailsScreen();
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objManageStash), "Manage Stash");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objManageStash), "ManageStash"), "ManageStash", "ManageStash", "contains");
        logger.info("TDB-ST-036, Stash - Verify User Can View Group Stash Details-ST-036");
        extentLoggerPass("TDB-ST-036", "TDB-ST-036, Stash -Verify User Can View Group Stash Details-ST-036 is passed");
    }
    /**
     * method to Verify if user can change the name & modify the picture of the Group stash
     * @throws Exception
     */
    public void verifyUserCanChangeNameAndModifyPictureOfGroupStash() throws Exception {
        HeaderChildNode("TDB-ST-037, Stash -Verify if user can change the name & modify the picture of the Group stash");
        String updatedInput = propertyFileReader("updatedInput", "Stash");
        tonikLogin(propertyFileReader("password", "Login"));
        stashScreen();
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        selectedSoloGroupStashScreen("0");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objManageStashBtn), "Manage Stash button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objManageStash), "Manage Stash");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashDetails), "Stash details");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objModify), "Modify");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objWithdrawToYourTonikAccount), "WithDraw To Your Tonik Account");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objClose), "Close");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objModify), "Modify for Manage Stash");
        modifyStashScreen();
        setUpOrModifyYourStashScreenValidations();
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objYouUpdatedYourStashTitle), "You updated your Stash.");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashSuccessDesc), "Update when you go out with someone who is better looking than you. Thanks for updating.");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashDoneButton), "Success Stash Done");
        swipe("UP", 1);
        String actualValue = getText(StashPage.getByOSType(platform,StashPage.objLastStashName));
        if (actualValue.equals(updatedInput)) {
            logger.info("TBD_PRO_037, Stash - Verify User Can Change Name And Modify Picture Of Group Stash_TBD_PST_037");
            extentLoggerPass("TBD_PRO_037", "TBD_PRO_037, Verify User Can Change Name And Modify Picture Of GroupStash_TBD_PST_037 is passed");
        }
    }
    /**
     * method to Verify the characters limit for Group Stash name
     * @throws Exception
     */
    public void verifyCharactersLimitForGroupStashName() throws Exception {
        HeaderChildNode("TDB-ST-038, Stash -Verify the characters limit for Group Stash name");
        String validInput = propertyFileReader("validInput", "Stash");
        tonikLogin(propertyFileReader("password", "Login"));
        stashScreen();
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        selectedSoloGroupStashScreen("0");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objManageStashBtn), "Manage Stash button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objManageStash), "Manage Stash");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashDetails), "Stash details");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objModify), "Modify");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objWithdrawToYourTonikAccount), "WithDraw To Your Tonik Account");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objClose), "Close");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objModify), "Modify for Manage Stash");
        modifyStashScreen();
        uploadProfilePicture();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNameInputTextField), "Stash for Edit text field");
        clearField(StashPage.getByOSType(platform,StashPage.objNameInputTextField), "Stash for Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objNameInputTextField), propertyFileReader("lessThan", "Stash"), "text_field");
        click(ProfilePage.getByOSType(platform,ProfilePage.objProfilePicture), "Profile Picture");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objPleaseEnterAValidStashName), "Please Enter a valid stash name inline Error Message");
        click(StashPage.getByOSType(platform,StashPage.objNameInputTextField), "Stash for Edit text field");
        clearField(StashPage.getByOSType(platform,StashPage.objNameInputTextField), "Stash for Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objNameInputTextField), propertyFileReader("MoreThan", "Stash"), "MoreThan");
        click(ProfilePage.getByOSType(platform,ProfilePage.objProfilePicture), "Profile Picture");
        String updatedName = getText(StashPage.getByOSType(platform,StashPage.objNameInputTextField));
        if (!Objects.equals(updatedName, propertyFileReader("MoreThan", "Stash"))) {
            logger.info("Accepted only 35 character");
            extentLoggerPass("Minimum Char", "Accepted only 35 character");
        }
        click(StashPage.getByOSType(platform,StashPage.objNameInputTextField), "Stash for Edit text field");
        clearField(StashPage.getByOSType(platform,StashPage.objNameInputTextField), "Stash for Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objNameInputTextField), propertyFileReader("validInput", "Stash"), "text_field");
        click(ProfilePage.getByOSType(platform,ProfilePage.objProfilePicture), "Profile Picture");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTargetAmountField), "Target Amount Edit text field");
        clearField(StashPage.getByOSType(platform,StashPage.objTargetAmountField), "Target Amount Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objTargetAmountField), propertyFileReader("LessThanTarget", "Stash"), "LessThan Target Amount ");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSetUpYourStashNextButton), "Next button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objMinimumAmountCanBe), "Minimum Amount can be 1000 Peso Error Message");
        clearField(StashPage.getByOSType(platform,StashPage.objTargetAmountField), "Target Amount Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objTargetAmountField), propertyFileReader("MoreThanTarget", "Stash"), "MoreThan Target Amount");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSetUpYourStashNextButton), "Next button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objYouUpdatedYourStashTitle), "You updated your Stash.");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashSuccessDesc), "Update when you go out with someone who is better looking than you. Thanks for updating.");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashDoneButton), "Success Stash Done");
        swipe("UP", 1);
        String actualValue = getText(StashPage.getByOSType(platform,StashPage.objLastStashName));
        if (actualValue.equals(validInput)) {
            logger.info("TBD_PRO_038, Stash - verify Characters Limit For Group Stash Name_TBD_PST_038");
            extentLoggerPass("TBD_PRO_038", "TBD_PRO_038, verify Characters Limit For Group Stash Name_TBD_PST_038 is passed");
        }
    }
    /**
     * method to Verify if user can modify the target amount of the Group stash
     * @throws Exception
     */
    public void verifyUserCanModifyTargetAmountOfGroupStash() throws Exception {
        HeaderChildNode("TDB-ST-039,040, Stash -Verify if user can modify the target amount of the Group stash");
        String updatedTargetAmount = propertyFileReader("updateTargetAmount", "stash");
        updatedTargetAmountValue = convertStringIntoDouble(updatedTargetAmount);
        tonikLogin(propertyFileReader("password", "Login"));
        stashScreen();
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        selectedSoloGroupStashScreen("0");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objManageStashBtn), "Manage Stash button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objModify), "Modify for Manage Stash");
        modifyStashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTargetAmountField), "Target Amount Edit text field");
        clearField(StashPage.getByOSType(platform,StashPage.objTargetAmountField), "Target Amount Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objTargetAmountField), propertyFileReader("LessThanTarget", "Stash"), "LessThan Target Amount ");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSetUpYourStashNextButton), "Next button");
        click(ProfilePage.getByOSType(platform,ProfilePage.objProfilePicture), "Profile Picture");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objMinimumAmountCanBe), "Minimum Amount can be 1000 Peso Error Message");
        clearField(StashPage.getByOSType(platform,StashPage.objTargetAmountField), "Target Amount Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objTargetAmountField), updatedTargetAmount, "MoreThan Target Amount");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSetUpYourStashNextButton), "Next button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objYouUpdatedYourStashTitle), "You updated your Stash.");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashSuccessDesc), "Update when you go out with someone who is better looking than you. Thanks for updating.");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashDoneButton), "Success Stash Done");
        swipe("UP", 1);
        groupTargetAmount = getText(StashPage.getByOSType(platform,StashPage.objLastTargetAmount)).split(" ")[3];
        groupTargetAmount = groupTargetAmount.substring(1, groupTargetAmount.indexOf(".")).replaceAll(",", "");
        groupTargetAmountValue = convertStringIntoDouble(groupTargetAmount);
        assertionValidationForDoubleType(updatedTargetAmountValue, groupTargetAmountValue, "updated Target amount of group Stash");
        logger.info("TDB-ST-039,040, Stash - verify User Can Modify Target Amount Of Group Stash_TDB-ST-039,040");
        extentLoggerPass("TDB-ST-039,040", "TDB-ST-039,040, verify User Can Modify Target Amount Of Group Stash_TDB-ST-039,040 is passed");
    }
    /**
     * method to Verify if user can withdraw the amount from Group stash
     * @throws Exception
     */
    public void verifyUserCanWithdrawAmountFromGroupStash() throws Exception {
        HeaderChildNode("TDB-ST-041, Stash -Verify if user can withdraw the amount from Group stash");
        tonikLogin(propertyFileReader("password", "Login"));
        currentAmountValue=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        String validPeso = propertyFileReader("ValidPeso", "Stash");
        stashedAmountValue = convertStringIntoDouble(validPeso);
        stashScreen();
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        selectedSoloGroupStashScreen("0");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objManageStashBtn), "Manage Stash button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objWithdrawToYourTonikAccount), " Withdraw to your TONIK Account");
        withdrawFromYourStashScreen();
        click(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText), "Target amount field");
        type(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText), validPeso, "Target amount field");
        if(platform.equalsIgnoreCase("Android")){
            hideKeyboard();
        }else{
            tapOnScreen(300,150,"screen");
        }
        click(StashPage.getByOSType(platform,StashPage.objWithdrawButton), "Withdraw button");
        reviewWithdrawalScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objConfirmButton), "Confirm button");
        sweetScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objViewDetailsOfHowWeMovedIt), "view Details");
        transactionDetailsValidation(validPeso,propertyFileReader("To","stash"),propertyFileReader("validInput","stash"));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objQuestionMarkIconOnTransactionDetails), "contact customer Icon ");
        verifyElementPresent(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader), "Get In Touch Screen");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objQuestionMarkIconOnTransactionDetails), 10, "contact customer Icon");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSweetDoneButton), "Oh yeah! :done button");
        swipe("UP", 1);
        String groupSatshAmount = getText(StashPage.getByOSType(platform,StashPage.objLastTargetAmount)).split(" ")[1];
        groupSatshAmount = groupSatshAmount.substring(1, groupSatshAmount.indexOf("."));
        groupStashAmountValue = convertStringIntoDouble(groupSatshAmount);
        verifyContentOfGroupStash();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        selectedSoloGroupStashScreen("0");
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitTime(3000);
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        updatedAmountValue=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        assertionValidationForDoubleType(updatedAmountValue, currentAmountValue + stashedAmountValue, "TSA Balance");
        if (updatedAmountValue > currentAmountValue) {
            logger.info("TSA Balance increased based on the amount stashed");
            extentLoggerPass("Stashed Amount", "TSA Balance increased based on the amount stashed");
        }
        swipe("UP", 1);
        stashTileContent();
        swipe("DOWN", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objHistoryIcon), "HistoryIcon");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objNameOfTheStashOnHistory), getTextVal(StashPage.getByOSType(platform,StashPage.objNameOfTheStashOnHistory), ":Name Of The Stash On History"));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), getTextVal(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), ":Transaction Value On History"));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), "Transaction History");
        historyTransactionDetailsScreen();
        logger.info("TDB-ST-041, Stash - verify User Can Withdraw Amount From Group Stash_TDB-ST-041");
        extentLoggerPass("TDB-ST-041", "TDB-ST-041, verify User Can Withdraw Amount From Group Stash_TDB-ST-041 is passed");
    }
    /**
     * method to Verify if user can withdraw amount from Group stash - insufficient balance
     * @throws Exception
     */
    public void verifyUserCanWithdrawMoreThanStashAmountFromGroupStash() throws Exception {
        HeaderChildNode("TDB-ST-043, Stash -Verify if user can withdraw amount from Group stash - insufficient balance");
        tonikLogin(propertyFileReader("password", "Login"));
        stashScreen();
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        selectedSoloGroupStashScreen("0");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objManageStashBtn), "Manage Stash button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objWithdrawToYourTonikAccount), " Withdraw to your TONIK Account");
        withdrawFromYourStashScreen();
        String stashBalance = getText(StashPage.getByOSType(platform,StashPage.objBalanceTxt));
        stashBalance = stashBalance.substring(stashBalance.indexOf("₱") + 1, stashBalance.indexOf("."));
        int stashValue = Integer.parseInt(stashBalance) + 1;
        click(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText), "Target amount field");
        type(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText), String.valueOf(stashValue), "Target amount field");
        if(platform.equalsIgnoreCase("Android")){
            hideKeyboard();
        }else{
            tapOnScreen(300,150,"screen");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objWithdrawButton), "Withdraw button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashAmountError), getTextVal(StashPage.getByOSType(platform,StashPage.objStashAmountError), " inline Error message"));
        if (verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objStashAmountError))) {
            logger.info("TDB-ST-043, Stash - verify User Can Withdraw More Than Stash Amount From Group Stash_TDB-ST-043");
            extentLoggerPass("TDB-ST-043", "TDB-ST-043, Stash -verify User Can Withdraw More Than Stash Amount From Group Stash_TDB-ST-043 is passed");
        }
    }
    /**
     * method to Verify if user can close the Group stash
     * @throws Exception
     */
    public void verifyUserCanCloseGroupStash() throws Exception {
        HeaderChildNode("TDB-ST-044, Stash -Verify if user can close the Group stash");
        tonikLogin(propertyFileReader("password", "Login"));
        currentAmountValue=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        stashScreen();
        String stashesDetails = getText(StashPage.getByOSType(platform,StashPage.obj5Of5AvailableStashes));
        String beforeClosingStash = stashesDetails.substring(0, stashesDetails.indexOf(" "));
        int beforeClosingStashValue = convertStringIntoInt(beforeClosingStash);
        swipe("UP", 1);
        String groupStashAmount = getText(StashPage.getByOSType(platform,StashPage.objLastTargetAmount)).split(" ")[1];
        groupStashAmount = groupStashAmount.substring(1, groupStashAmount.indexOf("."));
        stashedAmountValue = convertStringIntoDouble(groupStashAmount);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        selectedSoloGroupStashScreen("0");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objManageStashBtn), "Manage Stash button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objClose), "Close");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objClose), "Close");
        closePopup();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStayButton), "stay button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objManageStash), "Manage Stash");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objClose), "Close");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objCloseStashButton), "Yes, close the stash");
        youBrokeTheStashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashDoneButton), "Stash Done Button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashHeader), "Stash Header");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objStashHeader), "StashHeader"), "StashHeader", "Stash", "contains");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objStartANewStash), "StartANewStash"), "StartANewStash", "Start a New Stash", "contains");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.obj5Of5AvailableStashes), "5Of5AvailableStashes"), "5Of5AvailableStashes", "Stash", "contains");
        stashesDetails = getText(StashPage.getByOSType(platform,StashPage.obj5Of5AvailableStashes));
        String afterClosingStash = stashesDetails.substring(0, stashesDetails.indexOf(" "));
        int afterClosingStashValue = convertStringIntoInt(afterClosingStash);
        if (Objects.equals(afterClosingStashValue, beforeClosingStashValue + 1)) {
            logger.info("User closed the stash successfully");
            extentLoggerPass("Stash count", "Stash count increase : User closed the stash successfully");
        }
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        updatedAmountValue=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        assertionValidationForDoubleType(updatedAmountValue, currentAmountValue + stashedAmountValue, "TSA Balance");
        if (updatedAmountValue > currentAmountValue) {
            logger.info("TSA Balance increased based on the amount stashed");
            extentLoggerPass("Stashed Amount", "TSA Balance increased based on the amount stashed");
        }
        swipe("UP", 1);
        stashTileContent();
        swipe("DOWN", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objHistoryIcon), "HistoryIcon");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objNameOfTheStashOnHistory), getTextVal(StashPage.getByOSType(platform,StashPage.objNameOfTheStashOnHistory), ":Name Of The Stash On History"));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), getTextVal(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), ":Transaction Value On History"));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), "Transaction History");
        historyTransactionDetailsScreen();
        logger.info("TDB-ST-044, Stash - verify User Can Close Group Stash_TDB-ST-044");
        extentLoggerPass("TDB-ST-044", "TDB-ST-044, verify User Can Close Group Stash_TDB-ST-044 is passed");
    }
    /**
     * Method to verify BKYC user can create the stash and achieve the goal
     * @throws Exception
     */
    public void verifyIfBKYCUserCanCreateTheStashAndAchieveTheGoal_TDB_ST_068() throws Exception {
        HeaderChildNode("TDB_ST_068, Stash -verify If BKYC User Can Create The Stash And Achieve The Goal_TDB_ST_068");
        tonikLogin(propertyFileReader("password", "Login"));
        currentAmountValue=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        swipe("UP", 2);
        if (verifyElementPresent(StashPage.getByOSType(platform,StashPage.objPutYourCashInAStash), "Put Your Cash In Stash")) {
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objPutYourCashInAStash), "PutYourCashInaStash"), "PutYourCashInaStash", "PutYourCashInaStash", "contains");
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objStashes), "StashesOption"), "StashesOption", "StashesOption", "contains");
            valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objSaveAndGrowYourMoney), "SaveAndGrowYourMoney"), "SaveAndGrowYourMoney", "SaveAndGrowYourMoney", "contains");
        }
        click(StashPage.getByOSType(platform,StashPage.objStashes), "StashesOption");
        stashWelcomeScreens();
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashHeader), "Stash Header");
        containsValidation(getText(StashPage.getByOSType(platform,StashPage.objStashHeader)), propertyFileReader("StashHeader", "Stash"), ": Header :Stash");
        containsValidation(getText(StashPage.getByOSType(platform,StashPage.objStartANewStash)), propertyFileReader("StartANewStash", "Stash"), ": StartANewStash");
        containsValidation(getText(StashPage.getByOSType(platform,StashPage.obj5Of5AvailableStashes)), propertyFileReader("5Of5AvailableStashes", "Stash"), ": 5Of5AvailableStashes");
        newStashScreen();
        openANewStashScreen();
        setUpYourStashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashForEditText), "Stash for Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objStashForEditText), propertyFileReader("lessThan", "Stash"), "text_field");
        click(ProfilePage.getByOSType(platform,ProfilePage.objProfilePicture), "Profile Picture");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objPleaseEnterAValidStashName), "Please Enter a valid stash name inline Error Message");
        click(StashPage.getByOSType(platform,StashPage.objNameInputTextField), "Stash for Edit text field");
        clearField(StashPage.getByOSType(platform,StashPage.objNameInputTextField), "Stash for Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objNameInputTextField), propertyFileReader("validInput", "Stash"), "text_field");
        click(ProfilePage.getByOSType(platform,ProfilePage.objProfilePicture), "Profile Picture");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText), "Target Amount Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText), propertyFileReader("LessThanTarget", "Stash"), "LessThan Target Amount ");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSetUpYourStashNextButton), "Next button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objMinimumAmountCanBe), "Minimum Amount can be 1000 Peso Error Message");
        clearField(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText), "Target Amount Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText), propertyFileReader("MoreThanTarget", "Stash"), "MoreThan Target Amount");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSetUpYourStashNextButton), "Next button");
        setInitialSavingScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objIncreaseYourLimitHere), "Increase Your Limit here");
        verifyYourAccountScreen();
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objSkipForNow), 10, "Skip For Now");
        click(StashPage.getByOSType(platform,StashPage.objSkipForNow), "Skip For Now");
        reviewStashDetailsScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTermsAndCondition), "Terms and Condition");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTermsAndConditionScreen), "Terms and Condition Screen");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTermsAndConditionBackIcon), "Back Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objTermsAndConditionCheck), 20, "Terms and Condition");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTermsAndConditionCheck), "Terms and condition Check box");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objCreateStashButtonEnabled), "Create Stash");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSoloStashCreatedLuv), "Solo Stash created Luv");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objSoloStashCreatedLuv), "SoloStashCreatedLuv"), "SoloStashCreatedLuv", "SoloStashCreatedLuv", "contains");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objStashSuccessDescription), "StashSuccessDescription"), "StashSuccessDescription", "StashSuccessDescription", "contains");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objDoneButton), "Done Button");
        newlyCreatedSoloGroupStash();
        addToStashScreenForGroupSolo();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objHowCanIIncreaseMyLimitHere), "Increase Your Limits Here");
        verifyYourAccountScreen();
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objAddToStashHeader), 10, "Add to Stash Header");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        manageStashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objModify), "Back Button");
        modifyStashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objKnowMore), "Know More");
        verifyYourAccountScreen();
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objWithdrawToYourTonikAccount), 10, " Withdraw to your TONIK Account");
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        click(StashPage.getByOSType(platform,StashPage.objWithdrawToYourTonikAccount), " Withdraw to your TONIK Account");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objWithdrawFromStashTitle), "Withdraw from your Stash");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objClose), "Close");
        closePopup();
        click(StashPage.getByOSType(platform,StashPage.objStayButton), "Stay Button On popup");
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.obj5Of5AvailableStashes), 10, "Available stashes");
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        updatedAmountValue=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        assertionValidationForDoubleType(updatedAmountValue, currentAmountValue + stashedAmountValue, "TSA Balance");
        if (Objects.equals(currentAmountValue, updatedAmountValue)) {
            logger.info("TSA Balance is remain Same");
            extentLoggerPass("TDB-ST-068", " TSA Balance is remains same");
        }
        swipe("UP", 1);
        stashTileContent();
        swipe("DOWN", 2);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objHistoryIcon), "HistoryIcon");
    }
    /**
     * method to Verify if user can achieve the goal for Group Stash
     * @throws Exception
     */
    public void verifyUserCanAchieveGoalForGroupStash() throws Exception {
        HeaderChildNode("TDB-ST-045, Stash -Verify if user can achieve the goal for Group Stash");
        tonikLogin(propertyFileReader("password", "Login"));
        currentAmount = getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)).replace("₱", "").replace(",", "");
        currentAmountValue=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        swipe("UP", 1);
        String initialTileAmount = getText(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceOnDashBoard)).replace("₱", "").replace(",", "");
        double initialTileAmountValue = convertStringIntoDouble(initialTileAmount);
        stashScreen();
        navigateToSetupStashPage("Open a new Stash", "Group Stash");
        createStash(1001, 0, propertyFileReader("validInput", "stash"));
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objStashCloseButton), 10, "close button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashCloseButton), "close button");
        swipe("UP", 1);
        String StashAmount = getText(StashPage.getByOSType(platform,StashPage.objLastTargetAmount)).split(" ")[1];
        StashAmount = StashAmount.substring(1, StashAmount.indexOf("."));
        stashedAmountValue = convertStringIntoDouble(StashAmount);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objAddToStashBtn), "Add to Stash button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), "Add to stash Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), currentAmount, "Full Amount Input Text Field");
        if(platform.equalsIgnoreCase("Android")){
            hideKeyboard();
        }else{
            tapOnScreen(300,150,"screen");
        }
        waitTime(3000);
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objNextButton),10,"Next button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNextButton), "Next button");
        waitTime(3000);
        confirmTransferToStashScreen(currentAmount);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objConfirmButtonOnConfirmTransferToStash), "Confirm button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objYouAdded), 10, "Money Stashed");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objMoneyStashed), "MoneyStashed"), "MoneyStashed", "MoneyStashed", "contains");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objYouAdded), "You Added");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objViewDetailsOfHowWeMovedIt), "View Details How We Moved It");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSweetDoneButton), "Money Stashed Done button");
        waitTime(3000);
        swipe("UP", 3);
        soloGroupStashScreenAfterFullAmountAdded();
        String updatedStashAmount = getText(StashPage.getByOSType(platform,StashPage.objLastTargetAmount)).split(" ")[1];
        updatedStashAmount = updatedStashAmount.replace(",","").replace("₱","");
        double updatedStashedAmountValue = convertStringIntoDouble(updatedStashAmount);
        double currentAmountValue = convertStringIntoDouble(currentAmount);
        assertionValidationForDoubleType(updatedStashedAmountValue, currentAmountValue, "TSA Balance");
        if (updatedAmountValue > currentAmountValue) {
            logger.info("TSA Balance changed based on the amount added ");
            extentLoggerPass("Stashed Amount", "TSA Balance changed based on the amount added");
        }
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        String updatedAmount = getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)).replace("₱", "").replace(",", "");
        updatedAmountValue = convertStringIntoDouble(updatedAmount);
        if (Objects.equals(updatedAmount, "0.00")) {
            logger.info("amount reduced based on the amount stashed");
            extentLoggerPass("Stashed Amount", "amount reduced based on the amount stashed");
        }
        swipe("UP", 1);
        stashTileContentAfterFullAmountAdded();
        double updatedTileAmountValue=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        if (updatedTileAmountValue > initialTileAmountValue) {
            logger.info("Stash Balance is increased based on the amount added ");
            extentLoggerPass("Stashed Amount", "Stash Balance is increased based on the amount added ");
        }
        swipe("DOWN", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objHistoryIcon), "HistoryIcon");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objNameOfTheStashOnHistory), getTextVal(StashPage.getByOSType(platform,StashPage.objNameOfTheStashOnHistory), ":Name Of The Stash On History"));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), getTextVal(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), ":Transaction Value On History"));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), "Transaction History");
        historyTransactionDetailsScreen();
        logger.info("TDB-ST-045, Stash - verify User Can Achieve Goal For Group Stash_TDB-ST-045");
        extentLoggerPass("TDB-ST-045", "TDB-ST-045, verify User Can Achieve Goal For Group Stash_TDB-ST-045 is passed");
    }
    /**
     * method to Verify if user can convert a stash to Time Deposit for Group Stash
     * @throws Exception
     */
    public void verifyUserCanConvertStashToTimeDepositForGroupStash() throws Exception {
        HeaderChildNode("TDB-ST-046, Stash -Verify if user can convert a stash to Time Deposit for Group Stash");
        tonikLogin(propertyFileReader("password", "Login"));
        stashScreen();
        String stashesDetails = getText(StashPage.getByOSType(platform,StashPage.obj5Of5AvailableStashes));
        String beforeClosingStash = stashesDetails.substring(0, stashesDetails.indexOf(" "));
        int beforeClosingStashValue = convertStringIntoInt(beforeClosingStash);
        swipe("UP", 1);
        String achievedAmounts = getText(StashPage.getByOSType(platform,StashPage.objLastTargetAmount));
        groupStashAmount = achievedAmounts.split(" ")[1];
        groupTargetAmount = achievedAmounts.split(" ")[3];
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        groupStashGoalAchievedScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objGoalAchievedNextButton), "What's your next step?");
        goalAchievedNextStepPopup();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objBackToStashText), "Back to Stash");
        selectedSoloGroupStashScreen("0");
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objGoalAchievedNextButton), "What's your next step?");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objConvertToTimeDepositText), "Convert to Time Deposit");
        convertStashToTDPopup();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLeaveItAsButton), "Leave it as is");
        selectedSoloGroupStashScreen("0");
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objGoalAchievedNextButton), "What's your next step?");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objConvertToTimeDepositText), "Convert to Time Deposit");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objCloseStashTDButton), "Close Stash");
        youBrokeTheStashScreenForGoalAchieved();
        if(platform.equalsIgnoreCase("Android")){
            verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objCloseStashLaterButton), "Close Stash Later");
        }else{
            tapOnScreen(200,760,"Later link");
        }
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashHeader), "Stash Header");
        stashesDetails = getText(StashPage.getByOSType(platform,StashPage.obj5Of5AvailableStashes));
        String afterClosingStash = stashesDetails.substring(0, stashesDetails.indexOf(" "));
        int afterClosingStashValue = convertStringIntoInt(afterClosingStash);
        if (Objects.equals(afterClosingStashValue, beforeClosingStashValue + 1)) {
            logger.info("User closed the stash successfully");
            extentLoggerPass("Stash count", "Stash count increase : User closed the stash successfully");
        }
        navigateToSetupStashPage("Open a new Stash", "Group Stash");
        createStash(1001, 0, propertyFileReader("validInput", "stash"));
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objStashCloseButton), 10, "close button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashCloseButton), "close button");
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objAddToStashBtn), "Add to Stash button");
        String stashBalance = getText(StashPage.getByOSType(platform,StashPage.objStashBalance)).split(" ")[1].replace("₱", "").replace(",", "");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), "Add to stash Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), stashBalance, "Full Amount Input Text Field");
        if(platform.equalsIgnoreCase("Android")){
            hideKeyboard();
        }else{
            tapOnScreen(300,150,"screen");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNextButton), "Next button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objConfirmButtonOnConfirmTransferToStash), "Confirm button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objYouAdded), 10, "Money Stashed");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objMoneyStashed), "MoneyStashed"), "MoneyStashed", "MoneyStashed", "contains");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objYouAdded), "You Added");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objViewDetailsOfHowWeMovedIt), "View Details How We Moved It");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSweetDoneButton), "Money Stashed Done button");
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objGoalAchievedNextButton), "What's your next step?");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objConvertToTimeDepositText), "Convert to Time Deposit");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objCloseStashTDButton), "Close Stash");
        youBrokeTheStashScreenForGoalAchieved();
        if(platform.equalsIgnoreCase("Android")){
            verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStartATDButton), "Sart a Time Deposit");
        }else{
            tapOnScreen(200,700,"screen");
        }
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objHowMuchInvestHeader), "How much will you invest");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTDCalculatorNextButton), "I am interested");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSetupTDHeader), "Set up a Time Deposit");
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSetupTDToggleButton), "I accept Terms and Conditions");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSetUpTDNextButton), "Let's open this Time Deposit");
        waitTime(5000);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objWootDoneButton), "Woot Done Button");
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        stashScreen();
        stashesDetails = getText(StashPage.getByOSType(platform,StashPage.obj5Of5AvailableStashes));
        String afterClosingStash2 = stashesDetails.substring(0, stashesDetails.indexOf(" "));
        int afterClosingStashValue2 = convertStringIntoInt(afterClosingStash2);
        if (Objects.equals(afterClosingStashValue2, beforeClosingStashValue + 1)) {
            logger.info("User closed the stash successfully");
            extentLoggerPass("Stash count", "Stash count increase : User closed the stash successfully");
        }
        logger.info("TDB-ST-046, Stash - verify User Can Convert Stash To Time Deposit For GroupStash_TDB-ST-046");
        extentLoggerPass("TDB-ST-046", "TDB-ST-046, verify User Can Convert Stash To Time Deposit For Group Stash_TDB-ST-046 is passed");
    }
    /**
     * method to Verify if user can set a higher goal for achieved goal in Group Stash
     * @throws Exception
     */
    public void VerifyUserCanSetHigherGoalForAchievedGoalGroupStash() throws Exception {
        HeaderChildNode("TDB-ST-047, Stash -Verify if user can set a higher goal for achieved goal in Group Stash");
        tonikLogin(propertyFileReader("password", "Login"));
        currentAmount = getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)).replace("₱", "").replace(",", "");
        stashedAmountValue=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        stashScreen();
        navigateToSetupStashPage("Open a new Stash", "Group Stash");
        createStash(1001, 0, propertyFileReader("validInput", "stash"));
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objStashCloseButton), 10, "close button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashCloseButton), "close button");
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objAddToStashBtn), "Add to Stash button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), "Add to stash Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), currentAmount, "Full Amount Input Text Field");
        if(platform.equalsIgnoreCase("Android")){
            hideKeyboard();
        }else{
            tapOnScreen(300,150,"screen");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNextButton), "Next button");
        confirmTransferToStashScreen(currentAmount);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objConfirmButtonOnConfirmTransferToStash), "Confirm button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objYouAdded), 10, "Money Stashed");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objMoneyStashed), "MoneyStashed"), "MoneyStashed", "MoneyStashed", "contains");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objYouAdded), "You Added");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objViewDetailsOfHowWeMovedIt), "View Details How We Moved It");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSweetDoneButton), "Money Stashed Done button");
        swipe("UP", 1);
        String achievedAmounts = getText(StashPage.getByOSType(platform,StashPage.objLastTargetAmount));
        groupStashAmount = achievedAmounts.split(" ")[1];
        groupTargetAmount = achievedAmounts.split(" ")[3];
        String stashAmount = groupStashAmount.substring(1, groupStashAmount.indexOf(".")).replace(",", "");
        int groupStashAmountValue = convertStringIntoInt(stashAmount);
        int lessThanStashAmount = groupStashAmountValue - 1;
        int moreThanStashAmount = groupStashAmountValue + 1;
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        groupStashGoalAchievedScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objGoalAchievedNextButton), "What's your next step?");
        goalAchievedNextStepPopup();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSetAHigherGoalText), "Set a higher goal");
        modifyStashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTargetAmountField), "Target Amount Edit text field");
        clearField(StashPage.getByOSType(platform,StashPage.objTargetAmountField), "Target Amount Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objTargetAmountField), String.valueOf(lessThanStashAmount), "LessThan Target Amount ");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSetUpYourStashNextButton), "Next button");
        click(ProfilePage.getByOSType(platform,ProfilePage.objProfilePicture), "Profile Picture");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashAmountError), "Amount cannot be less than or equal to Stash balance");
        clearField(StashPage.getByOSType(platform,StashPage.objTargetAmountField), "Target Amount Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objTargetAmountField), String.valueOf(moreThanStashAmount), "MoreThan Target Amount");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSetUpYourStashNextButton), "Next button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objYouUpdatedYourStashTitle), "You updated your Stash.");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashSuccessDesc), "Update when you go out with someone who is better looking than you. Thanks for updating.");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashDoneButton), "Success Stash Done");
        swipe("UP", 1);
        if (verifyElementDisplayed(StashPage.getByOSType(platform, StashPage.objGoalAchievedTxt))) {
            logger.error("Goal achieved text is displayed");
            extentLoggerFail("Goal achieved", "Goal achieved text is displayed");
        } else {
            logger.info("Goal achieved text is not displayed after set higher Goal");
            extentLoggerPass("Higher goal set", "Goal achieved text is not displayed after set higher Goal");
        }
        String updatedTargetAmount = getText(StashPage.getByOSType(platform,StashPage.objLastTargetAmount)).split(" ")[3];
        updatedTargetAmount = updatedTargetAmount.substring(1, updatedTargetAmount.indexOf(".")).replace(",", "");
        updatedTargetAmountValue = convertStringIntoDouble(updatedTargetAmount);
        assertionValidationForDoubleType(moreThanStashAmount, updatedTargetAmountValue, "Target Amount for group Stash");
        logger.info("TDB-ST-047, Stash - Verify User Can Set Higher Goal For Achieved Goal Group Stash_TDB-ST-047");
        extentLoggerPass("TDB-ST-047", "TDB-ST-047, Verify User Can Set Higher Goal For Achieved Goal Group Stash_TDB-ST-047 is passed");
    }
    /**
     * method to Verify if TSA balance is debited twice if Confirm button is clicked twice while adding fund in Group stash
     * @throws Exception
     */
    public void verifyTSABalanceDebitedTwiceIfConfirmButtonClickedTwiceWhileAddingFundInGroupStash() throws Exception {
        HeaderChildNode("TDB-ST-034, Stash -Verify if TSA balance is debited twice if Confirm button is clicked twice while adding fund in Group stash");
        tonikLogin(propertyFileReader("password", "Login"));
        currentAmountValue=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        String stashedAmount = propertyFileReader("ValidPeso", "Stash");
        stashedAmountValue = convertStringIntoDouble(stashedAmount);
        stashScreen();
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        selectedSoloGroupStashScreen("0");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objAddToStashBtn), "Add to Stash button");
        click(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), "Stash Amount Input Text Field");
        clearField(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), "Target Amount Edit text field");
        type(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), stashedAmount, "Valid Peso");
        if(platform.equalsIgnoreCase("Android")){
            hideKeyboard();
        }else{
            tapOnScreen(300,150,"screen");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNextButton), "Next button");
        confirmTransferToStashScreen(stashedAmount);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objConfirmButtonOnConfirmTransferToStash), "Confirm button");
        click(StashPage.getByOSType(platform,StashPage.objConfirmButtonOnConfirmTransferToStash), "Confirm button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objYouAdded), 10, "Money Stashed");
        valueValidation(getTextVal(StashPage.getByOSType(platform,StashPage.objMoneyStashed), "MoneyStashed"), "MoneyStashed", "MoneyStashed", "contains");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objYouAdded), "You Added");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objDoneButton), "Done Button");
        waitTime(3000);
        waitForElementToBePresent(ProfilePage.getByOSType(platform,ProfilePage.objBackIcon), 10, "Back Icon");
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        updatedAmountValue=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        assertionValidationForDoubleType(updatedAmountValue, currentAmountValue - stashedAmountValue, "TSA Balance");
        if (Objects.equals(updatedAmountValue, currentAmountValue - stashedAmountValue)) {
            logger.info("Stashed amount debited only once from TSA balance");
            extentLoggerPass("Stashed Amount", "Stashed amount debited only once from TSA balance");
        } else if (Objects.equals(updatedAmountValue, currentAmountValue - stashedAmountValue * 2)) {
            logger.error("Stashed amount debited twice from TSA balance");
            extentLoggerPass("Stashed Amount", "Stashed amount debited twice from TSA balance");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objHistoryIcon), "HistoryIcon");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objNameOfTheStashOnHistory), getTextVal(StashPage.getByOSType(platform,StashPage.objNameOfTheStashOnHistory), ":Name Of The Stash On History"));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), getTextVal(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), ":Transaction Value On History"));
        logger.info("TDB-ST-034, Stash - verify TSA Balance Debited Twice If Confirm Button Clicked Twice While Adding Fund In Group Stash-ST-034");
        extentLoggerPass("TDB-ST-034", "TDB-ST-034, Stash -verifyTSABalanceDebitedTwiceIfConfirmButtonClickedTwiceWhileAddingFundInGroupStash-ST-034 is passed");
    }
    /**
     * method to Verify if TSA balance is credited twice if Confirm button is clicked twice while withdrawing fund in Group stash
     * @throws Exception
     */
    public void verifyTSABalanceCreditedTwiceIfConfirmButtonClickedTwiceWhileWithdrawingFundInGroupStash() throws Exception {
        HeaderChildNode("TDB-ST-042, Stash -Verify if TSA balance is credited twice if Confirm button is clicked twice while withdrawing fund in Group stash");
        tonikLogin(propertyFileReader("password", "Login"));
        currentAmountValue=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        String validPeso = propertyFileReader("ValidPeso", "Stash");
        stashedAmountValue = convertStringIntoDouble(validPeso);
        stashScreen();
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        selectedSoloGroupStashScreen("0");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objManageStashBtn), "Manage Stash button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objWithdrawToYourTonikAccount), " Withdraw to your TONIK Account");
        withdrawFromYourStashScreen();
        click(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText), "Target amount field");
        type(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText), validPeso, "Target amount field");
        if(platform.equalsIgnoreCase("Android")){
            hideKeyboard();
        }else{
            tapOnScreen(300,150,"screen");
        }
        click(StashPage.getByOSType(platform,StashPage.objWithdrawButton), "Withdraw button");
        reviewWithdrawalScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objConfirmButton), "Confirm button");
        click(StashPage.getByOSType(platform,StashPage.objConfirmButton), "Confirm button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSweetDoneButton), "Oh yeah! :done button");
        waitTime(3000);
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        updatedAmountValue=this.getAvailableBalanceInteger(getText(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard)));
        assertionValidationForDoubleType(updatedAmountValue, currentAmountValue + stashedAmountValue, "TSA Balance");
        if (Objects.equals(updatedAmountValue, currentAmountValue + stashedAmountValue)) {
            logger.info("Un-stash amount credited only once to TSA balance");
            extentLoggerPass("Stashed Amount", "Un-stash amount credited only once to TSA balance");
        } else if (Objects.equals(updatedAmountValue, currentAmountValue + stashedAmountValue * 2)) {
            logger.error("Un-stash amount credited twice to TSA balance");
            extentLoggerPass("Stashed Amount", "Un-stash amount credited twice to TSA balance");
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objHistoryIcon), "HistoryIcon");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objNameOfTheStashOnHistory), getTextVal(StashPage.getByOSType(platform,StashPage.objNameOfTheStashOnHistory), ":Name Of The Stash On History"));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), getTextVal(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), ":Transaction Value On History"));
        logger.info("TDB-ST-042, Stash - verify TSA Balance Credited Twice If Confirm Button Clicked Twice While Withdrawing Fund In GroupStash_TDB-ST-042");
        extentLoggerPass("TDB-ST-042", "TDB-ST-042, verify TSA Balance Credited Twice If Confirm Button Clicked Twice While Withdrawing Fund In Group Stash_TDB-ST-042 is passed");
    }
    /**
     * method toverify user can send invite to other Tonik customer
     * @throws Exception
     */
    public void verifyUserCanSendAnInviteToOtherTonikCustomer_TDB_ST_054() throws Exception {
        HeaderChildNode("TDB-ST-054, Stash -verify User Can Send An Invite To Other Tonik Customer_TDB_ST_054");
        tonikLogin(propertyFileReader("password", "Login"));
        stashScreen();
        swipe("UP", 1);
        if (verifyElementDisplayed(StashPage.getByOSType(platform, StashPage.objLastStashName))) {
            click(StashPage.getByOSType(platform, StashPage.objLastStashName), "Stash name");
        } else {
            navigateToSetupStashPage("Open a new Stash", "Group Stash");
            createStash(1001, 0, propertyFileReader("validInput", "stash"));
            waitForElementToBePresent(StashPage.getByOSType(platform, StashPage.objStashCloseButton), 10, "close button");
            verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objStashCloseButton), "close button");
            swipe("UP", 3);
            verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objLastStashName), "Stash Name on the image");
        }
        selectedSoloGroupStashScreen("0");
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objManageSaversText), "Manage Savers");
        ManageSaversScreen();
        click(StashPage.getByOSType(platform, StashPage.objInviteMoreSavers), "Invite More savers");
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objSearchInput), "Search input");
        type(StashPage.getByOSType(platform, StashPage.objSearchInput), propertyFileReader("SearchedName", "stash"), "Search name input");
        verifyElementPresent(StashPage.getByOSType(platform, StashPage.objSearchedRelatedContact), "Search input");
        clearField(StashPage.getByOSType(platform, StashPage.objSearchInput), "Search contact Name Text field");
        if (platform.equalsIgnoreCase("Android")) {
            hideKeyboard();
        } else {
            click(StashPage.getByOSType(platform, StashPage.objKeyboardDoneButton), "Keyboard done button");
        }
        verifyElementPresent(StashPage.getByOSType(platform, StashPage.objInviteToYourStashText), "Invite to your Stash");
        if (platform.equalsIgnoreCase("Android")) {
            click(StashPage.getByOSType(platform, StashPage.objSelectContactButton_1), "contact 1");
        }
        verifyElementPresent(StashPage.getByOSType(platform, StashPage.objSendInviteButtonDisabled), "Send Invite Button Disabled");
        click(StashPage.getByOSType(platform, StashPage.objSelectContactButton_2), "contact 2");
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objInviteFriendButton), "Send Invite via SMS");
        verifyElementPresent(StashPage.getByOSType(platform, StashPage.objMessageApp), "message app");
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objSendButton), "send button for message");
        verifyElementPresent(StashPage.getByOSType(platform, StashPage.objSelectedContact), "Selected Contact");
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objGoBackToStashText), "Go back to stash");
        waitForElementToBePresent(StashPage.getByOSType(platform, StashPage.objStashHeader), 10, "Stash Header");
        verifyElementPresent(StashPage.getByOSType(platform, StashPage.objStashHeader), "Stash Header");
        swipe("UP", 2);
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objLastStashName), "Stash Name on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objManageSaversText), "Manage Savers");
        verifyElementPresentAndClick(StashPage.getByOSType(platform, StashPage.objFirstMember), "Group member");
        waitForElementToBePresent(StashPage.getByOSType(platform, StashPage.objTotalContributedAmount), 10, "Total Contributed Amount");
        verifyElementPresent(StashPage.getByOSType(platform, StashPage.objSelectedMemberProfileName), "Selected profile name");
        String selectedProfile = getText(StashPage.getByOSType(platform, StashPage.objSelectedMemberProfileName));
        if (accountHolderName.equalsIgnoreCase(selectedProfile)) {
            logger.info("Selected Profile name is displayed");
            extentLoggerPass("profile Name", "Selected Profile name is displayed");
        } else {
            logger.info("Selected Profile name is not displayed");
            extentLoggerFail("profile Name", "Selected Profile name is not displayed");
        }
        verifyElementPresent(StashPage.getByOSType(platform, StashPage.objTotalContributedAmount), "Total Contributed Amount");
        String amount = getText(StashPage.getByOSType(platform, StashPage.objTotalContributedAmount));
        String totalContributed = amount.replace("₱", "");
        if (Objects.equals(contributedAmount, totalContributed)) {
            logger.info("Selected Profile contributed amount is same");
            extentLoggerPass("profile Name", "Selected Profile contributed amount is same");
        } else {
            logger.info("Selected Profile contributed amount is not same");
            extentLoggerFail("profile Name", "Selected Profile contributed amount is not same");
        }
        verifyElementPresentAndClick(LoginPage.getByOSType(platform, LoginPage.objBackIcon), "Back Button");
        if (verifyElementDisplayed(StashPage.getByOSType(platform, StashPage.objSaversHeader))) {
            logger.info("TDB-ST-054, Stash -verify User Can Send An Invite To Other Tonik Customer_TDB_ST_054");
            extentLoggerPass("TDB-ST-054", "TDB-ST-054, verify User Can Send An Invite To Other Tonik Customer_TDB_ST_054 is passed");
        }
    }
    /**
     * Method to verify user can resend the invite
     * @throws Exception
     */
    public void VerifyIfUserCanResendTheInvite_TDB_ST_055() throws Exception {
        HeaderChildNode("TDB-ST-055, Stash -verify User Can ReSend An Invite_TDB_ST_055");
        tonikLogin(propertyFileReader("password", "Login"));
        swipe("UP", 2);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader), "Stash Header");
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        selectedSoloGroupStashScreen("0");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objManageSaversText), "Manage Savers");
        ManageSaversScreen();
        click(StashPage.getByOSType(platform,StashPage.objInviteMoreSavers), "Invite More savers");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSearchInput), "Search input");
        type(StashPage.getByOSType(platform,StashPage.objSearchInput), propertyFileReader("SearchedName", "stash"), "Search name input");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSearchedRelatedContact), "Search input");
        clearField(StashPage.getByOSType(platform,StashPage.objSearchInput), "Search contact Name Text field");
        if(platform.equalsIgnoreCase("Android")) {
            hideKeyboard();
        }else{
            click(StashPage.getByOSType(platform,StashPage.objKeyboardDoneButton),"Keyboard done button");
        }
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objInviteToYourStashText), "Invite to your Stash");
        if(platform.equalsIgnoreCase("Android")) {
            click(StashPage.getByOSType(platform, StashPage.objSelectContactButton_1), "contact 1");
        }
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSendInviteButtonDisabled), "Send Invite Button Disabled");
        click(StashPage.getByOSType(platform,StashPage.objSelectContactButton_2), "contact 2");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objInviteFriendButton), "Send Invite via SMS");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objMessageApp), "message app");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSendButton), "send button for message");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSelectedContact), "Selected Contact");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objGoBackToStashText), "Go back to stash");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objStashHeader), 10, "Stash Header");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashHeader), "Stash Header");
        swipe("UP",2);
        Wait(3000);
        click(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objManageSaversText), "Manage Savers");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objPendingStatusMember), "Pending Status Member");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objResendInvitationPopup), "Resend Invitation Popup");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objRemoveFromSaverListButton), "RemoveFrom Saver List button on Popup");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objResendInvitationButton), "Resend Invitation button on Popup");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objWeSent), getTextVal(StashPage.getByOSType(platform,StashPage.objWeSent), ":We sent"));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objWeSentDescription),getTextVal(StashPage.getByOSType(platform,StashPage.objWeSentDescription),"We send Description"));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objDoneButton), "Done Button");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objSaversHeader), 10, "Savers Header");
        if (verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objSaversHeader))) {
            logger.info("TDB-ST-055, Stash -verify User Can ReSend An Invite_TDB_ST_055");
            extentLoggerPass("TDB-ST-055", "TDB-ST-055, verify User Can ReSend An Invite_TDB_ST_055 is passed");
        }
    }
    /**
     * method to Verify if user can invite more members
     * @throws Exception
     */
    public void verifyUserCanInviteMoreMembers_TDB_ST_056() throws Exception {
        HeaderChildNode("TDB_ST_056, Verify if user can invite more members_TDB_ST_056");
        tonikLogin(propertyFileReader("password", "Login"));
        verticalSwipeTillElementDisplayed(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader), "Stash Header");
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        selectedSoloGroupStashScreen("0");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objManageSaversText), "Manage Savers");
        ManageSaversScreen();
        click(StashPage.getByOSType(platform,StashPage.objInviteMoreSavers), "Invite More savers");
        inviteToYourStashScreen();
        verifyElementPresentAndClick(StashPage.objContact(platform,0), "Mobile Number");
        verifyElementDisabled(StashPage.getByOSType(platform,StashPage.objSendInviteButtonDisabled), "Send Invite via SMS Button");
        verifyElementPresentAndClick(StashPage.objContact(platform,1), "Mobile Number");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objInviteFriendButton), "Send Invite via SMS");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objMessageApp), "message app");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSendButton), "send button for message");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSelectedContacts), "Selected Contact");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objGoBackToStashText), "Go back to stash");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objStashHeader), 10, "Stash Header");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashHeader), "Stash Header");
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objManageSaversText), "Manage Savers");
        click(StashPage.getByOSType(platform,StashPage.objInviteMoreSavers), "Invite More savers");
        verifyElementPresentAndClick(StashPage.objContact(platform,1), "Mobile Number");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objInviteFriendButton), "Send Stash Invite via SMS");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSendButton), "send button for message");
        List<WebElement> allContacts = findElements(StashPage.getByOSType(platform,StashPage.objContactNames));
        for(WebElement contacts : allContacts) {
            if(contacts.isDisplayed()) {
                String contact = contacts.getText();
                logger.info(contact + " contact name is displayed");
                extentLoggerPass("checkElementPresent", contact + " contact name is displayed");
            }
        }
        if(verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objGoBackToStashText))) {
            logger.info("TDB_ST_056, Stash -verifyUserCanInviteMoreMembers_TDB_ST_056");
            extentLoggerPass("TDB_ST_056", "TDB_ST_056, verifyUserCanInviteMoreMembers_TDB_ST_056 is passed");
        }
    }
    /**
     * method to Verify if user can invite 10 members at a time from Contacts
     * @throws Exception
     */
    public void verifyUserCanInviteTenMembersAtATimeFromContacts_TDB_ST_057() throws Exception {
        HeaderChildNode("TDB_ST_057, Verify if user can invite 10 members at a time from Contacts_TDB_ST_057");
        tonikLogin(propertyFileReader("password", "Login"));
        verticalSwipeTillElementDisplayed(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader), "Stash Header");
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        selectedSoloGroupStashScreen("0");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objManageSaversText), "Manage Savers");
        ManageSaversScreen();
        click(StashPage.getByOSType(platform,StashPage.objInviteMoreSavers), "Invite More savers");
        inviteToYourStashScreen();
        verifyElementPresentAndClick(StashPage.objContact(platform,0), "Contact Number");
        verifyElementDisabled(StashPage.getByOSType(platform,StashPage.objSendInviteButtonDisabled), "Send Invite via SMS Button");
        for (int list = 1; list <= 10; list++) {
            if(list==4 | list==8) {
                swipe("UP", 1);
            }
            if(verifyElementDisplayed(StashPage.objContact(platform,list))) {
                verifyElementPresentAndClick(StashPage.objContact(platform,list), list+"Contact Number");
            }else {
                swipe("UP", 1);
                verifyElementPresentAndClick(StashPage.objContact(platform,list), list+"Contact Number");
            }
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objInviteFriendButton), "Send Stash Invite via SMS");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSendButton), "send button for message");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objGoBackToStashText), "Go back to stash");
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objManageSaversText), "Manage Savers");
        waitForElementDisplayed(StashPage.getByOSType(platform,StashPage.objInviteMoreSavers), 10);
        int count = 0;
        for (int list = 1; list <= 11; list++) {
            if(verifyElementDisplayed(StashPage.objMembersList(platform,list))) {
                count++;
            }else {
                swipe("UP", 1);
                if(verifyElementDisplayed(StashPage.objMembersList(platform,list))) {
                    count++;
                }else {
                    break;
                }
            }
        }
        if(count==10) {
            logger.info(count + " members are displayed");
            extentLoggerPass("checkElementPresent", count + " members are displayed in the saver list");
        }else {
            logger.error(count + " members are displayed");
            extentLoggerPass("checkElementPresent", count + " members are displayed in the saver list");
        }
        logger.info("TDB_ST_057, Stash -verifyUserCanInviteTenMembersAtATimeFromContacts_TDB_ST_057");
        extentLoggerPass("TDB_ST_057", "TDB_ST_057, verifyUserCanInviteTenMembersAtATimeFromContacts_TDB_ST_057 is passed");
    }
    /**
     * method to Verify if user can invite non-Tonik customer
     * @throws Exception
     */
    public void verifyUserCanInviteNonTonikCustomer_TDB_ST_058() throws Exception {
        HeaderChildNode("TDB_ST_058, Verify if user can invite non-Tonik customer_TDB_ST_058");
        tonikLogin(propertyFileReader("password", "Login"));
        verticalSwipeTillElementDisplayed(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader), "Stash Header");
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        selectedSoloGroupStashScreen("0");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objManageSaversText), "Manage Savers");
        ManageSaversScreen();
        click(StashPage.getByOSType(platform,StashPage.objInviteMoreSavers), "Invite More savers");
        inviteToYourStashScreen();
        verifyElementPresentAndClick(StashPage.objContact(platform,0), "Mobile Number");
        verifyElementDisabled(StashPage.getByOSType(platform,StashPage.objSendInviteButtonDisabled), "Send Invite via SMS Button");
        verticalSwipeTillElementDisplayed(StashPage.getByOSType(platform,StashPage.objNonTonikContact));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNonTonikContact), "NonTonik Contact Number");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objInviteFriendButton), "Send Stash Invite via SMS");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSendButton), "send button for message");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objGoBackToStashText), "Go back to stash");
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objManageSaversText), "Manage Savers");
        waitForElementDisplayed(StashPage.getByOSType(platform,StashPage.objInviteMoreSavers), 10);
        if(verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objStashStatus))) {
            logger.error("Invited non-Tonik customer displayed in the Savers list screen");
            extentLoggerFail("checkElementPresent", "Invited non-Tonik customer displayed in the Savers list screen");
        }else {
            logger.info("Invited non-Tonik customer is not displayed in the Savers list screen");
            extentLoggerPass("checkElementPresent", "Invited non-Tonik customer is not displayed in the Savers list screen");
        }
        logger.info("TDB_ST_058, Stash -verifyUserCanInviteNonTonikCustomer_TDB_ST_058");
        extentLoggerPass("TDB_ST_058", "TDB_ST_058, verifyUserCanInviteNonTonikCustomer_TDB_ST_058 is passed");
    }
    /**
     * method to Verify if user can view the Savers list
     * @throws Exception
     */
    public void verifyUserCanViewTheSaversList_TDB_ST_059() throws Exception {
        HeaderChildNode("TDB_ST_059, Verify if user can view the Savers list_TDB_ST_058");
        tonikLogin(propertyFileReader("password", "Login"));
        verticalSwipeTillElementDisplayed(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader), "Stash Header");
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        selectedSoloGroupStashScreen("0");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objManageSaversText), "Manage Savers");
        click(StashPage.getByOSType(platform,StashPage.objInviteMoreSavers), "Invite More savers");
        inviteToYourStashScreen();
        verifyElementPresentAndClick(StashPage.objContact(platform,0), "Mobile Number");
        verifyElementDisabled(StashPage.getByOSType(platform,StashPage.objSendInviteButtonDisabled), "Send Invite via SMS Button");
        verifyElementPresentAndClick(StashPage.objContact(platform,1), "Mobile Number");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objInviteFriendButton), "Send Invite via SMS");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSendButton), "send button for message");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objGoBackToStashText), "Go back to stash");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objStashHeader), 10, "Stash Header");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashHeader), "Stash Header");
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objManageSaversText), "Manage Savers");
        ownerName = getText(StashPage.getByOSType(platform,StashPage.objOwnerName)).toUpperCase();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSelectMember), "Member");
        selectedMemberScreen();
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        if(verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objSaversHeader))) {
            logger.info("TDB_ST_059, Stash -verifyUserCanViewTheSaversList_TDB_ST_059");
            extentLoggerPass("TDB_ST_059", "TDB_ST_059, verifyUserCanViewTheSaversList_TDB_ST_059 is passed");
        }
    }
    /**
     * method to Verify if user can view the contributions added by the member/s of the Group Stash
     * @throws Exception
     */
    public void verifyUserCanViewTheContributionsAddedByMemberOfGroupStash_TDB_ST_061() throws Exception {
        HeaderChildNode("TDB_ST_061, Verify if user can view the contributions added by the member/s of the Group Stash_TDB_ST_061");
        tonikLogin(propertyFileReader("password", "Login"));
        verticalSwipeTillElementDisplayed(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader), "Stash Header");
        waitTime(3000);
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objManageSaversText), "Manage Savers");
        selectedMemberContributionScreen();
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        if(verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objSaversHeader))) {
            logger.info("TDB_ST_061, Stash -verifyUserCanViewTheContributionsAddedByMemberOfGroupStash_TDB_ST_061");
            extentLoggerPass("TDB_ST_061", "TDB_ST_061, verifyUserCanViewTheContributionsAddedByMemberOfGroupStash_TDB_ST_061 is passed");
        }
    }
    /**
     * method to Verify the status of the invited member if invitation is not accepted
     * @throws Exception
     */
    public void verifyStatusOfInvitedMemberIfInvitationIsNotAccepted_TDB_ST_062() throws Exception {
        HeaderChildNode("TDB_ST_062, Verify the status of the invited member if invitation is not accepted_TDB_ST_062");
        tonikLogin(propertyFileReader("password", "Login"));
        verticalSwipeTillElementDisplayed(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader), "Stash Header");
        swipe("UP", 1);
        String stashName = getText(StashPage.getByOSType(platform,StashPage.objLastStashName));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objManageSaversText), "Manage Savers");
        click(StashPage.getByOSType(platform,StashPage.objInviteMoreSavers), "Invite More savers");
        inviteToYourStashScreen();
        verifyElementPresentAndClick(StashPage.objContact(platform,0), "Mobile Number");
        verifyElementDisabled(StashPage.getByOSType(platform,StashPage.objSendInviteButtonDisabled), "Send Invite via SMS Button");
        verifyElementPresentAndClick(StashPage.objContact(platform,1), "Mobile Number");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objInviteFriendButton), "Send Invite via SMS");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSendButton), "send button for message");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objGoBackToStashText), "Go back to stash");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objStashHeader), 10, "Stash Header");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashHeader), "Stash Header");
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objManageSaversText), "Manage Savers");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashStatus), "Pending");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objPopupText)), "Your invitation to "+stashName+" is still pending. Do you want to double down and resend invitation?", "Text");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objResendInvitationText)), propertyFileReader("ResendInvitationText", "stash"), "Text");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objRemoveFromSaverListText)), propertyFileReader("RemoveFromSaverListText", "stash"), "Text");
        tapOnScreen(300, 500, "outside popup");
        waitForElementDisplayed(StashPage.getByOSType(platform,StashPage.objInviteMoreSavers), 10);
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSaversHeader), getTextVal(StashPage.getByOSType(platform,StashPage.objSaversHeader), ":Header"));
        logger.info("TDB_ST_062, Stash -verifyStatusOfInvitedMemberIfInvitationIsNotAccepted_TDB_ST_062");
        extentLoggerPass("TDB_ST_062", "TDB_ST_062, verifyStatusOfInvitedMemberIfInvitationIsNotAccepted_TDB_ST_062 is passed");
    }
    /**
     * method to Verify if user can remove the member
     * @throws Exception
     */
    public void verifyUserCanRemoveTheMember_TDB_ST_063() throws Exception {
        HeaderChildNode("TDB_ST_063, Verify if user can remove the member_TDB_ST_063");
        tonikLogin(propertyFileReader("password", "Login"));
        verticalSwipeTillElementDisplayed(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader), "Stash Header");
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objManageSaversText), "Manage Savers");
        click(StashPage.getByOSType(platform,StashPage.objInviteMoreSavers), "Invite More savers");
        inviteToYourStashScreen();
        verifyElementPresentAndClick(StashPage.objContact(platform,0), "Mobile Number");
        verifyElementDisabled(StashPage.getByOSType(platform,StashPage.objSendInviteButtonDisabled), "Send Invite via SMS Button");
        verifyElementPresentAndClick(StashPage.objContact(platform,1), "Mobile Number");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objInviteFriendButton), "Send Invite via SMS");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSendButton), "send button for message");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objGoBackToStashText), "Go back to stash");
        waitForElementToBePresent(StashPage.getByOSType(platform,StashPage.objStashHeader), 10, "Stash Header");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashHeader), "Stash Header");
        swipe("UP", 1);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLastStashName), "Stash Name on the image");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objManageSaversText), "Manage Savers");
        String memberName = getText(StashPage.getByOSType(platform,StashPage.objMemberName)).toUpperCase();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashStatus), "Pending");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objRemoveFromSaverListBtn), "Remove from saver list");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objYouRemoveText)), "You removed "+memberName+" from your Saver list.", "Text");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objMaybeText)) , "Maybe it just wasn't the right time. You can always invite "+memberName+" again anytime.", "Text");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objDoneText)), "Done", "Text");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objDoneBtn), "Done");
        waitForElementDisplayed(StashPage.getByOSType(platform,StashPage.objInviteMoreSavers), 10);
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objSaversHeader), getTextVal(StashPage.getByOSType(platform,StashPage.objSaversHeader), ":Header"));
        logger.info("TDB_ST_063, Stash -verifyUserCanRemoveTheMember_TDB_ST_063");
        extentLoggerPass("TDB_ST_063", "TDB_ST_063, verifyUserCanRemoveTheMember_TDB_ST_063 is passed");
    }
    /**
     * method to Verify if user can Accept the Stash group invitation
     * @throws Exception
     */
    public void verifyUserCanAcceptTheStashGroupInvitation_TDB_ST_064() throws Exception {
        HeaderChildNode("TDB_ST_064, Verify if user can Accept the Stash group invitation_TDB_ST_064");
        tonikLogin(propertyFileReader("password", "Login"));
        stashAPIModule.groupStashRequest(propertyFileReader("OwnerMobileNumber","stash"));
        verticalSwipeTillElementDisplayed(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader), "Stash Header");
        waitForElementDisplayed(StashPage.getByOSType(platform,StashPage.objStashInviteName), 10);
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashInviteName), getTextVal(StashPage.getByOSType(platform,StashPage.objStashInviteName), "Title"));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashInvitation), "Stash invite");
        String stashName = getText(StashPage.getByOSType(platform,StashPage.objStashName));
        stashInvitationScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTNCAcceptBtn), "I accept the terms and conditions");
        verifyElementEnabled(StashPage.getByOSType(platform,StashPage.objAcceptInvitationBtnEnabled), "Accept Inviation");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objAcceptInvitationBtn), "Accept invitation");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objJoinThisStashHeaderText), getTextVal(StashPage.getByOSType(platform,StashPage.objJoinThisStashHeaderText), "Header"));
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objJoinThisStashText)), propertyFileReader("JoinThisStashText", "stash"), "Text");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objJoinThisStashBtn), "Join This Stash");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objWelcomeToStashText), getTextVal(StashPage.getByOSType(platform,StashPage.objWelcomeToStashText), "Text"));
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objGroupStashRules), getTextVal(StashPage.getByOSType(platform,StashPage.objGroupStashRules), "Group Stash Rules"));
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objContributeNowText)), propertyFileReader("ContributeNowText", "stash"), "Text");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objDoneBtn), "Done button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashHeader), getTextVal(StashPage.getByOSType(platform,StashPage.objStashHeader), "Header"));
        if(verifyElementDisplayed(StashPage.objNewStash(platform,stashName))) {
            logger.info("TDB_ST_064, Stash -verifyUserCanAcceptTheStashGroupInvitation_TDB_ST_064");
            extentLoggerPass("TDB_ST_064", "TDB_ST_064, verifyUserCanAcceptTheStashGroupInvitation_TDB_ST_064 is passed");
        }
    }
    /**
     * method to Verify if user can Decline the Stash group invitation
     * @throws Exception
     */
    public void verifyUserCanDeclineTheStashGroupInvitation_TDB_ST_065() throws Exception {
        HeaderChildNode("TDB_ST_065, Verify if user can Decline the Stash group invitation_TDB_ST_065");
        tonikLogin(propertyFileReader("password", "Login"));
        stashAPIModule.groupStashRequest(propertyFileReader("OwnerMobileNumber","stash"));
        verticalSwipeTillElementDisplayed(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader), "Stash Header");
        waitForElementDisplayed(StashPage.getByOSType(platform,StashPage.objStashInviteName), 10);
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashInviteName), getTextVal(StashPage.getByOSType(platform,StashPage.objStashInviteName), "Title"));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashInvitation), "Stash invite");
        String stashName = getText(StashPage.getByOSType(platform,StashPage.objStashName));
        stashInvitationScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objDeclineInvitationBtn), "Decline button");
        if(verifyElementNotDisplayed(StashPage.objNewStash(platform,stashName),"Stash invite")) {
            logger.info("TDB_ST_065, Stash -verifyUserCanDeclineTheStashGroupInvitation_TDB_ST_065");
            extentLoggerPass("TDB_ST_065", "TDB_ST_065, verifyUserCanDeclineTheStashGroupInvitation_TDB_ST_065 is passed");
        }
    }
    /**
     * method to Verify if user can Contribute the amount to Stash group invitation
     * @throws Exception
     */
    public void verifyUserCanContributeTheAmountToStashGroupInvitation_TDB_ST_066() throws Exception {
        HeaderChildNode("TDB_ST_066, Verify if user can Contribute the amount to Stash group invitation_TDB_ST_066");
        tonikLogin(propertyFileReader("password", "Login"));
        String otp = propertyFileReader("OTP", "stash");
        String amountValue = propertyFileReader("AddToStashAmount", "stash");
        stashedAmountValue = convertStringIntoDouble(amountValue);
        stashAPIModule.groupStashRequest(propertyFileReader("OwnerMobileNumber","stash"));
        verticalSwipeTillElementDisplayed(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader), "Stash Header");
        waitForElementDisplayed(StashPage.getByOSType(platform,StashPage.objStashInviteName), 10);
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashInviteName), getTextVal(StashPage.getByOSType(platform,StashPage.objStashInviteName), "Title"));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashInvitation), "Stash invite");
        stashName = getText(StashPage.getByOSType(platform,StashPage.objStashName));
        stashOwnerName = getText(StashPage.getByOSType(platform,StashPage.objStashOwnerName));
        stashInvitationScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTNCAcceptBtn), "I accept the terms and conditions");
        verifyElementEnabled(StashPage.getByOSType(platform,StashPage.objAcceptInvitationBtnEnabled), "Accept Inviation");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objAcceptInvitationBtn), "Accept invitation");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objJoinThisStashBtn), "Join This Stash");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objContributeNowBtn), "Contribute Now");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objHeader), getTextVal(StashPage.getByOSType(platform,StashPage.objHeader), ":Header"));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), "Add To Stash Input");
        type(StashPage.getByOSType(platform,StashPage.objAddToStashEditText), amountValue, "Add To Stash Input");
        if(platform.equalsIgnoreCase("Android")){
            hideKeyboard();
        }else{
            tapOnScreen(300,150,"screen");
        }
        verifyElementEnabled(StashPage.getByOSType(platform,StashPage.objNextBtnEnabled), "Next Button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objNextBtn), "Next Button");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objPopupHeader)), propertyFileReader("StashContributionInfoText", "stash"), "Text");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objContinueBtn), "Continue");
        confirmTransferToStashScreenForContribution();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashConfirmBtn), "Confirm");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objOtpInputField), "OTP Input");
        if(platform.equalsIgnoreCase("Android")){
            adbInputText(Integer.parseInt(otp));
        }else{
            DriverManager.getAppiumDriver().findElement(StashPage.getByOSType(platform,StashPage.objOtpInputField)).sendKeys(otp);
        }
        moneyStashedScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objViewStashDetailsBtn), "View details of how we moved it.");
        transactionDetailsScreenForContribution();
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objDoneBtn), "Done Button");
        waitForElementDisplayed(StashPage.objNewStash(platform,stashName), 10);
        verifyElementPresentAndClick(StashPage.objNewStash(platform,stashName), "Stash name");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objManageSaversText), "Manage Savers");
        fundContributedMemberScreen();
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementDisplayed(StashPage.getByOSType(platform,StashPage.objContributedMemberName), 10);
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementDisplayed(StashPage.getByOSType(platform,StashPage.objManageSaversText), 10);
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementDisplayed(StashPage.getByOSType(platform,StashPage.objStartANewStash), 10);
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back Button");
        waitForElementDisplayed(StashPage.getByOSType(platform,StashPage.objHistoryIcon), 10);
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objHistoryIcon), "HistoryIcon");
        accountHistoryScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTransactionValueOnHistory), "Transaction History");
        historyTransactionDetailsScreen();
        logger.info("TDB_ST_066, Stash -verifyUserCanContributeTheAmountToStashGroupInvitation_TDB_ST_066");
        extentLoggerPass("TDB_ST_066", "TDB_ST_066, verifyUserCanContributeTheAmountToStashGroupInvitation_TDB_ST_066 is passed");
    }
    /**
     * method to Verify if user can leave from the Group Stash
     * @throws Exception
     */
    public void verifyUserCanLeaveFromTheGroupStash_TDB_ST_067() throws Exception {
        HeaderChildNode("TDB_ST_067, Verify if user can leave from the Group Stash_TDB_ST_067");
        tonikLogin(propertyFileReader("password", "Login"));
        verticalSwipeTillElementDisplayed(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader), "Stash Header");
        waitForElementDisplayed(StashPage.getByOSType(platform,StashPage.objNewStash), 10);
        String stashName = getText(StashPage.getByOSType(platform,StashPage.objNewStash));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objManageStashButton), "Manage Button");
        waitForElementDisplayed(StashPage.getByOSType(platform,StashPage.objLeaveStashText), 10);
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objHeader), getTextVal(StashPage.getByOSType(platform,StashPage.objHeader), ":Header"));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLeaveStashBtn), "Leave Stash");
        leaveStashPopupScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStayButton), "Stay");
        verifyElementPresent(StashPage.objNewStash(platform,stashName), getTextVal(StashPage.objNewStash(platform,stashName), "Stash name"));
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objManageStashButton), "Manage Button");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objLeaveStashBtn), "Leave Stash");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objRemoveFromSaverListButton), "Yes, leave the stash");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objYouHaveLeftText)), propertyFileReader("YouHaveLeftTripText", "stash")+" "+stashName, "Text");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objDropsMicText)), propertyFileReader("DropsMicText", "stash"), "Text");
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objDoneButton), "Done Button");
        if(verifyElementNotDisplayed(StashPage.objNewStash(platform,stashName),"Stash invite")) {
            logger.info("TDB_ST_067, Stash -verifyUserCanLeaveFromTheGroupStash_TDB_ST_067");
            extentLoggerPass("TDB_ST_067", "TDB_ST_067, verifyUserCanLeaveFromTheGroupStash_TDB_ST_067 is passed");
        }
    }
    /**
     * Method to verify user can create emergency group stash
     * @param targetAmount
     * @param initialAmount
     * @param stashName
     * @param stashType
     * @throws Exception
     */
    public void verifyUserCanCreateEmergencyGroupStash_TDB_ST048(int targetAmount, int initialAmount, String stashName, String stashType) throws Exception {
        HeaderChildNode("TDB-ST-048, Stash - Verify if user can create Emergency Group Stash");
        createStashAndVerifyTheStashTileAdHistory(targetAmount, initialAmount, stashName, stashType);
        logger.info("TDB-ST-048, Stash - Verify if user can create Emergency Group Stash");
        extentLoggerPass("TDB-ST-048", "Stash - Verify if user can create Emergency Group Stash");
    }
    /**
     * Method to verify user can create vacation group stash
     * @param targetAmount
     * @param initialAmount
     * @param stashName
     * @param stashType
     * @throws Exception
     */
    public void verifyUserCanCreateVacationGroupStash_TDB_ST049(int targetAmount,int initialAmount, String stashName,String stashType) throws Exception {
        HeaderChildNode("TDB-ST-049, Stash - Verify if user can create Vacation Group Stash");
        createStashAndVerifyTheStashTileAdHistory(targetAmount,initialAmount,stashName,stashType);
        logger.info("TDB-ST-049, Stash - Verify if user can create Vacation Group Stash");
        extentLoggerPass("TDB-ST-049", "Stash - Verify if user can create Vacation Group Stash");
    }
    /**
     * Method to verify user can create tuition group stash
     * @param targetAmount
     * @param initialAmount
     * @param stashName
     * @param stashType
     * @throws Exception
     */
    public void verifyUserCanCreateTuitionGroupStash_TDB_ST050(int targetAmount,int initialAmount, String stashName,String stashType) throws Exception {
        HeaderChildNode("TDB-ST-050, Stash - Verify if user can create Tuition Group Stash");
        createStashAndVerifyTheStashTileAdHistory(targetAmount,initialAmount,stashName,stashType);
        logger.info("TDB-ST-050, Stash - Verify if user can create Tuition Group Stash");
        extentLoggerPass("TDB-ST-050", "Stash - Verify if user can create Tuition Group Stash");
    }
    /**
     * Method to verify user can create new ride group stash
     * @param targetAmount
     * @param initialAmount
     * @param stashName
     * @param stashType
     * @throws Exception
     */
    public void verifyUserCanCreateNewRideGroupStash_TDB_ST051(int targetAmount,int initialAmount, String stashName,String stashType) throws Exception {
        HeaderChildNode("TDB-ST-051, Stash - Verify if user can create New Ride Group Stash");
        createStashAndVerifyTheStashTileAdHistory(targetAmount,initialAmount,stashName,stashType);
        logger.info("TDB-ST-051, Stash - Verify if user can create New Ride Group Stash");
        extentLoggerPass("TDB-ST-051", "Stash - Verify if user can create New Ride Group Stash");
    }
    /**
     * Method to verify user can create game console group stash
     * @param targetAmount
     * @param initialAmount
     * @param stashName
     * @param stashType
     * @throws Exception
     */
    public void verifyUserCanCreateGameConsoleGroupStash_TDB_ST052(int targetAmount,int initialAmount, String stashName,String stashType) throws Exception {
        HeaderChildNode("TDB-ST-052, Stash - Verify if user can create Game Console Group Stash");
        createStashAndVerifyTheStashTileAdHistory(targetAmount,initialAmount,stashName,stashType);
        logger.info("TDB-ST-052, Stash - Verify if user can create Game Console Group Stash");
        extentLoggerPass("TDB-ST-052", "Stash - Verify if user can create Game Console Group Stash");
    }
    /**
     * Method to verify user can create more than 5 group stash
     * @param targetAmount
     * @param initialAmount
     * @param stashName
     * @param stashType
     * @throws Exception
     */
    public void verifyUserCanCreateMoreThan5GroupStashes_TDB_ST053(int targetAmount,int initialAmount, String stashName,String stashType) throws Exception {
        HeaderChildNode("TDB-ST-053, Stash - Verify if user can create more than 5 Group stashes");
        tonikLogin(propertyFileReader("password", "Login"));
        swipe("UP",2);
        if (verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader))) {
            click(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader),"Stash card");
        }else{
            click(StashPage.getByOSType(platform,StashPage.objPutYourCashInAStash),"Stash card");
        }
        while(!getText(StashPage.getByOSType(platform,StashPage.obj5Of5AvailableStashes)).equals("0 of 5 available stashes")){
            verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStartANewStash),getText(StashPage.getByOSType(platform,StashPage.objStartANewStash))+" : Button");
            verifyElementPresentAndClick(StashPage.objAllStashOption(getPlatform(),stashName),getText(StashPage.objAllStashOption(getPlatform(),stashName))+" Stash Option");
            verifySelectStashPopupAndSelectTypeOfStash(stashType);;
            createStash(targetAmount,initialAmount,stashName);
            verifyGroupSoloStashCreatedSuccessMessage();
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStartANewStash),getText(StashPage.getByOSType(platform,StashPage.objStartANewStash))+" : Button");
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objPopupHeader)),"Sorry, you can only have 5 actived Stashes created by yourself at the same time. You can close a Stash and create a new one."," Popup Header");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objPopupOkButton),getText(StashPage.getByOSType(platform,StashPage.objPopupOkButton))+" :Button");
        logger.info("TDB-ST-053, Stash - Verify if user can create more than 5 Group stashes");
        extentLoggerPass("TDB-ST-053", "Stash - Verify if user can create more than 5 Group stashes");
    }
    /**
     * Method to verify user can create solo stash
     * @throws Exception
     */
    public void verifyBKYCUserCanCreateSoloStashForMoreThan50K_TDB_ST_069() throws Exception {
    HeaderChildNode("TDB-ST-069, Stash - Verify if BKYC user can create the solo stash for more than 50K");
    tonikLogin(propertyFileReader("password", "Login"));
    stashScreen();
    newStashScreen();
    openANewStashScreen();
    setUpYourStashScreen();
    verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStashForEditText), "Stash for Edit text field");
    clearField(StashPage.getByOSType(platform,StashPage.objStashForEditText),"Stash for Edit text field");
    type(StashPage.getByOSType(platform,StashPage.objStashForEditText), propertyFileReader("validInput","Stash"),"text_field");
    click(ProfilePage.getByOSType(platform,ProfilePage.objProfilePicture), "Profile Picture");
    verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText), "Target Amount Edit text field");
    clearField(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText),"Target Amount Edit text field");
    type(StashPage.getByOSType(platform,StashPage.objTargetAmountEditText), propertyFileReader("MoreThanTargetAmount","Stash") ,"MoreThan Target Amount");
    verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objSetUpYourStashNextButton), "Next button");
    verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStashTargetAmountErrorForBKYC)," Error Message");
    assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objStashTargetAmountErrorForBKYC)),"You can add only up to ₱50,000.00\n" +
            "Unlock higher limits when you verify your account"," : Error Message");
    logger.info("TDB-ST-069, Stash - Verify if BKYC user can create the solo stash for more than 50K");
    extentLoggerPass("TDB-ST-069", "Stash -Verify if BKYC user can create the solo stash for more than 50K");
}
    /**
     * Method to verify BKYC user can create the group stash
     * @throws Exception
     */
    public void verifyBKYCUserCanCreateTheGroupStash_TDB_ST_070() throws Exception {
        HeaderChildNode("TDB-ST-070, Stash - Verify if BKYC user can create the group Stash");
        tonikLogin(propertyFileReader("password", "Login"));
        swipe("UP",2);
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objPutYourCashInAStash),getText(StashPage.getByOSType(platform,StashPage.objPutYourCashInAStash)));
        click(StashPage.getByOSType(platform,StashPage.objPutYourCashInAStash),"Stash Container");
        newStashScreen();
        openANewStashScreen();
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objGroupStashOnPopup), "Select Group Stash on pop up");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objPopupHeader),getText(StashPage.getByOSType(platform,StashPage.objPopupHeader)));
        assertionValidation(getText(StashPage.getByOSType(platform,StashPage.objPopupHeader)),propertyFileReader("WantToOpenMoreStashPopupMessage","Stash")," : Popup Message");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objVerifyAccountNowButton),getText(StashPage.getByOSType(platform,StashPage.objVerifyAccountNowButton))+" Button");
        click(StashPage.getByOSType(platform,StashPage.objVerifyAccountNowButton),getText(StashPage.getByOSType(platform,StashPage.objVerifyAccountNowButton))+" Button");
        verifyElementPresent(StashPage.getByOSType(platform,StashPage.objStepsToVerifyYourAccountPageHeader),getText(StashPage.getByOSType(platform,StashPage.objStepsToVerifyYourAccountPageHeader))+" Page Title");
        logger.info("TDB-ST-070, Stash - Verify if BKYC user can create the group Stash");
        extentLoggerPass("TDB-ST-070", "Stash -Verify if BKYC user can create the group Stash");
    }
    /**
     * Method to verify user can able to create two maximum stashes
     * @param targetAmount
     * @param initialAmount
     * @param stashNAme
     * @throws Exception
     */
    public void verifyBKYCUserCanOnlyCreateMaximum2SoloStashes_TBD_PST_0071(String targetAmount,String initialAmount, String stashNAme) throws Exception {
        HeaderChildNode("TBD_PRO_0071, Stash - Verify if BKYC user can only create a maximum of 2 solo stashes");
        tonikLogin(propertyFileReader("password", "Login"));
        int targetAmountForBKYC = convertStringIntoInt(targetAmount);
        int initialAmountForBKYC = convertStringIntoInt(initialAmount);
        swipe("UP",2);
        if (verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader))) {
            click(StashPage.getByOSType(platform,StashPage.objTotalStashBalanceHeader),"Stash card");
        }else{
            click(StashPage.getByOSType(platform,StashPage.objPutYourCashInAStash),"Stash card");
        }
        waitTime(2000);
        while (!getText(StashPage.getByOSType(platform,StashPage.objAvailableStashText)).equals("0 of 2 available stashes")){
            verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStartANewStash), "Start a New Stash");
            verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objOpenANewStash), "Open a New Stash");
            click(StashPage.getByOSType(platform,StashPage.objSoloStashOnPopup),getText(StashPage.getByOSType(platform,StashPage.objSoloStashOnPopup))+" : option");
            createStash(targetAmountForBKYC,initialAmountForBKYC,stashNAme);
            verifySoloStashCreatedSuccessMessage();
            newlyCreatedSoloGroupStash();
        }
        verifyElementPresentAndClick(StashPage.getByOSType(platform,StashPage.objStartANewStash), "Start a New Stash");
        logger.info("TBD_PRO_0071, Stash - Verify if BKYC user can only create a maximum of 2 solo stashes");
        extentLoggerPass("TBD_PRO_0071", "Verify if BKYC user can only create a maximum of 2 solo stashes");
    }
}