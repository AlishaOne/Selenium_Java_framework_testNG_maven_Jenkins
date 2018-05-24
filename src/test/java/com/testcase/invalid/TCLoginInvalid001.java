package com.testcase.invalid;

import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.helpers.FindElementHelper;
import com.helpers.GenHelper;
import com.log4j.Log;
import com.pageobject.BaseClass;
import com.pageobject.Login;
import com.utility.Constant;
import com.utility.ExcelUtils;
import com.utility.Utils;

public class TCLoginInvalid001 {
	//String webUrl="https://wordpress.com/wp-login.php?redirect_to=https%3A%2F%2Fwordpress.com%2F";
	private  String sTestCaseName;
	private int iTestCaseRow;
	
	@Test(priority=1)
	public void loginUrl() throws Exception{
		PropertyConfigurator.configure("./log/log4j.Properties");
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "logininvalid");
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
	@Test(priority=2,dependsOnMethods={"loginUrl"})
	public void loginWebsiteInvalidPassword() throws Exception{
		//password is invalid
		Login.loginWebsite(iTestCaseRow);
		String loginError=FindElementHelper.getElement("div.form-input-validation.is-error").getText();
		if(loginError.contains("that's not the right password")){
			Log.info("Verified passed.");
			ExcelUtils.setCellData("pass", iTestCaseRow, Constant.Col_Result);
		}else{
			Log.info("Verified does not pass.");
			BaseClass.bResult=false;
			GenHelper.takeScreenShot(sTestCaseName);
			Log.info(sTestCaseName+" ScreenShot is taken.");
			ExcelUtils.setCellData("fail", iTestCaseRow, Constant.Col_Result);
			Assert.fail();
		}
		//Assert.assertTrue(loginError.contains("that's not the right password"));
		//Assert.assertTrue(loginError.contains("password"));
		//Assert.assertTrue(loginError.contains("incorrect"));
		//ERROR: The password you entered for the email or username mjtest2018 is incorrect
		System.out.println(loginError);
	}

	@Test(priority=3,dependsOnMethods={"loginWebsiteInvalidPassword"})
	public void closeDriver() {
		Login.logout();
	}
	@Test
	public void tesTwo(){
		System.out.println("==================TestNG test case again==================");
	}
}
