package com.electronics.store.electronocs_Store.services;

import com.electronics.store.electronocs_Store.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    //Create
    public List<CategoryDto> createCategory(List<CategoryDto> categoryDto);
    //update
    public CategoryDto updateCategory(CategoryDto categoryDto,String categoryKid );
    //delete
    public String deleteCategoryById(String categoryKid );
    //get All Category
    public List<CategoryDto> getAllCategory();
    //get Category By ID
    public CategoryDto getCategoryById(String categoryKid);
}
