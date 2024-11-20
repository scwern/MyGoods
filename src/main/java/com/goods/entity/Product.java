package com.goods.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    @NotBlank(message = "Имя не может быть пустым")
    @Size(max = 255, message = "Имя не может превышать 255 символов")
    private String name;

    @Size(max = 4096, message = "Описание товара не может превышать 4096 символов")
    private String description;

    @Min(value = 0, message = "Цена не может быть меньше 0")
    private double price = 0.0;

    private boolean available = false;

}
