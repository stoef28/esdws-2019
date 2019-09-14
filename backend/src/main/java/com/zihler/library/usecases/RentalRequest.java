package com.zihler.library.usecases;

import java.util.List;

public class RentalRequest {
    private final List<String> booksToRent;
    private final String username;

    private RentalRequest(List<String> booksToRent, String username) {
        this.booksToRent = booksToRent;
        this.username = username;
    }

    public static RentalRequest from(List<String> booksToRent, String username) {
        return new RentalRequest(booksToRent, username);
    }

    public List<String> getBooksToRent() {
        return booksToRent;
    }

    public String getUsername() {
        return username;
    }
}
