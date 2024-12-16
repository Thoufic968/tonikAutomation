package com.tonik.pageObject;
import org.openqa.selenium.By;
public enum DebitCardPage {
	//Debit card on dashboard
	objDebitCardText("//*[contains(@text,'Debit Card')]","//*[contains(@value,'Debit Card')]","xpath","xpath"),
	objLearnMoreText("//*[contains(@text,'Learn More')]","//*[contains(@value,'Learn More')]","xpath","xpath"),
	objDebitCardTile("//*[contains(@text,'Debit Card')]/parent::*","//*[contains(@value,'Debit Card')]/parent::*","xpath","xpath"),
	//Cards Screen
	objHeader("com.tonik.mobile:id/Main_title_txt","com.tonik.mobile:id/Main_title_txt","id","id"),
	objDebitCardImage("(//*[contains(@resource-id,'Card_Details_View')])[2]/child::*","(//*[@name='com.tonik.mobile:id/Card_Details_View'])[2]","xpath","xpath"),
	objGetPhysicalText("//*[contains(@text,'get Physical')]","//*[contains(@value,'get Physical')]","xpath","xpath"),
	objCardContentText("com.tonik.mobile:id/LGP_Card_Content_Text","com.tonik.mobile:id/LGP_Card_Content_Text","id","id"),
	objCardContentTextGroup("(//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView)[2]","//XCUIElementTypeOther[@name=\"Let's get Physical\"]/following-sibling::*/XCUIElementTypeStaticText","xpath","xpath"),
	objBancnetButton("com.tonik.mobile:id/Bancnet_Debit_Card_Button","com.tonik.mobile:id/Bancnet_Debit_Card_Button","id","id"),
	objConfirmAddressButton("com.tonik.mobile:id/Confirm_Address_Button","com.tonik.mobile:id/Confirm_Address_Button","id","id"),
	objConfirmAddressText("com.tonik.mobile:id/Confirm_Address_Text","com.tonik.mobile:id/Confirm_Address_Text","id","id"),
	//where you want your card deliver
	objZipCodeInput("com.tonik.mobile:id/Card_Zipcode_Input","com.tonik.mobile:id/Card_Zipcode_Input","id","id"),
	objApplyZipcodeButton("com.tonik.mobile:id/Card_Apply_Zipcode_Button","com.tonik.mobile:id/Card_Apply_Zipcode_Button","id","id"),
	objInlineErrorMessage("com.tonik.mobile:id/Card_Find_Zipcode_Text","com.tonik.mobile:id/Card_Find_Zipcode_Text","id","id"),
	objProvinceInput("com.tonik.mobile:id/Card_Province_Input","com.tonik.mobile:id/Card_Province_Input","id","id"),
	objCityInput("//*[contains(@resource-id,'Card_City_Input')]/child::*/child::android.widget.EditText","//*[contains(@name,'Card_City_Input')]/child::*/child::*/child::*","xpath","xpath"),
	objBarangayInput("//*[contains(@resource-id,'Card_Barangay_Input')]/child::*/child::android.widget.EditText","//*[contains(@name,'Card_Barangay_Input')]/child::*/child::*/child::*","xpath","xpath"),
	objCardAddressInput("com.tonik.mobile:id/Card_Address_Input","com.tonik.mobile:id/Card_Address_Input","id","id"),
	objMobileNoText("com.tonik.mobile:id/Card_Mobile_No_Text","com.tonik.mobile:id/Card_Mobile_No_Text","id","id"),
	objMobileNoFieldDisabled("//*[contains(@resource-id,'Card_Mobile_No_Text') and @enabled='false']","//*[contains(@name,'Card_Mobile_No_Text') and @enabled='false']","xpath","xpath"),
	objAlternativeMobileNoInput("com.tonik.mobile:id/Card_ALT_Mobile_Number_Input","com.tonik.mobile:id/Card_ALT_Mobile_Number_Input","id","id"),
	objDeliveryInstructionsInput("com.tonik.mobile:id/Card_Delivery_Inst_Input","com.tonik.mobile:id/Card_Delivery_Inst_Input","id","id"),
	objCardNextButton("com.tonik.mobile:id/Card_Next_Button","com.tonik.mobile:id/Card_Next_Button","id","id"),
	objCardNextButtonEnabled("//*[contains(@resource-id,'Card_Next_Button') and @enabled='true']","//*[contains(@name,'Card_Next_Button') and @enabled='true']","xpath","xpath"),
	//Find your Barangay
	objNorthside("//*[@text='POST PROPER NORTHSIDE']","//*[@value='POST PROPER NORTHSIDE']","xpath","xpath"),
	//spaghetti-os screen
	objSpaghettiText("//*[@text='Uh oh spaghetti-os!']","//*[@value='Uh oh spaghetti-os!']","xpath","xpath"),
	objSpaghettiDesc("//*[@text='Uh oh spaghetti-os!']/following-sibling::android.widget.TextView","(//*[@name='Uh oh spaghetti-os!']/following-sibling::*)[2]","xpath","xpath"),
	objBackToDashBoard("//*[@text='Back to Dashboard']","//*[@value='Back to Dashboard']","xpath","xpath"),
	objTopUpAccount("//*[@text='Top-up account']","//*[@value='Top-up account']","xpath","xpath"),
	objTopUpAccountButton("//*[@text='Top-up account']/parent::*","(//*[@name='Top-up account'])[2]","xpath","xpath"),
	//Top up options Screen
	objOnline("//*[@text='Online']","//*[@value='Online']","xpath","xpath"),
	//online top up options
	objGCashOption("//*[@text=' GCash']/following-sibling::*","//*[@value=' GCash']/parent::*/following-sibling::*/child::*","xpath","xpath"),
	//Top up amount
	objAmountField("(//*[contains(@text,'Balance')]/following-sibling::*)[2]","(//*[@type='XCUIElementTypeTextField'])[2]","xpath","xpath"),
	objNextButton("//*[@text='Next']/parent::*","//*[@value='Next']/parent::*","xpath","xpath"),
	objProceedToPay("//*[@text='Proceed to Pay']","//*[@name='Proceed to Pay']","xpath","xpath"),
	//Vroom Vroom Baby screen
	objVroombabyTitle("//*[@text='Vroom vroom, baby']","//*[@value='Vroom vroom, baby']","xpath","xpath"),
	objBackToDashboardButton("//*[@text='Back to Dashboard']/parent::*","//*[@value='Back to Dashboard']/parent::*","xpath","xpath"),
	objOnTheWay("//*[@text='On the way']","//*[@value='On the way']","xpath","xpath"),
	objOnTheWayText("com.tonik.mobile:id/Card_On_The_Way_Text","com.tonik.mobile:id/Card_On_The_Way_Text","id","id"),
	objCardTitleText("com.tonik.mobile:id/Card_OTW_Title_Text","com.tonik.mobile:id/Card_OTW_Title_Text","id","id"),
	objCardMsgText("com.tonik.mobile:id/Card_OTW_Msg_Text","com.tonik.mobile:id/Card_OTW_Msg_Text","id","id"),
	objCardDescText("com.tonik.mobile:id/Card_OTW_DESC_Text","com.tonik.mobile:id/Card_OTW_DESC_Text","id","id"),
	objTwoDigitOnDebitCard("(//*[contains(@text,'Debit Card')]/following-sibling::*)[1]","(//*[contains(@value,'Debit Card')]/following-sibling::*/child::*)[1]","xpath","xpath"),
	objLockedOnDebitCardTile("//*[@text='Locked']","//*[@value='Locked']","xpath","xpath"),
	//Active Cards screen
	objActiveDebitCardImage("(//*[contains(@resource-id,'Card_Details_View')])[2]","(//*[contains(@name,'Card_Details_View')])[2]","xpath","xpath"),
	objLockPinText("//*[contains(@text,'Lock card')]","//*[contains(@value,'Lock card')]","xpath","xpath"),
	objRestPinText("//*[contains(@text,'Reset PIN')]","//*[contains(@value,'Reset PIN')]","xpath","xpath"),
	objSecurityText("//*[contains(@text,'Security')]","//*[contains(@value,'Security')]","xpath","xpath"),
	objCardLimitsText("//*[contains(@text,'Card limits')]","//*[contains(@value,'Card limits')]","xpath","xpath"),
	objCardFeesText("//*[contains(@text,'Card fees')]","//*[contains(@value,'Card fees')]","xpath","xpath"),
	objCardEyeButton("com.tonik.mobile:id/CARD_EYE_CLICK","(//*[contains(@name,'CARD_EYE_CLICK')])[2]","id","xpath"),
	objCardEyeButtonHide("//*[contains(@resource-id,'CardsCard_Number_Text')]/preceding-sibling::android.view.ViewGroup","(//*[contains(@name,'CardsCard_Number_Text')]/parent::*/parent::*/preceding-sibling::*)[4]","xpath","xpath"),
	objLockCardToggleButton("//*[contains(@text,'Lock card')]/following-sibling::*","//*[contains(@value,'Lock card')]/parent::*/following-sibling::*","xpath","xpath"),
	objResetPinButton("//*[contains(@text,'Reset PIN')]/following-sibling::*","//*[contains(@value,'Reset PIN')]/parent::*/following-sibling::*/child::*","xpath","xpath"),
	objSecurityButton("com.tonik.mobile:id/Security3","com.tonik.mobile:id/Security3","id","id"),
	objSecurityHeader("com.tonik.mobile:id/Main_title_txt","com.tonik.mobile:id/Main_title_txt","id","id"),
	objSecuritySubTitle("com.tonik.mobile:id/Sub_title_txt","com.tonik.mobile:id/Sub_title_txt","id","id"),
	objOnlinePaymentsOnSecurity("//*[contains(@text,'Online payments')]","//*[contains(@value,'Online payments')]","xpath","xpath"),
	objOnlinePaymentsToggleButton("(//*[contains(@text,'Online payments')]/parent::*/child::*)[2]","//*[@value='Online payments']/preceding-sibling::*","xpath","xpath"),
	objOverseasUsage("//*[contains(@text,'Overseas usage')]","//*[contains(@value,'Overseas usage')]","xpath","xpath"),
	objPhysicalPaymentsOnSecurity("//*[contains(@text,'Physical payments')]","//*[contains(@value,'Physical payments')]","xpath","xpath"),
	objPhysicalPaymentsToggleButton("(//*[contains(@text,'Physical payments')]/parent::*/child::*)[2]","//*[@value='Physical payments']/preceding-sibling::*","xpath","xpath"),
	objOnlinePaymentsSubTitle("//*[contains(@text,'You can automatically block this card from online payments and transactions.')]","//*[contains(@value,'You can automatically block this card from online payments and transactions.')]","xpath","xpath"),
	objPhysicalPaymentsSubTitle("//*[contains(@text,'Protect your card when used for swipe, tap and dip POS transactions.')]","//*[contains(@value,'Protect your card when used for swipe, tap and dip POS transactions.')]","xpath","xpath"),
	objAtmWithDrawalsSubTitle("//*[contains(@text,'You can automatically block this card from use at an ATM.')]","//*[contains(@value,'You can automatically block this card from use at an ATM.')]","xpath","xpath"),
	objAtmWithDrawalsToggleButton("(//*[contains(@text,'ATM withdrawals')]/parent::*/child::*)[2]","//*[contains(@value,'ATM withdrawals')]/preceding-sibling::*","xpath","xpath"),
	ObjOverseasUsageSubTitle("//*[contains(@text,'Prevent your card from being used outside the Philippines.')]","//*[contains(@value,'Prevent your card from being used outside the Philippines.')]","xpath","xpath"),
	objOverseasUsageToggleButton("(//*[contains(@text,'Overseas usage')]/parent::*/child::*)[2]","//*[contains(@value,'Overseas usage')]/preceding-sibling::*","xpath","xpath"),
	//card details
	objCardNumber("com.tonik.mobile:id/CardsCard_Number_Text","(//*[contains(@name,'CardsCard_Number_Text')])[2]","id","xpath"),
	objValidThruText("com.tonik.mobile:id/Cards_Valid_Thru_Text","//*[contains(@name,'Cards_Valid_Thru_Text')]","id","xpath"),
	objCVVText("com.tonik.mobile:id/Cards_CVV_Text","com.tonik.mobile:id/Cards_CVV_Text","id","id"),
	objValidThruValue("com.tonik.mobile:id/Physical_Card_Valid_Thru_Text","com.tonik.mobile:id/Physical_Card_Valid_Thru_Text","id","id"),
	objCVVValue("com.tonik.mobile:id/Physical_Card_CVV_Text","com.tonik.mobile:id/Physical_Card_CVV_Text","id","id"),
	objCardName("com.tonik.mobile:id/Card_Name_Text","com.tonik.mobile:id/Card_Name_Text","id","id"),
	objResetPinInput("//android.widget.TextView[@text='●']","","xpath","xpath"),
	objPinNextButton("com.tonik.mobile:id/Six_Digit_PinNext_Button_Click","com.tonik.mobile:id/Six_Digit_PinNext_Button_Click","id","id"),
	objReEnterPinErrorMessage("com.tonik.mobile:id/Re_Enter_PinText_Error_1","com.tonik.mobile:id/Re_Enter_PinText_Error_1","id","id"),
	objPinUpdateButton("com.tonik.mobile:id/Re_Enter_PinNext_Button_Click","com.tonik.mobile:id/Re_Enter_PinNext_Button_Click","id","id"),
	//ATM PIN updated
	objATMPinUpdatedText("//*[@text='ATM PIN updated!']","//*[@value='ATM PIN updated!']","xpath","xpath"),
	objNeverSharePinButton("//*[@text='I will never share my PIN']/parent::*","//*[@value='I will never share my PIN']/parent::*","xpath","xpath"),
	objNeverSharePinDesc("//*[contains(@text,'Never share your PIN with')]","//*[contains(@value,'Never share your PIN with')]","xpath","xpath"),
	objNeverSharePinText("//*[@text='I will never share my PIN']","//*[@value='I will never share my PIN']","xpath","xpath"),
	//Card daily limits
	objATMWithdrawalText("//*[contains(@text,'ATM withdrawal')]","//*[contains(@value,'ATM withdrawal')]","xpath","xpath"),
	objOnlinePaymentsText("//*[contains(@text,'Online and physical payments')]","//*[contains(@value,'Online and physical payments')]","xpath","xpath"),
	objOnlinePaymentsValue("//*[contains(@text,'Online and physical payments')]/following-sibling::android.widget.TextView","//*[contains(@value,'Online and physical payments')]/following-sibling::XCUIElementTypeStaticText","xpath","xpath"),
	objATMWithdrawalValue("//*[contains(@text,'ATM withdrawal')]/following-sibling::android.widget.TextView","//*[contains(@value,'ATM withdrawal')]/following-sibling::XCUIElementTypeStaticText","xpath","xpath"),
	objATMWithdrawalCTA("//*[contains(@text,'ATM withdrawal')]/following-sibling::android.view.ViewGroup","//*[contains(@value,'ATM withdrawal')]/parent::*/following-sibling::XCUIElementTypeOther","xpath","xpath"),
	objOnlinePaymentsCTA("//*[contains(@text,'Online and physical payments')]/following-sibling::android.view.ViewGroup","//*[contains(@value,'Online and physical payments')]/parent::*/following-sibling::*","xpath","xpath"),
	//ATM withdrawal screen
	objPhysicalPaymentValues("//*[contains(@text,'₱')]","//XCUIElementTypeOther/preceding-sibling::XCUIElementTypeStaticText[contains(@value,'₱')]","xpath","xpath"),
	objAmountButton("(//*[@resource-id='Online_Physical_Payment']/following-sibling::*)[1]","(//*[@label='ATM withdrawal'])[6]","xpath","xpath"),
	//debit card fees
	objDebitCardFeesForATMProvider("//*[contains(@text,'ATM provider.')]","//*[contains(@value,'ATM provider.')]","xpath","xpath"),
	objDebitCardFeesForBalanceEnquiry("//*[contains(@text,'balance inquiry')]","//*[contains(@value,'balance inquiry')]","xpath","xpath"),
	objDebitCardFeesForNewCard("//*[contains(@text,'new card or card replacement')]","//*[contains(@value,'new card or card replacement')]","xpath","xpath"),
	objDebitCardFeesForInternationalTrans("//*[contains(@text,'international transactions')]","//*[contains(@value,'international transactions')]","xpath","xpath"),
	objFeesHyperlink("//*[@text='What are these fees?']","//*[@value='What are these fees?']","xpath","xpath"),
	objCardLimitButton("//*[contains(@text,'Card limits')]/following-sibling::*","com.tonik.mobile:id/Card limits4_Button","xpath","id"),
	objCardFeesButton("//*[contains(@text,'Card fees')]/following-sibling::*","com.tonik.mobile:id/Card fees5_Button","xpath","id"),
	objDoNotForgetToTopUp("com.tonik.mobile:id/Card_Topup_Title_Text","com.tonik.mobile:id/Card_Topup_Title_Text","id","id"),
	objMakeSureYouHave("com.tonik.mobile:id/Card_Topup_Msg_Text","com.tonik.mobile:id/Card_Topup_Msg_Text","id","id"),
	objBackIcon("//*[@resource-id='com.tonik.mobile:id/Header_left_click']", "//*[@name='com.tonik.mobile:id/Header_left_click']","xpath","xpath"),
	objBackIconClick("//*[@resource-id='com.tonik.mobile:id/Header_left_click']","(//*[contains(@name,'Header_left_click')])[2]","xpath","xpath"),
	objOkButton("//*[@text='Ok']/parent::*","//*[@value='Ok']/parent::*","xpath","xpath"),
	objProcessingText("//*[@text='Processing...']","//*[@value='Processing...']","xpath","xpath"),
	objTSAAmount("(//*[contains(@text,'ACCOUNT NUMBER')]/following-sibling::*)[1]","(//*[contains(@value,'ACCOUNT NUMBER')]/parent::*/following-sibling::*/child::*)[1]","xpath","xpath"),
	objTonikAmountOnDashboard("(//*[contains(@text,'Your tonik account')]/following-sibling::android.widget.TextView)[1]","(//XCUIElementTypeStaticText[@name=\"Your tonik account\"]/following-sibling::*)[1]","xpath","xpath");
	private String android;
	private String ios;
	private String andPathType;
	private String iosPathType;
	DebitCardPage(String android, String ios, String andPathType, String iosPathType) {
		this.android = android;
		this.ios = ios;
		this.andPathType = andPathType;
		this.iosPathType = iosPathType;
	}
	public static By getByOSType(String osType, DebitCardPage objectName) {
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
}