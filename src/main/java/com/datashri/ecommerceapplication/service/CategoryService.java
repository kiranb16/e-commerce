package com.datashri.ecommerceapplication.service;

import com.datashri.ecommerceapplication.Exception.ApiException;
import com.datashri.ecommerceapplication.dto.CategoryDto;
import com.datashri.ecommerceapplication.dto.CategoryResponce;

public interface CategoryService {
    CategoryResponce getAllCategories() throws ApiException;

    CategoryDto createCategory(CategoryDto categoryDto) throws ApiException;

    CategoryDto deleteCategory(Long categoryId);

    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);
}
