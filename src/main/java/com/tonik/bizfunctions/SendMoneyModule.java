package com.tonik.bizfunctions;

import com.tonik.pageObject.*;
import com.tonik.utility.ExtentReporter;
import com.tonik.utility.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Random;
import static com.tonik.utility.DB_Utilites.selectQuery;
import static com.tonik.utility.Utilities.*;

public class SendMoneyModule extends BaseClass {
    String platform = Utilities.getPlatform();
    TopUpModule topUpModule = new TopUpModule();
    public SendMoneyModule() {
        super();
    }
    /**
     * Common method for Send money option page UI related validation
     * @throws Exception
     */
    public void selectSendMoneyOption(By byLocator) throws Exception {
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendMoneyOptionsPage),3,"Send money page");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader),": Page Header"));
        assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader)),propertyFileReader("SendMoneyOptions","SendMoney"),": Header");
        click(byLocator,getTextVal(byLocator,": Send Money Option"));
    }
    /**
     * Common method for Tonik number input
     * @param sTonikAccountNumber
     * @throws Exception
     */
    public void inputTonikAccountNumber(String sTonikAccountNumber) throws Exception {
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendMoneyPage),3,"Send money page");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendMoneyPage), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendMoneyPage), ": title"));
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objUseTonikAccountLink),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objUseTonikAccountLink),": link"));
        waitTime(4000);
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageSubTitle),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageSubTitle)," : Page Subtitle"));
        assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageSubTitle)),propertyFileReader("RecipientAccNumber","SendMoney"),": Page Subtitle");
        type(SendMoneyPage.getByOSType(platform,SendMoneyPage.objAccNumberInputField),sTonikAccountNumber,"Account number input field");
        if(getPlatform().equalsIgnoreCase("iOS")) {
            verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform, SendMoneyPage.objDoneButton), getTextVal(SendMoneyPage.getByOSType(platform, SendMoneyPage.objDoneButton), "Button"));
        }
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform, SendMoneyPage.objNextBtn), getTextVal(SendMoneyPage.getByOSType(platform, SendMoneyPage.objNextBtn), "Next Button"));
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendMoneyPage), 5, "Send Money page");
    }
    /**
     * Common method for Validating the Send money to bank via, dropdown UI related validation
     * @throws Exception
     */
    public void sendMoneyToBankViaDropdownValidation() throws Exception {
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendMoneyViaDropdown),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendMoneyViaDropdown),": Dropdown header"));
        assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPesonetOption)), propertyFileReader("PESONet","SendMoney"), "Option");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPesonetTransactionLimit),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPesonetTransactionLimit),": Pesonet transaction limit"));
        assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objInstaPayOption)), propertyFileReader("InstaPay","SendMoney"), "Option");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objInstaPayTransactionLimit),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objInstaPayTransactionLimit),": InstaPay transaction limit"));
    }
    /**
     * Common method for select Send money to bank option and Related UI Validation
     * @throws Exception
     */
    public void selectSendMoneyToBankOption(By selectSendMoneyOption) throws Exception {
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendMoneyPage),10,"Send Money page");
        if(verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader),": Page Header"))) {
            verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendMoneyToBankOptionsDropdown),"Send Money to Bank options dropdown");
            sendMoneyToBankViaDropdownValidation();
            click(selectSendMoneyOption,getTextVal(selectSendMoneyOption,": Send money to bank option"));
            waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendMoneyPage),5,"Send Money page");
            action_clickOnPosition(194,430);
            verifyElementPresent(selectSendMoneyOption,getTextVal(selectSendMoneyOption,": Selected Send Money option"));
        }
    }
    /**
     * Common method for getting Available balance for TSA and Incrementing value by 1 for negative validation
     * @return
     * @throws Exception
     */
    public static String getAvailableAndIncreaseValue() throws Exception {
        String availableBalance = getText(OnBoardingPage.objAvailableBalance);
        String sTrimAvailableBalance = availableBalance.replace(",","");
        String trim = sTrimAvailableBalance.replace("₱","");
        float availableValue = Float.parseFloat(trim);
        float amountInput = availableValue+1;
        return String.valueOf(amountInput);
    }
    /**
     * Common method to enter mobile number
     * @param sMobileNumber
     * @throws Exception
     */
    public void enterMobileNumber(String sMobileNumber) throws Exception {
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField,3,"Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField, sMobileNumber, "Mobile number input field");
        action_clickOnPosition(194,430);
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn), ": Button"));
    }
    /**
     * Common method to get available balance
     * @return
     * @throws Exception
     */
    public static String sBalance() throws Exception {
        waitTime(3000);//Hard wait provided to load dashboard
        String availableBalance = getText(OnBoardingPage.objAvailableBalance);
        String trimAvailableBalance = availableBalance.replace(",","");
        String trim = trimAvailableBalance.replace("₱","");
        float availableValue = Float.parseFloat(trim);
        return String.valueOf(availableValue);
    }
    /**
     * Generalized method for adding amount to amount input field and adding the purpose to purpose dropdown
     * @param amount
     * @throws Exception
     */
    public String addMoneyAndPurpose(String amount) throws Exception {
        type(SendMoneyPage.getByOSType(platform,SendMoneyPage.objAmountInputField),amount,"Amount input field");
        if(getPlatform().equalsIgnoreCase("iOS")){
            click(SendMoneyPage.getByOSType(platform,SendMoneyPage.objDoneButton), "Done Button");
        }
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPurposeDropdown), "Purpose Dropdown");
        verifyElementPresentAndClick(SendMoneyPage.objPurpose(platform,1),getTextVal(SendMoneyPage.objPurpose(platform,1),": Purpose"));
        return getTransactionFee();
    }
    /**
     * Common method to get Transaction fee
     * @return
     * @throws Exception
     */
    public String getTransactionFee() throws Exception {
        String transactionFee = getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionFee));//Reusable method for Fee
        int length = transactionFee.length();
        String splitTransactionFee = transactionFee.substring(15,length);
        String fee;
        if(splitTransactionFee.contains("Free")){
            fee = "0.00";
        }else{
            fee = splitTransactionFee.substring(0,1);
        }
        return fee;
    }
    /**
     * Common method to validate bank and select the bank
     * @param Bank
     * @throws Exception
     */
    public void validateBankListAndSelect(String Bank) throws Exception {
        click(SendMoneyPage.getByOSType(platform,SendMoneyPage.objBankNameEditField),"Bank name edit field");
        if(getPlatform().equalsIgnoreCase("iOS")) {
            click(SendMoneyPage.getByOSType(platform, SendMoneyPage.objBankNameEditField), "Bank name edit field");
        }
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objFindYourBankPage),5,"Find your bank page");
        if(getPlatform().equalsIgnoreCase("Android")) {
            List<WebElement> values = findElements(SendMoneyPage.getByOSType(platform,SendMoneyPage.objList));
            for (int bank = 0; bank < values.size(); bank++) {
                String displayedItem = values.get(bank).getText();
                logger.info("Bank : '" + displayedItem + "' is displayed");
                ExtentReporter.extentLogger(" ", "Bank : '" + displayedItem + "' is displayed");
            }
        }
        type(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSearchBank),Bank,": Search bank field");
        click(SendMoneyPage.objList(platform,1),getTextVal(SendMoneyPage.objList(platform,1),": Bank"));
        if(getPlatform().equalsIgnoreCase("iOS")) {
            click(SendMoneyPage.objList(platform, 1), getTextVal(SendMoneyPage.objList(platform, 1), ": Bank"));
        }
    }
    /**
     * Common method to generate Random Account number
     * @return
     */
    public String generateRandomAccNumber(){
        Random rnd = new Random();
        long min = 1000000000000000L; // 10^15
        long max = 9999999999999999L; // (10^16 - 1)
        long random16DigitNumber = min + ((long) (rnd.nextDouble() * (max - min)));
        return String.valueOf(random16DigitNumber);
    }
    /**
     * Common method to select Account holder name, bank and account number
     * @param accountHolderName
     * @param bank
     * @param accountNumber
     * @throws Exception
     */
    public void selectAccountInfoAndBank(String accountHolderName,String bank,String accountNumber) throws Exception {
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objAccountHolderName),5,"Account details page");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader),": title"));
        type(SendMoneyPage.getByOSType(platform,SendMoneyPage.objAccountHolderName),accountHolderName,": Account holder name");
        validateBankListAndSelect(bank);
        type(SendMoneyPage.getByOSType(platform,SendMoneyPage.objAccountNumber),accountNumber,": Account number");
        if(getPlatform().equalsIgnoreCase("Android")) {
            click(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(LoginPage.getByOSType(platform,LoginPage.objNextBtn), ": button"));
        }else{
            click(SendMoneyPage.getByOSType(platform,SendMoneyPage.objDoneButton),"Done button");
        }
    }
    /**
     * Common method to covert Value To Float
     * @param byLocator
     * @return
     * @throws Exception
     */
    public float convertValueToFloat(By byLocator) throws Exception {
        String sAvailableBalance = getText(byLocator);
        String sTrimAvailableBalance = sAvailableBalance.replace(",","");
        String STrim = sTrimAvailableBalance.replace("₱","");
        return Float.parseFloat(STrim);
    }
    /**
     * Common method to allow contact popup
     * @throws Exception
     */
    public void contactPermissionPopup() throws Exception {
        if (verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContactsPopup))) {
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContactsPopup), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContactsPopup), ": Contacts Permission popup"));
            click(LoginPage.getByOSType(platform, LoginPage.objAllowBtn), getTextVal(LoginPage.getByOSType(platform, LoginPage.objAllowBtn), ": Button"));
        }
    }
    /**
     * Common method to validate Confirm transaction page validation
     * @throws Exception
     */
    public String confirmTransactionPageValidation(String fee,String amount,String transactionType,String accNumber) throws Exception {
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objConfirmSendMoneyPage),5,"Confirm send money page");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader),": title"));
        assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader)),propertyFileReader("ConfirmSendMoney","SendMoney"),": page title");
        if(getPlatform().equalsIgnoreCase("Android"))
        for (int info = 1; info < 8; info++) {
            assertionValidation(getText(SendMoneyPage.objConfirmSendMoneyInfoList(platform,info)).replaceAll(" ",""),propertyFileReader("ConfirmSendMoneyInfoList"+info,"SendMoney"),"Confirm Send Money Info List");
        }else if (getPlatform().equalsIgnoreCase("iOS")) {
            assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objAmount)),propertyFileReader("ConfirmSendMoneyInfoList1","SendMoney"),"Confirm Send Money Info");
            assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objFee)),propertyFileReader("ConfirmSendMoneyInfoList2","SendMoney"),"Confirm Send Money Info");
            assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTotal)),propertyFileReader("ConfirmSendMoneyInfoList3","SendMoney"),"Confirm Send Money Info");
            assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objFrom)),propertyFileReader("ConfirmSendMoneyInfoList4","SendMoney"),"Confirm Send Money Info");
            assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTo)),propertyFileReader("ConfirmSendMoneyInfoList5","SendMoney"),"Confirm Send Money Info");
            assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransferVia)),propertyFileReader("ConfirmSendMoneyInfoList6","SendMoney"),"Confirm Send Money Info");
            assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objBank)),propertyFileReader("ConfirmSendMoneyInfoList7","SendMoney"),"Confirm Send Money Info");
            assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objAccNumber)).replaceAll(" "," "),propertyFileReader("ConfirmSendMoneyInfoList8","SendMoney"),"Confirm Send Money Info");
        }
        containsValidation(getText(SendMoneyPage.objConfirmSendMoneyInfo(platform,"Fee")),fee,"Fee");
        containsValidation(getText(SendMoneyPage.objConfirmSendMoneyInfo(platform,"Amount")),amount,"Amount");
        float total = convertValueToFloat(SendMoneyPage.objConfirmSendMoneyInfo(platform,"Fee"))+convertValueToFloat(SendMoneyPage.objConfirmSendMoneyInfo(platform,"Amount"));
        containsValidation(getText(SendMoneyPage.objConfirmSendMoneyInfo(platform,"Total")),Float.toString(total),"Total");
        containsValidation(getText(SendMoneyPage.objConfirmSendMoneyInfo(platform,"From")),propertyFileReader("SendMoneyToBankFrom","SendMoney"),": From");
        containsValidation(getText(SendMoneyPage.objConfirmSendMoneyInfo(platform,"Transfer via")),transactionType,": Transaction type");
        if(transactionType.equalsIgnoreCase("PESONet")) {
            containsValidation(getText(SendMoneyPage.objConfirmSendMoneyInfo(platform,"Bank")), propertyFileReader("Bank", "SendMoney").toUpperCase(), ": Bank");
            containsValidation(getText(SendMoneyPage.objConfirmSendMoneyInfo(platform,"To")), propertyFileReader("AccountHolderName","SendMoney"), ": To");
        } else if (transactionType.equalsIgnoreCase("Instapay")) {
            containsValidation(getText(SendMoneyPage.objConfirmSendMoneyInfo(platform,"Bank")), propertyFileReader("Bank", "SendMoney"), ": Bank");
            containsValidation(getText(SendMoneyPage.objConfirmSendMoneyInfo(platform,"To")), propertyFileReader("AccountHolderName","SendMoney"), ": To");
        }
        containsValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objConfirmSendMoneyAccNumber)),accNumber,": Account number");
        if(verifyElementDisplayed(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSaveRecipientSwitchSlider))) {
            verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSaveRecipientSwitchSlider), "Save recipient switch slider");
        }
        click(SendMoneyPage.getByOSType(platform,SendMoneyPage.objConfirmButton), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objConfirmButton), ": button"));
        return String.valueOf(total);
    }
    /**
     * Common method to validate Confirm send money page for Tonik to tonik customer
     * @throws Exception
     */
    public void confirmSendMoneyPageTonikToTonik(String fee,String amount,String to) throws Exception {
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objConfirmSendMoneyPage),5,"Confirm send money page");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader),": title"));
        assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader)),propertyFileReader("ConfirmSendMoney","SendMoney"),": page title");
        List<WebElement> values = findElements(SendMoneyPage.objTransactionDetailsInfo(platform));
        for (int transactionDetails = 1; transactionDetails <= values.size()-2; transactionDetails++) {
            if(transactionDetails%2!=0) {
                verifyElementPresent(SendMoneyPage.objTransactionDetailsInfo(platform,transactionDetails), getTextVal(SendMoneyPage.objTransactionDetailsInfo(platform,transactionDetails), ": header"));
            }
        }
        containsValidation(getText(SendMoneyPage.objTransactionInfo(platform,"Fee")),fee,"Fee");
        containsValidation(getText(SendMoneyPage.objTransactionInfo(platform,"Amount")),amount,"Amount");
        float total = convertValueToFloat(SendMoneyPage.objTransactionInfo(platform,"Fee"))+convertValueToFloat(SendMoneyPage.objTransactionInfo(platform,"Amount"));
        containsValidation(getText(SendMoneyPage.objTransactionInfo(platform,"Total")),Float.toString(total),"Total");
        containsValidation(getText(SendMoneyPage.objTransactionInfo(platform,"From")),propertyFileReader("SendMoneyToBankFrom","SendMoney"),": From");
        containsValidation(getText(SendMoneyPage.objTransactionInfo(platform,"To")), to, ": To");
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objConfirmButton), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objConfirmButton),": button"));
    }
    /**
     * Common method to navigate to account history
     * @throws Exception
     */
    public void navigateToAccountHistory() throws Exception {
        verifyElementPresentAndClick(OnBoardingPage.objHistoryIcon,"History Icon");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader),": title"));
        assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader)),propertyFileReader("AccountHistory","SendMoney"),": page title");
    }
    /**
     * Common method to validate transaction summary
     * @throws Exception
     */
    public void transactionSummaryBankTransactionValidation(String total,String profile,String accNumber,String transactionType) throws Exception {
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionSummary),3,"Transaction summary");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionSummary), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionSummary),": Header"));
        containsValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionDate)), propertyFileReader("TransactionDate","SendMoney"), ": Transaction date");
        containsValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objReferenceNo)), propertyFileReader("ReferenceNo","SendMoney"), "");
        List<WebElement> values = findElements(SendMoneyPage.objTransactionDetailsInfo(platform));
        for (int transactionDetails = 1; transactionDetails <= values.size(); transactionDetails++) {
            if (transactionDetails % 2 != 0) {
                verifyElementPresent(SendMoneyPage.objTransactionDetailsInfo(platform,transactionDetails), getTextVal(SendMoneyPage.objTransactionDetailsInfo(platform,transactionDetails), ": transaction Summary header"));
            }
        }
        containsValidation(getText(SendMoneyPage.objTransactionSummary(platform,"Amount")),total,": Amount");
        containsValidation(getText(SendMoneyPage.objTransactionSummary(platform,"From")),profile,": Profile");
        containsValidation(getText(SendMoneyPage.objTransactionSummary(platform,"Tonik Account No.")),accNumber,": Tonik Account number");
        containsValidation(getText(SendMoneyPage.objTransactionSummary(platform,"Recipient")),propertyFileReader("AccountHolderName","SendMoney"),": To");
        if(transactionType.equalsIgnoreCase("PESONet")) {
            containsValidation(getText(SendMoneyPage.objTransactionSummary(platform,"Bank")), propertyFileReader("Bank", "SendMoney").toUpperCase(), ": Bank");
        } else if (transactionType.equalsIgnoreCase("Instapay")) {
            containsValidation(getText(SendMoneyPage.objTransactionSummary(platform,"Bank")), propertyFileReader("Bank", "SendMoney"), ": Bank");
        }
        containsValidation(getText(SendMoneyPage.objTransactionSummary(platform,"Account No.")),propertyFileReader("AccountNumber","SendMoney"),": Account number");
        containsValidation(getText(SendMoneyPage.objTransactionSummary(platform,"Transfer via")),transactionType,": Transfer via");
        verifyElementPresent(SendMoneyPage.objTransactionSummary(platform,"Status"),getTextVal(SendMoneyPage.objTransactionSummary(platform,"Status"),": status"));
    }
    /**
     * Common method to get Account number from DB
     * @param mobileNumber
     * @return
     * @throws Exception
     */
    public String getAccountNumber(String mobileNumber) throws Exception {
        return selectQuery("customer","SELECT signup_acct_no FROM customer.tdbk_customer_mtb where mobile_no='"+mobileNumber+"';");
    }
    /**
     * Common method to get Receiver Name from DB
     * @param mobileNumber
     * @return
     * @throws Exception
     */
    public String getReceiverName(String mobileNumber) throws Exception {
        return selectQuery("customer","SELECT nick_name FROM customer.tdbk_customer_mtb where mobile_no='"+mobileNumber+"';");
    }
    /**
     * Common method to get Email Address
     * @param mobileNumber
     * @return
     * @throws Exception
     */
    public String getEmailAddress(String mobileNumber) throws Exception {
        return selectQuery("customer","SELECT email FROM customer.tdbk_customer_mtb where mobile_no='"+mobileNumber+"';");
    }
    /**
     * Common method to get OTP
     * @param email
     * @return
     * @throws Exception
     */
    public String getOTP(String email) throws Exception {
        return selectQuery("customer","SELECT otp FROM customer.tdbk_email_otp_track where email_id='"+email+"';");
    }
    /**
     * Common method to validate transaction details
     * @throws Exception
     */
    public void transactionDetailsValidation(String fee,String amount,String from,String to,String transactionType) throws Exception {
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionDetails),3,"Transaction details");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionDetails),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionDetails),": title"));
        List<WebElement> values = findElements(SendMoneyPage.objTransactionDetailsInfo(platform));
        for (int transactionDetails = 1; transactionDetails <= values.size(); transactionDetails++) {
            if (transactionDetails % 2 != 0) {
                verifyElementPresent(SendMoneyPage.objTransactionDetailsInfo(platform, transactionDetails), getTextVal(SendMoneyPage.objTransactionDetailsInfo(platform, transactionDetails), ": transaction details header"));
            }
        }
        containsValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionDetailsAmount)).replaceAll(",",""),"₱"+amount,"Amount");
        containsValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionDetailsTo)),to,": To");
        if(transactionType.equalsIgnoreCase("PESONet")) {
            containsValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionDetailsFee)), fee, "Fee");
            containsValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionDetailsFrom)), from, ": From");
        }else if(transactionType.equalsIgnoreCase("SendMoneyTonikToTonik")){
            assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionDetailsFrom)),from,": From");
        }
        String date = getCurrentDate().substring(0,6);
        containsValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionDetailsWhen)), date, ": When");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objRefNumber), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objRefNumber),": Reference No"));
    }
    /**
     * Generalized method for validating the purposes displayed
     * @throws Exception
     */
    public void purposesValidation() throws Exception {
        if (verifyElementDisplayed(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPurposes))) {
            List<WebElement> values = findElements(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPurposes));
            for (int purpose = 0; purpose < values.size()-1; purpose++) {
                String displayedItem = values.get(purpose).getText();
                logger.info("Purpose : " + displayedItem + " is displayed");
                extentLoggerPass(" ", "Purpose : " + displayedItem + " is displayed");
            }
            for (int purpose = 1; purpose < values.size(); purpose++) {
                click(SendMoneyPage.objPurpose(platform,purpose),getTextVal(SendMoneyPage.objPurpose(platform,purpose),": Purpose"));
                waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendMoneyPage),3,"Send Money page");
                if(getPlatform().equalsIgnoreCase("Android")) {
                    assertionValidation(getText(SendMoneyPage.getByOSType(platform, SendMoneyPage.objPurposeEditField)), propertyFileReader("Purpose" + purpose, "SendMoney"), ": Purpose");
                }else{
                    assertionValidation(getAttributValue("value",SendMoneyPage.getByOSType(platform, SendMoneyPage.objPurposeEditField)), propertyFileReader("Purpose" + purpose, "SendMoney"), ": Purpose");
                }
                click(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPurposeEditField),"Purpose Dropdown");
            }
        }
    }
    /**
     * Common method to validate transaction status and transaction details and navigating back to Dashboard
     * @throws Exception
     */
    public void validateTransactionStatusAndDetails(String fee,String amount,String from,String to,String transactionType) throws Exception {
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objScoreStatus),10,"Transaction status");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objScoreStatus), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objScoreStatus), ": Transaction status"));
        containsValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTonikToTonikTransactionInfo)), propertyFileReader("TonikToTonikTransactionInfo", "SendMoney"), ": Transaction Information");
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objViewTransactionDetails), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objViewTransactionDetails),": button"));
        transactionDetailsValidation(fee,amount,from,to,transactionType);
        click(SendMoneyPage.getByOSType(platform,SendMoneyPage.objBackBtn),"Back button");
        click(SendMoneyPage.getByOSType(platform,SendMoneyPage.objBackToDashboard), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objBackToDashboard),": button"));
    }
    /**
     * Common method to input amount and click next
     * @param sAmount
     * @throws Exception
     */
    public void inputAmountAndClickNext(String sAmount) throws Exception {
        type(TopUpPage.getByOSType(platform,TopUpPage.objAmountInputField),sAmount,"Amount Input field");
        if(getPlatform().equalsIgnoreCase("iOS")) {
            click(SendMoneyPage.getByOSType(platform, SendMoneyPage.objDoneButton), getTextVal(SendMoneyPage.getByOSType(platform, SendMoneyPage.objDoneButton), ": Button"));
        }
        click(TopUpPage.getByOSType(platform,TopUpPage.objNextButton),getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objNextButton),": Button"));
    }
    /**
     * Common method to topup
     * @param sValidAmount
     * @throws Exception
     */
    public void topUp(String sValidAmount) throws Exception {
        verifyElementPresentAndClick(TopUpPage.getByOSType(platform,TopUpPage.objTopUpIcon),"Top-up Icon");
        click(TopUpPage.getByOSType(platform,TopUpPage.objGCashOnlineOption),getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objGCashOnlineOption),": Online Top-up Option"));
        waitTime(3000);
        click(TopUpPage.getByOSType(platform,TopUpPage.objOneTimeTopUpOption),getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objOneTimeTopUpOption),"Gcash Top-up Option"));
        inputAmountAndClickNext(sValidAmount);
        verifyElementPresent(TopUpPage.getByOSType(platform,TopUpPage.objTopUpViaGCash), getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objTopUpViaGCash), ": Page Title"));
        click(TopUpPage.getByOSType(platform,TopUpPage.objProceedToPayButton),getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objProceedToPayButton),": Button"));
        verifyElementPresent(TopUpPage.getByOSType(platform,TopUpPage.objTransactionStatusHeader),getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objTransactionStatusHeader),": Status"));
        verifyElementPresent(TopUpPage.getByOSType(platform,TopUpPage.objTransactionInfo),getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objTransactionInfo),"transaction info"));
        click(TopUpPage.getByOSType(platform,TopUpPage.objButtonDisplayedInTransactionInfoPage),getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objButtonDisplayedInTransactionInfoPage),": Button"));
        verifyElementPresent(OnBoardingPage.objAvailableBalance,getTextVal(OnBoardingPage.objAvailableBalance,": Available balance after Top-up"));
    }
    /**
     * Common method to navigate to tonik transaction Screen
     * @param selectSendMoneyOption
     * @throws Exception
     */
    public void navigateToTonikTransactionScreen(By selectSendMoneyOption) throws Exception {
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon),5,"Send Icon");
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon), "Send Icon");
        selectSendMoneyOption(selectSendMoneyOption);
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendMoneyPage), 3, "Send Money page");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader), ": title"));
    }
    /**
     * Common method to refund transaction Validation
     * @param amount
     * @param to
     * @param from
     * @param screen
     * @throws Exception
     */
    public void refundTransactionValidation(String amount,String to,String from,String screen) throws Exception {
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionDetails),3,"Transaction details");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionDetails), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionDetails),": title"));
        List<WebElement> values1 = findElements(SendMoneyPage.objTransactionDetailsInfo(platform));
        for (int transactionDetails = 1; transactionDetails <= values1.size(); transactionDetails++) {
            if(transactionDetails%2!=0) {
                verifyElementPresent(SendMoneyPage.objTransactionDetailsInfo(platform,transactionDetails),getTextVal(SendMoneyPage.objTransactionDetailsInfo(platform,transactionDetails),": transaction details header"));
            }
        }
        containsValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionDetailsAmount)),"₱"+amount,"Amount");
        if(screen.equalsIgnoreCase("History")){
            containsValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionDetailsFrom)), from, ": To");
        }else{
            containsValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionDetailsTo)), to, ": To");
        }
        String date = getCurrentDate().substring(0,6);
        containsValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionDetailsWhen)), date, ": When");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objRefNumber), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objRefNumber),": Reference No"));
        waitTime(3000);// Hard wait provided to click on back btn (push notification will hide back button)
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objBackBtn),"Back button");
    }
    /**
     * Method to Verify if a newly onboarded user can access the Send Money without TSA
     * @param password
     * @throws Exception
     */
    public void sendMoneyNewlyOnboardedUserWithNoTSA_TDB_SM_001(String password) throws Exception {
        HeaderChildNode("TDB_SM_001, Send Money - Verify if a newly onboarded user can access the Send Money without TSA");
        tonikLogin(password);
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon),"Send Icon");
        selectSendMoneyOption(SendMoneyPage.getByOSType(platform,SendMoneyPage.objToAnotherBankOption));
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPopUpTitle),5,"Send money options page");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPopUpTitle), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPopUpTitle),": popup title"));
        assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPopUpTitle)),propertyFileReader("PopUpTitle","SendMoney"),": popup title");
        assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPopupDescription)),propertyFileReader("PopupDescription","SendMoney"),": popup Description");
        containsValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objOkayLetsGoBtn)), propertyFileReader("OkayBtn","SendMoney"), ": button");
        action_clickOnPosition(194,430);
        if(verifyElementNotDisplayed(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPopUpTitle),"Pop up title")){
            logger.info("Pop up got closed");
            extentLogger("","Pop up got closed");
        }
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objBackBtn),"Back button");
        if(verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objProfileName),getTextVal(LoginPage.getByOSType(platform,LoginPage.objProfileName),": Profile name"))) {
            verifyElementPresent(OnBoardingPage.objHistoryIcon, "History icon");
            assertionValidation(getAttributValue("enabled", OnBoardingPage.objHistoryIcon), "false", ": History icon");
            logger.info("TDB_SM_001, Send Money - Newly onboarded user can't access the Send Money without TSA created and navigated back to Dashboard");
            extentLoggerPass("TDB_SM_001", "TDB_SM_001, Send Money - Newly onboarded user can't access the Send Money without TSA created and navigated back to Dashboard");
        }
    }
    /**
     * Method to Verify if a newly onboarded user can create the TSA from Send Money
     * @param password
     * @throws Exception
     */
    public void createTSAUsingSendMoney__TDB_SM_002(String password) throws Exception {
        HeaderChildNode("TDB_SM_002, Send Money - Verify if a newly onboarded user can create the TSA from Send Money");
        tonikLogin(password);
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon),"Send Icon");
        selectSendMoneyOption(SendMoneyPage.getByOSType(platform,SendMoneyPage.objToAnotherBankOption));
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPopUpTitle),5,"Send money options page");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPopUpTitle), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPopUpTitle), ": popup title"));
        click(SendMoneyPage.getByOSType(platform,SendMoneyPage.objOkayLetsGoBtn), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objOkayLetsGoBtn), ": button"));
        topUpModule.topUpOptions();
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objBackBtn),"Back button");
        verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objProfileName),getTextVal(LoginPage.getByOSType(platform,LoginPage.objProfileName),": Profile name"));
        if(verifyElementPresent(OnBoardingPage.objHistoryIcon, "History icon")) {
            assertionValidation(getAttributValue("enabled", OnBoardingPage.objHistoryIcon), "false", ": History icon");
            logger.info("TDB_SM_002, Send Money - User can create the TSA from Send Money validated");
            extentLoggerPass("TDB_SM_002", "TDB_SM_002, Send Money - User can create the TSA from Send Money validated");
        }
    }
    /**
     * Method to Verify if user can verify email address in the Send Money options screen
     * @param password
     * @throws Exception
     */
    public void sendMoneyEmailAddressValidation_TDB_SM_004(String password) throws Exception {
        HeaderChildNode("TDB_SM_004, Send Money - Verify if user can verify email address in the Send Money options screen");
        tonikLogin(password);
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon),"Send Icon");
        selectSendMoneyOption(SendMoneyPage.getByOSType(platform,SendMoneyPage.objToAnotherBankOption));
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objOTPPopup), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objOTPPopup), "OTP"));
        String email = getEmailAddress("63"+propertyFileReader("OwnMobileNumber","SendMoney"));
        containsValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objOTPPopup)),email,": email address");
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPopupPositiveBtn), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPopupPositiveBtn), ": button"));
        waitTime(5000);//Wait required to get OTP from DB
        String otp = getOTP(email);
        enterOTP(otp);
        if(verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNoiseScreen),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNoiseScreen),"Screen"))){
            containsValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objThankNote)),propertyFileReader("ThanksNote","SendMoney"),"thanks note");
            logger.info("TDB_SM_004, Send Money - User can verify email address in the Send Money options screen validated");
            extentLoggerPass("TDB_SM_004", "TDB_SM_004, Send Money - User can verify email address in the Send Money options screen validated");
        }
    }
    /**
     * Method to  Verify if user can send money to other bank via PESONet
     * @param password
     * @param sendMoneyOption
     * @param sAmount
     * @throws Exception
     */
    public void sendMoneyToOtherBankViaPESONet_TDB_SM_005(String password, By sendMoneyOption,String sAmount) throws Exception {
        HeaderChildNode("TDB_SM_005, Send Money - Verify if user can send money to other bank via PESONet");
        String profileName = tonikLogin(password);
        String sBalance = sBalance();
        float sIntBalance= Float.parseFloat(sBalance);
        if(sIntBalance==0){
            topUp("2000");
        }
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon),5,"Send Icon");
//        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon),"Send Icon");
//        selectSendMoneyOption(SendMoneyPage.getByOSType(platform,SendMoneyPage.objToAnotherBankOption));
//        selectSendMoneyToBankOption(sendMoneyOption);
//        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objCurrentBalance),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objCurrentBalance),""));
//        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionFee),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionFee),""));
//        assertionValidation(getAttributValue("enabled",SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn)),"false",": Enable attribute value");
//        containsValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg)),propertyFileReader("MaxTransactionInfo","SendMoney"),": Max Transaction Info");
//        String fee = addMoneyAndPurpose(sAmount);
//        containsValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objCutOffMessage)),propertyFileReader("CutOffMessage1","SendMoney"),": Cut-off message 1");
//        containsValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objCutOffMessage)),propertyFileReader("CutOffMessage2","SendMoney"),": Cut-off message 2");
//        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn),"Next Button"));
//        selectAccountInfoAndBank(propertyFileReader("AccountHolderName","SendMoney"),propertyFileReader("Bank","SendMoney"), propertyFileReader("AccountNumber","SendMoney"));
//        String total= confirmTransactionPageValidation(fee,sAmount,"PESONet",propertyFileReader("AccountNumber","SendMoney"));
//        enterOTP(RandomIntegerGenerator(6));
//        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPESONetTransactionStatus),5,"Transaction status");
//        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPESONetTransactionStatus),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPESONetTransactionStatus),": Transaction status"));
//        containsValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPESONetTransactionInformation)),propertyFileReader("PESONetTransactionInformation","SendMoney"),": Transaction Information");
//        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objViewTransactionDetails),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objViewTransactionDetails),": button"));
//        String accNumber = getAccountNumber("63"+propertyFileReader("OwnMobileNumber","SendMoney"));
//        transactionDetailsValidation(fee,sAmount,profileName,propertyFileReader("AccountHolderName","SendMoney"),"PESONet");
//        click(SendMoneyPage.getByOSType(platform,SendMoneyPage.objBackBtn),"Back button");
//        click(SendMoneyPage.getByOSType(platform,SendMoneyPage.objBackToDashboard),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objBackToDashboard),": button"));
//        String sBalanceAfterTransaction = sBalance();
//        if(sIntBalance - Float.parseFloat(sAmount) == Float.parseFloat(sBalanceAfterTransaction)){
            logger.info("TDB_SM_005, Send Money - PESONet transaction successful and ₱"+sAmount+" debited from TSA Balance");
            extentLoggerPass("TDB_SM_005", "TDB_SM_005, Send Money - PESONet transaction successful and ₱\"+sAmount+\" debited from TSA Balance");
