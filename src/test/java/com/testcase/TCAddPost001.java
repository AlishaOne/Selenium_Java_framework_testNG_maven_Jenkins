package com.testcase;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.helpers.FindElementHelper;
import com.helpers.GenHelper;
import com.log4j.Log;
import com.pageobject.Login;
import com.pageobject.SetupTeardown;
import com.utility.Constant;
import com.utility.ExcelUtils;
import com.utility.Utils;

import java.lang.reflect.Method;

public class TCAddPost001 {
	private static String sTestCaseName;
	private static int iTestCaseRow;

	@BeforeClass
	public void setUpAdd() throws Exception {
		PropertyConfigurator.configure("./log/log4j.Properties");
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Sheet1");
		// sTestCaseName = this.toString();
		try {
			sTestCaseName = this.toString();
			sTestCaseName = Utils.getTestCaseName(sTestCaseName);
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

	@Test(priority = 1, invocationCount = 1)
	public void addPost() throws Exception {
		Method method = null;
		Log.info("sTestCaseName is " + sTestCaseName);
		Log.info("iTestCaseRow is " + iTestCaseRow);
		String postTitle = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_PostTitle);
		String postContent = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_PostContent);
		Log.info("post title from excel is " + postTitle);
		Log.info("postContent from excel is " + postContent);
		// name= new Object(){}.getClass().getEnclosingMethod().getName();
		Log.info("====================starting " + method.getName() + "addPost====================");
		Reporter.log("====================starting addpost====================");
		// dismiss alert
		GenHelper.alertAccept();
		// click write button
		FindElementHelper.textWebElement("a.masterbar__item.masterbar__item-new").click();
		Log.info("Write button is clicked");
		Login.customImplicitlyLongWait();
		Login.getTitle();
		// Assert.assertTrue(Login.getTitle().contains("New Post"));
		// post setting
		boolean bp = FindElementHelper.textWebElement(".button.editor-sidebar__back.is-compact.is-borderless")
				.isDisplayed();
		if (bp) {
			FindElementHelper.textWebElement(".button.editor-sidebar__back.is-compact.is-borderless").click();
		}
		// input post title
		FindElementHelper.textWebElement("textarea.textarea-autosize.editor-title__input").sendKeys(postTitle);
		Log.info("Post title is entered");
		// click visual style
		FindElementHelper.textWebElement(".segmented-control__item.is-selected").click();
		// click html style
		FindElementHelper.textWebElement(".segmented-control__item.is-selected>a").click();
		Login.customImplicitlyLongWait();
		// Login.customImplicitlyLongWait();
		// writing post
		// #tinymce>p
		FindElementHelper.getElement("#tinymce-1").sendKeys(postContent);
		// FindElementHelper.getElement(".tinymce.is-visible").sendKeys("8888");
		Log.info("Post writing is done");
		Login.customImplicitlyLongWait();
		// publishing post
		FindElementHelper.textWebElement(".button.editor-publish-button.is-primary").click();
		Log.info("Publish button is clicked");
		Login.customImplicitlyLongWait();
		GenHelper.alertDismiss();
		Login.getTitle();
		Assert.assertTrue(Login.getTitle().contains("Edit Post"));
		Reporter.log("ending addpost");
		Log.info("====================ending addpost====================" + "\n");
	}

	@AfterClass(alwaysRun = true)
	public void tearDownAdd() {
		GenHelper.alertDismiss();
		SetupTeardown.tearDown();
	}

}
