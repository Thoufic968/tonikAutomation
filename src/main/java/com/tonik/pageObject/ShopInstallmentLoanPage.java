package com.tonik.pageObject;

import org.openqa.selenium.By;

public enum ShopInstallmentLoanPage {
    objLoanTileHeader("//*[contains(@text,'Fast cash or easy payment')]","//*[contains(@value,'Fast cash or easy payment')]","xpath","xpath"),
    objLoansTile("//*[contains(@text,'Loans')]","//*[contains(@value,'Loans')]","xpath","xpath"),
    objFindTheRightLoan("//*[contains(@text,'Find the right loan')]","//*[contains(@value,'Find the right loan')]","xpath","xpath"),
    objProfileName("//*[@resource-id='appbar-content-title-text']","appbar-content-title-text","xpath","id"),
    objLoansHeader("//*[contains(@text,'Loans')]","//*[contains(@value,'Loans')]","xpath","xpath"),
    objLoansTileSubText1("//*[contains(@text,'Loans')]/following-sibling::android.widget.TextView[1]","//*[contains(@value,'Loans')]/../following-sibling::XCUIElementTypeOther/XCUIElementTypeStaticText[1]","xpath","xpath"),
    objLoansTileSubText2("//*[contains(@text,'Loans')]/following-sibling::android.widget.TextView[2]","//*[contains(@value,'Loans')]/../following-sibling::XCUIElementTypeOther/XCUIElementTypeStaticText[2]","xpath","xpath"),
    objScanTheQRCodePageHeader("//*[contains(@text,'Scan the QR')]","//*[contains(@value,'Scan the QR')]","xpath","xpath"),

