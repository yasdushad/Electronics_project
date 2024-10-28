package com.electronics.store.electronocs_Store.services.impl;

import com.electronics.store.electronocs_Store.dto.PageableResponse;
import com.electronics.store.electronocs_Store.dto.UserDTO;
import com.electronics.store.electronocs_Store.entity.User;
import com.electronics.store.electronocs_Store.exception.ResourceNotFoundException;
import com.electronics.store.electronocs_Store.helper.Helper;
import com.electronics.store.electronocs_Store.repository.UserRepository;
import com.electronics.store.electronocs_Store.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given id "));
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
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given id "));

        //delete User
        userRepository.delete(user);
    }

    @Override
    public PageableResponse<UserDTO> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {
//        Sort sort = Sort.by(sortBy) ;
        Sort sort = (sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<User> page = userRepository.findAll(pageable);
//        List<User> user = page.getContent();
//        List<UserDTO> dtoList = user.stream().map(user1 -> entityTODto(user1)).collect(Collectors.toList());
//        PageableResponse<UserDTO> response = new PageableResponse<>();
//        response.setContent(dtoList);
//        response.setPageNumber(page.getNumber());
//        response.setPageSize(page.getSize());
//        response.setTotalElements(page.getTotalElements());
//        response.setLastPage(page.isLast());
        PageableResponse<UserDTO> response = Helper.getpageableResponse(page,UserDTO.class);

        return response;
    }


    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found with given Email"));
    return entityTODto(user);
    }

    @Override
    public UserDTO getUserById(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with given ID"));

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
