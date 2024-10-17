package com.electronics.store.electronocs_Store.services.impl;

import com.electronics.store.electronocs_Store.dto.UserDTO;
import com.electronics.store.electronocs_Store.entity.User;
import com.electronics.store.electronocs_Store.repository.UserRepository;
import com.electronics.store.electronocs_Store.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDto) {
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
        List<User> user = userRepository.findAll();
        List<UserDTO> dtoList = user.stream().map(user1 -> entityTODto(user1)).collect(Collectors.toList());
        return dtoList;
    }


    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found with given Email"));
    return entityTODto(user);
    }

    @Override
    public UserDTO getUserById(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found with given ID"));

        return entityTODto(user);
    }

    @Override
    public List<UserDTO> searchUser(String keyword) {
        List<User> userList = userRepository.findByNameContaining(keyword);

        return userList.stream().map(user -> entityTODto(user)).collect(Collectors.toList());
    }
    private UserDTO entityTODto(User saveUser) {
//        UserDTO userDTO = UserDTO.builder().userid(saveUser.getUserid())
//                .name(saveUser.getName())
//                .email(saveUser.getEmail())
//                .password(saveUser.getPassword())
//                .about(saveUser.getAbout())
//                .gender(saveUser.getGender())
//                .imageName(saveUser.getImageName())
//                .build();
        return modelMapper.map(saveUser,UserDTO.class);
    }

    private User dtoToEntity(UserDTO userDto) {
//        User user = User.builder().userid(userDto.getUserid())
//            .name(userDto.getName())
//            .email(userDto.getEmail())
//            .password(userDto.getPassword())
//            .about(userDto.getAbout())
//            .gender(userDto.getGender())
//            .imageName(userDto.getImageName())
//            .build();

        return modelMapper.map(userDto,User.class);
    }
}
