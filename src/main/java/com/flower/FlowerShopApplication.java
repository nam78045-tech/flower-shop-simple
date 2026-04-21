package com.flower;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlowerShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowerShopApplication.class, args);
        System.out.println("=================================");
        System.out.println("🌸 Flower Shop đã khởi động!");
        System.out.println("🌐 Truy cập: http://localhost:8080");
        System.out.println("=================================");
    }
}