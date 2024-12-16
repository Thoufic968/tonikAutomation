package com.tonik.pageObject;

import org.openqa.selenium.By;

public enum FlexLoanWithoutVasPage {
    objAreYouSure("//*[@text='Are you sure?']","//*[@value='Are you sure?']","xpath","xpath"),
    objNoThanksBtn("//*[@resource-id='com.tonik.mobile:id/Custom_button_txt' and @text='No thanks']","//*[@name='com.tonik.mobile:id/Custom_button_txt' and @value='No thanks']","xpath","xpath"),
    objContactUsBtn ("//*[@resource-id='com.tonik.mobile:id/Right_click']/android.view.ViewGroup/android.view.ViewGroup","//XCUIElementTypeOther[@name='com.tonik.mobile:id/Right_click']","xpath","xpath"),
    objBackButton("//*[@text='New!']/preceding-sibling::android.view.ViewGroup/preceding-sibling::*","//*[contains(@name,'left_click')]","xpath","xpath"),
    objProceedWithAnUnProtectedLoanBtn("//*[@resource-id='com.tonik.mobile:id/Custom_button_txt']","//*[@name='com.tonik.mobile:id/Custom_button_txt']","xpath","xpath"),
    objLetGetPersonalBabePage("//*[contains(@text,'personal, babe')]","//*[contains(@value,'personal, babe')]","xpath","xpath"),
    objProceedWithUnProtectedLoan("//*[@resource-id='com.tonik.mobile:id/Custom_button_txt']","//*[@name='com.tonik.mobile:id/Custom_button_txt']","xpath","xpath"),
    objYouAreMissingOut("//*[contains(@text,'You’re missing out!')]","//*[contains(@value,'You’re missing out!')]","xpath","xpath"),
    objPopUpSubHeader("//*[contains(@text,'Enjoy up to 2 months of payment breaks and loan')]","//*[contains(@value,'Enjoy up to 2 months of payment breaks and loan')]","xpath","xpath"),
    objInstallmentPeriodMonth("//*[@resource-id='com.tonik.mobile:id/Installment period2']","//XCUIElementTypeStaticText[@name='com.tonik.mobile:id/Installment periodval2']","xpath","xpath"),
    objFirstInstallmentTxt("//*[@resource-id='com.tonik.mobile:id/First installment due date3']","//XCUIElementTypeStaticText[@name='com.tonik.mobile:id/First installment due date3']","xpath","xpath"),
    objInstallmentDuedate ("//*[@resource-id='com.tonik.mobile:id/First installment due dateval3']","//XCUIElementTypeStaticText[@name='com.tonik.mobile:id/Due dateval1']","xpath","xpath"),
    objLoansDocuments("//*[@resource-id='com.tonik.mobile:id/Loan Documents_txt']/parent::*/following-sibling::*/child::*/child::*/child::*/child::android.widget.TextView","(//XCUIElementTypeOther[@name=\"Rates and fees\"])[1]/following-sibling::*/following-sibling::*/child::*/child::*/child::*/child::XCUIElementTypeStaticText","xpath","xpath");
    private String android;
    private String ios;
    private String andPathType;
    private String iosPathType;
    FlexLoanWithoutVasPage(String android, String ios, String andPathType, String iosPathType) {
        this.android = android;
        this.ios = ios;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }
    FlexLoanWithoutVasPage(String android, String andPathType, String iosPathType) {
        this.android = android;
        this.ios = android;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }
    FlexLoanWithoutVasPage(String android, String andPathType) {
        this.android = android;
        this.ios = android;
        this.andPathType = andPathType;
        this.iosPathType = andPathType;
    }
    public static By getByOSType(String osType, FlexLoanWithoutVasPage objectName) {
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
        throw new IllegalArgumentException("Object not found: " + objectName);
    }
    public static By objLoansDocuments(String platform, int document) {
        if(platform.equalsIgnoreCase("android")){
            return By.xpath("(//*[@resource-id='com.tonik.mobile:id/Loan Documents_txt']/parent::*/following-sibling::*/child::*/child::*/child::*/child::android.widget.TextView)["+document+"]");
        } else {
            return By.xpath("((//XCUIElementTypeOther[@name=\"Rates and fees\"])[1]/following-sibling::*/following-sibling::*/child::*/child::*/child::*/child::XCUIElementTypeStaticText)["+document+"]");
        }
    }
}