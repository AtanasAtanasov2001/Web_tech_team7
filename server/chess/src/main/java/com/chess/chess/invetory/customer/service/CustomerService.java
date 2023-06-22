package com.chess.chess.invetory.customer.service;

import com.chess.chess.invetory.customer.Customer;
import com.chess.chess.invetory.customer.repository.CustomerDetailsRequest;
import com.chess.chess.invetory.customer.repository.CustomerRepository;
import com.chess.chess.invetory.customer.repository.CustomerRequest;
import com.chess.chess.security.Hashing;
import com.chess.chess.security.JWTUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
public class CustomerService
{

	private final CustomerCacheService customerCacheService;
	private final CustomerRepository customerRepository;
	private final JWTUtils jwtUtils;

	private final Logger logger = Logger.getLogger(CustomerService.class.getName());

	public CustomerService(CustomerCacheService customerCacheService,
						   CustomerRepository customerRepository,
						   JWTUtils jwtUtils)
	{
		this.customerCacheService = customerCacheService;
		this.customerRepository = customerRepository;
		this.jwtUtils = jwtUtils;
	}

	public Long createCustomer(final CustomerRequest customerRequest, final CustomerDetailsRequest customerDetailsRequest)
	{
		final String passwordHash = Hashing.generateStoringPasswordHash(customerRequest.password());

		final Long customerId = customerRepository.insertCustomer(customerRequest, passwordHash, LocalDateTime.now());

		customerRepository.insertCustomerDetails(customerDetailsRequest, customerId);

		final Customer customer = customerRepository.getCustomer(customerId).orElseThrow();

		customerCacheService.reloadCustomer(customer);

		logger.info(customerRequest.password() +"   Customer created: " + customer);

		return customerId;
	}

	public Customer getById(final Long customerId)
	{
		return customerCacheService.getCustomer(customerId);
	}

	public Customer getByUsername(final String username)
	{
		return customerCacheService.getCustomer(username);
	}

	public User getUser(String username)
	{
		User user = customerRepository.getUser(username).orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
		logger.info("User found: " + user.getUsername());
		return user;
	}

	public Customer getByToken(String token)
	{
		String username = jwtUtils.extractUsername(token);
		return customerCacheService.getCustomer(username);
	}
}

