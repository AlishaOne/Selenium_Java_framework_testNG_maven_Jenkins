package com.pageobject;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.helpers.GenHelper;
import com.utility.Constant;
import com.utility.ExcelUtils;
import com.utility.Utils;

public class SetupTeardown extends BaseClass{
	private  static String sTestCaseName;
	private static int iTestCaseRow;
	public static String getTestCaseName(){
		try {
			sTestCaseName = Utils.getTestCaseName(sTestCaseName.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sTestCaseName;
		
	}

	
	public  static void setUp() throws Exception{
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Sheet1");
		sTestCaseName = SetupTeardown.getTestCaseName();
		iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName, Constant.Col_TestCaseName);
		Login.openWebsite(iTestCaseRow);
		Login.loginWebsite(iTestCaseRow);
		//Login.customImplicitlyWaiPlus();
		//Login.getTitle();
		//Assert.assertTrue(Login.getTitle().contains("Following"));
	}

	public static void tearDown() {
		//GenHelper.closeWinSwitchToParentWindow();
		//GenHelper.alertAccept();
		Login.logout();
		

	}

}
