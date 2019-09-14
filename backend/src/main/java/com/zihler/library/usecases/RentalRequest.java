package com.zihler.library.usecases;

import java.util.List;

public class RentalRequest {
    private final List<String> booksToRent;
    private final String username;

    public RentalRequest(List<String> booksToRent, String username) {
        this.booksToRent = booksToRent;
        this.username = username;
    }

    public List<String> getBooksToRent() {
        return booksToRent;
    }

    public String getUsername() {
        return username;
    }
}
