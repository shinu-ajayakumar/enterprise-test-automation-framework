package com.shinu.automation.pages;

import org.openqa.selenium.By;

/** The customer login panel on the ParaBank home page. */
public class LoginPage extends BasePage {

    private final By usernameInput = By.name("username");
    private final By passwordInput = By.name("password");
    private final By loginButton   = By.cssSelector("input[value='Log In']");
    private final By errorMessage  = By.cssSelector("p.error");

    public boolean isLoginPanelDisplayed() {
        return isDisplayed(usernameInput) && isDisplayed(passwordInput) && isDisplayed(loginButton);
    }

    public void login(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        click(loginButton);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    /** Use when the test expects login to succeed. */
    public AccountsOverviewPage loginAs(String username, String password) {
        login(username, password);
        return new AccountsOverviewPage();
    }

    /** Use when the test expects login to fail and stay on an error. */
    public LoginPage loginExpectingError(String username, String password) {
        login(username, password);
        return this;
    }
}