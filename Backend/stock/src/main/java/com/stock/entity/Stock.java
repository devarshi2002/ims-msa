package com.stock.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    private Long productId; // FK reference to Product.productId (no enforced FK across DBs)
    private Integer quantity;
    private String location;

    // getters & setters

    public Stock(Long stockId, Long productId, Integer quantity, String location) {
        this.stockId = stockId;
        this.productId = productId;
        this.quantity = quantity;
        this.location = location;
    }

    public Stock() {
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
