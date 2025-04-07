package com.Restaurant.RestaurantApp.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
    @SequenceGenerator(name = "order_id_seq", sequenceName = "order_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "order_no", length = 50)
    private String orderNo;

    @Column(name = "grand_total", precision = 10, scale = 2)
    private BigDecimal grandTotal;

    @Column(name = "payment_method", length = 50)
    private String paymentMethod;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customers customers;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    // Getters and Setters

    @Formula(value = "(SELECT c.name FROM customers c WHERE c.id = customer_id)")  // Formula ile customer name çekiliyor
    private String customerName;

    // Constructors
    public Orders() {}

    public Orders(Long id, String orderNo, BigDecimal grandTotal, String paymentMethod, Customers customers, List<OrderItem> orderItems) {
        this.id = id;
        this.orderNo = orderNo;
        this.grandTotal = grandTotal;
        this.paymentMethod = paymentMethod;
        this.customers = customers;
        this.orderItems = orderItems;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOrderNo() { return orderNo; }
    public void setOrderNo(String orderNo) { this.orderNo = orderNo; }

    public BigDecimal getGrandTotal() { return grandTotal; }
    public void setGrandTotal(BigDecimal grandTotal) { this.grandTotal = grandTotal; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public Customers getCustomers() { return customers; }
    public void setCustomers(Customers customers) { this.customers = customers; }

    public List<OrderItem> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
}
