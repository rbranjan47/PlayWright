package automationLabsTest.playwright;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import automationLabsMain.playwright.baseClass;
import page.playwright.addUsersPages;
import page.playwright.loginPage;

public class addUsersTest {
	Page page;
	protected baseClass baseclass;
	protected loginPage loginpage;
	protected addUsersPages addusers;

	@BeforeTest
	public void setup() {
		baseclass = new baseClass();
		loginpage = new loginPage(page);
		addusers = new addUsersPages(page);
		page = baseclass.initBrowser();
	}

	@Test
	public void homePageTest() {
		try {
			loginpage.login();
			String actual_title = page.title();
			String expected_title = "Board Room - ResMan";
			Assert.assertEquals(actual_title, expected_title);
		} catch (Exception e) {
			throw e;
		}

		try {
			addusers.clickAdmin().first().hover();
			addusers.clickProperties().click();
			addusers.clickUsers().click();
		} catch (Exception e) {
			throw e;
		}

	}

	@AfterTest
	public void closeup() throws InterruptedException {
		loginpage.logout();
		baseclass.tearDown();
	}
}
