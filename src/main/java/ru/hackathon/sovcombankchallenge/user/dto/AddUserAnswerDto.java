package ru.hackathon.sovcombankchallenge.user.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AddUserAnswerDto {
    private UUID userId;
    private String answer;
    private UUID questionId;
}
