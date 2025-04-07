package com.Restaurant.RestaurantApp.Dto;

import java.math.BigDecimal;

public class GetOrderDto {

    public Long orderId;
    public String orderNo;
    public String paymentMethod;
    public Long customerId;
    public String customerName;
    public BigDecimal grandTotal;


    public GetOrderDto(Long orderId, String orderNo, String paymentMethod, Long customerId, String customerName, BigDecimal grandTotal) {
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.paymentMethod = paymentMethod;
        this.customerId = customerId;
        this.customerName = customerName;
        this.grandTotal = grandTotal;
    }
    public GetOrderDto(){}

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }
}
