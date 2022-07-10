package page.playwright;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import automationLabsMain.playwright.baseClass;
import automationLabsMain.playwright.configFiles;

public class loginPage extends baseClass {

	configFiles configure = new configFiles();;

	// locators
	private String username = "#Username";
	private String password = "#Password";
	private String sign_out = "#SignOut";
	private String submitButton = "button[type='submit']";
	private String closeAdvisor = "#CloseAdvisor";
	private String closePendo = "#pendo-close-guide-9389d88c";

	// constructor
	public loginPage(Page page) {
		baseClass.page = page;
	}

	// check pendo visible
	public void isPendoVisible() {
		Locator closePendoPopup = page.locator(closePendo);
		if (closePendoPopup.isVisible()) {
			closePendoPopup.click();
		}
	}

	// check close advisor
	public void closeAdvisor() {
		Locator closeadvisorPopup = page.locator(closeAdvisor);
		if (closeadvisorPopup.isEnabled()) {
			// closeadvisorPopup.waitFor();
			closeadvisorPopup.click();
		}
	}

	// actions
	public boolean login() {
		// login into applications
		page.locator(username).fill(configure.Username);
		page.locator(password).fill(configure.Password);
		page.locator(submitButton).click();

		// Pendo Popup
		isPendoVisible();

		// close advisor
		closeAdvisor();

		if (page.locator(sign_out).isVisible()) {
			System.out.println("user logged in successfuly!");
			return true;
		}
		return false;
	}

	public void logout() {
		// logout from applications
		page.locator(sign_out).click();
	}

}
