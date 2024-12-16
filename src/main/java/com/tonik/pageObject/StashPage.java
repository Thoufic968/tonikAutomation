package com.tonik.pageObject;

import org.openqa.selenium.By;
public enum StashPage {
    objStashCard("//*[contains(@text,'Your other accounts')]/following-sibling::android.view.ViewGroup[1]", "(//XCUIElementTypeStaticText[contains(@name,'Your other accounts')]/following-sibling::*)", "xpath", "xpath"),
    objStashes("//*[contains(@text,'Stashes')]", "//XCUIElementTypeStaticText[contains(@name,'Stashes')]", "xpath", "xpath"),
    objPutYourCashInAStash("//*[contains(@text,'Put your cash in a Stash!')]", "//XCUIElementTypeStaticText[contains(@name,'Put your cash in a Stash!')]", "xpath", "xpath"),
    objSaveAndGrowYourMoney("//*[contains(@text,'Save and grow your money with people you trust. Earn up to 4.5% per year.')]", "//XCUIElementTypeStaticText[contains(@name,'Save and grow your money')]", "xpath", "xpath"),
    objIndividualStashWelcomeScreen("//*[contains(@text,'Individual Stash')]", "com.tonik.mobile:id/Text_Title0", "xpath", "id"),
    objIndividualStashDescription("com.tonik.mobile:id/Text_Description0", "com.tonik.mobile:id/Text_Description0", "id", "id"),
    objAlrightButton("//*[contains(@text,'Alright')]", "com.tonik.mobile:id/Stash_Usp_Button0", "xpath", "id"),
    objDeserveThis("com.tonik.mobile:id/Text_Title1", "com.tonik.mobile:id/Text_Title1", "id", "id"),
    objDeserveThisDescription("com.tonik.mobile:id/Text_Description1", "com.tonik.mobile:id/Text_Description1", "id", "id"),
    objAmazingButton("//*[contains(@text,'Amazing!')]", "com.tonik.mobile:id/Stash_Usp_Button1", "xpath", "id"),
    objRebound("com.tonik.mobile:id/Text_Title2", "com.tonik.mobile:id/Text_Title2", "id", "id"),
    objReboundDescription("com.tonik.mobile:id/Text_Description2", "com.tonik.mobile:id/Text_Description2", "id", "id"),
    objReallyButton("//*[contains(@text,'Really?')]", "com.tonik.mobile:id/Stash_Usp_Button2", "xpath", "id"),
    objGoals("com.tonik.mobile:id/Text_Title3", "com.tonik.mobile:id/Text_Title3", "id", "id"),
    objGoalsDescription("com.tonik.mobile:id/Text_Description3", "com.tonik.mobile:id/Text_Description3", "id", "id"),
    objWorkIt("com.tonik.mobile:id/Text_Title4","com.tonik.mobile:id/Text_Title4","id","id"),
    objCoolButton("//*[contains(@text,'Cool!')]", "com.tonik.mobile:id/Stash_Usp_Button3", "xpath", "id"),
    objWerkitDescription("com.tonik.mobile:id/Text_Description4", "com.tonik.mobile:id/Text_Description4", "id", "id"),
    objLetsStartButton("//*[contains(@text,'Let’s start stashing!')]", "com.tonik.mobile:id/Stash_Usp_Button4", "xpath", "id"),
    objStashHeader("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objStartANewStash("//*[contains(@text,'Start a New Stash')]", "com.tonik.mobile:id/Start_New_Stash_Text", "xpath", "id"),
    obj5Of5AvailableStashes("//*[contains(@text,'available stashes')]", "com.tonik.mobile:id/Availanle_Stash_Text", "xpath", "id"),
    objOpenANewStash("com.tonik.mobile:id/Stash_Title", "com.tonik.mobile:id/Stash_Title", "id", "id"),
    objLuvStash("Luv Stash", "com.tonik.mobile:id/Luv Stash", "id", "id"),
    objEmergencyStash("Emergency Stash", "com.tonik.mobile:id/Emergency Stash", "id", "id"),
    objVacationStash("Vacation Stash", "com.tonik.mobile:id/Vacation Stash", "id", "id"),
    objTuitionStash("Tuition Stash", "com.tonik.mobile:id/Tuition Stash", "id", "id"),
    objGameConsole("Game Console", "com.tonik.mobile:id/Game Console", "id", "id"),
    objLearnMoreInOurFAQ("com.tonik.mobile:id/Stash_FAQ", "com.tonik.mobile:id/Stash_FAQ", "id", "id"),
    objSelectStashTypePopup("com.tonik.mobile:id/Select_Stash_Type_Text", "com.tonik.mobile:id/Select_Stash_Type_Text", "id", "id"),
    objThisCantBeChangeLater("com.tonik.mobile:id/Change_Later_Text", "com.tonik.mobile:id/Change_Later_Text", "id", "id"),
    objGroupStashOnPopup("com.tonik.mobile:id/Group_Stash_Text", "com.tonik.mobile:id/Group_Stash_Text", "id", "id"),
    objSoloStashOnPopup("com.tonik.mobile:id/Solo_Stash_Text", "com.tonik.mobile:id/Solo_Stash_Text", "id", "id"),
    objTeamworkMakesThisStashWorkOnPopup("com.tonik.mobile:id/GROUP_STASH_DESCRIPTION", "com.tonik.mobile:id/GROUP_STASH_DESCRIPTION", "id", "id"),
    objKeepItPersonalAndSaveInThisOnPopup("com.tonik.mobile:id/Solo_Stash_Description", "com.tonik.mobile:id/Solo_Stash_Description", "id", "id"),
    objSetUpYourStash("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objTargetAmount("//*[contains(@text,'Target amount')]", "//*[contains(@value,'Target amount')]", "xpath", "xpath"),
    objCameraIcon("com.tonik.mobile:id/Camera_View", "com.tonik.mobile:id/Camera_View", "id", "id"),
    objPleaseEnterAValidStashName("//*[contains(@text,'Please enter a valid stash name')]", "Please enter a valid stash name", "xpath", "id"),
    objEnterAValidStashName("com.tonik.mobile:id/Stash_Name_Error_Txt", "com.tonik.mobile:id/Stash_Name_Error_Txt", "id", "id"),
    objMinimumAmountCanBe("//*[contains(@text,'Min amount can be 1000 Peso.')]", "Min amount can be 1000 Peso.", "xpath", "id"),
    objSetInitialSaving("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objSkipForNow("com.tonik.mobile:id/Stash_Init_Saving_Skip_Text", "com.tonik.mobile:id/Stash_Init_Saving_Skip_Text", "id", "id"),
    objBalanceOn("com.tonik.mobile:id/STASH_BALANCE_TEXT", "com.tonik.mobile:id/STASH_BALANCE_TEXT", "id", "id"),
    objPhpText("com.tonik.mobile:id/Text_PHP", "com.tonik.mobile:id/Text_PHP", "id", "id"),
    objReviewStashDetails("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objStashNameOnTheImage("((//*[contains(@text,'Review Stash Details')]/parent::*/parent::*/following-sibling::*)[1]/child::*/child::android.widget.TextView)[1]", "(//XCUIElementTypeStaticText[contains(@value,'Review Stash Details')]/following::XCUIElementTypeStaticText)[1]", "xpath", "xpath"),
    objTargetAmountHeader("com.tonik.mobile:id/Stash_Traget_Amount_Text", "com.tonik.mobile:id/Stash_Traget_Amount_Text", "id", "id"),
    objInitialSavingHeader("com.tonik.mobile:id/Stash_Init_Saving_Text", "com.tonik.mobile:id/Stash_Init_Saving_Text", "id", "id"),
    objTransferFromHeader("com.tonik.mobile:id/Stash_Transfer_From_Text", "com.tonik.mobile:id/Stash_Transfer_From_Text", "id", "id"),
    objInterestRateHeader("//*[contains(@text,'Interest rate')]", "//*[contains(@value,'Interest rate')]", "xpath", "xpath"),
    objStashPDICText("com.tonik.mobile:id/Stash_PDIC_Text", "com.tonik.mobile:id/Stash_PDIC_Text", "id", "id"),
    objTermsAndCondition("//*[contains(@text,'terms and conditions')]", " terms and conditions", "xpath", "id"),
    objTermsAndConditionCheck("com.tonik.mobile:id/Stash_TNC_Click", "com.tonik.mobile:id/Stash_TNC_Click", "id", "id"),
    objCreateStashButtonDisabled("//*[contains(@text,'Create Stash') and @focusable='false']", "//*[contains(@name,'Create Stash') and @enabled='false']", "xpath", "xpath"),
    objTermsAndConditionScreen("(//*[contains(@text,'Terms and Conditions')])[1]", "(//XCUIElementTypeStaticText[contains(@name,'Terms and Conditions')])[1]", "xpath", "xpath"),
    objCreateStashButtonEnabled("//*[contains(@text,'Create Stash') and @enabled='true']", "(//*[@label='Create Stash' and @enabled='true'])[3]", "xpath", "xpath"),
    objSoloStashCreatedLuv("//*[@text=\"Solo Stash created, luv!\"]", "//XCUIElementTypeStaticText[@value=\"Solo Stash created, luv!\"]", "xpath", "xpath"),
    objStashSuccessDescription("com.tonik.mobile:id/Stash_Success_Description", "(//*[contains(@name,'You add many more money')])[11]", "id", "xpath"),
    objDoneButton("//*[contains(@text,'Done')]", "//XCUIElementTypeStaticText[(@label='Done')]", "xpath", "xpath"),
    objAddToStash("com.tonik.mobile:id/Add_To_Stash_Txt", "com.tonik.mobile:id/Add_To_Stash_Txt", "id", "id"),
    objManage("com.tonik.mobile:id/Manage_Stash_Txt", "com.tonik.mobile:id/Manage_Stash_Txt", "id", "id"),
    objAddToStashHeader("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objStashBalance("com.tonik.mobile:id/Stash_Balance", "com.tonik.mobile:id/Stash_Balance", "id", "id"),
    objStashPhpText("com.tonik.mobile:id/Stash_PHP_Text", "com.tonik.mobile:id/Stash_PHP_Text", "id", "id"),
    objNextButton("//*[contains(@text,'Next')]", "//*[@value='Next']", "xpath", "xpath"),
    objSetUpYourStashNextButton("com.tonik.mobile:id/Stash_Next_Button", "com.tonik.mobile:id/Stash_Next_Button", "id", "id"),
    objManageStash("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objStashDetails("//*[contains(@text,' Stash details')]", "//*[contains(@value,' Stash details')]", "xpath", "xpath"),
    objModify("//*[contains(@text,' Modify')]", "//*[contains(@value,' Modify')]", "xpath", "xpath"),
    objWithdrawToYourTonikAccount("//*[contains(@text,' Withdraw to your TONIK Account')]", "//XCUIElementTypeStaticText[contains(@label,' Withdraw to your TONIK Account')]", "xpath", "xpath"),
    objClose("//*[contains(@text,' Close')]", "//XCUIElementTypeStaticText[contains(@name,' Close')]", "xpath", "xpath"),
    objTotalStashBalanceHeader("//*[contains(@text,'Total Stash balance')]", "//XCUIElementTypeStaticText[contains(@name,'Total Stash balance')]", "xpath", "xpath"),
    objTotalStashBalanceOnDashBoard("(//*[contains(@text,'Total Stash balance')]/following-sibling::*)[2]", "(//*[@name='Total Stash balance']/following::XCUIElementTypeStaticText)[1]", "xpath", "xpath"),
    objLooksLonelyHere("//*[contains(@text,'Looks lonely here, why not create a Group Stash.')]", "Looks lonely here, why not create a Group Stash.", "xpath", "id"),
    objTeamWorks("//*[contains(@text,'Team works')]", "//*[contains(@value,'Team works')]", "xpath", "xpath"),
    objTargetAmountOnReviewStash("com.tonik.mobile:id/Stash_Traget_Amount_Value", "com.tonik.mobile:id/Stash_Traget_Amount_Value", "id", "id"),
    objInitialSavingOnReviewStash("com.tonik.mobile:id/Stash_Init_Saving_Value", "com.tonik.mobile:id/Stash_Init_Saving_Value", "id", "id"),
    objTransferFromOnReviewStash("com.tonik.mobile:id/Stash_Tonik_Account_Text", "com.tonik.mobile:id/Stash_Tonik_Account_Text", "id", "id"),
    objInterestRateOnReviewStash("(//*[contains(@text,'Interest rate')]/following-sibling::*)[1]", "(//*[contains(@value,'Interest rate')]/following-sibling::*)[1] | //XCUIElementTypeStaticText[@name='com.tonik.mobile:id/Stash_Floating_Interest_Value']", "xpath", "xpath"),
    objHistoryIcon("//*[contains(@text,'History')]//preceding-sibling::*", "(//*[contains(@name,'History')]//preceding::XCUIElementTypeButton)[6]", "xpath", "xpath"),
    ojHistoryScreen("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objYouHaveNoTransactionYet("//*[contains(@text,'You have no transactions yet.')]", "//XCUIElementTypeStaticText[contains(@name,'You have no transactions yet.')]", "xpath", "xpath"),
    objTermsAndConditionBackIcon("(//*[contains(@text,'Terms and Conditions')]/preceding::android.view.ViewGroup)[2]", "((//*[@name='Terms and Conditions'])[2]/child::*)[1]", "xpath", "xpath"),
    objInitialSavingAmountInputTextField("com.tonik.mobile:id/Stash_Init_Saving_Amount_Input", "com.tonik.mobile:id/Stash_Init_Saving_Amount_Input", "id", "id"),
    objConfirmTransferToStash("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objAmount("com.tonik.mobile:id/Stash_Amount_Value", "com.tonik.mobile:id/Stash_Amount_Value", "id", "id"),
    objToAccount("//android.widget.TextView[@text='To']/following-sibling::*", "//*[@value='To']/parent::*/following-sibling::*/child::*", "xpath", "xpath"),
    objStashOwner("com.tonik.mobile:id/Stash_Owner_Name", "com.tonik.mobile:id/Stash_Owner_Name", "id", "id"),
    objConfirmButtonOnConfirmTransferToStash("com.tonik.mobile:id/Stash_Confirm_Text", "com.tonik.mobile:id/Stash_Confirm_Text", "id", "id"),
    objMoneyStashed("com.tonik.mobile:id/Money_Stash_Success_Title", "com.tonik.mobile:id/Money_Stash_Success_Title", "id", "id"),
    objYouAdded("com.tonik.mobile:id/Money_Stash_Success_Description", "com.tonik.mobile:id/Money_Stash_Success_Description", "id", "id"),
    objViewDetailsOfHowWeMovedIt("com.tonik.mobile:id/View_Stash_Details_Txt", "com.tonik.mobile:id/View_Stash_Details_Txt", "id", "id"),
    objWhen("com.tonik.mobile:id/Stash_Txn_Date", "com.tonik.mobile:id/Stash_Txn_Date", "id", "id"),
    objRefNumber("com.tonik.mobile:id/Stash_Reference_Value", "com.tonik.mobile:id/Stash_Reference_Value", "id", "id"),
    objNameOfTheSoloStash("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objInterestRateValue("com.tonik.mobile:id/Stash_Interest_Value", "com.tonik.mobile:id/Stash_Interest_Value", "id", "id"),
    objMyselfTransactionAmount("com.tonik.mobile:id/TXN_Amount_Txt0", "com.tonik.mobile:id/TXN_Amount_Txt0", "id", "id"),
    objAchievedAmountOnSoloStash("com.tonik.mobile:id/Stash_Goal_Achieved_Amount", "com.tonik.mobile:id/Stash_Goal_Achieved_Amount", "id", "id"),
    objTonikAmountOnDashboard("(//*[contains(@text,'Your tonik account')]/following-sibling::android.widget.TextView)[1]", "(//*[contains(@value,'Your tonik account')]/following-sibling::XCUIElementTypeStaticText)[1]", "xpath", "xpath"),
    objNameOfTheStashOnHistory("//*[contains(@text,'Top up')]/parent::*/parent::*/following-sibling::android.view.ViewGroup/child::*/child::*/child::*[2]/child::*/child::*[1]/child::*[2]", "(//*[contains(@value,'Account History')]/following::XCUIElementTypeStaticText)[6]", "xpath", "xpath"),
    objTransactionValueOnHistory("//*[contains(@text,'Top up')]/parent::*/parent::*/following-sibling::android.view.ViewGroup/child::*/child::*/child::*[2]/child::*/child::*[1]/child::*[4]", "(//*[contains(@value,'Account History')]/following::XCUIElementTypeStaticText)[8]", "xpath", "xpath"),
    objAddToStashEditText("com.tonik.mobile:id/Stash_Amount_Input", "com.tonik.mobile:id/Stash_Amount_Input", "id", "id"),
    objTransactionDetails("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objFromTransactionDetails("com.tonik.mobile:id/Stash_From_Value", "com.tonik.mobile:id/Stash_From_Value", "id", "id"),
    objQuestionMarkIconOnTransactionDetails("com.tonik.mobile:id/Header_right_click", "com.tonik.mobile:id/Header_right_click", "id", "id"),
    objDisabledAmount("//*[contains(@text,'Your tonik account') and @focused='false']/following::android.widget.TextView[1]", "//*[contains(@value,'Your tonik account') and @enabled='false']/following::XCUIElementTypeStaticText[1]", "xpath", "xpath"),
    objEnabledAmount("//*[contains(@text,'Your tonik account') and @enabled='true']/following::android.widget.TextView[1]", "//*[contains(@value,'Your tonik account') and @enabled='true']/following::XCUIElementTypeStaticText[1]", "xpath", "xpath"),
    objWithdrawFromYourStashHeader("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objAreYouSureYouWantToCloseTheStashPopup("com.tonik.mobile:id/Popup_sub_header_txt", "com.tonik.mobile:id/Popup_sub_header_txt", "id", "id"),
    objAmountDetails("//*[@text='Amount']/following-sibling::*", "(//*[@value='Amount']/parent::*/following-sibling::*/child::*)", "xpath", "xpath"),
    objFromAccount("//*[@text='From']/following-sibling::*", "(//*[@value='From']/parent::*/following-sibling::*/child::*)", "xpath", "xpath"),
    objWhenDetails("//*[@text='When']/following-sibling::*", "(//*[@value='When']/parent::*/following-sibling::*/child::*)", "xpath", "xpath"),
    objRefNoDetails("//*[@text='Reference No.']/following-sibling::*", "(//*[@value='Reference No.']/parent::*/following-sibling::*/child::*)", "xpath", "xpath"),
    objStashDetailsHeader("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objStashIDOption("com.tonik.mobile:id/Stash_ID_Text", "com.tonik.mobile:id/Stash_ID_Text", "id", "id"),
    objStartingDateOption("com.tonik.mobile:id/Stash_Start_Date_Text", "com.tonik.mobile:id/Stash_Start_Date_Text", "id", "id"),
    ObjTargetAmountOption("com.tonik.mobile:id/Stash_Amount_Txt", "com.tonik.mobile:id/Stash_Amount_Txt", "id", "id"),
    objStashID("com.tonik.mobile:id/Stash_ID_Value", "com.tonik.mobile:id/Stash_ID_Value", "id", "id"),
    objStartingDate("com.tonik.mobile:id/Stash_Start_Date", "com.tonik.mobile:id/Stash_Start_Date", "id", "id"),
    objNoEnoughBalanceInStashAccount("com.tonik.mobile:id/Stash_Target_Amount_Error", "com.tonik.mobile:id/Stash_Target_Amount_Error", "id", "id"),
    objModifyStashHeader("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objStashFor("//*[contains(@text,'Stash for')]", "//*[contains(@value,'Stash for')]", "xpath", "xpath"),
    objTargetAmountEditText("//*[@text='Target amount']/parent::*/child::android.widget.EditText", "//XCUIElementTypeStaticText[@label='Target amount']/parent::*/child::XCUIElementTypeOther/child::XCUIElementTypeTextField", "xpath", "xpath"),
    objStashForEditText("//*[contains(@text,'Stash for')]/parent::*/child::android.widget.EditText", "com.tonik.mobile:id/Stash_Name_Input", "xpath", "id"),
    objImageView("//android.widget.ImageView", "//XCUIElementTypeImage", "xpath", "xpath"),
    objNameInputTxt("com.tonik.mobile:id/Stash_Name_Input_Txt", "com.tonik.mobile:id/Stash_Name_Input_Txt", "id", "id"),
    objNameInputTextField("com.tonik.mobile:id/Stash_Name_Input_Txt", "(//*[@value='Stash for']/parent::*/child::*/child::*)", "id", "xpath"),
    objTargetAmountField("com.tonik.mobile:id/Stash_Target_Amount_Input", "com.tonik.mobile:id/Stash_Target_Amount_Input", "id", "id"),
    objYouUpdatesYourStash("com.tonik.mobile:id/Close_Stash_Success_Title", "com.tonik.mobile:id/Close_Stash_Success_Title", "id", "id"),
    objYouUpdatesYourStashDescription("com.tonik.mobile:id/Close_Stash_Success_Description", "com.tonik.mobile:id/Close_Stash_Success_Description", "id", "id"),
    objSaveButton("//*[contains(@text,'Save')]", "//*[@name='Save']", "xpath", "xpath"),
    ObjTargetAmountOnTheImage("com.tonik.mobile:id/Stash_Current_Amount_Text0", "com.tonik.mobile:id/Stash_Current_Amount_Text0", "id", "id"),
    objWithdrawFromStashTitle("//*[@text='Withdraw from your Stash']", "com.tonik.mobile:id/Main_title_txt", "xpath", "id"),
    objBalanceTxt("//*[contains(@text,'Balance')]", "(//*[@value='Withdraw from your Stash']/parent::*/following::XCUIElementTypeStaticText)[1]", "xpath", "xpath"),
    objWithdrawButton("com.tonik.mobile:id/Stash_Withdraw_Button", "com.tonik.mobile:id/Stash_Withdraw_Button", "id", "id"),
    objStashTargetAmountInputField("com.tonik.mobile:id/Stash_Traget_Amount_Input", "com.tonik.mobile:id/Stash_Traget_Amount_Input", "id", "id"),
    objReviewWithDrawalHeader("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objReviewWithDrawalAmount("com.tonik.mobile:id/Stash_Traget_Amount_Text", "com.tonik.mobile:id/Stash_Traget_Amount_Text", "id", "id"),
    objReviewWithDrawalFrom("com.tonik.mobile:id/Stash_From_Value", "com.tonik.mobile:id/Stash_From_Value", "id", "id"),
    objReviewWithDrawalTo("com.tonik.mobile:id/Stash_To_Tonik_Account", "com.tonik.mobile:id/Stash_To_Tonik_Account", "id", "id"),
    objSweet("com.tonik.mobile:id/Money_Stash_Success_Title", "com.tonik.mobile:id/Money_Stash_Success_Title", "id", "id"),
    objSweetDescription("com.tonik.mobile:id/Money_Stash_Success_Description", "com.tonik.mobile:id/Money_Stash_Success_Description", "id", "id"),
    objSweetViewDetails("com.tonik.mobile:id/View_Stash_Details_Txt", "com.tonik.mobile:id/View_Stash_Details_Txt", "id", "id"),
    objOhYeahButton("com.tonik.mobile:id/Stash_Done_Btn", "com.tonik.mobile:id/Stash_Done_Btn", "id", "id"),
    objMoneyUnStashed("//*[contains(@text,'Money un-stashed')]", "//*[contains(@name,'Money un-stashed')]", "xpath", "xpath"),
    objCloserAmountTransaction("//*[contains(@text,'Closure Amount')]", "//*[contains(@value,'Closure Amount')]", "xpath", "xpath"),
    objStayButton("com.tonik.mobile:id/Popup_positive_btn_click", "com.tonik.mobile:id/Popup_positive_btn_click", "id", "id"),
    objYesCloseTheStash("com.tonik.mobile:id/Popup_negative_btn_txt", "com.tonik.mobile:id/Popup_negative_btn_txt", "id", "id"),
    objYouBrokeTheStash("com.tonik.mobile:id/Close_Stash_Success_Title", "com.tonik.mobile:id/Close_Stash_Success_Title", "id", "id"),
    objYouBrokeTheStashDescription("com.tonik.mobile:id/Close_Stash_Success_Description", "com.tonik.mobile:id/Close_Stash_Success_Description", "id", "id"),
    objLastStashName("(//*[contains(@resource-id,'Stash_Name_Text')])[last()]", "(//*[contains(@name,'Stash_Name_Text')])[last()]", "xpath", "xpath"),
    objLastTargetAmount("(//*[contains(@resource-id,'Stash_Current_Amount_Text')])[last()]", "(//*[contains(@name,'Stash_Current_Amount_Text')])[last()]", "xpath", "xpath"),
    objReviewWithdrawal("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objWithdrawAmountText("com.tonik.mobile:id/Stash_Traget_Amount_Text", "com.tonik.mobile:id/Stash_Traget_Amount_Text", "id", "id"),
    objFromValue("com.tonik.mobile:id/Stash_From_Value", "com.tonik.mobile:id/Stash_From_Value", "id", "id"),
    objToTonikAccount("com.tonik.mobile:id/Stash_To_Tonik_Account", "com.tonik.mobile:id/Stash_To_Tonik_Account", "id", "id"),
    objConfirmButton("com.tonik.mobile:id/Stash_Confirm_Button_View", "com.tonik.mobile:id/Stash_Confirm_Button_View", "id", "id"),
    objSweetDoneButton("com.tonik.mobile:id/Stash_Done_Btn", "com.tonik.mobile:id/Stash_Done_Btn", "id", "id"),
    objLastAddToStashButton("(//*[contains(@resource-id,'Add_To_Stash_Button')])[last()]", "(//*[contains(@value,'Add to Stash')])[last()]", "xpath", "xpath"),
    objLastManageButton("(//*[contains(@resource-id,'Manage_Stash_Button')])[last()]", "(//*[contains(@value,'Manage')])[last()]", "xpath", "xpath"),
    objStashAmountError("com.tonik.mobile:id/Stash_Target_Amount_Error", "com.tonik.mobile:id/Stash_Target_Amount_Error", "id", "id"),
    objGroupStashReadyText("com.tonik.mobile:id/Stash_Success_Title", "com.tonik.mobile:id/Stash_Success_Title", "id", "id"),
    objGroupStashReadyPageDesc("com.tonik.mobile:id/Stash_Success_Description", "com.tonik.mobile:id/Stash_Success_Description", "id", "id"),
    objInvitePeopleInStash("com.tonik.mobile:id/Stash_Invite_Text", "com.tonik.mobile:id/Stash_Invite_Text", "id", "id"),
    objInviteToYourStashText("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objSearchInput("com.tonik.mobile:id/Stash_Search_Input", "com.tonik.mobile:id/Stash_Search_Input", "id", "id"),
    objInviteFriendButton("com.tonik.mobile:id/Stash_Invite_Friend_Button", "com.tonik.mobile:id/Stash_Invite_Friend_Button", "id", "id"),
    objInviteFriendText("com.tonik.mobile:id/Stash_Invite_Friend_Text", "com.tonik.mobile:id/Stash_Invite_Friend_Text", "id", "id"),
    objMessageApp("//*[@text='New Message']", "(//XCUIElementTypeStaticText[@name='New Message'])[2]", "xpath", "xpath"),
    objSendButton("com.android.mms:id/send_button", "sendButton", "id", "id"),
    objCancelButton("com.android.mms:id/action_edit", "//XCUIElementTypeStaticText[@label='Cancel']", "id", "xpath"),
    objGoBackToStashText("//*[@text='Go back to my Stash']", "//XCUIElementTypeStaticText[@label='Go back to my Stash']", "xpath", "xpath"),
    objManageSaversText("com.tonik.mobile:id/Manage_Savers_Txt", "com.tonik.mobile:id/Manage_Savers_Txt", "id", "id"),
    objMyself("//*[contains(@text,'Myself')][1]", "com.tonik.mobile:id/Myself", "xpath", "id"),
    objTotalStashBalanceDesc("(//*[contains(@text,'Total Stash balance')]/following-sibling::android.widget.TextView)[2]", "(//*[contains(@value,'Total Stash balance')]/following::XCUIElementTypeStaticText)[2]", "xpath", "xpath"),
    objPopupHeader("com.tonik.mobile:id/Popup_sub_header_txt", "com.tonik.mobile:id/Popup_sub_header_txt", "id", "id"),
    objCloseStashButton("com.tonik.mobile:id/Popup_negative_btn_txt", "com.tonik.mobile:id/Popup_negative_btn_txt", "id", "id"),
    objAddToStashBtn("com.tonik.mobile:id/Add_To_Stash_Btn", "com.tonik.mobile:id/Add_To_Stash_Btn", "id", "id"),
    objManageStashBtn("com.tonik.mobile:id/Manage_Stash_Btn", "com.tonik.mobile:id/Manage_Stash_Btn", "id", "id"),
    objYouUpdatedYourStashTitle("com.tonik.mobile:id/Close_Stash_Success_Title", "com.tonik.mobile:id/Close_Stash_Success_Title", "id", "id"),
    objStashSuccessDesc("com.tonik.mobile:id/Close_Stash_Success_Description", "com.tonik.mobile:id/Close_Stash_Success_Description", "id", "id"),
    objStashDoneButton("com.tonik.mobile:id/Close_Stash_Done_Button", "com.tonik.mobile:id/Close_Stash_Done_Button", "id", "id"),
    objStepsToVerifyYourAccount("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objKnowMore("com.tonik.mobile:id/Stash_Target_Amount_Error", "com.tonik.mobile:id/Stash_Target_Amount_Error", "id", "id"),
    objGoalAchievedHeader("com.tonik.mobile:id/Goal_Achieved_Text", "com.tonik.mobile:id/Goal_Achieved_Text", "id", "id"),
    objTargetAmountText("//*[@text='Target Amount']", "com.tonik.mobile:id/Target Amount", "xpath", "id"),
    objTargetAmountValue("(//*[@text='Target Amount']/following-sibling::*)[1]", "(//*[@value='Target Amount']/following-sibling::*)[1]", "xpath", "xpath"),
    objStashDetailsTargetAmountValue("//*[contains(@text,'Target amount')]/following-sibling::*", "com.tonik.mobile:id/Stash_Amount_Txt_Value", "xpath", "id"),
    objStashBalanceText("//*[@text='Stash Balance']", "com.tonik.mobile:id/Stash Balance", "xpath", "id"),
    objStashBalanceValue("(//*[@text='Stash Balance']/following-sibling::*)[1]", "(//*[@value='Stash Balance']/following-sibling::*)[1]", "xpath", "xpath"),
    objStartingDateText("//*[@text='Starting date']", "com.tonik.mobile:id/Starting date", "xpath", "id"),
    objStartingDateValue("(//*[@text='Starting date']/following-sibling::*)[1]", "(//*[@value='Starting date']/following-sibling::*)[1]", "xpath", "xpath"),
    objInterestRateText("//*[contains(@text,'Interest rate')]", "com.tonik.mobile:id/Interest rate (p.a.)", "xpath", "id"),
    objGoalAchievedInterestRateValue("(//*[contains(@text,'Interest rate')]/following-sibling::*)[1]", "(//*[@value='Interest rate (p.a.)']/following-sibling::*)[1]", "xpath", "xpath"),
    objWithholdinTaxText("//*[@text='Withholding tax']", "com.tonik.mobile:id/Withholding tax", "xpath", "id"),
    objWithholdinTaxValue("(//*[@text='Withholding tax']/following-sibling::*)[1]", "(//*[@value='Withholding tax']/following-sibling::*)[1]", "xpath", "xpath"),
    objCloseStashDesc("com.tonik.mobile:id/Close_Stash_Description_Text", "com.tonik.mobile:id/Close_Stash_Description_Text", "id", "id"),
    objGoalAchievedNextButton("com.tonik.mobile:id/Button_Next", "com.tonik.mobile:id/Button_Next", "id", "id"),
    objGoalAchievedNextText("com.tonik.mobile:id/Text_Next", "com.tonik.mobile:id/Text_Next", "id", "id"),
    objNextStepHeader("//*[@text=\" What's your next step?\"]", " What's your next step?", "xpath", "id"),
    objConvertToTimeDepositText("//*[@text='Convert to Time Deposit']", "com.tonik.mobile:id/Convert to Time Deposit", "xpath", "id"),
    objSetAHigherGoalText("//*[@text='Set a higher goal']", "com.tonik.mobile:id/Set a higher goal", "xpath", "id"),
    objBackToStashText("//*[@text='Back to Stash']", "com.tonik.mobile:id/Back to Stash", "xpath", "id"),
    objConvertStashToTDText("com.tonik.mobile:id/Convert_Stash_To_TD_Text", "com.tonik.mobile:id/Convert_Stash_To_TD_Text", "id", "id"),
    objCloseStashTDButton("com.tonik.mobile:id/Close_Stash_Button", "com.tonik.mobile:id/Close_Stash_Button", "id", "id"),
    objLeaveItAsButton("com.tonik.mobile:id/Leave_Stash_As_It_Is_Button", "com.tonik.mobile:id/Leave_Stash_As_It_Is_Button", "id", "id"),
    objCloseStashTDText("com.tonik.mobile:id/Close_Stash_Text", "com.tonik.mobile:id/Close_Stash_Text", "id", "id"),
    objLeaveItAsText("com.tonik.mobile:id/Leave_Stash_As_It_Is_Text", "com.tonik.mobile:id/Leave_Stash_As_It_Is_Text", "id", "id"),
    objStartATDButton("com.tonik.mobile:id/Start_Time_Deposit_Button", "com.tonik.mobile:id/Start_Time_Deposit_Button", "id", "id"),
    objCloseStashLaterButton("com.tonik.mobile:id/Close_Stash_Later_Button", "com.tonik.mobile:id/Close_Stash_Later_Button", "id", "id"),
    objBackArrowButton("com.tonik.mobile:id/Header_left_click", "com.tonik.mobile:id/Header_left_click", "id", "id"),
    objStashNameErrorMessage("//*[@resource-id=\"com.tonik.mobile:id/Stash_Name_Input\"]/../following-sibling::android.widget.TextView", "(//*[@value='Stash for']/following::XCUIElementTypeStaticText)[1]", "xpath", "xpath"),
    objStashCloseButton("com.tonik.mobile:id/Stash_Close_Button", "com.tonik.mobile:id/Stash_Close_Button", "id", "id"),
    objTDCalculatorNextButton("com.tonik.mobile:id/TD_CalculatorNext_Button_Click", "com.tonik.mobile:id/TD_CalculatorNext_Button_Click", "id", "id"),
    objHowMuchInvestHeader("com.tonik.mobile:id/TD_Calculator_Text_View_1", "com.tonik.mobile:id/TD_Calculator_Text_View_1", "id", "id"),
    objSetupTDHeader("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objSetupTDToggleButton("com.tonik.mobile:id/Set_Up_TD_Toggle_Button", "com.tonik.mobile:id/Set_Up_TD_Toggle_Button", "id", "id"),
    objSetUpTDNextButton("com.tonik.mobile:id/Set_Up_TDNext_Button_Click", "com.tonik.mobile:id/Set_Up_TDNext_Button_Click", "id", "id"),
    objWootDoneButton("//*[contains(@resource-id,'woot!Next_Button_Click')]", "com.tonik.mobile:id/Woot woot!Next_Button_Click", "xpath", "id"),
    objSaversHeader("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objInviteMoreSavers("com.tonik.mobile:id/Invite_More_Savers_Text", "com.tonik.mobile:id/Invite_More_Savers_Text", "id", "id"),
    objSendInviteButtonDisabled("//*[@resource-id='com.tonik.mobile:id/Stash_Invite_Friend_Button' and @enabled='false']", "//*[@name='com.tonik.mobile:id/Stash_Invite_Friend_Button' and @enabled='false']", "xpath", "xpath"),
    objAccountHoldersName("(//*[@resource-id='com.tonik.mobile:id/Stash_Invitation_List_View0']/child::*)[2]", "(//*[@value='Invite more savers']/following::XCUIElementTypeStaticText)[1]", "xpath", "xpath"),
    objContributeAmount("(//*[@resource-id='com.tonik.mobile:id/Stash_Invitation_List_View0']/child::*)[3]", "(//*[@value='Invite more savers']/following::XCUIElementTypeStaticText)[2]", "xpath", "xpath"),
    objStashUserText("(//*[@resource-id='com.tonik.mobile:id/Stash_Invitation_List_View0']/child::*)[4]", "(//*[@value='Invite more savers']/following::XCUIElementTypeStaticText)[2]", "xpath", "xpath"),
    objSelectedContact("(//*[@resource-id='com.tonik.mobile:id/Select_Contact_Button1']/child::*/child::*/child::*)[3]", "(//*[@value='Invite to your Stash']/following::XCUIElementTypeStaticText)[2]", "xpath", "xpath"),
    objFirstMember("com.tonik.mobile:id/Stash_Invitation_List_View0", "com.tonik.mobile:id/Stash_Invitation_List_View0", "id", "id"),
    objSelectedMemberProfileName("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objTotalContributedAmount("com.tonik.mobile:id/Stash_Saved_Amount_Value", "com.tonik.mobile:id/Stash_Saved_Amount_Value", "id", "id"),
    objPendingStatusMember("//*[contains(@text,'Pending')]", "//*[@value='Pending']", "xpath", "xpath"),
    objResendInvitationPopup("com.tonik.mobile:id/Popup_sub_header_txt", "com.tonik.mobile:id/Popup_sub_header_txt", "id", "id"),
    objResendInvitationButton("com.tonik.mobile:id/Popup_positive_btn_click", "com.tonik.mobile:id/Popup_positive_btn_click", "id", "id"),
    objRemoveFromSaverListButton("com.tonik.mobile:id/Popup_negative_btn_click", "com.tonik.mobile:id/Popup_negative_btn_click", "id", "id"),
    objWeSent("//*[contains(@text,'We sent')]", "//*[contains(@value,'We sent')]", "xpath", "xpath"),
    objWeSentDescription("//*[contains(@text,'I took care of the balloons')]", "//*[contains(@value,'I took care of the balloons')]", "xpath", "xpath"),
    objSearchedRelatedContact("(//*[@text='Invite to your Stash']/following::android.widget.TextView)[2] ", "(//*[@value='Invite to your Stash']/following::XCUIElementTypeStaticText)[1]", "xpath", "xpath"),
    objStashTargetAmountErrorForBKYC("//*[contains(@text,'You can add only up to')]", "//*[contains(@value,'You can add only up to')]", "xpath", "xpath"),
    objVerifyAccountNowButton("com.tonik.mobile:id/Popup_single_btn_txt", "com.tonik.mobile:id/Popup_single_btn_txt", "id", "id"),
    objStepsToVerifyYourAccountPageHeader("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objAvailableStashText("com.tonik.mobile:id/Availanle_Stash_Text", "com.tonik.mobile:id/Availanle_Stash_Text", "id", "id"),
    objPopupOkButton("com.tonik.mobile:id/Popup_single_btn_txt", "com.tonik.mobile:id/Popup_single_btn_click", "id", "id"),
    objMinimumAmountCanBe1Peso("//*[contains(@text,'Min amount can be 1 Peso.')]", "//*[@value='Min amount can be 1 Peso.']", "xpath", "xpath"),
    objDoItLater("//*[contains(@text,\"I'll do it later, pinky swear\")]", "//*[contains(@value,\"I'll do it later, pinky swear\")]", "xpath", "xpath"),
    objCommunicationIsKeyPopup("//*[contains(@text,'Communication is key.')]", "//*[contains(@label,'Communication is key.')]", "xpath", ""),
    objNoEnoughBalanceInTonikAccount("//*[contains(@text,'No enough balance in TONIK account')]", "//*[contains(@value,'No enough balance in TONIK account')]", "xpath", "xpath"),
    objGroupIconAfterGoalAchieved("(//*[contains(@resource-id,'Stash_Name_Text')])[last()]/preceding-sibling::*[2]", "((//*[contains(@name,'Stash_Name_Text')])[last()]/parent::*/preceding-sibling::*/child::*)[2]", "xpath", "xpath"),
    objHorizontalBar("((//*[contains(@resource-id,'Stash_Current_Amount_Text')])[last()]/preceding-sibling::*)[5]", "((//*[contains(@name,'Stash_Current_Amount_Text')])[last()]/parent::*/parent::*/following-sibling::*/child::*)", "xpath", "xpath"),
    objIncreaseYourLimitHere("//*[contains(@text,'only Increase your limit here.')]", "//*[contains(@value,'only Increase your limit here.')]", "xpath", "xpath"),
    objHowCanIIncreaseMyLimitHere("//*[contains(@text,'How can I increase my limit?')]", "//*[contains(@value,'How can I increase my limit?')]", "xpath", "xpath"),
    objScanOneValidIDYouOwn("//*[contains(@text,'Scan one Valid ID you own.')]", "//*[contains(@value,'Scan one Valid ID you own.')]", "xpath", "xpath"),
    objOtpInputField("(//*[@text='Resend OTP']/parent::*/preceding-sibling::*)[2]", "(//*[@name='Resend OTP']/parent::*/preceding-sibling::*)[2]", "xpath", "xpath"),
    objGoalAchievedTxt("com.tonik.mobile:id/Stash_Status_Text2", "//*[@value='Congrats! Goal achieved!']", "id", "xpath"),
    objSelectContactButton_1("//*[contains(@resource-id,'Select_Contact_Button0')]", "com.tonik.mobile:id/Group1 ", "xpath", "xpath"),
    objSelectContactButton_2("//*[contains(@resource-id,'Select_Contact_Button1')]", "com.tonik.mobile:id/Group2 ", "xpath", "id"),
    objTakeAFaceIdentityScan("//*[contains(@text,'Take a Face Identity Scan (Keep your clothes on, please.)')]", "//*[contains(@value,'Take a Face Identity Scan')]", "xpath", "xpath"),
    objStashConfirmBtn("com.tonik.mobile:id/Stash_Confirm_Button", "com.tonik.mobile:id/Stash_Confirm_Button", "id", "id"),
    objMoneyStashedText("com.tonik.mobile:id/Money_Stash_Success_Title", "com.tonik.mobile:id/Money_Stash_Success_Title", "id", "id"),
    objMoneyStashedDesc("com.tonik.mobile:id/Money_Stash_Success_Description", "com.tonik.mobile:id/Money_Stash_Success_Description", "id", "id"),
    objViewStashDetailsText("com.tonik.mobile:id/View_Stash_Details_Txt", "com.tonik.mobile:id/View_Stash_Details_Txt", "id", "id"),
    objViewStashDetailsBtn("com.tonik.mobile:id/View_Stash_Details_Btn", "com.tonik.mobile:id/View_Stash_Details_Btn", "id", "id"),
    objStashWhenText("com.tonik.mobile:id/Stash_When_Text", "com.tonik.mobile:id/Stash_When_Text", "id", "id"),
    objStashFromValue("com.tonik.mobile:id/Stash_From_Value", "com.tonik.mobile:id/Stash_From_Value", "id", "id"),
    objStashTxnDate("com.tonik.mobile:id/Stash_Txn_Date", "com.tonik.mobile:id/Stash_Txn_Date", "id", "id"),
    objStashRefText("com.tonik.mobile:id/Stash_Reference_Text", "com.tonik.mobile:id/Stash_Reference_Text", "id", "id"),
    objStashRefValue("com.tonik.mobile:id/Stash_Reference_Value", "com.tonik.mobile:id/Stash_Reference_Value", "id", "id"),
    objNewStash("com.tonik.mobile:id/Stash_Name_Text0", "com.tonik.mobile:id/Stash_Name_Text0", "id", "id"),
    objContributedMemberName("(//*[contains(@resource-id,'Stash_Saved_Amount_Text')]/preceding-sibling::android.widget.TextView)[1]", "(//*[contains(@name,'Stash_Saved_Amount_Text')]/preceding-sibling::XCUIElementTypeStaticText)[1]", "xpath", "xpath"),
    objContributedMemberFund("(//*[contains(@resource-id,'Stash_Saved_Amount_Text')])[1]", "(//*[contains(@name,'Stash_Saved_Amount_Text')])[1]", "xpath", "xpath"),
    objAccountNumber("//*[contains(@text,'ACCOUNT NUMBER')]", "//*[contains(@value,'ACCOUNT NUMBER')]", "xpath", "xpath"),
    objTSAAmount("(//*[contains(@text,'ACCOUNT NUMBER')]/following-sibling::*)[1]", "(//*[contains(@value,'ACCOUNT NUMBER')]/parent::*/following-sibling::*/child::*)[1]", "xpath", "xpath"),
    objTopUpText("//*[@text='Top up']", "//*[@value='Top up']", "xpath", "xpath"),
    objManageText("//*[@text='Manage']", "//*[@value='Manage']", "xpath", "xpath"),
    objMoneyContributedText("(//*[@text='Money contributed'])[1]", "(//*[@value='Money contributed'])[1]", "xpath", "xpath"),
    objStashNameOnAccountHistory("(//*[@text='Money contributed'])[1]/preceding-sibling::android.widget.TextView", "(//*[@value='Money contributed'])[1]/preceding-sibling::XCUIElementTypeStaticText", "xpath", "xpath"),
    objManageStashButton("com.tonik.mobile:id/Manage_Stash_Button0", "com.tonik.mobile:id/Manage_Stash_Button0", "id", "id"),
    objLeaveStashBtn("//*[contains(@text,'Leave stash')]/following-sibling::*", "//*[contains(@value,'Leave stash')]/parent::*/following-sibling::*/child::*", "xpath", "xpath"),
    objLeaveStashText("//*[contains(@text,'Leave stash')]", "//*[contains(@value,'Leave stash')]", "xpath", "xpath"),
    objStayText("com.tonik.mobile:id/Popup_positive_btn_txt", "com.tonik.mobile:id/Popup_positive_btn_txt", "id", "id"),
    objYouHaveLeftText("//*[contains(@text,'You have left the Trip')]", "//*[contains(@value,'You have left the Trip')]", "xpath", "xpath"),
    objDropsMicText("//*[contains(@text,'Drops mic')]", "//*[contains(@value,'Drops mic')]", "xpath", "xpath"),
    objContactNames("//*[contains(@resource-id,'Select_Contact_Button')]//android.widget.TextView[contains(@resource-id,'com.tonik.mobile:id')]", "//*[contains(@name,'Select_Contact_Button')]//XCUIElementTypeStaticText[contains(@name,'com.tonik.mobile:id')]", "xpath", "xpath"),
    objNonTonikContact("//*[contains(@text,'NonTonik')]/ancestor::*[contains(@resource-id,'Select_Contact_Button')]", "//*[contains(@value,'NonTonik')]/ancestor::*[contains(@name,'Select_Contact_Button')]", "xpath", "xpath"),
    objStashStatus("com.tonik.mobile:id/Stash_Status_Text_View", "com.tonik.mobile:id/Stash_Status_Text_View", "id", "id"),
    objSelectedContacts("com.tonik.mobile:id/Contact_Name_Text0", "com.tonik.mobile:id/Contact_Name_Text0", "id", "id"),
    objPopupText("com.tonik.mobile:id/Popup_sub_header_txt", "com.tonik.mobile:id/Popup_sub_header_txt", "id", "id"),
    objResendInvitationText("com.tonik.mobile:id/Popup_positive_btn_txt", "com.tonik.mobile:id/Popup_positive_btn_txt", "id", "id"),
    objRemoveFromSaverListText("com.tonik.mobile:id/Popup_negative_btn_txt", "com.tonik.mobile:id/Popup_negative_btn_txt", "id", "id"),
    objRemoveFromSaverListBtn("com.tonik.mobile:id/Popup_negative_btn_click", "com.tonik.mobile:id/Popup_negative_btn_click", "id", "id"),
    objYouRemoveText("//*[contains(@text,'You removed')]", "//*[contains(@value,'You removed')]", "xpath", "xpath"),
    objMaybeText("//*[contains(@text,'Maybe it just')]", "//*[contains(@value,'Maybe it just')]", "xpath", "xpath"),
    objDoneText("//*[@text='Done']", "//*[@value='Done']", "xpath", "xpath"),
    objDoneBtn("//*[@text='Done']/parent::*", "//*[@value='Done']/parent::*", "xpath", "xpath"),
    objMemberName("(//*[contains(@resource-id,'Stash_Invitation_List_View1')]/child::*)[2]", "(//*[contains(@name,'Stash_Invitation_List_View1')]/child::*/child::*/child::*)[2]", "xpath", "xpath"),
    objStashInvitation("com.tonik.mobile:id/Stash_Inivitation_Veiw", "com.tonik.mobile:id/Stash_Inivitation_Veiw", "id", "id"),
    objStashInviteName("//*[contains(@resource-id,'Stash_Inivitation_Veiw')]/child::android.widget.TextView", "//*[contains(@name,'Stash_Inivitation_Veiw')]//XCUIElementTypeStaticText", "xpath", "xpath"),
    objStashInvitationHeader("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objStashOwnerName("com.tonik.mobile:id/Stash_Owner_Name", "com.tonik.mobile:id/Stash_Owner_Name", "id", "id"),
    objStashName("//*[contains(@resource-id,'Stash_Owner_Name')]/preceding-sibling::android.widget.TextView", "(//*[contains(@name,'Stash_Owner_Name')]/preceding-sibling::*/parent::*/preceding-sibling::*/child::*)[2]", "xpath", "xpath"),
    objStashTargetAmountText("com.tonik.mobile:id/Stash_Traget_Amount_Text", "com.tonik.mobile:id/Stash_Traget_Amount_Text", "id", "id"),
    objStashTargetAmountValue("com.tonik.mobile:id/Stash_Traget_Amount_Value", "com.tonik.mobile:id/Stash_Traget_Amount_Value", "id", "id"),
    objStashTargetDateText("com.tonik.mobile:id/Stash_Traget_Date_Text", "com.tonik.mobile:id/Stash_Traget_Date_Text", "id", "id"),
    objStashTargetDateValue("com.tonik.mobile:id/Stash_Traget_Date_Value", "com.tonik.mobile:id/Stash_Traget_Date_Value", "id", "id"),
    objOthersStasherText("com.tonik.mobile:id/Other_Stashers_Text", "com.tonik.mobile:id/Other_Stashers_Text", "id", "id"),
    objNumberOfSavers("com.tonik.mobile:id/Number_Of_Savers_Text", "com.tonik.mobile:id/Number_Of_Savers_Text", "id", "id"),
    objTNCAcceptBtn("com.tonik.mobile:id/Stash_TNC_Accept_Button", "com.tonik.mobile:id/Stash_TNC_Accept_Button", "id", "id"),
    objAcceptInvitationText("com.tonik.mobile:id/Stash_Acc_Invitation_Text", "com.tonik.mobile:id/Stash_Acc_Invitation_Text", "id", "id"),
    objAcceptInvitationBtn("com.tonik.mobile:id/Stash_Acc_Invitation_Button", "com.tonik.mobile:id/Stash_Acc_Invitation_Button", "id", "id"),
    objDeclineInvitationText("com.tonik.mobile:id/Stash_Decilne_Invitation_Text", "com.tonik.mobile:id/Stash_Decilne_Invitation_Text", "id", "id"),
    objDeclineInvitationBtn("com.tonik.mobile:id/Stash_Decilne_Invitation_Button", "com.tonik.mobile:id/Stash_Decilne_Invitation_Button", "id", "id"),
    objAcceptInvitationBtnEnabled("//*[contains(@resource-id,'Stash_Acc_Invitation_Button') and @enabled='true']", "//*[contains(@name,'Stash_Acc_Invitation_Button') and @enabled='true']", "xpath", "xpath"),
    objJoinThisStashHeaderText("com.tonik.mobile:id/Popup_sub_header_txt", "com.tonik.mobile:id/Popup_sub_header_txt", "id", "id"),
    objJoinThisStashText("com.tonik.mobile:id/Popup_single_btn_txt", "com.tonik.mobile:id/Popup_single_btn_txt", "id", "id"),
    objJoinThisStashBtn("com.tonik.mobile:id/Popup_single_btn_click", "com.tonik.mobile:id/Popup_single_btn_click", "id", "id"),
    objWelcomeToStashText("//*[contains(@text,'Welcome to')]", "//*[contains(@value,'Welcome to')]", "xpath", "xpath"),
    objGroupStashRules("//*[contains(@text,'Group Stash Rules')]", "//*[contains(@value,'Group Stash Rules')]", "xpath", "xpath"),
    objContributeNowText("//*[@text='Contribute now']", "//*[@value='Contribute now']", "xpath", "xpath"),
    objContributeNowBtn("//*[@text='Contribute now']/parent::*", "//*[@value='Contribute now']/parent::*", "xpath", "xpath"),
    objOwnerName("(//*[contains(@resource-id,'Stash_Saved_Amount_Text')]/preceding-sibling::android.widget.TextView)[2]", "//*[contains(@name,'Stash_Saved_Amount_Text')]/preceding-sibling::*", "xpath", "xpath"),
    objSelectMember("(//*[contains(@resource-id,'Stash_Saved_Amount_Text')]/parent::*)[2]", "(//*[contains(@value,'Pending')]/parent::*/parent::*/child::*)[1]", "xpath", "xpath"),
    objHeader("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objTotalContributionText("com.tonik.mobile:id/Stash_Total_Contribution_Text", "com.tonik.mobile:id/Stash_Total_Contribution_Text", "id", "id"),
    objStashSavedAmount("com.tonik.mobile:id/Stash_Saved_Amount_Value", "com.tonik.mobile:id/Stash_Saved_Amount_Value", "id", "id"),
    objNoContributionText("com.tonik.mobile:id/No_Contribution_Text", "com.tonik.mobile:id/No_Contribution_Text", "id", "id"),
    objStashSavedAmountText("(//*[contains(@resource-id,'Stash_Saved_Amount_Text')])[2]", "(//*[contains(@name,'Stash_Saved_Amount_Text')])[2]", "xpath", "xpath"),
    objStashTransactionDate("com.tonik.mobile:id/Stash_Transaction_Date", "com.tonik.mobile:id/Stash_Transaction_Date", "id", "id"),
    objDepositText("com.tonik.mobile:id/Stash_Deposit_TEXT0", "com.tonik.mobile:id/Stash_Deposit_TEXT0", "id", "id"),
    objCreditText("com.tonik.mobile:id/Stash_Transaction_Description", "com.tonik.mobile:id/Stash_Transaction_Description", "id", "id"),
    objTransactionAmount("com.tonik.mobile:id/Stash_Transaction_Amount0", "com.tonik.mobile:id/Stash_Transaction_Amount0", "id", "id"),
    objNextBtnEnabled("//*[contains(@resource-id,'Stash_Next_Btn') and @enabled='true']", "//*[contains(@name,'Stash_Next_Btn') and @enabled='true']", "xpath", "xpath"),
    objNextBtn("com.tonik.mobile:id/Stash_Next_Btn", "com.tonik.mobile:id/Stash_Next_Btn", "id", "id"),
    objContinueBtn("com.tonik.mobile:id/Popup_single_btn_click", "com.tonik.mobile:id/Popup_single_btn_click", "id", "id"),
    objAmountText("com.tonik.mobile:id/Stash_Amount_Text", "com.tonik.mobile:id/Stash_Amount_Text", "id", "id"),
    objAmountValue("com.tonik.mobile:id/Stash_Amount_Value", "com.tonik.mobile:id/Stash_Amount_Value", "id", "id"),
    objFromText("com.tonik.mobile:id/Stash_From_Text", "com.tonik.mobile:id/Stash_From_Text", "id", "id"),
    objMyTonikAccountText("com.tonik.mobile:id/Stash_Tonik_Account", "com.tonik.mobile:id/Stash_Tonik_Account", "id", "id"),
    objToText("com.tonik.mobile:id/Stash_To_Text", "com.tonik.mobile:id/Stash_To_Text", "id", "id"),
    objToValue("com.tonik.mobile:id/Stash_To_Value", "com.tonik.mobile:id/Stash_To_Value", "id", "id"),
    objKeyboardDoneButton("", "//*[@name='Done']", "", "xpath"),
    objStartATDText("com.tonik.mobile:id/Start_Time_Deposit_Text", "com.tonik.mobile:id/Start_Time_Deposit_Text", "id", "id"),
    objCloseStashLaterText("com.tonik.mobile:id/Close_Stash_Later_Text", "com.tonik.mobile:id/Close_Stash_Later_Text", "id", "id"),
    objStashOwnerText("com.tonik.mobile:id/Stash_Owner_Text", "com.tonik.mobile:id/Stash_Owner_Text", "id", "id"),
    objHistoryTransactionDetailsToAccount("//*[contains(@text,'To')]/following-sibling::*", "//*[@value='To']/parent::*/following-sibling::*/child::*", "xpath", "xpath"),
    objHeartSymbolOnTotalStashBalance("//*[contains(@text,'Total Stash balance')]/following-sibling::*[1]","","xpath","");
    private String android;
    private String ios;
    private String andPathType;
    private String iosPathType;
    StashPage(String android, String ios, String andPathType, String iosPathType) {
        this.android = android;
        this.ios = ios;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }
    StashPage(String android, String andPathType, String iosPathType) {
        this.android = android;
        this.ios = android;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }
    StashPage(String android, String andPathType) {
        this.android = android;
        this.ios = android;
        this.andPathType = andPathType;
        this.iosPathType = andPathType;
    }
    public static By getByOSType(String osType, StashPage objectName) {
        if (osType.equalsIgnoreCase("android") && objectName.andPathType.equalsIgnoreCase("xpath")) {
            return By.xpath(objectName.android);
        } else if (osType.equalsIgnoreCase("android") && objectName.andPathType.equalsIgnoreCase("id")) {
            return By.id(objectName.android);
        } else if (osType.equalsIgnoreCase("android") && objectName.andPathType.equalsIgnoreCase("name")) {
            return By.name(objectName.android);
        } else if (osType.equalsIgnoreCase("ios") && objectName.iosPathType.equalsIgnoreCase("xpath")) {
            return By.xpath(objectName.ios);
        } else if (osType.equalsIgnoreCase("ios") && objectName.iosPathType.equalsIgnoreCase("id")) {
            return By.id(objectName.ios);
        } else if (osType.equalsIgnoreCase("ios") && objectName.iosPathType.equalsIgnoreCase("name")) {
            return By.name(objectName.ios);
        }
        throw new IllegalArgumentException("Object not found: " + objectName);
    }
    public static By objStashNameOnTheImageOnStashScreen(String platform, String Name) {
        if (platform.equalsIgnoreCase("android")) {
            return By.id("com.tonik.mobile:id/Stash_Name_Text" + Name);
        } else {
            return By.id("com.tonik.mobile:id/Stash_Name_Text" + Name);
        }
    }
    public static By objAchievedOnTheImage(String platform, String Archieve) {
        if (platform.equalsIgnoreCase("android")) {
            return By.id("com.tonik.mobile:id/Stash_Current_Amount_Text" + Archieve);
        } else {
            return By.id("com.tonik.mobile:id/Stash_Current_Amount_Text" + Archieve);
        }
    }
    public static By objAddToStash(String platform, String Stash) {
        if (platform.equalsIgnoreCase("android")) {
            return By.id("com.tonik.mobile:id/Add_To_Stash_Text" + Stash);
        } else {
            return By.id("com.tonik.mobile:id/Add_To_Stash_Text" + Stash);
        }
    }
    public static By objManage(String platform, String manage) {
        if (platform.equalsIgnoreCase("android")) {
            return By.id("com.tonik.mobile:id/Manage_Stash_Text" + manage);
        } else {
            return By.id("com.tonik.mobile:id/Manage_Stash_Text" + manage);
        }
    }

    public static By objCredit(String platform, String Name) {
        if (platform.equalsIgnoreCase("android")) {
            return By.id("com.tonik.mobile:id/TXN_Description_TXT" + Name);
        } else {
            return By.id("com.tonik.mobile:id/TXN_Description_TXT" + Name);
        }
    }

    public static By objAllStashOption(String platform, String stashOption) {
        if (platform.equalsIgnoreCase("android")) {
            return By.xpath("//*[contains(@text,'" + stashOption + "')]");
        } else {
            return By.xpath("//*[contains(@value,'" + stashOption + "')]");
        }
    }
    public static By objTextButton(String platform, String text) {
        if (platform.equalsIgnoreCase("android")) {
            return By.xpath("//*[contains(@text,'" + text + "')]");
        } else {
            return By.xpath("//*[contains(@value,'" + text + "')]");
        }
    }
    public static By objContact(String platform, int number) {
        if (platform.equalsIgnoreCase("android")) {
            return By.id("com.tonik.mobile:id/Select_Contact_Button" + number + "");
        } else {
            return By.id("com.tonik.mobile:id/Select_Contact_Button" + number + "");
        }
    }
    public static By objMembersList(String platform, int number) {
        if (platform.equalsIgnoreCase("android")) {
            return By.id("com.tonik.mobile:id/Stash_Invitation_List_View" + number + "");
        } else {
            return By.id("com.tonik.mobile:id/Stash_Invitation_List_View" + number + "");
        }
    }
    public static By objNewStash(String platform, String name) {
        if (platform.equalsIgnoreCase("android")) {
            return By.xpath("//*[@text='" + name + "']");
        } else {
            return By.xpath("//*[@value='" + name + "']");
        }
    }
}