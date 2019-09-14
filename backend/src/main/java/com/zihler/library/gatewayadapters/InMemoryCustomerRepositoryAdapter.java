package com.zihler.library.gatewayadapters;

import com.zihler.library.entities.Customer;
import com.zihler.library.gateways.CustomerRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryCustomerRepositoryAdapter implements CustomerRepository {

    private InMemoryCustomerRepository inMemoryCustomerRepository = new InMemoryCustomerRepository();

    @Override
    public Customer findByUsername(String username) {
        return inMemoryCustomerRepository.findByUsername(username);
    }


}
