package com.tonik.bizfunctions;

import com.driverInstance.DriverManager;
import com.tonik.pageObject.OnBoardingPage;
import com.tonik.pageObject.TimeDepositPage;
import com.tonik.pageObject.TopUpPage;
import com.tonik.utility.Utilities;
import org.openqa.selenium.WebElement;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.tonik.utility.DB_Utilites.selectQuery;
import static com.tonik.utility.Utilities.*;

public class TimeDepositModule extends BaseClass {
    public TimeDepositModule() {
        super();
    }
    String platform = Utilities.getPlatform();
    public static String newNickName;
    public static String investAmount;
    public static String tenure;
    public static String interestRate;
    public static String email;
    public static String interest_amount_string = null;
    public static String total_days = null;
    public static String tsaAmountInDashboard;
    public static String tdAmountInDashboard;
    public static String availableTDText = "";
    public static DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
    public static String[] months = {"6", "9", "12", "18", "24"};
    /**
     * Reusable method to navigate to Investment Page
     * @throws Exception
     */
    public void navigateToInvestPage(String password) throws Exception {
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTimeDepositTextOnContainer));
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTimeDepositTextOnContainer), "Time Deposite");
        if (verifyElementDisplayed(TimeDepositPage.objAllPageHeaderText(platform, propertyFileReader("TDPage_Header0", "TimeDeposit")))) {
            waitForElementToBePresent(TimeDepositPage.objAllPageHeaderText(platform, propertyFileReader("TDPage_Header0", "TimeDeposit")), 3, propertyFileReader("TDPage_Header0", "TimeDeposit") + " : Header");
            for (int i = 0; i < 6; i++) {
                verifyElementPresentAndClick(TimeDepositPage.objTextButtons(platform, String.valueOf(i)), propertyFileReader("TDPage_Buttons" + i + "", "TimeDeposit") + " : Button");
            }
        } else {
            verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objPlusMarkInMyTDPage), "Start Another TD Buton");
        }
    }
    /**
     * Reusable method to verify All sales page and navigate to Investment calculation page
     * @throws Exception
     */
    public void verifyAllSalesPageAndNavigateToInvestCalculationPage(String password) throws Exception {
        tonikLogin(password);
        verticalSwipeTillElementDisplayed(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTimeDepositTextOnContainer));
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTimeDepositTextOnContainer), "Time Deposit");
        for (int i = 0; i < 6; i++) {
            assertionValidation(getText(TimeDepositPage.objAllPageHeaderText(platform, propertyFileReader("TDPage_Header" + i + "", "TimeDeposit"))), propertyFileReader("TDPage_Header" + i + "", "TimeDeposit"), " : Button");
            containsValidation(getText(TimeDepositPage.objAllPageDescriptionText(platform, propertyFileReader("TDPage_Header" + i + "", "TimeDeposit"))), propertyFileReader("TDPage_Description" + i + "", "TimeDeposit"), " Sales Page Description");
            verifyElementPresentAndClick(TimeDepositPage.objTextButtons(platform, String.valueOf(i)), propertyFileReader("TDPage_Buttons" + i + "", "TimeDeposit") + " : Button");
        }
    }
    /**
     * Reusable method to verify Investment calculation page
     * @throws Exception
     */
    public void verifyInvestmentCalculationPage() throws Exception {
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objHowMuchWillyouInvestText)), propertyFileReader("HowMuch", "TimeDeposit"), " Text");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInvestedAmount), getTextVal(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInvestedAmount), " : Invest Amount"));
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objEditCalculatorButton), "Edit Button");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objCalculatorSlideBar), "Amount Slide Bar");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objHowLongMoneyGrowTxt)), propertyFileReader("HowLongWillMoneyGrowText", "TimeDeposit"), " : Text");
        List<WebElement> InvestmentMonths = findElements(TimeDepositPage.getByOSType(platform, TimeDepositPage.objAllInvestmentMonthsGroup));
        for (int i = 0; i < InvestmentMonths.size(); i++) {
            List<WebElement> Text = findElements(TimeDepositPage.objTextsInEachMonthContainer(platform, i + 1));
            String MonthsValue = propertyFileReader(months[i] + "monthsContainer", "TimeDeposit");
            String[] text = MonthsValue.split(",");
            for (int j = 0; j < 3; j++) {
                assertionValidation(Text.get(j).getText(), text[j], "");
            }
        }
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objYouWillEarnInInterestTxt)), propertyFileReader("YouWillEarnInterestText", "TimeDeposit"), " : Text");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInterestAmount), "Interest Amount");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objBeforeTaxesTxt)), propertyFileReader("BeforeTaxText", "TimeDeposit"), " : Text");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objHowIsThisCalculatedLink)), propertyFileReader("HowIsThisCalculated", "TimeDeposit"), " : link");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInvestmentDescription)).replaceAll("\\r|\\n", " "), propertyFileReader("InvestmentDescription", "TimeDeposit"), " : Text");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objIamInterestedButton), "I am interested Button");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objBackArrowButton), "Back Arrow Button");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objGetInTouchIcon), "Get In Touch Icon");
    }

    /**
     * Reusable method to navigate to time deposit page
     * @throws Exception
     */
    public void navigateToTimeDepositePage() throws Exception {
        verticalSwipeTillElementDisplayed(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTimeDepositTextOnContainer));
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTimeDepositTextOnContainer), "Time Deposite");
    }
    /**
     * Reusable method to verify Edit investment page
     * @throws Exception
     */
    public void editInvestAmount(int month, String interest) throws Exception {
        waitForElementToBePresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objEditCalculatorButton), 3, "Edit Amount Button");
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objEditCalculatorButton), "Edit Amount Button");
        for (int amount = 0; amount < 3; amount++) {
            clearField(TimeDepositPage.getByOSType(platform, TimeDepositPage.objEditInvestAmountTextField), "Edit Amount TextField");
            type(TimeDepositPage.getByOSType(platform, TimeDepositPage.objEditInvestAmountTextField), propertyFileReader("TDInvestAmt" + amount, "TimeDeposit"), "Edit Amount TextField");
            if (getPlatform().equalsIgnoreCase("ios")) {
                click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objdoneButton), " Done Button");
            }
            click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objContinueButton), "Continue Button");
            if (amount != 2) {
                assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInvestmentAmtRangeText)).replaceAll(" ₱", " "), propertyFileReader("InvestmentAmtRangeMessage", "TimeDeposit"), "Amount Range Error Message");
            }
        }
        for (int i = 0; i < months.length; i++) {
            click(TimeDepositPage.objInvestmentMonthsText(platform, months[i]), months[i]);
            String interestInPage = getText(TimeDepositPage.objInterestRateBasedOnMonth(platform, months[i])).replaceAll("%", "");
            calculateInterestAmount(propertyFileReader("TDInvestAmt2", "TimeDeposit"), months[i], interestInPage);
            assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInterestAmount)), "₱" + interest_amount_string, " : Interest Amount For " + months[i] + " month");
        }
        click(TimeDepositPage.objInvestmentMonthsText(platform, String.valueOf(month)), " Month");
        String investedAmt = getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInvestedAmount));
        String interestEarned = getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInterestAmount));
        double maturityPayout = Double.parseDouble(investedAmt.substring(1).replaceAll(",", "")) + Double.parseDouble(interestEarned.substring(1).replaceAll(",", ""));
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objIamInterestedButton), "I am Interested Button");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objSetUpTimeHeader), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objSetUpTimeHeader)));
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInvestAmtText)), propertyFileReader("Details1", "TimeDeposit"), " Text");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInvestedAmountvalue)), investedAmt, " Invested Amount");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTermText)), propertyFileReader("Details2", "TimeDeposit"), " Text");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTermValue)), month + " months", "Months");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objNickNameText)), propertyFileReader("NickName", "TimeDeposit"), "Text");
        containsValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objNickNameValue)), propertyFileReader("MyTd", "TimeDeposit"), " Nick Name");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInterestRatetext)), propertyFileReader("Details3", "TimeDeposit"), " Text");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInterestRateValue)), interest + "%", "Interest Rate");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInterestEarnedText)), propertyFileReader("Details4", "TimeDeposit"), "Text");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInterestRateEarnedValue)), interestEarned, "Interest Earned Amount");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objPayOutMaturityText)), propertyFileReader("Details5", "TimeDeposit"), " Text");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objPayoutAtMaturityValue)), "₱" + decimalFormat.format(maturityPayout), "Interest Earned Amount");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objEarlyWithdrawalText)), propertyFileReader("EarlyWithdraw", "TimeDeposit"), " Text");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objEarlyWithdrawalValue)), propertyFileReader("Free", "TimeDeposit"), " Text");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objDateOfMaturityText)), propertyFileReader("DateOfMaturity", "TimeDeposit"), " Text");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objDateOfMaturityValue)), calculateMaturityDate(month), " Maturity Date");
        verticalSwipeTillElementDisplayed(TimeDepositPage.getByOSType(platform, TimeDepositPage.objOpenThisTDButton));
        containsValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objSetUpTimeDepositPageDescription)).replaceAll("\\s", " "), propertyFileReader("SetupTDPageDescrition", "TimeDeposit"), " Description");
        swipe("UP", 1);
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objAcceptTermsAndConditionLink)), propertyFileReader("TermsAndCondition", "TimeDeposit"), " Terms and condition link");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTermsAndConditionRadioBtn), "Terms and condition radio button");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objOpenThisTDButton), "Let's Open This TD Button");
        assertionValidation(getAttributValue("enabled", TimeDepositPage.getByOSType(platform, TimeDepositPage.objOpenThisTDButton)), "false", ": Enable Attribute value");
    }
    /**
     * Reusable method to create New Time Deposit
     * @throws Exception
     */
    public void createNewTD(String amount, String month) throws Exception {
        waitForElementToBePresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTSAAmount), 3, "TSA Amount");
        tsaAmountInDashboard = getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTSAAmount)).substring(1).replaceAll(",", "");
        swipe("UP", 2);
        if (verifyElementDisplayed(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTDAmountInDashBoard))) {
            tdAmountInDashboard = getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTDAmountInDashBoard)).substring(1).replaceAll(",", "");
        } else {
            tdAmountInDashboard = "0";
        }
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTimeDepositTextOnContainer), "Time Deposite");
        if (verifyElementDisplayed(TimeDepositPage.objAllPageHeaderText(platform, propertyFileReader("TDPage_Header0", "TimeDeposit")))) {
            for (int page = 0; page < 6; page++) {
                verifyElementPresentAndClick(TimeDepositPage.objTextButtons(platform, String.valueOf(page)), propertyFileReader("TDPage_Buttons" + page + "", "TimeDeposit") + " : Button");
                availableTDText = "5 of 5 available Time Deposits";
            }
        } else {
            availableTDText = getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objAvailableTdText));
            while (availableTDText.equals("0 of 5 available Time Deposits")) {
                click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objFirstTDCreated), " First TD");
                withdrawTD();
                verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objBackArrowBtnInTDDetailsPage), "Back Arrow Button");
                waitTime(3000);
                tsaAmountInDashboard = getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTSAAmount)).substring(1).replaceAll(",", "");
                swipe("UP", 2);
                if (verifyElementDisplayed(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTDAmountInDashBoard))) {
                    tdAmountInDashboard = getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTDAmountInDashBoard)).substring(1).replaceAll(",", "");
                } else {
                    tdAmountInDashboard = "0";
                }
                verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTimeDepositTextOnContainer), "Time Deposite");
                waitTime(2000);//Page Loading issue
                availableTDText = getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objAvailableTdText));
            }
            click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objPlusMarkInMyTDPage), "Start Another TD Button");
        }
        waitTime(3000);
        waitForElementToBePresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objHowMuchWillyouInvestText), 2, getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objHowMuchWillyouInvestText)));
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objEditCalculatorButton), "Edit Amount Button");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTonikALlPageHeaderText)), propertyFileReader("YourInvestAmount", "TimeDeposit"), " Page Header");
        clearField(TimeDepositPage.getByOSType(platform, TimeDepositPage.objEditInvestAmountTextField), "Edit Amount TextField");
        int insufficientAmount = 0;
        if (amount == "Insufficient") {
            if (Double.parseDouble(tsaAmountInDashboard) < 5000) {
                insufficientAmount = (int) (Double.parseDouble(tsaAmountInDashboard) + 5000);
            } else {
                insufficientAmount = (int) (Double.parseDouble(tsaAmountInDashboard) + 5);
            }
            type(TimeDepositPage.getByOSType(platform, TimeDepositPage.objEditInvestAmountTextField), String.valueOf(insufficientAmount), "Edit Amount TextField");
        } else {
            type(TimeDepositPage.getByOSType(platform, TimeDepositPage.objEditInvestAmountTextField), amount, "Edit Amount TextField");
        }
        if (getPlatform().equalsIgnoreCase("ios")) {
            click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objdoneButton), " Done Button");
        }
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objContinueButton), "Continue Button");
        verifyElementPresentAndClick(TimeDepositPage.objInvestmentMonthsText(platform, month), month + " Month");
        if(verifyElementDisplayed(TimeDepositPage.objMonth(platform,propertyFileReader("Month0","TimeDeposit")))){
            assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objSpecificConditionsApplyTxt)), propertyFileReader("SpecificConditionsApplyTxt", "TimeDeposit"), "Text");
        }else{
            assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.obj6MonthRestrictionText)), propertyFileReader("6MonthRestrictionText", "TimeDeposit"), "Text");
        }
        String interest = String.valueOf(getText(TimeDepositPage.objInterestRate(platform,month)).replaceAll("%",""));
        String investedAmt = getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInvestedAmount));
        String interestEarned = getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInterestAmount));
        double maturityPayout = Double.parseDouble(investedAmt.substring(1).replaceAll(",", "")) + Double.parseDouble(interestEarned.substring(1).replaceAll(",", ""));
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objIamInterestedButton), "I am Interested Button");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objSetUpTimeHeader), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objSetUpTimeHeader)));
        setUpATimeDeposit(String.valueOf(insufficientAmount),investedAmt,Integer.parseInt(month),interest,interestEarned,maturityPayout);
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTermsAndConditionRadioBtn), "Terms and condition radio button");
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objOpenThisTDButton), "Let's Create TD  Button");
        if (verifyIsElementDisplayed(TimeDepositPage.getByOSType(platform, TimeDepositPage.objPinkySwearButton))) {
            click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objPinkySwearButton), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objPinkySwearButton)));
        }
    }
    /**
     * Reusable method to perform topup
     * @throws Exception
     */
    public void topupViaGCash(String amount) throws Exception {
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTopupPageHeader), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTopupPageHeader)) + " : Page Header");
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objGcashTopupOption), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objGcashTopupOption)) + " Button");
        waitTime(5000);
        click(TopUpPage.getByOSType(platform,TopUpPage.objOneTimeTopUpOption),getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objOneTimeTopUpOption),"Gcash Top-up Option"));
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTopupAmountHeader), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTopupAmountHeader)));
        type(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTopupAmountTextfield), amount, "Amount Text field");
        if (getPlatform().equalsIgnoreCase("ios")) {
            click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objdoneButton), " Done Button");
        }
        click(TimeDepositPage.objTextButton(platform, "Next"), getText(TimeDepositPage.objTextButton(platform, "Next")) + " Button");
        waitTime(2000);
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objProceedToPayButton), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objProceedToPayButton)));
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTopupSuccessMessage), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTopupSuccessMessage)) + " Success Message");
        if (verifyElementDisplayed(TimeDepositPage.getByOSType(platform, TimeDepositPage.objOkButton))) {
            click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objOkButton), "Ok Button");
        } else {
            waitForElementToBePresent(TimeDepositPage.objTextButton(platform, "Back to Dashboard"), 2, "Back To Dashboard");
            verifyElementPresentAndClick(TimeDepositPage.objTextButton(platform, "Back to Dashboard"), getText(TimeDepositPage.objTextButton(platform, "Back to Dashboard")) + " Button");
        }
    }
    /**
     * Reusable method to verify woot page
     * @throws Exception
     */
    public void verifyWootPage() throws Exception {
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objWootWootText), "Woot Woot!");
        containsValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objWootWootDescription1)), propertyFileReader("WootDecs1", "TimeDeposit"), " Desription");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objWootWootDescription2)), propertyFileReader("WootDecs2", "TimeDeposit"), " Desription");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objViewTimeDepositDetailsLink), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objViewTimeDepositDetailsLink)) + " Link");
        assertionValidation(getAttributValue("enabled", TimeDepositPage.getByOSType(platform, TimeDepositPage.objDoneBtn)), "true", ": Enable Attribute value");
    }

    /**
     * reusable method to verify Time Deposit Details page
     * @throws Exception
     */
    public void verifyTimeDepositDetailsPage(String amount, String daysDefference, String interest, String interestEarned, String payoutAtmaturity, String maturityDate) throws Exception {
        amount = decimalFormat.format(Double.parseDouble(amount));
        payoutAtmaturity = decimalFormat.format(Double.parseDouble(payoutAtmaturity));
        String[] values = {"₱" + amount, daysDefference + " Days", interest + "%", "₱" + interestEarned, "₱" + payoutAtmaturity, maturityDate};
        waitForElementToBePresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTimeDepositDetailsHeader), 3, "Time Deposit Page Header");
        waitTime(3000);
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTimeDepositDetailsHeader)), propertyFileReader("TimeDepositDetails", "TimeDeposit"), ": Header");
        assertionValidation(getText(TimeDepositPage.objDetailsInTimeDepositDetailsPage(platform, propertyFileReader("Details0", "TimeDeposit"))), (propertyFileReader("Details0", "TimeDeposit")), " Text");
        verifyElementPresent(TimeDepositPage.objDetailsValueInTimeDepositDetailsPage(platform, propertyFileReader("Details0", "TimeDeposit")), getText(TimeDepositPage.objDetailsValueInTimeDepositDetailsPage(platform, propertyFileReader("Details0", "TimeDeposit"))) + " Account Number");
        for (int detail = 1; detail < values.length; detail++) {
            if (detail == 2 && Integer.parseInt(daysDefference) >= 365) {
                values[detail - 1] = Integer.parseInt(daysDefference) / 30 + " Months";
            }
            assertionValidation(getText(TimeDepositPage.objDetailsInTimeDepositDetailsPage(platform, propertyFileReader("Details" + detail, "TimeDeposit"))), propertyFileReader("Details" + detail, "TimeDeposit"), " Detail");
            assertionValidation(getText(TimeDepositPage.objDetailsValueInTimeDepositDetailsPage(platform, propertyFileReader("Details" + detail, "TimeDeposit"))), values[detail - 1], " : " + propertyFileReader("Details" + detail, "TimeDeposit"));
        }
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTimeDepositDetailsPageDescription)), propertyFileReader("TDDetailsPageDescription", "TimeDeposit"), " Description");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objBackArrowBtnInTDDetailsPage), "Back Arrow Button");
    }
    /**
     * Reusable method to verify Created Time deposit In My TimeDeposit page
     * @throws Exception
     */
    public void clickOncreateTdButtonAndCheckItInMyTimeDepositPage() throws Exception {
        swipe("UP", 1);
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTermsAndConditionRadioBtn), "Terms and condition radio button");
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objOpenThisTDButton), "Let's Create TD Button");
        containsValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objWootWootText)), propertyFileReader("Woot", "TimeDeposit"), " Header");
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objDoneBtn), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objDoneBtn)) + " Button");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTopupPageHeader)), propertyFileReader("MyTimeDeposit", "TimeDeposit"), " Page Header");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objNewlyCreatedTD), "Newly Created TD");
    }
    /**
     * Reusable method to calculate Maturity Date
     * @throws Exception
     */
    public String calculateMaturityDate(int month) {
        LocalDate today = LocalDate.now();
        String formattedToday = formatDate(today, "dd MMM yyyy");
        LocalDate newDate = addMonthsToDate(today, month);
        String formattedNewDate = formatDate(newDate, "dd MMM yyyy");
        return formattedNewDate;
    }
    /**
     * Reusable method to format date
     * @param date
     * @param format
     * @return
     */
    public String formatDate(LocalDate date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }
    /**
     * Reusable method to add Months To Date
     * @param date
     * @param months
     * @return
     */
    public LocalDate addMonthsToDate(LocalDate date, int months) {
        return date.plusMonths(months);
    }
    /**
     * Reusable method to fetch the data from setup time deposit page
     * @throws Exception
     */
    public void getAllTheDataFromSetUptimeDepositPage() throws Exception {
        investAmount = getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInvestedAmountvalue)).replaceAll(",", "");
        String[] termText = getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTermValue)).split(" ");
        tenure = termText[0];
        interestRate = getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInterestRateValue)).replaceAll("%", "");
    }
    /**
     * Reusable method to validate Set up a time deposit screen
     * @param amount
     * @param investedAmt
     * @param month
     * @param interest
     * @param interestEarned
     * @param maturityPayout
     * @throws Exception
     */
    public void setUpATimeDeposit(String amount,String investedAmt,int month,String interest,String interestEarned,double maturityPayout) throws Exception {
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objSetUpTimeHeader), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objSetUpTimeHeader)));
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInvestAmtText)), propertyFileReader("Details1", "TimeDeposit"), " Text");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInvestedAmountvalue)), investedAmt, " Invested Amount");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTermText)), propertyFileReader("Details2", "TimeDeposit"), " Text");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTermValue)), month + " months", "Months");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objNickNameText)), propertyFileReader("NickName", "TimeDeposit"), "Text");
        containsValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objNickNameValue)), propertyFileReader("MyTd", "TimeDeposit"), " Nick Name");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInterestRatetext)), propertyFileReader("Details3", "TimeDeposit"), " Text");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInterestRateValue)), interest + "%", "Interest Rate");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInterestEarnedText)), propertyFileReader("Details4", "TimeDeposit"), "Text");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInterestRateEarnedValue)), interestEarned, "Interest Earned Amount");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objPayOutMaturityText)), propertyFileReader("Details5", "TimeDeposit"), " Text");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objPayoutAtMaturityValue)), "₱" + decimalFormat.format(maturityPayout), "Interest Earned Amount");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objEarlyWithdrawalText)), propertyFileReader("EarlyWithdraw", "TimeDeposit"), " Text");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objEarlyWithdrawalValue)), propertyFileReader("Free", "TimeDeposit"), " Text");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objDateOfMaturityText)), propertyFileReader("DateOfMaturity", "TimeDeposit"), " Text");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objDateOfMaturityValue)), calculateMaturityDate(month), " Maturity Date");
        swipe("UP", 2);
        containsValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objSetUpTimeDepositPageDescription)).replaceAll("\\s", " "), propertyFileReader("SetupTDPageDescrition", "TimeDeposit"), " Description");
        if (month==6) {
            assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objAdditionalInterestText)), propertyFileReader("AdditionalInterestText", "TimeDeposit"), " Details text");
            assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objAdditionalInterestRate)), propertyFileReader("AdditionalInterest", "TimeDeposit"), "Additional Interest");
            assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.obj1PercentInterestPayoutText)), propertyFileReader("1PercentInterestPayout", "TimeDeposit"), "Additional Interest");
            containsValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.obj1PercentInterestPayout)).replaceAll(",",""), String.valueOf(Double.parseDouble(calculateInterestAmount(amount, String.valueOf(month), "6").replaceAll(",","").replaceAll("₱",""))+Double.parseDouble(amount)), "Interest Earned Amount");
            assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.obj1PercentInterestDescription)).replaceAll("₱", ""), propertyFileReader("1PercentInterestDescription", "TimeDeposit"), " Details text");
        }
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objAcceptTermsAndConditionLink)), propertyFileReader("TermsAndCondition", "TimeDeposit"), " Terms and condition link");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTermsAndConditionRadioBtn), "Terms and condition radio button");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objOpenThisTDButton), "Let's Open This TD Button");
        assertionValidation(getAttributValue("enabled", TimeDepositPage.getByOSType(platform, TimeDepositPage.objOpenThisTDButton)), "false", ": Enable Attribute value");
    }
    /**
     * Reusable method to verify Newly created Time deposit
     * @throws Exception
     */
    public void verifyNewlyCreatedTdInMyTimeDepositSheet(String investedAmount, String tenure) throws Exception {
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objSetUpTimeHeader), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objSetUpTimeHeader)) + " Page Header");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInvestedAmountvalue)).replaceAll(",", ""), "₱" + investedAmount, " Invest Amount");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTermValue)), tenure + " months", " Tenure");
        calculateInterestAmount(investedAmount + ".00", tenure, interestRate);
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInterestRateEarnedValue)).replaceAll(",", ""), "₱" + interest_amount_string, "Interest Amount");
        String payoutAtMaturityAmount = decimalFormat.format(Double.parseDouble(interest_amount_string) + Double.parseDouble(investedAmount));
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objPayoutAtMaturityValue)), "₱" + payoutAtMaturityAmount, "Maturity Payout Amount");
        clickOncreateTdButtonAndCheckItInMyTimeDepositPage();
        verticalSwipeTillElementDisplayed(TimeDepositPage.objNewlyCreatedTDBasedOnMaturityPayoutAmt(platform, "₱" + payoutAtMaturityAmount));
        assertionValidation(getText(TimeDepositPage.objNewlyCreatedTDBasedOnMaturityPayoutAmt(platform, "₱" + payoutAtMaturityAmount)), "₱" + payoutAtMaturityAmount, " Newly Created TD Maturity Payout Amount");
    }
    /**
     * Reusable method to calculate Time Deposit Interest
     * @throws Exception
     */
    public static String calculateInterestAmount(String amount, String months, String rate) throws Exception {
        float total_number_of_days = 0;
        float interest_amount = 0;
        float P = Float.parseFloat(amount);
        float R = (Float.parseFloat(rate)) / 100;
        float T;
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Singapore"));
        Calendar currentDate = Calendar.getInstance();
        Date date = currentDate.getTime();
        int current_year = currentDate.get(Calendar.YEAR);
        long time1 = currentDate.getTimeInMillis();
        currentDate.add(Calendar.MONTH, Integer.valueOf(months));
        Date mature_date = currentDate.getTime();
        int mature_year = currentDate.get(Calendar.YEAR);
        long time2 = currentDate.getTimeInMillis();
        if (current_year == mature_year) {
            long diffTime = time2 - time1;
            T = diffTime / (1000 * 3600 * 24);
            total_number_of_days = total_number_of_days + T;
            T = T / cal_number_of_days_in_year(current_year);
            interest_amount = P * R * T;
            interest_amount_string = String.format("%.2f", interest_amount);
        } else {
            float perYearValue = 0;
            for (int i = current_year; i <= mature_year; i++) {
                if (i == current_year) {
                    String lastDate = "31/12/" + Integer.toString(current_year);
                    Date mature_date_current = new SimpleDateFormat("dd/MM/yyyy").parse(lastDate);
                    long tine2_current = mature_date_current.getTime();
                    long diffTime_current = tine2_current - time1;
                    T = (diffTime_current / (1000 * 3600 * 24)) + 2;
                    total_number_of_days = total_number_of_days + T;
                    T = T / cal_number_of_days_in_year(current_year);
                    perYearValue = perYearValue + (P * R * T);
                } else if (i == mature_year) {
                    String yearStartDate = "01/01/" + Integer.toString(mature_year);
                    Date mature_date_mature = new SimpleDateFormat("dd/MM/yyyy").parse(yearStartDate);
                    long tine2_mature = mature_date_mature.getTime();
                    long diffTime_mature = time2 - tine2_mature;
                    T = (diffTime_mature / (1000 * 3600 * 24));
                    total_number_of_days = total_number_of_days + T;
                    T = T / cal_number_of_days_in_year(mature_year);
                    float interest_cal_mature = P * R * T;
                    perYearValue = perYearValue + interest_cal_mature;
                } else {
                    String startDate_between = "01/01/" + Integer.toString(i);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate sdate = LocalDate.parse(startDate_between, formatter);
                    LocalDate ldate = sdate.plusMonths(12);
                    long time1_between = sdate.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
                    long time2_between = ldate.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
                    long diffTime_between = time2_between - time1_between;
                    T = diffTime_between / (1000 * 3600 * 24);
                    total_number_of_days = total_number_of_days + T;
                    T = T / cal_number_of_days_in_year(i);
                    float interest_cal_between = P * R * T;
                    perYearValue = perYearValue + interest_cal_between;
                }
            }
            interest_amount = roundFloat(perYearValue, 2);//perYearValue;
            interest_amount_string = String.format("%.2f", perYearValue);
        }
        total_days = Float.toString(total_number_of_days).replace(".0", "");
        return interest_amount_string;
    }
    /**
     * Reusable method to verify number of days created Time deposit
     * @throws Exception
     */
    public static int cal_number_of_days_in_year(int year) {
        int days_in_year;
        if ((year % 4) == 0) {
            days_in_year = 366;
        } else {
            days_in_year = 365;
        }
        return days_in_year;
    }
    /**
     * Reusable method to round float value
     * @param f
     * @param place
     * @return
     */
    public static float roundFloat(float f, int place) {
        BigDecimal bigDecimal = new BigDecimal(Float.toString(f));
        bigDecimal = bigDecimal.setScale(place, RoundingMode.HALF_UP);
        return bigDecimal.floatValue();
    }
    /**
     * Reusable method to get the email from Popup Message
     * @throws Exception
     */
    public static void getTheEmailFromPopup(String message) {
        Pattern pattern = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b");
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            email = matcher.group();
        }
    }
    /**
     * Reusable method to Enter OTP and verify Nooice page
     * @throws Exception
     */
    public void enterOTPAndVerifyNooicePage() throws Exception {
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objOtpPageDescription), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objOtpPageDescription)) + " Page Header");
        containsValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objCodeExpiringInSeconds)), propertyFileReader("CodeExpiresIn", "TimeDeposit"), " Text");
        getTheEmailFromPopup(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objOtpPageDescription)));
        String otp = selectQuery("selectQuerycustomer", "SELECT otp FROM customer.tdbk_email_otp_track where email_id= '" + email + "';");
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objOtpTextField), "OTP Field");
        DriverManager.getAppiumDriver().getKeyboard().sendKeys(otp);
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objNooicePageHeader)), propertyFileReader("Nooice", "TimeDeposit"), " Header");
        containsValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objNooicePageDescription)), propertyFileReader("NooiceDescription", "TimeDeposit"), " Description");
        verifyElementPresentAndClick(TimeDepositPage.objTextButton(platform, "Close"), getText(TimeDepositPage.objTextButton(platform, "Close")) + " :Button");
    }
    /**
     * Reusable method to verify Emial VerificationPopup
     * @throws Exception
     */
    public void verifyEmailVerificationPopup() throws Exception {
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objPopupDescription), "Email Verification Popup");
        containsValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objPopupDescription)), propertyFileReader("EmailVerificationPopupDescription", "TimeDeposit"), "Email Verification Popup Message");
        getTheEmailFromPopup(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objPopupDescription)));
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objUpdateEmailAddressLink), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objUpdateEmailAddressLink)) + " Link");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objSendVerificationButton), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objSendVerificationButton)) + " Button");
    }
    /**
     * Reusable method to verify Newly created Time deposit
     * @throws Exception
     */
    public void verifyNewlyCreatedTDDetails(String payoutAmount, String interestRate, String earnedInterestAmount) throws Exception {
        payoutAmount = decimalFormat.format(Double.parseDouble(payoutAmount));
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTDName), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTDName)) + " Name");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objPayoutText)), propertyFileReader("Payout", "TimeDeposit"), " Text");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objPayoutAmount)), "₱" + payoutAmount, " Payout Amount");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInterestRateText)), propertyFileReader("Interest", "TimeDeposit"), " Text");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInterestRateValueForIndividualTD)), interestRate + "%", " Interest Rate");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objEarnedText)), propertyFileReader("Earned", "TimeDeposit"), " Earned Text");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objEarnedAmount)).replaceAll(",", ""), "₱" + earnedInterestAmount, " Earned Amount");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objRollOverTimeDeposiText), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objRollOverTimeDeposiText)));
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objWithDrawEarlyLink), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objWithDrawEarlyLink)));
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objIiconInTDpage), "I icon");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objBackArrowBtnInTDDetailsPage), " Back Arrow Button");
    }
    /**
     * Reusable method to verify Loan Tile Validatiom created Time deposit
     * @throws Exception
     */
    public void verifyTimeDepositTileContainerAfterNewlyCreatedTD(String tdAmount) throws Exception {
        waitForElementToBePresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTimeDepositTextOnContainer), 3, "Time Deposit Text");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTimeDepositTextOnContainer), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTimeDepositTextOnContainer)) + " Header");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTDAmountInDashBoard)), "₱" + tdAmount, " TD Amount");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTimeDepositTileContainerDescription)), propertyFileReader("TDTileContainerDescription", "TimeDeposit"), " Description");
    }
    /**
     * Reusable method to create TD based on months and verify the  Newly created Time deposit
     * @throws Exception
     */
    public void createTDBasedOnMonthAndVerifyTheDetails(String password, String amount, String month, String interest) throws Exception {
        createNewTD(amount, month);
        calculateInterestAmount(amount, month, interest);
        calculateMaturityDate(Integer.parseInt(month));
        containsValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objWootWootText)), propertyFileReader("Woot", "TimeDeposit"), " Header");
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objViewTimeDepositDetailsLink), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objViewTimeDepositDetailsLink)) + " Link");
        String newTdAccountNumber = getText(TimeDepositPage.objDetailsValueInTimeDepositDetailsPage(platform, propertyFileReader("Details0", "TimeDeposit")));
        String payoutAtMaturityAmount = String.valueOf(Double.parseDouble(interest_amount_string) + Double.parseDouble(amount));
        verifyTimeDepositDetailsPage(amount + ".00", String.valueOf(total_days), interest, interest_amount_string, payoutAtMaturityAmount, calculateMaturityDate(Integer.parseInt(month)));
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objBackArrowBtnInTDDetailsPage), "Back Arrow Button");
        containsValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objWootWootText)), propertyFileReader("Woot", "TimeDeposit"), " Header");
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objDoneBtn), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objDoneBtn)) + " Button");
        waitTime(3000);//page load issue to get the available Time deposit
        String availableTimDepositAfterNewlyCreatedTD = Integer.parseInt(availableTDText.substring(0, 1)) - 1 + availableTDText.substring(1);
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objAvailableTdText)), availableTimDepositAfterNewlyCreatedTD, "Available TD Text");
        String totalPayoutAmount = decimalFormat.format(Double.parseDouble(interest_amount_string) + Double.parseDouble(amount));
        verticalSwipeTillElementDisplayed(TimeDepositPage.objTextButton(platform, "₱" + totalPayoutAmount));
        verifyElementPresentAndClick(TimeDepositPage.objTextButton(platform, "₱" + totalPayoutAmount), " Newly Created TD");
        verifyNewlyCreatedTDDetails(String.valueOf(Double.parseDouble(interest_amount_string) + Double.parseDouble(amount)), interest, interest_amount_string);
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objIiconInTDpage), "I Icon");
        verifyTimeDepositDetailsPage(amount + ".00", String.valueOf(total_days), interest, interest_amount_string, String.valueOf(Double.parseDouble(interest_amount_string) + Double.parseDouble(amount)), calculateMaturityDate(Integer.parseInt(month)));
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objBackArrowBtnInTDDetailsPage), "Back Arrow Button");
        waitTime(3000);
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objBackArrowBtnInTDDetailsPage), "Back Arrow Button");
        waitTime(3000);
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objBackArrowBtnInTDDetailsPage), "Back Arrow Button");
        waitTime(3000);
        swipe("UP", 2);
        verifyTimeDepositTileContainerAfterNewlyCreatedTD(decimalFormat.format(Double.parseDouble(tdAmountInDashboard) + Double.parseDouble(amount)));
        swipe("DOWN", 2);
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTSAAmount)), "₱" + decimalFormat.format(Double.parseDouble(tsaAmountInDashboard) - Double.parseDouble(amount)), " TSA Amount After Created New TD");
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objHistoryButton), "History Button");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objAccountHistoryPageHeader)), propertyFileReader("AccountHistory", "TimeDeposit"), " Header");
        waitForElementToBePresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTonikSourceAccountNumber), 3, " Tonik source Account Number");
        String[] accountNumber = getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTonikSourceAccountNumber)).split(":");
        String tonikSourceAccountNumber = accountNumber[1].replaceAll("\\s", "");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objFirstHistoryDetailsHader)), "Time Deposit", " Transaction Header");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objFirstHistoryDetailsSubText)), "Money saved", " SubText");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objFirstHistoryDetailsAmount)), "- ₱" + decimalFormat.format(Double.parseDouble(amount)), " Transaction Amount");
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objFirstHistoryDetailsHader), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objFirstHistoryDetailsHader)));
        waitTime(5000);
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTransactionDetailsPageHeader)), propertyFileReader("TransactionDetails", "TimeDeposit"), " Header");
        String[] transactionDetailsValues = {"- ₱" + decimalFormat.format(Double.parseDouble(amount)), tonikSourceAccountNumber, newTdAccountNumber, calculateMaturityDate(0)};
        for (int detail = 0; detail < 4; detail++) {
            assertionValidation(getText(TimeDepositPage.objTransactionDetailsName(platform, propertyFileReader("TransactionDetails" + detail, "TimeDeposit"))), propertyFileReader("TransactionDetails" + detail, "TimeDeposit"), " Text");
            containsValidation(getText(TimeDepositPage.objTransactionDetailsValue(platform, propertyFileReader("TransactionDetails" + detail, "TimeDeposit"))), transactionDetailsValues[detail], propertyFileReader("TransactionDetails" + detail, "TimeDeposit"));
        }
        assertionValidation(getText(TimeDepositPage.objTransactionDetailsName(platform, propertyFileReader("ReferenceNumber", "TimeDeposit"))), propertyFileReader("ReferenceNumber", "TimeDeposit"), " Text");
        verifyElementPresent(TimeDepositPage.objTransactionDetailsValue(platform, propertyFileReader("ReferenceNumber", "TimeDeposit")), getText(TimeDepositPage.objTransactionDetailsValue(platform, propertyFileReader("ReferenceNumber", "TimeDeposit"))) + " : Reference Number");
    }
    /**
     * Reusable method to withdraw Time deposit
     * @throws Exception
     */
    public void withdrawTD() throws Exception {
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objWithDrawEarlyLink), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objWithDrawEarlyLink)));
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objWithdrawEarlyLink), propertyFileReader("WithdrawEarly", "TimeDeposit"));
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objReadyToShredButton), " Shred Button");
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objReadyToShredButton), " Shred Button");
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objReadyToShredButton), " Shred Button");
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objShreddingDoneButton), "Done Button");
    }
    /**
     * Navigate to set up time page
     */
    public void navigateToSetupTimePage() throws Exception {
        navigateToTimeDepositePage();
        waitTime(2000);//Page Load issue to get availed TD Text
        if ((getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objAvailableTdText)).equals("0 of 5 available Time Deposits"))) {
            click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objFirstTDCreated), "First TD");
            withdrawTD();
        }
        waitTime(3000);
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objPlusMarkInMyTDPage), "Start Another TD Buton");
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objIamInterestedButton), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objIamInterestedButton)) + " Button");
    }
    //==================================== Test methods ==============================================================//
    /**
     * Method to Verify if a newly onboarded user can access the Time Deposit without TSA
     * @throws Exception
     */
    public void timeDepositNewlyOnboardedUserWithNoTSA_TDB_TD_001(String password) throws Exception {
        HeaderChildNode("TDB-TD-001, Time Deposit - Verify if a newly onboarded user can access the Time Deposit without TSA");
        verifyAllSalesPageAndNavigateToInvestCalculationPage(password);
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objIamInterestedButton), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objIamInterestedButton)) + " : Button");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objNeedSomeFundPopupHeader), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objNeedSomeFundPopupHeader)) + " Popup Header");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objNeedSomeFundPopupHeader)), propertyFileReader("PopupTitle", "TimeDeposit"), " Popup Description");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objPopupDscription)), propertyFileReader("PopupDescription", "TimeDeposit"), " Popup Description");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objOkayLetsGoBtn), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objOkayLetsGoBtn)) + " Button");
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objOutOfPopup), "Out of Popup page");
        if (verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objBackArrowButton), "Back Button")) {
            verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTimeDepositTextOnContainer), getTextVal(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTimeDepositTextOnContainer), ": DashBoard Text"));
            logger.info("TDB-TD-001, Time Deposit - Verify if a newly onboarded user can access the Time Deposit without TSA");
            extentLoggerPass("TDB-TD-001", "TDB-TD-001, Time Deposit - Verify if a newly onboarded user can access the Time Deposit without TSA");
        }
    }
    /**
     * Method to Verify if user can create the TSA from Time Deposit
     * @throws Exception
     */
    public void verifyUserCanCreateTSAFromTimeDeposit_TDB_TD_002(String password) throws Exception {
        HeaderChildNode("TDB-TD-002, Time Deposit - Verify if user can create the TSA from Time Deposit");
        verifyAllSalesPageAndNavigateToInvestCalculationPage(password);
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objIamInterestedButton), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objIamInterestedButton)) + " : Button");
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objOkayLetsGoBtn), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objOkayLetsGoBtn)) + " Button");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objWeAreAlmostReadyText)).replaceAll("\\r|\\n", " "), propertyFileReader("WeAreReadyMessage", "TimeDeposit"), ": Header Text");
        containsValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTDCreateDescription)), propertyFileReader("WeAreReadyMessageDescription", "TimeDeposit"), " Description");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTopupPageHeader), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTopupPageHeader)));
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objBackArrowBtnInTDDetailsPage), "Back Arrow Button");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objHistoryButton), "History icon");
        assertionValidation(getAttributValue("enabled", TimeDepositPage.getByOSType(platform, TimeDepositPage.objHistoryButton)), "true", ": History icon");
        logger.info("TDB-TD-002, Time Deposit - Verify if user can create the TSA from Time Deposit");
        extentLoggerPass("TDB-TD-002", "TDB-TD-002, Time Deposit - Verify if user can create the TSA from Time Deposit");
    }

    /**
     * Method to Verify if user can verify the email address from Time Deposit
     * @throws Exception
     */
    public void verifyUserCanVerifyTheEmailAddressFromTimeDeposit_TDB_TD_003(String password) throws Exception {
        HeaderChildNode("TDB-TD-003, Time Deposit - Verify if user can verify the email address from Time Deposit");
        verifyAllSalesPageAndNavigateToInvestCalculationPage(password);
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objIamInterestedButton), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objIamInterestedButton)) + " : Button");
        verifyEmailVerificationPopup();
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objSendVerificationButton), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objSendVerificationButton)) + " Button");
        enterOTPAndVerifyNooicePage();
        logger.info("TDB-TD-003, Time Deposit - Verify if user can verify the email address from Time Deposit");
        extentLoggerPass("TDB-TD-003", "TDB-TD-002, Time Deposit - Verify if user can verify the email address from Time Deposit");
    }
    /**
     * Method to Verify the interest rate is calculated properly in Time Deposit calculator screen
     * @throws Exception
     */
    public void verifyTheInterestRateCalculatorScreen_TDB_TD_004(String password, double interest, int month) throws Exception {
        HeaderChildNode("TDB-TD-004, Time Deposit - Verify the interest rate is calculated properly in Time Deposit calculator screen");
        relaunchApp("iOS");
        navigateToInvestPage(password);
        verifyInvestmentCalculationPage();
        click(TimeDepositPage.objInvestmentMonthsText(platform, String.valueOf(month)), String.valueOf(month));
        String investAmountFromPage = getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInvestedAmount)).replaceAll("₱", "").replaceAll(",", "");
        calculateInterestAmount(investAmountFromPage, String.valueOf(month), String.valueOf(interest));
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInterestAmount)), "₱" + interest_amount_string, " : Interest Amount For " + month + " month");
        logger.info("TDB-TD-004, Time Deposit - Verify the interest rate is calculated properly in Time Deposit calculator screen");
        extentLoggerPass("TDB-TD-004", "Time Deposit - Verify the interest rate is calculated properly in Time Deposit calculator screen");
    }
    /**
     * Method to Verify if user can edit the amount & select the installment period
     * @throws Exception
     */
    public void verifyUserCanEditTheAmountAndSelectTheInstallmentMonth_TDB_TD_005(String password, String interest, int month) throws Exception {
        HeaderChildNode("TDB-TD-005, Time Deposit - Verify if user can edit the amount & select the installment period");
        navigateToInvestPage(password);
        editInvestAmount(month, interest);
        logger.info("TDB-TD-005, Time Deposit - Verify if user can edit the amount & select the installment period");
        extentLoggerPass("TDB-TD-005", "Time Deposit - Verify if user can edit the amount & select the installment period");
    }
    /**
     * Method to Verify if user can open a Time Deposit with 6 month term
     * @throws Exception
     */
    public void verifyIfUserCanOpenTimeDepositWith6MonthTerm_TDB_TD_006(String password, String amount, String month, String interest) throws Exception {
        HeaderChildNode("TDB-TD-006, Time Deposit - Verify if user can open a Time Deposit with 6 month term");
        tonikLogin(password);
        waitTime(3000);
        tsaAmountInDashboard = getText(OnBoardingPage.objAvailableBalance).substring(1).replaceAll(",", "");
        if (Double.parseDouble(tsaAmountInDashboard) < 50000.00) {
            click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTopUpButtonInDashBoard), "TopUp Button");
            topupViaGCash(propertyFileReader("TopupAmount", "TimeDeposit"));
        }
        createTDBasedOnMonthAndVerifyTheDetails(password, amount, month, interest);
        logger.info("TDB-TD-006, Time Deposit - Verify if user can open a Time Deposit with 6 month term");
        extentLoggerPass("TDB-TD-006", "Time Deposit - Verify if user can open a Time Deposit with 6 month month term");
    }
    /**
     * Method to Verify if user can open a Time Deposit with 9 month term
     * @throws Exception
     */
    public void verifyIfUserCanOpenTimeDepositWith9MonthTerm_TDB_TD_007(String password, String amount, String month, String interest) throws Exception {
        HeaderChildNode("TDB-TD-007, Time Deposit - Verify if user can open a Time Deposit with 9 month term");
        tonikLogin(password);
        createTDBasedOnMonthAndVerifyTheDetails(password, amount, month, interest);
        logger.info("TDB-TD-007, Time Deposit - Verify if user can open a Time Deposit with 9 month term");
        extentLoggerPass("TDB-TD-007", "Time Deposit - Verify if user can open a Time Deposit with 9 month term");
    }

    /**
     * method to Verify if user can open a Time Deposit with 12 month term
     *
     * @throws Exception
     */
    public void verifyIfUserCanOpenTimeDepositWith12MonthTerm_TDB_TD_008(String password, String amount, String month, String interest) throws Exception {
        HeaderChildNode("TDB-TD-008, Time Deposit - Verify if user can open a Time Deposit with 12 month term");
        tonikLogin(password);
        createTDBasedOnMonthAndVerifyTheDetails(password, amount, month, interest);
        logger.info("TDB-TD-008, Time Deposit - Verify if user can open a Time Deposit with 12 month term");
        extentLoggerPass("TDB-TD-008", "Time Deposit - Verify if user can open a Time Deposit with 12 month term");
    }
    /**
     * Method to Verify if user can open a Time Deposit with 18 month term
     * @throws Exception
     */
    public void verifyIfUserCanOpenTimeDepositWith18MonthTerm_TDB_TD_009(String password, String amount, String month, String interest) throws Exception {
        HeaderChildNode("TDB-TD-009, Time Deposit - Verify if user can open a Time Deposit with 18 month term");
        tonikLogin(password);
        createTDBasedOnMonthAndVerifyTheDetails(password, amount, month, interest);
        logger.info("TDB-TD-009, Time Deposit - Verify if user can open a Time Deposit with 18 month term");
        extentLoggerPass("TDB-TD-009", "Time Deposit - Verify if user can open a Time Deposit with 18 month term");
    }
    /**
     * Method to Verify if user can open a Time Deposit with 24 month term
     * @throws Exception
     */
    public void verifyIfUserCanOpenTimeDepositWith24MonthTerm_TDB_TD_010(String password, String amount, String month, String interest) throws Exception {
        HeaderChildNode("TDB-TD-0010, Time Deposit - Verify if user can open a Time Deposit with 24 month term");
        tonikLogin(password);
        createTDBasedOnMonthAndVerifyTheDetails(password, amount, month, interest);
        logger.info("TDB-TD-0010, Time Deposit - Verify if user can open a Time Deposit with 24 month term");
        extentLoggerPass("TDB-TD-0010", "Time Deposit - Verify if user can open a Time Deposit with 24 month term");
    }

    /**
     * Method to Verify if user can open a Time Deposit with insufficient TSA balance
     * @throws Exception
     */
    public void verifyUserCanOpenTimeDepositWithInsufficientTSABalance_TDB_TD_012(String password, String amount, String month) throws Exception {
        HeaderChildNode("TDB-TD-012, Time Deposit - Verify if user can open a Time Deposit with insufficient TSA balance");
        tonikLogin(password);
        createNewTD(amount, month);
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInsufficientTDMessage)), propertyFileReader("InsufficientErrorMsg", "TimeDeposit"), " Error Message");
        assertionValidation(getAttributValue("enabled", TimeDepositPage.getByOSType(platform, TimeDepositPage.objChangeInvestmentAmountButton)), "true", ": Enable Attribute value");
        logger.info("TDB-TD-012, Time Deposit - Verify if user can open a Time Deposit with insufficient TSA balance");
        extentLoggerPass("TDB-TD-012", "Time Deposit - Verify if user can open a Time Deposit with insufficient TSA balance");
    }
    /**
     * Method to Verify if user can Top up the TSA from Time Deposit
     * @throws Exception
     */
    public void verifyUserCanTopupTSAFromTD_TDB_TD_013(String password, String amount, String month, String interest) throws Exception {
        HeaderChildNode("TDB-TD-013, Time Deposit - Verify if user can Top up the TSA from Time Deposit");
        tonikLogin(password);
        createNewTD("Insufficient", month);
        calculateInterestAmount(amount, month, interest);
        calculateMaturityDate(Integer.parseInt(month));
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInsufficientTDMessage)), propertyFileReader("InsufficientErrorMsg", "TimeDeposit"), " Error Message");
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objChangeInvestmentAmountButton), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objChangeInvestmentAmountButton)) + " :Button");
        topupViaGCash(propertyFileReader("TopupAmount", "TimeDeposit"));
        createNewTD(amount, month);
        verifyWootPage();
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objViewTimeDepositDetailsLink), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objViewTimeDepositDetailsLink)) + " Link");
        verifyTimeDepositDetailsPage(amount + ".00", String.valueOf(total_days), interest, interest_amount_string, String.valueOf(Double.parseDouble(interest_amount_string) + Double.parseDouble(amount)), calculateMaturityDate(Integer.parseInt(month)));
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objBackArrowBtnInTDDetailsPage), "Back Arrow Button");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objWootWootText), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objWootWootText)));
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objDoneBtn), "Done Button");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTopupPageHeader)), propertyFileReader("MyTimeDeposit", "TimeDeposit"), " Page Header");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objNewlyCreatedTD), "Newly Created TD");
        logger.info("TDB-TD-013, Time Deposit - Verify if user can Top up the TSA from Time Deposit");
        extentLoggerPass("TDB-TD-013", "Time Deposit - Verify if user can Top up the TSA from Time Deposit");
    }
    /**
     * method to Verify if user can change the nickname of the Time Deposit
     * @throws Exception
     */
    public void verifyUserCanChangeNicknameOfTheTimeDeposit_TDB_TD_014(String password) throws Exception {
        HeaderChildNode("TDB-TD-014, Time Deposit - Verify if user can change the nickname of the Time Deposit");
        tonikLogin(password);
        navigateToSetupTimePage();
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objNickNameText), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objNickNameText)));
        waitForElementToBePresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTonikALlPageHeaderText), 2, " Page Header");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTonikALlPageHeaderText), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTonikALlPageHeaderText)) + " NickName Edit Page Header");
        clearField(TimeDepositPage.getByOSType(platform, TimeDepositPage.objNickNameTextField), "NickName Text field");
        type(TimeDepositPage.getByOSType(platform, TimeDepositPage.objNickNameTextField), generateRandomString(1), "NickName Textfield");
        if (platform.equalsIgnoreCase("ios")) {
            click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objdoneButton), " Done Button");
            action_clickOnPosition(190,745);
            assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objValidNickNameErrorMessage)), propertyFileReader("EnterValidNickName", "TimeDeposit"), " Error Message");
            clearField(TimeDepositPage.getByOSType(platform, TimeDepositPage.objNickNameTextField), "NickName Text field");
            type(TimeDepositPage.getByOSType(platform, TimeDepositPage.objNickNameTextField), generateRandomString(35), "NickName Textfield");
            String nickName = getAttributValue("value", TimeDepositPage.getByOSType(platform, TimeDepositPage.objNickNameTextField));
            assertionValidationForIntegerType(nickName.length(), 30, " Nick Name Max Character");
        }else {
            click(TimeDepositPage.objTextButton(platform, "Continue"), getText(TimeDepositPage.objTextButton(platform, "Continue")) + " Button");
            assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objValidNickNameErrorMessage)), propertyFileReader("EnterValidNickName", "TimeDeposit"), " Error Message");
            clearField(TimeDepositPage.getByOSType(platform, TimeDepositPage.objNickNameTextField), "NickName Text field");
            type(TimeDepositPage.getByOSType(platform, TimeDepositPage.objNickNameTextField), generateRandomString(35), "NickName Textfield");
            String nickName = getAttributValue("text", TimeDepositPage.getByOSType(platform, TimeDepositPage.objNickNameTextField));
            assertionValidationForIntegerType(nickName.length(), 30, " Nick Name Max Character");
        }
        clearField(TimeDepositPage.getByOSType(platform, TimeDepositPage.objNickNameTextField), "NickName Textfield");
        newNickName = generateRandomString(8);
        type(TimeDepositPage.getByOSType(platform, TimeDepositPage.objNickNameTextField), newNickName, "NickName Textfield");
        if (platform.equalsIgnoreCase("ios")) {
            click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objdoneButton), " Done Button");
            action_clickOnPosition(190,745);
        } else {
            click(TimeDepositPage.objTextButton(platform, "Continue"), getText(TimeDepositPage.objTextButton(platform, "Continue")) + " Button");
        }
        waitForElementToBePresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objSetUpTimeHeader), 3, getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objSetUpTimeHeader)));
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objSetUpTimeHeader), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objSetUpTimeHeader)) + " Page Header");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objNickNameValue)), newNickName, " NickName");
        clickOncreateTdButtonAndCheckItInMyTimeDepositPage();
        assertionValidation(getText(TimeDepositPage.objNewlyCreatedTDBasedOnNickName(platform, newNickName)), newNickName, " NickName");
        logger.info("TDB-TD-014, Time Deposit - Verify if user can change the nickname of the Time Deposit");
        extentLoggerPass("TDB-TD-014", "Time Deposit - Verify if user can change the nickname of the Time Deposit");
    }
    /**
     * method to Verify if user can change the investment amount of the Time Deposit
     * @throws Exception
     */
    public void verifyUserCanChangeTheInvestmentAmountOfTheTimeDeposit_TDB_TD_015(String password, String investedAmount) throws Exception {
        HeaderChildNode("TDB-TD-015, Time Deposit - Verify if user can change the investment amount of the Time Deposit");
        tonikLogin(password);
        navigateToSetupTimePage();
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInvestAmtText), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInvestAmtText)));
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objChangeInvestmentPageHeader), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objChangeInvestmentPageHeader)) + " Page Header");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objChangeInvestmentPageHeader)), propertyFileReader("ChangeYourInvestmentAmount", "TimeDeposit"), "Page Header");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objBackArrowBtnInTDDetailsPage), "Close X Button");
        for (int amount = 0; amount < 2; amount++) {
            clearField(TimeDepositPage.getByOSType(platform, TimeDepositPage.objEditInvestmentAmountTextfield), "Edit Amount TextField");
            type(TimeDepositPage.getByOSType(platform, TimeDepositPage.objEditInvestmentAmountTextfield), propertyFileReader("TDInvestAmt" + amount, "TimeDeposit"), "Edit Amount TextField");
            if (getPlatform().equalsIgnoreCase("ios")) {
                click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objdoneButton), " Done Button");
            }
            click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objContinueButton), "Continue Button");
            containsValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInvestmentAmountRangeErrorMesage)), "Should be between ₱5,000 to ₱100,000", "Amount Range Error Message");
        }
        clearField(TimeDepositPage.getByOSType(platform, TimeDepositPage.objEditInvestmentAmountTextfield), "Edit Amount TextField");
        type(TimeDepositPage.getByOSType(platform, TimeDepositPage.objEditInvestmentAmountTextfield), investedAmount, "Edit Amount TextField");
        if (getPlatform().equalsIgnoreCase("ios")) {
            click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objdoneButton), " Done Button");
        }
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objContinueButton), "Continue Button");
        getAllTheDataFromSetUptimeDepositPage();
        verifyNewlyCreatedTdInMyTimeDepositSheet(investedAmount, tenure);
        logger.info("TDB-TD-015, Time Deposit - Verify if user can change the investment amount of the Time Deposit");
        extentLoggerPass("TDB-TD-015", "Time Deposit - Verify if user can change the investment amount of the Time Deposit");
    }
    /**
     * method to Verify if User can change the term of the Time Deposit
     * @throws Exception
     */
    public void verifyUserCanChangeTheTermOfTheTimeDeposit_TDB_TD_016(String password, String investedAmount, String tenure) throws Exception {
        HeaderChildNode("TDB-TD-016, Time Deposit - Verify if User can change the term of the Time Deposit");
        tonikLogin(password);
        navigateToSetupTimePage();
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInvestAmtText), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInvestAmtText)));
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objChangeInvestmentPageHeader), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objChangeInvestmentPageHeader)) + " Page Header");
        clearField(TimeDepositPage.getByOSType(platform, TimeDepositPage.objEditInvestmentAmountTextfield), "Edit Amount TextField");
        type(TimeDepositPage.getByOSType(platform, TimeDepositPage.objEditInvestmentAmountTextfield), investedAmount, "Edit Amount TextField");
        if (getPlatform().equalsIgnoreCase("ios")) {
            click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objdoneButton), " Done Button");
        }
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objContinueButton), "Continue Button");
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTermText), " Term");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objSelectTenurePageHeader), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objSelectTenurePageHeader)));
        click(TimeDepositPage.objMonthsOption(platform, tenure), " Month Option");
        getAllTheDataFromSetUptimeDepositPage();
        verifyNewlyCreatedTdInMyTimeDepositSheet(investedAmount, tenure);
        logger.info("TDB-TD-016, Time Deposit - Verify if User can change the term of the Time Deposit");
        extentLoggerPass("TDB-TD-016", "Time Deposit - Verify if User can change the term of the Time Deposit");
    }
    /**
     * method to Verify if user can Roll over ON the Time Deposit
     * @throws Exception
     */
    public void verifyUserCanRollOverONTheTimeDeposit_TDB_TD_017(String password) throws Exception {
        HeaderChildNode("TDB-TD-017, Time Deposit - Verify if user can Roll over ON the Time Deposit");
        tonikLogin(password);
        navigateToTimeDepositePage();
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objFirstTDCreated), " Created Time Deposit");
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objRollOverTDToggleButton), "Roll Over TD Toggle Button");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objRollOverOnPopupHeader), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objRollOverOnPopupHeader)));
        containsValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objRollOverOnDescription)), propertyFileReader("RollOverONDescription", "TimeDeposit"), "Roll On Description");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTermsAndConditionText), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTermsAndConditionText)));
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objDoNotOverRollButton), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objDoNotOverRollButton)));
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objRollOverThisTDButton), "Roll Over This Time Deposit Button");
        assertionValidation(getAttributValue("enabled", TimeDepositPage.getByOSType(platform, TimeDepositPage.objRollOverThisTDButton)), "false", ": Enable Attribute value");
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objDoNotOverRollButton), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objDoNotOverRollButton)));
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTimeDepositDetailsHeader), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTimeDepositDetailsHeader)));
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objRollOverTDToggleButton), "Roll Over Toggle Button");
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTermsAndConditionRadioButton), "Accept Terms And Condition Button");
        assertionValidation(getAttributValue("enabled", TimeDepositPage.getByOSType(platform, TimeDepositPage.objRollOverThisTDButton)), "true", ": Enable Attribute value");
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTermsAndConditionRadioButton), "Accept Terms And Condition Button");
        assertionValidation(getAttributValue("enabled", TimeDepositPage.getByOSType(platform, TimeDepositPage.objRollOverThisTDButton)), "false", ": Enable Attribute value");
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTermsAndConditionRadioButton), "Accept Terms And Condition Button");
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objRollOverThisTDButton), "Roll Over This Time Deposit Button");
        if (verifyElementDisplayed(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTdRollOverPopupMessage))) {
            assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTdRollOverPopupMessage)), propertyFileReader("TimeDepositRollOverOn", "TimeDeposit"), "Time Deposit Roll Over On Message");
            logger.info("TDB-TD-017, Time Deposit - Verify if user can Roll over ON the Time Deposit");
            extentLoggerPass("TDB-TD-017", "Time Deposit - Verify if user can Roll over ON the Time Deposit");
        }
    }
    /**
     * Method to Verify if user can Roll over OFF the Time Deposit
     * @throws Exception
     */
    public void verifyUserCanRollOverOFFTheTimeDeposit_TDB_TD_019(String password) throws Exception {
        HeaderChildNode("TDB-TD-019, Time Deposit - Verify if user can Roll over OFF the Time Deposit");
        tonikLogin(password);
        navigateToTimeDepositePage();
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objFirstTDCreated), " Created Time Deposit");
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objRollOverTDToggleButton), "Roll Over TD Toggle Button");
        if (verifyElementDisplayed(TimeDepositPage.getByOSType(platform, TimeDepositPage.objRollOverOnPopupHeader))) {
            click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTermsAndConditionRadioButton), "Accept Terms And Condition Button");
            verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objRollOverThisTDButton), "Roll Over This Time Deposit Button");
            verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objRollOverTDToggleButton), "Roll Over TD Toggle Button");
        }
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objSwitchOffText), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objSwitchOffText)));
        containsValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objRollOverOffDescription)), propertyFileReader("RollOverOFFDescription", "TimeDeposit"), "Roll Off Description");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objKeepItOnButton), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objKeepItOnButton)));
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objIwantToTurnOffRollOverButton), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objIwantToTurnOffRollOverButton)));
        assertionValidation(getAttributValue("enabled", TimeDepositPage.getByOSType(platform, TimeDepositPage.objIwantToTurnOffRollOverButton)), "true", ": Enable Attribute value");
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objKeepItOnButton), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objKeepItOnButton)));
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTimeDepositDetailsHeader), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTimeDepositDetailsHeader)));
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objRollOverTDToggleButton), "Roll Over TD Toggle Button");
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objIwantToTurnOffRollOverButton), "I want To Turn Off Roll Over Button");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTdRollOverPopupMessage)), propertyFileReader("TimeDepositRollOverOff", "TimeDeposit"), "Time Deposit Roll Over Off Message");
        logger.info("TDB-TD-019, Time Deposit - Verify if user can Roll over OFF the Time Deposit");
        extentLoggerPass("TDB-TD-019", "Time Deposit - Verify if user can Roll over OFF the Time Deposit");
    }
    /**
     * Method to Verify if user can open more than 5 Time Deposits
     * @throws Exception
     */
    public void verifyUserCanOpenMoreThan5TimeDeposits_TDB_TD_021(String password) throws Exception {
        HeaderChildNode("TDB-TD-021, Time Deposit - Verify if user can open more than 5 Time Deposits");
        tonikLogin(password);
        navigateToTimeDepositePage();
        waitForElementToBePresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objNewlyCreatedTD), 2, "Created TD List");
        while (!(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objAvailableTdText))).equals("0 of 5 available Time Deposits")) {
            click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objPlusMarkInMyTDPage), "Start Another TD Buton");
            verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objIamInterestedButton), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objIamInterestedButton)) + " Button");
            clickOncreateTdButtonAndCheckItInMyTimeDepositPage();
        }
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objPlusMarkInMyTDPage), "Start Another TD Buton");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objMorethan5TDErrorMsg), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objMorethan5TDErrorMsg)) + " Error Message");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objMorethan5TDErrorMsg)), propertyFileReader("MoreThan5TdErrorMsg", "TimeDeposit"), " Eroor Message");
        assertionValidation(getAttributValue("enabled", TimeDepositPage.objTextButton(platform, "Ok")), "true", ": Enable Attribute value");
        logger.info("TDB-TD-021, Time Deposit - Verify if user can open more than 5 Time Deposits");
        extentLoggerPass("TDB-TD-021", "Time Deposit - Verify if user can open more than 5 Time Deposits");
    }
    /**
     * Method to Verify if user can withdraw the Time Deposit early
     * @throws Exception
     */
    public void verifyUserCanWithdrawTimeDepositEarly_TDB_TD_022(String password, String amount, String month, String interest) throws Exception {
        HeaderChildNode("TDB-TD-022, Time Deposit - Verify if user can withdraw the Time Deposit early");
        tonikLogin(password);
        createNewTD(amount, month);
        calculateInterestAmount(amount, month, interest);
        calculateMaturityDate(Integer.parseInt(month));
        Double totalPayoutAmount = Double.parseDouble(interest_amount_string) + Double.parseDouble(amount);
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objDoneBtn), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objDoneBtn)) + " Button");
        waitForElementToBePresent(TimeDepositPage.objTextButton(platform, "₱" + totalPayoutAmount), 2, "Newly Created Td");
        String availableTimDepositAfterCreatedTD = getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objAvailableTdText)).substring(0, 1);
        verticalSwipeTillElementDisplayed(TimeDepositPage.objTextButton(platform, "₱" + decimalFormat.format(totalPayoutAmount)));
        verifyElementPresentAndClick(TimeDepositPage.objTextButton(platform, "₱" + decimalFormat.format(totalPayoutAmount)), " Newly Created TD");
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objWithDrawEarlyLink), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objWithDrawEarlyLink)));
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objChangeOfHeartPageHeader)), propertyFileReader("ChangeOfHeart", "TimeDeposit"), " Header");
        containsValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objWithDrawPageDescription)), propertyFileReader("WithdrawDescription", "TimeDeposit"), " Description");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objYourPayoutAmount)), propertyFileReader("YourPayout", "TimeDeposit"), " Text");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objYourPayOutAmountInWithDrawPage)), "₱" + decimalFormat.format(Double.parseDouble(amount)), "Total Withdraw Payout Amount");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objOriginalInvestmentText)), propertyFileReader("OriginalInvestment", "TimeDeposit"), " : Original Investment text");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objOriginalInvestmentAmount)), "₱" + decimalFormat.format(Double.parseDouble(amount)), " : Original Investment Amount");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInterestYouCanEarnText)), propertyFileReader("InterestYouCanEarn", "TimeDeposit"), " Text");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objInterestYouCanEarnAmount)), "₱" + decimalFormat.format(Double.parseDouble(interest_amount_string)), " Interest Amount");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objWithdrawDescription)), propertyFileReader("TDDetailsPageDescription", "TimeDeposit"), " Description");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objSeeTDDetailsLink)), propertyFileReader("SeeTimeDepsitDetails", "TimeDeposit"), " Link");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objWithdrawEarlyLink)), propertyFileReader("WithdrawEarly", "TimeDeposit"), " Text");
        assertionValidation(getAttributValue("enabled", TimeDepositPage.getByOSType(platform, TimeDepositPage.objKeepMyTimeDepositButton)), "true", ": Enable Attribute value");
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objKeepMyTimeDepositButton), "Keep My TimeDeposit Button");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objRollOverTimeDeposiText), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objRollOverTimeDeposiText)));
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objWithDrawEarlyLink), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objWithDrawEarlyLink)));
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objSeeTDDetailsLink), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objSeeTDDetailsLink)));
        verifyTimeDepositDetailsPage(amount + ".00", String.valueOf(total_days), interest, interest_amount_string, String.valueOf(totalPayoutAmount), calculateMaturityDate(Integer.parseInt(month)));
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objBackArrowBtnInTDDetailsPage), "Back Arrow Button");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTonikALlPageHeaderText)), propertyFileReader("ChangeOfHeart", "TimeDeposit"), " Header");
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objWithdrawEarlyLink), propertyFileReader("WithdrawEarly", "TimeDeposit"));
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objGettingReadyToShredpageHeader)), propertyFileReader("ShredHeader", "TimeDeposit"), " Shred Page");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objGettingReadyToShredpageDescription)), propertyFileReader("ShredPageDescription", "TimeDeposit"), " Description");
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objReadyToShredButton), " Shred Button");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objGoingOncePageHeader)), propertyFileReader("ShredPage0", "TimeDeposit"), " Page1 Description");
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objReadyToShredButton), " Shred Button");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objGoingTwicePageHeader)), propertyFileReader("ShredPage1", "TimeDeposit"), " Page2 Description");
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objReadyToShredButton), " Shred Button");
        containsValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objDoneShreddingPageHeader)), propertyFileReader("ShredPage2", "TimeDeposit"), " Page2 Description");
        containsValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objDoneShreddingPageDescripion)), propertyFileReader("DoneShreddingDescription", "TimeDeposit"), " Done Shredding Page Description");
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objShreddingDoneButton), "Done Button");
        waitForElementToBePresent(TimeDepositPage.objWithDrawTDUnderTDArchives(platform, "₱" + decimalFormat.format(Double.parseDouble(amount))), 2, "Newly WithDraw Td");
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objAvailableTdText)), (Integer.parseInt(availableTimDepositAfterCreatedTD) + 1) + getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objAvailableTdText)).substring(1), " Available Text");
        verticalSwipeTillElementDisplayed(TimeDepositPage.objWithDrawTDUnderTDArchives(platform, "₱" + decimalFormat.format(Double.parseDouble(amount))));
        verifyElementPresent(TimeDepositPage.objWithDrawTDUnderTDArchives(platform, "₱" + decimalFormat.format(Double.parseDouble(amount))), "Newly WithDraw Td");
        assertionValidation(getText(TimeDepositPage.objEarlyWithDrawTextBasedOnNewWithdraw(platform, "₱" + decimalFormat.format(Double.parseDouble(amount)))), propertyFileReader("EarlyWithdrawn", "TimeDeposit"), " Text");
        verifyElementPresentAndClick(TimeDepositPage.getByOSType(platform, TimeDepositPage.objBackArrowBtnInTDDetailsPage), "Back Arrow Button");
        verticalSwipeTillElementDisplayed(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTimeDepositTextOnContainer));
        verifyTimeDepositTileContainerAfterNewlyCreatedTD(decimalFormat.format((Double.parseDouble(tdAmountInDashboard) + Double.parseDouble(amount)) - Double.parseDouble(amount)));
        swipe("DOWN", 2);
        assertionValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objTSAAmount)), "₱" + decimalFormat.format((Double.parseDouble(tsaAmountInDashboard) - Double.parseDouble(amount)) + Double.parseDouble(amount)), "TSA Amount After Early Withdraw");
        logger.info("TDB-TD-022, Time Deposit - Verify if user can withdraw the Time Deposit early");
        extentLoggerPass("TDB-TD-022", "Time Deposit - Verify if user can withdraw the Time Deposit early");
    }
    /**
     * Method to Verify if BKYC user can open the Time Deposit
     * @throws Exception
     */
    public void verifyBKYCUserCanOpenTheTimeDeposit_TDB_TD_024(String password) throws Exception {
        HeaderChildNode("TDB-TD-024, Time Deposit - Verify if BKYC user can open the Time Deposit");
        verifyAllSalesPageAndNavigateToInvestCalculationPage(password);
        click(TimeDepositPage.getByOSType(platform, TimeDepositPage.objIamInterestedButton), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objIamInterestedButton)) + " : Button");
        verifyElementPresent(TimeDepositPage.getByOSType(platform, TimeDepositPage.objPopupDescription), getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objPopupDescription)) + " Error Message");
        containsValidation(getText(TimeDepositPage.getByOSType(platform, TimeDepositPage.objPopupDescription)), propertyFileReader("BKYCError_Msg", "TimeDeposit"), " Error Message");
        assertionValidation(getAttributValue("enabled", TimeDepositPage.getByOSType(platform, TimeDepositPage.objUnlockTDNowButton)), "true", ": Unlock TD Now Button Enable Attribute value");
        logger.info("TDB-TD-024, Time Deposit - Verify if BKYC user can open the Time Deposit");
        extentLoggerPass("TDB-TD-024", "Time Deposit - Verify if BKYC user can open the Time Deposit");
    }
}