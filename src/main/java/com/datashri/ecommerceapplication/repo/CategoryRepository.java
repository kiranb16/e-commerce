package com.datashri.ecommerceapplication.repo;

import com.datashri.ecommerceapplication.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category, Long> {
}
