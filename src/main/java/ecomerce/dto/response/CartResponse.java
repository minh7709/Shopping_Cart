package ecomerce.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CartResponse {
    private Integer id;
    private Integer userId;
    private LocalDateTime updatedAt;
    private List<CartItemResponse> items;
    private BigDecimal totalAmount;
    private Integer totalItems;
}

