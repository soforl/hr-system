package ru.hackathon.sovcombankchallenge.stage.task.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateStageDto {
    private String stageName;
    private UUID vacancyId;
}
