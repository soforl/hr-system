package ru.hackathon.sovcombankchallenge.stage.task.dto;

import lombok.Data;

@Data
public class AddStageToVacancyDto {
    private Long vacancyId;
    private Long stageId;
}
