package com.chess.chess.invetory.customer;

import java.time.LocalDate;

public class CustomerDetails
{
    private Long customerId;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String city;

    public CustomerDetails(Long customerId, String name, String lastName, LocalDate birthDate, String city)
    {
        this.customerId = customerId;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.city = city;
    }

    public Long getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(Long customerId)
    {
        this.customerId = customerId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate)
    {
        this.birthDate = birthDate;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    @Override
    public String toString()
    {
        return "CustomerDetails{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", city='" + city + '\'' +
                '}';
    }
}
