package framework.testUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	static ExtentReports extent;
	
	public static ExtentReports getReporterObject() {
		
		String path=System.getProperty("user.dir")+"/reports/index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setReportName("Simple Stream Mobile Browser Website login Tests");
		reporter.config().setDocumentTitle("Mobile Test Results");
		
	    extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Shashi");
		return extent;
	}
}
