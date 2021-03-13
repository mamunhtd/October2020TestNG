package variousConcepts;

import java.util.Set;
import java.util.concurrent.TimeUnit;


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowHandle_Test {
	
	
	WebDriver driver;

	@Test
	public void init() {
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.yahoo.com/");
		// driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		System.out.println(driver.getTitle());
		String windowHandle1 = driver.getWindowHandle();
		System.out.println(windowHandle1);
		
		driver.findElement(By.xpath("//input[@id='ybar-sbq']")).sendKeys("xpath");
		driver.findElement(By.xpath("//input[@id='ybar-search']")).click();
		
		System.out.println(driver.getTitle());
		String windowHandle2 = driver.getWindowHandle();
		System.out.println(windowHandle2);
		
		driver.findElement(By.linkText("XPath Tutorial - W3Schools")).click();
		
		System.out.println(driver.getTitle());
		
		Set<String> windowHandle3 = driver.getWindowHandles();
		System.out.println(windowHandle3);
	
		for(String i : windowHandle3) {
			System.out.println(i);
			driver.switchTo().window(i);
			
		
		}
		
		//get back the driver to window number 1
		
		 System.out.println(driver.getTitle());
		 driver.switchTo().window(windowHandle1);
		 
		 System.out.println(driver.getTitle());
		 
		
		
		driver.close();
		driver.quit();

	}

	
}