    objQRHuntPageHeader("//*[@text='QR Hunt!']","//*[@value='QR Hunt!']","xpath","xpath"),
    objQRHuntPageSubText1("//*[@text='QR Hunt!']/following-sibling::android.widget.TextView[1]","//*[@value='QR Hunt!']/following-sibling::XCUIElementTypeStaticText[1]","xpath","xpath"),
    objQRHuntPageSubText2("//*[@text='QR Hunt!']/following-sibling::android.widget.TextView[2]","//*[@value='QR Hunt!']/following-sibling::XCUIElementTypeStaticText[2]","xpath","xpath"),
    objApplyForQuickLoanButton("//*[@text='Apply for a Quick Loan']","//*[@value='Apply for a Quick Loan']","xpath","xpath"),
    objBackToQRScanningButton("//*[@text='Back to QR Scanning']","//*[@value='Back to QR Scanning']","xpath","xpath"),
    objContactUsButton("//*[@resource-id='com.tonik.mobile:id/Header_right_click']/android.view.ViewGroup","//*[@name='com.tonik.mobile:id/Header_right_click']/XCUIElementTypeOther","xpath","xpath"),
    objScanTheQRCodePageSubTitle("//*[contains(@text,'Scan the QR')]/following-sibling::android.widget.TextView","//*[contains(@value,'Scan the QR')]/following-sibling::XCUIElementTypeStaticText","xpath","xpath"),
    objIDontSeeAnyTonikAgentButton("//*[contains(@text,'see any Tonik agent at the store')]","//*[contains(@value,'see any Tonik agent at the store')]","xpath","xpath"),
    objGotchaButton("//*[contains(@text,'Gotcha!')]","//*[contains(@value,'Gotcha!')]","xpath","xpath"),
    objTonikUrlTab("com.android.chrome:id/url_bar","com.android.chrome:id/url_bar","id","id"),
    objMakeSureTheQrText("//*[contains(@text,'QR code is within the frame')]","//*[contains(@value,'QR code is within the frame')]","xpath","xpath"),
    objScanQrFrame("//*[contains(@text,'QR code is within the frame')]/preceding-sibling::android.view.ViewGroup","//*[contains(@value,'QR code is within the frame')]/preceding-sibling::XCUIElementTypeOther","xpath","xpath"),
    objBackArrowButton("//*[@resource-id='com.tonik.mobile:id/Button_click']/android.view.ViewGroup","com.tonik.mobile:id/Button_click","xpath","id"),
    objLineOfWork("//*[contains(@text,'line of work?')]","//*[contains(@value,'line of work?')]","xpath","xpath"),
    objTimeToClaimYourItemPageHeader("//*[contains(@text,'Time to claim your item')]","//*[contains(@value,'Time to claim your item')]","xpath","xpath"),
    objTimeToClaimPageSubTitle("//*[contains(@text,' read the reminders below.')]","//*[contains(@value,' read the reminders below.')]","xpath","xpath"),
    objReminders("//*[contains(@text,'Reminders')]","//*[contains(@value,'Reminders')]","xpath","xpath"),
    objReminder1("//*[contains(@text,'Reminders')]/following-sibling::android.view.ViewGroup[1]/descendant::android.widget.TextView","//*[contains(@value,'Reminders')]/../following-sibling::XCUIElementTypeOther/XCUIElementTypeOther[1]/descendant::XCUIElementTypeStaticText","xpath","xpath"),
    objReminder2("//*[contains(@text,'Reminders')]/following-sibling::android.view.ViewGroup[2]/descendant::android.widget.TextView","//*[contains(@value,'Reminders')]/../following-sibling::XCUIElementTypeOther/XCUIElementTypeOther[2]/descendant::XCUIElementTypeStaticText","xpath","xpath"),
    objReminder3("//*[contains(@text,'Reminders')]/following-sibling::android.view.ViewGroup[3]/descendant::android.widget.TextView","//*[contains(@value,'Reminders')]/../following-sibling::XCUIElementTypeOther/XCUIElementTypeOther[3]/descendant::XCUIElementTypeStaticText","xpath","xpath"),
    objSummary("//*[contains(@text,'Summary')]","//*[contains(@value,'Summary')]","xpath","xpath"),
    objProductCategory("//*[contains(@text,'Product category')]","//*[contains(@value,'Product category')]","xpath","xpath"),
    objProductCategoryValue("//*[contains(@text,'Product category')]/following-sibling::android.widget.TextView","//*[contains(@value,'Product category')]/following-sibling::XCUIElementTypeStaticText","xpath","xpath"),
    objFinancedAmountText("//*[contains(@text,'Financed amount')]","//*[contains(@value,'Financed amount')]","xpath","xpath"),
    objFinancedAmountValue("//*[contains(@text,'Financed amount')]/following-sibling::android.widget.TextView[1]","//*[contains(@value,'Financed amount')]/following-sibling::XCUIElementTypeStaticText[1]","xpath","xpath"),
    objFinanceAmountDescription("//*[contains(@text,'Financed amount')]/following-sibling::android.widget.TextView[2]","//*[contains(@value,'Financed amount')]/following-sibling::XCUIElementTypeStaticText[2]","xpath","xpath"),
    objMonthlyInstllmentText("//*[contains(@text,'Your monthly installment')]","//*[contains(@value,'Your monthly installment')]","xpath","xpath"),
    objMonthlyInstllmentVlue("//*[contains(@text,'Your monthly installment')]/following-sibling::android.widget.TextView[1]","//*[contains(@value,'Your monthly installment')]/following-sibling::XCUIElementTypeStaticText[1]","xpath","xpath"),
    objMonthlyInstllmentDescription("//*[contains(@text,'Your monthly installment')]/following-sibling::android.widget.TextView[2]","//*[contains(@value,'Your monthly installment')]/following-sibling::XCUIElementTypeStaticText[2]","xpath","xpath"),
    objReadyToPayButtonText("//*[contains(@text,'ready to pay')]","//*[contains(@value,'ready to pay')]","xpath","xpath"),
    objAlreadyAtTheCahierPageHeader("//*[contains(@text,'cashier, luv?')]","//*[contains(@value,'cashier, luv?')]","xpath","xpath"),
    objTapSendConfirmationCode("//*[contains(@text,'Tap ‘Send confirmation code’')]","//*[contains(@value,'Tap ‘Send confirmation code’')]","xpath","xpath"),
    objWillReceiveCode("//*[contains(@text,'receive a 6-digit code to')]","//*[contains(@value,'receive a 6-digit code to')]","xpath","xpath"),
    objPrepareYourPayment("//*[contains(@text,'Prepare your payment')]","//*[contains(@value,'Prepare your payment')]","xpath","xpath"),
    objSettleTheAmount("//*[contains(@text,'Settle the amount')]","//*[contains(@value,'Settle the amount')]","xpath","xpath"),
    objEnterTheCodeOnTheNextStep("//*[contains(@text,'Enter the code on the next step')]","//*[contains(@value,'Enter the code on the next step')]","xpath","xpath"),
    objAskFor6digitOtp("//*[contains(@text,'Ask for this 6-digit code')]","//*[contains(@value,'Ask for this 6-digit code')]","xpath","xpath"),
    objSendConfirmationCodeButtonText("(//*[contains(@text,'Send confirmation code')])[2]","(//*[contains(@value,'Send confirmation code')])[2]","xpath","xpath"),
    objPayTogetYourConfirmationCodePageHeader("//*[contains(@text,'Pay to get your confirmation code from the cashier')]","//*[contains(@value,'Pay to get your confirmation code from the cashier')]","xpath","xpath"),
    objSendConfirmationCodePageSubTitle("com.tonik.mobile:id/Sub_title_txt","com.tonik.mobile:id/Sub_title_txt","id","id"),
    objResendConfirmationCodeLinkText("//*[contains(@text,'Resend confirmation code')]","//*[contains(@value,'Resend confirmation code')]","xpath","xpath"),
    objTopLeftSideBackArrowButton("//*[@resource-id='com.tonik.mobile:id/Header_left_click']/android.view.ViewGroup","//*[@name='com.tonik.mobile:id/Header_left_click']/XCUIElementTypeOther","xpath","xpath"),
    objBackButtonInAlreadyAtCashierPage("//*[contains(@text,'cashier, luv?')]/preceding-sibling::android.view.ViewGroup","//*[contains(@value,'cashier, luv?')]/../preceding-sibling::XCUIElementTypeOther/XCUIElementTypeOther","xpath","xpath"),
    objMeetTheCashierTileText("//*[contains(@text,'Meet the cashier')]","//*[contains(@value,'Meet the cashier')]","xpath","xpath"),
    objTimeToClaimTileText("//*[contains(@text,'time to claim your item')]","//*[contains(@value,'time to claim your item')]","xpath","xpath"),
    objShopInstallmentLoan("//*[contains(@text,'Shop installment loan')]","//*[contains(@value,'Shop installment loan')]","xpath","xpath"),
    objThankYouForPurchase("//*[contains(@text,'Thank you for your purchase!')]","//*[contains(@value,'Thank you for your purchase!')]","xpath","xpath"),
    objPurchaseAmount("//*[contains(@text,'Thank you for your purchase!')]/following-sibling::android.widget.TextView[1]","//*[contains(@value,'Thank you for your purchase!')]/following-sibling::XCUIElementTypeStaticText[1]","xpath","xpath"),
    objPurchaseDate("//*[contains(@text,'Thank you for your purchase!')]/following-sibling::android.widget.TextView[2]","//*[contains(@value,'Thank you for your purchase!')]/following-sibling::XCUIElementTypeStaticText[2]","xpath","xpath"),
    objStoreText("//*[contains(@text,'Store')]","//*[contains(@value,'Store')]","xpath","xpath"),
    
