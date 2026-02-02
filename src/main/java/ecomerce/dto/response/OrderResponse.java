package ecomerce.dto.response;

import ecomerce.entity.type.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderResponse {
    private Integer id;
    private Integer userId;
    private String userFullName;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private String shippingAddress;
    private String paymentMethod;
    private String notes;
    private List<OrderItemResponse> orderItems;
}

