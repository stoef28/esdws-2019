package com.zihler.library;

import java.util.List;

class RentalsRequest {
    private final String customerName;
    private final List<String> rentalRequests;

    RentalsRequest(String customerName, List<String> rentalRequests) {
        this.customerName = customerName;
        this.rentalRequests = rentalRequests;
    }

    String getCustomerName() {
        return customerName;
    }

    List<String> getRentalRequests() {
        return rentalRequests;
    }
}
