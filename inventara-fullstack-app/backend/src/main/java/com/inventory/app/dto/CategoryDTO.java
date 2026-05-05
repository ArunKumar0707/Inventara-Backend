package com.inventory.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {
    private Long id;

    @NotBlank(message = "Category name must not be blank")
    private String name;
}
