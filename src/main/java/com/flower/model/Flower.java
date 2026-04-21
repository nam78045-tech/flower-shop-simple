package com.flower.model;

import jakarta.persistence.*;

// @Entity: class này = 1 bảng trong database
// @Table: tên bảng là "flowers" trong MySQL
@Entity
@Table(name = "flowers")
public class Flower {

    // @Id: đây là khóa chính (PRIMARY KEY)
    // @GeneratedValue: tự động tăng ID (1, 2, 3...)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nullable = false: bắt buộc phải có, không được trống
    @Column(nullable = false, length = 150)
    private String name;        // Tên hoa

    @Column(length = 2000)
    private String description; // Mô tả

    @Column(nullable = false)
    private Double price;       // Giá tiền

    @Column(nullable = false)
    private Integer stockQuantity; // Số lượng tồn kho

    @Column(length = 255)
    private String imageUrl;    // Link ảnh

    @Column(length = 100)
    private String species;     // Loài hoa

    @Column(length = 50)
    private String color;       // Màu sắc

    // Getter và Setter
    // Getter: lấy giá trị ra
    // Setter: đặt giá trị vào
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}