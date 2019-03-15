package businessLogic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class GoogleMapsWeb {
	
	public void browseAddress(String address){
		System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(DesiredCapabilities.chrome());
		driver.get("https://www.google.es/maps");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@id='searchboxinput']")).sendKeys(address);
		driver.findElement(By.xpath("//*[@id='searchbox-searchbutton']")).click();
	}

}
