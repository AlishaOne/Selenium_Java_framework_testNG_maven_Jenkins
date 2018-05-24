package com.testcase;

import org.apache.log4j.PropertyConfigurator;
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
import com.log4j.Log;
import com.pageobject.Login;
import com.pageobject.SetupTeardown;
import com.utility.Constant;
import com.utility.ExcelUtils;
import com.utility.Utils;

public class TCEditPost001 extends SetupTeardown {
	private  static String sTestCaseName;
	private static int iTestCaseRow;
	@BeforeClass
	public void setUpEdit() throws Exception{
		PropertyConfigurator.configure("./log/log4j.Properties");
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Sheet2");
		//sTestCaseName = this.toString();
		try {
			sTestCaseName = this.toString();
			sTestCaseName = Utils.getTestCaseName(sTestCaseName);
			Log.info("sTestCaseName is "+sTestCaseName);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName, Constant.Col_TestCaseName);
			Log.info("iTestCaseRow is "+iTestCaseRow);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		Login.openWebsite(iTestCaseRow);
		Login.loginWebsite(iTestCaseRow);
	}
	@Test
	public void editPost() throws Exception{
		Log.info("====================starting editpost====================");
		String postTitle= ExcelUtils.getCellData(iTestCaseRow, Constant.Col_PostTitle);
		String postContent = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_PostContent);
		Log.info("post title from excel is "+postTitle);
		Log.info("postContent from excel is "+postContent);
		Login.customImplicitlyWaiPlus();
		//click My Site
		FindElementHelper.textWebElement("div>header#header>a:nth-child(1)").click();
		Log.info("My site button is clicked");
		Login.customImplicitlyLongWait();
		Login.getTitle();
		Assert.assertTrue(Login.getTitle().contains("Stats"));
		Login.customImplicitlyLongWait();
		Login.customImplicitlyWaiPlus();
		//click Blog Posts
		FindElementHelper.textWebElement(".sidebar__menu>ul>li[data-post-type='post']>a>span.menu-link-text").click();
		Log.info("Blog Posts button is clicked");
		Login.customImplicitlyLongWait();
		Login.customImplicitlyWaiPlus();
		Login.getTitle();
		//Assert.assertTrue(Login.getTitle().contains("Blog Posts"));
		//click post title
		Login.customImplicitlyWaiPlus();
		FindElementHelper.textWebElement("a>h4.post__title").click();
		Log.info("Post title is clicked in order to edit post");
		Login.customImplicitlyWaiPlus();
		//dismiss alert
//		if(FindElementHelper.textWebElement(".button.form-button.is-primary").isEnabled()){
//			FindElementHelper.textWebElement(".button.form-button.is-primary").click();
//		}

		GenHelper.alertAccept();
//		GenHelper.dialogSubmit(".ReactModal__Content.ReactModal__Content--after-open.dialog.card"
//				,".button.form-button.is-primary");
		//edit post title
		FindElementHelper.textWebElement("textarea.textarea-autosize.editor-title__input").sendKeys(postTitle);
		Log.info("Post title is edited");
//		//click visual style
//		FindElementHelper.textWebElement(".segmented-control__item.is-selected").click();
//		//click html style
//		FindElementHelper.textWebElement(".segmented-control__item.is-selected>a").click();
		//edit post
		FindElementHelper.textWebElement(".tinymce.is-visible").sendKeys(postContent);
		Log.info("Post content is edited");
		Login.customImplicitlyWaiPlus();
		Log.info("alert is present : "+GenHelper.isAlertPresent());
		//publishing post
		FindElementHelper.textWebElement(".button.editor-publish-button.is-primary").click();
		Log.info("Publish button is clicked");
		Login.customImplicitlyLongWait();
		Login.getTitle();
		Assert.assertTrue(Login.getTitle().contains("Edit Post"));
		Log.info("====================ending editpost====================");
		GenHelper.alertAccept();
		GenHelper.closeWinSwitchToParentWindow();
//		GenHelper.dialogSubmit(".ReactModal__Content.ReactModal__Content--after-open.dialog.card"
//				,".button.form-button.is-primary");				
		//.button.form-button.is-primary
	}
	@AfterClass(alwaysRun=true)
	public void tearDownEdit(){
//		GenHelper.dialogSubmit(".ReactModal__Content.ReactModal__Content--after-open.dialog.card"
//		,".button.form-button");
		SetupTeardown.tearDown();
	}
//	@AfterTest(alwaysRun=true)
//	public void closeDriver() {
//		Login.logout();
//		System.out.println("=====================aftere test-->edit post test case========================");
//		//GenHelper.alertAccept();
//	}
	
}
