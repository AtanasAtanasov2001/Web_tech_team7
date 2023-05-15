package com.chess.chess.security;

import com.chess.chess.invetory.customer.service.CustomerService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class LoginCustomerDetails implements UserDetailsService
{

    private final CustomerService repository;

    public LoginCustomerDetails(CustomerService repository)
    {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {

        return repository.getUser(username, username);
    }
}