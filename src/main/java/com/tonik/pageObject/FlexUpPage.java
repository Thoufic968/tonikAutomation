package com.tonik.pageObject;

import org.openqa.selenium.By;

public enum FlexUpPage {
	//Loans tile page
	objLoansText("//*[@text='Loans']","//*[@value='Loans']","xpath","xpath"),
	objTodayLuckyDayHeader("//*[contains(@text,'Today’s your lucky')]","//*[contains(@value,'Today’s your lucky')]","xpath","xpath"),
	objSomethingExcitingDesc("//*[contains(@text,'Something exciting is waiting for you')]","//*[contains(@value,'Something exciting is waiting for you')]","xpath","xpath"),
	objLoansTile("//*[@text='Loans']/parent::*","//*[@value='Loans']/parent::*","xpath","xpath"),
	//next Installment page
	objNextInstallmentMessage("com.tonik.mobile:id/message","com.tonik.mobile:id/message","id","id"),
	objPaymentDueDate("com.tonik.mobile:id/PaymentDueDate","com.tonik.mobile:id/PaymentDueDate","id","id"),
	objAmountTitle("com.tonik.mobile:id/AmountTitle","com.tonik.mobile:id/AmountTitle","id","id"),
	objNextInstallmentSubMessage("com.tonik.mobile:id/SubMessage","com.tonik.mobile:id/SubMessage","id","id"),
	objPaymentDone("com.tonik.mobile:id/PaymentDone","com.tonik.mobile:id/PaymentDone","id","id"),
	//Upgrade to flexup page
	objFlexHeadText("com.tonik.mobile:id/Flexup_head_text","com.tonik.mobile:id/Flexup_head_text","id","id"),
	objFlexUpLearnText("com.tonik.mobile:id/Flexup_learn_text","com.tonik.mobile:id/Flexup_learn_text","id","id"),
	objFlexUpTileButton("com.tonik.mobile:id/Flexup_tile_click","com.tonik.mobile:id/Flexup_tile_click","id","id"),
	//Pay Hinga
	objPayHingaTitle("com.tonik.mobile:id/Payhinga_Title0","com.tonik.mobile:id/Payhinga_Title0","id","id"),
	objPayHingaSubTitle("com.tonik.mobile:id/Payhinga_SubTitle0","com.tonik.mobile:id/Payhinga_SubTitle0","id","id"),
	objLearnMoreText("com.tonik.mobile:id/Payhinga_Click_Text0","com.tonik.mobile:id/Payhinga_Click_Text0","id","id"),
	//Life insurance page
	objLifeInsuance("com.tonik.mobile:id/Payhinga_Title1","com.tonik.mobile:id/Payhinga_Title1","id","id"),
	objTapForMoreInfo("com.tonik.mobile:id/Payhinga_SubTitle1","com.tonik.mobile:id/Payhinga_SubTitle1","id","id"),
	objCoveredText("com.tonik.mobile:id/Payhinga_Click_Text1","com.tonik.mobile:id/Payhinga_Click_Text1","id","id"),
	objCloseLoanText("com.tonik.mobile:id/Full_payment_txt","com.tonik.mobile:id/Full_payment_txt","id","id"),
	objPaymentRecordText("com.tonik.mobile:id/History_text","com.tonik.mobile:id/History_text","id","id"),
	objDateText("com.tonik.mobile:id/History_sub_text","com.tonik.mobile:id/History_sub_text","id","id"),
	//Flex to the max
	objFlexToTheMaxHeader("//*[contains(@text,'Flex to the max')]","//*[contains(@value,'Flex to the max')]","xpath","xpath"),
	objIncreaseLoanAmountDesc("//*[contains(@text,'Increase your current loan amount')]","//*[contains(@value,'Increase your current loan amount')]","xpath","xpath"),
	objCustomButtonText("com.tonik.mobile:id/Custom_button_txt","com.tonik.mobile:id/Custom_button_txt","id","id"),
	objCustomButtonClick("com.tonik.mobile:id/Custom_button_click","com.tonik.mobile:id/Custom_button_click","id","id"),
	//How it works
	objHowItWorksHeader("//*[contains(@text,'How it works')]","//*[contains(@value,'How it works')]","xpath","xpath"),
	objHowItWorksDesc("//*[contains(@text,'Once booked, your Flex Up loan')]","//*[contains(@value,'Once booked, your Flex Up loan')]","xpath","xpath"),
	//Lets do the math
	objLetsDoTheMathHeader("//*[contains(@text,'do the math')]","//*[contains(@value,'do the math')]","xpath","xpath"),
	objLetsDoTheMathDesc("//*[contains(@text,'If your Flex Up loan amount')]","//*[contains(@value,'If your Flex Up loan amount')]","xpath","xpath"),
	//Flexin the possibilities
	objFlexinPossibilitiesHeader("//*[contains(@text,'Flexin’ the possibilities')]","//*[contains(@value,'Flexin’ the possibilities')]","xpath","xpath"),
	objSelectPaymentOptionText("//*[contains(@text,'Select a payment option')]","//*[contains(@value,'Select a payment option')]","xpath","xpath"),
	objChooseYourPayentTermText("//*[contains(@text,'Choose your payment term')]","//*[contains(@value,'Choose your payment term')]","xpath","xpath"),
	objNumberOfMonths("//*[@text='Months']/preceding-sibling::*[not(contains(@text,'₱'))]","//*[@value='Months']/parent::*/preceding-sibling::*/child::*[not(contains(@value,'₱'))]","xpath","xpath"),
	objLearnMoreAboutFlex("//*[contains(@text,'Learn more about Flex Up')]","//*[contains(@value,'Learn more about Flex Up')]","xpath","xpath"),
	objYourMonthlyInstallments("//*[contains(@text,'Your monthly installments')]","//*[contains(@value,'Your monthly installments')]","xpath","xpath"),
	objNoThanksText("//*[@text='No thanks']","//*[@value='No thanks']","xpath","xpath"),
	objNoThanksLink("//*[@text='No thanks']/parent::*","//*[@value='No thanks']/parent::*","xpath","xpath"),
	objIAcceptTheOfferText("//*[@text='I accept the offer']","//*[@value='I accept the offer']","xpath","xpath"),
	objIAcceptTheOfferButton("//*[@text='I accept the offer']/parent::*","//*[@value='I accept the offer']/parent::*","xpath","xpath"),
	objMonthlyInstallmentAmount("(//*[contains(@text,'Your monthly installments')]/following-sibling::*)[1]","(//*[contains(@value,'Your monthly installments')]/following-sibling::*)[1]","xpath","xpath"),
	objAmountYouReceiveText("(//*[contains(@text,'Your monthly installments')]/following-sibling::*)[2]","(//*[contains(@value,'Your monthly installments')]/following-sibling::*)[2]","xpath","xpath"),
	objAmountYouReceive("(//*[contains(@text,'Your monthly installments')]/following-sibling::*)[3]","(//*[contains(@value,'Your monthly installments')]/following-sibling::*)[3]","xpath","xpath"),
	//Summary loan screen
	objLoanSummaryHeader("com.tonik.mobile:id/Main_title_txt","com.tonik.mobile:id/Main_title_txt","id","id"),
	objLoanAmountText("//*[@text='Loan amount']","//*[@value='Loan amount']","xpath","xpath"),
	objLoanAmountValue("//*[@text='Loan amount']/parent::*/following-sibling::*[contains(@text,'₱')]","//*[@value='Loan amount']/parent::*/following-sibling::*","xpath","xpath"),
	objOutstandingBalanceText("//*[contains(@text,'Outstanding balance')]","//*[contains(@value,'Outstanding balance')]","xpath","xpath"),
	objOutstandingBalanceValue("//*[contains(@text,'Outstanding balance')]/parent::*/following-sibling::*[contains(@text,'₱')]","//*[contains(@value,'Outstanding balance')]/parent::*/following-sibling::*","xpath","xpath"),
	objAmountReceiveText("//*[contains(@text,'Amount you')]","//*[contains(@value,'Amount you')]","xpath","xpath"),
	objAmountReceiveValue("//*[contains(@text,'Amount you')]/parent::*/following-sibling::*[contains(@text,'₱')]","//*[contains(@value,'Amount you')]/parent::*/following-sibling::*","xpath","xpath"),
	objMonthlyInstallmentText("//*[contains(@text,'Monthly installment')] | //*[contains(@text,'Monthly Payment')]","//*[contains(@value,'Monthly installment')]","xpath","xpath"),
	objMonthlyInstallmentvalue("//*[contains(@text,'Monthly installment')]/parent::*/child::*[contains(@text,'₱')]","//*[contains(@value,'Monthly installment')]/following-sibling::XCUIElementTypeStaticText","xpath","xpath"),
	objInstallmentTermsText("//*[contains(@text,'Installment terms')]","//*[contains(@value,'Installment terms')]","xpath","xpath"),
	objInstallmentTermsValue("//*[contains(@text,'Installment terms')]/parent::*/following-sibling::* | //*[contains(@text,'Installment period')]/parent::*/following-sibling::*","//*[contains(@value,'Installment terms')]/parent::*/following-sibling::* | //*[contains(@value,'Installment period')]/parent::*/following-sibling::*","xpath","xpath"),
	objMonthlyAddOnRateText("//*[contains(@text,'Monthly add-on rate')]","//*[contains(@value,'Monthly add-on rate')]","xpath","xpath"),
	objMonthlyAddOnRateValue("//*[contains(@text,'Monthly add-on rate')]/parent::*/following-sibling::*","//*[contains(@value,'Monthly add-on rate')]/parent::*/following-sibling::*","xpath","xpath"),
	objProcessingFeeText("//*[contains(@text,'Processing fee')]","//*[contains(@value,'Processing fee')]","xpath","xpath"),
	objProcessingFeeValue("//*[contains(@text,'Processing fee')]/parent::*/following-sibling::*[contains(@text,'₱')]","//*[contains(@value,'Processing fee')]/parent::*/following-sibling::*","xpath","xpath"),
	objDocumentaryStampText("//*[contains(@text,'Documentary stamp')]","//*[contains(@value,'Documentary stamp')]","xpath","xpath"),
	objDocumentaryStampValue("//*[contains(@text,'Documentary stamp')]/parent::*/following-sibling::*[contains(@text,'₱')]","//*[contains(@value,'Documentary stamp')]/parent::*/following-sibling::*","xpath","xpath"),
	objSweetIAcceptText("com.tonik.mobile:id/Button_text","com.tonik.mobile:id/Button_text","id","id"),
	objSweetIAcceptButton("com.tonik.mobile:id/Button_click","com.tonik.mobile:id/Button_click","id","id"),
	//What do you need it for screen
	objHeader("com.tonik.mobile:id/Main_title_txt","com.tonik.mobile:id/Main_title_txt","id","id"),
	objSubTitle("com.tonik.mobile:id/Sub_title_txt","com.tonik.mobile:id/Sub_title_txt","id","id"),
	objMonthlyInstallment("//*[contains(@text,'Monthly installment')]/parent::*/following-sibling::*[contains(@text,'₱')]","//*[contains(@value,'Monthly installment')]/parent::*/following-sibling::*/child::*","xpath","xpath"),
	objEducationText("com.tonik.mobile:id/Education","com.tonik.mobile:id/Education","id","id"),
	objMedicalOrOtherEmergencyText("//*[contains(@text,'Medical or Other Emergency')]","//*[contains(@value,'Medical or Other Emergency')]","xpath","xpath"),
	objHouseFurnitureText("//*[contains(@text,'House Furniture')]","//*[contains(@value,'House Furniture')]","xpath","xpath"),
	objHomeRepairImprovementText("//*[contains(@text,'Home Repair')]","//*[contains(@value,'Home Repair')]","xpath","xpath"),
	objWeddingText("//*[contains(@text,'Wedding')]","//*[contains(@value,'Wedding')]","xpath","xpath"),
	objHouseholdApplianceText("//*[contains(@text,'Household Appliances')]","//*[contains(@value,'Household Appliances')]","xpath","xpath"),
	objMobileAccessoriesText("//*[contains(@text,'Mobile Phone and Accessories')]","//*[contains(@value,'Mobile Phone and Accessories')]","xpath","xpath"),
	objElectronicsText("//*[contains(@text,'Electronics')]","//*[contains(@value,'Electronics')]","xpath","xpath"),
	objTravelOrVacationText("//*[contains(@text,'Travel or Vacation')]","//*[contains(@value,'Travel or Vacation')]","xpath","xpath"),
	objLivingExpenseText("//*[contains(@text,'Living Expenses')]","//*[contains(@value,'Living Expenses')]","xpath","xpath"),
	objDeptRepaymentText("//*[contains(@text,'Debt Repayment')]","//*[contains(@value,'Debt Repayment')]","xpath","xpath"),
	objHelpingFamilyAndFriendText("//*[contains(@text,'Helping family and friends')]","//*[contains(@value,'Helping family and friends')]","xpath","xpath"),
	objOtherText("//*[@text='Other']","//*[@value='Other']","xpath","xpath"),
	objNextButton("com.tonik.mobile:id/Button_click","com.tonik.mobile:id/Button_click","id","id"),
	objNextButtonDisabled("//*[@text= 'Next']/parent::*[@enabled='false']","//*[@value= 'Next']/parent::*[@enabled='false']","xpath","xpath"),
	objNextButtonEnabled("//*[@text= 'Next']/parent::*[@enabled='true']","//*[@value= 'Next']/parent::*[@enabled='true']","xpath","xpath"),
	//Is This your current address screen
	objIHaveDifferentAddressText("//*[contains(@text,'different address')]","//*[contains(@value,'different address')]","xpath","xpath"),
	objThisIsMyCurrentAddressText("//*[contains(@text,'my current address')]","//*[contains(@value,'my current address')]","xpath","xpath"),
	objThisIsMyCurrentAddressBtn("//*[contains(@text,'my current address')]/parent::*","//*[contains(@value,'my current address')]/parent::*","xpath","xpath"),
	//Terms and Conditions
	objTermsConditionsCheck("//*[@resource-id='Terms_check_click']","//*[@name='Terms_check_click']","xpath","xpath"),
	objAcceptAndGiveConsentText("//*[@text='I accept and give my consent']","//*[@value='I accept and give my consent']","xpath","xpath"),
	objAcceptAndGiveConsentBtn("//*[@text='I accept and give my consent']/parent::*","//*[@value='I accept and give my consent']/parent::*","xpath","xpath"),
	//Don't forget luv screen
	objDontForgetLuvHeader("//*[contains(@text,'Don’t forget')]","//*[contains(@value,'Don’t forget')]","xpath","xpath"),
	objDontForgetLuvDesc("//*[contains(@text,'Your loan payment')]","//*[contains(@value,'Your loan payment')]","xpath","xpath"),
	//monthly installment summary screen
	objMonthlyPaymentDueText("//*[contains(@text,'First installment due date')]","//*[contains(@value,'First installment due date')]","xpath","xpath"),
	objMonthlyPaymentDueValue("//*[contains(@text,'First installment due date')]/following-sibling::*","//*[contains(@value,'First installment due date')]/parent::*/following-sibling::*/child::*","xpath","xpath"),
	objDueDateText("//*[contains(@text,'Due date')]","//*[contains(@value,'Due date')]","xpath","xpath"),
	objDueDateValue("//*[contains(@text,'Due date')]/following-sibling::*","//*[contains(@value,'Due date')]/parent::*/following-sibling::*/child::*","xpath","xpath"),
	objInstallmentPeriodText("//*[contains(@text,'Installment period')]","//*[contains(@value,'Installment period')]","xpath","xpath"),
	objInstallmentPeriodValue("//*[contains(@text,'Installment period')]/following-sibling::*","//*[contains(@value,'Installment period')]/parent::*/following-sibling::*/child::*","xpath","xpath"),
	objFirstIntallmentDDText("//*[contains(@text,'First installment')]","//*[contains(@value,'First installment')]","xpath","xpath"),
	objFirstIntallmentDDValue("//*[contains(@text,'First installment')]/following-sibling::*","//*[contains(@value,'First installment')]/parent::*/following-sibling::*/child::*","xpath","xpath"),
	objSignPromissoryNoteBtn("//*[contains(@text,'Sign Promissory Note')]/parent::*","//*[contains(@value,'Sign Promissory Note')]/parent::*","xpath","xpath"),
	getObjSignPromissoryNoteTxt("//*[contains(@text,'Sign Promissory Note')]","//*[contains(@value,'Sign Promissory Note')]","xpath","xpath"),
	objSignDisclosureStatementBtn("//*[contains(@text,'Sign Disclosure Statement')]/parent::*","//*[contains(@value,'Sign Disclosure Statement')]/parent::*","xpath","xpath"),
	objSignSignAmortizationBtn("//*[contains(@text,'Sign Amortization Schedule')]/parent::*","//*[contains(@value,'Sign Amortization Schedule')]/parent::*","xpath","xpath"),
	//Pop The champagne
	objPopTheChampagneTitle("//*[contains(@text,'champagne')]","//*[contains(@value,'champagne')]","xpath","xpath"),
	objAmountOnPopTheChampagne("(//*[contains(@text,'champagne')]/following-sibling::*)[1]","(//*[contains(@value,'champagne')]/following-sibling::*)[1]","xpath","xpath"),
	objLoanInfoIcon("com.tonik.mobile:id/Loan_Info_Icon_Click","com.tonik.mobile:id/Loan_Info_Icon_Click","id","id"),
	//Loan Information 
	objDateBookedText("//*[@text='Date booked']","//*[@value='Date booked']","xpath","xpath"),
	objDateBooked("//*[@text='Date booked']/parent::*/following-sibling::*","//*[@value='Date booked']/parent::*/following-sibling::*/child::*","xpath","xpath"),
	objLoanInfoInstallmentPeriod("//*[contains(@text,'Installment period')]/parent::*/following-sibling::*","//*[contains(@value,'Installment period')]/parent::*/following-sibling::*/child::*","xpath","xpath"),
	objRatesAndFeesClick("//*[@text='Rates and fees']/parent::*/following-sibling::*","//*[@value='Rates and fees']/parent::*/following-sibling::*","xpath","xpath"),
	objTermsAndConditionsClick("//*[@text='Terms and Conditions']/parent::*/following-sibling::*","//*[@value='Terms and Conditions']/parent::*/following-sibling::*","xpath","xpath"),
	objPromissoryNoteClick("//*[@text='Promissory Note']/parent::*/following-sibling::*","//*[@value='Promissory Note']/parent::*/following-sibling::*","xpath","xpath"),
	objDisclosureStatementClick("//*[@text='Disclosure Statement']/parent::*/following-sibling::*","//*[@value='Disclosure Statement']/parent::*/following-sibling::*","xpath","xpath"),
	objAmortizationScheduleClick("//*[@text='Amortization Schedule']/parent::*/following-sibling::*","//*[@value='Amortization Schedule']/parent::*/following-sibling::*","xpath","xpath"),
	objRatesAndFeesHeader("//*[@text='Rates and fees']","//*[@value='Rates and fees']","xpath","xpath"),
	objDownloadText("com.tonik.mobile:id/Download_btn_txt","com.tonik.mobile:id/Download_btn_txt","id","id"),
	objDownloadBtn("com.tonik.mobile:id/Download_btn_click","com.tonik.mobile:id/Download_btn_click","id","id"),
	objCloseMyLoanBtn("//*[@text='Close my loan']/parent::*","//*[@value='Close my loan']/parent::*","xpath","xpath"),
	objNoteDocument("android.widget.RelativeLayout","(//*[@type='XCUIElementTypeImage'])[1]","className","xpath"),
	//Drop the mic
	objDropTheMicText("//*[contains(@text,'Drop the mic')]","//*[contains(@value,'Drop the mic')]","xpath","xpath"),
	objYourLoanFinallyPaidTitle("//*[contains(@text,'Your loan is finally fully paid')]","//*[contains(@value,'Your loan is finally fully paid')]","xpath","xpath"),
	objBackToDashboardBtn("//*[@text='Back to Dashboard']/parent::*","//*[@value='Back to Dashboard']/parent::*","xpath","xpath"),
	objBackToDashboardText("//*[@text='Back to Dashboard']","//*[@value='Back to Dashboard']","xpath","xpath"),
	objYourTonikAccount("//*[@text='Your tonik account']","//*[@value='Your tonik account']","xpath","xpath"),
	//Are you Sure Pop up
	objAreYouSureText("com.tonik.mobile:id/Popup_header_txt","com.tonik.mobile:id/Popup_header_txt","id","id"),
	objAreYouSureDesc("com.tonik.mobile:id/Popup_subdesc_txt","com.tonik.mobile:id/Popup_subdesc_txt","id","id"),
	objOfferNotForMeText("com.tonik.mobile:id/Popup_negative_btn_txt","com.tonik.mobile:id/Popup_negative_btn_txt","id","id"),
	objOfferNotForMeBtn("com.tonik.mobile:id/Popup_negative_btn_click","com.tonik.mobile:id/Popup_negative_btn_click","id","id"),
	objTakeAnotherLookText("com.tonik.mobile:id/Popup_positive_btn_txt","com.tonik.mobile:id/Popup_positive_btn_txt","id","id"),
	objTakeAnotherLookBtn("com.tonik.mobile:id/Popup_positive_btn_click","com.tonik.mobile:id/Popup_positive_btn_click","id","id"),
	//Sorry it wasn't match screen
	objINeedHelpText("//*[contains(@text,'I need help')]","//*[contains(@value,'I need help')]","xpath","xpath"),
	objTheAmountText("//*[contains(@text,'The amount')]","//*[contains(@value,'The amount')]","xpath","xpath"),
	objNewLoanRightNowText("//*[contains(@text,'loan right now')]","//*[contains(@value,'loan right now')]","xpath","xpath"),
	objOtherReasonText("//*[contains(@text,'I have other reasons')]","//*[contains(@value,'I have other reasons')]","xpath","xpath"),
	objNextBtnEnabled("//*[@text='Next']/parent::*[@enabled='true']","//*[@value='Next']/parent::*[@enabled='true']","xpath","xpath"),
	objNextBtn("com.tonik.mobile:id/Custom_button_click","com.tonik.mobile:id/Custom_button_click","id","id"),
	objBackButton("//*[@resource-id='com.tonik.mobile:id/Header_left_click']","com.tonik.mobile:id/Header_left_click","xpath","id"),
	//Summary Page
	objLoanAmountTxt("//*[@resource-id='com.tonik.mobile:id/Loan amount']","com.tonik.mobile:id/Loan amount","xpath","id"),
	objLoanAmount("//*[@resource-id='com.tonik.mobile:id/Loan amount']/parent::android.view.ViewGroup/following-sibling::android.widget.TextView","//*[@value='Loan amount']/parent::*/following-sibling::*/child::*","xpath","xpath"),
	objMainTitleTxt("//*[@resource-id='com.tonik.mobile:id/Main_title_txt']","com.tonik.mobile:id/Main_title_txt","xpath","id"),
	objSubTitleTxt("com.tonik.mobile:id/Sub_title_txt","com.tonik.mobile:id/Sub_title_txt","id","id"),
	objMonthlyInstallmentSummary("//*[contains(@text,'Monthly installment summary')]","//*[contains(@value,'Monthly installment summary')]","xpath","xpath"),
	objConfirmBtn("//*[@resource-id='com.tonik.mobile:id/Button_txt']","com.tonik.mobile:id/Button_txt","xpath","id"),
	//Contact Us Page
	objContactUsButton("com.tonik.mobile:id/Header_right_click","com.tonik.mobile:id/Header_right_click","id","id"),
	objSignedPage("com.tonik.mobile:id/Header_text","com.tonik.mobile:id/Header_text","id","id"),
	objSignedPageSubHeader("com.tonik.mobile:id/Sub_header_text","com.tonik.mobile:id/Sub_header_text","id","id"),
	objPromissoryNoteTxt("Promissory Note","com.tonik.mobile:id/Promissory Note","id","id"),
	objDisclouseTxt("Disclosure Statement","com.tonik.mobile:id/Disclosure Statement","id","id"),
	objAmortizationtxt("Amortization Schedule","com.tonik.mobile:id/Amortization Schedule","id","id"),
	objReadyToSignButton("com.tonik.mobile:id/Button_txt","com.tonik.mobile:id/Button_txt","id","id"),
	objDownSubTxt("com.tonik.mobile:id/Down_sub_txt","com.tonik.mobile:id/Down_sub_txt","id","id"),
	objEraseBtn("com.tonik.mobile:id/Sign_erase_click","com.tonik.mobile:id/Sign_erase_click","id","id"),
	objInfoScreenMonthlyPaymentTxt("//*[@resource-id='com.tonik.mobile:id/Monthly payment']","com.tonik.mobile:id/Monthly payment","xpath","id"),
	objInfoScreenMonthlyPaymentAmount("//*[@resource-id='com.tonik.mobile:id/Monthly payment']/parent::android.view.ViewGroup/following-sibling::android.widget.TextView","//*[@value='Monthly payment']/parent::*/following-sibling::*/child::*","xpath","xpath"),
	objInfoScreenDueDateTxt("//*[@resource-id='com.tonik.mobile:id/Due date']","com.tonik.mobile:id/Due date","xpath","id"),
	objInfoScreenDueDate("//*[@resource-id='com.tonik.mobile:id/Due date']/parent::android.view.ViewGroup/following-sibling::android.widget.TextView","//*[@value='Due date']/parent::*/following-sibling::*/child::*","xpath","xpath"),
	objInfoScreeRateAndFees("//*[@resource-id='com.tonik.mobile:id/Rates and fees_txt0']","com.tonik.mobile:id/Rates and fees_txt0","xpath","id"),
	objInfoScreenTermsAndConditionTxt("//*[@resource-id='com.tonik.mobile:id/Terms and Conditions_txt0']","com.tonik.mobile:id/Terms and Conditions_txt0","xpath","id"),
	objInfoScreenPromissoryNoteTxt("//*[@resource-id='com.tonik.mobile:id/Promissory Note_txt1']","com.tonik.mobile:id/Promissory Note_txt1","xpath","id"),
	objInfoScreenDisclosureTxt("//*[@resource-id='com.tonik.mobile:id/Disclosure Statement_txt2']","com.tonik.mobile:id/Disclosure Statement_txt2","xpath","id"),
	objInfoScreenAmortizationTxt("//*[@resource-id='com.tonik.mobile:id/Amortization Schedule_txt3']","com.tonik.mobile:id/Amortization Schedule_txt3","xpath","id"),
	objYourLoanAlmostReadyPage("com.tonik.mobile:id/Head_title_text","com.tonik.mobile:id/Head_title_text","id","id"),
	objYourLoanAlmostReadyPageSubTitle("com.tonik.mobile:id/Txt_sub_header","com.tonik.mobile:id/Txt_sub_header","id","id"),
	objBackIcon("//*[@resource-id='com.tonik.mobile:id/Header_left_click']", "//*[@name='com.tonik.mobile:id/Header_left_click']","xpath","xpath"),
	objEffectiveInterestRateTxt("//*[@text='Effective interest rate']","//*[@value='Effective interest rate']","xpath","xpath"),
	getObjEffectiveInterestRate("//*[@text='Effective interest rate']/following-sibling::*","//*[@value='Effective interest rate']/following-sibling::*","xpath","xpath"),
	objBackBtn("com.tonik.mobile:id/Header_left_click","com.tonik.mobile:id/Header_left_click","id","id"),
	objPageTitle("com.tonik.mobile:id/Main_title_txt","com.tonik.mobile:id/Main_title_txt","id","id"),
	objUnprotectedloanBtn ("//*[@text='Proceed with an unprotected loan']", "//XCUIElementTypeStaticText[@value='Proceed with an unprotected loan']", "xpath", "xpath"),
	objContinueWithPayHingBtn ("//*[@text='Continue with PayHinga']", "//XCUIElementTypeStaticText[@value='Continue with PayHinga']", "xpath", "xpath"),
	objPaymentHolidayPayHingaSubTxt ("//*[@resource-id='com.tonik.mobile:id/Payhinga_Subtext_0']", "//*[@name='com.tonik.mobile:id/Payhinga_Subtext_0']", "xpath", "xpath"),
	objAdditionalPayHingsSubTxt ("//*[@resource-id='com.tonik.mobile:id/Payhinga_Subtext_1']", "//*[@name='com.tonik.mobile:id/Payhinga_Subtext_1']", "xpath", "xpath"),
	objPayhingaFeeTxt ("//*[@text='PayHinga fee']", "//*[@value='PayHinga fee']", "xpath", "xpath"),
	objPayhingaAmount ("//*[@text='PayHinga fee']/following-sibling::*", "//*[@value='PayHinga fee']/following-sibling::*", "xpath", "xpath"),
	objHistoryBtn ("//*[contains(@text,'History')]/parent::android.view.ViewGroup/child::android.view.ViewGroup/child::android.widget.Button", "(//*[contains(@value,'History')]/preceding-sibling::*)", "xpath", "xpath");
	private String android;
	private String ios;
	private String andPathType;
	private String iosPathType;
	FlexUpPage(String android, String ios, String andPathType, String iosPathType) {
		this.android = android;
		this.ios = ios;
		this.andPathType = andPathType;
		this.iosPathType = iosPathType;
	}
	public static By getByOSType(String osType, FlexUpPage objectName) {
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
		else if (osType.equalsIgnoreCase("android") && objectName.andPathType.equalsIgnoreCase("className"))
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
	public static By objCustomBtn(String osType, String value){
		if(osType.equalsIgnoreCase("Android")){
			return By.xpath("//*[@text='"+value+"']");
		}else{
			return By.xpath("//*[@value='"+value+"']");
		}
	}
	public static By objMonthsText(String osType, String terms){
		if(osType.equalsIgnoreCase("Android")){
			return By.xpath("(//*[@text='"+terms+"']/following-sibling::*)[2]");
		}else{
			return By.xpath("(//*[@value='"+terms+"']/parent::*/following-sibling::*/child::*)[1]");
		}
	}
	public static By objAmountReceiveText(String osType, String terms){
		if(osType.equalsIgnoreCase("Android")){
			return By.xpath("(//*[@text='"+terms+"']/following-sibling::*)[3]");
		}else{
			return By.xpath("(//*[@value='"+terms+"']/parent::*/following-sibling::*/child::*)[2]");
		}
	}
	public static By objPaymentTerms(String osType, String terms){
		if(osType.equalsIgnoreCase("Android")){
			return By.xpath("//*[@text='"+terms+"']");
		}else{
			return By.xpath("//*[@value='"+terms+"']");
		}
	}
	public static By objPaymentAmount(String osType, String terms){
		if(osType.equalsIgnoreCase("Android")){
			return By.xpath("(//*[@text='"+terms+"']/following-sibling::*)[1]");
		}else{
			return By.xpath("(//*[@value='"+terms+"']/following-sibling::*)[1]");
		}
	}
}