package init;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.CommonUtils;

public class DriverClass {
	static RemoteWebDriver driver;
	CommonUtils commonUtils;
	public final String configFile = "src/main/java/config/appConfig.properties";
	public final String downloadPathFile = "download";
	Path currentRelativePath = Paths.get("");
	String startPath = currentRelativePath.toAbsolutePath().toString();

	/**
	 * @throws MalformedURLException
	 */
	public void intializeAppUnderTest() throws MalformedURLException {
		commonUtils = new CommonUtils();
		DesiredCapabilities capability = new DesiredCapabilities();
		System.out.println(startPath);
		String os_env = commonUtils.readConfig("osName", configFile);
		System.out.println(os_env);
		String device_nm = commonUtils.readConfig("device_name", configFile);
		System.out.println(device_nm);
		if (os_env.equals("Android")) {
			capability.setCapability("deviceName", commonUtils.readConfig("device_id", configFile));
			System.out.println();
			capability.setCapability("app", startPath + "/APK/expenseManager.apk");
			capability.setCapability(CapabilityType.BROWSER_NAME, "");
			capability.setCapability("platformName", os_env);
			capability.setCapability("platformVersion", commonUtils.readConfig("osVersion", configFile));
			capability.setCapability("appPackage", commonUtils.readConfig("packageNameofApp", configFile));
			capability.setCapability("appActivity", commonUtils.readConfig("activityNameOfApp", configFile));
			capability.setCapability("unicodeKeyboard", true);
			capability.setCapability("resetKeyboard", true);
			capability.setCapability("autoGrantPermissions", true);
			capability.setCapability("noReset", false);
			driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capability);
			setDriver(driver);
		} else {

		}

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

	}

	/**
	 * @return
	 */
	public static RemoteWebDriver getDriver() {
		return driver;
	}

	/**
	 * @param remoteDriver
	 */
	public static void setDriver(RemoteWebDriver remoteDriver) {
		driver = remoteDriver;
	}

}
