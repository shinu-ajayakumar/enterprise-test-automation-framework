package com.shinu.automation.tests;

import com.shinu.automation.base.BaseTest;
import com.shinu.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test(description = "Home page opens with the ParaBank title", groups = {"smoke"})
    public void homePageTitleIsCorrect() {
        String title = driver().getTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("ParaBank"), "Unexpected page title: " + title);
    }

    @Test(description = "Customer login panel is visible on the home page", groups = {"smoke"})
    public void loginPanelIsDisplayed() {
        Assert.assertTrue(new LoginPage().isLoginPanelDisplayed(), "Login panel not visible");
    }
}
