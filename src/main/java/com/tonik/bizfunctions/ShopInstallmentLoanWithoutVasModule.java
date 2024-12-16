package com.tonik.bizfunctions;

import com.driverInstance.DriverManager;
import com.tonik.pageObject.LoginPage;
import com.tonik.pageObject.OnBoardingPage;
import com.tonik.pageObject.QuickLoanWithVasPage;
import com.tonik.pageObject.ShopInstallmentLoanPage;
import com.tonik.utility.ExtentReporter;
import com.tonik.utility.Utilities;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.WebElement;

import java.text.DecimalFormat;
import java.util.List;

import static com.tonik.utility.ExtentReporter.*;
import static com.tonik.utility.Utilities.extentLogger;
import static com.tonik.utility.Utilities.*;

public class ShopInstallmentLoanWithoutVasModule extends BaseClass{
    public ShopInstallmentLoanWithoutVasModule() {
        super();
    }
    String platform = Utilities.getPlatform();
    LoanCommonMethods loanCommonMethods = new LoanCommonMethods();
    protected ShopInstallmentLoanWithVasModule shopInstallmentLoanModule = new ShopInstallmentLoanWithVasModule();
    protected ShopInstallmentLoanAPIModule shopInstallmentLoanAPIModule=new ShopInstallmentLoanAPIModule();
    protected TimeDepositModule timeDepositModule = new TimeDepositModule();
    public static String status="";
    public static DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
    /**
     * Verify Loan Dash Board Page
     * @param amount
     * @throws Exception
     */
    public void verifyLoanDashboardPageWithoutVas(String amount,String payment) throws Exception {
        containsValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextInstallmentText)),propertyFileReader("NextInstallment","ShopInstallmentLoan")," text");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoanInstallmentAmount)),"₱"+decimalFormat.format(Double.parseDouble(amount))," Installment");
        containsValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoanDashBoardDescription)),propertyFileReader("LoanDashBoardDescription","ShopInstallmentLoan")," Description");
        if(payment.equalsIgnoreCase("PaymentDone")){
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPaymentDoneText)),propertyFileReader("FirstPaymentDone","ShopInstallmentLoan")," Text");
        }
        else{
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPaymentDoneText)),propertyFileReader("PaymentDone","ShopInstallmentLoan")," Text");
        }
        verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objIicon)," Loan information I icon");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseMyLoan)),propertyFileReader("IWantToCloseLoan","ShopInstallmentLoan")," Button");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPaymentRecordedText)),propertyFileReader("PaymentRecord","ShopInstallmentLoan")," Button");
        verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPaymentRecorded)," Payment Record");
    }
    /**
     * verify Loan Information Page
     * @param loanAmount
     * @param borrowDate
     * @param installmentTerm
     * @param monthlyPayment
     * @param dueDate
     * @throws Exception
     */
    public void verifyLoanInformationPage(String loanAmount,String borrowDate,String installmentTerm,String monthlyPayment,String dueDate) throws Exception {
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageHeader)),propertyFileReader("LoanInformation","ShopInstallmentLoan")," Text");
        String[] loanDetail={"₱"+loanAmount,borrowDate,installmentTerm+" months","₱"+monthlyPayment,dueDate};
        for(int detail=0;detail<5;detail++){
            if(detail==4){
                assertionValidation(getText(ShopInstallmentLoanPage.objLoanDetails(platform, propertyFileReader("LoanDetailWithoutVas" + detail, "ShopInstallmentLoan"))), propertyFileReader("LoanDetailWithoutVas" + detail, "ShopInstallmentLoan"), " detail");
                verifyElementPresent(ShopInstallmentLoanPage.objLoanDetailValue(platform,propertyFileReader("LoanDetailWithoutVas"+detail,"ShopInstallmentLoan")),getText(ShopInstallmentLoanPage.objLoanDetailValue(platform,propertyFileReader("LoanDetailWithoutVas"+detail,"ShopInstallmentLoan")))+" : Due Date");
            }
            else {

                assertionValidation(getText(ShopInstallmentLoanPage.objLoanDetails(platform, propertyFileReader("LoanDetailWithoutVas" + detail, "ShopInstallmentLoan"))), propertyFileReader("LoanDetailWithoutVas" + detail, "ShopInstallmentLoan"), " detail");
                containsValidation(getText(ShopInstallmentLoanPage.objLoanDetailValue(platform, propertyFileReader("LoanDetailWithoutVas" + detail, "ShopInstallmentLoan"))), loanDetail[detail], propertyFileReader("LoanDetailWithoutVas" + detail, "ShopInstallmentLoan"));
            }
        }
        assertionValidation(getText(ShopInstallmentLoanPage.objLoanDetails(platform,propertyFileReader("RatesAndFees","ShopInstallmentLoan"))),propertyFileReader("RatesAndFees","ShopInstallmentLoan")," Text");
        swipe("UP",1);
        for(int doc=0;doc<4;doc++){
            assertionValidation(getText(ShopInstallmentLoanPage.objLoanDetails(platform,propertyFileReader("LoanDocument"+doc,"ShopInstallmentLoan"))),propertyFileReader("LoanDocument"+doc,"ShopInstallmentLoan")," Document");
        }
    }
    /**
     * click On ALl Button and Download
     * @throws Exception
     */
    public void clickOnAllDocumentAndDownload() throws Exception {
        swipe("UP", 1);
        for (int doc = 0; doc < 4; doc++) {
            waitForElementToBePresent(ShopInstallmentLoanPage.objLoanDetails(platform, propertyFileReader("LoanDocument" + doc, "ShopInstallmentLoan")), 3, "Document");
            click(ShopInstallmentLoanPage.objLoanDetails(platform, propertyFileReader("LoanDocument" + doc, "ShopInstallmentLoan")), propertyFileReader("LoanDocument" + doc, "ShopInstallmentLoan"));
            if(propertyFileReader("LoanDocument" + doc, "ShopInstallmentLoan").equalsIgnoreCase("About PayHinga")){
                assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objPageHeader)).replaceAll("\\s"," "), propertyFileReader("PayHingaPageHeader", "ShopInstallmentLoan"), " Document");
                click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objGotitButton), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objGotitButton)));
                if(platform.equalsIgnoreCase("ios")&&verifyElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseCrossButton))){
                    click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseCrossButton)," Cross Icon");
                }
            }
            else {
                waitTime(3000);
                assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objPageHeader)), propertyFileReader("LoanDocument" + doc, "ShopInstallmentLoan"), " Document");
                click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objDownloadButton), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objDownloadButton)));
                if(platform.equalsIgnoreCase("ios")&&verifyElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseCrossButton))){
                    click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseCrossButton)," Cross Icon");
                }
            }
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTopLeftSideBackArrowButton), " Back Arrow Button");
        }
    }
    /**
     * verify Here Is Summary Page
     * @param totalAmount
     * @param downPayment
     * @param loanAmount
     * @param processingFee
     * @param term
     * @throws Exception
     */
    public void verifyHereIsSummaryOfYourLoanPage(String totalAmount, String downPayment,String loanAmount,String processingFee,String term) throws Exception {
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageHeader)),propertyFileReader("SummaryLoanPage","ShopInstallmentLoan")," Page Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objWhatYouWant)),propertyFileReader("WHatYouWant","ShopInstallmentLoan")," Text");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objMobilePhones)),propertyFileReader("MobilePhone","ShopInstallmentLoan")," Text");
        String[] values={"₱"+totalAmount,"₱"+downPayment,"₱"+loanAmount,"₱"+processingFee,term+" months"};
        for(int detail=0;detail<5;detail++){
            containsValidation(getText(ShopInstallmentLoanPage.objPurchaseDetails(platform,propertyFileReader("SummaryDetail"+detail,"ShopInstallmentLoan"))),propertyFileReader("SummaryDetail"+detail,"ShopInstallmentLoan")," Detail");
            assertionValidation(getText(ShopInstallmentLoanPage.objPurchaseDetailsValue(platform,propertyFileReader("SummaryDetail"+detail,"ShopInstallmentLoan"))),values[detail]," Detail");
        }
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objMonthlyAddOnText)),propertyFileReader("MonthlyAddOn","ShopInstallmentLoan")," Text");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objMonthlyAddOnDescription)).replaceAll("\\s"," "),propertyFileReader("MonthlyAddOnDescription","ShopInstallmentLoan")," Description");
    }
    /**
     * Common method to customer Care Icon Validation
     * @throws Exception
     */
    public void customerCareIconValidation() throws Exception {
        click(OnBoardingPage.objCustomerCareIcon, "Customer care icon");
        contactUsPageUI();
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopLeftSideBackArrowButton), "Back Button");
    }
    /**
     * Common method to transaction Details Loan Repayment
     * @param transactionAmount
     * @param from
     * @param to
     * @throws Exception
     */
    public void transactionDetailsLoanRepayment(String transactionAmount, String from, String to) throws Exception {
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("TransactionDetails", "QuickLoanWithVas"), "page title");
        if (platform.equalsIgnoreCase("ios")) {
            List<IOSElement> values = DriverManager.getAppiumDriver().findElements(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTransactionDetailsInfo));
            for (int transactionDetails = 1; transactionDetails <= values.size(); transactionDetails++) {
                assertionValidation(getText(QuickLoanWithVasPage.objTransactionDetailsInfo(platform, transactionDetails)), propertyFileReader("TransactionDetailsLoanRepayment" + transactionDetails, "QuickLoanWithVas"), ": transaction details");
            }
        } else {
            List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTransactionDetailsInfo));
            for (int transactionDetails = 1; transactionDetails <= values.size(); transactionDetails++) {
                assertionValidation(getText(QuickLoanWithVasPage.objTransactionDetailsInfo(platform, transactionDetails)), propertyFileReader("TransactionDetailsLoanRepayment" + transactionDetails, "QuickLoanWithVas"), ": transaction details");
            }
        }
        containsValidation(getText(QuickLoanWithVasPage.objTransactionInfo(platform, "Amount")), transactionAmount, ": amount");
        assertionValidation(getText(QuickLoanWithVasPage.objTransactionInfo(platform, "From")), from, ": From");
        containsValidation(getText(QuickLoanWithVasPage.objTransactionInfo(platform, "When")).substring(0, 11), ShopInstallmentLoanWithVasModule.todaysPHDate("dd MMM yyyy"), ": When");
        verifyElementPresent(QuickLoanWithVasPage.objTransactionInfo(platform, "Reference No."), getTextVal(QuickLoanWithVasPage.objTransactionInfo(platform, "Reference No."), ": Reference number"));
        screencapture();
    }
    //=================================Test Methods===================================
    /**
     * Verify if newly onboarded user can access the Shop Installment Loan from the main Dashboard
     */
    public void verifyIfNewlyOnboardedUserCanAccessTheShopInstallmentLoanFromTheMainDashboard_TDB_SIL_004(String password) throws Exception {
        HeaderChildNode("TDB-SIL-004, Verify if newly onboarded user can access the Shop Installment Loan from the main Dashboard");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        shopInstallmentLoanModule.loanTileValidation();
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objContactUsButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objContactUsButton)));
        contactUsPageUI();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back Button");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTile),getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTile)));
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objIDontSeeAnyTonikAgentButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objIDontSeeAnyTonikAgentButton)));
        shopInstallmentLoanModule.verifyQRHuntPage();
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objApplyForQuickLoanButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objApplyForQuickLoanButton)));
        if (verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCountMeInButton))) {
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCountMeInButton), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCountMeInButton), ": button"));
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objGotItBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objGotItBtn), ": button"));
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.ObjLetsGoBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.ObjLetsGoBtn), ": button"));
        }
        shopInstallmentLoanModule.howMuchDoYouNeedPage();
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objBackArrowButton),"Back Arrow Button");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTile),getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTile)));
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objGotchaButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objGotchaButton)));
        shopInstallmentLoanModule.verifyScanQRPage();
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objContactUsButton)," FAQ Button");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objQRHuntPageHeader)),propertyFileReader("QrHunt","ShopInstallmentLoan")," Header");
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objBackToQRScanningButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objBackToQRScanningButton)));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objScanTheQRCodePageHeader)),propertyFileReader("ScanTheQR","ShopInstallmentLoan"), " Page Header");
        logger.info("TDB-SIL-004, ShopInstallment Loan - Verify if newly onboarded user can access the Shop Installment Loan from the main Dashboard");
        extentLoggerPass("TDB-SIL-004", "ShopInstallment Loan - Verify if newly onboarded user can access the Shop Installment Loan from the main Dashboard");
    }
    /**
     * Verify if user can accept the loan offer without VAS from the Loan Summary screen
     * @param password
     * @throws Exception
     */
    public void verifyUserCanAcceptTheLoanOfferWithoutVASFromTheLoanSummaryScreen_TDB_SIL_010(String password) throws Exception {
        HeaderChildNode("TDB-SIL-010, ShopInstallment Loan - Verify if user can accept the loan offer without VAS from the Loan Summary screen");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        shopInstallmentLoanModule.loanTileValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTile),getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTile)));
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objGotchaButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objGotchaButton)));
        shopInstallmentLoanModule.verifyScanQRPage();
        if(platform.equalsIgnoreCase("ios")){
            closeiOSTonikApp();
        } else{
            closeAndroidTonikApp();
        }
        shopInstallmentLoanAPIModule.createSILLoan(propertyFileReader("SILMobileNumber", "TestDataNumbers")
                ,propertyFileReader("withoutVasFlag","ShopInstallmentLoan"),propertyFileReader("MonthlyInstallmentWithoutVas","ShopInstallmentLoan"));
        relaunchApp(platform);
        waitTime(3000);
        tonikLogin(password);
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("InProgress","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText2)),propertyFileReader("TapHere","ShopInstallmentLoan"),"Header");
        logger.info("TDB-SIL-010, ShopInstallment Loan - Verify if user can accept the loan offer without VAS from the Loan Summary screen");
        extentLoggerPass("TDB-SIL-010", "ShopInstallment Loan - Verify if user can accept the loan offer without VAS from the Loan Summary screen");
    }
    /**
     * Verify if the user can select field of work
     * @param password
     * @throws Exception
     */
    public void verifyUserCanSelectFieldOfWork_TDB_SIL_011(String password) throws Exception {
        HeaderChildNode("TDB-SIL-011, ShopInstallment Loan - Verify if the user can select field of work");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("InProgress","ShopInstallmentLoan"),"Header");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseApplicationButton)," Leave Application Button");
        shopInstallmentLoanModule.verifyLeavingSoSoonPage();
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopLeftSideBackArrowButton),"Back Arrow Button");
        shopInstallmentLoanModule.fieldOfWork(propertyFileReader("FieldOfWork", "ShopInstallmentLoan"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objWhatsYourJobRolePage),"What's your Job Role? Screen");
        logger.info("TDB-SIL-011, ShopInstallment Loan - Verify if the user can select field of work");
        extentLoggerPass("TDB-SIL-011", "ShopInstallment Loan - Verify if the user can select field of work");
    }
    /**
     * Verify if user can select the Occupation details
     * @param password
     * @throws Exception
     */
    public void verifyUserCanSelectTheOccupationDetails_TDB_SIL_012(String password) throws Exception {
        HeaderChildNode("TDB-SIL-012, ShopInstallment Loan - Verify if user can select the Occupation details");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("InProgress","ShopInstallmentLoan"),"Header");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        shopInstallmentLoanModule.selectFieldOfWork();
        shopInstallmentLoanModule.verifyLeavingSoonPageHeaders();
        shopInstallmentLoanModule.selectOccupation(propertyFileReader("Occupation", "ShopInstallmentLoan"));
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("IndustrySelectionScreen", "QuickLoanWithVas"), ": page title");        logger.info("TDB-SIL-012, ShopInstallment Loan - Verify if user can select the Occupation details");
        extentLoggerPass("TDB-SIL-012", "ShopInstallment Loan - Verify if user can select the Occupation details");
    }
    /**
     * Verify if user can select the Industry & the Sub-Industry options
     * @param password
     * @throws Exception
     */
    public void verifyUserCanSelectIndustryAndSubIndustryOptions_TDB_SIL_013(String password) throws Exception {
        HeaderChildNode("TDB-SIL-013, ShopInstallment Loan - Verify if user can select the Industry & the Sub-Industry options");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("InProgress","ShopInstallmentLoan"),"Header");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        shopInstallmentLoanModule.selectFieldOfWork();
        shopInstallmentLoanModule.selectOccupationAndClickOnNext(propertyFileReader("Occupation", "ShopInstallmentLoan"));
        shopInstallmentLoanModule.verifyLeavingSoonPageHeaders();
        shopInstallmentLoanModule.selectIndustry(propertyFileReader("Industry1", "ShopInstallmentLoan"), propertyFileReader("SubIndustry", "ShopInstallmentLoan"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("MaritalStatus", "QuickLoanWithVas"), ": page title");
        logger.info("TDB-SIL-013, ShopInstallment Loan - Verify if user can select the Industry & the Sub-Industry options");
        extentLoggerPass("TDB-SIL-013", "ShopInstallment Loan - Verify if user can select the Industry & the Sub-Industry options");
    }
    /**
     * Verify if user can select the Marital Status
     * @param password
     * @throws Exception
     */
    public void verifyUserCanSelectMaritalStatus_TDB_SIL_014(String password) throws Exception {
        HeaderChildNode("TDB-SIL-014, ShopInstallment Loan - Verify if user can select the Marital Status");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("InProgress","ShopInstallmentLoan"),"Header");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        shopInstallmentLoanModule.selectFieldOfWork();
        shopInstallmentLoanModule.selectOccupationAndClickOnNext(propertyFileReader("Occupation", "ShopInstallmentLoan"));
        shopInstallmentLoanModule.selectIndustryAndSubIndustary();
        shopInstallmentLoanModule.verifyLeavingSoonPageHeaders();
        shopInstallmentLoanModule.selectMaritalStatus(propertyFileReader("MaritalStatusOption2", "QuickLoanWithVas"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("SelectNoOfKids", "QuickLoanWithVas"), ": page title");
        logger.info("TDB-SIL-014, ShopInstallment Loan - Verify if user can select the Marital Status");
        extentLoggerPass("TDB-SIL-014", "ShopInstallment Loan - Verify if user can select the Marital Status");
    }
    /**
     * Verify if user can select Dependents
     * @param password
     * @throws Exception
     */
    public void verifyUserCanSelectDependents_TDB_SIL_015(String password) throws Exception {
        HeaderChildNode("TDB-SIL-015, ShopInstallment Loan - Verify if user can select Dependents");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("InProgress","ShopInstallmentLoan"),"Header");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        shopInstallmentLoanModule.selectFieldOfWork();
        shopInstallmentLoanModule.selectOccupationAndClickOnNext(propertyFileReader("Occupation", "ShopInstallmentLoan"));
        shopInstallmentLoanModule.selectIndustryAndSubIndustary();
        shopInstallmentLoanModule.selectMaritialStatus();
        shopInstallmentLoanModule.verifyLeavingSoonPageHeaders();
        shopInstallmentLoanModule.selectKidsOrDependents(propertyFileReader("DependentsOption1", "QuickLoanWithVas"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),2," Page Header");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("HighestEducationalAttainment", "QuickLoanWithVas"), ": page title");
        logger.info("TDB-SIL-015, ShopInstallment Loan - Verify if user can select Dependents");
        extentLoggerPass("TDB-SIL-015", "ShopInstallment Loan - Verify if user can select Dependents");
    }
    /**
     * Verify if user can select the Educational details
     * @param password
     * @throws Exception
     */
    public void verifyUserCanSelectTheEducationalDetails_TDB_SIL_016(String password) throws Exception {
        HeaderChildNode("TDB-SIL-016, ShopInstallment Loan - Verify if user can select the Educational details");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("InProgress","ShopInstallmentLoan"),"Header");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        shopInstallmentLoanModule.selectFieldOfWork();
        shopInstallmentLoanModule.selectOccupationAndClickOnNext(propertyFileReader("Occupation", "ShopInstallmentLoan"));
        shopInstallmentLoanModule.selectIndustryAndSubIndustary();
        shopInstallmentLoanModule.selectMaritialStatus();
        shopInstallmentLoanModule.selectDependentType();
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),2," Page Header");
        shopInstallmentLoanModule.verifyLeavingSoonPageHeaders();
        shopInstallmentLoanModule.selectHighestEducationalAttainment(propertyFileReader("EducationalAttainment1", "QuickLoanWithVas"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactReferencePage)),propertyFileReader("ContactReference","QuickLoanWithVas")," Page Header");
        logger.info("TDB-SIL-016, ShopInstallment Loan - Verify if user can select the Educational details");
        extentLoggerPass("TDB-SIL-016", "ShopInstallment Loan - Verify if user can select the Educational details");
    }
    /**
     * Verify if user can input the reference contact details
     * @param password
     * @throws Exception
     */
    public void verifyUserCanInputReferenceContactDetails_TDB_SIL_017(String password) throws Exception {
        HeaderChildNode("TDB-SIL-017, ShopInstallment Loan - Verify if user can input the reference contact details");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("InProgress","ShopInstallmentLoan"),"Header");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        shopInstallmentLoanModule.selectFieldOfWork();
        shopInstallmentLoanModule.selectOccupationAndClickOnNext(propertyFileReader("Occupation", "ShopInstallmentLoan"));
        shopInstallmentLoanModule.selectIndustryAndSubIndustary();
        shopInstallmentLoanModule.selectMaritialStatus();
        shopInstallmentLoanModule.selectDependentType();
        shopInstallmentLoanModule.selectEducationType();
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactReferencePage),5,"Contact reference page");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("ContactReference","QuickLoanWithVas"),": page title");
        shopInstallmentLoanModule.verifyLeavingSoonPageHeaders();
        shopInstallmentLoanModule.updateContactReference(propertyFileReader("InvalidReferenceNumber","QuickLoanWithVas"));
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInvalidMobileNumberMsg),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInvalidMobileNumberMsg),"Error message"))){
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInvalidMobileNumberMsg)),propertyFileReader("InvalidMobileNumberMsg","QuickLoanWithVas"),"Error message");
        }
        shopInstallmentLoanModule.updateContactReference(propertyFileReader("ContactMobileNumber","QuickLoanWithVas"));
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRegisteredMobileNumberErrorMsg),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRegisteredMobileNumberErrorMsg),"Error message"))){
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRegisteredMobileNumberErrorMsg)),propertyFileReader("RegisteredMobileNumberErrorMsg","QuickLoanWithVas"),"Error message");
        }
        shopInstallmentLoanModule.updateContactReference(propertyFileReader("ReferenceNumber1","QuickLoanWithVas"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHaveAnotherReference),5,"Have another Reference page");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("HaveAnotherReference","QuickLoanWithVas"),": page title");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)),propertyFileReader("HaveAnotherReferenceSubtitle","QuickLoanWithVas"),": page subtitle");
        shopInstallmentLoanModule.updateContactReference(propertyFileReader("ContactMobileNumber","QuickLoanWithVas"));
        if(verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader))){
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),": popup header"));
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubtext)),propertyFileReader("SameNumberPopSubTxt","QuickLoanWithVas"),": Popup subtext");
            verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),": popup button"));
        }
        shopInstallmentLoanModule.updateContactReference(propertyFileReader("ReferenceNumber1","QuickLoanWithVas"));
        if(verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader))){
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),": popup header"));
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubtext)),propertyFileReader("PopSubtext","QuickLoanWithVas"),": Popup subtext");
            verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),": popup button"));
        }
        shopInstallmentLoanModule.updateContactReference(propertyFileReader("ReferenceNumber2","QuickLoanWithVas"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIsThisYourCurrentAddPage),5,"is this your current address screen");
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"))) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("IsThisYourCurrentAddPage","QuickLoanWithVas"),": page title");
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)),propertyFileReader("IsThisYourCurrentAddPageSubtitle","QuickLoanWithVas"),": page subtitle");
            logger.info("TDB-SIL-017, ShopInstallment Loan - Verify if user can input the reference contact details");
            extentLoggerPass("TDB-SIL-017", "ShopInstallment Loan - Verify if user can input the reference contact details");
        }
    }
    /**
     * Verify if user can select the Current Living City
     * @throws Exception
     */
    public void verifyUserCanSelectCurrentLivingCity_TDB_SIL_018(String password) throws Exception {
        HeaderChildNode("TDB-SIL-018, ShopInstallment Loan - Verify if user can select the Current Living City");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("InProgress","ShopInstallmentLoan"),"Header");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        shopInstallmentLoanModule.selectFieldOfWork();
        shopInstallmentLoanModule.selectOccupationAndClickOnNext(propertyFileReader("Occupation", "ShopInstallmentLoan"));
        shopInstallmentLoanModule.selectIndustryAndSubIndustary();
        shopInstallmentLoanModule.selectMaritialStatus();
        shopInstallmentLoanModule.selectDependentType();
        shopInstallmentLoanModule.selectEducationType();
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactReferencePage),5,"Contact reference page");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        shopInstallmentLoanModule.updateContactReference(propertyFileReader("ReferenceNumber1","QuickLoanWithVas"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHaveAnotherReference),5,"Have another Reference Screen");
        shopInstallmentLoanModule.updateContactReference(propertyFileReader("ReferenceNumber2","QuickLoanWithVas"));
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
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objHowToFindYourZipCodeLinkText),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objHowToFindYourZipCodeLinkText)));
        verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objUrl), getTextVal(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objUrl), "URL"));
        if(platform.equalsIgnoreCase("ios")) {
            DriverManager.getAppiumDriver().activateApp("com.mobile.tonik");
        }
        else {
            DriverManager.getAppiumDriver().navigate().back();
        }
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("WhatIsYourCurrentAddress","ShopInstallmentLoan"),": page title");
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)),propertyFileReader("TellUs","ShopInstallmentLoan"),": page subtitle");
        shopInstallmentLoanModule.applyZipCode(propertyFileReader("ValidZipCode","ShopInstallmentLoan"));
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
        swipeAddress();
        swipeAddress();
        type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHouseUnitFlrNumberInputField),propertyFileReader("HouseStreetName","QuickLoanWithVas"),"House/Street Name");
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn),getTextVal(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn),": button"));
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
            logger.info("TDB-SIL-018, ShopInstallment Loan - Verify if user can select the Current Living City");
            extentLoggerPass("TDB-SIL-018", "ShopInstallment Loan - Verify if user can select the Current Living City");
        }
    }
    /**
     * Verify if user can input the Monthly income, Company name & TIN details
     * @throws Exception
     */
    public void verifyUserCanInputTheMonthlyIncomeCompanyNameAndTINDetails_TDB_SIL_019(String password) throws Exception {
        HeaderChildNode("TDB-SIL-019, ShopInstallment Loan - Verify if user can input the Monthly income, Company name & TIN details");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("InProgress","ShopInstallmentLoan"),"Header");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        shopInstallmentLoanModule.selectFieldOfWork();
        shopInstallmentLoanModule.selectOccupationAndClickOnNext(propertyFileReader("Occupation", "ShopInstallmentLoan"));
        shopInstallmentLoanModule.selectIndustryAndSubIndustary();
        shopInstallmentLoanModule.selectMaritialStatus();
        shopInstallmentLoanModule.selectDependentType();
        shopInstallmentLoanModule.selectEducationType();
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactReferencePage),5,"Contact reference page");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        shopInstallmentLoanModule.updateContactReference(propertyFileReader("ReferenceNumber1","QuickLoanWithVas"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHaveAnotherReference),5,"Have another Reference Screen");
        shopInstallmentLoanModule.updateContactReference(propertyFileReader("ReferenceNumber2","QuickLoanWithVas"));
        shopInstallmentLoanModule.isThisYourCurrentAddressConfirmation();
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
        shopInstallmentLoanModule.verifyLeavingSoonPageHeaders();
        if(platform.equalsIgnoreCase("ios")){
            clearField(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyIncomeField), "Income input field");
        }
        type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyIncomeField),propertyFileReader("ValidIncome","ShopInstallmentLoan"),"Income input field");
        if(platform.equalsIgnoreCase("ios")){
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objKeyboardDoneBtn), "Keyboard Done Button");
        }
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn), getTextVal(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn), ": button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInputCompanyScreen),5,"Where do you work, luv? Screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("InputCompanyScreen", "QuickLoanWithVas"), ": page title");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("InputCompanyScreenSubtitle", "QuickLoanWithVas"), ": page subtitle");
        shopInstallmentLoanModule.verifyLeavingSoonPageHeaders();
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
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
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn),getTextVal(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn),": button"));
        if(verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInvalidTINMsg))){
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInvalidTINMsg)), propertyFileReader("InvalidTINMsg", "QuickLoanWithVas"), ": page title");
        }
        clearField(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTINInputField)," TIN TextField");
        type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTINInputField),propertyFileReader("ValidTIN","QuickLoanWithVas"),"TIN Input field");
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn),getTextVal(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn),": button"));
        waitTime(2000);
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"))) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("AnotherWayToReachYou", "QuickLoanWithVas"), ": page title");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("AnotherWayToReachYouSubtitle", "QuickLoanWithVas"), ": page subtitle");
            logger.info("TDB-SIL-019, ShopInstallment Loan - Verify if user can input the Monthly income, Company name & TIN details");
            extentLoggerPass("TDB-SIL-019", "ShopInstallment Loan - Verify if user can input the Monthly income, Company name & TIN details");
        }
    }
    /**
     * Verify if user can input Secondary Contact details
     * @throws Exception
     */
    public void verifyUserCanInputSecondaryContactDetails_TDB_SIL_020(String password) throws Exception {
        HeaderChildNode("TDB-SIL-020, ShopInstallment Loan - Verify if user can input Secondary Contact details");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("InProgress","ShopInstallmentLoan"),"Header");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        shopInstallmentLoanModule.selectFieldOfWork();
        shopInstallmentLoanModule.selectOccupationAndClickOnNext(propertyFileReader("Occupation", "ShopInstallmentLoan"));
        shopInstallmentLoanModule.selectIndustryAndSubIndustary();
        shopInstallmentLoanModule.selectMaritialStatus();
        shopInstallmentLoanModule.selectDependentType();
        shopInstallmentLoanModule.selectEducationType();
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactReferencePage),5,"Contact reference page");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        shopInstallmentLoanModule.updateContactReference(propertyFileReader("ReferenceNumber1","QuickLoanWithVas"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHaveAnotherReference),5,"Have another Reference Screen");
        shopInstallmentLoanModule.updateContactReference(propertyFileReader("ReferenceNumber2","QuickLoanWithVas"));
        shopInstallmentLoanModule.isThisYourCurrentAddressConfirmation();
        shopInstallmentLoanModule.inputIncomeCompanyAndTINDetails(propertyFileReader("ValidTIN","QuickLoanWithVas"),propertyFileReader("ValidIncome","ShopInstallmentLoan"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAnotherWayToReachYou),5,"Another way to reach you? Screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("AnotherWayToReachYou", "QuickLoanWithVas"), ": page title");
        waitTime(2000);// Wait required to click on back button
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTINInputField),5,"TIN Screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("TINScreen", "QuickLoanWithVas"), ": page title");
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn),getTextVal(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextBtn),": button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAnotherWayToReachYou),5,"Another way to reach you? Screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("AnotherWayToReachYou", "QuickLoanWithVas"), ": page title");
        shopInstallmentLoanModule.inputMobileNumberAndNext(propertyFileReader("SILMobileNumber","TestDataNumbers").replaceAll("63",""));
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),"Popup header"))){
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubtext)),propertyFileReader("SameOnboardedNumberPopup","QuickLoanWithVas"),"Popup header");
            verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),": button"));
        }
        shopInstallmentLoanModule.inputMobileNumberAndNext(propertyFileReader("SILReferenceContact","TestDataNumbers"));
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupHeader),"Popup header"))){
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubtext)),propertyFileReader("SameAsReferenceNumberPopup","QuickLoanWithVas"),"Popup header");
            verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupButton),": button"));
        }
        shopInstallmentLoanModule.inputMobileNumberAndNext(propertyFileReader("SecondaryContact","QuickLoanWithVas"));
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
            logger.info("TDB-SIL-020, ShopInstallment Loan - Verify if user can input Secondary Contact details");
            extentLoggerPass("TDB-SIL-020", "ShopInstallment Loan - Verify if user can input Secondary Contact details");
        }
    }
    /**
     * Verify if user can accept the Terms & Conditions
     * @throws Exception
     */
    public void verifyUserCanAcceptTermsAndConditions_TDB_SIL_021(String password,String mobileNumber) throws Exception {
        HeaderChildNode("TDB-SIL-021, ShopInstallment Loan - Verify if user can accept the Terms & Conditions");
        shopInstallmentLoanModule.updateUTCTime(mobileNumber);
        tonikLogin(password);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        shopInstallmentLoanModule.loanSegment2NavigationForSIL(propertyFileReader("ValidTIN","QuickLoanWithVas"),propertyFileReader("ValidIncome","ShopInstallmentLoan"), propertyFileReader("ReferenceNumber1", "QuickLoanWithVas"),propertyFileReader("ReferenceNumber2", "QuickLoanWithVas"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHoldOnPopupTitle),10,"Hold On Babe Page");
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHoldOnPopupTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHoldOnPopupTitle),": page"))){
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHoldOnPopupTitle)),propertyFileReader("HoldOnBabeScreen","ShopInstallmentLoan"),"Page Title");
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHoldOnPopupSubTitle)),propertyFileReader("HoldOnBabeScreenSubHeader","ShopInstallmentLoan"),"Page Subtitle");
            waitForElementToBePresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objIAcceptTheOffer),20,"I accept the offer screen button");
            verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objIAcceptTheOffer),"I accept the offer screen button");
            logger.info("TDB-SIL-021, ShopInstallment Loan - Verify if user can accept the Terms & Conditions");
            extentLoggerPass("TDB-SIL-021", "ShopInstallment Loan - Verify if user can accept the Terms & Conditions");
        }
    }
    public void verifyLoanTileIfTheUserIsPreApproved_TDB_SIL_027(String password) throws Exception {
        HeaderChildNode("TDB-SIL-027, ShopInstallment Loan  - Verify the Loan tile if the user is pre-approved");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        if(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText1)).equalsIgnoreCase(propertyFileReader("InProgress", "ShopInstallmentLoan"))) {
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)));
            shopInstallmentLoanModule.loanSegment2NavigationForSIL(propertyFileReader("ValidTIN","QuickLoanWithVas"),propertyFileReader("ValidIncome","ShopInstallmentLoan"), propertyFileReader("ReferenceNumber1", "QuickLoanWithVas"),propertyFileReader("ReferenceNumber2", "QuickLoanWithVas"));
            waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objProfileName),5,"Profile Name");
            waitTime(70000);
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
            logger.info("TDB-SIL-027, ShopInstallment Loan  - Verify the Loan tile if the user is pre-approved");
            extentLoggerPass("TDB-SIL-027", "TDB-SIL-027, ShopInstallment Loan  - Verify the Loan tile if the user is pre-approved");
        }
    }
    /**
     * Verify if user can accept the loan offer from the Loan Offer screen
     * @param password
     * @throws Exception
     */
    public void VerifyUserCanAcceptTheLoanOfferFromLoanOfferScreen_TDB_SIL_028(String password) throws Exception {
        HeaderChildNode("TDB-SIL-028, ShopInstallment Loan  - Verify if user can accept the loan offer from the Loan Offer screen");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile));
        if(verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile)))) {
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText1)), propertyFileReader("Approved", "ShopInstallmentLoan"), "Header");
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)));
            shopInstallmentLoanModule.verifyLoanApprovedPage(decimalFormat.format(Double.parseDouble(propertyFileReader("LoanableAmount","ShopInstallmentLoan"))),decimalFormat.format(Double.parseDouble(propertyFileReader("MonthlyInstallmentWithoutVas","ShopInstallmentLoan"))),propertyFileReader("Tenure","ShopInstallmentLoan"));
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopLeftSideBackArrowButton)," Back Arrow Button");
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objProfileName)," Profile Name In Dash Board");
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)));
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objIAcceptTheOffer),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objIAcceptTheOffer)));
            waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,(QuickLoanWithVasPage.objMainTitleTxt)), 5, "Tell us your pay day page");
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("tellUsPageHeader", "QuickLoanWithVas"), "Page Header", "contains");
            logger.info("TDB-SIL-028, ShopInstallment Loan  - Verify if user can accept the loan offer from the Loan Offer screen");
            extentLoggerPass("TDB-SIL-028", "TDB-SIL-028, ShopInstallment Loan  - Verify if user can accept the loan offer from the Loan Offer screen");
        }
    }
    /**
     * Verify if user can select the Pay Day & confirm Monthly Installment Summary
     * @param password
     * @throws Exception
     */
    public void verifyUserCanSelectPayDayAndConfirmMonthlyInstallmentSummary_TDB_SIL_029(String password) throws Exception {
        HeaderChildNode("TDB-SIL-029, ShopInstallment Loan  - Verify if user can select the Pay Day & confirm Monthly Installment Summary");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTile));
        if (verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTile), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTile)))) {
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText1)), propertyFileReader("Approved", "ShopInstallmentLoan"), "Header");
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader)));
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objYouGotApprovedPageHeader)), propertyFileReader("LoanApprovedPage", "ShopInstallmentLoan"), " Page Header");
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objIAcceptTheOffer), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objIAcceptTheOffer)));
            waitTime(2000);
            shopInstallmentLoanModule.tellUsYourPayDayPageValidation();
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTopLeftSideBackArrowButton), " Back Arrow Button");
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objYouGotApprovedPageHeader)), propertyFileReader("LoanApprovedPage", "ShopInstallmentLoan"), " Page Header");
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objIAcceptTheOffer), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objIAcceptTheOffer)));
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFifthOfTheMonth), " Pays Day");
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNextBtn), "Next Button");
            waitTime(2000);//StaleElement
            shopInstallmentLoanModule.verifyMonthlyInstallmentSummaryPage(propertyFileReader("Tenure","ShopInstallmentLoan"),decimalFormat.format(Double.parseDouble(propertyFileReader("MonthlyInstallmentWithoutVas","ShopInstallmentLoan"))));
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTopLeftSideBackArrowButton), " Back Arrow Button");
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("tellUsPageHeader", "QuickLoanWithVas"), "Page Header", "contains");
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNextBtn), "Next Button");
            waitForElementToBePresent(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objConfirmButton),5,"Confirm button");
            verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objConfirmButton), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objConfirmButton)));
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), "Page"));
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPageSubHeader)).trim(), propertyFileReader("holdOnTxt", "QuickLoanWithVas"), "Page Sub header");
            logger.info("TDB-SIL-029, ShopInstallment Loan  - Verify if user can select the Pay Day & confirm Monthly Installment Summary");
            extentLoggerPass("TDB-SIL-029", "TDB-SIL-029, ShopInstallment Loan  - Verify if user can select the Pay Day & confirm Monthly Installment Summary");
        }
    }
    /**
     * Verify if user can sign all the Loan documents & disburse the loan successfully
     * @param password
     * @throws Exception
     */
    public void verifyUserCanSignAllTheLoanDocumentsAndDisburseTheLoanSuccessfully_TDB_SIL_031(String password) throws Exception {
        HeaderChildNode("TDB-SIL-031, ShopInstallment Loan  - Verify if user can sign all the Loan documents & disburse the loan successfully");
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
            shopInstallmentLoanModule.signedSealedDeliveredPageValidation();
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignPromissoryNoteBtn), "Sign Promissory Note Button");
            waitTime(4000);//Hard wait required for execution
            shopInstallmentLoanModule.promissoryNotePageValidation();
            waitTime(3000);//Hard wait required for execution
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
            shopInstallmentLoanModule.signedSealedDeliveredPageValidation();
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignPromissoryNoteBtn), "Sign Promissory Note Button");
            waitTime(10000);//Hard wait required for execution
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign");
            shopInstallmentLoanModule.yourSignatureRequiredPageValidation();
            waitTime(3000);//Hard wait required for execution
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
            shopInstallmentLoanModule.signedSealedDeliveredPageValidation();
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
            shopInstallmentLoanModule.disclosureStatementPageValidation();
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
            shopInstallmentLoanModule.yourSignatureRequiredPageValidation();
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
            shopInstallmentLoanModule.amortizationScheduleValidation();
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
            shopInstallmentLoanModule.amortizationScheduleValidation();
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
            logger.info("TDB-SIL-031, ShopInstallment Loan - Verify if user can sign all the Loan documents & disburse the loan successfully");
            extentLoggerPass("TDB-SIL-031", "TDB-SIL-031, ShopInstallment Loan  - Verify if user can sign all the Loan documents & disburse the loan successfully");
        }
    }
    /**
     * Verify if the user can view and access Time to claim your item screen
     * @param password
     * @throws Exception
     */
    public void verifyUserCanViewAndAccessTimeToClaimYourItemScreen_TDB_SIL_032(String password) throws Exception {
        HeaderChildNode("TDB-SIL-032, ShopInstallment Loan - Verify if the user can view and access Time to claim your item screen");
        tonikLogin(password);
        if (getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText1)).equalsIgnoreCase(propertyFileReader("Approved", "ShopInstallmentLoan"))) {
            shopInstallmentLoanModule.navigateFromApprovedToTimeToClaimPage();
        }
        else {
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader)));
        }
        shopInstallmentLoanModule.verifyTimeToClaimYourItemPage(propertyFileReader("LoanableAmount","ShopInstallmentLoan"),propertyFileReader("MonthlyInstallmentWithoutVas","ShopInstallmentLoan"),"WithoutVas");
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objReadyToPayButtonText),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objReadyToPayButtonText)));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objAlreadyAtTheCahierPageHeader)).replaceAll("\\s"," "),propertyFileReader("AlreadyAtTheCashier","ShopInstallmentLoan")," Page Header");
        logger.info("TDB-SIL-032, ShopInstallment Loan - Verify if the user can view and access Time to claim your item screen");
        extentLoggerPass("TDB-SIL-032", "ShopInstallment Loan - Verify if the user can view and access Time to claim your item screen");
    }
    public void verifyUserCanRequestConfirmationCodeFromTheCashier_TDB_SIL_033(String password) throws Exception {
        HeaderChildNode("TDB-SIL-033, ShopInstallment Loan - Verify if the user can request for confirmation code from the cashier");
        tonikLogin(password);
        if (getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText1)).equalsIgnoreCase(propertyFileReader("Approved", "ShopInstallmentLoan"))) {
            shopInstallmentLoanModule.navigateFromApprovedToTimeToClaimPage();
        }
        else {
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader)));
        }
        shopInstallmentLoanModule.verifyTimeToClaimYourItemPage(propertyFileReader("LoanableAmount","ShopInstallmentLoan"),propertyFileReader("MonthlyInstallmentWithoutVas","ShopInstallmentLoan"),"WithoutVas");
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objReadyToPayButtonText),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objReadyToPayButtonText)));
        shopInstallmentLoanModule.verifyAlreadyAtTheCashierPage();
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objBackButtonInAlreadyAtCashierPage),"Back Arrow Button");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTimeToClaimYourItemPageHeader)),propertyFileReader("TimeToClaim","ShopInstallmentLoan")," Page Header");
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objReadyToPayButtonText),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objReadyToPayButtonText)));
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objSendConfirmationCodeButtonText),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objSendConfirmationCodeButtonText)));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPayTogetYourConfirmationCodePageHeader)),propertyFileReader("PayToGetConfirmationCode","ShopInstallmentLoan")," Page Header");
        logger.info("TDB-SIL-033, ShopInstallment Loan - Verify if the user can request for confirmation code from the cashier");
        extentLoggerPass("TDB-SIL-033", "ShopInstallment Loan - Verify if the user can request for confirmation code from the cashier");
    }
    /**
     * Verify if the user can input valid 6-digit code
     * @param password
     * @throws Exception
     */
    public void verifyUserCanInputConfirmationCodeValidation_TDB_SIL_034(String password) throws Exception {
        HeaderChildNode("TDB-SIL-034, ShopInstallment Loan - Verify if the user can input valid 6-digit code");
        tonikLogin(password);
        String tsaAmountBeforeLoanDisburs = getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTSAAmount));
        if (getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText1)).equalsIgnoreCase(propertyFileReader("Approved", "ShopInstallmentLoan"))) {
            shopInstallmentLoanModule.navigateFromApprovedToTimeToClaimPage();
        }
        else {
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader)));
        }
        shopInstallmentLoanModule.loanDisburs();
        waitForElementToBePresent(LoginPage.getByOSType(platform,LoginPage.objProfileName),5,"Profile name");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTSAAmount)),tsaAmountBeforeLoanDisburs,"TSA AMount AFter Loan Disburs");
        verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objProfileName),getTextVal(LoginPage.getByOSType(platform,LoginPage.objProfileName)," : Profile Name"));
        logger.info("TDB-SIL-034, ShopInstallment Loan - Verify if the user can input valid 6-digit code");
        extentLoggerPass("TDB-SIL-034", "ShopInstallment Loan - Verify if the user can input valid 6-digit code");
    }
    /**
     * Verify if the Loans tile status is changed after the successful loan disbursal
     * @param password
     * @throws Exception
     */
    public void verifyTheLoansTileStatusIsChangedAfterTheSuccessfulLoanDisbursal_TDB_SIL_035(String password) throws Exception {
        HeaderChildNode("TDB-SIL-035, ShopInstallment Loan - Verify if the Loans tile status is changed after the successful loan disbursal");
        tonikLogin(password);
        if((getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText1))).equalsIgnoreCase(propertyFileReader("MeetTheCashier", "ShopInstallmentLoan"))) {
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText1)), propertyFileReader("MeetTheCashier", "ShopInstallmentLoan"), " Page Header");
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader)));
            shopInstallmentLoanModule.loanDisburs();
        }
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("yes","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText2)),"₱"+decimalFormat.format(Double.parseDouble(propertyFileReader("LoanableAmount","ShopInstallmentLoan")))+" payment was just sent to "+propertyFileReader("StoreName","ShopInstallmentLoan")," Text");
        logger.info("TDB-SIL-035, ShopInstallment Loan - Verify if the Loans tile status is changed after the successful loan disbursal");
        extentLoggerPass("TDB-SIL-035", "ShopInstallment Loan - Verify if the Loans tile status is changed after the successful loan disbursal");
    }
    /**
     * Verify the user can view the Loan Dashboard screen
     * @param password
     * @throws Exception
     */
    public void verifyUserCanViewTheLoanDashboardScreen_TDB_SIL_037(String password) throws Exception {
        HeaderChildNode("TDB-SIL-037, ShopInstallment Loan - Verify the user can view the Loan Dashboard screen");
        tonikLogin(password);
        if(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText1)).equalsIgnoreCase(propertyFileReader("yes", "ShopInstallmentLoan"))) {
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText1)), propertyFileReader("yes", "ShopInstallmentLoan"), " Page Header");
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansHeader)));
            verifyLoanDashboardPageWithoutVas(propertyFileReader("MonthlyInstallmentWithoutVas","ShopInstallmentLoan"),"");
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objContactUsButton),"Contact Us Button");
            verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objGetInTouchWithCustomerCarePage),"Get in touch page");
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTopLeftSideBackArrowButton), " Back Arrow Button");
            containsValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objNextInstallmentText)),propertyFileReader("NextInstallment","ShopInstallmentLoan")," text");
            click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTopLeftSideBackArrowButton), " Back Arrow Button");
            verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objProfileName),"Profile name In DashBoard");
            logger.info("TDB-SIL-037, ShopInstallment Loan - Verify the user can view the Loan Dashboard screen");
            extentLoggerPass("TDB-SIL-037", "ShopInstallment Loan - Verify the user can view the Loan Dashboard screen");
        }
    }
    /**
     * Verify if user can access the Loan Information screen
     * @throws Exception
     */
    public void verifyUserCanAccessTheLoanInformationScreen_TDB_SIL_038(String password) throws Exception {
        HeaderChildNode("TDB-SIL-038, Verify if user can access the Loan Information screen");
        tonikLogin(password);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objIicon),"Loan Information I icon");
        verifyLoanInformationPage(decimalFormat.format(Double.parseDouble(propertyFileReader("LoanableAmount","ShopInstallmentLoan"))), shopInstallmentLoanModule.todaysPHDate("dd MMMM yyyy"),propertyFileReader("Tenure","ShopInstallmentLoan"),decimalFormat.format(Double.parseDouble(propertyFileReader("MonthlyInstallmentWithoutVas","ShopInstallmentLoan"))),
                shopInstallmentLoanModule.todaysPHDate("dd MMMM yyyy").substring(0,2));
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextInstallmentTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextInstallmentTxt), "Text"))) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseLoanButton)), propertyFileReader("iWantToCloseLoanBtn", "QuickLoanWithVas"), "Button");
            logger.info("TDB-SIL-038, ShopInstallment Loan - Verify if user can access the Loan Information screen");
            extentLoggerPass("TDB-SIL-038", "ShopInstallment Loan - Verify if user can access the Loan Information screen");
        }
    }
    /**
     * Verify if user can view the Rates and fees details
     * @throws Exception
     */
    public void verifyUserCanViewRatesFeesDetails_TDB_SIL_039(String password) throws Exception {
        HeaderChildNode("TDB-SIL-039, Verify if user can view the Rates and fees details");
        tonikLogin(password);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoButton), 10, "Information Button");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoButton), "Information Button");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoScreeRateAndFees), "Rates and fees");
        shopInstallmentLoanModule.verifyRatesAndFeePage("WithoutVas");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt)))) {
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageHeader)),propertyFileReader("LoanInformation","ShopInstallmentLoan")," Text");
            logger.info("TDB-SIL-039, Verify if user can view the Rates and fees details validated");
            extentLoggerPass("TDB-SIL-039", "Verify if user can view the Rates and fees details validated");
        }
    }
    /**
     * Verify if user can view & download all the Loan documents
     * @throws Exception
     */
    public void verifyUserCanViewAndDownloadAllTheLoanDocuments_TDB_SIL_040(String password) throws Exception {
        HeaderChildNode("TDB-SIL-040, Verify if user can view & download all the Loan documents");
        tonikLogin(password);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoButton),  "Information Icon");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageHeader)),propertyFileReader("LoanInformation","ShopInstallmentLoan")," Text");
        clickOnAllDocumentAndDownload();
        if(verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageHeader), getTextVal(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageHeader), "Text"))) {
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageHeader)),propertyFileReader("LoanInformation","ShopInstallmentLoan")," Text");
            logger.info("TDB-SIL-040, Verify if user can view & download all the Loan documents");
            extentLoggerPass("TDB-SIL-040", "Verify if user can view & download all the Loan documents");
        }
    }
    /**
     * Verify if user can view the Loan Payment screen
     * @param dueDate
     * @throws Exception
     */
    public void verifyUserCanViewLoanPaymentScreen_TDB_SIL_043(String password,int dueDate) throws Exception {
        HeaderChildNode("TDB-SIL-043, ShopInstallment Loan - Verify if user can view the Loan Payment screen");
        String userId = shopInstallmentLoanModule.getUserId(propertyFileReader("SILMobileNumber", "TestDataNumbers"));
        String loanAccountNumber = shopInstallmentLoanModule.getLoanAccountNumber(userId);
        String date = shopInstallmentLoanModule.returnDate(dueDate, "yyyy-MM-dd");
        shopInstallmentLoanModule.updateDueDateInLoanInstallmentDetails(date, loanAccountNumber);
        logger.info("Due date modified in Loan Installment details: " + date);
        extentLogger("", "Due date modified in Loan Installment details: " + date);
        shopInstallmentLoanModule.updateRepaymentDateInLoanRepaymentSchedule(date, loanAccountNumber, "1");
        tonikLogin(password);
        String balance = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBalanceOnScreen));
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayNowBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayNowBtn), "Button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanPaymentScreen), 5, "Loan payment screen");
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageTitle), "Screen"))) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageTitle)), propertyFileReader("LoanPaymentScreen", "QuickLoanWithVas"), "Screen");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAmountDueField)), propertyFileReader("AmountDueField", "QuickLoanWithVas"), "field");
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAmountDue)), decimalFormat.format(Double.parseDouble(propertyFileReader("MonthlyInstallmentWithoutVas", "ShopInstallmentLoan"))), "Amount due");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLateFeesField)), propertyFileReader("LateFeesField", "QuickLoanWithVas"), "field");
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLateFee)).replaceAll(",", ""), propertyFileReader("LateFee", "QuickLoanWithVas"), "Late fee");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAmountToBePaidField)), propertyFileReader("AmountToBePaidField", "QuickLoanWithVas"), "field");
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAmountToBePaid)), decimalFormat.format(Double.parseDouble(propertyFileReader("MonthlyInstallmentWithoutVas", "ShopInstallmentLoan"))), "Amount to be paid");
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
            logger.info("TDB-SIL-043, ShopInstallment Loan - Verify if user can view the Loan Payment screen");
            extentLoggerPass("TDB-SIL-043", "ShopInstallment Loan - Verify if user can view the Loan Payment screen");
        }
    }
    /**
     * Verify if user can make the payment via Tonik Account with sufficient balance
     * @param dueDate
     * @throws Exception
     */
    public void verifyUserCanMakePaymentViaTonikAccount_TDB_SIL_045(String password,int dueDate) throws Exception {
        HeaderChildNode("TDB_SIL_045, Quick Loan With VAS - Verify if user can make the payment via Tonik Account with sufficient balance");
        String userId = shopInstallmentLoanModule.getUserId(propertyFileReader("SILMobileNumber", "TestDataNumbers"));
        String loanAccountNumber = shopInstallmentLoanModule.getLoanAccountNumber(userId);
        String date = shopInstallmentLoanModule.returnDate(dueDate, "yyyy-MM-dd");
        shopInstallmentLoanModule.updateDueDateInLoanInstallmentDetails(date, loanAccountNumber);
        logger.info("Due date modified in Loan Installment details: " + date);
        extentLogger("", "Due date modified in Loan Installment details: " + date);
        shopInstallmentLoanModule.updateRepaymentDateInLoanRepaymentSchedule(date, loanAccountNumber, "1");
        tonikLogin(password);
        String sBalance = SendMoneyModule.sBalance();
        float sIntBalance= Float.parseFloat(sBalance);
        if(sIntBalance<Integer.parseInt(propertyFileReader("LoanableAmount","ShopInstallmentLoan"))){
            loanCommonMethods.topUp(propertyFileReader("LoanableAmount","ShopInstallmentLoan"));
        }
        shopInstallmentLoanModule.tsaAmountInDashboard = getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTSAAmount)).substring(1).replaceAll(",","");
        String balance = getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceOnScreen));
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPayNowBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPayNowBtn), "Button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanPaymentScreen), 5, "Loan payment screen");
        String amount = getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAmountToBePaid)).substring(1).replaceAll(",","");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objConfirmPaymentBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objConfirmPaymentBtn),"Button"));
        waitTime(4000);
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanPaymentReceivedScreen)),propertyFileReader("LoanPaymentReceived","QuickLoanWithVas"),"Screen");
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanPaymentReceivedInfo)),decimalFormat.format(Double.parseDouble(amount)),"Info");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objViewTransactionLink),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objViewTransactionLink),"Link"));
        transactionDetailsLoanRepayment(decimalFormat.format(Double.parseDouble(amount)),shopInstallmentLoanModule.getAccountNumber(propertyFileReader("SILMobileNumber", "TestDataNumbers")),"");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashboard),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashboard),"button"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYourLoanAccount)),propertyFileReader("YourLoanAccount","QuickLoanWithVas"),"Loan text");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanBlueTick),"Loan blue tick");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanTileAfter1stRepayment)),propertyFileReader("LoanTileAfter1stRepayment","QuickLoanWithVas"),"Loan tile");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextPaymentDate),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextPaymentDate),""));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanTileMsg)),propertyFileReader("LoanTileMsg","QuickLoanWithVas"),"Loan tile");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanTileAfter1stRepayment),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanTileAfter1stRepayment),"Tile"));
        verifyLoanDashboardPageWithoutVas(amount,"PaymentDone");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
        loanCommonMethods.iWillDoItLaterPopup();
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTSAAmount)),"₱"+decimalFormat.format(Double.parseDouble(shopInstallmentLoanModule.tsaAmountInDashboard)-Double.parseDouble(amount)),"TSA Amount After Payment Done");
        shopInstallmentLoanModule.verifyTransactionPageAfterPayment(decimalFormat.format(Double.parseDouble(amount)),propertyFileReader("LoanRepayment","ShopInstallmentLoan"),propertyFileReader("Shopinstallmentloan","ShopInstallmentLoan"));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
        waitTime(2000);
        if(verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageHeader),getTextVal(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageHeader),": title"))) {
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageHeader)), propertyFileReader("AccountHistory", "QuickLoanWithVas"), ": page title");
            logger.info("TDB_SIL_045, Quick Loan With VAS - Verify if user can make the payment via Tonik Account with sufficient balance");
            extentLoggerPass("TDB_SIL_045", "TDB_SIL_045, Quick Loan With VAS - Verify if user can make the payment via Tonik Account with sufficient balance");
        }
    }
    /**
     * Verify the Loans tile status after the full loan repayment
     * @throws Exception
     */
    public void verifyLoansTileStatusAfterTheFullLoanRepaymentAndReApllySIL_TDB_SILV_055_057(String password) throws Exception {
        HeaderChildNode("TDB-SIL-055 TDB-SIL-057, Verify the Loans tile status after the full loan repayment And Reapply the SIL");
        tonikLogin(password);
        if(verifyElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)) || verifyElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objYourLoanAccountText))){
            if(verifyElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objYourLoanAccountText))){
                click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objYourLoanAccountText),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objYourLoanAccountText)));
            }
            else {
                assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("yes","ShopInstallmentLoan")," Status");
                click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
            }
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseLoanButton), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseLoanButton),"Button"));
            if(verifyElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopUpMyAccountButton))){
                click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopUpMyAccountButton)," Topup My Account");
                waitTime(2000);
                timeDepositModule.topupViaGCash(propertyFileReader("TopupAmount","ShopInstallmentLoan"));
                if(verifyElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)) || verifyElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objYourLoanAccountText))) {
                    if (verifyElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objYourLoanAccountText))) {
                        click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objYourLoanAccountText), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objYourLoanAccountText)));
                    } else {
                        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTileSubText1)), propertyFileReader("yes", "ShopInstallmentLoan"), " Status");
                        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), "Loans Tile");
                    }
                    click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseMyLoan),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseMyLoan)));

                }
            }
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseMyLoanButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseMyLoanButton)));
            assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objDropTheMic)),propertyFileReader("DropTheMic","ShopInstallmentLoan")," Page header");
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objBackToDashboard),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objBackToDashboard)));
        }
        else{
            verticalSwipeTillElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile));
        }
        verticalSwipeTillElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoanTileHeader)),propertyFileReader("FastCash","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objFindTheRightLoan)),propertyFileReader("FindTheRightLoan","ShopInstallmentLoan"),"Header");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        shopInstallmentLoanModule.loanTileValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTile),getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTile)));
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objGotchaButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objGotchaButton)));
        shopInstallmentLoanModule.verifyScanQRPage();
        if(platform.equalsIgnoreCase("ios")){
            closeiOSTonikApp();
        } else{
            closeAndroidTonikApp();
        }
        shopInstallmentLoanAPIModule.createSILLoan(propertyFileReader("SILMobileNumber", "TestDataNumbers"),propertyFileReader("withoutVasFlag","ShopInstallmentLoan"),propertyFileReader("MonthlyInstallmentWithoutVas","ShopInstallmentLoan"));
        relaunchApp(platform);
        waitTime(3000);
        tonikLogin(password);
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("InProgress","ShopInstallmentLoan"),"Header");
        logger.info("TDB-SIL-055 TDB-SIL-057, Verify the Loans tile status after the full loan repayment And Reapply the SIL");
        extentLoggerPass("TDB-SIL-055 TDB-SIL-057", "TDB-SIL-055 TDB-SIL-057, Verify the Loans tile status after the full loan repayment And Reapply the SIL");
    }
    /**
     * Verify the Loans tile status if BKYC user is not upgraded to SKYC
     * @param password
     * @throws Exception
     */
    public void verifyLoansTileStatusIfBKYCUserIsNotUpgradedToSKYC_TDB_SIL_059(String password) throws Exception {
        HeaderChildNode("TDB-SIL-059, ShopInstallment Loan - Verify the Loans tile status if BKYC user is not upgraded to SKYC");
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTile),getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTile)));
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objGotchaButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objGotchaButton)));
        shopInstallmentLoanModule.verifyScanQRPage();
        if(platform.equalsIgnoreCase("ios")){
            closeiOSTonikApp();
        } else{
            closeAndroidTonikApp();
        }
        shopInstallmentLoanAPIModule.createSILLoan(propertyFileReader("BKYCMobileNumber","ShopInstallmentLoan"),propertyFileReader("withoutVasFlag","ShopInstallmentLoan"),propertyFileReader("MonthlyInstallmentWithoutVas","ShopInstallmentLoan"));
        relaunchApp(platform);
        waitTime(3000);
        tonikLogin(password);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        verifyHereIsSummaryOfYourLoanPage(decimalFormat.format(Double.parseDouble(propertyFileReader("Amount","ShopInstallmentLoan"))), decimalFormat.format(Double.parseDouble(propertyFileReader("DownPayment","ShopInstallmentLoan"))),decimalFormat.format(Double.parseDouble(propertyFileReader("LoanableAmount","ShopInstallmentLoan"))),propertyFileReader("ProcessingFeeValue","ShopInstallmentLoan")
                ,propertyFileReader("Tenure","ShopInstallmentLoan"));
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objSweetAcceptButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objSweetAcceptButton)));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objverifyMyIdPopupDescription)),propertyFileReader("PopupMessage","ShopInstallmentLoan")," Popup Message");
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objVerifyMyIdButton)," Verify My Id Button");
        verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageHeader),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageHeader)));
        verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTakeFaceIdentity),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTakeFaceIdentity)));
        verifyElementPresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objScanOneValid),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objScanOneValid)));
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopLeftSideBackArrowButton)," Back Arrow Button");
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopLeftSideBackArrowButton)," Back Arrow Button");
        waitForElementToBePresent(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile),3," Loan Tile");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("PendingLevelUp","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText2)),propertyFileReader("SubmitValid","ShopInstallmentLoan"),"Header");
        logger.info("TDB-SIL-059, ShopInstallment Loan - Verify the Loans tile status if BKYC user is not upgraded to SKYC");
        extentLoggerPass("TDB-SIL-059", "ShopInstallment Loan - Verify the Loans tile status if BKYC user is not upgraded to SKYC");
    }
    /**
     * Verify if SKYC user can apply SIL if TSA is created already
     * @param password
     * @throws Exception
     */
    public void verifySKYCUserCanApplySILifTSAIsCreatedAlready_TDB_SIL_063(String password,String mobileNumber) throws Exception {
        HeaderChildNode("TDB-SIL-063, ShopInstallment Loan - Verify if SKYC user can apply SIL if TSA is created already");
        shopInstallmentLoanModule.updateUTCTime(mobileNumber);
        tonikLogin(password);
        shopInstallmentLoanModule.tsaAmountInDashboard = getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTSAAmount)).substring(1).replaceAll(",","");
        verticalSwipeTillElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTile),getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objShoppingTile)));
        verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objGotchaButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objGotchaButton)));
        shopInstallmentLoanModule.verifyScanQRPage();
        if(platform.equalsIgnoreCase("ios")){
            closeiOSTonikApp();
        } else{
            closeAndroidTonikApp();
        }
        shopInstallmentLoanAPIModule.createSILLoan(propertyFileReader("SILMobileNumber", "TestDataNumbers"),propertyFileReader("withoutVasFlag","ShopInstallmentLoan"),propertyFileReader("MonthlyInstallmentWithoutVas","ShopInstallmentLoan"));
        relaunchApp(platform);
        waitTime(3000);
        tonikLogin(password);
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("InProgress","ShopInstallmentLoan"),"Header");
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)));
        shopInstallmentLoanModule.loanSegment2NavigationForSIL(propertyFileReader("ValidTIN","QuickLoanWithVas"),propertyFileReader("ValidIncome","ShopInstallmentLoan"), propertyFileReader("ReferenceNumber1", "QuickLoanWithVas"),propertyFileReader("ReferenceNumber2", "QuickLoanWithVas"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objProfileName),5,"Profile Name");
        waitTime(70000);
        if(platform.equalsIgnoreCase("ios")){
            closeiOSTonikApp();
        } else{
            closeAndroidTonikApp();
        }
        relaunchApp(platform);
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Tile"));
        waitTime(2000);
        shopInstallmentLoanModule.verifyLoanApprovedPage(decimalFormat.format(Double.parseDouble(propertyFileReader("LoanableAmount","ShopInstallmentLoan"))),decimalFormat.format(Double.parseDouble(propertyFileReader("MonthlyInstallmentWithoutVas","ShopInstallmentLoan"))),propertyFileReader("Tenure","ShopInstallmentLoan"));
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objIAcceptTheOffer),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objIAcceptTheOffer)));
        waitTime(2000);
        shopInstallmentLoanModule.tellUsYourPayDayPageValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objFifthOfTheMonth)," Pays Day");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),"Next Button");
        waitTime(2000);
        shopInstallmentLoanModule.verifyMonthlyInstallmentSummaryPage(propertyFileReader("Tenure","ShopInstallmentLoan"),decimalFormat.format(Double.parseDouble(propertyFileReader("MonthlyInstallmentWithoutVas","ShopInstallmentLoan"))));
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objConfirmButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objConfirmButton)));
        waitTime(2000);
        shopInstallmentLoanModule.validateAndSignAllThePages();
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTimeToClaimYourItemPageHeader)),propertyFileReader("TimeToClaim","ShopInstallmentLoan")," Page Header");
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objReadyToPayButtonText),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objReadyToPayButtonText)));
        waitTime(2000);
        shopInstallmentLoanModule.verifyAlreadyAtTheCashierPage();
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objSendConfirmationCodeButtonText),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objSendConfirmationCodeButtonText)));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPayTogetYourConfirmationCodePageHeader)),propertyFileReader("PayToGetConfirmationCode","ShopInstallmentLoan")," Page Header");
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objConfirmationCodeTextField),"Confirmation Code Textfield");
        DriverManager.getAppiumDriver().getKeyboard().sendKeys(RandomIntegerGenerator(6));
        waitTime(2000);
        shopInstallmentLoanModule.verifyPurchasedPage(decimalFormat.format(Double.parseDouble(propertyFileReader("LoanableAmount","ShopInstallmentLoan"))));
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objBackToDashboard),"Back To DashBoard");
        loanCommonMethods.iWillDoItLaterPopup();
        waitForElementToBePresent(LoginPage.getByOSType(platform,LoginPage.objProfileName),5,"Profile name");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1)),propertyFileReader("yes","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText2)),"₱"+decimalFormat.format(Double.parseDouble(propertyFileReader("LoanableAmount","ShopInstallmentLoan")))+" payment was just sent to Bharath","Header");
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader),"Loan Tile");
        verifyLoanDashboardPageWithoutVas(propertyFileReader("MonthlyInstallmentWithoutVas","ShopInstallmentLoan"),"");
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objIicon),"Loan Information I icon");
        verifyLoanInformationPage(decimalFormat.format(Double.parseDouble(propertyFileReader("LoanableAmount","ShopInstallmentLoan"))), shopInstallmentLoanModule.todaysPHDate("dd MMMM yyyy"),propertyFileReader("Tenure","ShopInstallmentLoan"),decimalFormat.format(Double.parseDouble(propertyFileReader("MonthlyInstallmentWithoutVas","ShopInstallmentLoan")))
                ,(ShopInstallmentLoanWithVasModule.todaysPHDate("dd MMMM yyyy")).substring(0,2));
        clickOnAllDocumentAndDownload();
        click(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTopLeftSideBackArrowButton), " Back Arrow Button");
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseMyLoan),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseMyLoan)));
        if(verifyElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopUpMyAccountButton))){
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objTopUpMyAccountButton)," Topup My Account");
            waitTime(2000);
            timeDepositModule.topupViaGCash(propertyFileReader("TopupAmount","TimeDeposit"));
            ShopInstallmentLoanWithVasModule.tsaAmountInDashboard = getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTSAAmount)).substring(1).replaceAll(",","");
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansHeader),"Loan Tile");
            click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseMyLoan),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseMyLoan)));
        }
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objPageHeader)),propertyFileReader("BeforeWeClose","ShopInstallmentLoan")," Page Header");
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseMyLoanButton),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objCloseMyLoanButton)));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objDropTheMic)),propertyFileReader("DropTheMic","ShopInstallmentLoan")," Page header");
        containsValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objFullyPaidText)).replaceAll("\\s"," "),propertyFileReader("YourLoanFullyPaid","ShopInstallmentLoan")," Page SubTitle");
        click(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objBackToDashboard),getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objBackToDashboard)));

        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objTSAAmount)),"₱"+decimalFormat.format(Double.parseDouble(shopInstallmentLoanModule.tsaAmountInDashboard)-Double.parseDouble(propertyFileReader("LoanableAmount","ShopInstallmentLoan")))," TSA Amount After Full Repayment");

        verticalSwipeTillElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile));
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoanTileHeader)),propertyFileReader("FastCash","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTile)),propertyFileReader("Loans","ShopInstallmentLoan"),"Header");
        assertionValidation(getText(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objFindTheRightLoan)),propertyFileReader("FindTheRightLoan","ShopInstallmentLoan"),"Header");
        swipe("DOWN",2);
        waitTime(2000);
        shopInstallmentLoanModule.verifyTransactionPage(decimalFormat.format(Double.parseDouble(propertyFileReader("LoanableAmount","ShopInstallmentLoan"))),propertyFileReader("FullPayment","ShopInstallmentLoan"),propertyFileReader("ViaEarlySettlement","ShopInstallmentLoan"));
        logger.info("TDB-SIL-063, ShopInstallment Loan - Verify if SKYC user can apply SIL if TSA is created already");
        extentLoggerPass("TDB-SIL-063", "ShopInstallment Loan - Verify if SKYC user can apply SIL if TSA is created already");
    }
    /**
     * Verify if user can quit the loan application
     * @param password
     * @throws Exception
     */
    public void verifyUserCanQuitTheLoanApplication_TDB_SIL_064(String password) throws Exception {
        HeaderChildNode("TDB-SIL-064, ShopInstallment Loan - Verify if user can quit the loan application");
        tonikLogin(password);
        if(!verifyElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform,ShopInstallmentLoanPage.objLoansTileSubText1))) {
            verticalSwipeTillElementDisplayed(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objLoansTile));
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), "Tile"));
            shopInstallmentLoanModule.loanTileValidation();
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objShoppingTile), getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objShoppingTile)));
            verifyElementPresentAndClick(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objGotchaButton), getText(ShopInstallmentLoanPage.getByOSType(platform, ShopInstallmentLoanPage.objGotchaButton)));
            shopInstallmentLoanModule.verifyScanQRPage();
            if (platform.equalsIgnoreCase("ios")) {
                closeiOSTonikApp();
            } else {
                closeAndroidTonikApp();
            }
            shopInstallmentLoanAPIModule.createSILLoan(propertyFileReader("SILMobileNumber", "TestDataNumbers"), propertyFileReader("withoutVasFlag", "ShopInstallmentLoan"), propertyFileReader("MonthlyInstallmentWithoutVas", "ShopInstallmentLoan"));
            waitTime(3000);
            relaunchApp(platform);
            waitTime(3000);
            tonikLogin(password);
        }
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),"Loans tile");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLeaveIcon),5,"Leave Icon");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLeaveIcon),"Leave Icon");
        click(QuickLoanWithVasPage.objLeavingSoonReason(platform,"I have other reasons"),getTextVal(QuickLoanWithVasPage.objLeavingSoonReason(platform,"I have other reasons"),": reason"));
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),": button"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objGoodByeScreen),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objGoodByeScreen),": Screen"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objGoodByeScreen)),propertyFileReader("GoodByeScreen","QuickLoanWithVas"),"Screen");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objGoodByeInfo)),propertyFileReader("GoodByeInfo","QuickLoanWithVas"),"Info");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashboard),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashboard),": button"));
        scrollToVertical("Loans");
        logger.info("TDB-SIL-064, ShopInstallment Loan - Verify if user can quit the loan application");
        extentLoggerPass("TDB-SILV-064", "ShopInstallment Loan - Verify if user can quit the loan application");
    }
}
