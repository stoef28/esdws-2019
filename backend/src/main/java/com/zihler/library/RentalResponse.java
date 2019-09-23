package com.zihler.library;

class RentalResponse {
    private String bookTitle;
    private String bookAuthors;
    private int daysRented;
    private double amount;

    RentalResponse(String bookTitle, String bookAuthors, int daysRented, double amount) {
        this.bookTitle = bookTitle;
        this.bookAuthors = bookAuthors;
        this.daysRented = daysRented;
        this.amount = amount;
    }

    String getBookTitle() {
        return this.bookTitle;
    }

    String getBookAuthors() {
        return bookAuthors;
    }

    int getDaysRented() {
        return this.daysRented;
    }

    double getAmount() {
        return amount;
    }
}
