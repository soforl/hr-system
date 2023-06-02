package ru.hackathon.sovcombankchallenge.stage.task.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateOpenQuestionDto {
    private UUID stageId;
    private String question;
}
