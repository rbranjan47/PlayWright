package playwrightTest.PlayWright;

import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.ElementState;

public class dynamicTables {
	@Test
	public void dynamicTableTest() throws Exception {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium()
					.launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("msedge"));

			String filePath = System.getProperty("user.dir") + "\\JsonFile\\login.json";
			BrowserContext context = browser
					.newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get(filePath)));

			Page page = context.newPage();
			page.navigate("https://qa.myresman.com");

			Locator closeAdvisorLocator = page.locator("#CloseAdvisor");
			if (closeAdvisorLocator.isEnabled()) {
				closeAdvisorLocator.waitFor();
				closeAdvisorLocator.click();
			}

			page.click("xpath=//li[@menuname='Admin']");

			page.locator("a:has-text('Properties')").click();

			ElementHandle handle = page.querySelector("div[id='Loading']");
			handle.waitForElementState(ElementState.VISIBLE);
			handle.waitForElementState(ElementState.HIDDEN);

			// handling dynamic tables
			Locator propertiesRows = page.locator("div#PropertyIndexList >> table >> tbody >> tr");
			for (int i = 0; i < propertiesRows.count(); i++) {
				System.out.println(propertiesRows.nth(i).innerText());
			}
			Thread.sleep(5000);
			browser.close();
			playwright.close();
		} catch (Exception e) {
			throw e;
		}
	}
}
