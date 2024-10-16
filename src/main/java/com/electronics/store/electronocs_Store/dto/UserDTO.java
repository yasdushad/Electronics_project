package com.electronics.store.electronocs_Store.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private String userid ;

    private String name;

    private String email;

    private String password;

    private String gender ;

    private String about;

    private String imageName;
}
