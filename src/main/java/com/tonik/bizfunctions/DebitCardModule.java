package com.tonik.bizfunctions;
import static com.tonik.utility.DB_Utilites.updateQuery;
import static com.tonik.utility.ExtentReporter.HeaderChildNode;
import static com.tonik.utility.ExtentReporter.extentLoggerPass;
import com.driverInstance.DriverManager;
import com.tonik.pageObject.*;
import com.tonik.utility.Utilities;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;
import static com.tonik.utility.Utilities.*;
import static com.tonik.utility.DB_Utilites.*;
public class DebitCardModule extends BaseClass {
	public DebitCardModule() {
        super();
    }
	String platform = Utilities.getPlatform();
	/**
	 * reusable method to verify content of Debit card tile and perform click
	 * @throws Exception
	 */
	public void verifyContentAndClickOnDebitCardTile() throws Exception {
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardText), "Debit Card");
		containsValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardText)), propertyFileReader("debitCardTitle", "debitCard"), "Debit Card");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objLearnMoreText), "Learn More");
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardTile), "Debit Card Tile");
	}
	/**
	 * reusable method for Cards screen without TSA created
	 * @throws Exception
	 */
	public void cardsScreenForWithoutTSA() throws Exception {
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader), "Cards Header");
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader)), "Cards", "Cards Header", "contains");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardImage), "Debit Card Image");
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objDoNotForgetToTopUp)), propertyFileReader("DonNotForgetToTopUp","debitCard"), "Don't Forget To TopUp", "contains");
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objMakeSureYouHave)), propertyFileReader("MakeSureYouHave","debitCard"), "Make Sure You Have", "contains");
	}
	/**
	 * Method to use convert to double
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
	 * reusable method for Cards Screen
	 * @throws Exception
	 */
	public void cardsScreen() throws Exception {
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader), "Cards Header");
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader)), "Cards", "Cards Header", "contains");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardImage), "Debit Card Image");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objGetPhysicalText), getText(DebitCardPage.getByOSType(platform,DebitCardPage.objGetPhysicalText)));
		assertionValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objGetPhysicalText)),propertyFileReader("GetPhysicalText","debitCard"),"Text");
		if(verifyElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objCardContentText))) {
			verifyElementPresent(DebitCardPage.getByOSType(platform, DebitCardPage.objCardContentText), getTextVal(DebitCardPage.getByOSType(platform, DebitCardPage.objCardContentText), "Cards content Text"));
			containsValidation(getText(DebitCardPage.getByOSType(platform, DebitCardPage.objCardContentText)), propertyFileReader("CardContentText1", "debitCard"), "Text");
			containsValidation(getText(DebitCardPage.getByOSType(platform, DebitCardPage.objCardContentText)), propertyFileReader("CardContentText2", "debitCard"), "Text");
			containsValidation(getText(DebitCardPage.getByOSType(platform, DebitCardPage.objCardContentText)), propertyFileReader("CardContentText3", "debitCard"), "Text");
			containsValidation(getText(DebitCardPage.getByOSType(platform, DebitCardPage.objCardContentText)), propertyFileReader("CardContentText4", "debitCard"), "Text");
		} else {
			if(verifyElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objCardContentTextGroup))) {
				verifyElementPresent(DebitCardPage.getByOSType(platform, DebitCardPage.objCardContentTextGroup), getTextVal(DebitCardPage.getByOSType(platform, DebitCardPage.objCardContentTextGroup), "Cards content Text"));
				containsValidation(getText(DebitCardPage.getByOSType(platform, DebitCardPage.objCardContentTextGroup)), propertyFileReader("CardContentTextGroup1", "debitCard"), "Text");
				containsValidation(getText(DebitCardPage.getByOSType(platform, DebitCardPage.objCardContentTextGroup)), propertyFileReader("CardContentTextGroup2", "debitCard"), "Text");
			}
		}
		verticalSwipeTillElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objConfirmAddressText));
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objBancnetButton), getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objBancnetButton), " :Button"));
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objConfirmAddressText), "Confirm my address");
		assertionValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objConfirmAddressText)),propertyFileReader("ConfirmAddressText","debitCard"),"Text");
	}
	/**
	 * reusable method for card delivered page
	 * @throws Exception
	 */
	public void cardDeliveredScreen() throws Exception {
		waitTime(3000);
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader), getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader), " :Header"));
		assertionValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader)),propertyFileReader("CardDeleveredHeader","debitCard")," :Header");
		assertionValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objSecuritySubTitle)),propertyFileReader("CardDeleveredSubHeader","debitCard"),":Sub Header");
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objZipCodeInput), "Zip Code");
		clearField(DebitCardPage.getByOSType(platform, DebitCardPage.objZipCodeInput), "Zipcode text field");
		type(DebitCardPage.getByOSType(platform,DebitCardPage.objZipCodeInput), propertyFileReader("invalidZipCode", "debitCard"), "Zip Code");
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objApplyZipcodeButton), "Apply zip code");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objInlineErrorMessage), getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objInlineErrorMessage), " inline error message"));
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objZipCodeInput), "Zip Code");
		clearField(DebitCardPage.getByOSType(platform, DebitCardPage.objZipCodeInput), "Zipcode text field");
		type(DebitCardPage.getByOSType(platform,DebitCardPage.objZipCodeInput), propertyFileReader("validZipCode", "debitCard"), "Zip Code");
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objApplyZipcodeButton), "Apply zip code");
		waitForElementToBePresent(DebitCardPage.getByOSType(platform,DebitCardPage.objProvinceInput),10,"Province input");
		containsValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objProvinceInput)), propertyFileReader("province", "debitCard"), " Province");
		waitForElementToBePresent(DebitCardPage.getByOSType(platform,DebitCardPage.objCityInput),10,"City Muncipality");
		containsValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objCityInput)), propertyFileReader("city", "debitCard"), " City/Muncipality");
		verticalSwipeTillElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objBarangayInput));
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objBarangayInput), " Barangay Input");
		waitTime(2000);
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader), getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader), " :Header"));
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objNorthside), "Post proper Northside");
		containsValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objBarangayInput)), propertyFileReader("postProperNorthside", "debitCard"), "Post proper Northside");
		verticalSwipeTillElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objMobileNoText));
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objCardAddressInput), "Address field");
		clearField(DebitCardPage.getByOSType(platform, DebitCardPage.objCardAddressInput), "Address text field");
		type(DebitCardPage.getByOSType(platform,DebitCardPage.objCardAddressInput), propertyFileReader("userAddressDetails", "debitCard"), "Address field");
		if(platform.equalsIgnoreCase("IOS")) {
			tapOnScreen(300,300,"screen");
		}else {
			hideKeyboard();
		}
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objMobileNoText), "Mobile Number field");
		assertionValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objMobileNoText)),propertyFileReader("MobileNumber","debitCard"),"Mobile Number");
		containsValidation(getAttributValue("enabled", DebitCardPage.getByOSType(platform,DebitCardPage.objMobileNoFieldDisabled)), "false", " Disabled Attribute value");
		swipe("UP", 1);
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objAlternativeMobileNoInput), "Alternative mobile number");
		clearField(DebitCardPage.getByOSType(platform, DebitCardPage.objAlternativeMobileNoInput), "Alternative Mobile text field");
		type(DebitCardPage.getByOSType(platform,DebitCardPage.objAlternativeMobileNoInput), propertyFileReader("alternateMobileNumber", "debitCard"), "Alternative mobile number");
		if(platform.equalsIgnoreCase("IOS")) {
			tapOnScreen(300,300,"screen");
		}else {
			hideKeyboard();
		}
		click(DebitCardPage.getByOSType(platform,DebitCardPage.objDeliveryInstructionsInput), "Delivery Instruction");
		clearField(DebitCardPage.getByOSType(platform, DebitCardPage.objDeliveryInstructionsInput), "Delivery Instructions text field");
		type(DebitCardPage.getByOSType(platform,DebitCardPage.objDeliveryInstructionsInput), propertyFileReader("deliveryInstructions", "debitCard"), "Delivery Instruction");
		if(platform.equalsIgnoreCase("IOS")) {
			tapOnScreen(300,300,"screen");
		}else {
			hideKeyboard();
		}
		containsValidation(getAttributValue("enabled", DebitCardPage.getByOSType(platform,DebitCardPage.objCardNextButtonEnabled)), "true", " enabled Attribute value");
	}
	/**
	 * reusable method for Snapghetti OS screen
	 * @throws Exception
	 */
	public void spaghetti_osScreen() throws Exception {
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objSpaghettiText), getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objSpaghettiText), " :Title"));
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objSpaghettiDesc), getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objSpaghettiDesc), " :Description"));
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objBackToDashBoard), getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objBackToDashBoard), " :Button"));
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objTopUpAccount), "Top-up account");
	}
	/**
	 * reusable method for Topup
	 * @throws Exception
	 */
	public void topUp(String amount) throws Exception {
	    click(DebitCardPage.getByOSType(platform,DebitCardPage.objOnline),getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objOnline),": Top-up option"));
	    verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objGCashOption), getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objGCashOption), ": Button"));
	    click(DebitCardPage.getByOSType(platform,DebitCardPage.objAmountField),getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objAmountField),": Button"));
	    type(DebitCardPage.getByOSType(platform,DebitCardPage.objAmountField), amount, "Top Up Amount");
	    if(platform.equalsIgnoreCase("Android")){
			hideKeyboard();
		}else {
			tapOnScreen(300,150,"screen");
		}
	    verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objNextButton), "Next Button");
		waitForElementToBePresent(DebitCardPage.getByOSType(platform,DebitCardPage.objProceedToPay), 60,"Vroom Title");
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objProceedToPay), "Proceed to pay");
		if(verifyElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objProcessingText))){
			click(DebitCardPage.getByOSType(platform,DebitCardPage.objOkButton),"Ok button");
		}
		if(verifyElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objBackToDashBoard))){
			click(DebitCardPage.getByOSType(platform,DebitCardPage.objBackToDashBoard),"Back Dashboard");
		}
	}
	/**
	 * reusable method of Card screen after order placed
	 * @throws Exception
	 */
	public void cardsScreenAfterOrderPlaced() throws Exception {
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader), "Cards Header");
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader)), "Cards", "Cards Header", "contains");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardImage), "Debit Card Image");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objOnTheWayText), getText(DebitCardPage.getByOSType(platform,DebitCardPage.objOnTheWayText)));
		assertionValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objOnTheWayText)),propertyFileReader("OnTheWayToYouText","debitCard"),"Text");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objCardTitleText), getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objCardTitleText), "Cards content Text"));
		assertionValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objCardTitleText)),propertyFileReader("CardTitleText","debitCard"),"Title");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objCardMsgText), getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objCardMsgText), "Cards content message"));
		assertionValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objCardMsgText)),propertyFileReader("CardMessageText","debitCard"),"Message");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objCardDescText), getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objCardDescText), "Cards content description"));
		assertionValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objCardDescText)),propertyFileReader("CardDescText","debitCard"),"Descriptions");
	}
	/**
	 * reusable method to verify content on activated card
	 * @throws Exception
	 */
	public void verifyContentAndClickOnActivatedDebitCardTile() throws Exception {
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardText), "Debit Card");
		containsValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardText)), propertyFileReader("debitCardTitle", "debitCard"), "Debit Card");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objTwoDigitOnDebitCard), getText(DebitCardPage.getByOSType(platform,DebitCardPage.objTwoDigitOnDebitCard)));
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objTwoDigitOnDebitCard)), propertyFileReader("lastTwoDigitOnDebitCard", "debitCard"), "Two digit on debit card", "contains");
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardTile), "Debit Card Tile");
	}
	/**
	 * reusable method to verify activated card screen
	 * @throws Exception
	 */
	public void activatedCardsScreen() throws Exception {
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader), "Cards Header");
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader)), "Cards", "Cards Header", "contains");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objActiveDebitCardImage), "Debit Card Image");
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objLockPinText)), propertyFileReader("lockCard", "debitCard"), "value", "contains");
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objRestPinText)), propertyFileReader("resetPin", "debitCard"), "value", "contains");
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objSecurityText)), propertyFileReader("security", "debitCard"), "value", "contains");
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objCardLimitsText)), propertyFileReader("cardLimits", "debitCard"), "value", "contains");
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objCardFeesText)), propertyFileReader("cardFees", "debitCard"), "value", "contains");
	}
	/**
	 * reusable method to verify Debit card details
	 * @throws Exception
	 */
	public void verifyDebitCardDetails() throws Exception {
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objCardNumber), getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objCardNumber), ":Unmasked number on debit card"));
		assertionValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objCardNumber)),propertyFileReader("CardNumber","debitCard"),"Card number");
		int cardNumber = getText(DebitCardPage.getByOSType(platform,DebitCardPage.objCardNumber)).length()-3;
		if(cardNumber==16){
			logger.info("16 digit card number is displayed");
			extentLoggerPass("Card numbers", "16 digit card number is displayed");
		}else{
			logger.info("16 digit card number is not displayed");
			extentLoggerFail("", "16 digit card number is not displayed");
		}
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objValidThruText)), propertyFileReader("validThru", "debitCard"), "Text", "equals");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objValidThruValue), getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objValidThruValue), ":Valid Thru date on debit card"));
		assertionValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objValidThruValue)),propertyFileReader("ValidThruDate","debitCard"),"Valid Thru");
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objCVVText)), propertyFileReader("CVV", "debitCard"), "Text", "equals");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objCVVValue), getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objCVVValue), ":CVV number on debit card"));
		assertionValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objCVVValue)),propertyFileReader("CvvNumber","debitCard"),"CVV");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objCardName), getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objCardName), ":name on debit card"));
	}
	/**
	 * reusable method to verify content on locked debit card
	 * @throws Exception
	 */
	public void verifyContentAndClickOnLockedDebitCardTile() throws Exception {
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardText), "Debit Card");
		containsValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardText)), propertyFileReader("debitCardTitle", "debitCard"), "Debit Card");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objLockedOnDebitCardTile), getText(DebitCardPage.getByOSType(platform,DebitCardPage.objTwoDigitOnDebitCard)));
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objLockedOnDebitCardTile)), "Locked", "text", "equals");
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardTile), "Debit Card Tile");
	}
	/**
	 * reusable method for ATM Pin updated page
	 * @throws Exception
	 */
	public void atmPinUpdatedScreen() throws Exception {
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objATMPinUpdatedText), getText(DebitCardPage.getByOSType(platform,DebitCardPage.objATMPinUpdatedText)));
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objNeverSharePinDesc), getText(DebitCardPage.getByOSType(platform,DebitCardPage.objNeverSharePinDesc)));
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objNeverSharePinText), getText(DebitCardPage.getByOSType(platform,DebitCardPage.objNeverSharePinText)));
	}
	/**
	 * reusable method to verify daily card limit page
	 * @throws Exception
	 */
	public void cardDailyLimitsScreen() throws Exception {
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader), getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader), ":Header"));
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader)), propertyFileReader("cardDailyLimitsHeader", "debitCard"), "Header", "equals");
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objATMWithdrawalText)), propertyFileReader("ATMWithdrawal", "debitCard"), "Title", "contains");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objATMWithdrawalValue), getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objATMWithdrawalValue), "Value"));
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objOnlinePaymentsText)), propertyFileReader("onlineAndPhysicalPayments", "debitCard"), "Title", "contains");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objOnlinePaymentsValue), getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objOnlinePaymentsValue), "Value"));
	}
	/**
	 * reusable method to verify ATM Withdrawal amount page
	 * @throws Exception
	 */
	public void atmWithdrawalScreen() throws Exception {
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objCardLimitButton), "Card Limits");
		waitForElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objATMWithdrawalCTA), 10);
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objATMWithdrawalCTA), "ATM Withdrawal CTA");
		waitForElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objAmountButton), 10);
		waitTime(2000);//added because of staleElementReferenceException
		List<WebElement> allAmount = findElements(DebitCardPage.getByOSType(platform,DebitCardPage.objPhysicalPaymentValues));
		for(int list=0; list<allAmount.size(); list++) {
			waitTime(2000);//added because of staleElementReferenceException
			waitForElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objAmountButton), 10);
			allAmount = findElements(DebitCardPage.getByOSType(platform,DebitCardPage.objPhysicalPaymentValues));
			String selectAmount = allAmount.get(list).getText();
			clickByElement(allAmount.get(list), selectAmount);
			waitForElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objATMWithdrawalCTA), 10);
			valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objATMWithdrawalValue)).trim(), selectAmount, "ATM amount", "equals");
			verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objBackIcon), "Back Icon");
			verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objCardLimitButton), "Card Limits");
			waitTime(3000);//added because of staleElementReferenceException
			verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objATMWithdrawalCTA), "ATM Withdrawal CTA");
		}
	}
	/**
	 * reusable method to verify online physical payment amount page
	 * @throws Exception
	 */
	public void onlineAndPhysicalPaymentsScreen() throws Exception {
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objCardLimitButton), "Card Limits");
		waitTime(2000);//added because of staleElementReferenceException
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objOnlinePaymentsCTA), "Online and physical payments CTA");
		waitForElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objAmountButton), 10);
		waitTime(3000);//added because of staleElementReferenceException
		List<WebElement> allAmount = findElements(DebitCardPage.getByOSType(platform,DebitCardPage.objPhysicalPaymentValues));
		for(int i=0; i<allAmount.size(); i++) {
			waitTime(2000);//added because of staleElementReferenceException
			allAmount = findElements(DebitCardPage.getByOSType(platform,DebitCardPage.objPhysicalPaymentValues));
			String selectAmount = allAmount.get(i).getText();
			clickByElement(allAmount.get(i), selectAmount);
			waitForElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objOnlinePaymentsCTA), 10);
			valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objOnlinePaymentsValue)).trim(), selectAmount, "Online and physical payments", "equals");
			verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objBackIcon), "Back Icon");
			verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objCardLimitButton), "Card Limits");
			waitTime(3000);
			verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objOnlinePaymentsCTA), "Online and physical payments CTA");
		}
	}
	/**
	 * reusable method for Debit card fees page
	 * @throws Exception
	 */
	public void debitCardFeesScreen() throws Exception {
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader), getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader), ":Header"));
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader)), propertyFileReader("debitCardFeesHeader", "debitCard"), "Header", "equals");
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardFeesForATMProvider)), propertyFileReader("debitCardFeesForATMProvider", "debitCard"), "Text", "equals");
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardFeesForBalanceEnquiry)), propertyFileReader("DebitCardFeesForBalanceEnquiry", "debitCard"), "Text", "equals");
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardFeesForNewCard)), propertyFileReader("DebitCardFeesForNewCard", "debitCard"), "Text", "equals");
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardFeesForInternationalTrans)), propertyFileReader("DebitCardFeesForInternationalTrans", "debitCard"), "Text", "equals");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objFeesHyperlink), getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objFeesHyperlink), "Hyperlink"));
	}
	/**
	 * method to Verify the user can access debit card tile if TSA is Not created
	 * @throws Exception
	 */
	public void verifyTheUserCanAccessDebitCardTileIfTSAIsNotCreated_TDB_PC_001() throws Exception {
		HeaderChildNode("TDB_PC_001, DebitCard -Verify the user can access debit card tile if TSA is Not created - TDB_PC_002");
		tonikLogin(propertyFileReader("password", "Login"));
		verifyContentAndClickOnDebitCardTile();
		cardsScreenForWithoutTSA();
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objTopUpAccountButton),"TopUp Account Button");
		topUp(propertyFileReader("amount", "debitCard"));
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardText), getText(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardText)));
		logger.info("TDB_PC_001, DebitCard - Verify the user can access debit card tile if TSA is Not created ");
		extentLoggerPass("TDB_PC_001", "TDB_PC_001, DebitCard - Verify the user can access debit card tile if TSA is Not created is passed");
	}
	/**
	 * method to Verify the user can access debit card tile if TSA is created
	 * @throws Exception
	 */
	public void verifyUserCanAccessDebitCardTile_TDB_PC_002() throws Exception {
		HeaderChildNode("TDB_PC_002, DebitCard -Verify the user can access debit card tile if TSA is created - TDB_PC_002");
		tonikLogin(propertyFileReader("password", "Login"));
		verifyContentAndClickOnDebitCardTile();
		cardsScreen();
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objBancnetButton), "Bancnet debit card");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardText), getText(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardText)));
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardTile), "Debit Card Tile");
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objBackIcon), "Back Icon");
		waitForElementToBePresent(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardText),10,"Debit Card tile");
		if(verifyElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardText))) {
			logger.info("TDB_PC_002, DebitCard - verify User Can Access Debit Card Tile_TDB_PC_002");
			extentLoggerPass("TDB_PC_002", "TDB_PC_002, DebitCard - verify User Can Access Debit CardTile_TDB_PC_002 is passed");
		}
	}
	/**
	 * method to Verify if user can confirm the address while ordering for a debit card
	 * @throws Exception
	 */
	public void verifyUserCanConfirmAddressWhileOrderingDebitCard_TDB_PC_003() throws Exception {
		HeaderChildNode("TDB_PC_003, DebitCard -Verify if user can confirm the address while ordering for a debit card - TDB_PC_003");
		tonikLogin(propertyFileReader("password", "Login"));
		verifyContentAndClickOnDebitCardTile();
		verticalSwipeTillElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objConfirmAddressButton));
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objConfirmAddressButton), "Confirm my address");
		cardDeliveredScreen();
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objBackIcon), "Back Icon");
		waitForElementToBePresent(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader),10,"Header: card");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader), getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader), " :Header"));
		logger.info("TDB_PC_003, DebitCard - verifyUserCanConfirmAddressWhileOrderingDebitCard_TDB_PC_003");
        extentLoggerPass("TDB_PC_003", "TDB_PC_003, DebitCard - verifyUserCanConfirmAddressWhileOrderingDebitCard_TDB_PC_003 is passed");
	}
	/**
	 * method to Verify if user can order a debit card
	 * @throws Exception
	 */
	public void verifyUserCanOrderDebitCard_TDB_PC_004(String cardType) throws Exception {
		HeaderChildNode("TDB_PC_004, DebitCard -Verify if user can order a debit card - TDB_PC_004");
		tonikLogin(propertyFileReader("password", "Login"));
		String currentTSAAmount;
		if(verifyElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objTSAAmount))) {
			currentTSAAmount = getText(DebitCardPage.getByOSType(platform, DebitCardPage.objTSAAmount));
		} else {
			currentTSAAmount = getText(DebitCardPage.getByOSType(platform, DebitCardPage.objTonikAmountOnDashboard));
		}
		currentTSAAmount = currentTSAAmount.replace("₱","").replace(",","");
		verifyContentAndClickOnDebitCardTile();
		verticalSwipeTillElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objConfirmAddressButton));
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objConfirmAddressButton), "Confirm my address");
		cardDeliveredScreen();
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objCardNextButton), "Next button");
		//Condition if test case is "Default" for regular card, and "Free" for free debit card
		if (cardType.equals("Default")) {
			//method spaghetti_osScreen will be triggered if TSAAmount is less than debit card fee
			spaghetti_osScreen();
			verifyElementPresentAndClick(DebitCardPage.getByOSType(platform, DebitCardPage.objBackToDashBoard), "Back To Dashboard");
			verifyContentAndClickOnDebitCardTile();
			verticalSwipeTillElementDisplayed(DebitCardPage.getByOSType(platform, DebitCardPage.objConfirmAddressButton));
			verifyElementPresentAndClick(DebitCardPage.getByOSType(platform, DebitCardPage.objConfirmAddressButton), "Confirm my address");
			cardDeliveredScreen();
			verifyElementPresentAndClick(DebitCardPage.getByOSType(platform, DebitCardPage.objCardNextButton), "Next button");
			spaghetti_osScreen();
			verifyElementPresentAndClick(DebitCardPage.getByOSType(platform, DebitCardPage.objTopUpAccountButton), "Top up Account button");
			topUp(propertyFileReader("topUpAmount", "debitCard"));
			if(verifyElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objTSAAmount))) {
				currentTSAAmount = getText(DebitCardPage.getByOSType(platform, DebitCardPage.objTSAAmount));
			} else {
				currentTSAAmount = getText(DebitCardPage.getByOSType(platform, DebitCardPage.objTonikAmountOnDashboard));
			}
			currentTSAAmount = currentTSAAmount.replace("₱","").replace(",","");
			verifyContentAndClickOnDebitCardTile();
			verticalSwipeTillElementDisplayed(DebitCardPage.getByOSType(platform, DebitCardPage.objConfirmAddressButton));
			verifyElementPresentAndClick(DebitCardPage.getByOSType(platform, DebitCardPage.objConfirmAddressButton), "Confirm my address");
			cardDeliveredScreen();
			verifyElementPresentAndClick(DebitCardPage.getByOSType(platform, DebitCardPage.objCardNextButton), "Next button");
		}
		waitForElementToBePresent(DebitCardPage.getByOSType(platform,DebitCardPage.objVroombabyTitle), 60,"Vroom Title");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objVroombabyTitle), getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objVroombabyTitle), "Title"));
		assertionValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objVroombabyTitle)),propertyFileReader("VroomVroomBabyText","debitCard"),"Tile");
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objBackToDashboardButton), "Back to dashboard");
		String afterTopupTSAAmount;
		if(verifyElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objTSAAmount))) {
			afterTopupTSAAmount = getText(DebitCardPage.getByOSType(platform, DebitCardPage.objTSAAmount));
		} else {
			afterTopupTSAAmount = getText(DebitCardPage.getByOSType(platform, DebitCardPage.objTonikAmountOnDashboard));
		}
		afterTopupTSAAmount = afterTopupTSAAmount.replace("₱","").replace(",","");
		double expectedTSAAmount;
		if (cardType.equals("Default")) {
			expectedTSAAmount = Double.parseDouble(currentTSAAmount)-Double.parseDouble(propertyFileReader("topUpAmount", "debitCard"));
		} else {
			expectedTSAAmount = Double.parseDouble(currentTSAAmount);
		}
		containsValidation(afterTopupTSAAmount,String.valueOf(expectedTSAAmount),"TSA amount on Dashboard");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardText), getText(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardText)));
		containsValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardText)), propertyFileReader("debitCardTitle", "debitCard"), "Debit Card");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objOnTheWay), getText(DebitCardPage.getByOSType(platform,DebitCardPage.objOnTheWay)));
		assertionValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objOnTheWay)),propertyFileReader("OnTheWayText","debitCard"),"Text");
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardTile), "Debit Card Tile");
		cardsScreenAfterOrderPlaced();
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objBackIcon), "Back Icon");
		logger.info("TDB_PC_004, DebitCard - verifyUserCanOrderDebitCard_TDB_PC_004");
        extentLoggerPass("TDB_PC_004", "TDB_PC_004, DebitCard - verifyUserCanOrderDebitCard_TDB_PC_004 is passed");
	}
	/**
	 * method to verify User Can Hide Un-hide Details Of Debit Card
	 * @throws Exception
	 */
	public void verifyUserCanHideUnhideDetailsOfDebitCard_TDB_PC_006() throws Exception {
		HeaderChildNode("TDB_PC_006, DebitCard -verify User Can Hide Un-hide Details Of Debit Card - TDB_PC_006");
		tonikLogin(propertyFileReader("password", "Login"));
		verifyContentAndClickOnActivatedDebitCardTile();
		activatedCardsScreen();
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objCardEyeButton), "Card Eye");
		verifyDebitCardDetails();
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objBackIcon), "Back Icon");
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardTile), "Debit Card Tile");
		containsValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objCardNumber)), propertyFileReader("maskedDebitCard", "debitCard"), "masked Value");
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objCardEyeButton), "Card Eye");
		verifyDebitCardDetails();
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objCardEyeButtonHide), "Card Eye");
		verifyElementNotPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objValidThruText), 10);
		containsValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objCardNumber)), propertyFileReader("maskedDebitCard", "debitCard"), "masked Value");
		logger.info("TDB_PC_006, DebitCard - verify User Can Hide Un-hide Details Of Debit Card_TDB_PC_006");
		extentLoggerPass("TDB_PC_006", "TDB_PC_006, DebitCard - verifyUserCanHideUnhideDetailsOfDebitCard_TDB_PC_006 is passed");
	}
	/**
	 * method to Verify if user can lock/unlock the debit card
	 * @throws Exception
	 */
	public void verifyUserCanLockUnlockDebitCard_TDB_PC_007() throws Exception {
		HeaderChildNode("TDB_PC_007, DebitCard -Verify if user can lock/unlock the debit card - TDB_PC_007");
		tonikLogin(propertyFileReader("password", "Login"));
		verifyContentAndClickOnActivatedDebitCardTile();
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objLockCardToggleButton), "Lock card Toggle Button");
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objBackIcon), "Back Icon");
		verifyContentAndClickOnLockedDebitCardTile();
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objLockCardToggleButton), "Lock card Toggle Button");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objCardEyeButtonHide), "Card Eye");
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objBackIcon), "Back Icon");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardText), "Debit Card");
		if(verifyElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objTwoDigitOnDebitCard))) {
			logger.info("TDB_PC_007, DebitCard - verifyUserCanLockUnlockDebitCard_TDB_PC_007");
			extentLoggerPass("TDB_PC_007", "TDB_PC_007, DebitCard - verifyUserCanLockUnlockDebitCard_TDB_PC_007 is passed");
		}
	}
	/**
	 * method to Verify if user can reset the pin of the debit card
	 * @throws Exception
	 */
	public void verifyUserCanResetPinOfDebitCard_TDB_PC_008() throws Exception {
		HeaderChildNode("TDB_PC_008, DebitCard -Verify if user can reset the pin of the debit card - TDB_PC_008");
		tonikLogin(propertyFileReader("password", "Login"));
		verifyContentAndClickOnActivatedDebitCardTile();
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objResetPinButton), "Reset pin button");
		if(waitForElementToBePresent(LoginPage.getByOSType(platform,LoginPage.objEditPassword), 60,"Password edit field")) {
			String camPermHeaderTxt = getText(LoginPage.getByOSType(platform,LoginPage.objEditPassword));
			Assert.assertEquals(camPermHeaderTxt, "Password");
			click(LoginPage.getByOSType(platform,LoginPage.objEditPassword), "Password field");
			type(LoginPage.getByOSType(platform,LoginPage.objEditPassword), propertyFileReader("password", "Login"), "Enter secret code");
			verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objLoginBtn),getTextVal(LoginPage.getByOSType(platform,LoginPage.objLoginBtn),"Button"));
		}
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objResetPinInput), "Input field");
		//adbInputText(111111); is not functioning, replaced with actionSendKeys
		actionSendKeys(DebitCardPage.getByOSType(platform,DebitCardPage.objResetPinInput),"111111","First PIN");
		hideKeyboard();
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objPinNextButton), "Next button");
		waitTime(3000);
		waitForElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader), 10);
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader), getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader), ":Header"));
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objResetPinInput), "Input field");
		actionSendKeys(DebitCardPage.getByOSType(platform,DebitCardPage.objResetPinInput),"123456","Second PIN");
		hideKeyboard();
		waitForElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objReEnterPinErrorMessage), 10);
		waitTime(3000);
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objReEnterPinErrorMessage), getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objReEnterPinErrorMessage), "inline error message"));
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objResetPinInput), "Input field");
		actionSendKeys(DebitCardPage.getByOSType(platform,DebitCardPage.objResetPinInput),"111111","Matching PIN");
		hideKeyboard();
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objPinUpdateButton), "Update button");
		atmPinUpdatedScreen();
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objNeverSharePinButton), "Never share your pin");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader), "Cards Header");
		logger.info("TDB_PC_008, DebitCard - verifyUserCanResetPinOfDebitCard_TDB_PC_008");
		extentLoggerPass("TDB_PC_008", "TDB_PC_008, DebitCard - verifyUserCanResetPinOfDebitCard_TDB_PC_008 is passed");
	}
	/**
	 * method to Verify if user can Access The Security Features Of The the debit card
	 * @throws Exception
	 */
	public void verifyUserCanAccessTheSecurityFeaturesOfTheDebitCard_TDB_PC_009() throws Exception {
        HeaderChildNode("TDB_PC_009, DebitCard -Verify if user can Access The Security Features Of The the debit card - TDB_PC_009 ");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyContentAndClickOnActivatedDebitCardTile();
        verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objSecurityButton), "Security button");
		waitTime(3000);
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objSecurityHeader)), propertyFileReader("SecurityHeader", "debitCard"), "Security Header", "contains");
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objSecuritySubTitle)), propertyFileReader("SecurityHeaderSubTitle", "debitCard"), "Security SubTitle", "contains");
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objOnlinePaymentsOnSecurity)), propertyFileReader("OnlinePayments", "debitCard"), "Online Payments", "contains");
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objOnlinePaymentsSubTitle)), propertyFileReader("OnlinePaymentsSubTitle", "debitCard"), "Online Payments SubTitle", "contains");
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objOnlinePaymentsToggleButton), "Online payments Toggle Button");
		waitTime(5000);//after click on toggle button loading is happening for that wait given
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objPhysicalPaymentsOnSecurity)), propertyFileReader("PhysicalPayments", "debitCard"), "Physical Payments", "contains");
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objPhysicalPaymentsSubTitle)), propertyFileReader("PhysicalPaymentsSubtitle", "debitCard"), "Physical Payments Subtitle", "contains");
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objPhysicalPaymentsToggleButton), "Physical Payments Toggle Button");
		waitTime(5000);//after click on toggle button loading is happening for that wait given
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objATMWithdrawalText)), propertyFileReader("ATMWithdrawal", "debitCard"), "ATM With Drawal", "contains");
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objAtmWithDrawalsSubTitle)), propertyFileReader("ATMWithdrawalSubTitle", "debitCard"), "ATM With drawal SubTitle", "contains");
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objAtmWithDrawalsToggleButton), "ATM With drawal Toggle Button");
		waitTime(5000);//after click on toggle button loading is happening for that wait given
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.objOverseasUsage)), propertyFileReader("OverseasUsage", "debitCard"), "Overseas Usage", "contains");
		valueValidation(getText(DebitCardPage.getByOSType(platform,DebitCardPage.ObjOverseasUsageSubTitle)), propertyFileReader("OverseasUsageSubTitle", "debitCard"), "Overseas Usage SubTitle", "contains");
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objOverseasUsageToggleButton), "Overseas Usage Toggle Button");
		waitTime(5000);//after click on toggle button loading is happening for that wait given
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objBackIcon), "Back Icon");
		if(verifyElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader))){
			logger.info("TDB_PC_009, DebitCard - Verify if user can Access The Security Features Of The the debit card_TDB_PC_009");
			extentLoggerPass("TDB_PC_009", "TDB_PC_009, DebitCard - Verify if user can Access The Security Features Of The the debit card_TDB_PC_009 is passed");
		}
	}
	/**
	 * method to Verify if user can change the debit card's ATM withdrawal limit
	 * @throws Exception
	 */
	public void verifyUserCanChangeDebitCardATMWithdrawalLimit_TDB_PC_010() throws Exception {
		HeaderChildNode("TDB_PC_010, DebitCard -Verify if user can change the debit card's ATM withdrawal limit - TDB_PC_010");
		tonikLogin(propertyFileReader("password", "Login"));
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardTile), "Debit Card Tile");
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objCardLimitButton), "Card Limits");
		verifyElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objATMWithdrawalText));
		cardDailyLimitsScreen();
		click(DebitCardPage.getByOSType(platform,DebitCardPage.objBackIcon), "Back Icon");
		atmWithdrawalScreen();
		waitTime(2000);//added because of staleElementReferenceException
		click(DebitCardPage.getByOSType(platform,DebitCardPage.objBackIconClick), "Back Icon");
		waitForElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objATMWithdrawalCTA), 10);
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objBackIcon), "Back Icon");
		waitForElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objCardLimitsText), 10);
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader), getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader), ":Header"));
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objBackIcon), "Back Icon");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objTwoDigitOnDebitCard), getText(DebitCardPage.getByOSType(platform,DebitCardPage.objTwoDigitOnDebitCard)));
		logger.info("TDB_PC_010, DebitCard - verifyUserCanChangeDebitCardATMWithdrawalLimit_TDB_PC_010");
		extentLoggerPass("TDB_PC_010", "TDB_PC_010, DebitCard - verifyUserCanChangeDebitCardATMWithdrawalLimit_TDB_PC_010 is passed");
	}
	/**
	 * method to Verify if user can change the debit card's Online and physical payments limit
	 * @throws Exception
	 */
	public void verifyUserCanChangeDebitCardOnlinePhysicalPaymentsLimit_TDB_PC_011() throws Exception {
		HeaderChildNode("TDB_PC_011, DebitCard -Verify if user can change the debit card's Online and physical payments limit - TDB_PC_011");
		tonikLogin(propertyFileReader("password", "Login"));
		verifyContentAndClickOnActivatedDebitCardTile();
		onlineAndPhysicalPaymentsScreen();
		waitTime(2000);//added because of staleElementReferenceException
		click(DebitCardPage.getByOSType(platform,DebitCardPage.objBackIconClick), "Back Icon");
		waitForElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objOnlinePaymentsCTA), 10);
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objBackIcon), "Back Icon");
		waitForElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objCardLimitsText), 10);
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader), getTextVal(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader), ":Header"));
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objBackIcon), "Back Icon");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objTwoDigitOnDebitCard), getText(DebitCardPage.getByOSType(platform,DebitCardPage.objTwoDigitOnDebitCard)));
		logger.info("TDB_PC_011, DebitCard - verifyUserCanChangeDebitCardOnlinePhysicalPaymentsLimit_TDB_PC_011");
		extentLoggerPass("TDB_PC_011", "TDB_PC_011, DebitCard - verifyUserCanChangeDebitCardOnlinePhysicalPaymentsLimit_TDB_PC_011 is passed");
	}
	/**
	 * method to Verify if user can see the debit card fees
	 * @throws Exception
	 */
	public void verifyUserCanSeeDebitCardFees_TDB_PC_012() throws Exception {
		HeaderChildNode("TDB_PC_012, DebitCard -Verify if user can see the debit card fees - TDB_PC_012");
		tonikLogin(propertyFileReader("password", "Login"));
		verifyContentAndClickOnActivatedDebitCardTile();
		click(DebitCardPage.getByOSType(platform,DebitCardPage.objCardFeesButton), "Card fees");
		verifyElementDisplayed(DebitCardPage.getByOSType(platform,DebitCardPage.objDebitCardFeesForBalanceEnquiry));
		debitCardFeesScreen();
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objFeesHyperlink), "What are these fees?");
		if(platform.equalsIgnoreCase("Android")) {
			Back(1);
			waitTime(3000);
		}else{
			DriverManager.getAppiumDriver().activateApp("com.mobile.tonik");
		}
		verifyElementPresentAndClick(DebitCardPage.getByOSType(platform,DebitCardPage.objBackIcon), "Back Icon");
		verifyElementPresent(DebitCardPage.getByOSType(platform,DebitCardPage.objHeader), "Cards Header");
		logger.info("TDB_PC_012, DebitCard - verifyUserCanSeeDebitCardFees_TDB_PC_012");
		extentLoggerPass("TDB_PC_012", "TDB_PC_012, DebitCard - verifyUserCanSeeDebitCardFees_TDB_PC_012 is passed");
	}
	/**
	 * method to Verify if user can receive a free debit card
	 * @throws Exception
	 */
	public void verifyUserReceiveFreeDebitCard_TDB_PC_013() throws Exception {
		HeaderChildNode("TDB_PC_013, DebitCard - Verify if user can receive a free debit card - TDB_PC_013");
		tonikLogin(propertyFileReader("password", "Login"));
		String currentAmount = getText(StashPage.getByOSType(platform, StashPage.objTonikAmountOnDashboard)).replaceAll("[,₱]", "");
		double stashedAmountValue = this.getAvailableBalanceInteger(currentAmount);
		verifyContentAndClickOnDebitCardTile();
		if (verifyElementDisplayed(DebitCardPage.getByOSType(platform, DebitCardPage.objCardContentTextGroup))) {
			verifyElementPresent(DebitCardPage.getByOSType(platform, DebitCardPage.objCardContentTextGroup), getTextVal(DebitCardPage.getByOSType(platform, DebitCardPage.objCardContentTextGroup), "Cards content Text"));
			containsValidation(getText(DebitCardPage.getByOSType(platform, DebitCardPage.objCardContentTextGroup)), propertyFileReader("CardContentTextGroup1", "debitCard"), "Text");
			containsValidation(getText(DebitCardPage.getByOSType(platform, DebitCardPage.objCardContentTextGroup)), propertyFileReader("CardContentTextGroup2", "debitCard"), "Text");
		}
		String mobile_no = propertyFileReader("MobileNumberCountry", "debitCard");
		String cust_id = selectQuery("selectQuerycustomer", "SELECT cust_id FROM customer.tdbk_customer_mtb where mobile_no = '" + mobile_no + "';");
		String user_id = selectQuery("selectQuerycustomer", "SELECT user_id FROM customer.tdbk_customer_mtb where mobile_no = '" + mobile_no + "';");
		String insertStatement = "INSERT INTO card.tdbk_free_debit_card_customer_stg (cust_id, user_id, mobile_no, free_debit_card_availed, created_dt, created_by) VALUES ( '" + cust_id + "', '" + user_id + "', '" + mobile_no + "', 'N', sysdate(), 'FreeDebitCardUploadProcessor scheduler');";
		insertQuery(insertStatement);
		click(LoginPage.getByOSType(platform, LoginPage.objBackIcon), "Back Button");
		verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon), "Profile page Icon");
		if(platform.equalsIgnoreCase("Android")) {
			closeAndroidTonikApp();
			relaunchApp("android");
		}else{
			closeAndroidTonikApp();
			relaunchApp("ios");
		}
		tonikLogin(propertyFileReader("password","Login"));
		double stashedUpdatedAmountValue=0;
		if(verifyElementDisplayed(StashPage.getByOSType(platform,StashPage.objTonikAmountOnDashboard))) {
			String updatedAmount = getText(StashPage.getByOSType(platform, StashPage.objTonikAmountOnDashboard)).replaceAll("[,₱]", "");
			stashedUpdatedAmountValue = this.getAvailableBalanceInteger(updatedAmount);
		}
		containsValidation(String.valueOf(stashedAmountValue), String.valueOf(stashedUpdatedAmountValue),"Dashboard Amount");
		verifyContentAndClickOnDebitCardTile();
		if (verifyElementDisplayed(DebitCardPage.getByOSType(platform, DebitCardPage.objCardContentTextGroup))) {
			verifyElementPresent(DebitCardPage.getByOSType(platform, DebitCardPage.objCardContentTextGroup), getTextVal(DebitCardPage.getByOSType(platform, DebitCardPage.objCardContentTextGroup), "Cards content Text"));
			containsValidation(getText(DebitCardPage.getByOSType(platform, DebitCardPage.objCardContentTextGroup)), propertyFileReader("FreeDebitCardContentTextGroup1", "debitCard"), "Text");
			containsValidation(getText(DebitCardPage.getByOSType(platform, DebitCardPage.objCardContentTextGroup)), propertyFileReader("FreeDebitCardContentTextGroup2", "debitCard"), "Text");
		}
		logger.info("TDB_PC_013, DebitCard - verifyUserCanSeeDebitCardFees_TDB_PC_013");
		extentLoggerPass("TDB_PC_013", "TDB_PC_013, DebitCard - verifyUserCanSeeDebitCardFees_TDB_PC_013 is passed");
	}
}