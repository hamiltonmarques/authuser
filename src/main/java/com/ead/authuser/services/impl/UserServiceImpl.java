package com.ead.authuser.services.impl;

import com.ead.authuser.dtos.UserDTO;
import com.ead.authuser.dtos.UserImageDTO;
import com.ead.authuser.dtos.UserPasswordDTO;
import com.ead.authuser.dtos.UserRegisterDTO;
import com.ead.authuser.dtos.UserUpdateDTO;
import com.ead.authuser.exception.notfound.UserNotFoundException;
import com.ead.authuser.exception.password.InvalidPasswordException;
import com.ead.authuser.exception.validation.EmailAlreadyExistsException;
import com.ead.authuser.exception.validation.UsernameAlreadyExistsException;
import com.ead.authuser.mapper.UserMapper;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.repositories.UserRepository;
import com.ead.authuser.services.UserService;
import com.ead.authuser.specifications.UserFilter;
import com.ead.authuser.specifications.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDTO getUser(UUID id) {
        UserModel userModel = findUser(id);
        return userMapper.toDTO(userModel);
    }

    @Override
    public void deleteUser(UUID id) {
        UserModel userModel = findUser(id);
        userRepository.delete(userModel);
    }

    @Override
    public UserDTO updateUser(UUID id, UserUpdateDTO userUpdateDTO) {
        UserModel userModel = findUser(id);

        userMapper.updateFromUpdateDTO(userUpdateDTO, userModel);
        userRepository.save(userModel);

        return userMapper.toDTO(userModel);
    }

    @Override
    public void updateUserPassword(UUID id, UserPasswordDTO userPasswordDTO) {
        UserModel userModel = findUser(id);

        if (!userPasswordDTO.getOldPassword().equals(userModel.getPassword())) {
            throw new InvalidPasswordException("Old password not match");
        }

        userModel.setPassword(userPasswordDTO.getPassword());
        userRepository.save(userModel);
    }

    @Override
    public void updateImage(UUID id, UserImageDTO userImageDTO) {
        UserModel userModel = findUser(id);
        userModel.setImageUrl(userImageDTO.getImageUrl());
        userRepository.save(userModel);
    }

    @Override
    public UserDTO registerUser(UserRegisterDTO userRegisterDTO) {
        if (userRepository.existsByUsername(userRegisterDTO.getUsername())) {
            throw new UsernameAlreadyExistsException();
        }

        if (userRepository.existsByEmail(userRegisterDTO.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        UserModel userModel = userMapper.toModel(userRegisterDTO);
        userRepository.save(userModel);

        return userMapper.toDTO(userModel);
    }

    @Override
    public Page<UserDTO> findAll(UserFilter userFilter, Pageable pageable) {
        Specification<UserModel> spec = UserSpecification.byFilter(userFilter);
        return userRepository.findAll(spec, pageable)
                .map(userMapper::toDTO);
    }

    private UserModel findUser(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }
}
