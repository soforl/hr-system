package ru.hackathon.sovcombankchallenge.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.hackathon.sovcombankchallenge.user.models.Role;

@Data
@AllArgsConstructor
public class UserInfoDto {
    private String email;
    private String name;
    private String phoneNumber;
    private String image_url;
}
