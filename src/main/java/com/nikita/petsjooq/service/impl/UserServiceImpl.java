package com.nikita.petsjooq.service.impl;

import com.nikita.petsjooq.dto.UserDto;
import com.nikita.petsjooq.repository.impl.UserRepositoryImpl;
import com.nikita.petsjooq.service.UserService;
import com.nikita.petsjooq.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepositoryImpl userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAll() {
        return userRepository.getAll().
                map(userMapper::toDto).
                orElseThrow(() -> new RuntimeException("Users not found"));
    }

    @Override
    public UserDto getById(Long id) {
        return userRepository.findBiId(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

    @Override
    public UserDto getByFullname(String fullname) {
        return userRepository.findByFullname(fullname)
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("User not found: " + fullname));
    }

    @Override
    @Transactional
    public Long add(UserDto dto) {
        return userRepository.createUser(userMapper.toEntity(dto)).orElseThrow(() -> new RuntimeException("Error while saving"));
    }

    @Override
    @Transactional
    public void changeById(Long id, UserDto userDto) {
        userRepository.updateUser(userMapper.toEntity(userDto)).orElseThrow(() -> new RuntimeException("Error while updating"));
    }

    @Override
    @Transactional
    public void remove(Long id) {
        userRepository.deleteById(id);
    }
}