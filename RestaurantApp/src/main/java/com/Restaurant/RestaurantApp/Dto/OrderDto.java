package com.Restaurant.RestaurantApp.Dto;

import java.util.List;

public class OrderDto {
    public OrderSubDto orderSubDto;
    public List<OrderItemModelDto> orderItemModelDtoList;


    public OrderDto(OrderSubDto orderSubDto, List<OrderItemModelDto> orderItemModelDtoList) {
        this.orderSubDto = orderSubDto;
        this.orderItemModelDtoList = orderItemModelDtoList;
    }

    public OrderSubDto getOrderSubDto() {
        return orderSubDto;
    }

    public void setOrderSubDto(OrderSubDto orderSubDto) {
        this.orderSubDto = orderSubDto;
    }

    public List<OrderItemModelDto> getOrderItemModelDtoList() {
        return orderItemModelDtoList;
    }

    public void setOrderItemModelDtoList(List<OrderItemModelDto> orderItemModelDtoList) {
        this.orderItemModelDtoList = orderItemModelDtoList;
    }
}
