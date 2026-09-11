package com.datashri.ecommerceapplication.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity(name="categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue()
    private Long categoryId;
    @NotBlank(message = "Category name is required")
    @Size(min = 2, max = 25, message = "Category name must be between 2 and 5 characters")
    private String categoryName;
}
