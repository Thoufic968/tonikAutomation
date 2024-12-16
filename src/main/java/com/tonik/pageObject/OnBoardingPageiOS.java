package com.tonik.pageObject;
import org.openqa.selenium.By;

public class OnBoardingPageiOS {
    private OnBoardingPageiOS() {
    }
    public static By objWelcomeScreen = By.xpath("((//XCUIElementTypeOther[@name=\"Welcome to Tonik, the first neobank in the Philippines. What’s a neobank?\"])[2]/child::*[2]/child::*/child::*/child::*/child::*)");
    public static By objButton(String button){
        return By.xpath("(//XCUIElementTypeStaticText[contains(@name,'"+button+"')])[2]");
    }
    public static By objWelcomeScreen1 = By.xpath("(//*[@name='Welcome to Tonik, the first neobank in the Philippines.'])[5]");
    public static By objWelcomeScreen1Btn = By.xpath("(//XCUIElementTypeOther[@name=\"What’s a neobank?\"])[4]");
    public static By objWelcomeScreen2Btn = By.xpath("(//XCUIElementTypeOther[@name='Tell me more'])[4]");
    public static By objWelcomeScreen3Btn = By.xpath("(//XCUIElementTypeOther[@name='Tell me more'])[4]");
    public static By objWelcomeScreen4Btn = By.xpath("(//XCUIElementTypeOther[@name='Nice!'])[4]");
    public static By objWelcomeScreen5Btn = By.xpath("(//XCUIElementTypeOther[@name='I do!'])[4]");
    public static By objMobileNumberInputField = By.xpath("//XCUIElementTypeTextField");
    public static By objLoginWithYourPasswordInputField = By.xpath("//XCUIElementTypeSecureTextField");
    public static By objTickSymbol = By.xpath("//XCUIElementTypeTextField/parent::*/following-sibling::*");
    public static By objAlreadyHaveAccount = By.xpath("//*[@name='Already have an account?']");
    public static By objCreateAccountPage = By.id("com.tonik.mobile:id/Main_title_txt");
    public static By objLoginHereLink = By.xpath("(//*[@name='Log in here'])[2]");
    public static By objNextBtn = By.xpath("//XCUIElementTypeStaticText[@name='Next']");
    public static By objTermsAndConditionInfo = By.xpath("(//*[contains(@label,'complete terms and conditions')])[11]");
    public static By objHouseRule1 = By.xpath("//*[@name='Who can open a Tonik account.']");
    public static By objHouseRule2 = By.xpath("//*[@name='What you can do with your Tonik accounts.']");
    public static By objHouseRule3 = By.xpath("//*[@name=\"Dos and don'ts of using Tonik's services.\"]");
    public static By objHouseRule4 = By.xpath("//*[@name=\"Why it's necessary to verify your identity by providing documents or performing certain actions.\"]");
    public static By objHouseRule5 = By.xpath("//*[@name=\"How we keep your Tonik app, accounts and cards safe.\"]");
    public static By objHouseRule6 = By.xpath("//*[@name=\"Explaining our fees, limits, and interest.\"]");
    public static By objHouseRule7 = By.xpath("//*[@name=\"How we will reach out to you.\"]");
    public static By objReadTermsAndConditionLink = By.xpath("(//*[@name='Read full General Terms & Conditions'])[3]");
    public static By objTermsAndConditionCheckBoxInfo = By.xpath("//XCUIElementTypeStaticText[contains(@name,'I confirm that I have read')]");
    public static By objTermsAndConditionInfoCheckBox = By.xpath("(//XCUIElementTypeStaticText[contains(@name,'I confirm that I have read')]/parent::*/child::*/child::*)");
    public static By objAgreeAndContinueBtn = By.xpath("(//XCUIElementTypeOther[@name='Agree and Continue'])[2]");
    public static By objOurPrivacyMatters = By.id("com.tonik.mobile:id/Main_title_txt");
    public static By objPrivacyMattersInfo = By.xpath("//XCUIElementTypeStaticText[contains(@name,'In the Data Privacy Statement')]");
    public static By objPrivacyPolicy1 = By.xpath("//*[@name='How we obtain your data.']");
    public static By objPrivacyPolicy2 = By.xpath("//*[@name='What data we get from you.']");
    public static By objPrivacyPolicy3 = By.xpath("//*[@name='Why we need your data.']");
    public static By objPrivacyPolicy4 = By.xpath("//*[@name='What we do with your data.']");
    public static By objPrivacyPolicy5 = By.xpath("//*[@name='Who we share your data with and why.']");
    public static By objPrivacyPolicy6 = By.xpath("//*[@name='Your rights and how we respect them.']");
    public static By objPrivacyPolicy7 = By.xpath("//*[@name='How we keep your data safe.']");
    public static By objTonikAppAccessOtherDeviceAppsMsg = By.xpath("(//*[contains(@name,'details in the Tonik cloud')])[13]");
    public static By objDataPrivacyStatementLink = By.xpath("//XCUIElementTypeStaticText[contains(@name,'Read full Data Privacy Statement')]");
    public static By objDataPrivacyStatementInfo = By.xpath("//XCUIElementTypeStaticText[contains(@name,'make a more accurate credit decision')]");
    public static By objDataPrivacyStatementRadioBtn = By.xpath("(//XCUIElementTypeStaticText[contains(@name,'make a more accurate credit decision')]/following-sibling::*)");
    public static By objCustomerCareIcon = By.id("com.tonik.mobile:id/Header_right_click");
    public static By objCustomerCareIconLoginScreen = By.id("com.tonik.mobile:id/Header_right_click");
    public static By objOTPInputField (String number){
        return  By.xpath("(//android.widget.TextView[@text='⋆'])["+number+"] | (//XCUIElementTypeStaticText[@name='⋆'])["+number+"]");
    }
    public static By objResendOTP = By.xpath("//XCUIElementTypeStaticText[@name='Resend OTP']");
    public static By objResendOTPParentElement = By.xpath("//*[@value='Resend OTP']/parent::*");
    public static By objInvalidOTPPopup = By.id("com.tonik.mobile:id/Popup_sub_header_txt");
    public static By objPopupBtn = By.id("com.tonik.mobile:id/Popup_single_btn_click");
    public static By objIDSelectionPageTile = By.id("com.tonik.mobile:id/Main_title_txt");
    public static By objAutoDetailsPopulatedPageTitle = By.id("com.tonik.mobile:id/Main_title_txt");
    public static By objAutoPopulatedFirstName = By.xpath("//XCUIElementTypeStaticText[@label='First name']/parent::*/child::XCUIElementTypeOther/child::XCUIElementTypeTextField");
    public static By objAutoPopulatedMiddleName = By.xpath("//XCUIElementTypeStaticText[@label='Middle name']/parent::*/child::XCUIElementTypeOther/child::XCUIElementTypeTextField");
    public static By objAutoPopulatedLastName = By.xpath("//XCUIElementTypeStaticText[@label='Last name']/parent::*/child::XCUIElementTypeOther/child::XCUIElementTypeTextField");
    public static By objAutoPopulatedGender = By.xpath("//XCUIElementTypeStaticText[@label='Gender']/parent::*/child::XCUIElementTypeOther/child::XCUIElementTypeTextField");
    public static By objGenderInputField = By.xpath("(//XCUIElementTypeStaticText[@value ='Gender']/preceding-sibling::*)");
    public static By objAutoPopulatedDateOfBirth = By.xpath("//XCUIElementTypeStaticText[@value ='Date of birth']/parent::*/child::XCUIElementTypeOther/child::XCUIElementTypeTextField");
    public static By objPlaceOfBirth = By.xpath("//XCUIElementTypeStaticText[@value ='Place of birth']/parent::*/child::XCUIElementTypeOther/child::XCUIElementTypeTextField");
    public static By objPlaceOfBirthInputField = By.xpath("//XCUIElementTypeStaticText[@value ='Place of birth']/parent::*/child::XCUIElementTypeOther/child::XCUIElementTypeTextField");
    public static By objGenderDropdown(String dropdown){
        return By.xpath("//*[@name='"+dropdown+"']");
    }
    public static By objPhilID = By.xpath("//*[@name='National ID/ PhilID']");
    public static By objLetScanThisIDBtn = By.xpath("//XCUIElementTypeStaticText[@name=\"Let's scan this ID\"]");
    public static By objGotAClearScanTitle = By.id("com.tonik.mobile:id/Main_title_txt");
    public static By objSubmitFrontIDScanBtn = By.xpath("(//*[@name='Submit Front ID scan'])[3]");
    public static By objSubmitBackIDScanBtn = By.xpath("//XCUIElementTypeStaticText[@name='Submit Back ID scan']");
    public static By objDidWeGetItRightTitle = By.id("com.tonik.mobile:id/Main_title_txt");
    public static By objLooksGoodBtn = By.xpath("//XCUIElementTypeStaticText[@name='Looks good']");
    public static By objButtonToTapBtn = By.xpath("//XCUIElementTypeStaticText[@name='This is the button you need to tap.']");
    public static By objEnterOTP = By.id("com.tonik.mobile:id/Main_title_txt");
    public static By objReSendOTP = By.id("//XCUIElementTypeStaticText[@name=\"Resend OTP\"]");
    public static By objSSSID = By.xpath("//XCUIElementTypeStaticText[@name='SSS ID']");
    public static By objCalendarOkBtn = By.xpath("//XCUIElementTypeStaticText[@name=\"Ok\"]");
    public static By objMaxOTPAttemptPopup = By.id("com.tonik.mobile:id/Popup_sub_header_txt");
    public static By objOkBtn = By.id("com.tonik.mobile:id/Popup_single_btn_click");
    public static By objContinueBtn = By.xpath("(//XCUIElementTypeStaticText[@name='Continue'])");
    public static By objInvalidPlaceOfBirth = By.xpath("//XCUIElementTypeStaticText[@name='Please enter a valid place of birth']");
    public static By objEnterAlphabetsErrorMessage = By.xpath("//XCUIElementTypeStaticText[@name='Please enter alphabets only']");
    public static By objLetsGetToKnowYou = By.id("com.tonik.mobile:id/Main_title_txt");
    public static By objAutoPopulatedCountryOfResidence = By.xpath("//*[@value ='Country of Residence']/parent::*/child::*/child::*");
    public static By objZipCode = By.xpath("//XCUIElementTypeStaticText[@label='Zip code']");
    public static By objApplyZipCode = By.xpath("//XCUIElementTypeStaticText[@label='Apply zip code']");
    public static By objHowToFindZipCodeLink = By.xpath("//XCUIElementTypeStaticText[@label='How to find your zip code?']");
    public static By objProvince = By.xpath("//*[@value='Province']");
    public static By objCityOrMunicipality = By.xpath("//*[@value='City / Municipality']");
    public static By objBarangay = By.xpath("//*[@value='Barangay']");
    public static By objHouseUnitFlrNumber = By.xpath("//*[@value='House/Unit/Flr #, Bldg, Blk or Lots']");
    public static By objZipCodeInputField = By.xpath("//*[@value='Zip code']/following-sibling::*");
    public static By objProvinceInputField = By.xpath("//*[@value='Province']/following-sibling::*");
    public static By objCityOrMunicipalityInputField = By.xpath("//*[@value='City / Municipality']/following-sibling::*");
    public static By objBarangayInputText = By.xpath("//*[@value ='Barangay']/parent::*/child::*/child::*");
    public static By objHouseUnitFlrNumberInputField = By.xpath("//*[@value='House/Unit/Flr #, Bldg, Blk or Lots']/preceding-sibling::*");
    public static By objHouseUnitFlrData = By.xpath("//*[@value='House/Unit/Flr #, Bldg, Blk or Lots']/parent::*/child::*[2]/child::XCUIElementTypeTextField");
    public static By objInvalidZipCodeErrorMsg = By.xpath("//*[@value='Please enter a valid Zipcode']");
    public static By objBarangayDisplayed = By.xpath("//*[@type='XCUIElementTypeScrollView']/child::*/child::*/child::*/child::*/child::XCUIElementTypeStaticText");
    public static By objBarangay(String barangay){
        return By.xpath("(//*[@type='XCUIElementTypeScrollView']/child::*/child::*/child::*/child::*/child::XCUIElementTypeStaticText)["+barangay+"]");
    }
    public static By objBarangaySearchField = By.xpath("//*[@value='Type here to search']");
    public static By objSourceOfFundsInputField = By.xpath("(//*[@value='Source of Funds']/preceding-sibling::*)");
    public static By objSourceOfFundsInputText = By.xpath("(//*[@value='Source of Funds']/parent::*/child::*/child::*)");
    public static By objCurrentEmploymentStatus = By.xpath("(//*[@value='Current employment status']/preceding-sibling::*)");
    public static By objCurrentEmploymentStatusText = By.xpath("(//*[@value='Current employment status']/parent::*/child::*/child::*)");
    public static By objNatureOfWork = By.xpath("(//*[@value='Nature of work']/preceding-sibling::*)");
    public static By objIndustry = By.xpath("(//*[@value='Industry']/preceding-sibling::*)");
    public static By objList = By.xpath("//*[@type='XCUIElementTypeScrollView']/child::*/child::*/child::*/child::XCUIElementTypeStaticText");
    public static By objList(int listElement) {
        return By.xpath("(//*[@type='XCUIElementTypeScrollView']/child::*/child::*/child::*/child::XCUIElementTypeStaticText)["+listElement+"]");
    }
    public static By objSourceOfFundsPage = By.xpath("//*[@value='What are your sources of funds?']");
    public static By objCurrentEmploymentStatusPage = By.xpath("//*[@name='What's your current employment status?']");
    public static By objSelectSourceOfFunds(String sourceOfFund){
        return By.xpath("//XCUIElementTypeStaticText[contains(@name,'"+sourceOfFund+"')]");
    }
    public static By objSelectCurrentEmploymentStatus(String currentEmploymentStatus){
        return By.xpath("//XCUIElementTypeStaticText[contains(@name,'"+currentEmploymentStatus+"')]");
    }
    public static By objSelectNatureOfWork(String natureOfWork){
        return By.xpath("//XCUIElementTypeStaticText[contains(@name,'"+natureOfWork+"')]");
    }
    public static By objSelectIndustry(String industry){
        return By.xpath("//XCUIElementTypeStaticText[contains(@name,'"+industry+"')]");
    }
    public static By objWhatDoYouDoPage = By.id("com.tonik.mobile:id/Main_title_txt");
    public static By objFATCAPageTitle = By.id("com.tonik.mobile:id/Main_title_txt");
    public static By objAUSACitizen = By.xpath("//XCUIElementTypeStaticText[@name='A USA Citizen']");
    public static By objGreenCardHolder = By.xpath("//XCUIElementTypeStaticText[@name='A USA Green Card Holder']");
    public static By objUSAResident = By.xpath("//XCUIElementTypeStaticText[@name='A USA Resident']");
    public static By objNoneOfTheAbove = By.xpath("//XCUIElementTypeStaticText[@name='None of the above']");
    public static By objEmailAddress = By.xpath("(//*[@value='Email address']/preceding-sibling::*)");
    public static By objEmailAddressText = By.xpath("(//*[@value='Email address']/parent::*/child::*/child::*)");
    public static By objMotherMaidenName = By.xpath("(//*[@value=\"Mother's maiden name\"]/preceding-sibling::*)");
    public static By objMotherMaidenNameText = By.xpath("(//*[@value=\"Mother's maiden name\"]/parent::*/child::*/child::*)");
    public static By objDefineOurBankingRelationship = By.xpath("(//*[@value='Define our banking relationship']/preceding-sibling::*)");
    public static By objDefineOurBankingRelationshipInputText = By.xpath("(//*[@value='Define our banking relationship']/parent::*/child::*/child::*)");
    public static By objMoreTransitionTextSwitchSlider = By.xpath("(//XCUIElementTypeStaticText[@name='I plan to transact more than ₱500,000 per month'])/following-sibling::*");
    public static By objSelectBankingRelationship(String relationship){
        return By.xpath("//XCUIElementTypeStaticText[contains(@name,\""+relationship+"\")]");
    }
    public static By objInvalidEmailAddressErrorMessage = By.xpath("//XCUIElementTypeStaticText[contains(@name,\"Please enter a valid email address\")]");
    public static By objBlankEmailAddressErrorMessage = By.xpath("//XCUIElementTypeStaticText[contains(@name,'Email address cannot be blank')]");
    public static By objInvalidMotherMaidenName = By.xpath("//XCUIElementTypeStaticText[@name=\"Please enter a valid Mother's maiden name\"]");
    public static By objInvalidMotherMaidenNameErrorMsg = By.xpath("//XCUIElementTypeStaticText[contains(@name,'Please enter alphabets only')]");
    public static By objBlankMotherMaidenName = By.xpath("//XCUIElementTypeStaticText[contains(@name,\"Mother's maiden name cannot be blank\")]");
    public static By objPurposeOfAccount = By.xpath("//*[@name='What will you use the account for?']/parent::*/parent::*/following::XCUIElementTypeStaticText");
    public static By objPurposeOfAccount(int sPurpose) {
        return By.xpath("(//*[@name='What will you use the account for?']/parent::*/parent::*/following::XCUIElementTypeStaticText)["+sPurpose+"]");
    }
    public static By objDefineBankingRelationshipPopup = By.xpath("(//XCUIElementTypeStaticText[@name='Define our banking relationship'])[2]    ");
    public static By objPurposeOfAccountTxt = By.xpath("(//XCUIElementTypeStaticText[@name='What will you use the account for?'])");
    public static By objPersonalOption = By.xpath("//XCUIElementTypeStaticText[@name=\"It's personal\"]");
    public static By objBusinessOption = By.xpath("//XCUIElementTypeStaticText[@name=\"It's all business\"]");
    public static By objRemittancesOption = By.xpath("//XCUIElementTypeStaticText[@name=\"For my remittances\"]");
    public static By objEraserIcon = By.xpath("(((//*[contains(@name,'By tapping')])[10]/preceding-sibling::*)[3]/child::*/child::*)[2]");
    public static By objLetsSealTheDealPage = By.id("com.tonik.mobile:id/Main_title_txt");
    public static By objNextBtnInfo = By.xpath("//XCUIElementTypeStaticText[contains(@name,'By tapping')]");
    public static By objCoupleMoreThingsPage = By.xpath("//*[contains(@name,'A couple more things')]");
    public static By objHowToLoginPage = By.xpath("//*[@name='How to login']");
    public static By objLoginStep1 = By.xpath("//*[@name='Enter your registered mobile number']");
    public static By objLoginStep2 = By.xpath("//*[@name='Take a Face Identity Scan']");
    public static By objLoginStep3 = By.xpath("//*[@name='Reset your password']");
    public static By objLoginStep1Info = By.xpath("//*[@name='If your registered mobile number is unavailable, please contact us.']");
    public static By objLoginStep2Info = By.xpath("//*[@name='We need to confirm your identity again since you are trying to log in on a new device.']");
    public static By objLoginStep3Info = By.xpath("//*[@name='For security reasons, we have to ask you to update your password.']");
    public static By objVerifyMobileNumberPage = By.xpath("//*[@name='Verify mobile number']");
    public static By objTryAgainBtn = By.xpath("(//*[@label='Try again'])[2]");
    public static By objChangeRegisteredMobileNumber = By.xpath("//*[@label='Change my registered mobile number']");
    public static By objInvalidMobileNumberPopup = By.id("com.tonik.mobile:id/Popup_sub_header_txt");
    public static By objGetInTouchWithCustomerCarePage = By.xpath("//*[contains(@name,'Get in touch')]");
    public static By objGetInTouchWithCustomerCarePageInfo = By.xpath("//*[contains(@name,'For security reasons')]");
    public static By objContactTonikBtn = By.xpath("//*[@label='Contact Tonik']");
    public static By objCloseBtn = By.xpath("//*[@label='Close']");
    public static By objExistingTonikCustomerPopup = By.xpath("//*[contains(@label,'We already have this number')]");
    public static By objEnterNewNumber = By.xpath("//*[@label='Enter new number']");
    public static By objLogInToTonik = By.xpath("//*[@label='Log in to Tonik']");
    public static By objHeresTheDeal = By.id("com.tonik.mobile:id/Main_title_txt");
    public static By objFeature1 = By.xpath("//XCUIElementTypeStaticText[contains(@name,'No minimum balance')]");
    public static By objFeature2 = By.xpath("//XCUIElementTypeStaticText[contains(@name,'Solo and Group Stashes')]");
    public static By objFeature3 = By.xpath("//XCUIElementTypeStaticText[contains(@name,'Open up to 5 Time Deposit accounts')]");
    public static By objFeature4 = By.xpath("//XCUIElementTypeStaticText[contains(@name,'Free transfers to other banks.')]");
    public static By objFeature5 = By.xpath("//XCUIElementTypeStaticText[contains(@name,'Free virtual card')]");
    public static By objFeature6 = By.xpath("//XCUIElementTypeStaticText[contains(@name,'Free Tonik to Tonik transfers.')]");
    public static By objFeature7 = By.xpath("//XCUIElementTypeStaticText[contains(@name,'Free hugs!')]");
    public static By objDeal = By.xpath("//XCUIElementTypeStaticText[@name='DEAL!']");
    public static By objPasswordRule(int rule){
        return By.xpath("(//XCUIElementTypeStaticText[@name='Create a password']/parent::*/child::*/child::XCUIElementTypeStaticText)["+rule+"]");
    }
    public static By objPasswordRule = By.xpath("//XCUIElementTypeStaticText[@name='Create a password']/parent::*/child::*/child::XCUIElementTypeStaticText");
    public static By objPasswordField = By.xpath("//XCUIElementTypeSecureTextField[@value='Password']");
    public static By objPasswordInputField = By.xpath("(//*[@type='XCUIElementTypeSecureTextField'])[1]");
    public static By objConfirmPasswordField = By.xpath("//XCUIElementTypeSecureTextField[@value='Confirm password']");
    public static By objTermsAndConditionPageTitle = By.id("com.tonik.mobile:id/Main_title_txt");
    public static By objIndustryLastOption = By.xpath("//*[@name='Wholesale and Retail Trade']");
    public static By objNatureOfWorkLastOption = By.xpath("//*[@name='Other Professional services']");
    public static By objPasswordSuggestionPopup = By.id("Pinky swear that you’ll…");
    public static By objPasswordSuggestion = By.id("Pinky swear that you’ll…");
    public static By objPinkySwearGotItButton = By.xpath("//XCUIElementTypeStaticText[@name=\"Got it\"]");
    public static By objPasswordSuggestion(int passwordSuggestion){
        return By.xpath("(//*[@name=\"Pinky swear that you’ll…\"]/parent::*/child::*/child::*)["+passwordSuggestion+"]");
    }
    public static By objGotItBtn = By.xpath("//XCUIElementTypeButton[@name=\"Got it\"]");
    public static By objPasswordMismatchError = By.xpath("//*[@name='Passwords did not match']");
    public static By objPasswordSuggestionCheckBox(int checkBox) {
        return By.xpath("(//*[@type='XCUIElementTypeScrollView']/child::XCUIElementTypeImage)["+checkBox+"]");
    }
    public static By objAvailableBalance = By.xpath("//*[contains(@name,'₱')]");
    public static By objTopUpIcon = By.xpath("//*[@name='Top up']/preceding-sibling::*/child::*");
    public static By objSendIcon = By.xpath("//*[@name='Send']/preceding-sibling::*/child::*");
    public static By objPayIcon = By.xpath("//*[@name='Pay']/preceding-sibling::*/child::*");
    public static By objHistoryIcon = By.xpath("//XCUIElementTypeStaticText[@name='History']/preceding-sibling::*/child::*/child::XCUIElementTypeButton");
    public static By objDashboardCustomerCareIcon = By.xpath("(//XCUIElementTypeOther[@name='icon-button-container-outer-layer'])[2]");
    public static By objVirtualCard = By.xpath("(//XCUIElementTypeOther[@name=\"Virtual Card -> **\"])[1]");
    public static By objDebitCard = By.xpath("//*[contains(@name,'Debit Card')]/parent::*/parent::*/parent::*");
    public static By objStashTile = By.xpath("//*[@name='Stashes']");
    public static By objStashTileInfo = By.xpath("//XCUIElementTypeStaticText[@name='Stashes']/following-sibling::XCUIElementTypeStaticText");
    public static By objTimeDepositTile = By.xpath("//*[@name='Time Deposit']");
    public static By objTimeDepositTileInfo = By.xpath("//XCUIElementTypeStaticText[@name='Time Deposit']/following-sibling::XCUIElementTypeStaticText");
    public static By objLoansTile = By.xpath("//*[@name='Loans']");
    public static By objLoansTileInfo = By.xpath("//XCUIElementTypeStaticText[@name='Loans']/following-sibling::XCUIElementTypeStaticText");
    public static By objDashboardWelcomeScreen = By.xpath("//*[@name='Welcome to Tonik, hun!']");
    public static By objWelcomeInfo = By.xpath("//*[@name='Your neobanking romance starts now']");
    public static By objBorrow = By.xpath("//*[@name='Borrow']");
    public static By objBorrowInfo = By.xpath("//*[@name='Find the right loan that fits your needs and wants. One day approval.']");
    public static By objBorrowLearnMore = By.xpath("(//*[@name='Learn more'])[1]");
    public static By objSave = By.xpath("//*[@name='Save']");
    public static By objSaveLearnMore = By.xpath("(//*[@name='Learn more'])[2]");
    public static By objSaveInfo = By.xpath("//*[@name='Fall in love with BIG savings - get up to 6% p.a.']");
    public static By objExploreNow = By.xpath("//*[@name='I want to Explore']");
    public static By objIWillDoItBtn = By.xpath("//*[contains(@name,'ll do it later, pinky swear')]");
    public static By objBorrowScreenTitle = By.xpath("//*[@name='Need fast cash or easy payments?']");
    public static By objTileHeader(int header) {
        return By.xpath("//*[@resource-id='com.tonik.mobile:id/Txt_header"+header+"']");
    }
    public static By objSubtitle(int subtitle){
        return By.xpath("//*[@resource-id='com.tonik.mobile:id/Txt_sub_title"+subtitle+"']");
    }
    public static By objReviewInProgress = By.xpath("//*[@name='Review in progress']");
    public static By objReviewInProgressInfo1 = By.xpath("//*[contains(@name,'We now have to review your application before we can open your Tonik')]");
    public static By objReviewInProgressInfo2 = By.xpath("//*[contains(@name,'This review will take around 1 working days')]");
    public static By objContactTonikCustomerCare = By.xpath("//*[contains(@name,'Customer Care')]");
    public static By objCloseTheApp = By.xpath("//*[@name='Close the app']");
    public static By objBlockedUSCitizenPage = By.xpath("//*[contains(@name,'This is really hard for us')]");
    public static By objBlockedUSCitizenInfo = By.xpath("//*[contains(@name,'we are unable to open a Tonik account')]");
    public static By objNextButton = By.xpath("(//XCUIElementTypeOther[@name='Next'])[3]");
    public static By objPageTitle = By.id("com.tonik.mobile:id/Main_title_txt");
    public  static By objPageHeader = By.id("com.tonik.mobile:id/Main_title_txt");
    public static By objBackBtn = By.id("com.tonik.mobile:id/Header_left_click");
    public static By objPageSubTitle = By.id("com.tonik.mobile:id/Sub_title_txt");
    public static By objKeyboardDoneButton = By.xpath("//*[@name='Done']");
    public static By objPopupSubHeader = By.id("com.tonik.mobile:id/Popup_sub_header_txt");
    public static By objCreateAPassword = By.id("Create a password");
    public static By objCreateAPasswordCustomerCareIcon = By.xpath("//*[@name='Create a password']/preceding::XCUIElementTypeButton");
    public static By objLoginBtn = By.xpath("//*[@name='Login']");
    public static By objWhileUsingAppIos = By.xpath("//XCUIElementTypeButton[@name='Allow While Using App']");
    public static By objPictureAccessPopupIos = By.xpath("//XCUIElementTypeStaticText[contains(@name,'Would Like to Access the Camera')]");
    public static By objPhotoAccessPopupIos = By.xpath("//XCUIElementTypeStaticText[contains(@name,'Would Like to Access Your Photos')]");
    public static By objAllowAccessAllPhotosIos = By.xpath("//XCUIElementTypeButton[contains(@name,'Allow Access to All Photos')]");
    public static By objNotificationPopupIos = By.xpath("//XCUIElementTypeStaticText[contains(@name,'Send You Notifications')]");
    public static By objAllowBtnIos = By.id("Allow");
    public static By objSkipThisStep =By.xpath("//XCUIElementTypeStaticText[@name='Skip this step']");
}