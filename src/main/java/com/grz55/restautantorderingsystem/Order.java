package com.grz55.restautantorderingsystem;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "restaurant_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private LocalDateTime startTime;

    @Transient
    private LocalDateTime readyTime;

    @Transient
    private LocalDateTime paidTime;

    @Transient
    private RestaurantTable restaurantTable;

    @Transient
    private List<OrderedProduct> orderedProductList;

    private OrderStatus orderStatus;

    public Order() {
    }

    public Order(LocalDateTime startTime, RestaurantTable restaurantTable) {
        this.startTime = startTime;
        this.restaurantTable = restaurantTable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getReadyTime() {
        return readyTime;
    }

    public void setReadyTime(LocalDateTime readyTime) {
        this.readyTime = readyTime;
    }

    public LocalDateTime getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(LocalDateTime paidTime) {
        this.paidTime = paidTime;
    }

    public RestaurantTable getTable() {
        return restaurantTable;
    }

    public void setTable(RestaurantTable restaurantTable) {
        this.restaurantTable = restaurantTable;
    }

    public List<OrderedProduct> getOrderedProductList() {
        return orderedProductList;
    }

    public void setOrderedProductList(List<OrderedProduct> orderedProductList) {
        this.orderedProductList = orderedProductList;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", readyTime=" + readyTime +
                ", paidTime=" + paidTime +
                ", table=" + restaurantTable +
                ", orderedProductList=" + orderedProductList +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
