package com.tonik.bizfunctions;

import com.driverInstance.DriverManager;
import com.tonik.pageObject.BillsPaymentPage;
import com.tonik.utility.ExtentReporter;
import com.tonik.utility.Utilities;
import org.openqa.selenium.WebElement;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.tonik.utility.DB_Utilites.selectQuery;
import static com.tonik.utility.ExtentReporter.HeaderChildNode;
import static com.tonik.utility.ExtentReporter.extentLoggerPass;
import static com.tonik.utility.Utilities.*;
import static com.tonik.utility.Utilities.verifyElementDisplayed;

public class BillsPaymentModule extends BaseClass {
	public BillsPaymentModule() {
        super();
    }
	String platform = Utilities.getPlatform();
	Set<String> popularBillersList = new LinkedHashSet<>();
	String conFee;
	double currentAmountValue;
	double updatedAmountValue;
	double billPaymentAmount;
	double convenienceFee;
	/**
	 * reusable method to verify Bills with no frills page
	 * @throws Exception
	 */
	public void billsWithNoFrillsScreen() throws Exception {
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillsWithNoFrillsTitle), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillsWithNoFrillsTitle), ":Title"));
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillsWithNoFrillsTitle)), propertyFileReader("billsWithNoFrills", "billsPayment"), "Title", "contains");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objKeepTrackText), getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objKeepTrackText)));
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objKeepTrackText)), propertyFileReader("keepTrackOfYourBills1", "billsPayment"), "Text", "contains");
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objKeepTrackText)), propertyFileReader("keepTrackOfYourBills2", "billsPayment"), "Text", "contains");
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNeverMissTitle)), propertyFileReader("neverMissDueDate", "billsPayment"), "Title", "contains");
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNeverMissDesc)), propertyFileReader("avoidLatePaymentFees", "billsPayment"), "Description", "contains");
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objGiveYourBillTitle)), propertyFileReader("giveYourBillPetName", "billsPayment"), "Title", "contains");
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objGiveYourBillDesc)), propertyFileReader("manageMultipleBills", "billsPayment"), "Description", "contains");
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objKeepAddingBillersTitle)).replace("’","'").trim(), propertyFileReader("keepAddingBillers", "billsPayment"), "Title", "contains");
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objKeepAddingBillersDesc)).replace("’","'").trim(), propertyFileReader("payYourUtility", "billsPayment"), "Description", "contains");
		if(platform.equalsIgnoreCase("Android")){
			verticalSwipeTillElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objStartABilltext));
		}else{
			swipe("UP",1);
		}
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objStartABilltext), "Start a bill");
	}

	/**
	 * reusable method to verify Bills pay page
	 * @throws Exception
	 */
	public void billsPayScreen() throws Exception {
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText), getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText)));
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText)), propertyFileReader("billPayTitle", "billsPayment"), "Title", "contains");
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSubTitleText)), propertyFileReader("billPaySubTitle", "billsPayment"), "Sub Title", "contains");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAddABillerText), "Add a Biller");
	}

	/**
	 * reusable method to verify need some fund popup
	 * @throws Exception
	 */
	public void needSomeFundPopup() throws Exception {
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPopUpTitle), getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPopUpTitle)));
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPopUpTitle)), propertyFileReader("needSomeFund", "billsPayment"), "Title", "contains");
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPopUpDesc)), propertyFileReader("needSomeFundDescription", "billsPayment"), "Description", "contains");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objOkLetsGoText), "Ok let's go");
	}

	/**
	 * reusable method to verify billers page
	 * @throws Exception
	 */
	public void billersScreen() throws Exception {
		waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShowAllText), 10);
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText), ":Header"));
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText)), propertyFileReader("billersHeader", "billsPayment"), "Header", "equals");
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSubTitleText)), propertyFileReader("selectABillerToPay", "billsPayment"), "sub Title", "equals");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShowAllText)), propertyFileReader("showAll", "billsPayment"), "Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPopularBillerText)), propertyFileReader("popularBillers", "billsPayment"), "Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objCreditCardsText)), propertyFileReader("creditCards", "billsPayment"), "Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objETollText)), propertyFileReader("eToll", "billsPayment"), "Text");
		newScrollMethodHorizontal(0.8, 0.4, 0.15, 0.4);
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objElectricUtilityText)), propertyFileReader("electricUtility", "billsPayment"), "Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objGovernmentText)), propertyFileReader("government", "billsPayment"), "Text");
		newScrollMethodHorizontal(0.8, 0.4, 0.15, 0.4);
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objTelecomsText)), propertyFileReader("telecoms", "billsPayment"), "Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objWaterUtilityText)), propertyFileReader("waterUtility", "billsPayment"), "Text");
	}

	/**
	 * reusable method for display all the billers
	 */
	public void showAllBillers() {
		boolean flag = false;
		List<WebElement> allBillers = findElements(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShowAllBillers));
		if (platform.equalsIgnoreCase("Android")) {
			for (int scroll = 0; scroll < 6; scroll++) {
				for (WebElement billers : allBillers) {
					try {
						if (billers.isDisplayed()) {
							String biller = billers.getText();
							logger.info(biller + " is Displayed");
							ExtentReporter.extentLoggerPass("", biller + " is Displayed");
							flag = false;
						} else {
							flag = true;
						}
					} catch (Exception e) {
						flag = true;
					}
					if (flag) {
						newScrollMethodVertical(0.5, 0.8, 0.5, 0.35);
						allBillers = findElements(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShowAllBillers));
					}
				}
				newScrollMethodVertical(0.5, 0.8, 0.5, 0.35);
				allBillers = findElements(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShowAllBillers));
			}
		}else{
			for (WebElement billers : allBillers) {
				verticalSwipeTillElementDispalyed1(billers);
				String biller = billers.getText();
				logger.info(biller + " is Displayed");
				ExtentReporter.extentLoggerPass("", biller + " is Displayed");
			}
		}
	}

	/**
	 * reusable method to add all the unique billers in a list
	 */
	public void addAllBillers() {
		boolean flag = false;
		List<WebElement> allBillers = findElements(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShowAllBillers));
		if (platform.equalsIgnoreCase("Android")) {
			for (int scroll = 0; scroll < 6; scroll++) {
				for (WebElement billers : allBillers) {
					try {
						if(billers.isDisplayed()){
							popularBillersList.add(billers.getText());
							flag = false;
						}else {
							flag = true;
						}
					} catch (Exception e) {
						flag = true;
					}
					if(flag) {
						newScrollMethodVertical(0.5, 0.8, 0.5, 0.35);
						allBillers = findElements(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShowAllBillers));
					}
				}
				newScrollMethodVertical(0.5, 0.8, 0.5, 0.35);
				allBillers = findElements(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShowAllBillers));
			}
		}else{
			for (WebElement billers : allBillers) {
				verticalSwipeTillElementDispalyed1(billers);
				popularBillersList.add(billers.getText());
			}
		}
	}

	/**
	 * reusable method to verify all the billers category
	 * @throws Exception
	 */
	public void verifyAllBillersCategory() throws Exception {
		click(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPopularBillerButton), "Popular Billers");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPopularBillersTitle), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPopularBillersTitle), ":Title"));
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPopularBillersTitle)), propertyFileReader("popularBillers", "billsPayment"), "Title");
		List<WebElement> allBillers = findElements(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShowAllBillers));
		List<WebElement> allProcessing = findElements(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAllProcessing));
		Set<String> popularBillersList = new LinkedHashSet<>();
		int count = 0;
		if(platform.equalsIgnoreCase("Android")){
			for (int scroll = 0; scroll < 3; scroll++) {
				for(int list=0; list<allBillers.size(); list++) {
					popularBillersList.add(allBillers.get(list).getText());
					if (allProcessing.get(list).getText().equals(propertyFileReader("processingForDays", "billsPayment"))) {
						assertionValidation(allProcessing.get(list).getText(), propertyFileReader("processingForDays", "billsPayment"), "Processing Type");
					} else {
						assertionValidation(allProcessing.get(list).getText(), propertyFileReader("processingForRealtime", "billsPayment"), "Processing Type");
					}
				}
				newScrollMethodVertical(0.5, 0.8, 0.5, 0.35);
				allBillers = findElements(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShowAllBillers));
				allProcessing = findElements(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAllProcessing));
			}
			for (String popularBillers : popularBillersList) {
				assertionValidation(popularBillers, propertyFileReader("popularBillersName"+count, "billsPayment"), "billers name");
				count++;
			}
		}else{
			for(WebElement biller : allBillers){
				verticalSwipeTillElementDispalyed1(biller);
				assertionValidation(biller.getText(), propertyFileReader("popularBillersName"+count, "billsPayment"), "billers name");
				count++;
			}
			for(WebElement processing : allProcessing){
				if (processing.getText().equals(propertyFileReader("processingForDays", "billsPayment"))) {
					assertionValidation(processing.getText(), propertyFileReader("processingForDays", "billsPayment"), "Processing Type");
				} else {
					assertionValidation(processing.getText(), propertyFileReader("processingForRealtime", "billsPayment"), "Processing Type");
				}
			}
		}
		click(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objCreditCardsButton), "Credit Cards");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objCreditCardsTitle), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objCreditCardsTitle), ":Title"));
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objCreditCardsTitle)), propertyFileReader("creditCards", "billsPayment"), "Title");
		allBillers = findElements(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShowAllBillers));
		allProcessing = findElements(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAllProcessing));
		for(int list=0; list<allBillers.size(); list++) {
			assertionValidation(allBillers.get(list).getText(), propertyFileReader("CreditCardsName"+list, "billsPayment"), "Credit Cards name");
			if(allProcessing.get(list).getText().equals(propertyFileReader("processingForDays", "billsPayment"))) {
				assertionValidation(allProcessing.get(list).getText(), propertyFileReader("processingForDays", "billsPayment"), "Processing Type");
			}else {
				assertionValidation(allProcessing.get(list).getText(), propertyFileReader("processingForRealtime", "billsPayment"), "Processing Type");
			}
		}
		click(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objETollButton), "e-Toll");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objETollTitle), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objETollTitle), ":Title"));
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objETollTitle)), propertyFileReader("eToll", "billsPayment"), "Title");
		allBillers = findElements(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShowAllBillers));
		allProcessing = findElements(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAllProcessing));
		for(int list=0; list<allBillers.size(); list++) {
			assertionValidation(allBillers.get(list).getText(), propertyFileReader("popularBillersName2", "billsPayment"), "e-Toll");
			if(allProcessing.get(list).getText().equals(propertyFileReader("processingForDays", "billsPayment"))) {
				assertionValidation(allProcessing.get(list).getText(), propertyFileReader("processingForDays", "billsPayment"), "Processing Type");
			}else {
				assertionValidation(allProcessing.get(list).getText(), propertyFileReader("processingForRealtime", "billsPayment"), "Processing Type");
			}
		}
		newScrollMethodHorizontal(0.8, 0.4, 0.15, 0.4);
		click(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objElectricUtilityButton), "Electric Utility");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objElectricUtilityTitle), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objElectricUtilityTitle), ":Title"));
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objElectricUtilityTitle)), propertyFileReader("electricUtility", "billsPayment"), "Title");
		allBillers = findElements(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShowAllBillers));
		allProcessing = findElements(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAllProcessing));
		for(int list=0; list<allBillers.size(); list++) {
			assertionValidation(allBillers.get(list).getText(), propertyFileReader("electricUtility"+list, "billsPayment"), "electic utility");
			if(allProcessing.get(list).getText().equals(propertyFileReader("processingForDays", "billsPayment"))) {
				assertionValidation(allProcessing.get(list).getText(), propertyFileReader("processingForDays", "billsPayment"), "Processing Type");
			}else {
				assertionValidation(allProcessing.get(list).getText(), propertyFileReader("processingForRealtime", "billsPayment"), "Processing Type");
			}
		}
		click(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objGovernmentButton), "Government");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objGovernmentTitle), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objGovernmentTitle), ":Title"));
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objGovernmentTitle)), propertyFileReader("government", "billsPayment"), "Title");
		allBillers = findElements(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShowAllBillers));
		allProcessing = findElements(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAllProcessing));
		for(int list=0; list<allBillers.size(); list++) {
			assertionValidation(allBillers.get(list).getText(), propertyFileReader("government"+list, "billsPayment"), "government");
			if(allProcessing.get(list).getText().equals(propertyFileReader("processingForDays", "billsPayment"))) {
				assertionValidation(allProcessing.get(list).getText(), propertyFileReader("processingForDays", "billsPayment"), "Processing Type");
			}else {
				assertionValidation(allProcessing.get(list).getText(), propertyFileReader("processingForRealtime", "billsPayment"), "Processing Type");
			}
		}
		newScrollMethodHorizontal(0.8, 0.4, 0.15, 0.4);
		click(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objTelecomsButton), "Telecoms");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objTelecomsTitle), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objTelecomsTitle), ":Title"));
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objTelecomsTitle)), propertyFileReader("telecoms", "billsPayment"), "Title");
		allBillers = findElements(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShowAllBillers));
		allProcessing = findElements(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAllProcessing));
		for(int list=0; list<allBillers.size(); list++) {
			assertionValidation(allBillers.get(list).getText(), propertyFileReader("telecoms"+list, "billsPayment"), "Telecoms");
			if(allProcessing.get(list).getText().equals(propertyFileReader("processingForDays", "billsPayment"))) {
				assertionValidation(allProcessing.get(list).getText(), propertyFileReader("processingForDays", "billsPayment"), "Processing Type");
			}else {
				assertionValidation(allProcessing.get(list).getText(), propertyFileReader("processingForRealtime", "billsPayment"), "Processing Type");
			}
		}
		click(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objWaterUtilityButton), "Water Utility");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objWaterUtilityTitle), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objWaterUtilityTitle), ":Title"));
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objWaterUtilityTitle)), propertyFileReader("waterUtility", "billsPayment"), "Title");
		allBillers = findElements(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShowAllBillers));
		allProcessing = findElements(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAllProcessing));
		for(int list=0; list<allBillers.size(); list++) {
			assertionValidation(allBillers.get(list).getText(), propertyFileReader("waterUtility"+list, "billsPayment"), "Water Utility");
			if(allProcessing.get(list).getText().equals(propertyFileReader("processingForDays", "billsPayment"))) {
				assertionValidation(allProcessing.get(list).getText(), propertyFileReader("processingForDays", "billsPayment"), "Processing Type");
			}else {
				assertionValidation(allProcessing.get(list).getText(), propertyFileReader("processingForRealtime", "billsPayment"), "Processing Type");
			}
		}
	}

	/**
	 * reusable method to verify Name this bill page
	 * @throws Exception
	 */
	public void nameThisBillScreen(String billerName) throws Exception {
		waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillerField), 10);
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText), ":Header"));
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText)), propertyFileReader("nameThisBillHeader", "billsPayment"), "Header", "equals");
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSubTitleText)), propertyFileReader("nameThisBillSubtitle", "billsPayment"), "sub Title", "equals");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillerField), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillerField), "Billers name"));
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillerField)), billerName, "Biller name", "equals");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextText), "Next");
	}

	/**
	 * reusable method for Account info page for Bank of the philippine Islands biller
	 * @throws Exception
	 */
	public void accountInfoForBankOfThePhilippineIslands() throws Exception {
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
		}
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText), ":Header"));
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText)), propertyFileReader("accountInfoHeader", "billsPayment"), "Header", "equals");
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSubTitleText)), propertyFileReader("accountInfoSubtitle", "billsPayment"), "sub Title", "equals");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText)), propertyFileReader("acountNumberText", "billsPayment"), "Account Number Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShouldContainText)), propertyFileReader("shouldContain16DigitText", "billsPayment"), "Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNameText)), propertyFileReader("accountNameText", "billsPayment"), "Text");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberErrorMessage)), propertyFileReader("accountNumberErrorMessage", "billsPayment"), "inline error message");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNameErrorMessage), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNameErrorMessage), "inline error message"));
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNameErrorMessage)), propertyFileReader("accountNameErrorMessage", "billsPayment"), "inline error message");
	}

	/**
	 * reusable method for Account info page for converge biller
	 * @throws Exception
	 */
	public void accountInfoForConverge() throws Exception {
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
		}
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText)), propertyFileReader("acountNumberText", "billsPayment"), "Account Number Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShouldContainText)), propertyFileReader("shouldContain13DigitText", "billsPayment"), "Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNameText)), propertyFileReader("accountNameText", "billsPayment"), "Text");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberErrorMessage)), propertyFileReader("accountNumberErrorMessage", "billsPayment"), "inline error message");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNameErrorMessage), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNameErrorMessage), "inline error message"));
	}

	/**
	 * reusable method for Account info page for Easy service corporation biller
	 * @throws Exception
	 */
	public void accountInfoForEasyServicesCorporation() throws Exception {
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
		}
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText)), propertyFileReader("acountNumberText", "billsPayment"), "Account Number Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShouldContainText)), propertyFileReader("shouldContain12DigitText", "billsPayment"), "Text");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberErrorMessage)), propertyFileReader("accountNumberErrorMessage", "billsPayment"), "inline error message");
	}

	/**
	 * reusable method for Account info page for Manila water biller
	 * @throws Exception
	 */
	public void accountInfoForManilaWater() throws Exception {
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
		}
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText)), propertyFileReader("acountNumberText", "billsPayment"), "Account Number Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShouldContainText)), propertyFileReader("shouldContain8DigitText", "billsPayment"), "Text");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberErrorMessage)), propertyFileReader("accountNumberErrorMessage", "billsPayment"), "inline error message");
	}

	/**
	 * reusable method for Account info page for Maynilad water biller
	 * @throws Exception
	 */
	public void accountInfoForMayniladWater() throws Exception {
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
		}
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText)), propertyFileReader("acountNumberText", "billsPayment"), "Account Number Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShouldContainText)), propertyFileReader("shouldContain8DigitText", "billsPayment"), "Text");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberErrorMessage)), propertyFileReader("accountNumberErrorMessage", "billsPayment"), "inline error message");
	}

	/**
	 * reusable method for Account info page for Meralco biller
	 * @throws Exception
	 */
	public void accountInfoForMeralco() throws Exception {
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
		}
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText)), propertyFileReader("acountNumberText", "billsPayment"), "Account Number Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShouldContainText)), propertyFileReader("shouldContain10DigitText", "billsPayment"), "Text");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberErrorMessage)), propertyFileReader("accountNumberErrorMessage", "billsPayment"), "inline error message");
	}

	/**
	 * reusable method for Account info page for Metrobank card biller
	 * @throws Exception
	 */
	public void accountInfoForMetrobankCard() throws Exception {
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
		}
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText)), propertyFileReader("acountNumberText", "billsPayment"), "Account Number Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShouldContainText)), propertyFileReader("shouldContain16DigitText", "billsPayment"), "Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNameText)), propertyFileReader("accountNameText", "billsPayment"), "Text");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberErrorMessage)), propertyFileReader("accountNumberErrorMessage", "billsPayment"), "inline error message");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNameErrorMessage), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNameErrorMessage), "inline error message"));
	}

	/**
	 * reusable method for Account info page for Pag IBIG biller
	 * @throws Exception
	 */
	public void accountInfoForPagIBIG() throws Exception {
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
		}
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSubTitleText), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSubTitleText), ":Sub title"));
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText)), propertyFileReader("acountNumberText", "billsPayment"), "Account Number Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPaymentTypeText)), propertyFileReader("paymentTypeText", "billsPayment"), "Account Number Text");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPaymentTypeText), "Payment Type");
		waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShortTermLoan), 10);
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShortTermLoan)), propertyFileReader("shortTermLoan", "billsPayment"), "Payment Type");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objHousingLoan)), propertyFileReader("housingLoan", "billsPayment"), "Payment Type");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMembershipSavings)).trim(), propertyFileReader("membershipSavings", "billsPayment"), "Payment Type");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objModifiedPagIBIG)), propertyFileReader("modifiedPagIBIG", "billsPayment"), "Payment Type");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objInfoPageBackIcon), "Back Icon");
		waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPaymentTypeText), 10);
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPaymentTypeText)), propertyFileReader("paymentTypeText", "billsPayment"), "Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillDateText)), propertyFileReader("billDateText", "billsPayment"), "Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objDueDateText)), propertyFileReader("dueDateText", "billsPayment"), "Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillDateField)), propertyFileReader("dateFieldType", "billsPayment"), "Bill Date Type");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objDueDateField)), propertyFileReader("dateFieldType", "billsPayment"), "Due Date Type");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objContactNumberText)), propertyFileReader("contactNumberText", "billsPayment"), "Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objCountryCode)).trim(), propertyFileReader("countryCode", "billsPayment"), "Text");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberErrorMessage)), propertyFileReader("accountNumberErrorMessage", "billsPayment"), "inline error message");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPaymentTypeErrorMessage), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPaymentTypeErrorMessage), "inline error message"));
		verticalSwipeTillElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objContactNumberErrorMessage));
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objContactNumberErrorMessage), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objContactNumberErrorMessage), "inline error message"));
	}

	/**
	 * reusable method for Account info page for PLDT biller
	 * @throws Exception
	 */
	public void accountInfoForPLDT() throws Exception {
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
		}
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText)), propertyFileReader("acountNumberText", "billsPayment"), "Account Number Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShouldContainText)), propertyFileReader("shouldContain10DigitText", "billsPayment"), "Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPhoneNumberText)), propertyFileReader("phoneNumberText", "billsPayment"), "Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objServiceText)), propertyFileReader("serviceText", "billsPayment"), "Text");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objServiceText), "Service");
		waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPLDTLandline), 10);
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPLDTLandline)), propertyFileReader("PLDTLandline", "billsPayment"), "Text");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objInfoPageBackIcon), "Back Icon");
		waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objServiceText), 10);
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objServiceText)), propertyFileReader("serviceText", "billsPayment"), "Text");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberErrorMessage)), propertyFileReader("accountNumberErrorMessage", "billsPayment"), "inline error message");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPhoneNumberErrorMessage), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPhoneNumberErrorMessage), "inline error message"));
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objServicErrorMessage), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objServicErrorMessage), "inline error message"));
	}

	/**
	 * reusable method for Account info page for Smart biller
	 * @throws Exception
	 */
	public void accountInfoForSmart() throws Exception {
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
		}
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSubTitleText), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSubTitleText), ":Sub title"));
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText)), propertyFileReader("acountNumberText", "billsPayment"), "Account Number Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShouldContainText)), propertyFileReader("shouldContain10DigitText", "billsPayment"), "Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objProductCodeText)), propertyFileReader("productCodeText", "billsPayment"), "Account Number Text");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objProductCodeText), "Product Code");
		waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSmartBro), 10);
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSmartBro)), propertyFileReader("smartBro", "billsPayment"), "Product Code");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSmartSun)), propertyFileReader("smartSun", "billsPayment"), "Product Code");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objInfoPageBackIcon), "Back Icon");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMobileNumberText)), propertyFileReader("mobileNumberText", "billsPayment"), "Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSRNText)), propertyFileReader("SRNText", "billsPayment"), "Text");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberErrorMessage)), propertyFileReader("accountNumberErrorMessage", "billsPayment"), "inline error message");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objProductCodeErrorMessage), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objProductCodeErrorMessage), "inline error message"));
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMobileNumberErrorMessage), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMobileNumberErrorMessage), "inline error message"));
	}

	/**
	 * reusable method for Account info page for Social security system biller
	 * @throws Exception
	 */
	public void accountInfoForSocialSecuritySystem() throws Exception {
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objRefNumberText)), propertyFileReader("refNumberText", "billsPayment"), "Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShouldStartWithText)), propertyFileReader("shouldStartWithText", "billsPayment"), "Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayForText)), propertyFileReader("payForText", "billsPayment"), "Text");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayForText), "Pay For");
		waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPRNText), 10);
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPRNText)), propertyFileReader("PRNText", "billsPayment"), "Payment Reference Number");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objInfoPageBackIcon), "Back Icon");
		waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPaymentTypeText), 10);
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPaymentTypeText)), propertyFileReader("paymentTypeText", "billsPayment"), "Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMemberNameText)), propertyFileReader("memberNameText", "billsPayment"), "Text");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objRefNumberErrorMessage), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objRefNumberErrorMessage), "inline error message"));
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayForErrorMessage), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayForErrorMessage), "inline error message"));
		verticalSwipeTillElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMemberNameErrorMessage));
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMemberNameErrorMessage), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMemberNameErrorMessage), "inline error message"));
	}

	/**
	 * reusable method for Account info page for Visayan electric biller
	 * @throws Exception
	 */
	public void accountInfoForVisayanElectric() throws Exception {
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
		}
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText)), propertyFileReader("acountNumberText", "billsPayment"), "Account Number Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShouldContainText)), propertyFileReader("shouldContain10-13DigitText", "billsPayment"), "Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objLastNameText)), propertyFileReader("lastName", "billsPayment"), "Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objFirstNameText)), propertyFileReader("firstName", "billsPayment"), "Text");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberErrorMessage)), propertyFileReader("accountNumberErrorMessage", "billsPayment"), "inline error message");
	}

	/**
	 * reusable method for Account info page for RCBC Bankard biller
	 * @throws Exception
	 */
	public void accountInfoForRCBCBankard() throws Exception {
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
		}
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText)), propertyFileReader("acountNumberText", "billsPayment"), "Account Number Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShouldContainText)), propertyFileReader("shouldContain16DigitText", "billsPayment"), "Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNameText)), propertyFileReader("accountNameText", "billsPayment"), "Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillDateText)), propertyFileReader("billDateText", "billsPayment"), "Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillDateField)), propertyFileReader("dateFieldType", "billsPayment"), "Bill Date Type");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberErrorMessage)), propertyFileReader("accountNumberErrorMessage", "billsPayment"), "inline error message");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNameErrorMessage), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNameErrorMessage), "inline error message"));
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNameErrorMessage)), propertyFileReader("accountNameErrorMessage", "billsPayment"), "inline error message");
	}

	/**
	 * reusable method When is your due date page
	 * @throws Exception
	 */
	public void whenIsYourDueDateScreen() throws Exception {
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
		}
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText), ":Header"));
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText)), propertyFileReader("dueDateHeader", "billsPayment"), "Header", "equals");
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSubTitleText)), propertyFileReader("dueDateSubTitle", "billsPayment"), "sub Title", "equals");
		for (int date = 1; date <= 31; date++) {
			verifyElementPresent(BillsPaymentPage.objCalendarDate(platform,String.valueOf(date)), getTextVal(BillsPaymentPage.objCalendarDate(platform,String.valueOf(date)), "Calendar Date"));
		}
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objRemindMeText)), propertyFileReader("remindMeText", "billsPayment"), "Text");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objRemindToggleButton), "Toggle Button");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSkipButton), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSkipButton), "Button"));
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSaveButton), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSaveButton), "Button"));
	}

	/**
	 * reusable method for successfully created biller page
	 * @throws Exception
	 */
	public void billersWasSuccessfullyCreatedScreen() throws Exception {
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
		}
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillerCreatedText)), propertyFileReader("electricUtility0", "billsPayment")+" "+propertyFileReader("successfullyCreatedText", "billsPayment"), "Successfully created biller");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objYouCanPayNowText)), propertyFileReader("youCanPayNowText", "billsPayment"), "Text");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objGoToBillersList), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objGoToBillersList), "Button"));
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objGoToBillersList)),propertyFileReader("GoToBillerListText","billsPayment"),"Text");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPaynowButtonInSuccessPage), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPaynowButtonInSuccessPage), "Button"));
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPaynowButtonInSuccessPage)),propertyFileReader("PayNowText","billsPayment"),"Text");
	}

	/**
	 * reusable method for pay bills page
	 * @throws Exception
	 */
	public void payBillScreen() throws Exception {
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
		}
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText), ":Header"));
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText)), propertyFileReader("payBillHeader", "billsPayment"), "Header", "equals");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSubTitleText), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSubTitleText), "Sub Title"));
		verifyElementPresent(BillsPaymentPage.objBillerName(platform,propertyFileReader("nickName", "billsPayment")), getTextVal(BillsPaymentPage.objBillerName(platform,propertyFileReader("nickName", "billsPayment")), "Biller name"));
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMyTonikAccount), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMyTonikAccount), "Text"));
		if(verifyElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountToBePaid))) {
			verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountToBePaid), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountToBePaid), "Text"));
		}else if(verifyElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objWithConvenienceFee))){
			verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objWithConvenienceFee), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objWithConvenienceFee), "Text"));
			conFee = getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objWithConvenienceFee)).split(" ")[5];
			String confFee = conFee.replace("₱", "");
			convenienceFee = convertStringIntoDouble(confFee);
		}else {
			verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objWithBankFee), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objWithBankFee), "Text"));
		}
	}

	/**
	 * reusable method for History transaction details page
	 * @throws Exception
	 */
	public void historyTransactionDetailsScreen(String validAmount) throws Exception{
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNameOfTheStashOnHistory), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNameOfTheStashOnHistory), ":Name Of The Stash On History"));
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objTransactionValueOnHistory), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objTransactionValueOnHistory), ":Transaction Value On History"));
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objTransactionStatusOnHistory), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objTransactionStatusOnHistory), ":Transaction Status On History"));
		if(verifyElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objProcessingTransaction))) {
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objProcessingTransaction), "Transaction History");
		}else {
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objLatestTransaction), "Transaction History");
		}
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText), "Transaction Details");
		valueValidation(getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText), "TransactionDetails"), "TransactionDetails", "TransactionDetails", "contains");
		explicitWaitVisible(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountDetails), 10);
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountDetails), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountDetails), ":Amount to transfer"));
		String amount = getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountDetails));
		amount = amount.substring(amount.indexOf("₱")+1).replace(",", "");
		double amountValue = convertStringIntoDouble(amount);
		assertionValidationForDoubleType(amountValue, convertStringIntoDouble(validAmount), "Amount to transfer");
		if(verifyElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objToAccountxt))) {
			verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objToAccountxt), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objToAccountxt), ":To Account"));
		}
		if(verifyElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objFromAccountTxt))){
			verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objFromAccountTxt), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objFromAccountTxt), ":From Account"));
		}
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objWhenDetails), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objWhenDetails), ":When"));
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objWhenDetails)), getCurrentDateInFormat("dd MMM yyyy"), "Date", "contains");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objRefNoDetails), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objRefNoDetails), ":Ref Number"));
	}

	/**
	 * reusable method for pay amount for billers and confirm
	 * @throws Exception
	 */
	public void enterAmountAndConfirm(String accountNumber,String lessThanMinAmount,String moreThanMaxAmount, String validAmount, String OTP) throws Exception {
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSkipButton), "skip button");
		waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayNowButton), 10);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayNowButton), "Pay now button");
		payBillScreen();
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountField), "Amount Field");
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountField), lessThanMinAmount, "Amount");
		if(platform.equalsIgnoreCase("Android")){
			hideKeyboard();
		}else{
			tapOnScreen(300,400,"screen");
		}
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayNowButton), "Pay now button");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountErrorMessage),  getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountErrorMessage), ":inline error message"));
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountField), "Amount Field");
		clearField(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountField), "Amount Field");
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountField), moreThanMaxAmount, "Amount");
		if(platform.equalsIgnoreCase("Android")){
			hideKeyboard();
		}else{
			tapOnScreen(300,400,"screen");
		}
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayNowButton), "Pay now button");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountErrorMessage),  getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountErrorMessage), ":inline error message"));
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountField), "Amount Field");
		clearField(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountField), "Amount Field");
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountField), validAmount, "Amount");
		if(platform.equalsIgnoreCase("Android")){
			hideKeyboard();
		}else{
			tapOnScreen(300,400,"screen");
		}
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayNowButton), "Pay now button");
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
		}
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText), ":Header"));
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText)), propertyFileReader("confirmBillsPaymentHeader", "billsPayment"), "Header", "equals");
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountValue)), validAmount, "Amount", "contains");
		if(verifyElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objConvenienceFee))) {
			valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objConvenienceFee)), conFee, "Convenience Fee", "equals");
		}
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillerName)), propertyFileReader("nickName", "billsPayment"), "Biller name");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountInfo)), accountNumber, "Account Number");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objConfirmButton), "confirm button");
		waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objResendCodeText), 10);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objOTPField), "OTP");
		if(platform.equalsIgnoreCase("Android")){
			adbInputText(Integer.parseInt(OTP));
		}else{
			type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objOTPField),OTP,"OTP field");
		}
	}

	/**
	 * reusable method to search a biller by biller name
	 * @param billerName
	 * @throws Exception
	 */
	public void searchAndSelectBiller(String billerName, String nickName) throws Exception {
		waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAddABillerText), 10);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAddIcon), "Add a Biller");
		for (int click = 0; click < 2; click++) {
			if (verifyElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSearchBillerField))) {
				break;
			}else {
				verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAddIcon), "Add a Biller");
			}
		}
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSearchBillerField), "Biller search field");
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSearchBillerInput), billerName, "Biller search");
		if(platform.equalsIgnoreCase("Android")){
			hideKeyboard();
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSelectBiller), "select Biller");
		}
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSelectBiller), "select Biller");
		waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillerField), 10);
		clearField(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillerField), "biller name");
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillerField), nickName, "Nick Name");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
	}

	/**
	 * reusable method to verify transaction status and TSA amount
	 * @throws Exception
	 */
	public void verifyTransactionStatus(String validAmount) throws Exception {
		waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBackToDashboardButton), 10);
		if(verifyElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSitTightLuvHeader))){
			verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSitTightLuvHeader), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSitTightLuvHeader), ":Header"));
			verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSitTightLuvDesc), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSitTightLuvDesc), ":Description"));
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBackToDashboardButton), "Back to Dashboard");
		}
		if(verifyElementDisplayed(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objProcessing))){
			assertionValidation(getText(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objProcessing)),propertyFileReader("ProcessingText","billsPayment"),"Text");
			containsValidation(getText(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objProcessingDesc)),propertyFileReader("ProcessingDesc1","billsPayment"),"Descriptions");
			containsValidation(getText(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objProcessingDesc)),propertyFileReader("ProcessingDesc2","billsPayment"),"Descriptions");
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBackToDashboardButton), "Back to Dashboard");
		}
		click(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objHistoryIcon), "HistoryIcon");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objMainTitleText), getTextVal(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objMainTitleText), ":Header"));
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objMainTitleText)), propertyFileReader("AccountHistoryText", "billsPayment"), ":Header");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objAccountNumber), getTextVal(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objAccountNumber), "Account Number"));
		verifyElementPresent(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objTSAAmount), getTextVal(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objTSAAmount), "TSA Amount"));
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objTopUpText)), propertyFileReader("TopUpText", "billsPayment"), "Text");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objManageText)), propertyFileReader("ManageText", "billsPayment"), "Text");
		historyTransactionDetailsScreen(validAmount);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objBackIcon), "Back Icon");
		if (getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objTransactionStatusOnHistory)).contains(propertyFileReader("TransactionFailed", "billsPayment"))) {
			logger.info("Transaction failed");
		} else {
			billPaymentAmount = convertStringIntoDouble(validAmount);
			String updatedAmount = getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objTSABalance)).replace("₱", "").replace(",", "");
			updatedAmountValue = convertStringIntoDouble(updatedAmount);
			assertionValidationForDoubleType(updatedAmountValue, currentAmountValue - (billPaymentAmount + convenienceFee), "TSA Balance");
			logger.info("Amount deducted from Tonik Account - Transaction passed");
			extentLoggerPass("pass","Amount deducted from Tonik Account - Transaction passed");
		}
	}

	/**
	 * reusable method to select a biller and navigate to bills pay page
	 * @param biller
	 * @throws Exception
	 */
	public void selectBillerAndNavigateToBillPayScreen(String biller) throws Exception {
		waitForElementToBePresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objFirstBillsAccountNumber),2,"Bills List");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAddIcon), "Add a Biller");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPopularBillersTitle)), propertyFileReader("PopularBillers", "billsPayment"), "Text");
		verifyElementPresentAndClick(BillsPaymentPage.objAllBillersName(platform,biller), " Biller");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNameThisBillPageHeader), getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNameThisBillPageHeader)));
		verifyElementPresentAndClick(BillsPaymentPage.objTextButton(platform,"Next"), "Next Button");
	}

	/**
	 * reusable method to verify bill payment and check status
	 * @param billersName
	 * @param accountNumber
	 * @param accoutName
	 * @throws Exception
	 */
	public void verifyBillsPayPageAndDoThePaymentAndCheckTheSuccessMessage(String billersName, String accountNumber, String accoutName) throws Exception {
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountInfoPageHeader)), propertyFileReader("AccountInfo", "billsPayment"), " Page Header");
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberTextField), accountNumber, "Account Number Textfield");
		if (verifyElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNameTextField))) {
			type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNameTextField), accoutName, "Account Name Textfield");
		}
		verifyElementPresentAndClick(BillsPaymentPage.objTextButton(platform,"Next"), "Next Button");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objWhenIsYourDueDate)), propertyFileReader("WhenISYourDueDate", "billsPayment"), " Page Header");
		verifyElementPresentAndClick(BillsPaymentPage.objTextButton(platform,"Skip"), "Skip Button");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillersCreatedSuccessMessage)), billersName + " was successfully created", "Success Message");
	}

	/**
	 * reusable method for Confirm bills payment page
	 * @param amount
	 * @param convenienceFee
	 * @param billerName
	 * @param accountInfo
	 * @throws Exception
	 */
	public void verifyConfirmBillsPaymentPage(String amount, String convenienceFee, String billerName, String accountInfo) throws Exception {
		String[] values={"₱"+amount,"₱"+convenienceFee,billerName,accountInfo};
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objConfirmBillsPaymentPageHeader)),propertyFileReader("ConfirmBill","billsPayment")," Hader");
		for(int detail=0;detail<values.length; detail++){
			if(verifyElementDisplayed(BillsPaymentPage.objBillsPaymentPageDetailValue(platform,propertyFileReader("detail"+detail,"billsPayment")))){
				assertionValidation(getText(BillsPaymentPage.objBillsPaymentPageDetailName(platform,propertyFileReader("detail"+detail,"billsPayment"))),propertyFileReader("detail"+detail,"billsPayment")," text");
				assertionValidation(getText(BillsPaymentPage.objBillsPaymentPageDetailValue(platform,propertyFileReader("detail"+detail,"billsPayment"))),values[detail]," : "+propertyFileReader("detail"+detail,"billsPayment"));
			}
			else {
				logger.info(propertyFileReader("detail"+detail,"billsPayment")+" Is Not Applicable For This USer");
			}
		}
	}

	/**
	 * reusable method for payment processing status
	 * @param amount
	 * @param biller
	 * @throws Exception
	 */
	public void verifyPaymentProcessingPage(String amount,String biller) throws Exception {
		waitForElementToBePresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSitTightLuvHeader),3,"Processing Page");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSitTightLuvHeader)),propertyFileReader("SitLUV","billsPayment")," Header");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSitTightLuvDesc)).replaceAll("\\s"," "),"We're now processing your payment of PHP "+amount+"0 to "+biller+".   "+propertyFileReader("SitTightDescription","billsPayment")," Description");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBackToDashboardButton),getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBackToDashboardButton))+" Button");
	}

	/**
	 * reusable method for Email verification popup
	 * @throws Exception
	 */
	public void emailVerificationPopup() throws Exception{
		verifyElementPresent(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objVerificationDesc), getTextVal(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objVerificationDesc),"Descriptions"));
		verifyElementPresent(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objSendVerificationText),getTextVal(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objSendVerificationText),"Text"));
		verifyElementPresent(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objUpdateEmailAddressText),getTextVal(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objUpdateEmailAddressText),"Text"));
	}

	/**
	 * method to extract the email id from the given text
	 * @param message
	 * @return
	 */
	public String getTheEmailFromPopup(String message) {
		String email="";
		// Regular expression pattern to match email addresses
		Pattern pattern = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b");
		Matcher matcher = pattern.matcher(message);
		// Find and print email addresses
		while (matcher.find()) {
			email = matcher.group();
			System.out.println("Email Address: " + email);
		}
		return email;
	}

	/**
	 * reusable method for verify your email page
	 * @throws Exception
	 */
	public void verifyYourEmailScreen() throws Exception{
		waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objCodeExpiringText),10);
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText), ":Header"));
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText)), propertyFileReader("VerifyYourEmailText", "billsPayment"), "Header", "equals");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSubTitleText), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSubTitleText), "Sub Title"));
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objCodeExpiringText), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objCodeExpiringText), "Text"));
	}

	/**
	 * reusable method for Nooice! page
	 * @throws Exception
	 */
	public void nooiceScreen() throws Exception{
		waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNooiceText),10);
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNooiceText), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNooiceText), ":Title"));
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objNooiceText)),propertyFileReader("NooiceText","billsPayment"),"Text");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNooiceDesc), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNooiceDesc), ":Descriptions"));
	}

	/**
	 * method to verify if user can access the Bills Pay module when TSA is not yet created
	 * @throws Exception
	 */
	public void verifyUserCanAccessBillsPayModuleWhenTSANotCreated_TDB_BP_001() throws Exception {
		HeaderChildNode("TDB_BP_001, BillsPayment -Verify if user can access the Bills Pay module when TSA is not yet created - TDB_BP_001");
		tonikLogin(propertyFileReader("password", "Login"));
		assertionValidation(getAttributValue("enabled", BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButtonEnable)), "true", " pay enabled Attribute value");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButton), "Pay button");
		billsWithNoFrillsScreen();
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objStartABillCTA), "Start a bill");
		billsPayScreen();
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAddIcon), "Add a Biller");
		needSomeFundPopup();
		if(platform.equalsIgnoreCase("Android")){
			tapOnScreenUsingBounds(300, 500, "outside popup");
		}else{
			tapOnScreen(200,500,"outside popup");
		}
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText), "Bills Pay screen");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objBackIcon), "Back Icon");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objProfileName),getTextVal(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objProfileName)," : Profile Name"));
		if(platform.equalsIgnoreCase("Android")){
			assertionValidation(getAttributValue("focusable", BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objTSAAmountFocus)), "false", "TSA is not created");
		}else{
			assertionValidation(getAttributValue("accessible", BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objTSAAmountFocus)), "false", "TSA is not created");
		}
		logger.info("TDB_BP_001, BillsPayment - verifyUserCanAccessBillsPayModuleWhenTSANotCreated_TDB_BP_001");
        extentLoggerPass("TDB_BP_001", "TDB_BP_001, BillsPayment - verifyUserCanAccessBillsPayModuleWhenTSANotCreated_TDB_BP_001 is passed");
	}

	/**
	 * method to Verify if user can create the TSA from Bills pay
	 * @throws Exception
	 */
	public void verifyUserCanCreateTSAFromBillsPay_TDB_BP_002() throws Exception {
		HeaderChildNode("TDB_BP_002, BillsPayment -Verify if user can create the TSA from Bills pay - TDB_BP_002");
		tonikLogin(propertyFileReader("password", "Login"));
		assertionValidation(getAttributValue("enabled", BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButtonEnable)), "true", " enabled Attribute value");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButton), "Pay button");
		billsWithNoFrillsScreen();
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objStartABillCTA), "Start a bill");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAddIcon), "Add a Biller");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPopUpButton), "Ok let's go");
		waitTime(5000);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objBackIcon), "Back Icon");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objProfileName),getTextVal(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objProfileName)," : Profile Name"));
		logger.info("TDB_BP_002, BillsPayment - verifyUserCanCreateTSAFromBillsPay_TDB_BP_002");
        extentLoggerPass("TDB_BP_002", "TDB_BP_002, BillsPayment - verifyUserCanCreateTSAFromBillsPay_TDB_BP_002 is passed");
	}

	/**
	 * method to Verify if user can verify the email address from Bills pay
	 * @throws Exception
	 */
	public void verifyUserCanVerifyTheEmailAddressFromBillsPay_TDB_BP_004() throws Exception{
		HeaderChildNode("TDB_BP_004, BillsPayment -Verify if user can verify the email address from Bills pay - TDB_BP_004");
		tonikLogin(propertyFileReader("password", "Login"));
		assertionValidation(getAttributValue("enabled", BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButtonEnable)), "true", " enabled Attribute value");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButton), "Pay button");
		if(verifyElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillsWithNoFrillsTitle))) {
			verticalSwipeTillElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objStartABilltext));
			click(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objStartABillCTA), "Start a bill");
		}
		waitForElementDisplayed(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objBillsPayWait),10);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAddIcon), "Add a Biller");
		emailVerificationPopup();
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objSendVerificationBtn),"Send verification");
		verifyYourEmailScreen();
		String email = getTheEmailFromPopup(getText(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objSubTitleText)));
		String otp = selectQuery("selectQuerycustomer","SELECT otp FROM customer.tdbk_email_otp_track where email_id= '"+email+"';");
		click(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objOTPFieldForEmail),"OTP field");
		DriverManager.getAppiumDriver().getKeyboard().sendKeys(otp);
		nooiceScreen();
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objCloseButton),"Close Button");
		waitForElementDisplayed(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objBillsPayWait),10);
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText)), propertyFileReader("BillsPay", "billsPayment"), "Page Header");
		click(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAddIcon), "Add a Biller");
		waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objShowAllText), 10);
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText), ":Header"));
		valueValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText)), propertyFileReader("billersHeader", "billsPayment"), "Header", "equals");
		logger.info("TDB_BP_004, BillsPayment - verifyUserCanVerifyTheEmailAddressFromBillsPay_TDB_BP_004");
		extentLoggerPass("TDB_BP_004", "TDB_BP_004, BillsPayment - verifyUserCanVerifyTheEmailAddressFromBillsPay_TDB_BP_004 is passed");
	}

	/**
	 * method to Verify if user can view the list of billers
	 * @throws Exception
	 */
	public void verifyUserCanViewListOfBillers_TDB_BP_005() throws Exception {
		HeaderChildNode("TDB_BP_005, BillsPayment -Verify if user can view the list of billers - TDB_BP_005");
		tonikLogin(propertyFileReader("password", "Login"));
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButton), "Pay button");
		if(verifyElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillsWithNoFrillsTitle))) {
			verticalSwipeTillElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objStartABilltext));
			click(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objStartABillCTA), "Start a bill");
		}
		waitForElementDisplayed(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objBillsPayWait),10);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAddIcon), "Add a Biller");
		billersScreen();
		showAllBillers();
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objBackIcon), "Back Icon");
		waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAddIcon), 10);
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText), ":Header"));
		logger.info("TDB_BP_005, BillsPayment - verifyUserCanViewListOfBillers_TDB_BP_005");
        extentLoggerPass("TDB_BP_005", "TDB_BP_005, BillsPayment - verifyUserCanViewListOfBillers_TDB_BP_005 is passed");
	}

	/**
	 * method to Verify if user can view the biller categories in tabs
	 * @throws Exception
	 */
	public void verifyUserCanViewBillerCategoriesInTabs_TDB_BP_006() throws Exception {
		HeaderChildNode("TDB_BP_006, BillsPayment -Verify if user can view the biller categories in tabs - TDB_BP_005");
		tonikLogin(propertyFileReader("password", "Login"));
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButton), "Pay button");
		if(verifyElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillsWithNoFrillsTitle))) {
			verticalSwipeTillElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objStartABilltext));
			click(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objStartABillCTA), "Start a bill");
		}
		waitForElementDisplayed(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objBillsPayWait),10);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAddIcon), "Add a Biller");
		verifyAllBillersCategory();
		logger.info("TDB_BP_006, BillsPayment - verifyUserCanViewBillerCategoriesInTabs_TDB_BP_006");
        extentLoggerPass("TDB_BP_006", "TDB_BP_006, BillsPayment - verifyUserCanViewBillerCategoriesInTabs_TDB_BP_006 is passed");
	}

	/**
	 * method to Verify if user can search a biller
	 * @throws Exception
	 */
	public void verifyUserCanSearchBiller_TDB_BP_007() throws Exception {
		HeaderChildNode("TDB_BP_007, BillsPayment -Verify if user can search a biller - TDB_BP_007");
		tonikLogin(propertyFileReader("password", "Login"));
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButton), "Pay button");
		if(verifyElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillsWithNoFrillsTitle))) {
			verticalSwipeTillElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objStartABillCTA));
			click(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objStartABillCTA), "Start a bill");
		}
		waitForElementDisplayed(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objBillsPayWait),10);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAddIcon), "Add a Biller");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSearchBillerField), "Biller search field");
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSearchBillerInput), propertyFileReader("popularBillersName1", "billsPayment"), "Biller search");
		hideKeyboard();
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSelectBiller), "select Biller");
		if(platform.equalsIgnoreCase("Android")) {
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSelectBiller), "select Biller");
		}
		nameThisBillScreen(propertyFileReader("popularBillersName1", "billsPayment"));
		clearField(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillerField), "Biller name");
		assertionValidation(getAttributValue("enabled", BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButtonDisabled)), "false", "for Next Disabled Attribute value");
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillerField), propertyFileReader("specialCharacter","billsPayment"),"text_field");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objBillerNameErrorMessage),getTextVal(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objBillerNameErrorMessage),"Error message"));
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objBillerNameErrorMessage)),propertyFileReader("errorMessage","billsPayment"),"Error message");
		clearField(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillerField), "Biller name");
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillerField), propertyFileReader("moreThan45Char","billsPayment"),"text_field");
        String maxChar = getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillerField));
        if(maxChar.length()>45) {
        	logger.error("Error: Input length exceeds 45 characters.");
        }else {
        	logger.info(maxChar.length()+" Character allowed for billers Input field");
			extentLoggerPass("pass",maxChar.length()+" Character allowed for billers Input field");
        }
        verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objBackIcon), "Back Icon");
        waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSelectBiller), 10);
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText), getTextVal(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText), ":Header"));
        logger.info("TDB_BP_007, BillsPayment - verifyUserCanSearchBiller_TDB_BP_007");
        extentLoggerPass("TDB_BP_007", "TDB_BP_007, BillsPayment - verifyUserCanSearchBiller_TDB_BP_007 is passed");
	}

	/**
	 * method to Verify if user can type account number/subscriber number in the Account info screen
	 * @throws Exception
	 */
	public void verifyUserCanTypeAccountNumberSubscriberNumberInAccountInfoScreen_TDB_BP_008() throws Exception {
		HeaderChildNode("TDB_BP_008, BillsPayment -Verify if user can type account number/subscriber number in the Account info screen - TDB_BP_008");
		tonikLogin(propertyFileReader("password", "Login"));
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButton), "Pay button");
		if(verifyElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillsWithNoFrillsTitle))) {
			verticalSwipeTillElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objStartABillCTA));
			click(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objStartABillCTA), "Start a bill");
		}
		waitForElementDisplayed(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objBillsPayWait),10);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAddIcon), "Add a Biller");
		addAllBillers();
		if(platform.equalsIgnoreCase("Android")){
			for (int scrollUp = 0; scrollUp < 6; scrollUp++) {
				newScrollMethodVertical(0.5, 0.45, 0.5, 0.8);
			}
		}
		for (String billers : popularBillersList) {
			verticalSwipeTillElementDisplayed(BillsPaymentPage.objBillerName(platform,billers));
			click(BillsPaymentPage.objBillerName(platform,billers), "Billers name");
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillerField), 10);
			assertionValidation(billers, getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillerField)), "Billers name");
			click(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
			switch(billers) {
			case "Bank of the Philippine Islands (BPI)":
				accountInfoForBankOfThePhilippineIslands();
				break;
			case "Converge":
				accountInfoForConverge();
				break;
			case "Easytrip Services Corporation":
				accountInfoForEasyServicesCorporation();
				break;
			case "Manila Water":
				accountInfoForManilaWater();
				break;
			case "MAYNILAD WATER":
				accountInfoForMayniladWater();
				break;
			case "Meralco":
				accountInfoForMeralco();
				break;
			case "Metrobank Card":
				accountInfoForMetrobankCard();
				break;
			case "Pag-IBIG":
				accountInfoForPagIBIG();
				break;
			case "PLDT":
				accountInfoForPLDT();
				break;
			case "Smart":
				accountInfoForSmart();
				break;
			case "Social Security System (SSS)":
				accountInfoForSocialSecuritySystem();
				break;
			case "Visayan electric":
				accountInfoForVisayanElectric();
				break;
			case "RCBC Bankard":
				accountInfoForRCBCBankard();
				break;
			default:
				logger.info("Invalid input. Please enter a valid Biller name");
			}
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objBackIcon), "Back Icon");
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillerField), 10);
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objBackIcon), "Back Icon");
		}
		logger.info("TDB_BP_008, BillsPayment - verifyUserCanTypeAccountNumberSubscriberNumberInAccountInfoScreen_TDB_BP_008");
		extentLoggerPass("TDB_BP_008", "TDB_BP_008, BillsPayment - verifyUserCanTypeAccountNumberSubscriberNumberInAccountInfoScreen_TDB_BP_008 is passed");
	}

	/**
	 * method to Verify if user can set a due date reminder for paying bill
	 * @throws Exception
	 */
	public void verifyUserCanSetDueDateReminderForPayingBill_TDB_BP_009() throws Exception {
		HeaderChildNode("TDB_BP_009, Verify if user can set a due date reminder for paying bill - TDB_BP_009");
		String accountNumber = propertyFileReader("meralcoAccountNumber", "billsPayment");
		tonikLogin(propertyFileReader("password", "Login"));
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButton), "Pay button");
		if(verifyElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillsWithNoFrillsTitle))) {
			verticalSwipeTillElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objStartABillCTA));
			click(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objStartABillCTA), "Start a bill");
		}
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAddIcon), "Add a Biller");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSearchBillerField), "Biller search field");
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSearchBillerInput), propertyFileReader("electricUtility0", "billsPayment"), "Biller search");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSelectBiller), "select Biller");
		if(platform.equalsIgnoreCase("Android")){
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSelectBiller), "select Biller");
		}
		waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillerField), 10);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
		}
		click(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), "Account Number");
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberField), accountNumber, "Account Number");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		whenIsYourDueDateScreen();
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSkipButton), "skip button");
		billersWasSuccessfullyCreatedScreen();
		logger.info("TDB_BP_009, BillsPayment - verifyUserCanSetDueDateReminderForPayingBill_TDB_BP_009");
		extentLoggerPass("TDB_BP_009", "TDB_BP_009, BillsPayment - verifyUserCanSetDueDateReminderForPayingBill_TDB_BP_009 is passed");
	}

	/**
	 * method to Verify if user can pay as a new biller (Meralco)
	 * @throws Exception
	 */
	public void verifyUserCanPayAsNewBiller_Meralco_TDB_BP_010() throws Exception {
		HeaderChildNode("TDB_BP_010, Verify if user can pay as a new biller (Meralco) - TDB_BP_010");
		tonikLogin(propertyFileReader("password", "Login"));
		String currentAmount = getText(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objTonikAmountOnDashboard)).replace("₱", "").replace(",", "");
        currentAmountValue = convertStringIntoDouble(currentAmount);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButton), "Pay button");
		searchAndSelectBiller(propertyFileReader("electricUtility0", "billsPayment"),propertyFileReader("nickName", "billsPayment"));
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
			click(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), "Account Number");
		}
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberField), propertyFileReader("meralcoAccountNumber", "billsPayment"), "Account Number");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		enterAmountAndConfirm(propertyFileReader("meralcoAccountNumber", "billsPayment"),propertyFileReader("MeralcoLessThanMinAmount", "billsPayment"),propertyFileReader("MeralcoMoreThanMaxAmount", "billsPayment"),propertyFileReader("MeralcoValidAmount", "billsPayment"),propertyFileReader("OTP", "billsPayment"));
		verifyTransactionStatus(propertyFileReader("MeralcoValidAmount", "billsPayment"));
		logger.info("TDB_BP_010, BillsPayment - verifyUserCanPayAsNewBiller_Meralco_TDB_BP_010");
		extentLoggerPass("TDB_BP_010", "TDB_BP_010, BillsPayment - verifyUserCanPayAsNewBiller_Meralco_TDB_BP_010 is passed");
	}

	/**
	 * method to Verify if user can pay as a new biller (Manila Water)
	 * @throws Exception
	 */
	public void verifyUserCanPayAsNewBiller_ManilaWater_TDB_BP_011() throws Exception {
		HeaderChildNode("TDB_BP_011, Verify if user can pay as a new biller (Manila Water) - TDB_BP_011");
		tonikLogin(propertyFileReader("password", "Login"));
		String currentAmount = getText(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objTonikAmountOnDashboard)).replace("₱", "").replace(",", "");
        currentAmountValue = convertStringIntoDouble(currentAmount);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButton), "Pay button");
		searchAndSelectBiller(propertyFileReader("waterUtility0", "billsPayment"),propertyFileReader("nickName", "billsPayment"));
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), "Account Number");
		}
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberField), propertyFileReader("manilaWaterAccountNumber", "billsPayment"), "Account Number");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		enterAmountAndConfirm(propertyFileReader("manilaWaterAccountNumber", "billsPayment"),propertyFileReader("ManilaWaterLessThanMinAmount", "billsPayment"),propertyFileReader("ManilaWaterMoreThanMaxAmount", "billsPayment"),propertyFileReader("ManilaWaterValidAmount", "billsPayment"),propertyFileReader("OTP", "billsPayment"));
		verifyTransactionStatus(propertyFileReader("ManilaWaterValidAmount", "billsPayment"));
		logger.info("TDB_BP_011, BillsPayment - verifyUserCanPayAsNewBiller_ManilaWater_TDB_BP_011");
		extentLoggerPass("TDB_BP_011", "TDB_BP_011, BillsPayment - verifyUserCanPayAsNewBiller_ManilaWater_TDB_BP_011 is passed");
	}

	/**
	 * method to Verify if user can pay as a new biller (Maynilad Water)
	 * @throws Exception
	 */
	public void verifyUserCanPayAsNewBiller_MayniladWater_TDB_BP_012() throws Exception {
		HeaderChildNode("TDB_BP_012, Verify if user can pay as a new biller (Maynilad Water) - TDB_BP_012");
		tonikLogin(propertyFileReader("password", "Login"));
		String currentAmount = getText(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objTonikAmountOnDashboard)).replace("₱", "").replace(",", "");
        currentAmountValue = convertStringIntoDouble(currentAmount);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButton), "Pay button");
		searchAndSelectBiller(propertyFileReader("waterUtility1", "billsPayment"),propertyFileReader("nickName", "billsPayment"));
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), "Account Number");
		}
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberField), propertyFileReader("mayniladWaterAccountNumber", "billsPayment"), "Account Number");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		enterAmountAndConfirm(propertyFileReader("mayniladWaterAccountNumber", "billsPayment"),propertyFileReader("MayniladWaterLessThanMinAmount", "billsPayment"),propertyFileReader("MayniladWaterMoreThanMaxAmount", "billsPayment"),propertyFileReader("MayniladWaterValidAmount", "billsPayment"),propertyFileReader("OTP", "billsPayment"));
		verifyTransactionStatus(propertyFileReader("MayniladWaterValidAmount", "billsPayment"));
		logger.info("TDB_BP_012, BillsPayment - verifyUserCanPayAsNewBiller_MayniladWater_TDB_BP_012");
		extentLoggerPass("TDB_BP_012", "TDB_BP_012, BillsPayment - verifyUserCanPayAsNewBiller_MayniladWater_TDB_BP_012 is passed");
	}

	/**
	 * method to Verify if user can pay as a new biller (Easytrip Services Corporation)
	 * @throws Exception
	 */
	public void verifyUserCanPayAsNewBiller_EasytripServicesCorporation_TDB_BP_013() throws Exception {
		HeaderChildNode("TDB_BP_013, Verify if user can pay as a new biller (Easytrip Services Corporation) - TDB_BP_013");
		tonikLogin(propertyFileReader("password", "Login"));
		String currentAmount = getText(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objTonikAmountOnDashboard)).replace("₱", "").replace(",", "");
        currentAmountValue = convertStringIntoDouble(currentAmount);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButton), "Pay button");
		searchAndSelectBiller(propertyFileReader("popularBillersName2", "billsPayment"),propertyFileReader("nickName", "billsPayment"));
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), "Account Number");
		}
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberField), propertyFileReader("EasyServiceAccountNumber", "billsPayment"), "Account Number");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		enterAmountAndConfirm(propertyFileReader("EasyServiceAccountNumber", "billsPayment"),propertyFileReader("EasyServiceLessThanMinAmount", "billsPayment"),propertyFileReader("EasyServiceMoreThanMaxAmount", "billsPayment"),propertyFileReader("EasyServiceValidAmount", "billsPayment"),propertyFileReader("OTP", "billsPayment"));
		verifyTransactionStatus(propertyFileReader("EasyServiceValidAmount", "billsPayment"));
		logger.info("TDB_BP_013, BillsPayment - verifyUserCanPayAsNewBiller_EasytripServicesCorporation_TDB_BP_013");
		extentLoggerPass("TDB_BP_013", "TDB_BP_013, BillsPayment - verifyUserCanPayAsNewBiller_EasytripServicesCorporation_TDB_BP_013 is passed");
	}

	/**
	 * method to Verify if user can pay as a new biller [Bank of the Philippine Islands (BPI)]
	 * @throws Exception
	 */
	public void verifyUserCanPayAsNewBiller_BPI_TDB_BP_014() throws Exception {
		HeaderChildNode("TDB_BP_014, Verify if user can pay as a new biller [Bank of the Philippine Islands (BPI)] - TDB_BP_014");
		tonikLogin(propertyFileReader("password", "Login"));
		String currentAmount = getText(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objTonikAmountOnDashboard)).replace("₱", "").replace(",", "");
        currentAmountValue = convertStringIntoDouble(currentAmount);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButton), "Pay button");
		searchAndSelectBiller(propertyFileReader("popularBillersName0", "billsPayment"),propertyFileReader("nickName", "billsPayment"));
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), "Account Number");
		}
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberField), propertyFileReader("BPIAccountNumber", "billsPayment"), "Account Number");
		if(platform.equalsIgnoreCase("Android")){
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNameText), "Account Name");
		}else{
			tapOnScreen(350,100,"screen");
		}
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNameField), propertyFileReader("AccountName", "billsPayment"), "Account Name");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		enterAmountAndConfirm(propertyFileReader("BPIAccountNumber", "billsPayment"),propertyFileReader("BPILessThanMinAmount", "billsPayment"),propertyFileReader("BPIMoreThanMaxAmount", "billsPayment"),propertyFileReader("BPIValidAmount", "billsPayment"),propertyFileReader("OTP", "billsPayment"));
		verifyTransactionStatus(propertyFileReader("BPIValidAmount", "billsPayment"));
		logger.info("TDB_BP_014, BillsPayment - verifyUserCanPayAsNewBiller_BPI_TDB_BP_014");
		extentLoggerPass("TDB_BP_014", "TDB_BP_014, BillsPayment - verifyUserCanPayAsNewBiller_BPI_TDB_BP_014 is passed");
	}

	/**
	 * method to Verify if user can pay as a new biller (Metrobank Card)
	 * @throws Exception
	 */
	public void verifyUserCanPayAsNewBiller_MetrobankCard_TDB_BP_015() throws Exception {
		HeaderChildNode("TDB_BP_015, Verify if user can pay as a new biller (Metrobank Card) - TDB_BP_015");
		tonikLogin(propertyFileReader("password", "Login"));
		String currentAmount = getText(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objTonikAmountOnDashboard)).replace("₱", "").replace(",", "");
        currentAmountValue = convertStringIntoDouble(currentAmount);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButton), "Pay button");
		searchAndSelectBiller(propertyFileReader("CreditCardsName1", "billsPayment"),propertyFileReader("nickName", "billsPayment"));
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), "Account Number");
		}
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberField), propertyFileReader("MetrobankCardAccountNumber", "billsPayment"), "Account Number");
		if(platform.equalsIgnoreCase("Android")){
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNameText), "Account Name");
		}
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNameField), propertyFileReader("AccountName", "billsPayment"), "Account Name");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		enterAmountAndConfirm(propertyFileReader("MetrobankCardAccountNumber", "billsPayment"),propertyFileReader("BPILessThanMinAmount", "billsPayment"),propertyFileReader("BPIMoreThanMaxAmount", "billsPayment"),propertyFileReader("BPIValidAmount", "billsPayment"),propertyFileReader("OTP", "billsPayment"));
		verifyTransactionStatus(propertyFileReader("BPIValidAmount", "billsPayment"));
		logger.info("TDB_BP_015, BillsPayment - verifyUserCanPayAsNewBiller_MetrobankCard_TDB_BP_015");
		extentLoggerPass("TDB_BP_015", "TDB_BP_015, BillsPayment - verifyUserCanPayAsNewBiller_MetrobankCard_TDB_BP_015 is passed");
	}

	/**
	 * method to Verify if user can pay as a new biller (RCBC Bankard)
	 * @throws Exception
	 */
	public void verifyUserCanPayAsNewBiller_RCBCBankard_TDB_BP_016() throws Exception {
		HeaderChildNode("TDB_BP_016, Verify if user can pay as a new biller (RCBC Bankard) - TDB_BP_016");
		tonikLogin(propertyFileReader("password", "Login"));
		String currentAmount = getText(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objTonikAmountOnDashboard)).replace("₱", "").replace(",", "");
        currentAmountValue = convertStringIntoDouble(currentAmount);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButton), "Pay button");
		searchAndSelectBiller(propertyFileReader("CreditCardsName2", "billsPayment"),propertyFileReader("nickName", "billsPayment"));
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), "Account Number");
		}
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberField), propertyFileReader("RCBCBankardAccountNumber", "billsPayment"), "Account Number");
		if(platform.equalsIgnoreCase("Android")) {
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNameText), "Account Name");
		}
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNameField), propertyFileReader("AccountName", "billsPayment"), "Account Name");
		if(platform.equalsIgnoreCase("Android")){
			hideKeyboard();
		}else{
			tapOnScreen(350,100,"screen");
		}
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillDateField), "Bill Date");
		if(platform.equalsIgnoreCase("Android")) {
			for (int click = 0; click < 2; click++) {
				if (verifyElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objDate))) {
					verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objDate), "Date");
					verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objOkButton), "OK");
					break;
				} else {
					verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillDateField), "Bill Date");
				}
			}
		}else{
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objOkButton), "OK");
		}
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		enterAmountAndConfirm(propertyFileReader("RCBCBankardAccountNumber", "billsPayment"),propertyFileReader("BPILessThanMinAmount", "billsPayment"),propertyFileReader("BPIMoreThanMaxAmount", "billsPayment"),propertyFileReader("BPIValidAmount", "billsPayment"),propertyFileReader("OTP", "billsPayment"));
		verifyTransactionStatus(propertyFileReader("BPIValidAmount", "billsPayment"));
		logger.info("TDB_BP_016, BillsPayment - verifyUserCanPayAsNewBiller_RCBCBankard_TDB_BP_016");
		extentLoggerPass("TDB_BP_016", "TDB_BP_016, BillsPayment - verifyUserCanPayAsNewBiller_RCBCBankard_TDB_BP_016 is passed");
	}

	/**
	 * method to Verify if user can pay as a new biller (Visayan electric)
	 * @throws Exception
	 */
	public void verifyUserCanPayAsNewBiller_VisayanElectric_TDB_BP_017() throws Exception {
		HeaderChildNode("TDB_BP_017, Verify if user can pay as a new biller (Visayan electric) - TDB_BP_017");
		tonikLogin(propertyFileReader("password", "Login"));
		String currentAmount = getText(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objTonikAmountOnDashboard)).replace("₱", "").replace(",", "");
        currentAmountValue = convertStringIntoDouble(currentAmount);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButton), "Pay button");
		searchAndSelectBiller(propertyFileReader("electricUtility1", "billsPayment"),propertyFileReader("nickName", "billsPayment"));
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), "Account Number");
		}
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberField), propertyFileReader("VisayanElectricAccountNumber", "billsPayment"), "Account Number");
		if(platform.equalsIgnoreCase("Android")){
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objLastNameText), "Last Name");
		}
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objLastNameField), propertyFileReader("LastNameValue", "billsPayment"), "Last Name");
		if(platform.equalsIgnoreCase("Android")){
			hideKeyboard();
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objFirstNameText), "First Name");
		}else{
			tapOnScreen(350,100,"screen");
		}
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objFirstNameField), propertyFileReader("FirstNameValue", "billsPayment"), "First Name");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		enterAmountAndConfirm(propertyFileReader("VisayanElectricAccountNumber", "billsPayment"),propertyFileReader("BPILessThanMinAmount", "billsPayment"),propertyFileReader("VisayanElectricMoreThanMaxAmount", "billsPayment"),propertyFileReader("BPIValidAmount", "billsPayment"),propertyFileReader("OTP", "billsPayment"));
		verifyTransactionStatus(propertyFileReader("BPIValidAmount", "billsPayment"));
		logger.info("TDB_BP_017, BillsPayment - verifyUserCanPayAsNewBiller_VisayanElectric_TDB_BP_017");
		extentLoggerPass("TDB_BP_017", "TDB_BP_017, BillsPayment - verifyUserCanPayAsNewBiller_VisayanElectric_TDB_BP_017 is passed");
	}

	/**
	 * method to Verify if user can pay as a new biller (Converge)
	 * @throws Exception
	 */
	public void verifyUserCanPayAsNewBiller_Converge_TDB_BP_018() throws Exception {
		HeaderChildNode("TDB_BP_018, Verify if user can pay as a new biller (Converge) - TDB_BP_018");
		tonikLogin(propertyFileReader("password", "Login"));
		String currentAmount = getText(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objTonikAmountOnDashboard)).replace("₱", "").replace(",", "");
        currentAmountValue = convertStringIntoDouble(currentAmount);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButton), "Pay button");
		searchAndSelectBiller(propertyFileReader("telecoms0", "billsPayment"),propertyFileReader("nickName", "billsPayment"));
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), "Account Number");
		}
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberField), propertyFileReader("ConvergeAccountNumber", "billsPayment"), "Account Number");
		if(platform.equalsIgnoreCase("Android")){
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNameText), "Account Name");
		}
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNameField), propertyFileReader("AccountName", "billsPayment"), "Account Name");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		enterAmountAndConfirm(propertyFileReader("ConvergeAccountNumber", "billsPayment"),propertyFileReader("BPILessThanMinAmount", "billsPayment"),propertyFileReader("BPIMoreThanMaxAmount", "billsPayment"),propertyFileReader("BPIValidAmount", "billsPayment"),propertyFileReader("OTP", "billsPayment"));
		verifyTransactionStatus(propertyFileReader("BPIValidAmount", "billsPayment"));
		logger.info("TDB_BP_018, BillsPayment - verifyUserCanPayAsNewBiller_Converge_TDB_BP_018");
		extentLoggerPass("TDB_BP_018", "TDB_BP_018, BillsPayment - verifyUserCanPayAsNewBiller_Converge_TDB_BP_018 is passed");
	}

	/**
	 * method to Verify if user can pay as a new biller (PLDT)
	 * @throws Exception
	 */
	public void verifyUserCanPayAsNewBiller_PLDT_TDB_BP_019() throws Exception {
		HeaderChildNode("TDB_BP_019, Verify if user can pay as a new biller (PLDT) - TDB_BP_019");
		tonikLogin(propertyFileReader("password", "Login"));
		String currentAmount = getText(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objTonikAmountOnDashboard)).replace("₱", "").replace(",", "");
        currentAmountValue = convertStringIntoDouble(currentAmount);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButton), "Pay button");
		searchAndSelectBiller(propertyFileReader("telecoms1", "billsPayment"),propertyFileReader("nickName", "billsPayment"));
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), "Account Number");
		}
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberField), propertyFileReader("PLDTAccountNumber", "billsPayment"), "Account Number");
		if(platform.equalsIgnoreCase("Android")){
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPhoneNumberText), "Phone number");
		}
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPhoneNumberField), propertyFileReader("PhoneNumber", "billsPayment"), "Phone Number");
		if(platform.equalsIgnoreCase("Android")){
			hideKeyboard();
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objFirstNameText), "First Name");
		}else{
			tapOnScreen(350,100,"screen");
		}
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objServiceText), "Service");
		for (int click = 0; click < 2; click++) {
			if(verifyElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPLDTLandline))) {
				verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPLDTLandline), "PLDT Landline");
				break;
			}else {
				verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objServiceText), "Service");
			}
		}
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		enterAmountAndConfirm(propertyFileReader("PLDTAccountNumber", "billsPayment"),propertyFileReader("PLDTLessThanMinAmount", "billsPayment"),propertyFileReader("PLDTMoreThanMaxAmount", "billsPayment"),propertyFileReader("PLDTValidAmount", "billsPayment"),propertyFileReader("OTP", "billsPayment"));
		verifyTransactionStatus(propertyFileReader("PLDTValidAmount", "billsPayment"));
		logger.info("TDB_BP_019, BillsPayment - verifyUserCanPayAsNewBiller_PLDT_TDB_BP_019");
		extentLoggerPass("TDB_BP_019", "TDB_BP_019, BillsPayment - verifyUserCanPayAsNewBiller_PLDT_TDB_BP_019 is passed");
	}

	/**
	 * method to Verify if user can pay as a new biller (Smart)
	 * @throws Exception
	 */
	public void verifyUserCanPayAsNewBiller_Smart_TDB_BP_020() throws Exception {
		HeaderChildNode("TDB_BP_020, Verify if user can pay as a new biller (Smart) - TDB_BP_020");
		tonikLogin(propertyFileReader("password", "Login"));
		String currentAmount = getText(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objTonikAmountOnDashboard)).replace("₱", "").replace(",", "");
        currentAmountValue = convertStringIntoDouble(currentAmount);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButton), "Pay button");
		searchAndSelectBiller(propertyFileReader("telecoms2", "billsPayment"),propertyFileReader("nickName", "billsPayment"));
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), "Account Number");
		}
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberField), propertyFileReader("SmartAccountNumber", "billsPayment"), "Account Number");
		click(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objProductCodeText), "Product code");
		for (int click = 0; click < 2; click++) {
			if(verifyElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSmartBro))) {
				verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSmartBro), "Smart Bro");
				break;
			}else {
				verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objProductCodeText), "Product code");
			}
		}
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMobileNumberField), "Mobile Number");
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMobileNumberField), propertyFileReader("MobileNumber", "billsPayment"), "Mobile Number");
		if(platform.equalsIgnoreCase("Android")){
			hideKeyboard();
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objFirstNameText), "First Name");
		}else{
			tapOnScreen(350,100,"screen");
		}
		if(platform.equalsIgnoreCase("Android")){
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSRNText), "Service Reference Number");
		}
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objSRNField), propertyFileReader("ServiceReferenceNumber", "billsPayment"), "Service Reference Number");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		enterAmountAndConfirm(propertyFileReader("SmartAccountNumber", "billsPayment"),propertyFileReader("BPILessThanMinAmount", "billsPayment"),propertyFileReader("BPIMoreThanMaxAmount", "billsPayment"),propertyFileReader("BPIValidAmount", "billsPayment"),propertyFileReader("OTP", "billsPayment"));
		verifyTransactionStatus(propertyFileReader("BPIValidAmount", "billsPayment"));
		logger.info("TDB_BP_020, BillsPayment - verifyUserCanPayAsNewBiller_Smart_TDB_BP_020");
		extentLoggerPass("TDB_BP_020", "TDB_BP_020, BillsPayment - verifyUserCanPayAsNewBiller_Smart_TDB_BP_020 is passed");
	}

	/**
	 * method to Verify if user can pay as a new biller (Pag-IBIG)
	 * @throws Exception
	 */
	public void verifyUserCanPayAsNewBiller_PagIBIG_TDB_BP_021() throws Exception{
		HeaderChildNode("TDB_BP_021, Verify if user can pay as a new biller (Pag-IBIG) - TDB_BP_021");
		tonikLogin(propertyFileReader("password", "Login"));
		String currentAmount = getText(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objTonikAmountOnDashboard)).replace("₱", "").replace(",", "");
        currentAmountValue = convertStringIntoDouble(currentAmount);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButton), "Pay button");
		searchAndSelectBiller(propertyFileReader("government0", "billsPayment"),propertyFileReader("nickName", "billsPayment"));
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), "Account Number");
		}
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberField), propertyFileReader("SmartAccountNumber", "billsPayment"), "Account Number");
		click(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPaymentTypeText), "Payment Type");
		for (int click = 0; click < 2; click++) {
			if(verifyElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objHousingLoan))) {
				verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objHousingLoan), "Housing Loan");
				break;
			}else {
				verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPaymentTypeText), "Payment Type");
			}
		}
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objContactNumberField), "Contact Number");
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objContactNumberField), propertyFileReader("ContactNumber", "billsPayment"), "Contact Number");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		enterAmountAndConfirm(propertyFileReader("SmartAccountNumber", "billsPayment"),propertyFileReader("PagIBIGLessThanMinAmount", "billsPayment"),propertyFileReader("PagIBIGMoreThanMaxAmount", "billsPayment"),propertyFileReader("PagIBIGValidAmount", "billsPayment"),propertyFileReader("OTP", "billsPayment"));
		verifyTransactionStatus(propertyFileReader("PagIBIGValidAmount", "billsPayment"));
		logger.info("TDB_BP_021, BillsPayment - verifyUserCanPayAsNewBiller_PagIBIG_TDB_BP_021");
		extentLoggerPass("TDB_BP_021", "TDB_BP_021, BillsPayment - verifyUserCanPayAsNewBiller_PagIBIG_TDB_BP_021 is passed");
	}

	/**
	 * method to Verify if user can pay the bill from Go to billers list option
	 * @param biller
	 * @param accountNumber
	 * @param accoutName
	 * @param amount
	 * @param convenienceFee
	 * @throws Exception
	 */
	public void verifyUserCanPayBillFromGoToBillersListOption_TDB_BP_022(String biller, String accountNumber, String accoutName,String amount,String convenienceFee) throws Exception {
		HeaderChildNode("TDB_BP_022, BillsPayment - Verify if user can pay the bill from Go to billers list option");
		tonikLogin(propertyFileReader("password", "Login"));
		assertionValidation(getAttributValue("enabled", BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButtonEnable)), "true", " enabled Attribute value");
		String tsaAmount = getText(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objTonikAmountOnDashboard)).substring(1).replace(",", "");
		double addExtraAmountToTSAAmount = Double.parseDouble(tsaAmount) + 5;
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButton), "Pay button");
		selectBillerAndNavigateToBillPayScreen(biller);
		verifyBillsPayPageAndDoThePaymentAndCheckTheSuccessMessage(biller, accountNumber, accoutName);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objGoToBillersListLink), getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objGoToBillersListLink)));
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText)), propertyFileReader("BillsPay", "billsPayment"), "Page Header");
		verticalSwipeTillElementDisplayed(BillsPaymentPage.objSavedBillersBasedOnAccountNumber(platform,accountNumber));
		waitForElementToBePresent(BillsPaymentPage.objSavedBillersBasedOnAccountNumber(platform,accountNumber),3," Recent Account");
		verifyElementPresentAndClick(BillsPaymentPage.objSavedBillersBasedOnAccountNumber(platform,accountNumber), " Recently Saved Biller");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayNowButton), getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayNowButton)) + " Button");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objErrorMessage)), propertyFileReader("PleaseEnterAmount", "billsPayment"), " Error Message");
		clearField(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountField),"Amount Textfield");
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountField), String.valueOf(addExtraAmountToTSAAmount), "Amount Textfield");
		if(platform.equalsIgnoreCase("Android")){
			hideKeyboard();
		}else{
			tapOnScreen(300,400,"screen");
		}
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayNowButton), getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayNowButton)) + " Button");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objErrorMessage)), propertyFileReader("InsufficientAmount", "billsPayment"), " Error Message");
		clearField(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountField),"Amount Textfield");
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountField), amount, "Amount Textfield");
		if(platform.equalsIgnoreCase("Android")){
			hideKeyboard();
		}else{
			tapOnScreen(300,400,"screen");
		}
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayNowButton), getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayNowButton)) + " Button");
		verifyConfirmBillsPaymentPage(amount,convenienceFee,biller,accountNumber);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objCloseIcon),"Cross Button");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText)), propertyFileReader("BillsPay", "billsPayment"), "Page Header");
		verticalSwipeTillElementDisplayed(BillsPaymentPage.objSavedBillersBasedOnAccountNumber(platform,accountNumber));
		waitForElementToBePresent(BillsPaymentPage.objSavedBillersBasedOnAccountNumber(platform,accountNumber),2," Recent Account");
		verifyElementPresentAndClick(BillsPaymentPage.objSavedBillersBasedOnAccountNumber(platform,accountNumber), " Recently Saved Biller");
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountField), amount, "Amount Textfield");
		if(platform.equalsIgnoreCase("Android")){
			hideKeyboard();
		}else{
			tapOnScreen(300,400,"screen");
		}
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayNowButton), getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayNowButton)) + " Button");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objConfirmButton),getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objConfirmButton))+" Button");
		click(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objOTPField), "OTP text field");
		DriverManager.getAppiumDriver().getKeyboard().sendKeys(RandomIntegerGenerator(6));
		verifyPaymentProcessingPage(String.valueOf(Double.parseDouble(amount)+Double.parseDouble(convenienceFee)),biller);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBackToDashboardButton),getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBackToDashboardButton))+" Button");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objTonikAccountNumberText)),propertyFileReader("YourTonikAccount","billsPayment")," Page");
		logger.info("TDB_BP_022, BillsPayment - Verify if user can pay the bill from Go to billers list option");
		extentLoggerPass("TDB_BP_022", "Verify if user can pay the bill from Go to billers list option");
	}

	/**
	 * method to Verify if user can pay the bill from the saved billers list
	 * @param biller
	 * @param accountNumber
	 * @param accoutName
	 * @param amount
	 * @param convenienceFee
	 * @throws Exception
	 */
	public void verifyIfUserCanPayTheBillFromTheSavedBillerslist_TDB_BP_023(String biller, String accountNumber, String accoutName,String amount,String convenienceFee) throws Exception {
		HeaderChildNode("TDB_BP_023, BillsPayment - Verify if user can pay the bill from the saved billers list");
		tonikLogin(propertyFileReader("password", "Login"));
		assertionValidation(getAttributValue("enabled", BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButtonEnable)), "true", " enabled Attribute value");
		String tsaAmount = getText(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objTonikAmountOnDashboard)).substring(1).replace(",", "");
		double addExtraAmountToTSAAmount = Double.parseDouble(tsaAmount) + 5;
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButton), "Pay button");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText)), propertyFileReader("BillsPay", "billsPayment"), "Page Header");
		verticalSwipeTillElementDisplayed(BillsPaymentPage.objSavedBillersBasedOnAccountNumber(platform,accountNumber));
		waitForElementToBePresent(BillsPaymentPage.objSavedBillersBasedOnAccountNumber(platform,accountNumber),3," Recent Account");
		verifyElementPresentAndClick(BillsPaymentPage.objSavedBillersBasedOnAccountNumber(platform,accountNumber), " Recently Saved Biller");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayNowButton), getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayNowButton)) + " Button");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objErrorMessage)), propertyFileReader("PleaseEnterAmount", "billsPayment"), " Error Message");
		clearField(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountField),"Amount Textfield");
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountField), String.valueOf(addExtraAmountToTSAAmount), "Amount Textfield");
		if(platform.equalsIgnoreCase("Android")){
			hideKeyboard();
		}else{
			tapOnScreen(300,400,"screen");
		}
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayNowButton), getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayNowButton)) + " Button");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objErrorMessage)), propertyFileReader("InsufficientAmount", "billsPayment"), " Error Message");
		clearField(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountField),"Amount Textfield");
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountField), amount, "Amount Textfield");
		if(platform.equalsIgnoreCase("Android")){
			hideKeyboard();
		}else{
			tapOnScreen(300,400,"screen");
		}
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayNowButton), getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayNowButton)) + " Button");
		verifyConfirmBillsPaymentPage(amount,convenienceFee,biller,accountNumber);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objCloseIcon),"Cross Button");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText)), propertyFileReader("BillsPay", "billsPayment"), "Page Header");
		verticalSwipeTillElementDisplayed(BillsPaymentPage.objSavedBillersBasedOnAccountNumber(platform,accountNumber));
		waitForElementToBePresent(BillsPaymentPage.objSavedBillersBasedOnAccountNumber(platform,accountNumber),2," Recent Account");
		verifyElementPresentAndClick(BillsPaymentPage.objSavedBillersBasedOnAccountNumber(platform,accountNumber), " Recently Saved Biller");
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountField), amount, "Amount Textfield");
		if(platform.equalsIgnoreCase("Android")){
			hideKeyboard();
		}else{
			tapOnScreen(300,400,"screen");
		}
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayNowButton), getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayNowButton)) + " Button");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objConfirmButton),getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objConfirmButton))+" Button");
		click(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objOTPField), "OTP text field");
		DriverManager.getAppiumDriver().getKeyboard().sendKeys(RandomIntegerGenerator(6));
		verifyPaymentProcessingPage(String.valueOf(Double.parseDouble(amount)+Double.parseDouble(convenienceFee)),biller);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBackToDashboardButton),getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBackToDashboardButton))+" Button");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objTonikAccountNumberText)),propertyFileReader("YourTonikAccount","billsPayment")," Page");
		logger.info("TDB_BP_023, BillsPayment - Verify if user can pay the bill from the saved billers list");
		extentLoggerPass("TDB_BP_023", "Verify if user can pay the bill from the saved billers list");
	}

	/**
	 * method to Verify if account holder can delete the saved biller
	 * @throws Exception
	 */
	public void verifyAccountHolderCanDeleteTheSavedBiller_TDB_BP_024() throws Exception {
		HeaderChildNode("TDB_BP_024, BillsPayment -Verify if account holder can delete the saved biller");
		tonikLogin(propertyFileReader("password", "Login"));
		assertionValidation(getAttributValue("enabled", BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButtonEnable)), "true", " enabled Attribute value");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButton), "Pay button");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText)), propertyFileReader("BillsPay", "billsPayment"), "Page Header");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAddIcon), "Add a Biller");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMybillsText)), propertyFileReader("MyBills", "billsPayment"), " text");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objFirstMyBillsList), "My Bills List");
		String myBillsAccountNumberBeforeDelete = getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objFirstBillsAccountNumber));
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objFirstBillsEllipsisIcon), "Ellipsis Icon");
		assertionValidation(getText(BillsPaymentPage.objTextButton(platform,"Pay")), propertyFileReader("Pay", "billsPayment"), " Button");
		assertionValidation(getText(BillsPaymentPage.objTextButton(platform,"Delete Biller")), propertyFileReader("Delete", "billsPayment"), " Button");
		assertionValidation(getText(BillsPaymentPage.objTextButton(platform,"Cancel")), propertyFileReader("Cancel", "billsPayment"), " Button");
		click(BillsPaymentPage.objTextButton(platform,"Cancel"), "Cancel Button");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMybillsText)), propertyFileReader("MyBills", "billsPayment"), " text");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objFirstBillsEllipsisIcon), "Ellipsis Icon");
		click(BillsPaymentPage.objTextButton(platform,"Delete Biller"), "Delete Biller Button");
		waitForElementToBePresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMybillsText), 2, "My bills");
		if (verifyElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objFirstMyBillsList))) {
			if (!getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objFirstBillsAccountNumber)).equals(myBillsAccountNumberBeforeDelete)) {
				logger.info("Deleted Biller Is not Displaying In My Bills List");
			} else {
				logger.error("Deleted Biller Is Still Displaying");
				extentLoggerFail("Fail", "Deleted Biller Is Still Displaying");
			}
		} else {
			logger.info("Deleted Biller Is not Displaying In My Bills List");
		}
		logger.info("TDB_BP_024, BillsPayment - Verify if account holder can delete the saved biller");
		extentLoggerPass("TDB_BP_024", "Verify if account holder can delete the saved biller");
	}

	/**
	 * method to Verify the error message for the unsuccessful bill payment
	 * @param billerName
	 * @param accountName
	 * @throws Exception
	 */
	public void verifyErrorMessageForTheUnsuccessfulBillPayment_TDB_BP_025(String billerName, String accountName) throws Exception {
		HeaderChildNode("TDB_BP_025, BillsPayment -Verify the error message for the unsuccessful bill payment");
		tonikLogin(propertyFileReader("password", "Login"));
		String tsaAmount = getText(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objTonikAmountOnDashboard));
		assertionValidation(getAttributValue("enabled", BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButtonEnable)), "true", " enabled Attribute value");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButton), "Pay button");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText)), propertyFileReader("BillsPay", "billsPayment"), "Page Header");
		String[] buttons = {"Try again", "Back to Dashboard"};
		for (int i = 0; i < 2; i++) {
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAddIcon), "Add a Biller");
			assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPopularBillersTitle)), propertyFileReader("PopularBillers", "billsPayment"), "Text");
			verifyElementPresentAndClick(BillsPaymentPage.objAllBillersName(platform,billerName), " Biller");
			verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNameThisBillPageHeader), getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNameThisBillPageHeader)));
			verifyElementPresentAndClick(BillsPaymentPage.objTextButton(platform,"Next"), "Next Button");
			assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountInfoPageHeader)), propertyFileReader("AccountInfo", "billsPayment"), " Page Header");
			type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberTextField), RandomIntegerGenerator(16), "Account Number Textfield");
			type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNameTextField), accountName, "Account Name Textfield");
			verifyElementPresentAndClick(BillsPaymentPage.objTextButton(platform,"Next"), "Next Button");
			assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objWhenIsYourDueDate)), propertyFileReader("WhenISYourDueDate", "billsPayment"), " Page Header");
			verifyElementPresentAndClick(BillsPaymentPage.objTextButton(platform,"Skip"), "Skip Button");
			assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillersCreatedSuccessMessage)), billerName + " was successfully created", "Success Message");
			verifyElementPresentAndClick(BillsPaymentPage.objTextButton(platform,"Pay now"), "Pay now Button");
			assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayBill)), propertyFileReader("PayBill", "billsPayment"), " Page Header");
			type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountField), "100", "Amount textfield");
			if(platform.equalsIgnoreCase("Android")){
				hideKeyboard();
			}else{
				tapOnScreen(300,400,"screen");
			}
			verifyElementPresentAndClick(BillsPaymentPage.objTextButton(platform,"Pay now"), "Pay now Button");
			waitForElementToBePresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objConfirmButton), 2, "Confirm Button");
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objConfirmButton), "Confirm Button");
			assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objEnterOtpPage)), propertyFileReader("EnterOtp", "billsPayment"), "OTP Page");
			click(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objOTPField), "OTP text field");
			DriverManager.getAppiumDriver().getKeyboard().sendKeys(RandomIntegerGenerator(6));
			waitForElementToBePresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objButterFingers), 5, "Success Message");
			assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objButterFingers)), propertyFileReader("ButterFingers", "billsPayment"), " Message");
			containsValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objButterFingersDescription)).replaceAll("\\s", " "), propertyFileReader("ButterfingerDescription", "billsPayment"), "Decsription");
			verifyElementPresent(BillsPaymentPage.objTextButton(platform,"Back to Dashboard"), "Back to Dashboard Button");
			verifyElementPresent(BillsPaymentPage.objTextButton(platform,"Try again"), "Try again Button");
			if (buttons[i].equals("Try again")) {
				click(BillsPaymentPage.objTextButton(platform,buttons[i]), buttons[i] + " Button");
				assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMybillsText)), propertyFileReader("MyBills", "billsPayment"), " text");
			}
			if (buttons[i].equals("Back to Dashboard")) {
				click(BillsPaymentPage.objTextButton(platform,buttons[i]), buttons[i] + " Button");
				assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objTonikAccountNumberText)), propertyFileReader("YourTonikAccount", "billsPayment"), " text");
				assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objTonikAccountNumber)), tsaAmount, " TSA Amount");
			}
		}
		logger.info("TDB_BP_025, BillsPayment - Verify the error message for the unsuccessful bill payment");
		extentLoggerPass("TDB_BP_025", "Verify the error message for the unsuccessful bill payment");
	}

	/**
	 * method to Verify if user can pay the bill with insufficient TSA balance
	 * @param billersName
	 * @param accountName
	 * @throws Exception
	 */
	public void verifyUserCanPayTheBillWithInsufficientTSABalance_TDB_BP_026(String billersName, String accountName) throws Exception {
		HeaderChildNode("TDB_BP_026, BillsPayment -Verify if user can pay the bill with insufficient TSA balance");
		tonikLogin(propertyFileReader("password", "Login"));
		String tsaAmount = getText(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objTonikAmountOnDashboard)).substring(1).replace(",", "");
		double addExtraAmountToTSAAmount = Double.parseDouble(tsaAmount) + 5;
		assertionValidation(getAttributValue("enabled", BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButtonEnable)), "true", " enabled Attribute value");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButton), "Pay button");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText)), propertyFileReader("BillsPay", "billsPayment"), "Page Header");
		waitForElementToBePresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objMainTitleText), 2, "Bills pay");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAddIcon), "Add a Biller");
		waitForElementToBePresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPopularBillersTitle), 2, "Popular Biller List");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPopularBillersTitle)), propertyFileReader("PopularBillers", "billsPayment"), "Text");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objFirstPopularBillers), "First Popular Biller");
		verifyElementPresent(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNameThisBillPageHeader), getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNameThisBillPageHeader)));
		verifyElementPresentAndClick(BillsPaymentPage.objTextButton(platform,"Next"), "Next Button");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountInfoPageHeader)), propertyFileReader("AccountInfo", "billsPayment"), " Page Header");
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberTextField), RandomIntegerGenerator(16), "Account Number Textfield");
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNameTextField), accountName, "Account Name Textfield");
		verifyElementPresentAndClick(BillsPaymentPage.objTextButton(platform,"Next"), "Next Button");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objWhenIsYourDueDate)), propertyFileReader("WhenISYourDueDate", "billsPayment"), " Page Header");
		verifyElementPresentAndClick(BillsPaymentPage.objTextButton(platform,"Skip"), "Skip Button");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objBillersCreatedSuccessMessage)), billersName + " was successfully created", "Success Message");
		verifyElementPresentAndClick(BillsPaymentPage.objTextButton(platform,"Pay now"), "Pay now Button");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayBill)), propertyFileReader("PayBill", "billsPayment"), " Page Header");
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountField), String.valueOf(addExtraAmountToTSAAmount), "Amount Textfield");
		if(platform.equalsIgnoreCase("Android")){
			hideKeyboard();
		}else{
			tapOnScreen(300,400,"screen");
		}
		verifyElementPresentAndClick(BillsPaymentPage.objTextButton(platform,"Pay now"), "Pay now Button");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objErrorMessage)), propertyFileReader("InsufficientAmount", "billsPayment"), " Error Message");
		logger.info("TDB_BP_026, BillsPayment - Verify if user can pay the bill with insufficient TSA balance");
		extentLoggerPass("TDB_BP_026", "Verify if user can pay the bill with insufficient TSA balance");
	}

	/**
	 * method to Verify if user can request for new OTP
	 * @param biller
	 * @param accountNumber
	 * @param accoutName
	 * @param amount
	 * @param convenienceFee
	 * @throws Exception
	 */
	public void verifyUserCanRequestForNewOTP_TDB_BP_030(String biller, String accountNumber, String accoutName,String amount,String convenienceFee) throws Exception {
		HeaderChildNode("TDB_BP_030, BillsPayment - Verify if user can request for new OTP");
		tonikLogin(propertyFileReader("password", "Login"));
		assertionValidation(getAttributValue("enabled", BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButtonEnable)), "true", " enabled Attribute value");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButton), "Pay button");
		selectBillerAndNavigateToBillPayScreen(biller);
		verifyBillsPayPageAndDoThePaymentAndCheckTheSuccessMessage(biller, accountNumber, accoutName);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPaynowButtonInSuccessPage), getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPaynowButtonInSuccessPage)));
		clearField(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountField),"Amount Textfield");
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAmountField), amount, "Amount Textfield");
		if(platform.equalsIgnoreCase("Android")){
			hideKeyboard();
		}else{
			tapOnScreen(300,400,"screen");
		}
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayNowButton), getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayNowButton)) + " Button");
		verifyConfirmBillsPaymentPage(amount,convenienceFee,biller,accountNumber);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objConfirmButton),getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objConfirmButton))+" Button");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objEnterOtpPage)),propertyFileReader("EnterOtp","billsPayment")," OTP Page");
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objResendCodeLinkText),getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objResendCodeLinkText))+" Button");
		assertionValidation(getText(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objEnterOtpPage)),propertyFileReader("EnterOtp","billsPayment")," OTP Page");
		logger.info("TDB_BP_030, BillsPayment - Verify if user can request for new OTP");
		extentLoggerPass("TDB_BP_030", "Verify if user can request for new OTP");
	}

	/**
	 * method to Verify if BKYC user can make the bill payment
	 * @throws Exception
	 */
	public void verifyBKYCUserCanMakeBillPayment_TDB_BP_027() throws Exception {
		HeaderChildNode("TDB_BP_027, Verify if BKYC user can make the bill payment - TDB_BP_027");
		tonikLogin(propertyFileReader("password", "Login"));
		String currentAmount = getText(BillsPaymentPage.getByOSType(platform,BillsPaymentPage.objTonikAmountOnDashboard)).replace("₱", "").replace(",", "");
        currentAmountValue = convertStringIntoDouble(currentAmount);
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPayButton), "Pay button");
		searchAndSelectBiller(propertyFileReader("telecoms1", "billsPayment"),propertyFileReader("nickName", "billsPayment"));
		if(platform.equalsIgnoreCase("Android")){
			waitForElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), 10);
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberText), "Account Number");
		}
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objAccountNumberField), propertyFileReader("PLDTAccountNumber", "billsPayment"), "Account Number");
		if(platform.equalsIgnoreCase("Android")){
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPhoneNumberText), "Phone number");
		}
		type(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPhoneNumberField), propertyFileReader("PhoneNumber", "billsPayment"), "Phone Number");
		if(platform.equalsIgnoreCase("Android")){
			hideKeyboard();
			verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objFirstNameText), "First Name");
		}else{
			tapOnScreen(350,100,"screen");
		}
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objServiceText), "Service");
		for (int i = 0; i < 2; i++) {
			if(verifyElementDisplayed(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPLDTLandline))) {
				verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objPLDTLandline), "PLDT Landline");
				break;
			}else {
				verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objServiceText), "Service");
			}
		}
		verifyElementPresentAndClick(BillsPaymentPage.getByOSType(platform, BillsPaymentPage.objNextButton), "Next");
		enterAmountAndConfirm(propertyFileReader("PLDTAccountNumber", "billsPayment"),propertyFileReader("PLDTLessThanMinAmount", "billsPayment"),propertyFileReader("PLDTMoreThanMaxAmount", "billsPayment"),propertyFileReader("PLDTValidAmount", "billsPayment"),propertyFileReader("OTP", "billsPayment"));
		verifyTransactionStatus(propertyFileReader("PLDTValidAmount", "billsPayment"));
		logger.info("TDB_BP_027, BillsPayment - verifyBKYCUserCanMakeBillPayment_TDB_BP_027");
		extentLoggerPass("TDB_BP_027", "TDB_BP_027, BillsPayment - verifyBKYCUserCanMakeBillPayment_TDB_BP_027 is passed");
	}
}