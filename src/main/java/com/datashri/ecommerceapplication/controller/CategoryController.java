package com.datashri.ecommerceapplication.controller;

import com.datashri.ecommerceapplication.Exception.ApiException;
import com.datashri.ecommerceapplication.dto.CategoryDto;
import com.datashri.ecommerceapplication.dto.CategoryResponce;
import com.datashri.ecommerceapplication.model.Category;
import com.datashri.ecommerceapplication.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {

        this.categoryService = categoryService;
    }

    @GetMapping("/api/public/categories")
  //  @RequestMapping(value="/api/public/categories", method = RequestMethod.GET)
    public ResponseEntity<CategoryResponce> getAllCategories() throws ApiException {
        CategoryResponce categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

   @PostMapping("/api/public/categories")
 //  @RequestMapping(value = "/api/public/categories", method= RequestMethod.POST)
    public ResponseEntity<String> createCategory( @Valid @RequestBody CategoryDto category) throws ApiException {
       categoryService.createCategory(category);
        return   new ResponseEntity<>( "Category created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDto> deleteCategory(
            @PathVariable Long categoryId) throws ApiException {

        CategoryDto deletedCategory =
                categoryService.deleteCategory(categoryId);

        return new ResponseEntity<>(deletedCategory, HttpStatus.OK);
    }

    @PutMapping("/api/admin/categories/{categoryId}")
    //@RequestMapping(value = "/api/admin/categories/{categoryId}", method=RequestMethod.PUT)
    public ResponseEntity<String> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable  Long categoryId){
        Category updatedCategory =categoryService.updateCategory(categoryDto,categoryId);
        return   new ResponseEntity<>( "Category updated successfully : "+updatedCategory.getCategoryName(), HttpStatus.CREATED);
    }
}
