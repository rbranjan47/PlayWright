package playwrightTest.PlayWright;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class pageRouteFromHAR {

	@Test
	public void pageRouteFromHarTest() throws Exception {

		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			String harFiles = System.getProperty("user.dir") + "\\harFiles\\harTracks.har";
			Path path = Paths.get(harFiles);

			BrowserContext context = browser.newContext();
			context.routeFromHAR(path);
			Page page = context.newPage();
			page.routeFromHAR(path);

			Thread.sleep(5000);
			context.close();
			browser.close();
			playwright.close();
		} catch (Exception e) {
			throw e;
		}

	}
}
