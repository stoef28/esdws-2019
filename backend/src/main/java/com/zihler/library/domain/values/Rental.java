package com.zihler.library.domain.values;

import com.zihler.library.domain.entities.Book;

import static com.zihler.library.domain.values.ReadingMode.BOTH;

public class Rental {
    private final Book book;
    private final DaysRented daysRented;

    public Rental(Book book, DaysRented daysRented) {
        this.book = book;
        this.daysRented = daysRented;
    }

    public DaysRented daysRented() {
        return daysRented;
    }

    public Authors bookAuthors() {
        return book.authors();
    }

    public Title bookTitle() {
        return book.title();
    }

    public FrequentRenterPoints frequentRenterPoints() {
        // add bonus for a reading mode "both"
        if (book.readingMode().equals(BOTH) && daysRented.asInt() > 1) {
            return FrequentRenterPoints.of(2);
        }
        return FrequentRenterPoints.of(1);
    }

    public Amount amount() {
        Amount amount = Amount.of(0);
        switch (book.readingMode()) {
            case IMAGE:
                amount.plus(Amount.of(2));
                if (daysRented.asInt() > 2) {
                    amount.plus(Amount.of((daysRented.asInt() - 2) * 1.5));
                }
                break;
            case TEXT:
                amount.plus(Amount.of(1.5));
                if (daysRented.asInt() > 3) {
                    amount.plus(Amount.of((daysRented.asInt() - 3) * 1.5));
                }
                break;
            case BOTH:
                amount.plus(Amount.of(daysRented.asInt() * 3));
                break;
        }
        return amount;
    }
}
