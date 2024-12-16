package com.tonik.regression;
import org.testng.annotations.Test;
import static com.tonik.utility.Utilities.propertyFileReader;
import static com.tonik.utility.Utilities.softAssert;
public class FlexUpScript extends BaseTest {
	@Test(priority = 1)
	public void TDB_FU_001() throws Exception {
		try {
			flexUpModule.verifyLoanTileForFlexUpOffer_TDB_FU_001();
		} catch (AssertionError e) {
			throw e;
		} finally {
			softAssert.assertAll();
		}
	}

	@Test(priority = 2)
	public void TDB_FU_003() throws Exception {
		try {
			flexUpModule.verifyFlexUpSalesScreen_TDB_FU_003();
		} catch (AssertionError e) {
			throw e;
		} finally {
			softAssert.assertAll();
		}
	}

	@Test(priority = 3)
	public void TDB_FU_005() throws Exception {
		try {
			flexUpModule.verifyUserCanAcceptFlexUpOffer_TDB_FU_005();
		} catch (AssertionError e) {
			throw e;
		} finally {
			softAssert.assertAll();
		}
	}

	@Test(priority = 4)
	public void TDB_FU_006() throws Exception {
		try {
			flexUpModule.verifyUserCanAcceptLoanFromLoanSummaryScreen_TDB_FU_006();
		} catch (AssertionError e) {
			throw e;
		} finally {
			softAssert.assertAll();
		}
	}

	@Test(priority = 5)
	public void TDB_FU_007() throws Exception {
		try {
			flexUpModule.verifyUserCanSelectAFlexUpReason_TDB_FU_007();
		} catch (AssertionError e) {
			throw e;
		} finally {
			softAssert.assertAll();
		}
	}

	@Test(priority = 6)
	public void TDB_FU_004() throws Exception {
		try {
			flexUpModule.verifyUserCanDeclineFlexUpOffer_TDB_FU_004();
		} catch (AssertionError e) {
			throw e;
		} finally {
			softAssert.assertAll();
		}
	}

	@Test(priority = 7)
	public void TDB_FU_024() throws Exception {
		try {
			flexUpModule.verifyUserCanDeclineFlexUpOfferOngoingFlexLoan_TDB_FU_024();
		} catch (AssertionError e) {
			throw e;
		} finally {
			softAssert.assertAll();
		}
	}

	@Test(priority = 8)
	public void TDB_FU_026() throws Exception {
		try {
			flexUpModule.verifyOngoingFlexLoanUserCanAcceptAndDisburseFlexUpOffer_TDB_FU_026();
		} catch (AssertionError e) {
			throw e;
		} finally {
			softAssert.assertAll();
		}
	}

	@Test(priority = 9)
	public void TDB_FU_027() throws Exception {
		try {
			flexUpModule.verifyOngoingSILLoanUserCanAcceptAndDisburseFlexUpOffer_TDB_FU_027();
		} catch (AssertionError e) {
			throw e;
		} finally {
			softAssert.assertAll();
		}
	}

	@Test(priority = 10)
	public void TDB_FU_028() throws Exception {
		try {
			flexUpModule.verifyOngoingFlexUpLoanUserCanAcceptAndDisburseFlexUpOffer_TDB_FU_028();
		} catch (AssertionError e) {
			throw e;
		} finally {
			softAssert.assertAll();
		}
	}

	@Test(priority = 11)
	public void TDB_FU_008() throws Exception {
		try {
			flexUpModule.verifyIfUserCanAcceptTermsAndConditions_TDB_FU_008(propertyFileReader("LoanPurpose","flexUp"));
		} catch (AssertionError e) {
			throw e;
		} finally {
			softAssert.assertAll();
		}
	}

	@Test(priority = 12)
	public void TDB_FU_009() throws Exception {
		try {
			flexUpModule.verifyUserCanConfirmMonthlyInstallmentSummary_TDB_FU_009(propertyFileReader("LoanPurpose","flexUp"));
		} catch (AssertionError e) {
			throw e;
		} finally {
			softAssert.assertAll();
		}
	}

	@Test(priority = 13)
	public void TDB_FU_010() throws Exception {
		try {
			flexUpModule.verifyUserCanSignAllTheLoanDocuments_TDB_FU_010(propertyFileReader("LoanPurpose","flexUp"));
		} catch (AssertionError e) {
			throw e;
		} finally {
			softAssert.assertAll();
		}
	}

