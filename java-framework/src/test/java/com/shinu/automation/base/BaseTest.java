package com.shinu.automation.base;

import com.shinu.automation.core.ConfigManager;
import com.shinu.automation.core.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    protected static final String BASE_URL = ConfigManager.get("base.url");

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        DriverFactory.initDriver();
        driver().get(BASE_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    protected WebDriver driver() {
        return DriverFactory.getDriver();
    }
}
