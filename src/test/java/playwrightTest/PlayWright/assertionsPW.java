package playwrightTest.PlayWright;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.ElementState;

public class assertionsPW {

	@Test
	public void assertionsPWTest() throws Exception {
		try(Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			System.out.println(browser.version());
			BrowserContext browsercontext = browser.newContext();
			browsercontext.clearCookies();
			
			Page pages = browsercontext.newPage();
			pages.navigate("https://www.orangehrm.com/");
			
			ElementHandle handleAcceptCookies = pages.querySelector("a[title='Accept Cookies']");
			handleAcceptCookies.waitForElementState(ElementState.ENABLED);
			Locator acceptCookies = pages.locator("//a[@title='Accept Cookies']");
			try {
				/*--------this assertThat is importing from playwright static class--------*/
				assertThat(acceptCookies).isEnabled();
			} catch (Exception e) {
				System.out.println(e.getStackTrace());
				throw e;
			}
			acceptCookies.click();
			
			Thread.sleep(5000);
			browser.close();
			playwright.close();
		} catch (Exception e) {
			throw e;
		}
	}
}
