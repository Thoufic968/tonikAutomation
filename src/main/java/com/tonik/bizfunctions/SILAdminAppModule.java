package com.tonik.bizfunctions;


import com.opencsv.CSVReader;
import com.tonik.pageObject.SILAdminAppPage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static com.tonik.bizfunctions.SILPurpleAppModule.jsClick;
import static com.tonik.utility.Utilities.*;


public class SILAdminAppModule extends BaseClass{
    public SILAdminAppModule(){
        super();
    }
    /**
     * Common method to Login to Purple App
     * @param userName
     * @param password
     * @throws Exception
     */
    public void adminAppLogin(String userName,String password,String sssUsername,String sssPassword) throws Exception {
        waitForElementToBePresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPurpleAdminApp),20,"Welcome screen");
        assertionValidation(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPurpleAdminApp)),propertyFileReader("PurpleAdminAppTxt","SILAdminApp"),"text");
        if(verifyElementDisplayed(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objUserNameInputField))){
            type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objUserNameInputField),userName,"User name input field");
            type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPasswordInputField),password,"Password input field");
        }
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objLoginBtn),getTextVal(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objLoginBtn),"Button"));
        waitForElementToBePresent(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objSSSUserNameInputField), 3, "SSS username screen");
        type(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objSSSUserNameInputField), sssUsername, "User name input field");
        waitTime(1000);
        click(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objNextBtn), "Next button");
        type(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objSSSPasswordInputField), sssPassword, "Password input field");
        waitTime(2000);
        click(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objNextBtn), "Sign in button");
        waitTime(4000);
        if(verifyElementDisplayed(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objSSSUserNameInputField))){
            type(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objSSSUserNameInputField), sssUsername, "User name input field");
            click(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objNextBtn), "Next button");
            type(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objSSSPasswordInputField), sssPassword, "Password input field");
            waitTime(3000);
            click(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objNextBtn), "Sign in button");
        }
    }
    /**
     * Method to get the latest download csv file
     * @throws Exception
     */
    private static File getLatestFileFromDir(String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".csv");
            }
        });
        if (files != null && files.length > 0) {
            Arrays.sort(files, new Comparator<File>() {
                public int compare(File o1, File o2) {
                    return Long.compare(o2.lastModified(), o1.lastModified());
                }
            });
            return files[0];
        }
        return null;
    }
    /**
     * Method to iterate and compare the status column on downloaded csv file
     * @throws Exception
     */
    public void compareStatus(String csvFilePath,int columnIndex){
        try (CSVReader csvReader = new CSVReader(new FileReader(csvFilePath))) {
            List<String[]> rows = csvReader.readAll();
            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                if (row.length > columnIndex) {
                    String cellValue = row[columnIndex];
                    if ("Active".equalsIgnoreCase(cellValue)) {
                        logger.info("Row " + i + ": Active");
                        extentLoggerPass("", "Row " + i + ": Active");
                    } else if ("Disabled".equalsIgnoreCase(cellValue)) {
                        logger.info("Row " + i + ": Disabled");
                        extentLoggerPass("", "Row " + i + ": Disabled");
                    } else {
                        System.out.println("Row " + i + ": Other value - " + cellValue);
                        logger.error("Row " + i + ": disabled");
                        extentLoggerFail("", "Row " + i + ": Disabled");
                    }
                } else {
                    System.out.println("Row " + i + ": Empty cell or not enough columns");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Method to Search Merchant
     * @throws Exception
     */
    public void searchMerchant(String value, String filter_value) throws Exception {
        waitForElementToBePresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPromoterSearch),10,"Merchant search field");
        selectByValue(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objSearchType),filter_value);
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPromoterSearch),value,"Merchant search input field");
        Actions actions = new Actions(getWebDriver());
        actions.sendKeys(Keys.ENTER).perform();
        waitTime(5000);
    }
    /**
     * Method to Search Promoter
     * @throws Exception
     */
    public void searchPromoter(String firstName,String value) throws Exception {
        waitForElementToBePresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPromoterSearch),5,"Promoter search field");
        selectByValue(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objSearchType),value);
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPromoterSearch),firstName,"Promoter serach input field");
        Actions actions = new Actions(getWebDriver());
        actions.sendKeys(Keys.ENTER).perform();
        waitTime(5000);
    }
    /**
     * Method to Search Partner
     * @throws Exception
     */
    public void searchPartner(String Search_value,String filter_value) throws Exception {
        waitForElementToBePresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPromoterSearch),5,"Partners search field");
        selectByValue(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objSearchType),filter_value);
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPromoterSearch),Search_value,"Partners search input field");
        Actions actions = new Actions(getWebDriver());
        actions.sendKeys(Keys.ENTER).perform();
        waitTime(5000);
    }
    /**
     * Method to verify the value on existed
     * @throws Exception
     */
    public String isNotValueExisted(String value, By objectDropDown) {
        String result= "true";
        try {
            Select selection= new Select(findElement(objectDropDown));
            selection.selectByVisibleText(value);
        }catch(Exception e) {
            result = "false";
        }
        return result;
    }
    /**
     * Method to navigate merchant list
     * @throws Exception
     */
    public void navigateToMerchantList() throws Exception {
        mouseHover(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objSidebar));
        click(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantToggle),"Merchant toggle");
        click(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantListTab),"Merchant-LIst Tab");
    }
    /**
     * Method to navigate merchant Bulk report
     * @throws Exception
     */
    public void navigateToMerchantBulkReport() throws Exception {
        mouseHover(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objSidebar));
        click(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantToggle),"Merchant toggle");
        click(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantBulkReportTab),"Bulk Upload Report Tab");
    }

    /**
    *Scenario that verify the merchant status is disabled
     * @throws Exception
    */
    public void verifyMerchantEditStatus(String value, String filter_value) throws Exception {
        mouseHover(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objSidebar));
        click(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantToggle),"Merchant toggle");
        click(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantListTab),"Merchant-LIst Tab");
        containsValidation(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantListHeader)),"Merchants","Merchant header");
        searchMerchant(value,filter_value);
        if(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantListStatus)).equals("Active")){
            waitTime(2000);
            jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditButton),"Merchant edit button");
            scrollToBottomOfPageWEB();
            waitTime(2000);
            jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantActiveToggle),"active toggle button");
            containsValidation(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditHeader)),"Edit Merchant","Merchant edit header");
            assertionValidation(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditStatus)),propertyFileReader("DisabledStatus","SILAdminApp"),"Status is disabled");
            jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditBack),"Merchant edit back button");
        }else {
            waitTime(2000);
            jsClick(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objMerchantEditButton), "Merchant edit button");
            scrollToBottomOfPageWEB();
            waitTime(2000);
            if(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditStatus)).equals("Active")){
                jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantActiveToggle),"active toggle button");
            }
            containsValidation(getText(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objMerchantEditHeader)), "Edit Merchant", "Merchant edit header");
            assertionValidation(getText(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objMerchantEditStatus)), propertyFileReader("DisabledStatus", "SILAdminApp"), "Status is disabled");
            jsClick(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objMerchantEditBack), "Merchant edit back button");
        }
    }
    /**
     * Method to get the value of partner from column
     * @throws Exception
     */
    public List<String> partnerColumnGetter(By column_xpath){
        waitTime(5000);
        List<WebElement> elements = getWebDriver().findElements(column_xpath);
        for(WebElement element : elements)
        {
            logger.info(element.getText());
        }
        List<String> columnValue = new ArrayList<>();
        elements.forEach(variable -> columnValue.add(variable.getText()));
        return columnValue;
    }
    /**
     * Method to upload csv file
     * @throws Exception
     */
    public String uploadCSVFileSendKeys(String FileName) throws Exception {
        File CSVFilePath = new File("testDataCSV/").getAbsoluteFile();
        File originalFile = new File(CSVFilePath + "/" + FileName);
        File renamedFile = new File(System.getProperty("user.home") + "/testDataCSV/" +
                FileName.replace(".csv", "") + RandomStringUtils.randomAlphanumeric(1) + ".csv");
        FileUtils.copyFile(originalFile, renamedFile);
        String getRenamedFile = String.valueOf(renamedFile);
        try {
            WebElement dropFile = getWebDriver().findElement(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objMerchantUploadDropFile));
            dropFile.sendKeys(getRenamedFile);
            extentLoggerPass("","Uploading this file path "+getRenamedFile);
            return renamedFile.getAbsolutePath();
        }catch (Exception e){
            extentLoggerFail("","Error Message "+ e.getMessage());
        }
        return renamedFile.getAbsolutePath();
    }
    /**
     * Method to Verify the merchant management edit option
     * @throws Exception
     */
    public void VerifyTheMerchantManagementEditOptionTDB_MA_12(String userName,String password,String sssUsername,String sssPassword) throws Exception {
        HeaderChildNode("TDB_MA_012, SIL Admin App - Verify the merchant management edit option");
        adminAppLogin(userName,password,sssUsername,sssPassword);
        verifyMerchantEditStatus(propertyFileReader("SearchMerchant","SILAdminApp"),propertyFileReader("merchantNameFilterByValue","SILAdminApp"));
        searchMerchant(propertyFileReader("SearchMerchant","SILAdminApp"),propertyFileReader("merchantNameFilterByValue","SILAdminApp"));
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditButton),"Merchant edit button");
        scrollToBottomOfPageWEB();
        if(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditStatus)).equals("Active")){
            jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantActiveToggle),"active toggle button");
        }
        containsValidation(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditStatus)),"Disabled","Verified if status is active");
        scrollByWEB();
        waitTime(2000);
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditSaveButton),"Save button");
        waitTime(3000);
        searchMerchant(propertyFileReader("SearchMerchant","SILAdminApp"),propertyFileReader("merchantNameFilterByValue","SILAdminApp"));
        waitTime(10000);
        containsValidation(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantListStatus)),"Disabled","Verify the system saves the status as disabled");
        js = (JavascriptExecutor) getWebDriver();
        js.executeScript("window.open()");
        Object[] windowHandles=getWebDriver().getWindowHandles().toArray();
        getWebDriver().switchTo().window((String) windowHandles[1]);
        getWebDriver().get(propertyFileReader("promoterApp","SILAdminApp"));
        logger.info("Navigate to "+propertyFileReader("promoterApp","SILAdminApp"));
        SILPurpleAppModule.purpleAppLogin(userName,password);
        assertionValidation(isNotValueExisted("TestPhase2",SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantPurpleStoreDropdown)),"false","Promoter should not be able to select the disabled merchant");
        waitTime(2000);
        getWebDriver().close();
        getWebDriver().switchTo().window((String) windowHandles[0]);
        waitTime(2000);
        mouseHover(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objSidebar));
        waitTime(1000);
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPromotersSubTab),"Promoter");
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPromoterListTab),"Promoter list tab");
        searchPromoter(propertyFileReader("searchPromoter","SILAdminApp"),propertyFileReader("LastNameFilterByValue","SILAdminApp"));
        waitTime(5000);
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objFirstActivePromoter),"edit button");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objEditPromoterPage),"Edit Promoter header");
        if(getText(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objPromoterStatus)).equals("Active")) {
            containsValidation(getText(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objPromoterStatus)), "Active", "Verify the 'Status' button is displayed as enabled");
            jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objToggleButton),"Status toggle button");
            containsValidation(getText(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objPromoterStatus)), "Disabled", "Toggle the 'Status' button to disable.");
            jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditBack),"Edit back");
            searchPromoter(propertyFileReader("searchPromoter","SILAdminApp"),propertyFileReader("LastNameFilterByValue","SILAdminApp"));
            waitTime(2000);
            containsValidation(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPromoterStatusRow)),"Active","status of promoter didn't change");
        }else{
            containsValidation(getText(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objPromoterStatus)), "Disabled", "Verify the 'Status' button is displayed as Disabled");
            jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objToggleButton),"Status toggle button");
            containsValidation(getText(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objPromoterStatus)), "Active", "Toggle the 'Status' button to active.");
            jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditBack),"Edit back");
            searchPromoter(propertyFileReader("searchPromoter","SILAdminApp"),propertyFileReader("LastNameFilterByValue","SILAdminApp"));
            waitTime(2000);
            containsValidation(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPromoterStatusRow)),"Disabled","status of promoter didn't change");
        }
        logger.info("TDB_MA_012, SIL Admin App - Verify the merchant management edit option");
        extentLoggerPass("TDB_MA_012","TDB_MA_012, SIL Admin App - Verify the merchant management edit option");
    }
    /**
     * Method to verify the enable /disable option by clicking edit pencil button
     * @throws Exception
     */
    public void ToVerifyTheEnableDisableOptionByClickingEditPencilButton_TDB_MA_013(String userName,String password,String sssUsername,String sssPassword) throws Exception {
        HeaderChildNode("TDB_MA_013, SIL Admin App - To verify the enable /disable option by clicking edit pencil button");
        adminAppLogin(userName,password,sssUsername,sssPassword);
        verifyMerchantEditStatus("TestPhase2","merchantName");
        click(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantToggle),"Merchant toggle");
        click(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantListTab),"Merchant-LIst Tab");
        containsValidation(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantListHeader)),"Merchants","Merchant header");
        searchMerchant(propertyFileReader("SearchMerchant","SILAdminApp"),propertyFileReader("merchantNameFilterByValue","SILAdminApp"));
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditButton),"Merchant edit button");
        scrollToBottomOfPageWEB();
        waitTime(5000);
        if(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditStatus)).equals("Disabled")){
            jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantActiveToggle),"active toggle button");
        }
        containsValidation(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditStatus)),"Active","Verified if status is active");
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditSaveButton),"Save button");
        js = (JavascriptExecutor) getWebDriver();
        js.executeScript("window.open()");
        Object[] windowHandles=getWebDriver().getWindowHandles().toArray();
        getWebDriver().switchTo().window((String) windowHandles[1]);
        getWebDriver().get(propertyFileReader("promoterApp","SILAdminApp"));
        SILPurpleAppModule.purpleAppLogin(userName,password);
        assertionValidation(isNotValueExisted("TestPhase2",SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantPurpleStoreDropdown)),"true","Merchant status is enabled and the merchant is displayed in the Merchant Store dropdown on the Purple app.");
        getWebDriver().close();
        getWebDriver().switchTo().window((String) windowHandles[0]);
        logger.info("TDB_MA_013, SIL Admin App - To verify the enable /disable option by clicking edit pencil button");
        extentLoggerPass("TDB_MA_013","TDB_MA_013, SIL Admin App - To verify the enable /disable option by clicking edit pencil button");
    }
    /**
     * Method to Verify the bulk upload and merchant management.
     * @throws Exception
     */
    public void verifyTheBulkUploadAndMerchantManagement_TDB_MA_014(String userName,String password,String sssUsername,String sssPassword) throws Exception {
        File CSVFilePath = new File("testDataCSV/").getAbsoluteFile();
        HeaderChildNode("TDB_MA_014, SIL Admin App - Verify the bulk upload and merchant management.");
        adminAppLogin(userName,password,sssUsername,sssPassword);
        navigateToMerchantList();
        containsValidation(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantListHeader)),"Merchants","Merchant header");
        click(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantListUploadButton),"Bulk upload button");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantUploadAddLabel),"add upload is displayed");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantUploadModifyLabel),"modify upload is displayed");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantUploadEnableLabel),"enable upload is displayed");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantUploadDisableLabel),"disable upload is displayed");
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantUploadAddLabel),"Click the add option");
        uploadCSVFileSendKeys(propertyFileReader("MerchantAddFileN","SILAdminApp"));
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantUploadFileSize),"File size");
        containsValidation(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantUploadFileName)),"","Merchant_User_Creation.csv");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantUploadFileName),"File name");
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantUploadCSVButton),"Upload CSV button");
        waitTime(3000);
        navigateToMerchantBulkReport();
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantBulkReportHeader),"Merchant - Bulk Upload Report header");
        if(getText(SILAdminAppPage.objMerchantUploadedStatusByFileName(propertyFileReader("MerchantAddFileN","SILAdminApp"),"")).equals("Completed")) {
            verifyElementPresent(SILAdminAppPage.objMerchantUploadedStatusByFileName(propertyFileReader("MerchantAddFileN", "SILAdminApp"), "Completed"), "add option with the upload Completed.");
        }else{
            verifyElementPresent(SILAdminAppPage.objMerchantUploadedStatusByFileName(propertyFileReader("MerchantAddFileN", "SILAdminApp"), "Progress"), "add option with the upload Progress.");
        }
        navigateToMerchantList();
        click(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantListUploadButton),"Bulk upload button");
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantUploadAddLabel),"Click the add option");
        WebElement dropFile = getWebDriver().findElement(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantUploadDropFile));
        dropFile.sendKeys(CSVFilePath+"/"+propertyFileReader("MerchantAddFileN","SILAdminApp"));
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantUploadCSVButton),"Upload CSV button");
        waitTime(3000);
        containsValidation(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantUploadErrorMessage)),"File name already exists. Please use a different file name.","Error message for existing file name");
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantUploadModalClose),"Upload CSV modal close button");
        navigateToMerchantBulkReport();
        verifyElementPresent(SILAdminAppPage.objMerchantUploadedStatusByFileName(propertyFileReader("MerchantAddFileN","SILAdminApp"),"Failed"),"add with the upload failed.");
        navigateToMerchantList();
        click(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantListUploadButton),"Bulk upload button");
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantUploadDisableLabel),"Click the Disable option");
        uploadCSVFileSendKeys(propertyFileReader("MerchantDisableFileN","SILAdminApp"));
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantUploadCSVButton),"Upload CSV button");
        waitTime(3000);
        navigateToMerchantBulkReport();
        if(getText(SILAdminAppPage.objMerchantUploadedStatusByFileName(propertyFileReader("MerchantDisableFileN","SILAdminApp"),"")).equals("Completed")) {
            verifyElementPresent(SILAdminAppPage.objMerchantUploadedStatusByFileName(propertyFileReader("MerchantDisableFileN", "SILAdminApp"), "Completed"), "Disable with the upload Completed.");
        }else {
            verifyElementPresent(SILAdminAppPage.objMerchantUploadedStatusByFileName(propertyFileReader("MerchantDisableFileN", "SILAdminApp"), "Progress"), "Disable with the upload Progress.");
        }
        navigateToMerchantList();
        click(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantListUploadButton),"Bulk upload button");
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantUploadEnableLabel),"Click the Enable option");
        uploadCSVFileSendKeys(propertyFileReader("MerchantEnableFileN","SILAdminApp"));
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantUploadCSVButton),"Upload CSV button");
        waitTime(3000);
        navigateToMerchantBulkReport();
        if(getText(SILAdminAppPage.objMerchantUploadedStatusByFileName(propertyFileReader("MerchantEnableFileN","SILAdminApp"), "")).equals("Completed"))
        {
            verifyElementPresent(SILAdminAppPage.objMerchantUploadedStatusByFileName(propertyFileReader("MerchantEnableFileN", "SILAdminApp"), "Completed"), "Enable with the upload Completed.");
        }else {
            verifyElementPresent(SILAdminAppPage.objMerchantUploadedStatusByFileName(propertyFileReader("MerchantEnableFileN", "SILAdminApp"), "Progress"), "Enable with the upload progress.");
        }
        navigateToMerchantList();
        click(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantListUploadButton),"Bulk upload button");
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantUploadModifyLabel),"Click the Modify option");
        uploadCSVFileSendKeys(propertyFileReader("MerchantModifyFileN","SILAdminApp"));
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantUploadCSVButton),"Upload CSV button");
        waitTime(3000);
        navigateToMerchantBulkReport();
        if(getText(SILAdminAppPage.objMerchantUploadedStatusByFileName(propertyFileReader("MerchantModifyFileN","SILAdminApp"), "")).equals("Completed"))
        {
            verifyElementPresent(SILAdminAppPage.objMerchantUploadedStatusByFileName(propertyFileReader("MerchantModifyFileN", "SILAdminApp"), "Completed"), "Modify with the upload Completed.");
        }else{
            verifyElementPresent(SILAdminAppPage.objMerchantUploadedStatusByFileName(propertyFileReader("MerchantModifyFileN", "SILAdminApp"), "Progress"), "Modify with the upload progress.");
        }
        logger.info("TDB_MA_014, SIL Admin App - Verify the merchant list extract file");
        extentLoggerPass("TDB_MA_014", "TDB_MA_014, SIL Admin App - Verify the bulk upload and merchant management.");
    }
    /**
     * Method to Verify the merchant list extract file
     * @throws Exception
     */
    public void verifyTheMerchantListExtractFile_TDB_MA_015(String userName,String password,String sssUsername,String sssPassword) throws Exception {
        String currentUsersHomeDir = System.getProperty("user.home");
        HeaderChildNode("TDB_MA_015, SIL Admin App - Verify the merchant list extract file");
        adminAppLogin(userName,password,sssUsername,sssPassword);
        navigateToMerchantList();
        containsValidation(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantListHeader)),"Merchants","Merchant header");
        waitTime(9000);
        click(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantListDownloadButton),"Download option button");
        waitForElementToBePresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objDownloadSuccessMsg),5,"Download Success message");
        containsValidation(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objDownloadSuccessMsg)),"Download Successfull!","Flash message after download");
        waitTime(5000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        waitTime(10000);
        File latestFile = getLatestFileFromDir(currentUsersHomeDir+"/Downloads/");
        if (latestFile != null && latestFile.getName().endsWith(".csv")) {
            containsValidation(latestFile.getName(),propertyFileReader("MerchantDownloadFileName","SILAdminApp"),"Downloaded file name");
            logger.info("The latest downloaded file is in CSV format: " + latestFile.getName());
            extentLoggerPass("","The latest downloaded file is in CSV format: " + latestFile.getName());
        } else {
            logger.error("The latest downloaded file is not in CSV format or no file found.");
            extentLoggerFail("","The latest downloaded file is not in CSV format or no file found.");
        }
        try (CSVReader csvReader = new CSVReader(new FileReader(latestFile))) {
            String[] firstRow = csvReader.readNext();
            if (firstRow != null) {
                for (int i = 0; i < firstRow.length; i++) {
                    containsValidation(firstRow[i], propertyFileReader("MerchantRowColumn" + i, "SILAdminApp"), "The .CSV file is correctly formatted and contains all expected data fields");
                }
            } else {
                logger.info("The CSV file is empty.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("TDB_MA_015, SIL Admin App - Verify the merchant list extract file");
        extentLoggerPass("TDB_MA_015", "TDB_MA_015, SIL Admin App - Verify the merchant list extract file");


    }
    /**
     * Method to Verify Partner Management | View & Search Partner
     * @throws Exception
     */
    public void verifyMerchantManagementViewSearchPartner_TDB_MA_016(String userName,String password,String sssUsername,String sssPassword) throws Exception {
        HeaderChildNode("TDB_MA_016, SIL Admin App - Verify Partner Management | View & Search Partner");
        adminAppLogin(userName,password,sssUsername,sssPassword);
        mouseHover(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objSidebar));
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPartnerTab),"Partner tab");
        waitTime(5000);
        containsValidation(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPromotersPage)),"Partners","Partners Header");
        List<WebElement> partnerValue = getWebDriver().findElements(By.xpath("//tbody[@class='table table--bordered']/tr/td"));
        for(WebElement element : partnerValue)
        {
            logger.info(" "+ element.getText());
        }
        if(partnerValue.isEmpty()){
            extentLoggerFail("","no existing partners");
        }else{
            extentLoggerPass(""," list of Partners existing ");
        }
        waitTime(3000);
        int i = 0;
        List<WebElement> partnerHeader = getWebDriver().findElements(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPartnerColumnHeader));
        for(WebElement element : partnerHeader)
        {
            i+=1;
            containsValidation(element.getText(),propertyFileReader("PartnerHeaderColumn"+i,"SILAdminApp"),"Verify the column header");
        }
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPartnerCreatedDateSortBy),"created date sort by");
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPartnerCreatedDateSortBy),"created date sort by");
        if(checkListIsSorted(partnerColumnGetter(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPartnerCreatedDateColumn)))){
            logger.info("Created date is descending");
        }else{
            logger.info("Create date is Ascending");
        }
        int paginationNumber = Integer.parseInt(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPartnerPaginationDropdown)));
        if(paginationNumber<=10 ){
            logger.info("Pagination number is "+paginationNumber);
            extentLoggerPass("","User should have page option either 10 or less than that based on the size of window");
        }else{
            logger.info("Pagination number is "+paginationNumber);
            extentLoggerFail("","page option is not 10 or less than that based on the size of window ");
        }
        jsClick(SILAdminAppPage.objPageButton("2"),"Page 2");
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPaginationNext),"next page");
        verifyElementPresent(SILAdminAppPage.objPageButton("5"),"pagination number 5");
        searchPartner(generateRandomString(10),"partnerId");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPartnerNoSearchFound),"No search found");
        logger.info("TDB_MA_016, SIL Admin App - Verify Partner Management | View & Search Partner");
        extentLoggerPass("TDB_MA_016", "TDB_MA_016, SIL Admin App - Verify Partner Management | View & Search Partner");
    }
    /**
     * Method to Verify Merchant Management | View / Search Merchant
     * @throws Exception
     */
    public void verifyMerchantManagementViewSearchMerchant_TDB_MA_017(String userName,String password,String sssUsername,String sssPassword) throws Exception {
        HeaderChildNode("TDB_MA_017, SIL Admin App - Verify Merchant Management | View / Search Merchant");
        adminAppLogin(userName,password,sssUsername,sssPassword);
        navigateToMerchantList();
        containsValidation(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantListHeader)),"Merchants","Merchant header");
        int i = 0;
        List<WebElement> partnerHeader = getWebDriver().findElements(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPartnerColumnHeader));
        for(WebElement element : partnerHeader)
        {
            containsValidation(element.getText().replace(", ",""),propertyFileReader("MerchantRowColumn"+i,"SILAdminApp"),"Verify the column header");
            i++;
        }
        if(checkListIsSorted(partnerColumnGetter(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantCreatedDate)))){
            logger.info("Created date is Ascending");
        }else{
            logger.info("Create date is descending");
        }
        int paginationNumber = Integer.parseInt(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPartnerPaginationDropdown)));
        if(paginationNumber<=10 ){
            logger.info("Pagination number is "+paginationNumber);
            extentLoggerPass("","User should have page option either 10 or less than that based on the size of window");
        }else{
            logger.info("Pagination number is "+paginationNumber);
            extentLoggerFail("","page option is not 10 or less than that based on the size of window ");
        }
        jsClick(SILAdminAppPage.objPageButton("2"),"Page 2");
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPaginationNext),"next page");
        verifyElementPresent(SILAdminAppPage.objPageButton("5"),"pagination number 5");
        searchMerchant(propertyFileReader("SearchMerchant","SILAdminApp"),propertyFileReader("merchantNameFilterByValue","SILAdminApp"));
        containsValidation(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantNameColumnValue)),"TestPhase2","matching results based on the text entered");
        searchMerchant(propertyFileReader("SearchMerchant","SILAdminApp"),propertyFileReader("merchantNameFilterByValue","SILAdminApp"));
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPartnerNoSearchFound),"No search found");
        logger.info("TDB_MA_017, SIL Admin App - SIL | Purple Admin App UI | Merchant Management | View / Search Merchant");
        extentLoggerPass("TDB_MA_017", "TDB_MA_017, SIL Admin App - SIL | Purple Admin App UI | Merchant Management | View / Search Merchant");
    }
    /**
     * Method to Verify Partner Management | Create Partner
     * @throws Exception
     */
    public void verifyPartnerManagementCreatePartner_TDB_MA_018(String userName,String password,String sssUsername,String sssPassword) throws Exception {
        HeaderChildNode("TDB_MA_018, SIL Admin App - Verify Partner Management | Create Partner");
        String partnerName = "QA Automation"+generateRandomString(1);
        String partnerRemarks = "QA Remarks"+generateRandomString(1);
        adminAppLogin(userName,password,sssUsername,sssPassword);
        mouseHover(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objSidebar));
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPartnerTab),"Partner tab");
        waitTime(5000);
        containsValidation(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPromotersPage)),"Partners","Partners Header");
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPartnerAddButton),"Partner add button");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objCreatePartner),"Create Partner");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objCreatePartnerName),"Create Name field");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objCreatePartnerRemarks),"Remarks field");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantActiveToggle),"Active toggle button");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objAddPartnerButton),"Add Partner button");
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objAddPartnerButton),"Add partner button");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objCreatePartnerMissingName),"Name required error message");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objCreatePartnerMissingRemarks),"Remarks required error message");
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantActiveToggle),"Status toggle button");
        containsValidation(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditStatus)),"Active","by default in Active and status will be not editable");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objCreatePartnerName), generateRandomString(10), "Partner name field");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objCreatePartnerRemarks), generateRandomString(10), "Remarks field");
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditBack),"edit back button");
        containsValidation(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPromotersPage)),"Partners","redirected back to Partner list screen");
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPartnerAddButton),"Partner add button");
        if(getAttributValue("value",SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objCreatePartnerName)).isEmpty() && getAttributValue("value",SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objCreatePartnerRemarks)).isEmpty()){
            extentLoggerPass("","create field and remarks field are reset");
        }else{
            extentLoggerFail("","fields are not been reset");
        }
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditBack),"edit back button");
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPartnerAddButton),"Partner add button");
        String getPartnerName = partnerName;
        String getPartnerRemarks = partnerRemarks;
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objCreatePartnerName),getPartnerName , "Partner name field");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objCreatePartnerRemarks), getPartnerRemarks, "Remarks field");
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objAddPartnerButton),"add partner button");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPartnerAddedMessage),"Partner added message pop up");
        waitTime(2000);
        searchPartner(getPartnerName,propertyFileReader("partnerNameFilterByValue","SILAdminApp"));
        waitTime(2000);
        verifyElementPresent(SILAdminAppPage.objValidateColumnPartnerName(getPartnerName),"add partner is successfully created");
        logger.info("TDB_MA_018, SIL Admin App - Verify Partner Management | Create Partner");
        extentLoggerPass("TDB_MA_018", "TDB_MA_018, Verify Partner Management | Create Partner");
    }
    /**
     * Method to Verify  Partner Management | Edit Partner
     * @throws Exception
     */
    public void verifyPartnerManagementEditPartner_TDB_MA_019(String userName,String password,String sssUsername,String sssPassword) throws Exception {
        HeaderChildNode("TDB_MA_019, SIL Admin App - Verify  Partner Management | Edit Partner");
        adminAppLogin(userName,password,sssUsername,sssPassword);
        mouseHover(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objSidebar));
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPartnerTab),"Partner tab");
        waitTime(2000);
        containsValidation(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPromotersPage)),"Partners","Partners Header");
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditButton),"edit button");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objCreatePartnerName),generateRandomString(1), "Partner name field");
        String getPartnerName = getAttributValue("value",SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objCreatePartnerName));
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objCreatePartnerRemarks), generateRandomString(1), "Remarks field");
        String getRemarks = getAttributValue("value",SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objCreatePartnerRemarks));
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPartnerEditSaveButton),"save changes button");
        waitTime(4000);
        searchPartner(getPartnerName,propertyFileReader("partnerNameFilterByValue","SILAdminApp"));
        waitTime(4000);
        containsValidation( getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPartnerNameColumn)),getPartnerName,"the changes on Partner name Successfully saved");
        containsValidation( getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPartnerRemarksColumn)),getRemarks,"the changes on Partner name Successfully saved");
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditButton),"edit button");
        String getPartnerNameValue = getAttributValue("value",SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objCreatePartnerName));
        String getRemarksNameValue = getAttributValue("value",SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objCreatePartnerRemarks));
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditBack),"edit back button");
        waitTime(4000);
        searchPartner(getPartnerNameValue,propertyFileReader("partnerNameFilterByValue","SILAdminApp"));
        waitTime(4000);
        containsValidation( getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPartnerNameColumn)),getPartnerNameValue,"no changes on partner name after clicking back button");
        containsValidation( getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPartnerRemarksColumn)),getRemarksNameValue,"no changes on remarks after clicking back button");
        logger.info("TDB_MA_019, SIL Admin App - Verify  Partner Management | Edit Partner");
        extentLoggerPass("TDB_MA_019", "TDB_MA_019, Verify  Partner Management | Edit Partner");
    }
    /**
     * Method to Verify Partners List | Bulk Extract Report
     * @throws Exception
     */
    public void verifyPartnersListBulkExtractReport_TDB_MA_020(String userName,String password,String sssUsername,String sssPassword) throws Exception {
        String currentUsersHomeDir = System.getProperty("user.home");
        HeaderChildNode("TDB_MA_020, SIL Admin App - Verify Partners List | Bulk Extract Report");
        adminAppLogin(userName,password,sssUsername,sssPassword);
        mouseHover(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objSidebar));
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPartnerTab),"Partner tab");
        waitTime(2000);
        containsValidation(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPromotersPage)),"Partners","Partners Header");
        waitTime(4000);
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objDownloadAsCSVBtn),"download button");
        containsValidation(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objDownloadSuccessMsg)),"Download Successfull!","Flash message after download");
        File latestFile = getLatestFileFromDir(currentUsersHomeDir+"/Downloads/");
        if (latestFile != null && latestFile.getName().endsWith(".csv")) {
            containsValidation(latestFile.getName(),propertyFileReader("PartnersDownloadFileName","SILAdminApp"),"Downloaded file name");
            logger.info("The latest downloaded file is in CSV format: " + latestFile.getName());
            extentLoggerPass("","The latest downloaded file is in CSV format: " + latestFile.getName());
        } else {
            logger.error("The latest downloaded file is not in CSV format or no file found.");
            extentLoggerFail("","The latest downloaded file is not in CSV format or no file found.");
        }
        compareStatus(String.valueOf(latestFile),3);
        try (CSVReader csvReader = new CSVReader(new FileReader(latestFile))) {
            String[] firstRow = csvReader.readNext();
            if (firstRow != null) {
                for (int i = 0; i < firstRow.length; i++) {
                    containsValidation(firstRow[i], propertyFileReader("PartnerRowColumn" + i, "SILAdminApp"), "The .CSV file is correctly formatted and contains all expected data fields");
                }
            } else {
                System.out.println("The CSV file is empty.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("TDB_MA_020, SIL Admin App - Verify Partners List | Bulk Extract Report");
        extentLoggerPass("TDB_MA_020", "TDB_MA_020, SIL Admin App - Verify Partners List | Bulk Extract Report");
    }
    /**
     * Method to Verify  Merchant Management | Create Merchant
     * @throws Exception
     */
    public void verifyMerchantManagementCreateMerchant_TDB_MA_021(String userName,String password,String sssUsername,String sssPassword) throws Exception {
        HeaderChildNode("TDB_MA_021, SIL Admin App - Verify  Merchant Management | Create Merchant");
        adminAppLogin(userName, password, sssUsername, sssPassword);
        navigateToMerchantList();
        containsValidation(getText(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objMerchantListHeader)), "Merchants", "Merchant header");
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantCreateButton),"Add Merchant button");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objCreateMerchantHeader),"Create Merchant header");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantUploadLogo),"Upload logo button");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantNameField),"Merchant name field");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantCategoryField),"Merchant Category field");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantSubCategoryField),"Merchant Sub Category");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEmailField),"Merchant email field");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantMobileField),"Merchant mobile field");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantAddressField),"Merchant Address field");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantMerLatLongField),"Merchant Lat. Lon. field");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantRemarksField),"Merchant Remarks field");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantCorpNameField),"Merchant Corporation field");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantAccntNoField),"Merchant Account number field");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantPartnerField),"Merchant Partner field");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantBankNameField),"Merchant Bank Name field");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantBankBranchField),"Merchant Bank Branch field");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantProductDropdown),"Merchant Partner dropdown");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objAddMerchantButton),"add Merchant button");
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objAddMerchantButton),"Add merchant Button");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantNameInlineError),"Merchant Name inline error");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantCategoryInlineError),"Merchant Category inline error");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantSubcategoryInlineError),"Merchant Subcategory inline error");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEmailInlineError),"Merchant Email inline error");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantAddressInlineError),"Merchant Address inline error");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantLatLonInlineError),"Merchant Lat lon inline error");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantRemarksInlineError),"Merchant Remarks inline error");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantCorporateInlineError),"Merchant Corporate inline error");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantAccntNoInlineError),"Merchant Account number inline error");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantPartnerNameInlineError),"Merchant Partner Name inline error");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantBankNameInlineError),"Merchant Bank Name inline error");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantBankBranchInlineError),"Merchant Bank Branch inline error");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantProductInlineError),"Merchant Product inline error");
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantActiveToggle),"Status toggle button");
        containsValidation(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditStatus)),"Active","by default in Active and status will be not editable");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantNameField),generateRandomString(5),"Merchant Name field");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantCategoryField),generateRandomString(5),"Merchant Category field");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantSubCategoryField),generateRandomString(5),"Merchant Subcategory field");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEmailField),generateRandomString(5),"Merchant Email field");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantMobileField),"123456789012345","Merchant Mobile field");
        if(getAttributValue("value",SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantMobileField)).length() <= 12){
            extentLoggerPass("","User will not able to add more than 12 digits ");
        }else {
            extentLoggerFail("","user able to add more than 12 digits");
        }
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantAddressField),generateRandomString(5),"Merchant Address field");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantMerLatLongField),generateRandomString(5),"Merchant Lat., Lon. field");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantRemarksField),generateRandomString(5),"Merchant Remarks field");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantCorpNameField),generateRandomString(5),"Merchant Corporation field");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantAccntNoField),generateRandomString(5),"Merchant Account Number field");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantBankNameField),generateRandomString(5),"Merchant Bank Name field");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantBankBranchField),generateRandomString(5),"Merchant Bank Branch field");
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditBack),"edit back button");
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantCreateButton),"Add Merchant button");
        containsValidation(getAttributValue("value",SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantNameField)),"","Merchant name is reset");
        containsValidation(getAttributValue("value",SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantAddressField)),"","Merchant Address is reset");
        containsValidation(getAttributValue("value",SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantRemarksField)),"","Merchant Remarks is reset");
        containsValidation(getAttributValue("value",SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantBankBranchField)),"","Merchant Bank Branch is reset");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantNameField),"Automation"+generateRandomString(1),"Merchant Name field");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantCategoryField),"Automation"+generateRandomString(1),"Merchant category field");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantSubCategoryField),"Automation"+generateRandomString(1),"Merchant Subcategory field");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEmailField),generateRandomEmail(),"Merchant Email field");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantMobileField),generateRandomMobileNumber(),"Merchant mobile number field");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantAddressField),"Automation address"+generateRandomString(1),"Merchant address field");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantMerLatLongField),"0.0,0.0","Merchant Lot. Lon. field");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantRemarksField),"Automation Remarks"+generateRandomString(1),"Merchant Remarks field");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantCorpNameField),"Automation Corporation"+generateRandomString(1),"Merchant Corporation field");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantAccntNoField),generateRandomMobileNumber(),"Merchant Account number field");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantBankNameField),"Automation Bank"+generateRandomString(1),"Merchant Account Bank field");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantBankBranchField),"Automation Branch"+generateRandomString(1),"Merchant Account Branch field");
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantPartnerField),"Partner field");
        type(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantPartnerField),"qa","Merchant Account Branch field");
        waitTime(7000);
        Actions actions = new Actions(getWebDriver());
        actions.sendKeys(Keys.ENTER).perform();
        actions.sendKeys(Keys.ESCAPE).perform();
        waitTime(5000);
        selectByValue(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantProductDropdown),"1");
        String getMerchantName = getAttributValue("value",SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantNameField));
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objAddMerchantButton),"Add merchant");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantAdded),"Merchant Added message pop up");
        waitTime(2000);
        searchMerchant(getMerchantName,propertyFileReader("merchantNameFilterByValue","SILAdminApp"));
        waitTime(5000);
        containsValidation(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantNameColumnValue)),getMerchantName,"verify the added merchant");
        if(getText(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objMerchantId)).isEmpty()){
            extentLoggerFail("","Merchant ID is empty");
        }else{
            extentLoggerPass("","Able to see the Merchant Id for the Merchant created newly");
        }
        logger.info("TDB_MA_021, SIL Admin App - Verify  Merchant Management | Create Merchant");
        extentLoggerPass("TDB_MA_021", "TDB_MA_021, SIL Admin App - Verify  Merchant Management | Create Merchant");
    }
    /**
     * Method to Verify Edit Partner – Enable / Disable Partner
     * @throws Exception
     */
    public void verifyEditPartnerEnableDisablePartner_TDB_MA_022(String userName,String password,String sssUsername,String sssPassword) throws Exception {
        HeaderChildNode("TDB_MA_021, SIL Admin App - Verify Edit Partner – Enable / Disable Partner");
        adminAppLogin(userName, password, sssUsername, sssPassword);
        mouseHover(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objSidebar));
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPartnerTab),"Partner tab");
        waitTime(2000);
        containsValidation(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPromotersPage)),"Partners","Partners Header");
        waitTime(4000);
        searchPartner(propertyFileReader("partnerName","SILAdminApp"),propertyFileReader("partnerNameFilterByValue","SILAdminApp"));
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditButton),"Edit button");
        verifyElementPresent(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objEditPartnerHeader),"Edit Partner header");
        if(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditStatus)).equals("Active")) {
            jsClick(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objToggleButton), "Status toggle button");
            containsValidation(getText(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objMerchantEditStatus)), "Disabled", "Disabled after clicking toggle button");
        }else{
            jsClick(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objToggleButton), "Status toggle button");
            containsValidation(getText(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objMerchantEditStatus)), "Active", "enabled after clicking toggle button");
        }
        String getThePartnerName = getAttributValue("value",SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objCreatePartnerName));
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditBack),"edit back button");
        searchPartner(getThePartnerName,propertyFileReader("partnerNameFilterByValue","SILAdminApp"));
        waitTime(5000);
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditButton),"Edit button");
        String getTheStatus = getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditStatus));
        if(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objMerchantEditStatus)).equals("Active")) {
            jsClick(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objToggleButton), "Status toggle button");
            containsValidation(getText(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objMerchantEditStatus)), "Disabled", "Disabled after clicking toggle button");
        }else{
            jsClick(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objToggleButton), "Status toggle button");
            containsValidation(getText(SILAdminAppPage.getByOSType(platform, SILAdminAppPage.objMerchantEditStatus)), "Active", "enabled after clicking toggle button");
        }
        jsClick(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPartnerEditSaveButton),"save button");
        waitTime(4000);
        searchPartner(getThePartnerName,propertyFileReader("partnerNameFilterByValue","SILAdminApp"));
        waitTime(4000);
        if(getTheStatus.contains(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPartnerStatusColumn)))){
            extentLoggerFail("","Status didn't save");
        }else{
            System.out.println(getText(SILAdminAppPage.getByOSType(platform,SILAdminAppPage.objPartnerStatusColumn)));
            extentLoggerPass("","status successfully saved");
        }
        logger.info("TDB_MA_022, SIL Admin App - Verify Edit Partner – Enable / Disable Partner");
        extentLoggerPass("TDB_MA_022", "TDB_MA_022, SIL Admin App - Verify Edit Partner – Enable / Disable Partner");
    }
}