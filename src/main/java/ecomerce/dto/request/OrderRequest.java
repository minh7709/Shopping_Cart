package ecomerce.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private String shippingAddress;
    private String paymentMethod;
    private String notes;
    private List<OrderItemRequest> orderItems;
}

