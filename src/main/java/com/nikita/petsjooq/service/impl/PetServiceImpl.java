package com.nikita.petsjooq.service.impl;

import com.nikita.petsjooq.dto.PetDto;
import com.nikita.petsjooq.model.PetModel;
import com.nikita.petsjooq.model.UserModel;
import com.nikita.petsjooq.repository.PetRepository;
import com.nikita.petsjooq.repository.UserRepository;
import com.nikita.petsjooq.service.PetService;
import com.nikita.petsjooq.service.mapper.PetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
    private final UserRepository userRepository;
    private final PetMapper petMapper;

    @Override
    public List<PetDto> getAll() {
        return petRepository.getAll()
                .map(petMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Pets not found"));
    }

    @Override
    public PetDto getById(Long id) {
        return petRepository.findById(id)
                .map(petMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Pet not found: " + id));
    }

    @Override
    @Transactional
    public PetDto add(PetDto dto) {
        UserModel user = userRepository.findBiId(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found: " + dto.getUserId()));
        PetModel petModel = petMapper.toEntity(dto);
        petModel.setUser(user);
        return petMapper.toDto(petRepository.createPet(petModel)
                .orElseThrow(() -> new RuntimeException("Error while saving")));
    }

    @Override
    public PetDto getByName(String name) {
        return petRepository.findByName(name)
                .map(petMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Pet not found: " + name));
    }

    @Override
    @Transactional
    public void changeById(Long id, PetDto petDto) {
        petRepository.updatePet(petMapper.toEntity(petDto))
                .orElseThrow(() -> new RuntimeException("Error while updating"));
    }

    @Override
    @Transactional
    public void remove(Long id) {
        petRepository.deleteById(id);
    }
}