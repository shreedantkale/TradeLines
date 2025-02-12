package com.trading.view;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TradingViewUtils {

    private WebDriver driver;

    public TradingViewUtils(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Navigate to TradingView chart page.
     */
    public void openTradingViewChart() {
        driver.get("https://in.tradingview.com/chart/wPubGvON/?symbol=NSE%3ANIFTY");
        System.out.println("Opened TradingView chart page.");
    }

    /**
     * Adds a horizontal line at the given coordinate with a label.
     *
     * @param coordinate The price level for the horizontal line.
     * @param label      The label for the line (e.g., Support, Resistance).
     * @throws InterruptedException If thread sleep is interrupted.
     */
    public void addHorizontalLine(String coordinate, String label) throws InterruptedException {
        // Click on the chart area to open the context menu
        WebElement chartArea = driver.findElement(By.className("chart-container"));
        chartArea.click();

        // Select the horizontal line tool (adjust XPath if needed)
        WebElement horizontalLineTool = driver.findElement(By.xpath("//button[@title='Horizontal line']"));
        horizontalLineTool.click();

        // Open settings to input the coordinate (adjust XPath if needed)
        Thread.sleep(2000);
        WebElement settingsButton = driver.findElement(By.xpath("//button[@title='Settings']"));
        settingsButton.click();

        // Set the line coordinate
        WebElement inputBox = driver.findElement(By.xpath("//input[@placeholder='Coordinate']"));
        inputBox.clear();
        inputBox.sendKeys(coordinate);

        // Set the label (if TradingView allows labeling lines)
        WebElement labelInput = driver.findElement(By.xpath("//input[@placeholder='Label']"));
        labelInput.clear();
        labelInput.sendKeys(label);

        // Save and close the settings
        WebElement saveButton = driver.findElement(By.xpath("//button[@title='OK']"));
        saveButton.click();

        System.out.println("Added horizontal line: " + label + " at " + coordinate);
    }
}
