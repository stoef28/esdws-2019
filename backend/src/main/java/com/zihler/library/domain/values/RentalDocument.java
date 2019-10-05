package com.zihler.library.domain.values;

public class RentalDocument {
    private final Amount amount;
    private final Authors bookAuthors;
    private final Title bookTitle;
    private final DaysRented daysRented;

    private RentalDocument(Amount amount, Authors bookAuthors, Title bookTitle, DaysRented daysRented) {
        this.amount = amount;
        this.bookAuthors = bookAuthors;
        this.bookTitle = bookTitle;
        this.daysRented = daysRented;
    }

    public static RentalDocument from(Rental rental) {
        return new RentalDocument(
                rental.amount(),
                rental.bookAuthors(),
                rental.bookTitle(),
                rental.daysRented()
        );
    }

    public Amount amount() {
        return amount;
    }

    public Authors bookAuthors() {
        return bookAuthors;
    }

    public Title bookTitle() {
        return bookTitle;
    }

    public DaysRented daysRented() {
        return daysRented;
    }
}
