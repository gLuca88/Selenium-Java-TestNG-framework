package gianluca.com.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import gianluca.com.configurazionereport.LoggerUtils;

public class AdsHandler {

	private final WebDriver driver;

	public AdsHandler(WebDriver driver) {
		this.driver = driver;
	}

	public void hideAdsIframeIfPresent() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;

			// iframe ads
			List<WebElement> iframes = driver.findElements(By.xpath("//iframe[starts-with(@id,'aswift_')]"));

			// host div ads
			List<WebElement> hosts = driver
					.findElements(By.xpath("//div[starts-with(@id,'aswift_') and contains(@id,'_host')]"));

			if (iframes.isEmpty() && hosts.isEmpty()) {
				LoggerUtils.info("Nessun iframe pubblicitario presente.");
				return;
			}

			for (WebElement iframe : iframes) {
				js.executeScript("arguments[0].style.display='none';", iframe);
			}

			for (WebElement host : hosts) {
				js.executeScript("arguments[0].style.display='none';", host);
			}

			LoggerUtils.warn("Iframe e host pubblicitari rimossi.");

		} catch (Exception e) {
			LoggerUtils.warn("Errore gestione ads: " + e.getMessage());
		}
	}
}
