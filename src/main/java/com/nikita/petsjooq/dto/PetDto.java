package com.nikita.petsjooq.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PetDto {
    private Long userId;
    private String name;
    private int age;
    private String breed;
}
