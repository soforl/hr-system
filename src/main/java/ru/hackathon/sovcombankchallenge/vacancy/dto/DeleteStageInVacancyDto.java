package ru.hackathon.sovcombankchallenge.vacancy.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class DeleteStageInVacancyDto {
    private UUID stageId;
    private UUID vacancyId;
}
