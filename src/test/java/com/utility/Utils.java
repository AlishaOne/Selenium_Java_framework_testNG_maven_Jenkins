package com.utility;

import java.io.File;

import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;

import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

	public static WebDriver driver = null;

	public static WebDriver OpenBrowser(int iTestCaseRow) throws Exception {

		String sBrowserName;

		try {

			sBrowserName = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_Browser);

			if (sBrowserName.equals("FireFox")) {

				driver = new FirefoxDriver();

				Log.info("New driver instantiated");

				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

				Log.info("Implicit wait applied on the driver for 10 seconds");

				driver.get(Constant.URL);

				Log.info("Web application launched successfully");

			}

		} catch (Exception e) {

			Log.error("Class Utils | Method OpenBrowser | Exception desc : " + e.getMessage());

		}

		return driver;

	}

	public static String getTestCaseName(String sTestCase) throws Exception {

		String value = sTestCase;

		try {

			int posi = value.indexOf("@");

			value = value.substring(0, posi);

			posi = value.lastIndexOf(".");

			value = value.substring(posi + 1);

			return value;

		} catch (Exception e) {

			Log.error("Class Utils | Method getTestCaseName | Exception desc : " + e.getMessage());

			throw (e);

		}

	}


	public static void waitForElement(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 10);

		wait.until(ExpectedConditions.elementToBeClickable(element));

	}


}
