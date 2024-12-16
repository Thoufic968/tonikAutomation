package com.tonik.bizfunctions;
import com.driverInstance.DriverManager;
import com.tonik.pageObject.*;
import com.tonik.utility.ExtentReporter;
import com.tonik.utility.Utilities;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.sql.SQLException;
import java.util.List;
import static com.tonik.utility.DB_Utilites.*;
import static com.tonik.utility.ExtentReporter.HeaderChildNode;
import static com.tonik.utility.Utilities.*;
import static com.tonik.utility.Utilities.waitForElementToBePresent;
public class FlexPivotWithoutVASModule extends BaseClass{
    static LoanCommonMethods loan = new LoanCommonMethods();
    String platform = Utilities.getPlatform();
    public FlexPivotWithoutVASModule(){
        super();
    }
    /**
     * Reusable method to insert Flex Pivot Offer
     * @param mobileNumber
     * @param userId
     * @param customerId
     * @throws SQLException
     */
    public void insertFlexPivotOffer(String mobileNumber,String userId,String customerId) throws SQLException {
        insertQuery("Insert into loans.tdbk_loan_offers_trx(user_id,cust_id,mobile_number,offer_start_date,offer_expired_date,offer_status,offer_type,offer_amount,offer_min_amount,offer_max_amount,min_emi_amount,max_emi_amount,min_dp_percent,status,loan_product_type,loan_sub_product_type,is_eligible,created_date,updated_by,interest_rate,customer_margin,tenures,file_uploaded_date,popup_triggered,max_tenure)\n" +
                "values('"+userId+"','"+customerId+"','"+mobileNumber+"','"+loan.extractedOneDayBackDate(1)+"','"+loan.extractUpcomingDate(5)+"','"+propertyFileReader("offer_status","FlexPivot")+"','"+propertyFileReader("offer_type","FlexPivot")+"','"+propertyFileReader("offer_amount","FlexPivot")+"'," +
                "'"+propertyFileReader("offer_min_amount","FlexPivot")+"','"+propertyFileReader("offer_max_amount","FlexPivot")+"','"+propertyFileReader("min_emi_amount","FlexPivot")+"','"+propertyFileReader("max_emi_amount","FlexPivot")+"','"+propertyFileReader("min_dp_percent","FlexPivot")+"'," +
                "'"+propertyFileReader("status","FlexPivot")+"','"+propertyFileReader("loan_product_type","FlexPivot")+"','"+propertyFileReader("loan_sub_product_type","FlexPivot")+"','"+propertyFileReader("is_eligible","FlexPivot")+"','"+loan.todayDate()+"','"+propertyFileReader("updated_by","FlexPivot")+"','"+propertyFileReader("interest_rate","FlexPivot")+"'," +
                "'"+propertyFileReader("customer_margin","FlexPivot")+"','"+propertyFileReader("tenures","FlexPivot")+"','"+loan.todayDate()+"','"+propertyFileReader("popup_triggered","FlexPivot")+"',"+Integer.parseInt(propertyFileReader("max_tenure","FlexPivot"))+");\n");
    }
    /**
     * Reusable method to verify Emi
     * @param amount
     * @param tenurepath
     * @param monthlyinstallmentpath
     * @param payhinga
     * @param interestRate
     * @return
     * @throws Exception
     */
    public static String vasEmiVerification(String amount, By tenurepath, By monthlyinstallmentpath, By payhinga, double interestRate) throws Exception {
        String installmentAmount = null;
        String removeSpcl = null;
        String[] expectedTenures = {"6", "9", "12", "18"};
        if (Utilities.getPlatform().equalsIgnoreCase("ios")) {
            List<IOSElement> tenures = DriverManager.getAppiumDriver().findElements(tenurepath);
            List<IOSElement> montlyInstallMentAmount = DriverManager.getAppiumDriver().findElements(monthlyinstallmentpath);
            List<IOSElement> payhingAmount = DriverManager.getAppiumDriver().findElements(payhinga);
            for (IOSElement tenure : tenures) {
                logger.info("For " + amount + " Amount the available tenures are : " + tenure.getText());
                extentLogger("tenure", "For " + amount + " Amount the available tenures are : " + tenure.getText());
                for (int i = 0; i < expectedTenures.length; i++) {
                    if (tenure.getText().contains(expectedTenures[i])) {
                        valueValidation(tenure.getText(), expectedTenures[i], "Month", "contains");
                        String perMonthInterest = loan.calculateEMI(Double.parseDouble(amount), Double.parseDouble(tenure.getText()), interestRate);
                        for (IOSElement installments : montlyInstallMentAmount) {
                            tenure.click();
                            waitTime(2000);//Hard wait required for execution
                            installmentAmount = installments.getText();
                            removeSpcl = loan.removeSpecialCharacter(installmentAmount, false);
                            containsValidation(removeSpcl, perMonthInterest, "InterestRate");
                            for (IOSElement payhingamontexpected : payhingAmount) {
                                String actualNumbers = loan.extractNumbers(payhingamontexpected.getText());
                                double expectedNumbers = loan.round(Double.parseDouble(loan.payHinga(perMonthInterest).toString()), 2);
                                containsValidation(actualNumbers, String.valueOf(expectedNumbers), "Pay hinga life insurance");
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        } else {
            List<WebElement> tenures = findElements(tenurepath);
            List<WebElement> montlyInstallMentAmount = findElements(monthlyinstallmentpath);
            List<WebElement> payhingAmount = findElements(payhinga);
            for (WebElement tenure : tenures) {
                logger.info("For " + amount + " Amount the available tenures are : " + tenure.getText());
                extentLogger("tenure", "For " + amount + " Amount the available tenures are : " + tenure.getText());
                for (int i = 0; i < expectedTenures.length; i++) {
                    if (tenure.getText().contains(expectedTenures[i])) {
                        valueValidation(tenure.getText(), expectedTenures[i], "Month", "contains");
                        String perMonthInterest = loan.calculateEMI(Double.parseDouble(amount), Double.parseDouble(tenure.getText()), interestRate);
                        for (WebElement installments : montlyInstallMentAmount) {
                            tenure.click();
                            waitTime(2000);//Hard wait required for execution
                            installmentAmount = installments.getText();
                            removeSpcl = loan.removeSpecialCharacter(installmentAmount, false);
                            valueValidation(removeSpcl, perMonthInterest, "InterestRate", "contains");
                            for (WebElement payhingamontexpected : payhingAmount) {
                                String actualNumbers = loan.extractNumbers(payhingamontexpected.getText());
                                double expectedNumbers = loan.round(Double.parseDouble(loan.payHinga(perMonthInterest).toString()), 2);
                                valueValidation(actualNumbers, String.valueOf(expectedNumbers), "Pay hinga life insurance", "contains");
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
        return removeSpcl;
    }
    /**
     * Common method to We're officially a thing Tile Validation
     * @throws Exception
     */
    public void weAreOfficiallyAThingTileValidation() throws Exception {
        verifyElementPresent(FlexPivotPage.getByOSType(platform, FlexPivotPage.objLoanTileAfterApproved), getTextVal(FlexPivotPage.getByOSType(platform, FlexPivotPage.objLoanTileAfterApproved), "Text on tile"));
        assertionValidation(getText(FlexPivotPage.getByOSType(platform, FlexPivotPage.objLoanTileAfterApproved)), propertyFileReader("WeAreOfficiallyAThing", "FlexPivot"), "Tile Sub header");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objYourLoanHasBeenAdded)), propertyFileReader("yourLoanAmountAdded", "QuickLoanWithVas"), "Tile Sub header");
    }
    /**
     * Reusable method to input income and company TIN Details
     * @param tinNumber
     * @param income
     * @throws Exception
     */
    public void inputIncomeCompanyAndTINDetails(String tinNumber,String income) throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyIncomeScreen),5,"Monthly Income Screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("MonthlyIncomeScreen", "QuickLoanWithVas"), ": page title");
        loan.inputIncomeAmountAndNext(income,"");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInputCompanyScreen),5,"Where do you work, luv? Screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTINInputField),5,"TIN Input screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("TINScreen", "QuickLoanWithVas"), ": page title");
        type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTINInputField),tinNumber,"TIN Input field");
        if(platform.equalsIgnoreCase("android")){
            hideKeyboard();
        }else{
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objKeyboardDoneBtn), "Keyboard Done Button");
            waitTime(2000);
        }
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),": button"));
    }
    /**
     * Method to Verify the existing user can access the Flex Pivot Loan from the main Dashboard
     * @param password
     * @throws Exception
     */
    public void verifyUserCanAccessFlexPivotLoanFromMainDashBoard_TDB_FP_001(String password,String mobileNumber) throws Exception {
        HeaderChildNode("TDB_FP_001, Flex Pivot Without VAS- Verify the existing user can access the Flex Pivot Loan from the main Dashboard");
        insertFlexPivotOffer("63"+mobileNumber,loan.getUserId("63"+mobileNumber), loan.getCustomerId("63"+mobileNumber));
        loan.insertedOfferPopupValidation(password,"Flex Pivot Without VAS","TDB_FP_001");
    }
    /**
     * Method to Verify the existing user can access the Flex Pivot Loan from the main Dashboard with no Pop up / or by clicking out of the pop up
     * @param password
     * @throws Exception
     */
    public void verifyUserCanAccessFlexPivotLoanFromMainDashBoardWithNoPopup_TDB_FP_002(String password,String mobileNumber) throws Exception {
        HeaderChildNode("TDB_FP_002, Flex Pivot Without VAS - Verify the existing user can access the Flex Pivot Loan from the main Dashboard with no Pop up / or by clicking out of the pop up");
        loan.updatePopupTriggered(loan.getUserId("63" +mobileNumber));
        loan.offerTileValidation(password,"Flex Pivot Without VAS","TDB_FP_002");    }
    /**
     * Method to Verify if user can decline the Flex pivot Loan with VAS offer from the 'Are you sure?' pop up
     * @throws Exception
     */
    public void verifyUserCanDeclineFlexLoanWithVASOfferFromAreYouSurePopup_TDB_FP_005(String mobileNumber,String tenure) throws Exception {
        HeaderChildNode("TDB_FP_005, Flex Pivot Without VAS - Verify if user can decline the Flex pivot Loan with VAS offer from the 'Are you sure?' pop up");
        loan.declineWithVASOfferValidation(mobileNumber,"Flex Pivot","TDB_FP_005",tenure);
    }
    /**
     * Method to Verify the Loans tile status after the successful loan disbursal
     * @throws Exception
     */
    public void verifyLoansTileStatusAfterSuccessfulLoanDisbursal_TDB_FP_027() throws Exception {
        HeaderChildNode("TDB_FP_027, Flex Pivot Without VAS - Verify the Loans tile status after the successful loan disbursal");
        tonikLogin(propertyFileReader("password", "Login"));
        weAreOfficiallyAThingTileValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), "Loans Tile");
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantToCloseBtn), "I want to close my loan Button")) {
            logger.info("TDB_FP_027, Flex Pivot Without VAS - Verify the Loans tile status after the successful loan disbursal validated");
            extentLoggerPass("TDB_FP_027", "TDB_FP_027, Flex Pivot Without VAS - Verify the Loans tile status after the successful loan disbursal validated");
        }
    }
    /**
     * Method to Verify if user can reapply Flex Pivot loan after the full loan repayment
     * @param password
     * @throws Exception
     */
    public void verifyUserCanReapplyFlexPivotWithoutVASAfterFullLoanRepayment_TDB_FP_049(String password,String mobileNumber) throws Exception {
        HeaderChildNode("TDB_FP_049, Flex Pivot Without VAS - Verify if user can reapply Flex Pivot loan after the full loan repayment");
        insertFlexPivotOffer("63"+mobileNumber,loan.getUserId("63"+mobileNumber), loan.getCustomerId("63"+mobileNumber));
        loan.reapplyAfterFullRepayment(password,"Flex Pivot Without VAS","TDB_FP_049");
    }
    /**
     * Method to Verify if SKYC user can apply Flex Pivot Loan if TSA is created already
     * @param mobileNo
     * @throws Exception
     */
    public void verifySKYCUserCanApplyFlexPivotWithoutVASTSACreatedAlready_TDB_FP_055(String mobileNo,String loanType,String tenure) throws Exception {
        HeaderChildNode("TDB_FP_055, Flex Pivot Without VAS - Verify if SKYC user can apply Flex Pivot Loan if TSA is created already");
        loan.updateOfferStatus(loan.getUserId("63"+mobileNo));
        loan.updatePopupTriggered(loan.getUserId("63"+mobileNo));
        loan.endToEndJourney(mobileNo,loanType,"Flex Pivot Without VAS","TDB_FP_055",tenure);
    }
    /**
     * Method to Verify if user can quit the loan application
     * @throws Exception
     */
    public void userCanQuitLoanApplicationValidation_TDB_FP_056(String mobileNumber) throws Exception {
        HeaderChildNode("TDB_FP_056, Flex Pivot Without VAS - Verify if user can quit the loan application");
        loan.updateOfferStatus(loan.getUserId("63"+propertyFileReader("FlexPivotMobileNumber","TestDataNumbers")));
        loan.quitLoanApplication("Flex Pivot Without VAS","TDB_FP_056",mobileNumber);
    }
    /**
     * Method to Verify if user can input the Monthly income, Company name & TIN details
     * @throws Exception
     */
    public void inputMonthlyIncomeTINDetails_TDB_FP_014() throws Exception {
        HeaderChildNode("TDB_FP_014, Flex Pivot Without VAS - Verify if user can input the Monthly income and TIN details");
        tonikLogin(propertyFileReader("password", "Login"));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile), ": Tile"));
        loan.whatDoYouNeedItForScreen("Education");
        loan.fieldOfWork(propertyFileReader("FieldOfWork", "QuickLoanWithVas"));
        loan.selectOccupation(propertyFileReader("Occupation", "QuickLoanWithVas"));
        loan.selectIndustry(propertyFileReader("Industry1", "QuickLoanWithVas"), propertyFileReader("SubIndustry", "QuickLoanWithVas"));
        loan.selectMaritalStatus(propertyFileReader("MaritalStatusOption1", "QuickLoanWithVas"));
        loan.selectKidsOrDependents(propertyFileReader("DependentsOption2", "QuickLoanWithVas"));
        loan.selectHighestEducationalAttainment(propertyFileReader("EducationalAttainment1", "QuickLoanWithVas"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactReferencePage), 5, "Contact reference page");
        loan.updateContactReference(propertyFileReader("ReferenceNumber1", "QuickLoanWithVas"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHaveAnotherReference), 5, "Have another Reference Screen");
        loan.updateContactReference(propertyFileReader("ReferenceNumber2", "QuickLoanWithVas"));
        loan.isThisYourCurrentAddressConfirmation();
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
        loan.leavingSoonPageValidation();
        type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyIncomeField),propertyFileReader("ValidIncomeAmount","QuickLoanWithVas"),"Income input field");
        if(platform.equalsIgnoreCase("ios")){
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objKeyboardDoneBtn), "Keyboard Done Button");
        }
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), ": button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTINInputField),5,"TIN Input screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageTitle)), propertyFileReader("TINScreen", "QuickLoanWithVas"), ": page title");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSubTitleTxt)), propertyFileReader("TINScreenSubtitle", "QuickLoanWithVas"), ": page subtitle");
        loan.leavingSoonPageValidation();
        waitTime(3000);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageTitle)), propertyFileReader("MonthlyIncomeScreen", "QuickLoanWithVas"), ": page title");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),": button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTINInputField),5,"TIN Screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageTitle)), propertyFileReader("TINScreen", "QuickLoanWithVas"), ": page title");
        type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTINInputField),propertyFileReader("InvalidTIN","QuickLoanWithVas"),"TIN Input field");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),": button"));
        if(verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInvalidTINMsg))){
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInvalidTINMsg)), propertyFileReader("InvalidTINMsg", "QuickLoanWithVas"), ": page title");
        }
        clearField(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTINInputField),"TIN Input field");
        type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objTINInputField),propertyFileReader("ValidTIN","QuickLoanWithVas"),"TIN Input field");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),": button"));
        waitTime(3000);
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"))) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("AnotherWayToReachYou", "QuickLoanWithVas"), ": page title");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("AnotherWayToReachYouSubtitle", "QuickLoanWithVas"), ": page subtitle");
            logger.info("TDB_FP_014, Flex Pivot Without VAS - User can input the Monthly income and TIN details and navigated to Another way to reach you? screen validated");
            extentLoggerPass("TDB_FP_014", "TDB_FP_014, Flex Pivot Without VAS - User can input the Monthly income and TIN details and navigated to Another way to reach you? screen validated");
        }
    }
}