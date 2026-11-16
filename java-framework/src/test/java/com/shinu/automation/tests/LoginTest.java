package com.shinu.automation.tests;

import com.shinu.automation.base.BaseTest;
import com.shinu.automation.pages.AccountsOverviewPage;
import com.shinu.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {

    private static final String VALID_USERNAME = System.getenv().getOrDefault("PARABANK_USER", "CHANGE_ME");
    private static final String VALID_PASSWORD = System.getenv().getOrDefault("PARABANK_PASSWORD", "CHANGE_ME");

    @Test(description = "Unknown username does not log in. KNOWN DEFECT: public ParaBank authenticates unknown users",
            groups = {"regression", "known-issue"})
    public void wrongPasswordDoesNotLogIn() {
        new LoginPage().loginExpectingError(VALID_USERNAME, "wrong-password-123");
        Assert.assertFalse(new AccountsOverviewPage().isLoadedWithin(Duration.ofSeconds(3)),
                "User was logged in with a wrong password");
    }

    @Test(description = "Unknown username does not log in. KNOWN DEFECT: public ParaBank authenticates unknown users",
            groups = {"regression", "known-issue"})
    public void unknownUserDoesNotLogIn() {
        new LoginPage().loginExpectingError("no_such_user_98765", "any-password");
        Assert.assertFalse(new AccountsOverviewPage().isLoadedWithin(Duration.ofSeconds(3)),
                "Unknown user was logged in");
    }

    @Test(description = "Wrong password does not log the user in. KNOWN DEFECT: public ParaBank authenticates any password",
            groups = {"regression", "known-issue"})
    public void wrongPasswordShowsErrorMessage() {
        LoginPage loginPage = new LoginPage().loginExpectingError(VALID_USERNAME, "wrong-password-123");
        Assert.assertFalse(loginPage.getErrorMessage().isBlank(), "Expected an error for a wrong password");
    }

    @Test(description = "Unknown user shows an error message. KNOWN ISSUE: same as above",
            groups = {"regression", "known-issue"})
    public void unknownUserShowsErrorMessage() {
        LoginPage loginPage = new LoginPage().loginExpectingError("no_such_user_98765", "any-password");
        Assert.assertFalse(loginPage.getErrorMessage().isBlank(), "Expected an error for an unknown user");
    }

    @Test(description = "Valid user lands on Accounts Overview", groups = {"smoke"})
    public void validLoginShowsAccountsOverview() {
        AccountsOverviewPage overview = new LoginPage().loginAs(VALID_USERNAME, VALID_PASSWORD);
        Assert.assertTrue(overview.isLoaded(), "Accounts Overview page not shown after login");
    }

    @Test(description = "User can log out after logging in", groups = {"regression"})
    public void userCanLogOut() {
        LoginPage loginPage = new LoginPage().loginAs(VALID_USERNAME, VALID_PASSWORD).logOut();
        Assert.assertTrue(loginPage.isLoginPanelDisplayed(), "Login panel not shown after logging out");
    }
}