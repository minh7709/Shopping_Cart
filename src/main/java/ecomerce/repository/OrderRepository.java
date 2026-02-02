package ecomerce.repository;

import ecomerce.entity.Order;
import ecomerce.entity.User;
import ecomerce.entity.type.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUser(User user);
    List<Order> findByUserId(Integer userId);
    Page<Order> findByUserId(Integer userId, Pageable pageable);
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    Page<Order> findAll(Pageable pageable);
}

