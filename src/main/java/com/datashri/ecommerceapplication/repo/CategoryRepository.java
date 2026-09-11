package com.datashri.ecommerceapplication.repo;

import com.datashri.ecommerceapplication.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public interface CategoryRepository  extends JpaRepository<Category, Long> {

    Category findBycategoryName( String categoryName);
}
