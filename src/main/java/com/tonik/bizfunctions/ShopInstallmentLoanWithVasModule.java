package com.tonik.bizfunctions;

import com.driverInstance.DriverManager;
import com.jcraft.jsch.JSchException;
import com.tonik.pageObject.*;
import com.tonik.utility.ExtentReporter;
import com.tonik.utility.Utilities;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import static com.tonik.utility.DB_Utilites.selectQuery;
import static com.tonik.utility.DB_Utilites.updateQuery;
import static com.tonik.utility.Utilities.*;

public class ShopInstallmentLoanWithVasModule extends BaseClass{
    public ShopInstallmentLoanWithVasModule() {
        super();
    }
    String platform = Utilities.getPlatform();
    protected ShopInstallmentLoanAPIModule shopInstallmentLoanAPIModule = new ShopInstallmentLoanAPIModule();
    protected TimeDepositModule timeDepositModule = new TimeDepositModule();
    protected LoanCommonMethods loanCommonMethods = new LoanCommonMethods();
    public static DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
    public static String status="";
    public static String tsaAmountInDashboard;
    public static String repaymentAmount;
    //========================================= reusable ==========================================
    public static String getUserId(String mobileNumber) throws JSchException, SQLException {
		return selectQuery("customer","SELECT user_id FROM customer.tdbk_customer_mtb where mobile_no = '"+mobileNumber+"';");
	}
	public static String getLoanRejectionType(String digitalLoanAccountID) throws JSchException, SQLException {
		return selectQuery("customer","SELECT rejectedReason FROM loans.tdbk_digital_loan_application where digitalLoanAccountID ='"+digitalLoanAccountID+"';");
	}
	public static String getLoanStatus(String userId) throws JSchException, SQLException {
		return selectQuery("customer","SELECT applicationStatus FROM loans.tdbk_digital_loan_application where userId ='"+userId+"' and applicationStatus='REJECT';");
	}
    public String getLoanAccountNumber(String userId) throws JSchException, SQLException {
        return selectQuery("", "SELECT loanAccountNumber FROM loans.tdbk_digital_loan_application where userId ='" + userId + "' and applicationStatus='ACTIVATED';");
    }
    public String getUTCTimeZone(){
        ZonedDateTime utcDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return utcDateTime.format(formatter);
    }
    public void updateUTCTime(String mobileNumber) throws Exception {
        updateQuery("update customer.tdbk_customer_mtb set created_dt='"+getUTCTimeZone()+"' where mobile_no = '"+mobileNumber+"';");
        logger.info("Updated UTC Time");
        extentLoggerPass("UTC Time","Updated UTC Time");
    }
    /**
     * Common method to return Update Repayment Date In Loan Repayment Schedule
     * @param repaymentDate
     * @param loanId
     * @param repaymentNumber
     * @return
     */
    public String updateRepaymentDateInLoanRepaymentSchedule(String repaymentDate, String loanId, String repaymentNumber) {
        return updateQuery("update loans.tdbk_loan_repayment_schedule set repaymentDate='" + repaymentDate + "' where loanId='" + loanId + "' and repaymentNumber='" + repaymentNumber + "';");
    }
    /**
     * Common method to return Update Due Date In Loan Installment Details
     * @param date
     * @param loanAccountNumber
     * @return
     */
    public String updateDueDateInLoanInstallmentDetails(String date, String loanAccountNumber) {
        return updateQuery("UPDATE loans.tdbk_loan_installment_details SET dueDate ='" + date + "' where loanAccountNumber='" + loanAccountNumber + "';");
    }
    /**
     * Todays Date Philipines
     * @return
     */
    public static String todaysPHDate(String pattern){
        Date currentDate = new Date();
        SimpleDateFormat date = new SimpleDateFormat(pattern, new Locale("en", "PH"));
        String formattedDate = date.format(currentDate);
        return formattedDate;
    }
    /**
     * Common method to return Get Account Number
     * @param mobileNumber
     * @return
     * @throws Exception
     */
    public String getAccountNumber(String mobileNumber) throws Exception {
        return selectQuery("customer", "SELECT signup_acct_no FROM customer.tdbk_customer_mtb where mobile_no='" + mobileNumber + "';");
    }
    /**
     * Common method to return Get Loan Repayment Details
     * @param fieldName
     * @param loanId
     * @param repaymentNumber
     * @return
     * @throws JSchException
     * @throws SQLException
     */
    public String getLoanRepaymentDetails(String fieldName, String loanId, String repaymentNumber) throws JSchException, SQLException {
        return selectQuery("", "SELECT " + fieldName + " FROM loans.tdbk_loan_repayment_schedule where loanId='" + loanId + "' and repaymentNumber='" + repaymentNumber + "';");
    }
    /**
     * Reusable method to validate Customer care icon and UI
     * @throws Exception
     */
    public void loanTileValidation() throws Exception {
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objEasypaymentsTitle), getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objEasypaymentsTitle)));
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objEasypaymentsTitle)), propertyFileReader("easyPaymentsScreenTitle", "QuickLoanWithVas"), "Need fast cash title", "contains");
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objEayPaymentsSubTitle)), propertyFileReader("easyPaymentsScreenSubTitle", "QuickLoanWithVas"), "Need fast cash sub title", "contains");
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objQuickLoanTileHeader)), propertyFileReader("quickLoanHeader", "ShopInstallmentLoan"), "Quick Loan title", "contains");
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCreditBuilderTile)), propertyFileReader("quickLoanOption", "ShopInstallmentLoan"), "Borrow Message", "contains");
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTileHeader)), propertyFileReader("shoppingTileHeader", "QuickLoanWithVas"), "Shopping Tile title", "contains");
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTile)), propertyFileReader("shoppingOption", "QuickLoanWithVas"), "Shopping tile", "contains");
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingSubTitle)), propertyFileReader("shoppingSubTitle", "QuickLoanWithVas"), "Shopping Tile sub title", "contains");
    }
    /**
     * How Much Do you Need Page
     * @throws Exception
     */
    public void howMuchDoYouNeedPage() throws Exception {
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHowMuchNeedPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHowMuchNeedPage), "Text"))) {
            logger.info("Successufully navigated to  How much do you need? page");
            ExtentReporter.extentLoggerPass("Payments Page", "Successufully navigated to How much do you need? page");
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHowMuchNeedPageBckBtn), "Back Button");
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPesosSymbol), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPesosSymbol), "Symbol"));
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAmount), "Amount"));
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueBtn), "button"));
        } else {
            logger.info("Failed to navigate to How much do you need? page");
            ExtentReporter.extentLoggerPass("How Much do you need Page", "Failed to navigate to How much do you need? page");
        }
    }
    /**
     * Verify QR Hunt Page
     * @throws Exception
     */
    public void verifyQRHuntPage() throws Exception {
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objQRHuntPageHeader)),propertyFileReader("QrHunt","ShopInstallmentLoan")," Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objQRHuntPageSubText1)),propertyFileReader("QrHuntSubTitle1","ShopInstallmentLoan")," Subtext");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objQRHuntPageSubText2)),propertyFileReader("QrHuntSubTitle2","ShopInstallmentLoan")," Subtext");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objApplyForQuickLoanButton)),propertyFileReader("ApplyForQuickLoan","ShopInstallmentLoan")," Button");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objBackToQRScanningButton)),propertyFileReader("BackToQrScanning","ShopInstallmentLoan")," Button");
    }
    /**
     * Verify Scan QR Page
     * @throws Exception
     */
    public void verifyScanQRPage() throws Exception {
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objScanTheQRCodePageHeader)),propertyFileReader("ScanTheQR","ShopInstallmentLoan"), " Page Header");
        verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objScanQrFrame),"Scan Qr Code frame");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objMakeSureTheQrText)),propertyFileReader("MakeSure","ShopInstallmentLoan")," Frame Text");
        verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton));
        verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objContactUsButton),"FAQ Button");
    }
    /**
     * verify Time To Claim Page
     * @param amount
     * @param monthlyInstallment
     * @param type
     * @throws Exception
     */
    public void verifyTimeToClaimYourItemPage(String amount,String monthlyInstallment,String type) throws Exception {
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTimeToClaimYourItemPageHeader)),propertyFileReader("TimeToClaim","ShopInstallmentLoan")," Page Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTimeToClaimPageSubTitle)),propertyFileReader("ReadTheReminders","ShopInstallmentLoan")," Sub Title");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objReminders)),propertyFileReader("Reminders","ShopInstallmentLoan")," Text");
        containsValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objReminder1)),propertyFileReader("Reminder1","ShopInstallmentLoan")," Reminder 1");
        containsValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objReminder2)),propertyFileReader("Reminder2","ShopInstallmentLoan")," Reminder 2");
        containsValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objReminder3)),propertyFileReader("Reminder3","ShopInstallmentLoan")," Reminder 3");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objSummary)),propertyFileReader("Summary","ShopInstallmentLoan")," Text");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objProductCategory)),propertyFileReader("ProducCategory","ShopInstallmentLoan")," Text");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objProductCategoryValue)),propertyFileReader("MobilePhone","ShopInstallmentLoan")," Text");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objFinancedAmountText)),propertyFileReader("FinancedAmount","ShopInstallmentLoan")," Text");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objFinancedAmountValue)),"₱"+decimalFormat.format(Double.parseDouble(amount))," Financed Amount");
