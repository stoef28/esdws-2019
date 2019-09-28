package com.zihler.domain;

public class Authors {
    private String authors;

    private Authors(String authors) {
        this.authors = authors;
    }

    public static Authors from(String authors) {
        return new Authors(authors);
    }

    @Override
    public String toString() {
        return authors;
    }
}
