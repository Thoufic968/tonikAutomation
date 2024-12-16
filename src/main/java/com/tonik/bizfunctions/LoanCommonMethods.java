package com.tonik.bizfunctions;

import com.driverInstance.DriverManager;
import com.jcraft.jsch.JSchException;
import com.tonik.pageObject.*;
import com.tonik.utility.ExtentReporter;
import com.tonik.utility.LoggingUtils;
import com.tonik.utility.Utilities;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.tonik.utility.DB_Utilites.*;
import static com.tonik.utility.ExtentReporter.extentLogger;
import static com.tonik.utility.Utilities.*;

public class LoanCommonMethods extends BaseClass {
    static LoggingUtils logger = new LoggingUtils();
    String platform = Utilities.getPlatform();
    String loanAmount = null;
    String installmentTerms = null;
    String addOnRate = null;
    String installAmount = null;
    SendMoneyModule sendMoneyModule = new SendMoneyModule();
    FlexPivotWithoutVASModule flexPivotWithoutVASModule = new FlexPivotWithoutVASModule();
    /**
     * Common method to calculate EMI
     * @param amount
     * @param time
     * @param interestRate
     * @return
     */
    public static String calculateEMI(double amount, double time, double interestRate) {
        double rate = interestRate / 100;
        double emi = (amount * rate * Math.pow(1 + rate, time)) / (Math.pow(1 + rate, time) - 1);
        return String.format("%.2f", emi);
    }
    /**
     * Common method to remove special Character
     * @param amount
     * @param decimal
     * @return
     */
    public static String removeSpecialCharacter(String amount, boolean decimal) {
        amount = amount.replace("₱", "").replace(",", "").trim();
        amount = amount.replace("?", "").replace(",", "").trim();
        if (decimal) {
            amount = amount.replace(".00", "").trim();
        }
        return amount;
    }
    /**
     * Common method to verify VAS EMI Verification
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
        String[] expectedTenures = {"6", "9", "12"};
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
                        String perMonthInterest = calculateEMI(Double.parseDouble(amount), Double.parseDouble(tenure.getText()), interestRate);
                        for (IOSElement installments : montlyInstallMentAmount) {
                            tenure.click();
                            waitTime(2000);//Hard wait required for execution
                            installmentAmount = installments.getText();
                            removeSpcl = removeSpecialCharacter(installmentAmount, false);
                            containsValidation(removeSpcl, perMonthInterest, "InterestRate");
                            for (IOSElement payhingamontexpected : payhingAmount) {
                                String actualNumbers = extractNumbers(payhingamontexpected.getText());
                                double expectedNumbers = round(Double.parseDouble(payHinga(perMonthInterest)), 2);
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
                        String perMonthInterest = calculateEMI(Double.parseDouble(amount), Double.parseDouble(tenure.getText()), interestRate);
                        for (WebElement installments : montlyInstallMentAmount) {
                            tenure.click();
                            waitTime(2000);//Hard wait required for execution
                            installmentAmount = installments.getText();
                            removeSpcl = removeSpecialCharacter(installmentAmount, false);
                            valueValidation(removeSpcl, perMonthInterest, "InterestRate", "contains");
                            for (WebElement payhingamontexpected : payhingAmount) {
                                String actualNumbers = extractNumbers(payhingamontexpected.getText());
                                double expectedNumbers = round(Double.parseDouble(payHinga(perMonthInterest)), 2);
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
     * Common method to verify big Loan EMI Verification
     * @param amount
     * @param tenurepath
     * @param monthlyinstallmentpath
     * @throws Exception
     */
    public static void bigLoanEmiVerification(String amount, By tenurepath, By monthlyinstallmentpath) throws Exception {
        String installmentAmount = null;
        String removeSpcl = null;
        List<WebElement> tenures = findElements(tenurepath);
        List<WebElement> montlyInstallMentAmount = findElements(monthlyinstallmentpath);
        String[] expectedTenures = {"12", "24", "36", "48", "60"};
        for (WebElement tenure : tenures) {
            logger.info("For " + amount + " Amount the available tenures are : " + tenure.getText());
            extentLogger("tenure", "For " + amount + " Amount the available tenures are : " + tenure.getText());
            for (int i = 0; i < expectedTenures.length; i++) {
                if (tenure.getText().contains(expectedTenures[i])) {
                    valueValidation(tenure.getText(), expectedTenures[i], "Month", "contains");
                    String perMonthInterest = bigLoanEMICalculator(Double.parseDouble(amount), Integer.parseInt(tenure.getText()), Double.parseDouble(propertyFileReader("interestRrate", "BigLoan")), Double.parseDouble(propertyFileReader("feeInterest", "BigLoan")));
                    for (WebElement installments : montlyInstallMentAmount) {
                        tenure.click();
                        waitTime(2000);//Hard wait required for execution
                        installmentAmount = installments.getText();
                        removeSpcl = removeSpecialCharacter(installmentAmount, false);
                        valueValidation(removeSpcl, perMonthInterest, "InterestRate", "contains");
                    }
                    break;
                }
            }
        }
    }
    /**
     * Common method to calculate payHinga amount
     * @param emi
     * @return
     */
    public static String payHinga(String emi) {
        double amount = (Double.parseDouble(emi) * Double.parseDouble(propertyFileReader("payHingaInterestRate","QuickLoanWithVas"))) / 100;
        return Double.toString(amount);
    }
    /**
     * Common method format To Two Decimal Places
     * @param input
     * @return
     */
    public static String formatToTwoDecimalPlaces(String input) {
        try {
            double value = Double.parseDouble(input);
            DecimalFormat df = new DecimalFormat("#0.00");
            return df.format(value);
        } catch (NumberFormatException e) {
            return "Invalid input for decimal";
        }
    }
    /**
     * Common method to extract Numbers
     * @param input
     * @return
     */
    public static String extractNumbers(String input) {
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            sb.append(matcher.group()).append(" ");
        }
        return sb.toString().trim();
    }
    /**
     * Common method to monthly Add On Calculation
     * @param emiAmount
     * @param loanAmount
     * @param tenure
     * @return
     */
    public static double monthlyAddOnCalculation(double emiAmount, double loanAmount, int tenure) {
        double totalPayment = emiAmount * tenure;
        double totalOverpayment = totalPayment - loanAmount;
        double overpaymentPercent = (totalOverpayment / loanAmount) * 100;
        double monthlyAddOn = overpaymentPercent / tenure;
        return round(monthlyAddOn, 2);
    }
    /**
     * Common method to round
     * @param value
     * @param places
     * @return
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        DecimalFormat df = new DecimalFormat("#.##");
        df.setMaximumFractionDigits(places);
        df.setMinimumFractionDigits(places);
        return Double.parseDouble(df.format(value));
    }
    /**
     * Common method to payhinga Payment Holiday
     * @param loanAmount
     * @return
     */
    public static double payhingaPaymentHoliday(String loanAmount) {
        double value = Double.parseDouble(loanAmount) * 1.2;
        value = Double.parseDouble(formatNumber(value));
        return value;
    }
    /**
     * Common method to format Number
     * @param number
     * @return
     */
    public static String formatNumber(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(number);
    }
    /**
     * Common  method to total Monthly Installment
     * @param monthlyInstallmentAmount
     * @param payHingaAmount
     * @return
     */
    public static String totalMonthlyInstallment(String monthlyInstallmentAmount, String payHingaAmount) {
        double total = Double.parseDouble(monthlyInstallmentAmount) + Double.parseDouble(payHingaAmount);
        return String.valueOf(total);
    }
    /**
     * Common method to document Stamp Fee Calculator
     * @param amount
     * @param time
     * @return
     */
    public static String documentStampFeeCalculator(double amount, double time) {
        double tenure = time;
        double tenureInDays = tenure * 30.44;
        double taxAmount;
        if (tenure >= 12) {
            taxAmount = Math.ceil((amount / 200) * 1.5);
        } else {
            taxAmount = Math.ceil((amount / 200) * 1.5) * (tenureInDays / 365);
        }
        taxAmount = Math.round(taxAmount);
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(taxAmount);
    }
    /**
     * Common method to return Date
     * @param month
     * @return
     */
    public static String calculateDate(int month) {
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Manila"));
        String formattedToday = formatDate(today, "dd MMM yyyy");
        LocalDate newDate = addMonthsToDate(today, month);
        String formattedNewDate = formatDate(newDate, "dd MMM yyyy");
        return formattedNewDate;
    }
    /**
     * Common method to return One Day Back Date
     * @param days
     * @return
     */
    public static String calculateOneDayBackDate(int days) {
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Manila"));
        String formattedToday = formatDate(today, "MMMM dd yyyy");
        LocalDate newDate = addDaysToDate(today, days);
        return formatDate(newDate.plusMonths(1), "MMMM dd yyyy");
    }

    /**
     * Common method to return Format Date
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(LocalDate date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }
    /**
     * Common method to return Add Days To Date
     * @param date
     * @param days
     * @return
     */
    public static LocalDate addDaysToDate(LocalDate date, int days) {
        return date.plusDays(days);

    }
    /**
     * Common method to return Add Months To Date
     * @param date
     * @param months
     * @return
     */
    public static LocalDate addMonthsToDate(LocalDate date, int months) {
        return date.plusMonths(months);

    }
    /**
     * Common method to return To Days Date
     * @return
     */
    public static String todaysDate(){
        Date currentDate = new Date();
        SimpleDateFormat date = new SimpleDateFormat("dd MMM yyyy");
        date.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
        String formattedDate = date.format(currentDate);
        return formattedDate;
    }
    TimeZone timeZone = TimeZone.getTimeZone("Asia/Singapore");
    /**
     * Common method to return Todays Date MMMM
     * @return
     */
    public static String todaysDateMMMM() {
        ZonedDateTime today = ZonedDateTime.now(ZoneId.of("Asia/Singapore"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return today.format(formatter);
    }
    /***
     * Common method to return One Day Back Date
     * @return
     */
    public static String oneDayBackDate() {
        ZonedDateTime today = ZonedDateTime.now(ZoneId.of("Asia/Singapore"));
        ZonedDateTime yesterday = today.minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return yesterday.format(formatter);
    }
    /**
     * Common method to return One Day Back Date MMM
     * @return
     */
    public static String oneDayBackDateMMM() {
        ZonedDateTime today = ZonedDateTime.now(ZoneId.of("Asia/Singapore"));
        ZonedDateTime yesterday = today.minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return yesterday.format(formatter);
    }
    /**
     * Common method to return Next Date MMM
     * @return
     */
    public static String nextDateMMM() {
        ZonedDateTime today = ZonedDateTime.now(ZoneId.of("Asia/Singapore"));
        ZonedDateTime yesterday = today.plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return yesterday.format(formatter);
    }
    /***
     * Common method to calculate Extract Date
     * @return
     */
    public static String extractedDate() {
        String todaysDate = todaysDate();
        todaysDate = todaysDate.substring(0, 2);
        int todaysDate1 = Integer.parseInt(todaysDate);
        return String.valueOf(todaysDate1);
    }
    /***
     * Common method to return Extract Date
     * @return
     */
    public String extractedOneDayBackDate(int days) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterday = now.minusDays(days);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return yesterday.format(formatter);
    }
    /***
     * Common method to return  Date
     * @return
     */
    public String todayDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
    /**
     * Common method to return Extract Date
     * @param days
     * @return
     */
    public String extractUpcomingDate(int days) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterday = now.plusDays(days);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return yesterday.format(formatter);
    }
    /**
     * Common method to calculate Big Loan EMI Verification
     * @param amount
     * @param tenure
     * @param interestRate
     * @param feeInterest
     * @return
     */
    public static String bigLoanEMICalculator(double amount, int tenure, double interestRate, double feeInterest) {
        double rate = interestRate / (12 * 100);
        double adminFee = amount * (feeInterest / 100);
        double emi = (amount * rate * Math.pow(1 + rate, tenure)) / (Math.pow(1 + rate, tenure) - 1);
        double calculatedEmi = emi + adminFee;
        return String.format("%.2f", calculatedEmi);
    }
    /**
     * Common method to Remove Special Characters
     * @param input
     * @return
     */
    public static String removeSpecialCharacters(String input) {
        // Remove special characters except period using regular expression
        String result = input.replaceAll("[^a-zA-Z0-9.]", "");
        return result;
    }
    /**
     * Common method return Generate Random TIN Number
     * @return
     */
    public String generateRandomTINNUMBER() {
        String strNumbers = "123456789";
        Random rnd = new Random();
        StringBuilder strRandomNumber = new StringBuilder(9);
        strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));
        String s1 = strRandomNumber.toString().toUpperCase();
        for (int i = 1; i < 7; i++) {
            strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));
        }
        return "5" + s1 + strRandomNumber;
    }
    /**
     * Common method to Return Date Format
     * @return
     */
    public static String returnDate() {
        ZonedDateTime today = ZonedDateTime.now(ZoneId.of("Asia/Singapore"));
        ZonedDateTime futureDate = today.plusDays(-35 - 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return futureDate.format(formatter);
    }
    /***
     * Common method to validate What Do You Need It For Screen
     * @param sReason
     * @throws Exception
     */
    public void whatDoYouNeedItForScreen(String sReason) throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNextBtn), 5, "What Do You Need It For Page");
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"))) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("WhatDoYouNeedItForPage", "QuickLoanWithVas"), ": page title");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("WhatDoYouNeedItForPageSubTitle", "QuickLoanWithVas"), ": page subtitle");
            assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "false", ": Enable Attribute value");
            verifyElementPresentAndClick(QuickLoanWithVasPage.objReasonForLoan(platform, sReason), getTextVal(QuickLoanWithVasPage.objReasonForLoan(platform, sReason), ": Reason"));
            assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "true", ": Enable Attribute value");
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), ": button"));
        }
    }
    /**
     * Common method to validate Field of Work
     * @param fieldOfWork
     * @throws Exception
     */
    public void fieldOfWork(String fieldOfWork) throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFieldOfWorkPage), 5, "Field of work page");
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"))) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("FieldOfWorkPage", "QuickLoanWithVas"), ": page title");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("FieldOfWorkPageSubtitle", "QuickLoanWithVas"), ": page subtitle");
            assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "false", ": Enable Attribute value");
            waitTime(2000);//Hard wait required for execution
            type(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFieldOfWokSearchField), fieldOfWork, "Field name");
            verifyElementPresentAndClick(QuickLoanWithVasPage.objList(platform, 1), getTextVal(QuickLoanWithVasPage.objList(platform, 1), ": Field Of Work"));
            if (platform.equalsIgnoreCase("ios")) {
                click(QuickLoanWithVasPage.objList(platform, 1), getTextVal(QuickLoanWithVasPage.objList(platform, 1), ": Field Of Work"));
            }
            assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "true", ": Enable Attribute value");
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), getTextVal(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), ": button"));
        }
    }
    /**
     * Common method to validate Select Occupation
     * @param occupation
     * @throws Exception
     */
    public void selectOccupation(String occupation) throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objWhatsYourJobRolePage), 5, "What's your Job Role? Screen");
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"))) {
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("WhatsYourJobRolePage", "QuickLoanWithVas"), ": page title");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("WhatsYourJobRolePageSubtitle", "QuickLoanWithVas"), ": page subtitle");
            assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "false", ": Enable Attribute value");
            type(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFieldOfWokSearchField), occupation, "occupation input field");
            verifyElementPresentAndClick(QuickLoanWithVasPage.objList(platform, 1), getTextVal(QuickLoanWithVasPage.objList(platform, 1), ": Occupation"));
            if (platform.equalsIgnoreCase("ios")) {
                click(QuickLoanWithVasPage.objList(platform, 1), getTextVal(QuickLoanWithVasPage.objList(platform, 1), ": Field Of Work"));
            }
            assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "true", ": Enable Attribute value");
            click(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), getTextVal(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), ": button"));
        }
    }

    /**
     * Common method to validate Select Industry
     * @param industry
     * @param subIndustry
     * @throws Exception
     */
    public void selectIndustry(String industry, String subIndustry) throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIndustrySelectionScreen), 5, "Industry Selection screen");
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"))) {
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("IndustrySelectionScreen", "QuickLoanWithVas"), ": page title");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("IndustrySelectionScreenSubTitle", "QuickLoanWithVas"), ": page subtitle");
            waitTime(3000);//Hard wait required for execution
            verifyElementPresentAndClick(QuickLoanWithVasPage.objSelectIndustry(platform, industry), getTextVal(QuickLoanWithVasPage.objSelectIndustry(platform, industry), ": industry"));
            assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "false", ": Enable Attribute value");
            verifyElementPresentAndClick(QuickLoanWithVasPage.objSelectSubIndustryRadioButton(platform, subIndustry), getTextVal(QuickLoanWithVasPage.objSelectIndustry(platform, subIndustry), ": subIndustry"));
            assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "true", ": Enable Attribute value");
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), getTextVal(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), ": button"));
        }
    }

    /**
     * Common method to validate Select Marital Status
     * @param status
     * @throws Exception
     */
    public void selectMaritalStatus(String status) throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMaritalStatusPage), 5, "Marital status selection screen");
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"))) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("MaritalStatus", "QuickLoanWithVas"), ": page title");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("MaritalStatusSubTitle", "QuickLoanWithVas"), ": page subtitle");
            assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "false", ": Enable Attribute value");
            verifyElementPresentAndClick(QuickLoanWithVasPage.objSelectMaritalStatus(platform, status), getTextVal(QuickLoanWithVasPage.objSelectMaritalStatus(platform, status), ": marital status"));
            assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "true", ": Enable Attribute value");
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), getTextVal(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), ": button"));
        }
    }

    /**
     * Common method to validate Select Kids Or Dependents
     * @param kidsDependent
     * @throws Exception
     */
    public void selectKidsOrDependents(String kidsDependent) throws Exception {
        waitTime(3000);
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"))) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("SelectNoOfKids", "QuickLoanWithVas"), ": page title");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("SelectNoOfKidsSubTitle", "QuickLoanWithVas"), ": page subtitle");
            if (platform.equalsIgnoreCase("ios")) {
                List<IOSElement> values = DriverManager.getAppiumDriver().findElements(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objList));
                for (int kids = 1; kids <= values.size(); kids++) {
                    assertionValidation(getText(QuickLoanWithVasPage.objListFromSelectIndustry(platform, kids)), propertyFileReader("DependentsOption" + kids, "QuickLoanWithVas"), ": Dependents Option " + kids);
                }
            } else {
                List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objList));
                for (int kids = 1; kids <= values.size(); kids++) {
                    assertionValidation(getText(QuickLoanWithVasPage.objList(platform, kids)), propertyFileReader("DependentsOption" + kids, "QuickLoanWithVas"), ": Dependents Option " + kids);
                }
            }
            assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "false", ": Enable Attribute value");
            verifyElementPresentAndClick(QuickLoanWithVasPage.objSelectMaritalStatus(platform, kidsDependent), getTextVal(QuickLoanWithVasPage.objSelectMaritalStatus(platform, kidsDependent), ": Kids/Dependents"));
            assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "true", ": Enable Attribute value");
            click(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), getTextVal(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), ": button"));
        }
    }

    /**
     * Common method to validate Select Highest Educational Attainment
     * @param higherEducation
     * @throws Exception
     */
    public void selectHighestEducationalAttainment(String higherEducation) throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHighestEducationalAttainment), 5, "Highest Educational attainment");
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"))) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("HighestEducationalAttainment", "QuickLoanWithVas"), ": page title");
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("HighestEducationalAttainmentSubTitle", "QuickLoanWithVas"), ": page subtitle");
            if (platform.equalsIgnoreCase("ios")) {
                List<IOSElement> values = DriverManager.getAppiumDriver().findElements(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objList));
                for (int education = 1; education <= values.size(); education++) {
                    assertionValidation(getText(QuickLoanWithVasPage.objListFromSelectIndustry(platform, education)), propertyFileReader("EducationalAttainment" + education, "QuickLoanWithVas"), ": Educational Attainment " + education);
                }
            } else {
                List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objList));
                for (int education = 1; education <= values.size(); education++) {
                    assertionValidation(getText(QuickLoanWithVasPage.objListFromSelectIndustry(platform, education)), propertyFileReader("EducationalAttainment" + education, "QuickLoanWithVas"), ": Educational Attainment " + education);
                }
            }
            assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "false", ": Enable Attribute value");
            verifyElementPresentAndClick(QuickLoanWithVasPage.objSelectMaritalStatus(platform, higherEducation), getTextVal(QuickLoanWithVasPage.objSelectMaritalStatus(platform, higherEducation), ": Education"));
            assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "true", ": Enable Attribute value");
            click(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), getTextVal(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), ": button"));
        }
    }

    /**
     * Common method to validate Leaving Soon Page Validation
     * @throws Exception
     */
    public void leavingSoonPageValidation() throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLeaveIcon), 5, "Leave application Icon");
        waitTime(3000);//Hard wait required for execution
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLeaveIcon), "Leave Icon");
        waitTime(3000);
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"))) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("LeavingSoonPage", "QuickLoanWithVas"), ": page title");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("LeavingSoonPageSubtitle", "QuickLoanWithVas"), ": page subtitle");
            if (platform.equalsIgnoreCase("ios")) {
                List<IOSElement> values = DriverManager.getAppiumDriver().findElements(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLeavingSoonReason));
                for (int info = 0; info < values.size(); info++) {
                    String displayedItem = values.get(info).getText();
                    logger.info("Leaving Reason : '" + displayedItem + "' is displayed");
                    ExtentReporter.extentLogger(" ", "Leaving Reason : '" + displayedItem + "' is displayed");
                }
            } else {
                List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLeavingSoonReason));
                for (int info = 0; info < values.size(); info++) {
                    String displayedItem = values.get(info).getText();
                    logger.info("Leaving Reason : '" + displayedItem + "' is displayed");
                    ExtentReporter.extentLogger(" ", "Leaving Reason : '" + displayedItem + "' is displayed");
                }
            }
        }
        assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)), "false", ": Enable Attribute value");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back button");
    }

    /**
     * Common method to validate Input Income Amount And Next
     * @param sAmount
     * @param exceptedError
     * @throws Exception
     */
    public void inputIncomeAmountAndNext(String sAmount, String exceptedError) throws Exception {
        if (platform.equalsIgnoreCase("ios")) {
            clearField(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMonthlyIncomeField), "Income input field");
        }
        type(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMonthlyIncomeField), sAmount, "Income input field");
        hideKeyboard();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), getTextVal(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), ": button"));
        if (verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInvalidIncomeMsg))) {
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInvalidIncomeMsg)), exceptedError, ": Error message");
        }
    }

    /**
     * Common method to Update Firstname Lastname And RelationShip of Reference contact method
     * @throws Exception
     */
    public void updateFirstNameLastNameAndRelationShip() throws Exception {
        type(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFirstNameInputField), generateRandomString(7), "First name input field");
        type(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLastNameInputField), generateRandomString(6), "Last name input field");
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objRelationShipDropdown), "Relationship dropdown");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objRelationShipPopup), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objRelationShipPopup), "Popup"));
        click(QuickLoanWithVasPage.objSelectRelationShip(platform, propertyFileReader("RelationShip", "QuickLoanWithVas")), getTextVal(QuickLoanWithVasPage.objSelectRelationShip(platform, propertyFileReader("RelationShip", "QuickLoanWithVas")), ": Relationship"));
    }

    /**
     * Common method to validate Is This YourCurrent Address Confirmation
     * @throws Exception
     */
    public void isThisYourCurrentAddressConfirmation() throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIsThisYourCurrentAddPage), 5, "Is this your current address? Screen");
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"))) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("IsThisYourCurrentAddPage", "QuickLoanWithVas"), ": page title");
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("IsThisYourCurrentAddPageSubtitle", "QuickLoanWithVas"), ": page subtitle");
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objYesThisMyAddressBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objYesThisMyAddressBtn), ": button"));
        }
    }

    /**
     * Common method to validate Input Income Company And TIN Details
     * @param tinNumber
     * @param income
     * @throws Exception
     */
    public void inputIncomeCompanyAndTINDetails(String tinNumber, String income) throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMonthlyIncomeScreen), 5, "Monthly Income Screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("MonthlyIncomeScreen", "QuickLoanWithVas"), ": page title");
        inputIncomeAmountAndNext(income, "");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInputCompanyScreen), 3, "Where do you work, luv? Screen");
        if(verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInputCompanyScreen))) {
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageTitle), ": page title"));
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageTitle)), propertyFileReader("InputCompanyScreen", "QuickLoanWithVas"), ": page title");
            type(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objCompanyInputField), generateRandomString(7), "Company Input Field");
            verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objUpdateButton), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objUpdateButton), ": button"));
        }
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTINInputField), 5, "TIN Input screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("TINScreen", "QuickLoanWithVas"), ": page title");
        type(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTINInputField), tinNumber, "TIN Input field");
        verifyElementPresentAndClick(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), getTextVal(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), ": button"));
    }

    /**
     * Common method to Input Mobile Number And Next for selecting Secondary Contact reference
     * @param mobileNumber
     * @throws Exception
     */
    public void inputMobileNumberAndNext(String mobileNumber) throws Exception {
        if (platform.equalsIgnoreCase("ios")) {
            clearField(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSecondaryContactInputField), "Secondary contact input field");
        }
        type(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSecondaryContactInputField), mobileNumber, "Secondary contact input field");
        click(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), getTextVal(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), ": button"));
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
     * Common method to return Get Receiver Name
     * @param mobileNumber
     * @return
     * @throws Exception
     */
    public String getReceiverName(String mobileNumber) throws Exception {
        return selectQuery("customer", "SELECT nick_name FROM customer.tdbk_customer_mtb where mobile_no='" + mobileNumber + "';");
    }

    /**
     * Common method to return Get Email Address
     * @param mobileNumber
     * @return
     * @throws Exception
     */
    public String getEmailAddress(String mobileNumber) throws Exception {
        return selectQuery("customer", "SELECT email FROM customer.tdbk_customer_mtb where mobile_no='" + mobileNumber + "';");
    }

    /**
     * Common method to return OTP
     * @param email
     * @return
     * @throws Exception
     */
    public String getOTP(String email) throws Exception {
        return selectQuery("customer", "SELECT otp FROM customer.tdbk_email_otp_track where email_id='" + email + "';");
    }

    /**
     * Common method to return Application Status
     * @param userId
     * @return
     * @throws JSchException
     * @throws SQLException
     */
    public String applicationStatus(String userId) throws JSchException, SQLException {
        return selectQuery("customer", "SELECT applicationStatus FROM loans.tdbk_digital_loan_application where userId ='" + userId + "'and applicationStatus='INPROGRESS';");
    }

    /**
     * Common method to return Application Status Approved
     * @param userId
     * @return
     * @throws JSchException
     * @throws SQLException
     */
    public String applicationStatusApproved(String userId) throws JSchException, SQLException {
        return selectQuery("customer", "SELECT applicationStatus FROM loans.tdbk_digital_loan_application where userId ='" + userId + "'and applicationStatus='APPROVED';");
    }

    /**
     * Common method to return Update Sweep Status
     * @param loanId
     * @param repaymentNumber
     * @return
     */
    public String updateSweepStatus(String loanId, String repaymentNumber) {
        return updateQuery("update loans.tdbk_loan_repayment_schedule set sweepStatus='Normal' where loanId='" + loanId + "' and repaymentNumber='" + repaymentNumber + "';");
    }

    /**
     * Common method to return Update Normal Paid Payments
     * @param digitalLoanAccountId
     * @param normalPaidPayments
     * @return
     */
    public String updateNormalPaidPayments(String digitalLoanAccountId, String normalPaidPayments) {
        return updateQuery("update loans.tdbk_vas_details set normal_paid_payments='" + normalPaidPayments + "' where digitalLoanAccountId='" + digitalLoanAccountId + "';");
    }

    /**
     * Common method to return Update Eligible Paid Holidays
     * @param digitalLoanAccountId
     * @param eligiblePaidHoliday
     * @return
     */
    public String updateEligiblePaidHolidays(String digitalLoanAccountId, String eligiblePaidHoliday) {
        return updateQuery("update loans.tdbk_vas_details set eligible_pay_holidays='" + eligiblePaidHoliday + "' where digitalLoanAccountId='" + digitalLoanAccountId + "';");

    }

    /**
     * Common method to return Get Digital Loan Id
     * @param loanId
     * @return
     * @throws JSchException
     * @throws SQLException
     */
    public String getDigitalLoanId(String loanId) throws JSchException, SQLException {
        return selectQuery("", "SELECT digitalLoanAccountId FROM loans.tdbk_loan_installment_details where loanAccountNumber='" + loanId + "';");
    }

    /**
     * Common method to  Update Poi3 VerificationDateAndTime
     * @param dateTime
     * @param userId
     */
    public void updatePoi3VerificationDateAndTime(String dateTime, String userId) {
        updateQuery("update loans.tdbk_digital_loan_application set poi3VerificationDateAndTime='" + dateTime + "' where userId ='" + userId + "' and applicationStatus='APPROVED';");
    }

    /**
     * Common method to return Update Updated Time Stamp
     * @param dateTime
     * @param userId
     */
    public void updateUpdatedTimeStamp(String dateTime, String userId) {
        updateQuery("update loans.tdbk_digital_loan_application set Updatedtimestamp='" + dateTime + "' where userId ='" + userId + "' and applicationStatus='APPROVED';");
    }

    /**
     * Common method to return Update application status as APPROVED
     * @param userId
     * @return
     */
    public String updateApplication(String userId) {
        return updateQuery("update loans.tdbk_digital_loan_application set applicationStatus='APPROVED' where userId = '" + userId + "' and applicationStatus='INPROGRESS';");
    }

    /**
     * Common method to return UserId
     * @param mobileNumber
     * @return
     * @throws JSchException
     * @throws SQLException
     */
    public String getUserId(String mobileNumber) throws JSchException, SQLException {
        return selectQuery("customer", "SELECT user_id FROM customer.tdbk_customer_mtb where mobile_no = '" + mobileNumber + "';");
    }

    /**
     * Common method to return Get Loan Rejection Type
     * @param userId
     * @return
     * @throws JSchException
     * @throws SQLException
     */
    public String getLoanRejectionType(String userId) throws JSchException, SQLException {
        return selectQuery("customer", "SELECT rejectedReason FROM loans.tdbk_digital_loan_application where userId ='" + userId + "'");
    }

    /**
     * Common method to return Get Loan Status
     * @param userId
     * @return
     * @throws JSchException
     * @throws SQLException
     */
    public String getLoanStatus(String userId) throws JSchException, SQLException {
        return selectQuery("customer", "SELECT applicationStatus FROM loans.tdbk_digital_loan_application where userId ='" + userId + "' and applicationStatus='REJECT';");
    }

    /**
     * Common method to return Get ID Expiry Date
     * @param userId
     * @return
     * @throws JSchException
     * @throws SQLException
     */
    public String getIDExpiryDate(String userId) throws JSchException, SQLException {
        return selectQuery("customer", "SELECT expire_on FROM customer.tdbk_cust_doc_mtb where user_id ='" + userId + "' and doc_type_desc='Philippines - ePassport';");
    }

    /**
     * Common method to return Update Back Date
     * @param date
     * @param userId
     * @return
     */
    public String updateBackDate(String date, String userId) {
        return updateQuery("UPDATE customer.tdbk_cust_doc_mtb SET expire_on ='" + date + "' WHERE user_id ='" + userId + "' AND doc_type_desc='Philippines - ePassport';");
    }

    /**
     * Common method to return Get Loan Account Number
     * @param userId
     * @return
     * @throws JSchException
     * @throws SQLException
     */
    public String getLoanAccountNumber(String userId) throws JSchException, SQLException {
        return selectQuery("", "SELECT loanAccountNumber FROM loans.tdbk_digital_loan_application where userId ='" + userId + "' and applicationStatus='ACTIVATED';");
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
     * Common method to validate Credit Builder Tile Validation
     * @throws Exception
     */
    public void creditBuilderTileValidation() throws Exception {
        scrollToVertical("Credit Builder");
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSmartSmall)), propertyFileReader("smartSmall", "QuickLoanWithVas"), "Tile Header");
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objCreditBuilderTile)), propertyFileReader("creditBuilder", "QuickLoanWithVas"), "Tile Sub Header");
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objApplyForCredit)), propertyFileReader("applyForCredit", "QuickLoanWithVas"), "Text");
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNowWithPayHinga)), propertyFileReader("nowWithPayHinga", "QuickLoanWithVas"), "Text");
    }

    /**
     * Common method to validate Shop Installment Tile Validation
     * @throws Exception
     */
    public void shopInstallmentTileValidation() throws Exception {
//        swipe("UP",2);
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objShoppingTileHeader)), propertyFileReader("shoppingTileHeader", "QuickLoanWithVas"), "Shopping Tile title", "contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objShoppingTile)), propertyFileReader("shoppingOption", "QuickLoanWithVas"), "Shopping tile", "contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objShoppingSubTitle)), propertyFileReader("shoppingSubTitle", "QuickLoanWithVas"), "Shopping Tile sub title", "contains");
    }

    /**
     * Common method to validate InProgress Tile Validation
     * @throws Exception
     */
    public void inProgressTileValidation() throws Exception {
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInprogressStatus), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInprogressStatus), "Status"));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInprogressStatus)), propertyFileReader("inProgressTxt", "QuickLoanWithVas"), "Status", "contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTapHereToContinue)), propertyFileReader("tapHereToContinueTxt", "QuickLoanWithVas"), "Text", "contains");
    }

    /**
     * Common method to update Approve Loan Status
     * @param mobileNo
     * @throws JSchException
     * @throws SQLException
     */
    public void approveLoanStatus(String mobileNo) throws JSchException, SQLException {
        String userId = getUserId("63" + mobileNo);
        logger.info("user id: " + userId);
        String applicationStatus = applicationStatus(userId);
        while (!applicationStatus.equalsIgnoreCase("APPROVED")) {
            applicationStatus = applicationStatus(userId);
            if (applicationStatus.equalsIgnoreCase("INPROGRESS")) {
                updateApplication(userId);
                logger.info("Application status: " + applicationStatus);
                waitTime(3000);//Hard wait required for execution
            } else {
                logger.info("Application status: " + applicationStatus);
            }
            applicationStatus = applicationStatusApproved(userId);
            if (applicationStatus.equalsIgnoreCase("APPROVED")) {
                logger.info("Loan status changed to APPROVED");
                extentLogger("status","Loan status changed to APPROVED");
                break;
            }
        }
    }

    /**
     * Common method to validate Need Fast Cash Page Validation
     * @throws Exception
     */
    public void needFastCashPageValidation() throws Exception {
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objEasypaymentsTitle), getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objEasypaymentsTitle)))) {
            logger.info("Successufully navigated to  Need fast cash or easy payments? Screen");
            extentLoggerPass("Payments Page", "Successufully navigated to  Need fast cash or easy payments? Screen");
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objEasypaymentsTitle)), propertyFileReader("easyPaymentsScreenTitle", "QuickLoanWithVas"), "Need fast cash title", "contains");
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objEayPaymentsSubTitle)), propertyFileReader("easyPaymentsScreenSubTitle", "QuickLoanWithVas"), "Need fast cash sub title", "contains");
            waitTime(2000);//Hard wait required for execution
            shopInstallmentTileValidation();
            creditBuilderTileValidation();
        }
    }

    /**
     * Common method to validate How Much Do You Need Page
     * @throws Exception
     */
    public void howMuchDoYouNeedPage() throws Exception {
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHowMuchNeedPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHowMuchNeedPage), "Text"))) {
            logger.info("Successufully navigated to  How much do you need? page");
            extentLoggerPass("Payments Page", "Successufully navigated to How much do you need? page");
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHowMuchNeedPageBckBtn), "Back Button");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHowMuchNeedPage)), propertyFileReader("howMuchDoYouNeed", "QuickLoanWithVas"), "Page Header");
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPesosSymbol), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPesosSymbol), "Symbol"));
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAmount), "Amount"));
        }
    }

    /**
     * Method to Calculate Payhinga in protect yourself page
     * @param amount
     * @param time
     * @param interestRate
     * @throws Exception
     */
    public void protectYourselfPagePayHingaCalculation(String amount, String time, String interestRate) throws Exception {
        String payhingaActualAmount =getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSecureTheFutureTxt));
        payhingaActualAmount = extractNumbers(payhingaActualAmount);
        String calculatedEmi=calculateEMI(Double.parseDouble(amount),Double.parseDouble(time),Double.parseDouble(interestRate));
        String payhingaExpectedAmount=payHinga(calculatedEmi);
        double payhingaExpectedAmountAfterRound = round(Double.parseDouble(payhingaExpectedAmount),2);
        valueValidation(payhingaActualAmount,String.valueOf(payhingaExpectedAmountAfterRound),"Payhinga Amount","contains");
    }
    /**
     * Common method to validate Protect Yourself Page Validation
     * @throws Exception
     */
    public void protectYourselfPageValidation() throws Exception {
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objProtectYourSelfTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objProtectYourSelfTxt), "Page"))) {
            logger.info("Successfully navigated to " + getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objProtectYourSelfTxt), "Page"));
            extentLoggerPass("Protect yourself Page", "Successfully navigated to " + getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objProtectYourSelfTxt), "Page"));
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNewTxt)), propertyFileReader("newTxt", "QuickLoanWithVas"), "Text", "contains");
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objProtectForUncertainties)), propertyFileReader("protectionTxt", "QuickLoanWithVas"), "Text", "contains");
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSecureTheFutureTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSecureTheFutureTxt), "Text"));
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objEnjoyAMonthTxt)), propertyFileReader("enjoyAMonthTxt", "QuickLoanWithVas"), "Text", "contains");
            scrollToVertical(propertyFileReader("scrollPayHinga", "QuickLoanWithVas"));
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPushBacktxt)), propertyFileReader("pushBacktxt", "QuickLoanWithVas"), "Text", "contains");
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objGetPaymentTxt)), propertyFileReader("getPaymentTxt", "QuickLoanWithVas"), "Text", "contains");
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayHingaComesTxt)), propertyFileReader("payHingComesWithTxt", "QuickLoanWithVas"), "Text", "contains");
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIwantTobeProtectedBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIwantTobeProtectedBtn), "Button"));
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIwantTobeProtectedBtn)), propertyFileReader("iWantToBeProtected", "QuickLoanWithVas"), "Button Text", "contains");
        }
    }

    /**
     * Common method to validate Main Loan Tile Validation Latest
     * @throws Exception
     */
    public void mainLoanTileValidationLatest() throws Exception {
        scrollToVertical(propertyFileReader("loanTile", "QuickLoanWithVas"));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNewLoanTileHeader)), propertyFileReader("newLoanTileHeader", "QuickLoanWithVas"), "Loan Tile header", "contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile)), propertyFileReader("loanTile", "QuickLoanWithVas"), "Loan Tile", "contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNewLoanTileSubHeader)), propertyFileReader("newloanTileSubHeader", "QuickLoanWithVas"), "Loan Tile", "contains");
    }

    /**
     * Common method to OnBoard As New SKYC User With No TSA
     * @throws Exception
     */
    public void onBoardAsNewSKYCUserWithNoTSA(String amount) throws Exception {
        mainLoanTileValidationLatest();
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), "Tile"));
        needFastCashPageValidation();
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objCreditBuilderTile),getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objCreditBuilderTile),"Tile"));
        readySetBoostPageValidation();
        callMeMayBePageValidation();
        nowWithPayHingaPageValidation();
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHowMuchNeedPageBckBtn), "Back Button");
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objProfileName), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objProfileName), " : Profile Name"))) {
            logger.info("Succesfully navigated to  Dasshboard screen");
            extentLoggerPass("Payments Page", "Succesfully navigated to  Dasshboard screen");
        }
        scrollToVertical(propertyFileReader("loanTile", "QuickLoanWithVas"));
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), "Tile"));
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objCreditBuilderTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objCreditBuilderTile), "Tile"));
        readySetBoostPageValidation();
        callMeMayBePageValidation();
        nowWithPayHingaPageValidation();
        howMuchDoYouNeedPage();
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAmount), "Amount Field");
        clearField(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAmount), "Amount Field");
        type(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAmount), amount, "Amount Field");
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContinueBtn), "Continue Button");
    }

    /**
     * Common method to validate Amount Error Msg Validation
     * @throws Exception
     */
    public void amountErrMsgValidation() throws Exception {
        String[] amounts = {"2000", "260000", "20000"};
        for (int i = 0; i < amounts.length; i++) {
            clearField(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAmount), "Amount Field");
            type(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAmount), amounts[i], "Amount Field");
            verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContinueBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContinueBtn), "button"));
            logger.info(getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.AmountErrMsg), amounts[i]));
            extentLogger("Amount Error Message", getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.AmountErrMsg), amounts[i]));
        }
    }

    /**
     * Common method to validate user Can Edit Amount In How Much Do You Need Screen
     * @throws Exception
     */
    public void userCanEditAmountInHowMuchDoYouNeedScreen() throws Exception {
        scrollToVertical(propertyFileReader("loanTile", "QuickLoanWithVas"));
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), "Tile"));
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objCreditBuilderTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objCreditBuilderTile), "Tile"));
        readySetBoostPageValidation();
        callMeMayBePageValidation();
        nowWithPayHingaPageValidation();
        howMuchDoYouNeedPage();
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAmount), "Amount Field");
        clearField(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAmount), "Amount Field");
        amountErrMsgValidation();
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanCalculatorPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanCalculatorPage), "Page"));
    }

    /**
     * Common method to Calculate Pay Hinga Payment Holiday
     * @return
     */
    public String payHingaPaymentHolidayCalc(String loanAmount) {
        double creditLifeInsurance = payhingaPaymentHoliday(loanAmount);
        creditLifeInsurance = round(creditLifeInsurance, 2);
        return String.valueOf(creditLifeInsurance);
    }

    /**
     * Common method to calculate Additional Monthly Payment
     * @return
     */
    public String additionalMonthlyPayment(String installAmount) {
        String aditionalPayment = payHinga(installAmount);
        double roundAditionalPayment = round(Double.parseDouble(aditionalPayment), 2);
        return String.valueOf(roundAditionalPayment);
    }

    /**
     * Common method to validate summary Page Validation
     * @param vasType
     * @throws Exception
     */
    public void summaryPageValidation(String vasType) throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHereisSummaryTxt), 5, "");
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHereisSummaryTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHereisSummaryTxt), "Page"))) {
            logger.info("Successfully naviagted to " + getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHereisSummaryTxt), "Page"));
            extentLoggerPass("Here is Summary page", "Successfully naviagted to " + getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHereisSummaryTxt), "Page"));
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanAmountTxt)), propertyFileReader("loanAmountTxt", "QuickLoanWithVas"), "Text", "contains");
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanAmount), "Loan Amount"));
            loanAmount = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanAmount));
            loanAmount = removeSpecialCharacter(loanAmount, false);
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInstallMentTermsTxt)), propertyFileReader("installmentTermsTxt", "QuickLoanWithVas"), "Text", "contains");
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInstallmentMonth), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInstallmentMonth), "Installment Terms"));
            installmentTerms = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInstallmentMonth));
            installmentTerms = extractNumbers(installmentTerms);
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMonthlyAddOnRate)), propertyFileReader("monthlyAddOnRateTxt", "QuickLoanWithVas"), "Text", "contains");
            String monthlyAddOnRateValue = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMonthlyAddOnRateInterestValue));
            addOnRate = extractNumbers(monthlyAddOnRateValue);
            addOnRate = addOnRate.substring(0, 4);
            double monthlyaddOnrate = monthlyAddOnCalculation(Double.parseDouble(installAmount), Double.parseDouble(loanAmount), Integer.parseInt(installmentTerms));
            containsValidation(addOnRate, String.valueOf(monthlyaddOnrate), "Monthly add on rate");
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objDeductedFromLoanTxt)), propertyFileReader("deductedTxt", "QuickLoanWithVas"), "Text");
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objProcessingFeeTxt)), propertyFileReader("processingFeeTxt", "QuickLoanWithVas"), "Text", "contains");
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objProcessingFeeAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objProcessingFeeAmount), "Processing Fee Amount"));
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objDocumentaryTxt)), propertyFileReader("documentaryTxt", "QuickLoanWithVas"), "Text", "contains");
            String actualDocumentaryStampTaxAmount = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objDocumentaryTaxAmount));
            actualDocumentaryStampTaxAmount = removeSpecialCharacter(actualDocumentaryStampTaxAmount, false);
            String expectedDocumentaryStampTaxAmount = documentStampFeeCalculator(Double.parseDouble(loanAmount), Double.parseDouble(installmentTerms));
            valueValidation(actualDocumentaryStampTaxAmount, expectedDocumentaryStampTaxAmount, "Documentary stamp tax", "contains");
            if (vasType.equalsIgnoreCase("WithVAS")) {
                if (platform.equalsIgnoreCase("ios")) {
                    swipe("UP", 1);
                }
                scrollToVertical(propertyFileReader("figuresMayChangeTxt", "QuickLoanWithVas"));
                valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayHingaTitleTxt)), propertyFileReader("payHingaTitleTxt", "QuickLoanWithVas"), "Text", "contains");
                verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPaymentHolidayPayHingaSubTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPaymentHolidayPayHingaSubTxt), "Credit Life Insurance text"));
                String creditLifeInsuranceAmount = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPaymentHolidayPayHingaSubTxt));
                creditLifeInsuranceAmount = extractNumbers(creditLifeInsuranceAmount);
                creditLifeInsuranceAmount = removeSpecialCharacter(creditLifeInsuranceAmount, false);
                creditLifeInsuranceAmount = creditLifeInsuranceAmount.replace(" ", "");
                valueValidation(creditLifeInsuranceAmount, payHingaPaymentHolidayCalc(loanAmount), "Credit Life Insurance Amount", "contains");
                verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAdditionalPayHingsSubTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAdditionalPayHingsSubTxt), "Text"));
                String additionalPayment = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAdditionalPayHingsSubTxt));
                additionalPayment = extractNumbers(additionalPayment);
                additionalPayment = removeSpecialCharacter(additionalPayment, false);
                containsValidation(additionalPayment, additionalMonthlyPayment(installAmount), "Additional monthly payment");
                verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHowItWorksBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHowItWorksBtn), "Button"));
                verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTotalMontlyInstallAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTotalMontlyInstallAmount), "Total monthly installment Amount"));
                String actualTotalMonhlyInstallmentAmount = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTotalMontlyInstallAmount));
                actualTotalMonhlyInstallmentAmount = removeSpecialCharacter(actualTotalMonhlyInstallmentAmount, false);
                String expectedTotalMonhlyInstallmentAmount = totalMonthlyInstallment(installAmount, additionalMonthlyPayment(installAmount));
                containsValidation(actualTotalMonhlyInstallmentAmount, expectedTotalMonhlyInstallmentAmount, getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTotalMonthlyInstallmentTxt)));
                verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFiguresAboveTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFiguresAboveTxt), "Text"));
            } else{
                verifyElementNotDisplayed(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayHingaTitleTxt),"PayHinga tile");
                logger.info("PayHinga tile not displayed for Without VAS");
                extentLogger("Without VAS","PayHinga tile not displayed for Without VAS");
            }
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHereIsSummaryPageBackBtn), "Back Button");
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSweetAcceptBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSweetAcceptBtn), "Button"));
        } else {
            logger.info("Failed to naviagted Here's a summary page");
            extentLoggerFail("Here's summary page", "Failed to naviagted Here's a summary page");
        }
    }
    /**
     * Common method to Loan Tile Status Validation
     * @throws Exception
     */
    public void loanTileStatusValidation() throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInprogressStatus), 10, "Loan status");
        inProgressTileValidation();
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), "Tile"));
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objCreditBuilderTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objCreditBuilderTile), "Tile"));
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContinueBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContinueBtn), "Button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContinueWithPayHingBtn), 10, "Button");
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContinueWithPayHingBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContinueWithPayHingBtn), "Button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIwantTobeProtectedBtn), 10, "Button");
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIwantTobeProtectedBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIwantTobeProtectedBtn), "Button"));
    }
    /**
     * Common method to Choose Installment Page Navigation
     * @param amount
     * @throws Exception
     */
    public void chooseInstallmentPageNavigation(String amount) throws Exception {
        scrollToVertical(propertyFileReader("loanTile", "QuickLoanWithVas"));
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), "Tile"));
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objCreditBuilderTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objCreditBuilderTile), "Tile"));
        howMuchDoYouNeedPage();
        clearField(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAmount), "Amount Field");
        type(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAmount), amount, "Amount Field");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContinueBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContinueBtn), "Button"));
    }

    /**
     * Common method to Loan Status Validation
     * @throws Exception
     */
    public void loanValidation() throws Exception {
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), ": Tile"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansStatus), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansStatus), ": Loan status"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanTileInfo)), propertyFileReader("LoanTileInfo", "QuickLoanWithVas"), ": Tile info");
    }

    /**
     * Common method to Loan Segment 2 Navigation
     * @param tinNumber
     * @param income
     * @param contact1
     * @param contact2
     * @throws Exception
     */
    public void loanSegment2Navigation(String tinNumber, String income, String contact1, String contact2) throws Exception {
        whatDoYouNeedItForScreen(propertyFileReader("ReasonForLoan", "QuickLoanWithVas"));
        fieldOfWork(propertyFileReader("FieldOfWork", "QuickLoanWithVas"));
        selectOccupation(propertyFileReader("Occupation", "QuickLoanWithVas"));
        selectIndustry(propertyFileReader("Industry7", "QuickLoanWithVas"), propertyFileReader("SubIndustry", "QuickLoanWithVas"));
        selectMaritalStatus(propertyFileReader("MaritalStatusOption1", "QuickLoanWithVas"));
        selectKidsOrDependents(propertyFileReader("DependentsOption1", "QuickLoanWithVas"));
        selectHighestEducationalAttainment(propertyFileReader("EducationalAttainment1", "QuickLoanWithVas"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContactReferencePage), 5, "Contact reference page");
        updateContactReference(contact1);
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHaveAnotherReference), 5, "Have another Reference Screen");
        updateContactReference(contact2);
        isThisYourCurrentAddressConfirmation();
        inputIncomeCompanyAndTINDetails(tinNumber, income);
        anotherWayToReachYou();
        click(OnBoardingPage.objOkBtn, getTextVal(OnBoardingPage.objOkBtn, "button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTermsAndConditionScreen), 5, "Terms and Condition Screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTermsAndConditionScreen), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTermsAndConditionScreen), ": page"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTermsAndConditionSubtitle)), propertyFileReader("TermsAndConditionScreenSubtitle", "QuickLoanWithVas"), "Page Subtitle");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTermsAndConditionCheckBox), "Terms And Condition Check Box");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIAcceptBtn), "I accept and give my consent button");
    }

    /**
     * Common method to validate Another Way To Reach You
     * @throws Exception
     */
    public void anotherWayToReachYou() throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAnotherWayToReachYou), 5, "Another way to reach you? Screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("AnotherWayToReachYou", "QuickLoanWithVas"), ": page title");
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSkipBtn), "Skip Button");
    }

    /**
     * Common method to Apply zip code
     * @param zipCode
     * @throws Exception
     */
    public void applyZipCode(String zipCode) throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objZipCodeInputField), 5, "Zip code page");
        type(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objZipCodeInputField), zipCode, "Zip code input field");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objApplyZipCode), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objApplyZipCode), ": button"));
    }

    /**
     * Common method to select Barangay
     * @throws Exception
     */
    public void selectBarangay() throws Exception {
        click(OnBoardingPage.objBarangay, getTextVal(OnBoardingPage.objBarangay, ": field"));
        if (verifyElementDisplayed(OnBoardingPage.objBarangay)) {
            click(OnBoardingPage.objBarangay, getTextVal(OnBoardingPage.objBarangay, ": field"));
        }
        type(OnBoardingPage.objBarangaySearchField, propertyFileReader("SanAndreBarangay", "onboarding"), ": Barangay search field");
        verifyElementPresentAndClick(OnBoardingPage.objBarangay("1"), getTextVal(OnBoardingPage.objBarangay("1"), ": Barangay"));
    }

    /**
     * Common method update Contact For Reference
     * @param sContactNumber
     * @throws Exception
     */
    public void updateContactForReference(String sContactNumber) throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContactsIcon), 3, "Contacts Icon");
        if (verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContactsPopup))) {
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContactsPopup), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContactsPopup), ": Contacts Permission popup"));
            click(LoginPage.getByOSType(platform,LoginPage.objAllowBtn), getTextVal(LoginPage.getByOSType(platform,LoginPage.objAllowBtn), ": Button"));
        }
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContactsIcon), "Contacts Icon");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSelectReferenceScreen), 5, "Select reference Screen");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("SelectReferenceScreen", "QuickLoanWithVas"), ": page title");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("SelectReferenceScreenSubtitle", "QuickLoanWithVas"), ": page subtitle");
        if (platform.equalsIgnoreCase("ios")) {
            clearField(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objReferenceSearchField), "Search field");
        }
        type(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objReferenceSearchField), sContactNumber, "Search field");
        click(QuickLoanWithVasPage.objListReferenceNumber(platform, 1), "Contact");
    }

    /**
     * Common method to update Contact Reference
     * @param contactNumber
     * @throws Exception
     */
    public void updateContactReference(String contactNumber) throws Exception {
        updateContactForReference(contactNumber);
        updateFirstNameLastNameAndRelationShip();
        if (platform.equalsIgnoreCase("ios")) {
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objKeyboardDoneBtn), "Keyboard Done Button");
        }
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),getTextVal(  QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), ": button"));
    }

    /**
     * Common method to Send Money
     * @param amount
     * @param selectSendMoneyOption
     * @throws Exception
     */
    /**
     * Common method to Send Money
     * @param amount
     * @throws Exception
     */
    public void sendMoney(String amount) throws Exception {
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon),5,"Send Icon");
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendIcon), "Send Icon");
        sendMoneyModule.selectSendMoneyOption(SendMoneyPage.getByOSType(platform,SendMoneyPage.objToAnotherBankOption));
        sendMoneyModule.addMoneyAndPurpose(amount);
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn), "Next Button"));
        sendMoneyModule.selectAccountInfoAndBank(propertyFileReader("AccountHolderName","SendMoney"),propertyFileReader("Bank","SendMoney"), propertyFileReader("AccountNumber","SendMoney"));
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objConfirmSendMoneyPage), 5, "Confirm send money page");
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objConfirmButton), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objConfirmButton), ": button"));
        enterOTP(RandomIntegerGenerator(6));
        click(SendMoneyPage.getByOSType(platform,SendMoneyPage.objBackToDashboard), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objBackToDashboard), ": button"));
    }

    /**
     * Common method to Send Money Bank Transfer
     * @param amount
     * @throws Exception
     */
    public void sendMoneyBankTransfer(String amount) throws Exception {
        sendMoneyModule.navigateToTonikTransactionScreen(SendMoneyPage.getByOSType(platform,SendMoneyPage.objToAnotherBankOption));
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objSendMoneyPage), 5, "Send Money page");
        sendMoneyModule.addMoneyAndPurpose(amount);
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objNextBtn), "Next Button"));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAccountHolderNameField), "Account Holder Name Field");
        type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAccountHolderNameField), "DHDFHFFH", "Account Holder Name input field");
        hideKeyboard();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBankNameField), "Bank Name Field");
        if(verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBankNameField))){
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBankNameField), "Bank Name Field");
        }
        click(SendMoneyPage.objList(platform,1), "ASIA UNITED BANK");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAccountNumberField), "Account Number Field");
        type(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAccountNumberField), "9867554378567898", "Account Holder Name input field");
        hideKeyboard();
        if(getPlatform().equalsIgnoreCase("iOS")){
            click(SendMoneyPage.getByOSType(platform,SendMoneyPage.objDoneButton),"Done button");
        }else {
            verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform, SendMoneyPage.objNextBtn), getTextVal(SendMoneyPage.getByOSType(platform, SendMoneyPage.objNextBtn), "Next Button"));
        }
        waitForElementToBePresent(SendMoneyPage.getByOSType(platform,SendMoneyPage.objConfirmSendMoneyPage), 5, "Confirm send money page");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": title"));
        verifyElementPresentAndClick(SendMoneyPage.getByOSType(platform,SendMoneyPage.objConfirmButton), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objConfirmButton), ": button"));
        enterOTP(RandomIntegerGenerator(6));
        click(SendMoneyPage.getByOSType(platform,SendMoneyPage.objBackToDashboard), getTextVal(SendMoneyPage.getByOSType(platform,SendMoneyPage.objBackToDashboard), ": button"));
    }

    /**
     * Common method to make Insufficient TSA
     * @param amount
     * @throws Exception
     */
    public void insufficientTSA(String amount) throws Exception {
        double availableBalance = getAvailableBalanceInteger(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBalanceOnScreen));
        double loanAmount = Double.parseDouble(amount);
        if (availableBalance >= loanAmount) {
            sendMoneyBankTransfer(String.valueOf(availableBalance - 4000));
        }
    }

    /**
     * Common method to user Can Apply Loan WithVAS From Loan Calculator Screen
     * @param amount
     * @throws Exception
     */
    public void userCanApplyLoanWithVASFromLoanCalculatorScreen(String amount) throws Exception {
        chooseInstallmentPageNavigation(amount);
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objYouareApplyingTxt)), propertyFileReader("youAreApplyingTxt", "QuickLoanWithVas"), "Page Text", "contains");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTotalLoanAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTotalLoanAmount), "Loan Amount"));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objYourInstallmentTxt)), propertyFileReader("chooseInstallmentTxt", "QuickLoanWithVas"), "Page Text", "contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.obActualAmountTxt)), propertyFileReader("actualAmountTxt", "QuickLoanWithVas"), "Page Text", "contains");
        installAmount = vasEmiVerification(amount, QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanTenure), QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMonthlyInstallmentAmount), QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayhingCalcTxt), Double.parseDouble(propertyFileReader("QLInterestRate", "QuickLoanWithVas")));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayHingaTxtOnInsurance)), propertyFileReader("payHingaTxt", "QuickLoanWithVas"), "Text on Pay Hinga Tab", "contains");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayhingCalcTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayhingCalcTxt), "Text on Pay Hinga Tab"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayHingaInformationIcon), "Information icon");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objUnprotectedloanBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objUnprotectedloanBtn), "Button"));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objUnprotectedloanBtn)), propertyFileReader("applyForButtonTxt", "QuickLoanWithVas"), "Button text", "contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContinueWithPayHingBtn)), propertyFileReader("continueWithpayHingaBtn", "QuickLoanWithVas"), "Button text", "contains");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objApplyingLoanPageBackBtn), "Back Button");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContactUsBtn1), 10, "Contact us Button");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContactUsBtn1), "Contact Us Button");
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContinueWithPayHingBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContinueWithPayHingBtn), "Button"));
        protectYourselfPageValidation();
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIwantTobeProtectedBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIwantTobeProtectedBtn), "Button"));
        action_clickOnPosition(527, 1577);
        summaryPageValidation("WithVAS");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHereisSummaryTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHereisSummaryTxt), "Page Title"));
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
        loanTileStatusValidation();
        if (platform.equalsIgnoreCase("ios")) {
            swipe("UP", 1);
        }
        scrollToVertical(propertyFileReader("figuresMayChangeTxt", "QuickLoanWithVas"));
        waitTime(4000);//Hard wait required for execution
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHowItWorksBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHowItWorksBtn), "Button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objRelaxPage), 10, "Relax Page");
    }

    /**
     * Common method to reason For Loan Application Validation
     * @throws Exception
     */
    public void reasonForLoanApplicationValidation(String sReason) throws Exception {
        loanValidation();
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), ": Tile"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objWhatDoYouNeedItForPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objWhatDoYouNeedItForPage), ": page"));
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back button");
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), ": Tile"));
        leavingSoonPageValidation();
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objList), 5, "What Do You Need It For Page");
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageTitle), ": page title"))) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageTitle)), propertyFileReader("WhatDoYouNeedItForPage", "QuickLoanWithVas"), ": page title");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPageSubTitle)), propertyFileReader("WhatDoYouNeedItForPageSubTitle", "QuickLoanWithVas"), ": page subtitle");
            if (platform.equalsIgnoreCase("ios")) {
                List<IOSElement> values = DriverManager.getAppiumDriver().findElements(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objList));
                for (int info = 0; info < values.size(); info++) {
                    String displayedItem = values.get(info).getText();
                    logger.info("Reason : '" + displayedItem + "' is displayed");
                    ExtentReporter.extentLogger(" ", "Reason : '" + displayedItem + "' is displayed");
                }
            } else {
                List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objList));
                for (int info = 0; info < values.size(); info++) {
                    String displayedItem = values.get(info).getText();
                    logger.info("Reason : '" + displayedItem + "' is displayed");
                    ExtentReporter.extentLogger(" ", "Reason : '" + displayedItem + "' is displayed");
                }
            }
            assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNextBtn)), "false", ": Enable Attribute value");
            verifyElementPresentAndClick(QuickLoanWithVasPage.objReasonForLoan(platform, sReason), getTextVal(QuickLoanWithVasPage.objReasonForLoan(platform, sReason), ": Reason"));
            assertionValidation(getAttributValue("enabled", QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNextBtn)), "true", ": Enable Attribute value");
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNextBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNextBtn), ": button"));
        }
    }

    /**
     * Common method to loan Approved Tile Validation
     * @throws Exception
     */
    public void loanApprovedTileValidation() throws Exception {
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), "Tile"));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile)), propertyFileReader("loanTile", "QuickLoanWithVas"), "Loan Tile", "contains");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objApprovedStateTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objApprovedStateTile), "Tile Sub header"));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objApprovedStateTile)), propertyFileReader("youAreApprovedTxt", "QuickLoanWithVas"), "Tile Sub header", "contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objApproveLoanTileSubHeader)), propertyFileReader("congratulationsTxt", "QuickLoanWithVas"), "Header", "contains");
    }

    /**
     * Common method to woot You Got Approved Page Validation
     * @throws Exception
     */
    public void wootYouGotApprovedPageValidation() throws Exception {
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt), "Page Header"));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("wootYouGotPageHeader", "QuickLoanWithVas"), "Page Haeder", "contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSubTitleTxt)), propertyFileReader("offerVaidTxt", "QuickLoanWithVas"), "Sub Header", "contains");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objApprovedAmountHeadTxt), 10, "Approved amount text");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objApprovedAmountHeadTxt)), propertyFileReader("approvedAmountTxt", "QuickLoanWithVas"), "Text", "contains");
    }

    /**
     * Common method to reviseed Total EMI Amout
     * @param loanAmont
     * @param laonTenure
     * @param interestRate
     * @param loanType
     * @return
     */
    public String reviseedTotalEmiAmout(String loanAmont, String laonTenure, String interestRate, String loanType) {
        double total = 0;
        String expectedEmi = calculateEMI(Double.parseDouble(loanAmont), Double.parseDouble(laonTenure), Double.parseDouble(interestRate));
        String amount = formatToTwoDecimalPlaces(payHinga(expectedEmi));
        if (loanType.equalsIgnoreCase("WithVAS")) {
            total = Double.parseDouble(expectedEmi) + Double.parseDouble(amount);
        } else {
            total = Double.parseDouble(expectedEmi);
        }
        return String.valueOf(total);
    }

    /**
     * Common method to tell Us Your PayDay Page Validation
     * @throws Exception
     */
    public void tellUsYourPayDayPageValidation() throws Exception {
        waitTime(2000);//Hard wait required for execution
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt), "Page Header"));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("tellUsPageHeader", "QuickLoanWithVas"), "Page Header", "contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSubTitleTxt)), propertyFileReader("weAreNotTxt", "QuickLoanWithVas"), "Sub Header", "contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFifthOfTheMonth)), propertyFileReader("fifthOfMonth", "QuickLoanWithVas"), "Radio Button Option", "contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTenthOftheMonth)), propertyFileReader("tenthOfMonth", "QuickLoanWithVas"), "Radio Button Option", "contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFifteenthOfTheMonth)), propertyFileReader("fifteenthOfMonth", "QuickLoanWithVas"), "Radio Button Option", "contains");
        boolean disableNextButton = findElement(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNextBtn)).isEnabled();
        softAssert.assertEquals(disableNextButton, false);
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt), "Page Header"));
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAcceptOfferBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAcceptOfferBtn), "Button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt), 10, "Tell us your pay day/s");
        waitTime(3000);//Hard wait required for execution
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFifthOfTheMonth), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFifthOfTheMonth), "Radio Button"));
        waitTime(3000);//Hard wait required for execution
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTenthOftheMonth), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTenthOftheMonth), "Radio Button"));
        waitTime(3000);//Hard wait required for execution
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFifteenthOfTheMonth), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFifteenthOfTheMonth), "Radio Button"));
        waitTime(3000);//Hard wait required for execution
        boolean enabledNextButton = findElement(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNextBtn)).isEnabled();
        if (enabledNextButton) {
            logger.info("Next button is enabled state");
            extentLoggerPass("Month Options", "Next button is enabled state");
        }
    }

    /**
     * Common method to monthly Installment Summary Page Validation
     * @param vasType
     * @param date
     * @param subHeader
     * @throws Exception
     */
    public void monthlyInstallmentSummaryPageValidation(String vasType, String date, String subHeader) throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMonthlyInstallmentSummary), 5, getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMonthlyInstallmentSummary), "Page Header"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMonthlyInstallmentSummary), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMonthlyInstallmentSummary), "Page Header"));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMonthlyInstallmentSummary)), propertyFileReader("monthlyInstallmentPageHeader", "QuickLoanWithVas"), "Page Header", "contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSubTitleTxt)), subHeader, "Sub Header", "contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInsatallmentTxt)), propertyFileReader("installmentPeriodTxt", "QuickLoanWithVas"), "Text", "contains");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInstallmentPeriodMonth), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInstallmentPeriodMonth), "Text"));
        String installmentPeriod = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInstallmentPeriodMonth));
        installmentPeriod = extractNumbers(installmentPeriod);
        logger.info(installmentPeriod);
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objDueDate)), propertyFileReader("dueDateTxt", "QuickLoanWithVas"), "Text", "contains");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objDueDateMonth), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objDueDateMonth), "Text"));
        String dueDateEveryMonth = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objDueDateMonth));
        dueDateEveryMonth = extractNumbers(dueDateEveryMonth);
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFirstInstallmentTxt)), propertyFileReader("firstInstallmentTxt", "QuickLoanWithVas"), "Text", "contains");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInstallmentDuedate), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInstallmentDuedate), "Text"));
        if (vasType.equalsIgnoreCase("WithVAS")) {
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayhingaFeeTxt)), propertyFileReader("payHingaFee", "QuickLoanWithVas"), "Text", "contains");
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayhingaAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayhingaAmount), "Pay hinga fee"));
            getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayhingaAmount));
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMonthlyInstallmentTxt)), propertyFileReader("totalMonthlyTxt", "QuickLoanWithVas"), "Text", "contains");
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMonthlyIstallmentAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMonthlyIstallmentAmount), "Text"));
        } else {
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMonthlyPaymentDueTxt)), propertyFileReader("MonthlyPaymentDue", "QuickLoanWithVas"), "Text", "contains");
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMonthlyPaymentDueAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMonthlyPaymentDueAmount), "Amount"));
        }
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objConfirmBtn)), propertyFileReader("confirmBtn", "QuickLoanWithVas"), "Text", "contains");
        boolean enabledConfirmButton = findElement(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objConfirmBtn)).isEnabled();
        if (enabledConfirmButton) {
            logger.info("Confirm button is enabled state");
            extentLoggerPass("Month Options", "Confirm button is enabled state");
        }
    }

    /**
     * Common method to contact Us Page Validation
     * @throws Exception
     */
    public void contactUsPageValidation() throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContactUsButton), 10, "Contact Us Button");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContactUsButton), "Contact Us Button");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt), "Page Header"));
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNextBtn), "Next Button");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNextBtn), 10, "Next Button");
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContactUsButton), "Contact Us Button");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objGetInTouchPage), 10, "Get in touch page");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objGetInTouchPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objGetInTouchPage), "Page"));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objGetInTouchPage)), propertyFileReader("getInTouch", "QuickLoanWithVas"), "Page Header", "contains");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
    }

    /**
     * Common method to get Available Balance Integer
     * @param locator
     * @return
     * @throws Exception
     */
    public double getAvailableBalanceInteger(By locator) throws Exception {
        String availableBalance = getText(locator);
        String trim = availableBalance.replace("₱", "");
        if (trim.contains(",")) {
            trim = trim.replace(",", "");
        }
        return Double.parseDouble(trim);
    }

    /**
     * Common method to signed Sealed Delivered Page Validation
     * @throws Exception
     */
    public void signedSealedDeliveredPageValidation() throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), 10, getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), "Page"));
        waitTime(5000);//Hard wait required for execution
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), "Page"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPageSubHeader)).trim(), propertyFileReader("holdOnTxt", "QuickLoanWithVas"), "Page Sub header");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPromissoryNoteTxt)), propertyFileReader("promissoryNoteTxt", "QuickLoanWithVas"), "Text");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objDisclouseTxt)), propertyFileReader("disclosureTxt", "QuickLoanWithVas"), "Text");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAmortizationtxt)), propertyFileReader("amortizationTxt", "QuickLoanWithVas"), "Text");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignPromissoryNoteBtn)), propertyFileReader("signBtnTxt", "QuickLoanWithVas"), "Button Text");
        isButtonEnabled(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignPromissoryNoteBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignPromissoryNoteBtn), "Button"));
    }

    /**
     * Common method to signed Sealed Delivered Page Validation Flex Loan
     * @throws Exception
     */
    public void signedSealedDeliveredPageValidationFlexLoan() throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), 10, getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), "Page"));
        waitTime(5000);//Hard wait required for execution
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), "Page"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPageSubHeader)).trim(), propertyFileReader("holdOnTxt", "QuickLoanWithVas"), "Page Sub header");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPromissoryNoteTxt)), propertyFileReader("promissoryNoteTxt", "QuickLoanWithVas"), "Text");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objDisclouseTxt)), propertyFileReader("disclosureTxt", "QuickLoanWithVas"), "Text");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAmortizationtxt)), propertyFileReader("amortizationTxt", "QuickLoanWithVas"), "Text");
        isButtonEnabled(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignDirectDebitForm), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignDirectDebitForm), "Button"));
    }

    /**
     * Common method to promissory Note Page Validation
     * @throws Exception
     */
    public void promissoryNotePageValidation() throws Exception {
        waitTime(5000);//Hard wait required for execution
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt), "Page"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("promissoryNotePageHeader", "QuickLoanWithVas"), "Page Header");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objReadyToSignButton)), propertyFileReader("readyToSignBtnTxt", "QuickLoanWithVas"), "Button Text");
        isButtonEnabled(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objReadyToSignButton), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objReadyToSignButton), "Button Text"));
    }

    /**
     * Common method to your Signature Required Page Validation
     * @throws Exception
     */
    public void yourSignatureRequiredPageValidation() throws Exception {
        waitTime(8000);//Hard wait required for execution
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt), "Page Header"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("yourSignatureTxt", "QuickLoanWithVas"), "Page Header");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSubTitleTxt)), propertyFileReader("youCanUseTxt", "QuickLoanWithVas"), "Sub Header");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objDownSubTxt)), propertyFileReader("bySigningTxt", "QuickLoanWithVas"), "Down Sub Header");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objEraseBtn), "Erase Button");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContactUsButton), "Contact Us Button");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
    }

    /**
     * Common method to disclosure Statement Page Validation
     * @throws Exception
     */
    public void disclosureStatementPageValidation() throws Exception {
        waitTime(5000);
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt), "Page Header"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("disclousureTxt", "QuickLoanWithVas"), "Page Header");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objReadyToSignButton)), propertyFileReader("readyToSignBtnTxt", "QuickLoanWithVas"), "Page Header");
        isButtonEnabled(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign");
    }

    /**
     * Common method to amortization Schedule Validation
     * @throws Exception
     */
    public void amortizationScheduleValidation() throws Exception {
        waitTime(5000);
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt), "Page Header"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("amortizationSchedule", "QuickLoanWithVas"), "Page Header");
        isButtonEnabled(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objConfirmBtn), "Sign Disclosure Statement Button");
    }

    /**
     * Common method to your Loan Almost Ready Page Validation
     * @throws Exception
     */
    public void yourLoanAlmostReadyPageValidation() throws Exception {
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objYourLoanAlmostReadyPage)), propertyFileReader("yourLoanAlmostReady", "QuickLoanWithVas"), "Page Header");
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objYourLoanAlmostReadyPageSubTitle)), propertyFileReader("thisLastStepCanTake", "QuickLoanWithVas"), "Page Sub Header");
    }

    /**
     * Common method to yes Yes Yes Tile Validation
     * @throws Exception
     */
    public void yesYesYesTileValidation() throws Exception {
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanTileAfterApproved), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanTileAfterApproved), "Text on tile"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanTileAfterApproved)), propertyFileReader("yes", "QuickLoanWithVas"), "Tile Sub header");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objYourLoanHasBeenAdded)), propertyFileReader("yourLoanAmountAdded", "QuickLoanWithVas"), "Tile Sub header");
    }

    /**
     * Common method to loan Disbursal Amount
     * @return
     */
    public String loanDisbursalAmount(String loanAmount, String loanTenure, String procesingFee) {
        String dstAmount = documentStampFeeCalculator(Double.parseDouble(loanAmount), Double.parseDouble(loanTenure));
        double disbursalAmount = Double.parseDouble(loanAmount) - (Double.parseDouble(procesingFee) + Double.parseDouble(dstAmount));
        String disbursedAmount = formatToTwoDecimalPlaces(String.valueOf(disbursalAmount));
        return String.valueOf(disbursedAmount);
    }

    /**
     * Common method to transaction Details
     * @param transactionAmount
     * @param from
     * @param to
     * @throws Exception
     */
    public void transactionDetails(String transactionAmount, String from, String to, String loanType) throws Exception {
        waitTime(3000);
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("TransactionDetails", "QuickLoanWithVas"), "page title");
        if (platform.equalsIgnoreCase("ios")) {
            List<IOSElement> values = DriverManager.getAppiumDriver().findElements(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTransactionDetailsInfo));
            for (int transactionDetails = 1; transactionDetails <= values.size(); transactionDetails++) {
                assertionValidation(getText(QuickLoanWithVasPage.objTransactionDetailsInfo(platform, transactionDetails)), propertyFileReader("TransactionDetails" + transactionDetails, loanType), ": transaction details");
            }
        } else {
            List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTransactionDetailsInfo));
            for (int transactionDetails = 1; transactionDetails <= values.size(); transactionDetails++) {
                assertionValidation(getText(QuickLoanWithVasPage.objTransactionDetailsInfo(platform, transactionDetails)), propertyFileReader("TransactionDetails" + transactionDetails, loanType), ": transaction details");
            }
        }
        String amount = getText(QuickLoanWithVasPage.objTransactionInfo(platform, "Amount")).replaceAll("-","");
        if (amount.contains("₱"))
            amount = String.valueOf(round(Double.parseDouble(removeSpecialCharacter(amount, false)),2));
        containsValidation(amount, transactionAmount.replaceAll("- ",""), ": amount");
        assertionValidation(getText(QuickLoanWithVasPage.objTransactionInfo(platform, "From")), from, ": From");
        if(loanType.contains("QuickLoanWithVas"))
            containsValidation(getText(QuickLoanWithVasPage.objTransactionInfo(platform, "To")), to, ": To");
        containsValidation(getText(QuickLoanWithVasPage.objTransactionInfo(platform, "When")).substring(0, 11), todaysDate(), ": When");
        verifyElementPresent(QuickLoanWithVasPage.objTransactionInfo(platform, "Reference No."), getTextVal(QuickLoanWithVasPage.objTransactionInfo(platform, "Reference No."), ": Reference number"));
        screencapture();
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
        String amount = getText(QuickLoanWithVasPage.objTransactionInfo(platform, "Amount"));
        if (amount.contains("₱")) {
            amount = removeSpecialCharacter(amount, false);
        }
        containsValidation(amount, transactionAmount, ": amount");
        assertionValidation(getText(QuickLoanWithVasPage.objTransactionInfo(platform, "From")), from, ": From");
        containsValidation(getText(QuickLoanWithVasPage.objTransactionInfo(platform, "When")).substring(0, 11), todaysDate(), ": When");
        verifyElementPresent(QuickLoanWithVasPage.objTransactionInfo(platform, "Reference No."), getTextVal(QuickLoanWithVasPage.objTransactionInfo(platform, "Reference No."), ": Reference number"));
        screencapture();
    }
    /**
     * Common method are you sure popup validation
     * @throws Exception
     */
    public void areYouSurePopup() throws Exception {
        verifyElementPresent(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objAreYouSure),getTextVal(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objAreYouSure),"Popup header"));
        assertionValidation(getText(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objAreYouSure)),propertyFileReader("AreYouSure","QuickLoanWithVas"),"Popup header");
        containsValidation(getText(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objYouAreMissingOut)),propertyFileReader("youAreMissingOut","QuickLoanWithVas"),"popup Sub-header");
        verifyElementPresent(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objClickHere),getTextVal(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objClickHere),"Button"));
        verifyElementPresent(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objNoThanks),getTextVal(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objNoThanks),"Button"));
        verifyElementPresent(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objCountMeIn),getTextVal(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objCountMeIn),"Button"));
    }
    /**
     * Common method to customer Care Icon Validation
     * @throws Exception
     */
    public void customerCareIconValidation() throws Exception {
        click(OnBoardingPage.objCustomerCareIcon, "Customer care icon");
        contactUsPageUI();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Get in touch back button");
    }

    /**
     * Common method to loan Information Screen
     * @param loanType
     * @throws Exception
     */
    public void loanInformationScreen(String loanType,String loanAmount,String tenure,String interest) throws Exception {
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt), "Page Header"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("loanInformation", "QuickLoanWithVas"), "Page Header");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanAmountTxt)), propertyFileReader("loanAmountTxt", "QuickLoanWithVas"), "Text");
        String infoScreenAmount = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanAmount));
        infoScreenAmount = removeSpecialCharacter(infoScreenAmount, false);
        assertionValidation(infoScreenAmount, formatToTwoDecimalPlaces(loanAmount), "Amount");
        if (loanType.equalsIgnoreCase("WithVAS")) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenBorrowDateTxt)), propertyFileReader("borrowingTxt", "QuickLoanWithVas"), "Text");
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenBorrowDate)), todaysDateMMMM(), "Borrow Date");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenInstallmentTermsTxt)), propertyFileReader("installmentTermsTxt", "QuickLoanWithVas"), "Text");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenInsuranceCoverageTxt)), propertyFileReader("insuranceCoverage", "QuickLoanWithVas"), "Text");
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenInsuranceAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenInsuranceAmount), "Insurance Amount"));
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenLoanDocumentTxt)), propertyFileReader("loanDocumentsTxt", "QuickLoanWithVas"), "Text");
        } else {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenDateBookedTxt)), propertyFileReader("DateBooked", "QuickLoanWithVas"), "Text");
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenDateBooked)), todaysDateMMMM(), "Date Booked");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenInstallmentTermsTxt)), propertyFileReader("installmentPeriodTxt","QuickLoanWithVas"), "Text");
        }
        assertionValidation(extractNumbers(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenMonth))), tenure, "Month");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenMonthlyPaymentTxt)), propertyFileReader("monthlyPayment", "QuickLoanWithVas"), "Text");
        String actualMonthlyPaymentMethod = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenMonthlyPaymentAmount));
        actualMonthlyPaymentMethod = removeSpecialCharacter(actualMonthlyPaymentMethod, false);
        containsValidation(actualMonthlyPaymentMethod, reviseedTotalEmiAmout(loanAmount, tenure, interest, loanType), "Monthly Payment Amount");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenDueDateTxt)), propertyFileReader("dueDateTxt", "QuickLoanWithVas"), "Text");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenDueDate), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenDueDate), "Due Date"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreeRateAndFees)), propertyFileReader("ratesAndFeesTxt", "QuickLoanWithVas"), "Text");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenTermsAndConditionTxt)), propertyFileReader("termsAndConditionsTxt", "QuickLoanWithVas"), "Text");
        swipe("UP",2);
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenPromissoryNoteTxt)), propertyFileReader("promissoryNote", "QuickLoanWithVas"), "Text");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenDisclosureTxt)), propertyFileReader("disclosureStatementTxt", "QuickLoanWithVas"), "Text");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenAmortizationTxt)), propertyFileReader("amortizationScheduleTxt", "QuickLoanWithVas"), "Text");
        if (loanType.equalsIgnoreCase(propertyFileReader("WithVAS", "QuickLoanWithVas"))) {
            scrollToVertical(propertyFileReader("aboutPayingaTxt", "QuickLoanWithVas"));
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenProofOfCoverTxt)), propertyFileReader("proofOfCoverTxt", "QuickLoanWithVas"), "Text");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoScreenAboutPayhingaTxt)), propertyFileReader("aboutPayingaTxt", "QuickLoanWithVas"), "Text");
        }
        if (verifyElementDisplayed(FlexLoanPage.getByOSType(platform, FlexLoanPage.objCardToChangedTxtInLoanInfoScreen))) {
            assertionValidation(getText(FlexLoanPage.getByOSType(platform, FlexLoanPage.objCardToChangedTxtInLoanInfoScreen)), propertyFileReader("cardToBechangedTxt", "FlexLoan"), "Text");
            verifyElementPresent(FlexLoanPage.getByOSType(platform, FlexLoanPage.objCardNumberInLoanInfoScreen), getText(FlexLoanPage.getByOSType(platform, FlexLoanPage.objCardNumberInLoanInfoScreen)));
            click(FlexLoanPage.getByOSType(platform, FlexLoanPage.objCardNumberInLoanInfoScreen), "Card to be changed on a monthly basis");
        }
    }
    /**
     * Common method to monthly Add On Rate Calculation
     * @param header
     * @param value
     * @param emiAmount
     * @throws Exception
     */
    public void monthlyAddOnRateCalculation(By header, By value, String emiAmount,String amount,String tenure) throws Exception {
        valueValidation(getText(header), propertyFileReader("monthlyAddOnRateTxt", "QuickLoanWithVas"), "Text", "contains");
        String monthlyAddOnRateValue = getText(value);
        String addOnRate = extractNumbers(monthlyAddOnRateValue);
        addOnRate = addOnRate.substring(0, 4);
        double monthlyaddOnrate = monthlyAddOnCalculation(Double.parseDouble(emiAmount), Double.parseDouble(amount), Integer.parseInt(tenure));
        valueValidation(addOnRate, String.valueOf(monthlyaddOnrate), "Monthly add on rate", "contains");
    }
    /**
     * Common method to documentary Stamp Tax Calculation
     * @param header
     * @param value
     * @throws Exception
     */
    public void documentaryStampTaxCalculation(By header, By value,String amount,String tenure) throws Exception {
        valueValidation(getText(header), propertyFileReader("docStampTax", "QuickLoanWithVas"), "Text", "contains");
        waitTime(2000);//Hard wait required for execution
        String actualDocumentaryStampTaxAmount = getText(value);
        actualDocumentaryStampTaxAmount = removeSpecialCharacter(actualDocumentaryStampTaxAmount, false);
        String documentaryStampTaxAmount = documentStampFeeCalculator(Double.parseDouble(amount),Double.parseDouble(tenure));
        String expectedDocumentaryStampTaxAmount = formatToTwoDecimalPlaces(documentaryStampTaxAmount);
        valueValidation(actualDocumentaryStampTaxAmount, expectedDocumentaryStampTaxAmount, "Documentary stamp tax", "contains");
    }

    /**
     * Common method to repayment Amount Calculation
     * @return
     */
    public String repaymentAmountCalculation(String vasType,String amount,String tenure,String interest,boolean round) {
        if(vasType.equalsIgnoreCase("WithVas")) {
            String payHingaFee = payHinga(calculateEMI(Double.parseDouble(amount),Double.parseDouble(tenure),Double.parseDouble(interest)));
            double totalRepaymentAmount;
            if(round) {
                 totalRepaymentAmount = Math.round(Double.parseDouble(amount) + Double.parseDouble(payHingaFee));
            }else{
                 totalRepaymentAmount = Double.parseDouble(amount) + Double.parseDouble(payHingaFee);
            }
            return String.valueOf(totalRepaymentAmount);
        }else{
            double totalRepaymentAmount = Double.parseDouble(amount);
            return String.valueOf(totalRepaymentAmount);
        }
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
        return futureDate.format(formatter);
    }

    /**
     * Common method to input Amount And Click Next
     * @param sAmount
     * @throws Exception
     */
    public void inputAmountAndClickNext(String sAmount) throws Exception {
        type(TopUpPage.getByOSType(platform,TopUpPage.objAmountInputField), sAmount, "Amount Input field");
        if (platform.equalsIgnoreCase("ios")) {
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objKeyboardDoneBtn), "Keyboard Done Button");
        }
        click(TopUpPage.getByOSType(platform,TopUpPage.objNextButton), getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objNextButton), ": Button"));
    }

    /**
     * Common method to topUp
     * @param sValidAmount
     * @throws Exception
     */
    public void topUp(String sValidAmount) throws Exception {
        if (verifyElementDisplayed(TopUpPage.getByOSType(platform,TopUpPage.objTopUpIcon))) {
            verifyElementPresentAndClick(TopUpPage.getByOSType(platform,TopUpPage.objTopUpIcon), "Top-up Icon");
        }
        click(TopUpPage.objTopUpOption(platform,"Online"), getTextVal(TopUpPage.objTopUpOption(platform,"Online"), ": Top-up option"));
        click(TopUpPage.getByOSType(platform,TopUpPage.objGCashOnlineOption), getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objGCashOnlineOption), ": Online Top-up Option"));
        waitTime(5000);
        click(TopUpPage.getByOSType(platform,TopUpPage.objGCashOnlineOption), getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objGCashOnlineOption), ": Online Top-up Option"));
        waitTime(5000);
        click(TopUpPage.getByOSType(platform,TopUpPage.objOneTimeTopUpOption),getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objOneTimeTopUpOption),"Gcash Top-up Option"));
        inputAmountAndClickNext(sValidAmount);
        waitTime(3000);//Hard wait required for execution
        verifyElementPresent(TopUpPage.getByOSType(platform,TopUpPage.objTopUpViaGCash), getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objTopUpViaGCash), ": Page Title"));
        click(TopUpPage.getByOSType(platform,TopUpPage.objProceedToPayButton), getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objProceedToPayButton), ": Button"));
        waitTime(3000);//Hard wait required for execution
        click(TopUpPage.getByOSType(platform,TopUpPage.objButtonDisplayedInTransactionInfoPage), getTextVal(TopUpPage.getByOSType(platform,TopUpPage.objButtonDisplayedInTransactionInfoPage), ": Button"));
        waitTime(3000);//Hard wait required for execution
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBalanceOnScreen), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBalanceOnScreen), ": Available balance after Top-up"));
    }
    /**
     * Common method to get Current Time
     * @return
     */
    public String getCurrentTime() {
        LocalDateTime currentTime = LocalDateTime.now(ZoneId.of("Asia/Manila"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return currentTime.format(formatter);
    }

    /**
     * Common method to ready Set Boost Page Validation
     * @throws Exception
     */
    public void readySetBoostPageValidation() throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objObjAsEasyScreen),3,"Loan info screen");
        if (verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objObjAsEasyScreen))) {
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objObjAsEasyScreen)), propertyFileReader("readySetBoost", "QuickLoanWithVas"), "Page Header");
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAsEasySubHeader)), propertyFileReader("getTheCash", "QuickLoanWithVas"), "Page Sub Header");
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objCountMeInButton)), propertyFileReader("countMeIn", "QuickLoanWithVas"), "Button");
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objCountMeInButton), "Count Me In! Button");
        }
    }

    /**
     * Common method to call Me May Be Page Validation
     * @throws Exception
     */
    public void callMeMayBePageValidation() throws Exception {
        if (verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSmartSmall))) {
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSmartSmall)), propertyFileReader("callMeMaybe", "QuickLoanWithVas"), "Page Header");
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objCallMeMaybePageSubTitle)), propertyFileReader("keepingInTouch", "QuickLoanWithVas"), "Page Sub Header");
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objGotItBtn), "Got it! Button");
        }
    }

    /**
     * Common method to now With PayHinga Page Validation
     * @throws Exception
     */
    public void nowWithPayHingaPageValidation() throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayHingPageHeader),5,"PayHinga Screen");
        if (verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayHingPageHeader))) {
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayHingPageHeader)), propertyFileReader("nowWithPayHinga", "QuickLoanWithVas"), "Page Header");
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayHingPageSubTitle)), propertyFileReader("availOur", "QuickLoanWithVas"), "Page Sub Header");
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLetsGoBtn), "Got it! Button");
        }
    }

    /**
     * Common method to nooice Page Validation
     * @throws Exception
     */
    public void nooicePageValidation() throws Exception {
        if (verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNooicePage))) {
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNooicePage)), propertyFileReader("Nooice", "QuickLoanWithVas"), "Page Header");
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNooicePageSubTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNooicePageSubTxt), "Page Sub Header"));
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objCloseButton), "Close Button");
        }
    }

    /**
     * Common method to verify OTP Popup
     * @param mobileNumber
     * @throws Exception
     */
    public void verifyOTPPopup(String mobileNumber) throws Exception {
        if (verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objVerifyNow))) {
            verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objVerifyNow), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objVerifyNow), ": button"));
            waitTime(3000);//Hard wait required for execution
            String emailAddress = getEmailAddress("63" + mobileNumber);
            String otp = getOTP(emailAddress);
            enterOTP(otp);
            click(OnBoardingPage.objCloseBtn, getTextVal(OnBoardingPage.objCloseBtn, "Button"));
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContinueWithPayHingBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContinueWithPayHingBtn), "Button"));
        }
    }

    /**
     * Common method to modify Date
     * @param mobileNumber
     * @param days
     * @param pattern
     * @throws JSchException
     * @throws SQLException
     */
    public void modifyDate(String mobileNumber, int days, String pattern) throws JSchException, SQLException {
        String userId = getUserId(mobileNumber);
        String date = returnDate(days, pattern);
        String modifiedDate = updateBackDate(date, userId);
        logger.info("Expiry date modified : " + date);
        extentLogger("", "Expiry date modified : " + date);
    }

    /**
     * Common method to validate I Want To Close My Loan Page Validation
     * @param loanType
     * @throws Exception
     */
    public void iWantToCloseMyLoanPageValidation(String loanType,String amount,String tenure,String interest) throws Exception {
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNextInstallmentTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNextInstallmentTxt), "Text"));
        String actualLoanDashBoardEmi = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanDashBoardEmiAmount));
        actualLoanDashBoardEmi = removeSpecialCharacter(actualLoanDashBoardEmi, false);
        containsValidation(actualLoanDashBoardEmi, reviseedTotalEmiAmout(amount,tenure,interest,loanType), "Loan amount");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objThisAmountMessage), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objThisAmountMessage), "Text"));
        containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objThisAmountMessage)), propertyFileReader("thisAmountMessage", "QuickLoanWithVas"), "Text");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPaymentDoneOf), getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPaymentDoneOf)));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPaymentDoneOf)), propertyFileReader("paymentDoneOf", "QuickLoanWithVas")+" "+tenure, "Text");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInfoButton), "Info Button");
        if (getPlatform().equalsIgnoreCase(loanType)) {
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayhingaTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayhingaTitle), "Payhinga Tile"));
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayhingaTitle)), propertyFileReader("payHingaTxt", "QuickLoanWithVas"), "Payhinga Tile");
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objThreeInstallmentTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objThreeInstallmentTxt), "Sub Header"));
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLifeInsurancePayhinga), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLifeInsurancePayhinga), "Payhinga"));
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLearnMoreBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLearnMoreBtn), "Button"));
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLifeInsurancePayhinga)), propertyFileReader("lifeInsurance", "QuickLoanWithVas"), "Payhinga Tile");
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTapMoreInfoTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTapMoreInfoTxt), "Sub Header"));
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objCoveredBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objCoveredBtn), "Button"));
        }
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIwantToCloseLoanButton), "I want to close my loan");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIwantToCloseLoanButton)), propertyFileReader("iWantToCloseLoanBtn", "QuickLoanWithVas"), "Button");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPaymentRecordTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPaymentRecordTxt), "Text"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPaymentRecordTxt)), propertyFileReader("paymentRecord", "QuickLoanWithVas"), "Button");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTodaysDate), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTodaysDate), "Date"));
        String todaysDateActual = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTodaysDate)).replace("Yesterday,", "").trim();
        containsValidation(todaysDateActual, todaysDate(), "Date");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMoneyCredited), "Money Credited Text");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMoneyCreditedAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMoneyCreditedAmount), "Amount"));
        String creditedAmount = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMoneyCreditedAmount));
        creditedAmount = removeSpecialCharacter(creditedAmount, false);
        containsValidation(creditedAmount, formatToTwoDecimalPlaces(amount), "Amount");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFundTransferredTo), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFundTransferredTo), "Text"));
    }

    /**
     * Common method to Documentary Sign Process Validation
     * @throws Exception
     */
    public void documentarySignProcessValidation() throws Exception {
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), "Tile"));
        waitTime(3000);//Hard wait required for execution
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAcceptOfferBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAcceptOfferBtn), "Button"));
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFifthOfTheMonth), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objFifthOfTheMonth), "Radio Button"));
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNextBtn), "Next Button");
        waitTime(3000);//Hard wait required for execution
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objConfirmBtn), "Confirm Button");
        waitTime(3000);//Hard wait required for execution
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
        updateSignature();
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objEraseBtn), "Erase Button");
        updateSignature();
        isButtonEnabled(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignPromissoryNoteBtn), "Sign Promissory Note Button");
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignPromissoryNoteBtn), "Sign Promissory Note Button");
        waitTime(5000);//Hard wait required for execution
        disclosureStatementPageValidation();
        waitTime(5000);//Hard wait required for execution
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
        waitTime(5000);//Hard wait required for execution
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign Button");
        waitTime(2000);//Hard wait required for execution
        updateSignature();
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objEraseBtn), "Erase Button");
        updateSignature();
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignDisclosureStatementBtn), "Sign Disclosure Statement Button");
        waitTime(3000);//Hard wait required for execution
        amortizationScheduleValidation();
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
        waitTime(3000);//Hard wait required for execution
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignedPage), "Page"));
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignAmortizationScheduleBtn), "Sign Amortization Schedule");
        waitTime(5000);//Hard wait required for execution
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
        updateSignature();
        waitTime(3000);//Hard wait required for execution
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objSignAmortizationScheduleBtn), "Sign Amortization Schedule");
        waitTime(3000);//Hard wait required for execution
        yourLoanAlmostReadyPageValidation();
        waitTime(3000);//Hard wait required for execution
    }
    /**
     * Common method to validate hold On Babe Screen
     * @throws Exception
     */
    public void holdOnBabeScreenValidation() throws Exception {
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHoldOnPopupTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHoldOnPopupTitle), ": page"))) {
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHoldOnPopupTitle)), propertyFileReader("HoldOnBabeScreen", "QuickLoanWithVas"), "Page Title");
            containsValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHoldOnPopupSubTitle)), propertyFileReader("HoldOnBabeScreenSubHeader", "QuickLoanWithVas"), "Page Subtitle");
            logger.info("Navigated to Hold on Babe Screen");
            extentLoggerPass("Hold on Babe screen","Navigated to Hold on Babe Screen");
        }else{
            logger.error("Doesn't navigated to Hold on Babe Screen");
            extentLoggerFail("Hold on Babe screen","Doesn't navigated to Hold on Babe Screen");
        }
    }
    /**
     * Common method to validate Rates and Fees
     * @throws Exception
     */
    public void ratesAndFeesValidation(String amount,String loanTenure,String interestRate,String loanType) throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt), 10, "Rates And Fees Page");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMainTitleTxt)), propertyFileReader("ratesAndFeesTxt", "QuickLoanWithVas"), "Text");
        String emiAmount = calculateEMI(Double.parseDouble(amount), Double.parseDouble(loanTenure), Double.parseDouble(interestRate));
        monthlyAddOnRateCalculation(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMonthlyAddOnRate), QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objRatesPageMonthlyAddOnRate), emiAmount, amount, loanTenure);
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAddOnRateSubTxt), getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objAddOnRateSubTxt)));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objProcessingFeeTxt)), propertyFileReader("processingFeeTxt", "QuickLoanWithVas"), "Proceessing Fee");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objRatesPageProcessingFee), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objRatesPageProcessingFee), "Processing Fee Amount"));
        String processingFee = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objRatesPageProcessingFee));
        processingFee = removeSpecialCharacter(processingFee, false);
        assertionValidation(processingFee, propertyFileReader("processingFee", "QuickLoanWithVas"), "Processing Fee");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objEffectiveInterestRate)), propertyFileReader("effectiveInterestRate", "QuickLoanWithVas"), "Text");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objEffectiveInterestRateValue), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objEffectiveInterestRate), "Effective Interest Rate"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objDocStampTaxTxt), 10, "Documentary Stamp Tax");
        documentaryStampTaxCalculation(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objDocStampTaxTxt), QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objDocStampTaxAmount), amount, loanTenure);
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objNetProceedsTxt)), propertyFileReader("netProceeds", "QuickLoanWithVas"), "Text");
        if(loanType.equalsIgnoreCase("WithVAS")) {
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMonthlyPayhingaFee)), propertyFileReader("monthlyPayHingaFee", "QuickLoanWithVas"), "Text");
            String payHingaAmount = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMonthlyPayHingaAmount));
            payHingaAmount = removeSpecialCharacter(payHingaAmount, false);
            assertionValidation(payHingaAmount, formatToTwoDecimalPlaces(payHinga(emiAmount)), "Pay hinga amount");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLateFeeTxt)).replaceAll("f","F"), propertyFileReader("lateFeeTxt", "QuickLoanWithVas"), "Text");
        }else{
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLateFeeTxt)), propertyFileReader("lateFeeTxtWithoutVAS", "QuickLoanWithVas"), "Text");
        }
        String lateFee = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLateFeeAmount));
        lateFee = removeSpecialCharacter(lateFee, false);
        assertionValidation(lateFee, propertyFileReader("lateFee", "QuickLoanWithVas"), "Late Fee Amount");
    }
    /**
     * reusable method to calculate Documentary Stamp Tax
     * @param tenure
     * @param loanAmount
     * @return
     */
    public static String calculateDocumentarySummaryTax(double tenure, double loanAmount){
        double tenureInDays = tenure * 30.44;
        double taxAmount;
        if (tenure >= 12) {
            taxAmount = Math.ceil((loanAmount / 200) * 1.5);
        } else {
            taxAmount = Math.ceil((loanAmount/ 200) * 1.5) * (tenureInDays / 365);
        }
        taxAmount = Math.round(taxAmount);
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(taxAmount);
    }

    /**
     * reusable method for loan amount calculation
     * @param emi
     * @param time
     * @param interest
     * @return
     */
    public static String loanAmountCalculation(String emi, String time,String interest) {
        double rate = Double.parseDouble(interest)/ 100;
        double calculatedLoanAmount = Double.parseDouble(emi) * ((Math.pow(1 + rate, Double.parseDouble(time)) - 1) / (rate * Math.pow(1 + rate, Double.parseDouble(time))));
        BigDecimal roundedLoanAmount = BigDecimal.valueOf(calculatedLoanAmount).setScale(2, RoundingMode.HALF_UP);
        System.out.println("ReLoan Amount :"+roundedLoanAmount);
        return roundedLoanAmount.toString();
    }

    /**
     * reusable method for Reloan calculation
     * @param emi
     * @param time
     * @param interest
     * @return
     */
    public static String reloanCalculation(String emi, String time,String interest) {
        double rate = Double.parseDouble(interest)/ 100;
        double calculatedLoanAmount = Double.parseDouble(emi) * ((Math.pow(1 + rate, Double.parseDouble(time)) - 1) / (rate * Math.pow(1 + rate, Double.parseDouble(time))));
        BigDecimal roundedLoanAmount = BigDecimal.valueOf(calculatedLoanAmount).setScale(2, RoundingMode.HALF_UP);
        System.out.println("ReLoan Amount :"+roundedLoanAmount);
        return roundedLoanAmount.toString();
    }
    public String firstInstallmentDate(){
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDateOfNextMonth = currentDate.plusMonths(2).with(TemporalAdjusters.firstDayOfMonth());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy");
        return firstDateOfNextMonth.format(formatter);
    }
    /**
     * Reusable method to select Offer amount
     * @param userId
     * @return
     * @throws JSchException
     * @throws SQLException
     */
    public String selectOfferAmount(String userId) throws JSchException, SQLException {
        return selectQuery("customer", "SELECT offer_amount FROM loans.tdbk_loan_offers_trx where user_id='"+ userId +"';");
    }
    /**
     * Reusable method to get Maximum loanable amount
     * @param userId
     * @return
     * @throws JSchException
     * @throws SQLException
     */
    public String maxLoanableAmount(String userId) throws JSchException, SQLException {
        return selectQuery("customer","SELECT offer_max_amount FROM loans.tdbk_loan_offers_trx where user_id ='"+ userId +"';");
    }
    /**
     * Reusable method to update Popup Triggered
     * @param userId
     */
    public void updatePopupTriggered(String userId){
        updateQuery("update loans.tdbk_loan_offers_trx set popup_triggered='N' where user_id='" + userId + "';");
    }
    /**
     * Reusable method to update status
     * @param userId
     */
    public void updateStatus(String userId){
        updateQuery("update loans.tdbk_loan_offers_trx set status='1' where user_id='" + userId + "';");
    }
    /**
     * Reusable method to update Offer Status
     * @param userId
     */
    public void updateOfferStatus(String userId){
        updateQuery("update loans.tdbk_loan_offers_trx set offer_status='1' where user_id='" + userId + "';");
    }
    /**
     * Reusable method to get Customer Id
     * @param mobileNumber
     * @return
     * @throws JSchException
     * @throws SQLException
     */
    public String getCustomerId(String mobileNumber) throws JSchException, SQLException {
        return selectQuery("customer", "SELECT cust_id FROM customer.tdbk_customer_mtb where mobile_no='"+ mobileNumber +"';");
    }

    /**
     * Reusable method for promotion Screen Validation
     * @throws Exception
     */
    public void promotionScreenValidation() throws Exception {
        for(int nPromotionScreen=0;nPromotionScreen<3;nPromotionScreen++){
            containsValidation(getText(FlexPivotPage.objPromotionScreenHeader(platform,nPromotionScreen)).replaceAll("’","'"),propertyFileReader("PromotionScreenHeader"+nPromotionScreen,"FlexPivot"),"Promotion Screen header");
            containsValidation(getText(FlexPivotPage.objPromotionScreenSubHeader(platform,nPromotionScreen)).replaceAll("’","'"),propertyFileReader("PromotionScreenSubHeader"+nPromotionScreen,"FlexPivot"),"Promotion Screen SubHeader");
            verifyElementPresentAndClick(FlexPivotPage.objPromotionScreenButton(platform,propertyFileReader("PromotionScreenButton"+nPromotionScreen,"FlexPivot")),getTextVal(FlexPivotPage.objPromotionScreenButton(platform,propertyFileReader("PromotionScreenButton"+nPromotionScreen,"FlexPivot")),"Promotion Screen Button"));
        }
    }
    /**
     * Reusable method for offer Popup Validation
     * @throws Exception
     */
    public void offerPopupValidation() throws Exception {
        waitForElementToBePresent(FlexPivotPage.getByOSType(platform,FlexPivotPage.objPopHeader),5,"It's a match, luv! popup");
        assertionValidation(getText(FlexPivotPage.getByOSType(platform,FlexPivotPage.objPopHeader)), propertyFileReader("PopupHeader","FlexPivot"), ": Popup Header");
        containsValidation(getText(FlexPivotPage.getByOSType(platform,FlexPivotPage.objPopupDescription)), propertyFileReader("PopupDescription","FlexPivot"), ": Popup Description");
        containsValidation(getText(FlexPivotPage.getByOSType(platform,FlexPivotPage.objPopupDescription)).replaceAll(",",""), selectOfferAmount(getUserId("63"+propertyFileReader("FlexPivotMobileNumber","TestDataNumbers"))).replace(".00",""), "Popup Description");
        verifyElementPresent(FlexPivotPage.getByOSType(platform,FlexPivotPage.objApplyNowBtn),getTextVal(FlexPivotPage.getByOSType(platform,FlexPivotPage.objApplyNowBtn), "Popup button"));
    }
    /**
     * Reusable method for ready To Take It Further Screen
     * @throws Exception
     */
    public void readyToTakeItFurtherScreen() throws Exception {
        waitForElementToBePresent(FlexPivotPage.getByOSType(platform,FlexPivotPage.objReadyToTakeItFurther),5, "Ready To Take It Further");
        containsValidation(getText(FlexPivotPage.getByOSType(platform,FlexPivotPage.objReadyToTakeItFurther)),propertyFileReader("ReadyToTakeItFurther","FlexPivot"),": Screen");
        containsValidation(getText(FlexPivotPage.getByOSType(platform,FlexPivotPage.objReadyToTakeItFurtherDescription)),propertyFileReader("ReadyToTakeItFurtherDescription1","FlexPivot"),"Description");
        containsValidation(getText(FlexPivotPage.getByOSType(platform,FlexPivotPage.objReadyToTakeItFurtherDescription)),propertyFileReader("ReadyToTakeItFurtherDescription2","FlexPivot"),"Description");
        containsValidation(getText(FlexPivotPage.getByOSType(platform,FlexPivotPage.objReadyToTakeItFurtherDescription)).replaceAll(",",""), selectOfferAmount(getUserId("63"+propertyFileReader("FlexPivotMobileNumber","TestDataNumbers"))).replace(".00",""),"Description");
        verifyElementPresent(FlexPivotPage.getByOSType(platform,FlexPivotPage.objApplyAnotherLoanBtn),getTextVal(FlexPivotPage.getByOSType(platform,FlexPivotPage.objApplyAnotherLoanBtn),"Button"));
        verifyElementPresent(FlexPivotPage.getByOSType(platform,FlexPivotPage.objContinueLoanApplicationBtn),getTextVal(FlexPivotPage.getByOSType(platform,FlexPivotPage.objContinueLoanApplicationBtn),"Button"));
    }
    /**
     * Reusable method for exclusive Loan Offer Tile Validation
     * @throws Exception
     */
    public void exclusiveLoanOfferTileValidation() throws Exception {
        swipe("DOWN",2);
        containsValidation(getText(FlexPivotPage.getByOSType(platform, FlexPivotPage.objJustForYouTileHeader)), propertyFileReader("JustForYou", "FlexPivot"), "Tile Header");
        containsValidation(getText(FlexPivotPage.getByOSType(platform, FlexPivotPage.objExclusiveLoanOffer)), propertyFileReader("ExclusiveLoanOffer", "FlexPivot"), "Tile Sub Header");
        containsValidation(getText(FlexPivotPage.getByOSType(platform, FlexPivotPage.objExclusiveLoanOfferSubTitle)), propertyFileReader("TextInfo", "FlexPivot"), "Text");
        containsValidation(getText(FlexPivotPage.getByOSType(platform, FlexPivotPage.objExclusiveLoanOfferSubTitle)).replaceAll(",",""), selectOfferAmount(getUserId("63"+propertyFileReader("FlexPivotMobileNumber","TestDataNumbers"))).replace(".00",""), "Text");
        containsValidation(getText(FlexPivotPage.getByOSType(platform, FlexPivotPage.objNowWithPayHinga)), propertyFileReader("NowWithPayHinga", "FlexPivot"), "Text");
    }
    /**
     * Reusable to flex Pivot Loan Tile Validation
     * @throws Exception
     */
    public void flexPivotLoanTileValidation() throws Exception {
        assertionValidation(getText(FlexPivotPage.getByOSType(platform,FlexPivotPage.objYouHave1Match)), propertyFileReader("YouHave1Match","FlexPivot"), "Tile Header");
        assertionValidation(getText(FlexPivotPage.getByOSType(platform,FlexPivotPage.objLoanOfferDescription)), propertyFileReader("LoanOfferDescription","FlexPivot"), "Tile description");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),"Tile"));
    }
    /**
     * Reusable method loan Calculation Screen Validation
     * @throws Exception
     */
    public void loanCalculationScreenValidation() throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYouareApplyingTxt),5,"Loan Calculator Screen");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYouareApplyingTxt)), propertyFileReader("youAreApplyingTxt","QuickLoanWithVas"), "Screen Header");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn),"Button"));
    }
    /**
     * Reusable method to slid Loan Amount Slider
     * @param locator
     * @param value
     */
    public void slidLoanAmountSlider(By locator,double value){
        WebElement slider = DriverManager.getAppiumDriver().findElement(FlexPivotPage.getByOSType(platform,FlexPivotPage.objSlider));
        int startX = slider.getLocation().getX();
        int endX = startX + slider.getSize().getWidth();
        int yAxis = slider.getLocation().getY();
        // Swipe to the desired position (50% in this example)
        int moveToX = (int) (endX * value); // Change 0.5 to whatever percentage you need
        waitTime(5000);
        // Perform the swipe using TouchAction
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "Fingername");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), endX, yAxis))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), moveToX, yAxis))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        DriverManager.getAppiumDriver().perform(Collections.singletonList(sequence));
        waitTime(5000);
    }
    /**
     * Reuasable method to apply loan for BKYC User
     * @param password
     * @param mobileNumber
     * @param module
     * @param tc_id
     * @throws Exception
     */
    public void applyLoanForBKYCUser(String password,String mobileNumber,String module,String tc_id) throws Exception {
        waitForElementToBePresent(LoginPage.getByOSType(platform, LoginPage.objEditPassword), 30, "Password edit field");
        click(LoginPage.getByOSType(platform, LoginPage.objEditPassword), "Password field");
        type(LoginPage.getByOSType(platform, LoginPage.objEditPassword), password, "Enter secret code");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform, LoginPage.objLoginBtn), getTextVal(LoginPage.getByOSType(platform, LoginPage.objLoginBtn), "Button"));
        offerPopupValidation();
        click(FlexPivotPage.getByOSType(platform,FlexPivotPage.objApplyNowBtn),getTextVal(FlexPivotPage.getByOSType(platform,FlexPivotPage.objApplyNowBtn), "Popup button"));
        promotionScreenValidation();
        loanCalculationScreenValidation();
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn),5,"Continue With PayHing Btn");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), "Button"));
        verifyOTPPopup(mobileNumber);
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn),5,"I want To be Protected Btn");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), "Button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn),5,"Sweet I accept button");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn),": button"));
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubHeader),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubHeader),": popup header"))){
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupSubHeader)),propertyFileReader("UpdateIdPopup","QuickLoanWithVas"),": popup header");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYesVerifyMyID)),propertyFileReader("UpdateIdPopupBtn","QuickLoanWithVas"),": button");
            logger.info(tc_id+", "+ module+" Loan With VAS - BKYC user can't apply "+module+" if TSA is not created validated");
            extentLoggerPass(tc_id, tc_id+", "+ module+" Loan With VAS - BKYC user can't apply "+module+" if TSA is not created validated");
        }
    }
    /**
     * Reusable method for user to not to accept offer in 30 days
     * @param password
     * @param mobileNo
     * @param module
     * @param tc_id
     * @throws Exception
     */
    public void userNotAcceptOfferIn30days(String password,String mobileNo,String module,String tc_id) throws Exception {
        waitForElementToBePresent(LoginPage.getByOSType(platform, LoginPage.objEditPassword), 30, "Password edit field");
        click(LoginPage.getByOSType(platform, LoginPage.objEditPassword), "Password field");
        type(LoginPage.getByOSType(platform, LoginPage.objEditPassword), password, "Enter secret code");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform, LoginPage.objLoginBtn), getTextVal(LoginPage.getByOSType(platform, LoginPage.objLoginBtn), "Button"));
        offerPopupValidation();
        click(FlexPivotPage.getByOSType(platform,FlexPivotPage.objApplyNowBtn),getTextVal(FlexPivotPage.getByOSType(platform,FlexPivotPage.objApplyNowBtn), "Popup button"));
        promotionScreenValidation();
        loanCalculationScreenValidation();
        click(FlexPivotPage.objSelectLoanTenure(platform,propertyFileReader("loanTenure","FlexPivot")),getTextVal(FlexPivotPage.objSelectLoanTenure(platform,propertyFileReader("loanTenure","FlexPivot")),"Tenure"));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), "Button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), 10, "Button");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), "Button"));
        action_clickOnPosition(527, 1577);
        if(platform.equalsIgnoreCase("ios")){
            swipe("UP", 1);
        }
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHereisSummaryTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHereisSummaryTxt), "Page"));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), "Sweet! I accept");
        loanSegment2Navigation(generateRandomTINNUMBER(),propertyFileReader("ValidIncomeAmount","QuickLoanWithVas"),
                propertyFileReader("ReferenceNumber1", "QuickLoanWithVas"), propertyFileReader("ReferenceNumber2", "QuickLoanWithVas"));
        waitTime(2000);
        holdOnBabeScreenValidation();
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objInprogressStatus), 80, "Inprogress");
        waitTime(3000);
        inProgressTileValidation();
        approveLoanStatus(mobileNo);
        waitTime(3000);
        if(platform.equalsIgnoreCase("ios")){
            closeiOSTonikApp();
        } else{
            closeAndroidTonikApp();
        }
        waitTime(2000);
        relaunchApp(platform);
        tonikLogin(propertyFileReader("password", "Login"));
        loanApprovedTileValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),"Tile");
        wootYouGotApprovedPageValidation();
        waitTime(5000);
        String userId = getUserId("63"+mobileNo);
        String date = returnDate(-35,"yyyy-MM-dd");
        updatePoi3VerificationDateAndTime(date+" "+getCurrentTime(),userId);
        updateUpdatedTimeStamp(date+" "+getCurrentTime(),userId);
        switchPlatformToWeb("");
        waitTime(2000);
        switchBackToApp();
        waitTime(5000);
        if(platform.equalsIgnoreCase("ios")){
            closeiOSTonikApp();
        } else{
            closeAndroidTonikApp();
        }
        waitTime(3000);
        relaunchApp(platform);
        tonikLogin(propertyFileReader("password", "Login"));
        mainLoanTileValidationLatest();
        logger.info(tc_id+", "+module+" - Verify the Loans tile if the user didn't accept the Loan offer in 30 days");
        extentLoggerPass(tc_id, tc_id+", "+module+" - Verify the Loans tile if the user didn't accept the Loan offer in 30 days");
    }
    /**
     * Reusable method to Quit the loan application
     * @param module
     * @param tc_id
     * @param mobileNumber
     * @throws Exception
     */
    public void quitLoanApplication(String module,String tc_id,String mobileNumber) throws Exception {
        tonikLogin(propertyFileReader("password", "Login"));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),"Tile"));
        click(FlexPivotPage.getByOSType(platform,FlexPivotPage.objContinueLoanApplicationBtn),getTextVal(FlexPivotPage.getByOSType(platform,FlexPivotPage.objContinueLoanApplicationBtn),"Button"));
        loanCalculationScreenValidation();
        slidLoanAmountSlider(FlexPivotPage.getByOSType(platform,FlexPivotPage.objContinueLoanApplicationBtn),0.6);
        loanAmount = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTotalLoanAmount));
        loanAmount = removeSpecialCharacter(loanAmount, false);
        valueValidation(getText(FlexPivotPage.getByOSType(platform,FlexPivotPage.objMaxLoanableAmount)).replace(",",""), maxLoanableAmount(getUserId("63"+mobileNumber)).replace(".00",""), "Max Loanable Amount", "contains");
        click(FlexPivotPage.objSelectLoanTenure(platform,propertyFileReader("loanTenure","FlexPivot")),getTextVal(FlexPivotPage.objSelectLoanTenure(platform,propertyFileReader("loanTenure","FlexPivot")),"Tenure"));
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContinueWithPayHingBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContinueWithPayHingBtn), "Button"));
        protectYourselfPageValidation();
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIwantTobeProtectedBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIwantTobeProtectedBtn), "Button"));
        waitTime(3000);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), "Sweet! I accept");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objWhatDoYouNeedItForPage),5,"What do you need it for screen");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLeaveIcon),5,"Leave Icon");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLeaveIcon),"Leave Icon");
        click(QuickLoanWithVasPage.objLeavingSoonReason(platform,"I have other reasons"),getTextVal(QuickLoanWithVasPage.objLeavingSoonReason(platform,"I have other reasons"),": reason"));
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn),": button"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objGoodByeScreen),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objGoodByeScreen),": Screen"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objGoodByeScreen)),propertyFileReader("GoodByeScreen","QuickLoanWithVas"),"Screen");
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objGoodByeInfo)),propertyFileReader("GoodByeInfo","QuickLoanWithVas"),"Info");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashboard),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashboard),": button"));
        scrollToVertical("Loans");
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),"tile"))){
            logger.info(tc_id+", "+module+" - User can quit the loan application validated");
            extentLoggerPass(tc_id, tc_id+", "+module+" - User can quit the loan application validated");
        }
    }
    /**
     * Reusable method to End to End Journey for loan application
     * @param mobileNo
     * @param loanType
     * @param module
     * @param tc_id
     * @throws Exception
     */
    public void endToEndJourney(String mobileNo,String loanType,String module,String tc_id,String tenure) throws Exception {
        waitForElementToBePresent(LoginPage.getByOSType(platform, LoginPage.objEditPassword), 30, "Password edit field");
        click(LoginPage.getByOSType(platform, LoginPage.objEditPassword), "Password field");
        type(LoginPage.getByOSType(platform, LoginPage.objEditPassword), propertyFileReader("password","login"), "Enter secret code");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform, LoginPage.objLoginBtn), getTextVal(LoginPage.getByOSType(platform, LoginPage.objLoginBtn), "Button"));
        offerPopupValidation();
        click(FlexPivotPage.getByOSType(platform,FlexPivotPage.objApplyNowBtn),getTextVal(FlexPivotPage.getByOSType(platform,FlexPivotPage.objApplyNowBtn), "Popup button"));
        promotionScreenValidation();
        loanCalculationScreenValidation();
        slidLoanAmountSlider(FlexPivotPage.getByOSType(platform,FlexPivotPage.objContinueLoanApplicationBtn),0.6);
        loanAmount = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTotalLoanAmount));
        loanAmount = removeSpecialCharacter(loanAmount, false);
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYouareApplyingTxt)), propertyFileReader("youAreApplyingTxt", "QuickLoanWithVas"), "Page Text", "contains");
        valueValidation(getText(FlexPivotPage.getByOSType(platform,FlexPivotPage.objMaxLoanableAmount)).replace(",",""), maxLoanableAmount(getUserId("63"+mobileNo)).replace(".00",""), "Max Loanable Amount", "contains");
        click(FlexPivotPage.objSelectLoanTenure(platform,tenure),getTextVal(FlexPivotPage.objSelectLoanTenure(platform,tenure),"Tenure"));
        installAmount = getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyInstallmentAmount));
        installAmount = removeSpecialCharacter(installAmount, false);
        slidLoanAmountSlider(FlexPivotPage.getByOSType(platform,FlexPivotPage.objContinueLoanApplicationBtn),0.6);
        if (loanType.equalsIgnoreCase("WithVAS")) {
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContinueWithPayHingBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContinueWithPayHingBtn), "Button"));
            protectYourselfPageValidation();
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIwantTobeProtectedBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIwantTobeProtectedBtn), "Button"));
        } else {
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUnprotectedloanBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUnprotectedloanBtn), "Button"));
            areYouSurePopup();
            click(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objNoThanks),getTextVal(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objNoThanks),"Button"));
        }
        waitTime(4000);
        summaryPageValidation(loanType);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), "Sweet! I accept");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objWhatDoYouNeedItForPage),5,"What do you need it for screen");
        loanSegment2Navigation(generateRandomTINNUMBER(),propertyFileReader("ValidIncomeAmount","QuickLoanWithVas"),
                propertyFileReader("ReferenceNumber1", "QuickLoanWithVas"),propertyFileReader("ReferenceNumber2", "QuickLoanWithVas"));
        waitTime(2000);
        holdOnBabeScreenValidation();
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInprogressStatus),80,"In Progress Tile");
        waitTime(3000);
        inProgressTileValidation();
        waitTime(20000);
        approveLoanStatus(mobileNo);
        waitTime(5000);
        click(TopUpPage.getByOSType(platform,TopUpPage.objTopUpIcon),"Top up Icon");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton),"Back button");
        loanApprovedTileValidation();
        // end of part 1
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),"Tile");
        wootYouGotApprovedPageValidation();
        waitTime(5000);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objAcceptOfferBtn),"I Accept Offer Button");
        waitTime(3000);
        tellUsYourPayDayPageValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn), "Next Button");
        monthlyInstallmentSummaryPageValidation(loanType,extractedDate(), propertyFileReader("monthlyPageSubHeader", "QuickLoanWithVas"));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objConfirmBtn), "Confirm Button");
        signedSealedDeliveredPageValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignPromissoryNoteBtn), "Sign Promissory Note Button");
        waitTime(20000);
        promissoryNotePageValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign");
        yourSignatureRequiredPageValidation();
        waitTime(6000);
        updateSignature();
        isButtonEnabled(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignPromissoryNoteBtn), "Sign Promissory Note Button");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignPromissoryNoteBtn), "Sign Promissory Note Button");
        waitTime(6000);
        disclosureStatementPageValidation();
        waitTime(6000);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign");
        waitTime(6000);
        updateSignature();
        isButtonEnabled(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignDisclosureStatementBtn), "Sign Disclosure Statement Button");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignDisclosureStatementBtn), "Sign Disclosure Statement Button");
        waitTime(5000);
        amortizationScheduleValidation();
        waitTime(6000);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objReadyToSignButton), "I am ready to sign Button");
        waitTime(6000);
        yourSignatureRequiredPageValidation();
        updateSignature();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSignAmortizationScheduleBtn), "Sign Amortization Schedule Button");
        waitTime(6000);
        yourLoanAlmostReadyPageValidation();
        waitTime(6000); // it takes time to load popup
        if(verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIWillDoItBtn))){
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIWillDoItBtn), "I Will Do It Later Button");
        }
