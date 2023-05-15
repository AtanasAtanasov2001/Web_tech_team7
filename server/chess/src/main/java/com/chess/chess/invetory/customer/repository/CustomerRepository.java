package com.chess.chess.invetory.customer.repository;

import com.chess.chess.invetory.customer.Customer;
import com.chess.chess.invetory.customer.CustomerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.*;

@Repository
@DependsOn({"flyway", "flywayInitializer"})
public class CustomerRepository extends NamedParameterJdbcDaoSupport
{
    private final CustomerRowMapper customerRowMapper;

    @Autowired
    public CustomerRepository(DataSource dataSource, CustomerRowMapper customerRowMapper)
    {
        this.customerRowMapper = customerRowMapper;
        setDataSource(dataSource);
    }

    public Long insertCustomer(final CustomerRequest request, final String passwordHash, final LocalDateTime registrationTime)
    {
        final String sql = """
                INSERT INTO customer (username, email, password, registration_date) VALUE (:username, :email, :password, :registration_date);
                 """;

        final SqlParameterSource params = new MapSqlParameterSource()
                .addValue("username", request.username())
                .addValue("email", request.email())
                .addValue("password", passwordHash)
                .addValue("registration_date", registrationTime);

        final KeyHolder keyHolder = new GeneratedKeyHolder();
        Objects.requireNonNull(getNamedParameterJdbcTemplate()).update(sql, params, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void insertCustomerDetails(final CustomerDetailsRequest request, final Long customerId)
    {
        final String sql = """
                INSERT INTO  customer_details (customer_id, name, last_name, birth_date, city) VALUES (:customer_id, :name, :last_name, :birth_date, :city );
                 """;

        final SqlParameterSource params = new MapSqlParameterSource()
                .addValue("customer_id", customerId)
                .addValue("name", request.name())
                .addValue("last_name", request.lastName())
                .addValue("birth_date", request.birthDate())
                .addValue("city", request.city());

        Objects.requireNonNull(getNamedParameterJdbcTemplate()).update(sql, params);

    }

    //todo fetch customer

    public Optional<Customer> getCustomer(Long id)
    {
        final String sql = """
                SELECT c.id AS customer_id,
                       username,
                       email,
                       registration_date,
                       name,
                       last_name,
                       birth_date,
                       city
                FROM customer c
                         JOIN customer_details cd ON c.id = cd.customer_id
                WHERE c.id = :id;
                """;

        final Map<String, Object> params = Map.of("id", id);

        return Optional.ofNullable(Objects.requireNonNull(getNamedParameterJdbcTemplate()).queryForObject(sql, params, customerRowMapper));
    }


    public List<Customer> getAllCustomers()
    {
        final String sql = """
                SELECT c.id AS customer_id,
                       username,
                       email,
                       registration_date,
                       name,
                       last_name,
                       birth_date,
                       city
                FROM customer c
                         JOIN customer_details cd ON c.id = cd.customer_id;
                """;

        final List<Customer> result = new ArrayList<>();

        Objects.requireNonNull(getNamedParameterJdbcTemplate()).query(sql, Collections.emptyMap(), (rs, rowNum) -> {
            final CustomerDetails details = new CustomerDetails(
                    rs.getLong("customer_id"),
                    rs.getString("name"),
                    rs.getString("last_name"),
                    rs.getTimestamp("birth_date").toLocalDateTime().toLocalDate(),
                    rs.getString("city")
            );

            result.add(new Customer(
                    rs.getLong("customer_id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getTimestamp("registration_date").toLocalDateTime(),
                    details
            ));
            return null;
        });

        return result;
    }

    public Optional<User> getUser(String username, String password)
    {
        final String sql = """
                SELECT username, password FROM customer WHERE username = :username AND password = :password;
                """;

        final Map<String, Object> params = Map.of("username", username, "password", password);

        return Optional.ofNullable(Objects.requireNonNull(getNamedParameterJdbcTemplate()).queryForObject(sql, params, (rs, rowNum) -> new User(rs.getString("username"), rs.getString("password"), Collections.emptyList())));
    }
}
