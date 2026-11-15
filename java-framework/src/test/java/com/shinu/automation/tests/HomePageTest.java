package com.shinu.automation.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Day 1: the simplest possible Selenium + TestNG tests.
 * No page objects, no config files yet. We add those on Day 2 and Day 3,
 * so you can explain in interviews WHY each layer exists.
 */
public class HomePageTest {

    private static final String BASE_URL = "https://parabank.parasoft.com/parabank/index.htm";
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();           // Selenium Manager resolves ChromeDriver automatically
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @Test(description = "Home page opens with the ParaBank title")
    public void homePageTitleIsCorrect() {
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("ParaBank"), "Unexpected page title: " + title);
    }

    @Test(description = "Customer login panel is visible on the home page")
    public void loginPanelIsDisplayed() {
        Assert.assertTrue(driver.findElement(By.name("username")).isDisplayed(), "Username field not visible");
        Assert.assertTrue(driver.findElement(By.name("password")).isDisplayed(), "Password field not visible");
        Assert.assertTrue(driver.findElement(By.cssSelector("input[value='Log In']")).isDisplayed(), "Log In button not visible");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();                     // quit() closes every window and ends the session; close() only closes one window
        }
    }
}
