package com.tonik.pageObject;
import org.openqa.selenium.By;

public class OnBoardingPage {
    // Private constructor to prevent instantiation
    private OnBoardingPage() {
    }
//  Welcome Screens
    public static By objWelcomeScreen = By.xpath("(//androidx.recyclerview.widget.RecyclerView/child::*/child::*/child::*)[2]/child::*");
    public static By objButton(String button){
        return By.xpath("//*[contains(@text,'"+button+"')]");
    }
    public static By objWelcomeScreen1 = By.xpath("//*[@text='Welcome to Tonik, the first neobank in the Philippines.']");
    public static By objWelcomeScreen1Btn = By.xpath("//*[@text='Welcome to Tonik, the first neobank in the Philippines.']/parent::*/following-sibling::android.view.ViewGroup/child::*/child::*");
    public static By objWelcomeScreen2Btn = By.xpath("//*[@text='A neobank is 100% digital - that means FAST. Do stuff like   opening an account and getting a loan in just minutes. ']/parent::*/following-sibling::android.view.ViewGroup/child::*/child::*");
    public static By objWelcomeScreen3Btn = By.xpath("//*[@text='Loans have never been easier. No credit history and collaterals? No problem! Get a fuss-free loan QUICK with  minimal docs needed.']/parent::*/following-sibling::android.view.ViewGroup/child::*/child::*");
    public static By objWelcomeScreen4Btn = By.xpath("//*[@text='You’ll also get a virtual debit card instantly. Ready to use for online shopping and payments.']/parent::*/following-sibling::android.view.ViewGroup/child::*/child::*");
    public static By objWelcomeScreen5Btn = By.xpath("//*[@text='Smart. Digital. Simple. \n" +
            "Your neobanking romance.']/parent::*/following-sibling::android.view.ViewGroup/child::*/child::*");
//  Mobile number input page
    public static By objMobileNumberInputField = By.xpath("//android.widget.EditText | //XCUIElementTypeTextField");
    public static By objTickSymbol = By.xpath("//android.widget.EditText/following-sibling::android.view.ViewGroup");
    public static By objAlreadyHaveAccount = By.xpath("//*[@text='Already have an account?']");
    public static By objCreateAccountPage = By.xpath("//*[@text='Fancy meeting you here, so what's your number?']");
    public static By objLoginHereLink = By.xpath("//*[@text='Log in here']");
    public static By objNextBtn = By.xpath("//*[@text='Next']");
//  Terms and conditions page
    public static By objTermsAndConditionInfo = By.xpath("//*[contains(@text,'complete terms and conditions')]");
    public static By objHouseRule1 = By.xpath("//*[@text='Who can open a Tonik account.']");
    public static By objHouseRule2 = By.xpath("//*[@text='What you can do with your Tonik accounts.']");
    public static By objHouseRule3 = By.xpath("//*[@text=\"Dos and don'ts of using Tonik's services.\"]");
    public static By objHouseRule4 = By.xpath("//*[@text=\"Why it's necessary to verify your identity by providing documents or performing certain actions.\"]");
    public static By objHouseRule5 = By.xpath("//*[@text=\"How we keep your Tonik app, accounts and cards safe.\"]");
    public static By objHouseRule6 = By.xpath("//*[@text=\"Explaining our fees, limits, and interest.\"]");
    public static By objHouseRule7 = By.xpath("//*[@text=\"How we will reach out to you.\"]");
    public static By objReadTermsAndConditionLink = By.xpath("//*[@text=\"Read full General Terms & Conditions\"]");
    public static By objTermsAndConditionCheckBoxInfo = By.xpath("//*[@text='I confirm that I have read, understood, and agreed to the full General Terms & Conditions.']");
    public static By objTermsAndConditionInfoCheckBox = By.xpath("//*[@text='I confirm that I have read, understood, and agreed to the full General Terms & Conditions.']/preceding-sibling::*");
    public static By objAgreeAndContinueBtn = By.xpath("//*[@text='Agree and Continue']");
//  Our privacy page
    public static By objOurPrivacyMatters = By.xpath("//*[@text='Our privacy matters']");
    public static By objPrivacyMattersInfo = By.xpath("//*[@text='In the Data Privacy Statement, you will find our complete statement regarding your privacy including the provisions on the following items:']");
    public static By objPrivacyPolicy1 = By.xpath("//*[@text='How we obtain your data.']");
    public static By objPrivacyPolicy2 = By.xpath("//*[@text='What data we get from you.']");
    public static By objPrivacyPolicy3 = By.xpath("//*[@text='Why we need your data.']");
    public static By objPrivacyPolicy4 = By.xpath("//*[@text='What we do with your data.']");
    public static By objPrivacyPolicy5 = By.xpath("//*[@text='Who we share your data with and why.']");
    public static By objPrivacyPolicy6 = By.xpath("//*[@text='Your rights and how we respect them.']");
    public static By objPrivacyPolicy7 = By.xpath("//*[@text='How we keep your data safe.']");
    public static By objTonikAppAccessOtherDeviceAppsMsg = By.xpath("//*[@text='The Tonik App requires your permission to access your  camera, audio, gallery, contacts, installed apps, and location  to function as intended. We do not store any of your contacts’ details in the Tonik cloud.']");
    public static By objDataPrivacyStatementLink = By.xpath("//*[@text='Read full Data Privacy Statement']");
    public static By objDataPrivacyStatementInfo = By.xpath("//*[contains(@text,'I have read the Data Privacy statement')]");
    public static By objDataPrivacyStatementRadioBtn = By.xpath("//*[contains(@text,'I have read the Data Privacy statement')]/preceding-sibling::*");
    public static By objCustomerCareIcon = By.id("com.tonik.mobile:id/contact_button");
    public static By objCustomerCareIconLoginScreen = By.id("com.tonik.mobile:id/Header_right_click");
//  Enter OTP page
    public static By objOTPInputField (String number){
        return  By.xpath("(//android.widget.TextView[@text='⋆'])["+number+"]");
    }
    public static By objResendOTP = By.xpath("//*[@text='Resend OTP']");
    public static By objResendOTPParentElement = By.xpath("//*[@text='Resend OTP']/parent::*");
    public static By objInvalidOTPPopup = By.id("com.tonik.mobile:id/Popup_sub_header_txt");
    public static By objPopupBtn = By.id("com.tonik.mobile:id/Popup_single_btn_txt");
//  ID Selection Page
    public static By objIDSelectionPageTile = By.xpath("//*[@text='You look too young. We’ll need to check your ID']");
//  Auto Populated Page details
    public static By objAutoDetailsPopulatedPageTitle = By.xpath("//*[@text='Let’s get to know the real you.']");
    public static By objFirstName = By.xpath("//*[@text='First name']/parent::*/child::android.widget.EditText");
    public static By objMiddleName = By.xpath("//*[@text='Middle name']/parent::*/child::android.widget.EditText");
    public static By objLastName = By.xpath("//*[@text='Last name']/parent::*/child::android.widget.EditText");
    public static By objGender = By.xpath("//*[@text='Gender']/parent::*/child::android.widget.EditText");
    public static By objGenderInputField = By.xpath("//*[@text='Gender']/parent::*/child::android.widget.EditText");
    public static By objDateOfBirth = By.xpath("//*[@text='Date of birth']/parent::*/child::android.widget.EditText");
    public static By objPlaceOfBirth = By.xpath("//*[@text='Place of birth']/parent::*/child::android.widget.EditText");
    public static By objPlaceOfBirthInputField = By.xpath("//*[@text='Place of birth']/parent::*/child::android.widget.EditText");
    public static By objGenderDropdown(String dropdown){
        return By.xpath("//*[@text='"+dropdown+"']");
    }
    public static By objPassportOption = By.xpath("//*[@text='Passport']");
    public static By objPhilID = By.xpath("//*[@text='National ID/ PhilID']");
//  Let Scan This ID Btn
    public static By objLetScanThisIDBtn = By.xpath("//*[@text=\"Let's scan this ID\"]");
    public static By objSkipThisStep = By.xpath("//*[@text='Skip this step']");
    public static By objGotAClearScanTitle = By.xpath("//*[@text='Got a clear scan?']");
    public static By objSubmitFrontIDScanBtn = By.xpath("//*[@text='Submit Front ID scan']");
    public static By objSubmitBackIDScanBtn = By.xpath("//*[@text='Submit Back ID scan']");
    public static By objDidWeGetItRightTitle = By.xpath("//*[@text='Did we get it right?']");
    public static By objLooksGoodBtn = By.xpath("//*[@text='Looks good']");
    public static By objButtonToTapBtn = By.xpath("//*[@text='This is the button you need to tap.']");
    public static By objEnterOTP = By.xpath("//*[@text='Enter OTP']");
    public static By objSSSID = By.xpath("//*[@text='SSS ID']");
    public static By objPreviousMonth = By.xpath("//*[@content-desc='Previous month']");
    public static By objSelectDate(String date){
        return By.xpath("//*[@text='"+date+"']");
    }
    public static By objCalendarOkBtn = By.id("android:id/button1");
    public static By objMaxOTPAttemptPopup = By.id("com.tonik.mobile:id/Popup_sub_header_txt");
    public static By objOkBtn = By.id("com.tonik.mobile:id/Popup_single_btn_click");
    public static By objContinueBtn = By.xpath("//*[@text='Continue']");
    public static By objInvalidPlaceOfBirth = By.xpath("//*[@text='Please enter a valid place of birth']");
    public static By objEnterAlphabetsErrorMessage = By.xpath("//*[@text='Please enter alphabets only']");
    public static By objLetsGetToKnowYou = By.xpath("//*[@text='Let’s get to know the real you.']");
//  Country of Residence page
    public static By objAutoPopulatedCountryOfResidence = By.xpath("//*[@text='Country of Residence']/following-sibling::*");
    public static By objZipCode = By.xpath("//*[@text='Zip code']");
    public static By objApplyZipCode = By.xpath("//*[@text='Apply zip code']");
    public static By objHowToFindZipCodeLink = By.xpath("//*[@text='How to find your zip code?']");
    public static By objHowToFindZipCodeWebPage = By.id("com.sec.android.app.sbrowser:id/location_bar_edit_text");
    public static By objProvince = By.xpath("//*[@text='Province']");
    public static By objCityOrMunicipality = By.xpath("//*[@text='City / Municipality']");
    public static By objBarangay = By.xpath("//*[@text='Barangay'] | //*[@value='Barangay']");
    public static By objHouseUnitFlrNumber = By.xpath("//*[@text='House/Unit/Flr #, Bldg, Blk or Lots']");
    public static By objZipCodeInputField = By.xpath("//*[@text='Zip code']/following-sibling::*");
    public static By objProvinceInputField = By.xpath("//*[@text='Province']/following-sibling::*");
    public static By objCityOrMunicipalityInputField = By.xpath("//*[@text='City / Municipality']/following-sibling::*");
    public static By objBarangayInputField = By.xpath("//*[@text='Barangay']/following-sibling::*");
    public static By objHouseUnitFlrNumberInputField = By.xpath("//*[@text='House/Unit/Flr #, Bldg, Blk or Lots']/parent::*/child::android.widget.EditText");
    public static By objInvalidZipCodeErrorMsg = By.xpath("//*[@text='Please enter a valid Zipcode']");
    public static By objBarangayDisplayed = By.xpath("//*[@class='android.widget.ScrollView']/child::*/child::*/child::*/child::*/child::android.widget.TextView");
    public static By objBarangay(String barangay){
        return By.xpath("(//*[@class='android.widget.ScrollView']/child::*/child::*/child::*/child::*/child::android.widget.TextView)["+barangay+"] | (//*[@type='XCUIElementTypeScrollView']/child::*/child::*/child::*/child::*/child::XCUIElementTypeStaticText)["+barangay+"]");
    }
    public static By objBarangaySearchField = By.xpath("//*[@class='android.widget.EditText'] | //*[@value='Type here to search']");
    public static By objSourceOfFundsInputField = By.xpath("//*[@text='Source of Funds']/parent::*/child::android.widget.EditText");
    public static By objCurrentEmploymentStatus = By.xpath("//*[@text='Current employment status']/parent::*/child::android.widget.EditText");
    public static By objNatureOfWork = By.xpath("//*[@text='Nature of work']/parent::*/child::android.widget.EditText");
    public static By objIndustry = By.xpath("//*[@text='Industry']/parent::*/child::android.widget.EditText");
    public static By objList = By.xpath("//*[@class='android.widget.ScrollView']/child::*/child::*/child::*/child::android.widget.TextView");
    public static By objList(int listElement) {
        return By.xpath("(//*[@class='android.widget.ScrollView']/child::*/child::*/child::*/child::android.widget.TextView)["+listElement+"]");
    }
    public static By objSourceOfFundsPage = By.xpath("//*[@text='What are your sources of funds?']");
    public static By objCurrentEmploymentStatusPage = By.xpath("//*[@text='What's your current employment status?']");
    public static By objSelectSourceOfFunds(String sourceOfFund){
        return By.xpath("//*[contains(@text,'"+sourceOfFund+"')]");
    }
    public static By objSelectCurrentEmploymentStatus(String currentEmploymentStatus){
        return By.xpath("//*[contains(@text,'"+currentEmploymentStatus+"')]");
    }
    public static By objSelectNatureOfWork(String natureOfWork){
        return By.xpath("//*[contains(@text,'"+natureOfWork+"')]");
    }
    public static By objSelectIndustry(String industry){
        return By.xpath("//*[contains(@text,'"+industry+"')]");
    }
//  What do you do? page
    public static By objWhatDoYouDoPage = By.xpath("//*[@text='What do you do?']");
    public static By objFATCAPageTitle = By.xpath("//*[@text='Are you sure you are not...']");
    public static By objAUSACitizen = By.xpath("//*[@text='A USA Citizen']");
    public static By objGreenCardHolder = By.xpath("//*[@text='A USA Green Card Holder']");
    public static By objUSAResident = By.xpath("//*[@text='A USA Resident']");
    public static By objNoneOfTheAbove = By.xpath("//*[@text='None of the above']");
//  Email , mother and Banking relation page
    public static By objEmailAddress = By.xpath("//*[@text='Email address']/parent::*/child::android.widget.EditText");
    public static By objMotherMaidenName = By.xpath("//*[@text=\"Mother's maiden name\"]/parent::*/child::android.widget.EditText");
    public static By objDefineOurBankingRelationship = By.xpath("//*[@text='Define our banking relationship']/parent::*/child::android.widget.EditText");
    public static By objMoreTransitionTextSwitchSlider = By.xpath("//*[@text='I plan to transact more than ₱500,000 per month']/following-sibling::*/child::*");
    public static By objSelectBankingRelationship(String relationship){
        return By.xpath("//*[contains(@text,\""+relationship+"\")]");
    }
    public static By objInvalidEmailAddressErrorMessage = By.xpath("//*[@text='Please enter a valid email address']");
    public static By objBlankEmailAddressErrorMessage = By.xpath("//*[@text='Email address cannot be blank']");
    public static By objInvalidMotherMaidenName = By.xpath("//*[@text=\"Please enter a valid Mother's maiden name\"]");
    public static By objInvalidMotherMaidenNameErrorMsg = By.xpath("//*[@text='Please enter alphabets only']");
    public static By objBlankMotherMaidenName = By.xpath("//*[@text=\"Mother's maiden name cannot be blank\"]");
    public static By objPurposeOfAccount = By.xpath("//*[@text='Define our banking relationship']/parent::*/following-sibling::*/child::*/child::*/child::android.widget.TextView");
    public static By objPurposeOfAccount(int sPurpose) {
        return By.xpath("(//*[@text='Define our banking relationship']/parent::*/following-sibling::*/child::*/child::*/child::android.widget.TextView)["+sPurpose+"]");
    }
    public static By objDefineBankingRelationshipPopup = By.xpath("//*[@text='Define our banking relationship']");
    public static By objPurposeOfAccountTxt = By.xpath("//*[@text='What will you use the account for?']");
    public static By objPersonalOption = By.xpath("//*[@text=\"It's personal\"]");
    public static By objBusinessOption = By.xpath("//*[@text=\"It's all business\"]");
    public static By objRemittancesOption = By.xpath("//*[@text=\"For my remittances\"]");
    public static By objEraserIcon = By.xpath("//android.widget.LinearLayout/following-sibling::android.view.ViewGroup");
//  Lets Seal The Deal Page
    public static By objLetsSealTheDealPage = By.xpath("//*[contains(@text,'seal the deal')]");
    public static By objNextBtnInfo = By.xpath("//*[contains(@text,'By tapping')]");
    public static By objCoupleMoreThingsPage = By.xpath("//*[contains(@text,'A couple more things')]");
//  How to login page
    public static By objHowToLoginPage = By.xpath("//*[@text='How to login']");
//  LoginSteps
    public static By objLoginStep1 = By.xpath("//*[@text='Enter your registered mobile number']");
    public static By objLoginStep2 = By.xpath("//*[@text='Take a Face Identity Scan']");
    public static By objLoginStep3 = By.xpath("//*[@text='Reset your password']");
    public static By objLoginStep1Info = By.xpath("//*[@text='If your registered mobile number is unavailable, please contact us.']");
    public static By objLoginStep2Info = By.xpath("//*[@text='We need to confirm your identity again since you are trying to log in on a new device.']");
    public static By objLoginStep3Info = By.xpath("//*[@text='For security reasons, we have to ask you to update your password.']");
    public static By objVerifyMobileNumberPage = By.xpath("//*[@text='Verify mobile number']");
    public static By objTryAgainBtn = By.xpath("//*[@text='Try again']");
    public static By objChangeRegisteredMobileNumber = By.xpath("//*[@text='Change my registered mobile number']");
    public static By objInvalidMobileNumberPopup = By.id("com.tonik.mobile:id/Popup_sub_header_txt");
    public static By objGetInTouchWithCustomerCarePage = By.xpath("//*[contains(@text,'Get in touch')]");
    public static By objGetInTouchWithCustomerCarePageInfo = By.xpath("//*[contains(@text,'For security reasons')]");
    public static By objContactTonikBtn = By.xpath("//*[@text='Contact Tonik']");
    public static By objCloseBtn = By.xpath("//*[@text='Close']");
    public static By objExistingTonikCustomerPopup = By.xpath("//*[contains(@text,'We already have this number')]");
    public static By objEnterNewNumber = By.xpath("//*[@text='Enter new number']");
    public static By objLogInToTonik = By.xpath("//*[@text='Log in to Tonik']");
//  Heres The Deal
    public static By objHeresTheDeal = By.xpath("//*[@text='Here’s the deal']");
    public static By objFeature1 = By.xpath("//*[@text='No minimum balance, no dormancy fees, no minimum deposits, no account closure fees']");
    public static By objFeature2 = By.xpath("//*[@text='Open up to 5 Solo and Group Stashes']");
    public static By objFeature3 = By.xpath("//*[@text='Open up to 5 Time Deposit accounts']");
    public static By objFeature4 = By.xpath("//*[@text='Free transfers to other banks.']");
    public static By objFeature5 = By.xpath("//*[@text='Free virtual card']");
    public static By objFeature6 = By.xpath("//*[@text='Free Tonik to Tonik transfers.']");
    public static By objFeature7 = By.xpath("//*[@text='Free hugs!']");
//  Deal page
    public static By objDeal = By.xpath("//*[@text='DEAL!']");
//  Create a Password page
    public static By objPasswordRule(int rule){
        return By.xpath("(//*[@resource-id='com.tonik.mobile:id/rules_main']/child::*/child::android.widget.TextView)["+rule+"]");
    }
    public static By objPasswordRule = By.xpath("//*[@resource-id='com.tonik.mobile:id/rules_main']/child::*/child::android.widget.TextView");
    public static By objPasswordField = By.id("com.tonik.mobile:id/pin");
    public static By objConfirmPasswordField = By.id("com.tonik.mobile:id/confirm");
    public static By objTermsAndConditionPageTitle = By.xpath("//*[contains(@text,'A few house rules')]");
    public static By objIndustryLastOption = By.xpath("//*[@text='Wholesale and Retail Trade']");
    public static By objNatureOfWorkLastOption = By.xpath("//*[@text='Other Professional services']");
    public static By objPasswordSuggestionPopup = By.id("com.tonik.mobile:id/tvPasswordTitle");
    public static By objPasswordSuggestion = By.id("com.tonik.mobile:id/tvInfo");
    public static By objPasswordSuggestion(int passwordSuggestion){
        return By.xpath("(//*[@resource-id='com.tonik.mobile:id/tvInfo'])["+passwordSuggestion+"]");
    }
    public static By objGotItBtn = By.id("com.tonik.mobile:id/btnGotIt");
    public static By objPasswordMismatchError = By.xpath("//*[@text='Passwords did not match']");
    public static By objPasswordSuggestionCheckBox(int checkBox) {
        return By.xpath("//*[@resource-id='com.tonik.mobile:id/img_rule"+checkBox+"']");
    }
//  Dashboard page
    public static By objAvailableBalance = By.xpath("//*[contains(@text,'₱')] | (//XCUIElementTypeStaticText[contains(@name,'₱')])[1]");
    public static By objTopUpIcon = By.xpath("//*[@text='Top up']/preceding-sibling::*/child::*");
    public static By objHistoryIcon = By.xpath("//*[@text='History']/preceding-sibling::*/child::* | //XCUIElementTypeStaticText[@name='History']/preceding-sibling::*/child::*/child::*");
    public static By objLoansTile = By.xpath("//*[@text='Loans']");
//  Welcome to Tonik, hun!
    public static By objDashboardWelcomeScreen = By.xpath("//*[@text='Welcome to Tonik, hun!']");
    public static By objWelcomeInfo = By.xpath("//*[@text='Your neobanking romance starts now']");
    public static By objBorrow = By.xpath("//*[@text='Borrow']");
    public static By objBorrowInfo = By.xpath("//*[@text='Find the right loan that fits your needs and wants. One day approval.']");
    public static By objBorrowLearnMore = By.xpath("(//*[@text='Learn more'])[1]");
    public static By objSave = By.xpath("//*[@text='Save']");
    public static By objSaveLearnMore = By.xpath("(//*[@text='Learn more'])[2]");
    public static By objSaveInfo = By.xpath("//*[@text='Fall in love with BIG savings - get up to 6% p.a.']");
    public static By objExploreNow = By.xpath("//*[@text='I want to Explore']");
    public static By objIWillDoItBtn = By.xpath("//*[contains(@text,'ll do it later, pinky swear')]");
    public static By objBorrowScreenTitle = By.xpath("//*[@text='Need fast cash or easy payments?']");
    public static By objTileHeader(int header) {
        return By.xpath("//*[@resource-id='com.tonik.mobile:id/Txt_header"+header+"']");
    }
    public static By objSubtitle(int subtitle){
        return By.xpath("//*[@resource-id='com.tonik.mobile:id/Txt_sub_title"+subtitle+"']");
    }
    public static By objReviewInProgress = By.xpath("//*[@text='Review in progress']");
    public static By objReviewInProgressInfo1 = By.xpath("//*[contains(@text,'We now have to review your application before we can open your Tonik')]");
    public static By objReviewInProgressInfo2 = By.xpath("//*[contains(@text,'This review will take around 1 working days')]");
    public static By objContactTonikCustomerCare = By.xpath("//*[contains(@text,'Customer Care')]");
    public static By objCloseTheApp = By.xpath("//*[@text='Close the app']");
    public static By objBlockedUSCitizenPage = By.xpath("//*[contains(@text,'This is really hard for us')]");
    public static By objBlockedUSCitizenInfo = By.xpath("//*[contains(@text,'we are unable to open a Tonik account')]");
    public static By objOTPInputField = By.xpath("(//android.widget.TextView[contains(@text,'We sent')]/parent::android.view.ViewGroup/parent::*/following-sibling::android.view.ViewGroup/child::*/child::android.widget.TextView)[1]");
    public static By objPageTitle = By.id("com.tonik.mobile:id/Main_title_txt");
    public static By objPageSubTitle = By.id("com.tonik.mobile:id/Sub_title_txt");
    public static By objBackBtn = By.id("com.tonik.mobile:id/Header_left_click");
    public static By objNextButton = By.xpath("//*[@resource-id='com.tonik.mobile:id/Custom_button_click']");
    public static By objPopupSubHeader = By.id("com.tonik.mobile:id/Popup_sub_header_txt");
    public static By objSendIcon = By.xpath("//*[@text='Send']/preceding-sibling::*/child::*");
    public static By objPayIcon = By.xpath("//*[@text='Pay']/preceding-sibling::*/child::*");
    public static By objDashboardCustomerCareIcon = By.xpath("//*[@resource-id='appbar-content']/following-sibling::android.view.ViewGroup");
    public static By objVirtualCard = By.xpath("//*[contains(@text,'Virtual Card')]/parent::*/parent::*/parent::*");
    public static By objDebitCard = By.xpath("//*[contains(@text,'Debit Card')]/parent::*/parent::*/parent::*");
    public static By objStashTile = By.xpath("//*[@text='Stashes']");
    public static By objStashTileInfo = By.xpath("//*[@text='Stashes']/following-sibling::android.widget.TextView");
    public static By objTimeDepositTile = By.xpath("//*[@text='Time Deposit']");
    public static By objTimeDepositTileInfo = By.xpath("//*[@text='Sit back, relax and see your time deposit savings grow at 6% interest per year.']");
    public static By objLoansTileInfo = By.xpath("//android.widget.TextView[@text=\"Find the right loan that fits your needs and wants. One day approval.\"]");
    public static By objCrossIcon = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ImageView");
}