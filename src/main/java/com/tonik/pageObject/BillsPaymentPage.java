package com.tonik.pageObject;

import org.openqa.selenium.By;

public enum BillsPaymentPage {
	//Pay button
	objPayButtonEnable("//*[@text='Pay' and @enabled='true']/preceding-sibling::*/child::*","//*[@value='Pay']/preceding-sibling::*//XCUIElementTypeButton[@enabled='true']","xpath","xpath"),
	objPayButton("//*[@text='Pay']/preceding-sibling::*/child::*","//*[@value='Pay']/preceding-sibling::*//XCUIElementTypeButton","xpath","xpath"),
	//Bills with no frills screen
	objBillsWithNoFrillsTitle("//*[@text='Bills with no frills']","//*[@value='Bills with no frills']","xpath","xpath"),
	objKeepTrackText("//*[contains(@text,'Keep track of your bills')]","//*[contains(@value,'Keep track of your bills')]","xpath","xpath"),
	objNeverMissTitle("//*[contains(@text,'Never miss a due date')]","//*[contains(@value,'Never miss a due date')]","xpath","xpath"),
	objNeverMissDesc("//*[contains(@text,'Avoid late payment')]","//*[contains(@value,'Avoid late payment')]","xpath","xpath"),
	objGiveYourBillTitle("//*[contains(@text,'Give your bill a pet name')]","//*[contains(@value,'Give your bill a pet name')]","xpath","xpath"),
	objGiveYourBillDesc("//*[contains(@text,'Manage multiple bills')]","//*[contains(@value,'Manage multiple bills')]","xpath","xpath"),
	objKeepAddingBillersTitle("//*[contains(@text,'We’ll keep adding billers')]","//*[contains(@value,'We’ll keep adding billers')]","xpath","xpath"),
	objKeepAddingBillersDesc("//*[contains(@text,'Pay your utility')]","//*[contains(@value,'Pay your utility')]","xpath","xpath"),
	objStartABilltext("//*[contains(@text,'Start a bill')]","//*[contains(@value,'Start a bill')]","xpath","xpath"),
	objStartABillCTA("//*[contains(@text,'Start a bill')]/parent::*","//*[contains(@value,'Start a bill')]/parent::*","xpath","xpath"),
	//Bills Pay screen
	objMainTitleText("com.tonik.mobile:id/Main_title_txt","com.tonik.mobile:id/Main_title_txt","id","id"),
	objSubTitleText("com.tonik.mobile:id/Sub_title_txt","com.tonik.mobile:id/Sub_title_txt","id","id"),
	objAddABillerText("//*[@text='Add a Biller']","//XCUIElementTypeStaticText[@name='Add a Biller']","xpath","xpath"),
	objAddIcon("//*[@text='Add a Biller']/preceding-sibling::*","//XCUIElementTypeStaticText[@name='Add a Biller']","xpath","xpath"),
	objBillsPayWait("com.tonik.mobile:id/billPayBETA","com.tonik.mobile:id/billPayBETA","id","id"),
	//Need some funds screen
	objPopUpTitle("com.tonik.mobile:id/Popup_title_text","com.tonik.mobile:id/Popup_title_text","id","id"),
	objPopUpDesc("com.tonik.mobile:id/Popup_Description","com.tonik.mobile:id/Popup_Description","id","id"),
	objPopUpButton("com.tonik.mobile:id/Popup_positive_button_Clicked","com.tonik.mobile:id/Popup_positive_button_Clicked","id","id"),
	objOkLetsGoText("//*[@text='Okay, let’s go']","//*[@value='Okay, let’s go']","xpath","xpath"),
	objTSAAmountFocus("(//*[@text='Your tonik account']/following-sibling::*[@focusable='false'])[1]","//*[@value='Your tonik account']/parent::*[@accessible='false']","xpath","xpath"),
	//Billers screen
	objSearchBillerField("//*[@text='Search biller']","//XCUIElementTypeTextField","xpath","xpath"),
	objSearchBillerInput("//*[@text='Search biller']/following-sibling::*","//XCUIElementTypeTextField","xpath","xpath"),
	objShowAllText("//*[@text='Show all']","//*[@value='Show all']","xpath","xpath"),
	objPopularBillerText("(//*[@text='Popular Billers'])[1]","(//*[@value='Popular Billers'])[1]","xpath","xpath"),
	objPopularBillerButton("(//*[@text='Popular Billers'])[1]/parent::*","(//*[@value='Popular Billers'])[1]/parent::*","xpath","xpath"),
	objCreditCardsText("//*[@text='Credit Cards']","(//*[@value='Credit Cards'])[1]","xpath","xpath"),
	objCreditCardsButton("//*[@text='Credit Cards']/parent::*","(//*[@value='Credit Cards'])[1]/parent::*","xpath","xpath"),
	objCreditCardsTitle("(//*[@text='Credit Cards'])[2]","(//*[@value='Credit Cards'])[2]","xpath","xpath"),
	objETollText("//*[@text='e-Toll']","(//*[@value='e-Toll'])[1]","xpath","xpath"),
	objETollButton("//*[@text='e-Toll']/parent::*","(//*[@value='e-Toll'])[1]/parent::*","xpath","xpath"),
	objETollTitle("(//*[@text='e-Toll'])[2]","(//*[@value='e-Toll'])[2]","xpath","xpath"),
	objElectricUtilityText("//*[@text='Electric Utility']","(//*[@value='Electric Utility'])[1]","xpath","xpath"),
	objElectricUtilityButton("//*[@text='Electric Utility']/parent::*","(//*[@value='Electric Utility'])[1]/parent::*","xpath","xpath"),
	objElectricUtilityTitle("(//*[@text='Electric Utility'])[2]","(//*[@value='Electric Utility'])[2]","xpath","xpath"),
	objGovernmentText("//*[@text='Government']","(//*[@value='Government'])[1]","xpath","xpath"),
	objGovernmentTitle("(//*[@text='Government'])[2]","(//*[@value='Government'])[2]","xpath","xpath"),
	objGovernmentButton("//*[@text='Government']/parent::*","(//*[@value='Government'])[1]/parent::*","xpath","xpath"),
	objTelecomsText("//*[@text='Telecoms']","(//*[@value='Telecoms'])[1]","xpath","xpath"),
	objTelecomsButton("//*[@text='Telecoms']/parent::*","(//*[@value='Telecoms'])[1]/parent::*","xpath","xpath"),
	objTelecomsTitle("(//*[@text='Telecoms'])[2]","(//*[@value='Telecoms'])[2]","xpath","xpath"),
	objWaterUtilityText("//*[@text='Water Utility']","(//*[@value='Water Utility'])[1]","xpath","xpath"),
	objWaterUtilityButton("//*[@text='Water Utility']/parent::*","(//*[@value='Water Utility'])[1]/parent::*","xpath","xpath"),
	objWaterUtilityTitle("(//*[@text='Water Utility'])[2]","(//*[@value='Water Utility'])[2]","xpath","xpath"),
	objPopularBillersTitle("(//*[@text='Popular Billers'])[2]","(//*[@value='Popular Billers'])[2]","xpath","xpath"),
	objShowAllBillers("//*[contains(@text,'Processing')]/preceding-sibling::android.widget.TextView","//*[contains(@value,'Processing')]/parent::*/preceding-sibling::*","xpath","xpath"),
	objAllProcessing("//*[contains(@text,'Processing')]","//*[contains(@value,'Processing')]","xpath","xpath"),
	objSelectBiller("//*[contains(@text,'Processing')]/parent::*","//*[contains(@value,'Processing')]/parent::*","xpath","xpath"),
	objBillerField("(//*[@text='Next']/parent::*/preceding-sibling::*)[2]","//*[@name='Next']/preceding-sibling::*//XCUIElementTypeTextField","xpath","xpath"),
	objNextButton("//*[@text='Next']/parent::*","//*[contains(@name,'touchable')]","xpath","xpath"),
	objNextButtonDisabled("//*[@text='Next']/parent::*[@enabled='false']","//*[contains(@name,'touchable') and @enabled='false']","xpath","xpath"),
	objNextText("//*[@text='Next']","(//*[@name='Next'])[2]","xpath","xpath"),
	//Account Info screen
	objAccountNumberText("//*[@text='Account Number']","//*[@value='Account Number']","xpath","xpath"),
	objAccountNumberField("//*[@text='Account Number']/following-sibling::*","XCUIElementTypeTextField","xpath","className"),
	objShouldContainText("//*[contains(@text,'Should contain')]","//*[contains(@value,'Should contain')]","xpath","xpath"),
	objAccountNameText("//*[contains(@text,'Account Name')]","//*[@value='Account Name']","xpath","xpath"),
	objAccountNameField("//*[contains(@text,'Account Name')]/following-sibling::*","//*[@value='Account Name']/preceding-sibling::*/child::*","xpath","xpath"),
	objAccountNumberErrorMessage("//*[@text='Please provide the account number.']","//*[@value='Please provide the account number.']","xpath","xpath"),
	objAccountNameErrorMessage("//*[@text='Account Name']/parent::*/following-sibling::*","//*[@value='Account Name']/parent::*/following-sibling::*","xpath","xpath"),
	objPaymentTypeErrorMessage("(//*[@text='Payment Type']/parent::*/following-sibling::*)[2]","//*[contains(@value,'Please provide the payment')]","xpath","xpath"),
	objContactNumberErrorMessage("(//*[@text='Contact Number']/following-sibling::*)[2]","//*[@value='Contact Number']/parent::*/parent::*/following-sibling::*","xpath","xpath"),
	objPhoneNumberErrorMessage("//*[@text='Phone Number']/parent::*/following-sibling::*","//*[@value='Phone Number']/parent::*/following-sibling::*/child::*","xpath","xpath"),
	objServicErrorMessage("(//*[@text='Service']/parent::*/following-sibling::*)[2]","(//*[@value='Service']/parent::*/following-sibling::*/child::*)[2]","xpath","xpath"),
	objProductCodeErrorMessage("(//*[@text='Product Code']/parent::*/following-sibling::*)[2]","(//*[@value='Product Code']/parent::*/following-sibling::*/child::*)[2]","xpath","xpath"),
	objMobileNumberErrorMessage("(//*[@text='Mobile Number']/following-sibling::*)[2]","com.tonik.mobile:id/error3","xpath","id"),
	objRefNumberErrorMessage("//*[@text='Reference Number']/parent::*/following-sibling::*","//*[@value='Reference Number']/parent::*/following-sibling::*/child::*","xpath","xpath"),
	objPayForErrorMessage("(//*[@text='Pay For']/parent::*/following-sibling::*)[2]","(//*[@value='Pay For']/parent::*/following-sibling::*/child::*)[2]","xpath","xpath"),
	objMemberNameErrorMessage("//*[@text='Member Name']/parent::*/following-sibling::*","//*[@value='Member Name']/parent::*/following-sibling::*/child::*","xpath","xpath"),
	objPaymentTypeText("//*[@text='Payment Type']","//*[@value='Payment Type']","xpath","xpath"),
	objBillDateField("//*[@text='Bill Date']/following-sibling::*","//*[@value='Bill Date']/following-sibling::*/child::*","xpath","xpath"),
	objDueDateField("//*[@text='Due Date']/following-sibling::*","//*[@value='Due Date']/following-sibling::*/child::*","xpath","xpath"),
	objContactNumberText("//*[@text='Contact Number']","//*[@value='Contact Number']","xpath","xpath"),
	objCountryCode("(//*[@text='Contact Number']/following-sibling::*/child::*)[1]","(//*[@value='Contact Number']/parent::*/following-sibling::*/child::*)[1]","xpath","xpath"),
	objContactNumberField("(//*[@text='Contact Number']/following-sibling::*/child::*)[2]","com.tonik.mobile:id/buttonnamedigitalCount","xpath","id"),
	objPhoneNumberText("//*[@text='Phone Number']","//*[@value='Phone Number']","xpath","xpath"),
	objPhoneNumberField("//*[@text='Phone Number']/following-sibling::*","//*[@value='Phone Number']/preceding-sibling::*/child::*","xpath","xpath"),
	objServiceText("//*[@text='Service']","//*[@value='Service']","xpath","xpath"),
	objProductCodeText("//*[@text='Product Code']","//*[@value='Product Code']","xpath","xpath"),
	objMobileNumberText("//*[@text='Mobile Number']","//*[@value='Mobile Number']","xpath","xpath"),
	objMobileNumberField("com.tonik.mobile:id/digitalCount","com.tonik.mobile:id/digitalCount","id","id"),
	objSRNText("//*[@text='Service Reference Number']","//*[@value='Service Reference Number']","xpath","xpath"),
	objSRNField("//*[@text='Service Reference Number']/following-sibling::*","//*[@value='Service Reference Number']/preceding-sibling::*/child::*","xpath","xpath"),
	objRefNumberText("//*[@text='Reference Number']","//*[@value='Reference Number']","xpath","xpath"),
	objShouldStartWithText("//*[contains(@text,'Should start with')]","//*[contains(@value,'Should start with')]","xpath","xpath"),
	objPayForText("//*[@text='Pay For']","//*[@value='Pay For']","xpath","xpath"),
	objMemberNameText("//*[@text='Member Name']","//*[@value='Member Name']","xpath","xpath"),
	objPRNText("//*[@text='Payment Reference Number']","//*[@value='Payment Reference Number']","xpath","xpath"),
	objLastNameText("//*[@text='Last Name']","//*[@value='Last Name']","xpath","xpath"),
	objLastNameField("//*[@text='Last Name']/following-sibling::*","//*[@value='Last Name']/preceding-sibling::*/child::*","xpath","xpath"),
	objFirstNameText("//*[@text='First Name']","//*[@value='First Name']","xpath","xpath"),
	objFirstNameField("//*[@text='First Name']/following-sibling::*","//*[@value='First Name']/preceding-sibling::*/child::*","xpath","xpath"),
	//Payment Type screen
	objShortTermLoan("//*[@text='Short Term Loan']","//*[@value='Short Term Loan']","xpath","xpath"),
	objHousingLoan("//*[@text='Housing Loan']","//*[@value='Housing Loan']","xpath","xpath"),
	objMembershipSavings("//*[@text='Membership Savings']","//*[@value='Membership Savings']","xpath","xpath"),
	objModifiedPagIBIG("//*[@text='Modified Pag-IBIG 2']","//*[@value='Modified Pag-IBIG 2']","xpath","xpath"),
	objBillDateText("//*[@text='Bill Date']","//*[@value='Bill Date']","xpath","xpath"),
	objDueDateText("//*[@text='Due Date']","//*[@value='Due Date']","xpath","xpath"),
	//Service screen
	objPLDTLandline("//*[@text='PLDT Landline']","//*[@value='PLDT Landline']","xpath","xpath"),
	//Product Code screen
	objSmartBro("//*[@text='Smart Bro']","//*[@value='Smart Bro']","xpath","xpath"),
	objSmartSun("//*[@text='Smart/Sun']","//*[@value='Smart/Sun']","xpath","xpath"),
	//When is your due date screen
	objRemindMeText("//*[contains(@text,'Remind me')]","//*[contains(@value,'Remind me to pay')]","xpath","xpath"),
	objRemindToggleButton("//*[contains(@text,'Remind me')]/following-sibling::*","//*[contains(@value,'Remind me to pay')]/following-sibling::*","xpath","xpath"),
	objSkipButton("//*[@text='Skip']/parent::*","//*[@value='Skip']","xpath","xpath"),
	objSaveButton("//*[@text='Save']/parent::*","//*[@value='Save']","xpath","xpath"),
	//<Biller> was successfully created screen
	objBillerCreatedText("//*[contains(@text,'successfully created')]","//*[contains(@value,'successfully created')]","xpath","xpath"),
	objYouCanPayNowText("//*[contains(@text,'you can pay now')]","//*[contains(@value,'you can pay now')]","xpath","xpath"),
	objGoToBillersList("//*[contains(@text,'Go to billers list')]","//*[contains(@value,'Go to billers list')]","xpath","xpath"),
	objPayNowButton("//*[@text='Pay now']/parent::*","com.tonik.mobile:id/touchable","xpath","id"),
	objAmountField("(//*[contains(@text,'My Tonik Account')]/following-sibling::*)[2]","com.tonik.mobile:id/textInput","xpath","id"),
	objMyTonikAccount("//*[contains(@text,'My Tonik Account')]","//*[contains(@value,'My Tonik Account')]","xpath","xpath"),
	objAmountToBePaid("//*[contains(@text,'Amount to be paid')]","//*[contains(@value,'Amount to be paid')]","xpath","xpath"),
	objWithConvenienceFee("//*[contains(@text,'With Convenience Fee')]","//*[contains(@value,'With Convenience Fee')]","xpath","xpath"),
	objWithBankFee("//*[contains(@text,'With Bank Fee')]","//*[contains(@value,'With Bank Fee')]","xpath","xpath"),
	//confirm bills payment
	objAmountValue("(//*[@text='Amount']/following-sibling::*)[1]","//*[@value='Amount']/following-sibling::*","xpath","xpath"),
	objConvenienceFee("(//*[@text='Convenience Fee']/following-sibling::*)[1]","//*[@value='Convenience Fee']/following-sibling::*","xpath","xpath"),
	objBillerName("(//*[@text='Biller Name']/following-sibling::*)[1]","//*[@value='Biller Name']/following-sibling::*","xpath","xpath"),
	objAccountInfo("(//*[@text='Account Info']/following-sibling::*)[1]","//*[@value='Account Info']/following-sibling::*","xpath","xpath"),
	objConfirmButton("//*[@text='Confirm']/parent::*","com.tonik.mobile:id/touchable","xpath","id"),
	objCloseIcon("com.tonik.mobile:id/Header_right_click","com.tonik.mobile:id/Header_right_click","id","id"),
	//Enter OTP screen
	objOTPField("(//*[contains(@text,'We sent the OTP')]/parent::*/parent::*/following-sibling::*)[1]","((//*[@name='Resend code'])[1]/preceding-sibling::*)[3]","xpath","xpath"),
	objResendCodeText("//*[@text='Resend code']","//*[@value='Resend code']","xpath","xpath"),
	objBackToDashboardButton("//*[@text='Back to Dashboard']/parent::*","//*[@value='Back to Dashboard']/parent::*","xpath","xpath"),
	//Transaction Status
	objTransactionStatusOnHistory("(//*[contains(@text,'Account History')]/following::android.widget.TextView)[7]","((//*[contains(@value,'Today')]/parent::*/following-sibling::*)[1]//XCUIElementTypeStaticText)[2]","xpath","xpath"),
	objAmountErrorMessage("(//*[contains(@text,'My Tonik Account')]/following-sibling::*)[4]","com.tonik.mobile:id/targetErrorMsg","xpath","id"),
	objSitTightLuvHeader("//*[contains(@text,'Sit tight')]","//*[contains(@value,'Sit tight')]","xpath","xpath"),
	objSitTightLuvDesc("(//*[contains(@text,'Sit tight')]/following-sibling::*)[1]","(//*[contains(@value,'Sit tight')]/following-sibling::*)[2]","xpath","xpath"),
	objLatestTransaction("(//*[contains(@text,'Transaction')])[1]","(//*[contains(@value,'Transaction')])[1]","xpath","xpath"),
	objProcessingTransaction("//*[@text='Processing']","(//*[contains(@value,'Processing')])[1]","xpath","xpath"),
	objTSABalance("(//*[contains(@text,'Account History')]/following::android.widget.TextView)[2]","(//*[contains(@value,'ACCOUNT NUMBER')]/parent::*/following-sibling::*/child::*)[1]","xpath","xpath"),
	objDate("//*[@text='9']","//*[@value='9']","xpath","xpath"),
	objOkButton("//*[@text='OK']","//*[@value='Ok']","xpath","xpath"),
	objProcessing("//*[contains(@text,'Processing')]","//*[contains(@value,'Processing')]","xpath","xpath"),
	objProcessingDesc("","(//*[contains(@value,'Processing')]/following-sibling::*)[2]","xpath","xpath"),
	objInfoPageBackIcon("//*[@resource-id='com.tonik.mobile:id/Header_left_click']","(//*[@name='com.tonik.mobile:id/Header_left_click'])[2]","xpath","xpath"),
	//account history
	objAccountNumber("//*[contains(@text,'ACCOUNT NUMBER')]","//*[contains(@value,'ACCOUNT NUMBER')]","xpath","xpath"),
	objTSAAmount("(//*[contains(@text,'ACCOUNT NUMBER')]/following-sibling::*)[1]","(//*[contains(@value,'ACCOUNT NUMBER')]/parent::*/following-sibling::*/child::*)[1]","xpath","xpath"),
	objTopUpText("//*[@text='Top up']","//*[@value='Top up']","xpath","xpath"),
	objManageText("//*[@text='Manage']","//*[@value='Manage']","xpath","xpath"),
	objAccountNumberTextField("//*[@text='Account Number']/preceding-sibling::android.widget.EditText","XCUIElementTypeTextField","xpath","className"),
	objAccountNameTextField("//*[@text='Account Name']/preceding-sibling::android.widget.EditText","//*[@value='Account Name']/preceding-sibling::*/child::*","xpath","xpath"),
	objAccountInfoPageHeader("//*[@text='Account info']","//*[@value='Account info']","xpath","xpath"),
	objBillersCreatedSuccessMessage("//*[contains(@text,'successfully created')]","//*[contains(@value,'successfully created')]","xpath","xpath"),
	objGoToBillersListLink("//*[contains(@text,'Go to billers list')]","//*[contains(@value,'Go to billers list')]","xpath","xpath"),
	objConfirmBillsPaymentPageHeader("//*[contains(@text,'Confirm Bills Payment')]","//*[contains(@value,'Confirm Bills Payment')]","xpath","xpath"),
	objResendCodeLinkText("//*[contains(@text,'Resend code')]","//*[contains(@value,'Resend code')]","xpath","xpath"),
	objButterFingers("//*[@text='Butterfingers!']","//*[@value='Butterfingers!']","xpath","xpath"),
	objButterFingersDescription("//*[@text='Butterfingers!']/following-sibling::android.widget.TextView","(//*[@value='Butterfingers!']/following-sibling::*)[2]","xpath","xpath"),
	objFirstBillsEllipsisIcon ("//*[@text='My bills']/following-sibling::android.widget.ScrollView/descendant::android.view.ViewGroup[5]","(//*[contains(@name,'menuMore')])[1]","xpath","xpath"),
	objFirstBillsAccountNumber("//*[@text='My bills']/following-sibling::android.widget.ScrollView/descendant::android.widget.TextView[2]","(//*[contains(@name,'accNumber')])[1]","xpath","xpath"),
	objMybillsText("//*[@text='My bills']","//*[@value='My bills']","xpath","xpath"),
	objFirstMyBillsList("//*[@text='My bills']/following-sibling::android.widget.ScrollView/descendant::android.view.ViewGroup[3]","(//*[contains(@name,'touchable')])[2]","xpath","xpath"),
	objFirstPopularBillers("(//*[@text='Popular Billers'])[2]/../following-sibling::android.view.ViewGroup[1]","(//*[@value='Popular Billers'])[2]/../following-sibling::*[1]","xpath","xpath"),
	objNameThisBillPageHeader("//*[@text='Name this bill']","//*[@value='Name this bill']","xpath","xpath"),
	objTonikAccountNumberText("//*[@text='Your tonik account']","//*[@value='Your tonik account']","xpath","xpath"),
	objTonikAccountNumber("(//*[@text='Your tonik account']/following-sibling::*)[1]","(//*[@value='Your tonik account']/following-sibling::*)[1]","xpath","xpath"),
	objWhenIsYourDueDate("//*[@text='When is your due date?']","//*[@value='When is your due date?']","xpath","xpath"),
	objPayBill("//*[@text='Pay Bill']","//*[@value='Pay Bill']","xpath","xpath"),
	objEnterOtpPage("//*[@text='Enter OTP']","//*[@value='Enter OTP']","xpath","xpath"),
	objErrorMessage("com.tonik.mobile:id/targetErrorMsg","com.tonik.mobile:id/targetErrorMsg","id","id"),
	objPaynowButtonInSuccessPage("com.tonik.mobile:id/nextText","com.tonik.mobile:id/nextText","id","id"),
	objTonikAmountOnDashboard("(//*[contains(@text,'Your tonik account')]/following::android.widget.TextView)[1]", "(//*[@value='Your tonik account']/following-sibling::*)[1]","xpath","xpath"),
	//Email Verification popup
	objVerificationDesc("com.tonik.mobile:id/Popup_sub_header_txt","com.tonik.mobile:id/Popup_sub_header_txt","id","id"),
	objSendVerificationText("com.tonik.mobile:id/Popup_positive_btn_txt","com.tonik.mobile:id/Popup_positive_btn_txt","id","id"),
	objSendVerificationBtn("com.tonik.mobile:id/Popup_positive_btn_click","com.tonik.mobile:id/Popup_positive_btn_click","id","id"),
	objUpdateEmailAddressText("com.tonik.mobile:id/Popup_negative_btn_txt","com.tonik.mobile:id/Popup_negative_btn_txt","id","id"),
	objCodeExpiringText("//*[contains(@text,'Code expiring')]","//*[contains(@value,'Code expiring')]","xpath","xpath"),
	objOTPFieldForEmail("(//*[contains(@text,'Code expiring')]/preceding-sibling::*)[2]","(//*[contains(@value,'Code expiring')]/parent::*/parent::*/preceding-sibling::*)[2]","xpath","xpath"),
	objCloseButton("//*[@text='Close']/parent::*","//*[@value='Close']/parent::*","xpath","xpath"),
	objNooiceText("//*[@text='Nooice!']","//*[@value='Nooice!']","xpath","xpath"),
	objNooiceDesc("//*[contains(@text,'Thanks for verifying')]","//*[contains(@value,'Thanks for verifying')]","xpath","xpath"),
	objNameOfTheStashOnHistory("(//*[contains(@text,'Account History')]/following::android.widget.TextView)[6]", "((//*[contains(@value,'Today')]/parent::*/following-sibling::*)[1]//XCUIElementTypeStaticText)[1]","xpath","xpath"),
	objTransactionValueOnHistory("(//*[contains(@text,'Account History')]/following::android.widget.TextView)[8]", "((//*[contains(@value,'Today')]/parent::*/following-sibling::*)[1]//XCUIElementTypeStaticText)[3]","xpath","xpath"),
	objAmountDetails("//*[@text='Amount']/following-sibling::*", "//*[@value='Amount']/parent::*/following-sibling::*/child::*","xpath","xpath"),
	objFromAccountTxt("//*[@text='From']/following-sibling::*", "//*[@value='From']/parent::*/following-sibling::*/child::*","xpath","xpath"),
	objToAccountxt("//*[@text='To']/following-sibling::*", "//*[@value='To']/parent::*/following-sibling::*/child::*","xpath","xpath"),
	objWhenDetails("//*[@text='When']/following-sibling::*", "//*[@value='When']/parent::*/following-sibling::*/child::*","xpath","xpath"),
	objRefNoDetails("//*[@text='Reference No.']/following-sibling::*", "//*[@value='Reference No.']/parent::*/following-sibling::*/child::*","xpath","xpath"),
	objHistoryIcon("//*[contains(@text,'History')]//preceding::android.view.ViewGroup[2]", "//*[@value='History']/preceding-sibling::*//XCUIElementTypeButton","xpath","xpath"),
	objProfileName("//*[@resource-id='appbar-content-title-text']", "//*[@name='appbar-content-title-text']","xpath","xpath"),
	objBackIcon("//*[@resource-id='com.tonik.mobile:id/Header_left_click']", "//*[@name='com.tonik.mobile:id/Header_left_click']","xpath","xpath"),
	objBillerNameErrorMessage("//*[@text='Please enter a valid Bill Name']","//*[@value='Please enter a valid Bill Name']","xpath","xpath");
	private String android;
	private String ios;
	private String andPathType;
	private String iosPathType;
	BillsPaymentPage(String android, String ios, String andPathType, String iosPathType) {
		this.android = android;
		this.ios = ios;
		this.andPathType = andPathType;
		this.iosPathType = iosPathType;
	}
	BillsPaymentPage(String android, String andPathType, String iosPathType) {
		this.android = android;
		this.ios = android;
		this.andPathType = andPathType;
		this.iosPathType = iosPathType;
	}
	BillsPaymentPage(String android, String andPathType) {
		this.android = android;
		this.ios = android;
		this.andPathType = andPathType;
		this.iosPathType = andPathType;
	}
	public static By getByOSType(String osType, BillsPaymentPage objectName) {
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
		else if (osType.equalsIgnoreCase("ios") && objectName.iosPathType.equalsIgnoreCase("className"))
		{
			return By.className(objectName.ios);
		}
		throw new IllegalArgumentException("Object not found: " + objectName);
	}
	public static  By objBillerName(String osType, String name) {
		if(osType.equalsIgnoreCase("Android")){
			return By.xpath("//*[contains(@text,'"+name+"')] ");
		}else{
			return By.xpath("//*[contains(@value,'"+name+"')]");
		}
	}
	public static  By objCalendarDate(String osType,String date){
        if(osType.equalsIgnoreCase("Android")){
			return By.xpath("//*[contains(@text,'"+date+"')]");
		}else{
			return By.xpath("//*[contains(@value,'"+date+"')]");
		}
    }
	public static  By objTextButton(String osType, String text) {
		if(osType.equalsIgnoreCase("Android")){
			return By.xpath("//*[contains(@text,'"+text+"')]");
		}else{
			return By.xpath("//*[contains(@value,'"+text+"')]");
		}
	}
	public static  By objSavedBillersBasedOnAccountNumber(String osType, String accountNumber){
		if(osType.equalsIgnoreCase("Android")){
			return  By.xpath("(//*[@text='"+accountNumber+"'])[1]");
		}else{
			return  By.xpath("(//*[@value='"+accountNumber+"'])[1]");
		}
	}
	public static  By objAllBillersName(String osType, String biller){
		if(osType.equalsIgnoreCase("Android")){
			return By.xpath("//*[@resource-id='com.tonik.mobile:id/"+biller+"']");
		}else{
			return By.xpath("//*[contains(@value,'"+biller+"')]");
		}
	}
	public static  By objBillsPaymentPageDetailName(String osType, String detail){
		if(osType.equalsIgnoreCase("Android")){
			return By.xpath("//*[@resource-id='com.tonik.mobile:id/"+detail+"']");
		}else{
			return By.xpath("//*[@name='com.tonik.mobile:id/"+detail+"']");
		}
	}
	public static  By objBillsPaymentPageDetailValue(String osType, String detail){
		if(osType.equalsIgnoreCase("Android")){
			return By.xpath("//*[@resource-id='com.tonik.mobile:id/"+detail+"']/following-sibling::android.widget.TextView");
		}else{
			return By.xpath("//*[@name='com.tonik.mobile:id/"+detail+"']/following-sibling::*");
		}
	}
}