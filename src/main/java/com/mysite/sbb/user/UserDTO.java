package com.mysite.sbb.user;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String userName;
    private String phoneNumber;
    private String password;
    private boolean sex;

    public boolean isSex() {
        return sex;
    }
}
