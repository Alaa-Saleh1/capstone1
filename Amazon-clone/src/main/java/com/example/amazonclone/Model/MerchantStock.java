package com.example.amazonclone.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {

    @NotEmpty(message = "Merchant Stock ID must not be empty")
    private String id;

    @NotEmpty(message = "ID must not be empty")
    private String productId;

    @NotEmpty(message = "Merchant ID must not be empty")
    private String merchantId;

    @NotNull(message = "Stock quantity must not be null")
    @Min(value = 10, message = "Stock quantity must be at least 10")
    private int stock ;
}
