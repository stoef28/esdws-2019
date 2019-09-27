package com.zihler.adapters.in_memory_persistence;

import com.zihler.application.outbound_ports.persistence.CustomerRepository;
import com.zihler.domain.CustomerName;
import com.zihler.domain.entities.Customer;

import java.util.Map;

public class InMemoryCustomerRepository implements CustomerRepository {
    private final Map<CustomerName, Customer> customers;

    public InMemoryCustomerRepository() {
        customers = Map.of(
                CustomerName.from("AnyUser"), new Customer(CustomerName.from("anyUser"))
        );
    }

    @Override
    public Customer findByName(CustomerName customerName) {
        return customers.getOrDefault(customerName, new Customer(customerName));
    }
}
