package com.nikita.petsjooq.repository;

import com.nikita.petsjooq.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<Long> createUser(UserModel userModel);

    Optional<UserModel> findBiId(Long id);

    Optional<UserModel> findByFullname(String fullname);

    Optional<List<UserModel>> getAll();

    Optional<UserModel> updateUser(UserModel userModel);

    void deleteById(Long id);
}