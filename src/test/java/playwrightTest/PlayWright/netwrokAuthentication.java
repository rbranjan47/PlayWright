package playwrightTest.PlayWright;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.BrowserType.LaunchOptions;

public class netwrokAuthentication {

	// Authentication -----> act of proving an assertion
	
	/* 4 types of Authentication: 
	 * Basic Authentication 
	 *     -->Simple: send User ID & Password in Base64 encoded form.
	 *                [given().auth().basic("username","password").when()..]
	 *                
	 *     -->Preemptive: checks whether server requires auth or not. 
	 *                [given().auth().preemptive().basic("username","password").when()..]
	 * 
	 * Digest Authentication 
	 *     --> Just like basic authentication, but It sends this hash key to the server to make secure.
	 *                [given().auth().digest("username","password").when()...]
	 *
	 * Form Authentication
	 *     --> Authentication in a form
	 *         [given().auth().form("adminuser","adminpassword", new FormAuthConfig("/authentication","username","password")).when()..]
	 * 
	 * O-auth1 & O-auth2 Authentication
	 *     --> O-auth1: 
	 *                [given().accept(ContentType.JSON).auth().oauth(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET..)]
	 *                
	 *    --> O-auth2:
	 *                [given().auth().oauth2(ACCESS_TOKEN).when()..] 
	 */

	//Playwright handles Basic Authentication, where we pass UserName & Password while entering into application
	
	@Test
	public void netwrokAuthenticationTest() {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new LaunchOptions().setHeadless(false));
			
			BrowserContext browsercontext = browser.newContext(new Browser.NewContextOptions().setHttpCredentials("username","passwords"));
			
			Page pages = browsercontext.newPage();
			
			pages.navigate("https://example.com");
		}
		catch(Exception e) {
			
		}
	}
}
