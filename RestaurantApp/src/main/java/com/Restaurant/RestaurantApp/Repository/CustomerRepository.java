package com.Restaurant.RestaurantApp.Repository;

import com.Restaurant.RestaurantApp.Entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {
}
