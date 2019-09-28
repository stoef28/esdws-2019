package com.zihler.domain;

public class Title {
    private String title;

    private Title(String title) {
        this.title = title;
    }

    public static Title from(String title) {
        return new Title(title);
    }

    @Override
    public String toString() {
        return title;
    }
}
