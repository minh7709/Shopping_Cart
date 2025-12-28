package ecomerce.controller.user;

import ecomerce.dto.ApiResponse;
import ecomerce.dto.ProductResponse;
import ecomerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Lấy tất cả sản phẩm
    @GetMapping
    public ApiResponse<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return ApiResponse.success("Products retrieved successfully", products);
    }

    // Lấy sản phẩm theo ID
    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(product -> ApiResponse.success("Product retrieved successfully", product))
                .orElse(ApiResponse.error("Product not found"));
    }

    // Tìm kiếm sản phẩm theo tên
    @GetMapping("/search")
    public ApiResponse<List<ProductResponse>> searchProducts(@RequestParam String name) {
        List<ProductResponse> products = productService.findProductsByName(name);
        return ApiResponse.success("Products searched successfully", products);
    }

    // Lấy sản phẩm còn hàng
    @GetMapping("/available")
    public ApiResponse<List<ProductResponse>> getAvailableProducts() {
        List<ProductResponse> products = productService.getAvailableProducts();
        return ApiResponse.success("Available products retrieved successfully", products);
    }
}
