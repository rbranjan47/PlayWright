package playwrightTest.PlayWright;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.FilePayload;

public class uploadFiles {
	@Test
	public void uploadFilesTest() throws Exception {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext browserContext = browser.newContext();
			Page pages = browserContext.newPage();

			pages.navigate("https://davidwalsh.name/demo/multiple-file-upload.php");

			// handling new single upload file
			String upload_path = System.getProperty("user.dir") + "\\uploadFile\\image.jpg";
			pages.setInputFiles("input#filesToUpload", Paths.get(upload_path));

			Locator uploadedFile = pages.locator("ul#fileList >> li");
			for (int i = 0; i < uploadedFile.count(); i++) {
				System.out.println(uploadedFile.nth(i).innerText());
			}

			// multiple file handle
			// opening same on another page
			Page newPage = browserContext.newPage();
			newPage.navigate("https://davidwalsh.name/demo/multiple-file-upload.php");

			// handling new multiple upload files
			String upload_pathFirst = System.getProperty("user.dir") + "\\uploadFile\\image.jpg";
			String upload_pathSecond = System.getProperty("user.dir") + "\\uploadFile\\peacock.jpg";
			newPage.setInputFiles("input#filesToUpload", new Path[] {Paths.get(upload_pathFirst), Paths.get(upload_pathSecond)});

			Locator uploadedFilePath = newPage.locator("ul#fileList >> li");
			for (int i = 0; i < uploadedFilePath.count(); i++) {
				System.out.println(uploadedFilePath.nth(i).innerText());
			}

			//upload from buffer
			Page newBufferPage = browserContext.newPage();
			newBufferPage.navigate("https://davidwalsh.name/demo/multiple-file-upload.php");
			
			//Store screenshot in buffer
			byte[] bufferFiles = newBufferPage.screenshot();
			
			/*
			//buffered bytes to array
			String screenshotBufferedText = bufferFiles.toString();
			
			//writing string file into a text files
			String textFile = System.getProperty("user.dir")+"\\uploadFile\\bufferedFile.txt";
			Path filePaths = Paths.get(textFile);
			
			try {
				 Files.writeString(filePaths, screenshotBufferedText, StandardCharsets.UTF_8);
			} catch (Exception e) {
				throw e;
			}
			*/
			
			//buffered image to image
			InputStream inputFiles = new ByteArrayInputStream(Base64.getDecoder().decode(bufferFiles)) ;
			BufferedImage screenShot = ImageIO.read(inputFiles);
			inputFiles.close();
			
			//storing into file
			File outputfile = new File(System.getProperty("user.dir") + "\\uploadFile\\bufferedImage.png");
			ImageIO.write(screenShot, "png", outputfile);
			
			//uploading buffered data
			newBufferPage.setInputFiles("input#filesToUpload", Paths.get(System.getProperty("user.dir") + "\\uploadFile\\bufferedImage.png"));

			Locator uploadedBufferedFilePath = newPage.locator("ul#fileList >> li");
			for (int i = 0; i < uploadedBufferedFilePath.count(); i++) {
				System.out.println(uploadedBufferedFilePath.nth(i).innerText());
			}
			
			Thread.sleep(5000);
			browser.close();
			playwright.close();
		} catch (Exception e) {
			throw e;
		}
	}
}