package com.mysite.sbb.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    /* 회원가입 */
    @PostMapping
    public void save(@RequestBody UserDTO userDTO) {
        userService.create(userDTO);
    }

    /* 로그인 */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        User_User user = userService.login(loginDTO);

        if (user != null && passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            String token = userService.generateToken(loginDTO.getPhoneNumber());
            return ResponseEntity.ok(Map.of("token", token)); // 로그인 성공
        }
        else {
            return ResponseEntity.badRequest()
                    .body("400 Bad Request: 요청 본문이 유효하지 않음"); // 로그인 실패
        }
    }

    /* 로그아웃 */
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok("Logged out successfully");
    }
}
