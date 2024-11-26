package com.example.amazonclone.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Product {

    @NotEmpty(message = "Product ID must not be empty")
    private String id;

    @NotEmpty(message = "Product Name must not be empty")
    @Size(min = 3,message = "Product name must be at least 3 characters long")
    private String name;

    @NotNull(message = "Product price must not be empty")
    @Positive(message = "Product price must be positive number")
    private double price;

    @NotEmpty(message = "Category ID must not be empty")
    private String categoryID;

    private LocalDate dateAdded;

}
