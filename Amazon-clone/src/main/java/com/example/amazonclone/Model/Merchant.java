package com.example.amazonclone.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant {

    @NotEmpty(message = "Merchant ID must not be empty")
    private String id;

    @NotEmpty(message = "Merchant name must not be empty")
    @Size(min = 3,message = "Merchant name must be at least 3 characters long")
    private String name;

    @NotEmpty(message = "City must not be empty")
    private String city;
}
