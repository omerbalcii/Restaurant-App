package com.Restaurant.RestaurantApp.Controller;

import com.Restaurant.RestaurantApp.Dto.CustomerDto;
import com.Restaurant.RestaurantApp.Entity.Customers;
import com.Restaurant.RestaurantApp.Service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping(path = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customers>> getAll() {
        try {
            List<Customers> customersList = customerService.getCustomers();
            return ResponseEntity.ok(customersList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping(path = "getAllDto", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerDto>> getalldto()
    {
        try
        {
            return ResponseEntity.ok(customerService.getAllDTO());
        }
        catch (Exception e)
        {
            // daha sonra değişecek exception handling olacak
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
