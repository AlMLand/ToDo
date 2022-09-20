package com.example.yeshasprabhakar.todo.withframework.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.yeshasprabhakar.todo.withframework.model.FillForm;
import com.example.yeshasprabhakar.todo.withframework.model.StartPage;
import com.example.yeshasprabhakar.todo.withframework.utils.TestConfig;

import org.junit.Test;

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

}
