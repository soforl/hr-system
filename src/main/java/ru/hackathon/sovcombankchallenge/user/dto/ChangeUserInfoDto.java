package ru.hackathon.sovcombankchallenge.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ChangeUserInfoDto {
    private UUID userId;
    private String phoneOrEmail;

}
