package playwrightTest.PlayWright;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

/*
 * Recording network responses;
 * HAR replay matches URL and HTTP method strictly. For POST requests, 
 * it also matches POST pay-loads strictly. If multiple recordings match a request, 
 * the one with the most matching headers is picked. 
 * An entry resulting in a redirect will be followed automatically
 */


public class networkReplayHarFile {

	@Test
	public void networkReplayHarFileTest() throws Exception {

		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium()
					.launch(new BrowserType.LaunchOptions().setHeadless(false)/* .setChannel("msedge") */);

			String harFiles = System.getProperty("user.dir") + "\\harFiles\\harTracks.har";
			Path path = Paths.get(harFiles);
			BrowserContext context = browser.newContext(
					new Browser.NewContextOptions().setRecordHarPath(path).setRecordHarUrlFilter("**/api/**"));

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
			context.close();
			browser.close();
			playwright.close();
		} catch (Exception e) {
			throw e;
		}
	}
}
