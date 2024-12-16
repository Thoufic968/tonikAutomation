package com.tonik.bizfunctions;


import com.driverInstance.DriverManager;
import com.tonik.pageObject.LoginPage;
import com.tonik.pageObject.OnBoardingPage;
import com.tonik.pageObject.SendMoneyPage;
import com.tonik.pageObject.TopUpPage;
import com.tonik.utility.ExtentReporter;
import com.tonik.utility.Utilities;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import static com.tonik.utility.DB_Utilites.selectQuery;
import static com.tonik.utility.Utilities.*;
import static com.tonik.utility.Utilities.waitTime;
import static java.lang.Integer.parseInt;

public class TopUpModule extends BaseClass{
    String platform = Utilities.getPlatform();
    public TopUpModule() {
        super();
    }
    /**
     * Generalized method for Top-up options
     * @throws Exception
     */
    public void topUpOptions() throws Exception {
        waitForElementToBePresent(TopUpPage.getByOSType(platform,TopUpPage.objTopUpOptions),5,"Top up options");
        if (verifyElementPresent(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle), getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle), ": Page Title"))) {
            assertionValidation(getText(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle)),propertyFileReader("TopUpOptions","TopUp"),": page title");
            if(platform.equalsIgnoreCase("ios")){
                List<IOSElement> values = DriverManager.getAppiumDriver().findElements(TopUpPage.getByOSType(platform, TopUpPage.objTopUpOptions));
                for (WebElement value : values) {
                    String displayedItem = value.getText();
                    logger.info("Online Top-up option : '" + displayedItem + "' is displayed");
                    ExtentReporter.extentLogger(" ", "Online Top-up option : '" + displayedItem + "' is displayed");
                }
            } else {
                List<WebElement> values = findElements(TopUpPage.getByOSType(platform, TopUpPage.objTopUpOptions));
                for (WebElement value : values) {
                    String displayedItem = value.getText();
                    logger.info("Online Top-up option : '" + displayedItem + "' is displayed");
                    ExtentReporter.extentLogger(" ", "Online Top-up option : '" + displayedItem + "' is displayed");
                }
            }
            verifyElementPresent(TopUpPage.getByOSType(platform,TopUpPage.objBackBtn),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader),"back button"));
        }
    }
    /**
     * Generalized method for Top-up using PESONet Or InstaPay Page Validation
     * @throws Exception
     */
    public void topUpUsingPESONetOrInstaPayPageValidation() throws Exception {
        waitForElementToBePresent(TopUpPage.getByOSType(platform,TopUpPage.objTopUpBankScreen),5,"Top-up Bank screen");
        if (verifyElementPresent(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle), getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle), ": Page Title"))) {
            assertionValidation(getText(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle)),propertyFileReader("TopUpBankScreen","TopUp"),"Page title");
            containsValidation(getText(TopUpPage.getByOSType(platform,TopUpPage.objPageSubTitle)),propertyFileReader("TopUpSubtitle","TopUp"),": SubTitle");
            assertionValidation(getText(TopUpPage.getByOSType(platform, TopUpPage.objNameHeader)),propertyFileReader("NameHeader","TopUp"),": Field name");
            verifyElementPresent(TopUpPage.getByOSType(platform, TopUpPage.objAccountHolderNameDisplayed),getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objAccountHolderNameDisplayed),": Account holder name"));
            verifyElementPresent(TopUpPage.getByOSType(platform, TopUpPage.objAccountHolderNameCopyIcon),"Account holder name copy icon");
            assertionValidation(getText(TopUpPage.getByOSType(platform, TopUpPage.objBankHeader)),propertyFileReader("BankHeader","TopUp"),": Field name");
            verifyElementPresent(TopUpPage.getByOSType(platform, TopUpPage.objBankNameDisplayed),getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objBankNameDisplayed),": Bank name"));
            assertionValidation(getText(TopUpPage.getByOSType(platform, TopUpPage.objTonikAccountNumberHeader)),propertyFileReader("TonikAccountNumberHeader","TopUp"),": Field name");
            verifyElementPresent(TopUpPage.getByOSType(platform, TopUpPage.objTonikAccountNumberDisplayed),getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objTonikAccountNumberDisplayed),": Tonik Account number"));
            verifyElementPresent(TopUpPage.getByOSType(platform, TopUpPage.objTonikAccountNumberCopyIcon),"Tonik Account number copy icon");
            verifyElementPresent(TopUpPage.getByOSType(platform, TopUpPage.objReminderHeader),getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objReminderHeader),": Header"));
            assertionValidation(getText(TopUpPage.getByOSType(platform, TopUpPage.objReminderHeader)),propertyFileReader("Reminders","TopUp"),"Header");
            if(platform.equalsIgnoreCase("ios")){
                List<IOSElement> values = DriverManager.getAppiumDriver().findElements(TopUpPage.getByOSType(platform, TopUpPage.objReminders));
                for (IOSElement reminderElement : values) {
                    logger.info("Reminder: '" + reminderElement.getText() + "' is displayed");
                    ExtentReporter.extentLogger(" ", "Reminder: '" + reminderElement.getText() + "' is displayed");
                }
            } else {
                List<WebElement> values = findElements(TopUpPage.getByOSType(platform, TopUpPage.objReminders));
                for (WebElement reminderElement : values) {
                    logger.info("Reminder: '" + reminderElement.getText() + "' is displayed");
                    ExtentReporter.extentLogger(" ", "Reminder: '" + reminderElement.getText() + "' is displayed");
                }
            }
            verifyElementPresent(TopUpPage.getByOSType(platform,TopUpPage.objBackBtn),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader),"back button"));
        }
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
     * Generalized method for Top-up via gcash page UI Validation
     * @throws Exception
     */
    public double topUpViaGCashPage(String amount) throws Exception {
        waitForElementToBePresent(TopUpPage.getByOSType(platform, TopUpPage.objAvailableBalance), 5, "Available balance");
        verifyElementPresent(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle), getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle), ": Page Title"));
        assertionValidation(getText(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle)), propertyFileReader("TopUpAmountPage", "TopUp"), ": Page title");
        containsValidation(getText(TopUpPage.getByOSType(platform, TopUpPage.objAvailableBalance)),amount,"Available balance");
        verifyElementPresent(TopUpPage.getByOSType(platform, TopUpPage.objMaxTransaction), getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objMaxTransaction), ": Max transaction amount"));
        verifyElementPresent(TopUpPage.getByOSType(platform, TopUpPage.objTransactionFee), getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objTransactionFee), ""));
        double fee;
        if(!getText(TopUpPage.getByOSType(platform, TopUpPage.objTransactionFee)).contains("Free")) {
            String transactionFee = getText(TopUpPage.getByOSType(platform, TopUpPage.objTransactionFee));
            int length = transactionFee.length();
            String splitTransactionFee = transactionFee.substring(17, length);
            fee = parseInt(splitTransactionFee);
        }else{
            fee = 0;
        }
        verifyElementPresent(TopUpPage.getByOSType(platform, TopUpPage.objTopUPInfo), getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objTopUPInfo), ": Top-up Info"));
        verifyElementPresent(TopUpPage.getByOSType(platform, TopUpPage.objNextButton),getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objNextButton), ": Button"));
        verifyElementPresent(TopUpPage.getByOSType(platform,TopUpPage.objBackBtn), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader), "back button"));
        return fee;
    }
    /**
     * Reusable method to get available balance as Integer
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
     * Generalized method for Online Topup options validation
     * @throws Exception
     */
    public void onlineTopUpOptions() throws Exception {
        waitForElementToBePresent(TopUpPage.getByOSType(platform, TopUpPage.objOnlineTopUpOptions),5,"Online TopUp options");
        if (verifyElementPresent(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle), getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle), ": Page Title"))) {
            assertionValidation(getText(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle)), propertyFileReader("OnlineTopUpOptions","TopUp"),": Page title");
            if(platform.equalsIgnoreCase("ios")){
                List<IOSElement> values = DriverManager.getAppiumDriver().findElements(TopUpPage.getByOSType(platform, TopUpPage.objOnlineTopUpOptions));
                for (IOSElement value : values) {
                    logger.info("Online Top-up option : '" + value.getText() + "' is displayed");
                    ExtentReporter.extentLogger(" ", "Online Top-up option : '" + value.getText() + "' is displayed");
                }
            } else {
                List<WebElement> values = findElements(TopUpPage.getByOSType(platform, TopUpPage.objOnlineTopUpOptions));
                for (WebElement value : values) {
                    logger.info("Online Top-up option : '" + value.getText() + "' is displayed");
                    ExtentReporter.extentLogger(" ", "Online Top-up option : '" + value.getText() + "' is displayed");
                }
            }
            verifyElementPresent(TopUpPage.getByOSType(platform,TopUpPage.objBackBtn),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader),"back button"));
        }
    }
    /**
     * Common method to navigate to account history
     * @throws Exception
     */
    public void navigateToAccountHistory() throws Exception {
        verifyElementPresentAndClick(OnBoardingPage.objHistoryIcon,"History Icon");
        waitTime(3000);//Wait time to load Account history
        verifyElementPresent(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle),getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle),": title"));
        assertionValidation(getText(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle)),propertyFileReader("AccountHistory","TopUp"),": page title");
    }
    /**
     * Generalized method for Transaction details page validation
     * @throws Exception
     */
    public void onlineTransactionDetailsValidation(String amount,String account) throws Exception {
        waitForElementToBePresent(TopUpPage.getByOSType(platform, TopUpPage.objTransactionDetails),3,"Transaction details");
        verifyElementPresent(TopUpPage.getByOSType(platform, TopUpPage.objTransactionDetails),getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objTransactionDetails),": title"));
        if(platform.equalsIgnoreCase("ios")){
            List<IOSElement> values = DriverManager.getAppiumDriver().findElements(TopUpPage.getByOSType(platform, TopUpPage.objTransactionDetailsInfo));
            for (int transactionDetails = 0; transactionDetails <= values.size(); transactionDetails++) {
                if(transactionDetails%2!=0) {
                    verifyElementPresent(TopUpPage.objTransactionDetailsInfo(platform, transactionDetails),getTextVal(TopUpPage.objTransactionDetailsInfo(platform, transactionDetails),"Info"));
                }
            }
        } else {
            List<WebElement> values = findElements(TopUpPage.getByOSType(platform, TopUpPage.objTransactionDetailsInfo));
            for (int transactionDetails = 0; transactionDetails <= values.size(); transactionDetails++) {
                if(transactionDetails%2!=0) {
                    verifyElementPresent(TopUpPage.objTransactionDetailsInfo(platform, transactionDetails),getTextVal(TopUpPage.objTransactionDetailsInfo(platform, transactionDetails),"Info"));
                }
            }
        }
        containsValidation(String.valueOf(getAvailableBalanceInteger(TopUpPage.objTransactionDetailsInfo(platform, "Amount"))),amount,": Transaction details amount");
        containsValidation(getText(TopUpPage.objTransactionDetailsInfo(platform, "From")).toUpperCase(),propertyFileReader("GCashTransaction","TopUp").toUpperCase(),": Transaction details from");
        assertionValidation(getText(TopUpPage.objTransactionDetailsInfo(platform, "To")),account,": Transaction details to");
        containsValidation(getText(TopUpPage.objTransactionDetailsInfo(platform, "When")),getCurrentDate().substring(0,6),": Transaction details date");
    }
    /**
     * Common method to input amount and click on next
     * @param sAmount
     * @throws Exception
     */
    public void inputAmountAndClickNext(String sAmount) throws Exception {
        if (platform.equalsIgnoreCase("iOS")) {
            clearField(TopUpPage.getByOSType(platform, TopUpPage.objAmountInputField), "Amount Input field");
        }
        type(TopUpPage.getByOSType(platform, TopUpPage.objAmountInputField),sAmount,"Amount Input field");
        if (platform.equalsIgnoreCase("iOS")) {
            click(SendMoneyPage.getByOSType(platform, SendMoneyPage.objDoneButton), "Keyboard Done Button");
        }
        click(TopUpPage.getByOSType(platform, TopUpPage.objNextButton),getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objNextButton),": Button"));
    }
    /**
     * Common method to select Online transaction
     * @param onlineTransactionType
     * @throws Exception
     */
    public void selectOnlineTransaction(By onlineTransactionType) throws Exception {
//        waitForElementToBePresent(TopUpPage.getByOSType(platform,TopUpPage.objTopUpOptions),5,"Top up options");
//        waitForElementToBePresent(TopUpPage.objTopUpOption(platform,"Online"),5,"Top-up options");
        waitTime(5000);
//        verifyElementPresent(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle), getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle), ": Page Title"));
        assertionValidation(getText(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle)), propertyFileReader("TopUpOptions","TopUp"),": Page title");
        click(TopUpPage.objTopUpOption(platform,"Online"),getTextVal(TopUpPage.objTopUpOption(platform, "Online"),": Top-up option"));
        waitForElementToBePresent(onlineTransactionType,5,"Online transaction screen");
        click(onlineTransactionType,getTextVal(onlineTransactionType,": Online Top-up Option"));
    }
    /**
     * Common method to validate maximum error message validation
     * @param byLocator
     * @param amount
     * @throws Exception
     */
    public void maxErrorMsgValidation(By byLocator,String amount) throws Exception {
        waitForElementToBePresent(TopUpPage.getByOSType(platform, TopUpPage.objTopUpIcon),5,"Top up Icon");
        verifyElementPresentAndClick(TopUpPage.getByOSType(platform, TopUpPage.objTopUpIcon),"Top-up Icon");
//        selectOnlineTransaction(byLocator);
//        waitForElementToBePresent(TopUpPage.getByOSType(platform, TopUpPage.objAmountInputField),5,"Amount field screen");
//        verifyElementPresent(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle), getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle), ": Page Title"));
//        assertionValidation(getText(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle)), propertyFileReader("TopUpAmountPage","TopUp"),": Page title");
//        clearField(TopUpPage.getByOSType(platform, TopUpPage.objAmountInputField),"Amount input field");
//        assertionValidation(getAttributValue("enabled",TopUpPage.getByOSType(platform, TopUpPage.objNextButton)),"false",": Enable Attribute value");
//        inputAmountAndClickNext(amount);
    }
    /**
     * Method to Verify the user can access the Top up module if TSA is not created
     * @throws Exception
     */
    public void accessTopUpModuleIfTSANotCreated_TDB_TU_001() throws Exception {
        HeaderChildNode("TDB_TU_001, Top-up - Verify the user can access the Top up module if TSA is not created");
        tonikLogin(propertyFileReader("Password","TopUp"));
        verifyElementPresentAndClick(TopUpPage.getByOSType(platform, TopUpPage.objTopUpIcon),"Top-up Icon");
        topUpOptions();
        click(TopUpPage.objTopUpOption(platform,"PESONet or InstaPay"),getTextVal(TopUpPage.objTopUpOption(platform,"PESONet or InstaPay"),": Top-up option"));
        verifyElementPresent(TopUpPage.getByOSType(platform, TopUpPage.objPopupHeader),getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objPopupHeader),": popup header"));
        assertionValidation(getText(TopUpPage.getByOSType(platform, TopUpPage.objPopupHeader)),propertyFileReader("ReadyToStartPopup","TopUp"),": Popup header");
        assertionValidation(getText(TopUpPage.getByOSType(platform, TopUpPage.objPopupDescription)),propertyFileReader("ReadyToStartPopupDescription","TopUp"),": Popup Description");
        click(TopUpPage.getByOSType(platform, TopUpPage.objNotYetButton),getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objNotYetButton),": button"));
        if(verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objProfileName),getTextVal(LoginPage.getByOSType(platform,LoginPage.objProfileName),": Profile name"))){
            waitForElementToBePresent(OnBoardingPage.objHistoryIcon,5,"History Icon");
            verifyElementPresent(OnBoardingPage.objHistoryIcon, "History icon");
            assertionValidation(getAttributValue("enabled", OnBoardingPage.objHistoryIcon), "false", ": History icon");
            logger.info("TDB_TU_001, Top-up - User can't access the Top up module if TSA is not created validated");
            extentLoggerPass("TDB_TU_001", "TDB_TU_001, Top-up - User can't access the Top up module if TSA is not created validated");
        }
    }
    /**
     * Method to Verify the user can access the Top up module if TSA is created
     * @throws Exception
     */
    public void accessTopUpModuleIfTSAIsCreated_TDB_TU_002() throws Exception {
        HeaderChildNode("TDB_TU_002, Top-up - Verify the user can access the Top up module if TSA is created");
        tonikLogin(propertyFileReader("Password","TopUp"));
        verifyElementPresentAndClick(TopUpPage.getByOSType(platform, TopUpPage.objTopUpIcon),"Top-up Icon");
        click(TopUpPage.objTopUpOption(platform, "PESONet or InstaPay"),getTextVal(TopUpPage.objTopUpOption(platform, "PESONet or InstaPay"),": Top-up option"));
        verifyElementPresent(TopUpPage.getByOSType(platform, TopUpPage.objPopupHeader),getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objPopupHeader),": popup header"));
        assertionValidation(getText(TopUpPage.getByOSType(platform, TopUpPage.objPopupHeader)),propertyFileReader("ReadyToStartPopup","TopUp"),": Popup header");
        assertionValidation(getText(TopUpPage.getByOSType(platform, TopUpPage.objPopupDescription)),propertyFileReader("ReadyToStartPopupDescription","TopUp"),": Popup Description");
        click(TopUpPage.getByOSType(platform, TopUpPage.objYesIWantToTopUp),getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objYesIWantToTopUp),": button"));
        click(TopUpPage.getByOSType(platform,TopUpPage.objBackBtn),"Back button");
        if(verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objProfileName),getTextVal(LoginPage.getByOSType(platform,LoginPage.objProfileName),": Profile name"))){
            assertionValidation(getAttributValue("enabled",OnBoardingPage.objHistoryIcon),"true","History icon");
            logger.info("TDB_TU_002, Top-up - user can access the Top up module if TSA is created validated");
            extentLoggerPass("TDB_TU_002", "TDB_TU_002, Top-up - user can access the Top up module if TSA is created validated");
        }
    }
    /**
     * Method to Verify if account holder can copy the account details from the PESONet or InstaPay screen
     * @throws Exception
     */
    public void topUpCopyAccountDetailsFromPESONetAndInstaPayValidation_TDB_TU_003() throws Exception {
        HeaderChildNode("TDB-TU-003, Top-up - Verify if account holder can copy the account details from the PESONet or InstaPay screen");
        tonikLogin(propertyFileReader("Password","TopUp"));
        verifyElementPresentAndClick(TopUpPage.getByOSType(platform, TopUpPage.objTopUpIcon),"Top-up Icon");
        topUpOptions();
        click(TopUpPage.objTopUpOption(platform, "PESONet or InstaPay"),getTextVal(TopUpPage.objTopUpOption(platform, "PESONet or InstaPay"),": Top-up option"));
        waitForElementToBePresent(TopUpPage.getByOSType(platform, TopUpPage.objTopUpBankScreen),5,"Top up bank screen");
        topUpUsingPESONetOrInstaPayPageValidation();
        click(TopUpPage.getByOSType(platform, TopUpPage.objAccountHolderNameCopyIcon),"Account holder name copy icon");
        waitForElementToBePresent(TopUpPage.getByOSType(platform, TopUpPage.objCopyToClipBoardMsg),5,"Copied to click board message");
        valueValidation(getText(TopUpPage.getByOSType(platform, TopUpPage.objCopyToClipBoardMsg)),propertyFileReader("ExceptedCopiedMsg","TopUp"),"Excepted Copied message","equals");
        // added wait time since "copied to clipboard" toast message will not be displayed if we click on copy icon before the 1st toast message disappears"
        waitForElementToBePresent(TopUpPage.getByOSType(platform, TopUpPage.objTonikAccountNumberCopyIcon),5,"Account holder name copy icon");
        click(TopUpPage.getByOSType(platform, TopUpPage.objTonikAccountNumberCopyIcon),"Account holder name copy icon");
        waitForElementToBePresent(TopUpPage.getByOSType(platform, TopUpPage.objCopyToClipBoardMsg),5,"Copied to click board message");
        valueValidation(getText(TopUpPage.getByOSType(platform, TopUpPage.objCopyToClipBoardMsg)),propertyFileReader("ExceptedCopiedMsg","TopUp"),"Excepted Copied message","equals");
        click(TopUpPage.getByOSType(platform,TopUpPage.objBackBtn),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader),"back button"));
        waitForElementToBePresent(TopUpPage.getByOSType(platform, TopUpPage.objTopUpOptions),3,"Top Up Options");
        if(verifyElementPresent(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle), getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle), ": Page Title"))) {
            valueValidation(getText(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle)), propertyFileReader("TopUpOptions","TopUp"),"page title","equals");
            logger.info("TDB-TU-003, Top-up - Account holder can copy the account details from the PESONet or InstaPay screen validated");
            extentLoggerPass("TDB-TU-003", "TDB-TU-003, Top-up - Account holder can copy the account details from the PESONet or InstaPay screen validated");
        }
    }
    /**
     * Method to Verify if account holder can Top up via GCash Account
     * @param sInvalidAmount
     * @param sValidAmount
     * @throws Exception
     */
    public void topUpViaGCashAccount_TDB_TU_004(String sInvalidAmount, String sValidAmount) throws Exception {
        HeaderChildNode("TDB_TU_004, Top-up - Verify if account holder can Top up via GCash Account");
        tonikLogin(propertyFileReader("Password","TopUp"));
        screencapture();
        String availableBalance = getText(OnBoardingPage.objAvailableBalance);
        double sAvailableBalanceBeforeTopUp = getAvailableBalanceInteger(OnBoardingPage.objAvailableBalance);
        extentLoggerPass("TDB_TU_004", "Available Balance : "+sAvailableBalanceBeforeTopUp);
        verifyElementPresentAndClick(TopUpPage.getByOSType(platform, TopUpPage.objTopUpIcon),"Top-up Icon");
        waitTime(5000);
        click(TopUpPage.getByOSType(platform,TopUpPage.objGCashOnlineOption), getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objGCashOnlineOption), ": Online Top-up Option"));
        waitTime(5000);
        click(TopUpPage.getByOSType(platform,TopUpPage.objOneTimeTopUpOption),getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objOneTimeTopUpOption),"Gcash Top-up Option"));
        double fee = topUpViaGCashPage(availableBalance);
        inputAmountAndClickNext(sInvalidAmount);
        inputAmountAndClickNext(sValidAmount);
        waitForElementToBePresent(TopUpPage.getByOSType(platform, TopUpPage.objTopUpViaGCash),5,"Top up via GCash");
        verifyElementPresent(TopUpPage.getByOSType(platform, TopUpPage.objTopUpViaGCash), getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objTopUpViaGCash), ": Page Title"));
        click(TopUpPage.getByOSType(platform, TopUpPage.objProceedToPayButton),getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objProceedToPayButton),": Button"));
        verifyElementPresent(TopUpPage.getByOSType(platform, TopUpPage.objTransactionStatusHeader),getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objTransactionStatusHeader),": Status"));
        assertionValidation(getText(TopUpPage.getByOSType(platform, TopUpPage.objTransactionStatusHeader)),propertyFileReader("TransactionStatus","TopUp"),"Status");
        verifyElementPresent(TopUpPage.getByOSType(platform, TopUpPage.objTransactionInfo),getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objTransactionInfo),"transaction info"));
        double totalAmount = fee+ parseInt(sValidAmount);
        containsValidation(getText(TopUpPage.getByOSType(platform, TopUpPage.objTransactionInfo)).replaceAll(",",""), "₱"+totalAmount,": Transaction Info");
        verifyElementPresentAndClick(TopUpPage.getByOSType(platform,TopUpPage.objViewTransactionDetails),getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objViewTransactionDetails),": Link"));
        onlineTransactionDetailsValidation(String.valueOf(totalAmount),propertyFileReader("TSAAccount","TopUp"));
        waitForElementToBePresent(TopUpPage.getByOSType(platform, TopUpPage.objContactUsIconInTransactionDetails),5,"Contact Us Icon In Transaction Details");
        verifyElementPresentAndClick(TopUpPage.getByOSType(platform, TopUpPage.objContactUsIconInTransactionDetails),getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle),"Contact us Link"));
        contactUsPageUI();
        click(TopUpPage.getByOSType(platform,TopUpPage.objBackBtn),"back button");
        waitTime(3000);// Time required to load entire page
        click(TopUpPage.getByOSType(platform,TopUpPage.objBackBtn),getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objPageHeader),"back button"));
        waitForElementToBePresent(TopUpPage.getByOSType(platform, TopUpPage.objButtonDisplayedInTransactionInfoPage),5,"");
        click(TopUpPage.getByOSType(platform, TopUpPage.objButtonDisplayedInTransactionInfoPage),getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objButtonDisplayedInTransactionInfoPage),": Button"));
        verifyElementPresent(OnBoardingPage.objAvailableBalance,getTextVal(OnBoardingPage.objAvailableBalance,": Available balance after Top-up"));
        double sAvailableBalanceAfterTopUp = getAvailableBalanceInteger(OnBoardingPage.objAvailableBalance);
        if(sAvailableBalanceAfterTopUp==sAvailableBalanceBeforeTopUp+Double.parseDouble(sValidAmount)){
            navigateToAccountHistory();
            verifyElementPresent(TopUpPage.getByOSType(platform, TopUpPage.objAccountNumber),getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objAccountNumber),""));
            click(TopUpPage.getByOSType(platform, TopUpPage.objGCashTransactionHistory),getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objGCashTransactionHistory),"Transaction"));
            onlineTransactionDetailsValidation(String.valueOf(totalAmount),getAccountNumber("63"+propertyFileReader("OwnMobileNumber","TopUp")));
            logger.info("TDB_TU_004, Top-up - Account holder successfully Topped up via GCash Account");
            extentLoggerPass("TDB_TU_004", "TDB_TU_004, Top-up - Account holder successfully Topped up via GCash Account");
        }else{
            logger.info("TDB_TU_004, Top-up - Account holder can't able Top up via GCash Account");
            extentLoggerFail("TDB_TU_004", "TDB_TU_004, Top-up - Account holder can't able Top up via GCash Account");
        }
    }
    /**
     * Method to Verify if user can Top up beyond the maximum limit of GCash
     * @param amount
     * @throws Exception
     */
    public void topUpBeyondMaxLimitOfGCashValidation_TDB_TU_005(String amount) throws Exception {
        HeaderChildNode("TDB_TU_005, Top-up - Verify if user can Top up beyond the maximum limit of GCash");
        tonikLogin(propertyFileReader("Password","TopUp"));
        waitForElementToBePresent(TopUpPage.getByOSType(platform, TopUpPage.objTopUpIcon),5,"Top up Icon");
        verifyElementPresentAndClick(TopUpPage.getByOSType(platform, TopUpPage.objTopUpIcon),"Top-up Icon");
        waitTime(5000);
        click(TopUpPage.getByOSType(platform,TopUpPage.objGCashOnlineOption), getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objGCashOnlineOption), ": Online Top-up Option"));
        waitTime(5000);
        click(TopUpPage.getByOSType(platform,TopUpPage.objOneTimeTopUpOption),getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objOneTimeTopUpOption),"Gcash Top-up Option"));
        waitForElementToBePresent(TopUpPage.getByOSType(platform, TopUpPage.objAmountInputField),5,"Amount field screen");
        verifyElementPresent(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle), getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle), ": Page Title"));
        assertionValidation(getText(TopUpPage.getByOSType(platform,TopUpPage.objPageTitle)), propertyFileReader("TopUpAmountPage","TopUp"),": Page title");
        clearField(TopUpPage.getByOSType(platform, TopUpPage.objAmountInputField),"Amount input field");
        assertionValidation(getAttributValue("enabled",TopUpPage.getByOSType(platform, TopUpPage.objNextButton)),"false",": Enable Attribute value");
        inputAmountAndClickNext(amount);
        if(verifyElementPresent(TopUpPage.getByOSType(platform, TopUpPage.objMaxTransactionErrorMsg),getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objMaxTransactionErrorMsg),": Error message"))) {
            containsValidation(getText(TopUpPage.getByOSType(platform, TopUpPage.objMaxTransactionErrorMsg)), propertyFileReader("ExceptedErrorMsg", "TopUp"), ": Error message");
            logger.info("TDB_TU_005, Top-up - User can't Top up beyond the maximum limit of GCash validated");
            extentLoggerPass("TDB_TU_005", "TDB_TU_005, Top-up - User can't Top up beyond the maximum limit of GCash validated");
        }
    }
    /**
     * Method to Verify if user can Top up beyond the maximum limit of BPI
     * @param amount
     * @throws Exception
     */
    public void topUpBeyondMaxLimitOfBPIValidation_TDB_TU_007(String amount) throws Exception {
        HeaderChildNode("TDB_TU_007, Top-up - Verify if user can Top up beyond the maximum limit of BPI");
        tonikLogin(propertyFileReader("Password","TopUp"));
        maxErrorMsgValidation(TopUpPage.getByOSType(platform, TopUpPage.objBPIOnlineOption),amount);
        if(verifyElementPresent(TopUpPage.getByOSType(platform, TopUpPage.objMaxTransactionErrorMsg),getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objMaxTransactionErrorMsg),": Error message"))) {
            containsValidation(getText(TopUpPage.getByOSType(platform, TopUpPage.objMaxTransactionErrorMsg)), propertyFileReader("BPIMaxTransactionErrorMsg", "TopUp"), ": Error message");
            logger.info("TDB_TU_007, Top-up - User can't Top up beyond the maximum limit of BPI validated");
            extentLoggerPass("TDB_TU_007", "TDB_TU_007, Top-up - Top-up - User can't Top up beyond the maximum limit of BPI validated");
        }
    }
    /**
     * Method to Verify if user can Top up beyond the maximum limit of Chinabank
     * @param amount
     * @throws Exception
     */
    public void topUpBeyondMaxLimitOfChinaBankValidation_TDB_TU_009(String amount) throws Exception {
        HeaderChildNode("TDB_TU_009, Top-up - Verify if user can Top up beyond the maximum limit of Chinabank");
        tonikLogin(propertyFileReader("Password","TopUp"));
        maxErrorMsgValidation(TopUpPage.getByOSType(platform, TopUpPage.objChinaBankOnlineOption),amount);
        if(verifyElementPresent(TopUpPage.getByOSType(platform, TopUpPage.objMaxTransactionErrorMsg),getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objMaxTransactionErrorMsg),": Error message"))) {
            containsValidation(getText(TopUpPage.getByOSType(platform, TopUpPage.objMaxTransactionErrorMsg)), propertyFileReader("ChinaBankMaxTransactionErrorMsg", "TopUp"), ": Error message");
            logger.info("TDB_TU_009, Top-up - User can't Top up beyond the maximum limit of Chinabank validated");
            extentLoggerPass("TDB_TU_009", "TDB_TU_009, Top-up - Top-up - User can't Top up beyond the maximum limit of Chinabank validated");
        }
    }
    /**
     * Method to Verify if user can Top up beyond the maximum limit of RCBC
     * @param amount
     * @throws Exception
     */
    public void topUpBeyondMaxLimitOfRCBCValidation_TDB_TU_011(String amount) throws Exception {
        HeaderChildNode("TDB_TU_011, Top-up - Verify if user can Top up beyond the maximum limit of RCBC");
        tonikLogin(propertyFileReader("Password","TopUp"));
        maxErrorMsgValidation(TopUpPage.getByOSType(platform, TopUpPage.objRCBCOnlineOption),amount);
        if(verifyElementPresent(TopUpPage.getByOSType(platform, TopUpPage.objMaxTransactionErrorMsg),getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objMaxTransactionErrorMsg),": Error message"))) {
            containsValidation(getText(TopUpPage.getByOSType(platform, TopUpPage.objMaxTransactionErrorMsg)), propertyFileReader("RCBCMaxTransactionErrorMsg", "TopUp"), ": Error message");
            logger.info("TDB_TU_011, Top-up - User can't Top up beyond the maximum limit of RCBC validated");
            extentLoggerPass("TDB_TU_011", "TDB_TU_011, Top-up - Top-up - User can't Top up beyond the maximum limit of RCBC validated");
        }
    }
    /**
     * Method to Verify if user can Top up beyond the maximum limit of Unionbank
     * @param amount
     * @throws Exception
     */
    public void topUpBeyondMaxLimitOfUnionBankValidation_TDB_TU_013(String amount) throws Exception {
        HeaderChildNode("TDB_TU_013, Top-up - Verify if user can Top up beyond the maximum limit of Unionbank");
        tonikLogin(propertyFileReader("Password","TopUp"));
        maxErrorMsgValidation(TopUpPage.getByOSType(platform, TopUpPage.objUnionBankOnlineOption),amount);
        if(verifyElementPresent(TopUpPage.getByOSType(platform, TopUpPage.objMaxTransactionErrorMsg),getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objMaxTransactionErrorMsg),": Error message"))) {
            containsValidation(getText(TopUpPage.getByOSType(platform, TopUpPage.objMaxTransactionErrorMsg)).replaceAll(",",""), "₱"+propertyFileReader("UnionBankMaxTransactionErrorMsg", "TopUp"), ": Error message");
            logger.info("TDB_TU_013, Top-up - User can't Top up beyond the maximum limit of Unionbank validated");
            extentLoggerPass("TDB_TU_013", "TDB_TU_013, Top-up - Top-up - User can't Top up beyond the maximum limit of Unionbank validated");
        }
    }
    /**
     * Method to Verify if BKYC user can top-up if account balance is at ₱50,000
     * @param amount
     * @throws Exception
     */
    public void topUpBKYCUserWhenHasMaxAccountBalance_TDB_TU_017(String amount) throws Exception {
        HeaderChildNode("TDB_TU_017, Top-up - Verify if BKYC user can top-up if account balance is at ₱50,000");
        tonikLogin(propertyFileReader("Password","TopUp"));
        verifyElementPresentAndClick(TopUpPage.getByOSType(platform, TopUpPage.objTopUpIcon),"Top-up Icon");
        click(TopUpPage.objTopUpOption(platform, "Online"),getTextVal(TopUpPage.objTopUpOption(platform, "Online"),": Top-up option"));
        click(TopUpPage.getByOSType(platform, TopUpPage.objGCashOnlineOption),getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objGCashOnlineOption),": Online Top-up Option"));
        inputAmountAndClickNext(amount);
        verifyElementPresent(TopUpPage.getByOSType(platform, TopUpPage.objTopUpViaGCash), getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objTopUpViaGCash), ": Page Title"));
        click(TopUpPage.getByOSType(platform, TopUpPage.objProceedToPayButton),getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objProceedToPayButton),": Button"));
        waitForElementToBePresent(TopUpPage.getByOSType(platform, TopUpPage.objButtonDisplayedInTransactionInfoPage),5,"Ok Button");
        click(TopUpPage.getByOSType(platform, TopUpPage.objButtonDisplayedInTransactionInfoPage),getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objButtonDisplayedInTransactionInfoPage),": Button"));
        maxErrorMsgValidation(TopUpPage.getByOSType(platform, TopUpPage.objGCashOnlineOption),amount);
        if(verifyElementPresent(TopUpPage.getByOSType(platform, TopUpPage.objBKYCMaxLimit),getTextVal(TopUpPage.getByOSType(platform, TopUpPage.objBKYCMaxLimit),": error message"))){
            containsValidation(getText(TopUpPage.getByOSType(platform, TopUpPage.objBKYCMaxLimit)).replaceAll("₱",""), propertyFileReader("BKYCTopUpMaxLimit", "TopUp"), ": Error message");
            logger.info("TDB_TU_017, Top-up - BKYC user can't top-up if account balance is at ₱50,000 validated");
            extentLoggerPass("TDB_TU_017", "TDB_TU_017, Top-up - Top-up - BKYC user can't top-up if account balance is at ₱50,000 validated");
        }
    }
}