package ru.hackathon.sovcombankchallenge.stage.task.dto;

import lombok.Data;

@Data
public class CreateTaskDto {
    private String question;
    private String answerFormat;
}
