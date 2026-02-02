package ecomerce.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CartItemResponse {
    private Integer id;
    private Integer productId;
    private String productName;
    private String productImageUrl;
    private BigDecimal productPrice;
    private Integer quantity;
    private BigDecimal subtotal;
    private Integer availableStock;
}

