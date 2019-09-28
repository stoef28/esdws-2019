package com.zihler.application.use_cases.rent_books.ports;

import com.zihler.domain.BookKey;
import com.zihler.domain.DaysRented;

public class RentalRequest {
    private final BookKey bookKey;
    private final DaysRented daysRented;

    public RentalRequest(BookKey bookKey, DaysRented daysRented) {
        this.bookKey = bookKey;
        this.daysRented = daysRented;
    }

    public BookKey getBookKey() {
        return bookKey;
    }

    public DaysRented getDaysRented() {
        return daysRented;
    }
}
