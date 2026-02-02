package ecomerce.dto.response;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.Builder;
import ecomerce.entity.type.Role;

@Builder
@Data

public class UserResponse {
    private LocalDateTime createdAt;
    private Role role;
    private String address;
    private String phone;
    private String fullName;
    private String email;
    private String username;
    private Integer id;
}