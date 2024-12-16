package com.tonik.regression;

import com.tonik.bizfunctions.*;
import com.driverInstance.AppiumServer;
import com.jcraft.jsch.JSchException;
import com.tonik.utility.Utilities;
import com.tonik.utility.emailReport.SendEmail;
import com.tonik.utility.excel.ExcelUpdate;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import static com.tonik.utility.DB_Utilites.initiateConnections;
import static com.tonik.utility.Utilities.softAssert;
import static com.tonik.utility.excel.ExcelUpdate.xlpath;

public class BaseTest {
	AppiumServer server = new AppiumServer();
	protected BaseClass baseClass;
	protected SendMoneyModule sendMoneyModule;
	protected SILPurpleAppModule silPurpleAppModule;
	protected ContactUsModule contactUsModule;
	protected TopUpModule topUpModule;
	protected ProfileModule profileModule;
	protected LoginModule loginModule;
	protected StashModule stashModule;
	protected OnboardingModuleIOS onboardingModuleIOS;
	protected FriendsWithBenefitsModule friendsWithBenefitsModule;
	protected VirtualCardModule virtualCardModule;
	protected QuickLoanWithVasModule quickLoanWithVasModule;
	protected QuickLoanWithOutVasModule quickLoanWithOutVasModule;
	protected TimeDepositModule timeDepositModule;
	protected ShopInstallmentLoanWithVasModule shopInstallmentLoanWithVasModule;
	protected ShopInstallmentLoanAPIModule shopInstallmentLoanAPIModule;
	protected ShopInstallmentLoanWithoutVasModule shopInstallmentLoanWithoutVasModule;
	protected FlexUpModule flexUpModule;
	protected ReloanModule reloanModule;
	protected OnboardingModule onboardingModule;
	String osName = System.getProperty("os.name").toLowerCase();
	protected BillsPaymentModule billsPaymentModule;
	protected DebitCardModule debitCardModule;
	protected FlexPivotWithoutVASModule flexPivotWithoutVASModule;
	protected FlexPivotWithVASModule flexPivotWithVASModule;
	protected SevenElevenWithoutVASModule sevenElevenWithoutVASModule;
	protected SevenElevenWithVASModule sevenElevenWithVASModule;
	protected TendoStashModule tendoStashModule;
	protected SILAdminAppModule silDuploModule;
  @BeforeSuite()
	public void beforeTest() throws JSchException, SQLException, IOException {
//		initiateConnections("customer");
	  ExcelUpdate.createExcel();
	}
//	//Start Application
	@Parameters({"Platform", "deviceName", "portno"})
	@BeforeMethod
	public void beforeMethod(@Optional String platfrom, @Optional String deviceName, @Optional String potrno) throws Exception {
		String port = AppiumServer.start();
		baseClass = new BaseClass(platfrom, deviceName, port);
		billsPaymentModule = new BillsPaymentModule();
		debitCardModule = new DebitCardModule();
		softAssert = new SoftAssert();
		topUpModule = new TopUpModule();
		profileModule = new ProfileModule();
		loginModule =new LoginModule();
		stashModule =new StashModule();
		contactUsModule =new ContactUsModule();
		friendsWithBenefitsModule =new FriendsWithBenefitsModule();
		sendMoneyModule = new SendMoneyModule();
		silPurpleAppModule = new SILPurpleAppModule();
		onboardingModuleIOS =new OnboardingModuleIOS();
		virtualCardModule = new VirtualCardModule();
		timeDepositModule = new TimeDepositModule();
		shopInstallmentLoanWithVasModule = new ShopInstallmentLoanWithVasModule();
		shopInstallmentLoanAPIModule = new ShopInstallmentLoanAPIModule();
		shopInstallmentLoanWithoutVasModule = new ShopInstallmentLoanWithoutVasModule();
		timeDepositModule = new TimeDepositModule();
		quickLoanWithVasModule = new QuickLoanWithVasModule();
		quickLoanWithOutVasModule = new QuickLoanWithOutVasModule();
		flexUpModule = new FlexUpModule();
		reloanModule = new ReloanModule();
		onboardingModule = new OnboardingModule();
		flexPivotWithoutVASModule = new FlexPivotWithoutVASModule();
		sevenElevenWithoutVASModule = new SevenElevenWithoutVASModule();
		sevenElevenWithVASModule = new SevenElevenWithVASModule();
		flexPivotWithVASModule = new FlexPivotWithVASModule();
		tendoStashModule = new TendoStashModule();
		silDuploModule = new SILAdminAppModule();
	}

	//Stop Application
	@AfterMethod
	public void afterTest() {
		baseClass.tearDown();
	}
	@AfterSuite
	public void afterSuite() throws MessagingException, IOException {
		try {
			// Finalize the Excel file
			if (xlpath != null) {
				System.out.println("Finalizing Excel report: " + xlpath);
				File excelFile = new File(xlpath);

				if (!excelFile.exists()) {
					System.err.println("Excel file not found: " + xlpath);
					return;
				}

				// Attempt to validate or read back the file to ensure integrity
				try (FileInputStream fis = new FileInputStream(xlpath);
					 XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
					System.out.println("Excel file is valid and ready: " + xlpath);
				}
			}
		} catch (Exception e) {
			System.err.println("Error in After Suite: " + e.getMessage());
			e.printStackTrace();
		}

		SendEmail.EmailReport(Utilities.getPlatform());
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
	}
}
