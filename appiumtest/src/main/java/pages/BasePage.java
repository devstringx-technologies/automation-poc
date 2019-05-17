package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.android.AndroidElement;

public class BasePage {


	public AndroidElement mobileElement;

	/**
	 * @param driver
	 * @param locator
	 * @return
	 */
	public boolean isElementPresence(RemoteWebDriver driver, By locator){

		boolean isElementPresent = false;

		try{
			mobileElement = (AndroidElement) driver.findElement(locator);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOf(mobileElement));
			return isElementPresent=true;	
		}catch(Exception e){
			System.out.println(e.getMessage());
			return isElementPresent;
		} 

	}

}
