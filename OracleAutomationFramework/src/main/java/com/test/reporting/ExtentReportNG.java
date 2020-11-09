package com.test.reporting;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportNG implements IReporter {

	private ExtentReports extent;
	ExtentTest test;

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		extent = new ExtentReports(outputDirectory + File.separator + "OracleAutomationTest.html", true);

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult suiteResult : result.values()) {
				ITestContext context = suiteResult.getTestContext();
				test = extent.startTest(context.getCurrentXmlTest().getName());
				buildTestNodes(context, context.getPassedTests(), LogStatus.PASS);
				buildTestNodes(context, context.getFailedTests(), LogStatus.FAIL);
				buildTestNodes(context, context.getSkippedTests(), LogStatus.SKIP);
				extent.endTest(test);
			}
		}
		extent.flush();
		extent.close();
	}

	private void buildTestNodes(ITestContext context, IResultMap tests, LogStatus status) {
		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				// test = extent.startTest(result.getMethod().getMethodName());
				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));
				test.log(LogStatus.INFO, "TestMethod: " + result.getMethod().getMethodName());
				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);

				if (result.getThrowable() != null) {
					test.log(status, result.getThrowable());
				} else {
					test.log(status, "Test " + status.toString().toLowerCase() + "ed");
				}
				// extent.endTest(test);
			}
		}
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
}
