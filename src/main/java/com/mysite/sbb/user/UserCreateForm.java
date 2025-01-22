package com.mysite.sbb.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class UserCreateForm {
    @NotEmpty(message = "이름은 필수 항목입니다.")
    private String userName;

    @NotEmpty(message = "전화번호는 필수 항목입니다.")
    private String phoneNumber;

    @NotEmpty(message = "비밀번호는 필수 항목입니다.")
    private String password1;

    @NotEmpty(message = "비밀번호 확인은 필수 항목입니다.")
    private String password2;

    @NotEmpty(message = "성별은 필수 항목입니다.")
    private boolean sex;
}
