package com.example.amazonclone.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {

    @NotEmpty(message = "Category ID must not be empty")
    private String id;

    @NotEmpty(message = "Category name must not be empty")
    @Size(min = 3,message = "Category name must be at least 3 characters long")
    private String name;
}
