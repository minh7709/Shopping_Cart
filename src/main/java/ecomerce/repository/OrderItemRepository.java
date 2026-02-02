package ecomerce.repository;

import ecomerce.entity.Order;
import ecomerce.entity.OrderItem;
import ecomerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findByOrder(Order order);
    List<OrderItem> findByOrderId(Integer orderId);
    List<OrderItem> findByProduct(Product product);
    List<OrderItem> findByProductId(Integer productId);
}

