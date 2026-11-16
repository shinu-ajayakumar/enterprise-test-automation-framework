package com.shinu.automation.pages;

import org.openqa.selenium.By;

/** Page shown after a successful login. */
public class AccountsOverviewPage extends BasePage {

    private final By heading   = By.xpath("//h1[contains(normalize-space(),'Accounts Overview')]");
    private final By logOutLink = By.linkText("Log Out");

    public boolean isLoaded() {
        return isDisplayed(heading);
    }

    public String getHeading() {
        return getText(heading);
    }

    public LoginPage logOut() {
        click(logOutLink);
        return new LoginPage();
    }

    /** Quick negative check used after a failed login. */
    public boolean isLoadedWithin(java.time.Duration timeout) {
        return isDisplayedWithin(logOutLink, timeout);
    }
}