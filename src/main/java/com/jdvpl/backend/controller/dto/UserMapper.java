package com.jdvpl.backend.controller.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.jdvpl.backend.repositories.entity.UserEntity;

@Mapper
public interface UserMapper {
    UserMapper mapper=Mappers.getMapper(UserMapper.class);
    @Mapping(source="password", target="pass")
    UserDto userEntityToUserDto(UserEntity userEntity);

    @Mapping(source="pass", target="password")
    UserEntity userDTOToUserEntity(UserDto user);

    
}