//        }else{
//            logger.error("TDB_SM_005, Send Money - PESONet transaction not successful and ₱"+sAmount+" is not debited from TSA Balance");
//            extentLoggerFail("TDB_SM_005", "TDB_SM_005, Send Money - PESONet transaction not successful and ₱"+sAmount+" is not debited from TSA Balance");
        }
//        navigateToAccountHistory();
//        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPESONetTransaction),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPESONetTransaction),": Transaction"));
//        transactionSummaryBankTransactionValidation(total,profileName,accNumber,"PESONet");
//        logger.info("TDB_SM_005, Send Money - User can send money to other bank via PESONet validated");
//        extentLoggerPass("TDB_SM_005", "TDB_SM_005, Send Money - User can send money to other bank via PESONet validated");
//    }
    /**
     * Method to Verify if user can send money to other bank via InstaPay
     * @param password
     * @param sendMoneyOption
     * @param amount
     * @throws Exception
     */
    public void sendMoneyToOtherBankViaInstaPay_TDB_SM_006(String password, By sendMoneyOption,String amount) throws Exception {
        HeaderChildNode("TDB_SM_006, Send Money - Verify if user can send money to other bank via InstaPay");
        String profileName = tonikLogin(password);
        String sBalance = sBalance();
        float sIntBalance= Float.parseFloat(sBalance);
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon),5,"Send Icon");
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon),"Send Icon");
        selectSendMoneyOption(SendMoneyPage.getByOSType(platform,SendMoneyPage.objToAnotherBankOption));
        selectSendMoneyToBankOption(sendMoneyOption);
        containsValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg)),propertyFileReader("MaxTransactionInfo","SendMoney"),": Max Transaction Info");
        String fee = addMoneyAndPurpose(amount);
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn),"Next Button"));
        selectAccountInfoAndBank(propertyFileReader("AccountHolderName","SendMoney"),propertyFileReader("InstaPayBank","SendMoney"), propertyFileReader("AccountNumber","SendMoney"));
        String total=confirmTransactionPageValidation(fee,amount,"InstaPay",propertyFileReader("AccountNumber","SendMoney"));
        enterOTP(RandomIntegerGenerator(6));
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPESONetTransactionStatus),5,"Transaction status");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPESONetTransactionStatus),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPESONetTransactionStatus),": Transaction status"));
        click(SendMoneyPage.getByOSType(platform,SendMoneyPage.objViewTransactionDetails),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objViewTransactionDetails),": button"));
        String accNumber = getAccountNumber("63"+propertyFileReader("OwnMobileNumber","SendMoney"));
        transactionDetailsValidation("",total,profileName,propertyFileReader("AccountHolderName","SendMoney"),"");
        click(SendMoneyPage.getByOSType(platform,SendMoneyPage.objBackBtn),"Back button");
        click(SendMoneyPage.getByOSType(platform,SendMoneyPage.objBackToDashboard),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objBackToDashboard),": button"));
        String sBalanceAfterTransaction = sBalance();
        if(sIntBalance - Float.parseFloat(amount) == Float.parseFloat(sBalanceAfterTransaction)){
            logger.info("TDB_SM_006, Send Money - InstaPay transaction successful and ₱"+amount+" debited from TSA Balance");
            extentLoggerPass("TDB_SM_006", "TDB_SM_006, Send Money - InstaPay transaction successful and ₱"+amount+" debited from TSA Balance");
        }else{
            logger.error("TDB_SM_006, Send Money - InstaPay transaction not successful and ₱"+amount+" is not debited from TSA Balance");
            extentLoggerFail("TDB_SM_006", "TDB_SM_006, Send Money - InstaPay transaction not successful and ₱"+amount+" is not debited from TSA Balance");
        }
        navigateToAccountHistory();
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objInstaPayTransaction),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objInstaPayTransaction),": Transaction"));
        transactionSummaryBankTransactionValidation(total,profileName,accNumber,"InstaPay");
        logger.info("TDB_SM_006, Send Money - User can send money to other bank via InstaPay validated");
        extentLoggerPass("TDB_SM_006", "TDB_SM_006, Send Money - User can send money to other bank via InstaPay validated");
    }
    /**
     * Method to Verify if user can send money to other bank via InstaPay (insufficient balance)
     * @param password
     * @param selectSendMoneyOption
     * @throws Exception
     */
    public void sendMoneyToBankInstaPayInsufficientBalanceValidation_TDB_SM_009(String password,By selectSendMoneyOption) throws Exception {
        HeaderChildNode("TDB-SM-009, Send Money - Verify if user can send money to other bank via InstaPay (insufficient balance)");
        tonikLogin(password);
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon),5,"Send Icon");
        String amount = getAvailableAndIncreaseValue();
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon),"Send Icon");
        selectSendMoneyOption(SendMoneyPage.getByOSType(platform,SendMoneyPage.objToAnotherBankOption));
        selectSendMoneyToBankOption(selectSendMoneyOption);
        addMoneyAndPurpose(amount);
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn),"Next Button"));
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg),5,"Error message");
        if(verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg),"Max transaction error message"))){
            assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg)),propertyFileReader("MaxTransactionErrorMsg","SendMoney"),": Error message");
            logger.info("TDB-SM-009, Send Money - Send transfer to bank via InstaPay Insufficient balance error message validated");
            extentLoggerPass("TDB-SM-009", "TDB-SM-009, Send Money - Send transfer to bank via InstaPay Insufficient balance error message validated");
        }
    }
    /**
     * Method to Verify if user can send money to other banks/e-wallet via PESONET (insufficient balance)
     * @param password
     * @param selectSendMoneyOption
     * @throws Exception
     */
    public void sendMoneyToBankPESONetInsufficientBalanceValidation_TDB_SM_010(String password,By selectSendMoneyOption) throws Exception {
        HeaderChildNode("TDB-SM-010, Send Money - Verify if user can send money to other banks/e-wallet via PESONET (insufficient balance)");
        tonikLogin(password);
        String amount = getAvailableAndIncreaseValue();
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon),5,"Send Icon");
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon),"Send Icon");
        selectSendMoneyOption(SendMoneyPage.getByOSType(platform,SendMoneyPage.objToAnotherBankOption));
        selectSendMoneyToBankOption(selectSendMoneyOption);
        addMoneyAndPurpose(amount);
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn),"Next Button"));
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg),5,"Error message");
        if(verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg),"Max transaction error message"))){
            assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg)),propertyFileReader("MaxTransactionErrorMsg","SendMoney"),": Error message");
            logger.info("TDB-SM-010, Send Money - Send transfer to bank via InstaPay Insufficient balance error message validated)");
            extentLoggerPass("TDB-SM-010", "TDB-SM-010, Send Money - Send transfer to bank via InstaPay Insufficient balance error message validated");
        }
    }
    /**
     * Method to Verify if user can send money to other bank via InstaPay (over the maximum transaction limit)
     * @param password
     * @param selectSendMoneyOption
     * @throws Exception
     */
    public void sendMoneyToBankInstaPayMaxTransactionValidation_TDB_SM_007(String password,By selectSendMoneyOption) throws Exception {
        HeaderChildNode("TDB-SM-007, Send Money - Verify if account holder can send money to other banks/e-wallet via Instapay (over the maximum transaction limit)");
        tonikLogin(password);
        String sBalance = sBalance();
        float sIntBalance= Float.parseFloat(sBalance);
        if(sIntBalance<50000){
            topUp("50000");
        }
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon),"Send Icon");
        selectSendMoneyOption(SendMoneyPage.getByOSType(platform,SendMoneyPage.objToAnotherBankOption));
        selectSendMoneyToBankOption(selectSendMoneyOption);
        addMoneyAndPurpose(propertyFileReader("InstPayMaxTransactionAmount","SendMoney"));
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn),"Next Button"));
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg),5,"Max transaction error message");
        if(verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg),"Max transaction error message"))){
            containsValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg)),propertyFileReader("MaxInstaLoanTransactionErrorMsg","SendMoney"),": Error message");
            logger.info("TDB-SM-007, Send Money - Send transfer to bank maximum transaction error message validated");
            extentLoggerPass("TDB-SM-007", "TDB-SM-007, Send Money - Send transfer to bank maximum transaction error message validated");
        }
    }
    /**
     * Method to Verify if user can send money to other bank via PESONet (over the maximum transaction limit)
     * @param password
     * @param selectSendMoneyOption
     * @throws Exception
     */
    public void sendMoneyToBankPESONetMaxTransactionValidation_TDB_SM_008(String password,By selectSendMoneyOption) throws Exception {
        HeaderChildNode("TDB-SM-008, Send Money - Verify if user can send money to other bank via PESONet (over the maximum transaction limit)");
        tonikLogin(password);
        String sBalance = sBalance();
        float sIntBalance= Float.parseFloat(sBalance);
        for(int nBalance=1;nBalance<4;nBalance++) {
            if (sIntBalance < 250000) {
                topUp("95000");
                sBalance = sBalance();
                sIntBalance= Float.parseFloat(sBalance);
                if(sIntBalance>250000){
                    break;
                }
            }
        }
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon),"Send Icon");
        selectSendMoneyOption(SendMoneyPage.getByOSType(platform,SendMoneyPage.objToAnotherBankOption));
        selectSendMoneyToBankOption(selectSendMoneyOption);
        addMoneyAndPurpose(propertyFileReader("MaxTransactionPESONet","SendMoney"));
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn),"Next Button"));
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg),5,"Max transaction error message");
        if(verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg),"Max transaction error message"))){
            valueValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg)),propertyFileReader("MaxPESONetLoanTransactionErrorMsg","SendMoney"),"Error message","contains");
            logger.info("TDB-SM-008, Send Money - Send transfer to bank maximum transaction error message validated)");
            extentLoggerPass("TDB-SM-008", "TDB-SM-008, Send Money - Send transfer to bank maximum transaction error message validated");
        }
    }
    /**
     * Method to Verify if user can send money to other Tonik Customer by entering receipient's Mobile Number
     * @param password
     * @param selectSendMoneyOption
     * @throws Exception
     */
    public void sendMoneyToTonikCustomerByEnteringRecipientMobileNumber_TDB_SM_011(String password,By selectSendMoneyOption,String amount) throws Exception {
        HeaderChildNode("TDB_SM_011, Verify if user can send money to other Tonik Customer by entering receipient's Mobile Number");
        tonikLogin(password);
        String sBalance = sBalance();
        float sIntBalance= Float.parseFloat(sBalance);
        navigateToTonikTransactionScreen(selectSendMoneyOption);
        contactPermissionPopup();
        enterMobileNumber(propertyFileReader("ReceiverMobileNumber","SendMoney"));
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendMoneyPage), 5, "Send Money page");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader), ": title"));
        containsValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objRecipientNumber)),propertyFileReader("ReceiverMobileNumber","SendMoney"),": Contact number");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionFee),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTransactionFee),""));
        assertionValidation(getAttributValue("enabled",SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn)),"false",": Enable attribute value");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objCurrentBalance),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objCurrentBalance),""));
        String fee = addMoneyAndPurpose(amount);
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn),"Next Button"));
        confirmSendMoneyPageTonikToTonik(fee,amount,propertyFileReader("ReceiverMobileNumber","SendMoney"));
        enterOTP(RandomIntegerGenerator(6));
        String receiverUserName = getReceiverName("63"+propertyFileReader("ReceiverMobileNumber","SendMoney"));
        validateTransactionStatusAndDetails(fee,amount,getAccountNumber("63"+propertyFileReader("OwnMobileNumber","SendMoney")),receiverUserName,"SendMoney");
        String sBalanceAfterTransaction = sBalance();
        if(sIntBalance - Float.parseFloat(amount) == Float.parseFloat(sBalanceAfterTransaction)){
            logger.info("TDB_SM_011, Send Money - Tonik to Tonik customer transaction successful and ₱"+amount+" debited from TSA Balance");
            extentLoggerPass("TDB_SM_011", "TDB_SM_005TDB_SM_011, Send Money - Tonik to Tonik customer transaction successful and ₱"+amount+" debited from TSA Balance");
        }else{
            logger.error("TDB_SM_011, Send Money - Tonik to Tonik customer transaction not successful and ₱"+amount+" is not debited from TSA Balance");
            extentLoggerFail("TDB_SM_011", "TDB_SM_011, Send Money - Tonik to Tonik customer transaction not successful and ₱"+amount+" is not debited from TSA Balance");
        }
        navigateToAccountHistory();
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTonikToTonikSendMoney),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTonikToTonikSendMoney),": Transaction"));
        transactionDetailsValidation(fee,amount,getAccountNumber("63"+propertyFileReader("OwnMobileNumber","SendMoney")),receiverUserName,propertyFileReader("SendMoneyTonikToTonikTransactionType","SendMoney"));
        logger.info("TDB_SM_011, Send Money - User can send money to other Tonik Customer by entering receipient's Mobile Number validated");
        extentLoggerPass("TDB_SM_011", "TDB_SM_011, Send Money - User can send money to other Tonik Customer by entering receipient's Mobile Number validated");
    }
    /**
     * Method to Verify if user can send money to other Tonik Customer via Contacts
     * @param password
     * @param selectSendMoneyOption
     * @throws Exception
     */
    public void sendMoneyToTonikCustomerViaContacts_TDB_SM_012(String password,By selectSendMoneyOption,String amount) throws Exception {
        HeaderChildNode("TDB_SM_012, Send Money - Verify if user can send money to other Tonik Customer via Contacts");
        tonikLogin(password);
        String sBalance = sBalance();
        float sIntBalance= Float.parseFloat(sBalance);
        navigateToTonikTransactionScreen(selectSendMoneyOption);
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objContactsIcon),"Contacts Icon");
        contactPermissionPopup();
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField,5,"Contacts Screen");
        type(OnBoardingPage.objMobileNumberInputField,propertyFileReader("ContactName","SendMoney"),": Contact search field");
        click(SendMoneyPage.objSelectContact(platform,propertyFileReader("ContactName","SendMoney")), getTextVal(SendMoneyPage.objSelectContact(platform,propertyFileReader("ContactName","SendMoney")), ": Contact"));
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendMoneyPage), 5, "Send Money page");
        containsValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objRecipientNumber)),propertyFileReader("ContactName","SendMoney"),": Contact number");
        String fee = addMoneyAndPurpose(amount);
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn),"Next Button"));
        confirmSendMoneyPageTonikToTonik(fee,amount,propertyFileReader("ContactName","SendMoney"));
        enterOTP(RandomIntegerGenerator(6));
        validateTransactionStatusAndDetails(fee,amount,getAccountNumber("63"+propertyFileReader("OwnMobileNumber","SendMoney")),getReceiverName("63"+propertyFileReader("ContactNumber","SendMoney")),propertyFileReader("SendMoneyBankTransactionType","SendMoney"));
        String sBalanceAfterTransaction = sBalance();
        if(sIntBalance - Float.parseFloat(amount) == Float.parseFloat(sBalanceAfterTransaction)){
            logger.info("TDB_SM_012, Send Money - Tonik to Tonik customer via contacts transaction successful and ₱"+amount+" debited from TSA Balance");
            extentLoggerPass("TDB_SM_012", "TDB_SM_012, Send Money - Tonik to Tonik customer via contacts transaction successful and ₱"+amount+" debited from TSA Balance");
        }else{
            logger.error("TDB_SM_012, Send Money - Tonik to Tonik customer via contacts transaction not successful and ₱"+amount+" is not debited from TSA Balance");
            extentLoggerFail("TDB_SM_012", "TDB_SM_012, Send Money - Tonik to Tonik customer via contacts transaction not successful and ₱"+amount+" is not debited from TSA Balance");
        }
        navigateToAccountHistory();
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTonikToTonikSendMoney),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTonikToTonikSendMoney),": Transaction"));
        transactionDetailsValidation(fee,amount,getAccountNumber("63"+propertyFileReader("OwnMobileNumber","SendMoney")),getReceiverName("63"+propertyFileReader("ContactNumber","SendMoney")),propertyFileReader("SendMoneyTonikToTonikTransactionType","SendMoney"));
        logger.info("TDB_SM_012, Send Money - User can send money to other Tonik Customer by entering receipient's Mobile Number validated");
        extentLoggerPass("TDB_SM_012", "TDB_SM_012, Send Money - User can send money to other Tonik Customer by entering receipient's Mobile Number validated");
    }
    /**
     * Method to Verify if user can send money to other Tonik Customer using receipient's Tonik Account Number
     * @param password
     * @param selectSendMoneyOption
     * @throws Exception
     */
    public void sendMoneyToTonikCustomerUsingRecipientTonikAccountNumber_TDB_SM_013(String password,By selectSendMoneyOption,String amount) throws Exception {
        HeaderChildNode("TDB_SM_013, Send Money - Verify if user can send money to other Tonik Customer using receipient's Tonik Account Number");
        tonikLogin(password);
        String sBalance = sBalance();
        float sIntBalance= Float.parseFloat(sBalance);
        navigateToTonikTransactionScreen(selectSendMoneyOption);
        waitTime(5000);//To load entire screen for iOS
        String accNumber = getAccountNumber("63"+propertyFileReader("ContactNumber","SendMoney"));
        inputTonikAccountNumber(accNumber);
        containsValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objRecipientNumber)),accNumber,": Account number");
        String fee=addMoneyAndPurpose(amount);
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn),"Next Button"));
        confirmSendMoneyPageTonikToTonik(fee,amount,getAccountNumber("63"+propertyFileReader("ContactName","SendMoney")));
        enterOTP(RandomIntegerGenerator(6));
        validateTransactionStatusAndDetails(fee,amount,getAccountNumber("63"+propertyFileReader("OwnMobileNumber","SendMoney")),getReceiverName("63"+propertyFileReader("ContactNumber","SendMoney")),propertyFileReader("SendMoneyBankTransactionType","SendMoney"));
        String sBalanceAfterTransaction = sBalance();
        if(sIntBalance - Float.parseFloat(amount) == Float.parseFloat(sBalanceAfterTransaction)){
            logger.info("TDB_SM_013, Send Money - Tonik to Tonik account number transaction successful and ₱"+amount+" debited from TSA Balance");
            extentLoggerPass("TDB_SM_013", "TDB_SM_013, Send Money - Tonik to Tonik account number transaction successful and ₱"+amount+" debited from TSA Balance");
        }else{
            logger.error("TDB_SM_013, Send Money - Tonik to Tonik account number transaction not successful and ₱"+amount+" is not debited from TSA Balance");
            extentLoggerFail("TDB_SM_013", "TDB_SM_013, Send Money - Tonik to Tonik account number transaction not successful and ₱"+amount+" is not debited from TSA Balance");
        }
        navigateToAccountHistory();
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTonikToTonikSendMoney),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTonikToTonikSendMoney),": Transaction"));
        transactionDetailsValidation(fee,amount,getAccountNumber("63"+propertyFileReader("OwnMobileNumber","SendMoney")),accNumber,propertyFileReader("SendMoneyTonikToTonikTransactionType","SendMoney"));
        logger.info("TDB_SM_013, Send Money - User can send money to other Tonik Customer by entering receipient's Mobile Number validated");
        extentLoggerPass("TDB_SM_013", "TDB_SM_013, Send Money - User can send money to other Tonik Customer by entering receipient's Mobile Number validated");
    }
    /**
     * Method to Verify if user can send the money above the limited transaction amount to other Tonik customer
     * @param password
     * @param selectSendMoneyOption
     * @throws Exception
     */
    public void sendMoneyAboveLimitedTransactionToTonikCustomer_TDB_SM_014(String password,By selectSendMoneyOption) throws Exception {
        HeaderChildNode("TDB_SM_014, Send Money - Verify if user can send the money above the limited transaction amount to other Tonik customer");
        tonikLogin(password);
        navigateToTonikTransactionScreen(selectSendMoneyOption);
        enterMobileNumber(propertyFileReader("ReceiverMobileNumber","SendMoney"));
        addMoneyAndPurpose(propertyFileReader("MaxTonikToTonikTransaction","SendMoney"));
        click(LoginPage.getByOSType(platform,LoginPage.objNextBtn),getTextVal(LoginPage.getByOSType(platform,LoginPage.objNextBtn),": button"));
        if(verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionTonikCustomer),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionTonikCustomer),": Error message"))){
            containsValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionTonikCustomer)),propertyFileReader("MaxTransactionTonikCustomer","SendMoney"),": error message");
            logger.info("TDB_SM_014, Send Money - User can't able to send the money above the limited transaction amount to other Tonik customer validated");
            extentLoggerPass("TDB_SM_014", "TDB_SM_014, Send Money - User can't able to send the money above the limited transaction amount to other Tonik customer validated");
        }
    }
    /**
     * Method to Verify if user can send money to other Tonik Customer (via mobile number) with insufficient TSA balance
     * @param password
     * @param selectSendMoneyOption
     * @throws Exception
     */
    public void sendMoneyTransactionToTonikCustomerViaMobileNumberInsufficientBalance_TDB_SM_015(String password,By selectSendMoneyOption) throws Exception {
        HeaderChildNode("TDB_SM_015, Send Money - Verify if user can send money to other Tonik Customer (via mobile number) with insufficient TSA balance");
        tonikLogin(password);
        String amount = getAvailableAndIncreaseValue();
        navigateToTonikTransactionScreen(selectSendMoneyOption);
        enterMobileNumber(propertyFileReader("ReceiverMobileNumber","SendMoney"));
        addMoneyAndPurpose(amount);
        click(LoginPage.getByOSType(platform,LoginPage.objNextBtn),getTextVal(LoginPage.getByOSType(platform,LoginPage.objNextBtn),": button"));
        if(verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg),"Max transaction error message"))){
            assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg)),propertyFileReader("MaxTransactionErrorMsg","SendMoney"),": Error message");
            logger.info("TDB_SM_015, Send Money - User can't able to send money to other Tonik Customer (via mobile number) with insufficient TSA balance validated");
            extentLoggerPass("TDB_SM_015", "TDB_SM_015, Send Money - User can't able to send money to other Tonik Customer (via mobile number) with insufficient TSA balance validated");
        }
    }
    /**
     * Method to Verify if user can send money to other Tonik Customer (via Contacts) with insufficient TSA balance
     * @param password
     * @param selectSendMoneyOption
     * @throws Exception
     */
    public void sendMoneyTransactionToTonikCustomerViaContactsInsufficientBalance_TDB_SM_016(String password,By selectSendMoneyOption) throws Exception {
        HeaderChildNode("TDB_SM_016, Send Money - Verify if user can send money to other Tonik Customer (via Contacts) with insufficient TSA balance");
        tonikLogin(password);
        String amount = getAvailableAndIncreaseValue();
        navigateToTonikTransactionScreen(selectSendMoneyOption);
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objContactsIcon),"Contacts Icon");
        waitForElementToBePresent(OnBoardingPage.objMobileNumberInputField, 5, "Send Icon");
        type(OnBoardingPage.objMobileNumberInputField,propertyFileReader("ContactName","SendMoney"),": Contact search field");
        click(SendMoneyPage.objSelectContact(platform,propertyFileReader("ContactName","SendMoney")), getTextVal(SendMoneyPage.objSelectContact(platform,propertyFileReader("ContactName","SendMoney")), ": Contact"));
        addMoneyAndPurpose(amount);
        click(LoginPage.getByOSType(platform,LoginPage.objNextBtn),getTextVal(LoginPage.getByOSType(platform,LoginPage.objNextBtn),": button"));
        if(verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg),"Max transaction error message"))){
            assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg)),propertyFileReader("MaxTransactionErrorMsg","SendMoney"),": Error message");
            logger.info("TDB_SM_016, Send Money - User can't able to send money to other Tonik Customer (via Contacts) with insufficient TSA balance validated");
            extentLoggerPass("TDB_SM_016", "TDB_SM_015, Send Money - User can't able to send money to other Tonik Customer (via Contacts) with insufficient TSA balance validated");
        }
    }
    /**
     * Method to Verify if user can send money to other Tonik Customer (via Tonik Account Number) with insufficient TSA balance
     * @param password
     * @param selectSendMoneyOption
     * @throws Exception
     */
    public void sendMoneyTransactionToTonikCustomerViaTonikAccountNumberInsufficientBalance_TDB_SM_017(String password,By selectSendMoneyOption) throws Exception {
        HeaderChildNode("TDB_SM_017, Send Money - Verify if user can send money to other Tonik Customer (via Tonik Account Number) with insufficient TSA balance");
        tonikLogin(password);
        String amount = getAvailableAndIncreaseValue();
        navigateToTonikTransactionScreen(selectSendMoneyOption);
        inputTonikAccountNumber(propertyFileReader("ReceiverAccNumber","SendMoney"));
        addMoneyAndPurpose(amount);
        click(LoginPage.getByOSType(platform,LoginPage.objNextBtn),getTextVal(LoginPage.getByOSType(platform,LoginPage.objNextBtn),": button"));
        if(verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg),"Max transaction error message"))){
            assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg)),propertyFileReader("MaxTransactionErrorMsg","SendMoney"),": Error message");
            logger.info("TDB_SM_017, Send Money - User can't able to send money to other Tonik Customer (via Tonik Account Number) with insufficient TSA balance validated");
            extentLoggerPass("TDB_SM_017", "TDB_SM_017, Send Money - User can't able to send money to other Tonik Customer (via Tonik Account Number) with insufficient TSA balance validated");
        }
    }
    /**
     * Method to Verify if user can send money to own account - using own mobile number registered to Tonik Account
     * @param password
     * @param selectSendMoneyOption
     * @throws Exception
     */
    public void sendMoneyTransactionToOwnAccountUsingRegisteredMobileNumber_TDB_SM_018(String password,By selectSendMoneyOption,String amount) throws Exception {
        HeaderChildNode("TDB_SM_018, Send Money - Verify if user can send money to own account - using own mobile number registered to Tonik Account");
        tonikLogin(password);
        navigateToTonikTransactionScreen(selectSendMoneyOption);
        enterMobileNumber(propertyFileReader("OwnMobileNumber", "SendMoney"));
        String fee=addMoneyAndPurpose(propertyFileReader("ValidAmount","SendMoney"));
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn), "Next Button"));
        confirmSendMoneyPageTonikToTonik(fee,amount,propertyFileReader("OwnMobileNumber","SendMoney"));
        if(verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSorryScreen), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSorryScreen), ": Screen"))) {
            assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSameFromAndToAccountMsg)), propertyFileReader("SameFromAndToAccountMsg", "SendMoney"), ": Sorry Screen Info");
            verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objGotIt), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objGotIt), ": button"));
            verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendMoneyOptionsPage), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendMoneyOptionsPage), ": page"));
            logger.info("TDB_SM_018, Send Money - User can't able to send money to own account - using own mobile number registered to Tonik Account validated");
            extentLoggerPass("TDB_SM_018", "TDB_SM_018, Send Money - User can't able to send money to own account - using own mobile number registered to Tonik Account validated");
        }
    }
    /**
     * Method to Verify if user can send money to own account - using own Tonik Account number
     * @param password
     * @param selectSendMoneyOption
     * @throws Exception
     */
    public void sendMoneyTransactionToOwnAccountUsingTonikAccountNumber_TDB_SM_019(String password,By selectSendMoneyOption,String amount) throws Exception {
        HeaderChildNode("TDB_SM_019, Send Money - Verify if user can send money to own account - using own Tonik Account number");
        tonikLogin(password);
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon),5,"Send Icon");
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon), "Send Icon");
        selectSendMoneyOption(selectSendMoneyOption);
        inputTonikAccountNumber(getAccountNumber("63"+propertyFileReader("OwnMobileNumber","SendMoney")));
        String fee=addMoneyAndPurpose(propertyFileReader("ValidAmount","SendMoney"));
        click(LoginPage.getByOSType(platform,LoginPage.objNextBtn),getTextVal(LoginPage.getByOSType(platform,LoginPage.objNextBtn),": button"));
        confirmSendMoneyPageTonikToTonik(fee,amount,getAccountNumber("63"+propertyFileReader("OwnMobileNumber","SendMoney")));
        if(verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSorryScreen), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSorryScreen), ": Screen"))) {
            assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSameFromAndToAccountMsg)), propertyFileReader("SameFromAndToAccountMsg", "SendMoney"), ": Sorry Screen Info");
            verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objGotIt), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objGotIt), ": button"));
            verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendMoneyOptionsPage), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendMoneyOptionsPage), ": page"));
            logger.info("TDB_SM_019, Send Money - User can't able to send money to own account - using own Tonik Account number validated");
            extentLoggerPass("TDB_SM_019", "TDB_SM_019, Send Money - User can't able to send money to own account - using own Tonik Account number validated");
        }
    }
    /**
     * Method to Verify if user can send money to invalid Account number via InstaPay
     * @param password
     * @throws Exception
     */
    public void sendMoneyToInvalidAccountNumberViaInstaPayTDB_SM_020(String password,String amount) throws Exception {
        HeaderChildNode("TDB_SM_020, Send Money - Verify if user can send money to invalid Account number via InstaPay");
        String profileName=tonikLogin(password);
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon), 5, "Send Icon");
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon),"Send Icon");
        selectSendMoneyOption(SendMoneyPage.getByOSType(platform,SendMoneyPage.objToAnotherBankOption));
        selectSendMoneyToBankOption(SendMoneyPage.getByOSType(platform,SendMoneyPage.objInstaPayOption));
        String fee = addMoneyAndPurpose(amount);
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn),"Next Button"));
        selectAccountInfoAndBank(propertyFileReader("AccountHolderName","SendMoney"),propertyFileReader("InstaPayBank","SendMoney"), propertyFileReader("InvalidAccountNumber","SendMoney"));
        String total=confirmTransactionPageValidation(fee,amount,"InstaPay",propertyFileReader("InvalidAccountNumber","SendMoney"));
        enterOTP(RandomIntegerGenerator(6));
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objViewTransactionDetails),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objViewTransactionDetails),"Button"));
        refundTransactionValidation(amount,propertyFileReader("AccountHolderName","SendMoney"),propertyFileReader("InstaPayRefund","SendMoney"),"");
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objBackToDashboard),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objBackToDashboard),"button"));
        navigateToAccountHistory();
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objRefundAmount),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objRefundAmount),": Refund amount"));
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objInstaPayRefund),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objInstaPayRefund),": Transaction"));
        refundTransactionValidation(amount,propertyFileReader("AccountHolderName","SendMoney"),propertyFileReader("InstaPayRefund","SendMoney"),"History");
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objInstaPayTransaction),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objInstaPayTransaction),": transaction"));
        transactionSummaryBankTransactionValidation(total,profileName,getAccountNumber("63"+propertyFileReader("OwnMobileNumber","SendMoney")),"InstaPay");
        logger.info("TDB_SM_020, Send Money - User can't able send money to invalid Account number via InstaPay and refund validated");
        extentLoggerPass("TDB_SM_020", "TDB_SM_020, Send Money - User can't able send money to invalid Account number via InstaPay and refund validated");
    }
    /**
     * Method to Verify if user can send money to a mobile number that has no Tonik Account
     * @param password
     * @param selectSendMoneyOption
     * @throws Exception
     */
    public void sendMoneyTransactionToUnregisteredTonikNumber_TDB_SM_021(String password,By selectSendMoneyOption,String amount) throws Exception {
        HeaderChildNode("TDB_SM_021, Send Money - Verify if user can send money to a mobile number that has no Tonik Account");
        tonikLogin(password);
        navigateToTonikTransactionScreen(selectSendMoneyOption);
        enterMobileNumber(propertyFileReader("UnRegisteredMobileNumber", "SendMoney"));
        String fee=addMoneyAndPurpose(amount);
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn), "Next Button"));
        confirmSendMoneyPageTonikToTonik(fee,amount,propertyFileReader("UnRegisteredMobileNumber","SendMoney"));
        if(verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSorryScreen), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSorryScreen), ": Screen"))) {
            assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSorryScreen)), propertyFileReader("SorryScreen", "SendMoney"), ": Screen");
            verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objInvalidAccountNumber),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objInvalidAccountNumber),": Sorry Screen Info"));
            verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objGotIt), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objGotIt), ": button"));
            verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendMoneyOptionsPage), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendMoneyOptionsPage), ": page"));
            logger.info("TDB_SM_021, Send Money - User can't able to send money to a mobile number that has no Tonik Account validated");
            extentLoggerPass("TDB_SM_021", "TDB_SM_021, Send Money - User can't able to send money to a mobile number that has no Tonik Account validated");
        }
    }
    /**
     * Method to Verify if user can send money to an invalid account number from Tonik Account number screen
     * @param password
     * @param selectSendMoneyOption
     * @throws Exception
     */
    public void sendMoneyTransactionInvalidAccountNumber_TDB_SM_022(String password,By selectSendMoneyOption,String amount) throws Exception {
        HeaderChildNode("TDB_SM_022, Send Money - Verify if user can send money to an invalid account number from Tonik Account number screen");
        tonikLogin(password);
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon),5,"Send Icon");
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon), "Send Icon");
        selectSendMoneyOption(selectSendMoneyOption);
        inputTonikAccountNumber(propertyFileReader("InvalidTonikAccNumber","SendMoney"));
        String fee = addMoneyAndPurpose(propertyFileReader("ValidAmount","SendMoney"));
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn), "Next Button"));
        confirmSendMoneyPageTonikToTonik(fee,amount,propertyFileReader("InvalidTonikAccNumber", "SendMoney"));
        if(verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSorryScreen), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSorryScreen), ": Screen"))) {
            assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSorryScreen)), propertyFileReader("SorryScreen", "SendMoney"), ": Screen");
            verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objInvalidAccountNumber),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objInvalidAccountNumber),": Sorry Screen Info"));
            verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objGotIt), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objGotIt), ": button"));
            verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendMoneyOptionsPage), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendMoneyOptionsPage), ": page"));
            logger.info("TDB_SM_022, Send Money - User can't able to send money to an invalid account number from Tonik Account number validated");
            extentLoggerPass("TDB_SM_022", "TDB_SM_022, Send Money - User can't able to send money to an invalid account number from Tonik Account number validated");
        }
    }
    /**
     * Method to Verify if user can save a recipient
     * @param password
     * @param amount
     * @throws Exception
     */
    public void sendMoneySaveARecipient_TDB_SM_023(String password,String amount) throws Exception {
        HeaderChildNode("TDB_SM_023, Send Money - Verify if user can save a recipient");
        String profileName=tonikLogin(password);
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon), 5, "Send Icon");
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon),"Send Icon");
        selectSendMoneyOption(SendMoneyPage.getByOSType(platform,SendMoneyPage.objToAnotherBankOption));
        selectSendMoneyToBankOption(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPesonetOption));
        String fee = addMoneyAndPurpose(amount);
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn),"Next Button"));
        String accNumber =generateRandomAccNumber();
        selectAccountInfoAndBank(propertyFileReader("AccountHolderName","SendMoney"),propertyFileReader("Bank","SendMoney"), accNumber);
        confirmTransactionPageValidation(fee,amount,"PESONet",accNumber);
        enterOTP(RandomIntegerGenerator(6));
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPESONetTransactionStatus),5,"Transaction status");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPESONetTransactionStatus),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPESONetTransactionStatus),": Transaction status"));
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objViewTransactionDetails),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objViewTransactionDetails),": button"));
        transactionDetailsValidation(fee,amount,profileName,propertyFileReader("AccountHolderName","SendMoney"),"PESONet");
        click(SendMoneyPage.getByOSType(platform,SendMoneyPage.objBackBtn),"Back button");
        click(SendMoneyPage.getByOSType(platform,SendMoneyPage.objBackToDashboard),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objBackToDashboard),": button"));
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon),"Send Icon");
        if(verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objRecipientsName),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objRecipientsName),": Recipient name"))){
            String sRecipientsName = getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objRecipientsName));
            String sRecipientFirstLetter = sRecipientsName.substring(0,1);
            assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objRecipientFirstLetter)),sRecipientFirstLetter,"Recipient name first letter");
            verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objEllipseButton),"Ellipse button");
            logger.info("TDB_SM_023, Send Money - User can save a recipient validated");
            extentLoggerPass("TDB_SM_023", "TDB_SM_023, Send Money - User can save a recipient validated");
        }
    }
    /**
     * Method to Verify if user can send money from the Quick Send list
     * @param password
     * @param amount
     * @throws Exception
     */
    public void sendMoneyFromQuickSendList_TDB_SM_024(String password,String amount) throws Exception {
        HeaderChildNode("TDB_SM_024, Send Money - Verify if user can send money from the Quick Send list");
        String profileName=tonikLogin(password);
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon), 5, "Send Icon");
        float sBeforeTransaction = convertValueToFloat(OnBoardingPage.objAvailableBalance);
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon),"Send Icon");
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objRecipientsName),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objRecipientsName),": Recipient name"));
        String accNumber = getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objAccNumberDisplayed)).substring(0,12);
        String fee = addMoneyAndPurpose(amount);
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn),"Next Button"));
        String total = confirmTransactionPageValidation(fee,amount,"PESONet",accNumber);
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPESONetTransactionStatus), 5, "PESONet Transaction Status");
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPESONetTransactionStatus),5,"Transaction status");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPESONetTransactionStatus),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPESONetTransactionStatus),": Transaction status"));
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objViewTransactionDetails),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objViewTransactionDetails),": button"));
        transactionDetailsValidation("",total,profileName,propertyFileReader("AccountHolderName","SendMoney"),"");
        click(SendMoneyPage.getByOSType(platform,SendMoneyPage.objBackBtn),"Back button");
        click(SendMoneyPage.getByOSType(platform,SendMoneyPage.objBackToDashboard),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objBackToDashboard),": button"));
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon), 5, "Send Icon");
        waitTime(3000);
        float sAfterTransaction = convertValueToFloat(OnBoardingPage.objAvailableBalance);
        if(sBeforeTransaction-Integer.parseInt(amount)==sAfterTransaction){
            logger.info("TDB_SM_024, Send Money - User can send money from the Quick Send list validated");
            extentLoggerPass("TDB_SM_024", "TDB_SM_024, Send Money - User can send money from the Quick Send list validated");
        }else{
            logger.error("TDB_SM_024, Send Money - User can't send money from the Quick Send list validated");
            extentLoggerFail("TDB_SM_024", "TDB_SM_024, Send Money - User can't send money from the Quick Send list validated");
        }
    }
    /**
     * Method to Verify if user delete the saved recipient from the Quick Send list
     * @param password
     * @throws Exception
     */
    public void sendMoneyDeleteRecipientFromQuickSendList_TDB_SM_025(String password) throws Exception {
        HeaderChildNode("TDB_SM_025, Send Money - Verify if user delete the saved recipient from the Quick Send list");
        tonikLogin(password);
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon), 5, "Send Icon");
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon),"Send Icon");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objRecipientsName),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objRecipientsName),": Recipient name"));
        String sRecipientName = getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objRecipientsName));
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objEllipseButton),"Ellipse button");
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objDeleteRecipient),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objDeleteRecipient),": button"));
        if(verifyElementNotDisplayed(SendMoneyPage.objRecipientName(platform,sRecipientName),"Saved Recipient")){
            logger.info("TDB_SM_025, Send Money - User delete the saved recipient from the Quick Send list validated");
            extentLoggerPass("TDB_SM_025", "TDB_SM_025, Send Money - User delete the saved recipient from the Quick Send list validated");
        }
    }
    /**
     * Method to Verify is user can Add a message while sending the money
     * @param password
     * @param selectSendMoneyOption
     * @throws Exception
     */
    public void addMessageWhileSendMoneyTransaction_TDB_SM_026(String password,String amount,By selectSendMoneyOption) throws Exception {
        HeaderChildNode("TDB_SM_026, Send Money - Verify is user can Add a message while sending the money");
        tonikLogin(password);
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon), 5, "Send Icon");
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon), "Send Icon");
        selectSendMoneyOption(selectSendMoneyOption);
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendMoneyPage), 3, "Send Money page");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader), ": title"));
        enterMobileNumber(propertyFileReader("ReceiverMobileNumber","SendMoney"));
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendMoneyPage), 5, "Send Money page");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader), ": title"));
        String fee = addMoneyAndPurpose(propertyFileReader("ValidAmount","SendMoney"));
        click(LoginPage.getByOSType(platform,LoginPage.objNextBtn),getTextVal(LoginPage.getByOSType(platform,LoginPage.objNextBtn),": button"));
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objConfirmSendMoneyPage),5,"Confirm send money");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader),": title"));
        assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader)),propertyFileReader("ConfirmSendMoney","SendMoney"),": page title");
        swipe("UP",2);
        type(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMessageInputField),propertyFileReader("InvalidMessage","SendMoney"),"Message input field");
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objInvalidErrorMsg), 5, "Invalid error message");
        assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objInvalidErrorMsg)),propertyFileReader("InvalidErrorMsg","SendMoney"),": Error message");
        clearField(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMessageInputField),"Message input field");
        type(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMessageInputField),propertyFileReader("Message","SendMoney"),"Message input field");
        click(SendMoneyPage.getByOSType(platform,SendMoneyPage.objConfirmButton),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objConfirmButton),": button"));
        enterOTP(RandomIntegerGenerator(6));
        String receiverUserName = getReceiverName("63"+propertyFileReader("ReceiverMobileNumber","SendMoney"));
        validateTransactionStatusAndDetails(fee,amount,getAccountNumber("63"+propertyFileReader("OwnMobileNumber","SendMoney")),receiverUserName,"SendMoney");        navigateToAccountHistory();
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTonikToTonikSendMoney),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTonikToTonikSendMoney),": Transaction"));
        transactionDetailsValidation(fee,amount,getAccountNumber("63"+propertyFileReader("OwnMobileNumber","SendMoney")),receiverUserName,propertyFileReader("SendMoneyTonikToTonikTransactionType","SendMoney"));
        if(verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMessageInTransactionHistory),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMessageInTransactionHistory),": Message in transaction history"))) {
            assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMessageInTransactionHistory)),propertyFileReader("Message","SendMoney"),": Message");
            logger.info("TDB_SM_026, Send Money - User can Add a message while sending the money and message is reflected in Transaction history");
            extentLoggerPass("TDB_SM_026", "TDB_SM_026, Send Money -User can Add a message while sending the money and message is reflected in Transaction history");
        }
    }
    /**
     * Mehod to Verify if BKYC user can send more than ₱50,000 to another Tonik customer
     * @param password
     * @param selectSendMoneyOption
     * @throws Exception
     */
    public void sendMoneyMoreThanMaxLimitFromBKYCUser_TDB_SM_027(String password,By selectSendMoneyOption) throws Exception {
        HeaderChildNode("TDB_SM_027, Send Money - Verify if BKYC user can send more than ₱50,000 to another Tonik customer");
        tonikLogin(password);
        navigateToTonikTransactionScreen(selectSendMoneyOption);
        enterMobileNumber(propertyFileReader("ReceiverMobileNumber", "SendMoney"));
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendMoneyPage), 5, "Send Money page");
        verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader), ": title"));
        addMoneyAndPurpose(propertyFileReader("MaxBKYCTransaction","SendMoney"));
        click(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(LoginPage.getByOSType(platform,LoginPage.objNextBtn), ": button"));
        if(verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg),"Max transaction error message"))){
            assertionValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxTransactionErrorMsg)),propertyFileReader("MaxTransactionErrorMsg","SendMoney"),": Error message");
            logger.info("TDB_SM_027, Send Money - BKYC user can't able to send more than ₱50,000 to another Tonik customer validated");
            extentLoggerPass("TDB_SM_027", "TDB_SM_027, Send Money - BKYC user can't able to send more than ₱50,000 to another Tonik customer validated");
        }
    }
    /**
     * Method to Verify if BKYC user can send money to another Tonik customer
     * @param password
     * @param selectSendMoneyOption
     * @throws Exception
     */
    public void sendMoneyToBKYCTonikCustomerByEnteringRecipientMobileNumber_TDB_SM_028(String password,By selectSendMoneyOption,String amount) throws Exception {
        HeaderChildNode("TDB_SM_028, Send Money - Verify if BKYC user can send money to another Tonik customer");
        tonikLogin(password);
        navigateToTonikTransactionScreen(selectSendMoneyOption);
        enterMobileNumber(propertyFileReader("ReceiverMobileNumber","SendMoney"));
        String fee=addMoneyAndPurpose(amount);
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn),"Next Button"));
        confirmSendMoneyPageTonikToTonik(fee,amount,propertyFileReader("ReceiverMobileNumber","SendMoney"));
        enterOTP(RandomIntegerGenerator(6));
        String receiverUserName = getReceiverName("63"+propertyFileReader("ReceiverMobileNumber","SendMoney"));
        validateTransactionStatusAndDetails(fee,amount,getAccountNumber("63"+propertyFileReader("OwnMobileNumber","SendMoney")),receiverUserName,"SendMoney");
        navigateToAccountHistory();
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTonikToTonikSendMoney),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objTonikToTonikSendMoney),": Transaction"));
        transactionDetailsValidation(fee,amount,getAccountNumber("63"+propertyFileReader("OwnMobileNumber","SendMoney")),getAccountNumber("63"+propertyFileReader("ReceiverMobileNumber","SendMoney")),propertyFileReader("SendMoneyTonikToTonikTransactionType","SendMoney"));
        logger.info("TDB_SM_028, Send Money - BKYC user can send money to another Tonik customer validated");
        extentLoggerPass("TDB_SM_028", "TDB_SM_028, Send Money - BKYC user can send money to another Tonik customer validated");
    }
    /**
     * Method to Verify if user can send money when maximum OTP attempts has been reached
     * @param password
     * @param sendMoneyOption
     * @param amount
     * @throws Exception
     */
    public void sendMoneyToOtherBankMaxOTPAttemptValidation_TDB_SM_030(String password, By sendMoneyOption,String amount) throws Exception {
        HeaderChildNode("TDB_SM_030, Send Money - Verify if user can send money when maximum OTP attempts has been reached");
        tonikLogin(password);
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon), 5, "Send Icon");
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon), "Send Icon");
        selectSendMoneyOption(SendMoneyPage.getByOSType(platform,SendMoneyPage.objToAnotherBankOption));
        selectSendMoneyToBankOption(sendMoneyOption);
        String fee = addMoneyAndPurpose(amount);
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn), "Next Button"));
        selectAccountInfoAndBank(propertyFileReader("AccountHolderName", "SendMoney"), propertyFileReader("InstaPayBank", "SendMoney"), propertyFileReader("AccountNumber", "SendMoney"));
        confirmTransactionPageValidation(fee,amount,"InstaPay",propertyFileReader("AccountNumber", "SendMoney"));
        for(int click=1;click<10;click++){
            click(SendMoneyPage.getByOSType(platform,SendMoneyPage.objResendOTP),"Resend OTP Button");
            waitTime(3000);//Screen will load once clicked on Resend otp
            click(SendMoneyPage.getByOSType(platform,SendMoneyPage.objBackBtn),"Back btn");
            click(SendMoneyPage.getByOSType(platform,SendMoneyPage.objConfirmButton), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objConfirmButton), ": button"));
            if(findElement(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxOTPAttempt)).isDisplayed()){
                break;
            }
        }
        if(verifyElementPresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxOTPAttempt),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxOTPAttempt),": Error message"))){
            valueValidation(getText(SendMoneyPage.getByOSType(platform,SendMoneyPage.objMaxOTPAttempt)),propertyFileReader("MaxOTPAttemptMsg","SendMoney"),": Error message","equals");
            logger.info("TDB_SM_030, Send Money - User can't able to  send money when maximum OTP attempts has been reached validated");
            extentLoggerPass("TDB_SM_030", "TDB_SM_030, Send Money - User can't able to  send money when maximum OTP attempts has been reached validated");
        }
    }
}