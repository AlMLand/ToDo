package com.example.yeshasprabhakar.todo.withframework.model;

import com.example.yeshasprabhakar.todo.withframework.utils.Actions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class StartPage extends Actions {

    private AndroidDriver driver;
    private ToDo toDo;
    @CacheLookup
    @AndroidFindBy(id = "com.example.yeshasprabhakar.todo:id/fab")
    private WebElement addNewToDoButton;

    public StartPage(final AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        this.toDo = new ToDo(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public List<String> getAllToDoTitles() {
        return toDo.getAllToDoTitles();
    }

    public int getNumberOfToDos() {
        return toDo.getNumberOdToDos();
    }

    public FillForm goToFillForm() {
        addNewToDoButton.click();
        return new FillForm(driver);
    }

}