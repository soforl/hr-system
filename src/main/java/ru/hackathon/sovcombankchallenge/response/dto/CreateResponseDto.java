package ru.hackathon.sovcombankchallenge.response.dto;

import lombok.Data;
import ru.hackathon.sovcombankchallenge.user.models.User;

import java.util.UUID;

@Data
public class CreateResponseDto {
    private UUID user;
    private UUID vacancy;
}
