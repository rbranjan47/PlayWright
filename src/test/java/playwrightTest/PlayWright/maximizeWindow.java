package playwrightTest.PlayWright;

import java.util.List;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class maximizeWindow {

	@Test
	public void maximizeWindowSize() throws Exception {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext browserContext = browser
					.newContext(new Browser.NewContextOptions().setViewportSize(1536, 714));
			Page page = browserContext.newPage();

			page.navigate("https://qa.myresman.com");
			page.type("input[name='Username']", "adminrabi");
			page.type("input[name='Password']", "tester1");
			page.click("button[type='submit']", new Page.ClickOptions().setTimeout(50000));

			Locator closeAdvisorLocator = page.locator("#CloseAdvisor");
			if (closeAdvisorLocator.isEnabled()) {
				closeAdvisorLocator.waitFor();
				closeAdvisorLocator.click();
			}
			
			//getting all inner texts of links
			List<String> innerElements = page.locator("a:visible").allInnerTexts();
			for(String innerElementsText : innerElements) {
				System.out.println(innerElementsText);
				
				
			}

			Thread.sleep(5000);
			browser.close();
			playwright.close();
		} catch (Exception e) {
			throw e;
		}
	}
}
