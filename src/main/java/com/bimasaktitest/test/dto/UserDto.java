package com.bimasaktitest.test.dto;

import lombok.Data;


@Data
public class UserDto {
    private Long idUser;
    private String name;
    private String city;
    private Integer age;
}
