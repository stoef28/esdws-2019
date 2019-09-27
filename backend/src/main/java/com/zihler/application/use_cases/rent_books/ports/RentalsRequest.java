package com.zihler.application.use_cases.rent_books.ports;

import com.zihler.domain.CustomerName;
import com.zihler.domain.RentalsDocument;

import java.util.List;

public class RentalsRequest {
    private final CustomerName customerName;
    private final List<RentalsDocument> rentalRequests;

    public RentalsRequest(CustomerName customerName, List<RentalsDocument> rentalRequests) {
        this.customerName = customerName;
        this.rentalRequests = rentalRequests;
    }


    public CustomerName getCustomerName() {
        return customerName;
    }

    public List<RentalsDocument> getRentalRequests() {
        return rentalRequests;
    }
}
