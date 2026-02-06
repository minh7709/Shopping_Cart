package ecomerce.service;

import ecomerce.dto.request.ProductSearchCriteria;
import ecomerce.dto.response.ProductResponse;
import ecomerce.dto.response.ProductSummaryResponse;
import ecomerce.repository.spec.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ecomerce.entity.Product;
import ecomerce.repository.ProductRepository;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    /**
     * Lấy danh sách sản phẩm với filter, sort, pagination
     * Tái sử dụng cho tất cả các trường hợp:
     * - Hot Products: sort=soldQuantity,desc
     * - Category Products: categoryIds=[1,2,...]
     * - In Stock: inStock=true
     * - Search: keyword=...
     * - Preorder: isPreorder=true/false
     */
    @Transactional(readOnly = true)
    public Page<ProductSummaryResponse> getProducts(ProductSearchCriteria criteria, Pageable pageable) {
        Specification<Product> spec = ProductSpecification.getProducts(criteria);
        Page<Product> productPage = productRepository.findAll(spec, pageable);
        return productPage.map(ProductSummaryResponse::toDTO);
    }

    /**
     * Lấy chi tiết sản phẩm theo ID
     */
    public ProductResponse getProductById(int id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            return null;
        }
        return ProductResponse.toDTO(product);
    }
}
