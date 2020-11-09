package com.aconex.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.test.base.TestBase;
import com.test.utility.CipherManager;
import com.test.utility.WebUtility;

public class LoginPage extends TestBase {

	@FindBy(how = How.ID, using = "userName")
	public WebElement userNameField;

	@FindBy(how = How.ID, using = "password")
	public WebElement passwordField;

	@FindBy(how = How.ID, using = "login")
	public WebElement loginButton;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public void login(String userName, String password) {

		WebUtility.enterText(userNameField, userName);
		WebUtility.enterText(passwordField, CipherManager.decode(password));
		WebUtility.click(loginButton);
		WebUtility.implicitWait(10);

		LOG.info("Logged into Oracle Aconex application");
	}

}
