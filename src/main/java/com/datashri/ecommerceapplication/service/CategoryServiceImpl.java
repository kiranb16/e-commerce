package com.datashri.ecommerceapplication.service;

import com.datashri.ecommerceapplication.Exception.ApiException;
import com.datashri.ecommerceapplication.Exception.ResponseStatusNOtFoundException;
import com.datashri.ecommerceapplication.dto.CategoryDto;
import com.datashri.ecommerceapplication.dto.CategoryResponce;
import com.datashri.ecommerceapplication.model.Category;
import com.datashri.ecommerceapplication.repo.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CategoryResponce getAllCategories() throws ApiException {

        List<Category> categories = categoryRepository.findAll();

        if (categories.isEmpty()) {
            throw new ApiException("No category created....");
        }

        List<CategoryDto> categoryList = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());

        CategoryResponce response = new CategoryResponce();
        response.setCategories(categoryList);

        return response;
    }


    @Override
    public CategoryDto createCategory(CategoryDto category) throws ApiException {

        Category savedCategory =
                categoryRepository.findBycategoryName(category.getCategoryName());

        if (savedCategory != null) {
            throw new ApiException(
                    "Category by this name already present !!!! "
                            + savedCategory.getCategoryId());
        }

        // Convert DTO to Entity
        Category categoryEntity =
                modelMapper.map(category, Category.class);

        // Save Entity
        Category saved =
                categoryRepository.save(categoryEntity);

        // Convert Entity back to DTO
        return modelMapper.map(saved, CategoryDto.class);
    }

    @Override
    public CategoryDto deleteCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusNOtFoundException(
                        HttpStatus.NOT_FOUND,
                        "Category not found for id: " + categoryId
                ));
        CategoryDto deletedCategory = modelMapper.map(category, CategoryDto.class);

        categoryRepository.delete(category);

        return deletedCategory;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {

        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusNOtFoundException(
                        HttpStatus.NOT_FOUND,
                        "Category not found for id: "+ categoryId
                ));

        existingCategory.setCategoryName(categoryDto.getCategoryName());

        Category updatedCategory= categoryRepository.save(existingCategory);
        return modelMapper.map(updatedCategory,  CategoryDto.class);
    }
}