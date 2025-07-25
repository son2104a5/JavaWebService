package data.ss13.controller;

import data.ss13.model.dto.request.LoginRequest;
import data.ss13.model.entity.User;
import data.ss13.repo.UserRepository;
import data.ss13.security.jwt.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class B4Controller {
    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<User> user = userRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (user.isPresent()) {
            String token = jwtProvider.generateToken(user.get().getUsername());
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai thông tin đăng nhập");
    }

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello(Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return ResponseEntity.ok("Xin chào " + username + "!");
    }
}
