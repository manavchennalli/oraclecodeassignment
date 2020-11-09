package com.aconex.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.test.base.TestBase;
import com.test.utility.WebUtility;

public class MainNavigation extends TestBase {

	@FindBy(how = How.XPATH, using = "//button[@id='nav-bar-CORRESPONDENCE']")
	public WebElement mailLink;

	@FindBy(how = How.XPATH, using = "//div[text()='Blank Mail']")
	public WebElement blankLink;

	@FindBy(how = How.XPATH, using = "//h1[@id='editMailHeading']")
	public WebElement newMailPageHeader;

	@FindBy(how = How.XPATH, using = "//button[@id='nav-bar-DOC']")
	public WebElement documentsLink;

	@FindBy(how = How.XPATH, using = "//div[@id='nav-bar-DOC-DOC-NEW']")
	public WebElement uploadNewDocumentLink;

	@FindBy(how = How.XPATH, using = "//div[@id='nav-bar-DOC-DOC-SEARCH']")
	public WebElement documentRegisterLink;

	@FindBy(how = How.XPATH, using = "//h1[text()='Upload Document']")
	public WebElement uploadDocumentPageHeader;

	@FindBy(how = How.XPATH, using = "//span[@id='mainHeading' and text()='Document Register']")
	public WebElement documentRegisterPageHeader;

	@FindBy(how = How.ID, using = "frameMain")
	public WebElement IFrame;

	public MainNavigation() {
		PageFactory.initElements(driver, this);
	}

	public void navigateToBlankMail() {
		WebUtility.click(mailLink);
		WebUtility.click(blankLink);
		WebUtility.explicitWait(newMailPageHeader);
		LOG.info("Navigated to New Mail Template Page");
	}

	public void navigateToUploadNewDocumentPage() {
		WebUtility.click(documentsLink);
		WebUtility.click(uploadNewDocumentLink);
		WebUtility.switchToFrame(IFrame);
		WebUtility.explicitWait(uploadDocumentPageHeader);
		WebUtility.switchToDefaultFrame();
		LOG.info("Navigated to Upload New Document Page");
	}

	public void navigateToDocumentRegisterPage() {
		WebUtility.click(documentsLink);
		WebUtility.click(documentRegisterLink);
		WebUtility.switchToFrame(IFrame);
		WebUtility.explicitWait(documentRegisterPageHeader);
		WebUtility.switchToDefaultFrame();
		LOG.info("Navigated to Document Register Page");
	}
}
