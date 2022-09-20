package com.example.yeshasprabhakar.todo;

import static com.example.yeshasprabhakar.todo.utils.ToDoLocators.ADD_NEW_TASK;
import static com.example.yeshasprabhakar.todo.utils.ToDoLocators.DAY_NIGHT_MODE;
import static com.example.yeshasprabhakar.todo.utils.ToDoLocators.DELETE_TASK;
import static com.example.yeshasprabhakar.todo.utils.ToDoLocators.DESCRIPTION_MESSAGE;
import static com.example.yeshasprabhakar.todo.utils.ToDoLocators.NEW_TASK_CANCEL;
import static com.example.yeshasprabhakar.todo.utils.ToDoLocators.NEW_TASK_DONE;
import static com.example.yeshasprabhakar.todo.utils.ToDoLocators.NEW_TASK_INPUT;
import static com.example.yeshasprabhakar.todo.utils.ToDoLocators.NEW_TASK_TITLE;
import static com.example.yeshasprabhakar.todo.utils.ToDoLocators.TASK_TITLE;
import static com.example.yeshasprabhakar.todo.utils.ToDoLocators.TOAST_MESSAGE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.example.yeshasprabhakar.todo.utils.ToDoTestUtils;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import io.appium.java_client.AppiumBy;

public class ToDoTest extends ToDoTestUtils {

    @Test
    public void addNewTaskTest() {
        String expectedTitle = "Lets add new task!";
        String expectedInput = "Test";
        int expectedToDosCount = 1;

        driver.findElement(AppiumBy.id(ADD_NEW_TASK.getLocator())).click();
        String actualTitle = driver.findElement(AppiumBy.id(NEW_TASK_TITLE.getLocator())).getText();
        assertEquals(expectedTitle, actualTitle);

        driver.findElement(AppiumBy.id(NEW_TASK_INPUT.getLocator())).sendKeys(expectedInput);
        driver.findElement(AppiumBy.id(NEW_TASK_DONE.getLocator())).click();
        List<WebElement> actualToDos = driver.findElements(AppiumBy.id(TASK_TITLE.getLocator()));
        Assert.assertEquals(expectedToDosCount, actualToDos.size());

        String actualInput = driver.findElement(AppiumBy.id(TASK_TITLE.getLocator())).getText();
        assertEquals(expectedInput, actualInput);
    }

    @Test
    public void cancelAddNewTaskTest() {
        String separatorWithDelimiter = "(?<=[?])";
        String expectedTitle = "Lets add new task!";
        String expectedText = "What do you want to do today?";
        int firstPartId = 0;

        driver.findElement(AppiumBy.id(ADD_NEW_TASK.getLocator())).click();
        String actualTitle = driver.findElement(AppiumBy.id(NEW_TASK_TITLE.getLocator())).getText();
        assertEquals(expectedTitle, actualTitle);

        driver.findElement(AppiumBy.id(NEW_TASK_CANCEL.getLocator())).click();
        String fullText = driver.findElement(AppiumBy.id(DESCRIPTION_MESSAGE.getLocator())).getText();
        String actualText = fullText.split(separatorWithDelimiter)[firstPartId];
        assertEquals(expectedText, actualText);
    }

    @Test
    public void addNewTaskWithoutTitleTest() {
        String expectedTitle = "Lets add new task!";
        String expectedMessage = "Oops, Cannot set an empty ToDo!!!";

        driver.findElement(AppiumBy.id(ADD_NEW_TASK.getLocator())).click();
        String actualTitle = driver.findElement(AppiumBy.id(NEW_TASK_TITLE.getLocator())).getText();
        assertEquals(expectedTitle, actualTitle);

        driver.findElement(AppiumBy.id(NEW_TASK_DONE.getLocator())).click();
        String actualMessage = driver.findElement(AppiumBy.xpath(TOAST_MESSAGE.getLocator())).getText();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void addNewTaskAndDeleteTest() throws InterruptedException {
        String expectedTitle = "Lets add new task!";
        String expectedInput = "Test";
        int expectedToDosCount = 1;
        String expectedDeleteMessage = "Deleted Successfully!";
        String attribute = "text";

        driver.findElement(AppiumBy.id(ADD_NEW_TASK.getLocator())).click();
        String actualTitle = driver.findElement(AppiumBy.id(NEW_TASK_TITLE.getLocator())).getText();
        assertEquals(expectedTitle, actualTitle);

        driver.findElement(AppiumBy.id(NEW_TASK_INPUT.getLocator())).sendKeys(expectedInput);
        driver.findElement(AppiumBy.id(NEW_TASK_DONE.getLocator())).click();
        List<WebElement> actualToDos = driver.findElements(AppiumBy.id(TASK_TITLE.getLocator()));
        Assert.assertEquals(expectedToDosCount, actualToDos.size());

        for (int i = 0; i < actualToDos.size(); i++) {
            String toDoTitle = actualToDos.get(i).getText();
            if (toDoTitle.equals(expectedInput)) {
                driver.findElements(AppiumBy.id(DELETE_TASK.getLocator())).get(i).click();
            }
        }

        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driverWait.until(ExpectedConditions
                .attributeContains(AppiumBy.xpath(TOAST_MESSAGE.getLocator()), attribute, expectedDeleteMessage));

        String actualDeleteMessage = driver.findElement(AppiumBy.xpath(TOAST_MESSAGE.getLocator())).getText();
        assertEquals(expectedDeleteMessage, actualDeleteMessage);
    }

    @Test
    public void dayNightModeTest() throws InterruptedException {
        String input = "Test";

        // make the app non-interactive for the test in 3 steps
        driver.findElement(AppiumBy.id(ADD_NEW_TASK.getLocator())).click();
        driver.findElement(AppiumBy.id(NEW_TASK_INPUT.getLocator())).sendKeys(input);
        driver.findElement(AppiumBy.id(NEW_TASK_DONE.getLocator())).click();

        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driverWait.until(ExpectedConditions.invisibilityOf(driver.findElement(AppiumBy.xpath(TOAST_MESSAGE.getLocator()))));

        byte[] screenshotDayMode = createScreenShot();
        driver.findElement(AppiumBy.id(DAY_NIGHT_MODE.getLocator())).click();
        byte[] screenshotNightMode = createScreenShot();
        assertFalse(Arrays.equals(screenshotDayMode, screenshotNightMode));
    }

}