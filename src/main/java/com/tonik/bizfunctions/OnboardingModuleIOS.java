package com.tonik.bizfunctions;

import com.driverInstance.DriverManager;
import com.tonik.pageObject.ContactUsPage;
import com.tonik.pageObject.LoginPage;
import com.tonik.pageObject.OnBoardingPage;
import com.tonik.pageObject.OnBoardingPageiOS;
import com.tonik.utility.ExtentReporter;
import com.tonik.utility.Utilities;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.tonik.utility.Utilities.*;

public class OnboardingModuleIOS extends BaseClass{
    public OnboardingModuleIOS() {
        super();
    }
    /**
     * Reusable method to Navigate to create page
     * @throws Exception
     */
    public void navigateToCreatePage() throws Exception {
        waitForElementToBePresent(OnBoardingPageiOS.objWelcomeScreen1Btn,5,"WelcomeScreen1Btn");
        if(verifyElementDisplayed(OnBoardingPageiOS.objWelcomeScreen1Btn)) {
            click(OnBoardingPageiOS.objWelcomeScreen1Btn, getTextVal(OnBoardingPageiOS.objWelcomeScreen1Btn, ": Onboarding Screen 1 button"));
            waitForElementToBePresent(OnBoardingPageiOS.objWelcomeScreen2Btn,5,"WelcomeScreen2Btn");
            click(OnBoardingPageiOS.objWelcomeScreen2Btn, getTextVal(OnBoardingPageiOS.objWelcomeScreen2Btn, ": Onboarding Screen 2 button"));
            click(OnBoardingPageiOS.objWelcomeScreen3Btn, getTextVal(OnBoardingPageiOS.objWelcomeScreen3Btn, ": Onboarding Screen 3 button"));
            click(OnBoardingPageiOS.objWelcomeScreen4Btn, getTextVal(OnBoardingPageiOS.objWelcomeScreen4Btn, ": Onboarding Screen 4 button"));
            click(OnBoardingPageiOS.objWelcomeScreen5Btn, getTextVal(OnBoardingPageiOS.objWelcomeScreen5Btn, ": Onboarding Screen 5 button"));
        }
        if(verifyElementDisplayed(OnBoardingPageiOS.objPictureAccessPopupIos)){
            verifyElementPresent((OnBoardingPageiOS.objAllowAccessAllPhotosIos),getTextVal(OnBoardingPageiOS.objAllowAccessAllPhotosIos, ": Take Picture Permission popup"));
            verifyElementPresentAndClick(OnBoardingPageiOS.objWhileUsingAppIos,getTextVal(OnBoardingPageiOS.objWhileUsingAppIos, ": Button"));
        }
    }

