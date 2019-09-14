package com.zihler.library.usecases;

import com.zihler.library.entities.Book;

public class BookResponse {
    private int key;
    private String authors;
    private String title;
    private String readingMode;
    private String link;

    public static BookResponse from(Book book) {
        BookResponse bookResponse = new BookResponse();

        bookResponse.authors = book.getAuthors();
        bookResponse.link = book.getLink();
        bookResponse.readingMode = book.getReadingMode();
        bookResponse.title = book.getTitle();
        bookResponse.key = book.getKey();

        return bookResponse;
    }

    public int getKey() {
        return key;
    }

    public String getAuthors() {
        return authors;
    }

    public String getTitle() {
        return title;
    }

    public String getReadingMode() {
        return readingMode;
    }

    public String getLink() {
        return link;
    }
}
