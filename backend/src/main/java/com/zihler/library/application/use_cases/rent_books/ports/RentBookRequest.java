package com.zihler.library.application.use_cases.rent_books.ports;

import com.zihler.library.domain.values.BookId;
import com.zihler.library.domain.values.DaysRented;

public class RentBookRequest {
    private final BookId bookId;
    private final DaysRented daysRented;

    private RentBookRequest(BookId bookId, DaysRented daysRented) {
        this.bookId = bookId;
        this.daysRented = daysRented;
    }

    public static RentBookRequest from(BookId bookId, DaysRented daysRented) {
        return new RentBookRequest(bookId, daysRented);
    }

    BookId getBookId() {
        return bookId;
    }

    DaysRented getDaysRented() {
        return daysRented;
    }
}
