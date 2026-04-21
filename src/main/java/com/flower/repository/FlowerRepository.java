package com.flower.repository;

import com.flower.model.Flower;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<Flower, Long>:
//   - Flower: quản lý bảng hoa
//   - Long  : kiểu dữ liệu của id
// Có sẵn các hàm: findAll(), findById(), save(), deleteById()... KHÔNG cần tự viết!
public interface FlowerRepository extends JpaRepository<Flower, Long> {

    // Spring tự hiểu tên hàm và tạo SQL tương ứng:
    // Tìm hoa có tên CHỨA keyword, không phân biệt hoa/thường
    // Trả về dạng phân trang (Page)
    // SQL: SELECT * FROM flowers WHERE name LIKE '%keyword%' LIMIT 10
    Page<Flower> findByNameContainingIgnoreCase(String keyword, Pageable pageable);

}