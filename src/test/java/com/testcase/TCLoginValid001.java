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
import com.utility.Constant;
import com.utility.ExcelUtils;
import com.utility.Utils;

public class TCLoginValid001 {
	private  String sTestCaseName;
	private int iTestCaseRow;

	@BeforeClass
	public void loginUrlva() throws Exception{
		PropertyConfigurator.configure("./log/log4j.Properties");
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "loginvalid");
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
		//System.setProperty("webdriver.gecko.driver", "D:\\WPworkspace\\wpproject\\src\\test\\java\\com\\resources\\geckodriver.exe");
		Login.openWebsite(iTestCaseRow);
	}
	@Test
	public void loginWebsiteva(){

		Log.info("====================starting login====================");
		Login.loginWebsite(iTestCaseRow);
		Login.customImplicitlyLongWait();
		Login.getTitle();
		Assert.assertTrue(Login.getTitle().contains("Following"));
		Log.info("====================ending login====================");
		GenHelper.closeWinSwitchToParentWindow();
	}
	
	@AfterClass(alwaysRun=true)
	public void closeDriver() {
		Login.logout();
		//GenHelper.alertAccept();
	}
	
}
