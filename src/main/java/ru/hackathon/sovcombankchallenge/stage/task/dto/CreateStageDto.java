package ru.hackathon.sovcombankchallenge.stage.task.dto;

import lombok.Data;

@Data
public class CreateStageDto {
    private String stageName;
    private Long vacancyId;
}
