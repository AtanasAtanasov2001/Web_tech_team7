package com.chess.chess.api.customer;

import com.chess.chess.invetory.customer.Customer;
import com.chess.chess.invetory.customer.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "customer")
public class CustomerController
{

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService)
	{
		this.customerService = customerService;
	}

	@GetMapping("/{id}")
	public Customer getById(@PathVariable("id") Long id)
	{
		return customerService.getById(id);
	}
}
