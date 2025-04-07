package com.Restaurant.RestaurantApp.Dto;

import java.math.BigDecimal;

public class OrderItemModelDto {

    public Long orderItemId;
    public Long itemId;
    public Long quantity;
    public String itemName;
    public BigDecimal price;
    public BigDecimal total;


    public OrderItemModelDto(Long orderItemId, Long itemId, Long quantity, String itemName, BigDecimal price, BigDecimal total) {
        this.orderItemId = orderItemId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.itemName = itemName;
        this.price = price;
        this.total = total;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
