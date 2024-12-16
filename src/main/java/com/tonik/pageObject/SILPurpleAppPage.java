package com.tonik.pageObject;
import org.openqa.selenium.By;
public class SILPurpleAppPage {
    private SILPurpleAppPage() {
    }
    //  Welcome screen
    public static By objWelcomeScreen = By.xpath("//p[contains(text(),'Welcome back!')]");
    public static By objPurpleApp = By.xpath("//span[contains(text(),'Purple App')]");
    public static By objLoginScreenSubHeader = By.xpath("//span[@class='sub-title text-secondary']");
    public static By objUserNameInputField = By.name("username");
    public static By objPasswordInputField = By.name("password");
    public static By objForgotPasswordLink = By.xpath("//a[contains(text(),'Forgot Password?')]");
    public static By objLoginBtn = By.xpath("//button[@type='submit']");
    public static By objPasswordEyeIcon = By.xpath("//*[@class='fa fa-eye-slash']");
    public static By objMerchantStore = By.xpath("//h2[contains(text(),'Merchant Store')]");
    public static By objSelectMerchantStore = By.xpath("//select[@class='form-control']");
    public static By objNextBtn = By.xpath("//button[contains(text(),'Next')]");
    public static By objInvalidCredentialErrorMsg = By.xpath("//*[contains(text(),'Please enter a valid username and password')]");
    public static By objSelectMerchantList = By.xpath("//*[@class=\"form-control\"]/child::option");
    public static By objRateChartOptions(int percentage, int amount) {
        return By.xpath("((//*[@class=\"d-flex flex-column justify-content-between\"])[" + percentage + "]/child::span)[" + amount + "]");
    }
    public static By objRateChartOptions = By.xpath("//*[@class=\"d-flex flex-column justify-content-between\"]");
    public static By objSelectMerchantList(int list) {
        return By.xpath("(//*[@class=\"form-control\"]/child::option)[" + list + "]");
    }
    public static By objAddOnRatePercentage = By.xpath("//*[@class=\"d-flex flex-column justify-content-between\"]/child::span[contains(text(),'Add-on rate monthly')]/preceding-sibling::*");
    public static By objAddOnRatePercentage(int percentage) {
        return By.xpath("(//*[@class=\"d-flex flex-column justify-content-between\"]/child::span[contains(text(),'Add-on rate monthly')]/preceding-sibling::*)[" + percentage + "]");
    }
    public static By objDownPaymentPercentage(int downPaymentPercentage) {
        return By.xpath("(//*[@class=\"d-flex flex-column justify-content-between\"]/child::span[contains(text(),'Min')])[" + downPaymentPercentage + "]");
    }
    public static By objRateChartList = By.xpath("//*[@class=\"payment-tile d-flex flex-row justify-content-between\"]");
    public static By objQRGeneratorScreen = By.xpath("//*[contains(text(),'QR Generator')]");
    public static By objProfileImage = By.xpath("//img[@alt='profile photo']");
    public static By objUserName = By.xpath("//div[@class='user-name py-2 font-weight-bold']");
    public static By objMerchantName = By.xpath("//div[@class='merchant-name py-2 font-weight-bold']");
    public static By objQRCodeLink = By.xpath("//li[@class='nav-link qr qc ']");
    public static By objLogOut = By.xpath("//li[@class='nav-link qr lo ']");
    public static By objAddToCartHeader = By.xpath("//div[contains(text(),'Add to cart')]");
    public static By objAddToCartSubHeader = By.xpath("//div[contains(text(),'Add to cart')]/following-sibling::div[@class='sub-title']");
    public static By objItemNumber = By.xpath("//*[@class='text-dark py-4']");
    public static By objCategoryField(String info) {
        return By.xpath("(//*[@name='categoryName'])[" + info + "]");
    }
    public static By objCategoryOptions = By.xpath("//*[@name ='categoryName']");
    public static By objCategoryOptions(String info) {
        return By.xpath("(//*[@name ='categoryName'])[" + info + "]");
    }
    public static By objBrand = By.xpath("//*[@name='brand']");
    public static By objBrand(String item) {
        return By.xpath("(//*[@name='brand'])[" + item + "]");
    }
    public static By objSKU = By.xpath("//*[@name='skuNo']");
    public static By objSKU(String item) {
        return By.xpath("(//*[@name='skuNo'])[" + item + "]");
    }
    public static By objPrice = By.id("amount");
    public static By objPrice(String item) {
        return By.xpath("(//*[@id='amount'])[" + item + "]");
    }
    public static By objAddMoreItem = By.xpath("//*[contains(text(),'Add more item')]");
    public static By objLoanSummaryDetailsHeader = By.xpath("//*[contains(text(),'Loan Summary Details')]");
    public static By objTotalAmount = By.xpath("//*[@id='totalamount']/child::span");
    public static By objTotalAmountTxt = By.xpath("//*[contains(text(),'Total price')]");
    public static By objActualDownPaymentTxt = By.xpath("//*[contains(text(),'Actual Down payment')]");
    public static By objActualDownPayment = By.xpath("//*[@class='amount d-flex flex-row justify-content-between']/child::span[contains(text(),'₱')]");
    public static By objLoanableAmountTxt = By.xpath("//*[contains(text(),'Loanable amount')]");
    public static By objLoanableAmount = By.xpath("//*[contains(text(),'Loanable amount')]/parent::*/preceding-sibling::div/child::*");
    public static By objInstallmentTerms = By.xpath("//*[contains(text(),'Installment terms')]");
    public static By objInstallmentTermsSubtitleOne = By.xpath("(//*[contains(text(),'Installment terms')]/following-sibling::div)[1]");
    public static By objInstallmentTermsSubtitleTwo = By.xpath("//*[contains(text(),'Paano gumagana')]");
    public static By objGenerateQRCode = By.xpath("//*[@class='btn btn-dark']");
    public static By objChangeAmount = By.xpath("//button[contains(text(),'Change amount')]");
    public static By objRateChart = By.xpath("//*[contains(text(),'Rate Chart')]");
    public static By objRateChartItem = By.xpath("//*[@class='payment-tile d-flex flex-row justify-content-between']");
    public static By objAddOnRate = By.xpath("//*[contains(text(),'Add-on rate')]");
    public static By objRateCharts = By.xpath("//*[contains(text(),'Rate Chart')]/following-sibling::span");
    public static By objDownPaymentAmount(int downPayment) {
        return By.xpath("(//*[@class=\"d-flex flex-column justify-content-between\"]/child::span[contains(text(),'₱')])[" + downPayment + "]");
    }
    public static By objWithOutPayHingaMonths = By.xpath("(//*[@class='d-flex '])[1]/child::div/div/div/div[@class='tenure']/span");
    public static By objWithOutPayHingaMonths(int month) {
        return By.xpath("((//*[@class='d-flex '])[1]/child::div)[" + month + "]/div/div/div[@class='tenure']/span");
    }
    public static By objWithPayHingaMonths = By.xpath("(//*[@class='d-flex '])[2]/child::div/div/div/div[@class='tenure']/span");
    public static By objWithPayHingaMonths(int month) {
        return By.xpath("((//*[@class='d-flex '])[2]/child::div)[" + month + "]/div/div/div[@class='tenure']/span");
    }
    public static By objSelectWithOutVASEMIMonth(String month){
        return By.xpath("(//*[@class='d-flex '])[1]/div/div/div/div[@class='tenure']/*[contains(text(),'"+month+"')]");
    }
    public static By objSelectWithVASEMIMonth(String month){
        return By.xpath("(//*[@class='d-flex '])[2]/div/div/div/div[@class='tenure']/*[contains(text(),'"+month+"')]");
    }
    public static By objGetWithOutVASEMIAmount(String month){
        return By.xpath("(//*[@class='d-flex '])[1]/div/div/div/div[@class='tenure']/*[contains(text(),'"+month+"')]/parent::*/following-sibling::div[@class='emi-amount']/span");
    }
    public static By objGetWithVASEMIAmount(String month){
        return By.xpath("(//*[@class='d-flex '])[2]/div/div/div/div[@class='tenure']/*[contains(text(),'"+month+"')]/parent::*/following-sibling::div[@class='emi-amount']/span");
    }
    public static By objWithOutPayHingaEMI(int month) {
        return By.xpath("((//*[@class='d-flex '])[1]/child::div)[" + month + "]/div/div/div[@class='emi-amount']/span");
    }
    public static By objWithPayHingaEMI(int month) {
        return By.xpath("((//*[@class='d-flex '])[2]/child::div)[" + month + "]/div/div/div[@class='emi-amount']/span");
    }
    public static By objDownPaymentPopup = By.xpath("//*[@class='modal-title h4']");
    public static By objDownPaymentInputField = By.id("down-pay-amount");
    public static By objDownPaymentInfo = By.xpath("//*[contains(text(),'The bigger DP, the lower the add-on rate')]");
    public static By objApplyNowBtn = By.id("apply-now");
    public static By objCloseIcon = By.xpath("//*[@class='btn-close']");
    public static By objWhyShouldIGetItInfo(int info) {
        return By.xpath("(//*[@class='section-3']/div/div/div/p)[" + info + "]");
    }
    public static By objWhyShouldIGetItInfo = By.xpath("//*[@class='section-3']/div/div/div/p");
    public static By objWhyShouldIGetItHeader = By.xpath("//*[@class='text-center text-heading-3']");
    public static By objTonikLogo = By.xpath("//*[@class='tonik-logo']");
    public static By objAddOnServiceHeader = By.xpath("//*[@class='text-center text-color text-heading-1 display-5 lh-sm']");
    public static By objGuideHeader = By.xpath("//*[@class='text-center text-heading-2 lh-sm']");
    public static By objWhoCanGetItInfo = By.xpath("//*[@class='side-icons fs-6']/child::li/p");
    public static By objWhoCanGetItInfo(int info) {
        return By.xpath("(//*[@class='side-icons fs-6']/child::li/p)[" + info + "]");
    }
    public static By objWhoCanGetItSubHeader = By.xpath("//*[@class='text-center text-color text-heading-5']");
    public static By objInsuranceInfo = By.xpath("//*[@class='text-center text-heading-5 m-eligib-criteria']");
    public static By objWhoCanGetItHeader = By.xpath("//*[@class='text-center text-heading-3 display-5']");
    public static By objWhoCanNotGetItInfo = By.xpath("//*[@class='side-icons']/child::li/p");
    public static By objWhoCanNotGetItInfo(int info){
        return  By.xpath("(//*[@class='side-icons']/child::li/p)["+info+"]");
    }
    public static By objRemainder = By.xpath("//*[@class='text-center reminder']");
    public static By objLimitation = By.xpath("//*[@class='sub-list']/li");
    public static By objLimitation(int limitation) {
        return By.xpath("(//*[@class='sub-list']/li)["+limitation+"]");
    }
    public static By objWhoCanNotGetIt = By.xpath("//*[@class='text-center text-color text-heading-3']");
    public static By objPopupCloseBtn = By.xpath("//*[@type='button']");
    public static By objFewRulesPopup = By.xpath("//*[@class='modal-title h4']");
    public static By objFewRulesPopupSubHeader = By.xpath("//*[contains(text(),'By creating a loan offer...')]");
    public static By objGenuineIDCheckBox = By.xpath("(//*[@type='checkbox'])[1]");
    public static By objGenuineID = By.xpath("//*[contains(text(),'I confirm the ID presented is genuine.')]");
    public static By objPhysicalPresenceCheckBox = By.xpath("(//*[@type='checkbox'])[2]");
    public static By objPhysicalPresence = By.xpath("//*[contains(text(),'I confirm the client is physically present.')]");
    public static By objAssessmentCode0 = By.id("as0");
    public static By objAssessmentCode1 = By.id("as1");
    public static By objAssessmentCode2 = By.id("as2");
    public static By objAssessmentInfo = By.xpath("//*[contains(text(),'Falsifying information will result in disciplinary action.')]");
    public static By objProceedBtn = By.id("as_proceed");
    public static By objQRCode = By.className("QR_title");
    public static By objItems = By.xpath("//*[contains(text(),'Items')]/parent::*/following-sibling::*");
    public static By objQRCodeTotalPrice = By.xpath("//*[contains(text(),'Total price')]/parent::*/following-sibling::*");
    public static By objQRCodeDownPaymentAmount = By.xpath("//*[contains(text(),'Down payment')]/parent::*/following-sibling::*");
    public static By objQRCodeLoanableAmount = By.xpath("//*[contains(text(),'Loanable amount')]/parent::*/following-sibling::*");
    public static By objQRCodeTerms = By.xpath("//*[contains(text(),'Terms')]/parent::*/following-sibling::*");
    public static By objQRCodeMonthlyInstallment = By.xpath("//*[contains(text(),'Monthly installment')]/parent::*/following-sibling::*");
    public static By objQRCodeAddOnRate = By.xpath("//*[contains(text(),'Add-on rate')]/parent::*/following-sibling::*");
    public static By objQRCodeGenerated = By.className("QR-Code-Genarated1");
    public static By objMerchantNameInQRCodeScreen = By.xpath("//h5");
    public static By objScanMeNowTxt = By.xpath("//*[@class='Scan-QR text-primary']");
    public static By objWaitTxtSubHeader = By.xpath("(//*[@class='sub-Scan-QR text-primary']/p)[1]");
    public static By objDoNotKeepWaiting = By.xpath("(//*[@class='sub-Scan-QR text-primary']/p)[2]");
    public static By objDoneBtn = By.xpath("//*[@class='btn btn-dark']");
    public static By objGeneratedQRCodeInfo(int info) {
        return By.xpath("(//*[@class='QR_info']/div/div/label)["+info+"]");
    }
    public static By objQRCodeSubTitle = By.xpath("//*[@class=\"sub-title py-2\"]");
    public static By objAddOnRateModified = By.xpath("//*[contains(text(),'Add-on rate')]/parent::div/preceding-sibling::div/child::span");
    public static By objHamburgerMenu = By.xpath("//i[@class=\"fa fa-align-justify\"]");
}