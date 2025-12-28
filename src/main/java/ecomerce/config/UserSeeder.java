package ecomerce.config;

import ecomerce.entity.Cart;
import ecomerce.entity.User;
import ecomerce.repository.CartRepository;
import ecomerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserSeeder {

    @Bean
    CommandLineRunner initDatabase(@Autowired UserRepository userRepository,@Autowired CartRepository cartRepository) {
        return args -> {
            // 1. Tạo User Admin
            if (!userRepository.existsByUsername("admin")) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword("123"); // Sau này có Security sẽ mã hóa sau
                admin.setRole("ADMIN");
                admin.setFullName("Hoàng Quang Minh");
                userRepository.save(admin);
            }

            // 2. Tạo User thường
            if (!userRepository.existsByUsername("user")) {
                User user = new User();
                user.setUsername("user");
                user.setPassword("123");
                user.setRole("USER");
                user.setFullName("Nguyễn Văn A");
                userRepository.save(user);

            }

            System.out.println("Data Seeded: admin (ID: 1), user (ID: 2)");
        };
    }
}