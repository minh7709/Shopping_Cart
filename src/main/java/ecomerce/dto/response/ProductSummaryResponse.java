package ecomerce.dto.response;

import ecomerce.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductSummaryResponse {
    private Integer id;
    private String name;
    private String sku;
    private BigDecimal price;
    private String mainImageUrl;
    private Integer stockQuantity;
    private Boolean inStock;

    private Boolean isPreorder;
    private String releaseDate;

    public static ProductSummaryResponse toDTO(Product product) {
        return ProductSummaryResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .price(product.getPrice())
                .mainImageUrl(product.getMainImageUrl())
                .stockQuantity(product.getStockQuantity())
                .inStock(product.getStockQuantity() != null && product.getStockQuantity() > 0)
                .isPreorder(product.getIsPreorder())
                .releaseDate(product.getReleaseDate() != null ? product.getReleaseDate().toString() : null)
                .build();
    }
}

