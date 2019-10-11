package com.zihler.library.domain.values;

import com.zihler.library.domain.entities.Book;
import com.zihler.library.domain.entities.ReadingMode;

public class Rental {
    private final Book book;
    private final int daysRented;

    public Rental(Book book, int daysRented) {
        this.book = book;
        this.daysRented = daysRented;
    }

    private Book getBook() {
        return book;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public double getAmount() {
        double amount = 0;
        switch (getBook().getReadingMode()) {
            case IMAGE:
                amount += 2;
                if (getDaysRented() > 2)
                    amount += (getDaysRented() - 2) * 1.5;
                break;
            case TEXT:
                amount += 1.5;
                if (getDaysRented() > 3)
                    amount += (getDaysRented() - 3) * 1.5;
                break;
            case BOTH:
                amount += getDaysRented() * 3;
            break;
        }
        return amount;
    }

    public int getFrequentRenterPoints() {
        // add bonus for a reading mode "both"
        if (getBook().getReadingMode().equals(ReadingMode.BOTH) && getDaysRented() > 1) {
            return 2;
        } else {
            return 1;
        }
    }


    public String getBookTitle() {
        return getBook().getTitle();
    }

    public String getBookAuthors() {
        return getBook().getAuthors();
    }
}
