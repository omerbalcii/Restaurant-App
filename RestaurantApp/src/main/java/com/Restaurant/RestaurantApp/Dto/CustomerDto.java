package com.Restaurant.RestaurantApp.Dto;

public class CustomerDto {
    public Long customerId;
    public String name;

    public CustomerDto(Long customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public CustomerDto(){}

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
