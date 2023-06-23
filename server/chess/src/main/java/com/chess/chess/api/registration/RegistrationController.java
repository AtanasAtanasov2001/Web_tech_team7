package com.chess.chess.api.registration;

import com.chess.chess.invetory.customer.Customer;
import com.chess.chess.invetory.customer.repository.CustomerDetailsRequest;
import com.chess.chess.invetory.customer.repository.CustomerRequest;
import com.chess.chess.invetory.customer.service.CustomerService;
import com.chess.chess.security.Token;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping(path = "registration")
public class RegistrationController
{
    private final CustomerService customerService;

    public RegistrationController(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    @PostMapping
    public Long register(@RequestBody CustomerRequest request,
                         @RequestBody CustomerDetailsRequest detailsRequest)
    {
        return customerService.createCustomer(request, detailsRequest);

    }

    @PostMapping("/skinny")
    public Long unknown(@RequestBody AuthRequest authRequest)
    {
        UUID uuid = UUID.randomUUID();
        return customerService.createCustomer(
                new CustomerRequest(
                        authRequest.username(),
                uuid.toString(), authRequest.password()),
                new CustomerDetailsRequest(
                        "unknown",
                        "unknown",
                        LocalDate.now(), "unknown"));

    }

    @GetMapping("/{username}")
    public Long getByUsername(@PathVariable("username") String username)
    {
        return customerService.getByUsername(username).getId();
    }
}
