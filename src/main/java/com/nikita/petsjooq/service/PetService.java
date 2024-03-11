package com.nikita.petsjooq.service;

import com.nikita.petsjooq.dto.PetDto;

import java.util.List;

public interface PetService {
    List<PetDto> getAll();

    PetDto getById(Long id);

    PetDto add(PetDto dto);

    PetDto getByName(String name);

    void changeById(Long id, PetDto petDto);

    void remove(Long id);
}