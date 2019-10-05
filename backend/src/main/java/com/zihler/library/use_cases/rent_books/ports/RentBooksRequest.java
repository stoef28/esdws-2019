package com.zihler.library.use_cases.rent_books.ports;

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

    public String getCustomerName() {
        return customerName;
    }

    public List<RentBookRequest> getRentBookRequests() {
        return rentBookRequests;
    }
}
