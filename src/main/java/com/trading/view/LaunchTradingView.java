package com.trading.view;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

	public class LaunchTradingView {
	
	    public static void main(String[] args) {

        // Automatically setup ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Set Chrome options to use your existing user profile
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("user-data-dir=C:\\Users\\hp\\AppData\\Local\\Google\\Chrome\\User Data");
//        options.addArguments("--profile-directory=Default"); // Use the default profile

        // Initialize WebDriver with options
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to NSE India
            driver.get("https://www.nseindia.com/");

            // Scrape high, low, and close values
            WebElement close = driver.findElement(By.xpath("(//span[@class='val'])[1]"));
            String closeValue = close.getText();
            System.out.println("Close Value: " + closeValue);

            WebElement high = driver.findElement(By.xpath("(//span[@class='highVal'])[1]"));
            String highValue = high.getText();
            System.out.println("High Value: " + highValue);

            WebElement low = driver.findElement(By.xpath("(//span[@class='lowVal'])[1]"));
            String lowValue = low.getText();
            System.out.println("Low Value: " + lowValue);

            // Update Google Sheet with scraped values
            GoogleSheets.updateSheet(highValue, lowValue, closeValue);

            // Fetch support values
            String supportRange = "Sheet1!F2:I2";
            System.out.println("Fetching support values...");
            GoogleSheets.fetchSupportValues(supportRange);

            // Fetch resistance values
            String resistanceRange = "Sheet1!J2:M2";
            System.out.println("Fetching resistance values...");
            GoogleSheets.fetchResistancetValues(resistanceRange);

//            // Instantiate TradingViewUtils for further actions
//            TradingViewUtils tradingViewUtils = new TradingViewUtils(driver);
//            tradingViewUtils.openTradingViewChart();
//            tradingViewUtils.addHorizontalLine("18000", "Resistance");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
