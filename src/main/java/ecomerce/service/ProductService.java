
package ecomerce.service;

import ecomerce.dto.ProductRequest;
import ecomerce.dto.ProductResponse;
import ecomerce.entity.Product;
import ecomerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // select all products
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // select product by id
    public Optional<ProductResponse> getProductById(Long id) {
        return productRepository.findById(id)
                .map(this::convertToResponse);
    }

    // add new product
    public ProductResponse addProduct(ProductRequest request) {
        Product product = convertToEntity(request);
        Product savedProduct = productRepository.save(product);
        return convertToResponse(savedProduct);
    }

    // update product
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        existingProduct.setName(request.getName());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setImageUrl(request.getImageUrl());
        existingProduct.setStockQuantity(request.getStockQuantity());

        Product savedProduct = productRepository.save(existingProduct);
        return convertToResponse(savedProduct);
    }

    // delete product
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // find products by name containing ignore case
    public List<ProductResponse> findProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // get available products (stock > 0)
    public List<ProductResponse> getAvailableProducts() {
        return productRepository.findByStockQuantityGreaterThan(0).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Convert Entity to Response DTO
    private ProductResponse convertToResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setDescription(product.getDescription());
        response.setImageUrl(product.getImageUrl());
        response.setStockQuantity(product.getStockQuantity());
        response.setInStock(product.getStockQuantity() != null && product.getStockQuantity() > 0);
        return response;
    }

    // Convert Request DTO to Entity
    private Product convertToEntity(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setImageUrl(request.getImageUrl());
        product.setStockQuantity(request.getStockQuantity());
        return product;
    }
}

