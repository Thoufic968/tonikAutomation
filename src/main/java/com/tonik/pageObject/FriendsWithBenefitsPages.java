package com.tonik.pageObject;

import org.openqa.selenium.By;

public enum FriendsWithBenefitsPages {
    objFriendsWithBenefitsTile("//*[contains(@text,'Friends with benefits')]","Friends with benefits","xpath","id"),
    objProfileIcon("//*[@resource-id='appbar-content-title-text']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup/child::android.widget.Button","(//*[@name='icon-button'])[1]","xpath","xpath"),
    objAccountInformation("//*[contains(@text,' Account information')]","(//*[@name=' Account information'])[4]","xpath","xpath"),
    objBackBtn("com.tonik.mobile:id/Header_left_click","com.tonik.mobile:id/Header_left_click","id","id"),
    objHistoryBtn("//*[contains(@text,'History')]/parent::android.view.ViewGroup/child::android.view.ViewGroup/child::android.widget.Button","(//*[contains(@value,'History')]/preceding-sibling::*)","xpath","xpath"),
    objFriendsTileSubTitle("//*[contains(@text,'successful loan referral!')]","(//*[@name='Friends with benefits']/following-sibling::*)","xpath","xpath"),
    objFriendsWithBenefitScreen("//*[contains(@text,'Friends with')]","Friends with\n" +
            "benefits","xpath","xpath"),
    objSuccessfullyBookText("//*[contains(@text,'for each friend who')]","(//*[contains(@name,'successfully books a Quick Loan ')])[11]","xpath","xpath"),
    objReferralLinkText("//*[contains(@text,'Referral link')]","Referral link","xpath","id"),
    objLink("//*[contains(@text,'Referral link')]/following-sibling::android.widget.TextView","(//*[@name='Referral link']/following-sibling::*)","xpath","xpath"),
    objYourReferralTxt("//*[contains(@text,'Your referral link may not be compatible')]","(//*[contains(@name,'Your referral link may not be compatible')])[11]","xpath","xpath"),
    objHowDoesThisWorkText("//*[contains(@text,'How does this work?')]","(//*[@name='How does this work?'])","xpath","xpath"),
    objShareYourCodeButton("//*[contains(@text,'Share your code')]","(//*[@name='Share your code'])[3]","xpath","xpath"),
    objClipBoardButton("//*[contains(@text,'Referral link')]/parent::android.view.ViewGroup/child::android.view.ViewGroup","(//*[@name='Referral link']/following::XCUIElementTypeOther)[2]","xpath","xpath"),
    objWebView("//*[@resource-id='com.sec.android.app.sbrowser:id/location_bar_edit_text']","(//*[@name='Toggle navigation'])","xpath","xpath"),
    objWeAreAlmostReady ("//*[contains(@text,'We are almost')] "," //*[contains(@name,'We are almost')]", "xpath", "xpath"),
    objClipBoardMsg("//*[@text='Copied to clipboard']","(//*[@name='Copied to clipboard'])[3]","xpath","xpath");
    private String android;
    private String ios;
    private String andPathType;
    private String iosPathType;

    FriendsWithBenefitsPages(String android, String ios, String andPathType, String iosPathType) {
        this.android = android;
        this.ios = ios;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }

    FriendsWithBenefitsPages(String android, String andPathType, String iosPathType) {
        this.android = android;
        this.ios = android;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }

    FriendsWithBenefitsPages(String android, String andPathType) {
        this.android = android;
        this.ios = android;
        this.andPathType = andPathType;
        this.iosPathType = andPathType;
    }

    public static By getByOSType(String osType, FriendsWithBenefitsPages objectName) {
        if (osType.equalsIgnoreCase("android") && objectName.andPathType.equalsIgnoreCase("xpath")) {
            return By.xpath(objectName.android);
        } else if (osType.equalsIgnoreCase("android") && objectName.andPathType.equalsIgnoreCase("id")) {
            return By.id(objectName.android);
        } else if (osType.equalsIgnoreCase("android") && objectName.andPathType.equalsIgnoreCase("name")) {
            return By.name(objectName.android);
        } else if (osType.equalsIgnoreCase("ios") && objectName.iosPathType.equalsIgnoreCase("xpath")) {
            return By.xpath(objectName.ios);
        } else if (osType.equalsIgnoreCase("ios") && objectName.iosPathType.equalsIgnoreCase("id")) {
            return By.id(objectName.ios);
        } else if (osType.equalsIgnoreCase("ios") && objectName.iosPathType.equalsIgnoreCase("name")) {
            return By.name(objectName.ios);
        }
        throw new IllegalArgumentException("Object not found: " + objectName);
    }
}
