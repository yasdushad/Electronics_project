package com.electronics.store.electronocs_Store.services;

import com.electronics.store.electronocs_Store.dto.UserDTO;
import com.electronics.store.electronocs_Store.entity.User;

import java.util.List;

public interface UserService {

    //create
    UserDTO createUser(UserDTO userDto);

    //update
    UserDTO updateUser(UserDTO userdto,String userId) ;

    //delete
    void deleteUser (String userId);

    //getAll user
    List<UserDTO> getAllUser();

    // get single user by email
    UserDTO getUserByEmail(String email);

    //get single user by id
    UserDTO getUserById(String userId);

    //Search User
    List<UserDTO> searchUser(String keyword);
}
