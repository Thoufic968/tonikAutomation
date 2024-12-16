package com.tonik.pageObject;

import org.openqa.selenium.By;

public enum SILAdminAppPage {
    objPurpleAdminApp("//span[contains(text(),'Purple Admin App')]","xpath"),
    objUserNameInputField("email","name"),
    objPasswordInputField ("password","name"),
    objSSSUserNameInputField("//input[@type='email']","xpath"),
    objSSSPasswordInputField ("passwd","name"),
    objCreatePromoterBtn("//span[contains(text(),'Promoter')]","xpath"),
    objAddPromoterIcon("//*[@class='icon me-2 text-light']","xpath"),
    objLastName("lastName","name"),
    objCreateNewPromoter("//*[contains(text(),'Create New Promoter')]","xpath"),
    objFirstName("firstName","name"),
    objNextBtn("//*[@type='submit']","xpath"),
    objPromoterSearch("//input[@placeholder='Search']","xpath"),
    objFirstElementEditIcon("(//tr/td)[1]","xpath"),
    objFirstActivePromoter("(//span[contains(text(),'Active')])[1]/parent::td/parent::tr/child::td[@class='edit-promo']","xpath"),
    objMiddleName("middleName","name"),
    objMobileNumber("mobile","name"),
    objAddress("address","name"),
    objZipCode("zipcode","name"),
    objProvince("province","name"),
    objCity("city","name"),
    objBarangay("barangay","name"),
    objArea("area","name"),
    objLocation("location","name"),
    objDOBField("//div[@class='react-datepicker-wrapper  form-control']","xpath"),
    objStoreNameInputField("react-select-3-input","id"),
    objStoreID("//*[contains(text(),'Store ID')]","xpath"),
    objYearPicker("(//*[@class='p-1'])[1]","xpath"),
    objRequiredErrorMsg("//*[contains(text(),'Required')]","xpath"),
    objDatePicker("//div[@class='react-datepicker__day react-datepicker__day--001']","xpath"),
    objStoreName("//*[@id='react-select-3-option-0']","xpath"),
    objMonthPicker("(//*[@class='p-1'])[2]","xpath"),
    objLoginBtn("//button[@type='button']","xpath"),
    objEditPromoterPage("//h4[contains(text(),'Edit Promoter')]","xpath"),
    objToggleButton("//*[@type='checkbox']","xpath"),
    objDisabledStatus("(//span[contains(text(),'Disabled')])[1]","xpath"),
    objSearchType("//select[@class='form-select']","xpath"),
    objDisabledPromoterId("(//span[contains(text(),'Disabled')])[1]/parent::td/parent::tr/child::td[@class='cell-id']/child::*","xpath"),
    objPromoterStatus("//*[@type='checkbox']/parent::*/preceding-sibling::span","xpath"),
    objResetPassword("//*[contains(text(),' Reset password')]","xpath"),
    objResetPasswordPopup("//*[contains(text(),'Password Reset')]","xpath"),
    objResetPasswordDescription("//*[contains(text(),'Do you want to reset password?')]","xpath"),
    objCancelBtn("//*[contains(text(),'Cancel')]","xpath"),
    objResetBtn("//button[contains(text(),'Cancel')]/following-sibling::button","xpath"),
    objSuccessfulPasswordResetPopup("//*[contains(text(),'Password reset successful')]","xpath"),
    objSuccessfulPasswordResetPopupDescription("//*[contains(text(),'password was reset')]","xpath"),
    objDownloadAsCSVBtn("(//*[@class='promos-bulk'])[1]","xpath"),
    objUploadCSVBtn("(//*[@class='promos-bulk'])[2]","xpath"),
    objUploadCSVFilePopup("//h5[@class='modal-title']","xpath"),
    objDownloadSuccessMsg("//*[contains(text(),'Download Successfull!')]","xpath"),
    objUploadOptions("//*[@class='btn btn-outline-primary undefined']","xpath"),
    objPromotersPage("//*[@class='card-header']","xpath"),
    objPromotersIDDisplayed("//*[@class='cell-id']/span","xpath"),
    objPromoterFirstNameDisplayed("//td[@class='cell-firstName']/span","xpath"),
    objPromoterLastNameDisplayed("//*[@class='cell-lastName']/child::span","xpath"),
    objPromoterMiddleNameDisplayed("//*[@class='cell-middleName']/child::span","xpath"),
    objPromoterMobileNumberDisplayed("//*[@class='cell-mobile']/child::span","xpath"),
    objPromoterAddressDisplayed("//*[@class='cell-address']/child::span","xpath"),
    objPromoterZipcodeDisplayed("//*[@class='cell-zipcode']/child::span","xpath"),
    objPromoterProvinceDisplayed("//*[@class='cell-province']/child::span","xpath"),
    objPromoterCityDisplayed("//*[@class='cell-city']/child::span","xpath"),
    objPromoterBarangayDisplayed("//*[@class='cell-barangay']/child::span","xpath"),
    objPromoterAreaDisplayed("//*[@class='cell-area']/child::span","xpath"),
    objPromoterLocationDisplayed("//*[@class='cell-location']/child::span","xpath"),
    objPromoterDOBDisplayed("//*[@class='cell-dob']/child::span","xpath"),
    objPromoterCreatedByDisplayed("//*[@class='cell-createdBy']/child::span","xpath"),
    objPromoterCreatedOnDisplayed("//*[@class='cell-createdOn']/child::span","xpath"),
    objCreatedBySort("//*[@class='react-table__column-header sortable']","xpath"),
    objPromoterStatusDisplayed("//*[@class='cell-status']/child::span","xpath"),
    objActiveScreenNumber("//*[@class=\"pagination__item page-numbers page-item active\"]/button","xpath"),
    objRightArrowBtn("(//button[@class=\"pagination__link pagination__link--arrow page-link\"])[2]","xpath"),
    objProfileIcon("//*[@class='sidebar-brand']","xpath"),
    objDropdownFields("//select[@class=\"form-select\"]/option","xpath"),
    objLogoutBtn("//*[@class='sidebar-toggler']/parent::*","xpath"),
    objProfileName("//*[contains(text(),'Hello')]","xpath"),
    objPromoters("//*[contains(text(),'Promoters')]","xpath"),
    objMerchantsMenu("(//*[contains(text(),'Merchants')])[1]","xpath"),
    objPartnersMenu("//*[contains(text(),'Partners')]","xpath"),
    objLogout("(//*[contains(text(),'Logout')])[1]","xpath"),
    objCreatePromoterBackBtn("//*[@class='icon icon-sm']","xpath"),
    objNoResultsFoundMsg("//*[contains(text(),'No Results found')]","xpath"),
    objSelectedStoreName("//*[contains(text(),'Store Name')]/preceding-sibling::*/child::*[@class=\" css-13cymwt-control\"]/child::*/child::*/child::*/child::*[@class=' css-9jq23d']","xpath"),
    objAutoPopulatedStoreID("//*[contains(text(),'Store ID')]/preceding-sibling::*/child::*[@class=' css-16xfy0z-control']/child::*/child::*/child::*/child::div[@class=' css-9jq23d']","xpath"),
    objOKBtn("//*[contains(text(),'OK')]","xpath"),
    objFieldsDisplayed("(//tr[@role=\"row\"])[1]/child::*[@role='columnheader']","xpath"),
    objPromoterAddedMsg("//*[contains(text(),'Prompter added')]","xpath"),

