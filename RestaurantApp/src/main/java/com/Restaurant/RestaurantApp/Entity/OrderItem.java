package com.Restaurant.RestaurantApp.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_id_seq")
    @SequenceGenerator(name = "order_item_id_seq", sequenceName = "order_item_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;  // 'item' adında bir ilişki var

    @Column(name = "quantity", length = 50)
    private Long quantity;

    @Column(name = "price" ,length = 50)
    private BigDecimal price;

    public OrderItem() {}

    public OrderItem(Long id, Orders orders, Item item, Long quantity, BigDecimal price) {
        this.id = id;
        this.orders = orders;
        this.item = item;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Orders getOrder() { return orders; }
    public void setOrder(Orders order) { this.orders = order; }

    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }

    public Long getQuantity() { return quantity; }
    public void setQuantity(Long quantity) { this.quantity = quantity; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
