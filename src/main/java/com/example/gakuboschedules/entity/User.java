package com.example.gakuboschedules.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class User {
    public User(String userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }

    @NotNull 
    private Integer id; // 主キー、整数型で連番

    @NotNull 
    private String userId;

    @NotNull
    @Min(value = 8, message = "パスワードは８文字以上で入力してください。")
    private String userPassword; // パスワード、重複は可能で空にしない。また、長さは８文字以上

    private String userRole;
}