    objBackToDashboard("//*[contains(@text,'Back to Dashboard')]","//*[contains(@value,'Back to Dashboard')]","xpath","xpath"),
    objCloseApplicationButton("//*[@resource-id='com.tonik.mobile:id/Header_right_click']/android.view.ViewGroup","//*[@name='com.tonik.mobile:id/Header_right_click']/XCUIElementTypeOther","xpath","xpath"),
    objLeavingSoSoonPageHeader("//*[contains(@text,'Leaving so soon, luv?')]","//*[contains(@value,'Leaving so soon, luv?')]","xpath","xpath"),
    objLeavingSoonPageDescription("//*[contains(@text,'Sad to see you go!')]","//*[contains(@value,'Sad to see you go!')]","xpath","xpath"),
    objNextButton("com.tonik.mobile:id/Button_click","com.tonik.mobile:id/Button_click","id","id"),
    objGoodByeSeeyouLater("//*[contains(@text,'goodbye, only see you later')]","//*[contains(@value,'goodbye, only see you later')]","xpath","xpath"),
    objGoodByeSeeYouLaterPageDescription("//*[contains(@text,' Feel free to apply for another loan in the future.')]","//*[contains(@value,' Feel free to apply for another loan in the future.')]","xpath","xpath"),
    objNextBtn("//*[@text='Next']","//*[@value='Next']","xpath","xpath"),
    objOkBtn("//*[@resource-id='com.tonik.mobile:id/Popup_single_btn_click']","com.tonik.mobile:id/Popup_single_btn_click","xpath","id"),
    objdoneButton("Done","Done","id","id"),
    objYouGotApprovedPageHeader("//*[contains(@text,'You got approved')]","//*[contains(@value,'You got approved')]","xpath","xpath"),
    objTheOfferIsValidFor("//*[contains(@text,'The offer is valid for')]","//*[contains(@value,'The offer is valid for')]","xpath","xpath"),
    objApprovedAmountText("//*[@resource-id='Approved_head_txt']","Approved_head_txt","xpath","id"),
    objApprovedAmount("//*[@resource-id='Approved_amt_txt']","Approved_amt_txt","xpath","id"),
    objConfirmAppliedInstallmentTermText("//*[contains(@text,'Confirm applied installment term')]","//*[contains(@value,'Confirm applied installment term')]","xpath","xpath"),
    objMonthlyAmount("com.tonik.mobile:id/Tenure_amount0","com.tonik.mobile:id/Tenure_amount0","id","id"),
    objMonths("com.tonik.mobile:id/Tenure_month0","com.tonik.mobile:id/Tenure_month0","id","id"),
    objIAcceptTheOffer("//*[contains(@text,'I accept the offer')]","//*[contains(@value,'I accept the offer')]","xpath","xpath"),
    objPageHeader("com.tonik.mobile:id/Main_title_txt","com.tonik.mobile:id/Main_title_txt","id","id"),
    objPageSubTitle("com.tonik.mobile:id/Sub_title_txt","com.tonik.mobile:id/Sub_title_txt","id","id"),
    objLoanProductText("//*[contains(@resource-id,'Loan product0')]","//*[contains(@name,'Loan product0')]","xpath","xpath"),
    objLoanProductName("//*[contains(@resource-id,'Loan product0')]/following-sibling::android.widget.TextView","//*[contains(@name,'Loan product0')]/../following-sibling::XCUIElementTypeOther/XCUIElementTypeStaticText","xpath","xpath"),
    objInstallmentPeriodText("//*[contains(@text,'Installment period')]","//*[contains(@value,'Installment period')]","xpath","xpath"),
    objInstallmentPeriod("//*[contains(@text,'Installment period')]/following-sibling::android.widget.TextView","//*[contains(@name,'Installment period')]/../following-sibling::XCUIElementTypeOther/XCUIElementTypeStaticText","xpath","xpath"),
    objPayHingaFeeText("//*[@resource-id='com.tonik.mobile:id/PayHinga fee2']","com.tonik.mobile:id/PayHinga fee2","xpath","id"),
    objPayHingaFeeValue("//*[@resource-id='com.tonik.mobile:id/PayHinga feeval2']","com.tonik.mobile:id/PayHinga feeval2","xpath","id"),
    objMonthlyAddOnText("//*[contains(@text,'Monthly add-on rate')]","//*[contains(@value,'Monthly add-on rate')]","xpath","xpath"),
    objMonthlyAddOnDescription("//*[contains(@text,'Monthly add-on rate')]/following-sibling::android.widget.TextView","//*[contains(@value,'Monthly add-on rate')]/../following-sibling::XCUIElementTypeOther","xpath","xpath"),
    objTotalMonthlyInstallmentText("//*[contains(@text,'Total')]","//*[contains(@value,'Total')]","xpath", "xpath"),
    objTotalMonthlyInstallmentAmount("//*[contains(@text,'Total')]/following-sibling::android.widget.TextView","//*[contains(@value,'Total')]/following-sibling::XCUIElementTypeStaticText","xpath","xpath"),
    objConfirmButton("//*[contains(@text,'Confirm')]","//*[contains(@value,'Confirm')]","xpath","xpath"),
    objDueDateText("//*[contains(@text,'Due date')]","//*[contains(@value,'Due date')]","xpath","xpath"),
    objDueDate("//*[contains(@text,'Due date')]/following-sibling::android.widget.TextView","//*[contains(@value,'Due date')]/../following-sibling::XCUIElementTypeOther/XCUIElementTypeStaticText","xpath","xpath"),
    objConfirmationCodeTextField("//*[contains(@text,'Resend confirmation code')]/../preceding-sibling::android.view.ViewGroup[2]","//*[contains(@value,'Resend confirmation code')]/../../../XCUIElementTypeOther[1]","xpath","xpath"),
    objNextInstallmentText("com.tonik.mobile:id/message","com.tonik.mobile:id/message","id","id"),
    objLoanInstallmentAmount("com.tonik.mobile:id/AmountTitle","com.tonik.mobile:id/AmountTitle","id","id"),
    objPayHingaText("com.tonik.mobile:id/Payhinga_Title0","com.tonik.mobile:id/Payhinga_Title0","id","id"),
    objPayHingaSubTitle("com.tonik.mobile:id/Payhinga_SubTitle0","com.tonik.mobile:id/Payhinga_SubTitle0","id","id"),
    objLifeInsuranceText("//*[contains(@text,'Life insurance')]","//*[contains(@value,'Life insurance')]","xpath","xpath"),
    objProofofCoverSheet("Proof of Cover ","Proof of Cover ","name","name"),
    objIicon("//*[contains(@resource-id,'com.tonik.mobile:id/message')]/following-sibling::android.view.ViewGroup","//*[contains(@name,'com.tonik.mobile:id/message')]/../following-sibling::XCUIElementTypeOther","xpath","xpath"),
    objLoanDashBoardDescription("com.tonik.mobile:id/SubMessage","com.tonik.mobile:id/SubMessage","id","id"),
    objPaymentRecordedText("com.tonik.mobile:id/History_text","com.tonik.mobile:id/History_text","id","id"),
    objPaymentRecorded("//*[contains(@text,'Payment record')]/following-sibling::android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]","//*[contains(@value,'Payment record')]/following-sibling::XCUIElementTypeOther","xpath","xpath"),
    objCloseMyLoan("//*[@text='I want to close my loan']","//*[@value='I want to close my loan']","xpath","xpath"),
    objPaymentDoneText("com.tonik.mobile:id/PaymentDone","com.tonik.mobile:id/PaymentDone","id","id"),
    objDownloadButton("com.tonik.mobile:id/Download_btn_txt","com.tonik.mobile:id/Download_btn_txt","id","id"),
    objGetInTouchWithCustomerCarePage("//*[contains(@text,'Get in touch')]","//*[contains(@name,'Get in touch')]","xpath","xpath"),
    objSearchedReferenceNumber("(//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView)[1]","//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[1]/descendant::XCUIElementTypeStaticText[2]","xpath","xpath"),
    objPinkySwearButton("//*[contains(@text,'pinky swear')]","//*[contains(@value,'pinky swear')]","xpath","xpath"),
    objSendVerificationButton("com.tonik.mobile:id/Popup_positive_btn_txt","com.tonik.mobile:id/Popup_positive_btn_txt","id","id"),
    objUpdateEmailAddressLink("com.tonik.mobile:id/Popup_negative_btn_txt","com.tonik.mobile:id/Popup_negative_btn_txt","id","id"),
    objOtpPageDescription("com.tonik.mobile:id/Sub_title_view","com.tonik.mobile:id/Sub_title_view","id","id"),
    objCodeExpiringInSeconds("//*[contains(@text,'Code expiring in')]","//*[contains(@value,'Code expiring in')]","xpath","xpath"),
    objNooicePageHeader ("//*[contains(@text,'Nooice!')]","//*[contains(@value,'Nooice!')]","xpath","xpath"),
    objNooicePageDescription ("//*[contains(@text,'Nooice!')]/following-sibling::android.widget.TextView","//*[contains(@value,'Nooice!')]/following-sibling::XCUIElementTypeStaticText[2]","xpath","xpath"),
    objOtpTextField("//*[contains(@text,'Code expiring')]/preceding-sibling::android.view.ViewGroup[2]","//*[contains(@value,'Code expiring')]/../../preceding-sibling::XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]","xpath","xpath"),
    objCloseCrossButton("Close","Close","name","name"),
    objGotitButton("(//*[@resource-id='com.tonik.mobile:id/Got it'])[2]","(//*[@name='com.tonik.mobile:id/Got it'])[2]","xpath","xpath"),
    objTopUpMyAccountButton("//*[contains(@text,'Top up my Tonik account')]","//*[contains(@value,'Top up my Tonik account')]","xpath","xpath"),
    objCloseMyLoanButton("//*[contains(@text,'Close my loan')]","//*[contains(@value,'Close my loan')]","xpath","xpath"),
    objDropTheMic("//*[contains(@text,'Drop the mic!')]","//*[contains(@value,'Drop the mic!')]","xpath","xpath"),
    objFullyPaidText("//*[contains(@text,'Your loan is finally fully paid')]","//*[contains(@value,'Your loan is finally fully paid')]","xpath","xpath"),
    objFullRepaymentAmountText("//*[contains(@text,'Full repayment amount')]","//*[contains(@value,'Full repayment amount')]","xpath","xpath"),
    objFullRepaymentAmountValue("//*[contains(@text,'Full repayment amount')]/following-sibling::android.widget.TextView[1]","//*[contains(@value,'Full repayment amount')]/following-sibling::XCUIElementTypeStaticText[1]","xpath", "xpath"),
    objFullRepaymentAmountDescription("//*[contains(@text,'Full repayment amount')]/following-sibling::android.widget.TextView[2]","//*[contains(@value,'Full repayment amount')]//following-sibling::XCUIElementTypeStaticText[2]","xpath","xpath"),
    objBreakdownText("//*[contains(@text,'Breakdown ')]","//*[contains(@value,'Breakdown ')]","xpath","xpath"),
    objNotEnoughBalance("//*[contains(@text,'Not enough balance')]","//*[contains(@value,'Not enough balance')]","xpath","xpath"),
    objNotEnoughBalanceDescription("//*[contains(@text,'Not enough balance')]/following-sibling::android.widget.TextView","//*[contains(@value,'Not enough balance')]/following-sibling::XCUIElementTypeStaticText","xpath","xpath"),
    objTSAAmount ("//*[@text='Your tonik account']/following-sibling::android.widget.TextView[1]","(//*[@value='Your tonik account']/following-sibling::*)[1]","xpath","xpath"),
    objAllFieldsInWhereYourCurrentAddress("android.widget.EditText","XCUIElementTypeStaticText","className","className"),
    objHowToFindYourZipCodeLinkText("//*[@text='How to find your zip code?']","//*[@value='How to find your zip code?']","xpath","xpath"),
    objUrl("com.sec.android.app.sbrowser:id/url_bar_container","TabBarItemTitle","id","id"),
    objEffectiveInterestRate ("//*[contains(@text,'Effective interest rate')]", "com.tonik.mobile:id/Effective interest rate", "xpath", "id"),
    objLateFeeTxt ("//*[contains(@resource-id,'com.tonik.mobile:id/Late')]", "//*[contains(@name,'com.tonik.mobile:id/Late')]", "xpath", "xpath"),
    objLateFeeAmount ("//*[contains(@resource-id,'com.tonik.mobile:id/Late')]/following-sibling::android.widget.TextView", "//*[contains(@name,'com.tonik.mobile:id/Late')]/following-sibling::XCUIElementTypeStaticText", "xpath", "xpath"),
    objZipCodeInputField ("//*[@text='Zipcode']/parent::*/child::android.widget.EditText", "(//*[@label='Zipcode'])[1]/XCUIElementTypeOther/XCUIElementTypeTextField", "xpath", "xpath"),
    objYourLoanAccountText("//*[@text='Your Loan Account']","//*[@value='Your Loan Account']","xpath","xpath"),
    objWhatYouWant("//*[contains(@text,'What you want to buy')]","//*[contains(@value,'What you want to buy')]","xpath","xpath"),
    objMobilePhones("//*[contains(@text,'Mobile Phones')]","//*[contains(@value,'Mobile Phones')]","xpath","xpath"),
    objSweetAcceptButton("//*[contains(@text,'Sweet! I accept')]","//*[contains(@value,'Sweet! I accept')]","xpath","xpath"),
    objVerifyMyIdButton("//*[contains(@text,'verify my ID')]","//*[contains(@value,'verify my ID')]","xpath","xpath"),
    objverifyMyIdPopupDescription("//*[contains(@text,'verify your account with a valid ID.')]","//*[contains(@value,'verify your account with a valid ID.')]","xpath","xpath"),
    objTakeFaceIdentity("//*[contains(@text,'Take a Face Identity Scan')]","//*[contains(@value,'Take a Face Identity Scan')]","xpath","xpath"),
    objScanOneValid("//*[contains(@text,'Scan one Valid ID you own.')]","//*[contains(@value,'Scan one Valid ID you own.')]","xpath","xpath"),
    objContinueButton("//*[contains(@text,'Continue')]","//*[contains(@value,'Continue')]","xpath","xpath");

















