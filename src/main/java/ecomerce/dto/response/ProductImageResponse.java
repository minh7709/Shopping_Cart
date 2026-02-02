package ecomerce.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductImageResponse {
    private Integer id;
    private String imageUrl;
    private Boolean isMain;
}

