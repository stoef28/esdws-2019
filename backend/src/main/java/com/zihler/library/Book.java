package com.zihler.library;

public class Book {
    private final int key;
    private final String title;
    private final String authors;
    private final String readingMode;
    private final String link;

    private Book(int key, String title, String authors, String readingMode, String link) {
        this.key = key;
        this.title = title;
        this.authors = authors;
        this.readingMode = readingMode;
        this.link = link;
    }

    static Book from(String line) {
        final String[] bookData = line.split(";");
        return new Book(Integer.parseInt(bookData[0]), bookData[1], bookData[2], bookData[3], bookData[4]);
    }

    public int getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public String getReadingMode() {
        return readingMode;
    }

    public String getLink() {
        return link;
    }
}
