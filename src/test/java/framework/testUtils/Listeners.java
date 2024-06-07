package framework.testUtils;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;
import simplestream.utils.AppiumUtils;

public class Listeners extends AppiumUtils implements ITestListener {
	
	ExtentReports extent=ExtentReporterNG.getReporterObject();
	ExtentTest test;
	AppiumDriver driver;
	
	@Override
	public void onTestStart(ITestResult result) {
		
		test=extent.createTest(result.getMethod().getMethodName());	
	}
	
	@Override
	public  void onTestSuccess(ITestResult result) {
		test.log(Status.PASS,"Test Passed");
		try {
			test.addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(),driver),result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		
		try {
			driver=(AppiumDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();}
	
		test.fail(result.getThrowable());
		try {
			test.addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(),driver),result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void onFinish(ITestContext context) {
	    extent.flush();
	}

}
