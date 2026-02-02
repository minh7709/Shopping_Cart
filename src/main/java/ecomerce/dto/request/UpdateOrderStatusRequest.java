package ecomerce.dto.request;

import ecomerce.entity.type.OrderStatus;
import lombok.Data;

@Data
public class UpdateOrderStatusRequest {
    private OrderStatus status;
}

