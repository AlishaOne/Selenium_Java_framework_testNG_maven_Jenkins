package com.testcase;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.helpers.FindElementHelper;
import com.helpers.GenHelper;
import com.pageobject.Login;
import com.pageobject.SetupTeardown;

public class TCSerchPost001 extends SetupTeardown {

	@BeforeClass
	public void setUpSerch() throws Exception {
		SetupTeardown.setUp();
	}

	@Test(priority = 1)
	public void serchPost() {
		System.err.println("====================starting serchpost====================");
		Reporter.log("====================starting serchpost====================");
		// click write button
		FindElementHelper.textWebElement("a.masterbar__item.masterbar__item-new").click();
		Login.customImplicitlyWaiPlus();
		Login.getTitle();
		Assert.assertTrue(Login.getTitle().contains("New Post"));
		// postsetting
		boolean bp = FindElementHelper.textWebElement(".button.editor-sidebar__back.is-compact.is-borderless")
				.isDisplayed();
		if (bp) {
			FindElementHelper.textWebElement(".button.editor-sidebar__back.is-compact.is-borderless").click();
		}
		// input post title
		FindElementHelper.textWebElement("textarea.textarea-autosize.editor-title__input").sendKeys("It's fun today!");
		// click visual style
		FindElementHelper.textWebElement(".segmented-control__item.is-selected").click();
		// click html style
		FindElementHelper.textWebElement(".segmented-control__item.is-selected>a").click();
		// writing post
		FindElementHelper.textWebElement(".tinymce.is-visible")
				.sendKeys("Today we have a good day" + "\n" + "We were doing scooter riding!");
		Login.customImplicitlyWaiPlus();
		// publishing post
		FindElementHelper.textWebElement(".button.editor-publish-button.is-primary").click();
		Login.customImplicitlyWaiPlus();
		Login.getTitle();
		Assert.assertTrue(Login.getTitle().contains("Edit Post"));
		Reporter.log("ending addpost");
		System.err.println("====================ending addpost====================");
	}

	@AfterClass(alwaysRun = true)
	public void tearDownAdd() {
		SetupTeardown.tearDown();
	}

}
