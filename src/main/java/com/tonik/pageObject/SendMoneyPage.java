package com.tonik.pageObject;
import org.openqa.selenium.By;

public enum SendMoneyPage {
//  Send Icon and page
    objSendIcon("//*[@text='Send']/preceding-sibling::*","//XCUIElementTypeStaticText[@name='Send']/preceding-sibling::XCUIElementTypeOther","xpath","xpath"),
    objPageHeader("//*[@resource-id='com.tonik.mobile:id/Main_title_txt']","//XCUIElementTypeStaticText[@name='com.tonik.mobile:id/Main_title_txt']","xpath","xpath"),
    objPageSubTitle("//*[@resource-id='com.tonik.mobile:id/Sub_title_txt']","//XCUIElementTypeStaticText[@name='com.tonik.mobile:id/Sub_title_txt']","xpath","xpath"),
    objToAnotherBankOption("//*[@text='To another bank']","//*[@label='To another bank']","xpath","xpath"),
    objToAnotherTonikCustomerOption("//*[@text='To another Tonik customer']","//*[@label='To another Tonik customer']","xpath","xpath"),
    objSendMoneyToBankOptionsDropdown("//*[@resource-id='com.tonik.mobile:id/Button_click']","//XCUIElementTypeStaticText[@name='com.tonik.mobile:id/_Text_View_3']","xpath","xpath"),
    objSendMoneyViaDropdown("//*[@text='Send money via?']","//XCUIElementTypeOther[@name='Send money via?']","xpath","xpath"),
    objPesonetOption("//*[@text='PESONet']","//XCUIElementTypeStaticText[contains(@label,'PESONet')]","xpath","xpath"),
    objPesonetTransactionLimit("//*[@text='PESONet']/following-sibling::android.widget.TextView","//*[@label='PESONet']/following-sibling::XCUIElementTypeOther/child::XCUIElementTypeStaticText","xpath","xpath"),
    objInstaPayOption("//*[@text='InstaPay']","//XCUIElementTypeStaticText[@label='InstaPay']","xpath","xpath"),
    objInstaPayTransactionLimit("//*[@text='InstaPay']/following-sibling::android.widget.TextView","//*[@label='InstaPay']/following-sibling::XCUIElementTypeOther/child::XCUIElementTypeStaticText","xpath","xpath"),
    objCurrentBalance("//*[contains(@text,'Current')]","//XCUIElementTypeStaticText[contains(@label,'Current')]","xpath","xpath"),
    objTransactionFee("//*[contains(@text,'Transaction')]","//XCUIElementTypeStaticText[contains(@label,'Transaction')]","xpath","xpath"),
    objConfirmSendMoneyAccNumber("//*[contains(@text,'Account')]/following-sibling::*/child::android.widget.TextView","//*[contains(@label,'Account')]/following-sibling::XCUIElementTypeOther/child::*","xpath","xpath"),
    objTransactionDetailsAmount("//*[@text='Amount']/following-sibling::*","//*[@label='Amount']/following-sibling::XCUIElementTypeOther/child::*","xpath","xpath"),
    objTransactionDetailsFee("//*[@text='Fee']/following-sibling::*","//*[@label='Fee']/following-sibling::XCUIElementTypeOther/child::*","xpath","xpath"),
    objTransactionDetailsTo("//*[@text='To']/following-sibling::*","//*[@label='To']/following-sibling::XCUIElementTypeOther/child::*","xpath","xpath"),
    objTransactionDetailsFrom("//*[@text='From']/following-sibling::*","//*[@label='From']/following-sibling::XCUIElementTypeOther/child::*","xpath","xpath"),
    objTransactionDetailsWhen("//*[@text='When']/following-sibling::*","//*[@label='When']/following-sibling::XCUIElementTypeOther/child::*","xpath","xpath"),
    objNextBtn("//*[@text='Next']/parent::*","//XCUIElementTypeStaticText[@label='Next']/parent::*","xpath","xpath"),
    objAmountInputField("//*[@text='₱']/following-sibling::android.widget.EditText","//XCUIElementTypeTextField[@value='₱']/parent::*/following-sibling::XCUIElementTypeOther/child::XCUIElementTypeTextField","xpath","xpath"),
    objPurposeDropdown("//*[@text='Purpose']/parent::*/child::android.widget.EditText","//XCUIElementTypeOther[@label='Purpose']/child::XCUIElementTypeOther/child::XCUIElementTypeTextField","xpath","xpath"),
    objPurposes("//*[@class='android.widget.ScrollView']/child::*/child::*/child::*/child::android.widget.TextView","//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText","xpath","xpath"),
    objRecipientNumber("Send money_Text_View_1","//XCUIElementTypeStaticText[@name=\"com.tonik.mobile:id/Send money_Text_View_1\"]","id","xpath"),
    objPurposeEditField("//*[@text='Purpose']/parent::*/child::android.widget.EditText","(//XCUIElementTypeTextField[@name=\"com.tonik.mobile:id/Text_Input_1\"])[2]","xpath","xpath"),
    objMaxTransactionErrorMsg("(//*[@text='₱']/following-sibling::android.widget.TextView)[1]","//XCUIElementTypeTextField[@name=\"com.tonik.mobile:id/Text_Input_1\"]/parent::*/parent::*/following-sibling::*/child::XCUIElementTypeStaticText | //XCUIElementTypeStaticText[@label=\"This amount exceeds your Tonik balance\"]","xpath","xpath"),
    objUseTonikAccountLink("//*[@text='Use the Tonik account number instead']", "//XCUIElementTypeStaticText[@label='Use the Tonik account number instead']","xpath","xpath"),
    objContactsIcon("//*[@class='android.widget.EditText']/following-sibling::android.view.ViewGroup","(//XCUIElementTypeOther[@name=\"+ 63 -\"])[3]/XCUIElementTypeOther[4]","xpath","xpath"),
    objCutOffMessage("//*[contains(@text,'4PM daily cut-off')]","//XCUIElementTypeStaticText[contains(@label,'4PM daily cut-off')]","xpath","xpath"),
//  Confirm send money page
    objConfirmButton("//*[@text='Confirm']","//XCUIElementTypeStaticText[@label='Confirm']","xpath","xpath"),
//  Transaction Status page
    objScoreStatus("//*[@text='Scooore!']","//XCUIElementTypeStaticText[@label='Scooore!']","xpath","xpath"),
    objPopUpTitle("//*[@resource-id='com.tonik.mobile:id/Popup_title_text']","//XCUIElementTypeStaticText[@name='com.tonik.mobile:id/Popup_title_text']","xpath","xpath"),
    objPopupDescription("//*[@resource-id='com.tonik.mobile:id/Popup_Description']","//XCUIElementTypeStaticText[@name='com.tonik.mobile:id/Popup_Description']","xpath","xpath"),
    objOkayLetsGoBtn("//*[@resource-id='Okay, let’s go']","//XCUIElementTypeStaticText[@name='Okay, let’s go']","xpath","xpath"),
    objEntireScreen("//*[@resource-id='android:id/content']","","xpath","xpath"),
    objSendMoneyOptionsPage("//*[@text='Send money options']","//XCUIElementTypeStaticText[@label='Send money options']","xpath","xpath"),
    objSendMoneyPage("//*[contains(@text,'money')]","//XCUIElementTypeStaticText[contains(@label,'money')]","xpath","xpath"),
//  Account details
    objAccountHolderName("//*[@text='Account holder name']/parent::*/child::android.widget.EditText","//XCUIElementTypeStaticText[contains(@label,\"Account holder name\")]/preceding-sibling::XCUIElementTypeOther/child::XCUIElementTypeTextField","xpath","xpath"),
    objBankNameEditField("//*[@text='Bank name']/parent::*/child::android.widget.EditText","(//*[@name=\"Bank name\"]/XCUIElementTypeOther/child::XCUIElementTypeOther/child::XCUIElementTypeOther/child::XCUIElementTypeOther)[6]","xpath","xpath"),
    objAccountNumber("//*[@text='Account number']/parent::*/child::android.widget.EditText","//XCUIElementTypeStaticText[contains(@label,\"Account number\")]/preceding-sibling::XCUIElementTypeOther/child::XCUIElementTypeTextField","xpath","xpath"),
    objFindYourBankPage("//*[@text='Find your Bank']","//XCUIElementTypeStaticText[@label='Find your Bank']","xpath","xpath"),
    objSearchBank("//android.widget.EditText","(//XCUIElementTypeOther[@name=\"Type here to search\"])[3]/child::XCUIElementTypeOther/child::XCUIElementTypeTextField","xpath","xpath"),
    objNoiseScreen("//*[@text='Nooice!']","//XCUIElementTypeStaticText[@label='Nooice!']","xpath","xpath"),
    objThankNote("//*[contains(@text,'Thanks')]","//XCUIElementTypeStaticText[contains(@label,'Thanks')]","xpath","xpath"),
    objConfirmSendMoneyPage("//*[@text='Confirm send money']","//*[@label='Confirm send money']","xpath","xpath"),

