package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.UUID;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CommonUtils {
	
	/** Method to read the specified property value from configuration file
	 * @param propertyName:- Property to be read
	 * @param configFile:- Configuration properties file to read
	 * @return:- Corresponding value of the property specified
	 */
	public String readConfig(String propertyName, String configFile) {
		Properties  prop = new Properties();
		InputStream input = null;
		String propertyValue = "";

		try {
			input = new FileInputStream(configFile);
			prop.load(input);
			propertyValue = prop.getProperty(propertyName);
		}catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (!(null == input)) {
				try {
					input.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		}
		return propertyValue;
	}
	
	/**Method to take screenshot and save the screenshot within results/screenshots folder
	 * @param driver RemoteWebDriver instance
	 * @return Name of screenshot file
	 */
	public void screenshot(String path_screenshot, RemoteWebDriver driver) throws IOException{
	    File srcFile=driver.getScreenshotAs(OutputType.FILE);
	    String filename=UUID.randomUUID().toString();
	    String timeFormat = this.readConfig("dateTimeFormat", "config/generalConfig.properties");
		String time = this.getCurrentSysDateTime(timeFormat);
	    File targetFile=new File(path_screenshot + filename + time +".jpg");
	    //FileUtils.copyFile(srcFile,targetFile);
	}
	
	/** Method to get the current system date and time
	 * @param dateTimeFormat Date and time format as per Java SimpleDateTimeFormat
	 * @return The current system date and time
	 */
	public String getCurrentSysDateTime(String dateTimeFormat) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat s = new SimpleDateFormat(dateTimeFormat);
		String time = s.format(cal.getTime());
		return time;
	}	

}
