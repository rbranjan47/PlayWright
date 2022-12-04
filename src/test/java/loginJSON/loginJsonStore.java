package loginJSON;

import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;

import automationLabsMain.playwright.baseClass;
import page.playwright.loginPage;

public class loginJsonStore extends baseClass {

	protected loginPage loginpage;
	@Test
	public void loginJson() {
		try{
			//launching browser
			initBrowser();
			//adding file path
			String jsonFile = System.getProperty("user.dir")+"jsonFolder\\loginJSON.json";
			//overriding context file in child class 
			browsercontextLocal.set( getBrowser().newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get(jsonFile))));
			
			//page = browserContext.newPage();
			pageLocal.set(getBrowserContext().newPage());
			//login into the application
			loginpage.login();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
