
package com.test.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

	private static ExtentReports extent = ExtentManager.getInstance();
	static ExtentTest test;

	public static ExtentTest getTest() {
		return test;
	}

	public static ExtentTest createTest(String testName) {
		test = extent.createTest(testName);
		return test;
	}
}