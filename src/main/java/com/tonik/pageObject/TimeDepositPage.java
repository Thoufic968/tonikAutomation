package com.tonik.pageObject;

import org.openqa.selenium.By;
public enum TimeDepositPage {
    //DashBoard Page
    objTimeDepositTextOnContainer("//*[@text='Time Deposit']","//*[@value='Time Deposit']","xpath","xpath"),
    //Investment Calculation  Page
    objHowMuchWillyouInvestText("com.tonik.mobile:id/TD_Calculator_Text_View_1","com.tonik.mobile:id/TD_Calculator_Text_View_1","id","id"),
    objInvestedAmount("com.tonik.mobile:id/TD_Calculator_Text_View_2","com.tonik.mobile:id/TD_Calculator_Text_View_2","id","id"),
    objEditCalculatorButton("com.tonik.mobile:id/TD_CalculatorButton_click","com.tonik.mobile:id/TD_CalculatorButton_click","id","id"),
    objEditInvestAmountTextField ("(//*[@class='android.widget.EditText'])[2]","(//*[@type='XCUIElementTypeTextField'])[2]","xpath","xpath"),
    objInvestmentAmtRangeText ("//*[contains(@text,'Should be between')]","//*[contains(@value,'Should be between')]","xpath","xpath"),
    objCalculatorSlideBar("com.tonik.mobile:id/TD_CalculatorSlider_View","com.tonik.mobile:id/TD_CalculatorSlider_View","id","id"),
    objHowLongMoneyGrowTxt("com.tonik.mobile:id/TD_Calculator_Text_View_3","com.tonik.mobile:id/TD_Calculator_Text_View_3","id","id"),
    objYouWillEarnInInterestTxt("com.tonik.mobile:id/TD_Calculator_Text_View_4","com.tonik.mobile:id/TD_Calculator_Text_View_4","id","id"),
    objInterestAmount("com.tonik.mobile:id/TD_Calculator_Text_View_5","com.tonik.mobile:id/TD_Calculator_Text_View_5","id","id"),
    objBeforeTaxesTxt("com.tonik.mobile:id/TD_Calculator_Text_View_6","com.tonik.mobile:id/TD_Calculator_Text_View_6","id","id"),
    objHowIsThisCalculatedLink("com.tonik.mobile:id/TD_Calculator_Text_View_7","com.tonik.mobile:id/TD_Calculator_Text_View_7","id","id"),
    objInvestmentDescription("com.tonik.mobile:id/TD_Calculator_Text_View_8","com.tonik.mobile:id/TD_Calculator_Text_View_8","id","id"),
    objIamInterestedButton("com.tonik.mobile:id/TD_CalculatorNext_Button_Text","com.tonik.mobile:id/TD_CalculatorNext_Button_Text","id","id"),
    objAllInvestmentMonthsGroup ("//*[@class='android.widget.HorizontalScrollView']/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup","//*[contains(@name,'TD_CalculatorList_Click')]","xpath","xpath"),
    objBackArrowButton("com.tonik.mobile:id/TD_Calculator_Back_Click","com.tonik.mobile:id/TD_Calculator_Back_Click","id","id"),
    objGetInTouchIcon("com.tonik.mobile:id/TD_Calculator_Button_Right_Click","com.tonik.mobile:id/TD_Calculator_Button_Right_Click","id","id"),
    objContinueButton ("//*[@text='Continue']","//*[@name='Continue']","xpath","xpath"),
    //SetUp Time Page
    objSetUpTimeHeader ("//*[@text=\"Set up a Time Deposit\"]","//*[@value='Set up a Time Deposit']","xpath","xpath"),
    objInvestAmtText("com.tonik.mobile:id/Set_Up_TD_Text_View_1","com.tonik.mobile:id/Set_Up_TD_Text_View_1","id","id"),
    objInvestedAmountvalue("com.tonik.mobile:id/Set_Up_TD_Text_View_2","com.tonik.mobile:id/Set_Up_TD_Text_View_2","id","id"),
    objTermText("com.tonik.mobile:id/Set_Up_TD_Text_View_3","com.tonik.mobile:id/Set_Up_TD_Text_View_3","id","id"),
    objTermValue("com.tonik.mobile:id/Set_Up_TD_Text_View_4","com.tonik.mobile:id/Set_Up_TD_Text_View_4","id","id"),
    objNickNameText("com.tonik.mobile:id/Set_Up_TDNickname","com.tonik.mobile:id/Set_Up_TDNickname","id","id"),
    objNickNameValue ("//*[@resource-id='com.tonik.mobile:id/Set_Up_TDNickname']/following-sibling::android.widget.TextView","(//*[contains(@name,'Set_Up_TDNickname')]/parent::*/following-sibling::*/child::*)[1]","xpath","xpath"),
    objInterestRatetext("//*[contains(@resource-id,'com.tonik.mobile:id/Set_Up_TDInterest rate (p.a.)')]","com.tonik.mobile:id/Set_Up_TDInterest rate (p.a.)","xpath","id"),
    objInterestRateValue("com.tonik.mobile:id/Set_Up_TD_Text_View_5","com.tonik.mobile:id/Set_Up_TD_Text_View_5","id","id"),
    objInterestRateEarnedValue("com.tonik.mobile:id/Set_Up_TD_Text_View_6","com.tonik.mobile:id/Set_Up_TD_Text_View_6","id","id"),
    objPayoutAtMaturityValue("com.tonik.mobile:id/Set_Up_TD_Text_View_7","com.tonik.mobile:id/Set_Up_TD_Text_View_7","id","id"),
    objEarlyWithdrawalValue("com.tonik.mobile:id/Set_Up_TD_Text_View_8","com.tonik.mobile:id/Set_Up_TD_Text_View_8","id","id"),
    objDateOfMaturityValue("com.tonik.mobile:id/Set_Up_TD_Text_View_9","com.tonik.mobile:id/Set_Up_TD_Text_View_9","id","id"),
    objInterestEarnedText("//*[contains(@resource-id,'com.tonik.mobile:id/Set_Up_TDInterest earned at maturity')]","com.tonik.mobile:id/Set_Up_TDInterest earned at maturity","xpath","id"),
    objPayOutMaturityText("//*[contains(@resource-id,'com.tonik.mobile:id/Set_Up_TDPayout at maturity')]","com.tonik.mobile:id/Set_Up_TDPayout at maturity","xpath","id"),
    objEarlyWithdrawalText("//*[contains(@resource-id,'com.tonik.mobile:id/Set_Up_TDEarly withdrawal')]","com.tonik.mobile:id/Set_Up_TDEarly withdrawal","xpath","id"),
    objDateOfMaturityText("//*[contains(@resource-id,'com.tonik.mobile:id/Set_Up_TDDate of maturity')]","com.tonik.mobile:id/Set_Up_TDDate of maturity","xpath","id"),
    objSetUpTimeDepositPageDescription("//*[contains(@text,'No Document Stamp Tax')]","//*[contains(@value,'No Document Stamp Tax')]","xpath","xpath"),
    objAcceptTermsAndConditionLink("//*[contains(@resource-id,'com.tonik.mobile:id/Set_Up_TDI accept the')]","com.tonik.mobile:id/Set_Up_TDI accept the","xpath","id"),
    objTermsAndConditionRadioBtn("com.tonik.mobile:id/Set_Up_TD_Toggle_Button","com.tonik.mobile:id/Set_Up_TD_Toggle_Button","id","id"),
    objOpenThisTDButton("com.tonik.mobile:id/Set_Up_TDNext_Button_Click","com.tonik.mobile:id/Set_Up_TDNext_Button_Click","id","id"),
    objNeedSomeFundPopupHeader("com.tonik.mobile:id/Popup_title_text","com.tonik.mobile:id/Popup_title_text","id","id"),
    objPopupDscription("com.tonik.mobile:id/Popup_Description","com.tonik.mobile:id/Popup_Description","id","id"),
    objOkayLetsGoBtn("//*[@resource-id='Okay, let’s go']","//*[@name='Okay, let’s go']","xpath","xpath"),
    objOutOfPopup("//*[@resource-id=\"com.tonik.mobile:id/Popup_title_text\"]/../../../preceding-sibling::android.view.ViewGroup","//*[@name ='com.tonik.mobile:id/Popup_title_text']/../../../../preceding-sibling::XCUIElementTypeOther","xpath","xpath"),
    objWeAreAlmostReadyText("//*[@text=\"We are almost\n" +
            "ready!\"]","//*[@value='We are almost\n" +
            "ready!']","xpath","xpath"),
    objTDCreateDescription("//*[@text=\"We are almost\n" +
            "ready!\"]/following-sibling::android.widget.TextView","//*[@value='We are almost\n" +
            "ready!']//following-sibling::XCUIElementTypeStaticText","xpath","xpath"),
    //woot woot page
    objWootWootText("//*[@text='Woot woot!']","//*[@value='Woot woot!']","xpath","xpath"),
    objWootWootDescription1("//*[@text='Woot woot!']/following-sibling::android.widget.TextView[1]","(//*[@value='Woot woot!']/parent::*/following-sibling::*/child::*)[1]","xpath","xpath"),
    objWootWootDescription2("//*[@text='Woot woot!']/following-sibling::android.widget.TextView[2]","(//*[@value='Woot woot!']/parent::*/following-sibling::*/child::*)[2]","xpath","xpath"),
    objViewTimeDepositDetailsLink("//*[@text='View Time Deposit Details']","//*[@value='View Time Deposit Details']","xpath","xpath"),
    objDoneBtn("//*[@text='Done']","//*[@value='Done']","xpath","xpath"),
    //Time Deposit Details Page
    objTimeDepositDetailsHeader("com.tonik.mobile:id/Main_title_txt","com.tonik.mobile:id/Main_title_txt","id","id"),
    //History Page
    objHistoryButton ("//*[@text='History']/preceding-sibling::android.view.ViewGroup","//*[@value='History']/preceding-sibling::*","xpath","xpath"),
    objAccountHistoryPageHeader ("//*[@text='Account History']","//*[@value='Account History']","xpath","xpath"),
    objTransactionDetailsPageHeader("com.tonik.mobile:id/Main_title_txt","com.tonik.mobile:id/Main_title_txt","id","id"),
    objPlusMarkInMyTDPage ("//*[@resource-id='com.tonik.mobile:id/My_TDButton_text']/preceding-sibling::android.view.ViewGroup","//*[@name='com.tonik.mobile:id/My_TDButton_text']/preceding-sibling::*","xpath","xpath"),
    objAvailableTdText("com.tonik.mobile:id/My_TDButton_text_1","com.tonik.mobile:id/My_TDButton_text_1","id","id"),
    objNewlyCreatedTD ("(//*[@resource-id='com.tonik.mobile:id/My_TDList_Click_0'])[1]","com.tonik.mobile:id/My_TDList_Click_0","xpath","id"),
    objInsufficientTDMessage("com.tonik.mobile:id/Popup_sub_header_txt","com.tonik.mobile:id/Popup_sub_header_txt","id","id"),
    objChangeInvestmentAmountButton("com.tonik.mobile:id/Popup_single_btn_click","com.tonik.mobile:id/Popup_single_btn_click","id","id"),
    objTSAAmount ("//*[@text='Your tonik account']/following-sibling::android.widget.TextView[1]","(//*[@value='Your tonik account']/following-sibling::*)[1]","xpath","xpath"),
    objTopupPageHeader("com.tonik.mobile:id/Main_title_txt","com.tonik.mobile:id/Main_title_txt","id","id"),
    objOnlineTopupOptionHeader("com.tonik.mobile:id/Main_title_txt","com.tonik.mobile:id/Main_title_txt","id","id"),
    objTopupAmountHeader ("//*[@text='Top up amount']","//*[@value='Top up amount']","xpath","xpath"),
    objTopupAmountTextfield ("//*[@resource-id='com.tonik.mobile:id/Main_title_txt']/../../following-sibling::android.view.ViewGroup/android.widget.EditText[2]","(//*[@type='XCUIElementTypeTextField'])[2]","xpath","xpath"),
    objTopupSuccessMessage ("//*[@text='Proceed to Pay']/following-sibling::android.widget.TextView","//*[@name='Proceed to Pay']/following-sibling::*/child::*","xpath","xpath"),
    objBackArrowBtnInTDDetailsPage ("//*[@resource-id='com.tonik.mobile:id/Header_left_click']/android.view.ViewGroup","//*[@name='com.tonik.mobile:id/Header_left_click']/XCUIElementTypeOther","xpath","xpath"),
    objGcashTopupOption ("//*[@text='GCash']","//*[@value='GCash']","xpath","xpath"),
    objMorethan5TDErrorMsg("com.tonik.mobile:id/Popup_sub_header_txt","com.tonik.mobile:id/Popup_sub_header_txt","id","id"),
    objNickNameTextField("android.widget.EditText","XCUIElementTypeTextField","className","className"),
    objTonikALlPageHeaderText("com.tonik.mobile:id/Main_title_txt","com.tonik.mobile:id/Main_title_txt","id","id"),
    objChangeInvestmentPageHeader ("//*[@text='Change your investment amount']","//*[@value='Change your investment amount']","xpath","xpath"),
    objEditInvestmentAmountTextfield ("//*[@text='Change your investment amount']/../../following-sibling::android.view.ViewGroup/android.widget.EditText[2]","(//*[@type='XCUIElementTypeTextField'])[2]","xpath","xpath"),
    objInvestmentAmountRangeErrorMesage ("//*[@text='Change your investment amount']/../../following-sibling::android.view.ViewGroup/android.widget.TextView[2]","//*[contains(@value,'Should be between')]","xpath","xpath"),
    objSelectTenurePageHeader ("//*[@text='Select tenure']","//*[@value='Select tenure']","xpath","xpath"),
    objPopupDescription("com.tonik.mobile:id/Popup_sub_header_txt","com.tonik.mobile:id/Popup_sub_header_txt","id","id"),
    objUnlockTDNowButton("com.tonik.mobile:id/Popup_single_btn_txt","com.tonik.mobile:id/Popup_single_btn_txt","id","id"),
    objFirstTDCreated ("(//*[contains(@resource-id,'com.tonik.mobile:id/My_TDList_Click')])[1]","(//*[contains(@name,'com.tonik.mobile:id/My_TDList_Click')])[1]","xpath","xpath"),
    //Respective TD Page
    objWithDrawEarlyLink("com.tonik.mobile:id/undefinedWithdraw_Text","com.tonik.mobile:id/undefinedWithdraw_Text","id","id"),
    objTDName("com.tonik.mobile:id/undefined_Text_View_1","com.tonik.mobile:id/undefined_Text_View_1","id","id"),
    objPayoutText("com.tonik.mobile:id/undefinedPayout","com.tonik.mobile:id/undefinedPayout","id","id"),
    objPayoutAmount ("//*[contains(@resource-id,'com.tonik.mobile:id/undefinedPayout')]/following-sibling::android.widget.TextView","//*[contains(@value,'Payout')]/../following-sibling::XCUIElementTypeOther/XCUIElementTypeStaticText","xpath","xpath"),
    objInterestRateText ("//*[@resource-id='com.tonik.mobile:id/undefinedInterest (p.a.): ']","//*[@name='com.tonik.mobile:id/undefinedInterest (p.a.): ']","xpath","xpath"),
    objInterestRateValueForIndividualTD("com.tonik.mobile:id/undefined_Text_View_2","com.tonik.mobile:id/undefined_Text_View_2","id","id"),
    //Withdraw page and rollover Page
    objEarnedText ("//*[@resource-id='com.tonik.mobile:id/undefinedEarned: ']","//*[@name='com.tonik.mobile:id/undefinedEarned: ']","xpath","xpath"),
    objEarnedAmount("com.tonik.mobile:id/undefined_Text_View_3","com.tonik.mobile:id/undefined_Text_View_3","id","id"),
    objRollOverTimeDeposiText ("//*[@text='Roll over time deposit']","//*[@value='Roll over time deposit']","xpath","xpath"),
    objRollOverTDToggleButton("com.tonik.mobile:id/undefined_Toggle_Button","com.tonik.mobile:id/undefined_Toggle_Button","id","id"),
    objRollOverOnPopupHeader ("//*[@resource-id='com.tonik.mobile:id/Wait...roll over what now?']","//*[@name='com.tonik.mobile:id/Wait...roll over what now?']","xpath","xpath"),
    objRollOverOnDescription ("//*[@resource-id='com.tonik.mobile:id/Wait...roll over what now?']/following-sibling::android.widget.TextView[1]","//*[@name='com.tonik.mobile:id/Wait...roll over what now?']/following-sibling::XCUIElementTypeStaticText","xpath","xpath"),
    objTermsAndConditionRadioButton("com.tonik.mobile:id/POPUP_CLICK","com.tonik.mobile:id/POPUP_CLICK","id","id"),
    objTermsAndConditionText("com.tonik.mobile:id/Checkbox_Button_click","com.tonik.mobile:id/Checkbox_Button_click","id","id"),
    objDoNotOverRollButton ("//*[@resource-id='Do not roll over']","//*[@name='Do not roll over']","xpath","xpath"),
    objRollOverThisTDButton("com.tonik.mobile:id/Popup_positive_button_Clicked","com.tonik.mobile:id/Popup_positive_button_Clicked","id","id"),
    objTdRollOverPopupMessage ("//*[contains(@text,'Time Deposit roll over')]","//*[contains(@value,'Time Deposit roll over')]","xpath","xpath"),
    objSwitchOffText("//*[@resource-id='com.tonik.mobile:id/Switch off?']","//*[@name='com.tonik.mobile:id/Switch off?']","xpath","xpath"),
    objRollOverOffDescription ("//*[@resource-id='com.tonik.mobile:id/Switch off?']/following-sibling::android.widget.TextView","//*[@name='com.tonik.mobile:id/Switch off?']/following-sibling::XCUIElementTypeStaticText","xpath","xpath"),
    objKeepItOnButton ("//*[@resource-id='Keep it on']","//*[@name='Keep it on']","xpath","xpath"),
    objIwantToTurnOffRollOverButton("//*[@resource-id='I want to turn off roll over']","//*[@name='I want to turn off roll over']","xpath","xpath"),
    //WithdrawPage
    objWithDrawPageDescription("com.tonik.mobile:id/Sub_title_txt","com.tonik.mobile:id/Sub_title_txt","id","id"),
    objYourPayoutAmount ("//*[contains(@resource-id,'com.tonik.mobile:id/Change of heart?Your payout')]","//*[contains(@name,'com.tonik.mobile:id/Change of heart?Your payout')]","xpath","xpath"),
    objYourPayOutAmountInWithDrawPage ("//*[contains(@resource-id,'com.tonik.mobile:id/Change of heart?Your payout')]/following-sibling::android.widget.TextView","//*[contains(@name,'com.tonik.mobile:id/Change of heart?Your payout')]/../following-sibling::XCUIElementTypeOther/XCUIElementTypeStaticText","xpath","xpath"),
    objOriginalInvestmentText ("//*[@resource-id='com.tonik.mobile:id/Original investment0']","com.tonik.mobile:id/Original investment0","xpath","name"),
    objOriginalInvestmentAmount ("//*[@resource-id='com.tonik.mobile:id/Original investment0']/following-sibling::android.widget.TextView","//*[@name='com.tonik.mobile:id/Original investment0']/../following-sibling::XCUIElementTypeOther/XCUIElementTypeStaticText","xpath","xpath"),
    objInterestYouCanEarnText ("//*[contains(@text,'Interest you can earn')]","//*[contains(@value,'Interest you can earn')]","xpath","xpath"),
    objInterestYouCanEarnAmount ("//*[contains(@resource-id,'Change of heart?_Text_View_2')]","//*[contains(@name,'Change of heart?_Text_View_2')]","xpath","xpath"),
    objWithdrawDescription ("//*[contains(@resource-id,'Change of heart?_Text_View_1')]","//*[contains(@name,'Change of heart?_Text_View_1')]","xpath","xpath"),
    objSeeTDDetailsLink ("(//*[contains(@resource-id,'Change of heart?Button_text')])[1]","(//*[contains(@name,'Change of heart?Button_text')])[1]","xpath","xpath"),
    objWithdrawEarlyLink("(//*[contains(@resource-id,'Change of heart?Button_text')])[2]","(//*[contains(@name,'Change of heart?Button_text')])[2]","xpath","xpath"),
    objKeepMyTimeDepositButton ("//*[@resource-id='com.tonik.mobile:id/Change of heart?Next_Button_Click']","//*[@name='com.tonik.mobile:id/Change of heart?Next_Button_Click']","xpath","xpath"),
    objGettingReadyToShredpageHeader ("//*[@resource-id='com.tonik.mobile:id/Getting ready to shred']","//*[@name='com.tonik.mobile:id/Getting ready to shred']","xpath","xpath"),
    objGettingReadyToShredpageDescription ("//*[@resource-id='com.tonik.mobile:id/Getting ready to shred']/following-sibling::android.widget.TextView","//*[@name='com.tonik.mobile:id/Getting ready to shred']/following-sibling::XCUIElementTypeOther/XCUIElementTypeStaticText","xpath","xpath"),
    objReadyToShredButton("com.tonik.mobile:id/Button_click","com.tonik.mobile:id/Button_click","id","id"),
    objGoingOncePageHeader("//*[@resource-id='com.tonik.mobile:id/Going once...']","com.tonik.mobile:id/Going once...","xpath","id"),
    objGoingTwicePageHeader ("//*[@resource-id='com.tonik.mobile:id/Going twice...']","com.tonik.mobile:id/Going twice...","xpath","id"),
    objDoneShreddingPageHeader ("//*[@resource-id='com.tonik.mobile:id/Done shredding!']","com.tonik.mobile:id/Done shredding!","xpath","id"),
    objDoneShreddingPageDescripion ("//*[@resource-id='com.tonik.mobile:id/Done shredding!']/following-sibling::android.widget.TextView","//*[@name='com.tonik.mobile:id/Done shredding!']/following-sibling::XCUIElementTypeOther/XCUIElementTypeStaticText","xpath","xpath"),
    //Email verification page
    objShreddingDoneButton("com.tonik.mobile:id/Next_Button_Click","com.tonik.mobile:id/Next_Button_Click","id","id"),
    objSendVerificationButton("com.tonik.mobile:id/Popup_positive_btn_txt","com.tonik.mobile:id/Popup_positive_btn_txt","id","id"),
    objUpdateEmailAddressLink("com.tonik.mobile:id/Popup_negative_btn_txt","com.tonik.mobile:id/Popup_negative_btn_txt","id","id"),
    objOtpPageDescription("com.tonik.mobile:id/Sub_title_view","com.tonik.mobile:id/Sub_title_view","id","id"),
    objCodeExpiringInSeconds("//*[contains(@text,'Code expiring in')]","//*[contains(@value,'Code expiring in')]","xpath","xpath"),
    objNooicePageHeader ("//*[contains(@text,'Nooice!')]","//*[contains(@value,'Nooice!')]","xpath","xpath"),
    objNooicePageDescription ("//*[contains(@text,'Nooice!')]/following-sibling::android.widget.TextView","//*[contains(@value,'Nooice!')]/following-sibling::XCUIElementTypeStaticText[2]","xpath","xpath"),
    objPinkySwearButton ("//*[contains(@text,' pinky swear')]","//*[contains(@value,' pinky swear')]","xpath","xpath"),
    objTDAmountInDashBoard ("//*[@text='Time Deposit']/following-sibling::android.widget.TextView[contains(@text,'₱')]","//*[@value='Time Deposit']/../following-sibling::XCUIElementTypeOther/XCUIElementTypeStaticText[1]","xpath","xpath"),
    objTimeDepositTileContainerDescription ("//*[@text='Time Deposit']/following-sibling::android.widget.TextView[2]","//*[@value='Time Deposit']/../following-sibling::XCUIElementTypeOther/XCUIElementTypeStaticText[2]","xpath","xpath"),
    objTimeDepositDetailsPageDescription ("//*[contains(@text,'20% withholding tax.')]","//*[contains(@value,'20% withholding tax.')]","xpath","xpath"),
    objIiconInTDpage("com.tonik.mobile:id/Header_right_click","com.tonik.mobile:id/Header_right_click","id","id"),
    //History Page
    objFirstHistoryDetailsHader ("//*[contains(@text,'Today')]/../following-sibling::android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[1]","//*[contains(@value,'Today')]/../following-sibling::XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[1]","xpath","xpath"),
    objFirstHistoryDetailsSubText ("//*[contains(@text,'Today')]/../following-sibling::android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[2]","//*[contains(@value,'Today')]/../following-sibling::XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[2]","xpath","xpath"),
    objFirstHistoryDetailsAmount ("//*[contains(@text,'Today')]/../following-sibling::android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[3]","//*[contains(@value,'Today')]/../following-sibling::XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeStaticText","xpath","xpath"),
    objChangeOfHeartPageHeader ("//*[@text='Change of heart?']","//*[@value='Change of heart?']","xpath","xpath"),
    objTonikSourceAccountNumber("//*[contains(@text,' ACCOUNT NUMBER')]","//*[contains(@value,' ACCOUNT NUMBER')]","xpath","xpath"),
    objOtpTextField("//*[contains(@text,'Code expiring')]/preceding-sibling::android.view.ViewGroup[2]","//*[contains(@value,'Code expiring')]/../../preceding-sibling::XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]","xpath","xpath"),
    objdoneButton("Done","Done","id","id"),
    objProcessing("//*[@text='Processing...']","//*[@value='Processing...']","xpath","xpath"),
    objOkButton("//*[@text='Ok']","//*[@value='Ok']","xpath","xpath"),
    objProceedToPayButton("//*[@text='Proceed to Pay']","//*[@label='Proceed to Pay']","xpath","xpath"),
    objTopUpButtonInDashBoard("//*[@text='Top up']/preceding-sibling::android.view.ViewGroup","//*[@value='Top up']/preceding-sibling::XCUIElementTypeOther","xpath","xpath"),
    obj1PercentInterestDescription("//*[contains(@text,'Tonik Savings Account for 6 months')]","//*[contains(@value,'Tonik Savings Account for 6 months')]","xpath","xpath"),
    objAdditionalInterestText("//*[@text='Additional interest rate (p.a.)']","//*[@value='Additional interest rate (p.a.)']","xpath","xpath"),
    objAdditionalInterestRate("//*[@text='Additional interest rate (p.a.)']/following-sibling::android.widget.TextView","//XCUIElementTypeOther[@label='Additional interest rate (p.a.)']/following-sibling::XCUIElementTypeOther/child::XCUIElementTypeStaticText","xpath","xpath"),
    obj1PercentInterestPayoutText("//*[@text='+1% interest at payout']","//*[@value='+1% interest at payout']","xpath","xpath"),
    obj1PercentInterestPayout("//*[@text='+1% interest at payout']/following-sibling::android.widget.TextView","//XCUIElementTypeOther[@label='+1% interest at payout']/following-sibling::XCUIElementTypeOther/child::XCUIElementTypeStaticText","xpath","xpath"),
    objSpecificConditionsApplyTxt("//*[@text='*Specific conditions apply.']","//XCUIElementTypeStaticText[@name='*Specific conditions apply.']","xpath","xpath"),
    obj6MonthRestrictionText("//*[@text='You can only have 1 active six-month time deposit.']","//XCUIElementTypeStaticText[@name='You can only have 1 active six-month time deposit.']","xpath","xpath"),
    objValidNickNameErrorMessage("//*[@text='Please enter a valid nick name']","//*[@value='Please enter a valid nick name']","xpath","xpath");
    private String android;
    private String ios;
    private String andPathType;
    private String iosPathType;
    TimeDepositPage(String android, String ios, String andPathType, String iosPathType) {
        this.android = android;
        this.ios = ios;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }
    public static By getByOSType(String osType, TimeDepositPage objectName) {
        if (osType.equalsIgnoreCase("android") && objectName.andPathType.equalsIgnoreCase("xpath"))
        {
            return By.xpath(objectName.android);
        }
        else if (osType.equalsIgnoreCase("android") && objectName.andPathType.equalsIgnoreCase("id"))
        {
            return By.id(objectName.android);
        }
        else if (osType.equalsIgnoreCase("android") && objectName.andPathType.equalsIgnoreCase("name"))
        {
            return By.name(objectName.android);
        }
        else if (osType.equalsIgnoreCase("android") && objectName.iosPathType.equalsIgnoreCase("className"))
        {
            return By.className(objectName.android);
        }
        else if (osType.equalsIgnoreCase("ios") && objectName.iosPathType.equalsIgnoreCase("xpath"))
        {
            return By.xpath(objectName.ios);
        }
        else if (osType.equalsIgnoreCase("ios") && objectName.iosPathType.equalsIgnoreCase("id"))
        {
            return By.id(objectName.ios);
        }
        else if (osType.equalsIgnoreCase("ios") && objectName.iosPathType.equalsIgnoreCase("name"))
        {
            return By.name(objectName.ios);
        }
        else if (osType.equalsIgnoreCase("ios") && objectName.iosPathType.equalsIgnoreCase("className"))
        {
            return By.className(objectName.ios);
        }
        throw new IllegalArgumentException("Object not found: " + objectName);
    }
    public static By objTransactionDetailsName(String osType, String detail) {
        if(osType.equalsIgnoreCase("Android")){
            return By.xpath("//*[@class='android.widget.ScrollView']/descendant::android.widget.TextView[@text='"+detail+"']");
        }else{
            return By.xpath("//*[@value='"+detail+"']");
        }
    }
    public static By objTransactionDetailsValue(String osType, String detail) {
        if(osType.equalsIgnoreCase("Android")){
            return By.xpath("//*[@class='android.widget.ScrollView']/descendant::android.widget.TextView[@text='"+detail+"']/following-sibling::android.widget.TextView");
        }else{
            return By.xpath("//*[@value='"+detail+"']/parent::*/following-sibling::*/child::*");
        }
    }
    public static  By objAllPageHeaderText(String osType, String headerText){
        if(osType.equalsIgnoreCase("Android")){
            return  By.xpath("//*[@resource-id='com.tonik.mobile:id/My_TD"+headerText+"']");
        }else{
            return By.xpath("//*[@name='com.tonik.mobile:id/My_TD"+headerText+"']");
        }
    }
    public static By objAllPageDescriptionText(String osType, String headerText) {
        if(osType.equalsIgnoreCase("Android")){
            return  By.xpath("//*[@resource-id='com.tonik.mobile:id/My_TD"+headerText+"']/following-sibling::android.widget.TextView");
        }else{
            return By.xpath("//*[@name='com.tonik.mobile:id/My_TD"+headerText+"']/following-sibling::*");
        }
    }
    public static By objTextButtons(String osType, String index){
        if(osType.equalsIgnoreCase("Android")){
            return  By.xpath("//*[@resource-id='com.tonik.mobile:id/My_TDNext_Button_Click"+index+"']/android.widget.TextView");
        }else{
            return  By.xpath("//*[@name='com.tonik.mobile:id/My_TDNext_Button_Click"+index+"']");
        }
    }
    public static  By objInvestmentMonthsText(String osType, String months) {
        if(osType.equalsIgnoreCase("Android")){
            return By.xpath("//*[contains(@resource-id,\"com.tonik.mobile:id/TD_CalculatorList\")]/android.widget.TextView[contains(@text,'"+months+"')][1]");
        }else{
            return By.xpath("//*[contains(@name,'com.tonik.mobile:id/TD_CalculatorList')]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[@value='"+months+"']");
        }
    }
    public static By objInterestRateBasedOnMonth(String osType, String month){
        if(osType.equalsIgnoreCase("Android")){
            return By.xpath("//*[contains(@resource-id,\"com.tonik.mobile:id/TD_CalculatorList\")]/android.widget.TextView[contains(@text,'"+month+"')][1]/following-sibling::android.widget.TextView[2]");
        }else{
            return By.xpath("//*[@value='"+month+"']/parent::*/following-sibling::*/child::*");
        }
    }
    public static By objTextsInEachMonthContainer(String osType, int index) {
        if(osType.equalsIgnoreCase("Android")){
            return By.xpath("(//*[@class='android.widget.HorizontalScrollView']/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup)["+index+"]//android.widget.TextView");
        }else{
            return By.xpath("//*[@type='XCUIElementTypeScrollView']/XCUIElementTypeOther/XCUIElementTypeOther["+index+"]/XCUIElementTypeOther/XCUIElementTypeOther/descendant::XCUIElementTypeStaticText");
        }
    }
    public static By objDetailsInTimeDepositDetailsPage(String osType, String details) {
        if(osType.equalsIgnoreCase("Android")){
            return By.xpath("//*[@text='"+details+"']");
        }else{
            return  By.xpath("//*[@value='"+details+"']");
        }
    }
    public static By objDetailsValueInTimeDepositDetailsPage(String osType, String details) {
        if(osType.equalsIgnoreCase("Android")){
            return  By.xpath("//*[@text='"+details+"']/following-sibling::android.widget.TextView");
        }else{
            return By.xpath("//*[@value='"+details+"']/../following-sibling::XCUIElementTypeOther/XCUIElementTypeStaticText");
        }
    }
    public static By objMonthsOption(String osType, String month){
        if(osType.equalsIgnoreCase("Android")){
            return By.xpath("//*[@class='android.widget.ScrollView']/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup//*[@text='"+month+" months']");
        }else{
            return By.xpath("//*[@value='"+month+" months']");
        }
    }
    public static By objNewlyCreatedTDBasedOnMaturityPayoutAmt(String osType, String payoutAmt) {
        if(osType.equalsIgnoreCase("Android")){
            return By.xpath("(//*[@text='"+payoutAmt+"'])[1]");
        }else{
            return By.xpath("(//*[@value='"+payoutAmt+"'])[1]");
        }
    }
    public static By objNewlyCreatedTDBasedOnNickName(String osType, String NickNAme) {
        if(osType.equalsIgnoreCase("Android")){
            return By.xpath("(//*[@text='"+NickNAme+"'])[1]");
        }else{
            return By.xpath("(//*[@value='"+NickNAme+"'])[1]");
        }
    }
    public static By objWithDrawTDUnderTDArchives(String osType, String payout) {
        if(osType.equalsIgnoreCase("Android")){
            return By.xpath("(//*[@text='Time Deposit Archives']/following-sibling::android.widget.ScrollView/descendant::android.widget.TextView[@text='" + payout + "'])[1]");
        }else{
            return By.xpath("//*[@value='Time Deposit Archives']/following-sibling::XCUIElementTypeOther/XCUIElementTypeScrollView/descendant::XCUIElementTypeStaticText[@value='"+payout+"']");
        }
    }
    public static By objTextButton(String osType, String option) {
        if(osType.equalsIgnoreCase("Android")){
            return By.xpath("(//*[contains(@text,'" + option + "')])[1]");
        }else{
            return By.xpath("(//*[contains(@value,'" + option + "')])[1]");
        }
    }
    public static By objEarlyWithDrawTextBasedOnNewWithdraw(String osType, String withdrawAmount) {
        if(osType.equalsIgnoreCase("Android")){
            return By.xpath("(//*[@text='Time Deposit Archives']/following-sibling::android.widget.ScrollView/descendant::android.widget.TextView[@text='"+withdrawAmount+"'])[1]/../preceding-sibling::android.view.ViewGroup[2]/descendant::android.widget.TextView[@text='Early Withdrawn']");
        }else{
            return By.xpath("//*[@value='Time Deposit Archives']/following-sibling::XCUIElementTypeOther/XCUIElementTypeScrollView/descendant::XCUIElementTypeStaticText[@value='"+withdrawAmount+"']/../../preceding-sibling::XCUIElementTypeOther/descendant::XCUIElementTypeStaticText[@value='Early Withdrawn']");
        }
    }
    public static By objInterestRate(String osType, String month){
        if(osType.equalsIgnoreCase("Android")){
            return By .xpath("//*[@text='"+month+"']/following-sibling::android.widget.TextView[contains(@text,'%')]");
        }else{
            return By.xpath("//XCUIElementTypeOther[@name='"+month+" mos']/following-sibling::XCUIElementTypeOther/child::XCUIElementTypeStaticText");
        }
    }
    public static By objMonth(String osType, String month){
        if(osType.equalsIgnoreCase("Android")){
            return By .xpath("//*[@text='"+month+"']");
        }else{
            return By.xpath("//XCUIElementTypeOther[@name='"+month+" mos']");
        }
    }
}
