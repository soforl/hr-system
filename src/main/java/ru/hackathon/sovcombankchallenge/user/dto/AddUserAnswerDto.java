package ru.hackathon.sovcombankchallenge.user.dto;

import lombok.Data;

@Data
public class AddUserAnswerDto {
    private Long userId;
    private String answer;
    private Long questionId;
}
