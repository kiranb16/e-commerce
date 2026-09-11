package com.datashri.ecommerceapplication.service;

import com.datashri.ecommerceapplication.Exception.ApiException;
import com.datashri.ecommerceapplication.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    void createCategory(Category category) throws ApiException;

    String deleteCategory(Long categoryId);

    Category updateCategory(Category category, Long categoryId);
}
