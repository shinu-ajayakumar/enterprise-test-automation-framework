package com.shinu.automation.tests;

import com.shinu.automation.base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Day 1: the simplest possible Selenium + TestNG tests.
 * No page objects, no config files yet. We add those on Day 2 and Day 3,
 * so you can explain in interviews WHY each layer exists.
 */
public class HomePageTest extends BaseTest {

    @Test(description = "Home page opens with the ParaBank title", groups = {"smoke"})
    public void homePageTitleIsCorrect() {
        String title = driver().getTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("ParaBank"), "Unexpected page title: " + title);
    }

    @Test(description = "Customer login panel is visible on the home page", groups = {"smoke"})
    public void loginPanelIsDisplayed() {
        Assert.assertTrue(driver().findElement(By.name("username")).isDisplayed(), "Username field not visible");
        Assert.assertTrue(driver().findElement(By.name("password")).isDisplayed(), "Password field not visible");
        Assert.assertTrue(driver().findElement(By.cssSelector("input[value='Log In']")).isDisplayed(), "Log In button not visible");
    }
}
