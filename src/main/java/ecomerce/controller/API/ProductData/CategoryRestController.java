package ecomerce.controller.API.ProductData;
import ecomerce.dto.response.CategoryResponse;
import ecomerce.entity.Category;
import ecomerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ecomerce.dto.response.ApiResponse;
import java.util.List;

@RestController
@RequestMapping("/api/product-data")
@RequiredArgsConstructor
public class CategoryRestController {
    private final CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public ApiResponse<List<CategoryResponse>> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> categoryResponses = categories.stream()
                .map(CategoryResponse::toDTO)
                .toList();
        return ApiResponse.success(categoryResponses);
    }

}
