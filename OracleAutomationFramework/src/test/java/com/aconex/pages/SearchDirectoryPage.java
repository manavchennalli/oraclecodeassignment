package com.aconex.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.test.base.TestBase;
import com.test.utility.PropertyDataReader;
import com.test.utility.WebUtility;

public class SearchDirectoryPage extends TestBase {

	@FindBy(how = How.ID, using = "ACONEX_list")
	public WebElement globalLink;

	@FindBy(how = How.ID, using = "FIRST_NAME")
	public WebElement groupNameField;

	@FindBy(how = How.ID, using = "LAST_NAME")
	public WebElement familyNameField;

	@FindBy(how = How.XPATH, using = "//button[@id='btnSearch_page']")
	public WebElement searchButton;

	@FindBy(how = How.XPATH, using = "//input[@name='USERS_LIST']")
	public WebElement userListCheckbox;

	@FindBy(how = How.XPATH, using = "//div[@id='searchResultsToolbar']//button[@id='btnAddTo_page']")
	public WebElement selectToButton;

	@FindBy(how = How.XPATH, using = "//div[text()='OK']")
	public WebElement okButton;

	@FindBy(how = How.ID, using = "frameMain")
	public WebElement searchDirectoryPageIframe;

	public SearchDirectoryPage() {
		PageFactory.initElements(driver, this);
	}

	public void switchToSearchDirectoryPageFrame() {
		WebUtility.switchToFrame(searchDirectoryPageIframe);
	}

	public void clickOnGlobalLink() {
		WebUtility.click(globalLink);
		LOG.info("Clicked on Global");
	}

	public void enterGroupName() {
		String groupName = PropertyDataReader.getProperty("Data.properties", "groupName");
		WebUtility.enterText(groupNameField, groupName);
		LOG.info("Group Name " + groupName + " entered");
	}

	public void enterFamilyName() {
		String familyName = PropertyDataReader.getProperty("Data.properties", "familyName");
		WebUtility.enterText(familyNameField, familyName);
		LOG.info("Family Name " + familyName + " entered");
	}

	public void clickOnSearch() {
		WebUtility.click(searchButton);
		WebUtility.implicitWait(5);
		LOG.info("Clicked on Search button");
	}

	public void selectUserListForTo() {
		WebUtility.click(userListCheckbox);
		WebUtility.click(selectToButton);
		WebUtility.implicitWait(5);
		LOG.info("Selected user list for To");
	}

	public void clickOnOk() {
		WebUtility.click(okButton);
		WebUtility.implicitWait(5);
		LOG.info("Clicked on OK button");
	}
}
