package com.nikita.petsjooq.service.mapper;

import com.nikita.petsjooq.dto.PetDto;
import com.nikita.petsjooq.example.domain.tables.Pets;
import com.nikita.petsjooq.model.PetModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetMapper {
    PetDto toDto(PetModel petModel);

    PetModel toEntity(PetDto petDto);

    List<PetDto> toDto(List<PetModel> petModels);
}