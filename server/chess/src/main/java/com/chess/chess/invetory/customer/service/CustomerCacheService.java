package com.chess.chess.invetory.customer.service;

import com.chess.chess.invetory.customer.Customer;
import com.chess.chess.invetory.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

@Service
public class CustomerCacheService
{
    final Map<Long, Customer> customerCache;

    final CustomerRepository repository;

    final Logger logger = Logger.getLogger(CustomerCacheService.class.getName());

    public CustomerCacheService(CustomerRepository repository)
    {
        this.repository = repository;
        customerCache = new ConcurrentHashMap<>();


        loadCustomers();
    }


    public Customer getCustomer(final Long customerId)
    {
        return customerCache.computeIfAbsent(customerId, id -> repository.getCustomer(id).orElseThrow());
    }

    public void reloadCustomer(final Customer customer)
    {
        customerCache.put(customer.getId(), customer);
    }

    public void deleteCustomer(final Long customerId)
    {
        customerCache.remove(customerId);
    }

    private void loadCustomers()
    {
        repository.getAllCustomers().forEach(customer -> customerCache.put(customer.getId(), customer));

        logger.info("Loaded " + customerCache.size() + " customers");
    }

}
