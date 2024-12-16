package com.tonik.bizfunctions;
import com.driverInstance.DriverManager;
import com.tonik.pageObject.ContactUsPage;
import com.tonik.pageObject.LoginPage;
import com.tonik.pageObject.OnBoardingPage;
import com.tonik.utility.ExtentReporter;
import com.tonik.utility.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Random;
import static com.tonik.utility.Utilities.*;
public class OnboardingModule extends BaseClass{
    public OnboardingModule() {
        super();
    }
    /**
     * Reusable method to Navigate to create page
     * @throws Exception
     */
    public void navigateToCreatePage() throws Exception {
        waitForElementToBePresent(OnBoardingPage.objWelcomeScreen1Btn,5,"WelcomeScreen1Btn");
        if(verifyElementDisplayed(OnBoardingPage.objWelcomeScreen1Btn)) {
            click(OnBoardingPage.objWelcomeScreen1Btn, getTextVal(OnBoardingPage.objWelcomeScreen1Btn, ": Onboarding Screen 1 button"));
            waitForElementToBePresent(OnBoardingPage.objWelcomeScreen2Btn,5,"WelcomeScreen2Btn");
            click(OnBoardingPage.objWelcomeScreen2Btn, getTextVal(OnBoardingPage.objWelcomeScreen2Btn, ": Onboarding Screen 2 button"));
            click(OnBoardingPage.objWelcomeScreen3Btn, getTextVal(OnBoardingPage.objWelcomeScreen3Btn, ": Onboarding Screen 3 button"));
            click(OnBoardingPage.objWelcomeScreen4Btn, getTextVal(OnBoardingPage.objWelcomeScreen4Btn, ": Onboarding Screen 4 button"));
            click(OnBoardingPage.objWelcomeScreen5Btn, getTextVal(OnBoardingPage.objWelcomeScreen5Btn, ": Onboarding Screen 5 button"));
        }
        if(verifyElementDisplayed(LoginPage.getByOSType(platform,LoginPage.objWhileUsingApp))){
            verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objWhileUsingApp),getTextVal(LoginPage.getByOSType(platform,LoginPage.objWhileUsingApp),": Take Picture Permission popup"));
            verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objWhileUsingApp),getTextVal(LoginPage.getByOSType(platform,LoginPage.objWhileUsingApp),": Button"));
        }
    }
    /**
     * Reusable method to validate UI of Create Account page
     * @throws Exception
     */
    public void createTonikAccountPageUIValidation() throws Exception {
        waitForElementToBePresent(OnBoardingPage.objCreateAccountPage,5,"Create account page");
        if (verifyElementDisplayed(OnBoardingPage.objCreateAccountPage)) {
            verifyElementPresent(OnBoardingPage.objPageTitle, getTextVal(OnBoardingPage.objPageTitle, ": Page Title"));
            assertionValidation(getText(OnBoardingPage.objPageTitle), propertyFileReader("CreateAccountPageTitle","onboarding"),": Page Title");
            verifyElementPresent(OnBoardingPage.objPageSubTitle,getTextVal(OnBoardingPage.objPageSubTitle,": Page Subtitle"));
            assertionValidation(getText(OnBoardingPage.objPageSubTitle), propertyFileReader("CreatePageAccountSubtitle","onboarding"),": page Subtitle");
            verifyElementPresent(OnBoardingPage.objMobileNumberInputField,"Mobile number input field");
            verifyElementPresent(OnBoardingPage.objTickSymbol,"Tick Symbol");
            assertionValidation(getText(OnBoardingPage.objAlreadyHaveAccount),propertyFileReader("AlreadyHaveAnAccountTxt","onboarding"),": Text");
            assertionValidation(getText(OnBoardingPage.objLoginHereLink),propertyFileReader("LoginHereLink","onboarding"),": Link");
            verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objNextBtn),getTextVal(OnBoardingPage.objNextBtn,": Button"));
            verifyElementPresent(OnBoardingPage.objCustomerCareIconLoginScreen,"Customer care icon");
        }
    }
    /**
     * Reusable method to validate Customer care icon and UI
     * @throws Exception
     */
    public void customerCareIconValidation() throws Exception {
        click(OnBoardingPage.objCustomerCareIcon,"Customer care icon");
        contactUsPageUI();
        click(OnBoardingPage.objBackBtn,"Get in touch back button");
    }
    /**
     * Reusable method to accept terms and condition
     * @throws Exception
     */
    public void acceptTermsAndCondition() throws Exception {
        waitForElementToBePresent(OnBoardingPage.objAgreeAndContinueBtn,10,"Agree and continue");
        if(verifyElementDisplayed(OnBoardingPage.objAgreeAndContinueBtn)) {
            verifyElementPresent(OnBoardingPage.objPageTitle, getTextVal(OnBoardingPage.objPageTitle, ": Page Title"));
            verifyElementPresentAndClick(OnBoardingPage.objTermsAndConditionInfoCheckBox, "Terms and Condition check Box");
            verifyElementPresentAndClick(OnBoardingPage.objAgreeAndContinueBtn, getTextVal(OnBoardingPage.objAgreeAndContinueBtn, ": Button"));
        }
    }
    /**
     * Reusable method to accept Terms and condition and Privacy policy
     * @throws Exception
     */
    public void acceptTermsAndConditionAndPrivacyPolicy() throws Exception {
        waitForElementToBePresent(OnBoardingPage.objAgreeAndContinueBtn,5,"Agree and continue");
        if(verifyElementDisplayed(OnBoardingPage.objAgreeAndContinueBtn)) {
            acceptTermsAndCondition();
            acceptDataPrivacyStatement();
            waitForElementToBePresent(OnBoardingPage.objEnterOTP, 5, "Enter OTP page");
            enterOTP();
        }
    }
    /**
     * Reusable method to accept data privacy statement
     * @throws Exception
     */
    public void acceptDataPrivacyStatement() throws Exception {
        waitForElementToBePresent(OnBoardingPage.objDataPrivacyStatementRadioBtn,10,"Agree and continue");
        if(verifyElementDisplayed(OnBoardingPage.objDataPrivacyStatementRadioBtn)) {
            verifyElementPresent(OnBoardingPage.objPageTitle, getTextVal(OnBoardingPage.objPageTitle, ": Page Title"));
            verifyElementPresentAndClick(OnBoardingPage.objDataPrivacyStatementRadioBtn, "Data Privacy statement check Box");
            verifyElementPresentAndClick(OnBoardingPage.objAgreeAndContinueBtn, getTextVal(OnBoardingPage.objAgreeAndContinueBtn, ": Button"));
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
        waitForElementToBePresent(OnBoardingPage.objEnterOTP,5,"Enter OTP");
        if(verifyElementDisplayed(OnBoardingPage.objEnterOTP)) {
            verifyElementPresent(OnBoardingPage.objPageTitle, getTextVal(OnBoardingPage.objPageTitle, " : Page Title"));
            assertionValidation(getText(OnBoardingPage.objPageTitle), propertyFileReader("EnterOTP", "onboarding"), ": Title");
            click(OnBoardingPage.objOTPInputField("1"), "OTP Input field");
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
        if(verifyElementDisplayed(OnBoardingPage.objIDSelectionPageTile)){
            verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle," : Page Title"));
            containsValidation(getText(OnBoardingPage.objPageTitle),propertyFileReader("IDSelectionPageTile","onboarding"),": Title");
            verifyElementPresentAndClick(selectId,getTextVal(selectId,": Id selected"));
            click(OnBoardingPage.objLetScanThisIDBtn,getTextVal(OnBoardingPage.objLetScanThisIDBtn,": Button"));
            waitUntilElementVisible_NoCustomMessage(OnBoardingPage.objGotAClearScanTitle);
            verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle," : Page Title"));
            assertionValidation(getText(OnBoardingPage.objPageTitle),propertyFileReader("GotAClearScan","onboarding"),": Title");
            click(OnBoardingPage.objSubmitFrontIDScanBtn,getTextVal(OnBoardingPage.objSubmitFrontIDScanBtn,": Button"));
            if(verifyElementDisplayed(OnBoardingPage.objSubmitBackIDScanBtn)) {
                click(OnBoardingPage.objSubmitBackIDScanBtn, getTextVal(OnBoardingPage.objSubmitBackIDScanBtn, ": Button"));
            }
            waitForElementToBePresent(OnBoardingPage.objDidWeGetItRightTitle,60,"Did we get it right page");
            verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle," : Page Title"));
            assertionValidation(getText(OnBoardingPage.objPageTitle),propertyFileReader("DidWeGetItRightTitle","onboarding"),": Title");
            click(OnBoardingPage.objLooksGoodBtn,getTextVal(OnBoardingPage.objLooksGoodBtn,": Button"));
        }
    }
    /**
     * Reusable method to button need to tap
     * @throws Exception
     */
    public void buttonNeedToTapValidation() throws Exception {
        waitForElementToBePresent(OnBoardingPage.objButtonToTapBtn,10,"Button to tap");
        if(verifyElementDisplayed(OnBoardingPage.objButtonToTapBtn)) {
            click(OnBoardingPage.objButtonToTapBtn, getTextVal(OnBoardingPage.objButtonToTapBtn, ": Button"));
        }
    }
    /**
     * Reusable method to update signature and click on next button
     * @throws Exception
     */
    public void updateSignatureAndNext() throws Exception {
        waitForElementToBePresent(OnBoardingPage.objLetsSealTheDealPage,5,"Let's seal the deal page");
        verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle,": title"));
        containsValidation(getText(OnBoardingPage.objPageTitle), propertyFileReader("LetsSealTheDealPage", "onboarding"), ": Title");
        verifyElementPresent(OnBoardingPage.objPageSubTitle,getTextVal(OnBoardingPage.objPageSubTitle,": Page Subtitle"));
        assertionValidation(getText(OnBoardingPage.objPageSubTitle), propertyFileReader("LetsSealTheDealPageSubtitle","onboarding"),": page Subtitle");
        updateSignature();
        verifyElementPresentAndClick(OnBoardingPage.objNextBtn,getTextVal(OnBoardingPage.objNextBtn,": button"));
    }
    /**
     * Reusable method to enter mobile number
     * @throws Exception
     */
    public void enterMobileNumber(String sMobileNumber) throws Exception {
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField,5,"Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader(sMobileNumber, "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
    }
    /**
     * Reusable method to Input place of birth and continue
     * @throws Exception
     */
    public void inputPlaceOfBirthAndContinue() throws Exception {
        if(verifyElementDisplayed(OnBoardingPage.objLetsGetToKnowYou)) {
            verifyElementPresent(OnBoardingPage.objAutoDetailsPopulatedPageTitle, getTextVal(OnBoardingPage.objAutoDetailsPopulatedPageTitle, ": title"));
            containsValidation(getText(OnBoardingPage.objAutoDetailsPopulatedPageTitle), propertyFileReader("AutoDetailsPopulatedPageTitle", "onboarding"), ": title");
            type(OnBoardingPage.objPlaceOfBirth,propertyFileReader("ValidPlaceOfBirth","onboarding"),"Place of birth input field");
            verifyElementPresentAndClick(OnBoardingPage.objContinueBtn,getTextVal(OnBoardingPage.objContinueBtn,": button"));
        }
    }
    /**
     * Reusable method to navigate to Zip code page
     * @param id
     * @throws Exception
     */
    public void navigateToZipCode(By id) throws Exception {
        navigateToCreatePage();
        enterMobileNumber("ValidMobileNumber");
        if(verifyElementDisplayed(OnBoardingPage.objAgreeAndContinueBtn)) {
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
        waitForElementToBePresent(OnBoardingPage.objZipCodeInputField, 10, "Zip code page");
        verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle," : Page Title"));
        assertionValidation(getText(OnBoardingPage.objPageTitle),propertyFileReader("YourPlaceRightPage","onboarding"),": Title");
        type(OnBoardingPage.objZipCodeInputField,zipCode,"Zip code input field");
        verifyElementPresentAndClick(OnBoardingPage.objApplyZipCode,getTextVal(OnBoardingPage.objApplyZipCode,": button"));
    }
    /**
     * Reusable method to select Barangay
     * @throws Exception
     */
    public void selectBarangay() throws Exception {
        verifyElementPresentAndClick(OnBoardingPage.objBarangay,getTextVal(OnBoardingPage.objBarangay,": field"));
        if(verifyElementDisplayed(OnBoardingPage.objBarangay)) {
            click(OnBoardingPage.objBarangay, getTextVal(OnBoardingPage.objBarangay, ": field"));
        }
        waitForElementToBePresent(OnBoardingPage.objBarangayDisplayed, 5, "Barangay");
        verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle," : Page Title"));
        assertionValidation(getText(OnBoardingPage.objPageTitle),propertyFileReader("FindYourBarangay","onboarding"),": Title");
        type(OnBoardingPage.objBarangaySearchField,propertyFileReader("SanAndreBarangay","onboarding"),": Barangay search field");
        verifyElementPresent(OnBoardingPage.objBarangay("1"),getTextVal(OnBoardingPage.objBarangay("1"),": Barangay"));
        containsValidation(getText(OnBoardingPage.objBarangaySearchField),propertyFileReader("SanAndreBarangay","onboarding"),"Barangay");
        verifyElementPresentAndClick(OnBoardingPage.objBarangay("1"),getTextVal(OnBoardingPage.objBarangay("1"),": Barangay"));
    }
    /**
     * Reusable method to update what do you do
     * @throws Exception
     */
    public void updateWhatDoYouDo(String sourceOfFunds,String currentEmployment,String natureOfWork,String industry) throws Exception {
        waitForElementToBePresent(OnBoardingPage.objSourceOfFundsInputField,5,"What do you do page");
        verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle," : Page Title"));
        verifyElementPresent(OnBoardingPage.objPageSubTitle,getTextVal(OnBoardingPage.objPageSubTitle," : Page Subtitle"));
        verifyElementPresentAndClick(OnBoardingPage.objSourceOfFundsInputField,"Source of Funds input field");
        verifyElementPresentAndClick(OnBoardingPage.objSelectSourceOfFunds(sourceOfFunds),getTextVal(OnBoardingPage.objSelectSourceOfFunds(sourceOfFunds),": Source of fund"));
        verifyElementPresentAndClick(OnBoardingPage.objCurrentEmploymentStatus,"Current Employment Status input field");
        verifyElementPresentAndClick(OnBoardingPage.objSelectCurrentEmploymentStatus(currentEmployment),getTextVal(OnBoardingPage.objSelectCurrentEmploymentStatus(currentEmployment),": Current Employment Status"));
        verifyElementPresentAndClick(OnBoardingPage.objNatureOfWork,"Nature Of Work input field");
        verifyElementPresentAndClick(OnBoardingPage.objSelectNatureOfWork(natureOfWork),getTextVal(OnBoardingPage.objSelectNatureOfWork(natureOfWork),": Nature Of Work"));
        verifyElementPresentAndClick(OnBoardingPage.objIndustry,"Industry input field");
        swipeDownUntilElementVisible(industry);
        verifyElementPresentAndClick(OnBoardingPage.objSelectIndustry(industry),getTextVal(OnBoardingPage.objSelectIndustry(industry),": Industry"));
        verifyElementPresentAndClick(OnBoardingPage.objContinueBtn,getTextVal(OnBoardingPage.objContinueBtn,": button"));
    }
    /**
     * Reusable method to select Us citizen or not and click on next button
     * @throws Exception
     */
    public void selectUSCitizenOrNotAndNext() throws Exception {
        if (verifyElementPresent(OnBoardingPage.objFATCAPageTitle, getTextVal(OnBoardingPage.objFATCAPageTitle, ": title"))) {
            assertionValidation(getText(OnBoardingPage.objPageTitle), propertyFileReader("FATCAPageTitle", "onboarding"), ": Title");
            verifyElementPresentAndClick(OnBoardingPage.objNextBtn,getTextVal(OnBoardingPage.objNextBtn,": button"));
        }
    }
    /**
     * Reusable method to validate login steps
     * @throws Exception
     */
    public void loginStepsValidation() throws Exception {
        if(verifyElementDisplayed(OnBoardingPage.objHowToLoginPage)){
            verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle," : Page Title"));
            assertionValidation(getText(OnBoardingPage.objPageTitle),propertyFileReader("HowToLoginPage","onboarding"),": Title");
            assertionValidation(getText(OnBoardingPage.objLoginStep1),propertyFileReader("LoginStep1","onboarding"),": Step 1");
            assertionValidation(getText(OnBoardingPage.objLoginStep2),propertyFileReader("LoginStep2","onboarding"),": Step 2");
            assertionValidation(getText(OnBoardingPage.objLoginStep3),propertyFileReader("LoginStep3","onboarding"),": Step 3");
            assertionValidation(getText(OnBoardingPage.objLoginStep1Info),propertyFileReader("LoginStep1Info","onboarding"),": Step 1 info");
            assertionValidation(getText(OnBoardingPage.objLoginStep2Info),propertyFileReader("LoginStep2Info","onboarding"),": Step 2 info");
            assertionValidation(getText(OnBoardingPage.objLoginStep3Info),propertyFileReader("LoginStep3Info","onboarding"),": Step 3 info");
            verifyElementPresentAndClick(OnBoardingPage.objContinueBtn,getTextVal(OnBoardingPage.objContinueBtn,": button"));
        }
    }
    /**
     * Reusable method to create password and Continue
     * @throws Exception
     */
    public void createPasswordAndContinue() throws Exception {
        waitForElementToBePresent(OnBoardingPage.objPasswordField,5,"Password field");
        verifyElementPresentAndClick(OnBoardingPage.objPasswordField,"Password field");
        verifyElementPresent(OnBoardingPage.objPasswordSuggestionPopup,getTextVal(OnBoardingPage.objPasswordSuggestionPopup,": Password suggestion popup"));
        containsValidation(getText(OnBoardingPage.objPasswordSuggestionPopup),propertyFileReader("PasswordSuggestionPopup","onboarding"),": Password suggestion popup");
        if (verifyElementDisplayed(OnBoardingPage.objPasswordSuggestion)) {
            List<WebElement> values = findElements(OnBoardingPage.objPasswordSuggestion);
            for (int passwordSuggestion = 1; passwordSuggestion < values.size(); passwordSuggestion++) {
                assertionValidation(getText(OnBoardingPage.objPasswordSuggestion(passwordSuggestion)), propertyFileReader("PasswordSuggestion"+ passwordSuggestion,"onboarding"), ": Password rule");
            }
        }
        waitTime(5000);//waiting to get Got It button enabling, doesn't have proper attribute.
        verifyElementPresentAndClick(OnBoardingPage.objGotItBtn,getTextVal(OnBoardingPage.objGotItBtn,": button"));
        type(OnBoardingPage.objPasswordField,propertyFileReader("ValidPassword","onboarding"),": Password field");
        type(OnBoardingPage.objConfirmPasswordField,propertyFileReader("ValidPassword","onboarding"),": Confirm Password field");
        verifyElementPresentAndClick(OnBoardingPage.objContinueBtn,getTextVal(OnBoardingPage.objContinueBtn,": button"));
    }
    /**
     * Reusable method to validate Dashboard welcome screen
     * @throws Exception
     */
    public void dashboardWelcomeScreenValidation() throws Exception {
        waitForElementToBePresent(OnBoardingPage.objDashboardWelcomeScreen,5,"Dashboard welcome screen");
        if(verifyElementDisplayed(OnBoardingPage.objDashboardWelcomeScreen)) {
            verifyElementPresent(OnBoardingPage.objDashboardWelcomeScreen, getTextVal(OnBoardingPage.objDashboardWelcomeScreen, ": Welcome Screen"));
            assertionValidation(getText(OnBoardingPage.objDashboardWelcomeScreen), propertyFileReader("DashBoardWelcomeScreen", "onboarding"), ": Welcome screen");
            assertionValidation(getText(OnBoardingPage.objWelcomeInfo), propertyFileReader("WelcomeInfo", "onboarding"), ": Welcome Info");
            assertionValidation(getText(OnBoardingPage.objWelcomeInfo), propertyFileReader("WelcomeInfo", "onboarding"), ": Welcome Info");
            assertionValidation(getText(OnBoardingPage.objWelcomeInfo), propertyFileReader("WelcomeInfo", "onboarding"), ": Welcome Info");
            verifyElementPresent(OnBoardingPage.objBorrow, getTextVal(OnBoardingPage.objBorrow, "; tile"));
            swipe("UP", 2);
            verifyElementPresent(OnBoardingPage.objBorrowLearnMore, getTextVal(OnBoardingPage.objBorrowLearnMore, ": button"));
            assertionValidation(getText(OnBoardingPage.objBorrowInfo), propertyFileReader("BorrowInfo", "onboarding"), ": Borrow Info");
            verifyElementPresent(OnBoardingPage.objSave, getTextVal(OnBoardingPage.objSave, "; tile"));
            verifyElementPresent(OnBoardingPage.objSaveLearnMore, getTextVal(OnBoardingPage.objSaveLearnMore, ": button"));
            assertionValidation(getText(OnBoardingPage.objSaveInfo), propertyFileReader("SaveInfo", "onboarding"), ": Borrow Info");
            assertionValidation(getText(OnBoardingPage.objExploreNow), propertyFileReader("ExploreNowBtn", "onboarding"), ": button");
        }
    }
    /**
     * Reusable method to validate barrow screen UI
     * @throws Exception
     */
    public void borrowScreenUIValidation() throws Exception {
        if(verifyElementDisplayed(OnBoardingPage.objBorrowScreenTitle)){
            verifyElementPresent(OnBoardingPage.objBorrowScreenTitle,getTextVal(OnBoardingPage.objBorrowScreenTitle,": Page Title"));
            for(int header=1;header<=3;header++){
                assertionValidation(getText(OnBoardingPage.objTileHeader(header)), propertyFileReader("TileHeader"+header,"onboarding"), ": Header "+header);
            }
            swipe("UP",2);
            for(int tileSubtitle=1;tileSubtitle<2;tileSubtitle++){
                containsValidation(getText(OnBoardingPage.objSubtitle(tileSubtitle)),propertyFileReader("Subtitle"+tileSubtitle,"onboarding"),": Subtitle");
            }
        }
    }
    /**
     * Reusable method to update Email, Mother name, Banking relationship
     * @throws Exception
     */
    public void updateEmailMotherNameAndBankingRelationship() throws Exception {
        waitForElementToBePresent(OnBoardingPage.objCoupleMoreThingsPage,5,"Couple more things page");
        verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle,": title"));
        assertionValidation(getText(OnBoardingPage.objPageTitle), propertyFileReader("CoupleMoreThinsTitle", "onboarding"), ": Title");
        type(OnBoardingPage.objEmailAddress,generateRandomEmail(),"Email Address field");
        type(OnBoardingPage.objMotherMaidenName,propertyFileReader("ValidMaidenName","onboarding"),"Mother maiden field");
        click(OnBoardingPage.objDefineOurBankingRelationship,"Define our banking relationship");
        verifyElementPresentAndClick(OnBoardingPage.objSelectBankingRelationship(propertyFileReader("BankingRelationship","onboarding")), getTextVal(OnBoardingPage.objSelectBankingRelationship(propertyFileReader("BankingRelationship","onboarding")),": Banking Relationship"));
        verifyElementPresent(OnBoardingPage.objMoreTransitionTextSwitchSlider,"More transition Switch slider");
        verifyElementPresentAndClick(OnBoardingPage.objContinueBtn,getTextVal(OnBoardingPage.objContinueBtn,": button"));
    }
    /**
     * Method to Verify if user can open the Tonik App
     * @throws Exception
     */
    public void openTonikApp_TBD_OB_001() throws Exception {
        HeaderChildNode("TBD_OB_001, Onboarding - Verify if user can open the Tonik App");
        handlePopup();
        waitForElementToBePresent(OnBoardingPage.objWelcomeScreen1,5,"Welcome screen 1");
        for(int welcomeScreen=1;welcomeScreen<6;welcomeScreen++){
            containsValidation(getText(OnBoardingPage.objWelcomeScreen),propertyFileReader("WelcomeScreenMsg"+welcomeScreen,"onboarding"),": Welcome screen"+welcomeScreen);
            click(OnBoardingPage.objButton(propertyFileReader("WelcomeScreenButton"+welcomeScreen,"onboarding")), getTextVal(OnBoardingPage.objButton(propertyFileReader("WelcomeScreenButton"+welcomeScreen,"onboarding")),": button"));
        }
        if(verifyElementDisplayed(LoginPage.getByOSType(platform,LoginPage.objWhileUsingApp))){
            verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objWhileUsingApp),getTextVal(LoginPage.getByOSType(platform,LoginPage.objWhileUsingApp),": Button"));
        }
        if(verifyElementDisplayed(LoginPage.getByOSType(platform,LoginPage.objAllowNotificationPopup))){
            verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objAllowBtn),getTextVal(LoginPage.getByOSType(platform,LoginPage.objAllowBtn),": Button"));
        }
        if(verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle," : Page Header"))){
            assertionValidation(getText(OnBoardingPage.objPageTitle),propertyFileReader("CreateAccountPageTitle","onboarding"),": Create Account page title");
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
        customerCareIconValidation();
        type(OnBoardingPage.objMobileNumberInputField,propertyFileReader("InvalidMobileNumberLessThan10","onboarding"),"Mobile number input field");
        assertionValidation(getAttributValue("enabled",OnBoardingPage.objNextButton),"false",": Enable Attribute value");
        type(OnBoardingPage.objMobileNumberInputField,propertyFileReader("InvalidMobileNumberStartingWith7","onboarding"),"Mobile number input field");
        assertionValidation(getAttributValue("enabled",OnBoardingPage.objNextButton),"false",": Enable Attribute value");
        type(OnBoardingPage.objMobileNumberInputField,propertyFileReader("InvalidSameNumber1","onboarding"),"Mobile number input field");
        assertionValidation(getAttributValue("enabled",OnBoardingPage.objNextButton),"false",": Enable Attribute value");
        type(OnBoardingPage.objMobileNumberInputField,propertyFileReader("InvalidSameNumber2","onboarding"),"Mobile number input field");
        assertionValidation(getAttributValue("enabled",OnBoardingPage.objNextButton),"false",": Enable Attribute value");
        type(OnBoardingPage.objMobileNumberInputField,propertyFileReader("InvalidAlphaNumericMobileNumber","onboarding"),"Mobile number input field");
        assertionValidation(getAttributValue("enabled",OnBoardingPage.objNextButton),"false",": Enable Attribute value");
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
        type(OnBoardingPage.objMobileNumberInputField,propertyFileReader("OnboardingMobileNumber","onboarding"),"Mobile number input field");
        String sNextBtnEnableAttributeValue = getAttributValue("enabled",OnBoardingPage.objNextButton);
        assertionValidation(getAttributValue("enabled",OnBoardingPage.objNextButton),"true",": Enable Attribute value");
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
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("OnboardingMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        waitForElementToBePresent(OnBoardingPage.objTermsAndConditionPageTitle,5,"Terms and condition");
        verifyElementPresent(OnBoardingPage.objPageTitle, getTextVal(OnBoardingPage.objPageTitle, ": Page Title"));
        assertionValidation(getText(OnBoardingPage.objPageTitle), propertyFileReader("TermsAndConditionPageTitle", "onboarding"), ": Page Title");
        assertionValidation(getText(OnBoardingPage.objTermsAndConditionInfo), propertyFileReader("TermsAndConditionPageInfo", "onboarding"), ": Terms and Condition info");
        assertionValidation(getText(OnBoardingPage.objHouseRule1), propertyFileReader("HouseRule1", "onboarding"), ": House Rule 1");
        assertionValidation(getText(OnBoardingPage.objHouseRule2), propertyFileReader("HouseRule2", "onboarding"), ": House Rule 2");
        assertionValidation(getText(OnBoardingPage.objHouseRule3), propertyFileReader("HouseRule3", "onboarding"), ": House Rule 3");
        assertionValidation(getText(OnBoardingPage.objHouseRule4), propertyFileReader("HouseRule4", "onboarding"), ": House Rule 4");
        assertionValidation(getText(OnBoardingPage.objHouseRule5), propertyFileReader("HouseRule5", "onboarding"), ": House Rule 5");
        assertionValidation(getText(OnBoardingPage.objHouseRule6), propertyFileReader("HouseRule6", "onboarding"), ": House Rule 6");
        assertionValidation(getText(OnBoardingPage.objHouseRule7), propertyFileReader("HouseRule7", "onboarding"), ": House Rule 7");
        assertionValidation(getText(OnBoardingPage.objReadTermsAndConditionLink), propertyFileReader("TermsAndConditionLink", "onboarding"), ": Link");
        click(OnBoardingPage.objBackBtn, getTextVal(OnBoardingPage.objPageTitle, ": Back Button"));
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField,5,"Mobile input field");
        verifyElementPresent(OnBoardingPage.objPageTitle, getTextVal(OnBoardingPage.objPageTitle, ": Page Title"));
        assertionValidation(getText(OnBoardingPage.objPageTitle), propertyFileReader("CreateAccountPageTitle", "onboarding"), ": Page Title");
        assertionValidation(getText(OnBoardingPage.objMobileNumberInputField),propertyFileReader("MobileNumberInputField","onboarding"),"Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        verifyElementPresentAndClick(OnBoardingPage.objTermsAndConditionInfoCheckBox, "Terms and Condition check Box");
        assertionValidation(getText(OnBoardingPage.objTermsAndConditionCheckBoxInfo), propertyFileReader("TermsAndConditionCheckBoxInfo", "onboarding"), ": Info");
        click(OnBoardingPage.objAgreeAndContinueBtn, getTextVal(OnBoardingPage.objAgreeAndContinueBtn, ": Button"));
        waitForElementToBePresent(OnBoardingPage.objOurPrivacyMatters,10,"Privacy matters page");
        verifyElementPresent(OnBoardingPage.objPageTitle, getTextVal(OnBoardingPage.objPageTitle, ": Page Title"));
        assertionValidation(getText(OnBoardingPage.objPageTitle), propertyFileReader("PrivacyPolicyPageTitle", "onboarding"), ": Page Title");
        assertionValidation(getText(OnBoardingPage.objPrivacyMattersInfo), propertyFileReader("PrivacyPolicyInfo", "onboarding"), ": Privacy policy info");
        assertionValidation(getText(OnBoardingPage.objPrivacyPolicy1), propertyFileReader("PrivacyPolicy1", "onboarding"), ": Privacy policy 1");
        assertionValidation(getText(OnBoardingPage.objPrivacyPolicy2), propertyFileReader("PrivacyPolicy2", "onboarding"), ": Privacy policy 2");
        assertionValidation(getText(OnBoardingPage.objPrivacyPolicy3), propertyFileReader("PrivacyPolicy3", "onboarding"), ": Privacy policy 3");
        assertionValidation(getText(OnBoardingPage.objPrivacyPolicy4), propertyFileReader("PrivacyPolicy4", "onboarding"), ": Privacy policy 4");
        assertionValidation(getText(OnBoardingPage.objPrivacyPolicy5), propertyFileReader("PrivacyPolicy5", "onboarding"), ": Privacy policy 5");
        assertionValidation(getText(OnBoardingPage.objPrivacyPolicy6), propertyFileReader("PrivacyPolicy6", "onboarding"), ": Privacy policy 6");
        assertionValidation(getText(OnBoardingPage.objPrivacyPolicy7), propertyFileReader("PrivacyPolicy7", "onboarding"), ": Privacy policy 7");
        assertionValidation(getText(OnBoardingPage.objDataPrivacyStatementLink), propertyFileReader("DataPrivacyLink", "onboarding"), ": Link");
        containsValidation(getText(OnBoardingPage.objTonikAppAccessOtherDeviceAppsMsg), propertyFileReader("TonikAppAccessOtherDeviceAppsMsg", "onboarding"), "App Access message");
        assertionValidation(getText(OnBoardingPage.objDataPrivacyStatementInfo), propertyFileReader("DataPrivacyStatementInfo", "onboarding"), ": Info");
        verifyElementPresentAndClick(OnBoardingPage.objBackBtn, getTextVal(OnBoardingPage.objPageTitle, ": Back Button"));
        waitForElementToBePresent(OnBoardingPage.objTermsAndConditionPageTitle,5,"Terms and condition");
        verifyElementPresent(OnBoardingPage.objPageTitle, getTextVal(OnBoardingPage.objPageTitle, ": Page Title"));
        assertionValidation(getText(OnBoardingPage.objPageTitle), propertyFileReader("TermsAndConditionPageTitle", "onboarding"), ": Page Title");
        click(OnBoardingPage.objAgreeAndContinueBtn, getTextVal(OnBoardingPage.objAgreeAndContinueBtn, ": Button"));
        verifyElementPresentAndClick(OnBoardingPage.objDataPrivacyStatementRadioBtn, "Data Privacy statement check Box");
        click(OnBoardingPage.objAgreeAndContinueBtn, getTextVal(OnBoardingPage.objAgreeAndContinueBtn, ": Button"));
        waitForElementToBePresent(OnBoardingPage.objEnterOTP,5,"Enter OTP page");
        if(verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle," : Page Title"))){
            assertionValidation(getText(OnBoardingPage.objPageTitle),propertyFileReader("EnterOTP","onboarding"),": Title");
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
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField, 10,"Welcome Screen 1");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("InvalidOTPMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(LoginPage.getByOSType(platform,LoginPage.objNextBtn), ": Button"));
        acceptTermsAndConditionAndPrivacyPolicy();
        hideKeyboard();
        if(verifyElementPresent(OnBoardingPage.objInvalidOTPPopup,getTextVal(OnBoardingPage.objInvalidOTPPopup,": Error message"))){
            assertionValidation(getText(OnBoardingPage.objInvalidOTPPopup),propertyFileReader("InvalidOTPErrorMessage","onboarding"),": Error message");
            verifyElementPresent(OnBoardingPage.objPopupBtn,getTextVal(OnBoardingPage.objPopupBtn,": Popup button"));
            assertionValidation(getText(OnBoardingPage.objPopupBtn),propertyFileReader("OkButton","onboarding"),": Popup button");
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
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField, 60,"Welcome Screen 1");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("OnboardingMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        acceptTermsAndCondition();
        acceptDataPrivacyStatement();
        waitForElementToBePresent(OnBoardingPage.objEnterOTP,5,"Enter OTP page");
        verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle,": Page Title"));
        assertionValidation(getText(OnBoardingPage.objPageTitle),propertyFileReader("EnterOTP","onboarding"),": Title");
        verifyElementPresent(OnBoardingPage.objPageSubTitle,getTextVal(OnBoardingPage.objPageSubTitle,": Page SubTitle"));
        containsValidation(getText(OnBoardingPage.objPageSubTitle),propertyFileReader("EnterOTPPageSubTitle","onboarding"),": Title");
        customerCareIconValidation();
        String sResendOTPEnableAttributeValue = getAttributValue("enabled",OnBoardingPage.objResendOTP);
        assertionValidation(getAttributValue("enabled",OnBoardingPage.objResendOTPParentElement),"true",": Enable Attribute value");
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
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField, 60,"Welcome Screen 1");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("OnboardingMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        acceptTermsAndCondition();
        acceptDataPrivacyStatement();
        waitForElementToBePresent(OnBoardingPage.objEnterOTP,5,"Enter OTP page");
        verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle,": Page Title"));
        assertionValidation(getText(OnBoardingPage.objPageTitle),propertyFileReader("EnterOTP","onboarding"),": Title");
        for(int iteration=1;iteration<=5;iteration++){
            click(OnBoardingPage.objResendOTP,getTextVal(OnBoardingPage.objResendOTP,": Link"));
        }
        if(verifyElementPresent(OnBoardingPage.objMaxOTPAttemptPopup,getTextVal(OnBoardingPage.objMaxOTPAttemptPopup,": Max OTP attempt error message"))){
            assertionValidation(getText(OnBoardingPage.objMaxOTPAttemptPopup),propertyFileReader("MaxOTPAttemptMessage","onboarding"),": Max OTP attempt error message");
            verifyElementPresent(OnBoardingPage.objOkBtn,getTextVal(OnBoardingPage.objOkBtn,": button"));
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
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField, 10,"Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        acceptTermsAndConditionAndPrivacyPolicy();
        verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle,": Page Title"));
        containsValidation(getText(OnBoardingPage.objPageTitle),propertyFileReader("IDSelectionPage","onboarding"),": Title");
        selectIDAndScan(OnBoardingPage.objPassportOption);
        buttonNeedToTapValidation();
        verifyElementPresent(OnBoardingPage.objAutoDetailsPopulatedPageTitle,getTextVal(OnBoardingPage.objAutoDetailsPopulatedPageTitle,": title"));
        containsValidation(getText(OnBoardingPage.objAutoDetailsPopulatedPageTitle),propertyFileReader("AutoDetailsPopulatedPageTitle","onboarding"),": title");
        closeAndroidTonikApp();
        relaunchApp("android");
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField,10,"Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        buttonNeedToTapValidation();
        waitForElementToBePresent(OnBoardingPage.objAutoDetailsPopulatedPageTitle,5,"AutoDetails Populated Page Title");
        if(verifyElementPresent(OnBoardingPage.objAutoDetailsPopulatedPageTitle,getTextVal(OnBoardingPage.objAutoDetailsPopulatedPageTitle,": page"))){
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
        navigateToCreatePage();
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField, 5,"Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        if(verifyElementDisplayed(OnBoardingPage.objAgreeAndContinueBtn)) {
            acceptTermsAndConditionAndPrivacyPolicy();
            selectIDAndScan(OnBoardingPage.objPassportOption);
        }
        buttonNeedToTapValidation();
        if(verifyElementPresent(OnBoardingPage.objAutoDetailsPopulatedPageTitle,getTextVal(OnBoardingPage.objAutoDetailsPopulatedPageTitle,": title"))){
            containsValidation(getText(OnBoardingPage.objAutoDetailsPopulatedPageTitle),propertyFileReader("AutoDetailsPopulatedPageTitle","onboarding"),": title");
            verifyElementPresent(OnBoardingPage.objFirstName,getTextVal(OnBoardingPage.objFirstName,": Auto populated first name"));
            verifyElementPresent(OnBoardingPage.objMiddleName,getTextVal(OnBoardingPage.objMiddleName,": Auto populated middle name"));
            verifyElementPresent(OnBoardingPage.objLastName,getTextVal(OnBoardingPage.objLastName,": Auto populated last name"));
            verifyElementPresent(OnBoardingPage.objGender,getTextVal(OnBoardingPage.objGender,": Auto populated Gender"));
            verifyElementPresent(OnBoardingPage.objDateOfBirth,getTextVal(OnBoardingPage.objDateOfBirth,": Auto populated Date of  birth"));
            logger.info("TBD_OB_022, Onboarding - Auto-populated fields are First Name, Middle Name, Last Name, Gender and Date of Birth is validated");
            extentLoggerPass("TBD_OB_022", "TBD_OB_022, Onboarding - Auto-populated fields are First Name, Middle Name, Last Name, Gender and Date of Birth is validated");
        }
        type(OnBoardingPage.objPlaceOfBirth,propertyFileReader("ValidPlaceOfBirth","onboarding"),"Place of birth input field");
        verifyElementPresentAndClick(OnBoardingPage.objContinueBtn,getTextVal(OnBoardingPage.objContinueBtn,": button"));
        waitForElementToBePresent(OnBoardingPage.objAutoPopulatedCountryOfResidence,5,"Your Place Right Page");
        verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle," : Page Title"));
        assertionValidation(getText(OnBoardingPage.objPageTitle),propertyFileReader("YourPlaceRightPage","onboarding"),": Title");
        verifyElementPresent(OnBoardingPage.objPageSubTitle,getTextVal(OnBoardingPage.objPageSubTitle,": Page Subtitle"));
        assertionValidation(getText(OnBoardingPage.objPageSubTitle), propertyFileReader("YourPlaceRightSubtitle","onboarding"),": page Subtitle");
        customerCareIconValidation();
        assertionValidation(getText(OnBoardingPage.objAutoPopulatedCountryOfResidence), propertyFileReader("Country","onboarding"), ": Country of Residence");
        screencapture();
        assertionValidation(getText(OnBoardingPage.objZipCode),propertyFileReader("ZipCode","onboarding"),": field");
        assertionValidation(getText(OnBoardingPage.objApplyZipCode),propertyFileReader("ApplyZipCode","onboarding"),": button");
        assertionValidation(getText(OnBoardingPage.objHowToFindZipCodeLink),propertyFileReader("HowToFindZipCodeLink","onboarding"),": Link");
        assertionValidation(getText(OnBoardingPage.objProvince),propertyFileReader("Province","onboarding"),": field");
        assertionValidation(getText(OnBoardingPage.objCityOrMunicipality),propertyFileReader("CityOrMunicipality","onboarding"),": field");
        assertionValidation(getText(OnBoardingPage.objBarangay),propertyFileReader("Barangay","onboarding"),": field");
        swipe("UP",1);
        assertionValidation(getText(LoginPage.getByOSType(platform,LoginPage.objNextBtn)),propertyFileReader("NextButton","onboarding"),"Button");
        assertionValidation(getAttributValue("enabled",OnBoardingPage.objNextButton),"false",": Enable Attribute value");
        assertionValidation(getText(OnBoardingPage.objHouseUnitFlrNumber),propertyFileReader("HouseUnitFlrNumber","onboarding"),": field");
        screencapture();
        verifyElementPresentAndClick(OnBoardingPage.objBackBtn,getTextVal(OnBoardingPage.objPageTitle,": back button"));
        if(verifyElementPresent(OnBoardingPage.objAutoDetailsPopulatedPageTitle, getTextVal(OnBoardingPage.objAutoDetailsPopulatedPageTitle, ": title"))){
            containsValidation(getText(OnBoardingPage.objAutoDetailsPopulatedPageTitle), propertyFileReader("AutoDetailsPopulatedPageTitle", "onboarding"), ": title");
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
        DriverManager.getAppiumDriver().resetApp();
        handlePopup();
        navigateToCreatePage();
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField, 10,"Welcome Screen 1");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        acceptTermsAndCondition();
        acceptDataPrivacyStatement();
        enterOTP();
        selectIDAndScan(OnBoardingPage.objSSSID);
        buttonNeedToTapValidation();
        waitUntilElementVisible_NoCustomMessage(OnBoardingPage.objAutoDetailsPopulatedPageTitle);
        verifyElementPresent(OnBoardingPage.objAutoDetailsPopulatedPageTitle,getTextVal(OnBoardingPage.objAutoDetailsPopulatedPageTitle,": title"));
        verifyElementPresentAndClick(OnBoardingPage.objGenderInputField,"Gender input field");
        verifyElementPresentAndClick(OnBoardingPage.objGenderDropdown("Male"),getTextVal(OnBoardingPage.objGenderDropdown("Male"),": Gender dropdown element"));
        assertionValidation(getText(OnBoardingPage.objGender), propertyFileReader("Gender","onboarding"), ": Gender dropdown element");
        if(getText(OnBoardingPage.objGender).equals(propertyFileReader("Gender","onboarding"))){
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
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField, 10,"Welcome Screen 1");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        buttonNeedToTapValidation();
        waitUntilElementVisible_NoCustomMessage(OnBoardingPage.objAutoDetailsPopulatedPageTitle);
        verifyElementPresent(OnBoardingPage.objAutoDetailsPopulatedPageTitle,getTextVal(OnBoardingPage.objAutoDetailsPopulatedPageTitle,": title"));
        containsValidation(getText(OnBoardingPage.objAutoDetailsPopulatedPageTitle),propertyFileReader("AutoDetailsPopulatedPageTitle","onboarding"),": title");
        verifyElementPresent(OnBoardingPage.objDateOfBirth,getTextVal(OnBoardingPage.objDateOfBirth,": Auto populated Date of  birth"));
        String sAutoPopulatedDateOfBirth = getText(OnBoardingPage.objDateOfBirth);
        click(OnBoardingPage.objDateOfBirth,getTextVal(OnBoardingPage.objDateOfBirth,": Auto populated Date of  birth"));
        click(OnBoardingPage.objPreviousMonth,"Previous month");
        click(OnBoardingPage.objSelectDate("1"),getTextVal(OnBoardingPage.objSelectDate("1"),": Date"));
        verifyElementPresentAndClick(OnBoardingPage.objCalendarOkBtn,getTextVal(OnBoardingPage.objCalendarOkBtn,": Button"));
        verifyElementPresent(OnBoardingPage.objDateOfBirth,getTextVal(OnBoardingPage.objDateOfBirth,": Edited Date of  birth"));
        String sUpdatedDateOfBirth = getText(OnBoardingPage.objDateOfBirth);
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
        navigateToCreatePage();
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField, 5,"Welcome Screen 1");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        buttonNeedToTapValidation();
        verifyElementPresent(OnBoardingPage.objAutoDetailsPopulatedPageTitle,getTextVal(OnBoardingPage.objAutoDetailsPopulatedPageTitle,": title"));
        containsValidation(getText(OnBoardingPage.objAutoDetailsPopulatedPageTitle),propertyFileReader("AutoDetailsPopulatedPageTitle","onboarding"),": title");
        verifyElementPresentAndClick(OnBoardingPage.objGenderInputField,"Gender input field");
        verifyElementPresentAndClick(OnBoardingPage.objGenderDropdown("Male"),getTextVal(OnBoardingPage.objGenderDropdown("Male"),": Gender dropdown element"));
        assertionValidation(getText(OnBoardingPage.objGender), propertyFileReader("Gender","onboarding"), ": Gender dropdown element");
        verifyElementPresent(OnBoardingPage.objDateOfBirth,getTextVal(OnBoardingPage.objDateOfBirth,": Auto populated Date of  birth"));
        clearField(OnBoardingPage.objPlaceOfBirth,"Place of birth");
        type(OnBoardingPage.objPlaceOfBirth,propertyFileReader("InvalidPlaceOfBirth","onboarding"),"Place of birth input field");
        verifyElementPresentAndClick(OnBoardingPage.objContinueBtn,getTextVal(OnBoardingPage.objContinueBtn,": button"));
        waitForElementToBePresent(OnBoardingPage.objInvalidPlaceOfBirth,5,"Invalid place of birth");
        hideKeyboard();
        verifyElementPresent(OnBoardingPage.objInvalidPlaceOfBirth,getTextVal(OnBoardingPage.objInvalidPlaceOfBirth,": Error message"));
        assertionValidation(getText(OnBoardingPage.objInvalidPlaceOfBirth),propertyFileReader("InvalidPlaceOfBirthErrorMsg","onboarding"),": Error message");
        clearField(OnBoardingPage.objPlaceOfBirthInputField,"Place of birth input field");
        type(OnBoardingPage.objPlaceOfBirth,propertyFileReader("MaxInputValuePlaceOfBirth","onboarding"),"Place of birth input field");
        int placeOfBirth = getText(OnBoardingPage.objPlaceOfBirth).length();
        if(placeOfBirth==50){
            logger.info("Maximum input value for Place of birth is 50 character validated");
            extentLoggerPass("", "Maximum input value for Place of birth is 50 character validated");
        }else{
            logger.info("Maximum input value for Place of birth isn't 50 validated");
            extentLoggerFail("", "Maximum input value for Place of birth isn't 50 character validated");
        }
        verifyElementPresentAndClick(OnBoardingPage.objContinueBtn,getTextVal(OnBoardingPage.objContinueBtn,": button"));
        hideKeyboard();
        if(verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle," : Page Title"))){
            assertionValidation(getText(OnBoardingPage.objPageTitle),propertyFileReader("YourPlaceRightPage","onboarding"),": Title");
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
        enterMobileNumber(propertyFileReader("ValidMobileNumber","onboarding"));
        buttonNeedToTapValidation();
        verifyElementPresent(OnBoardingPage.objAutoDetailsPopulatedPageTitle,getTextVal(OnBoardingPage.objAutoDetailsPopulatedPageTitle,": title"));
        containsValidation(getText(OnBoardingPage.objAutoDetailsPopulatedPageTitle),propertyFileReader("AutoDetailsPopulatedPageTitle","onboarding"),": title");
        assertionValidation(getText(OnBoardingPage.objGender), propertyFileReader("Gender","onboarding"), ": Gender dropdown element");
        click(OnBoardingPage.objPlaceOfBirthInputField,"Place of birth input field");
        clearField(OnBoardingPage.objPlaceOfBirthInputField,"Place of birth input field");
        type(OnBoardingPage.objPlaceOfBirth,propertyFileReader("AlphaNumericPlaceOfBirth","onboarding"),"Place of birth input field");
        verifyElementPresentAndClick(OnBoardingPage.objContinueBtn,getTextVal(OnBoardingPage.objContinueBtn,": button"));
        hideKeyboard();
        waitForElementToBePresent(OnBoardingPage.objEnterAlphabetsErrorMessage,5,"Alphabets Error Message");
        if(verifyElementPresent(OnBoardingPage.objEnterAlphabetsErrorMessage,getTextVal(OnBoardingPage.objEnterAlphabetsErrorMessage,": Error message"))) {
            assertionValidation(getText(OnBoardingPage.objEnterAlphabetsErrorMessage), propertyFileReader("InvalidAlphaNumericPlaceOfBirthInput", "onboarding"), ": Error message");
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
        navigateToZipCode(OnBoardingPage.objPassportOption);
        waitForElementToBePresent(OnBoardingPage.objHowToFindZipCodeLink,5,"How to find zip code link");
        verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle," : Page Title"));
        assertionValidation(getText(OnBoardingPage.objPageTitle),propertyFileReader("YourPlaceRightPage","onboarding"),": Title");
        hideKeyboard();
        waitForElementToBePresent(OnBoardingPage.objHowToFindZipCodeLink,5,"How to find zip code link");
        verifyElementPresentAndClick(OnBoardingPage.objHowToFindZipCodeLink,getTextVal(OnBoardingPage.objHowToFindZipCodeLink,": Link"));
        click(OnBoardingPage.objHowToFindZipCodeLink,getTextVal(OnBoardingPage.objHowToFindZipCodeLink,": Link"));
        waitForElementToBePresent(OnBoardingPage.objHowToFindZipCodeWebPage,10,": Web page");
        if(verifyElementPresent(OnBoardingPage.objHowToFindZipCodeWebPage,getTextVal(OnBoardingPage.objHowToFindZipCodeWebPage,": Web page"))){
            containsValidation(getText(OnBoardingPage.objHowToFindZipCodeWebPage),propertyFileReader("HowToFindZipCodeWebPage","onboarding"),"Web page");
            logger.info("TBD_OB_028, Onboarding - User can access the How to find your zip code hyperlink and Web page navigation validated");
            extentLoggerPass("TBD_OB_028", "TBD_OB_028, Onboarding - User can access the How to find your zip code hyperlink and Web page navigation validated");
        }
    }
    /**
     * Method to Verify if user can input invalid details in Zip Code field
     * @throws Exception
     */
    public void invalidZipCodeDetailsValidation_TBD_OB_029() throws Exception {
        HeaderChildNode("TBD_OB_029, Onboarding - Verify if user can input invalid details in Zip Code field");
        navigateToZipCode(OnBoardingPage.objPassportOption);
        waitForElementToBePresent(OnBoardingPage.objApplyZipCode,5,"Enter OTP page");
        applyZipCode(propertyFileReader("InvalidZipCode","onboarding"));
        if(verifyElementPresent(OnBoardingPage.objInvalidZipCodeErrorMsg,getTextVal(OnBoardingPage.objInvalidZipCodeErrorMsg,": Error message"))){
            assertionValidation(getText(OnBoardingPage.objInvalidZipCodeErrorMsg),propertyFileReader("InvalidZipCodeErrorMsg","onboarding"),": Error message");
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
        navigateToZipCode(OnBoardingPage.objPassportOption);
        waitForElementToBePresent(OnBoardingPage.objApplyZipCode,5,"Enter OTP page");
        applyZipCode(propertyFileReader("ValidZipCode","onboarding"));
        if(verifyElementPresent(OnBoardingPage.objProvinceInputField,getTextVal(OnBoardingPage.objProvinceInputField,": Province"))){
            verifyElementPresent(OnBoardingPage.objCityOrMunicipalityInputField,getTextVal(OnBoardingPage.objCityOrMunicipalityInputField,": City / Municipality input field"));
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
        navigateToZipCode(OnBoardingPage.objPassportOption);
        waitForElementToBePresent(OnBoardingPage.objApplyZipCode,5,"Enter OTP page");
        applyZipCode(propertyFileReader("ValidZipCode","onboarding"));
        verifyElementPresentAndClick(OnBoardingPage.objBarangay,getTextVal(OnBoardingPage.objBarangay,": field"));
        verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle," : Page Title"));
        assertionValidation(getText(OnBoardingPage.objPageTitle),propertyFileReader("FindYourBarangay","onboarding"),": Title");
        if (verifyElementDisplayed(OnBoardingPage.objBarangayDisplayed)) {
            List<WebElement> values = findElements(OnBoardingPage.objBarangayDisplayed);
            for (int info = 0; info < values.size(); info++) {
                if(info%2==0) {
                    String displayedItem = values.get(info).getText();
                    logger.info("Barangay : '" + displayedItem + "' is displayed");
                    ExtentReporter.extentLogger(" ", "Barangay : '" + displayedItem + "' is displayed");
                }
            }
        }
        type(OnBoardingPage.objBarangaySearchField,propertyFileReader("SanAndreBarangay","onboarding"),": Barangay search field");
        verifyElementPresent(OnBoardingPage.objBarangay("1"),getTextVal(OnBoardingPage.objBarangay("1"),": Barangay"));
        containsValidation(getText(OnBoardingPage.objBarangaySearchField),propertyFileReader("SanAndreBarangay","onboarding"),"Barangay");
        String sBarangaySelected = getText(OnBoardingPage.objBarangay("1"));
        verifyElementPresentAndClick(OnBoardingPage.objBarangay("1"),getTextVal(OnBoardingPage.objBarangay("1"),": Barangay"));
        if(sBarangaySelected.equals(getText(OnBoardingPage.objBarangayInputField))){
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
        DriverManager.getAppiumDriver().resetApp();
        handlePopup();
        navigateToZipCode(OnBoardingPage.objPhilID);
        applyZipCode(propertyFileReader("ValidZipCode","onboarding"));
        selectBarangay();
        swipe("UP",2);
        if(verifyElementPresent(OnBoardingPage.objHouseUnitFlrNumberInputField,getTextVal(OnBoardingPage.objHouseUnitFlrNumberInputField,": Auto-populated House/Unit/Flr #, Bldg, Blk or Lots"))){
            clearField(OnBoardingPage.objHouseUnitFlrNumberInputField,"House Unit Flr Number Input Field");
            type(OnBoardingPage.objHouseUnitFlrNumberInputField,propertyFileReader("HouseStreetName","onboarding"),"House Unit Flr Number Input Field");
            assertionValidation(getAttributValue("enabled",OnBoardingPage.objNextButton),"true",": Enable Attribute value");
            int houseUnitFlrNumber = getText(OnBoardingPage.objHouseUnitFlrNumberInputField).length();
            if(houseUnitFlrNumber>5){
                logger.info("Minimum input value for House Unit Floor Number Input Field is more than 5 character validated");
                extentLoggerPass("", "Minimum input value for House Unit Floor Number Input Field is more than 5 character validated");
            }else{
                logger.info("Minimum input value for House Unit Floor Number Input Field is less than 5 character validated");
                extentLoggerFail("", "Minimum input value for House Unit Floor Number Input Field is less than 5 character validated");
            }
            clearField(OnBoardingPage.objHouseUnitFlrNumberInputField,"House Unit Flr Number Input Field");
            type(OnBoardingPage.objHouseUnitFlrNumberInputField,propertyFileReader("HouseStreetInvalid","onboarding"),"House Unit Flr Number Input Field");
            houseUnitFlrNumber = getText(OnBoardingPage.objHouseUnitFlrNumberInputField).length();
            if(houseUnitFlrNumber>50){
                logger.info("Maximum input value for House Unit Floor Number Input Field is more than than 50 character validated");
                extentLoggerFail("", "Maximum input value for House Unit Floor Number Input Field is more than than 50 character validated");
            }else{
                logger.info("Maximum input value for House Unit Floor Number Input Field is equal or less than than 50 character validated");
                extentLoggerPass("", "Maximum input value for House Unit Floor Number Input Field is equal or less than than 50 character validated");
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
        navigateToZipCode(OnBoardingPage.objPhilID);
        applyZipCode(propertyFileReader("ValidZipCode","onboarding"));
        swipe("UP",2);
        if(verifyElementDisplayed(OnBoardingPage.objHouseUnitFlrNumberInputField)) {
            click(OnBoardingPage.objHouseUnitFlrNumberInputField, "House or Street Input field");
            clearField(OnBoardingPage.objHouseUnitFlrNumberInputField, "House or Street Input field");
        }
        actionSendKeys(OnBoardingPage.objHouseUnitFlrNumber,propertyFileReader("NewHouseOrFloorNumber","onboarding"),"House or Street Input field");
        assertionValidation(getText(OnBoardingPage.objHouseUnitFlrNumberInputField),propertyFileReader("NewHouseOrFloorNumber","onboarding"),": House or Street Input field");
        hideKeyboard();
        selectBarangay();
        assertionValidation(getAttributValue("enabled",OnBoardingPage.objNextButton),"true",": Enable Attribute value");
        if(getAttributValue("enabled",OnBoardingPage.objNextButton).contains("true")){
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
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField,5,"Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        waitForElementToBePresent(OnBoardingPage.objButtonToTapBtn,5,"Button to tap");
        buttonNeedToTapValidation();
        inputPlaceOfBirthAndContinue();
        verifyElementPresentAndClick(OnBoardingPage.objNextBtn,getTextVal(OnBoardingPage.objNextBtn,": button"));
        updateWhatDoYouDo("Salary","Employed","Accountant/Auditor","Accounting/Bookkeeping");
        if (verifyElementPresent(OnBoardingPage.objFATCAPageTitle,getTextVal(OnBoardingPage.objFATCAPageTitle,": title"))) {
            assertionValidation(getText(OnBoardingPage.objPageTitle), propertyFileReader("FATCAPageTitle", "onboarding"), ": Title");
            verifyElementPresent(OnBoardingPage.objPageSubTitle, getTextVal(OnBoardingPage.objPageSubTitle, ": Page Subtitle"));
            containsValidation(getText(OnBoardingPage.objPageSubTitle), propertyFileReader("FATCAPageSubtitle", "onboarding"), ": Subtitle");
            assertionValidation(getText(OnBoardingPage.objAUSACitizen), propertyFileReader("AUSACitizen", "onboarding"), ": option");
            assertionValidation(getText(OnBoardingPage.objGreenCardHolder), propertyFileReader("GreenCardHolder", "onboarding"), ": option");
            assertionValidation(getText(OnBoardingPage.objUSAResident), propertyFileReader("USAResident", "onboarding"), ": option");
            assertionValidation(getText(OnBoardingPage.objNoneOfTheAbove), propertyFileReader("NoneOfTheAbove", "onboarding"), ": option");
            verifyElementPresent(OnBoardingPage.objNextBtn,getTextVal(OnBoardingPage.objNextBtn,": button"));
            assertionValidation(getAttributValue("enabled",OnBoardingPage.objNextButton),"true",": Enable Attribute value");
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
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField,10,"Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        buttonNeedToTapValidation();
        inputPlaceOfBirthAndContinue();
        verifyElementPresentAndClick(OnBoardingPage.objNextBtn,getTextVal(OnBoardingPage.objNextBtn,": button"));
        updateWhatDoYouDo("Salary","Employed","Accountant/Auditor","Accounting/Bookkeeping");
        selectUSCitizenOrNotAndNext();
        waitForElementToBePresent(OnBoardingPage.objEmailAddress,5,"Email Address");
        verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle,": title"));
        assertionValidation(getText(OnBoardingPage.objPageTitle), propertyFileReader("CoupleMoreThinsTitle", "onboarding"), ": Title");
        type(OnBoardingPage.objEmailAddress,propertyFileReader("ExistingEmailAddressInput","onboarding"),"Email Address field");
        String emailAddress = getText(OnBoardingPage.objEmailAddress);
        type(OnBoardingPage.objMotherMaidenName,propertyFileReader("MotherMaidenName","onboarding"),"Mother Maiden Name field");
        click(OnBoardingPage.objDefineOurBankingRelationship,"Define our banking relationship");
        verifyElementPresentAndClick(OnBoardingPage.objSelectBankingRelationship(propertyFileReader("BankingRelationship","onboarding")), getTextVal(OnBoardingPage.objSelectBankingRelationship(propertyFileReader("BankingRelationship","onboarding")),": Banking Relationship"));
        verifyElementPresentAndClick(OnBoardingPage.objContinueBtn,getTextVal(OnBoardingPage.objContinueBtn,": button"));
        if(verifyElementPresent(OnBoardingPage.objPopupSubHeader,getTextVal(OnBoardingPage.objPopupSubHeader,": Error message"))){
            assertionValidation(getText(OnBoardingPage.objPopupSubHeader),propertyFileReader("InvalidEmailAddress","onboarding"),": error message");
            verifyElementPresentAndClick(OnBoardingPage.objOkBtn,getTextVal(OnBoardingPage.objOkBtn,": button"));
            click(OnBoardingPage.objEmailAddress,"Email address input field");
            clearField(OnBoardingPage.objEmailAddress,"Email address input field");
            type(OnBoardingPage.objEmailAddress,propertyFileReader("ValidEmailAddress","onboarding"),"Email Address field");
            if(!emailAddress.equals(getText(OnBoardingPage.objEmailAddress))){
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
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField,10,"Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        waitForElementToBePresent(OnBoardingPage.objButtonToTapBtn,5,"Button to tap");
        buttonNeedToTapValidation();
        inputPlaceOfBirthAndContinue();
        waitForElementToBePresent(OnBoardingPage.objNextBtn,5,"Next button");
        click(OnBoardingPage.objNextBtn,getTextVal(OnBoardingPage.objNextBtn,": button"));
        updateWhatDoYouDo("Salary","Employed","Accountant/Auditor","Accounting/Bookkeeping");
        selectUSCitizenOrNotAndNext();
        waitForElementToBePresent(OnBoardingPage.objEmailAddress,5,"Email Address");
        verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle,": title"));
        assertionValidation(getText(OnBoardingPage.objPageTitle), propertyFileReader("CoupleMoreThinsTitle", "onboarding"), ": Title");
        type(OnBoardingPage.objEmailAddress,propertyFileReader("InvalidEmailAddressInput","onboarding"),"Email Address field");
        click(OnBoardingPage.objMotherMaidenName,"Mother maiden name field");
        click(OnBoardingPage.objEmailAddress,"Email address field");
        click(OnBoardingPage.objMotherMaidenName,"Mother maiden name field");
        if(verifyElementPresent(OnBoardingPage.objInvalidEmailAddressErrorMessage,getTextVal(OnBoardingPage.objInvalidEmailAddressErrorMessage,": Error message"))){
            assertionValidation(getText(OnBoardingPage.objInvalidEmailAddressErrorMessage),propertyFileReader("InvalidEmailAddressErrorMsg","onboarding"),": Error message");
            logger.info("TBD_OB_043, Onboarding - User entered invalid email address, error message is displayed and validated");
            extentLoggerPass("TBD_OB_043", "TBD_OB_043, Onboarding -  User entered invalid email address, error message is displayed and validated");
        }
        click(OnBoardingPage.objEmailAddress,"Email address field");
        clearField(OnBoardingPage.objEmailAddress,"Email address field");
        click(OnBoardingPage.objMotherMaidenName,"Mother maiden name field");
        verifyElementPresent(OnBoardingPage.objBackBtn,"Email page back button");
        if(verifyElementPresent(OnBoardingPage.objBlankEmailAddressErrorMessage,getTextVal(OnBoardingPage.objBlankEmailAddressErrorMessage,": Error message"))){
            assertionValidation(getText(OnBoardingPage.objBlankEmailAddressErrorMessage),propertyFileReader("BlankEmailAddress","onboarding"),": Error message");
            logger.info("TBD_OB_044, Onboarding - User kept blank Email address field and Email address required error message validated");
            extentLoggerPass("TBD_OB_044", "TBD_OB_044, Onboarding - User kept blank Email address field and Email address required error message validated");
        }
        click(OnBoardingPage.objEmailAddress,"Email address field");
        clearField(OnBoardingPage.objEmailAddress,"Email address field");
        String email = generateRandomEmail();
        type(OnBoardingPage.objEmailAddress,email,"Email Address field");
        click(OnBoardingPage.objMotherMaidenName,"Mother maiden name field");
        String sEmailAddress = getText(OnBoardingPage.objEmailAddress);
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
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField,10,"Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        waitForElementToBePresent(OnBoardingPage.objButtonToTapBtn,5,"Button to tap");
        buttonNeedToTapValidation();
        inputPlaceOfBirthAndContinue();
        waitForElementToBePresent(OnBoardingPage.objNextBtn,5,"Next button");
        click(OnBoardingPage.objNextBtn,getTextVal(OnBoardingPage.objNextBtn,": button"));
        updateWhatDoYouDo("Salary","Employed","Accountant/Auditor","Accounting/Bookkeeping");
        selectUSCitizenOrNotAndNext();
        waitForElementToBePresent(OnBoardingPage.objEmailAddress,5,"Email Address");
        verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle,": title"));
        assertionValidation(getText(OnBoardingPage.objPageTitle), propertyFileReader("CoupleMoreThinsTitle", "onboarding"), ": Title");
        type(OnBoardingPage.objEmailAddress,propertyFileReader("ValidEmailAddress","onboarding"),"Email Address field");
        click(OnBoardingPage.objDefineOurBankingRelationship,"Define your banking relationship  field");
        click(OnBoardingPage.objPurposeOfAccount(1),getTextVal(OnBoardingPage.objPurposeOfAccount(1),": purpose"));
        type(OnBoardingPage.objMotherMaidenName,propertyFileReader("InvalidMaidenName","onboarding"),"Mother maiden field");
        click(OnBoardingPage.objEmailAddress,"Email address field");
        click(OnBoardingPage.objMotherMaidenName,"Mother maiden name field");
        click(OnBoardingPage.objEmailAddress,"Email address field");
        assertionValidation(getText(OnBoardingPage.objInvalidMotherMaidenName),propertyFileReader("InvalidMotherMaidenNameErrorMsg","onboarding"),": Error message");
        click(OnBoardingPage.objMotherMaidenName,"Mother maiden name field");
        clearField(OnBoardingPage.objMotherMaidenName,"Mother maiden name field");
        type(OnBoardingPage.objMotherMaidenName,propertyFileReader("MaidenNameMax","onboarding"),"Mother maiden field");
        click(OnBoardingPage.objEmailAddress,"Email address field");
        int motherMaidenName = getText(OnBoardingPage.objMotherMaidenName).length();
        if(motherMaidenName==50){
            logger.info("Maximum input value for Mother maiden name is 50 character validated");
            extentLoggerPass("", "Maximum input value for Mother maiden name is 50 character validated");
        }else{
            logger.info("Maximum input value for Mother maiden isn't 50 validated");
            extentLoggerFail("", "Maximum input value for Mother maiden isn't 50 character validated");
        }
        clearField(OnBoardingPage.objMotherMaidenName,"Mother maiden name field");
        type(OnBoardingPage.objMotherMaidenName,propertyFileReader("InvalidMaidenName","onboarding"),"Mother maiden field");
        click(OnBoardingPage.objEmailAddress,"Email address field");
        motherMaidenName = getText(OnBoardingPage.objMotherMaidenName).length();
        if(motherMaidenName<3){
            logger.info("Minimum input value for Mother maiden name is 3 character validated");
            extentLoggerPass("", "Minimum input value for Mother maiden name is 3 character validated");
        }else{
            logger.info("Minimum input value for Mother maiden name is less than 3 character validated");
            extentLoggerFail("", "Minimum input value for Mother maiden name is less than 3 character validated");
        }
        clearField(OnBoardingPage.objMotherMaidenName,"Mother maiden name field");
        type(OnBoardingPage.objMotherMaidenName,propertyFileReader("ValidMaidenName","onboarding"),"Mother maiden field");
        if(getText(OnBoardingPage.objMotherMaidenName).equals(propertyFileReader("ValidMaidenName","onboarding"))){
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
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField,10,"Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        waitForElementToBePresent(OnBoardingPage.objButtonToTapBtn,5,"Button to tap");
        buttonNeedToTapValidation();
        inputPlaceOfBirthAndContinue();
        waitForElementToBePresent(OnBoardingPage.objNextBtn,5,"Next button");
        click(OnBoardingPage.objNextBtn,getTextVal(OnBoardingPage.objNextBtn,": button"));
        updateWhatDoYouDo("Salary","Employed","Accountant/Auditor","Accounting/Bookkeeping");
        selectUSCitizenOrNotAndNext();
        waitForElementToBePresent(OnBoardingPage.objEmailAddress,5,"Email Address");
        verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle,": title"));
        assertionValidation(getText(OnBoardingPage.objPageTitle), propertyFileReader("CoupleMoreThinsTitle", "onboarding"), ": Title");
        type(OnBoardingPage.objMotherMaidenName,propertyFileReader("InvalidAlphaNumericMotherMaidenName","onboarding"),"Mother maiden field");
        click(OnBoardingPage.objEmailAddress,"Email address field");
        click(OnBoardingPage.objMotherMaidenName,"Mother maiden name field");
        click(OnBoardingPage.objEmailAddress,"Email address field");
        if(verifyElementPresent(OnBoardingPage.objInvalidMotherMaidenNameErrorMsg,getTextVal(OnBoardingPage.objInvalidMotherMaidenNameErrorMsg,": error message"))){
            assertionValidation(getText(OnBoardingPage.objInvalidMotherMaidenNameErrorMsg),propertyFileReader("InvalidMotherMaidenNameErrorMsg2","onboarding"),": Error message");
            logger.info("TBD_OB_047, Onboarding - User entered alphanumeric characters in Mother's Maiden name field and corresponding error message validated");
            extentLoggerPass("TBD_OB_047", "TBD_OB_047, Onboarding - User entered alphanumeric characters in Mother's Maiden name field and corresponding error message validated");
        }
        click(OnBoardingPage.objMotherMaidenName,"Mother maiden name field");
        clearField(OnBoardingPage.objMotherMaidenName,"Mother maiden name field");
        click(OnBoardingPage.objEmailAddress,"Email address field");
        hideKeyboard();
        if(verifyElementPresent(OnBoardingPage.objBlankMotherMaidenName,getTextVal(OnBoardingPage.objBlankMotherMaidenName,": error message"))){
            assertionValidation(getText(OnBoardingPage.objBlankMotherMaidenName),propertyFileReader("BlankMotherMaidenNameErrorMsg","onboarding"),": Error message");
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
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField,10,"Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        waitForElementToBePresent(OnBoardingPage.objButtonToTapBtn,5,"Button to tap");
        buttonNeedToTapValidation();
        inputPlaceOfBirthAndContinue();
        waitForElementToBePresent(OnBoardingPage.objNextBtn,5,"Next button");
        click(OnBoardingPage.objNextBtn,getTextVal(OnBoardingPage.objNextBtn,": button"));
        updateWhatDoYouDo("Salary","Employed","Accountant/Auditor","Accounting/Bookkeeping");
        selectUSCitizenOrNotAndNext();
        waitForElementToBePresent(OnBoardingPage.objMotherMaidenName,5,"Mother maiden name");
        verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle,": title"));
        assertionValidation(getText(OnBoardingPage.objPageTitle), propertyFileReader("CoupleMoreThinsTitle", "onboarding"), ": Title");
        click(OnBoardingPage.objDefineOurBankingRelationship,"Define your banking relationship  field");
        waitForElementToBePresent(OnBoardingPage.objDefineBankingRelationshipPopup,5,"Define Banking Relationship Popup");
        assertionValidation(getText(OnBoardingPage.objDefineBankingRelationshipPopup), propertyFileReader("DefineBankingRelationshipPopup", "onboarding"), ": Popup");
        assertionValidation(getText(OnBoardingPage.objPurposeOfAccountTxt), propertyFileReader("PurposeOfAccountTxt", "onboarding"), ": text");
        assertionValidation(getText(OnBoardingPage.objPersonalOption), propertyFileReader("PersonalOption", "onboarding"), ": option");
        assertionValidation(getText(OnBoardingPage.objBusinessOption), propertyFileReader("BusinessOption", "onboarding"), ": option");
        assertionValidation(getText(OnBoardingPage.objRemittancesOption), propertyFileReader("RemittancesOption", "onboarding"), ": option");
        if (verifyElementDisplayed(OnBoardingPage.objPurposeOfAccount)) {
            List<WebElement> values = findElements(OnBoardingPage.objPurposeOfAccount);
            for (int nPurpose = 1; nPurpose <= values.size(); nPurpose++) {
                String sPurpose = getText(OnBoardingPage.objPurposeOfAccount(nPurpose));
                click(OnBoardingPage.objPurposeOfAccount(nPurpose),getTextVal(OnBoardingPage.objPurposeOfAccount(nPurpose),": purpose"));
                assertionValidation(sPurpose,getText(OnBoardingPage.objDefineOurBankingRelationship),": Purpose");
                screencapture();
                click(OnBoardingPage.objDefineOurBankingRelationship,"Define your banking relationship  field");
                waitForElementToBePresent(OnBoardingPage.objDefineBankingRelationshipPopup,5,"Define Banking Relationship Popup");
            }
        }
        logger.info("BD_OB_049, Onboarding - User can select a banking relationship and reflected in A couple more things page");
        extentLoggerPass("BD_OB_049", "BD_OB_049, Onboarding - User can select a banking relationship and reflected in A couple more things page");
    }

    public void proceedingToNextPageAfterSegmentation2Validation_TBD_OB_042() throws Exception {
        HeaderChildNode("BD_OB_042, Onboarding - Verify if user can able to proceed after segmentation 2");
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField,10,"Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        buttonNeedToTapValidation();
        inputPlaceOfBirthAndContinue();
        waitForElementToBePresent(OnBoardingPage.objNextBtn,5,"Next button");
        click(OnBoardingPage.objNextBtn,getTextVal(OnBoardingPage.objNextBtn,": button"));
        updateWhatDoYouDo("Salary","Employed","Accountant/Auditor","Accounting/Bookkeeping");
        selectUSCitizenOrNotAndNext();
        updateEmailMotherNameAndBankingRelationship();
        verifyElementPresent(OnBoardingPage.objLetsSealTheDealPage,getTextVal(OnBoardingPage.objLetsSealTheDealPage,": page title"));
        closeAndroidTonikApp();
        relaunchApp("android");
        enterMobileNumber(propertyFileReader("ValidMobileNumber","onboarding"));
        waitForElementToBePresent(OnBoardingPage.objLetsSealTheDealPage,10,"Let's Seal The Deal page");
        if(verifyElementPresent(OnBoardingPage.objLetsSealTheDealPage,getTextVal(OnBoardingPage.objLetsSealTheDealPage,": page title"))) {
            verifyElementPresent(OnBoardingPage.objPageTitle, getTextVal(OnBoardingPage.objPageTitle, ": title"));
            containsValidation(getText(OnBoardingPage.objPageTitle), propertyFileReader("LetsSealTheDealPage", "onboarding"), ": Title");
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
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField,5,"Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        if(verifyElementDisplayed(OnBoardingPage.objButtonToTapBtn)) {
            buttonNeedToTapValidation();
            inputPlaceOfBirthAndContinue();
            waitForElementToBePresent(OnBoardingPage.objNextBtn, 5, "Next button");
            click(OnBoardingPage.objNextBtn, getTextVal(OnBoardingPage.objNextBtn, ": button"));
            updateWhatDoYouDo("Salary","Employed","Accountant/Auditor","Accounting/Bookkeeping");
            selectUSCitizenOrNotAndNext();
            waitForElementToBePresent(OnBoardingPage.objCoupleMoreThingsPage, 5, "Couple more things page");
            verifyElementPresent(OnBoardingPage.objPageTitle, getTextVal(OnBoardingPage.objPageTitle, ": title"));
            assertionValidation(getText(OnBoardingPage.objPageTitle), propertyFileReader("CoupleMoreThinsTitle", "onboarding"), ": Title");
            type(OnBoardingPage.objEmailAddress, propertyFileReader("EmailAddressInput", "onboarding"), "Email Address field");
            type(OnBoardingPage.objMotherMaidenName, propertyFileReader("ValidMaidenName", "onboarding"), "Mother maiden field");
            click(OnBoardingPage.objDefineOurBankingRelationship, "Define our banking relationship");
            verifyElementPresentAndClick(OnBoardingPage.objSelectBankingRelationship(propertyFileReader("BankingRelationship", "onboarding")), getTextVal(OnBoardingPage.objSelectBankingRelationship(propertyFileReader("BankingRelationship", "onboarding")), ": Banking Relationship"));
            verifyElementPresentAndClick(OnBoardingPage.objContinueBtn, getTextVal(OnBoardingPage.objContinueBtn, ": button"));
        }
        verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle,": title"));
        containsValidation(getText(OnBoardingPage.objPageTitle), propertyFileReader("LetsSealTheDealPage", "onboarding"), ": Title");
        verifyElementPresent(OnBoardingPage.objPageSubTitle,getTextVal(OnBoardingPage.objPageSubTitle,": Page Subtitle"));
        assertionValidation(getText(OnBoardingPage.objPageSubTitle), propertyFileReader("LetsSealTheDealPageSubtitle","onboarding"),": page Subtitle");
        assertionValidation(getText(OnBoardingPage.objNextBtnInfo), propertyFileReader("NextBtnInfo","onboarding"),": info");
        updateSignature();
        verifyElementPresentAndClick(OnBoardingPage.objEraserIcon,"Eraser Icon");
        updateSignature();
        verifyElementPresentAndClick(OnBoardingPage.objNextBtn,getTextVal(OnBoardingPage.objNextBtn,": button"));
        waitForElementToBePresent(OnBoardingPage.objHeresTheDeal,5,"Here's the deal");
        closeAndroidTonikApp();
        relaunchApp("android");
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField,5,"Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        waitForElementToBePresent(OnBoardingPage.objHeresTheDeal,5,"Here's the deal");
        if(verifyElementPresent(OnBoardingPage.objHeresTheDeal,getTextVal(OnBoardingPage.objHeresTheDeal,": page"))){
            logger.info("BD_OB_050, Onboarding - User can input Signature Screen and continue");
            extentLoggerPass("BD_OB_050", "BD_OB_050, Onboarding - User can input Signature Screen and continue");
        }
    }
    /**
     * Method to Verify if user can accept the Deal in the Here's the deal screen
     * @throws Exception
     */
    public void hereTheDealScreenUIValidation_BD_OB_051() throws Exception {
        HeaderChildNode("BD_OB_051, Onboarding - Verify if user can accept the Deal in the Here's the deal screen");
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField,10,"Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        waitForElementToBePresent(OnBoardingPage.objHeresTheDeal,5,"Here's the deal");
        verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle,": Page Title"));
        containsValidation(getText(OnBoardingPage.objPageTitle),propertyFileReader("HeresTheDeal","onboarding"),": Page Title");
        assertionValidation(getText(OnBoardingPage.objFeature1),propertyFileReader("Feature1","onboarding"),": Feature 1");
        assertionValidation(getText(OnBoardingPage.objFeature2),propertyFileReader("Feature2","onboarding"),": Feature 2");
        assertionValidation(getText(OnBoardingPage.objFeature3),propertyFileReader("Feature3","onboarding"),": Feature 3");
        assertionValidation(getText(OnBoardingPage.objFeature4),propertyFileReader("Feature4","onboarding"),": Feature 4");
        assertionValidation(getText(OnBoardingPage.objFeature5),propertyFileReader("Feature5","onboarding"),": Feature 5");
        assertionValidation(getText(OnBoardingPage.objFeature6),propertyFileReader("Feature6","onboarding"),": Feature 6");
        assertionValidation(getText(OnBoardingPage.objFeature7),propertyFileReader("Feature7","onboarding"),": Feature 7");
        customerCareIconValidation();
        verifyElementPresentAndClick(OnBoardingPage.objDeal,getTextVal(OnBoardingPage.objDeal,": button"));
        waitForElementToBePresent(OnBoardingPage.objCreateAccountPage,5,"Create a password");
        if(verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle,": title"))){
            assertionValidation(getText(OnBoardingPage.objPageTitle),propertyFileReader("CreateAPassword","onboarding"),": title");
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
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField, 10, "Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        waitForElementToBePresent(OnBoardingPage.objHeresTheDeal, 5, "Here's the deal");
        verifyElementPresent(OnBoardingPage.objPageTitle, getTextVal(OnBoardingPage.objPageTitle, ": Page Title"));
        verifyElementPresentAndClick(OnBoardingPage.objDeal,getTextVal(OnBoardingPage.objDeal,": button"));
        waitForElementToBePresent(OnBoardingPage.objPageTitle,5,"Create password page");
        verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle,": title"));
        assertionValidation(getText(OnBoardingPage.objPageTitle),propertyFileReader("CreateAPassword","onboarding"),": title");
        if (verifyElementDisplayed(OnBoardingPage.objPasswordRule)) {
            List<WebElement> values = findElements(OnBoardingPage.objPasswordRule);
            for (int sPasswordRules = 1; sPasswordRules < values.size(); sPasswordRules++) {
                assertionValidation(getText(OnBoardingPage.objPasswordRule(sPasswordRules)), propertyFileReader("PasswordRule"+ sPasswordRules,"onboarding"), ": Password rule");
            }
        }
        customerCareIconValidation();
        verifyElementPresentAndClick(OnBoardingPage.objDeal,getTextVal(OnBoardingPage.objDeal,": button"));
        verifyElementPresentAndClick(OnBoardingPage.objPasswordField,"Password field");
        verifyElementPresent(OnBoardingPage.objPasswordSuggestionPopup,getTextVal(OnBoardingPage.objPasswordSuggestionPopup,": Password suggestion popup"));
        containsValidation(getText(OnBoardingPage.objPasswordSuggestionPopup),propertyFileReader("PasswordSuggestionPopup","onboarding"),": Password suggestion popup");
        if (verifyElementDisplayed(OnBoardingPage.objPasswordSuggestion)) {
            List<WebElement> values = findElements(OnBoardingPage.objPasswordSuggestion);
            for (int sPasswordSuggestion = 1; sPasswordSuggestion < values.size(); sPasswordSuggestion++) {
                assertionValidation(getText(OnBoardingPage.objPasswordSuggestion(sPasswordSuggestion)), propertyFileReader("PasswordSuggestion"+ sPasswordSuggestion,"onboarding"), ": Password rule");
            }
        }
        waitTime(10000);//waiting to get Got It button enabling, doesn't have proper attribute.
        verifyElementPresentAndClick(OnBoardingPage.objGotItBtn,getTextVal(OnBoardingPage.objGotItBtn,": button"));
        type(OnBoardingPage.objPasswordField,propertyFileReader("ValidPassword","onboarding"),": Password field");
        type(OnBoardingPage.objConfirmPasswordField,propertyFileReader("InvalidConfirmPassword","onboarding"),": Confirm Password field");
        verifyElementPresentAndClick(OnBoardingPage.objContinueBtn,getTextVal(OnBoardingPage.objContinueBtn,": button"));
        if(verifyElementPresent(OnBoardingPage.objPasswordMismatchError,getTextVal(OnBoardingPage.objPasswordMismatchError,": error message"))){
            assertionValidation(getText(OnBoardingPage.objPasswordMismatchError),propertyFileReader("PasswordMismatchError","onboarding"),": error message");
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
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField, 10, "Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        waitForElementToBePresent(OnBoardingPage.objHeresTheDeal, 5, "Here's the deal");
        verifyElementPresent(OnBoardingPage.objPageTitle, getTextVal(OnBoardingPage.objPageTitle, ": Page Title"));
        verifyElementPresentAndClick(OnBoardingPage.objDeal,getTextVal(OnBoardingPage.objDeal,": button"));
        waitForElementToBePresent(OnBoardingPage.objPageTitle,5,"Create password page");
        verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle,": title"));
        assertionValidation(getText(OnBoardingPage.objPageTitle),propertyFileReader("CreateAPassword","onboarding"),": title");
        verifyElementPresentAndClick(OnBoardingPage.objPasswordField,"Password field");
        verifyElementPresent(OnBoardingPage.objPasswordSuggestionPopup,getTextVal(OnBoardingPage.objPasswordSuggestionPopup,": Password suggestion popup"));
        containsValidation(getText(OnBoardingPage.objPasswordSuggestionPopup),propertyFileReader("PasswordSuggestionPopup","onboarding"),": Password suggestion popup");
        waitTime(10000);//Hard wait provided to load entire page
        verifyElementPresentAndClick(OnBoardingPage.objGotItBtn,getTextVal(OnBoardingPage.objGotItBtn,": button"));
        for (int sPasswordSuggestionCheckBox = 1; sPasswordSuggestionCheckBox <= 3; sPasswordSuggestionCheckBox++) {
            actionSendKeys(OnBoardingPage.objPasswordField, propertyFileReader("Password" + sPasswordSuggestionCheckBox, "onboarding"), ": Checkbox");
            assertionValidation(getAttributValue("enabled", OnBoardingPage.objPasswordSuggestionCheckBox(sPasswordSuggestionCheckBox)), "false", ": Enable Attribute value");
            clearField(OnBoardingPage.objPasswordField,"Password field");
        }
        type(OnBoardingPage.objPasswordField,propertyFileReader("ValidPassword","onboarding"),": Password field");
        type(OnBoardingPage.objConfirmPasswordField,propertyFileReader("ValidPassword","onboarding"),": Confirm Password field");
        verifyElementPresentAndClick(OnBoardingPage.objContinueBtn,getTextVal(OnBoardingPage.objContinueBtn,": button"));
        waitForElementToBePresent(OnBoardingPage.objDashboardWelcomeScreen,5,"Dashboard welcome screen");
        if(verifyElementPresent(OnBoardingPage.objDashboardWelcomeScreen, getTextVal(OnBoardingPage.objDashboardWelcomeScreen, ": Welcome Screen"))) {
            assertionValidation(getText(OnBoardingPage.objDashboardWelcomeScreen), propertyFileReader("DashBoardWelcomeScreen", "onboarding"), ": Welcome screen");
            screencapture();
            logger.info("BD_OB_053, Onboarding - User can create valid password and navigated to welcome screen is  validated");
            extentLoggerPass("BD_OB_053", "BD_OB_053, Onboarding - User can create valid password and navigated to welcome screen is  validated");
        }
        dashboardWelcomeScreenValidation();
        screencapture();
        logger.info("BD_OB_054, Onboarding - User can view the Welcome hun! and UI validated");
        extentLoggerPass("BD_OB_054", "BD_OB_054, Onboarding - User can view the Welcome hun! and UI validated");
        swipe("DOWN",2);
        verifyElementPresentAndClick(OnBoardingPage.objBorrow, getTextVal(OnBoardingPage.objBorrow, "; tile"));
        borrowScreenUIValidation();
        screencapture();
        logger.info("BD_OB_056, Onboarding - User can view the Loans by selecting Borrow tile from the Welcome screen validated");
        extentLoggerPass("BD_OB_056", "BD_OB_056, Onboarding - User can view the Loans by selecting Borrow tile from the Welcome screen validated");
        closeAndroidTonikApp();
        relaunchApp("android");
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField,40,"Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("ValidPassword", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objLoginBtn), getTextVal(LoginPage.getByOSType(platform,LoginPage.objLoginBtn), ": Button"));
        if(verifyElementDisplayed(OnBoardingPage.objIWillDoItBtn)){
            click(OnBoardingPage.objIWillDoItBtn,getTextVal(OnBoardingPage.objIWillDoItBtn,": button"));
        }
        tonikDashBordWhenNOTSAValidation();
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
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField,5,"Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        waitForElementToBePresent(OnBoardingPage.objButtonToTapBtn,5,"Button to tap");
        buttonNeedToTapValidation();
        inputPlaceOfBirthAndContinue();
        verifyElementPresentAndClick(OnBoardingPage.objNextBtn,getTextVal(OnBoardingPage.objNextBtn,": button"));
        waitForElementToBePresent(OnBoardingPage.objWhatDoYouDoPage,5,"What do you do? page");
        verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle,": Page Title"));
        assertionValidation(getText(OnBoardingPage.objPageTitle),propertyFileReader("WhatDoYouDoPageTitle","onboarding"),": Page Title");
        verifyElementPresent(OnBoardingPage.objPageSubTitle,getTextVal(OnBoardingPage.objPageSubTitle," : Page Subtitle"));
        assertionValidation(getText(OnBoardingPage.objPageSubTitle),propertyFileReader("WhatDoYouDoPageSubTitle","onboarding"),": Page Subtitle");
        verifyElementPresentAndClick(OnBoardingPage.objSourceOfFundsInputField,"Source of Funds input field");
        waitForElementToBePresent(OnBoardingPage.objSourceOfFundsPage,10,"Source of funds page");
        verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle,": Page Title"));
        assertionValidation(getText(OnBoardingPage.objPageTitle),propertyFileReader("SourceOfFundPage","onboarding"),": Page title");
        if (verifyElementDisplayed(OnBoardingPage.objList)) {
            List<WebElement> values = findElements(OnBoardingPage.objList);
            for (int sSourceOfFunds = 0; sSourceOfFunds < values.size(); sSourceOfFunds++) {
                String displayedItem = values.get(sSourceOfFunds).getText();
                logger.info("Source of funds : '" + displayedItem + "' is displayed");
                ExtentReporter.extentLogger(" ", "Source of funds : '" + displayedItem + "' is displayed");
            }
            for (int sSourceOfFunds = 1; sSourceOfFunds <= values.size(); sSourceOfFunds++) {
                String sPurpose = getText(OnBoardingPage.objList(sSourceOfFunds));
                click(OnBoardingPage.objList(sSourceOfFunds),getTextVal(OnBoardingPage.objList(sSourceOfFunds),": Source of funds"));
                assertionValidation(sPurpose,getText(OnBoardingPage.objSourceOfFundsInputField),": Source of funds");
                screencapture();
                click(OnBoardingPage.objSourceOfFundsInputField,"Source of funds");
                waitForElementToBePresent(OnBoardingPage.objSourceOfFundsPage,10,"Source of funds page");
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
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField,5,"Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        waitForElementToBePresent(OnBoardingPage.objButtonToTapBtn,5,"Button to tap");
        buttonNeedToTapValidation();
        inputPlaceOfBirthAndContinue();
        verifyElementPresentAndClick(OnBoardingPage.objNextBtn,getTextVal(OnBoardingPage.objNextBtn,": button"));
        waitForElementToBePresent(OnBoardingPage.objWhatDoYouDoPage,5,"What do you do? page");
        verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle,": Page Title"));
        assertionValidation(getText(OnBoardingPage.objPageTitle),propertyFileReader("WhatDoYouDoPageTitle","onboarding"),": Page Title");
        verifyElementPresent(OnBoardingPage.objPageSubTitle,getTextVal(OnBoardingPage.objPageSubTitle," : Page Subtitle"));
        assertionValidation(getText(OnBoardingPage.objPageSubTitle),propertyFileReader("WhatDoYouDoPageSubTitle","onboarding"),": Page Subtitle");
        verifyElementPresentAndClick(OnBoardingPage.objCurrentEmploymentStatus,"Current employment status input field");
        waitForElementToBePresent(OnBoardingPage.objCurrentEmploymentStatusPage,10,"Current employment status page");
        verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle,": Page Title"));
        assertionValidation(getText(OnBoardingPage.objPageTitle),propertyFileReader("CurrentEmploymentStatusPage","onboarding"),": Page title");
        if (verifyElementDisplayed(OnBoardingPage.objList)) {
            List<WebElement> values = findElements(OnBoardingPage.objList);
            for (int sSourceOfFunds = 0; sSourceOfFunds < values.size(); sSourceOfFunds++) {
                String displayedItem = values.get(sSourceOfFunds).getText();
                logger.info("Current employment status : '" + displayedItem + "' is displayed");
                ExtentReporter.extentLogger(" ", "Current employment status : '" + displayedItem + "' is displayed");
            }
            for (int sSourceOfFunds = 1; sSourceOfFunds <= values.size(); sSourceOfFunds++) {
                String sPurpose = getText(OnBoardingPage.objList(sSourceOfFunds));
                click(OnBoardingPage.objList(sSourceOfFunds),getTextVal(OnBoardingPage.objList(sSourceOfFunds),": Current employment status"));
                assertionValidation(sPurpose,getText(OnBoardingPage.objCurrentEmploymentStatus),": Current employment status");
                screencapture();
                click(OnBoardingPage.objCurrentEmploymentStatus,"Current employment status");
                waitForElementToBePresent(OnBoardingPage.objCurrentEmploymentStatusPage,5,"Current employment status page");
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
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField,5,"Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        waitForElementToBePresent(OnBoardingPage.objButtonToTapBtn,5,"Button to tap");
        buttonNeedToTapValidation();
        inputPlaceOfBirthAndContinue();
        verifyElementPresentAndClick(OnBoardingPage.objNextBtn, getTextVal(OnBoardingPage.objNextBtn, ": button"));
        waitForElementToBePresent(OnBoardingPage.objWhatDoYouDoPage, 5, "What do you do? page");
        verifyElementPresent(OnBoardingPage.objPageTitle, getTextVal(OnBoardingPage.objPageTitle, ": Page Title"));
        assertionValidation(getText(OnBoardingPage.objPageTitle), propertyFileReader("WhatDoYouDoPageTitle", "onboarding"), ": Page Title");
        verifyElementPresent(OnBoardingPage.objPageSubTitle, getTextVal(OnBoardingPage.objPageSubTitle, " : Page Subtitle"));
        assertionValidation(getText(OnBoardingPage.objPageSubTitle), propertyFileReader("WhatDoYouDoPageSubTitle", "onboarding"), ": Page Subtitle");
        verifyElementPresentAndClick(OnBoardingPage.objSourceOfFundsInputField, "Source of Funds input field");
        verifyElementPresentAndClick(OnBoardingPage.objSelectSourceOfFunds("Salary"), getTextVal(OnBoardingPage.objSelectSourceOfFunds("Salary"), ": Source of fund"));
        verifyElementPresentAndClick(OnBoardingPage.objCurrentEmploymentStatus, "Current Employment Status input field");
        verifyElementPresentAndClick(OnBoardingPage.objSelectCurrentEmploymentStatus("Employed"), getTextVal(OnBoardingPage.objSelectCurrentEmploymentStatus("Employed"), ": Current Employment Status"));
        verifyElementPresentAndClick(OnBoardingPage.objNatureOfWork, "Nature Of Work input field");
        if (verifyElementDisplayed(OnBoardingPage.objList)) {
            List<WebElement> values = findElements(OnBoardingPage.objList);
            for (int swipe = 1;swipe <= 10;swipe++) {
                waitTime(5000);//Getting stale element exception added hard wait
                for (int natureOfWork = 1; natureOfWork < values.size(); natureOfWork++) {
                    String displayedItem = getText(OnBoardingPage.objList(natureOfWork));
                    logger.info("Nature of work : '" + displayedItem + "' is displayed");
                    ExtentReporter.extentLogger(" ", "Nature of work : '" + displayedItem + "' is displayed");
                }
                if(verifyElementDisplayed(OnBoardingPage.objNatureOfWorkLastOption)){
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
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField,5,"Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("ValidMobileNumber", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        waitForElementToBePresent(OnBoardingPage.objButtonToTapBtn,5,"Button to tap");
        buttonNeedToTapValidation();
        inputPlaceOfBirthAndContinue();
        verifyElementPresentAndClick(OnBoardingPage.objNextBtn, getTextVal(OnBoardingPage.objNextBtn, ": button"));
        waitForElementToBePresent(OnBoardingPage.objWhatDoYouDoPage, 5, "What do you do? page");
        verifyElementPresent(OnBoardingPage.objPageTitle, getTextVal(OnBoardingPage.objPageTitle, ": Page Title"));
        assertionValidation(getText(OnBoardingPage.objPageTitle), propertyFileReader("WhatDoYouDoPageTitle", "onboarding"), ": Page Title");
        verifyElementPresent(OnBoardingPage.objPageSubTitle, getTextVal(OnBoardingPage.objPageSubTitle, " : Page Subtitle"));
        assertionValidation(getText(OnBoardingPage.objPageSubTitle), propertyFileReader("WhatDoYouDoPageSubTitle", "onboarding"), ": Page Subtitle");
        verifyElementPresentAndClick(OnBoardingPage.objSourceOfFundsInputField, "Source of Funds input field");
        verifyElementPresentAndClick(OnBoardingPage.objSelectSourceOfFunds("Salary"), getTextVal(OnBoardingPage.objSelectSourceOfFunds("Salary"), ": Source of fund"));
        verifyElementPresentAndClick(OnBoardingPage.objCurrentEmploymentStatus, "Current Employment Status input field");
        verifyElementPresentAndClick(OnBoardingPage.objSelectCurrentEmploymentStatus("Employed"), getTextVal(OnBoardingPage.objSelectCurrentEmploymentStatus("Employed"), ": Current Employment Status"));
        verifyElementPresentAndClick(OnBoardingPage.objNatureOfWork,"Nature Of Work input field");
        verifyElementPresentAndClick(OnBoardingPage.objSelectNatureOfWork("Accountant/Auditor"),getTextVal(OnBoardingPage.objSelectNatureOfWork("Accountant/Auditor"),": Nature Of Work"));
        verifyElementPresentAndClick(OnBoardingPage.objIndustry,"Industry input field");
        if (verifyElementDisplayed(OnBoardingPage.objList)) {
            List<WebElement> values = findElements(OnBoardingPage.objList);
            for (int swipe = 1;swipe <= 10;swipe++) {
                waitTime(3000);//Getting stale element exception added hard wait
                for (int sIndustry = 1; sIndustry < values.size(); sIndustry++) {
                    String displayedItem = getText(OnBoardingPage.objList(sIndustry));
                    logger.info("Industry : '" + displayedItem + "' is displayed");
                    ExtentReporter.extentLogger(" ", "Industry : '" + displayedItem + "' is displayed");
                }
                swipe("UP",1);
                if(verifyElementDisplayed(OnBoardingPage.objIndustryLastOption)){
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
        DriverManager.getAppiumDriver().resetApp();
        handlePopup();
        navigateToCreatePage();
        verifyElementPresentAndClick(OnBoardingPage.objLoginHereLink,getTextVal(OnBoardingPage.objLoginHereLink,": Link"));
        loginStepsValidation();
        waitForElementAndClickIfPresent(OnBoardingPage.objVerifyMobileNumberPage,5,"Verify mobile number page");
        verifyElementPresent(OnBoardingPage.objPageTitle, getTextVal(OnBoardingPage.objPageTitle, ": Page Title"));
        assertionValidation(getText(OnBoardingPage.objPageTitle), propertyFileReader("VerifyMobileNumberPage","onboarding"),": Page Title");
        verifyElementPresent(OnBoardingPage.objPageSubTitle,getTextVal(OnBoardingPage.objPageSubTitle,": Page Subtitle"));
        assertionValidation(getText(OnBoardingPage.objPageSubTitle), propertyFileReader("VerifyMobileNumberPageSubTitle","onboarding"),": page Subtitle");
        type(OnBoardingPage.objMobileNumberInputField,propertyFileReader("UnregisteredNumber","onboarding"),"Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPage.objNextBtn,getTextVal(OnBoardingPage.objNextBtn,": button"));
        verifyElementPresent(OnBoardingPage.objInvalidMobileNumberPopup,getTextVal(OnBoardingPage.objInvalidMobileNumberPopup,": popup"));
        assertionValidation(getText(OnBoardingPage.objInvalidMobileNumberPopup), propertyFileReader("UnregisteredMobileNumberPopup","onboarding"),": popup");
        verifyElementPresentAndClick(OnBoardingPage.objTryAgainBtn,getTextVal(OnBoardingPage.objTryAgainBtn,": button"));
        if(getText(OnBoardingPage.objMobileNumberInputField)==""){
            logger.info("User can able to re-enter the mobile number");
            extentLoggerPass("","User can able to re-enter the mobile number");
        }else{
            logger.info("User unable to re-enter the mobile number");
            extentLoggerPass("","User unable to re-enter the mobile number");
        }
        type(OnBoardingPage.objMobileNumberInputField,propertyFileReader("UnregisteredNumber","onboarding"),"Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPage.objNextBtn,getTextVal(OnBoardingPage.objNextBtn,": button"));
        verifyElementPresentAndClick(OnBoardingPage.objChangeRegisteredMobileNumber,getTextVal(OnBoardingPage.objChangeRegisteredMobileNumber,": button"));
        verifyElementPresent(OnBoardingPage.objGetInTouchWithCustomerCarePage,getTextVal(OnBoardingPage.objGetInTouchWithCustomerCarePage,": page"));
        containsValidation(getText(OnBoardingPage.objGetInTouchWithCustomerCarePage),propertyFileReader("GetInTouchWithCustomerCarePage","onboarding"),": page");
        assertionValidation(getText(OnBoardingPage.objGetInTouchWithCustomerCarePageInfo),propertyFileReader("GetInTouchWithCustomerCarePageInfo","onboarding"),": info");
        verifyElementPresentAndClick(OnBoardingPage.objContactTonikBtn,getTextVal(OnBoardingPage.objContactTonikBtn,": button"));
        contactUsPageUI();
        click(ContactUsPage.getByOSType(platform,ContactUsPage.objBackBtn),getTextVal(OnBoardingPage.objPageTitle," : Page Back button"));
        verifyElementPresentAndClick(OnBoardingPage.objCloseBtn,getTextVal(OnBoardingPage.objCloseBtn,": button"));
        logger.info("TBD_OB_058, Onboarding - User entered Unregistered Tonik Account number and corresponding error message is validated");
        extentLoggerPass("TBD_OB_058", "TBD_OB_058, Onboarding - User entered Unregistered Tonik Account number and corresponding error message is validated");
    }
    /**
     * Method to Verify if user can input an already registered number in Tonik while onboarding
     * @throws Exception
     */
    public void inputExistingAccountMobileNumberValidation_TBD_OB_059() throws Exception {
        HeaderChildNode("TBD_OB_059, Onboarding - Verify if user can input an already registered number in Tonik while onboarding");
        DriverManager.getAppiumDriver().resetApp();
        handlePopup();
        navigateToCreatePage();
        type(OnBoardingPage.objMobileNumberInputField,propertyFileReader("ExistingTonikAccount","onboarding"),"Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        waitForElementToBePresent(OnBoardingPage.objExistingTonikCustomerPopup,5,"Existing tonik account popup");
        if(verifyElementPresent(OnBoardingPage.objExistingTonikCustomerPopup,getTextVal(OnBoardingPage.objExistingTonikCustomerPopup,": popup"))){
            containsValidation(getText(OnBoardingPage.objExistingTonikCustomerPopup),propertyFileReader("ExistingTonikAccountPopup","onboarding"),": popup");
            assertionValidation(getText(OnBoardingPage.objEnterNewNumber),propertyFileReader("EnterNewNumber","onboarding"),": button");
            assertionValidation(getText(OnBoardingPage.objLogInToTonik),propertyFileReader("LogInToTonik","onboarding"),": link");
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
        DriverManager.getAppiumDriver().resetApp();
        navigateToCreatePage();
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField,5,"Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("HighRiskProfile", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        acceptTermsAndConditionAndPrivacyPolicy();
        selectIDAndScan(OnBoardingPage.objPhilID);
        buttonNeedToTapValidation();
        inputPlaceOfBirthAndContinue();
        applyZipCode(propertyFileReader("ValidZipCode","onboarding"));
        selectBarangay();
        verifyElementPresentAndClick(OnBoardingPage.objNextBtn,getTextVal(OnBoardingPage.objNextBtn,": button"));
        updateWhatDoYouDo("Salary","Employed","Accountant/Auditor","Real Estate");
        selectUSCitizenOrNotAndNext();
        updateEmailMotherNameAndBankingRelationship();
        updateSignatureAndNext();
        waitForElementToBePresent(OnBoardingPage.objHeresTheDeal,5,"Here's the deal");
        verifyElementPresent(OnBoardingPage.objHeresTheDeal,getTextVal(OnBoardingPage.objHeresTheDeal,": page"));
        verifyElementPresentAndClick(OnBoardingPage.objDeal,getTextVal(OnBoardingPage.objDeal,": button"));
        createPasswordAndContinue();
        waitForElementToBePresent(OnBoardingPage.objReviewInProgress,5,"Review in progress page");
        verifyElementPresent(OnBoardingPage.objReviewInProgress,getTextVal(OnBoardingPage.objReviewInProgress,": page"));
        containsValidation(getText(OnBoardingPage.objReviewInProgressInfo1),propertyFileReader("ReviewInProgressInfo1","onboarding"),"Review InProgress");
        containsValidation(getText(OnBoardingPage.objReviewInProgressInfo2),propertyFileReader("ReviewInProgressInfo2","onboarding"),"Review InProgress");
        verifyElementPresentAndClick(OnBoardingPage.objContactTonikCustomerCare,getTextVal(OnBoardingPage.objContactTonikCustomerCare,": button"));
        contactUsPageUI();
        verifyElementPresentAndClick(OnBoardingPage.objBackBtn,"Back btn");
        verifyElementPresentAndClick(OnBoardingPage.objCloseTheApp,getTextVal(OnBoardingPage.objCloseTheApp,": button"));
        relaunchApp("Android");
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField,5,"Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("HighRiskProfile", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        if(verifyElementPresent(OnBoardingPage.objExistingTonikCustomerPopup,getTextVal(OnBoardingPage.objExistingTonikCustomerPopup,": popup"))) {
            containsValidation(getText(OnBoardingPage.objExistingTonikCustomerPopup), propertyFileReader("ExistingTonikAccountPopup", "onboarding"), ": popup");
            verifyElementPresentAndClick(OnBoardingPage.objOkBtn, getTextVal(OnBoardingPage.objOkBtn, ": Button"));
        }
        verifyElementPresentAndClick(OnBoardingPage.objLoginHereLink,getTextVal(OnBoardingPage.objLoginHereLink,": link"));
        verifyElementPresentAndClick(OnBoardingPage.objContinueBtn,getTextVal(OnBoardingPage.objContinueBtn,": button"));
        type(OnBoardingPage.objMobileNumberInputField,propertyFileReader("HighRiskProfile","onboarding"),"Mobile number input field");
        verifyElementPresentAndClick(OnBoardingPage.objNextBtn,getTextVal(OnBoardingPage.objNextBtn,": button"));
        if(verifyElementPresent(OnBoardingPage.objReviewInProgress,getTextVal(OnBoardingPage.objReviewInProgress,": page"))) {
            screencapture();
            containsValidation(getText(OnBoardingPage.objReviewInProgressInfo1),propertyFileReader("ReviewInProgressInfo1","onboarding"),"Review InProgress");
            containsValidation(getText(OnBoardingPage.objReviewInProgressInfo2),propertyFileReader("ReviewInProgressInfo2","onboarding"),"Review InProgress");
            verifyElementPresentAndClick(OnBoardingPage.objContactTonikCustomerCare, getTextVal(OnBoardingPage.objContactTonikCustomerCare, ": button"));
            contactUsPageUI();
            verifyElementPresentAndClick(OnBoardingPage.objBackBtn, "Back btn");
            verifyElementPresent(OnBoardingPage.objVerifyMobileNumberPage,getTextVal(OnBoardingPage.objVerifyMobileNumberPage,": page"));
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
        DriverManager.getAppiumDriver().resetApp();
        navigateToCreatePage();
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField,5,"Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("NewSKYCUser", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        acceptTermsAndConditionAndPrivacyPolicy();
        selectIDAndScan(OnBoardingPage.objPhilID);
        buttonNeedToTapValidation();
        inputPlaceOfBirthAndContinue();
        applyZipCode(propertyFileReader("ValidZipCode","onboarding"));
        selectBarangay();
        verifyElementPresentAndClick(OnBoardingPage.objNextBtn,getTextVal(OnBoardingPage.objNextBtn,": button"));
        updateWhatDoYouDo("Salary","Employed","Accountant/Auditor","Accounting/Bookkeeping");
        selectUSCitizenOrNotAndNext();
        updateEmailMotherNameAndBankingRelationship();
        updateSignatureAndNext();
        waitForElementToBePresent(OnBoardingPage.objHeresTheDeal,5,"Here's the deal");
        verifyElementPresent(OnBoardingPage.objHeresTheDeal,getTextVal(OnBoardingPage.objHeresTheDeal,": page"));
        verifyElementPresentAndClick(OnBoardingPage.objDeal,getTextVal(OnBoardingPage.objDeal,": button"));
        createPasswordAndContinue();
        dashboardWelcomeScreenValidation();
        verifyElementPresentAndClick(OnBoardingPage.objSave, getTextVal(OnBoardingPage.objSave, ": tile"));
        if(verifyElementDisplayed(OnBoardingPage.objIWillDoItBtn)){
            click(OnBoardingPage.objIWillDoItBtn,getTextVal(OnBoardingPage.objIWillDoItBtn,": button"));
        }
        tonikDashBordWhenNOTSAValidation();
        logger.info("BD_OB_065, Onboarding - User can onboarded as SKYC successfully validated");
        extentLoggerPass("BD_OB_065", "BD_OB_065, Onboarding - User can onboarded as SKYC successfully validated");
        click(OnBoardingPage.objLoansTile,getTextVal(OnBoardingPage.objLoansTile,": tile"));
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
        DriverManager.getAppiumDriver().resetApp();
        navigateToCreatePage();
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField,5,"Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("USACitizen", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        acceptTermsAndConditionAndPrivacyPolicy();
        selectIDAndScan(OnBoardingPage.objPhilID);
        buttonNeedToTapValidation();
        inputPlaceOfBirthAndContinue();
        applyZipCode(propertyFileReader("ValidZipCode","onboarding"));
        selectBarangay();
        verifyElementPresentAndClick(OnBoardingPage.objNextBtn,getTextVal(OnBoardingPage.objNextBtn,": button"));
        updateWhatDoYouDo("Salary","Employed","Accountant/Auditor","Accounting/Bookkeeping");
        verifyElementPresent(OnBoardingPage.objFATCAPageTitle, getTextVal(OnBoardingPage.objFATCAPageTitle, ": title"));
        assertionValidation(getText(OnBoardingPage.objPageTitle), propertyFileReader("FATCAPageTitle", "onboarding"), ": Title");
        verifyElementPresentAndClick(OnBoardingPage.objAUSACitizen, getTextVal(OnBoardingPage.objAUSACitizen, ": option"));
        verifyElementPresentAndClick(OnBoardingPage.objNextBtn,getTextVal(OnBoardingPage.objNextBtn,": button"));
        updateEmailMotherNameAndBankingRelationship();
        updateSignatureAndNext();
        waitForElementToBePresent(OnBoardingPage.objHeresTheDeal,5,"Here's the deal");
        verifyElementPresent(OnBoardingPage.objHeresTheDeal,getTextVal(OnBoardingPage.objHeresTheDeal,": page"));
        verifyElementPresentAndClick(OnBoardingPage.objDeal,getTextVal(OnBoardingPage.objDeal,": button"));
        if(verifyElementPresent(OnBoardingPage.objBlockedUSCitizenPage,getTextVal(OnBoardingPage.objBlockedUSCitizenPage,": page"))){
            containsValidation(getText(OnBoardingPage.objBlockedUSCitizenInfo),propertyFileReader("BlockedUSCitizenInfo","onboarding"),": info");
            verifyElementPresent(OnBoardingPage.objCloseBtn,getTextVal(OnBoardingPage.objCloseBtn,": button"));
            verifyElementPresent(OnBoardingPage.objContactTonikCustomerCare,getTextVal(OnBoardingPage.objContactTonikCustomerCare,": button"));
            logger.info("BD_OB_040, Onboarding - User can go through after selecting USA Citizen FATCA and user got blocked");
            extentLoggerPass("BD_OB_040", "BD_OB_040, Onboarding - User can go through after selecting USA Citizen FATCA and user got blocked");
        }
    }

    public void onboardingBKYCUserValidation_TDB_OB_060() throws Exception {
        HeaderChildNode("TDB_OB_060, Onboarding - Verify if user can onboard as BKYC");
        handlePopup();
        navigateToCreatePage();
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField,5,"Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("NewSKYCUser", "onboarding"), "Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        acceptTermsAndConditionAndPrivacyPolicy();
        waitForElementToBePresent(OnBoardingPage.objIDSelectionPageTile, 10, "ID Selection screen");
        verifyElementPresent(OnBoardingPage.objPageTitle,getTextVal(OnBoardingPage.objPageTitle," : Page Title"));
        containsValidation(getText(OnBoardingPage.objPageTitle),propertyFileReader("IDSelectionPageTile","onboarding"),": Title");
        verifyElementPresentAndClick(OnBoardingPage.objSkipThisStep,getTextVal(OnBoardingPage.objSkipThisStep,"Button"));
        buttonNeedToTapValidation();
        waitForElementToBePresent(OnBoardingPage.objAutoDetailsPopulatedPageTitle,5,"Let's get to know screen");
        verifyElementPresent(OnBoardingPage.objAutoDetailsPopulatedPageTitle, getTextVal(OnBoardingPage.objAutoDetailsPopulatedPageTitle, ": title"));
        containsValidation(getText(OnBoardingPage.objAutoDetailsPopulatedPageTitle), propertyFileReader("AutoDetailsPopulatedPageTitle", "onboarding"), ": title");
        type(OnBoardingPage.objFirstName, propertyFileReader("FirstName", "onboarding"), "First name input field");
        type(OnBoardingPage.objMiddleName, propertyFileReader("MiddleName", "onboarding"), "Middle name input field");
        type(OnBoardingPage.objLastName, propertyFileReader("LastName", "onboarding"), "Last name input field");
        verifyElementPresentAndClick(OnBoardingPage.objGenderInputField,"Gender input field");
        verifyElementPresentAndClick(OnBoardingPage.objGenderDropdown("Male"),getTextVal(OnBoardingPage.objGenderDropdown("Male"),": Gender dropdown element"));
        click(OnBoardingPage.objDateOfBirth,getTextVal(OnBoardingPage.objDateOfBirth,": Auto populated Date of  birth"));
        click(OnBoardingPage.objPreviousMonth,"Previous month");
        click(OnBoardingPage.objSelectDate("1"),getTextVal(OnBoardingPage.objSelectDate("1"),": Date"));
        verifyElementPresentAndClick(OnBoardingPage.objCalendarOkBtn,getTextVal(OnBoardingPage.objCalendarOkBtn,": Button"));
        type(OnBoardingPage.objPlaceOfBirth, propertyFileReader("ValidPlaceOfBirth", "onboarding"), "Place of birth input field");
        verifyElementPresentAndClick(OnBoardingPage.objContinueBtn, getTextVal(OnBoardingPage.objContinueBtn, ": button"));
        applyZipCode(propertyFileReader("ValidZipCode","onboarding"));
        selectBarangay();
        scrollToVertical("House");
        type(OnBoardingPage.objHouseUnitFlrNumberInputField,propertyFileReader("NewHouseOrFloorNumber","onboarding"),"House, Unit or Flr name");
        verifyElementPresentAndClick(OnBoardingPage.objNextBtn,getTextVal(OnBoardingPage.objNextBtn,": button"));
        updateWhatDoYouDo("Salary","Employed","Accountant/Auditor","Accounting/Bookkeeping");
        selectUSCitizenOrNotAndNext();
        updateEmailMotherNameAndBankingRelationship();
        updateSignatureAndNext();
        waitForElementToBePresent(OnBoardingPage.objHeresTheDeal,5,"Here's the deal");
        verifyElementPresent(OnBoardingPage.objHeresTheDeal,getTextVal(OnBoardingPage.objHeresTheDeal,": page"));
        verifyElementPresentAndClick(OnBoardingPage.objDeal,getTextVal(OnBoardingPage.objDeal,": button"));
        createPasswordAndContinue();
        dashboardWelcomeScreenValidation();
        verifyElementPresentAndClick(OnBoardingPage.objSave, getTextVal(OnBoardingPage.objSave, ": tile"));
        if(verifyElementDisplayed(OnBoardingPage.objIWillDoItBtn)){
            click(OnBoardingPage.objIWillDoItBtn,getTextVal(OnBoardingPage.objIWillDoItBtn,": button"));
        }
        if(verifyElementDisplayed(OnBoardingPage.objCrossIcon)){
            click(OnBoardingPage.objCrossIcon,"Cross button");
        }
        tonikDashBordWhenNOTSAValidation();
        logger.info("TDB_OB_060, Onboarding - User can onboarded as BKYC successfully validated");
        extentLoggerPass("TDB_OB_060", "BD_OB_065, Onboarding - User can onboarded as BKYC successfully validated");
    }
}