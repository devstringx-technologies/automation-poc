package tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import constants.Constants;
import init.DriverClass;
import pages.HomePage;
import utils.FileOperations;

public class ExpenseTests extends DriverClass {

	RemoteWebDriver driver;

	HomePage homePage = new HomePage();
	FileOperations fileOperations = new FileOperations();
	String expense;
	Constants cs = new Constants();
	Logger log ;

	@BeforeClass
	public void setUp() throws IOException {
		try {
			intializeAppUnderTest();
			driver = getDriver();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<ArrayList<String>> mySheetData = fileOperations.getDataFromExcelSheet("testdata/TestData.xlsx", "Sheet1");
		expense = mySheetData.get(0).get(0);
		log= Logger.getLogger(ExpenseTests.class);
		PropertyConfigurator.configure(cs.CONFIG_LOG4J_FILE_PATH);
	}


	@Test(priority = 1)
	public void verifyTheExpenseCategoryScreenTitle() {
		log.info("verifyTheExpenseCategoryScreenTitle test is started ");
		SoftAssert softAssert = new SoftAssert();
		homePage.clickOnAddButton(driver);
		homePage.clickOnAddExpense(driver);
		softAssert.assertTrue(homePage.isTravelShownInCategories(driver));
		softAssert.assertAll();
		log.info("verifyTheExpenseCategoryScreenTitle test is finish ");
	}

	@Test(priority = 2)
	public void verifyAddExpenseScreen() {
		log.info("verifyAddExpenseScreen test is started ");
		SoftAssert softAssert = new SoftAssert();
		homePage.selectTravel(driver);
		softAssert.assertTrue(homePage.isAddExpensePage(driver));
		softAssert.assertAll();
		log.info("verifyAddExpenseScreen test is finish");
	}

	@Test(priority = 3)
	public void verifyAddExpenseFeature() {
		log.info("verifyAddExpenseFeature test is started");
		SoftAssert softAssert = new SoftAssert();
		homePage.addAmountInTravelAmount(driver, expense);
		homePage.clickOnSaveButton(driver);
		softAssert.assertTrue(homePage.getAddedExpense(driver).contains(expense));
		softAssert.assertAll();
		log.info("verifyAddExpenseFeature test is finish");
	}

	@Test(priority = 4)
	public void verifyHomePageAfterAddedExpense() {
		log.info("verifyHomePageAfterAddedExpense test is started");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(homePage.isHomePageOpened(driver));
		softAssert.assertAll();
		log.info("verifyHomePageAfterAddedExpense test is finish");
	}
	
	@Test(priority = 5)
	public void verifySelectAnyDateRangeToShowDataMessage() {
		log.info("Select any date range to show data. test is started");
		SoftAssert softAssert = new SoftAssert();
		homePage.clickOnView(driver);
		softAssert.assertTrue(homePage.isSelectAnyDataRangeToShowData(driver));
		softAssert.assertAll();
		log.info("Select any date range to show data. test is finish");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
