package ecomerce.repository;

import ecomerce.entity.Cart;
import ecomerce.entity.CartItem;
import ecomerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCartId(Long cartId);

    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);

    @Modifying
    @Transactional
    @Query("UPDATE CartItem c SET c.quantity = :quantity WHERE c.id = :id")
    void updateQuantityById(@Param("id") Long id, @Param("quantity") Integer quantity);

    @Modifying
    @Transactional
    void deleteByCartIdAndProductId(Long cartId, Long productId);

    @Modifying
    @Transactional
    void deleteByCartId(Long cartId);

    @Modifying
    @Transactional
    Long countByCartId(Long cartId);


}

