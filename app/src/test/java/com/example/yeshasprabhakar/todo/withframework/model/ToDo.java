package com.example.yeshasprabhakar.todo.withframework.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ToDo {

    private AndroidDriver driver;
    @CacheLookup
    @AndroidFindBy(id = "com.example.yeshasprabhakar.todo:id/title")
    private List<WebElement> toDoNames;

    public ToDo(final AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public List<String> getAllToDoTitles() {
        return toDoNames.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public int getNumberOdToDos() {
        return toDoNames.size();
    }
}
