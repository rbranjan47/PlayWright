package playwrightTest.PlayWright;

import java.nio.file.Paths;

import org.testng.annotations.Test;


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.ScreenshotOptions;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

public class screenShots {

	@Test
	public void handleScreenShots() throws Exception {
		try(Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium()
					.launch(new BrowserType.LaunchOptions().setHeadless(false)/*.setChannel("msedge")*/);
			
			BrowserContext context = browser.newContext(new Browser.NewContextOptions());
			
			// Start tracing before creating / navigating a page.
			context.tracing().start(new Tracing.StartOptions()
			  .setScreenshots(true)
			  .setSnapshots(true));
	
			
			Page page = context.newPage();
			page.navigate("https://amazon.in/");
			
			String fullPageScreenShotPath = System.getProperty("user.dir")+"\\screenShotsVideos\\fullPage.jpg";
			
			/*----------full page screenshot----------*/
			page.screenshot(new ScreenshotOptions().setPath(Paths.get(fullPageScreenShotPath)).setFullPage(true));
			
			/*----------Elements screenshot----------*/
			Page newPage = context.newPage();
			String elementScreenShotPath = System.getProperty("user.dir")+"\\screenShotsVideos\\element.jpg";
			
			newPage.navigate("https://a-star.app/");
			Locator elementScreenShot = newPage.locator("xpath = //section[@class='detail-specific-area']");
			elementScreenShot.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get(elementScreenShotPath)));
			Thread.sleep(5000);
			browser.close();
			playwright.close();
		} catch (Exception e) {
			throw e;
		}
	}
}
