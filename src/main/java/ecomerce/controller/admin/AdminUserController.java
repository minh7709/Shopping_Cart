package ecomerce.controller.admin;

import ecomerce.entity.User;
import ecomerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/user")

public class AdminUserController {
    @Autowired
    private UserService userService;

}
