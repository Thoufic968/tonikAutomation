package com.tonik.bizfunctions;
import com.driverInstance.DriverManager;
import com.tonik.pageObject.*;
import com.tonik.utility.ExtentReporter;
import com.tonik.utility.Utilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import static com.tonik.utility.DB_Utilites.selectQuery;
import static com.tonik.utility.ExtentReporter.extentLoggerPass;
import static com.tonik.utility.ExtentReporter.platform;
import static com.tonik.utility.Utilities.*;
import java.util.List;
import java.util.Objects;
public class ProfileModule extends BaseClass {
    public ProfileModule() {
        super();
    }
    String platform = Utilities.getPlatform();
    String firstName;
    String middleName;
    String lastName;
    String gender;
    String placeOfBirth;
    String dateOfBirth;
    String nationality;
    String iDNumber;
    /**
     * Reusable method to account information UI validations
     * @throws Exception
     */
    public void accountInformationUIValidation() throws Exception {
        logger.info("accountInformationUIValidation");
        if (verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objAccountInformation), "Account Information")) {
            if (verifyElementDisplayed(ProfilePage.getByOSType(platform, ProfilePage.objWeAreAlmostReady))) {
                logger.info("We are Almost Ready Screen is Displayed");
                extentLoggerPass("Screen", "We are Almost Ready Screen is Displayed");
            }
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objAccountDetails), "Header: Account Details");
            containsValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objBankNameOption)), propertyFileReader("BankName", "Profile"), ":Bank Name");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objBankName), getTextVal(ProfilePage.getByOSType(platform, ProfilePage.objBankName), ":Bank Name"));
            containsValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objAccountNumberOption)), propertyFileReader("AccountNumber", "Profile"), ":Account Number");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objAccountNumber), getTextVal(ProfilePage.getByOSType(platform, ProfilePage.objAccountNumber), ":Account number"));
            containsValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objAccountHolderOption)), propertyFileReader("AccountHolder", "Profile"), ":Account Holder");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objAccountHolder), "Account Holder Name");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objAccountHolder), getTextVal(ProfilePage.getByOSType(platform, ProfilePage.objAccountHolder), ":Account number"));
        }
    }
    /**
     * Reusable method to PersonalDetailsScreenUIValidation
     * @throws Exception
     */
    public void personalDetailsScreenUIValidation() throws Exception {
        if (verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objPersonalDetails), "Personal Details")) {
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objPersonalDetailsHeader), "Header: Personal Details");
            containsValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objPersonalDetailsHeader)), propertyFileReader("PersonalDetails", "Profile"), ": Header :Personal Details");
            containsValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objFirstNameOption)), propertyFileReader("FirstName", "Profile"), ": First Name");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objFirstName), getTextVal(ProfilePage.getByOSType(platform, ProfilePage.objFirstName), ": Header :First Name"));
            firstName = getText(ProfilePage.getByOSType(platform, ProfilePage.objFirstName));
            containsValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objMiddleNameOption)), propertyFileReader("MiddleName", "Profile"), ":Middle Name");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objMiddleName), getTextVal(ProfilePage.getByOSType(platform, ProfilePage.objMiddleName), ": Header :Middle Name"));
            middleName = getText(ProfilePage.getByOSType(platform, ProfilePage.objMiddleName));
            containsValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objLastNameOption)), propertyFileReader("LastName", "Profile"), ":Last Name");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objLastName), getTextVal(ProfilePage.getByOSType(platform, ProfilePage.objLastName), ": Header :Last Name"));
            lastName = getText(ProfilePage.getByOSType(platform, ProfilePage.objLastName));
            containsValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objGenderOption)), propertyFileReader("Gender", "Profile"), ":Gender");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objGender), getTextVal(ProfilePage.getByOSType(platform, ProfilePage.objGender), ": Header :Gender"));
            gender = getText(ProfilePage.getByOSType(platform, ProfilePage.objGender));
            if (gender.equalsIgnoreCase("male")) {
                gender = "M";
            }else if(gender.equalsIgnoreCase("female")) {
                gender = "F";
            };
            containsValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objDateOfBirthOption)), propertyFileReader("DateOfBirth", "Profile"), ":Date Of Birth");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objDateOfBirth), getTextVal(ProfilePage.getByOSType(platform, ProfilePage.objDateOfBirth), ":Date Of Birth"));
            dateOfBirth = getText(ProfilePage.getByOSType(platform, ProfilePage.objDateOfBirth));
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objPlaceOfBirthOption), "Place Of Birth Option");
            containsValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objPlaceOfBirthOption)), propertyFileReader("PlaceOfBirth", "Profile"), ":Place Of Birth");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objPlaceOfBirth), getTextVal(ProfilePage.getByOSType(platform, ProfilePage.objPlaceOfBirth), ": Place of Birth"));
            placeOfBirth = getText(ProfilePage.getByOSType(platform, ProfilePage.objPlaceOfBirth));
            containsValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objNationalityOption)), propertyFileReader("Nationality", "Profile"), ":Nationality");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objNationality), getTextVal(ProfilePage.getByOSType(platform, ProfilePage.objNationality), ":Nationality"));
            nationality = getText(ProfilePage.getByOSType(platform, ProfilePage.objNationality));
            containsValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objRegisterIDOption)), propertyFileReader("RegisterId", "Profile"), ":Register ID");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objRegisterID), getTextVal(ProfilePage.getByOSType(platform, ProfilePage.objRegisterID), ":Register ID"));
            swipe("UP", 1);
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objIDNumber), "ID Number");
            containsValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objIDNumberOption)), propertyFileReader("IDNumber", "Profile"), ":ID Number option");
            iDNumber = getText(ProfilePage.getByOSType(platform, ProfilePage.objIDNumber));
            containsValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objIDExpiryDateOption)), propertyFileReader("IDExpiryDate", "Profile"), ":ID Expiry date");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objIDExpiryDate), getTextVal(ProfilePage.getByOSType(platform, ProfilePage.objIDExpiryDate), ":ID Expiry Date"));
        }
    }
    /**
     * Reusable method to Contact information screen UI validation
     * @throws Exception
     */
    public void contactInformationScreenUIValidation() throws Exception {
        if (verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objContactInformation), "Contact Information")) {
            waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objContactInfoHeader),10,"Contact info Header");
            assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objContactInfoHeader)), propertyFileReader("HeaderOfContactInfo", "Profile"), ": Header : Contact Info");
            assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objMailingAddress)), propertyFileReader("MailingAddress", "Profile"), ": Mailing Address");
            assertionValidation(getText(ProfilePage.getByOSType(platform,ProfilePage.objProvideYourCommunicationAddress)), propertyFileReader("ProvideYourCommunicationAddress", "Profile"), ":Provide your communication address for all the communications");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objMobileNumber), ": Mobile Number");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objEmailAddress), ": Email Address");
            containsValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objVerifyNow)), propertyFileReader("VerifyNow", "Profile"), ":Verify Now");
        }
    }
    /**
     * Reusable method to Mailing address Screen UI validation* @throws Exception
     */
    public void mailingAddressScreenUIValidation() throws Exception {
        if (verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objMailingAddress), "Mailing Address")) {
            waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objMailingAddressHeader),10,"Header : Mailing Address");
            assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objMailingAddressHeader)), propertyFileReader("MailingAddress", "Profile"), ": Header : Mailing Address");
            assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objOfficialPrintedDocument)), propertyFileReader("OfficialPrintedDocument", "Profile"), ": Official printed Document");
            assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objCountryOfResidency)), propertyFileReader("CountryOfResidency", "Profile"), ":Country of Residency");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objMailingAddressHeader), ": zip code field");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objHowToFindYourZipCode), ": How to find your Zip Code");
            verticalSwipeTillElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objProvince));
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objProvince), ": Province");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objCityMuncipality), ":City/Muncipality");
            swipe("UP", 1);
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objBarangayTextField), "Barangay");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objHouseUnitFloor), "House Unit floor");
        }
    }
    /**
     * Reusable method to Additional information Screen UI validation
     * @throws Exception
     */
    public void additionalInformationScreenUIValidation() throws Exception {
        if (verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objAdditionalInformation), "Additional information")) {
            waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objAdditionalInformationPageHeader),10,"Header: Additional information Page Header");
            verifyElementPresent(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "Back icon on addition information page");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objAdditionalInformationPageHeader), "Additional information Page Header");
            containsValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objAdditionalInformationPageHeader)), propertyFileReader("AdditionalInformationPageHeader", "Profile"), ": Header : Additional information");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objAdditionalInformationSubTitle), "Additional information Page Subtitle");
            assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objAdditionalInformationSubTitle)), propertyFileReader("AdditionalInformationSubTitle", "Profile"), ":Additional information subtitle");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objCurrentEmploymentStatus), "Current Employment Status");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objNameOfTheCompany), "Name of the Company");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objMonthlyIncome), "Monthly income edit text field");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objPurposeOfOpeningAnAccount), "Purpose of opening an account edit text field");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objUpdateButton), "Update button");
        }
    }
    /**
     * Reusable method to App information screen UI validation
     * @throws Exception
     */
    public void appInformationScreenUIValidation() throws Exception {
        if (verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objAppInformation), "Application information")) {
            waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objAppInformationHeaderPage),10,"Header:App information Page Header");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objAppInformationHeaderPage), "App information page header");
            assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objAppInformationHeaderPage)), propertyFileReader("AppInformationPageHeader", "Profile"), ":App information Page Header");
            assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objAppVersionOption)), propertyFileReader("AppVersionOption", "Profile"), ":App Version");
            assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objDeviceNameOption)), propertyFileReader("DeviceNameOption", "Profile"), ":Device Version");
            verifyElementPresent(ProfilePage.getByOSType(platform,ProfilePage.objDeviceName),getTextVal(ProfilePage.getByOSType(platform,ProfilePage.objDeviceName), ":Device Name"));
            assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objDeviceVersionOption)), propertyFileReader("DeviceVersionOption", "Profile"), ":Device Version Option");
            verifyElementPresent(ProfilePage.getByOSType(platform,ProfilePage.objDeviceVersion),getTextVal(ProfilePage.getByOSType(platform,ProfilePage.objDeviceVersion), ":Device Version"));
            assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objDeviceModelOption)), propertyFileReader("DeviceModelOption", "Profile"), ":Device Model Option");
            verifyElementPresent(ProfilePage.getByOSType(platform,ProfilePage.objDeviceModel),getTextVal(ProfilePage.getByOSType(platform,ProfilePage.objDeviceModel), ":Device model"));
            assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objOsVersionOption)), propertyFileReader("OsVersionOption", "Profile"), ":Os Version Option");
            verifyElementPresent(ProfilePage.getByOSType(platform,ProfilePage.objOsVersion),getTextVal(ProfilePage.getByOSType(platform,ProfilePage.objOsVersion), ":Os Version Option"));
        }
    }
    /**
     * Method to enter OTP
     * @param otp
     * @throws Exception
     */
    public static void enterOTP(String otp) throws Exception {
        click(LoginPage.objOTPInputField(Utilities.getPlatform(),1),"OTP Input field");
        DriverManager.getAppiumDriver().getKeyboard().sendKeys(otp);
    }
    /**
     * function to create new Password
     * @throws Exception
     */
    public String enterNewPassword() throws Exception {
        String randomPassword=null;
        if(verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objNewPassword))) {
            randomPassword = RandomPassword();
            Actions a = new Actions(DriverManager.getAppiumDriver());
            click(ProfilePage.getByOSType(platform,ProfilePage.objNewPassword), "New password Input field");
            if(verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objPinkySwearPopup))){
                waitForElementToBePresent(ProfilePage.getByOSType(platform,ProfilePage.objGotItButton), 20,"Got it Button");
                waitTime(5000);//waiting to get Got It button enabling, doesn't have proper attribute.
                click(ProfilePage.getByOSType(platform,ProfilePage.objGotItButton), "Got it Button");
            }
            a.sendKeys(randomPassword).perform();
            logger.info("Entered Random New Password");
            ExtentReporter.extentLoggerPass("", "Entered Random New Password");
            if (platform.equalsIgnoreCase("Android")) {
                hideKeyboard();
            } else {
                swipe("UP", 1);
            }
            click(ProfilePage.getByOSType(platform,ProfilePage.objReEnter), "Re Enter password Input field");
            a.sendKeys(randomPassword).perform();
            logger.info("RE Entered Random New Password");
            ExtentReporter.extentLoggerPass("", "Re Entered Random New Password");
            verifyElementPresentAndClick(ProfilePage.getByOSType(platform,ProfilePage.objConfirmButton),"Confirm Button");
        }
        return randomPassword;
    }
    /**
     * Method To verify if account holder can access the Profile menu when TSA is not yet created
     * @throws Exception
     */
    public void verifyIfAccountHolderCanAccessTheProfileMenuWhenTSANotYetCreated_TBD_PRO_001() throws Exception {
        HeaderChildNode("TBD_PRO_001, Login - Verify if account holder can access the Profile menu when TSA is not yet created");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon), "Profile Icon");
        waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objProfileHeader), 10, "Header:profile Header");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objProfileHeader), " : profile header");
        assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objProfileHeader)), propertyFileReader("ProfileHeader", "Profile"), ":Profile Header");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objAccountInformation), " : profile header");
        containsValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objAccountInformation)), propertyFileReader("AccountInformation", "Profile"), ":Account Information");
        verifyElementDisabled(ProfilePage.getByOSType(platform, ProfilePage.objPersonalDetailsDisabled), " : personal Details");
        verifyElementDisabled(ProfilePage.getByOSType(platform, ProfilePage.objContactInformationDisabled), " : Contact Information");
        verifyElementDisabled(ProfilePage.getByOSType(platform, ProfilePage.objAdditionalInformationDisabled), " : Additional information");
        verifyElementDisabled(ProfilePage.getByOSType(platform, ProfilePage.objRequestBankCertificateDisabled), " : Request Bank certificate");
        verifyElementEnabled(ProfilePage.getByOSType(platform, ProfilePage.objLogInOption), " : Login Option");
        verifyElementDisabled(ProfilePage.getByOSType(platform, ProfilePage.objAppInformationDisabled), " :App information");
        swipe("UP", 1);
        verifyElementEnabled(ProfilePage.getByOSType(platform, ProfilePage.objResetApp), " : Reset App");
        verifyElementEnabled(ProfilePage.getByOSType(platform, ProfilePage.objLogOut), " : Logout Button");
        verifyElementPresent(LoginPage.getByOSType(platform, LoginPage.objBackIcon), "Back icon on addition information page");
        if (verifyElementDisplayed(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon))) {
            logger.info("TBD_PRO_001, Profile - Verify if account holder can access the Profile menu when TSA is not yet created");
            extentLoggerPass("TBD_PRO_001", "TBD_PRO_001, Profile - Verify if account holder can access the Profile menu when TSA is not yet created");
        }
    }
    /**
     * Method to verify User can create TSA from profile
     * @throws Exception
     */
    public void verifyIfUserCanCreateTSAFromProfile_TBD_PRO_002() throws Exception {
        HeaderChildNode("TBD_PRO_002, Login - Verify If User Can Create TSA From Profile");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon), "Profile Icon");
        waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objProfileHeader), 10, "Header:profile Header");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objProfileHeader), " : profile header");
        assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objProfileHeader)), propertyFileReader("ProfileHeader", "Profile"), ":Profile Header");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objAccountInformation), " : profile header");
        containsValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objAccountInformation)), propertyFileReader("AccountInformation", "Profile"), ":Account Information");
        verifyElementDisabled(ProfilePage.getByOSType(platform, ProfilePage.objPersonalDetailsDisabled), " : personal Details");
        verifyElementDisabled(ProfilePage.getByOSType(platform, ProfilePage.objContactInformationDisabled), " : Contact Information");
        verifyElementDisabled(ProfilePage.getByOSType(platform, ProfilePage.objAdditionalInformationDisabled), " : Additional information");
        verifyElementDisabled(ProfilePage.getByOSType(platform, ProfilePage.objRequestBankCertificateDisabled), " : Request Bank certificate");
        verifyElementEnabled(ProfilePage.getByOSType(platform, ProfilePage.objLogInOption), " : Login Option");
        verifyElementDisabled(ProfilePage.getByOSType(platform, ProfilePage.objAppInformationDisabled), " :App information");
        swipe("UP", 1);
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objResetApp), " : Reset App");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objLogOut), " : Logout Button");
        swipe("DOWN", 2);
        accountInformationUIValidation();
        verifyElementPresent(LoginPage.getByOSType(platform, LoginPage.objBackIcon), "Back icon on addition information page");
        verifyElementEnabled(ProfilePage.getByOSType(platform, ProfilePage.objContactInformation), " : Contact Information");
        verifyElementEnabled(ProfilePage.getByOSType(platform, ProfilePage.objAdditionalInformation), " : Additional information");
        verifyElementEnabled(ProfilePage.getByOSType(platform, ProfilePage.objRequestBankCertificate), " : Request Bank certificate");
        verifyElementEnabled(ProfilePage.getByOSType(platform, ProfilePage.objLogInOption), " : Login Option");
        verifyElementEnabled(ProfilePage.getByOSType(platform, ProfilePage.objAppInformation), " :App information");
        swipe("UP", 1);
        verifyElementEnabled(ProfilePage.getByOSType(platform, ProfilePage.objResetApp), " : Reset App");
        if (verifyElementEnabled(ProfilePage.getByOSType(platform, ProfilePage.objLogOut), " : Logout Button")) {
            logger.info("TBD_PRO_002, Profile - Verify If User Can Create TSA From Profile");
            extentLoggerPass("TBD_PRO_002", "TBD_PRO_002, Profile - Verify If User Can Create TSA From Profile");
        }
    }
    /**
     * Method to verify If User Can Update Profile Picture
     * @throws Exception
     */
    public void verifyIfUserCanUpdateProfilePicture_TBD_PRO_003() throws Exception {
        HeaderChildNode("TBD_PRO_003, Login - verify User can update profile picture");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon), "Profile Icon");
        if(verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objProfilePicture))) {
            verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfilePicture), "Profile Picture");
        } else {
            verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfilePicture4), "Profile Picture");
        }
        if(verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objPickFromYourGallery))) {
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objPickFromYourGallery), "Pick from Your Gallery");
        } else {
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objPickFromGallery), "Pick from Your Gallery");
        }
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objTakeAPhoto), "Take a Photo");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objSwitchCameraButton), "Switch camera Button");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objCameraCloseButton), "Camera Close Button");
        if (verifyElementDisplayed(ProfilePage.getByOSType(platform, ProfilePage.objTakeAPhoto))) {
            click(ProfilePage.getByOSType(platform, ProfilePage.objTakeAPhoto), "Take a Photo");
        } else {
            if(verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objProfilePicture))) {
                verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfilePicture), "Profile Picture");
            } else {
                verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfilePicture4), "Profile Picture");
            }
            verifyElementDisplayed(ProfilePage.getByOSType(platform, ProfilePage.objTakeAPhoto));
            verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objTakeAPhoto), "Take a Photo");
        }
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objSwitchCameraButton), "Switch camera Button");
        waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objShutterButton), 10, "Shutter Button");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objShutterButton), "Shutter Button");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objDoneButton), "Done Button");
        if(verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objProfilePicture))) {
            click(ProfilePage.getByOSType(platform, ProfilePage.objProfilePicture), "Profile Picture");
        } else {
            click(ProfilePage.getByOSType(platform, ProfilePage.objProfilePicture4), "Profile Picture");
        }
        if(verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objPickFromYourGallery))) {
            verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objPickFromYourGallery), "Pick from Your Gallery");
        } else {
            verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objPickFromGallery), "Pick from Your Gallery");
        }
        if (platform.equalsIgnoreCase("Android")) {
            click(ProfilePage.getByOSType(platform, ProfilePage.objGallery), "Gallery");
            if(verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objGalleryAlbums))){
                click(ProfilePage.getByOSType(platform, ProfilePage.objGalleryAlbums), "Albums");
            }
            click(ProfilePage.getByOSType(platform, ProfilePage.objAllPhotos), "All Photos");
        } else {
            click(ProfilePage.getByOSType(platform, ProfilePage.objAlbumIOS), "Album");
        }
        click(ProfilePage.getByOSType(platform, ProfilePage.objSelectPhotoInFolder), "Select Photo");
        if (platform.equalsIgnoreCase("iOS")) {
            click(ProfilePage.getByOSType(platform, ProfilePage.objSelectPhotoInRecents), "Select Photo");
        }
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objUpdatedProfilePicture), "Uploaded Profile Picture");
        logger.info("TBD_PRO_003, Profile - verify User can update profile picture");
        extentLoggerPass("TBD_PRO_003", "TBD_PRO_003, Profile - verify User can update profile picture");
    }
    /**
     * Method to verify user can update Profile Name
     * @throws Exception
     */
    public void verifyUserCanUpdateProfileName_TDB_PRO_004() throws Exception {
        HeaderChildNode("verify user can update the profile name");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon), "Profile icon");
        waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objProfileHeader),10,"Header:profile Header");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objProfileHeader), " : profile header");
        assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objProfileHeader)), propertyFileReader("ProfileHeader", "Profile"), ":Profile Header");
        String currentProfileName = getText(ProfilePage.getByOSType(platform, ProfilePage.objProfileName));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfileName), "Profile name");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objHowShouldWeCallYouScreen), " : How should we call you screen");
        assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objHowShouldWeCallYouScreen)), propertyFileReader("HowShouldWeCallYou", "Profile"), ":How should we call you");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objNicknameTextField), "Nickname text field");
        clearField(ProfilePage.getByOSType(platform, ProfilePage.objNicknameTextField), "NickName Text field");
        type(ProfilePage.getByOSType(platform, ProfilePage.objNicknameTextField), propertyFileReader("NumericNumber", "Profile"), "text_field");
        if (platform.equalsIgnoreCase("iOS")) {
            click(ProfilePage.getByOSType(platform, ProfilePage.objKeyboardDoneBtn), "Keyboard Done Button");
        } else {
            hideKeyboard();
        }
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objConfirmNickNameButton), "Confirm Nickname Button");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objInlineErrorMessage), "Inline error message");
        if (platform.equalsIgnoreCase("Android")) {
            clearField(ProfilePage.getByOSType(platform, ProfilePage.objNicknameTextField), "NickName Text field");
        } else {
            verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objNicknameTextField), "Nickname text field");
            clearField(ProfilePage.getByOSType(platform, ProfilePage.objNicknameTextField), "NickName Text field");
        }
        type(ProfilePage.getByOSType(platform, ProfilePage.objNicknameTextField), propertyFileReader("SpecialCharacter", "Profile"), "text_field");
        if (platform.equalsIgnoreCase("iOS")) {
            click(ProfilePage.getByOSType(platform, ProfilePage.objKeyboardDoneBtn), "Keyboard Done Button");
        } else {
            hideKeyboard();
        }
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objConfirmNickNameButton), "Confirm Nickname Button");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objInlineErrorMessage), "Inline error message");
        if (platform.equalsIgnoreCase("Android")) {
            clearField(ProfilePage.getByOSType(platform, ProfilePage.objNicknameTextField), "NickName Text field");
        } else {
            verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objNicknameTextField), "Nickname text field");
            clearField(ProfilePage.getByOSType(platform, ProfilePage.objNicknameTextField), "NickName Text field");
        }
        type(ProfilePage.getByOSType(platform, ProfilePage.objNicknameTextField), propertyFileReader("MoreThan", "Profile"), "text_field");
        if (platform.equalsIgnoreCase("iOS")) {
            click(ProfilePage.getByOSType(platform, ProfilePage.objKeyboardDoneBtn), "Keyboard Done Button");
        } else {
            hideKeyboard();
        }
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objConfirmNickNameButton), "Confirm Nickname Button");
        String moreThanProfileName = getText(ProfilePage.getByOSType(platform,ProfilePage.objProfileName));
        if (!Objects.equals(moreThanProfileName, propertyFileReader("MoreThan", "Profile"))) {
            logger.info("TBD_PRO_004, Profile - Allows only 3-30 Characters");
            extentLoggerPass("TBD_PRO_004", "TBD_PRO_004, Profile - Allows only 3-30 Characters");
        } else {
            logger.error("TBD_PRO_004, Profile - fails To Allows only 3-30 Characters");
            extentLoggerFail("TBD_PRO_004", "TBD_PRO_004, Profile - Fails To Allows only 3-30 Characters");
        }
        waitTime(3000);
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform,ProfilePage.objProfileName), "Profile name");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform,ProfilePage.objNicknameTextField), "Nickname text field");
        if (platform.equalsIgnoreCase("Android")) {
            clearField(ProfilePage.getByOSType(platform, ProfilePage.objNicknameTextField), "NickName Text field");
        } else {
            verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objNicknameTextField), "Nickname text field");
            clearField(ProfilePage.getByOSType(platform, ProfilePage.objNicknameTextField), "NickName Text field");
        }
        type(ProfilePage.getByOSType(platform, ProfilePage.objNicknameTextField), propertyFileReader("ValidProfile", "Profile"), "text_field");
        if (platform.equalsIgnoreCase("iOS")) {
            click(ProfilePage.getByOSType(platform, ProfilePage.objKeyboardDoneBtn), "Keyboard Done Button");
        } else {
            hideKeyboard();
        }
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objConfirmNickNameButton), "Confirm Nickname Button");
        if (!Objects.equals(moreThanProfileName, currentProfileName)) {
            logger.info("TBD_PRO_004, Profile - verify user can update the profile name is passed");
            extentLoggerPass("TBD_PRO_004", "TBD_PRO_004, Profile - verify user can update the profile name is passed");
        }
    }
    /**
     * Method to Verify if user can copy the account details
     * @throws Exception
     */
    public void verifyIfUserCanCopyTheAccountDetails_TBD_PRO_005() throws Exception {
        HeaderChildNode("TBD_PRO_005, Login - Verify if user can copy the Account details");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon), "Profile Icon");
        waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objProfileHeader), 10, "Header:profile Header");
        verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objBankNameOption));
        accountInformationUIValidation();
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objBankNameCopyIcon), "Bank Name Copy Icon");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objCopiedToYourClipBoard), "Copied To your Clip Board toast Message");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objAccountNumberCopyIcon), "Account Number Copy Icon");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objCopiedToYourClipBoard), "Copied To your Clip Board toast Message");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objAccountHolderCopyIcon), "Account Number Copy Icon");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objCopiedToYourClipBoard), "Copied To your Clip Board toast Message");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objBackIcon), "Back Icon In account Information Page");
        if(verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objProfilePicture))) {
            logger.info("TBD_PRO_005, Profile - Verify if user can copy the Account details_TBD_PRO_005 is passed");
            extentLoggerPass("TBD_PRO_005", "TBD_PRO_005, Profile - Verify if user can copy the Account details_TBD_PRO_005 is passed");
        } else if (verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objProfilePicture4), "Profile Picture")) {
            logger.info("TBD_PRO_005, Profile - Verify if user can copy the Account details_TBD_PRO_005 is passed");
            extentLoggerPass("TBD_PRO_005", "TBD_PRO_005, Profile - Verify if user can copy the Account details_TBD_PRO_005 is passed");
        }
    }
    /**
     * Method to varify if user can view the personal details
     * @throws Exception
     */
    public void verifyIfUserCanViewThePersonalDetails_TBD_PRO_006() throws Exception {
        HeaderChildNode("TBD_PRO_006, Login - Verify if user can view the Personal Details");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon), "Profile Icon");
        waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objPersonalDetails), 10, "Personal Details");
        waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objContactInformation), 10, "Contact Information");
        verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objMobileNumber));
        click(ProfilePage.getByOSType(platform, ProfilePage.objContactInformation), "Contact Information");
        waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objContactInfoHeader), 10, "Contact info Header");
        String mobileNumber = getText(ProfilePage.getByOSType(platform, ProfilePage.objMobileNumber)).replace("+", "").replace(" ", "");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objBackIcon), "Back Icon");
        waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objPersonalDetails), 10, "Personal details");
        personalDetailsScreenUIValidation();
        String userID = selectQuery("selectQuerycustomer", "SELECT user_id FROM customer.tdbk_customer_mtb where mobile_no= '" + mobileNumber + "';");
        String firstNameOnDB = selectQuery("selectQuerycustomer", "SELECT first_nm FROM customer.tdbk_cust_profile_mtb where user_id= '" + userID + "';");
        assertionValidation(firstName, firstNameOnDB, "First Name");
        String middleNameOnDB = selectQuery("selectQuerycustomer", "SELECT middle_nm FROM customer.tdbk_cust_profile_mtb where user_id= '" + userID + "';");
        assertionValidation(middleName, middleNameOnDB, "Middle Name");
        String lastNameOnDB = selectQuery("selectQuerycustomer", "SELECT last_nm FROM customer.tdbk_cust_profile_mtb where user_id= '" + userID + "';");
        assertionValidation(lastName, lastNameOnDB, "Last Name");
        String genderOnDB = selectQuery("selectQuerycustomer", "SELECT gender FROM customer.tdbk_customer_mtb where user_id= '" + userID + "';");
        assertionValidation(gender, genderOnDB, "Gender");
        String dateOfBirthOnDB = selectQuery("selectQuerycustomer", "SELECT dob FROM customer.tdbk_customer_mtb where user_id= '" + userID + "';");
        assertionValidation(dateOfBirth, dateOfBirthOnDB, "Date Of birth");
        String placeOfBirthOnDB = selectQuery("selectQuerycustomer", "SELECT birthplace FROM customer.tdbk_customer_mtb where user_id= '" + userID + "';");
        assertionValidation(placeOfBirth, placeOfBirthOnDB, "Place of Birth");
        String nationalityOnDB = selectQuery("selectQuerycustomer", "SELECT nationality FROM customer.tdbk_customer_mtb where user_id= '" + userID + "';");
        //comment in the meantime to find column in table for nationality
        assertionValidation(nationality, nationalityOnDB, "Nationality");
        String iDNumberOnDB = selectQuery("selectQuerycustomer", "SELECT national_id FROM customer.tdbk_customer_mtb where user_id= '" + userID + "';");
        assertionValidation(iDNumber, iDNumberOnDB, "ID Number");
