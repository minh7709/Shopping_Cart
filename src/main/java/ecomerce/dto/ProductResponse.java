package ecomerce.dto;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String imageUrl;
    private Integer stockQuantity;
    private Boolean inStock;
}
