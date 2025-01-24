package com.mysite.sbb.user;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Getter
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // JWT 암호화 키

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void create(UserDTO userDTO){
        User_User user = new User_User();
        user.setUserName(userDTO.getUserName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//        user.setPassword(userDTO.getPassword());
        user.setSex(userDTO.isSex());
        this.userRepository.save(user);
    }

    @Transactional
    public User_User login(LoginDTO loginDTO) {
        Optional<User_User> user = userRepository.findByPhoneNumber(loginDTO.getPhoneNumber());
        User_User realUser = null;
        if (user.isPresent()) {
            realUser = user.get();
        }
        return realUser;
    }

    public String generateToken(String phoneNumber) {
        return Jwts.builder()
                .setSubject(phoneNumber) // 사용자 식별 정보
                .setIssuedAt(new Date()) // 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1일 만료
                .signWith(key) // 서명
                .compact();
    }
}
