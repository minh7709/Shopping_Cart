package ecomerce.dto.request;
import lombok.Data;
@Data
public class UserRequest {
    private String address;
    private String phone;
    private String fullName;
    private String password;
    private String email;
    private String username;
}