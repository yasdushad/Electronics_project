package com.electronics.store.electronocs_Store.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private String categoryId;
    @NotBlank
    @Min(value=4,message ="title must be of minimum 4 character !!")
    private String title;
    @NotBlank(message = "Description is required")
    private String description;
//    @NotBlank(message = "Cover Image is required")
    private String coverImage;
}
