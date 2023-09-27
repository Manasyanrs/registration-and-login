package com.example.servletapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterModel {
    private String fullName;
    private String email;
    private String password;
    private String confirmPassword;
}
