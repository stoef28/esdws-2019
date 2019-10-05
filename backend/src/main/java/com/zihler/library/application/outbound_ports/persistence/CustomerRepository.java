package com.zihler.library.application.outbound_ports.persistence;

import com.zihler.library.domain.entities.Customer;

public interface CustomerRepository {
    Customer findByUsername(String username);
}