//        verifyLinkTextAndClick(ProfilePage.getByOSType(platform, ProfilePage.objContactTonicCustomerCare), "Contact Tonik Customer Care Hyper Link");
        if(!verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objPersonalDetailsHeader))) {
            verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objBackIcon), "Back Icon");
        }
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objPersonalDetailsHeader), "Header: Personal Details");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objBackIcon), "Back Icon");
        if (verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objProfileHeader), "Header: Profile")) {
            logger.info("TBD_PRO_006, Profile - Verify if user can view the Personal Details is passed");
            extentLoggerPass("TBD_PRO_006", "TBD_PRO_006, Profile - Verify if user can view the Personal Details_TBD_PRO_006 is passed");
        }
    }
    /**
     * Method to verify if user can view the contact information
     * @throws Exception
     */
    public void verifyIfUserCanViewTheContactInformation_TBD_PRO_007() throws Exception {
        HeaderChildNode("TBD_PRO_007, Profile -Verify if user can view the contact information");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon), "Profile Icon");
        waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objProfileHeader), 10, "Profile Header");
        verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objMailingAddress));
        contactInformationScreenUIValidation();
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objBackIcon), "Back Icon In Contact Information Page");
        if (verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objProfileHeader), "Header: Profile")) {
            logger.info("TBD_PRO_007, Profile - Verify if user can view the contact information is passed");
            extentLoggerPass("TBD_PRO_007", "TBD_PRO_007, Profile - Verify if user can view the contact information_TBD_PRO_007 is passed");
        }
    }
    /**
     * Method to verify if user can update the mailing address
     * @throws Exception
     */
    public void verifyIfUserCanUpdateTheMailingAddress_TBD_PRO_008() throws Exception {
        HeaderChildNode("TBD_PRO_008, Profile -Verify if user can update the Mailing Address");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon), "Profile Icon");
        waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objProfileHeader), 10, "Header:profile Header");
        verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objMailingAddress));
        contactInformationScreenUIValidation();
        mailingAddressScreenUIValidation();
        swipe("DOWN", 1);
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objZipCodeTextBox), "zipcode text box");
        clearField(ProfilePage.getByOSType(platform, ProfilePage.objZipCodeTextBox), "zipcode text box");
        type(ProfilePage.getByOSType(platform, ProfilePage.objZipCodeTextBox), propertyFileReader("InvalidZipcode", "Profile"), "invalid zip code");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objApplyZipCode), "Apply Zip code");
        assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objEnterValidZipCode)), propertyFileReader("InlineErrorMessage", "Profile"), ":Please Enter a valid Zipcode");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objZipCodeTextBox), "zipcode text box");
        type(ProfilePage.getByOSType(platform, ProfilePage.objZipCodeTextBox), propertyFileReader("ValidZipcode", "Profile"), "Valid zip code");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objApplyZipCode), "Apply Zip code");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objProvince), ": province");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objCityMuncipality), ":City/Municipality");
        swipe("UP", 1);
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objBarangayTextField), "Barangay Text field");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objFindYourBarangay), ":Header : find Your Barangay");
        List<WebElement> barangay = DriverManager.getAppiumDriver().findElements(ProfilePage.getByOSType(platform, ProfilePage.objBarangayName));
        for (int count = 0; count < barangay.size(); count++) {
            String barangayName = barangay.get(count).getText();
            logger.info("The barangay name is :" + barangayName);
        }
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objBarangaySearchField), ":Header : find Your Barangay Search field");
        type(ProfilePage.getByOSType(platform, ProfilePage.objBarangaySearchField), propertyFileReader("PostProperNorthside", "Profile"), "POST PROPER NORTHSIDE");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objSearchedBarangay), ":Searched Barangay");
        clearField(ProfilePage.getByOSType(platform, ProfilePage.objHouseUnitFloor), "House unit floor");
        type(ProfilePage.getByOSType(platform, ProfilePage.objHouseUnitFloor), propertyFileReader("HouseUnitFloor", "Profile"), "House unit floor");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objConfirmButton), ":Confirm Button");
        if (verifyElementDisplayed(ProfilePage.getByOSType(platform, ProfilePage.objEmailAddress))) {
            logger.info("TBD_PRO_008, Profile - Verify if user can update the Mailing Address");
            extentLoggerPass("TBD_PRO_008", "TBD_PRO_008, Profile -Verify if user can update the Mailing Address is passed");
        }
    }
    /**
     * Method to verify if user can update the mobile number
     * @throws Exception
     */
    public void verifyIfUserCanUpdateTheMobileNumber_TBD_PRO_009() throws Exception {
        HeaderChildNode("TBD_PRO_009, Profile -Verify if user can update the Mobile Number");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon), "Profile Icon");
        waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objContactInformation), 10, "Contact Information");
        verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objMailingAddress));
        if (verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objContactInformation), "Contact Information")) {
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objContactInfoHeader), getTextVal(ProfilePage.getByOSType(platform, ProfilePage.objContactInfoHeader), ": Header : Contact Info"));
            assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objContactInfoHeader)), propertyFileReader("HeaderOfContactInfo", "Profile"), ": Header : Contact Info");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objMailingAddress), getTextVal(ProfilePage.getByOSType(platform, ProfilePage.objMailingAddress), ": Mailing Address"));
            assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objMailingAddress)), propertyFileReader("MailingAddress", "Profile"), ": Mailing Address");
        }
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objMobileNumberHeader), "Mobile Number Header");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objYouNeedToContactTonikCustomer), "You Need To contact Tonik Customer care");
        assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objYouNeedToContactTonikCustomer)), propertyFileReader("YouNeedToContactTonikCustomerCare", "Profile"), ":You Need To Contact Tonik Customer Care");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objContactTonikButton), "Contact Tonik Button");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objGoBackButtonOnPopup), "Go Back Button");
        click(ProfilePage.getByOSType(platform, ProfilePage.objContactTonikButton), "Contact Tonik Button");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objPageHeader), "Get In Touch");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform, LoginPage.objBackIcon), "Mobile Number Header");
        if (verifyElementDisplayed(ProfilePage.getByOSType(platform, ProfilePage.objContactInfoHeader))) {
            logger.info("TBD_PRO_009, Profile - Verify if user can update the Mobile Number");
            extentLoggerPass("TBD_PRO_009", "TBD_PRO_009, Profile -Verify if user can update the Mobile Number is passed");
        }
    }
    /**
     * Method to verify if user can update the mailing address
     * @throws Exception
     */
    public void verifyIfUserCanUpdateTheEmailAddress_TBD_PRO_010() throws Exception {
        HeaderChildNode("TBD_PRO_010, Profile -Verify if user can update the Email address");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon), "Profile Icon");
        waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objProfileHeader), 10, "Header:profile Header");
        verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objMailingAddress));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objContactInformation), "Contact Information");
        if (verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objContactInfoText))) {
            waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objContactInfoHeader), 5, "Contact Info header");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objContactInfoHeader), getTextVal(ProfilePage.getByOSType(platform, ProfilePage.objContactInfoHeader), ": Header : Contact Info"));
            containsValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objContactInfoHeader)), propertyFileReader("HeaderOfContactInfo", "Profile"), ": Header : Contact Info");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objUpdatedCommunicationAddress), ":Updated Communication Address");
        } else {
            verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objContactInformation), "Contact Information");
            waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objContactInfoHeader), 5, "Contact Info header");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objContactInfoHeader), getTextVal(ProfilePage.getByOSType(platform, ProfilePage.objContactInfoHeader), ": Header : Contact Info"));
            containsValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objContactInfoHeader)), propertyFileReader("HeaderOfContactInfo", "Profile"), ": Header : Contact Info");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objUpdatedCommunicationAddress), ":Updated Communication Address");
        }
        String currentEmailAddress = getText(ProfilePage.getByOSType(platform, ProfilePage.objEmailAddress));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objVerifyNow), getTextVal(ProfilePage.getByOSType(platform, ProfilePage.objVerifyNow), ":Verify Now"));
        assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objVerifyNow)), propertyFileReader("VerifyNow", "Profile"), ":Verify Now");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objPopup), ":Verify Now Email popup");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objVerifyNowButtonOnPopup), ":Verify Now Button on Email popup");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objUpdateYourEmailButtonOnEmailPopup), ":Update your Button on Email popup");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objPickANewEmail), ":Pick A New Email Header");
        containsValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objPickANewEmail)), propertyFileReader("PickANewEmail", "Profile"), ":Pick A New Email");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objEmailAddressEditTextField), "Email Address Edit Text Field");
        clearField(ProfilePage.getByOSType(platform, ProfilePage.objEmailAddressEditTextField), "Email Address Edit Text Field");
        type(ProfilePage.getByOSType(platform, ProfilePage.objEmailAddressField), propertyFileReader("InvalidEmail", "Profile"), "Enter Invalid Email Address");
        if (platform.equalsIgnoreCase("iOS")) {
            click(ProfilePage.getByOSType(platform, ProfilePage.objKeyboardDoneBtn), "Keyboard Done Button");
        } else {
            hideKeyboard();
        }
        verifyElementPresentAndClick(LoginPage.getByOSType(platform, LoginPage.objNextBtn), getTextVal(LoginPage.getByOSType(platform, LoginPage.objNextBtn), ": Button"));
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objErrorMessageInEmailAddressField), "Inline Error Message In Email Address Field");
        click(ProfilePage.getByOSType(platform, ProfilePage.objEmailAddressEditTextField), "Email Address Edit Text Field");
        clearField(ProfilePage.getByOSType(platform, ProfilePage.objEmailAddressEditTextField), "Email Address Edit Text Field");
        String email = generateRandomEmail();
        type(ProfilePage.getByOSType(platform, ProfilePage.objEmailAddressField), email, "Email Address Edit Text Field");
        if (platform.equalsIgnoreCase("iOS")) {
            click(ProfilePage.getByOSType(platform, ProfilePage.objKeyboardDoneBtn), "Keyboard Done Button");
        } else {
            hideKeyboard();
        }
        verifyElementPresentAndClick(LoginPage.getByOSType(platform, LoginPage.objNextBtn), getTextVal(LoginPage.getByOSType(platform, LoginPage.objNextBtn), ": Button"));
        waitForElementToBePresent(ProfilePage.getByOSType(platform,ProfilePage.objOTPText), 10, "OTP screen");
        verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objVerifyYourEmailSubTitle));
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objVerifyYourEmail), ":Verify Your Email Header");
        containsValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objVerifyYourEmail)), propertyFileReader("VerifyYourEmail", "Profile"), ":Verify Your Email");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objVerifyYourEmailSubTitle), ":Verify Your Email Header SubTitle");
        enterOTP(generateRandomOTP());
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objOTPNotMatchingPopup), ":OTP Not Matching popup");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objOKButton), ":OK Button On popup");
        String otp = selectQuery("selectQuerycustomer", "SELECT otp FROM customer.tdbk_email_otp_track where email_id= '" + email + "';");
        enterOTP(otp);
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objNooiceScreen), ":Nooice screen");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objThanksForVerifying), ":Nooive screen description");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objCloseButton), ":Close Button");
        waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objEmailAddress), 10, "Email address");
        String updatedEmailAddress = getText(ProfilePage.getByOSType(platform, ProfilePage.objEmailAddress));
        containsValidation(email, updatedEmailAddress, "Email address");
        if (!Objects.equals(currentEmailAddress, updatedEmailAddress)) {
            logger.info("TBD_PRO_010, Profile -Verify if user can update the Email address");
            extentLoggerPass("TBD_PRO_010", "TBD_PRO_010, Profile -Verify if user can update the Email address");
        }
    }
    /**
     * Method to Verify if user can view the additional information
     * @throws Exception
     */
    public void verifyIfUserCanViewAdditionalInformation_TBD_PRO_011() throws Exception {
        HeaderChildNode("TBD_PRO_011, Profile -Verify if user can view Additional Information");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon), "Profile Icon");
        waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objAdditionalInformation), 10, "Additional information");
        verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objCurrentEmploymentStatus));
        verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objAdditionalInformationSubTitle));
        additionalInformationScreenUIValidation();
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "BAck icon");
        if (verifyElementDisplayed(ProfilePage.getByOSType(platform, ProfilePage.objProfileHeader))) {
            logger.info("TBD_PRO_011, Profile - Verify if user can view Additional Information");
            extentLoggerPass("TBD_PRO_011", "TBD_PRO_011, Profile -Verify if user can view Additional Information is passed");
        }
    }
    /**
     * Method to verify if user can edit current employment status
     * @throws Exception
     */
    public void verifyIfUserCanEditCurrentEmploymentStatus_TBD_PRO_012() throws Exception {
        HeaderChildNode("TBD_PRO_012, Profile -Verify if user can edit Current employment status and purpose of opening account");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon), "Profile Icon");
        waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objAdditionalInformation), 10, "Additional information");
        verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objCurrentEmploymentStatus));
        verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objAdditionalInformationSubTitle));
        additionalInformationScreenUIValidation();
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objCurrentEmploymentStatusLockIcon), "current employment lock icon");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objLockIconPopup), "Popup");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objOkayButtonOnLockIconPopup), "Okay button on popup");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objCurrentEmploymentStatusLockIcon), "current employment lock icon");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objLockIconPopup), "Popup");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objContactTonikCustomerCareButtonOnLockIconPopup), "Contact tonik customer care button");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objBackIcon), "BAck icon");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objPurposeOfOpeningAnAccountLockIcon), "Purpose of opening an account lock icon");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objLockIconPopup), "Popup");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objOkayButtonOnLockIconPopup), "Okay button on popup");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objCurrentEmploymentStatusLockIcon), "current employment lock icon");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objLockIconPopup), "Popup");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objContactTonikCustomerCareButtonOnLockIconPopup), "Contact tonik customer care button");
        if (verifyElementDisplayed(ProfilePage.getByOSType(platform, ProfilePage.objPageHeader))) {
            logger.info("TBD_PRO_012, Profile - Verify if user can edit Current employment status");
            extentLoggerPass("TBD_PRO_012", "TBD_PRO_0012, Profile -Verify if user can edit Current employment status is passed");
        }
    }
    /**
     * Method to verify if user can edit purpose of opening account
     * @throws Exception
     */
    public void verifyIfUserCanEditPurposeOfOpeningAccountTBD_PRO_15() throws Exception {
        HeaderChildNode("TBD_PRO_012 and TBD_PRO_015 , Profile -Verify if user can edit Current employment status and purpose of opening account");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon), "Profile Icon");
        waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objAdditionalInformation), 10, "Additional information");
        verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objCurrentEmploymentStatus));
        verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objAdditionalInformationSubTitle));
        additionalInformationScreenUIValidation();
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objPurposeOfOpeningAnAccountLockIcon), "Purpose of opening an account lock icon");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objLockIconPopup), "Popup");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objOkayButtonOnLockIconPopup), "Okay button on popup");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objCurrentEmploymentStatusLockIcon), "current employment lock icon");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objLockIconPopup), "Popup");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objContactTonikCustomerCareButtonOnLockIconPopup), "Contact tonik customer care button");
        if (verifyElementDisplayed(ProfilePage.getByOSType(platform, ProfilePage.objPageHeader))) {
            logger.info("TBD_PRO_015, Profile - Verify if user can edit purpose of opening account");
            extentLoggerPass("TBD_PRO_015", "TBD_PRO_015, Profile -Verify if user can edit purpose of opening account is passed");
        }
    }
    /**
     * Method to verify if user can edit the name of the company
     * @throws Exception
     */
    public void verifyIfUserCanEditTheNameOfTheCompanyTBD_PRO_13() throws Exception {
        HeaderChildNode("TBD_PRO_013, Profile -Verify if user can edit the Name of the company and monthly income and Update the Additional Information");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon), "Profile Icon");
        waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objAdditionalInformation), 10, "Additional information");
        verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objCurrentEmploymentStatus));
        verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objAdditionalInformationSubTitle));
        additionalInformationScreenUIValidation();
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objNameOfTheCompany), "Name of the company edit text box");
        type(ProfilePage.getByOSType(platform, ProfilePage.objNameOfTheCompanyTextBox), propertyFileReader("NameOfTheCompany", "Profile"), "Name Of the Company Text Box");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objNameOfTheCompany), "Name of the company edit text box");
        type(ProfilePage.getByOSType(platform, ProfilePage.objNameOfTheCompanyTextBox), propertyFileReader("NameOfTheCompany1", "Profile"), "Name Of the Company Text Box");
        if (platform.equalsIgnoreCase("iOS")) {
            click(ProfilePage.getByOSType(platform, ProfilePage.objKeyboardDoneBtn), "Keyboard Done Button");
        } else {
            hideKeyboard();
        }
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objMonthlyIncome), "Monthly income edit Text Field");
        clearField(ProfilePage.getByOSType(platform, ProfilePage.objMonthlyIncome), "Monthly incomeText field");
        type(ProfilePage.getByOSType(platform, ProfilePage.objMonthlyIncome), propertyFileReader("UpdatedMonthlyIncome", "Profile"), "updated Monthly income");
        assertionValidation(getAttributValue("enabled", (ProfilePage.getByOSType(platform, ProfilePage.objUpdateButton))), "true", ": Enable Attribute value");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objNameOfTheCompany), "Name of the company edit text box");
        type(ProfilePage.getByOSType(platform, ProfilePage.objNameOfTheCompany), propertyFileReader("NameOfTheCompany50", "Profile"), "Name Of the Company");
        assertionValidation(getAttributValue("enabled", (ProfilePage.getByOSType(platform, ProfilePage.objUpdateButton))), "true", ": Enable Attribute value");
        int companyName = 0;
        if (platform.equalsIgnoreCase("Android")) {
            companyName = getText(ProfilePage.getByOSType(platform, ProfilePage.objNameOfTheCompany)).length();
        } else {
            companyName = getText(ProfilePage.getByOSType(platform, ProfilePage.objNameOfTheCompanyTextBox)).length();
        }
        if (companyName == 50) {
            logger.info("Maximum input value for company name is 50 Character validated");
            extentLoggerPass("Card numbers", "Maximum input value for company nameis 50 Character validated");
        } else {
            logger.info("Maximum input value for company name 50 Character is not validated");
            extentLoggerFail("", "Maximum input value for company name 50 Character is not validated");
        }
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objNameOfTheCompany), "Name of the company edit text box");
        clearField(ProfilePage.getByOSType(platform, ProfilePage.objMonthlyIncome), "Monthly incomeText field");
        type(ProfilePage.getByOSType(platform, ProfilePage.objNameOfTheCompany), propertyFileReader("NameOfTheCompany", "Profile"), "Name Of the Company");
        if (platform.equalsIgnoreCase("iOS")) {
            click(ProfilePage.getByOSType(platform, ProfilePage.objKeyboardDoneBtn), "Keyboard Done Button");
        } else {
            hideKeyboard();
        }
        logger.info("TBD_PRO_013, Profile - Verify if user can edit the Name of the company");
        extentLoggerPass("TBD_PRO_0013", "TBD_PRO_0013, Profile -Verify if user can edit the Name of the company is passed");
        hideKeyboard();
        logger.info("TBD_PRO_013, Profile - Verify if user can edit the Name of the company");
        extentLoggerPass("TBD_PRO_0013", "TBD_PRO_0013, Profile -Verify if user can edit the Name of the company is passed");
    }
    /**
     * Method to verify if user can edit the monthly income
     * @throws Exception
     */
    public void verifyIfUserCanEdiTheMonthlyIncome_TBD_PRO_14() throws Exception {
        HeaderChildNode("TBD_PRO_014, Profile -Verify if user can edit the monthly income");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon), "Profile Icon");
        waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objAdditionalInformation), 10, "Additional information");
        verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objCurrentEmploymentStatus));
        verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objAdditionalInformationSubTitle));
        additionalInformationScreenUIValidation();
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objMonthlyIncome), "Monthly income");
        clearField(ProfilePage.getByOSType(platform, ProfilePage.objMonthlyIncomeTextBox), "Monthly Income Less than 100");
        if (platform.equalsIgnoreCase("Android")) {
            click(ProfilePage.getByOSType(platform, ProfilePage.objMonthlyIncome), "Keyboard Done Button");
        }
        type(ProfilePage.getByOSType(platform, ProfilePage.objMonthlyIncome), propertyFileReader("LessMonthlyIncome", "Profile"), "Monthly Income Less than 100");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objGraydoutUpdateButton), "Graydout Update Button");
        if (platform.equalsIgnoreCase("iOS")) {
            verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objNameOfTheCompany), "Click on the Screen");
            verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objKeyboardDoneBtn), "Keyboard Done Button");
        } else {
            verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objNameOfTheCompany), "Click on the Screen");
        }
        if (platform.equalsIgnoreCase("iOS")) {
            verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objNameOfTheCompany), "Click on the Screen");
            click(ProfilePage.getByOSType(platform, ProfilePage.objKeyboardDoneBtn), "Keyboard Done Button");
        } else {
            hideKeyboard();
        }
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objInlineErrorMessageForMonthlyIncome), "Inline Error Message for less than monthly income");
        containsValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objInlineErrorMessageForMonthlyIncome)), propertyFileReader("MonthlyIncomeInlineErrorMessage", "Profile"), ":Monthly income inline error message");
        if (platform.equalsIgnoreCase("Android")) {
            click(ProfilePage.getByOSType(platform, ProfilePage.objMonthlyIncome), "Keyboard Done Button");
        }
        type(ProfilePage.getByOSType(platform, ProfilePage.objMonthlyIncome), propertyFileReader("MoreMonthlyIncome", "Profile"), "Monthly Income Morethan");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objGraydoutUpdateButton), "Graydout Update Button");
        if (platform.equalsIgnoreCase("iOS")) {
            verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objNameOfTheCompany), "Click on the Screen");
            verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objKeyboardDoneBtn), "Keyboard Done Button");
        } else {
            verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objNameOfTheCompany), "Click on the Screen");
        }
        if (platform.equalsIgnoreCase("iOS")) {
            verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objNameOfTheCompany), "Click on the Screen");
            click(ProfilePage.getByOSType(platform, ProfilePage.objKeyboardDoneBtn), "Keyboard Done Button");
        } else {
            hideKeyboard();
        }
        if (verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objInlineErrorMessageForMonthlyIncome), "Inline Error Message for less than monthly income")) {
            logger.info("TBD_PRO_014, Profile - Verify if user can edit the monthly income");
            extentLoggerPass("TBD_PRO_0014", "TBD_PRO_0014, Profile -Verify if user can edit the Monthly income is passed");
        }
    }
    /**
     * Method to verify user can update the additional information
     * @throws Exception
     */
    public void verifyIfUserCanUpdateTheAdditionalInfoTBD_PRO_16() throws Exception {
        HeaderChildNode("TBD_PRO_016, Profile -Verify if user can Update the Additional Information");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon), "Profile Icon");
        waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objAdditionalInformation), 10, "Additional information");
        verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objCurrentEmploymentStatus));
        verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objAdditionalInformationSubTitle));
        additionalInformationScreenUIValidation();
        String currentCompanyName = getText(ProfilePage.getByOSType(platform, ProfilePage.objNameOfTheCompany));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objNameOfTheCompany), "Comapny Name Text Field");
        clearField(ProfilePage.getByOSType(platform, ProfilePage.objNameOfTheCompanyTextBox), "Company name ");
        type(ProfilePage.getByOSType(platform, ProfilePage.objNameOfTheCompanyTextBox), propertyFileReader("UpdatedCompanyName", "Profile"), "updated Company name ");
        if (platform.equalsIgnoreCase("iOS")) {
            verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objKeyboardDoneBtn), "Keyboard Done Button");
        } else {
            hideKeyboard();
        }
        String currentMonthlyIncome = getText(ProfilePage.getByOSType(platform, ProfilePage.objMonthlyIncome));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objMonthlyIncome), "Monthly income edit Text Field");
        clearField(ProfilePage.getByOSType(platform, ProfilePage.objMonthlyIncomeTextBox), "Monthly incomeText field");
        type(ProfilePage.getByOSType(platform, ProfilePage.objMonthlyIncomeTextBox), propertyFileReader("UpdatedMonthlyIncome", "Profile"), "updated Monthly income");
        if (platform.equalsIgnoreCase("iOS")) {
            verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objNameOfTheCompany), "Click on the Screen");
            click(ProfilePage.getByOSType(platform, ProfilePage.objKeyboardDoneBtn), "Keyboard Done Button");
        } else {
            hideKeyboard();
        }
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objUpdateButton), "Update Button");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objAdditionalInformation), "additional Information");
        String updatedCompanyName = "";
        if (platform.equalsIgnoreCase("Android")) {
            updatedCompanyName = getText(ProfilePage.getByOSType(platform, ProfilePage.objNameOfTheCompany));
        } else {
            updatedCompanyName = getText(ProfilePage.getByOSType(platform, ProfilePage.objNameOfTheCompanyTextBox));
        }
        containsValidation(propertyFileReader("UpdatedCompanyName", "Profile"), updatedCompanyName, "Company name");
        if (!Objects.equals(currentCompanyName, updatedCompanyName)) {
            logger.info("User can able to Edit the Company Name Field");
            extentLoggerPass("TBD_PRO_0016", "TBD_PRO_0016, Profile -User can able to Edit the Company Name Field");
        }
        String updatedMonthlyIncome = "";
        if (platform.equalsIgnoreCase("Android")) {
            updatedMonthlyIncome = getText(ProfilePage.getByOSType(platform, ProfilePage.objMonthlyIncome));
        } else {
            updatedMonthlyIncome = getText(ProfilePage.getByOSType(platform, ProfilePage.objMonthlyIncomeTextBox));
        }
        containsValidation(updatedMonthlyIncome, propertyFileReader("UpdatedMonthlyIncome", "Profile"), "Monthly income");
        if (!Objects.equals(currentMonthlyIncome, updatedMonthlyIncome)) {
            logger.info("User can able to Edit the Monthly Income Field");
            extentLoggerPass("TBD_PRO_0016", "TBD_PRO_0016, Profile -User can able to Edit the Monthly Income Field");
        }
    }
    /**
     * Method to verify user can view the "List of login" option
     * @throws Exception
     */
    public void verifyIfUserCanViewTheListOfLoginOptions_TBD_PRO_18() throws Exception {
        HeaderChildNode("TBD_PRO_018, Profile -Verify if user can view the list of Log-in options");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon), "Profile Icon");
        waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objAdditionalInformation), 10, "Additional information");
        verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objCurrentEmploymentStatus));
        swipe("UP", 1);
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objLogInOption), "Log-In Options");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objLogInOptionPageHeader), "Log-In Options page Header");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objResetPasswordOnLoginOptionPage), "Reset Password");
        containsValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objLogInOptionPageHeader)), propertyFileReader("LoginOptionsPageHeader", "Profile"), ":Login Options page header");
        containsValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objResetPasswordOnLoginOptionPage)), propertyFileReader("ResetPassword", "Profile"), "Reset Password");
        containsValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objDefaultLoginOption)), propertyFileReader("DefaultLoginOption", "Profile"), "Default login option");
        click(ProfilePage.getByOSType(platform, ProfilePage.objDefaultLoginOption), "Default login");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objHowWouldYouLikeToLoginPopup), "How would you like to login Popup");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objPasswordOnPopup), "Password On  Popup");
        if (platform.equalsIgnoreCase("iOS")) {
            click(ProfilePage.getByOSType(platform, ProfilePage.objPasswordOnPopupConfirm), "Pop up Confirm");
        }
        verifyElementPresentAndClick(LoginPage.getByOSType(platform, LoginPage.objBackIcon), "Back icon on Login option page");
        if (verifyElementDisplayed(ProfilePage.getByOSType(platform, ProfilePage.objProfileHeader))) {
            logger.info("TBD_PRO_018, Profile - Verify if user can view the list of Log-in options");
            extentLoggerPass("TBD_PRO_018", "TBD_PRO_0018, Profile -Verify if user can view the list of Log-in options is passed");
        }
    }
    /**
     * Method to verify if user can view and update the app information
     * @throws Exception
     */
    public void verifyIfUserCanViewAndCopyTheAppInformation_TBD_PRO_22() throws Exception {
        HeaderChildNode("TBD_PRO_022, Profile -Verify if user can view and Copy the app information");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon), "Profile Icon");
        verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objAppInformation));
        swipe("UP",1);
        waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objAppInformation), 10, "App information");
        appInformationScreenUIValidation();
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objCopyToClipBoard), "Copy To Clip Board");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform, LoginPage.objBackIcon), "Back Button");
        swipe("DOWN", 1);
        if (verifyElementDisplayed(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon))) {
            logger.info("TBD_PRO_022, Profile - Verify if user can view and Copy the app information");
            extentLoggerPass("TBD_PRO_022", "TBD_PRO_0022, Profile -Verify if user can view and Copy the app information is passed");
        }
    }
    /**
     * Method to verify user can Log out of Tonik app
     * @throws Exception
     */
    public void verifyUserCanLogoutOfTonikApp_TBD_LG_0024() throws Exception {
        HeaderChildNode("TBD_LG_024, Profile - Verify if user can Logout of Tonik App");
        tonikLogin(propertyFileReader("password", "Login"));
        if (verifyElementDisplayed(LoginPage.getByOSType(platform,LoginPage.objHeadsUpLuvPopup))) {
            click(LoginPage.getByOSType(platform,LoginPage.objGotItButton), "Got It Button");
        }
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon), "Profile page Icon");
        verifyElementDisplayed(LoginPage.getByOSType(platform,LoginPage.objLogOutButton));
        swipe("UP", 1);
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objLogOutButton), "Logout Button");
        waitTime(2000);
        if (platform.equalsIgnoreCase("Android")) {
            relaunchApp("android");
        } else {
            relaunchApp("IOS");
        }
        tonikLogin(propertyFileReader("password", "Login"));
        if (verifyElementDisplayed(LoginPage.getByOSType(platform,LoginPage.objHeadsUpLuvPopup))) {
            click(LoginPage.getByOSType(platform,LoginPage.objGotItButton), "Got It Button");
        }
        if(verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon), "Profile page Icon")){
            logger.info("TBD_LG_024, Profile- Verify if user can Logout of Tonik App is passed");
            extentLoggerPass("TBD_LG_024", "TBD_LG_024, Profile - Verify if user can Logout of Tonik App is passed");
        }
    }
    /**
     * Method to Verify the profile screen if user is onboarded as BKYC
     * @throws Exception
     */
    public void verifyTheProfileScreenIfUserIsOnboardedAsBKYC_TBD_PRO_025() throws Exception {
        HeaderChildNode("TBD_PRO_025, Profile - Verify the Profile screen if user is onboarded as BKYC");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon), "Profile Icon");
        waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objProfileHeader), 10, "profile header");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objProfileHeader), " : profile header");
        assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objProfileHeader)), propertyFileReader("ProfileHeader", "Profile"), ":Profile Header");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objYourAccountLimitIs), " : Your Account Limit Is");
        assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objYourAccountLimitIs)), propertyFileReader("YourAccountLimitIs", "Profile"), ":Your Account Limit Is");
        assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objGetHigherLimitIs)), propertyFileReader("GetHigherLimits", "Profile"), ":Get Higher Limits");
        assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objAccountInformation)), propertyFileReader("AccountInformation", "Profile"), ":Account Information");
        verifyElementEnabled(ProfilePage.getByOSType(platform, ProfilePage.objPersonalDetails), " : personal Details");
        verifyElementEnabled(ProfilePage.getByOSType(platform, ProfilePage.objContactInformation), " : Contact Information");
        verifyElementEnabled(ProfilePage.getByOSType(platform, ProfilePage.objAdditionalInformation), " : Additional information");
        verifyElementEnabled(ProfilePage.getByOSType(platform, ProfilePage.objRequestBankCertificate), " : Request Bank certificate");
        swipe("UP",1);
        verifyElementEnabled(ProfilePage.getByOSType(platform, ProfilePage.objLogInOption), " : Login Option");
        verifyElementEnabled(ProfilePage.getByOSType(platform, ProfilePage.objAppInformationDisabled), " :App information");
        verifyElementEnabled(ProfilePage.getByOSType(platform, ProfilePage.objResetApp), " : Reset App");
        verifyElementEnabled(ProfilePage.getByOSType(platform, ProfilePage.objLogOut), " : Logout Button");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform, LoginPage.objBackIcon), "Back icon");
        if (verifyElementDisplayed(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon))) {
            logger.info("TBD_PRO_025, Profile - Verify if account holder can access the Profile menu when TSA is not yet created");
            extentLoggerPass("TBD_PRO_025", "TBD_PRO_001, Profile - Verify if account holder can access the Profile menu when TSA is not yet created");
        }
    }
    /**
     * Method to verify if user can reset the app
     * @throws Exception
     */
    public void verifyIfUserCanResetTheApp_TBD_LG_023() throws Exception {
        HeaderChildNode("TBD_LG_024, Profile - Verify if user can Reset the App");
        OnboardingModule onboardingModule = new OnboardingModule();
        OnboardingModuleIOS onboardingModuleIOS=new OnboardingModuleIOS();
        tonikLogin(propertyFileReader("password", "Login"));
        if (verifyElementDisplayed(LoginPage.getByOSType(platform,LoginPage.objHeadsUpLuvPopup))) {
            click(LoginPage.getByOSType(platform,LoginPage.objGotItButton), "Got It Button");
        }
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon), "Profile page Icon");
        verifyElementDisplayed(LoginPage.getByOSType(platform,LoginPage.objLogOutButton));
        swipe("UP",1);
        String BeforeResetAppVersion = getText(ProfilePage.getByOSType(platform, ProfilePage.objAppVersion));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objResetApp), "Reset App");
        if (verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objResetAppPopUp), "Reset App Popup")) {
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objResetAppButton), "Reset App Button on Popup");
            verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objTAkeMeBack), "Take Me Back Button on Popup");
        }
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objResetAppPopUp), "Reset App Popup");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objResetAppButton), "Reset App Button on Popup");
        waitTime(2000);
        if (platform.equalsIgnoreCase("Android")) {
            relaunchApp("android");
        } else {
            relaunchApp("IOS");
        }
        if(platform.equalsIgnoreCase("Android")) {
            onboardingModule.navigateToCreatePage();
        }else{
            onboardingModuleIOS.navigateToCreatePage();
        }
        if(platform.equalsIgnoreCase("Android")) {
            verifyElementPresentAndClick(OnBoardingPage.objLoginHereLink, ": Login Here Link");
        } else {
            verifyElementPresentAndClick(OnBoardingPageiOS.objLoginHereLink, ": Login Here Link");
        }
        assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objEnterYourRegisterMobileNumber)), propertyFileReader("EnterYourRegisterMobileNumber", "Profile"), ":Enter Your register Mobile number");
        assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objTakeAFaceIdentityScan)), propertyFileReader("TakeAFaceIdentityScan", "Profile"), ":Take a Face Identity scan");
        assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objResetYourPassword)), propertyFileReader("RestYourPassword", "Profile"), ":Reset Your Password");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objContinueButton), ":Continue Button");
        if(platform.equalsIgnoreCase("Android")) {
            verifyElementPresentAndClick(OnBoardingPage.objMobileNumberInputField, ":Mobile number input field");
            type(OnBoardingPage.objMobileNumberInputField, propertyFileReader("RegisterMobileNumber", "Profile"), "Mobile number input field");
        } else {
            verifyElementPresentAndClick(OnBoardingPageiOS.objMobileNumberInputField, ":Mobile number input field");
            type(OnBoardingPageiOS.objMobileNumberInputField, propertyFileReader("RegisterMobileNumber", "Profile"), "Mobile number input field");
        }
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(LoginPage.getByOSType(platform,LoginPage.objNextBtn), ": Button"));
        enterOTP(generateRandomOTP());
        if (verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objCreateNewPassword), ":Create new Password")) {
            assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objCreateNewPassword)), propertyFileReader("CreateNewPassword", "Profile"), ":Create New Password");
            String NewPassword = enterNewPassword();
