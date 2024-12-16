package com.tonik.pageObject;

import org.openqa.selenium.By;

public enum FlexLoanPage {

	objCreditBuilderTile ("//*[@resource-id='com.tonik.mobile:id/Credit Builder']", "//*[@name='com.tonik.mobile:id/Credit Builder']", "xpath", "xpath"),
	objPrepYourRequirementsScreenTitle ("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
	objPrepYourRequirementsScreenSubTitle ("com.tonik.mobile:id/Sub_title_txt", "com.tonik.mobile:id/Sub_title_txt", "id", "id"),
	objSalaryDebitCardRadioButton ("//*[@text='Salary Debit card']", "//*[@value='Salary Debit card']", "xpath", "xpath"),
	objProofOfIncomeRadioButton ("//*[contains(@text,'Proof of income')]", "//*[contains(@value,'Proof of income')]", "xpath", "xpath"),
	objBankStatementsFromPreviousMonthRadioButton ("//*[contains(@text,'Bank Statement from previous month')]", "//*[contains(@value,'Bank Statement from previous month')]", "xpath", "xpath"),
	objIDontHaveAnyOfTheseDocumentsLink ("//*[contains(@text,\"I don't have any of these documents\")]", "//*[contains(@value,\"I don't have any of these documents\")]", "xpath", "xpath"),
	objImReadyButton ("//*[@resource-id=\"com.tonik.mobile:id/I'm ready!\"]", "//*[@name=\"com.tonik.mobile:id/I'm ready!\"]", "xpath", "xpath"),
	objImReadyButtonDisabled ("//*[@resource-id=\"com.tonik.mobile:id/I'm ready!\"]/parent::*[@enabled='false']", "//*[@name=\"com.tonik.mobile:id/I'm ready!\"]/parent::*[@enabled='false']", "xpath", "xpath"),
	objImReadyButtonEnabled ("//*[@resource-id=\"com.tonik.mobile:id/I'm ready!\"]/parent::*[@enabled='true']", "//*[@name=\"com.tonik.mobile:id/I'm ready!\"]/parent::*[@enabled='true']", "xpath", "xpath"),
	objPrepYourRequirementsScreenBckBtn ("//*[@resource-id='com.tonik.mobile:id/Header_left_click']", "//*[@name='com.tonik.mobile:id/Header_left_click']", "xpath", "xpath"),
	objHowMuchYouNeedScreenTitle ("com.tonik.mobile:id/Button_text", "com.tonik.mobile:id/Button_text", "id", "id"),
	objHowMuchYouNeedScreenAmountFieldDefaultValue ("//*[@text='5,000']", "//*[@value='5,000']", "xpath", "xpath"),
	objTryALowerAmountOfUpTo20000Msg ("//*[@text='Try a lower amount of up to ₱20,000']", "//*[@value='Try a lower amount of up to ₱20,000']", "xpath", "xpath"),
	objLetsGetPersonalPageHeader ("//*[contains(@text,'personal, babe')]", "//*[contains(@value,'personal, babe')]", "xpath", "xpath"),
	objWeWouldLikeSubHeader ("//*[contains(@text,'like to get to know you')]", "//*[contains(@value,'like to get to know you')]", "xpath", "xpath"),
	objProceedButton ("com.tonik.mobile:id/Custom_button_click", "com.tonik.mobile:id/Custom_button_click", "id", "id"),
	objTimeToFlexPageHeader ("//*[contains(@text,'Time to flex that')]", "//*[contains(@value,'Time to flex that')]", "xpath", "xpath"),
	objTimeToFlexPageSubHeader ("//*[contains(@text,'Make sure your card details are')]", "//*[contains(@value,'Make sure your card details are')]", "xpath", "xpath"),
	objLinkMyATMButton ("//*[@resource-id='com.tonik.mobile:id/Custom_button_txt']", "//XCUIElementTypeStaticText[@name='com.tonik.mobile:id/Custom_button_txt' and @label='Link my ATM']", "xpath", "xpath"),
	onjNewCardButton ("//*[contains(@text,'New Card')]", "//*[contains(@value,'New Card')] | //*[contains(@value,'new card')]", "xpath", "xpath"),
	objCardDetailsPage ("(//*[contains(@text,'Card Details')])[1]", "(//*[contains(@value,'Card Details')])[1]", "xpath", "xpath"),
	onjCardDetailsTxt ("//*[contains(@text,'Enter Card Details')]", "//*[contains(@value,'Enter Card Details')]", "xpath", "xpath"),
	objCardNumber ("//*[@resource-id='ccform_cardnumber_lbl']", "//XCUIElementTypeStaticText[@name=\"Card Number\"]", "xpath", "xpath"),
	objCardNumberType ("//*[@resource-id='ccform_cardnumber_TB']", "//XCUIElementTypeTextField[@value=\"Card Number\"]", "xpath", "xpath"),
	objExpiryDate ("//*[contains(@text,'Expiry Date')]", "//XCUIElementTypeStaticText[@name=\"Expiry Date\"]", "xpath", "xpath"),
	objExpiryDateType ("//*[@resource-id='ccform_expdate']", "//XCUIElementTypeTextField[@value=\"MM/YY\"]", "xpath", "xpath"),
	objCVV ("(//*[contains(@text,'CVV')])[1]", "//XCUIElementTypeStaticText[@name=\"CVV\"]", "xpath", "xpath"),
	objCVVType ("//*[@resource-id='ccform_cvv_TB']", "//XCUIElementTypeSecureTextField[@value=\"CVV\"]", "xpath", "xpath"),
	objNameOnCard ("//*[contains(@text,'Name on Card')]", "//*[contains(@value,'Name on Card')]", "xpath", "xpath"),
	objNameOnCardType ("//*[@resource-id='ccform_cardholdername_TB']", "//XCUIElementTypeTextField[@value=\"Name on Card\"]", "xpath", "xpath"),
	objAddCardButton ("//*[@text='Add Card']", "//XCUIElementTypeButton[@name=\"Add Card\"]", "xpath", "xpath"),
	objLetsPretendPopUp ("com.tonik.mobile:id/Popup_sub_header_txt", "com.tonik.mobile:id/Popup_sub_header_txt", "id", "id"),
	objStartOver ("com.tonik.mobile:id/Popup_positive_btn_txt", "com.tonik.mobile:id/Popup_positive_btn_txt", "id", "id"),
	objStayHere ("com.tonik.mobile:id/Popup_negative_btn_txt", "com.tonik.mobile:id/Popup_negative_btn_txt", "id", "id"),
	objCardDetailsPageBackButton ("(//*[contains(@text,'Card Details')])[1]/parent::android.view.ViewGroup/child::android.view.ViewGroup", "//*[@value='Card Details']/parent::*/preceding-sibling::*/child::*/child::*", "xpath", "xpath"),
	objErrMessage ("//*[@resource-id='errorMsgTXT']", "//XCUIElementTypeStaticText[@name=\"Please enter valid card details\"]", "xpath", "xpath"),
	objOKAYButton ("//*[contains(@text,'OKAY')]", "//XCUIElementTypeButton[@name=\"OKAY\"]", "xpath", "xpath"),
	objWeCannotLinkPage ("//*[contains(@text,'We cannot link your card')]", "//*[contains(@value,'We cannot link your card')]", "xpath", "xpath"),
	objWeCannotLinkPageSubTitle ("//*[contains(@text,'We were unable to verify and link your ATM card.')]", "//*[contains(@value,'We were unable to verify and link your ATM card.')]", "xpath", "xpath"),
	objTryAnotherCardButton ("//*[contains(@text,'Try another card')]", "//*[contains(@value,'Try another card')]", "xpath", "xpath"),
	objGoBackToDashboardButton ("//*[contains(@text,'Go back to dashboard')]", "//*[contains(@value,'Go back to dashboard')]", "xpath", "xpath"),
	objCardSuccessfullyLinkedPageTitle ("//*[contains(@text,'Card successfully linked!')]", "//*[contains(@value,'Card successfully linked!')]", "xpath", "xpath"),
	objContinueWithFlexLoanButton ("//*[contains(@text,'Continue with Flex Loan')]", "//*[contains(@value,'Continue with Flex Loan')]", "xpath", "xpath"),
	objWhereDoYouWorkPageExitButton ("com.tonik.mobile:id/Header_right_click", "com.tonik.mobile:id/Header_right_click", "id", "id"),
	objWhereDoYouWorkPageTitle ("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
	objIFoundABetterOfferRadioButton ("I found a better offer", "I found a better offer", "id", "id"),

	objNextButton ("//*[contains(@text,'Next')]", "//*[contains(@value,'Next')]", "xpath", "xpath"),
	objBackToDashboardButton ("//*[contains(@text,'Back to Dashboard')]", "//*[contains(@value,'Back to Dashboard')]", "xpath", "xpath"),
	objPleaseWait ("//*[contains(@text,'Please wait while transaction is being processed.')]", "//*[contains(@value,'Please wait while transaction is being processed.')]", "xpath", "xpath"),
	objCardDetailsBackButton ("//*[contains(@text,'Card Details')]/parent::android.view.ViewGroup/child::android.view.ViewGroup", "", "xpath", "xpath"),
	objSuccessFullyLinkedPageHeader ("//*[contains(@text,'Card successfully linked!')]", "//*[contains(@value,'Card successfully linked!')]", "xpath", "xpath"),
	objSuccessFullyLinkedPageSubHeader ("//*[contains(@text,'Congrats, you can now apply for a Flex Loan.')]", "//*[contains(@value,'Congrats, you can now apply for a Flex Loan.')]", "xpath", "xpath"),
	objLinkANewCardButton ("//*[contains(@text,'Link a new card')]", "//*[contains(@value,'Link a new card')]", "xpath", "xpath"),
	objSavedCardName ("(//*[contains(@text,'Saved cards')]/following-sibling::*/child::*/child::*/child::*/child::*)[2]", "//XCUIElementTypeOther[@name='Saved cards']/following-sibling::*/child::*[1]", "xpath", "xpath"),
	objEllipsisButton ("(//*[contains(@text,'Saved cards')]/following-sibling::*/child::*/child::*/child::*/child::*)[3]", "(//XCUIElementTypeOther[@name='Saved cards']/following-sibling::*/child::*/child::*/child::*/child::*/child::*)[3]/child::*/child::*", "xpath", "xpath"),
	objDeleteCardButton ("//*[contains(@text,' Delete card')]", "//*[contains(@value,' Delete card')]", "xpath", "xpath"),
	objCardDeletedToastMsg ("//*[contains(@text,'Debit card ending xxxxxxxxxxxx')]", "//*[contains(@value,'Debit card ending xxxxxxxxxxxx')]", "xpath", "xpath"),
	objCalculatorScreenBckBtn ("//*[@resource-id='com.tonik.mobile:id/Left_click']", "//*[@name='com.tonik.mobile:id/Left_click']", "xpath", "xpath"),
	objContactUsButton ("com.tonik.mobile:id/Right_click", "com.tonik.mobile:id/Right_click", "id", "id"),
	objIUnderstandButton ("//android.widget.Button[@text,'I Understand']", "//*[@value,'I Understand']", "xpath", "xpath"),
	objYouCanOnlySave3ATMCardsPopupMsg ("com.tonik.mobile:id/Popup_sub_header_txt", "com.tonik.mobile:id/Popup_sub_header_txt", "id", "id"),
	objYouCanOnlySave3ATMCardsPopupOkbtn ("com.tonik.mobile:id/Popup_single_btn_txt", "com.tonik.mobile:id/Popup_single_btn_txt", "id", "id"),
	objCardToChangedTxtInLoanInfoScreen ("((//*[contains(@resource-id,'com.tonik.mobile:id/****')])/parent::android.view.ViewGroup/child::android.widget.TextView)[1]", "//XCUIElementTypeStaticText[contains(@name,'Card to be charged on a monthly basis:')]", "xpath", "xpath"),
	objCardNumberInLoanInfoScreen ("((//*[contains(@resource-id,'com.tonik.mobile:id/****')])/parent::android.view.ViewGroup/child::android.widget.TextView)[2]", "//XCUIElementTypeStaticText[@name=\"Card to be charged on a monthly basis:\"]/parent::*/following-sibling::*/child::*[1]", "xpath", "xpath"),
	objCardExpiredTxt ("//*[contains(@text,'Card expired')]", "//*[contains(@value,'Card expired')]", "xpath", "xpath"),
	objCardStolenTxt ("//*[contains(@text,'Card is stolen')]", "//*[contains(@value,'Card is stolen')]", "xpath", "xpath"),
	objJobChangedTxt ("//*[contains(@text,'Job is changed')]", "//*[contains(@value,'Job is changed')]", "xpath", "xpath"),
	objOtherTxt ("//*[contains(@text,'Other')]", "//*[contains(@value,'Other')]", "xpath", "xpath"),
	objCancelButton ("//*[contains(@text,'Cancel')]", "//*[contains(@value,'Cancel')]", "xpath", "xpath"),
	objOKButton ("//*[contains(@text,'OK')] | //*[contains(@text,'Ok')]", "//*[contains(@value,'OK')] | //*[contains(@value,'Ok')]", "xpath", "xpath"),
	objCardRelinked ("//*[contains(@text,'Card relinked successfully')]", "//*[contains(@value,'Card relinked successfully')]", "xpath", "xpath"),
	objCardAddedSuccessfully ("//*[contains(@text,'was added successfully')]", "//*[contains(@value,'was added successfully')]", "xpath", "xpath"),
	objSignDirectDebitFormButton ("//*[contains(@text,'Sign Direct Debit Form')]", "//XCUIElementTypeStaticText[@name='com.tonik.mobile:id/Custom_button_txt']", "xpath", "xpath"),
	objTryLowerAmountInsteadBtn ("//*[@text='Try a lower amount instead']", "//*[@value='Try a lower amount instead']", "xpath", "xpath"),
	objTryALowerAmountErrorMsg ("//*[contains(@text,'Try a lower amount of up to')]", "//*[contains(@value,'Try a lower amount of up to')]", "xpath", "xpath"),
	objEmployerName ("(//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText)[1]", "(//XCUIElementTypeOther[@name='Employer Name'])[4]", "xpath", "xpath"),
	objYourWorkMail ("(//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText)[2]", "(//XCUIElementTypeOther[@name='Your Work Email'])[4]/XCUIElementTypeOther/XCUIElementTypeTextField", "xpath", "xpath"),

	objContinueBtn ("//*[contains(@text,'Continue')]", "//*[contains(@value,'Continue')]", "xpath", "xpath"),
	objContactReferenceIcon ("(//*[@text='+63 ']/following-sibling::*/following-sibling::*)[1]", "//XCUIElementTypeTextField[@value='+63 ']/parent::*/following-sibling::*/child::XCUIElementTypeOther", "xpath", "xpath"),
	objSearchBar ("//*[@text='Search user']", "//*[@value='Search user']", "xpath", "xpath"),
	objProvideOTP ("(//android.widget.TextView[@text='Provide OTP'])", "(//*[@value='Provide OTP'])", "xpath", "xpath"),
	objAccountHolderName ("(//android.widget.ScrollView/child::*/child::*/child::android.widget.EditText)[1]", "(//XCUIElementTypeOther[@name=\"Account holder name\"])[4]/XCUIElementTypeOther/XCUIElementTypeTextField | //*[@value='Account holder name']/following-sibling::*/child::*", "xpath", "xpath"),
	objBankNameDropDown ("//android.widget.ScrollView/child::*/child::*[2]/child::*/following-sibling::* | //*[@text='Bank name']/preceding-sibling::*", "(//XCUIElementTypeStaticText[@name='Bank name']/parent::*/parent::*/child::*)[2]", "xpath", "xpath"),
	objAccountNumber ("(//android.widget.ScrollView/child::*/child::*/child::android.widget.EditText)[2]", "(//XCUIElementTypeOther[@name='Account number'])[4]/XCUIElementTypeOther/XCUIElementTypeTextField | //*[@value='Account number']/following-sibling::*/child::*", "xpath", "xpath"),
	objProceedButton1 ("//*[contains(@text,'Proceed')]", "//*[contains(@value,'Proceed')]", "xpath", "xpath"),
	objHeyCanYouFlexPageHeader ("//*[contains(@resource-id,'USP_heading')]","//XCUIElementTypeStaticText[@name='USP_heading0']","xpath","xpath"),
	objHeyCanYouFlexSubPageHeader ("//*[contains(@resource-id,'USP_sub_txt')]","//XCUIElementTypeStaticText[@name='USP_sub_txt0']","xpath","xpath"),
	objPageHeader ("//*[contains(@resource-id,'USP_heading')]", "(//*[contains(@name,'USP_heading')])[2]", "xpath", "xpath"),
	objPageSubHeader ("//*[contains(@resource-id,'USP_sub_txt')]", "(//*[contains(@name,'USP_sub_txt')])[2]", "xpath", "xpath"),
	objBankStatement ("//*[contains(@text,'Bank Statement')]", "//*[contains(@value,'Bank Statement')]", "xpath", "xpath"),
	objProofOfIncome ("//*[contains(@text,'Proof of income')]", "//*[contains(@value,'Proof of income')]", "xpath", "xpath"),
	objUploadDocuments ("com.tonik.mobile:id/upload_doc_txt", "com.tonik.mobile:id/upload_doc_txt", "id", "id"),
	objGetYourBankStatementPage ("//*[contains(@text,'Get your bank statement ready!')]", "//*[contains(@value,'Get your bank statement ready!')]", "xpath", "xpath"),
	objReadyToSignButton1("com.tonik.mobile:id/Button_txt","//XCUIElementTypeStaticText[@name='I am ready to sign']","xpath","xpath"),
	objTakePhoto ("//*[contains(@text,' Take a photo')]", "//*[contains(@value,' Take a photo')]", "xpath", "xpath"),
	objUploadImageFromGallery ("//*[contains(@text,' Upload screenshot from gallery')]", "//*[contains(@value,' Upload screenshot from gallery')]", "xpath", "xpath"),
	objChooseActionPopUp ("//*[contains(@text,'Choose an action')]", "//*[contains(@value,'Choose an action')]", "xpath", "xpath"),
	objGallery ("//*[contains(@text,'Gallery')]", "//*[contains(@value,'Gallery')]", "xpath", "xpath"),
	objPerfectButton ("//*[@resource-id='com.tonik.mobile:id/Button_perfect_text']", "(//*[@name='com.tonik.mobile:id/Button_perfect_text'])[2]", "xpath", "xpath"),
	objPaySlip ("com.tonik.mobile:id/Payslip", "com.tonik.mobile:id/Payslip", "id", "id"),

	objGotItBtn ("//*[@resource-id='Got it! click']", "//*[@name='Got it! click']", "xpath", "xpath"),

	objMainTitleTxt ("//*[@resource-id='com.tonik.mobile:id/Main_title_txt']", "//*[@name='com.tonik.mobile:id/Main_title_txt']", "xpath", "xpath"),

	objSubTitleTxt ("com.tonik.mobile:id/Sub_title_txt", "com.tonik.mobile:id/Sub_title_txt", "id", "id"),
	objResendConfirmationCode ("//android.widget.TextView[contains(@text,'Resend confirmation code')]","//*[contains(@value,'Resend confirmation code')]","xpath","xpath"),
	objResendCodeLink ("//android.widget.TextView[@text='Resend Code']","//*[@value='Resend Code']","xpath","xpath"),
	objIOSImageSelect("","(//XCUIElementTypeImage[contains(@label,'Photo')])[1]","xpath","xpath"),
	objTapTheFollowingDocumentsOnHandText ("//*[@text='Tap the following documents on hand.']", "//*[@value='Tap the following documents on hand.']", "xpath", "xpath"),
	objAccountHolderNameErrorMsg("//*[@text='Please enter a valid Account holder name']","//*[@value='Please enter a valid Account holder name']","xpath","xpath"),
	objAccountNumberErrorMsg("//*[@text='Please enter a valid Account number']","//*[@value='Please enter a valid Account number']","xpath","xpath"),
	objNameOfBankTxt("//*[@text='Name of bank']","//*[@value='Name of bank']","xpath","xpath"),
	objNameOfBankField("//*[@text='Name of bank']/preceding-sibling::*","//*[@value='Name of bank']/preceding-sibling::*","xpath","xpath"),
	objSelectedBank("//*[@text='Bank of Commerce']","//*[@value='Bank of Commerce']","xpath","xpath"),
	objInvalidEmailIDErrorMessage ("//*[contains(@text,'Invalid Email id')]", "//*[contains(@value,'Invalid Email id')]", "xpath", "xpath"),
	objEnterValidMobileNumberErrorMessage ("//*[contains(@text,'Please enter a valid mobile number')]", "//*[contains(@value,'Please enter a valid mobile number')]", "xpath", "xpath"),
	objEnterValidEmployerContactPersonErrorMessage ("//*[contains(@text,'Enter valid Employer Contact Person')]", "//*[contains(@value,'Enter valid Employer Contact Person')]", "xpath", "xpath"),
	objHRContactNumberInputField ("(//*[contains(@text,'HR Contact Number')]/following-sibling::*)[2]", "(//XCUIElementTypeTextField)[4]", "xpath", "xpath"),
	objHRContactPersonInputField ("//*[contains(@text,'HR Contact Person')]/preceding-sibling::*", "(//XCUIElementTypeTextField)[5]", "xpath", "xpath"),
	objKeyboardDoneButton("", "//*[@name='Done']", "", "xpath"),
	objKeyboardNextButton("", "//*[@label='next']", "", "xpath"),
	objYourWorkMail1 ("(//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText)[2]", "//XCUIElementTypeStaticText[@label='Your Work Email']/following-sibling::XCUIElementTypeOther/child::XCUIElementTypeTextField", "xpath", "xpath"),
	objEmployerName1 ("(//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText)[1]", "(//XCUIElementTypeTextField)[1]", "xpath", "xpath"),
	objEmployerContactPersonCannotEmptyErrorMessage ("//*[contains(@text,'Employer Contact Person cannot be empty')]", "//*[contains(@value,'Employer Contact Person cannot be empty')]", "xpath", "xpath"),
	objPopupSubHeader("com.tonik.mobile:id/Popup_sub_header_txt","com.tonik.mobile:id/Popup_sub_header_txt","id","id"),
	objPopupBtn("com.tonik.mobile:id/Popup_single_btn_txt","com.tonik.mobile:id/Popup_single_btn_txt","id","id"),
	objDisplayedCard("//*[@text=\"Saved cards\"]/following-sibling::*","//XCUIElementTypeOther[@name=\"Saved cards\"]/following-sibling::XCUIElementTypeOther/XCUIElementTypeOther","xpath","xpath"),
	objAccountNumberField("//*[@text='Account number']/following-sibling::*","//*[@value='Account number']/following-sibling::*","xpath","xpath");
	private String android;
	private String ios;
	private String andPathType;
	private String iosPathType;

	FlexLoanPage(String android, String ios, String andPathType, String iosPathType) {
		this.android = android;
		this.ios = ios;
		this.andPathType = andPathType;
		this.iosPathType = iosPathType;
	}
	FlexLoanPage(String android, String andPathType, String iosPathType) {
		this.android = android;
		this.ios = android;
		this.andPathType = andPathType;
		this.iosPathType = iosPathType;
	}
	FlexLoanPage(String android, String andPathType) {
		this.android = android;
		this.ios = android;
		this.andPathType = andPathType;
		this.iosPathType = andPathType;
	}
	public static By getByOSType(String osType, FlexLoanPage objectName) {
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
	public static By contactSearch(String platform, String searchIndex) {
		if(platform.equalsIgnoreCase("android")) {
			return By.xpath("(//android.widget.ScrollView/child::*/child::*/child::*/child::*/following-sibling::android.widget.TextView)[" + searchIndex + "]");
		}else{
			return By.xpath("(//XCUIElementTypeScrollView/child::*/child::*/child::*/child::*)["+searchIndex+"]");
		}
	}
	public static By  objChooseBank(String platform, String bankName) {
		if(platform.equalsIgnoreCase("android")) {
			return By.xpath("//android.widget.ScrollView/child::*/child::*/child::*/child::android.widget.TextView[contains(@text,'" + bankName + "')]/following-sibling::*");
		}else{
			return By.xpath("//XCUIElementTypeScrollView/child::*/child::*/child::*/child::XCUIElementTypeStaticText[contains(@value,'"+bankName+"')]/following-sibling::*");
		}
	}
	public static By objSelectFolder(String platform, String imageIndex) {
		return By.xpath("(//*[@resource-id='com.sec.android.gallery3d:id/thumbnail'])["+imageIndex+"]");
	}
}