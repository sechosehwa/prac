package com.mysite.sbb.user;

import lombok.Data;

@Data
public class LoginDTO {
    private String phoneNumber;
    private String password;
}
