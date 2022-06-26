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
			Locator enterEmail = pages.locator("_react=p[placeholder='email'] >> input");
			enterEmail.first().fill("abcxyz@gmail.com");

			Locator selectLanguage = pages.locator("_react=UISelect[data-uia='language-picker']");
			selectLanguage.click();

			// could be handle Once done with drop-down
			pages.locator("select#lang-switcher-select >> option[data-language='hi']").click();

			Thread.sleep(5000);
			browser.close();
			playwright.close();
		} catch (Exception e) {
			throw e;
		}
	}
}
