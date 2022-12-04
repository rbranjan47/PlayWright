package page.playwright;

import com.microsoft.playwright.Locator;

import automation.libraries.methods;
import automationLabsMain.playwright.configFiles;

public class loginPage extends methods {

	configFiles configure = new configFiles();;

	// locators
	private String username = "#Username";
	private String password = "#Password";
	private String sign_out = "#SignOut";
	private String submitButton = "button[type='submit']";
	private String closeAdvisor = "#CloseAdvisor";
	private String closePendo = "#pendo-close-guide-9389d88c";

	// check pendo visible
	public void isPendoVisible() {
		Locator closePendoPopup = getPage().locator(closePendo);
		if (closePendoPopup.isVisible()) {
			closePendoPopup.click();
		}
	}

	// check close advisor
	public void closeAdvisor() {
		Locator closeadvisorPopup = getPage().locator(closeAdvisor);
		if (closeadvisorPopup.isEnabled()) {
			// closeadvisorPopup.waitFor();
			closeadvisorPopup.click();
		}
	}

	// actions
	public boolean login() {
		// login into applications
		getPage().locator(username).fill(configure.Username);
		getPage().locator(password).fill(configure.Password);
		getPage().locator(submitButton).click();

		// Pendo Popup
		isPendoVisible();

		// close advisor
		closeAdvisor();

		if (getPage().locator(sign_out).isVisible()) {
			System.out.println("user logged in successfuly!");
			return true;
		}
		return false;
	}

	public void logout() {
		// logout from applications
		getPage().locator(sign_out).click();
	}

}
