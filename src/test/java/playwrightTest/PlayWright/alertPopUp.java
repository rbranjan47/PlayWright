package playwrightTest.PlayWright;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class alertPopUp {

	@Test
	public void alertPopupTest() throws Exception {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext browserContext = browser.newContext();
			Page pages = browserContext.newPage();

			pages.navigate("https://the-internet.herokuapp.com/javascript_alerts");

			// handling java-script pop-up, As playwright handles this pop-up automatically
			pages.locator("button[onclick='jsAlert()']").click();

			// handling JS confirm pop-up
			//pages.onDialog(dialog -> dialog.accept());    As it effect down alert send messages actions
			pages.click("button[onclick='jsConfirm()']");

			/*
			 * //To not accept JS Confirm pop-up, just dismiss Thread.sleep(2000);
			 * pages.reload(); pages.onDialog(dialog -> dialog.dismiss());
			 * pages.click("button[onclick='jsConfirm()']");
			 */

			Thread.sleep(3000);
			// handling prompt pop-up
			pages.onDialog(dialog2 -> {
				String dialogText = dialog2.message();
				System.out.println(dialogText);
				dialog2.accept("this is an alert popup");
			});
			pages.click("button[onclick='jsPrompt()']");

			Thread.sleep(5000);
			browser.close();
			playwright.close();
		} catch (Exception e) {
			throw e;
		}
	}
}
