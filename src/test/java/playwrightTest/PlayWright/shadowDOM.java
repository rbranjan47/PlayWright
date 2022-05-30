package playwrightTest.PlayWright;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class shadowDOM {

	@Test
	public void shadowDomTest() throws Exception {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext browserContext = browser.newContext();
			Page page = browserContext.newPage();

			page.navigate("https://books-pwakit.appspot.com/");

			// Move to Parent Shadow Element ----> to child DOM element
			Locator searchParent = page.locator("book-app[apptitle='BOOKS'] #input");
			searchParent.fill("Chetan Bhagat");
			page.keyboard().press("Enter");
			Thread.sleep(5000);
			
			page.goBack();

			// Moving to Child Shadow element ------> To Access DOM
			Locator searchChild = page.locator("book-input-decorator #input");
			searchChild.fill("Ravinder Singh");
			page.keyboard().press("Enter");

			Thread.sleep(5000);
			browser.close();
			playwright.close();
		} catch (Exception e) {
			throw e;
		}
	}
}
