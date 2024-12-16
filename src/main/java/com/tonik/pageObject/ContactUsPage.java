package com.tonik.pageObject;

import org.openqa.selenium.By;

public enum ContactUsPage {
    objPageHeader("com.tonik.mobile:id/Main_title_txt","com.tonik.mobile:id/Main_title_txt","id","id"),
    objChatWithUs("(//*[@class='android.widget.ScrollView']/child::*/child::android.view.ViewGroup/child::android.widget.TextView)[1]","Chat with us","xpath","id"),
    objContactNumber("(//*[@class='android.widget.ScrollView']/child::*/child::android.view.ViewGroup/child::android.widget.TextView)[2]","(//*[@name='Frequently Asked Questions']/preceding-sibling::*)[2]","xpath","xpath"),
    objFrequentlyAskedQuestion("(//*[@class='android.widget.ScrollView']/child::*/child::android.view.ViewGroup/child::android.widget.TextView)[3]","Frequently Asked Questions","xpath","id"),
    objBackBtn("com.tonik.mobile:id/Header_left_click","com.tonik.mobile:id/Header_left_click","id","id"),
    objContactUsInfo("//*[contains(@text,'Whether')]","//*[contains(@value,'Whether')]","xpath","xpath"),
    objQuestionMarkIcon("(//*[@resource-id='appbar-header']/child::android.view.ViewGroup)[3]","(//*[@name='icon-button'])[2]","xpath","xpath"),
    objChatInputField("//android.widget.EditText","//*[@value ='How may I help you?']","xpath","xpath"),
    objSendButton("//android.widget.EditText/parent::*/following-sibling::*/child::*","//*[@name='Return']","xpath","xpath"),
    objUserMessages("//*[@text='HI']","//*[@name='Hi']","xpath","xpath"),
    objBotReplayMessages("(//*[@text='HI']/parent::*/parent::*/parent::*/following-sibling::android.view.View/child::*/child::*/child::*)[1]","(//*[@name='banner']/following::XCUIElementTypeStaticText)[3]","xpath","xpath"),
    objChatBoxTonikIcon("//*[@text='header-icon']","//*[@name='banner']/child::XCUIElementTypeImage","xpath","xpath"),
    objChatBoxBackButton("//*[@text='minimize']","(//*[@name='banner']/child::*)[2]","xpath","xpath"),
    objDialPadNumber("//*[@class='android.widget.EditText']","//*[@class='android.widget.EditText']","xpath","xpath"),
    objTonikWebPageTitle("com.sec.android.app.sbrowser:id/location_bar_edit_text","//*[@name ='Toggle navigation']","id","xpath"),
    objCustomerCareMailId("//*[contains(@text, 'customercare@tonikbank.com')]","//*[contains(@value,'customercare@tonikbank.com')]","xpath","xpath"),
    objToFieldInMailPage("com.google.android.gm:id/peoplekit_chip","(//*[@name='Welcome to Mail'])[2]","id","xpath"),
    objContactUsButton("com.tonik.mobile:id/contact_button","//*[@name='Login with your password.']/preceding::XCUIElementTypeButton","id","xpath"),
    objGetInTouchInfo("//*[contains(@text,'Whether')]","//*[contains(@value,'Whether')]","xpath","xpath"),
    objBotReplyMessageLink("","//XCUIElementTypeOther[@name='chatBoxFrame']/XCUIElementTypeLink","","xpath"),
    objEmailId("//*[contains(@text,'customercare@tonikbank.com')]","//*[contains(@value,'customercare@tonikbank.com')]","xpath","xpath");

    private String android;
    private String ios;
    private String andPathType;
    private String iosPathType;

    ContactUsPage(String android, String ios, String andPathType, String iosPathType) {
        this.android = android;
        this.ios = ios;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }
    ContactUsPage(String android,  String andPathType, String iosPathType) {
        this.android = android;
        this.ios = android;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }
    ContactUsPage(String android,  String andPathType) {
        this.android = android;
        this.ios = android;
        this.andPathType = andPathType;
        this.iosPathType = andPathType;
    }

    public static By getByOSType(String osType, ContactUsPage objectName) {
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
    public static By objBotReplyMessages(String platform, int value) {
        if (platform.equalsIgnoreCase("android")) {
            return By.xpath("");
        } else {
            return By.xpath("((//XCUIElementTypeOther[@name='chatBoxFrame']/XCUIElementTypeLink)["+value+"]/following-sibling::XCUIElementTypeStaticText)[1]");
        }
    }
}