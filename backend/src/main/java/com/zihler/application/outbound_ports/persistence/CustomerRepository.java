package com.zihler.application.outbound_ports.persistence;

import com.zihler.domain.CustomerName;
import com.zihler.domain.entities.Customer;

public interface CustomerRepository {
    Customer findByName(CustomerName customerName);
}
