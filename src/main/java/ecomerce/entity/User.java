package ecomerce.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String role;

    @Column(name = "full_name")
    private String fullName;
}
