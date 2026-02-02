package ecomerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ecomerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    Page<Product> findByCategoryId(Integer categoryId, Pageable pageable);
    Page<Product> findByStockQuantityGreaterThan(Integer stockQuantity, Pageable pageable);
}
