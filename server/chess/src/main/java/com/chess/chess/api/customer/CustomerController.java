package com.chess.chess.api.customer;

import com.chess.chess.invetory.customer.Customer;
import com.chess.chess.invetory.customer.service.CustomerService;
import com.chess.chess.security.Token;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{username}")
    public Customer getByUsername(@PathVariable("username") String username)
    {
        return customerService.getByUsername(username);
    }

    @PostMapping("/token")
    public Customer getByToken(@RequestBody Token token)
    {
        return customerService.getByToken(token.token);

    }
}
