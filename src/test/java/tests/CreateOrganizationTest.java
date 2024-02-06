package tests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import CommonUtils.BaseClass;
import CommonUtils.ExcelFileUtils;
import CommonUtils.JavaUtils;
import CommonUtils.Listenerimplementation;
import CommonUtils.WebDriverUtils;

@Listeners(Listenerimplementation.class)
public class CreateOrganizationTest extends BaseClass {
	@Test
	public void createOrg() throws InterruptedException, EncryptedDocumentException , IOException{
		
		ExcelFileUtils eutils=new ExcelFileUtils();
		WebDriverUtils wutils=new WebDriverUtils();
		JavaUtils jutils=new JavaUtils();
				
				
				//To get data from ExcelFile
				
				String NAME=eutils.getDataFromExcel("Organisation", 0, 1);
				String GROUP=eutils.getDataFromExcel("Organisation", 1, 1);
				String INDUSTRY=eutils.getDataFromExcel("Organisation", 2, 1);
				
				
      
      //step 7:to click on organizations
      c.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
      Assert.assertEquals("Pune", "Deccan");
      
      //step 8 :to click on + icons
      c.findElement(By.cssSelector("img[alt='Create Organization...']")).click();
      
      //step 9:to enter organization name
      c.findElement(By.name("accountname")).sendKeys(NAME+jutils.RandomNumbers());
      
      //step 10: to click on group radio button
      
      c.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
      
      //step 11:select support group in drop-down
     WebElement dropdown = c.findElement(By.name("assigned_group_id"));
     wutils.handleDropdownByVisableText(dropdown, GROUP);
     
     
     //step 12:select industry in drop-down
     WebElement industrydropdown = c.findElement(By.name("industry"));
     wutils.handleDropdownByValue(industrydropdown, INDUSTRY);
     
//	 step 13 : to click on save button
	Thread.sleep(2000);
     c.findElement(By.xpath("(//input[@name='button'])[1]")) .click();
     
//	 step 14:for validation
    String title = c.getTitle();
    System.out.println(title);
    
    String expectedtitle ="Administrator - Organizations - vtiger CRM 5 - Commercial Open Source CRM";

    
//   Using Explicit-wait because IF-ELSE wont support in TestNG
   
    wutils.explicitWaitTitle(c, 10, expectedtitle);
//   To Take ScreenShot of WebPage
   	Thread.sleep(3000);
   	wutils.toTakeScreenshot(c,"Contact");
   
   	Thread.sleep(4000);
	}
}
