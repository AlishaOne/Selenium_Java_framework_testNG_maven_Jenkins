package com.pageobject;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import com.helpers.FindElementHelper;
import com.helpers.GenHelper;
import com.log4j.Log;
import com.utility.Constant;
import com.utility.ExcelUtils;

public class Login extends BaseClass {
	public static String WebTitle;

	public Login(WebDriver driver) {
		super(driver);
	}

	// you can parameter driver too
	// public static void openWebsite(String webUrl) {
	public static void openWebsite(int iTestCaseRow) {
		String browserName = null;
		PropertyConfigurator.configure("./log/log4j.Properties");
		Log.info("===========================Open Website===========================");

		try {
			browserName = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_Browser);
			Log.info("Browser name is " + browserName);
			if (browserName.equalsIgnoreCase("FireFox")) {
				System.setProperty("webdriver.gecko.driver", GenHelper.getPath() + "geckodriver.exe");
				driver = new FirefoxDriver();
			}
			if (browserName.equalsIgnoreCase("Chrome")) {
				// pay attention about the chrome version
				System.setProperty("webdriver.chrome.driver", GenHelper.getPath() + "chromedriver.exe");
				ChromeOptions opt = new ChromeOptions();
				// opt.addArguments("Proxy = null");
				opt.addArguments("test-type");
				opt.addArguments("test-type=browser");
				opt.addArguments("disable-extensions");
				opt.addArguments("no-sandbox");
				opt.addArguments("disable-infobars");
				opt.addArguments("start-maximized");
				opt.addArguments("--js-flags=--expose-gc");
				opt.addArguments("--enable-precise-memory-info");
				opt.addArguments("--disable-popup-blocking");
				opt.addArguments("--disable-default-apps");
				DesiredCapabilities cap = DesiredCapabilities.chrome();
				cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				cap.setCapability(ChromeOptions.CAPABILITY, opt);
				driver = new ChromeDriver(cap);
				// driver = new ChromeDriver(opt);

			}
			if (browserName.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", GenHelper.getPath() + "IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			driver.manage().window().maximize();
			driver.get(Constant.URL);

		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Browser/URL is not able to start. ");
		}
		// return driver;
	}

	public static void loginWebsite(int iTestCaseRow) {
		Log.info("===========================login website===========================");
		WebElement username, password, checkbox, loginbutton;
		String txtusername;
		String txtpassword;
		try {
			txtusername = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_UserName);
			txtpassword = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_Password);
			username = FindElementHelper.getElement("#usernameOrEmail");
			username.sendKeys(txtusername);
			Log.info("user name entered");
			password = FindElementHelper.getElement("#password");
			password.sendKeys(txtpassword);
			Log.info("password entered");
			checkbox = FindElementHelper.getElement(".form-checkbox");
			checkbox.isSelected();
			loginbutton = FindElementHelper.getElement(".button.form-button.is-primary");
			loginbutton.submit();
			Log.info("login button clicked");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Its not able to get username/password from excel file.");
		}

	}

	public static String getTitle() {
		WebTitle = driver.getTitle();
		System.out.println("WebTitle is :" + WebTitle);
		return WebTitle;
	}

	public static void customImplicitlyWaiPlus() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			for (int i = 0; i <= 5; i++) {
				Thread.sleep(1000);
				System.out.println("Printing line " + i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void customImplicitlyLongWait() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			for (int i = 0; i <= 5; i++) {
				Thread.sleep(2000);
				System.out.println("Printing line " + i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void logout() {
		Log.info("=================================Starting tearDown================================");

		GenHelper.alertAccept();
		GenHelper.closeWinSwitchToParentWindow();
		try {
			if (driver != null) {
				// driver.close();
				driver.quit();
				driver = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
