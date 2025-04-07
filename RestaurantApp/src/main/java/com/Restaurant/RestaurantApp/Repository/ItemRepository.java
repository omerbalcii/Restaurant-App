package com.Restaurant.RestaurantApp.Repository;

import com.Restaurant.RestaurantApp.Dto.ItemDto;
import com.Restaurant.RestaurantApp.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