    //Merchant-List
    objSidebar("//div[@class='simplebar-content']","xpath"),
    objMerchantToggle("//a[@class='nav-link nav-group-toggle' and contains(text(),'Merchants')]","xpath"),
    objMerchantListTab("//a[@href='/merchants/list']","xpath"),
    objMerchantListHeader("//div[@class='card-header' and contains(text(),'Merchants')]","xpath"),
    objMerchantListDownloadButton("//div[@class='promos-bulk'][1]//child::*[local-name() = 'svg']","xpath"),
    objMerchantListUploadButton("//div[@class='promos-bulk'][2]//child::*[local-name() = 'svg']","xpath"),
    objMerchantEditButton("//td[@class='edit-promo']","xpath"),
    objMerchantEditHeader("//div[@class='inner d-flex align-items-center gap-3']//child::h4","xpath"),
    objMerchantBreadCrumb("//nav[@ aria-label='breadcrumb']","xpath"),
    objMerchantActiveToggle("//label[contains(text(),'Status')]//following::input[@type='checkbox']","xpath"),
    objMerchantEditBack("//a[@class='go-back']","xpath"),
    objMerchantEditStatus("//label[contains(text(),'Status')]//following::span","xpath"),
    objMerchantEditSaveButton("//button[@type='submit' and contains(text(),'Save changes')]","xpath"),
    objMerchantListStatus("//td[@class='cell-merchantRefCode']//following::td[@class='cell-flexiField1']//child::span[contains(text(),'')]","xpath"),
    objMerchantPurpleStoreDropdown("//h2[contains(text(),'Merchant Store')]//following::select","xpath"),
    objMerchantUploadAddLabel("//label[@for='add']","xpath"),
    objMerchantUploadModifyLabel("//label[@for='modify']","xpath"),
    objMerchantUploadEnableLabel("//label[@for='enable']","xpath"),
    objMerchantUploadDisableLabel("//label[@for='disable']","xpath"),
    objMerchantUploadModalClose("//h5[contains(text(),'Upload .CSV file')]//following-sibling::button[@aria-label='Close']","xpath"),
    objMerchantUploadDropFile("//*[@class='dropzone csvuploaddropzone']/input","xpath"),
    objMerchantUploadFileSize("//div[@class='statusDetail']/span[@class='fileSize']","xpath"),
    objMerchantUploadFileName("//span[@class='fileName']","xpath"),
    objMerchantBulkReportTab("//a[@href='/merchants/upload-status']","xpath"),
    objMerchantBulkReportHeader("//div[@class='card-header'][contains(text(),'merchant')]","xpath"),
    objMerchantUploadErrorMessage("//div[starts-with(@class,'invalid')]","xpath"),
    objMerchantUploadCSVButton("//button[contains(text(),'Upload .CSV')]","xpath"),
    objPromoterListTab("//a[@href='/promoters/list']","xpath"),
    objPromotersSubTab("//a[@class='nav-link nav-group-toggle' and contains(text(),'Promoters')]","xpath"),
    objPromoterStatusRow("//td[@class='cell-status']/span","xpath"),
    objMerchantCreatedDate("//tbody//child::td[@class='cell-createdOn']","xpath"),
    objMerchantNameColumnValue("//td[@class='cell-merchantName']/span","xpath"),
    objMerchantCreateButton("//button[@class='btn btn-primary addPromo']/span[contains(text(),'Merchant')]","xpath"),
    objMerchantUploadLogo("//label[@class='btn-upload btn' and contains(text(),'Upload logo')]","xpath"),
    objMerchantNameField("//input[@name='merchantName']","xpath"),
    objMerchantCategoryField("//input[@name='merchantCategory']","xpath"),
    objMerchantSubCategoryField("//input[@name='merchantSubCategory']","xpath"),
    objMerchantEmailField("//input[@name='merchantEmail']","xpath"),
    objMerchantMobileField("//input[@name='merchantMobile']","xpath"),
    objMerchantAddressField("//input[@name='merchantAddress']","xpath"),
    objMerchantMerLatLongField("//input[@name='merlatlong']","xpath"),
    objMerchantRemarksField("//input[@name='remarks']","xpath"),
    objMerchantCorpNameField("//input[@name='merchantCorporateName']","xpath"),
    objMerchantAccntNoField("//input[@name='merchantAccountNo']","xpath"),
    objMerchantPartnerField("//div[@class=' css-19bb58m']/input","xpath"),
    objMerchantBankNameField("//input[@name='merchantBankName']","xpath"),
    objMerchantBankBranchField("//input[@name='merchantBankBranch']","xpath"),
    objMerchantProductDropdown("//select[@name='storeType']","xpath"),
    objAddMerchantButton("//button[contains(text(),'Add Merchant')]","xpath"),
    objCreateMerchantHeader("//h4[contains(text(),'Create Merchant')]","xpath"),
    objMerchantNameInlineError("//label[contains(text(),'Merchant Name')]/following-sibling::div[@class='invalid-feedback' and contains(text(),'Required')]","xpath"),
    objMerchantCategoryInlineError("//label[contains(text(),'Merchant Category')]/following-sibling::div[@class='invalid-feedback' and contains(text(),'Required')]","xpath"),
    objMerchantSubcategoryInlineError("//label[contains(text(),'Merchant Subcategory')]/following-sibling::div[@class='invalid-feedback' and contains(text(),'Required')]","xpath"),
    objMerchantEmailInlineError("//label[contains(text(),'Email')]/following-sibling::div[@class='invalid-feedback' and contains(text(),'Required')]","xpath"),
    objMerchantAddressInlineError("//label[contains(text(),'Mobile')]/following-sibling::div[@class='invalid-feedback' and contains(text(),'Required')]","xpath"),
    objMerchantLatLonInlineError("//label[contains(text(),'Lat.,Lon')]/following-sibling::div[@class='invalid-feedback' and contains(text(),'Required')]","xpath"),
    objMerchantRemarksInlineError("//label[contains(text(),'Remarks')]/following-sibling::div[@class='invalid-feedback' and contains(text(),'Required')]","xpath"),
    objMerchantCorporateInlineError("//label[contains(text(),'Corporate Name')]/following-sibling::div[@class='invalid-feedback' and contains(text(),'Required')]","xpath"),
    objMerchantAccntNoInlineError("//label[contains(text(),'Account Number')]/following-sibling::div[@class='invalid-feedback' and contains(text(),'Required')]","xpath"),
    objMerchantPartnerNameInlineError("//label[contains(text(),'Partner Name')]/following-sibling::div[@class='invalid-feedback' and contains(text(),'Required')]","xpath"),
    objMerchantBankNameInlineError("//label[contains(text(),'Bank Name')]/following-sibling::div[@class='invalid-feedback' and contains(text(),'Required')]","xpath"),
    objMerchantBankBranchInlineError("//label[contains(text(),'Bank Branch')]/following-sibling::div[@class='invalid-feedback' and contains(text(),'Required')]","xpath"),
    objMerchantProductInlineError("//label[contains(text(),'Product')]/following-sibling::div[@class='invalid-feedback' and contains(text(),'Required')]","xpath"),
    objMerchantAdded("//*[contains(text(),'Merchant added')]","xpath"),
    objMerchantId("//td[@class='cell-merchantRefCode']/span","xpath"),
    objPaginationNext("//button[@class='pagination__link pagination__link--arrow page-link'][2]","xpath"),










