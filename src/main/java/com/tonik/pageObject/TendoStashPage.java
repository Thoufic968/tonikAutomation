package com.tonik.pageObject;

import org.openqa.selenium.By;
public enum TendoStashPage {
    objTendoStashTile("//*[@text='Tendo stash']","//XCUIElementTypeStaticText[@label='Tendo stash']","xpath","xpath"),
    objTendoStashAmount("//android.widget.TextView[@text='Tendo stash']/following-sibling::android.widget.TextView[contains(@text,'₱')]","//XCUIElementTypeStaticText[@label='Tendo stash']/parent::*/following-sibling::XCUIElementTypeStaticText","xpath","xpath"),
    objManageTendoStash("(//*[contains(@text,'Tendo')]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/child::android.widget.TextView)[2]","(//XCUIElementTypeOther[@label='Tendo stash']/parent::*/following-sibling::XCUIElementTypeOther/child::*/child::XCUIElementTypeStaticText)[2]","xpath","xpath"),
    ObjTendoStashAddToStash("(//*[contains(@text,'Tendo')]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/child::android.widget.TextView)[1]","(//XCUIElementTypeOther[@label='Tendo stash']/parent::*/following-sibling::XCUIElementTypeOther/child::*/child::XCUIElementTypeStaticText)[1]","xpath","xpath"),
    objCameraIcon("com.tonik.mobile:id/Stash_Camera_Img", "com.tonik.mobile:id/Stash_Camera_Img", "id", "id"),
    objTendoStashTileInfo("//*[@text='Tendo stash']/following-sibling::*","//XCUIElementTypeStaticText[@label='Tendo stash']/following-sibling::*","xpath","xpath");
    private String android;
    private String ios;
    private String andPathType;
    private String iosPathType;
    TendoStashPage(String android, String ios, String andPathType, String iosPathType) {
        this.android = android;
        this.ios = ios;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }
    TendoStashPage(String android,  String andPathType, String iosPathType) {
        this.android = android;
        this.ios = android;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }
    TendoStashPage(String android,  String andPathType) {
        this.android = android;
        this.ios = android;
        this.andPathType = andPathType;
        this.iosPathType = andPathType;
    }
    public static By getByOSType(String osType, TendoStashPage objectName) {
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
}