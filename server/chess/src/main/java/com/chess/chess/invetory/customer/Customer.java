package com.chess.chess.invetory.customer;

import java.time.LocalDateTime;

public class Customer {
    private Long id;
    private String username;
    private String email;
    private LocalDateTime registrationDate;
    private CustomerDetails customerDetails;

    public Customer(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public Customer(Long id, String username, String email, LocalDateTime registrationDate, CustomerDetails customerDetails)
    {
        this.id = id;
        this.username = username;
        this.email = email;
        this.registrationDate = registrationDate;
        this.customerDetails = customerDetails;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public LocalDateTime getRegistrationDate()
    {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate)
    {
        this.registrationDate = registrationDate;
    }

    public CustomerDetails getCustomerDetails()
    {
        return customerDetails;
    }

    public void setCustomerDetails(CustomerDetails customerDetails)
    {
        this.customerDetails = customerDetails;
    }

    @Override
    public String toString()
    {
        return "Customer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", registrationDate=" + registrationDate +
                ", customerDetails=" + customerDetails.toString() +
                '}';
    }
}
