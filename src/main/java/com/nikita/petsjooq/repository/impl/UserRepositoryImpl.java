package com.nikita.petsjooq.repository.impl;

import com.nikita.petsjooq.example.domain.tables.Users;
import com.nikita.petsjooq.model.UserModel;
import com.nikita.petsjooq.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final DSLContext context;
    private final Users user = Users.USERS;

    @Override
    @Transactional
    public Optional<Long> createUser(UserModel userModel) {

        return context.insertInto(user, user.FULLNAME, user.AGE)
                .values(userModel.getFullname(), userModel.getAge())
                .returning(user.ID)
                .fetchOptionalInto(UserModel.class)
                .orElseThrow(() -> new DataAccessException("Error inserting entity: " + userModel.getId())).getId().describeConstable();
    }

    @Override
    @Transactional
    public Optional<UserModel> updateUser(UserModel userModel) {
        return Optional.ofNullable(Objects.requireNonNull(context.update(user)
                        .set(user.FULLNAME, userModel.getFullname())
                        .set(user.AGE, userModel.getAge()).
                        where(user.ID.eq(userModel.getId()))
                        .returning()
                        .fetchOptionalInto(UserModel.class))
                .orElseThrow(() -> new RuntimeException("Error inserting entity: " + userModel.getId())));
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserModel> findBiId(Long id) {
        return Optional.ofNullable(context.selectFrom(user)
                .where(user.ID.eq(id))
                .fetchOptionalInto(UserModel.class)
                .orElseThrow(() -> new RuntimeException("")));
    }

    @Override
    public Optional<UserModel> findByFullname(String fullname) {
        return Optional.ofNullable(context.selectFrom(user)
                .where(user.FULLNAME.eq(fullname))
                .fetchOptionalInto(UserModel.class)
                .orElseThrow(() -> new RuntimeException("")));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<UserModel>> getAll() {
        return Optional.of(context.selectFrom(user)
                .fetch()
                .stream()
                .map(record -> record.into(UserModel.class))
                .collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        context.deleteFrom(user)
                .where(user.ID.eq(id))
                .execute();
    }
}