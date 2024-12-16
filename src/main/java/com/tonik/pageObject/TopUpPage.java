package com.tonik.pageObject;

import org.openqa.selenium.By;
public enum TopUpPage {
    objTopUpIcon ("//*[@text='Top up']/preceding-sibling::*","//*[@value='Top up']/preceding-sibling::*", "xpath", "xpath"),
    objTopUpOptions ("//android.widget.ScrollView/child::*/child::*/child::*/child::*/child::android.widget.TextView","//XCUIElementTypeOther[@name='PESONet or InstaPay Online']/child::*/child::*/child::*/child::XCUIElementTypeStaticText", "xpath", "xpath"),
    objNameHeader ("//*[@text='Name']","(//*[@value='Name'])", "xpath", "xpath"),
    objAccountHolderNameDisplayed ("//*[@text='Name']/following-sibling::android.widget.TextView","//*[@value='Name']/following-sibling::XCUIElementTypeStaticText", "xpath", "xpath"),
    objAccountHolderNameCopyIcon ("//*[@text='Name']/following-sibling::android.view.ViewGroup/child::* ","//*[@value='Name']/parent::*/following-sibling::*", "xpath", "xpath"),
    objBankHeader ("//*[@text='Bank']", "//*[@value='Bank']", "xpath", "xpath"),
    objBankNameDisplayed ("//*[@text='Bank']/following-sibling::android.widget.TextView", "//*[@value='Bank']/following-sibling::XCUIElementTypeStaticText", "xpath", "xpath"),
    objTonikAccountNumberHeader ("//*[@text='Tonik Account No.']", "//*[@value='Tonik Account No.']", "xpath", "xpath"),
    objTonikAccountNumberDisplayed ("//*[@text='Tonik Account No.']/following-sibling::android.widget.TextView", "//*[@value='Tonik Account No.']/following-sibling::XCUIElementTypeStaticText", "xpath", "xpath"),
    objTonikAccountNumberCopyIcon ("//*[@text='Tonik Account No.']/following-sibling::android.view.ViewGroup/child::*", "//*[@value='Tonik Account No.']/parent::*/following-sibling::*", "xpath", "xpath"),
    objCopyToClipBoardMsg ("//*[@text='Copied to your clipboard']", "//*[@value='Copied to your clipboard']", "xpath", "xpath"),
    objReminders ("//*[@text='Reminders']/parent::*/following-sibling::*/child::*/child::android.widget.TextView", "//*[@name='Reminders']/following-sibling::*/child::*/child::*/child::*/child::XCUIElementTypeStaticText", "xpath", "xpath"),
    objReminderHeader ("//*[@text='Reminders']", "//*[@value='Reminders']", "xpath", "xpath"),
    objOnlineTopUpOptions ("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup//android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView", "//XCUIElementTypeOther[@name=' GCash  BPI  Chinabank  RCBC  Unionbank']/child::*/child::*/child::*/child::*/child::*/child::XCUIElementTypeStaticText", "xpath", "xpath"),
    objGCashOnlineOption ("//*[@text='GCash']", "//*[@value='GCash']", "xpath", "xpath"),
    objOneTimeTopUpOption ("//android.widget.TextView[@text=\"One-time top-up via GCash\"]", "//XCUIElementTypeStaticText[@value='One-time top-up via GCash']","xpath","xpath"),
    objAvailableBalance ("//*[contains(@text, 'Balance')]", "//*[contains(@value, 'Balance')]", "xpath", "xpath"),
    objMaxTransaction ("//*[contains(@text, 'Up to')]", "//*[contains(@value, 'Up to')]", "xpath", "xpath"),
    objTransactionFee ("//*[contains(@text, 'Transaction Fee')]", "//*[contains(@value, 'Transaction Fee')]", "xpath", "xpath"),
    objAmountInputField ("//*[@text='₱']/following-sibling::android.widget.EditText", "//*[@value='₱']/parent::*/following-sibling::*/child::XCUIElementTypeTextField", "xpath", "xpath"),
    objTopUPInfo ("//*[contains(@text, 'Transaction Fee')]/following-sibling::android.widget.TextView", "//*[contains(@value, 'Transaction Fee')]/following-sibling::*", "xpath", "xpath"),
    objNextButton ("//*[@text='Next']/parent::*","//XCUIElementTypeStaticText[@value='Next']/parent::*", "xpath", "xpath"),
    objProceedToPayButton ("//android.widget.Button[@text='Proceed to Pay']","//*[@name='Proceed to Pay']", "xpath", "xpath"),
    objTransactionStatusHeader ("(//*[@content-desc='View transaction details']/parent::*/child::android.widget.TextView)[1]","(//XCUIElementTypeStaticText[@value='View transaction details']/parent::*/parent::*/preceding-sibling::*)[1]", "xpath", "xpath"),
    objTransactionInfo ("(//*[@content-desc='View transaction details']/parent::*/child::android.widget.TextView)[2]","(//XCUIElementTypeStaticText[@value='View transaction details']/parent::*/parent::*/preceding-sibling::*)[2]", "xpath", "xpath"),
    objButtonDisplayedInTransactionInfoPage ("//*[@content-desc='View transaction details']/following-sibling::*/child::android.widget.TextView","//*[@value='Ok'] | //XCUIElementTypeStaticText[@label='Back to Dashboard']", "xpath", "xpath"),
    objContactUsIconInTransactionDetails ("//*[@resource-id='com.tonik.mobile:id/Header_right_click']/child::*", "//*[@name='com.tonik.mobile:id/Header_right_click']/child::*", "xpath", "xpath"),
    objTopUpViaGCash ("//*[@text='Top up via GCash']", "//XCUIElementTypeStaticText[contains(@label,'Top up via GCash')]", "xpath", "xpath"),
    objGCashTransactionHistory ("(//*[@text='Top-up'])[1]/preceding-sibling::android.widget.TextView", "(//*[contains(@value,'Top-up')])[1]", "xpath", "xpath"),
    objMaxTransactionErrorMsg ("(//android.widget.EditText/following-sibling::android.widget.TextView)[1]", "//XCUIElementTypeTextField/parent::*/parent::*/following-sibling::*/child::*", "xpath", "xpath"),
    objBPIOnlineOption ("//*[@text=' BPI']", "//*[@value=' BPI']", "xpath", "xpath"),
    objChinaBankOnlineOption ("//*[@text=' Chinabank']", "//*[@value=' Chinabank']", "xpath", "xpath"),
    objRCBCOnlineOption ("//*[@text=' RCBC']", "//*[@value=' RCBC']","xpath", "xpath"),
    objUnionBankOnlineOption ("//*[@text=' Unionbank']", "//*[@value=' Unionbank']", "xpath", "xpath"),
    objPopupHeader ("//*[@resource-id='com.tonik.mobile:id/Popup_title_text']", "//XCUIElementTypeStaticText[@name='com.tonik.mobile:id/Popup_title_text']", "xpath", "xpath"),
    objPopupDescription ("//*[@resource-id='com.tonik.mobile:id/Popup_Description']", "//XCUIElementTypeStaticText[@name='com.tonik.mobile:id/Popup_Description']", "xpath", "xpath"),
    objNotYetButton ("//*[@resource-id='Not yet']", "//*[@value='Not yet']", "xpath", "xpath"),
    objYesIWantToTopUp ("//*[@resource-id='Yes I want to top up']", "//*[@value='Yes I want to top up']", "xpath", "xpath"),
    objTopUpBankScreen ("//*[@text='Top up using PESONet or InstaPay']", "//*[@value='Top up using PESONet or InstaPay']", "xpath", "xpath"),
    objTransactionDetailsInfo ("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView", "//*[contains(@name,'Amount') and contains(@name,'From')]/child::*/child::*[1]/child::XCUIElementTypeStaticText", "xpath", "xpath"),
    objTransactionDetails ("//*[contains(@text,'Transaction')]", "//*[contains(@value,'Transaction')]", "xpath", "xpath"),
    objBKYCMaxLimit ("//*[contains(@text,'Your account limit is')]", "//*[contains(@value,'Your account limit is')]", "xpath", "xpath"),
    objAccountNumber ("//*[contains(@text,' ACCOUNT NUMBER')]", "//*[contains(@value,' ACCOUNT NUMBER')]", "xpath", "xpath"),
    objPageTitle("//*[@resource-id='com.tonik.mobile:id/Main_title_txt']","//XCUIElementTypeStaticText[@name='com.tonik.mobile:id/Main_title_txt']","xpath","xpath"),
    objBackBtn("//*[@resource-id='com.tonik.mobile:id/Header_left_click']","//XCUIElementTypeOther[@name=\"com.tonik.mobile:id/Header_left_click\"]","xpath","xpath"),
    objPageSubTitle("//*[@resource-id='com.tonik.mobile:id/Sub_title_txt']","//XCUIElementTypeStaticText[@name='com.tonik.mobile:id/Sub_title_txt']","xpath","xpath"),
    objViewTransactionDetails("//*[@content-desc='View transaction details']","//XCUIElementTypeStaticText[@label='View transaction details']","xpath","xpath");
    private String android;
    private String ios;
    private String andPathType;
    private String iosPathType;
    TopUpPage(String android, String ios, String andPathType, String iosPathType) {
        this.android = android;
        this.ios = ios;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }
    TopUpPage(String android, String andPathType, String iosPathType) {
        this.android = android;
        this.ios = android;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }
    TopUpPage(String android, String andPathType) {
        this.android = android;
        this.ios = android;
        this.andPathType = andPathType;
        this.iosPathType = andPathType;
    }
    public static By getByOSType(String osType, TopUpPage objectName) {
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
    public static By objTransactionDetailsInfo(String platform, String info) {
        if(platform.equalsIgnoreCase("android")){
            return By.xpath("//*[@text='"+info+"']/following-sibling::android.widget.TextView");
        } else {
            // add iOS parameter
            return By.xpath("//*[@value='"+info+"']/parent::*/following-sibling::*/child::XCUIElementTypeStaticText");
        }
    }
    public static By objTransactionDetailsInfo(String platform, int info) {
        if(platform.equalsIgnoreCase("android")){
            return By.xpath("(//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView)["+info+"]");
        } else {
            // add iOS parameter
            return By.xpath("(//*[contains(@name,'Amount') and contains(@name,'From')]/child::*/child::*[1]/child::XCUIElementTypeStaticText)["+info+"]");
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