//            propertyFileWriter("password",NewPassword,"Login");
        }
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objYouNowHaveANewPassword), ":You Have a new Password");
        assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objYouNowHaveANewPassword)), propertyFileReader("YouNowHaveANewPassword", "Profile"), ":You Now Have a New Password");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objLoginWithNewPassword), ":Login With New Password");
        tonikLogin(propertyFileReader("password", "Login"));
        if(verifyElementDisplayed(ProfilePage.getByOSType(platform,ProfilePage.objLoginBanner))) {
//            verifyBannerPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objLoginBanner), "Banner Pop-up");
        }
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon), "Profile page Icon");
        verifyElementDisplayed(LoginPage.getByOSType(platform,LoginPage.objLogOutButton));
        swipe("UP",1);
        String AfterResetAppVersion = getText(ProfilePage.getByOSType(platform, ProfilePage.objAppVersion));
        if (Objects.equals(BeforeResetAppVersion, AfterResetAppVersion)) {
            logger.info("TBD_LG_023, Profile- Verify if user can reset the App is passed");
            extentLoggerPass("TBD_LG_023", "TBD_LG_023, Profile - Verify if user can reset the App is passed");
        }
    }
    public void mobileNumberChange() throws Exception {
        HeaderChildNode("Mobile number change");
        tonikLogin(propertyFileReader("password", "Login"));
        click(VirtualCardPage.getByOSType(platform,VirtualCardPage.objVirtualCardOption),getTextVal(VirtualCardPage.getByOSType(platform,VirtualCardPage.objVirtualCardOption),"Card"));
        verifyElementPresentAndClick(VirtualCardPage.getByOSType(platform,VirtualCardPage.objEyeIcon), ": Eye Icon On left side Of the Corner");
        String virtualCardNumber = getText(VirtualCardPage.getByOSType(platform,VirtualCardPage.objUnmaskedNum));
        click(LoginPage.getByOSType(platform, LoginPage.objBackIcon)," Back Button");
        System.out.println(virtualCardNumber);
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon), "Profile Icon");
        waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objProfileHeader), 10, "Profile Header");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objContactInformation), "Contact Information");
        waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objContactInfoHeader), 10, "Contact info Header");
        assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objContactInfoHeader)), propertyFileReader("HeaderOfContactInfo", "Profile"), ": Header : Contact Info");
        assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objMailingAddress)), propertyFileReader("MailingAddress", "Profile"), ": Mailing Address");
        assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objProvideYourCommunicationAddress)), propertyFileReader("ProvideYourCommunicationAddress", "Profile"), ":Provide your communication address for all the communications");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objMobileNumber), ": Mobile Number");
        String mobileNumberBeforeChange = getText(ProfilePage.getByOSType(platform, ProfilePage.objMobileNumber));
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objEmailAddress), ": Email Address");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objRegisteredMobileNumberBtn), getTextVal(ProfilePage.getByOSType(platform, ProfilePage.objRegisteredMobileNumberBtn), "Button"));
        for (int popupMsg = 1; popupMsg < 6; popupMsg++)
            containsValidation(getText(ProfilePage.getByOSType(platform,ProfilePage.objPopup)), propertyFileReader("Popup" + popupMsg, "Profile"), "Popup message");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objNegativeBtn), getTextVal(ProfilePage.getByOSType(platform, ProfilePage.objNegativeBtn), "Button"));
        assertionValidation(getText(ProfilePage.getByOSType(platform, ProfilePage.objContactInfoHeader)), propertyFileReader("HeaderOfContactInfo", "Profile"), ": Header : Contact Info");
        click(ProfilePage.getByOSType(platform, ProfilePage.objRegisteredMobileNumberBtn), getTextVal(ProfilePage.getByOSType(platform, ProfilePage.objRegisteredMobileNumberBtn), "Button"));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objPositiveBtn), getTextVal(ProfilePage.getByOSType(platform, ProfilePage.objPositiveBtn), "Button"));
        click(LoginPage.getByOSType(platform,LoginPage.objEditPassword),"Password field");
        type(LoginPage.getByOSType(platform,LoginPage.objEditPassword),propertyFileReader("password","Login"), "Enter secret code");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objLoginBtn),getTextVal(LoginPage.getByOSType(platform,LoginPage.objLoginBtn),"Button"));
        enterOTP(generateRandomString(6));
        type(OnBoardingPage.objMobileNumberInputField,propertyFileReader("InvalidSameNumber1","onboarding"),"Mobile number input field");
        assertionValidation(getAttributValue("enabled",QuickLoanWithVasPage.getByOSType(platform,QuickLoanWithVasPage.objNextBtn)),"false",": Enable Attribute value");
        clearField(OnBoardingPage.objMobileNumberInputField,"Mobile number input field");
        type(OnBoardingPage.objMobileNumberInputField,propertyFileReader("ExistingTonikAccount","onboarding"),"Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        waitForElementToBePresent(ProfilePage.getByOSType(platform,ProfilePage.objPopup),5,"Already registered popup");
        assertionValidation(getText(ProfilePage.getByOSType(platform,ProfilePage.objPopup)), propertyFileReader("AlreadyRegisteredNumber", "Profile"), "Popup message");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objPositiveBtn), getTextVal(ProfilePage.getByOSType(platform, ProfilePage.objPositiveBtn), "Button"));
        assertionValidation(getText(ProfilePage.getByOSType(platform,ProfilePage.objPageHeader)),propertyFileReader("EnterNewMobileNumber","Profile"),"Page header");
        type(OnBoardingPage.objMobileNumberInputField,propertyFileReader("ExistingTonikAccount","onboarding"),"Mobile number input field");
        verifyElementPresentAndClick(LoginPage.getByOSType(platform,LoginPage.objNextBtn), getTextVal(OnBoardingPage.objNextBtn, ": Button"));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objNegativeBtn), getTextVal(ProfilePage.getByOSType(platform, ProfilePage.objNegativeBtn), "Button"));
        contactUsPageUI();
        click(LoginPage.getByOSType(platform, LoginPage.objBackIcon)," Back Button");
        type(ProfilePage.getByOSType(platform,ProfilePage.objMobileNumberField),propertyFileReader("NewMobileNumber","Profile"), "New Mobile Number field");
        click(ProfilePage.getByOSType(platform,ProfilePage.objNextBtn),getTextVal(ProfilePage.getByOSType(platform,ProfilePage.objNextBtn),"Button"));
        enterOTP(generateRandomString(6));
        if(verifyElementPresent(ProfilePage.getByOSType(platform,ProfilePage.objRequestMsg),getTextVal(ProfilePage.getByOSType(platform,ProfilePage.objRequestMsg),"Message"))){
            assertionValidation(getText(ProfilePage.getByOSType(platform,ProfilePage.objRequestMsg)),propertyFileReader("RequestStatus","Profile"),"Request status");
            assertionValidation(getText(ProfilePage.getByOSType(platform,ProfilePage.objRequestDescription)),propertyFileReader("RequestDescription","Profile"),"Request description");
            assertionValidation(getText(ProfilePage.getByOSType(platform,ProfilePage.objRequestMsg)),propertyFileReader("BackToDashboard","Profile"),"Button");
        }
        relaunchApp("Android");
        tonikLogin(propertyFileReader("password", "Login"));
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objProfileIcon), "Profile Icon");
        waitForElementToBePresent(ProfilePage.getByOSType(platform, ProfilePage.objProfileHeader), 10, "Profile Header");
        verifyElementPresentAndClick(ProfilePage.getByOSType(platform, ProfilePage.objContactInformation), "Contact Information");
        verifyElementPresent(ProfilePage.getByOSType(platform, ProfilePage.objMobileNumber), ": Mobile Number");
        String mobileNumberAfterChange = getText(ProfilePage.getByOSType(platform, ProfilePage.objMobileNumber));
        if(!mobileNumberBeforeChange.equalsIgnoreCase(mobileNumberAfterChange)){
            logger.info("Mobile number change - Mobile number changed successfully");
            extentLoggerPass("Mobile number change", "Mobile number change - Mobile number changed successfully");
        }else{
            logger.error("Mobile number change - Mobile number not changed");
            extentLoggerPass("Mobile number change", "Mobile number change - Mobile number not changed");
        }
        click(LoginPage.getByOSType(platform, LoginPage.objBackIcon)," Back Button");
        click(LoginPage.getByOSType(platform, LoginPage.objBackIcon)," Back Button");
        click(VirtualCardPage.getByOSType(platform,VirtualCardPage.objVirtualCardOption),getTextVal(VirtualCardPage.getByOSType(platform,VirtualCardPage.objVirtualCardOption),"Card"));
        verifyElementPresentAndClick(VirtualCardPage.getByOSType(platform,VirtualCardPage.objEyeIcon), ": Eye Icon On left side Of the Corner");
        String virtualCardNumberAfterChange = getText(VirtualCardPage.getByOSType(platform,VirtualCardPage.objUnmaskedNum));
        click(LoginPage.getByOSType(platform, LoginPage.objBackIcon)," Back Button");
        System.out.println(virtualCardNumber);
        if(virtualCardNumber.equalsIgnoreCase(virtualCardNumberAfterChange)){
            logger.info("Mobile number change - Mobile number changed successfully and Virtual card is same");
            extentLoggerPass("Mobile number change", "Mobile number change - Mobile number changed successfully and Virtual card is same");
        }else{
            logger.error("Mobile number change - Mobile number not changed");
            extentLoggerPass("Mobile number change", "Mobile number change - Mobile number not changed");
        }
    }
}