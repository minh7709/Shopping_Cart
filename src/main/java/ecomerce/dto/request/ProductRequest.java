package ecomerce.dto.request;

import ecomerce.dto.response.BrandResponse;
import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String sku;
    private Integer price;
    private Integer stockQuantity;
    private Integer categoryId;
    private BrandResponse brand;
    private Integer seriesId;
    private Integer characterId;
    private String description;
    private String material;
    private String scale;
    private String dimensions;
    private Float weight;
    private Boolean isPreorder;
    private String releaseDate;
    private String mainImageUrl;

    // Getters and Setters
}