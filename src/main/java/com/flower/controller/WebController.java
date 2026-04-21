package com.flower.controller;

import com.flower.model.Flower;
import com.flower.service.CartService;
import com.flower.service.FlowerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// @Controller: nhận request từ trình duyệt và trả về trang HTML
@Controller
public class WebController {

    private final FlowerService flowerService;
    private final CartService cartService;

    public WebController(FlowerService flowerService, CartService cartService) {
        this.flowerService = flowerService;
        this.cartService = cartService;
    }

    // Tự động thêm số giỏ hàng vào MỌI trang HTML
    // Nhờ vậy icon giỏ hàng luôn hiển thị đúng số
    @ModelAttribute("cartCount")
    public int cartCount(HttpSession session) {
        return cartService.getTotalItems(session);
    }

    // TRANG CHỦ: danh sách hoa + tìm kiếm + phân trang
    // URL: http://localhost:8080/
    @GetMapping("/")
    public String home(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) String keyword,
            Model model) {

        int pageSize = 10;
        Page<Flower> flowerPage = flowerService.getFlowers(keyword, page, pageSize);

        model.addAttribute("flowers", flowerPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", flowerPage.getTotalPages());
        model.addAttribute("keyword", keyword);

        return "index"; // templates/index.html
    }

    // TRANG CHI TIẾT: thông tin 1 bông hoa
    // URL: http://localhost:8080/detail/1
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Optional<Flower> flower = flowerService.getFlowerById(id);

        if (flower.isPresent()) {
            model.addAttribute("flower", flower.get());
            return "detail"; // templates/detail.html
        } else {
            return "redirect:/"; // Không tìm thấy → về trang chủ
        }
    }

    // TRANG GIỎ HÀNG
    // URL: http://localhost:8080/cart
    @GetMapping("/cart")
    public String cart(HttpSession session, Model model) {
        model.addAttribute("cartItems", cartService.getCart(session));
        model.addAttribute("totalAmount", cartService.getTotalAmount(session));
        return "cart"; // templates/cart.html
    }

    // THÊM VÀO GIỎ: xử lý khi bấm nút "Thêm vào giỏ"
    @PostMapping("/cart/add")
    public String addToCart(
            @RequestParam Long flowerId,
            @RequestParam(defaultValue = "1") int quantity,
            HttpSession session) {

        Optional<Flower> flower = flowerService.getFlowerById(flowerId);
        if (flower.isPresent()) {
            cartService.addToCart(session, flower.get(), quantity);
        }
        return "redirect:/cart";
    }

    // XÓA KHỎI GIỎ: xử lý khi bấm nút xóa
    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam Long flowerId, HttpSession session) {
        cartService.removeFromCart(session, flowerId);
        return "redirect:/cart";
    }
}