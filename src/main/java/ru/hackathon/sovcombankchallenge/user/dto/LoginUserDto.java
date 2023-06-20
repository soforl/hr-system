package ru.hackathon.sovcombankchallenge.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LoginUserDto {
    private String email;
    private String password;
}
