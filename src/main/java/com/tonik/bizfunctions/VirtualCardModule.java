package com.tonik.bizfunctions;

import com.tonik.pageObject.LoginPage;
import com.tonik.pageObject.ProfilePage;
import com.tonik.pageObject.VirtualCardPage;
import com.tonik.utility.ExtentReporter;
import com.tonik.utility.Utilities;
import org.openqa.selenium.WebElement;
import java.util.List;
import static com.tonik.utility.ExtentReporter.*;
import static com.tonik.utility.Utilities.*;

public class VirtualCardModule extends BaseClass {
    public VirtualCardModule() {
        super();
    }
    String platform = Utilities.getPlatform();
    /**
     * Method to verify virtual card On OnBoarding Screen
     * @throws Exception
     */
    public void virtualCard() throws Exception {
            waitTime(2000);
            waitForElementToBePresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objVirtualCardOnBoard),2,"Virtual Card");
            verifyElementPresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objVirtualCardOnBoard), "Virtual Card Image On OnBoarding Screen");
            verifyElementPresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objVirtualCardOption), "Virtual Card Image On OnBoarding Screen");
            assertionValidation(getText(VirtualCardPage.getByOSType(platform,VirtualCardPage.objVirtualCardOption)), propertyFileReader("VirtualCardOption", "VirtualCard"), ": Virtual Card option");
            waitForElementToBePresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objMaskedNumbers),3,"Virtual Card Marked");
            verifyElementPresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objMaskedNumbers), getTextVal(VirtualCardPage.getByOSType(platform,VirtualCardPage.objMaskedNumbers), ":Two Masked Number"));
    }
    /**
     * Method to verify Virtual card screen
     * @throws Exception
     */
    public void virtualCardScreen() throws Exception {
           waitForElementToBePresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objCardsHeader),2,"Card Header");
            verifyElementPresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objCardsHeader), "Virtual Cards Header ");
            assertionValidation(getText(VirtualCardPage.getByOSType(platform,VirtualCardPage.objCardsHeader)), propertyFileReader("CardsHeader", "VirtualCard"), ": Virtual Card Header");
            verifyElementPresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objVirtualCardImage), "Virtual Card Image");
            verifyElementPresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objUnmaskedNum), getTextVal(VirtualCardPage.getByOSType(platform,VirtualCardPage.objUnmaskedNum), ": Masked Numbers ON card"));
            verifyElementPresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objEyeIcon), ": Eye Icon On left side Of the Corner");
            containsValidation(getText(VirtualCardPage.getByOSType(platform,VirtualCardPage.objLockCard)), propertyFileReader("LockCard", "VirtualCard"), ": Lock Card");
            containsValidation(getText(VirtualCardPage.getByOSType(platform,VirtualCardPage.objSecurity)), propertyFileReader("Security", "VirtualCard"), ": Security");
            containsValidation(getText(VirtualCardPage.getByOSType(platform,VirtualCardPage.objCardLimits)), propertyFileReader("CardLimits", "VirtualCard"), ": Card Limits");
    }
    /**
     * Method to verify Unmask the Values On virtual card Screen
     * @throws Exception
     */
    public void unmaskTheValuesOnVirtualCardScreen() throws Exception {
        waitForElementToBePresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objEyeIcon),2,"Eye icon");
        verifyElementPresentAndClick(VirtualCardPage.getByOSType(platform,VirtualCardPage.objEyeIcon), "Eye Icon On the Left side of Image");
        verifyElementPresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objUnmaskedNum), getTextVal(VirtualCardPage.getByOSType(platform,VirtualCardPage.objUnmaskedNum), ": UnMasked Numbers ON card"));
        int cardNumber = getText(VirtualCardPage.getByOSType(platform,VirtualCardPage.objUnmaskedNum)).length()-3;
        if(cardNumber==16){
            logger.info("16 digit card number is displayed");
            extentLoggerPass("Card numbers", "16 digit card number is displayed");
        }else{
            logger.info("16 digit card number is not displayed");
            extentLoggerFail("", "16 digit card number is not displayed");
        }
        containsValidation(getText(VirtualCardPage.getByOSType(platform,VirtualCardPage.objUnmaskedNum)), propertyFileReader("CardNumber", "VirtualCard"), ":Card Number");
        containsValidation(getText(VirtualCardPage.getByOSType(platform,VirtualCardPage.objValidThruOption)), propertyFileReader("ValidThru", "VirtualCard"), ":Valid Thru");
        containsValidation(getText(VirtualCardPage.getByOSType(platform,VirtualCardPage.objCvvOption)), propertyFileReader("Cvv", "VirtualCard"), ":CVV");
        verifyElementPresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objCustomerNameOnCard), getTextVal(VirtualCardPage.getByOSType(platform,VirtualCardPage.objCustomerNameOnCard), ": Customer Name ON card"));
        verifyElementPresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objValidThru), getTextVal(VirtualCardPage.getByOSType(platform,VirtualCardPage.objValidThru), ": Valid Thru ON card"));
        containsValidation(getText(VirtualCardPage.getByOSType(platform,VirtualCardPage.objValidThru)), propertyFileReader("ValidThruDate", "VirtualCard"), ":ValidThruDate");
        verifyElementPresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objCvv), getTextVal(VirtualCardPage.getByOSType(platform,VirtualCardPage.objCvv), ": Valid Thru ON card"));
        containsValidation(getText(VirtualCardPage.getByOSType(platform,VirtualCardPage.objCvv)), propertyFileReader("CvvNumber", "VirtualCard"), ":CvvNumber");
    }
    /**
     * Method to verify card limits validation
     * @throws Exception
     */
    public void cardLimitsValidation() throws Exception {
        waitForElementToBePresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objCardLimits),3,"Wait Card Limit");
        verifyElementPresentAndClick(VirtualCardPage.getByOSType(platform,VirtualCardPage.objCardLimits), "Card Limits");
        verifyElementPresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objCardDailyLimitsHeader), "Card Daily Limits Header");
        containsValidation(getText(VirtualCardPage.getByOSType(platform,VirtualCardPage.objCardDailyLimitsHeader)), propertyFileReader("CardDailyLimits", "VirtualCard"), ":Card Daily Limits header");
        containsValidation(getText(VirtualCardPage.getByOSType(platform,VirtualCardPage.objOnlineAndPhysicalPaymentsOption)), propertyFileReader("OnlineAndPhysicalPayments", "VirtualCard"), ":Online and Physical payment");
    }
    /**
     * Method for convert to double
     * @param values
     * @return
     * @throws Exception
     */
    public double getAvailableBalanceInteger(String values) throws Exception {
        String trim = values.replace("₱", "");
        if (trim.contains(",")) {
            trim = trim.replace(",", "");
        }
        return Double.parseDouble(trim);
    }
    /**
     * Method to verify user can access virtual card tile without TSA
     * @throws Exception
     */
    public void verifyTheUserCanAccessVirtualCardTileIfTSAIsNotCreated_TDB_VC_001() throws Exception {
        HeaderChildNode("TDB_VC_001, Virtual Card -Verify The User Can Access  VirtualCard Tile If TSA isCreated_TDB_VC_001");
        tonikLogin(propertyFileReader("password", "Login"));
        virtualCard();
        verifyElementPresentAndClick(VirtualCardPage.getByOSType(platform,VirtualCardPage.objVirtualCardOnBoard), "Virtual Card Image On OnBoarding Screen");
        verifyElementPresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objVirtualCardGraydOut), "Virtual Card Graydout On OnBoarding Screen");
        logger.info("TDB_VC_001, Virtual Card - Verify The User Can Access Virtual Card Tile If TSA is Created_TDB_VC_001");
        extentLoggerPass("TDB_VC_001", "TDB_VC_001, Virtual Card -Verify The User Can Access  VirtualCard Tile If TSA isCreated_TDB_VC_001 is passed");
    }
    /**
     * Method to verify User can access the virtual card if TSA is created
     * @throws Exception
     */
    public void verifyTheUserCanAccessVirtualCardTileIfTSAisCreated_TDB_VC_002() throws Exception {
        HeaderChildNode("TDB_VC_002, Virtual Card -Verify The User Can Access Virtual Card Tile If TSA is Created_TDB_VC_002");
        tonikLogin(propertyFileReader("password", "Login"));
        virtualCard();
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform,ProfilePage.objProfileIcon), "Profile Icon");
        waitForElementToBePresent(ProfilePage.getByOSType(platform,ProfilePage.objAccountInformation), 10, "Account information");
        ProfileModule profileModule = new ProfileModule();
        profileModule.accountInformationUIValidation();
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back icon ");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back icon ");
        virtualCard();
        click(VirtualCardPage.getByOSType(platform,VirtualCardPage.objVirtualCardOnBoard), "Virtual Card Image On OnBoarding Screen");
        virtualCardScreen();
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back icon ");
        if (verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objProfileIcon))) {
            logger.info("TDB_VC_002, Virtual Card - Verify The User Can Access Virtual Card Tile If TSA is Created");
            extentLoggerPass("TDB_VC_002", "TDB_VC_002, Virtual Card -Verify The User Can Access Virtual Card Tile If TSA is Created_TDB_VC_002 is passed");
        }
    }
    /**
     * Method to verify user can hide and Un-hide the virtual card details
     * @throws Exception
     */
    public void verifyIfUserCanHideUnHideTheDetailsOfTheVirtualCard_TDB_VC_003() throws Exception {
        HeaderChildNode("TDB_VC_003, Virtual Card -Verify If User Can Hide UnHide The Details Of The Virtual Card_TDB_VC_003");
        tonikLogin(propertyFileReader("password", "Login"));
        virtualCard();
        click(VirtualCardPage.getByOSType(platform,VirtualCardPage.objVirtualCardOnBoard), "Virtual Card Image On OnBoarding Screen");
        unmaskTheValuesOnVirtualCardScreen();
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back icon ");
        waitForElementToBePresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objVirtualCardOnBoard),10, "Virtual Card Image On OnBoarding Screen");
        click(VirtualCardPage.getByOSType(platform,VirtualCardPage.objVirtualCardOnBoard), "Virtual Card Image On OnBoarding Screen");
        virtualCardScreen();
        unmaskTheValuesOnVirtualCardScreen();
        verifyElementPresentAndClick(VirtualCardPage.getByOSType(platform,VirtualCardPage.objUnMaskedEyeIcon), "Eye Icon On the Left side of Image");
        verifyElementPresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objUnmaskedNum), getTextVal(VirtualCardPage.getByOSType(platform,VirtualCardPage.objUnmaskedNum), ": Masked Numbers ON card"));
        verifyElementNotDisplayed(VirtualCardPage.getByOSType(platform,VirtualCardPage.objCvvOption), "CVV");
        verifyElementNotDisplayed(VirtualCardPage.getByOSType(platform,VirtualCardPage.objValidThruOption), "Valid Thru");
        verifyElementNotDisplayed(VirtualCardPage.getByOSType(platform,VirtualCardPage.objCustomerNameOnCard), "Customer name On Card");
        logger.info("TDB_VC_003, Virtual Card - Verify If User Can Hide UnHide The Details Of The Virtual Card_TDB_VC_003");
        extentLoggerPass("TDB_VC_003", "TBD_VC_003, Virtual Card -Verify If User Can Hide UnHide The Details Of The VirtualCard_TDB_VC_003 is passed");
    }
    /**
     * Method to verify user can lock and unlock the virtual card
     * @throws Exception
     */
    public void verifyIfUserCanLockUnlockTheVirtualCard_TDB_VC_004() throws Exception {
        HeaderChildNode("TDB_VC_004, Virtual Card -Verify If User Can Lock Unlock The VirtualCard_TDB_VC_004");
        tonikLogin(propertyFileReader("password", "Login"));
        virtualCard();
        click(VirtualCardPage.getByOSType(platform,VirtualCardPage.objVirtualCardOnBoard), "Virtual Card Image On OnBoarding Screen");
        verifyElementPresentAndClick(VirtualCardPage.getByOSType(platform,VirtualCardPage.objLockCardToggle), "Lock Card Toggle Button");
        assertionValidation(getAttributValue("clickable", VirtualCardPage.getByOSType(platform,VirtualCardPage.objSecurity)), "false", ": Checked Attribute value");
        assertionValidation(getAttributValue("clickable", VirtualCardPage.getByOSType(platform,VirtualCardPage.objCardLimits)), "false", ": Checked Attribute value");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back icon ");
        verifyElementPresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objVirtualCardOption), "Virtual Card Image On OnBoarding Screen");
        if (verifyElementPresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objLocked), "Locked Option On virtual card in OnBoarding Screen")) {
            logger.info("Virtual Card Is Locked,Security and card limits are not clickable");
            extentLoggerPass("Lock the card", "Virtual Card Is Locked,Security and card limits are not clickable");
        }
        containsValidation(getText(VirtualCardPage.getByOSType(platform,VirtualCardPage.objLocked)), propertyFileReader("Locked", "VirtualCard"), ":Locked Text");
        click(VirtualCardPage.getByOSType(platform,VirtualCardPage.objVirtualCardOnBoard), "Virtual card in OnBoarding Screen");
        click(VirtualCardPage.getByOSType(platform,VirtualCardPage.objSecurity),"Security");
        if (verifyElementDisplayed(VirtualCardPage.getByOSType(platform,VirtualCardPage.objCardsHeader))) {
            logger.info("Security is not clickable");
            extentLoggerPass("Security", "Security is not clickable");
        }else{
            logger.error("Security is clickable");
            extentLoggerFail("Security", "Security is clickable");
        }
        click(VirtualCardPage.getByOSType(platform,VirtualCardPage.objCardLimits),"Card limits");
        if (verifyElementDisplayed(VirtualCardPage.getByOSType(platform,VirtualCardPage.objCardsHeader))) {
            logger.info("Card limits is not clickable");
            extentLoggerPass("Card Limits", "Card limits is not clickable");
        }else{
            logger.error("Card limits is clickable");
            extentLoggerFail("Card Limits", "Card limits is clickable");
        }
        verifyElementPresentAndClick(VirtualCardPage.getByOSType(platform,VirtualCardPage.objLockCardToggle), "Lock Card");
        if (verifyElementPresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objEyeIcon), "Eye icon")) {
            logger.info("Virtual Card Is Unlocked");
            extentLoggerPass("Unlock the card", "Virtual Card Is Unlocked");
        }else{
            logger.error("Virtual Card Is Not Unlocked");
            extentLoggerFail("Unlock the card", "Virtual Card Is Not Unlocked");
        }
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back icon ");
        virtualCard();
        logger.info("TDB_VC_004, Virtual Card - Verify If User Can Lock Unlock The VirtualCard_TDB_VC_004");
        extentLoggerPass("TDB_VC_004", "TDB_VC_004, Virtual Card -Verify If User Can Lock Unlock The Virtual Card_TDB_VC_004 is passed");
    }
    /**
     * Method to verify user can block the virtual card from online payments
     * @throws Exception
     */
    public void verifyIfUserCanBlockTheVirtualCardFromOnlinePayments_TDB_VC_005() throws Exception {
        HeaderChildNode("TDB_VC_005, Virtual Card -Verify If User Can Block The Virtual Card From Online Payments_TDB_VC_005");
        tonikLogin(propertyFileReader("password", "Login"));
        virtualCard();
        click(VirtualCardPage.getByOSType(platform,VirtualCardPage.objVirtualCardOnBoard), "Virtual Card Image On OnBoarding Screen");
        if (verifyElementPresentAndClick(VirtualCardPage.getByOSType(platform,VirtualCardPage.objSecurity), "Security Option")) {
            verifyElementPresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objSecurityHeader), "Security Header");
            containsValidation(getText(VirtualCardPage.getByOSType(platform,VirtualCardPage.objSecurityHeader)), propertyFileReader("Security", "VirtualCard"), ":Security header");
            containsValidation(getText(VirtualCardPage.getByOSType(platform,VirtualCardPage.objSecuritySubTitle)), propertyFileReader("SecurityPageSubTitle", "VirtualCard"), ":Security Page SubTitle");
            containsValidation(getText(VirtualCardPage.getByOSType(platform,VirtualCardPage.objOnlinePayments)), propertyFileReader("OnlinePayments", "VirtualCard"), ":Online Payments");
            containsValidation(getText(VirtualCardPage.getByOSType(platform,VirtualCardPage.objBlockOnlinePayments)), propertyFileReader("BlockOnlinePayments", "VirtualCard"), ":Block Online Payments");
        }
        waitForElementToBePresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objOnlinePaymentsToggle),2,"Toggle Option");
        verifyElementPresentAndClick(VirtualCardPage.getByOSType(platform,VirtualCardPage.objOnlinePaymentsToggle), "Online Payments Toggle");
        verifyElementEnabled(VirtualCardPage.getByOSType(platform,VirtualCardPage.objOnlinePaymentsToggle), " : Online Button Toggle");
        assertionValidation(getAttributValue("enabled", VirtualCardPage.getByOSType(platform,VirtualCardPage.objOnlinePaymentsToggle)), "true", ": Enable Attribute value");
        verifyElementPresentAndClick(VirtualCardPage.getByOSType(platform,VirtualCardPage.objOnlinePaymentsToggle), "Online Payments Toggle");
        assertionValidation(getAttributValue("checked", VirtualCardPage.getByOSType(platform,VirtualCardPage.objOnlinePaymentsToggle)), "false", ": Checked Attribute value");
        verifyElementPresentAndClick(VirtualCardPage.getByOSType(platform,VirtualCardPage.objOnlinePaymentsToggle), "Online Payments Toggle");
        assertionValidation(getAttributValue("enabled", VirtualCardPage.getByOSType(platform,VirtualCardPage.objOnlinePaymentsToggle)), "true", ": Enable Attribute value");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back icon ");
        verifyElementPresentAndClick(VirtualCardPage.getByOSType(platform,VirtualCardPage.objSecurity), "Security Option");
        assertionValidation(getAttributValue("enabled", VirtualCardPage.getByOSType(platform,VirtualCardPage.objOnlinePaymentsToggle)), "true", ": Enable Attribute value");
        logger.info("TDB_VC_005, Virtual Card - Verify If User Can Block The Virtual Card From On linePayments_TDB_VC_005");
        extentLoggerPass("TDB_VC_005", "TBD_VC_005, Virtual Card -Verify If User Can Block The Virtual Card From Online Payments_TDB_VC_005 is passed");
    }
    /**
     * Method to verify user can change the virtual card daily online and physical payments
     * @throws Exception
     */
    public void verifyIfUserCanChangeTheVirtualCardsDailyOnlineAndPhysicalPaymentLimit_TDB_VC_006() throws Exception {
        HeaderChildNode("TDB_VC_006, Virtual Card -Verify If User Can Change The VirtualCard Daily Online Payments_TDB_VC_006");
        tonikLogin(propertyFileReader("password", "Login"));
        virtualCard();
        click(VirtualCardPage.getByOSType(platform,VirtualCardPage.objVirtualCardOnBoard), "Virtual Card Image On OnBoarding Screen");
        cardLimitsValidation();
        waitTime(2000);
        waitForElementToBePresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objDefaultAmount),2,"Online Payments");
        verifyElementPresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objDefaultAmount), getTextVal(VirtualCardPage.getByOSType(platform,VirtualCardPage.objDefaultAmount), ":Default Amount"));
        verifyElementPresentAndClick(VirtualCardPage.getByOSType(platform,VirtualCardPage.objOnlineAndPhysicalPaymentsOption), "Online and physical Payments");
        waitForElementToBePresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objOnlineAndPhysicalPaymentsHeader),10, "Online and physical Payments Header");
        verifyElementPresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objOnlineAndPhysicalPaymentsHeader), "Online and physical Payments Header");
        List<WebElement> values = findElements(VirtualCardPage.getByOSType(platform,VirtualCardPage.objAmountList));
        for (int list = 0; list < values.size(); list++) {
            String displayedItem = values.get(list).getText();
            logger.info("Amount list : " + displayedItem + " is displayed");
            extentLogger(" ", "Amount list : " + displayedItem + " is displayed");
        }
        for (int list = 1; list < values.size(); list++) {
            String sAmountList = getText(VirtualCardPage.objPhysicalPaymentList(platform,list));
            click(VirtualCardPage.objPhysicalPaymentList(platform,list), getTextVal(VirtualCardPage.objPhysicalPaymentList(platform,list), ": Amount list"));
            waitForElementToBePresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objDefaultAmount), 10, "Default Amount");
            click(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back icon ");
            waitForElementToBePresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objCardLimits), 10, "Default Amount");
            verifyElementPresentAndClick(VirtualCardPage.getByOSType(platform,VirtualCardPage.objCardLimits), "Card Limits");
            containsValidation(getText(VirtualCardPage.getByOSType(platform,VirtualCardPage.objDefaultAmount)), sAmountList, ": Default");
            click(VirtualCardPage.getByOSType(platform,VirtualCardPage.objDefaultAmount), "Default Amount");
        }
        logger.info("TDB_VC_006, Virtual Card - Verify If User Can Change The VirtualCards Daily Online And PhysicalPaymentLimit_TDB_VC_006");
        extentLoggerPass("TDB_VC_006", "TDB_VC_006, Verify If User Can Change The VirtualCards Daily Online And PhysicalPaymentLimit_TDB_VC_006 is passed");
    }
    /**
     * Method to verify user maximum cards limit until 250000 if user as SKYC
     * @throws Exception
     */
    public void verifyIfUsersMaximumCardLimitIsUntil250000IfUserIsSKYC_TDB_VC_011() throws Exception {
        HeaderChildNode("TDB_VC_011, Virtual Card -Verify If Users Maximum Card Limit Is Until ₱250_000 If User Is SKYC_TDB_VC_011");
        tonikLogin(propertyFileReader("password", "Login"));
        virtualCard();
        click(VirtualCardPage.getByOSType(platform,VirtualCardPage.objMaskedNumbers),"Virtual Card Marked");
        virtualCardScreen();
        cardLimitsValidation();
        verifyElementPresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objDefaultAmount), getTextVal(VirtualCardPage.getByOSType(platform,VirtualCardPage.objDefaultAmount), ":Default Amount"));
        verifyElementPresentAndClick(VirtualCardPage.getByOSType(platform,VirtualCardPage.objOnlineAndPhysicalPaymentsOption), "Online and physical Payments");
        waitForElementToBePresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objOnlineAndPhysicalPaymentsHeader), 10, "Online and physical Payments Header");
        verifyElementPresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objOnlineAndPhysicalPaymentsHeader), "Online and physical Payments Header");
        List<WebElement> values = findElements(VirtualCardPage.getByOSType(platform,VirtualCardPage.objAmountList));
        double maxAmount = 250000;
        for (int list = 0; list < values.size(); list++) {
            WebElement element = values.get(list);
            System.out.println(element);
            String displayedAmount = values.get(list).getText();
            logger.info("Amount list : " + displayedAmount + " is displayed");
            extentLogger(" ", "Amount list : " + displayedAmount + " is displayed");
            double amount = getAvailableBalanceInteger(displayedAmount);
            if (amount >= maxAmount) {
                maxAmount = amount;
                element.click();
            }
        }
        String maximumAmount = getText(VirtualCardPage.getByOSType(platform,VirtualCardPage.objSKYCMaximumAmount));
        waitForElementToBePresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objDefaultAmount), 3, "Default amount");
        containsValidation(getText(VirtualCardPage.getByOSType(platform,VirtualCardPage.objDefaultAmount)), maximumAmount, ":Default amount");
        logger.info("TDB_VC_011, Virtual Card - Verify If Users Maximum Card Limit Is Until ₱250_000 If User Is SKYC_TDB_VC_011");
        extentLoggerPass("TDB_VC_011", "TDB_VC_011, Virtual Card -Verify If Users Maximum Card Limit Is Until ₱250_000 If User Is SKYC_TDB_VC_011 is passed");
    }
    /**
     * Method to verify user maximum card limit until 40000 if user as BKYC
     * @throws Exception
     */
    public void verifyIfUsersMaximumCardLimitIsOnlyUntil40000IfUserIsBKYC_TDB_VC_010() throws Exception {
        HeaderChildNode("TDB_VC_010, Virtual Card -Verify If Users MaximumCard Limit Is Only Until ₱40000 If User Is BKYC_TDB_VC_010");
        tonikLogin(propertyFileReader("password", "Login"));
        virtualCard();
        click(VirtualCardPage.getByOSType(platform,VirtualCardPage.objVirtualCardOnBoard), "Virtual Card Image On OnBoarding Screen");
        cardLimitsValidation();
        verifyElementPresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objDefaultAmount), getTextVal(VirtualCardPage.getByOSType(platform,VirtualCardPage.objDefaultAmount), ":Default Amount"));
        verifyElementPresentAndClick(VirtualCardPage.getByOSType(platform,VirtualCardPage.objOnlineAndPhysicalPaymentsOption), "Online and physical Payments");
        waitForElementToBePresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objOnlineAndPhysicalPaymentsHeader),10, "Online and physical Payments Header");
        verifyElementPresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objOnlineAndPhysicalPaymentsHeader), "Online and physical Payments Header");
        List<WebElement> values = findElements(VirtualCardPage.getByOSType(platform,VirtualCardPage.objAmountList));
        double maxAmount = 40000;
        for (int list = 0; list < values.size(); list++) {
            WebElement element = values.get(list);
            System.out.println(element);
            String displayedAmount = values.get(list).getText();
            logger.info("Amount list : " + displayedAmount + " is displayed");
            extentLogger(" ", "Amount list : " + displayedAmount + " is displayed");
            double amount = getAvailableBalanceInteger(displayedAmount);
            if (amount >= maxAmount) {
                maxAmount = amount;
                element.click();
            }
        }
        String maximumAmount = getText(VirtualCardPage.getByOSType(platform,VirtualCardPage.objBKYCMaximumAmount));
        waitForElementToBePresent(VirtualCardPage.getByOSType(platform,VirtualCardPage.objDefaultAmount), 3, "Default amount");
        containsValidation(getText(VirtualCardPage.getByOSType(platform,VirtualCardPage.objDefaultAmount)), maximumAmount, ":Default amount");
        logger.info("TDB_VC_010, Virtual Card - Verify If Users MaximumCard Limit Is Only Until ₱40000 If User Is BKYC_TDB_VC_010");
        extentLoggerPass("TDB_VC_010", "TDB_VC_010, Virtual Card -Verify If Users MaximumCard Limit Is Only Until ₱40000 If User Is BKYC_TDB_VC_010 is passed");
    }
}
