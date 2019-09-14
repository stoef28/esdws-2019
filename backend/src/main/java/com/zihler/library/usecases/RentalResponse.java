package com.zihler.library.usecases;

import com.zihler.library.entities.Rental;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class RentalResponse {
    private double amount;
    private String bookAuthors;
    private String bookTitle;
    private int daysRented;

    public static RentalResponse from(Rental rental) {
        RentalResponse rentalResponse = new RentalResponse();
        rentalResponse.amount = rental.getAmount();
        rentalResponse.bookAuthors = rental.getBookAuthors();
        rentalResponse.bookTitle = rental.getBookTitle();
        rentalResponse.daysRented = rental.getDaysRented();
        return rentalResponse;
    }

    static List<RentalResponse> toRentalResponses(List<Rental> rentals) {
        return rentals.stream().map(RentalResponse::from).collect(toList());
    }

    public double getAmount() {
        return amount;
    }

    public String getBookAuthors() {
        return bookAuthors;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public int getDaysRented() {
        return daysRented;
    }
}
