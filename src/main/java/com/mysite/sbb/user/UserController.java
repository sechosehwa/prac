package com.mysite.sbb.user;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    /* 회원가입 */
    @PostMapping
    public void save(@RequestBody UserDTO userDTO) {
        userService.create(userDTO);
    }
}
