package ecomerce.controller.API;

import ecomerce.dto.request.ProductSearchCriteria;
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

    @GetMapping
    public Page<ProductSummaryResponse> getProducts(
            @ModelAttribute ProductSearchCriteria criteria,
            Pageable pageable
    ) {
        return productService.getProducts(criteria, pageable);
    }
}
