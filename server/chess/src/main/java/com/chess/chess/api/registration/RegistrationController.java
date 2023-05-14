package com.chess.chess.api.registration;

import com.chess.chess.invetory.customer.repository.CustomerDetailsRequest;
import com.chess.chess.invetory.customer.repository.CustomerRequest;
import com.chess.chess.invetory.customer.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
        return customerService.createCustomer(
                new CustomerRequest(
                        authRequest.username(),
                "unknown", authRequest.password()),
                new CustomerDetailsRequest(
                        "unknown",
                        "unknown",
                        LocalDate.now(), "unknown"));

    }
}
