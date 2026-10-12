package com.example.practice.Controller;

import com.example.practice.User;
import com.example.practice.dto.AssignRoleRequest;
import com.example.practice.dto.RegisterRequest;
import com.example.practice.service.JwtService;
import com.example.practice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest registerRequest) {

        return userService.register(registerRequest);

    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/assign-role")
    public User assignRole(@RequestBody AssignRoleRequest request) {
        return userService.assignRole(request.getUserId(), request.getRoleId());
    }

    @GetMapping("/token")
    public String generateToken() {
        return jwtService.generateToken("Aniket");
    }
}