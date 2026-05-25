package com.ead.authuser.services;

import com.ead.authuser.dtos.UserDTO;
import com.ead.authuser.dtos.UserImageDTO;
import com.ead.authuser.dtos.UserPasswordDTO;
import com.ead.authuser.dtos.UserRegisterDTO;
import com.ead.authuser.dtos.UserUpdateDTO;
import com.ead.authuser.specifications.UserFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {
    UserDTO getUser(UUID id);

    void deleteUser(UUID id);

    UserDTO updateUser(UUID id, UserUpdateDTO userUpdateDTO);

    void updateUserPassword(UUID id, UserPasswordDTO userPasswordDTO);

    void updateImage(UUID id, UserImageDTO userImageDTO);

    UserDTO registerUser(UserRegisterDTO userRegisterDTO);

    Page<UserDTO> findAll(UserFilter userFilter, Pageable pageable);
}
