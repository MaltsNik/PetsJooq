package com.nikita.petsjooq.repository.impl;

import com.nikita.petsjooq.example.domain.tables.Pets;
import com.nikita.petsjooq.model.PetModel;
import com.nikita.petsjooq.model.UserModel;
import com.nikita.petsjooq.repository.PetRepository;
import com.nikita.petsjooq.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PetRepositoryImpl implements PetRepository {
    private final DSLContext context;
    private final UserRepository userRepository;
    private final Pets pet = Pets.PETS;

    @Override
    @Transactional
    public Optional<PetModel> createPet(PetModel model) {
        UserModel user = userRepository
                .findBiId(model.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found: " + model.getId()));
        return context.insertInto(pet, pet.NAME, pet.AGE, pet.BREED, pet.USER_ID)
                .values(model.getName(), model.getAge(), model.getBreed(), user.getId())
                .returning(pet.ID)
                .fetchOptionalInto(PetModel.class);

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PetModel> findById(Long id) {
        return Optional.ofNullable(context.selectFrom(pet)
                .where(pet.ID.eq(id))
                .fetchOptionalInto(PetModel.class)
                .orElseThrow(() -> new RuntimeException("pet not found")));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PetModel>> getAll() {
        return Optional.of(context.selectFrom(pet)
                .fetch()
                .stream()
                .map(record -> record.into(PetModel.class))
                .collect(Collectors.toList()));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PetModel> findByName(String name) {
        return Optional.ofNullable(context.selectFrom(pet)
                .where(pet.NAME.eq(name))
                .fetchOptionalInto(PetModel.class)
                .orElseThrow(() -> new RuntimeException("pet not found")));
    }

    @Override
    @Transactional
    public Optional<PetModel> updatePet(PetModel petModel) {
        return Optional.ofNullable(context.update(pet)
                .set(pet.NAME, petModel.getName())
                .set(pet.AGE, petModel.getAge())
                .set(pet.BREED, petModel.getBreed())
                .where(pet.ID.eq(petModel.getId()))
                .returning()
                .fetchOptionalInto(PetModel.class)
                .orElseThrow(() -> new RuntimeException("Error updating entity: " + petModel.getId())));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        context.deleteFrom(pet)
                .where(pet.ID.eq(id))
                .execute();
    }
}