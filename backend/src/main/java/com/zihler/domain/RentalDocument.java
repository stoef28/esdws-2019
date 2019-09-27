package com.zihler.domain;

public class RentalDocument {
    private String bookTitle;
    private String bookAuthors;
    private int daysRented;
    private double amount;

    RentalDocument(String bookTitle, String bookAuthors, int daysRented, double amount) {
        this.bookTitle = bookTitle;
        this.bookAuthors = bookAuthors;
        this.daysRented = daysRented;
        this.amount = amount;
    }

    public String getBookTitle() {
        return this.bookTitle;
    }

    public String getBookAuthors() {
        return bookAuthors;
    }

    public int getDaysRented() {
        return this.daysRented;
    }

    public double getAmount() {
        return amount;
    }
}
