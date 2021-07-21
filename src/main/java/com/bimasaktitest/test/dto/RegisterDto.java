package com.bimasaktitest.test.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegisterDto {
    @NotBlank
    private String nama;
    @NotBlank
    private Integer age;
    @NotBlank
    private String city;
}
