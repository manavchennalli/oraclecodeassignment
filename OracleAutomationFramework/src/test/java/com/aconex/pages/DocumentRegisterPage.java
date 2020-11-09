package com.aconex.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.test.base.TestBase;
import com.test.utility.PropertyDataReader;
import com.test.utility.WebUtility;

public class DocumentRegisterPage extends TestBase {

	@FindBy(how = How.XPATH, using = "//input[@id='search-keywords-id']")
	public WebElement searchInputField;

	@FindBy(how = How.XPATH, using = "//div[2]/button[contains(text(),'Search')]")
	public WebElement searchButton;

	@FindBy(how = How.XPATH, using = "//div[3]//button[@title='Save the current search configuration']")
	public WebElement saveSearchASButton;

	@FindBy(how = How.XPATH, using = "//div[@ng-hide='savingSearchInProgress']//input[@type='text']")
	public WebElement nameField;

	@FindBy(how = How.XPATH, using = "//input[@id='ok']")
	public WebElement okButton;

	@FindBy(how = How.ID, using = "frameMain")
	public WebElement documentRegisterPageIFrame;

	public DocumentRegisterPage() {
		PageFactory.initElements(driver, this);
	}

	public void switchToDocumentRegisterPageIFrame() {
		WebUtility.switchToFrame(documentRegisterPageIFrame);
	}

	public void searchForDocument() {
		String searchKey = PropertyDataReader.getProperty("Data.properties", "searchKeyword");
		WebUtility.enterText(searchInputField, searchKey);
		WebUtility.click(searchButton);
		LOG.info("Document searched");
	}

	public void saveTheSearch() {
		String searchName = PropertyDataReader.getProperty("Data.properties", "searchName");
		WebUtility.click(saveSearchASButton);
		WebUtility.enterText(nameField, searchName);
		WebUtility.click(okButton);
		LOG.info("Document search saved successfully");
	}
}
