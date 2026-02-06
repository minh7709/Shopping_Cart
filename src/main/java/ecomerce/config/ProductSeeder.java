package ecomerce.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ecomerce.entity.*;
import ecomerce.repository.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class ProductSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final SeriesRepository seriesRepository;
    private final CharacterRepository characterRepository;
    private final ProductImageRepository imageRepository;

    private final Random random = new Random();

    // Dữ liệu ngẫu nhiên cho các thuộc tính sản phẩm
    private static final String[] MATERIALS = {"PVC", "ABS", "PVC + ABS", "Resin", "Die-cast"};
    private static final String[] SCALES = {"1/4", "1/6", "1/7", "1/8", "Non-scale"};
    private static final String[] DIMENSIONS = {
            "H: 250mm x W: 150mm x D: 100mm",
            "H: 180mm x W: 120mm x D: 80mm",
            "H: 300mm x W: 200mm x D: 150mm",
            "H: 100mm x W: 80mm x D: 60mm",
            "H: 220mm x W: 140mm x D: 90mm"
    };
    private static final Float[] WEIGHTS = {0.3f, 0.5f, 0.8f, 1.2f, 1.5f, 2.0f};

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (productRepository.count() > 0) {
            return;
        }

        System.out.println("--- BẮT ĐẦU SEED DATA ---");

        // 1. Lấy dữ liệu từ API Dragon Ball
        List<CharacterDto> apiCharacters = fetchDragonBallCharacters();

        // 2. Tạo Category
        Category scale = saveCategory("Scale Figure");
        Category nendoroid = saveCategory("Nendoroid");
        Category popUp = saveCategory("Pop Up Parade");
        Category blindBox = saveCategory("Blind Box Series");

        // 3. Tạo Brand
        Brand gsc = saveBrand("Good Smile Company");
        Brand alter = saveBrand("Alter");
        Brand bandai = saveBrand("Bandai Namco");
        Brand popMart = saveBrand("Pop Mart");

        // 4. Tạo Series
        Series onePiece = saveSeries("One Piece");
        Series naruto = saveSeries("Naruto Shippuden");
        Series genshin = saveSeries("Genshin Impact");
        Series dragonBall = saveSeries("Dragon Ball Z"); // Thêm series cho đúng context API

        // 5. Tạo Character (Local DB)
        CharacterEntity luffy = saveCharacter(onePiece, "Monkey D. Luffy");
        CharacterEntity zoro = saveCharacter(onePiece, "Roronoa Zoro");
        CharacterEntity narutoChar = saveCharacter(naruto, "Uzumaki Naruto");
        CharacterEntity raiden = saveCharacter(genshin, "Raiden Shogun");
        CharacterEntity goku = saveCharacter(dragonBall, "Son Goku"); // Placeholder character

        // 6. Tạo Products Cố định (Giữ lại các sản phẩm mẫu đẹp)
        createProduct("Monkey D. Luffy - Gear 5", "OP-G5-001", new BigDecimal("4500000"), 50, scale, bandai, onePiece, luffy,
                "Mô hình Luffy trạng thái Gear 5 cực ngầu.", "https://cdn.hstatic.net/products/200000462939/bandai-spirits-s-h-figuarts-monkey-d-luffy-romance-dawn-one-piece-01_f68ac3ea311c4921bd68da63f3c441eb_grande.jpg");

        createProduct("Roronoa Zoro - Ashura", "OP-ZORO-002", new BigDecimal("5200000"), 30, scale, alter, onePiece, zoro,
                "Zoro kỹ thuật 3 đầu 6 tay Ashura.", "https://product.hstatic.net/200000462939/product/figure-171088_8a5c58788cd242c3a0b43db6d4fff67a_grande.jpg");

        createProduct("Nendoroid Naruto", "NAR-NEN-003", new BigDecimal("1200000"), 100, nendoroid, gsc, naruto, narutoChar,
                "Naruto phiên bản Nendoroid dễ thương.", "https://cdn.hstatic.net/products/200000462939/-wo-mune-ni-tsunageta-chikara-naruto-shippudenbandai-spirits-figure-01_cd72071fd8cd434287b781eabf7760f2_grande.jpg");

        createProduct("Raiden Shogun Statue", "GEN-RAIDEN-004", new BigDecimal("6500000"), 15, scale, gsc, genshin, raiden,
                "Tượng Lôi Thần Raiden Shogun uy nghiêm.", "https://product.hstatic.net/200000462939/product/2_dac9ca3cc8f6414abe1979a28255bb65_grande.png");

        createProduct("Luffy Pop Up Parade", "OP-L-POP-005", new BigDecimal("950000"), 200, popUp, gsc, onePiece, luffy,
                "Dòng Pop Up Parade giá rẻ chất lượng cao.", "https://cdn.hstatic.net/products/200000462939/bandai-spirits-s-h-figuarts-monkey-d-luffy-romance-dawn-one-piece-01_f68ac3ea311c4921bd68da63f3c441eb_grande.jpg");

        // 7. Tạo Products Random từ API Dragon Ball
        // Nếu không lấy được data từ API thì bỏ qua vòng lặp này
        if (!apiCharacters.isEmpty()) {
            for (int i = 1; i <= 15; i++) {
                // Lấy ngẫu nhiên 1 nhân vật từ list API trả về
                CharacterDto randomChar = apiCharacters.get(random.nextInt(apiCharacters.size()));

                // Random giá từ 500k đến 5 triệu
                BigDecimal randomPrice = BigDecimal.valueOf(500000 + random.nextInt(4500000));

                createProduct(
                        randomChar.getName() + " Figure", // Tên lấy từ API
                        "DBZ-RDM-" + i,
                        randomPrice,
                        10 + random.nextInt(50),
                        blindBox, // Gán tạm vào category này hoặc random category
                        bandai,
                        dragonBall,
                        goku,
                        randomChar.getDescription() != null && randomChar.getDescription().length() > 255
                                ? randomChar.getDescription().substring(0, 255)
                                : randomChar.getDescription(),
                        randomChar.getImage()        // Ảnh lấy từ API
                );
            }
        }

        System.out.println("--- SEED DATA HOÀN TẤT ---");
    }

    // --- Helper Methods ---

    // Hàm gọi API để lấy danh sách nhân vật
    private List<CharacterDto> fetchDragonBallCharacters() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://dragonball-api.com/api/characters?limit=20"; // Lấy 20 nhân vật
            DragonBallResponse response = restTemplate.getForObject(url, DragonBallResponse.class);
            return response != null ? response.getItems() : new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Không thể lấy dữ liệu từ DragonBall API: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private Category saveCategory(String name) {
        Category c = new Category(); c.setName(name); return categoryRepository.save(c);
    }
    private Brand saveBrand(String name) {
        Brand b = new Brand(); b.setName(name); return brandRepository.save(b);
    }
    private Series saveSeries(String name) {
        Series s = new Series(); s.setName(name); return seriesRepository.save(s);
    }
    private CharacterEntity saveCharacter(Series s, String name) {
        CharacterEntity c = new CharacterEntity(); c.setSeries(s); c.setName(name); return characterRepository.save(c);
    }

    private void createProduct(String name, String sku, BigDecimal price, int stock,
                               Category cat, Brand brand, Series series, CharacterEntity charr,
                               String description, String imgUrl) {
        Product p = new Product();
        p.setName(name);
        p.setSku(sku);
        p.setPrice(price);
        p.setStockQuantity(stock);
        p.setSoldQuantity(random.nextInt(100)); // Random số lượng đã bán từ 0-499
        p.setCategory(cat);
        p.setBrand(brand);
        p.setSeries(series);
        p.setCharacter(charr);

        // Set Description lấy từ tham số (API)
        p.setDescription(description);

        // Random các thuộc tính bổ sung
        p.setMaterial(MATERIALS[random.nextInt(MATERIALS.length)]);
        p.setScale(SCALES[random.nextInt(SCALES.length)]);
        p.setDimensions(DIMENSIONS[random.nextInt(DIMENSIONS.length)]);
        p.setWeight(WEIGHTS[random.nextInt(WEIGHTS.length)]);
        p.setIsPreorder((p.getStockQuantity() > 0)? false : true); // Cập nhật isPreorder dựa trên stockQuantity

        p.setIsDeleted(false);
        p.setReleaseDate(LocalDate.now().plusDays(random.nextInt(60) - 30)); // Random trong khoảng -30 đến +30 ngày
        p.setCreatedAt(LocalDate.now().minusDays(random.nextInt(30))); // Random ngày tạo trong 30 ngày qua

        Product savedProduct = productRepository.save(p);

        // Lưu 3 Images cho mỗi product
        for (int i = 0; i < 3; i++) {
            ProductImage img = new ProductImage();
            img.setProduct(savedProduct);
            img.setImageUrl(imgUrl);
            img.setIsMain(i == 0); // Chỉ ảnh đầu tiên là main
            imageRepository.save(img);
        }
    }

    // --- DTO Classes để hứng JSON từ API ---
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class DragonBallResponse {
        private List<CharacterDto> items;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class CharacterDto {
        private String name;
        private String description;
        private String image;
    }
}