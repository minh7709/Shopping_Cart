package ecomerce.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class ProductRequest {
    private String name;
    private String sku;
    private BigDecimal price;
    private Integer stockQuantity;
    private Integer categoryId;
    private Integer brandId;
    private Integer seriesId;
    private Integer characterId;
    private String material;
    private String scale;
    private String dimensions;
    private Float weight;
    private Boolean isPreorder;
    private LocalDate releaseDate;
    private List<ProductImageRequest> images;
}

