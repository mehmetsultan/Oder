package com.weborder;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Order {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver",
				"/Users/Mehmet/Documents/Seleniumdependencies/drivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
		driver.findElement(By.id("ctl00_MainContent_login_button")).click();
		driver.findElement(By.xpath("//*[@id=\"ctl00_menu\"]/li[3]/a")).click();
		Random rd = new Random();
		int Result = rd.nextInt(100) + 1;
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys("" + Result);

		int middleNameLength = rd.nextInt(15);

		int a = 97; // letter ‘a’
		int z = 122; // letter ‘z’

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < middleNameLength; i++) {
			int randomLetterInt = rd.nextInt(z - a + 1) + a;
			sb.append((char) randomLetterInt);
		}
		String middleName = sb.toString(); // get a random middleName
		// System.out.println(middleName);
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys("John " + middleName + " Smith");

		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2")).sendKeys("123 Any st");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).sendKeys("Anytown");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox4")).sendKeys("Virginia");

		int zip = rd.nextInt(90000) + 10000;
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys("" + zip);

		int target = rd.nextInt(3) + 1; // get a random number from 1 to 3 (inclusive)

		if (target == 1) {
			driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_cardList_0\"]")).click();

			StringBuilder cardNumber = new StringBuilder("4");
			for (int i = 0; i < 15; i++) {
				cardNumber.append(rd.nextInt(10));
			}
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(cardNumber);
		} else if (target == 2) {
			driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_cardList_1\"]")).click();

			StringBuilder cardNumber = new StringBuilder("5");
			for (int i = 0; i < 15; i++) {
				cardNumber.append(rd.nextInt(10));
			}
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(cardNumber);
		} else {
			driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_cardList_2\"]")).click();

			StringBuilder cardNumber = new StringBuilder("3");
			for (int i = 0; i < 14; i++) {
				cardNumber.append(rd.nextInt(10));
			}
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(cardNumber);

			int m = rd.nextInt(12) + 1;
			String month = "" + m;
			if (m < 10) {
				month = 0 + month;
			}

			int y = rd.nextInt(100);
			String year = "" + y;
			if (y < 10) {
				year = 0 + year;
			}
			driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox1\"]"))
					.sendKeys(month + "/" + year);
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();

			String expected = ("New order has been successfully added");
			String actual = driver.getPageSource();
			if (actual.contains(expected)) {
				System.out.println("pass");
			} else {
				System.out.println("fail");
				System.out.println("Expected:\t" + expected);
				System.out.println("actual:\t" + actual);

			}

		}
	}
}
