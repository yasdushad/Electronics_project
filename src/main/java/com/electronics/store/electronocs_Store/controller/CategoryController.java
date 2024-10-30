package com.electronics.store.electronocs_Store.controller;

import com.electronics.store.electronocs_Store.dto.*;
import com.electronics.store.electronocs_Store.services.CategoryService;
import com.electronics.store.electronocs_Store.services.FileService;
import com.electronics.store.electronocs_Store.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

@Autowired
private CategoryService categoryService;

@Autowired
private FileService fileService;
@Value("${user.profile.image.path}")
private String imageFilePath;
//create
@PostMapping
public ResponseEntity<List<CategoryDto>> createCategories(@RequestBody List<CategoryDto> categoryDto ){
    List<CategoryDto> category = categoryService.createCategory(categoryDto);
    return new ResponseEntity<>(category, HttpStatus.CREATED);
}

//update
@PutMapping("/updateCategory/{categoryId}")
public ResponseEntity<CategoryDto> updateCategory(@PathVariable String categoryId , @RequestBody CategoryDto category){
    CategoryDto category1 = categoryService.updateCategory(category,categoryId);
    return new ResponseEntity<>(category1,HttpStatus.OK);
}
//delete
@DeleteMapping("/{categoryId}")
public ResponseEntity<APIResponseMessage> deleteCategoryById(@PathVariable String categoryId){
    String messageDlt = categoryService.deleteCategoryById(categoryId);
    APIResponseMessage message = APIResponseMessage
            .builder()
            .message(messageDlt)
            .success(true)
            .httpStatus(HttpStatus.OK)
            .build();
    return new ResponseEntity<>(message,HttpStatus.OK);
}
//get All
@GetMapping("/fetchAllCategory")
public ResponseEntity<PageableResponse<CategoryDto>> fetchAllCategory(@RequestParam(value="pageNumber",defaultValue = "0",required = false)int pageNumber,
                                                              @RequestParam(value="pageSize",defaultValue = "10",required = false)int pageSize,
                                                              @RequestParam(value="sortBy",defaultValue = "name",required = false )String sortBy,
                                                              @RequestParam(value="sortDir",defaultValue = "ASC",required = false )String sortDir
){//page size means how many data show in page , pageNumber which page used //sortBy which field sorted required //sortDir ASC and desc
    PageableResponse<CategoryDto> category = categoryService.getAllCategory(pageNumber,pageSize,sortBy,sortDir);
    return new ResponseEntity<>(category,HttpStatus.OK);
}
//get Single
@GetMapping("/getCategoryById/{categoryId}")
    public ResponseEntity<CategoryDto> getSingleUserById(@PathVariable String categoryId){
    CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
    return new ResponseEntity<>(categoryDto,HttpStatus.OK);
}

}

