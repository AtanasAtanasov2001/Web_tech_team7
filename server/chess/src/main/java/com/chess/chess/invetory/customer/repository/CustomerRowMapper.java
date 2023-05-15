package com.chess.chess.invetory.customer.repository;

import com.chess.chess.invetory.customer.Customer;
import com.chess.chess.invetory.customer.CustomerDetails;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CustomerRowMapper implements RowMapper<Customer>
{
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        final CustomerDetails details = new CustomerDetails(
                rs.getLong("customer_id"),
                rs.getString("name"),
                rs.getString("last_name"),
                rs.getTimestamp("birth_date").toLocalDateTime().toLocalDate(),
                rs.getString("city")
        );

        return new Customer(
                rs.getLong("customer_id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getTimestamp("registration_date").toLocalDateTime(),
                details
        );
    }
}
