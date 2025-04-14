package test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Set;

public class loginTest {
	
	public static void main(String[] args) throws Exception {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();

		  try {
			
			    //step1 - go to intervue.io website
			    driver.get("https://www.intervue.io/");
			    String mainWindow = driver.getWindowHandle();
			  
			    //step2 - click the Login button
			    WebElement loginButton = driver.findElement(By.linkText("Login"));
			    Actions actions = new Actions(driver);
			    actions.moveToElement(loginButton).pause(Duration.ofMillis(500)).click().perform();
			  
			  	//step3 - switch to the new login window
			    Set<String> allWindows = driver.getWindowHandles();
			     for (String window : allWindows) {
			        if (!window.equals(mainWindow)) {
			            driver.switchTo().window(window);
			            break;
			        }
			    }
			    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			    
			    //step4 - click on login button for companies
			    WebElement companyLoginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.AccessAccount-ColoredButton[href='/login']")));
			    companyLoginBtn.click();
			    
			    //step5 - fill the credentials
			    WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
			    emailInput.sendKeys("neha@intervue.io");

			    WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
			    passwordInput.sendKeys("Ps@neha@123");
			    
			   //step6 - click the login button
			    WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
			    loginBtn.click();
			    
			    //step7 - wait until dashboard is fully loaded
			    wait.until(ExpectedConditions.urlContains("/dashboard"));
			    Thread.sleep(1000);
			    
			    // Step 8: Click the search icon at the top right
			    WebElement searchIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[class*='HeaderSearch__SearchLensIconWrap']")));
			    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchIcon);
			    actions.moveToElement(searchIcon).pause(Duration.ofMillis(500)).click().perform();
			    Thread.sleep(1000);

			    // Step 9: Enter the word "hello" in the search popup
			    WebElement popupSearchInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[class*='SearchBox__StyledInput']")));
			    popupSearchInput.clear();
			    popupSearchInput.sendKeys("hello");
			    Thread.sleep(800);

			    // Step 10: Click the first matching search result
			    WebElement helloSuggestion = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.SearchThrough__PlaceholderText-sc-8f4vh4-0.fEvpzS")));
			    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", helloSuggestion);
			    Thread.sleep(800);

			    // Step 11: Click on the profile dropdown
			    WebElement userDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ant-dropdown-link.ProfileHeader__StyedDropdownHoverLink-sc-1gwp6c1-3.cwhrSp")));
			    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", userDropdown);
			    Thread.sleep(800);

			    // Step 12: Click on the "Logout" option
			    WebElement logoutLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/logout']")));
			    actions.moveToElement(logoutLink).pause(Duration.ofMillis(500)).click().perform();
			    Thread.sleep(2000);


			} catch (Exception e) {
			    e.printStackTrace();
			} finally {
			    driver.quit();
			}
		}

}

