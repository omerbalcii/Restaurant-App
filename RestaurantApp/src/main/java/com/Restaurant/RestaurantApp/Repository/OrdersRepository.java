package com.Restaurant.RestaurantApp.Repository;

import com.Restaurant.RestaurantApp.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
