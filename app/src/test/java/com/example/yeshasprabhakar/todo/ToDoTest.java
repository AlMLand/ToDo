package com.example.yeshasprabhakar.todo;

import com.example.yeshasprabhakar.todo.utils.ToDoTestUtils;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.Collection;

import io.appium.java_client.AppiumBy;

public class ToDoTest extends ToDoTestUtils {

    @Test
    public void addNewTaskTest() {
        driver.findElement(AppiumBy.id("com.example.yeshasprabhakar.todo:id/fab")).click();

        String expectedTitle = "Lets add new task!";
        String actualTitle = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/" +
                "android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/" +
                "android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView")).getText();
        Assert.assertEquals(expectedTitle, actualTitle);

        String expectedInput = "Test";
        driver.findElement(AppiumBy.id("com.example.yeshasprabhakar.todo:id/edit_title")).sendKeys(expectedInput);
        driver.findElement(AppiumBy.id("android:id/button1")).click();

        int expectedWebElements = 1;
        Collection<WebElement> actualWebElements = driver.findElements(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/" +
                "android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/" +
                "android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout/android.widget.FrameLayout"));
        Assert.assertEquals(expectedWebElements, actualWebElements.size());

        String actualInput = driver.findElement(AppiumBy.id("com.example.yeshasprabhakar.todo:id/title")).getText();
        Assert.assertEquals(expectedInput, actualInput);
    }

    @Test
    public void cancelAddNewTask() {
        driver.findElement(AppiumBy.id("com.example.yeshasprabhakar.todo:id/fab")).click();

        String expectedTitle = "Lets add new task!";
        String actualTitle = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/" +
                "android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/" +
                "android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView")).getText();
        Assert.assertEquals(expectedTitle, actualTitle);

        driver.findElement(AppiumBy.id("android:id/button2")).click();
        String expectedText = "What do you want to do today?";
        String fullText = driver.findElement(AppiumBy.id("com.example.yeshasprabhakar.todo:id/emptyTextView")).getText();
        String separatorWithDelimiter = "(?<=[?])";
        int firstPartId = 0;
        String actualText = fullText.split(separatorWithDelimiter)[firstPartId];

        Assert.assertEquals(expectedText, actualText);
    }

}