    private String android;
    private String ios;
    private String andPathType;
    private String iosPathType;

    ShopInstallmentLoanPage(String android, String ios, String andPathType, String iosPathType) {
        this.android = android;
        this.ios = ios;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }
    ShopInstallmentLoanPage(String android, String andPathType, String iosPathType) {
        this.android = android;
        this.ios = android;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }
    ShopInstallmentLoanPage(String android, String andPathType) {
        this.android = android;
        this.ios = android;
        this.andPathType = andPathType;
        this.iosPathType = andPathType;
    }

    public static By getByOSType(String osType, ShopInstallmentLoanPage objectName) {
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
        else if (osType.equalsIgnoreCase("android") && objectName.iosPathType.equalsIgnoreCase("className"))
        {
            return By.className(objectName.android);
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

    public static By objLineOfWorksList(String platform,String lineOfWork) {
        if(platform.equalsIgnoreCase("Android")) {
            return By.xpath("//*[contains(@text,'" + lineOfWork + "')]");
        }
        else {
            return By.xpath("//*[contains(@value,'" + lineOfWork + "')]");
        }
    }
    public static By objLeavingSoonReasonList(String platform,String reason){
        if(platform.equalsIgnoreCase("Android")) {
            return By.xpath("//*[contains(@text,'"+reason+"')]");
        }
        else {
            return By.xpath("//*[contains(@value,'"+reason+"')]");
        }
    }
    public static By objPurchaseDetails(String platform,String detail){
        if(platform.equalsIgnoreCase("Android")) {
            return By.xpath("//*[contains(@text,'"+detail+"')]");
        }
        else {
            return By.xpath("//*[contains(@value,'"+detail+"')]");
        }

    }
    public static By objPurchaseDetailsValue(String platform,String detail){
        if(platform.equalsIgnoreCase("Android")) {
            return By.xpath("//*[contains(@text,'"+detail+"')]/following-sibling::android.widget.TextView[1]");
        }
        else {
            return By.xpath("//*[contains(@value,'"+detail+"')]/following-sibling::XCUIElementTypeStaticText[1]");
        }
    }
    public static By objLoanDetails(String platform,String detail){
        if(platform.equalsIgnoreCase("Android")) {
            return By.xpath("//*[contains(@text,'"+detail+"')]");
        }
        else {
            return By.xpath("//*[contains(@value,'"+detail+"')]");
        }

    }
    public static By objLoanDetailValue(String platform,String detail){
        if(platform.equalsIgnoreCase("Android")) {
            return By.xpath("//*[contains(@text,'"+detail+"')]/../following-sibling::android.widget.TextView[1]");
        }
        else {
            return By.xpath("//*[contains(@value,'"+detail+"')]/../following-sibling::XCUIElementTypeOther/XCUIElementTypeStaticText");
        }
    }
    public static By objSelectSubIndustry(String platform, String subIndustry) {
        if(platform.equalsIgnoreCase("android")){
            return By.xpath("//*[@resource-id='com.tonik.mobile:id/"+subIndustry+"']");
        } else {
            return By.xpath("(//XCUIElementTypeStaticText[@name='com.tonik.mobile:id/"+subIndustry+"'])[2]");
        }
    }













}
