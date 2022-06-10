package playwrightTest.PlayWright;

import java.nio.file.Path;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class downloadFiles {
	@Test
	public void alertPopupTest() throws Exception {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext browserContext = browser.newContext();
			Page pages = browserContext.newPage();
			
			pages.navigate("https://chromedriver.chromium.org/downloads");
			Locator downloadChromeDriver = pages.locator("a:has-text('ChromeDriver 103.0.5060.24')");
			
			//handling new download pages
			Page downloadPage = browserContext.waitForPage(()->{
				downloadChromeDriver.last().click();
			});
			downloadPage.waitForLoadState();
			
			//Download class of playwright
			Download download = downloadPage.waitForDownload(()->{
				downloadPage.click("a:text('chromedriver_win32.zip')");
			});
			//paths
			Path paths = download.path();
			System.out.println(paths);
			//URL
			System.out.println(download.url());
			//Pages Title
			System.out.println(download.page().title());
			
			Thread.sleep(5000);
			browser.close();
			playwright.close();
		} catch (Exception e) {
			throw e;
		}
	}
}
