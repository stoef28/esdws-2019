package com.zihler.library.use_cases.rent_books.ports;

import com.zihler.library.domain.values.CustomerName;

import java.util.List;

public class RentBooksRequest {
    private final CustomerName customerName;
    private final List<RentBookRequest> rentBookRequests;

    private RentBooksRequest(CustomerName customerName, List<RentBookRequest> rentBookRequests) {
        this.customerName = customerName;
        this.rentBookRequests = rentBookRequests;
    }

    public static RentBooksRequest from(CustomerName customerName, List<RentBookRequest> rentBookRequests) {
        return new RentBooksRequest(customerName, rentBookRequests);
    }

    public CustomerName getCustomerName() {
        return customerName;
    }

    public List<RentBookRequest> getRentBookRequests() {
        return rentBookRequests;
    }
}
