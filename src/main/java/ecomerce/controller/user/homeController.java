package ecomerce.controller.user;

import ecomerce.controller.API.ProductRestController;
import ecomerce.dto.request.ProductSearchCriteria;
import ecomerce.service.ProductService;
import ecomerce.service.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class homeController {
    private final ProductService productService;

    @GetMapping
    public String homePage(Model model) {
        // 1. Lấy 6 sản phẩm HOT (Bán chạy nhất) - sort theo soldQuantity giảm dần
        ProductSearchCriteria hotCriteria = new ProductSearchCriteria();
        model.addAttribute("hotProducts",
                productService.getProducts(hotCriteria,
                PageRequest.of(0, 6, Sort.by("soldQuantity").descending())).getContent());

        // 2. Lấy 6 sản phẩm Blind Box - filter theo categoryId
        ProductSearchCriteria blindboxCriteria = new ProductSearchCriteria();
        blindboxCriteria.setCategoryIds(List.of(4)); // Blind Box category ID
        model.addAttribute("blindboxProducts",
                productService.getProducts(blindboxCriteria,
                PageRequest.of(0, 6, Sort.by("createdAt").descending())).getContent());

        // 3. Lấy 16 sản phẩm IN STOCK (Có sẵn) - filter inStock = true
        ProductSearchCriteria inStockCriteria = new ProductSearchCriteria();
        inStockCriteria.setInStock(true);
        model.addAttribute("inStockProducts",
                productService.getProducts(inStockCriteria,
                PageRequest.of(0, 16, Sort.by("createdAt").descending())).getContent());

        return "user/home";
    }

}
