package page.playwright;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import automationLabsMain.playwright.baseClass;

public class addUsersPages extends baseClass{

	//Locators
	private String adminOption = "text = Admin";
	private String propertiesOptions = "text = Properties";
	private String clickUser = "";
	
	//constructors
	public addUsersPages(Page page) {
		baseClass.page = page;
	}
	//Actions
	public Locator clickAdmin() {
		return getPage().locator(adminOption);
	}
	
	public Locator clickProperties() { 
		return getPage().locator(propertiesOptions);
	}
	
	public Locator clickUsers() {
		return getPage().locator(clickUser);
	}
	
}
