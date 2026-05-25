package com.ead.authuser.mapper;

import com.ead.authuser.dtos.UserDTO;
import com.ead.authuser.dtos.UserRegisterDTO;
import com.ead.authuser.dtos.UserUpdateDTO;
import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.ead.authuser.models.UserModel;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", imports = {UserStatus.class, UserType.class})
public interface UserMapper {

    UserDTO toDTO(UserModel userModel);

    @Mapping(target = "status", expression = "java(UserStatus.ACTIVE)")
    @Mapping(target = "type", expression = "java(UserType.STUDENT)")
    UserModel toModel(UserRegisterDTO userRegisterDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromUpdateDTO(UserUpdateDTO userUpdateDTO, @MappingTarget UserModel userModel);
}
