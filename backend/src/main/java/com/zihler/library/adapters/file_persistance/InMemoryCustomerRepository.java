package com.zihler.library.adapters.file_persistance;

import com.zihler.library.application.outbound_ports.persistance.IFindCustomers;
import com.zihler.library.domain.entities.Customer;
import com.zihler.library.domain.values.CustomerName;

import java.util.Map;

public class InMemoryCustomerRepository implements IFindCustomers {
    private final Map<CustomerName, Customer> customers;

    public InMemoryCustomerRepository() {
        customers = Map.of(
                CustomerName.from("anyUser"), Customer.from(CustomerName.from("anyUser"))
        );
    }

    @Override
    public Customer byName(CustomerName customerName) {
        return customers.getOrDefault(customerName, Customer.from(customerName));
    }
}
