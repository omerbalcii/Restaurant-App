package com.Restaurant.RestaurantApp.Controller;

import com.Restaurant.RestaurantApp.Dto.ItemDto;
import com.Restaurant.RestaurantApp.Entity.Item;
import com.Restaurant.RestaurantApp.Service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/item")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // http://localhost:8085/item/getall
    @GetMapping(path = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemDto>> getAll() {
        try {
            List<ItemDto> itemsList = itemService.getItems();
            return ResponseEntity.ok(itemsList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping(path = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody Item items)
    {
        // localhost:8085/api/item/save
        try
        {
            boolean result = itemService.addItems(items);
            if (result)
            {
                return ResponseEntity.ok("Kayıt başarı ile kaydedildi");
            }
            else
            {
                return ResponseEntity.internalServerError().body("Kayıt başarı ile kaydedilemedi");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Kayıt başarı ile kaydedilemedi");
        }
    }
}