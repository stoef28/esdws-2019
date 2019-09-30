package com.zihler.domain;

import com.zihler.domain.entities.Book;

public class Rental {
    private final Book book;
    private final DaysRented daysRented;

    public Rental(Book book, DaysRented daysRented) {
        this.book = book;
        this.daysRented = daysRented;
    }

    private Book getBook() {
        return book;
    }

    DaysRented getDaysRented() {
        return daysRented;
    }

    double getAmount() {
        double thisAmount = 0;
        switch (getBook().getReadingMode().toString()) {
            case "IMAGE":
                thisAmount += 2;
                if (getDaysRented().toInt() > 2)
                    thisAmount += (getDaysRented().toInt() - 2) * 1.5;
                break;
            case "TEXT":
                thisAmount += 1.5;
                if (getDaysRented().toInt() > 3)
                    thisAmount += (getDaysRented().toInt() - 3) * 1.5;
                break;
            case "BOTH":
                thisAmount += getDaysRented().toInt() * 3;
                break;
        }
        return thisAmount;
    }

    int getFrequentRenterPoints() {
        int frequentRenterPoints = 1;

        // add bonus for a reading mode "both"
        if (getBook().getReadingMode().toString().equals("BOTH") && getDaysRented().toInt() > 1) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }

    String getBookTitle() {
        return getBook().getTitle();
    }

    String getBookAuthors() {
        return getBook().getAuthors().toString();
    }
}
