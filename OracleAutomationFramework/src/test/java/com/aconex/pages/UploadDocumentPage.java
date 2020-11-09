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

public class UploadDocumentPage extends TestBase {

	@FindBy(how = How.XPATH, using = "//input[@id='docno_0']")
	public WebElement documentNumberField;

	@FindBy(how = How.XPATH, using = "//input[@name='revision_0']")
	public WebElement revisionField;

	@FindBy(how = How.XPATH, using = "//input[@name='title_0']")
	public WebElement titleField;

	@FindBy(how = How.XPATH, using = "//select[@id='doctype_0']")
	public WebElement documentTypeDropdown;

	@FindBy(how = How.XPATH, using = "//select[@id='docstatus_0']")
	public WebElement documentStatusDropdown;

	@FindBy(how = How.XPATH, using = "//select[@id='discipline_0']")
	public WebElement disciplineDropdown;

	@FindBy(how = How.XPATH, using = "//select[@id='vdrcode_0']")
	public WebElement vdrCodeDropdown;

	@FindBy(how = How.XPATH, using = "//select[@id='category_0']")
	public WebElement categoryDropdown;

	@FindBy(how = How.XPATH, using = "//div[@id='attribute1_0']")
	public WebElement attributeLink;

	@FindBy(how = How.XPATH, using = "//div[@id='attribute1_0_bidi']//input")
	public WebElement attributeFilterField;

	@FindBy(how = How.XPATH, using = "//button[@id='attribute1_0_bidi_add']")
	public WebElement attributeAddButton;

	@FindBy(how = How.XPATH, using = "//button[@id='attribute1_0_panel-commit']")
	public WebElement attributePanelOkButton;

	@FindBy(how = How.XPATH, using = "//div[@id='attribute2_0']")
	public WebElement starCategoryLink;

	@FindBy(how = How.XPATH, using = "//div[@id='attribute2_0_bidi']//input")
	public WebElement starCategoryFilterField;

	@FindBy(how = How.XPATH, using = "//button[@id='attribute2_0_bidi_add']")
	public WebElement starCategoryAddButton;

	@FindBy(how = How.XPATH, using = "//button[@id='attribute2_0_panel-commit']")
	public WebElement starCategoryOkButton;

	@FindBy(how = How.XPATH, using = "//input[@name='printsize_0']")
	public WebElement printSizeField;

	@FindBy(how = How.XPATH, using = "//button[@id='btnUploadDocument']")
	public WebElement uploadButton;

	@FindBy(how = How.XPATH, using = "//span[@class='uiPanel-title']")
	public WebElement documentUploadSuccessMsg;

	@FindBy(how = How.ID, using = "frameMain")
	public WebElement uploadDocumentPageIFrame;

	public UploadDocumentPage() {
		PageFactory.initElements(driver, this);
	}

	public void performNewDocumentUpload() {
		switchToUploadDocumentPageIFrame();
		enterDocumentNumber();
		enterRevision();
		enterTitle();
		selectDocumentType();
		selectDocumentStatus();
		selectDiscipline();
		selectVDRCode();
		selectCategory();
		selectAttribute();
		selectGreenStarCategory();
		enterPrintSize();
		clickOnUpload();
		verifyIfDocumentUploadSuccessfull();
		WebUtility.switchToDefaultFrame();
	}

	public void switchToUploadDocumentPageIFrame() {
		WebUtility.switchToFrame(uploadDocumentPageIFrame);
	}

	public void enterDocumentNumber() {
		String documentNo = PropertyDataReader.getProperty("Data.properties", "documentNo");
		WebUtility.enterText(documentNumberField, documentNo);
		LOG.info("Document number is entered");
	}

	public void enterRevision() {
		String revision = PropertyDataReader.getProperty("Data.properties", "revision");
		WebUtility.enterText(revisionField, revision);
		LOG.info("Revision is entered");
	}

	public void enterTitle() {
		String title = PropertyDataReader.getProperty("Data.properties", "title");
		WebUtility.enterText(titleField, title);
		LOG.info("Title is entered");
	}

	public void selectDocumentType() {
		String type = PropertyDataReader.getProperty("Data.properties", "type");
		WebUtility.selectValueByVisibiltyText(documentTypeDropdown, type);
		LOG.info("Document Type " + type + " is selected");
	}

	public void selectDocumentStatus() {
		String status = PropertyDataReader.getProperty("Data.properties", "status");
		WebUtility.selectValueByVisibiltyText(documentStatusDropdown, status);
		LOG.info("Document Status " + status + " is selected");
	}

	public void selectDiscipline() {
		String discipline = PropertyDataReader.getProperty("Data.properties", "discipline");
		WebUtility.selectValueByVisibiltyText(disciplineDropdown, discipline);
		LOG.info("Discipline " + discipline + " is selected");
	}

	public void selectVDRCode() {
		String vdrcode = PropertyDataReader.getProperty("Data.properties", "vdrcode");
		WebUtility.selectValueByVisibiltyText(vdrCodeDropdown, vdrcode);
		LOG.info("VDR Code " + vdrcode + " is selected");
	}

	public void selectCategory() {
		String category = PropertyDataReader.getProperty("Data.properties", "category");
		WebUtility.selectValueByVisibiltyText(categoryDropdown, category);
		LOG.info("Category " + category + " is selected");
	}

	public void selectAttribute() {
		WebUtility.click(attributeLink);
		String attributeValue = PropertyDataReader.getProperty("Data.properties", "attribute");
		WebUtility.enterText(attributeFilterField, attributeValue);
		String attributeXpath = "//option[text()='attribute']".replace("attribute", attributeValue);
		driver.findElement(By.xpath(attributeXpath)).click();
		WebUtility.click(attributeAddButton);
		WebUtility.click(attributePanelOkButton);
		LOG.info("Attribute " + attributeValue + " selected");
	}

	public void selectGreenStarCategory() {
		WebUtility.click(starCategoryLink);
		String starCategoryValue = PropertyDataReader.getProperty("Data.properties", "starCategory");
		WebUtility.enterText(starCategoryFilterField, starCategoryValue);
		String categoryXpath = "//option[text()='starcategory']".replace("starcategory", starCategoryValue);
		driver.findElement(By.xpath(categoryXpath)).click();
		WebUtility.click(starCategoryAddButton);
		WebUtility.click(starCategoryOkButton);
		LOG.info("Green Star Category " + starCategoryValue + " selected");
	}

	public void enterPrintSize() {
		String printSizeValue = PropertyDataReader.getProperty("Data.properties", "printsize");
		WebUtility.enterText(printSizeField, printSizeValue);
		LOG.info("Print Size " + printSizeValue + " entered");
	}

	public void clickOnUpload() {
		WebUtility.click(uploadButton);
		WebUtility.explicitWait(documentUploadSuccessMsg);
		LOG.info("Clicked on upload");
	}

	public void verifyIfDocumentUploadSuccessfull() {
		String successMsgActual = WebUtility.getText(documentUploadSuccessMsg);
		Assert.assertEquals(successMsgActual, "Document Uploaded Successfully");
		LOG.info("Verified and Document uploaded successfully");
	}
}
