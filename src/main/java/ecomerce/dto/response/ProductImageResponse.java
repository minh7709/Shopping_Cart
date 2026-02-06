package ecomerce.dto.response;

import lombok.Builder;
import lombok.Data;
import ecomerce.entity.ProductImage;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ProductImageResponse {
    private Integer id;
    private String imageUrl;
    private Boolean isMain;

    public static ProductImageResponse toDTO(ProductImage productImage) {
        return ProductImageResponse.builder()
                .id(productImage.getId())
                .imageUrl(productImage.getImageUrl())
                .isMain(productImage.getIsMain())
                .build();
    }

    public static List<ProductImageResponse> toDTOList(List<ProductImage> images) {
        if (images == null || images.isEmpty()) {
            return List.of();
        }
        return images.stream()
                .map(ProductImageResponse::toDTO)
                .collect(Collectors.toList());
    }
}

