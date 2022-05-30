package playwrightTest.PlayWright;

import java.nio.file.Paths;
import java.util.Base64;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

public class locatorsDemo {

	@Test
	public void testRough() {

		String tracePath = System.getProperty("user.dir") + "\\Traces\\traces.zip";

		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium()
					.launch(new BrowserType.LaunchOptions().setHeadless(false)/* .setChannel("msedge") */);

			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			// Start tracing before creating / navigating a page.
			context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));

			page.navigate("https://academy.naveenautomationlabs.com/");

			//storing page screenshot into buffer
			byte[] buffer = page.screenshot();
			System.out.println(Base64.getEncoder().encode(buffer));
			
			Locator loginBtn = page.locator("text = Login");
			loginBtn.nth(0).click();

			// Locator emailField = page.locator("xpath = //input[@id='le']");
			// assertTrue(emailField.isVisible());

			// stop tracing
			context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get(tracePath)));

			// closing browser
			browser.close();
			playwright.close();
		}

		catch (Exception e) {
			throw e;
		}
	}
}
