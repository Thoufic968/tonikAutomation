package com.tonik.bizfunctions;
import com.tonik.pageObject.SILPurpleAppPage;
import com.tonik.utility.ExtentReporter;
import com.tonik.utility.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.tonik.utility.ExtentReporter.HeaderChildNode;
import static com.tonik.utility.Utilities.*;
public class SILPurpleAppModule extends BaseClass {
    public SILPurpleAppModule() {
        super();
    }

    /**
     * Common method to Login to Purple App
     * @param userName
     * @param password
     * @throws Exception
     */
    public static void purpleAppLogin(String userName, String password) throws Exception {
        waitForElementToBePresent(SILPurpleAppPage.objWelcomeScreen,20,"Welcome screen");
        assertionValidation(getText(SILPurpleAppPage.objPurpleApp),propertyFileReader("PurpleAppTxt","SILPurpleApp"),"text");
        type(SILPurpleAppPage.objUserNameInputField,userName,"User name input field");
        type(SILPurpleAppPage.objPasswordInputField,password,"Password input field");
        jsClick(SILPurpleAppPage.objLoginBtn,getTextVal(SILPurpleAppPage.objLoginBtn,"Button"));
    }
    /**
     * Common method to select merchant and click on next
     * @param value
     * @throws Exception
     */
    public void selectMerchantAndNext(String value) throws Exception {
        waitForElementToBePresent(SILPurpleAppPage.objMerchantStore,20,"Merchant store");
        verifyElementPresent(SILPurpleAppPage.objMerchantStore,getTextVal(SILPurpleAppPage.objMerchantStore,"Screen"));
        assertionValidation(getText(SILPurpleAppPage.objMerchantStore),propertyFileReader("MerchantStoreScreen","SILPurpleApp"),"Screen");
        selectByVisibleTextByLocator(SILPurpleAppPage.objSelectMerchantStore,value);
        jsClick(SILPurpleAppPage.objNextBtn,getTextVal(SILPurpleAppPage.objNextBtn,"Button"));
    }
    /**
     * Common method to add cart
     * @param category
     * @param brand
     * @param sku
     * @param price
     * @param item
     * @throws Exception
     */
    public void addToCart(String category,String brand,String sku,String price,String item) throws Exception {
        selectByValue(SILPurpleAppPage.objCategoryOptions(item),category);
        type(SILPurpleAppPage.objBrand(item),brand,"Brand");
        type(SILPurpleAppPage.objSKU(item),sku,"SKU");
        type(SILPurpleAppPage.objPrice(item),price,"Price");
    }
    /**
     * Common method to value QR Screen
     * @param price
     * @param downPayment
     * @param loanableAmount
     * @param emi
     * @param addOnRate
     * @throws Exception
     */
    public void qrCodeScreenValidation(String price, String downPayment, String loanableAmount, String emi, String addOnRate) throws Exception {
        verifyElementPresent(SILPurpleAppPage.objQRCode, getTextVal(SILPurpleAppPage.objQRCode, "Screen"));
        assertionValidation(getText(SILPurpleAppPage.objQRCode), propertyFileReader("QRCodeScreen", "SILPurpleApp"), "Screen");
        assertionValidation(getText(SILPurpleAppPage.objQRCodeSubTitle), propertyFileReader("QRCodeSubTitle", "SILPurpleApp"), "Screen Subtitle");
        assertionValidation(getText(SILPurpleAppPage.objMerchantNameInQRCodeScreen), propertyFileReader("SelectMerchant", "SILPurpleApp"), "Merchant Name");
        assertionValidation(getText(SILPurpleAppPage.objScanMeNowTxt), propertyFileReader("ScanMeNowTxt", "SILPurpleApp"), "Merchant Name");
        assertionValidation(getText(SILPurpleAppPage.objWaitTxtSubHeader), propertyFileReader("WaitTxtSubHeader", "SILPurpleApp"), "Wait sub header");
        assertionValidation(getText(SILPurpleAppPage.objDoNotKeepWaiting).replaceAll("’", ""), propertyFileReader("DoNotKeepWaiting", "SILPurpleApp"), "Wait sub header");
        verifyElementPresent(SILPurpleAppPage.objQRCodeGenerated, "QR Code generated");
        for (int info = 1; info <= 7; info++) {
            verifyElementPresent(SILPurpleAppPage.objGeneratedQRCodeInfo(info), getTextVal(SILPurpleAppPage.objGeneratedQRCodeInfo(info), "Info"));
        }
        waitTime(3000);//Screen navigation understanding
        assertionValidation(getText(SILPurpleAppPage.objItems), propertyFileReader("Category4", "SILPurpleApp"), "Item");
        containsValidation(getText(SILPurpleAppPage.objQRCodeTotalPrice).replaceAll(",", ""), "₱" + price, "Total price");
        containsValidation(getText(SILPurpleAppPage.objQRCodeDownPaymentAmount).replaceAll(",", ""), "₱" + downPayment, "Down payment amount");
        containsValidation(getText(SILPurpleAppPage.objQRCodeLoanableAmount).replaceAll(",", ""), "₱" + loanableAmount, "Loanable amount");
        containsValidation(getText(SILPurpleAppPage.objQRCodeTerms), propertyFileReader("EMIMonth", "SILPurpleApp"), "Loan term");
        assertionValidation(getText(SILPurpleAppPage.objQRCodeMonthlyInstallment), emi, "Installment amount");
        assertionValidation(getText(SILPurpleAppPage.objQRCodeAddOnRate), addOnRate, "Add on rate");
        screencapture();
    }
    /**
     * Common method to select EMI and Month
     * @param vasType
     * @param month
     * @return
     * @throws Exception
     */
    public String selectEMI(String vasType,String month) throws Exception {
        String emi;
        if(vasType.equalsIgnoreCase("WithVAS")){
            jsClick(SILPurpleAppPage.objSelectWithVASEMIMonth(month),getTextVal(SILPurpleAppPage.objSelectWithVASEMIMonth(month),"With VAS"));
            emi = getText(SILPurpleAppPage.objGetWithVASEMIAmount(month));
        }else{
            jsClick(SILPurpleAppPage.objSelectWithOutVASEMIMonth(month),getTextVal(SILPurpleAppPage.objSelectWithOutVASEMIMonth(month),"Without VAS"));
            emi = getText(SILPurpleAppPage.objGetWithOutVASEMIAmount(month));
        }
        return emi;
    }
    /**
     * Common method to js Click
     * @param byLocator
     * @param text
     * @return
     * @throws Exception
     */
    public static boolean jsClick(By byLocator, String text) throws Exception {
        try {
            js = (JavascriptExecutor) getWebDriver();
            js.executeScript("arguments[0].click();", getWebDriver().findElement(byLocator));
            logger.info(text + " " + " is clicked");
            ExtentReporter.extentLoggerPass("checkElementPresent", "" + text + " is clicked");
            return true;
        } catch (Exception e) {
            logger.error(text + " " + " is not clicked");
            ExtentReporter.extentLoggerFail("checkElementNotPresent", "" + text + " is not clicked");
            ExtentReporter.screencapture();
            return false;
        }
    }
    /**
     * Common method to validate few house rules
     * @param assessment
     * @param validation
     * @throws Exception
     */
    public void fewHouseRule(By assessment,String validation) throws Exception {
        verifyElementPresent(SILPurpleAppPage.objFewRulesPopup,getTextVal(SILPurpleAppPage.objFewRulesPopup,"Popup header"));
        assertionValidation(getText(SILPurpleAppPage.objFewRulesPopup),propertyFileReader("FewHouseRules","SILPurpleApp"),"Popup header");
        assertionValidation(getText(SILPurpleAppPage.objFewRulesPopupSubHeader),propertyFileReader("FewRulesPopupSubHeader","SILPurpleApp"),"SubHeader");
        assertionValidation(getText(SILPurpleAppPage.objGenuineID),propertyFileReader("GenuineID","SILPurpleApp"),"Option");
        verifyElementPresentAndClick(SILPurpleAppPage.objGenuineIDCheckBox,"Genuine ID Check Box");
        assertionValidation(getText(SILPurpleAppPage.objPhysicalPresence),propertyFileReader("PhysicalPresence","SILPurpleApp"),"Option");
        verifyElementPresentAndClick(SILPurpleAppPage.objPhysicalPresenceCheckBox,"Physical Presence Check Box");
        assertionValidation(getText(SILPurpleAppPage.objAssessmentInfo),propertyFileReader("AssessmentInfo","SILPurpleApp"),"Option");
        click(assessment,validation);
        jsClick(SILPurpleAppPage.objProceedBtn,getTextVal(SILPurpleAppPage.objProceedBtn,"Button"));
    }
    /**
     * Common method top scroll to particular element
     * @param driver
     * @param locator
     * @throws Exception
     */
    public static void scrollToElement(WebDriver driver, By locator) throws Exception {
        WebElement element = findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    /**
     * Common method to Apply Minimum down payment
     * @param minimumDownPayment
     * @throws Exception
     */
    public void applyNowPopup(int minimumDownPayment) throws Exception {
        if(verifyElementPresent(SILPurpleAppPage.objDownPaymentPopup,getTextVal(SILPurpleAppPage.objDownPaymentPopup,"Popup"))) {
            verifyElementPresent(SILPurpleAppPage.objDownPaymentInputField, "Down payment input field");
            assertionValidation(getText(SILPurpleAppPage.objDownPaymentInfo), propertyFileReader("DownPaymentInfo", "SILPurpleApp"), "Info");
            verifyElementPresent(SILPurpleAppPage.objCloseIcon, "Close Icon");
            type(SILPurpleAppPage.objDownPaymentInputField, String.valueOf(minimumDownPayment), "Down payment input field");
        }
    }
    /**
     * Common method to check button enable or disable
     * @param element
     * @param buttonName
     * @throws Exception
     */
    public static void isButtonDisabled(By element,String buttonName) throws Exception {
        WebElement button = findElement(element);
        boolean sButton=button.isEnabled();
        if(sButton) {
            logger.info(buttonName+" button is enabled state");
            extentLoggerFail("Month Options", buttonName+" button is in enabled state");
        } else {
            logger.info(buttonName+" button is disabled state");
            extentLoggerPass("Month Options", buttonName+" button is in disabled state");
        }
    }
    /**
     * Common method to calculate down payment
     * @param totalPrice
     * @param size
     * @return
     */
    public String calculateDownPayment(String totalPrice,int size){
        double downPayment = 0;
        if(Double.parseDouble(totalPrice)>100000){
            downPayment = Double.parseDouble(totalPrice)-Double.parseDouble(propertyFileReader("MinimumLoanableAmount","SILPurpleApp"));
        }else{
            downPayment = Double.parseDouble(totalPrice)*(Double.parseDouble(propertyFileReader("DownPaymentPercentage"+size,"SILPurpleApp"))/100);
        }
        return String.valueOf(downPayment);
    }
    /**
     * Method to calculate loanable amount
     * @param totalPrice
     * @param downPayment
     * @return
     */
    public String calculateLoanableAmount(String totalPrice,String downPayment){
        double loanableAmount = 0;
        if(Double.parseDouble(totalPrice)>100000) {
            loanableAmount = Double.parseDouble(propertyFileReader("MinimumLoanableAmount","SILPurpleApp"));
        }else{
            loanableAmount = Double.parseDouble(totalPrice)-Double.parseDouble(downPayment);
        }
        return String.valueOf(loanableAmount);
    }
    /**
     * Common method to calculate EMI
     * @param monthlyInterestRate
     * @param numberOfPayments
     * @param principal
     * @return
     */
    public static double calculateEMI(float monthlyInterestRate, int numberOfPayments, double principal) {
        double numerator = principal * monthlyInterestRate;
        double denominator = 1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments);
        return numerator / denominator;
    }
    /**
     * Common method top get PayHinga EMI
     * @param withOutPayHingEMI
     * @param payHingaInterest
     * @return
     */
    public String getPayHingaEMI(double withOutPayHingEMI,double payHingaInterest){
        return String.format("%.2f",withOutPayHingEMI*payHingaInterest+withOutPayHingEMI);
    }
    /**
     * Common method to get amount based on percentage
     * @param amount
     * @param percentage
     * @return
     */
    public double amountBasedOnPercentage(double amount,float percentage){
        return amount*(percentage/100);
    }
    /**
     * Common method to get Integer from String
     * @param month
     * @return
     */
    public int getIntegerFromString(String month){
        int number = 0;
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(month);
        while (matcher.find()) {
            number = Integer.parseInt(matcher.group());
        }
        return number;
    }
    /**
     * Common method to get available balance as Integer
     * @param locator
     * @return
     * @throws Exception
     */
    public double getAvailableBalanceInteger(By locator) throws Exception {
        String availableBalance = getText(locator);
        String trim = availableBalance.replace("₱","");
        if(trim.contains(",")){
            trim = trim.replace(",","");
        }
        return Double.parseDouble(trim);
    }
    /**
     * Method to Verify if promoter can load the Purple App Login page
     * @throws Exception
     */
    public void verifyIfPromoterCanLoadPurpleAppLoginPageValidation_TDB_PW_001() throws Exception {
        HeaderChildNode("TDB_PW_001, SIL Purple App - Verify if promoter can load the Purple App Login page");
        waitForElementToBePresent(SILPurpleAppPage.objWelcomeScreen,20,"Welcome screen");
        assertionValidation(getText(SILPurpleAppPage.objWelcomeScreen),propertyFileReader("WelcomeScreenHeader","SILPurpleApp"),"Screen");
        assertionValidation(getText(SILPurpleAppPage.objLoginScreenSubHeader),propertyFileReader("WelcomeScreenSubHeader","SILPurpleApp"),"Screen");
        assertionValidation(getText(SILPurpleAppPage.objPurpleApp),propertyFileReader("PurpleAppTxt","SILPurpleApp"),"text");
        verifyElementPresent(SILPurpleAppPage.objUserNameInputField,"User name input field");
        verifyElementPresent(SILPurpleAppPage.objPasswordInputField,"Password input field");
        containsValidation(getText(SILPurpleAppPage.objForgotPasswordLink),propertyFileReader("ForgotPasswordLink","SILPurpleApp"),"Link");
        verifyElementPresent(SILPurpleAppPage.objPasswordEyeIcon,"Password Eye icon");
        assertionValidation(getText(SILPurpleAppPage.objLoginBtn),propertyFileReader("LoginBtn","SILPurpleApp"),"Button");
        logger.info("TDB_PW_001, SIL Purple App - Promoter can load the Purple App Login validated");
        extentLoggerPass("TDB_PW_001", "TDB_PW_001, SIL Purple App - Promoter can load the Purple App Login validated");
    }
    /**
     * Method to validate valid user name and password
     * @throws Exception
     */
    public void validUserNameAndPasswordValidation_TDB_PW_002() throws Exception {
        HeaderChildNode("TDB_PW_002, SIL Purple App - Promoter must have a valid username and password");
        waitForElementToBePresent(SILPurpleAppPage.objWelcomeScreen,20,"Welcome screen");
        assertionValidation(getText(SILPurpleAppPage.objPurpleApp),propertyFileReader("PurpleAppTxt","SILPurpleApp"),"text");
        type(SILPurpleAppPage.objUserNameInputField,propertyFileReader("UserName","SILPurpleApp"),"User name input field");
        type(SILPurpleAppPage.objPasswordInputField,propertyFileReader("Password","SILPurpleApp"),"Password input field");
        screencapture();
        jsClick(SILPurpleAppPage.objPasswordEyeIcon,"Password Eye Icon");
        screencapture();
        jsClick(SILPurpleAppPage.objLoginBtn,getTextVal(SILPurpleAppPage.objLoginBtn,"Button"));
        waitForElementToBePresent(SILPurpleAppPage.objMerchantStore,5,"Merchant Store Screen");
        if(verifyElementPresent(SILPurpleAppPage.objMerchantStore,getTextVal(SILPurpleAppPage.objMerchantStore,"Screen"))){
            assertionValidation(getText(SILPurpleAppPage.objMerchantStore),propertyFileReader("MerchantStoreScreen","SILPurpleApp"),"Screen");
            verifyElementPresent(SILPurpleAppPage.objSelectMerchantStore,"Select Merchant Store dropdown");
            assertionValidation(getText(SILPurpleAppPage.objNextBtn), propertyFileReader("NextBtn","SILPurpleApp"), "Button");
            logger.info("TDB_PW_002, SIL Purple App - Promoter entered a valid username and password and navigated to Merchant Store screen validated");
            extentLoggerPass("TDB_PW_002", "TDB_PW_002, SIL Purple App - Promoter entered a valid username and password and navigated to Merchant Store screen validated");
        }
    }
    /**
     * Method to Verify the promoter can't Login to the Purple App with invalid credentials
     * @throws Exception
     */
    public void invalidUserNameAndPasswordValidation_TDB_PW_004() throws Exception {
        HeaderChildNode("TDB_PW_004, SIL Purple App - Verify the promoter can't Login to the Purple App with invalid credentials");
        purpleAppLogin(propertyFileReader("UserName","SILPurpleApp"),propertyFileReader("InvalidPassword","SILPurpleApp"));
        assertionValidation(getText(SILPurpleAppPage.objInvalidCredentialErrorMsg),propertyFileReader("InvalidCredentialErrorMsg","SILPurpleApp"),"Error message");
        purpleAppLogin(propertyFileReader("InvalidUserName","SILPurpleApp"),propertyFileReader("Password","SILPurpleApp"));
        assertionValidation(getText(SILPurpleAppPage.objInvalidCredentialErrorMsg),propertyFileReader("InvalidCredentialErrorMsg","SILPurpleApp"),"Error message");
        logger.info("TDB_PW_004, SIL Purple App - Promoter can't Login to the Purple App with invalid credentials validated");
        extentLoggerPass("TDB_PW_004", "TDB_PW_004, SIL Purple App - Promoter can't Login to the Purple App with invalid credentials validated");
    }
    /**
     * Method to Verify if promoter can select a Merchant Name
     * @throws Exception
     */
    public void verifyMerchantCanSelectMerchantName_TDB_PW_006() throws Exception {
        HeaderChildNode("TDB_PW_006, SIL Purple App - Verify if promoter can select a Merchant Name");
        purpleAppLogin(propertyFileReader("UserName","SILPurpleApp"),propertyFileReader("Password","SILPurpleApp"));
        verifyElementPresent(SILPurpleAppPage.objMerchantStore,getTextVal(SILPurpleAppPage.objMerchantStore,"Screen"));
        assertionValidation(getText(SILPurpleAppPage.objMerchantStore),propertyFileReader("MerchantStoreScreen","SILPurpleApp"),"Screen");
        Utilities.back(1);
        assertionValidation(getText(SILPurpleAppPage.objPurpleApp),propertyFileReader("PurpleAppTxt","SILPurpleApp"),"text");
        purpleAppLogin(propertyFileReader("UserName","SILPurpleApp"),propertyFileReader("Password","SILPurpleApp"));
        jsClick(SILPurpleAppPage.objSelectMerchantStore,"Select Merchant dropdown");
        waitTime(3000);//Required to load dropdown elements
        List<WebElement> values = findElements(SILPurpleAppPage.objSelectMerchantList);
        for (int merchantOption = 0; merchantOption < values.size(); merchantOption++) {
            logger.info("Merchant option : '" + values.get(merchantOption).getText() + "' is displayed");
            ExtentReporter.extentLogger(" ", "Merchant option : '" + values.get(merchantOption).getText() + "' is displayed");
        }
        selectByVisibleTextByLocator(SILPurpleAppPage.objSelectMerchantStore,propertyFileReader("SelectMerchant","SILPurpleApp"));
        jsClick(SILPurpleAppPage.objNextBtn,getTextVal(SILPurpleAppPage.objNextBtn,"Button"));
        if(verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen,getTextVal(SILPurpleAppPage.objQRGeneratorScreen,"Screen"))){
            assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen),propertyFileReader("QRGeneratorScreen","SILPurpleApp"),"Screen");
            logger.info("TDB_PW_006, SIL Purple App - Promoter can select a Merchant Name and navigated to QR Generator Screen validated");
            extentLoggerPass("TDB_PW_006", "TDB_PW_006, SIL Purple App -  Promoter can select a Merchant Name and navigated to QR Generator Screen validated");
        }
    }
    /**
     * Method to Verify if promoter can Logout from Purple Web
     * @throws Exception
     */
    public void  logoutFromPurpleWebValidation_TDB_PW_007() throws Exception {
        HeaderChildNode("TDB_PW_007, SIL Purple App - Verify if promoter can Logout from Purple Web");
        purpleAppLogin(propertyFileReader("UserName","SILPurpleApp"),propertyFileReader("Password","SILPurpleApp"));
        selectMerchantAndNext(propertyFileReader("SelectMerchant","SILPurpleApp"));
        verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen,getTextVal(SILPurpleAppPage.objQRGeneratorScreen,"Screen"));
        verifyElementPresent(SILPurpleAppPage.objProfileImage,"Profile Image");
        verifyElementPresent(SILPurpleAppPage.objUserName,getTextVal(SILPurpleAppPage.objUserName,"User name"));
        assertionValidation(getText(SILPurpleAppPage.objMerchantName),propertyFileReader("SelectMerchant","SILPurpleApp"),"Merchant name");
        assertionValidation(getText(SILPurpleAppPage.objQRCodeLink),propertyFileReader("QRCode","SILPurpleApp"),"Link");
        assertionValidation(getText(SILPurpleAppPage.objLogOut),propertyFileReader("LogOut","SILPurpleApp"),"Link");
        assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen),propertyFileReader("QRGeneratorScreen","SILPurpleApp"),"Screen");
        assertionValidation(getText(SILPurpleAppPage.objAddToCartHeader),propertyFileReader("AddToCartHeader","SILPurpleApp"),"Header");
        assertionValidation(getText(SILPurpleAppPage.objAddToCartSubHeader),propertyFileReader("AddToCartSubHeader","SILPurpleApp"),"SubHeader");
        containsValidation(getText(SILPurpleAppPage.objItemNumber),propertyFileReader("ItemNumber","SILPurpleApp"),"SubHeader");
        verifyElementPresent(SILPurpleAppPage.objCategoryField("1"),getTextVal(SILPurpleAppPage.objCategoryField("1"),"Dropdown"));
        verifyElementPresent(SILPurpleAppPage.objBrand,getTextVal(SILPurpleAppPage.objBrand,"Brand Input field"));
        verifyElementPresent(SILPurpleAppPage.objSKU,getTextVal(SILPurpleAppPage.objSKU,"SKU Input field"));
        verifyElementPresent(SILPurpleAppPage.objPrice,getTextVal(SILPurpleAppPage.objPrice,"Price Input field"));
        containsValidation(getText(SILPurpleAppPage.objAddMoreItem),propertyFileReader("AddMoreItemBtn","SILPurpleApp"),"Button");
        scrollDownWEB();
        assertionValidation(getText(SILPurpleAppPage.objLoanSummaryDetailsHeader),propertyFileReader("LoanSummaryDetailsHeader","SILPurpleApp"),"Header");
        containsValidation(getText(SILPurpleAppPage.objTotalAmount),propertyFileReader("BeforeAmount","SILPurpleApp"),"Amount");
        assertionValidation(getText(SILPurpleAppPage.objTotalAmountTxt),propertyFileReader("TotalAmountTxt","SILPurpleApp"),"Field name");
        containsValidation(getText(SILPurpleAppPage.objActualDownPayment),propertyFileReader("BeforeAmount","SILPurpleApp"),"Amount");
        assertionValidation(getText(SILPurpleAppPage.objActualDownPaymentTxt),propertyFileReader("ActualDownPaymentTxt","SILPurpleApp"),"Field name");
        assertionValidation(getText(SILPurpleAppPage.objLoanableAmountTxt),propertyFileReader("LoanableAmountTxt","SILPurpleApp"),"Field name");
        containsValidation(getText(SILPurpleAppPage.objLoanableAmount),propertyFileReader("BeforeAmount","SILPurpleApp"),"Amount");
        assertionValidation(getText(SILPurpleAppPage.objInstallmentTerms),propertyFileReader("InstallmentTermsHeader","SILPurpleApp"),"Header");
        assertionValidation(getText(SILPurpleAppPage.objInstallmentTermsSubtitleOne),propertyFileReader("InstallmentTermsSubtitleOne","SILPurpleApp"),"SubHeader");
        assertionValidation(getText(SILPurpleAppPage.objInstallmentTermsSubtitleTwo),propertyFileReader("InstallmentTermsSubtitleTwo","SILPurpleApp"),"SubHeader");
        assertionValidation(getText(SILPurpleAppPage.objGenerateQRCode),propertyFileReader("GenerateQRCode","SILPurpleApp"),"Button");
        Utilities.back(1);
        selectMerchantAndNext(propertyFileReader("SelectMerchant","SILPurpleApp"));
        jsClick(SILPurpleAppPage.objLogOut,getTextVal(SILPurpleAppPage.objLogOut,"Button"));
        if(verifyElementPresent(SILPurpleAppPage.objWelcomeScreen,getTextVal(SILPurpleAppPage.objWelcomeScreen,"Screen"))) {
            assertionValidation(getText(SILPurpleAppPage.objWelcomeScreen), propertyFileReader("WelcomeScreenHeader", "SILPurpleApp"), "Screen");
            assertionValidation(getText(SILPurpleAppPage.objLoginScreenSubHeader), propertyFileReader("WelcomeScreenSubHeader", "SILPurpleApp"), "Screen");
            assertionValidation(getText(SILPurpleAppPage.objPurpleApp), propertyFileReader("PurpleAppTxt", "SILPurpleApp"), "text");
            logger.info("TDB_PW_007, SIL Purple App - Promoter can Logout from Purple Web validated");
            extentLoggerPass("TDB_PW_007", "TDB_PW_007, SIL Purple App - Promoter can Logout from Purple Web validated");
        }
    }
    /**
     * Method to Verify if promoter can add an item to the cart
     * @throws Exception
     */
    public void  addItemToCartValidation_TDB_PW_008() throws Exception {
        HeaderChildNode("TDB_PW_008, SIL Purple App - Verify if promoter can add an item to the cart");
        purpleAppLogin(propertyFileReader("UserName", "SILPurpleApp"), propertyFileReader("Password", "SILPurpleApp"));
        selectMerchantAndNext(propertyFileReader("SelectMerchant", "SILPurpleApp"));
        verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen, getTextVal(SILPurpleAppPage.objQRGeneratorScreen, "Screen"));
        assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen),propertyFileReader("QRGeneratorScreen","SILPurpleApp"),"Screen");
        jsClick(SILPurpleAppPage.objCategoryField("1"),"Category dropdown");
        waitTime(3000);//Required to load dropdown elements
        List<WebElement> values = findElements(SILPurpleAppPage.objSelectMerchantList);
        for (int categoryOption = 1; categoryOption <=values.size(); categoryOption++) {
            assertionValidation(getText(SILPurpleAppPage.objSelectMerchantList(categoryOption)),propertyFileReader("Category"+categoryOption,"SILPurpleApp").toUpperCase(),"Category");
        }
        selectByVisibleTextByLocator(SILPurpleAppPage.objCategoryOptions("1"),propertyFileReader("Category1","SILPurpleApp"));
        containsValidation(getText(SILPurpleAppPage.objCategoryField("1")),propertyFileReader("Category1","SILPurpleApp").toUpperCase(),"Category");
        type(SILPurpleAppPage.objBrand("1"),propertyFileReader("Brand","SILPurpleApp"),"Brand");
        assertionValidation(getAttributValue("value",SILPurpleAppPage.objBrand("1")),propertyFileReader("Brand","SILPurpleApp"),"Brand");
        type(SILPurpleAppPage.objSKU("1"),propertyFileReader("SKU","SILPurpleApp"),"SKU");
        assertionValidation(getAttributValue("value",SILPurpleAppPage.objSKU("1")),propertyFileReader("SKU","SILPurpleApp"),"SKU");
        type(SILPurpleAppPage.objPrice("1"),propertyFileReader("Price","SILPurpleApp"),"Price");
        assertionValidation(getAttributValue("value",SILPurpleAppPage.objPrice("1")).replaceAll(",",""),"₱"+propertyFileReader("Price","SILPurpleApp"),"Price");
        logger.info("TDB_PW_008, SIL Purple App - Promoter can add an item to the cart validated");
        extentLoggerPass("TDB_PW_008", "TDB_PW_008, SIL Purple App - Promoter can add an item to the cart validated");
    }
    /**
     * Method to Verify if promoter can add 2nd item to the cart
     * @throws Exception
     */
    public void addSecondItemToCartValidation_TDB_PW_009() throws Exception {
        HeaderChildNode("TDB_PW_009, SIL Purple App - Verify if promoter can add 2nd item to the cart");
        purpleAppLogin(propertyFileReader("UserName", "SILPurpleApp"), propertyFileReader("Password", "SILPurpleApp"));
        selectMerchantAndNext(propertyFileReader("SelectMerchant", "SILPurpleApp"));
        verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen, getTextVal(SILPurpleAppPage.objQRGeneratorScreen, "Screen"));
        assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen),propertyFileReader("QRGeneratorScreen","SILPurpleApp"),"Screen");
        addToCart(propertyFileReader("Category1","SILPurpleApp"),propertyFileReader("Brand","SILPurpleApp"),propertyFileReader("SKU","SILPurpleApp"),propertyFileReader("Price","SILPurpleApp"),"1");
        jsClick(SILPurpleAppPage.objAddMoreItem,getTextVal(SILPurpleAppPage.objAddMoreItem,"Button"));
        jsClick(SILPurpleAppPage.objCategoryField("2"),"Category dropdown");
        waitTime(3000);//Required to load dropdown elements
        List<WebElement> values = findElements(SILPurpleAppPage.objSelectMerchantList);
        for (int categoryOption = 1; categoryOption <=values.size(); categoryOption++) {
            assertionValidation(getText(SILPurpleAppPage.objSelectMerchantList(categoryOption)),propertyFileReader("Category"+categoryOption,"SILPurpleApp").toUpperCase(),"Category");
        }
        selectByVisibleTextByLocator(SILPurpleAppPage.objCategoryOptions("2"),propertyFileReader("Category2","SILPurpleApp"));
        containsValidation(getText(SILPurpleAppPage.objCategoryField("2")),propertyFileReader("Category2","SILPurpleApp").toUpperCase(),"Category");
        type(SILPurpleAppPage.objBrand("2"),propertyFileReader("Brand","SILPurpleApp"),"Brand");
        assertionValidation(getAttributValue("value",SILPurpleAppPage.objBrand("2")), propertyFileReader("Brand","SILPurpleApp"), "Brand");
        type(SILPurpleAppPage.objSKU("2"),propertyFileReader("SKU","SILPurpleApp"),"SKU");
        assertionValidation(getAttributValue("value",SILPurpleAppPage.objSKU("2")),propertyFileReader("SKU","SILPurpleApp"),"SKU");
        type(SILPurpleAppPage.objPrice("2"),propertyFileReader("Price","SILPurpleApp"),"Price");
        assertionValidation(getAttributValue("value",SILPurpleAppPage.objPrice("2")).replaceAll(",",""),"₱"+propertyFileReader("Price","SILPurpleApp"),"Price");
        logger.info("TDB_PW_009, SIL Purple App - Promoter can add 2nd item to the cart validated");
        extentLoggerPass("TDB_PW_009", "TDB_PW_009, SIL Purple App - Promoter can add 2nd item to the cart validated");
    }
    /**
     * Method to Verify the promoter can add only 3 items to the cart
     * @throws Exception
     */
    public void addThirdItemToCartValidation_TDB_PW_010() throws Exception {
        HeaderChildNode("TDB_PW_010, SIL Purple App - Verify the promoter can add only 3 items to the cart");
        purpleAppLogin(propertyFileReader("UserName", "SILPurpleApp"), propertyFileReader("Password", "SILPurpleApp"));
        selectMerchantAndNext(propertyFileReader("SelectMerchant", "SILPurpleApp"));
        verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen, getTextVal(SILPurpleAppPage.objQRGeneratorScreen, "Screen"));
        assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen),propertyFileReader("QRGeneratorScreen","SILPurpleApp"),"Screen");
        addToCart(propertyFileReader("Category1","SILPurpleApp"),propertyFileReader("Brand","SILPurpleApp"),propertyFileReader("SKU","SILPurpleApp"),propertyFileReader("Price","SILPurpleApp"),"1");
        jsClick(SILPurpleAppPage.objAddMoreItem,getTextVal(SILPurpleAppPage.objAddMoreItem,"Button"));
        addToCart(propertyFileReader("Category2","SILPurpleApp"),propertyFileReader("Brand","SILPurpleApp"),propertyFileReader("SKU","SILPurpleApp"),propertyFileReader("Price","SILPurpleApp"),"2");
        jsClick(SILPurpleAppPage.objAddMoreItem,getTextVal(SILPurpleAppPage.objAddMoreItem,"Button"));
        jsClick(SILPurpleAppPage.objCategoryField("3"),"Category dropdown");
        waitTime(3000);//Required to load dropdown elements
        List<WebElement> values = findElements(SILPurpleAppPage.objSelectMerchantList);
        for (int categoryOption = 1; categoryOption <=values.size(); categoryOption++) {
            assertionValidation(getText(SILPurpleAppPage.objSelectMerchantList(categoryOption)),propertyFileReader("Category"+categoryOption,"SILPurpleApp").toUpperCase(),"Category");
        }
        selectByVisibleTextByLocator(SILPurpleAppPage.objCategoryOptions("3"),propertyFileReader("Category2","SILPurpleApp"));
        containsValidation(getText(SILPurpleAppPage.objCategoryField("3")),propertyFileReader("Category2","SILPurpleApp").toUpperCase(),"Category");
        type(SILPurpleAppPage.objBrand("3"),propertyFileReader("Brand","SILPurpleApp"),"Brand");
        assertionValidation(getAttributValue("value",SILPurpleAppPage.objBrand("2")), propertyFileReader("Brand","SILPurpleApp"), "Brand");
        type(SILPurpleAppPage.objSKU("3"),propertyFileReader("SKU","SILPurpleApp"),"SKU");
        assertionValidation(getAttributValue("value",SILPurpleAppPage.objSKU("3")),propertyFileReader("SKU","SILPurpleApp"),"SKU");
        type(SILPurpleAppPage.objPrice("3"),propertyFileReader("Price","SILPurpleApp"),"Price");
        assertionValidation(getAttributValue("value",SILPurpleAppPage.objPrice("3")).replaceAll(",",""),"₱"+propertyFileReader("Price","SILPurpleApp"),"Price");
        verifyElementNotDisplayed(SILPurpleAppPage.objAddMoreItem,"Add More button");
        logger.info("TDB_PW_010, SIL Purple App - Promoter can add only 3 items to the cart validated");
        extentLoggerPass("TDB_PW_010", "TDB_PW_010, SIL Purple App - Promoter can add only 3 items to the cart validated");
    }
    /**
     * Method to Verify if Loan Summary Details is auto calculated upon inputting the Price amount
     * @throws Exception
     */
    public void loanSummaryDetailsValidationAfterInputtingPriceAmount_TDB_PW_011() throws Exception {
        HeaderChildNode("TDB_PW_011, SIL Purple App - Verify if Loan Summary Details is auto calculated upon inputting the Price amount");
        purpleAppLogin(propertyFileReader("UserName", "SILPurpleApp"), propertyFileReader("Password", "SILPurpleApp"));
        selectMerchantAndNext(propertyFileReader("SelectMerchant", "SILPurpleApp"));
        verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen, getTextVal(SILPurpleAppPage.objQRGeneratorScreen, "Screen"));
        assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen),propertyFileReader("QRGeneratorScreen","SILPurpleApp"),"Screen");
        addToCart(propertyFileReader("Category4","SILPurpleApp"),propertyFileReader("Brand","SILPurpleApp"),propertyFileReader("SKU","SILPurpleApp"),propertyFileReader("LessPrice5K","SILPurpleApp"),"1");
        assertionValidation(getText(SILPurpleAppPage.objLoanSummaryDetailsHeader),propertyFileReader("LoanSummaryDetailsHeader","SILPurpleApp"),"Header");
        scrollDownWEB();
        containsValidation(getText(SILPurpleAppPage.objTotalAmount).replaceAll(",",""),propertyFileReader("LessPrice5K","SILPurpleApp"),"Total amount");
        assertionValidation(getText(SILPurpleAppPage.objTotalAmountTxt),propertyFileReader("TotalAmountTxt","SILPurpleApp"),"Field name");
        double actualDownPayment =  Double.parseDouble(propertyFileReader("LessPrice5K","SILPurpleApp"))*Double.parseDouble(propertyFileReader("LessDownPaymentPercentage","SILPurpleApp"));
        assertionValidation(getText(SILPurpleAppPage.objActualDownPaymentTxt),propertyFileReader("ActualDownPaymentTxt","SILPurpleApp"),"Field name");
        assertionValidation(getText(SILPurpleAppPage.objLoanableAmountTxt),propertyFileReader("LoanableAmountTxt","SILPurpleApp"),"Field name");
        containsValidation(getText(SILPurpleAppPage.objActualDownPayment).replaceAll(",",""),"₱"+actualDownPayment,"Actual Down Payment");
        verifyElementPresent(SILPurpleAppPage.objChangeAmount,"Edit Icon Actual down payment");
        double loanableAmount = Double.parseDouble(propertyFileReader("LessPrice5K","SILPurpleApp"))-actualDownPayment;
        containsValidation(getText(SILPurpleAppPage.objLoanableAmount).replaceAll(",",""),"₱"+loanableAmount,"Loanable amount");
        if(verifyElementDisplayed(SILPurpleAppPage.objAddOnRate)){
            logger.info("Add-on rate should be displayed if Loanable amount is less than 5K");
            extentLoggerFail("TDB_PW_011","Add-on rate should be displayed if Loanable amount is less than 5K");
        }else{
            logger.info("Add-on rate should not be displayed if Loanable amount is less than 5K");
            extentLoggerPass("TDB_PW_011","Add-on rate should not be displayed if Loanable amount is less than 5K");
        }
        scrollToTopOfPageWEB();
        clearWebField(SILPurpleAppPage.objPrice("1"),"Price input field");
        addToCart(propertyFileReader("Category4","SILPurpleApp"),propertyFileReader("Brand","SILPurpleApp"),propertyFileReader("SKU","SILPurpleApp"),propertyFileReader("MoreThan5K","SILPurpleApp"),"1");
        scrollDownWEB();
        containsValidation(getText(SILPurpleAppPage.objTotalAmount).replaceAll(",",""),propertyFileReader("MoreThan5K","SILPurpleApp"),"Total amount");
        assertionValidation(getText(SILPurpleAppPage.objTotalAmountTxt),propertyFileReader("TotalAmountTxt","SILPurpleApp"),"Field name");
        double actualDownPaymentMaximum =  Double.parseDouble(propertyFileReader("MoreThan5K","SILPurpleApp"))*Double.parseDouble(propertyFileReader("LessDownPaymentPercentage","SILPurpleApp"));
        containsValidation(getText(SILPurpleAppPage.objActualDownPayment).replaceAll(",",""),"₱"+actualDownPaymentMaximum,"Actual Down Payment");
        verifyElementPresent(SILPurpleAppPage.objChangeAmount,"Edit Icon Actual down payment");
        double loanableAmountMaximum = Double.parseDouble(propertyFileReader("MoreThan5K","SILPurpleApp"))-actualDownPaymentMaximum;
        containsValidation(getText(SILPurpleAppPage.objLoanableAmount).replaceAll(",",""),"₱"+loanableAmountMaximum,"Loanable amount");
        scrollToElement(getWebDriver(),SILPurpleAppPage.objAddOnRate);
        if(verifyElementDisplayed(SILPurpleAppPage.objAddOnRate)){
            logger.info("Add-on rate should be displayed if Loanable amount is less than 5K");
            extentLoggerPass("TDB_PW_011","Add-on rate should not be displayed if Loanable amount is less than 5K");
        }else{
            logger.info("Add-on rate should not be displayed if Loanable amount is less than 5K");
            extentLogger("TDB_PW_011","Add-on rate should not be displayed if Loanable amount is less than 5K");
        }
        logger.info("TDB_PW_011, SIL Purple App - Loan Summary Details is auto calculated upon inputting the Price amount validated");
        extentLoggerPass("TDB_PW_011", "TDB_PW_011, SIL Purple App - Loan Summary Details is auto calculated upon inputting the Price amount validated");
    }
    /**
     * Method to Verify if Rate Chart is auto calculated upon inputting the Price amount
     * @throws Exception
     */
    public void rateChartValidationUponInputtingPriceAmount_TDB_PW_012() throws Exception {
        HeaderChildNode("TDB_PW_012, SIL Purple App - Verify if Rate Chart is auto calculated upon inputting the Price amount");
        purpleAppLogin(propertyFileReader("UserName", "SILPurpleApp"), propertyFileReader("Password", "SILPurpleApp"));
        selectMerchantAndNext(propertyFileReader("SelectMerchant", "SILPurpleApp"));
        verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen, getTextVal(SILPurpleAppPage.objQRGeneratorScreen, "Screen"));
        assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen), propertyFileReader("QRGeneratorScreen", "SILPurpleApp"), "Screen");
        addToCart(propertyFileReader("Category4", "SILPurpleApp"), propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), propertyFileReader("MoreThan5K", "SILPurpleApp"), "1");
        clearWebField(SILPurpleAppPage.objPrice("1"),"Price input field");
        addToCart(propertyFileReader("Category4", "SILPurpleApp"), propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), propertyFileReader("MoreThan5K", "SILPurpleApp"), "1");
        List<WebElement> values = findElements(SILPurpleAppPage.objAddOnRatePercentage);
        for (int percentage = 1; percentage <=values.size(); percentage++) {
            containsValidation(getText(SILPurpleAppPage.objAddOnRatePercentage(percentage)),propertyFileReader("AddOnRatePercentage"+percentage,"SILPurpleApp")+"%","Monthly Add-on Rate");
            containsValidation(getText(SILPurpleAppPage.objDownPaymentPercentage(percentage)),propertyFileReader("DownPaymentPercentage"+percentage,"SILPurpleApp")+"%","Rate charge percentage");
            String amount = String.format("%.2f",amountBasedOnPercentage(Double.parseDouble(propertyFileReader("MoreThan5K","SILPurpleApp")),Float.parseFloat(propertyFileReader("DownPaymentPercentage"+percentage,"SILPurpleApp"))));
            containsValidation(getText(SILPurpleAppPage.objDownPaymentAmount(percentage)).replaceAll(",",""),"₱"+amount,"Down payment amount");
        }
        logger.info("TDB_PW_012, SIL Purple App - Rate Chart is auto calculated upon inputting the Price amount validated");
        extentLoggerPass("TDB_PW_012", "TDB_PW_012, SIL Purple App - Rate Chart is auto calculated upon inputting the Price amount validated");
    }
    /**
     * Method to Verify if Installment terms is auto populated upon inputting the Price amount
     * @param price
     * @throws Exception
     */
    public void installmentTermsValidationUponInputtingPriceAmount_TDB_PW_013(String price) throws Exception {
        HeaderChildNode("TDB_PW_013, SIL Purple App - Verify if Installment terms is auto populated upon inputting the Price amount");
        purpleAppLogin(propertyFileReader("UserName", "SILPurpleApp"), propertyFileReader("Password", "SILPurpleApp"));
        selectMerchantAndNext(propertyFileReader("SelectMerchant", "SILPurpleApp"));
        verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen, getTextVal(SILPurpleAppPage.objQRGeneratorScreen, "Screen"));
        assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen), propertyFileReader("QRGeneratorScreen", "SILPurpleApp"), "Screen");
        addToCart(propertyFileReader("Category4", "SILPurpleApp"), propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), price, "1");
        clearWebField(SILPurpleAppPage.objPrice("1"), "Price input field");
        addToCart(propertyFileReader("Category4", "SILPurpleApp"), "", "", price, "1");
        scrollDownWEB();
        List<WebElement> rates = findElements(SILPurpleAppPage.objAddOnRatePercentage);
        int highLightedDownPayment = rates.size();
        String downPaymentAmount = String.format("%.2f",amountBasedOnPercentage(Double.parseDouble(price),Float.parseFloat(propertyFileReader("DownPaymentPercentage"+highLightedDownPayment,"SILPurpleApp"))));
        double loanableAmount = Double.parseDouble(price)-Double.parseDouble(downPaymentAmount);
        scrollToBottomOfPageWEB();
        List<WebElement> values = findElements(SILPurpleAppPage.objWithOutPayHingaMonths);
        for (int month = 1; month <=values.size(); month++) {
            assertionValidation(getText(SILPurpleAppPage.objWithOutPayHingaMonths(month)),propertyFileReader("Month"+month,"SILPurpleApp"),"Without PayHinga EMI months");
            int tenure=getIntegerFromString(getText(SILPurpleAppPage.objWithOutPayHingaMonths(month)));
            String emi= String.format("%.2f",calculateEMI(Float.parseFloat(propertyFileReader("MonthlyInterestRate"+month,"SILPurpleApp")),tenure,loanableAmount));
            containsValidation(getText(SILPurpleAppPage.objWithOutPayHingaEMI(month)).replaceAll(",",""),"₱"+emi,"EMI Amount without PayHinga");
            assertionValidation(getText(SILPurpleAppPage.objWithPayHingaMonths(month)),propertyFileReader("Month"+month,"SILPurpleApp"),"With PayHinga EMI months");
            containsValidation(getText(SILPurpleAppPage.objWithPayHingaEMI(month)).replaceAll(",",""),
                    "₱"+getPayHingaEMI(Double.parseDouble(emi),Double.parseDouble(propertyFileReader("WithPayHingaInterest","SILPurpleApp"))),"With PayHinga EMI months");
        }
        isButtonDisabled(SILPurpleAppPage.objGenerateQRCode,"Generate QR Code");
        logger.info("TDB_PW_013, SIL Purple App - Installment terms is auto populated upon inputting the Price amount validated");
        extentLoggerPass("TDB_PW_013", "TDB_PW_013, SIL Purple App - Installment terms is auto populated upon inputting the Price amount validated");
    }
    /**
     * Method to Verify the down payment % if Promoter selects the Mobile Phones
     * @param price
     * @param category
     * @throws Exception
     */
    public void downPaymentPercentageIfPromoterSelectsMobilePhones_TDB_PW_014(String price,String category,String otherCategory) throws Exception {
        HeaderChildNode("TDB_PW_014, SIL Purple App - Verify the down payment % if Promoter selects the Mobile Phones");
        purpleAppLogin(propertyFileReader("UserName", "SILPurpleApp"), propertyFileReader("Password", "SILPurpleApp"));
        selectMerchantAndNext(propertyFileReader("SelectMerchant", "SILPurpleApp"));
        verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen, getTextVal(SILPurpleAppPage.objQRGeneratorScreen, "Screen"));
        assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen), propertyFileReader("QRGeneratorScreen", "SILPurpleApp"), "Screen");
        addToCart(otherCategory, propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), price, "1");
        clearWebField(SILPurpleAppPage.objPrice("1"),"Price input field");
        addToCart(category, propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), price, "1");
        List<WebElement> values = findElements(SILPurpleAppPage.objAddOnRatePercentage);
        containsValidation(getText(SILPurpleAppPage.objTotalAmount).replaceAll(",",""),price,"Total amount");
        double actualDownPaymentMaximum =  Double.parseDouble(price)*(Double.parseDouble(propertyFileReader("DownPaymentPercentage"+values.size(),"SILPurpleApp"))/100);
        containsValidation(getText(SILPurpleAppPage.objActualDownPayment).replaceAll(",",""),"₱"+actualDownPaymentMaximum,"Actual Down Payment");
        verifyElementPresent(SILPurpleAppPage.objChangeAmount,"Edit Icon Actual down payment");
        double loanableAmountMaximum = Double.parseDouble(price)-actualDownPaymentMaximum;
        containsValidation(getText(SILPurpleAppPage.objLoanableAmount).replaceAll(",",""),"₱"+loanableAmountMaximum,"Loanable amount");
        containsValidation(getText(SILPurpleAppPage.objDownPaymentPercentage(values.size())),propertyFileReader("MinDownPaymentPercentageForMobile","SILPurpleApp"),"Minimum Rate charge percentage");
        for (int percentage = 1; percentage <=values.size(); percentage++) {
            containsValidation(getText(SILPurpleAppPage.objAddOnRatePercentage(percentage)),propertyFileReader("AddOnRatePercentage"+percentage,"SILPurpleApp")+"%","Monthly Add-on Rate");
            containsValidation(getText(SILPurpleAppPage.objDownPaymentPercentage(percentage)),propertyFileReader("DownPaymentPercentage"+percentage,"SILPurpleApp")+"%","Rate charge percentage");
            String amount = String.format("%.2f",amountBasedOnPercentage(Double.parseDouble(price),Float.parseFloat(propertyFileReader("DownPaymentPercentage"+percentage,"SILPurpleApp"))));
            containsValidation(getText(SILPurpleAppPage.objDownPaymentAmount(percentage)).replaceAll(",",""),"₱"+amount,"Down payment amount");
        }
        logger.info("TDB_PW_014, SIL Purple App - Minimum down payment % if Promoter selects the Mobile Phones is 30% validated");
        extentLoggerPass("TDB_PW_014", "TDB_PW_014, SIL Purple App - Minimum down payment % if Promoter selects the Mobile Phones is 30% validated");
    }
    /**
     * Method to Verify the down payment % if Promoter selects other than the Mobile Phones
     * @param price
     * @param category
     * @throws Exception
     */
    public void downPaymentPercentageIfPromoterSelectsOtherThanMobilePhones_TDB_PW_015(String price,String category) throws Exception {
        HeaderChildNode("TDB_PW_015, SIL Purple App - Verify the down payment % if Promoter selects other than the Mobile Phones");
        purpleAppLogin(propertyFileReader("UserName", "SILPurpleApp"), propertyFileReader("Password", "SILPurpleApp"));
        selectMerchantAndNext(propertyFileReader("SelectMerchant", "SILPurpleApp"));
        verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen, getTextVal(SILPurpleAppPage.objQRGeneratorScreen, "Screen"));
        assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen), propertyFileReader("QRGeneratorScreen", "SILPurpleApp"), "Screen");
        addToCart(category, propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), price, "1");
        clearWebField(SILPurpleAppPage.objPrice("1"),"Price input field");
        addToCart(category, propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), price, "1");
        List<WebElement> values = findElements(SILPurpleAppPage.objAddOnRatePercentage);
        containsValidation(getText(SILPurpleAppPage.objTotalAmount).replaceAll(",",""),price,"Total amount");
        double actualDownPaymentMaximum =  Double.parseDouble(price)*(Double.parseDouble(propertyFileReader("DownPaymentPercentage"+values.size(),"SILPurpleApp"))/100);
        containsValidation(getText(SILPurpleAppPage.objActualDownPayment).replaceAll(",",""),"₱"+actualDownPaymentMaximum,"Actual Down Payment");
        verifyElementPresent(SILPurpleAppPage.objChangeAmount,"Edit Icon Actual down payment");
        double loanableAmountMaximum = Double.parseDouble(price)-actualDownPaymentMaximum;
        containsValidation(getText(SILPurpleAppPage.objLoanableAmount).replaceAll(",",""),"₱"+loanableAmountMaximum,"Loanable amount");
        containsValidation(getText(SILPurpleAppPage.objDownPaymentPercentage(values.size())),propertyFileReader("MinDownPaymentPercentageOtherThanMobile","SILPurpleApp"),"Minimum Rate charge percentage");
        for (int percentage = 1; percentage <=values.size(); percentage++) {
            containsValidation(getText(SILPurpleAppPage.objAddOnRatePercentage(percentage)),propertyFileReader("AddOnRatePercentage"+percentage,"SILPurpleApp")+"%","Monthly Add-on Rate");
            containsValidation(getText(SILPurpleAppPage.objDownPaymentPercentage(percentage)),propertyFileReader("DownPaymentPercentage"+percentage,"SILPurpleApp")+"%","Rate charge percentage");
            String amount = String.format("%.2f",amountBasedOnPercentage(Double.parseDouble(price),Float.parseFloat(propertyFileReader("DownPaymentPercentage"+percentage,"SILPurpleApp"))));
            containsValidation(getText(SILPurpleAppPage.objDownPaymentAmount(percentage)).replaceAll(",",""),"₱"+amount,"Down payment amount");
        }
        logger.info("TDB_PW_015, SIL Purple App - Minimum down payment % if Promoter selects other than the Mobile Phones is 10% validated");
        extentLoggerPass("TDB_PW_015", "TDB_PW_015, SIL Purple App - Minimum down payment % if Promoter selects other than the Mobile Phones is 10% validated");
    }
    /**
     * Method to Verify the down payment % if Promoter selects both Mobile Phones & other items
     * @param price1
     * @param price2
     * @throws Exception
     */
    public void downPaymentPercentageIfPromoterSelectsBothMobilePhonesAndOtherItems_TDB_PW_016(String price1,String price2) throws Exception {
        HeaderChildNode("TDB_PW_016, SIL Purple App - Verify the down payment % if Promoter selects both Mobile Phones & other items");
        purpleAppLogin(propertyFileReader("UserName", "SILPurpleApp"), propertyFileReader("Password", "SILPurpleApp"));
        selectMerchantAndNext(propertyFileReader("SelectMerchant", "SILPurpleApp"));
        verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen, getTextVal(SILPurpleAppPage.objQRGeneratorScreen, "Screen"));
        assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen), propertyFileReader("QRGeneratorScreen", "SILPurpleApp"), "Screen");
        addToCart(propertyFileReader("Category1", "SILPurpleApp"), propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), price1, "1");
        jsClick(SILPurpleAppPage.objAddMoreItem, getTextVal(SILPurpleAppPage.objAddMoreItem, "Button"));
        addToCart(propertyFileReader("Category2", "SILPurpleApp"), propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), price2, "2");
        List<WebElement> values = findElements(SILPurpleAppPage.objAddOnRatePercentage);
        String totalAmountCalculated = String.valueOf(Double.parseDouble(price1)+Double.parseDouble(price2));
        containsValidation(getText(SILPurpleAppPage.objDownPaymentPercentage(values.size())),propertyFileReader("MinDownPaymentPercentageForMobile","SILPurpleApp"),"Minimum Rate charge percentage");
        containsValidation(getText(SILPurpleAppPage.objTotalAmount).replaceAll(",",""),totalAmountCalculated,"Total amount");
        double actualDownPaymentMaximum =  Double.parseDouble(totalAmountCalculated)*(Double.parseDouble(propertyFileReader("DownPaymentPercentage"+values.size(),"SILPurpleApp"))/100);
        containsValidation(getText(SILPurpleAppPage.objActualDownPayment).replaceAll(",",""),"₱"+actualDownPaymentMaximum,"Actual Down Payment");
        verifyElementPresent(SILPurpleAppPage.objChangeAmount,"Edit Icon Actual down payment");
        double loanableAmountMaximum = Double.parseDouble(totalAmountCalculated)-actualDownPaymentMaximum;
        containsValidation(getText(SILPurpleAppPage.objLoanableAmount).replaceAll(",",""),"₱"+loanableAmountMaximum,"Loanable amount");
        for (int percentage = 1; percentage <=values.size(); percentage++) {
            containsValidation(getText(SILPurpleAppPage.objAddOnRatePercentage(percentage)),propertyFileReader("AddOnRatePercentage"+percentage,"SILPurpleApp")+"%","Monthly Add-on Rate");
            containsValidation(getText(SILPurpleAppPage.objDownPaymentPercentage(percentage)),propertyFileReader("DownPaymentPercentage"+percentage,"SILPurpleApp")+"%","Rate charge percentage");
            String amount = String.format("%.2f",amountBasedOnPercentage(Double.parseDouble(totalAmountCalculated),Float.parseFloat(propertyFileReader("DownPaymentPercentage"+percentage,"SILPurpleApp"))));
            containsValidation(getText(SILPurpleAppPage.objDownPaymentAmount(percentage)).replaceAll(",",""),"₱"+amount,"Down payment amount");
        }
        logger.info("TDB_PW_016, SIL Purple App - Minimum down payment % if Promoter selects both Mobile Phones & other items is 30% validated");
        extentLoggerPass("TDB_PW_016", "TDB_PW_016, SIL Purple App - Minimum down payment % if Promoter selects both Mobile Phones & other items is 30% validated");
    }
    /**
     * Method to Verify the minimum & maximum Loanable amount
     * @param price1
     * @param price2
     * @throws Exception
     */
    public void minimumAndMaximumLoanableAmountValidation_TDB_PW_017(String price1,String price2) throws Exception {
        HeaderChildNode("TDB_PW_017, SIL Purple App - Verify the minimum & maximum Loanable amount");
        purpleAppLogin(propertyFileReader("UserName", "SILPurpleApp"), propertyFileReader("Password", "SILPurpleApp"));
        selectMerchantAndNext(propertyFileReader("SelectMerchant", "SILPurpleApp"));
        verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen, getTextVal(SILPurpleAppPage.objQRGeneratorScreen, "Screen"));
        assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen), propertyFileReader("QRGeneratorScreen", "SILPurpleApp"), "Screen");
        addToCart(propertyFileReader("Category2", "SILPurpleApp"), propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), price1, "1");
        if(verifyElementDisplayed(SILPurpleAppPage.objAddOnRate)){
            logger.info("Add-on rate should be displayed if Loanable amount is less than 5K");
            extentLoggerFail("TDB_PW_017","Add-on rate should be displayed if Loanable amount is less than 5K");
        }else{
            logger.info("Add-on rate should not be displayed if Loanable amount is less than 5K");
            extentLoggerPass("TDB_PW_017","Add-on rate should not be displayed if Loanable amount is less than 5K");
        }
        if(verifyElementDisplayed(SILPurpleAppPage.objRateChartOptions)){
            logger.info("Rate chart should be displayed if Loanable amount is less than 5K");
            extentLoggerFail("TDB_PW_017","Rate chart should be displayed if Loanable amount is less than 5K");
        }else{
            logger.info("Rate chart should not be displayed if Loanable amount is less than 5K");
            extentLoggerPass("TDB_PW_017","Rate chart should not be displayed if Loanable amount is less than 5K");
        }
        clearWebField(SILPurpleAppPage.objPrice("1"),"Price Input field");
        addToCart(propertyFileReader("Category2", "SILPurpleApp"), propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), price2, "1");
        if(verifyElementPresent(SILPurpleAppPage.objAddOnRate,getTextVal(SILPurpleAppPage.objAddOnRate,"Field name"))){
            List<WebElement> values = findElements(SILPurpleAppPage.objAddOnRatePercentage);
            containsValidation(getText(SILPurpleAppPage.objTotalAmount).replaceAll(",",""),price2,"Total amount");
            verifyElementPresent(SILPurpleAppPage.objChangeAmount,"Edit Icon Actual down payment");
            String downPayment = calculateDownPayment(price2, values.size());
            containsValidation(getText(SILPurpleAppPage.objDownPaymentPercentage(values.size())),propertyFileReader("DownPaymentPercentage"+values.size() ,"SILPurpleApp")+"%","Minimum Rate charge percentage");
            containsValidation(getText(SILPurpleAppPage.objLoanableAmount).replaceAll(",", ""),"₱"+calculateLoanableAmount(price2,downPayment),"Loanable amount");
            for (int percentage = 1; percentage <=values.size(); percentage++) {
                containsValidation(getText(SILPurpleAppPage.objAddOnRatePercentage(percentage)),propertyFileReader("AddOnRatePercentage"+percentage,"SILPurpleApp")+"%","Monthly Add-on Rate");
                containsValidation(getText(SILPurpleAppPage.objDownPaymentPercentage(percentage)),propertyFileReader("DownPaymentPercentage"+percentage,"SILPurpleApp")+"%","Rate charge percentage");
                String amount = String.format("%.2f",amountBasedOnPercentage(Double.parseDouble(price2),Float.parseFloat(propertyFileReader("DownPaymentPercentage"+percentage,"SILPurpleApp"))));
                containsValidation(getText(SILPurpleAppPage.objDownPaymentAmount(percentage)).replaceAll(",",""),"₱"+amount,"Down payment amount");
            }
        }
        logger.info("TDB_PW_017, SIL Purple App - Minimum & maximum Loanable amount validated");
        extentLoggerPass("TDB_PW_017", "TDB_PW_017, SIL Purple App - Minimum & maximum Loanable amount validated validated");
    }
    /**
     * Method to Verify the promoter can't decrease the down payment amount
     * @param price
     * @throws Exception
     */
    public void verifyPromoterCanAbleToDecreaseDownPaymentOrNotValidation_TDB_PW_018(String price) throws Exception {
        HeaderChildNode("TDB_PW_018, SIL Purple App - Verify the promoter can't decrease the down payment amount");
        purpleAppLogin(propertyFileReader("UserName", "SILPurpleApp"), propertyFileReader("Password", "SILPurpleApp"));
        selectMerchantAndNext(propertyFileReader("SelectMerchant", "SILPurpleApp"));
        verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen, getTextVal(SILPurpleAppPage.objQRGeneratorScreen, "Screen"));
        assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen), propertyFileReader("QRGeneratorScreen", "SILPurpleApp"), "Screen");
        addToCart(propertyFileReader("Category2", "SILPurpleApp"), propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), price, "1");
        clearWebField(SILPurpleAppPage.objPrice("1"),"Price Input field");
        addToCart(propertyFileReader("Category2", "SILPurpleApp"), propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), price, "1");
        scrollByWEB();
        Double minimumDownPayment = Double.parseDouble(calculateDownPayment(price,1));
        jsClick(SILPurpleAppPage.objChangeAmount,"Edit Icon Actual down payment");
        applyNowPopup((int) (minimumDownPayment-1));
        isButtonDisabled(SILPurpleAppPage.objApplyNowBtn,getTextVal(SILPurpleAppPage.objApplyNowBtn,""));
        logger.info("TDB_PW_018, SIL Purple App - Promoter can't decrease the down payment amount validated");
        extentLoggerPass("TDB_PW_018", "TDB_PW_018, SIL Purple App - Promoter can't decrease the down payment amount validated");
    }
    /**
     * Method to Verify the promoter can't input the same down payment amount
     * @param price
     * @throws Exception
     */
    public void verifyPromoterCanAbleToSameDownPaymentOrNotValidation_TDB_PW_019(String price) throws Exception {
        HeaderChildNode("TDB_PW_019, SIL Purple App - Verify the promoter can't input the same down payment amount");
        purpleAppLogin(propertyFileReader("UserName", "SILPurpleApp"), propertyFileReader("Password", "SILPurpleApp"));
        selectMerchantAndNext(propertyFileReader("SelectMerchant", "SILPurpleApp"));
        verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen, getTextVal(SILPurpleAppPage.objQRGeneratorScreen, "Screen"));
        assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen), propertyFileReader("QRGeneratorScreen", "SILPurpleApp"), "Screen");
        addToCart(propertyFileReader("Category2", "SILPurpleApp"), propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), price, "1");
        clearWebField(SILPurpleAppPage.objPrice("1"),"Price Input field");
        addToCart(propertyFileReader("Category2", "SILPurpleApp"), propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), price, "1");
        scrollByWEB();
        Double minimumDownPayment = Double.parseDouble(calculateDownPayment(price, 1));
        jsClick(SILPurpleAppPage.objChangeAmount, "Edit Icon Actual down payment");
        int downPayment = (int) Math.round(minimumDownPayment);
        applyNowPopup(downPayment);
        isButtonDisabled(SILPurpleAppPage.objApplyNowBtn,getTextVal(SILPurpleAppPage.objApplyNowBtn,""));
        logger.info("TDB_PW_019, SIL Purple App - Promoter can't input the same down payment amount validated");
        extentLoggerPass("TDB_PW_019", "TDB_PW_019, SIL Purple App - Promoter can't input the same down payment amount validated");
    }
    /**
     * Method to Verify if Promoter can Close the Down payment window
     * @param price
     * @throws Exception
     */
    public void verifyPromoterCanCloseDownPaymentWindowValidation_TDB_PW_021(String price) throws Exception {
        HeaderChildNode("TDB_PW_021, SIL Purple App - Verify if Promoter can Close the Down payment window");
        purpleAppLogin(propertyFileReader("UserName", "SILPurpleApp"), propertyFileReader("Password", "SILPurpleApp"));
        selectMerchantAndNext(propertyFileReader("SelectMerchant", "SILPurpleApp"));
        verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen, getTextVal(SILPurpleAppPage.objQRGeneratorScreen, "Screen"));
        assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen), propertyFileReader("QRGeneratorScreen", "SILPurpleApp"), "Screen");
        addToCart(propertyFileReader("Category2", "SILPurpleApp"), propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), price, "1");
        clearWebField(SILPurpleAppPage.objPrice("1"),"Price Input field");
        addToCart(propertyFileReader("Category2", "SILPurpleApp"), propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), price, "1");
        scrollByWEB();
        Double minimumDownPayment = Double.parseDouble(calculateDownPayment(price,1));
        jsClick(SILPurpleAppPage.objChangeAmount,"Edit Icon Actual down payment");
        int downPayment = (int) Math.round(minimumDownPayment);
        applyNowPopup(downPayment+1);
        jsClick(SILPurpleAppPage.objCloseIcon,"Close Icon");
        verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen, getTextVal(SILPurpleAppPage.objQRGeneratorScreen, "Screen"));
        assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen), propertyFileReader("QRGeneratorScreen", "SILPurpleApp"), "Screen");
        logger.info("TDB_PW_021, SIL Purple App - Promoter can Close the Down payment window validated");
        extentLoggerPass("TDB_PW_021", "TDB_PW_021, SIL Purple App - Promoter can Close the Down payment window validated");
    }
    /**
     * Method to Verify the Paano gumagana ang PayHinga at bakit kailangan ni client ito?
     * @param price
     * @throws Exception
     */
    public void verifyPaanoGumaganaAngPayHingaAtBakitKailanganNiClientItoHyperLinkValidationValidation_TDB_PW_022(String price) throws Exception {
        HeaderChildNode("TDB_PW_022, SIL Purple App - Verify the Paano gumagana ang PayHinga at bakit kailangan ni client ito? hyperlink link validation");
        purpleAppLogin(propertyFileReader("UserName", "SILPurpleApp"), propertyFileReader("Password", "SILPurpleApp"));
        selectMerchantAndNext(propertyFileReader("SelectMerchant", "SILPurpleApp"));
        verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen, getTextVal(SILPurpleAppPage.objQRGeneratorScreen, "Screen"));
        assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen), propertyFileReader("QRGeneratorScreen", "SILPurpleApp"), "Screen");
        addToCart(propertyFileReader("Category2", "SILPurpleApp"), propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), price, "1");
        clearWebField(SILPurpleAppPage.objPrice("1"), "Price Input field");
        addToCart(propertyFileReader("Category2", "SILPurpleApp"), propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), price, "1");
        scrollDownWEB();
        scrollDownWEB();
        JSClick(SILPurpleAppPage.objInstallmentTermsSubtitleTwo,"Paano gumagana ang PayHinga at bakit kailangan ni client ito?");
        if(verifyElementPresent(SILPurpleAppPage.objTonikLogo,"Tonik Logo")){
            assertionValidation(getText(SILPurpleAppPage.objAddOnServiceHeader),propertyFileReader("AddOnServiceHeader","SILPurpleApp"),"Header");
            assertionValidation(getText(SILPurpleAppPage.objGuideHeader),propertyFileReader("GuideHeader","SILPurpleApp"),"Header");
            assertionValidation(getText(SILPurpleAppPage.objGuideHeader),propertyFileReader("GuideHeader","SILPurpleApp"),"Header");
            assertionValidation(getText(SILPurpleAppPage.objWhyShouldIGetItHeader),propertyFileReader("WhyShouldIGetItHeader","SILPurpleApp"),"Header");
            List<WebElement> values = findElements(SILPurpleAppPage.objWhyShouldIGetItInfo);
            for(int info=1;info<=values.size();info++){
                assertionValidation(getText(SILPurpleAppPage.objWhyShouldIGetItInfo(info)),propertyFileReader("WhyShouldIGetItInfo"+info,"SILPurpleApp"),"Why Should I Get It Info");
            }
            assertionValidation(getText(SILPurpleAppPage.objWhoCanGetItHeader),propertyFileReader("WhoCanGetItHeader","SILPurpleApp"),"Header");
            assertionValidation(getText(SILPurpleAppPage.objWhoCanGetItSubHeader),propertyFileReader("WhoCanGetItSubHeader","SILPurpleApp"),"Sub Header");
            List<WebElement> values2 = findElements(SILPurpleAppPage.objWhoCanGetItInfo);
            for(int info=1;info<=values2.size();info++){
                assertionValidation(getText(SILPurpleAppPage.objWhoCanGetItInfo(info)),propertyFileReader("WhoCanGetItInfo"+info,"SILPurpleApp"),"Who Can Get It Info");
            }
            List<WebElement> values3 = findElements(SILPurpleAppPage.objLimitation);
            for(int info=1;info<=values3.size();info++){
                containsValidation(getText(SILPurpleAppPage.objLimitation(info)),propertyFileReader("Limitation"+info,"SILPurpleApp"),": Limitation");
            }
            assertionValidation(getText(SILPurpleAppPage.objInsuranceInfo),propertyFileReader("InsuranceInfo","SILPurpleApp"),"Insurance info");
                assertionValidation(getText(SILPurpleAppPage.objWhoCanNotGetIt).replaceAll("’",""),propertyFileReader("WhoCanNotGetItHeader","SILPurpleApp"),"Header");
            List<WebElement> values4 = findElements(SILPurpleAppPage.objWhoCanNotGetItInfo);
            for(int info=1;info<=values4.size();info++){
                assertionValidation(getText(SILPurpleAppPage.objWhoCanNotGetItInfo(info)),propertyFileReader("WhoCanNotGetItInfo"+info,"SILPurpleApp"),"Who Can Not Get It Info");
            }
            containsValidation(getText(SILPurpleAppPage.objRemainder),propertyFileReader("Remainder","SILPurpleApp"),"Remainder Note");
            verifyElementPresentAndClick(SILPurpleAppPage.objPopupCloseBtn,"Close Icon");
            scrollToTopOfPageWEB();
            assertionValidation(getAttributValue("value",SILPurpleAppPage.objPrice).replaceAll(",",""), "₱"+price, "Price input field");
            logger.info("TDB_PW_022, SIL Purple App - Paano gumagana ang PayHinga at bakit kailangan ni client ito? hyper link validated");
            extentLoggerPass("TDB_PW_022", "TDB_PW_022, SIL Purple App - Paano gumagana ang PayHinga at bakit kailangan ni client ito? hyper link validated");
        }
    }
    /**
     * Method to Verify if Promoter can select an Installment term
     * @param price
     * @throws Exception
     */
    public void selectInstallmentTermsGenerateCodeBtnValidation_TDB_PW_023(String price) throws Exception {
        HeaderChildNode("TDB_PW_023, SIL Purple App - Verify if Promoter can select an Installment term and Generate code button validation");
        purpleAppLogin(propertyFileReader("UserName", "SILPurpleApp"), propertyFileReader("Password", "SILPurpleApp"));
        selectMerchantAndNext(propertyFileReader("SelectMerchant", "SILPurpleApp"));
        verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen, getTextVal(SILPurpleAppPage.objQRGeneratorScreen, "Screen"));
        assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen), propertyFileReader("QRGeneratorScreen", "SILPurpleApp"), "Screen");
        addToCart(propertyFileReader("Category4", "SILPurpleApp"), propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), price, "1");
        clearWebField(SILPurpleAppPage.objPrice("1"), "Price input field");
        addToCart(propertyFileReader("Category4", "SILPurpleApp"), "", "", price, "1");
        scrollDownWEB();
        List<WebElement> rates = findElements(SILPurpleAppPage.objAddOnRatePercentage);
        int highLightedDownPayment = rates.size();
        String downPaymentAmount = String.format("%.2f",amountBasedOnPercentage(Double.parseDouble(price),Float.parseFloat(propertyFileReader("DownPaymentPercentage"+highLightedDownPayment,"SILPurpleApp"))));
        double loanableAmount = Double.parseDouble(price)-Double.parseDouble(downPaymentAmount);
        scrollToBottomOfPageWEB();
        waitTime(3000);//Added hard to click on Installment term
        List<WebElement> values = findElements(SILPurpleAppPage.objWithOutPayHingaMonths);
        for (int month = 1; month <=values.size(); month++) {
            assertionValidation(getText(SILPurpleAppPage.objWithOutPayHingaMonths(month)),propertyFileReader("Month"+month,"SILPurpleApp"),"Without PayHinga EMI months");
            int tenure=getIntegerFromString(getText(SILPurpleAppPage.objWithOutPayHingaMonths(month)));
            String emi= String.format("%.2f",calculateEMI(Float.parseFloat(propertyFileReader("MonthlyInterestRate"+month,"SILPurpleApp")),tenure,loanableAmount));
            containsValidation(getText(SILPurpleAppPage.objWithOutPayHingaEMI(month)).replaceAll(",",""),"₱"+emi,"EMI Amount without PayHinga");
            click(SILPurpleAppPage.objWithOutPayHingaMonths(month),getTextVal(SILPurpleAppPage.objWithOutPayHingaMonths(month),"Without PayHinga Installment"));
            isButtonEnabled(SILPurpleAppPage.objGenerateQRCode,"Generate QR Code");
            assertionValidation(getText(SILPurpleAppPage.objWithPayHingaMonths(month)),propertyFileReader("Month"+month,"SILPurpleApp"),"With PayHinga EMI months");
            containsValidation(getText(SILPurpleAppPage.objWithPayHingaEMI(month)).replaceAll(",",""),
                    "₱"+getPayHingaEMI(Double.parseDouble(emi),Double.parseDouble(propertyFileReader("WithPayHingaInterest","SILPurpleApp"))),"With PayHinga EMI Amount");
            click(SILPurpleAppPage.objWithPayHingaMonths(month),getTextVal(SILPurpleAppPage.objWithPayHingaMonths(month),"With PayHinga Installment"));
            isButtonEnabled(SILPurpleAppPage.objGenerateQRCode,"Generate QR Code");
        }
        logger.info("TDB_PW_023, SIL Purple App - Promoter can select an Installment term and Generate code button validated");
        extentLoggerPass("TDB_PW_023", "TDB_PW_023, SIL Purple App - Promoter can select an Installment term and Generate code button validated");
    }
    /**
     * Method to Verify is Promoter can the generate QR when selecting a code 0
     * @param price
     * @param assessmentCode
     * @throws Exception
     */
    public void verifyPromoterCanGenerateQRCode0Validation_TDB_PW_024(String price,By assessmentCode) throws Exception {
        HeaderChildNode("TDB_PW_024, SIL Purple App - Verify is Promoter can the generate QR when selecting a code 0");
        purpleAppLogin(propertyFileReader("UserName", "SILPurpleApp"), propertyFileReader("Password", "SILPurpleApp"));
        selectMerchantAndNext(propertyFileReader("SelectMerchant", "SILPurpleApp"));
        verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen, getTextVal(SILPurpleAppPage.objQRGeneratorScreen, "Screen"));
        assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen), propertyFileReader("QRGeneratorScreen", "SILPurpleApp"), "Screen");
        addToCart(propertyFileReader("Category4", "SILPurpleApp"), propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), price, "1");
        clearWebField(SILPurpleAppPage.objPrice("1"), "Price input field");
        addToCart(propertyFileReader("Category4", "SILPurpleApp"), "", "", price, "1");
        scrollToBottomOfPageWEB();
        waitTime(3000);//Added hard to click on Installment term
        List<WebElement> values = findElements(SILPurpleAppPage.objWithOutPayHingaMonths);
        click(SILPurpleAppPage.objWithOutPayHingaMonths(values.size()),getTextVal(SILPurpleAppPage.objWithOutPayHingaMonths(values.size()),"EMI month"));
        click(SILPurpleAppPage.objGenerateQRCode,getTextVal(SILPurpleAppPage.objGenerateQRCode,"Button"));
        fewHouseRule(assessmentCode,getTextVal(assessmentCode,"Assessment Code"));
        if(verifyElementPresent(SILPurpleAppPage.objQRCode,getTextVal(SILPurpleAppPage.objQRCode,"Screen"))){
            assertionValidation(getText(SILPurpleAppPage.objQRCode),propertyFileReader("QRCodeScreen","SILPurpleApp"),"Screen");
            logger.info("TDB_PW_024, SIL Purple App - Promoter can the generate QR when selecting a code 0 validated");
            extentLoggerPass("TDB_PW_024", "TDB_PW_024, SIL Purple App - Promoter can the generate QR when selecting a code 0 validated");
        }
    }
    /**
     * Method to Verify is Promoter can the generate QR when selecting a code 1
     * @param price
     * @param assessmentCode
     * @throws Exception
     */
    public void verifyPromoterCanGenerateQRCode1Validation_TDB_PW_025(String price,By assessmentCode) throws Exception {
        HeaderChildNode("TDB_PW_025, SIL Purple App - Verify is Promoter can the generate QR when selecting a code 1");
        purpleAppLogin(propertyFileReader("UserName", "SILPurpleApp"), propertyFileReader("Password", "SILPurpleApp"));
        selectMerchantAndNext(propertyFileReader("SelectMerchant", "SILPurpleApp"));
        verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen, getTextVal(SILPurpleAppPage.objQRGeneratorScreen, "Screen"));
        assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen), propertyFileReader("QRGeneratorScreen", "SILPurpleApp"), "Screen");
        addToCart(propertyFileReader("Category4", "SILPurpleApp"), propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), price, "1");
        clearWebField(SILPurpleAppPage.objPrice("1"), "Price input field");
        addToCart(propertyFileReader("Category4", "SILPurpleApp"), "", "", price, "1");
        scrollToBottomOfPageWEB();
        waitTime(3000);//Added hard to click on Installment term
        List<WebElement> values = findElements(SILPurpleAppPage.objWithOutPayHingaMonths);
        click(SILPurpleAppPage.objWithOutPayHingaMonths(values.size()),getTextVal(SILPurpleAppPage.objWithOutPayHingaMonths(values.size()),"EMI month"));
        click(SILPurpleAppPage.objGenerateQRCode,getTextVal(SILPurpleAppPage.objGenerateQRCode,"Button"));
        fewHouseRule(assessmentCode,getTextVal(assessmentCode,"Assessment Code"));
        if(verifyElementPresent(SILPurpleAppPage.objQRCode,getTextVal(SILPurpleAppPage.objQRCode,"Screen"))){
            assertionValidation(getText(SILPurpleAppPage.objQRCode),propertyFileReader("QRCodeScreen","SILPurpleApp"),"Screen");
            logger.info("TDB_PW_025, SIL Purple App - Promoter can the generate QR when selecting a code 1 validated");
            extentLoggerPass("TDB_PW_025", "TDB_PW_025, SIL Purple App - Promoter can the generate QR when selecting a code 1 validated");
        }
    }
    /**
     * Method to Verify is Promoter can the generate QR when selecting a code 2
     * @param price
     * @param assessmentCode
     * @throws Exception
     */
    public void verifyPromoterCanGenerateQRCode2Validation_TDB_PW_026(String price,By assessmentCode) throws Exception {
        HeaderChildNode("TDB_PW_026, SIL Purple App - Verify is Promoter can the generate QR when selecting a code 2");
        purpleAppLogin(propertyFileReader("UserName", "SILPurpleApp"), propertyFileReader("Password", "SILPurpleApp"));
        selectMerchantAndNext(propertyFileReader("SelectMerchant", "SILPurpleApp"));
        verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen, getTextVal(SILPurpleAppPage.objQRGeneratorScreen, "Screen"));
        assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen), propertyFileReader("QRGeneratorScreen", "SILPurpleApp"), "Screen");
        addToCart(propertyFileReader("Category4", "SILPurpleApp"), propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), price, "1");
        clearWebField(SILPurpleAppPage.objPrice("1"), "Price input field");
        addToCart(propertyFileReader("Category4", "SILPurpleApp"), "", "", price, "1");
        scrollToBottomOfPageWEB();
        waitTime(3000);//Added hard to click on Installment term
        List<WebElement> values = findElements(SILPurpleAppPage.objWithOutPayHingaMonths);
        click(SILPurpleAppPage.objWithOutPayHingaMonths(values.size()),getTextVal(SILPurpleAppPage.objWithOutPayHingaMonths(values.size()),"EMI month"));
        click(SILPurpleAppPage.objGenerateQRCode,getTextVal(SILPurpleAppPage.objGenerateQRCode,"Button"));
        fewHouseRule(assessmentCode,getTextVal(assessmentCode,"Assessment Code"));
        if(verifyElementPresent(SILPurpleAppPage.objQRCode,getTextVal(SILPurpleAppPage.objQRCode,"Screen"))){
            assertionValidation(getText(SILPurpleAppPage.objQRCode),propertyFileReader("QRCodeScreen","SILPurpleApp"),"Screen");
            logger.info("TDB_PW_026, SIL Purple App - Promoter can the generate QR when selecting a code 2 validated");
            extentLoggerPass("TDB_PW_026", "TDB_PW_026, SIL Purple App - Promoter can the generate QR when selecting a code 2 validated");
        }
    }
    /**
     * Method to Verify the promoter can generate the QR code without PayHinga
     * @param price
     * @param assessmentCode
     * @throws Exception
     */
    public void verifyPromoterCanGenerateQRCodeWithoutPayHingaValidation_TDB_PW_028(String price,By assessmentCode) throws Exception {
        HeaderChildNode("TDB_PW_028, SIL Purple App - Verify the promoter can generate the QR code without PayHinga");
        purpleAppLogin(propertyFileReader("UserName", "SILPurpleApp"), propertyFileReader("Password", "SILPurpleApp"));
        selectMerchantAndNext(propertyFileReader("SelectMerchant", "SILPurpleApp"));
        verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen, getTextVal(SILPurpleAppPage.objQRGeneratorScreen, "Screen"));
        assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen), propertyFileReader("QRGeneratorScreen", "SILPurpleApp"), "Screen");
        addToCart(propertyFileReader("Category4", "SILPurpleApp"), propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), price, "1");
        clearWebField(SILPurpleAppPage.objPrice("1"), "Price input field");
        addToCart(propertyFileReader("Category4", "SILPurpleApp"), "", "", price, "1");
        scrollByWEB();
        List<WebElement> rates = findElements(SILPurpleAppPage.objRateChartList);
        String addOnRate = getText(SILPurpleAppPage.objAddOnRatePercentage(rates.size()));
        String downPayment = calculateDownPayment(price, 2);
        String loanableAmount = calculateLoanableAmount(price,downPayment);
        scrollToBottomOfPageWEB();
        waitTime(5000);//Wait included to Click on EMI month
        String emi=selectEMI("WithoutVAS",propertyFileReader("EMIMonth","SILPurpleApp"));
        click(SILPurpleAppPage.objGenerateQRCode,getTextVal(SILPurpleAppPage.objGenerateQRCode,"Button"));
        fewHouseRule(assessmentCode,getTextVal(assessmentCode,"Assessment Code"));
        qrCodeScreenValidation(price,downPayment,loanableAmount,emi,addOnRate);
        verifyElementPresentAndClick(SILPurpleAppPage.objDoneBtn,getTextVal(SILPurpleAppPage.objDoneBtn,"Button"));
        if(verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen,getTextVal(SILPurpleAppPage.objQRGeneratorScreen,"Screen"))){
            assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen),propertyFileReader("QRGeneratorScreen","SILPurpleApp"),"Screen");
            logger.info("TDB_PW_028, SIL Purple App - Promoter can generate the QR code without PayHinga validated");
            extentLoggerPass("TDB_PW_028", "TDB_PW_028, SIL Purple App -  Promoter can generate the QR code without PayHinga validated");
        }
    }
    /**
     * Method to Verify the promoter can generate the QR code with PayHinga
     * @param price
     * @param assessmentCode
     * @throws Exception
     */
    public void verifyPromoterCanGenerateQRCodeWithPayHingaValidation_TDB_PW_029(String price,By assessmentCode) throws Exception {
        HeaderChildNode("TDB_PW_029, SIL Purple App - Verify the promoter can generate the QR code with PayHinga");
        purpleAppLogin(propertyFileReader("UserName", "SILPurpleApp"), propertyFileReader("Password", "SILPurpleApp"));
        selectMerchantAndNext(propertyFileReader("SelectMerchant", "SILPurpleApp"));
        verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen, getTextVal(SILPurpleAppPage.objQRGeneratorScreen, "Screen"));
        assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen), propertyFileReader("QRGeneratorScreen", "SILPurpleApp"), "Screen");
        addToCart(propertyFileReader("Category4", "SILPurpleApp"), propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), price, "1");
        clearWebField(SILPurpleAppPage.objPrice("1"), "Price input field");
        addToCart(propertyFileReader("Category4", "SILPurpleApp"), "", "", price, "1");
        scrollByWEB();
        List<WebElement> rates = findElements(SILPurpleAppPage.objRateChartList);
        String addOnRate = getText(SILPurpleAppPage.objAddOnRatePercentage(rates.size()));
        String downPayment = calculateDownPayment(price, 2);
        String loanableAmount = calculateLoanableAmount(price,downPayment);
        scrollToBottomOfPageWEB();
        waitTime(5000);//Wait included to Click on EMI month
        String emi=selectEMI("WithVAS",propertyFileReader("EMIMonth","SILPurpleApp"));
        click(SILPurpleAppPage.objGenerateQRCode,getTextVal(SILPurpleAppPage.objGenerateQRCode,"Button"));
        fewHouseRule(assessmentCode,getTextVal(assessmentCode,"Assessment Code"));
        qrCodeScreenValidation(price, downPayment, loanableAmount, emi, addOnRate);
        verifyElementPresentAndClick(SILPurpleAppPage.objDoneBtn, getTextVal(SILPurpleAppPage.objDoneBtn, "Button"));
        if (verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen, getTextVal(SILPurpleAppPage.objQRGeneratorScreen, "Screen"))) {
            assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen), propertyFileReader("QRGeneratorScreen", "SILPurpleApp"), "Screen");
            logger.info("TDB_PW_029, SIL Purple App - Promoter can generate the QR code with PayHinga validated");
            extentLoggerPass("TDB_PW_029", "TDB_PW_029, SIL Purple App -  Promoter can generate the QR code with PayHinga validated");
        }
    }
    /**
     * Method to Verify if Promoter can Logout from generated QR Code screen
     * @param price
     * @param assessmentCode
     * @throws Exception
     */
    public void verifyPromoterCanLogOutFromQRCodeScreenValidation_TDB_PW_030(String price,By assessmentCode) throws Exception {
        HeaderChildNode("TDB_PW_030, SIL Purple App - Verify if Promoter can Logout from generated QR Code screen");
        purpleAppLogin(propertyFileReader("UserName", "SILPurpleApp"), propertyFileReader("Password", "SILPurpleApp"));
        selectMerchantAndNext(propertyFileReader("SelectMerchant", "SILPurpleApp"));
        verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen, getTextVal(SILPurpleAppPage.objQRGeneratorScreen, "Screen"));
        assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen), propertyFileReader("QRGeneratorScreen", "SILPurpleApp"), "Screen");
        addToCart(propertyFileReader("Category4", "SILPurpleApp"), propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), price, "1");
        clearWebField(SILPurpleAppPage.objPrice("1"), "Price input field");
        addToCart(propertyFileReader("Category4", "SILPurpleApp"), "", "", price, "1");
        scrollToBottomOfPageWEB();
        waitTime(3000);//Wait included to Click on EMI month
        selectEMI("WithVAS", propertyFileReader("EMIMonth", "SILPurpleApp"));
        click(SILPurpleAppPage.objGenerateQRCode, getTextVal(SILPurpleAppPage.objGenerateQRCode, "Button"));
        fewHouseRule(assessmentCode,getTextVal(assessmentCode,"Assessment Code"));
        verifyElementPresent(SILPurpleAppPage.objQRCode, getTextVal(SILPurpleAppPage.objQRCode, "Screen"));
        assertionValidation(getText(SILPurpleAppPage.objQRCode), propertyFileReader("QRCodeScreen", "SILPurpleApp"), "Screen");
        jsClick(SILPurpleAppPage.objLogOut,getTextVal(SILPurpleAppPage.objLogOut,"Button"));
        if(verifyElementPresent(SILPurpleAppPage.objWelcomeScreen,getTextVal(SILPurpleAppPage.objWelcomeScreen,"Screen"))) {
            assertionValidation(getText(SILPurpleAppPage.objWelcomeScreen), propertyFileReader("WelcomeScreenHeader", "SILPurpleApp"), "Screen");
            assertionValidation(getText(SILPurpleAppPage.objLoginScreenSubHeader), propertyFileReader("WelcomeScreenSubHeader", "SILPurpleApp"), "Screen");
            assertionValidation(getText(SILPurpleAppPage.objPurpleApp), propertyFileReader("PurpleAppTxt", "SILPurpleApp"), "text");
            logger.info("TDB_PW_030, SIL Purple App - Promoter can Logout from generated QR Code screen validated");
            extentLoggerPass("TDB_PW_030", "TDB_PW_030, SIL Purple App - Promoter can Logout from generated QR Code screen validated");
        }
    }
    /**
     * Method to Verify the PayHinga is not available for 3 months installment terms
     * @param price
     * @throws Exception
     */
    public void verifyPromoterCanLogOutFromQRCodeScreenValidation_TDB_PW_027(String price) throws Exception {
        HeaderChildNode("TDB_PW_027, SIL Purple App - Verify the PayHinga is not available for 3 months installment terms");
        purpleAppLogin(propertyFileReader("UserName", "SILPurpleApp"), propertyFileReader("Password", "SILPurpleApp"));
        selectMerchantAndNext(propertyFileReader("SelectMerchant", "SILPurpleApp"));
        verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen, getTextVal(SILPurpleAppPage.objQRGeneratorScreen, "Screen"));
        assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen), propertyFileReader("QRGeneratorScreen", "SILPurpleApp"), "Screen");
        addToCart(propertyFileReader("Category4", "SILPurpleApp"), propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), price, "1");
        clearWebField(SILPurpleAppPage.objPrice("1"), "Price input field");
        addToCart(propertyFileReader("Category4", "SILPurpleApp"), "", "", price, "1");
        scrollToBottomOfPageWEB();
        assertionValidation(getText(SILPurpleAppPage.objInstallmentTerms),propertyFileReader("InstallmentTermsHeader","SILPurpleApp"),"Header");
        List<WebElement> values = findElements(SILPurpleAppPage.objWithOutPayHingaMonths);
        for (int month = 1; month <=values.size(); month++) {
            verifyElementPresent(SILPurpleAppPage.objWithOutPayHingaMonths(month),getTextVal(SILPurpleAppPage.objWithOutPayHingaMonths(month),"Without PayHinga Month"));
            verifyElementPresent(SILPurpleAppPage.objWithOutPayHingaEMI(month),getTextVal(SILPurpleAppPage.objWithOutPayHingaEMI(month),"Without PayHinga EMI Amount"));
        }
        List<WebElement> values2 = findElements(SILPurpleAppPage.objWithPayHingaMonths);
        for (int month = 1; month <=values2.size(); month++) {
            verifyElementPresent(SILPurpleAppPage.objWithPayHingaMonths(month),getTextVal(SILPurpleAppPage.objWithPayHingaMonths(month),"With PayHinga Month"));
            verifyElementPresent(SILPurpleAppPage.objWithPayHingaEMI(month),getTextVal(SILPurpleAppPage.objWithPayHingaEMI(month),"With PayHinga EMI Amount"));
        }
        if(verifyElementDisplayed(SILPurpleAppPage.objSelectWithVASEMIMonth("3"))){
            logger.error("PayHinga is available for 3 months installment terms");
            extentLoggerFail("","PayHinga is available for 3 months installment terms");
        }else {
            logger.error("PayHinga is not available for 3 months installment terms");
            extentLoggerPass("TDB_PW_027", "PayHinga is not available for 3 months installment terms");
        }
        logger.info("TDB_PW_027, SIL Purple App - PayHinga is not available for 3 months installment terms validated");
        extentLoggerPass("TDB_PW_027", "TDB_PW_027, SIL Purple App - PayHinga is not available for 3 months installment terms validated");
    }
    /**
     * Method to Verify if promoter can edit the actual down payment with valid amount
     * @param price
     * @throws Exception
     */
    public void rateChartValidationUponInputtingValidPriceAmount_TDB_PW_020(String price) throws Exception {
        HeaderChildNode("TDB_PW_020, SIL Purple App - Verify if promoter can edit the actual down payment with valid amount");
        purpleAppLogin(propertyFileReader("UserName", "SILPurpleApp"), propertyFileReader("Password", "SILPurpleApp"));
        selectMerchantAndNext(propertyFileReader("SelectMerchant", "SILPurpleApp"));
        verifyElementPresent(SILPurpleAppPage.objQRGeneratorScreen, getTextVal(SILPurpleAppPage.objQRGeneratorScreen, "Screen"));
        assertionValidation(getText(SILPurpleAppPage.objQRGeneratorScreen), propertyFileReader("QRGeneratorScreen", "SILPurpleApp"), "Screen");
        addToCart(propertyFileReader("Category2", "SILPurpleApp"), propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), price, "1");
        clearWebField(SILPurpleAppPage.objPrice("1"),"Price Input field");
        addToCart(propertyFileReader("Category2", "SILPurpleApp"), propertyFileReader("Brand", "SILPurpleApp"), propertyFileReader("SKU", "SILPurpleApp"), price, "1");
        scrollByWEB();
        List<WebElement> values = findElements(SILPurpleAppPage.objAddOnRatePercentage);
        containsValidation(getText(SILPurpleAppPage.objTotalAmount).replaceAll(",",""),price,"Total amount");
        verifyElementPresent(SILPurpleAppPage.objChangeAmount,"Edit Icon Actual down payment");
        String downPayment = calculateDownPayment(price, values.size());
        containsValidation(getText(SILPurpleAppPage.objDownPaymentPercentage(values.size())),propertyFileReader("DownPaymentPercentage"+values.size() ,"SILPurpleApp")+"%","Minimum Rate charge percentage");
        containsValidation(getText(SILPurpleAppPage.objLoanableAmount).replaceAll(",", ""),"₱"+calculateLoanableAmount(price,downPayment),"Loanable amount");
        for (int percentage = 1; percentage <values.size(); percentage++) {
            double downPaymentAmount = getAvailableBalanceInteger(SILPurpleAppPage.objDownPaymentAmount(values.size()-percentage));
            Integer inputDownPayment = Math.toIntExact(Math.round(downPaymentAmount));
            jsClick(SILPurpleAppPage.objChangeAmount,"Change amount button");
            applyNowPopup(inputDownPayment);
            scrollToElement(getWebDriver(),SILPurpleAppPage.objApplyNowBtn);
            click(SILPurpleAppPage.objApplyNowBtn,getTextVal(SILPurpleAppPage.objApplyNowBtn,"button"));
            containsValidation(getText(SILPurpleAppPage.objAddOnRateModified),getText(SILPurpleAppPage.objAddOnRatePercentage(values.size()-percentage)),"Modified Add on Rate");
            logger.info("TDB_PW_020, SIL Purple App - Promoter can edit the actual down payment with valid amount validated");
            extentLoggerPass("TDB_PW_020", "TDB_PW_020, SIL Purple App - Promoter can edit the actual down payment with valid amount validated");
        }
    }
}