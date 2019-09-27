package com.zihler.domain;

public class CustomerName {
    private String customerName;

    private CustomerName(String customerName) {
        this.customerName = customerName;
    }

    public static CustomerName from(String customerName) {
        return new CustomerName(customerName);
    }

    public String get() {
        return customerName;
    }
}
