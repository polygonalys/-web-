package com.example.gakuboschedules.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor 
@Setter
@Getter
public class User {
    private Integer id; // 主キー、整数型で連番
    
    @NotNull
    @Email(message = "形式が不正です。")
    private String userEmail; // Eメール、重複は可能で空にしない

    @NotNull
    @Min(value = 8, message = "パスワードは８文字以上で入力してください。")
    private String userPassword; // パスワード、重複は可能で空にしない。また、長さは８文字以上

    private String userName; // 名前、重複は可能で空の場合は田中太郎という名前になる
}
