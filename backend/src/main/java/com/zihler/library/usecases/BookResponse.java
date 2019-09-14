package com.zihler.library.usecases;

import com.zihler.library.entities.Book;

import java.util.List;

import static java.util.stream.Collectors.toList;

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

    static List<BookResponse> from(List<Book> books) {
        return books.stream()
                .map(BookResponse::from)
                .collect(toList());
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
