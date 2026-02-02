package ecomerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Formula;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String sku;
    private BigDecimal price;
    private Integer stockQuantity;
    @Column(name = "sold_quantity")
    private Integer soldQuantity = 0; // Mặc định là 0

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "series_id")
    private Series series;

    @ManyToOne
    @JoinColumn(name = "character_id")
    private CharacterEntity character; // Đổi tên tránh trùng keyword Character của Java


    @Column(columnDefinition = "TEXT")
    private String description;
    private String material;
    private String scale;
    private String dimensions;
    private Float weight;
    private Boolean isPreorder;
    private LocalDate releaseDate;

    /**
     * Tự động cập nhật isPreorder dựa trên stockQuantity
     * Khi stockQuantity > 0 thì isPreorder = false, ngược lại = true
     */
    @PrePersist
    @PreUpdate
    public void updatePreorderStatus() {
        if (this.stockQuantity != null && this.stockQuantity > 0) {
            this.isPreorder = false;
        } else {
            this.isPreorder = true;
        }
    }
    @Formula("(SELECT i.image_url FROM product_images i WHERE i.product_id = id AND i.is_main = true LIMIT 1)")
    private String mainImageUrl;

    @BatchSize(size = 20)
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProductImage> images;
    private LocalDate createdAt;
    private Boolean isDeleted = false;
}
