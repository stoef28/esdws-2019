package com.zihler.library;

public interface CustomerRepository {
    Customer findByUsername(String username);
}
