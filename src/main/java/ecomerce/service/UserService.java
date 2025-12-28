package ecomerce.service;

import ecomerce.dto.UserRequest;
import ecomerce.dto.UserResponse;
import ecomerce.entity.User;
import ecomerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // Convert Entity to Response DTO
    private UserResponse convertToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setRole(user.getRole());
        response.setFullName(user.getFullName());
        return response;
    }

    // Convert Request DTO to Entity
    private User convertToEntity(UserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setFullName(request.getFullName());
        return user;
    }

    // Get all users
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Get user by id
    public Optional<UserResponse> getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::convertToResponse);
    }

    // Add new user
    public UserResponse addUser(UserRequest request) {
        // Check if username already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists: " + request.getUsername());
        }
        User user = convertToEntity(request);
        User savedUser = userRepository.save(user);
        return convertToResponse(savedUser);
    }

    // Update user
    public UserResponse updateUser(Long id, UserRequest request) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        existingUser.setUsername(request.getUsername());
        existingUser.setPassword(request.getPassword());
        existingUser.setRole(request.getRole());
        existingUser.setFullName(request.getFullName());

        User updatedUser = userRepository.save(existingUser);
        return convertToResponse(updatedUser);
    }

    // Delete user
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    // Find user by username
    public Optional<UserResponse> getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user != null ? Optional.of(convertToResponse(user)) : Optional.empty();
    }
}
