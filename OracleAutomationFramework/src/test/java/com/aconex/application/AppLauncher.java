package com.aconex.application;

import com.test.base.TestBase;
import com.test.utility.WebUtility;

public enum AppLauncher {

	TEST_URL("TEST", "https://qa122.aconex.com");

	private final String env;
	private final String url;

	private AppLauncher(String env, String url) {
		this.env = env;
		this.url = url;
	}

	public String getEnv() {
		return env;
	}

	public String getUrl() {
		return url;
	}

	public void launchUrl(AppLauncher env) {
		TestBase.driver.get(env.getUrl());
		WebUtility.implicitWait(10);
	}
}
