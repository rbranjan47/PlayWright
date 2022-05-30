package playwrightTest.PlayWright;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class handleFrameSet {

	@Test
	public void testHandleFrames() {

		try (Playwright playwright = Playwright.create()) {
			// launching browser
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			// browser context
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			page.navigate("https://the-internet.herokuapp.com/");

			page.locator("a:has-text('Frames')").nth(0).click();

			page.locator("a:has-text('Nested Frames')").click();

			page.reload();
			// printing text from each
			for (Frame child_frames : page.mainFrame().childFrames()) {
				System.out.println(child_frames.name());
			}

			/*---------------HANDLING NESTED FRAMES---------------*/
			// Main(Whole page) Frame
			Frame whole_page_frame = page.mainFrame();

			// top frame
			// Frame top_frame = whole_page_frame.childFrames().get(0);
			Frame top_frame = whole_page_frame.childFrames().get(0);

			// button frame
			Frame button_frame = whole_page_frame.childFrames().get(1);

			// fetching child frames
			Frame top_left = top_frame.childFrames().get(0);
			System.out.println(top_left.locator("body").innerText());

			Frame top_middle = top_frame.childFrames().get(1);
			System.out.println(top_middle.locator("body").innerText());

			Frame top_right = top_frame.childFrames().get(2);
			System.out.println(top_right.locator("body").innerText());

			System.out.println(button_frame.locator("body").innerText());

			browser.close();
			playwright.close();
		} catch (Exception e) {
			throw e;
		}

	}
}
