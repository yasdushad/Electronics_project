package com.electronics.store.electronocs_Store.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private String userid ;

    @Size(min = 3,max = 15,message = "Invalid Name")
    private String name;

//    @Email(message = "Invalid Email ")
    @Email(regexp = "\0\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b\n",message = "Invalid User Email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @Size(min = 4,max = 6,message = "Invalid Gender")
    private String gender ;

    @NotBlank(message = "write something about about yourself")
    private String about;

    private String imageName;
}
