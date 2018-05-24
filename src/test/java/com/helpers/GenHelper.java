package com.helpers;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
//import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;

import com.log4j.Log;
import com.pageobject.BaseClass;
import com.utility.Constant;

public class GenHelper extends BaseClass {
	private static Alert alert = null;
	private static String aText = null;

	public static void alertAccept() {
		if (GenHelper.isAlertPresent()) {
			try {
				alert = driver.switchTo().alert();
				aText = alert.getText();
				alert.accept();
				driver.switchTo().parentFrame();
				Log.info("There is alert present,and its been accepted");
				Log.info("Alert Text is " + aText);
			} catch (Exception e) {
				e.printStackTrace();
				Log.error("Alert(s) is/are not able to close");

			}
		}
	}

	public static void alertDismiss() {
		if (GenHelper.isAlertPresent()) {
			try {
				alert = driver.switchTo().alert();
				aText = alert.getText();
				alert.dismiss();
				driver.switchTo().parentFrame();
				Log.info("There is alert present,and its been dismissed");
				Log.info("Alert Text is :");
				Log.info(aText);
			} catch (Exception e) {
				e.printStackTrace();
				Log.error("Alert(s) is/are not able to close");

			}
		}
	}

	public static boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void dialogSubmit(String dialogLocator, String buttonLocator) {
		if (GenHelper.isDialogPresent(dialogLocator)) {
			try {
				driver.switchTo().frame(driver.findElement(By.cssSelector(dialogLocator)));
				driver.findElement(By.cssSelector(buttonLocator)).click();
				driver.switchTo().parentFrame();
			} catch (Exception e) {
				e.printStackTrace();
				Log.error("Dialog(s) is/are not able to close");
			}
		}
	}

	public static void dialogCancle(String dialogLocator, String buttonLocator) {
		try {
			if (GenHelper.isDialogPresent(dialogLocator)) {
				driver.switchTo().frame(driver.findElement(By.cssSelector(dialogLocator)));
				driver.findElement(By.cssSelector(buttonLocator)).click();
				driver.switchTo().parentFrame();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("Dialog(s) is/are not able to close");
		}

	}

	public static boolean isDialogPresent(String dialogLocator) {
		try {
			driver.switchTo().frame(driver.findElement(By.cssSelector(dialogLocator)));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean isWindowPresent() {
		List<String> win = new ArrayList<String>(driver.getWindowHandles());
		if (win.size() > 1) {
			Log.info("There is/are windows present,window handle(s) is/are " + win.size());
			return true;
		} else
			return false;

	}

	public static void closeWinSwitchToParentWindow() {
		List<String> win = new ArrayList<String>(driver.getWindowHandles());
		if (isWindowPresent()) {
			try {
				for (int i = 1; i <= win.size(); i++) {
					driver.switchTo().window(win.get(i));
					driver.close();
				}
				driver.switchTo().window(win.get(0));
			} catch (Exception e) {
				e.printStackTrace();
				Log.error("Window(s) is/are not able to close");
			}
		}
	}

	public static void closeWinSwitchToParentWindowTwo() {
		String mainHandles = driver.getWindowHandle();
		String subHandles = null;
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();

		while (iterator.hasNext()) {
			subHandles = iterator.next();
			driver.switchTo().window(subHandles).close();

		}
		driver.switchTo().window(mainHandles);
	}

	public static String getMethodName() {
		class Local {
		}
		;
		return Local.class.getEnclosingMethod().getName();
	}

	public static String getPath() {
		String Path = "";
		Path = GenHelper.class.getClassLoader().getResource("com/resources/").getPath();
		Path = Path.replaceAll("target", "src");
		Path = Path.replaceAll("test-classes", "test/java");
		System.out.println(Path);
		return Path;
	}

	public static void takeScreenShot(String sTestCaseName) {
		if (BaseClass.bResult == false) {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(srcFile,
						new File(Constant.Path_ScreenShot + sTestCaseName + "-" + GenHelper.timestamp() + ".jpg"));
			} catch (IOException e) {
				e.printStackTrace();
				Log.info("Its not able to take screen shot.");
			}

		}
	}
	// create a unique name of screen shot

	public static String timestamp() {
		String t = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
		// File f=new
		// File(String.format("%s.%s",t,RandomStringUtils.randomAlphanumeric(8)));
		System.out.println("Time is :" + t);
		return t;
	}

	public static void takeMultipleScreeShot(String sTestCaseName) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		exe.executeScript("window.scrollTo(0,0)");
		Boolean a = (Boolean) exe
				.executeScript("return document.documentElement.scrollHeight>document.documentElement.clientHeight");
		Long clientH = (Long) exe.executeScript("return document.documentElement.clientHeight");
		Long scrollH = (Long) exe.executeScript("return document.documentElement.scrollHeight");
		int index = 1;
		if (a.booleanValue()) {
			while (scrollH.intValue() > 0) {
				GenHelper.takeScreenShot(sTestCaseName + "-" + index);
				GenHelper.executeScript("window.scrollTo(0," + clientH * index + ")");
				scrollH = scrollH - clientH;
				System.out.println("Screen Shot Taken : " + index);
				index++;
			}
		} else
			GenHelper.takeScreenShot(sTestCaseName);
	}

	public static Object executeScript(String script) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		return (exe.executeScript(script));

	}
}
