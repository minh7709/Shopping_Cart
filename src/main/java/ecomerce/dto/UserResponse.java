package ecomerce.dto;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String role;
    private String fullName;

    // Không trả về password vì lý do bảo mật
}

