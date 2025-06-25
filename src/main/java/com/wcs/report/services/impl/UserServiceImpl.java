package com.wcs.report.services.impl;

import com.wcs.report.entities.User;
import com.wcs.report.payload.UserDTO;
import com.wcs.report.repository.UserRepository;
import com.wcs.report.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        User createdUser = userRepository.save(user);
        return modelMapper.map(createdUser, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(
                user -> modelMapper.map(user, UserDTO.class)
        ).toList();
    }
}
