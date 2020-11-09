package com.test.reporting;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.test.utility.WebUtility;

/*
 * Author: Manav C K
 */
public class ExtentManager {
	private static ExtentReports extent;

	public static ExtentReports getInstance() {
		if (extent == null) {
			try {
				creatExtentInstance();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		return extent;
	}

	public static ExtentReports creatExtentInstance() throws UnknownHostException {
		WebUtility.createFolder("extent-report");
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "/test-output/extent-report/ExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", getCurrentServerIpAdd().getHostName());
		extent.setSystemInfo("Environment", "TEST");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		htmlReporter.config().setDocumentTitle("ORACLE AUTOMATION REPORT");
		htmlReporter.config().setReportName("ORACLE AUTOMATION");
		htmlReporter.config().setChartVisibilityOnOpen(false);
		htmlReporter.config().setTheme(Theme.STANDARD);

		return extent;
	}

	public static InetAddress getCurrentServerIpAdd() throws UnknownHostException {
		return InetAddress.getLocalHost();
	}
}