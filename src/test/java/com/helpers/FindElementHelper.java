package com.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.pageobject.BaseClass;

public class FindElementHelper extends BaseClass {

	public FindElementHelper() {
		super();

	}

	public static WebElement textWebElement(String wayToIndetifyElement) {
		WebElement element = null;
		try {
			element = driver.findElement(By.cssSelector(wayToIndetifyElement));
		} catch (Exception e) {
			throw new ElementNotFoundException("Element not found by " + wayToIndetifyElement, wayToIndetifyElement,
					wayToIndetifyElement);
		}
		return element;
	}

	public static WebElement WebElementById(String wayToIndetifyElement) {
		WebElement element = null;
		try {
			element = driver.findElement(By.id(wayToIndetifyElement));
		} catch (Exception e) {
			throw new ElementNotFoundException("Element not found by " + wayToIndetifyElement, wayToIndetifyElement,
					wayToIndetifyElement);
		}
		return element;
	}

	public static WebElement getElement(String locator) {
		boolean flag = false;
		if (locator.contains("/"))
			flag = true;
		if (driver.findElements(By.id(locator)).size() == 1) {
			return driver.findElement(By.id(locator));
		} else if (driver.findElements(By.name(locator)).size() == 1) {
			return driver.findElement(By.name(locator));
		} else if ((!flag) && driver.findElements(By.cssSelector(locator)).size() == 1) {
			return driver.findElement(By.cssSelector(locator));
		} else if (driver.findElements(By.xpath(locator)).size() == 1) {
			return driver.findElement(By.xpath(locator));
		} else
			throw new NoSuchElementException("No Such Element : " + locator);

	}

}
