package com.zihler.library.application.outbound_ports.persistance;

import com.zihler.library.domain.entities.Customer;
import com.zihler.library.domain.values.CustomerName;

public interface IFindCustomers {
    Customer byName(CustomerName customerName);
}
