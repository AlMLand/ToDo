package com.example.yeshasprabhakar.todo.withframework.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.appium.java_client.android.AndroidDriver;

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
    protected byte[] createScreenShot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}
