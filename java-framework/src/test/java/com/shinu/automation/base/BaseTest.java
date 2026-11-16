package com.shinu.automation.base;

import com.shinu.automation.core.ConfigManager;
import com.shinu.automation.core.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class BaseTest {

    protected static final String BASE_URL = ConfigManager.get("base.url");

    protected final Logger log = LogManager.getLogger(getClass());

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) {
        log.info("===== START {} =====", method.getName());
        DriverFactory.initDriver();
        driver().get(BASE_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String status = switch (result.getStatus()) {
            case ITestResult.SUCCESS -> "PASSED";
            case ITestResult.FAILURE -> "FAILED";
            default -> "SKIPPED";
        };

        if (result.getStatus() == ITestResult.FAILURE) {
            captureFailureEvidence(testName);
        }

        log.info("===== END {} : {} =====", testName, status);
        DriverFactory.quitDriver();
    }

    private void captureFailureEvidence(String testName) {
        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            Path dir = Paths.get("target", "screenshots");
            Files.createDirectories(dir);
            Path file = dir.resolve(testName + "_" + timestamp + ".png");

            byte[] png = ((TakesScreenshot) driver()).getScreenshotAs(OutputType.BYTES);
            Files.write(file, png);

            log.error("Test failed on URL: {}", driver().getCurrentUrl());
            log.error("Screenshot saved: {}", file.toAbsolutePath());
            Reporter.log("Screenshot: <a href='" + file.toAbsolutePath().toUri() + "'>" + file.getFileName() + "</a>");
        } catch (Exception e) {
            log.warn("Could not capture failure evidence: {}", e.getMessage());   // evidence must never hide the real failure
        }
    }

    protected WebDriver driver() {
        return DriverFactory.getDriver();
    }
}
