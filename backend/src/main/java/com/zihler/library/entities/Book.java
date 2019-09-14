package com.zihler.library.entities;

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

    public static Book from(String bookLine) {
        String[] values = bookLine.split(";");
        return new Book(
                Integer.parseInt(values[0]),
                values[1],
                values[2],
                values[3],
                values[4]
        );
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

    public String[] toStringArray() {
        return new String[]{
                getKey() + "",
                getTitle(),
                getAuthors(),
                getReadingMode(),
                getLink()
        };
    }
}
