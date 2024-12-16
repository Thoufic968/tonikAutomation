package com.tonik.pageObject;

import org.openqa.selenium.By;
public enum QuickLoanWithVasPage {
	objCreditBuilderTile ("//*[@text='Credit Builder']", "//*[@name='com.tonik.mobile:id/Credit Builder']", "xpath", "xpath"),
	objApplyForCredit ("//*[@resource-id='com.tonik.mobile:id/Txt_sub_title1']", "//*[@name='com.tonik.mobile:id/Txt_sub_title1']", "xpath", "xpath"),
	objNowWithPayHinga ("//*[contains(@resource-id,'com.tonik.mobile:id/Txt_header_view1')]/parent::*/following-sibling::*/child::*", "(//*[@name='Now with PayHinga!'])[1]", "xpath", "xpath"),
	verifyEmailPopUpHeader ("com.tonik.mobile:id/Popup_sub_header_txt", "com.tonik.mobile:id/Popup_sub_header_txt", "id", "id"),
	objUpdateEmailAddressBtn ("com.tonik.mobile:id/Popup_positive_btn_txt", "com.tonik.mobile:id/Popup_positive_btn_txt", "id", "id"),
	objVerifyNowBtn ("com.tonik.mobile:id/Popup_negative_btn_txt", "com.tonik.mobile:id/Popup_negative_btn_txt", "id", "id"),
	objVerifyNow ("//*[@text='Verify now']", "//*[@value='Verify now']", "xpath", "xpath"),
	objCountMeInButton ("//*[contains(@text,'Count me in!')]", "//*[contains(@value,'Count me in!')]", "xpath", "xpath"),
	objLoanTileHeader ("//*[contains(@text,'For your needs and wants')]", "", "xpath", "xpath"),
	objLoansTile ("//*[contains(@text,'Loans')] | //*[contains(@text,'Your Loan Account')]", "//*[@name='Loans'] | //*[contains(@value,'Your Loan Account')]", "xpath", "xpath"),
	objBorrowUpTxt ("//*[contains(@text,'Borrow up to ₱5 million for your dreams, shopping, or peace of mind.')]", "", "xpath", "xpath"),
	objNewLoanTileHeader ("//*[contains(@text,'Fast cash or easy payments?')]", "(//*[@name='Fast cash or easy payments?'])[2]", "xpath", "xpath"),
	objNewLoanTileSubHeader ("//*[contains(@text,'Find the right loan that fits your needs and wants. One day approval.')]", "//*[@name='Find the right loan that fits your needs and wants. One day approval.']", "xpath", "xpath"),
	objSmartSmall ("com.tonik.mobile:id/Txt_header1", "//*[@name='com.tonik.mobile:id/Txt_header1']", "id", "xpath"),
	objQuickLoanTileHeader ("//*[@resource-id='com.tonik.mobile:id/Txt_header1']", "//*[@name='com.tonik.mobile:id/Txt_header1']", "xpath", "xpath"),
	objQuickLoanTile ("//*[@resource-id='com.tonik.mobile:id/Quick loan']", "//*[@name='com.tonik.mobile:id/Quick loan']", "xpath", "xpath"),
	objQuickLoanSubTitle ("//*[@resource-id='com.tonik.mobile:id/Txt_sub_title1']", "//*[@name='com.tonik.mobile:id/Txt_sub_title1']", "xpath", "xpath"),
	objShoppingTileHeader ("//*[@resource-id='com.tonik.mobile:id/Txt_header2']", "//*[@name='com.tonik.mobile:id/Txt_header2']", "xpath", "xpath"),
	objShoppingTile ("//*[@resource-id='com.tonik.mobile:id/Shop installment loan']", "//*[@name='com.tonik.mobile:id/Shop installment loan']", "xpath", "xpath"),
	objShoppingSubTitle ("//*[@resource-id='com.tonik.mobile:id/Txt_sub_title2']", "//*[@name='com.tonik.mobile:id/Txt_sub_title2']", "xpath", "xpath"),
	objBackButton ("//*[@resource-id='com.tonik.mobile:id/Header_left_click']", "//*[contains(@name,'left_click')]", "xpath", "xpath"),
	objObjAsEasyScreen ("//*[@resource-id='com.tonik.mobile:id/Txt_header0']", "//*[@name='com.tonik.mobile:id/Txt_header0']", "xpath", "xpath"),
	objAsEasySubHeader ("//*[@resource-id='com.tonik.mobile:id/Txt_sub_header0']", "//*[@name='com.tonik.mobile:id/Txt_sub_header0']", "xpath", "xpath"),
	objGotTheseBtn ("//*[contains(@text,'I got these!')]", "", "xpath", "xpath"),
	objCallMeMaybePageHeader ("//*[@resource-id='com.tonik.mobile:id/Txt_header1']", "", "xpath", "xpath"),
	objCallMeMaybePageSubTitle ("//*[@resource-id='com.tonik.mobile:id/Txt_sub_header1']", "//*[@name='com.tonik.mobile:id/Txt_sub_header1']", "xpath", "xpath"),
	objGotItBtn ("//*[contains(@text,'Got it!')]", "//*[contains(@value,'Got it!')]", "xpath", "xpath"),
	objPayHingPageHeader ("//*[@resource-id='com.tonik.mobile:id/Txt_header2'] | //*[contains(@text,'Now with')]", "//*[@name='com.tonik.mobile:id/Txt_header2'] | //*[contains(@value,'Now with')]", "xpath", "xpath"),
	objPayHingPageSubTitle ("//*[@resource-id='com.tonik.mobile:id/Txt_sub_header2'] | //*[contains(@text,'Avail our newest offer to enjoy')]", "//*[@name='com.tonik.mobile:id/Txt_sub_header2'] | //*[contains(@value,'Avail our newest offer to enjoy')]", "xpath", "xpath"),
	objLetsGoBtn ("//*[contains(@text,'Let')]", "//*[contains(@value,'Let')]", "xpath", "xpath"),
	objHowMuchNeedPage ("//*[@resource-id='com.tonik.mobile:id/Button_text']", "//*[@name='com.tonik.mobile:id/Button_text']", "xpath", "xpath"),
	objPesosSymbol ("(//*[contains(@text,'₱')])[1]", "(//*[contains(@value,'₱')])[1]", "xpath", "xpath"),
	objAmount ("//*[contains(@text,'₱')]/following-sibling::android.widget.EditText", "//*[contains(@value,'₱')]/following-sibling::*/child::XCUIElementTypeTextField", "xpath", "xpath"),
	objContinueBtn ("(//*[@resource-id='com.tonik.mobile:id/Continue'])[2]", "(//*[@name='com.tonik.mobile:id/Continue'])[2]", "xpath", "xpath"),
	objHowMuchNeedPageBckBtn ("//*[@resource-id='com.tonik.mobile:id/Button_click']", "//*[@name='com.tonik.mobile:id/Button_click']", "xpath", "xpath"),
	objLoanCalculatorPage ("//*[@resource-id='com.tonik.mobile:id/Installment_selection_txt']", "//*[@name='com.tonik.mobile:id/Installment_selection_txt']", "xpath", "xpath"),
	objLoanCalculatorPageBackBtn ("//*[@resource-id='com.tonik.mobile:id/Left_click']", "", "xpath", "xpath"),
	AmountErrMsg ("(//*[contains(@text,'₱')])[2]", "(//*[contains(@value,'₱')])[2]", "xpath", "xpath"),
	objLoanTenure ("//*[contains(@text,'mos')]/preceding-sibling::*", "//*[contains(@value,'mos')]/preceding-sibling::*", "xpath", "xpath"),
	objYouareApplyingTxt ("//*[@resource-id='com.tonik.mobile:id/Txt_title']", "//*[@name='com.tonik.mobile:id/Txt_title']", "xpath", "xpath"),
	objTotalLoanAmount ("//*[@resource-id='com.tonik.mobile:id/Amount_display']", "//*[@name='com.tonik.mobile:id/Amount_display']", "xpath", "xpath"),
	objYourInstallmentTxt ("//*[@resource-id='com.tonik.mobile:id/Installment_selection_txt']", "//*[@name='com.tonik.mobile:id/Installment_selection_txt']", "xpath", "xpath"),
	objMonthlyInstallmentAmount ("//*[@resource-id='com.tonik.mobile:id/Monthly_installment_amt']", "//*[@name='com.tonik.mobile:id/Monthly_installment_amt']", "xpath", "xpath"),
	obActualAmountTxt ("//*[@resource-id='com.tonik.mobile:id/Autual_amt']", "//*[@name='com.tonik.mobile:id/Autual_amt']", "xpath", "xpath"),
	objPayhingCalcTxt ("//*[contains(@text,'Payment Holiday bundled with')]", "//*[contains(@value,'Payment Holiday bundled with')]", "xpath", "xpath"),
	objPayHingaTxtOnInsurance ("//*[contains(@text,'PayHinga')]", "(//*[contains(@value,'PayHinga')])[1]", "xpath", "xpath"),
	objPayHingaInformationIcon ("//*[contains(@text,'PayHinga')]/parent::android.view.ViewGroup/child::android.view.ViewGroup/child::android.view.ViewGroup", "//XCUIElementTypeStaticText[contains(@name,'Payment Holiday bundled with')]/parent::*/parent::*/following-sibling::*/child::*", "xpath", "xpath"),
	objUnprotectedloanBtn ("//*[@resource-id='com.tonik.mobile:id/Custom_button_txt']", "//*[@name='com.tonik.mobile:id/Custom_button_txt']", "xpath", "xpath"),
	objContinueWithPayHingBtn ("//*[@resource-id='com.tonik.mobile:id/Button_text']", "//*[@name='com.tonik.mobile:id/Button_text']", "xpath", "xpath"),
	objApplyingLoanPageBackBtn ("//*[@resource-id='com.tonik.mobile:id/Left_click']", "//*[@name='com.tonik.mobile:id/Left_click']", "xpath", "xpath"),
	objConatctUsBtn ("//*[@resource-id='com.tonik.mobile:id/Header_right_click']", "//*[@name='com.tonik.mobile:id/Header_right_click']", "xpath", "xpath"),
	objContactUsBtn1 ("//*[@resource-id='com.tonik.mobile:id/Right_click']", "//*[@name='com.tonik.mobile:id/Right_click']", "xpath", "xpath"),
	objNewTxt ("//*[contains(@text,'New!')]", "//*[contains(@value,'New!')]", "xpath", "xpath"),
	objProtectYourSelfTxt ("//*[contains(@text,'Protect Yourself with PayHinga')]", "//*[contains(@value,'Protect Yourself with PayHinga')]", "xpath", "xpath"),
	objProtectForUncertainties ("//*[@resource-id='com.tonik.mobile:id/Protection for life’s uncertainties']", "//*[@name='com.tonik.mobile:id/Protection for life’s uncertainties']", "xpath", "xpath"),
	objSecureTheFutureTxt ("//*[@resource-id='com.tonik.mobile:id/description0']", "//*[@name='com.tonik.mobile:id/description0']", "xpath", "xpath"),
	objEnjoyAMonthTxt ("//*[@resource-id='com.tonik.mobile:id/Enjoy a month-long payment break']", "//*[@name='com.tonik.mobile:id/Enjoy a month-long payment break']", "xpath", "xpath"),
	objPushBacktxt ("//*[@resource-id='com.tonik.mobile:id/description1']", "//*[@name='com.tonik.mobile:id/description1']", "xpath", "xpath"),
	objGetPaymentTxt ("//*[@resource-id='com.tonik.mobile:id/Get payment insurance']", "//*[@name='com.tonik.mobile:id/Get payment insurance']", "xpath", "xpath"),
	objPayHingaComesTxt ("//*[@resource-id='com.tonik.mobile:id/description2']", "//*[@name='com.tonik.mobile:id/description2']", "xpath", "xpath"),
	objIwantTobeProtectedBtn ("//*[@resource-id='com.tonik.mobile:id/Custom_button_txt']", "//*[@name='com.tonik.mobile:id/Custom_button_txt']", "xpath", "xpath"),
	objPayHingaTitleTxt ("//*[@resource-id='com.tonik.mobile:id/Payhinga_Title']", "//*[@name='com.tonik.mobile:id/Payhinga_Title']", "xpath", "xpath"),
	objHereisSummaryTxt ("//*[@resource-id='com.tonik.mobile:id/Main_title_txt']", "//*[@name='com.tonik.mobile:id/Main_title_txt']", "xpath", "xpath"),
	objLoanAmountTxt ("//*[@resource-id='com.tonik.mobile:id/Loan amount']", "//*[@name='com.tonik.mobile:id/Loan amount']", "xpath", "xpath"),
	objLoanAmount ("//*[@resource-id='com.tonik.mobile:id/Loan amount']/parent::android.view.ViewGroup/following-sibling::android.widget.TextView", "//*[@name='com.tonik.mobile:id/Loan amount']/parent::*/following-sibling::*", "xpath", "xpath"),
	objInstallMentTermsTxt ("//*[@resource-id='com.tonik.mobile:id/Installment terms']", "//*[@name='com.tonik.mobile:id/Installment terms']", "xpath", "xpath"),
	objInstallmentMonth ("//*[@resource-id='com.tonik.mobile:id/Installment terms']/parent::android.view.ViewGroup/following-sibling::android.widget.TextView", "//*[@name='com.tonik.mobile:id/Installment terms']/parent::*/following-sibling::*", "xpath", "xpath"),
	objMonthlyAddOnRate ("//*[@resource-id='com.tonik.mobile:id/Monthly add-on rate']", "//*[@name='com.tonik.mobile:id/Monthly add-on rate']", "xpath", "xpath"),
	objMonthlyAddOnRateInterestValue ("//*[@resource-id='com.tonik.mobile:id/[object Object]_value']", "//*[@name='com.tonik.mobile:id/[object Object]_value']", "xpath", "xpath"),
	objDeductedFromLoanTxt ("//*[@resource-id='com.tonik.mobile:id/Deducted from loan amount']", "//*[@name='com.tonik.mobile:id/Deducted from loan amount']", "xpath", "xpath"),
	objProcessingFeeTxt ("//*[@resource-id='com.tonik.mobile:id/Processing fee']", "//*[@name='com.tonik.mobile:id/Processing fee']", "xpath", "xpath"),
	objProcessingFeeAmount ("//*[@resource-id='com.tonik.mobile:id/Processing fee']/parent::android.view.ViewGroup/following-sibling::android.widget.TextView", "//*[@name='com.tonik.mobile:id/Processing fee']/parent::*/following-sibling::*", "xpath", "xpath"),
	objDocumentaryTxt ("//*[@resource-id='com.tonik.mobile:id/Documentary stamp tax']", "//*[@name='com.tonik.mobile:id/Documentary stamp tax']", "xpath", "xpath"),
	objDocumentaryTaxAmount ("//*[@resource-id='com.tonik.mobile:id/Documentary stamp tax']/parent::android.view.ViewGroup/following-sibling::android.widget.TextView", "//*[@name='com.tonik.mobile:id/Documentary stamp tax']/parent::*/following-sibling::*", "xpath", "xpath"),
	objPaymentHolidayPayHingaSubTxt ("//*[@resource-id='com.tonik.mobile:id/Payhinga_Subtext_0']", "//*[@name='com.tonik.mobile:id/Payhinga_Subtext_0']", "xpath", "xpath"),
	objAdditionalPayHingsSubTxt ("//*[@resource-id='com.tonik.mobile:id/Payhinga_Subtext_1']", "//*[@name='com.tonik.mobile:id/Payhinga_Subtext_1']", "xpath", "xpath"),
	objTotalMonthlyInstallmentTxt ("//*[@resource-id='com.tonik.mobile:id/Payhinga_Total_Value']", "//*[@name='com.tonik.mobile:id/Payhinga_Total_Value']", "xpath", "xpath"),
	objTotalMontlyInstallAmount ("//*[@resource-id='com.tonik.mobile:id/Payhinga_Total_Value']", "//*[@name='com.tonik.mobile:id/Payhinga_Total_Value']", "xpath", "xpath"),
	objHowItWorksBtn ("//*[@resource-id='com.tonik.mobile:id/Learn_Payhinga_Text']", "//*[@name='com.tonik.mobile:id/Learn_Payhinga_Text']", "xpath", "xpath"),
	objTotalMonthlyAmount ("//*[@resource-id='com.tonik.mobile:id/Payhinga_Total_Value']", "//*[@name='com.tonik.mobile:id/Payhinga_Total_Value']", "xpath", "xpath"),
	objFiguresAboveTxt ("//*[@resource-id='com.tonik.mobile:id/Footer_txt']", "//*[@name='com.tonik.mobile:id/Footer_txt']", "xpath", "xpath"),
	objSweetAcceptBtn ("//*[@resource-id='com.tonik.mobile:id/Button_text']", "//*[@name='com.tonik.mobile:id/Button_text']", "xpath", "xpath"),
	objHereIsSummaryPageBackBtn ("//*[@resource-id='com.tonik.mobile:id/Header_left_click']", "//*[@name='com.tonik.mobile:id/Header_left_click']", "xpath", "xpath"),
	objInprogressStatus ("//*[contains(@text,'In progress')]", "//*[contains(@value,'In progress')]", "xpath", "xpath"),
	objTapHereToContinue ("//*[contains(@text,'Tap here to continue.')]", "//*[contains(@value,'Tap here to continue.')]", "xpath", "xpath"),
	objRelaxPage ("//*[@resource-id='com.tonik.mobile:id/Main_title_txt']", "//*[@name='com.tonik.mobile:id/Main_title_txt']", "xpath", "xpath"),
	objApprovedStateTile ("//*[contains(@text,'approved!')]", "//*[contains(@value,'approved!')]", "xpath", "xpath"),
	objApproveLoanTileSubHeader ("//*[contains(@text,'Congratulations! Tap here to complete your application and')]", "//*[contains(@value,'Congratulations! Tap here to complete your application and')]", "xpath", "xpath"),
	objMainTitleTxt ("//*[@resource-id='com.tonik.mobile:id/Main_title_txt']", "//*[@name='com.tonik.mobile:id/Main_title_txt']", "xpath", "xpath"),
	objSubTitleTxt ("com.tonik.mobile:id/Sub_title_txt", "com.tonik.mobile:id/Sub_title_txt", "id", "id"),
	objApprovedAmountHeadTxt ("//*[@resource-id='Approved_head_txt']", "//*[@name='Approved_head_txt']", "xpath", "xpath"),
	objApprovedAmountTxt ("//*[@resource-id='Approved_amt_txt']", "//*[@name='Approved_amt_txt']", "xpath", "xpath"),
	objApprovedRecepientTxt ("//*[@resource-id='Approved_recepient_txt']", "//*[@name='Approved_recepient_txt']", "xpath", "xpath"),
	objTenureAmount ("com.tonik.mobile:id/Tenure_amount0", "com.tonik.mobile:id/Tenure_amount0", "id", "id"),
	objTenureMonth ("com.tonik.mobile:id/Tenure_month0", "com.tonik.mobile:id/Tenure_month0", "id", "id"),
	objAcceptOfferBtn ("com.tonik.mobile:id/Button_click", "com.tonik.mobile:id/Button_click", "id", "id"),
	objTellUsMonths ("//*[contains(@resource-id,'com.tonik.mobile:id/Sub_title_txt')]/parent::*/parent::*/following-sibling::android.widget.ScrollView/child::*/child::*/child::*/child::*/following-sibling::android.view.ViewGroup", "", "xpath", "xpath"),
	objFifthOfTheMonth ("//*[@resource-id='com.tonik.mobile:id/5th & 20th of the month0']", "//*[@name='com.tonik.mobile:id/5th & 20th of the month0']", "xpath", "xpath"),
	objTenthOftheMonth ("//*[@resource-id='com.tonik.mobile:id/10th & 25th of the month1']", "//*[@name='com.tonik.mobile:id/10th & 25th of the month1']", "xpath", "xpath"),
	objFifteenthOfTheMonth ("//*[@resource-id='com.tonik.mobile:id/15th & 30th of the month2']", "//*[@name='com.tonik.mobile:id/15th & 30th of the month2']", "xpath", "xpath"),
	objNextBtn ("//*[@text='Next']/parent::*","//*[@value='Next']/parent::* | //*[@value='Button_text']/parent::*","xpath","xpath"),
	objMonthOptions ("//*[@resource-id='com.tonik.mobile:id/Sub_title_txt']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.widget.ScrollView/child::android.view.ViewGroup/child::android.view.ViewGroup/child::android.view.ViewGroup/child::android.widget.TextView", "", "xpath", "xpath"),
	objMonthlyInstallmentSummary ("//*[contains(@text,'Monthly installment summary')]", "//*[contains(@value,'Monthly installment summary')]", "xpath", "xpath"),
	objInsatallmentTxt ("//*[@text='Installment period']", "//XCUIElementTypeStaticText[@label='Installment period']", "xpath", "xpath"),
	objInsatallmentTxt2 ("//*[@resource-id='com.tonik.mobile:id/Installment period2']", "//*[@name='com.tonik.mobile:id/Installment period2']", "xpath", "xpath"),
	objInstallmentPeriodMonth ("//*[@text='Installment period']/following-sibling::android.widget.TextView", "//XCUIElementTypeOther[@name='Installment period']/following-sibling::*/child::*", "xpath", "xpath"),
	objDueDate ("//*[@text='Due date']", "//XCUIElementTypeStaticText[@label='Due date']", "xpath", "xpath"),
	objDueDateMonth ("//*[@text='Due date']/following-sibling::android.widget.TextView", "//XCUIElementTypeOther[@name='Due date']/following-sibling::*/child::*", "xpath", "xpath"),
	objFirstInstallmentTxt ("//*[@text='First installment due date']", "//XCUIElementTypeStaticText[@label='First installment due date']", "xpath", "xpath"),
	objInstallmentDuedate ("//*[@text='First installment due date']/following-sibling::android.widget.TextView", "//XCUIElementTypeOther[@name='First installment due date']/following-sibling::*/child::*", "xpath", "xpath"),
	objPayhingaFeeTxt ("//*[@resource-id='com.tonik.mobile:id/PayHinga fee3']", "//*[@name='com.tonik.mobile:id/PayHinga fee3']", "xpath", "xpath"),
	objPayhingaAmount ("//*[@resource-id='com.tonik.mobile:id/PayHinga feeval3']", "//*[@name='com.tonik.mobile:id/PayHinga feeval3']", "xpath", "xpath"),
	objMonthlyInstallmentTxt ("//*[contains(@text,'Total Monthly installment')]", "//*[contains(@value,'Total Monthly installment')]", "xpath", "xpath"),
	objMonthlyIstallmentAmount ("//*[contains(@text,'Total Monthly installment')]/following-sibling::android.widget.TextView", "//*[contains(@value,'Total Monthly installment')]/following-sibling::XCUIElementTypeStaticText", "xpath", "xpath"),
	objConfirmBtn ("//*[@resource-id='com.tonik.mobile:id/Button_txt']", "//*[@name='com.tonik.mobile:id/Button_txt']", "xpath", "xpath"),
	objGetInTouchPage ("//*[contains(@text,'Get in touch')]", "//*[contains(@value,'Get in touch')]", "xpath", "xpath"),
	objContactUsButton ("com.tonik.mobile:id/Header_right_click", "com.tonik.mobile:id/Header_right_click", "id", "id"),
	objSignedPage ("com.tonik.mobile:id/Header_text", "com.tonik.mobile:id/Header_text", "id", "id"),
	objSignedPageSubHeader ("com.tonik.mobile:id/Sub_header_text", "com.tonik.mobile:id/Sub_header_text", "id", "id"),
	objPromissoryNoteTxt ("Promissory Note", "Promissory Note", "id", "id"),
	objDisclouseTxt ("Disclosure Statement", "Disclosure Statement", "id", "id"),
	objAmortizationtxt ("Amortization Schedule", "Amortization Schedule", "id", "id"),
	objSignPromissoryNoteBtn ("//*[contains(@text,'Sign Promissory Note')]", "//*[contains(@value,'Sign Promissory Note')]", "xpath", "xpath"),
	objSignDisclosureStatementBtn ("//*[contains(@text,'Sign Disclosure Statement')]", "//*[contains(@value,'Sign Disclosure Statement')]", "xpath", "xpath"),
	objSignAmortizationScheduleBtn ("//*[contains(@text,'Sign Amortization Schedule')]", "//*[contains(@value,'Sign Amortization Schedule')]", "xpath", "xpath"),
	objReadyToSignButton ("com.tonik.mobile:id/Button_txt", "com.tonik.mobile:id/Button_txt", "id", "id"),
	objDownSubTxt ("com.tonik.mobile:id/Down_sub_txt", "com.tonik.mobile:id/Down_sub_txt", "id", "id"),
	objSignPromissryBtn ("com.tonik.mobile:id/Custom_button_click", "", "id", "xpath"),
	objEraseBtn ("com.tonik.mobile:id/Sign_erase_click", "com.tonik.mobile:id/Sign_erase_click", "id", "id"),
	objMainDashBoard ("//*[contains(@text,'Your tonik account')]", "//*[contains(@value,'Your tonik account')]", "xpath", "xpath"),
	objBalanceOnScreen ("(//*[contains(@text,'Your tonik account')]/following-sibling::android.widget.TextView)[1]", "(//XCUIElementTypeStaticText[@name='Your tonik account']/following-sibling::XCUIElementTypeStaticText)[1]", "xpath", "xpath"),
	objLoanTileAfterApproved ("//*[contains(@text,'Yes')]", "//*[contains(@value,'Yes')]", "xpath", "xpath"),
	objYourLoanHasBeenAdded ("//*[contains(@text,'Your loan has been added to your Tonik account.')]", "//*[contains(@value,'Your loan has been added to your Tonik account.')]", "xpath", "xpath"),
	objIwantToCloseBtn ("com.tonik.mobile:id/Full_payment_txt", "com.tonik.mobile:id/Full_payment_txt", "id", "id"),
	objNextInstallmentTxt ("com.tonik.mobile:id/message", "com.tonik.mobile:id/message", "id", "id"),
	objLoanDashBoardDueDate ("com.tonik.mobile:id/PaymentDueDate", "com.tonik.mobile:id/PaymentDueDate", "id", "id"),
	objLoanDashBoardEmiAmount ("com.tonik.mobile:id/AmountTitle", "com.tonik.mobile:id/AmountTitle", "id", "id"),
	objThisAmountMessage ("com.tonik.mobile:id/SubMessage", "com.tonik.mobile:id/SubMessage", "id", "id"),
	objPaymentDoneOf ("com.tonik.mobile:id/PaymentDone", "com.tonik.mobile:id/PaymentDone", "id", "id"),
	objInfoButton ("com.tonik.mobile:id/Loan_Info_Icon_Click", "com.tonik.mobile:id/Loan_Info_Icon_Click", "id", "id"),
	objPayhingaTitle ("com.tonik.mobile:id/Payhinga_Title0", "com.tonik.mobile:id/Payhinga_Title0", "id", "id"),
	objThreeInstallmentTxt ("com.tonik.mobile:id/Payhinga_SubTitle0", "com.tonik.mobile:id/Payhinga_SubTitle0", "id", "id"),
	objLifeInsurancePayhinga ("com.tonik.mobile:id/Payhinga_Title1", "com.tonik.mobile:id/Payhinga_Title1", "id", "id"),
	objTapMoreInfoTxt ("com.tonik.mobile:id/Payhinga_SubTitle1", "com.tonik.mobile:id/Payhinga_SubTitle1", "id", "id"),
	objLearnMoreBtn ("com.tonik.mobile:id/Payhinga_Click_Text0", "com.tonik.mobile:id/Payhinga_Click_Text0", "id", "id"),
	objCoveredBtn ("com.tonik.mobile:id/Payhinga_Click_Text1", "com.tonik.mobile:id/Payhinga_Click_Text1", "id", "id"),
	objIwantToCloseLoanButton ("com.tonik.mobile:id/Full_payment_txt", "com.tonik.mobile:id/Full_payment_txt", "id", "id"),
	objPaymentRecordTxt ("com.tonik.mobile:id/History_text", "com.tonik.mobile:id/History_text", "id", "id"),
	objTodaysDate ("com.tonik.mobile:id/History_sub_text", "com.tonik.mobile:id/History_sub_text", "id", "id"),
	objMoneyCredited ("//*[@text='Money Credited']", "//XCUIElementTypeStaticText[@name='com.tonik.mobile:id/Money Credited0']", "xpath", "xpath"),
	objMoneyCreditedAmount ("//*[@text='Money Credited']/following-sibling::android.widget.TextView", "//*[@name='com.tonik.mobile:id/Money Credited0']/parent::*/following-sibling::XCUIElementTypeStaticText", "xpath", "xpath"),
	objFundTransferredTo ("Funds Transferred to your TSA0", "com.tonik.mobile:id/Funds Transferred to your TSA0", "id", "id"),
	objInfoScreenLoanAmountTxt ("com.tonik.mobile:id/Loan amount", "com.tonik.mobile:id/Loan amount", "id", "id"),
	objInfoScreenLaonAmount ("//*[@resource-id='com.tonik.mobile:id/Loan amount']/parent::android.view.ViewGroup/following-sibling::android.widget.TextView", "//*[@name='com.tonik.mobile:id/Loan amount']/parent::*/following-sibling::*", "xpath", "xpath"),
	objInfoScreenBorrowDateTxt ("//*[@resource-id='com.tonik.mobile:id/Borrowing date']", "//*[@name='com.tonik.mobile:id/Borrowing date']", "xpath", "xpath"),
	objInfoScreenBorrowDate ("//*[@resource-id='com.tonik.mobile:id/Borrowing date']/parent::android.view.ViewGroup/following-sibling::android.widget.TextView", "//*[@name='com.tonik.mobile:id/Borrowing date']/parent::*/following-sibling::*", "xpath", "xpath"),
	objInfoScreenInstallmentTermsTxt ("//*[contains(@text,'Installment')]", "//XCUIElementTypeStaticText[contains(@label,'Installment')]", "xpath", "xpath"),
	objInfoScreenMonth ("//*[contains(@text,'Installment')]/parent::android.view.ViewGroup/following-sibling::android.widget.TextView", "//XCUIElementTypeStaticText[contains(@label,'Installment')]/parent::*/following-sibling::*", "xpath", "xpath"),
	objInfoScreenMonthlyPaymentTxt ("//*[@resource-id='com.tonik.mobile:id/Monthly payment']", "//*[@name='com.tonik.mobile:id/Monthly payment']", "xpath", "xpath"),
	objInfoScreenMonthlyPaymentAmount ("//*[@resource-id='com.tonik.mobile:id/Monthly payment']/parent::android.view.ViewGroup/following-sibling::android.widget.TextView", "//*[@name='com.tonik.mobile:id/Monthly payment']/parent::*/following-sibling::*/child::XCUIElementTypeStaticText", "xpath", "xpath"),
	objInfoScreenDueDateTxt ("//*[@resource-id='com.tonik.mobile:id/Due date']", "//*[@name='com.tonik.mobile:id/Due date']", "xpath", "xpath"),
	objInfoScreenDueDate ("//*[@resource-id='com.tonik.mobile:id/Due date']/parent::android.view.ViewGroup/following-sibling::android.widget.TextView", "//*[@name='com.tonik.mobile:id/Due date']/parent::*/following-sibling::*/child::XCUIElementTypeStaticText", "xpath", "xpath"),
	objInfoScreenInsuranceCoverageTxt ("//*[@resource-id='com.tonik.mobile:id/Insurance coverage worth']", "//*[@name='com.tonik.mobile:id/Insurance coverage worth']", "xpath", "xpath"),
	objInfoScreenInsuranceAmount ("//*[@resource-id='com.tonik.mobile:id/Insurance coverage worth']/parent::android.view.ViewGroup/following-sibling::android.widget.TextView", "//*[@name='com.tonik.mobile:id/Insurance coverage worth']/parent::*/following-sibling::*/child::XCUIElementTypeStaticText", "xpath", "xpath"),
	objInfoScreeRateAndFees ("//*[@resource-id='com.tonik.mobile:id/Rates and fees_txt0']", "//*[@name='com.tonik.mobile:id/Rates and fees_txt0']", "xpath", "xpath"),
	objInfoScreenLoanDocumentTxt ("//*[@resource-id='com.tonik.mobile:id/Loan Documents_txt']", "//*[@name='com.tonik.mobile:id/Loan Documents_txt']", "xpath", "xpath"),
	objInfoScreenTermsAndConditionTxt ("//*[@resource-id='com.tonik.mobile:id/Terms and Conditions_txt0']", "//*[@name='com.tonik.mobile:id/Terms and Conditions_txt0']", "xpath", "xpath"),
	objInfoScreenPromissoryNoteTxt ("//*[@resource-id='com.tonik.mobile:id/Promissory Note_txt1']", "//*[@name='com.tonik.mobile:id/Promissory Note_txt1']", "xpath", "xpath"),
	objInfoScreenDisclosureTxt ("//*[@resource-id='com.tonik.mobile:id/Disclosure Statement_txt2']", "//*[@name='com.tonik.mobile:id/Disclosure Statement_txt2']", "xpath", "xpath"),
	objInfoScreenAmortizationTxt ("//*[@resource-id='com.tonik.mobile:id/Amortization Schedule_txt3']", "//*[@name='com.tonik.mobile:id/Amortization Schedule_txt3']", "xpath", "xpath"),
	objInfoScreenProofOfCoverTxt ("//*[@resource-id='com.tonik.mobile:id/Proof of Cover_txt4']", "//*[@name='com.tonik.mobile:id/Proof of Cover_txt4']", "xpath", "xpath"),
	objInfoScreenAboutPayhingaTxt ("//*[@resource-id='com.tonik.mobile:id/About PayHinga_txt5']", "//*[@name='com.tonik.mobile:id/About PayHinga_txt5']", "xpath", "xpath"),
	objRatesPageMonthlyAddOnRate ("(//*[@resource-id='com.tonik.mobile:id/Monthly add-on rate']/following-sibling::android.widget.TextView)[1]", "(//*[@name='com.tonik.mobile:id/Monthly add-on rate']/following-sibling::XCUIElementTypeStaticText)[1]", "xpath", "xpath"),
	objAddOnRateSubTxt ("//*[contains(@text,'This is equivalent to a monthly contractual interest rate of ')]", "(//*[@name='com.tonik.mobile:id/Monthly add-on rate']/following-sibling::XCUIElementTypeStaticText)[2]", "xpath", "xpath"),
	objEffectiveInterestRate ("Effective interest rate", "com.tonik.mobile:id/Effective interest rate", "id", "id"),
	objEffectiveInterestRateValue ("//*[@resource-id='com.tonik.mobile:id/Effective interest rate']/following-sibling::android.widget.TextView", "//*[@name='com.tonik.mobile:id/Effective interest rate']/following-sibling::XCUIElementTypeStaticText", "xpath", "xpath"),
	objRatesPageProcessingFee ("//*[@resource-id='com.tonik.mobile:id/Processing fee']/following-sibling::android.widget.TextView", "(//*[@name='com.tonik.mobile:id/Processing fee']/following-sibling::XCUIElementTypeStaticText)[1]", "xpath", "xpath"),
	objDocStampTaxTxt ("//*[@resource-id='com.tonik.mobile:id/Document stamp tax']", "//*[@name='com.tonik.mobile:id/Document stamp tax']", "xpath", "xpath"),
	objDocStampTaxAmount ("//*[@resource-id='com.tonik.mobile:id/Document stamp tax']/following-sibling::android.widget.TextView", "//*[@name='com.tonik.mobile:id/Document stamp tax']/following-sibling::XCUIElementTypeStaticText", "xpath", "xpath"),
	objNetProceedsTxt ("//*[@resource-id='com.tonik.mobile:id/Net proceeds']", "//*[@name='com.tonik.mobile:id/Net proceeds']", "xpath", "xpath"),
	objNetProceedsAmunt ("//*[@resource-id='com.tonik.mobile:id/Net proceeds']/following-sibling::android.widget.TextView", "//*[@name='com.tonik.mobile:id/Net proceeds']/following-sibling::XCUIElementTypeStaticText", "xpath", "xpath"),
	objMonthlyPayhingaFee ("//*[@resource-id='com.tonik.mobile:id/Monthly PayHinga fee']", "//*[@name='com.tonik.mobile:id/Monthly PayHinga fee']", "xpath", "xpath"),
	objMonthlyPayHingaAmount ("//*[@resource-id='com.tonik.mobile:id/Monthly PayHinga fee']/following-sibling::android.widget.TextView", "//*[@name='com.tonik.mobile:id/Monthly PayHinga fee']/following-sibling::XCUIElementTypeStaticText", "xpath", "xpath"),
	objLateFeeTxt ("//*[@resource-id='com.tonik.mobile:id/Late Fee'] | //*[@text='Late fee']", "//*[@name='com.tonik.mobile:id/Late Fee'] | //XCUIElementTypeStaticText[@label='Late fee']", "xpath", "xpath"),
	objLateFeeAmount ("//*[@resource-id='com.tonik.mobile:id/Late Fee']/following-sibling::android.widget.TextView | //*[@text='Late fee']/following-sibling::android.widget.TextView", "//*[@name='com.tonik.mobile:id/Late Fee']/following-sibling::XCUIElementTypeStaticText | //XCUIElementTypeStaticText[@label='Late fee']/following-sibling::XCUIElementTypeStaticText", "xpath", "xpath"),
	objDownloadBtn ("com.tonik.mobile:id/Download_btn_txt", "com.tonik.mobile:id/Download_btn_txt", "id", "id"),
	objTile ("//*[android.widget.ScrollView]/child::*/child::*/child::*/child::*/child::android.widget.TextView", "", "xpath", "xpath"),
	objTileSubHeader ("//*[android.widget.ScrollView]/child::*/child::*/child::*/child::*/(child::android.widget.TextView)[1]", "(//XCUIElementTypeScrollView)[2]/child::*/child::*/child::*/child::*/child::XCUIElementTypeStaticText[1]", "xpath", "xpath"),
	objMunaPageHowItWorks ("//*[@resource-id='com.tonik.mobile:id/How it works']", "//*[@name='com.tonik.mobile:id/How it works']", "xpath", "xpath"),
	objReadOurFAQBtn ("//*[@resource-id='com.tonik.mobile:id/Read our FAQs to learn more']", "//*[@name='com.tonik.mobile:id/Read our FAQs to learn more']", "xpath", "xpath"),
	objHelUsTxt ("Help us, help you!", "com.tonik.mobile:id/Help us, help you!", "id", "id"),
	objHelpUsTileSubHeader ("com.tonik.mobile:id/description4", "com.tonik.mobile:id/description4", "id", "id"),
	objWebView ("com.sec.android.app.sbrowser:id/url_bar_container", "com.sec.android.app.sbrowser:id/url_bar_container", "id", "id"),
	objInfoScreenDateBookedTxt ("//*[contains(@text,'Date booked')] | //*[contains(@text,'Borrowing date')]", "//XCUIElementTypeStaticText[contains(@value,'Date booked')] | //XCUIElementTypeStaticText[contains(@value,'Borrowing date')]", "xpath", "xpath"),
	objInfoScreenDateBooked("//*[contains(@text,'Date booked')]/parent::android.view.ViewGroup/following-sibling::android.widget.TextView | //*[contains(@text,'Borrowing date')]/parent::android.view.ViewGroup/following-sibling::android.widget.TextView", "//XCUIElementTypeStaticText[@name='com.tonik.mobile:id/Date booked']/parent::*/following-sibling::*", "xpath", "xpath"),
	objGotItButton ("//*[contains(@text,'Got it')]", "//*[contains(@value,'Got it')]", "xpath", "xpath"),
	objFullRepaymentTxt ("//*[contains(@text,'Full repayment amount')]", "//*[contains(@value,'Full repayment amount')]", "xpath", "xpath"),
	objRepaymentAmount ("(//*[contains(@text,'Full repayment amount')]/following-sibling::android.widget.TextView)[1]", "(//*[contains(@value,'Full repayment amount')]/following-sibling::XCUIElementTypeStaticText)[1]", "xpath", "xpath"),
	objSubjectToChangeTxt ("//*[contains(@text,'Subject to change once reserved')]", "//*[contains(@value,'Subject to change once reserved')]", "xpath", "xpath"),
	objBreakDownTxt ("//*[contains(@text,' Breakdown ')]", "//*[contains(@value,' Breakdown ')]", "xpath", "xpath"),
	objLoanProduct ("//*[contains(@text,'Loan product')]", "//*[contains(@value,'Loan product')]", "xpath", "xpath"),
	objQuickLoan ("//*[contains(@text,'Credit Builder')]", "//*[contains(@value,'Credit Builder')]", "xpath", "xpath"),
	objPrincipal ("//*[contains(@text,'Principal')]", "//*[contains(@value,'Principal')]", "xpath", "xpath"),
	objPrincipalAmount ("//*[@text='Principal']/following-sibling::android.widget.TextView", "//*[contains(@value,'Principal')]/following-sibling::XCUIElementTypeStaticText", "xpath", "xpath"),
	objNextRepaymentAmount ("//android.widget.TextView[@resource-id='com.tonik.mobile:id/AmountTitle']", "com.tonik.mobile:id/AmountTitle", "xpath", "id"),
	objPayHingaFeeTxt ("//*[contains(@text,'PayHinga fee')]", "//*[contains(@value,'PayHinga fee')]", "xpath", "xpath"),
	objPayHingaFeeAmount ("//*[contains(@text,'PayHinga fee')]/following-sibling::android.widget.TextView", "//*[contains(@value,'PayHinga fee')]/following-sibling::XCUIElementTypeStaticText", "xpath", "xpath"),
	objInterestTxt ("//*[contains(@text,'Interest')]", "//*[contains(@value,'Interest')]", "xpath", "xpath"),
	objInterestRate ("//*[contains(@text,'Interest')]/following-sibling::android.widget.TextView", "//*[contains(@value,'Interest')]/following-sibling::XCUIElementTypeStaticText", "xpath", "xpath"),
	objNotEnoughBalanceTxt ("//*[contains(@text,'Not enough balance')]", "//*[contains(@value,'Not enough balance')]", "xpath", "xpath"),
	objTheOnlyWayToProcess ("//*[contains(@text,'The only way to process your')]", "//*[contains(@value,'The only way to process your')]", "xpath", "xpath"),
	objTonikAccBalTxt ("//*[contains(@text,'Tonik account balance')]", "//*[contains(@value,'Tonik account balance')]", "xpath", "xpath"),
	objBalanceAmount ("//*[contains(@text,'Tonik account balance')]/following-sibling::android.widget.TextView", "//*[contains(@value,'Tonik account balance')]/following-sibling::XCUIElementTypeStaticText", "xpath", "xpath"),
	objTopUpTonicAccountButton ("//*[contains(@text,'Top up my Tonik account')]", "//*[contains(@value,'Top up my Tonik account')]", "xpath", "xpath"),
	objFullRepaymentAmount ("(//*[@text='Full repayment amount']/following-sibling::android.widget.TextView)[2]", "(//*[contains(@value,'Full repayment amount')]/following-sibling::XCUIElementTypeStaticText)[1]", "xpath", "xpath"),
	objCloseMyLoanButton ("//*[contains(@text,'Close my loan')]", "//*[contains(@value,'Close my loan')]", "xpath", "xpath"),
	objDropTheMicPage ("//*[contains(@text,'Drop the mic!')]", "//*[contains(@value,'Drop the mic!')]", "xpath", "xpath"),
	objYourLoanPaidFullyText ("//*[contains(@text,'Your loan is finally fully paid, luv.')]", "//*[contains(@value,'Your loan is finally fully paid, luv.')]", "xpath", "xpath"),
	objBackToDashBoardButton ("//*[contains(@text,'Back to Dashboard')]", "//*[contains(@value,'Back to Dashboard')]", "xpath", "xpath"),
	objYourLoanAlmostReadyPage ("com.tonik.mobile:id/Head_title_text", "com.tonik.mobile:id/Head_title_text", "id", "id"),
	objYourLoanAlmostReadyPageSubTitle ("com.tonik.mobile:id/Txt_sub_header", "com.tonik.mobile:id/Txt_sub_header", "id", "id"),
	objIWillDoItBtn ("//*[contains(@text,'ll do it later, pinky swear')]", "//*[contains(@value,'ll do it later, pinky swear')]", "xpath", "xpath"),
	objNooicePage ("//*[contains(@text,'Nooice!')]", "//*[contains(@value,'Nooice!')]", "xpath", "xpath"),
	objNooicePageSubTxt ("//*[contains(@text,'Thanks for verifying, luv.')]", "//*[contains(@value,'Thanks for verifying, luv.')]", "xpath", "xpath"),
	objCloseButton ("//*[contains(@text,'Close')]", "//*[contains(@value,'Close')]", "xpath", "xpath"),
	objEducationRadioBtn ("//*[@resource-id='com.tonik.mobile:id/Education']/following-sibling::android.view.ViewGroup", "", "xpath", "xpath"),
	objAccomodationAndFoodRadioBtn ("//*[contains(@text,'Accommodation and Food Service Activities')]/following-sibling::android.view.ViewGroup", "", "xpath", "xpath"),
	objAccountsRadioBtn ("//*[@resource-id='com.tonik.mobile:id/Accountants (including Auditors)']/following-sibling::android.view.ViewGroup", "", "xpath", "xpath"),
	objAgricultureBtn ("//*[@resource-id='com.tonik.mobile:id/Agriculture']/following-sibling::android.view.ViewGroup", "", "xpath", "xpath"),
	objFoodProcessingRadioBtn ("//*[@resource-id='com.tonik.mobile:id/Food Processing']/following-sibling::android.view.ViewGroup", "", "xpath", "xpath"),
	objSingleRadioBtn ("//*[@resource-id='com.tonik.mobile:id/Single']/following-sibling::android.view.ViewGroup", "", "xpath", "xpath"),
	objNoDependentsRadioBtn ("//*[@resource-id='com.tonik.mobile:id/No dependents']/following-sibling::android.view.ViewGroup", "", "xpath", "xpath"),
	objHighSchoolGraduateRadioBtn ("//*[@resource-id='com.tonik.mobile:id/High School Graduate']/following-sibling::android.view.ViewGroup", "", "xpath", "xpath"),
	objWhatDoYouNeedItForPage ("//*[@text='What do you need it for?']", "//*[@value='What do you need it for?']", "xpath", "xpath"),
	objMonthlyPaymentDueTxt ("//*[contains(@text,'Monthly payment due')]", "//*[contains(@value,'Monthly payment due')]", "xpath", "xpath"),
	objMonthlyPaymentDueAmount ("//*[contains(@text,'Monthly payment due')]/following-sibling::android.widget.TextView", "//XCUIElementTypeOther[@name='Monthly payment due']/following-sibling::*/child::*", "xpath", "xpath"),
	objList ("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView", "//XCUIElementTypeScrollView/child::*/child::*/child::*/child::XCUIElementTypeStaticText", "xpath", "xpath"),
	objLoansStatus ("(//*[@text='Loans']/following-sibling::android.widget.TextView)[1]", "//*[@value='Loans']/parent::*/following-sibling::*/child::*[1]", "xpath", "xpath"),
	objLoanTileInfo ("(//*[@text='Loans']/following-sibling::android.widget.TextView)[2]", "//*[@value='Loans']/parent::*/following-sibling::*/child::*[2]", "xpath", "xpath"),
	objLeaveIcon ("//*[@resource-id='com.tonik.mobile:id/Header_right_click']/child::*", "(//*[contains(@name,'right_click')]/child::*)[1]", "xpath", "xpath"),
	objLeaveIconSelectIndustryPage ("//*[@resource-id='com.tonik.mobile:id/Header_right_click']/child::*", "(//*[contains(@name,'right_click')])[2]", "xpath", "xpath"),
	objLeavingSoonPage ("//*[@text='Leaving so soon, luv?']", "", "xpath", "xpath"),
	objLeavingSoonReason ("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView", "//XCUIElementTypeScrollView/child::*/child::*/child::*/child::XCUIElementTypeStaticText", "xpath", "xpath"),
	objFieldOfWokSearchField ("//android.widget.EditText", "//XCUIElementTypeTextField", "xpath", "xpath"),
	objFieldOfWorkPage ("//*[@text='You work in...']", "//*[@value='You work in...']", "xpath", "xpath"),
	objIndustrySelectionScreen ("//*[@text='What industry do you work in?']", "//*[@value='What industry do you work in?']", "xpath", "xpath"),
	objMaritalStatusPage ("//*[@text='Are you?']", "//*[@value='Are you?']", "xpath", "xpath"),
	objSelectNoOfKids ("//*[@text='How many kids do you have?']", "", "xpath", "xpath"),
	objContactReferencePage ("//*[@text='Who can we contact for reference?']", "//*[@value='Who can we contact for reference?']", "xpath", "xpath"),
	objHighestEducationalAttainmentPage ("//*[@text='What is your highest educational attainment?']", "//*[@value='What is your highest educational attainment?']", "xpath", "xpath"),
	objFirstNameInputField ("//*[@resource-id='com.tonik.mobile:id/First_Name_text']", "//*[@name='com.tonik.mobile:id/First_Name_text']", "xpath", "xpath"),
	objLastNameInputField ("//*[@resource-id='com.tonik.mobile:id/Last_Name_text']", "//*[@name='com.tonik.mobile:id/Last_Name_text']", "xpath", "xpath"),
	objRelationShipDropdown ("//*[@resource-id='com.tonik.mobile:id/relationShipTxt_text']", "//*[@name='Relationship']/following-sibling::*", "xpath", "xpath"),
	objRelationShipPopup ("//*[@resource-id='com.tonik.mobile:id/Relationship']", "//XCUIElementTypeStaticText[@name='com.tonik.mobile:id/Relationship']", "xpath", "xpath"),
	objContactsIcon ("//*[@resource-id='com.tonik.mobile:id/Phone_book_icon_click']", "//*[@name='com.tonik.mobile:id/Phone_book_icon_click']", "xpath", "xpath"),
	objReferenceSearchField ("//*[@resource-id='com.tonik.mobile:id/Search_placeholder']", "//*[@name='com.tonik.mobile:id/Search_placeholder']", "xpath", "xpath"),
	objInvalidMobileNumberMsg ("//*[@text='Please enter a valid mobile number']", "//*[@value='Please enter a valid mobile number']", "xpath", "xpath"),
	objHighestEducationalAttainment ("//*[@text='What is your highest educational attainment?']", "//*[@value='What is your highest educational attainment?']", "xpath", "xpath"),
	objContactsPopup ("//*[@text='Allow TONIK to access your contacts?']", "//*[@value='Allow TONIK to access your contacts?']", "xpath", "xpath"),
	objIsThisYourCurrentAddPage ("//*[@text='Is this your current address?']", "//*[@value='Is this your current address?']", "xpath", "xpath"),
	objHaveAnotherReference ("//*[@text='Have another reference?']", "//*[@value='Have another reference?']", "xpath", "xpath"),
	objPopupHeader ("com.tonik.mobile:id/Popup_header_txt", "com.tonik.mobile:id/Popup_header_txt", "id", "id"),
	objPopupSubtext ("com.tonik.mobile:id/Popup_sub_txt", "com.tonik.mobile:id/Popup_sub_txt", "id", "id"),
	objPopupButton ("//*[@text='OK']", "//*[@value='OK']", "xpath", "xpath"),
	objRegisteredMobileNumberErrorMsg ("//*[@text=\"You can't enter registered mobile number\"]", "//*[@value=\"You can't enter registered mobile number\"]", "xpath", "xpath"),
	objSelectReferenceScreen ("//*[@text='Select a reference']", "//*[@value='Select a reference']", "xpath", "xpath"),
	objAddress ("(//*[@resource-id='com.tonik.mobile:id/Header_left_click']/parent::*/following-sibling::android.view.ViewGroup)[1]/child::*", "(//XCUIElementTypeOther[@name='Is this your current address? Confirm if the address below is where you’re residing at the moment.'])[1]/following-sibling::*/child::*/child::*/child::XCUIElementTypeStaticText", "xpath", "xpath"),
	objChangeAddressBtn ("//android.widget.TextView[@resource-id='com.tonik.mobile:id/Change_Address_click']", "//XCUIElementTypeStaticText[@name='com.tonik.mobile:id/Change_Address_click']", "xpath", "xpath"),
	objYesThisMyAddressBtn ("//android.widget.TextView[@resource-id='com.tonik.mobile:id/Submit_click']", "//XCUIElementTypeStaticText[@name='com.tonik.mobile:id/Submit_click']", "xpath", "xpath"),
	objMonthlyIncomeScreen ("//*[contains(@text,'your monthly income')]", "//*[contains(@value,'your monthly income')]", "xpath", "xpath"),
	objZipCodeInputField ("//*[@text='Zipcode']/parent::*/child::android.widget.EditText", "//*[@value='Zipcode']/following-sibling::*/child::XCUIElementTypeTextField", "xpath", "xpath"),
	objApplyZipCode ("//*[@text='Apply Zipcode']", "//*[@value='Apply Zipcode']", "xpath", "xpath"),
	objMonthlyIncomeField ("com.tonik.mobile:id/Monthly_inc_text", "com.tonik.mobile:id/Monthly_inc_text", "id", "id"),
	objHouseUnitFlrNumberInputField ("//*[@text='House/Unit/Flr #, Bldg, Blk or Lot #, Street']/parent::*/child::android.widget.EditText", "//XCUIElementTypeStaticText[@name='undefined_Float' and @label='House/Unit/Flr #, Bldg, Blk or Lot #, Street']/preceding-sibling::*/child::XCUIElementTypeTextField", "xpath", "xpath"),
	objInvalidIncomeMsg ("com.tonik.mobile:id/Monthly_inc_error", "com.tonik.mobile:id/Monthly_inc_error", "id", "id"),
	objCompanyInputField ("com.tonik.mobile:id/Company_name_text", "com.tonik.mobile:id/Company_name_text", "id", "id"),
	objUpdateButton ("com.tonik.mobile:id/Button_text", "com.tonik.mobile:id/Button_text", "id", "id"),
	objWhatsYourJobRolePage ("//*[contains(@text,'your job title/role?')]", "//*[contains(@value,'your job title/role?')]", "xpath", "xpath"),
	objTINInputField ("com.tonik.mobile:id/Tin_num_text", "com.tonik.mobile:id/Tin_num_text", "id", "id"),
	objInvalidTINMsg ("com.tonik.mobile:id/Tin_error", "com.tonik.mobile:id/Tin_error", "id", "id"),
	objInputCompanyScreen ("//*[@text=\"Where do you work, luv?\"]", "//*[@value=\"Where do you work, luv?\"]", "xpath", "xpath"),
	objAnotherWayToReachYou ("//*[@text='Another way to reach you?']", "//*[@value='Another way to reach you?']", "xpath", "xpath"),
	objSecondaryContactInputField ("com.tonik.mobile:id/Mobile_num_enter_text", "com.tonik.mobile:id/Mobile_num_enter_text", "id", "id"),
	objTermsAndConditionScreen ("//*[@text='Terms and Conditions']", "//*[@value='Terms and Conditions']", "xpath", "xpath"),
	objSkipBtn ("com.tonik.mobile:id/Button_skip_text", "com.tonik.mobile:id/Button_skip_text", "id", "id"),
	objTermsAndConditionSubtitle ("//*[@resource-id='Terms_subtitle_txt']", "//*[@name='Terms_subtitle_txt']", "xpath", "xpath"),
	objDownloadTermsAndConditionBtn ("//*[@resource-id='com.tonik.mobile:id/Header_right_click']/child::*", "//*[@name='com.tonik.mobile:id/Header_right_click']/child::*", "xpath", "xpath"),
	objCopyButtonIOS ("", "//XCUIElementTypeStaticText[@name='Copy']", "xpath", "xpath"),
	objTermsAndConditionCheckBox ("//*[@resource-id='Terms_check_click']", "//*[@name='Terms_check_click']", "xpath", "xpath"),
	objIAcceptBtn ("//*[@text='I accept and give my consent']/parent::*", "//*[@value='I accept and give my consent']/parent::*", "xpath", "xpath"),
	objLoansDocuments ("//*[@resource-id='com.tonik.mobile:id/Rates and fees_click']/parent::*/following-sibling::android.view.ViewGroup/child::android.view.ViewGroup/child::android.view.ViewGroup/child::android.view.ViewGroup/child::android.widget.TextView",
			"(//XCUIElementTypeOther[@name=\"Rates and fees\"])[1]/following-sibling::XCUIElementTypeOther/child::XCUIElementTypeOther/child::XCUIElementTypeOther/child::XCUIElementTypeOther/child::XCUIElementTypeStaticText", "xpath", "xpath"),
	objAboutPayhingaScreen ("//*[contains(@text,'Relax')]", "//*[contains(@value,'Relax')]", "xpath", "xpath"),
	objGoodByeScreen ("//*[@text=\"This isn't goodbye, only see you later\"]", "//XCUIElementTypeStaticText[contains(@label,\"This isn't goodbye\")]", "xpath", "xpath"),
	objGoodByeInfo ("//*[contains(@text,'Feel free to apply for another loan')]", "//*[contains(@value,'Feel free to apply for another loan')]", "xpath", "xpath"),
	objBackToDashboard ("//*[@text='Back to Dashboard']", "//*[@value='Back to Dashboard']", "xpath", "xpath"),
	objLoansStatusAfterLeaveApplication ("//*[@text='Fast cash or easy payments?']", "//*[@value='Fast cash or easy payments?']", "xpath", "xpath"),
	objLoanTileInfoAfterLeaveApplication ("//*[@text='Find the right loan that fits your needs and wants. One day approval.']", "//*[@value='Find the right loan that fits your needs and wants. One day approval.']", "xpath", "xpath"),
	objTransactionDetailsInfo ("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/(android.widget.TextView)[1]", "//XCUIElementTypeScrollView/child::*/child::*/child::*/child::*/child::*[1]/child::XCUIElementTypeStaticText", "xpath", "xpath"),
	objPopupSubHeader ("com.tonik.mobile:id/Popup_sub_header_txt", "com.tonik.mobile:id/Popup_sub_header_txt", "id", "id"),
	objYesVerifyMyID ("//*[@text='Yes, verify my ID']", "//*[@value='Yes, verify my ID']", "xpath", "xpath"),
	objSubHeader ("com.tonik.mobile:id/Txt_sub_header", "com.tonik.mobile:id/Txt_sub_header", "id", "id"),
	objSoftRejectLoanStatus ("//*[@text='We cannot extend a loan to you at this time.']", "//*[@value='We cannot extend a loan to you at this time.']", "xpath", "xpath"),
	objSoftRejectLoanInfo ("//*[@text='Find the right loan that fits your needs and wants. One day approval.']", "//*[@value='Find the right loan that fits your needs and wants. One day approval.']", "xpath", "xpath"),
	objIGotTheseButton ("//*[@resource-id='com.tonik.mobile:id/I got these!']/child::*", "", "xpath", "xpath"),
	ObjLetsGoBtn ("//*[@resource-id=\"com.tonik.mobile:id/Let's go!\"]/child::*", "//*[@name=\"com.tonik.mobile:id/Let's go!\"]/child::*", "xpath", "xpath"),
	objHardToSayScreen ("//*[@text=\"It's hard to say no to you\"]", "//*[@value=\"It's hard to say no to you\"]", "xpath", "xpath"),
	objPayNowBtn ("//*[@text='Pay now']", "//*[@value='Pay now']", "xpath", "xpath"),
	objLoanPaymentScreen ("//*[@text='Loan Payment']", "//*[@value='Loan Payment']", "xpath", "xpath"),
	objAmountDueField ("//*[@text='Amount due']", "//*[@value='Amount due']", "xpath", "xpath"),
	objAmountDue ("//*[@text='Amount due']/following-sibling::*", "//*[@value='Amount due']/following-sibling::XCUIElementTypeStaticText", "xpath", "xpath"),
	objLateFeesField ("//*[@text='Late fees']", "//*[@value='Late fees']", "xpath", "xpath"),
	objLateFee ("//*[@text='Late fees']/following-sibling::*", "//*[@value='Late fees']/following-sibling::XCUIElementTypeStaticText", "xpath", "xpath"),
	objAmountToBePaidField ("//*[@text='Amount to be paid']", "//*[@value='Amount to be paid']", "xpath", "xpath"),
	objAmountToBePaid ("//*[@text='Amount to be paid']/following-sibling::*", "//*[@value='Amount to be paid']/following-sibling::XCUIElementTypeStaticText", "xpath", "xpath"),
	objPaymentOptionsHeader ("//*[@text='Payment options']", "//*[@value='Payment options']", "xpath", "xpath"),
	objTonikAccountOptions ("//*[@text='Tonik account']", "//*[@value='Tonik account']", "xpath", "xpath"),
	objBalanceDisplayedInLoanPaymentScreen ("//*[contains(@text,'Balance:')]", "//*[contains(@value,'Balance:')]", "xpath", "xpath"),
	objTonikAccountCheckBox ("//*[contains(@text,'Balance:')]/following-sibling::*", "//*[contains(@value,'Balance:')]/parent::*/following-sibling::*", "xpath", "xpath"),
	objOtherPaymentOptions ("//*[@text='Other']/following-sibling::android.widget.ScrollView/*/*/*/(child::android.widget.TextView)[1]", "//*[contains(@value,'Other')]/parent::*/following-sibling::*/child::XCUIElementTypeScrollView/child::*/child::*/child::*/child::*/child::*/child::XCUIElementTypeStaticText[1]", "xpath", "xpath"),
	objSelectPaymentOption ("//*[@text='Select a payment option']", "//*[@value='Select a payment option']", "xpath", "xpath"),
	objConfirmPaymentBtn ("//*[@text='Confirm payment']", "//*[@value='Confirm payment']", "xpath", "xpath"),
	objOtherHeader ("//*[@text='Other']", "//*[@value='Other']", "xpath", "xpath"),
	objItsNotYouItsUs ("//*[@text=\"It's not you, it's us.\"]", "//*[@value=\"It's not you, it's us.\"]", "xpath", "xpath"),
	objUploadNewId ("//*[@text='Upload new ID']", "//*[@value='Upload new ID']", "xpath", "xpath"),
	objStepsToVerifyScreen ("//*[@text='Steps to verify your account']", "//*[@value='Steps to verify your account']", "xpath", "xpath"),
	objSteps1ToVerify ("//*[@text='Take a Face Identity Scan (Keep your clothes on, please.)']", "//*[@value='Take a Face Identity Scan (Keep your clothes on, please.)']", "xpath", "xpath"),
	objSteps2ToVerify ("//*[@text='Scan one Valid ID you own.']", "//*[@value='Scan one Valid ID you own.']", "xpath", "xpath"),
	objContinueButton ("//*[contains(@text,'Continue')]", "//*[contains(@value,'Continue')]", "xpath", "xpath"),
	objLoanPaidInFull ("//*[@text='Loan paid in Full ']", "(//*[@value='Loan paid in Full '])[1]", "xpath", "xpath"),
	objEasypaymentsTitle ("//*[@resource-id='com.tonik.mobile:id/Main_title']", "//*[@name='com.tonik.mobile:id/Main_title']", "xpath", "xpath"),
	objEayPaymentsSubTitle ("//*[@resource-id='com.tonik.mobile:id/Main_sub_title']", "//*[@name='com.tonik.mobile:id/Main_sub_title']", "xpath", "xpath"),
	objKeyboardDoneBtn ("//*[@text='Done']", "//XCUIElementTypeButton[@name='Done']", "xpath", "xpath"),
	objHoldOnPopupTitle ("(//*[contains(@text,'Hold on')])[1]", "(//*[contains(@value,'Hold on')])[1]", "xpath", "xpath"),
	objHoldOnPopupSubTitle ("(//*[contains(@text,'Thanks for completing those steps')])[1]", "(//*[contains(@value,'Thanks for completing those steps')])[1]", "xpath", "xpath"),
	objPayHingaSubtitle ("com.tonik.mobile:id/Payhinga_SubTitle0", "com.tonik.mobile:id/Payhinga_SubTitle0", "id", "id"),
	objActivateBtn ("//*[@text='Activate']", "//*[@value='Activate']", "xpath", "xpath"),
	objWantToActivatePayHingaScreen ("//*[@text='Are you sure you want to activate Payhinga?']", "//*[@value='Are you sure you want to activate Payhinga?']", "xpath", "xpath"),
	objReasonForPayHinga ("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView", "//XCUIElementTypeScrollView/child::*/child::*/child::*/child::XCUIElementTypeStaticText", "xpath", "xpath"),
	objMayBeLaterBtn ("//*[@text='Maybe later']", "//*[@value='Maybe later']", "xpath", "xpath"),
	objYesImSureBtn ("//*[@text=\"Yes, I'm sure\"]", "//*[@value=\"Yes, I'm sure\"]", "xpath", "xpath"),
	objPayHingaConfirmationMsg ("//*[contains(@text,'By activating 1 month of PayHinga')]", "//*[contains(@value,'By activating 1 month of PayHinga')]", "xpath", "xpath"),
	objActivateNow ("//*[@text='Activate now']", "//*[@value='Activate now']", "xpath", "xpath"),
	objApprovedPayHingaMsg ("//*[contains(@text,'Congrats')]", "//*[contains(@value,'Congrats')]", "xpath", "xpath"),
	objLoanPaymentReceivedScreen ("//*[@text='Loan payment received']", "//*[@value='Loan payment received']", "xpath", "xpath"),
	objLoanPaymentReceivedInfo ("//*[contains(@text,'Tonik account was reserved as your loan payment.')]", "//*[contains(@value,'Tonik account was reserved as your loan payment.')]", "xpath", "xpath"),
	objImportantNote ("//*[contains(@text,'Important note')]", "//*[contains(@value,'Important note')]", "xpath", "xpath"),
	objImportantNoteInfo ("//*[contains(@text,'This transaction will be posted as your loan payment on your due date.')]", "//*[contains(@value,'This transaction will be posted as your loan payment on your due date.')]", "xpath", "xpath"),
	objViewTransactionLink ("//*[@text='View transaction details']", "//*[@value='View transaction details']", "xpath", "xpath"),
	objYourLoanAccount ("//*[@text='Your Loan Account']", "//*[@value='Your Loan Account']", "xpath", "xpath"),
	objLoanTileAfter1stRepayment ("//*[@text='Done for the month']", "//*[@value='Done for the month']", "xpath", "xpath"),
	objNextPaymentDate ("//*[contains(@text,'Next payment')]", "//*[contains(@value,'Next payment')]", "xpath", "xpath"),
	objLoanTileMsg ("//*[@text='Woohoo! Time to chill']", "//*[@value='Woohoo! Time to chill']", "xpath", "xpath"),
	objLoanBlueTick ("//*[@text='Your Loan Account']/following-sibling::android.view.ViewGroup", "//*[@value='Your Loan Account']", "xpath", "xpath"),
	objLoanPaymentTransaction ("//*[@text='Loan Payment']", "//*[@value='Loan Payment']", "xpath", "xpath"),
	objNextInstallment ("//*[contains(@text,'Next installment')]", "//*[contains(@value,'Next installment')]", "xpath", "xpath"),
	objNextDueDate ("//*[contains(@text,'Due on')]", "//*[contains(@value,'Due on')]", "xpath", "xpath"),
	objSubMessage ("com.tonik.mobile:id/SubMessage", "com.tonik.mobile:id/SubMessage", "id", "id"),
	objPaymentDone ("com.tonik.mobile:id/PaymentDone", "com.tonik.mobile:id/PaymentDone", "id", "id"),
	objPayHingaTitle ("com.tonik.mobile:id/Payhinga_Title0", "com.tonik.mobile:id/Payhinga_Title0", "id", "id"),
	objLearnMore ("//*[@text='Learn More']", "//*[@value='Learn More']", "xpath", "xpath"),
	objQuickLoanPayHinga ("//*[contains(@text,'Quick loan')]", "//*[contains(@value,'Quick loan')]", "xpath", "xpath"),
	objSignDirectDebitForm ("//android.widget.TextView[@resource-id='com.tonik.mobile:id/Button_txt']", "//*[@name='com.tonik.mobile:id/Button_txt']", "xpath", "xpath"),
	objSignDirectDebitForm1 ("//android.widget.TextView[@resource-id='com.tonik.mobile:id/Custom_button_txt']", "//*[@name='com.tonik.mobile:id/Custom_button_txt']", "xpath", "xpath"),
	objPageTitle("//*[@resource-id='com.tonik.mobile:id/Main_title_txt']","//XCUIElementTypeStaticText[@name='com.tonik.mobile:id/Main_title_txt']","xpath","xpath"),
	objAccountHolderNameField("//*[@resource-id='com.tonik.mobile:id/Bank detailsText_Input_1']","//*[@name='com.tonik.mobile:id/Bank detailsText_Input_1']","xpath","xpath"),
	objBankNameField("//*[@resource-id='com.tonik.mobile:id/Bank detailsText_Input_2']","//XCUIElementTypeStaticText[@name=\"Bank name\"]","xpath","xpath"),
	objAccountNumberField("//*[@resource-id='com.tonik.mobile:id/Bank detailsText_Input_3']","//*[@name='com.tonik.mobile:id/Bank detailsText_Input_3']","xpath","xpath"),
	objCardToChangedTxtInLoanInfoScreen ("((//*[contains(@resource-id,'com.tonik.mobile:id/****')])/parent::android.view.ViewGroup/child::android.widget.TextView)[1]", "", "xpath", "xpath"),
	objCardNumberInLoanInfoScreen ("((//*[contains(@resource-id,'com.tonik.mobile:id/****')])/parent::android.view.ViewGroup/child::android.widget.TextView)[2]", "//XCUIElementTypeStaticText[@name=\"Card to be charged on a monthly basis:\"]/parent::*/following-sibling::*/child::*[1]", "xpath", "xpath"),
	objPageSubTitle("//*[@resource-id='com.tonik.mobile:id/Sub_title_txt']","//XCUIElementTypeStaticText[@name='com.tonik.mobile:id/Sub_title_txt']","xpath","xpath"),
	objCloseIcon("","//XCUIElementTypeButton[@name=\"Close\"]","","xpath"),
	objPopupTitle("com.tonik.mobile:id/Popup_title_text","id"),
	objPopupDescription("com.tonik.mobile:id/Popup_Description","id"),
	objWantToPayInFull("//*[@resource-id='Yes, I want to pay in full']","//XCUIElementTypeStaticText[@name='Yes, I want to pay in full']","xpath","xpath"),
	objProfileName ("//*[@resource-id='appbar-content-title-text']", "//*[@name='appbar-content-title-text']", "xpath", "xpath");
	private String android;
	private String ios;
	private String andPathType;
	private String iosPathType;
	QuickLoanWithVasPage(String android, String ios, String andPathType, String iosPathType) {
		this.android = android;
		this.ios = ios;
		this.andPathType = andPathType;
		this.iosPathType = iosPathType;
	}
	QuickLoanWithVasPage(String android, String andPathType, String iosPathType) {
		this.android = android;
		this.ios = android;
		this.andPathType = andPathType;
		this.iosPathType = iosPathType;
	}
	QuickLoanWithVasPage(String android, String andPathType) {
		this.android = android;
		this.ios = android;
		this.andPathType = andPathType;
		this.iosPathType = andPathType;
	}
	public static By getByOSType(String osType, QuickLoanWithVasPage objectName) {
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
		throw new IllegalArgumentException("Object not found: " + objectName);
	}
	public static By objTransactionDetailsInfo(String platform, String info) {
		if(platform.equalsIgnoreCase("android")){
			return By.xpath("//*[@text='"+info+"']/following-sibling::android.widget.TextView");
		} else {
			// add iOS parameter
			return By.xpath("");
		}
	}
	public static By objTileSubHeader(String platform, int tileSubHeader) {
		if (platform.equalsIgnoreCase("android")) {
			return By.xpath("(//*[android.widget.ScrollView]/child::*/child::*/child::*/child::*/(child::android.widget.TextView)[1])[" + tileSubHeader + "]");
		} else {
			return By.xpath("((//XCUIElementTypeScrollView)[2]/child::*/child::*/child::*/child::*/child::XCUIElementTypeStaticText[1])["+tileSubHeader+"]");
		}
	}
	public static By objTileInfo(String platform, int tileInfo) {
		if(platform.equalsIgnoreCase("android")){
			return By.xpath("(//*[android.widget.ScrollView]/child::*/child::*/child::*/child::*/(child::android.widget.TextView)[2])["+tileInfo+"]");
		} else {
			return By.xpath("((//XCUIElementTypeScrollView)[2]/child::*/child::*/child::*/child::*/child::XCUIElementTypeStaticText[2])["+tileInfo+"]");
		}
	}
	public static By objReasonForLoan(String platform, String sReason) {
		if(platform.equalsIgnoreCase("android")){
			return By.xpath("//*[@resource-id='com.tonik.mobile:id/"+sReason+"']");
		} else {
			return By.xpath("//*[@name='com.tonik.mobile:id/"+sReason+"']");
		}
	}
	public static By objList(String platform, int matchingElement) {
		if(platform.equalsIgnoreCase("android")){
			return By.xpath("(//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView)["+matchingElement+"]");
		} else {
			return By.xpath("//XCUIElementTypeScrollView/child::*[1]/child::*["+matchingElement+"]/child::*/child::*[2]/child::*");
		}
	}
	public static By objListReferenceNumber(String platform, int matchingElement) {
		if(platform.equalsIgnoreCase("android")){
			return By.xpath("(//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView)["+matchingElement+"]");
		} else {
			return By.xpath("(//XCUIElementTypeScrollView/child::*/child::*/child::*/child::*)["+matchingElement+"]");
		}
	}
	public static By objListFromSelectIndustry(String platform, int matchingElement) {
		if(platform.equalsIgnoreCase("android")){
			return By.xpath("(//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView)["+matchingElement+"]");
		} else {
			return By.xpath("//XCUIElementTypeScrollView/child::*[1]/child::*["+matchingElement+"]/child::*/child::XCUIElementTypeStaticText");
		}
	}
	public static By objLeavingSoonReason(String platform, String reason) {
		if(platform.equalsIgnoreCase("android")){
			return By.xpath("//*[contains(@text,'"+reason+"')]");
		} else {
			// add iOS parameter
			return By.xpath("//*[contains(@value,'"+reason+"')]");
		}
	}
	public static By objLeavingSoonReason(String platform, int reason) {
		if (platform.equalsIgnoreCase("android")) {
			return By.xpath("(//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView)[" + reason + "]");
		} else {
			return By.xpath("(//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText)["+reason+"]");
		}
	}
	public static By objSelectIndustry(String platform, String industry) {
		if(platform.equalsIgnoreCase("android")){
			return By.xpath("//*[@resource-id='com.tonik.mobile:id/"+industry+"']");
		} else {
			return By.xpath("(//XCUIElementTypeStaticText[@name='com.tonik.mobile:id/"+industry+"'])[1]");
		}
	}
	public static By objSelectSubIndustryRadioButton(String platform, String subIndustry) {
		if(platform.equalsIgnoreCase("android")){
			return By.xpath("//*[@resource-id='com.tonik.mobile:id/"+subIndustry+"']");
		} else {
			return By.xpath("(//*[contains(@name,'com.tonik.mobile:id/"+subIndustry+"')]/following-sibling::*/child::*/child::*/child::*)[2]");
		}
	}
	public static By objSelectMaritalStatus(String platform, String status) {
		if(platform.equalsIgnoreCase("android")){
			return By.xpath("//*[@resource-id='com.tonik.mobile:id/"+status+"']");
		} else {
			return By.xpath("//XCUIElementTypeStaticText[@name='com.tonik.mobile:id/"+status+"']");
		}
	}
	public static By objSelectRelationShip(String platform, String relationShip) {
		if(platform.equalsIgnoreCase("android")){
			return By.xpath("//*[@resource-id='com.tonik.mobile:id/"+relationShip+"']");
		} else {
			return By.xpath("//*[@name='com.tonik.mobile:id/"+relationShip+"']");
		}
	}
	public static By objAddress(String platform, int address) {
		if(platform.equalsIgnoreCase("android")){
			return By.xpath("((//*[@resource-id='com.tonik.mobile:id/Header_left_click']/parent::*/following-sibling::android.view.ViewGroup)[1]/child::*)["+address+"]");
		} else {
			return By.xpath("");
		}
	}
	public static By objLoansDocuments(String platform, int document) {
		if(platform.equalsIgnoreCase("android")){
			return By.xpath("(//*[@resource-id='com.tonik.mobile:id/Rates and fees_click']/parent::*/following-sibling::android.view.ViewGroup/child::android.view.ViewGroup/child::android.view.ViewGroup/child::android.view.ViewGroup/child::android.widget.TextView)["+document+"]");
		} else {
			return By.xpath("((//XCUIElementTypeOther[@name='Rates and fees'])[1]/following-sibling::XCUIElementTypeOther/child::XCUIElementTypeOther/child::XCUIElementTypeOther/child::XCUIElementTypeOther/child::XCUIElementTypeStaticText)["+document+"]");
		}
	}
	public static By objTransactionAmount(String platform, String transaction) {
		if(platform.equalsIgnoreCase("android")){
			return By.xpath("//*[contains(@text,'"+transaction+"')]/following-sibling::android.widget.TextView[contains(@text,'₱')]");
		} else {
			return By.xpath("(//*[contains(@value,'"+transaction+"')]/parent::*/following-sibling::XCUIElementTypeStaticText[contains(@value,'₱')])[1]");
		}
	}
	public static By objTransaction(String platform, String transaction) {
		if(platform.equalsIgnoreCase("android")){
			return By.xpath("//*[contains(@text,'"+transaction+"')]");
		} else {
			return By.xpath("(//*[contains(@value,'"+transaction+"')])[1]");
		}
	}
	public static By objTransactionDetailsInfo(String platform, int info) {
		if(platform.equalsIgnoreCase("android")){
			return By.xpath("(//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/(android.widget.TextView)[1])["+info+"]");
		} else {
			return By.xpath("(//XCUIElementTypeScrollView/child::*/child::*/child::*/child::*/child::*[1]/child::XCUIElementTypeStaticText)["+info+"]");
		}
	}
	public static By objTransactionInfo(String platform, String info) {
		if(platform.equalsIgnoreCase("android")){
			return By.xpath("//*[@text='"+info+"']/following-sibling::*");
		} else {
			return By.xpath("//*[@value='"+info+"']/parent::*/following-sibling::*/child::XCUIElementTypeStaticText");
		}
	}
	public static By objOtherPaymentOptions(String platform, int paymentOptions) {
		if(platform.equalsIgnoreCase("android")){
			return By.xpath("(//*[@text='Other']/following-sibling::android.widget.ScrollView/*/*/*/(child::android.widget.TextView)[1])["+paymentOptions+"]");
		} else {
			return By.xpath("(//*[contains(@value,'Other')]/parent::*/following-sibling::*/child::XCUIElementTypeScrollView/child::*/child::*/child::*/child::*/child::*/child::XCUIElementTypeStaticText[1])["+paymentOptions+"]");
		}
	}
	public static By objOtherPaymentOptions(String platform, String info) {
		if(platform.equalsIgnoreCase("android")){
			return By.xpath("(//*[@text='Other']/following-sibling::android.widget.ScrollView/*/*/*/(child::android.widget.TextView)[1])["+info+"]");
		} else {
			// add iOS parameter
			return By.xpath("");
		}
	}
	public static By objOtherPaymentOptionsInfo(String platform, int info) {
		if(platform.equalsIgnoreCase("android")){
			return By.xpath("(//*[@text='Other']/following-sibling::android.widget.ScrollView/*/*/*/(child::android.widget.TextView)[2])["+info+"]");
		} else {
			return By.xpath("(//*[contains(@value,'Other')]/parent::*/following-sibling::*/child::XCUIElementTypeScrollView/child::*/child::*/child::*/child::*/child::*/child::XCUIElementTypeStaticText[2])["+info+"]");
		}
	}
	public static By objReasonForPayHinga(String platform,int reason) {
		if(platform.equalsIgnoreCase("android")){
			return By.xpath("(//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView)["+reason+"]");
		} else {
			return By.xpath("(//XCUIElementTypeScrollView/child::*/child::*/child::*/child::XCUIElementTypeStaticText)["+reason+"]");
		}
	}
}