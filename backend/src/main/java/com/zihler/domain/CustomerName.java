package com.zihler.domain;

import java.util.Objects;

public class CustomerName {
    private String customerName;

    private CustomerName(String customerName) {
        this.customerName = customerName;
    }

    public static CustomerName from(String customerName) {
        return new CustomerName(customerName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerName that = (CustomerName) o;
        return Objects.equals(customerName, that.customerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerName);
    }


    @Override
    public String toString() {
        return customerName;
    }
}
