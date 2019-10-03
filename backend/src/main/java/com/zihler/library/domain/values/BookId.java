package com.zihler.library.domain.values;

public class BookId {
    private int bookId;

    private BookId(int bookId) {
        this.bookId = bookId;
    }

    public static BookId from(String id) {
        return new BookId(Integer.parseInt(id));
    }


    @Override
    public String toString() {
        return String.valueOf(bookId);
    }

    public int asInt() {
        return bookId;
    }


}
