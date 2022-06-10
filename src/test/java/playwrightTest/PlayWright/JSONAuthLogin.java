package playwrightTest.PlayWright;

import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class JSONAuthLogin {
	
	@Test
	public void useStoredJson() {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium()
					.launch(new BrowserType.LaunchOptions().setHeadless(false)/* .setChannel("msedge") */);

			String filePath = System.getProperty("user.dir")+"\\JsonFile\\login.json";
			BrowserContext context = browser.newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get(filePath)));

			Page page = context.newPage();
			page.navigate("https://qa.myresman.com");
			

			Locator closeAdvisorLocator = page.locator("#CloseAdvisor");
			if (closeAdvisorLocator.isEnabled()) {
				closeAdvisorLocator.waitFor();
				closeAdvisorLocator.click();
			}

			page.click("xpath=//li[@menuname='Admin']");

			page.locator("a:has-text(\"Properties\")").click();

			page.locator("text=Aknsha Test Property").click();

			page.locator("#UsersLink").click();
		} catch (Exception e) {
			throw e;
		}
	}
}
