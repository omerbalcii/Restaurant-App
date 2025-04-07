package com.Restaurant.RestaurantApp.Service;

import com.Restaurant.RestaurantApp.Dto.CustomerDto;
import com.Restaurant.RestaurantApp.Entity.Customers;
import com.Restaurant.RestaurantApp.Repository.CustomerRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final JdbcTemplate jdbcTemplate;

    public CustomerService(CustomerRepository customerRepository, JdbcTemplate jdbcTemplate) {
        this.customerRepository = customerRepository;
        this.jdbcTemplate=jdbcTemplate;
    }
    public List<Customers> getCustomers() {
        return customerRepository.findAll();}

    public List<CustomerDto> getAllDTO() {
        String sql = "SELECT \"customers\".\"id\" AS \"customerId\", \"customers\".\"name\" AS \"name\" FROM \"public\".\"customers\"";

        RowMapper<CustomerDto> mapper = new RowMapper<>() {
            @Override
            public CustomerDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                CustomerDto customerDto = new CustomerDto();
                customerDto.setCustomerId(rs.getLong("customerId"));
                customerDto.setName(rs.getString("name"));
                return customerDto;
            }
        };

        return jdbcTemplate.query(sql, mapper);
    }
}
