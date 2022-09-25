package com.example.yeshasprabhakar.todo.withframework.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * ToDo logic filed externally.
 */
public class ToDo {

    @CacheLookup
    @AndroidFindBy(id = "com.example.yeshasprabhakar.todo:id/title")
    private List<WebElement> toDoNames;
    @CacheLookup
    @AndroidFindBy(id = "com.example.yeshasprabhakar.todo:id/delete")
    private WebElement deleteButton;

    public ToDo(final AndroidDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public int getNumberOfToDos() {
        return toDoNames.size();
    }

    public void deleteSpecifiedToDo(final String toDoName) {
        toDoNames.stream()
                .map(WebElement::getText).filter(name -> name.equals(toDoName)).forEach(name -> deleteButton.click());
    }

    public List<String> getAllToDoTitles() {
        return toDoNames.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
