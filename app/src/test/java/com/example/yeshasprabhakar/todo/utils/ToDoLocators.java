package com.example.yeshasprabhakar.todo.utils;

public enum ToDoLocators {

    DAY_NIGHT_MODE("com.example.yeshasprabhakar.todo:id/themeActionButton"),

    ADD_NEW_TASK("com.example.yeshasprabhakar.todo:id/fab"),
    DESCRIPTION_MESSAGE("com.example.yeshasprabhakar.todo:id/emptyTextView"),
    DELETE_TASK("com.example.yeshasprabhakar.todo:id/delete"),
    TASK_TITLE("com.example.yeshasprabhakar.todo:id/title"),

    NEW_TASK_TITLE("com.example.yeshasprabhakar.todo:id/alertTitle"),
    NEW_TASK_INPUT("com.example.yeshasprabhakar.todo:id/edit_title"),
    NEW_TASK_DONE("android:id/button1"),
    NEW_TASK_CANCEL("android:id/button2"),

    TOAST_MESSAGE("//(android.widget.Toast)[1]");

    private String locator;

    ToDoLocators(final String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return locator;
    }
}
