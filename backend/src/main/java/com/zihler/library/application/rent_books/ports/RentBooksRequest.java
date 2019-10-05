package com.zihler.library.application.rent_books.ports;

import java.util.List;

public class RentBooksRequest {
    private final String customerName;
    private final List<RentBookRequest> rentBookRequests;

    private RentBooksRequest(String customerName, List<RentBookRequest> rentBookRequests) {
        this.customerName = customerName;
        this.rentBookRequests = rentBookRequests;
    }

    public static RentBooksRequest from(String customerName, List<RentBookRequest> rentBookRequests) {
        return new RentBooksRequest(customerName, rentBookRequests);
    }

    String getCustomerName() {
        return customerName;
    }

    List<RentBookRequest> getRentBookRequests() {
        return rentBookRequests;
    }
}
