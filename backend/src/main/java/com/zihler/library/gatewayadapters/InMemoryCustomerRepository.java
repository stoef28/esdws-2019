package com.zihler.library.gatewayadapters;

import com.zihler.library.entities.Customer;

import java.util.Map;

public class InMemoryCustomerRepository {
    private final Map<String, Customer> customers;

    public InMemoryCustomerRepository() {
        customers = Map.of(
                "AnyUser", new Customer("anyUser")
        );
    }

    public Customer findByUsername(String username) {
        return customers.getOrDefault(username, new Customer(username));
    }
}
