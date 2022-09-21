package com.example.yeshasprabhakar.todo.withframework.test;

import static com.example.yeshasprabhakar.todo.withframework.utils.ElementAttribute.ATTRIBUTE_TEXT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.yeshasprabhakar.todo.withframework.model.FillForm;
import com.example.yeshasprabhakar.todo.withframework.model.StartPage;
import com.example.yeshasprabhakar.todo.withframework.utils.TestConfig;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ToDoTest extends TestConfig {

    @Test
    public void addNewTaskTest() {
        String expectedTitle = "Lets add new task!";
        String expectedInput = "Test";
        int expectedNumberOfToDos = 1;

        StartPage startPage = new StartPage(driver);
        FillForm fillForm = startPage.goToFillForm();
        assertEquals(expectedTitle, fillForm.getFillFormTitle());

        fillForm.setNewToDoName(expectedInput);
        startPage = fillForm.clickDone();
        assertEquals(expectedNumberOfToDos, startPage.getNumberOfToDos());

        List<String> allToDoTitles = startPage.getAllToDoTitles();
        assertEquals(1, allToDoTitles.size());
        assertTrue(allToDoTitles.contains(expectedInput));
    }

    @Test
    public void cancelAddNewTaskTest() {
        String expectedTitle = "Lets add new task!";
        String expectedText = "What do you want to do today?";

        StartPage startPage = new StartPage(driver);
        FillForm fillForm = startPage.goToFillForm();
        assertEquals(expectedTitle, fillForm.getFillFormTitle());

        startPage = fillForm.clickCancel();
        String actualText = startPage.getStartPageDescriptionTextOnlyFirstRow();
        assertEquals(expectedText, actualText);
    }

    @Test
    public void addNewTaskWithoutTitleTest() {
        String expectedTitle = "Lets add new task!";
        String expectedMessage = "Oops, Cannot set an empty ToDo!!!";

        StartPage startPage = new StartPage(driver);
        FillForm fillForm = startPage.goToFillForm();
        assertEquals(expectedTitle, fillForm.getFillFormTitle());

        startPage = fillForm.clickDone();
        String actualMessage = startPage.getToastMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void addNewTaskAndDeleteTest() {
        String expectedTitle = "Lets add new task!";
        String input = "Test";
        int expectedNumberOfToDos = 1;
        String expectedDeleteMessage = "Deleted Successfully!";

        StartPage startPage = new StartPage(driver);
        FillForm fillForm = startPage.goToFillForm();
        assertEquals(expectedTitle, fillForm.getFillFormTitle());

        fillForm.setNewToDoName(input);
        startPage = fillForm.clickDone();
        assertEquals(expectedNumberOfToDos, startPage.getNumberOfToDos());

        startPage.deleteSpecifiedToDo(input);
        startPage.waitUntilAttributeContains(startPage.getToast(), 2, ATTRIBUTE_TEXT, expectedDeleteMessage);

        String actualDeleteMessage = startPage.getToastMessage();
        assertEquals(expectedDeleteMessage, actualDeleteMessage);
    }

    @Test
    public void dayNightModeTest() {
        String input = "Test";

        StartPage startPage = new StartPage(driver);
        FillForm fillForm = startPage.goToFillForm();
        fillForm.setNewToDoName(input);
        startPage = fillForm.clickDone();
        startPage.waitUntilInvisible(startPage.getToast(), 2);

        byte[] screenshotDayMode = startPage.createScreenShot();
        startPage.activateDarkMode();
        byte[] screenshotNightMode = startPage.createScreenShot();
        assertFalse(Arrays.equals(screenshotDayMode, screenshotNightMode));
    }
}
