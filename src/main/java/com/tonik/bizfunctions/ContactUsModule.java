package com.tonik.bizfunctions;

import com.driverInstance.DriverManager;
import com.tonik.pageObject.ContactUsPage;
import com.tonik.pageObject.LoginPage;
import com.tonik.pageObject.OnBoardingPage;
import com.tonik.utility.Utilities;
import io.appium.java_client.MobileElement;
import java.util.List;
import static com.tonik.utility.Utilities.*;

public class ContactUsModule extends BaseClass{
    String platform = Utilities.getPlatform();
    public ContactUsModule() {
        super();
    }
    /**
     * Generalized method to navigate to Contact us page
     * @throws Exception
     */
    public void navigateToContactUs() throws Exception {
        tonikLogin(propertyFileReader("Password","ContactUs"));
        verifyElementPresentAndClick(ContactUsPage.getByOSType(platform,ContactUsPage.objQuestionMarkIcon),"Contact us, Question mark Icon");
        assertionValidation(getText(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader)),propertyFileReader("GetInTouchPageTitle","ContactUs"),"");
        contactUsPageUI();
    }
    /**
     * Generalized method to validate Contact us User interface
     * @throws Exception
     */
    public void contactUsPageUI() throws Exception {
        waitForElementToBePresent(OnBoardingPage.objGetInTouchWithCustomerCarePage,5,"Get in touch page");
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
     * Method to Verify if account holder can view the Get in touch options
     * @throws Exception
     */
    public void contactUsPageUIValidation_TDB_CU_001() throws Exception {
        HeaderChildNode("TDB-CU-001, Contact Us - Verify if account holder can view the Get in touch options");
        navigateToContactUs();
        click(ContactUsPage.getByOSType(platform,ContactUsPage.objBackBtn),getTextVal(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader)," : Page Back button"));
        if(verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objProfileName),getTextVal(LoginPage.getByOSType(platform,LoginPage.objProfileName)," : Profile Name"))) {
            logger.info("TDB-CU-001, Contact Us - Account holder can view the Get in touch options and navigation back to dashboard is validated");
            extentLoggerPass("TDB-CU-001", "TDB-CU-001, Contact Us - Account holder can view the Get in touch options and navigation back to dashboard is validated");
        }
    }
    /**
     * Method to Verify if account holder can select Ask the Tonik chatBot
     * @throws Exception
     */
    public void contactUsUsingChatWithUs_TDB_CU_002() throws Exception {
        HeaderChildNode("TDB-CU-002, Contact Us - Verify if account holder can select Ask the Tonik chatBot");
        navigateToContactUs();
        click(ContactUsPage.getByOSType(platform,ContactUsPage.objChatWithUs),getTextVal(ContactUsPage.getByOSType(platform,ContactUsPage.objChatWithUs),"Contact Option"));
        waitForElementToBePresent(ContactUsPage.getByOSType(platform,ContactUsPage.objChatBoxTonikIcon),10,"Chat Box Tonik Icon");
        verifyElementPresent(ContactUsPage.getByOSType(platform,ContactUsPage.objChatBoxTonikIcon),"Chat Box Tonik Icon");
        type(ContactUsPage.getByOSType(platform,ContactUsPage.objChatInputField),propertyFileReader("BotMessage","ContactUs"),"Chat input field");
        verifyElementPresentAndClick(ContactUsPage.getByOSType(platform,ContactUsPage.objSendButton),"Send Button");
        assertionValidation(getText(ContactUsPage.getByOSType(platform,ContactUsPage.objUserMessages)),propertyFileReader("BotMessage","ContactUs"),": User message");
        if(platform.equalsIgnoreCase("IOS")) {
            List<MobileElement> value = DriverManager.getAppiumDriver().findElements(ContactUsPage.getByOSType(platform, ContactUsPage.objBotReplyMessageLink));
            verifyElementPresent(ContactUsPage.objBotReplyMessages(platform, value.size()), getTextVal(ContactUsPage.objBotReplyMessages(platform, value.size()), "bot reply message"));
            logger.info("bot replay message is displayed");
            extentLoggerPass("bot Replay", "bot replay message is displayed");
        }else{
            waitForElementToBePresent(ContactUsPage.getByOSType(platform, ContactUsPage.objBotReplayMessages), 5, "Bot reply message");
            verifyElementPresent(ContactUsPage.getByOSType(platform, ContactUsPage.objBotReplayMessages), getTextVal(ContactUsPage.getByOSType(platform, ContactUsPage.objBotReplayMessages), " : bot reply message"));
        }
        verifyElementPresentAndClick(ContactUsPage.getByOSType(platform,ContactUsPage.objChatBoxBackButton),"ChatBot Box back button");
        if(verifyElementPresent(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader),getTextVal(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader)," ; Page Header"))){
            assertionValidation(getText(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader)),propertyFileReader("GetInTouchPageTitle","ContactUs"),"Page title");
            logger.info("TDB-CU-002, Contact Us - Account holder can select Ask the Tonik chatBot, chat, replay and navigation back to Get in touch page is validated");
            extentLoggerPass("TDB-CU-002", "TDB-CU-002, Contact Us - Account holder can select Ask the Tonik chatBot, chat, replay and navigation back to Get in touch page is validated");
        }
    }
    /**
     * Method to Verify if account holder can select the +63 253222645
     * @throws Exception
     */
    public void contactUsUsingContactNumber_TDB_CU_003() throws Exception {
        HeaderChildNode("TDB-CU-003, Contact Us - Verify if account holder can select the +63 253222645");
        navigateToContactUs();
        String contactNumberDisplayed = getText(ContactUsPage.getByOSType(platform,ContactUsPage.objContactNumber));
        String contactNumber = contactNumberDisplayed.replaceAll(" ","").trim();
        click(ContactUsPage.getByOSType(platform,ContactUsPage.objContactNumber),getTextVal(ContactUsPage.getByOSType(platform,ContactUsPage.objContactNumber),"Contact Number"));
        if(platform.equalsIgnoreCase("Android")) {
            if (verifyElementPresent(ContactUsPage.getByOSType(platform, ContactUsPage.objDialPadNumber), getTextVal(ContactUsPage.getByOSType(platform, ContactUsPage.objDialPadNumber), "Dial Pad Number"))) {
                assertionValidation(getText(ContactUsPage.getByOSType(platform, ContactUsPage.objDialPadNumber)), contactNumber, "Dial pad number");
                logger.info("TDB-CU-003, Contact Us - Account holder can select the +63 253222645 and App navigates to dialPad is validated");
                extentLoggerPass("TDB-CU-003", "TDB-CU-003, Contact Us - Account holder can select the +63 253222645 and App navigates to dialPad is validated");
            }
        }else{
            screencapture();
            logger.info("TDB-CU-003, Contact Us - Account holder can select the +63 253222645 and App navigates to dialPad is validated");
            extentLoggerPass("TDB-CU-003", "TDB-CU-003, Contact Us - Account holder can select the +63 253222645 and App navigates to dialPad is validated");
        }
    }
    /**
     * Method to Verify if the account holder can select Frequently Asked Questions
     * @throws Exception
     */
    public void contactUsFrequentlyAskedQuestionsOptionValidation_TDB_CU_011() throws Exception {
        HeaderChildNode("TDB-CU-011, Contact Us - Verify if the account holder can select Frequently Asked Questions");
        navigateToContactUs();
        click(ContactUsPage.getByOSType(platform,ContactUsPage.objFrequentlyAskedQuestion),getTextVal(ContactUsPage.getByOSType(platform,ContactUsPage.objFrequentlyAskedQuestion),"Frequently Asked question"));
        if(verifyElementPresent(ContactUsPage.getByOSType(platform,ContactUsPage.objTonikWebPageTitle),getTextVal(ContactUsPage.getByOSType(platform,ContactUsPage.objTonikWebPageTitle)," : Page Title"))){
            logger.info("TDB-CU-011, Contact Us - Account holder can select Frequently Asked Questions option and App navigates to Tonik Web page is validated");
            extentLoggerPass("TDB-CU-011", "TDB-CU-011, Contact Us - Account holder can select Frequently Asked Questions option and App navigates to Tonik Web page is validated");
        }
    }
    /**
     * Method to Verify if the user can access the customer care email address
     * @throws Exception
     */
    public void contactUsUserCanAccessCustomerEmailIdValidation_TDB_CU_012() throws Exception {
        HeaderChildNode("TDB-CU-012, Contact Us - Verify if the user can access the customer care email address");
        navigateToContactUs();
        click(ContactUsPage.getByOSType(platform,ContactUsPage.objCustomerCareMailId),getTextVal(ContactUsPage.getByOSType(platform,ContactUsPage.objCustomerCareMailId)," : Customer care mail id"));
        verifyElementPresent(ContactUsPage.getByOSType(platform,ContactUsPage.objToFieldInMailPage),getTextVal(ContactUsPage.getByOSType(platform,ContactUsPage.objToFieldInMailPage)," : To field in mail page"));
        if(platform.equalsIgnoreCase("Android")) {
            assertionValidation(getText(ContactUsPage.getByOSType(platform, ContactUsPage.objToFieldInMailPage)), propertyFileReader("MailId", "ContactUs"), "MailId");
            DriverManager.getAppiumDriver().navigate().back();
            DriverManager.getAppiumDriver().navigate().back();
        }else{
            DriverManager.getAppiumDriver().activateApp("com.mobile.tonik");
        }
        if(verifyElementPresent(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader),getTextVal(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader)," : Header"))){
            assertionValidation(getText(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader)),propertyFileReader("GetInTouchPageTitle","ContactUs"),"Page title");
            logger.info("TDB-CU-012, Contact Us - User can access the customer care email address is validated");
            extentLoggerPass("TDB-CU-012", "TDB-CU-012, Contact Us - User can access the customer care email address is validated");
        }
    }
    /**
     * Method to Verify if user can access the Contact Us from Login screen
     * @throws Exception
     */
    public void contactUsPageNavigationFromLogInPageValidation_TDB_CU_013() throws Exception {
        HeaderChildNode("TDB-CU-013, Contact Us - Verify if user can access the Contact Us from Login screen");
        waitForElementToBePresent(LoginPage.getByOSType(platform,LoginPage.objEditPassword), 10, "Password edit field");
        verifyElementPresentAndClick(ContactUsPage.getByOSType(platform,ContactUsPage.objContactUsButton), "Contact us Button");
        if (verifyElementPresent(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader), getTextVal(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader), " : Header"))) {
            assertionValidation(getText(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader)),propertyFileReader("GetInTouchPageTitle","ContactUs"),"Page title");
            contactUsPageUI();
            logger.info("TDB-CU-013, Contact Us - User can access the Contact Us from Login screen is validated");
            extentLoggerPass("TDB-CU-013", "TDB-CU-013, Contact Us - User can access the Contact Us from Login screen is validated");
        }
    }
}