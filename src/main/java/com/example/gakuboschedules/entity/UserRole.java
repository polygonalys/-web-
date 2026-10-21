package com.example.gakuboschedules.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor 
@Setter 
@Getter 
public class UserRole {
    public UserRole(String userId, String userRole) {
        this.userId = userId;
        this.userRole = userRole;
    }

    @NotNull 
    private String userId;

    @NotNull 
    private String userRole;
}
