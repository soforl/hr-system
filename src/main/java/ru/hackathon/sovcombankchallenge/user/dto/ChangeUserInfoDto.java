package ru.hackathon.sovcombankchallenge.user.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ChangeUserInfoDto {
    private UUID userId;
    private String phoneOrEmail;

}
