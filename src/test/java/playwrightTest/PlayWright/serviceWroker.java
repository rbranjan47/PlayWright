package playwrightTest.PlayWright;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.ServiceWorkerPolicy;

public class serviceWroker {
	@Test
	public void serviceWorkerTest() throws Exception {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

			/*
			 * BLOCKED SERVICE WORKER //----> Proxy servers that sit between web
			 * applications, the browser, and the network (when available). They are
			 * intended, among other things, to enable the creation of effective offline
			 * experiences, intercept network requests and take appropriate action based on
			 * whether the network is available, and update assets residing on the server.
			 * They will also allow access to push notifications and background sync APIs.
			 */
			BrowserContext browserContext = browser
					.newContext(new Browser.NewContextOptions().setServiceWorkers(ServiceWorkerPolicy.BLOCK));
			Page pages = browserContext.newPage();

			// Now Perform the actions
			pages.navigate("url");
			
			//close-up
			Thread.sleep(5000);
			browserContext.close();
			browser.close();
		} catch (Exception e) {
			throw e;
		}
	}
}
