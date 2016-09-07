package com.gspann;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Listeners(com.gspann.MyListner.class)
public class RodanTest {
	static public WebDriver driver;

	@Parameters("browser")
	@BeforeMethod
	public void startup(@Optional ("firefox")String browsertype)
	{
		//browsertype="firefox";
	if (browsertype.equalsIgnoreCase("firefox"))
	{
	driver=new FirefoxDriver();
	}
	
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	driver.get("https://www.rodanandfields.com/ca/");
	}
	
	@Test
	public void Homepage() throws InterruptedException
	{
		RodanClass rodan=new RodanClass(driver);
		rodan.clickSkinCare();
		System.out.println("done1");
		rodan.mouseOver();
		System.out.println("done2");
		rodan.clickallProduct();
		System.out.println("done3");
		Thread.sleep(7000);
		
		rodan.refineByDropdown(); 
		System.out.println("Refine By dropdown is clicked");
		rodan.redefineCheckbox();
		System.out.println("redefine checkbox clicked");
		Thread.sleep(3000);
		rodan.refineByDropdown();
		System.out.println("refineByDropdown is clicked");
		rodan.reverseCheckbox();
		Thread.sleep(3000);
		rodan.PriceDropDown();
		System.out.println("price dropdown is clicked");
		rodan.selectPrice();
		System.out.println("price is selected ");
		Thread.sleep(3000);
		
		Boolean checkbox=rodan.verifyRefineRedefine();
		Assert.assertTrue(checkbox, "Checkboxis not present");
		System.out.println("done 4");
		
		boolean pricecheck=rodan.verifyPrice();
		Assert.assertTrue(pricecheck, "price is not present");
		System.out.println("done 5");
		rodan.close();
				
	}
	
	
	@Test
	public void consultant() throws InterruptedException
	{
		driver.get("https://www.rodanandfields.com/ca/");
		driver.manage().window().maximize();
		RodanConsultant consult=new RodanConsultant(driver);
		consult.clickBecomeConsultant();
		System.out.println("done6");
		Thread.sleep(4000);
		consult.mouseOver();
		Thread.sleep(4000);
		System.out.println("done7");
		consult.clickEnrollNow();
		Thread.sleep(4000);
		System.out.println("done8");
		consult.clickPolicies();
		Thread.sleep(4000);
		System.out.println("done9");
		consult.switchWindow();
		Thread.sleep(4000);
		System.out.println("Window switch done");
		String url=consult.verifyUrl();
		Assert.assertTrue(url.contains("https://www.rodanandfields.com/_ui/responsive/common/pdf/Archives/Policies_Procedures_CANADA.pdf"), "url mismatch");
		consult.close();
		consult.switchParentWin();
		System.out.println("Window is switched to parent");
		consult.enterName();
		Thread.sleep(4000);
		System.out.println("done10");
		consult.search();
		Thread.sleep(4000);
		System.out.println("done11");
		consult.productMouseOver();
		Thread.sleep(4000);
		System.out.println("done12");
		String productName=consult.getProductName();
		consult.productClick();
		Thread.sleep(4000);
		System.out.println("done13");
		String actualProduct=consult.brandVerify();
		String actualProductLowerCase=actualProduct.toLowerCase();
		Assert.assertEquals(actualProductLowerCase, productName);
		System.out.println("done14");
		consult.close();
	}
	
}
	

