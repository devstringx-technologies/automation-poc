package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.BasePage;

public class HomePage extends BasePage {

	By plus = By.xpath("//android.widget.Button");
	By addIncome = By.xpath("//android.widget.TextView[contains(@text,'Add Income')]");
	By addExpense = By.xpath("//android.widget.TextView[contains(@text,'Add Expense')]");
	By salary = By.xpath("//android.widget.TextView[contains(@text,'Salary')]");
	By travel = By.xpath("//android.widget.TextView[contains(@text,'Travel')]");
	By amountField = By.xpath("//android.widget.EditText[contains(@text,'Enter Amount')]");
	By saveButton = By.xpath("//android.widget.Button[contains(@text,'SAVE')]");
	By addedSalary = By.xpath("//android.widget.TextView[contains(@text,'Income')]");
	By addedExpanse = By.xpath("//android.widget.TextView[contains(@text,'Expense')]");

	/**
	 * @param driver
	 */
	public void clickOnAddButton(RemoteWebDriver driver) {
		driver.findElement(plus).click();
	}

	/**
	 * @param driver
	 */
	public void clickOnAddIncome(RemoteWebDriver driver) {
		driver.findElement(addIncome).click();
	}

	/**
	 * @param driver
	 */
	public void clickOnSalary(RemoteWebDriver driver) {
		driver.findElement(salary).click();
	}

	/**
	 * @param driver
	 * @param val
	 */
	public void addSalary(RemoteWebDriver driver, String val) {
		driver.findElement(amountField).sendKeys(val);
	}

	/**
	 * @param driver
	 */
	public void clickOnSaveButton(RemoteWebDriver driver) {
		driver.findElement(saveButton).click();
	}

	/**
	 * @param driver
	 * @return
	 */
	public String getAddedSalary(RemoteWebDriver driver) {
		return driver.findElement(addedSalary).getText();
	}

	/**
	 * @param driver
	 * @return
	 */
	public boolean isHomePageOpened(RemoteWebDriver driver) {
		return driver.findElement(plus).isEnabled();
	}

	/**
	 * @param driver
	 * @return
	 */
	public boolean isCategoriesPopupOpened(RemoteWebDriver driver) {
		return driver.findElement(addIncome).isEnabled();
	}

	/**
	 * @param driver
	 * @return
	 */
	public boolean isIncomePageOpened(RemoteWebDriver driver) {
		return driver.findElement(salary).isEnabled();
	}

	/**
	 * @param driver
	 * @return
	 */
	public boolean isSalaryPageOpened(RemoteWebDriver driver) {
		return driver.findElement(amountField).isEnabled();
	}

	/**
	 * @param driver
	 */
	public void clickOnAddExpense(RemoteWebDriver driver) {
		driver.findElement(addExpense).click();
	}

	/**
	 * @param driver
	 */
	public void selectTravel(RemoteWebDriver driver) {
		driver.findElement(travel).click();
	}

	/**
	 * @param driver
	 * @param val
	 */
	public void addAmountInTravelAmount(RemoteWebDriver driver, String val) {
		driver.findElement(amountField).sendKeys(val);
	}

	/**
	 * @param driver
	 * @return
	 */
	public String getAddedExpense(RemoteWebDriver driver) {
		return driver.findElement(addedExpanse).getText();
	}

	/**
	 * @param driver
	 * @return
	 */
	public boolean isExpanseCategoriesOpened(RemoteWebDriver driver) {
		return driver.findElement(addExpense).isEnabled();
	}

	/**
	 * @param driver
	 * @return
	 */
	public boolean isTravelShownInCategories(RemoteWebDriver driver) {
		return driver.findElement(travel).isEnabled();
	}

	/**
	 * @param driver
	 * @return
	 */
	public boolean isAddExpensePage(RemoteWebDriver driver) {
		return driver.findElement(amountField).isEnabled();
	}
}