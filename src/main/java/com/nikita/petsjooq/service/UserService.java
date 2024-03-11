package com.nikita.petsjooq.service;

import com.nikita.petsjooq.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    UserDto getById(Long id);

    UserDto getByFullname(String fullname);

    Long add(UserDto dto);

    void changeById(Long id, UserDto userDto);

    void remove(Long id);
}