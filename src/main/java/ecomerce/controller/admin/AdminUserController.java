package ecomerce.controller.admin;

import ecomerce.dto.ApiResponse;
import ecomerce.dto.UserRequest;
import ecomerce.dto.UserResponse;
import ecomerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {
    @Autowired
    private UserService userService;

    // Lấy tất cả users
    @GetMapping
    public ApiResponse<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ApiResponse.success("Users retrieved successfully", users);
    }

    // Lấy user theo ID
    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> ApiResponse.success("User retrieved successfully", user))
                .orElse(ApiResponse.error("User not found"));
    }

    // Thêm user mới
    @PostMapping
    public ApiResponse<UserResponse> addUser(@RequestBody UserRequest request) {
        UserResponse user = userService.addUser(request);
        return ApiResponse.success("User added successfully", user);
    }

    // Cập nhật user
    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest request) {
        UserResponse user = userService.updateUser(id, request);
        return ApiResponse.success("User updated successfully", user);
    }

    // Xóa user
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ApiResponse.success("User deleted successfully");
    }

    // Tìm user theo username
    @GetMapping("/search")
    public ApiResponse<UserResponse> getUserByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username)
                .map(user -> ApiResponse.success("User found", user))
                .orElse(ApiResponse.error("User not found with username: " + username));
    }
}
