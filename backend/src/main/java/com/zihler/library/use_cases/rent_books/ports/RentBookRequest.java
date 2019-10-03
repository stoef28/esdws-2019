package com.zihler.library.use_cases.rent_books.ports;

import com.zihler.library.domain.values.BookId;
import com.zihler.library.domain.values.DaysRented;

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
