package com.zihler.library.domain.entities;

import com.zihler.library.domain.values.*;

public class Book {
    private final BookId id;
    private final Title title;
    private final Authors authors;
    private final ReadingMode readingMode;
    private final ThumbnailLink thumbnailLink;

    public Book(BookId bookId, Title title, Authors authors, ReadingMode readingMode, ThumbnailLink thumbnailLink) {
        this.id = bookId;
        this.title = title;
        this.authors = authors;
        this.readingMode = readingMode;
        this.thumbnailLink = thumbnailLink;
    }

    public ReadingMode readingMode() {
        return readingMode;
    }

    public Title title() {
        return title;
    }

    public Authors authors() {
        return authors;
    }

    public BookId getId() {
        return id;
    }

    public ThumbnailLink getThumbnailLink() {
        return thumbnailLink;
    }
}
