package ecomerce.controller.API;

import ecomerce.dto.request.ProductSearchCriteria;
import ecomerce.dto.response.ApiResponse;
import ecomerce.dto.response.ProductResponse;
import ecomerce.dto.response.ProductSummaryResponse;
import ecomerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductService productService;

    /**
     * API public: Lấy danh sách sản phẩm với filter, sort, pagination
     * Trả về ApiResponse chuẩn cho frontend/client
     */
    @GetMapping
    public ApiResponse<Page<ProductSummaryResponse>> getProductsApi(
            @ModelAttribute ProductSearchCriteria criteria,
            Pageable pageable
    ) {
        Page<ProductSummaryResponse> products = productService.getProducts(criteria, pageable);
        return ApiResponse.success(products);
    }

    /**
     * API public: Lấy chi tiết sản phẩm theo ID
     * Trả về ApiResponse chuẩn cho frontend/client
     */
    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getProductByIdApi(@PathVariable int id) {
        ProductResponse product = productService.getProductById(id);
        if (product == null) {
            return ApiResponse.error("Product not found");
        }
        return ApiResponse.success(product);
    }
}
