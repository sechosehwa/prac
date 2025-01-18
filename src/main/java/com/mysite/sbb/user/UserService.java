package com.mysite.sbb.user;

//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void create(UserDTO userDTO){
        User_User user = new User_User();
        user.setUserName(userDTO.getUserName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
//        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setPassword(userDTO.getPassword());
        user.setSex(userDTO.isSex());
        this.userRepository.save(user);
    }
}
