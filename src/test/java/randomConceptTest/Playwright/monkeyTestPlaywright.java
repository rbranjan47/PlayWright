package randomConceptTest.Playwright;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class monkeyTestPlaywright {
	@Test
	public void monkeyTestingPlaywright() throws Exception {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext browserContext = browser.newContext();
			Page pages = browserContext.newPage();

			pages.navigate("https://amazon.in");
			Locator footerLinks = pages.locator("div.navFooterVerticalRow li a");
			int footerLinksCount = footerLinks.count();
			System.out.println(footerLinksCount);

			for (int i = 0; i < footerLinksCount; i++) {
				// Generating random numbers
				int randomNumber = (int) Math.floor(Math.random() * footerLinksCount);

				// Scroll into view if needed
				ElementHandle footerLinkHandles = pages.querySelector("div.navFooterVerticalRow li a");
				footerLinkHandles.scrollIntoViewIfNeeded();
				String footerLinksText = footerLinks.nth(randomNumber).innerText();

				try {
					assertThat(footerLinks.nth(randomNumber)).isEnabled();
					footerLinks.nth(randomNumber).click();
					System.out.println(footerLinksText + " is clickable!");
				} catch (Exception e) {
					System.out.println(footerLinksText + " is not clickable!");
				}
				
				pages.goBack();
				Thread.sleep(2000);
				footerLinks = pages.locator("div.navFooterVerticalRow li a");
			}

			Thread.sleep(5000);
			browserContext.close();
			browser.close();
		} catch (Exception e) {
			
			throw e;
		}
	}
}