//        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objFinanceAmountDescription)),propertyFileReader("FinanceAmounDescription","ShopInstallmentLoan")," Text");
        verifyElementNotDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objFinanceAmountDescription),"minus the P0.00 processing fee text");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objMonthlyInstllmentText)),propertyFileReader("YourMonthlyPayment","ShopInstallmentLoan")," Text");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objMonthlyInstllmentVlue)),"₱"+decimalFormat.format(Double.parseDouble(monthlyInstallment))," Monthly Instalment amount");
        if(type.equalsIgnoreCase("WithVas")){
            containsValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objMonthlyInstllmentDescription)),propertyFileReader("EffectiveInterestWithPayHinga","ShopInstallmentLoan")," Text");
        }
        else{
            containsValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objMonthlyInstllmentDescription)),propertyFileReader("EffectiveInterest","ShopInstallmentLoan")," Text");
        }
        containsValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objReadyToPayButtonText)),propertyFileReader("ReadyToPayButton","ShopInstallmentLoan")," Button");
    }
    /**
     * Verify Already At The Cashier Page
     * @throws Exception
     */
    public void verifyAlreadyAtTheCashierPage() throws Exception {
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objAlreadyAtTheCahierPageHeader)).replaceAll("\\s"," "),propertyFileReader("AlreadyAtTheCashier","ShopInstallmentLoan")," Page Header");
        containsValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTapSendConfirmationCode)),propertyFileReader("TapSendConfirmationCode","ShopInstallmentLoan")," Text");
        containsValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objWillReceiveCode)),propertyFileReader("WillReceiveCode","ShopInstallmentLoan")," Text");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPrepareYourPayment)),propertyFileReader("PrepareYourPayment","ShopInstallmentLoan")," Text");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objSettleTheAmount)),propertyFileReader("SettleTheAmountSettle","ShopInstallmentLoan")," Text");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objEnterTheCodeOnTheNextStep)),propertyFileReader("EnterTheCodeONextStep","ShopInstallmentLoan")," Text");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objAskFor6digitOtp)),propertyFileReader("AskFor6DigitOtp","ShopInstallmentLoan")," Text");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objSendConfirmationCodeButtonText)),propertyFileReader("SendConfirmationCode","ShopInstallmentLoan")," Button");
    }
    /**
     * Verify Leaving So Soon Page
     * @throws Exception
     */
    public void verifyLeavingSoSoonPage() throws Exception {
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLeavingSoSoonPageHeader)),propertyFileReader("LeavingSoSoon","ShopInstallmentLoan")," Page Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLeavingSoonPageDescription)),propertyFileReader("SadToSay","ShopInstallmentLoan")," Page SubTitle");
        for(int reason=0; reason<6; reason++){
            containsValidation(getText(ShopInstallmentLoanPage.objLeavingSoonReasonList(platform,propertyFileReader("LeaveReason"+reason,"ShopInstallmentLoan"))),propertyFileReader("LeaveReason"+reason,"ShopInstallmentLoan")," Reason");
            assertionValidation(getAttributValue("enabled", ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn)), "false", ": Enable Attribute value");
        }
        for(int reason=0; reason<6; reason++){
            click(ShopInstallmentLoanPage.objLeavingSoonReasonList(platform,propertyFileReader("LeaveReason"+reason,"ShopInstallmentLoan")),propertyFileReader("LeaveReason"+reason,"ShopInstallmentLoan"));
            assertionValidation(getAttributValue("enabled", ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn)), "true", ": Enable Attribute value");
        }
    }
    /**
     * VerifyLeaving So Soon Page Header Validation
     * @throws Exception
     */
    public void verifyLeavingSoonPageHeaders() throws Exception {
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseApplicationButton)," Leave Application Button");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLeavingSoSoonPageHeader)),propertyFileReader("LeavingSoSoon","ShopInstallmentLoan")," Page Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLeavingSoonPageDescription)),propertyFileReader("SadToSay","ShopInstallmentLoan")," Page SubTitle");
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopLeftSideBackArrowButton),"Back Arrow Button");
    }
    /**
     * Field Of work Validation
     * @param fieldOfWork
     * @throws Exception
     */
    public  void fieldOfWork(String fieldOfWork) throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objFieldOfWorkPage),5,"Field of work");
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"))) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("FieldOfWorkPage", "QuickLoanWithVas"), ": page title");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("FieldOfWorkPageSubtitle", "QuickLoanWithVas"), ": page subtitle");
            List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objList));
            for (int info = 0; info < values.size(); info++) {
                String displayedItem = values.get(info).getText();
                logger.info("Field of work : '" + displayedItem + "' is displayed");
                ExtentReporter.extentLogger(" ", "Field of work : '" + displayedItem + "' is displayed");
            }
            assertionValidation(getAttributValue("enabled",QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)),"false",": Enable Attribute value");
            type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objFieldOfWokSearchField),fieldOfWork,"Field name");
            if(getPlatform().equalsIgnoreCase("ios")){
                click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objdoneButton)," Done Button");
            }
            verifyElementPresentAndClick(QuickLoanWithVasPage.objList(platform,1),getTextVal(QuickLoanWithVasPage.objList(platform,1), ": Field Of Work"));
            assertionValidation(getAttributValue("enabled",QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)),"true",": Enable Attribute value");
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn),getTextVal(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn),": button"));
        }
    }
    /**
     * Select Occupation Validation
     * @param occupation
     * @throws Exception
     */
    public  void selectOccupation(String occupation) throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objWhatsYourJobRolePage),5,"What's your Job Role? Screen");
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"))) {
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("WhatsYourJobRolePage", "QuickLoanWithVas"), ": page title");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("WhatsYourJobRolePageSubtitle", "QuickLoanWithVas"), ": page subtitle");
            List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objList));
            for (int info = 0; info < values.size(); info++) {
                String displayedItem = values.get(info).getText();
                logger.info("Occupation : '" + displayedItem + "' is displayed");
                ExtentReporter.extentLogger(" ", "Occupation : '" + displayedItem + "' is displayed");
            }
            assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "false", ": Enable Attribute value");
            type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objFieldOfWokSearchField), occupation, "occupation input field");
            if(getPlatform().equalsIgnoreCase("ios")){
                click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objdoneButton)," Done Button");
            }
            verifyElementPresentAndClick(QuickLoanWithVasPage.  objList(platform,1), getTextVal(QuickLoanWithVasPage.objList(platform,1), ": Occupation"));
            assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "true", ": Enable Attribute value");
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn), getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn))+ ": button");
        }
    }
    /**
     * Select Industry Validation
     * @param industry
     * @param subIndustry
     * @throws Exception
     */
    public  void selectIndustry(String industry, String subIndustry) throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIndustrySelectionScreen),5,"Industry Selection screen");
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"))) {
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("IndustrySelectionScreen", "QuickLoanWithVas"), ": page title");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("IndustrySelectionScreenSubTitle", "QuickLoanWithVas"), ": page subtitle");
            List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objList));
            for (int industryName = 1; industryName <= values.size(); industryName++) {
                assertionValidation(getText(QuickLoanWithVasPage.objListFromSelectIndustry(platform,industryName)), propertyFileReader("Industry"+industryName,"QuickLoanWithVas"), ": Industry "+industryName);
            }
            waitTime(3000);
            verifyElementPresentAndClick(QuickLoanWithVasPage.objSelectIndustry(platform,industry), getTextVal(QuickLoanWithVasPage.objSelectIndustry(platform,industry), ": industry"));
            assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "false", ": Enable Attribute value");
            click(ShopInstallmentLoanPage.objSelectSubIndustry(platform,subIndustry), getTextVal(ShopInstallmentLoanPage.objSelectSubIndustry(platform,subIndustry), ": subIndustry"));
            assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "true", ": Enable Attribute value");
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn), getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn))+ ": button");
        }
    }
    /**
     * Select Marital Status
     * @param status
     * @throws Exception
     */
    public  void selectMaritalStatus(String status) throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMaritalStatusPage),5,"Marital status selection screen");
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"))) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("MaritalStatus", "QuickLoanWithVas"), ": page title");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("MaritalStatusSubTitle", "QuickLoanWithVas"), ": page subtitle");
            List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objList));
            for (int maritalStatusOption = 1; maritalStatusOption <= values.size(); maritalStatusOption++) {
                assertionValidation(getText(QuickLoanWithVasPage.objListFromSelectIndustry(platform,maritalStatusOption)), propertyFileReader("MaritalStatusOption"+maritalStatusOption,"QuickLoanWithVas"), ": Marital Status Option "+maritalStatusOption);
            }
            assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "false", ": Enable Attribute value");
            verifyElementPresentAndClick(QuickLoanWithVasPage.objSelectMaritalStatus(platform,status), getTextVal(QuickLoanWithVasPage.objSelectMaritalStatus(platform,status), ": marital status"));
            assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "true", ": Enable Attribute value");
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn), getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn))+ ": button");
        }
    }
    /**
     * Select Kids Or dependents Page Validation
     * @param kidsDependent
     * @throws Exception
     */
    public  void selectKidsOrDependents(String kidsDependent) throws Exception {
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"))) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("SelectNoOfKids", "QuickLoanWithVas"), ": page title");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("SelectNoOfKidsSubTitle", "QuickLoanWithVas"), ": page subtitle");
            List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objList));
            for (int kids = 1; kids <= values.size(); kids++) {
                assertionValidation(getText(QuickLoanWithVasPage.objListFromSelectIndustry(platform,kids)), propertyFileReader("DependentsOption"+kids,"QuickLoanWithVas"), ": Dependents Option "+kids);
            }
            assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "false", ": Enable Attribute value");
            verifyElementPresentAndClick(QuickLoanWithVasPage.objSelectMaritalStatus(platform,kidsDependent), getTextVal(QuickLoanWithVasPage.objSelectMaritalStatus(platform,kidsDependent), ": Kids/Dependents"));
            assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "true", ": Enable Attribute value");
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn), getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn))+ ": button");
        }
    }
    /**
     * Select Highest Education Page Validation
     * @param higherEducation
     * @throws Exception
     */
    public  void selectHighestEducationalAttainment(String higherEducation) throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHighestEducationalAttainment),5,"Highest Educational attainment");
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"))) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("HighestEducationalAttainment", "QuickLoanWithVas"), ": page title");
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("HighestEducationalAttainmentSubTitle", "QuickLoanWithVas"), ": page subtitle");
            List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objList));
            for (int education = 1; education <= values.size(); education++) {
                assertionValidation(getText(QuickLoanWithVasPage.objListFromSelectIndustry(platform,education)), propertyFileReader("EducationalAttainment"+education,"QuickLoanWithVas"), ": Educational Attainment "+education);
            }
            assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "false", ": Enable Attribute value");
            verifyElementPresentAndClick(QuickLoanWithVasPage.objSelectMaritalStatus(platform,higherEducation), getTextVal(QuickLoanWithVasPage.objSelectMaritalStatus(platform,higherEducation), ": Kids/Dependents"));
            assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "true", ": Enable Attribute value");
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn), getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn))+ ": button");
        }
    }
    /**
     * Input Income Page Validation
     * @param sAmount
     * @param exceptedError
     * @throws Exception
     */
    public void inputIncomeAmountAndNext(String sAmount, String exceptedError) throws Exception {
        type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyIncomeField),sAmount,"Income input field");
        if(getPlatform().equalsIgnoreCase("ios")){
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objdoneButton)," Done Button");
        }
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn), getTextVal(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn), ": button"));
        if(verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInvalidIncomeMsg))){
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInvalidIncomeMsg)),exceptedError,": Error message");
        }
    }
    /**
     * Update First Name and Last Name Relation Ship
     * @throws Exception
     */
    public void updateFirstNameLastNameAndRelationShip() throws Exception {
        type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objFirstNameInputField),propertyFileReader("FirstName","QuickLoanWithVas"),"First name input field");
        if(getPlatform().equalsIgnoreCase("ios")){
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objdoneButton)," Done Button");
        }
        type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLastNameInputField),propertyFileReader("LastName","QuickLoanWithVas"),"Last name input field");
        if(getPlatform().equalsIgnoreCase("ios")){
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objdoneButton)," Done Button");
        }
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRelationShipDropdown),"Relationship dropdown");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRelationShipPopup),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRelationShipPopup),"Popup"));
        click(QuickLoanWithVasPage.objSelectRelationShip(platform,propertyFileReader("RelationShip","QuickLoanWithVas")), getTextVal(QuickLoanWithVasPage.objSelectRelationShip(platform,propertyFileReader("RelationShip","QuickLoanWithVas")),": Relationship"));
    }
    /**
     * Is this Your current Address Page
     * @throws Exception
     */
    public void isThisYourCurrentAddressConfirmation() throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIsThisYourCurrentAddPage),5,"Is this your current address? Screen");
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"))) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("IsThisYourCurrentAddPage", "QuickLoanWithVas"), ": page title");
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("IsThisYourCurrentAddPageSubtitle", "QuickLoanWithVas"), ": page subtitle");
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYesThisMyAddressBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYesThisMyAddressBtn),": button"));
        }
    }
    /**
     * Input Income Company And TIN
     * @param tinNumber
     * @param income
     * @throws Exception
     */
    public void inputIncomeCompanyAndTINDetails(String tinNumber, String income) throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyIncomeScreen),5,"Monthly Income Screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("MonthlyIncomeScreen", "QuickLoanWithVas"), ": page title");
        inputIncomeAmountAndNext(income,"");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInputCompanyScreen),5,"Where do you work, luv? Screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("InputCompanyScreen", "QuickLoanWithVas"), ": page title");
        clearField(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCompanyInputField)," CompanyName TextField");
        type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCompanyInputField),propertyFileReader("Company","ShopInstallmentLoan"),"Company Input Field");
        if(getPlatform().equalsIgnoreCase("ios")){
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objdoneButton)," Done Button");
        }
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUpdateButton),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUpdateButton),": button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTINInputField),5,"TIN Input screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("TINScreen", "QuickLoanWithVas"), ": page title");
        type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTINInputField),tinNumber,"TIN Input field");
        if(getPlatform().equalsIgnoreCase("ios")){
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objdoneButton)," Done Button");
        }
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn),getTextVal(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn),": button"));
    }
    /**
     * Input Mobile Number And Click On Next
     * @param mobileNumber
     * @throws Exception
     */
    public void inputMobileNumberAndNext(String mobileNumber) throws Exception {
        clearField(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSecondaryContactInputField),"Text field");
        type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSecondaryContactInputField),mobileNumber,"Secondary contact input field");
        if(getPlatform().equalsIgnoreCase("ios")){
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objdoneButton)," Done Button");
        }
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn), getTextVal(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn), ": button"));
    }
    /**
     * Another Way To Reach You Page
     * @throws Exception
     */
    public void anotherWayToReachYou() throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAnotherWayToReachYou),5,"Another way to reach you? Screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("AnotherWayToReachYou", "QuickLoanWithVas"), ": page title");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSkipBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSkipBtn),"Button"));
    }
	/**
	 * Reusable method to Apply zip code
	 * @param zipCode
	 * @throws Exception
	 */
	public void applyZipCode(String zipCode) throws Exception {
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objZipCodeInputField), 5, "Zip code page");
		type(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objZipCodeInputField),zipCode,"Zip code input field");
        if(getPlatform().equalsIgnoreCase("ios")&&verifyElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objdoneButton))){
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objdoneButton)," Done Button");
        }
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objApplyZipCode),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objApplyZipCode),": button"));
	}
    /**
     * Update Contact For Reference Page
     * @param sContactNumber
     * @throws Exception
     */
	public void updateContactForReference(String sContactNumber) throws Exception {
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactsIcon),3,"Contacts Icon");
		if(verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactsPopup))){
			verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactsPopup),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactsPopup),": Contacts Permission popup"));
			click(LoginPage.getByOSType(platform,LoginPage.objAllowBtn),getTextVal(LoginPage.getByOSType(platform,LoginPage.objAllowBtn),": Button"));
		}
		verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactsIcon),"Contacts Icon");
		waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSelectReferenceScreen),5,"Select reference Screen");
		verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("SelectReferenceScreen", "QuickLoanWithVas"), ": page title");
		assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("SelectReferenceScreenSubtitle", "QuickLoanWithVas"), ": page subtitle");
        clearField(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReferenceSearchField)," Search field");
		type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReferenceSearchField),sContactNumber,"Search field");
        if(getPlatform().equalsIgnoreCase("ios")){
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objdoneButton)," Done Button");
        }
		click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objSearchedReferenceNumber),getTextVal(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objSearchedReferenceNumber),"Contact"));
	}
    /**
     * Update Contact Reference
     * @param contactNumber
     * @throws Exception
     */
	public void updateContactReference(String contactNumber) throws Exception {
		updateContactForReference(contactNumber);
		updateFirstNameLastNameAndRelationShip();
		click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn), getTextVal(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn), ": button"));
	}
    /**
     * Segmentaiton 2 Page Navigation For SIL
     * @param tinNumber
     * @param income
     * @param contact1
     * @param contact2
     * @throws Exception
     */
    public void loanSegment2NavigationForSIL(String tinNumber, String income, String contact1, String contact2) throws Exception {
        selectFieldOfWork();
        selectOccupationAndClickOnNext(propertyFileReader("Occupation", "ShopInstallmentLoan"));
        selectIndustryAndSubIndustary();
        selectMaritialStatus();
        selectDependentType();
        selectEducationType();
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactReferencePage), 5, "Contact reference page");
        updateContactReference(contact1);
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHaveAnotherReference), 5, "Have another Reference Screen");
        updateContactReference(contact2);
        isThisYourCurrentAddressConfirmation();
        inputIncomeCompanyAndTINDetails(tinNumber,income);
        anotherWayToReachYou();
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objOkBtn),getTextVal(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objOkBtn),"button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionScreen),5,"Terms and Condition Screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionScreen),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionScreen),": page"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionSubtitle)),propertyFileReader("TermsAndConditionScreenSubtitle","QuickLoanWithVas"),"Page Subtitle");
        assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIAcceptBtn)), "false", ": Enable Attribute value");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionCheckBox),"Terms And Condition Check Box");
        assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIAcceptBtn)), "true", ": Enable Attribute value");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIAcceptBtn),"I accept and give my consent button");
        if(verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHoldOnPopupTitle))){
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHoldOnPopupTitle)),propertyFileReader("HoldOnBabeScreen","ShopInstallmentLoan"),"Page Title");
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHoldOnPopupSubTitle)),propertyFileReader("HoldOnBabeScreenSubHeader","ShopInstallmentLoan"),"Page Subtitle");
        }
    }
    /**
     * Verify Approved Page
     * @param loanableAmount
     * @param monthlyInstallmentAmount
     * @param tenure
     * @throws Exception
     */
    public void verifyLoanApprovedPage(String loanableAmount,String monthlyInstallmentAmount,String tenure) throws Exception {
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objYouGotApprovedPageHeader)),propertyFileReader("LoanApprovedPage","ShopInstallmentLoan")," Page Header");
        containsValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTheOfferIsValidFor)),propertyFileReader("TheOfferIsValid","ShopInstallmentLoan")," Page SubTitle");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objApprovedAmountText)),propertyFileReader("ApprovedAmount","ShopInstallmentLoan")," Text");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objApprovedAmount)),"₱"+loanableAmount," Loan Amount");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objConfirmAppliedInstallmentTermText)),propertyFileReader("ConfirmAppliedInstallment","ShopInstallmentLoan")," Text");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objMonthlyAmount)),"₱"+monthlyInstallmentAmount+" per month","Monthly Installment Amount");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objMonths)),tenure+" months","Tenure");
        verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objIAcceptTheOffer),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objIAcceptTheOffer)));
    }
    /**
     * Tell Us Your Page Validation
     * @throws Exception
     */
    public void tellUsYourPayDayPageValidation() throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,(QuickLoanWithVasPage.objMainTitleTxt)), 3, getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt)));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), "Page Header"));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("tellUsPageHeader", "QuickLoanWithVas"), "Page Header", "contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSubTitleTxt)), propertyFileReader("weAreNotTxt", "QuickLoanWithVas"), "Sub Header", "contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objFifthOfTheMonth)), propertyFileReader("fifthOfMonth", "QuickLoanWithVas"), "Radio Button Option", "contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTenthOftheMonth)), propertyFileReader("tenthOfMonth", "QuickLoanWithVas"), "Radio Button Option", "contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objFifteenthOfTheMonth)), propertyFileReader("fifteenthOfMonth", "QuickLoanWithVas"), "Radio Button Option", "contains");
        boolean disableNextButton = findElement(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)).isEnabled();
        softAssert.assertEquals(disableNextButton,false);
    }
    /**
     * verify Monthly Installment Summary Page
     * @param tenure
     * @param monthlyInstallmentAmount
     * @throws Exception
     */
    public void verifyMonthlyInstallmentSummaryPage(String tenure,String monthlyInstallmentAmount) throws Exception {
        waitTime(5000);
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageHeader)),propertyFileReader("MonthlyInstallmentSummary","ShopInstallmentLoan")," Page Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageSubTitle)),propertyFileReader("ForgotToReview","ShopInstallmentLoan")," Page Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoanProductText)),propertyFileReader("LoanProduct","ShopInstallmentLoan")," Text");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoanProductName)),propertyFileReader("ShopInstallmentLoan","ShopInstallmentLoan")," Loan Product");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objInstallmentPeriodText)),propertyFileReader("InstallmentPeriod","ShopInstallmentLoan")," Text");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objInstallmentPeriod)),tenure+" months"," Installment period");
        if(monthlyInstallmentAmount.equalsIgnoreCase(decimalFormat.format(Double.parseDouble(propertyFileReader("MonthlyInstallmentWithVas","ShopInstallmentLoan"))))){
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPayHingaFeeText)),propertyFileReader("PayHinga","ShopInstallmentLoan")," Text");
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPayHingaFeeValue)),"₱"+propertyFileReader("PayHingaAmount","ShopInstallmentLoan")," PayHinga Amount");
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTotalMonthlyInstallmentText)).replaceAll("\\s"," "),propertyFileReader("TotalMonthlyInstallment","ShopInstallmentLoan")," Text");
        }
        else {
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTotalMonthlyInstallmentText)).replaceAll("\\s"," "),propertyFileReader("TotalMonthlyInstallmentWithoutVas","ShopInstallmentLoan")," Text");
        }
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTotalMonthlyInstallmentAmount)),"₱"+monthlyInstallmentAmount," Installment Amount");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objDueDateText)),propertyFileReader("DueDate","ShopInstallmentLoan")," Text");
        verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objDueDate),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objDueDate)));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objMonthlyAddOnText)),propertyFileReader("MonthlyAddOn","ShopInstallmentLoan")," Text");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objMonthlyAddOnDescription)).replaceAll("\\s"," "),propertyFileReader("MonthlyAddOnDescription","ShopInstallmentLoan")," Description");
        verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objConfirmButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objConfirmButton)));
    }
    /**
     * Signe And Seal Delivery Page
     * @throws Exception
     */
    public void signedSealedDeliveredPageValidation() throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignedPage), 10, getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignedPage), "Page"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignedPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignedPage), "Page"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignedPageSubHeader)).trim(), propertyFileReader("holdOnTxt", "QuickLoanWithVas"), "Page Sub header");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPromissoryNoteTxt)), propertyFileReader("promissoryNoteTxt", "QuickLoanWithVas"), "Text");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDisclouseTxt)), propertyFileReader("disclosureTxt", "QuickLoanWithVas"), "Text");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAmortizationtxt)), propertyFileReader("amortizationTxt", "QuickLoanWithVas"), "Text");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignPromissoryNoteBtn)), propertyFileReader("signBtnTxt", "QuickLoanWithVas"), "Button Text");
        isButtonEnabled(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignPromissoryNoteBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignPromissoryNoteBtn),"Button"));
    }
    /**
     * Promise Note Page Validation
     * @throws Exception
     */
    public void promissoryNotePageValidation() throws Exception {
        waitTime(5000);
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), "Page"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("promissoryNotePageHeader", "QuickLoanWithVas"), "Page Header");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadyToSignButton)), propertyFileReader("readyToSignBtnTxt", "QuickLoanWithVas"), "Button Text");
        isButtonEnabled(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadyToSignButton), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadyToSignButton),"Button Text"));
    }
    /**
     * Your Sign Required Page Validation
     * @throws Exception
     */
    public void yourSignatureRequiredPageValidation() throws Exception {
        waitTime(2000);
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt),"Page Header"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("yourSignatureTxt", "QuickLoanWithVas"), "Page Header");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSubTitleTxt)), propertyFileReader("youCanUseTxt", "QuickLoanWithVas"), "Sub Header");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownSubTxt)), propertyFileReader("bySigningTxt", "QuickLoanWithVas"), "Down Sub Header");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objEraseBtn), "Erase Button");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactUsButton), "Contact Us Button");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
    }
    /**
     * Disclosure Page Validation
     * @throws Exception
     */
    public void disclosureStatementPageValidation() throws Exception {
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt),"Page Header"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("disclousureTxt", "QuickLoanWithVas"), "Page Header");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadyToSignButton)), propertyFileReader("readyToSignBtnTxt", "QuickLoanWithVas"), "Page Header");
        isButtonEnabled(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign");
    }
    /**
     * Amortization Page Validation
     * @throws Exception
     */
    public void amortizationScheduleValidation() throws Exception {
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt),"Page Header"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("amortizationSchedule", "QuickLoanWithVas"), "Page Header");
        isButtonEnabled(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objConfirmBtn), "Sign Disclosure Statement Button");
    }
    /**
     * validate And Sign All The Pages
     * @throws Exception
     */
    public void validateAndSignAllThePages() throws Exception {
        waitTime(4000);
        signedSealedDeliveredPageValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignPromissoryNoteBtn), "Sign Promissory Note Button");
        waitTime(4000);
        promissoryNotePageValidation();
        waitTime(4000);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign");
        yourSignatureRequiredPageValidation();
        updateSignature();
        isButtonEnabled(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignPromissoryNoteBtn), "Sign Promissory Note Button");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignPromissoryNoteBtn), "Sign Promissory Note Button");
        waitTime(4000);
        disclosureStatementPageValidation();
        waitTime(4000);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign Button");
        waitTime(4000);
        yourSignatureRequiredPageValidation();
        updateSignature();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignDisclosureStatementBtn), "Sign Disclosure Statement Button");
        waitTime(4000);
        amortizationScheduleValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign Button");
        yourSignatureRequiredPageValidation();
        updateSignature();
        waitTime(4000);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignAmortizationScheduleBtn), "Sign Amortization Schedule");
        waitTime(4000);
    }
    /**
     * verify Your Purchased Page
     * @param purchasedAmount
     * @throws Exception
     */
    public void verifyPurchasedPage(String purchasedAmount) throws Exception {
        waitForElementToBePresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objShopInstallmentLoan),3," Purchased Page");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objShopInstallmentLoan)),propertyFileReader("ShopInstallmentLoan","ShopInstallmentLoan")," Text");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objThankYouForPurchase)),propertyFileReader("ThankYouForPurchase","ShopInstallmentLoan")," Text");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPurchaseAmount)),"₱"+purchasedAmount," Purchased Amount");
        containsValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPurchaseDate)).replaceAll(",",""),todaysPHDate("dd MMM yyyy")," Purchased Date");
        String[] purchasedDetails={propertyFileReader("StoreName","ShopInstallmentLoan"),propertyFileReader("CategoryName","ShopInstallmentLoan"),propertyFileReader("ProcessingFeeValue","ShopInstallmentLoan")};
        for(int detail=0;detail<purchasedDetails.length;detail++){
            assertionValidation(getText(ShopInstallmentLoanPage.objPurchaseDetails(platform,propertyFileReader("PurchasedDetail"+detail,"ShopInstallmentLoan"))),propertyFileReader("PurchasedDetail"+detail,"ShopInstallmentLoan")," Text");
            containsValidation(getText(ShopInstallmentLoanPage.objPurchaseDetailsValue(platform,propertyFileReader("PurchasedDetail"+detail,"ShopInstallmentLoan"))),purchasedDetails[detail],propertyFileReader("PurchasedDetail"+detail,"ShopInstallmentLoan"));
        }
        assertionValidation(getText(ShopInstallmentLoanPage.objPurchaseDetails(platform,propertyFileReader("AgreementNo","ShopInstallmentLoan"))),propertyFileReader("AgreementNo","ShopInstallmentLoan")," Text");
        verifyElementPresent(ShopInstallmentLoanPage.objPurchaseDetailsValue(platform,propertyFileReader("AgreementNo","ShopInstallmentLoan")),getText(ShopInstallmentLoanPage.objPurchaseDetailsValue(platform,propertyFileReader("AgreementNo","ShopInstallmentLoan"))));
        verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objBackToDashboard),"Back To Dashboard Button");
    }
    /**
     * Verify Loan Information Page
     * @param loanAmount
     * @param borrowDate
     * @param installmentTerm
     * @param monthlyPayment
     * @param insuranceCoverage
     * @throws Exception
     */
    public void verifyLoanInformationPage(String loanAmount,String borrowDate,String installmentTerm,String monthlyPayment,String insuranceCoverage) throws Exception {
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageHeader)),propertyFileReader("LoanInformation","ShopInstallmentLoan")," Text");
        String[] loanDetail={"₱"+loanAmount,borrowDate,installmentTerm+" months","₱"+monthlyPayment,"₱"+insuranceCoverage};
        for(int detail=0;detail<5;detail++){
            assertionValidation(getText(ShopInstallmentLoanPage.objLoanDetails(platform,propertyFileReader("LoanDetail"+detail,"ShopInstallmentLoan"))),propertyFileReader("LoanDetail"+detail,"ShopInstallmentLoan")," detail");
            containsValidation(getText(ShopInstallmentLoanPage.objLoanDetailValue(platform,propertyFileReader("LoanDetail"+detail,"ShopInstallmentLoan"))),loanDetail[detail],propertyFileReader("LoanDetail"+detail,"ShopInstallmentLoan"));
        }
        assertionValidation(getText(ShopInstallmentLoanPage.objLoanDetails(platform,propertyFileReader("RatesAndFees","ShopInstallmentLoan"))),propertyFileReader("RatesAndFees","ShopInstallmentLoan")," Text");
        assertionValidation(getText(ShopInstallmentLoanPage.objLoanDetails(platform,propertyFileReader("LoanDocument","ShopInstallmentLoan"))),propertyFileReader("LoanDocument","ShopInstallmentLoan")," Text");
        swipe("UP",1);
        for(int doc=0;doc<6;doc++){
            assertionValidation(getText(ShopInstallmentLoanPage.objLoanDetails(platform,propertyFileReader("LoanDocument"+doc,"ShopInstallmentLoan"))),propertyFileReader("LoanDocument"+doc,"ShopInstallmentLoan")," Document");
        }
    }
    /**
     * click on all the pages and download
     * @throws Exception
     */
    public void clickOnAllDocumentAndDownload() throws Exception {
        swipe("UP", 2);
        for (int doc = 0; doc < 6; doc++) {
            click(ShopInstallmentLoanPage.objLoanDetails(platform, propertyFileReader("LoanDocument" + doc, "ShopInstallmentLoan")), propertyFileReader("LoanDocument" + doc, "ShopInstallmentLoan"));
            if(propertyFileReader("LoanDocument" + doc, "ShopInstallmentLoan").equalsIgnoreCase("About PayHinga")){
                waitForElementToBePresent(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objPageHeader),3," Page Header");
                assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objPageHeader)).replaceAll("\\s"," "), propertyFileReader("PayHingaPageHeader", "ShopInstallmentLoan"), " Document");
                click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objGotitButton), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objGotitButton)));
                if(platform.equalsIgnoreCase("ios")&&verifyElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseCrossButton))){
                    click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseCrossButton)," Cross Icon");
                }
            }
            else {
                waitForElementToBePresent(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objPageHeader),3," Page Header");
                assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objPageHeader)), propertyFileReader("LoanDocument" + doc, "ShopInstallmentLoan"), " Document");
                click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objDownloadButton), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objDownloadButton)));
                if(platform.equalsIgnoreCase("ios")&&verifyElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseCrossButton))){
                    click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseCrossButton)," Cross Icon");
                }
                click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTopLeftSideBackArrowButton), " Back Arrow Button");
            }
        }
    }
    /**
     * Contact Us Page
     * @throws Exception
     */
    public void contactUsPageUI() throws Exception {
        waitForElementToBePresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objGetInTouchWithCustomerCarePage),5,"Get in touch page");
        verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageHeader),getTextVal(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageHeader)," : Page Header"));
        if (verifyElementDisplayed(ContactUsPage.getByOSType(platform,ContactUsPage.objGetInTouchInfo))) {
            List<WebElement> values = findElements(ContactUsPage.getByOSType(platform,ContactUsPage.objGetInTouchInfo));
            for (int info = 0; info < values.size(); info++) {
                if(info%2==0) {
                    String displayedItem = values.get(info).getText();
                    logger.info("Info : '" + displayedItem + "' is displayed");
                    ExtentReporter.extentLogger(" ", "Info : '" + displayedItem + "' is displayed");
                }
            }
        }
        verifyElementPresent(ContactUsPage.getByOSType(platform,ContactUsPage.objChatWithUs),getTextVal(ContactUsPage.getByOSType(platform,ContactUsPage.objChatWithUs),"Contact Option"));
        assertionValidation(getText(ContactUsPage.getByOSType(platform,ContactUsPage.objChatWithUs)),propertyFileReader("ChatWithUs","contactUs"),": Option");
        verifyElementPresent(ContactUsPage.getByOSType(platform,ContactUsPage.objContactNumber),getTextVal(ContactUsPage.getByOSType(platform,ContactUsPage.objContactNumber),"Contact Number"));
        assertionValidation(getText(ContactUsPage.getByOSType(platform,ContactUsPage.objContactNumber)),propertyFileReader("ContactNumber","contactUs"),": Option");
        verifyElementPresent(ContactUsPage.getByOSType(platform,ContactUsPage.objFrequentlyAskedQuestion),getTextVal(ContactUsPage.getByOSType(platform,ContactUsPage.objFrequentlyAskedQuestion),"option"));
        assertionValidation(getText(ContactUsPage.getByOSType(platform,ContactUsPage.objFrequentlyAskedQuestion)),propertyFileReader("FrequentlyAskedQuestion","contactUs"),": Option");
        verifyElementPresent(ContactUsPage.getByOSType(platform,ContactUsPage.objBackBtn),getTextVal(ContactUsPage.getByOSType(platform,ContactUsPage.objPageHeader)," : Page Back button"));
    }
    /**
     * verify Loan DashBoard Page
     * @throws Exception
     */
    public void verifyLoanDashboardPage() throws Exception {
        containsValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextInstallmentText)),propertyFileReader("NextInstallment","ShopInstallmentLoan")," text");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoanInstallmentAmount)),"₱"+decimalFormat.format(Double.parseDouble(propertyFileReader("MonthlyInstallmentWithVas","ShopInstallmentLoan")))," Installment");
        containsValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoanDashBoardDescription)),propertyFileReader("LoanDashBoardDescription","ShopInstallmentLoan")," Description");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPaymentDoneText)),propertyFileReader("PaymentDone","ShopInstallmentLoan")," Text");
        verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objIicon)," Loan information I icon");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPayHingaText)),propertyFileReader("PayHingaText","ShopInstallmentLoan")," Text");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPayHingaSubTitle)),propertyFileReader("InstallmentLeft","ShopInstallmentLoan")," Text");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLifeInsuranceText)),propertyFileReader("LifeInsurance","ShopInstallmentLoan")," Text");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseMyLoan)),propertyFileReader("IWantToCloseLoan","ShopInstallmentLoan")," Button");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPaymentRecordedText)),propertyFileReader("PaymentRecord","ShopInstallmentLoan")," Button");
        verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPaymentRecorded)," Payment Record");
    }
    /**
     * verify Loan Transaction Page
     * @param amount
     * @param paymentName
     * @param PaymentDetails
     * @throws Exception
     */
    public void verifyTransactionPage(String amount, String paymentName, String PaymentDetails) throws Exception {
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objHistoryButton), "History Button");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objAccountHistoryPageHeader)), propertyFileReader("AccountHistory", "TimeDeposit"), " Header");
        waitForElementToBePresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTonikSourceAccountNumber),3," Tonik source Account Number");
        String[] accountNumber = getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTonikSourceAccountNumber)).split(":");
        String tonikSourceAccountNumber = accountNumber[1].replaceAll("\\s", "");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objFirstHistoryDetailsHader)), paymentName, " Transaction Header");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objFirstHistoryDetailsSubText)), PaymentDetails, " SubText");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objFirstHistoryDetailsAmount)), "- ₱" +amount, " Transaction Amount");
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objFirstHistoryDetailsHader), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objFirstHistoryDetailsHader)));
        waitForElementToBePresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTransactionDetailsPageHeader), 3, getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTransactionDetailsPageHeader)));
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTransactionDetailsPageHeader)), propertyFileReader("TransactionDetails", "TimeDeposit"), " Header");
        String[] transactionDetailsValues = {"- ₱" +amount, tonikSourceAccountNumber, "Settlement", todaysPHDate("dd MMM yyyy")};
        for (int detail = 0; detail < 4; detail++) {

            assertionValidation(getText(TimeDepositPage.objTransactionDetailsName(platform,propertyFileReader("TransactionDetails" + detail, "TimeDeposit"))), propertyFileReader("TransactionDetails" + detail, "TimeDeposit"), " Text");
            containsValidation(getText(TimeDepositPage.objTransactionDetailsValue(platform,propertyFileReader("TransactionDetails" + detail, "TimeDeposit"))), transactionDetailsValues[detail], propertyFileReader("TransactionDetails" + detail, "TimeDeposit"));
        }
        assertionValidation(getText(TimeDepositPage.objTransactionDetailsName(platform,propertyFileReader("ReferenceNumber", "TimeDeposit"))), propertyFileReader("ReferenceNumber", "TimeDeposit"), " Text");
        verifyElementPresent(TimeDepositPage.objTransactionDetailsValue(platform,propertyFileReader("ReferenceNumber", "TimeDeposit")), getText(TimeDepositPage.objTransactionDetailsValue(platform,propertyFileReader("ReferenceNumber", "TimeDeposit")))+" : Reference Number");
    }

    /**
     * Verify BreakDown Page
     * @param loanAmount
     * @param payHingaAmount
     * @param interest
     * @throws Exception
     */
    public void verifyBreakdownPage(String loanAmount, String payHingaAmount,String interest) throws Exception {
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageHeader)),propertyFileReader("BeforeClose","ShopInstallmentLoan")," Page Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageSubTitle)),propertyFileReader("CanYouTakeAnotherLook","ShopInstallmentLoan")," Page SubTitle");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objFullRepaymentAmountText)),propertyFileReader("FullRepayment","ShopInstallmentLoan")," Text");
        repaymentAmount = decimalFormat.format(Double.parseDouble(loanAmount) + Double.parseDouble(payHingaAmount));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objFullRepaymentAmountValue)),"₱"+repaymentAmount," Repayment Amount");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objFullRepaymentAmountDescription)).replaceAll("\\s"," "),propertyFileReader("SubjectToChange","ShopInstallmentLoan")," Text");
        containsValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objBreakdownText)),propertyFileReader("Breakdown","ShopInstallmentLoan")," Text");
        String[] details={propertyFileReader("ShopInstallmentLoan1","ShopInstallmentLoan"),"₱"+decimalFormat.format(Double.parseDouble(loanAmount)),"₱"+payHingaAmount,"₱"+interest};
        for(int detail=0;detail<details.length;detail++){
            assertionValidation(getText(ShopInstallmentLoanPage.objLoanDetails(platform,propertyFileReader("Detail"+ detail,"ShopInstallmentLoan"))),propertyFileReader("Detail"+detail,"ShopInstallmentLoan")," Text");
            assertionValidation(getText(ShopInstallmentLoanPage.objPurchaseDetailsValue(platform,propertyFileReader("Detail"+ detail,"ShopInstallmentLoan"))),details[detail],propertyFileReader("Detail"+detail,"ShopInstallmentLoan"));
        }
        swipe("UP",1);
        if(verifyElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNotEnoughBalance))){
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNotEnoughBalance)),propertyFileReader("NotEnoughBalance","ShopInstallmentLoan")," Text");
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNotEnoughBalanceDescription)),propertyFileReader("NoButs","ShopInstallmentLoan")," Description");
            verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopUpMyAccountButton),"Toup My Account Button");
        }
    }
    /**
     * Select Field Of Work
     * @throws Exception
     */
    public void selectFieldOfWork() throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objFieldOfWorkPage),5,"Field of work");
        type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objFieldOfWokSearchField),propertyFileReader("FieldOfWork", "ShopInstallmentLoan"),"Field name");
        if(getPlatform().equalsIgnoreCase("ios")&&verifyElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objdoneButton))){
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objdoneButton)," Done Button");
        }
        verifyElementPresentAndClick(QuickLoanWithVasPage.objList(platform,1),getTextVal(QuickLoanWithVasPage.objList(platform,1), ": Field Of Work"));
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn),getTextVal(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn),": button"));
    }
    /**
     * Select Occupation And Click on Next
     * @param occupation
     * @throws Exception
     */
    public void selectOccupationAndClickOnNext(String occupation) throws Exception {
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("WhatsYourJobRolePage", "QuickLoanWithVas"), ": page title");
        type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objFieldOfWokSearchField), occupation, "occupation input field");
        if(getPlatform().equalsIgnoreCase("ios")){
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objdoneButton)," Done Button");
        }
        verifyElementPresentAndClick(QuickLoanWithVasPage.  objList(platform,1), getTextVal(QuickLoanWithVasPage.objList(platform,1), ": Occupation"));
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn), getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn))+ ": button");
    }
    /**
     * Selct Industry And Click On Next
     * @throws Exception
     */
    public void selectIndustryAndSubIndustary() throws Exception {
        verifyElementPresentAndClick(QuickLoanWithVasPage.objSelectIndustry(platform,propertyFileReader("Industry1", "ShopInstallmentLoan")), getTextVal(QuickLoanWithVasPage.objSelectIndustry(platform,propertyFileReader("Industry1", "ShopInstallmentLoan")), ": industry"));
        verifyElementPresentAndClick(ShopInstallmentLoanPage.objSelectSubIndustry(platform,propertyFileReader("SubIndustry", "ShopInstallmentLoan")), getTextVal(ShopInstallmentLoanPage.objSelectSubIndustry(platform,propertyFileReader("SubIndustry", "ShopInstallmentLoan")), ": subIndustry"));
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn),getTextVal(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn),": button"));
    }
    /**
     * Select Marital Status
     * @throws Exception
     */
    public void selectMaritialStatus() throws Exception {
        verifyElementPresentAndClick((QuickLoanWithVasPage.objSelectMaritalStatus(platform,propertyFileReader("MaritalStatusOption2", "QuickLoanWithVas"))), getTextVal(QuickLoanWithVasPage.objSelectMaritalStatus(platform,propertyFileReader("MaritalStatusOption2", "QuickLoanWithVas")), ": marital status"));
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn), getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn))+ ": button");
    }
    /**
     * Select Dependant Page
     * @throws Exception
     */
    public void selectDependentType() throws Exception {
        verifyElementPresentAndClick(QuickLoanWithVasPage.objSelectMaritalStatus(platform,propertyFileReader("DependentsOption1", "QuickLoanWithVas")), getTextVal(QuickLoanWithVasPage.objSelectMaritalStatus(platform,propertyFileReader("DependentsOption1", "QuickLoanWithVas")), ": Kids/Dependents"));
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn), getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn))+ ": button");
    }
    /**
     * Select Education Type pAge
     * @throws Exception
     */
    public void selectEducationType() throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),2," Page Header");
        verifyElementPresentAndClick(QuickLoanWithVasPage.objSelectMaritalStatus(platform,propertyFileReader("EducationalAttainment1", "QuickLoanWithVas")), getTextVal(QuickLoanWithVasPage.objSelectMaritalStatus(platform,propertyFileReader("EducationalAttainment1", "QuickLoanWithVas")), ": Kids/Dependents"));
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn), getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn))+ ": button");
    }
    /**
     * Navigating From Approved Claim PAge
     * @throws Exception
     */
    public void navigateFromApprovedToTimeToClaimPage() throws Exception {
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText1)), propertyFileReader("Approved", "ShopInstallmentLoan"), "Header");
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader)));
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objYouGotApprovedPageHeader)), propertyFileReader("LoanApprovedPage", "ShopInstallmentLoan"), " Page Header");
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objIAcceptTheOffer), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objIAcceptTheOffer)));
            waitTime(2000);
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFifthOfTheMonth), " Pays Day");
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNextBtn), "Next Button");
            waitTime(2000);//StaleElement
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objPageHeader)), propertyFileReader("MonthlyInstallmentSummary", "ShopInstallmentLoan"), " Page Header");
            verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objConfirmButton), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objConfirmButton)));
            validateAndSignAllThePages();
    }
    /**
     * Loan Disburs
     * @throws Exception
     */
    public void loanDisburs() throws Exception {
        waitForElementToBePresent(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTimeToClaimYourItemPageHeader),3," Time To Claim Page");
        containsValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTimeToClaimYourItemPageHeader)), propertyFileReader("TimeToClaim", "ShopInstallmentLoan"), " Page Header");
        click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objReadyToPayButtonText), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objReadyToPayButtonText)));
        verifyAlreadyAtTheCashierPage();
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objSendConfirmationCodeButtonText),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objSendConfirmationCodeButtonText)));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPayTogetYourConfirmationCodePageHeader)),propertyFileReader("PayToGetConfirmationCode","ShopInstallmentLoan")," Page Header");
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objConfirmationCodeTextField),"Confirmation Code Textfield");
        DriverManager.getAppiumDriver().getKeyboard().sendKeys(RandomIntegerGenerator(6));
        verifyPurchasedPage(decimalFormat.format(Double.parseDouble(propertyFileReader("LoanableAmount","ShopInstallmentLoan"))));
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objBackToDashboard),"Back To DashBoard");
        loanCommonMethods.iWillDoItLaterPopup();
    }
    /**
     * verify Rates And Fee Page
     * @param type
     * @throws Exception
     */
    public void verifyRatesAndFeePage(String type) throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), 10, "Rates And Fees Page");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("ratesAndFeesTxt", "QuickLoanWithVas"), "Text");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyAddOnRate)), propertyFileReader("MonthlyAddOn","ShopInstallmentLoan")," Monthly Add on Rate");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRatesPageMonthlyAddOnRate)), propertyFileReader("MonthlyAddOnRate","ShopInstallmentLoan")," Monthly Add on Rate");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAddOnRateSubTxt)),propertyFileReader("MonthlyAddOnRateSubText","ShopInstallmentLoan")," Monthly Add On Rate SubText");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objEffectiveInterestRate)), propertyFileReader("effectiveInterestRate", "QuickLoanWithVas"), "Text");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objEffectiveInterestRateValue),getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objEffectiveInterestRateValue))+" :Effective Interest Rate");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objProcessingFeeTxt)), propertyFileReader("processingFeeTxt", "QuickLoanWithVas"), " Text");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRatesPageProcessingFee)), "₱"+propertyFileReader("ProcessingFeeValue", "ShopInstallmentLoan"), "Processing Fee");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNetProceedsTxt)), propertyFileReader("netProceeds", "QuickLoanWithVas"), "Text");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNetProceedsAmunt)), "₱"+decimalFormat.format(Double.parseDouble(propertyFileReader("LoanableAmount", "ShopInstallmentLoan"))), " Net Amount");
        if(type.equalsIgnoreCase("WithVas")) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMonthlyPayhingaFee)), propertyFileReader("monthlyPayHingaFee", "QuickLoanWithVas"), "Text");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMonthlyPayHingaAmount)), "₱" + propertyFileReader("PayHingaAmount", "ShopInstallmentLoan"), "Pay hinga amount");
        }
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLateFeeTxt)).replaceAll("f","F"), propertyFileReader("lateFeeTxt", "QuickLoanWithVas"), "Text");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLateFeeAmount)), "₱"+propertyFileReader("lateFee", "QuickLoanWithVas"), "Text");
    }
    /**
     * Common method to return Date
     * @param daysToModify
     * @param pattern
     * @return
     */
    public String returnDate(int daysToModify, String pattern) {
        LocalDate today = LocalDate.now();
        LocalDate futureDate = today.plusDays(daysToModify - 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        System.out.println("Format : " + futureDate.format(formatter));
        return futureDate.format(formatter);
    }

    /**
     * Verify The Loan Transaction History After Payment Through Tonik Account
     * @param amount
     * @param paymentName
     * @param PaymentDetails
     * @throws Exception
     */
    public void verifyTransactionPageAfterPayment(String amount, String paymentName, String PaymentDetails) throws Exception {
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objHistoryButton), "History Button");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objAccountHistoryPageHeader)), propertyFileReader("AccountHistory", "TimeDeposit"), " Header");
        waitForElementToBePresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTonikSourceAccountNumber),3," Tonik source Account Number");
        String[] accountNumber = getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTonikSourceAccountNumber)).split(":");
        String tonikSourceAccountNumber = accountNumber[1].replaceAll("\\s", "");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objFirstHistoryDetailsHader)), paymentName, " Transaction Header");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objFirstHistoryDetailsSubText)), PaymentDetails, " SubText");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objFirstHistoryDetailsAmount)), "- ₱" +amount, " Transaction Amount");
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objFirstHistoryDetailsHader), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objFirstHistoryDetailsHader)));
        waitForElementToBePresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTransactionDetailsPageHeader), 3, getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTransactionDetailsPageHeader)));
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTransactionDetailsPageHeader)), propertyFileReader("TransactionDetails", "TimeDeposit"), " Header");
        String[] transactionDetailsValues = {"- ₱" +amount, tonikSourceAccountNumber, todaysPHDate("dd MMM yyyy")};
        for (int detail = 0; detail < 3; detail++) {
            assertionValidation(getText(TimeDepositPage.objTransactionDetailsName(platform,propertyFileReader("TransactionDetails" + detail, "ShopInstallmentLoan"))), propertyFileReader("TransactionDetails" + detail, "ShopInstallmentLoan"), " Text");
            containsValidation(getText(TimeDepositPage.objTransactionDetailsValue(platform,propertyFileReader("TransactionDetails" + detail, "ShopInstallmentLoan"))), transactionDetailsValues[detail], propertyFileReader("ShopInstallmentLoan" + detail, "TimeDeposit"));
        }
        assertionValidation(getText(TimeDepositPage.objTransactionDetailsName(platform,propertyFileReader("ReferenceNumber", "TimeDeposit"))), propertyFileReader("ReferenceNumber", "TimeDeposit"), " Text");
        verifyElementPresent(TimeDepositPage.objTransactionDetailsValue(platform,propertyFileReader("ReferenceNumber", "TimeDeposit")), getText(TimeDepositPage.objTransactionDetailsValue(platform,propertyFileReader("ReferenceNumber", "TimeDeposit")))+" : Reference Number");
    }
    //===================================Test Methods ===================================
    /**
     * Verify if newly onboarded user can access the Shop Installment Loan from the main Dashboard
     * @param password
     * @throws Exception
     */
    public void verifyIfNewlyOnboardedUserCanAccessTheShopInstallmentLoanFromTheMainDashboard_TDB_SILV_004(String password) throws Exception {
        HeaderChildNode("TDB-SILV-004, Verify if newly onboarded user can access the Shop Installment Loan from the main Dashboard");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        loanTileValidation();
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objContactUsButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objContactUsButton)));
        contactUsPageUI();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back Button");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTile),getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTile)));
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objIDontSeeAnyTonikAgentButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objIDontSeeAnyTonikAgentButton)));
        verifyQRHuntPage();
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objApplyForQuickLoanButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objApplyForQuickLoanButton)));
        if (verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCountMeInButton))) {
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCountMeInButton), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCountMeInButton), ": button"));
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objGotItBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objGotItBtn), ": button"));
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.ObjLetsGoBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.ObjLetsGoBtn), ": button"));
        }
        howMuchDoYouNeedPage();
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objBackArrowButton),"Back Arrow Button");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTile),getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTile)));
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objGotchaButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objGotchaButton)));
        verifyScanQRPage();
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objContactUsButton)," FAQ Button");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objQRHuntPageHeader)),propertyFileReader("QrHunt","ShopInstallmentLoan")," Header");
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objBackToQRScanningButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objBackToQRScanningButton)));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objScanTheQRCodePageHeader)),propertyFileReader("ScanTheQR","ShopInstallmentLoan"), " Page Header");
        logger.info("TDB-SILV-004, ShopInstallment Loan - Verify if newly onboarded user can access the Shop Installment Loan from the main Dashboard");
        extentLoggerPass("TDB-SILV-004", "ShopInstallment Loan - Verify if newly onboarded user can access the Shop Installment Loan from the main Dashboard");
    }
    /**
     * Verify if user can accept the loan offer with VAS from the Loan Summary screen
     * @param password
     * @throws Exception
     */
    public void verifyUserCanAcceptTheLoanOfferWithVASFromTheLoanSummaryScreen_TDB_SILV_010(String password,String mobileNumber) throws Exception {
        HeaderChildNode("TDB-SILV-010, ShopInstallment Loan - Verify if user can accept the loan offer with VAS from the Loan Summary screen");
        updateUTCTime(mobileNumber);
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        loanTileValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTile),getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTile)));
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objGotchaButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objGotchaButton)));
        verifyScanQRPage();
        if(platform.equalsIgnoreCase("ios")){
            closeiOSTonikApp();
        } else{
            closeAndroidTonikApp();
        }
        shopInstallmentLoanAPIModule.createSILLoan(mobileNumber,propertyFileReader("vasFlag","ShopInstallmentLoan"),propertyFileReader("MonthlyInstallmentWithVas","ShopInstallmentLoan"));
        relaunchApp(platform);
        tonikLogin(password);
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("InProgress","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText2)),propertyFileReader("TapHere","ShopInstallmentLoan"),"Header");
        logger.info("TDB-SILV-010, ShopInstallment Loan - Verify if user can accept the loan offer with VAS from the Loan Summary screen");
        extentLoggerPass("TDB-SILV-010", "ShopInstallment Loan - Verify if user can accept the loan offer with VAS from the Loan Summary screen");
    }
    /**
     * Verify if the user can select field of work
     * @param password
     * @throws Exception
     */
    public void verifyUserCanSelectFieldOfWork_TDB_SILV_011(String password) throws Exception {
        HeaderChildNode("TDB-SILV-011, ShopInstallment Loan - Verify if the user can select field of work");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("InProgress","ShopInstallmentLoan"),"Header");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseApplicationButton)," Leave Application Button");
        verifyLeavingSoSoonPage();
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopLeftSideBackArrowButton),"Back Arrow Button");
        fieldOfWork(propertyFileReader("FieldOfWork", "ShopInstallmentLoan"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objWhatsYourJobRolePage),"What's your Job Role? Screen");
        logger.info("TDB-SILV-011, ShopInstallment Loan - Verify if the user can select field of work");
        extentLoggerPass("TDB-SILV-011", "ShopInstallment Loan - Verify if the user can select field of work");
    }
    /**
     * Verify if user can select the Occupation details
     * @param password
     * @throws Exception
     */
    public void verifyUserCanSelectTheOccupationDetails_TDB_SILV_012(String password) throws Exception {
        HeaderChildNode("TDB-SILV-012, ShopInstallment Loan - Verify if user can select the Occupation details");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("InProgress","ShopInstallmentLoan"),"Header");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        selectFieldOfWork();
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseApplicationButton)," Leave Application Button");
        verifyLeavingSoSoonPage();
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopLeftSideBackArrowButton),"Back Arrow Button");
        selectOccupation(propertyFileReader("Occupation", "ShopInstallmentLoan"));
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("IndustrySelectionScreen", "QuickLoanWithVas"), ": page title");        logger.info("TDB-SIL-012, ShopInstallment Loan - Verify if user can select the Occupation details");
        logger.info("TDB-SILV-012, ShopInstallment Loan - Verify if user can select the Occupation details");
        extentLoggerPass("TDB-SILV-012", "ShopInstallment Loan - Verify if user can select the Occupation details");
    }
    /**
     * Verify if user can select the Industry & the Sub-Industry options
     * @param password
     * @throws Exception
     */
    public void verifyUserCanSelectIndustryAndSubIndustryOptions_TDB_SILV_013(String password) throws Exception {
        HeaderChildNode("TDB-SILV-013, ShopInstallment Loan - Verify if user can select the Industry & the Sub-Industry options");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("InProgress","ShopInstallmentLoan"),"Header");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        selectFieldOfWork();
        selectOccupationAndClickOnNext(propertyFileReader("Occupation", "ShopInstallmentLoan"));
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseApplicationButton)," Leave Application Button");
        verifyLeavingSoSoonPage();
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopLeftSideBackArrowButton),"Back Arrow Button");
        selectIndustry(propertyFileReader("Industry1", "ShopInstallmentLoan"), propertyFileReader("SubIndustry", "ShopInstallmentLoan"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("MaritalStatus", "QuickLoanWithVas"), ": page title");
        logger.info("TDB-SILV-013, ShopInstallment Loan - Verify if user can select the Industry & the Sub-Industry options");
        extentLoggerPass("TDB-SILV-013", "ShopInstallment Loan - Verify if user can select the Industry & the Sub-Industry options");
    }
    /**
     * Verify if user can select the Marital Status
     * @param password
     * @throws Exception
     */
    public void verifyUserCanSelectMaritalStatus_TDB_SILV_014(String password) throws Exception {
        HeaderChildNode("TDB-SILV-014, ShopInstallment Loan - Verify if user can select the Marital Status");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("InProgress","ShopInstallmentLoan"),"Header");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        selectFieldOfWork();
        selectOccupationAndClickOnNext(propertyFileReader("Occupation", "ShopInstallmentLoan"));
        selectIndustryAndSubIndustary();
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseApplicationButton)," Leave Application Button");
        verifyLeavingSoSoonPage();
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopLeftSideBackArrowButton),"Back Arrow Button");
        selectMaritalStatus(propertyFileReader("MaritalStatusOption2", "QuickLoanWithVas"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("SelectNoOfKids", "QuickLoanWithVas"), ": page title");
        logger.info("TDB-SILV-014, ShopInstallment Loan - Verify if user can select the Marital Status");
        extentLoggerPass("TDB-SILV-014", "ShopInstallment Loan - Verify if user can select the Marital Status");
    }
    /**
     * Verify if user can select Dependents
     * @param password
     * @throws Exception
     */
    public void verifyUserCanSelectDependents_TDB_SILV_015(String password) throws Exception {
        HeaderChildNode("TDB-SILV-015, ShopInstallment Loan - Verify if user can select Dependents");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("InProgress","ShopInstallmentLoan"),"Header");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        selectFieldOfWork();
        selectOccupationAndClickOnNext(propertyFileReader("Occupation", "ShopInstallmentLoan"));
        selectIndustryAndSubIndustary();
        selectMaritialStatus();
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseApplicationButton)," Leave Application Button");
        verifyLeavingSoSoonPage();
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopLeftSideBackArrowButton),"Back Arrow Button");
        selectKidsOrDependents(propertyFileReader("DependentsOption1", "QuickLoanWithVas"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),2," Page Header");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("HighestEducationalAttainment", "QuickLoanWithVas"), ": page title");
        logger.info("TDB-SILV-015, ShopInstallment Loan - Verify if user can select Dependents");
        extentLoggerPass("TDB-SILV-015", "ShopInstallment Loan - Verify if user can select Dependents");
    }
    /**
     * Verify if user can select the Educational details
     * @param password
     * @throws Exception
     */
    public void verifyUserCanSelectTheEducationalDetails_TDB_SILV_016(String password) throws Exception {
        HeaderChildNode("TDB-SILV-016, ShopInstallment Loan - Verify if user can select the Educational details");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("InProgress","ShopInstallmentLoan"),"Header");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        selectFieldOfWork();
        selectOccupationAndClickOnNext(propertyFileReader("Occupation", "ShopInstallmentLoan"));
        selectIndustryAndSubIndustary();
        selectMaritialStatus();
        selectDependentType();
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),2," Page Header");
        verifyLeavingSoonPageHeaders();
        selectHighestEducationalAttainment(propertyFileReader("EducationalAttainment1", "QuickLoanWithVas"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactReferencePage)),propertyFileReader("ContactReference","QuickLoanWithVas")," Page Header");
        logger.info("TDB-SILV-016, ShopInstallment Loan - Verify if user can select the Educational details");
        extentLoggerPass("TDB-SILV-016", "ShopInstallment Loan - Verify if user can select the Educational details");
    }
    /**
     * Verify if user can input the reference contact details
     * @param password
     * @throws Exception
     */
    public void verifyUserCanInputReferenceContactDetails_TDB_SILV_017(String password) throws Exception {
        HeaderChildNode("TDB-SILV-017, ShopInstallment Loan - Verify if user can input the reference contact details");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("InProgress","ShopInstallmentLoan"),"Header");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        selectFieldOfWork();
        selectOccupationAndClickOnNext(propertyFileReader("Occupation", "ShopInstallmentLoan"));
        selectIndustryAndSubIndustary();
        selectMaritialStatus();
        selectDependentType();
        selectEducationType();
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactReferencePage),5,"Contact reference page");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("ContactReference","QuickLoanWithVas"),": page title");
        verifyLeavingSoonPageHeaders();
        updateContactReference(propertyFileReader("InvalidReferenceNumber","QuickLoanWithVas"));
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInvalidMobileNumberMsg),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInvalidMobileNumberMsg),"Error message"))){
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInvalidMobileNumberMsg)),propertyFileReader("InvalidMobileNumberMsg","QuickLoanWithVas"),"Error message");
        }
        updateContactReference(propertyFileReader("ContactMobileNumber","QuickLoanWithVas"));
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRegisteredMobileNumberErrorMsg),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRegisteredMobileNumberErrorMsg),"Error message"))){
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRegisteredMobileNumberErrorMsg)),propertyFileReader("RegisteredMobileNumberErrorMsg","QuickLoanWithVas"),"Error message");
        }
        updateContactReference(propertyFileReader("ReferenceNumber1","QuickLoanWithVas"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHaveAnotherReference),5,"Have another Reference page");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("HaveAnotherReference","QuickLoanWithVas"),": page title");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)),propertyFileReader("HaveAnotherReferenceSubtitle","QuickLoanWithVas"),": page subtitle");
        updateContactReference(propertyFileReader("ContactMobileNumber","QuickLoanWithVas"));
        if(verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader))){
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),": popup header"));
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubtext)),propertyFileReader("SameNumberPopSubTxt","QuickLoanWithVas"),": Popup subtext");
            verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),": popup button"));
        }
        updateContactReference(propertyFileReader("ReferenceNumber1","QuickLoanWithVas"));
        if(verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader))){
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),": popup header"));
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubtext)),propertyFileReader("PopSubtext","QuickLoanWithVas"),": Popup subtext");
            verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),": popup button"));
        }
        updateContactReference(propertyFileReader("ReferenceNumber2","QuickLoanWithVas"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIsThisYourCurrentAddPage),5,"is this your current address screen");
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"))) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("IsThisYourCurrentAddPage","QuickLoanWithVas"),": page title");
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)),propertyFileReader("IsThisYourCurrentAddPageSubtitle","QuickLoanWithVas"),": page subtitle");
            logger.info("TDB-SILV-017, ShopInstallment Loan - Verify if user can input the reference contact details");
            extentLoggerPass("TDB-SILV-017", "ShopInstallment Loan - Verify if user can input the reference contact details");
        }
    }
    /**
     * Verify if user can select the Current Living City
     * @throws Exception
     */
    public void verifyUserCanSelectCurrentLivingCity_TDB_SILV_018(String password) throws Exception {
        HeaderChildNode("TDB-SILV-018, ShopInstallment Loan - Verify if user can select the Current Living City");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("InProgress","ShopInstallmentLoan"),"Header");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        selectFieldOfWork();
        selectOccupationAndClickOnNext(propertyFileReader("Occupation", "ShopInstallmentLoan"));
        selectIndustryAndSubIndustary();
        selectMaritialStatus();
        selectDependentType();
        selectEducationType();
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactReferencePage),5,"Contact reference page");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        updateContactReference(propertyFileReader("ReferenceNumber1","QuickLoanWithVas"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHaveAnotherReference),5,"Have another Reference Screen");
        updateContactReference(propertyFileReader("ReferenceNumber2","QuickLoanWithVas"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIsThisYourCurrentAddPage),5,"Is this your current address? page");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("IsThisYourCurrentAddPage","QuickLoanWithVas"),": page title");
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)),propertyFileReader("IsThisYourCurrentAddPageSubtitle","QuickLoanWithVas"),": page subtitle");
        if(platform.equalsIgnoreCase("ios")){
            List<IOSElement> values = DriverManager.getAppiumDriver().findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAddress));
            for (int address = 0; address < values.size(); address++) {
                String displayedItem = values.get(address).getText();
                logger.info("'" + displayedItem + "' Address "+address+"is displayed");
                ExtentReporter.extentLogger(" ", "'" + displayedItem + "' Address "+address+" is displayed");
            }
        } else {
            List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAddress));
            for (int address = 0; address < values.size(); address++) {
                String displayedItem = values.get(address).getText();
                logger.info("'" + displayedItem + "' Address "+address+"is displayed");
                ExtentReporter.extentLogger(" ", "'" + displayedItem + "' Address "+address+" is displayed");
            }
        }
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objChangeAddressBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objChangeAddressBtn),": button"));
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objHowToFindYourZipCodeLinkText),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objHowToFindYourZipCodeLinkText)));
        applyZipCode(propertyFileReader("ValidZipCode","ShopInstallmentLoan"));
        if(platform.equalsIgnoreCase("ios")){
            List<IOSElement> values = DriverManager.getAppiumDriver().findElements(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objAllFieldsInWhereYourCurrentAddress));
            for (int address = 0; address < values.size(); address++) {
                String displayedItem = values.get(address).getText();
                logger.info("'" + displayedItem + "' Address "+address+"is displayed");
                ExtentReporter.extentLogger(" ", "'" + displayedItem + "' Address "+address+" is displayed");
            }
        } else {
            List<WebElement> values = findElements(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objAllFieldsInWhereYourCurrentAddress));
            for (int address = 0; address < values.size(); address++) {
                String displayedItem = values.get(address).getText();
                logger.info("'" + displayedItem + "' Address "+address+"is displayed");
                ExtentReporter.extentLogger(" ", "'" + displayedItem + "' Address "+address+" is displayed");
            }
        }
        swipe("UP", 2);
        type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHouseUnitFlrNumberInputField),propertyFileReader("HouseStreetName","QuickLoanWithVas"),"House/Street Name");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),": button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyIncomeScreen),5,"What's your monthly income? screen");
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"))) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("MonthlyIncomeScreen", "QuickLoanWithVas"), ": page title");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("MonthlyIncomeScreenSubTitle", "QuickLoanWithVas"), ": page subtitle");
        }
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopLeftSideBackArrowButton),"Back button");
        waitTime(2000);
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopLeftSideBackArrowButton),"Back button");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYesThisMyAddressBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYesThisMyAddressBtn),": button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyIncomeScreen),5,"What's your monthly income? screen");
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"))) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("MonthlyIncomeScreen", "QuickLoanWithVas"), ": page title");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("MonthlyIncomeScreenSubTitle", "QuickLoanWithVas"), ": page subtitle");
            logger.info("TDB-SILV-018, ShopInstallment Loan - Verify if user can select the Current Living City");
            extentLoggerPass("TDB-SILV-018", "ShopInstallment Loan - Verify if user can select the Current Living City");
        }
    }
    /**
     * Verify if user can input the Monthly income, Company name & TIN details
     * @throws Exception
     */
    public void verifyUserCanInputTheMonthlyIncomeCompanyNameAndTINDetails_TDB_SILV_019(String password) throws Exception {
        HeaderChildNode("TDB-SILV-019, ShopInstallment Loan - Verify if user can input the Monthly income, Company name & TIN details");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("InProgress","ShopInstallmentLoan"),"Header");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        selectFieldOfWork();
        selectOccupationAndClickOnNext(propertyFileReader("Occupation", "ShopInstallmentLoan"));
        selectIndustryAndSubIndustary();
        selectMaritialStatus();
        selectDependentType();
        selectEducationType();
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactReferencePage),5,"Contact reference page");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        updateContactReference(propertyFileReader("ReferenceNumber1","QuickLoanWithVas"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHaveAnotherReference),5,"Have another Reference Screen");
        updateContactReference(propertyFileReader("ReferenceNumber2","QuickLoanWithVas"));
        isThisYourCurrentAddressConfirmation();
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyIncomeScreen),5,"Monthly Income Screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("MonthlyIncomeScreen", "QuickLoanWithVas"), ": page title");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("IsThisYourCurrentAddPage","QuickLoanWithVas"),": page title");
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)),propertyFileReader("IsThisYourCurrentAddPageSubtitle","QuickLoanWithVas"),": page subtitle");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYesThisMyAddressBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYesThisMyAddressBtn),": button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyIncomeScreen),5,"monthly income screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("MonthlyIncomeScreen", "QuickLoanWithVas"), ": page title");
        waitTime(3000);
        verifyLeavingSoonPageHeaders();
        if(platform.equalsIgnoreCase("ios")){
            clearField(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyIncomeField), "Income input field");
        }
        type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyIncomeField),propertyFileReader("ValidIncome","ShopInstallmentLoan"),"Income input field");
        if(platform.equalsIgnoreCase("ios")){
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objKeyboardDoneBtn), "Keyboard Done Button");
        }
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), ": button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInputCompanyScreen),5,"Where do you work, luv? Screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("InputCompanyScreen", "QuickLoanWithVas"), ": page title");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("InputCompanyScreenSubtitle", "QuickLoanWithVas"), ": page subtitle");
        verifyLeavingSoonPageHeaders();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("MonthlyIncomeScreen", "QuickLoanWithVas"), ": page title");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),": button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCompanyInputField),5,"Company Input field");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("InputCompanyScreen", "QuickLoanWithVas"), ": page title");
        type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCompanyInputField),propertyFileReader("Company","QuickLoanWithVas"),"Company Input Field");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUpdateButton),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUpdateButton),": button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTINInputField),5,"TIN Screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("TINScreen", "QuickLoanWithVas"), ": page title");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCompanyInputField),5,"Company Input screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("InputCompanyScreen", "QuickLoanWithVas"), ": page title");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUpdateButton),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUpdateButton),": button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTINInputField),5,"TIN Screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("TINScreen", "QuickLoanWithVas"), ": page title");
        type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTINInputField),propertyFileReader("InvalidTIN","QuickLoanWithVas"),"TIN Input field");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),": button"));
        if(verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInvalidTINMsg))){
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInvalidTINMsg)), propertyFileReader("InvalidTINMsg", "QuickLoanWithVas"), ": page title");
        }
        type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTINInputField),propertyFileReader("ValidTIN","QuickLoanWithVas"),"TIN Input field");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),": button"));
        waitTime(2000);
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"))) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("AnotherWayToReachYou", "QuickLoanWithVas"), ": page title");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("AnotherWayToReachYouSubtitle", "QuickLoanWithVas"), ": page subtitle");
            logger.info("TDB-SILV-019, ShopInstallment Loan - Verify if user can input the Monthly income, Company name & TIN details");
            extentLoggerPass("TDB-SILV-019", "ShopInstallment Loan - Verify if user can input the Monthly income, Company name & TIN details");
        }
    }
    /**
     * Verify if user can input Secondary Contact details
     * @throws Exception
     */
    public void verifyUserCanInputSecondaryContactDetails_TDB_SILV_020(String password) throws Exception {
        HeaderChildNode("TDB-SILV-020, ShopInstallment Loan - Verify if user can input Secondary Contact details");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("InProgress","ShopInstallmentLoan"),"Header");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        selectFieldOfWork();
        selectOccupationAndClickOnNext(propertyFileReader("Occupation", "ShopInstallmentLoan"));
        selectIndustryAndSubIndustary();
        selectMaritialStatus();
        selectDependentType();
        selectEducationType();
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactReferencePage),5,"Contact reference page");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        updateContactReference(propertyFileReader("ReferenceNumber1","QuickLoanWithVas"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHaveAnotherReference),5,"Have another Reference Screen");
        updateContactReference(propertyFileReader("ReferenceNumber2","QuickLoanWithVas"));
        isThisYourCurrentAddressConfirmation();
        loanCommonMethods.inputIncomeCompanyAndTINDetails(propertyFileReader("ValidTIN","QuickLoanWithVas"),propertyFileReader("ValidIncome","ShopInstallmentLoan"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAnotherWayToReachYou),5,"Another way to reach you? Screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("AnotherWayToReachYou", "QuickLoanWithVas"), ": page title");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTINInputField),5,"TIN Screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("TINScreen", "QuickLoanWithVas"), ": page title");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),": button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAnotherWayToReachYou),5,"Another way to reach you? Screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("AnotherWayToReachYou", "QuickLoanWithVas"), ": page title");
        inputMobileNumberAndNext(propertyFileReader("SILMobileNumber","TestDataNumbers").replaceAll("63",""));
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),"Popup header"))){
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubtext)),propertyFileReader("SameOnboardedNumberPopup","QuickLoanWithVas"),"Popup header");
            verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),": button"));
        }
        inputMobileNumberAndNext(propertyFileReader("SILReferenceContact","TestDataNumbers"));
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),"Popup header"))){
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubtext)),propertyFileReader("SameAsReferenceNumberPopup","QuickLoanWithVas"),"Popup header");
            verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),": button"));
        }
        inputMobileNumberAndNext(propertyFileReader("SecondaryContact","QuickLoanWithVas"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),"Popup header"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubtext)),propertyFileReader("SecondaryContactConfirmation","QuickLoanWithVas"),"Popup header");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),": button"));
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionScreen),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionScreen),": page"))){
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionSubtitle)),propertyFileReader("TermsAndConditionScreenSubtitle","QuickLoanWithVas"),"Page Subtitle");
        }
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAnotherWayToReachYou),5,"Another way to reach you? Screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("AnotherWayToReachYou", "QuickLoanWithVas"), ": page title");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSkipBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSkipBtn),": button"));
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),"Popup header"))){
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubtext)),propertyFileReader("SecondaryContactConfirmation","QuickLoanWithVas"),"Popup header");
            verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),": button"));
        }
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionScreen),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionScreen),": page"))){
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTermsAndConditionSubtitle)),propertyFileReader("TermsAndConditionScreenSubtitle","QuickLoanWithVas"),"Page Subtitle");
            logger.info("TDB-SILV-020, ShopInstallment Loan - Verify if user can input Secondary Contact details");
            extentLoggerPass("TDB-SILV-020", "ShopInstallment Loan - Verify if user can input Secondary Contact details");
        }
    }
    /**
     * Verify if user can accept the Terms & Conditions
     * @throws Exception
     */
    public void verifyUserCanAcceptTermsAndConditions_TDB_SILV_021(String password,String mobileNumber) throws Exception {
        HeaderChildNode("TDB-SILV-021, ShopInstallment Loan - Verify if user can accept the Terms & Conditions");
        updateUTCTime(mobileNumber);
        tonikLogin(password);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        loanSegment2NavigationForSIL(propertyFileReader("ValidTIN","QuickLoanWithVas"),propertyFileReader("ValidIncome","ShopInstallmentLoan"), propertyFileReader("ReferenceNumber1", "QuickLoanWithVas"),propertyFileReader("ReferenceNumber2", "QuickLoanWithVas"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHoldOnPopupTitle),30,"Hold On Babe Page");
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHoldOnPopupTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHoldOnPopupTitle),": page"))){
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHoldOnPopupTitle)),propertyFileReader("HoldOnBabeScreen","ShopInstallmentLoan"),"Page Title");
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHoldOnPopupSubTitle)),propertyFileReader("HoldOnBabeScreenSubHeader","ShopInstallmentLoan"),"Page Subtitle");
            waitForElementToBePresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objIAcceptTheOffer),20,"I accept the offer screen button");
            verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objIAcceptTheOffer),"I accept the offer screen button");
            logger.info("TDB-SILV-021, ShopInstallment Loan - Verify if user can accept the Terms & Conditions");
            extentLoggerPass("TDB-SILV-021", "ShopInstallment Loan - Verify if user can accept the Terms & Conditions");
        }
    }
    public void verifyTheLoanTileIfTheUserIsSoftRejected_TDB_SILV_022(String password) throws Exception {
        HeaderChildNode("TDB-SILV-022, ShopInstallment Loan - Verify the Loan tile if the user is soft rejected");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        loanTileValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTile),getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTile)));
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objGotchaButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objGotchaButton)));
        verifyScanQRPage();
        if(platform.equalsIgnoreCase("ios")){
            closeiOSTonikApp();
        } else{
            closeAndroidTonikApp();
        }
        shopInstallmentLoanAPIModule.createSILLoan("63"+propertyFileReader("SoftRejectMobileNumber","TestDataNumbers"),propertyFileReader("vasFlag","ShopInstallmentLoan"),propertyFileReader("MonthlyInstallmentWithVas","ShopInstallmentLoan"));
        relaunchApp(platform);
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        loanSegment2NavigationForSIL(propertyFileReader("SoftRejectTIN","QuickLoanWithVas"),propertyFileReader("ValidIncome","ShopInstallmentLoan"), propertyFileReader("ReferenceNumber1", "QuickLoanWithVas"),propertyFileReader("ReferenceNumber2", "QuickLoanWithVas"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHardToSayScreen),5,"Hard to say no");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHardToSayScreen),"Hard To Say Screen");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashboard),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashboard),": button"));
        waitForElementToBePresent(LoginPage.getByOSType(platform,LoginPage.objProfileName),10,"Dashboard");
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSoftRejectLoanStatus),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSoftRejectLoanStatus),"Status"))) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSoftRejectLoanStatus)),propertyFileReader("RejectedLoanTile","ShopInstallmentLoan")," Header");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSoftRejectLoanInfo)),propertyFileReader("RejectedLoanInfo","ShopInstallmentLoan")," Info");
            String userId = getUserId("63" + propertyFileReader("SoftRejectMobileNumber","TestDataNumbers"));
            assertionValidation(getLoanStatus(userId), propertyFileReader("RejectedStatus", "QuickLoanWithVas"), "Status");
            logger.info("Rejection type" + getLoanRejectionType(shopInstallmentLoanAPIModule.digital_loan_id));
            extentLogger("", "Rejection type" + getLoanRejectionType(shopInstallmentLoanAPIModule.digital_loan_id));
        }
            logger.info("TDB-SILV-022, ShopInstallment Loan  - Loan tile status if the user is soft rejected");
            extentLoggerPass("TDB-SILV-022", "TDB-SILV-022, ShopInstallment Loan  - Loan tile status if the user is soft rejected");
        }
    /**
     * Verify the Loan tile status if the user is hard rejected
     * @param password
     * @throws Exception
     */
    public void verifyTheLoanTileIfTheUserIsHardRejected_TDB_SILV_023(String password) throws Exception {
        HeaderChildNode("TDB-SILV-023, ShopInstallment Loan  - Verify the Loan tile status if the user is hard rejected");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        loanTileValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTile),getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTile)));
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objGotchaButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objGotchaButton)));
        verifyScanQRPage();
        if(platform.equalsIgnoreCase("ios")){
            closeiOSTonikApp();
        } else{
            closeAndroidTonikApp();
        }
        shopInstallmentLoanAPIModule.createSILLoan("63"+propertyFileReader("HardRejectMobileNumber","TestDataNumbers"),propertyFileReader("vasFlag","ShopInstallmentLoan"),propertyFileReader("MonthlyInstallmentWithVas","ShopInstallmentLoan"));
        waitTime(3000);
        relaunchApp(platform);
        waitTime(3000);
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        loanSegment2NavigationForSIL(propertyFileReader("ValidTIN","QuickLoanWithVas"),propertyFileReader("ValidIncome","ShopInstallmentLoan"),
                propertyFileReader("HardRejectNumber2", "QuickLoanWithVas"),propertyFileReader("ReferenceNumber2", "QuickLoanWithVas"));
        if(verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHardToSayScreen))) {
            waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHardToSayScreen),5,"Hard to say no");
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHardToSayScreen),"Hard To Say Screen");
            verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashboard),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashboard),": button"));
            waitForElementToBePresent(LoginPage.getByOSType(platform,LoginPage.objProfileName),10,"Dashboard");
        }
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSoftRejectLoanStatus),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSoftRejectLoanStatus),"Status"))) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSoftRejectLoanStatus)), propertyFileReader("RejectedLoanTile", "ShopInstallmentLoan"), " Header");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSoftRejectLoanInfo)), propertyFileReader("RejectedLoanInfo", "ShopInstallmentLoan"), " Info");
            String userId = getUserId("63" + propertyFileReader("HardRejectMobileNumber", "TestDataNumbers"));
            assertionValidation(getLoanStatus(userId), propertyFileReader("RejectedStatus", "QuickLoanWithVas"), "Status");
            logger.info("Rejection type : " + getLoanRejectionType(shopInstallmentLoanAPIModule.digital_loan_id));
            extentLogger("", "Rejection type : " + getLoanRejectionType(shopInstallmentLoanAPIModule.digital_loan_id));
        }
            logger.info("TDB-SILV-023, ShopInstallment Loan  - Loan tile status if the user is Hard rejected validated");
            extentLoggerPass("TDB-SILV-023", "TDB-SILV-023, ShopInstallment Loan  - Loan tile status if the user is Hard rejected validated");
    }
    /**
     * Verify the Loan tile if the user is pre-approved
     * @param password
     * @throws Exception
     */
    public void verifyLoanTileIfTheUserIsPreApproved_TDB_SILV_027(String password) throws Exception {
        HeaderChildNode("TDB-SILV-027, ShopInstallment Loan  - Verify the Loan tile if the user is pre-approved");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        if(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText1)).equalsIgnoreCase(propertyFileReader("InProgress", "ShopInstallmentLoan"))) {
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)));
            loanSegment2NavigationForSIL(propertyFileReader("ValidTIN","QuickLoanWithVas"),propertyFileReader("ValidIncome","ShopInstallmentLoan"), propertyFileReader("ReferenceNumber1", "QuickLoanWithVas"),propertyFileReader("ReferenceNumber2", "QuickLoanWithVas"));
            waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objProfileName),5,"Profile Name");
            waitTime(30000);
            if(platform.equalsIgnoreCase("ios")){
                closeiOSTonikApp();
            } else{
                closeAndroidTonikApp();
            }
            relaunchApp(platform);
            tonikLogin(password);
        }
        if(verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile)))) {
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTile)), propertyFileReader("Loans", "ShopInstallmentLoan"), "Header");
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText1)), propertyFileReader("Approved", "ShopInstallmentLoan"), "Header");
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText2)), propertyFileReader("congratulationsTxt", "ShopInstallmentLoan"), "Header");
            logger.info("TDB-SILV-027, ShopInstallment Loan  - Verify the Loan tile if the user is pre-approved");
            extentLoggerPass("TDB-SILV-027", "TDB-SILV-027, ShopInstallment Loan  - Verify the Loan tile if the user is pre-approved");
        }
    }
    /**
     * Verify if user can accept the loan offer from the Loan Offer screen
     * @param password
     * @throws Exception
     */
    public void VerifyUserCanAcceptTheLoanOfferFromLoanOfferScreen_TDB_SILV_028(String password) throws Exception {
        HeaderChildNode("TDB-SILV-028, ShopInstallment Loan  - Verify if user can accept the loan offer from the Loan Offer screen");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        if(verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile)))) {
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText1)), propertyFileReader("Approved", "ShopInstallmentLoan"), "Header");
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)));
            verifyLoanApprovedPage(decimalFormat.format(Double.parseDouble(propertyFileReader("LoanableAmount","ShopInstallmentLoan"))),decimalFormat.format(Double.parseDouble(propertyFileReader("MonthlyInstallmentWithVas","ShopInstallmentLoan"))),propertyFileReader("Tenure","ShopInstallmentLoan"));
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopLeftSideBackArrowButton)," Back Arrow Button");
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objProfileName)," Profile Name In Dash Board");
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)));
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objIAcceptTheOffer),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objIAcceptTheOffer)));
            waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,(QuickLoanWithVasPage.objMainTitleTxt)), 3, getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt)));
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("tellUsPageHeader", "QuickLoanWithVas"), "Page Header", "contains");
            logger.info("TDB-SILV-028, ShopInstallment Loan  - Verify if user can accept the loan offer from the Loan Offer screen");
            extentLoggerPass("TDB-SILV-028", "TDB-SILV-028, ShopInstallment Loan  - Verify if user can accept the loan offer from the Loan Offer screen");
        }
    }
    /**
     * Verify if user can select the Pay Day & confirm Monthly Installment Summary
     * @param password
     * @throws Exception
     */
    public void verifyUserCanSelectPayDayAndConfirmMonthlyInstallmentSummary_TDB_SILV_029(String password) throws Exception {
        HeaderChildNode("TDB-SILV-029, ShopInstallment Loan  - Verify if user can select the Pay Day & confirm Monthly Installment Summary");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTile));
        if (verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTile), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTile)))) {
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText1)), propertyFileReader("Approved", "ShopInstallmentLoan"), "Header");
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader)));
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objYouGotApprovedPageHeader)), propertyFileReader("LoanApprovedPage", "ShopInstallmentLoan"), " Page Header");
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objIAcceptTheOffer), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objIAcceptTheOffer)));
            waitTime(2000);
            tellUsYourPayDayPageValidation();
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTopLeftSideBackArrowButton), " Back Arrow Button");
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objYouGotApprovedPageHeader)), propertyFileReader("LoanApprovedPage", "ShopInstallmentLoan"), " Page Header");
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objIAcceptTheOffer), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objIAcceptTheOffer)));
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFifthOfTheMonth), " Pays Day");
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNextBtn), "Next Button");
            waitTime(2000);//StaleElement
            verifyMonthlyInstallmentSummaryPage(propertyFileReader("Tenure", "ShopInstallmentLoan"), decimalFormat.format(Double.parseDouble(propertyFileReader("MonthlyInstallmentWithVas", "ShopInstallmentLoan"))));
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTopLeftSideBackArrowButton), " Back Arrow Button");
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("tellUsPageHeader", "QuickLoanWithVas"), "Page Header", "contains");
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNextBtn), "Next Button");
            verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objConfirmButton), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objConfirmButton)));
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), "Page"));
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPageSubHeader)).trim(), propertyFileReader("holdOnTxt", "QuickLoanWithVas"), "Page Sub header");
            logger.info("TDB-SILV-029, ShopInstallment Loan  - Verify if user can select the Pay Day & confirm Monthly Installment Summary");
            extentLoggerPass("TDB-SILV-029", "TDB-SILV-029, ShopInstallment Loan  - Verify if user can select the Pay Day & confirm Monthly Installment Summary");
        }
    }
    /**
     * Verify if user can sign all the Loan documents & disburse the loan successfully
     * @param password
     * @throws Exception
     */
    public void verifyUserCanSignAllTheLoanDocumentsAndDisburseTheLoanSuccessfully_TDB_SILV_031(String password) throws Exception {
        HeaderChildNode("TDB-SILV-031, ShopInstallment Loan  - Verify if user can sign all the Loan documents & disburse the loan successfully");
        tonikLogin(password);
        String tsaAmountInDashboard = getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTSAAmount));
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTile));
        if (verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTile), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTile)))) {
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText1)), propertyFileReader("Approved", "ShopInstallmentLoan"), "Header");
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader)));
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objYouGotApprovedPageHeader)), propertyFileReader("LoanApprovedPage", "ShopInstallmentLoan"), " Page Header");
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objIAcceptTheOffer), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objIAcceptTheOffer)));
            waitTime(2000);
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFifthOfTheMonth), " Pays Day");
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNextBtn), "Next Button");
            waitTime(2000);//StaleElement
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objPageHeader)), propertyFileReader("MonthlyInstallmentSummary", "ShopInstallmentLoan"), " Page Header");
            verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objConfirmButton), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objConfirmButton)));
            waitTime(2000);//StaleElement
            signedSealedDeliveredPageValidation();
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignPromissoryNoteBtn), "Sign Promissory Note Button");
            waitTime(4000);//Hard wait required for execution
            promissoryNotePageValidation();
            waitTime(3000);//Hard wait required for execution
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
            signedSealedDeliveredPageValidation();
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignPromissoryNoteBtn), "Sign Promissory Note Button");
            waitTime(10000);//Hard wait required for execution
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign");
            yourSignatureRequiredPageValidation();
            waitTime(3000);//Hard wait required for execution
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
            signedSealedDeliveredPageValidation();
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignPromissoryNoteBtn), "Sign Promissory Note Button");
            waitTime(10000);//Hard wait required for execution
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign Button");
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objConatctUsBtn), "Contact Us Button");
            contactUsPageUI();
            waitTime(3000);//Hard wait required for execution
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
            waitTime(3000);
            updateSignature();
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objEraseBtn), "Erase Button");
            waitTime(3000);
            updateSignature();
            isButtonEnabled(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignPromissoryNoteBtn), "Sign Promissory Note Button");
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignPromissoryNoteBtn), "Sign Promissory Note Button");
            waitTime(5000);//Hard wait required for execution
            disclosureStatementPageValidation();
            waitTime(3000);//Hard wait required for execution
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign Button");
            waitTime(3000);//Hard wait required for execution
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), "Page"));
            isButtonEnabled(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignDisclosureStatementBtn), "Sign Disclosure Statement Button");
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignDisclosureStatementBtn), "Sign Disclosure Statement Button");
            waitTime(5000);//Hard wait required for execution
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign Button");
            waitTime(3000);//Hard wait required for execution
            yourSignatureRequiredPageValidation();
            waitTime(3000);//Hard wait required for execution
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
            waitTime(3000);//Hard wait required for execution
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), "Page"));
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignDisclosureStatementBtn), "Sign Disclosure Statement Button");
            waitTime(2000);//Hard wait required for execution
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign Button");
            waitTime(2000);//Hard wait required for execution
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContactUsButton), "Contact Us Button");
            waitTime(3000);//Hard wait required for execution
            contactUsPageUI();
            waitTime(3000);//Hard wait required for execution
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
            waitTime(3000);
            updateSignature();
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objEraseBtn), "Erase Button");
            waitTime(3000);
            updateSignature();
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignDisclosureStatementBtn), "Sign Disclosure Statement Button");
            waitTime(3000);//Hard wait required for execution
            amortizationScheduleValidation();
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
            waitTime(3000);//Hard wait required for execution
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), "Page"));
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignAmortizationScheduleBtn), "Sign Amortization Schedule");
            waitTime(2000);//Hard wait required for execution
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign Button");
            waitTime(3000);//Hard wait required for execution
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("yourSignatureTxt", "QuickLoanWithVas"), "Page Header");
            waitTime(3000);//Hard wait required for execution
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
            waitTime(3000);//Hard wait required for execution
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), "Page"));
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignAmortizationScheduleBtn), "Sign Amortization Schedule");
            waitTime(5000);//Hard wait required for execution
            amortizationScheduleValidation();
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign Button");
            waitTime(3000);//Hard wait required for execution
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objConatctUsBtn), "Contact Us Button");
            waitTime(3000);//Hard wait required for execution
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
            waitTime(3000);//Hard wait required for execution
            updateSignature();
            waitTime(3000);//Hard wait required for execution
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignAmortizationScheduleBtn), "Sign Amortization Schedule");
            waitForElementToBePresent(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTimeToClaimYourItemPageHeader),3," Time To Claim Your Item Page");
            containsValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTimeToClaimYourItemPageHeader)), propertyFileReader("TimeToClaim", "ShopInstallmentLoan"), " Page Header");
            if (platform.equalsIgnoreCase("ios")) {
                closeiOSTonikApp();
            } else {
                closeAndroidTonikApp();
            }
            relaunchApp(platform);
            tonikLogin(password);
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTSAAmount)),tsaAmountInDashboard," Tsa Amount After Loan Disburse Is not Changed");
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTile)), propertyFileReader("Loans", "ShopInstallmentLoan"), "Header");
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText1)), propertyFileReader("MeetTheCashier", "ShopInstallmentLoan"), "Header");
            containsValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText2)), propertyFileReader("TimeToClaimYourItem", "ShopInstallmentLoan"), "Header");
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader)));
            containsValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTimeToClaimYourItemPageHeader)), propertyFileReader("TimeToClaim", "ShopInstallmentLoan"), " Page Header");
            logger.info("TDB-SILV-031, ShopInstallment Loan  - Verify if user can sign all the Loan documents & disburse the loan successfully");
            extentLoggerPass("TDB-SILV-031", "TDB-SILV-031, ShopInstallment Loan  - Verify if user can sign all the Loan documents & disburse the loan successfully");
        }
    }
    /**
     * Verify if the user can view and access Time to claim your item screen
     * @param password
     * @throws Exception
     */
    public void verifyUserCanViewAndAccessTimeToClaimYourItemScreen_TDB_SILV_032(String password) throws Exception {
        HeaderChildNode("TDB-SILV-032, ShopInstallment Loan - Verify if the user can view and access Time to claim your item screen");
        tonikLogin(password);
        if (getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText1)).equalsIgnoreCase(propertyFileReader("Approved", "ShopInstallmentLoan"))) {
            navigateFromApprovedToTimeToClaimPage();
        }
        else {
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader)));
        }
        verifyTimeToClaimYourItemPage(propertyFileReader("LoanableAmount","ShopInstallmentLoan"),propertyFileReader("MonthlyInstallmentWithVas","ShopInstallmentLoan"),"WithVas");
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objReadyToPayButtonText),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objReadyToPayButtonText)));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objAlreadyAtTheCahierPageHeader)).replaceAll("\\s"," "),propertyFileReader("AlreadyAtTheCashier","ShopInstallmentLoan")," Page Header");
        logger.info("TDB-SILV-032, ShopInstallment Loan - Verify if the user can view and access Time to claim your item screen");
        extentLoggerPass("TDB-SILV-032", "ShopInstallment Loan - Verify if the user can view and access Time to claim your item screen");
    }
    /**
     * Verify if the user can request for confirmation code from the cashier
     * @param password
     * @throws Exception
     */
    public void verifyUserCanRequestConfirmationCodeFromTheCashier_TDB_SILV_033(String password) throws Exception {
        HeaderChildNode("TDB-SILV-033, ShopInstallment Loan - Verify if the user can request for confirmation code from the cashier");
        tonikLogin(password);
        if (getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText1)).equalsIgnoreCase(propertyFileReader("Approved", "ShopInstallmentLoan"))) {
            navigateFromApprovedToTimeToClaimPage();
        }
        else {
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader)));
        }
        verifyTimeToClaimYourItemPage(propertyFileReader("LoanableAmount","ShopInstallmentLoan"),propertyFileReader("MonthlyInstallmentWithVas","ShopInstallmentLoan"),"WithVas");
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objReadyToPayButtonText),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objReadyToPayButtonText)));
        verifyAlreadyAtTheCashierPage();
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objBackButtonInAlreadyAtCashierPage),"Back Arrow Button");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTimeToClaimYourItemPageHeader)),propertyFileReader("TimeToClaim","ShopInstallmentLoan")," Page Header");
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objReadyToPayButtonText),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objReadyToPayButtonText)));
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objSendConfirmationCodeButtonText),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objSendConfirmationCodeButtonText)));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPayTogetYourConfirmationCodePageHeader)),propertyFileReader("PayToGetConfirmationCode","ShopInstallmentLoan")," Page Header");
        logger.info("TDB-SILV-033, ShopInstallment Loan - Verify if the user can request for confirmation code from the cashier");
        extentLoggerPass("TDB-SILV-033", "ShopInstallment Loan - Verify if the user can request for confirmation code from the cashier");
    }
    /**
     * Verify if the user can input confirmation code- validation
     * @param password
     * @throws Exception
     */
    public void verifyUserCanInputConfirmationCodeValidation_TDB_SILV_034(String password) throws Exception {
        HeaderChildNode("TDB-SILV-034, ShopInstallment Loan - Verify if the user can input confirmation code- validation");
        tonikLogin(password);
        tsaAmountInDashboard=getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTSAAmount));
        if (getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText1)).equalsIgnoreCase(propertyFileReader("Approved", "ShopInstallmentLoan"))) {
            navigateFromApprovedToTimeToClaimPage();
        }
        else {
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader)));
        }
        loanDisburs();
        waitForElementToBePresent(LoginPage.getByOSType(platform,LoginPage.objProfileName),5,"Profile name");
        verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objProfileName),getTextVal(LoginPage.getByOSType(platform,LoginPage.objProfileName)," : Profile Name"));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTSAAmount)),tsaAmountInDashboard," TSA Amount After Disburse");
        logger.info("TDB-SILV-034, ShopInstallment Loan - Verify if the user can input confirmation code- validation");
        extentLoggerPass("TDB-SILV-034", "ShopInstallment Loan - Verify if the user can input confirmation code- validation");
    }
    /**
     * Verify if the Loans tile status is changed after the successful loan disbursal
     * @param password
     * @throws Exception
     */
    public void verifyTheLoansTileStatusIsChangedAfterTheSuccessfulLoanDisbursal_TDB_SILV_035(String password) throws Exception {
        HeaderChildNode("TDB-SILV-035, ShopInstallment Loan - Verify if the Loans tile status is changed after the successful loan disbursal");
        tonikLogin(password);
        loanCommonMethods.iWillDoItLaterPopup();
        if(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText1)).equalsIgnoreCase(propertyFileReader("MeetTheCashier", "ShopInstallmentLoan"))) {
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText1)), propertyFileReader("MeetTheCashier", "ShopInstallmentLoan"), " Page Header");
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader)));
            loanDisburs();
        }
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("yes","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText2)),"₱"+decimalFormat.format(Double.parseDouble(propertyFileReader("LoanableAmount","ShopInstallmentLoan")))+" payment was just sent to "+propertyFileReader("StoreName","ShopInstallmentLoan")," Text");
        logger.info("TDB-SILV-035, ShopInstallment Loan - Verify if the Loans tile status is changed after the successful loan disbursal");
        extentLoggerPass("TDB-SILV-035", "ShopInstallment Loan - Verify if the Loans tile status is changed after the successful loan disbursal");
    }
    /**
     * Verify the user can view the Loan Dashboard screen
     * @param password
     * @throws Exception
     */
    public void verifyUserCanViewTheLoanDashboardScreen_TDB_SILV_037(String password) throws Exception {
        HeaderChildNode("TDB-SILV-037, ShopInstallment Loan - Verify the user can view the Loan Dashboard screen");
        tonikLogin(password);
        if(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText1)).equalsIgnoreCase(propertyFileReader("yes", "ShopInstallmentLoan"))) {
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText1)), propertyFileReader("yes", "ShopInstallmentLoan"), " Page Header");
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader)));
            verifyLoanDashboardPage();
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objContactUsButton),"Contact Us Button");
            verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objGetInTouchWithCustomerCarePage),"Get in touch page");
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTopLeftSideBackArrowButton), " Back Arrow Button");
            containsValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextInstallmentText)),propertyFileReader("NextInstallment","ShopInstallmentLoan")," text");
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTopLeftSideBackArrowButton), " Back Arrow Button");
            verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objProfileName),"Profile name In DashBoard");
            logger.info("TDB-SILV-037, ShopInstallment Loan - Verify the user can view the Loan Dashboard screen");
            extentLoggerPass("TDB-SILV-037", "ShopInstallment Loan - Verify the user can view the Loan Dashboard screen");
        }
    }
    /**
     * Verify if user can access the PayHinga tile from the Loan Dashboard screen
     * @param password
     * @throws Exception
     */
    public void verifyUserCanAccessThePayHingaTileFromTheLoanDashboardScreen_TDB_SILV_038(String password) throws Exception {
        HeaderChildNode("TDB-SILV-038, ShopInstallment Loan - Verify if user can access the PayHinga tile from the Loan Dashboard screen");
        tonikLogin(password);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPayhingaTitle)), propertyFileReader("payHingaTxt", "QuickLoanWithVas"), "Payhinga Tile");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLearnMoreBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLearnMoreBtn), "Button"));
        waitTime(3000);
        if(platform.equalsIgnoreCase("ios")){
            List<IOSElement> values = DriverManager.getAppiumDriver().findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTileSubHeader));
            for (int tile = 1; tile < values.size(); tile++) {
                assertionValidation(getText(QuickLoanWithVasPage.objTileSubHeader(platform, tile)),propertyFileReader("tile"+tile,"QuickLoanWithVas"),": Tile");
                containsValidation(getText(QuickLoanWithVasPage.objTileInfo(platform, tile)),propertyFileReader("tileInfo"+tile,"QuickLoanWithVas"),": Tile Info");
            }
        } else {
            List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTileSubHeader));
            for (int tile = 1; tile < values.size(); tile++) {
                assertionValidation(getText(QuickLoanWithVasPage.objTileSubHeader(platform, tile)),propertyFileReader("tile"+tile,"QuickLoanWithVas"),": Tile");
                containsValidation(getText(QuickLoanWithVasPage.objTileInfo(platform, tile)),propertyFileReader("tileInfo"+tile,"QuickLoanWithVas"),": Tile Info");
            }
        }
        if(platform.equalsIgnoreCase("android")){
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadOurFAQBtn), "Read our FAQs to learn more Button");
            verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objUrl), getTextVal(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objUrl), "URL"));
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objUrl)),propertyFileReader("PayHingaUrl","ShopInstallmentLoan")," PayHinga Url");
            DriverManager.getAppiumDriver().navigate().back();
        } else {
            swipe("UP", 1);
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadOurFAQBtn), "Read our FAQs to learn more Button");
        }
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objGotItButton), "Got It Button");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextInstallmentTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextInstallmentTxt), "Loan Dashboard Screen"));
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLearnMoreBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLearnMoreBtn), "Button"));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMunaPageHowItWorks), "How it works Button");
        if(platform.equalsIgnoreCase("ios")){
            List<IOSElement> values = DriverManager.getAppiumDriver().findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTileSubHeader));
            for (int tile = 1; tile < values.size(); tile++) {
                assertionValidation(getText(QuickLoanWithVasPage.objTileSubHeader(platform, tile)),propertyFileReader("howItWorksTile"+tile,"QuickLoanWithVas"),": Tile");
                containsValidation(getText(QuickLoanWithVasPage.objTileInfo(platform, tile)),propertyFileReader("howItWorksInfo"+tile,"QuickLoanWithVas"),": Tile Info");
            }
        } else {
            List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTileSubHeader));
            for (int tile = 1; tile < values.size(); tile++) {
                assertionValidation(getText(QuickLoanWithVasPage.objTileSubHeader(platform, tile)),propertyFileReader("howItWorksTile"+tile,"QuickLoanWithVas"),": Tile");
                containsValidation(getText(QuickLoanWithVasPage.objTileInfo(platform, tile)),propertyFileReader("howItWorksInfo"+tile,"QuickLoanWithVas"),": Tile Info");
            }
        }
        swipe("UP", 1);
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHelUsTxt)), propertyFileReader("howItWorksTile5", "QuickLoanWithVas"), "Tile header");
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHelpUsTileSubHeader)), propertyFileReader("howItWorksInfo5", "QuickLoanWithVas"), "Tile Sub Header");
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadOurFAQBtn)), propertyFileReader("readOurFAQButtonTxt", "QuickLoanWithVas"), "Button Text");
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objGotItButton)), propertyFileReader("gotItBtnTxt", "QuickLoanWithVas"), "Button Text");
        if(platform.equalsIgnoreCase("android")){
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadOurFAQBtn), "Read our FAQs to learn more Button");
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objUrl)),propertyFileReader("PayHingaUrl","ShopInstallmentLoan")," PayHinga Url");
            verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objUrl), getTextVal(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objUrl), "URL"));
            DriverManager.getAppiumDriver().navigate().back();
        } else {
            swipe("UP", 1);
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadOurFAQBtn), "Read our FAQs to learn more Button");
        }
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objGotItButton), "Got It Button");
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextInstallmentTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextInstallmentTxt), "Loan Dashboard Screen"))) {
            logger.info("TDB-SILV-038, ShopInstallment Loan - Verify if user can access the PayHinga tile from the Loan Dashboard screen");
            extentLoggerPass("TDB-SILV-038", "ShopInstallment Loan - Verify if user can access the PayHinga tile from the Loan Dashboard screen");
        }
    }
    /**
     * Verify if user can access the Life Insurance tile from the Loan Dashboard screen
     * @throws Exception
     */
    public void verifyUserCanAccessLifeInsuranceTileFromLoanDashboardScreen_TDB_SILV_039(String password) throws Exception {
        HeaderChildNode("TDB-SILV-039, Verify if user can access the Life Insurance tile from the Loan Dashboard screen");
        tonikLogin(password);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLifeInsurancePayhinga), 10, "Life Insurance Payhinga");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLifeInsurancePayhinga), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLifeInsurancePayhinga),"Payhinga Tile"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt),"Page Header"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("proofOfCover", "QuickLoanWithVas"), "Page Header");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), 10, "Download Button");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), "Button"));
        if(platform.equalsIgnoreCase("ios")){
            waitTime(3000);
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCopyButtonIOS), "Copy Button");
            waitTime(2000);
        }
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextInstallmentTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextInstallmentTxt), "Text"))) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseLoanButton)), propertyFileReader("iWantToCloseLoanBtn", "QuickLoanWithVas"), "Button");
            logger.info("TDB-SILV-039, ShopInstallment Loan - Verify if user can access the Life Insurance tile from the Loan Dashboard screen");
            extentLoggerPass("TDB-SILV-039", "ShopInstallment Loan - Verify if user can access the Life Insurance tile from the Loan Dashboard screen");
        }
    }
    /**
     * Verify if user can access the Loan Information screen
     * @throws Exception
     */
    public void verifyUserCanAccessTheLoanInformationScreen_TDB_SILV_040(String password) throws Exception {
        HeaderChildNode("TDB-SILV-040, Verify if user can access the Loan Information screen");
        tonikLogin(password);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objIicon),"Loan Information I icon");
        verifyLoanInformationPage(decimalFormat.format(Double.parseDouble(propertyFileReader("LoanableAmount","ShopInstallmentLoan"))), todaysPHDate("dd MMMM yyyy"),propertyFileReader("Tenure","ShopInstallmentLoan"),decimalFormat.format(Double.parseDouble(propertyFileReader("MonthlyInstallmentWithVas","ShopInstallmentLoan"))),propertyFileReader("InsuranceCoverageamount","ShopInstallmentLoan"));
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextInstallmentTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextInstallmentTxt), "Text"))) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseLoanButton)), propertyFileReader("iWantToCloseLoanBtn", "QuickLoanWithVas"), "Button");
            logger.info("TDB-SILV-040, ShopInstallment Loan - Verify if user can access the Loan Information screen");
            extentLoggerPass("TDB-SILV-040", "ShopInstallment Loan - Verify if user can access the Loan Information screen");
        }
    }
    /**
     * Verify if user can view the Rates and fees details
     * @throws Exception
     */
    public void verifyUserCanViewRatesFeesDetails_TDB_QLV_041(String password) throws Exception {
        HeaderChildNode("TDB-SILV-041, Verify if user can view the Rates and fees details");
        tonikLogin(password);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoButton), 10, "Information Button");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoButton), "Information Button");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoScreeRateAndFees), "Rates and fees");
        verifyRatesAndFeePage("WithVas");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt)))) {
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageHeader)),propertyFileReader("LoanInformation","ShopInstallmentLoan")," Text");
            logger.info("TDB-SILV-041, Verify if user can view the Rates and fees details validated");
            extentLoggerPass("TDB-SILV-041", "Verify if user can view the Rates and fees details validated");
        }
    }
    /**
     * Verify if user can view & download all the Loan documents
     * @throws Exception
     */
    public void verifyUserCanViewAndDownloadAllTheLoanDocuments_TDB_QLV_042(String password) throws Exception {
        HeaderChildNode("TDB-SILV-042, Verify if user can view & download all the Loan documents");
        tonikLogin(password);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoButton),  "Information Icon");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageHeader)),propertyFileReader("LoanInformation","ShopInstallmentLoan")," Text");
        clickOnAllDocumentAndDownload();
        waitTime(3000);
        if(verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageHeader), getTextVal(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageHeader), "Text"))) {
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageHeader)),propertyFileReader("LoanInformation","ShopInstallmentLoan")," Text");
            logger.info("TDB-SILV-042, Verify if user can view & download all the Loan documents");
            extentLoggerPass("TDB-SILV-042", "Verify if user can view & download all the Loan documents");
        }
    }
    /**
     * Verify if user can view the Loan Payment screen
     * @param dueDate
     * @throws Exception
     */
    public void verifyUserCanViewLoanPaymentScreen_TDB_SILV_045(String password,int dueDate) throws Exception {
        HeaderChildNode("TDB-SILV-045, ShopInstallment Loan - Verify if user can view the Loan Payment screen");
        String userId = getUserId(propertyFileReader("SILMobileNumber", "TestDataNumbers"));
        String loanAccountNumber = getLoanAccountNumber(userId);
        String date = returnDate(dueDate, "yyyy-MM-dd");
        updateDueDateInLoanInstallmentDetails(date, loanAccountNumber);
        logger.info("Due date modified in Loan Installment details: " + date);
        extentLogger("", "Due date modified in Loan Installment details: " + date);
        updateRepaymentDateInLoanRepaymentSchedule(date, loanAccountNumber, "1");
        tonikLogin(password);
        String balance = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBalanceOnScreen));
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayNowBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayNowBtn), "Button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanPaymentScreen), 5, "Loan payment screen");
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageTitle), "Screen"))) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageTitle)), propertyFileReader("LoanPaymentScreen", "QuickLoanWithVas"), "Screen");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAmountDueField)), propertyFileReader("AmountDueField", "QuickLoanWithVas"), "field");
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAmountDue)), decimalFormat.format(Double.parseDouble(propertyFileReader("MonthlyInstallmentWithVas", "ShopInstallmentLoan"))), "Amount due");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLateFeesField)), propertyFileReader("LateFeesField", "QuickLoanWithVas"), "field");
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLateFee)).replaceAll(",", ""), propertyFileReader("LateFee", "QuickLoanWithVas"), "Late fee");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAmountToBePaidField)), propertyFileReader("AmountToBePaidField", "QuickLoanWithVas"), "field");
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAmountToBePaid)), decimalFormat.format(Double.parseDouble(propertyFileReader("MonthlyInstallmentWithVas", "ShopInstallmentLoan"))), "Amount to be paid");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPaymentOptionsHeader)), propertyFileReader("PaymentOptionsHeader", "QuickLoanWithVas"), "Header");
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBalanceDisplayedInLoanPaymentScreen)), balance, "Balance");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objOtherHeader)), propertyFileReader("OtherHeader", "QuickLoanWithVas"), "Header");
            swipe("UP", 1);
            if (platform.equalsIgnoreCase("ios")) {
                List<IOSElement> values = DriverManager.getAppiumDriver().findElements(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objOtherPaymentOptions));
                for (int option = 1; option <= values.size(); option++) {
                    assertionValidation(getText(QuickLoanWithVasPage.objOtherPaymentOptions(platform, option)), propertyFileReader("OtherPaymentOptions" + option, "QuickLoanWithVas"), ": Other Payment option");
                    assertionValidation(getText(QuickLoanWithVasPage.objOtherPaymentOptionsInfo(platform, option)), propertyFileReader("OtherPaymentOptionsInfo" + option, "QuickLoanWithVas"), ": Other Payment option info");
                }
            } else {
                List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objOtherPaymentOptions));
                for (int option = 1; option <= values.size(); option++) {
                    assertionValidation(getText(QuickLoanWithVasPage.objOtherPaymentOptions(platform, option)), propertyFileReader("OtherPaymentOptions" + option, "QuickLoanWithVas"), ": Other Payment option");
                    assertionValidation(getText(QuickLoanWithVasPage.objOtherPaymentOptionsInfo(platform, option)), propertyFileReader("OtherPaymentOptionsInfo" + option, "QuickLoanWithVas"), ": Other Payment option info");
                }
            }
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSelectPaymentOption)), propertyFileReader("SelectPaymentOption", "QuickLoanWithVas"), "Text");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objConfirmPaymentBtn)), propertyFileReader("ConfirmPaymentBtn", "QuickLoanWithVas"), "Button");
            verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBalanceOnScreen)), balance, "Balance");
            logger.info("TDB-SILV-045, ShopInstallment Loan - Verify if user can view the Loan Payment screen");
            extentLoggerPass("TDB-SILV-045", "ShopInstallment Loan - Verify if user can view the Loan Payment screen");
        }
    }
    /**
     * Verify if user can make full repayment with insufficient TSA balance
     * @throws Exception
     */
    public void verifyUserMakeFullRepaymentWithInsufficientTSABalance_TDB_SILV_116(String password) throws Exception {
        HeaderChildNode("TDB-SILV-116, Verify if user can make full repayment with insufficient TSA balance");
        waitForElementToBePresent(LoginPage.getByOSType(platform, LoginPage.objEditPassword), 60, "Password edit field");
        String camPermHeaderTxt = getText(LoginPage.getByOSType(platform, LoginPage.objEditPassword));
        Assert.assertEquals(camPermHeaderTxt, "Password");
        click(LoginPage.getByOSType(platform, LoginPage.objEditPassword), "Password field");
        type(LoginPage.getByOSType(platform, LoginPage.objEditPassword), password, "Enter secret code");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform, LoginPage.objLoginBtn), getTextVal(LoginPage.getByOSType(platform, LoginPage.objLoginBtn), "Button"));
        loanCommonMethods.iWillDoItLaterPopup();
        waitForElementToBePresent(LoginPage.getByOSType(platform, LoginPage.objProfileName), 5, ": Profile Name");
        verifyElementPresent(LoginPage.getByOSType(platform, LoginPage.objProfileName), getTextVal(LoginPage.getByOSType(platform, LoginPage.objProfileName), " : Profile Name"));
        logger.info("Successfully logged into Tonik app");
        extentLoggerPass("", "Successfully logged into Tonik app");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseLoanButton), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseLoanButton),"Button"));
        verifyBreakdownPage(propertyFileReader("LoanableAmount","ShopInstallmentLoan"),propertyFileReader("PayHingaAmount","ShopInstallmentLoan"),propertyFileReader("ProcessingFeeValue","ShopInstallmentLoan"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTonikAccBalTxt)), propertyFileReader("tonikAccBal", "ShopInstallmentLoan"), "Text");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceAmount), "Balance Amount"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTopUpTonicAccountButton)), propertyFileReader("topUpMyTonikBtnTxt", "QuickLoanWithVas"), "Button");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextInstallmentTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextInstallmentTxt), "Loan Dashboard Screen"))) {
            logger.info("TDB-SILV-116, Verify if user can make full repayment with insufficient TSA balance");
            extentLoggerPass("TDB-SILV-116", "TDB-SILV-116, Verify if user can make full repayment with insufficient TSA balance");
        }
    }
    /**
     * Verify if user can make Top-up from Loan Dashboard & do the full repayment
     * @throws Exception
     */
    public void verifyUserCanMakeTopupFromLoanDashboardAndDoTheFullRepayment_TDB_SILV_117(String password) throws Exception {
        HeaderChildNode("TDB-SILV-117, Verify if user can make Top-up from Loan Dashboard & do the full repayment");
        tonikLogin(password);
        tsaAmountInDashboard = getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTSAAmount)).substring(1).replaceAll(",", "");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseLoanButton), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseLoanButton),"Button"));
        String fullRepaymentAmount = getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objFullRepaymentAmountValue)).substring(1).replaceAll(",", "");
        if(verifyElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopUpMyAccountButton))){
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopUpMyAccountButton)," Topup My Account");
            waitForElementToBePresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTopupPageHeader),5,"Top up screen");
            timeDepositModule.topupViaGCash(propertyFileReader("TopupAmount","ShopInstallmentLoan"));
            tsaAmountInDashboard = getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTSAAmount)).substring(1).replaceAll(",","");
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),"Loan Tile");
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseMyLoan),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseMyLoan)));
        }
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageHeader)),propertyFileReader("BeforeWeClose","ShopInstallmentLoan")," Page Header");
        verifyBreakdownPage(propertyFileReader("LoanableAmount","ShopInstallmentLoan"),propertyFileReader("PayHingaAmount","ShopInstallmentLoan"),propertyFileReader("ProcessingFeeValue","ShopInstallmentLoan"));
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseMyLoanButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseMyLoanButton)));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objDropTheMic)),propertyFileReader("DropTheMic","ShopInstallmentLoan")," Page header");
        containsValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objFullyPaidText)).replaceAll("\\s"," "),propertyFileReader("YourLoanFullyPaid","ShopInstallmentLoan")," Page SubTitle");
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objBackToDashboard),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objBackToDashboard)));
        verticalSwipeTillElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoanTileHeader)),propertyFileReader("FastCash","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objFindTheRightLoan)),propertyFileReader("FindTheRightLoan","ShopInstallmentLoan"),"Header");
        swipe("DOWN",2);
        verifyTransactionPage(decimalFormat.format(Double.parseDouble(propertyFileReader("LoanableAmount","ShopInstallmentLoan"))+Double.parseDouble(propertyFileReader("PayHingaAmount","ShopInstallmentLoan"))),propertyFileReader("FullPayment","ShopInstallmentLoan"),propertyFileReader("ViaEarlySettlement","ShopInstallmentLoan"));
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopLeftSideBackArrowButton),"Back arrow Button");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objAccountHistoryPageHeader)), propertyFileReader("AccountHistory", "TimeDeposit"), " Header");
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopLeftSideBackArrowButton),"Back arrow Button");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objProfileName),"Profile Name In DashBoard");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTSAAmount)),"₱"+decimalFormat.format(Double.parseDouble(tsaAmountInDashboard)-Double.parseDouble(fullRepaymentAmount))," Tsa Amount After Ful Repayment");
        logger.info("TDB-SILV-117, Verify if user can make Top-up from Loan Dashboard & do the full repayment");
        extentLoggerPass("TDB-SILV-117", "TDB-SILV-117, Verify if user can make Top-up from Loan Dashboard & do the full repayment");
    }
    /**
     * Verify the Loans tile status after the full loan repayment
     * @throws Exception
     */
    public void verifyLoansTileStatusAfterTheFullLoanRepayment_TDB_SILV_119_120(String password) throws Exception {
        HeaderChildNode("TDB-SILV-119, Verify the Loans tile status after the full loan repayment And Reapply the SIL");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile));
        if(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)).equals(propertyFileReader("yes","ShopInstallmentLoan"))){
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseLoanButton), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseLoanButton),"Button"));
            if(verifyElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopUpMyAccountButton))){
                click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopUpMyAccountButton)," Topup My Account");
                timeDepositModule.topupViaGCash(propertyFileReader("TopupAmount","ShopInstallmentLoan"));
                click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader),"Loan Tile");
                click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseMyLoan),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseMyLoan)));
            }
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseMyLoanButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseMyLoanButton)));
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objDropTheMic)),propertyFileReader("DropTheMic","ShopInstallmentLoan")," Page header");
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objBackToDashboard),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objBackToDashboard)));
        }
        else{
            verticalSwipeTillElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile));
        }
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoanTileHeader)),propertyFileReader("FastCash","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objFindTheRightLoan)),propertyFileReader("FindTheRightLoan","ShopInstallmentLoan"),"Header");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        loanTileValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTile),getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTile)));
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objGotchaButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objGotchaButton)));
        verifyScanQRPage();
        if(platform.equalsIgnoreCase("ios")){
            closeiOSTonikApp();
        } else{
            closeAndroidTonikApp();
        }
        shopInstallmentLoanAPIModule.createSILLoan(propertyFileReader("SILMobileNumber", "TestDataNumbers"),propertyFileReader("vasFlag","ShopInstallmentLoan"),propertyFileReader("MonthlyInstallmentWithVas","ShopInstallmentLoan"));
        relaunchApp(platform);
        waitTime(3000);
        tonikLogin(password);
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("InProgress","ShopInstallmentLoan"),"Header");
        logger.info("TDB-SILV-119 TDB-SILV-120, Verify the Loans tile status after the full loan repayment And Reapply the SIL");
        extentLoggerPass("TDB-SILV-119 TDB-SILV-120", "TDB-SILV-119 TDB-SILV-120, Verify the Loans tile status after the full loan repayment And Reapply the SIL");
    }
    /**
     * Verify if SKYC user can apply SIL if TSA is created already
     * @param password
     * @throws Exception
     */
    public void verifySKYCUserCanApplySILIfTSAIsCreatedAlready_TDB_SILV_126(String password,String mobileNumber) throws Exception {
        HeaderChildNode("TDB-SILV-126, ShopInstallment Loan - Verify if SKYC user can apply SIL if TSA is created already");
        updateUTCTime(mobileNumber);
        tonikLogin(password);
        tsaAmountInDashboard = getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTSAAmount)).substring(1).replaceAll(",","");
        if(verifyElementNotDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInprogressStatus),"InProgress")) {
            verticalSwipeTillElementDisplayed(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile));
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), "Tile"));
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objShoppingTile), getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objShoppingTile)));
            verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objGotchaButton), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objGotchaButton)));
            verifyScanQRPage();
            if (platform.equalsIgnoreCase("ios")) {
                closeiOSTonikApp();
            } else {
                closeAndroidTonikApp();
            }
            shopInstallmentLoanAPIModule.createSILLoan(propertyFileReader("SILMobileNumber", "TestDataNumbers"), propertyFileReader("vasFlag", "ShopInstallmentLoan"), propertyFileReader("MonthlyInstallmentWithVas", "ShopInstallmentLoan"));
            relaunchApp(platform);
            waitTime(3000);
            tonikLogin(password);
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader)), propertyFileReader("Loans", "ShopInstallmentLoan"), "Header");
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText1)), propertyFileReader("InProgress", "ShopInstallmentLoan"), "Header");
        }
        click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader)));
        loanSegment2NavigationForSIL(propertyFileReader("ValidTIN","QuickLoanWithVas"),propertyFileReader("ValidIncome","ShopInstallmentLoan"), propertyFileReader("ReferenceNumber1", "QuickLoanWithVas"),propertyFileReader("ReferenceNumber2", "QuickLoanWithVas"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objProfileName),5,"Profile Name");
        waitTime(70000);
        if(platform.equalsIgnoreCase("ios")){
            closeiOSTonikApp();
        } else{
            closeAndroidTonikApp();
        }
        relaunchApp(platform);
        tonikLogin(password);
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("Approved","ShopInstallmentLoan"),"Header");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        verifyLoanApprovedPage(decimalFormat.format(Double.parseDouble(propertyFileReader("LoanableAmount","ShopInstallmentLoan"))),decimalFormat.format(Double.parseDouble(propertyFileReader("MonthlyInstallmentWithVas","ShopInstallmentLoan"))),propertyFileReader("Tenure","ShopInstallmentLoan"));
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objIAcceptTheOffer),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objIAcceptTheOffer)));
        waitTime(2000);
        tellUsYourPayDayPageValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objFifthOfTheMonth)," Pays Day");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),"Next Button");
        verifyMonthlyInstallmentSummaryPage(propertyFileReader("Tenure","ShopInstallmentLoan"),decimalFormat.format(Double.parseDouble(propertyFileReader("MonthlyInstallmentWithVas","ShopInstallmentLoan"))));
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objConfirmButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objConfirmButton)));
        waitTime(2000);
        validateAndSignAllThePages();
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTimeToClaimYourItemPageHeader)),propertyFileReader("TimeToClaim","ShopInstallmentLoan")," Page Header");
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objReadyToPayButtonText),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objReadyToPayButtonText)));
        verifyAlreadyAtTheCashierPage();
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objSendConfirmationCodeButtonText),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objSendConfirmationCodeButtonText)));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPayTogetYourConfirmationCodePageHeader)),propertyFileReader("PayToGetConfirmationCode","ShopInstallmentLoan")," Page Header");
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objConfirmationCodeTextField),"Confirmation Code Textfield");
        DriverManager.getAppiumDriver().getKeyboard().sendKeys(RandomIntegerGenerator(6));
        verifyPurchasedPage(decimalFormat.format(Double.parseDouble(propertyFileReader("LoanableAmount","ShopInstallmentLoan"))));
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objBackToDashboard),"Back To DashBoard");
        waitForElementToBePresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPinkySwearButton),5,"Communication pop up");
        loanCommonMethods.iWillDoItLaterPopup();
        waitForElementToBePresent(LoginPage.getByOSType(platform,LoginPage.objProfileName),5,"Profile name");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("yes","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText2)),"₱"+decimalFormat.format(Double.parseDouble(propertyFileReader("LoanableAmount","ShopInstallmentLoan")))+" payment was just sent to "+propertyFileReader("StoreName","ShopInstallmentLoan")," Text");
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader),"Loan Tile");
        waitTime(2000);
        verifyLoanDashboardPage();
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPayHingaText),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPayHingaText)));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageHeader)).replaceAll("\\s"," "),propertyFileReader("PayHingaPageHeader","ShopInstallmentLoan")," Text");
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopLeftSideBackArrowButton),"Back arrow Button");
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLifeInsuranceText),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLifeInsuranceText)));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageHeader)),propertyFileReader("LifeInsurancePageHeader","ShopInstallmentLoan")," Text");
        waitForElementToBePresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objProofofCoverSheet),3,"Proof Of Cover Sheet");
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopLeftSideBackArrowButton),"Back arrow Button");
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objIicon),"Loan Information I icon");
        verifyLoanInformationPage(decimalFormat.format(Double.parseDouble(propertyFileReader("LoanableAmount","ShopInstallmentLoan"))), todaysPHDate("dd MMMM yyyy"),propertyFileReader("Tenure","ShopInstallmentLoan"),decimalFormat.format(Double.parseDouble(propertyFileReader("MonthlyInstallmentWithVas","ShopInstallmentLoan")))
                ,propertyFileReader("InsuranceCoverageamount","ShopInstallmentLoan"));
        clickOnAllDocumentAndDownload();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseMyLoan),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseMyLoan)));
        if(verifyElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopUpMyAccountButton))){
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopUpMyAccountButton)," Topup My Account");
            timeDepositModule.topupViaGCash(propertyFileReader("TopupAmount","ShopInstallmentLoan"));
            tsaAmountInDashboard = getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTSAAmount)).substring(1).replaceAll(",","");
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader),"Loan Tile");
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseMyLoan),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseMyLoan)));
        }
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageHeader)),propertyFileReader("BeforeWeClose","ShopInstallmentLoan")," Page Header");
        verifyBreakdownPage(propertyFileReader("LoanableAmount","ShopInstallmentLoan"),propertyFileReader("PayHingaAmount","ShopInstallmentLoan"),propertyFileReader("ProcessingFeeValue","ShopInstallmentLoan"));
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseMyLoanButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseMyLoanButton)));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objDropTheMic)),propertyFileReader("DropTheMic","ShopInstallmentLoan")," Page header");
        containsValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objFullyPaidText)).replaceAll("\\s"," "),propertyFileReader("YourLoanFullyPaid","ShopInstallmentLoan")," Page SubTitle");
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objBackToDashboard),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objBackToDashboard)));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTSAAmount)),"₱"+decimalFormat.format(Double.parseDouble(tsaAmountInDashboard)-(Double.parseDouble(propertyFileReader("LoanableAmount","ShopInstallmentLoan"))+Double.parseDouble(propertyFileReader("PayHingaAmount","ShopInstallmentLoan"))))," TSA Amount After Full Repayment");
        verticalSwipeTillElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoanTileHeader)),propertyFileReader("FastCash","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objFindTheRightLoan)),propertyFileReader("FindTheRightLoan","ShopInstallmentLoan"),"Header");
        swipe("DOWN",2);
        verifyTransactionPage(decimalFormat.format(Double.parseDouble(propertyFileReader("LoanableAmount","ShopInstallmentLoan"))+Double.parseDouble(propertyFileReader("PayHingaAmount","ShopInstallmentLoan"))),propertyFileReader("FullPayment","ShopInstallmentLoan"),propertyFileReader("ViaEarlySettlement","ShopInstallmentLoan"));
        logger.info("TDB-SILV-126, ShopInstallment Loan - Verify if SKYC user can apply SIL if TSA is created already");
        extentLoggerPass("TDB-SILV-126", "ShopInstallment Loan - Verify if SKYC user can apply SIL if TSA is created already");
    }
    /**
     * Verify if user can quit the loan application
     * @param password
     * @throws Exception
     */
    public void verifyUserCanQuitTheLoanApplication_TDB_SILV_127(String password) throws Exception {
        HeaderChildNode("TDB-SILV-127, ShopInstallment Loan - Verify if user can quit the loan application");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        loanTileValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTile),getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTile)));
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objGotchaButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objGotchaButton)));
        verifyScanQRPage();
        if(platform.equalsIgnoreCase("ios")){
            closeiOSTonikApp();
        } else{
            closeAndroidTonikApp();
        }
        shopInstallmentLoanAPIModule.createSILLoan(propertyFileReader("SILMobileNumber", "TestDataNumbers"),propertyFileReader("vasFlag","ShopInstallmentLoan"),propertyFileReader("MonthlyInstallmentWithVas","ShopInstallmentLoan"));
        Wait(3000);
        relaunchApp(platform);
        waitTime(3000);
        tonikLogin(password);
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)));
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseApplicationButton)," Close Application Button");
        verifyLeavingSoSoonPage();
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn),"Next Button");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objGoodByeSeeyouLater)),propertyFileReader("GoodBye","ShopInstallmentLoan")," Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objGoodByeSeeYouLaterPageDescription)),propertyFileReader("FeelToFree","ShopInstallmentLoan")," Header");
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objBackToDashboard),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objBackToDashboard)));
        swipe("UP",2);
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoanTileHeader)),propertyFileReader("FastCash","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objFindTheRightLoan)),propertyFileReader("FindTheRightLoan","ShopInstallmentLoan"),"Header");
        logger.info("TDB-SILV-127, ShopInstallment Loan - Verify if user can quit the loan application");
        extentLoggerPass("TDB-SILV-127", "ShopInstallment Loan - Verify if user can quit the loan application");
    }
}
