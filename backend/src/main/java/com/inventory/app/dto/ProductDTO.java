package com.inventory.app.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;

    @NotBlank(message = "Product name must not be blank")
    private String name;

    @NotNull(message = "Price must not be null")
    @Min(value = 0, message = "Price must be zero or greater")
    private Double price;

    @NotNull(message = "Quantity must not be null")
    @Min(value = 0, message = "Quantity must be zero or greater")
    private Integer quantity;

    private Long categoryId;
    private String categoryName;
}
