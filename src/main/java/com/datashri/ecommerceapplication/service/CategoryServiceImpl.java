package com.datashri.ecommerceapplication.service;

import com.datashri.ecommerceapplication.Exception.ApiException;
import com.datashri.ecommerceapplication.Exception.ResponseStatusNOtFoundException;
import com.datashri.ecommerceapplication.model.Category;
import com.datashri.ecommerceapplication.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() throws ApiException {
        List<Category> categoryList =categoryRepository.findAll();
        if(categoryList.isEmpty()){
            throw new ApiException("No category created....");
        }

        return categoryList;
    }

    @Override
    public void createCategory(Category category) throws ApiException {

              Category  savedCategory =categoryRepository.findBycategoryName(category.getCategoryName());
              if(savedCategory!=null){
                  throw new ApiException("category by this name already present !!!!"+savedCategory.getCategoryId());
              }

        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusNOtFoundException(
                        HttpStatus.NOT_FOUND,
                        "Category not found for id: " + categoryId
                ));

        categoryRepository.delete(category);

        return "Category " + categoryId + " deleted successfully";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {

        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusNOtFoundException(
                        HttpStatus.NOT_FOUND,
                        "Category not found for id: "+ categoryId
                ));

        existingCategory.setCategoryName(category.getCategoryName());

        return categoryRepository.save(existingCategory);
    }
}