package ru.hackathon.sovcombankchallenge.stage.task.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AddStageToVacancyDto {
    private UUID vacancyId;
    private UUID stageId;
}
