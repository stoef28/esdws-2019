package com.zihler.library.domain.values;

public class CustomerName {
    private String name;

    public CustomerName(String name) {
        this.name = name;
    }

    public static CustomerName from(String name) {
        return new CustomerName(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