    //Partner
    objPartnerTab("//a[@href='/partners/list']","xpath"),
    objPartnerIDSortBy("//th[@title='Toggle SortBy' and contains(text(),'Partner Id')]","xpath"),
    objPartnerIDColumn("//tbody[@class='table table--bordered']//child::td[@class='cell-partnerId']","xpath"),
    objPartnerNameSortBy("//th[@title='Toggle SortBy' and contains(text(),'Partner Name')]","xpath"),
    objPartnerNameColumn("//tbody[@class='table table--bordered']//child::td[@class='cell-partnerName']","xpath"),
    objPartnerCreatedDateSortBy("//th[@title='Toggle SortBy' and contains(text(),'Created Date')]","xpath"),
    objPartnerCreatedDateColumn("//tbody[@class='table table--bordered']//child::td[@class='cell-createdDateAndTime']","xpath"),
    objPartnerPaginationDropdown("//span[contains(text(),'Rows per page:')]/following-sibling::select/option","xpath"),
    objPartnerColumnHeader("//thead/tr/th[@title='Toggle SortBy']","xpath"),
    objPartnerNoSearchFound("//td/p[contains(text(),'No Results found')]","xpath"),
    objPartnerAddButton("//button[@class='btn btn-primary addPromo']/span[contains(text(),'Partner')]","xpath"),
    objCreatePartner("//h4[contains(text(),'Create Partner')]","xpath"),
    objCreatePartnerName("//input[@name='partnerName']","xpath"),
    objCreatePartnerRemarks("//input[@name='remarks']","xpath"),
    objAddPartnerButton("//button[@type='submit' and contains(text(),'Add Partner')]","xpath"),
    objCreatePartnerMissingName("//label[contains(text(),'Name')]//following-sibling::div[contains(text(),'Required')]","xpath"),
    objCreatePartnerMissingRemarks("//label[contains(text(),'Remarks')]//following-sibling::div[contains(text(),'Required')]","xpath"),
    objPartnerAddedMessage("//div[contains(text(),'Partner added')]","xpath"),
    objPartnerRemarksColumn("//tbody[@class='table table--bordered']//child::td[@class='cell-remarks']","xpath"),
    objPartnerEditSaveButton("//button[contains(text(),'Save changes')]","xpath"),
    objEditPartnerHeader("//h4[contains(text(),'Edit Partner')]","xpath"),
    objPartnerStatusColumn("//tbody[@class='table table--bordered']//child::td[@class='cell-partnerStatus']","xpath");






