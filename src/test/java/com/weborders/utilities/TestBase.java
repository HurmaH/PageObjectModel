package com.weborders.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public abstract class TestBase {
    //protected variable so whoever extends this class will extend these variables also

    protected WebDriver driver;
    protected Pages pages;

    protected static ExtentReports report;
    protected static ExtentHtmlReporter htmlReporter;
    protected static ExtentTest extentLogger;

    @BeforeMethod
    public void setupMethod() {
        driver = Driver.getDriver();
        //initializing pages object
        pages = new Pages();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfigurationReader.getProperty("url"));
    }

    @AfterMethod
    public void tearDownMethod(ITestResult result) throws IOException {
        //if any test fails, it can detect and take screen shot at the point
        //and attach to report
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotLocation = BrowserUtils.getScreenshot(result.getName());
            extentLogger.fail(result.getName());
            extentLogger.addScreenCaptureFromPath(screenshotLocation);
            extentLogger.fail(result.getThrowable());

        } else if (result.getStatus() == ITestResult.SKIP) {
            extentLogger.skip("Test Case Skipped: " + result.getName());
        }
        Driver.closeDriver();
    }

    @BeforeTest
    public void setUpTest() {
        //initialize ExtentReporter
        report = new ExtentReports();
        //this is custom location of the report that will be generated
        //report will be generated in the current project inside
        //  folder: test-output
        //report file name: report.html
        String filePath = System.getProperty("user.dir") + "/test-output/report.html";// + name + time + ".png";

        //initialize the htmlReporter with the path to the report
        htmlReporter = new ExtentHtmlReporter(filePath);

        //Below lines are optional:

        //we attach the html
        report.attachReporter(htmlReporter);

        //testing different environment
        report.setSystemInfo("Environment", "Staging");

        //picking browser
        report.setSystemInfo("Browser", ConfigurationReader.getProperty("browser"));

        //operating system
        report.setSystemInfo("OS", System.getProperty("os.name"));

        //i can put testers name as well:
        report.setSystemInfo("QA Engineer", "Hurma Haytyyeva");

        htmlReporter.config().setDocumentTitle("Weborder Reports");
        htmlReporter.config().setReportName("Weborder Automated Test Reports");
//        htmlReporter.config().setTheme(Theme.DARK);

    }

    @AfterTest
    public void tearDownTest() {

        report.flush();
    }
}
