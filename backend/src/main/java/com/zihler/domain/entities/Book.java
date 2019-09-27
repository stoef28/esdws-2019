package com.zihler.domain.entities;

import com.zihler.domain.BookKey;

public class Book {
    private final BookKey key;
    private final Title title;
    private final Authors authors;
    private final ReadingMode readingMode;
    private final Link link;

    private Book(BookKey bookKey, Title title, Authors authors, ReadingMode readingMode, Link link) {
        this.key = bookKey;
        this.title = title;
        this.authors = authors;
        this.readingMode = readingMode;
        this.link = link;
    }

    public static Book from(String[] bookData) {
        return new Book(
                BookKey.from(Integer.parseInt(bookData[0])),
                Title.from(bookData[1]),
                Authors.from(bookData[2]),
                ReadingMode.valueOf(bookData[3]),
                Link.from(bookData[4])
        );
    }

    public int getKey() {
        return key.toInt();
    }

    public String getTitle() {
        return title.toString();
    }

    public String getAuthors() {
        return authors.toString();
    }

    public String getReadingMode() {
        return readingMode.toString();
    }

    public String getLink() {
        return link.toString();
    }
}
