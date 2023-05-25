package ru.hackathon.sovcombankchallenge.response.dto;

import lombok.Data;
import ru.hackathon.sovcombankchallenge.user.models.User;

@Data
public class CreateResponseDto {
    private Long user;
    private Long vacancy;
}
