package com.zihler.library.gateways;

import com.zihler.library.entities.Customer;

public interface CustomerRepository {
    Customer findByUsername(String username);
}
