package com.zihler.library.gatewayadapters;

import com.zihler.library.entities.Customer;

public class InMemoryCustomerRepository {
    public Customer findByUsername(String username) {
        return new Customer(username);
    }
}
