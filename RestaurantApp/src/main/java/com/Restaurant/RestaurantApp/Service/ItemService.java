package com.Restaurant.RestaurantApp.Service;

import com.Restaurant.RestaurantApp.Dto.ItemDto;
import com.Restaurant.RestaurantApp.Entity.Item;
import com.Restaurant.RestaurantApp.Entity.Orders;
import com.Restaurant.RestaurantApp.Repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemDto> getItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream()
                .map(item -> new ItemDto(item.getId(), item.getName(), item.getPrice()))
                .collect(Collectors.toList());
    }

    public boolean addItems(Item item) {
        itemRepository.save(item);
        return true;
    }
}
