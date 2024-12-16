package com.tonik.pageObject;
import org.openqa.selenium.By;
public enum ProfilePage {
    //Profile Screen
    objProfileIcon("(//android.widget.Button[@resource-id='icon-button'])[1]/android.view.ViewGroup", "(//*[@name='icon-button'])[1]", "xpath", "xpath"),
    objLoginBanner("/hierarchy/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ImageView", "(//*[@name='iconbutton'])[1]", "xpath", "xpath"),
    objProfileHeader("//android.widget.TextView[@resource-id='com.tonik.mobile:id/Main_title_txt']", "com.tonik.mobile:id/Main_title_txt", "xpath", "id"),
    objProfilePicture("//android.view.ViewGroup[@resource-id='com.tonik.mobile:id/Camera_View']/android.view.ViewGroup", "//*[@type='XCUIElementTypeImage']", "xpath", "xpath"),
    objProfilePicture2("//android.view.ViewGroup[@resource-id=\"com.tonik.mobile:id/Stash_Background_View\"]/android.view.ViewGroup", "//*[@type='XCUIElementTypeImage']", "xpath", "xpath"),
    objProfilePicture3("//android.view.ViewGroup[@resource-id='com.tonik.mobile:id/Stash_Camera_Img']/android.view.ViewGroup", "//*[@type='XCUIElementTypeImage']", "xpath", "xpath"),
    objProfilePicture4("//android.widget.ImageView", "//*[@type='XCUIElementTypeImage']", "xpath", "xpath"),
    objProfileName("//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]", "(//*[contains(@value,'Profile')]/following::XCUIElementTypeOther)[8]", "xpath", "xpath"),
    objProfileCameraIcon("//android.widget.ImageView", "//XCUIElementTypeOther[@name=\"com.tonik.mobile:id/Camera_View\"]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther", "xpath", "xpath"),
    objGalleryAlbums("//android.widget.TextView[@resource-id=\"com.sec.android.gallery3d:id/title\" and @text=\"Albums\"]", "(//*[contains(@value,'Profile')]/following::XCUIElementTypeOther)[8]", "xpath", "xpath"),
    objAccountInformation("//*[contains(@text,' Account information')]", "(//*[@name=' Account information'])[1]", "xpath", "xpath"),
    objAccountDetails("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    //Account Details screen
    objBankNameOption("//*[@text='Bank name']", "//*[@name='Bank name']", "xpath", "xpath"),
    objBankName("//*[@text='Bank name']/following-sibling::android.widget.TextView", "(//*[@name='Bank name']/following-sibling::*)[1]", "xpath", "xpath"),
    objAccountNumberOption("//*[@text='Account number']", "//*[@name='Account number']", "xpath", "xpath"),
    objAccountNumber("//*[@text='Account number']/following-sibling::android.widget.TextView", "(//*[@name='Account number']/following-sibling::*)[1]", "xpath", "xpath"),
    objAccountHolderOption("//*[@text='Account holder']", "//*[@name='Account holder']", "xpath", "xpath"),
    objAccountHolder("//*[@text='Account holder']/following-sibling::android.widget.TextView", "(//*[@name='Account holder']/following-sibling::*)[1]", "xpath", "xpath"),
    objBankNameCopyIcon("//*[@text='Bank name']/following-sibling::android.view.ViewGroup", "(//*[@name='Bank name']/following::XCUIElementTypeOther)[2]", "xpath", "xpath"),
    objAccountNumberCopyIcon("//*[@text='Account number']/following-sibling::android.view.ViewGroup", "(//*[@name='Account number']/following::XCUIElementTypeOther)[2]", "xpath", "xpath"),
    objAccountHolderCopyIcon("//*[@text='Account holder']/following-sibling::android.view.ViewGroup", "(//*[@name='Account holder']/following::XCUIElementTypeOther)[2]", "xpath", "xpath"),
    objCopiedToYourClipBoard("//*[contains(@text,'Copied to your clipboard')]", "//XCUIElementTypeStaticText[@name='Copied to your clipboard ']", "xpath", "xpath"),
    objBackIcon("com.tonik.mobile:id/Header_left_click", "com.tonik.mobile:id/Header_left_click", "id", "id"),
    //    Personal Details Screen
    objPersonalDetails ("//*[@text=\" Personal details\"] ","(//*[@name=' Personal details'])[3]", "xpath", "xpath"),
    objPersonalDetailsDisabled ("//*[contains(@text,'Personal details') and @clickable='false'] "," //*[@name=' Personal details' and @enabled='false']", "xpath", "xpath"),
    objPersonalDetailsHeader ("//*[@text='Personal details'] "," //*[@name='Personal details']", "xpath", "xpath"),
    objContactTonicCustomerCare ("//*[contains(@text,'contact')]","//*[@name='Personal details']/following::XCUIElementTypeStaticText[1]", "xpath", "xpath"),
    objFirstNameOption ("//*[@text='First name'] "," (//*[@name='First name'])[2]", "xpath", "xpath"),
    objFirstName ("//*[@text='First name']/following-sibling::*[1] ","((//*[@name='First name'])[2]/following::XCUIElementTypeStaticText)[1]", "xpath", "xpath"),
    objMiddleNameOption ("//*[@text='Middle name'] "," (//*[@name='Middle name'])[2]", "xpath", "xpath"),
    objMiddleName ("//*[@text='Middle name']/following-sibling::*[1] ","((//*[@name='Middle name'])[2]/following::XCUIElementTypeStaticText)[1]", "xpath", "xpath"),
    objLastNameOption ("//*[@text='Last name'] "," (//*[@name='Last name'])[2]", "xpath", "xpath"),
    objLastName ("//*[@text='Last name']/following-sibling::*[1] "," ((//*[@name='Last name'])[2]/following::XCUIElementTypeStaticText)[1]", "xpath", "xpath"),
    objGenderOption ("//*[@text='Gender'] "," (//*[@name='Gender'])[2]", "xpath", "xpath"),
    objGender ("//*[@text='Gender']/following-sibling::*[1] "," ((//*[@name='Gender'])[2]/following::XCUIElementTypeStaticText)[1]", "xpath", "xpath"),
    objDateOfBirthOption ("//*[@text='Date of birth'] "," (//*[@name='Date of birth'])[2]", "xpath", "xpath"),
    objDateOfBirth ("//*[@text='Date of birth']/following-sibling::*[1] "," ((//*[@name='Date of birth'])[2]/following::XCUIElementTypeStaticText)[1]", "xpath", "xpath"),
    objPlaceOfBirthOption ("//*[@text='Place of birth'] "," (//*[@name='Place of birth'])[2]", "xpath", "xpath"),
    objPlaceOfBirth ("//*[@text='Place of birth']/following-sibling::*[1] "," ((//*[@name='Place of birth'])[2]/following::XCUIElementTypeStaticText)[1]", "xpath", "xpath"),
    objNationalityOption ("//*[@text='Nationality'] "," (//*[@name='Nationality'])[2]", "xpath", "xpath"),
    objNationality ("//*[@text='Nationality']/following-sibling::*[1] "," ((//*[@name='Nationality'])[2]/following::XCUIElementTypeStaticText)[1]", "xpath", "xpath"),
    objRegisterIDOption ("//*[@text='Registered ID'] ","(//*[@name='Registered ID'])[2]", "xpath", "xpath"),
    objRegisterID ("//*[@text='Registered ID']/following-sibling::*[1] "," ((//*[@name='Registered ID'])[2]/following::XCUIElementTypeStaticText)[1]", "xpath", "xpath"),
    objIDNumberOption ("//*[@text='ID number'] ","(//*[@name='ID number'])[2]", "xpath", "xpath"),
    objIDNumber ("//*[@text='ID number']/following-sibling::*[1] "," ((//*[@name='ID number'])[2]/following::XCUIElementTypeStaticText)[1]", "xpath", "xpath"),
    objIDExpiryDateOption ("//*[@text='ID expiry date'] "," (//*[@name='ID expiry date'])[2]", "xpath", "xpath"),
    objIDExpiryDate ("//*[@text='ID expiry date']/following-sibling::*[1] "," ((//*[@name='ID expiry date'])[2]/following::XCUIElementTypeStaticText)[1]", "xpath", "xpath"),
    //    Contact information Screen
    objContactInformation ("//*[contains(@text,'Contact information')]"," (//*[@name=' Contact information'])[3]", "xpath", "xpath"),
    objContactInformationDisabled ("//*[contains(@text,'Contact information')and @clickable='false'] "," //*[@name=' Contact information' and @enabled='false']", "xpath", "xpath"),
    objContactInfoHeader ("//*[@resource-id='com.tonik.mobile:id/Main_title_txt']"," //*[@name='com.tonik.mobile:id/Main_title_txt']", "xpath", "xpath"),
    objContactInfoText ("//android.widget.TextView[@text=\"Contact info\"]"," //*[@name='com.tonik.mobile:id/Main_title_txt']", "xpath", "xpath"),
    objMailingAddress ("//*[contains(@text,'Mailing address')] "," //*[@label='Mailing address']", "xpath", "xpath"),
    objProvideYourCommunicationAddress ("//*[contains(@text,'Provide your communication address for all the communications')] "," (//*[@name='Provide your communication address for all the communications'])", "xpath", "xpath"),
    objUpdatedCommunicationAddress ("//*[contains(@text,'Mailing address')]"," (//*[@label='Mailing address']/following-sibling::*)[2]", "xpath", "xpath"),
    objMobileNumberHeader ("//*[contains(@text,'Mobile number')] "," //*[@label='Mobile number']", "xpath", "xpath"),
    objMobileNumber ("(//*[contains(@resource-id,'Mobile numberclick')]/following-sibling::*)[2]","//*[contains(@value,'+63')]", "xpath", "xpath"),
    objVerifyNow ("//*[contains(@text,'Verify now')] ","(//*[contains(@label,'Verify now')])[17]", "xpath", "xpath"),
    objPopup ("com.tonik.mobile:id/Popup_sub_header_txt", "com.tonik.mobile:id/Popup_sub_header_txt", "id", "id"),
    objUpdateYourEmailButtonOnEmailPopup ("com.tonik.mobile:id/Popup_negative_btn_txt", "com.tonik.mobile:id/Popup_negative_btn_txt", "id", "id"),
    objVerifyNowButtonOnPopup ("com.tonik.mobile:id/Popup_positive_btn_click", "com.tonik.mobile:id/Popup_positive_btn_click", "id", "id"),
    objEmailAddress ("(//*[@text='Email']/parent::*/following-sibling::*)","((//*[contains(@label,'Email')])[17]/../../following-sibling::*)[2]", "xpath", "xpath"),
    objAdditionalInformation ("//*[contains(@text,' Additional information')] "," (//*[@name=' Additional information'])[3]", "xpath", "xpath"),
    objAdditionalInformationDisabled ("//*[contains(@text,'Additional information')and @clickable='false'] ","  //*[@name=' Additional information' and @enabled='false']", "xpath", "xpath"),
    objRequestBankCertificate ("//*[contains(@text,'Request a Bank Certificate')] "," (//*[@name=' Request a Bank Certificate You can request 1 per day'])[3]", "xpath", "xpath"),
    objRequestBankCertificateDisabled ("//*[contains(@text,'Request a Bank Certificate')and @clickable='false'] "," //*[@name=' Request a Bank Certificate You can request 1 per day' and @enabled='false']", "xpath", "xpath"),
    //    Log in Option screen
    objLogInOption ("//*[contains(@text,'Log-in options')]"," (//*[@name=' Log-in options'])[3]", "xpath", "xpath"),
    objLogInOptionPageHeader ("//*[@resource-id='com.tonik.mobile:id/Main_title_txt']", "com.tonik.mobile:id/Main_title_txt", "xpath", "id"),
    objResetPasswordOnLoginOptionPage ("//*[contains(@text,'Reset password')]"," (//*[@name='Reset password'])[2]", "xpath", "xpath"),
    objDefaultLoginOption ("//*[contains(@text,'Default log-in option')]"," //*[@name='Default log-in option']", "xpath", "xpath"),
    objAppInformation ("//*[contains(@text,'App information')]"," (//*[contains(@name,' App information')])[16]", "xpath", "xpath"),
    objAppInformationDisabled ("//*[contains(@text,'App information')] "," (//XCUIElementTypeOther[contains(@name,' App information') and @accessible='false'])[15]", "xpath", "xpath"),
    objWeAreAlmostReady ("//*[contains(@text,'We are almost')] "," //*[contains(@name,'We are almost')]", "xpath", "xpath"),
    //App Information screen
    objAppInformationHeaderPage ("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objAppVersionOption ("//*[contains(@text,'App Version')]"," //XCUIElementTypeStaticText[@name='App Version']", "xpath", "xpath"),
    objAppVersion ("//*[contains(@text,'App information')]","(//*[contains(@name,' App information')])[21]", "xpath", "xpath"),
    objDeviceNameOption ("//*[contains(@text,'Device Name')] "," //XCUIElementTypeStaticText[@name='Device Name']", "xpath", "xpath"),
    objDeviceName ("//*[contains(@text,'Device Name')]"," //*[@name='Device Name']/following-sibling::*", "xpath", "xpath"),
    objDeviceVersionOption ("//*[contains(@text,'Device Version')] "," //XCUIElementTypeStaticText[@name='Device Version']", "xpath", "xpath"),
    objDeviceVersion ("//*[contains(@text,'Device Version')]"," //*[@name='Device Version']/following-sibling::*", "xpath", "xpath"),
    objDeviceModelOption ("//*[contains(@text,'Device Model')] "," //XCUIElementTypeStaticText[@name='Device Model']", "xpath", "xpath"),
    objDeviceModel ("//*[contains(@text,'Device Model')]"," //*[@name='Device Model']/following-sibling::*", "xpath", "xpath"),
    objOsVersionOption ("//*[contains(@text,'OS Version')] "," //XCUIElementTypeStaticText[@name='OS Version']", "xpath", "xpath"),
    objOsVersion ("//*[contains(@text,'OS Version')]"," //*[@name='OS Version']/following-sibling::*", "xpath", "xpath"),
    objCopyToClipBoard ("//*[contains(@text,'Copy to clipboard')] ","//XCUIElementTypeStaticText[@name='Copy to clipboard']", "xpath", "xpath"),
    objResetApp ("//*[contains(@text,' Reset app')]"," (//*[@name=' Reset app'])[3]", "xpath", "xpath"),
    objLogOut ("//*[contains(@text,' Log out')]"," (//*[@name=' Log out'])[3]", "xpath", "xpath"),
    // How should we call screen
    objHowShouldWeCallYouScreen ("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objConfirmNickNameButton ("//android.widget.TextView[@text='Confirm nickname']","(//XCUIElementTypeOther[@name='Confirm nickname'])[3]", "xpath", "xpath"),
    objNicknameTextField ("//*[@class='android.widget.EditText']","//*[@name='Nickname']/child::*/child::XCUIElementTypeTextField", "xpath", "xpath"),
    objInlineErrorMessage ("//*[contains(@text,'Please enter a valid Nickname')]","(//*[@name='Please enter a valid Nickname'])[2]", "xpath", "xpath"),
    //Profile Photo update screen
    objTakeAPhoto ("//*[@text=' Take a photo']","(//*[@name=' Take a photo'])[2]", "xpath", "xpath"),
    objPickFromYourGallery ("//*[contains(@text,'Pick from the gallery')]","(//*[@name=' Pick from your gallery'])[2]", "xpath", "xpath"),
    objPickFromTheGallery ("//*[contains(@text,'Pick from the gallery')]","(//*[@name=' Pick from the gallery'])[2]", "xpath", "xpath"),
    objPickFromGallery ("//*[contains(@text,'Pick from your gallery')]","(//*[@name=' Pick from your gallery'])[2]", "xpath", "xpath"),
    objCancel ("//*[@text='Cancel'] "," (//*[@name='Cancel'])[2]", "xpath", "xpath"),
    objShutterButton ("//android.view.View[@resource-id='com.sec.android.app.camera:id/bottom_background']"," //*[@name='PhotoCapture']", "xpath", "xpath"),
    objDoneButton ("//android.widget.TextView[@resource-id='com.sec.android.app.camera:id/navigation_bar_item_small_label_view' and @text='OK']"," //*[@name='Use Photo']", "xpath", "xpath"),
    objSwitchCameraButton ("//android.view.View[@resource-id='com.sec.android.app.camera:id/bottom_background']"," //*[@name='FrontBackFacingCameraChooser']", "xpath", "xpath"),
    objCameraCloseButton ("//android.widget.TextView[@resource-id='com.sec.android.app.camera:id/navigation_bar_item_small_label_view' and @text='OK']"," (//*[@name='Cancel'])[2]", "xpath", "xpath"),
    //Mailing Address screen
    objMailingAddressHeader ("//*[@resource-id='com.tonik.mobile:id/Main_title_txt']", "com.tonik.mobile:id/Main_title_txt", "xpath", "id"),
    objOfficialPrintedDocument ("com.tonik.mobile:id/Sub_title_txt", "com.tonik.mobile:id/Sub_title_txt", "id", "id"),
    objCountryOfResidency ("//*[contains(@text,'Country of Residence')]/following-sibling::*","//*[@label='Country of Residence']/child::*/child::XCUIElementTypeTextField", "xpath", "xpath"),
    objZipCodeTextBox ("//*[contains(@text,'Zip code')]/parent::*/child::android.widget.EditText","//*[@name='Zip code']//child::XCUIElementTypeTextField", "xpath", "xpath"),
    objApplyZipCode ("//*[contains(@text,'Apply zip code')]"," //*[@name='Apply zip code']", "xpath", "xpath"),
    objEnterValidZipCode ("//*[contains(@text,'Please enter a valid Zipcode.')]","//*[@name='Please enter a valid Zipcode.']", "xpath", "xpath"),
    objHowToFindYourZipCode ("//*[contains(@text,'How to find your zip code?')] ","//XCUIElementTypeOther[@name='How to find your zip code?']", "xpath", "xpath"),
    objProvince ("//*[contains(@text,'Province')]","//*[@value='Province']", "xpath", "xpath"),
    objCityMuncipality ("//*[contains(@text,'City / Municipality')]"," //*[@name='City / Municipality']/following-sibling::*", "xpath", "xpath"),
    objBarangayTextField ("//*[contains(@text,'Barangay')]","//*[@value='Barangay']", "xpath", "xpath"),
    objHouseUnitFloor ("//*[contains(@text,'House')]/parent::*/child::android.widget.EditText ","//*[contains(@name,'House')]/child::*/child::XCUIElementTypeTextField", "xpath", "xpath"),
    //Find your Barangay Screen
    objFindYourBarangay ("//*[@resource-id='com.tonik.mobile:id/Main_title_txt'] "," (//*[@name='com.tonik.mobile:id/Main_title_txt'])[2]", "xpath", "xpath"),
    objBarangayName ("//*[contains(@text,'POST PROPER')]","//XCUIElementTypeStaticText[contains(@label,'POST PROPER')]", "xpath", "xpath"),
    objBarangaySearchField ("//*[contains(@text,'Type here to search')]","//*[@value='Type here to search']", "xpath", "xpath"),
    objSearchedBarangay ("//android.widget.ScrollView/child::*/child::*/child::*/child::android.view.ViewGroup/child::android.widget.TextView","(//*[@value='Find your Barangay']/following::XCUIElementTypeOther)[9]", "xpath", "xpath"),
    objConfirmButton ("//*[contains(@text,'Confirm')] ","(//*[@name='Continue'])[2]", "xpath", "xpath"),
    //new Mailing Address Screen
    objYouNeedToContactTonikCustomer ("com.tonik.mobile:id/Popup_sub_header_txt", "com.tonik.mobile:id/Popup_sub_header_txt", "id", "id"),
    objContactTonikButton ("com.tonik.mobile:id/Popup_positive_btn_click", "com.tonik.mobile:id/Popup_positive_btn_click", "id", "id"),
    objGoBackButtonOnPopup ("com.tonik.mobile:id/Popup_negative_btn_txt", "com.tonik.mobile:id/Popup_negative_btn_txt", "id", "id"),
    //Additional information Page Screen
    objAdditionalInformationPageHeader ("com.tonik.mobile:id/Main_title_txt","com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objAdditionalInformationSubTitle ("com.tonik.mobile:id/Sub_title_txt", "com.tonik.mobile:id/Sub_title_txt", "id", "id"),
    objCurrentEmploymentStatus ("//*[contains(@text,'Current employment status')]/following-sibling::*"," (//*[contains(@value,' Current employment status')]/following-sibling::*)", "xpath", "xpath"),
    objNameOfTheCompany ("//*[contains(@text,'Name of the company')]/parent::*/child::android.widget.EditText ","//XCUIElementTypeStaticText[@value='Name of the company']/parent::*", "xpath", "xpath"),
    objNameOfTheCompanyTextBox ("//*[contains(@text,'Name of the company')]/parent::*/child::android.widget.EditText ","//XCUIElementTypeStaticText[@value='Name of the company']/parent::*/child::*/child::XCUIElementTypeTextField", "xpath", "xpath"),
    objMonthlyIncome ("//*[contains(@text,' Monthly income')]/parent::*/child::android.widget.EditText","((//XCUIElementTypeStaticText[@value=' Monthly income'])[1]/following-sibling::*)", "xpath", "xpath"),
    objMonthlyIncomeTextBox ("//*[contains(@text,' Monthly income')]/parent::*/child::android.widget.EditText ","//XCUIElementTypeStaticText[@value=' Monthly income']/parent::*/child::*/child::XCUIElementTypeTextField", "xpath", "xpath"),
    objPurposeOfOpeningAnAccount ("//*[contains(@text,'Purpose of opening an account')]/parent::*/child::android.widget.EditText "," (//*[@label='Purpose of opening an account'])[4]", "xpath", "xpath"),
    objUpdateButton ("//*[contains(@text,'Update')] "," (//*[@name='Update'])[3]", "xpath", "xpath"),
    objCurrentEmploymentStatusLockIcon ("//*[contains(@text,'Current employment status')]/parent::*/following-sibling::android.view.ViewGroup[1]"," ((//XCUIElementTypeStaticText[@value=' Current employment status'])[1]/following::XCUIElementTypeOther)[2]", "xpath", "xpath"),
    objPurposeOfOpeningAnAccountLockIcon ("//*[contains(@text,'Purpose of opening an account')]/parent::*/following-sibling::android.view.ViewGroup[1]"," ((//XCUIElementTypeStaticText[@value='Purpose of opening an account'])[1]/following::XCUIElementTypeOther)[2]", "xpath", "xpath"),
    objLockIconPopup ("com.tonik.mobile:id/Popup_sub_header_txt", "com.tonik.mobile:id/Popup_sub_header_txt", "id", "id"),
    objOkayButtonOnLockIconPopup ("com.tonik.mobile:id/Popup_positive_btn_click", "com.tonik.mobile:id/Popup_positive_btn_click", "id", "id"),
    objContactTonikCustomerCareButtonOnLockIconPopup ("com.tonik.mobile:id/Popup_negative_btn_txt", "id", "id"),
    objInlineErrorMessageForMonthlyIncome ("//*[contains(@text,'Monthly Income should be between ₱ 100 and ₱ 99,999,999')]","//XCUIElementTypeStaticText[@value='Monthly Income should be between ₱ 100 and ₱ 99,999,999']", "xpath", "xpath"),
    objGraydoutUpdateButton ("//*[contains(@text,'Update') and @clickable='false' ] "," (//XCUIElementTypeOther[@name='Update' and @enabled='false'])", "xpath", "xpath"),
    //Reset App Screen
    objResetAppPopUp ("//*[contains(@text,'If you reset this app') ] "," //*[contains(@value,'If you reset this app')]", "xpath", "xpath"),
    objResetAppButton ("//*[contains(@text,'Reset app')] "," (//XCUIElementTypeOther[@name='Reset app'])[2]", "xpath", "xpath"),
    objTAkeMeBack ("//*[contains(@text,'Take me back')] "," (//XCUIElementTypeOther[@name='Take me back'])[2]", "xpath", "xpath"),
    objEnterYourRegisterMobileNumber ("//*[contains(@text,'Enter your registered mobile number')] "," //XCUIElementTypeStaticText[@name='Enter your registered mobile number']", "xpath", "xpath"),
    objTakeAFaceIdentityScan ("//*[contains(@text,'Take a Face Identity Scan')] "," //XCUIElementTypeStaticText[@name='Take a Face Identity Scan']", "xpath", "xpath"),
    objResetYourPassword ("//*[contains(@text,'Reset your password')] "," //XCUIElementTypeStaticText[@name='Reset your password']", "xpath", "xpath"),
    objContinueButton ("//*[contains(@text,'Continue')] "," //XCUIElementTypeStaticText[@name='Continue']", "xpath", "xpath"),
    //Create new Password Screen
    objCreateNewPassword ("//*[@resource-id='com.tonik.mobile:id/header_textView'] "," //XCUIElementTypeStaticText[@name='Create a password']", "xpath", "xpath"),
    objNewPassword ("//*[@resource-id='com.tonik.mobile:id/pin'] "," //XCUIElementTypeSecureTextField[@value='Password']", "xpath", "xpath"),
    objReEnter ("//*[@resource-id='com.tonik.mobile:id/confirm'] "," //XCUIElementTypeSecureTextField[@value='Confirm password']", "xpath", "xpath"),
    objConfirmPasswordButton ("//*[@resource-id='com.tonik.mobile:id/confirm'] "," //XCUIElementTypeButton[@name='Continue']", "xpath", "xpath"),
    objPinkySwearPopup ("//*[@resource-id='com.tonik.mobile:id/tvPasswordTitle'] "," //XCUIElementTypeStaticText[@name='Pinky swear that you’ll…']", "xpath", "xpath"),
    objGotItButton ("//*[contains(@text,'Got it') and @enabled='true'] "," //XCUIElementTypeStaticText[@name='Got it']", "xpath", "xpath"),
    objYouNowHaveANewPassword ("//*[contains(@text,'You now have a new password')] "," //XCUIElementTypeStaticText[@name='You now have a new password']", "xpath", "xpath"),
    objLoginWithNewPassword ("//*[contains(@text,'Log in with new password')] "," (//XCUIElementTypeOther[@name='Log in with new password'])[3]", "xpath", "xpath"),
    //Pick a New Email Screen
    objPickANewEmail ("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objEmailAddressEditTextField ("//*[@text='Email address']/following-sibling::android.widget.EditText ","//*[@value='Email address']/following-sibling::XCUIElementTypeOther/XCUIElementTypeTextField", "xpath", "xpath"),
    //changes to be
    objEmailAddressField ("(//*[contains(@text,'Email address')]/preceding-sibling::*) ","//*[@value='Email address']/parent::*/XCUIElementTypeOther", "xpath", "xpath"),
    objVerifyYourEmailSubTitle ("com.tonik.mobile:id/Sub_title_txt", "com.tonik.mobile:id/Sub_title_txt", "id", "id"),
    objOTPNotMatchingPopup ("com.tonik.mobile:id/Popup_sub_header_txt", "com.tonik.mobile:id/Popup_sub_header_txt", "id", "id"),
    objOKButton ("//*[contains(@text,'Ok')] ","//XCUIElementTypeOther[@name=\"com.tonik.mobile:id/Popup_single_btn_click\"]", "xpath", "xpath"),
    objNooiceScreen ("//*[contains(@text,'Nooice!')]", "Nooice!","xpath", "id"),
    objThanksForVerifying ("//*[contains(@text,'Thanks for verifying')]", "//XCUIElementTypeStaticText[contains(@name,'Thanks for verifying')]","xpath", "xpath"),
    objCloseButton ("//*[contains(@text,'Close')]","//XCUIElementTypeStaticText[contains(@name,'Close')]", "xpath", "xpath"),
    objVerifyYourEmail ("com.tonik.mobile:id/Main_title_txt", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objOTPText ("//android.widget.TextView[contains(@text,'OTP')]", "com.tonik.mobile:id/Main_title_txt", "id", "id"),
    objErrorMessageInEmailAddressField ("//android.widget.TextView[@text='Please enter a valid email id ']"," //XCUIElementTypeStaticText[@name='Please enter a valid email id ']", "xpath", "xpath"),
    objYourAccountLimitIs ("//*[contains(@text,'Your account limit is ₱50,000')] "," //XCUIElementTypeOther[@name=\"Your account limit is ₱50,000 \"]", "xpath", "xpath"),
    objGetHigherLimitIs ("//*[contains(@text,'Get higher limits')] "," (//*[contains(@name,'Get higher limits')])[15]", "xpath", "xpath"),
    //Gallery
    objGallery ("(//android.widget.ImageView[@resource-id='com.android.intentresolver:id/icon'])[1]"," (//*[@name='Albums'])[3]", "xpath", "xpath"),
    objAllPhotos ("//*[@text='All Photos']","", "xpath", "xpath"),
    objAutomationFolder ("//*[contains(@text,'Automation')] ","(//*[@name='Automation'])", "xpath", "xpath"),
    objSelectPhotoInFolder ("(//android.widget.FrameLayout[@resource-id=\"com.sec.android.gallery3d:id/deco_view_layout\"])[1]", "//XCUIElementTypeOther[@name=\"Recents\"]", "xpath", "xpath"),
    objSelectPhotoInRecents ("(//android.widget.FrameLayout[@resource-id=\"com.sec.android.gallery3d:id/deco_view_layout\"])[1]", "//XCUIElementTypeOther[@name=\"Recents\"]/descendant::XCUIElementTypeImage", "xpath", "xpath"),
    objAlbumIOS ("//XCUIElementTypeButton[@name='Albums']", "xpath", "xpath"),
    objKeyboardDoneBtn ("//*[@text='Done']", "//XCUIElementTypeButton[@name='Done']", "xpath", "xpath"),
    objKeyboardNextBtn ("//*[@text='Done']", "//XCUIElementTypeButton[@name='Next']", "xpath", "xpath"),
    objUpdatedProfilePicture("//*[@class='android.widget.ImageView']","//XCUIElementTypeImage","xpath","xpath"),
    objHowWouldYouLikeToLoginPopup("//*[contains(@text,'How would you like to log in?')]","//XCUIElementTypeStaticText[contains(@name,'How would you like to log in?')]","xpath","xpath"),
    objPageHeader("com.tonik.mobile:id/Main_title_txt","com.tonik.mobile:id/Main_title_txt","id","id"),
    objPasswordOnPopup("//*[contains(@text,'Password')]", "(//XCUIElementTypeStaticText[contains(@name,'Password')])[2]","xpath","xpath"),
    objRegisteredMobileNumberBtn("//android.widget.TextView[@text=\"Registered Mobile number\"]","//*[@value=\"Registered Mobile number\"]","xpath","xpath"),
    objPositiveBtn("com.tonik.mobile:id/Popup_negative_btn_txt", "com.tonik.mobile:id/Popup_negative_btn_txt", "id", "id"),
    objNegativeBtn("com.tonik.mobile:id/Popup_positive_btn_click", "com.tonik.mobile:id/Popup_positive_btn_click", "id", "id"),
    objRequestMsg("//android.widget.TextView[@text='Your request is our command!']","//*[@value='Your request is our command!']","xpath","xpath"),
    objBackToDashboard("//android.widget.TextView[@text=\"Back to Dashboard\"]","//*[@value=\"Back to Dashboard\"]","xpath","xpath"),
    objRequestDescription("//android.widget.TextView[@text=\"Give us 24 hours to update your registered mobile number. In the meantime, check your inbox for a confirmation email.\"]","//*[@value=\"Give us 24 hours to update your registered mobile number. In the meantime, check your inbox for a confirmation email.\"]","xpath","xpath"),
    objNextBtn ("//*[@text='Next']/parent::*","//*[@value='Next']/parent::* | //*[@value='Button_text']/parent::*","xpath","xpath"),
    objMobileNumberField ("//*[@class='android.widget.EditText']","//XCUIElementTypeTextField", "xpath", "xpath"),
    objPasswordOnPopupConfirm("//*[contains(@text,'Confirm')]", "(//XCUIElementTypeOther[@name=\"Confirm\"])[2]","xpath","xpath");
    private String android;
    private String ios;
    private String andPathType;
    private String iosPathType;
    ProfilePage(String android, String ios, String andPathType, String iosPathType) {
        this.android = android;
        this.ios = ios;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }
    ProfilePage(String android,  String andPathType, String iosPathType) {
        this.android = android;
        this.ios = android;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }
    ProfilePage(String android,  String andPathType) {
        this.android = android;
        this.ios = android;
        this.andPathType = andPathType;
        this.iosPathType = andPathType;
    }
    public static By getByOSType(String osType, ProfilePage objectName) {
       if (osType.equalsIgnoreCase("android") && objectName.andPathType.equalsIgnoreCase("xpath"))
       {
    	   return By.xpath(objectName.android);
       }
       else if (osType.equalsIgnoreCase("android") && objectName.andPathType.equalsIgnoreCase("id"))
       {
    	   return By.id(objectName.android);
       }
       else if (osType.equalsIgnoreCase("android") && objectName.andPathType.equalsIgnoreCase("name"))
       {
    	   return By.name(objectName.android);
       }  
       else if (osType.equalsIgnoreCase("ios") && objectName.iosPathType.equalsIgnoreCase("xpath"))
       {
    	   return By.xpath(objectName.ios);
       }
       else if (osType.equalsIgnoreCase("ios") && objectName.iosPathType.equalsIgnoreCase("id"))
       {
    	   return By.id(objectName.ios);
       }
       else if (osType.equalsIgnoreCase("ios") && objectName.iosPathType.equalsIgnoreCase("name"))
       {
    	   return By.name(objectName.ios);
       }  
        throw new IllegalArgumentException("Object not found: " + objectName);
    }
    public static void main(String[] args) {
        System.out.println(ProfilePage.getByOSType("ios", ProfilePage.objSelectPhotoInFolder));
    }
    public static By objPopup(String platform, int popup){
        if(platform.equalsIgnoreCase("Android"))
            return By.xpath("(//android.widget.TextView[@resource-id=\"com.tonik.mobile:id/Popup_sub_header_txt\"])["+popup+"]");
        else
            return By.xpath("(//*[@resource-id=\"com.tonik.mobile:id/Popup_sub_header_txt\"])["+popup+"]");
    }
}
