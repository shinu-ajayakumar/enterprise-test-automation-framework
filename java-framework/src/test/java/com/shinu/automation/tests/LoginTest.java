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

    @Test(description = "Wrong password does not log the user in", groups = {"regression"})
    public void wrongPasswordDoesNotLogIn() {
        new LoginPage().loginExpectingError(VALID_USERNAME, "wrong-password-123");
        Assert.assertFalse(new AccountsOverviewPage().isLoadedWithin(Duration.ofSeconds(3)),
                "User was logged in with a wrong password");
    }

    @Test(description = "Unknown username does not log in", groups = {"regression"})
    public void unknownUserDoesNotLogIn() {
        new LoginPage().loginExpectingError("no_such_user_98765", "any-password");
        Assert.assertFalse(new AccountsOverviewPage().isLoadedWithin(Duration.ofSeconds(3)),
                "Unknown user was logged in");
    }

    @Test(description = "Wrong password shows an error message. KNOWN ISSUE: public ParaBank returns a JSON 400 instead of the HTML error page",
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
}