//         end of part 2
        flexPivotWithoutVASModule.weAreOfficiallyAThingTileValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),"Tile");
        waitTime(2000);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoButton), "Information Button");
        loanInformationScreen(loanType,propertyFileReader("FlexPivotLoanAmount","FlexPivot"),propertyFileReader("loanTenure","FlexPivot"),propertyFileReader("FlexPivotInterestRate","FlexPivot"));
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objInfoScreeRateAndFees), "Rates and fees");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMainTitleTxt), 10, "Rates And Fees Page");
        ratesAndFeesValidation(propertyFileReader("FlexPivotLoanAmount","FlexPivot"), propertyFileReader("loanTenure", "FlexPivot"), propertyFileReader("FlexPivotInterestRate", "FlexPivot"),loanType);
        waitTime(3000);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
        swipe("UP", 1);
        if(platform.equalsIgnoreCase("iOS")){
            List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansDocuments));
            for (int document = 1; document < values.size(); document++) {
                assertionValidation(getText(QuickLoanWithVasPage.objLoansDocuments(platform, document)), propertyFileReader("LoanDocument" + document, "QuickLoanWithVas"), "Loan document");
                click(QuickLoanWithVasPage.objLoansDocuments(platform, document), getTextVal(QuickLoanWithVasPage.objLoansDocuments(platform, document), "Document"));
                waitTime(5000);
                verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"));
                assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("LoanDocument" + document, "QuickLoanWithVas"), ": Loan document");
                isButtonEnabled(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), ": button"));
                click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), ": button"));
                waitTime(3000);
                click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCopyButtonIOS), "Copy Button");
                waitTime(1000);
                click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objCloseIcon), "Close Icon");
                click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back button");
            }
        } else {
            List<WebElement> values = findElements(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansDocuments));
            for (int document = 1; document < values.size(); document++) {
                assertionValidation(getText(QuickLoanWithVasPage.objLoansDocuments(platform, document)), propertyFileReader("LoanDocument" + document, "QuickLoanWithVas"), "Loan document");
                click(QuickLoanWithVasPage.objLoansDocuments(platform, document), getTextVal(QuickLoanWithVasPage.objLoansDocuments(platform, document), "Document"));
                waitTime(5000);
                verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle), ": page title"));
                assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("LoanDocument" + document, "QuickLoanWithVas"), ": Loan document");
                isButtonEnabled(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), ": button"));
                click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objDownloadBtn), ": button"));
                click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back button");
            }
        }
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back button");
        waitTime(2000);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back button");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceOnScreen), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBalanceOnScreen),"TSA Balance"));
        verifyElementPresentAndClick(OnBoardingPage.objHistoryIcon,"History Icon");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"));
        assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)), propertyFileReader("AccountHistory", "QuickLoanWithVas"), ": page title");
        verifyElementPresent(QuickLoanWithVasPage.objTransaction(platform, "Processing Fee"),getTextVal(QuickLoanWithVasPage.objTransaction(platform, "Processing Fee"),": transaction"));
        verifyElementPresent(QuickLoanWithVasPage.objTransaction(platform, "DST Fee"),getTextVal(QuickLoanWithVasPage.objTransaction(platform, "DST Fee"),": transaction"));
        verifyElementPresent(QuickLoanWithVasPage.objTransaction(platform, "Money Credited"),getTextVal(QuickLoanWithVasPage.objTransaction(platform, "Money Credited"),": transaction"));
        waitTime(3000);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back button");
        logger.info(tc_id+", "+module+" - Verify if SKYC user can apply "+module+" if TSA is created already validated");
        extentLoggerPass(tc_id, tc_id+", "+module+" - Verify if SKYC user can apply "+module+" if TSA is created already validated");
    }
    /**
     * Reusable method to validate inserted offer popup
     * @param password
     * @param module
     * @param tc_id
     * @throws Exception
     */
    public void insertedOfferPopupValidation(String password,String module,String tc_id) throws Exception {
        waitForElementToBePresent(LoginPage.getByOSType(platform, LoginPage.objEditPassword), 30, "Password edit field");
        click(LoginPage.getByOSType(platform, LoginPage.objEditPassword), "Password field");
        type(LoginPage.getByOSType(platform, LoginPage.objEditPassword), password, "Enter secret code");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform, LoginPage.objLoginBtn), getTextVal(LoginPage.getByOSType(platform, LoginPage.objLoginBtn), "Button"));
        offerPopupValidation();
        click(FlexPivotPage.getByOSType(platform,FlexPivotPage.objApplyNowBtn),getTextVal(FlexPivotPage.getByOSType(platform,FlexPivotPage.objApplyNowBtn), "Popup button"));
        promotionScreenValidation();
        loanCalculationScreenValidation();
        verifyElementPresentAndClick(FlexPivotPage.getByOSType(platform,FlexPivotPage.objBackBtn),"Back button");
        needFastCashPageValidation();
        logger.info(tc_id+", "+module+" - Existing user can access the "+module+" from the main Dashboard validated");
        extentLoggerPass(tc_id, tc_id+", "+module+" - Existing user can access the "+module+" from the main Dashboard validated");
    }
    /**
     * Reusable method for offer tile validation
     * @param password
     * @param module
     * @param tc_id
     * @throws Exception
     */
    public void offerTileValidation(String password,String module,String tc_id) throws Exception {
        waitForElementToBePresent(LoginPage.getByOSType(platform, LoginPage.objEditPassword), 30, "Password edit field");
        click(LoginPage.getByOSType(platform, LoginPage.objEditPassword), "Password field");
        type(LoginPage.getByOSType(platform, LoginPage.objEditPassword), password, "Enter secret code");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform, LoginPage.objLoginBtn), getTextVal(LoginPage.getByOSType(platform, LoginPage.objLoginBtn), "Button"));
        offerPopupValidation();
        action_clickOnPosition(33,418);
        flexPivotLoanTileValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),"Tile"));
        readyToTakeItFurtherScreen();
        click(FlexPivotPage.getByOSType(platform,FlexPivotPage.objApplyAnotherLoanBtn),getTextVal(FlexPivotPage.getByOSType(platform,FlexPivotPage.objApplyAnotherLoanBtn),"Button"));
        needFastCashPageValidation();
        exclusiveLoanOfferTileValidation();
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
        flexPivotLoanTileValidation();
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),"Tile"));
        click(FlexPivotPage.getByOSType(platform,FlexPivotPage.objApplyAnotherLoanBtn),getTextVal(FlexPivotPage.getByOSType(platform,FlexPivotPage.objApplyAnotherLoanBtn),"Button"));
        verifyElementPresentAndClick(FlexPivotPage.getByOSType(platform,FlexPivotPage.objExclusiveLoanOffer),getTextVal(FlexPivotPage.getByOSType(platform,FlexPivotPage.objExclusiveLoanOffer),"Tile"));
        promotionScreenValidation();
        loanCalculationScreenValidation();
        verifyElementPresentAndClick(FlexPivotPage.getByOSType(platform,FlexPivotPage.objBackBtn),"Back button");
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objBackButton), "Back Button");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),"Tile"));
        click(FlexPivotPage.getByOSType(platform,FlexPivotPage.objContinueLoanApplicationBtn),getTextVal(FlexPivotPage.getByOSType(platform,FlexPivotPage.objContinueLoanApplicationBtn),"Button"));
        loanCalculationScreenValidation();
        logger.info(tc_id+", "+module+" - Existing user can access the "+module+" from the main Dashboard with no Pop up / or by clicking out of the pop up validated");
        extentLoggerPass(tc_id, tc_id+", "+module+" - Existing user can access the "+module+" from the main Dashboard with no Pop up / or by clicking out of the pop up validated");
    }
    /**
     * Reusable method to apply loan
     * @param loanType
     * @param tenure
     * @param module
     * @param tc_id
     * @throws Exception
     */
    public void applyLoan(String loanType,String tenure,String module,String tc_id) throws Exception {
        tonikLogin(propertyFileReader("password", "Login"));
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoansTile), "Tile"));
        click(FlexPivotPage.getByOSType(platform, FlexPivotPage.objContinueLoanApplicationBtn), getTextVal(FlexPivotPage.getByOSType(platform, FlexPivotPage.objContinueLoanApplicationBtn), "Button"));
        loanCalculationScreenValidation();
        slidLoanAmountSlider(FlexPivotPage.getByOSType(platform, FlexPivotPage.objContinueLoanApplicationBtn), 0.6);
        loanAmount = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTotalLoanAmount));
        loanAmount = removeSpecialCharacter(loanAmount, false);
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTotalLoanAmount), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTotalLoanAmount), "Loan Amount"));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objYourInstallmentTxt)), propertyFileReader("chooseInstallmentTxt", "QuickLoanWithVas"), "Page Text", "contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.obActualAmountTxt)), propertyFileReader("actualAmountTxt", "QuickLoanWithVas"), "Page Text", "contains");
        FlexPivotWithoutVASModule.vasEmiVerification(loanAmount, QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objLoanTenure), QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMonthlyInstallmentAmount), QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayhingCalcTxt), Double.parseDouble(propertyFileReader("FlexPivotInterestRate", "FlexPivot")));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayHingaTxtOnInsurance)), propertyFileReader("payHingaTxt", "QuickLoanWithVas"), "Text on Pay Hinga Tab", "contains");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayhingCalcTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayhingCalcTxt), "Text on Pay Hinga Tab"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPayHingaInformationIcon), "Information icon");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objUnprotectedloanBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objUnprotectedloanBtn), "Button"));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objUnprotectedloanBtn)), propertyFileReader("applyForButtonTxt", "QuickLoanWithVas"), "Button text", "contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContinueWithPayHingBtn)), propertyFileReader("continueWithpayHingaBtn", "QuickLoanWithVas"), "Button text", "contains");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objApplyingLoanPageBackBtn), "Back Button");
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContactUsBtn1), 10, "Contact us Button");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContactUsBtn1), "Contact Us Button");
        click(FlexPivotPage.objSelectLoanTenure(platform,tenure),getTextVal(FlexPivotPage.objSelectLoanTenure(platform,tenure),"Tenure"));
        installAmount = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objMonthlyInstallmentAmount));
        installAmount = removeSpecialCharacter(installAmount,false);
        if (loanType.equalsIgnoreCase("WithVAS")) {
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContinueWithPayHingBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objContinueWithPayHingBtn), "Button"));
            protectYourselfPageValidation();
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIwantTobeProtectedBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objIwantTobeProtectedBtn), "Button"));
        } else {
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUnprotectedloanBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUnprotectedloanBtn), "Button"));
            areYouSurePopup();
            click(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objNoThanks),getTextVal(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objNoThanks),"Button"));
        }
        action_clickOnPosition(527, 1577);
        summaryPageValidation(loanType);
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHereisSummaryTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHereisSummaryTxt), "Page Title"));
        waitTime(4000);//Hard wait required for execution
        click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHowItWorksBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objHowItWorksBtn), "Button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objRelaxPage), 10, "Relax Page");
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRelaxPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRelaxPage), "Page Text"))) {
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRelaxPage)), propertyFileReader("relaxPageTxt", "QuickLoanWithVas"), "Page Header", "contains");
            logger.info(tc_id+", "+module+" - Verify if user can apply the loan with VAS from the Loan Calculator screen validated");
            extentLoggerPass(tc_id, tc_id+", "+module+" - Verify if user can apply the loan with VAS from the Loan Calculator screen validated");
        }
    }
    /**
     * Reusable method to accept loan offer
     * @param loanType
     * @param tc_id
     * @param tenure
     * @throws Exception
     */
    public void acceptLoanOffer(String loanType,String tc_id,String tenure) throws Exception {
        tonikLogin(propertyFileReader("password", "Login"));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),"Tile"));
        click(FlexPivotPage.getByOSType(platform,FlexPivotPage.objContinueLoanApplicationBtn),getTextVal(FlexPivotPage.getByOSType(platform,FlexPivotPage.objContinueLoanApplicationBtn),"Button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), 10, "Button");
        slidLoanAmountSlider(FlexPivotPage.getByOSType(platform,FlexPivotPage.objContinueLoanApplicationBtn),0.6);
        click(FlexPivotPage.objSelectLoanTenure(platform,tenure),getTextVal(FlexPivotPage.objSelectLoanTenure(platform,propertyFileReader("loanTenure","FlexPivot")),"Tenure"));
        if(loanType.equalsIgnoreCase("WithVAS")){
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), "Button"));
            waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), 10, "Button");
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), "Button"));
        }else{
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUnprotectedloanBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUnprotectedloanBtn), "Button"));
            areYouSurePopup();
            click(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objNoThanks),getTextVal(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objNoThanks),"Button"));
        }
        action_clickOnPosition(527, 1577);
        if(platform.equalsIgnoreCase("ios")){
            swipe("UP", 1);
        }
        scrollToVertical(propertyFileReader("figuresMayChangeTxt", "QuickLoanWithVas"));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHowItWorksBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHowItWorksBtn), "Button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRelaxPage), 10, "Relax Page");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRelaxPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objRelaxPage), "Page Text"));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackButton), "Back Button");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), "Button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHereisSummaryTxt), 10, "Button");
        if (verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHereisSummaryTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHereisSummaryTxt), "Page"))) {
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHereisSummaryTxt)), propertyFileReader("whatDoYouNeedPage", "QuickLoanWithVas"), "Page Header", "contains");
            logger.info(tc_id+" Successfully navigated to " + getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHereisSummaryTxt), "Page"));
            extentLoggerPass("What do yoy need page", tc_id+" Successfully navigated to " + getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHereisSummaryTxt), "Page"));
        }
    }
    /**
     * Reusable method to reapply loan after full repayment
     * @param password
     * @param module
     * @param tc_id
     * @throws Exception
     */
    public void reapplyAfterFullRepayment(String password,String module,String tc_id) throws Exception {
        waitForElementToBePresent(LoginPage.getByOSType(platform, LoginPage.objEditPassword), 30, "Password edit field");
        click(LoginPage.getByOSType(platform, LoginPage.objEditPassword), "Password field");
        type(LoginPage.getByOSType(platform, LoginPage.objEditPassword), password, "Enter secret code");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform, LoginPage.objLoginBtn), getTextVal(LoginPage.getByOSType(platform, LoginPage.objLoginBtn), "Button"));
        offerPopupValidation();
        click(FlexPivotPage.getByOSType(platform,FlexPivotPage.objApplyNowBtn),getTextVal(FlexPivotPage.getByOSType(platform,FlexPivotPage.objApplyNowBtn), "Popup button"));
        promotionScreenValidation();
        loanCalculationScreenValidation();
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanCalculatorPage), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanCalculatorPage), "Page"))) {
            valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYourInstallmentTxt)), propertyFileReader("chooseInstallmentTxt", "QuickLoanWithVas"), "Page Text", "contains");
            logger.info(tc_id+", Verify if user can reapply "+module+" loan after the full loan repayment validated");
            extentLoggerPass(tc_id, tc_id+", Verify if user can reapply "+module+" after the full loan repayment validated");
        }
    }
    /**
     * Reusable method to validate soft rejection of loan
     * @param password
     * @param module
     * @param tc_id
     * @param mobileNumber
     * @throws Exception
     */
    public void softRejectValidation(String password,String module,String tc_id,String mobileNumber) throws Exception {
        waitForElementToBePresent(LoginPage.getByOSType(platform, LoginPage.objEditPassword), 30, "Password edit field");
        click(LoginPage.getByOSType(platform, LoginPage.objEditPassword), "Password field");
        type(LoginPage.getByOSType(platform, LoginPage.objEditPassword), password, "Enter secret code");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform, LoginPage.objLoginBtn), getTextVal(LoginPage.getByOSType(platform, LoginPage.objLoginBtn), "Button"));
        offerPopupValidation();
        click(FlexPivotPage.getByOSType(platform,FlexPivotPage.objApplyNowBtn),getTextVal(FlexPivotPage.getByOSType(platform,FlexPivotPage.objApplyNowBtn), "Popup button"));
        promotionScreenValidation();
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), 10, "Button");
        click(FlexPivotPage.getByOSType(platform,FlexPivotPage.objContinueLoanApplicationBtn),getTextVal(FlexPivotPage.getByOSType(platform,FlexPivotPage.objContinueLoanApplicationBtn),"Button"));
        slidLoanAmountSlider(FlexPivotPage.getByOSType(platform,FlexPivotPage.objContinueLoanApplicationBtn),0.6);        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), "Button"));
        if(verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objVerifyNow))) {
            verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objVerifyNow), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objVerifyNow), ": button"));
            waitTime(3000);
            String emailAddress = getEmailAddress("63" + mobileNumber);
            String otp = getOTP(emailAddress);
            enterOTP(otp);
            click(OnBoardingPage.objCloseBtn,getTextVal(OnBoardingPage.objCloseBtn,"Button"));
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), "Button"));
        }
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn),5,"I want To be Protected Btn");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), "Button"));
        waitTime(4000);
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn),5,"Sweet I accept button");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), "Button"));
        loanSegment2Navigation(propertyFileReader("SoftRejectTIN","QuickLoanWithVas"),propertyFileReader("ValidIncomeAmount","QuickLoanWithVas"), propertyFileReader("RejectNumber1", "QuickLoanWithVas"),propertyFileReader("RejectNumber2", "QuickLoanWithVas"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHardToSayScreen),5,"Hard to say no");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHardToSayScreen),"Hard To Say Screen");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashboard),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashboard),": button"));
        waitForElementToBePresent(LoginPage.getByOSType(platform,LoginPage.objProfileName),10,"Dashboard");
        scrollToVertical(propertyFileReader("loanTile", "QuickLoanWithVas"));
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSoftRejectLoanStatus),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSoftRejectLoanStatus),"Status"))) {
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSoftRejectLoanInfo),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSoftRejectLoanInfo),": info"));
            String userId = getUserId("63" + mobileNumber);
            assertionValidation(getLoanStatus(userId), propertyFileReader("RejectedStatus", "QuickLoanWithVas"), "Status");
            logger.info("Rejection type"+getLoanRejectionType(userId));
            extentLogger("","Rejection type"+getLoanRejectionType(userId));
            logger.info(tc_id+", "+module+" - Loan tile status if the user is soft rejected validated");
            extentLoggerPass(tc_id, tc_id+", "+module+" - Loan tile status if the user is soft rejected validated");
        }
    }
    /**
     * Reusable method to validate hard rejection of loan
     * @param password
     * @param module
     * @param tc_id
     * @throws Exception
     */
    public void hardRejectValidation(String password,String module,String tc_id) throws Exception {
        waitForElementToBePresent(LoginPage.getByOSType(platform, LoginPage.objEditPassword), 30, "Password edit field");
        click(LoginPage.getByOSType(platform, LoginPage.objEditPassword), "Password field");
        type(LoginPage.getByOSType(platform, LoginPage.objEditPassword), password, "Enter secret code");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform, LoginPage.objLoginBtn), getTextVal(LoginPage.getByOSType(platform, LoginPage.objLoginBtn), "Button"));
        offerPopupValidation();
        click(FlexPivotPage.getByOSType(platform,FlexPivotPage.objApplyNowBtn),getTextVal(FlexPivotPage.getByOSType(platform,FlexPivotPage.objApplyNowBtn), "Popup button"));
        promotionScreenValidation();
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), 10, "Button");
        click(FlexPivotPage.getByOSType(platform,FlexPivotPage.objContinueLoanApplicationBtn),getTextVal(FlexPivotPage.getByOSType(platform,FlexPivotPage.objContinueLoanApplicationBtn),"Button"));
        slidLoanAmountSlider(FlexPivotPage.getByOSType(platform,FlexPivotPage.objContinueLoanApplicationBtn),0.6);        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), "Button"));
        if(verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objVerifyNow))) {
            verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objVerifyNow), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objVerifyNow), ": button"));
            waitTime(3000);
            String emailAddress = getEmailAddress("63" + propertyFileReader("HardRejectMobileNumber", "TestDataNumbers"));
            String otp = getOTP(emailAddress);
            enterOTP(otp);
            click(OnBoardingPage.objCloseBtn,getTextVal(OnBoardingPage.objCloseBtn,"Button"));
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn), "Button"));
        }
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn),5,"I want To be Protected Btn");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIwantTobeProtectedBtn), "Button"));
        waitTime(4000);
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn),5,"Sweet I accept button");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), "Button"));
        loanSegment2Navigation(propertyFileReader("ValidTIN","QuickLoanWithVas"),propertyFileReader("ValidIncomeAmount","QuickLoanWithVas"),
                propertyFileReader("RejectNumber1", "QuickLoanWithVas"),propertyFileReader("RejectNumber2", "QuickLoanWithVas"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHardToSayScreen),5,"Hard to say no");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objHardToSayScreen),"Hard To Say Screen");
        verifyElementPresentAndClick(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashboard),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objBackToDashboard),": button"));
        waitForElementToBePresent(LoginPage.getByOSType(platform,LoginPage.objProfileName),10,"Dashboard");
        scrollToVertical(propertyFileReader("loanTile", "QuickLoanWithVas"));
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSoftRejectLoanStatus),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSoftRejectLoanStatus),"Status"))) {
            verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSoftRejectLoanInfo),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSoftRejectLoanInfo),": info"));
            String userId = getUserId("63" + propertyFileReader("HardRejectMobileNumber", "TestDataNumbers"));
            assertionValidation(getLoanStatus(userId), propertyFileReader("RejectedStatus", "QuickLoanWithVas"), "Status");
            logger.info("Rejection type : "+getLoanRejectionType(userId));
            extentLogger("","Rejection type : "+getLoanRejectionType(userId));
            logger.info(tc_id+", "+module+" - Loan tile status if the user is hard rejected validated");
            extentLoggerPass(tc_id, tc_id+", "+module+" - Loan tile status if the user is hard rejected validated");
        }
    }
    /**
     * Reusable method to decline with VAS offer
     * @param mobileNumber
     * @param module
     * @param tc_id
     * @throws Exception
     */
    public void declineWithVASOfferValidation(String mobileNumber,String module,String tc_id,String tenure) throws Exception {
        tonikLogin(propertyFileReader("password", "Login"));
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoansTile),"Tile"));
        click(FlexPivotPage.getByOSType(platform,FlexPivotPage.objContinueLoanApplicationBtn),getTextVal(FlexPivotPage.getByOSType(platform,FlexPivotPage.objContinueLoanApplicationBtn),"Button"));
        loanCalculationScreenValidation();
        slidLoanAmountSlider(FlexPivotPage.getByOSType(platform,FlexPivotPage.objContinueLoanApplicationBtn),0.6);
        loanAmount = getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objTotalLoanAmount));
        loanAmount = removeSpecialCharacter(loanAmount, false);
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYouareApplyingTxt)), propertyFileReader("youAreApplyingTxt", "QuickLoanWithVas"), "Page Text", "contains");
        valueValidation(getText(FlexPivotPage.getByOSType(platform,FlexPivotPage.objMaxLoanableAmount)).replace(",",""), maxLoanableAmount(getUserId("63"+mobileNumber)).replace(".00",""), "Max Loanable Amount", "contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objYourInstallmentTxt)), propertyFileReader("chooseInstallmentTxt", "QuickLoanWithVas"), "Page Text", "contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.obActualAmountTxt)), propertyFileReader("actualAmountTxt", "QuickLoanWithVas"),"Page Text", "contains");
        flexPivotWithoutVASModule.vasEmiVerification(loanAmount, QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objLoanTenure), QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyInstallmentAmount), QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPayhingCalcTxt),Double.parseDouble(propertyFileReader("FlexPivotInterestRate","FlexPivot")));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPayHingaTxtOnInsurance)), propertyFileReader("payHingaTxt", "QuickLoanWithVas"),"Text on Pay Hinga Tab", "contains");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPayhingCalcTxt), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPayhingCalcTxt), "Text on Pay Hinga Tab"));
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPayHingaInformationIcon), "Information icon");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUnprotectedloanBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUnprotectedloanBtn), "Button"));
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUnprotectedloanBtn)), propertyFileReader("applyForButtonTxt", "QuickLoanWithVas"), "Button text","contains");
        valueValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContinueWithPayHingBtn)),propertyFileReader("continueWithpayHingaBtn", "QuickLoanWithVas"), "Button text", "contains");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objApplyingLoanPageBackBtn), "Back Button");
        verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objContactUsBtn1), "Contact us Button");
        click(FlexPivotPage.objSelectLoanTenure(platform,tenure),getTextVal(FlexPivotPage.objSelectLoanTenure(platform,tenure),"Tenure"));
        installAmount = getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objMonthlyInstallmentAmount));
        installAmount = removeSpecialCharacter(installAmount, false);
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUnprotectedloanBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objUnprotectedloanBtn), "Button"));
        areYouSurePopup();
        click(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objNoThanks),getTextVal(QuickLoanWithoutVasPage.getByOSType(platform,QuickLoanWithoutVasPage.objNoThanks),"Button"));
        summaryPageValidation("WithoutVAS");
        click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objSweetAcceptBtn), "Button"));
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objWhatDoYouNeedItForPage),5,"What do you need it for screen");
        if(verifyElementPresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),getTextVal(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle),": page title"))){
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageTitle)),propertyFileReader("WhatDoYouNeedItForPage","QuickLoanWithVas"),": page title");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPageSubTitle)),propertyFileReader("WhatDoYouNeedItForPageSubTitle","QuickLoanWithVas"),": page subtitle");
            logger.info(tc_id+", "+module+" without VAS - User can decline the "+module+" Loan with VAS offer from the 'Are you sure?' pop up validated");
            extentLoggerPass(tc_id, tc_id+", "+module+" without VAS - User can decline the "+module+" Loan with VAS offer from the 'Are you sure?' pop up validated");
        }
    }
    /**
     * MEthod to handle I will do it later popup
     * @throws Exception
     */
    public void iWillDoItLaterPopup() throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIWillDoItBtn),5,"I Will Do it Later Popup");
        if(verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIWillDoItBtn))){
            click(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objIWillDoItBtn), "I Will Do It Later Button");
        }
    }
    public void fullRepaymentConfirmationPopup() throws Exception {
        waitForElementToBePresent(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupTitle),5,"Full repayment confirmation popup");
        if(verifyElementDisplayed(QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objPopupTitle))){
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPopupTitle)), propertyFileReader("RepaymentPopup", "QuickLoanWithVas"), "Full repayment confirmation popup title");
            assertionValidation(getText(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objPopupDescription)), propertyFileReader("RepaymentPopupDescription", "QuickLoanWithVas"), "Full repayment confirmation popup description");
            click(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objWantToPayInFull), getTextVal(QuickLoanWithVasPage.getByOSType(platform, QuickLoanWithVasPage.objWantToPayInFull), "Button"));
        }
    }
}