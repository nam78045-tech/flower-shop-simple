package com.flower.service;

import com.flower.model.Flower;
import com.flower.repository.FlowerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

// @Service: báo với Spring "đây là class xử lý logic"
// Spring tự tạo và quản lý 1 instance duy nhất của class này
@Service
public class FlowerService {

    // FlowerRepository để giao tiếp với database
    private final FlowerRepository flowerRepository;

    // Spring tự động truyền FlowerRepository vào đây
    public FlowerService(FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }

    // Lấy danh sách hoa có phân trang + tìm kiếm
    // keyword : từ khóa tìm kiếm (có thể null)
    // page    : đang ở trang số mấy
    // pageSize: mỗi trang bao nhiêu hoa (sẽ truyền vào 10)
    public Page<Flower> getFlowers(String keyword, int page, int pageSize) {

        // page - 1 vì Spring tính trang từ 0, người dùng thấy từ 1
        Pageable pageable = PageRequest.of(page - 1, pageSize);

        // Nếu không có keyword → lấy tất cả
        // Nếu có keyword → tìm kiếm theo tên
        if (keyword == null || keyword.trim().isEmpty()) {
            return flowerRepository.findAll(pageable);
        } else {
            return flowerRepository.findByNameContainingIgnoreCase(keyword.trim(), pageable);
        }
    }

    // Lấy 1 bông hoa theo ID (dùng cho trang chi tiết)
    // Optional: có thể có hoặc không có kết quả, tránh lỗi null
    public Optional<Flower> getFlowerById(Long id) {
        return flowerRepository.findById(id);
    }
}