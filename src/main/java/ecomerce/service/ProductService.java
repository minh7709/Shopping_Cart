package ecomerce.service;

import ecomerce.dto.request.ProductSearchCriteria;
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

    @Transactional(readOnly = true)
    public Page<ProductSummaryResponse> getProducts(ProductSearchCriteria criteria, Pageable pageable) {
        Specification<Product> spec = ProductSpecification.getProducts(criteria);
        Page<Product> productPage = productRepository.findAll(spec, pageable);
        return productPage.map(ProductSummaryResponse::toDTO);
    }
    // 1. Lấy sản phẩm HOT (Sắp xếp theo số lượng bán)
    public Page<ProductSummaryResponse> getHotProducts(int limit) {
        Pageable pageable = PageRequest.of(0, limit, Sort.by("soldQuantity").descending());
        return productRepository.findAll(pageable).map(ProductSummaryResponse::toDTO);
    }

    // 2. Lấy sản phẩm theo Danh mục (Ví dụ: Goods)
    public Page<ProductSummaryResponse> getProductsByCategory(Integer categoryId, int limit) {
        Pageable pageable = PageRequest.of(0, limit, Sort.by("createdAt").descending());
        return productRepository.findByCategoryId(categoryId, pageable).map(ProductSummaryResponse::toDTO);
    }

    // 3. Lấy sản phẩm Có sẵn (In Stock)
    public Page<ProductSummaryResponse> getInStockProducts(int limit) {
        Pageable pageable = PageRequest.of(0, limit, Sort.by("createdAt").descending());
        return productRepository.findByStockQuantityGreaterThan(0, pageable).map(ProductSummaryResponse::toDTO);
    }

}
