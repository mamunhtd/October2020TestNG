package variousConcepts;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login_Test_TestNG {

	WebDriver driver;
	String browser = null;
	String url  = null;
	
	
	@BeforeClass
	public void readConfig() {
		//InputStream  // BufferedReder  // FileReder  // Scanner  : These are the class, concepts of Java can read any file
		
		Properties prop = new Properties();
		
		try {
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			prop.load(input);
			browser = prop.getProperty("browser");
			System.out.println("Used browser: " + browser);
			
			url = prop.getProperty("url");
			System.out.println("Used url: " + url);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

		 
	@BeforeMethod
	public void init() {

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void loginTest() {

		// Element Library
		By USER_NAME_FIELD = By.xpath("//input[@id='username']");
		By PASSWORD_FIELD = By.xpath("//input[@id='password']");
		By SIGNIN_BUTTON = By.xpath("//button[text()='Sign in']");

		Assert.assertEquals(driver.getTitle(), "Login - iBilling", "Page not Found");

		driver.findElement(USER_NAME_FIELD).clear();
		driver.findElement(USER_NAME_FIELD).sendKeys("demo@techfios.com");
		driver.findElement(PASSWORD_FIELD).sendKeys("abc123");
		driver.findElement(SIGNIN_BUTTON).click();

		Assert.assertEquals(driver.getTitle(), "Dashboard- iBilling", "Page not Found");

	}

	@Test(priority = 2)
	public void addCustomerTest() throws InterruptedException {

		By USER_NAME_FIELD = By.xpath("//input[@id='username']");
		By PASSWORD_FIELD = By.xpath("//input[@id='password']");
		By SIGNIN_BUTTON = By.xpath("//button[text()='Sign in']");

		By CUSTOMERS_BTN_ELE = By.xpath("//span[contains(text(),'Customers')]");
		By ADD_CUSTOMER_BUT_ELE = By.linkText("Add Customer");

		By FULLNAME_FLD_ELE = By.xpath("//input[@id='account']");
		By COMPANY_DROPDOWND_ELEMENT = By.xpath("//select[@id='cid']");
		By EMAIL_FLD_ELE = By.xpath("//input[@name='email']");
		By PHONE_FLD_ELE = By.xpath("//input[@id='phone']");
		By ADDRESS_FLD_ELE = By.xpath("//input[@id='address']");
		By CITY_FLD_ELE = By.xpath("//input[@id='city']");
		By STATE_FLD_ELE = By.xpath("//input[@id='state']");
		By ZIP_FLD_ELE = By.xpath("//input[@id='zip']");
		By COUNTRY_DROPDOWN_ELE = By.xpath("//select[@id='country']");
		By CURRENCY_DROPDOWN_ELE = By.xpath("//select[@id='currency']");
		By GROUP_DROPDOWN_ELE = By.xpath("//select[@id='group']");
		By SUBMIT_BUTTON = By.xpath("//button[@id='submit']");

		// Login Data
		String loginId = "demo@techfios.com";
		String password = "abc123";

		// Test Data
		String fullName = "xyz";
		String companyName = "Google";
		String emailAddress = "abc@gmail.com";
		String phoneNumber = "123654";
		String address = "123 ABC HOME";
		String city = "XYZ";
		String state = "DDD";
		String zip = "12345";
		String country = "United States";
		String currency = "USD";
		String group = "June2020";

		// Login
		Assert.assertEquals(driver.getTitle(), "Login - iBilling", "Page not Found");
		driver.findElement(USER_NAME_FIELD).clear();
		driver.findElement(USER_NAME_FIELD).sendKeys(loginId);
		driver.findElement(PASSWORD_FIELD).sendKeys(password);
		driver.findElement(SIGNIN_BUTTON).click();
		Assert.assertEquals(driver.getTitle(), "Dashboard- iBilling", "Page not Found");

		// Add Customer

		waitForElement(driver, 5, CUSTOMERS_BTN_ELE);
		driver.findElement(CUSTOMERS_BTN_ELE).click();
		waitForElement(driver, 5, ADD_CUSTOMER_BUT_ELE);
		driver.findElement(ADD_CUSTOMER_BUT_ELE).click();

		// Generate Random Number
		Random rnd = new Random();
		int randomNumber = rnd.nextInt(900);
		waitForElement(driver, 3, FULLNAME_FLD_ELE);
		driver.findElement(FULLNAME_FLD_ELE).sendKeys(fullName + randomNumber);

		Select sel = new Select(driver.findElement(COMPANY_DROPDOWND_ELEMENT));
		sel.selectByVisibleText(companyName);
		
		driver.findElement(EMAIL_FLD_ELE).sendKeys(randomNumber + emailAddress);
		driver.findElement(PHONE_FLD_ELE).sendKeys(phoneNumber);
		driver.findElement(ADDRESS_FLD_ELE).sendKeys(address);
		driver.findElement(CITY_FLD_ELE).sendKeys(city);
		driver.findElement(STATE_FLD_ELE).sendKeys(state);
		driver.findElement(ZIP_FLD_ELE).sendKeys(zip);
		
		
		Select sel1 = new Select(driver.findElement(COUNTRY_DROPDOWN_ELE));
		sel1.selectByValue(country);
		
		Select sel2 = new Select(driver.findElement(CURRENCY_DROPDOWN_ELE));
		sel2.selectByVisibleText(currency);
		
		Select sel3 = new Select(driver.findElement(GROUP_DROPDOWN_ELE));
		sel3.selectByVisibleText(group);
		
		driver.findElement(SUBMIT_BUTTON).click();
		
		

	}

	// Explicity Wait

	public void waitForElement(WebDriver driver, int timeInSeconds, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	 @AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
