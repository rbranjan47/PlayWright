package playwrightTest.PlayWright;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class StoredJSONLOGIN {

	@Test
	public void storeJsonFile() throws Exception {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium()
					.launch(new BrowserType.LaunchOptions().setHeadless(false)/* .setChannel("msedge") */);

			BrowserContext context = browser.newContext(new Browser.NewContextOptions());

			Page page = context.newPage();

			page.navigate("https://qa.myresman.com");
			System.out.println(page.title());
			page.type("input[name='Username']", "avadmin");
			page.type("input[name='Password']", "tester");
			page.click("button[type='submit']", new Page.ClickOptions().setTimeout(50000));
			
			//storing JSON
			String filePath = System.getProperty("user.dir")+"\\JsonFile\\login.json";
			Path json_file = Paths.get(filePath);
			context.storageState(new BrowserContext.StorageStateOptions().setPath(json_file));
			
			Thread.sleep(5000);
			browser.close();
			playwright.close();
			
		} catch (Exception e) {
			throw e;
		}
	}
}