    objPESONetTransactionStatus("//*[@text='Working on it, luv']","//XCUIElementTypeStaticText[@label='Working on it, luv']","xpath","xpath"),
    objPESONetTransactionInformation("//*[contains(@text,' transfer')]","//XCUIElementTypeStaticText[contains(@label,' transfer')]","xpath","xpath"),
    objViewTransactionDetails("//*[@text='View transaction details']","//XCUIElementTypeStaticText[@label='View transaction details']","xpath","xpath"),
    objTransactionDetails("//*[contains(@text,'Transaction')]","//XCUIElementTypeStaticText[contains(@label,'Transaction')]","xpath","xpath"),
    objBackToDashboard("//*[@text='Back to Dashboard']","//XCUIElementTypeStaticText[@label='Back to Dashboard']","xpath","xpath"),
    objPESONetTransaction("(//*[@text='PESONet'])[1]","(//XCUIElementTypeStaticText[@name=\"PESONet\"])[1]","xpath","xpath"),
    objInstaPayTransaction("(//*[@text='InstaPay'])[1]","(//XCUIElementTypeStaticText[@name=\"InstaPay\"])[1]","xpath","xpath"),
    objTransactionSummary("//*[@text='Transaction Summary']","//XCUIElementTypeStaticText[@value=\"Transaction Summary\"]","xpath","xpath"),
    objTransactionDate("//*[contains(@text,'Transaction Date')]","//XCUIElementTypeStaticText[contains(@value,\"Transaction Date\")]","xpath","xpath"),
    objReferenceNo("//*[contains(@text,'Reference No')]","//XCUIElementTypeStaticText[contains(@value,\"Reference No\")]","xpath","xpath"),
    objRefNumber("//*[contains(@text,'Reference No')]/following-sibling::android.widget.TextView","//XCUIElementTypeStaticText[contains(@value,\"Reference No\")]","xpath","xpath"),
    objTonikToTonikTransactionInfo("//*[contains(@text,'This transaction is always free')]","//XCUIElementTypeStaticText[contains(@label,'This transaction is always free')]","xpath","xpath"),
    objTonikToTonikSendMoney("//*[@text='Send money']/preceding-sibling::android.widget.TextView","(//XCUIElementTypeStaticText[@value=\"Send money\"])[1]/preceding-sibling::XCUIElementTypeStaticText","xpath","xpath"),
    objAccNumberInputField("//android.widget.EditText","//XCUIElementTypeTextField[@name=\"com.tonik.mobile:id/Send moneyText_Input_1\"]","xpath","xpath"),
    objMaxTransactionTonikCustomer("//*[contains(@text,'Tonik-to-Tonik transfers are limited to')]","//XCUIElementTypeStaticText[contains(@label,'Tonik-to-Tonik transfers are limited to')]","xpath","xpath"),
    objSorryScreen("//*[@text='So sorry!']","//XCUIElementTypeStaticText[@label='So sorry!']","xpath","xpath"),
    objSameFromAndToAccountMsg("//*[@text=\"From and To accounts can't be same.\"]","//XCUIElementTypeStaticText[@label=\"From and To accounts can't be same.\"]","xpath","xpath"),
    objGotIt("//*[contains(@text,'Got')]","//XCUIElementTypeStaticText[contains(@label,'Got')]","xpath","xpath"),
    objInvalidAccountNumber("(//*[@text='So sorry!']/following-sibling::*)[1]","//XCUIElementTypeStaticText[@name=\"Sorry. We can't find the account number you entered with us.\"]","xpath","xpath"),
    objRefundAmount("(//*[@text='InstaPay refund']/following-sibling::android.widget.TextView)[2]","//XCUIElementTypeOther[@name=\"InstaPay refund Transaction failed\"]/following-sibling::XCUIElementTypeStaticText","xpath","xpath"),
    objInstaPayRefund("//*[@text='InstaPay refund']","//XCUIElementTypeStaticText[@label='InstaPay refund']","xpath","xpath"),
    objSaveRecipientSwitchSlider("//android.view.ViewGroup[@resource-id=\"com.tonik.mobile:id/Confirm send moneyList_Click_\"]","//XCUIElementTypeOther[@name=\"com.tonik.mobile:id/Confirm send moneyList_Click_\"]/XCUIElementTypeOther/XCUIElementTypeOther","xpath","xpath"),
    objRecipientFirstLetter("//*[@resource-id='com.tonik.mobile:id/ List_Click_0']/child::*/child::android.widget.TextView","((//XCUIElementTypeOther[@name=\"Quick Send list\"]/following-sibling::XCUIElementTypeOther)[1]/child::XCUIElementTypeOther/child::XCUIElementTypeOther/child::XCUIElementTypeOther)[1]/XCUIElementTypeOther/XCUIElementTypeStaticText","xpath","xpath"),
    objRecipientsName("(//*[@resource-id='com.tonik.mobile:id/ List_Click_0']/child::android.widget.TextView)[1]","((//XCUIElementTypeOther[@name=\"Quick Send list\"]/following-sibling::XCUIElementTypeOther)[1]/child::XCUIElementTypeOther/child::XCUIElementTypeOther/child::XCUIElementTypeOther)[2]/XCUIElementTypeStaticText","xpath","xpath"),
    objEllipseButton("//*[@resource-id='com.tonik.mobile:id/ List_Click_0']/child::android.view.ViewGroup/child::android.view.ViewGroup","((//XCUIElementTypeOther[@name=\"Quick Send list\"]/following-sibling::XCUIElementTypeOther)[1]/child::XCUIElementTypeOther/child::XCUIElementTypeOther/child::XCUIElementTypeOther)[3]/XCUIElementTypeOther","xpath","xpath"),
    objDeleteRecipient("//*[contains(@text,'Delete')]","//XCUIElementTypeStaticText[contains(@label,'Delete')]","xpath","xpath"),
    objMessageInputField("//*[@resource-id='com.tonik.mobile:id/Confirm send moneyText_Input_1']","//XCUIElementTypeTextField[@name=\"com.tonik.mobile:id/Confirm send moneyText_Input_1\"]","xpath","xpath"),
    objInvalidErrorMsg("//*[@resource-id='com.tonik.mobile:id/Confirm send moneyText_Error_1']","//XCUIElementTypeStaticText[@name='com.tonik.mobile:id/Confirm send moneyText_Error_1']","xpath","xpath"),
    objMessageInTransactionHistory("//*[@text='Message']/following-sibling::*","//XCUIElementTypeStaticText[@name=\"com.tonik.mobile:id/Message_Text_View\"]/following-sibling::*","xpath","xpath"),
    objMaxOTPAttempt("com.tonik.mobile:id/Popup_sub_header_txt","","xpath","xpath"),
    objOTPPopup("com.tonik.mobile:id/Popup_sub_header_txt","com.tonik.mobile:id/Popup_sub_header_txt","id","id"),
    objPopupPositiveBtn("com.tonik.mobile:id/Popup_positive_btn_txt","com.tonik.mobile:id/Popup_positive_btn_txt","id","name"),
    objDoneButton("","//XCUIElementTypeButton[@name='Done']","xpath","xpath"),
    objList("//*[@class='android.widget.ScrollView']/child::*/child::*/child::*/child::android.widget.TextView","//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText","xpath","xpath"),
    objAmount("","//XCUIElementTypeStaticText[@label=\"Amount\"]","","xpath"),
    objFee("","//XCUIElementTypeStaticText[@label=\"Fee\"]","","xpath"),
    objTotal("","//XCUIElementTypeStaticText[@label=\"Total\"]","","xpath"),
    objFrom("","//XCUIElementTypeStaticText[@label=\"From\"]","","xpath"),
    objTo("","//XCUIElementTypeStaticText[@label=\"To\"]","","xpath"),
    objTransferVia("","//XCUIElementTypeStaticText[@label=\"Transfer via\"]","","xpath"),
    objBank("","//XCUIElementTypeStaticText[@label=\"Bank\"]","","xpath"),
    objAccNumber("","//XCUIElementTypeStaticText[contains(@label,'Account')]","","xpath"),
    objAccNumberDisplayed("//android.widget.TextView[@resource-id=\"com.tonik.mobile:id/Send money_Text_View_2\"]","//XCUIElementTypeStaticText[@name=\"com.tonik.mobile:id/Send money_Text_View_2\"]","xpath","xpath"),
    objBackBtn("//*[@resource-id='com.tonik.mobile:id/Header_left_click']", "//XCUIElementTypeOther[@name=\"com.tonik.mobile:id/Header_left_click\"]","xpath","xpath"),
    objResendOTP("//*[@text='Resend OTP']","//XCUIElementTypeStaticText[@label='Resend OTP']","xpath","xpath");
    private String android;
    private String ios;
    private String andPathType;
    private String iosPathType;

