package com.example.yeshasprabhakar.todo.withframework.utils;

public enum ElementAttribute {

    ATTRIBUTE_TEXT("text");

    private String attribute;

    ElementAttribute(final String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}
