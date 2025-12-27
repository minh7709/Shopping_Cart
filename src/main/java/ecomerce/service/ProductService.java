
package ecomerce.service;
import ecomerce.entity.Product;
import ecomerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // select all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // select product by id
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    // add new product
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // update product
    public Product updateProduct(Long id, Product productDetails) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        existingProduct.setName(productDetails.getName());
        existingProduct.setPrice(productDetails.getPrice());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setImageUrl(productDetails.getImageUrl());
        existingProduct.setStockQuantity(productDetails.getStockQuantity());
        return productRepository.save(existingProduct);
    }
    // delete product
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // find products by name containing ignore case
    public List<Product> findProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
}

