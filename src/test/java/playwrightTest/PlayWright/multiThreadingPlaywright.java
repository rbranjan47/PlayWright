package playwrightTest.PlayWright;

import static java.util.Arrays.asList;
/*
 * Playwright java is not Thread safe, i.e. BrowserContext, Browser, Page all calling on single Thread
 * 
 * Here, three playwright instances are created.
 *  Each on its own thread. 
 *  Each instance launches its own browser process and runs the test against it.
 */

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class multiThreadingPlaywright extends Thread {

	private final String browserName;

	private multiThreadingPlaywright(String browserName) {
		this.browserName = browserName;
	}

	public static void main(String[] args) {
		for (String browserName : asList("chromium", "firefox", "webkit")) {
			// Creating Thread class
			Thread thread = new multiThreadingPlaywright(browserName);
			thread.start();
		}
	}

	@Override
	public void run() {
		try (Playwright playwright = Playwright.create()) {
			BrowserType browserType = getBrowserType(playwright, browserName);
			Browser browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
			Page page = browser.newPage();

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
			
			page.waitForNavigation(() -> {
				page.locator("text=Sign Out").click();
			});
			
			System.out.println(page.title());

		} catch (Exception e) {
			throw e;
		}
	}

	private static BrowserType getBrowserType(Playwright playwright, String browserName) {
		switch (browserName) {
		case "chromium":
			return playwright.chromium();
		case "firefox":
			return playwright.firefox();
		case "webkit":
			return playwright.webkit();
		default:
			throw new IllegalArgumentException();
		}
	}
}
