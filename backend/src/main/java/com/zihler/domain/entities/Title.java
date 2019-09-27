package com.zihler.domain.entities;

public class Title {
    private String title;

    public Title(String title) {

        this.title = title;
    }

    public static Title from(String title) {
        return new Title(title);
    }
}
