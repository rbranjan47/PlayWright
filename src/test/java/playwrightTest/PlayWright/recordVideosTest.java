package playwrightTest.PlayWright;

import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

public class recordVideosTest {
	@Test
	public void recordVideoTest() throws Exception{
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium()
					.launch(new BrowserType.LaunchOptions().setHeadless(false)/* .setChannel("msedge") */);

			String recorderPath = System.getProperty("user.dir")+"\\screenShotsVideos\\";
			BrowserContext context = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get(recorderPath)));

			// Start tracing before creating / navigating a page.
			context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));

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

			page.click("xpath=//li[@menuname='Admin']");

			page.locator("a:has-text(\"Properties\")").click();

			page.locator("text=Aknsha Test Property").click();

			page.locator("#UsersLink").click();

			page.locator("button[role=\"button\"]:has-text(\"Add Access\")").click();

			page.locator("text=ZERBIN, Delores >> input[name=\"PersonIDs\"]").check();

			page.locator("button[role=\"button\"]:has-text(\"OK\")").click();

			page.locator("text=ZERBIN, Delores msimms Manager 6/23/2021 No >> td").nth(4).click();

			page.locator("button[role=\"button\"]:has-text(\"Remove Access\")").click();

			page.locator("button[role=\"button\"]:has-text(\"Yes\")").click();

			page.waitForNavigation(() -> {
				page.locator("text=Sign Out").click();
			});

			Thread.sleep(5000);
			browser.close();
			playwright.close();
		} catch (Exception e) {
			throw e;
		}
	}
}
