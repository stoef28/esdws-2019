package com.zihler.domain.entities;

import com.zihler.domain.CustomerName;

public class Customer {
    private CustomerName customerName;

    public Customer(CustomerName customerName) {
        this.customerName = customerName;
    }

    public CustomerName getCustomerName() {
        return customerName;
    }
}
