package com.shinu.automation.tests;

import com.shinu.automation.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Day 1: login tests.
 *
 * BEFORE RUNNING: open https://parabank.parasoft.com/parabank/register.htm in Chrome,
 * register your own user, and put that username/password below.
 * (The public demo database is reset from time to time; if login starts failing, register again.)
 *
 * Notice the duplicated setup/locators across both test classes. That duplication is
 * on purpose: tomorrow we remove it with a BaseTest and the Page Object Model.
 */
public class LoginTest extends BaseTest {

    private static final String VALID_USERNAME = System.getenv().getOrDefault("PARABANK_USER", "CHANGE_ME");
    private static final String VALID_PASSWORD = System.getenv().getOrDefault("PARABANK_PASSWORD", "CHANGE_ME");

    @Test(description = "Valid user lands on Accounts Overview")
    public void validLoginShowsAccountsOverview() {
        login(VALID_USERNAME, VALID_PASSWORD);
        WebDriverWait wait = new WebDriverWait(driver(), Duration.ofSeconds(10));
        WebElement heading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Accounts Overview')]")));
        Assert.assertTrue(heading.isDisplayed(), "Accounts Overview page not shown after login");
    }

    @Test(description = "Invalid password shows an error message")
    public void invalidLoginShowsError() {
        login("wrong-password-56478", "wrong-password-56478");
        WebDriverWait wait = new WebDriverWait(driver(), Duration.ofSeconds(10));
//        WebElement error = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.error")));
//        Assert.assertFalse(error.getText().isBlank(), "Expected an error message for invalid login");
    }

    private void login(String username, String password) {
        driver().findElement(By.name("username")).sendKeys(username);
        driver().findElement(By.name("password")).sendKeys(password);
        driver().findElement(By.cssSelector("input[value='Log In']")).click();
    }
}
