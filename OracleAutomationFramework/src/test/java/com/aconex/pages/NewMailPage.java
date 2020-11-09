package com.aconex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.test.base.TestBase;
import com.test.utility.PropertyDataReader;
import com.test.utility.WebUtility;

public class NewMailPage extends TestBase {

	@FindBy(how = How.XPATH, using = "//tr[1]//button/div/div")
	public WebElement directoryLink;

	@FindBy(how = How.XPATH, using = "//select[@id='Correspondence_correspondenceTypeID']")
	public WebElement typeDropdown;

	@FindBy(how = How.XPATH, using = "//input[@id='Correspondence_subject']")
	public WebElement subjectField;

	@FindBy(how = How.XPATH, using = "//div[@id='multiselect0']")
	public WebElement attribute1Link;

	@FindBy(how = How.XPATH, using = "//div[@id='attributeBidi_PRIMARY_ATTRIBUTE']//input")
	public WebElement attribute1FilterField;

	@FindBy(how = How.XPATH, using = "//div[@id='attributeBidi_SECONDARY_ATTRIBUTE']//input")
	public WebElement attribute2FilterField;

	@FindBy(how = How.XPATH, using = "//button[@id='attributeBidi_PRIMARY_ATTRIBUTE_add']/div/div")
	public WebElement attribute1AddItemBtn;

	@FindBy(how = How.XPATH, using = "//button[@id='attributeBidi_SECONDARY_ATTRIBUTE_add']/div/div")
	public WebElement attribute2AddItemBtn;

	@FindBy(how = How.XPATH, using = "//button[@id='attributePanel-commit']//div[text()='OK']")
	public WebElement attributePanelOkBtn;

	@FindBy(how = How.ID, using = "btnSend")
	public WebElement sendButton;

	@FindBy(how = How.XPATH, using = "//div[@class='auiDetails-value']/span")
	public WebElement sentDetails;

	@FindBy(how = How.ID, using = "frameMain")
	public WebElement newMailPageIframe;

	@FindBy(how = How.XPATH, using = "//h1[text()='View Mail']")
	public WebElement viewEmailHeader;

	public NewMailPage() {
		PageFactory.initElements(driver, this);
	}

	public void switchToNewMailPageFrame() {
		WebUtility.switchToFrame(newMailPageIframe);
	}

	public void selectType() {
		String type = PropertyDataReader.getProperty("Data.properties", "type");
		// WebUtility.selectValueByValue(typeDropdown, type);
		WebUtility.click(typeDropdown);
		String dynamicXpath = "//option[@name='" + type + "']";
		driver.findElement(By.xpath(dynamicXpath)).click();
		LOG.info("Selected " + type + " Type");
	}

	public void clickOnDirectory() {
		WebUtility.click(directoryLink);
		LOG.info("Clicked on Directory link");
	}

	public void enterSubject() {
		String subject = PropertyDataReader.getProperty("Data.properties", "subject");
		WebUtility.enterText(subjectField, subject);
		LOG.info("Subject " + subject + " entered");
	}

	public void selectAttributes() {
		WebUtility.click(attribute1Link);
		String attribute1Value = PropertyDataReader.getProperty("Data.properties", "attribute1");
		String attribute2Value = PropertyDataReader.getProperty("Data.properties", "attribute2");
		WebUtility.enterText(attribute1FilterField, attribute1Value);
		String att1 = "//option[text()='att1']".replace("att1", attribute1Value);
		driver.findElement(By.xpath(att1)).click();
		WebUtility.click(attribute1AddItemBtn);
		WebUtility.enterText(attribute2FilterField, attribute2Value);
		String att2 = "//option[text()='" + attribute2Value + "']";
		driver.findElement(By.xpath(att2)).click();
		WebUtility.click(attribute2AddItemBtn);
		WebUtility.click(attributePanelOkBtn);
		LOG.info("Attribute1 " + attribute1Value + " and Attribute2 " + attribute2Value + " selected");
	}

	public void clickOnSend() {
		WebUtility.click(sendButton);
		WebUtility.explicitWait(viewEmailHeader);
		LOG.info("Clicked on Send button");
	}

	public void verifyIfMailSent() {
		String mailSentDetails = WebUtility.getText(sentDetails);
		Assert.assertEquals(mailSentDetails.isEmpty(), false);
		LOG.info("Verified if mail is sent. Mail sent details " + mailSentDetails);
	}
}
