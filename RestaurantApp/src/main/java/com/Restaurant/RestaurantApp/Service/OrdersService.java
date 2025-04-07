package com.Restaurant.RestaurantApp.Service;

import com.Restaurant.RestaurantApp.Dto.GetOrderDto;
import com.Restaurant.RestaurantApp.Dto.OrderDto;
import com.Restaurant.RestaurantApp.Dto.OrderItemModelDto;
import com.Restaurant.RestaurantApp.Dto.OrderSubDto;
import com.Restaurant.RestaurantApp.Entity.Customers;
import com.Restaurant.RestaurantApp.Entity.Item;
import com.Restaurant.RestaurantApp.Entity.OrderItem;
import com.Restaurant.RestaurantApp.Entity.Orders;
import com.Restaurant.RestaurantApp.Repository.CustomerRepository;
import com.Restaurant.RestaurantApp.Repository.OrdersRepository;
import com.Restaurant.RestaurantApp.Repository.OrderItemRepository;
import com.Restaurant.RestaurantApp.Repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrdersService {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;
    private final OrdersRepository ordersRepository;
    private final CustomerRepository customerRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;

    public OrdersService(OrdersRepository ordersRepository, CustomerRepository customerRepository, OrderItemRepository orderItemRepository, ItemRepository itemRepository,JdbcTemplate jdbcTemplate,NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.ordersRepository = ordersRepository;
        this.customerRepository = customerRepository;
        this.orderItemRepository = orderItemRepository;
        this.itemRepository = itemRepository;
        this.jdbcTemplate=jdbcTemplate;
        this.namedParameterJdbcTemplate=namedParameterJdbcTemplate;
    }

    public List<Orders> getOrders() {
        return ordersRepository.findAll();
    }

    public boolean addOrder(Orders order) {
        ordersRepository.save(order);
        return true;
    }

    @Transactional
    public boolean saveOrder(OrderDto orderDto) {
        try {
            Orders order = new Orders();
            OrderSubDto subDto = orderDto.getOrderSubDto();

            Long customerId = subDto.getCustomerId();

            Customers customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

            order.setCustomers(customer);
            order.setOrderNo(subDto.getOrderNo());
            order.setPaymentMethod(subDto.getPaymentMethod());
            order.setGrandTotal(subDto.getGrandTotal());

            ordersRepository.save(order);

            for (OrderItemModelDto itemDto : orderDto.getOrderItemModelDtoList()) {
                OrderItem orderItem = new OrderItem();
                Item item = itemRepository.findById(itemDto.getItemId())
                        .orElseThrow(() -> new RuntimeException("Item not found with ID: " + itemDto.getItemId()));
                orderItem.setOrder(order);
                orderItem.setItem(item);
                orderItem.setQuantity(itemDto.getQuantity());
                orderItem.setPrice(itemDto.getPrice());

                orderItemRepository.save(orderItem);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace(); // Hata detaylarını logla
            return false;
        }
    }
    public List<GetOrderDto> getAllOrderDto() {
        RowMapper<GetOrderDto> mapper = new RowMapper<GetOrderDto>() {
            @Override
            public GetOrderDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                GetOrderDto dto = new GetOrderDto();
                dto.setOrderId(rs.getLong("ORDER_ID"));
                dto.setOrderNo(rs.getString("ORDER_NO"));
                dto.setPaymentMethod(rs.getString("PAYMENT_METHOD"));
                dto.setGrandTotal(rs.getBigDecimal("GRAND_TOTAL"));
                dto.setCustomerId(rs.getLong("CUSTOMER_ID"));
                dto.setCustomerName(rs.getString("CUSTOMER_NAME"));
                return dto;
            }
        };

        String sql = """
        SELECT o.id AS ORDER_ID,
               o.order_no AS ORDER_NO,
               o.payment_method AS PAYMENT_METHOD,
               o.grand_total AS GRAND_TOTAL,
               c.id AS CUSTOMER_ID,
               c.name AS CUSTOMER_NAME
        FROM Orders o
        INNER JOIN Customers c ON c.id = o.customer_id
        """;

        return jdbcTemplate.query(sql, mapper);
    }
        public List<OrderItem> getByOrderId(Long orderId) {
        String sql = "Select * From \"order_items\" Where \"order_id\" = :orderId";
        Map<String, Object> param = new HashMap<>();
        param.put("orderId", orderId);

            return namedParameterJdbcTemplate.query(
                    sql,
                    param,
                    new BeanPropertyRowMapper<>(OrderItem.class)
            );
        }

    @Transactional
    public boolean deleteOrderById(Long orderId) {
        try {
            Optional<Orders> orderOptional = ordersRepository.findById(orderId);
            if (orderOptional.isEmpty()) {
                throw new RuntimeException("Order not found with ID: " + orderId);
            }

            Orders order = orderOptional.get();

            List<OrderItem> orderItems = getByOrderId(orderId);
            orderItemRepository.deleteAll(orderItems);

            ordersRepository.delete(order);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



}
