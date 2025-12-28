package ecomerce.controller.admin;

import ecomerce.dto.ApiResponse;
import ecomerce.dto.ProductRequest;
import ecomerce.dto.ProductResponse;
import ecomerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    // Thêm sản phẩm mới
    @PostMapping
    public ApiResponse<ProductResponse> addProduct(@RequestBody ProductRequest request) {
        ProductResponse product = productService.addProduct(request);
        return ApiResponse.success("Product added successfully", product);
    }

    // Cập nhật sản phẩm
    @PutMapping("/{id}")
    public ApiResponse<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest request) {
        ProductResponse product = productService.updateProduct(id, request);
        return ApiResponse.success("Product updated successfully", product);
    }

    // Xóa sản phẩm
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ApiResponse.success("Product deleted successfully");
    }
}