package listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.model.MediaType;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import init.DriverClass;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ReportListener
        implements ITestListener, IClassListener, ISuiteListener {

    private static ExtentReports extent;
    private static ExtentTest test;
    private static ExtentHtmlReporter htmlReporter; 
    private static Media med;
    private static MediaEntityModelProvider mp;
    private static RemoteWebDriver webDriver;
    String screen, screen1 = null;
    
    
    // Save extent reports path
    private static String filePathex = System.getProperty("user.dir") +"/extent_reports/" + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp(System.currentTimeMillis())) + "extentreport.html";;
    // Save screenshot path
    String filePath = System.getProperty("user.dir")+"/screenshots/";
    int count = 1, count1 = 1;
    String line = "<td class=\"result\">";
    String method[] = new String[100];
    String method1[] = new String[100];
    ITestResult result;

    public void onStart(ISuite suite) {
        extent = getExtent();
        System.out.println("Suite start");
    }

    public void onStart(ITestContext context) {
        System.out.println("Test will be started");
    }

    public void onBeforeClass(ITestClass testclass) {
        test = extent.createTest(testclass.getRealClass().getSimpleName());
    }

    public void onTestStart(ITestResult result) {
        webDriver = DriverClass.getDriver();
        System.out.println(" Test case is started");
    }

    public void onTestSuccess(ITestResult result) {
        test.createNode(result.getMethod().getMethodName(), result.getMethod().getDescription())
                .pass(MarkupHelper.createLabel("PASS", ExtentColor.GREEN)).log(Status.PASS, result.getTestName());
        System.out.println("Test case is executed successfully");
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("***** Skip " + result.getName() + " test has failed *****");
        method1[count1 - 1] = result.getName().toString().trim();
        this.result = result;
        takeScreenShot(method1[count1 - 1], DriverClass.getDriver());
        med = new ScreenCapture();
        med.setMediaType(MediaType.IMG);
        med.setPath(screen1);
        mp = new MediaEntityModelProvider(med);
        test.createNode(result.getMethod().getMethodName(), result.getMethod().getDescription())
                .skip(MarkupHelper.createLabel("SKIP", ExtentColor.BLUE))
                .skip("Screenshot " + result.getMethod().getMethodName(), mp).log(Status.SKIP, result.getThrowable());
        count++;
        System.out.println("Test case is skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailure(ITestResult result) {
        System.out.println("***** Error " + result.getName() + " test has failed *****");
        method[count - 1] = result.getName().toString().trim();
        this.result = result;
        takeScreenShot(method[count - 1], DriverClass.getDriver());
        med = new ScreenCapture();
        med.setMediaType(MediaType.IMG);
        med.setPath(screen1);
        mp = new MediaEntityModelProvider(med);
        test.createNode(result.getMethod().getMethodName(), result.getMethod().getDescription())
                .fail(MarkupHelper.createLabel("FAIL", ExtentColor.RED))
                .fail("Screenshot " + result.getMethod().getMethodName(), mp).log(Status.FAIL, result.getThrowable());
        count++;
        System.out.println("Test case is failed");
    }

    public void onAfterClass(ITestClass testclass) {
        System.out.println("Class ending" + testclass.getRealClass().getSimpleName());
    }

    public void onFinish(ITestContext context) {
        System.out.println("Test will be ending");
    }

    public void onFinish(ISuite suite) {
        extent.flush();
        System.out.println("Suite Finish");

    }


    public void takeScreenShot(String methodName, RemoteWebDriver driver) {

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            File scrFile2 = new File(filePath + methodName + ".png");
            System.setProperty("org.uncommons.reportng.escape-output", "false");
            FileUtils.copyFile(scrFile, scrFile2);
            System.out.println("***Placed screen shot in " + filePath + " ***");
            screen1 = scrFile2.toString();
            screen = "<img src='" + scrFile2.toString() + "' width='200' height='200'  > ";
            Reporter.setEscapeHtml(false);
            Reporter.log(screen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ExtentReports getExtent() {
        if (extent == null) {
            extent = new ExtentReports();

            extent.attachReporter(getHtmlReporter());
        }
        return extent;
    }

    private static ExtentHtmlReporter getHtmlReporter() {
        htmlReporter = new ExtentHtmlReporter(filePathex);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("Mobile Automation POC");
        htmlReporter.config().setReportName("test report");
        return htmlReporter;
    }


}