    /**
     * Reusable method to update date of birth
     */
    public static void updateDateOfBirth() {
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 0);
        sequence.addAction(finger1.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), 309, 385));
        sequence.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(new Pause(finger1, Duration.ofMillis(1000))); // Pause for 1 second
        sequence.addAction(finger1.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), 309, 538));
        sequence.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        DriverManager.getAppiumDriver().perform(Arrays.asList(sequence));
    }
    /**
     * Method to verify contact_us page UI validation
     * @throws Exception
     */
    public void contactUsPageUI() throws Exception {
        waitForElementToBePresent(OnBoardingPageiOS.objGetInTouchWithCustomerCarePage,5,"Get in touch page");
        verifyElementPresent(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader),getTextVal(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader)," : Page Header"));
        assertionValidation(getText(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader)),propertyFileReader("GetInTouchPageTitle","ContactUs"),"Page header");
        assertionValidation(getText(ContactUsPage.getByOSType(platform,ContactUsPage.objContactUsInfo)),propertyFileReader("ContactUsInfo","ContactUs"),"Info");
        containsValidation(getText(ContactUsPage.getByOSType(platform,ContactUsPage.objEmailId)),propertyFileReader("MailId","ContactUs"),"Mail");
        assertionValidation(getText(ContactUsPage.getByOSType(platform,ContactUsPage.objChatWithUs)),propertyFileReader("ChatWithUs","contactUs"),": Option");
        assertionValidation(getText(ContactUsPage.getByOSType(platform,ContactUsPage.objContactNumber)),propertyFileReader("ContactNumber","contactUs"),": Option");
        assertionValidation(getText(ContactUsPage.getByOSType(platform,ContactUsPage.objFrequentlyAskedQuestion)),propertyFileReader("FrequentlyAskedQuestion","contactUs"),": Option");
        verifyElementPresent(ContactUsPage.getByOSType(platform,ContactUsPage.objBackBtn),getTextVal(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader)," : Page Back button"));
    }
    /**
     * Reusable method to validate UI of Create Account page
     * @throws Exception
     */
    public void createTonikAccountPageUIValidation() throws Exception {
        waitForElementToBePresent(OnBoardingPageiOS.objCreateAccountPage,5,"Create account page");
        if (verifyElementDisplayed(OnBoardingPageiOS.objCreateAccountPage)) {
            verifyElementPresent(OnBoardingPageiOS.objPageTitle, getTextVal(OnBoardingPageiOS.objPageTitle, ": Page Title"));
            assertionValidation(getText(OnBoardingPageiOS.objPageTitle), propertyFileReader("CreateAccountPageTitle","onboarding"),": Page Title");
            verifyElementPresent(OnBoardingPageiOS.objPageSubTitle,getTextVal(OnBoardingPageiOS.objPageSubTitle,": Page Subtitle"));
            assertionValidation(getText(OnBoardingPageiOS.objPageSubTitle), propertyFileReader("CreatePageAccountSubtitle","onboarding"),": page Subtitle");
            verifyElementPresent(OnBoardingPageiOS.objMobileNumberInputField,"Mobile number input field");
            verifyElementPresent(OnBoardingPageiOS.objTickSymbol,"Tick Symbol");
            assertionValidation(getText(OnBoardingPageiOS.objAlreadyHaveAccount),propertyFileReader("AlreadyHaveAnAccountTxt","onboarding"),": Text");
            assertionValidation(getText(OnBoardingPageiOS.objLoginHereLink),propertyFileReader("LoginHereLink","onboarding"),": Link");
            verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objNextBtn),getTextVal(OnBoardingPageiOS.objNextBtn,": Button"));
            verifyElementPresent(OnBoardingPageiOS.objCustomerCareIconLoginScreen,"Customer care icon");
        }
    }
    /**
     * Reusable method to validate Customer care icon and UI
     * @throws Exception
     */
    public void customerCareIconValidation() throws Exception {
        if(verifyElementDisplayed(OnBoardingPageiOS.objCustomerCareIcon)) {
            click(OnBoardingPageiOS.objCustomerCareIcon, "Customer care icon");
        }else{
            click(OnBoardingPageiOS.objCreateAPasswordCustomerCareIcon, "Customer care icon");
        }
        waitForElementToBePresent(OnBoardingPageiOS.objGetInTouchWithCustomerCarePage,5,"Get in touch page");
        verifyElementPresent(OnBoardingPageiOS.objPageHeader,getTextVal(OnBoardingPageiOS.objPageHeader," : Page Header"));
        contactUsPageUI();
        click(LoginPage.getByOSType(platform,LoginPage.objBackIcon),"Get in touch back button");
    }
    /**
     * Reusable method to accept terms and condition
     * @throws Exception
     */
    public void acceptTermsAndCondition() throws Exception {
        waitForElementToBePresent(OnBoardingPageiOS.objAgreeAndContinueBtn,10,"Agree and continue");
        if(verifyElementDisplayed(OnBoardingPageiOS.objAgreeAndContinueBtn)) {
            verifyElementPresent(OnBoardingPageiOS.objPageTitle, getTextVal(OnBoardingPageiOS.objPageTitle, ": Page Title"));
            verifyElementPresentAndClick(OnBoardingPageiOS.objTermsAndConditionInfoCheckBox, "Terms and Condition check Box");
            verifyElementPresentAndClick(OnBoardingPageiOS.objAgreeAndContinueBtn, getTextVal(OnBoardingPageiOS.objAgreeAndContinueBtn, ": Button"));
        }
    }
    /**
     * Reusable method to accept Terms and condition and Privacy policy
     * @throws Exception
     */
    public void acceptTermsAndConditionAndPrivacyPolicy() throws Exception {
        waitForElementToBePresent(OnBoardingPageiOS.objAgreeAndContinueBtn,5,"Agree and continue");
        if(verifyElementDisplayed(OnBoardingPageiOS.objAgreeAndContinueBtn)) {
            acceptTermsAndCondition();
            acceptDataPrivacyStatement();
            enterOTP();
        }
    }
    /**
     * Reusable method to accept data privacy statement
     * @throws Exception
     */
    public void acceptDataPrivacyStatement() throws Exception {
        waitForElementToBePresent(OnBoardingPageiOS.objDataPrivacyStatementRadioBtn,10,"Agree and continue");
        if(verifyElementDisplayed(OnBoardingPageiOS.objDataPrivacyStatementRadioBtn)) {
            verifyElementPresent(OnBoardingPageiOS.objPageTitle, getTextVal(OnBoardingPageiOS.objPageTitle, ": Page Title"));
            verifyElementPresentAndClick(OnBoardingPageiOS.objDataPrivacyStatementRadioBtn, "Data Privacy statement check Box");
            verifyElementPresentAndClick(OnBoardingPageiOS.objAgreeAndContinueBtn, getTextVal(OnBoardingPageiOS.objAgreeAndContinueBtn, ": Button"));
        }
    }
    /**
     * Reusable method to generate Random OTP
     * @return
     */
    public String generateRandomOTP(){
        Random rnd = new Random();
        int randomNumber = rnd.nextInt(999999);
        return String.valueOf(randomNumber);
    }
    /**
     * Reusable method to Enter OTP
     * @throws Exception
     */
    public void enterOTP() throws Exception {
        waitForElementToBePresent(OnBoardingPageiOS.objReSendOTP,5,"Enter OTP");
        if(verifyElementDisplayed(OnBoardingPageiOS.objEnterOTP)) {
            verifyElementPresent(OnBoardingPageiOS.objPageTitle, getTextVal(OnBoardingPageiOS.objPageHeader, " : Page Title"));
            assertionValidation(getText(OnBoardingPageiOS.objPageTitle), propertyFileReader("EnterOTP", "onboarding"), ": Title");
            verifyElementPresentAndClick(OnBoardingPageiOS.objOTPInputField("1"), "OTP Input field");
            waitTime(3000);//wait time Required here
            DriverManager.getAppiumDriver().getKeyboard().sendKeys(generateRandomOTP());
            logger.info("Entered Random OTP");
            ExtentReporter.extentLoggerPass("", "Entered Random OTP");
        }
    }
    /**
     * Reusable method to Select ID and Scan
     * @param selectId
     * @throws Exception
     */
    public void selectIDAndScan(By selectId) throws Exception {
        if(verifyElementDisplayed(OnBoardingPageiOS.objIDSelectionPageTile)){
            verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(OnBoardingPageiOS.objPageHeader," : Page Title"));
            containsValidation(getText(OnBoardingPageiOS.objPageTitle),propertyFileReader("IDSelectionPageTile","onboarding"),": Title");
            verifyElementPresentAndClick(selectId,getTextVal(selectId,": Id selected"));
            click(OnBoardingPageiOS.objLetScanThisIDBtn,getTextVal(OnBoardingPageiOS.objLetScanThisIDBtn,": Button"));
            waitUntilElementVisible_NoCustomMessage(OnBoardingPageiOS.objGotAClearScanTitle);
            verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(OnBoardingPageiOS.objPageHeader," : Page Title"));
            assertionValidation(getText(OnBoardingPageiOS.objPageTitle),propertyFileReader("GotAClearScan","onboarding"),": Title");
            click(OnBoardingPageiOS.objSubmitFrontIDScanBtn,getTextVal(OnBoardingPageiOS.objSubmitFrontIDScanBtn,": Button"));
            if(verifyElementDisplayed(OnBoardingPageiOS.objSubmitBackIDScanBtn)) {
                click(OnBoardingPageiOS.objSubmitBackIDScanBtn, getTextVal(OnBoardingPageiOS.objSubmitBackIDScanBtn, ": Button"));
            }
            waitForElementToBePresent(OnBoardingPageiOS.objDidWeGetItRightTitle,60,"Did we get it right page");
            verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader)," : Page Title"));
            assertionValidation(getText(OnBoardingPageiOS.objPageTitle),propertyFileReader("DidWeGetItRightTitle","onboarding"),": Title");
            click(OnBoardingPageiOS.objLooksGoodBtn,getTextVal(OnBoardingPageiOS.objLooksGoodBtn,": Button"));
        }
    }
    /**
     * Reusable method to button need to tap
     * @throws Exception
     */
    public void buttonNeedToTapValidation() throws Exception {
        waitForElementToBePresent(OnBoardingPageiOS.objButtonToTapBtn,10,"Button to tap");
        if(verifyElementDisplayed(OnBoardingPageiOS.objButtonToTapBtn)) {
            click(OnBoardingPageiOS.objButtonToTapBtn, getTextVal(OnBoardingPageiOS.objButtonToTapBtn, ": Button"));
        }
    }
    /**
     * Reusable method to update signature and click on next button
     * @throws Exception
     */
    public void updateSignatureAndNext() throws Exception {
        waitForElementToBePresent(OnBoardingPageiOS.objLetsSealTheDealPage,5,"Let's seal the deal page");
        verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(OnBoardingPageiOS.objPageTitle,": title"));
        containsValidation(getText(OnBoardingPageiOS.objPageTitle), propertyFileReader("LetsSealTheDealPage", "onboarding"), ": Title");
        verifyElementPresent(OnBoardingPageiOS.objPageSubTitle,getTextVal(OnBoardingPageiOS.objPageSubTitle,": Page Subtitle"));
        assertionValidation(getText(OnBoardingPageiOS.objPageSubTitle), propertyFileReader("LetsSealTheDealPageSubtitle","onboarding"),": page Subtitle");
        updateSignature();
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn,getTextVal(OnBoardingPageiOS.objNextBtn,": button"));
    }
    /**
     * Reusable method to enter mobile number
     * @throws Exception
     */
    public void enterMobileNumber() throws Exception {
        waitForElementToBePresent(OnBoardingPageiOS.objMobileNumberInputField,5,"Mobile number input field");
        click(OnBoardingPageiOS.objMobileNumberInputField,"Onboarding input text field");
        type(OnBoardingPageiOS.objMobileNumberInputField, propertyFileReader("ValidMobileNumber","onboarding"),"Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn, getTextVal(OnBoardingPageiOS.objNextBtn, ": Button"));
    }
    /**
     * Reusable method to Input place of birth and continue
     * @throws Exception
     */
    public void inputPlaceOfBirthAndContinue() throws Exception {
        swipe("UP",1);
        if(verifyElementDisplayed(OnBoardingPageiOS.objLetsGetToKnowYou)) {
            verifyElementPresent(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle, getTextVal(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle, ": title"));
            containsValidation(getText(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle), propertyFileReader("AutoDetailsPopulatedPageTitle", "onboarding"), ": title");
            type(OnBoardingPageiOS.objPlaceOfBirth,propertyFileReader("ValidPlaceOfBirth","onboarding"),"Place of birth input field");
            verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
        }
    }
    /**
     * Reusable method to navigate to Zip code page
     * @param id
     * @throws Exception
     */
    public void navigateToZipCode(By id) throws Exception {
        navigateToCreatePage();
        if(verifyElementDisplayed(OnBoardingPageiOS.objAgreeAndContinueBtn)) {
            acceptTermsAndConditionAndPrivacyPolicy();
        }
        selectIDAndScan(id);
        buttonNeedToTapValidation();
        inputPlaceOfBirthAndContinue();
    }
    /**
     * Reusable method to Apply zip code
     * @param zipCode
     * @throws Exception
     */
    public void applyZipCode(String zipCode) throws Exception {
        waitForElementToBePresent(OnBoardingPageiOS.objZipCodeInputField, 10, "Zip code page");
        verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader)," : Page Title"));
        assertionValidation(getText(OnBoardingPageiOS.objPageTitle),propertyFileReader("YourPlaceRightPage","onboarding"),": Title");
        type(OnBoardingPageiOS.objZipCodeInputField,zipCode,"Zip code input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objApplyZipCode,getTextVal(OnBoardingPageiOS.objApplyZipCode,": button"));
    }
    /**
     * Reusable method to select Barangay
     * @throws Exception
     */
    public void selectBarangay() throws Exception {
        swipe("UP",1);
        verifyElementPresentAndClick(OnBoardingPageiOS.objBarangay,getTextVal(OnBoardingPageiOS.objBarangay,": field"));
        if(verifyElementDisplayed(OnBoardingPageiOS.objBarangay)) {
            click(OnBoardingPageiOS.objBarangay, getTextVal(OnBoardingPageiOS.objBarangay, ": field"));
        }
        waitForElementToBePresent(OnBoardingPageiOS.objBarangayDisplayed, 5, "Barangay");
        verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(OnBoardingPageiOS.objPageHeader," : Page Title"));
        assertionValidation(getText(OnBoardingPageiOS.objPageTitle),propertyFileReader("FindYourBarangay","onboarding"),": Title");
        type(OnBoardingPageiOS.objBarangaySearchField,propertyFileReader("SanAndreBarangay","onboarding"),": Barangay search field");
        verifyElementPresent(OnBoardingPageiOS.objBarangay("2"),getTextVal(OnBoardingPageiOS.objBarangay("2"),": Barangay"));
        containsValidation(getText(OnBoardingPageiOS.objBarangay("2")),propertyFileReader("SanAndreBarangay","onboarding"),"Barangay");
        verifyElementPresentAndClick(OnBoardingPageiOS.objBarangay("2"),getTextVal(OnBoardingPageiOS.objBarangay("2"),": Barangay"));
    }
    /**
     * Reusable method to update what do you do
     * @throws Exception
     */
    public void updateWhatDoYouDo(String sourceOfFunds,String currentEmployment,String natureOfWork,String industry) throws Exception {
        waitForElementToBePresent(OnBoardingPageiOS.objSourceOfFundsInputField,5,"What do you do page");
        verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(OnBoardingPageiOS.objPageTitle," : Page Title"));
        verifyElementPresent(OnBoardingPageiOS.objPageSubTitle,getTextVal(OnBoardingPageiOS.objPageSubTitle," : Page Subtitle"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objSourceOfFundsInputField,"Source of Funds input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objSelectSourceOfFunds(sourceOfFunds),getTextVal(OnBoardingPageiOS.objSelectSourceOfFunds(sourceOfFunds),": Source of fund"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objCurrentEmploymentStatus,"Current Employment Status input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objSelectCurrentEmploymentStatus(currentEmployment),getTextVal(OnBoardingPageiOS.objSelectCurrentEmploymentStatus(currentEmployment),": Current Employment Status"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objNatureOfWork,"Nature Of Work input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objSelectNatureOfWork(natureOfWork),getTextVal(OnBoardingPageiOS.objSelectNatureOfWork(natureOfWork),": Nature Of Work"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objIndustry,"Industry input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objSelectIndustry(industry),getTextVal(OnBoardingPageiOS.objSelectIndustry(industry),": Industry"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
    }
    /**
     * Reusable method to select Us citizen or not and click on next button
     * @throws Exception
     */
    public void selectUSCitizenOrNotAndNext() throws Exception {
        if (verifyElementPresent(OnBoardingPageiOS.objFATCAPageTitle, getTextVal(OnBoardingPageiOS.objFATCAPageTitle, ": title"))) {
            assertionValidation(getText(OnBoardingPageiOS.objPageTitle), propertyFileReader("FATCAPageTitle", "onboarding"), ": Title");
            verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn,getTextVal(OnBoardingPageiOS.objNextBtn,": button"));
        }
    }
    /**
     * Reusable method to validate login steps
     * @throws Exception
     */
    public void loginStepsValidation() throws Exception {
        if(verifyElementDisplayed(OnBoardingPageiOS.objHowToLoginPage)){
            verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(OnBoardingPageiOS.objPageTitle," : Page Title"));
            assertionValidation(getText(OnBoardingPageiOS.objPageTitle),propertyFileReader("HowToLoginPage","onboarding"),": Title");
            assertionValidation(getText(OnBoardingPageiOS.objLoginStep1),propertyFileReader("LoginStep1","onboarding"),": Step 1");
            assertionValidation(getText(OnBoardingPageiOS.objLoginStep2),propertyFileReader("LoginStep2","onboarding"),": Step 2");
            assertionValidation(getText(OnBoardingPageiOS.objLoginStep3),propertyFileReader("LoginStep3","onboarding"),": Step 3");
            assertionValidation(getText(OnBoardingPageiOS.objLoginStep1Info),propertyFileReader("LoginStep1Info","onboarding"),": Step 1 info");
            assertionValidation(getText(OnBoardingPageiOS.objLoginStep2Info),propertyFileReader("LoginStep2Info","onboarding"),": Step 2 info");
            assertionValidation(getText(OnBoardingPageiOS.objLoginStep3Info),propertyFileReader("LoginStep3Info","onboarding"),": Step 3 info");
            verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
        }
    }
    /**
     * Reusable method to create password and Continue
     * @throws Exception
     */
    public void createPasswordAndContinue() throws Exception {
        waitForElementToBePresent(OnBoardingPageiOS.objPasswordField,5,"Password field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objPasswordField,"Password field");
        verifyElementPresent(OnBoardingPageiOS.objPasswordSuggestionPopup,getTextVal(OnBoardingPageiOS.objPasswordSuggestionPopup,": Password suggestion popup"));
        containsValidation(getText(OnBoardingPageiOS.objPasswordSuggestionPopup),propertyFileReader("PasswordSuggestionPopup","onboarding"),": Password suggestion popup");
        if (verifyElementDisplayed(OnBoardingPageiOS.objPasswordSuggestion)) {
            List<MobileElement> values = DriverManager.getAppiumDriver().findElements(OnBoardingPageiOS.objPasswordSuggestion);
            for (int passwordSuggestion = 1; passwordSuggestion < values.size(); passwordSuggestion++) {
                assertionValidation(getText(OnBoardingPageiOS.objPasswordSuggestion(passwordSuggestion)), propertyFileReader("PasswordSuggestion"+ passwordSuggestion,"onboarding"), ": Password rule");
            }
        }
        waitTime(5000);//waiting to get Got It button enabling, doesn't have proper attribute.
        verifyElementPresentAndClick(OnBoardingPageiOS.objGotItBtn,getTextVal(OnBoardingPageiOS.objGotItBtn,": button"));
        type(OnBoardingPageiOS.objPasswordInputField,propertyFileReader("ValidPassword","onboarding"),": Password field");
        type(OnBoardingPageiOS.objConfirmPasswordField,propertyFileReader("ValidPassword","onboarding"),": Confirm Password field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
    }
    /**
     * Reusable method to validate Dashboard welcome screen
     * @throws Exception
     */
    public void dashboardWelcomeScreenValidation() throws Exception {
        waitForElementToBePresent(OnBoardingPageiOS.objDashboardWelcomeScreen,5,"Dashboard welcome screen");
        if(verifyElementDisplayed(OnBoardingPageiOS.objDashboardWelcomeScreen)) {
            verifyElementPresent(OnBoardingPageiOS.objDashboardWelcomeScreen, getTextVal(OnBoardingPageiOS.objDashboardWelcomeScreen, ": Welcome Screen"));
            assertionValidation(getText(OnBoardingPageiOS.objDashboardWelcomeScreen), propertyFileReader("DashBoardWelcomeScreen", "onboarding"), ": Welcome screen");
            assertionValidation(getText(OnBoardingPageiOS.objWelcomeInfo), propertyFileReader("WelcomeInfo", "onboarding"), ": Welcome Info");
            assertionValidation(getText(OnBoardingPageiOS.objWelcomeInfo), propertyFileReader("WelcomeInfo", "onboarding"), ": Welcome Info");
            assertionValidation(getText(OnBoardingPageiOS.objWelcomeInfo), propertyFileReader("WelcomeInfo", "onboarding"), ": Welcome Info");
            verifyElementPresent(OnBoardingPageiOS.objBorrow, getTextVal(OnBoardingPageiOS.objBorrow, "; tile"));
            swipe("UP", 2);
            verifyElementPresent(OnBoardingPageiOS.objBorrowLearnMore, getTextVal(OnBoardingPageiOS.objBorrowLearnMore, ": button"));
            assertionValidation(getText(OnBoardingPageiOS.objBorrowInfo), propertyFileReader("BorrowInfo", "onboarding"), ": Borrow Info");
            verifyElementPresent(OnBoardingPageiOS.objSave, getTextVal(OnBoardingPageiOS.objSave, "; tile"));
            verifyElementPresent(OnBoardingPageiOS.objSaveLearnMore, getTextVal(OnBoardingPageiOS.objSaveLearnMore, ": button"));
            assertionValidation(getText(OnBoardingPageiOS.objSaveInfo), propertyFileReader("SaveInfo", "onboarding"), ": Borrow Info");
            assertionValidation(getText(OnBoardingPageiOS.objExploreNow), propertyFileReader("ExploreNowBtn", "onboarding"), ": button");
        }
    }
    /**
     * Reusable method to validate barrow screen UI
     * @throws Exception
     */
    public void borrowScreenUIValidation() throws Exception {
        if(verifyElementDisplayed(OnBoardingPageiOS.objBorrowScreenTitle)){
            verifyElementPresent(OnBoardingPageiOS.objBorrowScreenTitle,getTextVal(OnBoardingPageiOS.objBorrowScreenTitle,": Page Title"));
            for(int header=1;header<=3;header++){
                assertionValidation(getText(OnBoardingPageiOS.objTileHeader(header)), propertyFileReader("TileHeader"+header,"onboarding"), ": Header "+header);
            }
            swipe("UP",2);
            for(int tileSubtitle=1;tileSubtitle<2;tileSubtitle++){
                containsValidation(getText(OnBoardingPageiOS.objSubtitle(tileSubtitle)),propertyFileReader("Subtitle"+tileSubtitle,"onboarding"),": Subtitle");
            }
        }
    }
    /**
     * Reusable method to update Email, Mother name, Banking relationship
     * @throws Exception
     */
    public void updateEmailMotherNameAndBankingRelationship() throws Exception {
        waitForElementToBePresent(OnBoardingPageiOS.objCoupleMoreThingsPage,5,"Couple more things page");
        verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(OnBoardingPageiOS.objPageTitle,": title"));
        assertionValidation(getText(OnBoardingPageiOS.objPageTitle), propertyFileReader("CoupleMoreThinsTitle", "onboarding"), ": Title");
        type(OnBoardingPageiOS.objEmailAddress,generateRandomEmail(),"Email Address field");
        type(OnBoardingPageiOS.objMotherMaidenName,propertyFileReader("ValidMaidenName","onboarding"),"Mother maiden field");
        click(OnBoardingPageiOS.objKeyboardDoneButton,"Done button");
        click(OnBoardingPageiOS.objDefineOurBankingRelationship,"Define our banking relationship");
        verifyElementPresentAndClick(OnBoardingPageiOS.objSelectBankingRelationship(propertyFileReader("BankingRelationship","onboarding")), getTextVal(OnBoardingPageiOS.objSelectBankingRelationship(propertyFileReader("BankingRelationship","onboarding")),": Banking Relationship"));
        verifyElementPresent(OnBoardingPageiOS.objMoreTransitionTextSwitchSlider,"More transition Switch slider");
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
    }
    /**
     * Method to Verify if user can open the Tonik App
     * @throws Exception
     */
    public void openTonikApp_TBD_OB_001() throws Exception {
        HeaderChildNode("TBD_OB_001, Onboarding - Verify if user can open the Tonik App");
        handlePopup();
        waitForElementToBePresent(OnBoardingPageiOS.objWelcomeScreen1,5,"Welcome screen 1");
        for(int welcomeScreen=1;welcomeScreen<6;welcomeScreen++){
            containsValidation(getText(OnBoardingPageiOS.objWelcomeScreen),propertyFileReader("WelcomeScreenMsg"+welcomeScreen,"onboarding"),": Welcome screen"+welcomeScreen);
            click(OnBoardingPageiOS.objButton(propertyFileReader("WelcomeScreenButton"+welcomeScreen,"onboarding")), getTextVal(OnBoardingPageiOS.objButton(propertyFileReader("WelcomeScreenButton"+welcomeScreen,"onboarding")),": button"));
        }
        if(verifyElementDisplayed(OnBoardingPageiOS.objPhotoAccessPopupIos)){
            verifyElementPresentAndClick(OnBoardingPageiOS.objWhileUsingAppIos,getTextVal(OnBoardingPageiOS.objWhileUsingAppIos,": Button"));
        }
        if(verifyElementDisplayed(OnBoardingPageiOS.objNotificationPopupIos)){
            verifyElementPresentAndClick(OnBoardingPageiOS.objAllowBtnIos,getTextVal(OnBoardingPageiOS.objAllowBtnIos,": Button"));
        }
        if(verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader)," : Page Header"))){
            assertionValidation(getText(OnBoardingPageiOS.objPageTitle),propertyFileReader("CreateAccountPageTitle","onboarding"),": Create Account page title");
            logger.info("TBD_OB_001, Onboarding - User can able to open the Tonik App is validated");
            extentLoggerPass("TBD_OB_001", "TBD_OB_001, Onboarding - User can able to open the Tonik App is validated");
        }
    }
    /**
     * Method to Verify if user will not be able to create Tonik account when entering invalid mobile number
     * @throws Exception
     */
    public void createTonikAccountUsingInvalidMobileNumber_TBD_OB_002() throws Exception {
        HeaderChildNode("TBD_OB_002, Onboarding - Verify if user will not be able to create Tonik account when entering invalid mobile number");
        navigateToCreatePage();
        createTonikAccountPageUIValidation();
        type(OnBoardingPageiOS.objMobileNumberInputField,propertyFileReader("InvalidMobileNumberLessThan10","onboarding"),"Mobile number input field");
        assertionValidation(getAttributValue("enabled", OnBoardingPageiOS.objNextButton),"false",": Enable Attribute value");
        clearField(OnBoardingPageiOS.objMobileNumberInputField,"Mobile input field");
        type(OnBoardingPageiOS.objMobileNumberInputField,propertyFileReader("InvalidMobileNumberStartingWith7","onboarding"),"Mobile number input field");
        assertionValidation(getAttributValue("enabled", OnBoardingPageiOS.objNextButton),"false",": Enable Attribute value");
        clearField(OnBoardingPageiOS.objMobileNumberInputField,"Mobile input field");
        type(OnBoardingPageiOS.objMobileNumberInputField,propertyFileReader("InvalidSameNumber1","onboarding"),"Mobile number input field");
        assertionValidation(getAttributValue("enabled", OnBoardingPageiOS.objNextButton),"false",": Enable Attribute value");
        clearField(OnBoardingPageiOS.objMobileNumberInputField,"Mobile input field");
        type(OnBoardingPageiOS.objMobileNumberInputField,propertyFileReader("InvalidSameNumber2","onboarding"),"Mobile number input field");
        assertionValidation(getAttributValue("enabled", OnBoardingPageiOS.objNextButton),"false",": Enable Attribute value");
        clearField(OnBoardingPageiOS.objMobileNumberInputField,"Mobile input field");
        type(OnBoardingPageiOS.objMobileNumberInputField,propertyFileReader("InvalidAlphaNumericMobileNumber","onboarding"),"Mobile number input field");
        assertionValidation(getAttributValue("enabled", OnBoardingPageiOS.objNextButton),"false",": Enable Attribute value");
        logger.info("TBD_OB_002, Onboarding - User will not be able to create Tonik account when entering invalid mobile number is validated");
        extentLoggerPass("TBD_OB_002", "TBD_OB_002, Onboarding - User will not be able to create Tonik account when entering invalid mobile number is validated");
    }
    /**
     * Method to Verify if user will be able to create a Tonik account when entering a valid mobile number
     * @throws Exception
     */
    public void createTonikAccountUsingValidMobileNumber_TBD_OB_003() throws Exception {
        HeaderChildNode("TBD_OB_003, Onboarding - Verify if user will be able to create a Tonik account when entering a valid mobile number");
        navigateToCreatePage();
        type(OnBoardingPageiOS.objMobileNumberInputField,propertyFileReader("OnboardingMobileNumber","onboarding"),"Mobile number input field");
        String sNextBtnEnableAttributeValue = getAttributValue("enabled", OnBoardingPageiOS.objNextButton);
        assertionValidation(getAttributValue("enabled", OnBoardingPageiOS.objNextButton),"true",": Enable Attribute value");
        if(sNextBtnEnableAttributeValue.contains("true")){
            logger.info("TBD_OB_003, Onboarding - User will be able to create a Tonik account when entering a valid mobile number is validated");
            extentLoggerPass("TBD_OB_003", "TBD_OB_003, Onboarding - User will be able to create a Tonik account when entering a valid mobile number is validated");
        }else{
            logger.info("TBD_OB_003, Onboarding - User will not be able to create a Tonik account when entering a valid mobile number is validated");
            extentLoggerFail("TBD_OB_003", "TBD_OB_003, Onboarding - User will not be able to create a Tonik account when entering a valid mobile number is validated");
        }
    }
    /**
     * Method to Verify if user can agree and accept the Terms and Conditions and Data Privacy Statement
     * @throws Exception
     */
    public void termsAndConditionDataPrivacyStatementValidation_TBD_OB_004() throws Exception {
        HeaderChildNode("TBD_OB_004, Onboarding - Verify if user can agree and accept the Terms and Conditions and Data Privacy Statement");
        navigateToCreatePage();
        type(OnBoardingPageiOS.objMobileNumberInputField, propertyFileReader("OnboardingMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn, getTextVal(OnBoardingPageiOS.objNextBtn, ": Button"));
        waitForElementToBePresent(OnBoardingPageiOS.objTermsAndConditionPageTitle,5,"Terms and condition");
        verifyElementPresent(OnBoardingPageiOS.objPageTitle, getTextVal(OnBoardingPageiOS.objPageTitle, ": Page Title"));
        assertionValidation(getText(OnBoardingPageiOS.objPageTitle), propertyFileReader("TermsAndConditionPageTitle", "onboarding"), ": Page Title");
        assertionValidation(getText(OnBoardingPageiOS.objTermsAndConditionInfo), propertyFileReader("TermsAndConditionPageInfo", "onboarding"), ": Terms and Condition info");
        assertionValidation(getText(OnBoardingPageiOS.objHouseRule1), propertyFileReader("HouseRule1", "onboarding"), ": House Rule 1");
        assertionValidation(getText(OnBoardingPageiOS.objHouseRule2), propertyFileReader("HouseRule2", "onboarding"), ": House Rule 2");
        assertionValidation(getText(OnBoardingPageiOS.objHouseRule3), propertyFileReader("HouseRule3", "onboarding"), ": House Rule 3");
        assertionValidation(getText(OnBoardingPageiOS.objHouseRule4), propertyFileReader("HouseRule4", "onboarding"), ": House Rule 4");
        assertionValidation(getText(OnBoardingPageiOS.objHouseRule5), propertyFileReader("HouseRule5", "onboarding"), ": House Rule 5");
        assertionValidation(getText(OnBoardingPageiOS.objHouseRule6), propertyFileReader("HouseRule6", "onboarding"), ": House Rule 6");
        assertionValidation(getText(OnBoardingPageiOS.objHouseRule7), propertyFileReader("HouseRule7", "onboarding"), ": House Rule 7");
        assertionValidation(getText(OnBoardingPageiOS.objReadTermsAndConditionLink), propertyFileReader("TermsAndConditionLink", "onboarding"), ": Link");
        click(OnBoardingPageiOS.objBackBtn, getTextVal(OnBoardingPageiOS.objPageTitle, ": Back Button"));
        waitForElementToBePresent(OnBoardingPageiOS.objMobileNumberInputField,5,"Mobile input field");
        verifyElementPresent(OnBoardingPageiOS.objPageTitle, getTextVal(OnBoardingPageiOS.objPageHeader, ": Page Title"));
        assertionValidation(getText(OnBoardingPageiOS.objPageTitle), propertyFileReader("CreateAccountPageTitle", "onboarding"), ": Page Title");
        type(OnBoardingPageiOS.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn, getTextVal(OnBoardingPageiOS.objNextBtn, ": Button"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objTermsAndConditionInfoCheckBox, "Terms and Condition check Box");
        assertionValidation(getText(OnBoardingPageiOS.objTermsAndConditionCheckBoxInfo), propertyFileReader("TermsAndConditionCheckBoxInfo", "onboarding"), ": Info");
        click(OnBoardingPageiOS.objAgreeAndContinueBtn, getTextVal(OnBoardingPageiOS.objAgreeAndContinueBtn, ": Button"));
        waitForElementToBePresent(OnBoardingPageiOS.objOurPrivacyMatters,10,"Privacy matters page");
        verifyElementPresent(OnBoardingPageiOS.objPageTitle, getTextVal(OnBoardingPageiOS.objPageTitle, ": Page Title"));
        assertionValidation(getText(OnBoardingPageiOS.objPageTitle), propertyFileReader("PrivacyPolicyPageTitle", "onboarding"), ": Page Title");
        assertionValidation(getText(OnBoardingPageiOS.objPrivacyMattersInfo), propertyFileReader("PrivacyPolicyInfo", "onboarding"), ": Privacy policy info");
        assertionValidation(getText(OnBoardingPageiOS.objPrivacyPolicy1), propertyFileReader("PrivacyPolicy1", "onboarding"), ": Privacy policy 1");
        assertionValidation(getText(OnBoardingPageiOS.objPrivacyPolicy2), propertyFileReader("PrivacyPolicy2", "onboarding"), ": Privacy policy 2");
        assertionValidation(getText(OnBoardingPageiOS.objPrivacyPolicy3), propertyFileReader("PrivacyPolicy3", "onboarding"), ": Privacy policy 3");
        assertionValidation(getText(OnBoardingPageiOS.objPrivacyPolicy4), propertyFileReader("PrivacyPolicy4", "onboarding"), ": Privacy policy 4");
        assertionValidation(getText(OnBoardingPageiOS.objPrivacyPolicy5), propertyFileReader("PrivacyPolicy5", "onboarding"), ": Privacy policy 5");
        assertionValidation(getText(OnBoardingPageiOS.objPrivacyPolicy6), propertyFileReader("PrivacyPolicy6", "onboarding"), ": Privacy policy 6");
        assertionValidation(getText(OnBoardingPageiOS.objPrivacyPolicy7), propertyFileReader("PrivacyPolicy7", "onboarding"), ": Privacy policy 7");
        assertionValidation(getText(OnBoardingPageiOS.objDataPrivacyStatementLink), propertyFileReader("DataPrivacyLink", "onboarding"), ": Link");
        containsValidation(getText(OnBoardingPageiOS.objTonikAppAccessOtherDeviceAppsMsg), propertyFileReader("TonikAppAccessOtherDeviceAppsMsg", "onboarding"), "App Access message");
        assertionValidation(getText(OnBoardingPageiOS.objDataPrivacyStatementInfo), propertyFileReader("DataPrivacyStatementInfo", "onboarding"), ": Info");
        verifyElementPresentAndClick(OnBoardingPageiOS.objBackBtn, getTextVal(OnBoardingPageiOS.objPageTitle, ": Back Button"));
        waitForElementToBePresent(OnBoardingPageiOS.objTermsAndConditionPageTitle,5,"Terms and condition");
        verifyElementPresent(OnBoardingPageiOS.objPageTitle, getTextVal(OnBoardingPageiOS.objPageHeader, ": Page Title"));
        assertionValidation(getText(OnBoardingPageiOS.objPageTitle), propertyFileReader("TermsAndConditionPageTitle", "onboarding"), ": Page Title");
        click(OnBoardingPageiOS.objAgreeAndContinueBtn, getTextVal(OnBoardingPageiOS.objAgreeAndContinueBtn, ": Button"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objDataPrivacyStatementRadioBtn, "Data Privacy statement check Box");
        click(OnBoardingPageiOS.objAgreeAndContinueBtn, getTextVal(OnBoardingPageiOS.objAgreeAndContinueBtn, ": Button"));
        waitForElementToBePresent(OnBoardingPageiOS.objEnterOTP,5,"Enter OTP page");
        if(verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(OnBoardingPageiOS.objPageHeader," : Page Title"))){
            assertionValidation(getText(OnBoardingPageiOS.objPageTitle),propertyFileReader("EnterOTP","onboarding"),": Title");
            logger.info("TBD_OB_004, Onboarding - User can agree and accept the Terms and Conditions and Data Privacy Statement and navigated to Enter OTP screen is validated");
            extentLoggerPass("TBD_OB_004", "TBD_OB_004, Onboarding - User can agree and accept the Terms and Conditions and Data Privacy Statement and navigated to Enter OTP screen is validated");
        }
    }
    /**
     * Method to Verify is user can input an invalid OTP
     * @throws Exception
     */
    public void invalidOTPValidation_TBD_OB_005() throws Exception {
        HeaderChildNode("TBD_OB_005, Onboarding - Verify is user can input an invalid OTP");
        navigateToCreatePage();
        waitForElementToBePresent(OnBoardingPageiOS.objMobileNumberInputField, 10,"Welcome Screen 1");
        type(OnBoardingPageiOS.objMobileNumberInputField, propertyFileReader("InvalidOTPMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn, getTextVal(OnBoardingPageiOS.objNextBtn, ": Button"));
        acceptTermsAndConditionAndPrivacyPolicy();
        hideKeyboard();
        if(verifyElementPresent(OnBoardingPageiOS.objInvalidOTPPopup,getTextVal(OnBoardingPageiOS.objInvalidOTPPopup,": Error message"))){
            assertionValidation(getText(OnBoardingPageiOS.objInvalidOTPPopup),propertyFileReader("InvalidOTPErrorMessage","onboarding"),": Error message");
            verifyElementPresent(OnBoardingPageiOS.objPopupBtn,getTextVal(OnBoardingPageiOS.objPopupBtn,": Popup button"));
            assertionValidation(getText(OnBoardingPageiOS.objPopupBtn),propertyFileReader("OkButton","onboarding"),": Popup button");
            logger.info("TBD_OB_005, Onboarding - User can input an invalid OTP, Error message is validated");
            extentLoggerPass("TBD_OB_005", "TBD_OB_005, Onboarding - User can input an invalid OTP, Error message is validated");
        }
    }
    /**
     * Method to Verify is user can request another OTP
     * @throws Exception
     */
    public void resendOTPLinkValidation_TBD_OB_006() throws Exception {
        HeaderChildNode("TBD_OB_006, Onboarding - Verify is user can request another OTP");
        navigateToCreatePage();
        waitForElementToBePresent(OnBoardingPageiOS.objMobileNumberInputField, 60,"Welcome Screen 1");
        type(OnBoardingPageiOS.objMobileNumberInputField, propertyFileReader("OnboardingMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn, getTextVal(OnBoardingPageiOS.objNextBtn, ": Button"));
        acceptTermsAndCondition();
        acceptDataPrivacyStatement();
        waitForElementToBePresent(OnBoardingPageiOS.objEnterOTP,5,"Enter OTP page");
        verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(OnBoardingPageiOS.objPageTitle,": Page Title"));
        assertionValidation(getText(OnBoardingPageiOS.objPageTitle),propertyFileReader("EnterOTP","onboarding"),": Title");
        verifyElementPresent(OnBoardingPageiOS.objPageSubTitle,getTextVal(OnBoardingPageiOS.objPageSubTitle,": Page SubTitle"));
        containsValidation(getText(OnBoardingPageiOS.objPageSubTitle),propertyFileReader("EnterOTPPageSubTitle","onboarding"),": Title");
        String sResendOTPEnableAttributeValue = getAttributValue("enabled", OnBoardingPageiOS.objResendOTP);
        assertionValidation(getAttributValue("enabled", OnBoardingPageiOS.objResendOTPParentElement),"true",": Enable Attribute value");
        if(sResendOTPEnableAttributeValue.contains("true")){
            logger.info("TBD_OB_006, Onboarding - User can request another OTP is validated");
            extentLoggerPass("TBD_OB_006", "TBD_OB_006, Onboarding - User can request another OTP is validated");
        }else{
            logger.info("TBD_OB_006, Onboarding - User can't request another OTP is validated");
            extentLoggerFail("TBD_OB_006", "TBD_OB_006, Onboarding - User can't request another OTP is validated");
        }
    }
    /**
     * Method to Verify if user can max request OTP
     * @throws Exception
     */
    public void resendOTPMaxAttemptValidation_TBD_OB_007() throws Exception {
        HeaderChildNode("TBD_OB_007, Onboarding - Verify if user can max request OTP");
        navigateToCreatePage();
        waitForElementToBePresent(OnBoardingPageiOS.objMobileNumberInputField, 60,"Welcome Screen 1");
        type(OnBoardingPageiOS.objMobileNumberInputField, propertyFileReader("OnboardingMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn, getTextVal(OnBoardingPageiOS.objNextBtn, ": Button"));
        acceptTermsAndCondition();
        acceptDataPrivacyStatement();
        waitForElementToBePresent(OnBoardingPageiOS.objEnterOTP,5,"Enter OTP page");
        verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(OnBoardingPageiOS.objPageTitle,": Page Title"));
        assertionValidation(getText(OnBoardingPageiOS.objPageTitle),propertyFileReader("EnterOTP","onboarding"),": Title");
        for(int iteration=1;iteration<=5;iteration++){
            click(OnBoardingPageiOS.objResendOTP,getTextVal(OnBoardingPageiOS.objResendOTP,": Link"));
        }
        if(verifyElementPresent(OnBoardingPageiOS.objMaxOTPAttemptPopup,getTextVal(OnBoardingPageiOS.objMaxOTPAttemptPopup,": Max OTP attempt error message"))){
            assertionValidation(getText(OnBoardingPageiOS.objMaxOTPAttemptPopup),propertyFileReader("MaxOTPAttemptMessage","onboarding"),": Max OTP attempt error message");
            verifyElementPresent(OnBoardingPageiOS.objOkBtn,getTextVal(OnBoardingPageiOS.objOkBtn,": button"));
            logger.info("TBD_OB_007, Onboarding - User can request max 5 OTP attempts is validated");
            extentLoggerPass("TBD_OB_007", "TBD_OB_007, Onboarding - User can request max 5 OTP attempts is validated");
        }
    }
    /**
     * Method to Verify is user can be able to proceed after Segmentation 1
     * @throws Exception
     */
    public void proceedingToNextPageAfterSegmentation1Validation_TBD_OB_020() throws Exception {
        HeaderChildNode("TBD_OB_020, Onboarding - Verify is user can be able to proceed after Segmentation 1");
        navigateToCreatePage();
        waitForElementToBePresent(OnBoardingPageiOS.objMobileNumberInputField, 10,"Mobile number input field");
        type(OnBoardingPageiOS.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn, getTextVal(OnBoardingPageiOS.objNextBtn, ": Button"));
        acceptTermsAndConditionAndPrivacyPolicy();
        verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(OnBoardingPageiOS.objPageTitle,": Page Title"));
        containsValidation(getText(OnBoardingPageiOS.objPageTitle),propertyFileReader("IDSelectionPage","onboarding"),": Title");
        selectIDAndScan(OnBoardingPageiOS.objSSSID);
        buttonNeedToTapValidation();
        verifyElementPresent(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle,getTextVal(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle,": title"));
        containsValidation(getText(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle),propertyFileReader("AutoDetailsPopulatedPageTitle","onboarding"),": title");
        relaunchApp("ios");
        waitForElementToBePresent(OnBoardingPageiOS.objMobileNumberInputField,10,"Mobile number input field");
        type(OnBoardingPageiOS.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn, getTextVal(OnBoardingPageiOS.objNextBtn, ": Button"));
        buttonNeedToTapValidation();
        waitForElementToBePresent(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle,5,"AutoDetails Populated Page Title");
        if(verifyElementPresent(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle,getTextVal(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle,": page"))){
            logger.info("BD_OB_020, Onboarding - User can be able to proceed after Segmentation 1 directly after selecting valid ID");
            extentLoggerPass("BD_OB_020", "BD_OB_020, Onboarding - User can be able to proceed after Segmentation 1 directly after selecting valid ID");
        }
    }
    /**
     * Method to Verify if the auto-populated fields are First Name, Middle Name, Last Name, Gender and Date of Birth
     * @throws Exception
     */
    public void autoPopulatedPassportDetailsValidation_TBD_OB_022_TBD_OB_027() throws Exception {
        HeaderChildNode("TBD_OB_022, Onboarding - Verify if the auto-populated fields are First Name, Middle Name, Last Name, Gender and Date of Birth");
        waitForElementToBePresent(OnBoardingPageiOS.objMobileNumberInputField, 5,"Mobile number input field");
        type(OnBoardingPageiOS.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn, getTextVal(OnBoardingPageiOS.objNextBtn, ": Button"));
        buttonNeedToTapValidation();
        if(verifyElementPresent(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle,getTextVal(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle,": title"))){
            containsValidation(getText(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle),propertyFileReader("AutoDetailsPopulatedPageTitle","onboarding"),": title");
            verifyElementPresent(OnBoardingPageiOS.objAutoPopulatedFirstName,getTextVal(OnBoardingPageiOS.objAutoPopulatedFirstName,": Auto populated first name"));
            verifyElementPresent(OnBoardingPageiOS.objAutoPopulatedMiddleName,getTextVal(OnBoardingPageiOS.objAutoPopulatedMiddleName,": Auto populated middle name"));
            verifyElementPresent(OnBoardingPageiOS.objAutoPopulatedLastName,getTextVal(OnBoardingPageiOS.objAutoPopulatedLastName,": Auto populated last name"));
            verifyElementPresent(OnBoardingPageiOS.objAutoPopulatedGender,getTextVal(OnBoardingPageiOS.objAutoPopulatedGender,": Auto populated Gender"));
            verifyElementPresent(OnBoardingPageiOS.objAutoPopulatedDateOfBirth,getTextVal(OnBoardingPageiOS.objAutoPopulatedDateOfBirth,": Auto populated Date of  birth"));
            logger.info("TBD_OB_022, Onboarding - Auto-populated fields are First Name, Middle Name, Last Name, Gender and Date of Birth is validated");
            extentLoggerPass("TBD_OB_022", "TBD_OB_022, Onboarding - Auto-populated fields are First Name, Middle Name, Last Name, Gender and Date of Birth is validated");
        }
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
        waitForElementToBePresent(OnBoardingPageiOS.objAutoPopulatedCountryOfResidence,5,"Your Place Right Page");
        verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(OnBoardingPageiOS.objPageHeader," : Page Title"));
        assertionValidation(getText(OnBoardingPageiOS.objPageTitle),propertyFileReader("YourPlaceRightPage","onboarding"),": Title");
        verifyElementPresent(OnBoardingPageiOS.objPageSubTitle,getTextVal(OnBoardingPageiOS.objPageSubTitle,": Page Subtitle"));
        assertionValidation(getText(OnBoardingPageiOS.objPageSubTitle), propertyFileReader("YourPlaceRightSubtitle","onboarding"),": page Subtitle");
        String country= getText(OnBoardingPageiOS.objAutoPopulatedCountryOfResidence);
        System.out.println(country);
        containsValidation(getText(OnBoardingPageiOS.objAutoPopulatedCountryOfResidence), propertyFileReader("Country","onboarding"), ": Country of Residence");
        screencapture();
        assertionValidation(getText(OnBoardingPageiOS.objZipCode),propertyFileReader("ZipCode","onboarding"),": field");
        assertionValidation(getText(OnBoardingPageiOS.objApplyZipCode),propertyFileReader("ApplyZipCode","onboarding"),": button");
        assertionValidation(getText(OnBoardingPageiOS.objHowToFindZipCodeLink),propertyFileReader("HowToFindZipCodeLink","onboarding"),": Link");
        assertionValidation(getText(OnBoardingPageiOS.objProvince),propertyFileReader("Province","onboarding"),": field");
        assertionValidation(getText(OnBoardingPageiOS.objCityOrMunicipality),propertyFileReader("CityOrMunicipality","onboarding"),": field");
        assertionValidation(getText(OnBoardingPageiOS.objBarangay),propertyFileReader("Barangay","onboarding"),": field");
        swipe("UP",1);
        assertionValidation(getText(OnBoardingPageiOS.objNextBtn),propertyFileReader("NextButton","onboarding"),"Button");
        assertionValidation(getAttributValue("enabled", OnBoardingPageiOS.objNextButton),"false",": Enable Attribute value");
        assertionValidation(getText(OnBoardingPageiOS.objHouseUnitFlrNumber),propertyFileReader("HouseUnitFlrNumber","onboarding"),": field");
        screencapture();
        verifyElementPresentAndClick(OnBoardingPageiOS.objBackBtn,getTextVal(OnBoardingPageiOS.objPageTitle,": back button"));
        if(verifyElementPresent(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle, getTextVal(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle, ": title"))){
            containsValidation(getText(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle), propertyFileReader("AutoDetailsPopulatedPageTitle", "onboarding"), ": title");
            logger.info("TBD_OB_027, Onboarding - Country of residence auto-populated as 'Philippines' and Your place, right? page UI validated and back button functionality validated");
            extentLoggerPass("TBD_OB_027", "TBD_OB_027, Onboarding - Country of residence auto-populated as 'Philippines' and Your place, right? page UI validated and back button functionality validated");
        }
    }
    /**
     * Method to Verify if user can input / edit the Gender field
     * @throws Exception
     */
    public void inputOrEditInputGenderFieldValidation_TBD_OB_023() throws Exception {
        HeaderChildNode("TBD_OB_023, Onboarding - Verify if user can input / edit the Gender field");
        waitForElementToBePresent(OnBoardingPageiOS.objMobileNumberInputField, 10,"Welcome Screen 1");
        type(OnBoardingPageiOS.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn, getTextVal(OnBoardingPageiOS.objNextBtn, ": Button"));
        buttonNeedToTapValidation();
        waitUntilElementVisible_NoCustomMessage(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle);
        verifyElementPresent(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle,getTextVal(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle,": title"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objGenderInputField,"Gender input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objGenderDropdown("Male"),getTextVal(OnBoardingPageiOS.objGenderDropdown("Male"),": Gender dropdown element"));
        String country= getText(OnBoardingPageiOS.objAutoPopulatedGender);
        System.out.println(country);
        assertionValidation(getText(OnBoardingPageiOS.objAutoPopulatedGender), propertyFileReader("Gender","onboarding"), ": Gender dropdown element");
        if(getText(OnBoardingPageiOS.objAutoPopulatedGender).equals(propertyFileReader("Gender","onboarding"))){
            logger.info("TBD_OB_023, Onboarding - User edited Gender input field is updated and validated");
            extentLoggerPass("TBD_OB_023", "TBD_OB_023, Onboarding - User edited Gender input field is updated and validated");
        }
    }
    /**
     * Method to Verify if user can input / edit the Date of Birth field
     * @throws Exception
     */
    public void inputOrEditInputDateOfBirthFieldValidation_TBD_OB_024() throws Exception {
        HeaderChildNode("TBD_OB_024, Onboarding - Verify if user can input / edit the Date of Birth field");
        waitForElementToBePresent(OnBoardingPageiOS.objMobileNumberInputField, 10,"Welcome Screen 1");
        type(OnBoardingPageiOS.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn, getTextVal(OnBoardingPageiOS.objNextBtn, ": Button"));
        buttonNeedToTapValidation();
        waitUntilElementVisible_NoCustomMessage(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle);
        verifyElementPresent(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle,getTextVal(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle,": title"));
        containsValidation(getText(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle),propertyFileReader("AutoDetailsPopulatedPageTitle","onboarding"),": title");
        verifyElementPresent(OnBoardingPageiOS.objAutoPopulatedDateOfBirth,getTextVal(OnBoardingPageiOS.objAutoPopulatedDateOfBirth,": Auto populated Date of  birth"));
        String sAutoPopulatedDateOfBirth = getText(OnBoardingPageiOS.objAutoPopulatedDateOfBirth);
        click(OnBoardingPageiOS.objAutoPopulatedDateOfBirth,getTextVal(OnBoardingPageiOS.objAutoPopulatedDateOfBirth,": Auto populated Date of  birth"));
        updateDateOfBirth();
        verifyElementPresentAndClick(OnBoardingPageiOS.objCalendarOkBtn,getTextVal(OnBoardingPageiOS.objCalendarOkBtn,": Button"));
        verifyElementPresent(OnBoardingPageiOS.objAutoPopulatedDateOfBirth,getTextVal(OnBoardingPageiOS.objAutoPopulatedDateOfBirth,": Edited Date of  birth"));
        String sUpdatedDateOfBirth = getText(OnBoardingPageiOS.objAutoPopulatedDateOfBirth);
        if((sAutoPopulatedDateOfBirth.equals(sUpdatedDateOfBirth))==false){
            logger.info("TBD_OB_024, Onboarding - User edited Date of birth input field is updated and validated");
            extentLoggerPass("TBD_OB_024", "TBD_OB_024, Onboarding - User edited Date of birth input field is updated and validated");
        }
    }
    /**
     * Method to Verify if user can input the Place of Birth
     * @throws Exception
     */
    public void placeOfBirthInputFieldValidation_TBD_OB_025() throws Exception {
        HeaderChildNode("TBD_OB_025, Onboarding - Verify if user can input the Place of Birth");
        waitForElementToBePresent(OnBoardingPageiOS.objMobileNumberInputField, 5,"Welcome Screen 1");
        type(OnBoardingPageiOS.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn, getTextVal(OnBoardingPageiOS.objNextBtn, ": Button"));
        buttonNeedToTapValidation();
        verifyElementPresent(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle,getTextVal(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle,": title"));
        containsValidation(getText(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle),propertyFileReader("AutoDetailsPopulatedPageTitle","onboarding"),": title");
        verifyElementPresentAndClick(OnBoardingPageiOS.objGenderInputField,"Gender input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objGenderDropdown("Male"),getTextVal(OnBoardingPageiOS.objGenderDropdown("Male"),": Gender dropdown element"));
        assertionValidation(getText(OnBoardingPageiOS.objAutoPopulatedGender), propertyFileReader("Gender","onboarding"), ": Gender dropdown element");
        verifyElementPresent(OnBoardingPageiOS.objAutoPopulatedDateOfBirth,getTextVal(OnBoardingPageiOS.objAutoPopulatedDateOfBirth,": Auto populated Date of  birth"));
        swipe("UP",1);
        click(OnBoardingPageiOS.objPlaceOfBirth,"Place of birth");
        clearField(OnBoardingPageiOS.objPlaceOfBirth,"Place of birth");
        type(OnBoardingPageiOS.objPlaceOfBirth,propertyFileReader("InvalidPlaceOfBirth","onboarding"),"Place of birth input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
        waitForElementToBePresent(OnBoardingPageiOS.objInvalidPlaceOfBirth,5,"Invalid place of birth");
        click(OnBoardingPageiOS.objAutoPopulatedDateOfBirth,"date od birth");
        verifyElementPresent(OnBoardingPageiOS.objInvalidPlaceOfBirth,getTextVal(OnBoardingPageiOS.objInvalidPlaceOfBirth,": Error message"));
        assertionValidation(getText(OnBoardingPageiOS.objInvalidPlaceOfBirth),propertyFileReader("InvalidPlaceOfBirthErrorMsg","onboarding"),": Error message");
        clearField(OnBoardingPageiOS.objPlaceOfBirthInputField,"Place of birth input field");
        type(OnBoardingPageiOS.objPlaceOfBirth,propertyFileReader("MaxInputValuePlaceOfBirth","onboarding"),"Place of birth input field");
        int placeOfBirth = getText(OnBoardingPageiOS.objPlaceOfBirth).length();
        if(placeOfBirth==50){
            logger.info("Maximum input value for Place of birth is 50 character validated");
            extentLoggerPass("", "Maximum input value for Place of birth is 50 character validated");
        }else{
            logger.info("Maximum input value for Place of birth isn't 50 validated");
            extentLoggerFail("", "Maximum input value for Place of birth isn't 50 character validated");
        }
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
        hideKeyboard();
        if(verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(OnBoardingPageiOS.objPageHeader," : Page Title"))){
            assertionValidation(getText(OnBoardingPageiOS.objPageTitle),propertyFileReader("YourPlaceRightPage","onboarding"),": Title");
            logger.info("TBD_OB_025, Onboarding - User edited Date of birth input field is updated and validated");
            extentLoggerPass("TBD_OB_025", "TBD_OB_025, Onboarding - User edited Date of birth input field is updated and validated");
        }
    }
    /**
     * Method to Verify if user can input Alphanumeric Place of Birth
     * @throws Exception
     */
    public void placeOfBirthAlphaNumericInputFieldValidation_TBD_OB_026() throws Exception {
        HeaderChildNode("TBD_OB_026, Onboarding - Verify if user can input Alphanumeric Place of Birth");
        waitForElementToBePresent(OnBoardingPageiOS.objMobileNumberInputField, 5,"Welcome Screen 1");
        type(OnBoardingPageiOS.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn, getTextVal(OnBoardingPageiOS.objNextBtn, ": Button"));
        buttonNeedToTapValidation();
        verifyElementPresent(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle,getTextVal(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle,": title"));
        containsValidation(getText(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle),propertyFileReader("AutoDetailsPopulatedPageTitle","onboarding"),": title");
        assertionValidation(getText(OnBoardingPageiOS.objAutoPopulatedGender), propertyFileReader("Gender","onboarding"), ": Gender dropdown element");
        swipe("UP",1);
        click(OnBoardingPageiOS.objPlaceOfBirthInputField,"Place of birth input field");
        clearField(OnBoardingPageiOS.objPlaceOfBirthInputField,"Place of birth input field");
        type(OnBoardingPageiOS.objPlaceOfBirth,propertyFileReader("AlphaNumericPlaceOfBirth","onboarding"),"Place of birth input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
        click(OnBoardingPageiOS.objKeyboardDoneButton,"Done button");
        waitForElementToBePresent(OnBoardingPageiOS.objEnterAlphabetsErrorMessage,5,"Alphabets Error Message");
        if(verifyElementPresent(OnBoardingPageiOS.objEnterAlphabetsErrorMessage,getTextVal(OnBoardingPageiOS.objEnterAlphabetsErrorMessage,": Error message"))) {
            assertionValidation(getText(OnBoardingPageiOS.objEnterAlphabetsErrorMessage), propertyFileReader("InvalidAlphaNumericPlaceOfBirthInput", "onboarding"), ": Error message");
            logger.info("TBD_OB_026, Onboarding - User entered Alphanumeric input in Place of Birth field, and excepted error message is validated");
            extentLoggerPass("TBD_OB_026", "TBD_OB_026, Onboarding - User entered Alphanumeric input in Place of Birth field, and excepted error message is validated");
        }
    }
    /**
     * Method to Verify if user can access the How to find your zip code hyperlink
     * @throws Exception
     */
    public void howToFindYourZipCodeHyperlinkValidation_TBD_OB_028() throws Exception {
        HeaderChildNode("TBD_OB_028, Onboarding - Verify if user can access the How to find your zip code hyperlink");
        waitForElementToBePresent(OnBoardingPageiOS.objMobileNumberInputField, 5,"Welcome Screen 1");
        type(OnBoardingPageiOS.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn, getTextVal(OnBoardingPageiOS.objNextBtn, ": Button"));
        click(OnBoardingPageiOS.objButtonToTapBtn, getTextVal(OnBoardingPageiOS.objButtonToTapBtn, ": Button"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
        waitForElementToBePresent(OnBoardingPageiOS.objHowToFindZipCodeLink,5,"Enter OTP page");
        verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(OnBoardingPageiOS.objPageHeader," : Page Title"));
        assertionValidation(getText(OnBoardingPageiOS.objPageTitle),propertyFileReader("YourPlaceRightPage","onboarding"),": Title");
        waitForElementToBePresent(OnBoardingPageiOS.objHowToFindZipCodeLink,5,"Enter OTP page");
        verifyElementPresentAndClick(OnBoardingPageiOS.objHowToFindZipCodeLink,getTextVal(OnBoardingPageiOS.objHowToFindZipCodeLink,": Link"));
        click(OnBoardingPageiOS.objHowToFindZipCodeLink,getTextVal(OnBoardingPageiOS.objHowToFindZipCodeLink,": Link"));
        screencapture();
            logger.info("TBD_OB_028, Onboarding - User can access the How to find your zip code hyperlink and Web page navigation validated");
            extentLoggerPass("TBD_OB_028", "TBD_OB_028, Onboarding - User can access the How to find your zip code hyperlink and Web page navigation validated");
    }
    /**
     * Method to Verify if user can input invalid details in Zip Code field
     * @throws Exception
     */
    public void invalidZipCodeDetailsValidation_TBD_OB_029() throws Exception {
        HeaderChildNode("TBD_OB_029, Onboarding - Verify if user can input invalid details in Zip Code field");
        type(OnBoardingPageiOS.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn, getTextVal(OnBoardingPageiOS.objNextBtn, ": Button"));
        click(OnBoardingPageiOS.objButtonToTapBtn, getTextVal(OnBoardingPageiOS.objButtonToTapBtn, ": Button"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
        waitForElementToBePresent(OnBoardingPageiOS.objApplyZipCode,5,"Enter OTP page");
        applyZipCode(propertyFileReader("InvalidZipCode","onboarding"));
        if(verifyElementPresent(OnBoardingPageiOS.objInvalidZipCodeErrorMsg,getTextVal(OnBoardingPageiOS.objInvalidZipCodeErrorMsg,": Error message"))){
            assertionValidation(getText(OnBoardingPageiOS.objInvalidZipCodeErrorMsg),propertyFileReader("InvalidZipCodeErrorMsg","onboarding"),": Error message");
            logger.info("TBD_OB_029, Onboarding - User entered invalid details in Zip Code field and error message validated");
            extentLoggerPass("TBD_OB_029", "TBD_OB_029, Onboarding - User entered invalid details in Zip Code field and error message validated");
        }
    }
    /**
     * Method to Verify if user can input invalid details in Zip Code field
     * @throws Exception
     */
    public void validZipCodeDetailsValidation_TBD_OB_030_TBD_OB_031() throws Exception {
        HeaderChildNode("TBD_OB_030, Onboarding - Verify if user can input invalid details in Zip Code field");
        enterMobileNumber();
        click(OnBoardingPageiOS.objButtonToTapBtn, getTextVal(OnBoardingPageiOS.objButtonToTapBtn, ": Button"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
        waitForElementToBePresent(OnBoardingPageiOS.objApplyZipCode,5,"Enter OTP page");
        applyZipCode(propertyFileReader("ValidZipCode","onboarding"));
        if(verifyElementPresent(OnBoardingPageiOS.objProvinceInputField,getTextVal(OnBoardingPageiOS.objProvinceInputField,": Province"))){
            verifyElementPresent(OnBoardingPageiOS.objCityOrMunicipalityInputField,getTextVal(OnBoardingPageiOS.objCityOrMunicipalityInputField,": City / Municipality input field"));
            logger.info("TBD_OB_030, Onboarding - User can input valid details in Zip Code field and auto populated Province and City/Municipality validated");
            extentLoggerPass("TBD_OB_030", "TBD_OB_030, Onboarding - User can input valid details in Zip Code field and auto populated Province and City/Municipality validated");
            logger.info("TBD_OB_031, Onboarding - After clicking on Apply Zip Code, Auto populated Province and City/Municipality validated");
            extentLoggerPass("TBD_OB_031", "TBD_OB_031, Onboarding - After clicking on Apply Zip Code, Auto populated Province and City/Municipality validated");
        }
    }
    /**
     * Method to Verify if user can select Barangay if Barangay is not auto-populated
     * @throws Exception
     */
    public void barangaySelectionScreenValidation_TBD_OB_032() throws Exception {
        HeaderChildNode("TBD_OB_032, Onboarding - Verify if user can select Barangay if Barangay is not auto-populated");
        enterMobileNumber();
        click(OnBoardingPageiOS.objButtonToTapBtn, getTextVal(OnBoardingPageiOS.objButtonToTapBtn, ": Button"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
        waitForElementToBePresent(OnBoardingPageiOS.objApplyZipCode,5,"Enter OTP page");
        applyZipCode(propertyFileReader("ValidZipCode","onboarding"));
        swipe("UP",1);
        verifyElementPresentAndClick(OnBoardingPageiOS.objBarangay,getTextVal(OnBoardingPageiOS.objBarangay,": field"));
        verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(OnBoardingPageiOS.objPageHeader," : Page Title"));
        assertionValidation(getText(OnBoardingPageiOS.objPageTitle),propertyFileReader("FindYourBarangay","onboarding"),": Title");
        List<MobileElement> elements = DriverManager.getAppiumDriver().findElements(OnBoardingPageiOS.objBarangayDisplayed);
        for (MobileElement element : elements) {
             String elementText = element.getText();
             System.out.println("Element text: " + elementText);
            logger.info("Barangay : '" + elementText + "' is displayed");
                    ExtentReporter.extentLogger(" ", "Barangay : '" + elementText + "' is displayed");
             }
        type(OnBoardingPageiOS.objBarangaySearchField,propertyFileReader("SanAndreBarangay","onboarding"),": Barangay search field");
        verifyElementPresent(OnBoardingPageiOS.objBarangay("2"),getTextVal(OnBoardingPageiOS.objBarangay("2"),": Barangay"));
        containsValidation(getText(OnBoardingPageiOS.objBarangay("2")),propertyFileReader("SanAndreBarangay","onboarding"),"Barangay");
        String sBarangaySelected = getText(OnBoardingPageiOS.objBarangay("2"));
        System.out.println(sBarangaySelected);
        verifyElementPresentAndClick(OnBoardingPageiOS.objBarangay("2"),getTextVal(OnBoardingPageiOS.objBarangay("2"),": Barangay"));
        swipe("UP",1);
        String name = getText(OnBoardingPageiOS.objBarangayInputText);
        System.out.println(name);
        if(sBarangaySelected.equalsIgnoreCase(getText(OnBoardingPageiOS.objBarangayInputText))){
            logger.info("TBD_OB_032, Onboarding - User can select Barangay and selected Barangay is reflected in Zip code page");
            extentLoggerPass("TBD_OB_032", "TBD_OB_032, Onboarding - User can select Barangay and selected Barangay is reflected in Zip code page");
        }
    }
    /**
     * Method to Verify if the House/Unit/Flr #, Bldg, Blk or Lot#, Street field will be auto-populated of the ID
     * @throws Exception
     */
    public void autoPopulatedHouseOrStreetFieldValidation_TBD_OB_033() throws Exception {
        HeaderChildNode("TBD_OB_033, Onboarding - Verify if the House/Unit/Flr #, Bldg, Blk or Lot#, Street field will be auto-populated of the ID");
        enterMobileNumber();
        click(OnBoardingPageiOS.objButtonToTapBtn, getTextVal(OnBoardingPageiOS.objButtonToTapBtn, ": Button"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
        waitForElementToBePresent(OnBoardingPageiOS.objApplyZipCode,5,"Enter OTP page");
        applyZipCode(propertyFileReader("ValidZipCode","onboarding"));
        selectBarangay();
        swipe("UP",2);
        if(verifyElementPresent(OnBoardingPageiOS.objHouseUnitFlrNumberInputField,getTextVal(OnBoardingPageiOS.objHouseUnitFlrNumberInputField,": Auto-populated House/Unit/Flr #, Bldg, Blk or Lots"))){
            clearField(OnBoardingPageiOS.objHouseUnitFlrNumberInputField,"House Unit Flr Number Input Field");
            type(OnBoardingPageiOS.objHouseUnitFlrNumberInputField,propertyFileReader("HouseStreetName","onboarding"),"House Unit Flr Number Input Field");
            assertionValidation(getAttributValue("enabled", OnBoardingPageiOS.objNextButton),"true",": Enable Attribute value");
            click(OnBoardingPageiOS.objKeyboardDoneButton,"Done Button");
            int houseUnitFlrNumber = getText(OnBoardingPageiOS.objHouseUnitFlrData).length();
            System.out.println(houseUnitFlrNumber);
            if(houseUnitFlrNumber>5){
                logger.info("Minimum input value for House Unit Floor Number Input Field is more than 5 character validated");
                extentLoggerPass("", "Minimum input value for House Unit Floor Number Input Field is more than 5 character validated");
            }else{
                logger.info("Minimum input value for House Unit Floor Number Input Field is less than 5 character validated");
                extentLoggerPass("", "Minimum input value for House Unit Floor Number Input Field is less than 5 character validated");
            }
            logger.info("TBD_OB_033, Onboarding - House/Unit/Flr #, Bldg, Blk or Lot#, Street field auto-populated for the PHIL-ID is validated");
            extentLoggerPass("TBD_OB_033", "TBD_OB_033, Onboarding - House/Unit/Flr #, Bldg, Blk or Lot#, Street field auto-populated for the PHIL-ID is validated");
        }
    }
    /**
     * Method to Verify if user can edit / input in the House/Unit/Flr #, Bldg, Blk or Lot#, Street field
     * @throws Exception
     */
    public void inputOrEditInputDateOfBirthFieldValidation_TBD_OB_034() throws Exception {
        HeaderChildNode("TBD_OB_034, Onboarding - Verify if user can edit / input in the House/Unit/Flr #, Bldg, Blk or Lot#, Street field");
        enterMobileNumber();
        click(OnBoardingPageiOS.objButtonToTapBtn, getTextVal(OnBoardingPageiOS.objButtonToTapBtn, ": Button"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
        waitForElementToBePresent(OnBoardingPageiOS.objApplyZipCode,5,"Enter OTP page");
        applyZipCode(propertyFileReader("ValidZipCode","onboarding"));
        swipe("UP",2);
        selectBarangay();
        if(verifyElementDisplayed(OnBoardingPageiOS.objHouseUnitFlrNumberInputField)) {
            click(OnBoardingPageiOS.objHouseUnitFlrNumberInputField, "House or Street Input field");
            clearField(OnBoardingPageiOS.objHouseUnitFlrNumberInputField, "House or Street Input field");
        }
        actionSendKeys(OnBoardingPageiOS.objHouseUnitFlrNumberInputField,propertyFileReader("NewHouseOrFloorNumber","onboarding"),"House or Street Input field");
        assertionValidation(getText(OnBoardingPageiOS.objHouseUnitFlrNumberInputField),propertyFileReader("NewHouseOrFloorNumber","onboarding"),": House or Street Input field");
        assertionValidation(getAttributValue("enabled", OnBoardingPageiOS.objNextButton),"true",": Enable Attribute value");
        if(getAttributValue("enabled", OnBoardingPageiOS.objNextButton).contains("true")){
            logger.info("TBD_OB_034, Onboarding - User can edit / input in the House/Unit/Flr #, Bldg, Blk or Lot#, Street field and updated House field is validated");
            extentLoggerPass("TBD_OB_034", "TBD_OB_034, Onboarding - User can edit / input in the House/Unit/Flr #, Bldg, Blk or Lot#, Street field and updated House field is validated");
        }
    }
    /**
     * Method to Verify if user can select FATCA
     * @throws Exception
     */
    public void FACTAPageUIValidation_TBD_OB_039() throws Exception {
        HeaderChildNode("TBD_OB_039, Onboarding - Verify if user can select FATCA");
        enterMobileNumber();
        waitForElementToBePresent(OnBoardingPageiOS.objButtonToTapBtn,5,"Button to tap");
        buttonNeedToTapValidation();
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn,getTextVal(OnBoardingPageiOS.objNextBtn,": button"));
        updateWhatDoYouDo("Salary","Employed","Accountant/Auditor","Accounting/Bookkeeping");
        if (verifyElementPresent(OnBoardingPageiOS.objFATCAPageTitle,getTextVal(OnBoardingPageiOS.objFATCAPageTitle,": title"))) {
            assertionValidation(getText(OnBoardingPageiOS.objPageTitle), propertyFileReader("FATCAPageTitle", "onboarding"), ": Title");
            verifyElementPresent(OnBoardingPageiOS.objPageSubTitle, getTextVal(OnBoardingPageiOS.objPageSubTitle, ": Page Subtitle"));
            containsValidation(getText(OnBoardingPageiOS.objPageSubTitle), propertyFileReader("FATCAPageSubtitle", "onboarding"), ": Subtitle");
            assertionValidation(getText(OnBoardingPageiOS.objAUSACitizen), propertyFileReader("AUSACitizen", "onboarding"), ": option");
            assertionValidation(getText(OnBoardingPageiOS.objGreenCardHolder), propertyFileReader("GreenCardHolder", "onboarding"), ": option");
            assertionValidation(getText(OnBoardingPageiOS.objUSAResident), propertyFileReader("USAResident", "onboarding"), ": option");
            assertionValidation(getText(OnBoardingPageiOS.objNoneOfTheAbove), propertyFileReader("NoneOfTheAbove", "onboarding"), ": option");
            verifyElementPresent(OnBoardingPageiOS.objNextBtn,getTextVal(OnBoardingPageiOS.objNextBtn,": button"));
            assertionValidation(getAttributValue("enabled", OnBoardingPageiOS.objNextButton),"true",": Enable Attribute value");
            logger.info("TBD_OB_039, Onboarding - User can select FATCA page UI validated");
            extentLoggerPass("TBD_OB_039", "TBD_OB_039, Onboarding - User can select FATCA page UI validated");
        }
    }
    /**
     * Method to Verify if user can input existing email address
     * @throws Exception
     */
    public void inputExistingEmailValidation_TBD_OB_041() throws Exception {
        HeaderChildNode("TBD_OB_041, Onboarding - Verify if user can input existing email address");
        enterMobileNumber();
        buttonNeedToTapValidation();
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn,getTextVal(OnBoardingPageiOS.objNextBtn,": button"));
        updateWhatDoYouDo("Salary","Employed","Accountant/Auditor","Accounting/Bookkeeping");
        selectUSCitizenOrNotAndNext();
        waitForElementToBePresent(OnBoardingPageiOS.objEmailAddress,5,"Email Address");
        verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(OnBoardingPageiOS.objPageTitle,": title"));
        assertionValidation(getText(OnBoardingPageiOS.objPageTitle), propertyFileReader("CoupleMoreThinsTitle", "onboarding"), ": Title");
        type(OnBoardingPageiOS.objEmailAddress,propertyFileReader("ExistingEmailAddressInput","onboarding"),"Email Address field");
        String emailAddress = getText(OnBoardingPageiOS.objEmailAddressText);
        type(OnBoardingPageiOS.objMotherMaidenName,propertyFileReader("MotherMaidenName","onboarding"),"Mother Maiden Name field");
        click(OnBoardingPageiOS.objKeyboardDoneButton,"Done Button");
        click(OnBoardingPageiOS.objDefineOurBankingRelationship,"Define our banking relationship");
        verifyElementPresentAndClick(OnBoardingPageiOS.objSelectBankingRelationship(propertyFileReader("BankingRelationship","onboarding")), getTextVal(OnBoardingPageiOS.objSelectBankingRelationship(propertyFileReader("BankingRelationship","onboarding")),": Banking Relationship"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
        if(verifyElementPresent(OnBoardingPageiOS.objPopupSubHeader,getTextVal(OnBoardingPageiOS.objPopupSubHeader,": Error message"))){
            assertionValidation(getText(OnBoardingPageiOS.objPopupSubHeader),propertyFileReader("InvalidEmailAddress","onboarding"),": error message");
            verifyElementPresentAndClick(OnBoardingPageiOS.objOkBtn,getTextVal(OnBoardingPageiOS.objOkBtn,": button"));
            click(OnBoardingPageiOS.objEmailAddressText,"Email address input field");
            clearField(OnBoardingPageiOS.objEmailAddressText,"Email address input field");
            type(OnBoardingPageiOS.objEmailAddressText,propertyFileReader("ValidEmailAddress","onboarding"),"Email Address field");
            if(!emailAddress.equals(getText(OnBoardingPageiOS.objEmailAddressText))){
                logger.info("Email Address edited after getting error popup");
                extentLoggerPass("TBD_OB_041", "Email Address edited after getting error popup");
            }
            logger.info("TBD_OB_041, Onboarding - User entered existing email address, error message is displayed and able to edit Email address field");
            extentLoggerPass("TBD_OB_041", "TBD_OB_041, Onboarding - User entered existing email address, error message is displayed and able to edit Email address field");
        }
    }
    /**
     * Method to Verify if user can input invalid email address
     * @throws Exception
     */
    public void inputInvalidEmailValidation_TBD_OB_043_TBD_OB_044_TBD_OB_045() throws Exception {
        HeaderChildNode("TBD_OB_043_TBD_OB_044_TBD_OB_045, Onboarding - Verify if user can input invalid email address");
        enterMobileNumber();
        waitForElementToBePresent(OnBoardingPageiOS.objButtonToTapBtn,5,"Button to tap");
        buttonNeedToTapValidation();
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
        waitForElementToBePresent(OnBoardingPageiOS.objNextBtn,5,"Next button");
        click(OnBoardingPageiOS.objNextBtn,getTextVal(OnBoardingPageiOS.objNextBtn,": button"));
        updateWhatDoYouDo("Salary","Employed","Accountant/Auditor","Accounting/Bookkeeping");
        selectUSCitizenOrNotAndNext();
        waitForElementToBePresent(OnBoardingPageiOS.objEmailAddress,5,"Email Address");
        verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(OnBoardingPageiOS.objPageTitle,": title"));
        assertionValidation(getText(OnBoardingPageiOS.objPageTitle), propertyFileReader("CoupleMoreThinsTitle", "onboarding"), ": Title");
        type(OnBoardingPageiOS.objEmailAddress,propertyFileReader("InvalidEmailAddressInput","onboarding"),"Email Address field");
        click(OnBoardingPageiOS.objMotherMaidenName,"Mother maiden name field");
        if(verifyElementPresent(OnBoardingPageiOS.objInvalidEmailAddressErrorMessage,getTextVal(OnBoardingPageiOS.objInvalidEmailAddressErrorMessage,": Error message"))){
            assertionValidation(getText(OnBoardingPageiOS.objInvalidEmailAddressErrorMessage),propertyFileReader("InvalidEmailAddressErrorMsg","onboarding"),": Error message");
            logger.info("TBD_OB_043, Onboarding - User entered invalid email address, error message is displayed and validated");
            extentLoggerPass("TBD_OB_043", "TBD_OB_043, Onboarding -  User entered invalid email address, error message is displayed and validated");
        }
        click(OnBoardingPageiOS.objEmailAddressText,"Email address field");
        clearField(OnBoardingPageiOS.objEmailAddressText,"Email address field");
        click(OnBoardingPageiOS.objMotherMaidenName,"Mother maiden name field");
        verifyElementPresent(OnBoardingPageiOS.objBackBtn,"Email page back button");
        if(verifyElementPresent(OnBoardingPageiOS.objBlankEmailAddressErrorMessage,getTextVal(OnBoardingPageiOS.objBlankEmailAddressErrorMessage,": Error message"))){
            assertionValidation(getText(OnBoardingPageiOS.objBlankEmailAddressErrorMessage),propertyFileReader("BlankEmailAddress","onboarding"),": Error message");
            logger.info("TBD_OB_044, Onboarding - User kept blank Email address field and Email address required error message validated");
            extentLoggerPass("TBD_OB_044", "TBD_OB_044, Onboarding - User kept blank Email address field and Email address required error message validated");
        }
        click(OnBoardingPageiOS.objEmailAddress,"Email address field");
        clearField(OnBoardingPageiOS.objEmailAddress,"Email address field");
        String email = generateRandomEmail();
        type(OnBoardingPageiOS.objEmailAddressText,email,"Email Address field");
        click(OnBoardingPageiOS.objMotherMaidenName,"Mother maiden name field");
        String sEmailAddress = getText(OnBoardingPageiOS.objEmailAddressText);
        if(sEmailAddress.contains(propertyFileReader("EmailFormat","onboarding"))){
            logger.info("TBD_OB_045, Onboarding - User entered valid email address and accepted email format is validated");
            extentLoggerPass("TBD_OB_045", "TBD_OB_045, Onboarding - User entered valid email address and accepted email format is validated");
        }
    }
    /**
     * Method to Verify if user can input valid characters in Mother's Maiden name field
     * @throws Exception
     */
    public void validMotherMaidenNameValidation_TBD_OB_046() throws Exception {
        HeaderChildNode("TBD_OB_046, Onboarding - Verify if user can input valid characters in Mother's Maiden name field");
        enterMobileNumber();
        waitForElementToBePresent(OnBoardingPageiOS.objButtonToTapBtn,5,"Button to tap");
        buttonNeedToTapValidation();
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
        waitForElementToBePresent(OnBoardingPageiOS.objNextBtn,5,"Next button");
        click(OnBoardingPageiOS.objNextBtn,getTextVal(OnBoardingPageiOS.objNextBtn,": button"));
        updateWhatDoYouDo("Salary","Employed","Accountant/Auditor","Accounting/Bookkeeping");
        selectUSCitizenOrNotAndNext();
        waitForElementToBePresent(OnBoardingPageiOS.objEmailAddress,5,"Email Address");
        verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(OnBoardingPageiOS.objPageTitle,": title"));
        assertionValidation(getText(OnBoardingPageiOS.objPageTitle), propertyFileReader("CoupleMoreThinsTitle", "onboarding"), ": Title");
        type(OnBoardingPageiOS.objMotherMaidenName,propertyFileReader("InvalidMaidenName","onboarding"),"Mother maiden field");
        click(OnBoardingPageiOS.objEmailAddress,"Email address field");
        assertionValidation(getText(OnBoardingPageiOS.objInvalidMotherMaidenName),propertyFileReader("InvalidMotherMaidenNameErrorMsg","onboarding"),": Error message");
        click(OnBoardingPageiOS.objMotherMaidenNameText,"Mother maiden name field");
        clearField(OnBoardingPageiOS.objMotherMaidenNameText,"Mother maiden name field");
        type(OnBoardingPageiOS.objMotherMaidenNameText,propertyFileReader("ValidMaidenName","onboarding"),"Mother maiden field");
        click(OnBoardingPageiOS.objEmailAddress,"Email address field");
        int motherMaidenName = getText(OnBoardingPageiOS.objMotherMaidenNameText).length();
        if(motherMaidenName==50){
            logger.info("Maximum input value for Mother maiden name is 50 character validated");
            extentLoggerPass("", "Maximum input value for Mother maiden name is 50 character validated");
        }else{
            logger.info("Maximum input value for Mother maiden isn't 50 validated");
            extentLoggerFail("", "Maximum input value for Mother maiden isn't 50 character validated");
        }
        if(getText(OnBoardingPageiOS.objMotherMaidenNameText).equals(propertyFileReader("ValidMaidenName","onboarding"))){
            logger.info("TBD_OB_046, Onboarding - User can input valid characters in Mother's Maiden name field and validated");
            extentLoggerPass("TBD_OB_046", "TBD_OB_046, Onboarding - User can input valid characters in Mother's Maiden name field and validated");
        }
    }
    /**
     * Method to Verify if user can input alphanumeric characters in Mother's Maiden name field
     * @throws Exception
     */
    public void invalidMotherMaidenNameValidation_TBD_OB_047_BD_OB_048() throws Exception {
        HeaderChildNode("TBD_OB_047, Onboarding - Verify if user can input alphanumeric characters in Mother's Maiden name field");
        enterMobileNumber();
        waitForElementToBePresent(OnBoardingPageiOS.objButtonToTapBtn,5,"Button to tap");
        buttonNeedToTapValidation();
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
        waitForElementToBePresent(OnBoardingPageiOS.objNextBtn,5,"Next button");
        click(OnBoardingPageiOS.objNextBtn,getTextVal(OnBoardingPageiOS.objNextBtn,": button"));
        updateWhatDoYouDo("Salary","Employed","Accountant/Auditor","Accounting/Bookkeeping");
        selectUSCitizenOrNotAndNext();
        waitForElementToBePresent(OnBoardingPageiOS.objEmailAddress,5,"Email Address");
        verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(OnBoardingPageiOS.objPageTitle,": title"));
        assertionValidation(getText(OnBoardingPageiOS.objPageTitle), propertyFileReader("CoupleMoreThinsTitle", "onboarding"), ": Title");
        type(OnBoardingPageiOS.objMotherMaidenName,propertyFileReader("InvalidAlphaNumericMotherMaidenName","onboarding"),"Mother maiden field");
        click(OnBoardingPageiOS.objEmailAddress,"Email address field");
        click(OnBoardingPageiOS.objMotherMaidenNameText,"Mother maiden name field");
        click(OnBoardingPageiOS.objEmailAddress,"Email address field");
        if(verifyElementPresent(OnBoardingPageiOS.objInvalidMotherMaidenNameErrorMsg,getTextVal(OnBoardingPageiOS.objInvalidMotherMaidenNameErrorMsg,": error message"))){
            assertionValidation(getText(OnBoardingPageiOS.objInvalidMotherMaidenNameErrorMsg),propertyFileReader("InvalidMotherMaidenNameErrorMsg2","onboarding"),": Error message");
            logger.info("TBD_OB_047, Onboarding - User entered alphanumeric characters in Mother's Maiden name field and corresponding error message validated");
            extentLoggerPass("TBD_OB_047", "TBD_OB_047, Onboarding - User entered alphanumeric characters in Mother's Maiden name field and corresponding error message validated");
        }
        click(OnBoardingPageiOS.objMotherMaidenNameText,"Mother maiden name field");
        clearField(OnBoardingPageiOS.objMotherMaidenNameText,"Mother maiden name field");
        click(OnBoardingPageiOS.objEmailAddress,"Email address field");
        if(verifyElementPresent(OnBoardingPageiOS.objBlankMotherMaidenName,getTextVal(OnBoardingPageiOS.objBlankMotherMaidenName,": error message"))){
            assertionValidation(getText(OnBoardingPageiOS.objBlankMotherMaidenName),propertyFileReader("BlankMotherMaidenNameErrorMsg","onboarding"),": Error message");
            logger.info("BD_OB_048, Onboarding - User kept blank Mother maiden name field and Mother maiden names required error message validated");
            extentLoggerPass("BD_OB_048", "BD_OB_048, Onboarding - User kept blank Mother maiden name field and Mother maiden names required error message validated");
        }
    }
    /**
     * Method to Verify if user can select a banking relationship
     * @throws Exception
     */
    public void selectBankingRelationshipValidation_BD_OB_049() throws Exception {
        HeaderChildNode("BD_OB_049, Onboarding - Verify if user can select a banking relationship");
        enterMobileNumber();
        waitForElementToBePresent(OnBoardingPageiOS.objButtonToTapBtn,5,"Button to tap");
        buttonNeedToTapValidation();
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
        waitForElementToBePresent(OnBoardingPageiOS.objNextBtn,5,"Next button");
        click(OnBoardingPageiOS.objNextBtn,getTextVal(OnBoardingPageiOS.objNextBtn,": button"));
        updateWhatDoYouDo("Salary","Employed","Accountant/Auditor","Accounting/Bookkeeping");
        selectUSCitizenOrNotAndNext();
        waitForElementToBePresent(OnBoardingPageiOS.objMotherMaidenName,5,"Mother maiden name");
        verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(OnBoardingPageiOS.objPageTitle,": title"));
        assertionValidation(getText(OnBoardingPageiOS.objPageTitle), propertyFileReader("CoupleMoreThinsTitle", "onboarding"), ": Title");
        click(OnBoardingPageiOS.objDefineOurBankingRelationship,"Define your banking relationship  field");
        waitForElementToBePresent(OnBoardingPageiOS.objDefineBankingRelationshipPopup,5,"Define Banking Relationship Popup");
        assertionValidation(getText(OnBoardingPageiOS.objDefineBankingRelationshipPopup), propertyFileReader("DefineBankingRelationshipPopup", "onboarding"), ": Popup");
        assertionValidation(getText(OnBoardingPageiOS.objPurposeOfAccountTxt), propertyFileReader("PurposeOfAccountTxt", "onboarding"), ": text");
        assertionValidation(getText(OnBoardingPageiOS.objPersonalOption), propertyFileReader("PersonalOption", "onboarding"), ": option");
        assertionValidation(getText(OnBoardingPageiOS.objBusinessOption), propertyFileReader("BusinessOption", "onboarding"), ": option");
        assertionValidation(getText(OnBoardingPageiOS.objRemittancesOption), propertyFileReader("RemittancesOption", "onboarding"), ": option");
        if (verifyElementDisplayed(OnBoardingPageiOS.objPurposeOfAccount)) {
            List<MobileElement> values = DriverManager.getAppiumDriver().findElements(OnBoardingPageiOS.objPurposeOfAccount);
            for (int nPurpose = 1; nPurpose <= values.size(); nPurpose++) {
                String sPurpose = getText(OnBoardingPageiOS.objPurposeOfAccount(nPurpose));
                click(OnBoardingPageiOS.objPurposeOfAccount(nPurpose),getTextVal(OnBoardingPageiOS.objPurposeOfAccount(nPurpose),": purpose"));
                assertionValidation(sPurpose,getText(OnBoardingPageiOS.objDefineOurBankingRelationshipInputText),": Purpose");
                screencapture();
                click(OnBoardingPageiOS.objDefineOurBankingRelationshipInputText,"Define your banking relationship  field");
                waitForElementToBePresent(OnBoardingPageiOS.objDefineBankingRelationshipPopup,5,"Define Banking Relationship Popup");
            }
        }
        logger.info("BD_OB_049, Onboarding - User can select a banking relationship and reflected in A couple more things page");
        extentLoggerPass("BD_OB_049", "BD_OB_049, Onboarding - User can select a banking relationship and reflected in A couple more things page");
    }

    public void proceedingToNextPageAfterSegmentation2Validation_TBD_OB_042() throws Exception {
        HeaderChildNode("BD_OB_042, Onboarding - Verify if user can able to proceed after segmentation 2");
        enterMobileNumber();
        waitForElementToBePresent(OnBoardingPageiOS.objButtonToTapBtn,5,"Button to tap");
        buttonNeedToTapValidation();
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
        waitForElementToBePresent(OnBoardingPageiOS.objNextBtn,5,"Next button");
        click(OnBoardingPageiOS.objNextBtn,getTextVal(OnBoardingPageiOS.objNextBtn,": button"));
        updateWhatDoYouDo("Salary","Employed","Accountant/Auditor","Accounting/Bookkeeping");
        selectUSCitizenOrNotAndNext();
        updateEmailMotherNameAndBankingRelationship();
        verifyElementPresent(OnBoardingPageiOS.objLetsSealTheDealPage,getTextVal(OnBoardingPageiOS.objLetsSealTheDealPage,": page title"));
        relaunchApp("IOS");
        enterMobileNumber();
        waitForElementToBePresent(OnBoardingPageiOS.objLetsSealTheDealPage,10,"Let's Seal The Deal page");
        if(verifyElementPresent(OnBoardingPageiOS.objLetsSealTheDealPage,getTextVal(OnBoardingPageiOS.objLetsSealTheDealPage,": page title"))) {
            verifyElementPresent(OnBoardingPageiOS.objPageTitle, getTextVal(OnBoardingPageiOS.objPageTitle, ": title"));
            containsValidation(getText(OnBoardingPageiOS.objPageTitle), propertyFileReader("LetsSealTheDealPage", "onboarding"), ": Title");
            logger.info("BD_OB_042, Onboarding - User can be able to proceed after Segmentation 2 directly after Let's Seal The Deal");
            extentLoggerPass("BD_OB_042", "BD_OB_042, Onboarding - User can be able to proceed after Segmentation 2 directly after Let's Seal The Deal");
        }
    }
    /**
     * Method to Verify if user can continue to Signature Screen
     * @throws Exception
     */
    public void signatureScreenValidation_BD_OB_050() throws Exception {
        HeaderChildNode("BD_OB_050, Onboarding - Verify if user can continue to Signature Screen");
        enterMobileNumber();
        verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(OnBoardingPageiOS.objPageTitle,": title"));
        containsValidation(getText(OnBoardingPageiOS.objPageTitle), propertyFileReader("LetsSealTheDealPage", "onboarding"), ": Title");
        verifyElementPresent(OnBoardingPageiOS.objPageSubTitle,getTextVal(OnBoardingPageiOS.objPageSubTitle,": Page Subtitle"));
        assertionValidation(getText(OnBoardingPageiOS.objPageSubTitle), propertyFileReader("LetsSealTheDealPageSubtitle","onboarding"),": page Subtitle");
        assertionValidation(getText(OnBoardingPageiOS.objNextBtnInfo), propertyFileReader("NextBtnInfo","onboarding"),": info");
        updateSignature();
        waitForElementToBePresent(OnBoardingPageiOS.objEraserIcon,10,"Eraser icon");
        verifyElementPresentAndClick(OnBoardingPageiOS.objEraserIcon,"Eraser Icon");
        updateSignature();
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn,getTextVal(OnBoardingPageiOS.objNextBtn,": button"));
        waitForElementToBePresent(OnBoardingPageiOS.objHeresTheDeal,5,"Here's the deal");
        relaunchApp("IOS");
        waitForElementToBePresent(OnBoardingPageiOS.objMobileNumberInputField,5,"Mobile number input field");
        type(OnBoardingPageiOS.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn, getTextVal(OnBoardingPageiOS.objNextBtn, ": Button"));
        waitForElementToBePresent(OnBoardingPageiOS.objHeresTheDeal,5,"Here's the deal");
        if(verifyElementPresent(OnBoardingPageiOS.objHeresTheDeal,getTextVal(OnBoardingPageiOS.objHeresTheDeal,": page"))){
            logger.info("BD_OB_050, Onboarding - User can input Signature Screen and continue");
            extentLoggerPass("BD_OB_050", "BD_OB_050, Onboarding - User can input Signature Screen and continue");
        }
    }
    /**
     * Method to Verify if user can accept the Deal in the Here's the deal screen
     * @throws Exception
     */
    public void hereTheDealScreenUIValidation_TBD_OB_051() throws Exception {
        HeaderChildNode("BD_OB_051, Onboarding - Verify if user can accept the Deal in the Here's the deal screen");
        enterMobileNumber();
        waitForElementToBePresent(OnBoardingPageiOS.objHeresTheDeal,5,"Here's the deal");
        verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(OnBoardingPageiOS.objPageTitle,": Page Title"));
        containsValidation(getText(OnBoardingPageiOS.objPageTitle),propertyFileReader("HeresTheDeal","onboarding"),": Page Title");
        assertionValidation(getText(OnBoardingPageiOS.objFeature1),propertyFileReader("Feature1","onboarding"),": Feature 1");
        assertionValidation(getText(OnBoardingPageiOS.objFeature2),propertyFileReader("Feature2","onboarding"),": Feature 2");
        assertionValidation(getText(OnBoardingPageiOS.objFeature3),propertyFileReader("Feature3","onboarding"),": Feature 3");
        assertionValidation(getText(OnBoardingPageiOS.objFeature4),propertyFileReader("Feature4","onboarding"),": Feature 4");
        assertionValidation(getText(OnBoardingPageiOS.objFeature5),propertyFileReader("Feature5","onboarding"),": Feature 5");
        assertionValidation(getText(OnBoardingPageiOS.objFeature6),propertyFileReader("Feature6","onboarding"),": Feature 6");
        assertionValidation(getText(OnBoardingPageiOS.objFeature7),propertyFileReader("Feature7","onboarding"),": Feature 7");
        customerCareIconValidation();
        verifyElementPresentAndClick(OnBoardingPageiOS.objDeal,getTextVal(OnBoardingPageiOS.objDeal,": button"));
        waitForElementToBePresent(OnBoardingPageiOS.objCreateAPassword,5,"Create a password");
        if(verifyElementDisplayed(OnBoardingPageiOS.objCreateAPassword)){
            assertionValidation(getText(OnBoardingPageiOS.objCreateAPassword),propertyFileReader("CreateAPassword","onboarding"),": title");
            logger.info("BD_OB_051, Onboarding - User can accept the Deal in the Here's the deal screen and navigated to Create a password page");
            extentLoggerPass("BD_OB_051", "BD_OB_051, Onboarding - User can accept the Deal in the Here's the deal screen and navigated to Create a password page");
        }
    }
    /**
     * Method to Verify if user can proceed if password and confirm password did not match
     * @throws Exception
     */
    public void incorrectConfirmPasswordValueValidation_BD_OB_052() throws Exception {
        HeaderChildNode("BD_OB_052, Onboarding - Verify if user can proceed if password and confirm password did not match");
        enterMobileNumber();
        waitForElementToBePresent(OnBoardingPageiOS.objHeresTheDeal, 10, "Here's the deal");
        verifyElementPresent(OnBoardingPageiOS.objPageTitle, getTextVal(OnBoardingPageiOS.objPageTitle, ": Page Title"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objDeal,getTextVal(OnBoardingPageiOS.objDeal,": button"));
        waitForElementToBePresent(OnBoardingPageiOS.objPasswordRule,10,"Create password page");
        verifyElementPresent(OnBoardingPageiOS.objCreateAPassword,getTextVal(OnBoardingPageiOS.objCreateAPassword,": title"));
        assertionValidation(getText(OnBoardingPageiOS.objCreateAPassword),propertyFileReader("CreateAPassword","onboarding"),": title");
        if (verifyElementDisplayed(OnBoardingPageiOS.objPasswordRule)) {
            List<MobileElement> values = DriverManager.getAppiumDriver().findElements(OnBoardingPageiOS.objPasswordRule);
            for (int sPasswordRules = 1; sPasswordRules < values.size(); sPasswordRules++) {
                assertionValidation(getText(OnBoardingPageiOS.objPasswordRule(sPasswordRules)), propertyFileReader("PasswordRule"+ sPasswordRules,"onboarding"), ": Password rule");
            }
        }
        customerCareIconValidation();
        waitForElementToBePresent(OnBoardingPageiOS.objDeal,10,"Here is the Deal");
        click(OnBoardingPageiOS.objDeal,getTextVal(OnBoardingPageiOS.objDeal,": button"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objPasswordField,"Password field");
        verifyElementPresent(OnBoardingPageiOS.objPasswordSuggestionPopup,getTextVal(OnBoardingPageiOS.objPasswordSuggestionPopup,": Password suggestion popup"));
        containsValidation(getText(OnBoardingPageiOS.objPasswordSuggestionPopup),propertyFileReader("PasswordSuggestionPopup","onboarding"),": Password suggestion popup");
        if (verifyElementDisplayed(OnBoardingPageiOS.objPasswordSuggestion)) {
            List<MobileElement> values = DriverManager.getAppiumDriver().findElements(OnBoardingPageiOS.objPasswordSuggestion);
            for (int sPasswordSuggestion = 1; sPasswordSuggestion < values.size(); sPasswordSuggestion++) {
                assertionValidation(getText(OnBoardingPageiOS.objPasswordSuggestion(sPasswordSuggestion)), propertyFileReader("PasswordSuggestion"+ sPasswordSuggestion,"onboarding"), ": Password rule");
            }
        }
        waitTime(10000);//waiting to get Got It button enabling, doesn't have proper attribute.
        verifyElementPresentAndClick(OnBoardingPageiOS.objPinkySwearGotItButton,getTextVal(OnBoardingPageiOS.objPinkySwearGotItButton,": button"));
        type(OnBoardingPageiOS.objPasswordInputField,propertyFileReader("ValidPassword","onboarding"),": Password field");
        type(OnBoardingPageiOS.objConfirmPasswordField,propertyFileReader("InvalidConfirmPassword","onboarding"),": Confirm Password field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
        if(verifyElementPresent(OnBoardingPageiOS.objPasswordMismatchError,getTextVal(OnBoardingPageiOS.objPasswordMismatchError,": error message"))){
            assertionValidation(getText(OnBoardingPageiOS.objPasswordMismatchError),propertyFileReader("PasswordMismatchError","onboarding"),": error message");
            logger.info("BD_OB_052, Onboarding - User can't proceed if password and confirm password did not match validated");
            extentLoggerPass("BD_OB_052", "BD_OB_052, Onboarding - User can't proceed if password and confirm password did not match validated");
        }
    }
    /**
     * Method to Verify if user can create valid password
     * @throws Exception
     */
    public void createValidPasswordValidation_BD_OB_053_BD_OB_054_BD_OB_056_BD_OB_055() throws Exception {
        HeaderChildNode("BD_OB_053, Onboarding - Verify if user can create valid password");
        enterMobileNumber();
        waitForElementToBePresent(OnBoardingPageiOS.objHeresTheDeal, 5, "Here's the deal");
        verifyElementPresent(OnBoardingPageiOS.objPageTitle, getTextVal(OnBoardingPageiOS.objPageTitle, ": Page Title"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objDeal,getTextVal(OnBoardingPageiOS.objDeal,": button"));
        waitForElementToBePresent(OnBoardingPageiOS.objCreateAPassword,5,"Create password page");
        verifyElementPresent(OnBoardingPageiOS.objCreateAPassword,getTextVal(OnBoardingPageiOS.objCreateAPassword,": title"));
        assertionValidation(getText(OnBoardingPageiOS.objCreateAPassword),propertyFileReader("CreateAPassword","onboarding"),": title");
        verifyElementPresentAndClick(OnBoardingPageiOS.objPasswordField,"Password field");
        verifyElementPresent(OnBoardingPageiOS.objPasswordSuggestionPopup,getTextVal(OnBoardingPageiOS.objPasswordSuggestionPopup,": Password suggestion popup"));
        containsValidation(getText(OnBoardingPageiOS.objPasswordSuggestionPopup),propertyFileReader("PasswordSuggestionPopup","onboarding"),": Password suggestion popup");
        waitTime(10000);//Hard wait provided to load entire page
        verifyElementPresentAndClick(OnBoardingPageiOS.objPinkySwearGotItButton,getTextVal(OnBoardingPageiOS.objPinkySwearGotItButton,": button"));
        for (int sPasswordSuggestionCheckBox = 1; sPasswordSuggestionCheckBox <= 3; sPasswordSuggestionCheckBox++) {
            type(OnBoardingPageiOS.objPasswordInputField, propertyFileReader("Password" + sPasswordSuggestionCheckBox, "onboarding"), ": input field");
            assertionValidation(getAttributValue("enabled", OnBoardingPageiOS.objPasswordSuggestionCheckBox(sPasswordSuggestionCheckBox)), "true", ": Enable Attribute value");
            clearField(OnBoardingPageiOS.objPasswordInputField,"Password field");
        }
        type(OnBoardingPageiOS.objPasswordInputField,propertyFileReader("ValidPassword","onboarding"),": Password field");
        type(OnBoardingPageiOS.objConfirmPasswordField,propertyFileReader("ValidPassword","onboarding"),": Confirm Password field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
        waitForElementToBePresent(OnBoardingPageiOS.objDashboardWelcomeScreen,5,"Dashboard welcome screen");
        if(verifyElementPresent(OnBoardingPageiOS.objDashboardWelcomeScreen, getTextVal(OnBoardingPageiOS.objDashboardWelcomeScreen, ": Welcome Screen"))) {
            assertionValidation(getText(OnBoardingPageiOS.objDashboardWelcomeScreen), propertyFileReader("DashBoardWelcomeScreen", "onboarding"), ": Welcome screen");
            screencapture();
            logger.info("BD_OB_053, Onboarding - User can create valid password and navigated to welcome screen is  validated");
            extentLoggerPass("BD_OB_053", "BD_OB_053, Onboarding - User can create valid password and navigated to welcome screen is  validated");
        }
        dashboardWelcomeScreenValidation();
        screencapture();
        logger.info("BD_OB_054, Onboarding - User can view the Welcome hun! and UI validated");
        extentLoggerPass("BD_OB_054", "BD_OB_054, Onboarding - User can view the Welcome hun! and UI validated");
        swipe("DOWN",2);
        verifyElementPresentAndClick(OnBoardingPageiOS.objBorrow, getTextVal(OnBoardingPageiOS.objBorrow, "; tile"));
        borrowScreenUIValidation();
        screencapture();
        logger.info("BD_OB_056, Onboarding - User can view the Loans by selecting Borrow tile from the Welcome screen validated");
        extentLoggerPass("BD_OB_056", "BD_OB_056, Onboarding - User can view the Loans by selecting Borrow tile from the Welcome screen validated");
        relaunchApp("IOS");
        waitForElementToBePresent(OnBoardingPageiOS.objLoginWithYourPasswordInputField,40,"Mobile number input field");
        type(OnBoardingPageiOS.objLoginWithYourPasswordInputField, propertyFileReader("ValidPassword", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objLoginBtn, getTextVal(OnBoardingPageiOS.objLoginBtn, ": Button"));
        if(verifyElementDisplayed(OnBoardingPageiOS.objIWillDoItBtn)){
            click(OnBoardingPageiOS.objIWillDoItBtn,getTextVal(OnBoardingPageiOS.objIWillDoItBtn,": button"));
        }
        tonikDashBordWhenNOTSAValidationiOS();
        screencapture();
        logger.info("BD_OB_055, Onboarding - User can view Dashboard when no TSA is created and UI validated");
        extentLoggerPass("BD_OB_055", "BD_OB_055, Onboarding - User can view Dashboard when no TSA is created and UI validated");
    }
    /**
     * Method to Verify if user can select Source of Funds
     * @throws Exception
     */
    public void selectSourceOfFundsValidation_BD_OB_035() throws Exception {
        HeaderChildNode("TBD_OB_035, Onboarding - Verify if user can select Source of Funds");
        enterMobileNumber();
        waitForElementToBePresent(OnBoardingPageiOS.objButtonToTapBtn,5,"Button to tap");
        buttonNeedToTapValidation();
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
        waitForElementToBePresent(OnBoardingPageiOS.objApplyZipCode,5,"Enter OTP page");
        applyZipCode(propertyFileReader("ValidZipCode","onboarding"));
        swipe("UP",2);
        selectBarangay();
        click(OnBoardingPageiOS.objHouseUnitFlrNumberInputField, "House or Street Input field");
        type(OnBoardingPageiOS.objHouseUnitFlrNumberInputField,propertyFileReader("NewHouseOrFloorNumber","onboarding"),"House or Street Input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn,getTextVal(OnBoardingPageiOS.objNextBtn,": button"));
        waitForElementToBePresent(OnBoardingPageiOS.objWhatDoYouDoPage,5,"What do you do? page");
        assertionValidation(getText(OnBoardingPageiOS.objPageTitle),propertyFileReader("WhatDoYouDoPageTitle","onboarding"),": Page Title");
        assertionValidation(getText(OnBoardingPageiOS.objPageSubTitle),propertyFileReader("WhatDoYouDoPageSubTitle","onboarding"),": Page Subtitle");
        verifyElementPresentAndClick(OnBoardingPageiOS.objSourceOfFundsInputField,"Source of Funds input field");
        waitForElementToBePresent(OnBoardingPageiOS.objSourceOfFundsPage,10,"Source of funds page");
        verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(OnBoardingPageiOS.objPageTitle,": Page Title"));
        assertionValidation(getText(OnBoardingPageiOS.objPageTitle),propertyFileReader("SourceOfFundPage","onboarding"),": Page title");
        if (verifyElementDisplayed(OnBoardingPageiOS.objList)) {
            List<MobileElement> values = DriverManager.getAppiumDriver().findElements(OnBoardingPageiOS.objList);
            for (int sSourceOfFunds = 0; sSourceOfFunds < values.size(); sSourceOfFunds++) {
                String displayedItem = values.get(sSourceOfFunds).getText();
                logger.info("Source of funds : '" + displayedItem + "' is displayed");
                ExtentReporter.extentLogger(" ", "Source of funds : '" + displayedItem + "' is displayed");
            }
            for (int sSourceOfFunds = 1; sSourceOfFunds <= values.size(); sSourceOfFunds++) {
                String sPurpose = getText(OnBoardingPageiOS.objList(sSourceOfFunds));
                click(OnBoardingPageiOS.objList(sSourceOfFunds),getTextVal(OnBoardingPageiOS.objList(sSourceOfFunds),": Source of funds"));
                assertionValidation(sPurpose,getText(OnBoardingPageiOS.objSourceOfFundsInputText),": Source of funds");
                screencapture();
                click(OnBoardingPageiOS.objSourceOfFundsInputText,"Source of funds");
                waitForElementToBePresent(OnBoardingPageiOS.objSourceOfFundsPage,10,"Source of funds page");
            }
        }
        logger.info("TBD_OB_035, Onboarding - User can select a Source of funds and reflected in What do you do page");
        extentLoggerPass("TBD_OB_035", "TBD_OB_035, Onboarding - User can select a Source of funds and reflected in What do you do page");
    }
    /**
     * Method to Verify if user can select Current Employment Status
     * @throws Exception
     */
    public void selectCurrentEmploymentStatusValidation_BD_OB_036() throws Exception {
        HeaderChildNode("TBD_OB_036, Onboarding - Verify if user can select Current Employment Status");
        enterMobileNumber();
        waitForElementToBePresent(OnBoardingPageiOS.objButtonToTapBtn,5,"Button to tap");
        buttonNeedToTapValidation();
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn,getTextVal(OnBoardingPageiOS.objNextBtn,": button"));
        waitForElementToBePresent(OnBoardingPageiOS.objWhatDoYouDoPage,5,"What do you do? page");
        verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(OnBoardingPageiOS.objPageTitle,": Page Title"));
        assertionValidation(getText(OnBoardingPageiOS.objPageTitle),propertyFileReader("WhatDoYouDoPageTitle","onboarding"),": Page Title");
        verifyElementPresent(OnBoardingPageiOS.objPageSubTitle,getTextVal(OnBoardingPageiOS.objPageSubTitle," : Page Subtitle"));
        assertionValidation(getText(OnBoardingPageiOS.objPageSubTitle),propertyFileReader("WhatDoYouDoPageSubTitle","onboarding"),": Page Subtitle");
        verifyElementPresentAndClick(OnBoardingPageiOS.objCurrentEmploymentStatus,"Current employment status input field");
        waitForElementToBePresent(OnBoardingPageiOS.objCurrentEmploymentStatusPage,10,"Current employment status page");
        verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(OnBoardingPageiOS.objPageTitle,": Page Title"));
        assertionValidation(getText(OnBoardingPageiOS.objPageTitle),propertyFileReader("CurrentEmploymentStatusPage","onboarding"),": Page title");
        if (verifyElementDisplayed(OnBoardingPageiOS.objList)) {
            List<MobileElement> values = DriverManager.getAppiumDriver().findElements(OnBoardingPageiOS.objList);
            for (int sSourceOfFunds = 0; sSourceOfFunds < values.size(); sSourceOfFunds++) {
                String displayedItem = values.get(sSourceOfFunds).getText();
                logger.info("Current employment status : '" + displayedItem + "' is displayed");
                ExtentReporter.extentLogger(" ", "Current employment status : '" + displayedItem + "' is displayed");
            }
            for (int sSourceOfFunds = 1; sSourceOfFunds <= values.size(); sSourceOfFunds++) {
                String sPurpose = getText(OnBoardingPageiOS.objList(sSourceOfFunds));
                click(OnBoardingPageiOS.objList(sSourceOfFunds),getTextVal(OnBoardingPageiOS.objList(sSourceOfFunds),": Current employment status"));
                assertionValidation(sPurpose,getText(OnBoardingPageiOS.objCurrentEmploymentStatusText),": Current employment status");
                click(OnBoardingPageiOS.objCurrentEmploymentStatusText,"Current employment status");
                waitForElementToBePresent(OnBoardingPageiOS.objCurrentEmploymentStatusPage,5,"Current employment status page");
            }
        }
        logger.info("TBD_OB_036, Onboarding - User can select a Current Employment Status and reflected in What do you do page");
        extentLoggerPass("TBD_OB_036", "TBD_OB_036, Onboarding - User can select a Current Employment Status and reflected in What do you do page");
    }
    /**
     * Method to Verify if user can select Nature of Work
     * @throws Exception
     */
    public void selectNatureOfWorkValidation_BD_OB_037() throws Exception {
        HeaderChildNode("TBD_OB_037, Onboarding - Verify if user can select Nature of Work");
        enterMobileNumber();
        waitForElementToBePresent(OnBoardingPageiOS.objButtonToTapBtn,5,"Button to tap");
        buttonNeedToTapValidation();
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn, getTextVal(OnBoardingPageiOS.objNextBtn, ": button"));
        waitForElementToBePresent(OnBoardingPageiOS.objWhatDoYouDoPage, 5, "What do you do? page");
        verifyElementPresent(OnBoardingPageiOS.objPageTitle, getTextVal(OnBoardingPageiOS.objPageTitle, ": Page Title"));
        assertionValidation(getText(OnBoardingPageiOS.objPageTitle), propertyFileReader("WhatDoYouDoPageTitle", "onboarding"), ": Page Title");
        verifyElementPresent(OnBoardingPageiOS.objPageSubTitle, getTextVal(OnBoardingPageiOS.objPageSubTitle, " : Page Subtitle"));
        assertionValidation(getText(OnBoardingPageiOS.objPageSubTitle), propertyFileReader("WhatDoYouDoPageSubTitle", "onboarding"), ": Page Subtitle");
        verifyElementPresentAndClick(OnBoardingPageiOS.objSourceOfFundsInputField, "Source of Funds input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objSelectSourceOfFunds("Salary"), getTextVal(OnBoardingPageiOS.objSelectSourceOfFunds("Salary"), ": Source of fund"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objCurrentEmploymentStatus, "Current Employment Status input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objSelectCurrentEmploymentStatus("Employed"), getTextVal(OnBoardingPageiOS.objSelectCurrentEmploymentStatus("Employed"), ": Current Employment Status"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objNatureOfWork, "Nature Of Work input field");
        if (verifyElementDisplayed(OnBoardingPageiOS.objList)) {
            List<MobileElement> values = DriverManager.getAppiumDriver().findElements(OnBoardingPageiOS.objList);
            for (int swipe = 1;swipe <= 10;swipe++) {
                waitTime(3000);//Getting stale element exception added hard wait
                for (int natureOfWork = 1; natureOfWork < values.size(); natureOfWork++) {
                    String displayedItem = getText(OnBoardingPageiOS.objList(natureOfWork));
                    logger.info("Nature of work : '" + displayedItem + "' is displayed");
                    ExtentReporter.extentLogger(" ", "Nature of work : '" + displayedItem + "' is displayed");
                }
                if(verifyElementDisplayed(OnBoardingPageiOS.objNatureOfWorkLastOption)){
                    break;
                }
                swipe("UP",1);
            }
        }
        logger.info("TBD_OB_037, Onboarding - Nature of work options validated");
        extentLoggerPass("TBD_OB_037", "TBD_OB_037, Onboarding - Nature of work options validated");
    }
    /**
     * Method to Verify if user can select Industry
     * @throws Exception
     */
    public void selectIndustryValidation_BD_OB_038() throws Exception {
        HeaderChildNode("TBD_OB_038, Onboarding - Verify if user can select Industry");
        enterMobileNumber();
        waitForElementToBePresent(OnBoardingPageiOS.objButtonToTapBtn,5,"Button to tap");
        buttonNeedToTapValidation();
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn, getTextVal(OnBoardingPageiOS.objNextBtn, ": button"));
        waitForElementToBePresent(OnBoardingPageiOS.objWhatDoYouDoPage, 5, "What do you do? page");
        verifyElementPresent(OnBoardingPageiOS.objPageTitle, getTextVal(OnBoardingPageiOS.objPageTitle, ": Page Title"));
        assertionValidation(getText(OnBoardingPageiOS.objPageTitle), propertyFileReader("WhatDoYouDoPageTitle", "onboarding"), ": Page Title");
        verifyElementPresent(OnBoardingPageiOS.objPageSubTitle, getTextVal(OnBoardingPageiOS.objPageSubTitle, " : Page Subtitle"));
        assertionValidation(getText(OnBoardingPageiOS.objPageSubTitle), propertyFileReader("WhatDoYouDoPageSubTitle", "onboarding"), ": Page Subtitle");
        verifyElementPresentAndClick(OnBoardingPageiOS.objSourceOfFundsInputField, "Source of Funds input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objSelectSourceOfFunds("Salary"), getTextVal(OnBoardingPageiOS.objSelectSourceOfFunds("Salary"), ": Source of fund"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objCurrentEmploymentStatus, "Current Employment Status input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objSelectCurrentEmploymentStatus("Employed"), getTextVal(OnBoardingPageiOS.objSelectCurrentEmploymentStatus("Employed"), ": Current Employment Status"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objNatureOfWork,"Nature Of Work input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objSelectNatureOfWork("Accountant/Auditor"),getTextVal(OnBoardingPageiOS.objSelectNatureOfWork("Accountant/Auditor"),": Nature Of Work"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objIndustry,"Industry input field");
        if (verifyElementDisplayed(OnBoardingPageiOS.objList)) {
            List<MobileElement> values = DriverManager.getAppiumDriver().findElements(OnBoardingPageiOS.objList);
            for (int swipe = 1;swipe <= 10;swipe++) {
                waitTime(3000);//Getting stale element exception added hard wait
                for (int sIndustry = 1; sIndustry < values.size(); sIndustry++) {
                    String displayedItem = getText(OnBoardingPageiOS.objList(sIndustry));
                    logger.info("Industry : '" + displayedItem + "' is displayed");
                    ExtentReporter.extentLogger(" ", "Industry : '" + displayedItem + "' is displayed");
                }
                swipe("UP",1);
                if(verifyElementDisplayed(OnBoardingPageiOS.objIndustryLastOption)){
                    break;
                }
            }
        }
        logger.info("TBD_OB_038, Onboarding - Industry options validated");
        extentLoggerPass("TBD_OB_038", "TBD_OB_038, Onboarding - Industry options validated");
    }
    /**
     * Method to Verify if user can onboard a non existing user
     * @throws Exception
     */
    public void onboardingNonExistingUserValidation_TBD_OB_058() throws Exception {
        HeaderChildNode("TBD_OB_058, Onboarding - Verify if user can onboard a non existing user");
        navigateToCreatePage();
        verifyElementPresentAndClick(OnBoardingPageiOS.objLoginHereLink,getTextVal(OnBoardingPageiOS.objLoginHereLink,": Link"));
        loginStepsValidation();
        waitForElementToBePresent(OnBoardingPageiOS.objVerifyMobileNumberPage,5,"Verify mobile number page");
        verifyElementPresent(OnBoardingPageiOS.objPageTitle, getTextVal(OnBoardingPageiOS.objPageTitle, ": Page Title"));
        assertionValidation(getText(OnBoardingPageiOS.objPageTitle), propertyFileReader("VerifyMobileNumberPage","onboarding"),": Page Title");
        verifyElementPresent(OnBoardingPageiOS.objPageSubTitle,getTextVal(OnBoardingPageiOS.objPageSubTitle,": Page Subtitle"));
        assertionValidation(getText(OnBoardingPageiOS.objPageSubTitle), propertyFileReader("VerifyMobileNumberPageSubTitle","onboarding"),": page Subtitle");
        type(OnBoardingPageiOS.objMobileNumberInputField,propertyFileReader("UnregisteredNumber","onboarding"),"Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn,getTextVal(OnBoardingPageiOS.objNextBtn,": button"));
        verifyElementPresent(OnBoardingPageiOS.objInvalidMobileNumberPopup,getTextVal(OnBoardingPageiOS.objInvalidMobileNumberPopup,": popup"));
        assertionValidation(getText(OnBoardingPageiOS.objInvalidMobileNumberPopup), propertyFileReader("UnregisteredMobileNumberPopup","onboarding"),": popup");
        verifyElementPresentAndClick(OnBoardingPageiOS.objTryAgainBtn,getTextVal(OnBoardingPageiOS.objTryAgainBtn,": button"));
        if(verifyElementDisplayed(OnBoardingPageiOS.objMobileNumberInputField)){
            logger.info("User can able to re-enter the mobile number");
            extentLoggerPass("","User can able to re-enter the mobile number");
        }else{
            logger.info("User unable to re-enter the mobile number");
            extentLoggerPass("","User unable to re-enter the mobile number");
        }
        type(OnBoardingPageiOS.objMobileNumberInputField,propertyFileReader("UnregisteredNumber","onboarding"),"Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn,getTextVal(OnBoardingPageiOS.objNextBtn,": button"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objChangeRegisteredMobileNumber,getTextVal(OnBoardingPageiOS.objChangeRegisteredMobileNumber,": button"));
        verifyElementPresent(OnBoardingPageiOS.objGetInTouchWithCustomerCarePage,getTextVal(OnBoardingPageiOS.objGetInTouchWithCustomerCarePage,": page"));
        containsValidation(getText(OnBoardingPageiOS.objGetInTouchWithCustomerCarePage),propertyFileReader("GetInTouchWithCustomerCarePage","onboarding"),": page");
        containsValidation(getText(OnBoardingPageiOS.objGetInTouchWithCustomerCarePageInfo),propertyFileReader("GetInTouchWithCustomerCarePageInfo","onboarding"),": info");
        verifyElementPresentAndClick(OnBoardingPageiOS.objContactTonikBtn,getTextVal(OnBoardingPageiOS.objContactTonikBtn,": button"));
        click(OnBoardingPageiOS.objBackBtn,getTextVal(OnBoardingPageiOS.objPageHeader," : Page Back button"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objCloseBtn,getTextVal(OnBoardingPageiOS.objCloseBtn,": button"));
        logger.info("TBD_OB_058, Onboarding - User entered Unregistered Tonik Account number and corresponding error message is validated");
        extentLoggerPass("TBD_OB_058", "TBD_OB_058, Onboarding - User entered Unregistered Tonik Account number and corresponding error message is validated");
    }
    /**
     * Method to Verify if user can input an already registered number in Tonik while onboarding
     * @throws Exception
     */
    public void inputExistingAccountMobileNumberValidation_TBD_OB_059() throws Exception {
        HeaderChildNode("TBD_OB_059, Onboarding - Verify if user can input an already registered number in Tonik while onboarding");
        navigateToCreatePage();
        type(OnBoardingPageiOS.objMobileNumberInputField,propertyFileReader("ExistingTonikAccount","onboarding"),"Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn, getTextVal(OnBoardingPageiOS.objNextBtn, ": Button"));
        waitForElementToBePresent(OnBoardingPageiOS.objExistingTonikCustomerPopup,5,"Existing tonik account popup");
        if(verifyElementPresent(OnBoardingPageiOS.objExistingTonikCustomerPopup,getTextVal(OnBoardingPageiOS.objExistingTonikCustomerPopup,": popup"))){
            containsValidation(getText(OnBoardingPageiOS.objExistingTonikCustomerPopup),propertyFileReader("ExistingTonikAccountPopup","onboarding"),": popup");
            assertionValidation(getText(OnBoardingPageiOS.objEnterNewNumber),propertyFileReader("EnterNewNumber","onboarding"),": button");
            assertionValidation(getText(OnBoardingPageiOS.objLogInToTonik),propertyFileReader("LogInToTonik","onboarding"),": link");
            logger.info("TBD_OB_059, Onboarding - User entered existing tonik account number and corresponding error message is validated");
            extentLoggerPass("TBD_OB_059", "TBD_OB_059, Onboarding - User entered existing tonik account number and corresponding error message is validated");
        }
    }
    /**
     * Method to Verify if SKYC user can onboard with high risk profile successfully
     * @throws Exception
     */
    public void onboardingSKYCWithHighRiskProfile_TBD_OB_064() throws Exception {
        HeaderChildNode("TBD_OB_064, Onboarding - Verify if SKYC user can onboard with high risk profile successfully");
        navigateToCreatePage();
        waitForElementToBePresent(OnBoardingPageiOS.objMobileNumberInputField,5,"Mobile number input field");
        type(OnBoardingPageiOS.objMobileNumberInputField, propertyFileReader("HighRiskProfile", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn, getTextVal(OnBoardingPageiOS.objNextBtn, ": Button"));
        acceptTermsAndConditionAndPrivacyPolicy();
        selectIDAndScan(OnBoardingPageiOS.objPhilID);
        buttonNeedToTapValidation();
        inputPlaceOfBirthAndContinue();
        applyZipCode(propertyFileReader("ValidZipCode","onboarding"));
        selectBarangay();
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn,getTextVal(OnBoardingPageiOS.objNextBtn,": button"));
        updateWhatDoYouDo("Salary","Employed","Accountant/Auditor","Real Estate");
        selectUSCitizenOrNotAndNext();
        updateEmailMotherNameAndBankingRelationship();
        updateSignatureAndNext();
        waitForElementToBePresent(OnBoardingPageiOS.objHeresTheDeal,5,"Here's the deal");
        verifyElementPresent(OnBoardingPageiOS.objHeresTheDeal,getTextVal(OnBoardingPageiOS.objHeresTheDeal,": page"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objDeal,getTextVal(OnBoardingPageiOS.objDeal,": button"));
        createPasswordAndContinue();
        waitForElementToBePresent(OnBoardingPageiOS.objReviewInProgress,5,"Review in progress page");
        verifyElementPresent(OnBoardingPageiOS.objReviewInProgress,getTextVal(OnBoardingPageiOS.objReviewInProgress,": page"));
        containsValidation(getText(OnBoardingPageiOS.objReviewInProgressInfo1),propertyFileReader("ReviewInProgressInfo1","onboarding"),"Review InProgress");
        containsValidation(getText(OnBoardingPageiOS.objReviewInProgressInfo2),propertyFileReader("ReviewInProgressInfo2","onboarding"),"Review InProgress");
        verifyElementPresentAndClick(OnBoardingPageiOS.objContactTonikCustomerCare,getTextVal(OnBoardingPageiOS.objContactTonikCustomerCare,": button"));
        contactUsPageUI();
        verifyElementPresentAndClick(OnBoardingPageiOS.objBackBtn,"Back btn");
        verifyElementPresentAndClick(OnBoardingPageiOS.objCloseTheApp,getTextVal(OnBoardingPageiOS.objCloseTheApp,": button"));
        relaunchApp("ios");
        waitForElementToBePresent(OnBoardingPageiOS.objMobileNumberInputField,5,"Mobile number input field");
        type(OnBoardingPageiOS.objMobileNumberInputField, propertyFileReader("HighRiskProfile", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn, getTextVal(OnBoardingPageiOS.objNextBtn, ": Button"));
        if(verifyElementPresent(OnBoardingPageiOS.objExistingTonikCustomerPopup,getTextVal(OnBoardingPageiOS.objExistingTonikCustomerPopup,": popup"))) {
            containsValidation(getText(OnBoardingPageiOS.objExistingTonikCustomerPopup), propertyFileReader("ExistingTonikAccountPopup", "onboarding"), ": popup");
            verifyElementPresentAndClick(OnBoardingPageiOS.objOkBtn, getTextVal(OnBoardingPageiOS.objOkBtn, ": Button"));
        }
        verifyElementPresentAndClick(OnBoardingPageiOS.objLoginHereLink,getTextVal(OnBoardingPageiOS.objLoginHereLink,": link"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn,getTextVal(OnBoardingPageiOS.objContinueBtn,": button"));
        type(OnBoardingPageiOS.objMobileNumberInputField,propertyFileReader("HighRiskProfile","onboarding"),"Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn,getTextVal(OnBoardingPageiOS.objNextBtn,": button"));
        if(verifyElementPresent(OnBoardingPageiOS.objReviewInProgress,getTextVal(OnBoardingPageiOS.objReviewInProgress,": page"))) {
            screencapture();
            containsValidation(getText(OnBoardingPageiOS.objReviewInProgressInfo1),propertyFileReader("ReviewInProgressInfo1","onboarding"),"Review InProgress");
            containsValidation(getText(OnBoardingPageiOS.objReviewInProgressInfo2),propertyFileReader("ReviewInProgressInfo2","onboarding"),"Review InProgress");
            verifyElementPresentAndClick(OnBoardingPageiOS.objContactTonikCustomerCare, getTextVal(OnBoardingPageiOS.objContactTonikCustomerCare, ": button"));
            contactUsPageUI();
            verifyElementPresentAndClick(OnBoardingPageiOS.objBackBtn, "Back btn");
            verifyElementPresent(OnBoardingPageiOS.objVerifyMobileNumberPage,getTextVal(OnBoardingPageiOS.objVerifyMobileNumberPage,": page"));
            logger.info("TBD_OB_064, Onboarding - SKYC user can onboard with high risk profile and Review in progress page validated");
            extentLoggerPass("TBD_OB_064", "TBD_OB_064, Onboarding - SKYC user can onboard with high risk profile and Review in progress page validated");
        }
    }
    /**
     * Method to Verify if user can onboard as SKYC
     * @throws Exception
     */
    public void onboardingSKYCUserValidation_TBD_OB_065_BD_OB_057() throws Exception {
        HeaderChildNode("TBD_OB_065, Onboarding - Verify if user can onboard as SKYC");
        navigateToCreatePage();
        waitForElementToBePresent(OnBoardingPageiOS.objMobileNumberInputField,5,"Mobile number input field");
        type(OnBoardingPageiOS.objMobileNumberInputField, propertyFileReader("NewSKYCUser", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn, getTextVal(OnBoardingPageiOS.objNextBtn, ": Button"));
        acceptTermsAndConditionAndPrivacyPolicy();
        selectIDAndScan(OnBoardingPageiOS.objPhilID);
        buttonNeedToTapValidation();
        inputPlaceOfBirthAndContinue();
        applyZipCode(propertyFileReader("ValidZipCode","onboarding"));
        selectBarangay();
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn,getTextVal(OnBoardingPageiOS.objNextBtn,": button"));
        updateWhatDoYouDo("Salary","Employed","Accountant/Auditor","Accounting/Bookkeeping");
        selectUSCitizenOrNotAndNext();
        updateEmailMotherNameAndBankingRelationship();
        updateSignatureAndNext();
        waitForElementToBePresent(OnBoardingPageiOS.objHeresTheDeal,5,"Here's the deal");
        verifyElementPresent(OnBoardingPageiOS.objHeresTheDeal,getTextVal(OnBoardingPageiOS.objHeresTheDeal,": page"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objDeal,getTextVal(OnBoardingPageiOS.objDeal,": button"));
        createPasswordAndContinue();
        dashboardWelcomeScreenValidation();
        verifyElementPresentAndClick(OnBoardingPageiOS.objSave, getTextVal(OnBoardingPageiOS.objSave, ": tile"));
        if(verifyElementDisplayed(OnBoardingPageiOS.objIWillDoItBtn)){
            click(OnBoardingPageiOS.objIWillDoItBtn,getTextVal(OnBoardingPageiOS.objIWillDoItBtn,": button"));
        }
        tonikDashBordWhenNOTSAValidationiOS();
        logger.info("BD_OB_065, Onboarding - User can onboarded as SKYC successfully validated");
        extentLoggerPass("BD_OB_065", "BD_OB_065, Onboarding - User can onboarded as SKYC successfully validated");
        click(OnBoardingPageiOS.objLoansTile,getTextVal(OnBoardingPageiOS.objLoansTile,": tile"));
        borrowScreenUIValidation();
        screencapture();
        logger.info("BD_OB_057, Onboarding - User can view the Loans by selecting Save tile from the Welcome screen validated");
        extentLoggerPass("BD_OB_057", "BD_OB_057, Onboarding - User can view the Loans by selecting Save tile from the Welcome screen validated");
    }
    /**
     * Method to Verify if user can go through after selecting FATCA
     * @throws Exception
     */
    public void usaCitizenFATCAValidation_BD_OB_040() throws Exception {
        HeaderChildNode("TBD_OB_040, Onboarding - Verify if user can go through after selecting FATCA");
        navigateToCreatePage();
        waitForElementToBePresent(OnBoardingPageiOS.objMobileNumberInputField,5,"Mobile number input field");
        type(OnBoardingPageiOS.objMobileNumberInputField, propertyFileReader("USACitizen", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn, getTextVal(OnBoardingPageiOS.objNextBtn, ": Button"));
        selectIDAndScan(OnBoardingPageiOS.objPhilID);
        buttonNeedToTapValidation();
        inputPlaceOfBirthAndContinue();
        applyZipCode(propertyFileReader("ValidZipCode","onboarding"));
        selectBarangay();
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn,getTextVal(OnBoardingPageiOS.objNextBtn,": button"));
        updateWhatDoYouDo("Salary","Employed","Accountant/Auditor","Accounting/Bookkeeping");
        verifyElementPresent(OnBoardingPageiOS.objFATCAPageTitle, getTextVal(OnBoardingPageiOS.objFATCAPageTitle, ": title"));
        assertionValidation(getText(OnBoardingPageiOS.objPageTitle), propertyFileReader("FATCAPageTitle", "onboarding"), ": Title");
        verifyElementPresentAndClick(OnBoardingPageiOS.objAUSACitizen, getTextVal(OnBoardingPageiOS.objAUSACitizen, ": option"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn,getTextVal(OnBoardingPageiOS.objNextBtn,": button"));
        updateEmailMotherNameAndBankingRelationship();
        updateSignatureAndNext();
        waitForElementToBePresent(OnBoardingPageiOS.objHeresTheDeal,5,"Here's the deal");
        verifyElementPresent(OnBoardingPageiOS.objHeresTheDeal,getTextVal(OnBoardingPageiOS.objHeresTheDeal,": page"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objDeal,getTextVal(OnBoardingPageiOS.objDeal,": button"));
        if(verifyElementPresent(OnBoardingPageiOS.objBlockedUSCitizenPage,getTextVal(OnBoardingPageiOS.objBlockedUSCitizenPage,": page"))){
            containsValidation(getText(OnBoardingPageiOS.objBlockedUSCitizenInfo),propertyFileReader("BlockedUSCitizenInfo","onboarding"),": info");
            verifyElementPresent(OnBoardingPageiOS.objCloseBtn,getTextVal(OnBoardingPageiOS.objCloseBtn,": button"));
            verifyElementPresent(OnBoardingPageiOS.objContactTonikCustomerCare,getTextVal(OnBoardingPageiOS.objContactTonikCustomerCare,": button"));
            logger.info("BD_OB_040, Onboarding - User can go through after selecting USA Citizen FATCA and user got blocked");
            extentLoggerPass("BD_OB_040", "BD_OB_040, Onboarding - User can go through after selecting USA Citizen FATCA and user got blocked");
        }
    }

    public void onboardingBKYCUserValidation_TDB_OB_060() throws Exception {
        HeaderChildNode("TDB_OB_060, Onboarding - Verify if user can onboard as BKYC");
        navigateToCreatePage();
        waitForElementToBePresent(OnBoardingPageiOS.objMobileNumberInputField,5,"Mobile number input field");
        type(OnBoardingPageiOS.objMobileNumberInputField, propertyFileReader("NewSKYCUser", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPageiOS.objNextBtn, ": Button"));
        acceptTermsAndConditionAndPrivacyPolicy();
        waitTime(5000);
        verifyElementPresent(OnBoardingPageiOS.objPageTitle,getTextVal(OnBoardingPageiOS.objPageTitle," : Page Title"));
        containsValidation(getText(OnBoardingPageiOS.objPageTitle),propertyFileReader("IDSelectionPageTile","onboarding"),": Title");
        verifyElementPresentAndClick(OnBoardingPageiOS.objSkipThisStep,getTextVal(OnBoardingPageiOS.objSkipThisStep,"Button"));
        buttonNeedToTapValidation();
        waitForElementToBePresent(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle,5,"Let's get to know screen");
        verifyElementPresent(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle, getTextVal(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle, ": title"));
        containsValidation(getText(OnBoardingPageiOS.objAutoDetailsPopulatedPageTitle), propertyFileReader("AutoDetailsPopulatedPageTitle", "onboarding"), ": title");
        type(OnBoardingPageiOS.objAutoPopulatedFirstName, propertyFileReader("FirstName", "onboarding"), "First name input field");
        type(OnBoardingPageiOS.objAutoPopulatedMiddleName, propertyFileReader("MiddleName", "onboarding"), "Middle name input field");
        type(OnBoardingPageiOS.objAutoPopulatedLastName, propertyFileReader("LastName", "onboarding"), "Last name input field");
        click(OnBoardingPageiOS.objAutoPopulatedGender,"Gender input field");
        click(OnBoardingPageiOS.objAutoPopulatedGender,"Gender input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objGenderDropdown("Male"),getTextVal(OnBoardingPageiOS.objGenderDropdown("Male"),": Gender dropdown element"));
        click(OnBoardingPageiOS.objAutoPopulatedDateOfBirth,getTextVal(OnBoardingPageiOS.objAutoPopulatedDateOfBirth,": Auto populated Date of  birth"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objCalendarOkBtn,getTextVal(OnBoardingPageiOS.objCalendarOkBtn,": Button"));
        type(OnBoardingPageiOS.objPlaceOfBirth, propertyFileReader("ValidPlaceOfBirth", "onboarding"), "Place of birth input field");
        verifyElementPresentAndClick(OnBoardingPageiOS.objContinueBtn, getTextVal(OnBoardingPageiOS.objContinueBtn, ": button"));
        applyZipCode(propertyFileReader("ValidZipCode","onboarding"));
        selectBarangay();
        scrollToVertical("House");
        type(OnBoardingPageiOS.objHouseUnitFlrNumberInputField,propertyFileReader("NewHouseOrFloorNumber","onboarding"),"House, Unit or Flr name");
        verifyElementPresentAndClick(OnBoardingPageiOS.objNextBtn,getTextVal(OnBoardingPageiOS.objNextBtn,": button"));
        updateWhatDoYouDo("Salary","Employed","Accountant/Auditor","Accounting/Bookkeeping");
        selectUSCitizenOrNotAndNext();
        updateEmailMotherNameAndBankingRelationship();
        updateSignatureAndNext();
        waitForElementToBePresent(OnBoardingPageiOS.objHeresTheDeal,5,"Here's the deal");
        verifyElementPresent(OnBoardingPageiOS.objHeresTheDeal,getTextVal(OnBoardingPageiOS.objHeresTheDeal,": page"));
        verifyElementPresentAndClick(OnBoardingPageiOS.objDeal,getTextVal(OnBoardingPageiOS.objDeal,": button"));
        createPasswordAndContinue();
        dashboardWelcomeScreenValidation();
        verifyElementPresentAndClick(OnBoardingPageiOS.objSave, getTextVal(OnBoardingPageiOS.objSave, ": tile"));
        if(verifyElementDisplayed(OnBoardingPageiOS.objIWillDoItBtn)){
            click(OnBoardingPageiOS.objIWillDoItBtn,getTextVal(OnBoardingPageiOS.objIWillDoItBtn,": button"));
        }
        tonikDashBordWhenNOTSAValidationiOS();
        logger.info("TDB_OB_060, Onboarding - User can onboarded as BKYC successfully validated");
        extentLoggerPass("TDB_OB_060", "BD_OB_065, Onboarding - User can onboarded as BKYC successfully validated");
    }
}