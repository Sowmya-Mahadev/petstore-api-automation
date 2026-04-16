package api.utilities;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


	public class ExtentReportManager implements ITestListener {

	    public ExtentSparkReporter sparkReporter; // for look and feel of the report
	    public ExtentReports extent;
	    public ExtentTest test;

	    String repName;

	    // ================================
	    // ON START
	    // ================================
	    public void onStart(ITestContext testContext) {
	        System.out.println(" Extent Report Started");

	        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	        repName = "Test-Report-" + timeStamp + ".html";

	        String reportPath = System.getProperty("user.dir") + "/reports/" + repName;

	     // create folder if not exists
	     new File(System.getProperty("user.dir") + "/reports").mkdir();

	     sparkReporter = new ExtentSparkReporter(reportPath);
	        sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); // title of the report
	        sparkReporter.config().setReportName("Pet Store Users API"); // name of the report
	        sparkReporter.config().setTheme(Theme.DARK); // theme of the report

	        extent = new ExtentReports();
	        extent.attachReporter(sparkReporter);

	        extent.setSystemInfo("Application", "Pet Store Users API");
	        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
	        extent.setSystemInfo("User Name", System.getProperty("user.name"));
	        extent.setSystemInfo("Environment", "QA");
	    }

	    // ================================
	    // TEST SUCCESS
	    // ================================
	    public void onTestSuccess(ITestResult result) {

	        test = extent.createTest(result.getName());
	        test.assignCategory(result.getMethod().getGroups());
            test.createNode(result.getName());
            test.log(Status.PASS, "Test Passed");
	    }

	    // ================================
	    // TEST FAILURE
	    // ================================
	    public void onTestFailure(ITestResult result) {

	        test = extent.createTest(result.getName());
	        test.assignCategory(result.getMethod().getGroups());

	        test.log(Status.FAIL, "Test Failed");
	        test.log(Status.FAIL, result.getThrowable().getMessage());

	        // Optional Screenshot (for Selenium only)
	        /*
	        String path = System.getProperty("user.dir") + "/screenshots/" + result.getName() + ".png";
	        try {
	            test.addScreenCaptureFromPath(path);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        */
	    }

	    // ================================
	    // TEST SKIPPED
	    // ================================
	    public void onTestSkipped(ITestResult result) {

	        test = extent.createTest(result.getName());
	        test.assignCategory(result.getMethod().getGroups());

	        test.log(Status.SKIP, "Test Skipped");
	        test.log(Status.SKIP, result.getThrowable().getMessage());
	    }

	    // ================================
	    // ON FINISH
	    // ================================
	    public void onFinish(ITestContext testContext) {
	        extent.flush();
	        System.out.println(" Report flushed");
	    }
	}

