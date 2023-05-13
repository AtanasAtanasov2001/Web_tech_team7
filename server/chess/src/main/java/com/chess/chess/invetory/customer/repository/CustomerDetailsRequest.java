package com.chess.chess.invetory.customer.repository;

import java.time.LocalDate;

public record CustomerDetailsRequest(String name, String lastName, LocalDate birthDate, String city)
{
    public CustomerDetailsRequest(String name, String lastName, LocalDate birthDate, String city)
    {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.city = city;
    }

}
