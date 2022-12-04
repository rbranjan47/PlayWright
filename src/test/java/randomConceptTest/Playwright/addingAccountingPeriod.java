package randomConceptTest.Playwright;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import automationLabsMain.playwright.baseClass;
import page.playwright.addUsersPages;
import page.playwright.loginPage;

public class addingAccountingPeriod extends baseClass{
	protected loginPage loginpage;
	addUsersPages addusers;

	@BeforeTest
	public void setup() {
		loginpage = new loginPage();
		initBrowser();
	}

	@Test
	public void homePageTest() {
		loginpage.login();
		addusers.clickAdmin().first().hover();
		getPage().locator("text = Settings").click();
	}

	@AfterTest
	public void closeup() throws InterruptedException {
		Thread.sleep(5000);
		loginpage.logout();
		tearDown();
	}
}
