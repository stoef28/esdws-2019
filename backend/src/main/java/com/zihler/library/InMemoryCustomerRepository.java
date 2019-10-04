package com.zihler.library;

import com.zihler.library.domain.entities.Customer;
import com.zihler.library.domain.values.CustomerName;

import java.util.Map;

public class InMemoryCustomerRepository {
    private final Map<CustomerName, Customer> customers;

    public InMemoryCustomerRepository() {
        customers = Map.of(
                CustomerName.from("anyUser"), Customer.from(CustomerName.from("anyUser"))
        );
    }

    public Customer findByUsername(CustomerName customerName) {
        return customers.getOrDefault(customerName, Customer.from(customerName));
    }
}
