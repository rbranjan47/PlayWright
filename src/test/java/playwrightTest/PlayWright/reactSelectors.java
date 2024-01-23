package playwrightTest.PlayWright;

import org.testng.annotations.Test;

import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Playwright;

public class reactSelectors {

	// To Test -----> Install react plug-in in browser
	// & Get React components by checking web-site is react based or not

	@Test
	public void reactSelectorsTest() throws Exception {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new LaunchOptions().setHeadless(false));
			BrowserContext browsercontext = browser.newContext();
			Page pages = browsercontext.newPage();

			pages.navigate("https://www.netflix.com/in/");
			// react locators
			Locator enterEmail = pages.locator("_react=Anonymous[name='email'] >> input");
			enterEmail.nth(1).fill("abcxyz@gmail.com");

			Locator selectLanguage = pages.locator("_react=UISelect[data-uia='language-picker']");
			selectLanguage.click();

			// could be handle Once done with drop-down
			pages.locator("select#lang-switcher-select").selectOption("/in-hi/");
			pages.waitForTimeout(3000);
			//navigating back to english 
			pages.locator("select#lang-switcher-select").selectOption("/in/");

			pages.waitForTimeout(5000);
			Locator storyHeader = pages.locator("h1[class='our-story-card-title']");
			for (int i = 0; i < storyHeader.count(); i++) {
				System.out.println(storyHeader.nth(i).textContent());
			}

			browser.close();
			playwright.close();
		} catch (Exception e) {
			throw e;
		}
	}
}
