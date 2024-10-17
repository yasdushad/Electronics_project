package com.electronics.store.electronocs_Store.controller;

import com.electronics.store.electronocs_Store.dto.APIResponseMessage;
import com.electronics.store.electronocs_Store.dto.UserDTO;
import com.electronics.store.electronocs_Store.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

@Autowired
private UserService userService;

//create
@PostMapping
public ResponseEntity<UserDTO> createUsers(@Valid @RequestBody UserDTO userDTO){
    UserDTO userDTO1 = userService.createUser(userDTO);
    return new ResponseEntity<>(userDTO1, HttpStatus.CREATED);
}

//update
@PutMapping("/updateUser/{userId}")
public ResponseEntity<UserDTO> updateUser(@PathVariable String userId , @RequestBody UserDTO userDTO){
    UserDTO userDTO1 = userService.updateUser(userDTO,userId);
    return new ResponseEntity<>(userDTO1,HttpStatus.OK);
}
//delete
@DeleteMapping("/{userId}")
public ResponseEntity<APIResponseMessage> deleteUserById(@PathVariable String userId){
    userService.deleteUser(userId);
    APIResponseMessage message = APIResponseMessage
            .builder()
            .message("User is deleted Successfully")
            .success(true)
            .httpStatus(HttpStatus.OK)
            .build();
    return new ResponseEntity<>(message,HttpStatus.OK);
}
//get All
@GetMapping("/fetchAllUser")
public ResponseEntity<List<UserDTO>> fetchAllUser(){
    List<UserDTO> userDTOS = userService.getAllUser();
    return new ResponseEntity<>(userDTOS,HttpStatus.OK);
}
//get Single
@GetMapping("/getUserById/{userId}")
    public ResponseEntity<UserDTO> getSingleUserById(@PathVariable String userId){
    UserDTO userDTO = userService.getUserById(userId);
    return new ResponseEntity<>(userDTO,HttpStatus.OK);
}
    //get Single
@GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getSingleUserByEmail(@PathVariable String email){
        UserDTO userDTO = userService.getUserByEmail(email);
        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }
@GetMapping("/search/{keyword}")
    public ResponseEntity<List<UserDTO>> getUserBySearchUserName(@PathVariable String keyword){
        List<UserDTO> userDTO = userService.searchUser(keyword);
        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }
}

