package playwrightTest.PlayWright;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Base64;

import javax.imageio.ImageIO;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class rough {
	public static void main(String[] args) throws Exception {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext browserContext = browser.newContext();
			Page pages = browserContext.newPage();

			pages.navigate("https://davidwalsh.name/demo/multiple-file-upload.php");
			
			//Store screenshot in buffer
			byte[] bufferFiles = pages.screenshot(); 
			Thread.sleep(4000);
			System.out.println("Screenshot bytes "+ bufferFiles);
			
			BufferedImage image = ImageIO.read(new File(System.getProperty("user.dir") + "\\uploadFile\\image.jpg"));
			

			ByteArrayOutputStream outStreamObj = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", outStreamObj);

			byte [] byteArray = outStreamObj.toByteArray();
			System.out.println("Image bytes "+byteArray);
			
			ByteArrayInputStream inStreambj = new ByteArrayInputStream(bufferFiles);
			BufferedImage newImage = ImageIO.read(inStreambj);
			ImageIO.write(newImage, "jpg", new File(System.getProperty("user.dir") + "\\uploadFile\\Newimage.jpg"));
			
		} catch (Exception e) {
			throw e;
		}
	}
}