package automationLabsMain.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class baseClass {

	configFiles configure;
	public static Playwright playwright;
	public static Browser browser;
	public static BrowserContext browserContext;
	// default first page
	public static Page page;

	public Page initBrowser() {
		configure = new configFiles();
		String Browsername = configure.browserName;
		System.out.println(Browsername + " is opening!");

		playwright = Playwright.create();
		switch (Browsername.toLowerCase()) {
		case "chrome":
			browser = playwright.chromium()
					.launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));
			break;
		case "firefox":
			browser = playwright.firefox()
					.launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("firefox"));
			break;
		case "edge":
			browser = playwright.chromium()
					.launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("edge"));
			break;
		default:
			System.out.println("Please enter valid browser name!");
			break;
		}
		browserContext = browser.newContext();

		page = browserContext.newPage();

		page.navigate(configure.url);

		return page;
	}
	
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		browser.close();
		playwright.close();
	}
}
