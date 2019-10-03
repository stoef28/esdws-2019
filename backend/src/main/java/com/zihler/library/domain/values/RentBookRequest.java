package com.zihler.library.domain.values;

public class RentBookRequest {
    private final BookId bookId;
    private final DaysRented daysRented;

    public RentBookRequest(BookId bookId, DaysRented daysRented) {
        this.bookId = bookId;
        this.daysRented = daysRented;
    }

    public BookId getBookId() {
        return bookId;
    }

    public DaysRented getDaysRented() {
        return daysRented;
    }
}
