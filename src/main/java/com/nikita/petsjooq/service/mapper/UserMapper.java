package com.nikita.petsjooq.service.mapper;

import com.nikita.petsjooq.dto.UserDto;
import com.nikita.petsjooq.model.UserModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(UserModel user);

    UserModel toEntity(UserDto dto);

    List<UserDto> toDto(List<UserModel> userModels);
}