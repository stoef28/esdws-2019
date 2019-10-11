package com.zihler.library.domain.entities;

public class Book {
    private final int id;
    private final String title;
    private final String thumbnailLink;
    private final String authors;
    private final ReadingMode readingMode;

    public Book(int id, String title, String authors, ReadingMode readingMode, String thumbnailLink) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.readingMode = readingMode;
        this.thumbnailLink = thumbnailLink;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnailLink() {
        return thumbnailLink;
    }

    public ReadingMode getReadingMode() {
        return readingMode;
    }

    public String getAuthors() {
        return authors;
    }
}
