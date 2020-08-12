package com.grz55.restautantorderingsystem;

import javax.persistence.*;

@Entity
public class OrderedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private MenuProduct menuProduct;

    private int quantity;

    private String comment;

    public OrderedProduct() {
    }

    public OrderedProduct(MenuProduct menuProduct, int quantity) {
        this.menuProduct = menuProduct;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MenuProduct getMenuProduct() {
        return menuProduct;
    }

    public void setMenuProduct(MenuProduct menuProduct) {
        this.menuProduct = menuProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "OrderedProduct{" +
                "id=" + id +
                ", menuProduct=" + menuProduct +
                ", quantity=" + quantity +
                ", comment='" + comment + '\'' +
                '}';
    }
}
