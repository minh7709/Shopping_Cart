package ecomerce.dto.response;

import ecomerce.entity.Brand;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BrandResponse {
    private Integer id;
    private String name;
    public static BrandResponse toDTO(Brand brand) {
        return BrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .build();
    }
    public static Brand toEntity(BrandResponse brandResponse) {
        Brand brand = new Brand();
        brand.setId(brandResponse.getId());
        brand.setName(brandResponse.getName());
        return brand;
    }
}

