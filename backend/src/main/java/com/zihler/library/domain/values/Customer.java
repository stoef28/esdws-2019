package com.zihler.library.domain.values;

public class Customer {
    private CustomerName name;

    private Customer(CustomerName name) {
        this.name = name;
    }

    public static Customer from(CustomerName name) {
        return new Customer(name);
    }

    public CustomerName name() {
        return name;
    }
}
