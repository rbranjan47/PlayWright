package playwrightTest.PlayWright;

import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

public class selectClass {

	@Test
	public void testSelectOptions() {
		String tracePath = System.getProperty("user.dir") + "\\Traces\\traces.zip";
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			String browserVersion = browser.version();
			BrowserContext browserContext = browser.newContext();

			browserContext.tracing()
					.start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));

			Page pages = browserContext.newPage();
			System.out.println(browserVersion);
			pages.navigate("https://www.facebook.com/");
			pages.getByTestId("open-registration-form-button").click();

			pages.locator("select#day").selectOption("31");
			pages.locator("select#month").selectOption("Oct");
			pages.locator("select#year").selectOption("1997");
			pages.waitForTimeout(3000);
			browserContext.tracing().stop(new Tracing.StopOptions().setPath(Paths.get(tracePath)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
