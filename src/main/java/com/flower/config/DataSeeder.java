package com.flower.config;

import com.flower.model.Flower;
import com.flower.repository.FlowerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

// CommandLineRunner: tự động chạy khi app khởi động
// Mục đích: thêm dữ liệu mẫu vào database nếu đang trống
@Component
public class DataSeeder implements CommandLineRunner {

    private final FlowerRepository flowerRepository;

    public DataSeeder(FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }

    @Override
    public void run(String... args) {
        // Chỉ thêm nếu database đang trống, tránh trùng lặp
        if (flowerRepository.count() == 0) {
            System.out.println("🌱 Đang thêm dữ liệu mẫu...");

            flowerRepository.saveAll(List.of(
                makeFlower("Hoa Hồng Đỏ Tình Yêu", "Hoa Hồng", "Đỏ", 150000.0, 50,
                    "Bó hoa hồng đỏ thắm, biểu tượng của tình yêu mãnh liệt.",
                    "https://images.unsplash.com/photo-1582794543139-8ac9cb0f7b11?w=600&q=80"),

                makeFlower("Hoa Hướng Dương Rực Rỡ", "Hướng Dương", "Vàng", 120000.0, 30,
                    "Hoa hướng dương vàng rực rỡ, tượng trưng cho sự lạc quan.",
                    "https://images.unsplash.com/photo-1490750967868-88aa4486c946?w=600&q=80"),

                makeFlower("Hoa Baby Trắng Tinh Khôi", "Baby", "Trắng", 200000.0, 20,
                    "Hoa baby trắng nhỏ li ti, thanh lịch và dịu dàng.",
                    "https://images.unsplash.com/photo-1562690868-60bbe7293e94?w=600&q=80"),

                makeFlower("Hoa Cẩm Tú Cầu Xanh", "Cẩm Tú Cầu", "Xanh", 250000.0, 15,
                    "Cẩm tú cầu xanh mang vẻ đẹp lãng mạn, biểu tượng lòng biết ơn.",
                    "https://images.unsplash.com/photo-1544038659-12337883d216?w=600&q=80"),

                makeFlower("Hoa Ly Trắng Thuần Khiết", "Hoa Ly", "Trắng", 180000.0, 25,
                    "Hoa ly trắng thanh cao, hương thơm dịu nhẹ.",
                    "https://images.unsplash.com/photo-1490750967868-88aa4486c946?w=600&q=80"),

                makeFlower("Hoa Tulip Hồng Ngọt Ngào", "Tulip", "Hồng", 220000.0, 35,
                    "Tulip hồng từ Hà Lan, biểu tượng tình yêu hoàn hảo.",
                    "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=600&q=80"),

                makeFlower("Hoa Cúc Vàng Tươi Sáng", "Hoa Cúc", "Vàng", 80000.0, 60,
                    "Hoa cúc vàng tươi sáng, mang ý nghĩa may mắn.",
                    "https://images.unsplash.com/photo-1508610048659-a06b669e3321?w=600&q=80"),

                makeFlower("Hoa Lan Tím Quý Phái", "Hoa Lan", "Tím", 350000.0, 10,
                    "Hoa lan tím quý phái, biểu tượng sự sang trọng.",
                    "https://images.unsplash.com/photo-1518709268805-4e9042af9f23?w=600&q=80"),

                makeFlower("Hoa Đồng Tiền Đỏ May Mắn", "Đồng Tiền", "Đỏ", 90000.0, 45,
                    "Hoa đồng tiền đỏ tươi, mang lại may mắn và tài lộc.",
                    "https://images.unsplash.com/photo-1490750967868-88aa4486c946?w=600&q=80"),

                makeFlower("Hoa Hồng Vàng Sang Trọng", "Hoa Hồng", "Vàng", 170000.0, 40,
                    "Hoa hồng vàng rực rỡ, biểu tượng tình bạn.",
                    "https://images.unsplash.com/photo-1582794543139-8ac9cb0f7b11?w=600&q=80"),

                makeFlower("Hoa Cẩm Chướng Hồng", "Cẩm Chướng", "Hồng", 110000.0, 55,
                    "Hoa cẩm chướng hồng tươi, tượng trưng tình mẫu tử.",
                    "https://images.unsplash.com/photo-1490750967868-88aa4486c946?w=600&q=80"),

                makeFlower("Hoa Thược Dược Tím", "Thược Dược", "Tím", 130000.0, 20,
                    "Hoa thược dược tím rực rỡ từ Đà Lạt.",
                    "https://images.unsplash.com/photo-1490750967868-88aa4486c946?w=600&q=80")
            ));

            System.out.println("✅ Đã thêm 12 loài hoa mẫu!");
        }
    }

    // Hàm tạo đối tượng Flower nhanh, tránh lặp code
    private Flower makeFlower(String name, String species, String color,
                               Double price, Integer stock, String desc, String imageUrl) {
        Flower f = new Flower();
        f.setName(name);
        f.setSpecies(species);
        f.setColor(color);
        f.setPrice(price);
        f.setStockQuantity(stock);
        f.setDescription(desc);
        f.setImageUrl(imageUrl);
        return f;
    }
}