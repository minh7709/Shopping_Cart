package ecomerce.dto.request;

import lombok.Data;

@Data
public class ProductImageRequest {
    private String imageUrl;
    private Boolean isMain;
}

