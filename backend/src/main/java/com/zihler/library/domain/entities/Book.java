package com.zihler.library.domain.entities;

public class Book {
    private final String id;
    private final String title;
    private final String authors;
    private final String readingMode;
    private final String thumbnailLink;

    public Book(String id, String title, String authors, String readingMode, String thumbnailLink) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.readingMode = readingMode;
        this.thumbnailLink = thumbnailLink;
    }

    public String getReadingMode() {
        return readingMode;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }
}
