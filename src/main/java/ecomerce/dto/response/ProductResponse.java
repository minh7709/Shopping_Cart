package ecomerce.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import ecomerce.entity.Product;

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
    private String description;
    private String material;
    private String scale;
    private String dimensions;
    private Float weight;
    private Boolean isPreorder;
    private LocalDate releaseDate;
    private String mainImageUrl;
    private List<ProductImageResponse> images;

    private Boolean isDeleted;

    public static ProductResponse toDTO(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .category(product.getCategory() != null ? CategoryResponse.toDTO(product.getCategory()) : null)
                .brand(product.getBrand() != null ? BrandResponse.toDTO(product.getBrand()) : null)
                .series(product.getSeries() != null ? SeriesResponse.toDTO(product.getSeries()) : null)
                .character(product.getCharacter() != null ? CharacterResponse.toDTO(product.getCharacter()) : null)
                .description(product.getDescription())
                .material(product.getMaterial())
                .scale(product.getScale())
                .dimensions(product.getDimensions())
                .weight(product.getWeight())
                .isPreorder(product.getIsPreorder())
                .releaseDate(product.getReleaseDate())
                .mainImageUrl(product.getMainImageUrl())
                .images(ProductImageResponse.toDTOList(product.getImages()))
                .isDeleted(product.getIsDeleted())
                .build();
    }
}

