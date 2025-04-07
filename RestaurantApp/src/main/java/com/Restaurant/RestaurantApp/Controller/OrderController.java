package com.Restaurant.RestaurantApp.Controller;

import com.Restaurant.RestaurantApp.Dto.CustomerDto;
import com.Restaurant.RestaurantApp.Dto.GetOrderDto;
import com.Restaurant.RestaurantApp.Dto.OrderDto;
import com.Restaurant.RestaurantApp.Entity.Orders;
import com.Restaurant.RestaurantApp.Service.OrdersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/order")
public class OrderController {
    private final OrdersService ordersService;

    public OrderController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    //http://localhost:8085/order/getall
    @GetMapping(path = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Orders>> getAll() {
        try {
            List<Orders> ordersList = ordersService.getOrders();
            return ResponseEntity.ok(ordersList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping(path = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody OrderDto orders) {
        try {
            boolean result = ordersService.saveOrder(orders);
            if (result) {
                return ResponseEntity.ok("Kayıt başarı ile kaydedildi");
            } else {
                return ResponseEntity.internalServerError().body("Kayıt başarısız");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Sunucu hatası: " + e.getMessage());
        }
    }

    @DeleteMapping(path = "deletebyid/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> deletebyid(@PathVariable(name = "id") long id)
    {
        // localhost:8080/Ders/deletebyid/1
        try
        {
            boolean result = ordersService.deleteOrderById(id);
            if (result)
            {
                return ResponseEntity.ok(id + "id 'li kayıt başarı ile silindi");
            }
            else
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id + "id 'li kayıt bulunamadı");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(id + "id 'li kayıt başarı ile silindi");
        }
    }
    @GetMapping(path = "getAllDto", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GetOrderDto>> getalldto()
    {
        // localhost:8080/ders/getalldto
        try
        {
            return ResponseEntity.ok(ordersService.getAllOrderDto());
        }
        catch (Exception e)
        {
            // daha sonra değişecek exception handling olacak
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
