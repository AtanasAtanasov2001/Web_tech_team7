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
    final Map<Long, Customer> customerById;

    final Map<String, Customer> customerByUsername;

    final CustomerRepository repository;

    final Logger logger = Logger.getLogger(CustomerCacheService.class.getName());

    public CustomerCacheService(CustomerRepository repository)
    {
        this.repository = repository;
        this.customerByUsername = new ConcurrentHashMap<>();
        this.customerById = new ConcurrentHashMap<>();


        loadCustomers();
    }


    public Customer getCustomer(final Long customerId)
    {
        return customerById.computeIfAbsent(customerId, id -> repository.getCustomer(id).orElseThrow());
    }

    public Customer getCustomer(final String username)
    {
        return customerByUsername.computeIfAbsent(username, name -> repository.getCustomer(name).orElseThrow());
    }

    public void reloadCustomer(final Customer customer)
    {
        customerById.put(customer.getId(), customer);
        customerByUsername.put(customer.getUsername(), customer);
    }

    public void deleteCustomer(final Long customerId)
    {
        final Customer customer = customerById.get(customerId);
        customerById.remove(customerId);
        customerByUsername.remove(customer.getUsername());
    }

    public void deleteCustomer(final String username)
    {
        final Customer customer = customerByUsername.get(username);
        customerById.remove(customer.getId());
        customerByUsername.remove(username);
    }

    private void loadCustomers()
    {
        repository.getAllCustomers().forEach(customer -> {
            customerById.put(customer.getId(), customer);
            customerByUsername.put(customer.getUsername(), customer);
        });


        logger.info("Loaded " + customerById.size() + " customers");
    }

}
