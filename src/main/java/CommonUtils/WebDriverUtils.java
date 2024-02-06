package CommonUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WebDriverUtils {

//	MAXIMIZE
	public void maximizeWindow(WebDriver driver) 
	{	
		driver.manage().window().maximize();
	}
	
//	FULLSCREEN
	public void fullscreenWindow(WebDriver driver) 
	{	
		driver.manage().window().fullscreen();
	}
	
//	MINIMIZE()
	public void minimizeWindow(WebDriver driver) 
	{
	driver.manage().window().minimize();
	}
	
//	IMPLICITLY WAITs
//	Seconds-
	public void implicitlyWaitInSeconds(WebDriver driver,int sec)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
	}
	
//	Minutes-
	public void implicitlyWaitInMinutes(WebDriver driver,int min)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(min));
	}
//	MilliSeconds-
	public void implicitlyWaitInMilliSec(WebDriver driver,int milsec)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(milsec));
	}
	
//	ExplicitWait
	public void explicitWaitUrl(WebDriver driver,int sec,String url)
	{
		WebDriverWait waits=new WebDriverWait(driver,Duration.ofSeconds(sec));
		waits.until(ExpectedConditions.urlToBe(url));
	}
	public void explicitWaitTitle(WebDriver driver,int sec,String title)
	{
		WebDriverWait waits=new WebDriverWait(driver,Duration.ofSeconds(sec));
		waits.until(ExpectedConditions.titleContains(title));
	}
	
//	PageLoadTimeout
	public void pageloadBySeconds(WebDriver driver, int sec) {
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(sec));
	}
	
	public void pageloadByMinutes(WebDriver driver, int min) {
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(min));
	}
	
	
	
//	Select Class
	public void handleDropdownByVisableText(WebElement target,String text)
	{
		Select s=new Select(target);
		s.selectByVisibleText(text);
	}
	public void handleDropdownByValue(WebElement target,String value)
	{
		Select s=new Select(target);
		s.selectByValue(value);
	}
	public void handleDropdownByIndex(WebElement target,int index)
	{
		Select s=new Select(target);
		s.selectByIndex(index);
	}
	
//	Actions Class
	
//	MouseHover-
	public void mouseHover(WebDriver driver,WebElement target)
	{
		Actions a=new Actions(driver);
		a.moveToElement(target).perform();
	}
	
//	Context-Click-
	public void rightClick(WebDriver driver,WebElement target)
	{
		Actions a=new Actions(driver);
		a.contextClick(target).perform();
	}
	
//	Double-Click
	public void doubleClick(WebDriver driver,WebElement target)
	{
		Actions a=new Actions(driver);
		a.doubleClick(target).perform();
	}
	
//	Click
	public void leftclick(WebDriver driver,WebElement target)
	{
		Actions a=new Actions(driver);
		a.click(target).perform();
	}
	
//	SwitchTo
	public void switching(WebDriver driver,String expectedUrl) {
	Set<String> ids=driver.getWindowHandles();
	System.out.println(ids);
	for(String s:ids){
		String actual=driver.switchTo().window(s).getCurrentUrl();
			if(actual.contains(expectedUrl))
			{
				break;
			}
		}
	}
	
//	To Take Screenshot
	public void toTakeScreenshot(WebDriver driver , String name) throws IOException , InterruptedException {
		LocalDateTime td=LocalDateTime.now();
		String timedate=td.toString().replace(":","-");
		
		TakesScreenshot t=(TakesScreenshot)driver;
		Thread.sleep(3000);
		 File temp= t.getScreenshotAs(OutputType.FILE);
	       File desti=new File("./Screenshots/"+name+" "+timedate+".png");
	       FileUtils.copyFile(temp, desti);
	}
	
//	To Take Screenshot FailedScript
	public String toTakeScreenshotFailed(WebDriver driver , String name) throws IOException , InterruptedException {
		LocalDateTime td=LocalDateTime.now();
		String timedate=td.toString().replace(":","-");
		
		TakesScreenshot t=(TakesScreenshot)driver;
		Thread.sleep(3000);
		 File temp= t.getScreenshotAs(OutputType.FILE);
	       File desti=new File("./Screenshots/"+name+" "+timedate+".png");
	       FileUtils.copyFile(temp, desti);
	       return desti.getAbsolutePath();
	}
	
//	For OK Popups
	public void okPopup(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
//	For Cancel Popups
	public void cancelPopup(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
//	For TextPopups
	public void textPopuop(WebDriver driver) {
		driver.switchTo().alert().getText();
	}
	
//	For ValuePopups
	public void valuePopup(WebDriver driver,String text) {
		driver.switchTo().alert().sendKeys(text);
	}
	
//	FOR IFRAMES
	
//	By Index-
	public void iFramesByIndexValue(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	
//	By Name-
	public void iFramesByName(WebDriver driver,String name) {
		driver.switchTo().frame(name);
	}
	
//	By WebElement-
	public void iFramesByWebElement(WebDriver driver,WebElement element) {
		driver.switchTo().frame(element);
	}
}
