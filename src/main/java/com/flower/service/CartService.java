package com.flower.service;

import com.flower.model.Flower;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Class đại diện cho 1 sản phẩm trong giỏ hàng
class CartItem {
    private Flower flower;  // Thông tin bông hoa
    private int quantity;   // Số lượng muốn mua

    public CartItem(Flower flower, int quantity) {
        this.flower = flower;
        this.quantity = quantity;
    }

    public Flower getFlower() { return flower; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    // Tính thành tiền: đơn giá × số lượng
    public double getLineTotal() {
        return flower.getPrice() * quantity;
    }
}

@Service
public class CartService {

    // Tên key lưu giỏ hàng trong Session
    private static final String CART_KEY = "CART";

    // Lấy giỏ hàng từ Session, chưa có thì tạo giỏ trống
    @SuppressWarnings("unchecked")
    public List<CartItem> getCart(HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute(CART_KEY);
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute(CART_KEY, cart);
        }
        return cart;
    }

    // Thêm hoa vào giỏ
    // Nếu hoa đã có → cộng thêm số lượng
    // Nếu chưa có → thêm mới
    public void addToCart(HttpSession session, Flower flower, int quantity) {
        List<CartItem> cart = getCart(session);

        for (CartItem item : cart) {
            if (item.getFlower().getId().equals(flower.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        cart.add(new CartItem(flower, quantity));
    }

    // Xóa 1 sản phẩm khỏi giỏ theo ID hoa
    public void removeFromCart(HttpSession session, Long flowerId) {
        List<CartItem> cart = getCart(session);
        cart.removeIf(item -> item.getFlower().getId().equals(flowerId));
    }

    // Tính tổng tiền toàn bộ giỏ hàng
    public double getTotalAmount(HttpSession session) {
        List<CartItem> cart = getCart(session);
        double total = 0;
        for (CartItem item : cart) {
            total += item.getLineTotal();
        }
        return total;
    }

    // Đếm tổng số lượng sản phẩm trong giỏ
    // Dùng để hiển thị số trên icon giỏ hàng
    public int getTotalItems(HttpSession session) {
        List<CartItem> cart = getCart(session);
        int total = 0;
        for (CartItem item : cart) {
            total += item.getQuantity();
        }
        return total;
    }
}