package com.test.utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.base.TestBase;

public class WebUtility extends TestBase {
	public static Logger LOG = Logger.getLogger(WebUtility.class.getName());

	/**
	 * @author Manav C K This function will Check if element is Displayed or not.
	 * @param element
	 * @return enabled status
	 */

	public static boolean isElementDisplayed(WebElement element) {
		WebUtility.waitForElementVisivility(element);
		return element.isDisplayed();
	}

	/**
	 * @author Manav C K This method will
	 * @param webelement
	 */

	public static void waitForElementVisivility(WebElement webelement) {
		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.visibilityOf(webelement));
	}

	/**
	 * @author Manav C K It will highlight the web element
	 * @param element
	 */

	public static void highlightWebElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].setAttribute('style', 'background:#ffffb3; border:3px solid green;');", element);
	}

	/**
	 * @author Manav C K It clicks on given web element using javascript.
	 * @param element
	 */

	public static void jsClick(WebElement element) {
		highlightWebElement(element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	/**
	 * @author Manav C K It will click on a given locator.
	 * @param locator
	 */
	public static void click(WebElement element) {
		try {

			waitForElementVisivility(element);
			highlightWebElement(element);
			element.click();
			LOG.info("Clicking on : [{}]" + element);
		} catch (Exception e) {
			System.out.println("Exception occurred while clicking" + element);
			LOG.error("Exception occurred while clicking : " + e.getMessage());
		}
	}

	/**
	 * @author Manav C K This function will highlight and clear the text box and
	 *         enters the String value
	 * @param locator
	 * @param text
	 */

	public static void enterText(WebElement element, String text) {

		waitForElementVisivility(element);
		highlightWebElement(element);
		element.clear();
		element.sendKeys(text);
	}

	/**
	 * @author Manav C K This function will highlight and clear the text box
	 * @param locator
	 * @param text
	 */

	public static void clearText(WebElement element) {
		highlightWebElement(element);
		element.clear();
	}

	/**
	 * @author Manav C K This function will get the screenshot and return the
	 *         location of saved screenshot
	 * @param locator
	 * @return innertext
	 * @throws IOException
	 */

	public static String getScreenshot(File file, String screenshotName) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = file + "\\" + screenshotName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	/**
	 * @author Manav C K This function will get the folder
	 * @param locator
	 * @return
	 * @return innertext
	 * @throws IOException
	 */

	public static File createFolderWithDate(String foldername) {
		String todaysDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		File folder = new File(
				System.getProperty("user.dir") + "/failedTestsScreenshots/" + foldername + "_" + todaysDate);
		if (!folder.exists()) {
			if (folder.mkdir()) {
			} else {
				System.out.println("Failed to create directory!");
			}
		}
		return folder;
	}

	static String imageFolderName;

	public void createFolder() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH_mm_ssa");
		Calendar cal = Calendar.getInstance();
		String path = System.getProperty("user.dir") + "\\target\\log\\genie-report\\image\\";
		imageFolderName = path + dateFormat.format(cal.getTime());
		new File(imageFolderName).mkdirs();
	}

	/**
	 * @author Manav C K This function will get the folder
	 * @param locator
	 * @return File
	 * @throws IOException
	 */

	public static File createFolder(String foldername) {

		File folder = new File(System.getProperty("user.dir") + "\\test-output\\" + foldername);
		if (!folder.exists()) {
			if (folder.mkdir()) {
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}
		return folder;
	}

	/**
	 * Thread.sleep method
	 * 
	 * @param seconds
	 */
	public static void wait(int seconds) {
		LOG.info(Thread.currentThread().getStackTrace()[2].getMethodName() + " Waiting for some time unit: "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception e) {
			LOG.fatal("Exception occured: ", e);
		}
	}

	/**
	 * Implicit wait method
	 * 
	 * @param seconds
	 */
	public static void implicitWait(long seconds) {
		LOG.info(Thread.currentThread().getStackTrace()[2].getMethodName() + " Waiting for some time unit: "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
		try {
			driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
		} catch (Exception e) {
			LOG.fatal("Exception occured: ", e);
		}
	}

	public static void selectValueByValue(WebElement element, String value) {
		click(element);
		Select select = new Select(element);
		select.selectByValue(value);
	}

	public static void selectValueByVisibiltyText(WebElement element, String value) {
		click(element);
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}
	/**
	 * Method to get attribute
	 * 
	 * @param element
	 * @param attributeType
	 * @return
	 */
	public static String getAttribute(WebElement element, String attributeType) {
		String attribute = null;
		try {
			attribute = element.getAttribute(attributeType);
			return attribute;
		} catch (Exception e) {
			LOG.fatal("Exception occured: ", e);
		}
		return attribute;
	}

	public static String getText(WebElement element) {
		String text = null;
		try {
			text = element.getText().trim();
			return text;
		} catch (Exception e) {
			LOG.fatal("Exception occured: ", e);
		}
		return text;
	}

	public static void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
	}

	public static void switchToDefaultFrame() {
		driver.switchTo().defaultContent();
	}

	public static void explicitWait(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			LOG.fatal("Exception occured: ", e);
		}
	}
}
