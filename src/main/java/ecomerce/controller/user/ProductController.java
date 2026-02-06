package ecomerce.controller.user;

import ecomerce.dto.response.ProductResponse;
import ecomerce.service.ProductService;
import ecomerce.dto.request.ProductSearchCriteria;
import ecomerce.dto.response.ProductSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String getProducts(
            @ModelAttribute ProductSearchCriteria criteria,
            Pageable pageable,
            Model model,
            @RequestHeader(value = "HX-Request", required = false) boolean isAjax
    ) {
        // Gọi qua API RestController thay vì Service trực tiếp
        Page<ProductSummaryResponse> productPage = productService.getProducts(criteria, pageable);
        model.addAttribute("products", productPage);

        if (isAjax) {
            // Nếu là AJAX, chỉ trả về đoạn HTML danh sách sản phẩm
            return "user/products :: productListFragment";
        }

        // Nếu load thường, trả về cả trang web
        return "user/products";
    }

    @GetMapping("/{id}")
    public String getProductDetail(@PathVariable int id, Model model) {
        ProductResponse product = productService.getProductById(id);
        if (product == null) {
            return "error/404";
        }
        model.addAttribute("product", product);

        // Lấy sản phẩm cùng series (nếu có series)
        if (product.getSeries() != null) {
            ProductSearchCriteria criteria = new ProductSearchCriteria();
            criteria.setSeriesIds(List.of(product.getSeries().getId()));

            // Lấy 10 sản phẩm cùng series (để có thể slide), loại trừ sản phẩm hiện tại
            Pageable pageable = PageRequest.of(0, 11);
            Page<ProductSummaryResponse> seriesProductsPage = productService.getProducts(criteria, pageable);

            // Loại trừ sản phẩm hiện tại
            java.util.List<ProductSummaryResponse> seriesProducts = seriesProductsPage.getContent().stream()
                    .filter(p -> p.getId() != id)
                    .limit(10)
                    .toList();

            model.addAttribute("seriesProducts", seriesProducts);
            model.addAttribute("seriesName", product.getSeries().getName());
        }

        return "user/productDetail";
    }
}
