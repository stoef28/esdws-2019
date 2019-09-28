package com.zihler.domain;

import com.zihler.domain.entities.Book;

import static com.zihler.domain.FrequentRenterPoints.*;
import static com.zihler.domain.ReadingMode.BOTH;

public class Rental {
    private final Book book;
    private final DaysRented daysRented;

    private Rental(Book book, DaysRented daysRented) {
        this.book = book;
        this.daysRented = daysRented;
    }

    public static Rental from(Book book, DaysRented daysRented) {
        return new Rental(book, daysRented);
    }

    DaysRented daysRented() {
        return daysRented;
    }

    Amount amount() {
        Amount thisAmount = Amount.zero();

        switch (book.readingMode()) {
            case IMAGE:
                thisAmount.add(Amount.of(2));

                if (daysRented().asInt() > 2) {
                    Amount amount = Amount.of((daysRented().asDouble() - 2) * 1.5);
                    thisAmount.add(amount);
                }

                break;
            case TEXT:
                thisAmount.add(Amount.of(1.5));

                if (daysRented().asInt() > 3) {
                    thisAmount.add(Amount.of((daysRented().asDouble() - 3) * 1.5));
                }
                break;
            case BOTH:
                thisAmount.add(Amount.of(daysRented().asDouble() * 3));
                break;
        }
        return thisAmount;
    }

    FrequentRenterPoints frequentRenterPoints() {
        FrequentRenterPoints points = one();
        // add bonus for a reading mode "both"
        if (book.readingMode().equals(BOTH) && daysRented().asInt() > 1) {
            points.add(one());
        }
        return points;
    }

    Title bookTitle() {
        return book.title();
    }

    Authors bookAuthors() {
        return book.authors();
    }
}
