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
import CommonUtils.Listenerimplementation;
import CommonUtils.WebDriverUtils;

@Listeners(Listenerimplementation.class)
public class CreateLeadTest  extends BaseClass{

		@Test
		public void LeadsTest() throws EncryptedDocumentException, IOException, InterruptedException {

			WebDriverUtils wutils=new WebDriverUtils();
			
			ExcelFileUtils eutils=new ExcelFileUtils();
			String FIRST=eutils.getDataFromExcel("LEADS", 0, 1);
			String LAST=eutils.getDataFromExcel("LEADS", 1, 1);
			String COMPANY=eutils.getDataFromExcel("LEADS", 2, 1);
			String ASSIGN=eutils.getDataFromExcel("LEADS", 3, 1);
			
			
			
			c.findElement(By.xpath("(//a[text()='Leads'])[1]")).click();
			
			c.findElement(By.xpath("//img[@alt='Create Lead...']")).click();
			Thread.sleep(3000);
			Assert.assertEquals("Pune", "Deccan");
			
			c.findElement(By.name("firstname")).sendKeys(FIRST);
			c.findElement(By.name("lastname")).sendKeys(LAST);
			c.findElement(By.name("company")).sendKeys(COMPANY);
			c.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
			
			WebElement drpdwn=c.findElement(By.name("assigned_group_id"));
			wutils.handleDropdownByVisableText(drpdwn, ASSIGN);
			
			c.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
			
		}
	}

