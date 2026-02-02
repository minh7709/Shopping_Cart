package ecomerce.controller.API.ProductData;

import ecomerce.entity.Brand;
import ecomerce.dto.response.BrandResponse;
import ecomerce.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ecomerce.dto.response.ApiResponse;
import java.util.List;

@RestController
@RequestMapping("/api/product-data")
@RequiredArgsConstructor
public class BrandRestController {
    private final BrandRepository brandRepository;

    @GetMapping("/brands")
    public ApiResponse<List<BrandResponse>> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        List<BrandResponse> brandResponses = brands.stream()
                .map(BrandResponse::toDTO)
                .toList();
        return ApiResponse.success(brandResponses);
    }

}
