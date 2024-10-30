package com.electronics.store.electronocs_Store.services.impl;

import com.electronics.store.electronocs_Store.dto.CategoryDto;
import com.electronics.store.electronocs_Store.dto.PageableResponse;
import com.electronics.store.electronocs_Store.dto.UserDTO;
import com.electronics.store.electronocs_Store.entity.Category;
import com.electronics.store.electronocs_Store.entity.User;
import com.electronics.store.electronocs_Store.exception.ResourceNotFoundException;
import com.electronics.store.electronocs_Store.helper.Helper;
import com.electronics.store.electronocs_Store.repository.CategoryRepo;
import com.electronics.store.electronocs_Store.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoryDto> createCategory(List<CategoryDto> categoryDto) {
        List<CategoryDto> createdCategories = new ArrayList<>();
        for (CategoryDto categoryDto1  : categoryDto) {
            String categoryKid = UUID.randomUUID().toString();
            //dto to entity
            Category category = modelMapper.map(categoryDto1, Category.class);
            //save the category
            category.setCategoryId(categoryKid);
            Category savedCategory = categoryRepo.save(category);
            //Convert Entity to dto
            CategoryDto createdCategoryDto2 = modelMapper.map(savedCategory,CategoryDto.class);
            createdCategories.add(createdCategoryDto2);
        }
        return createdCategories;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, String categoryKid) {
        Category dtoToEntity = modelMapper.map(categoryDto,Category.class);
        Category existingData = categoryRepo.findById(categoryKid)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with given id "));
        existingData.setTitle(dtoToEntity.getTitle());
        existingData.setDescription(dtoToEntity.getDescription());
        existingData.setCoverImage(dtoToEntity.getCoverImage());
        CategoryDto updatedRecord = modelMapper.map(existingData,CategoryDto.class);
        return updatedRecord;
    }

    @Override
    public String deleteCategoryById(String categoryKid) {
        Category category = categoryRepo.findById(categoryKid).orElseThrow(() -> new ResourceNotFoundException("Category not found with given id "));

        //delete User
        categoryRepo.delete(category);
        return "Category Deleted Successfully";
    }

    @Override
    public PageableResponse<CategoryDto> getAllCategory(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<Category> page = categoryRepo.findAll(pageable);
        PageableResponse<CategoryDto> response = Helper.getpageableResponse(page,CategoryDto.class);
        return response;
    }

    @Override
    public List<CategoryDto> fetchAllCategoryList(){
        List<Category> listCategory = categoryRepo.findAll();
        List<CategoryDto> listCategoryDto = listCategory.stream().map(object -> modelMapper.map(object,CategoryDto.class)).collect(Collectors.toList());
        return listCategoryDto;
    }
    @Override
    public CategoryDto getCategoryById(String categoryKid) {
        Category Category = categoryRepo.findById(categoryKid).orElseThrow(() -> new ResourceNotFoundException("Category not found with given ID"));
        return modelMapper.map(Category,CategoryDto.class);
    }
}
