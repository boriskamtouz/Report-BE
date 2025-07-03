package com.wcs.report.services.impl;

import com.wcs.report.entities.SelectedCBElement;
import com.wcs.report.entities.User;
import com.wcs.report.mappers.UserMapper;
import com.wcs.report.payload.UserDTO;
import com.wcs.report.repository.SelectedCBElementRepository;
import com.wcs.report.repository.UserRepository;
import com.wcs.report.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SelectedCBElementRepository selectedCBElementRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, SelectedCBElementRepository selectedCBElementRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.selectedCBElementRepository = selectedCBElementRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        User createdUser = userRepository.save(user);
        return userMapper.toDTO(createdUser);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(
                user -> {
                    SelectedCBElement selectedCBElement = selectedCBElementRepository.findByUserId(user.getId());
                    user.setSelectedCBElement(selectedCBElement);
                    return userMapper.toDTO(user);
                }
        ).toList();
    }
}
