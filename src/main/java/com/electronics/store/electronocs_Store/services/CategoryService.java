package com.electronics.store.electronocs_Store.services;

import com.electronics.store.electronocs_Store.dto.CategoryDto;
import com.electronics.store.electronocs_Store.dto.PageableResponse;

import java.util.List;

public interface CategoryService {
    //Create
    public List<CategoryDto> createCategory(List<CategoryDto> categoryDto);
    //update
    public CategoryDto updateCategory(CategoryDto categoryDto,String categoryKid );
    //delete
    public String deleteCategoryById(String categoryKid );
    //get All Category
    public PageableResponse<CategoryDto> getAllCategory(int pageNumber, int pageSize, String sortBy, String sortDir);
    //get Category By ID
    public CategoryDto getCategoryById(String categoryKid);
    public List<CategoryDto> fetchAllCategoryList();
}
