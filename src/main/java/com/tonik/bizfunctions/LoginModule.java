package com.tonik.bizfunctions;

import com.tonik.pageObject.LoginPage;
import com.tonik.pageObject.ProfilePage;
import com.tonik.utility.ExtentReporter;
import com.tonik.utility.Utilities;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSElement;

import static com.tonik.utility.ExtentReporter.HeaderChildNode;
import static com.tonik.utility.Utilities.*;

public class LoginModule extends BaseClass {
    public LoginModule() {
        super();
    }
    String platform = Utilities.getPlatform();
    /**
     * Method to verify welcome back Screen UI validation
     * @throws Exception
     */
    public void welcomeBackScreenUIValidation() throws Exception{
       if(verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objTonikLogo),"Tonik Logo")) {
           verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objCustomerCareIcon), "Customer Care Icon");
           verifyElementPresent((LoginPage.getByOSType(platform,LoginPage.objWelcomeBack)), "Welcome Back");
           verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objLoginWithYourPassword), "Login With your Password");
           verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objPasswordFieldWithMaskedEyeIcon), "Password Field With Masked Icon");
           verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objForgotPassword), "forgot Password");
       }
    }
    /**
     * Method to verify User can login when TSA is not Yet created
     * @throws Exception
     */
    public void verifyUserCanLoginWhenTSAisNotYetCreated_TDB_LG_001() throws Exception{
        HeaderChildNode("TDB_LG_001, Login - verify User can Login When TSA is Not Yet Created_TBD_LG_001");
        welcomeBackScreenUIValidation();
        click(LoginPage.getByOSType(platform,LoginPage.objEditPassword), "Password field");
        type(LoginPage.getByOSType(platform,LoginPage.objEditPassword), propertyFileReader("password", "Login"), "Enter secret code");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objLoginBtn), getTextVal(LoginPage.getByOSType(platform,LoginPage.objLoginBtn), "Button"));
        waitForElementToBePresent(ProfilePage.getByOSType(platform,ProfilePage.objProfileIcon), 10, "Profile page Icon");
        if (verifyElementPresent(ProfilePage.getByOSType(platform,ProfilePage.objProfileIcon), "Profile page Icon")) ;
        {
            logger.info("TDB_LG_001, Login - verify User can Login When TSA is Not Yet Created_TBD_LG_001 is passed");
            extentLoggerPass("TDB_LG_001", "TBD_LG_001, Login - verify User can Login When TSA is Not Yet Created_TBD_LG_001 is passed");
        }
    }
    /**
     * Method to verify User can Log in when TSA is created
     * @throws Exception
     */
    public void verifyUserCanLoginWhenTSAisCreated_TDB_LG_002() throws Exception{
        HeaderChildNode("TDB_LG_002, Login - Verify if user can Login when TSA is created");
        welcomeBackScreenUIValidation();
        tonikLogin(propertyFileReader("password","Login"));
        if(verifyElementDisplayed(LoginPage.getByOSType(platform,LoginPage.objHeadsUpLuvPopup))){
            click(LoginPage.getByOSType(platform,LoginPage.objGotItButton),"Got It Button");
        }
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform,ProfilePage.objProfileIcon),"Profile page Icon");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform,ProfilePage.objAccountInformation),"Account information");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon),"Back Icon");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon),"Back Icon");
        if (platform.equalsIgnoreCase("Android")) {
            AndroidElement historyButton = (AndroidElement) findElement(LoginPage.getByOSType(platform,LoginPage.objHistoryBtn));
            if (historyButton.isEnabled()) {
                logger.info("User is redirected to the Main Dashboard with TSA Created");
                ExtentReporter.extentLoggerPass("TSA Created", "User is redirected to the Main Dashboard with TSA Created");
            } else {
                logger.info("User is redirected to the Main Dashboard with No TSA Created");
                ExtentReporter.extentLoggerFail("No TSA Created", "User is redirected to the Main Dashboard with No TSA Created");
            }
        } else {
            IOSElement historyButton = (IOSElement) findElement(LoginPage.getByOSType(platform,LoginPage.objHistoryBtn));
            if (historyButton.isEnabled()) {
                logger.info("User is redirected to the Main Dashboard with TSA Created");
                ExtentReporter.extentLoggerPass("TSA Created", "User is redirected to the Main Dashboard with TSA Created");
            } else {
                logger.info("User is redirected to the Main Dashboard with No TSA Created");
                ExtentReporter.extentLoggerFail("No TSA Created", "User is redirected to the Main Dashboard with No TSA Created");
            }
        }
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform,ProfilePage.objProfileIcon),"Profile page Icon");
        swipe("UP",2);
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objLogOutButton),"Logout Button");
        if(platform.equalsIgnoreCase("Android")) {
            relaunchApp("android");
        }else{
            relaunchApp("ios");
        }
        tonikLogin(propertyFileReader("password","Login"));
        if(verifyElementDisplayed(LoginPage.getByOSType(platform,LoginPage.objHeadsUpLuvPopup))){
            click(LoginPage.getByOSType(platform,LoginPage.objGotItButton),"Got It Button");
        }
        if(verifyElementPresent(ProfilePage.getByOSType(platform,ProfilePage.objProfileIcon),"Profile page Icon"));{
            logger.info("TDB_LG_002, Login - verify User can Login When TSA is Created is passed");
            extentLoggerPass("TDB_LG_002", "TDB_LG_002, Login - verify User can Login When TSA is Created is passed");
        }
    }
    /**
     * Method to verify user can kill the App and Relaunch the app
     * @throws Exception
     */
    public void verifyIfUserCanKillTheAppAndRelaunchTheApp_TDB_LG_008() throws Exception{
        HeaderChildNode("TDB_LG_008, Login - verify if User Can Kill The App And Relaunch The App_TBD_LG_008");
        welcomeBackScreenUIValidation();
        tonikLogin(propertyFileReader("password","Login"));
        if(verifyElementDisplayed(LoginPage.getByOSType(platform,LoginPage.objHeadsUpLuvPopup))){
            click(LoginPage.getByOSType(platform,LoginPage.objGotItButton),"Got It Button");
        }
        if(platform.equalsIgnoreCase("Android")) {
            closeAndroidTonikApp();
            relaunchApp("android");
        }else{
            relaunchApp("ios");
        }
        tonikLogin(propertyFileReader("password","Login"));
        if(verifyElementDisplayed(LoginPage.getByOSType(platform,LoginPage.objHeadsUpLuvPopup))){
            click(LoginPage.getByOSType(platform,LoginPage.objGotItButton),"Got It Button");
        }
        if(verifyElementPresent(ProfilePage.getByOSType(platform,ProfilePage.objProfileIcon),"Profile page Icon")){
            logger.info("TDB_LG_008, Login - verify if User Can Kill The App And Relaunch The App_TBD_LG_008 is passed");
            extentLoggerPass("TDB_LG_008", "TDB_LG_008, Login - verify if User Can Kill The App And Relaunch The App_TBD_LG_008 is passed");
        }
    }

    /**
     * Method to verify user can log out from the profile screen and login again
     * @throws Exception
     */
    public void verifyIfUserCanLogoutFromProfileScreenAndLoginAgain_TDB_LG_009() throws Exception {
        HeaderChildNode("TDB_LG_009, Login - Verify if user can Login when TSA is created");
        welcomeBackScreenUIValidation();
        tonikLogin(propertyFileReader("password", "Login"));
        if (verifyElementDisplayed(LoginPage.getByOSType(platform,LoginPage.objHeadsUpLuvPopup))) {
            click(LoginPage.getByOSType(platform,LoginPage.objGotItButton), "Got It Button");
        }
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform,ProfilePage.objProfileIcon), "Profile page Icon");
        if(platform.equalsIgnoreCase("Android")) {
            verticalSwipeTillElementDispalyed(LoginPage.getByOSType(platform,LoginPage.objLogOutButton));
        }else{
           swipe("UP",3);
        }
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objLogOutButton),"Logout Button");
        if(platform.equalsIgnoreCase("Android")) {
            relaunchApp("android");
        }else{
            relaunchApp("ios");
        }
        tonikLogin(propertyFileReader("password","Login"));
        if(verifyElementDisplayed(LoginPage.getByOSType(platform,LoginPage.objHeadsUpLuvPopup))){
            click(LoginPage.getByOSType(platform,LoginPage.objGotItButton),"Got It Button");
        }
        if(verifyElementPresent(ProfilePage.getByOSType(platform,ProfilePage.objProfileIcon),"Profile page Icon"));{
            logger.info("TDB_LG_009, Login - Verify if user can logout from Profile screen and login again_TBD_LG_009 is passed");
            extentLoggerPass("TDB_LG_009", "TDB_LG_009, Login - Verify if user can logout from Profile screen and login again_TBD_LG_009 is passed");
        }
    }
    /**
     * Method to verify user can attempt maximum invalid password in login
     * @throws Exception
     */
    public void verifyIfUserCanAttemptMaximumInvalidPasswordsInLogin_TDB_LG_006() throws Exception {
        HeaderChildNode("TDB_LG_006, Login - Verify if user can attempt maximum invalid passwords in Login_TBD_LG_006");
        welcomeBackScreenUIValidation();
        for(int invalidPassword=0;invalidPassword<=4;invalidPassword++) {
            click(LoginPage.getByOSType(platform,LoginPage.objEditPassword), "Password field");
            type(LoginPage.getByOSType(platform,LoginPage.objEditPassword), propertyFileReader("Invalid", "Login"), "Enter secret code");
            verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objLoginBtn), getTextVal(LoginPage.getByOSType(platform,LoginPage.objLoginBtn), "Button"));
        }
        verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objErrorContent),"Error content");
        verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objForgotPassword),"Forgot Password");
        verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objContactLink),"Contact link");
        logger.info("TDB_LG_006, Login - Verify if user can attempt maximum invalid passwords in Login_TBD_LG_006 is passed");
        extentLoggerPass("TDB_LG_006", "TDB_LG_006, Login - Verify if user can attempt maximum invalid passwords in Login_TBD_LG_006 is passed");
    }
}
