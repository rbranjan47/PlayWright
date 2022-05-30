package playwrightTest.PlayWright;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class iFrameHandles {

	@Test
	public void iframeHandles() throws Exception {
		try (Playwright playwright = Playwright.create()){
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext browsercontext = browser.newContext();
			Page page = browsercontext.newPage();
			
			page.navigate("https://www.formsite.com/");
			//Templates
			Locator templates = page.locator("xpath =//a[@title='Templates']");
			System.out.println(templates.count());
			templates.nth(0).click();
			
			page.locator("xpath = //a[contains(text(),'Type')]").click();
			page.locator("text=Registration Forms All Registration Forms  >> button").click();
			
			Locator vehicle_registeration_form = page.locator("text = All Registration Forms Templates").nth(1);
			vehicle_registeration_form.scrollIntoViewIfNeeded();
			vehicle_registeration_form.click();
			
			page.locator("text = Vehicle Registration Form").nth(0).click();
			
			Locator registeration_form = page.locator("#imageTemplateContainer");
			registeration_form.scrollIntoViewIfNeeded();
			registeration_form.click();
			
			page.frameLocator("//iframe[contains(@id,'frame-one748593425')]").locator("#RESULT_TextField-2").fill("2022");
			
			Thread.sleep(5000);
			browser.close();
			playwright.close();
		} catch (Exception e) {
			throw e;
		}
	}
}
