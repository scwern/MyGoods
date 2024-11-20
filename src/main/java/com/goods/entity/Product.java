package com.goods.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя не может быть пустым")
    @Size(max = 255, message = "Имя не может превышать 255 символов")
    private String name;

    @Size(max = 4096, message = "Описание товара не может превышать 4096 символов")
    private String description;

    @Min(value = 0, message = "Цена не может быть меньше 0")
    private Double price;

    private Boolean available;

    public Product(String name, String description, Double price, Boolean available) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
    }

}
