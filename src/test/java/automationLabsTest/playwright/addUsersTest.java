package automationLabsTest.playwright;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import automationLabsMain.playwright.baseClass;
import page.playwright.homePages;

public class addUsersTest extends baseClass {
	Page page;
	homePages homepage = new homePages(page);

	@BeforeTest
	public void setup() {
		page = initBrowser();
	}

	@Test
	public void homePageTest() {
		try {
			homepage.login();
			String actual_title = page.title();
			String expected_title = "Board Room - ResMan";
			Assert.assertEquals(actual_title, expected_title);
		} catch (Exception e) {
			throw e;
		}
	}

	@AfterTest
	public void closeup() throws InterruptedException {
		homepage.logout();
		Thread.sleep(5000);
		browser.close();
		playwright.close();
	}
}
