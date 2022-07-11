package automationLabsTest.playwright;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import automationLabsMain.playwright.baseClass;
import page.playwright.addUsersPages;
import page.playwright.loginPage;


public class addUsersTest extends baseClass {
	
	protected loginPage loginpage;
	protected addUsersPages addusers;

	@BeforeTest
	public void setup() {
		loginpage = new loginPage();
		addusers = new addUsersPages(page);
		initBrowser();
	}

	@Test
	public void homePageTest() {
		try {
			loginpage.login();
			String actual_title = getPage().title();
			String expected_title = "Board Room - ResMan";
			Assert.assertEquals(actual_title, expected_title);
		} catch (Exception e) {
			throw e;
		}

		try {
			addusers.clickAdmin().first().hover();
			addusers.clickProperties().first().click();
			addusers.clickUsers().click();
		} catch (Exception e) {
			throw e;
		}

	}

	@AfterTest
	public void closeup() throws InterruptedException {
		loginpage.logout();
		tearDown();
	}
}
