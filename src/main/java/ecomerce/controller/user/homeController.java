package ecomerce.controller.user;

import ecomerce.service.ProductService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor

public class homeController {
    final ProductService productService;
    @GetMapping
    public String homePage(Model model) {
        // 1. Lấy 9 sản phẩm HOT (Bán chạy nhất) - hiển thị 3 cột, có slider
        model.addAttribute("hotProducts", productService.getHotProducts(6).getContent());

        // 2. Lấy 9 sản phẩm Blind Box - hiển thị 3 cột, có slider
        int blindboxCategoryId = 4;
        model.addAttribute("blindboxProducts", productService.getProductsByCategory(blindboxCategoryId, 6).getContent());

        // 3. Lấy 16 sản phẩm IN STOCK (Có sẵn) - hiển thị 8 cột x 2 hàng
        model.addAttribute("inStockProducts", productService.getInStockProducts(16).getContent());

        return "user/home";
    }

}
