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

	/*
	 * We need to provide a copy of intitialization of all Playwright,
	 * BrowserContext, Browser, & Pages So, that each test can be Independent from
	 * each others
	 * 
	 * ThreadLocal --> Provies ThreadLocal variables, which differe from each others
	 * i.e. a initialized copy Access by Getter & Setter method(get() & set()) It's
	 * Instances are generally PRIVATE, STATIC in classes
	 */

	// for playwright
	private static ThreadLocal<Playwright> playwirghtLocal = new ThreadLocal<>();
	// for browser
	private static ThreadLocal<Browser> browserLocal = new ThreadLocal<>();
	// for browser context
	private static ThreadLocal<BrowserContext> browsercontextLocal = new ThreadLocal<>();
	// for pages
	private static ThreadLocal<Page> pageLocal = new ThreadLocal<>();

	// Getter method to Get ThreadLocal
	public static Playwright getPlaywright() {
		return playwirghtLocal.get();
	}

	public static Browser getBrowser() {
		return browserLocal.get();
	}

	public static BrowserContext getBrowserContext() {
		return browsercontextLocal.get();
	}

	public static Page getPage() {
		return pageLocal.get();
	}

	public Page initBrowser() {
		configure = new configFiles();
		String Browsername = configure.browserName;
		System.out.println(Browsername + " is opening!");

		// playwright = Playwright.create();
		playwirghtLocal.set(Playwright.create());

		switch (Browsername.toLowerCase()) {
		case "chrome":
			//browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));
			browserLocal.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome")));
			break;
		case "firefox":
			//browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("firefox"));
			browserLocal.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("firefox")));
			break;
		case "edge":
			//browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("edge"));
			browserLocal.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("edge")));
			break;
		default:
			System.out.println("Please enter valid browser name!");
			break;
		}
		//browserContext = browser.newContext();
		browsercontextLocal.set( getBrowser().newContext());
		
		//page = browserContext.newPage();
		pageLocal.set(getBrowserContext().newPage());
		getPage().navigate(configure.url);

		return getPage();
	}

	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		getBrowser().close();
		getPlaywright().close();
	}
}
