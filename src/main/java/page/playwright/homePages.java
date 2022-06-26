package page.playwright;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import automationLabsMain.playwright.baseClass;
import automationLabsMain.playwright.configFiles;

public class homePages extends baseClass {

	configFiles configure = new configFiles();;

	// locators
	private String username = "#Username";
	private String password = "#Password";
	private String sign_out = "#SignOut";
	private String submitButton = "button[type='submit']";
	private String closeAdvisor = "#CloseAdvisor";

	// constructor
	public homePages(Page page) {
		baseClass.page = page;
	}

	// actions
	public void login() {
		// login into applications
		page.locator(username).fill(configure.Username);
		page.locator(password).fill(configure.Password);
		page.locator(submitButton).click();

		Locator closeadvisorPopup = page.locator(closeAdvisor);
		if (closeadvisorPopup.isEnabled()) {
			// closeadvisorPopup.waitFor();
			closeadvisorPopup.click();
		}
	}

	public void logout() {
		// logout from applications
		page.locator(sign_out).click();
	}

}
