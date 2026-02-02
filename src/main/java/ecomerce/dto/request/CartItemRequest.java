package ecomerce.dto.request;

import lombok.Data;

@Data
public class CartItemRequest {
    private Integer productId;
    private Integer quantity;
}

