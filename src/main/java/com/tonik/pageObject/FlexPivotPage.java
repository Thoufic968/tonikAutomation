package com.tonik.pageObject;

import org.openqa.selenium.By;

public enum FlexPivotPage {

    objPopHeader("com.tonik.mobile:id/Popup_title_text","com.tonik.mobile:id/Popup_title_text","id","name"),
    objPopupDescription("com.tonik.mobile:id/Popup_Description","com.tonik.mobile:id/Popup_Description","id","name"),
    objApplyNowBtn("//*[@resource-id='Apply now']","//XCUIElementTypeStaticText[@name='Apply now']","xpath","xpath"),
    objYouHave1Match("//*[@text='You have (1) match']","//XCUIElementTypeStaticText[@name='You have (1) match']","xpath","xpath"),
    objLoanOfferDescription("//*[@text=\"We've got a loan offer made just for you!\"]","//XCUIElementTypeStaticText[@name=\"We've got a loan offer made just for you!\"]","xpath","xpath"),
    objReadyToTakeItFurther("//*[contains(@text,'Ready to take it')]","//*[contains(@label,'Ready to take it')]","xpath","xpath"),
    objReadyToTakeItFurtherDescription("//*[contains(@text,'We like you so much')]","//*[contains(@label,'We like you so much')]","xpath","xpath"),
    objApplyAnotherLoanBtn("I want to apply for another loan","//XCUIElementTypeStaticText[@label='I want to apply for another loan']","id","xpath"),
    objContinueLoanApplicationBtn("//*[@text='Continue to loan application']","//XCUIElementTypeStaticText[@label='Continue to loan application']","xpath","xpath"),
    objJustForYouTileHeader ("com.tonik.mobile:id/Txt_header3", "//*[@name='com.tonik.mobile:id/Txt_header3']", "id", "xpath"),
    objExclusiveLoanOffer ("//*[@resource-id='com.tonik.mobile:id/Exclusive Loan Offer']", "//*[@name='com.tonik.mobile:id/Exclusive Loan Offer']", "xpath", "xpath"),
    objExclusiveLoanOfferSubTitle ("//*[@resource-id='com.tonik.mobile:id/Txt_sub_title3']", "//*[@name='com.tonik.mobile:id/Txt_sub_title3']", "xpath", "xpath"),
    objNowWithPayHinga ("//*[contains(@resource-id,'com.tonik.mobile:id/Txt_header_view3')]/parent::*/following-sibling::*/child::*", "(//XCUIElementTypeStaticText[@name='Now with PayHinga!'])[1]", "xpath", "xpath"),
    objMaxLoanableAmount("//*[@resource-id=\"com.tonik.mobile:id/Max_amount_view\"]/child::*","//XCUIElementTypeStaticText[contains(@label,'Max amount')]","xpath","xpath"),
    objSlider("//android.view.ViewGroup[@resource-id=\"com.tonik.mobile:id/Slider_tracker\"]","//XCUIElementTypeOther[@name=\"com.tonik.mobile:id/Slider_tracker\"]","xpath","xpath"),
    objLoanTileAfterApproved ("//*[contains(@text,'officially a thing')]", "//XCUIElementTypeStaticText[contains(@value,'officially a thing')]", "xpath", "xpath"),
    objBackBtn("com.tonik.mobile:id/Left_click","com.tonik.mobile:id/Left_click","id","name");
    private String android;
    private String ios;
    private String andPathType;
    private String iosPathType;
    FlexPivotPage(String android, String ios, String andPathType, String iosPathType) {
        this.android = android;
        this.ios = ios;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }
    FlexPivotPage(String android, String andPathType, String iosPathType) {
        this.android = android;
        this.ios = android;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }
    FlexPivotPage(String android, String andPathType) {
        this.android = android;
        this.ios = android;
        this.andPathType = andPathType;
        this.iosPathType = andPathType;
    }
    public static By getByOSType(String osType, FlexPivotPage objectName) {
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
    public static void main(String[] args) {
        System.out.println(TopUpPage.getByOSType("ios", TopUpPage.objTopUpIcon));
    }
    public static By objPromotionScreenButton(String platform, String button) {
        if(platform.equalsIgnoreCase("android")){
            return By.xpath("//*[@text=\""+button+"\"]");
        } else {
            return By.xpath("//XCUIElementTypeStaticText[@label=\""+button+"\"]");
        }
    }
    public static By objPromotionScreenHeader(String platform, int screenIndex) {
        if(platform.equalsIgnoreCase("android")){
            return By.id("com.tonik.mobile:id/Txt_header"+screenIndex);
        } else {
            return By.name("com.tonik.mobile:id/Txt_header"+screenIndex);
        }
    }
    public static By objPromotionScreenSubHeader(String platform, int screenIndex) {
        if(platform.equalsIgnoreCase("android")){
            return By.id("com.tonik.mobile:id/Txt_sub_header"+screenIndex);
        } else {
            return By.name("com.tonik.mobile:id/Txt_sub_header"+screenIndex);
        }
    }
    public static By objSelectLoanTenure(String platform, String tenure){
        if(platform.equalsIgnoreCase("android")){
            return By.xpath("//*[@text='"+tenure+"']");
        } else {
            return By.xpath("//XCUIElementTypeStaticText[@label='"+tenure+"']");
        }
    }
    public static By objTopUpOption(String platform, String option) {
        if(platform.equalsIgnoreCase("android")){
            return By.xpath("//*[@text='"+option+"']");
        } else {
            return By.xpath("//*[@value='"+option+"']");
        }
    }
}
