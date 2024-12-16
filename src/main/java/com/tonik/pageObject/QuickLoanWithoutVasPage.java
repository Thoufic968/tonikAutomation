package com.tonik.pageObject;

import org.openqa.selenium.By;

public enum QuickLoanWithoutVasPage {
    objNextTxt("//*[contains(@text,'New!')]","//*[contains(@label,'New!')]","xpath","xpath"),
    objAreYouSure("//*[@text='Are you sure?']","//XCUIElementTypeStaticText[@name='Are you sure?']","xpath","xpath"),
    objYouAreMissingOut("//*[@text='You’re missing out!']","//XCUIElementTypeStaticText[@name=\"You’re missing out!\"]","xpath","xpath"),
    objClickHere("//*[contains(@text,'Click here')]","//XCUIElementTypeStaticText[contains(@label,'Click here')]","xpath","xpath"),
    objNoThanks("//*[contains(@text,'No thanks')]","//XCUIElementTypeStaticText[contains(@label,'No thanks')]","xpath","xpath"),
    objCountMeIn("//*[contains(@text,'Count me in')]","//XCUIElementTypeStaticText[contains(@label,'Count me in')]","xpath","xpath"),
    objDropTheMic("//*[@text='Drop the mic!']","//*[contains(@value,'Drop the mic!')]","xpath","xpath"),
    objDropTheMicInfo("//*[contains(@text,'Your loan is finally fully paid, luv.')]","//*[contains(@value,'Your loan is finally fully paid, luv.')]","xpath","xpath"),
    objLoanPaidInFullTransaction("//*[contains(@text,'Loan paid in Full')]","//*[contains(@value,'Loan paid in Full')]","xpath","xpath"),
    objLoanPaymentReceivedScreen("//*[@text='Loan payment received']","//*[@value='Loan payment received']","xpath","xpath"),
    objLoanPaymentReceivedInfo("//*[contains(@text,'Tonik account was reserved as your loan payment.')]","//*[contains(@label,'Tonik account was reserved as your loan payment.')]","xpath","xpath");
    private String android;
    private String ios;
    private String andPathType;
    private String iosPathType;

    QuickLoanWithoutVasPage(String android, String ios, String andPathType, String iosPathType) {
        this.android = android;
        this.ios = ios;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }
    QuickLoanWithoutVasPage(String android,  String andPathType, String iosPathType) {
        this.android = android;
        this.ios = android;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }
    QuickLoanWithoutVasPage(String android,  String andPathType) {
        this.android = android;
        this.ios = android;
        this.andPathType = andPathType;
        this.iosPathType = andPathType;
    }
    public static By getByOSType(String osType, QuickLoanWithoutVasPage objectName) {
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