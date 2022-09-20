package com.example.yeshasprabhakar.todo.withframework.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FillForm {

    private AndroidDriver driver;
    @CacheLookup
    @AndroidFindBy(id = "com.example.yeshasprabhakar.todo:id/alertTitle")
    private WebElement fillFormTitle;
    @CacheLookup
    @AndroidFindBy(id = "com.example.yeshasprabhakar.todo:id/edit_title")
    private WebElement inputField;
    @CacheLookup
    @AndroidFindBy(id = "android:id/button1")
    private WebElement doneButton;

    public FillForm(final AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public StartPage clickDone() {
        doneButton.click();
        return new StartPage(driver);
    }

    public void setNewToDoName(final String name) {
        inputField.sendKeys(name);
    }

    public String getFillFormTitle() {
        return fillFormTitle.getText();
    }
}
