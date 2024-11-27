package com.example.amazonclone.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class User {

    @NotEmpty(message = "User ID must not be empty")
    private String id;

    @NotEmpty(message = "Username must not be empty")
    @Size(min = 5, message = "Username must be at least 5 characters long")
    private String username;


    @NotEmpty(message = "Password must not be empty")
    @Size(min = 6,message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{6,}$",message = "Password must have characters and digits")
    private String password;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email must be a valid email address")
    private String email;

    @NotEmpty(message = "Role must not be empty")
    @Pattern(regexp = "Admin|Customer",message = "Role must be either 'Admin' or 'Customer'.")
    private String role;

    @NotNull(message = "Balance must not be empty")
    @Positive(message = "balance must be a positive number")
    private double balance;

    private int counter;

    private boolean isPrime ;


}
