package com.zihler.domain;

public class RentalDocument {
    private Title bookTitle;
    private Authors bookAuthors;
    private DaysRented daysRented;
    private Amount amount;

    private RentalDocument(Title bookTitle, Authors bookAuthors, DaysRented daysRented, Amount amount) {
        this.bookTitle = bookTitle;
        this.bookAuthors = bookAuthors;
        this.daysRented = daysRented;
        this.amount = amount;
    }

    public static RentalDocument with(Title bookTitle, Authors bookAuthors, DaysRented daysRented, Amount amount) {
        return new RentalDocument(bookTitle, bookAuthors, daysRented, amount);
    }

    public Title getBookTitle() {
        return this.bookTitle;
    }

    public Authors getBookAuthors() {
        return bookAuthors;
    }

    public DaysRented getDaysRented() {
        return this.daysRented;
    }

    public Amount getAmount() {
        return amount;
    }
}
