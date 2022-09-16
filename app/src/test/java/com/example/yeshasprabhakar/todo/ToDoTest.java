package com.example.yeshasprabhakar.todo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class ToDoTest {

    private static final String APPIUM_SERVER_PATH = "%s\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
    private static final String IP_ADDRESS = "127.0.0.1";
    private static final int PORT = 4723;
    private static final String USER_HOME = "user.home";
    private static final String DEVICE_NAME = "Nexus_6_API_33";
    private static final String URL = "http://127.0.0.1:4723";
    private static final String TODO_APP_PATH = "%s\\StudioProjects\\ToDo\\app\\build\\outputs\\apk\\debug\\app-debug.apk";
    private AppiumDriverLocalService service;
    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        service = new AppiumServiceBuilder()
                .withAppiumJS(
                        new File(String.format(APPIUM_SERVER_PATH, System.getProperty(USER_HOME))))
                .withIPAddress(IP_ADDRESS)
                .usingPort(PORT)
                .build();
        service.start();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(DEVICE_NAME);
        options.setApp(String
                .format(TODO_APP_PATH, System.getProperty(USER_HOME)));

        driver = new AndroidDriver(new URL(URL), options);
    }

    @Test
    public void test() {
        System.out.println("success");
    }

    @After
    public void tearDown() {
        driver.quit();
        service.stop();
    }
}