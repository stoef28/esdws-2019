package com.zihler.domain.entities;

import com.zihler.domain.*;

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

    public static Book from(BookKey key, Title title, Authors authors, ReadingMode readingMode, Link link) {
        return new Book(key, title, authors, readingMode, link);
    }

    public BookKey key() {
        return key;
    }

    public Title title() {
        return title;
    }

    public Authors authors() {
        return authors;
    }

    public ReadingMode readingMode() {
        return readingMode;
    }

    public Link link() {
        return link;
    }


}
