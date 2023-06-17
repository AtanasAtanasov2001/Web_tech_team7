package com.chess.chess.invetory.customer.service;

import com.chess.chess.invetory.customer.Customer;
import com.chess.chess.invetory.customer.repository.CustomerDetailsRequest;
import com.chess.chess.invetory.customer.repository.CustomerRepository;
import com.chess.chess.invetory.customer.repository.CustomerRequest;
import com.chess.chess.security.Hashing;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
public class CustomerService
{

	private final CustomerCacheService customerCacheService;
	private final CustomerRepository customerRepository;

	private final Logger logger = Logger.getLogger(CustomerService.class.getName());

	public CustomerService(CustomerCacheService customerCacheService,
						   CustomerRepository customerRepository)
	{
		this.customerCacheService = customerCacheService;
		this.customerRepository = customerRepository;
	}

	public Long createCustomer(final CustomerRequest customerRequest, final CustomerDetailsRequest customerDetailsRequest)
	{
		final String passwordHash = Hashing.generateStoringPasswordHash(customerRequest.password());

		final Long customerId = customerRepository.insertCustomer(customerRequest, passwordHash, LocalDateTime.now());

		customerRepository.insertCustomerDetails(customerDetailsRequest, customerId);

		final Customer customer = customerRepository.getCustomer(customerId).orElseThrow();

		customerCacheService.reloadCustomer(customer);

		logger.info("Customer created: " + customer);

		return customerId;
	}

	public Customer getById(final Long customerId)
	{
		return customerCacheService.getCustomer(customerId);
	}

	public User getUser(String username, String password)
	{
		final String passwordHash = Hashing.generateStoringPasswordHash(password);
		return customerRepository.getUser(username, passwordHash).orElseThrow();
	}
}

