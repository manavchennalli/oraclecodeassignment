package com.test.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

/**
 * @author akanksha.a.kumar This is the base class created to perform the
 *         initial set up required for script execution.
 * 
 *         Reading config.properties file. An object of WebDriver is created.
 *         Browser, Desired Capability settings. Browser related actions like,
 *         Maximize browser, delete cookies. Page load time and Implicit waits
 *         are implemented. Launching URL and storing/ fetching URL Credentials
 * 
 */
public class TestBase {

	public static Logger LOG = Logger.getLogger(TestBase.class.getName());
	public static WebDriver driver;
	private static String currentDriver;
	public static Properties prop;
	private static final String IE_DRIVER = "IEDriver";
	private static final String CHROME_DRIVER = "ChromeDriver";
	private static final String FIREFOX_DRIVER = "FirefoxDriver";
	private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
	private static final String WEBDRIVER_IE_DRIVER = "webdriver.ie.driver";
	private static final String WEBDRIVER_FF_DRIVER = "webdriver.gecko.driver";
	private static final String CHROME_DRIVER_LOC = System.getProperty("user.dir") + "/drivers/chromedriver.exe";
	private static final String IE_DRIVER_LOC = System.getProperty("user.dir") + "/drivers/IEDriverServer.exe";
	private static final String FF_DRIVER_LOC = System.getProperty("user.dir") + "/drivers/geckodriver.exe";

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/resources/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialization() {
		String browserName = prop.getProperty("browser");

		if (browserName.equals(CHROME_DRIVER)) {
			LOG.debug("Driver location: " + CHROME_DRIVER_LOC);
			System.setProperty(WEBDRIVER_CHROME_DRIVER, CHROME_DRIVER_LOC);
			System.setProperty("webdriver.chrome.silentOutput", "true");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			options.addArguments("start-maximized");
			options.addArguments("--js-flags=--expose-gc");
			options.addArguments("--enable-precise-memory-info");
			options.addArguments("--disable-popup-blocking");
			options.addArguments("--disable-default-apps");
			options.addArguments("--enable-automation");
			options.addArguments("test-type=browser");
			options.addArguments("disable-infobars");
			options.addArguments("disable-extensions");
			options.setExperimentalOption("useAutomationExtension", false);
			driver = new ChromeDriver(options);
			LOG.info("Chrome Driver Launched Successfully");

		} else if (browserName.equals(FIREFOX_DRIVER)) {
			LOG.debug("Driver Location: " + FF_DRIVER_LOC);
			System.setProperty(WEBDRIVER_FF_DRIVER, FF_DRIVER_LOC);
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "null");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			LOG.info("Firefox Driver Launched Successfully");
			
		} else if (browserName.equals(IE_DRIVER)) {
			LOG.debug("Driver location: " + IE_DRIVER_LOC);
			System.setProperty(WEBDRIVER_IE_DRIVER, IE_DRIVER_LOC);
			InternetExplorerOptions iCaps = new InternetExplorerOptions();
			iCaps.ignoreZoomSettings();
			driver = new InternetExplorerDriver(iCaps);
			driver.manage().window().maximize();
			LOG.info("IE Driver Launched Successfully");
		} else {
			LOG.fatal("Browser Type is invalid!1");
		}
		driver.manage().deleteAllCookies();
	}

	/**
	 * 
	 * @return currentDriver
	 */
	public static synchronized WebDriver getInstance() {
		return getInstance(currentDriver);
	}

	/**
	 * Get an instance of Web Driver
	 * 
	 * @param type
	 * @return driver
	 */

	public static synchronized WebDriver getInstance(String type) {

		if (driver == null) {
			return null;
		}
		return driver;
	}

	/**
	 * Method to close driver
	 */
	public static void quitDriver() {
		driver.quit();
		driver = null;
		LOG.info("WebDriver Closed Successfully");
	}

}