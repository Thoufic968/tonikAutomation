package com.tonik.pageObject;

import org.openqa.selenium.By;

public enum LoginPage {

    //Login page
    objEditPassword("//android.widget.EditText[@resource-id='com.tonik.mobile:id/pin']","(//XCUIElementTypeSecureTextField)","xpath","xpath"),
    objLoginBtn("//android.widget.Button[@resource-id='com.tonik.mobile:id/loginbutton']", "//*[@name='Login']","xpath","xpath"),
    //  Profile Nam
    objProfileName("//*[@resource-id='appbar-content-title-text']", "//*[@name='appbar-content-title-text']","xpath","xpath"),
    objCallsPermissionPopup("//*[contains(@text,'phone calls')]","//*[contains(@value,'phone calls')]","xpath","xpath"),
    objLocationAccessPopup ("//*[contains(@text,'location')]","//*[contains(@value,'location')]","xpath","xpath"),
    objAllowBtn("com.android.permissioncontroller:id/permission_allow_button","com.android.permissioncontroller:id/permission_allow_button","id","id"),
    objWhileUsingApp("com.android.permissioncontroller:id/permission_allow_foreground_only_button","com.android.permissioncontroller:id/permission_allow_foreground_only_button","id","id"),
    objPictureAccessPopup("//*[@text='Allow TONIK to access photos and media on your device?']","//*[@value='Allow TONIK to access photos and media on your device?']","xpath","xpath"),
    objAllowNotificationPopup("//*[contains(@text,'notifications')]","//*[contains(@value,'notifications')]","xpath","xpath"),
    //FOR IOS POPUP
    objContactAccessPopupIos("//XCUIElementTypeStaticText[contains(@name,'Would Like to Access Your Contacts')]","//XCUIElementTypeStaticText[contains(@name,'Would Like to Access Your Contacts')]","xpath","xpath"),
    objLocationAccessPopupIos("//XCUIElementTypeStaticText[@name=\"Allow “TONIK” to use your location?\"]","//XCUIElementTypeStaticText[@name=\"Allow “TONIK” to use your location?\"]","xpath","xpath"),
    objOkBtnIos("OK","OK","id"),
    objWhileUsingAppIos("//XCUIElementTypeButton[@name='Allow While Using App']","//XCUIElementTypeButton[@name='Allow While Using App']","xpath","xpath"),
    objPictureAccessPopupIos("//XCUIElementTypeStaticText[contains(@name,'Would Like to Access the Camera')]","//XCUIElementTypeStaticText[contains(@name,'Would Like to Access the Camera')]","xpath","xpath"),
    objPhotoAccessPopupIos("//XCUIElementTypeStaticText[contains(@name,'Would Like to Access Your Photos')]","//XCUIElementTypeStaticText[contains(@name,'Would Like to Access Your Photos')]","xpath","xpath"),
    objAllowAccessAllPhotosIos("//XCUIElementTypeButton[contains(@name,'Allow Access to All Photos')]","//XCUIElementTypeButton[contains(@name,'Allow Access to All Photos')]","xpath","xpath"),
    objNotificationPopupIos("//XCUIElementTypeStaticText[contains(@name,'Send You Notifications')]","//XCUIElementTypeStaticText[contains(@name,'Send You Notifications')]","xpath","xpath"),
    objAllowBtnIos("Allow","Allow","id"),
    //  Profile Name
    objBackIcon("com.tonik.mobile:id/Header_left_click","com.tonik.mobile:id/Header_left_click","id","id"),
    objNextBtn("//*[@text='Next']","(//*[@name ='Next'])[3]","xpath","xpath"),
    //Login Screen
    objTonikLogo("//*[@resource-id='com.tonik.mobile:id/back_button']","//*[@value='Password']/preceding::XCUIElementTypeImage","xpath","xpath"),
    objCustomerCareIcon("//*[@resource-id='com.tonik.mobile:id/contact_button']","//*[@value='Password']/preceding::XCUIElementTypeButton","xpath","xpath"),
    objWelcomeBack("//*[@resource-id='com.tonik.mobile:id/title']","(//*[@value='Password']/preceding::XCUIElementTypeStaticText)[2]","xpath","xpath"),
    objLoginWithYourPassword("//*[@resource-id='com.tonik.mobile:id/info']","//*[@name='Login with your password.']","xpath","xpath"),
    objForgotPassword("//*[@resource-id='com.tonik.mobile:id/forgotpwdbutton']","//XCUIElementTypeStaticText[@name='Forgot password']","xpath","xpath"),
    objPasswordFieldWithMaskedEyeIcon("//*[@resource-id='com.tonik.mobile:id/pin']","//*[@value='Password']/child::XCUIElementTypeButton","xpath","xpath"),
    //Heads up luv pop up
    objHeadsUpLuvPopup("//*[@text='Heads up, luv!']","//*[@value='Heads up, luv!']","xpath","xpath"),
    objGotItButton("//*[@text='Got it']","//*[@value='Got it']","xpath","xpath"),
    objLogOutButton("//*[contains(@text, 'Log out')]","//*[contains(@value,'Log out')]","xpath","xpath"),
    objErrorContent("//*[@text='You Account Was Temporarily blocked']","//*[@name='Your account was temporarily locked for security reasons. Reset your password to unlock it']","xpath","xpath"),
    objHistoryBtn("//*[contains(@text,'History')]/parent::android.view.ViewGroup/child::android.view.ViewGroup/child::android.widget.Button","(//*[contains(@value,'History')]/preceding-sibling::*)","xpath","xpath"),
    objContactLink("//*[@text='Contact Link']","//*[@name='Contact Tonik']","xpath","xpath");

    private String android;
    private String ios;
    private String andPathType;
    private String iosPathType;

    LoginPage(String android, String ios, String andPathType, String iosPathType) {
        this.android = android;
        this.ios = ios;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }
    LoginPage(String android,  String andPathType, String iosPathType) {
        this.android = android;
        this.ios = android;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }
    LoginPage(String android,  String andPathType) {
        this.android = android;
        this.ios = android;
        this.andPathType = andPathType;
        this.iosPathType = andPathType;
    }

    public static By getByOSType(String osType, LoginPage objectName) {
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

    public static By objOTPInputField (String platform, int number){
        if(platform.equalsIgnoreCase("android")){
            return  By.xpath("(//android.widget.TextView[@text='⋆'])["+number+"]");
        } else {
            return  By.xpath("(//XCUIElementTypeOther[@name='*'])["+number+"]");
        }
    }
}
