package com.ead.authuser.controllers;

import com.ead.authuser.api.response.PageResponse;
import com.ead.authuser.dtos.ResponseDTO;
import com.ead.authuser.dtos.UserDTO;
import com.ead.authuser.dtos.UserImageDTO;
import com.ead.authuser.dtos.UserPasswordDTO;
import com.ead.authuser.dtos.UserUpdateDTO;
import com.ead.authuser.services.UserService;
import com.ead.authuser.specifications.UserFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> getUsers(UserFilter userFilter, @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable) {
        PageResponse<UserDTO> pageResponse = userService.findAll(userFilter, pageable);
        return ResponseDTO.ok("Users listed successfully", pageResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable(value = "id") UUID id) {
        UserDTO userDTO = userService.getUser(id);
        return ResponseDTO.ok("User found successfully", userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") UUID id) {
        userService.deleteUser(id);
        return ResponseDTO.ok("User deleted successfully", id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(value = "id") UUID id, @RequestBody @Validated UserUpdateDTO userUpdateDTO) {
        UserDTO userDTO = userService.updateUser(id, userUpdateDTO);
        return ResponseDTO.ok("User updated successfully", userDTO);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<?> updatePassword(@PathVariable(value = "id") UUID id, @RequestBody @Validated UserPasswordDTO userPasswordDTO) {
        userService.updateUserPassword(id, userPasswordDTO);
        return ResponseDTO.ok("Password updated successfully", id);
    }

    @PutMapping("/{id}/image")
    public ResponseEntity<?> updateImage(@PathVariable(value = "id") UUID id, @RequestBody @Validated UserImageDTO userImageDTO) {
        userService.updateImage(id, userImageDTO);
        return ResponseDTO.ok("Image updated successfully", id);
    }
}
