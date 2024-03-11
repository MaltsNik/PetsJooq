package com.nikita.petsjooq.repository;

import com.nikita.petsjooq.dto.PetDto;
import com.nikita.petsjooq.model.PetModel;

import java.util.List;
import java.util.Optional;

public interface PetRepository {
    Optional<PetModel> createPet(PetModel petModel);

    Optional<PetModel> findById(Long id);

    Optional<List<PetModel>> getAll();

    Optional<PetModel> findByName(String name);

    Optional<PetModel> updatePet(PetModel petModel);

    void deleteById(Long id);
}