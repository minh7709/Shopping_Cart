package ecomerce.controller.user;

import ecomerce.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import ecomerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/product")
class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/search")
    public java.util.List<Product> searchProducts(@RequestParam String name) {
        return productService.findProductsByName(name);
    }
}