    SendMoneyPage(String android, String ios, String andPathType, String iosPathType) {
        this.android = android;
        this.ios = ios;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }
    SendMoneyPage(String android, String andPathType, String iosPathType) {
        this.android = android;
        this.ios = android;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }
    SendMoneyPage(String android, String andPathType) {
        this.android = android;
        this.ios = android;
        this.andPathType = andPathType;
        this.iosPathType = andPathType;
    }

    public static By getByOSType(String osType, SendMoneyPage objectName) {
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
    public static By objConfirmSendMoneyInfo(String osType, String info){
        if(osType.equalsIgnoreCase("Android")){
            return By.xpath("//*[@text='"+info+"']/following-sibling::android.view.ViewGroup/child::android.widget.TextView");
        }else{
            return By.xpath("//*[@label='"+info+"']/following-sibling::XCUIElementTypeOther/child::*");
        }
    }
    public static By objPurpose(String osType,int purpose){
        if(osType.equalsIgnoreCase("Android")) {
            return By.xpath("(//*[@class='android.widget.ScrollView']/child::*/child::*/child::*/child::android.widget.TextView)[" + purpose + "]");
        }else{
            return By.xpath("(//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText)[" + purpose + "]");
        }
    }
    public static By objConfirmSendMoneyInfoList(String osType,int info){
        if(osType.equalsIgnoreCase("Android")) {
            return By.xpath("(//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView)[" + info + "]");
        }else{
            return By.xpath("");
        }
    }
    public static By objTransactionInfo(String osType,String info){
        if(osType.equalsIgnoreCase("Android")) {
            return By.xpath("//*[@text='" + info + "']/following-sibling::*");
        }else{
            return By.xpath("//*[@name='" + info + "']/following-sibling::XCUIElementTypeOther/child::XCUIElementTypeStaticText");
        }
    }
    public static By objTransactionDetailsInfo(String osType,int info) {
        if(osType.equalsIgnoreCase("Android")) {
            return By.xpath("(//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView)["+info+"]");
        }else{
            return By.xpath("(//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther//XCUIElementTypeOther/XCUIElementTypeStaticText)["+info+"]");
        }
    }
    public static By objSelectContact(String osType, String sContactName) {
        if (osType.equalsIgnoreCase("Android")) {
            return By.xpath("//android.widget.TextView[contains(@text,'" + sContactName + "')]");
        }else{
            return By.xpath("//XCUIElementTypeStaticText[contains(@label,'" + sContactName + "')]");
        }
    }
    public static By objTransactionSummary(String osType,String info){
        if(osType.equalsIgnoreCase("Android")) {
            return By.xpath("//*[@text='"+info+"']/following-sibling::android.widget.TextView");
        }else{
            return By.xpath("//*[@name='" + info + "']/following-sibling::XCUIElementTypeOther/child::XCUIElementTypeStaticText");
        }
    }
    public static By objRecipientName (String osType,String sRecipientName){
        if(osType.equalsIgnoreCase("Android")) {
            return By.xpath("//*[@text='"+sRecipientName+"']");
        }else{
            return By.xpath("//XCUIElementTypeStaticText[@label='"+sRecipientName+"']");
        }
    }
    public static By objList(String osType, int listElement) {
        if (osType.equalsIgnoreCase("Android")) {
            return By.xpath("(//*[@class='android.widget.ScrollView']/child::*/child::*/child::*/child::android.widget.TextView)[" + listElement + "]");
        } else {
            return By.xpath("(//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText)[" + listElement + "]");
        }
    }
    public static By objTransactionDetailsInfo(String osType){
        if (osType.equalsIgnoreCase("Android")) {
            return By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView");
        } else {
            return By.xpath("//XCUIElementTypeScrollView/child::XCUIElementTypeOther/child::XCUIElementTypeOther/child::XCUIElementTypeOther/child::XCUIElementTypeOther/child::XCUIElementTypeOther/child::XCUIElementTypeStaticText");
        }
    }
}