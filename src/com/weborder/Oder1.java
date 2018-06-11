package com.weborder;

	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Random;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
		
	public class Oder1 {
	
		public static int randNum(int a, int b) {
			Random rd = new Random();
			return rd.nextInt(b + 1 - a) + a;
		}

		public static String randName() {

			List<String> lst = new ArrayList<>();

			// try with resources
			try (FileReader fr = new FileReader("names.txt"); BufferedReader br = new BufferedReader(fr);) {

				String line = "";

				while ((line = br.readLine()) != null)
					lst.add(line);
			} catch (Exception e) {
				System.out.println("Something went wrong");
			}

			int rand = randNum(0, 4944);

			return lst.get(rand).toString();
		}

		public static void main(String[] args) throws Exception {
			System.setProperty("webdriver.chrome.driver",
					"C:/Users/arslan/Google Drive/IT/Cybertek/Selenium/selenium dependencies/drivers/chromedriver.exe");
			WebDriver driver = new ChromeDriver();

			driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");

			driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
			driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
			driver.findElement(By.name("ctl00$MainContent$login_button")).click();

			driver.findElement(By.xpath("//*[@id=\"ctl00_menu\"]/li[3]/a")).click();
			Thread.sleep(1000);
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).clear();
			Thread.sleep(1000);
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).sendKeys(randNum(1, 100) + "");
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys("John " + randName() + " Smith");
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys("123 Any st");
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys("Anytown");
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).sendKeys("VA");

			String randZip = "" + randNum(0, 9) + randNum(0, 9) + randNum(0, 9) + randNum(0, 9) + randNum(0, 9);

			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys(randZip);

			int randCard = randNum(1, 3);
			String selectedCard = "";
			StringBuilder cardNum = new StringBuilder();
			int cardLen = 0;

			switch (randCard) {
			case 1:
				selectedCard = "ctl00_MainContent_fmwOrder_cardList_0";
				cardNum.append(4);
				cardLen = 16;
				break;
			case 2:
				selectedCard = "ctl00_MainContent_fmwOrder_cardList_1";
				cardNum.append(5);
				cardLen = 16;
				break;
			case 3:
				selectedCard = "ctl00_MainContent_fmwOrder_cardList_2";
				cardNum.append(3);
				cardLen = 15;
				break;
			}

			// generate random card number
			for (int i = 0; i < cardLen - 1; i++) {
				cardNum.append(randNum(0, 9));
			}

			driver.findElement(By.id(selectedCard)).click();

			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(cardNum.toString());

			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1")).sendKeys("05/22");

			driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();

			String expected = "New order has been successfully added.";

			String actual = driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder\"]/tbody/tr/td/div/strong"))
					.getText();

			if(expected.equals(actual))
				System.out.println("Expected result matches the actual result.");
			else
				System.out.println("Expected result does not match the actual result.");

		}

	}

