package com.aconex.tests;

import java.lang.reflect.Method;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aconex.application.AppLauncher;
import com.aconex.pages.DocumentRegisterPage;
import com.aconex.pages.LoginPage;
import com.aconex.pages.MainNavigation;
import com.aconex.pages.NewMailPage;
import com.aconex.pages.SearchDirectoryPage;
import com.aconex.pages.UploadDocumentPage;
import com.test.base.TestBase;
import com.test.exception.FrameworkException;
import com.test.utility.PropertyDataReader;
import com.test.utility.WebUtility;

@Listeners(com.test.listeners.TestListeners.class)
public class AconexTestClass extends TestBase {

	public LoginPage loginObject;
	public MainNavigation navigationObject;
	public NewMailPage mailObject;
	public SearchDirectoryPage searchObject;
	public UploadDocumentPage uploadDocObject;
	public DocumentRegisterPage docRegisterObject;

	@BeforeClass
	public void setUp() {
		initialization();
		AppLauncher app = AppLauncher.TEST_URL;
		app.launchUrl(app);

		loginObject = new LoginPage();
		navigationObject = new MainNavigation();
		mailObject = new NewMailPage();
		searchObject = new SearchDirectoryPage();
		uploadDocObject = new UploadDocumentPage();
		docRegisterObject = new DocumentRegisterPage();
	}

	@BeforeMethod
	public void beforeMethod(Method testMethod) throws FrameworkException {

		String desc = testMethod.getAnnotation(Test.class).description();
		LOG.debug("Executing the Test: " + desc);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		quitDriver();
	}

	@Test(priority = 1, description = "Testing send mail functionality")
	public void sendMail() {
		String userName = PropertyDataReader.getProperty("config.properties", "app_username1");
		String password = PropertyDataReader.getProperty("config.properties", "app_password1");
		loginObject.login(userName, password);
		navigationObject.navigateToBlankMail();
		mailObject.switchToNewMailPageFrame();
		mailObject.selectType();
		mailObject.clickOnDirectory();
		searchObject.clickOnGlobalLink();
		searchObject.enterGroupName();
		searchObject.enterFamilyName();
		searchObject.clickOnSearch();
		searchObject.selectUserListForTo();
		searchObject.clickOnOk();
		mailObject.enterSubject();
		mailObject.selectAttributes();
		mailObject.clickOnSend();
		mailObject.verifyIfMailSent();
		WebUtility.switchToDefaultFrame();
	}

	@Test(priority = 2, description = "Upload New Document Functionality")
	public void uploadNewDocument() {
		navigationObject.navigateToUploadNewDocumentPage();
		uploadDocObject.performNewDocumentUpload();
	}

	@Test(priority = 3, description = "Save the search result")
	public void saveTheSearch() {
		navigationObject.navigateToDocumentRegisterPage();
		docRegisterObject.switchToDocumentRegisterPageIFrame();
		docRegisterObject.searchForDocument();
		docRegisterObject.saveTheSearch();
	}

}
