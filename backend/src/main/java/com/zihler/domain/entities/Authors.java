package com.zihler.domain.entities;

public class Authors {
    private String authors;

    public Authors(String authors) {

        this.authors = authors;
    }

    public static Authors from(String authors) {
        return new Authors(authors);
    }
}
