package com.example.yeshasprabhakar.todo;

import static com.example.yeshasprabhakar.todo.utils.ToDoLocators.ADD_NEW_TASK;
import static com.example.yeshasprabhakar.todo.utils.ToDoLocators.DELETE_TASK;
import static com.example.yeshasprabhakar.todo.utils.ToDoLocators.DESCRIPTION_MESSAGE;
import static com.example.yeshasprabhakar.todo.utils.ToDoLocators.LISTED_TASK;
import static com.example.yeshasprabhakar.todo.utils.ToDoLocators.NEW_TASK_CANCEL;
import static com.example.yeshasprabhakar.todo.utils.ToDoLocators.NEW_TASK_DONE;
import static com.example.yeshasprabhakar.todo.utils.ToDoLocators.NEW_TASK_INPUT;
import static com.example.yeshasprabhakar.todo.utils.ToDoLocators.NEW_TASK_TITLE;
import static com.example.yeshasprabhakar.todo.utils.ToDoLocators.TASK_NAME;
import static com.example.yeshasprabhakar.todo.utils.ToDoLocators.TOAST_MESSAGE;

import com.example.yeshasprabhakar.todo.utils.ToDoTestUtils;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.Collection;

import io.appium.java_client.AppiumBy;

public class ToDoTest extends ToDoTestUtils {

    @Test
    public void addNewTaskTest() {
        String expectedTitle = "Lets add new task!";
        String expectedInput = "Test";
        int expectedWebElements = 1;

        driver.findElement(AppiumBy.id(ADD_NEW_TASK.getLocator())).click();
        String actualTitle = driver.findElement(AppiumBy.id(NEW_TASK_TITLE.getLocator())).getText();

        Assert.assertEquals(expectedTitle, actualTitle);
        driver.findElement(AppiumBy.id(NEW_TASK_INPUT.getLocator())).sendKeys(expectedInput);
        driver.findElement(AppiumBy.id(NEW_TASK_DONE.getLocator())).click();
        Collection<WebElement> actualWebElements = driver.findElements(AppiumBy.xpath(LISTED_TASK.getLocator()));

        Assert.assertEquals(expectedWebElements, actualWebElements.size());
        String actualInput = driver.findElement(AppiumBy.id(TASK_NAME.getLocator())).getText();

        Assert.assertEquals(expectedInput, actualInput);
    }

    @Test
    public void cancelAddNewTask() {
        String separatorWithDelimiter = "(?<=[?])";
        String expectedTitle = "Lets add new task!";
        String expectedText = "What do you want to do today?";

        driver.findElement(AppiumBy.id(ADD_NEW_TASK.getLocator())).click();
        String actualTitle = driver.findElement(AppiumBy.id(NEW_TASK_TITLE.getLocator())).getText();

        Assert.assertEquals(expectedTitle, actualTitle);
        driver.findElement(AppiumBy.id(NEW_TASK_CANCEL.getLocator())).click();
        String fullText = driver.findElement(AppiumBy.id(DESCRIPTION_MESSAGE.getLocator())).getText();
        int firstPartId = 0;
        String actualText = fullText.split(separatorWithDelimiter)[firstPartId];

        Assert.assertEquals(expectedText, actualText);
    }

    @Test
    public void addNewTaskWithoutTitle() {
        String expectedTitle = "Lets add new task!";
        String expectedMessage = "Oops, Cannot set an empty ToDo!!!";

        driver.findElement(AppiumBy.id(ADD_NEW_TASK.getLocator())).click();
        String actualTitle = driver.findElement(AppiumBy.id(NEW_TASK_TITLE.getLocator())).getText();

        Assert.assertEquals(expectedTitle, actualTitle);
        driver.findElement(AppiumBy.id(NEW_TASK_DONE.getLocator())).click();
        String actualMessage = driver.findElement(AppiumBy.xpath(TOAST_MESSAGE.getLocator())).getText();

        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void addNewTaskAndDelete() throws InterruptedException {
        String expectedTitle = "Lets add new task!";
        String expectedInput = "Test";
        int expectedWebElements = 1;
        String expectedDeleteMessage = "Deleted Successfully!";

        driver.findElement(AppiumBy.id(ADD_NEW_TASK.getLocator())).click();
        String actualTitle = driver.findElement(AppiumBy.id(NEW_TASK_TITLE.getLocator())).getText();

        Assert.assertEquals(expectedTitle, actualTitle);
        driver.findElement(AppiumBy.id(NEW_TASK_INPUT.getLocator())).sendKeys(expectedInput);
        driver.findElement(AppiumBy.id(NEW_TASK_DONE.getLocator())).click();
        Collection<WebElement> actualWebElements = driver.findElements(AppiumBy.xpath(LISTED_TASK.getLocator()));
        
        Assert.assertEquals(expectedWebElements, actualWebElements.size());
        String actualInput = driver.findElement(AppiumBy.id(TASK_NAME.getLocator())).getText();

        Assert.assertEquals(expectedInput, actualInput);
        driver.findElement(AppiumBy.id(DELETE_TASK.getLocator())).click();
        // TODO : how make explicit wait; PROBLEM : the old toast is still there
        Thread.sleep(2000);
        String actualDeleteMessage = driver.findElement(AppiumBy.xpath(TOAST_MESSAGE.getLocator())).getText();

        Assert.assertEquals(expectedDeleteMessage, actualDeleteMessage);
    }

}