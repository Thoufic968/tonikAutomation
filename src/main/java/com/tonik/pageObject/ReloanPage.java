package com.tonik.pageObject;

import org.openqa.selenium.By;

public enum ReloanPage {
    //Loans tile page
    objLoanTileHeader("//*[contains(@text,'Loans')]","//*[contains(@value,'Loans')]","xpath","xpath"),
    objLoansTileSubText1("//*[contains(@text,'Loans')]/following-sibling::android.widget.TextView[1]","(//*[contains(@value,'Loans')]/parent::*/following-sibling::*/child::*)[1]","xpath","xpath"),
    objLoansTileSubText2("//*[contains(@text,'Loans')]/following-sibling::android.widget.TextView[2]","(//*[contains(@value,'Loans')]/parent::*/following-sibling::*/child::*)[2]","xpath","xpath"),
    //Tonik to the rescue page
    objTonikToRescuePageHeader("//*[contains(@text,'rescue')]","//*[contains(@value,'rescue')]","xpath","xpath"),
    objTonikToRescueDescription("//*[contains(@text,'rescue')]/following-sibling::android.widget.TextView","//*[contains(@value,'rescue')]/following-sibling::*","xpath","xpath"),
    objDeclineTheOfferButton("//*[contains(@text,'I decline the offer')]","//*[contains(@value,'I decline the offer')]","xpath","xpath"),
    objTellMeMoreButton("com.tonik.mobile:id/Custom_button_txt","com.tonik.mobile:id/Custom_button_txt","id","id"),
    //You got an approved loan offer page
    objLoanOfferPageHeader("//*[contains(@text,'approved loan offer')]","//*[contains(@value,'approved loan offer')]","xpath","xpath"),
    objLoanOfferPageHeaderDescription("//*[contains(@text,'approved loan offer')]/following-sibling::android.widget.TextView[1]","//*[contains(@value,'Select a payment option')]","xpath","xpath"),
    objChooseYourPayment("//*[contains(@text,'Choose your payment term')]","//*[contains(@value,'Choose your payment term')]","xpath","xpath"),
    objYourMonthlyInstallmentText("//*[contains(@text,'monthly installments')]","//*[contains(@value,'monthly installments')]","xpath","xpath"),
    objYourMonthlyInstallmentAmount("//*[contains(@text,'monthly installments')]/following-sibling::android.widget.TextView","//*[contains(@value,'monthly installments')]/following-sibling::XCUIElementTypeStaticText","xpath","xpath"),
    objNoThanksButton("//*[contains(@text,'No thanks')]","//*[contains(@value,'No thanks')]","xpath","xpath"),
    objIAcceptTheOfferButton("//*[contains(@text,'I accept the offer')]","//*[contains(@value,'I accept the offer')]","xpath","xpath"),
    objBackArrowButton("//*[@resource-id='com.tonik.mobile:id/Header_left_click']/android.view.ViewGroup","com.tonik.mobile:id/Header_left_click","xpath","id"),
    //Summary loan screen
    objLoanSummaryHeader("com.tonik.mobile:id/Main_title_txt","com.tonik.mobile:id/Main_title_txt","id","id"),
    objLoanAmountText("//*[@text='Loan amount']","//*[@value='Loan amount']","xpath","xpath"),
    objLoanAmountValue("//*[@text='Loan amount']/parent::*/following-sibling::*[contains(@text,'₱')]","//*[@value='Loan amount']/parent::*/following-sibling::*","xpath","xpath"),
    objInstallmentTermsText("//*[contains(@text,'Installment terms')]","//*[contains(@value,'Installment terms')]","xpath","xpath"),
    objInstallmentTermsValue("//*[contains(@text,'Installment terms')]/parent::*/following-sibling::*","//*[@value='Installment terms']/parent::*/following-sibling::*","xpath","xpath"),
    objMonthlyAddOnRateText("//*[contains(@text,'Monthly add-on rate')]","//*[contains(@value,'Monthly add-on rate')]","xpath","xpath"),
    objMonthlyAddOnRateValue("//*[contains(@text,'Monthly add-on rate')]/parent::*/following-sibling::*","//*[@value='Monthly add-on rate']/parent::*/following-sibling::*","xpath","xpath"),
    objProcessingFeeText("//*[contains(@text,'Processing fee')]","//*[contains(@value,'Processing fee')]","xpath","xpath"),
    objProcessingFeeValue("//*[contains(@text,'Processing fee')]/parent::*/following-sibling::*[contains(@text,'₱')]","//*[@value='Processing fee']/parent::*/following-sibling::*","xpath","xpath"),
    objDocumentaryStampText("//*[contains(@text,'Documentary stamp')]","//*[contains(@value,'Documentary stamp')]","xpath","xpath"),
    objDocumentaryStampValue("//*[contains(@text,'Documentary stamp')]/parent::*/following-sibling::*[contains(@text,'₱')]","//*[@value='Documentary stamp tax']/parent::*/following-sibling::*","xpath","xpath"),
    objSweetIAcceptText("com.tonik.mobile:id/Button_text","com.tonik.mobile:id/Button_text","id","id"),
    objSummaryLoanPageFooter("com.tonik.mobile:id/Footer_txt","com.tonik.mobile:id/Footer_txt","id","id"),
    objMonthlyPaymentText("//*[contains(@resource-id,'Monthly payment')]","//*[contains(@value,'Monthly payment')]","xpath","xpath"),
    objMonthlyPaymentAmount("//*[contains(@resource-id,'Monthly payment')]/../following-sibling::android.widget.TextView","//*[contains(@value,'Monthly payment')]/parent::*/following-sibling::*","xpath","xpath"),
    objDeductedFromLoanText("//*[contains(@resource-id,'Deducted from loan amount')]","//*[contains(@value,'Deducted from loan amount')]","xpath","xpath"),
    //What do you need it for? page
    objWhatDoYouNeedPageHeader("//*[contains(@text,'What do you need it for?')]","//*[contains(@value,'What do you need it for?')]","xpath","xpath"),
    objWhatDoYouNeedPageSubText("//*[contains(@text,'I will use')]","//*[contains(@value,'I will use')]","xpath","xpath"),
    objLoanDetails("//*[@class='android.widget.ScrollView']/android.view.ViewGroup/descendant::android.widget.TextView","//*[@type='XCUIElementTypeScrollView']/XCUIElementTypeOther/descendant::XCUIElementTypeStaticText","xpath","xpath"),
    objNextButton("com.tonik.mobile:id/Button_click","com.tonik.mobile:id/Button_click","id","id"),
    //Is this your current address? page
    objIsThisYourCurrentAddressButton("(//*[@resource-id='com.tonik.mobile:id/Submit_click'])[2]","(//*[@name='com.tonik.mobile:id/Submit_click'])[2]","xpath","xpath"),
    //Terms and conditions page
    objTermsAndConditionPageHeader("(//*[contains(@text,'Terms and Conditions')])[1]","(//*[contains(@value,'Terms and Conditions')])[1]","xpath","xpath"),
    //loan tile page after decline loan
    objLoanTileHeaderAfterDecline("//*[contains(@text,'Fast cash or easy payments?')]","//*[contains(@value,'Fast cash or easy payments?')]","xpath","xpath"),
    objLoansTile("//*[contains(@text,'Loans')]","//*[contains(@value,'Loans')]","xpath","xpath"),
    objFindTheRightLoan("//*[contains(@text,'Find the right loan that fits your needs and wants. One day approval.')]","//*[contains(@value,'Find the right loan that fits your needs and wants. One day approval.')]","xpath","xpath"),
    objSorryIt("//*[contains(@text,'Sorry it wasn’t a match...')]","//*[contains(@value,'Sorry it wasn’t a match...')]","xpath","xpath"),
    objNextButtonInDeclineLoanPage("com.tonik.mobile:id/Custom_button_click","com.tonik.mobile:id/Custom_button_click","id","id"),
    objMonthlyPayDueText("//*[@text='Monthly payment due']","//*[@value='Monthly payment due']","xpath","xpath"),
    objMonthlyPayDueValue("//*[@text='Monthly payment due']/following-sibling::*","//*[@value='Monthly payment due']/parent::*/following-sibling::*/child::*","xpath","xpath"),
    objInsatallmentTxt("//*[@text='Installment period']","//*[@value='Installment period']","xpath","xpath"),
    objInstallmentPeriodMonth("//*[contains(@text,'months')]","//*[contains(@value,'months')]","xpath","xpath"),
    objFirstInstallmentTxt("//*[@text='First installment due date']","//*[@value='First installment due date']","xpath","xpath"),
    objInstallmentDuedate("//*[@text='First installment due date']/following-sibling::*","//*[@value='First installment due date']/parent::*/following-sibling::*/child::*","xpath","xpath"),
    objLateFeeTxt("//*[@text='Late fee'] | //*[@text='Late Fee']","//*[@value='Late fee'] | //*[@value='Late Fee']","xpath","xpath"),
    objLateFeeAmount("//*[@text='Late fee']/following-sibling::* | //*[@text='Late Fee']/following-sibling::*","//*[@value='Late fee']/following-sibling::* | //*[@value='Late Fee']/following-sibling::*","xpath","xpath"),
    objMoneyCreditedAmount("(//*[@text='Money Credited']/following-sibling::*)[1]","//*[@value='Money Credited']/parent::*/following-sibling::*","xpath","xpath"),
    objFastCashText("//*[contains(@text,'Fast cash or easy payments')]","//*[contains(@value,'Fast cash or easy payments')]","xpath","xpath"),
    objMonths("(//android.widget.ScrollView)[3]/android.view.ViewGroup/android.view.ViewGroup","(//XCUIElementTypeScrollView)[2]/XCUIElementTypeOther/child::XCUIElementTypeOther","xpath","xpath"),
    objLoanTileDesc("//*[contains(@text,'Find the right loan that fits your needs')]","//*[contains(@value,'Find the right loan that fits your needs')]","xpath","xpath");
    private String android;
    private String ios;
    private String andPathType;
    private String iosPathType;
    ReloanPage(String android, String ios, String andPathType, String iosPathType) {
        this.android = android;
        this.ios = ios;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }
    public static By getByOSType(String osType, ReloanPage objectName) {
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

    public static By objTerms(String osType, String term){
        if(osType.equalsIgnoreCase("Android")){
            return By.xpath("//*[contains(@text,'Months')]/preceding-sibling::android.widget.TextView[@text='"+term+"']");
        }else{
            return By.xpath("//*[contains(@value,'Months')]/parent::*/preceding-sibling::*/child::*[@value='"+term+"']");
        }
    }
    public static By objLoanAmountBasedOnTerms(String osType, String term){
        if(osType.equalsIgnoreCase("Android")){
            return By.xpath("//*[contains(@text,'Months')]/preceding-sibling::android.widget.TextView[@text='"+term+"']/following-sibling::android.widget.TextView[1]");
        }else{
            return By.xpath("//*[contains(@value,'Months')]/parent::*/preceding-sibling::*/child::*[@value='"+term+"']/following-sibling::*");
        }
    }
    public static By objMonthsBasedOnTerms(String osType, String term){
        if(osType.equalsIgnoreCase("Android")){
            return By.xpath("//*[contains(@text,'Months')]/preceding-sibling::android.widget.TextView[@text='"+term+"']/following-sibling::android.widget.TextView[2]");
        }else{
            return By.xpath("(//*[@value='"+term+"']/parent::*/following-sibling::*/child::*)[1]");
        }
    }
    public static By objYouWillReceiveTextBasedOnTerms(String osType, String term){
        if(osType.equalsIgnoreCase("Android")){
            return By.xpath("//*[contains(@text,'Months')]/preceding-sibling::android.widget.TextView[@text='"+term+"']/following-sibling::android.widget.TextView[3]");
        }else{
            return By.xpath("(//*[@value='"+term+"']/parent::*/following-sibling::*/child::*)[2]");
        }
    }
    public static By objLoanPurpose(String osType, String loanPurpose){
        if(osType.equalsIgnoreCase("Android")){
            return By.xpath("(//*[contains(@resource-id,'"+loanPurpose+"')])[1]");
        }else{
            return By.xpath("//*[contains(@value,'"+loanPurpose+"')]");
        }
    }
    public static By objLoanDeclineReason(String osType, String reason){
        if(osType.equalsIgnoreCase("Android")){
            return  By.xpath("//*[contains(@text,'"+reason+"')]");
        }else{
            return By.xpath("//*[contains(@value,'"+reason+"')]");
        }
    }
}