package com.example.gakuboschedules.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor 
public class ClassesSchedules {
    @NotNull(message = "学年は必須項目です")
    @Min(value = 1, message = "半角数字１～６を入力してください")
    @Max(value = 6, message = "半角数字１～６を入力してください")
    private Integer grade;

    @NotNull(message = "時間割は必須項目です")
    private Integer hour;

    private String schoolSubject;
}
