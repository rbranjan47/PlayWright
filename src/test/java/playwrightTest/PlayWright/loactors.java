package playwrightTest.PlayWright;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class loactors {

	@Test
	public void Invoices() throws Exception {
		//String tracePath = System.getProperty("user.dir") + "\\Traces\\traces.zip";
		String propertyName = "Alexandria Apartments";
		String selectDate = "All Dates Before";

		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium()
					.launch(new BrowserType.LaunchOptions().setHeadless(false)/* .setChannel("msedge") */);

			BrowserContext context = browser.newContext();
			//TO SET BROWSER SIZE: new Browser.NewContextOptions().setViewportSize(1536, 319)

			// Start tracing before creating / navigating a page.
			//context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));

			Page page = context.newPage();

			page.navigate("https://qa.myresman.com");
			System.out.println(page.title());
			page.type("input[name='Username']", "avadmin");
			page.type("input[name='Password']", "tester");
			page.click("button[type='submit']", new Page.ClickOptions().setTimeout(50000));

			Locator closeAdvisorLocator = page.locator("#CloseAdvisor");
			if (closeAdvisorLocator.isEnabled()) {
				closeAdvisorLocator.waitFor();
				closeAdvisorLocator.click();
			}

			page.locator("xpath=//li[@menuname='Accounting']").hover();

			Locator Invoices = page.locator("a:has-text('Invoices')");
			System.out.println(Invoices.count());
			Invoices.nth(0).click();
			
			page.locator("xpath = //span[@class='ui-combo-button']").click();
			
			Locator propertesGroupID = page.locator("xpath = //li[@class='ui-menu-item']");
			for (int i=0; i< propertesGroupID.count() ; i++) {
				String propertyFetched = propertesGroupID.nth(i).textContent();
				System.out.println(propertyFetched);
				if (propertyFetched.equalsIgnoreCase(propertyName)) {
					propertesGroupID.nth(i).click();
				}
			}
			
			page.locator("xpath = //input[@id='DateRangePicker']").click();
			
			//selection date
			DateFormat dateForm =  new SimpleDateFormat("MM/dd/yyyy");
			Calendar cal = Calendar.getInstance();
			String selectedDate = dateForm.format(cal.getTime());
			
			Locator dates = page.locator("xpath = //ul[@class='ui-widget-content']/li");
			for (int j=0; j< dates.count() ; j++) {
				String datesNeedToSelect = propertesGroupID.nth(j).textContent();
				System.out.println(datesNeedToSelect);
				if (datesNeedToSelect.equalsIgnoreCase(selectDate)) {
					propertesGroupID.nth(j).click();
					
					page.locator("xpath = //input[@id='dp1653501755452']").type(selectedDate);
					page.locator("text = Done").click();
					page.locator("xpath = //input[@id='Go']").click();
				}
			}
			
			// stop tracing the report
			//context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get(tracePath)));

			Thread.sleep(5000);
			//closing window and playwright
			browser.close();
			playwright.close();
		}

		catch (Exception e) {
			throw e;
		}
	}
}
