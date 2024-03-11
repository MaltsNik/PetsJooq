package com.nikita.petsjooq.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserModel {
    private Long id;
    private String fullname;
    private int age;
    private List<PetModel> petList;
}