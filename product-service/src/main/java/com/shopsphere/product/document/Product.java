package com.shopsphere.product.document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Document
@NoArgsConstructor
@Data
public class Product {

    @Id
    private String id;
    @NotNull
    private String name;
    private String description;
    @NotBlank
    private BigDecimal price;
    private Integer stock;
    private String categoryId;
    private LocalDateTime createdAt;

    public Product(String id, String name, String description, BigDecimal price, Integer stock, String categoryId, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.categoryId = categoryId;
        this.createdAt = LocalDateTime.now();
    }
}
