package ecomerce.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ProductResponse {
    private Integer id;
    private String name;
    private String sku;
    private BigDecimal price;
    private Integer stockQuantity;
    private CategoryResponse category;
    private BrandResponse brand;
    private SeriesResponse series;
    private CharacterResponse character;
    private String material;
    private String scale;
    private String dimensions;
    private Float weight;
    private Boolean isPreorder;
    private LocalDate releaseDate;
    private List<ProductImageResponse> images;
    private Boolean isDeleted;
}

