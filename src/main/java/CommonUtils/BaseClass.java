package CommonUtils;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {

	public WebDriver c;
	public static WebDriver sd;
	PropertyFileUtils putils=new PropertyFileUtils();
	WebDriverUtils wutils=new WebDriverUtils();


	@BeforeSuite
	public void BS() {
		System.out.println("Connect to DataBase");
		
	}
	
	@BeforeClass
	public void BC() throws IOException{
//		To read Data from Property file
		String BROWSER=putils.getDataFromPropertyFile("browser");
		String URL=putils.getDataFromPropertyFile("url");
		
		  //To launch the browser
	      if(BROWSER.equalsIgnoreCase("Chrome")) {
	      	 c=new ChromeDriver();	
	      }
	      else if (BROWSER.equalsIgnoreCase("Edge")) {
	      	c=new EdgeDriver();
				
			}
	      else {
	      	 c=new FirefoxDriver();
	      	System.out.println("defaultBrowser");
	      	
	      }
	      
	    sd=c;
//	      To Maximize the window
	     wutils.maximizeWindow(c);
	     
//	     To apply implicitlyWait
	     wutils.implicitlyWaitInSeconds(c, 10);
	      
	      	
	      //step 5:Load the URL
	      c.get(URL);
	}
	
	@BeforeMethod
	public void BM() throws FileNotFoundException, IOException {
		
		String USERNAME=putils.getDataFromPropertyFile("username");
		String PASSWORD=putils.getDataFromPropertyFile("password");
		
		//step 6:Login to application
	      c.findElement(By.name("user_name")).sendKeys(USERNAME);
	      c.findElement(By.name("user_password")).sendKeys(PASSWORD);
	      c.findElement(By.id("submitButton")).click();	
	}
	
	@AfterMethod
	public void AM() throws InterruptedException {
		 //step 15:mouse over on administrator
	     
	     WebElement signout = c.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	     wutils.mouseHover(c, signout);
	     Thread.sleep(2000);
	     
	     //step 13:to click on sign-out
	     c.findElement(By.xpath("//a[text()='Sign Out']")).click();
	     Thread.sleep(2000);
		
	}
	
	@AfterClass
	public void AC() {
		c.quit();
	}
	
	@AfterSuite
	public void AS() {
	System.out.println("Disconnect to DataBase");
	}
	
}
