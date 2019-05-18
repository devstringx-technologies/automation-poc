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

public class IncomeTests extends DriverClass {

	RemoteWebDriver driver;

	HomePage homePage = new HomePage();
	FileOperations fileOperations = new FileOperations();
	String investment;
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
		investment = mySheetData.get(0).get(1);
		log= Logger.getLogger(ExpenseTests.class);
		PropertyConfigurator.configure(cs.CONFIG_LOG4J_FILE_PATH);
	}
	
	@Test(priority=1)
	public void VerifyAppLaunchesSuccessfully() {
		log.info("VerifyAppLaunchesSuccessfully test is started ");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(homePage.isHomePageOpened(driver));
		softAssert.assertAll();
		log.info("VerifyAppLaunchesSuccessfully test is finish ");
	}
	
	@Test(priority=2)
	public void verifyCategoriesModal() {
		log.info("verifyCategoriesModal test is started");
		SoftAssert softAssert = new SoftAssert();
		homePage.clickOnAddButton(driver);
		softAssert.assertTrue(homePage.isAddIncomeShownInCategoriesModal(driver ));
		softAssert.assertAll();
		log.info("verifyCategoriesModal test is finish");
	}
	
	@Test(priority=3)
	public void verifyTheIncomeCategoryScreenTitle() {
		log.info("VerifyTheIncomeCategoryScreenTitle test is started ");
		SoftAssert softAssert = new SoftAssert();
		homePage.clickOnAddIncome(driver);
		softAssert.assertTrue(homePage.isIncomePageOpened(driver ));
		softAssert.assertAll();
		log.info("VerifyTheIncomeCategoryScreenTitle test is finish ");
	}
	
	@Test(priority=4)
	public void verifyAddIncomeScreen() {
		log.info("verifyAddIncomeScreen test is started ");
		SoftAssert softAssert = new SoftAssert();
		homePage.clickOnSalary(driver);
		softAssert.assertTrue(homePage.isSalaryPageOpened(driver ));
		softAssert.assertAll();
		log.info("verifyAddIncomeScreen test is started ");

	}
	
	@Test(priority=5)
	public void verifyAddIncomeFeature() {
		log.info("verifyAddIncomeFeature test is started ");
		SoftAssert softAssert = new SoftAssert();
		homePage.addSalary(driver, investment);
		homePage.clickOnSaveButton(driver);
		softAssert.assertTrue(homePage.getAddedSalary(driver).contains(investment));
		softAssert.assertAll();
		log.info("verifyAddIncomeFeature test is finish ");
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
