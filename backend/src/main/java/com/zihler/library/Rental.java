package com.zihler.library;

class Rental {
    private final Book book;
    private final int daysRented;

    Rental(Book book, int daysRented) {
        this.book = book;
        this.daysRented = daysRented;
    }

    private Book getBook() {
        return book;
    }

    int getDaysRented() {
        return daysRented;
    }

    double getAmount() {
        double thisAmount = 0;
        switch (getBook().getReadingMode()) {
            case "IMAGE":
                thisAmount += 2;
                if (getDaysRented() > 2)
                    thisAmount += (getDaysRented() - 2) * 1.5;
                break;
            case "TEXT":
                thisAmount += 1.5;
                if (getDaysRented() > 3)
                    thisAmount += (getDaysRented() - 3) * 1.5;
                break;
            case "BOTH":
                thisAmount += getDaysRented() * 3;
                break;
        }
        return thisAmount;
    }

    int getFrequentRenterPoints() {
        int frequentRenterPoints = 1;

        // add bonus for a reading mode "both"
        if (getBook().getReadingMode().equals("BOTH") && getDaysRented() > 1) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }

    String getBookTitle() {
        return getBook().getTitle();
    }

    String getBookAuthors() {
        return getBook().getAuthors();
    }
}
