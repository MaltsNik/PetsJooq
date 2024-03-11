package com.nikita.petsjooq.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PetModel {
    private Long id;
    private String name;
    private int age;
    private String breed;
    private UserModel user;
}