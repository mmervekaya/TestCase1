package com.hepsiburada.tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.hepsiburada.conf.Driver;
import com.hepsiburada.conf.User;

public class AddProductToBasketWithLogin extends Driver {

		User user = new User();
		String userMail= user.getMail();
		String userPsswrd= user.getPassword();
		String userName= user.getName();
		String product="zar adam";
		String nameOfSearchProduct=null;
		
		
		
		@Test (priority=0)
		public void login() {
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='myAccount']")));
			driver.findElement(By.xpath("//div[@id='myAccount']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Destek Taleplerim']")));
			driver.findElement(By.cssSelector("a#login")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='btnLogin']")));
			driver.findElement(By.xpath("//input[@id='txtUserName']")).click();
			driver.findElement(By.xpath("//input[@id='txtUserName']")).sendKeys(userMail);
			driver.findElement(By.id("btnLogin")).click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Hoş geldiniz']")));
			driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(userPsswrd);
			driver.findElement(By.id("btnEmailSelect")).click();
		}
		
		@Test (priority=1)
		public void checkLogin() {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='myAccount']")));
			String loginUser =driver.findElement(By.cssSelector("span.sf-OldMyAccount-1k66b")).getText();
			Assert.assertEquals(userName, loginUser);
		}
		
		@Test (priority=2)
		public void searchProduct() {
			
			driver.findElement(By.xpath("//div[@class='SearchBoxOld-root']/div/div[2]/input[@type='text']")).click();
			driver.findElement(By.xpath("//div[@class='SearchBoxOld-root']/div/div[2]/input[@type='text']")).sendKeys(product);
			driver.findElement(By.xpath("//div[text()='ARA']")).click();
			
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@data-test-id='collapse-title']"), "Kategori"));
			
		}
		
		@Test (priority=3)
		public void addProductToBasket() {
           
			List <WebElement> productList = driver.findElements(By.xpath("//li[@class='productListContent-item']"));
			Assert.assertNotEquals(0, productList.size());
			productList.get(0).click();
			
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id("addToCart")));
			nameOfSearchProduct= driver.findElement(By.xpath("//h1[@class='product-name best-price-trick']")).getText();
			driver.findElement(By.id("addToCart")).click();
			
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.checkoutui-Modal-2iZXl"))).click();
			driver.findElement(By.xpath("//div[@class='SearchBoxOld-root']/div/div[2]/input[@type='text']")).click();
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement Element = driver.findElement(By.cssSelector("span.otherBuyOptions"));
			js.executeScript("arguments[0].scrollIntoView();", Element);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a#productDescription")));
			List <WebElement> otherSaler= driver.findElements(By.xpath("//button[contains(@class,'add-to-basket')]"));
			otherSaler.get(0).click();
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Sepete git']"))).click();
			
		}
		
		@Test (priority=4)
		public void checkBasket() {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='basket_headerTop_15H0U']/h1[text()='Sepetim']")));
			List<WebElement> productInBasket=driver.findElements(By.cssSelector("div.product_name_3Lh3t"));
			
			String productName1=productInBasket.get(0).findElement(By.xpath("a")).getText();
			String productName2=productInBasket.get(1).findElement(By.xpath("a")).getText();
			
			Assert.assertEquals(nameOfSearchProduct, productName1);
			Assert.assertEquals(nameOfSearchProduct, productName2);
		}
	
}
