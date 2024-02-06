package tests;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import CommonUtils.BaseClass;
import CommonUtils.ExcelFileUtils;
import CommonUtils.Listenerimplementation;
import CommonUtils.WebDriverUtils;
@Listeners(Listenerimplementation.class)
public class CreateContactTest extends BaseClass{
	@Test()
		public void CreateContact()throws IOException, InterruptedException , EncryptedDocumentException {
			
		ExcelFileUtils eutils=new ExcelFileUtils();
		WebDriverUtils wutils=new WebDriverUtils();
			
			//To get data from ExcelFile
			
			String FIRSTNAME=eutils.getDataFromExcel("Contact", 0, 1);
			String LASTNAME=eutils.getDataFromExcel("Contact", 1, 1);
			String GROUP=eutils.getDataFromExcel("Contact", 2, 1);
			String NAME=eutils.getDataFromExcel("Contact", 3, 1);
//			String BIRTH=eutils.getDataFromExcel("Contact", 4, 1);?			
			
	        //step 1:to click on Contacts
	        c.findElement(By.xpath("//a[text()='Contacts']")).click();
	        Thread.sleep(2000);	 
	        Assert.assertEquals("pune", "Deccan");
	        //step 2 :to click on + icons
	        c.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
	        
//	        Step 3: Enter First Name
	        c.findElement(By.name("firstname")).sendKeys(FIRSTNAME);

//	        Step 4:Enter Last Name
	        c.findElement(By.name("lastname")).sendKeys(LASTNAME);
	        
	        //step 5: to click on group radio button
	        
	        c.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
	        
	        //step 6:select support group in drop-down
		       WebElement dropdown = c.findElement(By.name("assigned_group_id"));
		      wutils.handleDropdownByVisableText(dropdown, GROUP);
		      
		       
		       c.findElement(By.xpath("(//img[@alt='Select'])[1]")).click();
		     
//				Switch From Parent to Child
		       String Child="http://localhost:8888/index.php?module=Accounts&action=Popup&popuptype=specific_contact_account_address&form=TasksEditView&form_submit=false&fromlink=&recordid=";
					wutils.switching(c, Child);
				Thread.sleep(2000);
				
//				Send Organization name
				c.findElement(By.id("search_txt")).sendKeys(NAME);
				
//				Click on Search NOW button
				c.findElement(By.name("search")).click();
				
				Thread.sleep(2000);
				c.findElement(By.xpath("//a[text()='Halwa101']")).click();
				
//				Switch From Child to Parent
				String parent="http://localhost:8888/index.php?module=Contacts&action=EditView&return_action=DetailView&parenttab=Marketing";
				wutils.switching(c, parent);
				Thread.sleep(2000);
				
//				For Birthday
				WebElement birth=c.findElement(By.id("jscal_field_birthday"));
				birth.sendKeys("1999-11-11");
				Thread.sleep(2000);
				
		       //step 13 : to click on save button
		       c.findElement(By.xpath("(//input[@name='button'])[1]")) .click();
		       
		       //step 14:for validation
			      String title = c.getTitle();
			      System.out.println(title);
			      
			      
			      String expectedtitle = "Administrator - Contacts - vtiger CRM 5 - Commercial Open Source CRM";
			      
			      WebDriverWait waits=new WebDriverWait(c, Duration.ofSeconds(10));
			      waits.until(ExpectedConditions.titleIs(expectedtitle));
			      
			        
//			        To Take ScreenShot of WebPage
			        Thread.sleep(3000);
			        wutils.toTakeScreenshot(c,"Contact");
			        
			       Thread.sleep(4000);

		}


}

