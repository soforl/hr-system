package ru.hackathon.sovcombankchallenge.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUser {
    @NotBlank
    // TODO: CHECK IF IT EMAIL
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    private String phoneNumber;
}
