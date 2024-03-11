package com.nikita.petsjooq.controller;

import com.nikita.petsjooq.dto.UserDto;
import com.nikita.petsjooq.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/add")
    public ResponseEntity<Long> addUser(@RequestBody UserDto userDto) {
        userService.add(userDto);
        return ResponseEntity.status(CREATED).build();
    }

    @PutMapping("/change/{id}")
    public ResponseEntity<UserDto> changeUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        userService.changeById(id, userDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.remove(id);
        return ResponseEntity.ok().build();
    }
}