package com.zihler.library.adapters.in_memory_persistence;

import com.zihler.library.domain.entities.Customer;
import com.zihler.library.application.outbound_ports.persistence.CustomerRepository;

import java.util.Map;

public class InMemoryCustomerRepository implements CustomerRepository {
    private final Map<String, Customer> customers;

    public InMemoryCustomerRepository() {
        customers = Map.of(
                "AnyUser", new Customer("anyUser")
        );
    }

    @Override
    public Customer findByUsername(String username) {
        return customers.getOrDefault(username, new Customer(username));
    }
}