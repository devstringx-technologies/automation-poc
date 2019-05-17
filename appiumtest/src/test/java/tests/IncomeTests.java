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
	public void verifyHomePageAfterLunchApp() {
		log.info("verifyHomePageAfterLunchApp test is started ");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(homePage.isHomePageOpened(driver));
		softAssert.assertAll();
		log.info("verifyHomePageAfterLunchApp test is finish ");
	}
	
	@Test(priority=2)
	public void verifyCategoriesList() {
		log.info("verifyCategoriesList test is started ");
		SoftAssert softAssert = new SoftAssert();
		homePage.clickOnAddButton(driver);
		softAssert.assertTrue(homePage.isCategoriesPopupOpened(driver ));
		softAssert.assertAll();
		log.info("verifyCategoriesList test is finish ");

	}
	
	@Test(priority=3)
	public void verifyIncomePage() {
		log.info("verifyIncomePage test is started ");
		SoftAssert softAssert = new SoftAssert();
		homePage.clickOnAddIncome(driver);
		softAssert.assertTrue(homePage.isIncomePageOpened(driver ));
		softAssert.assertAll();
		log.info("verifyIncomePage test is finish ");
	}
	
	@Test(priority=4)
	public void verifySalaryPage() {
		log.info("verifySalaryPage test is started ");
		SoftAssert softAssert = new SoftAssert();
		homePage.clickOnSalary(driver);
		softAssert.assertTrue(homePage.isSalaryPageOpened(driver ));
		softAssert.assertAll();
		log.info("verifySalaryPage test is started ");

	}
	
	@Test(priority=5)
	public void addSalaryAsIncomeAndVerifyThem() {
		log.info("addSalaryAsIncomeAndVerifyThem test is started ");
		SoftAssert softAssert = new SoftAssert();
		homePage.addSalary(driver, "30000");
		homePage.clickOnSaveButton(driver);
		softAssert.assertTrue(homePage.getAddedSalary(driver).contains("30000"));
		softAssert.assertAll();
		log.info("addSalaryAsIncomeAndVerifyThem test is finish ");
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
