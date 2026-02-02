package ecomerce.controller.user;

import ecomerce.dto.request.ProductSearchCriteria;
import ecomerce.dto.response.ProductSummaryResponse;
import ecomerce.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

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
        Page<ProductSummaryResponse> productPage = productService.getProducts(criteria, pageable);
        model.addAttribute("products", productPage);

        if (isAjax) {
            // Nếu là AJAX, chỉ trả về đoạn HTML danh sách sản phẩm
            // Cú pháp: "tên_file_html :: tên_fragment"
            return "user/products :: productListFragment";
        }

        // Nếu load thường, trả về cả trang web
        return "user/products";
    }
}
