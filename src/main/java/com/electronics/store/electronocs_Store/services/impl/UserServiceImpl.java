package com.electronics.store.electronocs_Store.services.impl;

import com.electronics.store.electronocs_Store.dto.UserDTO;
import com.electronics.store.electronocs_Store.entity.User;
import com.electronics.store.electronocs_Store.repository.UserRepository;
import com.electronics.store.electronocs_Store.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO creaUser(UserDTO userDto) {
        //generate unique id in String format
        String userId = UUID.randomUUID().toString();
        userDto.setUserid(userId);
    // dto -> entity
        User user = dtoToEntity(userDto)  ;
        User saveUser = userRepository.save(user);
    // entity -> dto
        UserDTO userDTO = entityTODto(saveUser);
        return userDTO;
    }



    @Override
    public UserDTO updateUser(UserDTO userdto, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with given id "));
        user.setName(userdto.getName());
        user.setEmail(userdto.getEmail());
        user.setAbout(userdto.getAbout());
        user.setImageName(userdto.getImageName());
        user.setGender(userdto.getGender());
        user.setPassword(userdto.getPassword());

        //save Data
        User updateUser = userRepository.save(user);
        UserDTO updateDTO =entityTODto(updateUser);
        return updateDTO;
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with given id "));

        //delete User
        userRepository.delete(user);
    }

    @Override
    public List<UserDTO> getAllUser() {
        return List.of();
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return null;
    }

    @Override
    public UserDTO getUserById(String userId) {
        return null;
    }

    @Override
    public List<UserDTO> searchUser(String keyword) {
        return List.of();
    }
    private UserDTO entityTODto(User saveUser) {
        UserDTO userDTO = UserDTO.builder().userid(saveUser.getUserid())
                .name(saveUser.getName())
                .email(saveUser.getEmail())
                .password(saveUser.getPassword())
                .about(saveUser.getAbout())
                .gender(saveUser.getGender())
                .imageName(saveUser.getImageName())
                .build();
        return userDTO;
    }

    private User dtoToEntity(UserDTO userDto) {
        User user = User.builder().userid(userDto.getUserid())
            .name(userDto.getName())
            .email(userDto.getEmail())
            .password(userDto.getPassword())
            .about(userDto.getAbout())
            .gender(userDto.getGender())
            .imageName(userDto.getImageName())
            .build();

        return user;
    }
}
