package playwrightTest.PlayWright;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class networkResponses {

	//Network Responses ----> Request & Response Messages
	
	
	@Test
	public void networkResponsesTest() throws Exception {
		try(Playwright playwright = Playwright.create()) {
			BrowserType chromeBrwsert= playwright.chromium();
			Browser browser = chromeBrwsert.launch(new BrowserType.LaunchOptions().setHeadless(true));
			Page page = browser.newPage();
			
			page.onRequest(request -> System.out.println(">>"+ request.method() + " "+request.url()));
			page.onResponse(response -> System.out.println("<<"+response.status() +" "+response.url()));
			
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
			Thread.sleep(5000);
			browser.close();
			playwright.close();
		} catch (Exception e) {
			throw e;
		}
	}
}
