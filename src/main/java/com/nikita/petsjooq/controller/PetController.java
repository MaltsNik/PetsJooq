package com.nikita.petsjooq.controller;

import com.nikita.petsjooq.dto.PetDto;
import com.nikita.petsjooq.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/pets")
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;

    @GetMapping("/all")
    public ResponseEntity<List<PetDto>> getAll() {
        List<PetDto> pets = petService.getAll();
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(petService.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Long> add(@RequestBody PetDto petDto) {
        petService.add(petDto);
        return ResponseEntity.status(CREATED).build();
    }

    @PutMapping("/change/{id}")
    public ResponseEntity<PetDto> change(@PathVariable Long id, @RequestBody PetDto petDto) {
        petService.changeById(id, petDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        petService.remove(id);
        return ResponseEntity.ok().build();
    }
}