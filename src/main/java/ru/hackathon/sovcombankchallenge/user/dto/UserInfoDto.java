package ru.hackathon.sovcombankchallenge.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.hackathon.sovcombankchallenge.user.models.Role;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserInfoDto {
    private String email;
    private String name;
    private String phoneNumber;
    private String image_url;
    private String role;
    private UUID userId;
}