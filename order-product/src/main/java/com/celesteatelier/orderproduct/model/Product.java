package com.celesteatelier.orderproduct.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Product {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ProductID;

    @Column
    String name;

    @Column
    String description;

    @Column
    String price;

    @Column
    String stockQuantity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Long getProductID() {
        return ProductID;
    }

    public void setProductID(Long productID) {
        ProductID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(String stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
