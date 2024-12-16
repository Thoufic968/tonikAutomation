package com.tonik.pageObject;
import org.openqa.selenium.By;

public enum VirtualCardPage {
    objVirtualCardOnBoard("//*[contains(@text,'Virtual Card')]/parent::*/parent::*/parent::*","(//*[@name='card'])[3]","xpath","xpath"),
    objVirtualCardGraydOut("//*[contains(@text,'Virtual Card ->') and @focused='false']","(//*[contains(@name,'Virtual Card ->') and @accessible='false'])[15]","xpath","xpath"),
    objVirtualCardOption("//*[contains(@text,'Virtual Card ->')]","//*[@name='Virtual Card ->']","xpath","xpath"),
    objMaskedNumbers("//android.widget.TextView[contains(@text,'**')]","//*[@name='Virtual Card ->']/following-sibling::*","xpath","xpath"),
    objCardsHeader("com.tonik.mobile:id/Main_title_txt","com.tonik.mobile:id/Main_title_txt","id","id"),
    objVirtualCardImage("//*[contains(@text,'Cards')]/following::android.widget.ImageView[1]","(//*[contains(@name,'com.tonik.mobile:id/Card_Details_View')])[1]","xpath","xpath"),
    objEyeIcon("com.tonik.mobile:id/CARD_EYE_CLICK","//*[contains(@name,'com.tonik.mobile:id/CARD_EYE_CLICK')]","id","xpath"),
    objUnMaskedEyeIcon("com.tonik.mobile:id/CARD_EYE_CLICK","//*[contains(@value,'VALID THRU')]/preceding::XCUIElementTypeOther[6]","id","xpath"),
    objLockCard("//*[contains(@text,'Lock card')]","(//*[@label=' Lock card'])[3]","xpath","xpath"),
    objLockCardToggle("com.tonik.mobile:id/Lock_Card_Toggle_Button","//XCUIElementTypeOther[@name=\"com.tonik.mobile:id/Lock_Card_Toggle_Button\"]","id","xpath"),
    objSecurity("//*[contains(@text,'Security')]","//*[contains(@name,'com.tonik.mobile:id/Security2')]","xpath","xpath"),
    objCardLimits("//*[contains(@text,' Card limits')]","(//*[contains(@label,'Card limits')])[19]","xpath","xpath"),
    objUnmaskedNum("com.tonik.mobile:id/CardsCard_Number_Text","(//*[contains(@value,'VALID THRU')]/preceding::XCUIElementTypeStaticText)[2]","id","xpath"),
    objValidThru("com.tonik.mobile:id/Virtual_Card_Valid_Thru_Text","//XCUIElementTypeStaticText[@name=\"com.tonik.mobile:id/Virtual_Card_Valid_Thru_Text\"]","id","xpath"),
    objCvv("com.tonik.mobile:id/Virtual_Card_CVV_Text","com.tonik.mobile:id/Virtual_Card_CVV_Text","id","id"),
    objLocked("//*[contains(@text,'Locked')]","//*[contains(@value,'Locked')]","xpath","xpath"),
    objSecurityHeader("com.tonik.mobile:id/Main_title_txt","com.tonik.mobile:id/Main_title_txt","id","id"),
    objSecuritySubTitle("//*[@resource-id='com.tonik.mobile:id/Sub_title_txt']","//XCUIElementTypeStaticText[@name=\"com.tonik.mobile:id/Sub_title_txt\"]","xpath","xpath"),
    objOnlinePayments("//*[contains(@text,'Online payments')]","//XCUIElementTypeStaticText[@name=\"Online payments\"]","xpath","xpath"),
    objBlockOnlinePayments("//*[contains(@text,'Block online payments and transactions made using this card automatically.')]","//XCUIElementTypeStaticText[@name=\"com.tonik.mobile:id/List_Text_0\"]","xpath","xpath"),
    objOnlinePaymentsToggle("//android.widget.TextView[contains(@text,'Online payments')]/following-sibling::android.view.ViewGroup[1]","//XCUIElementTypeOther[@name=\"com.tonik.mobile:id/Online paymentsList_Click_\"]","xpath","xpath"),
    objValidThruOption("com.tonik.mobile:id/Cards_Valid_Thru_Text","//XCUIElementTypeStaticText[@name=\"com.tonik.mobile:id/Cards_Valid_Thru_Text\"]","id","xpath"),
    objCvvOption("com.tonik.mobile:id/Cards_CVV_Text","//XCUIElementTypeStaticText[@name=\"com.tonik.mobile:id/Cards_CVV_Text\"]","id","xpath"),
    objCustomerNameOnCard("com.tonik.mobile:id/Card_Name_Text","//XCUIElementTypeStaticText[@name=\"com.tonik.mobile:id/Card_Name_Text\"]","id","xpath"),
    objCardDailyLimitsHeader("com.tonik.mobile:id/Main_title_txt","com.tonik.mobile:id/Main_title_txt","id","id"),
    objOnlineAndPhysicalPaymentsOption("//*[contains(@text,' Online and physical payments')]","//XCUIElementTypeStaticText[@name=\"com.tonik.mobile:id/Online_Physical_Payment_Online and physical payments\"]","xpath","xpath"),
    objDefaultAmount("//android.widget.TextView[contains(@text,'₱')]","(//*[contains(@value,'Online and physical payments')]/following::XCUIElementTypeStaticText)[1]","xpath","xpath"),
    objOnlineAndPhysicalPaymentsHeader("//*[@resource-id='com.tonik.mobile:id/Main_title_txt']","(//*[contains(@value,'Online and physical payments')])[2]","xpath","xpath"),
    objAmountList("//*[contains(@text,'Online and physical payments')]/following-sibling::android.widget.TextView","//XCUIElementTypeOther[@name=\"com.tonik.mobile:id/Online_Physical_Payment List_Click']","xpath","xpath"),
    objSKYCMaximumAmount("com.tonik.mobile:id/Online_Physical_PaymentList_Text_1_6","//XCUIElementTypeOther[@name='₱250,000']","id","xpath"),
    objBKYCMaximumAmount("//*[contains(@text,'Online and physical payments')]/following-sibling::android.widget.TextView[4]","//XCUIElementTypeStaticText[@name=\"Your account limit is ₱50,000 \"]","xpath","xpath");

    private String android;
    private String ios;
    private String andPathType;
    private String iosPathType;

    VirtualCardPage(String android, String ios, String andPathType, String iosPathType) {
        this.android = android;
        this.ios = ios;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }
    VirtualCardPage(String android,  String andPathType, String iosPathType) {
        this.android = android;
        this.ios = android;
        this.andPathType = andPathType;
        this.iosPathType = iosPathType;
    }
    VirtualCardPage(String android,  String andPathType) {
        this.android = android;
        this.ios = android;
        this.andPathType = andPathType;
        this.iosPathType = andPathType;
    }

    public static By getByOSType(String osType, VirtualCardPage objectName) {
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

    public static By objPhysicalPaymentList(String platform, int listElement) {
        if (platform.equalsIgnoreCase("android")) {
            return By.xpath("(//*[contains(@text,'Online and physical payments')]/following::android.widget.TextView)["+listElement+"]");
        } else {
            return By.xpath("//XCUIElementTypeOther[@name=\"com.tonik.mobile:id/Online_Physical_Payment List_Click_["+listElement+"]");
        }
    }
}
