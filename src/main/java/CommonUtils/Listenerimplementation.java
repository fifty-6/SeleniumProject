package CommonUtils;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listenerimplementation implements ITestListener{

	public ExtentReports report;
	public ExtentTest test;
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		Reporter.log("Test Script is Started",true);
		String methodname=result.getMethod().getMethodName();
		test=report.createTest(methodname);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String methodname=result.getMethod().getMethodName();
		Reporter.log(methodname+" Test Script is Passed",true);
		test.log(Status.PASS, methodname+" Test Script is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		JavaUtils jutils=new JavaUtils();
		String message=result.getThrowable().toString();
		String methodname=result.getMethod().getMethodName();
//		Reporter.log(methodname+" Test Script is Failed "+message,true);
		test.log(Status.FAIL, methodname+" Test Script is Failed "+message);
		test.log(Status.FAIL, result.getThrowable());
		WebDriverUtils wutils=new WebDriverUtils();
		try {
			String path=wutils.toTakeScreenshotFailed(BaseClass.sd,"Image"+jutils.RandomNumbers());
			test.addScreenCaptureFromPath(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		String methodname=result.getMethod().getMethodName();
//		Reporter.log(methodname+" Test Script is Skipped",true);
		test.log(Status.SKIP, methodname+" Test Script is Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ExtentSparkReporter htmlreport=new ExtentSparkReporter("./ExtentReports/Report.html");
		htmlreport.config().setDocumentTitle("VtigerCRM Framework");
		htmlreport.config().setReportName("VtigerCRM Framework");
		htmlreport.config().setTheme(Theme.DARK);
		
		report=new ExtentReports();
		report.attachReporter(htmlreport);
		report.setSystemInfo("Operating System", "Windows10");
		report.setSystemInfo("Browser", "Chrome");
		report.setSystemInfo("Browser version", "120.0.6099");
		report.setSystemInfo("Programming Language", "Java");
		report.setSystemInfo("Author", "Sanket");
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
		
	}

}