    private String web;
    private String webPathType;
    SILAdminAppPage(String web, String webPathType) {
        this.web = web;
        this.webPathType = webPathType;
    }
    public static By getByOSType(String osType, SILAdminAppPage objectName) {
        if (osType.equalsIgnoreCase("web") && objectName.webPathType.equalsIgnoreCase("xpath"))
        {
            return By.xpath(objectName.web);
        }
        else if (osType.equalsIgnoreCase("web") && objectName.webPathType.equalsIgnoreCase("id"))
        {
            return By.id(objectName.web);
        }
        else if (osType.equalsIgnoreCase("web") && objectName.webPathType.equalsIgnoreCase("name"))
        {
            return By.name(objectName.web);
        }
        throw new IllegalArgumentException("Object not found: " + objectName);
    }
    public static By objUploadOptions(int option) {
        return By.xpath("(//*[@class='btn btn-outline-primary undefined'])[" + option + "]");
    }
    public static By objSelectedStoreNames(int storeName){
        return By.xpath("(//*[contains(text(),'Store Name')]/preceding-sibling::*/child::*[@class=\" css-13cymwt-control\"]/child::*/child::*/child::*/child::*[@class=' css-9jq23d'])["+storeName+"]");
    }
    public static By objAutoPopulatedStoreID(int storeId){
        return By.xpath("(//*[contains(text(),'Store ID')]/preceding-sibling::*/child::*[@class=' css-16xfy0z-control']/child::*/child::*/child::*/child::div[@class=' css-9jq23d'])["+storeId+"]");
    }
    public static By objSelectedStoreName(String text){
        return By.xpath("//*[contains(text(),'"+text+"')]");
    }
    public static By objPromotersIDDisplayed(int id){
        return By.xpath("(//*[@class='cell-id']/span)["+id+"]");
    }
    public static By objFieldsDisplayed(int field){
        return By.xpath("((//tr[@role=\"row\"])[1]/child::*[@role='columnheader'])["+field+"]");
    }
    public static By objFirstNameDisplayed(int firstName){
        return By.xpath("(//*[@class='cell-firstName']/span)["+firstName+"]");
    }
    public static By objDropDownFields(int item){
        return By.xpath("(//select[@class=\"form-select\"]/option)["+item+"]");
    }
    public static By objMerchantStatus(String status){
        return By.xpath("//td[@class='cell-flexiField1']/span[contains(text(),'"+status+"')]");
    }
    public static By objMerchantUploadedStatusByFileName(String File_name, String Status){
        return By.xpath("//td[contains(text(),'"+File_name+"')]//following-sibling::td/span[contains(text(),'"+Status+"')]");
    }
    public static By objValidateColumnPartnerName(String Partner_name){
        return By.xpath("//td[@class='cell-partnerName']/span[contains(text(),'"+Partner_name+"')]");
    }
    public static By objPageButton(String page_number){
        return By.xpath("//button[@class='pagination__link page-link'][contains(text(),'"+page_number+"')]");
    }
}
