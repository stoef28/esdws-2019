package com.zihler.domain.entities;

import com.zihler.domain.BookKey;
import com.zihler.domain.Link;
import com.zihler.domain.ReadingMode;
import com.zihler.domain.Title;

public class Book {
    public final BookKey key;
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


    public BookKey getKey() {
        return key;
    }

    public Title getTitle() {
        return title;
    }

    public Authors getAuthors() {
        return authors;
    }

    public ReadingMode getReadingMode() {
        return readingMode;
    }

    public Link getLink() {
        return link;
    }

}
