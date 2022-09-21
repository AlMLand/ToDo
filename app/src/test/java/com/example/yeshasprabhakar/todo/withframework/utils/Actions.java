package com.example.yeshasprabhakar.todo.withframework.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import io.appium.java_client.android.AndroidDriver;

/**
 * This is a helper class, which has the common function.
 */
public class Actions {

    private AndroidDriver driver;

    public Actions(final AndroidDriver driver) {
        this.driver = driver;
    }

    /**
     * Create a screen shot from current point.
     *
     * @return the byte array
     */
    public byte[] createScreenShot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * Wait specified time until the element is invisible.
     *
     * @param element the element
     * @param seconds the duration of seconds
     */
    public void waitUntilInvisible(final WebElement element, final int seconds) {
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        driverWait.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * Wait specified time until the element has message equals to `message`.
     *
     * @param element   the element
     * @param seconds   the duration of seconds
     * @param attribute the element attribute
     * @param message   the specified message
     */
    public void waitUntilAttributeContains(final WebElement element, final int seconds, final ElementAttribute attribute,
                                           final String message) {
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        driverWait.until(ExpectedConditions
                .attributeContains(element, attribute.getAttribute(), message));
    }
}