	@Test(priority = 14,dependsOnMethods = {"TDB_FU_010"})
	public void TDB_FU_011() throws Exception {
		try {
			flexUpModule.verifyFlexUpOfferAmountDisbursedSuccessfully_TDB_FU_011();
		} catch (AssertionError e) {
			throw e;
		} finally {
			softAssert.assertAll();
		}
	}

	@Test(priority = 15,dependsOnMethods = {"TDB_FU_010"})
	public void TDB_FU_012() throws Exception {
		try {
			flexUpModule.verifyUserCanViewAccountHistoryOfDisbursedFlexUp_TDB_FU_012(propertyFileReader("loanAmount", "QuickLoanWithVas"));
		} catch (AssertionError e) {
			throw e;
		} finally {
			softAssert.assertAll();
		}
	}

	@Test(priority = 16,dependsOnMethods = {"TDB_FU_010"})
	public void TDB_FU_013() throws Exception {
		try {
			flexUpModule.verifyUserCanViewLoanDashboardAfterSuccessfullyFlexUpDisbursement_TDB_FU_013();
		} catch (AssertionError e) {
			throw e;
		} finally {
			softAssert.assertAll();
		}
	}

	@Test(priority = 17,dependsOnMethods = {"TDB_FU_010"})
	public void TDB_FU_014() throws Exception {
		try {
			flexUpModule.verifyUserCanViewLoanInformationScreen_TDB_FU_014();
		} catch (AssertionError e) {
			throw e;
		} finally {
			softAssert.assertAll();
		}
	}

	@Test(priority = 18,dependsOnMethods = {"TDB_FU_010"})
	public void TDB_FU_015() throws Exception {
		try {
			flexUpModule.verifyUserCanViewRatesAndFeesScreen_TDB_FU_015();
		} catch (AssertionError e) {
			throw e;
		} finally {
			softAssert.assertAll();
		}
	}

	@Test(priority = 19,dependsOnMethods = {"TDB_FU_010"})
	public void TDB_FU_016() throws Exception {
		try {
			flexUpModule.verifyUserCanViewAndDownloadLoanDocuments_TDB_FU_016();
		} catch (AssertionError e) {
			throw e;
		} finally {
			softAssert.assertAll();
		}
	}

	@Test(priority = 20,dependsOnMethods = {"TDB_FU_010"})
	public void TDB_FU_017() throws Exception {
		try {
			flexUpModule.verifyUserCanMakeFullRepaymentWithInsufficientTSABalance_TDB_FU_017("WithVAS");
		} catch (AssertionError e) {
			throw e;
		} finally {
			softAssert.assertAll();
		}
	}

	@Test(priority = 21,dependsOnMethods = {"TDB_FU_010"})
	public void TDB_FU_018() throws Exception {
		try {
			flexUpModule.verifyUserCanMakeFullPaymentWithSufficientTSABalance_TDB_FU_018();
		} catch (AssertionError e) {
			throw e;
		} finally {
			softAssert.assertAll();
		}
	}

	@Test(priority = 22,dependsOnMethods = {"TDB_FU_010"})
	public void TDB_FU_019() throws Exception {
		try {
			flexUpModule.verifyLoanTileStatusAfterFullLoanRepaymentOfReloan_TDB_FU_019();
		} catch (AssertionError e) {
			throw e;
		} finally {
			softAssert.assertAll();
		}
	}
	@Test(priority = 23,dependsOnMethods = {"TDB_FU_019"})
	public void TDB_FU_021() throws Exception {
		try {
			flexUpModule.verifyUserCanReapplyAnyLoanAfterTheFullLoanRepaymentOfFlexUp_TDB_FU_021();
		} catch (AssertionError e) {
			throw e;
		} finally {
			softAssert.assertAll();
		}
	}
	@Test(priority = 24)
	public void flexUpEndToEndJourney() throws Exception {
		try {
			flexUpModule.flexUpEndToEndJourney(propertyFileReader("Terms","flexUp"),propertyFileReader("LoanPurpose","flexUp"));
		} catch (AssertionError e) {
			throw e;
		} finally {
			softAssert.assertAll();
		}
	}
}