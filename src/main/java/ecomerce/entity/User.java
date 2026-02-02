package ecomerce.entity;

import ecomerce.entity.type.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "full_name", length = 100)
    private String fullName;

    private String phone;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Enumerated(EnumType.STRING) // Quan trọng: Lưu tên enum dưới dạng String vào DB (customer/admin)
    @Column(columnDefinition = "ENUM('customer', 'admin') DEFAULT 'customer'")
    private Role role = Role.customer;

    @Column(name = "reset_token")
    private String resetToken;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "reset_token_expiry")
    private LocalDateTime resetTokenExpiry;
}