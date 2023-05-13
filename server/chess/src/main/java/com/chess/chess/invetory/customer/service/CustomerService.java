package com.chess.chess.invetory.customer.service;

import com.chess.chess.invetory.customer.Customer;
import com.chess.chess.invetory.customer.repository.CustomerDetailsRequest;
import com.chess.chess.invetory.customer.repository.CustomerRepository;
import com.chess.chess.invetory.customer.repository.CustomerRequest;
import com.chess.chess.security.Hashing;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

@Service
public class CustomerService
{
    private final CustomerCacheService customerCacheService;
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerCacheService customerCacheService,
                           CustomerRepository customerRepository)
    {
        this.customerCacheService = customerCacheService;
        this.customerRepository = customerRepository;
    }


    @Transactional
    public void createCustomer(final CustomerRequest customerRequest, final CustomerDetailsRequest customerDetailsRequest) throws NoSuchAlgorithmException
    {
        final String passwordHash = Hashing.generateStoringPasswordHash(customerRequest.password());

        final Long customerId = customerRepository.insertCustomer(customerRequest, passwordHash, null);

        customerRepository.insertCustomerDetails(customerDetailsRequest, customerId);

        final Customer customer = customerRepository.getCustomer(customerId).orElseThrow();

        customerCacheService.reloadCustomer(customer);

    }
}
