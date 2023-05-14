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
    public Long test(@RequestParam String username, @RequestParam String email, @RequestParam String password)
    {
        return customerService.createCustomer(new CustomerRequest(username, email, password), new CustomerDetailsRequest("test", "test", LocalDate.now(), "test"));

    }
}
