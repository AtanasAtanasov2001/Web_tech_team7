package com.chess.chess.registration;

import com.chess.chess.invetory.customer.repository.CustomerDetailsRequest;
import com.chess.chess.invetory.customer.repository.CustomerRequest;
import com.chess.chess.invetory.customer.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public String register(@RequestParam CustomerRequest request,
                           @RequestParam CustomerDetailsRequest detailsRequest)
    {
        try {
            customerService.createCustomer(request, detailsRequest);
            return "OK";
        } catch (Exception e) {
            return "FAIL";
        }
    }

    @PostMapping("/test")
    public String test()
    {
        customerService.createCustomer(new CustomerRequest("blago1", "blago1", "blago1"), new CustomerDetailsRequest("test", "test", LocalDate.now(), "test"));
        return "OK";
    }
}
