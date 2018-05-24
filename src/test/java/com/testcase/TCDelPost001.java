package com.testcase;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.helpers.FindElementHelper;
import com.helpers.GenHelper;

import java.lang.reflect.Method;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Rule;
import org.junit.rules.*;
import com.log4j.Log;
import com.pageobject.Login;
import com.pageobject.SetupTeardown;
import com.utility.Constant;
import com.utility.ExcelUtils;
import com.utility.Utils;

public class TCDelPost001 extends SetupTeardown {
	private static String sTestCaseName;
	private static int iTestCaseRow;

	@BeforeClass
	public void setUpAdd() throws Exception {
		// SetupTeardown.setUp();
		PropertyConfigurator.configure("./log/log4j.Properties");
		Log.info("=====================before class--" + this.getClass().getName()
				+ " test case========================");

		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Sheet1");
		// sTestCaseName = this.toString();
		try {
			sTestCaseName = this.toString();
			sTestCaseName = Utils.getTestCaseName(sTestCaseName);
			Log.info("sTestCaseName is " + sTestCaseName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName, Constant.Col_TestCaseName);
			Log.info("iTestCaseRow is " + iTestCaseRow);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Login.openWebsite(iTestCaseRow);
		Login.loginWebsite(iTestCaseRow);
	}

	@Test(priority = 1)
	public void setUpDelPost() {
		Log.info("====================starting setUpDelPost=======================");

		// Log.info("====================starting
		// "+GenHelper.getMethodName()+"=======================");
		// click My Site
		FindElementHelper.textWebElement("div>header#header>a:nth-child(1)").click();
		Log.info("My Site button is clicked");
		Login.customImplicitlyLongWait();
		Login.getTitle();
		Assert.assertTrue(Login.getTitle().contains("Stats"));
		// click Blog Posts
		FindElementHelper.textWebElement(".sidebar__menu>ul>li[data-post-type='post']>a>span.menu-link-text").click();
		Log.info(" Blog Posts button is clicked");
		Login.customImplicitlyLongWait();
		Login.getTitle();
		// Assert.assertTrue(Login.getTitle().contains("Blog Posts"));
		Log.info("====================ending setUpDelPost=======================");

	}

	@Test(priority = 2, invocationCount = 2)
	public void delPost() {
		Log.info("====================starting delPost=======================");
		// click more
		Login.customImplicitlyWaiPlus();
		FindElementHelper.textWebElement("a.post-controls__more").click();
		Log.info(" More option is clicked");
		// click trash
		FindElementHelper.textWebElement("a.post-controls__trash").click();
		Log.info(" Trash option is clicked");
		Login.customImplicitlyLongWait();
		Login.getTitle();
		Assert.assertTrue(
				FindElementHelper.textWebElement("div>span.conf-alert_title").getText().contains("Moved to Trash"));
		Log.info("====================ending delPost=========================");

	}

	@AfterClass(alwaysRun = true)
	public void tearDownAdd() {
		SetupTeardown.tearDown();
	}

}
