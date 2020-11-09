package com.test.listeners;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.IConfigurationListener;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.test.reporting.ExtentManager;
import com.test.reporting.ExtentTestManager;
import com.test.utility.WebUtility;

/**
 * 
 * @author Manav C K
 * Date: 8 November 2020
 *
 */

public class TestListeners implements ITestListener, IInvokedMethodListener, ISuiteListener, IConfigurationListener {

	private static ExtentReports extent = ExtentManager.getInstance();
	private static String tcName;
	@SuppressWarnings("rawtypes")
	private Map<Class, Boolean> skipTestClassMap = new HashMap<>();
	ExtentTest parentnode;
	ExtentTest childnode;
	static String failedIssueKey = "";

	public void onTestStart(ITestResult result) {

	}

	public void onTestSuccess(ITestResult result) {
		childnode = parentnode.createNode(result.getMethod().getMethodName());
		childnode.log(Status.PASS, "Test Passed");
	}

	public void onTestFailure(ITestResult result) {
		childnode = parentnode.createNode(result.getMethod().getMethodName());
		childnode.log(Status.FAIL, "Test failed because of " + result.getThrowable());
		try {

			String filename = WebUtility.getScreenshot(WebUtility.createFolderWithDate(tcName), result.getName());
			childnode.addScreenCaptureFromPath(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		childnode = parentnode.createNode(result.getMethod().getMethodName());
		childnode.log(Status.SKIP, "Test method skipped");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context) {
		tcName = context.getName();
		System.out.println("========= Test Under Execution:" + tcName + " =========");
		parentnode = ExtentTestManager.createTest(tcName);
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		Boolean shouldSkipTestCases = skipTestClassMap.get(testResult.getTestClass().getRealClass());
		// methods failed, so we need skip them
		if (shouldSkipTestCases != null && shouldSkipTestCases == false) {
			throw new SkipException("Test case is skipped...");
		}
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if (testResult.getStatus() != ITestResult.SUCCESS) {
			Boolean shouldSkipTestCases = skipTestClassMap.get(testResult.getTestClass().getRealClass());
			if (shouldSkipTestCases == null) {
				// Add failed test class in the map
				skipTestClassMap.put(testResult.getTestClass().getRealClass(), false);
			}
		}
	}

	@Override
	public void onConfigurationSuccess(ITestResult itr) {

	}

	@Override
	public void onConfigurationFailure(ITestResult itr) {

	}

	@Override
	public void onConfigurationSkip(ITestResult itr) {

	}

	public void beforeConfiguration(ITestResult tr) {

	}

	@Override
	public void onStart(ISuite suite) {

	}

	@Override
	public void onFinish(ISuite suite) {

	}

	public void manageFailedTestCases(ITestResult result, String filename) {

	}

	public void getIssueKey(String className) {

	}
}